package com.trascender.contabilidad.reporte.dataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;

import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;
import com.trascender.saic.recurso.interfaces.Pagable;

public class PlanillaDiariaCajaIngresoVarioSubreporteDS extends TrascenderDataSource{
	private int lineaActual = -1;
	private Map <String, Object>parametros;
	private List<TicketCaja> listaLineas;
	private JasperReport reportePlanillaDiariaCajaIngresoVario;
	
	public PlanillaDiariaCajaIngresoVarioSubreporteDS(List<TicketCaja> plistaDetalleTicketCaja) throws Exception{
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
		
		List<DetalleTicketCaja> locListaDetalleTicketCaja = (List<DetalleTicketCaja>) listaLineas.get(lineaActual).getDetalles();
		Pagable locDeuda = locListaDetalleTicketCaja.get(0).getDeuda();
		
		if("F_TICKET_NUMERO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getNumero());
		}else if ("F_FECHA_CANCELACION".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(locListaDetalleTicketCaja.get(0).getFechaCancelacion());
		}else if ("F_SELLADO_NUEMRO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(((IngresoVario)locDeuda).getNumero());
		}else if ("F_SELLADO_ESTADO".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(((IngresoVario)locDeuda).getEstado());
		}else if ("F_PERSONA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(((IngresoVario)locDeuda).getPersona().getDenominacion());
		}else if ("CONCEPTO_NOMBRE".equals(arg0.getName())){
		}else if ("F_PERSONA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(((IngresoVario)locDeuda).getPersona().getDenominacion());
		}else if ("F_CONCEPTO_NOMBRE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(((IngresoVario)locDeuda).getConceptoIngresoVario().getDescripcion());
		}else if ("F_SELLADO_FECHA".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(((IngresoVario)locDeuda).getFechaEmision());
		}else if ("F_TICKET_IMPORTE".equals(arg0.getName())){
			locValor = Util.getFormatIfNull(listaLineas.get(lineaActual).getImporteTotal());
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
		return "Reporte_Planilla_Diaria_Caja_Sellado.jasper";
	}

}
