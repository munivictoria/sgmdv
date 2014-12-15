package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaPresupuestoTableModel extends TAbstractTableModel<LineaPresupuesto> {

	private static final long serialVersionUID = 5513255615940373091L;
	
	protected static final int COLUMNA_VALOR_MODIFICABLE = 1;

	public LineaPresupuestoTableModel() throws Exception {
		super(LineaPresupuesto.class);
		init();
	}

	private void init() throws Exception {
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		
		try {
			locListaColumnas.add(new TColumnField("Cuenta", LineaPresupuesto.class.getDeclaredField("cuenta")));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == COLUMNA_VALOR_MODIFICABLE) return true;
		else return false;
	}
	
}
