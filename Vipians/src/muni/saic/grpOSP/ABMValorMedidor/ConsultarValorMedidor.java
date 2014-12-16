/*
 * ConsultarValorMedidor.java
 *
 * Created on 1 de noviembre de 2006, 14:27
 * Copyright Trascender SRL
 */

package muni.saic.grpOSP.ABMValorMedidor;

import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.event.ValueChangeEvent;

import com.sun.data.provider.RowKey;
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
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ConsultarValorMedidor extends AbstractPageBean {
	// Atributos propios de la pagina.
	// CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
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
	private final String NOMBRE_PAGINA = "Consultar Medici\363n del Medidor";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "ConsultarValorMedidor";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;

	private int __placeholder;

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {

		/**/if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}/**/

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

	private Button btnGuardar = new Button();

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Button b) {
		this.btnGuardar = b;
	}

	private Button btnCancelar = new Button();

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button b) {
		this.btnCancelar = b;
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

	/* TEXTAREA */

	private TextArea taObligacion = new TextArea();

	public TextArea getTaObligacion() {
		return taObligacion;
	}

	public void setTaObligacion(TextArea taObligacion) {
		this.taObligacion = taObligacion;
	}

	private TextArea taServicio = new TextArea();

	public TextArea getTaServicio() {
		return taServicio;
	}

	public void setTaServicio(TextArea taServicio) {
		this.taServicio = taServicio;
	}

	/* ROWKEY */
	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	/* DATAPROVIDER */
	private ObjectListDataProvider ldpHistoricoMedicion = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpHistoricoMedicion() {
		return ldpHistoricoMedicion;
	}

	public void setLdpHistoricoMedicion(ObjectListDataProvider ldpHistoricoMedicion) {
		this.ldpHistoricoMedicion = ldpHistoricoMedicion;
	}

	/* TABLAS */

	private Table tblHistoricoMedicion = new Table();

	public Table getTblHistoricoMedicion() {
		return tblHistoricoMedicion;
	}

	public void setTblHistoricoMedicion(Table tblHistoricoMedicion) {
		this.tblHistoricoMedicion = tblHistoricoMedicion;
	}

	/* TABLE GROUP */
	private TableRowGroup tblRGHistoricoMedicion = new TableRowGroup();

	public TableRowGroup getTblRGHistoricoMedicion() {
		return tblRGHistoricoMedicion;
	}

	public void setTblRGHistoricoMedicion(TableRowGroup tblRGHistoricoMedicion) {
		this.tblRGHistoricoMedicion = tblRGHistoricoMedicion;
	}

	/* TABLECOLUMN */

	private TableColumn tblClmConsumo = new TableColumn();

	public TableColumn getTblClmConsumo() {
		return tblClmConsumo;
	}

	public void setTblClmConsumo(TableColumn tblClmConsumo) {
		this.tblClmConsumo = tblClmConsumo;
	}

	private TableColumn tblClmPeriodo = new TableColumn();

	public TableColumn getTblClmPeriodo() {
		return tblClmPeriodo;
	}

	public void setTblClmPeriodo(TableColumn tblClmPEriodo) {
		this.tblClmPeriodo = tblClmPeriodo;
	}

	private TableColumn tblClmLecturaActual = new TableColumn();

	public TableColumn getTblClmLecturaActual() {
		return tblClmLecturaActual;
	}

	public void setTblClmLecturaActual(TableColumn tblClmLecturaActual) {
		this.tblClmLecturaActual = tblClmLecturaActual;
	}

	/* TEXTFIELD */
	private TextField tfPeriodo = new TextField();

	public TextField getTfPeriodo() {
		return tfPeriodo;
	}

	public void setTfPeriodo(TextField tf) {
		this.tfPeriodo = tf;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private TextField tfFecha = new TextField();

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tf) {
		this.tfFecha = tf;
	}

	private TextField tfMontoImponible = new TextField();

	public TextField getTfMontoImponible() {
		return tfMontoImponible;
	}

	public void setTfMontoImponible(TextField tf) {
		this.tfMontoImponible = tf;
	}

	private TextField tfCodigoMedidor = new TextField();

	public TextField getTfCodigoMedidor() {
		return tfCodigoMedidor;
	}

	public void setTfCodigoMedidor(TextField tf) {
		this.tfCodigoMedidor = tf;
	}

	private TextField tfDireccion = new TextField();

	public TextField getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(TextField tf) {
		this.tfDireccion = tf;
	}

	/* LABEL */

	private Label lblObligacion = new Label();

	public Label getLblObligacion() {
		return lblObligacion;
	}

	public void setLblObligacion(Label lblObligacion) {
		this.lblObligacion = lblObligacion;
	}

	private Label lblHistoricoMedicion = new Label();

	public Label getLblHistoricoMedicion() {
		return lblHistoricoMedicion;
	}

	public void setLblHistoricoMedicion(Label lblHistoricoMedicion) {
		this.lblHistoricoMedicion = lblHistoricoMedicion;
	}

	private Label lblDocumentoOSP = new Label();

	public Label getLblDocumentoOSP() {
		return lblDocumentoOSP;
	}

	public void setLblDocumentoOSP(Label lblDocumentoOSP) {
		this.lblDocumentoOSP = lblDocumentoOSP;
	}

	private Label lblServicio = new Label();

	public Label getLblServicio() {
		return lblServicio;
	}

	public void setLblServicio(Label lblServicio) {
		this.lblServicio = lblServicio;
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

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
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

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	/* STATICTEXT */

	private StaticText sttClmLecturaActual = new StaticText();

	public StaticText getSttClmConsumo() {
		return sttClmConsumo;
	}

	public void setSttClmConsumo(StaticText sttClmConsumo) {
		this.sttClmConsumo = sttClmConsumo;
	}

	public StaticText getSttClmLecturaActual() {
		return sttClmLecturaActual;
	}

	public void setSttClmLecturaActual(StaticText sttClmLecturaActual) {
		this.sttClmLecturaActual = sttClmLecturaActual;
	}

	public StaticText getSttClmPeriodo() {
		return sttClmPeriodo;
	}

	public void setSttClmPeriodo(StaticText sttClmPeriodo) {
		this.sttClmPeriodo = sttClmPeriodo;
	}

	private StaticText sttClmPeriodo = new StaticText();
	private StaticText sttClmConsumo = new StaticText();

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private StaticText stSeparador = new StaticText();

	public StaticText getStSeparador() {
		return stSeparador;
	}

	public void setStSeparador(StaticText st) {
		this.stSeparador = st;
	}

	private StaticText stTitulo = new StaticText();

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ConsultarValorMedidor() {
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
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
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
			log("ConsultarValorMedidor Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

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
		boolean cargarTabla = false;

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

				cargarTabla = true;
			}
		}

		// 2. Recarga de la misma pagina por validacion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// APLICAR LOGICA AQUI.. ver si es as� realmente..
		}

		// 3. Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if(existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();

			cargarTabla = true;
		}

		// 4. Pagina nueva - hacia atras en la transaccion
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

	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new ValorMedidor()); // 0: ValorMedidor
		ep.getObjetos().add(ind++, new ArrayList()); // 1: Lista de Medidiciones // DocHabilitanteEspecializado

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		ValorMedidor valorMedidor = (ValorMedidor) this.obtenerObjetoDelElementoPila(ind++, ValorMedidor.class);
		ArrayList listaMediciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		DocHabilitanteEspecializado docHabilitanteEspecializado = valorMedidor.getDocHabilitanteEspecializado();

		Object fecha = this.getTfFecha().getText();
		Object montoImponible = this.getTfMontoImponible().getText();

		if(fecha != null && fecha != "")
			valorMedidor.setFecha(Conversor.getFechaCortaDeString(fecha.toString()));
		else
			valorMedidor.setFecha(null);

		/*
		 * if (montoImponible != null && montoImponible != "") valorMedidor.setMontoImponible(Conversor.getDoubleDeString(montoImponible.toString())); else
		 * valorMedidor.setMontoImponible(null);
		 */
		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, valorMedidor);
		this.getElementoPila().getObjetos().set(ind++, listaMediciones);
	}

	private void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		ValorMedidor valorMedidor = null;
		ArrayList locListaMediciones = new ArrayList();
		DocumentoOSP documentoOsp = null;

		this.acomodarSeleccionado();

		// CAMBIAR: Obtener datos del Request y asignarlos a los textField
		if(this.getRequestBean1().getObjetoABM() != null) {

			valorMedidor = (ValorMedidor) this.getRequestBean1().getObjetoABM();
			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, valorMedidor);
		}

		ind = 0;
		if(valorMedidor != null) {
			try {
				// this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
				// valorMedidor = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getValorMedidorPorId(valorMedidor.getIdRegistroValuado());
				// // documentoOsp = (DocumentoOSP)valorMedidor.getDocHabilitanteEspecializado();

				this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
				documentoOsp = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP()
						.getDocumentoOSPPorId(valorMedidor.getDocHabilitanteEspecializado().getIdDocHabilitanteEspecializado());

				/* HECHO POR TATO, VERRRRRRRR */

				// this.getTaDocumentoOSP().setText(documentoOsp.toString());
				this.getTaServicio().setText(valorMedidor.getServicioOSP());
				this.getTaObligacion().setText(documentoOsp.getObligacion().toString());
				// this.getTfPersona().setText(documentoOsp.getObligacion().getPersona());
				this.getTfPersona().setText(documentoOsp.getObligacion().getPersona());
				this.getTfDireccion().setText(documentoOsp.getDomicilio());
				this.getTfCodigoMedidor().setText(documentoOsp.getCodigoMedidor());
				this.getTfFecha().setText(Conversor.getStringDeFechaCorta(valorMedidor.getFecha()));
				// this.getTfMontoImponible().setText(valorMedidor.getMontoImponible().toString());

				Iterator itr = documentoOsp.getListaRegistrosValuados().iterator();

				while(itr.hasNext()) {
					RegistroValuado locRegistroValuado = (RegistroValuado) itr.next();
					locListaMediciones.add(locRegistroValuado);

				}

				this.setListaDelCommunication(locListaMediciones);
				this.getObjectListDataProvider().setList(locListaMediciones);/**/

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpHistoricoMedicion();
	}

	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationSAICBean().getListaMedicionesMedidoresOSP();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationSAICBean().setListaMedicionesMedidoresOSP(lista);

	}

	// private void refrescarTabla() throws Exception{
	// ValorMedidor valorMedidor = (ValorMedidor) this.obtenerObjetoDelElementoPila(0, ValorMedidor.class);
	// ArrayList listaMediciones = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
	// DocumentoOSP documentoOSP = null;
	//
	// try{
	// this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
	// documentoOSP =
	// this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().getDocumentoOSPPorId(valorMedidor.getDocHabilitanteEspecializado().getIdDocHabilitanteEspecializado());
	// if(documentoOSP != null ){
	// listaMediciones = (ArrayList) documentoOSP.getListaRegistrosValuados();
	// }
	// }
	// catch(Exception ex){
	// ex.printStackTrace();
	// listaMediciones = null;
	// }
	// this.setListaDelCommunication(listaMediciones);
	// this.getObjectListDataProvider().setList(listaMediciones);
	// }

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
			if(objeto == null) {
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
			if(campo != null)
				campo.setText(null);
		} catch(Exception ex) {
		}

	}

	public String btnCancelar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			retorno = this.prepararParaVolver(Constantes.ACCION_CONSULTAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void tfServicioOSP_processValueChange(ValueChangeEvent event) {
	}

	public void tfDocumentoOSP_processValueChange(ValueChangeEvent event) {
	}
}