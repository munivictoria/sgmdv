package com.trascender.contabilidad.gui.abmParametroAsociacion;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ParametroAsociacion;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ParametroAsociacionBusquedaModel extends TAbstractBusquedaModel<ParametroAsociacion> {

	@Override
	public List<ParametroAsociacion> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<ParametroAsociacion> locLista = locSystem.findListaParametroAsociacion();
		return locLista;
	}

	@Override
	public void reiniciar() {
		// TODO Auto-generated method stub
		
	}

}
