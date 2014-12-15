/*
 * ModificarAuditoriaTributaria.java
 *
 * Created on 23 de octubre de 2006, 13:23
 * Copyright Trascender SRL
 */
package muni.saic.ABMAuditoriaTributaria;

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
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.Intimacion;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ValueChangeEvent;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class ModificarAuditoriaTributaria extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Modificar Auditoria Tributaria";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarAuditoriaTributaria";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    // private final String lnkAgregar = "AgregarPlanPagoRefinanciacion";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        /*
         * if (this.getListaDelCommunication() != null) {
         * this.getObjectListDataProvider().setList(this.getListaDelCommunication());
         * }
         *
         * if (this.getListaDelCommunicationTabla2() != null) {
         * this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());
         * }
         */

        if (this.getListaDelCommunicationTabla3() != null) {
            this.getObjectListDataProviderTabla3().setList(this.getListaDelCommunicationTabla3());
        }

        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoTasa().keySet().toArray(), "cap");
        ddTipoObligacionDefaultOptions.setOptions(op);

        //numberConverter1.setPattern("$ #,###,##0.000");
        numberConverter1.setPattern("$ #,##0.00");
        dateTimeConverter.setTimeZone(null);
        dateTimeConverter.setPattern("dd/MM/yyyy");
        dateTimeConverter.setTimeStyle("full");

    }
    private static long idIntimacion = -1;
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
    private StaticText stSeparador = new StaticText();

    public StaticText getStSeparador() {
        return stSeparador;
    }

    public void setStSeparador(StaticText stSeparador) {
        this.stSeparador = stSeparador;
    }
    private TextField tfContribuyente = new TextField();

    public TextField getTfContribuyente() {
        return tfContribuyente;
    }

    public void setTfContribuyente(TextField tf) {
        this.tfContribuyente = tf;
    }
    private TextField tfFecha = new TextField();

    public TextField getTfFecha() {
        return tfFecha;
    }

    public void setTfFecha(TextField tfFecha) {
        this.tfFecha = tfFecha;
    }
    private Label lblFecha = new Label();

    public Label getLblFecha() {
        return lblFecha;
    }

    public void setLblFecha(Label lblFecha) {
        this.lblFecha = lblFecha;
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
    private NumberConverter numberConverter1 = new NumberConverter();

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter numberConverter1) {
        this.numberConverter1 = numberConverter1;
    }
    private DateTimeConverter dateTimeConverter = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter() {
        return dateTimeConverter;
    }

    public void setDateTimeConverter(DateTimeConverter dateTimeConverter) {
        this.dateTimeConverter = dateTimeConverter;
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
    private ObjectListDataProvider ldpObligaciones = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpObligaciones() {
        return ldpObligaciones;
    }

    public void setLdpObligaciones(ObjectListDataProvider oldp) {
        this.ldpObligaciones = oldp;
    }
    private ObjectListDataProvider ldpIntimaciones = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpIntimaciones() {
        return ldpIntimaciones;
    }

    public void setLdpIntimaciones(ObjectListDataProvider ldpIntimaciones) {
        this.ldpIntimaciones = ldpIntimaciones;
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
    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }
    private Checkbox checkbox4 = new Checkbox();

    public Checkbox getCheckbox4() {
        return checkbox4;
    }

    public void setCheckbox4(Checkbox c) {
        this.checkbox4 = c;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private DropDown ddTipoObligacion = new DropDown();

    public DropDown getDdTipoObligacion() {
        return ddTipoObligacion;
    }

    public void setDdTipoObligacion(DropDown dd) {
        this.ddTipoObligacion = dd;
    }
    private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
        return ddTipoObligacionDefaultOptions;
    }

    public void setDdTipoObligacionDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddTipoObligacionDefaultOptions = ssol;
    }
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private Button btnArmarPlanDePago = new Button();

    public Button getBtnArmarPlanDePago() {
        return btnArmarPlanDePago;
    }

    public void setBtnArmarPlanDePago(Button b) {
        this.btnArmarPlanDePago = b;
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
    private Table table3 = new Table();

    public Table getTable3() {
        return table3;
    }

    public void setTable3(Table t) {
        this.table3 = t;
    }
    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup trg) {
        this.tableRowGroup2 = trg;
    }
    private TableRowGroup tableRowGroup3 = new TableRowGroup();

    public TableRowGroup getTableRowGroup3() {
        return tableRowGroup3;
    }

    public void setTableRowGroup3(TableRowGroup trg) {
        this.tableRowGroup3 = trg;
    }
    private PanelGroup groupPanel2 = new PanelGroup();

    public PanelGroup getGroupPanel2() {
        return groupPanel2;
    }

    public void setGroupPanel2(PanelGroup pg) {
        this.groupPanel2 = pg;
    }
    private PanelGroup groupPanel3 = new PanelGroup();

    public PanelGroup getGroupPanel3() {
        return groupPanel3;
    }

    public void setGroupPanel3(PanelGroup pg) {
        this.groupPanel3 = pg;
    }
    private Button btnAgregarIntimacion = new Button();

    public Button getBtnAgregarIntimacion() {
        return btnAgregarIntimacion;
    }

    public void setBtnAgregarIntimacion(Button btnAgregarIntimacion) {
        this.btnAgregarIntimacion = btnAgregarIntimacion;
    }
    private Button btnModificarIntimacion = new Button();

    public Button getBtnModificarIntimacion() {
        return btnModificarIntimacion;
    }

    public void setBtnModificarIntimacion(Button btnModificarIntimacion) {
        this.btnModificarIntimacion = btnModificarIntimacion;
    }

    public Button getBtnQuitar() {
        return btnQuitar;
    }

    public void setBtnQuitar(Button btnQuitar) {
        this.btnQuitar = btnQuitar;
    }
    private Button btnQuitar = new Button();
    private Button btnGuardar = new Button();
    private Button btnCancelar = new Button();

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(Button btnGuardar) {
        this.btnGuardar = btnGuardar;
    }
    private ObjectListDataProvider ldpPeriodosAdeudados = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpPeriodosAdeudados() {
        return ldpPeriodosAdeudados;
    }

    public void setLdpPeriodosAdeudados(ObjectListDataProvider oldp) {
        this.ldpPeriodosAdeudados = oldp;
    }
    private TableColumn tableColumn9 = new TableColumn();

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
    private TableColumn tableColumn10 = new TableColumn();
    private TableColumn tableColumn16 = new TableColumn();
    private TableColumn tableColumn17 = new TableColumn();
    private TableColumn tableColumn18 = new TableColumn();
    private TableColumn tableColumn19 = new TableColumn();
    private TableColumn tableColumn20 = new TableColumn();
    private TableColumn tableColumn21 = new TableColumn();
    private TableColumn tableColumn22 = new TableColumn();
    private TableColumn tableColumn23 = new TableColumn();

    public TableColumn getTableColumn17() {
        return tableColumn17;
    }

    public void setTableColumn17(TableColumn tableColumn17) {
        this.tableColumn17 = tableColumn17;
    }

    public TableColumn getTableColumn18() {
        return tableColumn18;
    }

    public void setTableColumn18(TableColumn tableColumn18) {
        this.tableColumn18 = tableColumn18;
    }

    public TableColumn getTableColumn19() {
        return tableColumn19;
    }

    public void setTableColumn19(TableColumn tableColumn19) {
        this.tableColumn19 = tableColumn19;
    }

    public TableColumn getTableColumn20() {
        return tableColumn20;
    }

    public void setTableColumn20(TableColumn tableColumn20) {
        this.tableColumn20 = tableColumn20;
    }

    public TableColumn getTableColumn21() {
        return tableColumn21;
    }

    public void setTableColumn21(TableColumn tableColumn21) {
        this.tableColumn21 = tableColumn21;
    }

    public TableColumn getTableColumn22() {
        return tableColumn22;
    }

    public void setTableColumn22(TableColumn tableColumn22) {
        this.tableColumn22 = tableColumn22;
    }

    public TableColumn getTableColumn23() {
        return tableColumn23;
    }

    public void setTableColumn23(TableColumn tableColumn23) {
        this.tableColumn23 = tableColumn23;
    }

    public TableColumn getTableColumn10() {
        return tableColumn10;
    }

    public void setTableColumn10(TableColumn tc) {
        this.tableColumn10 = tc;
    }
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }
    private TableColumn tableColumn11 = new TableColumn();

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
    private StaticText staticText11 = new StaticText();
    private StaticText staticText12 = new StaticText();
    private StaticText staticText13 = new StaticText();
    private StaticText staticText14 = new StaticText();
    private StaticText staticText15 = new StaticText();
    private StaticText staticText16 = new StaticText();
    private StaticText staticText17 = new StaticText();
    private StaticText staticText18 = new StaticText();

    public StaticText getStaticText18() {
        return staticText18;
    }

    public void setStaticText18(StaticText staticText18) {
        this.staticText18 = staticText18;
    }

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText staticText12) {
        this.staticText12 = staticText12;
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

    public StaticText getStaticText15() {
        return staticText15;
    }

    public void setStaticText15(StaticText staticText15) {
        this.staticText15 = staticText15;
    }

    public StaticText getStaticText16() {
        return staticText16;
    }

    public void setStaticText16(StaticText staticText16) {
        this.staticText16 = staticText16;
    }

    public StaticText getStaticText17() {
        return staticText17;
    }

    public void setStaticText17(StaticText staticText17) {
        this.staticText17 = staticText17;
    }

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText staticText11) {
        this.staticText11 = staticText11;
    }

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tableColumn16) {
        this.tableColumn16 = tableColumn16;
    }

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }
    private TableColumn tableColumn15 = new TableColumn();

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tc) {
        this.tableColumn15 = tc;
    }
    private Checkbox checkbox1 = new Checkbox();

    public Checkbox getCheckbox1() {
        return checkbox1;
    }

    public void setCheckbox1(Checkbox c) {
        this.checkbox1 = c;
    }
    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
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
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    private RadioButton radioButton1 = new RadioButton();

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton radioButton1) {
        this.radioButton1 = radioButton1;
    }

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ModificarAuditoriaTributaria() {
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
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
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
            log("ModificarAuditoriaTributaria Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

        tablePhaseListener = this.getTableSelectPhaseListener();
        tablePhaseListenerTabla2 = this.getTableSelectPhaseListenerTabla2();
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
        boolean cargarTabla = false;

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

                cargarTabla = true;
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

        //   if (!recarga) {
        this.mostrarEstadoObjetosUsados();
        //   }

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
        ep.getObjetos().add(ind++, new AuditoriaTributaria()); //0
        ep.getObjetos().add(ind++, null); // persona //1
        ep.getObjetos().add(ind++, null); // PlantillaObligacion.TipoObligacion //2
        ep.getObjetos().add(ind++, new ArrayList()); // lista de obligaciones (Obligacion) //3
        ep.getObjetos().add(ind++, new HashSet()); // lista de periodos adeudados (......) //4
        ep.getObjetos().add(ind++, new HashSet()); // lista de intimaciones //5
        ep.getObjetos().add(ind++, new HashSet()); // lista de periodos adeudados seleccionados(......) //6

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        AuditoriaTributaria auditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(ind++, AuditoriaTributaria.class);
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        TipoObligacion tipoObligacion = (TipoObligacion) this.obtenerObjetoDelElementoPila(ind++, TipoObligacion.class);
        ArrayList obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        Set periodosAdeudados = (Set) this.obtenerObjetoDelElementoPila(ind++, Set.class);
        Set listaIntimaciones = (Set) this.obtenerObjetoDelElementoPila(ind++, Set.class);
        Set periodosAdeudadosSeleccionados = (Set) this.obtenerObjetoDelElementoPila(ind++, Set.class);

        tipoObligacion = this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(ddTipoObligacion.getSelected());
        Object fechaCreacion = this.getTfFecha().getText();

        auditoriaTributaria.setTipoObligacion(tipoObligacion);

        if (persona != null && persona.getIdPersona() == -1) {
            persona = null;
        }
        auditoriaTributaria.setContribuyente(persona);

        if (fechaCreacion != null && fechaCreacion != "") {
            auditoriaTributaria.setFechaCreacion(Conversor.getFechaCortaDeString(fechaCreacion.toString()));
        } else {
            auditoriaTributaria.setFechaCreacion(null);
        }

        auditoriaTributaria.setListaIntimaciones(new HashSet(listaIntimaciones));

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, auditoriaTributaria);
        this.getElementoPila().getObjetos().set(ind++, persona);
        this.getElementoPila().getObjetos().set(ind++, tipoObligacion);
        this.getElementoPila().getObjetos().set(ind++, obligaciones);
        this.getElementoPila().getObjetos().set(ind++, periodosAdeudados);
        this.getElementoPila().getObjetos().set(ind++, listaIntimaciones);
        this.getElementoPila().getObjetos().set(ind++, periodosAdeudadosSeleccionados);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.

        int ind = 0;
        Persona persona = null;
        TipoObligacion tipoObligacion = null;
        ArrayList obligaciones = null;
        Set periodosAdeudados = null;
        Set listaIntimaciones = new HashSet();
        AuditoriaTributaria auditoriaTributaria = null;
        Intimacion intimacion = null;


        if (this.getRequestBean1().getObjetoABM() != null) {
            auditoriaTributaria = (AuditoriaTributaria) this.getRequestBean1().getObjetoABM();
            obligaciones = null;
            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, auditoriaTributaria);
            this.getElementoPila().getObjetos().set(ind++, auditoriaTributaria.getContribuyente());
            this.getElementoPila().getObjetos().set(ind++, auditoriaTributaria.getTipoObligacion());
            this.getElementoPila().getObjetos().set(ind++, obligaciones);
            this.getElementoPila().getObjetos().set(ind++, auditoriaTributaria.getListaRegistroDeuda());
            this.getElementoPila().getObjetos().set(ind++, auditoriaTributaria.getListaIntimaciones());
        }

        //this.acomodarSeleccionado();

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            //auditoriaTributaria = (AuditoriaTributaria)this.obtenerObjetoDelElementoPila(ind++, AuditoriaTributaria.class);

            if (seleccionado instanceof Intimacion) {
                persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);
                listaIntimaciones = (Set) this.obtenerObjetoDelElementoPila(5, Set.class);

                intimacion = (Intimacion) seleccionado;
                intimacion.setContribuyente(persona);
                if (intimacion.getIdIntimacion() == -1) {
                    intimacion.setIdIntimacion(idIntimacion--);
                }
                listaIntimaciones.add(intimacion);
                this.getElementoPila().getObjetos().set(5, listaIntimaciones);
                this.getRequestBean1().setObjetoSeleccion(null);

            }
        }

        ind = 0;
        auditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(ind++, AuditoriaTributaria.class);
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        tipoObligacion = (TipoObligacion) this.obtenerObjetoDelElementoPila(ind++, TipoObligacion.class);
        obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        periodosAdeudados = (Set) this.obtenerObjetoDelElementoPila(ind++, Set.class);
        listaIntimaciones = (Set) this.obtenerObjetoDelElementoPila(ind++, Set.class);

        if (persona != null) {
            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
                obligaciones = new ArrayList(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligaciones(persona, tipoObligacion, null));
                //obligaciones.addAll( this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligaciones(persona, tipoObligacion, null) );
                tablePhaseListener.clear();
                tablePhaseListenerTabla2.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
                error(NOMBRE_PAGINA + ": No se pudo recuperar la lista de Obligaciones.");
            }
        }

        if (persona != null) {
            this.getTfContribuyente().setText(persona.toString());
        }
        if (tipoObligacion != null) {
            this.getDdTipoObligacion().setSelected(Util.getEnumNameFromString(String.valueOf(tipoObligacion)));
            this.getDdTipoObligacionDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(tipoObligacion)));
        }

        this.getTfFecha().setText(Conversor.getStringDeFechaCorta(auditoriaTributaria.getFechaCreacion()));

        this.setListaDelCommunication(obligaciones);
        this.getObjectListDataProvider().setList(obligaciones);

        ArrayList listaPeriodosAdeudados = new ArrayList();
        listaPeriodosAdeudados.addAll(periodosAdeudados);
        this.setListaDelCommunicationTabla2(listaPeriodosAdeudados);
        this.getObjectListDataProviderTabla2().setList(listaPeriodosAdeudados);

        ArrayList listIntimaciones = new ArrayList();
        listIntimaciones.addAll(listaIntimaciones);
        this.setListaDelCommunicationTabla3(listIntimaciones);
        this.getObjectListDataProviderTabla3().setList(listIntimaciones);

        if (this.getRequestBean1().getObjetoSeleccion() != null && this.getRequestBean1().getObjetoSeleccion() instanceof Persona) {
            this.limpiarTabla2();
        }
    }

    // PARA TABLA 1
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpObligaciones();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getListaObligacionesRefinanciacion();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationExcepcionesBean().setListaObligacionesRefinanciacion(lista);
    }

    private TableSelectPhaseListener getTableSelectPhaseListener() {
        // CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getTablePhaseListenerObligacionesRefinanciacion();
    }

    // PARA TABLA 2
    private ObjectListDataProvider getObjectListDataProviderTabla2() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpPeriodosAdeudados();
    }

    private ArrayList getListaDelCommunicationTabla2() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getListaPeriodosAdeudadosRefinanciacion();
    }

    private void setListaDelCommunicationTabla2(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationExcepcionesBean().setListaPeriodosAdeudadosRefinanciacion(lista);
    }

    private TableSelectPhaseListener getTableSelectPhaseListenerTabla2() {
        // CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getTablePhaseListenerPeriodosAdeudadosRefinanciacion();
    }

    // PARA TABLA 3
    private ObjectListDataProvider getObjectListDataProviderTabla3() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpIntimaciones();
    }

    private ArrayList getListaDelCommunicationTabla3() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationSAICBean().getListaIntimacionesAuditoriaTributaria();
    }

    private void setListaDelCommunicationTabla3(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationSAICBean().setListaIntimacionesAuditoriaTributaria(lista);
    }

//    private void actualizarTablaObligaciones() throws Exception {
//        // CAMBIAR:
//        ind = 0;
//        persona           = (Persona)   this.obtenerObjetoDelElementoPila(ind++, Persona.class);
//        tipoObligacion    = (PlantillaObligacion.TipoObligacion) this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.TipoObligacion.class);
//        obligaciones      = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
//
//        try {
//            this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
//            obligaciones = new ArrayList(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligaciones(persona, tipoObligacion, null));
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//            error(NOMBRE_PAGINA + ": No se pudo recuperar la lista de Obligaciones.");
//        }
//    }
//    private void refrescarTabla() throws Exception {
//        // CAMBIAR: Segun objeto de busqueda.
//
//        Rol rol = (Rol) this.obtenerObjetoDelElementoPila(0, Rol.class);
//        if (rol != null && rol.getIdRol() == -1) rol = null;
//
//        // CAMBIAR: Utilizar el EJBClient adecuado.
//        this.getComunicationBean().getRemoteSystemRol().setLlave(this.getSessionBean1().getLlave());
//        this.setListaDelCommunication((ArrayList) this.getComunicationBean().getRemoteSystemRol().getListaPermisosPorRol(rol));
//        this.getObjectListDataProvider().setList(this.getListaDelCommunication());
//    }
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

    private void limpiarTabla2() {
        this.getObjectListDataProviderTabla2().getList().clear();
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
    //   <editor-fold defaultstate = "collapsed" desc  = "Metodos para seleccionar RaddioButton" >

    public String getCurrentRow() {
        return tableRowGroup3.getRowKey().getRowId();
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
    /*
     * public void seleccionarFila(Long posicionGlobal) { long cantFilas =
     * this.getTableRowGroup1().getRowCount();
     *
     * if (cantFilas > 0) { // si hay al menos una fila if
     * (posicionGlobal.longValue() >= cantFilas) { // si elimine la ultima fila,
     * me posiciono en la anterior posicionGlobal = new Long(cantFilas - 1); };
     *
     * int index =
     * this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
     * this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
     * lastSelected = new Long(index).toString(); } }
     */

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
            rk = this.getObjectListDataProviderTabla3().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodos para trabajar con checkboxes de seleccion">
    // TABLA 1
    private TableSelectPhaseListener tablePhaseListener;

    public void setSelected(Object object) {
        RowKey rowKey = tableRowGroup1.getRowKey();
        if (rowKey != null) {
            tablePhaseListener.setSelected(rowKey, object);
        }
    }

    public Object getSelected() {
        RowKey rowKey = tableRowGroup1.getRowKey();
        return tablePhaseListener.getSelected(rowKey);
    }

    public Object getSelectedValue() {
        RowKey rowKey = tableRowGroup1.getRowKey();
        return (rowKey != null) ? rowKey.getRowId() : null;
    }

    public boolean isCurrentRowSelected() {
        RowKey rowKey = tableRowGroup1.getRowKey();
        return tablePhaseListener.isSelected(rowKey);
    }
    // TABLA 2
    private TableSelectPhaseListener tablePhaseListenerTabla2;

    public void setSelectedTabla2(Object object) {
        RowKey rowKey = tableRowGroup2.getRowKey();
        if (rowKey != null) {
            tablePhaseListenerTabla2.setSelected(rowKey, object);
        }
    }

    public Object getSelectedTabla2() {
        RowKey rowKey = tableRowGroup2.getRowKey();
        return tablePhaseListenerTabla2.getSelected(rowKey);
    }

    public Object getSelectedValueTabla2() {
        RowKey rowKey = tableRowGroup2.getRowKey();
        return (rowKey != null) ? rowKey.getRowId() : null;
    }

    public boolean isCurrentRowSelectedTabla2() {
        RowKey rowKey = tableRowGroup2.getRowKey();
        return tablePhaseListenerTabla2.isSelected(rowKey);
    }

    // </editor-fold>
    // </editor-fold>
    public void ddTipoObligacion_processValueChange(ValueChangeEvent vce) {
        this.getElementoPila().getObjetos().set(1, vce.getNewValue());
        return;
    }

    private void actualizarTablaPeriodosAdeudados() throws Exception {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;
            this.guardarEstadoObjetosUsados();

            ArrayList obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
            ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);

            // APLICAR LOGICA AQUI...
            try {

                // Inicializo el Array de objetos seleccionados
                if (this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
                    this.getRequestBean1().getObjetosSeleccionMultiple().clear();
                } else {
                    this.getRequestBean1().setObjetosSeleccionMultiple(new ArrayList());
                }

                RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();

                for (int i = 0; i < selectedRowKeys.length; i++) {
                    String rowId = selectedRowKeys[i].getRowId();
                    //RowKey rowKey = this.getObjectListDataProvider().getRowKey(rowId);
                    Object obj = this.getObjectListDataProvider().getObjects()[Integer.parseInt(rowId)];
                    this.getRequestBean1().getObjetosSeleccionMultiple().add(obj);
                }

                // ariel - descomentar cuando este listo
                //periodosAdeudados = new ArrayList( this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findPeriodosAdeudados(this.getRequestBean1().getObjetosSeleccionMultiple()) );

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_SeleccionarMultiplesObjetosError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar M\372ltiples Objetos: " + ex.getMessage());
            }

            //if (!this.getRequestBean1().getObjetosSeleccionMultiple().isEmpty()) retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);

        } else {
            retorno = this.prepararCaducidad();
        }

        return;
    }

    public String btnAgregarIntimacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);
        Set listaIntimaciones = (Set) this.obtenerObjetoDelElementoPila(5, Set.class);

        if (ultimo) {
            if (persona != null) {

                this.guardarEstadoObjetosUsados();
                //   this.getRequestBean1().setTipoSeleccion("FAMILIAR");
                this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
                for (Iterator it = listaIntimaciones.iterator(); it.hasNext();) {
                    Intimacion intimacion = (Intimacion) it.next();
                    if (intimacion.getEstado().equals(Intimacion.EstadoIntimacion.VIGENTE)) {
                        warn("Existe una intimación Vigente.");
                        return null;
                    }
                }
                // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
                retorno = "AgregarIntimacion";
            } else {
                warn("Debe seleccionar un Contribuyente para agregar Intimaciones.");
                return null;
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnModificarIntimacion_action() {

        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        AuditoriaTributaria auditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(0, AuditoriaTributaria.class);
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);
        ArrayList listaIntimaciones = (ArrayList) this.obtenerObjetoDelElementoPila(5, ArrayList.class);

        if (ultimo) {

            if (persona != null) {
                RowKey rk = null;

                try {
                    rk = this.getSeleccionado();
                    if (rk != null) {
                        int index = getNroFila(rk.toString());
                        // CAMBIAR: Utilizar el ListDataProvider adecuado.

                        Object obj = this.getObjectListDataProviderTabla3().getObjects()[index];
                        Intimacion intimacion = (Intimacion) obj;
                        for (Iterator it = listaIntimaciones.iterator(); it.hasNext();) {
                            Intimacion locIntimacion = (Intimacion) it.next();
                            if (locIntimacion.getEstado().equals(Intimacion.EstadoIntimacion.PRESCRIPTA) || locIntimacion.getEstado().equals(Intimacion.EstadoIntimacion.PRESCRIPTA_SIN_FECHA_RECEPCION)) {
                                warn("No pueden modificarse Intimaciones Prescriptas.");
                                return null;
                            }
                        }

                        auditoriaTributaria.getListaIntimaciones().remove(intimacion);

                        this.getRequestBean1().setObjetoABM(intimacion);
                        this.setRowKeySeleccionado(this.getSeleccionado());
                        this.getElementoPila().getObjetos().set(0, auditoriaTributaria);
                    }
                } catch (Exception ex) {
                    log(CASO_NAVEGACION + "_ModificarError:", ex);
                    error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
                }

                this.guardarEstadoObjetosUsados();
                this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

                // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
                if (rk != null) {

                    retorno = "ModificarIntimacion";
                }
            } else {
                warn("Debe seleccionar un Contribuyente.");
                return null;
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;

    }

    public String btnQuitar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        AuditoriaTributaria auditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(0, AuditoriaTributaria.class);

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProviderTabla3().getObjects()[index];

                    auditoriaTributaria.getListaIntimaciones().remove((Intimacion) obj);
                    this.getElementoPila().getObjetos().set(0, auditoriaTributaria);

                    //this.getObjectListDataProvider().commitChanges();
                    // VER SI ES NEC EL REFRESCAR SIGUIENTE
                    //  this.refrescarTablaIntimaciones();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());

        if (ultimo) {

            try {



//                // CAMBIAR: Validar los campos necesarios.
//                Validador v = new Validador();
//                UIComponent[] locNoVacios = new UIComponent[3];
//                String[] locNomNoVacios = new String[3];
//                UIComponent[] esFlotante = new UIComponent[1];
//                String[] nomEsFlotante = new String[1];
//
//                int pos = 0;
//                locNoVacios[pos] = this.getTfBien();
//                locNomNoVacios[pos++] = "Bien";
//                locNoVacios[pos] = this.getTfCantidad();
//                locNomNoVacios[pos++] = "Cantidad";
//                locNoVacios[pos] = this.getTfDeposito();
//                locNomNoVacios[pos++] = "Dep\363sito";
//
//                pos = 0;
//                esFlotante[pos] = this.getTfCantidad();
//                nomEsFlotante[pos++] = "Cantidad";
//
//                v.noSonVacios(locNoVacios, locNomNoVacios);
//                v.sonFlotantes(esFlotante, nomEsFlotante);
//                
//                if (this.getTfCantidad().getText() != null && this.getTfCantidad().isValid()){
//                    if( new Double(this.getTfCantidad().getText().toString()).compareTo(new Double(0)) <= 0){
//                        v.getErrores().add("La cantidad debe ser mayor a 0.");
//                    }
//                }

//                if (v.getErrores().size() > 0) {
//                    error("Existen Errores:");
//                    for (int i = 0; i < v.getErrores().size(); i++) {
//                        warn(v.getErrores().toArray()[i].toString());
//                    }
//                    return null;
//                }

                this.guardarEstadoObjetosUsados();

                AuditoriaTributaria auditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(0, AuditoriaTributaria.class);


                //////////pasar a -1 los id de los registros nuevos/////////
                for (Iterator it = auditoriaTributaria.getListaIntimaciones().iterator(); it.hasNext();) {
                    Intimacion intimacion = (Intimacion) it.next();
                    if (intimacion.getIdIntimacion() < 0) {
                        intimacion.setIdIntimacion(-1);
                    }
                }
                //////////////////////////////////////

                this.getCommunicationSAICBean().getRemoteSystemAuditoriaTributaria().setLlave(this.getSessionBean1().getLlave());
                auditoriaTributaria = (AuditoriaTributaria) this.getCommunicationSAICBean().getRemoteSystemAuditoriaTributaria().updateAuditoriaTributaria(auditoriaTributaria);

                this.getRequestBean1().setRespuestaABM(auditoriaTributaria);

                info("La Auditoria Tributaria se Modific\363 exitosamente.");

                retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);
            } catch (Exception ex) {

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
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
}
