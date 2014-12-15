package com.trascender.saic.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

public class LiquidacionAutomotorDS extends TrascenderDataSource{

	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List<Map<String, Object>> filas = new ArrayList<Map<String,Object>>();
	
	public LiquidacionAutomotorDS (List<LiquidacionTasa> pListaLiquidacionTasa, String pTitulo, String pSubTitulo){
		parametros = new HashMap<String, Object> ();
		parametros.put("PAR_TITULO", pTitulo);
		parametros.put("PAR_SUBTITULO", pSubTitulo);
		
		for (LiquidacionTasa cadaLiquidacionTasa : pListaLiquidacionTasa){
				Map<String, Object> locMapa = new HashMap<String, Object>();
				locMapa.put("F_LIQUIDACION_TASA", cadaLiquidacionTasa);
				locMapa.put("F_DOCUMENTO_AUTOMOTOR", cadaLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado());
				filas.add(locMapa);
		}
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		return filas.get(lineaActual).get(arg0.getName());
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lineaActual < filas.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Liquidacion_Automotor.jasper";
	}

	
	
}
