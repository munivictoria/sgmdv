/*
 * AgregarLineaOrdenCompra.java
 *
 * Created on 1 de noviembre de 2006, 08:32
 * Copyright Trascender
 */
package muni.compras.ABMOrdenCompra;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.convert.NumberConverter;

import muni.CommunicationMesaEntradaBean;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
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
import com.sun.rave.web.ui.component.Tree;
import com.sun.rave.web.ui.component.TreeNode;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class AgregarLineaOrdenCompra extends AbstractPageBean {
	// <editor-fold defaultstate="collapsed"
	// desc="Creator-managed Component Definition">

	private int __placeholder;

	// CAMBIAR: Constantes que varian segun la pagina.
	// cantidad de objetos administrados por la pagina
	// private final int CANTIDAD_OBJETOS = 2;
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Agregar Linea Orden de Compra";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AgregarLineaOrdenCompra";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;
	// CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
	private static long idLineaOrdenCompraTemp = -1;
	private Page page1 = new Page();
	private Long idPagina = null;
	private Long idSubSesion = null;
	private Html html1 = new Html();
	private Head head1 = new Head();
	private Body body1 = new Body();
	private Form form1 = new Form();
	private MessageGroup messageGroup = new MessageGroup();
	private HiddenField hidIdPagina = new HiddenField();
	private HiddenField hidIdSubSesion = new HiddenField();
	private Link link1 = new Link();

	private StaticText stCantidadRegistros = new StaticText();
	private StaticText stTitulo = new StaticText();
	private StaticText stProveedor = new StaticText();
	private StaticText stTotal = new StaticText();
	private StaticText stTotal_1 = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stArea = new StaticText();
	private StaticText stNumeroSolicitud;
	private StaticText stBienAsociado = new StaticText();
	private StaticText stCantidad = new StaticText();
	private StaticText stSeparador = new StaticText();

	private Label lblEncontrados = new Label();
	private Label lblProveedor = new Label();
	private Label lblMonto = new Label();
	private Label lblTexto1 = new Label();

	private Button btnCancelar = new Button();
	private Button btnAceptar = new Button();

	private Table table1 = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	private TextField tfCuenta = new TextField();
	private TextField tfMonto = new TextField();
	private TextField tfMontoTotalLinea = new TextField();
	private TextField tfMontoUnit = new TextField();
	private TextField tfCantidadBien = new TextField();
	private TextField tfProveedor = new TextField();

	private TableColumn tcArea = new TableColumn();
	private TableColumn tcNumeroSolicitud = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();
	private TableColumn tcBienAsociado = new TableColumn();
	private TableColumn tcCantidad = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();

	private RadioButton radioButton1 = new RadioButton();
	private NumberConverter numberConverter1 = new NumberConverter();
	private Script scriptFinal1 = new Script();
	private Script scriptValidador = new Script();
	private Checkbox checkbox1 = new Checkbox();
	private PanelGroup groupPanel1 = new PanelGroup();
	private ObjectListDataProvider ldpLineasSoSuministro = new ObjectListDataProvider();

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong>
	 * This method is automatically generated, so any user-specified code
	 * inserted here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {

		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		numberConverter1.setPattern("$ #,##0.00");
		numberConverter1.setMinIntegerDigits(1);
		numberConverter1.setMaxIntegerDigits(40);
		numberConverter1.setMaxFractionDigits(3);

	}

	public Page getPage1() {
		return page1;
	}

	public void setPage1(Page p) {
		this.page1 = p;
	}

	public Html getHtml1() {
		return html1;
	}

	public void setHtml1(Html h) {
		this.html1 = h;
	}

	public Head getHead1() {
		return head1;
	}

	public void setHead1(Head h) {
		this.head1 = h;
	}

	public Body getBody1() {
		return body1;
	}

	public void setBody1(Body b) {
		this.body1 = b;
	}

	public Form getForm1() {
		return form1;
	}

	public void setForm1(Form f) {
		this.form1 = f;
	}

	public MessageGroup getMessageGroup() {
		return messageGroup;
	}

	public void setMessageGroup(MessageGroup mg) {
		this.messageGroup = mg;
	}

	public HiddenField getHidIdPagina() {
		return hidIdPagina;
	}

	public void setHidIdPagina(HiddenField hf) {
		this.hidIdPagina = hf;
	}

	public HiddenField getHidIdSubSesion() {
		return hidIdSubSesion;
	}

	public void setHidIdSubSesion(HiddenField hf) {
		this.hidIdSubSesion = hf;
	}

	public Link getLink1() {
		return link1;
	}

	public void setLink1(Link l) {
		this.link1 = l;
	}

	public StaticText getStProveedor() {
		return stProveedor;
	}

	public void setStProveedor(StaticText stProveedor) {
		this.stProveedor = stProveedor;
	}

	public StaticText getStCantidadRegistros() {
		return stCantidadRegistros;
	}

	public void setStCantidadRegistros(StaticText st) {
		this.stCantidadRegistros = st;
	}

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	public Label getLblTexto1() {
		return lblTexto1;
	}

	public void setLblTexto1(Label lblTexto1) {
		this.lblTexto1 = lblTexto1;
	}

	public Label getLblMonto() {
		return lblMonto;
	}

	public void setLblMonto(Label lblMonto) {
		this.lblMonto = lblMonto;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public Label getLblEncontrados() {
		return lblEncontrados;
	}

	public void setLblEncontrados(Label l) {
		this.lblEncontrados = l;
	}

	public Button getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(Button btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button b) {
		this.btnCancelar = b;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
	// Atributos propios de la pagina.
	// CAMBIAR: Vincular a campos ocultos.

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

	// CAMBIAR: Objetos administrados por la pagina.
	// Generar getters y setters.
	// En el getter poner:
	// if (this.objeto == null) this.objeto = new Objeto();
	// Ariel
	// private Calle calleABuscar = null;
	// private TipoCalle tipoCalleSeleccionado = null;
	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	public TextField getTfMontoUnit() {
		return tfMontoUnit;
	}

	public void setTfMontoUnit(TextField tfMontoUnit) {
		this.tfMontoUnit = tfMontoUnit;
	}

	public TextField getTfMontoTotalLinea() {
		return tfMontoTotalLinea;
	}

	public void setTfMontoTotalLinea(TextField tfMontoTotalLinea) {
		this.tfMontoTotalLinea = tfMontoTotalLinea;
	}

	public TextField getTfCantidadBien() {
		return tfCantidadBien;
	}

	public void setTfCantidadBien(TextField tfCantidadBien) {
		this.tfCantidadBien = tfCantidadBien;
	}

	public TextField getTfCuenta() {
		return tfCuenta;
	}

	public void setTfCuenta(TextField tfCuenta) {
		this.tfCuenta = tfCuenta;
	}

	public TextField getTfMonto() {
		return tfMonto;
	}

	public void setTfMonto(TextField tfMonto) {
		this.tfMonto = tfMonto;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public StaticText getStTotal_1() {
		return stTotal_1;
	}

	public void setStTotal_1(StaticText stTotal_1) {
		this.stTotal_1 = stTotal_1;
	}

	public StaticText getStTotal() {
		return stTotal;
	}

	public void setStTotal(StaticText stTotal) {
		this.stTotal = stTotal;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public TableSelectPhaseListener getTablePhaseListener() {
		return tablePhaseListener;
	}

	public void setTablePhaseListener(TableSelectPhaseListener tablePhaseListener) {
		this.tablePhaseListener = tablePhaseListener;
	}

	public ObjectListDataProvider getLdpLineasSoSuministro() {
		return ldpLineasSoSuministro;
	}

	public void setLdpLineasSoSuministro(ObjectListDataProvider oldp) {
		this.ldpLineasSoSuministro = oldp;
	}

	public TableColumn getTcNumeroSolicitud() {
		return tcNumeroSolicitud;
	}

	public void setTcNumeroSolicitud(TableColumn tcNumeroSolicitud) {
		this.tcNumeroSolicitud = tcNumeroSolicitud;
	}

	public TableColumn getTcArea() {
		return tcArea;
	}

	public void setTcArea(TableColumn tcArea) {
		this.tcArea = tcArea;
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

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	public StaticText getStBienAsociado() {
		return stBienAsociado;
	}

	public void setStBienAsociado(StaticText stBienAsociado) {
		this.stBienAsociado = stBienAsociado;
	}

	public TableColumn getTcBienAsociado() {
		return tcBienAsociado;
	}

	public void setTcBienAsociado(TableColumn tcBienAsociado) {
		this.tcBienAsociado = tcBienAsociado;
	}

	public StaticText getStNumeroSolicitud() {
		return stNumeroSolicitud;
	}

	public void setStNumeroSolicitud(StaticText stNumeroSolicitud) {
		this.stNumeroSolicitud = stNumeroSolicitud;
	}

	public StaticText getStArea() {
		return stArea;
	}

	public void setStArea(StaticText stArea) {
		this.stArea = stArea;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public StaticText getStSeparador() {
		return stSeparador;
	}

	public void setStSeparador(StaticText stSeparador) {
		this.stSeparador = stSeparador;
	}

	public StaticText getStCantidad() {
		return stCantidad;
	}

	public void setStCantidad(StaticText stCantidad) {
		this.stCantidad = stCantidad;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tf) {
		this.tfProveedor = tf;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public Script getScriptFinal1() {
		return scriptFinal1;
	}

	public void setScriptFinal1(Script s) {
		this.scriptFinal1 = s;
	}

	public Script getScriptValidador() {
		return scriptValidador;
	}

	public void setScriptValidador(Script scriptValidador) {
		this.scriptValidador = scriptValidador;
	}

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox checkbox1) {
		this.checkbox1 = checkbox1;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AgregarLineaOrdenCompra() {
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
	 * Devolver una referencia al bean de datos con ámbito.
	 * </p>
	 */
	protected muni.CommunicationContabilidadBean getCommunicationContabilidadBean() {
		return (muni.CommunicationContabilidadBean) getBean("CommunicationContabilidadBean");
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
	protected muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1) getBean("ApplicationBean1");
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
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
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
	 * M�todo de devoluci�n de llamada al que se llama cuando se navega hasta
	 * esta p�gina, ya sea directamente mediante un URL o de manera indirecta a
	 * trav�s de la navegaci�n de p�ginas. Puede personalizar este m�todo para
	 * adquirir recursos que se necesitar�n para los controladores de eventos y
	 * m�todos del proceso, sin tener en cuenta si esta p�gina realiza
	 * procesamiento de devoluci�n de env�os.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los
	 * valores de propiedad de los componentes <strong>no</strong> representan
	 * ning�n valor enviado con esta petici�n. En su lugar, representan los
	 * valores de propiedades que se guardaron para esta vista cuando se
	 * proces�.
	 * </p>
	 */
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicaci�n que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		// <editor-fold defaultstate="collapsed"
		// desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();

		} catch (Exception e) {
			log("AdminLineaOrdenCompra Initialization Failure", e);
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
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de
	 * componentes se ha restaurado, pero antes de que se produzca el
	 * procesamiento de cualquier evento. Este m�todo <strong>s�lo</strong> se
	 * llamar� en una petici�n de devoluci�n de env�o que est� procesando el
	 * env�o de un formulario. Puede personalizar este m�todo para asignar
	 * recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del
	 * procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la
	 * p�gina que se procesa, no se llamar�, por ejemplo, en una p�gina que ha
	 * procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra
	 * p�gina. Puede personalizar este m�todo para asignar recursos necesarios
	 * para procesar esta p�gina.
	 * </p>
	 */
	public void prerender() {

		boolean existeIdSubSesionReq = false;
		boolean existeIdPaginaReq = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag = false;
		boolean recarga = false;

		if (this.getRequestBean1() != null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag = this.getIdPagina() != null;

		// 1. Pagina nueva - Inicio de transaccion
		if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if (this.PUEDE_SER_PAGINA_INICIAL) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();
			}
		}

		// 2. Recarga de la misma pagina por validacion
		if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// APLICAR LOGICA AQUI.. ver si es as� realmente..
		}

		// 3. Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();
		}

		// 4. Pagina nueva - hacia atras en la transaccion
		if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());

			if (ep.getOrdenamiento() != null) {
				this.setearOrdenamiento();
				this.getElementoPila().setOrdenamiento(null);
			}

			if (this.getListaDelCommunication() != null
					&& this.getListaDelCommunication().size() > 0
					&& this.getRequestBean1().getAccion() != null
					&& (this.getRequestBean1().getAccion().equals(Constantes.ACCION_AGREGAR)
							|| this.getRequestBean1().getAccion().equals(Constantes.ACCION_MODIFICAR) || this.getRequestBean1().getAccion()
							.equals(Constantes.ACCION_ELIMINAR))) {
				// try {
				// this.refrescarTabla();
				// } catch (Exception ex) {
				// this.limpiarTabla();
				// }
			}

		}
		if (!recarga) {
			this.mostrarEstadoObjetosUsados();
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1());
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el
	 * procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code>
	 * (sin tener en cuenta si se trata de la p�gina que se ha procesado o no).
	 * Puede personalizar este m�todo para liberar los recursos adquiridos en
	 * los m�todos <code>init()</code>, <code>preprocess()</code> o
	 * <code>prerender()</code> (o durante la ejecuci�n de un controlador de
	 * eventos).
	 * </p>
	 */
	public void destroy() {
	}

	// <editor-fold defaultstate="collapsed"
	// desc="Metodos para seleccionar RaddioButton">
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

	private int getNroFila(String pCadena) {
		// Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed"
	// desc="Metodos para seleccionar la fila recientemente seleccionada">
	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void limpiarObjeto(int posicion, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, null);
			if (campo != null) {
				campo.setText(null);
			}
		} catch (Exception ex) {
		}
	}

	private int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
	}

	private void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
	}

	public void guardarOrdenamiento() {
		// Juan Pablo
		// this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion()).setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		// Juan Pablo
		// ElementoPila ep =
		// this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
		// this.getTableRowGroup1().setSortCriteria((SortCriteria[])
		// ep.getOrdenamiento());
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	/*
	 * public void setearCantidadFilasPorPagina() { int cantidadFilas = 10;
	 * this.getTableRowGroup1().setRows(cantidadFilas); }
	 */

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
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	public ArrayList getSeleccionados() {
		ArrayList listaRk = null;
		String rk = null;
		try {
			ArrayList aRowId = (ArrayList) Checkbox.getSelected("buttonsGroup");

			rk = (String) aRowId.get(0);

			for (int i = 0; i < aRowId.size(); i++) {
				listaRk.add(this.getObjectListDataProvider().getRowKeys(i, (RowKey) aRowId.get(i)));
			}
			// RowKey(aRowId);
		} catch (Exception ex) {
		}
		return listaRk;
	}

	// </editor-fold>
	// Mines : agregaste esto para la zonificacion
	private TableSelectPhaseListener tablePhaseListener;

	/**
	 * Setter for selected
	 * 
	 * @param object
	 *            Value to set the property to Bind this to the checkbox's
	 *            selected property If the object's value matches selectedValue
	 *            then the checkbox is considered to be selected.
	 */
	public void setSelected(Object object) {
		RowKey rowKey = tableRowGroup1.getRowKey();
		if (rowKey != null) {
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
	 * Getter for selectedValue
	 * 
	 * @return Object value of the component when it is selected Bind this
	 *         property to the checkbox's selectedValue property If the object's
	 *         value matches selectedValue then the checkbox is considered to be
	 *         selected. In this case, when the checkbox's selected property
	 *         returns its RowKey value, then it is considered to be selected.
	 */
	public Object getSelectedValue() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	/**
	 * Getter for currentRowSelected
	 * 
	 * @return Boolean true if the checkbox in the current row is selected Bind
	 *         this property to the row's selected property
	 */
	public boolean isCurrentRowSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		// // ariel
		// if (rowKey != null) {
		return tablePhaseListener.isSelected(rowKey);
		// }
		// else return false;
	}

	// hasta aca Mines
	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina

		ep.getObjetos().add(ind++, new Proveedor());// 1
		ep.getObjetos().add(ind++, new ArrayList());// 2 listado de lineas de
													// solicitud!
		ep.getObjetos().add(ind++, new OrdenCompra()); // 3 OC

		// tipo de seleccion multipla // simple
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	private void guardarEstadoObjetosUsados() {
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);
		ArrayList listaLineasSoSuministro = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
		OrdenCompra locOrdenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(2, OrdenCompra.class);

		LineaOrdenCompra lineaOrdenCompra = null;
		boolean validoLineaOCRepetida = false;
		boolean mjeOCRepetida = false;

		Double totalLinea = null;

		// if (!listaLineasSoSuministro.isEmpty()) {
		// if (listaLineasSoSuministro != null) {
		// for (int i = 0; i < listaLineasSoSuministro.size(); i++) {
		// lineaOrdenCompra = new LineaOrdenCompra();
		// LineaSolicitudSuministro locLineaSS = (LineaSolicitudSuministro)
		// listaLineasSoSuministro.get(i);
		//
		//
		// for (Iterator it = listaLineasOrdenCompra.iterator(); it.hasNext();)
		// {
		// LineaOrdenCompra locLineaOC = (LineaOrdenCompra)it.next();
		// if (locLineaSS.equals(locLineaOC.getLineaSolicitudSuministro())){
		// validoLineaOCRepetida = true;
		// mjeOCRepetida = true;
		// }
		// }
		//
		// if (locOrdenCompra.getListaLineaOrdenCompra() != null &&
		// !locOrdenCompra.getListaLineaOrdenCompra().isEmpty()){
		// for (Iterator it =
		// locOrdenCompra.getListaLineaOrdenCompra().iterator(); it.hasNext();)
		// {
		// LineaOrdenCompra locLineaOC = (LineaOrdenCompra)it.next();
		// if (locLineaSS.equals(locLineaOC.getLineaSolicitudSuministro())){
		// validoLineaOCRepetida = true;
		// mjeOCRepetida = true;
		// }
		// }
		// }
		//
		// if (!validoLineaOCRepetida){
		// //seteamos un idTemporal a la linea de orden de compra
		// lineaOrdenCompra.setIdLineaOrdenCompra(idLineaOrdenCompraTemp--);
		//
		// lineaOrdenCompra.setLineaSolicitudSuministro(locLineaSS);
		//
		// if (bienProvisto != null) {
		// lineaOrdenCompra.setBienProvisto(bienProvisto);
		// } else {
		// lineaOrdenCompra.setBienProvisto(null);
		// }
		//
		// /*lineaOrdenCompra.setMontoUnitario(locLineaSS.getValorEstimado());
		// lineaOrdenCompra.setCantidad (new Double(locLineaSS.getCantidad()));
		//
		// if ((new Double(locLineaSS.getCantidad())) != null &&
		// locLineaSS.getValorEstimado() != null) {
		// totalLinea = new Double (locLineaSS.getCantidad()*
		// locLineaSS.getValorEstimado().doubleValue());
		// lineaOrdenCompra.setMontoTotal(totalLinea);
		// }
		//
		// lineaOrdenCompra.setCuentaRfr(locLineaSS.getCuentaRfr());*/
		//
		// listaLineasOrdenCompra.add(lineaOrdenCompra);
		// locLineaSS.setLineaOrdenCompra(lineaOrdenCompra);
		// }
		// validoLineaOCRepetida = false;
		// }
		// if (mjeOCRepetida){
		// warn("Una o m\341s l\355neas seleccionadas ya se encuentran en la lista de l\355neas de Orden de Compra.");
		// }
		//
		// }
		// listaLineasSoSuministro.clear();
		// }
		// this.getLdpLineasSoSuministro().commitChanges();

		this.getElementoPila().getObjetos().set(0, proveedor);
		this.getElementoPila().getObjetos().set(1, listaLineasSoSuministro);
	}

	private void mostrarEstadoObjetosUsados() {

		Proveedor proveedor = null;
		ArrayList listaLineasSoSuministro;

		double cantBienes = 0.0;

		if (this.getRequestBean1().getObjetoABM() != null) {
			Object objetoAbm = this.getRequestBean1().getObjetoABM();

			if (objetoAbm instanceof Proveedor) {
				proveedor = (Proveedor) objetoAbm;
				System.out.println(proveedor);
				this.getElementoPila().getObjetos().set(0, proveedor);
				try {
					this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
					// List locListaLineas =
					// this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().findListaLineasSolicitudSuministro(proveedor);
					// this.getElementoPila().getObjetos().set(1,
					// locListaLineas);
				} catch (Exception ex) {
				}
			}
		}

		proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);
		listaLineasSoSuministro = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);

		if (proveedor != null) {
			this.getStProveedor().setText(proveedor.toString());
		}

		this.getObjectListDataProvider().setList(listaLineasSoSuministro);
		this.setListaDelCommunication(listaLineasSoSuministro);

	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLineasSoSuministro();
	}

	private List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaLineasSoLSuministro();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaLineasSoLSuministro(lista);
	}

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		// CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que
		// corresponda
		return this.getCommunicationComprasBean().getTablePhaseListenerLineaOrdenCompra();
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Metodos estaticos de la pagina">
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
		if (locElementoAnterior != null) {
			this.getSessionBean1().getMgrPilas().removeElemento(this.getElementoPila());
			this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
			this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
			this.getRequestBean1().setElementoPilaPaginaAnt(locElementoAnterior);
			retorno = locElementoAnterior.getCasoNavegacion();
			this.getTableSelectPhaseListener().clear();
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
			if (objeto == null) {
				objeto = tipoClase.newInstance();
			}
		} catch (Exception ex) {
		}
		return objeto;
	}

	// </editor-fold>

	public TreeNode getNodoSeleccionado(Tree tr, String idCompleto) {
		TreeNode selectedNode = null;
		if (idCompleto != null) {
			String idNodo = this.getIdSinPrefijo(idCompleto, tr.getId());
			selectedNode = (TreeNode) tr.findComponent(idNodo);
		}
		return selectedNode;
	}

	private String getIdSinPrefijo(String idCompleto, String idComponente) {
		String retorno = null;
		if (idCompleto != null && idCompleto.length() > 0) {
			retorno = idCompleto.substring(idCompleto.indexOf(idComponente) + idComponente.length() + 1);
		}
		return retorno;
	}

	public String btnCancelar_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			retorno = this.prepararParaVolver(retorno);
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	/**
	 * <p>
	 * Return a reference to the scoped data bean.
	 * </p>
	 * 
	 * @return reference to the scoped data bean
	 */
	protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
		return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
	}

	public String btnAceptar_action() {
		this.guardarEstadoObjetosUsados();
		String retorno = null;
		// ArrayList listaLineasOrdenCompra = (ArrayList)
		// this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {

			try {

				if (this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
					this.getRequestBean1().getObjetosSeleccionMultiple().clear();
				} else {
					this.getRequestBean1().setObjetosSeleccionMultiple(new ArrayList());
				}

				RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();
				if (selectedRowKeys.length > 0) {
					this.getObjectListDataProvider().commitChanges();
					for (int i = 0; i < selectedRowKeys.length; i++) {
						String rowId = selectedRowKeys[i].getRowId();
						RowKey rowKey = this.getObjectListDataProvider().getRowKey(rowId);
						Object obj = this.getObjectListDataProvider().getObjects()[getNroFila(rowKey.toString())];

						LineaSolicitudSuministro lineaSolSum = (LineaSolicitudSuministro) obj;
						this.getRequestBean1().getObjetosSeleccionMultiple().add(lineaSolSum);

					}
				} else {
					warn("Seleccione las l\355neas de Solicitudes de Suministros que desee agregar.");
					return null;
				}

			} catch (Exception ex) {
				log(CASO_NAVEGACION + "_SeleccionarError:", ex);
				error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
				ex.printStackTrace();
			}

			this.setListaDelCommunication(null);
			this.getObjectListDataProvider().setList(null);

			retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	/*
	 * protected EliminarOrdenCompra
	 * getcompras$ABMOrdenCompra$EliminarOrdenCompra() { return
	 * (EliminarOrdenCompra)
	 * getBean("compras$ABMOrdenCompra$EliminarOrdenCompra"); }
	 */


	public String btnCalcular_action() {
		this.guardarEstadoObjetosUsados();// TODO: Replace with your code
		return null;
	}

}