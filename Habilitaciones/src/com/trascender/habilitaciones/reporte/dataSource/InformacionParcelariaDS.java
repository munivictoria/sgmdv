package com.trascender.habilitaciones.reporte.dataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;

public class InformacionParcelariaDS extends TrascenderDataSource {

	private Map <String, Object>parametros;
	private BusinessZonificacionLocal businessZonificacion;

	public InformacionParcelariaDS (Parcela pParcela, DocumentoTGI pDocumentoTGI, 
			DocumentoOSP pDocumentoOSP, BusinessZonificacionLocal pBusinessZonificacion){
		
		this.businessZonificacion = pBusinessZonificacion;
		
		parametros = new HashMap<String, Object> ();
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_PARCELA", pParcela);
		parametros.put("PAR_DOCUMENTO_TGI", pDocumentoTGI);
		parametros.put("PAR_DOCUMENTO_OSM", pDocumentoOSP);
		parametros.put("PAR_TITULAR", pParcela
				.getTituloPropiedad()
				.getRegistroPropietarioEncargadoObligaciones().getPersona());
		parametros.put("PAR_STRING_ZONAS", this.getZonas(pParcela));
	}
	
	private String getZonas(Parcela pParcela){
		String retorno = "";
		if (this.businessZonificacion != null) {
			try{
				List<Zona> locListaZona = this.businessZonificacion.getZonasFromParcela(pParcela);
				retorno = locListaZona.get(0).getNombre();
			} catch (Exception e){
			}
		}
		return retorno;
	}

	public Object getFieldValue(JRField arg0) throws JRException {
		return null;
	}

	public boolean next() throws JRException {
		return false;
	}

	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	public String getNombreReporte() {
		return "Reporte_Informacion_Parcelaria.jasper";
	}
}
