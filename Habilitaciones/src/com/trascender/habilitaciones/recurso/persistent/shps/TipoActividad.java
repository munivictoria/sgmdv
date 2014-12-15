package com.trascender.habilitaciones.recurso.persistent.shps;

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

import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;

@Entity
@Table(name = "TIPO_ACTIVIDAD")
public class TipoActividad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5929560659219411452L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_tipo_actividad")
	@SequenceGenerator(name = "gen_id_tipo_actividad", sequenceName = "gen_id_tipo_actividad", allocationSize = 1)
	@Column(name = "ID_TIPO_ACTIVIDAD")
	private long idTipoActividad=-1;
	
	@Column(nullable = false)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO", nullable = false)
	private DocHabilitanteEspecializado documentoEspecializado;
	
	public DocHabilitanteEspecializado getDocumentoEspecializado() {
		return documentoEspecializado;
	}
	public void setDocumentoEspecializado(
			DocHabilitanteEspecializado documentoEspecializado) {
		this.documentoEspecializado = documentoEspecializado;
	}
	public long getIdTipoActividad() {
		return idTipoActividad;
	}
	public void setIdTipoActividad(long idTipoActividad) {
		this.idTipoActividad = idTipoActividad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idTipoActividad ^ (idTipoActividad >>> 32));
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
		final TipoActividad other = (TipoActividad) obj;
		if (idTipoActividad != other.idTipoActividad)
			return false;
		return true;
	}
	

}
