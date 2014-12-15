package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Es el Nomenclador de la Clasificación Industrial Internacional Uniforme de todas las Actividades Económicas
 * provisto por la AFIP
 * @author Fernando Gareis
 *
 */
@Entity
@Table(name = "CODIGO_CIIU")
public class CodigoCiiu implements Serializable{
	
	public static final long serialVersionUID = -5663533104683006884L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_codigo_ciiu")
	@SequenceGenerator(name = "gen_id_codigo_ciiu", sequenceName = "gen_id_codigo_ciiu",allocationSize = 1)
	@Column(name="ID_CODIGO_CIIU")
	private long idCodigoCiiu = -1;
	
	private String codigo;
	private String descripcion;
	private String informacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_GRUPO_CIIU")
	private GrupoCiiu grupoCiiu;

	public long getIdCodigoCiiu() {
		return idCodigoCiiu;
	}

	public void setIdCodigoCiiu(long pIdCodigoCiiu) {
		idCodigoCiiu = pIdCodigoCiiu;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String pCodigo) {
		codigo = pCodigo;
	}
	
	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String pInformacion) {
		informacion = pInformacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		descripcion = pDescripcion;
	}

	public GrupoCiiu getGrupoCiiu() {
		return grupoCiiu;
	}

	public void setGrupoCiiu(GrupoCiiu pGrupoCiiu) {
		grupoCiiu = pGrupoCiiu;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCodigoCiiu ^ (idCodigoCiiu >>> 32));
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
		CodigoCiiu other = (CodigoCiiu) obj;
		if (idCodigoCiiu != other.idCodigoCiiu)
			return false;
		return true;
	}

	public String toString(){
		return "["+codigo+"]"+descripcion;
	}
	
	
}
