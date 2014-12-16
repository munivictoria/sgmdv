///*
// * EliminarExencion.java
// *
// * Copyright Trascender
// */
//package muni.saic.ABMExencionRegistroDeuda;
//
//import com.sun.data.provider.RowKey;
//import com.sun.data.provider.impl.ObjectListDataProvider;
//import com.sun.rave.web.ui.appbase.AbstractPageBean;
//import com.sun.rave.web.ui.component.Body;
//import com.sun.rave.web.ui.component.Button;
//import com.sun.rave.web.ui.component.DropDown;
//import com.sun.rave.web.ui.component.Form;
//import com.sun.rave.web.ui.component.Head;
//import com.sun.rave.web.ui.component.HiddenField;
//import com.sun.rave.web.ui.component.Html;
//import com.sun.rave.web.ui.component.Label;
//import com.sun.rave.web.ui.component.Link;
//import com.sun.rave.web.ui.component.MessageGroup;
//import com.sun.rave.web.ui.component.Page;
//import com.sun.rave.web.ui.component.PanelGroup;
//import com.sun.rave.web.ui.component.Script;
//import com.sun.rave.web.ui.component.StaticText;
//import com.sun.rave.web.ui.component.Table;
//import com.sun.rave.web.ui.component.TableColumn;
//import com.sun.rave.web.ui.component.TableRowGroup;
//import com.sun.rave.web.ui.component.TextArea;
//import com.sun.rave.web.ui.component.TextField;
//import com.sun.rave.web.ui.model.Option;
//import com.sun.rave.web.ui.model.SingleSelectOptionsList;
//import com.trascender.framework.recurso.persistent.DigestoMunicipal;
//import com.trascender.framework.recurso.transients.Periodo;
//import com.trascender.presentacion.navegacion.ElementoPila;
//import com.trascender.presentacion.validadores.Validador;
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//import javax.faces.FacesException;
//import javax.faces.component.UIComponent;
//import com.trascender.framework.util.Periodicidad;
//import com.trascender.framework.util.Util;
//import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
//import com.trascender.presentacion.conversores.Conversor;
//import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
//import com.trascender.presentacion.utiles.Constantes;
//import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;
//import com.trascender.saic.recurso.persistent.LiquidacionTasa;
//import com.trascender.saic.recurso.persistent.RegistroDeuda;
//import com.trascender.saic.recurso.persistent.RegistroExencionRegistroDeuda;
//import java.util.Calendar;
//import java.util.HashSet;
//import java.util.Iterator;
//import javax.faces.convert.DateTimeConverter;
//import javax.faces.convert.NumberConverter;
//
///**
// * <p>Page bean that corresponds to a similarly named JSP page.  This
// * class contains component definitions (and initialization code) for
// * all components that you have defined on this page, as well as
// * lifecycle methods and event handlers where you may add behavior
// * to respond to incoming events.</p>
// */
//public class EliminarExencionRegistroDeuda extends AbstractPageBean {
//    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
//    private int __placeholder;
//
//    /**
//     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
//     * This method is automatically generated, so any user-specified code inserted
//     * here is subject to being replaced.</p>
//     */
//    private void _init() throws Exception {
//         if (this.getListaDelCommunication()!=null) {
//            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
//        }
//
//        Option[] op = null;
//        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Periodicidad.values(),"cap");
//
//        ddPeriodicidadDefaultOptions.setOptions(op);
//        ddPeriodicidadCuotaDefaultOptions.setOptions(op);
//        numberConverter1.setPattern("$ #,##0.00");
//        dateTimeConverter1.setTimeZone(null);
//        dateTimeConverter1.setPattern("dd/MM/yyyy");
//        dateTimeConverter1.setTimeStyle("full");
//
//    }
//
//    private Page page1 = new Page();
//
//    public Page getPage1() {
//        return page1;
//    }
//
//    public void setPage1(Page p) {
//        this.page1 = p;
//    }
//
//    private Html html1 = new Html();
//
//    public Html getHtml1() {
//        return html1;
//    }
//
//    public void setHtml1(Html h) {
//        this.html1 = h;
//    }
//
//    private Head head1 = new Head();
//
//    public Head getHead1() {
//        return head1;
//    }
//
//    public void setHead1(Head h) {
//        this.head1 = h;
//    }
//
//    private Link link1 = new Link();
//
//    public Link getLink1() {
//        return link1;
//    }
//
//    public void setLink1(Link l) {
//        this.link1 = l;
//    }
//
//    private Body body1 = new Body();
//
//    public Body getBody1() {
//        return body1;
//    }
//
//    public void setBody1(Body b) {
//        this.body1 = b;
//    }
//
//    private Form form1 = new Form();
//
//    public Form getForm1() {
//        return form1;
//    }
//
//    public void setForm1(Form f) {
//        this.form1 = f;
//    }
//
//    private NumberConverter numberConverter1 = new NumberConverter();
//
//    public NumberConverter getNumberConverter1() {
//        return numberConverter1;
//    }
//
//    public void setNumberConverter1(NumberConverter numberConverter1) {
//        this.numberConverter1 = numberConverter1;
//    }
//
//    private StaticText stTitulo = new StaticText();
//
//    public StaticText getStTitulo() {
//        return stTitulo;
//    }
//
//    public void setStTitulo(StaticText st) {
//        this.stTitulo = st;
//    }
//
//    private Label lblNombre = new Label();
//
//    public Label getLblNombre() {
//        return lblNombre;
//    }
//
//    public void setLblNombre(Label l) {
//        this.lblNombre = l;
//    }
//
//    private TextField tfNombre = new TextField();
//
//    public TextField getTfNombre() {
//        return tfNombre;
//    }
//
//    public void setTfNombre(TextField tf) {
//        this.tfNombre = tf;
//    }
//
//    private TextField tfAnio = new TextField();
//
//    public TextField getTfAnio() {
//        return tfAnio;
//    }
//
//    public void setTfAnio(TextField tf) {
//        this.tfAnio = tf;
//    }
//    private Label label3 = new Label();
//
//    public Label getLabel3() {
//        return label3;
//    }
//    public void setLabel3(Label l) {
//        this.label3 = l;
//    }
//
//    private Label label4 = new Label();
//
//    public Label getLabel4() {
//        return label4;
//    }
//    public void setLabel4(Label l) {
//        this.label4 = l;
//    }
//
//    private Label label5 = new Label();
//
//    public Label getLabel5() {
//        return label5;
//    }
//    public void setLabel5(Label l) {
//        this.label5 = l;
//    }
//
//    private Label label6 = new Label();
//
//    public Label getLabel6() {
//        return label6;
//    }
//    public void setLabel6(Label l) {
//        this.label6 = l;
//    }
//
//    private Label label7 = new Label();
//
//    public Label getLabel7() {
//        return label7;
//    }
//    public void setLabel7(Label l) {
//        this.label7 = l;
//    }
//
//    private Label label8 = new Label();
//
//    public Label getLabel8() {
//        return label8;
//    }
//    public void setLabel8(Label l) {
//        this.label8 = l;
//    }
//
//    private Label lblPorcentaje = new Label();
//
//    public Label getLblPorcentaje() {
//        return lblPorcentaje;
//    }
//
//    public void setLblPorcentaje(Label l) {
//        this.lblPorcentaje = l;
//    }
//
//    private TextField tfPorcentaje = new TextField();
//
//    public TextField getTfPorcentaje() {
//        return tfPorcentaje;
//    }
//
//    public void setTfPorcentaje(TextField tf) {
//        this.tfPorcentaje = tf;
//    }
//
//    private TextField tfPeriodo = new TextField();
//
//    public TextField getTfPeriodo() {
//        return tfPeriodo;
//    }
//
//    public void setTfPeriodo(TextField tf) {
//        this.tfPeriodo = tf;
//    }
//    private Label lblMotivo = new Label();
//
//    public Label getLblMotivo() {
//        return lblMotivo;
//    }
//
//    public void setLblMotivo(Label l) {
//        this.lblMotivo = l;
//    }
//
//    private TextArea taMotivo = new TextArea();
//
//    public TextArea getTaMotivo() {
//        return taMotivo;
//    }
//
//    public void setTaMotivo(TextArea ta) {
//        this.taMotivo = ta;
//    }
//
//    private Label lblDigestoMunicipal = new Label();
//
//    public Label getLblDigestoMunicipal() {
//        return lblDigestoMunicipal;
//    }
//
//    public void setLblDigestoMunicipal(Label l) {
//        this.lblDigestoMunicipal = l;
//    }
//
//    private TextField tfDigestoMunicipal = new TextField();
//
//    public TextField getTfDigestoMunicipal() {
//        return tfDigestoMunicipal;
//    }
//
//    public void setTfDigestoMunicipal(TextField tf) {
//        this.tfDigestoMunicipal = tf;
//    }
//
//    private Table table1 = new Table();
//
//    public Table getTable1() {
//        return table1;
//    }
//
//    public void setTable1(Table t) {
//        this.table1 = t;
//    }
//
//    private TableRowGroup tableRowGroup1 = new TableRowGroup();
//
//    public TableRowGroup getTableRowGroup1() {
//        return tableRowGroup1;
//    }
//
//    public void setTableRowGroup1(TableRowGroup trg) {
//        this.tableRowGroup1 = trg;
//    }
//    private TableColumn tableColumn1 = new TableColumn();
//
//    public TableColumn getTableColumn1() {
//        return tableColumn1;
//    }
//
//    public void setTableColumn1(TableColumn tc) {
//        this.tableColumn1 = tc;
//    }
//
//    private TableColumn tcObligacion = new TableColumn();
//
//    public TableColumn getTcObligacion () {
//        return tcObligacion ;
//    }
//
//    public void setTcObligacion (TableColumn tc) {
//        this.tcObligacion  = tc;
//    }
//
//    public String getCurrentRow3() {
//        return tableRowGroup1.getRowKey().getRowId();
//    }
//    public void setCurrentRow3(int row) {
//    }
//
//   /* private Object lastSelected = null;
//
//    public Object getRBSelected() {
//        String sv = (String)radioButton1.getSelectedValue();
//        return sv.equals(lastSelected) ? sv : null;
//    }
//    public void setRBSelected(Object selected){
//        if (selected != null) {
//            lastSelected = selected;
//        }
//    }*/
//    private ObjectListDataProvider ldpExencion = new ObjectListDataProvider();
//
//    public ObjectListDataProvider getLdpExencion() {
//        return ldpExencion;
//    }
//
//    public void setLdpExencion(ObjectListDataProvider oldp) {
//        this.ldpExencion = oldp;
//    }
//
//    private TableColumn tcPeriodo = new TableColumn();
//
//    public TableColumn getTcPeriodo() {
//        return tcPeriodo;
//    }
//
//    public void setTcPeriodo(TableColumn tcPeriodo) {
//        this.tcPeriodo = tcPeriodo;
//    }
//
//    private TableColumn tcMonto = new TableColumn();
//
//    public TableColumn getTcMonto() {
//        return tcMonto;
//    }
//
//    public void setTcMonto(TableColumn tcMonto) {
//        this.tcMonto = tcMonto;
//    }
//
//    private TableColumn tcMontoExento = new TableColumn();
//
//    public TableColumn getTcMontoExento() {
//        return tcMontoExento;
//    }
//
//    public void setTcMontoExento(TableColumn tcMontoExento) {
//        this.tcMontoExento = tcMontoExento;
//    }
//
//    private TableColumn tcFechaVencimiento = new TableColumn();
//
//    public TableColumn getTcFechaVencimiento() {
//        return tcFechaVencimiento;
//    }
//
//    public void setTcFechaVencimiento(TableColumn tcFechaVencimiento) {
//        this.tcFechaVencimiento = tcFechaVencimiento;
//    }
//    private TableColumn tcReferenciaNotaHCD = new TableColumn();
//
//    public TableColumn getTcReferenciaNotaHCD() {
//        return tcReferenciaNotaHCD;
//    }
//
//    public void setTcReferenciaNotaHCD(TableColumn tcReferenciaNotaHCD) {
//        this.tcReferenciaNotaHCD = tcReferenciaNotaHCD;
//    }
//
//    private StaticText stPeriodo = new StaticText();
//
//    public void setStPeriodo(StaticText stPeriodo) {
//        this.stPeriodo = stPeriodo;
//    }
//
//    public StaticText getStPeriodo() {
//        return stPeriodo;
//    }
//
//    private StaticText stObligacion = new StaticText();
//
//    public StaticText getStObligacion() {
//        return stObligacion;
//    }
//
//    public void setStObligacion(StaticText st) {
//        this.stObligacion = st;
//    }
//
//    private StaticText staticText5 = new StaticText();
//
//    public StaticText getStaticText5() {
//        return staticText5;
//    }
//
//    public void setStaticText5(StaticText st) {
//        this.staticText5 = st;
//    }
//
//    private StaticText staticText7 = new StaticText();
//
//    public StaticText getStaticText7() {
//        return staticText7;
//    }
//
//    public void setStaticText7(StaticText st) {
//        this.staticText7 = st;
//    }
//
//    private StaticText stMonto = new StaticText();
//
//    public void setStMonto(StaticText stMonto) {
//        this.stMonto = stMonto;
//    }
//
//    public StaticText getStMonto() {
//        return stMonto;
//    }
//     private StaticText stMontoExento = new StaticText();
//
//    public void setStMontoExento(StaticText stMontoExento) {
//        this.stMontoExento = stMontoExento;
//    }
//
//    public StaticText getStMontoExento() {
//        return stMontoExento;
//    }
//    private StaticText stFechaVencimiento = new StaticText();
//
//    public void setStFechaVencimiento(StaticText stFechaVencimiento) {
//        this.stFechaVencimiento = stFechaVencimiento;
//    }
//
//    public StaticText getStFechaVencimiento() {
//        return stFechaVencimiento;
//    }
//    private StaticText stReferenciaNotaHCD = new StaticText();
//
//    public void setStReferenciaNotaHCD(StaticText stReferenciaNotaHCD) {
//        this.stReferenciaNotaHCD = stReferenciaNotaHCD;
//    }
//
//    public StaticText getStReferenciaNotaHCD() {
//        return stReferenciaNotaHCD;
//    }
//    
//    private DropDown ddPeriodicidad = new DropDown();
//
//    public DropDown getDdPeriodicidad() {
//        return ddPeriodicidad;
//    }
//
//    public void setDdPeriodicidad(DropDown dd) {
//        this.ddPeriodicidad = dd;
//    }
//
//    private DropDown ddPeriodicidadCuota = new DropDown();
//
//    public DropDown getDdPeriodicidadCuota() {
//        return ddPeriodicidadCuota;
//    }
//
//    public void setDdPeriodicidadCuota(DropDown dd) {
//        this.ddPeriodicidadCuota = dd;
//    }
//
//    private SingleSelectOptionsList ddPeriodicidadDefaultOptions = new SingleSelectOptionsList();
//
//    public SingleSelectOptionsList getDdPeriodicidadDefaultOptions() {
//        return ddPeriodicidadDefaultOptions;
//    }
//
//    public void setDdPeriodicidadDefaultOptions(SingleSelectOptionsList ssol) {
//        this.ddPeriodicidadDefaultOptions = ssol;
//    }
//
//    private SingleSelectOptionsList ddPeriodicidadCuotaDefaultOptions = new SingleSelectOptionsList();
//
//    public SingleSelectOptionsList getDdPeriodicidadCuotaDefaultOptions() {
//        return ddPeriodicidadCuotaDefaultOptions;
//    }
//
//    public void setDdPeriodicidadCuotaDefaultOptions(SingleSelectOptionsList ssol) {
//        this.ddPeriodicidadCuotaDefaultOptions = ssol;
//    }
//
//    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
//
//    public DateTimeConverter getDateTimeConverter1() {
//        return dateTimeConverter1;
//    }
//
//    public void setDateTimeConverter1(DateTimeConverter dtc) {
//        this.dateTimeConverter1 = dtc;
//    }
//
//    private Button btnEliminar = new Button();
//
//    public Button getBtnEliminar() {
//        return btnEliminar;
//    }
//
//    public void setBtnEliminar(Button b) {
//        this.btnEliminar = b;
//    }
//
//    private TextField tfNotaHCD = new TextField();
//
//    public TextField getTfNotaHCD() {
//        return tfNotaHCD;
//    }
//
//    public void setTfNotaHCD(TextField tf) {
//        this.tfNotaHCD = tf;
//    }
//
//    private Button btnCancelar = new Button();
//
//    public Button getBtnCancelar() {
//        return btnCancelar;
//    }
//
//    public void setBtnCancelar(Button b) {
//        this.btnCancelar = b;
//    }
//
//    private StaticText stSeparador = new StaticText();
//
//    public StaticText getStSeparador() {
//        return stSeparador;
//    }
//
//    public void setStSeparador(StaticText st) {
//        this.stSeparador = st;
//    }
//
//    private MessageGroup messageGroup1 = new MessageGroup();
//
//    public MessageGroup getMessageGroup1() {
//        return messageGroup1;
//    }
//
//    public void setMessageGroup1(MessageGroup mg) {
//        this.messageGroup1 = mg;
//    }
//
//    private HiddenField hidIdPagina = new HiddenField();
//
//    public HiddenField getHidIdPagina() {
//        return hidIdPagina;
//    }
//
//    public void setHidIdPagina(HiddenField hf) {
//        this.hidIdPagina = hf;
//    }
//
//    private HiddenField hidIdSubSesion = new HiddenField();
//
//    public HiddenField getHidIdSubSesion() {
//        return hidIdSubSesion;
//    }
//
//    public void setHidIdSubSesion(HiddenField hf) {
//        this.hidIdSubSesion = hf;
//    }
//    
//    private Script scriptValidador = new Script();
//
//    public Script getScriptValidador() {
//        return scriptValidador;
//    }
//
//    public void setScriptValidador(Script scriptValidador) {
//        this.scriptValidador = scriptValidador;
//    }
//
//    // </editor-fold>
//
//    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
//
//    // Atributos propios de la pagina.
//    // CAMBIAR: Vincular a los campos ocultos.
//    private Long idPagina = null;
//    private Long idSubSesion = null;
//    public Long getIdPagina() { return idPagina; }
//    public void setIdPagina(Long idPagina) { this.idPagina = idPagina; }
//    public Long getIdSubSesion() { return idSubSesion; }
//    public void setIdSubSesion(Long idSubSesion) { this.idSubSesion = idSubSesion; }
//
//    public ElementoPila getElementoPila() {
//        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
//    }
//
//    // CAMBIAR: Constantes que varian segun la pagina.
//    // cantidad de objetos administrados por la pagina
//    private final int CANTIDAD_OBJETOS = 2;
//    // nombre a mostrar en la ruta de la operacion.
//    private final String NOMBRE_PAGINA = "Eliminar Exencion";
//    // nombre del caso de navegacion para llegar a esta pagina.
//    private final String CASO_NAVEGACION = "EliminarExencionRegistroDeuda";
//    // nombre del caso de navegacion para llegar a la pagina de caducidad
//    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
//    // nombre del caso de navegacion para llegar a la pagina que se debe
//    // mostrar al salir de la pagina de caducidad
//    private final String CASO_NAV_POST_CADUCIDAD = "Main";
//    // es una pagina que no necesita idSubSesion
//    // Inicia una sub sesion.
//    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
//
//
//
//    // </editor-fold>
//
//
//    /**
//     * <p>Construir una instancia de bean de página.</p>
//     */
//    public EliminarExencionRegistroDeuda() {
//    }
//
//    /**
//     * <p>Devolver una referencia al bean de datos con �mbito.</p>
//     */
//    protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
//        return (muni.CommunicationExcepcionesBean)getBean("CommunicationExcepcionesBean");
//    }
//
//    /**
//     * <p>Devolver una referencia al bean de datos con �mbito.</p>
//     */
//    protected muni.CommunicationCajaBean getCommunicationCajaBean() {
//        return (muni.CommunicationCajaBean)getBean("CommunicationCajaBean");
//    }
//
//    /**
//     * <p>Devolver una referencia al bean de datos con �mbito.</p>
//     */
//    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
//        return (muni.CommunicationSAICBean)getBean("CommunicationSAICBean");
//    }
//
//    /**
//     * <p>Devolver una referencia al bean de datos con ámbito.</p>
//     */
//    protected muni.RequestBean1 getRequestBean1() {
//        return (muni.RequestBean1)getBean("RequestBean1");
//    }
//
//
//    /**
//     * <p>Devolver una referencia al bean de datos con ámbito.</p>
//     */
//    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
//        return (muni.CommunicationHabilitacionesBean)getBean("CommunicationHabilitacionesBean");
//    }
//
//
//    /**
//     * <p>Devolver una referencia al bean de datos con ámbito.</p>
//     */
//    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
//        return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
//    }
//
//
//    /**
//     * <p>Devolver una referencia al bean de datos con ámbito.</p>
//     */
//    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
//        return (muni.CommunicationComprasBean)getBean("CommunicationComprasBean");
//    }
//
//
//    /**
//     * <p>Devolver una referencia al bean de datos con ámbito.</p>
//     */
//    protected muni.ComunicationBean getComunicationBean() {
//        return (muni.ComunicationBean)getBean("ComunicationBean");
//    }
//
//
//    /**
//     * <p>Devolver una referencia al bean de datos con ámbito.</p>
//     */
//    protected muni.ApplicationBean1 getApplicationBean1() {
//        return (muni.ApplicationBean1)getBean("ApplicationBean1");
//    }
//
//
//    /**
//     * <p>Devolver una referencia al bean de datos con ámbito.</p>
//     */
//    protected muni.SessionBean1 getSessionBean1() {
//        return (muni.SessionBean1)getBean("SessionBean1");
//    }
//
//
//    /**
//     * <p>Método de devolución de llamada al que se llama cuando se navega hasta esta página,
//     * ya sea directamente mediante un URL o de manera indirecta a través de la navegación de páginas.
//     * Puede personalizar este método para adquirir recursos que se necesitarán
//     * para los controladores de eventos y métodos del proceso, sin tener en cuenta si esta
//     * página realiza procesamiento de devolución de envíos.</p>
//     *
//     * <p>Tenga en cuenta que si la petición actual es una devolución de envío, los valores
//     * de propiedad de los componentes <strong>no</strong> representan ningún
//     * valor enviado con esta petición.  En su lugar, representan los
//     * valores de propiedades que se guardaron para esta vista cuando se procesó.</p>
//     */
//    public void init() {
//        // Realizar iniciaciones heredadas de la superclase
//        super.init();
//        // Realizar inicio de aplicación que debe finalizar
//        // *antes* de que se inicien los componentes administrados
//        // TODO - Eliminar código de inicio propio aquí
//
//        // <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
//        // Iniciar componentes administrados automáticamente
//        // *Nota* - esta lógica NO debe Eliminarse
//        try {
//            _init();
//        } catch (Exception e) {
//            log("EliminarExencion Initialization Failure", e);
//            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
//        }
//        // </editor-fold>
//        // Realizar inicio de aplicación que debe finalizar
//        // *después* de que se inicien los componentes administrados
//        // TODO - Eliminar código de inicio propio aquí
//
//      }
//
//    /**
//     * <p>Método de devolución de llamada al que se llama cuando el árbol de componentes se ha
//     * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este método
//     * <strong>sólo</strong> se llamará en una petición de devolución de envío que
//     * esté procesando el envío de un formulario.  Puede personalizar este método para asignar
//     * recursos necesarios para los controladores de eventos.</p>
//     */
//    public void preprocess() {
//    }
//
//    /**
//     * <p>Método de devolución de llamada al que se llama justo antes del procesamiento.
//     * <strong>Sólo</strong> se llamará a este método en la página que
//     * se procesa, no se llamará, por ejemplo, en una página que
//     * ha procesado una devolución de envío y a continuación ha navegado hasta otra página.  Puede personalizar
//     * este método para asignar recursos necesarios para procesar
//     * esta página.</p>
//     */
//    /**
//     * <p>M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento.
//     * <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que
//     * se procesa, no se llamar�, por ejemplo, en una p�gina que
//     * ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina.  Puede personalizar
//     * este m�todo para asignar recursos necesarios para procesar
//     * esta p�gina.</p>
//     */
//    public void prerender() {
//        boolean existeIdSubSesionReq = false;
//        boolean existeIdPaginaReq    = false;
//        boolean existeIdSubSesionPag = false;
//        boolean existeIdPaginaPag    = false;
//        boolean recarga              = false;
//
//        if(this.getRequestBean1()!=null) {
//            existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
//            existeIdPaginaReq    = (this.getRequestBean1().getIdPagina() != null);
//        }
//
//        this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
//        this.setIdPagina((Long) this.getHidIdPagina().getText());
//
//        existeIdSubSesionPag = this.getIdSubSesion() != null;
//        existeIdPaginaPag    = this.getIdPagina() != null;
//
//        // Pagina nueva - Inicio de transaccion
//        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
//            if(this.PUEDE_SER_PAGINA_INICIAL) {
//                Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
//                this.setIdPagina(key);
//                this.setIdSubSesion(key);
//                this.crearElementoPila();
//            }
//        }
//
//        // Recarga de la misma pagina por validacion
//        if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
//            // no se hace nada por ahora
//            recarga = true;
//            // .. ver si es as� realmente..
//        }
//
//        // Pagina nueva - hacia adelante en la transaccion
//        // Para el caso de las paginas de inicio de transaccion nunca entra
//        if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
//            Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
//            this.setIdPagina(key);
//            this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
//            this.crearElementoPila();
//
//             if (this.getListaDelCommunication() != null &&
//                    this.getListaDelCommunication().size() > 0) {
//                    try {
//                        this.refrescarTabla();
//                    }catch (Exception ex){
//                  //       this.limpiarTabla();
//                    }
//            }
//        }
//
//        // Pagina nueva - hacia atras en la transaccion
//        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
//            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
//            this.setIdPagina(ep.getIdPagina());
//            this.setIdSubSesion(ep.getIdSubSesion());
//        }
//
//        if (!recarga) {
//            try {
//                this.mostrarEstadoObjetosUsados();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
//
//    }
//
//    /**
//     * <p>Método de devolución de llamada al que se llama cuando se completa el procesamiento de
//     * esta petición, si se llamó al método <code>init()</code> (sin tener en cuenta
//     * si se trata de la página que se ha procesado o no).  Puede personalizar este
//     * método para liberar los recursos adquiridos en los métodos <code>init()</code>,
//     * <code>preprocess()</code> o <code>prerender()</code> (o
//     * durante la ejecución de un controlador de eventos).</p>
//     */
//    public void destroy() {
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
//    private ElementoPila EliminarObjetosAElementoPila(ElementoPila ep) {
//        int ind = 0;
//
//        //CAMBIAR: settear los objetos administrados por la pagina
//       ep.getObjetos().add(ind++, new ExencionRegistroDeuda());
//       ep.getObjetos().add(ind++, new DigestoMunicipal());
//       ep.getObjetos().add(ind++, null); //anio
//       ep.getObjetos().add(ind++, null); //periodicidad
//       ep.getObjetos().add(ind++, null); //periodoNum
//
//
//       // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
//        ep.getObjetos().add(ind++, new Integer(0));
//        return ep;
//    }
//
//    private void guardarEstadoObjetosUsados() {
//        // CAMBIAR: Obtener los valores de los campos y
//        //          asignarlos a los atributos de los objetos de la pagina
//        int ind=0;
//        ExencionRegistroDeuda exencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(ind++, ExencionRegistroDeuda.class);
//        DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
//        Integer anio = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
//        Periodicidad periodicidad = (Periodicidad) this.obtenerObjetoDelElementoPila(ind++, Periodicidad.class);
//        Integer periodoNumero = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
//
//        Object nombre = this.getTfNombre().getText();
//        Object porcentaje= this.tfPorcentaje.getText();
//        Object motivo = this.taMotivo.getText();
//        Object anioTexto = this.tfAnio.getText();
//        Object periodicidadSelected = this.ddPeriodicidad.getSelected();
//        Object periodoTexto = this.tfPeriodo.getText();
//
//
//        if (nombre != null && nombre != "") exencionRegistroDeuda.setNombre(nombre.toString());
//        else exencionRegistroDeuda.setNombre(null);
//        if (porcentaje != null && porcentaje != "") exencionRegistroDeuda.setPorcentaje(new Double(porcentaje.toString()));
//        else exencionRegistroDeuda.setPorcentaje(null);
//        if (motivo != null && motivo != "") exencionRegistroDeuda.setMotivo(motivo.toString());
//        else exencionRegistroDeuda.setMotivo(null);
//
// 
//        periodicidadSelected = this.getDdPeriodicidad().getSelected();
//        if ((periodicidadSelected != null) && (periodicidadSelected.toString().length() > 0)) {
//          periodicidad = Periodicidad.valueOf(periodicidadSelected.toString());
//        }else{ periodicidad = null;
//        }
//        if (anioTexto != null && anioTexto != "") {
//            anio = Conversor.getIntegerDeString(anioTexto.toString());
//        } else {
//            anio = null;
//        }
//        if (periodoTexto != null && periodoTexto != "") {
//            periodoNumero = Conversor.getIntegerDeString(periodoTexto.toString());
//        } else {
//            periodoNumero = null;
//        }
//
//       Periodo periodo = null;
//        // trato de obtener un per�odo
//       if (anio != null && periodoNumero != null && periodicidad != null){
//           try {
//                this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
//                
////                Comentado
////                periodo = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getPeriodo(periodicidad, new Integer(periodoNumero.toString()), new Integer(anio.toString()));
//            
//           } catch (Exception ex) {
//                error("Error al intentar generar el Per\355odo: " + ex.getMessage());
//                log(CASO_NAVEGACION + "_", ex);
//            }
//       }
//       
////       Comentado
////       exencionRegistroDeuda.setPeriodo(periodo);
//
//
//       if (digestoMunicipal.getIdDigestoMunicipal() == -1) digestoMunicipal = null;
//       if (digestoMunicipal != null)exencionRegistroDeuda.setDigestoMunicipal(digestoMunicipal);
//
//       this.getLdpExencion().commitChanges();
//
//       ind = 0;
//       this.getElementoPila().getObjetos().set(ind++, exencionRegistroDeuda);
//       this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);
//       this.getElementoPila().getObjetos().set(ind++, anio);
//       this.getElementoPila().getObjetos().set(ind++, periodicidad);
//       this.getElementoPila().getObjetos().set(ind++, periodoNumero);
//
//    }
//
//    private void mostrarEstadoObjetosUsados() throws Exception {
//        // CAMBIAR: Obtener la instancia por cada objeto manejado en la pagina
//        ExencionRegistroDeuda locExencionRegistroDeuda = null;
//        DigestoMunicipal digesto = null;
//        Integer anio = null;
//        Periodicidad periodicidad = null;
//        Integer periodoNumero = null;
//   
//        if (this.getRequestBean1().getObjetoABM() != null) {
//            try{
//                locExencionRegistroDeuda  = (ExencionRegistroDeuda) this.getRequestBean1().getObjetoABM();
//            }catch(Exception e){
//                log(CASO_NAVEGACION + "_Guardar Error:", e);
//                e.printStackTrace();
//            }
//
////            Comentado
//            digesto = (DigestoMunicipal)locExencionRegistroDeuda.getDigestoMunicipal();
////            anio = new Integer(locExencionRegistroDeuda.getPeriodo().getFechaInicio().get(Calendar.YEAR));
////            periodicidad = locExencionRegistroDeuda.getPeriodo().getPeriodicidad();
////            periodoNumero = locExencionRegistroDeuda.getPeriodo().getNumeroPeriodo();
//     
//            this.getElementoPila().getObjetos().set(0, locExencionRegistroDeuda);
//            this.getElementoPila().getObjetos().set(1, digesto);
//            this.getElementoPila().getObjetos().set(2, anio);
//            this.getElementoPila().getObjetos().set(3, periodicidad);
//            this.getElementoPila().getObjetos().set(4, periodoNumero);
//        }
//
//       int ind = 0;
//     
//       locExencionRegistroDeuda =  (ExencionRegistroDeuda)this.obtenerObjetoDelElementoPila(ind++, ExencionRegistroDeuda.class);
//       digesto = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
//       anio = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
//       periodicidad = (Periodicidad) this.obtenerObjetoDelElementoPila(ind++, Periodicidad.class);
//       periodoNumero = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
//
//
//       this.tfNombre.setText(locExencionRegistroDeuda.getNombre());
//       this.tfPorcentaje.setText(locExencionRegistroDeuda.getPorcentaje());
//       this.taMotivo.setText(locExencionRegistroDeuda.getMotivo());
//
//       if (locExencionRegistroDeuda.getPeriodicidadCuotas() != null){
//            this.getDdPeriodicidadCuota().setSelected(Util.getEnumNameFromString(String.valueOf(locExencionRegistroDeuda.getPeriodicidadCuotas())));
//            this.getDdPeriodicidadCuotaDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locExencionRegistroDeuda.getPeriodicidadCuotas())));
//       }
//       System.out.println("ME period cuotassssss:" +  locExencionRegistroDeuda.getPeriodicidadCuotas());
//
//     //periodo:
//       if (anio != null) {
//            this.getTfAnio().setText(anio.toString());
//       }
//
//       if (periodicidad != null){
//        this.getDdPeriodicidad().setSelected(Util.getEnumNameFromString(String.valueOf(periodicidad)));
//        this.getDdPeriodicidadDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(periodicidad)));
//       }
//
//       if (periodoNumero != null) {
//            this.getTfPeriodo().setText(periodoNumero.toString());
//       }
//        /////
//
//       if (digesto.getIdDigestoMunicipal() == -1 ) digesto = null;
//       if (digesto != null) this.getTfDigestoMunicipal().setText(digesto.toString());
//            else this.getTfDigestoMunicipal().setText(null);
//
//       ArrayList listaExenciones= new ArrayList();
//       listaExenciones.addAll(locExencionRegistroDeuda.getListaRegistrosExencion());
//       this.getLdpExencion().setList(listaExenciones);
//       this.setListaDelCommunication(listaExenciones);
//
//     }
//
//    private ObjectListDataProvider getObjectListDataProvider() {
//        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
//        return this.getLdpExencion();
//    }
//
//    private ArrayList getListaDelCommunication() {
//        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
//        return this.getCommunicationSAICBean().getListaExencionRegistroDeuda();
//    }
//
//    private void setListaDelCommunication(ArrayList lista) {
//        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
//        this.getCommunicationSAICBean().setListaExencionRegistroDeuda(lista);
//    }
//    // </editor-fold>
//    // <editor-fold defaultstate="collapsed" desc="Metodos estaticos de la pagina">
//
//    private void crearElementoPila() {
//        ElementoPila ep = new ElementoPila();
//        ep.setCasoNavegacion(CASO_NAVEGACION);
//        ep.setIdPagina(this.getIdPagina());
//        ep.setIdSubSesion(this.getIdSubSesion());
//        ep.setNombrePagina(NOMBRE_PAGINA);
//
//        ep = this.EliminarObjetosAElementoPila(ep);
//
//        this.getSessionBean1().getMgrPilas().addElemento(ep);
//    }
//
//    private String prepararParaVolver(String pAccionRetorno) {
//        this.getRequestBean1().setAccion(pAccionRetorno);
//        String retorno = null;
//        ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
//        if (locElementoAnterior != null) {
//            this.getSessionBean1().getMgrPilas().removeElemento(this.getElementoPila());
//            this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
//            this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
//            this.getRequestBean1().setElementoPilaPaginaAnt(locElementoAnterior);
//            retorno = locElementoAnterior.getCasoNavegacion();
//        } else {
//            retorno = CASO_NAV_POST_CADUCIDAD;
//        }
//        return retorno;
//    }
//
//    private String prepararCaducidad() {
//        // redireccionar a pagina con mensaje de caducidad
//        this.getRequestBean1().setCasoNavegacionPostCaducidad(CASO_NAV_POST_CADUCIDAD);
//        return CASO_NAV_CADUCIDAD;
//    }
//
//// Juan Pablo
//    private boolean ultimoElementoPilaDeSubSesion() {
//        return this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
//    }
//
//    private Object obtenerObjetoDelElementoPila(int posicion, Class tipoClase) {
//        Object objeto = null;
//        try {
//            objeto = this.getElementoPila().getObjetos().get(posicion);
//            if (objeto == null) {
//                objeto = tipoClase.newInstance();
//            }
//        } catch (Exception ex) {
//        }
//        return objeto;
//    }
//// </editor-fold>
//
//    public String btnEliminar_action() {
//       String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        if (ultimo) {
//
//            try {
//                this.guardarEstadoObjetosUsados();
//                ExencionRegistroDeuda locExencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(0, ExencionRegistroDeuda.class);
//                this.getCommunicationSAICBean().getRemoteSystemExencion().terminarExencionRegistroDeuda(locExencionRegistroDeuda);
//                this.getRequestBean1().setRespuestaABM(locExencionRegistroDeuda);
//                
//                info("La Exencion se finaliz\363 exitosamente.");
//            } catch (Exception ex) {
//                log(CASO_NAVEGACION+"_EliminarError:", ex);
//                error(NOMBRE_PAGINA+" - Eliminar: " + ex.getMessage());
//            }
// 
//            retorno = this.prepararParaVolver(Constantes.ACCION_ELIMINAR);
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//      return retorno;
//    }
//
//    public String btnCancelar_action() {
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        if (ultimo) {
//
//            this.setListaDelCommunication(null);
//            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
//    }
//    
//    private void refrescarTabla() throws Exception{
//        ExencionRegistroDeuda locExencionRegistroDeuda= (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(0, ExencionRegistroDeuda.class);
//        ArrayList listaRegistrosDeuda= new ArrayList();
//        //listaRegistrosDeuda.addAll(locExencionRegistroDeuda.getListaRegistrosExencion());
//        RegistroExencionRegistroDeuda locRegistroExencionRegistroDeuda = new RegistroExencionRegistroDeuda();
//        Iterator iter = locExencionRegistroDeuda.getListaRegistrosExencion().iterator();
//        while (iter.hasNext()){
//            locRegistroExencionRegistroDeuda= (RegistroExencionRegistroDeuda)iter.next();
//             if (locRegistroExencionRegistroDeuda.getExencionRegistroDeuda() != null){
//                    listaRegistrosDeuda.add(locRegistroExencionRegistroDeuda);
//
//             }
//
//        }      
//        try{
//             this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
//             this.setListaDelCommunication(listaRegistrosDeuda);
//             this.getLdpExencion().setList(this.getListaDelCommunication());
//
//        }catch(Exception ex){
//            this.getLdpExencion().setList(null);
//        }
//    }
//
//}