package com.trascender.contabilidad.gui.abmArea;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.filtros.FiltroArea;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class AreaBusquedaModel extends TAbstractBusquedaModel<Area> {

	private String nombre;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Area> buscar() throws Exception {
		SystemMunicipalidad locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemMunicipalidad();
		FiltroArea locFiltro = new FiltroArea();
		locFiltro.setNombre(this.getNombre());
		List<Area> locLista = locSystem.findArea(locFiltro).getListaResultados();
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
