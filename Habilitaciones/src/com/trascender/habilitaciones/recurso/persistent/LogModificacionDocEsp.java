package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;


@Entity
@Table(name="log_modificacion_doc_esp")
public class LogModificacionDocEsp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7019437547479057854L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_log_modificacion_docesp")
	@SequenceGenerator(name = "gen_id_log_modificacion_docesp", sequenceName = "gen_id_log_modificacion_docesp", allocationSize = 1)
	@Column(name = "ID_LOG_MODIFICACION_DOC_ESP")
	private long idLogModificacionDocEsp = -1;
	
	@Column(name = "COMENTARIO")
	private String comentario;
	
	@Column(name = "FECHA")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name="ID_DOC_HAB_ESPECIALIZADO")
	private DocHabilitanteEspecializado docHabilitanteEspecializado;
	
	public long getIdLogModificacionDocEsp() {
		return idLogModificacionDocEsp;
	}

	public void setIdLogModificacionDocEsp(long idLogModificacionDocEsp) {
		this.idLogModificacionDocEsp = idLogModificacionDocEsp;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public DocHabilitanteEspecializado getDocHabilitanteEspecializado() {
		return docHabilitanteEspecializado;
	}

	public void setDocHabilitanteEspecializado(
			DocHabilitanteEspecializado docHabilitanteEspecializado) {
		this.docHabilitanteEspecializado = docHabilitanteEspecializado;
	}
	
}
