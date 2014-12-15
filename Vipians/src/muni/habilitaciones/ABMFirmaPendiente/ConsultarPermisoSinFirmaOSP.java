/*
 * ConsultarFirmaPendienteOSP.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */
package muni.habilitaciones.ABMFirmaPendiente;

import com.sun.data.provider.RowKey;
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
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.AsocServicioOsp;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import javax.faces.FacesException;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.habilitaciones.recurso.persistent.PermisoHab;
import com.trascender.presentacion.utiles.Constantes;
import java.util.ArrayList;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ConsultarPermisoSinFirmaOSP extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Permisos Sin Firmar: OySP";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ConsultarFirmaPendienteOSP";
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
        if (this.getListaDelCommunication()!=null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
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
    
    private Button btnFirmar = new Button();
    
    public Button getBtnFirmar() {
        return btnFirmar;
    }
    
    public void setBtnFirmar(Button b) {
        this.btnFirmar = b;
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

    private Label lblParcela1 = new Label();

    public Label getLblParcela1() {
        return lblParcela1;
    }

    public void setLblParcela1(Label l) {
        this.lblParcela1 = l;
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

    private TextField tfServicioOSP = new TextField();

    public TextField getTfServicioOSP() {
        return tfServicioOSP;
    }

    public void setTfServicioOSP(TextField tf) {
        this.tfServicioOSP = tf;
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

    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }

    private StaticText stDomicilioPostal = new StaticText();

    public StaticText getStDomicilioPostal() {
        return stDomicilioPostal;
    }

    public void setStDomicilioPostal(StaticText st) {
        this.stDomicilioPostal = st;
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

    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }

    private ObjectListDataProvider ldpFirmaPendienteOSP = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpFirmaPendienteOSP() {
        return ldpFirmaPendienteOSP;
    }

    public void setLdpFirmaPendienteOSP(ObjectListDataProvider oldp) {
        this.ldpFirmaPendienteOSP = oldp;
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

    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }

    private Checkbox checkbox1 = new Checkbox();

    public Checkbox getCheckbox1() {
        return checkbox1;
    }

    public void setCheckbox1(Checkbox c) {
        this.checkbox1 = c;
    }

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }

    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }

    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }

    private TextField tfNumeroCuenta = new TextField();

    public TextField getTfNumeroCuenta() {
        return tfNumeroCuenta;
    }

    public void setTfNumeroCuenta(TextField tf) {
        this.tfNumeroCuenta = tf;
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
    
    // </editor-fold>

    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ConsultarPermisoSinFirmaOSP() {
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
            log("ConsultarFirmaPendienteOSP Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

        tablePhaseListener = this.getTableSelectPhaseListener();
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
        int caso = 0;
        
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
            caso = 1;
        }
        
        // Recarga de la misma pagina por validacion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
            // no se hace nada por ahora
            recarga = true;
            // APLICAR LOGICA AQUI.. ver si es as� realmente..
            caso = 2;
        }
        
        // Pagina nueva - hacia adelante en la transaccion
        // Para el caso de las paginas de inicio de transaccion nunca entra
        if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
            this.setIdPagina(key);
            this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
            this.crearElementoPila();
            caso = 3;
        }
        
        // Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());
            caso = 4;
        }
        
        if (!recarga && caso != 0) {
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
        ep.getObjetos().add(ind++, null); // obligacion
        ep.getObjetos().add(ind++, new DocumentoOSP());
        ep.getObjetos().add(ind++, null); // Persona solicitante: personaFisica
        ep.getObjetos().add(ind++, new Parcela());
        ep.getObjetos().add(ind++, new Domicilio());
        ep.getObjetos().add(ind++, new ServicioOSP());
        //ep.getObjetos().add(ind++, new RegAlicuota());
        ep.getObjetos().add(ind++, new PlantillaObligacion()); // plantilla para generar otra obligacion
        ep.getObjetos().add(ind++, new ArrayList()); // permisos habilitantes pendientes de firma
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Obligacion obligacion = (Obligacion)this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
        DocumentoOSP documentoOSP = (DocumentoOSP) this.obtenerObjetoDelElementoPila(ind++, DocumentoOSP.class);
        ind++; // no se hace nada con la persona, solo se la muestra.
        Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        ServicioOSP servicio = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
        //RegAlicuota regAlicuota = (RegAlicuota) this.obtenerObjetoDelElementoPila(ind++, RegAlicuota.class);
        PlantillaObligacion plantillaObligacion = (PlantillaObligacion) this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class);
        ArrayList permisosSinFirmar = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        
        Object nombre = this.getTfNombre().getText();
        Object fechaInicio = this.getTfFechaInicio().getText();
        Object fechaCese = this.getTfFechaCese().getText();
        Object codigoMedidor = this.getTfCodigoMedidor().getText();

        if (nombre != null && nombre != "") documentoOSP.setNombre(nombre.toString());
        else documentoOSP.setNombre(null);
        if (fechaInicio != null && fechaInicio != "")  documentoOSP.setFechaInicioActividad(Conversor.getFechaCortaDeString(fechaInicio.toString()));
        else documentoOSP.setFechaInicioActividad(null);
        if (fechaCese != null && fechaCese != "") documentoOSP.setFechaCeseActividad(Conversor.getFechaCortaDeString(fechaCese.toString()));
        else documentoOSP.setFechaCeseActividad(null);
        if (codigoMedidor != null && codigoMedidor != "") ((AsocServicioOsp)documentoOSP.getListaAsocRegAlicuota().get(0)).setCodigoMedidor(codigoMedidor.toString());
        else ((AsocServicioOsp)documentoOSP.getListaAsocRegAlicuota().get(0)).setCodigoMedidor(null);
        
        if (parcela.getIdParcela()==-1) parcela = null;
        else documentoOSP.setParcela(parcela);
        
        if (servicio.getIdTipoAlicuota()==-1) servicio = null;
        documentoOSP.getListaRegAlicuotas().add(servicio);

//        if (regAlicuota.getIdTipoAlicuota()==-1) regAlicuota = null;
//        documentoOSP.setRegistroAlicuota(regAlicuota);

        if (domicilio.getLocalidad()==null) domicilio = null;
        documentoOSP.setDomicilio(domicilio);
        
        this.getObjectListDataProvider().commitChanges();
        permisosSinFirmar = new ArrayList(this.getObjectListDataProvider().getList());
       
        ind = 0;
        ind++;
        this.getElementoPila().getObjetos().set(ind++, documentoOSP);
        ind++;
        this.getElementoPila().getObjetos().set(ind++, parcela);
        this.getElementoPila().getObjetos().set(ind++, domicilio);
        this.getElementoPila().getObjetos().set(ind++, servicio);
        //this.getElementoPila().getObjetos().set(ind++, regAlicuota);
        this.getElementoPila().getObjetos().set(ind++, plantillaObligacion);
        this.getElementoPila().getObjetos().set(ind++, permisosSinFirmar);
    }
    
    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Obligacion obligacion = null;
        DocumentoOSP documentoOSP = null;
        Persona persona = null;
        Parcela parcela = null;
        Domicilio domicilio = null;
        ServicioOSP servicio = null;
        //RegAlicuota regAlicuota = null;
        PlantillaObligacion plantillaObligacion = null;
        ArrayList permisosSinFirmar = null;
        
        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            int posicion = ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
            
            if ((seleccionado instanceof Persona) && (posicion == 0)) {
                persona = (Persona) seleccionado;
                domicilio = persona.getDomicilio();
                this.getElementoPila().getObjetos().set(2, persona);
                this.getElementoPila().getObjetos().set(4, domicilio);
                this.getRequestBean1().setObjetoSeleccion(null);
            }
        }
        
        this.acomodarSeleccionado();
        
        if (this.getRequestBean1().getRespuestaABM() != null) {
            Object respuesta = this.getRequestBean1().getRespuestaABM();
            int posicionEP = -1;
            
            if (respuesta instanceof Domicilio) {
                int posicion = ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
                this.getElementoPila().getObjetos().set(posicion, respuesta);
            }
        }
        
        if (this.getRequestBean1().getObjetoABM() != null) {
            
            obligacion = (Obligacion) this.getRequestBean1().getObjetoABM();
            
            long id = obligacion.getIdObligacion();
            
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
                obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(id);
            } catch (Exception ex) {
                log("Error Description", ex);
            }
            
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
                documentoOSP = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().getDocumentoOSP(obligacion);
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_getDocumentoHabilitanteOSP:", ex);
                error(NOMBRE_PAGINA+" - No se pudo obtener el Documento OSP: " + ex.getMessage());
            }
            
            try {
                this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
                //Mines: cambiaste getPArcela por getParcelaRfr()
                parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(documentoOSP.getParcela().getIdParcela());
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_getParcelaPorId:", ex);
                error(NOMBRE_PAGINA+" - No se pudo obtener la Parcela asociada: " + ex.getMessage());
            }
            
            persona = obligacion.getPersona();
            domicilio = (Domicilio) documentoOSP.getDomicilio();
            
            servicio = (ServicioOSP) documentoOSP.getRegistroAlicuota();
//            regAlicuota = (RegAlicuota) documentoOSP.getRegistroAlicuota();
            
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
                permisosSinFirmar = new ArrayList(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaPermisosHabAFirmar(obligacion));
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_findListaPermisosHabAFirmar:", ex);
                error(NOMBRE_PAGINA+" - No se pudo obtener la Lista de Permisos sin Firmar: " + ex.getMessage());
            }
            
            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, obligacion);
            this.getElementoPila().getObjetos().set(ind++, documentoOSP);
            this.getElementoPila().getObjetos().set(ind++, persona);
            this.getElementoPila().getObjetos().set(ind++, parcela);
            this.getElementoPila().getObjetos().set(ind++, domicilio);
            this.getElementoPila().getObjetos().set(ind++, servicio);
            //this.getElementoPila().getObjetos().set(ind++, regAlicuota);
            this.getElementoPila().getObjetos().set(ind++, plantillaObligacion);
            this.getElementoPila().getObjetos().set(ind++, permisosSinFirmar);
            
        }
        
        ind = 0;
        ind++;
        documentoOSP = (DocumentoOSP) this.obtenerObjetoDelElementoPila(ind++, DocumentoOSP.class);
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        servicio = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
        //regAlicuota = (RegAlicuota) this.obtenerObjetoDelElementoPila(ind++, RegAlicuota.class);
        plantillaObligacion = (PlantillaObligacion) this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class);
        permisosSinFirmar = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        
        this.ocultarCodigoMedidor(servicio);

        this.getTfNombre().setText(documentoOSP.getNombre());
        if (documentoOSP.getFechaInicioActividad() != null) this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoOSP.getFechaInicioActividad()));
        if (documentoOSP.getFechaCeseActividad() != null) this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoOSP.getFechaCeseActividad()));
        if (documentoOSP.getNumeroCuenta() != null) this.getTfNumeroCuenta().setText(documentoOSP.getNumeroCuenta().toString());
        if (documentoOSP.getNumeroSubCuenta() != null) this.getTfNumeroSubCuenta().setText(documentoOSP.getNumeroSubCuenta().toString());
        this.getTfPersona().setText(persona.toString());
        this.getTfParcela().setText(parcela.toString());
        this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
        this.getTfServicioOSP().setText(servicio.toString());
        //this.getTfServicioOSP().setText(regAlicuota.toString());
        this.getTfCodigoMedidor().setText(((AsocServicioOsp)documentoOSP.getListaAsocRegAlicuota().get(0)).getCodigoMedidor());
        this.getTfCoeficienteZonal().setText(documentoOSP.getCoeficienteZonal());
        
        this.getObjectListDataProvider().setList(permisosSinFirmar);
        this.setListaDelCommunication(permisosSinFirmar);
    }
 
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpFirmaPendienteOSP();
    }
    
    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaPermisosSinFirmarPorObligacion();
    }
    
    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaPermisosSinFirmarPorObligacion(lista);
    }

    private TableSelectPhaseListener getTableSelectPhaseListener() {
        // CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getTablePhaseListenerPermisosSinFirmarPorObligacion();
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
    
    private int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para trabajar con checkboxes de seleccion">
    
    private TableSelectPhaseListener tablePhaseListener;
    
    /**
     * Setter for selected
     * @param object Value to set the property to
     * Bind this to the checkbox's selected property
     * If the object's value matches selectedValue
     * then the checkbox is considered to be selected.
     */
    public void setSelected(Object object) {
        RowKey rowKey = tableRowGroup1.getRowKey();
        if (rowKey != null) {
            tablePhaseListener.setSelected(rowKey, object);
        }
    }
    
    /**
     * Getter for selected.
     * @return Object value for the current row's checkbox
     */
    public Object getSelected(){
        RowKey rowKey = tableRowGroup1.getRowKey();
        return tablePhaseListener.getSelected(rowKey);
    }
    
    /**
     * Getter for selectedValue
     * @return Object value of the component when it is selected
     * Bind this property to the checkbox's selectedValue property
     * If the object's value matches selectedValue
     * then the checkbox is considered to be selected.
     * In this case, when the checkbox's selected property
     * returns its RowKey value, then it is considered to be
     * selected.
     */
    public Object getSelectedValue() {
        RowKey rowKey = tableRowGroup1.getRowKey();
        return (rowKey != null) ? rowKey.getRowId() : null;
    }
    
    /**
     * Getter for currentRowSelected
     * @return Boolean true if the checkbox in the current row is selected
     * Bind this property to the row's selected property
     */
    public boolean isCurrentRowSelected() {
        RowKey rowKey = tableRowGroup1.getRowKey();
        return tablePhaseListener.isSelected(rowKey);
    }
    
    // </editor-fold>
    // </editor-fold>
    
    
    public String btnFirmar_action() {
        // CAMBIAR: Revisar el metodo completo.
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            try {
                this.guardarEstadoObjetosUsados();
                
                ArrayList permisosSinFirmar = (ArrayList) this.obtenerObjetoDelElementoPila(7, ArrayList.class);
                
                RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();
                int cantidad = selectedRowKeys.length;
                int cantidadFirmados = 0;
                
                try {
                    this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
                    
                    for(int i=0; i < cantidad; i++){
                        String rowId = selectedRowKeys[i].getRowId();
                        RowKey rowKey = this.getObjectListDataProvider().getRowKey(rowId);
                        Object obj = this.getObjectListDataProvider().getObjects()[getNroFila(rowKey.toString())];
                        this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().firmarDocHabilitante((PermisoHab)obj, null);
                        cantidadFirmados++;
                    }
                    
                } catch (Exception ex) {
                    log(CASO_NAVEGACION+"_firmarDocHabilitanteError:", ex);
                    error(NOMBRE_PAGINA+" - Firmar Permisos: " + ex.getMessage());
                }
                
                if (cantidad > 0 && cantidadFirmados > 0) {
                    String mensaje = "El Permiso ha sido firmado satisfactoriamente.";
                    if (cantidadFirmados > 1) mensaje = "Los " + cantidadFirmados +  " Permisos han sido firmados satisfactoriamente.";
                    retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);
                    info(mensaje);
                }
                else {
                    retorno = null;
                    warn("Debe seleccionar al menos un Permiso a Firmar.");
                }
                
            } catch (Exception ex) {
                if (ex instanceof TrascenderException) {
                    int codigoError = ((TrascenderException)ex).getCodeTrascenderException();
                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) retorno = null;
                    else retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
                }
                log(CASO_NAVEGACION+"_FirmarError:", ex);
                error(NOMBRE_PAGINA+" - Firmar: " + ex.getMessage());
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


    private void ocultarCodigoMedidor(ServicioOSP servicioOSP) {
        if (servicioOSP != null) {
            //ServicioOSP servicioOSP = (ServicioOSP) regAlicuota;
            boolean muestro = servicioOSP.isMedido();
            this.getLblCodigoMedidor().setRendered(muestro);
            this.getTfCodigoMedidor().setRendered(muestro);
        }
        return;
    }
}
