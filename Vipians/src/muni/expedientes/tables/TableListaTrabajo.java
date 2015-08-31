/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

import javax.faces.context.FacesContext;

import muni.CommunicationExpedientesBean;
import muni.SessionBean1;
import muni.expedientes.utils.FiltroListaTrabajo;

import com.sun.rave.web.ui.component.Button;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;

public class TableListaTrabajo extends TableBean {

	private Button btnModificar = new Button();
	private Button btnConsultar = new Button();

	public Button getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(Button btnModificar) {
		this.btnModificar = btnModificar;
	}

	public Button getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(Button btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return getListaExpedientes();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void setListaDelCommunication(List lista) {
		FiltroListaTrabajo flTrabajo = getCommunicationExpedienteBean().getFiltroListaTrabajo();
		flTrabajo.setListaResultado(lista);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addToList(List pList, Object pObject) {
	}

	private List<NodoExpediente> getListaExpedientes() {
		FiltroListaTrabajo flTrabajo = getCommunicationExpedienteBean().getFiltroListaTrabajo();
		
		return flTrabajo.getListaResultado();
	}

	private CommunicationExpedientesBean getCommunicationExpedienteBean() {
		return (CommunicationExpedientesBean) this.getSessionBean("CommunicationExpedientesBean");
	}

	@SuppressWarnings("unused")
	private SessionBean1 getSessionBean1() {
		return (SessionBean1) getSessionBean("SessionBean1");
	}

	@Override
	public Object getSessionBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(pBeanName);
	}

}