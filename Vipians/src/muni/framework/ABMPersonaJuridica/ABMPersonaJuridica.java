/*
 * ModificarPersonaJuridica.java
 *
 * Created on 17 de octubre de 2006, 11:20
 * Copyright Trascender SRL
 */
package muni.framework.ABMPersonaJuridica;

import java.util.ArrayList;
import java.util.HashSet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.Socio;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
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
public class ABMPersonaJuridica extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(Socio.CargoSocietario.values(), "may");
		ddCargoSocietarioDefaultOptions.setOptions(op);

		Option[] opOrganismo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PersonaJuridica.OrganismoEmisor.values(), "may");
		ddOrganismoDefaultOptions.setOptions(opOrganismo);

		Option[] opTipoSoc = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PersonaJuridica.TipoSocietario.values(), "may");
		ddTipoSocDefaultOptions.setOptions(opTipoSoc);
	}

	private Label lblOrganismoEmisor = new Label();
	private Label lblNombreFantasia = new Label();
	private Label lblNroSociedad = new Label();
	private Label lblTipoSocietario = new Label();
	private Label lblIngresosBrutos = new Label();
	private Label lblNroConvenio = new Label();

	private TextField tfNombreFantasia = new TextField();
	private TextField tfNroSociedad = new TextField();
	private TextField tfNroIngresosBrutos = new TextField();
	private TextField tfNroConvenio = new TextField();

	private DropDown ddOrganismoEmisor = new DropDown();
	private DropDown ddTipoSocietario = new DropDown();

	private SingleSelectOptionsList ddOrganismoDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddTipoSocDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdOrganismoDefaultOptions() {
		return ddOrganismoDefaultOptions;
	}

	public void setDdOrganismoDefaultOptions(SingleSelectOptionsList ddOrganismoDefaultOptions) {
		this.ddOrganismoDefaultOptions = ddOrganismoDefaultOptions;
	}

	public DropDown getDdOrganismoEmisor() {
		return ddOrganismoEmisor;
	}

	public void setDdOrganismoEmisor(DropDown ddOrganismoEmisor) {
		this.ddOrganismoEmisor = ddOrganismoEmisor;
	}

	public SingleSelectOptionsList getDdTipoSocDefaultOptions() {
		return ddTipoSocDefaultOptions;
	}

	public void setDdTipoSocDefaultOptions(SingleSelectOptionsList ddTipoSocDefaultOptions) {
		this.ddTipoSocDefaultOptions = ddTipoSocDefaultOptions;
	}

	public DropDown getDdTipoSocietario() {
		return ddTipoSocietario;
	}

	public void setDdTipoSocietario(DropDown ddTipoSocietario) {
		this.ddTipoSocietario = ddTipoSocietario;
	}

	public Label getLblIngresosBrutos() {
		return lblIngresosBrutos;
	}

	public void setLblIngresosBrutos(Label lblIngresosBrutos) {
		this.lblIngresosBrutos = lblIngresosBrutos;
	}

	public Label getLblNombreFantasia() {
		return lblNombreFantasia;
	}

	public void setLblNombreFantasia(Label lblNombreFantasia) {
		this.lblNombreFantasia = lblNombreFantasia;
	}

	public Label getLblNroConvenio() {
		return lblNroConvenio;
	}

	public void setLblNroConvenio(Label lblNroConvenio) {
		this.lblNroConvenio = lblNroConvenio;
	}

	public Label getLblNroSociedad() {
		return lblNroSociedad;
	}

	public void setLblNroSociedad(Label lblNroSociedad) {
		this.lblNroSociedad = lblNroSociedad;
	}

	public Label getLblOrganismoEmisor() {
		return lblOrganismoEmisor;
	}

	public void setLblOrganismoEmisor(Label lblOrganismoEmisor) {
		this.lblOrganismoEmisor = lblOrganismoEmisor;
	}

	public Label getLblTipoSocietario() {
		return lblTipoSocietario;
	}

	public void setLblTipoSocietario(Label lblTipoSocietario) {
		this.lblTipoSocietario = lblTipoSocietario;
	}

	public TextField getTfNombreFantasia() {
		return tfNombreFantasia;
	}

	public void setTfNombreFantasia(TextField tfNombreFantasia) {
		this.tfNombreFantasia = tfNombreFantasia;
	}

	public TextField getTfNroConvenio() {
		return tfNroConvenio;
	}

	public void setTfNroConvenio(TextField tfNroConvenio) {
		this.tfNroConvenio = tfNroConvenio;
	}

	public TextField getTfNroIngresosBrutos() {
		return tfNroIngresosBrutos;
	}

	public void setTfNroIngresosBrutos(TextField tfNroIngresosBrutos) {
		this.tfNroIngresosBrutos = tfNroIngresosBrutos;
	}

	public TextField getTfNroSociedad() {
		return tfNroSociedad;
	}

	public void setTfNroSociedad(TextField tfNroSociedad) {
		this.tfNroSociedad = tfNroSociedad;
	}

	private DropDown ddCargo = new DropDown();

	public DropDown getDdCargo() {
		return ddCargo;
	}

	public void setDdCargo(DropDown ddCargo) {
		this.ddCargo = ddCargo;
	}

	private SingleSelectOptionsList ddCargoSocietarioDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdCargoSocietarioDefaultOptions() {
		return ddCargoSocietarioDefaultOptions;
	}

	public void setDdCargoSocietarioDefaultOptions(SingleSelectOptionsList ddCargoSocietarioDefaultOptions) {
		this.ddCargoSocietarioDefaultOptions = ddCargoSocietarioDefaultOptions;
	}

	private TextField tfCuit = new TextField();

	public TextField getTfCuit() {
		return tfCuit;
	}

	public void setTfCuit(TextField tf) {
		this.tfCuit = tf;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private TextField tfRazonSocial = new TextField();

	public TextField getTfRazonSocial() {
		return tfRazonSocial;
	}

	public void setTfRazonSocial(TextField tf) {
		this.tfRazonSocial = tf;
	}

	private TextField tfTelefono = new TextField();

	public TextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(TextField tf) {
		this.tfTelefono = tf;
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

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
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

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private ObjectListDataProvider ldpSocios = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpSocios() {
		return ldpSocios;
	}

	public void setLdpSocios(ObjectListDataProvider oldp) {
		this.ldpSocios = oldp;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	private DropDown listaCargos = new DropDown();

	public DropDown getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(DropDown listaCargos) {
		this.listaCargos = listaCargos;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Button btnCopiarDomicilioFiscal = new Button();

	public Button getBtnCopiarDomicilioFiscal() {
		return btnCopiarDomicilioFiscal;
	}

	public void setBtnCopiarDomicilioFiscal(Button btnCopiarDomicilioFiscal) {
		this.btnCopiarDomicilioFiscal = btnCopiarDomicilioFiscal;
	}
	
	private Button btnCopiarDomicilioPostal = new Button();

	public Button getBtnCopiarDomicilioPostal() {
		return btnCopiarDomicilioPostal;
	}

	public void setBtnCopiarDomicilioPostal(Button btnCopiarDomicilioPostal) {
		this.btnCopiarDomicilioPostal = btnCopiarDomicilioPostal;
	}

	private Button btnAgregar = new Button();
	private Button btnAgregarPJ = new Button();

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button b) {
		this.btnAgregar = b;
	}

	public Button getBtnAgregarPJ() {
		return btnAgregarPJ;
	}

	public void setBtnAgregarPJ(Button btnAgregarPJ) {
		this.btnAgregarPJ = btnAgregarPJ;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	 protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private RadioButton radioButton2 = new RadioButton();

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton rb) {
		this.radioButton2 = rb;
	}

	 protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfNacionalidad = new TextField();

	public TextField getTfNacionalidad() {
		return tfNacionalidad;
	}

	public void setTfNacionalidad(TextField tf) {
		this.tfNacionalidad = tf;
	}

	private StaticText stDomicilio = new StaticText();

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

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMPersonaJuridica() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PersonaJuridica());
		ep.getObjetos().add(ind++, new Domicilio());
		ep.getObjetos().add(ind++, new Domicilio()); // Domicilio Postal
		ep.getObjetos().add(ind++, new ArrayList());
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		PersonaJuridica persona = (PersonaJuridica) this.obtenerObjetoDelElementoPila(ind++, PersonaJuridica.class);
		Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		Domicilio domicilioPostal = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		ArrayList socios = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		persona.setCuit(this.getTextFieldValue(getTfCuit()));
		persona.setRazonSocial(this.getTextFieldValue(getTfRazonSocial()));
		persona.setNombreFantasia(this.getTextFieldValue(getTfNombreFantasia()));
		persona.setNumeroSociedad(this.getTextFieldValue(getTfNroSociedad()));
		persona.setNumeroIngresosBrutos(this.getTextFieldValue(getTfNroIngresosBrutos()));
		persona.setNumeroConvenioMultilateral(this.getTextFieldValue(getTfNroConvenio()));
		persona.setOrganismoEmisor(this.getDDEnumValue(getDdOrganismoEmisor(), PersonaJuridica.OrganismoEmisor.class));
		persona.setTipoSocietario(this.getDDEnumValue(getDdTipoSocietario(), PersonaJuridica.TipoSocietario.class));
		persona.setTelefono(this.getTextFieldValue(getTfTelefono()));
		persona.setCelular(this.getTextFieldValue(getTfCelular()));
		persona.setEmail(this.getTextFieldValue(getTfEmail()));
		persona.setNacionalidad(this.getTextFieldValue(getTfNacionalidad()));

		if (domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		persona.setDomicilio(domicilio);

		if (domicilioPostal.getLocalidad() == null) {
			domicilioPostal = null;
		}
		persona.setDomicilioPostal(domicilioPostal);

		this.getObjectListDataProvider().commitChanges();
		socios = (ArrayList) this.getObjectListDataProvider().getList();
		if (socios.isEmpty()) {
			socios = null;
		}
		this.setListaDelCommunication(socios);

		if (socios != null) {
			persona.setListaSocios(new HashSet(socios));
		} else {
			persona.setListaSocios(null);
		}

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		persona.setListaAtributosDinamicos(atributosDinamicos);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, domicilioPostal);
		this.getElementoPila().getObjetos().set(ind++, socios);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		PersonaJuridica persona = null;
		ArrayList socios = null;
		ArrayList atributosDinamicos = null;

		this.acomodarSeleccionado();

		if (this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, respuesta);
		}

		if (this.getListaDelCommunicationAtributosDinamicos() == null) {
			persona = (PersonaJuridica) this.obtenerObjetoDelElementoPila(0, PersonaJuridica.class);
			try {
				atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(PersonaJuridica.serialVersionUID, persona.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(4, atributosDinamicos);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		ind = 0;
		persona = (PersonaJuridica) this.obtenerObjetoDelElementoPila(ind++, PersonaJuridica.class);
		Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		Domicilio domicilioPostal = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		socios = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{framework$ABMPersonaJuridica$ABMPersonaJuridica}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.getTfCuit().setText(persona.getCuit());
		this.getTfRazonSocial().setText(persona.getRazonSocial());

		this.getTfNombreFantasia().setText(persona.getNombreFantasia());
		this.getTfNroSociedad().setText(persona.getNumeroSociedad());
		this.getTfNroIngresosBrutos().setText(persona.getNumeroIngresosBrutos());
		this.getTfNroConvenio().setText(persona.getNumeroConvenioMultilateral());

		this.getTfTelefono().setText(persona.getTelefono());
		this.getTfCelular().setText(persona.getCelular());
		this.getTfEmail().setText(persona.getEmail());
		this.getTfNacionalidad().setText(persona.getNacionalidad());

		this.getDdOrganismoEmisor().setSelected(Util.getEnumNameFromString(String.valueOf(persona.getOrganismoEmisor())));
		this.getDdOrganismoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(persona.getOrganismoEmisor())));

		this.getDdTipoSocietario().setSelected(Util.getEnumNameFromString(String.valueOf(persona.getTipoSocietario())));
		this.getDdTipoSocDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(persona.getTipoSocietario())));

		this.getDdOrganismoEmisor().setSelected(Util.getEnumNameFromString(String.valueOf(persona.getOrganismoEmisor())));
		this.getDdTipoSocietario().setSelected(Util.getEnumNameFromString(String.valueOf(persona.getTipoSocietario())));

		this.getStDomicilio().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
		this.getStDomicilioPostal().setText(domicilioPostal.getDomicilioCompleto().replaceAll("\n", "<br/>"));

		this.getObjectListDataProvider().setList(socios);
		this.setListaDelCommunication(socios);

		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}

	private ArrayList getListaDelCommunicationAtributosDinamicos() {
		return this.getComunicationBean().getListaAtributosDinamicosPersonasJuridicas();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getComunicationBean().setListaAtributosDinamicosPersonasJuridicas(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpSocios();
	}

	private ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaSocios();
	}

	private void setListaDelCommunication(ArrayList lista) {
		this.getComunicationBean().setListaSocios(lista);
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if (selected != null) {
			lastSelected = selected;
		}
	}

	protected int getNroFila(String pCadena) {
		// Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
	}

	public String getPrincipalRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setPrincipalRow(int row) {
	}

	private Object lastPrincipalSelected = null;

	public Object getRBPrincipalSelected() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastPrincipalSelected) ? sv : null;
	}

	public void setRBPrincipalSelected(Object selected) {
		if (selected != null) {
			lastPrincipalSelected = selected;
		}
	}

	public void seleccionarFilaPrincipal(Object principal) {
		int cantFilas = this.getObjectListDataProvider().getList().size();
		System.out.println("------------CANTIDAD DE FILAS:" + cantFilas);
		PersonaFisica enTabla = null;
		for (int i = 0; i < cantFilas; i++) {
			enTabla = (PersonaFisica) this.getObjectListDataProvider().getObjects()[i];
			if (enTabla.getIdPersonaFisica() == ((PersonaFisica) principal).getIdPersonaFisica()) {
				lastPrincipalSelected = new Long(i).toString();
			}
		}
	}

	public RowKey getPrincipalSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	private Object obtenerPrincipalSeleccionado() {
		RowKey rk = null;
		Object ppal = null;
		try {
			rk = this.getPrincipalSeleccionado();
			if (rk != null) {
				int index = getNroFila(rk.toString());
				ppal = this.getObjectListDataProvider().getObjects()[index];
			}
		} catch (Exception ex) {
		}
		return ppal;
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if (rk != null) {
			while (!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while (!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if (!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if (!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if (cantFilas > 0) {
			// si hay al menos una fila
			if (posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}
			;

			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	public String btnSeleccionarDomicilio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 1;

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(posicionObjetoSeleccionado, Domicilio.class);
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

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 2;

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilioPostal = (Domicilio) this.obtenerObjetoDelElementoPila(posicionObjetoSeleccionado, Domicilio.class);
			Localidad localidad = domicilioPostal.getLocalidad();

			if (localidad != null) {
				this.getRequestBean1().setObjetoABM(domicilioPostal);
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

			PersonaJuridica persona = (PersonaJuridica) this.obtenerObjetoDelElementoPila(0, PersonaJuridica.class);
			persona.setDomicilioPostal(locDomicilio);

			this.getElementoPila().getObjetos().set(2, locDomicilio);
			this.getStDomicilioPostal().setText(locDomicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
			this.guardarEstadoObjetosUsados();
		}
	}
	
	public void btnCopiarDomicilioPostal_action() {
		Domicilio locDomicilio = (Domicilio) this.obtenerObjetoDelElementoPila(2, Domicilio.class);

		if (locDomicilio != null && locDomicilio.getLocalidad() != null) {

			PersonaJuridica persona = (PersonaJuridica) this.obtenerObjetoDelElementoPila(0, PersonaJuridica.class);
			persona.setDomicilio(locDomicilio);

			this.getElementoPila().getObjetos().set(1, locDomicilio);
			this.getStDomicilio().setText(locDomicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
			this.guardarEstadoObjetosUsados();
		}
	}

	public String btnAgregar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarPJ_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminPersonaJuridica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(obj);
				}
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunication().clear();
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPersonaJuridica";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Persona) {
			Persona nuevoSocio = (Persona) pObject;
			ArrayList socios = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

			Socio deLaTabla = null;
			boolean esta = false;
			int i = 0;
			while (i < socios.size() && !esta) {
				deLaTabla = (Socio) socios.get(i++);
				esta = deLaTabla.getPersona().equals(nuevoSocio);
			}
			if (!esta) {
				Socio locSocio = new Socio();
				locSocio.setPersona(nuevoSocio);
				locSocio.setCargo(null);
				socios.add(locSocio);
			} else {
				warn("La Persona que intenta agregar ya se encuentra en la lista.");
			}

			this.getElementoPila().getObjetos().set(3, socios);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PersonaJuridica persona = (PersonaJuridica) this.getRequestBean1().getObjetoABM();
		Domicilio domicilio = persona.getDomicilio();
		Domicilio domicilioPostal = persona.getDomicilioPostal();
		ArrayList socios = new ArrayList(persona.getListaSocios());
		ArrayList atributosDinamicos = null;

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, domicilioPostal);
		this.getElementoPila().getObjetos().set(ind++, socios);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}
	
	@Override
	public long getSerialVersionUID() {
		return PersonaJuridica.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMPersonaJuridica$ABMPersonaJuridica}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		PersonaJuridica locPersona = this.obtenerObjetoDelElementoPila(0, PersonaJuridica.class);
		this.getTablaLogs().getLdpLogs().setList(locPersona.getListaLogsAuditoria());
	}
}