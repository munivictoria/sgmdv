/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

import muni.expedientes.ABMExpediente.PlazoExpediente;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.framework.recurso.persistent.DiaFeriado;

public class TableTramite extends TableBean {

	private HtmlAjaxCommandButton btnModificarTramite = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnConsultarTramite = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnModificarTramite() {
		return btnModificarTramite;
	}

	public void setBtnModificarTramite(HtmlAjaxCommandButton btnModificarTramite) {
		this.btnModificarTramite = btnModificarTramite;
	}

	public HtmlAjaxCommandButton getBtnConsultarTramite() {
		return btnConsultarTramite;
	}

	public void setBtnConsultarTramite(HtmlAjaxCommandButton btnConsultarTramite) {
		this.btnConsultarTramite = btnConsultarTramite;
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaTramitesExpediente();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaTramitesExpediente(lista);
	}

	@SuppressWarnings("unused")
	private List<DiaFeriado> getDiasFeriados() {
		return getCommunicationExpedientesBean().getListaFeriados();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addToList(List pList, Object pObject) {
	}

	public class WTramite {

		public WTramite(Object pTramite, int pIndex) {
			tramite = (Tramite) pTramite;
			index = pIndex;
		}

		public boolean cancel = false;
		public Tramite tramite;
		public Integer index;
		public PlazoExpediente plazo;
		
	}

	@SuppressWarnings("unchecked")
	public void actualizarTramite(WTramite pWtramite) {
		this.getListaDelCommunication().set(pWtramite.index, pWtramite.tramite);
	}

	Integer cont = 0;

	public String getDiasRestantes() {
		String diasRestantes = "--";
		// Tramite locT = (Tramite) objectListDataProvider.getObjects()[cont++];
		// Plazo p = locT.getPlazo();
		// if ( p!=null && !locT.getEstado().equals(EstadoTramite.CERRADO)) {
		// diasRestantes = Integer.valueOf(p.getDatosCalculados(getDiasFeriados()).getCantidadDias()).toString();
		// }
		
		return diasRestantes;
	}

}