package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;

public class FiltroPlantillaObligacion extends FiltroAbstracto<PlantillaObligacion>{
	private static final long serialVersionUID = 2571808701938277142L;
	
	
	private String nombre;
	private TipoObligacion tipoObligacion;
	
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
