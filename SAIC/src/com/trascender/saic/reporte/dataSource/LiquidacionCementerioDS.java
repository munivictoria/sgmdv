package com.trascender.saic.reporte.dataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

public class LiquidacionCementerioDS extends TrascenderDataSource{
	
	private List<LiquidacionTasa> listaLiquidaciones;
	
	private int lineaActual = -1;
	Map<String,Object> mapaParametros = new HashMap<String, Object>();
	
	public LiquidacionCementerioDS(List<LiquidacionTasa> listaLiquidaciones, Usuario pUsuario) {
		super();
		this.listaLiquidaciones = listaLiquidaciones;
		this.mapaParametros.put("P_USUARIO", pUsuario);
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if (field.getName().equals("F_LIQUIDACION")) {
			return listaLiquidaciones.get(lineaActual);
		} else if (field.getName().equals("F_DOCUMENTO")) {
			return listaLiquidaciones.get(lineaActual).getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado();
		}
		return null;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < listaLiquidaciones.size();
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		return mapaParametros;
	}

	@Override
	public String getNombreReporte() {
		return "Reporte_LiquidacionCementerio.jasper";
	}
}
