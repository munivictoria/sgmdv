/*
 * AgregarTipoModificador.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.habilitaciones.ABMTipoModificador;

import java.util.ArrayList;
import java.util.List;

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
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.VariableFormula;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaSimple;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMTipoModificador extends ABMPageBean {

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	@Override
	protected void _init() throws Exception {
		ddOperador1DefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[] {new com.sun.rave.web.ui.model.Option("", ""),
				new com.sun.rave.web.ui.model.Option(">", "es mayor que"), new com.sun.rave.web.ui.model.Option("<", "es menor que"),
				new com.sun.rave.web.ui.model.Option("==", "es igual que"), new com.sun.rave.web.ui.model.Option(">=", "es mayor o igual que"),
				new com.sun.rave.web.ui.model.Option("<=", "es menor o igual que"), new com.sun.rave.web.ui.model.Option("== 1", "es verdadero"),
				new com.sun.rave.web.ui.model.Option("== 0", "es falso")});
		ddOperador1DefaultOptions.setSelectedValue("");

		ddUnion1DefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[] {new com.sun.rave.web.ui.model.Option("", ""), new com.sun.rave.web.ui.model.Option("&&", "Y"),
				new com.sun.rave.web.ui.model.Option("||", "O")});
		ddUnion1DefaultOptions.setSelectedValue("");

		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoModificador.EnumTipoModificador.values(), "cap");

		ddTipoModificadorDefaultOptions.setOptions(op);
		ddTipoModificadorDefaultOptions.setSelectedValue("");

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		if(this.getListaDelCommunicationVariablesFormulaModificador() != null) {
			this.getObjectListDataProviderVariableFormula().setList(new ArrayList(this.getListaDelCommunicationVariablesFormulaModificador()));
		}
	}

	private Label lblNombreColumnaReportes = new Label();
	private TextField tfNombreColumnaReportes = new TextField();
	private StaticText stMensajeNombreColumnaReportes = new StaticText();
	private TextField tfNombre = new TextField();
	private Label lblVariablesFormula = new Label();
	private Button btnAgregarVariable = new Button();
	private HtmlAjaxCommandButton btnQuitarVariable = new HtmlAjaxCommandButton();
	private Table tablaVariables = new Table();
	private TableRowGroup trgVariables = new TableRowGroup();
	private ObjectListDataProvider ldpVariablesFormula = new ObjectListDataProvider();
	private RadioButton rbVariables = new RadioButton();
	private TableColumn tcRadioButton = new TableColumn();
	private TableColumn tcNombreVariable = new TableColumn();
	private TableColumn tcExpresion = new TableColumn();
	private TextField tfNombreVariable = new TextField();
	private TextArea taExpresion = new TextArea();

	public Label getLblVariablesFormula() {
		return lblVariablesFormula;
	}

	public void setLblVariablesFormula(Label lblVariablesFormula) {
		this.lblVariablesFormula = lblVariablesFormula;
	}

	public Button getBtnAgregarVariable() {
		return btnAgregarVariable;
	}

	public void setBtnAgregarVariable(Button btnAgregarVariable) {
		this.btnAgregarVariable = btnAgregarVariable;
	}

	public HtmlAjaxCommandButton getBtnQuitarVariable() {
		return btnQuitarVariable;
	}

	public void setBtnQuitarVariable(HtmlAjaxCommandButton btnQuitarVariable) {
		this.btnQuitarVariable = btnQuitarVariable;
	}

	public Table getTablaVariables() {
		return tablaVariables;
	}

	public void setTablaVariables(Table tablaVariables) {
		this.tablaVariables = tablaVariables;
	}

	public TableRowGroup getTrgVariables() {
		return trgVariables;
	}

	public void setTrgVariables(TableRowGroup trgVariables) {
		this.trgVariables = trgVariables;
	}

	public ObjectListDataProvider getLdpVariablesFormula() {
		return ldpVariablesFormula;
	}

	public void setLdpVariablesFormula(ObjectListDataProvider ldpVariablesFormula) {
		this.ldpVariablesFormula = ldpVariablesFormula;
	}

	public RadioButton getRbVariables() {
		return rbVariables;
	}

	public void setRbVariables(RadioButton rbVariables) {
		this.rbVariables = rbVariables;
	}

	public TableColumn getTcRadioButton() {
		return tcRadioButton;
	}

	public void setTcRadioButton(TableColumn tcRadioButton) {
		this.tcRadioButton = tcRadioButton;
	}

	public TableColumn getTcNombreVariable() {
		return tcNombreVariable;
	}

	public void setTcNombreVariable(TableColumn tcNombreVariable) {
		this.tcNombreVariable = tcNombreVariable;
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

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	public Label getLblNombreColumnaReportes() {
		return lblNombreColumnaReportes;
	}

	public void setLblNombreColumnaReportes(Label lblNombreColumnaReportes) {
		this.lblNombreColumnaReportes = lblNombreColumnaReportes;
	}

	public TextField getTfNombreColumnaReportes() {
		return tfNombreColumnaReportes;
	}

	public void setTfNombreColumnaReportes(TextField tfNombreColumnaReportes) {
		this.tfNombreColumnaReportes = tfNombreColumnaReportes;
	}

	public StaticText getStMensajeNombreColumnaReportes() {
		return stMensajeNombreColumnaReportes;
	}

	public void setStMensajeNombreColumnaReportes(StaticText stMensajeNombreColumnaReportes) {
		this.stMensajeNombreColumnaReportes = stMensajeNombreColumnaReportes;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private TextField tfMesesDesde = new TextField();

	public TextField getTfMesesDesde() {
		return tfMesesDesde;
	}

	public void setTfMesesDesde(TextField tf) {
		this.tfMesesDesde = tf;
	}

	private StaticText stValor1 = new StaticText();

	public StaticText getStValor1() {
		return stValor1;
	}

	public void setStValor1(StaticText st) {
		this.stValor1 = st;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private TextField tfMesesHasta = new TextField();

	public TextField getTfMesesHasta() {
		return tfMesesHasta;
	}

	public void setTfMesesHasta(TextField tf) {
		this.tfMesesHasta = tf;
	}

	private StaticText stValor2 = new StaticText();

	public StaticText getStValor2() {
		return stValor2;
	}

	public void setStValor2(StaticText st) {
		this.stValor2 = st;
	}

	private RadioButton rbTasa = new RadioButton();

	public RadioButton getRbTasa() {
		return rbTasa;
	}

	public void setRbTasa(RadioButton rb) {
		this.rbTasa = rb;
	}

	private RadioButton rbSubtotal = new RadioButton();

	public RadioButton getRbSubtotal() {
		return rbSubtotal;
	}

	public void setRbSubtotal(RadioButton rb) {
		this.rbSubtotal = rb;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private Label label8 = new Label();

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label l) {
		this.label8 = l;
	}

	private RadioButton rbFijo = new RadioButton();

	public RadioButton getRbFijo() {
		return rbFijo;
	}

	public void setRbFijo(RadioButton rb) {
		this.rbFijo = rb;
	}

	private RadioButton rbPorcentual = new RadioButton();

	public RadioButton getRbPorcentual() {
		return rbPorcentual;
	}

	public void setRbPorcentual(RadioButton rb) {
		this.rbPorcentual = rb;
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

	private TextField tfDiasDesde = new TextField();

	public TextField getTfDiasDesde() {
		return tfDiasDesde;
	}

	public void setTfDiasDesde(TextField tf) {
		this.tfDiasDesde = tf;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TextField tfDiasHasta = new TextField();

	public TextField getTfDiasHasta() {
		return tfDiasHasta;
	}

	public void setTfDiasHasta(TextField tf) {
		this.tfDiasHasta = tf;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Label label11 = new Label();

	public Label getLabel11() {
		return label11;
	}

	public void setLabel11(Label l) {
		this.label11 = l;
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

	private Label label12 = new Label();

	public Label getLabel12() {
		return label12;
	}

	public void setLabel12(Label l) {
		this.label12 = l;
	}

	private DropDown ddVariable1 = new DropDown();

	public DropDown getDdVariable1() {
		return ddVariable1;
	}

	public void setDdVariable1(DropDown dd) {
		this.ddVariable1 = dd;
	}

	private DropDown ddOperador1 = new DropDown();

	public DropDown getDdOperador1() {
		return ddOperador1;
	}

	public void setDdOperador1(DropDown dd) {
		this.ddOperador1 = dd;
	}

	private TextField tfValor1 = new TextField();

	public TextField getTfValor1() {
		return tfValor1;
	}

	public void setTfValor1(TextField tf) {
		this.tfValor1 = tf;
	}

	private SingleSelectOptionsList ddOperador1DefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdOperador1DefaultOptions() {
		return ddOperador1DefaultOptions;
	}

	public void setDdOperador1DefaultOptions(SingleSelectOptionsList ssol) {
		this.ddOperador1DefaultOptions = ssol;
	}

	private DropDown ddUnion1 = new DropDown();

	public DropDown getDdUnion1() {
		return ddUnion1;
	}

	public void setDdUnion1(DropDown dd) {
		this.ddUnion1 = dd;
	}

	private SingleSelectOptionsList ddTipoModificadorDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoModificadorDefaultOptions() {
		return ddTipoModificadorDefaultOptions;
	}

	public void setDdTipoModificadorDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoModificadorDefaultOptions = ssol;
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

	private DropDown ddOperador2 = new DropDown();

	public DropDown getDdOperador2() {
		return ddOperador2;
	}

	public void setDdOperador2(DropDown dd) {
		this.ddOperador2 = dd;
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

	private TextArea taCondicion = new TextArea();

	public TextArea getTaCondicion() {
		return taCondicion;
	}

	public void setTaCondicion(TextArea ta) {
		this.taCondicion = ta;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private ObjectListDataProvider ldpTipoParametroTipoModificador = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpTipoParametroTipoModificador() {
		return ldpTipoParametroTipoModificador;
	}

	public void setLdpTipoParametroTipoModificador(ObjectListDataProvider oldp) {
		this.ldpTipoParametroTipoModificador = oldp;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Label label13 = new Label();

	public Label getLabel13() {
		return label13;
	}

	public void setLabel13(Label l) {
		this.label13 = l;
	}

	private Label label14 = new Label();

	public Label getLabel14() {
		return label14;
	}

	public void setLabel14(Label l) {
		this.label14 = l;
	}

	private Label label15 = new Label();

	public Label getLabel15() {
		return label15;
	}

	public void setLabel15(Label l) {
		this.label15 = l;
	}

	private Checkbox cbQuitarReliquidarVencida = new Checkbox();

	public Checkbox getCbQuitarReliquidarVencida() {
		return cbQuitarReliquidarVencida;
	}

	public void setCbQuitarReliquidarVencida(Checkbox cbQuitarReliquidarVencida) {
		this.cbQuitarReliquidarVencida = cbQuitarReliquidarVencida;
	}

	private DropDown ddTipoModificador = new DropDown();

	public DropDown getDdTipoModificador() {
		return ddTipoModificador;
	}

	public void setDdTipoModificador(DropDown dd) {
		this.ddTipoModificador = dd;
	}

	private ObjectListDataProvider getObjectListDataProviderVariableFormula() {
		return this.getLdpVariablesFormula();
	}

	public List<VariableFormula> getListaDelCommunicationVariablesFormulaModificador() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaVariablesFormulaModificador();
	}

	private void setListaDelCommunicationVariablesFormula(List<VariableFormula> lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaVariablesFormulaModificador(lista);
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) rbVariables.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public String getCurrentRow() {
		return trgVariables.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup1");
			rk = this.getObjectListDataProviderVariableFormula().getRowKey(aRowId);
		} catch(Exception ex) {
		}

		return rk;
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMTipoModificador() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new TipoModificador());
		ep.getObjetos().add(ind++, new ArrayList()); // lista de parametros
		ep.getObjetos().add(ind++, new ArrayList());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		TipoModificador tipoModificador = this.obtenerObjetoDelElementoPila(ind++, TipoModificador.class);
		ArrayList parametros = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List<VariableFormula> listaVariables = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		Object fijo = this.getRbFijo().getValue();
		Object sobreTasa = this.getRbTasa().getValue();

		tipoModificador.setNombre(getTextFieldValue(this.getTfNombre()));
		if(fijo != null && fijo != "")
			tipoModificador.setFijo(((Boolean) fijo).booleanValue());
		if(sobreTasa != null && sobreTasa != "")
			tipoModificador.setSobreSaldoNeto(((Boolean) sobreTasa).booleanValue());
		tipoModificador.setDesdeMeses(Conversor.getIntegerDeString(getTextFieldValue(this.getTfMesesDesde())));
		tipoModificador.setHastaMeses(Conversor.getIntegerDeString(getTextFieldValue(this.getTfMesesHasta())));
		tipoModificador.setDesdeDias(Conversor.getIntegerDeString(getTextFieldValue(this.getTfDiasDesde())));
		tipoModificador.setHastaDias(Conversor.getIntegerDeString(getTextFieldValue(this.getTfDiasHasta())));
		tipoModificador.setCondicion(getTextAreaValue(this.getTaCondicion()));
		tipoModificador.setQuitarReliquidarVencida(this.getCbQuitarReliquidarVencida().isChecked());
		tipoModificador.setNombreColumnaReportes(getTextFieldValue(this.getTfNombreColumnaReportes()));

		this.getObjectListDataProvider().commitChanges();
		parametros = (ArrayList) this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(parametros);

		this.getObjectListDataProviderVariableFormula().commitChanges();
		listaVariables = this.getObjectListDataProviderVariableFormula().getList();

		tipoModificador.setListaVariables(listaVariables);
		this.setListaDelCommunicationVariablesFormula(this.getObjectListDataProviderVariableFormula().getList());

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, tipoModificador);
		this.getElementoPila().getObjetos().set(ind++, parametros);
		this.getElementoPila().getObjetos().set(ind++, listaVariables);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		TipoModificador tipoModificador = null;
		ArrayList parametros = null;
		List<VariableFormula> listaVariables = null;

		tipoModificador = this.obtenerObjetoDelElementoPila(ind++, TipoModificador.class);
		parametros = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		listaVariables = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		// variables/parametros
		this.getObjectListDataProvider().setList(parametros);
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider().getObjects(), "cap");
		lbVariablesDefaultOptions.setOptions(op);

		this.getTfNombre().setText(tipoModificador.getNombre());
		this.getRbFijo().setValue(new Boolean(tipoModificador.isFijo()));
		this.getRbPorcentual().setValue(new Boolean(!tipoModificador.isFijo()));
		this.getRbTasa().setValue(new Boolean(tipoModificador.isSobreSaldoNeto()));
		this.getRbSubtotal().setValue(new Boolean(!tipoModificador.isSobreSaldoNeto()));
		this.getCbQuitarReliquidarVencida().setValue(tipoModificador.getQuitarReliquidarVencida());
		if(tipoModificador.getDesdeMeses() == null)
			tipoModificador.setDesdeMeses(new Integer(0));
		this.getTfMesesDesde().setText(tipoModificador.getDesdeMeses().toString());
		if(tipoModificador.getHastaMeses() == null)
			tipoModificador.setHastaMeses(new Integer(0));
		this.getTfMesesHasta().setText(tipoModificador.getHastaMeses().toString());
		if(tipoModificador.getDesdeDias() == null)
			tipoModificador.setDesdeDias(new Integer(0));
		this.getTfDiasDesde().setText(tipoModificador.getDesdeDias().toString());
		if(tipoModificador.getHastaDias() == null)
			tipoModificador.setHastaDias(new Integer(0));
		this.getTfDiasHasta().setText(tipoModificador.getHastaDias().toString());
		// this.getCbQuitarReliquidarVencida().setValue(tipoModificador.getQuitarReliquidarVencida());
		this.getTaCondicion().setText(tipoModificador.getCondicion());
		this.getTfNombreColumnaReportes().setText(tipoModificador.getNombreColumnaReportes());

		this.setListaDelCommunication(parametros);
		this.getObjectListDataProvider().setList(parametros);

		this.setListaDelCommunicationVariablesFormula(listaVariables);
		this.getObjectListDataProviderVariableFormula().setList(new ArrayList(listaVariables));
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpTipoParametroTipoModificador();
	}

	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaTiposParametroTipoModificador();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaTiposParametroTipoModificador(lista);
	}

	// </editor-fold>

	@Override
	public void limpiarObjeto(int posicion, Class tipoClase, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, tipoClase.newInstance());
			if(campo != null)
				campo.setText("");
		} catch(Exception ex) {
		}
	}

	private void cargarValoresPorDefecto() {
		// CAMBIAR: Obtener los valores por defecto.
		TipoModificador tipoModificador = this.obtenerObjetoDelElementoPila(0, TipoModificador.class);
		Integer def = new Integer(0);
		tipoModificador.setDesdeDias(def);
		tipoModificador.setDesdeMeses(def);
		tipoModificador.setHastaDias(def);
		tipoModificador.setHastaMeses(def);
		this.getElementoPila().getObjetos().set(0, tipoModificador);
		return;
	}

	public void btnAgregarVariable_action() {
		this.getObjectListDataProviderVariableFormula().commitChanges();
		VariableFormulaSimple v = new VariableFormulaSimple();
		v.setNombre("");
		v.setExpresion("");

		this.getListaDelCommunicationVariablesFormulaModificador().add(v);
		this.getObjectListDataProviderVariableFormula().getList().add(v);
		guardarEstadoObjetosUsados();
		this.mostrarEstadoObjetosUsados();
	}

	public String btnQuitarVariable_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			this.getObjectListDataProviderVariableFormula().commitChanges();
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderVariableFormula().getObjects()[index];
					this.getListaDelCommunicationVariablesFormulaModificador().remove(obj);
					this.getObjectListDataProviderVariableFormula().getList().remove(obj);

				}
			} catch(Exception ex) {
			}
			this.guardarEstadoObjetosUsados();
			this.mostrarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoModificador";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		this.getElementoPila().getObjetos().set(1, pObject);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoModificador tipoModificador = null;

		if(pObject != null && pObject instanceof TipoModificador) {
			tipoModificador = (TipoModificador) pObject;
			this.getElementoPila().getObjetos().set(0, tipoModificador);
			this.getElementoPila().getObjetos().set(2, tipoModificador.getListaVariables());
		}
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$ABMTipoModificador$ABMTipoModificador}";
	}

	@Override
	public long getSerialVersionUID() {
		return TipoModificador.serialVersionUID;
	}
}