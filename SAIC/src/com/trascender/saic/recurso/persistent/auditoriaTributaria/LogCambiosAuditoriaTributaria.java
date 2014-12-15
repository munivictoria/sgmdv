package com.trascender.saic.recurso.persistent.auditoriaTributaria;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.Util;
/**
 * @author jsantacruz
 */

@Entity
@Table(name="LOG_CAMBIOS_A_T")
public class LogCambiosAuditoriaTributaria implements Serializable {
	
	public static final long serialVersionUID = -8059777215566710773L;

	@Id
	@GeneratedValue(generator="gen_id_log_audit_trib",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize=1, name="gen_id_log_audit_trib", sequenceName="gen_id_log_audit_trib")
	@Column(name="ID_LOG_CAMBIOS_A_T")
	private long idLogCambiosAudotoriaTributaria = -1;
	
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_ANTERIOR")
	private AuditoriaTributaria.EstadoAuditoriaTributaria estadoAnterior;
	
	private String causa;
	
	@ManyToOne
	@JoinColumn(name="ID_AUDITORIA_TRIBUTARIA",nullable=false)
	private AuditoriaTributaria auditoriaTributaria;
	
	public long getIdLogCambiosAudotoriaTributaria() {
		return idLogCambiosAudotoriaTributaria;
	}
	public void setIdLogCambiosAudotoriaTributaria(long idLogCambiosAudotoriaTributaria) {
		this.idLogCambiosAudotoriaTributaria = idLogCambiosAudotoriaTributaria;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	
	public AuditoriaTributaria.EstadoAuditoriaTributaria getEstadoAnterior() {
		return estadoAnterior;
	}
	public void setEstadoAnterior(AuditoriaTributaria.EstadoAuditoriaTributaria estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}
	
	public AuditoriaTributaria getAuditoriaTributaria() {
		return auditoriaTributaria;
	}
	public void setAuditoriaTributaria(AuditoriaTributaria auditoriaTributaria) {
		this.auditoriaTributaria = auditoriaTributaria;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLogCambiosAudotoriaTributaria ^ (idLogCambiosAudotoriaTributaria >>> 32));
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
		LogCambiosAuditoriaTributaria other = (LogCambiosAuditoriaTributaria) obj;
		if (idLogCambiosAudotoriaTributaria != other.idLogCambiosAudotoriaTributaria)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[Usuario: " + this.getUsuario() + ", "+ Util.getString(this.getFecha()) + ", Estado Anterior: " + estadoAnterior + "]";
	}
}
