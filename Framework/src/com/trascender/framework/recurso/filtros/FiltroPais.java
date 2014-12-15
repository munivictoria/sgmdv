package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroPais extends FiltroAbstracto<Pais>{
	
	public FiltroPais() {
	}

	public FiltroPais(String pPais) {
		this.nombre = pPais;
	}

	private static final long serialVersionUID = -6110491252192328941L;
	
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

}
