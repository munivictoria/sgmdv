package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.Util;
/**
 * Representa un socio de una Persona Juridica
 * @author jsantacruz
 */
@Entity
public class Socio implements Serializable, AuditoriaIndirecta{

	public static final long serialVersionUID = 3425939625204051848L;
	
	public enum CargoSocietario{
		DIRECTOR_TITULAR,
		PRESIDENTE,
		SOCIO,
		SOCIO_GERENTE,
		REPRESENTANTE, 
		ADMINISTRADOR_FIDUCIARIO, 
		CONDOMINO, 
		CONDOMINO_ADMINISTRADOR, 
		SOCIEDAD_UTE,
		GERENTE_TITULAR, 
		ADMINISTRADOR_TITULAR, 
		SOCIO_COMANDITADO, 
		SOCIO_COMANDITARIO, 
		MIEMBRO_DEL_CONSEJO_DE_VIGILANCIA, 
		SINDICO_TITULAR, 
		REPRESENTANTE_DEL_CONSEJO_DE_ADMINISTRACION, 
		APODERADO, 
		FUNDADOR, 
		FIDUCIANTE, 
		FIDEICOMISARIO, 
		BENEFICIARIO,
		SOCIEDAD_DEPOSITARIA, 
		ACCIONISTA, 
		REPRESENTANTE_DEL_ORGANO_DIRECTIVO, 
		REPRESENTANTE_DEL_ORGANO_DE_FISCALIZACION, 
		PRESIDENTE_DEL_CONSEJO_DE_ADMINISTRACION, 
		SOCIOS_PARTICIPES, 
		SOCIOS_PROTECTORES, 
		PROPIETARIOS, 
		PROMOTORES, 
		APODERADO_F_3283, 
		VICEPRESIDENTE ,
		SECRETARIO, 
		PROSECRETARIO, 
		TESORERO, 
		PROTESORERO, 
		CONSEJERO, 
		DIRECTOR_SUPLENTE, 
		GERENTE_SUPLENTE, 
		ADMINISTRADOR_SUPLENTE, 
		SINDICO_SUPLENTE, 
		LIQUIDADOR_TITULAR, 
		LIQUIDADOR_SUPLENTE, 
		MIEMBRO_DEL_CONSEJO_DE_VIGILANCIA_SUPLENTE,
		ADMINISTRADOR,
		OTROS_CARGOS;
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	@Id
	@SequenceGenerator(allocationSize=1, name="GEN_ID_SOCIO", sequenceName="GEN_ID_SOCIO")
	@GeneratedValue(generator="GEN_ID_SOCIO",strategy=GenerationType.SEQUENCE)
	@Column(name="ID_SOCIO")
	private Long idSocio = -1l;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA_JURIDICA")
	private PersonaJuridica personaJuridica;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA",nullable=false)
	private Persona persona;
	
	@Enumerated(EnumType.STRING)
	private CargoSocietario cargo;
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona pPersona) {
		persona = pPersona;
	}

	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica pPersonaJuridica) {
		personaJuridica = pPersonaJuridica;
	}

	public CargoSocietario getCargo() {
		return cargo;
	}

	public void setCargo(CargoSocietario pCargo) {
		cargo = pCargo;
	}

	public Long getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(Long pIdSocio) {
		idSocio = pIdSocio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.persona.getDenominacion() + " " + this.cargo;
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.personaJuridica;
	}

	public String getNombrePropiedad() {
		return "Socio["+this.getPersona().getDenominacion()+"]";
	}

	public boolean concatenaNombre() {
		return true;
	}
}
