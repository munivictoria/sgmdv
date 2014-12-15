/*
 * AdminValorMedidor.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.saic.grpTasaMenor.ABMRegistroValuadoTasaMenor;

import java.util.ArrayList;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
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
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;

//comment
/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminRegistroValuadoTasaMenor extends AbstractPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;
    
    private PanelGroup pgParametros = new PanelGroup();

    public PanelGroup getPgParametros() {
		return pgParametros;
	}

	public void setPgParametros(PanelGroup pgParametros) {
		this.pgParametros = pgParametros;
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
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Registros Valuados de Tasas Menores";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminRegistroValuadoTasaMenor";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarRegistroValuadoTasaMenor";
    private final String lnkModificar = "ModificarRegistroValuadoTasaMenor";
    private final String lnkConsultar = "ConsultarRegistroValuadoTasaMenor";
    private final String lnkEliminar = null;
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

        dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        dateTimeConverter1.setTimeStyle("short");

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
        
        this.habilitarBtnExportar();        
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
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private PanelGroup gpBotones = new PanelGroup();

    public PanelGroup getGpBotones() {
        return gpBotones;
    }

    public void setGpBotones(PanelGroup pg) {
        this.gpBotones = pg;
    }
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
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
    //----------------------------Button---------------------------------------
    private Button btnBuscar = new Button();
    private Button btnCancelar = new Button();
    private Button btnEliminar = new Button();
    private Button btnModificar = new Button();
    private Button btnAgregar = new Button();
    private Button btnSeleccionar = new Button();
    private Button btnReiniciar = new Button();
    private Button btnSeleccionarTipoObligacion = new Button();
    private HtmlAjaxCommandButton btnLimpiarTipoObligacion = new HtmlAjaxCommandButton();
    private Button btnSeleccionarPersonaFisica = new Button();
    private Button btnSeleccionarPersonaJuridica = new Button();
    private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
    private Button btnExportar = new Button();
    private Button btnConsultar = new Button();

    public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public HtmlAjaxCommandButton getBtnLimpiarTipoObligacion() {
		return btnLimpiarTipoObligacion;
	}

	public void setBtnLimpiarTipoObligacion(HtmlAjaxCommandButton btnLimpiarTipoObligacion) {
		this.btnLimpiarTipoObligacion = btnLimpiarTipoObligacion;
	}

	public Button getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(Button btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    public Button getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(Button btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public Button getBtnConsultar() {
        return btnConsultar;
    }

    public void setBtnConsultar(Button btnConsultar) {
        this.btnConsultar = btnConsultar;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Button btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public Button getBtnExportar() {
        return btnExportar;
    }

    public void setBtnExportar(Button btnExportar) {
        this.btnExportar = btnExportar;
    }

    public Button getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(Button btnModificar) {
        this.btnModificar = btnModificar;
    }

    public Button getBtnReiniciar() {
        return btnReiniciar;
    }

    public void setBtnReiniciar(Button btnReiniciar) {
        this.btnReiniciar = btnReiniciar;
    }

    public Button getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public void setBtnSeleccionar(Button btnSeleccionar) {
        this.btnSeleccionar = btnSeleccionar;
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

    public Button getBtnSeleccionarTipoObligacion() {
        return btnSeleccionarTipoObligacion;
    }

    public void setBtnSeleccionarTipoObligacion(Button btnSeleccionarTipoObligacion) {
        this.btnSeleccionarTipoObligacion = btnSeleccionarTipoObligacion;
    }
    //-------------------------------------------------------------------------
    //----------------------------DropDown-------------------------------------
    private DropDown ddCalendarios = new DropDown();
    private DropDown ddPeriodos = new DropDown();
    private DropDown ddCuotas = new DropDown();

    public DropDown getDdCalendarios() {
        return ddCalendarios;
    }

    public void setDdCalendarios(DropDown ddCalendarios) {
        this.ddCalendarios = ddCalendarios;
    }

    public DropDown getDdCuotas() {
        return ddCuotas;
    }

    public void setDdCuotas(DropDown ddCuotas) {
        this.ddCuotas = ddCuotas;
    }

    public DropDown getDdPeriodos() {
        return ddPeriodos;
    }

    public void setDdPeriodos(DropDown ddPeriodos) {
        this.ddPeriodos = ddPeriodos;
    }
    
    
    //---------------SingleSelectedOptionLists---------------
    private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdCalendariosOptions() {
        return ddCalendariosOptions;
    }

    public void setDdCalendariosOptions(SingleSelectOptionsList ddCalendariosOptions) {
        this.ddCalendariosOptions = ddCalendariosOptions;
    }

    public SingleSelectOptionsList getDdCuotasOptions() {
        return ddCuotasOptions;
    }

    public void setDdCuotasOptions(SingleSelectOptionsList ddCuotasOptions) {
        this.ddCuotasOptions = ddCuotasOptions;
    }

    public SingleSelectOptionsList getDdPeriodosOptions() {
        return ddPeriodosOptions;
    }

    public void setDdPeriodosOptions(SingleSelectOptionsList ddPeriodosOptions) {
        this.ddPeriodosOptions = ddPeriodosOptions;
    }
    //-------------------------------------------------------------------------
    //----------------------------Label----------------------------------------
    private Label lblPersona = new Label();
    private Label label1 = new Label();
    private Label label2 = new Label();
    private Label label3 = new Label();
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

    public Label getLblPersona() {
        return lblPersona;
    }

    public void setLblPersona(Label lblPersona) {
        this.lblPersona = lblPersona;
    }

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label label1) {
        this.label1 = label1;
    }

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label label2) {
        this.label2 = label2;
    }

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label label3) {
        this.label3 = label3;
    }

    //-------------------------------------------------------------------------
    //--------------------------StaticText-------------------------------------
    private StaticText stTitulo = new StaticText();
    private StaticText staticText1 = new StaticText();
    private StaticText staticText2 = new StaticText();
    private StaticText staticText3 = new StaticText();
    private StaticText staticText4 = new StaticText();
    private StaticText staticText5 = new StaticText();
    private StaticText staticText6 = new StaticText();
    private StaticText staticText7 = new StaticText();
    private StaticText staticText8 = new StaticText();
    private StaticText staticText9 = new StaticText();
    private StaticText staticText11 = new StaticText();
    private StaticText staticText12 = new StaticText();
    private StaticText staticText14 = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText stTitulo) {
        this.stTitulo = stTitulo;
    }

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText staticText1) {
        this.staticText1 = staticText1;
    }

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText staticText11) {
        this.staticText11 = staticText11;
    }

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText staticText12) {
        this.staticText12 = staticText12;
    }

    public StaticText getStaticText14() {
        return staticText14;
    }

    public void setStaticText14(StaticText staticText14) {
        this.staticText14 = staticText14;
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

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText staticText7) {
        this.staticText7 = staticText7;
    }

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
    //-------------------------------------------------------------------------        
    //--------------------------RadioButton------------------------------------
    private RadioButton radioButton1 = new RadioButton();

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton rb) {
        this.radioButton1 = rb;
    }
    //-------------------------------------------------------------------------
    //--------------------------TextField--------------------------------------
    private TextField tfPersona = new TextField();
    private TextField tfTipoObligacion = new TextField();

    public TextField getTfPersona() {
        return tfPersona;
    }

    public void setTfPersona(TextField tfPersona) {
        this.tfPersona = tfPersona;
    }

    public TextField getTfTipoObligacion() {
        return tfTipoObligacion;
    }

    public void setTfTipoObligacion(TextField tfTipoObligacion) {
        this.tfTipoObligacion = tfTipoObligacion;
    }
    //-------------------------------------------------------------------------
    //----------------------------Tabla----------------------------------------
    private Table table1 = new Table();
    private TableRowGroup tableRowGroup1 = new TableRowGroup();
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn2 = new TableColumn();
    private TableColumn tableColumn3 = new TableColumn();
    private TableColumn tableColumn4 = new TableColumn();
    private TableColumn tableColumn5 = new TableColumn();
    private TableColumn tableColumn6 = new TableColumn();
    private ObjectListDataProvider ldpRegistroValuado = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpRegistroValuado() {
        return ldpRegistroValuado;
    }

    public void setLdpRegistroValuado(ObjectListDataProvider ldpRegistroValuado) {
        this.ldpRegistroValuado = ldpRegistroValuado;
    }

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table table1) {
        this.table1 = table1;
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

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
        this.tableRowGroup1 = tableRowGroup1;
    }
    //-------------------------------------------------------------------------

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p?gina.</p>
     */
    public AdminRegistroValuadoTasaMenor() {
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
            log("AdminValorMedidor Initialization Failure", e);
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

                cargarValoresPorDefecto();
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
        ep.getObjetos().add(ind++, null);     // 0 Calendario
        ep.getObjetos().add(ind++, null);     // 1 Periodo
        ep.getObjetos().add(ind++, null);     // 2 Cuota
        ep.getObjetos().add(ind++, null);     // 3 Tipo Obligacion (plantillaDocumentoTasaMenor)
        ep.getObjetos().add(ind++, null);     // 4 Persona

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        
        CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
        
        PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor = (PlantillaDocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, PlantillaDocumentoTasaMenor.class);
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);

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
        this.getElementoPila().getObjetos().set(ind++, calendario);
        this.getElementoPila().getObjetos().set(ind++, periodoCalendario);
        this.getElementoPila().getObjetos().set(ind++, cuota);
        this.getElementoPila().getObjetos().set(ind++, plantillaDocumentoTasaMenor);
        this.getElementoPila().getObjetos().set(ind++, persona);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        
        CalendarioMunicipal calendario = null;
        PeriodoLiquidacion periodoCalendario = null;
        CuotaLiquidacion cuota = null;
        
        PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor = null;
        Persona persona = null;

//        if (this.getRequestBean1().getObjetoSeleccion() != null) {
//            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
//            int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
//
//            if (posicion == 0) {
//                this.getElementoPila().getObjetos().set(0, seleccionado);
//                this.limpiarTabla();
//            }
//
//            if (posicion == 4) {
//                if (((ServicioOSP) seleccionado).isMedido()) {
//                    this.getElementoPila().getObjetos().set(4, seleccionado);
//                    this.limpiarTabla();
//                } else {
//                    warn("Debe seleccionar un Servicio Medido.");
//                    //return null;
//                }
//            }
//        }

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            if (seleccionado instanceof PlantillaDocumentoTasaMenor) {
                plantillaDocumentoTasaMenor = (PlantillaDocumentoTasaMenor) seleccionado;
                this.getElementoPila().getObjetos().set(3, plantillaDocumentoTasaMenor);
                this.getRequestBean1().setObjetoSeleccion(null);
            }
            if (seleccionado instanceof Persona) {
                persona = (Persona) seleccionado;
                this.getElementoPila().getObjetos().set(4, persona);
                this.getRequestBean1().setObjetoSeleccion(null);
            }
        }

        int ind = 0;
        
        calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
        
        plantillaDocumentoTasaMenor = (PlantillaDocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, PlantillaDocumentoTasaMenor.class);
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);

        if (plantillaDocumentoTasaMenor != null) {
            this.getTfTipoObligacion().setText(plantillaDocumentoTasaMenor);
        }

        if (persona != null) {
            this.getTfPersona().setText(persona);
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
        
        if (this.getLdpRegistroValuado().getList() != null){
             Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
             System.out.println("filaSeleccionada :" +filaSeleccionada);
             this.seleccionarFila(filaSeleccionada);             
        }

    }

    private void refrescarTabla() throws Exception {
        // CAMBIAR: Segun objeto de busqueda.
        int ind = 0;
        
        CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
        
        PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor = (PlantillaDocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, PlantillaDocumentoTasaMenor.class);
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);

        if (persona == null || persona.getIdPersona() == -1) {
            persona = null;
        }

        if (plantillaDocumentoTasaMenor == null || plantillaDocumentoTasaMenor.getIdDocumentoTasaMenor() == -1) {
            plantillaDocumentoTasaMenor = null;
        }

        if(cuota != null && cuota.getIdPeriodo() != -1) {
            System.out.println("refrescarTabla() -> Periodo seleccionado");
        } else warn("Debe seleccionar un Per\355odo.");

        this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
        
        this.setListaDelCommunication((ArrayList) this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().findListaRegistrosValuadosTasaMenor(persona, cuota, periodoCalendario, calendario, plantillaDocumentoTasaMenor));
        
        this.getObjectListDataProvider().setList(this.getListaDelCommunication());
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
        this.getDdCalendarios().setSelected(null);
        this.getDdPeriodos().setSelected(null);
        this.getDdCuotas().setSelected(null);
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpRegistroValuado();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationSAICBean().getListaRegistroValuadoTasaMenor();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationSAICBean().setListaRegistroValuadoTasaMenor(lista);
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
        int posicionObjetoSeleccionado = 4;

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
                error(NOMBRE_PAGINA + " - Seleccionar Persona Fisica: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
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
        int posicionObjetoSeleccionado = 4;

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
                error(NOMBRE_PAGINA + " - Seleccionar Persona Juridica: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
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

    public String btnSeleccionarTipoObligacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 3;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                // CAMBIAR:
                log(CASO_NAVEGACION + "_SeleccionarTipoObligacionError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar Tipo de Obligacion: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPlantillaObligacionTasaMenor";
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

                CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(1, CalendarioMunicipal.class);
                PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(2, PeriodoLiquidacion.class);
                CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(3, CuotaLiquidacion.class);

                Object calendarioSeleccionado = this.getDdCalendarios().getSelected();
                Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
                Object cuotaSeleccionada = this.getDdCuotas().getSelected();

                if ((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0)) {
                    calendario = getCalendarioPorNombre(calendarioSeleccionado.toString());

                    if ((periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0)) {
                        periodoCalendario = getPeriodoPorNombre(calendario, periodoCalendarioSeleccionado.toString());

                        if ((cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)) {
                            cuota = this.getCuotaPorNombre(periodoCalendario, cuotaSeleccionada.toString());
                        } else {
                            cuota = null;
                        }
                    }
                }

                if (cuota != null && cuota.getIdPeriodo() != -1) {
                    System.out.println("btnBuscar_action() -> Periodo seleccionado");
                } else {
                    warn("Debe seleccionar un Per\355odo para realizar la busqueda.");
                    return null;
                }

                
//                Object servicioOSPTexto = this.getTfServicioOSP().getText();
//                if ( (servicioOSPTexto == null || servicioOSPTexto.toString().length()==0) ) {
//                    warn("Debe seleccionar un Servicio para realizar la b\372squeda.");
//                    return null;
//                }

                this.refrescarTabla();
                this.guardarOrdenamiento();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());

            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION + "_BuscarError:", ex);
                error(NOMBRE_PAGINA + " - Buscar: " + ex.getMessage());
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

    public String btnAgregar_action() {
        CalendarioMunicipal calendario = null;
        PeriodoLiquidacion periodoCalendario = null;
        CuotaLiquidacion cuota = null;
        
        PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor = null;
        Persona persona = null;

        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
//        Object calleTexto = null;
//        Object anio = null;
//        Object periodoNumero = null;
//        Object servicioOSPTexto = null;

//        Calle calle = null;
//        ServicioOSP servicioOSP = null;
//        String codigoMedidor = null;
        // Para pasar varios objetos a la p�gina de AgregarRegistroValuadoTasaMenor
        ArrayList objetosAPasar = new ArrayList();
        int ind = 0;

        if (ultimo) {

            // APLICAR LOGICA AQUI...
            this.guardarEstadoObjetosUsados();

//            calleTexto = this.getTfCalle().getText();
//
//            Periodicidad periodicidad = null;
//            servicioOSPTexto = this.getTfServicioOSP().getText();

//            Object periodicidadSelected = this.getDdPeriodicidad().getSelected();
//            if ((periodicidadSelected != null) && (periodicidadSelected.toString().length() > 0)) {
//                periodicidad = Periodicidad.valueOf(periodicidadSelected.toString());
//            } else {
//                periodicidad = null;
//            }

            calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
            periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
            cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
            plantillaDocumentoTasaMenor = (PlantillaDocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, PlantillaDocumentoTasaMenor.class);
            persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
            
            Object calendarioSeleccionado = this.getDdCalendarios().getSelected();
            Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
            Object cuotaSeleccionada = this.getDdCuotas().getSelected();

            if ((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0)) {
                calendario = getCalendarioPorNombre(calendarioSeleccionado.toString());

                if ((periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0)) {
                    periodoCalendario = getPeriodoPorNombre(calendario, periodoCalendarioSeleccionado.toString());

                    if ((cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)) {
                        cuota = this.getCuotaPorNombre(periodoCalendario, cuotaSeleccionada.toString());
                    } else {
                        cuota = null;
                    }
                }
            }

            if (cuota != null && cuota.getIdPeriodo() != -1) {
                System.out.println("btnBuscar_action() -> Periodo seleccionado");
            } else {
                warn("Debe seleccionar un Per\355odo para realizar la busqueda.");
                return null;
            }

            ind = 0;            
                    
            objetosAPasar.add(ind++, cuota);
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().setLlave(this.getSessionBean1().getLlave());
                plantillaDocumentoTasaMenor = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().getPlantillaDocumentoPorId(plantillaDocumentoTasaMenor.getIdDocumentoTasaMenor());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
            objetosAPasar.add(ind++, plantillaDocumentoTasaMenor);
            objetosAPasar.add(ind++, persona);

            this.getRequestBean1().setObjetoABM(objetosAPasar);
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

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];

//                    if (!(((ValorMedidor) obj).getEstado().equals(ValorMedidor.Estado.CARGADO))) {
//                        warn("S\363lo se pueden modificar las Mediciones de Medidor que est\351n en estado 'Cargado'.");
//                        return null;
//                    }

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
                retorno = lnkEliminar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    private void cargarValoresPorDefecto() {
        // CAMBIAR: Obtener los valores por defecto.

//        Periodicidad periodicidad = null;
//        periodicidad = Periodicidad.MENSUAL;
//
//        this.getElementoPila().getObjetos().set(0, new Integer(Calendar.getInstance().get(Calendar.YEAR)));          // anio (correspondiente a la fecha actual)
//        this.getElementoPila().getObjetos().set(1, periodicidad);       // Periodicidad
//        this.getElementoPila().getObjetos().set(2, new Integer(Calendar.getInstance().get(Calendar.MONTH) + 1));     // Numero de Periodo (correspondiente a la fecha actual)
        return;
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
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
//                    ValorMedidor locValorMedidor = (ValorMedidor) obj;
//                    locValorMedidor = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getValorMedidorPorId(locValorMedidor.getIdRegistroValuado());

//                    this.getRequestBean1().setObjetoABM(locValorMedidor);
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

    public String btnLimpiarPersona_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(4, this.getTfPersona());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarTipoObligacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(3, this.getTfTipoObligacion());
            this.guardarEstadoObjetosUsados();
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
                JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Valores de Medidores");

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_ValoresMedidores");
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
