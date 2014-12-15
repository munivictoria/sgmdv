package com.trascender.accionSocial.recurso.filtros;

import com.trascender.accionSocial.recurso.persistent.ObraSocial;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroObraSocial extends FiltroAbstracto<ObraSocial>{

	private static final long serialVersionUID = -8824634452057178394L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
