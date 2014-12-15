package com.trascender.framework.util;

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

@Entity
@Table(name = "LOG_AUDITORIA")
public class LogAuditoria implements Serializable{
	private static final long serialVersionUID = -2239216715851324536L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_log_auditoria")
	@SequenceGenerator(name = "gen_id_log_auditoria", sequenceName = "gen_id_log_auditoria", allocationSize = 1)
	@Column(name = "ID_LOG_AUDITORIA")
	private long idLogAuditoria = -1;

	@Column(name = "ID_RECURSO")
	private long idRecurso;

	@Column(name = "ID_ENTIDAD")
	private long idEntidad;

	private String propiedad;

	@Column(name = "VALOR_NUEVO")
	private String valorNuevo;

	@Column(name = "VALOR_ANTERIOR")
	private String valorAnterior;

	private String comentario;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", insertable=false, updatable=false)
	private Usuario usuario;

	@Column(name = "ID_USUARIO")
	private long idUsuario;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	private Date fecha;

	@Column(name="ID_ENTIDAD_NUEVA")
	private Long idEntidadNueva;

	@Column(name ="ID_ENTIDAD_ANTERIOR")
	private Long idEntidadAnterior;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo pTipo) {
		tipo = pTipo;
	}

	public enum Tipo{CREO, ELIMINO, MODIFICO, AGREGO, QUITO}

	public String getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(String pPropiedad) {
		propiedad = pPropiedad;
	}

	public String getValorNuevo() {
		return valorNuevo;
	}

	public void setValorNuevo(String pValorNuevo) {
		valorNuevo = pValorNuevo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String pComentario) {
		comentario = pComentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario pUsuario) {
		usuario = pUsuario;
	}

	public long getIdLogAuditoria() {
		return idLogAuditoria;
	}

	public void setIdLogAuditoria(long pIdLogAuditoria) {
		idLogAuditoria = pIdLogAuditoria;
	}

	public long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(long pIdRecurso) {
		idRecurso = pIdRecurso;
	}

	public long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(long pIdEntidad) {
		idEntidad = pIdEntidad;
	}

	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String pValorAnterior) {
		valorAnterior = pValorAnterior;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date pFecha) {
		fecha = pFecha;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long pIdUsuario) {
		idUsuario = pIdUsuario;
	}

	public long getIdEntidadNueva() {
		return idEntidadNueva;
	}

	public void setIdEntidadNueva(Long pIdEntidadNueva) {
		idEntidadNueva = pIdEntidadNueva;
	}

	public long getIdEntidadAnterior() {
		return idEntidadAnterior;
	}

	public void setIdEntidadAnterior(Long pIdEntidadAnterior) {
		idEntidadAnterior = pIdEntidadAnterior;
	}

}
