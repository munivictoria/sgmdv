package com.trascender.contabilidad.gui.abmParametroRetencion;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ParametroRetencionBusquedaModel extends TAbstractBusquedaModel<ParametroRetencion> {

	@Override
	public List<ParametroRetencion> buscar() throws Exception {
		SystemAdministracionEgresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos();
		List<ParametroRetencion> locLista = locSystem.findListaParametroRetencion();
		return locLista;
	}

	@Override
	public void reiniciar() {
		// TODO Auto-generated method stub
		
	}

}
