/*
 * AgregarExencion.java
 *
 * Copyright Trascender
 */
package muni.saic.ABMExencionRegistroDeuda;

import java.util.ArrayList;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
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
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroExencionRegistroDeuda;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class AgregarExencionRegistroDeuda extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
         if (this.getListaDelCommunication()!=null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }

        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Periodicidad.values(),"cap");

        ddPeriodicidadCuotaDefaultOptions.setOptions(op);
        numberConverter1.setPattern("$ #,##0.00");
        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern("dd/MM/yyyy");
        dateTimeConverter1.setTimeStyle("full");
        
        Set<String> locListaCalendarios = this.getCommunicationSAICBean().getMapaCalendarios().keySet();
        
        Option[] opCalendarios = new Option[locListaCalendarios.size() + 1];
        int i = 0;
        opCalendarios[i++] = new Option("", "");
        for (String cadaCalendario : locListaCalendarios){
            opCalendarios[i++] = new Option(cadaCalendario, cadaCalendario);
        }     
        ddCalendariosOptions.setOptions(opCalendarios);

        Option[] opPeriodos = new Option[0];
        ddPeriodosOptions.setOptions(opPeriodos);
        
        Option[] opCuotas = new Option[0];
        ddCuotasOptions.setOptions(opCuotas);

        System.out.println("//////////////// FIN INIT()   ////////////////////////////");
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

    private NumberConverter numberConverter1 = new NumberConverter();

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter numberConverter1) {
        this.numberConverter1 = numberConverter1;
    }

    private StaticText stTitulo = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }

    private StaticText stObligacion = new StaticText();

    public StaticText getStObligacion() {
        return stObligacion;
    }

    public void setStObligacion(StaticText st) {
        this.stObligacion = st;
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

    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }
    public void setLabel7(Label l) {
        this.label7 = l;
    }

    private Label label8 = new Label();

    public Label getLabel8() {
        return label8;
    }
    public void setLabel8(Label l) {
        this.label8 = l;
    }

    private Label lblPorcentaje = new Label();

    public Label getLblPorcentaje() {
        return lblPorcentaje;
    }

    public void setLblPorcentaje(Label l) {
        this.lblPorcentaje = l;
    }

    private TextField tfPorcentaje = new TextField();

    public TextField getTfPorcentaje() {
        return tfPorcentaje;
    }

    public void setTfPorcentaje(TextField tf) {
        this.tfPorcentaje = tf;
    }

    private Label lblMotivo = new Label();

    public Label getLblMotivo() {
        return lblMotivo;
    }

    public void setLblMotivo(Label l) {
        this.lblMotivo = l;
    }

    private TextArea taMotivo = new TextArea();

    public TextArea getTaMotivo() {
        return taMotivo;
    }

    public void setTaMotivo(TextArea ta) {
        this.taMotivo = ta;
    }

    private Label lblDigestoMunicipal = new Label();

    public Label getLblDigestoMunicipal() {
        return lblDigestoMunicipal;
    }

    public void setLblDigestoMunicipal(Label l) {
        this.lblDigestoMunicipal = l;
    }

    private TextField tfDigestoMunicipal = new TextField();

    public TextField getTfDigestoMunicipal() {
        return tfDigestoMunicipal;
    }

    public void setTfDigestoMunicipal(TextField tf) {
        this.tfDigestoMunicipal = tf;
    }

    private Button btnSeleccionarDigesto = new Button();

    public Button getBtnSeleccionarDigesto() {
        return btnSeleccionarDigesto;
    }

    public void setBtnSeleccionarDigesto(Button b) {
        this.btnSeleccionarDigesto = b;
    }

    private HtmlAjaxCommandButton btnLimpiarDigesto = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarDigesto() {
		return btnLimpiarDigesto;
	}

	public void setBtnLimpiarDigesto(HtmlAjaxCommandButton btnLimpiarDigesto) {
		this.btnLimpiarDigesto = btnLimpiarDigesto;
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
    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }

    private TableColumn tcObligacion = new TableColumn();

    public TableColumn getTcObligacion () {
        return tcObligacion ;
    }

    public void setTcObligacion (TableColumn tc) {
        this.tcObligacion  = tc;
    }
    public String getCurrentRow3() {
        return tableRowGroup1.getRowKey().getRowId();
    }
    public void setCurrentRow3(int row) {
    }

    private Object lastSelected = null;

    public Object getRBSelected() {
        String sv = (String)radioButton1.getSelectedValue();
        return sv.equals(lastSelected) ? sv : null;
    }
    public void setRBSelected(Object selected){
        if (selected != null) {
            lastSelected = selected;
        }
    }
    private ObjectListDataProvider ldpExencion = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpExencion() {
        return ldpExencion;
    }

    public void setLdpExencion(ObjectListDataProvider oldp) {
        this.ldpExencion = oldp;
    }

    private RadioButton radioButton1 = new RadioButton();

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton rb) {
        this.radioButton1 = rb;
    }
    private TableColumn tcPeriodo = new TableColumn();

    public TableColumn getTcPeriodo() {
        return tcPeriodo;
    }

    public void setTcPeriodo(TableColumn tcPeriodo) {
        this.tcPeriodo = tcPeriodo;
    }

    private TableColumn tcMonto = new TableColumn();

    public TableColumn getTcMonto() {
        return tcMonto;
    }

    public void setTcMonto(TableColumn tcMonto) {
        this.tcMonto = tcMonto;
    }

    private TableColumn tcMontoExento = new TableColumn();

    public TableColumn getTcMontoExento() {
        return tcMontoExento;
    }

    public void setTcMontoExento(TableColumn tcMontoExento) {
        this.tcMontoExento = tcMontoExento;
    }

    private TableColumn tcFechaVencimiento = new TableColumn();

    public TableColumn getTcFechaVencimiento() {
        return tcFechaVencimiento;
    }

    public void setTcFechaVencimiento(TableColumn tcFechaVencimiento) {
        this.tcFechaVencimiento = tcFechaVencimiento;
    }
    private TableColumn tcReferenciaNotaHCD = new TableColumn();

    public TableColumn getTcReferenciaNotaHCD() {
        return tcReferenciaNotaHCD;
    }

    public void setTcReferenciaNotaHCD(TableColumn tcReferenciaNotaHCD) {
        this.tcReferenciaNotaHCD = tcReferenciaNotaHCD;
    }

    private StaticText stPeriodo = new StaticText();

    public void setStPeriodo(StaticText stPeriodo) {
        this.stPeriodo = stPeriodo;
    }

    public StaticText getStPeriodo() {
        return stPeriodo;
    }

    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }

    private StaticText stMonto = new StaticText();

    public void setStMonto(StaticText stMonto) {
        this.stMonto = stMonto;
    }

    public StaticText getStMonto() {
        return stMonto;
    }
     private StaticText stMontoExento = new StaticText();

    public void setStMontoExento(StaticText stMontoExento) {
        this.stMontoExento = stMontoExento;
    }

    public StaticText getStMontoExento() {
        return stMontoExento;
    }
    private StaticText stFechaVencimiento = new StaticText();

    public void setStFechaVencimiento(StaticText stFechaVencimiento) {
        this.stFechaVencimiento = stFechaVencimiento;
    }

    public StaticText getStFechaVencimiento() {
        return stFechaVencimiento;
    }
    private StaticText stReferenciaNotaHCD = new StaticText();

    public void setStReferenciaNotaHCD(StaticText stReferenciaNotaHCD) {
        this.stReferenciaNotaHCD = stReferenciaNotaHCD;
    }

    public StaticText getStReferenciaNotaHCD() {
        return stReferenciaNotaHCD;
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

    private DropDown ddPeriodicidadCuota = new DropDown();

    public DropDown getDdPeriodicidadCuota() {
        return ddPeriodicidadCuota;
    }

    public void setDdPeriodicidadCuota(DropDown dd) {
        this.ddPeriodicidadCuota = dd;
    }

    private SingleSelectOptionsList ddPeriodicidadCuotaDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdPeriodicidadCuotaDefaultOptions() {
        return ddPeriodicidadCuotaDefaultOptions;
    }

    public void setDdPeriodicidadCuotaDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddPeriodicidadCuotaDefaultOptions = ssol;
    }

    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }

    private Button btnGuardar = new Button();

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(Button b) {
        this.btnGuardar = b;
    }

    private Button btnSeleccionarLiquidacionOSP = new Button();

    public Button getBtnSeleccionarLiquidacionOSP() {
        return btnSeleccionarLiquidacionOSP;
    }

    public void setBtnSeleccionarLiquidacionOSP(Button b) {
        this.btnSeleccionarLiquidacionOSP = b;
    }

    private Button btnSeleccionarLiquidacionTGI = new Button();

    public Button getBtnSeleccionarLiquidacionTGI() {
        return btnSeleccionarLiquidacionTGI;
    }

    public void setBtnSeleccionarLiquidacionTGI(Button b) {
        this.btnSeleccionarLiquidacionTGI = b;
    }

    private Button btnSeleccionarLiquidacionPFO = new Button();

    public Button getBtnSeleccionarLiquidacionPFO() {
        return btnSeleccionarLiquidacionPFO;
    }

    public void setBtnSeleccionarLiquidacionPFO(Button b) {
        this.btnSeleccionarLiquidacionPFO = b;
    }

    private Button btnSeleccionarLiquidacionSHPS = new Button();

    public Button getBtnSeleccionarLiquidacionSHPS() {
        return btnSeleccionarLiquidacionSHPS;
    }

    public void setBtnSeleccionarLiquidacionSHPS(Button b) {
        this.btnSeleccionarLiquidacionSHPS = b;
    }

    private Button btnSeleccionarReliquidacion = new Button();

    public Button getBtnSeleccionarReliquidacion() {
        return btnSeleccionarReliquidacion;
    }

    public void setBtnSeleccionarReliquidacion(Button b) {
        this.btnSeleccionarReliquidacion = b;
    }

    private TextField tfNotaHCD = new TextField();

    public TextField getTfNotaHCD() {
        return tfNotaHCD;
    }

    public void setTfNotaHCD(TextField tf) {
        this.tfNotaHCD = tf;
    }

    private Button btnQuitar = new Button();

    public Button getBtnQuitar() {
        return btnQuitar;
    }

    public void setBtnQuitar(Button b) {
        this.btnQuitar = b;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">

    // Atributos propios de la pagina.
    // CAMBIAR: Vincular a los campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;
    public Long getIdPagina() { return idPagina; }
    public void setIdPagina(Long idPagina) { this.idPagina = idPagina; }
    public Long getIdSubSesion() { return idSubSesion; }
    public void setIdSubSesion(Long idSubSesion) { this.idSubSesion = idSubSesion; }

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }

    // CAMBIAR: Constantes que varian segun la pagina.
    // cantidad de objetos administrados por la pagina
    private final int CANTIDAD_OBJETOS = 2;
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Agregar Exencion";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarExencionRegistroDeuda";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;



    // </editor-fold>


    /**
     * <p>Construir una instancia de bean de página.</p>
     */
    public AgregarExencionRegistroDeuda() {
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
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1)getBean("RequestBean1");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean)getBean("CommunicationHabilitacionesBean");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean)getBean("CommunicationComprasBean");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean)getBean("ComunicationBean");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1)getBean("ApplicationBean1");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1)getBean("SessionBean1");
    }


    /**
     * <p>Método de devolución de llamada al que se llama cuando se navega hasta esta página,
     * ya sea directamente mediante un URL o de manera indirecta a través de la navegación de páginas.
     * Puede personalizar este método para adquirir recursos que se necesitarán
     * para los controladores de eventos y métodos del proceso, sin tener en cuenta si esta
     * página realiza procesamiento de devolución de envíos.</p>
     *
     * <p>Tenga en cuenta que si la petición actual es una devolución de envío, los valores
     * de propiedad de los componentes <strong>no</strong> representan ningún
     * valor enviado con esta petición.  En su lugar, representan los
     * valores de propiedades que se guardaron para esta vista cuando se procesó.</p>
     */
    public void init() {
        // Realizar iniciaciones heredadas de la superclase
        super.init();
        // Realizar inicio de aplicación que debe finalizar
        // *antes* de que se inicien los componentes administrados
        // TODO - Agregar código de inicio propio aquí

        // <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
        // Iniciar componentes administrados automáticamente
        // *Nota* - esta lógica NO debe modificarse
        try {
            _init();
        } catch (Exception e) {
            log("AgregarExencion Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicación que debe finalizar
        // *después* de que se inicien los componentes administrados
        // TODO - Agregar código de inicio propio aquí

      }

    /**
     * <p>Método de devolución de llamada al que se llama cuando el árbol de componentes se ha
     * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este método
     * <strong>sólo</strong> se llamará en una petición de devolución de envío que
     * esté procesando el envío de un formulario.  Puede personalizar este método para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /**
     * <p>Método de devolución de llamada al que se llama justo antes del procesamiento.
     * <strong>Sólo</strong> se llamará a este método en la página que
     * se procesa, no se llamará, por ejemplo, en una página que
     * ha procesado una devolución de envío y a continuación ha navegado hasta otra página.  Puede personalizar
     * este método para asignar recursos necesarios para procesar
     * esta página.</p>
     */
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

          /*   if (this.getListaDelCommunication() != null &&
                    this.getListaDelCommunication().size() > 0) {
                    try {
                        this.refrescarTabla();
                    }catch (Exception ex){
                  //       this.limpiarTabla();
                    }
            }*/
        }

        // Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());
        }

        if (!recarga) {
            try {
                this.mostrarEstadoObjetosUsados();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));

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
     * <p>Método de devolución de llamada al que se llama cuando se completa el procesamiento de
     * esta petición, si se llamó al método <code>init()</code> (sin tener en cuenta
     * si se trata de la página que se ha procesado o no).  Puede personalizar este
     * método para liberar los recursos adquiridos en los métodos <code>init()</code>,
     * <code>preprocess()</code> o <code>prerender()</code> (o
     * durante la ejecución de un controlador de eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
       ep.getObjetos().add(ind++, new ExencionRegistroDeuda());
       ep.getObjetos().add(ind++, new RegistroExencionRegistroDeuda());
       ep.getObjetos().add(ind++, new DigestoMunicipal());
       ep.getObjetos().add(ind++, null); //Calendario
       ep.getObjetos().add(ind++, null); //Periodo
       ep.getObjetos().add(ind++, null); //Cuota
       ep.getObjetos().add(ind++, null); //periodicidadCuota


       // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() throws Exception {
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        int ind=0;
        ExencionRegistroDeuda locExencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(ind++, ExencionRegistroDeuda.class);
        RegistroExencionRegistroDeuda locRegistroExencion = (RegistroExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(ind++,RegistroExencionRegistroDeuda.class);
        DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
        
        CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
        
        Periodicidad periodicidadCuota = (Periodicidad) this.obtenerObjetoDelElementoPila(ind++, Periodicidad.class);

        Object nombre = this.getTfNombre().getText();
        Object porcentaje= this.tfPorcentaje.getText();
        Object motivo = this.taMotivo.getText();
        
        Object periodicidadCuotaSelected = this.ddPeriodicidadCuota.getSelected();


        if (nombre != null && nombre != "") locExencionRegistroDeuda.setNombre(nombre.toString());
        else locExencionRegistroDeuda.setNombre(null);
        if (porcentaje != null && porcentaje != "") locExencionRegistroDeuda.setPorcentaje(new Double(porcentaje.toString()));
        else locExencionRegistroDeuda.setPorcentaje(null);
        if (motivo != null && motivo != "") locExencionRegistroDeuda.setMotivo(motivo.toString());
        else locExencionRegistroDeuda.setMotivo(null);


        periodicidadCuotaSelected = this.getDdPeriodicidadCuota().getSelected();
        if ((periodicidadCuotaSelected != null) && (periodicidadCuotaSelected.toString().length() > 0)){
            periodicidadCuota= (Periodicidad.valueOf(periodicidadCuotaSelected.toString()));
        }else periodicidadCuota = null;
        
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
        
       locExencionRegistroDeuda.setPeriodicidadCuotas(periodicidadCuota);
       if (digestoMunicipal.getIdDigestoMunicipal() == -1) digestoMunicipal = null;
       if (digestoMunicipal != null)locExencionRegistroDeuda.setDigestoMunicipal(digestoMunicipal);
             
       this.getLdpExencion().commitChanges();
       
       ind = 0;
       this.getElementoPila().getObjetos().set(ind++, locExencionRegistroDeuda);
       this.getElementoPila().getObjetos().set(ind++, locRegistroExencion);
       this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);
       this.getElementoPila().getObjetos().set(ind++, calendario);
       this.getElementoPila().getObjetos().set(ind++, periodoCalendario);
       this.getElementoPila().getObjetos().set(ind++, cuota);
       this.getElementoPila().getObjetos().set(ind++, periodicidadCuota);
    }

    private void mostrarEstadoObjetosUsados() throws Exception {
        // CAMBIAR: Obtener la instancia por cada objeto manejado en la pagina
        
        ExencionRegistroDeuda locExencionRegistroDeuda = null;
        RegistroExencionRegistroDeuda locRegistroExencion = new RegistroExencionRegistroDeuda();
        DigestoMunicipal digesto = null;
        
        CalendarioMunicipal calendario = null;
        PeriodoLiquidacion periodoCalendario = null;
        CuotaLiquidacion cuota = null; 
        
        Periodicidad periodicidadCuota = null;
    
        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            locExencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(0, ExencionRegistroDeuda.class);
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();

            if (seleccionado instanceof DigestoMunicipal){
                digesto = (DigestoMunicipal) seleccionado;
                this.getElementoPila().getObjetos().set(2, digesto);
            }
            else if (seleccionado instanceof LiquidacionTasa) {
            
                RegistroDeuda nuevoRegDeuda = (RegistroDeuda) seleccionado;

                try {
                     if (nuevoRegDeuda.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VIGENTE) || nuevoRegDeuda.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA)){
                        locRegistroExencion.setRegistroDeuda(nuevoRegDeuda);
                        locRegistroExencion.setExencionRegistroDeuda(locExencionRegistroDeuda);
                        locExencionRegistroDeuda.addRegistroDeudaExento(locRegistroExencion);
                     }else {
                            warn("S\363lo pueden agregarse Registros de Deuda en Estado VIGENTE o VENCIDO.");
                    }

                }catch(Exception e){
                    e.printStackTrace();
                    log(CASO_NAVEGACION+"_GuardarError:", e);
                    error(NOMBRE_PAGINA+" - Guardar: " + e.getMessage());
                }
                this.getElementoPila().getObjetos().set(0, locExencionRegistroDeuda);
                this.getElementoPila().getObjetos().set(1, locRegistroExencion);
            }
            this.getRequestBean1().setObjetoSeleccion(null);
        }

          
       int ind= 0;
       locExencionRegistroDeuda =  (ExencionRegistroDeuda)this.obtenerObjetoDelElementoPila(ind++, ExencionRegistroDeuda.class);
       locRegistroExencion = (RegistroExencionRegistroDeuda)this.obtenerObjetoDelElementoPila(ind++, RegistroExencionRegistroDeuda.class);
       digesto = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
       
       calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
       periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
       cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
       
       periodicidadCuota = (Periodicidad) this.obtenerObjetoDelElementoPila(ind++, Periodicidad.class);

       this.tfNombre.setText(locExencionRegistroDeuda.getNombre());
       this.tfPorcentaje.setText(locExencionRegistroDeuda.getPorcentaje());
       this.taMotivo.setText(locExencionRegistroDeuda.getMotivo());

       if (periodicidadCuota != null){
            this.getDdPeriodicidadCuota().setSelected(Util.getEnumNameFromString(String.valueOf(locExencionRegistroDeuda.getPeriodicidadCuotas())));
            this.getDdPeriodicidadCuotaDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locExencionRegistroDeuda.getPeriodicidadCuotas())));
            
        }
       
       
       
       //periodo
       if(calendario != null && calendario.getIdCalendario() != -1) {
            this.getDdCalendarios().setSelected(calendario.toString());
        }
        if(periodoCalendario != null && periodoCalendario.getIdPeriodo() != -1) {
            this.getDdPeriodos().setSelected(periodoCalendario.toString());
        }
        if(cuota != null && cuota.getIdPeriodo() != -1) {
            this.getDdCuotas().setSelected(cuota.toString());
        }
        /////

        
        
        
       if (digesto.getIdDigestoMunicipal() == -1) digesto = null;
       if (digesto != null) this.getTfDigestoMunicipal().setText(digesto.toString());
            else this.getTfDigestoMunicipal().setText(null);

       if (locExencionRegistroDeuda.getListaRegistrosExencion().size() > 0) {
            this.cambiarEstadoComponentes(true);
       }

       ArrayList listaExenciones= new ArrayList();
       listaExenciones.addAll(locExencionRegistroDeuda.getListaRegistrosExencion());
       this.getObjectListDataProvider().setList(listaExenciones);
       this.setListaDelCommunication(listaExenciones);

     }
     
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpExencion();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationSAICBean().getListaExencion();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
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

// Juan Pablo
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

    public String btnGuardar_action() {
       String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

           try {
                this.guardarEstadoObjetosUsados();
                ExencionRegistroDeuda locExencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(0, ExencionRegistroDeuda.class);

                // CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[6];
                String[] nomNoVacios = new String[6];

                int pos = 0;
                noVacios[pos] = this.getTfNombre();
                nomNoVacios[pos++] = "Nombre";
                noVacios[pos] = this.getTfPorcentaje();
                nomNoVacios[pos++] = "Porcentaje";
                noVacios[pos] = this.getDdCalendarios();
                nomNoVacios[pos++] = "Calendario";
                noVacios[pos] = this.getDdPeriodos();
                nomNoVacios[pos++] = "Periodo";
                noVacios[pos] = this.getDdCuotas();
                nomNoVacios[pos++] = "Cuota";
                noVacios[pos] = this.getDdPeriodicidadCuota();
                nomNoVacios[pos++] = "Periodicidad de las Cuotas";

                v.noSonVacios(noVacios, nomNoVacios);
         
                 if (locExencionRegistroDeuda.getPorcentaje() != null){
                    if(new Double(this.getTfPorcentaje().getText().toString()).compareTo(new Double(0)) <= 0)
                        v.getErrores().add("El porcentaje debe ser mayor a 0");
                }

                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
                this.getCommunicationSAICBean().getRemoteSystemExencion().addExencionRegistroDeuda(locExencionRegistroDeuda);


                this.getRequestBean1().setRespuestaABM(locExencionRegistroDeuda);
                
                info("La Exenci\363n se agreg\363 exitosamente.");
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_GuardarError:", ex);
                error(NOMBRE_PAGINA+" - Guardar: " + ex.getMessage());
                ex.printStackTrace();
                return null;
            }
    
            retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
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

    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo!=null) campo.setText(null);
        } catch (Exception ex) { }
    }
    private void limpiarTabla() {
        this.getLdpExencion().getList().clear();
    }

    public void cambiarEstadoComponentes(boolean deshabilitar) {

        this.ddCalendarios.setDisabled(deshabilitar);
        this.ddPeriodos.setDisabled(deshabilitar);
        this.ddCuotas.setDisabled(deshabilitar);
    }

    public String btnLimpiarDigesto_action() throws Exception {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if(ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(2, tfDigestoMunicipal);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    public String btnSeleccionarDigesto_action() throws Exception {
       String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminDigestoMunicipal";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    public String btnSeleccionarLiquidacionOSP_action() throws Exception {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();


        if (ultimo) {
     
            Object calendarioSeleccionado = this.getDdCalendarios().getSelected();      
            Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
            Object cuotaSeleccionada = this.getDdCuotas().getSelected();
            
            if ((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0) && (periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0) && (cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)){
                 System.out.println("/*/* datos OK...");
            }
            else {
                warn ("Ingrese los datos correspondientes al per\355odo");
                return null;
            }
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminLiquidacionOSP";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

     public String btnSeleccionarLiquidacionTGI_action() throws Exception {
       String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
          
            Object calendarioSeleccionado = this.getDdCalendarios().getSelected();      
            Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
            Object cuotaSeleccionada = this.getDdCuotas().getSelected();
            
            if ((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0) && (periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0) && (cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)){
                 System.out.println("/*/* datos OK...");
            }
            else {
                warn ("Ingrese los datos correspondientes al per\355odo");
                return null;
            }
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminLiquidacionTGI";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarLiquidacionPFO_action() throws Exception {
       String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
        
            Object calendarioSeleccionado = this.getDdCalendarios().getSelected();      
            Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
            Object cuotaSeleccionada = this.getDdCuotas().getSelected();
            
            if ((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0) && (periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0) && (cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)){
                 System.out.println("/*/* datos OK...");
            }
            else {
                warn ("Ingrese los datos correspondientes al per\355odo");
                return null;
            }
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminLiquidacionPFO";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarLiquidacionSHPS_action() throws Exception {
       String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
  
            Object calendarioSeleccionado = this.getDdCalendarios().getSelected();      
            Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
            Object cuotaSeleccionada = this.getDdCuotas().getSelected();
            
            if ((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0) && (periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0) && (cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)){
                 System.out.println("/*/* datos OK...");
            }
            else {
                warn ("Ingrese los datos correspondientes al per\355odo");
                return null;
            }
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminLiquidacionSHPS";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

     public String btnSeleccionarReliquidacion_action() throws Exception {
       String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
  
            Object calendarioSeleccionado = this.getDdCalendarios().getSelected();      
            Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
            Object cuotaSeleccionada = this.getDdCuotas().getSelected();
            
            if ((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0) && (periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0) && (cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)){
                 System.out.println("/*/* datos OK...");
            }
            else {
                warn ("Ingrese los datos correspondientes al per\355odo");
                return null;
            }
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminReliquidacion";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
     
    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            rk = this.getLdpExencion().getRowKey(aRowId);
        } catch (Exception ex) {}
        return rk;
    } 
    
    private int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
    }

    public String btnQuitar_action() throws Exception {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        ExencionRegistroDeuda locExencionRegistroDeuda = (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(0, ExencionRegistroDeuda.class);

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado();
                RegistroDeuda registroDeuda = null;
                if (rk != null) {
                    int index = getNroFila(rk.toString());

                    Object obj = this.getLdpExencion().getObjects()[index];
                    
                   // this.getLdpExencion().removeObject(obj);
                 //   this.getListaDelCommunication().remove(obj);
                    locExencionRegistroDeuda.removeRegistroDeudaExento((RegistroExencionRegistroDeuda) obj);
                    //
                    //registroDeuda= (RegistroDeuda) obj;
                  //  registroDeuda.setReferenciaNotaHCD(null);
                    //
                    if (locExencionRegistroDeuda.getListaRegistrosExencion().size() > 0) {
                                this.cambiarEstadoComponentes(true);
                    } else {
                            this.cambiarEstadoComponentes(false);
                    }
                    this.getElementoPila().getObjetos().set(0, locExencionRegistroDeuda);
                    this.refrescarTabla();
                }
            } catch (Exception ex) {}

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    private void refrescarTabla() throws Exception{
        // CAMBIAR: Segun objeto de busqueda.
        //DigestoMunicipal digestoMunicipal= (DigestoMunicipal) this.obtenerObjetoDelElementoPila(0, DigestoMunicipal.class);
        ExencionRegistroDeuda locExencionRegistroDeuda= (ExencionRegistroDeuda) this.obtenerObjetoDelElementoPila(0, ExencionRegistroDeuda.class);
        ArrayList listaRegistrosDeuda= new ArrayList();
        listaRegistrosDeuda.addAll(locExencionRegistroDeuda.getListaRegistrosExencion());

       // locExencionRegistroDeuda.
     //   if(registroDeuda.getIdRegistroDeuda() == -1l){
      //      registroDeuda = null;
     //   }
          /*   if(digestoMunicipal.getIdDigestoMunicipal() == -1){
            digestoMunicipal = null;
        }*/
     try{

         this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
         this.setListaDelCommunication(listaRegistrosDeuda);
         this.getObjectListDataProvider().setList(this.getListaDelCommunication());

        }catch(Exception ex){
            this.getObjectListDataProvider().setList(null);
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

}