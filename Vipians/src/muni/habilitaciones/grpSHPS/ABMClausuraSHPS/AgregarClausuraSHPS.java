/*
 * AgregarClausuraSHPS.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpSHPS.ABMClausuraSHPS;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.habilitaciones.recurso.persistent.shps.ClausuraSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class AgregarClausuraSHPS extends AbstractPageBean {

	// <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">

	// Atributos propios de la pagina.
	// CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
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
	private final String NOMBRE_PAGINA = "Agregar Clausura SHPS";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AgregarClausuraSHPS";
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

	private StaticText stTitulo = new StaticText();

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
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

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfFechaAlta = new TextField();

	public TextField getTfFechaAlta() {
		return tfFechaAlta;
	}

	public void setTfFechaAlta(TextField tf) {
		this.tfFechaAlta = tf;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfFechaBaja = new TextField();

	public TextField getTfFechaBaja() {
		return tfFechaBaja;
	}

	public void setTfFechaBaja(TextField tf) {
		this.tfFechaBaja = tf;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TextField tfRol = new TextField();

	public TextField getTfRol() {
		return tfRol;
	}

	public void setTfRol(TextField tf) {
		this.tfRol = tf;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	private Button btnSeleccionarRol = new Button();

	public Button getBtnSeleccionarRol() {
		return btnSeleccionarRol;
	}

	public void setBtnSeleccionarRol(Button b) {
		this.btnSeleccionarRol = b;
	}

	private HtmlAjaxCommandButton btnLimpiarRol = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarRol() {
		return btnLimpiarRol;
	}
	public void setBtnLimpiarRol(HtmlAjaxCommandButton btnLimpiarRol) {
		this.btnLimpiarRol = btnLimpiarRol;
	}
	/** 
	 * <p>Construir una instancia de bean de p�gina.</p>
	 */
	 public AgregarClausuraSHPS() {
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
	    protected muni.ApplicationBean1 getApplicationBean1() {
	    	return (muni.ApplicationBean1)getBean("ApplicationBean1");
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
	    protected muni.ComunicationBean getComunicationBean() {
	    	return (muni.ComunicationBean)getBean("ComunicationBean");
	    }


	    /** 
	     * <p>Devolver una referencia al bean de datos con �mbito.</p>
	     */
	    protected muni.RequestBean1 getRequestBean1() {
	    	return (muni.RequestBean1)getBean("RequestBean1");
	    }


	    /** 
	     * <p>Devolver una referencia al bean de datos con �mbito.</p>
	     */
	    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
	    	return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
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
	    		log("AgregarArea Initialization Failure", e);
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
	    	ep.getObjetos().add(ind++, new ClausuraSHPS());
	    	ep.getObjetos().add(ind++, new Rol());
	    	ep.getObjetos().add(ind++, null); // documento shps

	    	// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
	    	ep.getObjetos().add(ind++, new Integer(0));
	    	return ep;
	    }

	    private void guardarEstadoObjetosUsados() {
	    	// CAMBIAR: Revisar el metodo completo.
	    	int ind = 0;
	    	ClausuraSHPS clausuraSHPS = (ClausuraSHPS) this.obtenerObjetoDelElementoPila(ind++, ClausuraSHPS.class);
	    	Rol rol = (Rol) this.obtenerObjetoDelElementoPila(ind++, Rol.class);
	    	DocumentoSHPS documentoSHPS = (DocumentoSHPS) this.obtenerObjetoDelElementoPila(ind++, DocumentoSHPS.class);

	    	Object fechaAlta = this.getTfFechaAlta().getText();
	    	Object fechaBaja = this.getTfFechaBaja().getText();
	    	Object descripcion = this.getTaDescripcion().getText();

	    	if (fechaAlta != null && fechaAlta != "") clausuraSHPS.setFechaAlta(Conversor.getFechaCortaDeString(fechaAlta.toString()));
	    	else clausuraSHPS.setFechaAlta(null);
	    	if (fechaBaja != null && fechaBaja != "") clausuraSHPS.setFechaBaja(Conversor.getFechaCortaDeString(fechaBaja.toString()));
	    	else clausuraSHPS.setFechaBaja(null);
	    	if (descripcion != null && descripcion != "") clausuraSHPS.setDescripcion(descripcion.toString());
	    	else clausuraSHPS.setDescripcion(null);

	    	if (rol.getIdRol()==-1) rol = null;
	    	clausuraSHPS.setRol(rol);

	    	if (documentoSHPS.getIdDocumentoSHPS()==-1) documentoSHPS = null;
	    	clausuraSHPS.setDocumentoSHPS(documentoSHPS);

	    	ind = 0;
	    	this.getElementoPila().getObjetos().set(ind++, clausuraSHPS);
	    	this.getElementoPila().getObjetos().set(ind++, rol);
	    }

	    private void mostrarEstadoObjetosUsados() {
	    	// CAMBIAR: Revisar el metodo completo.
	    	ClausuraSHPS clausuraSHPS = null;
	    	Rol rol = null;
	    	DocumentoSHPS documentoSHPS = null;

	    	if (this.getRequestBean1().getObjetoSeleccion() != null) {
	    		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
	    		if (seleccionado instanceof DocumentoSHPS) {
	    			this.getElementoPila().getObjetos().set(2, seleccionado);
	    			this.getRequestBean1().setObjetoSeleccion(null);
	    		}

	    		if (seleccionado instanceof Rol) {
	    			rol = (Rol) seleccionado;
	    			if (rol.isFirma()) this.getElementoPila().getObjetos().set(1, seleccionado);
	    			else error("Debe seleccionar un Rol con permiso para Firmar");
	    			this.getRequestBean1().setObjetoSeleccion(null);
	    		}
	    	}

	    	this.acomodarSeleccionado();

	    	int ind = 0;
	    	clausuraSHPS = (ClausuraSHPS) this.obtenerObjetoDelElementoPila(ind++, ClausuraSHPS.class);
	    	rol = (Rol) this.obtenerObjetoDelElementoPila(ind++, Rol.class);

	    	this.getTfFechaAlta().setText(Conversor.getStringDeFechaCorta(clausuraSHPS.getFechaAlta()));
	    	this.getTfFechaBaja().setText(Conversor.getStringDeFechaCorta(clausuraSHPS.getFechaBaja()));
	    	this.getTfRol().setText(rol.toString());
	    	this.getTaDescripcion().setText(clausuraSHPS.getDescripcion());
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

	    public void limpiarObjeto(int posicion, Class tipoClase, TextField campo) {
	    	try {
	    		this.getElementoPila().getObjetos().set(posicion, tipoClase.newInstance());
	    		if (campo!=null) campo.setText("");
	    	} catch (Exception ex) { }
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


	    public String btnSeleccionarRol_action() {
	    	String retorno = null;
	    	boolean ultimo = this.ultimoElementoPilaDeSubSesion();

	    	// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
	    	int posicionObjetoSeleccionado = 1;

	    	if (ultimo) {

	    		// APLICAR LOGICA AQUI...

	    		this.guardarEstadoObjetosUsados();
	    		this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
	    		this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

	    		// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
	    		retorno = "AdminRol";
	    	} else {
	    		retorno = this.prepararCaducidad();
	    	}

	    	return retorno;
	    }

	    public String btnLimpiarRol_action() {
	    	String retorno = null;
	    	boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	    	if(ultimo) {
	    		// CAMBIAR: Especificar objeto
	    		this.limpiarObjeto(1, this.getTfRol());
	    		this.guardarEstadoObjetosUsados();
	    	} else {
	    		retorno = this.prepararCaducidad();
	    	}
	    	return retorno;
	    }


	    public String btnGuardar_action() {
	    	String retorno = null;
	    	boolean ultimo = this.ultimoElementoPilaDeSubSesion();

	    	if (ultimo) {

	    		//APLICAR LOGICA AQUI...
	    		try {
	    			this.guardarEstadoObjetosUsados();

	    			// CAMBIAR: Validar los campos necesarios.
	    			Validador v = new Validador();
	    			UIComponent[] noVacios = new UIComponent[3];
	    			String[] nomNoVacios = new String[3];
	    			UIComponent[] fechas = new UIComponent[2];
	    			String[] nomFechas = new String[2];

	    			int pos = 0;
	    			noVacios[pos] = this.getTfFechaAlta();
	    			nomNoVacios[pos++] = "Fecha de Alta";
	    			noVacios[pos] = this.getTfRol();
	    			nomNoVacios[pos++] = "Rol";
	    			noVacios[pos] = this.getTaDescripcion();
	    			nomNoVacios[pos++] = "Descripci\363n";

	    			pos = 0;
	    			fechas[pos] = this.getTfFechaAlta();
	    			nomFechas[pos++] = "Fecha de Alta";
	    			fechas[pos] = this.getTfFechaBaja();
	    			nomFechas[pos++] = "Fecha de Baja";

	    			v.noSonVacios(noVacios, nomNoVacios);
	    			v.formatoFechaValido(fechas, nomFechas);

	    			if (v.getErrores().size() > 0) {
	    				error("Existen Errores:");
	    				for (int i = 0; i < v.getErrores().size(); i++) {
	    					warn(v.getErrores().toArray()[i].toString());
	    				}
	    				return null;
	    			}

	    			// CAMBIAR:
	    			ClausuraSHPS clausuraSHPS = (ClausuraSHPS) this.obtenerObjetoDelElementoPila(0, ClausuraSHPS.class);

	    			this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(this.getSessionBean1().getLlave());

	    			System.out.println("LA LLAVE ANTES: " + this.getSessionBean1().getLlave());
	    			clausuraSHPS.getDocumentoSHPS().setLlaveUsuarioAuditoria(this.getSessionBean1().getLlave());

	    			clausuraSHPS = this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().addClausuraSHPS(clausuraSHPS);
	    			this.getRequestBean1().setRespuestaABM(clausuraSHPS);

	    			info("La Clausura se agreg\363 satisfactoriamente.");

	    			retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
	    		} catch (Exception ex) {
	    			if (ex instanceof TrascenderException) {
	    				int codigoError = ((TrascenderException)ex).getCodeTrascenderException();
	    				if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) retorno = null;
	    				else retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
	    			}
	    			log(CASO_NAVEGACION+"_GuardarError:", ex);
	    			error(NOMBRE_PAGINA+" - Guardar: " + ex.getMessage());
	    		}
	    	} else {
	    		retorno = this.prepararCaducidad();
	    	}
	    	return retorno;
	    }

	    public String btnCancelar_action() {
	    	String retorno = null;
	    	boolean ultimo = this.ultimoElementoPilaDeSubSesion();

	    	if (ultimo) {

	    		retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
	    	} else {
	    		retorno = this.prepararCaducidad();
	    	}
	    	return retorno;
	    }

}
