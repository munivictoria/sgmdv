/*
 * ModificarTipoTasa.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */
package muni.habilitaciones.ABMTipoTasaFormulaAlicuota;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlPanelGrid;

import muni.CommunicationMesaEntradaBean;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.Listbox;
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
import com.sun.rave.web.ui.model.DefaultOptionsList;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaCompuesta;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaSimple;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AgregarTipoTasaFormulaAlicuota extends AbstractPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
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
    private final String NOMBRE_PAGINA = "F\363rmula de C\341lculo Alicuota";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarTipoTasaFormulaAlicuota";
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
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        ddOperador1DefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[]{new com.sun.rave.web.ui.model.Option("", ""),
                    new com.sun.rave.web.ui.model.Option(">", "es mayor que"),
                    new com.sun.rave.web.ui.model.Option("<", "es menor que"),
                    new com.sun.rave.web.ui.model.Option("==", "es igual que"),
                    new com.sun.rave.web.ui.model.Option(">=", "es mayor o igual que"),
                    new com.sun.rave.web.ui.model.Option("<=", "es menor o igual que"),
                    new com.sun.rave.web.ui.model.Option("== 1", "es verdadero"),
                    new com.sun.rave.web.ui.model.Option("== 0", "es falso")});
        ddOperador1DefaultOptions.setSelectedValue("");

        ddUnion1DefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[]{new com.sun.rave.web.ui.model.Option("", ""), new com.sun.rave.web.ui.model.Option("&&", "Y"), new com.sun.rave.web.ui.model.Option("||", "O")});
        ddUnion1DefaultOptions.setSelectedValue("");

        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        if (this.getListaDelCommunication4() != null) {
            this.getObjectListDataProvider4().setList(this.getListaDelCommunication4());
        }
        if (this.getListaDelCommunicationVariablesSimples() != null) {
            this.getObjectListDataProviderVariablesSimples().setList(new ArrayList(this.getListaDelCommunicationVariablesSimples()));
        }
        if (this.getListaDelCommunicationVariablesCompuestas() != null) {
            this.getObjectListDataProviderVariablesCompuestas().setList(new ArrayList(this.getListaDelCommunicationVariablesCompuestas()));
        }
        
        Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(VariableFormulaCompuesta.Tipo.values(), "may");
        ddTipoDefaultOptions.setOptions(op);
    }
    
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    
    private Table table4 = new Table();
    
    public Table getTable4() {
        return table4;
    }

    public void setTable4(Table table4) {
        this.table4 = table4;
    }
    
    private ObjectListDataProvider ldpVariablesSimples = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpVariablesSimples() {
        return ldpVariablesSimples;
    }

    public void setLdpVariablesSimples(ObjectListDataProvider ldpVariablesSimples) {
        this.ldpVariablesSimples = ldpVariablesSimples;
    }
    
    private ObjectListDataProvider getObjectListDataProviderVariablesSimples() {
        return this.getLdpVariablesSimples();
    }
    
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tableColumn6) {
        this.tableColumn6 = tableColumn6;
    }
    
    private RadioButton radioButton4 = new RadioButton();

    public RadioButton getRadioButton4() {
        return radioButton4;
    }

    public void setRadioButton4(RadioButton radioButton4) {
        this.radioButton4 = radioButton4;
    }
    
    private Object lastSelected4 = null;
    
    public Object getRBSelected4() {
        String sv = (String) radioButton4.getSelectedValue();
        return sv.equals(lastSelected4) ? sv : null;
    }

    public void setRBSelected4(Object selected) {
        if (selected != null) {
            lastSelected4 = selected;
        }
    }
    
    public String getCurrentRow4() {
        return tableRowGroup1.getRowKey().getRowId();
    }

    public void setCurrentRow4(int row) {
    }
    
    private TableColumn tcNombre = new TableColumn();
    private TableColumn tcExpresion = new TableColumn();
    private TextField tfNombreVariable = new TextField();
    private TextArea taExpresion = new TextArea();
    private PanelGroup groupPanel6 = new PanelGroup();
    private Button btnAgregarVariable = new Button();
    private Button btnQuitarVariable = new Button();
    private boolean noNavega;
    private Checkbox cbIteraSobreAlicuotas = new Checkbox();

    public Checkbox getCbIteraSobreAlicuotas() {
        return cbIteraSobreAlicuotas;
    }

    public void setCbIteraSobreAlicuotas(Checkbox cbIteraSobreAlicuotas) {
        this.cbIteraSobreAlicuotas = cbIteraSobreAlicuotas;
    }

    public Button getBtnAgregarVariable() {
        return btnAgregarVariable;
    }

    public void setBtnAgregarVariable(Button btnAgregarVariable) {
        this.btnAgregarVariable = btnAgregarVariable;
    }

    public Button getBtnQuitarVariable() {
        return btnQuitarVariable;
    }

    public void setBtnQuitarVariable(Button btnQuitarVariable) {
        this.btnQuitarVariable = btnQuitarVariable;
    }

    public PanelGroup getGroupPanel6() {
        return groupPanel6;
    }

    public void setGroupPanel6(PanelGroup groupPanel6) {
        this.groupPanel6 = groupPanel6;
    }

    public TableColumn getTcNombre() {
        return tcNombre;
    }

    public void setTcNombre(TableColumn tcNombre) {
        this.tcNombre = tcNombre;
    }

    public TableColumn getTcExpresion() {
        return tcExpresion;
    }

    public void setTcExpresion(TableColumn tcExpresion) {
        this.tcExpresion = tcExpresion;
    }
    
    public TextField getTfNombreVariable() {
        return tfNombreVariable;
    }

    public void setTfNombreVariable(TextField tfNombreVariable) {
        this.tfNombreVariable = tfNombreVariable;
    }

    public TextArea getTaExpresion() {
        return taExpresion;
    }

    public void setTaExpresion(TextArea taExpresion) {
        this.taExpresion = taExpresion;
    }
    
    private Label lblVariablesFormula = new Label();

    public Label getLblVariablesFormula() {
        return lblVariablesFormula;
    }

    public void setLblVariablesFormula(Label lblVariablesFormula) {
        this.lblVariablesFormula = lblVariablesFormula;
    }
    
    private Table table5 = new PaginatedTable();
    private TableRowGroup tableRowGroup2 = new TableRowGroup();
    private TableColumn tableColumn7 = new TableColumn();
    private TableColumn tcNombre2 = new TableColumn();
    private TableColumn tcTipo = new TableColumn();
    private TableColumn tcExpresion2 = new TableColumn();
    private TextField tfNombreVariable2 = new TextField();
    private TextArea taExpresion2 = new TextArea();
    private DropDown ddTipo = new DropDown();
    private PanelGroup groupPanel7 = new PanelGroup();
    private Button btnAgregarVariableCompuesta = new Button();
    private Button btnQuitarVariableCompuesta = new Button();
    private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();
    private Label lblVariablesCompuestas = new Label();

    public Label getLblVariablesCompuestas() {
        return lblVariablesCompuestas;
    }

    public void setLblVariablesCompuestas(Label lblVariablesCompuestas) {
        this.lblVariablesCompuestas = lblVariablesCompuestas;
    }

    public SingleSelectOptionsList getDdTipoDefaultOptions() {
        return ddTipoDefaultOptions;
    }

    public void setDdTipoDefaultOptions(SingleSelectOptionsList ddTipoDefaultOptions) {
        this.ddTipoDefaultOptions = ddTipoDefaultOptions;
    }
    
    public Button getBtnAgregarVariableCompuesta() {
        return btnAgregarVariableCompuesta;
    }

    public void setBtnAgregarVariableCompuesta(Button btnAgregarVariableCompuesta) {
        this.btnAgregarVariableCompuesta = btnAgregarVariableCompuesta;
    }

    public Button getBtnQuitarVariableCompuesta() {
        return btnQuitarVariableCompuesta;
    }

    public void setBtnQuitarVariableCompuesta(Button btnQuitarVariableCompuesta) {
        this.btnQuitarVariableCompuesta = btnQuitarVariableCompuesta;
    }
    
    public PanelGroup getGroupPanel7() {
        return groupPanel7;
    }

    public void setGroupPanel7(PanelGroup groupPanel7) {
        this.groupPanel7 = groupPanel7;
    }
    
    public DropDown getDdTipo() {
        return ddTipo;
    }

    public void setDdTipo(DropDown ddTipo) {
        this.ddTipo = ddTipo;
    }

    public TextArea getTaExpresion2() {
        return taExpresion2;
    }

    public void setTaExpresion2(TextArea taExpresion2) {
        this.taExpresion2 = taExpresion2;
    }

    public TextField getTfNombreVariable2() {
        return tfNombreVariable2;
    }

    public void setTfNombreVariable2(TextField tfNombreVariable2) {
        this.tfNombreVariable2 = tfNombreVariable2;
    }

    public TableColumn getTcExpresion2() {
        return tcExpresion2;
    }

    public void setTcExpresion2(TableColumn tcExpresion2) {
        this.tcExpresion2 = tcExpresion2;
    }

    public TableColumn getTcNombre2() {
        return tcNombre2;
    }

    public void setTcNombre2(TableColumn tcNombre2) {
        this.tcNombre2 = tcNombre2;
    }

    public TableColumn getTcTipo() {
        return tcTipo;
    }

    public void setTcTipo(TableColumn tcTipo) {
        this.tcTipo = tcTipo;
    }
    
    private ObjectListDataProvider ldpVariablesCompuestas = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpVariablesCompuestas() {
        return ldpVariablesCompuestas;
    }

    public void setLdpVariablesCompuestas(ObjectListDataProvider ldpVariablesCompuestas) {
        this.ldpVariablesCompuestas = ldpVariablesCompuestas;
    }
    
    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
        this.tableRowGroup2 = tableRowGroup2;
    }
        
    private ObjectListDataProvider getObjectListDataProviderVariablesCompuestas() {
        return this.getLdpVariablesCompuestas();
    }

    public Table getTable5() {
        return table5;
    }

    public void setTable5(Table table5) {
        this.table5 = table5;
    }

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tableColumn7) {
        this.tableColumn7 = tableColumn7;
    }
    
    private RadioButton radioButton5 = new RadioButton();

    public RadioButton getRadioButton5() {
        return radioButton5;
    }
    public void setRadioButton5(RadioButton radioButton5) {
        this.radioButton5 = radioButton5;
    }
    
    private Object lastSelected5 = null;
    
    public Object getRBSelected5() {
        String sv = (String) radioButton5.getSelectedValue();
        return sv.equals(lastSelected5) ? sv : null;
    }

    public void setRBSelected5(Object selected) {
        if (selected != null) {
            lastSelected5 = selected;
        }
    }
    
    public String getCurrentRow5() {
        return tableRowGroup2.getRowKey().getRowId();
    }

    public void setCurrentRow5(int row) {
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
    private StaticText staticText1 = new StaticText();
    private StaticText staticText2 = new StaticText();
    private StaticText staticText3 = new StaticText();
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText staticText1) {
        this.staticText1 = staticText1;
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
    private StaticText stParametroPrueba = new StaticText();

    public StaticText getStParametroPrueba() {
        return stParametroPrueba;
    }

    public void setStParametroPrueba(StaticText stParametroPrueba) {
        this.stParametroPrueba = stParametroPrueba;
    }

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
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
    private TextArea taFormula = new TextArea();
    private TextArea taFormulaAlicuota = new TextArea();

    public TextArea getTaFormulaAlicuota() {
        return taFormulaAlicuota;
    }

    public void setTaFormulaAlicuota(TextArea taFormulaAlicuota) {
        this.taFormulaAlicuota = taFormulaAlicuota;
    }

    public TextArea getTaFormula() {
        return taFormula;
    }

    public void setTaFormula(TextArea ta) {
        this.taFormula = ta;
    }
    private ObjectListDataProvider ldpTipoParametro = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpTipoParametro() {
        return ldpTipoParametro;
    }

    public void setLdpTipoParametro(ObjectListDataProvider oldp) {
        this.ldpTipoParametro = oldp;
    }
    private Listbox lbVariables = new Listbox();

    public Listbox getLbVariables() {
        return lbVariables;
    }

    public void setLbVariables(Listbox l) {
        this.lbVariables = l;
    }
    private DefaultOptionsList lbVariablesDefaultOptions = new DefaultOptionsList();

    public DefaultOptionsList getLbVariablesDefaultOptions() {
        return lbVariablesDefaultOptions;
    }

    public void setLbVariablesDefaultOptions(DefaultOptionsList dol) {
        this.lbVariablesDefaultOptions = dol;
    }
    private SingleSelectOptionsList ddOperador1DefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdOperador1DefaultOptions() {
        return ddOperador1DefaultOptions;
    }

    public void setDdOperador1DefaultOptions(SingleSelectOptionsList ssol) {
        this.ddOperador1DefaultOptions = ssol;
    }
    private DropDown ddTiposParametro = new DropDown();

    public DropDown getDdTiposParametro() {
        return ddTiposParametro;
    }

    public void setDdTiposParametro(DropDown dd) {
        this.ddTiposParametro = dd;
    }
    private Button btnNuevoParametro = new Button();

    public Button getBtnNuevoParametro() {
        return btnNuevoParametro;
    }

    public void setBtnNuevoParametro(Button b) {
        this.btnNuevoParametro = b;
    }
    private SingleSelectOptionsList ddTiposParametroDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdTiposParametroDefaultOptions() {
        return ddTiposParametroDefaultOptions;
    }

    public void setDdTiposParametroDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddTiposParametroDefaultOptions = ssol;
    }
    private Button btnQuitarParametro = new Button();
    private Button btnProbarFormula = new Button();

    public Button getBtnProbarFormula() {
        return btnProbarFormula;
    }

    public void setBtnProbarFormula(Button btnProbarFormula) {
        this.btnProbarFormula = btnProbarFormula;
    }

    public TextField getTfFechaCobro() {
        return tfFechaCobro;
    }

    public void setTfFechaCobro(TextField tfFechaCobro) {
        this.tfFechaCobro = tfFechaCobro;
    }

    public Button getBtnQuitarParametro() {
        return btnQuitarParametro;
    }

    public void setBtnQuitarParametro(Button b) {
        this.btnQuitarParametro = b;
    }
    private TextField tfValor1 = new TextField();
    private TextField tfValorPrueba = new TextField();
    private TextField tfFechaLiquidacion = new TextField();
    private TextField tfFechaCobro = new TextField();

    public TextField getTfFechaLiquidacion() {
        return tfFechaLiquidacion;
    }

    public void setTfFechaLiquidacion(TextField tfFechaLiquidacion) {
        this.tfFechaLiquidacion = tfFechaLiquidacion;
    }

    public TextField getTfValorPrueba() {
        return tfValorPrueba;
    }

    public void setTfValorPrueba(TextField tfValorPrueba) {
        this.tfValorPrueba = tfValorPrueba;
    }

    public TextField getTfValor1() {
        return tfValor1;
    }

    public void setTfValor1(TextField tf) {
        this.tfValor1 = tf;
    }
    private DropDown ddOperador1 = new DropDown();

    public DropDown getDdOperador1() {
        return ddOperador1;
    }

    public void setDdOperador1(DropDown dd) {
        this.ddOperador1 = dd;
    }
    private DropDown ddVariable1 = new DropDown();

    public DropDown getDdVariable1() {
        return ddVariable1;
    }

    public void setDdVariable1(DropDown dd) {
        this.ddVariable1 = dd;
    }
    private Label label7 = new Label();
    private Label label8 = new Label();
    private Label label9 = new Label();
    private Label label10 = new Label();
    private Label label11 = new Label();

    public Label getLabel11() {
        return label11;
    }

    public void setLabel11(Label label11) {
        this.label11 = label11;
    }

    public Label getLabel10() {
        return label10;
    }

    public void setLabel10(Label label10) {
        this.label10 = label10;
    }

    public Label getLabel9() {
        return label9;
    }

    public void setLabel9(Label label9) {
        this.label9 = label9;
    }

    public Label getLabel8() {
        return label8;
    }

    public void setLabel8(Label label8) {
        this.label8 = label8;
    }

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }
    private DropDown ddUnion1 = new DropDown();

    public DropDown getDdUnion1() {
        return ddUnion1;
    }

    public void setDdUnion1(DropDown dd) {
        this.ddUnion1 = dd;
    }
    private SingleSelectOptionsList ddUnion1DefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdUnion1DefaultOptions() {
        return ddUnion1DefaultOptions;
    }

    public void setDdUnion1DefaultOptions(SingleSelectOptionsList ssol) {
        this.ddUnion1DefaultOptions = ssol;
    }
    private DropDown ddUnion2 = new DropDown();

    public DropDown getDdUnion2() {
        return ddUnion2;
    }

    public void setDdUnion2(DropDown dd) {
        this.ddUnion2 = dd;
    }
    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }
    private TextField tfValor2 = new TextField();

    public TextField getTfValor2() {
        return tfValor2;
    }

    public void setTfValor2(TextField tf) {
        this.tfValor2 = tf;
    }
    private DropDown ddVariable2 = new DropDown();

    public DropDown getDdVariable2() {
        return ddVariable2;
    }

    public void setDdVariable2(DropDown dd) {
        this.ddVariable2 = dd;
    }
    private DropDown ddOperador2 = new DropDown();

    public DropDown getDdOperador2() {
        return ddOperador2;
    }

    public void setDdOperador2(DropDown dd) {
        this.ddOperador2 = dd;
    }
    private DropDown ddOperador3 = new DropDown();

    public DropDown getDdOperador3() {
        return ddOperador3;
    }

    public void setDdOperador3(DropDown dd) {
        this.ddOperador3 = dd;
    }
    private TextField tfValor3 = new TextField();

    public TextField getTfValor3() {
        return tfValor3;
    }

    public void setTfValor3(TextField tf) {
        this.tfValor3 = tf;
    }
    private DropDown ddVariable3 = new DropDown();

    public DropDown getDdVariable3() {
        return ddVariable3;
    }

    public void setDdVariable3(DropDown dd) {
        this.ddVariable3 = dd;
    }
    private TextField tfValorFinal = new TextField();

    public TextField getTfValorFinal() {
        return tfValorFinal;
    }

    public void setTfValorFinal(TextField tf) {
        this.tfValorFinal = tf;
    }
    private TextField tfValorFinalSino = new TextField();

    public TextField getTfValorFinalSino() {
        return tfValorFinalSino;
    }

    public void setTfValorFinalSino(TextField tf) {
        this.tfValorFinalSino = tf;
    }
    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }
    private Button btnQuitarFormulaAlicuota = new Button();
    private Button btnAgregarFormulaAlicuota = new Button();

    public Button getBtnAgregarFormulaAlicuota() {
        return btnAgregarFormulaAlicuota;
    }

    public void setBtnAgregarFormulaAlicuota(Button btnAgregarFormulaAlicuota) {
        this.btnAgregarFormulaAlicuota = btnAgregarFormulaAlicuota;
    }

    public Button getBtnQuitarFormulaAlicuota() {
        return btnQuitarFormulaAlicuota;
    }

    public void setBtnQuitarFormulaAlicuota(Button btnQuitarFormulaAlicuota) {
        this.btnQuitarFormulaAlicuota = btnQuitarFormulaAlicuota;
    }
    private ObjectListDataProvider ldpParametroValor = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpParametroValor() {
        return ldpParametroValor;
    }

    public void setLdpParametroValor(ObjectListDataProvider oldp) {
        this.ldpParametroValor = oldp;
    }
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }
    private TableRowGroup tableRowGroup4 = new TableRowGroup();

    public TableRowGroup getTableRowGroup4() {
        return tableRowGroup4;
    }

    public void setTableRowGroup4(TableRowGroup trg) {
        this.tableRowGroup4 = trg;
    }
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn2 = new TableColumn();

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
    private HtmlPanelGrid gdpResultado = new HtmlPanelGrid();

    public HtmlPanelGrid getGdpResultado() {
        return gdpResultado;
    }

    public void setGdpResultado(HtmlPanelGrid hpg) {
        this.gdpResultado = hpg;
    }
    private StaticText stResultado = new StaticText();

    public StaticText getStResultado() {
        return stResultado;
    }

    public void setStResultado(StaticText st) {
        this.stResultado = st;
    }
    private DropDown ddPeriodicidad = new DropDown();

    public DropDown getDdPeriodicidad() {
        return ddPeriodicidad;
    }

    public void setDdPeriodicidad(DropDown dd) {
        this.ddPeriodicidad = dd;
    }
    // </editor-fold>

    /**
     * <p>Construir una instancia de bean de página.</p>
     */
    public AgregarTipoTasaFormulaAlicuota() {
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
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando se navega hasta
     * esta página, ya sea directamente mediante un URL o de manera indirecta a
     * través de la navegación de páginas. Puede personalizar este método para
     * adquirir recursos que se necesitarán para los controladores de eventos y
     * métodos del proceso, sin tener en cuenta si esta página realiza
     * procesamiento de devolución de envíos.</p>
     *
     * <p>Tenga en cuenta que si la petición actual es una devolución de envío,
     * los valores de propiedad de los componentes <strong>no</strong>
     * representan ningún valor enviado con esta petición. En su lugar,
     * representan los valores de propiedades que se guardaron para esta vista
     * cuando se procesó.</p>
     */
    @Override
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
            log("AgregarTipoTasaFormulaAlicuota Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicación que debe finalizar
        // *después* de que se inicien los componentes administrados
        // TODO - Agregar código de inicio propio aquí

    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando el árbol de
     * componentes se ha restaurado, pero antes de que se produzca el
     * procesamiento de cualquier evento. Este método <strong>sólo</strong> se
     * llamará en una petición de devolución de envío que esté procesando el
     * envío de un formulario. Puede personalizar este método para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    @Override
	public void preprocess() {
    }

    /**
     * <p>Método de devolución de llamada al que se llama justo antes del
     * procesamiento. <strong>Sólo</strong> se llamará a este método en la
     * página que se procesa, no se llamará, por ejemplo, en una página que ha
     * procesado una devolución de envío y a continuación ha navegado hasta otra
     * página. Puede personalizar este método para asignar recursos necesarios
     * para procesar esta página.</p>
     */
    @Override
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

            this.cargarValoresPorDefecto();
        }

        // Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());
        }

        //if (!recarga) {
        this.mostrarEstadoObjetosUsados();
        //}

        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));

    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando se completa el
     * procesamiento de esta petición, si se llamó al método
     * <code>init()</code> (sin tener en cuenta si se trata de la página que se
     * ha procesado o no). Puede personalizar este método para liberar los
     * recursos adquiridos en los métodos
     * <code>init()</code>,
     * <code>preprocess()</code> o
     * <code>prerender()</code> (o durante la ejecución de un controlador de
     * eventos).</p>
     */
    @Override
	public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new TipoTasa());//0
        ep.getObjetos().add(ind++, new ArrayList()); // lista de tipos de parametro (variables)// 1
        ep.getObjetos().add(ind++, new ArrayList()); // lista de parametros-valor //2
        ep.getObjetos().add(ind++, new HashMap());   // lista de parametros-valor (en un map) //3
        ep.getObjetos().add(ind++, null);   // TipoParametro seleccionado del dropDown//4
        ep.getObjetos().add(ind++, new ArrayList());    //componentes para composicion de formula  //5
        ep.getObjetos().add(ind++, new String());    //formula alicuota//6
        ep.getObjetos().add(ind++, null);    //fecha liquidacion
        ep.getObjetos().add(ind++, null);    //fecha cobro
        ep.getObjetos().add(ind++, new String());   //9 //tipoSeleccion =  add o edit
        ep.getObjetos().add(ind++, new HashSet<VariableFormulaSimple>()); // 10
        ep.getObjetos().add(ind++, new HashSet<VariableFormulaCompuesta>()); // 11
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        int ind = 0;
        TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(ind++, TipoTasa.class);
        ArrayList variables = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        ArrayList parametrosValor = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);      
        HashMap parametrosValorMap = new HashMap();
        ind++; // caso especial
        String tipoParametro = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
        ArrayList listaDatos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        String formulaAlicuota = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
        Date fechaLiquidacion = (Date) this.obtenerObjetoDelElementoPila(ind++, Date.class);
        Date fechaCobro = (Date) this.obtenerObjetoDelElementoPila(ind++, Date.class);
        List<VariableFormulaSimple> variablesSimples = (List<VariableFormulaSimple>) this.obtenerObjetoDelElementoPila(10, HashSet.class);
        List<VariableFormulaCompuesta> variablesCompuestas = (List<VariableFormulaCompuesta>) this.obtenerObjetoDelElementoPila(11, HashSet.class);

        listaDatos = new ArrayList();

        Object formula = this.getTaFormula().getText();
        Object tipoParametroSelected = this.getDdTiposParametro().getSelected();


        Object fechaLiq = this.getTfFechaLiquidacion().getText();
        Object fechaCob = this.getTfFechaCobro().getText();
        
        if (fechaLiq != null && fechaLiq != "") {
            fechaLiquidacion = Conversor.getFechaCortaDeString(fechaLiq.toString());
        } else {
            fechaLiquidacion = null;
        }
        if (fechaCob != null && fechaCob != "") {
            fechaCobro = Conversor.getFechaCortaDeString(fechaCob.toString());
        } else {
            fechaCobro = null;
        }

        if ((tipoParametroSelected != null) && (tipoParametroSelected.toString().length() > 0)) {
            tipoParametro = tipoParametroSelected.toString();
        } else {
            tipoParametro = null;
        }
        if (formula != null && formula != "") {
            formulaAlicuota = (formula.toString());
        } else {
            formulaAlicuota = null;
        }
        tipoTasa.setFormulaRegAlicuota(formulaAlicuota);
        
        this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        this.getObjectListDataProvider().commitChanges();
        variables = (ArrayList) this.getObjectListDataProvider().getList();
        if (variables.isEmpty()) {
            variables = null;
        }
        if (tipoTasa.getListaParametros() != null) {
            tipoTasa.getListaParametros().clear();
            tipoTasa.getListaParamatrosAlicuota().clear();
            if (variables != null) {
                for (Object obj : variables) {
                    if (obj instanceof TipoParametroAlicuota) {
                        tipoTasa.addTipoParametroAlicuota((TipoParametroAlicuota) obj);
                    } else {
                        tipoTasa.getListaParametros().add((TipoParametro) obj);
                    }
                }
            }
        }
        this.setListaDelCommunication(variables);
        
//        System.out.println("cant lista ldp4-: " + this.getObjectListDataProvider4().getList().size());
//        this.getObjectListDataProvider4().setList(this.getListaDelCommunication4());
        this.getObjectListDataProvider4().commitChanges();
//         System.out.println("cant lista ldp4--: " + this.getObjectListDataProvider4().getList().size());
        //    }
        
        parametrosValor = (ArrayList) this.getObjectListDataProvider4().getList();
        if (parametrosValor.isEmpty()) {
            parametrosValor = null;
        } else {
            for (int i = 0; i < parametrosValor.size(); i++) {
                ParametroValor pv = (ParametroValor) parametrosValor.get(i);
                String valor = pv.getValor().toString();
       
                if (!valor.startsWith("\"") && (!valor.endsWith("\""))) {
                    try {
                        parametrosValorMap.put(pv.getParametro(), Double.valueOf(valor));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    valor = valor.trim().substring(1, valor.trim().length() - 1);
                    parametrosValorMap.put(pv.getParametro(), valor);
                }

                System.out.println("pv.getParametro() : " + pv.getParametro());
                System.out.println("pv.getValor() : " + pv.getValor());
                System.out.println("cant map----" + parametrosValorMap.size());
            }
        }
        this.setListaDelCommunication4(parametrosValor);
        
        this.getObjectListDataProviderVariablesSimples().commitChanges();
        
        variablesSimples = this.getObjectListDataProviderVariablesSimples().getList();
        
        boolean hayRepetidos = false;
        boolean hayVacios = false;
        
        for(VariableFormulaSimple indice : variablesSimples){
            int i = 0;
            
            for(VariableFormulaSimple cadaVar : variablesSimples){
                if(indice.getNombre().equals(cadaVar.getNombre())){
                    i++;
                }
            }
            
            if(i > 1 && !hayRepetidos){
                warn("Existen variables repetidas");
                hayRepetidos = true;
            }
            if((indice.getNombre().equals("") || indice.getExpresion().equals("")) && !hayVacios){
                warn("Existen variables con campos vacíos");
                hayVacios = true;
            }
            
            if(hayVacios && hayRepetidos){
                break;
            }
        }
        
        if(hayRepetidos || hayVacios){
            this.noNavega = true;
        }
        
        tipoTasa.setListaVariablesSimpleAlicuota(variablesSimples);
        
        this.setListaDelCommunicationVariablesSimples(this.getObjectListDataProviderVariablesSimples().getList());
       ////////
        
        this.getObjectListDataProviderVariablesCompuestas().commitChanges();
        
        variablesCompuestas = this.getObjectListDataProviderVariablesCompuestas().getList();
        
        for(VariableFormulaCompuesta indice : variablesCompuestas){
            int i = 0;
            
            for(VariableFormulaCompuesta cadaVar : variablesCompuestas){
                if(indice.getNombre().equals(cadaVar.getNombre())){
                    i++;
                }
            }
            
            if(i > 1 && !hayRepetidos){
                warn("Existen variables repetidas");
                hayRepetidos = true;
            }
            if((indice.getNombre().equals("") || indice.getExpresion().equals("")) && !hayVacios){
                warn("Existen variables con campos vacíos");
                hayVacios = true;
            }
            
            if(hayVacios && hayRepetidos){
                break;
            }
        }
        
        if(hayRepetidos || hayVacios){
            this.noNavega = true;
        }
        
        tipoTasa.setListaVariablesFormulaCompuesta(variablesCompuestas);
        
        this.setListaDelCommunicationVariablesCompuestas(this.getObjectListDataProviderVariablesCompuestas().getList());
        
        tipoTasa.setIteraSobreAlicuotas(this.getCbIteraSobreAlicuotas().isChecked());
        
        // guardo datos de los componentes para el armado de condiciones

        this.guardarDatos(this.ddUnion1, listaDatos);
        this.guardarDatos(this.ddUnion2, listaDatos);

        this.guardarDatos(this.ddVariable1, listaDatos);
        this.guardarDatos(this.ddVariable2, listaDatos);
        this.guardarDatos(this.ddVariable3, listaDatos);

        this.guardarDatos(this.ddOperador1, listaDatos);
        this.guardarDatos(this.ddOperador2, listaDatos);
        this.guardarDatos(this.ddOperador3, listaDatos);

        this.guardarDatos(this.tfValor1, listaDatos);
        this.guardarDatos(this.tfValor2, listaDatos);
        this.guardarDatos(this.tfValor3, listaDatos);

        this.guardarDatos(this.tfValorFinal, listaDatos);
        this.guardarDatos(this.tfValorFinalSino, listaDatos);

        //////////////////////////
        System.out.println("GE: " + formulaAlicuota);
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, tipoTasa);
        this.getElementoPila().getObjetos().set(ind++, variables);
        this.getElementoPila().getObjetos().set(ind++, parametrosValor);
        this.getElementoPila().getObjetos().set(ind++, parametrosValorMap);
        this.getElementoPila().getObjetos().set(ind++, tipoParametro);
        this.getElementoPila().getObjetos().set(ind++, listaDatos);
        this.getElementoPila().getObjetos().set(ind++, formulaAlicuota);
        this.getElementoPila().getObjetos().set(ind++, fechaLiquidacion);
        this.getElementoPila().getObjetos().set(ind++, fechaCobro);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        int ind = 0;
        TipoTasa tipoTasa = null;
        ArrayList variables = null;
        ArrayList parametrosValor = null;
        HashMap parametrosValorMap = null;
        String tipoParametro = null;
        ArrayList listaDatos = null;
        Date fechaLiquidacion = null;
        Date fechaCobro = null;
        String seleccion = null;
        List<VariableFormulaSimple> variablesSimples = null;
        List<VariableFormulaCompuesta> variablesCompuestas = null;

        TipoParametro nuevoTipoParametro = null;
        TipoParametroAlicuota nuevoTipoParametroAlicuota = null;
        if (this.getRequestBean1().getTipoSeleccion() != null) {
            seleccion = this.getRequestBean1().getTipoSeleccion();
            System.out.println("TIPO SELECCION---- " + seleccion);
            this.getElementoPila().getObjetos().set(9, seleccion);
        }

        if (this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
            ArrayList seleccionados = this.getRequestBean1().getObjetosSeleccionMultiple();
//            int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
            int posicion = 1;
            System.out.println("ENTRO EN EL SELECCION MULTIPLE != NULL");
            variables = (ArrayList) this.obtenerObjetoDelElementoPila(posicion, ArrayList.class);
            parametrosValor = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

            if (posicion == 1) {

                for (Object selected : seleccionados) {
                    if (selected instanceof TipoParametroAlicuota) {
                        nuevoTipoParametroAlicuota = (TipoParametroAlicuota) selected;
                        System.out.println("--NUEVO TIPO PARAMETRO: " + nuevoTipoParametroAlicuota);
                        Object deLaTabla = null;
                        boolean esta = false;
                        for (int i = 0; i < variables.size(); i++) {
                            deLaTabla = variables.get(i);
                            if (deLaTabla instanceof TipoParametroAlicuota) {
                                if (((TipoParametroAlicuota) deLaTabla).getNombreVariable().equalsIgnoreCase(nuevoTipoParametroAlicuota.getNombreVariable())) {
                                    esta = true;
                                }
                            }
                            if (deLaTabla instanceof TipoParametro) {
                                if (((TipoParametro) deLaTabla).getNombreVariable().equalsIgnoreCase(nuevoTipoParametroAlicuota.getNombreVariable())) {
                                    esta = true;
                                }
                            }
                        }
                        if (!esta) {
                            variables.add(nuevoTipoParametroAlicuota);
                            if (!(nuevoTipoParametro instanceof TipoParametroConstante)
                                    && !(nuevoTipoParametro instanceof TipoParametroVencimiento)) {
                                parametrosValor.add(new ParametroValor(nuevoTipoParametroAlicuota.getNombreVariable()));
                                this.getElementoPila().getObjetos().set(2, parametrosValor);
                            }
                        }
                    } else {
                        nuevoTipoParametro = (TipoParametro) selected;
                        System.out.println("--NUEVO TIPO PARAMETRO: " + nuevoTipoParametro);
                        Object deLaTabla = null;
                        boolean esta = false;
                        for (int i = 0; i < variables.size(); i++) {
                            deLaTabla = variables.get(i);
                            if (deLaTabla instanceof TipoParametroAlicuota) {
                                if (((TipoParametroAlicuota) deLaTabla).getNombreVariable().equalsIgnoreCase(nuevoTipoParametro.getNombreVariable())) {
                                    esta = true;
                                }
                            }
                            if (deLaTabla instanceof TipoParametro) {
                                if (((TipoParametro) deLaTabla).getNombreVariable().equalsIgnoreCase(nuevoTipoParametro.getNombreVariable())) {
                                    esta = true;
                                }
                            }
                        }
                        if (!esta) {
                            variables.add(nuevoTipoParametro);
                            if (!(nuevoTipoParametro instanceof TipoParametroConstante)
                                    && !(nuevoTipoParametro instanceof TipoParametroVencimiento)) {
                                parametrosValor.add(new ParametroValor(nuevoTipoParametro.getNombreVariable()));
                                this.getElementoPila().getObjetos().set(2, parametrosValor);
                            }
                        }
                    }
                }
//                else {
//                    warn("El Par\341metro que intenta agregar ya se encuentra en la lista.");
//                }
            }
            //this.getElementoPila().getObjetos().set(2, parametrosValor);
            this.getElementoPila().getObjetos().set(posicion, variables);

            this.getRequestBean1().setObjetosSeleccionMultiple(null);
        }

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();

            if (posicion == 1) {
                nuevoTipoParametro = (TipoParametro) seleccionado;
                variables = (ArrayList) this.obtenerObjetoDelElementoPila(posicion, ArrayList.class);
                parametrosValor = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

                TipoParametro deLaTabla = null;
                boolean esta = false;
                for (int i = 0; i < variables.size(); i++) {
                    deLaTabla = (TipoParametro) variables.get(i);
                    if (deLaTabla.getNombreVariable().equalsIgnoreCase(nuevoTipoParametro.getNombreVariable())) {
                        esta = true;
                    }
                }
                if (!esta) {
                    variables.add(nuevoTipoParametro);
                    if (!(nuevoTipoParametro instanceof TipoParametroConstante)
                            && !(nuevoTipoParametro instanceof TipoParametroVencimiento)) {
                        parametrosValor.add(new ParametroValor(nuevoTipoParametro.getNombreVariable()));
                        this.getElementoPila().getObjetos().set(2, parametrosValor);
                    }
                } else {
                    warn("El Par\341metro que intenta agregar ya se encuentra en la lista.");
                }
                this.getElementoPila().getObjetos().set(posicion, variables);
                this.getElementoPila().getObjetos().set(2, parametrosValor);
            }
            this.getRequestBean1().setObjetoSeleccion(null);
        }

        if (this.getRequestBean1().getObjeto() != null) {
            tipoTasa = (TipoTasa) this.getRequestBean1().getObjeto();
            seleccion = (String) this.obtenerObjetoDelElementoPila(9, String.class);
            variablesSimples = tipoTasa.getListaVariablesSimpleAlicuota();
            variablesCompuestas = tipoTasa.getListaVariablesFormulaCompuesta();
            
            System.out.println("tipo tasaa----------" + tipoTasa);

            variables = new ArrayList();
            if (tipoTasa.getListaParametros() != null) {
                variables.addAll(tipoTasa.getListaParametros());
            }
            if (tipoTasa.getListaParamatrosAlicuota() != null) {
                variables.addAll(tipoTasa.getListaParamatrosAlicuota());
            }

            parametrosValor = new ArrayList();
            TipoParametro tp = null;
            TipoParametroAlicuota tpAlicuota = null;
            Object temp = null;
            System.out.println("///////cantidad variables: " + variables.size());
            for (int i = 0; i < variables.size(); i++) {
                temp = variables.get(i);
                if (!(temp instanceof TipoParametroConstante) && !(temp instanceof TipoParametroVencimiento)) {
                    if (temp instanceof TipoParametroAlicuota) {
                        tpAlicuota = (TipoParametroAlicuota) temp;
                        parametrosValor.add(new ParametroValor(tpAlicuota.getNombreVariable()));
                    } else {
                        tp = (TipoParametro) temp;
                        parametrosValor.add(new ParametroValor(tp.getNombreVariable()));
                    }
                }
            }
            parametrosValorMap = null;

            //limpio formula:
            System.out.println("seleccion: " + seleccion);
            if (seleccion.equals(("ADD"))) {
                tipoTasa.setFormulaRegAlicuota(null);
                tipoTasa.setIteraSobreAlicuotas(true);
            } else if (seleccion.equals(("EDIT"))) {
                tipoTasa.setFormulaRegAlicuota(tipoTasa.getFormulaRegAlicuota());
            }
            
            this.getCbIteraSobreAlicuotas().setSelected(tipoTasa.isIteraSobreAlicuotas());

            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, tipoTasa);
            this.getElementoPila().getObjetos().set(ind++, variables);
            this.getElementoPila().getObjetos().set(9, seleccion);
            this.getElementoPila().getObjetos().set(2, parametrosValor);
            this.getElementoPila().getObjetos().set(3, parametrosValorMap);
            this.getElementoPila().getObjetos().set(10, variablesSimples);
            this.getElementoPila().getObjetos().set(11, variablesCompuestas);
        }

        ind = 0;
        tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(ind++, TipoTasa.class);
        variables = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        parametrosValor = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        parametrosValorMap = (HashMap) this.obtenerObjetoDelElementoPila(ind++, HashMap.class);
        tipoParametro = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
        listaDatos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        ind++;
        fechaLiquidacion = (Date) this.obtenerObjetoDelElementoPila(ind++, Date.class);
        fechaCobro = (Date) this.obtenerObjetoDelElementoPila(ind++, Date.class);
        variablesSimples = (List<VariableFormulaSimple>) this.obtenerObjetoDelElementoPila(10, HashSet.class);
        variablesCompuestas = (List<VariableFormulaCompuesta>) this.obtenerObjetoDelElementoPila(11, HashSet.class);

        ///recupero datos guardados del armado de condiciones:

        if (listaDatos.size() > 0) {
            if (listaDatos.get(0) != null) {
                this.getDdUnion1().setSelected(Util.getEnumNameFromString(listaDatos.get(0).toString()));
            }
            if (listaDatos.get(1) != null) {
                this.getDdUnion2().setSelected(Util.getEnumNameFromString(listaDatos.get(1).toString()));
            }

            if (listaDatos.get(2) != null) {
                this.getDdVariable1().setSelected(Util.getEnumNameFromString(listaDatos.get(2).toString()));
            }
            if (listaDatos.get(3) != null) {
                this.getDdVariable2().setSelected(Util.getEnumNameFromString(listaDatos.get(3).toString()));
            }
            if (listaDatos.get(4) != null) {
                this.getDdVariable3().setSelected(Util.getEnumNameFromString(listaDatos.get(4).toString()));
            }

            if (listaDatos.get(5) != null) {
                this.getDdOperador1().setSelected(Util.getEnumNameFromString(listaDatos.get(5).toString()));
            }
            if (listaDatos.get(6) != null) {
                this.getDdOperador2().setSelected(Util.getEnumNameFromString(listaDatos.get(6).toString()));
            }
            if (listaDatos.get(7) != null) {
                this.getDdOperador3().setSelected(Util.getEnumNameFromString(listaDatos.get(7).toString()));
            }

            if (listaDatos.get(8) != null) {
                this.getTfValor1().setText(listaDatos.get(8).toString());
            }
            if (listaDatos.get(9) != null) {
                this.getTfValor2().setText(listaDatos.get(9).toString());
            }
            if (listaDatos.get(10) != null) {
                this.getTfValor3().setText(listaDatos.get(10).toString());
            }

            if (listaDatos.get(11) != null) {
                this.getTfValorFinal().setText(listaDatos.get(11).toString());
            }
            if (listaDatos.get(12) != null) {
                this.getTfValorFinalSino().setText(listaDatos.get(12).toString());
            }

        }
        //////////////////////////

        this.getObjectListDataProvider().setList(variables);
        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider().getObjects(), "cap");
        lbVariablesDefaultOptions.setOptions(op);

        int k = 0;
        Object[] opcionesAMostrar;
        if (tipoTasa.getPlan().getTipoObligacion().getNombre().equals("OYSP")) {
            opcionesAMostrar = new Object[8];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.OSP;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.SERVICIOS_OSP;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        } else if (tipoTasa.getPlan().getTipoObligacion().getNombre().equals("PLAN_FINANCIACION_OBRA")) {
            opcionesAMostrar = new Object[8];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.OBRA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        } else if (tipoTasa.getPlan().getTipoObligacion().getNombre().equals("SHPS")) {
            opcionesAMostrar = new Object[6];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.SHPS;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        } else if (tipoTasa.getPlan().getTipoObligacion().getNombre().equals("TASAS_MENORES_COMERCIO")) {
            opcionesAMostrar = new Object[6];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.SHPS;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        } else if (tipoTasa.getPlan().getTipoObligacion().getNombre().equals("TGI")) {
            opcionesAMostrar = new Object[7];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        } else if (tipoTasa.getPlan().getTipoObligacion().getNombre().equals("TRANSITO")) {
            opcionesAMostrar = new Object[5];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        } else if (tipoTasa.getPlan().getTipoObligacion().getNombre().equals("CEMENTERIO")) {
            opcionesAMostrar = new Object[7];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CEMENTERIO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.PARCELA_CEMENTERIO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.TIPO_SEPULTURA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        } else if (tipoTasa.getPlan().getTipoObligacion().getNombre().equals("AUTOMOTOR")) {
            opcionesAMostrar = new Object[6];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.AUTOMOTOR;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VEHICULO;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        } else {
            opcionesAMostrar = new Object[6];
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
            opcionesAMostrar[k++] = TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS;
            opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
        }

        op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(opcionesAMostrar, "cap");
        ddTiposParametroDefaultOptions.setOptions(op);

        if (TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS.toString().equals(tipoParametro)) {
            this.getDdTiposParametro().setSelected(Util.getEnumNameFromString(String.valueOf(TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS)));
            this.getDdTiposParametroDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS)));
        } else {
            this.getDdTiposParametro().setSelected(Util.getEnumNameFromString(tipoParametro));
            this.getDdTiposParametroDefaultOptions().setSelectedValue(Util.getEnumNameFromString(tipoParametro));
        }
        this.getTaFormula().setText(tipoTasa.getFormulaRegAlicuota());

        if (fechaLiquidacion != null) {
            this.getTfFechaLiquidacion().setText(Conversor.getStringDeFechaCorta(fechaLiquidacion));
        }
        if (fechaCobro != null) {
            this.getTfFechaCobro().setText(Conversor.getStringDeFechaCorta(fechaCobro));
        }

        this.setListaDelCommunication(variables);
        this.getObjectListDataProvider().setList(variables);

//        parametrosValor = new ArrayList();
//        TipoParametro tp = null;
//        TipoParametroAlicuota tpAlicuota = null;
//        Object temp = null;
//        System.out.println("///////cantidad variables: " + variables.size());
//        for (int i = 0; i < variables.size(); i++) {
//            temp = variables.get(i);
//            if (!(temp instanceof TipoParametroConstante) && !(temp instanceof TipoParametroVencimiento)) {
//                if (temp instanceof TipoParametroAlicuota) {
//                    tpAlicuota = (TipoParametroAlicuota) temp;
//                    parametrosValor.add(new ParametroValor(tpAlicuota.getNombreVariable()));
//                } else {
//                    tp = (TipoParametro) temp;
//                    parametrosValor.add(new ParametroValor(tp.getNombreVariable()));
//                }
//            }
//        }
        this.setListaDelCommunication4(parametrosValor);
        this.getObjectListDataProvider4().setList(parametrosValor);
        this.getObjectListDataProvider4().commitChanges();
        
        this.setListaDelCommunicationVariablesSimples(variablesSimples);
        this.getObjectListDataProviderVariablesSimples().setList(new ArrayList(variablesSimples));
        
        this.setListaDelCommunicationVariablesCompuestas(variablesCompuestas);
        this.getObjectListDataProviderVariablesCompuestas().setList(new ArrayList(variablesCompuestas));
        
//        if (tipoTasa.getEstado().equals(TipoTasa.Estado.ACTIVA)) {
//            this.getBtnGuardar().setDisabled(true);
//        }
    }

    private void guardarDatos(UIComponent componente, ArrayList lista) {
        if (((UIInput) componente).getValue() != null && ((UIInput) componente).getValue().toString().length() > 0) {
            lista.add(((UIInput) componente).getValue().toString());
        } else {
            lista.add(null);
        }
    }

    // Variables
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpTipoParametro();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaTiposParametroAlicuota();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaTiposParametroAlicuota(lista);
    }

    // ParametrosValor
    private ObjectListDataProvider getObjectListDataProvider4() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente     
        return this.getLdpParametroValor();
    }

    private ArrayList getListaDelCommunication4() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaParametrosValoresFormulaAlicuota();
    }

    private void setListaDelCommunication4(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaParametrosValoresFormulaAlicuota(lista);
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
            if (objeto == null && !tipoClase.getName().equals("java.util.Date") && !tipoClase.getName().equals("com.trascender.habilitaciones.recurso.persistent.TipoParametro")) {
                objeto = tipoClase.newInstance();
            }
        } catch (Exception ex) {
        }
        return objeto;
    }

    public void limpiarObjeto(int posicion, Class tipoClase, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, tipoClase.newInstance());
            if (campo != null) {
                campo.setText("");
            }
        } catch (Exception ex) {
        }
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

    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar RaddioButton">
//    public String getCurrentRow() {
//        return tableRowGroup1.getRowKey().getRowId();
//    }
    public void setCurrentRow(int row) {
    }
    private Object lastSelected = null;

//    public Object getRBSelected() {
//        String sv = (String) radioButton1.getSelectedValue();
//        return sv.equals(lastSelected) ? sv : null;
//    }
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

//    public void guardarOrdenamiento() {
//        this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
//    }
//
//    public void setearOrdenamiento() {
//        this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
//    }
//
//    public void setPropiedadesTabla() {
//        String width = null;
//        int cantidadFilas = this.getApplicationBean1().getCantidadFilasTablasAdmin().intValue();
//        boolean paginar = false;
////
////        this.getTableRowGroup1().setRows(cantidadFilas);
////        this.getTable1().setWidth(width);
////        this.getTable1().setPaginateButton(paginar);
//
//    }
//
//    public Long getPosicionEnTabla(RowKey rk) {
//        long inicioPagina = 0;
//        long posicionGlobal = 0;
//        long posicionEnPagina = 0;
//        long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
//        long cantRegistros = this.getTableRowGroup1().getRowCount();
//        boolean encontrado = false;
//
//        if (rk != null) {
//            while (!encontrado && inicioPagina < cantRegistros) {
//                this.getTableRowGroup1().setFirst((int) inicioPagina);
//                posicionEnPagina = 0;
//                while (!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
//                    encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
//                    if (!encontrado) {
//                        posicionEnPagina++;
//                        posicionGlobal++;
//                    }
//                }
//                if (!encontrado) {
//                    inicioPagina += cantRegistrosPorPagina;
//                }
//            }
//        }
//        return new Long(posicionGlobal);
//    }
//    public RowKey getRowKeyAsociado(Long posicionEnTabla) {
//        RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
//        return rk;
//    }
//    public void seleccionarFila(Long posicionGlobal) {
//        long cantFilas = this.getTableRowGroup1().getRowCount();
//        if (cantFilas > 0) {
//            // si hay al menos una fila
//            if (posicionGlobal.longValue() >= cantFilas) {
//                // si elimine la ultima fila, me posiciono en la anterior
//                posicionGlobal = new Long(cantFilas - 1);
//            }
//            ;
//
//            int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
//            this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
//            lastSelected = new Long(index).toString();
//        }
//    }
//
//    public Long getInicioPagina(Long posicionGlobal) {
//        long inicioPagina = 0;
//        long posicionEnPagina = 0;
//        long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
//
//        inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
//        return new Long(inicioPagina);
//    }
//   
    public RowKey getSeleccionado4() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup4");
            rk = this.getObjectListDataProvider4().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    
    public RowKey getSeleccionado8() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup7");
            rk = this.getObjectListDataProviderVariablesSimples().getRowKey(aRowId);
        } catch (Exception ex) {
        }

        return rk;
    }
    
    public RowKey getSeleccionado9() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup8");
            rk = this.getObjectListDataProviderVariablesCompuestas().getRowKey(aRowId);
        } catch (Exception ex) {
        }

        return rk;
    }

    // </editor-fold>
    public String btnNuevoParametro_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;

        if (ultimo) {

            Object tipoParametro = this.getDdTiposParametro().getSelected();

            if (tipoParametro != null && tipoParametro != "") {
                if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.PARCELARIO.toString()))) {
                    retorno = "AdminTipoParametroParcelario";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA.toString()))) {
                    this.getRequestBean1().setTipoSeleccion("MULTIPLE");
                    this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
                    retorno = "AdminTipoParametroGrupoZona";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.CONSTANTE.toString()))) {
                    this.getRequestBean1().setTipoSeleccion("MULTIPLE");
                    this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
                    retorno = "AdminTipoParametroConstante";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.OBRA.toString()))) {
                    retorno = "AdminTipoParametroObra";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.PERSONA.toString()))) {
                    retorno = "AdminTipoParametroPersona";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.SHPS.toString()))) {
                    String tipoParametroSHPS = "SHPS";
                    this.getRequestBean1().setObjetoSeleccion(tipoParametroSHPS);
                    retorno = "AdminTipoParametroSHPS";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.OSP.toString()))) {
                    retorno = "AdminTipoParametroOSP";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO.toString()))) {
                    retorno = "AdminTipoParametroVencimiento";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametroAlicuota.TIPOS_PARAMETRO.ALICUOTA_SHPS.toString()))) {
                    //TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
                    String tipoParametroSHPS = "ALICUOTA_SHPS";
                    this.getRequestBean1().setObjetoSeleccion(tipoParametroSHPS);
                    retorno = "AdminTipoParametroSHPS";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametroAlicuota.TIPOS_PARAMETRO.SERVICIOS_OSP.toString()))) {
                    String tipoParametroServiciosOSP = "SERVICIOS_OSP";
                    this.getRequestBean1().setObjetoSeleccion(tipoParametroServiciosOSP);
                    retorno = "AdminTipoParametroServiciosOSP";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.CEMENTERIO.toString()))) {
                    String tipoParametroCementerio = "CEMENTERIO";
                    this.getRequestBean1().setObjetoSeleccion(tipoParametroCementerio);
                    retorno = "AdminTipoParametroCementerio";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametroAlicuota.TIPOS_PARAMETRO.PARCELA_CEMENTERIO.toString()))) {
                    String tipoParametroCementerio = "PARCELA_CEMENTERIO";
                    this.getRequestBean1().setObjetoSeleccion(tipoParametroCementerio);
                    retorno = "AdminTipoParametroCementerio";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametroAlicuota.TIPOS_PARAMETRO.TIPO_SEPULTURA.toString()))) {
                    String tipoParametroCementerio = "TIPO_SEPULTURA";
                    this.getRequestBean1().setObjetoSeleccion(tipoParametroCementerio);
                    retorno = "AdminTipoParametroCementerio";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.AUTOMOTOR.toString()))) {
                    String tipoParametroAutomotor = "AUTOMOTOR";
                    this.getRequestBean1().setObjetoSeleccion(tipoParametroAutomotor);
                    retorno = "AdminTipoParametroAutomotor";
                } else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.VEHICULO.toString()))) {
                	String tipoParametroVehiculo = "AUTOMOTOR";
					this.getRequestBean1().setObjetoSeleccion(tipoParametroVehiculo);
					retorno = "AdminTipoParametroVehiculo";
				}
				else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.DEUDA.toString()))) {
					String tipoParametroDeuda = "DEUDA";
					this.getRequestBean1().setObjetoSeleccion(tipoParametroDeuda);
					retorno = "AdminTipoParametroDeuda";
				}

                this.getRequestBean1().setRefrescarTabla(true);
            } else {
                warn("Seleccione un Tipo para agregar un nuevo Par\341metro.");
            }

            this.guardarEstadoObjetosUsados();

            if (this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
                for (Object object : this.getRequestBean1().getObjetosSeleccionMultiple()) {
                    this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
                }
            }
            
            if(noNavega){
                retorno = null;
            }

            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.getRequestBean1().setAccion(Constantes.ACCION_SELECCIONAR);

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnQuitarParametro_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {

                TipoParametro tipoParametro = null;
                TipoParametroAlicuota tipoParametroAlicuota = null;
                Object tipoParametroSelected = this.getLbVariables().getSelected();

                if ((tipoParametroSelected != null) && (tipoParametroSelected.toString().length() > 0)) {
                    
                    this.getObjectListDataProvider().commitChanges();
                    this.getObjectListDataProvider4().commitChanges();
                    
                    ArrayList tiposParametro = this.getListaDelCommunication();
                    for (int i = 0; i < this.getListaDelCommunication().size(); i++) {
                        if (this.getListaDelCommunication().get(i) instanceof TipoParametroAlicuota) {
                            tipoParametroAlicuota = (TipoParametroAlicuota) this.getListaDelCommunication().get(i);
                            if (tipoParametroAlicuota.getNombreVariable().equalsIgnoreCase(tipoParametroSelected.toString())) {
                                this.getListaDelCommunication().remove(i);
                                this.getListaDelCommunication4().remove(i);
                                break;
                            }
                        } else {
                            tipoParametro = (TipoParametro) this.getListaDelCommunication().get(i);
                            if (tipoParametro.getNombreVariable().equalsIgnoreCase(tipoParametroSelected.toString())) {
                                this.getListaDelCommunication().remove(i);
                                if(!(tipoParametro instanceof TipoParametroConstante)){
                                    this.getListaDelCommunication4().remove(i);
                                }
                                break;
                            }
                        }
                    }                    
                }
                //this.getObjectListDataProvider4().setList(this.getListaDelCommunication4());

            } catch (Exception ex) {
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        //  boolean ultimo = this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());

        if (ultimo) {

            try {
                this.guardarEstadoObjetosUsados();

                TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
                String formulaAlicuota = (String) this.obtenerObjetoDelElementoPila(6, String.class);
                int cantidadComp = 1;

                // CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[cantidadComp];
                String[] nomNoVacios = new String[cantidadComp];

                int pos = 0;

                noVacios[pos] = this.getTaFormula();
                nomNoVacios[pos++] = "F\363rmula";

                v.noSonVacios(noVacios, nomNoVacios);

                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }


                System.out.println("guardar: tt" + tipoTasa);
                System.out.println("cant parametros: " + tipoTasa.getListaParametros().size());

                System.out.println("guardar fa: " + formulaAlicuota);

                this.getRequestBean1().setRespuestaABM(tipoTasa);
                this.getRequestBean1().setObjeto(formulaAlicuota);

                info("La F\363rmula de C\341lculo se agreg\363 exitosamente.");

                if (this.getListaDelCommunication() != null) {
                    this.getListaDelCommunication().clear();
                }
                
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

    private void cargarValoresPorDefecto() {
        return;
    }

    public String btnProbarFormula_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {

                this.guardarEstadoObjetosUsados();

                TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
                HashMap parametrosValorMap = (HashMap) this.obtenerObjetoDelElementoPila(3, HashMap.class);

                Validador v = new Validador();
                UIComponent[] novacio = new UIComponent[3];
                String[] nomNovacio = new String[3];
                UIComponent[] fechas = new UIComponent[2];
                String[] nomFechas = new String[2];

                int pos = 0;
                novacio[pos] = this.getTaFormula();
                nomNovacio[pos++] = "F\363rmula";
                novacio[pos] = this.getTfFechaLiquidacion();
                nomNovacio[pos++] = "Fecha de Liquidaci\363n";
                novacio[pos] = this.getTfFechaCobro();
                nomNovacio[pos++] = "Fecha de Cobro";

                pos = 0;
                fechas[pos] = this.getTfFechaLiquidacion();
                nomFechas[pos++] = "Fecha de Liquidaci\363n";
                fechas[pos] = this.getTfFechaCobro();
                nomFechas[pos++] = "Fecha de Cobro";

                v.noSonVacios(novacio, nomNovacio);
                v.formatoFechaValido(fechas, nomFechas);


                if (v.getErrores().size() > 0) {
                    error("Para probar la F\363rmula de C\341lculo:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                Date fechaLiquidacion = Conversor.getFechaCortaDeString(this.getTfFechaLiquidacion().getText().toString());
                Date fechaCobro = Conversor.getFechaCortaDeString(this.getTfFechaCobro().getText().toString());

                this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());

                ///////////////
//                System.out.println("cantidad--- " + this.getListaDelCommunication4().size());
//                this.getObjectListDataProvider4().setList(this.getListaDelCommunication4());
//                if (this.getObjectListDataProvider4().getList() != null && this.getObjectListDataProvider4().getList().size() > 0) {
//                    this.getObjectListDataProvider4().commitChanges();
//                    System.out.println("lalala");
//                }
//                ArrayList parametrosValor = new ArrayList();
//                parametrosValor.addAll(this.getObjectListDataProvider4().getList());
//                HashMap parametrosValorMap = new HashMap();
//                if (parametrosValor.isEmpty()) {
//                    parametrosValor = null;
//                } else {
//                    for (int i = 0; i < parametrosValor.size(); i++) {
//                        ParametroValor pv = (ParametroValor) parametrosValor.get(i);
//                        parametrosValorMap.put(pv.getParametro(), pv.getValor());
//                        System.out.println("pepepe");
//                    }
//                }
//                this.setListaDelCommunication4(parametrosValor);

                ///////////////
                Double tasa = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().calcularTasaAlicuota(tipoTasa, parametrosValorMap);

                // Muestro Resultado: tasa
                String resultado = "<table cellspacing='0' cellpadding='2'><tr>   <td align='right' style=''>Tasa:</td>   <td align='right'>$</td>   <td>" + tasa + "</td>   <td></td></tr>";
                // String resultado = "<table cellspacing='0' cellpadding='2'><tr>   <td align='right' style=''>Tasa:</td>   <td align='right'>$</td>   <td>" + tasa + "</td>   <td></td></tr>";

                String nombreModif = null;
                Double valorModif = null;

//                // Muestro Resultado y Calculo SubTotal: modificadores sobre la tasa
//                double subtotal = tasa.doubleValue();
//                for (int i = 0; i < modificadoresSobreTasa.size(); i++) {
//                    nombreModif = (String) modificadoresSobreTasa.keySet().toArray()[i];
//                    valorModif = (Double) modificadoresSobreTasa.get(modificadoresSobreTasa.keySet().toArray()[i]);
//
//                    resultado += "<tr>  <td align='right'>" + nombreModif + "</td>   <td align='right'>$</td>   <td>" + valorModif + "</td>   <td></td></tr>";
//                    subtotal += valorModif.doubleValue();
//                }
//
//                // Muestro Resultado: subtotal
//                resultado += "<tr>   <td align='right' style='border-top: 1px solid #666666;'>SubTotal:</td>   <td align='right' style='border-top: 1px solid #666666'>$</td>   <td style='border-top: 1px solid #666666'>" + subtotal + "</td>   <td style='border-top: 1px solid #666666'></td></tr>";
//                resultado += "<tr><td colspan='4'/></tr>";
//
//                // Muestro Resultado y Calculo Total: modificadores sobre el subtotal
//                double total = subtotal;
//                for (int i = 0; i < modificadoresSobreSubtotal.size(); i++) {
//                    nombreModif = (String) modificadoresSobreSubtotal.keySet().toArray()[i];
//                    valorModif = (Double) modificadoresSobreSubtotal.get(modificadoresSobreSubtotal.keySet().toArray()[i]);
//
//                    resultado += "<tr>  <td align='right'>" + nombreModif + "</td>   <td align='right'>$</td>   <td>" + valorModif + "</td>   <td></td></tr>";
//                    total += valorModif.doubleValue();
//                }

                // Muestro Resultado: total
//                resultado += "<tr>   <td align='right' style='border-top: 1px solid #666666;'>Total:</td>   <td align='right' style='border-top: 1px solid #666666'>$</td>   <td style='border-top: 1px solid #666666'>" + total + "</td>   <td style='border-top: 1px solid #666666'></td></tr>";

//
//                //*---------------------------------------------------------------------------------------------------------------
//                //Interes
//                // Muestro Resultado y Calculo Total: modificadores sobre el subtotal
//
//                //float total = subtotal;
//
//                if (interesRecargo != null) {
//                    for (int i = 0; i < interesRecargo.size(); i++) {
//                        nombreModif = (String) interesRecargo.keySet().toArray()[i];
//                        valorModif = (Double) interesRecargo.get(nombreModif);
//
//                        resultado += "<tr>  <td align='right'>" + nombreModif + "</td>   <td align='right'>$</td>   <td>" + valorModif + "</td>   <td></td></tr>";
//                        total += valorModif.doubleValue();
//                    }
//
//                    resultado += "<tr>   <td align='right' style='border-top: 1px solid #666666;'>Total con Intereses:</td>   <td align='right' style='border-top: 1px solid #666666'>$</td>   <td style='border-top: 1px solid #666666'>" + total + "</td>   <td style='border-top: 1px solid #666666'></td></tr>";
//                }
//                // Vencimientos
//                resultado += "<tr><td colspan='4'/></tr><tr>   <td align='left' style='border-top: 1px solid #666666;'>Vencimientos</td>   <td align='right' style='border-top: 1px solid #666666'></td>   <td style='border-top: 1px solid #666666'></td>   <td style='border-top: 1px solid #666666'></td></tr>";
//
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//                for (int i = 0; i < vencimientos.size(); i++) {
//                    nombreModif = sdf.format((Date) vencimientos.keySet().toArray()[i]);
//                    valorModif = (Double) vencimientos.get(vencimientos.keySet().toArray()[i]);
//
//                    resultado += "<tr>  <td align='right'>" + nombreModif + "</td>   <td align='right'>$</td>   <td>" + valorModif + "</td>   <td></td></tr>";
//                }
//
                resultado += "</table>";

                this.getGdpResultado().setRendered(true);
                this.getStResultado().setText(resultado);

            } catch (Exception ex) {
                error(NOMBRE_PAGINA + " - Probar F\363rmula: " + ex.getMessage());
                ex.printStackTrace();
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    /**
     * Inner Class para listar en la tabla.
     */
    public class ParametroValor {

        private String parametro;
        private Object valor;

        /**
         * Constructor por defecto.
         */
        public ParametroValor() {
        }

        /**
         * Constructor con valor inicializado en 0.0F.
         */
        public ParametroValor(String parametro) {
            this.setParametro(parametro);
            this.setValor(new Double(0.0F));
        }

        /**
         * Constructor con 2 parametros
         */
        public ParametroValor(String parametro, Double valor) {
            this.setParametro(parametro);
            this.setValor(valor);
        }

        public String getParametro() {
            return parametro;
        }

        public void setParametro(String parametro) {
            this.parametro = parametro;
        }

        public Object getValor() {
            return valor;
        }

        public void setValor(Object valor) {
            this.valor = valor;
        }

        @Override
		public String toString() {
            return this.getParametro() + " : " + this.getValor();
        }
    }
    
    public void btnAgregarVariable_action(){
        this.getObjectListDataProviderVariablesSimples().commitChanges();
        this.getObjectListDataProviderVariablesCompuestas().commitChanges();
        TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
        if(this.getTaFormula().getText() != null) {
			tipoTasa.setFormulaRegAlicuota(this.getTaFormula().getText().toString());
		}
        VariableFormulaSimple v = new VariableFormulaSimple();   
        v.setNombre("");
        v.setExpresion("");
        
        this.getListaDelCommunicationVariablesSimples().add(v);
        this.getObjectListDataProviderVariablesSimples().getList().add(v);

        //this.guardarEstadoObjetosUsados();
        this.getElementoPila().getObjetos().set(0, tipoTasa);
        this.mostrarEstadoObjetosUsados();
    }
    
    public String btnQuitarVariable_action(){
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;
            this.getObjectListDataProviderVariablesSimples().commitChanges();
            this.getObjectListDataProviderVariablesCompuestas().commitChanges();
            try {
                rk = this.getSeleccionado8();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProviderVariablesSimples().getObjects()[index];
                    this.getListaDelCommunicationVariablesSimples().remove(obj);
                    this.getObjectListDataProviderVariablesSimples().getList().remove(obj);
                    
                }
            } catch (Exception ex) {
            }
            this.guardarEstadoObjetosUsados();
            this.mostrarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public void btnAgregarVariableCompuesta_action(){
        this.getObjectListDataProviderVariablesCompuestas().commitChanges();
        this.getObjectListDataProviderVariablesSimples().commitChanges();
        TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
		if(this.getTaFormula().getText() != null) {
			tipoTasa.setFormulaRegAlicuota(this.getTaFormula().getText().toString());
		}
        VariableFormulaCompuesta v = new VariableFormulaCompuesta();   
        v.setTipo(null);
        v.setNombre("");
        v.setExpresion("");
        
        this.getListaDelCommunicationVariablesCompuestas().add(v);
        this.getObjectListDataProviderVariablesCompuestas().getList().add(v);

        //this.guardarEstadoObjetosUsados();
        this.mostrarEstadoObjetosUsados();
    }
    
    public String btnQuitarVariableCompuesta_action(){
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;
            this.getObjectListDataProviderVariablesCompuestas().commitChanges();
            this.getObjectListDataProviderVariablesSimples().commitChanges();
            try {
                rk = this.getSeleccionado9();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProviderVariablesCompuestas().getObjects()[index];
                    this.getListaDelCommunicationVariablesCompuestas().remove(obj);
                    this.getObjectListDataProviderVariablesCompuestas().getList().remove(obj);
                    
                }
            } catch (Exception ex) {
            }
            this.guardarEstadoObjetosUsados();
            this.mostrarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public List<VariableFormulaSimple> getListaDelCommunicationVariablesSimples() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaVariablesSimpleAlicuota();
    }

    private void setListaDelCommunicationVariablesSimples(List<VariableFormulaSimple> lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaVariablesSimpleAlicuota(lista);
    }
    
    public List<VariableFormulaCompuesta> getListaDelCommunicationVariablesCompuestas() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaVariablesCompuestaAlicuota();
    }

    private void setListaDelCommunicationVariablesCompuestas(List<VariableFormulaCompuesta> lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaVariablesCompuestaAlicuota(lista);
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
        return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
    }
}
