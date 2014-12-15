package com.trascender.accionSocial.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

public class AspectoHabitacional implements Serializable{

	private static final long serialVersionUID = -2190849479488525268L;
	
	@Column(name="NRO_PERSONAS")
	private Integer numeroPersonas;
	private String vivienda;
	private String tenencia;
	
	@Column(name="NRO_CAMAS")
	private Integer numeroCamas;
	
	@Column(name="NRO_AMBIENTES")
	private Integer numeroAmbientes;
	
	@Column(name="BANIO_COMPLETO")
	private boolean banioCompleto;
	
	@Column(name="BANIO_INTERNO")
	private boolean banioInterno;
	
	private boolean agua;
	private boolean luz;
	private boolean cloaca;
	
	@Column(name="GAS_NATURAL")
	private boolean gasNatural;
	
	public Integer getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(Integer numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

	public String getVivienda() {
		return vivienda;
	}

	public void setVivienda(String vivienda) {
		this.vivienda = vivienda;
	}

	public String getTenencia() {
		return tenencia;
	}

	public void setTenencia(String tenencia) {
		this.tenencia = tenencia;
	}

	public Integer getNumeroCamas() {
		return numeroCamas;
	}

	public void setNumeroCamas(Integer numeroCamas) {
		this.numeroCamas = numeroCamas;
	}

	public Integer getNumeroAmbientes() {
		return numeroAmbientes;
	}

	public void setNumeroAmbientes(Integer numeroAmbientes) {
		this.numeroAmbientes = numeroAmbientes;
	}

	public boolean isBanioCompleto() {
		return banioCompleto;
	}

	public void setBanioCompleto(boolean banioCompleto) {
		this.banioCompleto = banioCompleto;
	}

	public boolean isBanioInterno() {
		return banioInterno;
	}

	public void setBanioInterno(boolean banioInterno) {
		this.banioInterno = banioInterno;
	}

	public boolean isAgua() {
		return agua;
	}

	public void setAgua(boolean agua) {
		this.agua = agua;
	}

	public boolean isLuz() {
		return luz;
	}

	public void setLuz(boolean luz) {
		this.luz = luz;
	}

	public boolean isCloaca() {
		return cloaca;
	}

	public void setCloaca(boolean cloaca) {
		this.cloaca = cloaca;
	}

	public boolean isGasNatural() {
		return gasNatural;
	}

	public void setGasNatural(boolean gasNatural) {
		this.gasNatural = gasNatural;
	}

}
