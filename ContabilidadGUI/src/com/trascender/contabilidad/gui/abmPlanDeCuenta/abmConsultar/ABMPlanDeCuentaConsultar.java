package com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar;

import java.util.List;

import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaABMModel;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.abmStandard.ABMController;

public abstract class ABMPlanDeCuentaConsultar extends ABMController<PlanDeCuenta>{
	
	protected abstract ABMPlanDeCuentaConsultarView getView();
	protected abstract PlanDeCuentaABMModel getAbmModel();
	protected abstract PlanDeCuentaTableModelConsultar getTableModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	@Override
	protected void actualizarABMModel() {
		if (this.getTableModel() != null) {
			List<Cuenta> locLista = this.getTableModel().getListaObjetos();
			this.getAbmModel().getListaCuentas().clear();
			this.getAbmModel().getListaCuentas().addAll(locLista);
		}
		this.getAbmModel().fireActualizarDatos();
	}
}


