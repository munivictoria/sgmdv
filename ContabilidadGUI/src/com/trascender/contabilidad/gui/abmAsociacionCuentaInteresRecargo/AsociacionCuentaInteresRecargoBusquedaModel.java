package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;

public class AsociacionCuentaInteresRecargoBusquedaModel extends TAbstractBusquedaModel<CuentaInteresRecargo> {

	private Cuenta cuenta;
	private ConceptoPorMora conceptoPorMora;

	@SuppressWarnings("unchecked")
	@Override
	public List<CuentaInteresRecargo> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<CuentaInteresRecargo> locList = locSystem.findListaCuentaInteresRecargo(this.getCuenta(), this.getConceptoPorMora());
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setCuenta(null);
		this.setConceptoPorMora(null);

		this.fireActualizarDatos();
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public ConceptoPorMora getConceptoPorMora() {
		return conceptoPorMora;
	}

	public void setConceptoPorMora(ConceptoPorMora conceptoPorMora) {
		this.conceptoPorMora = conceptoPorMora;
	}

}
