package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.Usuario.Estado;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroUsuario extends FiltroAbstracto<Usuario>{

	public FiltroUsuario() {
	}
	public FiltroUsuario(String pUser, String pPassword, Estado pEstado,
			PersonaFisica pPersonaFisica) {
		user = pUser;
		password = pPassword;
		estado = pEstado;
		personaFisica = pPersonaFisica;
	}
	private static final long serialVersionUID = 4929950967848674491L;
	
	
	private String user;
	private String password;
	private Usuario.Estado estado;
	private PersonaFisica personaFisica;
	
	public String getUser() {
		return user;
	}
	public void setUser(String pUser) {
		user = pUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pPassword) {
		password = pPassword;
	}
	public com.trascender.framework.recurso.persistent.Usuario.Estado getEstado() {
		return estado;
	}
	public void setEstado(
			com.trascender.framework.recurso.persistent.Usuario.Estado pEstado) {
		estado = pEstado;
	}
	public com.trascender.framework.recurso.persistent.PersonaFisica getPersonaFisica() {
		return personaFisica;
	}
	public void setPersonaFisica(
			com.trascender.framework.recurso.persistent.PersonaFisica pPersonaFisica) {
		personaFisica = pPersonaFisica;
	}

}
