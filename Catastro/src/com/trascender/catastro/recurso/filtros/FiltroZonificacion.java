package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroZonificacion extends FiltroAbstracto<Zonificacion>{
	private static final long serialVersionUID = -990784871317512031L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
