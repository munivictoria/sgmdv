package com.trascender.framework.recurso.transients;

import java.io.Serializable;
import java.util.ArrayList;

import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;

public class Grupo implements Serializable, Comparable {
	/**
	 * 
	 */
	public static final long serialVersionUID = -3232819427540168596L;
	/**
	 * 
	 */
	

	private long id;
	private String nombre;
	
	private ConfiguracionRecurso configuracion;
	
	private ArrayList<Recurso> listaRecursos=new ArrayList<Recurso>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Recurso> getListaRecursos() {
		return listaRecursos;
	}
	public void setListaRecursos(ArrayList<Recurso> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}
	
	public boolean canAccess(){
		return true;
	}
	
	public int compareTo(Object pGrupo) throws ClassCastException {
		    if (!(pGrupo instanceof Grupo))
		      throw new ClassCastException("Se esperaba un objeto Grupo");
		    String nombreGrupo = ((Grupo) pGrupo).nombre;  
		    return nombreGrupo.compareTo(this.nombre);		    	
	 }
	

	
	
}

