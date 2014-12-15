package com.trascender.contabilidad.gui.abmLineaOrdenCompra;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.reference.CuentaRfr;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class LineaOrdenCompraTableModel extends TAbstractTableModel<LineaOrdenCompra>{

	private static final long serialVersionUID = -782960518079163924L;

	public LineaOrdenCompraTableModel() throws Exception {
		super(LineaOrdenCompra.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Cantidad", LineaOrdenCompra.class.getDeclaredField("cantidad")));
			locListaColumnas.add(new TColumnField("Cantidad Faltante", LineaOrdenCompra.class.getDeclaredField("cantidadFaltante")));
			locListaColumnas.add(new TColumnField("Monto Unitario", LineaOrdenCompra.class.getDeclaredField("montoUnitario")));
			locListaColumnas.add(new TColumnField("Monto Total", LineaOrdenCompra.class.getDeclaredField("montoTotal")));
			locListaColumnas.add(new TColumnField("Cuenta", LineaOrdenCompra.class.getDeclaredField("cuentaRfr"), CuentaRfr.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
