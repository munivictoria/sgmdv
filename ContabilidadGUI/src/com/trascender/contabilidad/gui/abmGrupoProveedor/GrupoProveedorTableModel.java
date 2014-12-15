package com.trascender.contabilidad.gui.abmGrupoProveedor;

import java.util.ArrayList;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.GrupoProveedor;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class GrupoProveedorTableModel extends TAbstractTableModel<GrupoProveedor> {

	private static final long serialVersionUID = -8054637680397430939L;
	
	public GrupoProveedorTableModel() throws Exception {
		super(GrupoProveedor.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", GrupoProveedor.class.getDeclaredField("nombre")));
			return locListaColumnas;	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
