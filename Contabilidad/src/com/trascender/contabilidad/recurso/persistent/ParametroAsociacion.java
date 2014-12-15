package com.trascender.contabilidad.recurso.persistent;

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

@Entity
@Table(name = "PARAMETRO_ASOCIACION")
public class ParametroAsociacion implements Serializable {

	public static final long serialVersionUID = 2598143710490826688L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_parametro_asociacion")
	@SequenceGenerator(name = "gen_id_parametro_asociacion", sequenceName = "gen_id_parametro_asociacion",allocationSize = 1)
	@Column(name="ID_PARAMETRO_ASOCIACION")
	private long idParametroAsociacion = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	private Double porcentaje;
	
	public long getIdParametroAsociacion() {
		return idParametroAsociacion;
	}
	
	public void setIdParametroAsociacion(long idParametroAsociacion) {
		this.idParametroAsociacion = idParametroAsociacion;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}  
	
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime
				* result
				+ (int) (idParametroAsociacion ^ (idParametroAsociacion >>> 32));
		result = prime * result
				+ ((porcentaje == null) ? 0 : porcentaje.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ParametroAsociacion other = (ParametroAsociacion) obj;
		if (cuenta == null) {
			if (other.cuenta != null) {
				return false;
			}
		} else if (!cuenta.equals(other.cuenta)) {
			return false;
		}
		if (idParametroAsociacion != other.idParametroAsociacion) {
			return false;
		}
		if (porcentaje == null) {
			if (other.porcentaje != null) {
				return false;
			}
		} else if (!porcentaje.equals(other.porcentaje)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ParametroAsociacion [cuenta=" + cuenta
				+ ", idParametroAsociacion=" + idParametroAsociacion
				+ ", porcentaje=" + porcentaje + "]";
	}
}
