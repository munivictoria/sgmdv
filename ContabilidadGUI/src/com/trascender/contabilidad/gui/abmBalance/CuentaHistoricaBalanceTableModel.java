package com.trascender.contabilidad.gui.abmBalance;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.CuentaHistoricaBalance;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class CuentaHistoricaBalanceTableModel extends TAbstractTableModel<CuentaHistoricaBalance> {

	private static final long serialVersionUID = -700825440911917340L;
	
	public CuentaHistoricaBalanceTableModel()throws Exception {
		super(CuentaHistoricaBalance.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Cód. Imputación", CuentaHistoricaBalance.class.getDeclaredField("codigoImputacion")));
			locListaColumnas.add(new TColumnField("Nombre",CuentaHistoricaBalance.class.getDeclaredField("nombre")));
			locListaColumnas.add(new TColumnField("Abreviatura", CuentaHistoricaBalance.class.getDeclaredField("abreviatura")));
			locListaColumnas.add(new TColumnField("Saldo Presupuestado", CuentaHistoricaBalance.class.getDeclaredField("valor"),Double.class));
			locListaColumnas.add(new TColumnField("Importe Acumulado", CuentaHistoricaBalance.class.getDeclaredField("acumulado"),Double.class));
			locListaColumnas.add(new TColumnField("Importe Presupuesto", CuentaHistoricaBalance.class.getDeclaredField("importePresupuestado"),Double.class));
			return locListaColumnas;
			} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}

}
