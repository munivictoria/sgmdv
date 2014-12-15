package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.gui.framework.model.TAbstractABMModel;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public class AsociacionCuentaModificadorABMModel extends TAbstractABMModel<CuentaModificador> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addCuentaModificador(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteCuentaModificador(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateCuentaModificador(this.objetoABM);
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

	public TipoModificador getTipoModificador() {
		return this.objetoABM.getTipoModificador();
	}

	public void setTipoModificador(TipoModificador tipoModificador) {
		this.objetoABM.setTipoModificador(tipoModificador);
	}
}
