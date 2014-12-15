package com.trascender.caja.gui.cobro;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.PagoTicket;
import com.trascender.gui.framework.component.TColumn;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.component.TColumnGeter;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class PagoTicketTableModel extends TAbstractTableModel<PagoTicket>{

	private static final long serialVersionUID = -5688600876608803806L;

	public PagoTicketTableModel() throws Exception {
		super(PagoTicket.class);
		this.setListaColumnas(this.inicializarColumnas());
	}
	
	private List<TColumn> inicializarColumnas() throws Exception {
		List<TColumn> locListaColumnas = new ArrayList<TColumn>();
		try {
			locListaColumnas.add(new TColumnGeter("Descripción",PagoTicket.class.getDeclaredMethod("getDescripcion")));
			locListaColumnas.add(new TColumnField("Monto",PagoTicket.class.getDeclaredField("monto"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {			
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}


}
