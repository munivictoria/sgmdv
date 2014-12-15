package com.trascender.contabilidad.gui.abmSubdiarioCaja;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class SubdiarioCajaTableModel extends TAbstractTableModel<SubdiarioCaja> {

	private static final long serialVersionUID = 2076166583904616969L;
	
	public SubdiarioCajaTableModel() throws Exception {
		super(SubdiarioCaja.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Fecha Creación", SubdiarioCaja.class.getDeclaredField("fechaCreacion"), Date.class));
			locListaColumnas.add(new TColumnField("Tipo", SubdiarioCaja.class.getDeclaredField("tipo"), SubdiarioCaja.Tipo.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
