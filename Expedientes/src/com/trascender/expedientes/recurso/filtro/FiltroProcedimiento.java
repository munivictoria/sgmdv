/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroProcedimiento extends FiltroAbstracto<Procedimiento> {

	private static final long serialVersionUID = -2928140279185130791L;

	private String nombre;
	private EstadoPlantilla estado;

	public FiltroProcedimiento() {
	}

	public FiltroProcedimiento(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

}