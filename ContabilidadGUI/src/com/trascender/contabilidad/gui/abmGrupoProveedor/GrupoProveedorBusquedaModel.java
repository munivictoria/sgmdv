package com.trascender.contabilidad.gui.abmGrupoProveedor;

import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.GrupoProveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class GrupoProveedorBusquedaModel extends TAbstractBusquedaModel<GrupoProveedor> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GrupoProveedor> buscar() throws Exception {
		SystemAdministracionProveedores locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionProveedores();
s		List<GrupoProveedor> locLista = locSystem.findListadoGruposProveedores(); 
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.fireActualizarDatos();
	}

}
