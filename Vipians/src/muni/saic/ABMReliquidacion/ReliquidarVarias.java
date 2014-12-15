/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.saic.ABMReliquidacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.RadioButton;
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
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ParametroReliquidacion;
import com.trascender.saic.recurso.persistent.ParametroValuado;
import com.trascender.saic.recurso.persistent.ParametroValuadoAlicuota;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

/**
 * 
 * @author Fer Luca
 */
public class ReliquidarVarias extends ABMPageBean {

	private MessageGroup messageGroup1 = new MessageGroup();
	private StaticText stSeleccionados = new StaticText();
	private Table table1 = new Table();
	private ObjectListDataProvider ldpLiquidaciones = new ObjectListDataProvider();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableColumn tcPeriodo = new TableColumn();
	private TableColumn tcPersona = new TableColumn();
	private TableColumn tcMonto = new TableColumn();
	private TableColumn tcTipoDeuda = new TableColumn();
	private TableColumn tcEstado = new TableColumn();
	private StaticText stPeriodo = new StaticText();
	private StaticText stMonto = new StaticText();
	private StaticText stTipoDeuda = new StaticText();
	private StaticText stEstado = new StaticText();
	private StaticText stFecha = new StaticText();
	private NumberConverter numberConverter1 = new NumberConverter();
	private Label lblFecha = new Label();
	private Label lblDigesto = new Label();
	private Label lblAplicarInteres = new Label();
	private TextField tfFecha = new TextField();
	private TextField tfDigesto = new TextField();
	private Listbox lbVariables = new Listbox();
	private Listbox lbVariablesAlicuota = new Listbox();
	private Listbox lbVariablesACargar = new Listbox();
	private Listbox lbVariablesACargarAlicuota = new Listbox();
	private DefaultOptionsList lbParametroValorDefaultOptions = new DefaultOptionsList();
	private DefaultOptionsList lbVariablesDefaultOptions = new DefaultOptionsList();
	private DefaultOptionsList lbVariablesACargarDefaultOptions = new DefaultOptionsList();
	private DefaultOptionsList lbVariablesAlicuotaDefaultOptions = new DefaultOptionsList();
	private DefaultOptionsList lbVariablesACargarAlicuotaDefaultOptions = new DefaultOptionsList();
	private Button btnSeleccionarDigesto = new Button();
	private HtmlAjaxCommandButton btnLimpiarDigesto = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnAddParametro = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnAddParametroAlicuota = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnDelParametroAlicuota = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnDelParametro = new HtmlAjaxCommandButton();
	private Checkbox cbAplicarInteres = new Checkbox();
	private ObjectListDataProvider ldpParametrosFormula = new ObjectListDataProvider();
	private ObjectListDataProvider ldpNuevosParametrosFormula = new ObjectListDataProvider();
	private ObjectListDataProvider ldpParametrosFormulaAlicuota = new ObjectListDataProvider();
	private ObjectListDataProvider ldpNuevosParametrosFormulaAlicuota = new ObjectListDataProvider();
	private RadioButton radioButton1 = new RadioButton();
	private TextArea taObligacion = new TextArea();

	public TextArea getTaObligacion() {
		return taObligacion;
	}

	public void setTaObligacion(TextArea taObligacion) {
		this.taObligacion = taObligacion;
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

	public HtmlAjaxCommandButton getBtnLimpiarDigesto() {
		return btnLimpiarDigesto;
	}

	public void setBtnLimpiarDigesto(HtmlAjaxCommandButton btnLimpiarDigesto) {
		this.btnLimpiarDigesto = btnLimpiarDigesto;
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

	public ObjectListDataProvider getLdpNuevosParametrosFormula() {
		return ldpNuevosParametrosFormula;
	}

	public void setLdpNuevosParametrosFormula(ObjectListDataProvider ldpNuevosParametrosFormula) {
		this.ldpNuevosParametrosFormula = ldpNuevosParametrosFormula;
	}

	public ObjectListDataProvider getLdpNuevosParametrosFormulaAlicuota() {
		return ldpNuevosParametrosFormulaAlicuota;
	}

	public void setLdpNuevosParametrosFormulaAlicuota(ObjectListDataProvider ldpNuevosParametrosFormulaAlicuota) {
		this.ldpNuevosParametrosFormulaAlicuota = ldpNuevosParametrosFormulaAlicuota;
	}

	public ObjectListDataProvider getLdpParametrosFormula() {
		return ldpParametrosFormula;
	}

	public void setLdpParametrosFormula(ObjectListDataProvider ldpParametrosFormula) {
		this.ldpParametrosFormula = ldpParametrosFormula;
	}

	public ObjectListDataProvider getLdpParametrosFormulaAlicuota() {
		return ldpParametrosFormulaAlicuota;
	}

	public void setLdpParametrosFormulaAlicuota(ObjectListDataProvider ldpParametrosFormulaAlicuota) {
		this.ldpParametrosFormulaAlicuota = ldpParametrosFormulaAlicuota;
	}

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

	public Button getBtnSeleccionarDigesto() {
		return btnSeleccionarDigesto;
	}

	public void setBtnSeleccionarDigesto(Button btnSeleccionarDigesto) {
		this.btnSeleccionarDigesto = btnSeleccionarDigesto;
	}

	public Checkbox getCbAplicarInteres() {
		return cbAplicarInteres;
	}

	public void setCbAplicarInteres(Checkbox cbAplicarInteres) {
		this.cbAplicarInteres = cbAplicarInteres;
	}

	public DefaultOptionsList getLbParametroValorDefaultOptions() {
		return lbParametroValorDefaultOptions;
	}

	public void setLbParametroValorDefaultOptions(DefaultOptionsList lbParametroValorDefaultOptions) {
		this.lbParametroValorDefaultOptions = lbParametroValorDefaultOptions;
	}

	public Listbox getLbVariables() {
		return lbVariables;
	}

	public void setLbVariables(Listbox lbVariables) {
		this.lbVariables = lbVariables;
	}

	public Listbox getLbVariablesACargar() {
		return lbVariablesACargar;
	}

	public void setLbVariablesACargar(Listbox lbVariablesACargar) {
		this.lbVariablesACargar = lbVariablesACargar;
	}

	public Listbox getLbVariablesACargarAlicuota() {
		return lbVariablesACargarAlicuota;
	}

	public void setLbVariablesACargarAlicuota(Listbox lbVariablesACargarAlicuota) {
		this.lbVariablesACargarAlicuota = lbVariablesACargarAlicuota;
	}

	public DefaultOptionsList getLbVariablesACargarAlicuotaDefaultOptions() {
		return lbVariablesACargarAlicuotaDefaultOptions;
	}

	public void setLbVariablesACargarAlicuotaDefaultOptions(DefaultOptionsList lbVariablesACargarAlicuotaDefaultOptions) {
		this.lbVariablesACargarAlicuotaDefaultOptions = lbVariablesACargarAlicuotaDefaultOptions;
	}

	public DefaultOptionsList getLbVariablesACargarDefaultOptions() {
		return lbVariablesACargarDefaultOptions;
	}

	public void setLbVariablesACargarDefaultOptions(DefaultOptionsList lbVariablesACargarDefaultOptions) {
		this.lbVariablesACargarDefaultOptions = lbVariablesACargarDefaultOptions;
	}

	public Listbox getLbVariablesAlicuota() {
		return lbVariablesAlicuota;
	}

	public void setLbVariablesAlicuota(Listbox lbVariablesAlicuota) {
		this.lbVariablesAlicuota = lbVariablesAlicuota;
	}

	public DefaultOptionsList getLbVariablesAlicuotaDefaultOptions() {
		return lbVariablesAlicuotaDefaultOptions;
	}

	public void setLbVariablesAlicuotaDefaultOptions(DefaultOptionsList lbVariablesAlicuotaDefaultOptions) {
		this.lbVariablesAlicuotaDefaultOptions = lbVariablesAlicuotaDefaultOptions;
	}

	public DefaultOptionsList getLbVariablesDefaultOptions() {
		return lbVariablesDefaultOptions;
	}

	public void setLbVariablesDefaultOptions(DefaultOptionsList lbVariablesDefaultOptions) {
		this.lbVariablesDefaultOptions = lbVariablesDefaultOptions;
	}

	public Label getLblAplicarInteres() {
		return lblAplicarInteres;
	}

	public void setLblAplicarInteres(Label lblAplicarInteres) {
		this.lblAplicarInteres = lblAplicarInteres;
	}

	public Label getLblDigesto() {
		return lblDigesto;
	}

	public void setLblDigesto(Label lblDigesto) {
		this.lblDigesto = lblDigesto;
	}

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label lblFecha) {
		this.lblFecha = lblFecha;
	}

	public StaticText getStFecha() {
		return stFecha;
	}

	public void setStFecha(StaticText stFecha) {
		this.stFecha = stFecha;
	}

	public TextField getTfDigesto() {
		return tfDigesto;
	}

	public void setTfDigesto(TextField tfDigesto) {
		this.tfDigesto = tfDigesto;
	}

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tfFecha) {
		this.tfFecha = tfFecha;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public StaticText getStMonto() {
		return stMonto;
	}

	public void setStMonto(StaticText stMonto) {
		this.stMonto = stMonto;
	}

	public StaticText getStPeriodo() {
		return stPeriodo;
	}

	public void setStPeriodo(StaticText stPeriodo) {
		this.stPeriodo = stPeriodo;
	}

	public StaticText getStTipoDeuda() {
		return stTipoDeuda;
	}

	public void setStTipoDeuda(StaticText stTipoDeuda) {
		this.stTipoDeuda = stTipoDeuda;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public TableColumn getTcPeriodo() {
		return tcPeriodo;
	}

	public void setTcPeriodo(TableColumn tcPeriodo) {
		this.tcPeriodo = tcPeriodo;
	}

	public TableColumn getTcPersona() {
		return tcPersona;
	}

	public void setTcPersona(TableColumn tcPersona) {
		this.tcPersona = tcPersona;
	}

	public TableColumn getTcTipoDeuda() {
		return tcTipoDeuda;
	}

	public void setTcTipoDeuda(TableColumn tcTipoDeuda) {
		this.tcTipoDeuda = tcTipoDeuda;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public ObjectListDataProvider getLdpLiquidaciones() {
		return ldpLiquidaciones;
	}

	public void setLdpLiquidaciones(ObjectListDataProvider ldpLiquidaciones) {
		this.ldpLiquidaciones = ldpLiquidaciones;
	}

	public StaticText getStSeleccionados() {
		return stSeleccionados;
	}

	public void setStSeleccionados(StaticText stSeleccionados) {
		this.stSeleccionados = stSeleccionados;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup messageGroup1) {
		this.messageGroup1 = messageGroup1;
	}

	private ArrayList getListaDelCommunicationParamAlicFormula() {
		return this.getCommunicationHabilitacionesBean().getListaParametroAlicuotaFormula();
	}

	private void setListaDelCommunicationParamAlicFormula(ArrayList lista) {
		this.getCommunicationHabilitacionesBean().setListaParametroAlicuotaFormula(lista);
	}

	private ArrayList getListaDelCommunicationParamAlicNuevos() {
		return this.getCommunicationHabilitacionesBean().getListaParametroAlicuotaNuevos();
	}

	private void setListaDelCommunicationParamAlicNuevos(ArrayList lista) {
		this.getCommunicationHabilitacionesBean().setListaParametroAlicuotaNuevos(lista);
	}

	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLiquidaciones();
	}

	// Parametros de la formula
	private ObjectListDataProvider getObjectListDataProviderParamForm() {
		return this.getLdpParametrosFormula();
	}

	private ObjectListDataProvider getObjectListDataProviderNuevosParamForm() {
		return this.getLdpNuevosParametrosFormula();
	}

	// Parametros de la formula
	private ObjectListDataProvider getObjectListDataProviderParamFormAlic() {
		return this.getLdpParametrosFormulaAlicuota();
	}

	private ObjectListDataProvider getObjectListDataProviderNuevosParamFormAlic() {
		return this.getLdpNuevosParametrosFormulaAlicuota();
	}

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication3() != null) {
			this.getObjectListDataProvider3().setList(this.getListaDelCommunication3());
		}

		Option[] opTipoValor = null;
		opTipoValor = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(ParametroReliquidacion.TipoValor.values(), "cap");
		ddTipoValorOptions.setOptions(opTipoValor);

		Option[] opParametros = null;
		opParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProviderParamForm().getObjects(), "cap");
		lbVariablesDefaultOptions.setOptions(opParametros);

		Option[] opNuevosParametros = null;
		opNuevosParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProviderNuevosParamForm().getObjects(), "cap");
		lbVariablesACargarDefaultOptions.setOptions(opNuevosParametros);

		Option[] opParametrosAlicuota = null;
		opParametrosAlicuota = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProviderParamFormAlic().getObjects(), "");
		lbVariablesAlicuotaDefaultOptions.setOptions(opParametrosAlicuota);

		Option[] opNuevosParametrosAlicuota = null;
		opNuevosParametrosAlicuota = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProviderNuevosParamFormAlic().getObjects(), "");
		lbVariablesACargarAlicuotaDefaultOptions.setOptions(opNuevosParametrosAlicuota);

		this.getNumberConverter1().setPattern("$ #,##0.00");
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

	public TableColumn getTableColumnSeleccion() {
		return tableColumnSeleccion;
	}

	public void setTableColumnSeleccion(TableColumn tableColumnSeleccion) {
		this.tableColumnSeleccion = tableColumnSeleccion;
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

	public ObjectListDataProvider getObjectListDataProvider3() {
		return getLdpParametros();
	}

	public ObjectListDataProvider getLdpParametros() {
		return ldpParametros;
	}

	public void setLdpParametros(ObjectListDataProvider ldpParametros) {
		this.ldpParametros = ldpParametros;
	}

	private List getListaDelCommunication3() {
		return this.getCommunicationSAICBean().getListaParametrosReliquidacionVarias();
	}

	private void setListaDelCommunication3(List lista) {
		this.getCommunicationSAICBean().setListaParametrosReliquidacionVarias(lista);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		List<LiquidacionTasa> listaLiquidaciones = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Date fechaReliquidacion = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		DigestoMunicipal digestoMunicipal = this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
		// ArrayList nuevosParametrosAValuar = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList nuevosParametrosAValuarAlicuota = this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		List locListadoAlicuota = null;

		Object fecha = tfFecha.getText();

		if(fecha != null && fecha != "") {
			fechaReliquidacion = Conversor.getFechaCortaDeString(fecha.toString());
		} else {
			fechaReliquidacion = null;
		}

		if(digestoMunicipal == null && digestoMunicipal.getIdDigestoMunicipal() == -1) {
			digestoMunicipal = null;
		}

		this.getObjectListDataProviderParamForm().commitChanges();
		this.getObjectListDataProviderNuevosParamForm().commitChanges();

		this.getObjectListDataProvider3().commitChanges();
		List nuevosParametrosAValuar = this.getObjectListDataProvider3().getList();

		if(nuevosParametrosAValuar != null && nuevosParametrosAValuar.size() > 0) {
			this.setListaDelCommunication3(nuevosParametrosAValuar);
			this.getObjectListDataProvider3().setList(nuevosParametrosAValuar);
		}

		if(nuevosParametrosAValuarAlicuota.isEmpty()) {
			nuevosParametrosAValuarAlicuota = null;
		}
		if(nuevosParametrosAValuarAlicuota != null && nuevosParametrosAValuarAlicuota.size() > 0) {
			// this.setListaDelCommunication4(nuevosParametrosAValuarAlicuota);
			this.getObjectListDataProviderNuevosParamFormAlic().setList(nuevosParametrosAValuarAlicuota);
		}
		List locListado = new ArrayList();
		for(LiquidacionTasa liquidacionTasa : listaLiquidaciones) {
			if(!liquidacionTasa.getListaParametrosValuados().isEmpty()) {

				if(!liquidacionTasa.getListaParametrosValuados().isEmpty()) {
					Iterator iterador = liquidacionTasa.getListaParametrosValuados().iterator();
					while(iterador.hasNext()) {
						ParametroValuado locParametroValuado = (ParametroValuado) iterador.next();
						locListado.add(locParametroValuado.getNombreParametro());
					}
				}
				// this.getObjectListDataProvider().setList(locListado);
			}
		}
		this.getObjectListDataProviderParamForm().getList().clear();
		this.getObjectListDataProviderParamForm().getList().addAll(locListado);

		// Option[] opParametros = null;
		// opParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProviderParamForm().getObjects(), "cap");
		// lbVariablesDefaultOptions.setOptions(opParametros);
		//
		// Option[] opNuevosParametros = null;
		// opNuevosParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProviderNuevosParamForm().getObjects(),
		// "cap");
		// lbVariablesACargarDefaultOptions.setOptions(opNuevosParametros);

		for(LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
			if(!cadaLiquidacion.getListaAlicuotasLiquidadas().isEmpty()) {
				locListadoAlicuota = new ArrayList();

				for(Object obj : cadaLiquidacion.getListaAlicuotasLiquidadas()) {
					AlicuotaLiquidada locAlicuotaLiquidada = (AlicuotaLiquidada) obj;
					if(locAlicuotaLiquidada.getListaParametrosValuados() != null && locAlicuotaLiquidada.getListaParametrosValuados().size() > 0) {
						locListadoAlicuota.add(locAlicuotaLiquidada.getListaParametrosValuados());
					}
				}
				System.out.println("GE cantidad--------" + locListadoAlicuota.size());
				this.getObjectListDataProviderParamFormAlic().setList(locListadoAlicuota);
			}
		}

		Option[] opParametrosAlicuota = null;
		opParametrosAlicuota = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getObjectListDataProviderParamFormAlic().getObjects(), "cap");
		lbVariablesAlicuotaDefaultOptions.setOptions(opParametrosAlicuota);

		Option[] opNuevosParametrosAlicuota = null;
		opNuevosParametrosAlicuota = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getObjectListDataProviderNuevosParamFormAlic().getObjects(), "cap");
		lbVariablesACargarAlicuotaDefaultOptions.setOptions(opNuevosParametrosAlicuota);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, listaLiquidaciones);
		this.getElementoPila().getObjetos().set(ind++, fechaReliquidacion);
		this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);
		// this.getElementoPila().getObjetos().set(ind++, nuevosParametrosAValuar);
		this.getElementoPila().getObjetos().set(4, nuevosParametrosAValuarAlicuota);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		List<LiquidacionTasa> listaLiquidaciones = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Date fechaVencimiento = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		DigestoMunicipal digestoMunicipal = this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);
		ArrayList nuevosParametrosAValuarAlicuota = this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		ArrayList nuevosParametrosAValuar = null;
		ArrayList listaParametrosValuadosAlicuota = null;

		if(digestoMunicipal.getIdDigestoMunicipal() == -1) {
			digestoMunicipal = null;
		}

		ArrayList locListadoParametrosFormula = new ArrayList();
		Set<String> locListado = new HashSet<String>();
		for(LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
			if(!cadaLiquidacion.getListaParametrosValuados().isEmpty()) {
				Iterator iter = cadaLiquidacion.getListaParametrosValuados().iterator();
				while(iter.hasNext()) {
					locListadoParametrosFormula.add(iter.next());
				}
			}

			if(!cadaLiquidacion.getListaParametrosValuados().isEmpty()) {

				if(!cadaLiquidacion.getListaParametrosValuados().isEmpty()) {
					Iterator iterador = cadaLiquidacion.getListaParametrosValuados().iterator();
					while(iterador.hasNext()) {
						ParametroValuado locParametroValuado = (ParametroValuado) iterador.next();
						locListado.add(locParametroValuado.getNombreParametro());
					}
				}
			}
		}
		this.getObjectListDataProviderParamForm().getList().addAll(locListado);

		nuevosParametrosAValuar = (ArrayList) this.getListaDelCommunication3();
		if(nuevosParametrosAValuar != null && nuevosParametrosAValuar.size() > 0) {
			this.getObjectListDataProvider3().setList(nuevosParametrosAValuar);
		}
		if(nuevosParametrosAValuarAlicuota.size() > 0) {
			this.getObjectListDataProvider3().setList(nuevosParametrosAValuarAlicuota);
		}

		if(nuevosParametrosAValuarAlicuota.size() > 0) {
			this.getObjectListDataProviderNuevosParamFormAlic().setList(nuevosParametrosAValuarAlicuota);
		}

		Option[] opParametros = null;
		opParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProviderParamForm().getObjects(), "cap");
		lbVariablesDefaultOptions.setOptions(opParametros);

		// Option[] opNuevosParametros = null;
		// opNuevosParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProviderNuevosParamForm().getObjects(),
		// "cap");
		// lbVariablesACargarDefaultOptions.setOptions(opNuevosParametros);

		listaParametrosValuadosAlicuota = new ArrayList();
		for(LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
			for(Object object : cadaLiquidacion.getListaAlicuotasLiquidadas()) {
				AlicuotaLiquidada locAlicuotaLiquidada = (AlicuotaLiquidada) object;
				if(locAlicuotaLiquidada.getListaParametrosValuados() != null && locAlicuotaLiquidada.getListaParametrosValuados().size() > 0) {
					listaParametrosValuadosAlicuota.addAll(locAlicuotaLiquidada.getListaParametrosValuados());
					// this.setListaDelCommunication3(listaParametrosValuadosAlicuota);
					this.getObjectListDataProviderParamFormAlic().setList(listaParametrosValuadosAlicuota);
				}
			}
		}

		opParametros = null;
		opParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getObjectListDataProviderParamFormAlic().getObjects(), "cap");
		System.out.println("obj 3--- " + this.getObjectListDataProviderParamFormAlic().getList().size());
		lbVariablesAlicuotaDefaultOptions.setOptions(opParametros);

		Option[] opNuevosParametros = null;
		opNuevosParametros = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getObjectListDataProviderNuevosParamFormAlic().getObjects(), "cap");
		System.out.println("obj 4--- " + this.getObjectListDataProviderNuevosParamFormAlic().getList().size());
		lbVariablesACargarAlicuotaDefaultOptions.setOptions(opNuevosParametros);

		this.getTfFecha().setText(Conversor.getStringDeFechaCorta(fechaVencimiento));

		if(digestoMunicipal != null) {
			this.getTfDigesto().setText(digestoMunicipal.toString());
		} else {
			this.getTfDigesto().setText("");
		}

		this.getObjectListDataProvider().setList(listaLiquidaciones);
	}

	public String btnSeleccionarDigesto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		// int posicionObjetoSeleccionado = 2;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			// this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
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
			this.limpiarObjeto(2, this.getTfDigesto());
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
			nuevosParametrosAValuar = (ArrayList) this.getListaDelCommunication3();

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

					this.setListaDelCommunication3(nuevosParametrosAValuar);
					this.getObjectListDataProvider3().setList(nuevosParametrosAValuar);
				}
			}

			// this.getObjectListDataProviderNuevosParamForm().setList(nuevosParametrosAValuar);
			// this.getElementoPila().getObjetos().set(3, nuevosParametrosAValuar);

			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

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
					nuevosParametrosAValuar = (ArrayList) this.getListaDelCommunication3();

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
					Object obj = this.getObjectListDataProvider3().getObjects()[index];

					ParametroReliquidacion locParametro = (ParametroReliquidacion) obj;

					if(this.getObjectListDataProvider3().getList().size() > 0) {
						this.getObjectListDataProvider3().commitChanges();
					}
					nuevosParametrosAValuar.remove(locParametro);

					this.setListaDelCommunication3(nuevosParametrosAValuar);
					this.getObjectListDataProvider3().setList(nuevosParametrosAValuar);

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

	public String btnAddParametroAlicuota_action() {
		String retorno = null;
		ArrayList nuevosParametrosAValuarAlicuota = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		if(ultimo) {
			nuevosParametrosAValuarAlicuota = this.obtenerObjetoDelElementoPila(4, ArrayList.class);
			if(nuevosParametrosAValuarAlicuota.isEmpty()) {
				nuevosParametrosAValuarAlicuota = new ArrayList();
			}

			Object seleccionado = null;

			if(this.getLbVariablesAlicuota().getSelected() != null) {
				seleccionado = this.getLbVariablesAlicuota().getSelected();
				for(Object cadaParametro : this.getListaDelCommunicationParamAlicFormula()) {
					ParametroValuadoAlicuota locParametroValuadoAlicuota = (ParametroValuadoAlicuota) cadaParametro;
					if(seleccionado.equals(locParametroValuadoAlicuota.toString()) && !nuevosParametrosAValuarAlicuota.contains(locParametroValuadoAlicuota)) {
						nuevosParametrosAValuarAlicuota.add(locParametroValuadoAlicuota);
					}
				}

				this.setListaDelCommunicationParamAlicNuevos(nuevosParametrosAValuarAlicuota);
				this.getObjectListDataProvider().setList(this.getListaDelCommunicationParamAlicNuevos());
				this.getElementoPila().getObjetos().set(4, nuevosParametrosAValuarAlicuota);

				this.guardarEstadoObjetosUsados();

				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnDelParametroAlicuota_action() {
		String retorno = null;
		ArrayList nuevosParametrosAValuarAlicuota = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		if(ultimo) {
			nuevosParametrosAValuarAlicuota = this.obtenerObjetoDelElementoPila(4, ArrayList.class);
			if(nuevosParametrosAValuarAlicuota.isEmpty()) {
				nuevosParametrosAValuarAlicuota = new ArrayList();
			}

			if(this.getLbVariablesACargarAlicuota().getSelected() != null) {
				String seleccionado = (String) this.getLbVariablesACargarAlicuota().getSelected();
				for(Object cadaObject : getListaDelCommunicationParamAlicFormula()) {
					ParametroValuadoAlicuota cadaParametro = (ParametroValuadoAlicuota) cadaObject;
					if(cadaParametro.toString().equals(seleccionado)) {
						nuevosParametrosAValuarAlicuota.remove(cadaParametro);
					}
				}
			}
			this.setListaDelCommunicationParamAlicNuevos(nuevosParametrosAValuarAlicuota);
			this.getObjectListDataProviderNuevosParamFormAlic().setList(this.getListaDelCommunicationParamAlicNuevos());
			this.getElementoPila().getObjetos().set(4, nuevosParametrosAValuarAlicuota);

			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected String getNombrePagina() {
		return "Reliquidar Varias";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ReliquidarVarias";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		ep.getObjetos().add(ind++, new ArrayList()); // 0 lista liquidaciones seleccionadas
		ep.getObjetos().add(ind++, null); // 1 fecha de Reliquidacion
		ep.getObjetos().add(ind++, new DigestoMunicipal()); // 2
		ep.getObjetos().add(ind++, new ArrayList()); // 3 listado de Parametros (lbParametros)
		ep.getObjetos().add(ind++, new ArrayList()); // 4 listado de Parametros (lbParametros)

		if(this.getListaDelCommunication3() != null) {
			this.getListaDelCommunication3().clear();
		}

		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof DigestoMunicipal) {
			DigestoMunicipal digestoMunicipal = (DigestoMunicipal) pObject;
			this.getElementoPila().getObjetos().set(2, digestoMunicipal);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		List<LiquidacionTasa> listaLiquidaciones = (List) pObject;

		this.getElementoPila().getObjetos().set(0, listaLiquidaciones);
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ABMReliquidacion$ReliquidarVarias}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
}