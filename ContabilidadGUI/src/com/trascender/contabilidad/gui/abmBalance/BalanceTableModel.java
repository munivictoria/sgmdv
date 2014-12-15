package com.trascender.contabilidad.gui.abmBalance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class BalanceTableModel extends TAbstractTableModel<Balance> {
	
	private static final long serialVersionUID = -6111528481952167625L;

	public BalanceTableModel() throws Exception {
		super(Balance.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", Balance.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Fecha", Balance.class.getDeclaredField("fecha"), Date.class));
			locListaColumnas.add(new TColumnField("Plantilla", Balance.class.getDeclaredField("tipoBalance"), TipoBalance.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
