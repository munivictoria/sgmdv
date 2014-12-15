package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.gui.framework.model.TAbstractABMModel;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public class AsociacionCuentaInteresRecargoABMModel extends TAbstractABMModel<CuentaInteresRecargo> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addCuentaInteresRecargo(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteCuentaInteresRecargo(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateCuentaInteresRecargo(this.objetoABM);
	}

	public Cuenta getCuenta() {
		return this.objetoABM.getCuenta();
	}

	public void setCuenta(Cuenta cuenta) {
		this.objetoABM.setCuenta(cuenta);
	}

	public Cuenta getCuentaAtrasada(){
		return this.objetoABM.getCuentaPagosAtrasados();
	}

	public void setCuentaAtrasada(Cuenta pCuenta){
		this.objetoABM.setCuentaPagosAtrasados(pCuenta);
	}
	
	public ConceptoPorMora getConceptoPorMora(){
		return this.objetoABM.getConceptoPorMora();
	}
	
	public void setConceptoPorMora(ConceptoPorMora pConcepto){
		this.objetoABM.setConceptoPorMora(pConcepto);
	}
}
