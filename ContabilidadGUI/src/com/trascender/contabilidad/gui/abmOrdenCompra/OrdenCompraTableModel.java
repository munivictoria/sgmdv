package com.trascender.contabilidad.gui.abmOrdenCompra;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class OrdenCompraTableModel extends TAbstractTableModel<OrdenCompra> {

	private static final long serialVersionUID = 8102321259822069272L;
	
	public OrdenCompraTableModel() throws Exception {
		super(OrdenCompra.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nº", OrdenCompra.class.getDeclaredField("numero"), Integer.class));
			locListaColumnas.add(new TColumnField("Fecha de Emisión", OrdenCompra.class.getDeclaredField("fechaEmision")));
			locListaColumnas.add(new TColumnField("Proveedor", OrdenCompra.class.getDeclaredField("proveedor"), Proveedor.class));
			locListaColumnas.add(new TColumnField("Estado", OrdenCompra.class.getDeclaredField("estado")));
			locListaColumnas.add(new TColumnField("Monto", OrdenCompra.class.getDeclaredField("total"), Double.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
