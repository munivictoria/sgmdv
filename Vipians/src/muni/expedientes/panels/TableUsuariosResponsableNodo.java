package muni.expedientes.panels;

import java.util.List;

import muni.expedientes.tables.TableUsuarios;

public class TableUsuariosResponsableNodo extends TableUsuarios {

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		// TODO Auto-generated method stub
		return getCommunicationExpedientesBean().getListaUsuariosResponsablesNodo();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaUsuariosResponsablesNodo(lista);
		
	}

	
	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}
}
