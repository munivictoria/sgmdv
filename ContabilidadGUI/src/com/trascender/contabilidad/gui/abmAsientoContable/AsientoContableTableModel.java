package com.trascender.contabilidad.gui.abmAsientoContable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class AsientoContableTableModel extends TAbstractTableModel<AsientoContable> {

	private static final long serialVersionUID = -8013743463164331925L;
	
	public AsientoContableTableModel() throws Exception {
		super(AsientoContable.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nro. Asiento", AsientoContable.class.getDeclaredField("numeroAsiento")));
			locListaColumnas.add(new TColumnField("Fecha", AsientoContable.class.getDeclaredField("fecha"), Date.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
