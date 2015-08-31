/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

import muni.SessionBean1;

import com.sun.rave.web.ui.component.Button;
import com.trascender.expedientes.recurso.persistent.Documento;
import com.trascender.framework.recurso.persistent.Usuario;

public class TableDocumentos extends TableBean {

	private Button btnModificar = new Button();
	private Button btnProcesarReporte = new Button();
	private Button btnImprimirReporte = new Button();
	private Usuario usuario = getSessionBean1().getUsuario();

	public Button getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(Button btnModificar) {
		this.btnModificar = btnModificar;
	}

	public Button getBtnProcesarReporte() {
		return btnProcesarReporte;
	}

	public void setBtnProcesarReporte(Button btnProcesarReporte) {
		this.btnProcesarReporte = btnProcesarReporte;
	}

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	private SessionBean1 getSessionBean1() {
		return (SessionBean1) getSessionBean("SessionBean1");
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaDocumentoTramites();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaDocumentoTramites(lista);
	}

	public List getListaDelCommunication2() {
		return getCommunicationExpedientesBean().getListaDocumentoTramitesProcesar();
	}

	public void setListaDelCommunication2(List lista) {
		getCommunicationExpedientesBean().setListaDocumentoTramitesProcesar(lista);
	}

	@Override
	public void _init() {
		if(this.getListaDelCommunication2() != null) {
			this.getObjectListDataProvider2().setList(getListaDelCommunication2());
		}
		super._init();
	}

	public class WDocumento {

		public WDocumento(Object pDocumento, Integer pIndex) {

			documento = (Documento) pDocumento;
			index = pIndex;
		}

		public boolean cancel = false;
		public Documento documento;
		public Integer index;

	}

	@SuppressWarnings("unchecked")
	public void actualizarDocumento(WDocumento pWDocumento) {
		if(pWDocumento.index != null) {
			this.getListaDelCommunication().set(pWDocumento.index, pWDocumento.documento);
		} else {
			this.getListaDelCommunication().add(pWDocumento.documento);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addToList(List pList, Object pObject) {
	}

	// public class ConverterEstado implements Converter {
	//
	// @Override
	// public Object getAsObject(FacesContext context, UIComponent component, String value) {
	// if (Estado.PRESENTADO.toString().equals(value)){
	// int index = Integer.valueOf(getCurrentRow());
	// Documento documento = (Documento)objectListDataProvider.getObjects()[index];
	// documento.cambioEstado(usuario, Estado.PRESENTADO);
	// return Estado.PRESENTADO;
	// }
	// return Estado.NO_PRESENTADO;
	// }

	// @Override
	// public String getAsString(FacesContext context, UIComponent component, Object value) {
	// return value.toString();
	// }
	//
	// }

}