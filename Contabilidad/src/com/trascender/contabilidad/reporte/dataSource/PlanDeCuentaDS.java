package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class PlanDeCuentaDS extends TrascenderDataSource {
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List<Cuenta> listaLineas;
	private PlanDeCuenta locPlanDeCuenta;
	
	public PlanDeCuentaDS(PlanDeCuenta pPlanDeCuenta) throws Exception{
		parametros = new HashMap<String, Object> ();
		locPlanDeCuenta = pPlanDeCuenta;
		listaLineas = new ArrayList<Cuenta>();
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("PAR_DESCRIPCION", locPlanDeCuenta.getDescripcion());
		parametros.put("PAR_FECHA_ALTA", locPlanDeCuenta.getFechaAlta());

		for (Cuenta cadaLinea : pPlanDeCuenta.getListaCuentas()) {
			listaLineas.add(cadaLinea);
		}
	}
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if("F_CODIGO_IMPUTACION".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getCodigoImputacion());
		}else if ("F_NOMBRE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getNombre());
		}
		
		return locValor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++lineaActual < 1;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return parametros;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return "Reporte_Plan_Cuentas.jasper";
	}

}
