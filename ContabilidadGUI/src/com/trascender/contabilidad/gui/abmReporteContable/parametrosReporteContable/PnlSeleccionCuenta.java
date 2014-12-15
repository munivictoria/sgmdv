package com.trascender.contabilidad.gui.abmReporteContable.parametrosReporteContable;

import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;

public class PnlSeleccionCuenta extends PnlSeleccion {

	private static final long serialVersionUID = 5482189184898073115L;

	private Cuenta cuenta;
	
	public PnlSeleccionCuenta(ParametrosReporteContable pVentana) {
		super(pVentana);
	}
	
	@Override
	protected Long getIdObjetoSeleccionado() {
		return this.cuenta.getIdCuenta();
	}
	
	@Override
	protected void setObjetoSeleccionado(Object pObjeto) {
		this.cuenta = (Cuenta) pObjeto;
	}
	
	@Override
	protected void seleccionarObjeto() throws Exception {
		AdminCuenta adminCuenta = new AdminCuenta(this.getVentana());
		Cuenta locCuenta = adminCuenta.openSelect();
		if (locCuenta != null) {
			this.cuenta = locCuenta;
			this.getTextField().setText(locCuenta.getNombre());
		}
	}
}
