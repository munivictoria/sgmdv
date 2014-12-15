package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;

public class FiltroObligacionOSP extends FiltroAbstracto<Obligacion>{
	private static final long serialVersionUID = -8161696880779976773L;
	
	private Persona persona;
	private Parcela parcela;
	private Integer numeroCuenta;
	private Obligacion.Estado estado;
	private Boolean poseeExenciones;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	private List<Long> listaIdPersonas;
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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
	public Boolean getPoseeExenciones() {
		return poseeExenciones;
	}
	public void setPoseeExenciones(Boolean poseeExenciones) {
		this.poseeExenciones = poseeExenciones;
	}
	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}
	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
}
