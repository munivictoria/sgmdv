/*
 * EliminarOfertaLicitacion.java
 *
 * Created on 2 de noviembre de 2006, 11:21
 * Copyright Trascender SRL
 */
package muni.compras.ABMOfertaLicitacion;

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
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
//import org.jboss.resource.metadata.LicenseMetaData;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class EliminarOfertaLicitacion extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Eliminar Oferta Licitacion";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "EliminarOfertaLicitacion";
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
        Option[] opTipo = null;
        opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Contratacion.Tipo.values(), "may");
        ddTipoLicitacionDefaultOptions.setOptions(opTipo);
        Option[] opEstado = null;
        opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Contratacion.Estado.values(), "may");
        ddEstadoLicitacionDefaultOptions.setOptions(opEstado);
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
    private TextField tfNumero = new TextField();

    public TextField getTfNumero() {
        return tfNumero;
    }

    public void setTfNumero(TextField tf) {
        this.tfNumero = tf;
    }
    private TextField tfPlazoMantenimientoOferta = new TextField();

    public TextField getTfPlazoMantenimientoOferta() {
        return tfPlazoMantenimientoOferta;
    }

    public void setTfPlazoMantenimientoOferta(TextField tfPlazoMantenimientoOferta) {
        this.tfPlazoMantenimientoOferta = tfPlazoMantenimientoOferta;
    }
    private TextField tfExtensionMantenimientoOferta = new TextField();

    public TextField getTfExtensionMantenimientoOferta() {
        return tfExtensionMantenimientoOferta;
    }

    public void setTfExtensionMantenimientoOferta(TextField tfExtensionMantenimientoOferta) {
        this.tfExtensionMantenimientoOferta = tfExtensionMantenimientoOferta;
    }
    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }
    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }
    private TextField tfLicitacion = new TextField();

    public TextField getTfLicitacion() {
        return tfLicitacion;
    }

    public void setTfLicitacion(TextField tfLicitacion) {
        this.tfLicitacion = tfLicitacion;
    }
    private TextField tfProveedor = new TextField();

    public TextField getTfProveedor() {
        return tfProveedor;
    }

    public void setTfProveedor(TextField tfProveedor) {
        this.tfProveedor = tfProveedor;
    }
    private TextField tfFechaOferta = new TextField();

    public TextField getTfFechaOferta() {
        return tfFechaOferta;
    }

    public void setTfFechaOferta(TextField tfFechaOferta) {
        this.tfFechaOferta = tfFechaOferta;
    }
    private TextField tfImporte = new TextField();

    public TextField getTfImporte() {
        return tfImporte;
    }

    public void setTfImporte(TextField tfImporte) {
        this.tfImporte = tfImporte;
    }
    private TextField tfGarantia = new TextField();

    public TextField getTfGarantia() {
        return tfGarantia;
    }

    public void setTfGarantia(TextField tfGarantia) {
        this.tfGarantia = tfGarantia;
    }
    private TextField tfPlazo = new TextField();

    public TextField getTfPlazo() {
        return tfPlazo;
    }

    public void setTfPlazo(TextField tfPlazo) {
        this.tfPlazo = tfPlazo;
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
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label label2) {
        this.label2 = label2;
    }
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label label3) {
        this.label3 = label3;
    }
    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label label6) {
        this.label6 = label6;
    }
    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label label7) {
        this.label7 = label7;
    }
    private Label label8 = new Label();

    public Label getLabel8() {
        return label8;
    }

    public void setLabel8(Label label8) {
        this.label8 = label8;
    }
    private Label label9 = new Label();

    public Label getLabel9() {
        return label9;
    }

    public void setLabel9(Label label9) {
        this.label9 = label9;
    }
    private Label label10 = new Label();

    public Label getLabel10() {
        return label10;
    }

    public void setLabel10(Label label10) {
        this.label10 = label10;
    }
    private Label label11 = new Label();

    public Label getLabel11() {
        return label11;
    }

    public void setLabel11(Label label11) {
        this.label11 = label11;
    }
    private Label label12 = new Label();

    public Label getLabel12() {
        return label12;
    }

    public void setLabel12(Label label12) {
        this.label12 = label12;
    }
    private Label label13 = new Label();

    public Label getLabel13() {
        return label13;
    }

    public void setLabel13(Label label13) {
        this.label13 = label13;
    }
    private Label label14 = new Label();

    public Label getLabel14() {
        return label14;
    }

    public void setLabel14(Label label14) {
        this.label14 = label14;
    }
    private TextArea taComentariosOferta = new TextArea();

    public TextArea getTaComentariosOferta() {
        return taComentariosOferta;
    }

    public void setTaComentariosOferta(TextArea taComentariosOferta) {
        this.taComentariosOferta = taComentariosOferta;
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
    
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private ObjectListDataProvider ldpOfertaRenglonesLicitacion = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpOfertaRenglonesLicitacion() {
        return ldpOfertaRenglonesLicitacion;
    }

    public void setLdpOfertaRenglonesLicitacion(ObjectListDataProvider oldp) {
        this.ldpOfertaRenglonesLicitacion = oldp;
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
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText staticText2) {
        this.staticText2 = staticText2;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText staticText3) {
        this.staticText3 = staticText3;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText staticText5) {
        this.staticText5 = staticText5;
    }
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText staticText6) {
        this.staticText6 = staticText6;
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
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    
    private DropDown ddTipoLicitacion = new DropDown();
    private SingleSelectOptionsList ddTipoLicitacionDefaultOptions = new SingleSelectOptionsList();

    public DropDown getDdTipoLicitacion() {
        return ddTipoLicitacion;
    }

    public void setDdTipoLicitacion(DropDown ddTipoLicitacion) {
        this.ddTipoLicitacion = ddTipoLicitacion;
    }

    public SingleSelectOptionsList getDdTipoLicitacionDefaultOptions() {
        return ddTipoLicitacionDefaultOptions;
    }

    public void setDdTipoLicitacionDefaultOptions(SingleSelectOptionsList ddTipoLicitacionDefaultOptions) {
        this.ddTipoLicitacionDefaultOptions = ddTipoLicitacionDefaultOptions;
    }
    private DropDown ddTipoContratacion = new DropDown();
    private SingleSelectOptionsList ddTipoContratacionDefaultOptions = new SingleSelectOptionsList();

    public DropDown getDdTipoContratacion() {
        return ddTipoContratacion;
    }

    public void setDdTipoContratacion(DropDown ddTipoContratacion) {
        this.ddTipoContratacion = ddTipoContratacion;
    }

    public SingleSelectOptionsList getDdTipoContratacionDefaultOptions() {
        return ddTipoContratacionDefaultOptions;
    }

    public void setDdTipoContratacionDefaultOptions(SingleSelectOptionsList ddTipoContratacionDefaultOptions) {
        this.ddTipoContratacionDefaultOptions = ddTipoContratacionDefaultOptions;
    }
    private DropDown ddEstadoLicitacion = new DropDown();
    private SingleSelectOptionsList ddEstadoLicitacionDefaultOptions = new SingleSelectOptionsList();

    public DropDown getDdEstadoLicitacion() {
        return ddEstadoLicitacion;
    }

    public void setDdEstadoLicitacion(DropDown ddEstadoLicitacion) {
        this.ddEstadoLicitacion = ddEstadoLicitacion;
    }

    public SingleSelectOptionsList getDdEstadoLicitacionDefaultOptions() {
        return ddEstadoLicitacionDefaultOptions;
    }

    public void setDdEstadoLicitacionDefaultOptions(SingleSelectOptionsList ddEstadoLicitacionDefaultOptions) {
        this.ddEstadoLicitacionDefaultOptions = ddEstadoLicitacionDefaultOptions;
    }
    private TabSet tabSet1 = new TabSet();

    public TabSet getTabSet1() {
        return tabSet1;
    }

    public void setTabSet1(TabSet tabSet1) {
        this.tabSet1 = tabSet1;
    }

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public EliminarOfertaLicitacion() {
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
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
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
            log("EliminarOfertaLicitacion Initialization Failure", e);
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
        ep.getObjetos().add(ind++, new OfertaContratacion());
        ep.getObjetos().add(ind++, new Contratacion());
        ep.getObjetos().add(ind++, new Proveedor());
        ep.getObjetos().add(ind++, new ArrayList());// lista de renglones de la oferta de licitacion

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
       /* int ind = 0;
        OfertaLicitacion ofertaLicitacion = (OfertaLicitacion) this.obtenerObjetoDelElementoPila(ind++, OfertaLicitacion.class);
        Licitacion licitacion = (Licitacion) this.obtenerObjetoDelElementoPila(ind++, Licitacion.class);
        Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
        ArrayList listaOfertaRenglonLicitacion = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        Object fechaOferta = this.getTfFechaOferta().getText();
        Object importe = this.getTfImporte().getText();
        Object garantia = this.getTfGarantia().getText();
        Object plazo = this.getTfPlazo().getText();
        Object comentario = this.getTaComentariosOferta().getText();

        if (fechaOferta != null && fechaOferta != "") {
            ofertaLicitacion.setFechaOferta(Conversor.getFechaCortaDeString(fechaOferta.toString()));
        } else {
            ofertaLicitacion.setFechaOferta(null);
        }
        if (importe != null && importe != "") {
            ofertaLicitacion.setImporte(Conversor.getDoubleDeString(importe.toString()));
        } else {
            ofertaLicitacion.setImporte(null);
        }

        if (garantia != null && garantia != "") {
            ofertaLicitacion.setGarantia(garantia.toString());
        } else {
            ofertaLicitacion.setGarantia(null);
        }

        if (plazo != null && plazo != "") {
            ofertaLicitacion.setPlazo(plazo.toString());
        } else {
            ofertaLicitacion.setPlazo(null);
        }

        if (comentario != null && comentario != "") {
            ofertaLicitacion.setComentarios(comentario.toString());
        } else {
            ofertaLicitacion.setComentarios(null);
        }


        if (licitacion.getIdLicitacion() == -1) {
            licitacion = null;
        }
        ofertaLicitacion.setLicitacion(licitacion);


        if (proveedor.getIdProveedor() == -1) {
            proveedor = null;
        }
        ofertaLicitacion.setProveedor(proveedor);


        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, ofertaLicitacion);
        this.getElementoPila().getObjetos().set(ind++, licitacion);
        this.getElementoPila().getObjetos().set(ind++, proveedor);
        this.getElementoPila().getObjetos().set(ind++, listaOfertaRenglonLicitacion);*/
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.

        OfertaContratacion ofertaLicitacion = null;
        Contratacion contratacion = null;
        Proveedor proveedor = null;
        ArrayList listaOfertaRenglonLicitacion = new ArrayList();
        LineaOfertaContratacion ofertaRenglonLicitacion = null;

        if (this.getRequestBean1().getObjetoABM() != null) {
            ofertaLicitacion = (OfertaContratacion) this.getRequestBean1().getObjetoABM();
            listaOfertaRenglonLicitacion.addAll(ofertaLicitacion.getListaLineasOfertasContratacion());
            //contratacion = ofertaLicitacion.getActaApertura().getLicitacion();
            proveedor = ofertaLicitacion.getProveedor();
                        
            int ind = 0;
            this.getElementoPila().getObjetos().set(ind++, ofertaLicitacion);
            this.getElementoPila().getObjetos().set(ind++, contratacion);
            this.getElementoPila().getObjetos().set(ind++, proveedor);
            this.getElementoPila().getObjetos().set(ind++, listaOfertaRenglonLicitacion);
        }

        //  this.acomodarSeleccionado();     


        int ind = 0;
        ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(ind++, OfertaContratacion.class);
        contratacion = (Contratacion) this.obtenerObjetoDelElementoPila(ind++, Contratacion.class);
        proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
        listaOfertaRenglonLicitacion = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        if (contratacion != null && contratacion.getIdContratacion() != -1) {
            this.getTfLicitacion().setText(contratacion.toString());
        }
        if (proveedor != null && proveedor.getIdProveedor() != -1) {
            this.getTfProveedor().setText(proveedor.toString());
        }
//        this.getTfFechaOferta().setText(Conversor.getStringDeFechaCorta(ofertaLicitacion.getFechaOferta()));
//        this.getTfImporte().setText(Conversor.getStringDeDouble(ofertaLicitacion.getImporte()));
        this.getTfGarantia().setText(ofertaLicitacion.getGarantia());
        this.getTfPlazo().setText(ofertaLicitacion.getPlazo());
        this.getTaComentariosOferta().setText(ofertaLicitacion.getComentarios());

        this.getObjectListDataProvider().setList(listaOfertaRenglonLicitacion);
        this.setListaDelCommunication(listaOfertaRenglonLicitacion);
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpOfertaRenglonesLicitacion();
    }

    private List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaOfertaRenglonLicitacion();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaOfertaRenglonLicitacion(lista);
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
        long cantFilas = this.getTableRowGroup1().getRowCount();

        if (cantFilas > 0) {
            // si hay al menos una fila
            if (posicionGlobal.longValue() >= cantFilas) {
                // si elimine la ultima fila, me posiciono en la anterior
                posicionGlobal = new Long(cantFilas - 1);
            };

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
            rk = this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    // </editor-fold>

    // </editor-fold>    
      
    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {

                // CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[4];
                String[] nomNoVacios = new String[4];
                UIComponent[] flotantes = new UIComponent[1];
                String[] nomFlotantes = new String[1];
                UIComponent[] fecha = new UIComponent[1];
                String[] nomFecha = new String[1];

                int pos = 0;
                noVacios[pos] = this.getTfLicitacion();
                nomNoVacios[pos++] = "Licitaci\363n";
                noVacios[pos] = this.getTfProveedor();
                nomNoVacios[pos++] = "Proveedor";
                noVacios[pos] = this.getTfFechaOferta();
                nomNoVacios[pos++] = "Fecha Oferta";
                noVacios[pos] = this.getTfImporte();
                nomNoVacios[pos++] = "Importe";


                pos = 0;
                fecha[pos] = this.getTfFechaOferta();
                nomFecha[pos++] = "Fecha Oferta";

                pos = 0;
                flotantes[pos] = this.getTfImporte();
                nomFlotantes[pos++] = "Importe";

                v.noSonVacios(noVacios, nomNoVacios);
                v.formatoFechaValido(fecha, nomFecha);
                v.sonFlotantes(flotantes, nomFlotantes);
                v.sonPositivos(flotantes, nomFlotantes);


                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                this.guardarEstadoObjetosUsados();

                OfertaContratacion ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(0, OfertaContratacion.class);
                ArrayList listaOfertaRenglonLicitacion = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

                if (listaOfertaRenglonLicitacion == null || listaOfertaRenglonLicitacion.isEmpty()) {
                    warn("La Oferta debe tener al menos un Rengl\363n.");
                    return null;
                }

                this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(this.getSessionBean1().getLlave());
                this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().deleteOfertaLicitacion(ofertaLicitacion);

                //this.getRequestBean1().setRespuestaABM(ofertaLicitacion);

                this.setListaDelCommunication(null);

                info("La Oferta de Licitaci\363n se agreg\363 exitosamente.");
                retorno = this.prepararParaVolver(Constantes.ACCION_ELIMINAR);

            } catch (Exception ex) {
                if (ex instanceof TrascenderException) {
                    int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) {
                        retorno = null;
                    } else {
                        retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
                    }
                }
                log(CASO_NAVEGACION + "_GuardarError:", ex);
                error(NOMBRE_PAGINA + " - Guardar: " + ex.getMessage());
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
