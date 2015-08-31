/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.filtro;

import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroFaseProcedimiento extends FiltroAbstracto<FaseProcedimiento> {

	private static final long serialVersionUID = -7537028540894893712L;

	private String nombre;

	public FiltroFaseProcedimiento() {
	}

	public FiltroFaseProcedimiento(String nombre) {
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