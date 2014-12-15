package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoGastos;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.framework.util.TrascenderDataSource;

public class PresupuestoGastosDS extends TrascenderDataSource {

	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <LineaPresupuestoGastos> listaLineas;
	private Presupuesto locPresupuesto;
	
	public PresupuestoGastosDS(Presupuesto pPresupuesto) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<LineaPresupuestoGastos>();
		locPresupuesto = pPresupuesto;
		
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("P_TIPO", locPresupuesto.getTipo().toString());
		parametros.put("P_NOMBRE", locPresupuesto.getNombre());
		parametros.put("P_ESTADO", locPresupuesto.getEstado().toString());
		parametros.put("P_DECRETO", locPresupuesto.getDigestoMunicipal().getNumero().toString());
		parametros.put("P_ANIO", locPresupuesto.getFecha());
		
		for (LineaPresupuesto cadaLinea : pPresupuesto.getLineaPresupuesto()) {
			listaLineas.add((LineaPresupuestoGastos)cadaLinea);
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if ("CUENTA".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getCuenta().getCodigoImputacionCompleto() + " " + listaLineas.get(lineaActual).getCuenta().getNombre();
		}else if ("MONTO_PRESUPUESTADO".equals(arg0.getName())) {
			if(listaLineas.get(lineaActual).getMontoPresupuestado() != null){
				locValor = listaLineas.get(lineaActual).getMontoPresupuestado();
			}else{
				locValor = 0d;
			}
		}else if ("MONTO_COMPROMETIDO".equals(arg0.getName())){
			if(listaLineas.get(lineaActual).getMontoComprometido() != null){
				locValor = listaLineas.get(lineaActual).getMontoComprometido();
			}else{
				locValor = 0d;
			}
		}else if ("MONTO_PAGADO".equals(arg0.getName())){
			if(listaLineas.get(lineaActual).getMontoPagado() != null){
				locValor = listaLineas.get(lineaActual).getMontoPagado();
			}else{
				locValor = 0d;
			}
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
		return "Reporte_Presupuesto_Gastos.jasper";
	}

}
