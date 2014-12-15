/*
 * AgregarDocEspOSP.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpOSP.ABMDocEspOSP;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import muni.catastro.ABMParcela.ParcelaModel;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
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
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.filtros.FiltroConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.AsocServicioOsp;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
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
public class ABMDocEspOSP extends ABMPageBean {

	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Agregar Obligaci\363n: OSP";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AgregarDocEspOSP";

	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */

	@Override
	protected void _init() {
		if(this.getListaDelCommunication2() != null) {
			this.getObjectListDataProvider3().setList(this.getListaDelCommunication2());
		}
		if(this.getListaDelCommunication5() != null) {
			this.getObjectListDataProvider5().setList(this.getListaDelCommunication5());
		}
		getTablaDocsGeneradoresDeuda().setClearSortButton(true);

		if(this.getCommunicationSAICBean().getListaLogLiquidacion() != null) {
			this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
		}

		Option[] opTipoEvento = null;
		opTipoEvento = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(LogLiquidacion.Evento.values(), "cap");
		ddFiltrarEventoLogLiqOptions.setOptions(opTipoEvento);

		dateTimeConverter2.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		dateTimeConverter2.setPattern("dd/MM/yyyy HH:mm");
		dateTimeConverter2.setTimeStyle("full");
		numberConverter2.setPattern("$ #,##0.00");
	}

	private TableColumn tcCodigoMedidor = new TableColumn();
	private TextField tfCodigoMedidorTabla = new TextField();

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

	private TableColumn tableColumn20 = new TableColumn();
	private TableColumn tableColumn21 = new TableColumn();
	private TableColumn tableColumn22 = new TableColumn();
	private TableColumn tableColumn23 = new TableColumn();
	private TableColumn tableColumn24 = new TableColumn();
	private TableColumn tableColumn25 = new TableColumn();

	private StaticText staticText20 = new StaticText();
	private StaticText staticText21 = new StaticText();
	private StaticText staticText22 = new StaticText();
	private StaticText staticText23 = new StaticText();
	private StaticText staticText24 = new StaticText();
	private StaticText staticText25 = new StaticText();

	private DateTimeConverter dateTimeConverter2 = new DateTimeConverter();
	private NumberConverter numberConverter2 = new NumberConverter();

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

	public TableColumn getTableColumn20() {
		return tableColumn20;
	}

	public void setTableColumn20(TableColumn tableColumn20) {
		this.tableColumn20 = tableColumn20;
	}

	public TableColumn getTableColumn21() {
		return tableColumn21;
	}

	public void setTableColumn21(TableColumn tableColumn21) {
		this.tableColumn21 = tableColumn21;
	}

	public TableColumn getTableColumn22() {
		return tableColumn22;
	}

	public void setTableColumn22(TableColumn tableColumn22) {
		this.tableColumn22 = tableColumn22;
	}

	public TableColumn getTableColumn23() {
		return tableColumn23;
	}

	public void setTableColumn23(TableColumn tableColumn23) {
		this.tableColumn23 = tableColumn23;
	}

	public TableColumn getTableColumn24() {
		return tableColumn24;
	}

	public void setTableColumn24(TableColumn tableColumn24) {
		this.tableColumn24 = tableColumn24;
	}

	public TableColumn getTableColumn25() {
		return tableColumn25;
	}

	public void setTableColumn25(TableColumn tableColumn25) {
		this.tableColumn25 = tableColumn25;
	}

	public StaticText getStaticText20() {
		return staticText20;
	}

	public void setStaticText20(StaticText staticText20) {
		this.staticText20 = staticText20;
	}

	public StaticText getStaticText21() {
		return staticText21;
	}

	public void setStaticText21(StaticText staticText21) {
		this.staticText21 = staticText21;
	}

	public StaticText getStaticText22() {
		return staticText22;
	}

	public void setStaticText22(StaticText staticText22) {
		this.staticText22 = staticText22;
	}

	public StaticText getStaticText23() {
		return staticText23;
	}

	public void setStaticText23(StaticText staticText23) {
		this.staticText23 = staticText23;
	}

	public StaticText getStaticText24() {
		return staticText24;
	}

	public void setStaticText24(StaticText staticText24) {
		this.staticText24 = staticText24;
	}

	public StaticText getStaticText25() {
		return staticText25;
	}

	public void setStaticText25(StaticText staticText25) {
		this.staticText25 = staticText25;
	}

	public DateTimeConverter getDateTimeConverter2() {
		return dateTimeConverter2;
	}

	public void setDateTimeConverter2(DateTimeConverter dateTimeConverter2) {
		this.dateTimeConverter2 = dateTimeConverter2;
	}

	public NumberConverter getNumberConverter2() {
		return numberConverter2;
	}

	public void setNumberConverter2(NumberConverter numberConverter2) {
		this.numberConverter2 = numberConverter2;
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

	public TableColumn getTcCodigoMedidor() {
		return tcCodigoMedidor;
	}

	public void setTcCodigoMedidor(TableColumn tcCodigoMedidor) {
		this.tcCodigoMedidor = tcCodigoMedidor;
	}

	public TextField getTfCodigoMedidorTabla() {
		return tfCodigoMedidorTabla;
	}

	public void setTfCodigoMedidorTabla(TextField tfCodigoMedidorTabla) {
		this.tfCodigoMedidorTabla = tfCodigoMedidorTabla;
	}

	private Button btnConsultarParcela = new Button();

	public Button getBtnConsultarParcela() {
		return btnConsultarParcela;
	}

	public void setBtnConsultarParcela(Button btnConsultarParcela) {
		this.btnConsultarParcela = btnConsultarParcela;
	}

	private Label label16 = new Label();
	private Label lblBaseConsumo = new Label();

	public Label getLabel16() {
		return label16;
	}

	public void setLabel16(Label l) {
		this.label16 = l;
	}

	private TextField tfPersona = new TextField();
	private TextField tfBaseConsumo = new TextField();

	public Label getLblBaseConsumo() {
		return lblBaseConsumo;
	}

	public void setLblBaseConsumo(Label lblConsumoBasico) {
		this.lblBaseConsumo = lblConsumoBasico;
	}

	public TextField getTfBaseConsumo() {
		return tfBaseConsumo;
	}

	public void setTfBaseConsumo(TextField tfBaseConsumo) {
		this.tfBaseConsumo = tfBaseConsumo;
	}

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

	private Button btnSeleccionarDomicilioPostal = new Button();

	public Button getBtnSeleccionarDomicilioPostal() {
		return btnSeleccionarDomicilioPostal;
	}

	public void setBtnSeleccionarDomicilioPostal(Button b) {
		this.btnSeleccionarDomicilioPostal = b;
	}

	private final ObjectListDataProvider ldpDocEspOSP = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDocEspOSP() {
		return ldpDocEspOSP;
	}

	private HtmlAjaxCommandButton btnLimpiarDomicilioPostal = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarDomicilioPostal() {
		return btnLimpiarDomicilioPostal;
	}

	public void setBtnLimpiarDomicilioPostal(HtmlAjaxCommandButton btnLimpiarDomicilioPostal) {
		this.btnLimpiarDomicilioPostal = btnLimpiarDomicilioPostal;
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
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

	private TextField tfFechaInicio = new TextField();

	public TextField getTfFechaInicio() {
		return tfFechaInicio;
	}

	public void setTfFechaInicio(TextField tf) {
		this.tfFechaInicio = tf;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfFechaCese = new TextField();

	public TextField getTfFechaCese() {
		return tfFechaCese;
	}

	public void setTfFechaCese(TextField tf) {
		this.tfFechaCese = tf;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText st) {
		this.staticText12 = st;
	}

	private Checkbox checkbox1 = new Checkbox();

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox c) {
		this.checkbox1 = c;
	}

	private NumberConverter numberConverter1 = new NumberConverter();

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	private StaticText staticText13 = new StaticText();
	private StaticText staticText14 = new StaticText();
	private StaticText staticText15 = new StaticText();
	private StaticText staticText16 = new StaticText();
	private StaticText staticText17 = new StaticText();
	private StaticText staticText18 = new StaticText();

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText staticText14) {
		this.staticText14 = staticText14;
	}

	public StaticText getStaticText15() {
		return staticText15;
	}

	public void setStaticText15(StaticText staticText15) {
		this.staticText15 = staticText15;
	}

	public StaticText getStaticText16() {
		return staticText16;
	}

	public void setStaticText16(StaticText staticText16) {
		this.staticText16 = staticText16;
	}

	public StaticText getStaticText17() {
		return staticText17;
	}

	public void setStaticText17(StaticText staticText17) {
		this.staticText17 = staticText17;
	}

	public StaticText getStaticText18() {
		return staticText18;
	}

	public void setStaticText18(StaticText staticText18) {
		this.staticText18 = staticText18;
	}

	private StaticText stDomicilioPostal = new StaticText();

	public StaticText getStDomicilioPostal() {
		return stDomicilioPostal;
	}

	public void setStDomicilioPostal(StaticText st) {
		this.stDomicilioPostal = st;
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

	private Label lblParcela1 = new Label();

	public Label getLblParcela1() {
		return lblParcela1;
	}

	public void setLblParcela1(Label l) {
		this.lblParcela1 = l;
	}

	private Label parcela2 = new Label();

	public Label getParcela2() {
		return parcela2;
	}

	public void setParcela2(Label parcela2) {
		this.parcela2 = parcela2;
	}

	private Button btnSeleccionarParcela = new Button();
	private Button btnSeleccionarSubparcela = new Button();

	public Button getBtnSeleccionarSubparcela() {
		return btnSeleccionarSubparcela;
	}

	public void setBtnSeleccionarSubparcela(Button btnSeleccionarSubparcela) {
		this.btnSeleccionarSubparcela = btnSeleccionarSubparcela;
	}

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button b) {
		this.btnSeleccionarParcela = b;
	}

	private HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(HtmlAjaxCommandButton btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

	private TextField tfParcela = new TextField();

	public TextField getTfParcela() {
		return tfParcela;
	}

	public void setTfParcela(TextField tf) {
		this.tfParcela = tf;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label label7) {
		this.label7 = label7;
	}

	private TextField tfServicioOSP = new TextField();

	public TextField getTfServicioOSP() {
		return tfServicioOSP;
	}

	public void setTfServicioOSP(TextField tf) {
		this.tfServicioOSP = tf;
	}

	private Button btnSeleccionarServicio = new Button();

	public Button getBtnSeleccionarServicio() {
		return btnSeleccionarServicio;
	}

	public void setBtnSeleccionarServicio(Button b) {
		this.btnSeleccionarServicio = b;
	}

	private Button btnLimpiarServicio = new Button();

	public Button getBtnLimpiarServicio() {
		return btnLimpiarServicio;
	}

	public void setBtnLimpiarServicio(Button b) {
		this.btnLimpiarServicio = b;
	}

	private Label lblCodigoMedidor = new Label();

	public Label getLblCodigoMedidor() {
		return lblCodigoMedidor;
	}

	public void setLblCodigoMedidor(Label l) {
		this.lblCodigoMedidor = l;
	}

	private TextField tfCodigoMedidor = new TextField();

	public TextField getTfCodigoMedidor() {
		return tfCodigoMedidor;
	}

	public void setTfCodigoMedidor(TextField tf) {
		this.tfCodigoMedidor = tf;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextArea taNombre = new TextArea();

	public TextArea getTaNombre() {
		return taNombre;
	}

	public void setTaNombre(TextArea ta) {
		this.taNombre = ta;
	}

	private TextField tfNumeroCuenta = new TextField();

	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tf) {
		this.tfNumeroCuenta = tf;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TextField tfNumeroSubCuenta = new TextField();

	public TextField getTfNumeroSubCuenta() {
		return tfNumeroSubCuenta;
	}

	public void setTfNumeroSubCuenta(TextField tf) {
		this.tfNumeroSubCuenta = tf;
	}

	private Button btnAgregarServicio = new Button();
	protected HtmlAjaxCommandButton btnQuitarServicio = new HtmlAjaxCommandButton();

	public Button getBtnAgregarServicio() {
		return btnAgregarServicio;
	}

	public void setBtnAgregarServicio(Button btnAgregarServicio) {
		this.btnAgregarServicio = btnAgregarServicio;
	}

	public HtmlAjaxCommandButton getBtnQuitarServicio() {
		return btnQuitarServicio;
	}

	public void setBtnQuitarServicio(HtmlAjaxCommandButton btnQuitarServicio) {
		this.btnQuitarServicio = btnQuitarServicio;
	}

	private PanelGroup groupPanel1 = new PanelGroup();
	private PanelGroup groupPanel2 = new PanelGroup();
	private PanelGroup groupPanel3 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup groupPanel3) {
		this.groupPanel3 = groupPanel3;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private TableRowGroup tableRowGroup2 = new TableRowGroup();

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private Button btnLimpiarPersona = new Button();

	public Button getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(Button b) {
		this.btnLimpiarPersona = b;
	}

	private Label lblCoeficienteZonal = new Label();

	public Label getLblCoeficienteZonal() {
		return lblCoeficienteZonal;
	}

	public void setLblCoeficienteZonal(Label l) {
		this.lblCoeficienteZonal = l;
	}

	private TextField tfCoeficienteZonal = new TextField();

	public TextField getTfCoeficienteZonal() {
		return tfCoeficienteZonal;
	}

	public void setTfCoeficienteZonal(TextField tf) {
		this.tfCoeficienteZonal = tf;
	}

	private ObjectListDataProvider ldpPropietariosParcela = new ObjectListDataProvider();

	private ObjectListDataProvider ldpServicioOSP = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpServicioOSP() {
		return ldpServicioOSP;
	}

	public void setLdpServicioOSP(ObjectListDataProvider ldpServicioOSP) {
		this.ldpServicioOSP = ldpServicioOSP;
	}

	public ObjectListDataProvider getLdpPropietariosParcela() {
		return ldpPropietariosParcela;
	}

	public void setLdpPropietariosParcela(ObjectListDataProvider oldp) {
		this.ldpPropietariosParcela = oldp;
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

	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();
	private TableColumn tableColumn8 = new TableColumn();
	private TableColumn tableColumn9 = new TableColumn();
	private TableColumn tableColumn10 = new TableColumn();

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

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tableColumn9) {
		this.tableColumn9 = tableColumn9;
	}

	public TableColumn getTableColumn10() {
		return tableColumn10;
	}

	public void setTableColumn10(TableColumn tableColumn10) {
		this.tableColumn10 = tableColumn10;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private RadioButton radioButton2 = new RadioButton();

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
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

	private Object lastSelected2 = null;

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	private StaticText stPersona = new StaticText();

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText stPersona) {
		this.stPersona = stPersona;
	}

	private TextField tfSubParcela = new TextField();

	public TextField getTfSubParcela() {
		return tfSubParcela;
	}

	public void setTfSubParcela(TextField tfSubParcela) {
		this.tfSubParcela = tfSubParcela;
	}

	private Button btnLimpiarSubParcela = new Button();

	public Button getBtnLimpiarSubParcela() {
		return btnLimpiarSubParcela;
	}

	public void setBtnLimpiarSubParcela(Button btnLimpiarSubParcela) {
		this.btnLimpiarSubParcela = btnLimpiarSubParcela;
	}

	private ObjectListDataProvider ldpPropietarios = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPropietarios() {
		return ldpPropietarios;
	}

	public void setLdpPropietarios(ObjectListDataProvider oldp) {
		this.ldpPropietarios = oldp;
	}

	private List getListaDelCommunicationLogs() {
		return this.getCommunicationHabilitacionesBean().getListaLogsAuditoriaDocEspOsp();
	}

	private void setListaDelCommunicationLogs(List lista) {
		this.getCommunicationHabilitacionesBean().setListaLogsAuditoriaDocEspOsp(lista);
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMDocEspOSP() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// ////////////////////////////////////////////////////////////////
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, null); // obligacion
		ep.getObjetos().add(ind++, new DocumentoOSP());
		ep.getObjetos().add(ind++, null); // Persona solicitante: persona
		ep.getObjetos().add(ind++, new Parcela()); // 3 parcela
		ep.getObjetos().add(ind++, new Domicilio());
		ep.getObjetos().add(ind++, null);
		ep.getObjetos().add(ind++, new PlantillaObligacion()); // plantilla para generar otra obligacion
		ep.getObjetos().add(ind++, new ArrayList());// AtributosDinamicos
		ep.getObjetos().add(ind++, null);// 8 subparcela
		ep.getObjetos().add(ind++, null); // 9 FiltroLogLiquidacion

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
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
		DocumentoOSP documentoOSP = this.obtenerObjetoDelElementoPila(ind++, DocumentoOSP.class);
		Persona persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		Parcela parcela = this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		Domicilio domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(9, FiltroLogLiquidacion.class);

		// Comentado
		// ServicioOSP servicio = (ServicioOSP)
		// this.obtenerObjetoDelElementoPila(
		// ind++, ServicioOSP.class);

		ind++;
		PlantillaObligacion plantillaObligacion = this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class);
		List atributosDinamicos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		SubParcela subparcela = this.obtenerObjetoDelElementoPila(ind++, SubParcela.class);

		Parcela parcelaSubparcela = new Parcela();
		List listaPropietarios = new ArrayList();

		List<AsocServicioOsp> listaAsocRegAlic = new ArrayList();

		if(SecurityMgr.getInstance().getMunicipalidad().isVariosServiciosOSP()) {

			if(this.getObjectListDataProvider5().getList() != null && this.getObjectListDataProvider5().getList().size() > 0) {
				this.getObjectListDataProvider5().commitChanges();
			}

			listaAsocRegAlic = this.getObjectListDataProvider5().getList();

		} else {

			if(documentoOSP.getListaRegAlicuotas() != null && documentoOSP.getListaRegAlicuotas().size() > 0) {

				List<AsocRegAlicuota> lista = documentoOSP.getListaAsocRegAlicuota();
				listaAsocRegAlic.add(((AsocServicioOsp) lista.get(0)));
				ServicioOSP locServicio = (ServicioOSP) listaAsocRegAlic.get(0).getRegistroAlicuota();
				if(locServicio != null && locServicio.isMedido()) {
					try {
						System.out.println("***** servicio isMedido");
						List locListaConsumosBasicos = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().getListaConsumosBasicos(new FiltroConsumoBasico())
								.getListaResultados();
						System.out.println("***** TAMAÑO LISTA CONSUMOS BASICOS: " + locListaConsumosBasicos.size());

						if(locListaConsumosBasicos.size() > 0) {
							for(int i = 0; i < locListaConsumosBasicos.size(); i++) {
								ConsumoBasico locConsumoBasico = (ConsumoBasico) locListaConsumosBasicos.get(i);
								// if
								// (locConsumoBasico.getSuperficieMejorasMinimo().doubleValue()
								// <=
								// documentoOSP.getParcela().getSuperficieMejoras().doubleValue()
								// &&
								// locConsumoBasico.getSuperficieMejorasMaximo().doubleValue()
								// >=
								// documentoOSP.getParcela().getSuperficieMejoras().doubleValue())
								// {
								documentoOSP.setConsumoBasico(locConsumoBasico);
								System.out.println("**** setConsumoBasico");
								// }
							}
						}
						System.out.println("/*/*/*/* le envio el servicio para ocultar medidor = " + locServicio);
						this.ocultarCodigoMedidor(locServicio);
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		documentoOSP.setListaAsocRegAlicuota((List) listaAsocRegAlic);

		this.setListaDelCommunication5(listaAsocRegAlic);

		Object codigoMedidor = getTextFieldValue(this.getTfCodigoMedidor());
		Object coeficienteZonal = getTextFieldValue(this.getTfCoeficienteZonal());

		documentoOSP.setNombre(getTextAreaValue(this.getTaNombre()));
		documentoOSP.setNumeroCuenta(getTextFieldValueInteger(this.getTfNumeroCuenta()));
		documentoOSP.setNumeroSubCuenta(getTextFieldValueInteger(this.getTfNumeroSubCuenta()));
		documentoOSP.setFechaInicioActividad(getTextFieldValueDate(this.getTfFechaInicio()));
		documentoOSP.setFechaCeseActividad(getTextFieldValueDate(this.getTfFechaCese()));

		if(persona != null && persona.getIdPersona() == -1) {
			System.out.println("\nENTRA ACA id persona = -1\n");
			persona = null;
		}
		// obligacion.setPersona(persona);

		// No va mas, utilizar
		// documentoOSP.setListaRegAlicuotas(getListaDelCommunication)
		// if (servicio.getIdTipoAlicuota() == -1) {
		// servicio = null;
		// } else {
		// documentoOSP.getListaRegAlicuotas().clear();
		// documentoOSP.addRegistroAlicuota(servicio);
		// }

		if(parcela.getIdParcela() == -1) {
			parcela = null;
		}
		if(subparcela.getIdParcela() == -1) {
			subparcela = null;
		}
		if(subparcela != null) {
			System.out.println("id subp: " + subparcela.getIdParcela());
		}
		if(parcela != null) {
			System.out.println("parc id: " + parcela.getIdParcela());
		}
		if(parcela != null && parcela.getIdParcela() != -1) {
			try {
				parcelaSubparcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(parcela.getIdParcela());
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			for(RegistroPropietario rp : parcelaSubparcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
				Persona p = rp.getPersona();
				listaPropietarios.add(p);
			}

			this.getObjectListDataProvider3().setList(listaPropietarios);
			this.setListaDelCommunication2(listaPropietarios);

		}

		if(subparcela != null) {
			try {
				parcelaSubparcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(subparcela.getIdParcela());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		if(parcela != null) {
			try {
				parcelaSubparcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(parcela.getIdParcela());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		documentoOSP.setParcela(parcelaSubparcela);

		if(domicilio.getLocalidad() == null) {
			System.out.println("\n\nLOCALIDAD DOMICILIO: " + domicilio + " \n\n");
			domicilio = null;
		}
		documentoOSP.setDomicilio(domicilio);

		if(!SecurityMgr.getInstance().getMunicipalidad().isVariosServiciosOSP()) {
			if(codigoMedidor != null && codigoMedidor != "") {
				((AsocServicioOsp) documentoOSP.getListaAsocRegAlicuota().get(0)).setCodigoMedidor(codigoMedidor.toString());
			} else {
				((AsocServicioOsp) documentoOSP.getListaAsocRegAlicuota().get(0)).setCodigoMedidor(null);
			}
		}

		if(coeficienteZonal != null && coeficienteZonal != "") {
			documentoOSP.setCoeficienteZonal(Conversor.getDoubleDeString(coeficienteZonal.toString()));
		} else {
			documentoOSP.setCoeficienteZonal(null);
		}

		if(parcela != null || subparcela != null) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					if(index < getObjectListDataProvider3().getList().size()) {
						Object obj = this.getObjectListDataProvider3().getObjects()[index];
						// RegistroPropietario locRegistroPropietario =
						// (RegistroPropietario) obj;
						persona = (Persona) obj;
						System.out.println("VALOR DE PERSONA EN OBJECTLISTDATAPROVIDER: " + persona);
					}
				} else
					System.out.println("NO ENCONTRO NADA ROWKEY NULL");
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("\nNO  ENTRA if parcela subparcela\n");
			persona = null;
			domicilio = null;
		}
		obligacion.setPersona(persona);
		obligacion.setDocumentoEspecializado(documentoOSP);
		atributosDinamicos = panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		documentoOSP.setListaAtributosDinamicos(atributosDinamicos);

		filtroLogLiq.setObligacion(obligacion);
		filtroLogLiq.setUsuario(getTextFieldValue(this.getTfFiltrarUsuarioLogLiq()));
		filtroLogLiq.setEvento(getDDEnumValue(this.getDdFiltrarEventoLogLiq(), LogLiquidacion.Evento.class));
		filtroLogLiq.setFechaDesde(getTextFieldValueDate(this.getTfFiltrarFechaDesdeLogLiq()));
		filtroLogLiq.setFechaHasta(getTextFieldValueDate(this.getTfFiltrarFechaHastaLogLiq()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, obligacion);
		this.getElementoPila().getObjetos().set(ind++, documentoOSP);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, parcela);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		// this.getElementoPila().getObjetos().set(ind++, servicio);
		this.getElementoPila().getObjetos().set(6, plantillaObligacion);
		this.getElementoPila().getObjetos().set(7, atributosDinamicos);
		this.getElementoPila().getObjetos().set(8, subparcela);
		this.getElementoPila().getObjetos().set(9, filtroLogLiq);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Obligacion obligacion = null;
		DocumentoOSP documentoOSP = null;
		Persona persona = null;
		Parcela parcela = null;
		SubParcela subparcela = null;
		Domicilio domicilio = null;

		// Comentado
		// ServicioOSP servicio = null;

		PlantillaObligacion plantillaObligacion = null;
		ArrayList listaPropietarios = new ArrayList();
		ArrayList atributosDinamicos = null;

		this.acomodarSeleccionado();

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();

			if(respuesta instanceof Domicilio) {
				this.getElementoPila().getObjetos().set(4, respuesta);
			}
		}

		try {
			DocumentoOSP locDocumento = (DocumentoOSP) this.obtenerObjetoDelElementoPila(1);
			this.getElementoPila()
					.getObjetos()
					.set(7, this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoOSP.serialVersionUID, locDocumento.getListaAtributosDinamicos(), null));
		} catch(Exception e) {
			e.printStackTrace();
		}

		ind = 0;
		obligacion = this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
		documentoOSP = this.obtenerObjetoDelElementoPila(ind++, DocumentoOSP.class);
		persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		parcela = this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(9, FiltroLogLiquidacion.class);

		// Comentado
		// servicio = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++,
		// ServicioOSP.class);

		plantillaObligacion = this.obtenerObjetoDelElementoPila(6, PlantillaObligacion.class);
		atributosDinamicos = this.obtenerObjetoDelElementoPila(7, ArrayList.class);
		subparcela = this.obtenerObjetoDelElementoPila(8, SubParcela.class);

		this.getTaNombre().setText(documentoOSP.getNombre());

		if(documentoOSP.getNumeroCuenta() != null) {
			this.getTfNumeroCuenta().setText(documentoOSP.getNumeroCuenta().toString());
		}
		if(documentoOSP.getNumeroSubCuenta() != null) {
			this.getTfNumeroSubCuenta().setText(documentoOSP.getNumeroSubCuenta().toString());
		}
		if(documentoOSP.getFechaInicioActividad() != null) {
			this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoOSP.getFechaInicioActividad()));
		}
		if(documentoOSP.getFechaCeseActividad() != null) {
			this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoOSP.getFechaCeseActividad()));
		}

		if(!SecurityMgr.getInstance().getMunicipalidad().isVariosServiciosOSP() && !documentoOSP.getListaAsocRegAlicuota().isEmpty()) {
			AsocServicioOsp locServicioOsp = (AsocServicioOsp) documentoOSP.getListaAsocRegAlicuota().get(0);
			if(locServicioOsp.getCodigoMedidor() != null) {
				this.getTfCodigoMedidor().setText(locServicioOsp.getCodigoMedidor());
			}
		}
		if(documentoOSP.getCoeficienteZonal() != null) {
			this.getTfCoeficienteZonal().setText(documentoOSP.getCoeficienteZonal().toString());
		}
		if(persona != null) {
			this.getTfPersona().setText(persona.toString());
			this.seleccionarPersonaObligacion(persona);
		}
		if(parcela != null && parcela.getIdParcela() != -1) {
			this.getTfParcela().setText(parcela.toString());
		}
		if(subparcela != null && subparcela.getIdParcela() != -1) {
			this.getTfSubParcela().setText(subparcela.toString());
		}

		this.ldpPropietariosParcela.setObjectType(Persona.class);

		if((parcela != null && parcela.getIdParcela() != -1) || (subparcela != null && subparcela.getIdParcela() != -1)) {
			System.out.println("entrooo");
			if(subparcela != null && subparcela.getIdParcela() != -1) {
				System.out.println("subparcelaaa");
				if(subparcela.getTitular() != null) {
					System.out.println("titular: " + subparcela.getTitular());
					listaPropietarios.add(subparcela.getTitular());
				}
			} else {
				System.out.println("parcelaaa");
				for(RegistroPropietario rp : parcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
					listaPropietarios.add(rp.getPersona());
				}
			}

			System.out.println("cant listaProp: " + listaPropietarios.size());

			for(Object obj : listaPropietarios) {
				Persona p = (Persona) obj;
				System.out.println("toStringCompleto: " + p.getToStringCompleto());
			}

			if(!SecurityMgr.getInstance().getMunicipalidad().isVariosServiciosOSP()) {
				List<AsocRegAlicuota> lista = documentoOSP.getListaAsocRegAlicuota();
				if(lista.size() > 0) {
					ServicioOSP servicio = (ServicioOSP) lista.get(0).getRegistroAlicuota();

					if(servicio != null && servicio.isMedido()) {
						boolean valido = false;
						if(parcela != null && parcela.getIdParcela() != -1) {
							documentoOSP.setParcela(parcela);
						}
						if(subparcela != null && subparcela.getIdParcela() != -1) {
							documentoOSP.setParcela(subparcela);
						}

						if(parcela == null || parcela.getIdParcela() == -1) {
							if(documentoOSP.getParcela() != null && documentoOSP.getParcela().getIdParcela() != -1) {
								parcela = documentoOSP.getParcela();
								valido = true;
							} else {
								valido = false;
							}
						} else if(subparcela == null || subparcela.getIdParcela() == -1) {
							if(documentoOSP.getParcela() != null && documentoOSP.getParcela().getIdParcela() != -1) {
								parcela = documentoOSP.getParcela();
								valido = true;
							} else {
								valido = false;
							}
						} else {
							valido = true;
						}

						// if (documentoOSP.getConsumoBasico() == null) {
						// System.out.println("Consumo Basico  == null");
						// this.guardarEstadoObjetosUsados();
						// }

						if(valido) {
							try {
								documentoOSP.getBaseConsumo();
								// Comentado
								// if (documentoOSP != null) {
								// // if (documentoOSP.getConsumoBasico() !=
								// // null) {
								// //
								//
								// System.out.println("FORMULA CONSUMOBASICO : consumoBasico.getConsumoInicial()="
								// // +
								// //
								// (documentoOSP.getConsumoBasico().getConsumoInicial()
								// // != null) ?
								// //
								// documentoOSP.getConsumoBasico().getConsumoInicial().toString()
								// // : "EL CONSUMO INICIAL ES NULL")
								// // +
								// //
								// " + ( (this.getParcela().getSuperficieMejoras().doubleValue()= "
								// // + ((parcela.getSuperficieMejoras() !=
								// null) ?
								// //
								// parcela.getSuperficieMejoras().toString()
								// :
								// //
								// "LA SUPERFICIE DE MEJORAS DE LA PARCELA ES NULL")
								// // +
								// //
								// " - this.consumoBasico.getSuperficieMejorasMinimo()="
								// // +
								// //
								// ((documentoOSP.getConsumoBasico().getSuperficieMejorasMinimo()
								// // != null) ?
								// //
								// documentoOSP.getConsumoBasico().getSuperficieMejorasMinimo().toString()
								// // :
								// "LA SUPERFICIE DE MEJROAS MINIMO ES NULL")
								// +
								// // ") "
								// // +
								// //
								// " * this.consumoBasico.getConsumoPorExcedente()="
								// // +
								// //
								// ((documentoOSP.getConsumoBasico().getConsumoPorExcedente()
								// // != null) ?
								// //
								// documentoOSP.getConsumoBasico().getConsumoPorExcedente().toString()
								// // : "EL CONSUMO POR EXEDENTE ES NULL"));
								// // } else {
								// //
								// System.out.println("EL CONSUMO BASICO ES NULL");
								// // }
								//
								// } else {
								// System.out.println("EL DOCUMENTO ES NULL");
								// }

								this.getTfBaseConsumo().setText(documentoOSP.getBaseConsumo());
							} catch(Exception ex) {
								log(CASO_NAVEGACION + "_BaseConsumoError:", ex);
								error(NOMBRE_PAGINA + " - BaseConsumo: " + ex.getMessage());
								ex.printStackTrace();
							}
						}
					}
				}
			}
		}
		// this.getTfCodigoMedidor().setText(documentoOSP.getCodigoMedidor());
		this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));

		// this.getTfCoeficienteZonal().setText(Conversor.getStringDeDouble(documentoOSP.getCoeficienteZonal()));

		this.getObjectListDataProvider3().setList(listaPropietarios);
		this.getLdpPropietariosParcela().setObjectType(Persona.class);
		this.setListaDelCommunication2(listaPropietarios);

		this.getObjectListDataProvider2().setList(listaPropietarios);
		this.setListaDelCommunication(listaPropietarios);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);

		if(SecurityMgr.getInstance().getMunicipalidad().isVariosServiciosOSP()) {
			this.getObjectListDataProvider5().setList(documentoOSP.getListaAsocRegAlicuota());
			this.setListaDelCommunication5(this.getObjectListDataProvider5().getList());
			this.getLdpServicioOSP().setObjectType(AsocServicioOsp.class);
		} else {
			List<AsocRegAlicuota> lista = documentoOSP.getListaAsocRegAlicuota();
			if(lista.size() > 0) {
				this.getTfServicioOSP().setText(lista.get(0).getRegistroAlicuota().getNombre());
				this.ocultarCodigoMedidor((ServicioOSP) lista.get(0).getRegistroAlicuota());
			} else {
				this.getLblBaseConsumo().setRendered(false);
				this.getTfBaseConsumo().setRendered(false);
				this.getLblCodigoMedidor().setRendered(false);
				this.getTfCodigoMedidor().setRendered(false);
			}
		}

		if(this.getRequestBean1().getObjetosSeleccionMultiple() != null && !this.getRequestBean1().getObjetosSeleccionMultiple().isEmpty()) {
			for(Object cadaObject : this.getRequestBean1().getObjetosSeleccionMultiple()) {
				this.agregarServicioSeleccionado((ServicioOSP) cadaObject, documentoOSP);
			}
			this.getElementoPila().getObjetos().set(1, documentoOSP);
		}

		this.getTfFiltrarUsuarioLogLiq().setText(filtroLogLiq.getUsuario());
		this.getDdFiltrarEventoLogLiq().setSelected(filtroLogLiq.getEvento());
		this.getTfFiltrarFechaDesdeLogLiq().setText(filtroLogLiq.getFechaDesde());
		this.getTfFiltrarFechaHastaLogLiq().setText(filtroLogLiq.getFechaHasta());
	}

	public void seleccionarPersonaObligacion(Persona persona) {
		System.out.println("TIRO EL METODO LOCO ESTE");
		Integer cantidadPersonas = this.ldpPropietariosParcela.getList().size();
		System.out.println("CANTIDAD DE PERSONAS: " + cantidadPersonas);
		Persona locPersona;
		for(int i = 0; i < cantidadPersonas; i++) {
			locPersona = (Persona) this.ldpPropietariosParcela.getObjects()[i];
			System.out.println("PERONA 1: " + locPersona.getIdPersona());
			System.out.println("PERONA 2: " + persona.getIdPersona());
			if(locPersona.getIdPersona() == persona.getIdPersona()) {
				System.out.println("LAST SELECTED AHORA: " + i);
				lastSelected = new Long(i).toString();
			}
		}
	}

	private ArrayList getListaDelCommunicationAtributosDinamicos() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaAtributosDinamicosOSP();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosOSP(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider3() {
		return this.getLdpPropietariosParcela();
	}

	private List getListaDelCommunication2() {
		return this.getCommunicationHabilitacionesBean().getListaPropietariosParcela();
	}

	private void setListaDelCommunication2(List lista) {
		this.getCommunicationHabilitacionesBean().setListaPropietariosParcela(lista);
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaPropietarios(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider2() {
		return this.getLdpPropietarios();
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if(!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if(!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			System.out.println("ROW ID: " + aRowId);
			rk = this.getObjectListDataProvider3().getRowKey(aRowId);
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

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilio = this.obtenerObjetoDelElementoPila(4, Domicilio.class);
			Localidad localidad = domicilio.getLocalidad();

			if(localidad != null) {
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

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Object pers = this.getElementoPila().getObjetos().get(2);
			Persona persona = null;
			if(pers != null) {
				persona = (Persona) pers;
			}
			if(persona != null && persona.getIdPersona() != -1) {
				Domicilio domicilio = persona.getDomicilioPostal();
				this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
			} else {
				warn("Seleccione una Persona Solicitante para obtener su Domicilio.");
			}

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

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Parcela parcela = this.obtenerObjetoDelElementoPila(3, Parcela.class);
			SubParcela subparcela = this.obtenerObjetoDelElementoPila(8, SubParcela.class);

			if(parcela != null && parcela.getIdParcela() != -1) {
				Domicilio domicilio = parcela.getDomicilioParcelario();
				this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
				return null;
			} else if(subparcela != null && subparcela.getIdParcela() != -1) {
				Domicilio domicilio = subparcela.getDomicilioParcelario();
				this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
				return null;
			} else {
				warn("Seleccione una Parcela para obtener el Domicilio Parcelario.");
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarParcela_action() {
		return navegarParaSeleccionar("AdminParcela", 3);
	}

	public String btnSeleccionarSubparcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 3;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setRefrescarTabla(true);
			this.getRequestBean1().setTipoSeleccion("SUBPARCELA");

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminSubparcela";
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
			this.limpiarObjeto(3, this.getTfParcela());
			// this.limpiarObjeto(8, null); //limpio subparcela
			this.getTfBaseConsumo().setText(null);
			this.getStDomicilioPostal().setText(null);
			this.getObjectListDataProvider3().setList(null); // limpia parcela
			this.setListaDelCommunication2(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarSubParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(8, this.getTfSubParcela()); // limpio subparcela
			this.getTfSubParcela().setText(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarServicio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 5;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setRefrescarTabla(true);

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminServicioOSP";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarServicio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(5, this.getTfServicioOSP());
			ServicioOSP servicioOSP = this.obtenerObjetoDelElementoPila(5, ServicioOSP.class);
			this.ocultarCodigoMedidor(servicioOSP);
			servicioOSP.getBaseConsumo();
			this.guardarEstadoObjetosUsados();

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarServicio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setTipoSeleccion("MULTIPLE");

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminServicioOSP";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarServicio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		DocumentoOSP documentoOSP = this.obtenerObjetoDelElementoPila(1, DocumentoOSP.class);

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado5();
				if(rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider5().getObjects()[index];

					AsocServicioOsp locAsocServicio = (AsocServicioOsp) obj;

					if(this.getObjectListDataProvider5().getList().size() > 0) {
						this.getObjectListDataProvider5().commitChanges();
					}

					documentoOSP.getListaAsocRegAlicuota().remove(locAsocServicio);

					this.setListaDelCommunication5((List) documentoOSP.getListaAsocRegAlicuota());
					this.getObjectListDataProvider5().setList(this.getListaDelCommunication5());

					this.getElementoPila().getObjetos().set(1, documentoOSP);
				}
			} catch(Exception ex) {
				System.out.println("btnQuitar() entra al catch...");
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		}
		return retorno;
	}

	private ObjectListDataProvider getObjectListDataProvider5() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpServicioOSP();
	}

	private List<AsocServicioOsp> getListaDelCommunication5() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaServiciosOSPObligacionOSP();
	}

	private void setListaDelCommunication5(List<AsocServicioOsp> lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaServiciosOSPObligacionOSP(lista);
	}

	public RowKey getSeleccionado5() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			System.out.println("ROW ID: " + aRowId);
			rk = this.getObjectListDataProvider5().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private void ocultarCodigoMedidor(ServicioOSP servicio) {
		boolean mostrar = false;
		System.out.println("****************/ servicio = " + servicio.getNombre() + " medido = " + servicio.isMedido());
		if(servicio != null) {
			mostrar = servicio.isMedido();
			System.out.println("/////// mostrar porque es medido");
		} else
			System.out.println("/////// no mostrar porque no es medido");

		Parcela locParcela = this.obtenerObjetoDelElementoPila(3, Parcela.class);

		this.getLblBaseConsumo().setRendered(mostrar);
		this.getTfBaseConsumo().setRendered(mostrar);
		this.getTfBaseConsumo().setVisible(mostrar);

		this.getLblCodigoMedidor().setRendered(mostrar);
		this.getTfCodigoMedidor().setRendered(mostrar);
		this.getTfCodigoMedidor().setVisible(mostrar);

		if(!mostrar) {
			this.getTfBaseConsumo().setText(null);
		}
		return;
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
		return "ABMDocEspOSP";
	}

	public void btnFiltrarLogLiq_action() {
		this.guardarEstadoObjetosUsados();
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				FiltroLogLiquidacion locFiltro = (FiltroLogLiquidacion) this.obtenerObjetoDelElementoPila(9);

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
		this.getElementoPila().getObjetos().set(9, null);

		this.getTfFiltrarUsuarioLogLiq().setText("");
		this.getDdFiltrarEventoLogLiq().setSelected("");
		this.getTfFiltrarFechaDesdeLogLiq().setText("");
		this.getTfFiltrarFechaHastaLogLiq().setText("");

		this.getCommunicationSAICBean().setListaLogLiquidacion(new ArrayList<LogLiquidacion>());
		this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

		// if (this.getRequestBean1().getTipoSeleccion() != null && this.getRequestBean1().getTipoSeleccion().equals("SUBPARCELA")) {
		if(pObject instanceof SubParcela) {
			System.out.println("es SUBPARCELA");
			this.setRBSelected((new Long(0)).toString());
			SubParcela subparcela = (SubParcela) pObject;

			if(subparcela.getTituloPropiedad() != null) {
				this.getElementoPila().getObjetos().set(8, subparcela);
			} else {
				this.getRequestBean1().setObjetoSeleccion(null);
				this.getElementoPila().getObjetos().set(8, null);
				this.getObjectListDataProvider3().setList(null);
				this.setListaDelCommunication2(null);
				warn("La Subparcela seleccionada no posee T\355tulo de Propiedad.");
			}
			this.getStDomicilioPostal().setText(null);
			this.getElementoPila().getObjetos().set(4, null);
			this.getElementoPila().getObjetos().set(3, null);// limpio
			// parcela
		} else {
			if(pObject instanceof Parcela) {
				System.out.println("es PARCELA");
				this.setRBSelected((new Long(0)).toString());
				Parcela parcela = (Parcela) pObject;

				try {
					this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
					parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(parcela.getIdParcela());
				} catch(Exception e) {
					e.printStackTrace();
				}

				if(parcela.getTituloPropiedad() != null) {
					this.getElementoPila().getObjetos().set(3, parcela);
				} else {
					this.getRequestBean1().setObjetoSeleccion(null);
					this.getElementoPila().getObjetos().set(3, null);
					this.getObjectListDataProvider3().setList(null);
					this.setListaDelCommunication2(null);
					warn("La parcela seleccionada no posee T\355tulo de Propiedad.");
				}
				this.getStDomicilioPostal().setText(null);
				this.getElementoPila().getObjetos().set(4, null);
				this.getElementoPila().getObjetos().set(8, null);// limpio
				// subparcela
			} else if(pObject instanceof ServicioOSP) {
				System.out.println("SERVICIOOSP SELECTED");
				ServicioOSP nuevoServicio = null;
				// servicio = (ServicioOSP) seleccionado;
				// this.getElementoPila().getObjetos().set(5, servicio);

				DocumentoOSP documentoOSP = this.obtenerObjetoDelElementoPila(1, DocumentoOSP.class);
				nuevoServicio = (ServicioOSP) pObject;

				if(SecurityMgr.getInstance().getMunicipalidad().isVariosServiciosOSP()) {
					this.agregarServicioSeleccionado(nuevoServicio, documentoOSP);
				} else {
					documentoOSP.getListaAsocRegAlicuota().get(0).setRegistroAlicuota(nuevoServicio);
				}
				this.getObjectListDataProvider5().setList(new ArrayList(documentoOSP.getListaRegAlicuotas()));
				this.setListaDelCommunication5(new ArrayList(documentoOSP.getListaRegAlicuotas()));
				this.getElementoPila().getObjetos().set(1, documentoOSP);
				this.getRequestBean1().setObjetoSeleccion(null);
			}
		}
	}

	private void agregarServicioSeleccionado(ServicioOSP pServicio, DocumentoOSP pDocumento) {
		boolean esta = false;
		int i = 0;
		List<AsocRegAlicuota> lista = pDocumento.getListaAsocRegAlicuota();
		ServicioOSP servicioExistente;
		while(i < lista.size() && !esta) {
			servicioExistente = (ServicioOSP) lista.get(i).getRegistroAlicuota();
			esta = (servicioExistente.equals(pServicio));
			i++;
		}
		if(!esta) {
			AsocServicioOsp nuevaAsoc = new AsocServicioOsp();
			nuevaAsoc.setDocHabilitanteEspecializado(pDocumento);
			nuevaAsoc.setRegistroAlicuota(pServicio);
			pDocumento.getListaAsocRegAlicuota().add(nuevaAsoc);
		} else {
			warn("El servicio " + pServicio.toString() + " ya se encontraba en la lista.");
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Obligacion obligacion = null;
		DocumentoOSP documentoOSP = null;
		Parcela parcela = null;
		Persona persona = null;
		Domicilio domicilio = null;
		ArrayList atributosDinamicos = null;
		PlantillaObligacion plantillaObligacion = null;
		SubParcela subparcela = null;
		int ind;

		if(pObject instanceof PlantillaObligacion) {
			plantillaObligacion = (PlantillaObligacion) this.getRequestBean1().getObjetoABM();

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().generarArbol(plantillaObligacion);

				documentoOSP = (DocumentoOSP) obligacion.getDocumentoEspecializado();
				documentoOSP.setObligacion(obligacion);
				documentoOSP.setNumeroCuenta(this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().getSugerenciaNumeroCuenta());

				this.setListaDelCommunicationDocsGeneradoresDeuda(new ArrayList());

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_GenerarObligacion:", ex);
				error(NOMBRE_PAGINA + " - No se pudo generar la Obligaci\363n: " + ex.getMessage());
				ex.printStackTrace();
			}
			this.getElementoPila().getObjetos().set(7, atributosDinamicos);
		} else {
			obligacion = (Obligacion) this.getRequestBean1().getObjetoABM();
			long id = obligacion.getIdObligacion();

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(id);

			} catch(Exception ex) {
				log("Error Description", ex);
			}

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
				documentoOSP = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().getDocumentoOSP(obligacion);
				for(LogAuditoria cadaLog : documentoOSP.getListaLogsAuditoria()) {
					System.out.println("cadaLog " + cadaLog);
				}
				// DocumentoOSP docOSP =
				// (DocumentoOSP)obligacion.getDocumentoEspecializado();
				documentoOSP.setObligacion(obligacion);
				obligacion.setDocumentoEspecializado(documentoOSP);

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_getDocumentoHabilitanteOSP:", ex);
				error(NOMBRE_PAGINA + " - No se pudo obtener el Documento OSP: " + ex.getMessage());
			}

			try {
				this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
				parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(documentoOSP.getParcela().getIdParcela());
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_getParcelaPorId:", ex);
				error(NOMBRE_PAGINA + " - No se pudo obtener la Parcela asociada: " + ex.getMessage());
			}

			persona = obligacion.getPersona();
			domicilio = documentoOSP.getDomicilio();

			// Comentado
			// servicio = (ServicioOSP) documentoOSP.getRegistroAlicuota();

			if(documentoOSP.getListaAtributosDinamicos() != null) {
				try {
					atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
							.getAtributosPorRecurso(DocumentoOSP.serialVersionUID, documentoOSP.getListaAtributosDinamicos(), null);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}

			if(parcela != null && parcela.getTituloPropiedad().getListaRegistrosPropietarios() != null) {
				List listaPersonas = new ArrayList();
				for(RegistroPropietario cadaReg : parcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
					listaPersonas.add(cadaReg.getPersona());
				}
				this.getLdpPropietariosParcela().setList(listaPersonas);
			}

			try {
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				this.setListaDelCommunicationDocsGeneradoresDeuda(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaDocsGeneradoresDeuda(obligacion));
			} catch(Exception e) {
				e.printStackTrace();
			}

			ind = 0;

			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoOSP);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, parcela);
			this.getElementoPila().getObjetos().set(ind++, domicilio);
			// Comentado
			// this.getElementoPila().getObjetos().set(ind++, servicio);
			this.getElementoPila().getObjetos().set(6, plantillaObligacion);
			this.getElementoPila().getObjetos().set(7, atributosDinamicos);
			this.getElementoPila().getObjetos().set(8, subparcela);
		}

		persona = this.obtenerObjetoDelElementoPila(2, Persona.class);
		domicilio = this.obtenerObjetoDelElementoPila(4, Domicilio.class);

		System.out.println("OBLIGACION: " + obligacion);
		System.out.println("PERSONA: " + persona);

		if(persona != null) {
			this.seleccionarPersonaObligacion(persona);
		}

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, obligacion);
		this.getElementoPila().getObjetos().set(ind++, documentoOSP);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, parcela);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		// Comentado
		// this.getElementoPila().getObjetos().set(ind++, servicio);
		this.getElementoPila().getObjetos().set(6, plantillaObligacion);
		this.getElementoPila().getObjetos().set(8, subparcela);
		// this.getElementoPila().getObjetos().set(ind++,
		// atributosDinamicos);

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

		DocumentoOSP locDocumento = this.obtenerObjetoDelElementoPila(1, DocumentoOSP.class);
		this.getTablaLogs().getLdpLogs().setList(locDocumento.getListaLogsAuditoria());

		if(this.getListaDelCommunicationDocsGeneradoresDeuda() != null) {
			this.getLdpDocsGeneradoresDeuda().setList(this.getListaDelCommunicationDocsGeneradoresDeuda());
		}
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoOSP.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP}";
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
		this.getObjectListDataProvider3().setList(listaPropietarios);
		this.setListaDelCommunication2(listaPropietarios);
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