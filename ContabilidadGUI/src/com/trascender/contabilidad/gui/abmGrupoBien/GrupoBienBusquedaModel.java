package com.trascender.contabilidad.gui.abmGrupoBien;

import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.GrupoBien;
import com.trascender.compras.system.interfaces.SystemAdministracionBienes;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class GrupoBienBusquedaModel extends TAbstractBusquedaModel<GrupoBien> {

	@SuppressWarnings("unchecked")
	@Override
	public List<GrupoBien> buscar() throws Exception {
		SystemAdministracionBienes locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionBienes(); 
		List<GrupoBien> locLista = locSystem.findListaGrupoBienes();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.fireActualizarDatos();
	}
	
}
