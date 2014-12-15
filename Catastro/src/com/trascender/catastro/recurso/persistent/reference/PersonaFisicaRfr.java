package com.trascender.catastro.recurso.persistent.reference;

import java.io.Serializable;


/**
 * 
 * @author Asus
 * @hibernate.class table = "PERSONA_FISICA" mutable = "false"
 */
public class PersonaFisicaRfr implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3860649423726466985L;
	
	private long idPersonaFisica=-1;
	
	private String cuil;
	private String apellido;
	private String nombre;
	private String numeroDocumento;
	private String telefono;
	private String celular;
	private String email;
	private Integer edad;
	private boolean jubilado;
	
	/**
	 * 
	 * @hibernate.property column = "JUBILADO" not-null = "false"
	 */
	public boolean isJubilado() {
		return jubilado;
	}
	
	public void setJubilado(boolean jubilado) {
		this.jubilado = jubilado;
	}

	public PersonaFisicaRfr(){
		
	}
	
	/**
	 * @hibernate.id column = "ID_PERSONA" generator-class = "increment"
	 * unsaved-value = "-1"
	 */
	public long getIdPersonaFisica() {
		return idPersonaFisica;
	}

	public void setIdPersonaFisica(long idPersonaFisica) {
		this.idPersonaFisica = idPersonaFisica;
	}
	
	/**
	 * 
	 * @hibernate.property
	 */
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * 
	 * @hibernate.property column = "CUIM"
	 */
	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public Integer getEdad() {
		
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @hibernate.property column = "NUMERO_DOCUMENTO"
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return ((this.getApellido()!=null)?(this.getApellido()+", "):"")+((this.getNombre()!=null)?this.getNombre():"");
	}
	
}
