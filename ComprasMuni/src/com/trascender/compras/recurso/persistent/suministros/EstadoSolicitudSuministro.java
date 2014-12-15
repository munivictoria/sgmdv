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
@Table(name = "ESTADO_SOLICITUD_SUMINISTRO")
public class EstadoSolicitudSuministro implements Serializable, EntidadTrascender {
	public static final long serialVersionUID = -522501756746027596L;

	@Id
	@SequenceGenerator(allocationSize = 1, name="GEN_ID_ESTADO_SOL_SUM", sequenceName="GEN_ID_ESTADO_SOL_SUM")
	@GeneratedValue(generator="GEN_ID_ESTADO_SOL_SUM", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_ESTADO_SOLICITUD_SUMINISTRO")
	private long idEstadoSolicitudSuministro = -1;
	
	private String nombre;
	
	private String descripcion;
	
	@Column(name = "ESTADO_FINAL")
	private boolean estadoFinal;
	
	@Column(name = "USADO_EN_CONTRATACION")
	private boolean usadoEnContratacion;
	
	@Column(name = "ESTADO_INICIAL")
	private boolean estadoInicial;
	
	private boolean modificable;
	
	@Column(name = "USADO_EN_MOVIMIENTOS")
	private boolean usadoEnMovimientos;
	
	public boolean isUsadoEnMovimientos() {
		return usadoEnMovimientos;
	}

	public void setUsadoEnMovimientos(boolean usadoEnMovimientos) {
		this.usadoEnMovimientos = usadoEnMovimientos;
	}

	public boolean isEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(boolean estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public boolean isModificable() {
		return modificable;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}

	public long getIdEstadoSolicitudSuministro() {
		return idEstadoSolicitudSuministro;
	}

	public void setIdEstadoSolicitudSuministro(long idEstadoSolicitudSuministro) {
		this.idEstadoSolicitudSuministro = idEstadoSolicitudSuministro;
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

	public boolean isEstadoFinal() {
		return estadoFinal;
	}

	public void setEstadoFinal(boolean estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public boolean isUsadoEnContratacion() {
		return usadoEnContratacion;
	}

	public void setUsadoEnContratacion(boolean usadoEnContratacion) {
		this.usadoEnContratacion = usadoEnContratacion;
	}
	
	@Override
	public String toString(){
		return this.nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idEstadoSolicitudSuministro ^ (idEstadoSolicitudSuministro >>> 32));
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
		EstadoSolicitudSuministro other = (EstadoSolicitudSuministro) obj;
		if (idEstadoSolicitudSuministro != other.idEstadoSolicitudSuministro)
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
			return this.idEstadoSolicitudSuministro;
		}

		public String getNombrePropiedadId() {
			return "idEstadoSolicitudSuministro";
		}

		public boolean isAuditable() {
			return true;
		}
}