package com.trascender.framework.recurso.persistent.referencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;

@Entity
@Table(name="OBLIGACION")
public class ObligacionRfr implements Serializable{

	public static final long serialVersionUID = -4760850884468711286L;
	
//	public enum Estado{
//		PENDIENTE_DE_ALTA;
//		
//		public String toString() {
//			return Util.capitalizeEnumName(this.name());
//		};
//	}
	
	public enum Estado {
		CREADO, PREHABILITADO, HABILITADO, INHABILITADO, ANULADO, TERMINADO, EXENTA, PENDIENTE_DE_ALTA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super
					.toString());
		}
	};
	
	@Id
	@Column(name="ID_OBLIGACION")
	private long idObligacion = -1L;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.PENDIENTE_DE_ALTA;
	
	private String descripcion = "Obligación generada automaticamente por el sistema."; 

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public long getIdObligacion() {
		return idObligacion;
	}

	public void setIdObligacion(long idObligacion) {
		this.idObligacion = idObligacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	} 
}
