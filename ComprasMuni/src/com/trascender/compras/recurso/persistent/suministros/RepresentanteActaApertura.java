package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;

@Entity
@Table(name="REPRESENTANTE_ACTA_APERTURA")
public class RepresentanteActaApertura implements Serializable{

	public static final long serialVersionUID = 714309937950786126L;
	
	@Id
	@SequenceGenerator(allocationSize=1, name="GEN_ID_REPRESENTANTE_A_P", sequenceName="GEN_ID_REPRESENTANTE_A_P")
	@GeneratedValue(generator="GEN_ID_REPRESENTANTE_A_P", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_REPRESENTANTE_A_P")
	private Long idRepresentanteActaApertura = -1L;
	
	@Column(nullable=false)
	private String cargo;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA", nullable=false)
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name="ID_ACTA_APERTURA", nullable=false)
	private ActaApertura actaApertura;

	public Long getIdRepresentanteActaApertura() {
		return idRepresentanteActaApertura;
	}

	public void setIdRepresentanteActaApertura(Long idRepresentanteActaApertura) {
		this.idRepresentanteActaApertura = idRepresentanteActaApertura;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public ActaApertura getActaApertura() {
		return actaApertura;
	}

	public void setActaApertura(ActaApertura actaApertura) {
		this.actaApertura = actaApertura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actaApertura == null) ? 0 : actaApertura.hashCode());
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
		RepresentanteActaApertura other = (RepresentanteActaApertura) obj;
		if (actaApertura == null) {
			if (other.actaApertura != null)
				return false;
		} else if (!actaApertura.equals(other.actaApertura))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Participante apertura licitacion nro: "+ this.actaApertura.getContratacion().getNumero();
	}
	
	
}
