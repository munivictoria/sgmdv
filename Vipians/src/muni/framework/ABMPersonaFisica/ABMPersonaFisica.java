/*
 * ABMPersonaFisica.java
 *
 * Created on 5 de octubre de 2006, 14:54
 * Copyright Trascender SRL
 */
package muni.framework.ABMPersonaFisica;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaFisica.EstadoCivil;
import com.trascender.framework.recurso.persistent.PersonaFisica.Sexo;
import com.trascender.framework.recurso.persistent.PersonaFisica.TipoDocumento;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMPersonaFisica extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		// Sexo
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Sexo.values(), "cap");
		ddSexoDefaultOptions.setOptions(op);

		// Estado Civil
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(EstadoCivil.values(), "cap");
		ddEstadoCivilDefaultOptions.setOptions(op);

		// Tipo Documento
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoDocumento.values(), "may");
		ddTipoDocumentoDefaultOptions.setOptions(op);
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfCuil = new TextField();

	public TextField getTfCuil() {
		return tfCuil;
	}

	public void setTfCuil(TextField tf) {
		this.tfCuil = tf;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextField tfApellido = new TextField();

	public TextField getTfApellido() {
		return tfApellido;
	}

	public void setTfApellido(TextField tf) {
		this.tfApellido = tf;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private DropDown ddTipoDocumento = new DropDown();

	public DropDown getDdTipoDocumento() {
		return ddTipoDocumento;
	}

	public void setDdTipoDocumento(DropDown dd) {
		this.ddTipoDocumento = dd;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private TextField tfNumeroDocumento = new TextField();

	public TextField getTfNumeroDocumento() {
		return tfNumeroDocumento;
	}

	public void setTfNumeroDocumento(TextField tf) {
		this.tfNumeroDocumento = tf;
	}

	private Label label8 = new Label();

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label l) {
		this.label8 = l;
	}

	private TextField tfTelefono = new TextField();

	public TextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(TextField tf) {
		this.tfTelefono = tf;
	}

	private Label label9 = new Label();

	public Label getLabel9() {
		return label9;
	}

	public void setLabel9(Label l) {
		this.label9 = l;
	}

	private TextField tfEdad = new TextField();

	public TextField getTfEdad() {
		return tfEdad;
	}

	public void setTfEdad(TextField tf) {
		this.tfEdad = tf;
	}

	private Label label10 = new Label();

	public Label getLabel10() {
		return label10;
	}

	public void setLabel10(Label l) {
		this.label10 = l;
	}

	private TextField tfCelular = new TextField();

	public TextField getTfCelular() {
		return tfCelular;
	}

	public void setTfCelular(TextField tf) {
		this.tfCelular = tf;
	}

	private Label label11 = new Label();

	public Label getLabel11() {
		return label11;
	}

	public void setLabel11(Label l) {
		this.label11 = l;
	}

	private TextField tfEmail = new TextField();

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tf) {
		this.tfEmail = tf;
	}

	private Label label12 = new Label();

	public Label getLabel12() {
		return label12;
	}

	public void setLabel12(Label l) {
		this.label12 = l;
	}

	private Label label13 = new Label();

	public Label getLabel13() {
		return label13;
	}

	public void setLabel13(Label l) {
		this.label13 = l;
	}

	private Label label14 = new Label();

	public Label getLabel14() {
		return label14;
	}

	public void setLabel14(Label l) {
		this.label14 = l;
	}

	private SingleSelectOptionsList ddTipoDocumentoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoDocumentoDefaultOptions() {
		return ddTipoDocumentoDefaultOptions;
	}

	public void setDdTipoDocumentoDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoDocumentoDefaultOptions = ssol;
	}

	private TextField tfNacimiento = new TextField();

	public TextField getTfNacimiento() {
		return tfNacimiento;
	}

	public void setTfNacimiento(TextField tf) {
		this.tfNacimiento = tf;
	}

	private DropDown ddEstadoCivil = new DropDown();

	public DropDown getDdEstadoCivil() {
		return ddEstadoCivil;
	}

	public void setDdEstadoCivil(DropDown dd) {
		this.ddEstadoCivil = dd;
	}

	private SingleSelectOptionsList ddEstadoCivilDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdEstadoCivilDefaultOptions() {
		return ddEstadoCivilDefaultOptions;
	}

	public void setDdEstadoCivilDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddEstadoCivilDefaultOptions = ssol;
	}

	private Button btnCopiarDomicilioFiscal = new Button();

	public Button getBtnCopiarDomicilioFiscal() {
		return btnCopiarDomicilioFiscal;
	}
	
	public void setBtnCopiarDomicilioFiscal(Button btnCopiarDomicilioFiscal) {
		this.btnCopiarDomicilioFiscal = btnCopiarDomicilioFiscal;
	}
	
	private Button btnCopiarDomicilioPostal= new Button();

	public Button getBtnCopiarDomicilioPostal() {
		return btnCopiarDomicilioPostal;
	}

	public void setBtnCopiarDomicilioPostal(Button btnCopiarDomicilioPostal) {
		this.btnCopiarDomicilioPostal = btnCopiarDomicilioPostal;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private DropDown ddSexo = new DropDown();

	public DropDown getDdSexo() {
		return ddSexo;
	}

	public void setDdSexo(DropDown dd) {
		this.ddSexo = dd;
	}

	private SingleSelectOptionsList ddSexoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdSexoDefaultOptions() {
		return ddSexoDefaultOptions;
	}

	public void setDdSexoDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddSexoDefaultOptions = ssol;
	}

	private Button btnSeleccionarDomicilio = new Button();

	public Button getBtnSeleccionarDomicilio() {
		return btnSeleccionarDomicilio;
	}

	public void setBtnSeleccionarDomicilio(Button b) {
		this.btnSeleccionarDomicilio = b;
	}

	private Button btnSeleccionarDomicilioPostal = new Button();

	public Button getBtnSeleccionarDomicilioPostal() {
		return btnSeleccionarDomicilioPostal;
	}

	public void setBtnSeleccionarDomicilioPostal(Button b) {
		this.btnSeleccionarDomicilioPostal = b;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText stDomicilio = new StaticText();
	private StaticText stTitulo = new StaticText();
	private StaticText stSeparador = new StaticText();

	public StaticText getStDomicilio() {
		return stDomicilio;
	}

	public void setStDomicilio(StaticText st) {
		this.stDomicilio = st;
	}

	private StaticText stDomicilioPostal = new StaticText();

	public StaticText getStDomicilioPostal() {
		return stDomicilioPostal;
	}

	public void setStDomicilioPostal(StaticText st) {
		this.stDomicilioPostal = st;
	}

	private Checkbox cbJubilado = new Checkbox();

	public Checkbox getCbJubilado() {
		return cbJubilado;
	}

	public void setCbJubilado(Checkbox c) {
		this.cbJubilado = c;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private HtmlAjaxCommandButton btnGenerarCuit = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnGenerarCuit() {
		return btnGenerarCuit;
	}

	public void setBtnGenerarCuit(HtmlAjaxCommandButton btnGenerarCuit) {
		this.btnGenerarCuit = btnGenerarCuit;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMPersonaFisica() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PersonaFisica());
		ep.getObjetos().add(ind++, new Domicilio());
		ep.getObjetos().add(ind++, new Domicilio()); // Domicilio Postal
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		PersonaFisica persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(ind++, PersonaFisica.class);
		Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		Domicilio domicilioPostal = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		persona.setCuil(this.getTextFieldValue(getTfCuil()));
		persona.setTipoDocumento(this.getDDEnumValue(getDdTipoDocumento(), TipoDocumento.class));
		persona.setNumeroDocumento(this.getTextFieldValue(getTfNumeroDocumento()));	
		persona.setApellido(this.getTextFieldValue(getTfApellido()));
		persona.setNombre(this.getTextFieldValue(getTfNombre()));
		persona.setSexo(this.getDDEnumValue(getDdSexo(), Sexo.class));
		persona.setEstadoCivil(this.getDDEnumValue(getDdEstadoCivil(), EstadoCivil.class));
		persona.setFechaNacimiento(this.getTextFieldValueDate(getTfNacimiento()));
		persona.setEdad(this.getTextFieldValueInteger(getTfEdad()));
		persona.setJubilado((Boolean) getCbJubilado().getValue());
		persona.setTelefono(this.getTextFieldValue(getTfTelefono()));
		persona.setCelular(this.getTextFieldValue(getTfCelular()));
		persona.setEmail(this.getTextFieldValue(getTfEmail()));

		if (domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		persona.setDomicilio(domicilio);

		if (domicilioPostal.getLocalidad() == null) {
			domicilioPostal = null;
		}
		persona.setDomicilioPostal(domicilioPostal);

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		persona.setListaAtributosDinamicos(atributosDinamicos);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, domicilioPostal);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		PersonaFisica persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(0, PersonaFisica.class);

		this.acomodarSeleccionado();

		if (this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, respuesta);
		}

		if (persona.getListaAtributosDinamicos() != null) {
			persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(0, PersonaFisica.class);
			try {
				ArrayList atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(PersonaFisica.serialVersionUID, persona.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(3, atributosDinamicos);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		int ind = 0;
		persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(ind++, PersonaFisica.class);
		Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		Domicilio domicilioPostal = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, getNombreBean());
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.getTfCuil().setText(persona.getCuil());
		ddTipoDocumento.setSelected(Util.getEnumNameFromString(String.valueOf(persona.getTipoDocumento())));
		ddTipoDocumentoDefaultOptions.setSelectedValue(Util.getEnumNameFromString(String.valueOf(persona.getTipoDocumento())));
		this.getTfNumeroDocumento().setText(persona.getNumeroDocumento());		
		this.getTfApellido().setText(persona.getApellido());
		this.getTfNombre().setText(persona.getNombre());
		ddSexo.setSelected(Util.getEnumNameFromString(String.valueOf(persona.getSexo())));
		ddSexoDefaultOptions.setSelectedValue(Util.getEnumNameFromString(String.valueOf(persona.getSexo())));
		ddEstadoCivil.setSelected(Util.getEnumNameFromString(String.valueOf(persona.getEstadoCivil())));
		ddEstadoCivilDefaultOptions.setSelectedValue(Util.getEnumNameFromString(String.valueOf(persona.getEstadoCivil())));
		this.getTfNacimiento().setText(Conversor.getStringDeFechaCorta(persona.getFechaNacimiento()));
		if (persona.getEdad() != null) {
			this.getTfEdad().setText(persona.getEdad().toString());
		}
		this.getCbJubilado().setValue(new Boolean(persona.isJubilado()));
		this.getTfTelefono().setText(persona.getTelefono());
		this.getTfCelular().setText(persona.getCelular());
		this.getTfEmail().setText(persona.getEmail());

		this.getStDomicilio().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
		this.getStDomicilioPostal().setText(domicilioPostal.getDomicilioCompleto().replaceAll("\n", "<br>"));

		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}

	protected List getListaDelCommunicationAtributosDinamicos() {
		return this.getComunicationBean().getListaAtributosDinamicosPersonasFisicas();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getComunicationBean().setListaAtributosDinamicosPersonasFisicas(lista);
	}

	public String btnSeleccionarDomicilio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		int posicionObjetoSeleccionado = 1;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(1, Domicilio.class);
			Localidad localidad = domicilio.getLocalidad();

			if (localidad != null) {
				this.getRequestBean1().setObjetoABM(domicilio);
				retorno = "ModificarDomicilio";
			} else {
				retorno = "AgregarDomicilio";
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarDomicilioPostal_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		int posicionObjetoSeleccionado = 2;

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(2, Domicilio.class);
			Localidad localidad = domicilio.getLocalidad();

			if (localidad != null) {
				this.getRequestBean1().setObjetoABM(domicilio);
				retorno = "ModificarDomicilio";
			} else {
				retorno = "AgregarDomicilio";
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void btnCopiarDomicilioFiscal_action() {
		Domicilio locDomicilio = (Domicilio) this.obtenerObjetoDelElementoPila(1, Domicilio.class);

		if (locDomicilio != null && locDomicilio.getLocalidad() != null) {

			PersonaFisica persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(0, PersonaFisica.class);
			persona.setDomicilioPostal(locDomicilio);

			this.getElementoPila().getObjetos().set(2, locDomicilio);
			this.getStDomicilioPostal().setText(locDomicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
			this.guardarEstadoObjetosUsados();
		}
	}
	
	public void btnCopiarDomicilioPostal_action(){
		Domicilio locDomicilio = (Domicilio) this.obtenerObjetoDelElementoPila(2, Domicilio.class);
		
		if(locDomicilio != null && locDomicilio.getLocalidad() != null) {
			PersonaFisica persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(0, PersonaFisica.class);
			persona.setDomicilio(locDomicilio);
			this.getElementoPila().getObjetos().set(1, locDomicilio);
			this.getStDomicilio().setText(locDomicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPersonaFisica";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PersonaFisica persona = (PersonaFisica) pObject;
		Domicilio domicilio = persona.getDomicilio();
		Domicilio domicilioPostal = persona.getDomicilioPostal();
		ArrayList atributosDinamicos = null;

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, domicilioPostal);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}
	
	@Override
	public long getSerialVersionUID() {
		return PersonaFisica.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMPersonaFisica$ABMPersonaFisica}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		PersonaFisica locPersona = this.obtenerObjetoDelElementoPila(0, PersonaFisica.class);
		this.getTablaLogs().getLdpLogs().setList(locPersona.getListaLogsAuditoria());
	}
}