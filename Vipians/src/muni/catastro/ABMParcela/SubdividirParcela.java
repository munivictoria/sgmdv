/*
 * SubdividirParcela.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender
 */
package muni.catastro.ABMParcela;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;

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
import com.sun.rave.web.ui.component.PageSeparator;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.PanelLayout;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class SubdividirParcela extends AbstractPageBean {

    /**
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
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
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    private TextField tfNroParcela = new TextField();

    public TextField getTfNroParcela() {
        return tfNroParcela;
    }

    public void setTfNroParcela(TextField tf) {
        this.tfNroParcela = tf;
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
    private TextField tfSuperficieSubdivision = new TextField();

    public TextField getTfSuperficieSubdivision() {
        return tfSuperficieSubdivision;
    }

    public void setTfSuperficieSubdivision(TextField tfSuperficieSubdivision) {
        this.tfSuperficieSubdivision = tfSuperficieSubdivision;
    }
    private TextField tfSuperficie = new TextField();

    public TextField getTfSuperficie() {
        return tfSuperficie;
    }

    public void setTfSuperficie(TextField tf) {
        this.tfSuperficie = tf;
    }
    private Button btnGuardar = new Button();
    private Button btnSeleccionarPersonaFisica = new Button();
    private Button btnSeleccionarPersonaJuridica = new Button();
    private HtmlAjaxCommandButton btnLimpiarTitular = new HtmlAjaxCommandButton();
    private Button btnAgregarSubdivision = new Button();
    private Button btnEliminarSubdivision = new Button();

    public HtmlAjaxCommandButton getBtnLimpiarTitular() {
		return btnLimpiarTitular;
	}

	public void setBtnLimpiarTitular(HtmlAjaxCommandButton btnLimpiarTitular) {
		this.btnLimpiarTitular = btnLimpiarTitular;
	}

	public Button getBtnAgregarSubdivision() {
        return btnAgregarSubdivision;
    }

    public void setBtnAgregarSubdivision(Button btnAgregarSubdivision) {
        this.btnAgregarSubdivision = btnAgregarSubdivision;
    }

    public Button getBtnEliminarSubdivision() {
        return btnEliminarSubdivision;
    }

    public void setBtnEliminarSubdivision(Button btnEliminarSubdivision) {
        this.btnEliminarSubdivision = btnEliminarSubdivision;
    }

    public Button getBtnSeleccionarPersonaFisica() {
        return btnSeleccionarPersonaFisica;
    }

    public void setBtnSeleccionarPersonaFisica(Button btnSeleccionarPersonaFisica) {
        this.btnSeleccionarPersonaFisica = btnSeleccionarPersonaFisica;
    }

    public Button getBtnSeleccionarPersonaJuridica() {
        return btnSeleccionarPersonaJuridica;
    }

    public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
        this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
    }

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
    private PageSeparator psLineaHorizontal = new PageSeparator();

    public PageSeparator getPsLineaHorizontal() {
        return psLineaHorizontal;
    }

    public void setPsLineaHorizontal(PageSeparator psLineaHorizontal) {
        this.psLineaHorizontal = psLineaHorizontal;
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

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Subdividir Parcela";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "SubdividirParcela";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private TextField tfNroPartidaProvincial = new TextField();

    public TextField getTfNroPartidaProvincial() {
        return tfNroPartidaProvincial;
    }

    public void setTfNroPartidaProvincial(TextField tf) {
        this.tfNroPartidaProvincial = tf;
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
    private ObjectListDataProvider ldpSubParcelas = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpSubParcelas() {
        return ldpSubParcelas;
    }

    public void setLdpSubParcelas(ObjectListDataProvider ldpSubParcelas) {
        this.ldpSubParcelas = ldpSubParcelas;
    }
    private TableColumn tcTitular = new TableColumn();
    private TableColumn tcSuperficie = new TableColumn();
    private TableColumn tcSup = new TableColumn();

    public TableColumn getTcSup() {
        return tcSup;
    }

    public void setTcSup(TableColumn tcSup) {
        this.tcSup = tcSup;
    }

    public TableColumn getTcSuperficie() {
        return tcSuperficie;
    }

    public void setTcSuperficie(TableColumn tcSuperficie) {
        this.tcSuperficie = tcSuperficie;
    }

    public TableColumn getTcTitular() {
        return tcTitular;
    }

    public void setTcTitular(TableColumn tcTitular) {
        this.tcTitular = tcTitular;
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
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tableColumn4) {
        this.tableColumn4 = tableColumn4;
    }

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }
    private TextField textField1 = new TextField();
    private TextField tfTitular = new TextField();

    public TextField getTfTitular() {
        return tfTitular;
    }

    public void setTfTitular(TextField tfTitular) {
        this.tfTitular = tfTitular;
    }
    private TextField tfTitularSubdivision = new TextField();

    public TextField getTfTitularSubdivision() {
        return tfTitularSubdivision;
    }

    public void setTfTitularSubdivision(TextField tfTitularSubdivision) {
        this.tfTitularSubdivision = tfTitularSubdivision;
    }

    public TextField getTextField1() {
        return textField1;
    }

    public void setTextField1(TextField tf) {
        this.textField1 = tf;
    }
    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }
    private Label label14 = new Label();

    public Label getLabel14() {
        return label14;
    }

    public void setLabel14(Label l) {
        this.label14 = l;
    }
    private Label label25 = new Label();

    public Label getLabel25() {
        return label25;
    }

    public void setLabel25(Label l) {
        this.label25 = l;
    }
    private Label label26 = new Label();

    public Label getLabel26() {
        return label26;
    }

    public void setLabel26(Label l) {
        this.label26 = l;
    }
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private StaticText staticText4 = new StaticText();
    private StaticText staticText5 = new StaticText();
    private StaticText staticText6 = new StaticText();
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText staticText7) {
        this.staticText7 = staticText7;
    }

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText staticText6) {
        this.staticText6 = staticText6;
    }

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText staticText5) {
        this.staticText5 = staticText5;
    }

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private PageSeparator pgSeparator = new PageSeparator();

    public PageSeparator getPgSeparator() {
        return pgSeparator;
    }

    public void setPgSeparator(PageSeparator pgSeparator) {
        this.pgSeparator = pgSeparator;
    }
    private PanelLayout pLayout = new PanelLayout();

    public PanelLayout getpLayout() {
        return pLayout;
    }

    public void setpLayout(PanelLayout pLayout) {
        this.pLayout = pLayout;
    }

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public SubdividirParcela() {
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
            log("SubdividirParcela Initialization Failure", e);
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
        ep.getObjetos().add(ind++, new Parcela());
        ep.getObjetos().add(ind++, null); //subparcela
        ep.getObjetos().add(ind++, new ArrayList()); //lista subdivisiones a agregar
        ep.getObjetos().add(ind++, null); //3 titular
        ep.getObjetos().add(ind++, null); // 4 bool para crear subparcela con el resto
        ep.getObjetos().add(ind++, null); // 5 lista subdivisiones ya agregadas

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        int ind = 0;
        Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        SubParcela subParcela = (SubParcela) this.obtenerObjetoDelElementoPila(ind++, SubParcela.class);
        ArrayList listaSubdivisiones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        Persona titular = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);

        Object superficie = this.getTfSuperficieSubdivision().getText();


        if (superficie != null && superficie != "") {
            subParcela.setSuperficie(Conversor.getDoubleDeString(superficie.toString()));
        } else {
            subParcela.setSuperficie(null);
        }

        if (titular != null && titular.getIdPersona() == -1) {
            titular = null;
        }

        this.getElementoPila().getObjetos().set(0, parcela);
        this.getElementoPila().getObjetos().set(1, subParcela);
        this.getElementoPila().getObjetos().set(2, listaSubdivisiones);
        this.getElementoPila().getObjetos().set(3, titular);
    }

    private void mostrarEstadoObjetosUsados() {
        int ind = 0;
        // CAMBIAR: Obtener una instancia por cada objeto manejado en la pagina
        Parcela parcela = null;
        SubParcela subParcela = null;
        List listaSubdivisiones = new ArrayList();
        Persona titular = null;

        if (this.getRequestBean1().getObjeto() != null) {
            Object objeto = this.getRequestBean1().getObjeto();
            if (objeto instanceof Parcela) {
                parcela = (Parcela) objeto;
                this.getElementoPila().getObjetos().set(0, parcela);
                //this.getElementoPila().getObjetos().set(2, listaSubdivisiones);
            }
        }
        
        if (this.getRequestBean1().getCommunication() != null){
            this.getElementoPila().getObjetos().set(4, false);
            this.getElementoPila().getObjetos().set(5, this.getRequestBean1().getCommunication());
            if (this.getRequestBean1().getCommunication().size() == 0){         
                this.getElementoPila().getObjetos().set(4, true);   
           }
        }

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.
            if (seleccionado instanceof Persona) {
                titular = (Persona) seleccionado;
                this.getElementoPila().getObjetos().set(3, titular);
                this.getRequestBean1().setObjetoSeleccion(null);
            }
        }

        ind = 0;
        parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        subParcela = (SubParcela) this.obtenerObjetoDelElementoPila(ind++, SubParcela.class);
        listaSubdivisiones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        titular = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);

        if (titular != null) {
            this.getTfTitularSubdivision().setText(titular.toString());
        }
        this.getTfSuperficieSubdivision().setText(subParcela.getSuperficie());

        this.setListaDelCommunication(new ArrayList(listaSubdivisiones));
        this.getLdpSubParcelas().setList(this.getListaDelCommunication());

        //this.getComunicationCatastroBean().setListaSubdivisiones(new ArrayList(listaSubdivisiones));
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpSubParcelas();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationCatastroBean().getListaSubParcelas();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationCatastroBean().setListaSubParcelas(lista);
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
        }
        return retorno;
    }

    private String prepararCaducidad() {
        // redireccionar a pagina con mensaje de caducacion
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

            if (objeto instanceof SubParcela) {
            } else if (objeto == null) {
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

    private void limpiarTabla() {
        this.getObjectListDataProvider().getList().clear();
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

    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar el RaddioButton Principal">
    public String getPrincipalRow() {
        return tableRowGroup1.getRowKey().getRowId();
    }

    public void setPrincipalRow(int row) {
    }
    private Object lastPrincipalSelected = null;

    public Object getRBPrincipalSelected() {
        //String sv = (String)radioButton2.getSelectedValue();
        //return sv.equals(lastPrincipalSelected) ? sv : null;
        return null;
    }

    public void setRBPrincipalSelected(Object selected) {
        if (selected != null) {
            lastPrincipalSelected = selected;
        }
    }

    public RowKey getPrincipalSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            // CAMBIAR: Utilizar el ListDataProvider correspondiente
            rk = this.getLdpSubParcelas().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }

    private Object obtenerPrincipalSeleccionado() {
        RowKey rk = null;
        Object ppal = null;
        try {
            rk = this.getPrincipalSeleccionado();
            if (rk != null) {
                int index = getNroFila(rk.toString());
                // CAMBIAR: Utilizar el ListDataProvider adecuado.
                ppal = this.getLdpSubParcelas().getObjects()[index];
            }
        } catch (Exception ex) {
        }
        return ppal;
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
            if (posicionGlobal.longValue() > cantFilas) {
                // si elimine la ultima fila, me posiciono en la anterior
                posicionGlobal = new Long(cantFilas);
            };

            int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
            this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
            lastSelected = new Long(index).toString();
        }
    }

    public void seleccionarFilaPrincipal(Object principal) {
        // CAMBIAR: Reemplazar por los objetos utilizados en la pagina.
        int cantFilas = this.getLdpSubParcelas().getList().size();
        Rol enTabla = null;
        for (int i = 0; i < cantFilas; i++) {
            enTabla = (Rol) this.getLdpSubParcelas().getObjects()[i];
            if (enTabla.getIdRol() == ((Rol) principal).getIdRol()) {
                lastPrincipalSelected = new Long(i).toString();
            }
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
            rk = this.getLdpSubParcelas().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    // </editor-fold>

    public String btnSeleccionarPersonaFisica_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
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

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPersonaJuridica";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarTitular_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.getTfSuperficieSubdivision().setText(null);
            this.limpiarObjeto(3, this.getTfTitularSubdivision());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    private void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        SubParcela subParcela = (SubParcela) this.obtenerObjetoDelElementoPila(1, SubParcela.class);
        this.getTfSuperficieSubdivision().setText(null);
        subParcela.setSuperficie(null);
        
        this.limpiarObjeto(3, this.getTfTitularSubdivision());
        
//        this.getElementoPila().getObjetos().set(1, subParcela);
        this.getElementoPila().getObjetos().set(3, null);
    }

    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                this.guardarEstadoObjetosUsados();            
                List listaSubdivisiones = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
           
                if (listaSubdivisiones != null && listaSubdivisiones.size() == 0){
                    warn("No hay subivisiones para Agregar.");
                    return null;
                }
                this.getRequestBean1().setObjetosSeleccionMultiple((ArrayList) listaSubdivisiones);
                retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_GuardarError:", ex);
                error(NOMBRE_PAGINA + " - Guardar: " + ex.getMessage());
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnAgregarSubdivision_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        SubParcela newSubParcela = null;

        if (ultimo) {
            try {
                this.guardarEstadoObjetosUsados();
                Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(0, Parcela.class);
                SubParcela subParcela = (SubParcela) this.obtenerObjetoDelElementoPila(1, SubParcela.class);
                List listaSubdivisiones = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
                Persona titular = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
                List listaSubdivisionesAgregadas = (List) this.obtenerObjetoDelElementoPila(5, ArrayList.class);
                
                /*Saco el total de superficie de las sub agregadas y las por agregar*/
                Double sup = 0.0;
                for (Object obj : listaSubdivisionesAgregadas) {
                    SubParcela s = (SubParcela) obj;
                    sup = sup + s.getSuperficie();                    
                }
                
                for (Object object : listaSubdivisiones) {
                    SubParcela s = (SubParcela) object;
                    sup = sup + s.getSuperficie();       
                }                
                
                if ((sup + subParcela.getSuperficie())  > parcela.getSuperficie()){
                    warn("La suma de la superficie de las subdivisiones no puede superar la superficie total de la Parcela.");
                    return null;                    
                }
                
                if (titular != null && subParcela.getSuperficie() != null && subParcela.getSuperficie() != 0) {

                    newSubParcela = new SubParcela(parcela, titular);
                    newSubParcela.setPadre(parcela);
                    newSubParcela.setSuperficie(subParcela.getSuperficie());
                    listaSubdivisiones.add(newSubParcela);

                    this.setListaDelCommunication(new ArrayList(listaSubdivisiones));
                    this.getLdpSubParcelas().setList(this.getListaDelCommunication());

                    this.getElementoPila().getObjetos().set(2, listaSubdivisiones);
                    this.btnLimpiarTitular_action();

                } else {
                    warn("Ingrese titular y superficie para realizar la subdivisi\363n.");
                    return null;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnEliminarSubdivision_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getLdpSubParcelas().getObjects()[index];
                    SubParcela subParcela = (SubParcela) obj;
                    List listaSubdivisiones = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

                    this.getListaDelCommunication().remove(subParcela);
                    this.getObjectListDataProvider().setList(this.getListaDelCommunication());

                    listaSubdivisiones.remove(subParcela);
                    this.getElementoPila().getObjetos().set(2, listaSubdivisiones);

                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_EliminarSubparcelaError:", ex);
                error(NOMBRE_PAGINA + " - Eliminar Subparcela: " + ex.getMessage());
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
}
