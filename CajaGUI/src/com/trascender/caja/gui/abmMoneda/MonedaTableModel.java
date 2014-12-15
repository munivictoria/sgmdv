package com.trascender.caja.gui.abmMoneda;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Moneda;
import com.trascender.contabilidad.recurso.persistent.Moneda.Tipo;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class MonedaTableModel extends TAbstractTableModel<Moneda> {

	private static final long serialVersionUID = -5120274119416160916L;
	
	public MonedaTableModel() throws Exception {
		super(Moneda.class);
		List<TColumnField> locListaComunas = this.inicializarColumnas();
		this.setListaColumnas(locListaComunas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre",Moneda.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Tipo",Moneda.class.getDeclaredField("tipo"),Tipo.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
