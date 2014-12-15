package com.trascender.contabilidad.gui.abmBanco;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class BancoTableModel extends TAbstractTableModel<Banco>{

	private static final long serialVersionUID = 5513255615940373091L;

	public BancoTableModel() throws Exception {
		super(Banco.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> listaColumnas = new ArrayList<TColumnField>();
		try {
			listaColumnas.add(new TColumnField("Nombre", Banco.class.getDeclaredField("nombre")));
			listaColumnas.add(new TColumnField("Sucursal", Banco.class.getDeclaredField("sucursal")));
			return listaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
