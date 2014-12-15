package com.trascender.contabilidad.gui.abmReporteContable;

import java.util.ArrayList;
import java.util.List;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class UsuarioTableModel extends TAbstractTableModel<Usuario>{

	private static final long serialVersionUID = -5637893112530774387L;
	
	public UsuarioTableModel() throws Exception {
		super(Usuario.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws TrascenderException {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", Usuario.class.getDeclaredField("user")));
			locListaColumnas.add(new TColumnField("Persona", Usuario.class.getDeclaredField("personaFisica")));
			return locListaColumnas;	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
