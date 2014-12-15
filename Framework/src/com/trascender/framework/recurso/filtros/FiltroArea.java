package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroArea extends FiltroAbstracto<Area> {

	private static final long serialVersionUID = -5903080138244185345L;
	
	public FiltroArea(){}
	
	public FiltroArea(String pNombre, Area.Estado pEstado) {
		this.setNombre(pNombre);
		this.setEstado(pEstado);
	}
	
	private String nombre; 
	private Area.Estado estado;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	public Area.Estado getEstado() {
		return estado;
	}
	public void setEstado(Area.Estado pEstado) {
		estado = pEstado;
	}

}
