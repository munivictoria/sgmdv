/*
 * ModificarDocEspPFO.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpPlanFinanciacionObra.ABMDocEspPFO;
import java.util.ArrayList;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.convert.DateTimeConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

// comment for ana
/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ModificarDocEspPFO extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Modificar Obligaci\363n: PFO";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarDocEspPFO";
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
// ariel - no se muestran las modificaciones
//        if (this.getListaDelCommunicationTabla3()!=null) {
//            this.getObjectListDataProviderTabla3().setList(this.getListaDelCommunicationTabla3());
//        }
        dateTimeConverter1.setTimeStyle("short");
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

    private ObjectListDataProvider ldpLogModificacionesPFO = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpLogModificacionesPFO() {
        return ldpLogModificacionesPFO;
    }

    public void setLdpLogModificacionesPFO(ObjectListDataProvider oldp) {
        this.ldpLogModificacionesPFO = oldp;
    }

    private Label label28 = new Label();

    public Label getLabel28() {
        return label28;
    }

    public void setLabel28(Label l) {
        this.label28 = l;
    }

    private Table table3 = new Table();

    public Table getTable3() {
        return table3;
    }

    public void setTable3(Table t) {
        this.table3 = t;
    }

    private TableRowGroup tableRowGroup3 = new TableRowGroup();

    public TableRowGroup getTableRowGroup3() {
        return tableRowGroup3;
    }

    public void setTableRowGroup3(TableRowGroup trg) {
        this.tableRowGroup3 = trg;
    }

    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }

    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }

    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }

    private RadioButton radioButton3 = new RadioButton();

    public RadioButton getRadioButton3() {
        return radioButton3;
    }

    public void setRadioButton3(RadioButton rb) {
        this.radioButton3 = rb;
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

    private TableColumn tableColumn16 = new TableColumn();

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tc) {
        this.tableColumn16 = tc;
    }

    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }

    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }

    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }

    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }

    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private TextField tfPlanCuenta = new TextField();

    public TextField getTfPlanCuenta() {
        return tfPlanCuenta;
    }

    public void setTfPlanCuenta(TextField tf) {
        this.tfPlanCuenta = tf;
    }

    private HtmlAjaxCommandButton btnLimpiarPlanCuenta = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarPlanCuenta() {
		return btnLimpiarPlanCuenta;
	}
	public void setBtnLimpiarPlanCuenta(HtmlAjaxCommandButton btnLimpiarPlanCuenta) {
		this.btnLimpiarPlanCuenta = btnLimpiarPlanCuenta;
	}

	private Button btnSeleccionarPlanCuenta = new Button();

    public Button getBtnSeleccionarPlanCuenta() {
        return btnSeleccionarPlanCuenta;
    }

    public void setBtnSeleccionarPlanCuenta(Button b) {
        this.btnSeleccionarPlanCuenta = b;
    }

    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }

    private TextField tfObra = new TextField();

    public TextField getTfObra() {
        return tfObra;
    }

    public void setTfObra(TextField tf) {
        this.tfObra = tf;
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

    private TextField tfFechaCese = new TextField();

    public TextField getTfFechaCese() {
        return tfFechaCese;
    }

    public void setTfFechaCese(TextField tf) {
        this.tfFechaCese = tf;
    }

    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }

    private ObjectListDataProvider ldpCuadrasAfectadasPFO = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpCuadrasAfectadasPFO() {
        return ldpCuadrasAfectadasPFO;
    }

    public void setLdpCuadrasAfectadasPFO(ObjectListDataProvider oldp) {
        this.ldpCuadrasAfectadasPFO = oldp;
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

    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }

    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }

    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    private Button btnSeleccionarPersonasFisica1 = new Button();

    public Button getBtnSeleccionarPersonasFisica1() {
        return btnSeleccionarPersonasFisica1;
    }

    public void setBtnSeleccionarPersonasFisica1(Button b) {
        this.btnSeleccionarPersonasFisica1 = b;
    }
    private Button btnSeleccionarPersonaJuridica1 = new Button();

    public Button getBtnSeleccionarPersonaJuridica1() {
        return btnSeleccionarPersonaJuridica1;
    }

    public void setBtnSeleccionarPersonaJuridica1(Button b) {
        this.btnSeleccionarPersonaJuridica1 = b;
    }
    private Button btnSeleccionarDomicilioPostal1 = new Button();

    public Button getBtnSeleccionarDomicilioPostal1() {
        return btnSeleccionarDomicilioPostal1;
    }

    public void setBtnSeleccionarDomicilioPostal1(Button b) {
        this.btnSeleccionarDomicilioPostal1 = b;
    }
    private HtmlAjaxCommandButton btnLimpiarDomicilioPostal1 = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarDomicilioPostal1() {
		return btnLimpiarDomicilioPostal1;
	}
	public void setBtnLimpiarDomicilioPostal1(HtmlAjaxCommandButton btnLimpiarDomicilioPostal1) {
		this.btnLimpiarDomicilioPostal1 = btnLimpiarDomicilioPostal1;
	}

	private Button btnSeleccionarDomicilioSolicitante1 = new Button();

    public Button getBtnSeleccionarDomicilioSolicitante1() {
        return btnSeleccionarDomicilioSolicitante1;
    }

    public void setBtnSeleccionarDomicilioSolicitante1(Button b) {
        this.btnSeleccionarDomicilioSolicitante1 = b;
    }
    private Button btnSeleccionarDomicilioParcela1 = new Button();

    public Button getBtnSeleccionarDomicilioParcela1() {
        return btnSeleccionarDomicilioParcela1;
    }

    public void setBtnSeleccionarDomicilioParcela1(Button b) {
        this.btnSeleccionarDomicilioParcela1 = b;
    }

    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ModificarDocEspPFO() {
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
            log("ModificarDocEspPFO Initialization Failure", e);
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
    public void destroy() {
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, null); // Obligacion
        ep.getObjetos().add(ind++, new DocumentoPlanObra());
        ep.getObjetos().add(ind++, null); // Persona solicitante: personaFisica o personaJuridica
        ep.getObjetos().add(ind++, new Obra());
        ep.getObjetos().add(ind++, new Parcela());
        ep.getObjetos().add(ind++, new Domicilio());
        ep.getObjetos().add(ind++, new PlanCuentaObra());
        ep.getObjetos().add(ind++, new ArrayList());    // log de modificaciones
        ep.getObjetos().add(ind++, new ArrayList());    // cuadras afectadas
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Obligacion obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
        DocumentoPlanObra documentoPFO = (DocumentoPlanObra) this.obtenerObjetoDelElementoPila(ind++, DocumentoPlanObra.class);
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        Obra obra = (Obra) this.obtenerObjetoDelElementoPila(ind++, Obra.class);
        Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        PlanCuentaObra planCuenta = (PlanCuentaObra) this.obtenerObjetoDelElementoPila(ind++, PlanCuentaObra.class);
        ArrayList modificaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        ArrayList cuadrasAfectadas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        
        Object nombre = this.getTfNombre().getText();
        Object fechaInicio = this.getTfFechaInicio().getText();
        Object fechaCese = this.getTfFechaCese().getText();

        if (nombre != null && nombre != "") documentoPFO.setNombre(nombre.toString());
        else documentoPFO.setNombre(null);
        if (fechaInicio != null && fechaInicio != "")  documentoPFO.setFechaInicioActividad(Conversor.getFechaCortaDeString(fechaInicio.toString()));
        else documentoPFO.setFechaInicioActividad(null);
        if (fechaCese != null && fechaCese != "") documentoPFO.setFechaCeseActividad(Conversor.getFechaCortaDeString(fechaCese.toString()));
        else documentoPFO.setFechaCeseActividad(null);

        if (persona.getIdPersona() == -1) persona = null;
        obligacion.setPersona(persona);
        
        if (planCuenta.getIdPlanCuentaPorObra() == -1) planCuenta = null;
        documentoPFO.setPlanCuentaObra(planCuenta);
        
        if (domicilio.getLocalidad()==null) domicilio = null;
        documentoPFO.setDomicilio(domicilio);
        
        ind = 0;
        ind++;
        this.getElementoPila().getObjetos().set(ind++, documentoPFO);
        this.getElementoPila().getObjetos().set(ind++, persona);
        this.getElementoPila().getObjetos().set(ind++, obra);
        this.getElementoPila().getObjetos().set(ind++, parcela);
        this.getElementoPila().getObjetos().set(ind++, domicilio);
        this.getElementoPila().getObjetos().set(ind++, planCuenta);
    }
    
    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Obligacion obligacion = null;
        DocumentoPlanObra documentoPFO= null;
        Persona persona = null;
        Obra obra = null;
        Parcela parcela = null;
        Domicilio domicilio = null;
        PlanCuentaObra planCuenta = null;
        ArrayList modificaciones = null;
        ArrayList cuadrasAfectadas = null;
        
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
            
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().setLlave(this.getSessionBean1().getLlave());
                documentoPFO = this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().getDocumentoHabilitantePFO(obligacion);
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_getDocumentoHabilitantePFO:", ex);
                error(NOMBRE_PAGINA+" - No se pudo obtener el Documento PFO: " + ex.getMessage());
            }
            
            persona = obligacion.getPersona();
            obra = documentoPFO.getObra();
            
            try {
                this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
                parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(documentoPFO.getParcela().getIdParcela());
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_getParcelaPorId:", ex);
                error(NOMBRE_PAGINA+" - No se pudo obtener la Parcela asociada: " + ex.getMessage());
            }
            
            domicilio = documentoPFO.getDomicilio();
            planCuenta = documentoPFO.getPlanCuentaObra();
            // ariel - no hay modificaciones
            //modificaciones = new ArrayList(documentoPFO.getListaModificaciones());

            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().setLlave(this.getSessionBean1().getLlave());
                cuadrasAfectadas = new ArrayList( this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().getListaCuadrasAfectadasFromDocumentoPFO(documentoPFO) );
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_getListaCuadrasAfectadasFromDocumentoPFO:", ex);
                error(NOMBRE_PAGINA+" - No se pudo obtener la lista de Cuadras Afectadas del Documento PFO: " + ex.getMessage());
            }
            
            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, obligacion);
            this.getElementoPila().getObjetos().set(ind++, documentoPFO);
            this.getElementoPila().getObjetos().set(ind++, persona);
            this.getElementoPila().getObjetos().set(ind++, obra);
            this.getElementoPila().getObjetos().set(ind++, parcela);
            this.getElementoPila().getObjetos().set(ind++, domicilio);
            this.getElementoPila().getObjetos().set(ind++, planCuenta);
            this.getElementoPila().getObjetos().set(ind++, modificaciones);
            this.getElementoPila().getObjetos().set(ind++, cuadrasAfectadas);
        }
        
        ind = 0;
        ind++;
        documentoPFO = (DocumentoPlanObra) this.obtenerObjetoDelElementoPila(ind++, DocumentoPlanObra.class);
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        obra = (Obra) this.obtenerObjetoDelElementoPila(ind++, Obra.class);
        parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        planCuenta = (PlanCuentaObra) this.obtenerObjetoDelElementoPila(ind++, PlanCuentaObra.class);
        modificaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        cuadrasAfectadas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        
        this.getTfNombre().setText(documentoPFO.getNombre());
        this.getTfObra().setText(obra.toString());
        if (documentoPFO.getFechaInicioActividad() != null) this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoPFO.getFechaInicioActividad()));
        if (documentoPFO.getFechaCeseActividad() != null) this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoPFO.getFechaCeseActividad()));
        this.getTfPersona().setText(persona.toString());
        this.getTfPlanCuenta().setText(planCuenta.toString());
        this.getTfParcela().setText(parcela.toString());
        if (domicilio.getLocalidad() != null) this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
        
        this.getObjectListDataProvider().setList(cuadrasAfectadas);
        this.setListaDelCommunication(cuadrasAfectadas);
        
        this.getObjectListDataProviderTabla3().setList(modificaciones);
        this.setListaDelCommunicationTabla3(modificaciones);
        
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpCuadrasAfectadasPFO();
    }    
    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaCuadrasAfectadasPFO();
    }
    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaCuadrasAfectadasPFO(lista);
    }
    
    private ObjectListDataProvider getObjectListDataProviderTabla3() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpLogModificacionesPFO();
    }    
    private ArrayList getListaDelCommunicationTabla3() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaLogModificacionesPFO();
    }
    private void setListaDelCommunicationTabla3(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaLogModificacionesPFO(lista);
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

    private void limpiarTabla() {
        this.getObjectListDataProvider().getList().clear();
    }
    
    private void limpiarTabla3() {
        this.getObjectListDataProviderTabla3().getList().clear();
    }    
    
    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo!=null) campo.setText(null);
        } catch (Exception ex) { }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar RaddioButton">
    private int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
    }
    // Tabla 3
    public String getCurrentRow3() {
        return tableRowGroup3.getRowKey().getRowId();
    }
    public void setCurrentRow3(int row) {
    }
    private Object lastSelected3 = null;
    public Object getRBSelected3() {
        String sv = (String)radioButton3.getSelectedValue();
        return sv.equals(lastSelected3) ? sv : null;
    }
    public void setRBSelected3(Object selected){
        if (selected != null) {
            lastSelected3 = selected;
        }
    }    
    // </editor-fold>
    
    // </editor-fold>

    public String btnSeleccionarPersona_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 2;
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPersonaFisica";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

  /*  public String btnLimpiarPersona_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if(ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(2, this.getTfPersona());
            this.guardarEstadoObjetosUsados();
            
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }*/


    public String btnSeleccionarPlanCuenta_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 6;
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            // Algoritmo para pasar como parametro la obra
            Obra obra = (Obra) this.obtenerObjetoDelElementoPila(3, Obra.class);
            this.getRequestBean1().setObjetoSeleccion(obra);
            
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPlanCuentaObra";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarPlanCuenta_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if(ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(6, this.getTfPlanCuenta());
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
        int posicionObjetoSeleccionado = 5;
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
            Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(posicionObjetoSeleccionado, Domicilio.class);
            Localidad localidad = domicilio.getLocalidad();
            
            if (localidad != null) {
                this.getRequestBean1().setObjetoABM(domicilio);
                retorno = "ModificarDomicilio";
            }
            else {
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
            this.limpiarObjeto(5, null);
            this.getStDomicilioPostal().setText(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public String btnSeleccionarDomicilioSolicitante_action() {
        /*String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 5;
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
            Persona persona = (Persona) this.obtenerObjetoDelElementoPila(2, Persona.class);
            Domicilio domicilio = persona.getDomicilio();
            this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
            
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;*/
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 5;
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
            Object pers = this.getElementoPila().getObjetos().get(2);
            Persona persona = null;
            if (pers != null) persona = (Persona) pers;
            if (persona != null && persona.getIdPersona() != -1) {
                Domicilio domicilio = persona.getDomicilio();
                this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
            }
            else {
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
        
        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 5;
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
            Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(4, Parcela.class);
            if (parcela.getIdParcela() != -1) {
                Domicilio domicilio = parcela.getDomicilioParcelario();
                this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
            }
            else {
                warn("Debe existir la Parcela para obtener el Domicilio Parcelario.");
            }

            
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    
    public String btnGuardar_action() {
        // CAMBIAR: Revisar el metodo completo.
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            try {
                this.guardarEstadoObjetosUsados();
                
                // CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[2];
                String[] nomNoVacios = new String[2];
                
                int pos = 0;
                noVacios[pos] = this.getTfPersona();
                nomNoVacios[pos++] = "Persona Solicitante";
                noVacios[pos] = this.getTfPlanCuenta();
                nomNoVacios[pos++] = "Plan de Cuenta";

                v.noSonVacios(noVacios, nomNoVacios);

                Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(5, Domicilio.class);
                if (domicilio.getLocalidad()==null) {
                    String msg = "Debe seleccionar un Domicilio Postal.";
                    v.getErrores().add(msg);
                }
                
                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                // CAMBIAR: Objeto a guardar
                DocumentoPlanObra documentoPFO = (DocumentoPlanObra) this.getElementoPila().getObjetos().get(1);
                this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().setLlave(this.getSessionBean1().getLlave());
                documentoPFO = (DocumentoPlanObra)this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().updateDocumentoPFO(documentoPFO);
                
                info("La Obligaci\363n de PFO se modific\363 exitosamente.");
                retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);
            } catch (Exception ex) {
                if (ex instanceof TrascenderException) {
                    int codigoError = ((TrascenderException)ex).getCodeTrascenderException();
                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) retorno = null;
                    else retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
                }
                log(CASO_NAVEGACION+"_ModificarError:", ex);
                error(NOMBRE_PAGINA+" - Modificar: " + ex.getMessage());
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
            
            //APLICAR LOGICA AQUI...
            this.setListaDelCommunication(null);
            this.setListaDelCommunicationTabla3(null);
            
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarPersonaFisica_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 2;
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
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
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPersonaJuridica";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

}
