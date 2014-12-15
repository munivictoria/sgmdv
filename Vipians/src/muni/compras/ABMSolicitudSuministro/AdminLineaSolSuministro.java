/*
 * AgregarLineaSolSuministro.java
 *
 * Created on 1 de noviembre de 2006, 08:32
 * Copyright Trascender
 */
package muni.compras.ABMSolicitudSuministro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;

import muni.CommunicationMesaEntradaBean;

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
import com.sun.rave.web.ui.component.Tree;
import com.sun.rave.web.ui.component.TreeNode;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class AdminLineaSolSuministro extends AbstractPageBean {

    private void _init() throws Exception {
        if (this.getListaDelCommunication() != null) {
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
    private MessageGroup messageGroup = new MessageGroup();

    public MessageGroup getMessageGroup() {
        return messageGroup;
    }

    public void setMessageGroup(MessageGroup mg) {
        this.messageGroup = mg;
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
    //-------------Static Text---------------
    private StaticText stCantidadRegistros = new StaticText();
    private StaticText stTitulo = new StaticText();
    private StaticText staticText2 = new StaticText();
    private StaticText stBienProvisto = new StaticText();

    public StaticText getStBienProvisto() {
        return stBienProvisto;
    }

    public void setStBienProvisto(StaticText stBienProvisto) {
        this.stBienProvisto = stBienProvisto;
    }

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }

    public StaticText getStCantidadRegistros() {
        return stCantidadRegistros;
    }

    public void setStCantidadRegistros(StaticText st) {
        this.stCantidadRegistros = st;
    }

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }
    //------------CheckBox--------------
    private Checkbox checkbox1 = new Checkbox();

    public Checkbox getCheckbox1() {
        return checkbox1;
    }

    public void setCheckbox1(Checkbox checkbox1) {
        this.checkbox1 = checkbox1;
    }
    //-------------Labels---------------
    private Label label1 = new Label();
    private Label lblEncontrados = new Label();
    private Label lblBien = new Label();
    private Label lblBienProvisto = new Label();
    private Label lblCuenta = new Label();
    private Label lblOrdenCompra = new Label();
    private Label lblCantidad = new Label();
    private Label lblValorEstimado = new Label();
    private Label lblSolicitudSuministro = new Label();
    private Label lblTexto1 = new Label();

    public Label getLblTexto1() {
        return lblTexto1;
    }

    public void setLblTexto1(Label lblTexto1) {
        this.lblTexto1 = lblTexto1;
    }

    public Label getLblSolicitudSuministro() {
        return lblSolicitudSuministro;
    }

    public void setLblSolicitudSuministro(Label lblSolicitudSuministro) {
        this.lblSolicitudSuministro = lblSolicitudSuministro;
    }

    public Label getLblValorEstimado() {
        return lblValorEstimado;
    }

    public void setLblValorEstimado(Label lblValorEstimado) {
        this.lblValorEstimado = lblValorEstimado;
    }

    public Label getLblBienProvisto() {
        return lblBienProvisto;
    }

    public void setLblBienProvisto(Label lblBienProvisto) {
        this.lblBienProvisto = lblBienProvisto;
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

    public void setLblOrdenCompra(Label lbl) {
        this.lblOrdenCompra = lbl;
    }

    public Label getLblBien() {
        return lblBien;
    }

    public void setLblBien(Label lblBien) {
        this.lblBien = lblBien;
    }

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    public Label getLblEncontrados() {
        return lblEncontrados;
    }

    public void setLblEncontrados(Label l) {
        this.lblEncontrados = l;
    }
    //-------------Buttons---------------
    private Button btnCancelar = new Button();
    private Button btnAgregarLinea = new Button();
    private Button btnQuitar = new Button();
    private Button btnQuitarTodos = new Button();
    private Button btnAceptar = new Button();
    private Button btnModificarLinea = new Button();
    private Button btnSeleccionarOrden = new Button();
    private Button btnLimpiarOrden = new Button();
    private Button btnSeleccionarCuenta = new Button();
    private Button btnLimpiarCuenta = new Button();
    private Button btnSeleccionarBienProvisto = new Button();
    private Button btnLimpiarBien = new Button();
    private Button btnSeleccionar = new Button();
    private Button btnSeleccionarSolSuministro = new Button();
    private HtmlAjaxCommandButton btnLimpiarSolSuministro = new HtmlAjaxCommandButton();
    private Button btnBuscarLinea = new Button();
    protected PanelGroup pgParametros = new PanelGroup();
    protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();
    protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarSolSuministro() {
		return btnLimpiarSolSuministro;
	}

	public void setBtnLimpiarSolSuministro(HtmlAjaxCommandButton btnLimpiarSolSuministro) {
		this.btnLimpiarSolSuministro = btnLimpiarSolSuministro;
	}

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
    
    public Button getBtnBuscarLinea() {
        return btnBuscarLinea;
    }

    public void setBtnBuscarLinea(Button btnBuscarLinea) {
        this.btnBuscarLinea = btnBuscarLinea;
    }

    public Button getBtnSeleccionarSolSuministro() {
        return btnSeleccionarSolSuministro;
    }

    public void setBtnSeleccionarSolSuministro(Button btnSeleccionarSolSuministro) {
        this.btnSeleccionarSolSuministro = btnSeleccionarSolSuministro;
    }

    public Button getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public void setBtnSeleccionar(Button btnSeleccionar) {
        this.btnSeleccionar = btnSeleccionar;
    }

    public Button getBtnLimpiarOrden() {
        return btnLimpiarOrden;
    }

    public void setBtnLimpiarOrden(Button btnLimpiarOrden) {
        this.btnLimpiarOrden = btnLimpiarOrden;
    }

    public Button getBtnSeleccionarOrden() {
        return btnSeleccionarOrden;
    }

    public void setBtnSeleccionarOrden(Button btnSeleccionarOrden) {
        this.btnSeleccionarOrden = btnSeleccionarOrden;
    }

    public Button getBtnModificarLinea() {
        return btnModificarLinea;
    }

    public void setBtnModificarLinea(Button btnModificarLinea) {
        this.btnModificarLinea = btnModificarLinea;
    }

    public Button getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(Button btnAceptar) {
        this.btnAceptar = btnAceptar;
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

    public Button getBtnAgregarLinea() {
        return btnAgregarLinea;
    }

    public void setBtnAgregarLinea(Button btnAgregarLinea) {
        this.btnAgregarLinea = btnAgregarLinea;
    }

    public Button getBtnLimpiarBien() {
        return btnLimpiarBien;
    }

    public void setBtnLimpiarBien(Button btnLimpiarBien) {
        this.btnLimpiarBien = btnLimpiarBien;
    }

    public Button getBtnLimpiarCuenta() {
        return btnLimpiarCuenta;
    }

    public void setBtnLimpiarCuenta(Button btnLimpiarCuenta) {
        this.btnLimpiarCuenta = btnLimpiarCuenta;
    }

    public Button getBtnSeleccionarBienProvisto() {
        return btnSeleccionarBienProvisto;
    }

    public void setBtnSeleccionarBienProvisto(Button btnSeleccionarBienProvisto) {
        this.btnSeleccionarBienProvisto = btnSeleccionarBienProvisto;
    }

    public Button getBtnSeleccionarCuenta() {
        return btnSeleccionarCuenta;
    }

    public void setBtnSeleccionarCuenta(Button btnSeleccionarCuenta) {
        this.btnSeleccionarCuenta = btnSeleccionarCuenta;
    }


    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button b) {
        this.btnCancelar = b;
    }
    //-------------Table Objects---------------
    private Table table1 = new Table();
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Vincular a campos ocultos.
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

    // CAMBIAR: Objetos administrados por la pagina.
    //          Generar getters y setters.
    //          En el getter poner:
    //          if (this.objeto == null) this.objeto = new Objeto();
// Ariel
//    private Calle calleABuscar = null;
//    private TipoCalle tipoCalleSeleccionado = null;
    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    // CAMBIAR: Constantes que varian segun la pagina.
    // cantidad de objetos administrados por la pagina
    //private final int CANTIDAD_OBJETOS = 2;
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administración Linea de Solicitud de Suministro";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminLineaSolSuministro";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    //-------------TextFields---------------
    private TextField tfBienProvisto = new TextField();
    private TextField tfCuenta = new TextField();
    private TextField tfOrdenCompra = new TextField();
    private TextField tfCantidad = new TextField();
    private TextField tfValorEstimado = new TextField();
    private TextField tfSolSuministro = new TextField();
    
     


    public TextField getTfSolSuministro() {
        return tfSolSuministro;
    }

    public void setTfSolSuministro(TextField tfSolSuministro) {
        this.tfSolSuministro = tfSolSuministro;
    }

    public TextField getTfValorEstimado() {
        return tfValorEstimado;
    }

    public void setTfValorEstimado(TextField tfValorEstimado) {
        this.tfValorEstimado = tfValorEstimado;
    }

    public TextField getTfCantidad() {
        return tfCantidad;
    }

    public void setTfCantidad(TextField tfCantidad) {
        this.tfCantidad = tfCantidad;
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

    public void setTfOrdenCompra(TextField tf) {
        this.tfOrdenCompra = tf;
    }

    public TextField getTfBienProvisto() {
        return tfBienProvisto;
    }

    public void setTfBienProvisto(TextField tf) {
        this.tfBienProvisto = tf;
    }
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
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

    public Object getLastSelected() {
        return lastSelected;
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
    private ObjectListDataProvider ldpLineaFactura = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpLineaFactura() {
        return ldpLineaFactura;
    }

    public void setLdpLineaFactura(ObjectListDataProvider oldp) {
        this.ldpLineaFactura = oldp;
    }
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn6 = new TableColumn();
    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tableColumn7) {
        this.tableColumn7 = tableColumn7;
    }

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tableColumn6) {
        this.tableColumn6 = tableColumn6;
    }

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
    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText staticText10) {
        this.staticText10 = staticText10;
    }

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
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private TextField tfBien = new TextField();

    public TextField getTfBien() {
        return tfBien;
    }

    public void setTfBien(TextField tf) {
        this.tfBien = tf;
    }
    private TableColumn tableColumn4 = new TableColumn();
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tableColumn5) {
        this.tableColumn5 = tableColumn5;
    }

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }
    private StaticText staticText4 = new StaticText();
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText staticText7) {
        this.staticText7 = staticText7;
    }

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
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
    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }

    // </editor-fold>
    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public AdminLineaSolSuministro() {
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
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.CommunicationContabilidadBean getCommunicationContabilidadBean() {
        return (muni.CommunicationContabilidadBean) getBean("CommunicationContabilidadBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
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
            log("AdminLineaSolSuministro Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
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

            if (this.getListaDelCommunication() != null &&
                    this.getListaDelCommunication().size() > 0 &&
                    this.getRequestBean1().getAccion() != null &&
                    (this.getRequestBean1().getAccion().equals(Constantes.ACCION_AGREGAR) ||
                    this.getRequestBean1().getAccion().equals(Constantes.ACCION_MODIFICAR) ||
                    this.getRequestBean1().getAccion().equals(Constantes.ACCION_ELIMINAR))
                    ) {
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

    private int getCantidadObjetosUsados() {
        return this.getElementoPila().getObjetos().size();
    }

    private void limpiarTabla() {
        this.getObjectListDataProvider().getList().clear();
    }

    public void guardarOrdenamiento() {
// Juan Pablo
        //this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion()).setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
        this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
    }

    public void setearOrdenamiento() {
// Juan Pablo
        //ElementoPila ep = this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
        //this.getTableRowGroup1().setSortCriteria((SortCriteria[]) ep.getOrdenamiento());
        this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
    }
    /*
    public void setearCantidadFilasPorPagina() {
    int cantidadFilas = 10;
    this.getTableRowGroup1().setRows(cantidadFilas);
    }
     */

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

    /**
     * Getter for currentRowSelected
     * @return Boolean true if the checkbox in the current row is selected
     * Bind this property to the row's selected property
     */
    public boolean isCurrentRowSelected() {
        RowKey rowKey = tableRowGroup1.getRowKey();
        // // ariel
        // if (rowKey != null) {
        return tablePhaseListener.isSelected(rowKey);
        // }
        // else return false;
    }

    //hasta aca Mines
    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina

        // ep.getObjetos().add(ind++, new ArrayList());//3 listado de lineas de facturas
        ep.getObjetos().add(ind++, new SolicitudSuministro()); // 3
        ep.getObjetos().add(ind++, new String()); //5 para la selsccion multiple
        ep.getObjetos().add(ind++, new BienProvisto());

        //tipo de seleccion multipla // simple
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() { //ver como seria realmente
        // CAMBIAR: Obtener los valores de los campos y
        SolicitudSuministro solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);
        BienProvisto bienProvisto = (BienProvisto) this.obtenerObjetoDelElementoPila(2, BienProvisto.class);
        this.getObjectListDataProvider().commitChanges();
        this.getElementoPila().getObjetos().set(0, solicitudSuministro);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Obtener una instancia por cada objeto manejado en la pagina
        SolicitudSuministro solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class); //(LineaFactura) this.obtenerObjetoDelElementoPila(4, LineaFactura.class);
        String tipoSeleccion = (String) this.obtenerObjetoDelElementoPila(1, String.class);
        BienProvisto bienProvisto = (BienProvisto) this.obtenerObjetoDelElementoPila(2, BienProvisto.class);

        if (this.getRequestBean1().getTipoSeleccion() != null) {
            tipoSeleccion = this.getRequestBean1().getTipoSeleccion();
            this.getElementoPila().getObjetos().set(1, tipoSeleccion);
        }

//        if (this.getRequestBean1().getObjetoSeleccion() != null) {
//            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
//            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.
//
//            if (seleccionado instanceof SolicitudSuministro) {
//                if (seleccionado != null) {
//                    solicitudSuministro = (SolicitudSuministro) seleccionado;
//                    limpiarTabla();
//                    if ((!solicitudSuministro.getEstado().equals(SolicitudSuministro.Estado.ABIERTA))){
//                        solicitudSuministro = null;
//                        warn("La solicitud de suministro debe estar en estado ABIERTA.");
//                    
//                    }
//                    this.getElementoPila().getObjetos().set(0, solicitudSuministro);
//                }
//            }
//        }
        if (this.getRequestBean1().getObjetoABM() instanceof SolicitudSuministro) {
            solicitudSuministro = (SolicitudSuministro) this.getRequestBean1().getObjetoABM();
            this.getElementoPila().getObjetos().set(0, solicitudSuministro);
        }

        if (this.getRequestBean1().getObjetoABM() instanceof BienProvisto) {
            bienProvisto = (BienProvisto) this.getRequestBean1().getObjetoABM();
            this.getElementoPila().getObjetos().set(2, bienProvisto);
        }

        if (bienProvisto != null) {
            this.getStBienProvisto().setText(bienProvisto);
        }
        solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);
        if (solicitudSuministro.getIdSolicitudSuministro() != -1) {
            this.getTfSolSuministro().setText(solicitudSuministro.toString());
        } else {
            this.getTfSolSuministro().setText("");
        }
        
    }

    private void refrescarTabla() throws Exception {
        // CAMBIAR: Segun objeto de busqueda.
        SolicitudSuministro solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);
        BienProvisto bienProvisto = (BienProvisto) this.obtenerObjetoDelElementoPila(2, BienProvisto.class);

        if(solicitudSuministro.getIdSolicitudSuministro() == -1){
            solicitudSuministro = (SolicitudSuministro) this.getRequestBean1().getObjetoSeleccion();
            this.getElementoPila().getObjetos().set(0, solicitudSuministro);

        }
        ArrayList listaLineaSolicitud = new ArrayList();
        // CAMBIAR: Utilizar el EJBClient adecuado.
        this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
        if (solicitudSuministro != null && solicitudSuministro.getIdSolicitudSuministro() != -1) {
            try {
                
                solicitudSuministro = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(solicitudSuministro.getIdSolicitudSuministro());
                //Se validan y muestran solo aquellas lineas que posean el bien previamente seleccionado y que no tengan asiganadas lineas de Ordenes de Compra
                Iterator locIterador = solicitudSuministro.getListaLineaSolSuministro().iterator();

                while (locIterador.hasNext()) {
                    LineaSolicitudSuministro cadaLineaSolicitudSuministro = (LineaSolicitudSuministro) locIterador.next();

                    if (cadaLineaSolicitudSuministro != null) {
                        if (cadaLineaSolicitudSuministro.getBien().toString().equals(bienProvisto.getBien().toString()) && cadaLineaSolicitudSuministro.getLineaOrdenCompra()== null) {
                            listaLineaSolicitud.add(cadaLineaSolicitudSuministro);
                        }
                    }
                }
                this.setListaDelCommunication(listaLineaSolicitud);
                this.getObjectListDataProvider().setList(this.getListaDelCommunication());

                if (this.getRequestBean1().getObjetoSeleccion() == null) {
                    if (listaLineaSolicitud.isEmpty()) {
                         warn("La Solicitud de Suministro seleccionada no posee L\355neas de Solicitud de Suministro para el Bien seleccionado sin asignar a una Orden de Compra.");
                    }
                }
               
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            warn("Debe seleccionar una Solicitud de Suministro para obtener sus l\355neas.");
        }
        this.setRBSelected((new Long(0)).toString());
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpLineaFactura();
    }

    private List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaLineasSoLSuministro();
    }

    private void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaLineasSoLSuministro(lista);
    }

    private TableSelectPhaseListener getTableSelectPhaseListener() {
        // CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que corresponda
        return this.getCommunicationComprasBean().getTablePhaseListenerLineaSolSuministro();
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
            this.getTableSelectPhaseListener().clear();
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

    public TreeNode getNodoSeleccionado(Tree tr, String idCompleto) {
        TreeNode selectedNode = null;
        if (idCompleto != null) {
            String idNodo = this.getIdSinPrefijo(idCompleto, tr.getId());
            selectedNode = (TreeNode) tr.findComponent(idNodo);
        }
        return selectedNode;
    }

    private String getIdSinPrefijo(String idCompleto, String idComponente) {
        String retorno = null;
        if (idCompleto != null && idCompleto.length() > 0) {
            retorno = idCompleto.substring(idCompleto.indexOf(idComponente) + idComponente.length() + 1);
        }
        return retorno;
    }

    public String btnCancelar_action() {
        //mines:
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            retorno = this.prepararParaVolver(retorno);
            this.limpiarTabla();
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
        // TODO: Replace with your code
        //mines

    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
        return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
    }

    public String btnSeleccionar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            Object tipoSeleccion = (String) this.obtenerObjetoDelElementoPila(1, String.class);

         if (tipoSeleccion.equals("MULTIPLE")) {
                try {
                    // Inicializo el Array de objetos seleccionados
                    if (this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
                        this.getRequestBean1().getObjetosSeleccionMultiple().clear();
                    } else {
                       this.getRequestBean1().setObjetosSeleccionMultiple(new ArrayList());
                    }

                    RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();
                    if (selectedRowKeys.length > 0){
                    
                        for (int i = 0; i < selectedRowKeys.length; i++) {
                            String rowId = selectedRowKeys[i].getRowId();
                            RowKey rowKey = this.getObjectListDataProvider().getRowKey(rowId);
                            Object obj = this.getObjectListDataProvider().getObjects()[getNroFila(rowKey.toString())];
                            LineaSolicitudSuministro lineaSolicitudSuministro = (LineaSolicitudSuministro) obj;
                            this.getRequestBean1().getObjetosSeleccionMultiple().add(obj);
                        }
                    } else{
                        return null;
                    }

                } catch (Exception ex) {
                    log(CASO_NAVEGACION + "_SeleccionarMultiplesObjetosError:", ex);
                    error(NOMBRE_PAGINA + " - Seleccionar M\372ltiples Objetos: " + ex.getMessage());
                    ex.printStackTrace();
                }

                if (!this.getRequestBean1().getObjetosSeleccionMultiple().isEmpty()) {
                    retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
                }
                this.limpiarTabla();
           } else {
//                try {
//                    rk = this.getSeleccionado();
//
//                    if (rk != null) {
//                        int index = getNroFila(rk.toString());
//                        Object obj = this.getObjectListDataProvider().getObjects()[index];
//                        getRequestBean1().setObjetoSeleccion(obj);
//
//                        this.setRowKeySeleccionado(this.getSeleccionado());
//                    }
//
//                } catch (Exception ex) {
//                    log(CASO_NAVEGACION+"_SeleccionarError:", ex);
//                    error(NOMBRE_PAGINA+" - Seleccionar: " + ex.getMessage());
//                }
//
//                if (rk != null) retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnBuscarLinea_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                this.guardarEstadoObjetosUsados();
                this.refrescarTabla();
                this.guardarOrdenamiento();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());
                this.getRequestBean1().setAccion(Constantes.ACCION_BUSCAR);
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

    public String btnSeleccionarSolSuministro_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        BienProvisto bienProvisto = (BienProvisto) this.obtenerObjetoDelElementoPila(2, BienProvisto.class);

        if (ultimo) {

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                // CAMBIAR:
                log(CASO_NAVEGACION + "_SeleccionarCuadraError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar Cuadra: " + ex.getMessage());
            }
            this.getRequestBean1().setObjetoABM(bienProvisto.getBien());
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminSolicitudSuministro";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarSolSuministro_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(0, this.getTfSolSuministro());
            limpiarTabla();
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    private void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        SolicitudSuministro solicitudSuministro = new SolicitudSuministro();
        this.getElementoPila().getObjetos().set(0, solicitudSuministro);
        this.getTfSolSuministro().setText("");
    }

    public String btnReiniciar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                this.limpiarObjetosUsados();
                this.guardarEstadoObjetosUsados();

                this.limpiarTabla();

                this.guardarOrdenamiento();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());

            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION + "_ReiniciarError:", ex);
                error(NOMBRE_PAGINA + " - Reiniciar: " + ex.getMessage());
            }

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
}

