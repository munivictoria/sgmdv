package muni.expedientes.tables;

import java.util.List;

import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;

public class TableTramiteCatalogo extends TableBean {

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaTramitesPorFaseCatalogo();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		this.getCommunicationExpedientesBean().setListaTramitesPorFaseCatalogo(lista);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addToList(List pList, Object pObject) {

		TramiteCatalogo nuevaTramiteCatalogo = (TramiteCatalogo) pObject;
		List locTramitesCatalogos = pList;

		TramiteCatalogo deLaTabla = null;
		boolean esta = false;
		int i = 0;
		while (i < locTramitesCatalogos.size() && !esta) {
			deLaTabla = (TramiteCatalogo) locTramitesCatalogos.get(i++);
			esta = (deLaTabla.getIdTramiteCatalogo() == nuevaTramiteCatalogo.getIdTramiteCatalogo());
		}
		if (!esta) {
			locTramitesCatalogos.add(nuevaTramiteCatalogo);
		} else {
			warn("El Tramite de Catalogo que intenta agregar ya se encuentra en la lista.");
		}
	}

}
