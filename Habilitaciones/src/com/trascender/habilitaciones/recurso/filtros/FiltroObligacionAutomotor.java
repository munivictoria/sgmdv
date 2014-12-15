package com.trascender.habilitaciones.recurso.filtros;

import java.util.ArrayList;
import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

public class FiltroObligacionAutomotor extends FiltroAbstracto<Obligacion>{
	
	private static final long serialVersionUID = 583169480238490101L;
	private Persona persona;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	private Obligacion.Estado estado;
	private String patente;
	private Integer numeroCuenta;
	private Vehiculo vehiculo;
	private List<Long> listaIdPersonas;
	
	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	public void setListaAtributosDinamicos(
			List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos = listaAtributosDinamicos;
	}
	
	public Obligacion.Estado getEstado() {
		return estado;
	}
	public void setEstado(Obligacion.Estado estado) {
		this.estado = estado;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}
	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
}
