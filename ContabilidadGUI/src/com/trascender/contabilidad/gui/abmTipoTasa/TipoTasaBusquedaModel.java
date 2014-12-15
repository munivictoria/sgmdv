package com.trascender.contabilidad.gui.abmTipoTasa;

import java.util.ArrayList;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoTasa;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa.Estado;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;

public class TipoTasaBusquedaModel extends TAbstractBusquedaModel<TipoTasa> {

	private String nombre;
	private TipoObligacion tipoObligacion;

	@Override
	@SuppressWarnings("unchecked")
	public List<TipoTasa> buscar() throws Exception {
		SystemTipoTasa locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemTipoTasa();
		FiltroTipoTasa locFiltro = new FiltroTipoTasa();
		locFiltro.setNombre(this.getNombre());
		locFiltro.setTipoObligacion(this.getTipoObligacion());
		return locSystem.findListaTiposTasa(locFiltro).getListaResultados();
	}
	
	public TipoObligacion[] findTiposObliacion(){
		List<TipoObligacion> locListaTiposObligacion = new ArrayList<TipoObligacion>();
		try {
			locListaTiposObligacion = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemTipoTasa().findListaTipoObligacion(null, false);
		} catch (TrascenderException e) {
			e.printStackTrace();
		}
		TipoObligacion[] locArrayTipoObligacion = new TipoObligacion[locListaTiposObligacion.size()];
		return locListaTiposObligacion.toArray(locArrayTipoObligacion);
	}

	@Override 
	public void reiniciar() {
		this.setNombre(null);
		this.setTipoObligacion(null);

		super.fireActualizarDatos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoObligacion getTipoObligacion() {
		return tipoObligacion;
	}

	public void setTipoObligacion(TipoObligacion tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
	}

}
