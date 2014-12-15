package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoRecursos;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.framework.util.TrascenderDataSource;

public class PresupuestoRecursosDS extends TrascenderDataSource {

	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <LineaPresupuestoRecursos> listaLineas;
	private Presupuesto locPresupuesto;
	
	public PresupuestoRecursosDS(Presupuesto pPresupuesto) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<LineaPresupuestoRecursos>();
		locPresupuesto = pPresupuesto;
		
		parametros = new HashMap <String, Object> ();
		listaLineas = new ArrayList<LineaPresupuestoRecursos>();
		parametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		parametros.put("PAR_TITULO", this.getTituloReporte());
		parametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		parametros.put("P_TIPO", pPresupuesto.getTipo().toString());
		parametros.put("P_NOMBRE", pPresupuesto.getNombre());
		parametros.put("P_ESTADO", pPresupuesto.getEstado().toString());
		parametros.put("P_DECRETO", pPresupuesto.getDigestoMunicipal().getNumero().toString());
		parametros.put("P_ANIO", pPresupuesto.getFecha());
		
		for (LineaPresupuesto cadaLinea : pPresupuesto.getLineaPresupuesto()) {
			listaLineas.add((LineaPresupuestoRecursos)cadaLinea);
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
Object locValor = null;
		
		if ("CUENTA".equals(arg0.getName())){
			locValor = listaLineas.get(lineaActual).getCuenta().getCodigoImputacionCompleto() + " " + listaLineas.get(lineaActual).getCuenta().getNombre();
		}else if ("MONTO_ESTIMADO".equals(arg0.getName())) {
			if(listaLineas.get(lineaActual).getMontoEstimado() != null){
				locValor = listaLineas.get(lineaActual).getMontoEstimado();
			}else{
				locValor = 0d;
			}
		}else if ("MONTO_RECAUDADO".equals(arg0.getName())){
			if(listaLineas.get(lineaActual).getMontoRecaudado() != null){
				locValor = (listaLineas.get(lineaActual)).getMontoRecaudado();
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
		return "Reporte_Presupuesto_Recursos.jasper";
	}

}
