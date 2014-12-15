package com.trascender.contabilidad.gui.abmTipoModificador;

import java.util.ArrayList;
import java.util.List;

import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public class TipoModificadorTableModel extends TAbstractTableModel<TipoModificador> {

	private static final long serialVersionUID = -2156839737293529785L;

	public TipoModificadorTableModel() throws Exception {
		super(TipoModificador.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);	
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", TipoModificador.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Fijo?", TipoModificador.class.getDeclaredField("fijo"), Boolean.class));
			locListaColumnas.add(new TColumnField("Meses desde", TipoModificador.class.getDeclaredField("desdeMeses")));
			locListaColumnas.add(new TColumnField("Meses hasta", TipoModificador.class.getDeclaredField("hastaMeses")));
			locListaColumnas.add(new TColumnField("Días desde", TipoModificador.class.getDeclaredField("desdeDias")));
			locListaColumnas.add(new TColumnField("Días hasta", TipoModificador.class.getDeclaredField("hastaDias")));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
