/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

public class TableUsuariosResponsablesProcedimiento extends TableUsuarios {

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return this.getCommunicationExpedientesBean().getListaUsuariosResponsables();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		this.getCommunicationExpedientesBean().setListaUsuariosResponsables(lista);
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

}