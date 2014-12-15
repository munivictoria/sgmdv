package com.trascender.framework.recurso.persistent.referencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import com.trascender.framework.recurso.persistent.PersonaFisica;

/**
 * Referencia a una persona física utilizada por el usuario
 */

////@Entity
//@Entity(polymorphism=PolymorphismType.EXPLICIT)
//@Table(name="PERSONA_FISICA")
public class PersonaFisicaRfr implements Serializable{

	private static final long serialVersionUID = -8095676762605231980L;
	
	@Id
	@Column(name="ID_PERSONA")
	private Long idPersonaFisica;
	
	private String nombre;
	private String apellido;
	
	
	public PersonaFisicaRfr(){
		
	}
	public PersonaFisicaRfr(PersonaFisica pPersonaFisica){
		this.setIdPersonaFisica(pPersonaFisica.getIdPersonaFisica());
		this.setNombre(pPersonaFisica.getNombre());
		this.setApellido(pPersonaFisica.getApellido());
	}
	
	public PersonaFisicaRfr(long pIdPersona, String pNombre, String pApellido){
		this.setIdPersonaFisica(pIdPersona);
		this.setNombre(pNombre);
		this.setApellido(pApellido);
	}
	
	/**
	 * 
	 * @hibernate.property
	 */
	public String getApellido() {
		return apellido;
	}

	void setApellido(String pApellido) {
		apellido = pApellido;
	}

	/**
	 * 
	 * @hibernate.property
	 */
	public String getNombre() {
		return nombre;
	}

	void setNombre(String pNombre) {
		nombre = pNombre;
	}

	/**
	 * 
	 * @hibernate.id column = "ID_PERSONA" generator-class = "hilo"
	 */
	public long getIdPersonaFisica() {
		return idPersonaFisica;
	}

	public void setIdPersonaFisica(long pIdPersonaFisica) {
		idPersonaFisica = pIdPersonaFisica;
	}
	
	
	
	public String toString() {
		return ((this.apellido!=null)?this.apellido+", ":"")+((this.nombre!=null)?this.nombre:"");
	}
}	
