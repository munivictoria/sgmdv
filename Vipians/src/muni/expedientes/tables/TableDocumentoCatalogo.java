/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;

public class TableDocumentoCatalogo extends TableBean {

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaDocumentosPorTramiteCatalogo();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		this.getCommunicationExpedientesBean().setListaDocumentosPorTramiteCatalogo(lista);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addToList(List pList, Object pObject) {
		DocumentoCatalogo nuevaDocumentoCatalogo = (DocumentoCatalogo) pObject;
		List locDocumentosCatalogos = pList;

		DocumentoCatalogo deLaTabla = null;
		boolean esta = false;
		int i = 0;
		while(i < locDocumentosCatalogos.size() && !esta) {
			deLaTabla = (DocumentoCatalogo) locDocumentosCatalogos.get(i++);
			esta = (deLaTabla.getIdDocumentoCatalogo() == nuevaDocumentoCatalogo.getIdDocumentoCatalogo());
		}
		if(!esta) {
			locDocumentosCatalogos.add(nuevaDocumentoCatalogo);
		} else {
			warn("El Documento de Catalogo que intenta agregar ya se encuentra en la lista.");
		}
	}

}