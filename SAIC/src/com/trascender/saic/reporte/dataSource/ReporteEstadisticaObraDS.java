package com.trascender.saic.reporte.dataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.saic.recurso.transients.LineaEstadisticaObra;

public class ReporteEstadisticaObraDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <LineaEstadisticaObra> listaLineas;
	
	public ReporteEstadisticaObraDS(List<LineaEstadisticaObra> plistaLineaEstadisticaObra, Obra pObra,String pImagen){
		parametros = new HashMap<String, Object> ();
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_OBRA",pObra.getNumeroObra() + " - " + Util.getFormatIfNull(pObra.getDescripcion()));
		
		listaLineas = plistaLineaEstadisticaObra;
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		Object locValor = null;
		if("F_COUTA".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getNumeroPeriodo();
		}else if("F_CANT".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getCantidadACobrar();
		}else if("F_TOTAL_A_COBRAR".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getTotalACobrar();
		}else if("F_CANT_COBRADA".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getCantidadCobrados();
		}else if("F_TOTAL_COBRADO".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getTotalCobrado();
		}else if("F_CANT_SIN_COBRAR".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getCantidadNoCobrados();
		}else if("F_TOTAL_SIN_COBRAR".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getTotalNoCobrados();
		}
		return locValor;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < listaLineas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_EstadisticaObra.jasper";
	}

}
