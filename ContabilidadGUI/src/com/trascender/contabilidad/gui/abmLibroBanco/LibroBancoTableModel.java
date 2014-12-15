package com.trascender.contabilidad.gui.abmLibroBanco;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LibroBancoTableModel extends TAbstractTableModel<LibroBanco>{

	private static final long serialVersionUID = 5513255615940373091L;

	public LibroBancoTableModel() throws Exception {
		super(LibroBanco.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception{
		List<TColumnField> locListaColumas = new ArrayList<TColumnField>();
		try {
			locListaColumas.add(new TColumnField("Nombre", LibroBanco.class.getDeclaredField("nombre")));
			locListaColumas.add(new TColumnField("Cuenta Bancaria", LibroBanco.class.getDeclaredField("cuentaBancaria"),CuentaBancaria.class));
			
			return locListaColumas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
