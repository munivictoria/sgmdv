package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.LineaOrdenPagoDevolucion;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class OrdenPagoDevolucionSubreporteDevolucionDS  extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List <LineaOrdenPagoDevolucion> listaLineas;
	
	public OrdenPagoDevolucionSubreporteDevolucionDS(List<LineaOrdenPagoDevolucion> plistaLineaOrdenPagoDevolucion) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<LineaOrdenPagoDevolucion>();

		for (LineaOrdenPagoDevolucion cadaLinea : plistaLineaOrdenPagoDevolucion) {
			listaLineas.add(cadaLinea);
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if("F_NUMERO_TICKET".equals(arg0.getName())){
			locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getTicketCaja().getNumero());
		}else if ("F_FECHA_TICKET".equals(arg0.getName())){
			locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getTicketCaja().getFecha());
		}else if ("F_IMPORTE_DEVOLUCION".equals(arg0.getName())){
			locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getImporte());
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
		return null;
	}

}
