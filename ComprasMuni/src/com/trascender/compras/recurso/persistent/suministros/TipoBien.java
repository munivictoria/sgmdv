package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "TIPO_BIEN")
public class TipoBien implements Serializable, EntidadTrascender {
	
	public static final long serialVersionUID = -8870645331033539757L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_tipo_bien")
	@SequenceGenerator(name = "gen_id_tipo_bien", sequenceName = "gen_id_tipo_bien",allocationSize = 1)
	@Column(name="ID_TIPO_BIEN")
	private long idTipoBien = -1;
	
	private String nombre;
	
	private String descripcion;
	
	@Column(name = "CODIGO_IMPUTACION")
	private String codigoImputacion;
	
	public TipoBien(){}

	public long getIdTipoBien() {
		return idTipoBien;
	}

	public void setIdTipoBien(long idTipoBien) {
		this.idTipoBien = idTipoBien;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCodigoImputacion() {
		return codigoImputacion;
	}

	public void setCodigoImputacion(String codigoImputacion) {
		this.codigoImputacion = codigoImputacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTipoBien ^ (idTipoBien >>> 32));
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
		TipoBien other = (TipoBien) obj;
		if (idTipoBien != other.idTipoBien)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", descripcion=" + descripcion + "]";
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
			return this.idTipoBien;
		}

		public String getNombrePropiedadId() {
			return "idTipoBien";
		}

		public boolean isAuditable() {
			return true;
		}
}
