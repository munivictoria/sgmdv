package com.trascender.contabilidad.gui.abmTipoCuenta;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Abreviatura;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class TipoCuentaTableModel extends TAbstractTableModel<TipoCuenta> {

	private static final long serialVersionUID = 5513255615940373091L;
	
	public TipoCuentaTableModel() throws Exception {
		super(TipoCuenta.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Nombre", TipoCuenta.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Abreviatura", TipoCuenta.class.getDeclaredField("abreviatura"), Abreviatura.class));
			locListaColumnas.add(new TColumnField("Op. Asientos", TipoCuenta.class.getDeclaredField("operaAsientos"), Opera.class));
			locListaColumnas.add(new TColumnField("Op. Mov. Caja", TipoCuenta.class.getDeclaredField("operaMovimientosCaja"), Opera.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
