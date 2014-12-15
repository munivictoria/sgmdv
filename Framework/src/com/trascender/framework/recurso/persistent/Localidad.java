package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;


/**
 * Representa una localidad de una provincia
 * @hibernate.class table = "LOCALIDAD"
 */
@Entity
@Table(name="LOCALIDAD")
public class Localidad implements Serializable, EntidadTrascender {

	public static final long serialVersionUID = -5397745598517115585L;
	
	@Id
	@GeneratedValue(generator="gen_id_localidad",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_localidad",sequenceName="gen_id_localidad",allocationSize = 1)
	@Column(name="ID_LOCALIDAD")
	private Long idLocalidad=-1L;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(name="CODIGO_POSTAL", nullable=false, length=4)
	private String codigoPostal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PROVINCIA")
	private Provincia provincia;
	
	/**
	 * @return provincia a la que pertenence la localidad
	 * @hibernate.many-to-one 
	 * column = "ID_PROVINCIA"
	 */
	public Provincia getProvincia() {
		if (this.provincia==null){
			return null;
		}
		return provincia;
	}
	public void setProvincia(Provincia pProvincia) {
		provincia = pProvincia;
	}
	/**
	 * 
	 * @return código postal de la provincia
	 * @hibernate.property not-null = "true" column = "CODIGO_POSTAL"  length = "4"
	 */
	public String getCodigoPostal() {
		return (codigoPostal != null)?codigoPostal.toUpperCase():null;
	}
	public void setCodigoPostal(String pCodigoPostal) {
		codigoPostal = pCodigoPostal;
	}
	
	/**
	 * 
	 * @return número de identificación de la localidad
	 * @hibernate.id column = "ID_LOCALIDAD" generator-class = "increment"
	 * unsaved-value = "-1"
	 */
	public long getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(long pIdLocalidad) {
		idLocalidad = pIdLocalidad;
	}
	
	/**
	 * @return nombre de la localidad
	 * @hibernate.property not-null = "true"
	 */
	public String getNombre() {
		return 	Util.capitalize(nombre);
	}
	
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	
	@Override
	public String toString() {
		return (this.getNombre()==null)?"":Util.capitalize(nombre);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idLocalidad == null) ? 0 : idLocalidad.hashCode());
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
		Localidad other = (Localidad) obj;
		if (idLocalidad == null) {
			if (other.idLocalidad != null)
				return false;
		} else if (!idLocalidad.equals(other.idLocalidad))
			return false;
		return true;
	}
	
	// ********************************************************************************************************************************/
		// AUDITORIA

		@Transient
		private long llaveUsuarioAuditoria;
		@Transient
		private String comentarioAuditoria;

		@OrderBy(value = "fecha")
		@Where(clause = "id_recurso = " + serialVersionUID)
		@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
		private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

		public List<LogAuditoria> getListaLogsAuditoria() {
			return listaLogsAuditoria;
		}

		public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
			this.listaLogsAuditoria = pListaLogsAuditoria;
		}

		public long getLlaveUsuarioAuditoria() {
			return llaveUsuarioAuditoria;
		}

		public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
			this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
		}

		public String getComentarioAuditoria() {
			return comentarioAuditoria;
		}

		public void setComentarioAuditoria(String comentarioAuditoria) {
			this.comentarioAuditoria = comentarioAuditoria;
		}

		public long getSerialVersionUID() {
			return serialVersionUID;
		}

		public long getIdEntidad() {
			return this.idLocalidad;
		}

		public String getNombrePropiedadId() {
			return "idLocalidad";
		}

		public boolean isAuditable() {
			return true;
		}
}
