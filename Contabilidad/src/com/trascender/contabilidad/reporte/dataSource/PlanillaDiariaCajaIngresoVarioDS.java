package com.trascender.contabilidad.reporte.dataSource;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.contabilidad.recurso.persistent.TicketCaja;

public class PlanillaDiariaCajaIngresoVarioDS extends ResumenCajaGeneralDS{
	private static final long serialVersionUID = 8348722710928477967L;
	
	private int lineaActual = -1;
	
	
	public PlanillaDiariaCajaIngresoVarioDS(List<TicketCaja> plistaTicketCaja) {
		super(plistaTicketCaja);
	}
	
	public Object getFieldValue(JRField field) throws JRException {
		if (field.getName().equals("F_DETALLE_TICKET")) return listaTicketsCaja.get(lineaActual).getDetalles().iterator().next();
		if (field.getName().equals("F_INGRESO_VARIO")) return listaTicketsCaja.get(lineaActual).getDetalles().iterator().next().getDeuda();
 		return null;
	}

	@Override
	public boolean next() throws JRException {
		return ++lineaActual < listaTicketsCaja.size();
	}

	public String getNombreReporte() {
		return "ReportePlanillaDiariaCajaIngresosVariosMaestro.jasper";
	}
	

}
