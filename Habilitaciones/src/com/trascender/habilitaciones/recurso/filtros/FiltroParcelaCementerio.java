package com.trascender.habilitaciones.recurso.filtros;

import java.util.Date;
import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.cementerio.Concesion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;

public class FiltroParcelaCementerio extends FiltroAbstracto<ParcelaCementerio>{

	private static final long serialVersionUID = 8573636075782814590L;
	
	private PersonaFisica personaDifunto;
	private TipoSepultura tipoSepultura;
	private Persona titular;
	private Date fechaInscripcion;
	private Date fechaFinalizacion;
	private Concesion.TipoConcesion tipoConcesion;
	private List<AtributoDinamico<?>> listaAtributoDinamico;
	private List<AtributoDinamico<?>> listaAtributoDinamico2;
	private List<Long> listaIdPersonas;
	private Integer numeroCuenta;
	
	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Concesion.TipoConcesion getTipoConcesion() {
		return tipoConcesion;
	}

	public void setTipoConcesion(Concesion.TipoConcesion tipoConcesion) {
		this.tipoConcesion = tipoConcesion;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public PersonaFisica getPersonaDifunto() {
		return personaDifunto;
	}

	public void setPersonaDifunto(PersonaFisica personaDifunto) {
		this.personaDifunto = personaDifunto;
	}

	public Persona getTitular() {
		return titular;
	}

	public void setTitular(Persona titular) {
		this.titular = titular;
	}

	public TipoSepultura getTipoSepultura() {
		return tipoSepultura;
	}
	
	public void setTipoSepultura(TipoSepultura tipoSepultura) {
		this.tipoSepultura = tipoSepultura;
	}
	
	public List<AtributoDinamico<?>> getListaAtributoDinamico() {
		return listaAtributoDinamico;
	}
	public void setListaAtributoDinamico(
			List<AtributoDinamico<?>> pListaAtributoDinamico) {
		listaAtributoDinamico = pListaAtributoDinamico;
	}

	public List<AtributoDinamico<?>> getListaAtributoDinamico2() {
		return listaAtributoDinamico2;
	}

	public void setListaAtributoDinamico2(List<AtributoDinamico<?>> listaAtributoDinamico2) {
		this.listaAtributoDinamico2 = listaAtributoDinamico2;
	}

	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}

	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
}
