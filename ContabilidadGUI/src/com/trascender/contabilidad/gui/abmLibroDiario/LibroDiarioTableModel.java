package com.trascender.contabilidad.gui.abmLibroDiario;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LibroDiarioTableModel extends TAbstractTableModel<LibroDiario> {
	
	private static final long serialVersionUID = -425559212679581464L;
	
	public LibroDiarioTableModel() throws Exception {
		super(LibroDiario.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Cod. Autoriz.",LibroDiario.class.getDeclaredField("codigoAutorizacion")));
			locListaColumnas.add(new TColumnField("Número", LibroDiario.class.getDeclaredField("numero")));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
