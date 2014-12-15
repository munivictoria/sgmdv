package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Servicio;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroServicio extends FiltroAbstracto<Servicio> {
	private static final long serialVersionUID = -8138958705807337259L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
