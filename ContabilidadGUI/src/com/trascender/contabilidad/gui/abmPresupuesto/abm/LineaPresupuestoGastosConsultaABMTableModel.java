package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.util.List;

import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoGastos;
import com.trascender.gui.framework.component.TColumn;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;

public class LineaPresupuestoGastosConsultaABMTableModel extends LineaPresupuestoGastosABMTableModel{

	private static final long serialVersionUID = 1L;

	public LineaPresupuestoGastosConsultaABMTableModel() throws Exception {
		super();
		List<TColumn> locListaColumnas = this.inicializarColumnas();
		setListaColumnas(locListaColumnas);
	}

	private List<TColumn> inicializarColumnas() throws Exception {
		List<TColumn> locListaColumnas = getListaColumnas();
		try {
			locListaColumnas.add(new TColumnField("Monto Comprometido", LineaPresupuestoGastos.class.getDeclaredField("montoComprometido"), Double.class));
			locListaColumnas.add(new TColumnField("Monto Pagado", LineaPresupuestoGastos.class.getDeclaredField("montoPagado"), Double.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
