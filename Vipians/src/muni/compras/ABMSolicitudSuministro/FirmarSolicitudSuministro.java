/*
 * ModificarSolicitudSuministro.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.compras.ABMSolicitudSuministro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

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
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
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
public class FirmarSolicitudSuministro extends AbstractPageBean {
    
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;
    public Long getIdPagina() { return idPagina; }
    public void setIdPagina(Long idPagina) { this.idPagina = idPagina; }
    public Long getIdSubSesion() { return idSubSesion; }
    public void setIdSubSesion(Long idSubSesion) { this.idSubSesion = idSubSesion; }
    
    
     private TextField tfArea = new TextField();
    private TextField tfValorEstimado = new TextField();
    private TextField tfFechaEmision = new TextField();
    private TextField tfCantidad = new TextField();
    private TextField tfBien = new TextField();
    private TextField tfUsuario = new TextField();
    private TextField tfEstado = new TextField();
    private TextField tfBienProvisto = new TextField();
    private TextField tfCuenta = new TextField();
    private TextField tfOrdenCompra = new TextField();
    private TextField tfNro = new TextField();
    private TextField tfValorTotal = new TextField();
    
    private StaticText stTitulo = new StaticText();
    private StaticText stSeparador = new StaticText();
    private StaticText staticText4 = new StaticText();
    private StaticText staticText1 = new StaticText();
    private StaticText staticText2 = new StaticText();
    private StaticText staticText3 = new StaticText();
    private StaticText staticText5 = new StaticText();
    private StaticText staticText6 = new StaticText();
    private StaticText staticText7 = new StaticText();
    private StaticText stCantidad = new StaticText();
    private StaticText stCuenta = new StaticText();
    
    private Page page1 = new Page();
    
    //----------TextAreas-----------------
    private TextArea taDescripcion = new TextArea();
    
    //----------Table Components-----------------
    private TableRowGroup tableRowGroup1 = new TableRowGroup();
    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    private Table table2 = new Table();
    private Table table1 = new Table();
    
    //-------Objects Lists------------------------
    private ObjectListDataProvider ldpLineaSolSuministro = new ObjectListDataProvider();
    private ObjectListDataProvider ldpListaFirmaPermiso = new ObjectListDataProvider();
    
    //--------RadioButtons-----------------------
    private RadioButton radioButton1 = new RadioButton();
    //-------TableColumns------------------------
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn2 = new TableColumn();
    private TableColumn tableColumn3 = new TableColumn();
    private TableColumn tableColumn4 = new TableColumn();
    private TableColumn tableColumn5 = new TableColumn();
    private TableColumn tableColumn6 = new TableColumn();
    private TableColumn tableColumn7 = new TableColumn();
    private TableColumn tableColumn8 = new TableColumn();
    private TableColumn tableColumn9 = new TableColumn();
    private TableColumn tableColumn10 = new TableColumn();
    private TableColumn tableColumn11 = new TableColumn();
    
    
    
    
    
    
    
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Firmar Solicitud de Suministro.";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "FirmarSolicitudSuministro";
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

        if(this.getListaDelCommunication() != null){
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        if(this.getListaDelCommunicationTabla2() != null){
            this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());
        }
        
    }
    
    
    
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

    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup groupPanel1) {
        this.groupPanel1 = groupPanel1;
    }




    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText staticText7) {
        this.staticText7 = staticText7;
    }
    
    public StaticText getStCuenta() {
        return stCuenta;
    }

    public void setStCuenta(StaticText stCuenta) {
        this.stCuenta = stCuenta;
    }
    
    public StaticText getStCantidad() {
        return stCantidad;
    }

    public void setStCantidad(StaticText stCantidad) {
        this.stCantidad = stCantidad;
    }
    

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText staticText1) {
        this.staticText1 = staticText1;
    }

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText staticText2) {
        this.staticText2 = staticText2;
    }

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText staticText3) {
        this.staticText3 = staticText3;
    }

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText staticText4) {
        this.staticText4 = staticText4;
    }

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText staticText5) {
        this.staticText5 = staticText5;
    }

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText staticText6) {
        this.staticText6 = staticText6;
    }
    
    public StaticText getStSeparador() {
        return stSeparador;
    }

    public void setStSeparador(StaticText st) {
        this.stSeparador = st;
    }

    public StaticText getStTitulo() {
        return stTitulo;
    }
    
    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }


    //----------TextField-----------------
   

    public TextField getTfValorTotal() {
        return tfValorTotal;
    }

    public void setTfValorTotal(TextField tfValorTotal) {
        this.tfValorTotal = tfValorTotal;
    }

    public TextField getTfNro() {
        return tfNro;
    }

    public void setTfNro(TextField tfNro) {
        this.tfNro = tfNro;
    }
    
    public TextField getTfBienProvisto() {
        return tfBienProvisto;
    }

    public void setTfBienProvisto(TextField tfBienProvisto) {
        this.tfBienProvisto = tfBienProvisto;
    }

    public TextField getTfCuenta() {
        return tfCuenta;
    }

    public void setTfCuenta(TextField tfCuenta) {
        this.tfCuenta = tfCuenta;
    }

    public TextField getTfOrdenCompra() {
        return tfOrdenCompra;
    }

    public void setTfOrdenCompra(TextField tfOrdenCompra) {
        this.tfOrdenCompra = tfOrdenCompra;
    }
    public TextField getTfEstado() {
        return tfEstado;
    }

    public void setTfEstado(TextField tfEstado) {
        this.tfEstado = tfEstado;
    }
    
    public TextField getTfUsuario() {
        return tfUsuario;
    }

    public void setTfUsuario(TextField tf) {
        this.tfUsuario = tf;
    }
    
    public TextField getTfBien() {
        return tfBien;
    }

    public void setTfBien(TextField tf) {
        this.tfBien = tf;
    }

    public TextField getTfCantidad() {
        return tfCantidad;
    }

    public void setTfCantidad(TextField tf) {
        this.tfCantidad = tf;
    }

    public TextField getTfFechaEmision() {
        return tfFechaEmision;
    }

    public void setTfFechaEmision(TextField tf) {
        this.tfFechaEmision = tf;
    }

    public TextField getTfValorEstimado() {
        return tfValorEstimado;
    }

    public void setTfValorEstimado(TextField tf) {
        this.tfValorEstimado = tf;
    }

    public TextField getTfArea() {
        return tfArea;
    }
    
    public void setTfArea(TextField tfArea) {
        this.tfArea = tfArea;
    }

    
    //----------Labels-----------------
    private Label lblLineaSolSuministro = new Label();
    private Label label5 = new Label();
    private Label label4 = new Label();
    private Label label3 = new Label();
    private Label label2 = new Label();
    private Label label6 = new Label();
    private Label label1 = new Label();
    private Label lblBien = new Label();
    private Label lblCuenta = new Label();
    private Label lblOrdenCompra = new Label();
    private Label lblCantidad = new Label();
    private Label lblValorEstimado = new Label();
    private Label lblNro = new Label();
    private Label lblListaFirmas = new Label();
    private Label lblEstado = new Label();

    public Label getLblEstado() {
        return lblEstado;
    }

    public void setLblEstado(Label lblEstado) {
        this.lblEstado = lblEstado;
    }

    public Label getLblListaFirmas() {
        return lblListaFirmas;
    }

    public void setLblListaFirmas(Label lblListaFirmas) {
        this.lblListaFirmas = lblListaFirmas;
    }
    
    public Label getLblNro() {
        return lblNro;
    }

    public void setLblNro(Label lblNro) {
        this.lblNro = lblNro;
    }

    public Label getLblBien() {
        return lblBien;
    }

    public void setLblBien(Label lblBien) {
        this.lblBien = lblBien;
    }

    public Label getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(Label lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    public Label getLblCuenta() {
        return lblCuenta;
    }

    public void setLblCuenta(Label lblCuenta) {
        this.lblCuenta = lblCuenta;
    }

    public Label getLblOrdenCompra() {
        return lblOrdenCompra;
    }

    public void setLblOrdenCompra(Label lblOrdenCompra) {
        this.lblOrdenCompra = lblOrdenCompra;
    }

    public Label getLblValorEstimado() {
        return lblValorEstimado;
    }

    public void setLblValorEstimado(Label lblValorEstimado) {
        this.lblValorEstimado = lblValorEstimado;
    }

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }

    public Label getLblLineaSolSuministro() {
        return lblLineaSolSuministro;
    }

    public void setLblLineaSolSuministro(Label lblLineaSolSuministro) {
        this.lblLineaSolSuministro = lblLineaSolSuministro;
    }

    public Label getLabel4() {
        return label4;
    }
    
    public void setLabel4(Label l) {
        this.label4 = l;
    }
    
    public Label getLabel5() {
        return label5;
    }
    
    public void setLabel5(Label l) {
        this.label5 = l;
    }


    //----------Buttons-----------------
    private Button btnGuardar = new Button();
    private Button btnCancelar = new Button();
    private Button btnSeleccionarArea = new Button();
    private Button btnLimpiarArea = new Button();
    private Button btnLimpiarBien = new Button();
    private Button btnSeleccionarBien = new Button();
    private Button btnAgregarLineaSolicitud = new Button();
    private Button btnQuitar = new Button();
    private Button btnQuitarTodos = new Button();
    private Button btnSeleccionarOrden = new Button();
    private Button btnLimpiarOrden = new Button();
    private Button btnSeleccionarCuenta = new Button();
    private Button btnLimpiarCuenta = new Button();

    public Button getBtnLimpiarCuenta() {
        return btnLimpiarCuenta;
    }

    public void setBtnLimpiarCuenta(Button btnLimpiarCuenta) {
        this.btnLimpiarCuenta = btnLimpiarCuenta;
    }

    public Button getBtnLimpiarOrden() {
        return btnLimpiarOrden;
    }

    public void setBtnLimpiarOrden(Button btnLimpiarOrden) {
        this.btnLimpiarOrden = btnLimpiarOrden;
    }

    public Button getBtnSeleccionarCuenta() {
        return btnSeleccionarCuenta;
    }

    public void setBtnSeleccionarCuenta(Button btnSeleccionarCuenta) {
        this.btnSeleccionarCuenta = btnSeleccionarCuenta;
    }

    public Button getBtnSeleccionarOrden() {
        return btnSeleccionarOrden;
    }

    public void setBtnSeleccionarOrden(Button btnSeleccionarOrden) {
        this.btnSeleccionarOrden = btnSeleccionarOrden;
    }

    public Button getBtnQuitar() {
        return btnQuitar;
    }

    public void setBtnQuitar(Button btnQuitar) {
        this.btnQuitar = btnQuitar;
    }

    public Button getBtnQuitarTodos() {
        return btnQuitarTodos;
    }

    public void setBtnQuitarTodos(Button btnQuitarTodos) {
        this.btnQuitarTodos = btnQuitarTodos;
    }
    
    public Button getBtnAgregarLineaSolicitud() {
        return btnAgregarLineaSolicitud;
    }

    public void setBtnAgregarLineaSolicitud(Button btnAgregarLineaSolicitud) {
        this.btnAgregarLineaSolicitud = btnAgregarLineaSolicitud;
    }

    public Button getBtnLimpiarBien() {
        return btnLimpiarBien;
    }

    public void setBtnLimpiarBien(Button b) {
        this.btnLimpiarBien = b;
    }

    public Button getBtnSeleccionarBien() {
        return btnSeleccionarBien;
    }

    public void setBtnSeleccionarBien(Button b) {
        this.btnSeleccionarBien = b;
    }
    public Button getBtnSeleccionarArea() {
        return btnSeleccionarArea;
    }

    public void setBtnSeleccionarArea(Button b) {
        this.btnSeleccionarArea = b;
    }

    public Button getBtnLimpiarArea() {
        return btnLimpiarArea;
    }

    public void setBtnLimpiarArea(Button b) {
        this.btnLimpiarArea = b;
    }
    public Button getBtnGuardar() {
        return btnGuardar;
    }
    
    public void setBtnGuardar(Button b) {
        this.btnGuardar = b;
    }
    
    public Button getBtnCancelar() {
        return btnCancelar;
    }
    
    public void setBtnCancelar(Button b) {
        this.btnCancelar = b;
    }
    
    
    
    public TextArea getTaDescripcion() {
        return taDescripcion;
    }
    
    public void setTaDescripcion(TextArea ta) {
        this.taDescripcion = ta;
    }


    

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table table2) {
        this.table2 = table2;
    }

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
        this.tableRowGroup2 = tableRowGroup2;
    }
    
    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table table1) {
        this.table1 = table1;
    }

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
        this.tableRowGroup1 = tableRowGroup1;
    }

    

    public ObjectListDataProvider getLdpListaFirmaPermiso() {
        return ldpListaFirmaPermiso;
    }

    public void setLdpListaFirmaPermiso(ObjectListDataProvider ldpListaFirmaPermiso) {
        this.ldpListaFirmaPermiso = ldpListaFirmaPermiso;
    }

    public ObjectListDataProvider getLdpLineaSolSuministro() {
        return ldpLineaSolSuministro;
    }

    public void setLdpLineaSolSuministro(ObjectListDataProvider ldpLineaSolSuministro) {
        this.ldpLineaSolSuministro = ldpLineaSolSuministro;
    }

    

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton radioButton1) {
        this.radioButton1 = radioButton1;
    }

    

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tableColumn7) {
        this.tableColumn7 = tableColumn7;
    }
    
    public TableColumn getTableColumn10() {
        return tableColumn10;
    }

    public void setTableColumn10(TableColumn tableColumn10) {
        this.tableColumn10 = tableColumn10;
    }

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tableColumn11) {
        this.tableColumn11 = tableColumn11;
    }

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tableColumn8) {
        this.tableColumn8 = tableColumn8;
    }

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tableColumn9) {
        this.tableColumn9 = tableColumn9;
    }

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tableColumn1) {
        this.tableColumn1 = tableColumn1;
    }

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tableColumn2) {
        this.tableColumn2 = tableColumn2;
    }

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tableColumn3) {
        this.tableColumn3 = tableColumn3;
    }

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tableColumn4) {
        this.tableColumn4 = tableColumn4;
    }

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tableColumn5) {
        this.tableColumn5 = tableColumn5;
    }

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tableColumn6) {
        this.tableColumn6 = tableColumn6;
    }
    
    // </editor-fold>
    
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public FirmarSolicitudSuministro() {
    }
    
    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
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
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean)getBean("CommunicationSAICBean");
    }
    
    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean)getBean("CommunicationComprasBean");
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
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.CommunicationContabilidadBean getCommunicationContabilidadBean() {
        return (muni.CommunicationContabilidadBean) getBean("CommunicationContabilidadBean");
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
            log("ModificarSolicitudSuministro Initialization Failure", e);
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
            this.limpiarObjetosUsados();
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
        ep.getObjetos().add(ind++, new SolicitudSuministro());  //0
        ep.getObjetos().add(ind++, new Area());     //1
        ep.getObjetos().add(ind++, new ArrayList()); //2 listaLineas de Sol suministro

        ep.getObjetos().add(ind++, new Bien());//3
        ep.getObjetos().add(ind++, new OrdenCompra());//4
        ep.getObjetos().add(ind++, new LineaSolicitudSuministro()); //5
        ep.getObjetos().add(ind++, new String()); //6 cantidad
    

        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        SolicitudSuministro solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);
        Area area  = (Area) this.obtenerObjetoDelElementoPila(1, Area.class);
        ArrayList listaLineaSolSuministro = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

        Bien bien = (Bien) this.obtenerObjetoDelElementoPila(3, Bien.class);
        OrdenCompra ordenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(4, OrdenCompra.class);
        LineaSolicitudSuministro lineaSolicitudSuministro = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(5, LineaSolicitudSuministro.class);
        String cantidad = (String) this.obtenerObjetoDelElementoPila(6, String.class);
        ArrayList listaFirmaPermiso = (ArrayList) this.obtenerObjetoDelElementoPila(7, ArrayList.class);

        Object descripcion = this.getTaDescripcion().getText();
        Object objCantidad = this.getTfCantidad().getText();
        //SOLICITUD DE SUMINISTRO
        if (descripcion != null && descripcion != "") solicitudSuministro.setDescripcion(descripcion.toString()); else solicitudSuministro.setDescripcion(null);
        if(area != null && area.getIdArea()==-1) area = null;
        solicitudSuministro.setArea(area);
        solicitudSuministro.setUsuario(getSessionBean1().getUsuario());
        
        this.getElementoPila().getObjetos().set(0, solicitudSuministro);
        this.getElementoPila().getObjetos().set(1, area);

        if(bien != null && bien.getIdBien() != -1l){
            lineaSolicitudSuministro.setBien(bien);
        } else lineaSolicitudSuministro.setBien(null);

        if(!listaLineaSolSuministro.isEmpty()){
            HashSet locListadoLineas = new HashSet(listaLineaSolSuministro);
            solicitudSuministro.setListaLineaSolSuministro(locListadoLineas);
        }

        if(!listaFirmaPermiso.isEmpty()){
            HashSet locListadoFirmas = new HashSet(listaFirmaPermiso);
            solicitudSuministro.setListaFirmaPermiso(locListadoFirmas);
        }

//        if(ordenCompra != null && ordenCompra.getIdOrdenCompra() != -1l ){
//            lineaSolicitudSuministro.setOrdenCompra(ordenCompra);
//        } else lineaSolicitudSuministro.setOrdenCompra(null);

//        if (cuentaRfr != null && cuentaRfr.getIdCuenta() != -1l){
//            lineaSolicitudSuministro.setCuentaRfr(cuentaRfr);
//        } else lineaSolicitudSuministro.setCuentaRfr(null);

        this.getObjectListDataProvider().commitChanges(); //para el listado

        this.getElementoPila().getObjetos().set(2, listaLineaSolSuministro);
        this.getElementoPila().getObjetos().set(3, bien);
        this.getElementoPila().getObjetos().set(4, ordenCompra);
        this.getElementoPila().getObjetos().set(5, lineaSolicitudSuministro);
        this.getElementoPila().getObjetos().set(6, cantidad);
        this.getElementoPila().getObjetos().set(7, listaFirmaPermiso);

    }
    
    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.

        SolicitudSuministro solicitudSuministro = null;
        Area area  = null;
        Usuario usuario = getSessionBean1().getUsuario();
        ArrayList listaLineaSolSuministro = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
        Bien bien = (Bien) this.obtenerObjetoDelElementoPila(3, Bien.class);
        OrdenCompra ordenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(4, OrdenCompra.class);
        LineaSolicitudSuministro lineaSolicitudSuministro = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(5, LineaSolicitudSuministro.class);
        String cantidad = (String) this.obtenerObjetoDelElementoPila(6, String.class);
        ArrayList listaFirmaPermisos = (ArrayList) this.obtenerObjetoDelElementoPila(7, ArrayList.class);

        String descripcion = null;
        Date fecha = null;
        Integer numero = null;
        String estado = null;

        Cuenta cuenta = null;
        this.acomodarSeleccionado();

        if(this.getRequestBean1().getObjetoSeleccion() != null){
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();

            if(seleccionado instanceof Area){
                try{
                    area = (Area) seleccionado;
                    this.getElementoPila().getObjetos().set(1, area);
                }catch(Exception ex){

                }
            }

            if(seleccionado instanceof ArrayList) {
                try{
                    ArrayList locListado = (ArrayList) seleccionado;
                    for(int i = 0; i<locListado.size(); i++){
                        listaLineaSolSuministro.add(locListado.get(i));
                    }
                    this.getElementoPila().getObjetos().set(2, listaLineaSolSuministro);
                    // o.O .... O.o
                } catch (Exception ex) {
                    log("Error Description", ex);
                }
            }

            if(seleccionado instanceof Bien){
                try{
                    bien = (Bien) seleccionado;

                    this.getElementoPila().getObjetos().set(3, bien);
                }catch(Exception ex){
                    log("Error Description", ex);
                }
            }

            if(seleccionado instanceof OrdenCompra){
                try{
                    ordenCompra = (OrdenCompra) seleccionado;

                    this.getElementoPila().getObjetos().set(4, ordenCompra);
                }catch(Exception ex){
                    log("Error Description ",ex);
                }
            }


        }

        if (this.getRequestBean1().getObjetoABM() != null) {
            solicitudSuministro = (SolicitudSuministro) this.getRequestBean1().getObjetoABM();

            listaLineaSolSuministro = null;
            lineaSolicitudSuministro = null;
            cantidad = null;
            area = null;
            listaFirmaPermisos = null;
            if (solicitudSuministro != null) {
                try {
                    this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
                    solicitudSuministro = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(solicitudSuministro.getIdSolicitudSuministro());
                    area = solicitudSuministro.getArea();
                    fecha = solicitudSuministro.getFechaEmision();
                    descripcion = solicitudSuministro.getDescripcion();
                    numero = solicitudSuministro.getNumero();
                    estado = solicitudSuministro.getEstado().toString();

                    listaLineaSolSuministro = new ArrayList(solicitudSuministro.getListaLineaSolSuministro());

                    listaFirmaPermisos = new ArrayList(solicitudSuministro.getListaFirmaPermiso());

                    this.getElementoPila().getObjetos().set(0, solicitudSuministro);
                    this.getElementoPila().getObjetos().set(1, area);
                    this.getElementoPila().getObjetos().set(2, listaLineaSolSuministro);
                    this.getElementoPila().getObjetos().set(3, bien);
                    this.getElementoPila().getObjetos().set(7, listaFirmaPermisos);


                } catch (Exception ex) {
                    log(CASO_NAVEGACION + "_GuardarError:", ex);
                }
            }
        }
        int ind = 0;
        try{
            solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++, SolicitudSuministro.class);
            area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
            listaLineaSolSuministro = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
            bien = (Bien) this.obtenerObjetoDelElementoPila(ind++, Bien.class);
            ordenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(ind++, OrdenCompra.class);
            lineaSolicitudSuministro = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++, LineaSolicitudSuministro.class);
            cantidad = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
            listaFirmaPermisos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        if(area != null) this.getTfArea().setText(area.getNombre());
        if(solicitudSuministro != null) this.getTaDescripcion().setText(solicitudSuministro.getDescripcion());
        if(bien != null) this.getTfBien().setText(bien.getNombre());
        if(ordenCompra != null) this.getTfOrdenCompra().setText(ordenCompra.toString());
        
        if(cantidad != null) this.getTfCantidad().setText(cantidad);

        if(fecha != null) {
            this.getTfFechaEmision().setText(Conversor.getStringDeFechaCorta(fecha));
        } else {
            this.getTfFechaEmision().setText(Conversor.getStringDeFechaCorta(new Date()));
        }
        if(numero != null) {
            this.getTfNro().setText(numero.toString());
        } else this.getTfNro().setText(solicitudSuministro.getNumero().toString());

        this.getTfEstado().setText(estado);
        if(descripcion != null) this.getTaDescripcion().setText(descripcion);        
        this.getTfUsuario().setText(solicitudSuministro.getUsuario());
        this.getObjectListDataProvider().setList(listaLineaSolSuministro);
        this.setListaDelCommunication(listaLineaSolSuministro);
        this.getObjectListDataProviderTabla2().setList(listaFirmaPermisos);
        this.setListaDelCommunicationTabla2(listaFirmaPermisos);
    }
    // </editor-fold>

    private void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        LineaSolicitudSuministro lineaSolicitudSuministro = new LineaSolicitudSuministro();
        Bien bien = null;
        OrdenCompra ordenCompra = null;
        CuentaRfr cuenta = new CuentaRfr();

        this.getElementoPila().getObjetos().set(3, bien);
        this.getElementoPila().getObjetos().set(4, ordenCompra);
        this.getElementoPila().getObjetos().set(5, lineaSolicitudSuministro);
        this.getElementoPila().getObjetos().set(6, null);


        this.getTfBien().setText("");
        this.getTfCantidad().setText("");
        this.getTfValorEstimado().setText("");
        this.getTfOrdenCompra().setText("");
        this.setListaDelCommunication(null);
        this.getObjectListDataProvider().setList(null);
    }
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpLineaSolSuministro();
    }
    private ObjectListDataProvider getObjectListDataProviderTabla2() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpListaFirmaPermiso();
    }

    private ArrayList getListaDelCommunicationTabla2() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaFirmaPermisos();
    }

    private void setListaDelCommunicationTabla2(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaFirmaPermisos(lista);
    }

    private List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaLineasSoLSuministro();
    }

    private void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaLineasSoLSuministro(lista);
    }
    
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
            this.limpiarTabla();
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
    
    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            rk =
                    this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
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
    // </editor-fold>

    public String btnCancelar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            retorno = this.prepararParaVolver(Constantes.ACCION_CONSULTAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    private ArrayList crearListaLineaFactura(ArrayList list, SolicitudSuministro solicitudS) {
//         private ArrayList crearListaLineaFactura(ArrayList list, FacturaContrato facturaContrato) {
        Iterator iterator = list.iterator();
        LineaSolicitudSuministro locLineaSolSuministro;
        ArrayList locListaLineaSolSuministro = new ArrayList();

        while (iterator.hasNext()) {
            locLineaSolSuministro = (LineaSolicitudSuministro) iterator.next();

            if (locLineaSolSuministro.getCantidad() != 0) {

                locLineaSolSuministro.setSolicitudSuministro(solicitudS);
                locListaLineaSolSuministro.add(locLineaSolSuministro);
            }
        }

        return locListaLineaSolSuministro;
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
                UIComponent[] noVacios = new UIComponent[1];
                String[] nomNoVacios = new String[1];
                UIComponent[] positivo = new UIComponent[2];
                String[] nomPositivo = new String[2];

                int pos = 0;
                noVacios[pos] = this.getTfArea();
                nomNoVacios[pos++] = "\301rea";

                pos=0;
                positivo[pos] = this.getTfCantidad();
                nomPositivo[pos++] = "Cantidad";
                positivo[pos] = this.getTfValorEstimado();
                nomPositivo[pos++] = "Valor Estimado";

                v.noSonVacios(noVacios, nomNoVacios);
                v.sonPositivos(positivo, nomPositivo);

                if (this.getListaDelCommunication() == null || this.getListaDelCommunication().isEmpty()) {
                    String msg = "Debe agregar al menos una L\355nea Factura.";
                    v.getErrores().add(msg);
                }

                SolicitudSuministro solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);
                ArrayList listaLineaSolSuministro = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
                listaLineaSolSuministro = this.crearListaLineaFactura(listaLineaSolSuministro, solicitudSuministro);

                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }
                
              
                // CAMBIAR: Utilizar el EJBClient adecuado.
                this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
//                solicitudSuministro.setEstado(SolicitudSuministro.Estado.CREADA);
                //solicitudSuministro.setUsuario(this.getSessionBean1().getUsuario());


                solicitudSuministro = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().firmarSolicitudSuminstro(solicitudSuministro);
                this.getRequestBean1().setRespuestaABM(solicitudSuministro);
                info("La solicitud se firm\363 exitosamente.");

                retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
            } catch (Exception ex) {
                if (ex instanceof TrascenderException) {
                    int codigoError = ((TrascenderException)ex).getCodeTrascenderException();
                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) retorno = null;
                    else retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
                }
                log(CASO_NAVEGACION+"_GuardarError:", ex);
                error(NOMBRE_PAGINA+" - Guardar: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

}
