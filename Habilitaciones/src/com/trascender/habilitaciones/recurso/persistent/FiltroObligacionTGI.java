package com.trascender.habilitaciones.recurso.persistent;

import java.util.List;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;

public class FiltroObligacionTGI extends FiltroAbstracto<Obligacion> {

	public static final long serialVersionUID = -373714410724127718L;
	
	private Persona persona;
	private Integer numeroRegistro;
	private Obligacion.Estado estado;
	private Parcela parcela;
	private String nroParcela;
	private Boolean poseeExenciones;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	private List<Long> listaIdPersona;
	private List<Long> listaIdParcela;
	
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

	public Integer getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(Integer numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public String getNroParcela() {
		return nroParcela;
	}

	public void setNroParcela(String nroParcela) {
		this.nroParcela = nroParcela;
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	public void setListaAtributosDinamicos(
			List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos = listaAtributosDinamicos;
	}

	public Boolean getPoseeExenciones() {
		return poseeExenciones;
	}

	public void setPoseeExenciones(Boolean poseeExenciones) {
		this.poseeExenciones = poseeExenciones;
	}

	public List<Long> getListaIdPersona() {
		return listaIdPersona;
	}

	public void setListaIdPersona(List<Long> listaIdPersona) {
		this.listaIdPersona = listaIdPersona;
	}

	public List<Long> getListaIdParcela() {
		return listaIdParcela;
	}

	public void setListaIdParcela(List<Long> listaIdParcela) {
		this.listaIdParcela = listaIdParcela;
	}
	
}
