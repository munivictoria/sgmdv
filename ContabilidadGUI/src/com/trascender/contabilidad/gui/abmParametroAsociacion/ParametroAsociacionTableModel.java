package com.trascender.contabilidad.gui.abmParametroAsociacion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;

public class ParametroAsociacionTableModel extends TAbstractTableModel<ParametroAsociacion>{

	private static final long serialVersionUID = -6103960532797856220L;

	public ParametroAsociacionTableModel() throws Exception {
		super(ParametroAsociacion.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", ParametroAsociacion.class.getDeclaredField("cuenta")));
			locListaColumnas.add(new TColumnField("Porcentaje", ParametroAsociacion.class.getDeclaredField("porcentaje"), Integer.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}