package com.trascender.habilitaciones.recurso.filtros;

import java.util.HashSet;
import java.util.Set;

import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;

public class FiltroInspector extends FiltroAbstracto<Inspector>{
	
	private static final long serialVersionUID = -7731157691390203296L;
	private String nombre;
	private PersonaFisica persona;
	private Set<InspeccionSHPS> listaInspecciones=new HashSet<InspeccionSHPS>();
	
	public FiltroInspector() {
	}
	
	public FiltroInspector(String nombre, PersonaFisica persona,
			Set<InspeccionSHPS> listaInspecciones) {
		super();
		this.nombre = nombre;
		this.persona = persona;
		this.listaInspecciones = listaInspecciones;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public PersonaFisica getPersona() {
		return persona;
	}

	public void setPersona(PersonaFisica persona) {
		this.persona = persona;
	}

	public Set<InspeccionSHPS> getListaInspecciones() {
		return listaInspecciones;
	}

	public void setListaInspecciones(Set<InspeccionSHPS> listaInspecciones) {
		this.listaInspecciones = listaInspecciones;
	}
}
