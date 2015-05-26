/*
 * ConsultarRefinanciacion.java
 *
 * Created on 23 de octubre de 2006, 13:23
 * Copyright Trascender SRL
 */
/**
 * Mines
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
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.presentacion.navegacion.ElementoPila;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.saic.recurso.persistent.RegCancelacionPorRefinanciacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.model.Option;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.presentacion.conversores.Conversor;
import jasper.ConstantesReportes;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ValueChangeEvent;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class ConsultarRefinanciacion extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Consultar Refinanciaci\363n";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ConsultarRefinanciacion";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    private final String lnkAgregar = "AgregarPlanPagoRefinanciacion";
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
            this.getObjectListDataProviderTabla1().setList(this.getListaDelCommunication());
        }

        if (this.getListaDelCommunication2() != null) {
            this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunication2());
        }
        numberConverter1.setPattern("$ #,##0.00");

        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern("dd/MM/yy");
        dateTimeConverter1.setTimeStyle("full");

        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoTasa().keySet().toArray(), "cap");
        ddTipoObligacionDefaultOptions.setOptions(op);

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
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
        this.dateTimeConverter1 = dateTimeConverter1;
    }
    private ObjectListDataProvider ldpObligaciones = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpObligaciones() {
        return ldpObligaciones;
    }

    public void setLdpObligaciones(ObjectListDataProvider oldp) {
        this.ldpObligaciones = oldp;
    }
    private Table table2 = new Table();

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table table2) {
        this.table2 = table2;
    }
    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }
    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
        this.tableRowGroup2 = tableRowGroup2;
    }
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    private NumberConverter numberConverter1 = new NumberConverter();

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter numberConverter1) {
        this.numberConverter1 = numberConverter1;
    }
    private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
        return ddTipoObligacionDefaultOptions;
    }

    public void setDdTipoObligacionDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddTipoObligacionDefaultOptions = ssol;
    }
    private PanelGroup gpBotones = new PanelGroup();

    public PanelGroup getGpBotones() {
        return gpBotones;
    }

    public void setGpBotones(PanelGroup gpBotones) {
        this.gpBotones = gpBotones;
    }
    private PanelGroup panelGroup1 = new PanelGroup();

    public PanelGroup getPanelGroup1() {
        return panelGroup1;
    }

    public void setPanelGroup1(PanelGroup pg) {
        this.panelGroup1 = pg;
    }
    private ObjectListDataProvider ldpCuotasGeneradas = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpCuotasGeneradas() {
        return ldpCuotasGeneradas;
    }

    public void setLdpCuotasGeneradas(ObjectListDataProvider ldpCuotasGeneradas) {
        this.ldpCuotasGeneradas = ldpCuotasGeneradas;
    }
    private ObjectListDataProvider ldpPeriodosAdeudados = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpPeriodosAdeudados() {
        return ldpPeriodosAdeudados;
    }

    public void setLdpPeriodosAdeudados(ObjectListDataProvider oldp) {
        this.ldpPeriodosAdeudados = oldp;
    }

    public TableSelectPhaseListener getTablePhaseListener() {
        return tablePhaseListener;
    }

    public void setTablePhaseListener(TableSelectPhaseListener tablePhaseListener) {
        this.tablePhaseListener = tablePhaseListener;
    }

    public TableSelectPhaseListener getTablePhaseListenerTabla1() {
        return tablePhaseListenerTabla1;
    }

    public void setTablePhaseListenerTabla1(TableSelectPhaseListener tablePhaseListenerTabla1) {
        this.tablePhaseListenerTabla1 = tablePhaseListenerTabla1;
    }
    //----------------StaticTexts----------------
    private StaticText staticText15 = new StaticText();
    private StaticText staticText14 = new StaticText();
    private StaticText staticText13 = new StaticText();
    private StaticText staticText12 = new StaticText();
    private StaticText staticText11 = new StaticText();
    private StaticText staticText10 = new StaticText();
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText staticText10) {
        this.staticText10 = staticText10;
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

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText staticText8) {
        this.staticText8 = staticText8;
    }
    private StaticText stSeparador1 = new StaticText();
    private StaticText stSeparador2 = new StaticText();
    private StaticText stSeparador3 = new StaticText();
    private StaticText stSeparador4 = new StaticText();
    private StaticText stSeparador5 = new StaticText();
    private StaticText stSeparador6 = new StaticText();
    private StaticText stSeparador7 = new StaticText();
    private StaticText stSeparador8 = new StaticText();
    private StaticText stSeparador9 = new StaticText();
    private StaticText stSeparador10 = new StaticText();
    private StaticText stSeparador11 = new StaticText();
    private StaticText stSeparador12 = new StaticText();

    public StaticText getStSeparador11() {
        return stSeparador11;
    }

    public void setStSeparador11(StaticText stSeparador11) {
        this.stSeparador11 = stSeparador11;
    }

    public StaticText getStSeparador12() {
        return stSeparador12;
    }

    public void setStSeparador12(StaticText stSeparador12) {
        this.stSeparador12 = stSeparador12;
    }

    public StaticText getStSeparador10() {
        return stSeparador10;
    }

    public void setStSeparador10(StaticText stSeparador10) {
        this.stSeparador10 = stSeparador10;
    }

    public StaticText getStSeparador7() {
        return stSeparador7;
    }

    public void setStSeparador7(StaticText stSeparador7) {
        this.stSeparador7 = stSeparador7;
    }

    public StaticText getStSeparador8() {
        return stSeparador8;
    }

    public void setStSeparador8(StaticText stSeparador8) {
        this.stSeparador8 = stSeparador8;
    }

    public StaticText getStSeparador9() {
        return stSeparador9;
    }

    public void setStSeparador9(StaticText stSeparador9) {
        this.stSeparador9 = stSeparador9;
    }

    public StaticText getStSeparador1() {
        return stSeparador1;
    }

    public void setStSeparador1(StaticText stSeparador1) {
        this.stSeparador1 = stSeparador1;
    }

    public StaticText getStSeparador2() {
        return stSeparador2;
    }

    public void setStSeparador2(StaticText stSeparador2) {
        this.stSeparador2 = stSeparador2;
    }

    public StaticText getStSeparador3() {
        return stSeparador3;
    }

    public void setStSeparador3(StaticText stSeparador3) {
        this.stSeparador3 = stSeparador3;
    }

    public StaticText getStSeparador4() {
        return stSeparador4;
    }

    public void setStSeparador4(StaticText stSeparador4) {
        this.stSeparador4 = stSeparador4;
    }

    public StaticText getStSeparador5() {
        return stSeparador5;
    }

    public void setStSeparador5(StaticText stSeparador5) {
        this.stSeparador5 = stSeparador5;
    }

    public StaticText getStSeparador6() {
        return stSeparador6;
    }

    public void setStSeparador6(StaticText stSeparador6) {
        this.stSeparador6 = stSeparador6;
    }
    private StaticText stNombreRefinanciacion = new StaticText();

    public StaticText getStNombreRefinanciacion() {
        return stNombreRefinanciacion;
    }

    public void setStNombreRefinanciacion(StaticText stNombreRefinanciacion) {
        this.stNombreRefinanciacion = stNombreRefinanciacion;
    }
    private StaticText stTitulo = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText staticText7) {
        this.staticText7 = staticText7;
    }
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
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
    //----------------TextAreas----------------
    private TextArea taComercios = new TextArea();

    public TextArea getTaComercios() {
        return taComercios;
    }

    public void setTaComercios(TextArea taComercios) {
        this.taComercios = taComercios;
    }
    private TextArea taInmuebles = new TextArea();

    public TextArea getTaInmuebles() {
        return taInmuebles;
    }

    public void setTaInmuebles(TextArea taInmuebles) {
        this.taInmuebles = taInmuebles;
    }
    //----------------TextFields----------------
    private TextField tfTotalCondonado = new TextField();

    public TextField getTfTotalCondonado() {
        return tfTotalCondonado;
    }

    public void setTfTotalCondonado(TextField tfTotalCondonado) {
        this.tfTotalCondonado = tfTotalCondonado;
    }
    private TextField tfMontoTotal = new TextField();

    public TextField getTfMontoTotal() {
        return tfMontoTotal;
    }

    public void setTfMontoTotal(TextField tfMontoTotal) {
        this.tfMontoTotal = tfMontoTotal;
    }
    private TextField tfEstado = new TextField();

    public TextField getTfEstado() {
        return tfEstado;
    }

    public void setTfEstado(TextField tfEstado) {
        this.tfEstado = tfEstado;
    }
    private TextField tfNombreRefinanciacion = new TextField();

    public TextField getTfNombreRefinanciacion() {
        return tfNombreRefinanciacion;
    }

    public void setTfNombreRefinanciacion(TextField tfNombreRefinanciacion) {
        this.tfNombreRefinanciacion = tfNombreRefinanciacion;
    }
    private TextField tfCapitalRefinanciado = new TextField();

    public TextField getTfCapitalRefinanciado() {
        return tfCapitalRefinanciado;
    }

    public void setTfCapitalRefinanciado(TextField tfCapitalRefinanciado) {
        this.tfCapitalRefinanciado = tfCapitalRefinanciado;
    }
    private TextField tfMultaRefinanciacion = new TextField();

    public TextField getTfMultaRefinanciacion() {
        return tfMultaRefinanciacion;
    }

    public void setTfMultaRefinanciacion(TextField tfMultaRefinanciacion) {
        this.tfMultaRefinanciacion = tfMultaRefinanciacion;
    }
    private TextField tfMultaCondonado = new TextField();

    public TextField getTfMultaCondonado() {
        return tfMultaCondonado;
    }

    public void setTfMultaCondonado(TextField tfMultaCondonado) {
        this.tfMultaCondonado = tfMultaCondonado;
    }
    private TextField tfMultaTotal = new TextField();

    public TextField getTfMultaTotal() {
        return tfMultaTotal;
    }

    public void setTfMultaTotal(TextField tfMultaTotal) {
        this.tfMultaTotal = tfMultaTotal;
    }
    private TextField tfRecargoRefinanciacion = new TextField();

    public TextField getTfRecargoRefinanciacion() {
        return tfRecargoRefinanciacion;
    }

    public void setTfRecargoRefinanciacion(TextField tfRecargoRefinanciacion) {
        this.tfRecargoRefinanciacion = tfRecargoRefinanciacion;
    }
    private TextField tfRecargoCondonado = new TextField();

    public TextField getTfRecargoCondonado() {
        return tfRecargoCondonado;
    }

    public void setTfRecargoCondonado(TextField tfRecargoCondonado) {
        this.tfRecargoCondonado = tfRecargoCondonado;
    }
    private TextField tfRecargoTotal = new TextField();

    public TextField getTfRecargoTotal() {
        return tfRecargoTotal;
    }

    public void setTfRecargoTotal(TextField tfRecargoTotal) {
        this.tfRecargoTotal = tfRecargoTotal;
    }
    private TextField tfInteresRefinanciacion = new TextField();

    public TextField getTfInteresRefinanciacion() {
        return tfInteresRefinanciacion;
    }

    public void setTfInteresRefinanciacion(TextField tfInteresRefinanciacion) {
        this.tfInteresRefinanciacion = tfInteresRefinanciacion;
    }
    private TextField tfInteresCondonado = new TextField();

    public TextField getTfInteresCondonado() {
        return tfInteresCondonado;
    }

    public void setTfInteresCondonado(TextField tfInteresCondonado) {
        this.tfInteresCondonado = tfInteresCondonado;
    }
    private TextField tfInteresTotal = new TextField();

    public TextField getTfInteresTotal() {
        return tfInteresTotal;
    }

    public void setTfInteresTotal(TextField tfInteresTotal) {
        this.tfInteresTotal = tfInteresTotal;
    }
    private TextField tfSubTotal = new TextField();

    public TextField getTfSubTotal() {
        return tfSubTotal;
    }

    public void setTfSubTotal(TextField tfSubTotal) {
        this.tfSubTotal = tfSubTotal;
    }
    private TextField tfImporteCondonado = new TextField();

    public TextField getTfImporteCondonado() {
        return tfImporteCondonado;
    }

    public void setTfImporteCondonado(TextField tfImporteCondonado) {
        this.tfImporteCondonado = tfImporteCondonado;
    }
    private TextField tfImporteTotal = new TextField();

    public TextField getTfImporteTotal() {
        return tfImporteTotal;
    }

    public void setTfImporteTotal(TextField tfImporteTotal) {
        this.tfImporteTotal = tfImporteTotal;
    }
    private TextField tfInteresDiario = new TextField();

    public TextField getTfInteresDiario() {
        return tfInteresDiario;
    }

    public void setTfInteresDiario(TextField tfInteresDiario) {
        this.tfInteresDiario = tfInteresDiario;
    }
    private TextField tfTasaNominal = new TextField();

    public TextField getTfTasaNominal() {
        return tfTasaNominal;
    }

    public void setTfTasaNominal(TextField tfTasaNominal) {
        this.tfTasaNominal = tfTasaNominal;
    }
    private TextField tfCantCuotas = new TextField();

    public TextField getTfCantCuotas() {
        return tfCantCuotas;
    }

    public void setTfCantCuotas(TextField tfCantCuotas) {
        this.tfCantCuotas = tfCantCuotas;
    }
    private TextField tfDigesto = new TextField();

    public TextField getTfDigesto() {
        return tfDigesto;
    }

    public void setTfDigesto(TextField tfDigesto) {
        this.tfDigesto = tfDigesto;
    }
    private TextField tfFechaRefinanciacion = new TextField();

    public TextField getTfFechaRefinanciacion() {
        return tfFechaRefinanciacion;
    }

    public void setTfFechaRefinanciacion(TextField tfFechaRefinanciacion) {
        this.tfFechaRefinanciacion = tfFechaRefinanciacion;
    }
    private TextField tfNroRefinanciacion = new TextField();

    public TextField getTfNroRefinanciacion() {
        return tfNroRefinanciacion;
    }

    public void setTfNroRefinanciacion(TextField tfNroRefinanciacion) {
        this.tfNroRefinanciacion = tfNroRefinanciacion;
    }
    private TextField tfContribuyente = new TextField();

    public TextField getTfContribuyente() {
        return tfContribuyente;
    }

    public void setTfContribuyente(TextField tf) {
        this.tfContribuyente = tf;
    }
    //----------------Labels----------------
    private Label lblTotalCondonado = new Label();

    public Label getLblTotalCondonado() {
        return lblTotalCondonado;
    }

    public void setLblTotalCondonado(Label lblTotalCondonado) {
        this.lblTotalCondonado = lblTotalCondonado;
    }
    private Label lblMontoTotal = new Label();

    public Label getLblMontoTotal() {
        return lblMontoTotal;
    }

    public void setLblMontoTotal(Label lblMontoTotal) {
        this.lblMontoTotal = lblMontoTotal;
    }
    private Label lblCuotasGeneradas = new Label();

    public Label getLblCuotasGeneradas() {
        return lblCuotasGeneradas;
    }

    public void setLblCuotasGeneradas(Label lblCuotasGeneradas) {
        this.lblCuotasGeneradas = lblCuotasGeneradas;
    }
    private Label lblEstado = new Label();

    public Label getLblEstado() {
        return lblEstado;
    }

    public void setLblEstado(Label lblEstado) {
        this.lblEstado = lblEstado;
    }
    private Label lblResumenDeuda = new Label();

    public Label getLblResumenDeuda() {
        return lblResumenDeuda;
    }

    public void setLblResumenDeuda(Label lblResumenDeuda) {
        this.lblResumenDeuda = lblResumenDeuda;
    }
    private Label lblCapitalRefinanciado = new Label();

    public Label getLblCapitalRefinanciado() {
        return lblCapitalRefinanciado;
    }

    public void setLblCapitalRefinanciado(Label lblCapitalRefinanciado) {
        this.lblCapitalRefinanciado = lblCapitalRefinanciado;
    }
    private Label lblMultaRefinanciacion = new Label();

    public Label getLblMultaRefinanciacion() {
        return lblMultaRefinanciacion;
    }

    public void setLblMultaRefinanciacion(Label lblMultaRefinanciacion) {
        this.lblMultaRefinanciacion = lblMultaRefinanciacion;
    }
    private Label lblMultaCondonado = new Label();

    public Label getLblMultaCondonado() {
        return lblMultaCondonado;
    }

    public void setLblMultaCondonado(Label lblMultaCondonado) {
        this.lblMultaCondonado = lblMultaCondonado;
    }
    private Label lblMultaTotal = new Label();

    public Label getLblMultaTotal() {
        return lblMultaTotal;
    }

    public void setLblMultaTotal(Label lblMultaTotal) {
        this.lblMultaTotal = lblMultaTotal;
    }
    private Label lblRecargoRefinanciacion = new Label();

    public Label getLblRecargoRefinanciacion() {
        return lblRecargoRefinanciacion;
    }

    public void setLblRecargoRefinanciacion(Label lblRecargoRefinanciacion) {
        this.lblRecargoRefinanciacion = lblRecargoRefinanciacion;
    }
    private Label lblRecargoCondonado = new Label();

    public Label getLblRecargoCondonado() {
        return lblRecargoCondonado;
    }

    public void setLblRecargoCondonado(Label lblRecargoCondonado) {
        this.lblRecargoCondonado = lblRecargoCondonado;
    }
    private Label lblRecargoTotal = new Label();

    public Label getLblRecargoTotal() {
        return lblRecargoTotal;
    }

    public void setLblRecargoTotal(Label lblRecargoTotal) {
        this.lblRecargoTotal = lblRecargoTotal;
    }
    private Label lblInteresRefinanciacion = new Label();

    public Label getLblInteresRefinanciacion() {
        return lblInteresRefinanciacion;
    }

    public void setLblInteresRefinanciacion(Label lblInteresRefinanciacion) {
        this.lblInteresRefinanciacion = lblInteresRefinanciacion;
    }
    private Label lblInteresCondonado = new Label();

    public Label getLblInteresCondonado() {
        return lblInteresCondonado;
    }

    public void setLblInteresCondonado(Label lblInteresCondonado) {
        this.lblInteresCondonado = lblInteresCondonado;
    }
    private Label lblInteresTotal = new Label();

    public Label getLblInteresTotal() {
        return lblInteresTotal;
    }

    public void setLblInteresTotal(Label lblInteresTotal) {
        this.lblInteresTotal = lblInteresTotal;
    }
    private Label lblSubTotal = new Label();

    public Label getLblSubTotal() {
        return lblSubTotal;
    }

    public void setLblSubTotal(Label lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }
    private Label lblImporteCondonado = new Label();

    public Label getLblImporteCondonado() {
        return lblImporteCondonado;
    }

    public void setLblImporteCondonado(Label lblImporteCondonado) {
        this.lblImporteCondonado = lblImporteCondonado;
    }
    private Label lblImporteTotal = new Label();

    public Label getLblImporteTotal() {
        return lblImporteTotal;
    }

    public void setLblImporteTotal(Label lblImporteTotal) {
        this.lblImporteTotal = lblImporteTotal;
    }
    private Label lblInteresDiario = new Label();

    public Label getLblInteresDiario() {
        return lblInteresDiario;
    }

    public void setLblInteresDiario(Label lblInteresDiario) {
        this.lblInteresDiario = lblInteresDiario;
    }
    private Label lblTasaNominal = new Label();

    public Label getLblTasaNominal() {
        return lblTasaNominal;
    }

    public void setLblTasaNominal(Label lblTasaNominal) {
        this.lblTasaNominal = lblTasaNominal;
    }
    private Label lblCantCuotas = new Label();

    public Label getLblCantCuotas() {
        return lblCantCuotas;
    }

    public void setLblCantCuotas(Label lblCantCuotas) {
        this.lblCantCuotas = lblCantCuotas;
    }
    private Label lblTitle1 = new Label();

    public Label getLblTitle1() {
        return lblTitle1;
    }

    public void setLblTitle1(Label lblTitle1) {
        this.lblTitle1 = lblTitle1;
    }
    private Label lblNombreRefinanciacion = new Label();

    public Label getLblNombreRefinanciacion() {
        return lblNombreRefinanciacion;
    }

    public void setLblNombreRefinanciacion(Label l) {
        this.lblNombreRefinanciacion = l;
    }
    private Label lblNroRefinanciacion = new Label();

    public Label getLblNroRefinanciacion() {
        return lblNroRefinanciacion;
    }

    public void setLblNroRefinanciacion(Label l) {
        this.lblNroRefinanciacion = l;
    }
    private Label lblFechaRefinanciacion = new Label();

    public Label getLblFechaRefinanciacion() {
        return lblFechaRefinanciacion;
    }

    public void setLblFechaRefinanciacion(Label l) {
        this.lblFechaRefinanciacion = l;
    }
    private Label lblContribuyente = new Label();

    public Label getLblContribuyente() {
        return lblContribuyente;
    }

    public void setLblContribuyente(Label l) {
        this.lblContribuyente = l;
    }
    private Label lblDigesto = new Label();

    public Label getLblDigesto() {
        return lblDigesto;
    }

    public void setLblDigesto(Label lblDigesto) {
        this.lblDigesto = lblDigesto;
    }
    private Label lblInmuebles = new Label();

    public Label getLblInmuebles() {
        return lblInmuebles;
    }

    public void setLblInmuebles(Label lblInmuebles) {
        this.lblInmuebles = lblInmuebles;
    }
    private Label lblComercios = new Label();

    public Label getLblComercios() {
        return lblComercios;
    }

    public void setLblComercios(Label lblComercios) {
        this.lblComercios = lblComercios;
    }
    //----------------Buttons----------------
    private Button btnImprimirReconocimientoDeuda = new Button();

    public Button getBtnImprimirReconocimientoDeuda() {
        return btnImprimirReconocimientoDeuda;
    }

    public void setBtnImprimirReconocimientoDeuda(Button btnImprimirReconocimientoDeuda) {
        this.btnImprimirReconocimientoDeuda = btnImprimirReconocimientoDeuda;
    }
    private Button btnImprimirCuotasGeneradas = new Button();

    public Button getBtnImprimirCuotasGeneradas() {
        return btnImprimirCuotasGeneradas;
    }

    public void setBtnImprimirCuotasGeneradas(Button btnImprimirCuotasGeneradas) {
        this.btnImprimirCuotasGeneradas = btnImprimirCuotasGeneradas;
    }
    private Button btnVolver = new Button();

    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button b) {
        this.btnVolver = b;
    }
    private Button btnVerPeriodos = new Button();

    public Button getBtnVerPeriodos() {
        return btnVerPeriodos;
    }

    public void setBtnVerPeriodos(Button b) {
        this.btnVerPeriodos = b;
    }
    //----------------TableColumns----------------
    private TableColumn tableColumn16 = new TableColumn();
    private TableColumn tableColumn15 = new TableColumn();
    private TableColumn tableColumn14 = new TableColumn();
    private TableColumn tableColumn13 = new TableColumn();
    private TableColumn tableColumn12 = new TableColumn();
    private TableColumn tableColumn11 = new TableColumn();

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tableColumn11) {
        this.tableColumn11 = tableColumn11;
    }

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tableColumn12) {
        this.tableColumn12 = tableColumn12;
    }

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tableColumn13) {
        this.tableColumn13 = tableColumn13;
    }

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tableColumn14) {
        this.tableColumn14 = tableColumn14;
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
    private TableColumn tableColumn10 = new TableColumn();

    public TableColumn getTableColumn10() {
        return tableColumn10;
    }

    public void setTableColumn10(TableColumn tableColumn10) {
        this.tableColumn10 = tableColumn10;
    }
    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tableColumn9) {
        this.tableColumn9 = tableColumn9;
    }
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }
    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }
    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tableColumn8) {
        this.tableColumn8 = tableColumn8;
    }
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }
    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
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

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ConsultarRefinanciacion() {
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
            log("ConsultarRefinanciacion Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

        tablePhaseListener = this.getTableSelectPhaseListener();
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
        ep.getObjetos().add(ind++, null); // persona
        ep.getObjetos().add(ind++, new ArrayList()); // lista de periodos adeudados (......)
        ep.getObjetos().add(ind++, new DocumentoRefinanciacion()); //

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, persona);
        this.getElementoPila().getObjetos().set(ind++, periodosAdeudados);
        this.getElementoPila().getObjetos().set(ind++, documentoRefinanciacion);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Persona persona = null;
        ArrayList obligaciones = null;
        ArrayList listadoCuotas = null;
        ArrayList periodosAdeudados = null;
        DocumentoRefinanciacion documentoRefinanciacion = null;
        RegCancelacionPorRefinanciacion registroCancelacion = null;

        //Double interesRef = new Double(0.0);
        double montoTotal = 0.0;
        double totalCondonado = 0.0;
        double capitalAPagar = 0.0;
        double recargoRef = 0.0;
        double multaRef = 0.0;
        double interesRef = 0.0;
        double subtotal = 0.0;


        this.acomodarSeleccionado();

        if (this.getRequestBean1().getObjetoABM() != null) {
            documentoRefinanciacion = (DocumentoRefinanciacion) this.getRequestBean1().getObjetoABM();
            if (documentoRefinanciacion.getRegCancelacionPorRefinanciacion() != null) {
                registroCancelacion = documentoRefinanciacion.getRegCancelacionPorRefinanciacion();
            }
            this.getElementoPila().getObjetos().set(2, documentoRefinanciacion);
        }

        RegistroDeuda rg = null;
        ind = 0;
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        listadoCuotas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);
        registroCancelacion = documentoRefinanciacion.getRegCancelacionPorRefinanciacion();
//        if(documentoRefinanciacion.getIdDocGeneradorDeuda() == -1){
//            documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(2, DocumentoRefinanciacion.class);
//
//        }

        //Calculo de los montos refinanciados
        if (registroCancelacion.getInteres() != null && registroCancelacion.getInteresCondonado() != null) {
            interesRef = registroCancelacion.getInteres().doubleValue() - registroCancelacion.getInteresCondonado().doubleValue();
            this.getTfInteresRefinanciacion().setText(Conversor.getStringDeDouble(new Double(interesRef)));
        } //

        if (registroCancelacion.getRecargoTotal() != null && registroCancelacion.getRecargoCondonado() != null) {
            recargoRef = registroCancelacion.getRecargoTotal().doubleValue() - registroCancelacion.getRecargoCondonado().doubleValue();
            this.getTfRecargoRefinanciacion().setText(Conversor.getStringDeDouble(new Double(recargoRef)));

        }

        if (registroCancelacion.getMultasTotal() != null && registroCancelacion.getMultaCondonada() != null) {
            if (registroCancelacion.getMultaCondonada().doubleValue() > 0) {
                multaRef = registroCancelacion.getMultasTotal().doubleValue() - registroCancelacion.getMultaCondonada().doubleValue();
            } else {
                multaRef = registroCancelacion.getMultasTotal().doubleValue() + registroCancelacion.getMultaCondonada().doubleValue();
            }

            this.getTfMultaRefinanciacion().setText(Conversor.getStringDeDouble(new Double(multaRef)));

        }

        //Calculo de los montos totales, total condonado y total a pagar
        if (registroCancelacion.getInteres() != null && registroCancelacion.getImporteTotal() != null && registroCancelacion.getMultasTotal() != null && registroCancelacion.getRecargoTotal() != null) {
            montoTotal = registroCancelacion.getInteres().doubleValue() + registroCancelacion.getImporteTotal().doubleValue() + registroCancelacion.getMultasTotal().doubleValue() + registroCancelacion.getRecargoTotal().doubleValue();
            this.getTfMontoTotal().setText(Conversor.getStringDeDouble(new Double(montoTotal)));
        }
        if (registroCancelacion.getImporteCondonado() != null && registroCancelacion.getInteresCondonado() != null && registroCancelacion.getMultaCondonada() != null && registroCancelacion.getRecargoCondonado() != null) {
            totalCondonado = registroCancelacion.getInteresCondonado().doubleValue() + registroCancelacion.getImporteCondonado().doubleValue() + registroCancelacion.getMultaCondonada().doubleValue() + registroCancelacion.getRecargoCondonado().doubleValue();
            this.getTfTotalCondonado().setText(Conversor.getStringDeDouble(new Double(totalCondonado)));
        }

        if (registroCancelacion.getImporteTotal() != null && registroCancelacion.getImporteCondonado() != null) {
            subtotal = registroCancelacion.getImporteTotal().doubleValue() - registroCancelacion.getImporteCondonado().doubleValue();
            this.getTfSubTotal().setText(Conversor.getStringDeDouble(new Double(subtotal)));
        } //Subtotal


        capitalAPagar = interesRef + recargoRef + multaRef + subtotal;
        this.getTfCapitalRefinanciado().setText(Conversor.getStringDeDouble(new Double(capitalAPagar)));//documentoRefinanciacion.getCapital().doubleValue())));

        if (documentoRefinanciacion.getStringNombreRefinanciacion() != null) {
            this.getTfNombreRefinanciacion().setText(documentoRefinanciacion.getStringNombreRefinanciacion());
        }

        try {
            this.getTfEstado().setText(Conversor.getStringDeObject(documentoRefinanciacion.getEstadoRefinanciacion()));
        } catch (TrascenderException ex) {
            ex.printStackTrace();
        }
        if (documentoRefinanciacion.getStringPersona() != null) {
            this.getTfContribuyente().setText(documentoRefinanciacion.getStringPersona());
        }
        if (documentoRefinanciacion.getNumeroRefinanciacion() != null) {
            this.getTfNroRefinanciacion().setText(documentoRefinanciacion.getNumeroRefinanciacion().toString());
        }
        if (documentoRefinanciacion.getAnioInicioRefinanciacion() != null) {
            this.getTfFechaRefinanciacion().setText(documentoRefinanciacion.getAnioInicioRefinanciacion().toString());
        }
        if (documentoRefinanciacion.getNombre() != null) {
            this.getStNombreRefinanciacion().setText(documentoRefinanciacion.getNombre().toString());
        }
        if (registroCancelacion.getDigestoMunicipal() != null) {
            this.getTfDigesto().setText(registroCancelacion.getDigestoMunicipal().toString());
        }
        if (documentoRefinanciacion.getStringInmuebles() != null) {
            this.getTaInmuebles().setText(documentoRefinanciacion.getStringInmuebles());
        }
        if (documentoRefinanciacion.getStringComercios() != null) {
            this.getTaComercios().setText(documentoRefinanciacion.getStringComercios());
        }


        // FALTA: recargoRefinanciacion
        if (documentoRefinanciacion.getCantidadCuotas() != null) {
            this.getTfCantCuotas().setText(documentoRefinanciacion.getCantidadCuotas().toString());
        }
        if (documentoRefinanciacion.getTasaNominalAnual() != null) {
            this.getTfTasaNominal().setText(documentoRefinanciacion.getTasaNominalAnual().toString());
        }
        if (documentoRefinanciacion.getInteresDiario() != null) {
            this.getTfInteresDiario().setText(documentoRefinanciacion.getInteresDiario().toString());
        }
        if (registroCancelacion.getImporteTotal() != null) {
            this.getTfImporteTotal().setText(registroCancelacion.getImporteTotal().toString());
        }
        if (registroCancelacion.getImporteCondonado() != null) {
            this.getTfImporteCondonado().setText(registroCancelacion.getImporteCondonado().toString());
        }
        if (registroCancelacion.getInteres() != null) {
            this.getTfInteresTotal().setText(registroCancelacion.getInteres().toString());
        }
        if (registroCancelacion.getInteresCondonado() != null) {
            this.getTfInteresCondonado().setText(registroCancelacion.getInteresCondonado().toString());
        }
        if (registroCancelacion.getRecargoTotal() != null) {
            this.getTfRecargoTotal().setText(registroCancelacion.getRecargoTotal().toString());
        }
        if (registroCancelacion.getRecargoCondonado() != null) {
            this.getTfRecargoCondonado().setText(registroCancelacion.getRecargoCondonado().toString());
        }
        if (registroCancelacion.getMultasTotal() != null) {
            this.getTfMultaTotal().setText(registroCancelacion.getMultasTotal().toString());
        }
        if (registroCancelacion.getMultaCondonada() != null) {
            this.getTfMultaCondonado().setText(registroCancelacion.getMultaCondonada().toString());
        }

        if (documentoRefinanciacion.getListaRegistrosDeuda().size() > 0) {
            listadoCuotas = new ArrayList(documentoRefinanciacion.getListaRegistrosDeuda());
        }
        if (registroCancelacion.getListaRegistrosDeuda().size() > 0) {
            periodosAdeudados = new ArrayList(registroCancelacion.getListaRegistrosDeuda());
        }

        //Table1
        this.setListaDelCommunication(listadoCuotas);
        this.getObjectListDataProviderTabla1().setList(listadoCuotas);
        //Table2
        this.setListaDelCommunication2(periodosAdeudados);
        this.getObjectListDataProviderTabla2().setList(periodosAdeudados);

    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getListaCuotasRefinanciacion();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationExcepcionesBean().setListaCuotasRefinanciacion(lista);
    }

    private ArrayList getListaDelCommunication2() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getListaPeriodosAdeudadosRefinanciacion();
    }

    private void setListaDelCommunication2(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationExcepcionesBean().setListaPeriodosAdeudadosRefinanciacion(lista);
    }

    private TableSelectPhaseListener getTableSelectPhaseListener() {
        // CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que corresponda
        return this.getCommunicationExcepcionesBean().getTablePhaseListenerPeriodosAdeudadosRefinanciacion();
    }

    private ObjectListDataProvider getObjectListDataProviderTabla1() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpCuotasGeneradas();
    }

    private ObjectListDataProvider getObjectListDataProviderTabla2() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpPeriodosAdeudados();
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
        this.getObjectListDataProviderTabla1().getList().clear();
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
    /*
     * // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar
     * RaddioButton"> public String getCurrentRow() { return
     * tableRowGroup1.getRowKey().getRowId(); } public void setCurrentRow(int
     * row) { } private Object lastSelected = null; public Object
     * getRBSelected() { String sv = (String)radioButton1.getSelectedValue();
     * return sv.equals(lastSelected) ? sv : null; } public void
     * setRBSelected(Object selected){ if (selected != null) { lastSelected =
     * selected; } } private int getNroFila(String pCadena) { // Toma la Cadena
     * con el formato 'RowKey[i]' y devuelve el entero i String lCadenaAuxiliar
     * = pCadena.substring(7, pCadena.length() - 1); return new
     * Integer(lCadenaAuxiliar).intValue(); } // </editor-fold>
     */
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
            rk = this.getObjectListDataProviderTabla1().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    // </editor-fold>
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
    private TableSelectPhaseListener tablePhaseListenerTabla1;

    public void setSelectedTabla1(Object object) {
        RowKey rowKey = tableRowGroup1.getRowKey();
        if (rowKey != null) {
            tablePhaseListenerTabla1.setSelected(rowKey, object);
        }
    }

    public Object getSelectedTabla1() {
        RowKey rowKey = tableRowGroup1.getRowKey();
        return tablePhaseListenerTabla1.getSelected(rowKey);
    }

    public Object getSelectedValueTabla1() {
        RowKey rowKey = tableRowGroup1.getRowKey();
        return (rowKey != null) ? rowKey.getRowId() : null;
    }

    // </editor-fold>
    // </editor-fold>
    public String btnSeleccionarPersonaFisica_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 0;

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

    public void ddTipoObligacion_processValueChange(ValueChangeEvent vce) {
        this.getElementoPila().getObjetos().set(1, vce.getNewValue());
        return;
    }

    public String btnVerPeriodos_action() {
        // TODO: Buscar las deudas de las obligaciones seleccionadas.
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                this.guardarEstadoObjetosUsados();

                ArrayList obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
                List obligacionesSeleccionadas = new ArrayList();

                RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();

                if (selectedRowKeys.length > 0) {
                    for (int i = 0; i < selectedRowKeys.length; i++) {
                        String rowId = selectedRowKeys[i].getRowId();
                        RowKey rowKey = this.getObjectListDataProviderTabla1().getRowKey(rowId);
                        //Object obj = this.getObjectListDataProvider().getObjects()[getNroFila(rowKey.toString())];
                        Object obj = this.getObjectListDataProviderTabla1().getObjects()[Integer.valueOf(rowKey.getRowId()).intValue()];
                        obligacionesSeleccionadas.add(obj);
                    }

                    this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
                } else {
                    retorno = null;
                }

            } catch (Exception ex) {
                if (ex instanceof TrascenderException) {
                    ex.printStackTrace();
                    int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) {
                        retorno = null;
                    } else {
                        retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
                    }
                }
                log(CASO_NAVEGACION, ex);
                error(NOMBRE_PAGINA + " - " + this.btnVerPeriodos.getText() + ":" + ex.getMessage());
            }

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

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

    public String btnImprimirCuotasGeneradas_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            try {
                DocumentoRefinanciacion documentoRefinanciacion = null;
                documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(2, DocumentoRefinanciacion.class);

                this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
//                JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteListadoCuotasRefinanciacion(documentoRefinanciacion);
//
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_CuotasRefinanciacion");
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

            } catch (Exception e) {
                log(CASO_NAVEGACION + "_ReporteDinamicoError: ", e);
                error(NOMBRE_PAGINA + " - ReporteDinamico: " + e.getMessage());
            }
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        //Maru: selecciono el objeto de la tabla y de ahi obtengo los datos para imprimir el reporte, en vez de usar la pila.
//        if (ultimo) {
//            this.guardarEstadoObjetosUsados();
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//
//            try {
//
//                DocumentoRefinanciacion documentoRefinanciacion = null;
//
//                documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(2, DocumentoRefinanciacion.class);
//
////                documentoRefinanciacion = (DocumentoRefinanciacion) this.getRequestBean1().getObjetoABM();
//
//                this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
//                this.getCommunicationSAICBean().getRemoteSystemImpresion().imprimirListadoCuotasRefinanciacion(documentoRefinanciacion);
//            } catch (Exception ex) {
//                log(CASO_NAVEGACION + "_ImprimirLiquidacionesSHPS", ex);
//                error(NOMBRE_PAGINA + " - Imprimir: " + ex.getMessage());
//            }
//            // APLICAR LOGICA AQUI...
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//            this.guardarOrdenamiento();
//            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
//            this.getElementoPila().setPosicionGlobal(pos.longValue());
//
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;

    }

    public String btnImprimirR_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            try {
                DocumentoRefinanciacion documentoRefinanciacion = null;
                documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(2, DocumentoRefinanciacion.class);

                this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
                JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteReconocimientoDeuda(documentoRefinanciacion);

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_ReconocimientoDeudas");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

            } catch (Exception e) {
                log(CASO_NAVEGACION + "_ReporteDinamicoError: ", e);
                error(NOMBRE_PAGINA + " - ReporteDinamico: " + e.getMessage());
            }
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        //Maru: selecciono el objeto de la tabla y de ahi obtengo los datos para imprimir el reporte, en vez de usar la pila.
//        if (ultimo) {
//            this.guardarEstadoObjetosUsados();
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//
//            try{
//
//                DocumentoRefinanciacion documentoRefinanciacion = null;
//                documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(2, DocumentoRefinanciacion.class);
//
////                documentoRefinanciacion = (DocumentoRefinanciacion) this.getRequestBean1().getObjetoABM();
//
//                this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
//                this.getCommunicationSAICBean().getRemoteSystemImpresion().imprimirReconocimientoDeuda(documentoRefinanciacion);
//            }catch(Exception ex){
//                log(CASO_NAVEGACION+"_ImprimirLiquidacionesSHPS",ex);
//                error(NOMBRE_PAGINA + " - Imprimir: "+ ex.getMessage());
//            }
//            // APLICAR LOGICA AQUI...
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//            this.guardarOrdenamiento();
//            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
//            this.getElementoPila().setPosicionGlobal(pos.longValue());
//
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
    }
}
