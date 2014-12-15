package com.trascender.habilitaciones.business.ejb;

import java.io.File;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoOSPLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTGILocal;
import com.trascender.habilitaciones.business.interfaces.BusinessReportesHabilitacionesLocal;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionOSP;
import com.trascender.habilitaciones.recurso.persistent.FiltroObligacionTGI;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.reporte.dataSource.InformacionParcelariaDS;

@Stateless(name = "ejb/BusinessReportesHabilitaciones")
public class BusinessReportesHabilitacionesBean implements BusinessReportesHabilitacionesLocal{
	
	@EJB
	private BusinessRegistroParcelarioLocal businessRegistroParcelarioLocal;

	@EJB
	private BusinessDocumentoTGILocal businessDocumentoTGILocal;
	
	@EJB
	private BusinessDocumentoOSPLocal businessDocumentoOSPLocal;
	
	@EJB
	private BusinessZonificacionLocal businessZonificacionLocal;
	
	public JasperPrint getReporteInformacionParcelaria(long idParcela)	throws Exception {

		DocumentoTGI documentoTGI= null;
		DocumentoOSP documentoOSP= null;
		Parcela locParcela = this.businessRegistroParcelarioLocal.getParcelaPorId(idParcela);
		FiltroObligacionTGI locFiltroTGI = new FiltroObligacionTGI();
		locFiltroTGI.setParcela(locParcela);
		locFiltroTGI = businessDocumentoTGILocal.findListaObligacionesTGI(locFiltroTGI);
		List<Obligacion> listaObligacionesTGI = locFiltroTGI.getListaResultados();
		if (listaObligacionesTGI != null){
			documentoTGI = (DocumentoTGI) listaObligacionesTGI.get(0).getDocumentoEspecializado();
		}
		FiltroObligacionOSP locFiltroOSP = new FiltroObligacionOSP();
		locFiltroOSP.setParcela(locParcela);
		locFiltroOSP = businessDocumentoOSPLocal.findListaObligacionesOSP(locFiltroOSP);
		List<Obligacion> listaObligacionesOSP = locFiltroOSP.getListaResultados();
		if (!listaObligacionesOSP.isEmpty()){
			documentoOSP = (DocumentoOSP) listaObligacionesOSP.get(0).getDocumentoEspecializado();
		}
		
		InformacionParcelariaDS locDS = new InformacionParcelariaDS(locParcela, documentoTGI, documentoOSP, businessZonificacionLocal);
		String rutaReportes = SecurityMgr.getInstance().getMunicipalidad().getRutaReportes();
		File fileReporte = new File(rutaReportes + locDS.getNombreReporte());
		JasperReport JR = (JasperReport)JRLoader.loadObject(fileReporte);
		JR.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		JasperPrint jasperPrint = JasperFillManager.fillReport(JR, locDS.getMapaParametros(), locDS);
		return jasperPrint;
	
	}

}
