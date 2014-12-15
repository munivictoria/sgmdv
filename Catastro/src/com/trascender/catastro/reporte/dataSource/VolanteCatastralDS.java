package com.trascender.catastro.reporte.dataSource;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.TipoPlanta;
import com.trascender.catastro.recurso.persistent.VolanteCatastral;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Planta.Edificacion;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class VolanteCatastralDS extends TrascenderDataSource{

	private int lineaActual = -1;
	private Map <String, Object>parametros;

	public VolanteCatastralDS(VolanteCatastral pVolanteCatastral, Parcela pParcela, List<PlanoMensura> pListaPlanoMensura, List<Zona> pListaZonas){
		parametros = new HashMap<String, Object> ();
//		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
//		parametros.put("PAR_TITULO", this.getTituloReporte());
//		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_NRO_REGISTRO", pVolanteCatastral.getNroVolanteCatastral());
		parametros.put("PAR_NRO_PARCELA", pVolanteCatastral.getParcela().getNomenclaturaCatastral().getNroParcela());
		parametros.put("PAR_MANZANA", Util.getFormatIfNull(pVolanteCatastral.getParcela().getManzana().getNombre()));
		parametros.put("PAR_DOM_PARCELARIO", Util.getFormatIfNull(pVolanteCatastral.getParcela().getDomicilioParcelario().getDomicilioCompleto()));
		parametros.put("PAR_CODIGO_POSTAL", Util.getFormatIfNull(pVolanteCatastral.getParcela().getDomicilioParcelario().getCodigoPostal()));
		parametros.put("PAR_LOCALIDAD", Util.getFormatIfNull(pVolanteCatastral.getParcela().getDomicilioParcelario().getLocalidad().getNombre()));

		if (pVolanteCatastral.getParcela().getPlanta().getTipoPlanta().equals(TipoPlanta.Tipo.URBANA)){
			parametros.put("PAR_RADIO_CENTRICO", "SI");
		}else{
			parametros.put("PAR_RADIO_CENTRICO", "NO");
		}
	
		parametros.put("PAR_TIPO_PLANTA", pVolanteCatastral.getParcela().getPlanta().getTipoEdificacion().toString());
		parametros.put("PAR_PARTIDA_PROVINCIAL", Util.getFormatIfNull(pVolanteCatastral.getParcela().getNroPartidaProvincial()));
		parametros.put("PAR_SUPERFICIE_TERRENO", pVolanteCatastral.getParcela().getSuperficie());
		parametros.put("PAR_METROS_FRENTE", pVolanteCatastral.getParcela().getMetrosFrente());
		parametros.put("PAR_AVALUO_TERRENO", pVolanteCatastral.getParcela().getAvaluoTerreno());
		parametros.put("PAR_AVALUO_MEJORA", pVolanteCatastral.getParcela().getAvaluoPorMejoras());
		parametros.put("PAR_NRO_VOLANTE", pVolanteCatastral.getNroVolanteCatastral());
		parametros.put("PAR_FECHA_VOLANTE", new Date());
		
		if (pParcela.getTituloPropiedad() != null){
			parametros.put("PAR_CONTRIBUYENTE_DS", new VolanteCatastralContribuyentesDS(pParcela.getTituloPropiedad().getListaRegistrosPropietarios()));
		}
		
		parametros.put("PAR_MENSURA_DS", new VolanteCatastralMensurasDS(pListaPlanoMensura));
		parametros.put("PAR_ZONIFICACION_DS", new VolanteCatastralZonificacionDS(pListaZonas));
		parametros.put("PAR_MEJORAS_DS", new VolanteCatastralMejorasDS(pParcela.getListaRegistrosMejora()));
		
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValor = new Object();
//		if("mensurasDS".equals(arg0.getName())){
//			locValor = mensurasDS;
//		}else if("contribuyentesDS".equals(arg0.getName())){
//			locValor = contribuyentesDS;
//		}else if("zonificacionDS".equals(arg0.getName())){
//			locValor = zonificacionDS;
//		}else if("mejorasDS".equals(arg0.getName())){
//			locValor = mejorasDS;
//		} 
		return locValor;
	}
	
	public boolean next() throws JRException {
		return ++lineaActual < 1;
	}

	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	public String getNombreReporte() {
		return "Reporte_Volante_Catastral.jasper";
	}

}
