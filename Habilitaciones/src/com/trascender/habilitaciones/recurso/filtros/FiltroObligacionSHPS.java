package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;

public class FiltroObligacionSHPS extends FiltroAbstracto<Obligacion>{
	private static final long serialVersionUID = -1317207627636551633L;

	private Persona persona;
	private Persona contador;
	private String numeroInscripcion;
	private Obligacion.Estado estado;
	private Boolean poseeExenciones;
	private String cuim;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;


	public Persona getContador() {
		return contador;
	}
	public void setContador(Persona contador) {
		this.contador = contador;
	}
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	public void setListaAtributosDinamicos(
			List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos = listaAtributosDinamicos;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}
	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}
	public Obligacion.Estado getEstado() {
		return estado;
	}
	public void setEstado(Obligacion.Estado estado) {
		this.estado = estado;
	}
	public Boolean getPoseeExenciones() {
		return poseeExenciones;
	}
	public void setPoseeExenciones(Boolean poseeExenciones) {
		this.poseeExenciones = poseeExenciones;
	}
	public String getCuim() {
		return cuim;
	}
	public void setCuim(String cuim) {
		this.cuim = cuim;
	}
}