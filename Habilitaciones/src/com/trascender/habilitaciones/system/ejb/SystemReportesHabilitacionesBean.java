package com.trascender.habilitaciones.system.ejb;

import java.net.URL;
import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.business.interfaces.BusinessReportesHabilitacionesLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.reporte.dataSource.LibretaSanitariaDS;
import com.trascender.habilitaciones.system.interfaces.SystemBromatologia;
import com.trascender.habilitaciones.system.interfaces.SystemReportesHabilitaciones;

@Stateful(name = "ejb/SystemReportesHabilitaciones")
public class SystemReportesHabilitacionesBean implements SystemReportesHabilitaciones{
	
	@EJB
	private SystemBromatologia systemBromatologia;
	
	@EJB
	private BusinessReportesHabilitacionesLocal businessReportesHabilitacionesLocal;
	
	private long llave;
	
	public void setLlave(long pLlave) throws Exception{
		this.llave = pLlave;
		this.systemBromatologia.setLlave(llave);
	}
	
	public JasperPrint getReporteLibretaSanitaria(long pIdLibretaSanitaria) throws TrascenderException, RemoteException {
		try{
			LibretaSanitaria locLocLibreta = this.systemBromatologia.getLibretaSanitariaPorId(pIdLibretaSanitaria);
			LibretaSanitariaDS ds = new LibretaSanitariaDS(locLocLibreta);
			URL urlMaestro = this.getClass().getResource("/com/trascender/habilitaciones/reporte/compilado/"+ds.getNombreReporte());
			JasperReport JR = (JasperReport)JRLoader.loadObject(urlMaestro);
			JR.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint = JasperFillManager.fillReport(JR, ds.getMapaParametros(), ds);
			return jasperPrint;
		}catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(55);
		}
	}
	
	public JasperPrint getReporteInformacionParcelaria(long idParcela) throws Exception, RemoteException{
		return businessReportesHabilitacionesLocal.getReporteInformacionParcelaria(idParcela);
	}

}
