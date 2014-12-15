package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroMunicipalidad extends FiltroAbstracto<Municipalidad>{

	private static final long serialVersionUID = 1176449708234806466L;

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
}
