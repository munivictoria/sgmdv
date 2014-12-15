/*
 * AdminRefinanciacion.java
 *
 * Created on 04 de junio de 2007, 15.35
 * Copyright Trascender SRL
 */
package muni.excepciones.ABMRefinanciacion;

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
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.presentacion.navegacion.ElementoPila;
import java.util.ArrayList;
import javax.faces.FacesException;
import com.sun.rave.web.ui.component.TextArea;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.presentacion.conversores.Conversor;
import com.sun.rave.web.ui.component.Script;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import jasper.ConstantesReportes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.NumberConverter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;
import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminRefinanciacion extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Administraci\363n de Refinanciaciones";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminRefinanciacion";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarRefinanciacion";
    private final String lnkModificar = "ModificarRefinanciacion";
    private final String lnkEliminar = "EliminarRefinanciacion";
    private final String lnkConsultar = "ConsultarRefinanciacion";
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
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        numberConverter1.setPattern("$ #,##0.00");

        this.habilitarBtnExportar();
    }
   
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
    private StaticText stTitulo = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }

    private Button btnLimpiarPersona = new Button();

    public Button getBtnLimpiarPersona() {
        return btnLimpiarPersona;
    }

    public void setBtnLimpiarPersona(Button b) {
        this.btnLimpiarPersona = b;
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
    private NumberConverter numberConverter1 = new NumberConverter();

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter numberConverter1) {
        this.numberConverter1 = numberConverter1;
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
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private Button btnSeleccionar = new Button();

    public Button getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public void setBtnSeleccionar(Button b) {
        this.btnSeleccionar = b;
    }
    private ObjectListDataProvider ldpRefinanciacion = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpRefinanciacion() {
        return ldpRefinanciacion;
    }

    public void setLdpRefinanciacion(ObjectListDataProvider oldp) {
        this.ldpRefinanciacion = oldp;
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
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private TextField tfPersona = new TextField();

    public TextField getTfPersona() {
        return tfPersona;
    }

    public void setTfPersona(TextField tf) {
        this.tfPersona = tf;
    }

    private Button btnSeleccionarPersonaFisica = new Button();

    public Button getBtnSeleccionarPersonaFisica() {
        return btnSeleccionarPersonaFisica;
    }

    public void setBtnSeleccionarPersonaFisica(Button b) {
        this.btnSeleccionarPersonaFisica = b;
    }
    private TextArea textArea1 = new TextArea();

    public TextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(TextArea ta) {
        this.textArea1 = ta;
    }
    private PanelGroup gpBotones = new PanelGroup();

    public PanelGroup getGpBotones() {
        return gpBotones;
    }

    public void setGpBotones(PanelGroup pg) {
        this.gpBotones = pg;
    }
    private TableColumn tableColumn5 = new TableColumn();
    private TableColumn tableColumn6 = new TableColumn();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText staticText8) {
        this.staticText8 = staticText8;
    }

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tableColumn6) {
        this.tableColumn6 = tableColumn6;
    }

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
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
    private Button btnSeleccionarPersonaJuridica = new Button();

    public Button getBtnSeleccionarPersonaJuridica() {
        return btnSeleccionarPersonaJuridica;
    }

    public void setBtnSeleccionarPersonaJuridica(Button b) {
        this.btnSeleccionarPersonaJuridica = b;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    private Button btnModificar = new Button();

    public Button getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(Button b) {
        this.btnModificar = b;
    }
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }
    private Button btnGenerar = new Button();

    public Button getBtnGenerar() {
        return btnGenerar;
    }

    public void setBtnGenerar(Button b) {
        this.btnGenerar = b;
    }
    private Button btnEliminar = new Button();

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Button b) {
        this.btnEliminar = b;
    }

    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private TextField tfNumeroTramite = new TextField();

    public TextField getTfNumeroTramite() {
        return tfNumeroTramite;
    }

    public void setTfNumeroTramite(TextField tf) {
        this.tfNumeroTramite = tf;
    }
    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText staticText10) {
        this.staticText10 = staticText10;
    }
    private Button btnExportar = new Button();

    public Button getBtnExportar() {
        return btnExportar;
    }

    public void setBtnExportar(Button btnExportar) {
        this.btnExportar = btnExportar;
    }
    private StaticText staticText7 = new StaticText();
    private StaticText staticText8 = new StaticText();
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }
    private Button btnConsultar = new Button();

    public Button getBtnConsultar() {
        return btnConsultar;
    }

    public void setBtnConsultar(Button b) {
        this.btnConsultar = b;
    }
    // </editor-fold>

    /**
     * <p>Construir una instancia de bean de p?gina.</p>
     */
    public AdminRefinanciacion() {
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
     * <p>Devolver una referencia al bean de datos con ?mbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ?mbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ?mbito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ?mbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ?mbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
    }

    /**
     * <p>M?todo de devoluci?n de llamada al que se llama cuando se navega hasta
     * esta p?gina, ya sea directamente mediante un URL o de manera indirecta a
     * trav?s de la navegaci?n de p?ginas. Puede personalizar este m?todo para
     * adquirir recursos que se necesitar?n para los controladores de eventos y
     * m?todos del proceso, sin tener en cuenta si esta p?gina realiza
     * procesamiento de devoluci?n de env?os.</p>
     *
     * <p>Tenga en cuenta que si la petici?n actual es una devoluci?n de env?o,
     * los valores de propiedad de los componentes <strong>no</strong>
     * representan ning?n valor enviado con esta petici?n. En su lugar,
     * representan los valores de propiedades que se guardaron para esta vista
     * cuando se proces?.</p>
     */
    public void init() {
        // Realizar iniciaciones heredadas de la superclase
        super.init();
        // Realizar inicio de aplicaci?n que debe finalizar
        // *antes* de que se inicien los componentes administrados
        // TODO - Agregar c?digo de inicio propio aqu?

        // <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
        // Iniciar componentes administrados autom?ticamente
        // *Nota* - esta l?gica NO debe modificarse
        try {
            _init();
        } catch (Exception e) {
            log("AdminDocEspSHPS Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci?n que debe finalizar
        // *despu?s* de que se inicien los componentes administrados
        // TODO - Agregar c?digo de inicio propio aqu?

    }

    /**
     * <p>M?todo de devoluci?n de llamada al que se llama cuando el ?rbol de
     * componentes se ha restaurado, pero antes de que se produzca el
     * procesamiento de cualquier evento. Este m?todo <strong>s?lo</strong> se
     * llamar? en una petici?n de devoluci?n de env?o que est? procesando el
     * env?o de un formulario. Puede personalizar este m?todo para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /**
     * <p>M?todo de devoluci?n de llamada al que se llama justo antes del
     * procesamiento. <strong>S?lo</strong> se llamar? a este m?todo en la
     * p?gina que se procesa, no se llamar?, por ejemplo, en una p?gina que ha
     * procesado una devoluci?n de env?o y a continuaci?n ha navegado hasta otra
     * p?gina. Puede personalizar este m?todo para asignar recursos necesarios
     * para procesar esta p?gina.</p>
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
            // APLICAR LOGICA AQUI.. ver si es as? realmente..
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
     * <p>M?todo de devoluci?n de llamada al que se llama cuando se completa el
     * procesamiento de esta petici?n, si se llam? al m?todo
     * <code>init()</code> (sin tener en cuenta si se trata de la p?gina que se
     * ha procesado o no). Puede personalizar este m?todo para liberar los
     * recursos adquiridos en los m?todos
     * <code>init()</code>,
     * <code>preprocess()</code> o
     * <code>prerender()</code> (o durante la ejecuci?n de un controlador de
     * eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, null); // numero de tramite
        // ep.getObjetos().add(ind++, new DocumentoRefinanciacion()); //Documento de Refinanciacion
        //  ep.getObjetos().add(ind++, new HashSet()); // lista de registros de Deudas

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        int ind = 0;
        // CAMBIAR: Revisar el metodo completo.
        Integer numeroTramite = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
        Persona persona = this.getSessionBean1().getPersonaSeleccionada();
        //      DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);
        //      HashSet listaRegistroDeudas = (HashSet) this.obtenerObjetoDelElementoPila(ind++, HashSet.class);
        Object numTramite = this.getTfNumeroTramite().getText();

        if (numTramite != null && numTramite != "") {
//                Integer auxNumeroTramite = Conversor.getIntegerDeString(numTramite.toString());
//                Validador v = new Validador();
//                if(v.esEntero(numTramite.toString()) && v.esPositivo(numTramite.toString())){
            numeroTramite = new Integer(numTramite.toString());
//                }else{
//                    warn("El Número de Tramite no es válido");
//                }

        } else {
            numeroTramite = null;
        }

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, numeroTramite);;
        // this.getElementoPila().getObjetos().set(ind++, documentoRefinanciacion);
        //this.getElementoPila().getObjetos().set(ind++, listaRegistroDeudas);
    }

    private void mostrarEstadoObjetosUsados() {
        int ind = 0;
        // CAMBIAR: Revisar el metodo completo.
        Integer numeroTramite = null;
        Persona persona = null;

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            if (seleccionado instanceof Persona) {
                persona = (Persona) seleccionado;
                this.getSessionBean1().setPersonaSeleccionada(persona);
                this.limpiarTabla();
                this.getRequestBean1().setObjetoSeleccion(null);
            }
        }

        this.acomodarSeleccionado();

        numeroTramite = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
        persona = this.getSessionBean1().getPersonaSeleccionada();

        if (numeroTramite != null) {
            this.getTfNumeroTramite().setText(numeroTramite.toString());
        }
        if (persona != null) {
            this.getTfPersona().setText(persona.toString());
        }
        
        if (this.getLdpRefinanciacion().getList() != null){
             Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
             System.out.println("filaSeleccionada :" +filaSeleccionada);
             this.seleccionarFila(filaSeleccionada);             
        }
    }

    private void refrescarTabla() throws Exception {
        // CAMBIAR: Segun objeto de busqueda.
        int ind = 0;
        Integer numeroTramite = (Integer) this.obtenerObjetoDelElementoPila(ind++, Integer.class);
        Persona persona = this.getSessionBean1().getPersonaSeleccionada();
        Object numTramite = this.getTfNumeroTramite().getText();
        // CAMBIAR: Utilizar el EJBClient adecuado.
        // ariel - descomentar cuando este listo
        if(persona != null && persona.getIdPersona() == -1){
        	persona = null;
        }
        
        if(numTramite != null && !numTramite.equals("")){
        	numeroTramite = Integer.parseInt(numTramite.toString());
        } else{
        	numeroTramite = null;
        }
        
        this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
        this.setListaDelCommunication((ArrayList) this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaRefinanciaciones(persona, numeroTramite)); //EstadoCuentaContribuyente().getListaObligacionesContribuyente(numeroTramite, persona, null));
        this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        this.setRBSelected((new Long(0)).toString());
    }

    private void limpiarObjetosUsados() {
        for (int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
            this.getElementoPila().getObjetos().set(i, null);
        }
        
        this.getSessionBean1().setPersonaSeleccionada(null);
        // CAMBIAR: Limpiar los textField y los dropDown
        this.getTfNumeroTramite().setText(null);
        this.getTfPersona().setText(null);
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpRefinanciacion();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getListaRefinanciaciones();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationExcepcionesBean().setListaRefinanciaciones(lista);
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
            if (objeto == null && !tipoClase.getName().equals(Integer.class.getName())
                    && !tipoClase.getName().equals(Persona.class.getName())) {
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

    private void limpiarTabla() {
        this.getObjectListDataProvider().getList().clear();
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
            rk = this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    // </editor-fold>

    // </editor-fold>
    public String btnSeleccionarPersonaFisica_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                // CAMBIAR:
                log(CASO_NAVEGACION + "_SeleccionarPersonaFisicaError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar Persona F\355sica: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

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

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                // CAMBIAR:
                log(CASO_NAVEGACION + "_SeleccionarPersonaJuridicaError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar Persona Jur\355dica: " + ex.getMessage());
            }
            //Administraci\363n de Personas Jur\355dicas
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

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
            this.getTfPersona().setText("");
            this.getSessionBean1().setPersonaSeleccionada(null);
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
                this.guardarEstadoObjetosUsados();

                Object numeroTramite = this.getTfNumeroTramite().getText();
                Object persona = this.getTfPersona().getText();

                if ((numeroTramite == null || numeroTramite.toString().length() == 0)
                        && (persona == null || persona.toString().length() == 0)) {
                    warn("Debe completar al menos uno de los campos para realizar la b\372squeda.");
                    return null;
                }
                try {
                    Object numTramite = this.getTfNumeroTramite().getText();

                    if (numTramite != null && numTramite != "") {
                        Integer auxNumeroTramite = Conversor.getIntegerDeString(numTramite.toString());
                        Validador v = new Validador();
                        if (v.esEntero(numTramite.toString()) && v.esPositivo(numTramite.toString())) {
                            numeroTramite = new Long(auxNumeroTramite.longValue());
                        } else {
                            warn("El N\372mero de Tramite no es v\341lido.");
                        }

                    } else {
                        numeroTramite = null;
                    }
                } catch (Exception ex) {
                    log(CASO_NAVEGACION + "_SeleccionarPersonaFisicaError:", ex);
                    error(NOMBRE_PAGINA + " - Seleccionar Persona F\355sica: " + ex.getMessage());
                }

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

    public String btnReiniciar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                this.limpiarObjetosUsados();
                this.limpiarTabla();
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

    public String btnSeleccionar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    getRequestBean1().setObjetoSeleccion(obj);
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_SeleccionarError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
            }

            if (rk != null) {
                ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
                if (locElementoAnterior == null) {
                    return null;
                }
                retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnGenerar_action() {
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
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    getRequestBean1().setObjetoABM(obj);

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
                    DocumentoRefinanciacion obj = (DocumentoRefinanciacion) this.getObjectListDataProvider().getObjects()[index];
                    obj = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getDocumentoRefinanciacion(obj.getIdDocGeneradorDeuda());
                    getRequestBean1().setObjetoABM(obj);

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
                retorno = "ConsultarRefinanciacion";
            }
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
                JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Refinanciaciones");

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Refinanciaciones");
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
