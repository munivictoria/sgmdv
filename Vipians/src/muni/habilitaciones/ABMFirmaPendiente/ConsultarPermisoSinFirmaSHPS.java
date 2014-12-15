/*
 * ConsultarPermisoSinFirmaSHPS.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */
package muni.habilitaciones.ABMFirmaPendiente;

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
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import java.util.ArrayList;
import javax.faces.FacesException;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.sun.rave.web.ui.component.TextArea;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import javax.faces.convert.DateTimeConverter;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.habilitaciones.recurso.persistent.PermisoHab;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ConsultarPermisoSinFirmaSHPS extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Permisos Sin Firmar: SHPS";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ConsultarPermisoSinFirmaSHPS";
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
        if (this.getListaDelCommunicationTabla2()!=null) {
            this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());
        }
        if (this.getListaDelCommunicationTabla3()!=null) {
            this.getObjectListDataProviderTabla3().setList(this.getListaDelCommunicationTabla3());
        }
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
    
    private TextField tfNumeroInscripcion = new TextField();

    public TextField getTfNumeroInscripcion() {
        return tfNumeroInscripcion;
    }

    public void setTfNumeroInscripcion(TextField tf) {
        this.tfNumeroInscripcion = tf;
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

    private ObjectListDataProvider ldpLocalComercialSHPS = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpLocalComercialSHPS() {
        return ldpLocalComercialSHPS;
    }

    public void setLdpLocalComercialSHPS(ObjectListDataProvider oldp) {
        this.ldpLocalComercialSHPS = oldp;
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

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }

    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }

    private ObjectListDataProvider ldpTransporteVehicularSHPS = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpTransporteVehicularSHPS() {
        return ldpTransporteVehicularSHPS;
    }

    public void setLdpTransporteVehicularSHPS(ObjectListDataProvider oldp) {
        this.ldpTransporteVehicularSHPS = oldp;
    }

    private ObjectListDataProvider ldpFirmaPendienteSHPS = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpFirmaPendienteSHPS() {
        return ldpFirmaPendienteSHPS;
    }

    public void setLdpFirmaPendienteSHPS(ObjectListDataProvider oldp) {
        this.ldpFirmaPendienteSHPS = oldp;
    }

    private Label label27 = new Label();

    public Label getLabel27() {
        return label27;
    }

    public void setLabel27(Label l) {
        this.label27 = l;
    }

    private Label label28 = new Label();

    public Label getLabel28() {
        return label28;
    }

    public void setLabel28(Label l) {
        this.label28 = l;
    }

    private Table table2 = new Table();

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table t) {
        this.table2 = t;
    }

    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup trg) {
        this.tableRowGroup2 = trg;
    }

    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
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

    private TableColumn tableColumn11 = new TableColumn();

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tc) {
        this.tableColumn11 = tc;
    }

    private RadioButton radioButton2 = new RadioButton();

    public RadioButton getRadioButton2() {
        return radioButton2;
    }

    public void setRadioButton2(RadioButton rb) {
        this.radioButton2 = rb;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
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

    private Label lblLibretaSanitaria = new Label();

    public Label getLblLibretaSanitaria() {
        return lblLibretaSanitaria;
    }

    public void setLblLibretaSanitaria(Label l) {
        this.lblLibretaSanitaria = l;
    }

    private TextField tfLibretaSanitaria = new TextField();

    public TextField getTfLibretaSanitaria() {
        return tfLibretaSanitaria;
    }

    public void setTfLibretaSanitaria(TextField tf) {
        this.tfLibretaSanitaria = tf;
    }

    private TableColumn tableColumn13 = new TableColumn();

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tc) {
        this.tableColumn13 = tc;
    }

    private StaticText staticText13 = new StaticText();

    public StaticText getStaticText13() {
        return staticText13;
    }

    public void setStaticText13(StaticText st) {
        this.staticText13 = st;
    }

    private TableColumn tableColumn14 = new TableColumn();

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tc) {
        this.tableColumn14 = tc;
    }

    private TextArea textArea1 = new TextArea();

    public TextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(TextArea ta) {
        this.textArea1 = ta;
    }

    private TableColumn tableColumn15 = new TableColumn();

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tc) {
        this.tableColumn15 = tc;
    }

    private TextArea textArea2 = new TextArea();

    public TextArea getTextArea2() {
        return textArea2;
    }

    public void setTextArea2(TextArea ta) {
        this.textArea2 = ta;
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

    private TableColumn tableColumn17 = new TableColumn();

    public TableColumn getTableColumn17() {
        return tableColumn17;
    }

    public void setTableColumn17(TableColumn tc) {
        this.tableColumn17 = tc;
    }

    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }

    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }

    private StaticText staticText15 = new StaticText();

    public StaticText getStaticText15() {
        return staticText15;
    }

    public void setStaticText15(StaticText st) {
        this.staticText15 = st;
    }

    private StaticText staticText16 = new StaticText();

    public StaticText getStaticText16() {
        return staticText16;
    }

    public void setStaticText16(StaticText st) {
        this.staticText16 = st;
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

    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }

    private TextField tfRubro = new TextField();

    public TextField getTfRubro() {
        return tfRubro;
    }

    public void setTfRubro(TextField tf) {
        this.tfRubro = tf;
    }

    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }

    private TextField tfDenominacionEntidad = new TextField();

    public TextField getTfDenominacionEntidad() {
        return tfDenominacionEntidad;
    }

    public void setTfDenominacionEntidad(TextField tf) {
        this.tfDenominacionEntidad = tf;
    }

    private StaticText stDomicilioPostal = new StaticText();

    public StaticText getStDomicilioPostal() {
        return stDomicilioPostal;
    }

    public void setStDomicilioPostal(StaticText st) {
        this.stDomicilioPostal = st;
    }

    private Checkbox checkbox1 = new Checkbox();

    public Checkbox getCheckbox1() {
        return checkbox1;
    }

    public void setCheckbox1(Checkbox c) {
        this.checkbox1 = c;
    }

    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }

    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    
    // </editor-fold>

    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ConsultarPermisoSinFirmaSHPS() {
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
            log("ConsultarPermisoSinFirmaSHPS Initialization Failure", e);
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
        ep.getObjetos().add(ind++, new DocumentoSHPS());
        ep.getObjetos().add(ind++, null); // Persona solicitante: personaFisica o personaJuridica
        ep.getObjetos().add(ind++, new ArrayList());  //listaLibretaSanitaria
        ep.getObjetos().add(ind++, new Domicilio());
        ep.getObjetos().add(ind++, new ArrayList());    // Tabla 1: locales comerciales
        ep.getObjetos().add(ind++, new ArrayList());    // Tabla 2: transportes vehiculares
        ep.getObjetos().add(ind++, new ArrayList());    // Tabla 3: permisos sin firmar
        ep.getObjetos().add(ind++, new RegAlicuota());  // regAlicuota = rubro

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        DocumentoSHPS documentoSHPS = (DocumentoSHPS) this.obtenerObjetoDelElementoPila(ind++, DocumentoSHPS.class);
        ind++; // no se hace nada con la persona, solo se la muestra.
        ArrayList listaLibretasSanitarias = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        ArrayList locales = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        ArrayList transportes = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        ArrayList permisosSinFirmar = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        RegAlicuota rubro = (RegAlicuota) this.obtenerObjetoDelElementoPila(ind++, RegAlicuota.class);
        
        Object numeroInscripcion = this.getTfNumeroInscripcion().getText();
        Object nombre = this.getTfNombre().getText();
        Object fechaInicio = this.getTfFechaInicio().getText();
        Object fechaCese = this.getTfFechaCese().getText();
        Object denominacionEntidad = this.getTfDenominacionEntidad().getText();
        
        if (numeroInscripcion != null && numeroInscripcion != "") documentoSHPS.setNumeroInscripcion(numeroInscripcion.toString());
        else documentoSHPS.setNumeroInscripcion(null);
        if (nombre != null && nombre != "") documentoSHPS.setNombre(nombre.toString());
        else documentoSHPS.setNombre(null);
        if (fechaInicio != null && fechaInicio != "")  documentoSHPS.setFechaInicioActividad(Conversor.getFechaCortaDeString(fechaInicio.toString()));
        else documentoSHPS.setFechaInicioActividad(null);
        if (fechaCese != null && fechaCese != "") documentoSHPS.setFechaCeseActividad(Conversor.getFechaCortaDeString(fechaCese.toString()));
        else documentoSHPS.setFechaCeseActividad(null);
        if (denominacionEntidad != null && denominacionEntidad != "") documentoSHPS.setDenominacionEntidad(denominacionEntidad.toString());
        else documentoSHPS.setDenominacionEntidad(null);
        
//        if (libretaSanitaria.getIdLibretaSanitaria()==-1) libretaSanitaria = null;
//        documentoSHPS.setLibretaSanitaria(libretaSanitaria);
        
        if (domicilio.getLocalidad()==null) domicilio = null;
        documentoSHPS.setDomicilio(domicilio);
        
        if (rubro.getIdTipoAlicuota()==-1) rubro = null;
        documentoSHPS.getListaRegAlicuotas().add(rubro);
        
        this.getObjectListDataProvider().commitChanges();
        locales = (ArrayList) this.getObjectListDataProvider().getList();
        if (locales.size()<=0) locales = null;
        if (documentoSHPS.getListaLocalesComerciales() != null) {
            documentoSHPS.getListaLocalesComerciales().clear();
            if (locales != null) documentoSHPS.getListaLocalesComerciales().addAll(locales);
        }
        this.setListaDelCommunication(locales);
        
        this.getObjectListDataProviderTabla2().commitChanges();
        transportes = (ArrayList) this.getObjectListDataProviderTabla2().getList();
        if (transportes.size()<=0) transportes = null;
        if (documentoSHPS.getListaTransportesVehiculares() != null) {
            documentoSHPS.getListaTransportesVehiculares().clear();
            if (transportes != null) documentoSHPS.getListaTransportesVehiculares().addAll(transportes);
        }
        this.setListaDelCommunicationTabla2(transportes);
        
        this.getObjectListDataProviderTabla3().commitChanges();
        permisosSinFirmar = new ArrayList(this.getObjectListDataProviderTabla3().getList());
        
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, documentoSHPS);
        ind++;
        this.getElementoPila().getObjetos().set(ind++, listaLibretasSanitarias);
        this.getElementoPila().getObjetos().set(ind++, domicilio);
        this.getElementoPila().getObjetos().set(ind++, locales);
        this.getElementoPila().getObjetos().set(ind++, transportes);
        this.getElementoPila().getObjetos().set(ind++, permisosSinFirmar);
        this.getElementoPila().getObjetos().set(ind++, rubro);
    }
    
    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        DocumentoSHPS documentoSHPS = null;
        Persona persona = null;
        //LibretaSanitaria libretaSanitaria = null;
        Domicilio domicilio = null;
        ArrayList locales = null;
        ArrayList transportes = null;
        ArrayList permisosSinFirmar = null;
        ArrayList listaLibretasSanitarias = null;
        RegAlicuota rubro = null;

        if (this.getRequestBean1().getObjetoSeleccion() != null){
            listaLibretasSanitarias = (ArrayList)this.obtenerObjetoDelElementoPila(2, ArrayList.class);
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            listaLibretasSanitarias.add(seleccionado);
            this.getElementoPila().getObjetos().set(2, listaLibretasSanitarias);
        
        }
        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            int posicionEP = ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
            
            if (seleccionado instanceof LocalComercial) {
                //posicionEP = 4;
                LocalComercial localSeleccionado = (LocalComercial) seleccionado;
                locales = (ArrayList) this.obtenerObjetoDelElementoPila(posicionEP, ArrayList.class);
                
                LocalComercial deLaTabla = null;
                boolean esta = false;
                int i = -1;
                while (i+1 < locales.size() && !esta) {
                    i++;
                    deLaTabla = (LocalComercial) locales.get(i);
                    esta = (deLaTabla.getIdLocalComercial() == localSeleccionado.getIdLocalComercial());
                    //esta = deLaTabla.getNumeroInscripcion().equalsIgnoreCase(localRespuesta.getNumeroInscripcion());
                }
                if (!esta) locales.add(localSeleccionado);
                else warn("El Local Comercial que intenta agregar ya se encuentra en la lista.");
                
                this.getElementoPila().getObjetos().set(posicionEP, locales);
                this.getRequestBean1().setObjetoSeleccion(null);
            }
            
            if (seleccionado instanceof TransporteVehicular) {
                //posicionEP = 5;
                TransporteVehicular transpSeleccionado = (TransporteVehicular) seleccionado;
                transportes = (ArrayList) this.obtenerObjetoDelElementoPila(posicionEP, ArrayList.class);
                
                TransporteVehicular deLaTabla = null;
                boolean esta = false;
                int i = -1;
                while (i+1 < transportes.size() && !esta) {
                    i++;
                    deLaTabla = (TransporteVehicular) transportes.get(i);
                    esta = (deLaTabla.getIdTransporteVehicular() == transpSeleccionado.getIdTransporteVehicular());
                }
                if (!esta) transportes.add(transpSeleccionado);
                else warn("El Transporte Vehicular que intenta agregar ya se encuentra en la lista.");
                
                this.getElementoPila().getObjetos().set(posicionEP, transportes);
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
            
            Obligacion obligacion = (Obligacion) this.getRequestBean1().getObjetoABM();
            documentoSHPS = null;

            long id = obligacion.getIdObligacion();
            
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
                obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(id);
            } catch (Exception ex) {
                log("Error Description", ex);
            }
            
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(this.getSessionBean1().getLlave());
                documentoSHPS = this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getDocumentoHabilitanteSHPS(obligacion);
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_getDocumentoHabilitanteSHPS:", ex);
                error(NOMBRE_PAGINA+" - No se pudo obtener el Documento SHPS: " + ex.getMessage());
            }
            
            documentoSHPS.setObligacion(obligacion);
            
            persona = obligacion.getPersona();
           // libretaSanitaria = documentoSHPS.getLibretaSanitaria();
            domicilio = documentoSHPS.getDomicilio();
            locales = new ArrayList(documentoSHPS.getListaLocalesComerciales());
            transportes = new ArrayList(documentoSHPS.getListaTransportesVehiculares());
            
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
                permisosSinFirmar = new ArrayList(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaPermisosHabAFirmar(obligacion));
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_findListaPermisosHabAFirmar:", ex);
                error(NOMBRE_PAGINA+" - No se pudo obtener la Lista de Permisos sin Firmar: " + ex.getMessage());
            }
            
            rubro = documentoSHPS.getRegistroAlicuota();

            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, documentoSHPS);
            this.getElementoPila().getObjetos().set(ind++, persona);
            this.getElementoPila().getObjetos().set(ind++, listaLibretasSanitarias);
            this.getElementoPila().getObjetos().set(ind++, domicilio);
            this.getElementoPila().getObjetos().set(ind++, locales);
            this.getElementoPila().getObjetos().set(ind++, transportes);
            this.getElementoPila().getObjetos().set(ind++, permisosSinFirmar);
            this.getElementoPila().getObjetos().set(ind++, rubro);
        }
        
        ind = 0;
        documentoSHPS = (DocumentoSHPS) this.obtenerObjetoDelElementoPila(ind++, DocumentoSHPS.class);
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        listaLibretasSanitarias = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        locales = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        transportes = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        permisosSinFirmar = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        rubro = (RegAlicuota) this.obtenerObjetoDelElementoPila(ind++, RegAlicuota.class);
        
        this.ocultarLibretaSanitaria(persona);
        
        this.getTfNumeroInscripcion().setText(documentoSHPS.getNumeroInscripcion());
        this.getTfNombre().setText(documentoSHPS.getNombre());
        if (documentoSHPS.getFechaInicioActividad() != null) this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoSHPS.getFechaInicioActividad()));
        if (documentoSHPS.getFechaCeseActividad() != null) this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoSHPS.getFechaCeseActividad()));
        this.getTfPersona().setText(persona.toString());
        //this.getTfLibretaSanitaria().setText(libretaSanitaria.toString());
        this.getTfDenominacionEntidad().setText(documentoSHPS.getDenominacionEntidad());
        this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
        this.getTfRubro().setText(rubro.toString());
        
        // Tabla 1
        this.getObjectListDataProvider().setList(locales);
        this.setListaDelCommunication(locales);
        // Tabla 2
        this.getObjectListDataProviderTabla2().setList(transportes);
        this.setListaDelCommunicationTabla2(transportes);
        // Tabla 3
        this.getObjectListDataProviderTabla3().setList(permisosSinFirmar);
        this.setListaDelCommunicationTabla3(permisosSinFirmar);
//        // Tabla 4
//        this.getObjectListDataProviderTabla4().setList(listaLibretasSanitarias);
//        this.setListaDelCommunicationTabla4(listaLibretasSanitarias);
    }
    
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpLocalComercialSHPS();
    }
    
    private ObjectListDataProvider getObjectListDataProviderTabla2() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpTransporteVehicularSHPS();
    }
    
    private ObjectListDataProvider getObjectListDataProviderTabla3() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpFirmaPendienteSHPS();
    }
//    private ObjectListDataProvider getObjectListDataProviderTabla3() {
//        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
//        return this.getLdp();
//    }
    // Tabla 1
    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaLocalesComercialesSHPS();
    }
    
    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaLocalesComercialesSHPS(lista);
    }
    // Tabla 2
    private ArrayList getListaDelCommunicationTabla2() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaTransportesVehicularesSHPS();
    }
    
    private void setListaDelCommunicationTabla2(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaTransportesVehicularesSHPS(lista);
    }
    // Tabla 3
    private ArrayList getListaDelCommunicationTabla3() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaPermisosSinFirmarPorObligacion();
    }
    
    private void setListaDelCommunicationTabla3(ArrayList lista) {
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
    // Tabla 1
    private void limpiarTabla() {
        this.getObjectListDataProvider().getList().clear();
    }
    // Tabla 2
    private void limpiarTabla2() {
        this.getObjectListDataProviderTabla2().getList().clear();
    }
    // Tabla 3
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
    public String getCurrentRow() {
        return tableRowGroup1.getRowKey().getRowId();
    }
    public void setCurrentRow(int row) {
    }
    private Object lastSelected = null;
    public Object getRBSelected() {
        String sv = (String)radioButton1.getSelectedValue();
        return sv.equals(lastSelected) ? sv : null;
    }
    public void setRBSelected(Object selected){
        if (selected != null) {
            lastSelected = selected;
        }
    }
    private int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
    }
    // Tabla 2
    public String getCurrentRow2() {
        return tableRowGroup2.getRowKey().getRowId();
    }
    public void setCurrentRow2(int row) {
    }
    private Object lastSelected2 = null;
    public Object getRBSelected2() {
        String sv = (String)radioButton2.getSelectedValue();
        return sv.equals(lastSelected2) ? sv : null;
    }
    public void setRBSelected2(Object selected){
        if (selected != null) {
            lastSelected2 = selected;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar la fila recientemente seleccionada">
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
        
        if (rk!=null) {
            while (!encontrado && inicioPagina < cantRegistros) {
                this.getTableRowGroup1().setFirst((int)inicioPagina);
                posicionEnPagina = 0;
                while (!encontrado && posicionEnPagina < cantRegistrosPorPagina){
                    encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int)posicionEnPagina].equals(rk);
                    if (!encontrado) {
                        posicionEnPagina++;
                        posicionGlobal++;
                    }
                }
                if (!encontrado) inicioPagina += cantRegistrosPorPagina;
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
            };
            
            int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
            this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
            lastSelected = new Long(index).toString();
        }
    }
    
    public Long getInicioPagina(Long posicionGlobal) {
        long inicioPagina = 0 ;
        long posicionEnPagina = 0;
        long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
        
        inicioPagina = ( posicionGlobal.longValue() / cantRegistrosPorPagina ) * cantRegistrosPorPagina ;
        return new Long(inicioPagina);
    }
    
    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            rk = this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {}
        return rk;
    }

    // Tabla 2
    public RowKey getSeleccionado2() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup2");
            rk = this.getObjectListDataProviderTabla2().getRowKey(aRowId);
        } catch (Exception ex) {}
        return rk;
    }
    // Tabla 3
    public RowKey getSeleccionado3() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup3");
            rk = this.getObjectListDataProviderTabla3().getRowKey(aRowId);
        } catch (Exception ex) {}
        return rk;
    }
    // </editor-fold>
    
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
        RowKey rowKey = tableRowGroup3.getRowKey();
        if (rowKey != null) {
            tablePhaseListener.setSelected(rowKey, object);
        }
    }
    
    /**
     * Getter for selected.
     * @return Object value for the current row's checkbox
     */
    public Object getSelected(){
        RowKey rowKey = tableRowGroup3.getRowKey();
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
        RowKey rowKey = tableRowGroup3.getRowKey();
        return (rowKey != null) ? rowKey.getRowId() : null;
    }
    
    /**
     * Getter for currentRowSelected
     * @return Boolean true if the checkbox in the current row is selected
     * Bind this property to the row's selected property
     */
    public boolean isCurrentRowSelected() {
        RowKey rowKey = tableRowGroup3.getRowKey();
        return tablePhaseListener.isSelected(rowKey);
    }
    
    // </editor-fold>
    // </editor-fold>
    
    
    public String btnFirmar_action() {
        // CAMBIAR: Revisar el metodo completo.
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
//            try {
//                this.guardarEstadoObjetosUsados();
//                
//                //Obligacion obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(0, Obligacion.class);
//                //DocumentoOSP documentoOSP = (DocumentoOSP) this.obtenerObjetoDelElementoPila(1, DocumentoOSP.class);
//                
//                ArrayList permisosSinFirmar = (ArrayList) this.obtenerObjetoDelElementoPila(6, ArrayList.class);
////                ArrayList permisosAFirmar = new ArrayList();
//                int cantidad = 0;
//                
//                try {
//                    // Inicializo el Array de objetos seleccionados
//                    RowKey[] selectedRowKeys = getTableRowGroup3().getSelectedRowKeys();
//                    this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
//                    
//                    for(int i=0; i < selectedRowKeys.length; i++){
//                        String rowId = selectedRowKeys[i].getRowId();
//                        RowKey rowKey = this.getObjectListDataProviderTabla3().getRowKey(rowId);
//                        Object obj = this.getObjectListDataProviderTabla3().getObjects()[getNroFila(rowKey.toString())];
//                        this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().firmarDocHabilitante((PermisoHab)obj, null);
//                        cantidad++;
//                    }
//                    
//                } catch (Exception ex) {
//                    log(CASO_NAVEGACION+"_firmarDocHabilitanteError:", ex);
//                    error(NOMBRE_PAGINA+" - Firmar Permisos: " + ex.getMessage());
//                }
//
//                if (cantidad > 0) {
//                    String mensaje = "El Permiso ha sido firmado satisfactoriamente.";
//                    if (cantidad > 1) mensaje = "Los " + cantidad +  " Permisos han sido firmados satisfactoriamente.";
//                    retorno = this.prepararParaVolver();
//                    info(mensaje);
//                }
//                else {
//                    retorno = null;
//                    warn("Debe seleccionar al menos un Permiso a Firmar.");
//                }
//                
//            } catch (Exception ex) {
//                if (ex instanceof TrascenderException) {
//                    int codigoError = ((TrascenderException)ex).getCodeTrascenderException();
//                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) retorno = null;
//                    else retorno = this.prepararParaVolver();
//                }
//                log(CASO_NAVEGACION+"_FirmarError:", ex);
//                error(NOMBRE_PAGINA+" - Firmar: " + ex.getMessage());
//            }
            
            try {
                this.guardarEstadoObjetosUsados();
                
                ArrayList permisosSinFirmar = (ArrayList) this.obtenerObjetoDelElementoPila(6, ArrayList.class);
                
                RowKey[] selectedRowKeys = getTableRowGroup3().getSelectedRowKeys();
                int cantidad = selectedRowKeys.length;
                int cantidadFirmados = 0;
                
                try {
                    this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
                    
                    for(int i=0; i < cantidad; i++){
                        String rowId = selectedRowKeys[i].getRowId();
                        RowKey rowKey = this.getObjectListDataProviderTabla3().getRowKey(rowId);
                        Object obj = this.getObjectListDataProviderTabla3().getObjects()[getNroFila(rowKey.toString())];
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
            
            this.setListaDelCommunication(null);
            // Tabla 2
            this.setListaDelCommunicationTabla2(null);
            // Tabla 3
            this.setListaDelCommunicationTabla3(null);
            
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    
    private void ocultarLibretaSanitaria(Persona persona) {
        if (persona instanceof PersonaJuridica) {
            this.getLblLibretaSanitaria().setRendered(false);
            this.getTfLibretaSanitaria().setRendered(false);
        }
        return;
    }

}
