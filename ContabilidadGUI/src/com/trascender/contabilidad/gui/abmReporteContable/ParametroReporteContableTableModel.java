package com.trascender.contabilidad.gui.abmReporteContable;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.ParametroReporteContable;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ParametroReporteContableTableModel extends TAbstractTableModel<ParametroReporteContable>{

	public static final long serialVersionUID = -6607145900490231071L;
	
	public ParametroReporteContableTableModel() throws Exception {
		super(ParametroReporteContable.class);
		init();
	}

	private void init() throws Exception {
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		
		try {
			locListaColumnas.add(new TColumnField("Nombre", ParametroReporteContable.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Tipo", ParametroReporteContable.class.getDeclaredField("tipo")));
			locListaColumnas.add(new TColumnField("Requerido", ParametroReporteContable.class.getDeclaredField("requerido"), Boolean.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
}
