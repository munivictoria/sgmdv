package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.saic.recurso.interfaces.Pagable;

public class PlanillaDiariaCajaIngresoVarioDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List<Map<String, Object>> filas = new ArrayList<Map<String,Object>>();
	
	public PlanillaDiariaCajaIngresoVarioDS(List<TicketCaja> plistaTicketCaja) throws Exception{
		parametros = new HashMap<String, Object> ();
		
		Pagable locDeuda = null;
		for (TicketCaja cadaTicketCaja : plistaTicketCaja){
			locDeuda = cadaTicketCaja.getDetalles().iterator().next().getDeuda();
			IngresoVario locIngreso = (IngresoVario) locDeuda;
			for (ImputacionIngresoVario cadaImputacion : locIngreso.getListaImputacionIngresos()) {
				Map<String, Object> locMapa = new HashMap<String, Object>();
				locMapa.put("F_CONCEPTO", locIngreso.getConceptoIngresoVario().getNombre());
				locMapa.put("F_CUENTA", cadaImputacion.getCuenta().getNombre());
				locMapa.put("F_INGRESO", locIngreso.getNumero());
				locMapa.put("F_MONTO_IMPUTACION", cadaImputacion.getMonto());
				locMapa.put("F_NRO_TICKET", cadaTicketCaja.getNumero());
				
				String cuit = cadaTicketCaja.getStringPersona();
				int pos = cuit.lastIndexOf("[");
				String nombre = cuit.substring(0, pos-1);
				cuit = cuit.substring(pos +1, cuit.length() -1);
				locMapa.put("F_PERSONA", nombre);
				locMapa.put("F_CUIT", cuit);
				
				filas.add(locMapa);
			}
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
		return "Reporte_Planilla_Diaria_Caja_Ingresos_Varios.jasper";
	}

}
