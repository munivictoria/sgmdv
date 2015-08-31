/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.UsuarioExtensor;

public class TableUsuariosExtensores extends TableBean {

	StaticText staticTextNombre = new StaticText();
	TextField tfCantidadDiasMaximo = new TextField();

	public TextField getTfCantidadDiasMaximo() {
		return tfCantidadDiasMaximo;
	}

	public void setTfCantidadDiasMaximo(TextField tfCantidadDiasMaximo) {
		this.tfCantidadDiasMaximo = tfCantidadDiasMaximo;
	}

	public StaticText getStaticTextNombre() {
		return staticTextNombre;
	}

	public void setStaticTextNombre(StaticText staticTextNombre) {
		this.staticTextNombre = staticTextNombre;
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	@Override
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaUsuariosExtensores();
	}

	@Override
	public void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaUsuariosExtensores(lista);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addToList(List pList, Object pObject) {
		UsuarioExtensor nuevoUsuario = (UsuarioExtensor) pObject;
		List locUsuariosExtensores = pList;

		UsuarioExtensor deLaTabla = null;
		boolean esta = false;
		int i = 0;
		while(i < locUsuariosExtensores.size() && !esta) {
			deLaTabla = (UsuarioExtensor) locUsuariosExtensores.get(i++);
			esta = (deLaTabla.getUsuario().getIdUsuario() == nuevoUsuario.getUsuario().getIdUsuario());
		}
		if(!esta) {
			locUsuariosExtensores.add(nuevoUsuario);
		} else {
			warn("El Usuario que intenta agregar ya se encuentra en la lista.");
		}
	}

}