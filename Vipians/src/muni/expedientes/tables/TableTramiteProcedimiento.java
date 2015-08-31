/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;

public class TableTramiteProcedimiento extends TableBean {

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaTramitesPorFaseProcedimientos();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaTramitesPorFaseProcedimientos(lista);
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addToList(List pList, Object pObject) {
		TramiteProcedimiento nuevaTramiteProcedimiento = (TramiteProcedimiento) pObject;
		List locTramitesProcedimientos = pList;

		TramiteProcedimiento deLaTabla = null;
		boolean esta = false;
		int i = 0;
		while(i < locTramitesProcedimientos.size() && !esta) {
			deLaTabla = (TramiteProcedimiento) locTramitesProcedimientos.get(i++);
			esta = (deLaTabla.getIdNodoProcedimiento() == nuevaTramiteProcedimiento.getIdNodoProcedimiento());
		}
		if(!esta) {
			locTramitesProcedimientos.add(nuevaTramiteProcedimiento);
		} else {
			warn("El TramiteProcedimiento que intenta agregar ya se encuentra en la lista.");
		}
	}
	
}