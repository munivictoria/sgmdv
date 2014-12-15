package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class AsociacionCuentaConceptoIngresoVarioBusquedaModel extends TAbstractBusquedaModel<CuentaConceptoIngresoVario> {

	private ConceptoIngresoVario conceptoIngresoVario;
	private Periodo periodo;
	private Cuenta cuenta;
	
	
	@Override
	public List<CuentaConceptoIngresoVario> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<CuentaConceptoIngresoVario> locList = locSystem.findListaCuentaConceptoIngresoVario(this.getConceptoIngresoVario(), this.getPeriodo(), this.getCuenta());
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setCuenta(null);
		this.setConceptoIngresoVario(null);
		this.setPeriodo(null);
		
		this.fireActualizarDatos();
	}

	public ConceptoIngresoVario getConceptoIngresoVario() {
		return conceptoIngresoVario;
	}

	public void setConceptoIngresoVario(
			ConceptoIngresoVario conceptoIngresoVario) {
		this.conceptoIngresoVario = conceptoIngresoVario;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}
