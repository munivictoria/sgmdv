/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroFaseCatalogo extends FiltroAbstracto<FaseCatalogo> {

	private static final long serialVersionUID = 6144047246534004581L;

	private String nombre;
	private EstadoPlantilla estado;

	public FiltroFaseCatalogo() {
	}

	public FiltroFaseCatalogo(String nombre, EstadoPlantilla estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
	}

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