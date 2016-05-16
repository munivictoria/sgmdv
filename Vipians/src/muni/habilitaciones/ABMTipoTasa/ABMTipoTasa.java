/*
 * ModificarTipoTasa.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.habilitaciones.ABMTipoTasa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;

import muni.habilitaciones.ABMConceptoPorMora.ConceptoPorMoraModel;
import muni.habilitaciones.ABMTipoModificador.TipoModificadorModel;
import muni.habilitaciones.ABMTipoVencimiento.TipoVencimientoModel;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
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
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlan;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaSimple;
import com.trascender.habilitaciones.recurso.persistent.enums.TipoParametroInteres;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMTipoTasa extends ABMPageBean {

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

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if(this.getListaDelCommunication2() != null) {
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
		}
		if(this.getListaDelCommunication3() != null) {
			this.getObjectListDataProvider3().setList(this.getListaDelCommunication3());
		}
		if(this.getListaDelCommunication7() != null) {
			this.getObjectListDataProvider7().setList(this.getListaDelCommunication7());
		}
		if(this.getListaDelCommunication5() != null) {
			this.getObjectListDataProvider5().setList(this.getListaDelCommunication5());
		}
		if(this.getListaDelCommunication6() != null) {
			this.getObjectListDataProvider6().setList(this.getListaDelCommunication6());
		}
		if(this.getListaDelCommunicationVariablesFormula() != null) {
			this.getObjectListDataProviderVariableFormula().setList(new ArrayList(this.getListaDelCommunicationVariablesFormula()));
		}

		// op = null;
		// op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoParametro.TIPOS_PARAMETRO.values(),"cap");
		// ddTiposParametroDefaultOptions.setOptions(op);

		// op = null;
		// op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(this.getObjectListDataProvider().getObjects(),"cap");
		// lbVariablesDefaultOptions.setOptions(op);
		
		armarDropDownPlan();
	}
	
    private void armarDropDownPlan(){
        if (this.getCommunicationHabilitacionesBean().getMapaPlan() != null 
                && !this.getCommunicationHabilitacionesBean().getMapaPlan().isEmpty()){
            Option[] op2 = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(this.getCommunicationHabilitacionesBean().getMapaPlan().keySet().toArray(), "");
          ddPlanDefaultOptions.setOptions(op2);
        } else {
        	ddPlanDefaultOptions.setOptions(new Option[]{});
        }
    }
    
    private Label lblPlan = new Label();
    private DropDown ddPlanes = new DropDown();
    private SingleSelectOptionsList ddPlanDefaultOptions = new SingleSelectOptionsList();
    
	public Label getLblPlan() {
		return lblPlan;
	}

	public void setLblPlan(Label lblPlan) {
		this.lblPlan = lblPlan;
	}

	public DropDown getDdPlanes() {
		return ddPlanes;
	}

	public void setDdPlanes(DropDown ddPlanes) {
		this.ddPlanes = ddPlanes;
	}

	public SingleSelectOptionsList getDdPlanDefaultOptions() {
		return ddPlanDefaultOptions;
	}

	public void setDdPlanDefaultOptions(SingleSelectOptionsList ddPlanDefaultOptions) {
		this.ddPlanDefaultOptions = ddPlanDefaultOptions;
	}


	private Label lblCondicionNoLiquidacion = new Label();
	private TextArea taCondicionNoLiquidacion = new TextArea();
	
	public Label getLblCondicionNoLiquidacion() {
		return lblCondicionNoLiquidacion;
	}

	public void setLblCondicionNoLiquidacion(Label lblCondicionNoLiquidacion) {
		this.lblCondicionNoLiquidacion = lblCondicionNoLiquidacion;
	}

	public TextArea getTaCondicionNoLiquidacion() {
		return taCondicionNoLiquidacion;
	}

	public void setTaCondicionNoLiquidacion(TextArea taCondicionNoLiquidacion) {
		this.taCondicionNoLiquidacion = taCondicionNoLiquidacion;
	}

	private Button btnModificarVencimiento = new Button();

	public Button getBtnModificarVencimiento() {
		return btnModificarVencimiento;
	}

	public void setBtnModificarVencimiento(Button btnModificarVencimiento) {
		this.btnModificarVencimiento = btnModificarVencimiento;
	}

	private boolean noNavega;

	private Label lblVariablesFormula = new Label();

	public Label getLblVariablesFormula() {
		return lblVariablesFormula;
	}

	public void setLblVariablesFormula(Label lblVariablesFormula) {
		this.lblVariablesFormula = lblVariablesFormula;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfValor1) {
		this.tfNombre = tfValor1;
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

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
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

	private Checkbox cbFija = new Checkbox();

	public Checkbox getCbFija() {
		return cbFija;
	}

	public void setCbFija(Checkbox c) {
		this.cbFija = c;
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

	private Label label10 = new Label();

	public Label getLabel10() {
		return label10;
	}

	public void setLabel10(Label l) {
		this.label10 = l;
	}

	private Table tblRecargo = new Table();

	public Table getTblRecargo() {
		return tblRecargo;
	}

	public void setTblRecargo(Table tblRecargo) {
		this.tblRecargo = tblRecargo;
	}

	private Table tblInteres = new Table();

	public Table getTblInteres() {
		return tblInteres;
	}

	public void setTblInteres(Table t) {
		this.tblInteres = t;
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

	private TableRowGroup tableRowGroup4 = new TableRowGroup();

	public TableRowGroup getTableRowGroup4() {
		return tableRowGroup4;
	}

	public void setTableRowGroup4(TableRowGroup tableRowGroup4) {
		this.tableRowGroup4 = tableRowGroup4;
	}

	private PanelGroup groupPanel7 = new PanelGroup();

	public PanelGroup getGroupPanel7() {
		return groupPanel7;
	}

	public void setGroupPanel7(PanelGroup groupPanel7) {
		this.groupPanel7 = groupPanel7;
	}

	private PanelGroup groupPanel0 = new PanelGroup();

	public PanelGroup getGroupPanel0() {
		return groupPanel0;
	}

	public void setGroupPanel0(PanelGroup groupPanel0) {
		this.groupPanel0 = groupPanel0;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Button btnAgregarModificador = new Button();

	public Button getBtnAgregarModificador() {
		return btnAgregarModificador;
	}

	public void setBtnAgregarModificador(Button b) {
		this.btnAgregarModificador = b;
	}

	private Button btnAgregarInteres = new Button();

	public Button getBtnAgregarInteres() {
		return btnAgregarInteres;
	}

	public void setBtnAgregarInteres(Button b) {
		this.btnAgregarInteres = b;
	}

	protected HtmlAjaxCommandButton btnQuitarModificador = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarModificador() {
		return btnQuitarModificador;
	}

	public void setBtnQuitarModificador(HtmlAjaxCommandButton btnQuitarModificador) {
		this.btnQuitarModificador = btnQuitarModificador;
	}

	protected HtmlAjaxCommandButton btnQuitarInteres = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarInteres() {
		return btnQuitarInteres;
	}

	public void setBtnQuitarInteres(HtmlAjaxCommandButton btnQuitarInteres) {
		this.btnQuitarInteres = btnQuitarInteres;
	}

	private ObjectListDataProvider ldpInteres = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpInteres() {
		return ldpInteres;
	}

	public void setLdpInteres(ObjectListDataProvider oldp) {
		this.ldpInteres = oldp;
	}

	private ObjectListDataProvider ldpModificador = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpModificador() {
		return ldpModificador;
	}

	public void setLdpModificador(ObjectListDataProvider oldp) {
		this.ldpModificador = oldp;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private RadioButton radioButton7 = new RadioButton();

	public RadioButton getRadioButton7() {
		return radioButton7;
	}

	public void setRadioButton7(RadioButton radioButton7) {
		this.radioButton7 = radioButton7;
	}

	private RadioButton radioButton3 = new RadioButton();

	public RadioButton getRadioButton3() {
		return radioButton3;
	}

	public void setRadioButton3(RadioButton rb) {
		this.radioButton3 = rb;
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

	private TableColumn tableColumn7 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tc) {
		this.tableColumn7 = tc;
	}

	private ObjectListDataProvider ldpTipoParametro = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpTipoParametro() {
		return ldpTipoParametro;
	}

	public void setLdpTipoParametro(ObjectListDataProvider oldp) {
		this.ldpTipoParametro = oldp;
	}

	private Label label8 = new Label();

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label l) {
		this.label8 = l;
	}

	private TextField tfTipoObligacion = new TextField();

	public TextField getTfTipoObligacion() {
		return tfTipoObligacion;
	}

	public void setTfTipoObligacion(TextField tfValor1) {
		this.tfTipoObligacion = tfValor1;
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

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private Button btnQuitarParametro = new Button();

	public Button getBtnQuitarParametro() {
		return btnQuitarParametro;
	}

	public void setBtnQuitarParametro(Button b) {
		this.btnQuitarParametro = b;
	}

	private TextField tfValor1 = new TextField();

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

	private Label label9 = new Label();

	public Label getLabel9() {
		return label9;
	}

	public void setLabel9(Label l) {
		this.label9 = l;
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

	private TextArea textArea1 = new TextArea();

	public TextArea getTextArea1() {
		return textArea1;
	}

	public void setTextArea1(TextArea ta) {
		this.textArea1 = ta;
	}

	private TextArea textArea4 = new TextArea();

	public TextArea getTextArea4() {
		return textArea4;
	}

	public void setTextArea4(TextArea textArea4) {
		this.textArea4 = textArea4;
	}

	private TextArea textArea3 = new TextArea();

	public TextArea getTextArea3() {
		return textArea3;
	}

	public void setTextArea3(TextArea ta) {
		this.textArea3 = ta;
	}

	private Label label11 = new Label();

	public Label getLabel11() {
		return label11;
	}

	public void setLabel11(Label l) {
		this.label11 = l;
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

	private PanelGroup groupPanel2 = new PanelGroup();

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup pg) {
		this.groupPanel2 = pg;
	}

	private PanelGroup groupPanel4 = new PanelGroup();

	public PanelGroup getGroupPanel4() {
		return groupPanel4;
	}

	public void setGroupPanel4(PanelGroup pg) {
		this.groupPanel4 = pg;
	}

	private PanelGroup groupPanel5 = new PanelGroup();

	public PanelGroup getGroupPanel5() {
		return groupPanel5;
	}

	public void setGroupPanel5(PanelGroup groupPanel5) {
		this.groupPanel5 = groupPanel5;
	}

	private Button btnAgregarRecargo = new Button();

	public Button getBtnAgregarRecargo() {
		return btnAgregarRecargo;
	}

	public void setBtnAgregarRecargo(Button btnAgregarRecargo) {
		this.btnAgregarRecargo = btnAgregarRecargo;
	}

	public HtmlAjaxCommandButton getBtnQuitarRecargo() {
		return btnQuitarRecargo;
	}

	public void setBtnQuitarRecargo(HtmlAjaxCommandButton btnQuitarRecargo) {
		this.btnQuitarRecargo = btnQuitarRecargo;
	}

	protected HtmlAjaxCommandButton btnQuitarRecargo = new HtmlAjaxCommandButton();
	private Button btnQuitarFormulaAlicuota = new Button();
	private Button btnAgregarVencimiento = new Button();
	private Button btnAgregarFormulaAlicuota = new Button();
	private Button btnModificarFormulaAlicuota = new Button();

	public Button getBtnModificarFormulaAlicuota() {
		return btnModificarFormulaAlicuota;
	}

	public void setBtnModificarFormulaAlicuota(Button btnModificarFormulaAlicuota) {
		this.btnModificarFormulaAlicuota = btnModificarFormulaAlicuota;
	}

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

	public Button getBtnAgregarVencimiento() {
		return btnAgregarVencimiento;
	}

	public void setBtnAgregarVencimiento(Button b) {
		this.btnAgregarVencimiento = b;
	}

	protected HtmlAjaxCommandButton btnQuitarVencimiento = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarVencimiento() {
		return btnQuitarVencimiento;
	}

	public void setBtnQuitarVencimiento(HtmlAjaxCommandButton btnQuitarVencimiento) {
		this.btnQuitarVencimiento = btnQuitarVencimiento;
	}

	private ObjectListDataProvider ldpVencimiento = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpVencimiento() {
		return ldpVencimiento;
	}

	public void setLdpVencimiento(ObjectListDataProvider oldp) {
		this.ldpVencimiento = oldp;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private RadioButton radioButton2 = new RadioButton();

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton rb) {
		this.radioButton2 = rb;
	}

	private RadioButton radioButton5 = new RadioButton();

	public RadioButton getRadioButton5() {
		return radioButton5;
	}

	public void setRadioButton5(RadioButton rb) {
		this.radioButton5 = rb;
	}

	private RadioButton radioButton6 = new RadioButton();

	public RadioButton getRadioButton6() {
		return radioButton6;
	}

	public void setRadioButton6(RadioButton rb) {
		this.radioButton6 = rb;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	private TextArea textArea2 = new TextArea();

	public TextArea getTextArea2() {
		return textArea2;
	}

	public void setTextArea2(TextArea ta) {
		this.textArea2 = ta;
	}

	private TableColumn tableColumn10 = new TableColumn();

	public TableColumn getTableColumn10() {
		return tableColumn10;
	}

	public void setTableColumn10(TableColumn tc) {
		this.tableColumn10 = tc;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private TableColumn tableColumn11 = new TableColumn();

	public TableColumn getTableColumn11() {
		return tableColumn11;
	}

	public void setTableColumn11(TableColumn tc) {
		this.tableColumn11 = tc;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private Label label12 = new Label();

	public Label getLabel12() {
		return label12;
	}

	public void setLabel12(Label l) {
		this.label12 = l;
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

	private Table table3 = new Table();

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table t) {
		this.table3 = t;
	}

	private TableRowGroup tableRowGroup5 = new TableRowGroup();

	public TableRowGroup getTableRowGroup5() {
		return tableRowGroup5;
	}

	public void setTableRowGroup5(TableRowGroup tableRowGroup5) {
		this.tableRowGroup5 = tableRowGroup5;
	}

	private TableRowGroup tableRowGroup6 = new TableRowGroup();

	public TableRowGroup getTableRowGroup6() {
		return tableRowGroup6;
	}

	public void setTableRowGroup6(TableRowGroup tableRowGroup6) {
		this.tableRowGroup6 = tableRowGroup6;
	}

	private TableRowGroup tableRowGroup7 = new TableRowGroup();

	public TableRowGroup getTableRowGroup7() {
		return tableRowGroup7;
	}

	public void setTableRowGroup7(TableRowGroup trg) {
		this.tableRowGroup7 = trg;
	}

	private TableRowGroup tableRowGroup3 = new TableRowGroup();

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup trg) {
		this.tableRowGroup3 = trg;
	}

	private PanelGroup groupPanel3 = new PanelGroup();

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup pg) {
		this.groupPanel3 = pg;
	}

	private Button btnProbarFormula = new Button();

	public Button getBtnProbarFormula() {
		return btnProbarFormula;
	}

	public void setBtnProbarFormula(Button b) {
		this.btnProbarFormula = b;
	}

	private ObjectListDataProvider ldpRecargo = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRecargo() {
		return ldpRecargo;
	}

	public void setLdpRecargo(ObjectListDataProvider ldpRecargo) {
		this.ldpRecargo = ldpRecargo;
	}

	private ObjectListDataProvider ldpParametroValor = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpParametroValor() {
		return ldpParametroValor;
	}

	public void setLdpParametroValor(ObjectListDataProvider oldp) {
		this.ldpParametroValor = oldp;
	}

	private PanelGroup gdpResultado = new PanelGroup();

	public PanelGroup getGdpResultado() {
		return gdpResultado;
	}

	public void setGdpResultado(PanelGroup gdpResultado) {
		this.gdpResultado = gdpResultado;
	}

	private StaticText stResultado = new StaticText();

	public StaticText getStResultado() {
		return stResultado;
	}

	public void setStResultado(StaticText st) {
		this.stResultado = st;
	}

	private Label label16 = new Label();

	public Label getLabel16() {
		return label16;
	}

	public void setLabel16(Label l) {
		this.label16 = l;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	private TextField tfFechaLiquidacion = new TextField();

	public TextField getTfFechaLiquidacion() {
		return tfFechaLiquidacion;
	}

	public void setTfFechaLiquidacion(TextField tf) {
		this.tfFechaLiquidacion = tf;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText st) {
		this.staticText12 = st;
	}

	private TableColumn tableColumn12 = new TableColumn();

	public TableColumn getTableColumn12() {
		return tableColumn12;
	}

	public void setTableColumn12(TableColumn tc) {
		this.tableColumn12 = tc;
	}

	private StaticText stParametroPrueba = new StaticText();

	public StaticText getStParametroPrueba() {
		return stParametroPrueba;
	}

	public void setStParametroPrueba(StaticText st) {
		this.stParametroPrueba = st;
	}

	private TableColumn tableColumn13 = new TableColumn();

	public TableColumn getTableColumn13() {
		return tableColumn13;
	}

	public void setTableColumn13(TableColumn tc) {
		this.tableColumn13 = tc;
	}

	private TextField tfValorPrueba = new TextField();

	public TextField getTfValorPrueba() {
		return tfValorPrueba;
	}

	public void setTfValorPrueba(TextField tf) {
		this.tfValorPrueba = tf;
	}

	private Label label17 = new Label();

	public Label getLabel17() {
		return label17;
	}

	public void setLabel17(Label l) {
		this.label17 = l;
	}

	private Label label18 = new Label();

	public Label getLabel18() {
		return label18;
	}

	public void setLabel18(Label l) {
		this.label18 = l;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private TextField tfFechaCobro = new TextField();

	public TextField getTfFechaCobro() {
		return tfFechaCobro;
	}

	public void setTfFechaCobro(TextField tf) {
		this.tfFechaCobro = tf;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private TableColumn tableColumn14 = new TableColumn();

	public TableColumn getTableColumn14() {
		return tableColumn14;
	}

	public void setTableColumn14(TableColumn tc) {
		this.tableColumn14 = tc;
	}

	private StaticText staticText10 = new StaticText();

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

	private StaticText staticText13 = new StaticText();

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText st) {
		this.staticText13 = st;
	}

	private TableColumn tableColumn16 = new TableColumn();

	public TableColumn getTableColumn16() {
		return tableColumn16;
	}

	public void setTableColumn16(TableColumn tc) {
		this.tableColumn16 = tc;
	}

	private StaticText staticText14 = new StaticText();

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText st) {
		this.staticText14 = st;
	}

	private TableColumn tableColumn17 = new TableColumn();

	public TableColumn getTableColumn17() {
		return tableColumn17;
	}

	public void setTableColumn17(TableColumn tc) {
		this.tableColumn17 = tc;
	}

	private TableColumn tableColumn18 = new TableColumn();

	public TableColumn getTableColumn18() {
		return tableColumn18;
	}

	public void setTableColumn18(TableColumn tc) {
		this.tableColumn18 = tc;
	}

	private TableColumn tableColumn19 = new TableColumn();

	public TableColumn getTableColumn19() {
		return tableColumn19;
	}

	public void setTableColumn19(TableColumn tc) {
		this.tableColumn19 = tc;
	}

	private TableColumn tableColumn23 = new TableColumn();

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

	private TableColumn tableColumn22 = new TableColumn();
	private TableColumn tableColumn21 = new TableColumn();

	public TableColumn getTableColumn21() {
		return tableColumn21;
	}

	public void setTableColumn21(TableColumn tableColumn21) {
		this.tableColumn21 = tableColumn21;
	}

	private TableColumn tableColumn20 = new TableColumn();

	public TableColumn getTableColumn20() {
		return tableColumn20;
	}

	public void setTableColumn20(TableColumn tc) {
		this.tableColumn20 = tc;
	}

	private StaticText staticText15 = new StaticText();

	public StaticText getStaticText15() {
		return staticText15;
	}

	public void setStaticText15(StaticText st) {
		this.staticText15 = st;
	}

	private Label label19 = new Label();

	public Label getLabel19() {
		return label19;
	}

	public void setLabel19(Label l) {
		this.label19 = l;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText16 = new StaticText();

	public StaticText getStaticText16() {
		return staticText16;
	}

	public void setStaticText16(StaticText st) {
		this.staticText16 = st;
	}

	private TextField tfInteres = new TextField();

	public TextField getTfInteres() {
		return tfInteres;
	}

	public void setTfInteres(TextField tf) {
		this.tfInteres = tf;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private StaticText staticText18 = new StaticText();

	public StaticText getStaticText18() {
		return staticText18;
	}

	public void setStaticText18(StaticText st) {
		this.staticText18 = st;
	}

	private StaticText staticText20 = new StaticText();

	public StaticText getStaticText20() {
		return staticText20;
	}

	public void setStaticText20(StaticText staticText20) {
		this.staticText20 = staticText20;
	}

	private StaticText staticText19 = new StaticText();

	public StaticText getStaticText19() {
		return staticText19;
	}

	public void setStaticText19(StaticText st) {
		this.staticText19 = st;
	}

	private TextField tfRecargo = new TextField();

	public TextField getTfRecargo() {
		return tfRecargo;
	}

	public void setTfRecargo(TextField tf) {
		this.tfRecargo = tf;
	}

	private Label label20 = new Label();
	private Label label21 = new Label();

	public Label getLabel21() {
		return label21;
	}

	public void setLabel21(Label label21) {
		this.label21 = label21;
	}

	public Label getLabel20() {
		return label20;
	}

	public void setLabel20(Label l) {
		this.label20 = l;
	}

	private Table table4 = new Table();

	public Table getTable4() {
		return table4;
	}

	public void setTable4(Table table4) {
		this.table4 = table4;
	}

	private ObjectListDataProvider ldpVariablesFormula = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpVariablesFormula() {
		return ldpVariablesFormula;
	}

	public void setLdpVariablesFormula(ObjectListDataProvider ldpVariablesFormula) {
		this.ldpVariablesFormula = ldpVariablesFormula;
	}

	private ObjectListDataProvider getObjectListDataProviderVariableFormula() {
		return this.getLdpVariablesFormula();
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
		if(selected != null) {
			lastSelected4 = selected;
		}
	}

	public String getCurrentRow4() {
		return tableRowGroup4.getRowKey().getRowId();
	}

	public void setCurrentRow4(int row) {
	}

	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcExpresion = new TableColumn();
	private TextField tfNombreVariable = new TextField();
	private TextArea taExpresion = new TextArea();
	private PanelGroup groupPanel6 = new PanelGroup();
	private Button btnAgregarVariable = new Button();
	protected HtmlAjaxCommandButton btnQuitarVariable = new HtmlAjaxCommandButton();

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

	private Button btnModificarInteres = new Button();

	public Button getBtnModificarInteres() {
		return btnModificarInteres;
	}

	public void setBtnModificarInteres(Button btnModificarInteres) {
		this.btnModificarInteres = btnModificarInteres;
	}

	private Button btnModificarRecargo = new Button();

	public Button getBtnModificarRecargo() {
		return btnModificarRecargo;
	}

	public void setBtnModificarRecargo(Button btnModificarRecargo) {
		this.btnModificarRecargo = btnModificarRecargo;
	}

	private Button btnModificarModificador = new Button();

	public Button getBtnModificarModificador() {
		return btnModificarModificador;
	}

	public void setBtnModificarModificador(Button btnModificarModificador) {
		this.btnModificarModificador = btnModificarModificador;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMTipoTasa() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new TipoTasa());
		ep.getObjetos().add(ind++, new ArrayList()); // lista de tipos de parametro (variables)
		ep.getObjetos().add(ind++, new ArrayList()); // lista de modificadores//
		ep.getObjetos().add(ind++, new ArrayList()); // lista de vencimientos//
		ep.getObjetos().add(ind++, new ArrayList()); // lista de parametros-valor
		ep.getObjetos().add(ind++, new HashMap()); // lista de parametros-valor (en un map)

		ep.getObjetos().add(ind++, null); // fecha liquidacion
		ep.getObjetos().add(ind++, null); // fecha cobro
		ep.getObjetos().add(ind++, null); // TipoParametro del dropDown
		ep.getObjetos().add(ind++, new ArrayList()); // Lista de Intereses//
		ep.getObjetos().add(ind++, new ArrayList()); // listado de recargo//

		ep.getObjetos().add(ind++, new ArrayList()); // componentes para composicion de formula //11
		ep.getObjetos().add(ind++, null); // formula alicuota //12
		ep.getObjetos().add(ind++, new ArrayList<VariableFormulaSimple>()); // lista de VariableFormula 13
		ep.getObjetos().add(ind++, new TipoObligacion()); // 14 Tipo Obligacion

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(ind++, TipoTasa.class);
		ArrayList variables = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList modificadores = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList vencimientos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList parametrosValor = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		HashMap parametrosValorMap = new HashMap();
		ind++; // caso especial
		Date fechaLiquidacion = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		Date fechaCobro = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		// TipoParametro.TIPOS_PARAMETRO tipoParametro = this.obtenerObjetoDelElementoPila(ind++, TipoParametro.TIPOS_PARAMETRO.class);

		ArrayList listaDatos = this.obtenerObjetoDelElementoPila(11, ArrayList.class);
		String formulaAlicuota = this.obtenerObjetoDelElementoPila(12, String.class);
		List<VariableFormulaSimple> variablesFormula = this.obtenerObjetoDelElementoPila(13, ArrayList.class);
		listaDatos = new ArrayList();

		TipoObligacion tipoObligacion = this.obtenerObjetoDelElementoPila(14, TipoObligacion.class);
		
		Plan plan = getDDObjectValue(this.ddPlanes, this.getCommunicationHabilitacionesBean().getMapaPlan());
		tipoTasa.setPlan(plan);
		
		Object fija = this.getCbFija().getValue();
		// Object interes = this.getTfInteres().getText();
		ConceptoPorMora conceptoRecargo = new ConceptoPorMora();

		tipoTasa.setNombre(getTextFieldValue(this.getTfNombre()));
		String tipoParametro = (String) this.getDdTiposParametro().getSelected();
		tipoTasa.setFormula(getTextAreaValue(this.getTaFormula()));
		tipoTasa.setFormulaRegAlicuota(getTextAreaValue(this.getTaFormulaAlicuota()));
		fechaLiquidacion = Conversor.getFechaCortaDeString(getTextFieldValue(this.getTfFechaLiquidacion()));
		fechaCobro = Conversor.getFechaCortaDeString(getTextFieldValue(this.getTfFechaCobro()));
		tipoTasa.setCondicionNoLiquidacion(getTextAreaValue(this.getTaCondicionNoLiquidacion()));

		if(fija != null && fija != "") {
			tipoTasa.setFija(((Boolean) fija).booleanValue());
		} else {
			tipoTasa.setFija(false);
		}

		// Marcos: MODIFICAR--------------------------------------------------------------------------------
		/*
		 * if (interes != null && interes != "") { tipoTasa.setInteres(Conversor.getFloatDeString(interes.toString())); } else { tipoTasa.setInteres(null); } if
		 * (recargo != null && recargo != "") { tipoTasa.setRecargo(Conversor.getFloatDeString(recargo.toString())); } else { tipoTasa.setRecargo(null); }
		 */

		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		this.getObjectListDataProvider().commitChanges();
		variables = (ArrayList) this.getObjectListDataProvider().getList();
		if(variables != null && variables.isEmpty()) {
			variables = null;
		}
		if(tipoTasa.getListaParametros() != null) {
			tipoTasa.getListaParametros().clear();
			if(variables != null) {
				tipoTasa.getListaParametros().addAll(variables);
			}
		}
		this.setListaDelCommunication(variables);

		this.getObjectListDataProvider2().commitChanges();
		modificadores = (ArrayList) this.getObjectListDataProvider2().getList();
		if(modificadores.isEmpty()) {
			modificadores = null;
		}
		if(tipoTasa.getListaModificadores() != null) {
			tipoTasa.getListaModificadores().clear();
			if(modificadores != null) {
				tipoTasa.getListaModificadores().addAll(modificadores);
			}
		}
		this.setListaDelCommunication2(modificadores);

		this.getObjectListDataProvider3().commitChanges();
		vencimientos = (ArrayList) this.getObjectListDataProvider3().getList();
		if(vencimientos.isEmpty()) {
			vencimientos = null;
		}
		if(tipoTasa.getListaVencimientos() != null) {
			tipoTasa.getListaVencimientos().clear();
			if(vencimientos != null) {
				tipoTasa.getListaVencimientos().addAll(vencimientos);
			}
		}
		this.setListaDelCommunication3(vencimientos);

		// this.getObjectListDataProvider7().setList(this.getListaDelCommunication7());
		this.getObjectListDataProvider7().commitChanges();

		parametrosValor = (ArrayList) this.getObjectListDataProvider7().getList();
		if(parametrosValor.isEmpty()) {
			parametrosValor = null;
		} else {
			for(int i = 0; i < parametrosValor.size(); i++) {
				ParametroValor pv = (ParametroValor) parametrosValor.get(i);
				String valor = pv.getValor().toString();

				if(!valor.startsWith("\"") && (!valor.endsWith("\""))) {
					try {
						parametrosValorMap.put(pv.getParametro(), Double.valueOf(valor));
					} catch(NumberFormatException e) {
						e.printStackTrace();
					}
				} else {
					valor = valor.trim().substring(1, valor.trim().length() - 1);
					parametrosValorMap.put(pv.getParametro(), valor);
				}
			}
		}
		this.setListaDelCommunication7(parametrosValor);

		this.getObjectListDataProviderVariableFormula().commitChanges();

		variablesFormula = this.getObjectListDataProviderVariableFormula().getList();

		boolean hayRepetidos = false;
		boolean hayVacios = false;

		for(VariableFormulaSimple indice : variablesFormula) {
			int i = 0;

			for(VariableFormulaSimple cadaVar : variablesFormula) {
				if(indice.getNombre().equals(cadaVar.getNombre())) {
					i++;
				}
			}

			if(i > 1 && !hayRepetidos) {
				warn("Existen variables repetidas");
				hayRepetidos = true;
			}
			if((indice.getNombre().equals("") || indice.getExpresion().equals("")) && !hayVacios) {
				warn("Existen variables con campos vacíos");
				hayVacios = true;
			}

			if(hayVacios && hayRepetidos) {
				break;
			}
		}

		if(hayRepetidos || hayVacios) {
			this.noNavega = true;
		}

		tipoTasa.setListaVariablesSimple(variablesFormula);

		this.setListaDelCommunicationVariablesFormula(this.getObjectListDataProviderVariableFormula().getList());
		/*
		 * this.getObjectListDataProvider5().commitChanges(); intereses = (ArrayList) this.getObjectListDataProvider5().getList(); if (intereses.isEmpty()) {
		 * intereses = null; } if (tipoTasa.getInteres() != null) {
		 * 
		 * }
		 */

		/*
		 * if (tipoTasa.getListaModificadores() != null) { tipoTasa.getListaModificadores().clear(); if (modificadores != null) {
		 * tipoTasa.getListaModificadores().addAll(modificadores); } } this.setListaDelCommunication2(modificadores);
		 */{
			ind = 0;
		}

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

		// ////////////////////////

		this.getElementoPila().getObjetos().set(ind++, tipoTasa);
		this.getElementoPila().getObjetos().set(ind++, variables);
		this.getElementoPila().getObjetos().set(ind++, modificadores);
		this.getElementoPila().getObjetos().set(ind++, vencimientos);
		// ind++;
		// ind++;
		this.getElementoPila().getObjetos().set(ind++, parametrosValor);
		this.getElementoPila().getObjetos().set(ind++, parametrosValorMap);
		this.getElementoPila().getObjetos().set(ind++, fechaLiquidacion);
		this.getElementoPila().getObjetos().set(ind++, fechaCobro);
		// this.getElementoPila().getObjetos().set(ind++, tipoParametro);
		this.getElementoPila().getObjetos().set(11, listaDatos);
		this.getElementoPila().getObjetos().set(12, tipoTasa.getFormulaRegAlicuota());
		this.getElementoPila().getObjetos().set(13, variablesFormula);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		TipoTasa tipoTasa = null;
		ArrayList variables = null;
		ArrayList modificadores = null;
		ArrayList vencimientos = null;
		ArrayList parametrosValor = null;
		HashMap parametrosValorMap = null;
		Date fechaLiquidacion = null;
		Date fechaCobro = null;
		// String tipoParametro = null;
		ArrayList listaDatos = null;
		String formulaAlicuota = null;
		List<VariableFormulaSimple> variablesFormula = null;

		// Intereses
		ArrayList intereses = null;
		ArrayList recargos = null;
		TipoParametro nuevoTipoParametro = null;

		if(this.getRequestBean1().getObjeto() != null) {
			formulaAlicuota = (String) this.getRequestBean1().getObjeto();
			tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
			System.out.println("fomula alicuota-----" + formulaAlicuota);
			tipoTasa.setFormulaRegAlicuota(formulaAlicuota);
			this.getElementoPila().getObjetos().set(0, tipoTasa);
		}

		if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
			ArrayList seleccionados = this.getRequestBean1().getObjetosSeleccionMultiple();
			// int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			int posicion = 1;

			variables = this.obtenerObjetoDelElementoPila(posicion, ArrayList.class);
			parametrosValor = this.obtenerObjetoDelElementoPila(4, ArrayList.class);

			if(posicion == 1) {

				for(Object selected : seleccionados) {
					nuevoTipoParametro = (TipoParametro) selected;
					TipoParametro deLaTabla = null;
					boolean esta = false;
					for(int i = 0; i < variables.size(); i++) {
						deLaTabla = (TipoParametro) variables.get(i);
						if(deLaTabla.getNombreVariable().equalsIgnoreCase(nuevoTipoParametro.getNombreVariable())) {
							esta = true;
						}
					}
					if(!esta) {
						boolean esGrilla = false;
						if (nuevoTipoParametro instanceof TipoParametroGrilla) {
							esGrilla = true;
							try {
								this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
								nuevoTipoParametro = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getTipoParametroGrillaPorId(nuevoTipoParametro.getIdTipoParametro());
							} catch (Exception e) {
								e.printStackTrace();
								error(e.getMessage());
							}
						}
						variables.add(nuevoTipoParametro);
						if(!(nuevoTipoParametro instanceof TipoParametroConstante) 
								&& !(nuevoTipoParametro instanceof TipoParametroVencimiento)
								&& !esGrilla) {
							parametrosValor.add(new ParametroValor(nuevoTipoParametro.getNombreVariable()));
							// this.getElementoPila().getObjetos().set(4, parametrosValor);
						}
					}
				}
				// else {
				// warn("El Par\341metro que intenta agregar ya se encuentra en la lista.");
				// }
			}
			this.getElementoPila().getObjetos().set(4, parametrosValor);
			this.getElementoPila().getObjetos().set(posicion, variables);

			this.getRequestBean1().setObjetosSeleccionMultiple(null);
		}

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
			System.out.println("posicionnnnnnn!!-- " + posicion);
			if(posicion == 2) {
				modificadores = this.obtenerObjetoDelElementoPila(posicion, ArrayList.class);

				modificadores.add(respuesta);
				this.getElementoPila().getObjetos().set(posicion, modificadores);
			}

			if(posicion == 3) {
				vencimientos = this.obtenerObjetoDelElementoPila(posicion, ArrayList.class);

				vencimientos.add(respuesta);
				this.getElementoPila().getObjetos().set(posicion, vencimientos);
			}

			if(posicion == 9) {

				tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
				intereses = this.obtenerObjetoDelElementoPila(posicion, ArrayList.class);

				tipoTasa.setInteres((ConceptoPorMora) respuesta);
				intereses.clear();
				intereses.add(respuesta);

				this.getElementoPila().getObjetos().set(0, tipoTasa);
				this.getElementoPila().getObjetos().set(posicion, intereses);
			}

			if(posicion == 10) {
				tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
				recargos = this.obtenerObjetoDelElementoPila(posicion, ArrayList.class);

				tipoTasa.setRecargo((ConceptoPorMora) respuesta);
				recargos.clear();
				recargos.add(respuesta);

				this.getElementoPila().getObjetos().set(0, tipoTasa);
				this.getElementoPila().getObjetos().set(posicion, recargos);
			}

			if(posicion == 0) {
				tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
				tipoTasa = (TipoTasa) respuesta;
				variables = new ArrayList();
				variables.addAll(tipoTasa.getListaParametros());
				// System.out.println("rtaABM: " + formulaAlicuota);
				// tipoTasa.setFormulaRegAlicuota(formulaAlicuota);

				parametrosValor = new ArrayList();
				TipoParametro tp = null;
				Object temp = null;
				System.out.println("///////cantidad variables: " + variables.size());
				for(int i = 0; i < variables.size(); i++) {
					temp = variables.get(i);
					if(!(temp instanceof TipoParametroConstante) && !(temp instanceof TipoParametroVencimiento)) {
						tp = (TipoParametro) temp;
						parametrosValor.add(new ParametroValor(tp.getNombreVariable()));
					}
				}
				System.out.println("////////parametrosValor: " + parametrosValor.size());

				this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, null);

				this.getElementoPila().getObjetos().set(0, tipoTasa);
				this.getElementoPila().getObjetos().set(1, variables);
				this.getElementoPila().getObjetos().set(4, parametrosValor);
			}

		}

		ind = 0;
		tipoTasa = this.obtenerObjetoDelElementoPila(ind++, TipoTasa.class);
		variables = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		modificadores = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		vencimientos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		parametrosValor = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		parametrosValorMap = this.obtenerObjetoDelElementoPila(ind++, HashMap.class);
		fechaLiquidacion = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		fechaCobro = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		// tipoParametro = this.obtenerObjetoDelElementoPila(ind++, TipoParametro.TIPOS_PARAMETRO.class);
		ind++;
		intereses = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		recargos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		listaDatos = this.obtenerObjetoDelElementoPila(11, ArrayList.class);
		variablesFormula = this.obtenerObjetoDelElementoPila(13, ArrayList.class);
		TipoObligacion tipoObligacion = this.obtenerObjetoDelElementoPila(14, TipoObligacion.class);
		// /recupero datos guardados del armado de condiciones:

		if(listaDatos.size() > 0) {
			if(listaDatos.get(0) != null) {
				this.getDdUnion1().setSelected(Util.getEnumNameFromString(listaDatos.get(0).toString()));
			}
			if(listaDatos.get(1) != null) {
				this.getDdUnion2().setSelected(Util.getEnumNameFromString(listaDatos.get(1).toString()));
			}

			if(listaDatos.get(2) != null) {
				this.getDdVariable1().setSelected(Util.getEnumNameFromString(listaDatos.get(2).toString()));
			}
			if(listaDatos.get(3) != null) {
				this.getDdVariable2().setSelected(Util.getEnumNameFromString(listaDatos.get(3).toString()));
			}
			if(listaDatos.get(4) != null) {
				this.getDdVariable3().setSelected(Util.getEnumNameFromString(listaDatos.get(4).toString()));
			}

			if(listaDatos.get(5) != null) {
				this.getDdOperador1().setSelected(Util.getEnumNameFromString(listaDatos.get(5).toString()));
			}
			if(listaDatos.get(6) != null) {
				this.getDdOperador2().setSelected(Util.getEnumNameFromString(listaDatos.get(6).toString()));
			}
			if(listaDatos.get(7) != null) {
				this.getDdOperador3().setSelected(Util.getEnumNameFromString(listaDatos.get(7).toString()));
			}

			if(listaDatos.get(8) != null) {
				this.getTfValor1().setText(listaDatos.get(8).toString());
			}
			if(listaDatos.get(9) != null) {
				this.getTfValor2().setText(listaDatos.get(9).toString());
			}
			if(listaDatos.get(10) != null) {
				this.getTfValor3().setText(listaDatos.get(10).toString());
			}

			if(listaDatos.get(11) != null) {
				this.getTfValorFinal().setText(listaDatos.get(11).toString());
			}
			if(listaDatos.get(12) != null) {
				this.getTfValorFinalSino().setText(listaDatos.get(12).toString());
			}

		}
		// ////////////////////////

		this.getObjectListDataProvider().setList(variables);
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider().getObjects(), "cap");
		lbVariablesDefaultOptions.setOptions(op);

		int k = 0;
		Object[] opcionesAMostrar;

		if(tipoObligacion.getNombre().equals("OYSP")) {
			opcionesAMostrar = new Object[7];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.OSP;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
		} else if(tipoObligacion.getNombre().equals("PLAN_FINANCIACION_OBRA")) {
			opcionesAMostrar = new Object[7];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.OBRA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
		} else if(tipoObligacion.getNombre().equals("SHPS")) {
			opcionesAMostrar = new Object[6];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.SHPS;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.GRILLA;
		} else if(tipoObligacion.getNombre().equals("TASAS_MENORES_COMERCIO")) {
			opcionesAMostrar = new Object[5];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.SHPS;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
		} else if(tipoObligacion.getNombre().equals("TGI")) {
			opcionesAMostrar = new Object[7];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.TGI;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
		} else if(tipoObligacion.getNombre().equals("ARRENDAMIENTO")) {
			opcionesAMostrar = new Object[6];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
//			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.TGI;
			//TODO Arrendamiento para los dinamicos
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
		} else if(tipoObligacion.getNombre().equals("TRANSITO")) {
			opcionesAMostrar = new Object[4];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
		} else if(tipoObligacion.getNombre().equals("CEMENTERIO")) {
			opcionesAMostrar = new Object[5];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CEMENTERIO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
		} else if(tipoObligacion.getNombre().equals("AUTOMOTOR")) {
			opcionesAMostrar = new Object[6];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.AUTOMOTOR;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VEHICULO;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.DEUDA;
		} else {
			FiltroPlantillaDocumentoTasaMenor locFiltro = new FiltroPlantillaDocumentoTasaMenor();
			locFiltro.setNombre(tipoObligacion.getNombre());

			try {
				locFiltro = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().findListaPlantillaDocumentoTasaMenor(locFiltro);
			} catch(TrascenderException e1) {
				e1.printStackTrace();
			}

			PlantillaDocumentoTasaMenor locPlantillaTasaMenor = null;
			locPlantillaTasaMenor = (PlantillaDocumentoTasaMenor) locFiltro.getListaResultados().get(0);

			int valor = 0;
			if(locPlantillaTasaMenor != null && locPlantillaTasaMenor.isAsociacionAParcela()) {
				valor = 1;
			}

			opcionesAMostrar = new Object[4 + valor];
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.CONSTANTE;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PERSONA;
			opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO;
			if(valor == 1) {
				opcionesAMostrar[k++] = TipoParametro.TIPOS_PARAMETRO.PARCELARIO;
			}
			opcionesAMostrar[k++] = tipoObligacion.getNombre();
		}

		op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(opcionesAMostrar, "cap");
		ddTiposParametroDefaultOptions.setOptions(op);

		// this.getDdTiposParametro().setSelected(Util.getEnumNameFromString(String.valueOf(tipoParametro)));
		// this.getDdTiposParametroDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(tipoParametro)));

		this.getTfTipoObligacion().setText(tipoObligacion.toString().toUpperCase());
		this.getTfNombre().setText(tipoTasa.getNombre());

		if(tipoTasa.getPlan() != null) {
			this.getDdPlanes().setSelected(tipoTasa.getPlan().getNombre().toString());
		}

		// this.getDdPeriodicidad().setSelected(Util.getEnumNameFromString(String.valueOf(tipoTasa.getPeriodicidad())));
		// this.getDdPeriodicidadDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(tipoTasa.getPeriodicidad())));
		// this.getDdPeriodicidadCuotas().setSelected(Util.getEnumNameFromString(String.valueOf(tipoTasa.getPeriodicidadCuotas())));
		// this.getDdPeriodicidadCuotasDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(tipoTasa.getPeriodicidadCuotas())));
		this.getCbFija().setValue(new Boolean(tipoTasa.isFija()));
		this.getTaFormula().setText(tipoTasa.getFormula());
		this.getTaFormulaAlicuota().setText(tipoTasa.getFormulaRegAlicuota());

		if(tipoTasa.getInteres() != null) {
			this.getTfInteres().setText(tipoTasa.getInteres().toString());
		}
		if(tipoTasa.getRecargo() != null) {
			this.getTfRecargo().setText(tipoTasa.getRecargo().toString());
		}

		if(fechaLiquidacion != null) {
			this.getTfFechaLiquidacion().setText(Conversor.getStringDeFechaCorta(fechaLiquidacion));
		}
		if(fechaCobro != null) {
			this.getTfFechaCobro().setText(Conversor.getStringDeFechaCorta(fechaCobro));
		}
		if(tipoTasa.getCondicionNoLiquidacion() != null){
			this.getTaCondicionNoLiquidacion().setText(tipoTasa.getCondicionNoLiquidacion());
		}
		this.setListaDelCommunication(variables);
		this.getObjectListDataProvider().setList(variables);

		this.setListaDelCommunication2(modificadores);
		this.getObjectListDataProvider2().setList(modificadores);

		this.setListaDelCommunication3(vencimientos);
		this.getObjectListDataProvider3().setList(vencimientos);

		// parametrosValor = new ArrayList();
		// TipoParametro tp = null;
		// Object temp = null;
		// for (int i = 0; i < variables.size(); i++) {
		// temp = variables.get(i);
		// if (!(temp instanceof TipoParametroConstante) && !(temp instanceof TipoParametroVencimiento)) {
		// tp = (TipoParametro) variables.get(i);
		// parametrosValor.add(new ParametroValor(tp.getNombreVariable()));
		// }
		// }

		this.setListaDelCommunication7(parametrosValor);
		this.getObjectListDataProvider7().setList(parametrosValor);

		this.setListaDelCommunication5(intereses);
		this.getObjectListDataProvider5().setList(intereses);

		this.setListaDelCommunication6(recargos);
		this.getObjectListDataProvider6().setList(recargos);

		this.setListaDelCommunicationVariablesFormula(variablesFormula);
		this.getObjectListDataProviderVariableFormula().setList(new ArrayList(variablesFormula));

		// ariel - No se puede Guardar si la Formula tiene el estado en ACTIVA.
		// if (tipoTasa.getEstado().equals(TipoTasa.Estado.ACTIVA)) {
		// this.getBtnGuardar().setDisabled(true);
		// }
	}

	private void guardarDatos(UIComponent componente, ArrayList lista) {
		if(((UIInput) componente).getValue() != null && ((UIInput) componente).getValue().toString().length() > 0) {
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

	public ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaTiposParametro();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaTiposParametro(lista);
	}

	// Modificadores
	private ObjectListDataProvider getObjectListDataProvider2() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpModificador();
	}

	public ArrayList getListaDelCommunication2() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaModificadores();
	}

	private void setListaDelCommunication2(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaModificadores(lista);
	}

	// Vencimientos
	private ObjectListDataProvider getObjectListDataProvider3() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpVencimiento();
	}

	public ArrayList getListaDelCommunication3() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaVencimientos();
	}

	private void setListaDelCommunication3(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaVencimientos(lista);
	}

	// ParametrosValor
	private ObjectListDataProvider getObjectListDataProvider7() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpParametroValor();
	}

	private ObjectListDataProvider getObjectListDataProvider5() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpInteres();
	}

	private ObjectListDataProvider getObjectListDataProvider6() {
		return this.getLdpRecargo();
	}

	private ArrayList getListaDelCommunication7() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaParametrosValores();
	}

	private void setListaDelCommunication7(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaParametrosValores(lista);
	}

	// Lista Intereses
	private ArrayList getListaDelCommunication5() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaIntereses();
	}

	private void setListaDelCommunication5(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaIntereses(lista);
	}

	private ArrayList getListaDelCommunication6() {
		return this.getCommunicationHabilitacionesBean().getListaRecargos();
	}

	private void setListaDelCommunication6(ArrayList lista) {
		this.getCommunicationHabilitacionesBean().setListaRecargos(lista);
	}

	public List<VariableFormulaSimple> getListaDelCommunicationVariablesFormula() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaVariablesFormulaSimples();
	}

	private void setListaDelCommunicationVariablesFormula(List<VariableFormulaSimple> lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaVariablesFormulaSimples(lista);
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
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
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	public String getCurrentRow3() {
		return tableRowGroup3.getRowKey().getRowId();
	}

	public void setCurrentRow3(int row) {
	}

	private Object lastSelected3 = null;

	public Object getRBSelected3() {
		String sv = (String) radioButton3.getSelectedValue();
		return sv.equals(lastSelected3) ? sv : null;
	}

	public void setRBSelected3(Object selected) {
		if(selected != null) {
			lastSelected3 = selected;
		}
	}

	private Object lastSelected7 = null;

	public Object getRBSelected7() {
		String sv = (String) radioButton7.getSelectedValue();
		return sv.equals(lastSelected7) ? sv : null;
	}

	public void setRBSelected7(Object selected) {
		if(selected != null) {
			lastSelected7 = selected;
		}
	}

	private Object lastSelected5 = null;

	public Object getRBSelected5() {
		String sv = (String) radioButton5.getSelectedValue();
		return sv.equals(lastSelected5) ? sv : null;
	}

	public void setRBSelected5(Object selected) {
		if(selected != null) {
			lastSelected5 = selected;
		}
	}

	public String getCurrentRow5() {
		return tableRowGroup5.getRowKey().getRowId();
	}

	public void setCurrentRow5(int row) {
	}

	private Object lastSelected6 = null;

	public Object getRBSelected6() {
		String sv = (String) radioButton6.getSelectedValue();
		return sv.equals(lastSelected6) ? sv : null;
	}

	public void setRBSelected6(Object selected) {
		if(selected != null) {
			lastSelected6 = selected;
		}
	}

	public String getCurrentRow6() {
		return tableRowGroup6.getRowKey().getRowId();
	}

	public void setCurrentRow6(int row) {
	}

	public String getCurrentRow7() {
		return tableRowGroup7.getRowKey().getRowId();
	}

	public void setCurrentRow7(int row) {
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			// Ariel - caso especial: se usa el ldp 2.
			rk = this.getObjectListDataProvider2().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado3() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup3");
			// Ariel - caso especial: se usa el ldp 3.
			rk = this.getObjectListDataProvider3().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado7() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup7");
			rk = this.getObjectListDataProvider7().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado5() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup5");
			rk = this.getObjectListDataProvider5().getRowKey(aRowId);
		} catch(Exception ex) {
		}

		return rk;
	}

	public RowKey getSeleccionado6() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup6");
			rk = this.getObjectListDataProvider6().getRowKey(aRowId);
		} catch(Exception ex) {
		}

		return rk;
	}

	public RowKey getSeleccionado8() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup7");
			rk = this.getObjectListDataProviderVariableFormula().getRowKey(aRowId);
		} catch(Exception ex) {
		}

		return rk;
	}

	public String btnNuevoParametro_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 1;

		if(ultimo) {

			Object tipoParametro = this.getDdTiposParametro().getSelected();

			this.guardarEstadoObjetosUsados();

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			if(tipoParametro != null && tipoParametro != "") {
				if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.PARCELARIO.toString()))) {
					retorno = "AdminTipoParametroParcelario";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.GRUPO_ZONA.toString()))) {
					this.getRequestBean1().setTipoSeleccion("MULTIPLE");
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					retorno = "AdminTipoParametroGrupoZona";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.CONSTANTE.toString()))) {
					this.getRequestBean1().setTipoSeleccion("MULTIPLE");
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					retorno = "AdminTipoParametroConstante";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.OBRA.toString()))) {
					retorno = "AdminTipoParametroObra";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.PERSONA.toString()))) {
					retorno = "AdminTipoParametroPersona";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.SHPS.toString()))) {
					String tipoParametroSHPS = "SHPS";
					this.getRequestBean1().setObjetoSeleccion(tipoParametroSHPS);
					retorno = "AdminTipoParametroSHPS";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.OSP.toString()))) {
					retorno = "AdminTipoParametroOSP";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.TGI.toString()))) {
					retorno = "AdminTipoParametroTGI";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.VENCIMIENTO.toString()))) {
					retorno = "AdminTipoParametroVencimiento";
				}else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.GRILLA.toString()))){
					this.getRequestBean1().setTipoSeleccion("MULTIPLE");
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					retorno = "AdminTipoParametroGrilla";
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.CEMENTERIO.toString()))) {
					TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
					this.getRequestBean1().setObjetoSeleccion(tipoTasa.getPlan().getTipoObligacion());
					retorno = "AdminTipoParametroCementerio";
					System.out.println("RETORNO: " + retorno);
				} else if(tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.AUTOMOTOR.toString()))) {
					TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
					this.getRequestBean1().setObjetoSeleccion(tipoTasa.getPlan().getTipoObligacion());
					retorno = "AdminTipoParametroAutomotor";
				} else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.VEHICULO.toString()))) {
					TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
					this.getRequestBean1().setObjetoSeleccion(tipoTasa.getPlan().getTipoObligacion());
					retorno = "AdminTipoParametroVehiculo";
				}
				else if (tipoParametro.equals(Util.getEnumNameFromString(TipoParametro.TIPOS_PARAMETRO.DEUDA.toString()))) {
					TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
					this.getRequestBean1().setObjetoSeleccion(tipoTasa.getPlan().getTipoObligacion());
					retorno = "AdminTipoParametroDeuda";
				} else {
					TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
					String tipoObligacion = tipoTasa.getPlan().getTipoObligacion().getNombre().replace(" ", "_").toUpperCase();

					if(tipoParametro.equals(tipoObligacion)) {
						this.getRequestBean1().setObjetoSeleccion(tipoTasa.getPlan().getTipoObligacion());
						retorno = "AdminTipoParametroDinamico";
					}
				}

				this.getRequestBean1().setRefrescarTabla(true);
			} else {
				warn("Seleccione un Tipo para agregar un nuevo Par\341metro.");
			}

			this.guardarEstadoObjetosUsados();

			if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
				for(Object object : this.getRequestBean1().getObjetosSeleccionMultiple()) {
					this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
				}
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarParametro_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {
				this.getObjectListDataProvider().commitChanges();
				this.getObjectListDataProvider7().commitChanges();
				TipoParametro tipoParametro = null;
				Object tipoParametroSelected = this.getLbVariables().getSelected();

				if((tipoParametroSelected != null) && (tipoParametroSelected.toString().length() > 0)) {

					for(int i = 0; i < this.getListaDelCommunication().size(); i++) {
						tipoParametro = (TipoParametro) this.getListaDelCommunication().get(i);

						if(tipoParametro.getNombreVariable().equalsIgnoreCase(tipoParametroSelected.toString())) {

							this.getObjectListDataProvider().getList().remove(i);

							if(!(tipoParametro instanceof TipoParametroConstante)) {
								for(Object o : this.getListaDelCommunication7()) {
									ParametroValor p = (ParametroValor) o;

									if(p.getParametro().equals(tipoParametro.toString())) {
										this.getObjectListDataProvider7().getList().remove(o);
										break;
									}
								}
							}
							break;
						}
					}
				}

			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarModificador_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 2;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setObjetoSeleccion(this.getListaDelCommunication());
			this.getRequestBean1().setAbmController(new TipoModificadorModel().new AgregarTipoModificadorController());
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}
			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "ABMTipoModificador";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarModificador_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			this.guardarEstadoObjetosUsados();

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider2().getObjects()[index];
					this.getRequestBean1().setObjetoSeleccion(this.getListaDelCommunication());
					this.getRequestBean1().setAbmController(new TipoModificadorModel().new ModificarTipoModificadorController());
					this.getRequestBean1().setObjetoABM(obj);

					retorno = "ABMTipoModificador";

				}
			} catch(Exception ex) {
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarModificador_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				this.getObjectListDataProvider2().commitChanges();
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider2().getObjects()[index];
					this.getListaDelCommunication2().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarVencimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 3;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setObjetoSeleccion(this.getListaDelCommunication());
			this.getRequestBean1().setAbmController(new TipoVencimientoModel().new AgregarTipoVencimientoController());
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "ABMTipoVencimiento";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnModificarVencimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			this.guardarEstadoObjetosUsados();

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			try {
				rk = this.getSeleccionado3();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider3().getObjects()[index];
					this.getRequestBean1().setObjetoSeleccion(this.getListaDelCommunication());
					this.getRequestBean1().setAbmController(new TipoVencimientoModel().new ModificarTipoVencimientoController());
					this.getRequestBean1().setObjetoABM(obj);

					retorno = "ABMTipoVencimiento";

				}
			} catch(Exception ex) {
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarVencimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado3();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider3().getObjects()[index];
					// OJO! Distinto. Pero asi anda. Con remove(obj) no funciona.
					this.getListaDelCommunication3().remove(index);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarFormulaAlicuota_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 0;

		if(ultimo) {
			this.guardarEstadoObjetosUsados();

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setObjetoSeleccion(this.getListaDelCommunication());
			if(tipoTasa != null) {
				this.getRequestBean1().setObjeto(tipoTasa);
				if(tipoTasa.getFormulaRegAlicuota() == null || tipoTasa.getFormulaRegAlicuota() == "") {
					this.getRequestBean1().setTipoSeleccion("ADD");
				} else {
					this.getRequestBean1().setTipoSeleccion("EDIT");
				}
			}
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AgregarTipoTasaFormulaAlicuota";

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarFormulaAlicuota_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
		String formulaAlicuota = this.obtenerObjetoDelElementoPila(12, String.class);

		if(ultimo) {

			this.getTaFormulaAlicuota().setText(null);
			tipoTasa.setFormulaRegAlicuota(null);
			this.getElementoPila().getObjetos().set(0, tipoTasa);
			this.getElementoPila().getObjetos().set(12, null);
			this.guardarEstadoObjetosUsados();
			this.mostrarEstadoObjetosUsados();
			// this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnProbarFormula_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				this.guardarEstadoObjetosUsados();

				TipoTasa tipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
				System.out.println("formula/// : " + tipoTasa.getFormula());
				HashMap parametrosValorMap = this.obtenerObjetoDelElementoPila(5, HashMap.class);

				Validador v = new Validador();
				UIComponent[] novacio = new UIComponent[3];
				String[] nomNovacio = new String[4];
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
				if(tipoTasa.getListaVencimientos() == null || tipoTasa.getListaVencimientos().isEmpty()) {
					v.getErrores().add("Debe agregar al menos un Vencimiento.");
				}

				if(v.getErrores().size() > 0) {
					error("Para probar la F\363rmula de C\341lculo:");
					for(int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					this.mostrarEstadoObjetosUsados();
					return null;
				}

				Date fechaLiquidacion = Conversor.getFechaCortaDeString(this.getTfFechaLiquidacion().getText().toString());
				Date fechaCobro = Conversor.getFechaCortaDeString(this.getTfFechaCobro().getText().toString());

				this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());

				// ///////////////
				//
				// this.getObjectListDataProvider7().setList(this.getListaDelCommunication7());
				// if (this.getObjectListDataProvider7().getList() != null && this.getObjectListDataProvider7().getList().size() > 0) {
				// this.getObjectListDataProvider7().commitChanges();
				// }
				// ArrayList parametrosValor = new ArrayList();
				// HashMap parametrosValorMap = new HashMap();
				// if (parametrosValor.isEmpty()) {
				// parametrosValor = null;
				// } else {
				// for (int i = 0; i < parametrosValor.size(); i++) {
				// muni.habilitaciones.ABMTipoTasaFormulaAlicuota.AgregarTipoTasaFormulaAlicuota.ParametroValor pv =
				// (muni.habilitaciones.ABMTipoTasaFormulaAlicuota.AgregarTipoTasaFormulaAlicuota.ParametroValor) parametrosValor.get(i);
				// parametrosValorMap.put(pv.getParametro(), pv.getValor());
				// }
				// }
				// this.setListaDelCommunication7(parametrosValor);

				// /////////////
				System.out.println("ProbarFormula cant map----" + parametrosValorMap.size());
				Double tasa = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().calcularTasa(tipoTasa, parametrosValorMap);

				// <String, Double>
				Map modificadoresSobreTasa = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().calcularModificadoresSobreTasa(tipoTasa, parametrosValorMap);

				// <String, Double>
				Map modificadoresSobreSubtotal = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().calcularModificadoresSobreSubtotal(tipoTasa, parametrosValorMap);

				// <Date, Double>
				Map vencimientos = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().calcularVencimientos(fechaLiquidacion, fechaCobro, tipoTasa, parametrosValorMap);

				// <String, Double>
				Map interesRecargo = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().calcularIntereses(fechaLiquidacion, fechaCobro, tipoTasa, parametrosValorMap);

				// Muestro Resultado: tasa
				String resultado = "<table cellspacing='0' cellpadding='2'><tr>   <td align='right' style=''>Tasa:</td>   <td align='right'>$</td>   <td>" + tasa
						+ "</td>   <td></td></tr>";

				String nombreModif = null;
				Double valorModif = null;

				// Muestro Resultado y Calculo SubTotal: modificadores sobre la tasa
				double subtotal = tasa.doubleValue();
				for(int i = 0; i < modificadoresSobreTasa.size(); i++) {
					nombreModif = (String) modificadoresSobreTasa.keySet().toArray()[i];
					valorModif = (Double) modificadoresSobreTasa.get(modificadoresSobreTasa.keySet().toArray()[i]);

					resultado += "<tr>  <td align='right'>" + nombreModif + "</td>   <td align='right'>$</td>   <td>" + valorModif + "</td>   <td></td></tr>";
					subtotal += valorModif.doubleValue();
				}

				// Muestro Resultado: subtotal
				resultado += "<tr>   <td align='right' style='border-top: 1px solid #666666;'>SubTotal:</td>   <td align='right' style='border-top: 1px solid #666666'>$</td>   <td style='border-top: 1px solid #666666'>"
						+ subtotal + "</td>   <td style='border-top: 1px solid #666666'></td></tr>";
				resultado += "<tr><td colspan='4'/></tr>";

				// Muestro Resultado y Calculo Total: modificadores sobre el subtotal
				double total = subtotal;
				for(int i = 0; i < modificadoresSobreSubtotal.size(); i++) {
					nombreModif = (String) modificadoresSobreSubtotal.keySet().toArray()[i];
					valorModif = (Double) modificadoresSobreSubtotal.get(modificadoresSobreSubtotal.keySet().toArray()[i]);

					resultado += "<tr>  <td align='right'>" + nombreModif + "</td>   <td align='right'>$</td>   <td>" + valorModif + "</td>   <td></td></tr>";
					total += valorModif.doubleValue();
				}

				// Muestro Resultado: total
				resultado += "<tr>   <td align='right' style='border-top: 1px solid #666666;'>Total:</td>   <td align='right' style='border-top: 1px solid #666666'>$</td>   <td style='border-top: 1px solid #666666'>"
						+ total + "</td>   <td style='border-top: 1px solid #666666'></td></tr>";

				// *---------------------------------------------------------------------------------------------------------------
				// Interes
				// Muestro Resultado y Calculo Total: modificadores sobre el subtotal

				// float total = subtotal;

				if(interesRecargo != null) {
					for(int i = 0; i < interesRecargo.size(); i++) {
						nombreModif = (String) interesRecargo.keySet().toArray()[i];
						valorModif = (Double) interesRecargo.get(nombreModif);

						resultado += "<tr>  <td align='right'>" + nombreModif + "</td>   <td align='right'>$</td>   <td>" + valorModif + "</td>   <td></td></tr>";
						total += valorModif.doubleValue();
					}

					resultado += "<tr>   <td align='right' style='border-top: 1px solid #666666;'>Total con Intereses:</td>   <td align='right' style='border-top: 1px solid #666666'>$</td>   <td style='border-top: 1px solid #666666'>"
							+ total + "</td>   <td style='border-top: 1px solid #666666'></td></tr>";
				}
				// Vencimientos
				resultado += "<tr><td colspan='4'/></tr><tr>   <td align='left' style='border-top: 1px solid #666666;'>Vencimientos</td>   <td align='right' style='border-top: 1px solid #666666'></td>   <td style='border-top: 1px solid #666666'></td>   <td style='border-top: 1px solid #666666'></td></tr>";

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				for(int i = 0; i < vencimientos.size(); i++) {
					nombreModif = sdf.format((Date) vencimientos.keySet().toArray()[i]);
					valorModif = (Double) vencimientos.get(vencimientos.keySet().toArray()[i]);

					resultado += "<tr>  <td align='right'>" + nombreModif + "</td>   <td align='right'>$</td>   <td>" + valorModif + "</td>   <td></td></tr>";
				}

				resultado += "</table>";

				this.getGdpResultado().setRendered(true);
				this.getStResultado().setText(resultado.toString());

			} catch(Exception ex) {
				error(this.getNombrePagina() + " - Probar F\363rmula: " + ex.getMessage());
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoTasa";
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

	public String btnAgregarInteres_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 9;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			/*
			 * TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0, TipoTasa.class); if (tipoTasa != null && tipoTasa.getInteres() != null) {
			 * this.getRequestBean1().setObjetoABM(tipoTasa.getInteres()); }
			 */

			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setObjetoSeleccion(this.getListaDelCommunication());
			this.getRequestBean1().setAbmController(new ConceptoPorMoraModel().new AgregarConceptoController());
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "ABMConceptoPorMora";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarInteres_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			this.guardarEstadoObjetosUsados();

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			try {
				rk = this.getSeleccionado5();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider5().getObjects()[index];
					this.getRequestBean1().setObjetoSeleccion(this.getListaDelCommunication());
					this.getRequestBean1().setAbmController(new ConceptoPorMoraModel().new ModificarConceptoController());
					this.getRequestBean1().setObjetoABM(obj);

					retorno = "ABMConceptoPorMora";

				}
			} catch(Exception ex) {
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarInteres_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado5();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider5().getObjects()[index];
					this.getListaDelCommunication5().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarRecargo_action() {
		// TODO: Replace with your code

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 10;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			/*
			 * TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0, TipoTasa.class); if (tipoTasa != null && tipoTasa.getRecargo() != null) {
			 * this.getRequestBean1().setObjetoABM(tipoTasa.getRecargo()); }
			 */

			ArrayList tiposParametro = new ArrayList();
			if(this.getListaDelCommunication() != null) {
				tiposParametro = this.getListaDelCommunication();
				tiposParametro.add(TipoParametroInteres.IMPORTE_INTERES);
			}

			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setObjetoSeleccion(tiposParametro);
			this.getRequestBean1().setAbmController(new ConceptoPorMoraModel().new AgregarConceptoController());
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "ABMConceptoPorMora";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;

	}

	public String btnModificarRecargo_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			this.guardarEstadoObjetosUsados();

			if(this.noNavega) {
				this.noNavega = false;
				retorno = null;
				this.mostrarEstadoObjetosUsados();
				return retorno;
			}

			try {
				rk = this.getSeleccionado6();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider6().getObjects()[index];
					this.getRequestBean1().setObjetoSeleccion(this.getListaDelCommunication());
					this.getRequestBean1().setAbmController(new ConceptoPorMoraModel().new ModificarConceptoController());
					this.getRequestBean1().setObjetoABM(obj);

					retorno = "ABMConceptoPorMora";

				}
			} catch(Exception ex) {
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarRecargo_action() {
		// TODO: Replace with your code
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado6();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider6().getObjects()[index];
					this.getListaDelCommunication6().remove(obj);
					this.getObjectListDataProvider6().setList(this.getListaDelCommunication6());
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void btnAgregarVariable_action() {
		this.getObjectListDataProviderVariableFormula().commitChanges();
		TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0);
		if(this.getTaFormula().getText() != null) {
			tipoTasa.setFormula(this.getTaFormula().getText().toString());
		}
		VariableFormulaSimple v = new VariableFormulaSimple();
		v.setNombre("");
		v.setExpresion("");

		this.getListaDelCommunicationVariablesFormula().add(v);
		this.getObjectListDataProviderVariableFormula().getList().add(v);

		this.mostrarEstadoObjetosUsados();
	}

	public String btnQuitarVariable_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			this.getObjectListDataProviderVariableFormula().commitChanges();
			try {
				rk = this.getSeleccionado8();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderVariableFormula().getObjects()[index];
					this.getListaDelCommunicationVariablesFormula().remove(obj);
					this.getObjectListDataProviderVariableFormula().setList(this.getListaDelCommunicationVariablesFormula());
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
	protected void procesarObjetoSeleccion(Object pObject) {

		TipoTasa tipoTasa = null;
		ArrayList variables = null;
		TipoParametro nuevoTipoParametro = null;
		ArrayList parametrosValor = null;
		TipoObligacion tipoObligacion = null;

		int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();

		if(pObject instanceof TipoObligacion) {
			tipoObligacion = (TipoObligacion) pObject;
			armarMapaPlan(tipoObligacion);
			this.getElementoPila().getObjetos().set(14, tipoObligacion);
		}

		if(posicion == 1) {
			nuevoTipoParametro = (TipoParametro) pObject;
			variables = this.obtenerObjetoDelElementoPila(posicion, ArrayList.class);
			parametrosValor = this.obtenerObjetoDelElementoPila(4, ArrayList.class);

			TipoParametro deLaTabla = null;
			boolean esta = false;
			for(int i = 0; i < variables.size(); i++) {
				deLaTabla = (TipoParametro) variables.get(i);
				if(deLaTabla.getNombreVariable().equalsIgnoreCase(nuevoTipoParametro.getNombreVariable())) {
					esta = true;
				}
			}
			if(!esta) {
				variables.add(nuevoTipoParametro);
				if(!(nuevoTipoParametro instanceof TipoParametroConstante) && !(nuevoTipoParametro instanceof TipoParametroVencimiento)) {
					parametrosValor.add(new ParametroValor(nuevoTipoParametro.getNombreVariable()));
					this.getElementoPila().getObjetos().set(4, parametrosValor);
				}
			} else {
				warn("El Par\341metro que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(posicion, variables);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {

		TipoTasa tipoTasa = (TipoTasa) pObject;
		ArrayList variables = new ArrayList(tipoTasa.getListaParametros());
		ArrayList modificadores = new ArrayList(tipoTasa.getListaModificadores());
		ArrayList vencimientos = new ArrayList(tipoTasa.getListaVencimientos());
		List<VariableFormulaSimple> variablesFormula = tipoTasa.getListaVariablesSimple();
		ArrayList recargos = null;
		ArrayList parametrosValor = null;
		HashMap parametrosValorMap = null;
		int ind;
		
		TipoObligacion tipoObligacion= tipoTasa.getTipoObligacion();
		this.getElementoPila().getObjetos().set(14, tipoObligacion);
		armarMapaPlan(tipoObligacion);
		
		// Intereses
		ArrayList intereses = new ArrayList();

		if(tipoTasa.getInteres() != null && tipoTasa.getInteres().getIdConceptoPorMora() != -1) {
			intereses.add(tipoTasa.getInteres());
		}

		recargos = new ArrayList();
		if(tipoTasa.getRecargo() != null) {
			recargos.add(tipoTasa.getRecargo());

		}
		
		parametrosValor = new ArrayList();
		TipoParametro tp = null;
		Object temp = null;
		for(int i = 0; i < variables.size(); i++) {
			temp = variables.get(i);
			if(!(temp instanceof TipoParametroConstante) && !(temp instanceof TipoParametroVencimiento)) {
				tp = (TipoParametro) variables.get(i);
				parametrosValor.add(new ParametroValor(tp.getNombreVariable()));
			}
		}
		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, tipoTasa);
		this.getElementoPila().getObjetos().set(ind++, variables);
		this.getElementoPila().getObjetos().set(ind++, modificadores);
		this.getElementoPila().getObjetos().set(ind++, vencimientos);
		this.getElementoPila().getObjetos().set(ind++, parametrosValor);
		this.getElementoPila().getObjetos().set(ind++, parametrosValorMap);

		this.getElementoPila().getObjetos().set(9, intereses);
		this.getElementoPila().getObjetos().set(10, recargos);
		this.getElementoPila().getObjetos().set(13, variablesFormula);

	}
	
    public void armarMapaPlan (TipoObligacion tipoObligacion){
    	   FiltroPlan locFiltro = new FiltroPlan();
           locFiltro.setTipoObligacion(tipoObligacion);
		try {
			this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa()
					.setLlave(this.getSessionBean1().getLlave());
			List<Plan> locListaPlan = this.getCommunicationHabilitacionesBean()
					.getRemoteSystemTipoTasa().findListaPlan(locFiltro)
					.getListaResultados();
			Map<String, Plan> locMapa = new LinkedHashMap<String, Plan>();
			for (Plan cadaPlan : locListaPlan) {
				locMapa.put(cadaPlan.getNombre().toString(), cadaPlan);
			}
			this.getCommunicationHabilitacionesBean().setMapaPlan(locMapa);
			Option[] op = this.getApplicationBean1().getMgrDropDown()
					.armarArrayOptionsObjectsList(locMapa.keySet().toArray(), "");
			ddPlanDefaultOptions.setOptions(op);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TipoTasa locTipoTasa = this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
		this.getTablaLogs().getLdpLogs().setList(locTipoTasa.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return TipoTasa.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMBien$ABMBien}";
	}
}