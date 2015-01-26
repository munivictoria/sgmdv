package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PROCESO_DB")
public class ProcesoDB implements Serializable{
	public static final long serialVersionUID = -5469827496019517148L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_proceso_db")
	@SequenceGenerator(name = "gen_id_proceso_db", sequenceName = "gen_id_proceso_db", allocationSize = 1)
	@Column(name = "ID_PROCESO_DB")
	private long idProcesoDB=-1;
	
	private String nombre;
	
	@Column(name = "NOMBRE_PROCESO")
	private String nombreProceso;
	
	private String descripcion;

	public long getIdProcesoDB() {
		return idProcesoDB;
	}

	public void setIdProcesoDB(long pIdProcesoDB) {
		idProcesoDB = pIdProcesoDB;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String pNombreProceso) {
		nombreProceso = pNombreProceso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		descripcion = pDescripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProcesoDB ^ (idProcesoDB >>> 32));
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
		ProcesoDB other = (ProcesoDB) obj;
		if (idProcesoDB != other.idProcesoDB)
			return false;
		return true;
	}
	

}
