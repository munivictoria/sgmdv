package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONDICION_APLICACION_EXENCION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_CONDICION")
public abstract class CondicionAplicacionExencion implements Serializable {

	private static final long serialVersionUID = 7319436870752146463L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_condicion_aplicacion_exencion")
	@SequenceGenerator(name = "gen_id_condicion_aplicacion_exencion", sequenceName = "gen_id_condicion_aplicacion_exencion", allocationSize = 1)
	@Column(name = "ID_CONDICION_APLICACION_EXENCION")
	private long idCondicionAplicacionExencion=-1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EXENCION")
	private ExencionObligacion exencionObligacion;

	public long getIdCondicionAplicacionExencion() {
		return idCondicionAplicacionExencion;
	}

	public void setIdCondicionAplicacionExencion(long idCondicionAplicacionExencion) {
		this.idCondicionAplicacionExencion = idCondicionAplicacionExencion;
	}

	public ExencionObligacion getExencionObligacion() {
		return exencionObligacion;
	}

	public void setExencionObligacion(ExencionObligacion exencionObligacion) {
		this.exencionObligacion = exencionObligacion;
	}
	
	public abstract String getNombre();
	public abstract boolean seAplica(CuotaLiquidacion c);
	
	public void setNombre(String pNombre){
	}

	@Override
	public String toString() {
		return "CondicionAplicacionExencion [exencionObligacion="
				+ exencionObligacion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((exencionObligacion == null) ? 0 : exencionObligacion
						.hashCode());
		result = prime
				* result
				+ (int) (idCondicionAplicacionExencion ^ (idCondicionAplicacionExencion >>> 32));
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
		CondicionAplicacionExencion other = (CondicionAplicacionExencion) obj;
		if (exencionObligacion == null) {
			if (other.exencionObligacion != null)
				return false;
		} else if (!exencionObligacion.equals(other.exencionObligacion))
			return false;
		if (idCondicionAplicacionExencion != other.idCondicionAplicacionExencion)
			return false;
		return true;
	}
}
