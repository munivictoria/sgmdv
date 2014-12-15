package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class AsociacionCuentaLineaFacturaTableModel extends TAbstractTableModel<LineaFactura>{
	
	private static final long serialVersionUID = -3907177309032782480L;

	public AsociacionCuentaLineaFacturaTableModel() throws Exception {
		super(LineaFactura.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Bien Provisto", LineaFactura.class.getDeclaredField("bienProvisto"), BienProvisto.class));
			locListaColumnas.add(new TColumnField("Cantidad", LineaFactura.class.getDeclaredField("cantidad"), Double.class));
			locListaColumnas.add(new TColumnField("Total", LineaFactura.class.getDeclaredField("total"), Double.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
