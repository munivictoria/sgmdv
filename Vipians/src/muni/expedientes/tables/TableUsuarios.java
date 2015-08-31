/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

import com.sun.rave.web.ui.component.StaticText;
import com.trascender.expedientes.recurso.persistent.UsuarioResponsable;
import com.trascender.framework.recurso.persistent.Usuario;

public abstract class TableUsuarios extends TableBean {

	StaticText staticTextNombre = new StaticText();

	public StaticText getStaticTextNombre() {
		return staticTextNombre;
	}

	public void setStaticTextNombre(StaticText staticTextNombre) {
		this.staticTextNombre = staticTextNombre;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addToList(List pList, Object pObject) {
		Usuario nuevoUsuario = (Usuario) pObject;
		List locUsuarios = pList;

		UsuarioResponsable deLaTabla = null;
		boolean esta = false;
		int i = 0;
		while(i < locUsuarios.size() && !esta) {
			deLaTabla = (UsuarioResponsable) locUsuarios.get(i++);
			esta = (deLaTabla.getUsuario().getIdUsuario() == nuevoUsuario.getIdUsuario());
		}
		if(!esta) {
			UsuarioResponsable nuevoUsuarioResponsable = new UsuarioResponsable();
			nuevoUsuarioResponsable.setUsuario(nuevoUsuario);
			locUsuarios.add(nuevoUsuarioResponsable);
		} else {
			warn("El Usuario que intenta agregar ya se encuentra en la lista.");
		}
	}

}