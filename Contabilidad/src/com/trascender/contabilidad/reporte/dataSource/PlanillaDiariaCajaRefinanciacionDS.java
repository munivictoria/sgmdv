package com.trascender.contabilidad.reporte.dataSource;

import java.util.List;

import com.trascender.contabilidad.recurso.persistent.TicketCaja;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PlanillaDiariaCajaRefinanciacionDS extends ResumenCajaGeneralDS {
	private static final long serialVersionUID = -7815814404578024291L;
	
	
	private int lineaActual = -1;

	public PlanillaDiariaCajaRefinanciacionDS(List<TicketCaja> pListaTickets) {
		super(pListaTickets);
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if (field.getName().equals("F_DETALLE_TICKET")) return listaTicketsCaja.get(lineaActual).getDetalles().iterator().next();
		if (field.getName().equals("F_CUOTA")) return listaTicketsCaja.get(lineaActual).getDetalles().iterator().next().getDeuda();
 		return null;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < listaTicketsCaja.size();
	}

	public String getNombreReporte() {
		return "ReportePlanillaDiariaCajaRefinanciacion.jasper";
	}

}
