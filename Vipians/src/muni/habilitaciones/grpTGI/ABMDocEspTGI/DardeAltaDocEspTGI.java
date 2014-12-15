/*
 * DardeAltaDocEspTGI.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpTGI.ABMDocEspTGI;

import java.util.ArrayList;
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
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class DardeAltaDocEspTGI extends AbstractPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a campos ocultos.
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

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "DardeAlta Obligaci\363n: TGI";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "DardeAltaDocEspTGI";
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
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
     */
    private void _init() throws Exception {

        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider2().setList(this.getListaDelCommunication());
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
    private StaticText stPersona = new StaticText();

    public StaticText getStPersona() {
        return stPersona;
    }

    public void setStPersona(StaticText st) {
        this.stPersona = st;
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
    private ObjectListDataProvider ldpDocEspOSP = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpDocEspOSP() {
        return ldpDocEspOSP;
    }
    private RowKey rowKeySeleccionado = null;

    public RowKey getRowKeySeleccionado() {
        return rowKeySeleccionado;
    }

    public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
        this.rowKeySeleccionado = rowKeySeleccionado;
    }
    private ObjectListDataProvider ldpLogModificacionesSHPS = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpLogModificacionesSHPS() {
        return ldpLogModificacionesSHPS;
    }

    public void setLdpLogModificacionesSHPS(ObjectListDataProvider oldp) {
        this.ldpLogModificacionesSHPS = oldp;
    }
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }

    public void guardarOrdenamiento() {
        this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
    }

    public void setearOrdenamiento() {
        this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
    }
    private Button btnSeleccionarDomicilioPostal = new Button();

    public Button getBtnSeleccionarDomicilioPostal() {
        return btnSeleccionarDomicilioPostal;
    }

    public void setBtnSeleccionarDomicilioPostal(Button b) {
        this.btnSeleccionarDomicilioPostal = b;
    }
    private HtmlAjaxCommandButton btnLimpiarDomicilioPostal = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarDomicilioPostal() {
		return btnLimpiarDomicilioPostal;
	}

	public void setBtnLimpiarDomicilioPostal(HtmlAjaxCommandButton btnLimpiarDomicilioPostal) {
		this.btnLimpiarDomicilioPostal = btnLimpiarDomicilioPostal;
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
    private Button btnSeleccionarDomicilioSolicitante = new Button();

    public Button getBtnSeleccionarDomicilioSolicitante() {
        return btnSeleccionarDomicilioSolicitante;
    }

    public void setBtnSeleccionarDomicilioSolicitante(Button b) {
        this.btnSeleccionarDomicilioSolicitante = b;
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
    private Button btnSeleccionarDomicilioParcela = new Button();

    public Button getBtnSeleccionarDomicilioParcela() {
        return btnSeleccionarDomicilioParcela;
    }

    public void setBtnSeleccionarDomicilioParcela(Button b) {
        this.btnSeleccionarDomicilioParcela = b;
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
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private TextField tfFechaCese = new TextField();

    public TextField getTfFechaCese() {
        return tfFechaCese;
    }

    public void setTfFechaCese(TextField tf) {
        this.tfFechaCese = tf;
    }
    private Button btnLimpiarParcela = new Button();

    public Button getBtnLimpiarParcela() {
        return btnLimpiarParcela;
    }

    public void setBtnLimpiarParcela(Button b) {
        this.btnLimpiarParcela = b;
    }
    private Button btnSeleccionarParcela = new Button();

    public Button getBtnSeleccionarParcela() {
        return btnSeleccionarParcela;
    }

    public void setBtnSeleccionarParcela(Button b) {
        this.btnSeleccionarParcela = b;
    }
    private ObjectListDataProvider ldpPropietarios = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpPropietarios() {
        return ldpPropietarios;
    }

    public void setLdpPropietarios(ObjectListDataProvider oldp) {
        this.ldpPropietarios = oldp;
    }
    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
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
    private RadioButton radioButton1 = new RadioButton();

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton rb) {
        this.radioButton1 = rb;
    }

    public String getCurrentRow3() {
        return tableRowGroup1.getRowKey().getRowId();
    }

    public void setCurrentRow3(int row) {
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

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public DardeAltaDocEspTGI() {
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
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
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
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
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
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se navega hasta
     * esta p�gina, ya sea directamente mediante un URL o de manera indirecta a
     * trav�s de la navegaci�n de p�ginas. Puede personalizar este m�todo para
     * adquirir recursos que se necesitar�n para los controladores de eventos y
     * m�todos del proceso, sin tener en cuenta si esta p�gina realiza
     * procesamiento de devoluci�n de env�os.</p>
     *
     * <p>Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o,
     * los valores de propiedad de los componentes <strong>no</strong>
     * representan ning�n valor enviado con esta petici�n. En su lugar,
     * representan los valores de propiedades que se guardaron para esta vista
     * cuando se proces�.</p>
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
            log("DardeAltaDocEspTGI Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

    }

    /**
     * <p>M�todo de devoluci�n de llamada al que se llama cuando el �rbol de
     * componentes se ha restaurado, pero antes de que se produzca el
     * procesamiento de cualquier evento. Este m�todo <strong>s�lo</strong> se
     * llamar� en una petici�n de devoluci�n de env�o que est� procesando el
     * env�o de un formulario. Puede personalizar este m�todo para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /**
     * <p>M�todo de devoluci�n de llamada al que se llama justo antes del
     * procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la
     * p�gina que se procesa, no se llamar�, por ejemplo, en una p�gina que ha
     * procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra
     * p�gina. Puede personalizar este m�todo para asignar recursos necesarios
     * para procesar esta p�gina.</p>
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
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se completa el
     * procesamiento de esta petici�n, si se llam� al m�todo
     * <code>init()</code> (sin tener en cuenta si se trata de la p�gina que se
     * ha procesado o no). Puede personalizar este m�todo para liberar los
     * recursos adquiridos en los m�todos
     * <code>init()</code>,
     * <code>preprocess()</code> o
     * <code>prerender()</code> (o durante la ejecuci�n de un controlador de
     * eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, null); // Obligacion
        ep.getObjetos().add(ind++, new DocumentoTGI());
        ep.getObjetos().add(ind++, null); // Persona solicitante
        ep.getObjetos().add(ind++, new Parcela());
        ep.getObjetos().add(ind++, new Domicilio());
        ep.getObjetos().add(ind++, null); // 5 lista de propietarios de la parcela

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Obligacion obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
        DocumentoTGI documentoTGI = (DocumentoTGI) this.obtenerObjetoDelElementoPila(ind++, DocumentoTGI.class);
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        ArrayList listaPropietarios  = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        
        Object nombre = this.getTfNombre().getText();
        Object fechaInicio = this.getTfFechaInicio().getText();
        Object fechaCese = this.getTfFechaCese().getText();

        if (nombre != null && nombre != "") {
            documentoTGI.setNombre(nombre.toString());
        } else {
            documentoTGI.setNombre(null);
        }
        if (fechaInicio != null && fechaInicio != "") {
            documentoTGI.setFechaInicioActividad(Conversor.getFechaCortaDeString(fechaInicio.toString()));
        } else {
            documentoTGI.setFechaInicioActividad(null);
        }
        if (fechaCese != null && fechaCese != "") {
            documentoTGI.setFechaCeseActividad(Conversor.getFechaCortaDeString(fechaCese.toString()));
        } else {
            documentoTGI.setFechaCeseActividad(null);
        }

//        if (parcela.getIdParcela() == -1) {
//            parcela = null;
//        } else {
            //documentoTGI.setParcela(parcela);
           // listaPropietarios.addAll(listaPropietarios);
//
//            this.getObjectListDataProvider2().setList(listaPropietarios);
//            this.setListaDelCommunication(listaPropietarios);
//        }

        if (domicilio.getLocalidad() == null) {
            domicilio = null;
        }
        documentoTGI.setDomicilio(domicilio);
        //////////
        RowKey rk = null;
        try {
            rk = this.getSeleccionado();
            if (rk != null) {
                int index = getNroFila(rk.toString());
                Object obj = this.getObjectListDataProvider2().getObjects()[index];
                RegistroPropietario locRegistroPropietario = (RegistroPropietario) obj;

                persona = (Persona) locRegistroPropietario.getPersona();
                //   domicilio = (Domicilio)locRegistroPropietario.getPersona().getDomicilioPostal();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ind = 0;
        ind++;
        this.getElementoPila().getObjetos().set(ind++, documentoTGI);
        this.getElementoPila().getObjetos().set(ind++, persona);
        this.getElementoPila().getObjetos().set(ind++, parcela);
        this.getElementoPila().getObjetos().set(ind++, domicilio);
        this.getElementoPila().getObjetos().set(ind++, listaPropietarios);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Obligacion obligacion = null;
        DocumentoTGI documentoTGI = null;
        Persona persona = null;
        Parcela parcela = null;
        Domicilio domicilio = null;
        ArrayList listaPropietarios = new ArrayList();

         if (this.getRequestBean1().getObjetoABM() != null) {

            obligacion = (Obligacion) this.getRequestBean1().getObjetoABM();
            
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
                obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(obligacion.getIdObligacion());
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_obligacion:", ex);
                error(NOMBRE_PAGINA + " - obligacion: " + ex.getMessage());
                ex.printStackTrace();
            }
            
            documentoTGI = (DocumentoTGI)obligacion.getDocumentoEspecializado();
//            
//            try {
//                this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTGI().setLlave(this.getSessionBean1().getLlave());
//                documentoTGI = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTGI().getDocumentoTGI(obligacion);
//            } catch (Exception ex) {
//                log(CASO_NAVEGACION + "_getDocumentoHabilitanteTGI:", ex);
//                error(NOMBRE_PAGINA + " - No se pudo obtener el Documento TGI: " + ex.getMessage());
//                ex.printStackTrace();
//            }
//             
            try {
                this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
                parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(obligacion.getDocumentoEspecializado().getParcela().getIdParcela());
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "parcela:", ex);
                error(NOMBRE_PAGINA + " - No se pudo obtener parcela: " + ex.getMessage());
                ex.printStackTrace();
            }

            if (parcela != null && parcela.getIdParcela() != -1) {
                listaPropietarios.addAll(parcela.getTituloPropiedad().getListaRegistrosPropietarios());
                System.out.println("tam lista prop " + parcela.getTituloPropiedad().getListaRegistrosPropietarios().size());
                
            }
            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, obligacion);
            this.getElementoPila().getObjetos().set(ind++, documentoTGI);
            this.getElementoPila().getObjetos().set(ind++, persona);
            this.getElementoPila().getObjetos().set(ind++, parcela);
            this.getElementoPila().getObjetos().set(ind++, domicilio);
            this.getElementoPila().getObjetos().set(ind++, listaPropietarios);
        }
        /*
         * if (this.getRequestBean1().getObjetoSeleccion() != null) { Object
         * seleccionado = this.getRequestBean1().getObjetoSeleccion(); int
         * posicion =
         * ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados()
         * - 1, Integer.class)).intValue();
         *
         * // Logica especial de DocEspSHPS para mostrar el solicitante en el
         * Agregar /* if ((seleccionado instanceof Persona)) { persona =
         * (Persona) seleccionado; domicilio = persona.getDomicilio();
         * this.getElementoPila().getObjetos().set(2, persona);
         * this.getElementoPila().getObjetos().set(4, domicilio);
         * this.getRequestBean1().setObjetoSeleccion(null); }
        }
         */
//        if (this.getRequestBean1().getObjetoSeleccion() != null) {
//            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
//
//        }

        this.acomodarSeleccionado();

        if (this.getRequestBean1().getRespuestaABM() != null) {
            Object respuesta = this.getRequestBean1().getRespuestaABM();
            // int posicionEP = -1;
            if (respuesta instanceof Domicilio) {
                int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
                this.getElementoPila().getObjetos().set(posicion, respuesta);
            }
        }
     
        ind = 0;
        ind++;
        documentoTGI = (DocumentoTGI) this.obtenerObjetoDelElementoPila(ind++, DocumentoTGI.class);
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        listaPropietarios = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        this.getTfNombre().setText(documentoTGI.getNombre());
        if (documentoTGI.getFechaInicioActividad() != null) {
            this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoTGI.getFechaInicioActividad()));
        }
        if (documentoTGI.getFechaCeseActividad() != null) {
            this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoTGI.getFechaCeseActividad()));
        }
        if (persona != null) {
            this.getTfPersona().setText(persona.toString());
        }
        this.getTfParcela().setText(parcela.toString());
        this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
      
        if (parcela != null && parcela.getIdParcela() != -1) {           
            this.getObjectListDataProvider2().setList(listaPropietarios);
            this.setListaDelCommunication(listaPropietarios);
            this.setRBSelected((new Long(0)).toString());
        }
    }

    private ObjectListDataProvider getObjectListDataProvider2() {

        return this.getLdpPropietarios();
    }

    private List getListaDelCommunication() {

        return this.getCommunicationHabilitacionesBean().getListaPropietarios();
    }

    private void setListaDelCommunication(List lista) {

        this.getCommunicationHabilitacionesBean().setListaPropietarios(lista);
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
        this.getObjectListDataProvider2().setList(null);
        this.setListaDelCommunication(null);
        return retorno;
    }

    private String prepararCaducidad() {
        // redireccionar a pagina con mensaje de caducidad
        this.getRequestBean1().setCasoNavegacionPostCaducidad(CASO_NAV_POST_CADUCIDAD);
        return CASO_NAV_CADUCIDAD;
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpDocEspOSP();
    }

    private boolean ultimoElementoPilaDeSubSesion() {
        return this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
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

    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            rk = this.getObjectListDataProvider2().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }

    private int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
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
            int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
            this.getElementoPila().getObjetos().set(posicion, seleccionado);
        }
    }

    private int getCantidadObjetosUsados() {
        return this.getElementoPila().getObjetos().size();
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
    // </editor-fold>

    public String btnSeleccionarParcela_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 3;

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminParcela";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarParcela_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(3, this.getTfParcela());
            this.getObjectListDataProvider2().setList(null);
            this.setListaDelCommunication(null);
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
        int posicionObjetoSeleccionado = 4;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            retorno = "AgregarDomicilio";

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarDomicilioPostal_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(4, null);
            this.getStDomicilioPostal().setText(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarDomicilioSolicitante_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.guardarEstadoObjetosUsados();

            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            RowKey rk = null;
            try {
                rk = this.getSeleccionado();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider2().getObjects()[index];
                    RegistroPropietario locRegistroPropietario = (RegistroPropietario) obj;

                    Domicilio domicilio = (Domicilio) locRegistroPropietario.getPersona().getDomicilioPostal();
                    this.getElementoPila().getObjetos().set(4, domicilio);
                } else {
                    warn("Seleccione una Persona para obtener el Domicilio Postal.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();

            }

            // this.guardarEstadoObjetosUsados();
            //  this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnSeleccionarDomicilioParcela_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 4;

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(3, Parcela.class);
            if (parcela.getIdParcela() != -1) {
                Domicilio domicilio = parcela.getDomicilioParcelario();
                this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
            } else {
                warn("Seleccione una Parcela para obtener el Domicilio Parcelario.");
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
                UIComponent[] noFechas = new UIComponent[1];
                String[] nomFechas = new String[1];

                int pos = 0;
                noVacios[pos] = this.getTfParcela();
                nomNoVacios[pos++] = "Parcela";
                noVacios[pos] = this.getTfFechaInicio();
                nomNoVacios[pos++] = "Fecha Inicio";

                pos = 0;
                noFechas[pos] = this.getTfFechaInicio();
                nomFechas[pos++] = "Fecha Inicio";

                v.noSonVacios(noVacios, nomNoVacios);
                v.formatoFechaValido(noFechas, nomFechas);

                Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(4, Domicilio.class);

                if (domicilio.getLocalidad() == null) {
                    String msg = "Debe seleccionar un Domicilio Postal.";
                    v.getErrores().add(msg);
                }

                // CAMBIAR: Objeto a guardar
                Obligacion obligacion = (Obligacion) this.getElementoPila().getObjetos().get(0);
                DocumentoTGI documentoTGI = (DocumentoTGI) this.getElementoPila().getObjetos().get(1);

                if (!v.fechaNoMayorAFechaActual(documentoTGI.getFechaInicioActividad(), "Inicio de Actividades")) {
                    this.getTfFechaInicio().setValid(false);
                }

                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                Persona persona = (Persona) this.getElementoPila().getObjetos().get(2);
                if (obligacion.getPersona() == null) {
                    obligacion.setPersona(persona);
                }
                obligacion.setPersona(persona);
                //obligacion.setDocumentoEspecializado(documentoTGI);
                obligacion.setEstado(Obligacion.Estado.CREADO);
                documentoTGI.setEstado(DocHabilitanteEspecializado.Estado.ACTIVO);

                
                this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTGI().setLlave(this.getSessionBean1().getLlave());
                this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
//                this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().setLlave(this.getSessionBean1().getLlave());
//                this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTGI().setLlave(this.getSessionBean1().getLlave());
                
              //  obligacion.setDocumentoEspecializado(documentoTGI);
                this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(obligacion);
               
              documentoTGI = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTGI().updateDocumentoTGI(documentoTGI); 
          

                info("La Obligaci\363n de TGI se modific\363 exitosamente.");
                retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_DardeAltaError:", ex);
                error(NOMBRE_PAGINA + " - DardeAlta: " + ex.getMessage());
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
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
}
