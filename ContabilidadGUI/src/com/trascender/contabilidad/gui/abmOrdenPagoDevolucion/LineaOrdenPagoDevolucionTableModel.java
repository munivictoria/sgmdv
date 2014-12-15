package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LineaOrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaOrdenPagoDevolucionTableModel extends TAbstractTableModel<LineaOrdenPagoDevolucion> {

	private static final long serialVersionUID = 4171026669939685430L;

	public LineaOrdenPagoDevolucionTableModel() throws Exception {
		super(LineaOrdenPagoDevolucion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas()throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
//			locListaColumnas.add(new TColumn("Orden de Pago", LineaOrdenPagoDevolucion.class.getDeclaredField("ordenPago"), OrdenPagoDevolucion.class));
			locListaColumnas.add(new TColumnField("Ticket de Caja", LineaOrdenPagoDevolucion.class.getDeclaredField("ticketCaja"), TicketCaja.class));
			locListaColumnas.add(new TColumnField("Importe", LineaOrdenPagoDevolucion.class.getDeclaredField("importe"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
