package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public class AsociacionCuentaModificadorBusquedaModel extends TAbstractBusquedaModel<CuentaModificador> {

	private Cuenta cuenta;
	private TipoModificador tipoModificador;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CuentaModificador> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<CuentaModificador> locList = locSystem.findListaCuentaModificador(this.getCuenta(), this.getTipoModificador());
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setCuenta(null);
		this.setTipoModificador(null);

		this.fireActualizarDatos();
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public TipoModificador getTipoModificador() {
		return tipoModificador;
	}

	public void setTipoModificador(TipoModificador tipoModificador) {
		this.tipoModificador = tipoModificador;
	}
}
