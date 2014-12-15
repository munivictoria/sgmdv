package com.trascender.contabilidad.gui.abmTicketCaja;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja.Estado;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class TicketCajaTableModel extends TAbstractTableModel<TicketCaja>{
	
	private static final long serialVersionUID = -322325888074045570L;
	
	public TicketCajaTableModel() throws Exception {
		super(TicketCaja.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nº", TicketCaja.class.getDeclaredField("numero"), Integer.class));
			locListaColumnas.add(new TColumnField("Detalle", TicketCaja.class.getDeclaredField("detalles")));
			locListaColumnas.add(new TColumnField("Estado", TicketCaja.class.getDeclaredField("estado"), Estado.class));
			locListaColumnas.add(new TColumnField("Fecha", TicketCaja.class.getDeclaredField("fecha"), Date.class));
			locListaColumnas.add(new TColumnField("Importe Total", TicketCaja.class.getDeclaredField("importeTotal"), Double.class));
			locListaColumnas.add(new TColumnField("Usuario", TicketCaja.class.getDeclaredField("usuario"), Usuario.class));
			locListaColumnas.add(new TColumnField("Caja", TicketCaja.class.getDeclaredField("caja"), Caja.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
