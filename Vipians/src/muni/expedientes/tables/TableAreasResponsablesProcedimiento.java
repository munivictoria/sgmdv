/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

public class TableAreasResponsablesProcedimiento extends TableAreas {

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return this.getCommunicationExpedientesBean().getListaAreasResponsables();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		this.getCommunicationExpedientesBean().setListaAreasResponsables(lista);
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

}