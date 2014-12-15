package com.trascender.caja.gui.resumenActualCaja;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ResumenActualCajaTableModel extends TAbstractTableModel<TicketCaja> {

	
	private static final long serialVersionUID = -3010732546261623802L;
	
	public ResumenActualCajaTableModel() throws Exception {
		super(TicketCaja.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws TrascenderException {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nº Ticket",TicketCaja.class.getDeclaredField("numero")));
			locListaColumnas.add(new TColumnField("Estado",TicketCaja.class.getDeclaredField("estado"),TicketCaja.Estado.class));
			locListaColumnas.add(new TColumnField("Importe",TicketCaja.class.getDeclaredField("importeTotal"),Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
