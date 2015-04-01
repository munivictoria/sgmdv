/*
 * AgregarLineaFacturaProveedor.java
 *
 * Created on 1 de noviembre de 2006, 08:32
 * Copyright Trascender
 */
package muni.compras.ABMFacturaProveedor;

import java.util.ArrayList;

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
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Tree;
import com.sun.rave.web.ui.component.TreeNode;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
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
public class ModificarLineaFacturaProveedor extends AbstractPageBean {
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
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


    //-------------Labels---------------
    private Label label1 = new Label();
    private Label lblEncontrados = new Label();
    private Label lblProveedor = new Label();
    private Label lblBienProvisto = new Label();
    private Label lblCuenta = new Label();
    private Label lblMonto = new Label();
    private Label lblCantidad = new Label();

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

    public Label getLblMonto() {
        return lblMonto;
    }

    public void setLblMonto(Label lblMonto) {
        this.lblMonto = lblMonto;
    }

    public Label getLblProveedor() {
        return lblProveedor;
    }

    public void setLblProveedor(Label lblProveedor) {
        this.lblProveedor = lblProveedor;
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

    private Button btnBuscar = new Button();
    private Button btnCancelar = new Button();
    private Button btnSeleccionarBienProvisto = new Button();
    private HtmlAjaxCommandButton btnLimpiarBien = new HtmlAjaxCommandButton();
    private Button btnSeleccionarCuenta = new Button();
    private HtmlAjaxCommandButton btnLimpiarCuenta = new HtmlAjaxCommandButton();
    private Button btnAgregarLinea = new Button();
    private Button btnQuitar = new Button();
    private Button btnQuitarTodos = new Button();
    private Button btnAceptar = new Button();

    public HtmlAjaxCommandButton getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(HtmlAjaxCommandButton btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCuenta() {
		return btnLimpiarCuenta;
	}

	public void setBtnLimpiarCuenta(HtmlAjaxCommandButton btnLimpiarCuenta) {
		this.btnLimpiarCuenta = btnLimpiarCuenta;
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

    public Button getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(Button b) {
        this.btnBuscar = b;
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
    private final String NOMBRE_PAGINA = "Modificar Linea de Factura de Proveedor";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarLineaFacturaProveedor";
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
    private TextField tfMonto = new TextField();
    private TextField tfCantidad = new TextField();

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

    public TextField getTfMonto() {
        return tfMonto;
    }

    public void setTfMonto(TextField tfMonto) {
        this.tfMonto = tfMonto;
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
    
    private TextField tfProveedor = new TextField();

    public TextField getTfProveedor() {
        return tfProveedor;
    }

    public void setTfProveedor(TextField tf) {
        this.tfProveedor = tf;
    }
   
    private TableColumn tableColumn4 = new TableColumn();
    private TableColumn tableColumn5 =  new TableColumn();

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
    public ModificarLineaFacturaProveedor() {
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
            log("AdminCalle Initialization Failure", e);
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
                    this.getRequestBean1().getAccion().equals(Constantes.ACCION_ELIMINAR))) {
//                try {
//                    this.refrescarTabla();
//                } catch (Exception ex) {
//                    this.limpiarTabla();
//                }
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
            if (campo!=null) campo.setText(null);
        } catch (Exception ex) { }
    }

    private int getCantidadObjetosUsados() {
        return this.getElementoPila().getObjetos().size();
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
        long cantFilas = this.getTableRowGroup1().getRowCount();
        if (cantFilas > 0) {
            // si hay al menos una fila
            if (posicionGlobal.longValue() >= cantFilas) {
                // si elimine la ultima fila, me posiciono en la anterior
                posicionGlobal = new Long(cantFilas - 1);
            }

            int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
            this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
            lastSelected = new Long(index).toString();
        }
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

        ep.getObjetos().add(ind++, new Proveedor());//0
        ep.getObjetos().add(ind++, new BienProvisto());//1
        ep.getObjetos().add(ind++, new CuentaRfr());//2
        ep.getObjetos().add(ind++, new ArrayList());//3 listado de lineas de facturas
//        ep.getObjetos().add(ind++, new LineaFactura());
        ep.getObjetos().add(ind++, new String()); //monto unitario

        //tipo de seleccion multipla // simple
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() { //ver como seria realmente
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);
        BienProvisto bienProvisto = (BienProvisto) this.obtenerObjetoDelElementoPila(1, BienProvisto.class);
        CuentaRfr cuentaRfr = (CuentaRfr) this.obtenerObjetoDelElementoPila(2, CuentaRfr.class);
        ArrayList listaLineasFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
        LineaFactura lineaFactura = (LineaFactura) this.obtenerObjetoDelElementoPila(4, LineaFactura.class);
        String montoUnitario = (String) this.obtenerObjetoDelElementoPila(5, String.class);


        if(this.getRequestBean1().getObjetoABM() != null && proveedor.getIdProveedor() == -1) {
            proveedor = (Proveedor) this.getRequestBean1().getObjetoABM();
        }
        Object monto = this.getTfMonto().getText();
        Object cantidad = this.getTfCantidad().getText();

        if (monto != null && monto != "") {
            //lineaFactura.setMontoUnitario(new Double(monto.toString()));
            montoUnitario = monto.toString();
        }
        else {
            montoUnitario = null;
            //lineaFactura.setMontoUnitario(null);
        }
        
        if (cantidad != null && cantidad != "") {
            lineaFactura.setCantidad(new Double(cantidad.toString()));
        }
        else {
            lineaFactura.setCantidad(null);
        }

        if(bienProvisto != null && bienProvisto.getIdBienProvisto() != -1l){
//            lineaFactura.setBienProvisto(bienProvisto);
        }
        
        if(cuentaRfr != null && cuentaRfr.getIdCuenta() != -1l){
            lineaFactura.setCuenta(cuentaRfr);
        }

        if(montoUnitario != null && montoUnitario !=""){
            lineaFactura.setMontoUnitario(Conversor.getDoubleDeString(montoUnitario));
        }

        if(lineaFactura.getCantidad() != null && lineaFactura.getMontoUnitario() != null){
//            double locTotal = lineaFactura.getCantidad().doubleValue() * lineaFactura.getMontoUnitario().doubleValue();
            lineaFactura.setTotal(null); //se calcula desde la logica
        }

        this.getObjectListDataProvider().commitChanges();

        this.getElementoPila().getObjetos().set(0, proveedor);
        this.getElementoPila().getObjetos().set(1, bienProvisto);
        this.getElementoPila().getObjetos().set(2, cuentaRfr);
        this.getElementoPila().getObjetos().set(3, listaLineasFactura);
        this.getElementoPila().getObjetos().set(4, lineaFactura);
        this.getElementoPila().getObjetos().set(5, montoUnitario);

    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Obtener una instancia por cada objeto manejado en la pagina
        this.limpiarObjeto(2,this.getTfCuenta());
        Proveedor proveedor = null;
        BienProvisto bienProvisto = null;
        CuentaRfr cuentaRfr = null;
        Cuenta cuenta = null;
        ArrayList listaLineasFactura = null; //(ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
//        LineaFactura lineaFactura = new LineaFactura(); //(LineaFactura) this.obtenerObjetoDelElementoPila(4, LineaFactura.class);
        String montoUnitario = null;


        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.
            if (seleccionado instanceof BienProvisto) {
                if (seleccionado != null) {
                    bienProvisto = (BienProvisto) seleccionado;
                    montoUnitario = Conversor.getStringDeDouble(new Double(bienProvisto.getPrecio()));
                    System.out.println("Bien provisto: "+bienProvisto);

                    //this.getTfMonto().setText(Conversor.getStringDeDouble(new Double(bienProvisto.getPrecio())));
//                    lineaFactura.setMontoUnitario(new Double(bienProvisto.getPrecio()));
                    this.getElementoPila().getObjetos().set(1, bienProvisto);
//                    this.getElementoPila().getObjetos().set(4, lineaFactura);
                    this.getElementoPila().getObjetos().set(5, montoUnitario);
                }
            }
            if(seleccionado instanceof Cuenta){
                if(seleccionado != null){
                    cuenta = (Cuenta) seleccionado;
                    try {
                        this.getCommunicationContabilidadBean().getRemoteSystemAdministracionConsultaContable().setLlave(this.getSessionBean1().getLlave());
                        cuenta = (Cuenta) this.getCommunicationContabilidadBean().getRemoteSystemAdministracionConsultaContable().getCuentaByID(new Long(cuenta.getIdCuenta()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                    cuentaRfr = new CuentaRfr();
                        //seteando a pata los datos de la cuenta a la cuentarRfr para asignarla a la linea de factura correspondiente
                    cuentaRfr.setAbreviatura(cuenta.getAbreviatura());
                    cuentaRfr.setCodigoImputacion(cuenta.getCodigoImputacion());
                    cuentaRfr.setNombre(cuenta.getNombre());
                    cuentaRfr.setIdCuenta(cuenta.getIdCuenta());
                    
                    this.getElementoPila().getObjetos().set(2, cuentaRfr);
                }
                
            }

            if(seleccionado instanceof ArrayList){
                if(seleccionado != null){
                    listaLineasFactura = (ArrayList) seleccionado;
                    this.getElementoPila().getObjetos().set(3, listaLineasFactura);
                }
            }

            if(seleccionado instanceof LineaFactura){
                if(seleccionado != null){
//                    lineaFactura = (LineaFactura) seleccionado;
//                    bienProvisto = lineaFactura.getBienProvisto();
//                    proveedor = lineaFactura.getBienProvisto().getProveedor();
//                    cuentaRfr = lineaFactura.getCuenta();
//                    montoUnitario = Conversor.getStringDeDouble(lineaFactura.getMontoUnitario());
                    
//                    listaLineasFactura.add(lineaFactura);
                    
                    this.getElementoPila().getObjetos().set(0, proveedor);
                    this.getElementoPila().getObjetos().set(1, bienProvisto);
                    this.getElementoPila().getObjetos().set(2, cuentaRfr);
                    this.getElementoPila().getObjetos().set(3, listaLineasFactura);
//                    this.getElementoPila().getObjetos().set(4, lineaFactura);
                    this.getElementoPila().getObjetos().set(5, montoUnitario);
                }
            }
        }

        if (this.getRequestBean1().getObjetoABM() != null) {

            if(this.getRequestBean1().getObjetoABM() instanceof LineaFactura){
//                lineaFactura = (LineaFactura) this.getRequestBean1().getObjetoABM();
//                bienProvisto = lineaFactura.getBienProvisto();
//                proveedor = lineaFactura.getBienProvisto().getProveedor();
//                montoUnitario = Conversor.getStringDeDouble(lineaFactura.getMontoUnitario());
//                cuentaRfr = lineaFactura.getCuenta();
//                if(lineaFactura!=null){
//                    listaLineasFactura = new ArrayList();
//                    listaLineasFactura.add(lineaFactura);
//                }
//                
                

                this.getElementoPila().getObjetos().set(0, proveedor);
                this.getElementoPila().getObjetos().set(1, bienProvisto);
                this.getElementoPila().getObjetos().set(2, cuentaRfr);
                this.getElementoPila().getObjetos().set(3, listaLineasFactura);
//                this.getElementoPila().getObjetos().set(4, lineaFactura);
                this.getElementoPila().getObjetos().set(5, montoUnitario);
            }
            if(this.getRequestBean1().getObjetoABM() instanceof Proveedor){
                proveedor = (Proveedor) this.getRequestBean1().getObjetoABM();
                this.getElementoPila().getObjetos().set(0, proveedor);
            }
            
        }

        proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);
        bienProvisto = (BienProvisto) this.obtenerObjetoDelElementoPila(1, BienProvisto.class);
        cuentaRfr = (CuentaRfr) this.obtenerObjetoDelElementoPila(2, CuentaRfr.class);
        listaLineasFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
//        lineaFactura = (LineaFactura) this.obtenerObjetoDelElementoPila(4, LineaFactura.class);
        montoUnitario = (String) this.obtenerObjetoDelElementoPila(5, String.class);

        System.out.println("Bien provisto mostrar: "+bienProvisto);
//        this.getTfBienProvisto().setText(lineaFactura.getBien());
        this.getTfProveedor().setText(proveedor);

//        if(lineaFactura.getCuenta() != null && lineaFactura.getCuenta().getIdCuenta() != -1){
//            this.getTfCuenta().setText(lineaFactura.getCuenta());
//        } else this.getTfCuenta().setText(" ");
//
//        this.getTfCantidad().setText(lineaFactura.getCantidad());

        this.getTfMonto().setText(montoUnitario);
        
        for(Object lf:listaLineasFactura){
            System.out.println("Linea Factura: "+lf);
        }
        
        //this.getObjectListDataProvider().setList(listaLineasFactura);
        //this.setListaDelCommunication(listaLineasFactura);

    }

    private void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
//        LineaFactura lineaFactura = new LineaFactura();
        BienProvisto bienProvisto = null;
        CuentaRfr cuenta = new CuentaRfr();
        Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);
        
        this.getElementoPila().getObjetos().set(1, bienProvisto);
        this.getElementoPila().getObjetos().set(2, cuenta);
//        this.getElementoPila().getObjetos().set(4, lineaFactura);

        this.getTfBienProvisto().setText("");
        this.getTfCantidad().setText("");
        this.getTfCuenta().setText("");
        this.getTfMonto().setText("");
        this.getTfProveedor().setText("");
        this.getTfProveedor().setText(proveedor);

    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpLineaFactura();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaLineasFacturaProveedor();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaLineasFacturaProveedor(lista);
    }

    private TableSelectPhaseListener getTableSelectPhaseListener() {
        // CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que corresponda
        return this.getCommunicationComprasBean().getTablePhaseListenerLineaContratacion();
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

            // APLICAR LOGICA AQUI...

            retorno = this.prepararParaVolver(retorno);
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    // TODO: Replace with your code
    //mines

    }

    public String btnLimpiarTipoCalle_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if(ultimo){
            this.limpiarObjeto(1, this.getTfBienProvisto());
            this.guardarEstadoObjetosUsados();
        }else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }


    public String btnSeleccionarBienProvisto_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);
        try{
            this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(this.getSessionBean1().getLlave());
            proveedor = this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().findProveedorByID(proveedor.getIdProveedor());

        } catch(Exception e){
            e.printStackTrace();
        }

        if (ultimo) {
            // APLICAR LOGICA AQUI...
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setObjetoABM(proveedor);
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminBien";

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnSeleccionarCuenta_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(0, Proveedor.class);

        if (ultimo) {
            // APLICAR LOGICA AQUI...
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setObjetoABM(proveedor);
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminCuenta";

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
//        int posicionObjetoSeleccionado = 1;
//
//        if (ultimo) {
//
//            // APLICAR LOGICA AQUI...
//
//            try {
//                RowKey rk = this.getSeleccionado();
//                if (rk != null) {
//                    this.setRowKeySeleccionado(this.getSeleccionado());
//                }
//            } catch (Exception ex) {
//                // CAMBIAR:
//                log(CASO_NAVEGACION+"_SeleccionarCuentaError:", ex);
//                error(NOMBRE_PAGINA+" - Seleccionar Cuenta: " + ex.getMessage());
//            }
//
//            this.guardarEstadoObjetosUsados();
//            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//            this.guardarOrdenamiento();
//            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
//            this.getElementoPila().setPosicionGlobal(pos.longValue());
//
//            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
//            retorno = "AdminCuenta";
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//
//        return retorno;
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
            } catch (Exception ex) {}

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
            } catch (Exception ex) {}

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public void btnAgregarLinea_action() {
        this.guardarEstadoObjetosUsados();
       
        ArrayList listaLineasFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
        LineaFactura lineaFactura = (LineaFactura) this.obtenerObjetoDelElementoPila(4, LineaFactura.class);

        //Validaciones
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[3];
        String[] nomNoVacios = new String[3];

        int pos = 0;
        noVacios[pos] = this.getTfBienProvisto();
        nomNoVacios[pos++] = "Bien Provisto";
        noVacios[pos] = this.getTfCantidad();
        nomNoVacios[pos++] = "Cantidad";
        noVacios[pos] = this.getTfMonto();
        nomNoVacios[pos++] = "Precio Unitario";

        v.noSonVacios(noVacios, nomNoVacios);

        if(this.getTfBienProvisto().getText().equals("") || this.getTfBienProvisto().getText().toString() == ""){
            String msg = "Debe seleccionar un Bien Provisto por el Proveedor";
            v.getErrores().add(msg);
        }
        if(this.getTfCantidad().getText().equals("") || this.getTfCantidad().getText().toString().equals("0.0")|| this.getTfCantidad().getText().toString().equals("0")){
            String msg = "No se Puede Agregar una L\355nea de Factura Proveedor con Cantidad 0.";
            v.getErrores().add(msg);
        }
        if(this.getTfMonto().getText().equals("") || this.getTfMonto().getText().toString().equals("0.0")|| this.getTfMonto().getText().toString().equals("0")){
            String msg = "No se Puede Agregar una L\355nea de Factura Proveedor con Monto 0.";
            v.getErrores().add(msg);
        }

        if (v.getErrores().size() > 0) {
            error("Existen Errores:");
            for (int i = 0; i < v.getErrores().size(); i++) {
                warn(v.getErrores().toArray()[i].toString());
            }

        } else {
            if(lineaFactura != null){
                listaLineasFactura.add(lineaFactura);
            }
            this.setListaDelCommunication(listaLineasFactura);
            this.getObjectListDataProvider().setList(listaLineasFactura);
            this.limpiarObjetosUsados();
        // TODO: Replace with your code
        }
    }

    public String btnAceptar_action() {
         String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
        
            try {
                ArrayList listaLineasFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                getRequestBean1().setObjetoSeleccion(listaLineasFactura);

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_SeleccionarError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
            }

            this.setListaDelCommunication(null);
            this.getObjectListDataProvider().setList(null);
            retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarBien_action() {
        String retorno=null;
        boolean ultimo= this.ultimoElementoPilaDeSubSesion();
        if(ultimo){
            this.limpiarObjeto(1,this.getTfBienProvisto());
            this.guardarEstadoObjetosUsados();
        }else{
            retorno=this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarCuenta_action() {
        String retorno=null;
        boolean ultimo= this.ultimoElementoPilaDeSubSesion();
        if(ultimo){
            this.limpiarObjeto(2,this.getTfCuenta());
            this.guardarEstadoObjetosUsados();
        }else{
            retorno=this.prepararCaducidad();
        }
        return retorno;
    }


}

