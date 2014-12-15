package com.trascender.contabilidad.gui.abmPlanDeCuenta;

import java.util.Date;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.model.TAbstractABMModel;
	
public class PlanDeCuentaABMModel extends TAbstractABMModel<PlanDeCuenta>{
	
public Set<Cuenta> getTmpCuentas() {
		return tmpCuentas;
	}

	public void setTmpCuentas(Set<Cuenta> tmpCuentas) {
		this.tmpCuentas = tmpCuentas;
	}

	//	//Lista de cuaentas eliminadas
	Set<Cuenta> tmpCuentas;// = new HashSet<Cuenta>();
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addPlanDeCuenta(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updatePlanDeCuenta(this.objetoABM);
	}
	
	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deletePlanDeCuenta(this.objetoABM);
	}

	public String getDescripcion() {
		
		return this.getObjetoABM().getDescripcion();
	}

	public void setDescripcion(String descripcion) {
		this.getObjetoABM().setDescripcion(descripcion);
		this.fireActualizarDatos();
	}

	public Date getFechaAlta() {
		return this.getObjetoABM().getFechaAlta();
	}

	public void setFechaAlta(Date fechaAlta) {
		this.objetoABM.setFechaAlta(fechaAlta);
		this.fireActualizarDatos();
	}

	public Set<Cuenta> getListaCuentas() {
		return this.getObjetoABM().getListaCuentas();
	}

	public void setListaCuentas(Set<Cuenta> listaCuentas) {
		this.getObjetoABM().setListaCuentas(listaCuentas);
	}
	
	public PlanDeCuenta getObjetoABM() {
		return this.objetoABM;
	}
	
}
