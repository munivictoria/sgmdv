package com.trascender.contabilidad.gui.abmCuenta;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class CuentaTableModel extends TAbstractTableModel<Cuenta> {
	
	private static final long serialVersionUID = 404518485692260386L;
	
	public CuentaTableModel() throws Exception {
		super(Cuenta.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}
	
	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Cód. Imputación", Cuenta.class.getDeclaredField("codigoImputacionCompleto")));
			locListaColumnas.add(new TColumnField("Nombre",Cuenta.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Abreviatura", Cuenta.class.getDeclaredField("abreviatura")));
//			locListaColumnas.add(new TColumnField("Tipo Cuenta", Cuenta.class.getDeclaredField("tipoCuenta"), TipoCuenta.class));
			locListaColumnas.add(new TColumnField("Plan de Cuenta", Cuenta.class.getDeclaredField("planDeCuenta"), PlanDeCuenta.class));
			return locListaColumnas;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
	
}
