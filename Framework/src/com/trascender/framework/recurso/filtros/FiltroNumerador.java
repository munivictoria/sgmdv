/**
 * 
 * © Copyright 2015, CoDeSoft Todos los derechos reservados.
 * 
 */

package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.Numerador;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroNumerador extends FiltroAbstracto<Numerador> {

	private static final long serialVersionUID = 3202080135030353468L;

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

}