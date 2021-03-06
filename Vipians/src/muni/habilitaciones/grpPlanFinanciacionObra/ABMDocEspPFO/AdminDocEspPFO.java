/*
 * AdminDocEspPFO.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpPlanFinanciacionObra.ABMDocEspPFO;

import jasper.ConstantesReportes;

import java.util.ArrayList;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;

// comment for ana

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminDocEspPFO extends AbstractPageBean {

	// CAMBIAR: Ir al dise�o y vincular a campos ocultos.
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
	private final String NOMBRE_PAGINA = "Administraci\363n de Obligaciones PFO";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AdminDocEspPFO";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = true;
	// Links hacia las paginas de agregar/modificar/eliminar
	private final String lnkAgregar = "AgregarDocEspPFO";
	private final String lnkModificar = "ModificarDocEspPFO";
	private final String lnkEliminar = "EliminarDocEspPFO";
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
	private int __placeholder;

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

		this.habilitarBtnExportar();
	}

	protected PanelGroup pgParametros = new PanelGroup();

	public PanelGroup getPgParametros() {
		return pgParametros;
	}

	public void setPgParametros(PanelGroup pgParametros) {
		this.pgParametros = pgParametros;
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

	protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(HtmlAjaxCommandButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Button btnCancelar = new Button();

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button b) {
		this.btnCancelar = b;
	}

	private MessageGroup messageGroup = new MessageGroup();

	public MessageGroup getMessageGroup() {
		return messageGroup;
	}

	public void setMessageGroup(MessageGroup mg) {
		this.messageGroup = mg;
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

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
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

	private Link link1 = new Link();

	public Link getLink1() {
		return link1;
	}

	public void setLink1(Link l) {
		this.link1 = l;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Button btnEliminar = new Button();

	public Button getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(Button b) {
		this.btnEliminar = b;
	}

	private Button btnModificar = new Button();

	public Button getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(Button b) {
		this.btnModificar = b;
	}

	private Button btnAgregar = new Button();

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button b) {
		this.btnAgregar = b;
	}

	private Button btnSeleccionar = new Button();

	public Button getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(Button b) {
		this.btnSeleccionar = b;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private ObjectListDataProvider ldpDocEspPFO = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDocEspPFO() {
		return ldpDocEspPFO;
	}

	public void setLdpDocEspPFO(ObjectListDataProvider oldp) {
		this.ldpDocEspPFO = oldp;
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

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnReiniciar() {
		return btnReiniciar;
	}

	public void setBtnReiniciar(HtmlAjaxCommandButton btnReiniciar) {
		this.btnReiniciar = btnReiniciar;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private StaticText stDocumento = new StaticText();

	public StaticText getStDocumento() {
		return stDocumento;
	}

	public void setStDocumento(StaticText stDocumento) {
		this.stDocumento = stDocumento;
	}

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup pg) {
		this.gpBotones = pg;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfNumeroRegistro = new TextField();

	public TextField getTfNumeroRegistro() {
		return tfNumeroRegistro;
	}

	public void setTfNumeroRegistro(TextField tf) {
		this.tfNumeroRegistro = tf;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private Script scriptFinal1 = new Script();

	public Script getScriptFinal1() {
		return scriptFinal1;
	}

	public void setScriptFinal1(Script s) {
		this.scriptFinal1 = s;
	}

	private Script scriptValidador = new Script();

	public Script getScriptValidador() {
		return scriptValidador;
	}

	public void setScriptValidador(Script scriptValidador) {
		this.scriptValidador = scriptValidador;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private Button btnSeleccionarObra = new Button();

	public Button getBtnSeleccionarObra() {
		return btnSeleccionarObra;
	}

	public void setBtnSeleccionarObra(Button b) {
		this.btnSeleccionarObra = b;
	}

	private TextField tfObra = new TextField();

	public TextField getTfObra() {
		return tfObra;
	}

	public void setTfObra(TextField tf) {
		this.tfObra = tf;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	private Button btnExportar = new Button();

	public Button getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(Button btnExportar) {
		this.btnExportar = btnExportar;
	}

	private Button btnImprimirReporte = new Button();

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private Button btnConsultar = new Button();

	public Button getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(Button b) {
		this.btnConsultar = b;
	}

	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

	private HtmlAjaxCommandButton btnLimpiarObra = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public HtmlAjaxCommandButton getBtnLimpiarObra() {
		return btnLimpiarObra;
	}

	public void setBtnLimpiarObra(HtmlAjaxCommandButton btnLimpiarObra) {
		this.btnLimpiarObra = btnLimpiarObra;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminDocEspPFO() {
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
	 * Devolver una referencia al bean de datos con ?mbito.
	 * </p>
	 */
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ?mbito.
	 * </p>
	 */
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ?mbito.
	 * </p>
	 */
	protected muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ?mbito.
	 * </p>
	 */
	protected muni.RequestBean1 getRequestBean1() {
		return (muni.RequestBean1) getBean("RequestBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ?mbito.
	 * </p>
	 */
	protected muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>
	 * M?todo de devoluci?n de llamada al que se llama cuando se navega hasta esta p?gina, ya sea directamente mediante un URL o de manera indirecta a trav?s de
	 * la navegaci?n de p?ginas. Puede personalizar este m?todo para adquirir recursos que se necesitar?n para los controladores de eventos y m?todos del
	 * proceso, sin tener en cuenta si esta p?gina realiza procesamiento de devoluci?n de env?os.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petici?n actual es una devoluci?n de env?o, los valores de propiedad de los componentes <strong>no</strong> representan ning?n
	 * valor enviado con esta petici?n. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se proces?.
	 * </p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicaci?n que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar c?digo de inicio propio aqu?

		// <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom?ticamente
		// *Nota* - esta l?gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("AdminDocEspPFO Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicaci?n que debe finalizar
		// *despu?s* de que se inicien los componentes administrados
		// TODO - Agregar c?digo de inicio propio aqu?

	}

	/**
	 * <p>
	 * M?todo de devoluci?n de llamada al que se llama cuando el ?rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m?todo <strong>s?lo</strong> se llamar? en una petici?n de devoluci?n de env?o que est? procesando el env?o de un formulario.
	 * Puede personalizar este m?todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	@Override
	public void preprocess() {
	}

	/**
	 * <p>
	 * M?todo de devoluci?n de llamada al que se llama justo antes del procesamiento. <strong>S?lo</strong> se llamar? a este m?todo en la p?gina que se
	 * procesa, no se llamar?, por ejemplo, en una p?gina que ha procesado una devoluci?n de env?o y a continuaci?n ha navegado hasta otra p?gina. Puede
	 * personalizar este m?todo para asignar recursos necesarios para procesar esta p?gina.
	 * </p>
	 */
	@Override
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

		// 1. Pagina nueva - Inicio de transaccion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if(this.PUEDE_SER_PAGINA_INICIAL) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();
			}
		}

		// 2. Recarga de la misma pagina por validacion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// APLICAR LOGICA AQUI.. ver si es as? realmente..
		}

		// 3. Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if(existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();
		}

		// 4. Pagina nueva - hacia atras en la transaccion
		if(existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());

			if(ep.getOrdenamiento() != null) {
				this.setearOrdenamiento();
				this.getElementoPila().setOrdenamiento(null);
			}

			// if (this.getListaDelCommunication() != null &&
			// this.getListaDelCommunication().size() > 0) {
			// try { this.refrescarTabla(); } catch (Exception ex) { this.limpiarTabla(); }
			// }
			if(this.getListaDelCommunication() != null
					&& this.getListaDelCommunication().size() > 0
					&& this.getRequestBean1().getAccion() != null
					&& (this.getRequestBean1().getAccion().equals(Constantes.ACCION_AGREGAR) || this.getRequestBean1().getAccion().equals(Constantes.ACCION_MODIFICAR) || this
							.getRequestBean1().getAccion().equals(Constantes.ACCION_ELIMINAR))) {
				try {
					this.refrescarTabla();
				} catch(Exception ex) {
					this.limpiarTabla();
				}
			}

		}
		if(!recarga) {
			this.mostrarEstadoObjetosUsados();
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1());

		this.habilitarBtnExportar();
	}

	/**
	 * <p>
	 * M?todo de devoluci?n de llamada al que se llama cuando se completa el procesamiento de esta petici?n, si se llam? al m?todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p?gina que se ha procesado o no). Puede personalizar este m?todo para liberar los recursos adquiridos en los m?todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci?n de un controlador de eventos).
	 * </p>
	 */
	@Override
	public void destroy() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, null); // 0 numero de registro de parcela
		ep.getObjetos().add(ind++, new Obra());// 1

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		Integer numeroRegistroEP = (Integer) this.obtenerObjetoDelElementoPila(0, Integer.class);
		Obra obra = (Obra) this.obtenerObjetoDelElementoPila(1, Obra.class);

		Object numeroRegistro = this.getTfNumeroRegistro().getText();

		if(numeroRegistro != null && numeroRegistro != "") {
			numeroRegistroEP = Conversor.getIntegerDeString(numeroRegistro.toString());
		} else {
			numeroRegistroEP = null;
		}

		this.getElementoPila().getObjetos().set(0, numeroRegistroEP);
		this.getElementoPila().getObjetos().set(1, obra);
	}

	private void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Persona persona = null;
		Integer numeroRegistroEP = null;
		Obra obra = null;

		if(this.getRequestBean1().getObjetoSeleccion() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
			if(seleccionado instanceof Persona || seleccionado instanceof Obra) {
				this.limpiarTabla();
			}
			if(seleccionado instanceof Persona) {
				persona = (Persona) seleccionado;
				this.getSessionBean1().setPersonaSeleccionada(persona);
				this.getRequestBean1().setObjetoSeleccion(null);
			}
		}

		this.acomodarSeleccionado();

		persona = this.getSessionBean1().getPersonaSeleccionada();
		numeroRegistroEP = (Integer) this.obtenerObjetoDelElementoPila(0, Integer.class);
		obra = (Obra) this.obtenerObjetoDelElementoPila(1, Obra.class);

		if(persona != null) {
			this.getTfPersona().setText(persona.toString());
		} else {
			this.getTfPersona().setText("");
		}
		if(numeroRegistroEP != null) {
			this.getTfNumeroRegistro().setText(numeroRegistroEP.toString());
		}
		this.getTfObra().setText(obra.toString());

		if(this.getLdpDocEspPFO().getList() != null) {
			Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
			System.out.println("filaSeleccionada :" + filaSeleccionada);
			this.seleccionarFila(filaSeleccionada);
		}
	}

	private void refrescarTabla() throws Exception {
		// CAMBIAR: Segun objeto de busqueda.
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		Obra obra = (Obra) this.obtenerObjetoDelElementoPila(1, Obra.class);
		Integer numeroRegistroEP = (Integer) this.obtenerObjetoDelElementoPila(0, Integer.class);

		// mines: FIJATE ACAA
		if(persona != null) {
			if(persona.getIdPersona() == -1) {
				persona = null;
			}
		}
		// if (persona.getIdPersona() == -1) persona = null;
		if(obra.getIdObra() == -1) {
			obra = null;
		}

		// CAMBIAR: Utilizar el EJBClient adecuado.
		this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
		this.setListaDelCommunication((ArrayList) this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesPFO(persona, numeroRegistroEP, obra));

		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		this.setRBSelected((new Long(0)).toString());
	}

	private void limpiarObjetosUsados() {
		for(int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
			this.getElementoPila().getObjetos().set(i, null);
		}

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().setParcelaSeleccionada(null);
		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfPersona().setText(null);
		this.getTfNumeroRegistro().setText(null);
		this.getTfObra().setText(null);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpDocEspPFO();
	}

	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaDocEspPFO();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaDocEspPFO(lista);
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
		} else {
			retorno = CASO_NAV_POST_CADUCIDAD;
		}
		return retorno;
	}

	private String prepararCaducidad() {
		// redireccionar a pagina con mensaje de caducidad
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
			if(objeto == null && !tipoClase.getName().equals("java.lang.Integer")) {
				objeto = tipoClase.newInstance();
			}
		} catch(Exception ex) {
		}
		return objeto;
	}

	private void acomodarSeleccionado() {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if(seleccionado != null) {
			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, seleccionado);
		}
	}

	private int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
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

	private void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
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
		if(selected != null) {
			lastSelected = selected;
		}
	}

	private int getNroFila(String pCadena) {
		// Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
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

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		lastSelected = posicionGlobal.toString();
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
		} catch(Exception ex) {
		}
		return rk;
	}

	public String btnSeleccionarPersonaFisica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		// int posicionObjetoSeleccionado = 0;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			try {
				RowKey rk = this.getSeleccionado();
				if(rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch(Exception ex) {
				// CAMBIAR:
				log(CASO_NAVEGACION + "_SeleccionarPersonaFisicaError:", ex);
				error(NOMBRE_PAGINA + " - Seleccionar Persona F\355sica: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			// this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarObra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 1;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			try {
				RowKey rk = this.getSeleccionado();
				if(rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch(Exception ex) {
				// CAMBIAR:
				log(CASO_NAVEGACION + "_SeleccionarObraError:", ex);
				error(NOMBRE_PAGINA + " - Seleccionar Obra: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminObra";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnBuscar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {
				this.guardarEstadoObjetosUsados();

				Object pf = this.getTfPersona().getText();
				Object numReg = this.getTfNumeroRegistro().getText();
				Object obra = this.getTfObra().getText();

				if((pf == null || pf.toString().length() == 0) && (numReg == null || numReg.toString().length() == 0) && (obra == null || obra.toString().length() == 0)) {
					warn("Debe completar al menos uno de los campos para realizar la b\372squeda.");
					return null;
				}

				this.refrescarTabla();
				this.guardarOrdenamiento();
				Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
				this.getElementoPila().setPosicionGlobal(pos.longValue());

			} catch(Exception ex) {
				this.limpiarTabla();
				log(CASO_NAVEGACION + "_BuscarError:", ex);
				error(NOMBRE_PAGINA + " - Buscar: " + ex.getMessage());
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnReiniciar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.limpiarObjetosUsados();
				this.limpiarTabla();
			} catch(Exception ex) {
				this.limpiarTabla();
				log(CASO_NAVEGACION + "_ReiniciarError:", ex);
				error(NOMBRE_PAGINA + " - Reiniciar: " + ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnCancelar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.setListaDelCommunication(null);
			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					getRequestBean1().setObjetoSeleccion(obj);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_SeleccionarError:", ex);
				error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
			}

			if(rk != null) {
				ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
				if(locElementoAnterior == null) {
					return null;
				}
				retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			retorno = lnkAgregar;
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnModificar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Obligacion obligacion = (Obligacion) obj;
					if(obligacion.getEstado().equals(Estado.ANULADO)) {
						warn("Las obligaciones ANULADAS no pueden modificarse");
						return null;
					}
					// paso la obligacion
					this.getRequestBean1().setObjetoABM(obj);
					this.setRowKeySeleccionado(this.getSeleccionado());

					// // Tambien debe seleccionar una persona:
					// PersonaFisica personaF = (PersonaFisica) this.obtenerObjetoDelElementoPila(0, PersonaFisica.class);
					// Object objPersona = null;
					// if (personaF.getIdPersonaFisica() != -1) objPersona = personaF;
					//
					// if (objPersona == null) {
					// warn("Debe seleccionar una Persona para poder modificar una Obligaci\363n.");
					// return null;
					// }
				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_ModificarError:", ex);
				error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = lnkModificar;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnEliminar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					getRequestBean1().setObjetoABM(obj);
					Obligacion obligacion = (Obligacion) obj;
					if(obligacion.getEstado().equals(Estado.ANULADO)) {
						warn("Las obligaciones ANULADAS no pueden volver a anularse");
						return null;
					}
					// Caso especial de las obligaciones.
					Object objetoABM = obligacion.getDocumentoEspecializado();

					this.setRowKeySeleccionado(this.getSeleccionado());
					this.refrescarTabla();
				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_EliminarError:", ex);
				error(NOMBRE_PAGINA + " - Eliminar: " + ex.getMessage());

			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = lnkEliminar;
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarPersonaJuridica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 0;

		if(ultimo) {

			try {
				RowKey rk = this.getSeleccionado();
				if(rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch(Exception ex) {
				// CAMBIAR:
				log(CASO_NAVEGACION + "_SeleccionarPersonaFisicaError:", ex);
				error(NOMBRE_PAGINA + " - Seleccionar Persona F\355sica: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminPersonaJuridica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnImprimirReporte_action() {
		// TODO: Process the button click action. Return value is a navigation
		// case name where null will return to the same page.
		return null;
	}

	/*
	 * Mines: boton Consultar....
	 */
	public String btnConsultar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Obligacion obligacion = (Obligacion) obj;
					this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
					obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(obligacion.getIdObligacion());
					getRequestBean1().setObjetoABM(obligacion);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_ConsultarError:", ex);
				error(NOMBRE_PAGINA + " - Consultar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = "ConsultarDocEspPFO";
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(0, this.getTfPersona());

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarObra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(2, this.getTfObra());

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnExportar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(),
						"Reporte Din\341mico de Obligaciones: Planes de Financiaci\363n de Obras");

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_ObligacionesPlanesFinanciaciónObras");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

			} catch(Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log(CASO_NAVEGACION + "_ReporteDinamicoError: ", e);
				error(NOMBRE_PAGINA + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	// Metodo para habilitar el boton exportar cuando corresponda
	public void habilitarBtnExportar() {
		this.btnExportar.setDisabled(true);
		if(getListaDelCommunication() != null) {
			if(getListaDelCommunication().isEmpty()) {
				this.btnExportar.setDisabled(true);
			} else {
				this.btnExportar.setDisabled(false);
			}
		}
	}
}