package com.trascender.contabilidad.gui.abmGrupoBien;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.GrupoBien;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class GrupoBienTableModel extends TAbstractTableModel<GrupoBien> {

	private static final long serialVersionUID = -2388552019068684061L;
	
	public GrupoBienTableModel() throws Exception {
		super(GrupoBien.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", GrupoBien.class.getDeclaredField("nombre")));
			return locListaColumnas;	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
