package com.trascender.contabilidad.gui.abmResumenCaja;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ResumenCajaTableModel extends TAbstractTableModel<TicketCaja> {

	private static final long serialVersionUID = -4064699274656714013L;
	
	public ResumenCajaTableModel() throws Exception {
		super(TicketCaja.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
		
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Caja",TicketCaja.class.getDeclaredField("caja"),Caja.class));
			locListaColumnas.add(new TColumnField("Estado",TicketCaja.class.getDeclaredField("estado"),TicketCaja.Estado.class));
			locListaColumnas.add(new TColumnField("Nº Ticket",TicketCaja.class.getDeclaredField("numero")));
			locListaColumnas.add(new TColumnField("Importe",TicketCaja.class.getDeclaredField("importeTotal"),Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
