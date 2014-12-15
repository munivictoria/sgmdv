package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroTipoCalle extends FiltroAbstracto<TipoCalle>{

	private static final long serialVersionUID = 1668580313792620696L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	
	

}
