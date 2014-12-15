package com.trascender.compras.recurso.filtros;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.FiltroAbstracto;


public class FiltroDeposito extends FiltroAbstracto<Deposito>{

	private static final long serialVersionUID = -7558766164375556776L;
	private String nombre;
	private Area area;
	private Set<Area> listaAreas = new HashSet<Area>();
	
	public FiltroDeposito() {
	}
	
	public FiltroDeposito(String nombre, Set<Area> listaAreas) {
		this.nombre = nombre;
		this.listaAreas = listaAreas;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Area> getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(Set<Area> listaAreas) {
		this.listaAreas = listaAreas;
	}
	
}
