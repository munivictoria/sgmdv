package com.trascender.contabilidad.gui.abmTipoOrdenCompra;

import java.util.List;

import com.trascender.compras.recurso.filtros.FiltroTipoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionOrdenCompra;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class TipoOrdenCompraBusquedaModel extends TAbstractBusquedaModel<TipoOrdenCompra> {

	private String nombre;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOrdenCompra> buscar() throws Exception {
		SystemAdministracionOrdenCompra locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionOrdenCompra();
		FiltroTipoOrdenCompra locFiltro = new FiltroTipoOrdenCompra();
		locFiltro.setNombre(this.getNombre());
		List<TipoOrdenCompra> locLista = locSystem.findListaTiposOrdenesCompra(locFiltro).getListaResultados();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
		
		this.fireActualizarDatos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
