package com.trascender.contabilidad.gui.abmArea;

import java.util.ArrayList;
import java.util.List;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class AreaTableModel extends TAbstractTableModel<Area> {

	private static final long serialVersionUID = 5535280727626750413L;

	public AreaTableModel() throws Exception {
		super(Area.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws TrascenderException {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", Area.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Municipalidad", Area.class.getDeclaredField("municipalidad")));
			return locListaColumnas;	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
