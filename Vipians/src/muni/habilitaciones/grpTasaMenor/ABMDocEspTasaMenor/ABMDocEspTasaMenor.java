/*
 * AgregarDocEspTasaMenor.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpTasaMenor.ABMDocEspTasaMenor;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

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
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.LogLiquidacion;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMDocEspTasaMenor extends ABMPageBean {

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Agregar Obligaci\363n: Tasa Menor";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AgregarDocEspTasaMenor";

	// nombre del caso de navegacion para llegar a la pagina de caducidad

	private Table tablaLogsLiquidacion = new Table();
	private TableRowGroup tableRowGroup = new TableRowGroup();
	private ObjectListDataProvider ldpLogsLiquidacion = new ObjectListDataProvider();

	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();
	private TableColumn tableColumn8 = new TableColumn();
	private TableColumn tableColumn9 = new TableColumn();

	private StaticText staticText4 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText8 = new StaticText();
	private StaticText staticText9 = new StaticText();

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

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tableColumn9) {
		this.tableColumn9 = tableColumn9;
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

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
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

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication());
		}
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

	private StaticText stPersona = new StaticText();

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText st) {
		this.stPersona = st;
	}

	private Label label16 = new Label();

	public Label getLabel16() {
		return label16;
	}

	public void setLabel16(Label l) {
		this.label16 = l;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private ObjectListDataProvider ldpDocEspOSP = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDocEspOSP() {
		return ldpDocEspOSP;
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	private ObjectListDataProvider ldpLogModificacionesSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLogModificacionesSHPS() {
		return ldpLogModificacionesSHPS;
	}

	public void setLdpLogModificacionesSHPS(ObjectListDataProvider oldp) {
		this.ldpLogModificacionesSHPS = oldp;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	private Button btnSeleccionarDomicilioPostal = new Button();

	public Button getBtnSeleccionarDomicilioPostal() {
		return btnSeleccionarDomicilioPostal;
	}

	public void setBtnSeleccionarDomicilioPostal(Button b) {
		this.btnSeleccionarDomicilioPostal = b;
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

	private Label lblPersona = new Label();

	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
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

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TextField tfFechaCese = new TextField();

	public TextField getTfFechaCese() {
		return tfFechaCese;
	}

	public void setTfFechaCese(TextField tf) {
		this.tfFechaCese = tf;
	}

	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	private HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(HtmlAjaxCommandButton btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

	private Button btnSeleccionarPersona = new Button();

	public Button getBtnSeleccionarPersona() {
		return btnSeleccionarPersona;
	}

	public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
		this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersona(Button btnSeleccionarPersona) {
		this.btnSeleccionarPersona = btnSeleccionarPersona;
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

	private PanelGroup pgPersona = new PanelGroup();
	private PanelGroup pgParcela = new PanelGroup();
	private PanelGroup pgPersonaSolicitante = new PanelGroup();

	public PanelGroup getPgPersona() {
		return pgPersona;
	}

	public void setPgPersona(PanelGroup pgPersona) {
		this.pgPersona = pgPersona;
	}

	public PanelGroup getPgParcela() {
		return pgParcela;
	}

	public void setPgParcela(PanelGroup pgParcela) {
		this.pgParcela = pgParcela;
	}

	public PanelGroup getPgPersonaSolicitante() {
		return pgPersonaSolicitante;
	}

	public void setPgPersonaSolicitante(PanelGroup pgPersonaSolicitante) {
		this.pgPersonaSolicitante = pgPersonaSolicitante;
	}

	public ABMDocEspTasaMenor() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, null); // Obligacion
		ep.getObjetos().add(ind++, new DocumentoTasaMenor());
		ep.getObjetos().add(ind++, null); // Persona solicitante
		ep.getObjetos().add(ind++, new Parcela());
		ep.getObjetos().add(ind++, new Domicilio());
		ep.getObjetos().add(ind++, new ArrayList());// AtributosDinamicos
		ep.getObjetos().add(ind++, null); // lista de propietarios de la parcela
		ep.getObjetos().add(ind++, null); // 7 FiltroLogLiquidacion

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
		Obligacion obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
		DocumentoTasaMenor documentoTasaMenor = (DocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, DocumentoTasaMenor.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		ArrayList listaPropietarios = new ArrayList();
		List atributosDinamicos = (List) this.obtenerObjetoDelElementoPila(ind++, List.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(7, FiltroLogLiquidacion.class);

		Object fechaInicio = this.getTfFechaInicio().getText();
		Object fechaCese = this.getTfFechaCese().getText();

		if(fechaInicio != null && fechaInicio != "") {
			documentoTasaMenor.setFechaInicioActividad(Conversor.getFechaCortaDeString(fechaInicio.toString()));
		} else {
			documentoTasaMenor.setFechaInicioActividad(null);
		}
		if(fechaCese != null && fechaCese != "") {
			documentoTasaMenor.setFechaCeseActividad(Conversor.getFechaCortaDeString(fechaCese.toString()));
		} else {
			documentoTasaMenor.setFechaCeseActividad(null);
		}

		if(parcela.getIdParcela() == -1) {
			parcela = null;
		} else {
			for(RegistroPropietario rp : parcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
				listaPropietarios.add(rp.getPersona());
			}
			this.getObjectListDataProvider2().setList(listaPropietarios);
			this.setListaDelCommunication(listaPropietarios);

			documentoTasaMenor.setParcela(parcela);
		}

		if(domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		documentoTasaMenor.setDomicilio(domicilio);
		// ////////
		RowKey rk = null;
		try {
			rk = this.getSeleccionado();
			if(rk != null) {
				int index = getNroFila(rk.toString());
				Object obj = this.getObjectListDataProvider2().getObjects()[index];
				Persona locPersona = (Persona) obj;

				persona = locPersona;
				// domicilio = (Domicilio)locRegistroPropietario.getPersona().getDomicilioPostal();

			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		atributosDinamicos = (List) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);

		this.getObjectListDataProvider2().setList(listaPropietarios);
		this.getLdpPropietarios().setObjectType(Persona.class);
		this.setListaDelCommunication(listaPropietarios);

		obligacion.setDocumentoEspecializado(documentoTasaMenor);

		filtroLogLiq.setObligacion(obligacion);
		filtroLogLiq.setUsuario(getTextFieldValue(this.getTfFiltrarUsuarioLogLiq()));
		filtroLogLiq.setEvento(getDDEnumValue(this.getDdFiltrarEventoLogLiq(), LogLiquidacion.Evento.class));
		filtroLogLiq.setFechaDesde(getTextFieldValueDate(this.getTfFiltrarFechaDesdeLogLiq()));
		filtroLogLiq.setFechaHasta(getTextFieldValueDate(this.getTfFiltrarFechaHastaLogLiq()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, obligacion);
		this.getElementoPila().getObjetos().set(ind++, documentoTasaMenor);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, parcela);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		// this.getElementoPila().getObjetos().set(ind++, listaPropietarios);
		this.getElementoPila().getObjetos().set(5, atributosDinamicos);
		this.getElementoPila().getObjetos().set(7, filtroLogLiq);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Obligacion obligacion = null;
		DocumentoTasaMenor documentoTasaMenor = null;
		Persona persona = null;
		Parcela parcela = null;
		Domicilio domicilio = null;
		ArrayList listaPropietarios = new ArrayList();
		List atributosDinamicos = null;

		/*
		 * if (this.getRequestBean1().getObjetoSeleccion() != null) { Object seleccionado = this.getRequestBean1().getObjetoSeleccion(); int posicion =
		 * ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
		 * 
		 * // Logica especial de DocEspSHPS para mostrar el solicitante en el Agregar /* if ((seleccionado instanceof Persona)) { persona = (Persona)
		 * seleccionado; domicilio = persona.getDomicilio(); this.getElementoPila().getObjetos().set(2, persona); this.getElementoPila().getObjetos().set(4,
		 * domicilio); this.getRequestBean1().setObjetoSeleccion(null); } }
		 */

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			// int posicionEP = -1;
			if(respuesta instanceof Domicilio) {
				int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
				this.getElementoPila().getObjetos().set(posicion, respuesta);
			}
		}

		try {
			DocumentoTasaMenor locDocumento = (DocumentoTasaMenor) this.obtenerObjetoDelElementoPila(1);
			// this.getElementoPila().getObjetos().set(5, this.getComunicationBean().getRemoteSystemParametro()
			// .getAtributosPorRecurso(DocumentoTasaMenor.serialVersionUID, locDocumento.getListaAtributosDinamicos(), null));
		} catch(Exception e) {
			e.printStackTrace();
		}

		ind = 0;
		ind++;
		documentoTasaMenor = (DocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, DocumentoTasaMenor.class);
		persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		atributosDinamicos = (List) this.obtenerObjetoDelElementoPila(5, List.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(7, FiltroLogLiquidacion.class);

		if(documentoTasaMenor.getFechaInicioActividad() != null) {
			this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoTasaMenor.getFechaInicioActividad()));
		}
		if(documentoTasaMenor.getFechaCeseActividad() != null) {
			this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoTasaMenor.getFechaCeseActividad()));
		}
		if(persona != null) {
			this.getTfPersona().setText(persona.toString());
			this.deshabilitarOpcionesParcela(true);
			this.deshabilitarOpcionesPersona(false);
			this.seleccionarPersonaObligacion(persona);
		}

		this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
		if(parcela != null && parcela.getIdParcela() != -1) {
			this.getTfParcela().setText(parcela.toString());
			for(RegistroPropietario rp : parcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
				listaPropietarios.add(rp.getPersona());
			}
			this.getObjectListDataProvider2().setList(listaPropietarios);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunication(listaPropietarios);
			// this.deshabilitarOpcionesPersona(true);
			this.deshabilitarOpcionesParcela(false);
		} else {
			this.getObjectListDataProvider2().setList(null);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunication(null);
		}

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.setListaDelCommunicationAtributosDinamicos((ArrayList) atributosDinamicos);

		this.getTfFiltrarUsuarioLogLiq().setText(filtroLogLiq.getUsuario());
		this.getDdFiltrarEventoLogLiq().setSelected(filtroLogLiq.getEvento());
		this.getTfFiltrarFechaDesdeLogLiq().setText(filtroLogLiq.getFechaDesde());
		this.getTfFiltrarFechaHastaLogLiq().setText(filtroLogLiq.getFechaHasta());
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

	private ArrayList getListaDelCommunicationAtributosDinamicos() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaAtributosDinamicosTasaMenor();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosTasaMenor(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider2() {

		return this.getLdpPropietarios();
	}

	private List getListaDelCommunication() {

		return this.getCommunicationHabilitacionesBean().getListaPropietariosTasaMenor();
	}

	private void setListaDelCommunication(List lista) {

		this.getCommunicationHabilitacionesBean().setListaPropietariosTasaMenor(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpDocEspOSP();
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

	public String btnSeleccionarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 3;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminParcela";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.deshabilitarOpcionesPersona(false);
			this.limpiarObjeto(3, this.getTfParcela());
			this.getObjectListDataProvider2().setList(null);
			this.setListaDelCommunication(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 2;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarPersonaJuridica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 2;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminPersonaJuridica";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.deshabilitarOpcionesParcela(false);
			this.getTfPersona().setText(null);
			this.getElementoPila().getObjetos().set(2, null);
			this.getElementoPila().getObjetos().set(4, null);
			this.getStDomicilioPostal().setText(null);
			this.getObjectListDataProvider2().setList(null);
			this.setListaDelCommunication(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarDomicilioPostal_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 4;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AgregarDomicilio";

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
			this.getElementoPila().getObjetos().set(4, null);
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
		Persona persona = null;
		persona = (Persona) this.obtenerObjetoDelElementoPila(2, Persona.class);

		if(ultimo) {
			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider2().getObjects()[index];
					Persona locPersona = (Persona) obj;
					Domicilio domicilio = (Domicilio) locPersona.getDomicilioPostal();
					this.getElementoPila().getObjetos().set(4, domicilio);
				} else if(persona != null) {
					Domicilio domicilio = persona.getDomicilioPostal();
					this.getElementoPila().getObjetos().set(4, domicilio);
				} else {
					warn("Seleccione una Persona para obtener el Domicilio Postal.");
				}

			} catch(Exception ex) {
				ex.printStackTrace();

			}

			// this.guardarEstadoObjetosUsados();
			// this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarDomicilioParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 4;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(3, Parcela.class);
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

	// si recibe true se deshabilita todo lo relacionado con Parcela, con false es el caso contrario
	public void deshabilitarOpcionesParcela(boolean deshabilitar) {
		if(deshabilitar == true) {
			this.tfParcela.setStyleClass("textFieldDisabled");
			this.btnSeleccionarParcela.setDisabled(false);
			this.btnLimpiarParcela.setDisabled(false);
			this.btnSeleccionarDomicilioPostal.setDisabled(false);
			this.btnLimpiarDomicilioPostal.setDisabled(false);
			this.btnSeleccionarDomicilioParcela.setDisabled(true);
			this.btnSeleccionarDomicilioSolicitante.setDisabled(false);
		} else {
			this.tfParcela.setStyleClass("textField");
			this.btnSeleccionarParcela.setDisabled(false);
			this.btnLimpiarParcela.setDisabled(false);
			this.btnSeleccionarDomicilioPostal.setDisabled(false);
			this.btnLimpiarDomicilioPostal.setDisabled(false);
			this.btnSeleccionarDomicilioParcela.setDisabled(false);
			this.btnSeleccionarDomicilioSolicitante.setDisabled(false);
		}
	}

	// si recibe true se deshabilita todo lo relacionado con Persona, con false es el caso contrario
	public void deshabilitarOpcionesPersona(boolean deshabilitar) {
		if(deshabilitar == true) {
			this.tfPersona.setStyleClass("textFieldDisabled");
			this.btnSeleccionarPersona.setDisabled(true);
			this.btnLimpiarPersona.setDisabled(true);
		} else {
			this.tfPersona.setStyleClass("textField");
			this.btnSeleccionarPersona.setDisabled(false);
			this.btnLimpiarPersona.setDisabled(false);
		}
	}

	public void btnFiltrarLogLiq_action() {
		this.guardarEstadoObjetosUsados();
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				FiltroLogLiquidacion locFiltro = (FiltroLogLiquidacion) this.obtenerObjetoDelElementoPila(7);

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
		this.getElementoPila().getObjetos().set(7, null);

		this.getTfFiltrarUsuarioLogLiq().setText("");
		this.getDdFiltrarEventoLogLiq().setSelected("");
		this.getTfFiltrarFechaDesdeLogLiq().setText("");
		this.getTfFiltrarFechaHastaLogLiq().setText("");

		this.getCommunicationSAICBean().setListaLogLiquidacion(new ArrayList<LogLiquidacion>());
		this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

		if((pObject instanceof Parcela)) {

			this.setRBSelected((new Long(0)).toString());
			Parcela parcela = (Parcela) pObject;
			// this.deshabilitarOpcionesPersona(true);
			this.deshabilitarOpcionesParcela(false);

			try {
				this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
				parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(parcela.getIdParcela());
			} catch(Exception e) {
				e.printStackTrace();
			}

			if(parcela.getTituloPropiedad() != null) {
				// domicilio= (Domicilio) parcela.getDomicilioParcelario();
				this.getElementoPila().getObjetos().set(3, parcela);
			} else {
				this.getRequestBean1().setObjetoSeleccion(null);
				this.getElementoPila().getObjetos().set(3, null);
				this.getElementoPila().getObjetos().set(4, null);
				this.getObjectListDataProvider2().setList(null);
				this.setListaDelCommunication(null);
				warn("La parcela seleccionada no posee Titulo de Propiedad.");
			}
		}
		if((pObject instanceof Persona)) {
			Persona persona = (Persona) pObject;
			try {
				if(persona instanceof PersonaFisica) {
					this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
					persona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(persona.getIdPersona());
				} else {
					this.getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(this.getSessionBean1().getLlave());
					persona = this.getComunicationBean().getRemoteSystemPersonaJuridica().getPersonaJuridicaPorId(persona.getIdPersona());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}

			this.deshabilitarOpcionesParcela(true);
			this.deshabilitarOpcionesPersona(false);
			this.getElementoPila().getObjetos().set(2, persona);
			Domicilio domicilio = persona.getDomicilioPostal();
			this.getElementoPila().getObjetos().set(4, domicilio);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		int ind = 0;
		Obligacion obligacion = null;
		DocumentoTasaMenor documentoTasaMenor = null;
		Persona persona = null;
		Parcela parcela = null;
		Domicilio domicilio = null;
		List listaPropietarios = new ArrayList();
		List atributosDinamicos = null;

		if(pObject instanceof PlantillaObligacion) {
			PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().generarArbol(plantillaObligacion);
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_GenerarObligacion:", ex);
				error(NOMBRE_PAGINA + " - No se pudo generar la Obligaci\363n: " + ex.getMessage());
			}

			documentoTasaMenor = (DocumentoTasaMenor) obligacion.getDocumentoEspecializado();
			persona = this.obtenerObjetoDelElementoPila(2, Persona.class);
			atributosDinamicos = documentoTasaMenor.generarAtributosDinamicos();

			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoTasaMenor);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, parcela);
			this.getElementoPila().getObjetos().set(ind++, domicilio);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
		} else if(pObject instanceof Obligacion) {
			obligacion = (Obligacion) pObject;
			long id = obligacion.getIdObligacion();
			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(id);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			documentoTasaMenor = (DocumentoTasaMenor) obligacion.getDocumentoEspecializado();
			try {
				documentoTasaMenor = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().getDocumentoTasaMenor(obligacion);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			persona = documentoTasaMenor.getObligacion().getPersona();

			try {
				this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
				parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(documentoTasaMenor.getParcela().getIdParcela());
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_getParcelaPorId:", ex);
				error(NOMBRE_PAGINA + " - No se pudo obtener la Parcela asociada: " + ex.getMessage());
				ex.printStackTrace();
			}

			domicilio = documentoTasaMenor.getDomicilio();

			atributosDinamicos = documentoTasaMenor.generarAtributosDinamicos();
			for(RegistroPropietario rp : parcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
				listaPropietarios.add(rp.getPersona());
			}

			this.getObjectListDataProvider2().setList(listaPropietarios);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunication(listaPropietarios);

			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoTasaMenor);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, parcela);
			this.getElementoPila().getObjetos().set(ind++, domicilio);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
			this.getElementoPila().getObjetos().set(ind++, listaPropietarios);
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDocEspTasaMenor";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		DocumentoTasaMenor locDoc = this.obtenerObjetoDelElementoPila(1, DocumentoTasaMenor.class);
		this.getTablaLogs().getLdpLogs().setList(locDoc.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoTasaMenor.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor}";
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

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(3, locParcela);
	}

	public boolean isHayParcela() {
		Parcela locParcela = (Parcela) this.obtenerObjetoDelElementoPila(3);
		return(locParcela != null && locParcela.getIdParcela() != -1);
	}
}
