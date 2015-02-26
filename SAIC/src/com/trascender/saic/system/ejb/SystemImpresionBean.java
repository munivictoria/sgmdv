package com.trascender.saic.system.ejb;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.saic.business.interfaces.BusinessImpresionLocal;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionSHPS;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;
import com.trascender.saic.system.interfaces.SystemImpresion;

@Stateless(name="ejb/SystemImpresion")
//@CacheConfig (maxSize=100000, idleTimeoutSeconds=300, removalTimeoutSeconds=0)
public class SystemImpresionBean implements SystemImpresion {

	@EJB
	private BusinessImpresionLocal businessImpresionLocal;

	private long llave;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4601673838417238085L;

	public SystemImpresionBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx)
			throws EJBException,
			RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {

	}

	@Override
	public void setLlave(long pLlave){
		this.llave = pLlave;
	}

	@Override
	public JasperPrint getReporteOSP( 
			Persona pPersona,Cuadra pCuadra,ServicioOSP pServicioOSP,Boolean pServicioMedido,Calle pCalle,Periodo pPeriodo,EstadoRegistroDeuda pEstadoRegistroDeuda,String pCuentaSubcuenta )
					throws Exception{
		//		return businessImpresionLocal.getReporteOSP(pPersona, pCuadra, pServicioOSP, pServicioMedido, pCalle, (CuotaLiquidacion)pPeriodo, pEstadoRegistroDeuda, pCuentaSubcuenta);
		return null;
	}

	@Override
	public JasperPrint getReportePFO(
			com.trascender.framework.recurso.persistent.Persona pPersona,
			com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra,
			com.trascender.catastro.recurso.persistent.Cuadra pCuadra, 
			com.trascender.catastro.recurso.persistent.Calle pCalle, 
			com.trascender.framework.recurso.transients.Periodo pPeriodo,
			EstadoRegistroDeuda pEstadoRegistroDeuda,
			TipoDeuda pTipoDeuda,
			Integer pCuotaDesde,
			Integer pCuotaHasta
			) throws Exception{
		return null;
		//			return businessImpresionLocal.getReportePFO(pPersona, pObra, pCuadra, pCalle, (CuotaLiquidacion)pPeriodo, pEstadoRegistroDeuda, pTipoDeuda, pCuotaDesde, pCuotaHasta);
	}

	@Override
	public JasperPrint getReporteSHPS(FiltroLiquidacionSHPS filtro) throws Exception{

		Usuario locUsuario = SecurityMgr.getInstance().getUsuario(llave);
		return businessImpresionLocal.getReporteSHPS(filtro, locUsuario);
	}

	@Override
	public JasperPrint getReporteSHPS(List<LiquidacionTasa> pListaLiquidaciones, Usuario pUsuario) throws Exception{
		return businessImpresionLocal.getReporteSHPS(pListaLiquidaciones, pUsuario);
	}
	
	@Override
	public JasperPrint getReporteDDJJSHPS(List<Obligacion> pListaObligaciones, CuotaLiquidacion pCuota) throws Exception{
		return businessImpresionLocal.getReporteDDJJSHPS(pListaObligaciones, pCuota);
	}

	@Override
	public JasperPrint getReporteTGI(
			CuotaLiquidacion pCuota, 
			Persona pPersona, 
			Parcela pParcela, 
			Periodicidad pPeriodicidad, 
			Integer pNumeroCuota, 
			EstadoRegistroDeuda 
			pEstadoRegistroDeuda, 
			boolean pIgualFormaPago,
			TipoDeuda pTipoDeuda) throws Exception{
		return this.businessImpresionLocal.getReporteTGI(pCuota, pPersona, pParcela, pPeriodicidad, pNumeroCuota, pEstadoRegistroDeuda, pIgualFormaPago, pTipoDeuda);
	}

	@Override
	public JasperPrint getReporteListadoCuotasRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion) throws Exception{
		return businessImpresionLocal.getReporteListadoCuotasRefinanciacion(pDocumentoRefinanciacion);
	}

	@Override
	public JasperPrint getReporteReliquidacion(RegistroDeuda pRegistroDeuda,
			Usuario pUsuario,
			boolean pImprimirReporteSimple) 
					throws Exception{
		return businessImpresionLocal.getReporteReliquidacion(pRegistroDeuda, pUsuario, pImprimirReporteSimple);
	}

	@Override
	public JasperPrint getReporteReconocimientoDeuda(DocumentoRefinanciacion pDocumentoRefinanciacion)throws Exception{
		return businessImpresionLocal.getReporteReconocimientoDeuda(pDocumentoRefinanciacion);
	}

	@Override
	public JasperPrint getReporteTasaUnificada(
			List<LiquidacionTasaRefer> pListaLiquidacionesTasa, List<TipoObligacion> pLista, Usuario pUsuario) throws Exception {
		return businessImpresionLocal.getReporteTasaUnificada(pListaLiquidacionesTasa, pLista, pUsuario);
	}

	@Override
	public void imprimirLiquidacionesEnServidor(
			List<LiquidacionTasaRefer> pListaLiquidacionesTasa, Usuario pUsuario) {
		this.businessImpresionLocal.imprimirLiquidacionesEnServidor(pListaLiquidacionesTasa, pUsuario);
	}
	
	@Override
	public JasperPrint getReporteLiquidacionAutomotor(List<LiquidacionTasa> pListaLiquidaciones) throws Exception{
		return businessImpresionLocal.getReporteLiquidacionAutomotor(pListaLiquidaciones);
	}
	
	@Override
	public JasperPrint getReporteLiquidacionOSP(List<LiquidacionTasa> pListaLiquidaciones) throws Exception{
		return businessImpresionLocal.getReporteLiquidacionOSP(pListaLiquidaciones);
	}
	
	public void imprimirLiquidacionesTasasEnServidor(
			List<LiquidacionTasa> pListaLiquidacionesTasa, Usuario pUsuario) {
		this.businessImpresionLocal.imprimirLiquidacionesTasasEnServidor(pListaLiquidacionesTasa, pUsuario);
	}
	
	public  Map<String, JasperPrint> getListaObligacionSHPSPorContador(FiltroObligacionSHPS pFiltro,CuotaLiquidacion pCuota,String pTipoZip) throws Exception{
		return this.businessImpresionLocal.getListaObligacionSHPSPorContador(pFiltro, pCuota,pTipoZip);
	}
}
