package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AsociacionCuentaConceptoSelladoAdministrativoABMModel extends TAbstractABMModel<CuentaConceptoIngresoVario>{

	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addCuentaConceptoIngresoVario(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteCuentaConceptoIngresoVario(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateCuentaConceptoIngresoVario(this.objetoABM);
	}

	public Periodo getPeriodo() {
		return this.getObjetoABM().getPeriodo();
	}

	public void setPeriodo(Periodo periodo) {
		this.objetoABM.setPeriodo(periodo);
	}

	public Cuenta getCuenta() {
		return this.objetoABM.getCuenta();
	}

	public void setCuenta(Cuenta cuenta) {
		this.objetoABM.setCuenta(cuenta);
	}

	public ConceptoIngresoVario getConceptoSelladoAdministrativo() {
		return this.objetoABM.getConceptoIngresoVario();
	}

	public void setConceptoSelladoAdministrativo(
			ConceptoIngresoVario conceptoSelladoAdministrativo) {
		this.objetoABM.setConceptoIngresoVario(conceptoSelladoAdministrativo);
	}
	
}
