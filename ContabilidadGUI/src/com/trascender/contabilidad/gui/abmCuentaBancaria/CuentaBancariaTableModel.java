package com.trascender.contabilidad.gui.abmCuentaBancaria;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class CuentaBancariaTableModel extends TAbstractTableModel<CuentaBancaria> {

	private static final long serialVersionUID = 5513255615940373091L;
	
	public CuentaBancariaTableModel() throws Exception {
		super(CuentaBancaria.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Número", CuentaBancaria.class.getDeclaredField("numero")));
			locListaColumnas.add(new TColumnField("Titular de la Cuenta", CuentaBancaria.class.getDeclaredField("titularCuentaBancaria"), Persona.class));
			locListaColumnas.add(new TColumnField("Banco", CuentaBancaria.class.getDeclaredField("banco"), Banco.class));
			locListaColumnas.add(new TColumnField("Tipo Cuenta", CuentaBancaria.class.getDeclaredField("tipoCuenta")));
			locListaColumnas.add(new TColumnField("Cuenta Propia", CuentaBancaria.class.getDeclaredField("propia"), boolean.class));
			return locListaColumnas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
