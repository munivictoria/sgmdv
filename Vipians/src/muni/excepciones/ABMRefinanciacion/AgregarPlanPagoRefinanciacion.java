/*
 * AgregarPlanPagoRefinanciacion.java
 *
 * Created on 2 de noviembre de 2006, 11:21
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
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.refinanciacion.DocumentoRef;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda.TipoDocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.RegCancelacionPorRefinanciacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
//import com.trascender.saic.util.enumerations.DocGeneradorDeudaTipo;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class AgregarPlanPagoRefinanciacion extends AbstractPageBean {

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
    }    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Armar Plan de Pago";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarPlanPagoRefinanciacion";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {

        dateTimeConverter1.setPattern("dd/MM/yyyy");

        numberConverter1.setPattern("$ #,##0.00");
        numberConverter1.setMinIntegerDigits(1);//2
        numberConverter1.setMaxIntegerDigits(40);
        numberConverter1.setMaxFractionDigits(3);//2

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
    private TextField tfPeriodosSeleccionados = new TextField();

    public TextField getTfPeriodosSeleccionados() {
        return tfPeriodosSeleccionados;
    }

    public void setTfPeriodosSeleccionados(TextField tf) {
        this.tfPeriodosSeleccionados = tf;
    }
    private Label lblPeriodosSeleccionados = new Label();

    public Label getLblPeriodosSeleccionados() {
        return lblPeriodosSeleccionados;
    }

    public void setLblPeriodosSeleccionados(Label l) {
        this.lblPeriodosSeleccionados = l;
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
    private Label lblImporte = new Label();

    public Label getLblImporte() {
        return lblImporte;
    }

    public void setLblImporte(Label l) {
        this.lblImporte = l;
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
    
    private TextField tfImporte = new TextField();

    public TextField getTfImporte() {
        return tfImporte;
    }

    public void setTfImporte(TextField tf) {
        this.tfImporte = tf;
    }
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private ObjectListDataProvider ldpCuotasRefinanciacion = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpCuotasRefinanciacion() {
        return ldpCuotasRefinanciacion;
    }

    public void setLdpCuotasRefinanciacion(ObjectListDataProvider oldp) {
        this.ldpCuotasRefinanciacion = oldp;
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
    private Button btnGenerarCuotas = new Button();

    public Button getBtnGenerarCuotas() {
        return btnGenerarCuotas;
    }

    public void setBtnGenerarCuotas(Button b) {
        this.btnGenerarCuotas = b;
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
    private TextField tfImporteACondonar = new TextField();

    public TextField getTfImporteACondonar() {
        return tfImporteACondonar;
    }

    public void setTfImporteACondonar(TextField tf) {
        this.tfImporteACondonar = tf;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private TextField tfImporteCondonado = new TextField();

    public TextField getTfImporteCondonado() {
        return tfImporteCondonado;
    }

    public void setTfImporteCondonado(TextField tf) {
        this.tfImporteCondonado = tf;
    }
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    private Label lblIntereses = new Label();

    public Label getLblIntereses() {
        return lblIntereses;
    }

    public void setLblIntereses(Label l) {
        this.lblIntereses = l;
    }
    private Label lblRecargos = new Label();

    public Label getLblRecargos() {
        return lblRecargos;
    }

    public void setLblRecargos(Label l) {
        this.lblRecargos = l;
    }
    private TextField tfIntereses = new TextField();

    public TextField getTfIntereses() {
        return tfIntereses;
    }

    public void setTfIntereses(TextField tf) {
        this.tfIntereses = tf;
    }
    private TextField tfRecargos = new TextField();

    public TextField getTfRecargos() {
        return tfRecargos;
    }

    public void setTfRecargos(TextField tf) {
        this.tfRecargos = tf;
    }
    private TextField tfInteresesACondonar = new TextField();

    public TextField getTfInteresesACondonar() {
        return tfInteresesACondonar;
    }

    public void setTfInteresesACondonar(TextField tf) {
        this.tfInteresesACondonar = tf;
    }
    private TextField tfRecargosACondonar = new TextField();

    public TextField getTfRecargosACondonar() {
        return tfRecargosACondonar;
    }

    public void setTfRecargosACondonar(TextField tf) {
        this.tfRecargosACondonar = tf;
    }
    private TextField tfInteresesCondonado = new TextField();

    public TextField getTfInteresesCondonado() {
        return tfInteresesCondonado;
    }

    public void setTfInteresesCondonado(TextField tf) {
        this.tfInteresesCondonado = tf;
    }
    private TextField tfRecargosCondonado = new TextField();

    public TextField getTfRecargosCondonado() {
        return tfRecargosCondonado;
    }

    public void setTfRecargosCondonado(TextField tf) {
        this.tfRecargosCondonado = tf;
    }
    private Label lblTotalCondonado = new Label();

    public Label getLblTotalCondonado() {
        return lblTotalCondonado;
    }

    public void setLblTotalCondonado(Label l) {
        this.lblTotalCondonado = l;
    }
    private TextField tfTotalCondonado = new TextField();

    public TextField getTfTotalCondonado() {
        return tfTotalCondonado;
    }

    public void setTfTotalCondonado(TextField tf) {
        this.tfTotalCondonado = tf;
    }
    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private RadioButton rbCondonarImportePorc = new RadioButton();

    public RadioButton getRbCondonarImportePorc() {
        return rbCondonarImportePorc;
    }

    public void setRbCondonarImportePorc(RadioButton rb) {
        this.rbCondonarImportePorc = rb;
    }
    private RadioButton rbCondonarImporteFijo = new RadioButton();

    public RadioButton getRbCondonarImporteFijo() {
        return rbCondonarImporteFijo;
    }

    public void setRbCondonarImporteFijo(RadioButton rb) {
        this.rbCondonarImporteFijo = rb;
    }
    private Label label9 = new Label();

    public Label getLabel9() {
        return label9;
    }

    public void setLabel9(Label l) {
        this.label9 = l;
    }
    private Label label10 = new Label();

    public Label getLabel10() {
        return label10;
    }

    public void setLabel10(Label l) {
        this.label10 = l;
    }
    private RadioButton rbCondonarInteresesPorc = new RadioButton();

    public RadioButton getRbCondonarInteresesPorc() {
        return rbCondonarInteresesPorc;
    }

    public void setRbCondonarInteresesPorc(RadioButton rb) {
        this.rbCondonarInteresesPorc = rb;
    }
    private Label label11 = new Label();

    public Label getLabel11() {
        return label11;
    }

    public void setLabel11(Label l) {
        this.label11 = l;
    }
    private RadioButton rbCondonarInteresesFijo = new RadioButton();

    public RadioButton getRbCondonarInteresesFijo() {
        return rbCondonarInteresesFijo;
    }

    public void setRbCondonarInteresesFijo(RadioButton rb) {
        this.rbCondonarInteresesFijo = rb;
    }
    private Label label12 = new Label();

    public Label getLabel12() {
        return label12;
    }

    public void setLabel12(Label l) {
        this.label12 = l;
    }
    private RadioButton rbCondonarRecargosPorc = new RadioButton();

    public RadioButton getRbCondonarRecargosPorc() {
        return rbCondonarRecargosPorc;
    }

    public void setRbCondonarRecargosPorc(RadioButton rb) {
        this.rbCondonarRecargosPorc = rb;
    }
    private Label label13 = new Label();

    public Label getLabel13() {
        return label13;
    }

    public void setLabel13(Label l) {
        this.label13 = l;
    }
    private RadioButton rbCondonarRecargosFijo = new RadioButton();

    public RadioButton getRbCondonarRecargosFijo() {
        return rbCondonarRecargosFijo;
    }

    public void setRbCondonarRecargosFijo(RadioButton rb) {
        this.rbCondonarRecargosFijo = rb;
    }
    private Label label14 = new Label();

    public Label getLabel14() {
        return label14;
    }

    public void setLabel14(Label l) {
        this.label14 = l;
    }
    private Label lblTasaNominalAnual = new Label();

    public Label getLblTasaNominalAnual() {
        return lblTasaNominalAnual;
    }

    public void setLblTasaNominalAnual(Label l) {
        this.lblTasaNominalAnual = l;
    }
    private Label lblCantidadCuotas = new Label();

    public Label getLblCantidadCuotas() {
        return lblCantidadCuotas;
    }

    public void setLblCantidadCuotas(Label l) {
        this.lblCantidadCuotas = l;
    }
    private TextField tfTasaNominalAnual = new TextField();

    public TextField getTfTasaNominalAnual() {
        return tfTasaNominalAnual;
    }

    public void setTfTasaNominalAnual(TextField tf) {
        this.tfTasaNominalAnual = tf;
    }
    private TextField tfCantidadCuotas = new TextField();

    public TextField getTfCantidadCuotas() {
        return tfCantidadCuotas;
    }

    public void setTfCantidadCuotas(TextField tf) {
        this.tfCantidadCuotas = tf;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    private Label lblMultas = new Label();

    public Label getLblMultas() {
        return lblMultas;
    }

    public void setLblMultas(Label l) {
        this.lblMultas = l;
    }
    private TextField tfMultas = new TextField();

    public TextField getTfMultas() {
        return tfMultas;
    }

    public void setTfMultas(TextField tf) {
        this.tfMultas = tf;
    }
    private TextField tfMultasACondonar = new TextField();

    public TextField getTfMultasACondonar() {
        return tfMultasACondonar;
    }

    public void setTfMultasACondonar(TextField tf) {
        this.tfMultasACondonar = tf;
    }
    private RadioButton rbCondonarMultasPorc = new RadioButton();

    public RadioButton getRbCondonarMultasPorc() {
        return rbCondonarMultasPorc;
    }

    public void setRbCondonarMultasPorc(RadioButton rb) {
        this.rbCondonarMultasPorc = rb;
    }
    private Label label22 = new Label();

    public Label getLabel22() {
        return label22;
    }

    public void setLabel22(Label l) {
        this.label22 = l;
    }
    private RadioButton rbCondonarMultasFijo = new RadioButton();

    public RadioButton getRbCondonarMultasFijo() {
        return rbCondonarMultasFijo;
    }

    public void setRbCondonarMultasFijo(RadioButton rb) {
        this.rbCondonarMultasFijo = rb;
    }
    private Label label23 = new Label();

    public Label getLabel23() {
        return label23;
    }

    public void setLabel23(Label l) {
        this.label23 = l;
    }
    private TextField tfMultasCondonado = new TextField();

    public TextField getTfMultasCondonado() {
        return tfMultasCondonado;
    }

    public void setTfMultasCondonado(TextField tf) {
        this.tfMultasCondonado = tf;
    }
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }
    private Label lblCapitalARefinanciar = new Label();

    public Label getLblCapitalARefinanciar() {
        return lblCapitalARefinanciar;
    }

    public void setLblCapitalARefinanciar(Label l) {
        this.lblCapitalARefinanciar = l;
    }
    private Label lblInteresesAPagar = new Label();

    public Label getLblInteresesAPagar() {
        return lblInteresesAPagar;
    }

    public void setLblInteresesAPagar(Label l) {
        this.lblInteresesAPagar = l;
    }
    private TextField tfTotalARefinanciar = new TextField();

    public TextField getTfTotalARefinanciar() {
        return tfTotalARefinanciar;
    }

    public void setTfTotalARefinanciar(TextField tf) {
        this.tfTotalARefinanciar = tf;
    }
    private TextField tfInteresesAPagar = new TextField();

    public TextField getTfInteresesAPagar() {
        return tfInteresesAPagar;
    }

    public void setTfInteresesAPagar(TextField tf) {
        this.tfInteresesAPagar = tf;
    }
    private Label lblDigesto = new Label();

    public Label getLblDigesto() {
        return lblDigesto;
    }

    public void setLblDigesto(Label l) {
        this.lblDigesto = l;
    }
    private TextField tfDigesto = new TextField();

    public TextField getTfDigesto() {
        return tfDigesto;
    }

    public void setTfDigesto(TextField tf) {
        this.tfDigesto = tf;
    }
    private Button btnSeleccionarDigesto = new Button();

    public Button getBtnSeleccionarDigesto() {
        return btnSeleccionarDigesto;
    }

    public void setBtnSeleccionarDigesto(Button b) {
        this.btnSeleccionarDigesto = b;
    }
    private Button btnLimpiarDigesto = new Button();

    public Button getBtnLimpiarDigesto() {
        return btnLimpiarDigesto;
    }

    public void setBtnLimpiarDigesto(Button b) {
        this.btnLimpiarDigesto = b;
    }
    private Label lblInteresPunitorio = new Label();

    public Label getLblInteresPunitorio() {
        return lblInteresPunitorio;
    }

    public void setLblInteresPunitorio(Label l) {
        this.lblInteresPunitorio = l;
    }
    private TextField tfInteresPunitorio = new TextField();

    public TextField getTfInteresPunitorio() {
        return tfInteresPunitorio;
    }

    public void setTfInteresPunitorio(TextField tf) {
        this.tfInteresPunitorio = tf;
    }
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }
    private Label lblDiaVencimiento = new Label();

    public Label getLblDiaVencimiento() {
        return lblDiaVencimiento;
    }

    public void setLblDiaVencimiento(Label l) {
        this.lblDiaVencimiento = l;
    }
    private Label lblInicioRefinanciacion = new Label();

    public Label getLblInicioRefinanciacion() {
        return lblInicioRefinanciacion;
    }

    public void setLblInicioRefinanciacion(Label l) {
        this.lblInicioRefinanciacion = l;
    }
    private TextField tfDiaVencimiento = new TextField();

    public TextField getTfDiaVencimiento() {
        return tfDiaVencimiento;
    }

    public void setTfDiaVencimiento(TextField tf) {
        this.tfDiaVencimiento = tf;
    }
    private TextField tfAnioInicio = new TextField();

    public TextField getTfAnioInicio() {
        return tfAnioInicio;
    }

    public void setTfAnioInicio(TextField tf) {
        this.tfAnioInicio = tf;
    }
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }
    private Label lblSaldoAFavor = new Label();

    public Label getLblSaldoAFavor() {
        return lblSaldoAFavor;
    }

    public void setLblSaldoAFavor(Label l) {
        this.lblSaldoAFavor = l;
    }
    private TextField tfSaldoAFavor = new TextField();

    public TextField getTfSaldoAFavor() {
        return tfSaldoAFavor;
    }

    public void setTfSaldoAFavor(TextField tf) {
        this.tfSaldoAFavor = tf;
    }
    private Label lblContribuyente = new Label();

    public Label getLblContribuyente() {
        return lblContribuyente;
    }

    public void setLblContribuyente(Label l) {
        this.lblContribuyente = l;
    }
    private TextField tfContribuyente = new TextField();

    public TextField getTfContribuyente() {
        return tfContribuyente;
    }

    public void setTfContribuyente(TextField tf) {
        this.tfContribuyente = tf;
    }
    private Label lblCantidadCuotasCaida = new Label();

    public Label getLblCantidadCuotasCaida() {
        return lblCantidadCuotasCaida;
    }

    public void setLblCantidadCuotasCaida(Label l) {
        this.lblCantidadCuotasCaida = l;
    }
    private Label lblCantidadDiasCaida = new Label();

    public Label getLblCantidadDiasCaida() {
        return lblCantidadDiasCaida;
    }

    public void setLblCantidadDiasCaida(Label l) {
        this.lblCantidadDiasCaida = l;
    }
    private TextField tfCantidadCuotasCaida = new TextField();

    public TextField getTfCantidadCuotasCaida() {
        return tfCantidadCuotasCaida;
    }

    public void setTfCantidadCuotasCaida(TextField tf) {
        this.tfCantidadCuotasCaida = tf;
    }
    private TextField tfCantidadDiasCaida = new TextField();

    public TextField getTfCantidadDiasCaida() {
        return tfCantidadDiasCaida;
    }

    public void setTfCantidadDiasCaida(TextField tf) {
        this.tfCantidadDiasCaida = tf;
    }
    private Label label32 = new Label();

    public Label getLabel32() {
        return label32;
    }

    public void setLabel32(Label l) {
        this.label32 = l;
    }
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }
    private Label lblTotalAPagar = new Label();

    public Label getLblTotalAPagar() {
        return lblTotalAPagar;
    }

    public void setLblTotalAPagar(Label l) {
        this.lblTotalAPagar = l;
    }
    private TextField tfTotalAPagar = new TextField();

    public TextField getTfTotalAPagar() {
        return tfTotalAPagar;
    }

    public void setTfTotalAPagar(TextField tf) {
        this.tfTotalAPagar = tf;
    }
    private TextField tfMesInicio = new TextField();

    public TextField getTfMesInicio() {
        return tfMesInicio;
    }

    public void setTfMesInicio(TextField tf) {
        this.tfMesInicio = tf;
    }
    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }
    private NumberConverter numberConverter1 = new NumberConverter();

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter nc) {
        this.numberConverter1 = nc;
    }
    private Label lblImporteACondonar = new Label();

    public Label getLblImporteACondonar() {
        return lblImporteACondonar;
    }

    public void setLblImporteACondonar(Label l) {
        this.lblImporteACondonar = l;
    }
    private Label lblInteresesACondonar = new Label();

    public Label getLblInteresesACondonar() {
        return lblInteresesACondonar;
    }

    public void setLblInteresesACondonar(Label l) {
        this.lblInteresesACondonar = l;
    }
    private Label lblRecargosACondonar = new Label();

    public Label getLblRecargosACondonar() {
        return lblRecargosACondonar;
    }

    public void setLblRecargosACondonar(Label l) {
        this.lblRecargosACondonar = l;
    }
    private Label lblMultasACondonar = new Label();

    public Label getLblMultasACondonar() {
        return lblMultasACondonar;
    }

    public void setLblMultasACondonar(Label l) {
        this.lblMultasACondonar = l;
    }
    // </editor-fold>

    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public AgregarPlanPagoRefinanciacion() {
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
            log("AgregarPlanPagoRefinanciacion Initialization Failure", e);
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

//        if (!recarga) {
        this.mostrarEstadoObjetosUsados();
//        }

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
        ep.getObjetos().add(ind++, new ArrayList()); // lista de periodos adeudados (RegistroDeuda)
        ep.getObjetos().add(ind++, new DigestoMunicipal());
        ep.getObjetos().add(ind++, new DocumentoRefinanciacion());
        ep.getObjetos().add(ind++, new RegCancelacionPorRefinanciacion());

        // valores de la columna Condonar. Los importes condonados se guardan en RegCancelacionPorRefinanciacion
        ep.getObjetos().add(ind++, new Double(0)); // valor del monto a condonar
        ep.getObjetos().add(ind++, new Double(0)); // valor de intereses a condonar
        ep.getObjetos().add(ind++, new Double(0)); // valor de recargos a condonar
        ep.getObjetos().add(ind++, new Double(0)); // valor de multas a condonar

        ep.getObjetos().add(ind++, null); // contribuyente
        ep.getObjetos().add(ind++, new ArrayList()); // lista de cuotas
        ep.getObjetos().add(ind++, new AuditoriaTributaria());//10

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
        DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);
        RegCancelacionPorRefinanciacion regCancelacionPorRefinanciacion = (RegCancelacionPorRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, RegCancelacionPorRefinanciacion.class);
        Double valorCondonarMonto = (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);
        Double valorCondonarInteres = (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);
        Double valorCondonarRecargo = (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);
        Double valorCondonarMulta = (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);
        Persona contribuyente = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        ArrayList listaCuotas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        // Obtencion de valores
        Object saldoAFavor = this.getTfSaldoAFavor().getText();

        Object importeACondonar = this.getTfImporteACondonar().getText();
        Object interesesACondonar = this.getTfInteresesACondonar().getText();
        Object recargosACondonar = this.getTfRecargosACondonar().getText();
        Object multasACondonar = this.getTfMultasACondonar().getText();

        //Agregado por mal por problema en null pointer al generar 
        DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        String locNombre = "[" + locDateFormat.format(Calendar.getInstance().getTime()) + "]";
        documentoRefinanciacion.setNombre(locNombre + contribuyente.toString());


        Object importeCondonado = this.getTfImporteCondonado().getText();
        Object interesesCondonado = this.getTfInteresesCondonado().getText();
        Object recargosCondonado = this.getTfRecargosCondonado().getText();
        Object multasCondonado = this.getTfMultasCondonado().getText();

        Object cantidadCuotas = this.getTfCantidadCuotas().getText();
        Object tasaNominalAnual = this.getTfTasaNominalAnual().getText();
        Object interesPunitorio = this.getTfInteresPunitorio().getText();
        Object diaVencimiento = this.getTfDiaVencimiento().getText();
        Object mesInicio = this.getTfMesInicio().getText();
        Object anioInicio = this.getTfAnioInicio().getText();

        Object cantidadDiasCaida = this.getTfCantidadDiasCaida().getText();
        Object cantidadCuotasCaida = this.getTfCantidadCuotasCaida().getText();

        // Asignacion de valores
        if (saldoAFavor != null && saldoAFavor != "") {
            documentoRefinanciacion.setSaldoAFavor(Conversor.getDoubleDeString(saldoAFavor.toString()));
        } else {
            documentoRefinanciacion.setSaldoAFavor(null);
        }
        if (importeACondonar != null && importeACondonar != "") {
            valorCondonarMonto = Conversor.getDoubleDeString(importeACondonar.toString());
        } else {
            valorCondonarMonto = null;
        }
        if (interesesACondonar != null && interesesACondonar != "") {
            valorCondonarInteres = Conversor.getDoubleDeString(interesesACondonar.toString());
        } else {
            valorCondonarInteres = null;
        }
        if (recargosACondonar != null && recargosACondonar != "") {
            valorCondonarRecargo = Conversor.getDoubleDeString(recargosACondonar.toString());
        } else {
            valorCondonarRecargo = null;
        }
        if (multasACondonar != null && multasACondonar != "") {
            valorCondonarMulta = Conversor.getDoubleDeString(multasACondonar.toString());
        } else {
            valorCondonarMulta = null;
        }

        if (importeCondonado != null && importeCondonado != "") {
            regCancelacionPorRefinanciacion.setImporteCondonado(Conversor.getDoubleDeString(importeCondonado.toString()));
        } else {
            regCancelacionPorRefinanciacion.setImporteCondonado(null);
        }
        if (interesesCondonado != null && interesesCondonado != "") {
            regCancelacionPorRefinanciacion.setInteresCondonado(Conversor.getDoubleDeString(interesesCondonado.toString()));
        } else {
            regCancelacionPorRefinanciacion.setInteresCondonado(null);
        }
        if (recargosCondonado != null && recargosCondonado != "") {
            regCancelacionPorRefinanciacion.setRecargoCondonado(Conversor.getDoubleDeString(recargosCondonado.toString()));
        } else {
            regCancelacionPorRefinanciacion.setRecargoCondonado(null);
        }
        if (multasCondonado != null && multasCondonado != "") {
            regCancelacionPorRefinanciacion.setMultaCondonada(Conversor.getDoubleDeString(multasCondonado.toString()));
        } else {
            regCancelacionPorRefinanciacion.setMultaCondonada(null);
        }
        if (cantidadCuotas != null && cantidadCuotas != "") {
            documentoRefinanciacion.setCantidadCuotas(Conversor.getIntegerDeString(cantidadCuotas.toString()));
        } else {
            documentoRefinanciacion.setCantidadCuotas(null);
        }
        if (tasaNominalAnual != null && tasaNominalAnual != "") {
            documentoRefinanciacion.setTasaNominalAnual(Conversor.getDoubleDeString(tasaNominalAnual.toString()));
        } else {
            documentoRefinanciacion.setTasaNominalAnual(null);
        }
        if (interesPunitorio != null && interesPunitorio != "") {
            documentoRefinanciacion.setInteresDiario(Conversor.getDoubleDeString(interesPunitorio.toString()));
        } else {
            documentoRefinanciacion.setInteresDiario(null);
        }
        if (diaVencimiento != null && diaVencimiento != "") {
            documentoRefinanciacion.setDiaVencimiento(Conversor.getIntegerDeString(diaVencimiento.toString()));
        } else {
            documentoRefinanciacion.setDiaVencimiento(null);
        }
        if (mesInicio != null && mesInicio != "") {
            documentoRefinanciacion.setMesInicioRefinanciacion(Conversor.getIntegerDeString(mesInicio.toString()));
        } else {
            documentoRefinanciacion.setMesInicioRefinanciacion(null);
        }
        if (anioInicio != null && anioInicio != "") {
            documentoRefinanciacion.setAnioInicioRefinanciacion(Conversor.getIntegerDeString(anioInicio.toString()));
        } else {
            documentoRefinanciacion.setAnioInicioRefinanciacion(null);
        }
        if (cantidadDiasCaida != null && cantidadDiasCaida != "") {
            documentoRefinanciacion.setCantidadDiasCadaRefinanciacion(Conversor.getIntegerDeString(cantidadDiasCaida.toString()));
        } else {
            documentoRefinanciacion.setCantidadDiasCadaRefinanciacion(null);
        }
        if (cantidadCuotasCaida != null && cantidadCuotasCaida != "") {
            documentoRefinanciacion.setCantidadCuotasCadaRefinanciacion(Conversor.getIntegerDeString(cantidadCuotasCaida.toString()));
        } else {
            documentoRefinanciacion.setCantidadCuotasCadaRefinanciacion(null);
        }

        // digesto en regCancelacion...
        if (digestoMunicipal != null && digestoMunicipal.getIdDigestoMunicipal() != -1) {
            regCancelacionPorRefinanciacion.setDigestoMunicipal(digestoMunicipal);
            documentoRefinanciacion.getRegCancelacionPorRefinanciacion().setDigestoMunicipal(digestoMunicipal);
        } else {
            regCancelacionPorRefinanciacion.setDigestoMunicipal(null);
            this.getTfDigesto().setText(" ");
        }

        documentoRefinanciacion.setRegCancelacionPorRefinanciacion(regCancelacionPorRefinanciacion);
        //sacado por mines
        /*if (digestoMunicipal != null && !digestoMunicipal.equals(null)) {//
        //documentoRefinanciacion.getRegCancelacionPorRefinanciacion().setDigestoMunicipal(digestoMunicipal);
        regCancelacionPorRefinanciacion.setDigestoMunicipal(digestoMunicipal);
        } else {
        documentoRefinanciacion.getRegCancelacionPorRefinanciacion().setDigestoMunicipal(null);
        this.getTfDigesto().setText(" ");
        }*/
        regCancelacionPorRefinanciacion.setDocumentoRefinanciacion(documentoRefinanciacion);

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, periodosAdeudados);
        this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);

        this.getElementoPila().getObjetos().set(ind++, documentoRefinanciacion);
        this.getElementoPila().getObjetos().set(ind++, regCancelacionPorRefinanciacion);

        this.getElementoPila().getObjetos().set(ind++, valorCondonarMonto);
        this.getElementoPila().getObjetos().set(ind++, valorCondonarInteres);
        this.getElementoPila().getObjetos().set(ind++, valorCondonarRecargo);
        this.getElementoPila().getObjetos().set(ind++, valorCondonarMulta);

        this.getElementoPila().getObjetos().set(ind++, contribuyente);
        this.getElementoPila().getObjetos().set(ind++, listaCuotas);

        this.setListaDelCommunication(listaCuotas);
        this.getObjectListDataProvider().setList(listaCuotas);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        ArrayList periodosAdeudados = null;
        DigestoMunicipal digestoMunicipal = null;
        DocumentoRefinanciacion documentoRefinanciacion = null;
        RegCancelacionPorRefinanciacion regCancelacionPorRefinanciacion = null;
        Double valorCondonarMonto = null;
        Double valorCondonarInteres = null;
        Double valorCondonarRecargo = null;
        Double valorCondonarMulta = null;
        Persona contribuyente = null;
        ArrayList listaCuotas = null;

        this.acomodarSeleccionado();

        if (this.getRequestBean1().getObjetoABM() != null) {
            // ariel - por objetoABM va a venir la lista de Periodos Adeudados
            periodosAdeudados = (ArrayList) this.getRequestBean1().getObjetoABM();
            documentoRefinanciacion = new DocumentoRefinanciacion();
            regCancelacionPorRefinanciacion = new RegCancelacionPorRefinanciacion();

            if (periodosAdeudados != null) {
                Set registrosDeuda = new HashSet(periodosAdeudados);
//                for (Iterator it = registrosDeuda.iterator(); it.hasNext();) {
//                    RegistroDeuda reg = (RegistroDeuda) it.next();                 
//                }
                regCancelacionPorRefinanciacion.getListaRegistrosDeuda().addAll(registrosDeuda);

                documentoRefinanciacion.setRegCancelacionPorRefinanciacion(regCancelacionPorRefinanciacion);
                regCancelacionPorRefinanciacion.setDocumentoRefinanciacion(documentoRefinanciacion);
                documentoRefinanciacion.setTipoDocGeneradorDeuda(TipoDocGeneradorDeuda.REFINANCIACION);
                contribuyente = ((RegistroDeuda) registrosDeuda.iterator().next()).getPersona();
            }

            valorCondonarMonto = new Double(0);
            valorCondonarInteres = new Double(0);
            valorCondonarRecargo = new Double(0);
            valorCondonarMulta = new Double(0);

            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, periodosAdeudados);
            this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);
            this.getElementoPila().getObjetos().set(ind++, documentoRefinanciacion);
            this.getElementoPila().getObjetos().set(ind++, regCancelacionPorRefinanciacion);
            this.getElementoPila().getObjetos().set(ind++, valorCondonarMonto);
            this.getElementoPila().getObjetos().set(ind++, valorCondonarInteres);
            this.getElementoPila().getObjetos().set(ind++, valorCondonarRecargo);
            this.getElementoPila().getObjetos().set(ind++, valorCondonarMulta);
            this.getElementoPila().getObjetos().set(ind++, contribuyente);
            this.getElementoPila().getObjetos().set(ind++, listaCuotas);
        }

        if (this.getRequestBean1().getRespuestaABM() != null) {
            Object seleccionado = this.getRequestBean1().getRespuestaABM();
            if (seleccionado instanceof AuditoriaTributaria) {
                AuditoriaTributaria locAuditoriaTributaria = (AuditoriaTributaria) seleccionado;
                this.getElementoPila().getObjetos().set(10, locAuditoriaTributaria);
            }
            this.getRequestBean1().setRespuestaABM(null);
        }

        ind = 0;
        periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
        if (digestoMunicipal.getIdDigestoMunicipal() == -1) {
            digestoMunicipal = null;

        }

        documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);
        regCancelacionPorRefinanciacion = (RegCancelacionPorRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, RegCancelacionPorRefinanciacion.class);
        valorCondonarMonto = (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);
        valorCondonarInteres = (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);
        valorCondonarRecargo = (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);
        valorCondonarMulta = (Double) this.obtenerObjetoDelElementoPila(ind++, Double.class);

        contribuyente = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        listaCuotas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        documentoRefinanciacion.getRegCancelacionPorRefinanciacion().setDigestoMunicipal(digestoMunicipal);//Mines

        DocGeneradorDeuda docGeneradorDeuda = regCancelacionPorRefinanciacion.getDocumentoRefinanciacion().getDocGeneradorDeudaAnterior();

        this.getTfPeriodosSeleccionados().setText(String.valueOf(regCancelacionPorRefinanciacion.getListaRegistrosDeuda().size()));
        if (contribuyente != null) {
            this.getTfContribuyente().setText(String.valueOf(contribuyente));
        }

        if (documentoRefinanciacion.getSaldoAFavor() != null) {
            this.getTfSaldoAFavor().setText(Conversor.getStringDeDouble(documentoRefinanciacion.getSaldoAFavor()));
        }
//        for (Iterator it = regCancelacionPorRefinanciacion.getListaRegistrosDeuda().iterator(); it.hasNext();) {
//            RegistroDeuda reg = (RegistroDeuda) it.next();     
//        }
     
        if (regCancelacionPorRefinanciacion.getImporteTotal() != null) {
            this.getTfImporte().setText(Conversor.getStringDeDouble(regCancelacionPorRefinanciacion.getImporteTotal()));
        }
        if (regCancelacionPorRefinanciacion.getInteres() != null) {
            this.getTfIntereses().setText(Conversor.getStringDeDouble(regCancelacionPorRefinanciacion.getInteres()));
        }
        if (regCancelacionPorRefinanciacion.getRecargoTotal() != null) {
            this.getTfRecargos().setText(Conversor.getStringDeDouble(regCancelacionPorRefinanciacion.getRecargoTotal()));
        }
        if (regCancelacionPorRefinanciacion.getMultasTotal() != null) {
            this.getTfMultas().setText(Conversor.getStringDeDouble(regCancelacionPorRefinanciacion.getMultasTotal()));
        }

        DigestoMunicipal locDigesto = regCancelacionPorRefinanciacion.getDigestoMunicipal();
        if (locDigesto == null) {
            this.getTfDigesto().setText(" ");
        } else {
            documentoRefinanciacion.getRegCancelacionPorRefinanciacion().setDigestoMunicipal(locDigesto);
            this.getTfDigesto().setText(locDigesto.toString());
        }
        //sacado por mines - hacia dos veces lo mismo
        /*
        if(documentoRefinanciacion.getRegCancelacionPorRefinanciacion().getDigestoMunicipal() != null){
        this.getTfDigesto().setText(regCancelacionPorRefinanciacion.getDigestoMunicipal().toString());
        
        }else{
        documentoRefinanciacion.getRegCancelacionPorRefinanciacion().setDigestoMunicipal(digestoMunicipal);
        this.getTfDigesto().setText(" ");
        }
        
        if(regCancelacionPorRefinanciacion.getDigestoMunicipal() != null){
        this.getTfDigesto().setText(regCancelacionPorRefinanciacion.getDigestoMunicipal().toString());
        }
        else{
        regCancelacionPorRefinanciacion.setDigestoMunicipal(digestoMunicipal);
        this.getTfDigesto().setText(" ");
        }*/

        if (valorCondonarMonto != null) {
            this.tfImporteACondonar.setText(Conversor.getStringDeDouble(valorCondonarMonto));
        }
        if (valorCondonarInteres != null) {
            this.tfInteresesACondonar.setText(Conversor.getStringDeDouble(valorCondonarInteres));
        }
        if (valorCondonarRecargo != null) {
            this.tfRecargosACondonar.setText(Conversor.getStringDeDouble(valorCondonarRecargo));
        }
        if (valorCondonarMulta != null) {
            this.tfMultasACondonar.setText(Conversor.getStringDeDouble(valorCondonarMulta));
        }
        if (documentoRefinanciacion.getCantidadCuotas() != null) {
            this.getTfCantidadCuotas().setText(documentoRefinanciacion.getCantidadCuotas().toString());
        }
        if (documentoRefinanciacion.getTasaNominalAnual() != null) {
            this.getTfTasaNominalAnual().setText(Conversor.getStringDeDouble(documentoRefinanciacion.getTasaNominalAnual()));
        }
        if (documentoRefinanciacion.getInteresDiario() != null) {
            this.getTfInteresPunitorio().setText(Conversor.getStringDeDouble(documentoRefinanciacion.getInteresDiario()));
        }

        if (documentoRefinanciacion.getDiaVencimiento() != null) {
            String diaVencimiento = documentoRefinanciacion.getDiaVencimiento().toString();
            if (diaVencimiento.length() < 2) {
                diaVencimiento = "0" + diaVencimiento;
            }
            this.getTfDiaVencimiento().setText(diaVencimiento);
        }
        if (documentoRefinanciacion.getMesInicioRefinanciacion() != null) {
            String mesInicio = documentoRefinanciacion.getMesInicioRefinanciacion().toString();
            if (mesInicio.length() < 2) {
                mesInicio = "0" + mesInicio;
            }
            this.getTfMesInicio().setText(mesInicio);
        }
        if (documentoRefinanciacion.getAnioInicioRefinanciacion() != null) {
            this.getTfAnioInicio().setText(documentoRefinanciacion.getAnioInicioRefinanciacion().toString());
        }
        if (documentoRefinanciacion.getCantidadDiasCadaRefinanciacion() != null) {
            this.getTfCantidadDiasCaida().setText(documentoRefinanciacion.getCantidadDiasCadaRefinanciacion().toString());
        }
        if (documentoRefinanciacion.getCantidadCuotasCadaRefinanciacion() != null) {
            this.getTfCantidadCuotasCaida().setText(documentoRefinanciacion.getCantidadCuotasCadaRefinanciacion().toString());
        }
        this.setListaDelCommunication(listaCuotas);
        this.getObjectListDataProvider().setList(listaCuotas);
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpCuotasRefinanciacion();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getListaCuotasRefinanciacion();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationExcepcionesBean().setListaCuotasRefinanciacion(lista);
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
                    && !tipoClase.getName().equals(Persona.class.getName())
                    && !tipoClase.getName().equals(Double.class.getName())) {
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
            rk = this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    // </editor-fold>

    // </editor-fold>    
    public String btnSeleccionarDigesto_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminDigestoMunicipal";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarDigesto_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(1, this.getTfDigesto());
            this.getTfDigesto().setText(" ");
            //this.tfDigesto.setText(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnGenerarCuotas_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                this.guardarEstadoObjetosUsados();

                // inicio de la validacion.
                Validador locValidador = new Validador();
                int cantNoVacios = 6;
                UIComponent[] noVacios = new UIComponent[cantNoVacios];
                String[] nomNoVacios = new String[cantNoVacios];
                int cantEnteros = 4;
                UIComponent[] enteros = new UIComponent[cantEnteros];
                String[] nomEnteros = new String[cantEnteros];
                int cantDecimales = 2;
                UIComponent[] decimales = new UIComponent[cantDecimales];
                String[] nomDecimales = new String[cantDecimales];
                int cantLongitudEntre = 1;
                UIComponent[] longEntre = new UIComponent[cantLongitudEntre];
                String[] nomLongEntre = new String[cantLongitudEntre];
                Integer[] minLongEntre = new Integer[cantLongitudEntre];
                Integer[] maxLongEntre = new Integer[cantLongitudEntre];

                int pos = 0;
                noVacios[pos] = this.getTfCantidadCuotas();
                nomNoVacios[pos++] = this.getLblCantidadCuotas().getText().toString();
                noVacios[pos] = this.getTfTasaNominalAnual();
                nomNoVacios[pos++] = this.getLblTasaNominalAnual().getText().toString();
                noVacios[pos] = this.getTfInteresPunitorio();
                nomNoVacios[pos++] = this.getLblInteresPunitorio().getText().toString();
                noVacios[pos] = this.getTfDiaVencimiento();
                nomNoVacios[pos++] = this.getLblDiaVencimiento().getText().toString();
                noVacios[pos] = this.getTfMesInicio();
                nomNoVacios[pos++] = "Mes de Inicio de la Refinanciaci\363n";
                noVacios[pos] = this.getTfAnioInicio();
                nomNoVacios[pos++] = "A\361o de Inicio de la Refinanciaci\363n";

                pos = 0;
                enteros[pos] = this.getTfCantidadCuotas();
                nomEnteros[pos++] = this.getLblCantidadCuotas().getText().toString();
                enteros[pos] = this.getTfDiaVencimiento();
                nomEnteros[pos++] = this.getLblDiaVencimiento().getText().toString();
                enteros[pos] = this.getTfMesInicio();
                nomEnteros[pos++] = "Mes de Inicio de la Refinanciaci\363n";
                enteros[pos] = this.getTfAnioInicio();
                nomEnteros[pos++] = "A\361o de Inicio de la Refinanciaci\363n";

                pos = 0;
                decimales[pos] = this.getTfTasaNominalAnual();
                nomDecimales[pos++] = this.getLblTasaNominalAnual().getText().toString();
                decimales[pos] = this.getTfInteresPunitorio();
                nomDecimales[pos++] = this.getLblInteresPunitorio().getText().toString();

                pos = 0;
                longEntre[pos] = this.getTfMesInicio();
                nomLongEntre[pos] = "Mes de Inicio de la Refinanciaci\363n";
                minLongEntre[pos] = new Integer(1);
                maxLongEntre[pos++] = new Integer(12);

                locValidador.noSonVacios(noVacios, nomNoVacios);
                locValidador.esNumero(enteros, nomEnteros);
                locValidador.sonFlotantes(decimales, nomDecimales);
                locValidador.cumplenLargo(longEntre, nomLongEntre, minLongEntre, maxLongEntre);

                if (locValidador.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < locValidador.getErrores().size(); i++) {
                        warn(locValidador.getErrores().toArray()[i].toString());
                    }
                    return null;
                }
                // fin de la validacion.


                DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(2, DocumentoRefinanciacion.class);
                this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
                Set listaCuotasGeneradas = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().calcularCuotasRefinanciacion(documentoRefinanciacion);

                ArrayList listaCuotas = new ArrayList(listaCuotasGeneradas);
                Collections.sort(listaCuotas, new Comparator() {

                    public int compare(Object o1, Object o2) {
                        return ((CuotaRefinanciacion) o1).getNumeroCuota().compareTo(((CuotaRefinanciacion) o2).getNumeroCuota());
                    }
                });

                double totalARefinanciar = 0.0F;
                double interesesAPagar = 0.0F;
                double totalAPagar = 0.0F;

                Object objTotalARefinanciar = this.getTfTotalARefinanciar().getText();
                if (objTotalARefinanciar != null && objTotalARefinanciar != "") {
                    totalARefinanciar = Conversor.getDoubleDeString(objTotalARefinanciar.toString()).doubleValue();
                }
                for (int i = 0; i < listaCuotas.size(); i++) {
                    CuotaRefinanciacion cuota = (CuotaRefinanciacion) listaCuotas.get(i);
                    interesesAPagar += cuota.getInteres().doubleValue();
                }
                totalAPagar = totalARefinanciar + interesesAPagar;

                //  DecimalFormat df = new DecimalFormat("###,###");
                // this.getTfInteresesAPagar().setText(df.format(interesesAPagar));
                //this.getTfInteresesAPagar().setText((new Double(interesesAPagar)).toString());
                this.getTfInteresesAPagar().setText((new Double(interesesAPagar)).toString());
                this.getTfTotalAPagar().setText((new Double(totalAPagar)).toString());

                this.getElementoPila().getObjetos().set(9, listaCuotas);
                this.setListaDelCommunication(listaCuotas);
                this.getObjectListDataProvider().setList(listaCuotas);
            } catch (Exception ex) {
                ex.printStackTrace();
                error("No se puede generar la lista de cuotas.");
            }

            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                this.guardarEstadoObjetosUsados();

                // CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                int cantNoVacios = 12; //mines cambiaste de 12 a 13
                UIComponent[] noVacios = new UIComponent[cantNoVacios];
                String[] nomNoVacios = new String[cantNoVacios];

                int cantEnteros = 6;
                UIComponent[] enteros = new UIComponent[cantEnteros];
                String[] nomEnteros = new String[cantEnteros];

                int cantDecimales = 2;
                UIComponent[] decimales = new UIComponent[cantDecimales];
                String[] nomDecimales = new String[cantDecimales];

                int cantLongitudEntre = 1;
                UIComponent[] longEntre = new UIComponent[cantLongitudEntre];
                String[] nomLongEntre = new String[cantLongitudEntre];
                Integer[] minLongEntre = new Integer[cantLongitudEntre];
                Integer[] maxLongEntre = new Integer[cantLongitudEntre];

                int pos = 0;
                noVacios[pos] = this.getTfImporteACondonar();
                nomNoVacios[pos++] = "Importe a Condonar";
                noVacios[pos] = this.getTfInteresesACondonar();
                nomNoVacios[pos++] = "Intereses a Condonar";
                noVacios[pos] = this.getTfRecargosACondonar();
                nomNoVacios[pos++] = "Recargos a Condonar";
                noVacios[pos] = this.getTfMultasACondonar();
                nomNoVacios[pos++] = "Multas a Condonar";


                noVacios[pos] = this.getTfCantidadCuotas();
                nomNoVacios[pos++] = this.getLblCantidadCuotas().getText().toString();
                noVacios[pos] = this.getTfTasaNominalAnual();
                nomNoVacios[pos++] = this.getLblTasaNominalAnual().getText().toString();
                noVacios[pos] = this.getTfInteresPunitorio();
                nomNoVacios[pos++] = this.getLblInteresPunitorio().getText().toString();
                noVacios[pos] = this.getTfDiaVencimiento();
                nomNoVacios[pos++] = this.getLblDiaVencimiento().getText().toString();
                noVacios[pos] = this.getTfMesInicio();
                nomNoVacios[pos++] = "Mes de Inicio de la Refinanciaci\363n";
                noVacios[pos] = this.getTfAnioInicio();
                nomNoVacios[pos++] = "A\361o de Inicio de la Refinanciaci\363n";

                noVacios[pos] = this.getTfCantidadDiasCaida();
                nomNoVacios[pos++] = this.getLblCantidadDiasCaida().getText().toString();
                noVacios[pos] = this.getTfCantidadCuotasCaida();
                nomNoVacios[pos++] = this.getLblCantidadCuotasCaida().getText().toString();

                pos = 0;
                enteros[pos] = this.getTfCantidadCuotas();
                nomEnteros[pos++] = this.getLblCantidadCuotas().getText().toString();
                enteros[pos] = this.getTfDiaVencimiento();
                nomEnteros[pos++] = this.getLblDiaVencimiento().getText().toString();
                enteros[pos] = this.getTfMesInicio();
                nomEnteros[pos++] = "Mes de Inicio de la Refinanciaci\363n";
                enteros[pos] = this.getTfAnioInicio();
                nomEnteros[pos++] = "A\361o de Inicio de la Refinanciaci\363n";
                enteros[pos] = this.getTfCantidadDiasCaida();
                nomEnteros[pos++] = this.getLblCantidadDiasCaida().getText().toString();
                enteros[pos] = this.getTfCantidadCuotasCaida();
                nomEnteros[pos++] = this.getLblCantidadCuotasCaida().getText().toString();

                pos = 0;
                decimales[pos] = this.getTfTasaNominalAnual();
                nomDecimales[pos++] = this.getLblTasaNominalAnual().getText().toString();
                decimales[pos] = this.getTfInteresPunitorio();
                nomDecimales[pos++] = this.getLblInteresPunitorio().getText().toString();

                pos = 0;
                longEntre[pos] = this.getTfMesInicio();
                nomLongEntre[pos] = "Mes de Inicio de la Refinanciaci\363n";
                minLongEntre[pos] = new Integer(1);
                maxLongEntre[pos++] = new Integer(12);

                v.noSonVacios(noVacios, nomNoVacios);
                v.esNumero(enteros, nomEnteros);
                v.sonFlotantes(decimales, nomDecimales);
                v.cumplenLargo(longEntre, nomLongEntre, minLongEntre, maxLongEntre);

                //**************************************************************
                String anio = this.getTfAnioInicio().getText().toString();
                String mes = this.getTfMesInicio().getText().toString();
                String dia = this.getTfDiaVencimiento().getText().toString();
                String fechaCompleta = dia + "/" + mes + "/" + anio;

                Object digesto = this.getTfDigesto().getText().toString();

                DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(2, DocumentoRefinanciacion.class);
                Persona contribuyente = (Persona) this.obtenerObjetoDelElementoPila(8, Persona.class);

                //MINES AGREGASTE ESTO: 
                DigestoMunicipal locDigestoMunicipal = documentoRefinanciacion.getRegCancelacionPorRefinanciacion().getDigestoMunicipal();
                DocGeneradorDeuda docGeneradorDeuda = documentoRefinanciacion.getDocGeneradorDeudaAnterior();


                if (!v.fechaValida(fechaCompleta, "dd/MM/yyyy")) {
                    String msg = "Los datos correspondientes a D\u00EDa de Vencimiento o a Inicio de la Refinanciaci\u00F3n no son v\u00E1lidos";
                    v.getErrores().add(msg);
                }
//                if (locDigestoMunicipal == null) {
//                    String msg = "Debe seleccionar un Decreto, Ordenanza o Resoluci\u00F3n ";
//                    v.getErrores().add(msg);
//                }

                //HASTA ACA
                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }
                // fin de la validacion.
                // CAMBIAR: Utilizar el EJBClient adecuado.

                //FIXME AGREGAR LA OBLIGACIÓN A LA REFINANCIACIÓN

                Obligacion locObligacion = new Obligacion();
                locObligacion.setPersona(contribuyente);
                locObligacion.setNombre(documentoRefinanciacion.getNombre() + " N Ref " + documentoRefinanciacion.getNumeroRefinanciacion());
                locObligacion.setDescripcion("Refinanciacion Obligacion correspondiente a: " + documentoRefinanciacion.getNombre() + " Numero de Refinanciacion: " + documentoRefinanciacion.getNumeroRefinanciacion());
                //Falta la relacion OBLIGACION-DOCUMENTOREFINANCIACION no se puede agregar DOCUMENTOREFINANCIACION en DOCUMENTOESPECIALIZADO


                DocHabilitanteEspecializado docHabilitanteEspecializado = new DocumentoRef();

                String nombreDocHabilitante = "Nombre Documento de Refinanciacion";
                docHabilitanteEspecializado.setNombre(nombreDocHabilitante);
                locObligacion.setDocumentoEspecializado(docHabilitanteEspecializado);
                documentoRefinanciacion.setObligacion(locObligacion);
                locObligacion.setLlaveUsuarioAuditoria(this.getSessionBean1().getLlave());

                AuditoriaTributaria locAuditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(10, AuditoriaTributaria.class);

                this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
                if (locAuditoriaTributaria != null && locAuditoriaTributaria.getIdAuditoriaTributaria() != -1) {
                    this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().addRefinanciacion(documentoRefinanciacion, locAuditoriaTributaria);
                    this.getRequestBean1().setRespuestaABM(documentoRefinanciacion);
                    this.getRequestBean1().setTipoSeleccion("AUDITORIA");
                } else {
                    this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().addRefinanciacion(documentoRefinanciacion, null);
                    this.getRequestBean1().setRespuestaABM(documentoRefinanciacion);
                    this.getRequestBean1().setTipoSeleccion("REFINANCIACION");
                }

                this.setListaDelCommunication(null);
                info("La Refinanciaci\363n se agreg\363 exitosamente.");
                retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_GuardarError:", ex);
                error(NOMBRE_PAGINA + " - Guardar: " + ex.getMessage());
                ex.printStackTrace();
            }

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnCancelar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        AuditoriaTributaria locAuditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(10, AuditoriaTributaria.class);

        if (ultimo) {
            //para q vuelva a la pestania de auditoria o no///////////
            if (locAuditoriaTributaria != null && locAuditoriaTributaria.getIdAuditoriaTributaria() != -1) {
                this.getRequestBean1().setTipoSeleccion("AUDITORIA");
            } else {
                this.getRequestBean1().setTipoSeleccion("REFINANCIACION");
            }
            /////////////////////////////////////
            this.setListaDelCommunication(null);

            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
}
