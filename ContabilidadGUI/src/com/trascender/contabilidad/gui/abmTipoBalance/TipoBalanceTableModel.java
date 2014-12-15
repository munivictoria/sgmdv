package com.trascender.contabilidad.gui.abmTipoBalance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class TipoBalanceTableModel extends TAbstractTableModel<TipoBalance>{

	private static final long serialVersionUID = 5513255615940373091L;
	
	public TipoBalanceTableModel() throws Exception {
		super(TipoBalance.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", TipoBalance.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Fecha", TipoBalance.class.getDeclaredField("fecha"), Date.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6); 
		}
	}
}
