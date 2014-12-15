/*
 * ABMDocEspTGI.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpTGI.ABMDocEspTGI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import muni.catastro.ABMParcela.ParcelaModel;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
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
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.Tasa;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMDocEspTGI extends ABMPageBean {

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Modificar Obligaci\363n: TGI";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "ModificarDocEspTGI";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.

	private Table tablaDocsGeneradoresDeuda = new Table();
	private ObjectListDataProvider ldpDocsGeneradoresDeuda = new ObjectListDataProvider();
	private StaticText stDocsGeneradoresDeuda = new StaticText();
	private TableRowGroup trgDocsGeneradoresDeuda = new TableRowGroup();
	private TableColumn tcAnio = new TableColumn();
	private TableColumn tcPlan = new TableColumn();
	private StaticText stAnio = new StaticText();
	private StaticText stPlan = new StaticText();

	private Table tablaLogsLiquidacion = new Table();
	private TableRowGroup tableRowGroup = new TableRowGroup();
	private ObjectListDataProvider ldpLogsLiquidacion = new ObjectListDataProvider();

	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();
	private TableColumn tableColumn8 = new TableColumn();

	private StaticText staticText3 = new StaticText();
	private StaticText staticText4 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText8 = new StaticText();

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	private NumberConverter numberConverter1 = new NumberConverter();

	private PanelGroup gpFiltroTablaLogsLiquidacion = new PanelGroup();

	private TextField tfFiltrarUsuarioLogLiq = new TextField();
	private DropDown ddFiltrarEventoLogLiq = new DropDown();
	private SingleSelectOptionsList ddFiltrarEventoLogLiqOptions = new SingleSelectOptionsList();
	private TextField tfFiltrarFechaDesdeLogLiq = new TextField();
	private TextField tfFiltrarFechaHastaLogLiq = new TextField();

	private HtmlAjaxCommandButton btnFiltrarLogLiq = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarFiltradoLogLiq = new HtmlAjaxCommandButton();

	public Table getTablaLogsLiquidacion() {
		return tablaLogsLiquidacion;
	}

	public void setTablaLogsLiquidacion(Table tablaLogsLiquidacion) {
		this.tablaLogsLiquidacion = tablaLogsLiquidacion;
	}

	public TableRowGroup getTableRowGroup() {
		return tableRowGroup;
	}

	public void setTableRowGroup(TableRowGroup tableRowGroup) {
		this.tableRowGroup = tableRowGroup;
	}

	public ObjectListDataProvider getLdpLogsLiquidacion() {
		return ldpLogsLiquidacion;
	}

	public void setLdpLogsLiquidacion(ObjectListDataProvider ldpLogsLiquidacion) {
		this.ldpLogsLiquidacion = ldpLogsLiquidacion;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tableColumn6) {
		this.tableColumn6 = tableColumn6;
	}

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tableColumn8) {
		this.tableColumn8 = tableColumn8;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText staticText3) {
		this.staticText3 = staticText3;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText staticText7) {
		this.staticText7 = staticText7;
	}

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText staticText8) {
		this.staticText8 = staticText8;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	public PanelGroup getGpFiltroTablaLogsLiquidacion() {
		return gpFiltroTablaLogsLiquidacion;
	}

	public void setGpFiltroTablaLogsLiquidacion(PanelGroup gpFiltroTablaLogsLiquidacion) {
		this.gpFiltroTablaLogsLiquidacion = gpFiltroTablaLogsLiquidacion;
	}

	public TextField getTfFiltrarUsuarioLogLiq() {
		return tfFiltrarUsuarioLogLiq;
	}

	public void setTfFiltrarUsuarioLogLiq(TextField tfFiltrarUsuarioLogLiq) {
		this.tfFiltrarUsuarioLogLiq = tfFiltrarUsuarioLogLiq;
	}

	public DropDown getDdFiltrarEventoLogLiq() {
		return ddFiltrarEventoLogLiq;
	}

	public void setDdFiltrarEventoLogLiq(DropDown ddFiltrarEventoLogLiq) {
		this.ddFiltrarEventoLogLiq = ddFiltrarEventoLogLiq;
	}

	public SingleSelectOptionsList getDdFiltrarEventoLogLiqOptions() {
		return ddFiltrarEventoLogLiqOptions;
	}

	public void setDdFiltrarEventoLogLiqOptions(SingleSelectOptionsList ddFiltrarEventoLogLiqOptions) {
		this.ddFiltrarEventoLogLiqOptions = ddFiltrarEventoLogLiqOptions;
	}

	public TextField getTfFiltrarFechaDesdeLogLiq() {
		return tfFiltrarFechaDesdeLogLiq;
	}

	public void setTfFiltrarFechaDesdeLogLiq(TextField tfFiltrarFechaDesdeLogLiq) {
		this.tfFiltrarFechaDesdeLogLiq = tfFiltrarFechaDesdeLogLiq;
	}

	public TextField getTfFiltrarFechaHastaLogLiq() {
		return tfFiltrarFechaHastaLogLiq;
	}

	public void setTfFiltrarFechaHastaLogLiq(TextField tfFiltrarFechaHastaLogLiq) {
		this.tfFiltrarFechaHastaLogLiq = tfFiltrarFechaHastaLogLiq;
	}

	public HtmlAjaxCommandButton getBtnFiltrarLogLiq() {
		return btnFiltrarLogLiq;
	}

	public void setBtnFiltrarLogLiq(HtmlAjaxCommandButton btnFiltrarLogLiq) {
		this.btnFiltrarLogLiq = btnFiltrarLogLiq;
	}

	public HtmlAjaxCommandButton getBtnLimpiarFiltradoLogLiq() {
		return btnLimpiarFiltradoLogLiq;
	}

	public void setBtnLimpiarFiltradoLogLiq(HtmlAjaxCommandButton btnLimpiarFiltradoLogLiq) {
		this.btnLimpiarFiltradoLogLiq = btnLimpiarFiltradoLogLiq;
	}

	public StaticText getStDocsGeneradoresDeuda() {
		return stDocsGeneradoresDeuda;
	}

	public void setStDocsGeneradoresDeuda(StaticText stDocsGeneradoresDeuda) {
		this.stDocsGeneradoresDeuda = stDocsGeneradoresDeuda;
	}

	public TableRowGroup getTrgDocsGeneradoresDeuda() {
		return trgDocsGeneradoresDeuda;
	}

	public void setTrgDocsGeneradoresDeuda(TableRowGroup trgDocsGeneradoresDeuda) {
		this.trgDocsGeneradoresDeuda = trgDocsGeneradoresDeuda;
	}

	public TableColumn getTcAnio() {
		return tcAnio;
	}

	public void setTcAnio(TableColumn tcAnio) {
		this.tcAnio = tcAnio;
	}

	public TableColumn getTcPlan() {
		return tcPlan;
	}

	public void setTcPlan(TableColumn tcPlan) {
		this.tcPlan = tcPlan;
	}

	public StaticText getStAnio() {
		return stAnio;
	}

	public void setStAnio(StaticText stAnio) {
		this.stAnio = stAnio;
	}

	public StaticText getStPlan() {
		return stPlan;
	}

	public void setStPlan(StaticText stPlan) {
		this.stPlan = stPlan;
	}

	public Table getTablaDocsGeneradoresDeuda() {
		return tablaDocsGeneradoresDeuda;
	}

	public void setTablaDocsGeneradoresDeuda(Table tablaDocsGeneradoresDeuda) {
		this.tablaDocsGeneradoresDeuda = tablaDocsGeneradoresDeuda;
	}

	public ObjectListDataProvider getLdpDocsGeneradoresDeuda() {
		return ldpDocsGeneradoresDeuda;
	}

	public void setLdpDocsGeneradoresDeuda(ObjectListDataProvider ldpDocsGeneradoresDeuda) {
		this.ldpDocsGeneradoresDeuda = ldpDocsGeneradoresDeuda;
	}

	private Button btnConsultarParcela = new Button();

	public Button getBtnConsultarParcela() {
		return btnConsultarParcela;
	}

	public void setBtnConsultarParcela(Button btnConsultarParcela) {
		this.btnConsultarParcela = btnConsultarParcela;
	}

	private StaticText stPersona = new StaticText();

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText st) {
		this.stPersona = st;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private ObjectListDataProvider ldpLogModificacionesSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLogModificacionesSHPS() {
		return ldpLogModificacionesSHPS;
	}

	public void setLdpLogModificacionesSHPS(ObjectListDataProvider oldp) {
		this.ldpLogModificacionesSHPS = oldp;
	}

	private Button btnSeleccionarDomicilioPostal = new Button();

	public Button getBtnSeleccionarDomicilioPostal() {
		return btnSeleccionarDomicilioPostal;
	}

	public void setBtnSeleccionarDomicilioPostal(Button b) {
		this.btnSeleccionarDomicilioPostal = b;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private Button btnLimpiarPersona = new Button();

	public Button getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(Button b) {
		this.btnLimpiarPersona = b;
	}

	private HtmlAjaxCommandButton btnLimpiarDomicilioPostal = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarDomicilioPostal() {
		return btnLimpiarDomicilioPostal;
	}

	public void setBtnLimpiarDomicilioPostal(HtmlAjaxCommandButton btnLimpiarDomicilioPostal) {
		this.btnLimpiarDomicilioPostal = btnLimpiarDomicilioPostal;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private StaticText stDomicilioPostal = new StaticText();

	public StaticText getStDomicilioPostal() {
		return stDomicilioPostal;
	}

	public void setStDomicilioPostal(StaticText st) {
		this.stDomicilioPostal = st;
	}

	private Label lblParcela = new Label();

	public Label getLblParcela() {
		return lblParcela;
	}

	public void setLblParcela(Label l) {
		this.lblParcela = l;
	}

	private TextField tfParcela = new TextField();

	public TextField getTfParcela() {
		return tfParcela;
	}

	public void setTfParcela(TextField tf) {
		this.tfParcela = tf;
	}

	private Button btnSeleccionarDomicilioSolicitante = new Button();

	public Button getBtnSeleccionarDomicilioSolicitante() {
		return btnSeleccionarDomicilioSolicitante;
	}

	public void setBtnSeleccionarDomicilioSolicitante(Button b) {
		this.btnSeleccionarDomicilioSolicitante = b;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private TextArea taNombre = new TextArea();

	public TextArea getTaNombre() {
		return taNombre;
	}

	public void setTaNombre(TextArea ta) {
		this.taNombre = ta;
	}

	private Button btnSeleccionarDomicilioParcela = new Button();

	public Button getBtnSeleccionarDomicilioParcela() {
		return btnSeleccionarDomicilioParcela;
	}

	public void setBtnSeleccionarDomicilioParcela(Button b) {
		this.btnSeleccionarDomicilioParcela = b;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextField tfFechaInicio = new TextField();

	public TextField getTfFechaInicio() {
		return tfFechaInicio;
	}

	public void setTfFechaInicio(TextField tf) {
		this.tfFechaInicio = tf;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TextField tfFechaCese = new TextField();

	public TextField getTfFechaCese() {
		return tfFechaCese;
	}

	public void setTfFechaCese(TextField tf) {
		this.tfFechaCese = tf;
	}

	private HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(HtmlAjaxCommandButton btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

	private Button btnSeleccionarParcela = new Button();

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button b) {
		this.btnSeleccionarParcela = b;
	}

	private ObjectListDataProvider ldpPropietarios = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPropietarios() {
		return ldpPropietarios;
	}

	public void setLdpPropietarios(ObjectListDataProvider oldp) {
		this.ldpPropietarios = oldp;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	public String getCurrentRow3() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow3(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public void seleccionarPersonaObligacion(Persona persona) {
		Integer cantidadPersonas = this.getObjectListDataProvider2().getList().size();

		Persona locPersona;
		for(int i = 0; i < cantidadPersonas; i++) {
			locPersona = (Persona) this.getObjectListDataProvider2().getObjects()[i];
			if(locPersona.getIdPersona() == persona.getIdPersona()) {
				lastSelected = new Long(i).toString();
			}
		}
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMDocEspTGI() {
	}

	@Override
	protected void _init() {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication());
		}
		getTablaDocsGeneradoresDeuda().setClearSortButton(true);
		getTable1().setClearSortButton(true);

		if(this.getCommunicationSAICBean().getListaLogLiquidacion() != null) {
			this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
		}

		Option[] opTipoEvento = null;
		opTipoEvento = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(LogLiquidacion.Evento.values(), "cap");
		ddFiltrarEventoLogLiqOptions.setOptions(opTipoEvento);

		dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		dateTimeConverter1.setPattern("dd/MM/yyyy HH:mm");
		dateTimeConverter1.setTimeStyle("full");
		numberConverter1.setPattern("$ #,##0.00");
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, null); // Obligacion
		ep.getObjetos().add(ind++, new DocumentoTGI());
		ep.getObjetos().add(ind++, null); // Persona solicitante
		ep.getObjetos().add(ind++, new Parcela());
		ep.getObjetos().add(ind++, new Domicilio());
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos
		ep.getObjetos().add(ind++, null); // 6 FiltroLogLiquidacion

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		if(this.getCommunicationSAICBean().getListaLogLiquidacion() != null) {
			this.getCommunicationSAICBean().getListaLogLiquidacion().clear();
		}

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Obligacion obligacion = this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
		DocumentoTGI documentoTGI = this.obtenerObjetoDelElementoPila(ind++, DocumentoTGI.class);
		Persona persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		Parcela parcela = this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		Domicilio domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		ArrayList listaPropietarios = new ArrayList();
		ArrayList atributosDinamicos = this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(6, FiltroLogLiquidacion.class);

		// Object nombre = this.getTaNombre().getText();
		// Object fechaInicio = this.getTfFechaInicio().getText();
		// Object fechaCese = this.getTfFechaCese().getText();

		documentoTGI.setNombre(getTextAreaValue(this.getTaNombre()));
		documentoTGI.setFechaInicioActividad(getTextFieldValueDate(this.getTfFechaInicio()));
		documentoTGI.setFechaCeseActividad(getTextFieldValueDate(this.getTfFechaCese()));

		if(parcela.getIdParcela() == -1) {
			parcela = null;
		} else {
			documentoTGI.setParcela(parcela);

			for(RegistroPropietario rp : parcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
				listaPropietarios.add(rp.getPersona());
			}
			this.getObjectListDataProvider2().setList(listaPropietarios);
			this.setListaDelCommunication(listaPropietarios);
		}
		documentoTGI.setParcela(parcela);
		if(domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		documentoTGI.setDomicilio(domicilio);

		// ////////
		RowKey rk = null;
		try {
			rk = this.getSeleccionado();
			if(rk != null) {
				int index = getNroFila(rk.toString());
				Object obj = this.getObjectListDataProvider2().getObjects()[index];
				persona = (Persona) obj;
				// domicilio =
				// (Domicilio)locRegistroPropietario.getPersona().getDomicilioPostal();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			// int posicionEP = -1;
			if(respuesta instanceof Domicilio) {
				int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
				this.getElementoPila().getObjetos().set(posicion, respuesta);
			}
		}

		obligacion.setDocumentoEspecializado(documentoTGI);
		obligacion.setPersona(persona);
		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		documentoTGI.setListaAtributosDinamicos(atributosDinamicos);

		this.getObjectListDataProvider2().setList(listaPropietarios);
		this.getLdpPropietarios().setObjectType(Persona.class);
		this.setListaDelCommunication(listaPropietarios);

		filtroLogLiq.setObligacion(obligacion);
		filtroLogLiq.setUsuario(getTextFieldValue(this.getTfFiltrarUsuarioLogLiq()));
		filtroLogLiq.setEvento(getDDEnumValue(this.getDdFiltrarEventoLogLiq(), LogLiquidacion.Evento.class));
		filtroLogLiq.setFechaDesde(getTextFieldValueDate(this.getTfFiltrarFechaDesdeLogLiq()));
		filtroLogLiq.setFechaHasta(getTextFieldValueDate(this.getTfFiltrarFechaHastaLogLiq()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, obligacion);
		this.getElementoPila().getObjetos().set(ind++, documentoTGI);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, parcela);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(5, atributosDinamicos);
		this.getElementoPila().getObjetos().set(6, filtroLogLiq);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Obligacion obligacion = null;
		DocumentoTGI documentoTGI = null;
		Persona persona = null;
		Parcela parcela = null;
		Domicilio domicilio = null;
		ArrayList listaPropietarios = new ArrayList();
		ArrayList atributosDinamicos = null;

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			if(respuesta instanceof Domicilio) {
				int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
				this.getElementoPila().getObjetos().set(posicion, respuesta);
			}
		}

		try {
			DocumentoTGI locDocumento = (DocumentoTGI) this.obtenerObjetoDelElementoPila(1);
			this.getElementoPila()
					.getObjetos()
					.set(5, this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoTGI.serialVersionUID, locDocumento.getListaAtributosDinamicos(), null));
		} catch(Exception e) {
			e.printStackTrace();
		}

		ind = 0;
		ind++;
		documentoTGI = this.obtenerObjetoDelElementoPila(ind++, DocumentoTGI.class);
		persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		parcela = this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		atributosDinamicos = this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(6, FiltroLogLiquidacion.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpTGI$ABMDocEspTGI$ABMDocEspTGI}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);

		this.getTaNombre().setText(documentoTGI.getNombre());
		if(documentoTGI.getFechaInicioActividad() != null) {
			this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoTGI.getFechaInicioActividad()));
		}
		if(documentoTGI.getFechaCeseActividad() != null) {
			this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoTGI.getFechaCeseActividad()));
		}
		if(persona != null) {
			// this.getTfPersona().setText(persona.toString());
			this.seleccionarPersonaObligacion(persona);
		}
		if(parcela != null && parcela.getIdParcela() != -1) {
			this.getTfParcela().setText(parcela.toString());
			for(RegistroPropietario rp : parcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
				listaPropietarios.add(rp.getPersona());
			}
			this.getObjectListDataProvider2().setList(listaPropietarios);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunication(listaPropietarios);
		} else {
			this.getObjectListDataProvider2().setList(null);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunication(null);
		}
		if(domicilio.getLocalidad() != null) {
			this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
		}

		this.getTfFiltrarUsuarioLogLiq().setText(filtroLogLiq.getUsuario());
		this.getDdFiltrarEventoLogLiq().setSelected(filtroLogLiq.getEvento());
		this.getTfFiltrarFechaDesdeLogLiq().setText(filtroLogLiq.getFechaDesde());
		this.getTfFiltrarFechaHastaLogLiq().setText(filtroLogLiq.getFechaHasta());
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosTGI(lista);
	}

	private void setListaDelCommunication(List lista) {

		this.getCommunicationHabilitacionesBean().setListaPropietarios(lista);
	}

	private List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaPropietarios();
	}

	private ObjectListDataProvider getObjectListDataProvider2() {

		return this.getLdpPropietarios();
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider2().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public String btnSeleccionarDomicilioPostal_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 4;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Persona locPersona = this.obtenerObjetoDelElementoPila(2, Persona.class);
			Parcela locParcela = this.obtenerObjetoDelElementoPila(3, Parcela.class);
			Domicilio locDomicilio = this.obtenerObjetoDelElementoPila(posicionObjetoSeleccionado, Domicilio.class);

			if(((locDomicilio.getIdDomicilio() != -1 && locParcela.getDomicilioParcelario().getIdDomicilio() != -1) && locDomicilio.getIdDomicilio() == locParcela
					.getDomicilioParcelario().getIdDomicilio())
					|| ((locDomicilio.getIdDomicilio() != -1 && locPersona.getDomicilioPostal().getIdDomicilio() != -1) && locDomicilio.getIdDomicilio() == locPersona
							.getDomicilioPostal().getIdDomicilio())) {
				retorno = "AgregarDomicilio";
			} else {
				this.getRequestBean1().setObjetoABM(locDomicilio);
				retorno = "ModificarDomicilio";
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarDomicilioPostal_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(4, this.getStDomicilioPostal());
			this.getStDomicilioPostal().setText(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarDomicilioSolicitante_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 4;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Persona persona = this.obtenerObjetoDelElementoPila(2, Persona.class);
			Domicilio domicilio = null;
			if(persona != null && persona.getDomicilioPostal() != null) {
				domicilio = persona.getDomicilioPostal();
			}

			this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarParcela_action() {
		return navegarParaSeleccionar("AdminParcela");
	}

	public String btnLimpiarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(3, this.getTfParcela());
			this.getObjectListDataProvider2().setList(null);
			this.setListaDelCommunication(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarDomicilioParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 4;

		if(ultimo) {

			// APLICAR LOGICA AQUI..
			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Parcela parcela = this.obtenerObjetoDelElementoPila(3, Parcela.class);
			if(parcela.getIdParcela() != -1) {
				Domicilio domicilio = parcela.getDomicilioParcelario();
				this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
			} else {
				warn("Seleccione una Parcela para obtener el Domicilio Parcelario.");
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnConsultarParcela_action() {
		String retorno = null;
		Parcela parcela = null;

		this.guardarEstadoObjetosUsados();

		parcela = (Parcela) this.obtenerObjetoDelElementoPila(3);

		if(parcela != null && parcela.getIdParcela() != -1) {

			try {
				this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
				parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(parcela.getIdParcela());
			} catch(Exception e) {
				e.printStackTrace();
			}

			this.getRequestBean1().setObjetoABM(parcela);
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new ParcelaModel().new ConsultarParcelaController());
			retorno = "ABMParcela";

		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDocEspTGI";
	}

	public void btnFiltrarLogLiq_action() {
		this.guardarEstadoObjetosUsados();
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				FiltroLogLiquidacion locFiltro = (FiltroLogLiquidacion) this.obtenerObjetoDelElementoPila(6);

				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				List<LogLiquidacion> listaLogs = new ArrayList<LogLiquidacion>();
				listaLogs = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getListaLogLiquidacion(locFiltro);

				this.getCommunicationSAICBean().setListaLogLiquidacion(listaLogs);
				this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void btnLimpiarFiltradoLogLiq_action() {
		this.getElementoPila().getObjetos().set(6, null);

		this.getTfFiltrarUsuarioLogLiq().setText("");
		this.getDdFiltrarEventoLogLiq().setSelected("");
		this.getTfFiltrarFechaDesdeLogLiq().setText("");
		this.getTfFiltrarFechaHastaLogLiq().setText("");

		this.getCommunicationSAICBean().setListaLogLiquidacion(new ArrayList<LogLiquidacion>());
		this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		System.out.println("---------------------> PROCESAR EL OBJETO SELECCIONADO GUACHEN");
		if(pObject instanceof Parcela) {
			Parcela locParcela = (Parcela) pObject;
			try {
				this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
				locParcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(locParcela.getIdParcela());
				System.out.println("--> tiro el get por id");
				DocumentoTGI documentoTGI = this.obtenerObjetoDelElementoPila(1, DocumentoTGI.class);
				documentoTGI.setParcela(locParcela);

				List listaPropietarios = new ArrayList();
				for(RegistroPropietario rp : locParcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
					listaPropietarios.add(rp.getPersona());
				}
				this.getObjectListDataProvider2().setList(listaPropietarios);
				this.setListaDelCommunication(listaPropietarios);
				PersonaFisica locPersona = (PersonaFisica) listaPropietarios.get(0);
				this.getRadioButton1().setSelected(locPersona);

				this.getElementoPila().getObjetos().set(1, documentoTGI);
				this.getElementoPila().getObjetos().set(2, locPersona);
				this.getElementoPila().getObjetos().set(3, locParcela);
			} catch(RemoteException e) {
				e.printStackTrace();
			} catch(TrascenderException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		int ind = 0;
		Obligacion obligacion = null;
		DocumentoTGI documentoTGI = null;
		Persona persona = null;
		Parcela parcela = null;
		Domicilio domicilio = null;
		ArrayList listaPropietarios = new ArrayList();
		ArrayList atributosDinamicos = null;

		if(pObject instanceof PlantillaObligacion) {
			PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().generarArbol(plantillaObligacion);
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_GenerarObligacion:", ex);
				error(NOMBRE_PAGINA + " - No se pudo generar la Obligaci\363n: " + ex.getMessage());
			}

			documentoTGI = (DocumentoTGI) obligacion.getDocumentoEspecializado();
			persona = this.obtenerObjetoDelElementoPila(2, Persona.class);

			this.setListaDelCommunicationDocsGeneradoresDeuda(new ArrayList());

			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoTGI);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, parcela);
			this.getElementoPila().getObjetos().set(ind++, domicilio);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
		} else if(this.getRequestBean1().getObjetoABM() instanceof Obligacion) {
			obligacion = (Obligacion) this.getRequestBean1().getObjetoABM();
			long id = obligacion.getIdObligacion();
			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(id);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			documentoTGI = (DocumentoTGI) obligacion.getDocumentoEspecializado();
			try {
				documentoTGI = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTGI().getDocumentoTGI(obligacion);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			persona = documentoTGI.getObligacion().getPersona();

			try {
				this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
				parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(documentoTGI.getParcela().getIdParcela());
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_getParcelaPorId:", ex);
				error(NOMBRE_PAGINA + " - No se pudo obtener la Parcela asociada: " + ex.getMessage());
				ex.printStackTrace();
			}

			domicilio = documentoTGI.getDomicilio();

			if(documentoTGI.getListaAtributosDinamicos() != null) {
				try {
					atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
							.getAtributosPorRecurso(DocumentoTGI.serialVersionUID, documentoTGI.getListaAtributosDinamicos(), null);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			for(RegistroPropietario rp : parcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
				listaPropietarios.add(rp.getPersona());
			}

			this.getObjectListDataProvider2().setList(listaPropietarios);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunication(listaPropietarios);

			try {
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				this.setListaDelCommunicationDocsGeneradoresDeuda(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaDocsGeneradoresDeuda(obligacion));
			} catch(Exception e) {
				e.printStackTrace();
			}

			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoTGI);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, parcela);
			this.getElementoPila().getObjetos().set(ind++, domicilio);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
		}
	}

	private void setListaDelCommunicationDocsGeneradoresDeuda(List<Tasa> pLista) {
		this.getCommunicationHabilitacionesBean().setListaDocsGeneradoresDeuda(pLista);
	}

	private List<Tasa> getListaDelCommunicationDocsGeneradoresDeuda() {
		return this.getCommunicationHabilitacionesBean().getListaDocsGeneradoresDeuda();
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		DocumentoTGI locDocumento = this.obtenerObjetoDelElementoPila(1, DocumentoTGI.class);
		this.getTablaLogs().getLdpLogs().setList(locDocumento.getListaLogsAuditoria());

		if(this.getListaDelCommunicationDocsGeneradoresDeuda() != null) {
			this.getLdpDocsGeneradoresDeuda().setList(this.getListaDelCommunicationDocsGeneradoresDeuda());
		}
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoTGI.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpTGI$ABMDocEspTGI$ABMDocEspTGI}";
	}

	public void setParcelaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Parcela locParcela = null;

		try {
			locParcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		List listaPropietarios = new ArrayList();
		for(RegistroPropietario rp : locParcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
			listaPropietarios.add(rp.getPersona());
		}
		this.getObjectListDataProvider2().setList(listaPropietarios);
		this.setListaDelCommunication(listaPropietarios);
		PersonaFisica locPersona = (PersonaFisica) listaPropietarios.get(0);
		this.getRadioButton1().setSelected(locPersona);

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(2, locPersona);
		this.getElementoPila().getObjetos().set(3, locParcela);
	}

	public boolean isHayParcela() {
		Parcela locParcela = (Parcela) this.obtenerObjetoDelElementoPila(3);
		return(locParcela != null && locParcela.getIdParcela() != -1);
	}
}
