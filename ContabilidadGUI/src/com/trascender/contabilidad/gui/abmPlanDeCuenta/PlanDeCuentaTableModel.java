package com.trascender.contabilidad.gui.abmPlanDeCuenta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.model.TAbstractTableModel;

public class PlanDeCuentaTableModel extends TAbstractTableModel<PlanDeCuenta>{

	private static final long serialVersionUID = 5513255615940373091L;


	public PlanDeCuentaTableModel() throws Exception {
		super(PlanDeCuenta.class);
		List<TColumnField> locListaColumnas = this.inicializarColumnas();
		this.setListaColumnas(locListaColumnas);
	}

	private List<TColumnField> inicializarColumnas() throws Exception {
		List<TColumnField> locListaColumnas = new ArrayList<TColumnField>();
		try {
			locListaColumnas.add(new TColumnField("Descripción",PlanDeCuenta.class.getDeclaredField("descripcion")));
			locListaColumnas.add(new TColumnField("Fecha Alta",PlanDeCuenta.class.getDeclaredField("fechaAlta"), Date.class));
			return locListaColumnas;	
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new GuiException(6);
		}
	}
}
