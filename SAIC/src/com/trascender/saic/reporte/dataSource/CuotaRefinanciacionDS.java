/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class CuotaRefinanciacionDS extends TrascenderDataSource {

	private int lineaActual = -1;
	private Map<String, Object> parametros;
	private List<Map<String, Object>> filas = new ArrayList<Map<String, Object>>();

	public CuotaRefinanciacionDS(List<CuotaRefinanciacion> pListaCuotasRefinanciacion, String pTitulo) {
		DocumentoRefinanciacion pDocumento = (DocumentoRefinanciacion) pListaCuotasRefinanciacion.get(0).getDocGeneradorDeuda();
		
		parametros = new HashMap<String, Object>();
		parametros.put("P_TITULO", pTitulo.toUpperCase());
		parametros.put("P_DOCUMENTO_REFINANCIACION", pDocumento);

		for(CuotaRefinanciacion cadaCuota : pListaCuotasRefinanciacion) {
			Map<String, Object> locMapa = new HashMap<String, Object>();
			
			locMapa.put("F_CUOTA_REFINANCIACION", cadaCuota);
//			locMapa.put("F_NUMERO_EN_TEXTO", NumberToText.numeroATexto(new BigDecimal(cadaCuota.getMonto())));
			
			filas.add(locMapa);
		}
	}

	public Object getFieldValue(JRField arg0) throws JRException {
		return filas.get(lineaActual).get(arg0.getName());
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < filas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_Cuotas_Refinanciacion.jasper";
	}

}