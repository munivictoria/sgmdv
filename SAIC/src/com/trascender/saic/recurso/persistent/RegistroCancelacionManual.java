package com.trascender.saic.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;

/**
 * Se deberia usar para cancelar deudas de forma manual, por ejemplo, en las migraciones de deudas
 * anteriores, o por por la gente del centro de computos. 
 * @author fgareis
 *
 */
@Entity
@Table(name = "REGISTRO_CANCELACION_MANUAL")
public class RegistroCancelacionManual extends RegistroCancelacion {
	
	public static final long serialVersionUID = -5567067645300842417L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	private String comentario;

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		super.toString();
		return this.getUsuario().toString();
	}
}
