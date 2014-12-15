package com.trascender.contabilidad.gui.abmImportarPlanDeCuenta;

import java.util.Date;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class ImportarPlanDeCuentaABMModel extends TAbstractABMModel<PlanDeCuenta> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addPlanDeCuenta(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		
	}

	@Override
	public void modificar() throws Exception {
		
	}
	
	public void importar() throws Exception {
		//this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().importarPlanDeCuenta(this.getObjetoABM());
	}

	public String getDescripcion() {
		return this.objetoABM.getDescripcion();
	}

	public void setDescripcion(String descripcion) {
		this.objetoABM.setDescripcion(descripcion);
	}

	public Date getFechaAlta() {
		return this.objetoABM.getFechaAlta();
	}

	public void setFechaAlta(Date fechaAlta) {
		this.objetoABM.setFechaAlta(fechaAlta);
	}

	public Set<Cuenta> getListaCuentas() {
		return this.objetoABM.getListaCuentas();
	}

	public void setListaCuentas(Set<Cuenta> listaCuentas) {
		this.objetoABM.setListaCuentas(listaCuentas);
	}
	
}
