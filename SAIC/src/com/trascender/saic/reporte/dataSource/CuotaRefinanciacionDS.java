/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class CuotaRefinanciacionDS extends TrascenderDataSource {

	private int lineaActual = -1;
	private Map<String, Object> parametros;
	private List<Map<String, Object>> filas = new ArrayList<Map<String, Object>>();

	public CuotaRefinanciacionDS(List<CuotaRefinanciacion> pListaCuotasRefinanciacion, String pTitulo, Usuario pUsuario) {
		DocumentoRefinanciacion pDocumento = (DocumentoRefinanciacion) pListaCuotasRefinanciacion.get(0).getDocGeneradorDeuda();
		
		parametros = new HashMap<String, Object>();
		parametros.put("P_TITULO", pTitulo.toUpperCase());
		parametros.put("P_DOCUMENTO_REFINANCIACION", pDocumento);
		parametros.put("P_USUARIO", pUsuario.getUser());
		
		if (pDocumento.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda().iterator().next() instanceof LiquidacionTasa) {
			LiquidacionTasa liq = (LiquidacionTasa) pDocumento.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda().iterator().next();
			DocHabilitanteEspecializado doc = liq.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
			Parcela locParcela = doc.getParcela();
			if (doc instanceof DocumentoTGI) {
				parametros.put("PAR_DOCUMENTO_TGI", doc);
			} else if (doc instanceof DocumentoOSP) {
				parametros.put("PAR_DOCUMENTO_OSP", doc);
			} else if (doc instanceof DocumentoSHPS) {
				parametros.put("PAR_DOCUMENTO_SHPS", doc);
			}
			parametros.put("PAR_PARCELA", locParcela);
		}

		for(CuotaRefinanciacion cadaCuota : pListaCuotasRefinanciacion) {
			Map<String, Object> locMapa = new HashMap<String, Object>();
			
			locMapa.put("F_CUOTA_REFINANCIACION", cadaCuota);
//			locMapa.put("F_NUMERO_EN_TEXTO", NumberToText.numeroATexto(new BigDecimal(cadaCuota.getMonto())));
			
			filas.add(locMapa);
		}
		List<CuotaLiquidacion> listaCuotasAbarcadas = new ArrayList<CuotaLiquidacion>();
		for (RegistroDeuda cadaRegistro : pDocumento.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()) {
			if (cadaRegistro instanceof LiquidacionTasa) {
				LiquidacionTasa cadaLiq = (LiquidacionTasa) cadaRegistro;
				listaCuotasAbarcadas.add(cadaLiq.getCuotaLiquidacion());
			}
		}
		Collections.sort(listaCuotasAbarcadas);
		List<String> listaCuotasString = new ArrayList<String>();
		for (CuotaLiquidacion cadaCuota : listaCuotasAbarcadas) {
			String cuotaString = cadaCuota.getAnio() + "-" + cadaCuota.getPeriodo().getNumero();
			if (!listaCuotasString.contains(cuotaString)) {
				listaCuotasString.add(cuotaString);
			}
		}
		parametros.put("PAR_PERIODOS_ABARCADOS", Util.getStringDeLista(listaCuotasString, "; "));
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