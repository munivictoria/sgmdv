package muni.expedientes.panels;

import java.util.List;

import muni.expedientes.tables.TableAreas;

public class TableAreasResponsablesNodo extends TableAreas {

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaAreasResponsablesNodo();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaAreasResponsablesNodo(lista);
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

}
