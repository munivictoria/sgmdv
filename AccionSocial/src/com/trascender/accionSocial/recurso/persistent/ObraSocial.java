package com.trascender.accionSocial.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="OBRA_SOCIAL")
public class ObraSocial implements Serializable{
	
	public static final long serialVersionUID = -6369188130534915204L;
	
	@Id
	@SequenceGenerator(allocationSize=1, name="gen_id_obra_social", sequenceName="gen_id_obra_social")
	@GeneratedValue(generator="gen_id_obra_social", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_OBRA_SOCIAL")
	private long idObraSocial = -1;
	
	private String nombre;
	
	public long getIdObraSocial() {
		return idObraSocial;
	}
	public void setIdObraSocial(long idObraSocial) {
		this.idObraSocial = idObraSocial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString(){
		return nombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idObraSocial ^ (idObraSocial >>> 32));
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
		ObraSocial other = (ObraSocial) obj;
		if (idObraSocial != other.idObraSocial)
			return false;
		return true;
	}

}
