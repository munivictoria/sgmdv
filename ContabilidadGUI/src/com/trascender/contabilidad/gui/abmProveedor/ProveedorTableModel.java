package com.trascender.contabilidad.gui.abmProveedor;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class ProveedorTableModel extends TAbstractTableModel<Proveedor> {
	
	private static final long serialVersionUID = 5611322235957233633L;
	
	public ProveedorTableModel() throws Exception {
		super(Proveedor.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Razón Social", Proveedor.class.getDeclaredField("proveedorLocal")));
			locListaColumnas.add(new TColumnField("Teléfono", Proveedor.class.getDeclaredField("telefono")));
			locListaColumnas.add(new TColumnField("E-mail", Proveedor.class.getDeclaredField("email")));
			return locListaColumnas;	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

	
	
	

}
