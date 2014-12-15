/*
 * ConsultarDomicilio.java
 *
 * Created on 13 de octubre de 2006, 13:41
 * Copyright Trascender SRL
 */
/**Mines*/
package muni.comunes.ABMDomicilio;
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
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.RfrCalle;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Script;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.filtros.FiltroMunicipalidad;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.presentacion.utiles.Constantes;
import muni.ComunicationCatastroBean;
import muni.CommunicationCajaBean;
import muni.SessionBean1;
import muni.RequestBean1;
import muni.CommunicationExcepcionesBean;
import muni.CommunicationHabilitacionesBean;
import muni.CommunicationComprasBean;
import muni.ApplicationBean1;
import muni.ComunicationBean;
import muni.CommunicationSAICBean;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ConsultarDomicilio extends AbstractPageBean {
    
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
    private final String NOMBRE_PAGINA = "Consultar Domicilio";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ConsultarDomicilio";
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
    
    private Button btnVolver = new Button();
    
    public Button getBtnVolver() {
        return btnVolver;
    }
    
    public void setBtnVolver(Button b) {
        this.btnVolver = b;
    }
    
    private Label label15 = new Label();
    
    public Label getLabel15() {
        return label15;
    }
    
    public void setLabel15(Label l) {
        this.label15 = l;
    }
    
    private Label label16 = new Label();
    
    public Label getLabel16() {
        return label16;
    }
    
    public void setLabel16(Label l) {
        this.label16 = l;
    }
    
    private Label label17 = new Label();
    
    public Label getLabel17() {
        return label17;
    }
    
    public void setLabel17(Label l) {
        this.label17 = l;
    }
    
    private Label label18 = new Label();
    
    public Label getLabel18() {
        return label18;
    }
    
    public void setLabel18(Label l) {
        this.label18 = l;
    }
    
    private TextField tfCalle = new TextField();
    
    public TextField getTfCalle() {
        return tfCalle;
    }
    
    public void setTfCalle(TextField tf) {
        this.tfCalle = tf;
    }
    
    private TextField tfNumero = new TextField();
    
    public TextField getTfNumero() {
        return tfNumero;
    }
    
    public void setTfNumero(TextField tf) {
        this.tfNumero = tf;
    }
    
    private TextField tfPiso = new TextField();
    
    public TextField getTfPiso() {
        return tfPiso;
    }
    
    public void setTfPiso(TextField tf) {
        this.tfPiso = tf;
    }
    
    private Label label19 = new Label();
    
    public Label getLabel19() {
        return label19;
    }
    
    public void setLabel19(Label l) {
        this.label19 = l;
    }
    
    private TextField tfDepartamento = new TextField();
    
    public TextField getTfDepartamento() {
        return tfDepartamento;
    }
    
    public void setTfDepartamento(TextField tf) {
        this.tfDepartamento = tf;
    }
    
    private Label label20 = new Label();
    
    public Label getLabel20() {
        return label20;
    }
    
    public void setLabel20(Label l) {
        this.label20 = l;
    }
    
    private Label label21 = new Label();
    
    public Label getLabel21() {
        return label21;
    }
    
    public void setLabel21(Label l) {
        this.label21 = l;
    }
    
    private Label label22 = new Label();
    
    public Label getLabel22() {
        return label22;
    }
    
    public void setLabel22(Label l) {
        this.label22 = l;
    }
    
    private TextField tfBarrio = new TextField();
    
    public TextField getTfBarrio() {
        return tfBarrio;
    }
    
    public void setTfBarrio(TextField tf) {
        this.tfBarrio = tf;
    }
    
    private TextField tfManzana = new TextField();
    
    public TextField getTfManzana() {
        return tfManzana;
    }
    
    public void setTfManzana(TextField tf) {
        this.tfManzana = tf;
    }
    
    private TextField tfSector = new TextField();
    
    public TextField getTfSector() {
        return tfSector;
    }
    
    public void setTfSector(TextField tf) {
        this.tfSector = tf;
    }
    
    private Label label23 = new Label();
    
    public Label getLabel23() {
        return label23;
    }
    
    public void setLabel23(Label l) {
        this.label23 = l;
    }
    
    private Label label24 = new Label();
    
    public Label getLabel24() {
        return label24;
    }
    
    public void setLabel24(Label l) {
        this.label24 = l;
    }
    
    private TextField tfTorre = new TextField();
    
    public TextField getTfTorre() {
        return tfTorre;
    }
    
    public void setTfTorre(TextField tf) {
        this.tfTorre = tf;
    }
    
    private TextField tfEscalera = new TextField();
    
    public TextField getTfEscalera() {
        return tfEscalera;
    }
    
    public void setTfEscalera(TextField tf) {
        this.tfEscalera = tf;
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
    
    private TextField tfLocalidad = new TextField();
    
    public TextField getTfLocalidad() {
        return tfLocalidad;
    }
    
    public void setTfLocalidad(TextField tf) {
        this.tfLocalidad = tf;
    }
    
    private ObjectListDataProvider ldpSocios = new ObjectListDataProvider();
    
    public ObjectListDataProvider getLdpSocios() {
        return ldpSocios;
    }
    
    public void setLdpSocios(ObjectListDataProvider oldp) {
        this.ldpSocios = oldp;
    }

    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    private TextField tfCodigoPostal = new TextField();

    public TextField getTfCodigoPostal() {
        return tfCodigoPostal;
    }

    public void setTfCodigoPostal(TextField tf) {
        this.tfCodigoPostal = tf;
    }
    // </editor-fold>
    
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ConsultarDomicilio() {
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
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
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
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1)getBean("SessionBean1");
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
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean)getBean("ComunicationBean");
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
            log("AgregarPersonaJuridica Initialization Failure", e);
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
        
        // 1. Pagina nueva - Inicio de transaccion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            if(this.PUEDE_SER_PAGINA_INICIAL) {
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
            
            this.getRequestBean1().setRespuestaABM("first");
        }
        
        // 4. Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());
        }
        
        if (!recarga) {
            this.cargarValoresPorDefecto();
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
    public void destroy() {
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new Domicilio());
        ep.getObjetos().add(ind++, new Localidad());
        ep.getObjetos().add(ind++, new RfrCalle());
        
        ep.getObjetos().add(ind++, new Localidad()); // localidad por defecto
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        Localidad localidad = (Localidad) this.obtenerObjetoDelElementoPila(ind++, Localidad.class);
        RfrCalle relacionCalle = (RfrCalle) this.obtenerObjetoDelElementoPila(ind++, RfrCalle.class);
        
        Object calle = tfCalle.getText();
        Object num = tfNumero.getText();
        Object cod = tfCodigoPostal.getText();
        Object piso = tfPiso.getText();
        Object depto = tfDepartamento.getText();
        Object barrio = tfBarrio.getText();
        Object manz = tfManzana.getText();
        Object torre = tfTorre.getText();
        Object esca = tfEscalera.getText();
        Object sec = tfSector.getText();
        
        if (num != null && num != "") domicilio.setNumero(num.toString()); else domicilio.setNumero(null);
        if (cod != null && cod != "") domicilio.setCodigoPostal(cod.toString()); else domicilio.setCodigoPostal(null);
        if (piso != null && piso != "") domicilio.setPiso(piso.toString()); else domicilio.setPiso(null);
        if (depto != null && depto != "") domicilio.setDepartamento(depto.toString()); else domicilio.setDepartamento(null);
        if (barrio != null && barrio != "") domicilio.setBarrio(barrio.toString()); else domicilio.setBarrio(null);
        if (manz != null && manz != "") domicilio.setManzana(manz.toString()); else domicilio.setManzana(null);
        if (torre != null && torre != "") domicilio.setTorre(torre.toString()); else domicilio.setTorre(null);
        if (esca != null && esca != "") domicilio.setEscalera(esca.toString()); else domicilio.setEscalera(null);
        if (sec != null && sec != "") domicilio.setSector(sec.toString()); else domicilio.setSector(null);
        
        if (localidad.getIdLocalidad()==-1) localidad = null;
        domicilio.setLocalidad(localidad);
        
        if (relacionCalle.getIdAbstractCalle()==-1) relacionCalle = null;
        domicilio.setRelacionCalle(relacionCalle);

        // ariel - cambio
//        if (relacionCalle == null && calle != null) domicilio.setCalle(calle.toString());
//        else domicilio.setCalle(null);
        String calleStr = null;
        if (relacionCalle == null && calle != null && calle != "") calleStr = calle.toString();
        domicilio.setCalle(calleStr);
        
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, domicilio);
        this.getElementoPila().getObjetos().set(ind++, localidad);
        this.getElementoPila().getObjetos().set(ind++, relacionCalle);
    }
    
    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Domicilio domicilio = null;
        Localidad localidad = null;
        RfrCalle relacionCalle = null;
        Localidad localidadDefecto = null;
        
        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            // Logica especial de Domicilio
            if (seleccionado instanceof Calle) {
                Calle calle = (Calle) seleccionado;
                
                relacionCalle = new RfrCalle();
                relacionCalle.setIdAbstractCalle(calle.getIdCalle());
                relacionCalle.setCodigo(calle.getCodigo());
                relacionCalle.setNombre(calle.getNombre());
                
                this.getElementoPila().getObjetos().set(2, relacionCalle);
                
                this.getRequestBean1().setObjetoSeleccion(null);
            }
        }
        
        this.acomodarSeleccionado();
        
        if (this.getRequestBean1().getObjetoABM() != null) {
            domicilio = (Domicilio) this.getRequestBean1().getObjetoABM();
            
            Domicilio nuevoDomicilio = null;
            try { nuevoDomicilio = domicilio.clone(); }
            catch (CloneNotSupportedException ex) {
                error("CloneError: "+ex.getMessage());
            }
            
            localidad = nuevoDomicilio.getLocalidad();
            relacionCalle = nuevoDomicilio.getRelacionCalle();
            
            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, nuevoDomicilio);
            this.getElementoPila().getObjetos().set(ind++, localidad);
            this.getElementoPila().getObjetos().set(ind++, relacionCalle);
        }
        
        ind = 0;
        domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        localidad = (Localidad) this.obtenerObjetoDelElementoPila(ind++, Localidad.class);
        relacionCalle = (RfrCalle) this.obtenerObjetoDelElementoPila(ind++, RfrCalle.class);
        localidadDefecto = (Localidad) this.obtenerObjetoDelElementoPila(ind++, Localidad.class);
        
        // Solo para agregar...
        if (this.getRequestBean1().getRespuestaABM() != null) {
            localidad = localidadDefecto;
            this.getElementoPila().getObjetos().set(1, localidad);
            this.getRequestBean1().setRespuestaABM(null);
        }
        
        boolean mostrar = (localidadDefecto.getIdLocalidad() == localidad.getIdLocalidad());
        this.cambiarSegunLocalidadPorDefecto(mostrar);
        
        this.getTfLocalidad().setText(localidad.toString());
        this.getTfNumero().setText(domicilio.getNumero());
        this.getTfCodigoPostal().setText(domicilio.getCodigoPostal());
        this.getTfPiso().setText(domicilio.getPiso());
        this.getTfDepartamento().setText(domicilio.getDepartamento());
        this.getTfBarrio().setText(domicilio.getBarrio());
        this.getTfManzana().setText(domicilio.getManzana());
        this.getTfTorre().setText(domicilio.getTorre());
        this.getTfEscalera().setText(domicilio.getEscalera());
        this.getTfSector().setText(domicilio.getSector());
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
    
    public String btnVolver_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
                        
            this.getRequestBean1().setRespuestaABM(null);
            
            retorno = this.prepararParaVolver(Constantes.ACCION_CONSULTAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    private void cargarValoresPorDefecto() {
        // CAMBIAR: Obtener los valores por defecto.
        Localidad localidadDefecto = null;
        
        try {
            this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
            Municipalidad muni = this.getComunicationBean().getRemoteSystemMunicipalidad().getMunicipalidad(new FiltroMunicipalidad()).getListaResultados().get(0);
            Domicilio domMuni = muni.getDomicilio();
            if (domMuni != null) localidadDefecto = domMuni.getLocalidad();
            if (localidadDefecto != null) this.getElementoPila().getObjetos().set(3, localidadDefecto);
            
        } catch (Exception ex) {}
        
        return;
    }
    
    private void mostrarSeleccion(boolean mostrar) {
        this.getTfCalle().setDisabled(mostrar);
        if (mostrar) this.getTfCalle().setText(null);
        return;
    }
    
    private void cambiarSegunLocalidadPorDefecto(boolean mostrar) {
        
        Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(0, Domicilio.class);
        RfrCalle relacionCalle = (RfrCalle) this.obtenerObjetoDelElementoPila(2, RfrCalle.class);

        this.getTfCalle().setDisabled(mostrar);

        if (mostrar) {
            this.getTfCalle().setText(null);
            this.getTfCalle().setText(relacionCalle.getNombreCalle());
        }
        else {
            this.getTfCalle().setText(domicilio.getCalle());
            this.getElementoPila().getObjetos().set(2, null);
        }
    }
}
