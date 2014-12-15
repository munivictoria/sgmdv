package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro;

import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class AsociacionCuentaSolicitudSuministroABMModel extends TAbstractABMModel<SolicitudSuministro> {

	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().updateSolicitudSuministro(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().updateSolicitudSuministro(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().updateSolicitudSuministro(this.getObjetoABM());
	}

	public void firmar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionSolicitudSuministro().firmarSolicitudSuminstro(this.getObjetoABM());
	}
	
//	public CuentaRfr getCuentaRfr() {
//		return this.objetoABM.getCuentaRfr();
//	}
//
//	public void setCuentaRfr(CuentaRfr cuentaRfr) {
//		this.objetoABM.setCuentaRfr(cuentaRfr);
//		this.fireActualizarDatos();
//	}

	
}
