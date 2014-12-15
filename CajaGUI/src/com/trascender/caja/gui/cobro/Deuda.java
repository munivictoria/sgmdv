package com.trascender.caja.gui.cobro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.saic.recurso.interfaces.Pagable;
/**
 * @author adrian
 */
public class Deuda implements Serializable {
	
	public static final long serialVersionUID = 167338730755615575L;
	
	private Double monto;
	private String nombre;
	private List<Pagable> listaPagables = new ArrayList<Pagable>();
	private Persona persona;
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Pagable> getListaPagables() {
		return listaPagables;
	}

	public void setListaPagables(List<Pagable> listaPagables) {
		this.listaPagables = listaPagables;
	}

	public Double getMonto() {
		return this.monto;
	}
	
	public String getNombre() {
		return this.nombre;
	}


	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
