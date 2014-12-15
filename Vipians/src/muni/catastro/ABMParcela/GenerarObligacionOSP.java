/*
 * GenerarObligacionOSP.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender
 */

package muni.catastro.ABMParcela;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.FacesException;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.PageSeparator;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.PanelLayout;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.MultipleSelectOptionsList;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionOSP;
import com.trascender.habilitaciones.recurso.filtros.FiltroServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class GenerarObligacionOSP extends AbstractPageBean {
	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if(this.getListaDelCommunication2() != null) {
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
		}
	}

	private Page page1 = new Page();

	public Page getPage1() {
		return page1;
	}

	public void setPage1(Page p) {
		this.page1 = p;
	}

	private Html html1 = new Html();

	public Html getHtml1() {
		return html1;
	}

	public void setHtml1(Html h) {
		this.html1 = h;
	}

	private Head head1 = new Head();

	public Head getHead1() {
		return head1;
	}

	public void setHead1(Head h) {
		this.head1 = h;
	}

	private Link link1 = new Link();

	public Link getLink1() {
		return link1;
	}

	public void setLink1(Link l) {
		this.link1 = l;
	}

	private Body body1 = new Body();

	public Body getBody1() {
		return body1;
	}

	public void setBody1(Body b) {
		this.body1 = b;
	}

	private Form form1 = new Form();

	public Form getForm1() {
		return form1;
	}

	public void setForm1(Form f) {
		this.form1 = f;
	}

	private StaticText stTitulo = new StaticText();

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label2 = new Label();
	private Label lblParcela = new Label();

	public Label getLblParcela() {
		return lblParcela;
	}

	public void setLblParcela(Label lblParcela) {
		this.lblParcela = lblParcela;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Button btnGuardar = new Button();
	private Button btnSeleccionarParcela = new Button();
	private Button btnLimpiarParcela = new Button();

	public Button getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(Button btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button btnSeleccionarParcela) {
		this.btnSeleccionarParcela = btnSeleccionarParcela;
	}

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Button b) {
		this.btnGuardar = b;
	}

	private Button btnCancelar = new Button();
	private Button btnAgregarOSP = new Button();
	private Button btnNoGenerar = new Button();
	private Button btnGenerar = new Button();
	private Button btnVolver = new Button();

	public Button getBtnGenerar() {
		return btnGenerar;
	}

	public void setBtnGenerar(Button btnGenerar) {
		this.btnGenerar = btnGenerar;
	}

	public Button getBtnNoGenerar() {
		return btnNoGenerar;
	}

	public void setBtnNoGenerar(Button btnNoGenerar) {
		this.btnNoGenerar = btnNoGenerar;
	}

	public Button getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(Button btnVolver) {
		this.btnVolver = btnVolver;
	}

	public Button getBtnAgregarOSP() {
		return btnAgregarOSP;
	}

	public void setBtnAgregarOSP(Button btnAgregarOSP) {
		this.btnAgregarOSP = btnAgregarOSP;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button b) {
		this.btnCancelar = b;
	}

	private StaticText stSeparador = new StaticText();

	public StaticText getStSeparador() {
		return stSeparador;
	}

	public void setStSeparador(StaticText st) {
		this.stSeparador = st;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private HiddenField hidIdPagina = new HiddenField();

	public HiddenField getHidIdPagina() {
		return hidIdPagina;
	}

	public void setHidIdPagina(HiddenField hf) {
		this.hidIdPagina = hf;
	}

	private HiddenField hidIdSubSesion = new HiddenField();

	public HiddenField getHidIdSubSesion() {
		return hidIdSubSesion;
	}

	public void setHidIdSubSesion(HiddenField hf) {
		this.hidIdSubSesion = hf;
	}

	private Script scriptValidador = new Script();

	public Script getScriptValidador() {
		return scriptValidador;
	}

	public void setScriptValidador(Script scriptValidador) {
		this.scriptValidador = scriptValidador;
	}

	private PageSeparator psLineaHorizontal = new PageSeparator();

	public PageSeparator getPsLineaHorizontal() {
		return psLineaHorizontal;
	}

	public void setPsLineaHorizontal(PageSeparator psLineaHorizontal) {
		this.psLineaHorizontal = psLineaHorizontal;
	}

	private SingleSelectOptionsList ddServicioOSPDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdServicioOSPDefaultOptions() {
		return ddServicioOSPDefaultOptions;
	}

	public void setDdServicioOSPDefaultOptions(SingleSelectOptionsList ddServicioOSPDefaultOptions) {
		this.ddServicioOSPDefaultOptions = ddServicioOSPDefaultOptions;
	}

	private MultipleSelectOptionsList chbgServiciosOSPDefaultOptions = new MultipleSelectOptionsList();

	public MultipleSelectOptionsList getChbgServiciosOSPDefaultOptions() {
		return chbgServiciosOSPDefaultOptions;
	}

	public void setChbgServiciosOSPDefaultOptions(MultipleSelectOptionsList chbgServiciosOSPDefaultOptions) {
		this.chbgServiciosOSPDefaultOptions = chbgServiciosOSPDefaultOptions;
	}

	private DropDown ddServicioOSP = new DropDown();

	public DropDown getDdServicioOSP() {
		return ddServicioOSP;
	}

	public void setDdServicioOSP(DropDown ddServicioOSP) {
		this.ddServicioOSP = ddServicioOSP;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
	// Atributos propios de la pagina.
	// CAMBIAR: Vincular a campos ocultos.
	private Long idPagina = null;
	private Long idSubSesion = null;

	public Long getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(Long idPagina) {
		this.idPagina = idPagina;
	}

	public Long getIdSubSesion() {
		return idSubSesion;
	}

	public void setIdSubSesion(Long idSubSesion) {
		this.idSubSesion = idSubSesion;
	}

	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Generar Obligacion OSP";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "GenerarObligacionOSP";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;
	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TextField tfNroPartidaProvincial = new TextField();

	public TextField getTfNroPartidaProvincial() {
		return tfNroPartidaProvincial;
	}

	public void setTfNroPartidaProvincial(TextField tf) {
		this.tfNroPartidaProvincial = tf;
	}

	private Table table1 = new Table();
	private Table table2 = new Table();

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private TableColumn tcTitular = new TableColumn();
	private TableColumn tcSuperficie = new TableColumn();
	private TableColumn tcSup = new TableColumn();

	public TableColumn getTcSup() {
		return tcSup;
	}

	public void setTcSup(TableColumn tcSup) {
		this.tcSup = tcSup;
	}

	public TableColumn getTcSuperficie() {
		return tcSuperficie;
	}

	public void setTcSuperficie(TableColumn tcSuperficie) {
		this.tcSuperficie = tcSuperficie;
	}

	public TableColumn getTcTitular() {
		return tcTitular;
	}

	public void setTcTitular(TableColumn tcTitular) {
		this.tcTitular = tcTitular;
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

	private StaticText staticText2 = new StaticText();
	private StaticText stNumeroCuenta = new StaticText();
	private StaticText stNumeroSubcuenta = new StaticText();
	private StaticText stCodigoMedidor = new StaticText();

	public StaticText getStCodigoMedidor() {
		return stCodigoMedidor;
	}

	public void setStCodigoMedidor(StaticText stCodigoMedidor) {
		this.stCodigoMedidor = stCodigoMedidor;
	}

	public StaticText getStNumeroCuenta() {
		return stNumeroCuenta;
	}

	public void setStNumeroCuenta(StaticText stNumeroCuenta) {
		this.stNumeroCuenta = stNumeroCuenta;
	}

	public StaticText getStNumeroSubcuenta() {
		return stNumeroSubcuenta;
	}

	public void setStNumeroSubcuenta(StaticText stNumeroSubcuenta) {
		this.stNumeroSubcuenta = stNumeroSubcuenta;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();
	private TableColumn tableColumn8 = new TableColumn();
	private TableColumn tableColumn9 = new TableColumn();
	private TableColumn tableColumn10 = new TableColumn();
	private TableColumn tableColumn11 = new TableColumn();

	public TableColumn getTableColumn11() {
		return tableColumn11;
	}

	public void setTableColumn11(TableColumn tableColumn11) {
		this.tableColumn11 = tableColumn11;
	}

	public TableColumn getTableColumn10() {
		return tableColumn10;
	}

	public void setTableColumn10(TableColumn tableColumn10) {
		this.tableColumn10 = tableColumn10;
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

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tableColumn6) {
		this.tableColumn6 = tableColumn6;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private TextField textField1 = new TextField();
	private TextField tfTitular = new TextField();
	private TextField tfNumeroCuenta = new TextField();
	private TextField tfNumeroSubCuenta = new TextField();
	private TextField tfCodigoMedidor = new TextField();

	public TextField getTfCodigoMedidor() {
		return tfCodigoMedidor;
	}

	public void setTfCodigoMedidor(TextField tfCodigoMedidor) {
		this.tfCodigoMedidor = tfCodigoMedidor;
	}

	public TextField getTfNumeroSubCuenta() {
		return tfNumeroSubCuenta;
	}

	public void setTfNumeroSubCuenta(TextField tfNumeroSubCuenta) {
		this.tfNumeroSubCuenta = tfNumeroSubCuenta;
	}

	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tfNumeroCuenta) {
		this.tfNumeroCuenta = tfNumeroCuenta;
	}

	public TextField getTfTitular() {
		return tfTitular;
	}

	public void setTfTitular(TextField tfTitular) {
		this.tfTitular = tfTitular;
	}

	private TextField tfTitularSubdivision = new TextField();

	public TextField getTfTitularSubdivision() {
		return tfTitularSubdivision;
	}

	public void setTfTitularSubdivision(TextField tfTitularSubdivision) {
		this.tfTitularSubdivision = tfTitularSubdivision;
	}

	public TextField getTextField1() {
		return textField1;
	}

	public void setTextField1(TextField tf) {
		this.textField1 = tf;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private StaticText staticText4 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText staticText7) {
		this.staticText7 = staticText7;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private PageSeparator pgSeparator = new PageSeparator();

	public PageSeparator getPgSeparator() {
		return pgSeparator;
	}

	public void setPgSeparator(PageSeparator pgSeparator) {
		this.pgSeparator = pgSeparator;
	}

	private PanelLayout pLayout = new PanelLayout();

	public PanelLayout getpLayout() {
		return pLayout;
	}

	public void setpLayout(PanelLayout pLayout) {
		this.pLayout = pLayout;
	}

	private ObjectListDataProvider ldpObligacionesOSP = new ObjectListDataProvider();
	private ObjectListDataProvider ldpObligacionesOSPExistentes = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpObligacionesOSPExistentes() {
		return ldpObligacionesOSPExistentes;
	}

	public void setLdpObligacionesOSPExistentes(ObjectListDataProvider ldpObligacionesOSPExistentes) {
		this.ldpObligacionesOSPExistentes = ldpObligacionesOSPExistentes;
	}

	public ObjectListDataProvider getLdpObligacionesOSP() {
		return ldpObligacionesOSP;
	}

	public void setLdpObligacionesOSP(ObjectListDataProvider ldpObligacionesOSP) {
		this.ldpObligacionesOSP = ldpObligacionesOSP;
	}

	private Checkbox checkbox1 = new Checkbox();

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox c) {
		this.checkbox1 = c;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public GenerarObligacionOSP() {
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (muni.CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationCajaBean getCommunicationCajaBean() {
		return (muni.CommunicationCajaBean) getBean("CommunicationCajaBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationSAICBean getCommunicationSAICBean() {
		return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.RequestBean1 getRequestBean1() {
		return (muni.RequestBean1) getBean("RequestBean1");
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina, ya sea directamente mediante un URL o de manera indirecta a trav�s de
	 * la navegaci�n de p�ginas. Puede personalizar este m�todo para adquirir recursos que se necesitar�n para los controladores de eventos y m�todos del
	 * proceso, sin tener en cuenta si esta p�gina realiza procesamiento de devoluci�n de env�os.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores de propiedad de los componentes <strong>no</strong> representan ning�n
	 * valor enviado con esta petici�n. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se proces�.
	 * </p>
	 */
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicaci�n que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		// <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("GenerarObligacionOSP Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�
		tablePhaseListener = this.getTableSelectPhaseListener();
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */
	public void prerender() {
		boolean existeIdSubSesionReq = false;
		boolean existeIdPaginaReq = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag = false;
		boolean recarga = false;

		if(this.getRequestBean1() != null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag = this.getIdPagina() != null;

		// Pagina nueva - Inicio de transaccion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if(this.PUEDE_SER_PAGINA_INICIAL) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();
			}
		}

		// Recarga de la misma pagina por validacion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// APLICAR LOGICA AQUI.. ver si es as� realmente..
		}

		// Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if(existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();
		}

		// Pagina nueva - hacia atras en la transaccion
		if(existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());
		}

		if(!recarga) {
			this.mostrarEstadoObjetosUsados();
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));

	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	public void destroy() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new ArrayList()); // 0 listaObligacionesOSPParcela
		ep.getObjetos().add(ind++, new ArrayList()); // 1 lista subparcelas
		ep.getObjetos().add(ind++, new ArrayList()); // 2 lista listaObligacionesOSPSubparcelas
		ep.getObjetos().add(ind++, new Obligacion()); // 3 obligacion
		ep.getObjetos().add(ind++, new Parcela()); // 4 parcela
		ep.getObjetos().add(ind++, new ArrayList()); // 5 lista listaObligacionesOSPNuevas
		ep.getObjetos().add(ind++, new ArrayList()); // 6 lista listaObligacionesOSPAnuladas

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	private void guardarEstadoObjetosUsados() {
		int ind = 0;
		List listaObligacionesOSP = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		// Busco los servicios OSP sobre los que tenga obligaciones la parcela:
		try {
			Option[] option = new Option[listaObligacionesOSP.size()];
			for(int i = 0; i < listaObligacionesOSP.size(); i++) {
				Obligacion obligacion = (Obligacion) listaObligacionesOSP.get(i);
				for(Object obj : obligacion.getDocumentoEspecializado().getListaRegAlicuotas()) {
					RegAlicuota locRegAlicuota = (RegAlicuota) obj;
					option[i] = new com.sun.rave.web.ui.model.Option(locRegAlicuota.getCodigo(), locRegAlicuota.toString());
				}
			}
			ddServicioOSPDefaultOptions.setOptions(option);

		} catch(Exception ex) {
			ex.printStackTrace();
		}
		this.getElementoPila().getObjetos().set(0, listaObligacionesOSP);
	}

	private void mostrarEstadoObjetosUsados() {
		int ind = 0;
		// CAMBIAR: Obtener una instancia por cada objeto manejado en la pagina
		List listaObligacionesOSP = new ArrayList();
		List listaObligacionesOSPSubparcela = new ArrayList();
		List listaSubparcela = new ArrayList();
		Parcela parcela = new Parcela();
		FiltroObligacionOSP locFiltro = new FiltroObligacionOSP();

		if(this.getRequestBean1().getObjetoABM() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoABM();
			parcela = (Parcela) seleccionado;
			List listaObligaciones = new ArrayList();
			try {

				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				locFiltro.setParcela(parcela);
				// listaObligaciones.addAll((ArrayList) this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesOSP(null,
				// parcela, null));
				listaObligacionesOSPSubparcela.add(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesOSP(locFiltro));
				System.out.println("cant oblig osp medidas y no medidas");
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_findObligacionesOSP: ", ex);
				error("No se pudieron obtener los Registros de Obligaciones OSP: " + ex.getMessage());
			}

			if(listaObligaciones != null) {
				for(Object object : listaObligaciones) {
					Obligacion obligacion = (Obligacion) object;
					for(Object obj : obligacion.getDocumentoEspecializado().getListaRegAlicuotas()) {
						RegAlicuota registroAlicuota = (RegAlicuota) obj;
						if(((ServicioOSP) registroAlicuota).isMedido()) {
							System.out.println("si no es medida");
							listaObligacionesOSP.add(obligacion);
						}
					}
					// if (((ServicioOSP) obligacion.getDocumentoEspecializado().getRegistroAlicuota()).isMedido()) {
					// System.out.println("si no es medida");
					// listaObligacionesOSP.add(obligacion);
					// }
				}
			}

			listaSubparcela.addAll(parcela.getListaSubParcelas());

			this.getElementoPila().getObjetos().set(0, listaObligacionesOSP);
			this.getElementoPila().getObjetos().set(1, listaSubparcela);
			// this.getElementoPila().getObjetos().set(2, listaObligacionesOSPSubparcela);
			System.out.println("OBLIGACIONES OSP DE SUBPARCELAS  " + listaObligacionesOSPSubparcela.size());
			this.getElementoPila().getObjetos().set(4, parcela);
			this.obtenerListaObligacionesSubparcela();
		}

		ind = 0;
		listaObligacionesOSP = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		parcela = (Parcela) this.obtenerObjetoDelElementoPila(4, Parcela.class);

		// Busco los servicios OSP sobre los que tenga obligaciones la parcela, solo las medidas:
		if(listaObligacionesOSP != null && listaObligacionesOSP.size() > 0) {
			try {

				System.out.println("cantidad de serv medidos: " + listaObligacionesOSP);
				Option[] option = new Option[listaObligacionesOSP.size()];
				for(int i = 0; i < listaObligacionesOSP.size(); i++) {
					Obligacion obligacion = (Obligacion) listaObligacionesOSP.get(i);
					for(Object obj : obligacion.getDocumentoEspecializado().getListaRegAlicuotas()) {
						RegAlicuota locRegAlicuota = (RegAlicuota) obj;
						option[i] = new com.sun.rave.web.ui.model.Option(locRegAlicuota.getCodigo(), locRegAlicuota.toString());
					}

				}
				System.out.println("TIENE OSP!!!!!!!!!!!!!!!!");
				ddServicioOSPDefaultOptions.setOptions(option);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("NO TIENE OSP!!!!!!!!!!!");
			Option[] option = new Option[1];
			option[0] = new com.sun.rave.web.ui.model.Option(0, "------");
			ddServicioOSPDefaultOptions.setOptions(option);
		}

		Boolean seleccionar = new Boolean(false); // habilitamos los botones

		if(parcela != null && parcela.getIdParcela() != -1) {
			this.getLblParcela().setText(parcela.toString());
			System.out.println("id parcela: " + parcela.getIdParcela());

			if(listaObligacionesOSP == null || listaObligacionesOSP.size() == 0) {

				seleccionar = true; // deshab botones
				warn("La parcela seleccionada no tiene Obligaciones OSP medidas.");
			}
			if(listaSubparcela == null || listaSubparcela.size() == 0) {

				seleccionar = true; // deshab botones
				warn("La parcela seleccionada no tiene Subparcelas.");
			}
		} else {
			seleccionar = true; // deshab botones
			warn("Seleccione una parcela para listar sus Obligaciones OSP.");
		}

		this.cambiarEstadoBotones(seleccionar);
	}

	private void obtenerListaObligacionesSubparcela() {
		List listaObligacionesOSPSubparcela = new ArrayList();
		List listaSubparcela = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
		FiltroObligacionOSP locFiltro = new FiltroObligacionOSP();

		for(Object obj : (ArrayList) listaSubparcela) {
			SubParcela locSubParcela = (SubParcela) obj;
			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				locFiltro.setPersona(locSubParcela.getTitular());
				locFiltro.setParcela(locSubParcela);
				// listaObligacionesOSPSubparcela.addAll((ArrayList)
				// this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesOSP(locSubParcela.getTitular(), null, null,
				// locSubParcela.getIdParcela()));
				// listaObligacionesOSPSubparcela.addAll(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesOSP(locFiltro));
				listaObligacionesOSPSubparcela.add(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesOSP(locFiltro));
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_findObligacionesOSP: ", ex);
				error("No se pudieron obtener los Registros de Obligaciones OSP: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		this.getElementoPila().getObjetos().set(2, listaObligacionesOSPSubparcela);
	}

	private void cambiarEstadoBotones(Boolean cambiarEstado) {
		this.getBtnAgregarOSP().setDisabled(cambiarEstado);
		this.getBtnNoGenerar().setDisabled(cambiarEstado);
		this.getBtnGenerar().setDisabled(cambiarEstado);
	}

	private ObjectListDataProvider getObjectListDataProvider() {

		return this.getLdpObligacionesOSP();
	}

	private ArrayList getListaDelCommunication() {

		return this.getCommunicationHabilitacionesBean().getListaObligacionesOSP();
	}

	private void setListaDelCommunication(ArrayList lista) {

		this.getCommunicationHabilitacionesBean().setListaObligacionesOSP(lista);
	}

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		// CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getTablePhaseListenerObligacionesOSP();
	}

	private ObjectListDataProvider getObjectListDataProvider2() {

		return this.getLdpObligacionesOSPExistentes();
	}

	private ArrayList getListaDelCommunication2() {

		return this.getCommunicationHabilitacionesBean().getListaObligacionesOSPExistentes();
	}

	private void setListaDelCommunication2(ArrayList lista) {

		this.getCommunicationHabilitacionesBean().setListaObligacionesOSPExistentes(lista);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Metodos estaticos de la pagina">
	private void crearElementoPila() {
		ElementoPila ep = new ElementoPila();
		ep.setCasoNavegacion(CASO_NAVEGACION);
		ep.setIdPagina(this.getIdPagina());
		ep.setIdSubSesion(this.getIdSubSesion());
		ep.setNombrePagina(NOMBRE_PAGINA);

		ep = this.agregarObjetosAElementoPila(ep);

		this.getSessionBean1().getMgrPilas().addElemento(ep);
	}

	private String prepararParaVolver(String pAccionRetorno) {
		this.getRequestBean1().setAccion(pAccionRetorno);
		String retorno = null;
		ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
		if(locElementoAnterior != null) {
			this.getSessionBean1().getMgrPilas().removeElemento(this.getElementoPila());
			this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
			this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
			this.getRequestBean1().setElementoPilaPaginaAnt(locElementoAnterior);
			retorno = locElementoAnterior.getCasoNavegacion();
		}
		return retorno;
	}

	private String prepararCaducidad() {
		// redireccionar a pagina con mensaje de caducacion
		this.getRequestBean1().setCasoNavegacionPostCaducidad(CASO_NAV_POST_CADUCIDAD);
		return CASO_NAV_CADUCIDAD;
	}

	private boolean ultimoElementoPilaDeSubSesion() {
		return this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
	}

	private Object obtenerObjetoDelElementoPila(int posicion, Class tipoClase) {
		Object objeto = null;
		try {
			objeto = this.getElementoPila().getObjetos().get(posicion);

			if(objeto instanceof SubParcela) {
			} else if(objeto == null) {
				objeto = tipoClase.newInstance();
			}
		} catch(Exception ex) {
		}
		return objeto;
	}

	// private void acomodarSeleccionado() {
	// Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
	// if (seleccionado != null) {
	// int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
	// this.getElementoPila().getObjetos().set(posicion, seleccionado);
	// }
	// }
	//
	// private int getCantidadObjetosUsados() {
	// return this.getElementoPila().getObjetos().size();
	// }
	//
	// private void limpiarTabla() {
	// this.getObjectListDataProvider().getList().clear();
	// }
	//
	// public void limpiarObjeto(int posicion, TextField campo) {
	// try {
	// this.getElementoPila().getObjetos().set(posicion, null);
	// if (campo != null) {
	// campo.setText(null);
	// }
	// } catch (Exception ex) {
	// }
	// }
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar RaddioButton">
	// public String getCurrentRow() {
	// return tableRowGroup1.getRowKey().getRowId();
	// }
	// public void setCurrentRow(int row) {
	// }
	// private Object lastSelected = null;
	//
	// public Object getRBSelected() {
	// String sv = (String) radioButton1.getSelectedValue();
	// return sv.equals(lastSelected) ? sv : null;
	// }
	//
	// public void setRBSelected(Object selected) {
	// if (selected != null) {
	// lastSelected = selected;
	// }
	// }
	//

	private int getNroFila(String pCadena) {
		// Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar el RaddioButton Principal">
	// public String getPrincipalRow() {
	// return tableRowGroup1.getRowKey().getRowId();
	// }
	//
	// public void setPrincipalRow(int row) {
	// }
	// private Object lastPrincipalSelected = null;
	// public Object getRBPrincipalSelected() {
	// //String sv = (String)radioButton2.getSelectedValue();
	// //return sv.equals(lastPrincipalSelected) ? sv : null;
	// return null;
	// }
	// public void setRBPrincipalSelected(Object selected) {
	// if (selected != null) {
	// lastPrincipalSelected = selected;
	// }
	// }
	// public RowKey getPrincipalSeleccionado() {
	// RowKey rk = null;
	// try {
	// String aRowId = (String) RadioButton.getSelected("buttonGroup");
	// // CAMBIAR: Utilizar el ListDataProvider correspondiente
	// rk = this.getLdpSubParcelas().getRowKey(aRowId);
	// } catch (Exception ex) {
	// }
	// return rk;
	// }
	// private Object obtenerPrincipalSeleccionado() {
	// RowKey rk = null;
	// Object ppal = null;
	// try {
	// rk = this.getPrincipalSeleccionado();
	// if (rk != null) {
	// int index = getNroFila(rk.toString());
	// // CAMBIAR: Utilizar el ListDataProvider adecuado.
	// ppal = this.getLdpSubParcelas().getObjects()[index];
	// }
	// } catch (Exception ex) {
	// }
	// return ppal;
	// }
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar la fila recientemente seleccionada">
	// private RowKey rowKeySeleccionado = null;
	//
	// public RowKey getRowKeySeleccionado() {
	// return rowKeySeleccionado;
	// }
	// public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
	// this.rowKeySeleccionado = rowKeySeleccionado;
	// }
	//
	// public void guardarOrdenamiento() {
	// this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	// }
	//
	// public void setearOrdenamiento() {
	// this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	// }
	// public Long getPosicionEnTabla(RowKey rk) {
	// long inicioPagina = 0;
	// long posicionGlobal = 0;
	// long posicionEnPagina = 0;
	// long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
	// long cantRegistros = this.getTableRowGroup1().getRowCount();
	// boolean encontrado = false;
	//
	// if (rk != null) {
	// while (!encontrado && inicioPagina < cantRegistros) {
	// this.getTableRowGroup1().setFirst((int) inicioPagina);
	// posicionEnPagina = 0;
	// while (!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
	// encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
	// if (!encontrado) {
	// posicionEnPagina++;
	// posicionGlobal++;
	// }
	// }
	// if (!encontrado) {
	// inicioPagina += cantRegistrosPorPagina;
	// }
	// }
	// }
	// return new Long(posicionGlobal);
	// }
	// public RowKey getRowKeyAsociado(Long posicionEnTabla) {
	// RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
	// return rk;
	// }
	// public void seleccionarFila(Long posicionGlobal) {
	// long cantFilas = this.getTableRowGroup1().getRowCount();
	// if (cantFilas > 0) {
	// // si hay al menos una fila
	// if (posicionGlobal.longValue() > cantFilas) {
	// // si elimine la ultima fila, me posiciono en la anterior
	// posicionGlobal = new Long(cantFilas);
	// };
	//
	// int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
	// this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
	// lastSelected = new Long(index).toString();
	// }
	// }
	// public void seleccionarFilaPrincipal(Object principal) {
	// // CAMBIAR: Reemplazar por los objetos utilizados en la pagina.
	// int cantFilas = this.getLdpSubParcelas().getList().size();
	// Rol enTabla = null;
	// for (int i = 0; i < cantFilas; i++) {
	// enTabla = (Rol) this.getLdpSubParcelas().getObjects()[i];
	// if (enTabla.getIdRol() == ((Rol) principal).getIdRol()) {
	// lastPrincipalSelected = new Long(i).toString();
	// }
	// }
	// }
	// public Long getInicioPagina(Long posicionGlobal) {
	// long inicioPagina = 0;
	// long posicionEnPagina = 0;
	// long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
	//
	// inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
	// return new Long(inicioPagina);
	// }
	//
	// public RowKey getSeleccionado() {
	// RowKey rk = null;
	// try {
	// String aRowId = (String) RadioButton.getSelected("buttonGroup");
	// // CAMBIAR: Utilizar el ListDataProvider correspondiente
	// rk = this.getLdpSubParcelas().getRowKey(aRowId);
	// } catch (Exception ex) {
	// }
	// return rk;
	// }
	// </editor-fold>
	//
	// private void limpiarObjetosUsados() {
	// // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
	// SubParcela subParcela = (SubParcela) this.obtenerObjetoDelElementoPila(1, SubParcela.class);
	// this.getTfSuperficieSubdivision().setText(null);
	// subParcela.setSuperficie(null);
	//
	// this.limpiarObjeto(3, this.getTfTitularSubdivision());
	//
	// // this.getElementoPila().getObjetos().set(1, subParcela);
	// this.getElementoPila().getObjetos().set(3, null);
	// }
	private TableSelectPhaseListener tablePhaseListener;

	/**
	 * Setter for selected
	 * 
	 * @param object
	 *            Value to set the property to Bind this to the checkbox's selected property If the object's value matches selectedValue then the checkbox is
	 *            considered to be selected.
	 */
	public void setSelected(Object object) {
		RowKey rowKey = tableRowGroup1.getRowKey();
		if(rowKey != null) {
			tablePhaseListener.setSelected(rowKey, object);
		}
	}

	/**
	 * Getter for selected.
	 * 
	 * @return Object value for the current row's checkbox
	 */
	public Object getSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListener.getSelected(rowKey);
	}

	/**
	 * Getter for currentRowSelected
	 * 
	 * @return Boolean true if the checkbox in the current row is selected Bind this property to the row's selected property
	 */
	public boolean isCurrentRowSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		// // ariel
		// if (rowKey != null) {
		return tablePhaseListener.isSelected(rowKey);
		// }
		// else return false;
	}

	/**
	 * Getter for selectedValue
	 * 
	 * @return Object value of the component when it is selected Bind this property to the checkbox's selectedValue property If the object's value matches
	 *         selectedValue then the checkbox is considered to be selected. In this case, when the checkbox's selected property returns its RowKey value, then
	 *         it is considered to be selected.
	 */
	public Object getSelectedValue() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public void limpiarObjeto(int posicion, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, null);
			if(campo != null) {
				campo.setText(null);
			}
		} catch(Exception ex) {
		}
	}

	public String btnAgregarOSP_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Obligacion obligacion = null;
		DocumentoOSP documentoOSP = null;
		PlantillaObligacion plantillaObligacion = null;
		List listaObligacionesOSPNuevas = new ArrayList();
		List listaObligacionesOSPExistentes = new ArrayList();
		List listaSubparcelas = new ArrayList();
		List listaObligacionesOSPSubparcelas = new ArrayList();
		// List listaAux = new ArrayList();
		ServicioOSP servicio = null;
		Boolean crearOSP = true;

		if(ultimo) {
			// obtener el servicio OSP seleccionado.
			System.out.println("selecteddd : " + ddServicioOSP.getSelected());
			Object cod = (Object) ddServicioOSP.getSelected();
			
			FiltroServicioOSP locFiltro = new FiltroServicioOSP();
			locFiltro.setCodigo(cod.toString());
			
			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
				servicio = (ServicioOSP) (this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().findListaServiciosOSP(locFiltro)).getListaResultados().get(0);
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_GenerarServicioOSP:", ex);
				error(NOMBRE_PAGINA + " - No se pudo generar el servicio OSP: " + ex.getMessage());
				ex.printStackTrace();
			}
			System.out.println("serviciooo: " + servicio);

			// crear Doc_Hab osp para cada subparcela para el OSP seleccionado, en caso de que no se haya creado aun.
			listaSubparcelas = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
			listaObligacionesOSPSubparcelas = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

			System.out.println("OBLIGACIONES OSP DE SUBPARCELAS  " + listaObligacionesOSPSubparcelas.size());

			for(Object obj : listaSubparcelas) {
				System.out.println("cantidad de subp: " + listaSubparcelas.size());
				SubParcela subparcela = (SubParcela) obj;
				crearOSP = true;
				DocumentoOSP locDocH = new DocumentoOSP();

				for(Object objOblig : listaObligacionesOSPSubparcelas) {
					Obligacion locObl = (Obligacion) objOblig;
					try {
						this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
						locDocH = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP()
								.getDocumentoOSPPorId(locObl.getDocumentoEspecializado().getIdDocHabilitanteEspecializado());
					} catch(Exception ex) {
						log(CASO_NAVEGACION + "_GenerarObligacion:", ex);
						error(NOMBRE_PAGINA + " - No se pudo generar la Obligaci\363n: " + ex.getMessage());
						ex.printStackTrace();
					}

					// pregunto si no existe ya una obligacion OSP para esa parcela y ese servicio, sino lo creo.
					System.out.println("locDocH.getParcela: " + locDocH.getParcela());
					System.out.println("subparcela: " + subparcela);
					System.out.println("locDocH.getRegistroAlicuota().getCodigo(): " + locDocH.getRegistroAlicuota().getCodigo());
					System.out.println("servicio.getCodigo(): " + servicio.getCodigo());

					if(locDocH.getParcela().equals(subparcela) && locDocH.getRegistroAlicuota().getCodigo().equals(servicio.getCodigo())) {
						System.out.println("entro");
						crearOSP = false;
						/*
						 * si ya tenia obligacion me aseguro que no este anulada asi q la reactivo
						 */

						Obligacion locObligacion = (Obligacion) locDocH.getObligacion();
						// locObligacion.reActivar();
						// ///////////

						listaObligacionesOSPExistentes.add(locDocH);// agrego a la lista la ya existente porque no se va a crear una nueva
						System.out.println("ya tenia osp la sub y serv: " + locDocH.getParcela() + ", osp: " + locDocH.getRegistroAlicuota().getCodigo());
						// si ya esta creada y en la lista no se crea
					}
				}
				// obtengo obligacion para recuperar con ella la plantilla:
				List listaObligacionesOSP = (ArrayList) this.obtenerObjetoDelElementoPila(0, ArrayList.class);
				for(Object object : listaObligacionesOSP) {
					Obligacion locObligacion = (Obligacion) object;
					for(Object obj1 : locObligacion.getDocumentoEspecializado().getListaRegAlicuotas()) {
						RegAlicuota registroAlicuota = (RegAlicuota) obj1;
						if(((ServicioOSP) registroAlicuota).isMedido()) {
							System.out.println("si no es medida");
							listaObligacionesOSP.add(obligacion);
						}
					}
				}

				if(crearOSP) {
					try { // recupero plantilla de la parcela
						this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
						this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().setLlave(this.getSessionBean1().getLlave());
						plantillaObligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().getPlantillaObligacionFromObligacion(obligacion);

						// genero obligacion
						obligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().generarArbol(plantillaObligacion);
						obligacion.setPersona(subparcela.getTitular());
						documentoOSP = (DocumentoOSP) obligacion.getDocumentoEspecializado();
						documentoOSP.setObligacion(obligacion);
					} catch(Exception ex) {
						log(CASO_NAVEGACION + "_GenerarObligacion:", ex);
						error(NOMBRE_PAGINA + " - No se pudo generar la Obligaci\363n: " + ex.getMessage());
						ex.printStackTrace();
					}

					documentoOSP.setFechaInicioActividad(new Date());
					documentoOSP.setNombre("Documento Generado por el Sistema");
					documentoOSP.setParcela(subparcela);// traer del listado de subparcelas
					documentoOSP.getListaRegAlicuotas().add(servicio);

					listaObligacionesOSPNuevas.add(documentoOSP);
				}

			}

			System.out.println("tam new: " + listaObligacionesOSPNuevas.size());
			System.out.println("tam existentes : " + listaObligacionesOSPExistentes.size());

			this.setListaDelCommunication((ArrayList) listaObligacionesOSPNuevas);
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());

			this.setListaDelCommunication2((ArrayList) listaObligacionesOSPExistentes);
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());

			// mostrar lista en tabla y guardar en pila:
			this.getElementoPila().getObjetos().set(5, listaObligacionesOSPNuevas);
			this.getElementoPila().getObjetos().set(6, listaObligacionesOSPExistentes);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnGuardar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		List listaObligacionesOSP = new ArrayList();

		if(ultimo) {

			try {
				this.guardarEstadoObjetosUsados();
				int ind = 0;
				listaObligacionesOSP = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_GuardarError:", ex);
				error(NOMBRE_PAGINA + " - Guardar: " + ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnVolver_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.setListaDelCommunication(null);
			this.setListaDelCommunication2(null);
			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnGenerar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		List listaObligacionesOSPGenerar = new ArrayList();
		List listaObligacionesOSPNuevas = (ArrayList) this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		List listaObligacionesOSPExistentes = (ArrayList) this.obtenerObjetoDelElementoPila(6, ArrayList.class);
		List lista = new ArrayList();
		if(ultimo) {
			RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();
			if(selectedRowKeys != null && selectedRowKeys.length > 0) {
				try {

					this.guardarEstadoObjetosUsados();
					this.getLdpObligacionesOSP().commitChanges();

					for(int i = 0; i < selectedRowKeys.length; i++) {
						String rowId = selectedRowKeys[i].getRowId();
						RowKey rowKey = this.getObjectListDataProvider().getRowKey(rowId);
						Object obj = this.getObjectListDataProvider().getObjects()[getNroFila(rowKey.toString())];
						DocumentoOSP documentoOSP = (DocumentoOSP) obj;
						listaObligacionesOSPGenerar.add(documentoOSP);
					}

					// for (Object object : listaObligacionesOSPGenerar) {
					// DocumentoOSP docOSP = (DocumentoOSP) object;
					// if ((docOSP.getNumeroCuenta() == null || docOSP.getNumeroCuenta() == 0) || (docOSP.getNumeroSubCuenta() == null ||
					// docOSP.getNumeroSubCuenta() == 0) || (docOSP.getCodigoMedidor() == null || docOSP.getCodigoMedidor() == "")) {
					// warn("Debe ingresar el n\372mero de cuenta, n\372mero de subcuenta y c\363digo de medidor.");
					// return null;
					// }
					// }

					for(Object object : listaObligacionesOSPGenerar) {
						DocumentoOSP locDocumentoOSP = (DocumentoOSP) object;

						Obligacion obligacion = (Obligacion) locDocumentoOSP.getObligacion();

						if(obligacion != null) {
							if(obligacion.getIdObligacion() == -1) {
								this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
								this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().addObligacion(obligacion.getPersona(), obligacion);
							}
						}
						// actualizo listas de los ldp:
						listaObligacionesOSPExistentes.add(locDocumentoOSP);
						listaObligacionesOSPNuevas.remove(locDocumentoOSP);
						System.out.println("locDOCUMENTO-OSP---------------- " + locDocumentoOSP.getIdDocHabilitanteEspecializado());
					}

					this.getElementoPila().getObjetos().set(5, listaObligacionesOSPNuevas);
					this.getElementoPila().getObjetos().set(6, listaObligacionesOSPExistentes);

					this.setListaDelCommunication(new ArrayList(listaObligacionesOSPNuevas));
					this.getLdpObligacionesOSP().setList(this.getListaDelCommunication());

					this.setListaDelCommunication2(new ArrayList(listaObligacionesOSPExistentes));
					this.getLdpObligacionesOSPExistentes().setList(this.getListaDelCommunication2());

					this.guardarEstadoObjetosUsados();
					this.obtenerListaObligacionesSubparcela();
					// info("La Obligación OSP se le generar\341 a las Subparcelas.");
					info("Las Obligaciones OSP seleccionadas se han generado exitosamente.");
					this.getTfCodigoMedidor().setText(null);
					this.getTfNumeroCuenta().setText(null);
					this.getTfNumeroSubCuenta().setText(null);
					this.getLdpObligacionesOSP().commitChanges();
					// retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
				} catch(Exception ex) {
					this.guardarEstadoObjetosUsados();
					this.getLdpObligacionesOSP().setList(this.getListaDelCommunication());
					this.getLdpObligacionesOSPExistentes().setList(this.getListaDelCommunication2());
					log(CASO_NAVEGACION + "_GenerarError:", ex);
					error(NOMBRE_PAGINA + " - Generar: " + ex.getMessage());
					ex.printStackTrace();
				}
			} else {
				this.guardarEstadoObjetosUsados();
				warn("Seleccione las Obligaciones que desea generar.");
				return null;
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
}
