package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroTramiteCatalogo extends FiltroAbstracto<TramiteCatalogo> {
	
	private static final long serialVersionUID = 2405304852111509563L;
	
	private String nombre;
	private EstadoPlantilla estado;
	
	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	

}
