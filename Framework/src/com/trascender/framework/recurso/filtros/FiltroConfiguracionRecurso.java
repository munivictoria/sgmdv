
package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroConfiguracionRecurso extends FiltroAbstracto<ConfiguracionRecurso> {

	public FiltroConfiguracionRecurso() {
	}

	public FiltroConfiguracionRecurso(Usuario pUsuario, Recurso pRecurso, String pNombreAlias) {
		usuario = pUsuario;
		recurso = pRecurso;
		nombreAlias = pNombreAlias;
	}

	public static final long serialVersionUID = -399308052158213574L;

	private Usuario usuario;
	private Recurso recurso;
	private String nombreAlias;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario pUsuario) {
		usuario = pUsuario;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso pRecurso) {
		recurso = pRecurso;
	}

	public String getNombreAlias() {
		if(nombreAlias != null && nombreAlias.equals("")) {
			return null;
		}
		return nombreAlias;
	}

	public void setNombreAlias(String pNombreAlias) {
		nombreAlias = pNombreAlias;
	}

}