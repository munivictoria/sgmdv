package com.trascender.framework.recurso.persistent;

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
@Table(name = "SECRETARIA")
public class Secretaria implements Serializable, EntidadTrascender{
	
	public static final long serialVersionUID = 7582882155496308446L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_secretaria")
	@SequenceGenerator(name = "gen_id_secretaria", sequenceName = "gen_id_secretaria",allocationSize = 1)
	@Column(name="ID_SECRETARIA")
	private long idSecretaria = -1;
	
	private String nombre;
	
	@Column(name="CODIGO_IMPUTACION")
	private String codigoImputacion;
	
	@OneToMany(mappedBy = "secretaria")
	private List<Area> listaAreas = new ArrayList<Area>();

	public long getIdSecretaria() {
		return idSecretaria;
	}

	public void setIdSecretaria(long pIdSecretaria) {
		idSecretaria = pIdSecretaria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	
	public String getCodigoImputacion() {
		return codigoImputacion;
	}

	public void setCodigoImputacion(String pCodigoImputacion) {
		codigoImputacion = pCodigoImputacion;
	}

	public List<Area> getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(List<Area> pListaAreas) {
		listaAreas = pListaAreas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idSecretaria ^ (idSecretaria >>> 32));
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
		Secretaria other = (Secretaria) obj;
		if (idSecretaria != other.idSecretaria)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
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
			return this.idSecretaria;
		}

		public String getNombrePropiedadId() {
			return "idSecretaria";
		}

		public boolean isAuditable() {
			return true;
		}
}
