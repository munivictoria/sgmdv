package com.trascender.framework.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Persona.Estado;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroPersonaJuridica extends FiltroAbstracto<PersonaJuridica> {

	public FiltroPersonaJuridica() {
	}
	
	public FiltroPersonaJuridica(String pCuit, String pRazonSocial,
			Domicilio pDomicilioPostal, PersonaFisica pTitular, Estado pEstado,
			List<AtributoDinamico<?>> pListaAtributoDinamico) {
		cuit = pCuit;
		razonSocial = pRazonSocial;
		domicilioPostal = pDomicilioPostal;
		titular = pTitular;
		estado = pEstado;
		listaAtributoDinamico = pListaAtributoDinamico;
	}
	
	private static final long serialVersionUID = 9083965858139302847L;
	
	private String cuit;
	private String razonSocial;
	private Domicilio domicilioPostal;
	private PersonaFisica titular;
	private Persona.Estado estado;
	private List<AtributoDinamico<?>> listaAtributoDinamico;
	private String nombreFantasia;
	
	public String getNombreFantasia() {
		return nombreFantasia;
	}
	public void setNombreFantasia(String pNombreFantasia) {
		nombreFantasia = pNombreFantasia;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String pCuit) {
		cuit = pCuit;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String pRazonSocial) {
		razonSocial = pRazonSocial;
	}
	public Domicilio getDomicilioPostal() {
		return domicilioPostal;
	}
	public void setDomicilioPostal(Domicilio pDomicilioPostal) {
		domicilioPostal = pDomicilioPostal;
	}
	public List<AtributoDinamico<?>> getListaAtributoDinamico() {
		return listaAtributoDinamico;
	}
	public void setListaAtributoDinamico(
			List<AtributoDinamico<?>> pListaAtributoDinamico) {
		listaAtributoDinamico = pListaAtributoDinamico;
	}
	public PersonaFisica getTitular() {
		return titular;
	}
	public void setTitular(PersonaFisica pTitular) {
		titular = pTitular;
	}
	public Persona.Estado getEstado() {
		return estado;
	}
	public void setEstado(Persona.Estado pEstado) {
		estado = pEstado;
	}	
}
