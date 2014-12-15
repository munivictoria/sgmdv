package com.trascender.compras.recurso.persistent.suministros;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@Table(name = "USUARIO_AUTORIZADOR_OC")
@PrimaryKeyJoinColumn(name = "ID_USUARIO_AUTORIZADOR")
public class UsuarioAutorizadorOrdenCompra extends UsuarioAutorizador implements EntidadTrascender {

	private boolean eliminado = false;
	
	@Column(name = "IMPRIME_ORDEN_NUEVA")
	private boolean imprimeOrdenNueva = false;
	
	public boolean isImprimeOrdenNueva() {
		return imprimeOrdenNueva;
	}

	public void setImprimeOrdenNueva(boolean imprimeOrdenNueva) {
		this.imprimeOrdenNueva = imprimeOrdenNueva;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public static final long serialVersionUID = 8027271137579183031L;

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
			return this.idUsuarioAutorizador;
		}

		public String getNombrePropiedadId() {
			return "idUsuarioAutorizador";
		}

		public boolean isAuditable() {
			return true;
		}
}
