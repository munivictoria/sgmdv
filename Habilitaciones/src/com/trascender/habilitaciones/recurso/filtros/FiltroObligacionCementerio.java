package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;

public class FiltroObligacionCementerio extends FiltroAbstracto<Obligacion>{
	
	private static final long serialVersionUID = 583169480238490101L;
	private Persona persona;
	private ParcelaCementerio parcelaCementerio;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	private Obligacion.Estado estado;
	private List<Long> listaIdPersonas;
	
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
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public ParcelaCementerio getParcelaCementerio() {
		return parcelaCementerio;
	}
	public void setParcelaCementerio(ParcelaCementerio parcelaCementerio) {
		this.parcelaCementerio = parcelaCementerio;
	}
	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}
	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
	
}
