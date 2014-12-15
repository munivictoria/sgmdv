/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.accionsocial.ABMFichaSocial;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
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
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.trascender.accionSocial.recurso.persistent.AspectoHabitacional;
import com.trascender.accionSocial.recurso.persistent.AspectoEconomico;
import com.trascender.accionSocial.recurso.persistent.Beneficiario;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.accionSocial.recurso.persistent.SolicitudBeneficio;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.convert.DateTimeConverter;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ModificarFichaSocial extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">

    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {

        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }

        if (this.getListaDelCommunication2() != null) {
            this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
        }

        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern("dd/MM/yy");
        dateTimeConverter1.setDateStyle("full");
    }
    private static long idSolicitudBeneficio = -1;
    private static long idFamiliarBeneficiario = -1;
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
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText staticText1) {
        this.staticText1 = staticText1;
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
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText staticText4) {
        this.staticText4 = staticText4;
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
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText staticText7) {
        this.staticText7 = staticText7;
    }
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText staticText8) {
        this.staticText8 = staticText8;
    }
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }
    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText staticText10) {
        this.staticText10 = staticText10;
    }
    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText staticText11) {
        this.staticText11 = staticText11;
    }
    private StaticText staticText12 = new StaticText();

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText staticText12) {
        this.staticText12 = staticText12;
    }
    private StaticText staticText13 = new StaticText();

    public StaticText getStaticText13() {
        return staticText13;
    }

    public void setStaticText13(StaticText staticText13) {
        this.staticText13 = staticText13;
    }
    private StaticText staticText14 = new StaticText();

    public StaticText getStaticText14() {
        return staticText14;
    }

    public void setStaticText14(StaticText staticText14) {
        this.staticText14 = staticText14;
    }
    private StaticText staticText15 = new StaticText();

    public StaticText getStaticText15() {
        return staticText15;
    }

    public void setStaticText15(StaticText staticText15) {
        this.staticText15 = staticText15;
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
    // cantidad de objetos administrados por la pagina
    //private final int CANTIDAD_OBJETOS = 2;
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Modificar FichaSocial";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarFichaSocial";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
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
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label label3) {
        this.label3 = label3;
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

    public void setLabel5(Label label5) {
        this.label5 = label5;
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
    private Label label15 = new Label();

    public Label getLabel15() {
        return label15;
    }

    public void setLabel15(Label label15) {
        this.label15 = label15;
    }
    private Label label16 = new Label();

    public Label getLabel16() {
        return label16;
    }

    public void setLabel16(Label label16) {
        this.label16 = label16;
    }
    private Label label17 = new Label();

    public Label getLabel17() {
        return label17;
    }

    public void setLabel17(Label label17) {
        this.label17 = label17;
    }
    private Label label18 = new Label();

    public Label getLabel18() {
        return label18;
    }

    public void setLabel18(Label label18) {
        this.label18 = label18;
    }
    private Label label19 = new Label();

    public Label getLabel19() {
        return label19;
    }

    public void setLabel19(Label label19) {
        this.label19 = label19;
    }
    private Label label20 = new Label();

    public Label getLabel20() {
        return label20;
    }

    public void setLabel20(Label label20) {
        this.label20 = label20;
    }
    private TextField tfNroPersonas = new TextField();

    public TextField getTfNroPersonas() {
        return tfNroPersonas;
    }

    public void setTfNroPersonas(TextField tfNroPersonas) {
        this.tfNroPersonas = tfNroPersonas;
    }
    private TextField tfVivienda = new TextField();

    public TextField getTfVivienda() {
        return tfVivienda;
    }

    public void setTfVivienda(TextField tfVivienda) {
        this.tfVivienda = tfVivienda;
    }
    private TextField tfTenencia = new TextField();

    public TextField getTfTenencia() {
        return tfTenencia;
    }

    public void setTfTenencia(TextField tfTenencia) {
        this.tfTenencia = tfTenencia;
    }
    private TextField tfNroCamas = new TextField();

    public TextField getTfNroCamas() {
        return tfNroCamas;
    }

    public void setTfNroCamas(TextField tfNroCamas) {
        this.tfNroCamas = tfNroCamas;
    }
    private TextField tfNroAmbientes = new TextField();

    public TextField getTfNroAmbientes() {
        return tfNroAmbientes;
    }

    public void setTfNroAmbientes(TextField tfNroAmbientes) {
        this.tfNroAmbientes = tfNroAmbientes;
    }
    private TextField tfNroCasas = new TextField();

    public TextField getTfNroCasas() {
        return tfNroCasas;
    }

    public void setTfNroCasas(TextField tfNroCasas) {
        this.tfNroCasas = tfNroCasas;
    }
    private TextField tfNroTerrenos = new TextField();

    public TextField getTfNroTerrenos() {
        return tfNroTerrenos;
    }

    public void setTfNroTerrenos(TextField tfNroTerrenos) {
        this.tfNroTerrenos = tfNroTerrenos;
    }
    private TextField tfNroCampos = new TextField();

    public TextField getTfNroCampos() {
        return tfNroCampos;
    }

    public void setTfNroCampos(TextField tfNroCampos) {
        this.tfNroCampos = tfNroCampos;
    }
    private TextField tfVehiculo = new TextField();

    public TextField getTfVehiculo() {
        return tfVehiculo;
    }

    public void setTfVehiculo(TextField tfVehiculo) {
        this.tfVehiculo = tfVehiculo;
    }
    private TextField tfIndustria = new TextField();

    public TextField getTfIndustria() {
        return tfIndustria;
    }

    public void setTfIndustria(TextField tfIndustria) {
        this.tfIndustria = tfIndustria;
    }
    private TextField tfActividadLaboral = new TextField();

    public TextField getTfActividadLaboral() {
        return tfActividadLaboral;
    }

    public void setTfActividadLaboral(TextField tfActividadLaboral) {
        this.tfActividadLaboral = tfActividadLaboral;
    }
    private TextField tfComercio = new TextField();

    public TextField getTfComercio() {
        return tfComercio;
    }

    public void setTfComercio(TextField tfComercio) {
        this.tfComercio = tfComercio;
    }
    private TextField tfTitular = new TextField();

    public TextField getTfTitular() {
        return tfTitular;
    }

    public void setTfTitular(TextField tfTitular) {
        this.tfTitular = tfTitular;
    }
    private Button btnAgregarTitular = new Button();

    public Button getBtnAgregarTitular() {
        return btnAgregarTitular;
    }

    public void setBtnAgregarTitular(Button btnAgregarTitular) {
        this.btnAgregarTitular = btnAgregarTitular;
    }
    private Button btnModificarTitular = new Button();

    public Button getBtnModificarTitular() {
        return btnModificarTitular;
    }

    public void setBtnModificarTitular(Button btnModificarTitular) {
        this.btnModificarTitular = btnModificarTitular;
    }
    private Button btnEliminarTitular = new Button();

    public Button getBtnEliminarTitular() {
        return btnEliminarTitular;
    }

    public void setBtnEliminarTitular(Button btnEliminarTitular) {
        this.btnEliminarTitular = btnEliminarTitular;
    }
    private Button btnAgregarFamiliar = new Button();

    public Button getBtnAgregarFamiliar() {
        return btnAgregarFamiliar;
    }

    public void setBtnAgregarFamiliar(Button btnAgregarFamiliar) {
        this.btnAgregarFamiliar = btnAgregarFamiliar;
    }
    private Button btnModificarFamiliar = new Button();

    public Button getBtnModificarFamiliar() {
        return btnModificarFamiliar;
    }

    public void setBtnModificarFamiliar(Button btnModificarFamiliar) {
        this.btnModificarFamiliar = btnModificarFamiliar;
    }
    private Button btnEliminarFamiliar = new Button();

    public Button getBtnEliminarFamiliar() {
        return btnEliminarFamiliar;
    }

    public void setBtnEliminarFamiliar(Button btnEliminarFamiliar) {
        this.btnEliminarFamiliar = btnEliminarFamiliar;
    }
    private Button btnAgregarSolicitud = new Button();

    public Button getBtnAgregarSolicitud() {
        return btnAgregarSolicitud;
    }

    public void setBtnAgregarSolicitud(Button btnAgregarSolicitud) {
        this.btnAgregarSolicitud = btnAgregarSolicitud;
    }
    private Button btnModificarSolicitud = new Button();

    public Button getBtnModificarSolicitud() {
        return btnModificarSolicitud;
    }

    public void setBtnModificarSolicitud(Button btnModificarSolicitud) {
        this.btnModificarSolicitud = btnModificarSolicitud;
    }
    private Button btnEliminarSolicitud = new Button();

    public Button getBtnEliminarSolicitud() {
        return btnEliminarSolicitud;
    }

    public void setBtnEliminarSolicitud(Button btnEliminarSolicitud) {
        this.btnEliminarSolicitud = btnEliminarSolicitud;
    }
    private Button btnAceptarSolicitud = new Button();

    public Button getBtnAceptarSolicitud() {
        return btnAceptarSolicitud;
    }

    public void setBtnAceptarSolicitud(Button btnAceptarSolicitud) {
        this.btnAceptarSolicitud = btnAceptarSolicitud;
    }
    private Button btnRechazarSolicitud = new Button();

    public Button getBtnRechazarSolicitud() {
        return btnRechazarSolicitud;
    }

    public void setBtnRechazarSolicitud(Button btnRechazarSolicitud) {
        this.btnRechazarSolicitud = btnRechazarSolicitud;
    }
    private TabSet tabSet1 = new TabSet();

    public TabSet getTabSet1() {
        return tabSet1;
    }

    public void setTabSet1(TabSet tabSet1) {
        this.tabSet1 = tabSet1;
    }
    private TextField tfCodigo = new TextField();

    public TextField getTfCodigo() {
        return tfCodigo;
    }

    public void setTfCodigo(TextField tfCodigo) {
        this.tfCodigo = tfCodigo;
    }
    private Label label22 = new Label();

    public Label getLabel22() {
        return label22;
    }

    public void setLabel22(Label label22) {
        this.label22 = label22;
    }
    private Label label23 = new Label();

    public Label getLabel23() {
        return label23;
    }

    public void setLabel23(Label label23) {
        this.label23 = label23;
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
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    private Checkbox cbBanioCompleto = new Checkbox();
    // </editor-fold>

    public Checkbox getCbBanioCompleto() {
        return cbBanioCompleto;
    }

    public void setCbBanioCompleto(Checkbox cbBanioCompleto) {
        this.cbBanioCompleto = cbBanioCompleto;
    }
    private Checkbox cbBanioInterno = new Checkbox();

    public Checkbox getCbBanioInterno() {
        return cbBanioInterno;
    }

    public void setCbBanioInterno(Checkbox cbBanioInterno) {
        this.cbBanioInterno = cbBanioInterno;
    }
    private Checkbox cbAgua = new Checkbox();

    public Checkbox getCbAgua() {
        return cbAgua;
    }

    public void setCbAgua(Checkbox cbAgua) {
        this.cbAgua = cbAgua;
    }
    private Checkbox cbLuz = new Checkbox();

    public Checkbox getCbLuz() {
        return cbLuz;
    }

    public void setCbLuz(Checkbox cbLuz) {
        this.cbLuz = cbLuz;
    }
    private Checkbox cbCloaca = new Checkbox();

    public Checkbox getCbCloaca() {
        return cbCloaca;
    }

    public void setCbCloaca(Checkbox cbCloaca) {
        this.cbCloaca = cbCloaca;
    }
    private Checkbox cbGasNatural = new Checkbox();

    public Checkbox getCbGasNatural() {
        return cbGasNatural;
    }

    public void setCbGasNatural(Checkbox cbGasNatural) {
        this.cbGasNatural = cbGasNatural;
    }
    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table table1) {
        this.table1 = table1;
    }
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    private ObjectListDataProvider ldpGrupoFamiliar = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpGrupoFamiliar() {
        return ldpGrupoFamiliar;
    }

    public void setLdpGrupoFamiliar(ObjectListDataProvider ldpGrupoFamiliar) {
        this.ldpGrupoFamiliar = ldpGrupoFamiliar;
    }
    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tableColumn2) {
        this.tableColumn2 = tableColumn2;
    }
    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tableColumn3) {
        this.tableColumn3 = tableColumn3;
    }
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tableColumn4) {
        this.tableColumn4 = tableColumn4;
    }
    private RadioButton radioButton1 = new RadioButton();

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton rb) {
        this.radioButton1 = rb;
    }
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private Table table2 = new Table();

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table table2) {
        this.table2 = table2;
    }
    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup trg) {
        this.tableRowGroup2 = trg;
    }
    private ObjectListDataProvider ldpSolicitudesBeneficios = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpSolicitudesBeneficios() {
        return ldpSolicitudesBeneficios;
    }

    public void setLdpSolicitudesBeneficios(ObjectListDataProvider ldpSolicitudesBeneficios) {
        this.ldpSolicitudesBeneficios = ldpSolicitudesBeneficios;
    }
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tableColumn5) {
        this.tableColumn5 = tableColumn5;
    }
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tableColumn6) {
        this.tableColumn6 = tableColumn6;
    }
    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tableColumn7) {
        this.tableColumn7 = tableColumn7;
    }
    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tableColumn8) {
        this.tableColumn8 = tableColumn8;
    }
    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tableColumn9) {
        this.tableColumn9 = tableColumn9;
    }
    private TableColumn tableColumn10 = new TableColumn();

    public TableColumn getTableColumn10() {
        return tableColumn10;
    }

    public void setTableColumn10(TableColumn tableColumn10) {
        this.tableColumn10 = tableColumn10;
    }
    private TableColumn tableColumn11 = new TableColumn();

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tableColumn11) {
        this.tableColumn11 = tableColumn11;
    }
    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tableColumn12) {
        this.tableColumn12 = tableColumn12;
    }
    private TableColumn tableColumn13 = new TableColumn();

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tableColumn13) {
        this.tableColumn13 = tableColumn13;
    }
    private TableColumn tableColumn14 = new TableColumn();

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tableColumn14) {
        this.tableColumn14 = tableColumn14;
    }
    private TableColumn tableColumn15 = new TableColumn();

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tableColumn15) {
        this.tableColumn15 = tableColumn15;
    }
    private TableColumn tableColumn16 = new TableColumn();

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tableColumn16) {
        this.tableColumn16 = tableColumn16;
    }
    private TableColumn tableColumn17 = new TableColumn();

    public TableColumn getTableColumn17() {
        return tableColumn17;
    }

    public void setTableColumn17(TableColumn tableColumn17) {
        this.tableColumn17 = tableColumn17;
    }
    private RadioButton radioButton2 = new RadioButton();

    public RadioButton getRadioButton2() {
        return radioButton2;
    }

    public void setRadioButton2(RadioButton rb) {
        this.radioButton2 = rb;
    }
    private PanelGroup groupPanel2 = new PanelGroup();

    public PanelGroup getGroupPanel2() {
        return groupPanel2;
    }

    public void setGroupPanel2(PanelGroup pg) {
        this.groupPanel2 = pg;
    }

    public String getCurrentRow1() {
        return tableRowGroup1.getRowKey().getRowId();
    }

    public void setCurrentRow1(int row) {
    }
    private Object lastSelected1 = null;

    public Object getRBSelected1() {
        String sv = (String) radioButton1.getSelectedValue();
        return sv.equals(lastSelected1) ? sv : null;
    }

    public void setRBSelected1(Object selected) {
        if (selected != null) {
            lastSelected1 = selected;
        }
    }

    public String getCurrentRow2() {
        return tableRowGroup2.getRowKey().getRowId();
    }

    public void setCurrentRow2(int row) {
    }
    private Object lastSelected2 = null;

    public Object getRBSelected2() {
        String sv = (String) radioButton2.getSelectedValue();
        return sv.equals(lastSelected2) ? sv : null;
    }

    public void setRBSelected2(Object selected) {
        if (selected != null) {
            lastSelected2 = selected;
        }
    }
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }

    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ModificarFichaSocial() {
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
    protected muni.CommunicationAccionSocialBean getComunicationAccionSocialBean() {
        return (muni.CommunicationAccionSocialBean) getBean("CommunicationAccionSocialBean");
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
            log("ModificarFichaSocial Initialization Failure", e);
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
            if (this.getSessionBean1().getRutaOperacion().contains("AgregarSolicitudBeneficio")) {
                tabSet1.setSelected("four");
            }
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
        ep.getObjetos().add(ind++, new FichaSocial());
        ep.getObjetos().add(ind++, new AspectoHabitacional());
        ep.getObjetos().add(ind++, new AspectoEconomico());

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        int ind = 0;
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(ind++, FichaSocial.class);
        AspectoHabitacional aspectoHabitacional = (AspectoHabitacional) this.obtenerObjetoDelElementoPila(ind++, AspectoHabitacional.class);
        AspectoEconomico aspectoEconomico = (AspectoEconomico) this.obtenerObjetoDelElementoPila(ind++, AspectoEconomico.class);

        Object numero = this.getTfCodigo().getText();
        Object fecha = this.getTfFecha().getText();

        //Aspecto Habitacional:
        Object nroPersona = this.getTfNroPersonas().getText();
        Object vivienda = this.getTfVivienda().getText();
        Object tenencia = this.getTfTenencia().getText();
        Object nroCamas = this.getTfNroCamas().getText();
        Object nroAmbientes = this.getTfNroAmbientes().getText();
        Object banioCompleto = this.getCbBanioCompleto().getValue();
        Object banioInterno = this.getCbBanioInterno().getValue();
        Object agua = this.getCbAgua().getValue();
        Object luz = this.getCbLuz().getValue();
        Object cloaca = this.getCbCloaca().getValue();
        Object gasNatural = this.getCbGasNatural().getValue();

        //Aspecto economico:
        Object nroCasas = this.getTfNroCasas().getText();
        Object nroTerrenos = this.getTfNroTerrenos().getText();
        Object nroCampos = this.getTfNroCampos().getText();
        Object vehiculo = this.getTfVehiculo().getText();
        Object industria = this.getTfIndustria().getText();
        Object actividadLaboral = this.getTfActividadLaboral().getText();
        Object comercio = this.getTfComercio().getText();


        /////////////////

        if (numero != null && numero != "") {
            fichaSocial.setNumero(Conversor.getIntegerDeString(numero.toString()));
        } else {
            fichaSocial.setNumero(null);
        }
        if (fecha != null && fecha != "") {
            fichaSocial.setFecha(Conversor.getFechaCortaDeString(fecha.toString()));
        } else {
            fichaSocial.setFecha(null);
        }

        //Aspecto Habitacional:
        //  if (fichaSocial.getAspectoHabitacional() != null) {
        if (nroPersona != null && nroPersona != "") {
            aspectoHabitacional.setNumeroPersonas(Conversor.getIntegerDeString(nroPersona.toString()));
        } else {
            aspectoHabitacional.setNumeroPersonas(null);
        }
        if (vivienda != null && vivienda != "") {
            aspectoHabitacional.setVivienda(vivienda.toString());
        } else {
            aspectoHabitacional.setVivienda(null);
        }
        if (tenencia != null && tenencia != "") {
            aspectoHabitacional.setTenencia(tenencia.toString());
        } else {
            aspectoHabitacional.setTenencia(null);
        }
        if (nroCamas != null && nroCamas != "") {
            aspectoHabitacional.setNumeroCamas(Conversor.getIntegerDeString(nroCamas.toString()));
        } else {
            aspectoHabitacional.setNumeroCamas(null);
        }
        if (nroAmbientes != null && nroAmbientes != "") {
            aspectoHabitacional.setNumeroAmbientes(Conversor.getIntegerDeString(nroAmbientes.toString()));
        } else {
            aspectoHabitacional.setNumeroAmbientes(null);
        }
        if (banioCompleto != null && banioCompleto != "") {
            aspectoHabitacional.setBanioCompleto(((Boolean) banioCompleto).booleanValue());
        } else {
            aspectoHabitacional.setBanioCompleto(false);
        }
        if (banioInterno != null && banioInterno != "") {
            aspectoHabitacional.setBanioInterno(((Boolean) banioInterno).booleanValue());
        } else {
            aspectoHabitacional.setBanioInterno(false);
        }
        if (agua != null && agua != "") {
            aspectoHabitacional.setAgua(((Boolean) agua).booleanValue());
        } else {
            aspectoHabitacional.setAgua(false);
        }
        if (luz != null && luz != "") {
            aspectoHabitacional.setLuz(((Boolean) luz).booleanValue());
        } else {
            aspectoHabitacional.setLuz(false);
        }
        if (cloaca != null && cloaca != "") {
            aspectoHabitacional.setCloaca(((Boolean) cloaca).booleanValue());
        } else {
            aspectoHabitacional.setCloaca(false);
        }
        if (gasNatural != null && gasNatural != "") {
            aspectoHabitacional.setGasNatural(((Boolean) gasNatural).booleanValue());
        } else {
            aspectoHabitacional.setGasNatural(false);
        }
        //  }
        fichaSocial.setAspectoHabitacional(aspectoHabitacional);

        //Aspecto Economico:

        // if (fichaSocial.getAspectoEconomico() != null) {
        if (nroCasas != null && nroCasas != "") {
            aspectoEconomico.setNumeroCasas(Conversor.getIntegerDeString(nroCasas.toString()));
        } else {
            aspectoEconomico.setNumeroCasas(null);
        }
        if (nroTerrenos != null && nroTerrenos != "") {
            aspectoEconomico.setNumeroTerrenos(Conversor.getIntegerDeString(nroTerrenos.toString()));
        } else {
            aspectoEconomico.setNumeroTerrenos(null);
        }
        if (nroCampos != null && nroCampos != "") {
            aspectoEconomico.setNumeroCampos(Conversor.getIntegerDeString(nroCampos.toString()));
        } else {
            aspectoEconomico.setNumeroCampos(null);
        }
        if (vehiculo != null && vehiculo != "") {
            aspectoEconomico.setVehiculo(vehiculo.toString());
        } else {
            aspectoEconomico.setVehiculo(null);
        }
        if (industria != null && industria != "") {
            aspectoEconomico.setIndustria(industria.toString());
        } else {
            aspectoEconomico.setIndustria(null);
        }
        if (actividadLaboral != null && actividadLaboral != "") {
            aspectoEconomico.setActividadLaboral(actividadLaboral.toString());
        } else {
            aspectoEconomico.setActividadLaboral(null);
        }
        if (comercio != null && comercio != "") {
            aspectoEconomico.setComercio(comercio.toString());
        } else {
            aspectoEconomico.setComercio(null);
        }
        //}
        fichaSocial.setAspectoEconomico(aspectoEconomico);
//        if (tipoFichaSocial.getIdTipoFichaSocial()==-1) tipoFichaSocial = null;
//        fichaSocial.setTipoFichaSocial(tipoFichaSocial);
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, fichaSocial);
        this.getElementoPila().getObjetos().set(ind++, aspectoHabitacional);
        this.getElementoPila().getObjetos().set(ind++, aspectoEconomico);

    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        Beneficiario beneficiario = null;
        FichaSocial fichaSocial = null;
        SolicitudBeneficio solicitudBeneficio = null;
        AspectoHabitacional aspectoHabitacional = null;
        AspectoEconomico aspectoEconomico = null;

        // CAMBIAR: Obtener datos del Request y asignarlos a los textField
        if (this.getRequestBean1().getObjetoABM() != null) {
            fichaSocial = (FichaSocial) this.getRequestBean1().getObjetoABM();
            aspectoHabitacional = (AspectoHabitacional) fichaSocial.getAspectoHabitacional();
            aspectoEconomico = (AspectoEconomico) fichaSocial.getAspectoEconomico();
            this.getElementoPila().getObjetos().set(0, fichaSocial);
            this.getElementoPila().getObjetos().set(1, aspectoHabitacional);
            this.getElementoPila().getObjetos().set(2, aspectoEconomico);
        }

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);
            if (seleccionado instanceof Beneficiario) {
                beneficiario = (Beneficiario) seleccionado;
                if (this.getRequestBean1().getTipoSeleccion() == "TITULAR") {
                    fichaSocial.setTitular(beneficiario);
                } else {
                    if (beneficiario.getIdBeneficiario() == -1) {//si es nuevo
                        beneficiario.setIdBeneficiario(idFamiliarBeneficiario--);
                    }
                    fichaSocial.addFamiliar(beneficiario);
                }
                this.getElementoPila().getObjetos().set(0, fichaSocial);
            }
            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.
            if (seleccionado instanceof SolicitudBeneficio) {
                if (seleccionado != null) {
                    solicitudBeneficio = (SolicitudBeneficio) seleccionado;
                    if (solicitudBeneficio.getIdSolicitudBeneficio() == -1) {//si es nuevo
                        solicitudBeneficio.setIdSolicitudBeneficio(idSolicitudBeneficio--);
                    }
                    fichaSocial.addSolicitudBeneficio(solicitudBeneficio);
                    this.getElementoPila().getObjetos().set(0, fichaSocial);
                }
            }
//        if (this.getElementoPila().getObjetos() != null && !this.getElementoPila().getObjetos().isEmpty()) {
//            // CAMBIAR:
//            fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);         
        }

        int ind = 0;
        fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(ind++, FichaSocial.class);
        aspectoHabitacional = (AspectoHabitacional) this.obtenerObjetoDelElementoPila(ind++, AspectoHabitacional.class);
        aspectoEconomico = (AspectoEconomico) this.obtenerObjetoDelElementoPila(ind++, AspectoEconomico.class);

        this.getTfCodigo().setText(fichaSocial.getNumero().toString());
        this.getTfFecha().setText(Conversor.getStringDeFechaCorta(fichaSocial.getFecha()));
        ////
        //Beneficiarios:
        if (fichaSocial.getTitular() != null) {
            this.getTfTitular().setText(fichaSocial.toString());
        }

        if (fichaSocial.getGrupoFamiliar().size() > 0) {
            this.setListaDelCommunication(new ArrayList(fichaSocial.getGrupoFamiliar()));
            this.getObjectListDataProvider().setList(new ArrayList(fichaSocial.getGrupoFamiliar()));
        }
        //Aspecto habitacional:
        if (aspectoHabitacional.getNumeroPersonas() != null) {
            this.getTfNroPersonas().setText(aspectoHabitacional.getNumeroPersonas().toString());
        }
        this.getTfVivienda().setText(aspectoHabitacional.getVivienda());
        this.getTfTenencia().setText(aspectoHabitacional.getTenencia());
        this.getTfNroCamas().setText(aspectoHabitacional.getNumeroCamas().toString());
        this.getTfNroAmbientes().setText(aspectoHabitacional.getNumeroAmbientes().toString());
        this.getCbBanioCompleto().setValue(new Boolean(aspectoHabitacional.isBanioCompleto()));
        this.getCbBanioInterno().setValue(new Boolean(aspectoHabitacional.isBanioInterno()));
        this.getCbAgua().setValue(new Boolean(aspectoHabitacional.isAgua()));
        this.getCbLuz().setValue(new Boolean(aspectoHabitacional.isLuz()));
        this.getCbCloaca().setValue(new Boolean(aspectoHabitacional.isCloaca()));
        this.getCbGasNatural().setValue(new Boolean(aspectoHabitacional.isGasNatural()));

        //Aspecto Economico:
        this.getTfNroCasas().setText(aspectoEconomico.getNumeroCasas().toString());
        this.getTfNroTerrenos().setText(aspectoEconomico.getNumeroTerrenos().toString());
        this.getTfNroCampos().setText(aspectoEconomico.getNumeroCampos().toString());
        this.getTfVehiculo().setText(aspectoEconomico.getVehiculo());
        this.getTfIndustria().setText(aspectoEconomico.getIndustria());
        this.getTfActividadLaboral().setText(aspectoEconomico.getActividadLaboral());
        this.getTfComercio().setText(aspectoEconomico.getComercio());

        //Solicitud Beneficio
        if (fichaSocial.getListaSolicitudBeneficio().size() > 0) {
            this.setListaDelCommunication2(new ArrayList(fichaSocial.getListaSolicitudBeneficio()));
            this.getObjectListDataProvider2().setList(new ArrayList(fichaSocial.getListaSolicitudBeneficio()));
        }

    }
    // </editor-fold>

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationAccionSocialBean().getListaGrupoFamiliar();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationAccionSocialBean().setListaGrupoFamiliar(lista);
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpGrupoFamiliar();
    }

    private ArrayList getListaDelCommunication2() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationAccionSocialBean().getListaSolicitudesBeneficio();
    }

    private void setListaDelCommunication2(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationAccionSocialBean().setListaSolicitudesBeneficio(lista);
    }

    private ObjectListDataProvider getObjectListDataProvider2() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpSolicitudesBeneficios();
    }

    private void refrescarTablaBeneficiarios() throws Exception {

        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);
        ArrayList listaFamiliaresBeneficiarios = new ArrayList();
        listaFamiliaresBeneficiarios.addAll(fichaSocial.getGrupoFamiliar());
        try {
            this.setListaDelCommunication(listaFamiliaresBeneficiarios);
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());

        } catch (Exception ex) {
            this.getObjectListDataProvider().setList(null);
        }
    }

    private void refrescarTablaSolicitudesBeneficio() throws Exception {

        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);
        ArrayList listaSolicitudesBeneficio = new ArrayList();
        listaSolicitudesBeneficio.addAll(fichaSocial.getListaSolicitudBeneficio());
        try {
            this.setListaDelCommunication2(listaSolicitudesBeneficio);
            this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());

        } catch (Exception ex) {
            this.getObjectListDataProvider2().setList(null);
        }
    }

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

    public RowKey getSeleccionado2() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup2");
            // CAMBIAR: Utilizar el ListDataProvider correspondiente
            rk = this.getObjectListDataProvider2().getRowKey(aRowId);

        } catch (Exception ex) {
        }
        return rk;
    }

    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup1");
            // CAMBIAR: Utilizar el ListDataProvider correspondiente
            rk = this.getObjectListDataProvider().getRowKey(aRowId);

        } catch (Exception ex) {
        }
        return rk;
    }

    private int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
    }
    private RowKey rowKeySeleccionado = null;

    public RowKey getRowKeySeleccionado() {
        return rowKeySeleccionado;
    }

    public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
        this.rowKeySeleccionado = rowKeySeleccionado;
    }
    // </editor-fold>

    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = null;
        List listaBeneficiarios = new ArrayList();
        List listaSolBeneficio = new ArrayList();

        if (ultimo) {
            try {

                // CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[10];
                String[] nomNoVacios = new String[10];
                UIComponent[] fecha = new UIComponent[1];
                String[] nomFecha = new String[1];
                UIComponent[] esEntero = new UIComponent[7];
                String[] nomEsEntero = new String[7];

                int pos = 0;
                noVacios[pos] = this.getTfCodigo();
                nomNoVacios[pos++] = "N\372mero";
                noVacios[pos] = this.getTfFecha();
                nomNoVacios[pos++] = "Fecha";
                noVacios[pos] = this.getTfTitular();
                nomNoVacios[pos++] = "Titular";
                noVacios[pos] = this.getTfNroPersonas();
                nomNoVacios[pos++] = "N\372mero de Personas";
                noVacios[pos] = this.getTfTenencia();
                nomNoVacios[pos++] = "Tenencia";
                noVacios[pos] = this.getTfNroCamas();
                nomNoVacios[pos++] = "N\372mero de Camas";
                noVacios[pos] = this.getTfNroAmbientes();
                nomNoVacios[pos++] = "N\372mero de Ambientes";
                noVacios[pos] = this.getTfNroCasas();
                nomNoVacios[pos++] = "N\372mero de Casas";
                noVacios[pos] = this.getTfNroTerrenos();
                nomNoVacios[pos++] = "N\372mero de Terrenos";
                noVacios[pos] = this.getTfNroCampos();
                nomNoVacios[pos++] = "N\372mero de Campos";

                pos = 0;
                fecha[pos] = this.getTfFecha();
                nomFecha[pos++] = "Fecha";

                pos = 0;
                esEntero[pos] = this.getTfCodigo();
                nomEsEntero[pos++] = "N\372mero";
                esEntero[pos] = this.getTfNroPersonas();
                nomEsEntero[pos++] = "N\372mero de Personas";
                esEntero[pos] = this.getTfNroCamas();
                nomEsEntero[pos++] = "N\372mero de Camas";
                esEntero[pos] = this.getTfNroAmbientes();
                nomEsEntero[pos++] = "N\372mero de Ambientes";
                esEntero[pos] = this.getTfNroCasas();
                nomEsEntero[pos++] = "N\372mero de Casas";
                esEntero[pos] = this.getTfNroTerrenos();
                nomEsEntero[pos++] = "N\372mero de Terrenos";
                esEntero[pos] = this.getTfNroCampos();
                nomEsEntero[pos++] = "N\372mero de Campos";

                v.noSonVacios(noVacios, nomNoVacios);
                v.formatoFechaValido(fecha, nomFecha);
                v.esNumero(esEntero, nomEsEntero);

                ///////////////saco el setvalid porque no funciona con los tab///////////
                for (int i = 0; i < noVacios.length; i++) {
                    ((UIInput) noVacios[i]).setValid(true);
                }

                for (int i = 0; i < fecha.length; i++) {
                    ((UIInput) fecha[i]).setValid(true);
                }

                for (int i = 0; i < esEntero.length; i++) {
                    ((UIInput) esEntero[i]).setValid(true);
                }
                //////////////////////     

                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                this.guardarEstadoObjetosUsados();
                fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

                // verificar que tenga al menos una sol de beneficio cargada
                if (fichaSocial.getListaSolicitudBeneficio().isEmpty()) {
                    warn("Debe ingresar al menos una Solicitud de Beneficio.");
                    return null;
                }

                //////////pasar a -1 los id de los registros nuevos/////////
            
                for (Iterator it = fichaSocial.getGrupoFamiliar().iterator(); it.hasNext();) {
                    Beneficiario beneficiario = (Beneficiario) it.next();
                    if (beneficiario.getIdBeneficiario() < 0) {                      
                        beneficiario.setIdBeneficiario(-1);
                        }
                }

                for (Iterator it = fichaSocial.getListaSolicitudBeneficio().iterator(); it.hasNext();) {
                    SolicitudBeneficio solicitudBeneficio = (SolicitudBeneficio) it.next();
                    if (solicitudBeneficio.getIdSolicitudBeneficio() < 0) {
                        solicitudBeneficio.setIdSolicitudBeneficio(-1);
                    }
                }

                // CAMBIAR: Utilizar el EJBClient y ListDataProvider adecuados.
                this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
                FichaSocial fichaSocialDevuelta = (FichaSocial) this.getComunicationAccionSocialBean().getRemoteSystemFichaSocial().updateFichaSocial(fichaSocial);

                info("La Ficha Social se modific\363 exitosamente.");
                retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);
            } catch (Exception ex) {

                 //en caso de que el update tire error, los registros quedan en en -1 y no funka mas el eliminar/mod de las tablas
                //por este motivo, seteo valores negativos nuevamente.-

                for (Iterator it = fichaSocial.getGrupoFamiliar().iterator(); it.hasNext();) {
                    Beneficiario beneficiario = (Beneficiario) it.next();
                    if (beneficiario.getIdBeneficiario() == -1) {                    
                        beneficiario.setIdBeneficiario(idFamiliarBeneficiario--);                      
                    }
                    listaBeneficiarios.add(beneficiario);
                }

                fichaSocial.getGrupoFamiliar().clear();
                fichaSocial.setGrupoFamiliar(new HashSet(listaBeneficiarios));

                for (Iterator it = fichaSocial.getListaSolicitudBeneficio().iterator(); it.hasNext();) {
                    SolicitudBeneficio solicitudBeneficio = (SolicitudBeneficio) it.next();
                    if (solicitudBeneficio.getIdSolicitudBeneficio() == -1) {
                        solicitudBeneficio.setIdSolicitudBeneficio(idSolicitudBeneficio--);
                    }
                    listaSolBeneficio.add(solicitudBeneficio);
                }

                fichaSocial.getListaSolicitudBeneficio().clear();
                fichaSocial.setListaSolicitudBeneficio(new HashSet(listaSolBeneficio));

                this.getElementoPila().getObjetos().set(0, fichaSocial);
                this.mostrarEstadoObjetosUsados();
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
            // CAMBIAR: comentar esta linea y cambiar el retorno
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnAgregarFamiliar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion("FAMILIAR");
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AgregarBeneficiario";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnModificarFamiliar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {

                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.

                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    Beneficiario beneficiario = (Beneficiario) obj;
                    fichaSocial.getGrupoFamiliar().remove(beneficiario);

                    this.getRequestBean1().setObjetoABM(beneficiario);
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ModificarError:", ex);
                error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion("FAMILIAR");
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            if (rk != null) {

                retorno = "ModificarBeneficiario";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnEliminarFamiliar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    Beneficiario beneficiario = (Beneficiario) obj;
                    fichaSocial.getGrupoFamiliar().remove(beneficiario);
                    this.getElementoPila().getObjetos().set(0, fichaSocial);

                    //   this.getObjectListDataProvider().commitChanges();
                    this.refrescarTablaBeneficiarios();
                }
            } catch (Exception ex) {
            }

            this.guardarEstadoObjetosUsados();
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnAgregarTitular_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion("TITULAR");
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AgregarBeneficiario";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnModificarTitular_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            Beneficiario beneficiario = (Beneficiario) fichaSocial.getTitular();
            if (beneficiario != null) {
                this.getRequestBean1().setObjetoABM(beneficiario);
                this.getRequestBean1().setTipoSeleccion("TITULAR");
                this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
                // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
                retorno = "ModificarBeneficiario";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnEliminarTitular_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);
        if (ultimo) {


            fichaSocial.setTitular(null);
            this.getTfTitular().setText(null);
            this.getElementoPila().getObjetos().set(0, fichaSocial);
            //            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnAgregarSolicitud_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion(null);
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AgregarSolicitudBeneficio";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnAceptarSolicitud_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado2();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.

                    Object obj = this.getObjectListDataProvider2().getObjects()[index];
                    SolicitudBeneficio solicitudBeneficio = (SolicitudBeneficio) obj;

                    if (!solicitudBeneficio.getEstado().equals(SolicitudBeneficio.Estado.NO_ENTREGADA)) {
                        warn("La Solicitud debe encontrarse en Estado NO ENTREGADA, para que ésta pueda ser Aceptada o Rechazada.");
                        return null;
                    }
                    fichaSocial.getListaSolicitudBeneficio().remove(solicitudBeneficio);

                    this.getRequestBean1().setObjetoABM(solicitudBeneficio);
                    this.setRowKeySeleccionado(this.getSeleccionado2());
                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ModificarError:", ex);
                error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion(null);
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            if (rk != null) {
                retorno = "AceptarSolicitudBeneficio";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnRechazarSolicitud_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado2();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.

                    Object obj = this.getObjectListDataProvider2().getObjects()[index];
                    SolicitudBeneficio solicitudBeneficio = (SolicitudBeneficio) obj;
                    if (!solicitudBeneficio.getEstado().equals(SolicitudBeneficio.Estado.NO_ENTREGADA)) {
                        warn("La Solicitud debe encontrarse en Estado NO ENTREGADA, para que ésta pueda ser Aceptada o Rechazada.");
                        return null;
                    }
                    fichaSocial.getListaSolicitudBeneficio().remove(solicitudBeneficio);

                    this.getRequestBean1().setObjetoABM(solicitudBeneficio);
                    this.setRowKeySeleccionado(this.getSeleccionado2());
                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ModificarError:", ex);
                error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion(null);
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            if (rk != null) {
                retorno = "RechazarSolicitudBeneficio";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnModificarSolicitud_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado2();

                if (rk != null) {

                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.

                    Object obj = this.getObjectListDataProvider2().getObjects()[index];
                    SolicitudBeneficio solicitudBeneficio = (SolicitudBeneficio) obj;
                    if (!solicitudBeneficio.getEstado().equals(SolicitudBeneficio.Estado.NO_ENTREGADA)) {
                        warn("La Solicitud debe encontrarse en Estado NO ENTREGADA, para que pueda ser modificada.");
                        return null;
                    }

                    fichaSocial.getListaSolicitudBeneficio().remove(solicitudBeneficio);

                    this.getRequestBean1().setObjetoABM(solicitudBeneficio);
                    this.setRowKeySeleccionado(this.getSeleccionado2());
                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ModificarError:", ex);
                error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion(null);
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            if (rk != null) {

                retorno = "ModificarSolicitudBeneficio";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnEliminarSolicitud_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        FichaSocial fichaSocial = (FichaSocial) this.obtenerObjetoDelElementoPila(0, FichaSocial.class);

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado2();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider2().getObjects()[index];

                    fichaSocial.getListaSolicitudBeneficio().remove((SolicitudBeneficio) obj);
                    this.getElementoPila().getObjetos().set(0, fichaSocial);

                    //this.getObjectListDataProvider2().commitChanges();
                    this.refrescarTablaSolicitudesBeneficio();
                }
            } catch (Exception ex) {
            }

            this.guardarEstadoObjetosUsados();
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
}
