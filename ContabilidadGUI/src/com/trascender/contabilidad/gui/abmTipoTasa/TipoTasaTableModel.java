package com.trascender.contabilidad.gui.abmTipoTasa;

import java.util.ArrayList;
import java.util.List;

import com.trascender.gui.framework.component.TColumn;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.component.TColumnGeter;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class TipoTasaTableModel extends TAbstractTableModel<TipoTasa> {

	private static final long serialVersionUID = 5513255615940373091L;

	public TipoTasaTableModel() throws Exception {
		super(TipoTasa.class);
		List<TColumn> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);

	}

	private List<TColumn> inicializarColumnas() throws Exception {
		List<TColumn> locListaColumnas = new ArrayList<TColumn>();
		try {
			locListaColumnas.add(new TColumnField("Nombre	", TipoTasa.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnGeter("Tipo Obligación", TipoTasa.class.getDeclaredMethod("getTipoObligacion")));
			locListaColumnas.add(new TColumnField("Estado", TipoTasa.class.getDeclaredField("estado")));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}

	}



}
