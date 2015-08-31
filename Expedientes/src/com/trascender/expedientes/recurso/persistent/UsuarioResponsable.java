/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@DiscriminatorValue(value = "usuario")
public class UsuarioResponsable extends Responsabilidad {

	private static final long serialVersionUID = 1997279905609244626L;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@Override
	public Object getEntidadResponsable() {
		return getUsuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}