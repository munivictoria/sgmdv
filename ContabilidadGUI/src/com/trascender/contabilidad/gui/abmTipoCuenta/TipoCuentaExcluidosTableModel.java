package com.trascender.contabilidad.gui.abmTipoCuenta;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class TipoCuentaExcluidosTableModel extends TAbstractTableModel<TipoCuenta> {

	private static final long serialVersionUID = 790818928115227654L;

	public TipoCuentaExcluidosTableModel() throws Exception {
		super(TipoCuenta.class);
		init();
	}

	private void init() throws Exception {
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		
		try {
			locListaColumnas.add(new TColumnField("Nombre", TipoCuenta.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Abreviatura", TipoCuenta.class.getDeclaredField("abreviatura")));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
