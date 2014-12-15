package com.trascender.catastro.system.ejb;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroPlanoMensura;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.VolanteCatastral;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.reporte.dataSource.PadronCatastralDS;
import com.trascender.catastro.reporte.dataSource.VolanteCatastralDS;
import com.trascender.catastro.system.interfaces.SystemAdministracionZonificacion;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.catastro.system.interfaces.SystemReportesCatastro;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemReportesCatastro")
public class SystemReportesCatastroBean implements SystemReportesCatastro{

	private long llave = -1;
	
	@EJB
	private SystemInformacionParcelaria systemParcela;
	
	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
	}
	
	public void setLlave(long pLlave) throws Exception{
		this.llave = pLlave;
		systemParcela.setLlave(llave);
	}

	public JasperPrint getReporteVolanteCatastral(VolanteCatastral pVolanteCatastral) throws Exception{
		Parcela locParcela = this.systemParcela.getParcelaPorId(pVolanteCatastral.getParcela().getIdParcela());
		FiltroPlanoMensura locFiltro = new FiltroPlanoMensura();
		locFiltro.setParcela(locParcela);
		List<PlanoMensura> locListaPlanosMensura = systemParcela.findListaPlanosMensura(locFiltro).getListaResultados(); 
		List<Zona> locListaZonas = this.getSystemZonificacion().getListaZonasFromParcela(locParcela);
		VolanteCatastralDS locVolanteDS = new VolanteCatastralDS(pVolanteCatastral, locParcela, locListaPlanosMensura, locListaZonas);
		URL urlMaestro = this.getClass().getResource("/com/trascender/catastro/reporte/compilado/" + locVolanteDS.getNombreReporte());
		JasperReport JR = (JasperReport)JRLoader.loadObject(urlMaestro);			
		JasperPrint jasperPrint = JasperFillManager.fillReport(JR, locVolanteDS.getMapaParametros(), locVolanteDS);
		return jasperPrint;
	}
	
	public JasperPrint getReportePadronCatastral(List<Parcela> pListasParcela, boolean incluyeMejoras) throws TrascenderException{
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave,Parcela.serialVersionUID,Permiso.Accion.SELECT)) {
				List<Parcela> locListaParcela = new ArrayList<Parcela>();
				for (Parcela cadaParcela : pListasParcela){
					locListaParcela.add(systemParcela.getParcelaPorId(cadaParcela.getIdParcela()));
				}
				URL urlMaestro = this.getClass().getResource("/com/trascender/catastro/reporte/compilado/Reporte_Padron_Catastral.jasper");
				SystemAdministracionZonificacion locSystem = getSystemZonificacion();
				locSystem.setLlave(llave);
				PadronCatastralDS DS = new PadronCatastralDS(locListaParcela, locSystem, incluyeMejoras);
				JasperReport JR = (JasperReport)JRLoader.loadObject(urlMaestro);			
				JasperPrint jasperPrint = JasperFillManager.fillReport(JR, DS.getMapaParametros(), DS);
				return jasperPrint;
			} else {
				throw new CatastroException(791);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CatastroException(414);
		}
	}
	
	public JasperPrint getReporteInformacionParcelaria(long idParcela) throws Exception, RemoteException{
		Parcela locParcela = this.systemParcela.getParcelaPorId(idParcela);
		SystemAdministracionZonificacion locSystemZonificacion = this.getSystemZonificacion();
		InformacionParcelariaDS locDS = new InformacionParcelariaDS(locParcela, locSystemZonificacion, this.systemParcela, "");
		URL urlMaestro = this.getClass().getResource("/com/trascender/catastro/reporte/compilado/" + locDS.getNombreReporte());
		JasperReport JR = (JasperReport) JRLoader.loadObject(urlMaestro);			
		JasperPrint jasperPrint = JasperFillManager.fillReport(JR, locDS.getMapaParametros(), locDS);
		return jasperPrint;
	}
	
	private SystemAdministracionZonificacion getSystemZonificacion() throws TrascenderException{
		try{
			Context ctx = new InitialContext();
			SystemAdministracionZonificacion locSystemZonificacion = (SystemAdministracionZonificacion) ctx.lookup(SystemAdministracionZonificacion.JNDI_NAME);
			locSystemZonificacion.setLlave(llave);
			return locSystemZonificacion;
		} catch (Exception e){
			throw new CatastroException(791);
		}
	}
}
