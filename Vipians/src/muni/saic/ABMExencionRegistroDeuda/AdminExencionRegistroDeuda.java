/*
 * AdminExencion.java
 *
 * Copyright Trascender SRL
 * 
 */
package muni.saic.ABMExencionRegistroDeuda;

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
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.ImageComponent;
import com.sun.rave.web.ui.component.Script;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroExencionRegistroDeuda;
import jasper.ConstantesReportes;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ValueChangeEvent;
import muni.CommunicationMesaEntradaBean;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;
import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class AdminExencionRegistroDeuda extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Administraci\363n de Exenciones de Registro de Deuda";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminExencionRegistroDeuda";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkConsultar = "ConsultarExencionRegistroDeuda";
    private final String lnkAgregar = "AgregarExencionRegistroDeuda";
    private final String lnkModificar = "ModificarExencionRegistroDeuda";
    private final String lnkEliminar = "EliminarExencionRegistroDeuda";
    private final String lnkAutorizar = "AutorizarExencionRegistroDeuda";
    //private final String lnkReliquidar = "GenerarExencion";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        
        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(ExencionRegistroDeuda.Estado.values(), "cap");
        ddEstadoDefaultOptions.setOptions(op);
        
        
        
        Set<String> locListaCalendarios = this.getCommunicationSAICBean().getMapaCalendarios().keySet();
        
        Option[] opCalendarios = new Option[locListaCalendarios.size() + 1];
        int i = 0;
        opCalendarios[i++] = new Option("", "");
        for (String cadaCalendario : locListaCalendarios){
            opCalendarios[i++] = new Option(cadaCalendario, cadaCalendario);
        }     
        ddCalendariosOptions.setOptions(opCalendarios);

        this.ddPeriodosOptions.setOptions(new Option[0]);
        this.ddCuotasOptions.setOptions(new Option[0]);
        
        
        
        
        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        /*VER si esto sirve: */
        //numberConverter1.setPattern("%");
        dateTimeConverter1.setTimeStyle("short");
        dateTimeConverter1.setPattern("dd/MM/yyyy");

        this.habilitarBtnExportar();
    }
    protected PanelGroup pgParametros = new PanelGroup();

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
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
    
    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }
    
    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }
    private StaticText stTitulo = new StaticText();
    
    public StaticText getStTitulo() {
        return stTitulo;
    }
    
    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }
    private StaticText stNombre = new StaticText();
    
    public StaticText getStNombre() {
        return stNombre;
    }
    
    public void setStNombre(StaticText st) {
        this.stNombre = st;
    }
    private Label lblNombre = new Label();
    
    public Label getLblNombre() {
        return lblNombre;
    }
    
    public void setLblNombre(Label l) {
        this.lblNombre = l;
    }
    private TextField tfNombre = new TextField();
    
    public TextField getTfNombre() {
        return tfNombre;
    }
    
    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    private Label lblPeriodo = new Label();
    
    public Label getLblPeriodo() {
        return lblPeriodo;
    }
    
    public void setLblPeriodo(Label l) {
        this.lblPeriodo = l;
    }

    private Label lblEstado = new Label();
    
    public Label getLblEstado() {
        return lblEstado;
    }
    
    public void setLblEstado(Label l) {
        this.lblEstado = l;
    }
    private DropDown ddEstado = new DropDown();
    
    public DropDown getDdEstado() {
        return ddEstado;
    }
    
    public void setDdEstado(DropDown l) {
        this.ddEstado = l;
    }
    private Label label3 = new Label();
    
    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    
    private Label lblCalendarios = new Label();
    private Label lblPeriodos = new Label();
    private Label lblCuotas = new Label();

    public Label getLblCalendarios() {
        return lblCalendarios;
    }

    public void setLblCalendarios(Label lblCalendarios) {
        this.lblCalendarios = lblCalendarios;
    }

    public Label getLblCuotas() {
        return lblCuotas;
    }

    public void setLblCuotas(Label lblCuotas) {
        this.lblCuotas = lblCuotas;
    }

    public Label getLblPeriodos() {
        return lblPeriodos;
    }

    public void setLblPeriodos(Label lblPeriodos) {
        this.lblPeriodos = lblPeriodos;
    }

    private DropDown ddCalendarios = new DropDown();
    private DropDown ddPeriodos = new DropDown();
    private DropDown ddCuotas = new DropDown();
    private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();

    public DropDown getDdCalendarios() {
        return ddCalendarios;
    }

    public void setDdCalendarios(DropDown ddCalendarios) {
        this.ddCalendarios = ddCalendarios;
    }

    public SingleSelectOptionsList getDdCalendariosOptions() {
        return ddCalendariosOptions;
    }

    public void setDdCalendariosOptions(SingleSelectOptionsList ddCalendariosOptions) {
        this.ddCalendariosOptions = ddCalendariosOptions;
    }

    public DropDown getDdCuotas() {
        return ddCuotas;
    }

    public void setDdCuotas(DropDown ddCuotas) {
        this.ddCuotas = ddCuotas;
    }

    public SingleSelectOptionsList getDdCuotasOptions() {
        return ddCuotasOptions;
    }

    public void setDdCuotasOptions(SingleSelectOptionsList ddCuotasOptions) {
        this.ddCuotasOptions = ddCuotasOptions;
    }

    public DropDown getDdPeriodos() {
        return ddPeriodos;
    }

    public void setDdPeriodos(DropDown ddPeriodos) {
        this.ddPeriodos = ddPeriodos;
    }

    public SingleSelectOptionsList getDdPeriodosOptions() {
        return ddPeriodosOptions;
    }

    public void setDdPeriodosOptions(SingleSelectOptionsList ddPeriodosOptions) {
        this.ddPeriodosOptions = ddPeriodosOptions;
    }

    private StaticText staticText5 = new StaticText();
    
    public StaticText getStaticText5() {
        return staticText5;
    }
    
    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
 protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(HtmlAjaxCommandButton btnBuscar) {
        this.btnBuscar = btnBuscar;
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
    private Button btnAgregar = new Button();
    
    public Button getBtnAgregar() {
        return btnAgregar;
    }
    
    public void setBtnAgregar(Button b) {
        this.btnAgregar = b;
    }
    private Button btnModificar = new Button();
    
    public Button getBtnModificar() {
        return btnModificar;
    }
    
    public void setBtnModificar(Button b) {
        this.btnModificar = b;
    }
    private Button btnTerminar = new Button();
    
    public Button getBtnTerminar() {
        return btnTerminar;
    }
    
    public void setBtnTerminar(Button b) {
        this.btnTerminar = b;
    }
    private Button btnAutorizar = new Button();
    
    public Button getBtnAutorizar() {
        return btnAutorizar;
    }
    
    public void setBtnAutorizar(Button b) {
        this.btnAutorizar = b;
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
    
    private Script scriptValidador = new Script();

    public Script getScriptValidador() {
        return scriptValidador;
    }

    public void setScriptValidador(Script scriptValidador) {
        this.scriptValidador = scriptValidador;
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
    private Button btnConsultar = new Button();
    
    public Button getBtnConsultar() {
        return btnConsultar;
    }
    
    public void setBtnConsultar(Button b) {
        this.btnConsultar = b;
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
    private StaticText staticText7 = new StaticText();
    
    public StaticText getStaticText7() {
        return staticText7;
    }
    
    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }
    private StaticText staticText8 = new StaticText();
    
    public StaticText getStaticText8() {
        return staticText8;
    }
    
    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
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
    
    private StaticText staticText9 = new StaticText();
    
    public StaticText getStaticText9() {
        return staticText9;
    }
    
    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }

    private ObjectListDataProvider ldpExencion = new ObjectListDataProvider();
    
    public ObjectListDataProvider getLdpExencion() {
        return ldpExencion;
    }
    
    public void setLdpExencion(ObjectListDataProvider oldp) {
        this.ldpExencion = oldp;
    }
    private TableColumn tableColumn1 = new TableColumn();
    
    public TableColumn getTableColumn1() {
        return tableColumn1;
    }
    
    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }
    private TableColumn tableColumn5 = new TableColumn();
    
    public TableColumn getTableColumn5() {
        return tableColumn5;
    }
    
    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }
    private TableColumn tcNombre = new TableColumn();
    
    public TableColumn getTcNombre() {
        return tcNombre;
    }

    public void setTcNombre(TableColumn tc) {
        this.tcNombre = tc;
    }
    private TableColumn tcPorcentaje = new TableColumn();
    
    public TableColumn getTcPorcentaje() {
        return tcPorcentaje;
    }

    public void setTcPorcentaje(TableColumn tc) {
        this.tcPorcentaje = tc;
    }
    private RadioButton radioButton1 = new RadioButton();
    
    public RadioButton getRadioButton1() {
        return radioButton1;
    }
    
    public void setRadioButton1(RadioButton rb) {
        this.radioButton1 = rb;
    }
    private TableColumn tcNumeroCuota = new TableColumn();
    
    public TableColumn getTcNumeroCuota() {
        return tcNumeroCuota;
    }
    
    public void setTcNumeroCuota(TableColumn tcNumeroCuota) {
        this.tcNumeroCuota = tcNumeroCuota;
    }
    private TableColumn tcPeriodo = new TableColumn();
    
    public TableColumn getTcPeriodo() {
        return tcPeriodo;
    }
    
    public void setTcPeriodo(TableColumn tc) {
        this.tcPeriodo = tc;
    }
    private StaticText stPorcentaje = new StaticText();
    
    public StaticText getStPorcentaje() {
        return stPorcentaje;
    }
    
    public void setStPorcentaje(StaticText stPorcentaje) {
        this.stPorcentaje = stPorcentaje;
    }
    private TableColumn tcEstado = new TableColumn();
    
    public TableColumn getTcEstado() {
        return tcEstado;
    }
    
    public void setTcEstado(TableColumn tc) {
        this.tcEstado = tc;
    }
    private StaticText stEstado = new StaticText();
    
    public StaticText getStEstado() {
        return stEstado;
    }
    
    public void setStEstado(StaticText stEstado) {
        this.stEstado = stEstado;
    }
    private StaticText stPeriodo = new StaticText();
    private StaticText stTipoDeuda = new StaticText();
    
    public StaticText getStTipoDeuda() {
        return stTipoDeuda;
    }
    
    public void setStTipoDeuda(StaticText stTipoDeuda) {
        this.stTipoDeuda = stTipoDeuda;
    }
    
    public StaticText getStPeriodo() {
        return stPeriodo;
    }
    
    public void setStPeriodo(StaticText st) {
        this.stPeriodo = st;
    }
    private PanelGroup gpBotones = new PanelGroup();
    
    public PanelGroup getGpBotones() {
        return gpBotones;
    }
    
    public void setGpBotones(PanelGroup pg) {
        this.gpBotones = pg;
    }
    private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
    
    public SingleSelectOptionsList getDdEstadoDefaultOptions() {
        return ddEstadoDefaultOptions;
    }
    
    public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
        this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
    }
 protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnReiniciar() {
        return btnReiniciar;
    }

    public void setBtnReiniciar(HtmlAjaxCommandButton btnReiniciar) {
        this.btnReiniciar = btnReiniciar;
    }
 
    private HtmlPanelGrid grpCargando = new HtmlPanelGrid();
    
    public HtmlPanelGrid getGrpCargando() {
        return grpCargando;
    }
    
    public void setGrpCargando(HtmlPanelGrid hpg) {
        this.grpCargando = hpg;
    }
    private ImageComponent image1 = new ImageComponent();
    
    public ImageComponent getImage1() {
        return image1;
    }
    
    public void setImage1(ImageComponent ic) {
        this.image1 = ic;
    }
    private StaticText staticText1 = new StaticText();
    
    public StaticText getStaticText1() {
        return staticText1;
    }
    
    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private PanelGroup gpExencion1 = new PanelGroup();
    
    public PanelGroup getGpExencion1() {
        return gpExencion1;
    }
    
    public void setGpExencion1(PanelGroup pg) {
        this.gpExencion1 = pg;
    }
    private StaticText stMonto = new StaticText();
    
    public StaticText getStMonto() {
        return stMonto;
    }
    
    public void setStMonto(StaticText st) {
        this.stMonto = st;
    }
    private NumberConverter numberConverter1 = new NumberConverter();
    
    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }
    
    public void setNumberConverter1(NumberConverter nc) {
        this.numberConverter1 = nc;
    }

    // </editor-fold>
    /** 
     * <p>Construir una instancia de bean de p?gina.</p>
     */
    public AdminExencionRegistroDeuda() {
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
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
        return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
    }

    /** 
     * <p>M?todo de devoluci?n de llamada al que se llama cuando se navega hasta esta p?gina,
     * ya sea directamente mediante un URL o de manera indirecta a trav?s de la navegaci?n de p?ginas.
     * Puede personalizar este m?todo para adquirir recursos que se necesitar?n
     * para los controladores de eventos y m?todos del proceso, sin tener en cuenta si esta
     * p?gina realiza procesamiento de devoluci?n de env?os.</p>
     * 
     * <p>Tenga en cuenta que si la petici?n actual es una devoluci?n de env?o, los valores
     * de propiedad de los componentes <strong>no</strong> representan ning?n
     * valor enviado con esta petici?n.  En su lugar, representan los
     * valores de propiedades que se guardaron para esta vista cuando se proces?.</p>
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
            log("AdminExencion Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci?n que debe finalizar
        // *despu?s* de que se inicien los componentes administrados
        // TODO - Agregar c?digo de inicio propio aqu?

    }

    /** 
     * <p>M?todo de devoluci?n de llamada al que se llama cuando el ?rbol de componentes se ha
     * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este m?todo
     * <strong>s?lo</strong> se llamar? en una petici?n de devoluci?n de env?o que
     * est? procesando el env?o de un formulario.  Puede personalizar este m?todo para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /** 
     * <p>M?todo de devoluci?n de llamada al que se llama justo antes del procesamiento.
     * <strong>S?lo</strong> se llamar? a este m?todo en la p?gina que
     * se procesa, no se llamar?, por ejemplo, en una p?gina que
     * ha procesado una devoluci?n de env?o y a continuaci?n ha navegado hasta otra p?gina.  Puede personalizar
     * este m?todo para asignar recursos necesarios para procesar
     * esta p?gina.</p>
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

                //cargarValoresPorDefecto();
            }
        }

        // 2. Recarga de la misma pagina por validacion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
            // no se hace nada por ahora
            recarga = true;
            // .. ver si es as? realmente..
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
        this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1()); /*VER si esto no tira error*/
        
        this.habilitarBtnExportar();
        
        
        
        if (this.getDdCalendarios().getSelected() != null && !this.getDdCalendarios().getSelected().toString().equals("")) {

            System.out.println("/*/* Calendario = tiene algo seleccionado = " + this.getDdCalendarios().getSelected() + ", se cargan periodos");
            CalendarioMunicipal locCalendarioSeleccionado = this.getCalendarioPorNombre(this.getDdCalendarios().getSelected().toString());

            cargarComboPeriodos(locCalendarioSeleccionado);     
            if (this.getDdPeriodos().getSelected() != null && !this.getDdPeriodos().getSelected().toString().equals("")) {
                System.out.println("/*/* Periodo = tiene algo seleccionado = " + this.getDdPeriodos().getSelected() + ", se cargan cuotas");
                String strPeriodo = this.getDdPeriodos().getSelected().toString();
                PeriodoLiquidacion locPeriodoCalendarioSeleccionado = this.getPeriodoPorNombre(locCalendarioSeleccionado, strPeriodo);

                cargarComboCuotas(locPeriodoCalendarioSeleccionado);                
            } else {
                System.out.println("/*/*/* Periodo = es distinto.. se pone en 0 cuotas");
                this.ddCuotasOptions.setOptions(new Option[0]);
            }
        } else {
            System.out.println("/*/*/* Calendario = es distinto.. se pone en 0 periodos y cuotas");
            this.ddPeriodosOptions.setOptions(new Option[0]);
            this.ddCuotasOptions.setOptions(new Option[0]);
        }

        System.out.println("//////////////// FIN prerender()"); 
    }

    /** 
     * <p>M?todo de devoluci?n de llamada al que se llama cuando se completa el procesamiento de
     * esta petici?n, si se llam? al m?todo <code>init()</code> (sin tener en cuenta
     * si se trata de la p?gina que se ha procesado o no).  Puede personalizar este
     * m?todo para liberar los recursos adquiridos en los m?todos <code>init()</code>,
     * <code>preprocess()</code> o <code>prerender()</code> (o
     * durante la ejecuci?n de un controlador de eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new ExencionRegistroDeuda());
        ep.getObjetos().add(ind++, new DigestoMunicipal());
        ep.getObjetos().add(ind++, null); //Calendarioa
        ep.getObjetos().add(ind++, null); //Periodo
        ep.getObjetos().add(ind++, null); //Cuota
        ep.getObjetos().add(ind++, null);// estado


        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        //  ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        ExencionRegistroDeuda locExencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(ind++, ExencionRegistroDeuda.class);
        DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
        
        CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
        
        Estado estadoExencion = null;
        
        Object nombre = this.getTfNombre().getText();
        Object estadoSelected = this.getDdEstado().getSelected();
        
        if (nombre != null && nombre != "") {
            locExencionRegistroDeuda.setNombre(nombre.toString());
        } else {
            locExencionRegistroDeuda.setNombre(null);
        }
        
        if ((estadoSelected != null) && (estadoSelected.toString().length() > 0)) {
            estadoExencion = Exencion.Estado.valueOf(estadoSelected.toString());
        } else {
            estadoExencion = null;
        }
        
        if (estadoExencion != null) {
            locExencionRegistroDeuda.setEstado(estadoExencion);
        } else {
            locExencionRegistroDeuda.setEstado(null);
        }
        
                
        Object calendarioSeleccionado = this.getDdCalendarios().getSelected();      
        Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
        Object cuotaSeleccionada = this.getDdCuotas().getSelected();
        
        if((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0)) {
            calendario = getCalendarioPorNombre(calendarioSeleccionado.toString());
        }
        else calendario = null;
        if((periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0)) {
            periodoCalendario = getPeriodoPorNombre(calendario, periodoCalendarioSeleccionado.toString());
        }
        else periodoCalendario = null;
        if((cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)) {
            cuota = this.getCuotaPorNombre(periodoCalendario, cuotaSeleccionada.toString());
        }
        else cuota = null;
        
        
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, locExencionRegistroDeuda);
        this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);
        this.getElementoPila().getObjetos().set(ind++, calendario);
        this.getElementoPila().getObjetos().set(ind++, periodoCalendario);
        this.getElementoPila().getObjetos().set(ind++, cuota);
        this.getElementoPila().getObjetos().set(ind++, locExencionRegistroDeuda.getEstado());
        
    }
    
    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        ExencionRegistroDeuda exencionRegistroDeuda = null;
        DigestoMunicipal digestoMunicipal = null;
        
        CalendarioMunicipal calendario = null;
        PeriodoLiquidacion periodoCalendario = null;
        CuotaLiquidacion cuota = null;  
        
        int ind = 0;
        exencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(ind++, ExencionRegistroDeuda.class);
        digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
        
        calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
        
        Estado estadoExencion = (Estado) this.obtenerObjetoDelElementoPila(ind++, Estado.class);
        
        this.getTfNombre().setText(exencionRegistroDeuda.getNombre());

        //  if (exencionRegistroDeuda.getEstado() != null){
        if (estadoExencion != null) {
            this.getDdEstado().setSelected(Util.getEnumNameFromString((exencionRegistroDeuda.getEstado().toString())));
            this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString((exencionRegistroDeuda.getEstado().toString())));
        }
        
        
        if(calendario != null && calendario.getIdCalendario() != -1) {
            this.getDdCalendarios().setSelected(calendario.toString());
        }
        if(periodoCalendario != null && periodoCalendario.getIdPeriodo() != -1) {
            this.getDdPeriodos().setSelected(periodoCalendario.toString());
        }
        if(cuota != null && cuota.getIdPeriodo() != -1) {
            this.getDdCuotas().setSelected(cuota.toString());
        }
        
        
        if (this.getLdpExencion().getList() != null){
             Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
             System.out.println("filaSeleccionada :" +filaSeleccionada);
             this.seleccionarFila(filaSeleccionada);             
        }
    }
    
    private void refrescarTabla() throws Exception {
        // CAMBIAR: Segun objeto de busqueda.
        int ind = 0;
        ExencionRegistroDeuda exencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(ind++, ExencionRegistroDeuda.class);
        DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
        
        CalendarioMunicipal locCalendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        PeriodoLiquidacion locPeriodo = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        CuotaLiquidacion locCuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
        
        if(locCalendario.getIdCalendario() == -1) {
            locCalendario = null;  
        }
        if(locPeriodo.getIdPeriodo() == -1) {
            locPeriodo = null;  
        }   
        if(locCuota.getIdPeriodo() == -1) {
            locCuota = null;  
        }

        try {
            this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
            
            this.setListaDelCommunication((ArrayList) this.getCommunicationSAICBean().getRemoteSystemExencion().findListaExencionesRegistrosDeuda(exencionRegistroDeuda.getNombre(), locCuota, locPeriodo, locCalendario, exencionRegistroDeuda.getEstado(), null));
           // if (this.getListaDelCommunication() != null) {
                for (Iterator it = this.getListaDelCommunication().iterator(); it.hasNext();) {
                    Object object = it.next();
                    ExencionRegistroDeuda exc = (ExencionRegistroDeuda) object;
                    RegistroExencionRegistroDeuda reg = null;
                    // reg.
                    System.out.println("ExencionRegistroDeuda" + exc.getNombre());
                }
           // }
            this.limpiarTabla();
                this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setRBSelected((new Long(0)).toString());
    }
    
    public void valueChangeEvent(ValueChangeEvent event) {
        System.out.println("**** INICIO evento calendario");
        if(this.getDdCalendarios().getSelected() == null || this.getDdCalendarios().getSelected().toString().equals("")){
            System.out.println("se borran periodos y cuotas...");
            this.ddPeriodosOptions.setOptions(new Option[0]);
            this.ddCuotasOptions.setOptions(new Option[0]);
        }else{
            CalendarioMunicipal locCalendarioSeleccionado = this.getCalendarioPorNombre(this.ddCalendarios.getSelected().toString());
            this.cargarComboPeriodos(locCalendarioSeleccionado);
            this.getDdPeriodos().setSelected(null);
        }      
        System.out.println("**** FIN evento calendario");
    }
    
    public void valueChangeEventDdPeriodos(ValueChangeEvent event) {
        System.out.println("**** INICIO evento periodo");
        if(this.getDdPeriodos().getSelected() == null || this.getDdPeriodos().getSelected().toString().equals("") ){
            System.out.println("se borran cuotas...");
            this.ddCuotasOptions.setOptions(new Option[0]);
        }else{
            CalendarioMunicipal locCalendarioSeleccionado = this.getCalendarioPorNombre(this.ddCalendarios.getSelected().toString());
            PeriodoLiquidacion locPeriodoSeleccionado = this.getPeriodoPorNombre(locCalendarioSeleccionado, this.ddPeriodos.getSelected().toString());
            this.cargarComboCuotas(locPeriodoSeleccionado);
            this.getDdCuotas().setSelected(null);
        }
        System.out.println("**** FIN evento periodo");
    }
    
    private CalendarioMunicipal getCalendarioPorNombre(String pCalendario){
        return this.getCommunicationSAICBean().getMapaCalendarios().get(pCalendario);
    }
    
    private PeriodoLiquidacion getPeriodoPorNombre(CalendarioMunicipal pCalendario, String pPeriodo){
        Set<Periodo> locListaPeriodos = pCalendario.getListaPeriodos();
        for(Periodo cadaPeriodo : locListaPeriodos)
        {
            if(cadaPeriodo.getNombre().equals(pPeriodo))
                return (PeriodoLiquidacion) cadaPeriodo;
        }
        return null;
    }
    
    private CuotaLiquidacion getCuotaPorNombre(PeriodoLiquidacion pPeriodo, String pCuota){
        Set<CuotaLiquidacion> locListaCuotas = pPeriodo.getListaCuotas();
        for(CuotaLiquidacion cadaCuota : locListaCuotas)
        {
            if(cadaCuota.getNombre().equals(pCuota))
                return cadaCuota;
        }
        return null;
    }
    
    private void cargarComboPeriodos(CalendarioMunicipal pCalendario){
        System.out.println("**** INICIO cargarComboPeriodos");
        Set<Periodo> locListaPeriodos = pCalendario.getListaPeriodos();
        
        Option[] opPeriodos = new Option[locListaPeriodos.size() + 1];
        System.out.println("/*/*/* La cantidad de periodos = " + locListaPeriodos.size());
        int i = 0;
        System.out.println("/*/*/* Todos los Periodos del calendario *"+ pCalendario + "* son: " + locListaPeriodos);
        opPeriodos[i++] = new Option("","");
        for (Periodo cadaPeriodo : locListaPeriodos){
            opPeriodos[i++] = new Option(cadaPeriodo.toString(), cadaPeriodo.toString());
        }
        this.ddPeriodosOptions.setOptions(opPeriodos);
        System.out.println("**** FIN cargarComboPeriodos");
    }
    
    private void cargarComboCuotas(PeriodoLiquidacion pPeriodo){
        System.out.println("**** INICIO cargarComboCuotas");
        Set<CuotaLiquidacion> locListaCuotas = pPeriodo.getListaCuotas();
        
        Option[] opCuotas = new Option[locListaCuotas.size() + 1];
        System.out.println("/*/*/* La cantidad de cuotas = " + locListaCuotas.size());
        int i = 0;
        System.out.println("/*/*/* Todos las Cuotas del periodo *"+ pPeriodo + "* son: " + locListaCuotas);
        opCuotas[i++] = new Option("","");
        for (CuotaLiquidacion cadaCuota : locListaCuotas){
            opCuotas[i++] = new Option(cadaCuota.toString(), cadaCuota.toString());
        }
        this.ddCuotasOptions.setOptions(opCuotas);
        System.out.println("**** FIN cargarComboCuotas");
    }
    
    private void limpiarObjetosUsados() {
        for (int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
            this.getElementoPila().getObjetos().set(i, null);
        }

        // CAMBIAR: Limpiar los textField y los dropDown
        this.getTfNombre().setText(null);
        this.getDdEstado().setSelected(null);
        this.getDdEstadoDefaultOptions().setSelectedValue(null);
        this.ddCalendarios.setSelected(null);
        this.ddPeriodos.setSelected(null);
        this.ddCuotas.setSelected(null);
    }
    
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpExencion();
    }
    
    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        //TODO: cambiar por Exencion
        return this.getCommunicationSAICBean().getListaExencion();
    }
    
    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        //TODO: cambiar para Exencion

        this.getCommunicationSAICBean().setListaExencion(lista);
        
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
            if (objeto == null
                    && !tipoClase.getName().equals(Boolean.class.getName())
                    && !tipoClase.getName().equals(Date.class.getName())
                    && !tipoClase.getName().equals(Integer.class.getName())) {
                objeto = tipoClase.newInstance();
            }
        } catch (Exception ex) {
        }
        return objeto;
    }

//    private void acomodarSeleccionado() {
//        Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
//        if (seleccionado != null) {
//            int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
//            this.getElementoPila().getObjetos().set(posicion, seleccionado);
//        }
//    }
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
    public String btnBuscar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            try {
                this.guardarEstadoObjetosUsados();
                
                this.refrescarTabla();
                this.guardarOrdenamiento();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());
                
            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION + "_BuscarError:", ex);
                error(NOMBRE_PAGINA + " - Buscar: " + ex.getMessage());
                ex.printStackTrace();
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
    
    public String btnCancelar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            if (this.getListaDelCommunication() != null) {
                this.getListaDelCommunication().clear();
            }
            
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
                    Object obj = this.getLdpExencion().getObjects()[index];
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
            
            retorno = lnkAgregar;
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }
    
    public String btnModificar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        ExencionRegistroDeuda locExencionRegistroDeuda = null;
        if (ultimo) {
            RowKey rk = null;
            
            try {
                rk = this.getSeleccionado();
                
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    
                    locExencionRegistroDeuda = (ExencionRegistroDeuda) obj;
                    
                    this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
                    locExencionRegistroDeuda = (ExencionRegistroDeuda) this.getCommunicationSAICBean().getRemoteSystemExencion().getExencionRegistroDeudaByID(new Long(locExencionRegistroDeuda.getIdExencion()));
                    
                    if (locExencionRegistroDeuda.getEstado().equals(ExencionRegistroDeuda.Estado.TERMINADA)) {
                        warn("Las exenciones en estado Terminada no pueden ser modificadas");
                        return null;
                    }
                    System.out.println("period cuota en el modif del admin: " + locExencionRegistroDeuda.getPeriodicidadCuotas());
                    this.getRequestBean1().setObjetoABM(locExencionRegistroDeuda);
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
                retorno = lnkModificar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }
    
    public String btnTerminar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        ExencionRegistroDeuda locExencionRegistroDeuda = null;
        if (ultimo) {
            RowKey rk = null;
            
            try {
                rk = this.getSeleccionado();
                
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getLdpExencion().getObjects()[index];
                    
                    locExencionRegistroDeuda = (ExencionRegistroDeuda) obj;
                    
                    if (locExencionRegistroDeuda.getEstado().equals(ExencionRegistroDeuda.Estado.TERMINADA)) {
                        warn("No se pueden terminar exenciones que se encuentren en Estado Terminada.");
                        return null;
                    }
                    this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
                    locExencionRegistroDeuda = (ExencionRegistroDeuda) this.getCommunicationSAICBean().getRemoteSystemExencion().getExencionRegistroDeudaByID(new Long(locExencionRegistroDeuda.getIdExencion()));
                    
                    this.getRequestBean1().setObjetoABM(locExencionRegistroDeuda);
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
                
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_TerminarError:", ex);
                error(NOMBRE_PAGINA + " - Terminar: " + ex.getMessage());
            }
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());
            
            if (rk != null) {
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
        ExencionRegistroDeuda locExencionRegistroDeuda = null;
        if (ultimo) {
            RowKey rk = null;
            
            try {
                
                rk = this.getSeleccionado();
                
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getLdpExencion().getObjects()[index];
                    
                    locExencionRegistroDeuda = (ExencionRegistroDeuda) obj;
                    
                    this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
                    locExencionRegistroDeuda = (ExencionRegistroDeuda) this.getCommunicationSAICBean().getRemoteSystemExencion().getExencionRegistroDeudaByID(new Long(locExencionRegistroDeuda.getIdExencion()));
                    
                    this.getRequestBean1().setObjetoABM(locExencionRegistroDeuda);
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
                retorno = lnkConsultar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public String btnAutorizar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        ExencionRegistroDeuda locExencionRegistroDeuda = null;
        if (ultimo) {
            RowKey rk = null;
            
            try {
                
                rk = this.getSeleccionado();
                
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getLdpExencion().getObjects()[index];
                    
                    locExencionRegistroDeuda = (ExencionRegistroDeuda) obj;
                    
                    this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
                    locExencionRegistroDeuda = (ExencionRegistroDeuda) this.getCommunicationSAICBean().getRemoteSystemExencion().getExencionRegistroDeudaByID(new Long(locExencionRegistroDeuda.getIdExencion()));
                    
                    this.getRequestBean1().setObjetoABM(locExencionRegistroDeuda);
                    this.setRowKeySeleccionado(this.getSeleccionado());
                    
                }
                
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_AutorizarError:", ex);
                error(NOMBRE_PAGINA + " - Autorizar: " + ex.getMessage());
            }
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());
            
            if (rk != null) {
                retorno = lnkAutorizar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public String btnImprimir_action() {
        // CAMBIAR:
        String retorno = null;/*
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        RegistroDeuda registroDeudaSeleccionado = null;
        if (ultimo) {
        RowKey rk = null;
        // APLICAR LOGICA AQUI...
        // ariel - no guardar. utilizar lo ya guardado (con resultado de la busqueda)
        //this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
        
        try {
        rk = this.getSeleccionado();
        if(rk != null){
        int index = getNroFila(rk.toString());
        
        Object obj = this.getObjectListDataProvider().getObjects()[index];
        getRequestBean1().setObjetoABM(obj);
        
        this.setRowKeySeleccionado(this.getSeleccionado());
        
        registroDeudaSeleccionado = (RegistroDeuda) obj;
        
        }
        //                // maru - acomodar cuando este listo
        this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
        this.getCommunicationSAICBean().getRemoteSystemImpresion().imprimirExencion(registroDeudaSeleccionado);
        
        } catch (Exception ex) {
        log(CASO_NAVEGACION + "_ImprimirExencion", ex);
        error(NOMBRE_PAGINA + " - Imprimir: " + ex.getMessage());
        }
        
        this.guardarOrdenamiento();
        Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
        this.getElementoPila().setPosicionGlobal(pos.longValue());
        
        } else {
        retorno = this.prepararCaducidad();
        }
         */ return retorno;
    }
    
//    private void cargarValoresPorDefecto() {
//        this.getElementoPila().getObjetos().set(4, new Integer(Calendar.getInstance().get(Calendar.YEAR)));
//        this.getElementoPila().getObjetos().set(5, Periodicidad.MENSUAL);
//        this.getElementoPila().getObjetos().set(6, new Integer(Calendar.getInstance().get(Calendar.MONTH) + 1));
//        return;
//    }
    
         public String btnExportar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Exenciones de Registros de Deudas");

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_ExencionesRegistrosDeudas");
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
