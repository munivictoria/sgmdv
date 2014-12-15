package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura;

import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AsociacionCuentaLineaFacturaABMModel extends TAbstractABMModel<CuentaLineaFactura> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().addCuentaLineaFactura(this.objetoABM);
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().deleteCuentaLineaFactura(this.objetoABM);		
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().updateCuentaLineaFactura(this.objetoABM);
	}

	public Periodo getPeriodo() {
		return this.objetoABM.getPeriodo();
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

	public LineaFactura getLineaFactura() {
		return this.objetoABM.getLineaFactura();
	}

	public void setLineaFactura(LineaFactura lineaFactura) {
		this.objetoABM.setLineaFactura(lineaFactura);
	}
	
}
