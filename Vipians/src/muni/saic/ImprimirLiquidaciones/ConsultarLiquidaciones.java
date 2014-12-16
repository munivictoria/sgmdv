/*
 * ConsultarLiquidaciones.java
 *
 * Created on 4 de marzo de 2013, 10:47
 * Copyright Trascender SRL
 */
/**Mines*/
package muni.saic.ImprimirLiquidaciones;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.FacesException;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
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
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.PagoTicket;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.saic.recurso.persistent.RegistroCancelacion;
import com.trascender.saic.recurso.persistent.RegistroCancelacionManual;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;



/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ConsultarLiquidaciones extends AbstractPageBean {

	// <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">

	// Atributos propios de la pagina.
	// CAMBIAR: Ir al dise�o y vincular a campos ocultos.
	private Long idPagina = null;
	private Long idSubSesion = null;
	public Long getIdPagina() { return idPagina; }
	public void setIdPagina(Long idPagina) { this.idPagina = idPagina; }
	public Long getIdSubSesion() { return idSubSesion; }
	public void setIdSubSesion(Long idSubSesion) { this.idSubSesion = idSubSesion; }

	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Consultar Liquidaaci\363n";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "ConsultarLiquidaciones";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
	private int __placeholder;

	/**
	 * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
	 * This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.</p>
	 */
	private void _init() throws Exception {
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


	private DropDown ddPeriodicidad = new DropDown();

	public DropDown getDdPeriodicidad() {
		return ddPeriodicidad;
	}

	public void setDdPeriodicidad(DropDown ddPeriodicidad) {
		this.ddPeriodicidad = ddPeriodicidad;
	}

	private SingleSelectOptionsList ddPeriodicidadDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdPeriodicidadDefaultOptions() {
		return ddPeriodicidadDefaultOptions;
	}

	public void setDdPeriodicidadDefaultOptions(SingleSelectOptionsList ddPeriodicidadDefaultOptions) {
		this.ddPeriodicidadDefaultOptions = ddPeriodicidadDefaultOptions;
	}

	//----------Static Texts---------------------------

	private StaticText stAnio = new StaticText();

	public StaticText getStAnio() {
		return stAnio;
	}

	public void setStAnio(StaticText stAnio) {
		this.stAnio = stAnio;
	}
	private StaticText stTitulo = new StaticText();

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}



	//----------Buttons---------------------------

	private Button btnVolver = new Button();

	public Button getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(Button b) {
		this.btnVolver = b;
	}

	//----------Labels---------------------------
	private Label lblDatosObligacion = new Label();
	private Label lblFechaObligacion = new Label();
	private Label lblPersona = new Label();
	private Label lblObligacion = new Label();  
	private Label lblTituloPeriodo = new Label();
	private Label lblAnio = new Label();
	private Label lblPeriodicidad = new Label();
	private Label lblPeriodo = new Label();
	private Label lblDatosLiquidacion = new Label();
	private Label lblCuotaLiquidada = new Label();
	private Label lblFechaVencimiento = new Label();
	private Label lblTipoDeuda = new Label();
	private Label lblEstado = new Label();
	private Label lblMonto = new Label();
	private Label lblModificadores = new Label();
	private Label lblParametros = new Label();
	private Label lblParametrosValuadosAlicuota = new Label();
	private Label lblUsuarioManual = new Label();
	private Label lblComentario = new Label();

	public Label getLblComentario() {
		return lblComentario;
	}

	public void setLblComentario(Label lblComentario) {
		this.lblComentario = lblComentario;
	}

	public Label getLblUsuarioManual() {
		return lblUsuarioManual;
	}

	public void setLblUsuarioManual(Label lblUsuarioManual) {
		this.lblUsuarioManual = lblUsuarioManual;
	}


	public Label getLblParametrosValuadosAlicuota() {
		return lblParametrosValuadosAlicuota;
	}

	public void setLblParametrosValuadosAlicuota(Label lblParametrosValuadosAlicuota) {
		this.lblParametrosValuadosAlicuota = lblParametrosValuadosAlicuota;
	}
	private Label lblVencimientos = new Label();
	private Label lblExencion = new Label();
	private Label lblFechaCancelacion = new Label();

	public Label getLblFechaCancelacion() {
		return lblFechaCancelacion;
	}

	public void setLblFechaCancelacion(Label lblFechaCancelacion) {
		this.lblFechaCancelacion = lblFechaCancelacion;
	}

	public Label getLblVencimientos() {
		return lblVencimientos;
	}

	public void setLblVencimientos(Label lblVencimientos) {
		this.lblVencimientos = lblVencimientos;
	}

	public Label getLblExencion() {
		return lblExencion;
	}

	public void setLblExencion(Label lblExencion) {
		this.lblExencion = lblExencion;
	}

	public Label getLblModificadores() {
		return lblModificadores;
	}

	public void setLblModificadores(Label lblModificadores) {
		this.lblModificadores = lblModificadores;
	}

	public Label getLblParametros() {
		return lblParametros;
	}

	public void setLblParametros(Label lblParametros) {
		this.lblParametros = lblParametros;
	}

	public Label getLblCuotaLiquidada() {
		return lblCuotaLiquidada;
	}

	public void setLblCuotaLiquidada(Label lblCuotaLiquidada) {
		this.lblCuotaLiquidada = lblCuotaLiquidada;
	}

	public Label getLblDatosLiquidacion() {
		return lblDatosLiquidacion;
	}

	public void setLblDatosLiquidacion(Label lblDatosLiquidacion) {
		this.lblDatosLiquidacion = lblDatosLiquidacion;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public Label getLblFechaVencimiento() {
		return lblFechaVencimiento;
	}

	public void setLblFechaVencimiento(Label lblFechaVencimiento) {
		this.lblFechaVencimiento = lblFechaVencimiento;
	}

	public Label getLblMonto() {
		return lblMonto;
	}

	public void setLblMonto(Label lblMonto) {
		this.lblMonto = lblMonto;
	}

	public Label getLblTipoDeuda() {
		return lblTipoDeuda;
	}

	public void setLblTipoDeuda(Label lblTipoDeuda) {
		this.lblTipoDeuda = lblTipoDeuda;
	}


	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public Label getLblDatosObligacion() {
		return lblDatosObligacion;
	}

	public void setLblDatosObligacion(Label lblDatosObligacion) {
		this.lblDatosObligacion = lblDatosObligacion;
	}

	public Label getLblFechaObligacion() {
		return lblFechaObligacion;
	}

	public void setLblFechaObligacion(Label lblFechaObligacion) {
		this.lblFechaObligacion = lblFechaObligacion;
	}

	public Label getLblObligacion() {
		return lblObligacion;
	}

	public void setLblObligacion(Label lblObligacion) {
		this.lblObligacion = lblObligacion;
	}

	public Label getLblPeriodicidad() {
		return lblPeriodicidad;
	}

	public void setLblPeriodicidad(Label lblPeriodicidad) {
		this.lblPeriodicidad = lblPeriodicidad;
	}

	public Label getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(Label lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public Label getLblTituloPeriodo() {
		return lblTituloPeriodo;
	}

	public void setLblTituloPeriodo(Label lblTituloPeriodo) {
		this.lblTituloPeriodo = lblTituloPeriodo;
	}


	//----------Text Fields---------------------------

	private TextField tfPersona = new TextField();
	private TextField tfFechaObligacion = new TextField();
	private TextField tfPeriodo = new TextField();
	private TextField tfCuotaLiquidada = new TextField();
	private TextField tfFechaVencimiento = new TextField();
	private TextField tfTipoDeuda = new TextField();
	private TextField tfEstado = new TextField();
	private TextField tfMonto = new TextField();
	private TextField tfFechaCancelacion = new TextField();
	private TextField tfUsuarioManual = new TextField();

	public TextField getTfUsuarioManual() {
		return tfUsuarioManual;
	}

	public void setTfUsuarioManual(TextField tfUsuarioManual) {
		this.tfUsuarioManual = tfUsuarioManual;
	}

	public TextField getTfFechaCancelacion() {
		return tfFechaCancelacion;
	}

	public void setTfFechaCancelacion(TextField tfFechaCancelacion) {
		this.tfFechaCancelacion = tfFechaCancelacion;
	}

	public TextField getTfCuotaLiquidada() {
		return tfCuotaLiquidada;
	}

	public void setTfCuotaLiquidada(TextField tfCuotaLiquidada) {
		this.tfCuotaLiquidada = tfCuotaLiquidada;
	}

	public TextField getTfEstado() {
		return tfEstado;
	}

	public void setTfEstado(TextField tfEstado) {
		this.tfEstado = tfEstado;
	}

	public TextField getTfFechaVencimiento() {
		return tfFechaVencimiento;
	}

	public void setTfFechaVencimiento(TextField tfFechaVencimiento) {
		this.tfFechaVencimiento = tfFechaVencimiento;
	}

	public TextField getTfMonto() {
		return tfMonto;
	}

	public void setTfMonto(TextField tfMonto) {
		this.tfMonto = tfMonto;
	}

	public TextField getTfTipoDeuda() {
		return tfTipoDeuda;
	}

	public void setTfTipoDeuda(TextField tfTipoDeuda) {
		this.tfTipoDeuda = tfTipoDeuda;
	}

	public TextField getTfFechaObligacion() {
		return tfFechaObligacion;
	}

	public void setTfFechaObligacion(TextField tfFechaObligacion) {
		this.tfFechaObligacion = tfFechaObligacion;
	}

	public TextField getTfPeriodo() {
		return tfPeriodo;
	}

	public void setTfPeriodo(TextField tfPeriodo) {
		this.tfPeriodo = tfPeriodo;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}


	//----------Text Areas---------------------------

	private TextArea taObligacion = new TextArea();
	private final TextArea taFormula = new TextArea();
	private TextArea taModificadores = new TextArea();
	private TextArea taParametros = new TextArea();
	private TextArea taParametrosValuadosAlicuota = new TextArea();
	private TextArea taVencimientos = new TextArea();
	private TextArea taExencion = new TextArea();
	private TextArea taComentario = new TextArea();

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	public TextArea getTaExencion() {
		return taExencion;
	}

	public void setTaExencion(TextArea taExencion) {
		this.taExencion = taExencion;
	}

	public TextArea getTaParametrosValuadosAlicuota() {
		return taParametrosValuadosAlicuota;
	}

	public void setTaParametrosValuadosAlicuota(TextArea taParametrosValuadosAlicuota) {
		this.taParametrosValuadosAlicuota = taParametrosValuadosAlicuota;
	}

	public TextArea getTaVencimientos() {
		return taVencimientos;
	}

	public void setTaVencimientos(TextArea taVencimientos) {
		this.taVencimientos = taVencimientos;
	}

	public TextArea getTaModificadores() {
		return taModificadores;
	}

	public void setTaModificadores(TextArea taModificadores) {
		this.taModificadores = taModificadores;
	}

	public TextArea getTaParametros() {
		return taParametros;
	}

	public void setTaParametros(TextArea taParametros) {
		this.taParametros = taParametros;
	}

	public TextArea getTaObligacion() {
		return taObligacion;
	}

	public void setTaObligacion(TextArea taObligacion) {
		this.taObligacion = taObligacion;
	}





	// </editor-fold>

	/**
	 * <p>Construir una instancia de bean de p�gina.</p>
	 */
	public ConsultarLiquidaciones() {
	}

	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (muni.CommunicationExcepcionesBean)getBean("CommunicationExcepcionesBean");
	}

	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationCajaBean getCommunicationCajaBean() {
		return (muni.CommunicationCajaBean)getBean("CommunicationCajaBean");
	}

	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean)getBean("CommunicationComprasBean");
	}

	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationSAICBean getCommunicationSAICBean() {
		return (muni.CommunicationSAICBean)getBean("CommunicationSAICBean");
	}

	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean)getBean("CommunicationHabilitacionesBean");
	}

	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1)getBean("SessionBean1");
	}


	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
	}


	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean)getBean("ComunicationBean");
	}


	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1)getBean("ApplicationBean1");
	}


	/**
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.RequestBean1 getRequestBean1() {
		return (muni.RequestBean1)getBean("RequestBean1");
	}


	/**
	 * <p>M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina,
	 * ya sea directamente mediante un URL o de manera indirecta a trav�s de la navegaci�n de p�ginas.
	 * Puede personalizar este m�todo para adquirir recursos que se necesitar�n
	 * para los controladores de eventos y m�todos del proceso, sin tener en cuenta si esta
	 * p�gina realiza procesamiento de devoluci�n de env�os.</p>
	 *
	 * <p>Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores
	 * de propiedad de los componentes <strong>no</strong> representan ning�n
	 * valor enviado con esta petici�n.  En su lugar, representan los
	 * valores de propiedades que se guardaron para esta vista cuando se proces�.</p>
	 */
	@Override
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
		} catch (Exception e) {
			log("ModificarDocEspSHPS Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

	}

	/**
	 * <p>M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha
	 * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este m�todo
	 * <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que
	 * est� procesando el env�o de un formulario.  Puede personalizar este m�todo para asignar
	 * recursos necesarios para los controladores de eventos.</p>
	 */
	@Override
	public void preprocess() {
	}

	/**
	 * <p>M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento.
	 * <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que
	 * se procesa, no se llamar�, por ejemplo, en una p�gina que
	 * ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina.  Puede personalizar
	 * este m�todo para asignar recursos necesarios para procesar
	 * esta p�gina.</p>
	 */
	@Override
	public void prerender() {
		boolean existeIdSubSesionReq = false;
		boolean existeIdPaginaReq    = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag    = false;
		boolean recarga              = false;

		if(this.getRequestBean1()!=null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq    = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag    = this.getIdPagina() != null;

		// Pagina nueva - Inicio de transaccion
		if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if(this.PUEDE_SER_PAGINA_INICIAL) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();
			}
		}

		// Recarga de la misma pagina por validacion
		if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// APLICAR LOGICA AQUI.. ver si es as� realmente..
		}

		// Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();
		}

		// Pagina nueva - hacia atras en la transaccion
		if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());
		}

		if (!recarga) {
			this.mostrarEstadoObjetosUsados();
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
	}

	/**
	 * <p>M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de
	 * esta petici�n, si se llam� al m�todo <code>init()</code> (sin tener en cuenta
	 * si se trata de la p�gina que se ha procesado o no).  Puede personalizar este
	 * m�todo para liberar los recursos adquiridos en los m�todos <code>init()</code>,
	 * <code>preprocess()</code> o <code>prerender()</code> (o
	 * durante la ejecuci�n de un controlador de eventos).</p>
	 */
	@Override
	public void destroy() {
	}


	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">

	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		//CAMBIAR: settear los objetos administrados por la pagina
		// ep.getObjetos().add(ind++, null); // Persona Fisica o Juridica
		ep.getObjetos().add(ind++, new Cuadra());
		ep.getObjetos().add(ind++, new Calle());
		ep.getObjetos().add(ind++, null);     // boolean de servicio medido
		ep.getObjetos().add(ind++, null);     // anio
		ep.getObjetos().add(ind++, null);     // Periodicidad
		ep.getObjetos().add(ind++, null);     // Periodo Numero
		ep.getObjetos().add(ind++, null);     // Fecha de reliquidacion
		//        ep.getObjetos().add(ind++, new ServicioSHPS());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;

		LiquidacionTasaAgrupada liquidacion = null;
		Integer anio = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
		Double montoTotal= (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);
		Periodicidad periodicidad = (Periodicidad) this.obtenerObjetoDelElementoPila(ind++, Periodicidad.class);
		Integer periodoNumero = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
		Date fechaReliquidacion = (Date) this.obtenerObjetoDelElementoPila(ind++, Date.class);

		Object obj = this.getRequestBean1().getObjetoABM();
		liquidacion = (LiquidacionTasaAgrupada) obj;
		Object periodoTexto = this.getTfPeriodo().getText();

		Object periodicidadSelected = this.getDdPeriodicidad().getSelected();
		if ((periodicidadSelected != null) && (periodicidadSelected.toString().length() > 0)) {
			periodicidad = Periodicidad.valueOf(periodicidadSelected.toString());
		}

		if (periodoTexto != null && periodoTexto != "") {
			periodoNumero = Conversor.getIntegerDeString(periodoTexto.toString());
		} else {
			periodoNumero = null;
		}

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, anio);
		this.getElementoPila().getObjetos().set(ind++, periodicidad);
		this.getElementoPila().getObjetos().set(ind++, periodoNumero);
		this.getElementoPila().getObjetos().set(ind++, fechaReliquidacion);
		this.getElementoPila().getObjetos().set(ind++, montoTotal);
		// this.getElementoPila().getObjetos().set(ind++, servicioSHPS);
	}

	private void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		LiquidacionTasaAgrupada liquidacion = null;
		RegistroCancelacion registroCancelacion = null;
		if(this.getRequestBean1().getObjetoABM() != null){
			LiquidacionTasaRefer locLiquidacionRefer = (LiquidacionTasaRefer) this.getRequestBean1().getObjetoABM();
			try{
				this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
				liquidacion = this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().inicializarLiquidacionTasaAgrupada(locLiquidacionRefer);
				registroCancelacion = liquidacion.getRegistroCancelacion();

			} catch (Exception e){
				e.printStackTrace();
			}
		}
		this.acomodarSeleccionado();

		if(liquidacion != null){ 
			this.getTfPeriodo().setText(liquidacion.getCuotaLiquidacion().getPeriodo().toString());      
			this.getTaParametros().setText(liquidacion.getStringParametrosValuados());
			this.getTaParametrosValuadosAlicuota().setText(liquidacion.getStringParametrosValuadosAlicuota());
			//////////////////////////////////////////////////
			this.getTaModificadores().setText(liquidacion.getStringModificadoresLiquidacion());
			this.getTaVencimientos().setText(liquidacion.getStringVencimientos());
			if (liquidacion.getRegistroExencionRegistroDeuda()!= null){
				this.getTaExencion().setText(liquidacion.getRegistroExencionRegistroDeuda().toString());
			}     
			this.getTaObligacion().setText(liquidacion.getStringObligacion());
			this.getTfCuotaLiquidada().setText(liquidacion.getCuotaLiquidada());
			this.getTfEstado().setText(liquidacion.getEstado().toString());
			this.getTfMonto().setText(Conversor.getStringDeDouble(Util.redondear(liquidacion.getMonto(), 2)));
			this.getTfFechaCancelacion().setText(liquidacion.getFechaCancelacion());


			if(registroCancelacion != null){
				SimpleDateFormat locSdf = new SimpleDateFormat();
				locSdf.applyPattern("dd/MM/yyyy");

				Usuario locUsuario = null;
				if(registroCancelacion instanceof RegistroCancelacionManual){
					RegistroCancelacionManual regCancelacionManual = (RegistroCancelacionManual) registroCancelacion;
					this.getTfFechaCancelacion().setText(locSdf.format(regCancelacionManual.getFechaCancelacion()));

					try {
						locUsuario = this.getComunicationBean().getRemoteSystemUsuario().getUsuarioPorId(regCancelacionManual.getUsuario().getIdUsuario());
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.getTfUsuarioManual().setText(locUsuario.getNombrePersonaFisica() + " (Manual)");

					if(regCancelacionManual.getComentario() != null) {
						this.getTaComentario().setText(regCancelacionManual.getComentario());
					}
				}
				else if(registroCancelacion instanceof DetalleTicketCaja){
					DetalleTicketCaja locDetalle = (DetalleTicketCaja) registroCancelacion;
					this.getTfFechaCancelacion().setText(locSdf.format(locDetalle.getFechaCancelacion()));

					try {
						locUsuario = this.getComunicationBean().getRemoteSystemUsuario().getUsuarioPorId(locDetalle.getTicketCaja().getUsuario().getIdUsuario());
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.getTfUsuarioManual().setText(locUsuario.getNombrePersonaFisica() + " (" + locDetalle.getTicketCaja().getCaja().getNumero() + ")");

					try {
						this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(this.getSessionBean1().getLlave());
						TicketCaja locTicket = getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().findTicketCajaByID(locDetalle.getTicketCaja().getIdTicketCaja());
						StringBuffer cadenaComentarios = new StringBuffer();
						for(PagoTicket cadaPago : locTicket.getListaPagosTicket()){
							if(cadaPago.getComentario() != null){
								cadenaComentarios.append("[" + cadaPago.getComentario() + "] ");
							}
						}
						this.getTaComentario().setText(cadenaComentarios);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

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
		if (locElementoAnterior != null) {
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
			if (objeto == null) {
				objeto = tipoClase.newInstance();
			}
		} catch (Exception ex) {
		}
		return objeto;
	}

	private void acomodarSeleccionado() {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if (seleccionado != null) {
			int posicion = ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, seleccionado);
		}
	}

	private int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
	}

	public void limpiarObjeto(int posicion, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, null);
			if (campo!=null) campo.setText(null);
		} catch (Exception ex) { }
	}

	// </editor-fold>

	public String btnVolver_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {

			retorno = this.prepararParaVolver(Constantes.ACCION_CONSULTAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}


	//    private void ocultarCodigoMedidor(ServicioSHPS servicio) {
	//        boolean mostrar = false;
	//        if (servicio != null) mostrar = servicio.isMedido();
	//        this.getLblCodigoMedidor().setRendered(mostrar);
	//        this.getTfCodigoMedidor().setRendered(mostrar);
	//        return;
	//    }
}