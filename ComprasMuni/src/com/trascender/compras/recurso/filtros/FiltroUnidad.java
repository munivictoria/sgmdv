package com.trascender.compras.recurso.filtros;

import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroUnidad extends FiltroAbstracto<Unidad>{

	public FiltroUnidad() {
	}

	public FiltroUnidad(String descripcion) {
		this.descripcion = descripcion;
	}

	private static final long serialVersionUID = -6037276674520004991L;

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
