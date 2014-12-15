package com.trascender.catastro.recurso.persistent;

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
@Table(name = "TIPO_CONSTRUCCION")
public class TipoConstruccion implements Serializable, EntidadTrascender {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1824369594622496848L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_tipo_construccion")
	@SequenceGenerator(name ="gen_id_tipo_construccion", sequenceName = "gen_id_tipo_construccion", allocationSize = 1)
	@Column(name = "ID_TIPO_CONSTRUCCION")
	private long idTipoConstruccion=-1;
	
	private String nombre;
	private String descripcion;
	private boolean activo;
//	private List<Categoria> listaCategorias=new ArrayList<Categoria>();
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean pActivo) {
		this.activo = pActivo;
	}

	public long getIdTipoConstruccion() {
		return idTipoConstruccion;
	}

	public void setIdTipoConstruccion(long idTipoConstruccion) {
		this.idTipoConstruccion = idTipoConstruccion;
	}

	// /**
	// * @hibernate.bag lazy = "false" cascade = "none" inverse = "true"
	// * @hibernate.collection-one-to-many class =
	// "com.trascender.catastro.recurso.persistent.Categoria"
	// * @hibernate.collection-key column = "ID_CATEGORIA"
	// */
	// public List<Categoria> getListaCategorias() {
	// return listaCategorias;
	// }
	// public void setListaCategorias(List<Categoria> listaCategorias) {
	// this.listaCategorias = listaCategorias;
	// }
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.getNombre();
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
			return this.idTipoConstruccion;
		}

		public String getNombrePropiedadId() {
			return "idTipoConstruccion";
		}

		public boolean isAuditable() {
			return true;
		}
}
