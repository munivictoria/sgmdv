package muni.expedientes.tables;

import java.util.List;

import com.trascender.expedientes.recurso.persistent.EstadoTramite;

public class TableEstadosTramite extends TableBean {

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addToList(List pList, Object pObject) {

		EstadoTramite nuevaEstadosTramite = (EstadoTramite) pObject;
		List locEstadosTramite = pList;

		EstadoTramite deLaTabla = null;
		boolean esta = false;
		int i = 0;
		while (i < locEstadosTramite.size() && !esta) {
			deLaTabla = (EstadoTramite) locEstadosTramite.get(i++);
			esta = (deLaTabla.getIdEstadoTramite() == nuevaEstadosTramite.getIdEstadoTramite());
		}
		if (!esta) {
			locEstadosTramite.add(nuevaEstadosTramite);
		} else {
			warn("El estado trámite que intenta agregar ya se encuentra en la lista.");
		}
	}

	@Override
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaEstadosTramitePorTramiteCatalogo();
	}

	@Override
	public void setListaDelCommunication(List lista) {
		this.getCommunicationExpedientesBean().setListaEstadosTramitePorTramiteCatalogo(lista);
	}
}
