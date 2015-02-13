package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;

public class FiltroObligacionArrendamiento extends FiltroAbstracto<Obligacion>{
	private static final long serialVersionUID = -1153659103773079593L;

	private Parcela parcela;
	private Persona persona;
	private Integer numeroCuenta;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	private List<Long> listaIdPersona;
	private List<Long> listaIdParcela;
	private String nroParcela;
	private Obligacion.Estado estado;
	
	public Obligacion.Estado getEstado() {
		return estado;
	}
	public void setEstado(Obligacion.Estado estado) {
		this.estado = estado;
	}
	public String getNroParcela() {
		return nroParcela;
	}
	public void setNroParcela(String nroParcela) {
		this.nroParcela = nroParcela;
	}
	public List<Long> getListaIdParcela() {
		return listaIdParcela;
	}
	public void setListaIdParcela(List<Long> listaIdParcela) {
		this.listaIdParcela = listaIdParcela;
	}
	public List<Long> getListaIdPersona() {
		return listaIdPersona;
	}
	public void setListaIdPersona(List<Long> listaIdPersona) {
		this.listaIdPersona = listaIdPersona;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
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
	
}
