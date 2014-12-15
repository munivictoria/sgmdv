package com.trascender.contabilidad.recurso.filtros;

import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroConceptoIngresoVario extends FiltroAbstracto<ConceptoIngresoVario>{
	
	private static final long serialVersionUID = 5730298473045869663L;

	private String nombre;

	public FiltroConceptoIngresoVario() {
	}

	public FiltroConceptoIngresoVario(String nombre) {
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
