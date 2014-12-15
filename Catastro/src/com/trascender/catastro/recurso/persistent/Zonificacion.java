package com.trascender.catastro.recurso.persistent;

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
 * Esta clase representa a un conjunto de zonas, es utilizada para el cálculo 
 * de las diferentes tasas
 */
@Entity
@Table(name = "ZONIFICACION")
public class Zonificacion implements Serializable, EntidadTrascender {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1876641970567032426L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_zonificacion")
	@SequenceGenerator(name ="gen_id_zonificacion", sequenceName = "gen_id_zonificacion", allocationSize = 1)
	@Column(name = "ID_ZONIFICACION")
	private long idZonificacion = -1;
	private String nombre;
	
	public enum Estado {
		ACTIVO, ELIMINADO;

		@Override
		public String toString() {

			return Util.capitalizeEnumName(this.name());
		}
		
	
	}
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;
	
	@OneToMany(mappedBy = "zonificacion", cascade = CascadeType.ALL)
	private List<Zona> listaZonas = new ArrayList<Zona>();
	
	public long getIdZonificacion() {
		return idZonificacion;
	}
	
	
	public void setIdZonificacion(long idZonificacion) {
		this.idZonificacion = idZonificacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Zona> getListaZonas() {
		return listaZonas;
	}
	
	public void setListaZonas(List<Zona> listaZonas) {
		this.listaZonas = listaZonas;
	}
	
	@Override
	public String toString() {
		return (this.nombre==null)?"":this.nombre + (this.getEstado().equals(Estado.ELIMINADO)?"(Eliminado)":"");
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idZonificacion ^ (idZonificacion >>> 32));
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
		Zonificacion other = (Zonificacion) obj;
		if (idZonificacion != other.idZonificacion)
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
			return this.idZonificacion;
		}

		public String getNombrePropiedadId() {
			return "idZonificacion";
		}

		public boolean isAuditable() {
			return true;
		}
}