package com.trascender.compras.recurso.filtros;

import com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroTipoOrdenCompra extends FiltroAbstracto<TipoOrdenCompra>{

	
	public FiltroTipoOrdenCompra() {
	}

	public FiltroTipoOrdenCompra(String nombre) {
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 7608337280191026545L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
