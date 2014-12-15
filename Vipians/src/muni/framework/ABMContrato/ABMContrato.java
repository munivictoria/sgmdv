/*
 * AgregarContrato.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */

package muni.framework.ABMContrato;

import java.rmi.RemoteException;

import muni.CommunicationMesaEntradaBean;
import muni.compras.ABMFacturaContrato.ABMFacturaContrato;
import muni.compras.ABMFacturaSubsidio.ABMFacturaSubsidio;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMContrato extends ABMPageBean {

	protected TextField tfTotal = new TextField();
	protected TextField tfCodigoContrato = new TextField();
	protected TextField tfPersona = new TextField();
	protected TextField tfFechaFin = new TextField();
	protected TextField tfCantidadCuotas = new TextField();
	protected TextField tfPrecioCuotas = new TextField();
	protected TextField tfFechaInicio = new TextField();

	protected Label label1 = new Label();
	protected Label label4 = new Label();
	protected Label label5 = new Label();

	protected Button btnSeleccionarPersonaFisica = new Button();
	protected Button btnSeleccionarPersonaJuridica = new Button();

	protected TextArea taDescripcion = new TextArea();

	protected HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

	protected Label label2 = new Label();
	protected Label label3 = new Label();
	protected Label lblTotal = new Label();
	protected Label lblCantidadCuotas = new Label();
	protected Label lblPrecioCuotas = new Label();

	protected StaticText staticText1 = new StaticText();
	protected StaticText staticText3 = new StaticText();

	// ***GETTERS & SETTERS***

	public TextField getTfTotal() {
		return tfTotal;
	}

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public void setTfTotal(TextField tf) {
		this.tfTotal = tf;
	}

	public TextField getTfCodigoContrato() {
		return tfCodigoContrato;
	}

	public void setTfCodigoContrato(TextField tf) {
		this.tfCodigoContrato = tf;
	}

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	public Label getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(Label l) {
		this.lblTotal = l;
	}

	public Label getLblCantidadCuotas() {
		return lblCantidadCuotas;
	}

	public void setLblCantidadCuotas(Label l) {
		this.lblCantidadCuotas = l;
	}

	public Label getLblPrecioCuotas() {
		return lblPrecioCuotas;
	}

	public void setLblPrecioCuotas(Label l) {
		this.lblPrecioCuotas = l;
	}

	public TextField getTfFechaFin() {
		return tfFechaFin;
	}

	public void setTfFechaFin(TextField tf) {
		this.tfFechaFin = tf;
	}

	public TextField getTfCantidadCuotas() {
		return tfCantidadCuotas;
	}

	public void setTfCantidadCuotas(TextField tf) {
		this.tfCantidadCuotas = tf;
	}

	public TextField getTfPrecioCuotas() {
		return tfPrecioCuotas;
	}

	public void setTfPrecioCuotas(TextField tf) {
		this.tfPrecioCuotas = tf;
	}

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	public TextField getTfFechaInicio() {
		return tfFechaInicio;
	}

	public void setTfFechaInicio(TextField tf) {
		this.tfFechaInicio = tf;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	// **METHODS***
	public ABMContrato() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new Contrato());
		ep.getObjetos().add(ind++, null); // Persona

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Contrato contrato = (Contrato) this.obtenerObjetoDelElementoPila(0, Contrato.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);

		contrato.setTotal(this.getTextFieldValueDouble(getTfTotal()));
		contrato.setPrecioPorCuota(this.getTextFieldValueDouble(getTfPrecioCuotas()));
		contrato.setCantidadCuotas(this.getTextFieldValueInteger(getTfCantidadCuotas()));
		contrato.setCodigoContrato(this.getTextFieldValue(getTfCodigoContrato()));
		contrato.setFechaInicio(this.getTextFieldValueDate(getTfFechaInicio()));
		contrato.setFechaFin(this.getTextFieldValueDate(getTfFechaFin()));
		contrato.setDescripcion(this.getTextAreaValue(getTaDescripcion()));

		if(persona == null || persona.getIdPersona() == -1) {
			persona = null;
		}
		contrato.setPersona(persona);

		this.getElementoPila().getObjetos().set(0, contrato);
		this.getElementoPila().getObjetos().set(1, persona);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		this.acomodarSeleccionado();

		Contrato contrato = (Contrato) this.obtenerObjetoDelElementoPila(0, Contrato.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);

		if(contrato.getCodigoContrato() != null) {
			this.getTfCodigoContrato().setText(contrato.getCodigoContrato());
		}
		if(contrato.getCantidadCuotas() != null) {
			this.getTfCantidadCuotas().setText(contrato.getCantidadCuotas().toString());
		}
		if(contrato.getPrecioPorCuota() != null) {
			this.getTfPrecioCuotas().setText(contrato.getPrecioPorCuota().toString());
		}
		if(contrato.getTotal() != null) {
			this.getTfTotal().setText(contrato.getTotal().toString());
		}

		if(contrato.getFechaInicio() != null) {
			this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(contrato.getFechaInicio()));
		}
		if(contrato.getFechaFin() != null) {
			this.getTfFechaFin().setText(Conversor.getStringDeFechaCorta(contrato.getFechaFin()));
		}
		this.getTaDescripcion().setText(contrato.getDescripcion());

		if(persona != null) {
			this.getTfPersona().setText(persona.toString());
		}

	}

	protected ABMFacturaSubsidio getcompras$ABMFacturaSubsidio$AgregarFacturaSubsidio() {
		return (ABMFacturaSubsidio) getBean("compras$ABMFacturaSubsidio$AgregarFacturaSubsidio");
	}

	protected ABMFacturaContrato getcompras$ABMFacturaContrato$AgregarFacturaContrato() {
		return (ABMFacturaContrato) getBean("compras$ABMFacturaContrato$AgregarFacturaContrato");
	}

	protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
		return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica", 1);
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica", 1);
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfPersona());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMContrato";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Contrato contrato = (Contrato) pObject;
		// Long idVehiculo = transporteVehicular.get .getIdParcela();
		//
		// try {
		// this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
		// parcela =
		// this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(idParcela.longValue());
		// } catch (Exception ex) {
		// log(CASO_NAVEGACION+"_BuscarParcelaPorIdError:", ex);
		// error(NOMBRE_PAGINA+" - No se pudo recuperar la Parcela vinculada al Local Comercial: "
		// + ex.getMessage());
		// }
		Persona persona = contrato.getPersona();

		this.getElementoPila().getObjetos().set(0, contrato);
		this.getElementoPila().getObjetos().set(1, persona);
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Persona persona = null;

		try {
			persona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, persona);
	}

	public boolean isHayPersona() {
		Persona locPersona = (Persona) this.obtenerObjetoDelElementoPila(1);
		return(locPersona != null && locPersona.getIdPersona() != -1);
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMContrato$ABMContrato}";
	}

	@Override
	public long getSerialVersionUID() {
		return Contrato.serialVersionUID;
	}
}