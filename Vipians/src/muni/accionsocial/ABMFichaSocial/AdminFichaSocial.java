/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.accionsocial.ABMFichaSocial;

/**
 *
 * @author tincho 
 */
import jasper.ConstantesReportes;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.accionSocial.recurso.persistent.Beneficiario;
import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class AdminFichaSocial extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">

    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        
        this.habilitarBtnExportar();
        
        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern("dd/MM/yy");
        dateTimeConverter1.setDateStyle("full");

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
    private StaticText stCantidadRegistros = new StaticText();

    public StaticText getStCantidadRegistros() {
        return stCantidadRegistros;
    }

    public void setStCantidadRegistros(StaticText st) {
        this.stCantidadRegistros = st;
    }
    private StaticText stTitulo = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private Label lblEncontrados = new Label();

    public Label getLblEncontrados() {
        return lblEncontrados;
    }

    public void setLblEncontrados(Label l) {
        this.lblEncontrados = l;
    }
  
    private Button btnLimpiarTipoFichaSocial = new Button();

    public Button getBtnLimpiarTipoFichaSocial() {
        return btnLimpiarTipoFichaSocial;
    }

    public void setBtnLimpiarTipoFichaSocial(Button btnLimpiarTipoFichaSocial) {
        this.btnLimpiarTipoFichaSocial = btnLimpiarTipoFichaSocial;
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
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Vincular a campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;
    private TextField tfBeneficiarioTitular = new TextField();
    
    protected PanelGroup pgParametros = new PanelGroup();

 protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();
 protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(HtmlAjaxCommandButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public HtmlAjaxCommandButton getBtnReiniciar() {
        return btnReiniciar;
    }

    public void setBtnReiniciar(HtmlAjaxCommandButton btnReiniciar) {
        this.btnReiniciar = btnReiniciar;
    }

    public PanelGroup getPgParametros() {
        return pgParametros;
    }

    public void setPgParametros(PanelGroup pgParametros) {
        this.pgParametros = pgParametros;
    }
 
    public TextField getTfBeneficiarioTitular() {
        return tfBeneficiarioTitular;
    }

    public void setTfBeneficiarioTitular(TextField tfBeneficiarioTitular) {
        this.tfBeneficiarioTitular = tfBeneficiarioTitular;
    }
    private TextField tfNumeroFichaSocial = new TextField();

    public TextField getTfNumeroFichaSocial() {
        return tfNumeroFichaSocial;
    }

    public void setTfNumeroFichaSocial(TextField tfNumeroFichaSocial) {
        this.tfNumeroFichaSocial = tfNumeroFichaSocial;
    }

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
    //          Generar getters y setters.
    //          En el getter poner:
    //          if (this.objeto == null) this.objeto = new Objeto();
//    private FichaSocial fichaSocialABuscar = null;

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    // CAMBIAR: Constantes que varian segun la pagina.
    // cantidad de objetos administrados por la pagina
    //private final int CANTIDAD_OBJETOS = 2;
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Fichas Sociales";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminFichaSocial";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarFichaSocial";
    private final String lnkModificar = "ModificarFichaSocial";
    private final String lnkEliminar = "EliminarFichaSocial";
    private final String lnkConsultar = "ConsultarFichaSocial";
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

    private Label label4 = new Label();
    private Label label5 = new Label();
    private Button btnSeleccionarFamiliar = new Button();
    private Button btnLimpiarFamiliar = new Button();
    private TextField tfFamiliar = new TextField();
    private Label Label3 = new Label();
    private TextField tfBeneficio = new TextField();
    private Button btnSeleccionarBeneficio = new Button();
    private Button btnLimpiarBeneficio = new Button();

    public Label getLabel3() {
        return Label3;
    }

    public void setLabel3(Label Label3) {
        this.Label3 = Label3;
    }

    public Button getBtnLimpiarBeneficio() {
        return btnLimpiarBeneficio;
    }

    public void setBtnLimpiarBeneficio(Button btnLimpiarBeneficio) {
        this.btnLimpiarBeneficio = btnLimpiarBeneficio;
    }

    public Button getBtnSeleccionarBeneficio() {
        return btnSeleccionarBeneficio;
    }

    public void setBtnSeleccionarBeneficio(Button btnSeleccionarBeneficio) {
        this.btnSeleccionarBeneficio = btnSeleccionarBeneficio;
    }

    public TextField getTfBeneficio() {
        return tfBeneficio;
    }

    public void setTfBeneficio(TextField tfBeneficio) {
        this.tfBeneficio = tfBeneficio;
    }

    public TextField getTfFamiliar() {
        return tfFamiliar;
    }

    public void setTfFamiliar(TextField tfFamiliar) {
        this.tfFamiliar = tfFamiliar;
    }

    public Button getBtnLimpiarFamiliar() {
        return btnLimpiarFamiliar;
    }

    public void setBtnLimpiarFamiliar(Button btnLimpiarFamiliar) {
        this.btnLimpiarFamiliar = btnLimpiarFamiliar;
    }

    public Button getBtnSeleccionarFamiliar() {
        return btnSeleccionarFamiliar;
    }

    public void setBtnSeleccionarFamiliar(Button btnSeleccionarFamiliar) {
        this.btnSeleccionarFamiliar = btnSeleccionarFamiliar;
    }

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label label4) {
        this.label4 = label4;
    }

    public Object getLastSelected() {
        return lastSelected;
    }

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label label5) {
        this.label5 = label5;
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
    private Button btnSeleccionarBeneficiarioPF = new Button();

    public Button getBtnSeleccionarBeneficiarioPF() {
        return btnSeleccionarBeneficiarioPF;
    }

    public void setBtnSeleccionarBeneficiarioPF(Button btnSeleccionarBeneficiarioPF) {
        this.btnSeleccionarBeneficiarioPF = btnSeleccionarBeneficiarioPF;
    }
    private Button btnSeleccionarBeneficiarioPJ = new Button();

    public Button getBtnSeleccionarBeneficiarioPJ() {
        return btnSeleccionarBeneficiarioPJ;
    }

    public void setBtnSeleccionarBeneficiarioPJ(Button btnSeleccionarBeneficiarioPJ) {
        this.btnSeleccionarBeneficiarioPJ = btnSeleccionarBeneficiarioPJ;
    }
    private Button btnLimpiarBeneficiario = new Button();

    public Button getBtnLimpiarBeneficiario() {
        return btnLimpiarBeneficiario;
    }

    public void setBtnLimpiarBeneficiario(Button btnLimpiarBeneficiario) {
        this.btnLimpiarBeneficiario = btnLimpiarBeneficiario;
    }
    private ObjectListDataProvider ldpFichaSocial = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpFichaSocial() {
        return ldpFichaSocial;
    }

    public void setLdpFichaSocial(ObjectListDataProvider oldp) {
        this.ldpFichaSocial = oldp;
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
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
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
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    
    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText staticText11) {
        this.staticText11 = staticText11;
    }
    
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }
    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
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
    
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    private Button btnConsultar = new Button();

    public Button getBtnConsultar() {
        return btnConsultar;
    }

    public void setBtnConsultar(Button b) {
        this.btnConsultar = b;
    }
    
    private Button btnExportar = new Button();

    public Button getBtnExportar() {
        return btnExportar;
    }

    public void setBtnExportar(Button btnExportar) {
        this.btnExportar = btnExportar;
    }
    
    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }
    private Checkbox checkbox1 = new Checkbox();

    public Checkbox getCheckbox1() {
        return checkbox1;
    }

    public void setCheckbox1(Checkbox c) {
        this.checkbox1 = c;
    }
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }
    // </editor-fold>

    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public AdminFichaSocial() {
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (muni.CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationCajaBean getCommunicationCajaBean() {
        return (muni.CommunicationCajaBean) getBean("CommunicationCajaBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationAccionSocialBean getComunicationAccionSocialBean() {
        return (muni.CommunicationAccionSocialBean) getBean("CommunicationAccionSocialBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
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
            log("AdminFichaSocial Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�
        // tablePhaseListener = this.getTableSelectPhaseListener();

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
                    || this.getRequestBean1().getAccion().equals(Constantes.ACCION_MODIFICAR)
                    || this.getRequestBean1().getAccion().equals(Constantes.ACCION_ELIMINAR))) {
                try {
                    this.refrescarTabla();
                } catch (Exception ex) {
                    this.limpiarTabla();
                }
            }

        }
        if (!recarga) {
            this.mostrarEstadoObjetosUsados();
        }

        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
        this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1());
        
        this.habilitarBtnExportar();
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

    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar RaddioButton">
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
    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar la fila recientemente seleccionada">
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

    public void guardarOrdenamiento() {
        //this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion()).setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
        this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
    }

    public void setearOrdenamiento() {
        //ElementoPila ep = this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
        //this.getTableRowGroup1().setSortCriteria((SortCriteria[]) ep.getOrdenamiento());
        this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
    }

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
            // CAMBIAR: Utilizar el ListDataProvider correspondiente
            rk = this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    // </editor-fold>
    //Mines : agregaste esto para la zonificacion
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
    public Object getSelected() {
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

    //hasta aca Mines
    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina

        ep.getObjetos().add(ind++, new FichaSocial());//0
        ep.getObjetos().add(ind++, new Beneficiario());// Persona Fisica o Juridica
        ep.getObjetos().add(ind++, new Beneficiario());// Familiar
        ep.getObjetos().add(ind++, new Beneficio());//
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        int ind = 0;
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(ind++, FichaSocial.class);
        Beneficiario titular = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        Beneficiario familiar = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        Beneficio beneficio = (Beneficio) this.obtenerObjetoDelElementoPila(ind++, Beneficio.class);

        Object numero = this.getTfNumeroFichaSocial().getText();

        if (numero != null && numero != "") {
            fichaSocial.setNumero(Conversor.getIntegerDeString(numero.toString()));
        } else {
            fichaSocial.setNumero(null);
        }

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, fichaSocial);
        this.getElementoPila().getObjetos().set(ind++, titular);
        this.getElementoPila().getObjetos().set(ind++, familiar);
        this.getElementoPila().getObjetos().set(ind++, beneficio);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Obtener una instancia por cada objeto manejado en la pagina
        FichaSocial fichaSocial = null;
        Persona persona = null;
        Beneficiario beneficiario = new Beneficiario();
        Beneficiario titular = null;
        Beneficiario familiar = null;
        Beneficio beneficio = null;

        int ind = 0;
        fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(ind++, FichaSocial.class);
        titular = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        familiar = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        beneficio = (Beneficio) this.obtenerObjetoDelElementoPila(ind++, Beneficio.class);

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.
            if (seleccionado instanceof Persona) {
                persona = (Persona) seleccionado;
                beneficiario.setPersona(persona);
            
                if (this.getRequestBean1().getTipoSeleccion() == "TITULAR") {
                    this.getElementoPila().getObjetos().set(1, beneficiario);
                } else {
                    this.getElementoPila().getObjetos().set(2, beneficiario);
                }
            }
            if (seleccionado instanceof Beneficio) {
                beneficio = (Beneficio) seleccionado;
                this.getElementoPila().getObjetos().set(3, beneficio);
            }
        }

//        if (this.getElementoPila().getObjetos() != null && !this.getElementoPila().getObjetos().isEmpty()) {
//            // CAMBIAR:
//            fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);
////            tipoFichaSocial = (TipoFichaSocial) this.obtenerObjetoDelElementoPila(1, TipoFichaSocial.class);
//        }
        if (this.getRequestBean1().getObjetoSeleccion() == null) {
            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, fichaSocial);
            this.getElementoPila().getObjetos().set(ind++, titular);
            this.getElementoPila().getObjetos().set(ind++, familiar);
            this.getElementoPila().getObjetos().set(ind++, beneficio);
        }
        ind = 0;
        fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(ind++, FichaSocial.class);
        titular = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        familiar = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        beneficio = (Beneficio) this.obtenerObjetoDelElementoPila(ind++, Beneficio.class);

        this.getTfNumeroFichaSocial().setText(fichaSocial.getNumero());
        this.getTfBeneficiarioTitular().setText(titular.getPersona());
        this.getTfFamiliar().setText(familiar.getPersona());
        if (beneficio != null && beneficio.getIdBeneficio() != -1) {
            this.getTfBeneficio().setText(beneficio);
        }
        
        if (this.getLdpFichaSocial().getList() != null){
            Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
            System.out.println("filaSeleccionada :" +filaSeleccionada);
            this.seleccionarFila(filaSeleccionada);
        }

    }

    private void refrescarTabla() throws Exception {
        // CAMBIAR: Segun objeto de busqueda.
        int ind = 0;
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(ind++, FichaSocial.class);
        Beneficiario titular = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        Beneficiario familiar = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        Beneficio beneficio = (Beneficio) this.obtenerObjetoDelElementoPila(ind++, Beneficio.class);
        Persona personaTitular = null;
        Persona personaFamiliar = null;

        if (titular.getPersona() != null) {
            personaTitular = titular.getPersona();
        }
        if (familiar.getPersona() != null) {
            personaFamiliar = familiar.getPersona();
        }

        if (beneficio.getIdBeneficio() == -1) {
            beneficio = null;
        }
        // CAMBIAR: Utilizar el ListDataProvider y EJBClient adecuados.

        this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
//        this.setListaDelCommunication((ArrayList) this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().findListaFichaSocial(fichaSocial.getNumero(), personaTitular, personaFamiliar, beneficio));
        this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        this.setRBSelected((new Long(0)).toString());
        System.out.println("tam lista " + this.getListaDelCommunication().size());
    }

    private void limpiarTabla() {
        // CAMBIAR: Utilizar el ListDataProvider adecuado.
        this.getObjectListDataProvider().getList().clear();
    }

    private void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        for (int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
            this.getElementoPila().getObjetos().set(i, null);
        }

        // CAMBIAR: Limpiar los textField y los dropDown
        this.getTfNumeroFichaSocial().setText("");
        this.getTfBeneficiarioTitular().setText("");
        this.getTfFamiliar().setText("");
        this.getTfBeneficio().setText("");
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpFichaSocial();
    }

    private List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationAccionSocialBean().getListaFichaSociales();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationAccionSocialBean().setListaFichaSociales(lista);
    }

//    private TableSelectPhaseListener getTableSelectPhaseListener() {
//        // CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que corresponda
//        return this.getComunicationAccionSocialBean().getTablePhaseListenerFichaSocial();
//    }
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
// </editor-fold>

    public String btnSeleccionarBeneficio_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminBeneficio";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarFamiliar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion("FAMILIAR");
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPersonaFisica";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarBeneficio_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.limpiarObjeto(3, this.getTfBeneficio());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarBeneficiario_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.limpiarObjeto(1, this.getTfBeneficiarioTitular());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarFamiliar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.limpiarObjeto(2, this.getTfFamiliar());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnBuscar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                Validador v = new Validador();

                UIComponent[] esNumero = new UIComponent[1];
                String[] nomEsNumero = new String[1];

                int pos1 = 0;
                esNumero[pos1] = this.getTfNumeroFichaSocial();
                nomEsNumero[pos1++] = "N\372mero de Ficha";

                v.esNumero(esNumero, nomEsNumero);

                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    this.limpiarTabla();
                    return null;

                } else {

                    this.guardarEstadoObjetosUsados();
                    this.refrescarTabla();
                    this.guardarOrdenamiento();
                    Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                    this.getElementoPila().setPosicionGlobal(pos.longValue());
                    this.getRequestBean1().setAccion(Constantes.ACCION_BUSCAR);
                }
            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION + "_BuscarError:", ex);
                error(NOMBRE_PAGINA + " - Buscar: " + ex.getMessage());
                this.getRequestBean1().setAccion(Constantes.ACCION_RSLT_ERROR);
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnReiniciar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                this.limpiarObjetosUsados();
                this.guardarEstadoObjetosUsados();
                this.limpiarTabla();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());
                this.getRequestBean1().setAccion(Constantes.ACCION_REINICIAR);
            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION + "_ReiniciarError:", ex);
                error(NOMBRE_PAGINA + " - Reiniciar: " + ex.getMessage());
                this.getRequestBean1().setAccion(Constantes.ACCION_RSLT_ERROR);
            }

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnConsultar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    FichaSocial fichaSocial = (FichaSocial) obj;
                    this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
                    fichaSocial = (FichaSocial) this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().getFichaSocialPorId(fichaSocial.getIdFichaSocial());
                    getRequestBean1().setObjetoABM(fichaSocial);

                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ConsultarError:", ex);
                error(NOMBRE_PAGINA + " - Consultar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            if (rk != null) {
                this.getRequestBean1().setAccion(Constantes.ACCION_GOTO_CONSULTAR);
                retorno = lnkConsultar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnSeleccionar_action() {
        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        if (ultimo) {
//            RowKey rk = null;
//
//            // NOTA: para la seleccion de multiples objetos. CAMBIAR: posicion en el elemento pila
//            Object tipoSeleccion = (String) this.obtenerObjetoDelElementoPila(2, String.class);
//
//            if (tipoSeleccion.equals("MULTIPLE")) {
//                try {
//                    // Inicializo el Array de objetos seleccionados
//                    if (this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
//                        this.getRequestBean1().getObjetosSeleccionMultiple().clear();
//                    } else {
//                        this.getRequestBean1().setObjetosSeleccionMultiple(new ArrayList());
//                    }
//
//
//                    //ACAAAAA NO ENTRA AL FOR PORQ selectedRowKeys = 0 !!!!! WTF
//                    RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();
//
//                    for (int i = 0; i < selectedRowKeys.length; i++) {
//                        String rowId = selectedRowKeys[i].getRowId();
//                        RowKey rowKey = this.getObjectListDataProvider().getRowKey(rowId);
//                        Object obj = this.getObjectListDataProvider().getObjects()[getNroFila(rowKey.toString())];
//                        this.getRequestBean1().getObjetosSeleccionMultiple().add(obj);
//                        //no entra al for
//                    }
//
//                } catch (Exception ex) {
//                    log(CASO_NAVEGACION + "_SeleccionarMultiplesObjetosError:", ex);
//                    error(NOMBRE_PAGINA + " - Seleccionar M\372ltiples Objetos: " + ex.getMessage());
//                }
//
//                if (!this.getRequestBean1().getObjetosSeleccionMultiple().isEmpty()) {
//                    retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
//                } else {
//                }
//                //retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
//
//            } else {
//                try {
//                    rk = this.getSeleccionado();
//                    if (rk != null) {
//                        int index = getNroFila(rk.toString());
//                        Object obj = this.getObjectListDataProvider().getObjects()[index];
//                        getRequestBean1().setObjetoSeleccion(obj);
//
//                        this.setRowKeySeleccionado(this.getSeleccionado());
//                    }
//
//                } catch (Exception ex) {
//                    log(CASO_NAVEGACION + "_SeleccionarError:", ex);
//                    error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
//                }
//
//                if (rk != null) {
//                    ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
//                    if (locElementoAnterior == null) {
//                        return null;
//                    }
//                    retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
//                }
//            }
//        } else {
//            retorno = this.prepararCaducidad();
//        }
        return retorno;
    }

    public String btnAgregar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_AgregarError:", ex);
                error(NOMBRE_PAGINA + " - Agregar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            this.getRequestBean1().setAccion(Constantes.ACCION_GOTO_AGREGAR);
            retorno = lnkAgregar;
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnModificar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    FichaSocial fichaSocial = (FichaSocial) obj;
                    this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
                    fichaSocial = (FichaSocial) this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().getFichaSocialPorId(fichaSocial.getIdFichaSocial());

                    this.getRequestBean1().setObjetoABM(fichaSocial);
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ModificarError:", ex);
                error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            if (rk != null) {
                this.getRequestBean1().setAccion(Constantes.ACCION_GOTO_MODIFICAR);
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

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    FichaSocial fichaSocial = (FichaSocial) obj;
                    this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
                    fichaSocial = (FichaSocial) this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().getFichaSocialPorId(fichaSocial.getIdFichaSocial());
                    getRequestBean1().setObjetoABM(fichaSocial);

                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_EliminarError:", ex);
                error(NOMBRE_PAGINA + " - Eliminar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            if (rk != null) {
                this.getRequestBean1().setAccion(Constantes.ACCION_GOTO_ELIMINAR);
                retorno = lnkEliminar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnSeleccionarBeneficiarioPF_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 0;

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion("TITULAR");
//            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPersonaFisica";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarBeneficiarioPJ_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 0;

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion("TITULAR");
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPersonaJuridica";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarPersona_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(0, this.getTfBeneficiarioTitular());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnCancelar_action() {
        //mines:
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.setListaDelCommunication(null);
            retorno = this.prepararParaVolver(retorno);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
         public String btnExportar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Fichas Sociales");

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_FichasSociales");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

            } catch (Exception e) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
                log(CASO_NAVEGACION + "_ReporteDinamicoError: ", e);
                error(NOMBRE_PAGINA + " - ReporteDinamico: " + e.getMessage());
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    //Metodo para habilitar el boton exportar cuando corresponda
    public void habilitarBtnExportar() {
        this.btnExportar.setDisabled(true);
        if (getListaDelCommunication() != null) {
            if (getListaDelCommunication().isEmpty()) {
                this.btnExportar.setDisabled(true);
            } else {
                this.btnExportar.setDisabled(false);
            }
        }
    }
}
