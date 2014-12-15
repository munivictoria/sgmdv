package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoRecursos;
import com.trascender.gui.framework.component.TColumn;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;

public class LineaPresupuestoRecursoABMTableModel extends LineaPresupuestoTableModel {

	private static final long serialVersionUID = 5513255615940373091L;

	public LineaPresupuestoRecursoABMTableModel() throws Exception {
		super();
		List<TColumn> locListaColumnas = this.inicializarColumnas();
		setListaColumnas(locListaColumnas);
	}

	private List<TColumn> inicializarColumnas() throws Exception {
		List<TColumn> locListaColumnas = getListaColumnas();		
		try {
			locListaColumnas.add(new TColumnField("Monto Estimado", LineaPresupuestoRecursos.class.getDeclaredField("montoEstimado"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
