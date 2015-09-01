package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionFormula;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

public class PreImpresionRefinanciacionDS extends TrascenderDataSource{
	
	List<Map<String, Object>> mapaLineas = new ArrayList<Map<String,Object>>();
	Map<String, Object> mapaParametro = new HashMap<String, Object>();
	private int lineaActual = -1;
	List<Pagable> listaLiquidacionTasa = new ArrayList<Pagable>();
	
	public PreImpresionRefinanciacionDS(DocumentoRefinanciacion pDocumento) {
		
		Map<String, Object> locMapa;
		mapaParametro.put("PAR_TITULO", "Titulo");
		mapaParametro.put("PAR_SUBTITULO", "Subtitulo");
		mapaParametro.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		
		List<LiquidacionTasaAgrupada> listaLiquidacion = new ArrayList<LiquidacionTasaAgrupada>();
		
		for (RegistroDeuda cadaRD : pDocumento.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()) {
			listaLiquidacion.add((LiquidacionTasaAgrupada) cadaRD);
		}
		
		//Todas las liquidaciones tienen la misma fecha de vencimiento, se toma una cualquiera.
		Date ultimaFechaVencimiento = listaLiquidacion.get(0).getListaLiquidacionesTasa().get(0).getListaVencimientos().last().getFecha();
		mapaParametro.put("PAR_FECHA_LIQUIDACION_DEUDA", ultimaFechaVencimiento);
		
		Parcela locParcela = listaLiquidacion.get(0).getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela();
		DocumentoTGI locDocTGI = listaLiquidacion.get(0).getDocHabilitanteEspecializado(DocumentoTGI.class);
		DocumentoOSP locDocOSP = listaLiquidacion.get(0).getDocHabilitanteEspecializado(DocumentoOSP.class);
		DocumentoSHPS locDocSHPS = listaLiquidacion.get(0).getDocHabilitanteEspecializado(DocumentoSHPS.class);
		mapaParametro.put("PAR_PARCELA", locParcela);
		mapaParametro.put("PAR_DOCUMENTO_TGI", locDocTGI);
		mapaParametro.put("PAR_DOCUMENTO_OSP", locDocOSP);
		mapaParametro.put("PAR_DOCUMENTO_SHPS", locDocSHPS);
		mapaParametro.put("PAR_DOCUMENTO_REFINANCIACION", pDocumento);
		
		for (LiquidacionTasaAgrupada cadaLiquidacionAgrupada : listaLiquidacion) {
			//Importe de cada tasa:
			for (LiquidacionTasa cadaLiquidacion : cadaLiquidacionAgrupada.getListaLiquidacionesTasa()) {
				//Primero actualizar la deuda.
				listaLiquidacionTasa.add(cadaLiquidacion);
				locMapa = this.getMapaInicial(cadaLiquidacionAgrupada);
				String nombreObligacion = cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre();
				locMapa.put("F_NOMBRE_COLUMNA", nombreObligacion.equalsIgnoreCase("OYSP") ? "OSM" : nombreObligacion);
				locMapa.put("F_VALOR_COLUMNA",  cadaLiquidacion.getValorTasaConDescuentos() 
							+ cadaLiquidacion.getSumaRecargosManuales());
				mapaLineas.add(locMapa);
			}
			//modificadores
			for (ModificadorLiquidacionFormula cadaModificador : 
				cadaLiquidacionAgrupada.getListaModificadoresLiquidacionFormula()) {
				locMapa = getMapaInicial(cadaLiquidacionAgrupada);
				locMapa.put("F_NOMBRE_COLUMNA", cadaModificador.getNombre());
				locMapa.put("F_VALOR_COLUMNA", cadaModificador.getValorModificador());
				mapaLineas.add(locMapa);
			}
			//Intereses
			locMapa = getMapaInicial(cadaLiquidacionAgrupada);
			locMapa.put("F_NOMBRE_COLUMNA", "ReAc");
			locMapa.put("F_VALOR_COLUMNA", cadaLiquidacionAgrupada.getInteres());
			mapaLineas.add(locMapa);
		}
	}
	
	private Map<String, Object> getMapaInicial(LiquidacionTasa pLiquidacion) {
		Map<String, Object> locMapa = new HashMap<String, Object>();
		locMapa.put("F_LIQUIDACION_TASA", pLiquidacion);
		locMapa.put("F_CUOTA_LIQUIDACION", 
				pLiquidacion.getCuotaLiquidacion());
		locMapa.put("F_DOCUMENTO_ESPECIALIZADO", pLiquidacion.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado());
		return locMapa;
	}

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		return mapaLineas.get(lineaActual).get(arg0.getName());
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < mapaLineas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return mapaParametro;
	}

	@Override
	public String getNombreReporte() {
		return "PreImpresionRefinanciacion.jasper";
	}

}
