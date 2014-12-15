package com.trascender.habilitaciones.recurso.filtros;

import java.util.Date;
import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

public class FiltroVehiculo extends FiltroAbstracto<Vehiculo>{
	private static final long serialVersionUID = -7122922927486420839L;
	
	private String patente;
	private String motor;
	private Modelo modelo;
	private Persona propietario;
	private Date fechaInscripcion;
	private String codigo;
	private List<AtributoDinamico<?>> listaAtributoDinamico;
	private List<AtributoDinamico<?>> listaAtributoDinamico2;
	
	public List<AtributoDinamico<?>> getListaAtributoDinamico() {
		return listaAtributoDinamico;
	}

	public void setListaAtributoDinamico(
			List<AtributoDinamico<?>> listaAtributoDinamico) {
		this.listaAtributoDinamico = listaAtributoDinamico;
	}
	
	public List<AtributoDinamico<?>> getListaAtributoDinamico2() {
		return listaAtributoDinamico2;
	}

	public void setListaAtributoDinamico2(List<AtributoDinamico<?>> listaAtributoDinamico2) {
		this.listaAtributoDinamico2 = listaAtributoDinamico2;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}
}
