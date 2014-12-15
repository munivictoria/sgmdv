package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoRecursos;
import com.trascender.gui.framework.component.TColumn;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;

public class LineaPresupuestoRecursoConsultaABMTableModel extends LineaPresupuestoRecursoABMTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LineaPresupuestoRecursoConsultaABMTableModel() throws Exception {
		super();
		List<TColumn> locListaColumnas = this.inicializarColumnas();
		setListaColumnas(locListaColumnas);
	}

	private List<TColumn> inicializarColumnas() throws Exception {
		List<TColumn> locListaColumnas = getListaColumnas();		
		try {
			locListaColumnas.add(new TColumnField("Monto Recaudado", LineaPresupuestoRecursos.class.getDeclaredField("montoRecaudado"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
