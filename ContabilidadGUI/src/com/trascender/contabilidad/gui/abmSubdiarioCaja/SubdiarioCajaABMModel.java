package com.trascender.contabilidad.gui.abmSubdiarioCaja;

import java.util.Date;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LineaSubdiarioCaja;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class SubdiarioCajaABMModel extends TAbstractABMModel<SubdiarioCaja> {
	
	private PlanDeCuenta planDeCuenta;
	
	public void generarSubdiarioCaja() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().generarSubdiarioCaja(this.getFechaCreacion(), this.getPlanDeCuenta(), this.getTipo());
	}
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addSubdiarioCaja(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteSubdiarioCaja(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateSubdiarioCaja(this.objetoABM);
	}

	public Date getFechaCreacion() {
		return this.objetoABM.getFechaCreacion();
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.objetoABM.setFechaCreacion(fechaCreacion);
	}

	public PlanDeCuenta getPlanDeCuenta() {
		return planDeCuenta;
	}

	public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
		this.planDeCuenta = planDeCuenta;
	}

	public SubdiarioCaja.Tipo getTipo() {
		return this.objetoABM.getTipo();
	}

	public void setTipo(SubdiarioCaja.Tipo tipo) {
		this.objetoABM.setTipo(tipo);
	}

	public Set<LineaSubdiarioCaja> getLineasSubdiarioCaja() {
		return this.objetoABM.getLineasSubdiarioCaja();
	}

	public void setLineasSubdiarioCaja(Set<LineaSubdiarioCaja> lineasSubdiarioCaja) {
		this.objetoABM.setLineasSubdiarioCaja(lineasSubdiarioCaja);
	}

}
