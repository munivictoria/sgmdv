package com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class AsociacionRefinanciacionBusquedaModel extends TAbstractBusquedaModel<AsociacionRefinanciacion>  {

	private Periodo periodo;
	
	@Override
	public List<AsociacionRefinanciacion> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<AsociacionRefinanciacion> locList = locSystem.findListaAsociacionRefinanciacion(this.getPeriodo());
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setPeriodo(null);
		
		this.fireActualizarDatos();
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
}