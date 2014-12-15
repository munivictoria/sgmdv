package com.trascender.contabilidad.gui.abmTipoOrdenCompra;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class TipoOrdenCompraTableModel extends TAbstractTableModel<TipoOrdenCompra> {

	private static final long serialVersionUID = -4001939897817097896L;
	
	public TipoOrdenCompraTableModel() throws Exception {
		super(TipoOrdenCompra.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField ("Nombre", TipoOrdenCompra.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField ("Monto Mínimo", TipoOrdenCompra.class.getDeclaredField("montoMinimo")));
			locListaColumnas.add(new TColumnField ("Monto Máximo", TipoOrdenCompra.class.getDeclaredField("montoMaximo")));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
