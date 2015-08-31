/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.panels;

import java.util.List;

import javax.faces.context.FacesContext;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.Plazo;
import com.trascender.expedientes.recurso.persistent.PlazoDatosCalculados;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.presentacion.conversores.Conversor;

public class PanelPlazoExpediente {

	private Label lblFechaInicioPlazo = new Label();
	private TextField tfFechaInicioPlazo = new TextField();
	private Label lblFechaFinPlazo = new Label();
	private TextField tfFechaFinPlazo = new TextField();
	private Label lblCantidadDias = new Label();
	private TextField tfCantidadDias = new TextField();
	private Label lblCantidadDiasRestantes = new Label();
	private TextField tfCantidadDiasRestantes = new TextField();
	private Checkbox chDiasCorridos = new Checkbox();
	private PanelGroup pgPlazo = new PanelGroup();

	private List<DiaFeriado> diasFeriados = getCommunicationExpedientesBean().getListaFeriados();

	public Label getLblFechaInicioPlazo() {
		return lblFechaInicioPlazo;
	}

	public void setLblFechaInicioPlazo(Label lblFechaInicioPlazo) {
		this.lblFechaInicioPlazo = lblFechaInicioPlazo;
	}

	public TextField getTfFechaInicioPlazo() {
		return tfFechaInicioPlazo;
	}

	public void setTfFechaInicioPlazo(TextField tfFechaInicioPlazo) {
		this.tfFechaInicioPlazo = tfFechaInicioPlazo;
	}

	public Label getLblFechaFinPlazo() {
		return lblFechaFinPlazo;
	}

	public void setLblFechaFinPlazo(Label lblFechaFinPlazo) {
		this.lblFechaFinPlazo = lblFechaFinPlazo;
	}

	public TextField getTfFechaFinPlazo() {
		return tfFechaFinPlazo;
	}

	public void setTfFechaFinPlazo(TextField tfFechaFinPlazo) {
		this.tfFechaFinPlazo = tfFechaFinPlazo;
	}

	public Label getLblCantidadDias() {
		return lblCantidadDias;
	}

	public void setLblCantidadDias(Label lblCantidadDias) {
		this.lblCantidadDias = lblCantidadDias;
	}

	public TextField getTfCantidadDias() {
		return tfCantidadDias;
	}

	public void setTfCantidadDias(TextField tfCantidadDias) {
		this.tfCantidadDias = tfCantidadDias;
	}

	public Label getLblCantidadDiasRestantes() {
		return lblCantidadDiasRestantes;
	}

	public void setLblCantidadDiasRestantes(Label lblCantidadDiasRestantes) {
		this.lblCantidadDiasRestantes = lblCantidadDiasRestantes;
	}

	public TextField getTfCantidadDiasRestantes() {
		return tfCantidadDiasRestantes;
	}

	public void setTfCantidadDiasRestantes(TextField tfCantidadDiasRestantes) {
		this.tfCantidadDiasRestantes = tfCantidadDiasRestantes;
	}

	public Checkbox getChDiasCorridos() {
		return chDiasCorridos;
	}

	public void setChDiasCorridos(Checkbox chDiasCorridos) {
		this.chDiasCorridos = chDiasCorridos;
	}

	public void mostrarDatos(Plazo pPlazo) {
		if(pPlazo != null && pPlazo.getFechaInicio() != null) {
			PlazoDatosCalculados locDatosPlazo = pPlazo.getDatosCalculados(diasFeriados);
			this.tfFechaInicioPlazo.setText(Conversor.getStringDeFechaCorta(locDatosPlazo.getFechaInicial()));
			this.tfFechaFinPlazo.setText(Conversor.getStringDeFechaCorta(locDatosPlazo.getFechaFinal()));
			this.tfCantidadDias.setText(locDatosPlazo.getCantidadDias());
			this.tfCantidadDiasRestantes.setText(locDatosPlazo.getCantidadDiasRestantes());
			this.chDiasCorridos.setValue(locDatosPlazo.isDiasCorridos());
		} else {
			this.pgPlazo.setRendered(false);
		}
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	public Object getSessionBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(pBeanName);
	}

	public PanelGroup getPgPlazo() {
		return pgPlazo;
	}

	public void setPgPlazo(PanelGroup pgPlazo) {
		this.pgPlazo = pgPlazo;
	}

}