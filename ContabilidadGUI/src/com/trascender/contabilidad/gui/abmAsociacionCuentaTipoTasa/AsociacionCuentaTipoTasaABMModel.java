package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.gui.framework.model.TAbstractABMModel;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;

public class AsociacionCuentaTipoTasaABMModel extends TAbstractABMModel<CuentaTipoTasa> {

	private Cuenta cuentaAtrasada;

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addCuentaTipoTasa(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteCuentaTipoTasa(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateCuentaTipoTasa(this.getObjetoABM());
	}

	public Cuenta getCuenta() {
		return this.objetoABM.getCuenta();
	}

	public void setCuenta(Cuenta cuenta) {
		this.objetoABM.setCuenta(cuenta);
	}

	public TipoTasa getTipoTasa() {
		return this.objetoABM.getTipoTasa();
	}

	public void setTipoTasa(TipoTasa tipoTasa) {
		this.objetoABM.setTipoTasa(tipoTasa);
	}

	public Cuenta getCuentaAtrasada() {
		return this.objetoABM.getCuentaPagosAtrasados();
	}

	public void setCuentaAtrasada(Cuenta cuentaAtrasada) {
		this.objetoABM.setCuentaPagosAtrasados(cuentaAtrasada);
	}


}
