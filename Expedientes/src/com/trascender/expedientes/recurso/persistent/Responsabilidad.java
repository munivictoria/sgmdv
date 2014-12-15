package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.Util;

@Entity
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "EXP_RESPONSABILIDAD")
public abstract class Responsabilidad implements Serializable {

	
	private static final long serialVersionUID = -7302469664678518496L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_responsabilidad")
	@SequenceGenerator(name = "gen_id_exp_responsabilidad",
			sequenceName = "gen_id_exp_responsabilidad", allocationSize = 1)
	@Column(name = "ID_RESPONSABILIDAD")
	private long idResponsabilidad;
	
	@ManyToOne
	@JoinColumn(name = "ID_RESPONSABLE")
	private Responsable responsable;

	@Enumerated(EnumType.STRING)
	private Accion responsabilidad = Accion.RESPONSABLE;

	public abstract Object getEntidadResponsable();

	public enum Accion {
		SUPERVISOR, RESPONSABLE;
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		};
	}

	public Accion getResponsabilidad() {
		return responsabilidad;
	}

	public void setResponsabilidad(Accion responsabilidad) {
		this.responsabilidad = responsabilidad;
	}

	public long getIdResponsabilidad() {
		return idResponsabilidad;
	}

	public void setIdResponsabilidad(long idResponsabilidad) {
		this.idResponsabilidad = idResponsabilidad;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idResponsabilidad ^ (idResponsabilidad >>> 32));
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
		Responsabilidad other = (Responsabilidad) obj;
		if (this.idResponsabilidad == -1 
				|| other.idResponsabilidad == -1)
			return this.getEntidadResponsable().equals(other.getEntidadResponsable());
		if (idResponsabilidad != other.idResponsabilidad)
			return false;
		return true;
	}

}
