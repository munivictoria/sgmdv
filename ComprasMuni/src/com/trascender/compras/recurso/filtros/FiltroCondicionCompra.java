package com.trascender.compras.recurso.filtros;

import com.trascender.compras.recurso.persistent.suministros.CondicionCompra;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroCondicionCompra extends FiltroAbstracto<CondicionCompra> {

	public FiltroCondicionCompra() {
	}


	public FiltroCondicionCompra(String nombre) {
		this.nombre = nombre;
	}


	private static final long serialVersionUID = 764587890506831379L;
	
	
	private String nombre;


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
