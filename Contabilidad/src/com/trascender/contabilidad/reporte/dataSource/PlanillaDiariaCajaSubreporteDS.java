package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class PlanillaDiariaCajaSubreporteDS  extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List<TicketCaja> listaLineas;
	
	public PlanillaDiariaCajaSubreporteDS(List<TicketCaja> plistaDetalleTicketCaja) throws Exception{
		parametros = new HashMap<String, Object> ();
		listaLineas = new ArrayList<TicketCaja>();

		for (TicketCaja cadaLinea : plistaDetalleTicketCaja) {
			listaLineas.add(cadaLinea);
		}
	}
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object locValor = null;
		
		if("F_TICKET_NUMERO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getNumero());
		}else if ("F_ESTADO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getEstado().toString());
		}else if ("F_TICKET_IMPORTE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull((listaLineas.get(lineaActual)).getImporteTotal());
		}
	
	return locValor;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getMapaParametros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombreReporte() {
		// TODO Auto-generated method stub
		return null;
	}

}
