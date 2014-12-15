package com.trascender.accionSocial.recurso.filtros;

import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroFichaSocial extends FiltroAbstracto<FichaSocial>{
	private static final long serialVersionUID = 7008228555623565170L;
	
	private Integer numero;
	private Persona titular;
	private Persona familiar; 
	private Beneficio beneficio;
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Persona getTitular() {
		return titular;
	}
	public void setTitular(Persona titular) {
		this.titular = titular;
	}
	public Persona getFamiliar() {
		return familiar;
	}
	public void setFamiliar(Persona familiar) {
		this.familiar = familiar;
	}
	public Beneficio getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}
	

}
