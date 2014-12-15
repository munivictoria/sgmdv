package com.trascender.contabilidad.gui.abmPresupuesto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class PresupuestoTableModel extends TAbstractTableModel<Presupuesto>{

	private static final long serialVersionUID = 5513255615940373091L;

	public PresupuestoTableModel() throws Exception {
		super(Presupuesto.class);
		List<TColumnField> locListaColumnas = this.InicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> InicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", Presupuesto.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Tipo", Presupuesto.class.getDeclaredField("tipo")));
			locListaColumnas.add(new TColumnField("Fecha Ult. Modif.", Presupuesto.class.getDeclaredField("fecha"), Date.class));
			locListaColumnas.add(new TColumnField("Año", Presupuesto.class.getDeclaredField("anio"), Integer.class));
			locListaColumnas.add(new TColumnField("Estado", Presupuesto.class.getDeclaredField("estado")));
			return locListaColumnas;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuiException(6);
		}
	}

}
