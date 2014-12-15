package com.trascender.contabilidad.gui.abmBien;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class BienTableModel extends TAbstractTableModel<Bien> {

	private static final long serialVersionUID = 2504473745993565823L;
	
	public BienTableModel() throws Exception {
		super(Bien.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", Bien.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Descripción",  Bien.class.getDeclaredField("descripcion")));
			locListaColumnas.add(new TColumnField("Estado", Bien.class.getDeclaredField("estado")));
			return locListaColumnas;	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
