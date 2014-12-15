package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

/**
 * Representa un area de la municipalidad
 * @hibernate.class table = "AREA" proxy = "com.trascender.framework.recurso.persistent.Area"
 */
@Entity
@Table(name="AREA")
public class Area implements Serializable, EntidadTrascender{

	static public final long serialVersionUID = -2626429325339333277L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_area")
	@SequenceGenerator(name = "gen_id_area", sequenceName = "gen_id_area", allocationSize = 1)
	@Column(name = "ID_AREA")
	private long idArea=-1;
	
	@Column(nullable = false)
	private String nombre;
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name = "ID_SECRETARIA")
	private Secretaria secretaria;
	
	public Secretaria getSecretaria() {
		return secretaria;
	}
	public void setSecretaria(Secretaria pSecretaria) {
		secretaria = pSecretaria;
	}

	/**
	 * Estados podibles {ELIMINADO,ACTIVO}
	 * @author jsantacruz
	 *
	 */
	public enum Estado{ELIMINADO,ACTIVO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	
	/**
	 * Retorna la descripción del Area
	 * @return la descripción de la municipalidad
	 * @hibernate.property
	 */
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String pDescripcion) {
		descripcion = pDescripcion;
	}
	
	/**
	 * Recupera el número de identificación del area
	 * @return id del area
	 * @hibernate.id generator-class = "increment" column = "ID_AREA"
	 * unsaved-value = "-1" 
	 */
	public long getIdArea() {
		return idArea;
	}
	public void setIdArea(long pIdArea) {
		idArea = pIdArea;
	}
	
	/**
	 * Recupera el nombre del area
	 * @return nombre del area
	 * @hibernate.property not-null = "true"
	 */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	
	
	 /**
	 * @return estado en que se encuentra el area
	 * @hibernate.property not-null = "true"
	 * type = "com.trascender.framework.util.enumerations.EstadoArea"
	 * 	 
	 */
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado pEstado) {
		estado = pEstado;
	}
	
    	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idArea ^ (idArea >>> 32));
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
		Area other = (Area) obj;
		if (idArea != other.idArea)
			return false;
		return true;
	}
	
	public String toString() {
		return (this.nombre==null)?"":this.nombre;
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
			return this.idArea;
		}

		public String getNombrePropiedadId() {
			return "idArea";
		}

		public boolean isAuditable() {
			return true;
		}
}
