/*
 * ConsultarEstadoCuenta.java
 *
 * Created on 23 de octubre de 2006, 13:23
 * Copyright Trascender SRL
 */
package muni.saic.ABMEstadoCuenta;
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
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.framework.util.Util;
import java.util.ArrayList;
import java.util.HashSet;

import javax.faces.FacesException;
import com.trascender.presentacion.utiles.Constantes;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.persistent.*;
import com.trascender.habilitaciones.recurso.transients.*;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.transients.EstadoCuentaContribuyente;
import java.util.Set;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ValueChangeEvent;




/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ConsultarEstadoCuenta extends AbstractPageBean {
    
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a campos ocultos.
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
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Consultar Estado de Cuenta";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ConsultarEstadoCuenta";
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
        System.out.println("////////////////////   INIT()   //////////////////////");
        if (this.getListaDelCommunication()!=null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }

        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Periodicidad.values(), "cap");

        ddEstadoDefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[]{new com.sun.rave.web.ui.model.Option("", ""),
            new com.sun.rave.web.ui.model.Option("Cancelada", "Cancelada"),
            new com.sun.rave.web.ui.model.Option("No Cancelada", "No Cancelada")});
        ddEstadoDefaultOptions.setSelectedValue("No Cancelada");
//        ddPeriodicidadDefaultOptions.setOptions(op);

        numberConverter1.setPattern("$ #,##0.00");
        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern("dd/MM/yyyy");
        dateTimeConverter1.setTimeStyle("full");
       
        
        
//        Set<String> locListaCalendarios = this.getCommunicationSAICBean().getMapaCalendarios().keySet();
        Set<String> locListaCalendarios = new HashSet<String>();
        
        Option[] opCalendarios = new Option[locListaCalendarios.size()];
        int i = 0;
//        opCalendarios[i++] = new Option("", "");
        for (String cadaCalendario : locListaCalendarios){
            opCalendarios[i++] = new Option(cadaCalendario, cadaCalendario);
        }     
        ddCalendariosOptions.setOptions(opCalendarios);
        this.getDdCalendarios().setSelected(locListaCalendarios.iterator().next());
        
        System.out.println("/*/*/*/ seleccion = " + this.getDdCalendarios().getSelected().toString());

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
    
    private StaticText stTitulo = new StaticText();
    
    public StaticText getStTitulo() {
        return stTitulo;
    }
    
    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }

//    private StaticText stFormatoAnio = new StaticText();
//
//    public StaticText getStFormatoAnio() {
//        return stFormatoAnio;
//    }
//
//    public void setStFormatoAnio(StaticText stFormatoAnio) {
//        this.stFormatoAnio = stFormatoAnio;
//    }

    private NumberConverter numberConverter1 = new NumberConverter();

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter numberConverter1) {
        this.numberConverter1 = numberConverter1;
    }

    private DropDown ddEstado = new DropDown();

    public DropDown getDdEstado() {
        return ddEstado;
    }

    public void setDdEstado(DropDown ddEstado) {
        this.ddEstado = ddEstado;
    }
    
    private DropDown ddCalendarios = new DropDown();
    private DropDown ddPeriodos = new DropDown();
    private DropDown ddCuotas = new DropDown();
    private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

    public DropDown getDdCalendarios() {
        return ddCalendarios;
    }

    public void setDdCalendarios(DropDown ddCalendarios) {
        this.ddCalendarios = ddCalendarios;
    }

    public SingleSelectOptionsList getDdCalendariosOptions() {
        return ddCalendariosOptions;
    }

    public void setDdCalendariosDefaultOptions(SingleSelectOptionsList ddCalendariosOptions) {
        this.ddCalendariosOptions = ddCalendariosOptions;
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

    public void setDdPeriodosOptions(SingleSelectOptionsList ddPeriodosDefaultOptions) {
        this.ddPeriodosOptions = ddPeriodosOptions;
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
    
    public SingleSelectOptionsList getDdEstadoDefaultOptions() {
        return ddEstadoDefaultOptions;
    }

    public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
        this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
    }
    
    private Button btnBuscar = new Button();
    private Button btnImprimir = new Button();

    public Button getBtnImprimir() {
        return btnImprimir;
    }

    public void setBtnImprimir(Button btnImprimir) {
        this.btnImprimir = btnImprimir;
    }

    public Button getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(Button btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public Label getLblEstado() {
        return lblEstado;
    }

    public void setLblEstado(Label lblEstado) {
        this.lblEstado = lblEstado;
    }

    public StaticText getStaticText13() {
        return staticText13;
    }

    public void setStaticText13(StaticText staticText13) {
        this.staticText13 = staticText13;
    }

    public StaticText getStaticText14() {
        return staticText14;
    }

    public void setStaticText14(StaticText staticText14) {
        this.staticText14 = staticText14;
    }

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tableColumn15) {
        this.tableColumn15 = tableColumn15;
    }

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tableColumn16) {
        this.tableColumn16 = tableColumn16;
    }

    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
        this.dateTimeConverter1 = dateTimeConverter1;
    }

    private Button btnVolver = new Button();
    
    public Button getBtnVolver() {
        return btnVolver;
    }
    
    public void setBtnVolver(Button b) {
        this.btnVolver = b;
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

    private Label label2 = new Label();
    private Label lblEstado = new Label();
    private Label lblPeriodo = new Label();
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

    public Label getLblPeriodo() {
        return lblPeriodo;
    }

    public void setLblPeriodo(Label lblPeriodo) {
        this.lblPeriodo = lblPeriodo;
    }

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }

    private Table table2 = new Table();

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table t) {
        this.table2 = t;
    }

    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup trg) {
        this.tableRowGroup2 = trg;
    }

    private ObjectListDataProvider ldpPeriodosAdeudados = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpPeriodosAdeudados() {
        return ldpPeriodosAdeudados;
    }

    public void setLdpPeriodosAdeudados(ObjectListDataProvider oldp) {
        this.ldpPeriodosAdeudados = oldp;
    }

    private TableColumn tableColumn9 = new TableColumn();
    private TableColumn tableColumn18 = new TableColumn();

    public TableColumn getTableColumn18() {
        return tableColumn18;
    }

    public void setTableColumn18(TableColumn tableColumn18) {
        this.tableColumn18 = tableColumn18;
    }

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }

    private TableColumn tableColumn11 = new TableColumn();
    private TableColumn tableColumn15 = new TableColumn();
    private TableColumn tableColumn16 = new TableColumn();
    private TableColumn tableColumn17 = new TableColumn();

    public TableColumn getTableColumn17() {
        return tableColumn17;
    }

    public void setTableColumn17(TableColumn tableColumn17) {
        this.tableColumn17 = tableColumn17;
    }

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tc) {
        this.tableColumn11 = tc;
    }

    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }

    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tc) {
        this.tableColumn12 = tc;
    }

    private StaticText staticText10 = new StaticText();
    private StaticText staticText13 = new StaticText();
    private StaticText staticText14 = new StaticText();
    private StaticText staticText15 = new StaticText();
    private StaticText staticText16 = new StaticText();
    private StaticText staticText17 = new StaticText();

    public StaticText getStaticText17() {
        return staticText17;
    }

    public void setStaticText17(StaticText staticText17) {
        this.staticText17 = staticText17;
    }

    public StaticText getStaticText16() {
        return staticText16;
    }

    public void setStaticText16(StaticText staticText16) {
        this.staticText16 = staticText16;
    }

    public StaticText getStaticText15() {
        return staticText15;
    }

    public void setStaticText15(StaticText staticText15) {
        this.staticText15 = staticText15;
    }

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }

    private TableColumn tableColumn13 = new TableColumn();

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tc) {
        this.tableColumn13 = tc;
    }

    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText st) {
        this.staticText11 = st;
    }

    private TableColumn tableColumn14 = new TableColumn();

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tc) {
        this.tableColumn14 = tc;
    }

    private StaticText staticText12 = new StaticText();

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText st) {
        this.staticText12 = st;
    }

    private TextArea taObligacion = new TextArea();

    public TextArea getTaObligacion() {
        return taObligacion;
    }

    public void setTaObligacion(TextArea ta) {
        this.taObligacion = ta;
    }
    // </editor-fold>
    
    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ConsultarEstadoCuenta() {
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
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean)getBean("CommunicationComprasBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean)getBean("CommunicationSAICBean");
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
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
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
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1)getBean("SessionBean1");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1)getBean("RequestBean1");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1)getBean("ApplicationBean1");
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
            log("ConsultarEstadoCuenta Initialization Failure", e);
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
        System.out.println("//////////////// INICIO prerender()");
        boolean existeIdSubSesionReq = false;
        boolean existeIdPaginaReq    = false;
        boolean existeIdSubSesionPag = false;
        boolean existeIdPaginaPag    = false;
        boolean recarga              = false;
        boolean cargarTabla          = false;
        
        if(this.getRequestBean1()!=null) {
            existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
            existeIdPaginaReq    = (this.getRequestBean1().getIdPagina() != null);
        }
        
        this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
        this.setIdPagina((Long) this.getHidIdPagina().getText());
        
        existeIdSubSesionPag = this.getIdSubSesion() != null;
        existeIdPaginaPag    = this.getIdPagina() != null;
        
        // 1. Pagina nueva - Inicio de transaccion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            if(this.PUEDE_SER_PAGINA_INICIAL) {
                Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
                this.setIdPagina(key);
                this.setIdSubSesion(key);
                this.crearElementoPila();
            
                cargarTabla = true ;
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
        }

       if (this.getListaDelCommunication() != null &&
           this.getListaDelCommunication().size() > 0) {
                try { this.refrescarTabla(); } catch (Exception ex) { this.limpiarTabla(); }
           }

        if (!recarga) {
            this.mostrarEstadoObjetosUsados();
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
        ep.getObjetos().add(ind++, null); // persona
        ep.getObjetos().add(ind++, null); // Obligacion        
        ep.getObjetos().add(ind++, new ArrayList()); // lista de periodos adeudados
        ep.getObjetos().add(ind++, null);     //        Calendario
        ep.getObjetos().add(ind++, null);     //        Periodo de los calendarios
        ep.getObjetos().add(ind++, null);     //        Cuota
        ep.getObjetos().add(ind++, new Periodo()); //Periodo para el imprimir.
        ep.getObjetos().add(ind++, new EstadoCuentaContribuyente(null, null));
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        System.out.println("////////// INICIO guardarEstadoObjetosUsados()");
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        Obligacion obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
        ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);

        Periodo periodo = (Periodo) this.obtenerObjetoDelElementoPila(ind++, Periodo.class);
        EstadoCuentaContribuyente estadoCuentaContribuyente = (EstadoCuentaContribuyente) this.obtenerObjetoDelElementoPila(ind++, EstadoCuentaContribuyente.class);

        
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
        
        FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
        locFiltro.setCuotaLiquidacion(cuota);        
        
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, persona);
        this.getElementoPila().getObjetos().set(ind++, obligacion);
        this.getElementoPila().getObjetos().set(ind++, periodosAdeudados);

        this.getElementoPila().getObjetos().set(ind++, calendario);
        this.getElementoPila().getObjetos().set(ind++, periodoCalendario);
        this.getElementoPila().getObjetos().set(ind++, cuota);
        
        this.getElementoPila().getObjetos().set(ind++, periodo);
        this.getElementoPila().getObjetos().set(ind++, estadoCuentaContribuyente);
        
        System.out.println("////////// FIN guardarEstadoObjetosUsados()");
    }
    
    private void mostrarEstadoObjetosUsados() {
        System.out.println("////////// INICIO mostrarEstadoObjetosUsados()");
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Persona persona = null;
        Obligacion obligacion = null;
        ArrayList periodosAdeudados = null;

        CalendarioMunicipal calendario = null;
        PeriodoLiquidacion periodoCalendario = null;
        CuotaLiquidacion cuota = null;        
        
        Periodo periodo = null;
        EstadoCuentaContribuyente estadoCuentaContribuyente = null;
        
        
        String estadoSelected  = Conversor.getStringDeObject(this.ddEstado.getSelected());
                
        if (this.getRequestBean1().getObjetoABM() != null) {
            estadoCuentaContribuyente = (EstadoCuentaContribuyente) this.getRequestBean1().getObjetoABM();
            obligacion = estadoCuentaContribuyente.getObligacion();
            persona = obligacion.getPersona();

            
//            try {
//                this.refrescarTabla();
//                // objetos del tipo LiquidacionTasa
//                //this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
//
//                //periodosAdeudados = (ArrayList) this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaLiquidacionesObligacion(obligacion, null,Conversor.getStringDeObject(ddEstado).toUpperCase(),periodo);
//              //  periodosAdeudados = new ArrayList(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaDeudasContribuyente(obligacion));
//                //estadoRegistroDeuda = new ArrayList(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaLiquidacionesObligacion(obligacion, EstadoRegistroDeuda.VIGENTE))
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                error(this.NOMBRE_PAGINA + ": " + ex.getMessage());
//            }
            
            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, persona);
            this.getElementoPila().getObjetos().set(ind++, obligacion);
            this.getElementoPila().getObjetos().set(7, estadoCuentaContribuyente);

        }
        this.acomodarSeleccionado();
        
        ind = 0;
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);        
        periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
        periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
        cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);
        
        periodo = (Periodo) this.obtenerObjetoDelElementoPila(ind++, Periodo.class);
        estadoCuentaContribuyente = (EstadoCuentaContribuyente) this.obtenerObjetoDelElementoPila(7, EstadoCuentaContribuyente.class);
        
        
        
        
        if(calendario != null && calendario.getIdCalendario() != -1) {
            this.getDdCalendarios().setSelected(calendario.toString());
        }
        if(periodoCalendario != null && periodoCalendario.getIdPeriodo() != -1) {
            this.getDdPeriodos().setSelected(periodoCalendario.toString());
        }
        if(cuota != null && cuota.getIdCuotaLiquidacion() != -1) {
            this.getDdCuotas().setSelected(cuota.toString());
        }
        

        this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(ddEstado.getSelected())));
        this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(ddEstado.getSelected())));

        if (estadoCuentaContribuyente.getStringObligacion() != null && !estadoCuentaContribuyente.getStringObligacion().isEmpty()) this.getTaObligacion().setText(estadoCuentaContribuyente.getStringObligacion());
        
        this.setListaDelCommunication(periodosAdeudados);
        this.getObjectListDataProvider().setList(periodosAdeudados);
        
        System.out.println("////////// FIN mostrarEstadoObjetosUsados()");        
    }
    
    // PARA TABLA 1
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpPeriodosAdeudados();
    }
    
    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationSAICBean().getListaPeriodosAdeudadosEstadoCuenta();
    }
    
    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationSAICBean().setListaPeriodosAdeudadosEstadoCuenta(lista);
    }

    private void refrescarTabla() throws Exception {
        Periodo periodo = null;
        Obligacion obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(1, Obligacion.class);      
        
        Object estadoSelected = null;

        if(ddEstado.getSelected() != null) estadoSelected = ddEstado.getSelected().toString();
        else estadoSelected = null;
        if(obligacion.getIdObligacion() == -1) obligacion = null;
        ArrayList listado = null;
        String estado = Conversor.getStringDeObject(estadoSelected).toUpperCase();
        
        boolean err = false;

        if(!err){
            try{
                this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
                listado = (ArrayList) this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaRegistrosDeudaObligacion(obligacion, null,estado,periodo);
            }catch (Exception ex){
            }

            this.setListaDelCommunication(listado);
            this.getObjectListDataProvider().setList(listado);
        }
        

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

    private RowKey rowKeySeleccionado = null;

    public RowKey getRowKeySeleccionado() {
        return rowKeySeleccionado;
    }

    public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
        this.rowKeySeleccionado = rowKeySeleccionado;
    }

    public void guardarOrdenamiento() {
        this.getElementoPila().setOrdenamiento(this.getTableRowGroup2().getTableDataSorter().getSortCriteria());
    }
    private Object obtenerObjetoDelElementoPila(int posicion, Class tipoClase) {
        Object objeto = null;
        try {
            objeto = this.getElementoPila().getObjetos().get(posicion);
            if (objeto == null && !tipoClase.getName().equals(Persona.class.getName())
                    && !tipoClase.getName().equals(TipoObligacion.class.getName())) {
                objeto = tipoClase.newInstance();
            }
        } catch (Exception ex) {
        }
        return objeto;
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
        this.getObjectListDataProvider().setList(null);
//        this.getObjectListDataProvider().getList().clear();
        this.setListaDelCommunication(null);

 //       this.getListaDelCommunication().clear();

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
//        return this.getCommunicationSAICBean().getMapaCalendarios().get(pCalendario);
    	return null;
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
    }

    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo!=null) campo.setText(null);
        } catch (Exception ex) { }
    }

    public Long getPosicionEnTabla(RowKey rk){
        long inicioPagina = 0;
        long posicionGlobal = 0;
        long posicionEnPagina = 0;
        long cantRegistrosPorPagina = this.getTableRowGroup2().getRows();
        long cantRegistros = this.getTableRowGroup2().getRowCount();
        boolean encontrado = false;

        if (rk!=null) {
            while (!encontrado && inicioPagina < cantRegistros) {
                this.getTableRowGroup2().setFirst((int)inicioPagina);
                posicionEnPagina = 0;
                while (!encontrado && posicionEnPagina < cantRegistrosPorPagina){
                    encontrado = this.getTableRowGroup2().getRenderedRowKeys()[(int)posicionEnPagina].equals(rk);
                    if (!encontrado) {
                        posicionEnPagina++;
                        posicionGlobal++;
                    }
                }
                if (!encontrado) inicioPagina += cantRegistrosPorPagina;
            }
        }
        return new Long(posicionGlobal);
    }
    
    // </editor-fold>    


    public String btnVolver_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
           
            //APLICAR LOGICA AQUI...
            
            this.setListaDelCommunication(null);
            
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnBuscar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if(ultimo) {

            try {
                this.guardarEstadoObjetosUsados();
                this.refrescarTabla();
                this.guardarOrdenamiento();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());

            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION+"_BuscarError:", ex);
                error(NOMBRE_PAGINA+" - Buscar: " + ex.getMessage());
            }

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnImprimir_action() {
         String retorno = null;

        Obligacion obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(1, Obligacion.class);
        Periodo periodo = (Periodo) this.obtenerObjetoDelElementoPila(6, Periodo.class);

        Object estadoSelected = ddEstado.getSelected().toString();
        String estado = Conversor.getStringDeObject(estadoSelected).toUpperCase();
        //FIXME: Alder a esto lo comente yo Fonch, xq si es no cancelada necesito no cancelada en vez de vigente.
//        if(estado.equalsIgnoreCase("NO CANCELADA")){
//            estado = "VIGENTE";
//        }

        if(obligacion.getIdObligacion() != -1){
            try {

               // this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
                this.guardarEstadoObjetosUsados();
                this.refrescarTabla();
              
                this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());

//                this.getCommunicationSAICBean().getRemoteSystemImpresion().imprimirListadoEstadosCuenta(obligacion, estado , periodo);
                        
                this.guardarOrdenamiento();
            }catch(Exception e){
                    e.printStackTrace();
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }


}
