package com.trascender.habilitaciones.recurso.filtros;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;

public class FiltroTipoParametroConstante extends FiltroAbstracto<TipoParametroConstante>{

	private static final long serialVersionUID = -5549609461944490534L;
	
	private String nombre;

	public FiltroTipoParametroConstante() {
	}
	
	public FiltroTipoParametroConstante(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
