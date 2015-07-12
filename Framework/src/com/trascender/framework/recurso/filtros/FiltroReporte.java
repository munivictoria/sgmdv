/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroReporte extends FiltroAbstracto<Reporte> {

	private static final long serialVersionUID = 739071219261391562L;

	private String nombre;

	public FiltroReporte() {
	}

	public FiltroReporte(String nombre) {
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