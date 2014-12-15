package com.trascender.contabilidad.gui.abmMayor;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class MayorTableModel extends TAbstractTableModel<Mayor>{

	private static final long serialVersionUID = -7424482829150686537L;

	public MayorTableModel() throws Exception {
		super(Mayor.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Cuenta", Mayor.class.getDeclaredField("cuenta"), Cuenta.class));
			locListaColumnas.add(new TColumnField("Tipo", Mayor.class.getDeclaredField("tipo"), Mayor.Tipo.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
