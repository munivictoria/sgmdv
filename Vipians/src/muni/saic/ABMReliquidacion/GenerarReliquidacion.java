/*
 * GenerarReliquidacion.java
 *
 * Created on 1 de noviembre de 2006, 14:27
 * Copyright Trascender SRL
 *
 * mines
 */

package muni.saic.ABMReliquidacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGrid;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.sun.rave.web.ui.component.ImageComponent;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
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
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.ParametroReliquidacion;
import com.trascender.saic.recurso.persistent.ParametroValuado;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.Reliquidacion;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class GenerarReliquidacion extends AbstractPageBean {

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
	private final String NOMBRE_PAGINA = "Generar Reliquidacion";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "GenerarReliquidacion";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {

		if(this.getListaDelCommunication5() != null) {
			this.getObjectListDataProvider5().setList(this.getListaDelCommunication5());
		}
		
		if(this.getListaDelCommunication4() != null) {
			this.getObjectListDataProvider4().setList(this.getListaDelCommunication4());
		}

		Option[] opTipoValor = null;
		opTipoValor = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(ParametroReliquidacion.TipoValor.values(), "cap");
		ddTipoValorOptions.setOptions(opTipoValor);
		ddTipoValorAlicuotaOptions.setOptions(opTipoValor);

		Option[] opParametros = null;
		opParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider().getObjects(), "cap");
		lbVariablesDefaultOptions.setOptions(opParametros);

//		Option[] opNuevosParametros = null;
//		opNuevosParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider2().getObjects(), "cap");
//		lbVariablesACargarDefaultOptions.setOptions(opNuevosParametros);
 
		Option[] opParametrosAlicuota = null;
		opParametrosAlicuota = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider3().getObjects(), "");
		lbVariablesAlicuotaDefaultOptions.setOptions(opParametrosAlicuota);

//		Option[] opNuevosParametrosAlicuota = null;
//		opNuevosParametrosAlicuota = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider4().getObjects(), "");
//		lbVariablesACargarAlicuotaDefaultOptions.setOptions(opNuevosParametrosAlicuota);
		
		cbAplicarInteres.setDisabled(!getCommunicationSAICBean().getEsAdministradorReliquidaciones());
	}

	private Table table2 = new Table();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private TableColumn tableColumnSeleccion = new TableColumn();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private StaticText staticText2 = new StaticText();
	private DropDown ddTipoValor = new DropDown();
	private SingleSelectOptionsList ddTipoValorOptions = new SingleSelectOptionsList();
	private TextField textField1 = new TextField();
	private ObjectListDataProvider ldpParametros = new ObjectListDataProvider();
	private RadioButton radioButton1 = new RadioButton();
	
	private Table tableAlicuota = new Table();
	private TableRowGroup tableRowGroupAlicuota = new TableRowGroup();
	private TableColumn tableColumnSeleccionAlicuota = new TableColumn();
	private TableColumn tableColumnAlicuota1 = new TableColumn();
	private TableColumn tableColumnAlicuota2 = new TableColumn();
	private TableColumn tableColumnAlicuota3 = new TableColumn();
	private StaticText stParametroAlicuota = new StaticText();
	private DropDown ddTipoValorAlicuota = new DropDown();
	private SingleSelectOptionsList ddTipoValorAlicuotaOptions = new SingleSelectOptionsList();
	private TextField tfValorAlicuota = new TextField();
	private ObjectListDataProvider ldpParametrosAlicuotas = new ObjectListDataProvider();
	private RadioButton radioButtonAlicuota = new RadioButton();
	
	private Object lastSelectedAlicuota = null;

	public Object getRBSelectedAlicuota() {
		String sv = (String) radioButtonAlicuota.getSelectedValue();
		return sv.equals(lastSelectedAlicuota) ? sv : null;
	}

	public void setRBSelectedAlicuota(Object selected) {
		if(selected != null) {
			lastSelectedAlicuota = selected;
		}
	}

	public String getCurrentRowAlicuota() {
		return tableRowGroupAlicuota.getRowKey().getRowId();
	}

	public void setCurrentRowAlicuota(int row) {
	}
	
	public Table getTableAlicuota() {
		return tableAlicuota;
	}

	public void setTableAlicuota(Table tableAlicuota) {
		this.tableAlicuota = tableAlicuota;
	}

	public TableRowGroup getTableRowGroupAlicuota() {
		return tableRowGroupAlicuota;
	}

	public void setTableRowGroupAlicuota(TableRowGroup tableRowGroupAlicuota) {
		this.tableRowGroupAlicuota = tableRowGroupAlicuota;
	}

	public TableColumn getTableColumnSeleccionAlicuota() {
		return tableColumnSeleccionAlicuota;
	}

	public void setTableColumnSeleccionAlicuota(
			TableColumn tableColumnSeleccionAlicuota) {
		this.tableColumnSeleccionAlicuota = tableColumnSeleccionAlicuota;
	}

	public TableColumn getTableColumnAlicuota1() {
		return tableColumnAlicuota1;
	}

	public void setTableColumnAlicuota1(TableColumn tableColumnAlicuota1) {
		this.tableColumnAlicuota1 = tableColumnAlicuota1;
	}

	public TableColumn getTableColumnAlicuota2() {
		return tableColumnAlicuota2;
	}

	public void setTableColumnAlicuota2(TableColumn tableColumnAlicuota2) {
		this.tableColumnAlicuota2 = tableColumnAlicuota2;
	}

	public TableColumn getTableColumnAlicuota3() {
		return tableColumnAlicuota3;
	}

	public void setTableColumnAlicuota3(TableColumn tableColumnAlicuota3) {
		this.tableColumnAlicuota3 = tableColumnAlicuota3;
	}

	public StaticText getStParametroAlicuota() {
		return stParametroAlicuota;
	}

	public void setStParametroAlicuota(StaticText stParametroAlicuota) {
		this.stParametroAlicuota = stParametroAlicuota;
	}

	public DropDown getDdTipoValorAlicuota() {
		return ddTipoValorAlicuota;
	}

	public void setDdTipoValorAlicuota(DropDown ddTipoValorAlicuota) {
		this.ddTipoValorAlicuota = ddTipoValorAlicuota;
	}

	public SingleSelectOptionsList getDdTipoValorAlicuotaOptions() {
		return ddTipoValorAlicuotaOptions;
	}

	public void setDdTipoValorAlicuotaOptions(
			SingleSelectOptionsList ddTipoValorAlicuotaOptions) {
		this.ddTipoValorAlicuotaOptions = ddTipoValorAlicuotaOptions;
	}

	public TextField getTfValorAlicuota() {
		return tfValorAlicuota;
	}

	public void setTfValorAlicuota(TextField tfValorAlicuota) {
		this.tfValorAlicuota = tfValorAlicuota;
	}

	public ObjectListDataProvider getLdpParametrosAlicuotas() {
		return ldpParametrosAlicuotas;
	}

	public void setLdpParametrosAlicuotas(
			ObjectListDataProvider ldpParametrosAlicuotas) {
		this.ldpParametrosAlicuotas = ldpParametrosAlicuotas;
	}

	public RadioButton getRadioButtonAlicuota() {
		return radioButtonAlicuota;
	}

	public void setRadioButtonAlicuota(RadioButton radioButtonAlicuota) {
		this.radioButtonAlicuota = radioButtonAlicuota;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	private Object lastSelected1 = null;

	public Object getRBSelected1() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected1) ? sv : null;
	}

	public void setRBSelected1(Object selected) {
		if(selected != null) {
			lastSelected1 = selected;
		}
	}

	public String getCurrentRow1() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow1(int row) {
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	public TableColumn getTableColumnSeleccion() {
		return tableColumnSeleccion;
	}

	public void setTableColumnSeleccion(TableColumn tableColumnSeleccion) {
		this.tableColumnSeleccion = tableColumnSeleccion;
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

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	public DropDown getDdTipoValor() {
		return ddTipoValor;
	}

	public void setDdTipoValor(DropDown ddTipoValor) {
		this.ddTipoValor = ddTipoValor;
	}

	public SingleSelectOptionsList getDdTipoValorOptions() {
		return ddTipoValorOptions;
	}

	public void setDdTipoValorOptions(SingleSelectOptionsList ddTipoValorOptions) {
		this.ddTipoValorOptions = ddTipoValorOptions;
	}

	public TextField getTextField1() {
		return textField1;
	}

	public void setTextField1(TextField textField1) {
		this.textField1 = textField1;
	}

	public ObjectListDataProvider getObjectListDataProvider5() {
		return getLdpParametros();
	}

	public ObjectListDataProvider getLdpParametros() {
		return ldpParametros;
	}

	public void setLdpParametros(ObjectListDataProvider ldpParametros) {
		this.ldpParametros = ldpParametros;
	}

	private List getListaDelCommunication5() {
		return this.getCommunicationSAICBean().getListaParametrosReliquidacion();
	}

	private void setListaDelCommunication5(List lista) {
		this.getCommunicationSAICBean().setListaParametrosReliquidacion(lista);
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

	private Listbox lbVariables = new Listbox();
	private Listbox lbVariablesAlicuota = new Listbox();
	private Listbox lbVariablesACargar = new Listbox();
	private Listbox lbVariablesACargarAlicuota = new Listbox();

	public Listbox getLbVariablesACargarAlicuota() {
		return lbVariablesACargarAlicuota;
	}

	public void setLbVariablesACargarAlicuota(Listbox lbVariablesACargarAlicuota) {
		this.lbVariablesACargarAlicuota = lbVariablesACargarAlicuota;
	}

	public Listbox getLbVariablesAlicuota() {
		return lbVariablesAlicuota;
	}

	public void setLbVariablesAlicuota(Listbox lbVariablesAlicuota) {
		this.lbVariablesAlicuota = lbVariablesAlicuota;
	}

	private Label lblFechaEmision = new Label();
	private Label label2 = new Label();
	private Label lblFecha = new Label();
	private Label lblObligacion = new Label();
	private Label lblParametroValor = new Label();
	private Label lblParametroValuadoAlicuota = new Label();
	private Label lblAplicarInteres = new Label();

	private Checkbox cbAplicarInteres = new Checkbox();

	public Checkbox getCbAplicarInteres() {
		return cbAplicarInteres;
	}

	public void setCbAplicarInteres(Checkbox cbAplicarInteres) {
		this.cbAplicarInteres = cbAplicarInteres;
	}

	public Label getLblAplicarInteres() {
		return lblAplicarInteres;
	}

	public void setLblAplicarInteres(Label lblAplicarInteres) {
		this.lblAplicarInteres = lblAplicarInteres;
	}

	public Label getLblParametroValuadoAlicuota() {
		return lblParametroValuadoAlicuota;
	}

	public void setLblParametroValuadoAlicuota(Label lblParametroValuadoAlicuota) {
		this.lblParametroValuadoAlicuota = lblParametroValuadoAlicuota;
	}

	private Label lblRecargo = new Label();
	private Label lblMultas = new Label();
	private Label lblInteres = new Label();
	private Label lblAclaracion = new Label();
	private Label lblValorTasa = new Label();

	public Label getLblValorTasa() {
		return lblValorTasa;
	}

	public void setLblValorTasa(Label lblValorTasa) {
		this.lblValorTasa = lblValorTasa;
	}

	public Label getLblAclaracion() {
		return lblAclaracion;
	}

	public void setLblAclaracion(Label lblAclaracion) {
		this.lblAclaracion = lblAclaracion;
	}

	public Label getLblMultas() {
		return lblMultas;
	}

	public void setLblMultas(Label lblMultas) {
		this.lblMultas = lblMultas;
	}

	public TextField getTfMultas() {
		return tfMultas;
	}

	public void setTfMultas(TextField tfMultas) {
		this.tfMultas = tfMultas;
	}

	public Label getLblRecargos() {
		return lblRecargo;
	}

	public void setLblRecargos(Label lblRecargos) {
		this.lblRecargo = lblRecargos;
	}

	public Label getLblFechaEmision() {
		return lblFechaEmision;
	}

	public void setLblFechaEmision(Label l) {
		this.lblFechaEmision = l;
	}

	private Button btnGenerarReliquidacion = new Button();
	private HtmlAjaxCommandButton btnAddParametro = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnDelParametro = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnAddParametroAlicuota = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnDelParametroAlicuota = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnAddParametro() {
		return btnAddParametro;
	}

	public void setBtnAddParametro(HtmlAjaxCommandButton btnAddParametro) {
		this.btnAddParametro = btnAddParametro;
	}

	public HtmlAjaxCommandButton getBtnDelParametro() {
		return btnDelParametro;
	}

	public void setBtnDelParametro(HtmlAjaxCommandButton btnDelParametro) {
		this.btnDelParametro = btnDelParametro;
	}

	public HtmlAjaxCommandButton getBtnAddParametroAlicuota() {
		return btnAddParametroAlicuota;
	}

	public void setBtnAddParametroAlicuota(HtmlAjaxCommandButton btnAddParametroAlicuota) {
		this.btnAddParametroAlicuota = btnAddParametroAlicuota;
	}

	public HtmlAjaxCommandButton getBtnDelParametroAlicuota() {
		return btnDelParametroAlicuota;
	}

	public void setBtnDelParametroAlicuota(HtmlAjaxCommandButton btnDelParametroAlicuota) {
		this.btnDelParametroAlicuota = btnDelParametroAlicuota;
	}

	private Button btnSeleccionarDigesto = new Button();
	private HtmlAjaxCommandButton btnLimpiarDigesto = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarDigesto() {
		return btnLimpiarDigesto;
	}

	public void setBtnLimpiarDigesto(HtmlAjaxCommandButton btnLimpiarDigesto) {
		this.btnLimpiarDigesto = btnLimpiarDigesto;
	}

	public Button getBtnSeleccionarDigesto() {
		return btnSeleccionarDigesto;
	}

	public void setBtnSeleccionarDigesto(Button btnSeleccionarDigesto) {
		this.btnSeleccionarDigesto = btnSeleccionarDigesto;
	}

	public Button getBtnGenerarReliquidacion() {
		return btnGenerarReliquidacion;
	}

	public void setBtnGenerarReliquidacion(Button b) {
		this.btnGenerarReliquidacion = b;
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

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private Label lblDigesto = new Label();

	public Label getLblDigesto() {
		return lblDigesto;
	}

	public void setLblDigesto(Label l) {
		this.lblDigesto = l;
	}

	private TextField tfPersona = new TextField();
	private TextField tfFecha = new TextField();
	private TextField tfRecargo = new TextField();
	private TextField tfMultas = new TextField();
	private TextField tfInteres = new TextField();
	private TextField tfValorTasa = new TextField();
	private TextField tfDigesto = new TextField();

	public TextField getTfDigesto() {
		return tfDigesto;
	}

	public void setTfDigesto(TextField tfDigesto) {
		this.tfDigesto = tfDigesto;
	}

	public TextField getTfValorTasa() {
		return tfValorTasa;
	}

	public void setTfValorTasa(TextField tfValorTasa) {
		this.tfValorTasa = tfValorTasa;
	}

	public Label getLblInteres() {
		return lblInteres;
	}

	public void setLblInteres(Label lblInteres) {
		this.lblInteres = lblInteres;
	}

	public TextField getTfInteres() {
		return tfInteres;
	}

	public void setTfInteres(TextField tfInteres) {
		this.tfInteres = tfInteres;
	}

	public Label getLblRecargo() {
		return lblRecargo;
	}

	public void setLblRecargo(Label lblRecargo) {
		this.lblRecargo = lblRecargo;
	}

	public TextField getTfRecargo() {
		return tfRecargo;
	}

	public void setTfRecargo(TextField tfRecargo) {
		this.tfRecargo = tfRecargo;
	}

	private TextArea taFormula = new TextArea();
	private TextArea taModificadores = new TextArea();
	private TextArea taObligacion = new TextArea();
	private TextArea taParametroValor = new TextArea();
	private TextArea taParametroValuadoAlicuota = new TextArea();

	public TextArea getTaParametroValuadoAlicuota() {
		return taParametroValuadoAlicuota;
	}

	public void setTaParametroValuadoAlicuota(TextArea taParametroValuadoAlicuota) {
		this.taParametroValuadoAlicuota = taParametroValuadoAlicuota;
	}

	public TextArea getTaParametroValor() {
		return taParametroValor;
	}

	public void setTaParametroValor(TextArea taParametroValor) {
		this.taParametroValor = taParametroValor;
	}

	public TextArea getTaObligacion() {
		return taObligacion;
	}

	public void setTaObligacion(TextArea taObligacion) {
		this.taObligacion = taObligacion;
	}

	public Label getLblModificadores() {
		return lblModificadores;
	}

	public void setLblModificadores(Label lblModificadores) {
		this.lblModificadores = lblModificadores;
	}

	public Label getLblVencimientos() {
		return lblVencimientos;
	}

	public void setLblVencimientos(Label lblVencimientos) {
		this.lblVencimientos = lblVencimientos;
	}

	public TextArea getTaModificadores() {
		return taModificadores;
	}

	public void setTaModificadores(TextArea taModificadores) {
		this.taModificadores = taModificadores;
	}

	public TextArea getTaVencimientos() {
		return taVencimientos;
	}

	public void setTaVencimientos(TextArea taVencimientos) {
		this.taVencimientos = taVencimientos;
	}

	private TextArea taVencimientos = new TextArea();

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label lblParcela) {
		this.lblFecha = lblParcela;
	}

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tfParcela) {
		this.tfFecha = tfParcela;
	}

	private TextField tfFechaEmision = new TextField();

	public TextField getTfFechaEmision() {
		return tfFechaEmision;
	}

	public void setTfFechaEmision(TextField tf) {
		this.tfFechaEmision = tf;
	}

	private DropDown ddTiposParametro = new DropDown();
	private DefaultOptionsList lbParametroValorDefaultOptions = new DefaultOptionsList();
	private DefaultOptionsList lbVariablesDefaultOptions = new DefaultOptionsList();
	private DefaultOptionsList lbVariablesACargarDefaultOptions = new DefaultOptionsList();
	private DefaultOptionsList lbVariablesAlicuotaDefaultOptions = new DefaultOptionsList();
	private DefaultOptionsList lbVariablesACargarAlicuotaDefaultOptions = new DefaultOptionsList();

	public DefaultOptionsList getLbVariablesACargarAlicuotaDefaultOptions() {
		return lbVariablesACargarAlicuotaDefaultOptions;
	}

	public void setLbVariablesACargarAlicuotaDefaultOptions(DefaultOptionsList lbVariablesACargarAlicuotaDefaultOptions) {
		this.lbVariablesACargarAlicuotaDefaultOptions = lbVariablesACargarAlicuotaDefaultOptions;
	}

	public DefaultOptionsList getLbVariablesAlicuotaDefaultOptions() {
		return lbVariablesAlicuotaDefaultOptions;
	}

	public void setLbVariablesAlicuotaDefaultOptions(DefaultOptionsList lbVariablesAlicuotaDefaultOptions) {
		this.lbVariablesAlicuotaDefaultOptions = lbVariablesAlicuotaDefaultOptions;
	}

	public Listbox getLbVariablesACargar() {
		return lbVariablesACargar;
	}

	public void setLbVariablesACargar(Listbox lbVariablesACargar) {
		this.lbVariablesACargar = lbVariablesACargar;
	}

	public DefaultOptionsList getLbVariablesACargarDefaultOptions() {
		return lbVariablesACargarDefaultOptions;
	}

	public void setLbVariablesACargarDefaultOptions(DefaultOptionsList lbVariablesACargarDefaultOptions) {
		this.lbVariablesACargarDefaultOptions = lbVariablesACargarDefaultOptions;
	}

	public DefaultOptionsList getLbVariablesDefaultOptions() {
		return lbVariablesDefaultOptions;
	}

	public void setLbVariablesDefaultOptions(DefaultOptionsList lbVariablesDefaultOptions) {
		this.lbVariablesDefaultOptions = lbVariablesDefaultOptions;
	}

	public DefaultOptionsList getLbParametroValorDefaultOptions() {
		return lbParametroValorDefaultOptions;
	}

	public void setLbParametroValorDefaultOptions(DefaultOptionsList lbParametroValorDefaultOptions) {
		this.lbParametroValorDefaultOptions = lbParametroValorDefaultOptions;
	}

	public Label getLblObligacion() {
		return lblObligacion;
	}

	public void setLblObligacion(Label lblObligacion) {
		this.lblObligacion = lblObligacion;
	}

	public Label getLblParametroValor() {
		return lblParametroValor;
	}

	public void setLblParametroValor(Label lblParametroValor) {
		this.lblParametroValor = lblParametroValor;
	}

	public TextArea getTaFormula() {
		return taFormula;
	}

	public void setTaFormula(TextArea taFormula) {
		this.taFormula = taFormula;
	}

	private SingleSelectOptionsList ddTiposParametroDefaultOptions = new SingleSelectOptionsList();
	private Label lblFormula = new Label();
	private Label lblModificadores = new Label();
	private Label lblVencimientos = new Label();

	public Label getLblFormula() {
		return lblFormula;
	}

	public void setLblFormula(Label l) {
		this.lblFormula = l;
	}

	private StaticText staticText4 = new StaticText();
	private StaticText stFecha = new StaticText();
	private StaticText stFechaEmision = new StaticText();

	public StaticText getStFechaEmision() {
		return stFechaEmision;
	}

	public void setStFechaEmision(StaticText stFechaEmision) {
		this.stFechaEmision = stFechaEmision;
	}

	public StaticText getStFecha() {
		return stFecha;
	}

	public void setStFecha(StaticText stFecha) {
		this.stFecha = stFecha;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private HtmlPanelGrid gdpGenerarLiquidaciones = new HtmlPanelGrid();

	public HtmlPanelGrid getGdpGenerarLiquidaciones() {
		return gdpGenerarLiquidaciones;
	}

	public void setGdpGenerarLiquidaciones(HtmlPanelGrid hpg) {
		this.gdpGenerarLiquidaciones = hpg;
	}

	private StaticText stMensajeGeneracion1 = new StaticText();

	public StaticText getStMensajeGeneracion1() {
		return stMensajeGeneracion1;
	}

	public void setStMensajeGeneracion1(StaticText st) {
		this.stMensajeGeneracion1 = st;
	}

	private StaticText stSpacer1 = new StaticText();

	public StaticText getStSpacer1() {
		return stSpacer1;
	}

	public void setStSpacer1(StaticText st) {
		this.stSpacer1 = st;
	}

	private Button btnGenerarLiquidacionesFinal = new Button();
	private Button btnLimpiarPersona = new Button();
	private Button btnNuevoParametro = new Button();

	public Button getBtnNuevoParametro() {
		return btnNuevoParametro;
	}

	public void setBtnNuevoParametro(Button btnNuevoParametro) {
		this.btnNuevoParametro = btnNuevoParametro;
	}

	public Button getBtnQuitarParametro() {
		return btnQuitarParametro;
	}

	public void setBtnQuitarParametro(Button btnQuitarParametro) {
		this.btnQuitarParametro = btnQuitarParametro;
	}

	public DropDown getDdTiposParametro() {
		return ddTiposParametro;
	}

	public void setDdTiposParametro(DropDown ddTiposParametro) {
		this.ddTiposParametro = ddTiposParametro;
	}

	public SingleSelectOptionsList getDdTiposParametroDefaultOptions() {
		return ddTiposParametroDefaultOptions;
	}

	public void setDdTiposParametroDefaultOptions(SingleSelectOptionsList ddTiposParametroDefaultOptions) {
		this.ddTiposParametroDefaultOptions = ddTiposParametroDefaultOptions;
	}

	public Listbox getLbVariables() {
		return lbVariables;
	}

	public void setLbVariables(Listbox lbVariables) {
		this.lbVariables = lbVariables;
	}

	private Button btnQuitarParametro = new Button();

	public Button getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(Button btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}

	public Button getBtnGenerarLiquidacionesFinal() {
		return btnGenerarLiquidacionesFinal;
	}

	public void setBtnGenerarLiquidacionesFinal(Button b) {
		this.btnGenerarLiquidacionesFinal = b;
	}

	private ImageComponent image1 = new ImageComponent();

	public ImageComponent getImage1() {
		return image1;
	}

	public void setImage1(ImageComponent ic) {
		this.image1 = ic;
	}

	private HtmlPanelGrid grpCargando = new HtmlPanelGrid();

	public HtmlPanelGrid getGrpCargando() {
		return grpCargando;
	}

	public void setGrpCargando(HtmlPanelGrid hpg) {
		this.grpCargando = hpg;
	}

	private ImageComponent image2 = new ImageComponent();

	public ImageComponent getImage2() {
		return image2;
	}

	public void setImage2(ImageComponent ic) {
		this.image2 = ic;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private Button btnGenerarLiquidacionPrueba = new Button();

	public Button getBtnGenerarLiquidacionPrueba() {
		return btnGenerarLiquidacionPrueba;
	}

	public void setBtnGenerarLiquidacionPrueba(Button b) {
		this.btnGenerarLiquidacionPrueba = b;
	}

	private StaticText stSeparador1 = new StaticText();

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText st) {
		this.stSeparador1 = st;
	}

	private ObjectListDataProvider ldpParametrosFormula = new ObjectListDataProvider();
	private ObjectListDataProvider ldpNuevosParametrosFormula = new ObjectListDataProvider();
	private ObjectListDataProvider ldpParametrosFormulaAlicuota = new ObjectListDataProvider();
	private ObjectListDataProvider ldpNuevosParametrosFormulaAlicuota = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpNuevosParametrosFormulaAlicuota() {
		return ldpNuevosParametrosFormulaAlicuota;
	}

	public void setLdpNuevosParametrosFormulaAlicuota(ObjectListDataProvider ldpNuevosParametrosFormulaAlicuota) {
		this.ldpNuevosParametrosFormulaAlicuota = ldpNuevosParametrosFormulaAlicuota;
	}

	public ObjectListDataProvider getLdpParametrosFormulaAlicuota() {
		return ldpParametrosFormulaAlicuota;
	}

	public void setLdpParametrosFormulaAlicuota(ObjectListDataProvider ldpParametrosFormulaAlicuota) {
		this.ldpParametrosFormulaAlicuota = ldpParametrosFormulaAlicuota;
	}

	public ObjectListDataProvider getLdpParametrosFormula() {
		return ldpParametrosFormula;
	}

	public void setLdpParametrosFormula(ObjectListDataProvider ldpParametrosFormula) {
		this.ldpParametrosFormula = ldpParametrosFormula;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public GenerarReliquidacion() {
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (muni.CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationCajaBean getCommunicationCajaBean() {
		return (muni.CommunicationCajaBean) getBean("CommunicationCajaBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationSAICBean getCommunicationSAICBean() {
		return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.RequestBean1 getRequestBean1() {
		return (muni.RequestBean1) getBean("RequestBean1");
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina, ya sea directamente mediante un URL o de manera indirecta a trav�s de
	 * la navegaci�n de p�ginas. Puede personalizar este m�todo para adquirir recursos que se necesitar�n para los controladores de eventos y m�todos del
	 * proceso, sin tener en cuenta si esta p�gina realiza procesamiento de devoluci�n de env�os.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores de propiedad de los componentes <strong>no</strong> representan ning�n
	 * valor enviado con esta petici�n. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se proces�.
	 * </p>
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
		} catch(Exception e) {
			log("GenerarReliquidacion Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */
	public void prerender() {
		boolean existeIdSubSesionReq = false;
		boolean existeIdPaginaReq = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag = false;
		boolean recarga = false;

		if(this.getRequestBean1() != null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag = this.getIdPagina() != null;

		// Pagina nueva - Inicio de transaccion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if(this.PUEDE_SER_PAGINA_INICIAL) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();
			}
		}

		// Recarga de la misma pagina por validacion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// .. ver si es as� realmente..
		}

		// Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if(existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();
		}

		// Pagina nueva - hacia atras en la transaccion
		if(existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());
		}

		if(!recarga) {
			this.mostrarEstadoObjetosUsados();
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	public void destroy() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new RegistroDeuda()); // 0 registro de deuda del contribuyente
		ep.getObjetos().add(ind++, new LiquidacionTasa()); // 1 registro de deuda del contribuyente
		ep.getObjetos().add(ind++, null); // 2 fecha de Reliquidacion
		ep.getObjetos().add(ind++, null); // 3 Persona
		ep.getObjetos().add(ind++, new DigestoMunicipal());
		ep.getObjetos().add(ind++, new ArrayList()); // 5 listado de Parametros (lbParametros)
		ep.getObjetos().add(ind++, new ArrayList()); // 6 listado de Parametros (lbParametros)
		// ep.getObjetos().add(ind++, new Reliquidacion());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		if(this.getListaDelCommunication5() != null) {
			this.getListaDelCommunication5().clear();
		}

		return ep;
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		RegistroDeuda registroDeuda = (RegistroDeuda) this.obtenerObjetoDelElementoPila(0, RegistroDeuda.class);
		LiquidacionTasa liquidacionTasa = (LiquidacionTasa) this.obtenerObjetoDelElementoPila(1, LiquidacionTasa.class);
		Date fechaReliquidacion = (Date) this.obtenerObjetoDelElementoPila(2, Date.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, null);
		DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(4, DigestoMunicipal.class);
		// ArrayList nuevosParametrosAValuar = (ArrayList) this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		List locListadoAlicuota = null;
		// Reliquidacion reliquidacion = (Reliquidacion) this.obtenerObjetoDelElementoPila(6, Reliquidacion.class);

		Object fecha = tfFecha.getText();
		/*
		 * VER
		 */
		if(fecha != null && fecha != "") {
			fechaReliquidacion = Conversor.getFechaCortaDeString(fecha.toString());
		} else {
			fechaReliquidacion = null;
		}

		if(digestoMunicipal == null && digestoMunicipal.getIdDigestoMunicipal() == -1) {
			digestoMunicipal = null;
		}

		liquidacionTasa = (LiquidacionTasa) registroDeuda;
		this.getObjectListDataProvider().commitChanges();
		this.getObjectListDataProvider2().commitChanges();

		this.getObjectListDataProvider5().commitChanges();
		List nuevosParametrosAValuar = this.getObjectListDataProvider5().getList();

		if(nuevosParametrosAValuar != null && nuevosParametrosAValuar.size() > 0) {
			this.setListaDelCommunication5(nuevosParametrosAValuar);
			this.getObjectListDataProvider5().setList(nuevosParametrosAValuar);
		}

		this.getObjectListDataProvider4().commitChanges();
		ArrayList nuevosParametrosAValuarAlicuota = (ArrayList) this.getObjectListDataProvider4().getList();
		
		if(nuevosParametrosAValuarAlicuota.isEmpty()) {
			nuevosParametrosAValuarAlicuota = null;
		}
		if(nuevosParametrosAValuarAlicuota != null && nuevosParametrosAValuarAlicuota.size() > 0) {
			this.setListaDelCommunication4(nuevosParametrosAValuarAlicuota);
			this.getObjectListDataProvider4().setList(nuevosParametrosAValuarAlicuota);
		}

		if(!liquidacionTasa.getListaParametrosValuados().isEmpty()) {
			List locListado = new ArrayList();

			if(!liquidacionTasa.getListaParametrosValuados().isEmpty()) {
				Iterator iterador = liquidacionTasa.getListaParametrosValuados().iterator();
				while(iterador.hasNext()) {
					ParametroValuado locParametroValuado = (ParametroValuado) iterador.next();
					locListado.add(locParametroValuado.getNombreParametro());
				}
			}
			this.getObjectListDataProvider().setList(locListado);
		}

		// Option[] opParametros = null;
		// opParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider().getObjects(), "cap");
		// lbVariablesDefaultOptions.setOptions(opParametros);
		//
		// Option[] opNuevosParametros = null;
		// opNuevosParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider2().getObjects(), "cap");
		// lbVariablesACargarDefaultOptions.setOptions(opNuevosParametros);

		if(!liquidacionTasa.getListaAlicuotasLiquidadas().isEmpty()) {
			locListadoAlicuota = new ArrayList();

			for(Object obj : liquidacionTasa.getListaAlicuotasLiquidadas()) {
				AlicuotaLiquidada locAlicuotaLiquidada = (AlicuotaLiquidada) obj;
				if(locAlicuotaLiquidada.getListaParametrosValuados() != null && locAlicuotaLiquidada.getListaParametrosValuados().size() > 0) {
					locListadoAlicuota.add(locAlicuotaLiquidada.getListaParametrosValuados());
				}
			}
			System.out.println("GE cantidad--------" + locListadoAlicuota.size());
			this.getObjectListDataProvider3().setList(locListadoAlicuota);
		}

//		Option[] opParametrosAlicuota = null;
//		opParametrosAlicuota = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getObjectListDataProvider3().getObjects(), "cap");
//		lbVariablesAlicuotaDefaultOptions.setOptions(opParametrosAlicuota);

//		Option[] opNuevosParametrosAlicuota = null;
//		opNuevosParametrosAlicuota = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getObjectListDataProvider4().getObjects(), "cap");
//		lbVariablesACargarAlicuotaDefaultOptions.setOptions(opNuevosParametrosAlicuota);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, registroDeuda);
		this.getElementoPila().getObjetos().set(ind++, liquidacionTasa);
		this.getElementoPila().getObjetos().set(ind++, fechaReliquidacion);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);
		// this.getElementoPila().getObjetos().set(ind++, nuevosParametrosAValuar);
		this.getElementoPila().getObjetos().set(6, nuevosParametrosAValuarAlicuota);

		// this.getElementoPila().getObjetos().set(ind++, reliquidacion);
	}

	private void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		RegistroDeuda registroDeuda = null;
		LiquidacionTasa liquidacionTasa = null;
		Persona persona = null;
		Date fechaEmision = null;
		Date fechaVencimiento = null;
		ArrayList nuevosParametrosAValuar = null;
		ArrayList nuevosParametrosAValuarAlicuota = null;
		ArrayList listaParametrosValuadosAlicuota = null;
		DigestoMunicipal digestoMunicipal = null;
		Reliquidacion reliquidacion = null;
		this.getTfFecha().setText(null);

		if(this.getRequestBean1().getObjetoSeleccion() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoSeleccion();

			if(seleccionado instanceof RegistroDeuda) {
				registroDeuda = (RegistroDeuda) seleccionado;
				this.getElementoPila().getObjetos().set(3, persona);
				this.getRequestBean1().setObjetoSeleccion(null);
			}
			if(seleccionado instanceof DigestoMunicipal) {
				digestoMunicipal = (DigestoMunicipal) seleccionado;
				this.getElementoPila().getObjetos().set(4, digestoMunicipal);
				this.getRequestBean1().setObjetoSeleccion(null);

			}

		}

		if(this.getRequestBean1().getObjetoABM() != null) {

			registroDeuda = (RegistroDeuda) this.getRequestBean1().getObjetoABM();
			// reliquidacion = (Reliquidacion) this.getRequestBean1().getObjetoABM();
			liquidacionTasa = (LiquidacionTasa) registroDeuda;
			// liquidacionTasa = (LiquidacionTasa) reliquidacion.getLiquidacionTasa();
			fechaEmision = registroDeuda.getFechaEmision();
			persona = (Persona) registroDeuda.getPersona();

			if(registroDeuda.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA)) {
				fechaVencimiento = Conversor.getFechaFormatoDeString(Calendar.getInstance().getTime().toString(), "dd/MM/yyyy");
			} else if(registroDeuda.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VIGENTE)) {
				fechaVencimiento = liquidacionTasa.getFechaVencimiento();
			}

			this.getElementoPila().getObjetos().set(0, registroDeuda);
			this.getElementoPila().getObjetos().set(1, liquidacionTasa);
			this.getElementoPila().getObjetos().set(2, fechaVencimiento);
			this.getElementoPila().getObjetos().set(3, persona);
			this.getElementoPila().getObjetos().set(4, digestoMunicipal);

		}

		registroDeuda = (RegistroDeuda) this.obtenerObjetoDelElementoPila(0, RegistroDeuda.class);
		liquidacionTasa = (LiquidacionTasa) this.obtenerObjetoDelElementoPila(1, LiquidacionTasa.class);
		fechaVencimiento = (Date) this.obtenerObjetoDelElementoPila(2, Date.class);
		persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class); /*
																				 * Ver si esto no esta al pedo. solo usas Registro Deuda
																				 */
		digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(4, DigestoMunicipal.class);
		// nuevosParametrosAValuar = (ArrayList) this.obtenerObjetoDelElementoPila(5, ArrayList.class);
//		nuevosParametrosAValuarAlicuota = (ArrayList) this.obtenerObjetoDelElementoPila(6, ArrayList.class);

		if(digestoMunicipal.getIdDigestoMunicipal() == -1) {
			digestoMunicipal = null;

		}
		liquidacionTasa = (LiquidacionTasa) registroDeuda;
		ArrayList locListadoParametrosFormula = new ArrayList();

		if(!liquidacionTasa.getListaParametrosValuados().isEmpty()) {
			Iterator iter = liquidacionTasa.getListaParametrosValuados().iterator();
			while(iter.hasNext()) {
				locListadoParametrosFormula.add(iter.next());
			}
		} else {
			locListadoParametrosFormula = null;
		}

		if(!liquidacionTasa.getListaParametrosValuados().isEmpty()) {
			Set<String> locListado = new HashSet<String>();

			if(!liquidacionTasa.getListaParametrosValuados().isEmpty()) {
				Iterator iterador = liquidacionTasa.getListaParametrosValuados().iterator();
				while(iterador.hasNext()) {
					ParametroValuado locParametroValuado = (ParametroValuado) iterador.next();
					locListado.add(locParametroValuado.getNombreParametro());
				}
			}
			this.getObjectListDataProvider().setList(new ArrayList(locListado));
			this.setListaDelCommunication(new ArrayList(locListado));
		}

		nuevosParametrosAValuar = (ArrayList) this.getListaDelCommunication5();
		if(nuevosParametrosAValuar != null && nuevosParametrosAValuar.size() > 0) {
			this.getObjectListDataProvider5().setList(nuevosParametrosAValuar);
		}
		
		nuevosParametrosAValuarAlicuota = (ArrayList) this.getListaDelCommunication4();
		if(nuevosParametrosAValuarAlicuota != null && nuevosParametrosAValuarAlicuota.size() > 0) {
			this.getObjectListDataProvider4().setList(nuevosParametrosAValuarAlicuota);
		}

		Option[] opParametros = null;
		opParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider().getObjects(), "cap");
		lbVariablesDefaultOptions.setOptions(opParametros);

		this.getTfPersona().setText(registroDeuda.getPersona().toString());
		this.getTfFechaEmision().setText(Conversor.getStringDeFechaCorta(registroDeuda.getFechaEmision()));

		this.getTfFecha().setText(Conversor.getStringDeFechaCorta(fechaVencimiento));

		if(digestoMunicipal != null) {
			this.getTfDigesto().setText(digestoMunicipal.toString());
		} else {
			this.getTfDigesto().setText("");
		}

		this.getTfValorTasa().setText(liquidacionTasa.getValor());
		this.getTaFormula().setText(liquidacionTasa.getStringFormula());
		this.getTaObligacion().setText(liquidacionTasa.getStringObligacion());
		this.getTaModificadores().setText(liquidacionTasa.getStringModificadoresLiquidacion());
		this.getTaVencimientos().setText(liquidacionTasa.getStringVencimientos());
		this.getTaParametroValor().setText(liquidacionTasa.getStringParametrosValuados());
		this.getTaParametroValuadoAlicuota().setText(liquidacionTasa.getStringParametrosValuadosAlicuota());

		listaParametrosValuadosAlicuota = new ArrayList();
		for(Object object : liquidacionTasa.getListaAlicuotasLiquidadas()) {
			AlicuotaLiquidada locAlicuotaLiquidada = (AlicuotaLiquidada) object;
			if(locAlicuotaLiquidada.getListaParametrosValuados() != null && locAlicuotaLiquidada.getListaParametrosValuados().size() > 0) {
				listaParametrosValuadosAlicuota.addAll(locAlicuotaLiquidada.getListaParametrosValuados());
				this.setListaDelCommunication3(listaParametrosValuadosAlicuota);
				this.getObjectListDataProvider3().setList(this.getListaDelCommunication3());
			}
		}

		opParametros = null;
		opParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getObjectListDataProvider3().getObjects(), "cap");
		lbVariablesAlicuotaDefaultOptions.setOptions(opParametros);

//		Option[] opNuevosParametros = null;
//		opNuevosParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getObjectListDataProvider4().getObjects(), "cap");
//		System.out.println("obj 4--- " + this.getObjectListDataProvider4().getList().size());
//		lbVariablesACargarAlicuotaDefaultOptions.setOptions(opNuevosParametros);
		// ////////////////////////////////////////////////

		this.getTfRecargo().setText(liquidacionTasa.getRecargo());
		this.getTfMultas().setText(liquidacionTasa.getMultas());
		this.getTfInteres().setText(liquidacionTasa.getInteres());

	}

	private void crearElementoPila() {
		ElementoPila ep = new ElementoPila();
		ep.setCasoNavegacion(CASO_NAVEGACION);
		ep.setIdPagina(this.getIdPagina());
		ep.setIdSubSesion(this.getIdSubSesion());
		ep.setNombrePagina(NOMBRE_PAGINA);

		ep = this.agregarObjetosAElementoPila(ep);

		this.getSessionBean1().getMgrPilas().addElemento(ep);
	}

	// Parametros de la formula
	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpParametrosFormula();
	}

	private ObjectListDataProvider getObjectListDataProvider2() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpNuevosParametrosFormula();
	}

	// Parametros de la formula
	private ObjectListDataProvider getObjectListDataProvider3() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpParametrosFormulaAlicuota();
	}

	private ObjectListDataProvider getObjectListDataProvider4() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpNuevosParametrosFormulaAlicuota();
	}

	public ObjectListDataProvider getLdpNuevosParametrosFormula() {
		return ldpNuevosParametrosFormula;
	}

	public void setLdpNuevosParametrosFormula(ObjectListDataProvider ldpNuevosParametrosFormula) {
		this.ldpNuevosParametrosFormula = ldpNuevosParametrosFormula;
	}

	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaTiposParametro();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaTiposParametro(lista);
	}

	private ArrayList getListaDelCommunication3() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaParametroAlicuotaFormula();
	}

	private void setListaDelCommunication3(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaParametroAlicuotaFormula(lista);
	}

	private ArrayList getListaDelCommunication4() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaParametroAlicuotaNuevos();
	}

	private void setListaDelCommunication4(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaParametroAlicuotaNuevos(lista);
	}

	private String prepararParaVolver(String pAccionRetorno) {
		this.getRequestBean1().setAccion(pAccionRetorno);
		String retorno = null;
		ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
		if(locElementoAnterior != null) {
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
			if(objeto == null) {
				objeto = tipoClase.newInstance();
			}
		} catch(Exception ex) {
		}
		return objeto;
	}

	private void acomodarSeleccionado() {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if(seleccionado != null) {
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
			if(campo != null) {
				campo.setText(null);
			}
		} catch(Exception ex) {
		}
	}

	public String btnSeleccionarDigesto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 1;

		if(ultimo) {

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
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfDigesto());
			this.getTfDigesto().setText(" ");
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAddParametro_action() {
		String retorno = null;
		RegistroDeuda registroDeuda = null;
		LiquidacionTasa liquidacionTasa = null;
		ArrayList nuevosParametrosAValuar = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		if(ultimo) {
			nuevosParametrosAValuar = (ArrayList) this.getListaDelCommunication5();

			if(nuevosParametrosAValuar == null || nuevosParametrosAValuar.isEmpty()) {
				nuevosParametrosAValuar = new ArrayList();
			}

			if(this.getRequestBean1().getObjetoABM() != null) {
				registroDeuda = (RegistroDeuda) this.getRequestBean1().getObjetoABM();
				if(registroDeuda.getIdRegistroDeuda() != -1) {
					liquidacionTasa = (LiquidacionTasa) registroDeuda;
				} else {
					liquidacionTasa = null;
				}
			}

			Object seleccionado = null;

			if(this.getLbVariables().getSelected() != null) {
				seleccionado = this.getLbVariables().getSelected();

				ParametroReliquidacion locParametro = new ParametroReliquidacion();
				locParametro.setNombreParametro(((String) seleccionado).toString());

				if(!nuevosParametrosAValuar.contains(locParametro)) {
					nuevosParametrosAValuar.add(locParametro);

					this.setListaDelCommunication5(nuevosParametrosAValuar);
					this.getObjectListDataProvider5().setList(nuevosParametrosAValuar);
				}
			}

			// this.getObjectListDataProvider2().setList(nuevosParametrosAValuar);
			// this.getElementoPila().getObjetos().set(5, nuevosParametrosAValuar);

			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAddParametroAlicuota_action() {
		String retorno = null;
		RegistroDeuda registroDeuda = null;
		LiquidacionTasa liquidacionTasa = null;
		ArrayList nuevosParametrosAlicuotaAValuar = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		if(ultimo) {
			nuevosParametrosAlicuotaAValuar = (ArrayList) this.getListaDelCommunication4();

			if(nuevosParametrosAlicuotaAValuar == null || nuevosParametrosAlicuotaAValuar.isEmpty()) {
				nuevosParametrosAlicuotaAValuar = new ArrayList();
			}

			if(this.getRequestBean1().getObjetoABM() != null) {
				registroDeuda = (RegistroDeuda) this.getRequestBean1().getObjetoABM();
				if(registroDeuda.getIdRegistroDeuda() != -1) {
					liquidacionTasa = (LiquidacionTasa) registroDeuda;
				} else {
					liquidacionTasa = null;
				}
			}

			Object seleccionado = null;

			if(this.getLbVariablesAlicuota().getSelected() != null) {
				seleccionado = this.getLbVariablesAlicuota().getSelected();

				ParametroReliquidacion locParametro = new ParametroReliquidacion();
				locParametro.setNombreParametro(((String) seleccionado).toString());

				if(!nuevosParametrosAlicuotaAValuar.contains(locParametro)) {
					nuevosParametrosAlicuotaAValuar.add(locParametro);

					this.setListaDelCommunication4(nuevosParametrosAlicuotaAValuar);
					this.getObjectListDataProvider4().setList(nuevosParametrosAlicuotaAValuar);
				}
			}
			this.getElementoPila().getObjetos().set(6, nuevosParametrosAlicuotaAValuar);
			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
		
	}

	public String btnGenerarReliquidacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		List listadoReliquidacion = new ArrayList();

		if(ultimo) {

			try {
				this.guardarEstadoObjetosUsados();
				// CAMBIAR: Validar los campos necesarios.
				Validador v = new Validador();
				UIComponent[] noVacios = new UIComponent[1];
				String[] nomNoVacios = new String[1];
				UIComponent[] fechas = new UIComponent[1];
				String[] nomFechas = new String[1];

				int pos = 0;
				noVacios[pos] = this.getTfFecha();
				nomNoVacios[pos++] = "Fecha";

				pos = 0;
				fechas[pos] = this.getTfFecha();
				nomFechas[pos++] = "Fecha de Nuevo Vencimiento";

				RegistroDeuda registroDeuda = (RegistroDeuda) this.obtenerObjetoDelElementoPila(0, RegistroDeuda.class);
				LiquidacionTasa liquidacionTasa = (LiquidacionTasa) this.obtenerObjetoDelElementoPila(1, LiquidacionTasa.class);
				Date fechaVencimiento = (Date) this.obtenerObjetoDelElementoPila(2, Date.class);
				Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
				DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(4, DigestoMunicipal.class);
				ArrayList nuevosParametrosAValuar = null;
				ArrayList nuevosParametrosAValuarAlicuota = (ArrayList) this.obtenerObjetoDelElementoPila(6, ArrayList.class);
				// Reliquidacion reliquidacion = (Reliquidacion) this.obtenerObjetoDelElementoPila(6, Reliquidacion.class);

				nuevosParametrosAValuar = (ArrayList) this.getListaDelCommunication5();
				if(nuevosParametrosAValuar != null && nuevosParametrosAValuar.size() > 0) {
					this.getObjectListDataProvider5().setList(nuevosParametrosAValuar);
				}
				
				List<String> listaNuevoParametrosAValuar = new ArrayList<String>();
				List<String> listaNuevoParametrosAlicuotaAValuar = new ArrayList<String>();
				HashMap<String, Object> locMapaParametrosFijos = new HashMap<String, Object>();
				HashMap<String, Object> locMapaParametrosFijosAlicuota = new HashMap<String, Object>();
				
				
				separarParametrosAReliquidar(nuevosParametrosAValuar, listaNuevoParametrosAValuar, locMapaParametrosFijos);
				separarParametrosAReliquidar(nuevosParametrosAValuarAlicuota, listaNuevoParametrosAlicuotaAValuar, locMapaParametrosFijosAlicuota);

				// this.getLdpNuevosParametrosFormula().setList(nuevosParametrosAValuar);

				v.noSonVacios(noVacios, nomNoVacios);
				v.formatoFechaValido(fechas, nomFechas);

				String horaActual = Conversor.getStringDeFechaCorta(Calendar.getInstance().getTime());

				if(registroDeuda.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VIGENTE)) {
					if(fechaVencimiento.before(liquidacionTasa.getFechaVencimiento())) {
						String msg = "La fecha de nuevo vencimiento no puede ser menor a la fecha de vencimiento actual.";
						v.getErrores().add(msg);
					}
				} else if(registroDeuda.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA)) {
					if(fechaVencimiento.before(Conversor.getFechaCortaDeString(horaActual))) {
						String msg = "La fecha de nuevo vencimiento no puede ser menor a la fecha actual.";
						v.getErrores().add(msg);
					}
				}

				if(v.getErrores().size() > 0) {
					error("Existen Errores:");
					for(int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					return null;
				}
				boolean aplicarInteres = this.getCbAplicarInteres().isChecked();
				try {
					this.getCommunicationSAICBean().getRemoteSystemReliquidacion().setLlave(this.getSessionBean1().getLlave());
					listadoReliquidacion = this.getCommunicationSAICBean().getRemoteSystemReliquidacion()
							.reliquidarObligacion(liquidacionTasa, fechaVencimiento, listaNuevoParametrosAValuar, null, locMapaParametrosFijos,
									digestoMunicipal, aplicarInteres);
					this.getRequestBean1().setRespuestaABM(null);
					this.getRequestBean1().setRefrescarTabla(true);
					
					info("Se gener\363 exitosamente la Reliquidaci\363n de la Tasa.");

				} catch(Exception ex) {
					log(CASO_NAVEGACION + "_GuardarError:", ex);
					error(NOMBRE_PAGINA + " - Guardar: " + ex.getMessage());
					ex.printStackTrace();
				}
				retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_GenerarReliquidacionError:", ex);
				error(NOMBRE_PAGINA + " - Generar Reliquidacion: " + ex.getMessage());
				ex.printStackTrace();
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	
	public void separarParametrosAReliquidar (List<ParametroReliquidacion> pListaParametroReliquidacion , List<String> pListaParametrosActuales,
			Map<String,Object> pMapaParametrosFijos){
		if (pListaParametroReliquidacion != null) {
			for (ParametroReliquidacion cadaParametro : pListaParametroReliquidacion) {
				if(cadaParametro.getTipoValor().toString().equals("ACTUAL")) {
					pListaParametrosActuales.add(cadaParametro.getNombreParametro());
				}
				else if (cadaParametro.getTipoValor().toString().equals("FIJO")) {
					if(cadaParametro.getValor().trim().length() == 0) {
						error("Existen Errores:");
						warn("Debe ingresar todos los valores Fijos.");
						this.mostrarEstadoObjetosUsados();
						return;
					} else {
						if(cadaParametro.getValor().startsWith("\"") && cadaParametro.getValor().endsWith("\"")) {
							String locValor = cadaParametro.getValor().trim();
							locValor = locValor.substring(1, locValor.length() - 1);
							pMapaParametrosFijos.put(cadaParametro.getNombreParametro(), locValor);
						} else {
							Double locValor = new Double(cadaParametro.getValor());
							pMapaParametrosFijos.put(cadaParametro.getNombreParametro(), locValor);
						}
					}
				}
			}
		}
	}
	
	public String btnCancelar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {

			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnDelParametro_action() {
		String retorno = null;
		RegistroDeuda registroDeuda = null;
		LiquidacionTasa liquidacionTasa = null;
		ArrayList parametrosValuados = null;
		ArrayList nuevosParametrosAValuar = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {					
					nuevosParametrosAValuar = (ArrayList) this.getListaDelCommunication5();

					if(nuevosParametrosAValuar == null || nuevosParametrosAValuar.isEmpty()) {
						nuevosParametrosAValuar = new ArrayList();
					}

					if(this.getRequestBean1().getObjetoABM() != null) {
						registroDeuda = (RegistroDeuda) this.getRequestBean1().getObjetoABM();
						if(registroDeuda.getIdRegistroDeuda() != -1) {
							liquidacionTasa = (LiquidacionTasa) registroDeuda;
						} else {
							liquidacionTasa = null;
						}
					}

					int index = obtenerFila(rk);
					Object obj = this.getObjectListDataProvider5().getObjects()[index];

					ParametroReliquidacion locParametro = (ParametroReliquidacion) obj;

					if (this.getObjectListDataProvider5().getList().size() > 0) {
						this.getObjectListDataProvider5().commitChanges();
					}
					nuevosParametrosAValuar.remove(locParametro);

					this.setListaDelCommunication5(nuevosParametrosAValuar);
					this.getObjectListDataProvider5().setList(nuevosParametrosAValuar);

					this.guardarEstadoObjetosUsados();

					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				}
			} catch(Exception ex) {
				System.out.println("btnQuitar() entra al catch...");
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	private Integer obtenerFila(RowKey fila) {
		String locValor = fila.toString().substring(7, fila.toString().length() - 1);
		return new Integer(locValor).intValue();
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpParametros().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionadoAlicuota() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupAlicuota");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpParametrosAlicuotas().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}
	
	public String btnDelParametroAlicuota_action() {
		String retorno = null;
		RegistroDeuda registroDeuda = null;
		LiquidacionTasa liquidacionTasa = null;
		ArrayList nuevosParametrosAlicuotaAValuar = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionadoAlicuota();
				if(rk != null) {					
					nuevosParametrosAlicuotaAValuar = (ArrayList) this.getListaDelCommunication4();

					if(nuevosParametrosAlicuotaAValuar == null || nuevosParametrosAlicuotaAValuar.isEmpty()) {
						nuevosParametrosAlicuotaAValuar = new ArrayList();
					}

					if(this.getRequestBean1().getObjetoABM() != null) {
						registroDeuda = (RegistroDeuda) this.getRequestBean1().getObjetoABM();
						if(registroDeuda.getIdRegistroDeuda() != -1) {
							liquidacionTasa = (LiquidacionTasa) registroDeuda;
						} else {
							liquidacionTasa = null;
						}
					}

					int index = obtenerFila(rk);
					Object obj = this.getObjectListDataProvider4().getObjects()[index];

					ParametroReliquidacion locParametro = (ParametroReliquidacion) obj;

					if (this.getObjectListDataProvider4().getList().size() > 0) {
						this.getObjectListDataProvider4().commitChanges();
					}
					nuevosParametrosAlicuotaAValuar.remove(locParametro);

					this.setListaDelCommunication4(nuevosParametrosAlicuotaAValuar);
					this.getObjectListDataProvider4().setList(nuevosParametrosAlicuotaAValuar);

					this.guardarEstadoObjetosUsados();

					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				}
			} catch(Exception ex) {
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
}
