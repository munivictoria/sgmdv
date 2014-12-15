package com.trascender.accionSocial.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;

public class AspectoEconomico implements Serializable{
	
	private static final long serialVersionUID = 4873919496099780592L;
	
	@Column(name="NRO_CASAS")
	private Integer numeroCasas;
	
	@Column(name="NRO_TERRENOS")
	private Integer numeroTerrenos;
	
	@Column(name="NRO_CAMPOS")
	private Integer numeroCampos;
	
	private String vehiculo;
	private String industria;
	
	@Column(name="ACTIVIDAD_LABORAL")
	private String actividadLaboral;
	
	private String comercio;
	
	public Integer getNumeroCasas() {
		return numeroCasas;
	}
	public void setNumeroCasas(Integer numeroCasas) {
		this.numeroCasas = numeroCasas;
	}
	public Integer getNumeroTerrenos() {
		return numeroTerrenos;
	}
	public void setNumeroTerrenos(Integer numeroTerrenos) {
		this.numeroTerrenos = numeroTerrenos;
	}
	public Integer getNumeroCampos() {
		return numeroCampos;
	}
	public void setNumeroCampos(Integer numeroCampos) {
		this.numeroCampos = numeroCampos;
	}
	public String getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	public String getIndustria() {
		return industria;
	}
	public void setIndustria(String industria) {
		this.industria = industria;
	}
	public String getActividadLaboral() {
		return actividadLaboral;
	}
	public void setActividadLaboral(String actividadLaboral) {
		this.actividadLaboral = actividadLaboral;
	}
	public String getComercio() {
		return comercio;
	}
	public void setComercio(String comercio) {
		this.comercio = comercio;
	}
	
	

}
