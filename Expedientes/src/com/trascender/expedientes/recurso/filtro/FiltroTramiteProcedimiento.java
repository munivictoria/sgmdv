/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroTramiteProcedimiento extends FiltroAbstracto<TramiteProcedimiento> {

	private static final long serialVersionUID = 8779203284650860847L;

	private String nombre;

	public FiltroTramiteProcedimiento() {
	}

	public FiltroTramiteProcedimiento(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}