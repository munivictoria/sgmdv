/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.saic.ImprimirLiquidaciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionManual;
import com.trascender.saic.recurso.persistent.Vencimiento;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

/**
 * 
 * @author Fer Luca
 */
public class ABMLiquidacion extends ABMPageBean {

	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private MessageGroup messageGroup1 = new MessageGroup();
	private Label lblTasas = new Label();
	private Label lblBasico = new Label();
	private Label lblIntereses = new Label();
	private StaticText stModificadores = new StaticText();
	private StaticText stVencimientos = new StaticText();
	private DropDown ddTasas = new DropDown();
	private SingleSelectOptionsList ddTasasDefaultOptions = new SingleSelectOptionsList();
	private TextField tfBasico = new TextField();
	private TextField tfIntereses = new TextField();
	private TextField tfNombre = new TextField();
	private TextField tfValorModificador = new TextField();
	private TextField tfNombreVencimiento = new TextField();
	private TextField tfFechaVencimiento = new TextField();
	private Table table1 = new Table();
	private Table table2 = new Table();
	private RadioButton radioButton1 = new RadioButton();
	private RadioButton radioButton2 = new RadioButton();
	private Checkbox checkBox1 = new Checkbox();
	private Object lastSelected = null;
	private Object lastSelected2 = null;
	private ObjectListDataProvider ldpModificadores = new ObjectListDataProvider();
	private ObjectListDataProvider ldpVencimientos = new ObjectListDataProvider();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcNombreVencimiento = new TableColumn();
	private TableColumn tcFechaVencimiento = new TableColumn();
	private TableColumn tcValorModificador = new TableColumn();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tcCheckbox = new TableColumn();
	private Button btnAgregarModificador = new Button();
	private Button btnQuitarModificador = new Button();
	private Button btnAgregarVencimiento = new Button();
	private Button btnQuitarVencimiento = new Button();
	private PanelGroup groupPanel1 = new PanelGroup();
	private PanelGroup groupPanel2 = new PanelGroup();
	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	private TableColumn tcValorVencimiento = new TableColumn();
	private TextField tfValorVencimiento = new TextField();
	private Label lblComentario = new Label();
	private TextArea taComentario = new TextArea();

	private StaticText stAlicuotasLiquidadas = new StaticText();
	private Table table3 = new Table();
	private TableRowGroup tableRowGroup3 = new TableRowGroup();
	private ObjectListDataProvider ldpAlicuotasLiquidadas = new ObjectListDataProvider();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private RadioButton radioButton3 = new RadioButton();
	private Object lastSelected3 = null;
	private TextField textField1 = new TextField();
	private TextField textField2 = new TextField();
	private PanelGroup groupPanel3 = new PanelGroup();
	private Button btnQuitarAlicuotaLiquidada = new Button();

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup groupPanel3) {
		this.groupPanel3 = groupPanel3;
	}

	public Button getBtnQuitarAlicuotaLiquidada() {
		return btnQuitarAlicuotaLiquidada;
	}

	public void setBtnQuitarAlicuotaLiquidada(Button btnQuitarAlicuotaLiquidada) {
		this.btnQuitarAlicuotaLiquidada = btnQuitarAlicuotaLiquidada;
	}

	public StaticText getStAlicuotasLiquidadas() {
		return stAlicuotasLiquidadas;
	}

	public void setStAlicuotasLiquidadas(StaticText stAlicuotasLiquidadas) {
		this.stAlicuotasLiquidadas = stAlicuotasLiquidadas;
	}

	public Object getRBSelected3() {
		String sv = (String) radioButton3.getSelectedValue();
		return sv.equals(lastSelected3) ? sv : null;
	}

	public void setRBSelected3(Object selected) {
		if(selected != null) {
			lastSelected3 = selected;
		}
	}

	public String getCurrentRow3() {
		return tableRowGroup3.getRowKey().getRowId();
	}

	public void setCurrentRow3(int row) {
	}

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table table3) {
		this.table3 = table3;
	}

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup tableRowGroup3) {
		this.tableRowGroup3 = tableRowGroup3;
	}

	public ObjectListDataProvider getLdpAlicuotasLiquidadas() {
		return ldpAlicuotasLiquidadas;
	}

	public void setLdpAlicuotasLiquidadas(ObjectListDataProvider ldpAlicuotasLiquidadas) {
		this.ldpAlicuotasLiquidadas = ldpAlicuotasLiquidadas;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public RadioButton getRadioButton3() {
		return radioButton3;
	}

	public void setRadioButton3(RadioButton radioButton3) {
		this.radioButton3 = radioButton3;
	}

	public TextField getTextField1() {
		return textField1;
	}

	public void setTextField1(TextField textField1) {
		this.textField1 = textField1;
	}

	public TextField getTextField2() {
		return textField2;
	}

	public void setTextField2(TextField textField2) {
		this.textField2 = textField2;
	}

	public Label getLblComentario() {
		return lblComentario;
	}

	public void setLblComentario(Label lblComentario) {
		this.lblComentario = lblComentario;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	public TableColumn getTcValorVencimiento() {
		return tcValorVencimiento;
	}

	public void setTcValorVencimiento(TableColumn tcValorVencimiento) {
		this.tcValorVencimiento = tcValorVencimiento;
	}

	public TextField getTfValorVencimiento() {
		return tfValorVencimiento;
	}

	public void setTfValorVencimiento(TextField tfValorVencimiento) {
		this.tfValorVencimiento = tfValorVencimiento;
	}

	public Label getLblIntereses() {
		return lblIntereses;
	}

	public void setLblIntereses(Label lblIntereses) {
		this.lblIntereses = lblIntereses;
	}

	public TextField getTfIntereses() {
		return tfIntereses;
	}

	public void setTfIntereses(TextField tfIntereses) {
		this.tfIntereses = tfIntereses;
	}

	public TableColumn getTcCheckbox() {
		return tcCheckbox;
	}

	public void setTcCheckbox(TableColumn tcCheckbox) {
		this.tcCheckbox = tcCheckbox;
	}

	public Checkbox getCheckBox1() {
		return checkBox1;
	}

	public void setCheckBox1(Checkbox checkBox1) {
		this.checkBox1 = checkBox1;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public Button getBtnAgregarVencimiento() {
		return btnAgregarVencimiento;
	}

	public void setBtnAgregarVencimiento(Button btnAgregarVencimiento) {
		this.btnAgregarVencimiento = btnAgregarVencimiento;
	}

	public Button getBtnQuitarVencimiento() {
		return btnQuitarVencimiento;
	}

	public void setBtnQuitarVencimiento(Button btnQuitarVencimiento) {
		this.btnQuitarVencimiento = btnQuitarVencimiento;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public ObjectListDataProvider getLdpVencimientos() {
		return ldpVencimientos;
	}

	public void setLdpVencimientos(ObjectListDataProvider ldpVencimientos) {
		this.ldpVencimientos = ldpVencimientos;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public StaticText getStVencimientos() {
		return stVencimientos;
	}

	public void setStVencimientos(StaticText stVencimientos) {
		this.stVencimientos = stVencimientos;
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public TableColumn getTcFechaVencimiento() {
		return tcFechaVencimiento;
	}

	public void setTcFechaVencimiento(TableColumn tcFechaVencimiento) {
		this.tcFechaVencimiento = tcFechaVencimiento;
	}

	public TableColumn getTcNombreVencimiento() {
		return tcNombreVencimiento;
	}

	public void setTcNombreVencimiento(TableColumn tcNombreVencimiento) {
		this.tcNombreVencimiento = tcNombreVencimiento;
	}

	public TextField getTfFechaVencimiento() {
		return tfFechaVencimiento;
	}

	public void setTfFechaVencimiento(TextField tfFechaVencimiento) {
		this.tfFechaVencimiento = tfFechaVencimiento;
	}

	public TextField getTfNombreVencimiento() {
		return tfNombreVencimiento;
	}

	public void setTfNombreVencimiento(TextField tfNombreVencimiento) {
		this.tfNombreVencimiento = tfNombreVencimiento;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public Button getBtnAgregarModificador() {
		return btnAgregarModificador;
	}

	public void setBtnAgregarModificador(Button btnAgregarModificador) {
		this.btnAgregarModificador = btnAgregarModificador;
	}

	public Button getBtnQuitarModificador() {
		return btnQuitarModificador;
	}

	public void setBtnQuitarModificador(Button btnQuitarModificador) {
		this.btnQuitarModificador = btnQuitarModificador;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public ObjectListDataProvider getLdpModificadores() {
		return ldpModificadores;
	}

	public void setLdpModificadores(ObjectListDataProvider ldpModificadores) {
		this.ldpModificadores = ldpModificadores;
	}

	public DropDown getDdTasas() {
		return ddTasas;
	}

	public void setDdTasas(DropDown ddTasas) {
		this.ddTasas = ddTasas;
	}

	public SingleSelectOptionsList getDdTasasDefaultOptions() {
		return ddTasasDefaultOptions;
	}

	public void setDdTasasDefaultOptions(SingleSelectOptionsList ddTasasDefaultOptions) {
		this.ddTasasDefaultOptions = ddTasasDefaultOptions;
	}

	public Label getLblBasico() {
		return lblBasico;
	}

	public void setLblBasico(Label lblBasico) {
		this.lblBasico = lblBasico;
	}

	public Label getLblTasas() {
		return lblTasas;
	}

	public void setLblTasas(Label lblTasas) {
		this.lblTasas = lblTasas;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup messageGroup1) {
		this.messageGroup1 = messageGroup1;
	}

	public StaticText getStModificadores() {
		return stModificadores;
	}

	public void setStModificadores(StaticText stModificadores) {
		this.stModificadores = stModificadores;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public TextField getTfBasico() {
		return tfBasico;
	}

	public void setTfBasico(TextField tfBasico) {
		this.tfBasico = tfBasico;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public TableColumn getTcValorModificador() {
		return tcValorModificador;
	}

	public void setTcValorModificador(TableColumn tcValorModificador) {
		this.tcValorModificador = tcValorModificador;
	}

	public TextField getTfValorModificador() {
		return tfValorModificador;
	}

	public void setTfValorModificador(TextField tfValorModificador) {
		this.tfValorModificador = tfValorModificador;
	}

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	protected ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpModificadores();
	}

	protected ObjectListDataProvider getObjectListDataProviderVencimientos() {
		return this.getLdpVencimientos();
	}

	// public void valueChangeEvent(ValueChangeEvent event) {
	// System.out.println("valueChangeEvent()");
	// seleccionarListas();
	// }

	public String getSeleccionarListas() {
		this.getObjectListDataProvider().commitChanges();
		this.getObjectListDataProviderVencimientos().commitChanges();

		for(LiquidacionTasa cadaLiquidacion : this.getCommunicationSAICBean().getListaLiquidaciones()) {
			if(cadaLiquidacion.equals(this.getCommunicationSAICBean().getLiquidacionSeleccionada())) {

				if(this.getTfBasico().getText() != null && !this.getTfBasico().getText().equals("")) {
					cadaLiquidacion.setValor(Double.parseDouble(this.getTfBasico().getText().toString()));
				} else {
					cadaLiquidacion.setValor(new Double(0.00));
				}

				if(this.getTfIntereses().getText() != null && !this.getTfIntereses().getText().equals("")) {
					cadaLiquidacion.setInteres(Double.parseDouble(this.getTfIntereses().getText().toString()));
				} else {
					cadaLiquidacion.setInteres(new Double(0.00));
				}

				this.getCommunicationSAICBean().getMapaModificadores().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
						.clear();
				this.getCommunicationSAICBean().getMapaModificadores().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
						.addAll(this.getObjectListDataProvider().getList());

				this.getCommunicationSAICBean().getMapaVencimientos().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
						.clear();
				this.getCommunicationSAICBean().getMapaVencimientos().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
						.addAll(this.getObjectListDataProviderVencimientos().getList());

				break;
			}
		}

		if(this.getDdTasas().getSelected() != null && this.getDdTasas().getSelected() != "") {

			List<LiquidacionTasa> listaLiq = this.getCommunicationSAICBean().getListaLiquidaciones();
			String nombreTasa = this.getDdTasas().getSelected().toString();
			for(LiquidacionTasa cadaLiq : listaLiq) {
				if(cadaLiq.getTipoTasa().getTipoObligacion().getNombre().equals(nombreTasa)) {
					System.out.println("entro al if equals");
					System.out.println("TAMAÑO LISTA MODIF PARA: " + cadaLiq + " = " + cadaLiq.getListaModificadoresLiquidacion().size());
					this.getTfBasico().setText(cadaLiq.getValor());
					this.getTfIntereses().setText(cadaLiq.getInteres());
					this.getCommunicationSAICBean().setLiquidacionSeleccionada(cadaLiq);
					this.getObjectListDataProvider().setList(
							new ArrayList(this.getCommunicationSAICBean().getMapaModificadores().get(cadaLiq.getTipoTasa().getTipoObligacion().getNombre())));
					this.getObjectListDataProviderVencimientos().setList(
							new ArrayList(this.getCommunicationSAICBean().getMapaVencimientos().get(cadaLiq.getTipoTasa().getTipoObligacion().getNombre())));
					break;
				}
			}
		} else {
			this.getObjectListDataProvider().setList(new ArrayList());
			this.getObjectListDataProviderVencimientos().setList(new ArrayList());
			this.getTfBasico().setText("");
			this.getTfIntereses().setText("");
			this.getCommunicationSAICBean().setLiquidacionSeleccionada(null);
		}

		return "this.form.submit()";
	}

	public void setSeleccionarListas() {

	}

	@Override
	protected void _init() throws Exception {
		System.out.println("_init()");
		this.getDateTimeConverter1().setPattern("dd/MM/yyyy");
		this.getDateTimeConverter1().setTimeZone(TimeZone.getDefault());

		if(this.getCommunicationSAICBean().getListaLiquidaciones() != null) {
			List listaNombres = new ArrayList();
			for(LiquidacionTasa cadaLiq : this.getCommunicationSAICBean().getListaLiquidaciones()) {
				listaNombres.add(cadaLiq.getTipoTasa().getTipoObligacion().getNombre());
			}
			Option[] op = null;
			op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(listaNombres.toArray(), "cap");
			ddTasasDefaultOptions.setOptions(op);
		}

		if(this.getCommunicationSAICBean().getListaAlicuotasLiquidadas() != null) {
			this.getLdpAlicuotasLiquidadas().setList(this.getCommunicationSAICBean().getListaAlicuotasLiquidadas());
		}

		if(this.getCommunicationSAICBean().getMapaModificadores() != null && this.getCommunicationSAICBean().getLiquidacionSeleccionada() != null) {
			this.getObjectListDataProvider().setList(
					new ArrayList(this.getCommunicationSAICBean().getMapaModificadores()
							.get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())));
			System.out.println("SETEO LDP (_init)");
		}

		if(this.getCommunicationSAICBean().getMapaVencimientos() != null && this.getCommunicationSAICBean().getLiquidacionSeleccionada() != null) {
			this.getObjectListDataProviderVencimientos().setList(
					new ArrayList(this.getCommunicationSAICBean().getMapaVencimientos()
							.get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())));
			System.out.println("SETEO LDP (_init)");
		}
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		this.getObjectListDataProvider().commitChanges();
		System.out.println(this.getObjectListDataProvider().getList());
		for(Object o : this.getObjectListDataProvider().getList()) {
			ModificadorLiquidacion m = (ModificadorLiquidacion) o;
			System.out.println("nombre " + m.getNombre());
			System.out.println("valor " + m.getValorModificador());
		}

		this.getLdpAlicuotasLiquidadas().commitChanges();

		this.getObjectListDataProviderVencimientos().commitChanges();

		for(LiquidacionTasa cadaLiquidacion : this.getCommunicationSAICBean().getListaLiquidaciones()) {
			if(cadaLiquidacion.equals(this.getCommunicationSAICBean().getLiquidacionSeleccionada())) {

				if(this.getTfBasico().getText() != null && !this.getTfBasico().getText().equals("")) {
					cadaLiquidacion.setValor(Double.parseDouble(this.getTfBasico().getText().toString()));
				} else {
					cadaLiquidacion.setValor(new Double(0.00));
				}

				if(this.getTfIntereses().getText() != null && !this.getTfIntereses().getText().equals("")) {
					cadaLiquidacion.setInteres(Double.parseDouble(this.getTfIntereses().getText().toString()));
				} else {
					cadaLiquidacion.setInteres(new Double(0.00));
				}

				this.getCommunicationSAICBean().getMapaModificadores().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
						.clear();
				this.getCommunicationSAICBean().getMapaModificadores().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
						.addAll(this.getObjectListDataProvider().getList());

				this.getCommunicationSAICBean().getMapaVencimientos().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
						.clear();
				this.getCommunicationSAICBean().getMapaVencimientos().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
						.addAll(this.getObjectListDataProviderVencimientos().getList());

				break;
			}
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
	}

	public void btnAgregarModificador_action() {
		this.getObjectListDataProvider().commitChanges();
		this.guardarEstadoObjetosUsados();
		LiquidacionTasa liquidacionSeleccionada = this.getCommunicationSAICBean().getLiquidacionSeleccionada();
		Set<ModificadorLiquidacion> listaModificadores = this.getCommunicationSAICBean().getMapaModificadores()
				.get(liquidacionSeleccionada.getTipoTasa().getTipoObligacion().getNombre());

		ModificadorLiquidacion locmodificador = new ModificadorLiquidacionManual();
		locmodificador.setLiquidacionTasa(liquidacionSeleccionada);
		locmodificador.setNombre("");
		locmodificador.setValorModificador(0.00);

		listaModificadores.add(locmodificador);
		this.getObjectListDataProvider().getList().add(locmodificador);

		this.mostrarEstadoObjetosUsados();
	}

	public String btnQuitarModificador_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getObjectListDataProvider().getList().remove(index);
					this.getCommunicationSAICBean().getMapaModificadores()
							.get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre()).clear();
					this.getCommunicationSAICBean().getMapaModificadores()
							.get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
							.addAll(this.getObjectListDataProvider().getList());
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	// public String btnQuitarTodosModificador_action(){
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	// if (ultimo) {
	//
	// // APLICAR LOGICA AQUI...
	// try {
	// this.getCommunicationSAICBean().getMapaModificadores().get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().
	// getTipoTasa().getTipoObligacion().getNombre()).clear();
	// this.getElementoPila().getObjetos().set(1, null);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	//
	// this.guardarEstadoObjetosUsados();
	// this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
	//
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	//
	// return retorno;
	// }

	public String btnQuitarAlicuotaLiquidada_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk;

			try {
				rk = this.getSeleccionado3();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getLdpAlicuotasLiquidadas().getObjects()[index];
					AlicuotaLiquidada alicuotaLiquidada = (AlicuotaLiquidada) obj;
					
					if(this.getLdpAlicuotasLiquidadas().getList().size() > 0) {
						this.getLdpAlicuotasLiquidadas().commitChanges();
					}
					
					List<LiquidacionTasa> listaLiquidaciones = (List<LiquidacionTasa>) this.obtenerObjetoDelElementoPila(0);
					listaLiquidaciones.get(0).getListaAlicuotasLiquidadas().remove(alicuotaLiquidada);
					
					this.getCommunicationSAICBean().setListaAlicuotasLiquidadas(new ArrayList(listaLiquidaciones.get(0).getListaAlicuotasLiquidadas()));
					this.getLdpAlicuotasLiquidadas().setList(this.getCommunicationSAICBean().getListaAlicuotasLiquidadas());
					
					this.getElementoPila().getObjetos().set(0, listaLiquidaciones);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void btnAgregarVencimiento_action() {
		this.getObjectListDataProviderVencimientos().commitChanges();
		this.guardarEstadoObjetosUsados();
		LiquidacionTasa liquidacionSeleccionada = this.getCommunicationSAICBean().getLiquidacionSeleccionada();
		SortedSet<Vencimiento> listaVencimientos = this.getCommunicationSAICBean().getMapaVencimientos().get(liquidacionSeleccionada.getTipoTasa().getTipoObligacion().getNombre());

		Vencimiento locVencimiento = new Vencimiento();
		locVencimiento.setNombre("");
		locVencimiento.setFecha(new Date());

		listaVencimientos.add(locVencimiento);
		this.getObjectListDataProviderVencimientos().getList().add(locVencimiento);

		this.mostrarEstadoObjetosUsados();
	}

	public String btnQuitarVencimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.getObjectListDataProviderVencimientos().commitChanges();
		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk;

			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderVencimientos().getObjects()[index];
					this.getObjectListDataProviderVencimientos().getList().remove(index);
					this.getCommunicationSAICBean().getMapaVencimientos()
							.get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre()).clear();
					this.getCommunicationSAICBean().getMapaVencimientos()
							.get(this.getCommunicationSAICBean().getLiquidacionSeleccionada().getTipoTasa().getTipoObligacion().getNombre())
							.addAll(this.getObjectListDataProviderVencimientos().getList());
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpModificadores().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpVencimientos().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado3() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup3");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpAlicuotasLiquidadas().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	@Override
	protected String getNombrePagina() {
		return "Liquidaci\363n";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMLiquidacion";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ArrayList()); // lista de liquidaciones seleccionadas

		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		List<LiquidacionTasa> listaLiquidaciones = (List) pObject;
		List listaNombres = new ArrayList();
		try {
			this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
			this.getCommunicationSAICBean().setMapaModificadores(new HashMap<String, Set>());
			this.getCommunicationSAICBean().setMapaVencimientos(new HashMap<String, SortedSet>());

			if(listaLiquidaciones.get(0) instanceof LiquidacionTasaAgrupada) {
				List<LiquidacionTasaAgrupada> listaAgrupadas = Util.castearLista(listaLiquidaciones);
				listaLiquidaciones.clear();
				for(LiquidacionTasaAgrupada cadaLiqAgrupada : listaAgrupadas) {
					for(LiquidacionTasa cadaLiq : cadaLiqAgrupada.getListaLiquidacionesTasa()) {
						listaLiquidaciones.add(cadaLiq);
					}
				}
			}

			for(LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
				listaNombres.add(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre());
				this.getCommunicationSAICBean().getMapaModificadores()
						.put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), cadaLiquidacion.getListaModificadoresLiquidacion());
				this.getCommunicationSAICBean().getMapaVencimientos().put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), cadaLiquidacion.getListaVencimientos());
			}

			Option[] op = null;
			op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(listaNombres.toArray(), "cap");
			ddTasasDefaultOptions.setOptions(op);

			this.getCommunicationSAICBean().setListaLiquidaciones(listaLiquidaciones);
			this.getDdTasas().setSelected(listaLiquidaciones.get(0).getTipoTasa().getTipoObligacion().getNombre());
			this.getCommunicationSAICBean().setLiquidacionSeleccionada(listaLiquidaciones.get(0));
			// this.seleccionarListaModificadores();

			this.getObjectListDataProvider().setList(
					new ArrayList(this.getCommunicationSAICBean().getMapaModificadores().get(listaLiquidaciones.get(0).getTipoTasa().getTipoObligacion().getNombre())));
			this.getObjectListDataProviderVencimientos().setList(
					new ArrayList(this.getCommunicationSAICBean().getMapaVencimientos().get(listaLiquidaciones.get(0).getTipoTasa().getTipoObligacion().getNombre())));
			
			this.getCommunicationSAICBean().setListaAlicuotasLiquidadas(new ArrayList(listaLiquidaciones.get(0).getListaAlicuotasLiquidadas()));
			this.getLdpAlicuotasLiquidadas().setList(new ArrayList(this.getCommunicationSAICBean().getListaAlicuotasLiquidadas()));

			this.getTfBasico().setText(listaLiquidaciones.get(0).getValor().toString());
			this.getTfIntereses().setText(listaLiquidaciones.get(0).getInteres().toString());

			this.getElementoPila().getObjetos().set(0, listaLiquidaciones);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ImprimirLiquidaciones$ABMLiquidacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
}