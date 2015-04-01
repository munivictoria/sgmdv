/*
 * AgregarSolicitudSuministro.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.compras.ABMSolicitudSuministro;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.recurso.persistent.Area;
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
public class ModificarLineaSolicitudSuministro extends AbstractPageBean {

    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;
    
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Modificar Linea de Solicitud de Suministro.";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarLineaSolicitudSuministro";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    
    
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn2 = new TableColumn();
    private TableColumn tableColumn3 = new TableColumn();
    private TableColumn tableColumn4 = new TableColumn();
    private TableColumn tableColumn5 = new TableColumn();
    private TableColumn tableColumn6 = new TableColumn();
    
    private StaticText stTitulo = new StaticText();
    private StaticText stSeparador = new StaticText();
    private StaticText staticText4 = new StaticText();
    private StaticText staticText1 = new StaticText();
    private StaticText staticText2 = new StaticText();
    private StaticText staticText3 = new StaticText();
    private StaticText staticText5 = new StaticText();
    private StaticText staticText6 = new StaticText();
    private StaticText staticText7 = new StaticText();
    private StaticText staticText8 = new StaticText();
    private StaticText stCantidad = new StaticText();
    private StaticText stCuenta = new StaticText();
    
    private TextField tfArea = new TextField();
    private TextField tfFechaEmision = new TextField();
    private TextField tfCantidad = new TextField();
    private TextField tfBien = new TextField();
    private TextField tfUsuario = new TextField();
    private TextField tfEstado = new TextField();
    private TextField tfBienProvisto = new TextField();
    private TextField tfOrdenCompra = new TextField();
    
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
    
    private Button btnGuardar = new Button();
    private Button btnCancelar = new Button();
    private Button btnSeleccionarArea = new Button();
    private Button btnLimpiarArea = new Button();
    private HtmlAjaxCommandButton btnLimpiarBien = new HtmlAjaxCommandButton();
    private Button btnSeleccionarBien = new Button();
    private Button btnAgregarLineaSolicitud = new Button();
    private Button btnQuitar = new Button();
    private Button btnQuitarTodos = new Button();
    private Button btnSeleccionarOrden = new Button();
    private Button btnLimpiarOrden = new Button();
    private Button btnSeleccionarCuenta = new Button();
    private Button btnLimpiarCuenta = new Button();
    private Button btnModificarLineaSS = new Button();
    
    private Form form1 = new Form();
    private MessageGroup messageGroup1 = new MessageGroup();
    private HiddenField hidIdPagina = new HiddenField();
    private HiddenField hidIdSubSesion = new HiddenField();
    private Script scriptValidador = new Script();
    private PanelGroup groupPanel1 = new PanelGroup();
    private Body body1 = new Body();
    private Link link1 = new Link();
    private Head head1 = new Head();
    private Html html1 = new Html();
    private Page page1 = new Page();
    
    private TextArea taDescripcion = new TextArea();
    private TableRowGroup tableRowGroup1 = new TableRowGroup();
    private Table table1 = new Table();
    private ObjectListDataProvider ldpLineaSolSuministro = new ObjectListDataProvider();
    private RadioButton radioButton1 = new RadioButton();
    private RowKey rowKeySeleccionado = null;
    
    public HtmlAjaxCommandButton getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(HtmlAjaxCommandButton btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
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

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    } 

    public Page getPage1() {
        return page1;
    }

    public void setPage1(Page p) {
        this.page1 = p;
    }    

    public Html getHtml1() {
        return html1;
    }

    public void setHtml1(Html h) {
        this.html1 = h;
    }    

    public Head getHead1() {
        return head1;
    }

    public void setHead1(Head h) {
        this.head1 = h;
    }    

    public Link getLink1() {
        return link1;
    }

    public void setLink1(Link l) {
        this.link1 = l;
    }    

    public Body getBody1() {
        return body1;
    }

    public void setBody1(Body b) {
        this.body1 = b;
    }    

    public Form getForm1() {
        return form1;
    }

    public void setForm1(Form f) {
        this.form1 = f;
    }    

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }   

    public HiddenField getHidIdPagina() {
        return hidIdPagina;
    }

    public void setHidIdPagina(HiddenField hf) {
        this.hidIdPagina = hf;
    }    

    public HiddenField getHidIdSubSesion() {
        return hidIdSubSesion;
    }

    public void setHidIdSubSesion(HiddenField hf) {
        this.hidIdSubSesion = hf;
    }    

    public Script getScriptValidador() {
        return scriptValidador;
    }

    public void setScriptValidador(Script scriptValidador) {
        this.scriptValidador = scriptValidador;
    }    

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup groupPanel1) {
        this.groupPanel1 = groupPanel1;
    }

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText staticText8) {
        this.staticText8 = staticText8;
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
    
    public TextField getTfBienProvisto() {
        return tfBienProvisto;
    }

    public void setTfBienProvisto(TextField tfBienProvisto) {
        this.tfBienProvisto = tfBienProvisto;
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


    public TextField getTfArea() {
        return tfArea;
    }

    public void setTfArea(TextField tfArea) {
        this.tfArea = tfArea;
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

    public Button getBtnModificarLineaSS() {
        return btnModificarLineaSS;
    }

    public void setBtnModificarLineaSS(Button btnModificarLineaSS) {
        this.btnModificarLineaSS = btnModificarLineaSS;
    }

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
    public ModificarLineaSolicitudSuministro() {
    }
    
    private void _init() throws Exception {
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
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
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
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
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
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
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
            log("AgregarSolicitudSuministro Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
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

        // Pagina nueva - Inicio de transaccion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            if (this.PUEDE_SER_PAGINA_INICIAL) {
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
        ep.getObjetos().add(ind++, new LineaSolicitudSuministro()); //0
        ep.getObjetos().add(ind++, new Bien());//1
        ep.getObjetos().add(ind++, new String()); //2 cantidad
        ep.getObjetos().add(ind++, new LineaSolicitudSuministro()); //3 sum. anterior
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        LineaSolicitudSuministro lineaSolicitudSuministro = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(0, LineaSolicitudSuministro.class);
        Bien bien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
        String cantidad = (String) this.obtenerObjetoDelElementoPila(2, String.class);
        LineaSolicitudSuministro lineaSolicitudSuministroAnterior = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(3, LineaSolicitudSuministro.class);

        Object objCantidad = this.getTfCantidad().getText();

        //LINEA SOLICITUD SUMINISTRO
        if (objCantidad != null && objCantidad != "") {
            lineaSolicitudSuministro.setCantidad(Conversor.getDoubleDeString(objCantidad.toString()).doubleValue());
            cantidad = objCantidad.toString();
        }


        if (bien != null && bien.getIdBien() != -1l) {
            lineaSolicitudSuministro.setBien(bien);
        } else {
            lineaSolicitudSuministro.setBien(null);
        }


        this.getObjectListDataProvider().commitChanges(); //para el listado
        System.out.println("GE bieeeeeeeen........" + bien);
        this.getElementoPila().getObjetos().set(0, lineaSolicitudSuministro);
        this.getElementoPila().getObjetos().set(1, bien);
        this.getElementoPila().getObjetos().set(2, cantidad);
        this.getElementoPila().getObjetos().set(3, lineaSolicitudSuministroAnterior);

    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        LineaSolicitudSuministro lineaSolicitudSuministro = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(0, LineaSolicitudSuministro.class);
        Bien bien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
        String cantidad = (String) this.obtenerObjetoDelElementoPila(2, String.class);

        this.acomodarSeleccionado();
        if (this.getRequestBean1().getObjetoABM() != null) {
            lineaSolicitudSuministro = (LineaSolicitudSuministro) this.getRequestBean1().getObjetoABM();
            LineaSolicitudSuministro lineaSolicitudSuministroAnterior = lineaSolicitudSuministro ;
            bien = lineaSolicitudSuministro.getBien();

            cantidad = Conversor.getStringDeDouble(new Double(lineaSolicitudSuministro.getCantidad()));

            this.getElementoPila().getObjetos().set(0, lineaSolicitudSuministro);
            this.getElementoPila().getObjetos().set(1, bien);
            this.getElementoPila().getObjetos().set(2, cantidad);
            this.getElementoPila().getObjetos().set(3, lineaSolicitudSuministroAnterior);
        }

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();

            if (seleccionado instanceof Bien) {
                try {
                    bien = (Bien) seleccionado;

                    this.getElementoPila().getObjetos().set(1, bien);
                } catch (Exception ex) {
                    log("Error Description", ex);
                }
            }

        }

        int ind = 0;
        try {
            lineaSolicitudSuministro = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++, LineaSolicitudSuministro.class);
            bien = (Bien) this.obtenerObjetoDelElementoPila(ind++, Bien.class);
            cantidad = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (bien != null) {
            this.getTfBien().setText(bien.getNombre());
        }
        
        if (cantidad != null) {
            this.getTfCantidad().setText(cantidad);
        }
  

        this.getTfFechaEmision().setText(Conversor.getStringDeFechaCorta(new Date()));
        this.getTfUsuario().setText(getSessionBean1().getUsuario().toString());

    }
    // </editor-fold>

    private void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        LineaSolicitudSuministro lineaSolicitudSuministro = new LineaSolicitudSuministro();
        Bien bien = null;
        CuentaRfr cuenta = new CuentaRfr();

        this.getElementoPila().getObjetos().set(0, lineaSolicitudSuministro);
        this.getElementoPila().getObjetos().set(1, bien);
        this.getElementoPila().getObjetos().set(2, null);
        this.getElementoPila().getObjetos().set(3, null);

        this.getTfBien().setText("");
        this.getTfCantidad().setText("");
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpLineaSolSuministro();
    }

    private List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaLineasSoLSuministro();
    }

    private void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaLineasSoLSuministro(lista);
    }    

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
            if (campo != null) {
                campo.setText("");
            }
        } catch (Exception ex) {
        }
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
            int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
            this.getElementoPila().getObjetos().set(posicion, seleccionado);
        }
    }

    private int getCantidadObjetosUsados() {
        return this.getElementoPila().getObjetos().size();
    }

    // </editor-fold>
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
        this.guardarEstadoObjetosUsados();
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        LineaSolicitudSuministro lineaSolicitudSuministro = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(0, LineaSolicitudSuministro.class);
        LineaSolicitudSuministro lineaSolicitudSuministroAnterior = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(3, LineaSolicitudSuministro.class);

        Bien bien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
 
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[2];
        String[] nomNoVacios = new String[2];

        int pos = 0;
        noVacios[pos] = this.getTfBien();
        nomNoVacios[pos++] = "Bien Asociado";
        noVacios[pos] = this.getTfCantidad();
        nomNoVacios[pos++] = "Cantidad";
 

        v.noSonVacios(noVacios, nomNoVacios);

        if (this.getTfCantidad().getText().equals("") || this.getTfCantidad().getText().toString().equals("0.0") || this.getTfCantidad().getText().toString().equals("0")) {
            String msg = "Se debe seleccionar una Cantidad Mayor a 0.";
            this.getTfCantidad().setValid(false);
            v.getErrores().add(msg);
        }


        if (v.getErrores().size() > 0) {
            error("Existen Errores:");
            for (int i = 0; i < v.getErrores().size(); i++) {
                warn(v.getErrores().toArray()[i].toString());
            }

            return null;
        } else {

        if (ultimo) {
         
                try {
                    lineaSolicitudSuministro.setBien(bien);
                   // lineaSolicitudSuministro.setCuentaRfr(cuentaRfr);

                    ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
                    this.getRequestBean1().setObjetoSeleccion(lineaSolicitudSuministro);
                    this.getRequestBean1().setObjetoABM(lineaSolicitudSuministroAnterior);
                    
                    this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
                    this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
                    this.setRowKeySeleccionado(this.getSeleccionado());

                } catch (Exception ex) {
                    log(CASO_NAVEGACION + "_SeleccionarError:", ex);
                    error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
                }

                retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);
            } else {
                retorno = this.prepararCaducidad();
            }
            return retorno;
        }
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

    public String btnSeleccionarArea_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminArea";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarArea_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(1, Area.class, this.getTfArea());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarBien_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminBien";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarBien_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(1, Bien.class, this.getTfBien());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public void btnAgregarLineaSolicitud_action() {
        this.guardarEstadoObjetosUsados();       

        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[2];
        String[] nomNoVacios = new String[2];

        int pos = 0;
        noVacios[pos] = this.getTfBien();
        nomNoVacios[pos++] = "Bien Asociado";
        noVacios[pos] = this.getTfCantidad();
        nomNoVacios[pos++] = "Cantidad";


        v.noSonVacios(noVacios, nomNoVacios);

        if (this.getTfCantidad().getText().equals("") || this.getTfCantidad().getText().toString().contains("0.0")) {
            String msg = "Se debe seleccionar una Cantidad Mayor a 0.";
            this.getTfCantidad().setValid(false);
            v.getErrores().add(msg);
        }


        if (v.getErrores().size() > 0) {
            error("Existen Errores:");
            for (int i = 0; i < v.getErrores().size(); i++) {
                warn(v.getErrores().toArray()[i].toString());
            }

        } else {
            this.limpiarObjetosUsados();

        }

    }

    public String btnQuitar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            RowKey rk = null;

            // APLICAR LOGICA AQUI...
            try {
                rk = this.getSeleccionado();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    this.getListaDelCommunication().remove(obj);
                }
            } catch (Exception ex) {
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnQuitarTodos_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            // APLICAR LOGICA AQUI...
            try {
                this.getListaDelCommunication().clear();
            } catch (Exception ex) {
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }
    
    public String btnModificarLineaSS_action() {

        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            // APLICAR LOGICA AQUI...
            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    this.getRequestBean1().setObjetoABM(obj);
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
                retorno = "ModificarLineaSolicitudSuministro";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

}
