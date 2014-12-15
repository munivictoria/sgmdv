/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.saic.ImprimirLiquidaciones;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;

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
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.Vencimiento;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

/**
 * 
 * @author Fer Luca
 */
public class ModificarVarias extends ABMPageBean {

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
	private Button btnQuitarTodosVencimiento = new Button();
	private PanelGroup groupPanel1 = new PanelGroup();
	private PanelGroup groupPanel2 = new PanelGroup();
	private TableColumn tcValorVencimiento = new TableColumn();
	private TextField tfValorVencimiento = new TextField();
	private Label lblComentario = new Label();
	private TextArea taComentario = new TextArea();
	
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

	public Button getBtnQuitarTodosVencimiento() {
		return btnQuitarTodosVencimiento;
	}

	public void setBtnQuitarTodosVencimiento(Button btnQuitarTodosVencimiento) {
		this.btnQuitarTodosVencimiento = btnQuitarTodosVencimiento;
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

	// public DateTimeConverter getDateTimeConverter1() {
	// return dateTimeConverter1;
	// }
	//
	// public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
	// this.dateTimeConverter1 = dateTimeConverter1;
	// }

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

	public String getSeleccionarListas() {
		System.out.println("seleccionarListas()");
		this.getObjectListDataProvider().commitChanges();
		this.getObjectListDataProviderVencimientos().commitChanges();

		if(this.getCommunicationSAICBean().getTasaSeleccionada() != null && !this.getCommunicationSAICBean().getTasaSeleccionada().equals("")) {
			String tasaSeleccionada = this.getCommunicationSAICBean().getTasaSeleccionada();
			if(this.getTfBasico().getText() != null && this.getTfBasico().getText() != "" && !this.getTfBasico().getText().equals("diferentes valores")) {
				this.getCommunicationSAICBean().getMapaBasicosLiq().put(tasaSeleccionada, this.getTfBasico().getText().toString());
			}

			if(this.getTfIntereses().getText() != null && this.getTfIntereses().getText() != "" && !this.getTfIntereses().getText().equals("diferentes valores")) {
				this.getCommunicationSAICBean().getMapaInteresesLiq().put(tasaSeleccionada, this.getTfIntereses().getText().toString());
			}

			this.getCommunicationSAICBean().getMapaModificadoresLiq().put(tasaSeleccionada, new HashSet(this.getObjectListDataProvider().getList()));
			this.getCommunicationSAICBean().getMapaVencimientosLiq().put(tasaSeleccionada, new TreeSet(this.getObjectListDataProviderVencimientos().getList()));
		}

		if(this.getDdTasas().getSelected() != null && this.getDdTasas().getSelected() != "") {
			String nombreTasa = this.getDdTasas().getSelected().toString();
			Set<ModificadorEnTabla> listaMods = this.getCommunicationSAICBean().getMapaModificadoresLiq().get(nombreTasa);
			this.getObjectListDataProvider().setList(new ArrayList(listaMods));
			this.getObjectListDataProviderVencimientos().setList(new ArrayList(this.getCommunicationSAICBean().getMapaVencimientosLiq().get(nombreTasa)));
			this.getTfBasico().setText(this.getCommunicationSAICBean().getMapaBasicosLiq().get(nombreTasa));
			this.getTfIntereses().setText(this.getCommunicationSAICBean().getMapaInteresesLiq().get(nombreTasa));
			this.getCommunicationSAICBean().setTasaSeleccionada(nombreTasa);
		} else {
			this.getObjectListDataProvider().setList(new ArrayList());
			this.getObjectListDataProviderVencimientos().setList(new ArrayList());
			this.getTfBasico().setText("");
			this.getTfIntereses().setText("");
			this.getCommunicationSAICBean().setTasaSeleccionada(null);
		}

		return "this.form.submit()";
	}

	public void setSeleccionarListas() {

	}

	@Override
	protected void _init() throws Exception {
		System.out.println("_init()");
		// this.getDateTimeConverter1().setPattern("dd/MM/yyyy");
		// this.getDateTimeConverter1().setTimeZone(TimeZone.getDefault());

		if(this.getCommunicationSAICBean().getListaNombresTasas() != null) {
			Option[] op = null;
			op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getCommunicationSAICBean().getListaNombresTasas().toArray(), "cap");
			ddTasasDefaultOptions.setOptions(op);
		}

		if(this.getCommunicationSAICBean().getTasaSeleccionada() != null && !this.getCommunicationSAICBean().getTasaSeleccionada().equals("")) {
			String nombreTasa = this.getCommunicationSAICBean().getTasaSeleccionada();
			this.getObjectListDataProvider().setList(new ArrayList(this.getCommunicationSAICBean().getMapaModificadoresLiq().get(nombreTasa)));
			this.getObjectListDataProviderVencimientos().setList(new ArrayList(this.getCommunicationSAICBean().getMapaVencimientosLiq().get(nombreTasa)));
			System.out.println("SETEO LDP (_init)");
		}
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		this.getObjectListDataProvider().commitChanges();
		this.getObjectListDataProviderVencimientos().commitChanges();

		if(this.getCommunicationSAICBean().getTasaSeleccionada() != null && !this.getCommunicationSAICBean().getTasaSeleccionada().equals("")) {
			String tasaSeleccionada = this.getCommunicationSAICBean().getTasaSeleccionada();
			if(this.getTfBasico().getText() != null && this.getTfBasico().getText() != "" && !this.getTfBasico().getText().equals("diferentes valores")) {
				this.getCommunicationSAICBean().getMapaBasicosLiq().put(tasaSeleccionada, this.getTfBasico().getText().toString());
			}

			if(this.getTfIntereses().getText() != null && this.getTfIntereses().getText() != "" && !this.getTfIntereses().getText().equals("diferentes valores")) {
				this.getCommunicationSAICBean().getMapaInteresesLiq().put(tasaSeleccionada, this.getTfIntereses().getText().toString());
			}

			this.getCommunicationSAICBean().getMapaModificadoresLiq().put(tasaSeleccionada, new HashSet(this.getObjectListDataProvider().getList()));
			this.getCommunicationSAICBean().getMapaVencimientosLiq().put(tasaSeleccionada, new TreeSet(this.getObjectListDataProviderVencimientos().getList()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		System.out.println("mostrarEstadoObjetosUsados()");
	}

	public void btnAgregarModificador_action() {
		this.guardarEstadoObjetosUsados();

		ModificadorEnTabla locmodificador = new ModificadorEnTabla();
		locmodificador.setNombre("");
		locmodificador.setValorModificador("0.0");
		locmodificador.setListaIds(new HashSet<Long>());
		locmodificador.setNuevo(true);
		this.getObjectListDataProvider().getList().add(locmodificador);
		this.getCommunicationSAICBean().getMapaModificadoresLiq()
				.put(this.getCommunicationSAICBean().getTasaSeleccionada(), new HashSet<ModificadorEnTabla>(this.getObjectListDataProvider().getList()));

		this.guardarEstadoObjetosUsados();
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
					this.getObjectListDataProvider().getList().remove(index);
					this.getCommunicationSAICBean().getMapaModificadoresLiq()
							.put(this.getCommunicationSAICBean().getTasaSeleccionada(), new HashSet<ModificadorEnTabla>(this.getObjectListDataProvider().getList()));
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

		this.guardarEstadoObjetosUsados();
		LiquidacionTasa liquidacionSeleccionada = this.getCommunicationSAICBean().getLiquidacionSeleccionada();

		VencimientoEnTabla locVencimiento = new VencimientoEnTabla();
		locVencimiento.setListaIds(new TreeSet<Long>());
		locVencimiento.setLiquidacion(liquidacionSeleccionada);
		locVencimiento.setNombre("");
		locVencimiento.setFecha(null);
		locVencimiento.setNuevo(true);

		this.getObjectListDataProviderVencimientos().getList().add(locVencimiento);
		this.getCommunicationSAICBean().getMapaVencimientosLiq()
				.put(this.getCommunicationSAICBean().getTasaSeleccionada(), new TreeSet<VencimientoEnTabla>(this.getObjectListDataProviderVencimientos().getList()));

		this.guardarEstadoObjetosUsados();
	}

	public String btnQuitarVencimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk;

			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					this.getObjectListDataProviderVencimientos().getList().remove(index);
					this.getCommunicationSAICBean().getMapaVencimientosLiq()
							.put(this.getCommunicationSAICBean().getTasaSeleccionada(), new TreeSet(this.getObjectListDataProviderVencimientos().getList()));
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

	public String btnQuitarTodosVencimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			this.getObjectListDataProviderVencimientos().getList().clear();
			this.getCommunicationSAICBean().getMapaVencimientosLiq().put(this.getCommunicationSAICBean().getTasaSeleccionada(), new TreeSet());

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
			rk = this.getLdpModificadores().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getLdpVencimientos().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	@Override
	protected String getNombrePagina() {
		return "Liquidaciones";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ModificarVarias";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ArrayList()); // lista de liquidaciones

		return ep;
	}

	public class ModificadorEnTabla implements Comparable<ModificadorLiquidacion> {
		private String nombre;
		private String valorModificador;
		private LiquidacionTasa liquidacionTasa;
		private Set<Long> listaIds;
		private boolean nuevo;

		public Set<Long> getListaIds() {
			return listaIds;
		}

		public void setListaIds(Set<Long> listaIds) {
			this.listaIds = listaIds;
		}

		public boolean isNuevo() {
			return nuevo;
		}

		public void setNuevo(boolean nuevo) {
			this.nuevo = nuevo;
		}

		public LiquidacionTasa getLiquidacionTasa() {
			return liquidacionTasa;
		}

		public void setLiquidacionTasa(LiquidacionTasa liquidacionTasa) {
			this.liquidacionTasa = liquidacionTasa;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getValorModificador() {
			return valorModificador;
		}

		public void setValorModificador(String valorModificador) {
			this.valorModificador = valorModificador;
		}

		@Override
		public int compareTo(ModificadorLiquidacion o) {
			if(this.getListaIds().contains(o.getIdModificadorLiquidacion())) {
				return 0;
			}
			return -1;
		}

		public int compareTo(VencimientoEnTabla o) {
			if(this.getListaIds().equals(o.getListaIds())) { // antes tenia containsAll()
				return 0;
			}
			return -1;
		}
	}

	public class VencimientoEnTabla implements Comparable<VencimientoEnTabla> {
		private String nombre;
		private String fecha;
		private Double valor;
		private Integer numero;
		private LiquidacionTasa liquidacion;
		private Set<Long> listaIds;
		private boolean nuevo;

		public Set<Long> getListaIds() {
			return listaIds;
		}

		public void setListaIds(Set<Long> listaIds) {
			this.listaIds = listaIds;
		}

		public boolean isNuevo() {
			return nuevo;
		}

		public void setNuevo(boolean nuevo) {
			this.nuevo = nuevo;
		}

		public LiquidacionTasa getLiquidacion() {
			return liquidacion;
		}

		public void setLiquidacion(LiquidacionTasa liquidacion) {
			this.liquidacion = liquidacion;
		}

		public Integer getNumero() {
			return numero;
		}

		public void setNumero(Integer numero) {
			this.numero = numero;
		}

		public Double getValor() {
			return valor;
		}

		public void setValor(Double valor) {
			this.valor = valor;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public int compareTo(Vencimiento o) {
			if(this.getListaIds().contains(o.getIdVencimiento())) {
				return 0;
			}
			return -1;
		}

		@Override
		public int compareTo(VencimientoEnTabla o) {
			if(this.getListaIds().equals(o.getListaIds())) { // antes tenia containsAll()
				return 0;
			}
			return -1;
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		List<LiquidacionTasa> listaLiquidaciones = (List) pObject;

		this.getCommunicationSAICBean().setMapaModificadoresLiq(new HashMap());
		this.getCommunicationSAICBean().setMapaVencimientosLiq(new HashMap());
		this.getCommunicationSAICBean().setMapaBasicosLiq(new HashMap<String, String>());
		this.getCommunicationSAICBean().setMapaInteresesLiq(new HashMap<String, String>());

		Map<String, List<Double>> mapaAuxiliarBasicos = new HashMap<String, List<Double>>();// para verificar si los Basicos de todas las liq de cierta tasa son
																							// = o !=
		Map<String, List<Double>> mapaAuxiliarInteres = new HashMap<String, List<Double>>();// para verificar si los Intereses de todas las liq de cierta tasa
																							// son = o !=

		Set listaNombresTasas = new HashSet();

		try {
			this.getCommunicationSAICBean().setListaLiquidaciones(new ArrayList<LiquidacionTasa>());
			this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());

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
				this.getCommunicationSAICBean().getListaLiquidaciones().add(cadaLiquidacion);
				listaNombresTasas.add(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre());

				if(!this.getCommunicationSAICBean().getMapaModificadoresLiq().containsKey(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre())) {
					this.getCommunicationSAICBean().getMapaModificadoresLiq().put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), new HashSet<ModificadorEnTabla>());
					// System.out.println("METIO " + liq.getTipoTasa().getTipoObligacion().getNombre() + " A LA LISTA");
				}

				Set<ModificadorEnTabla> listaModsCommunication = this.getCommunicationSAICBean().getMapaModificadoresLiq()
						.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre());
				for(ModificadorLiquidacion cadaMod : cadaLiquidacion.getListaModificadoresLiquidacion()) {
					boolean yaExiste = false;
					for(ModificadorEnTabla cadaModCom : listaModsCommunication) {
						if(cadaMod.getNombre().equals(cadaModCom.getNombre())) {
							yaExiste = true;
							cadaModCom.getListaIds().add(cadaMod.getIdModificadorLiquidacion());

							if(!cadaMod.getValorModificador().toString().equals(cadaModCom.getValorModificador())) {
								cadaModCom.setValorModificador("diferentes valores");
							}
						}
					}

					if(!yaExiste) {
						ModificadorEnTabla modTabla = new ModificadorEnTabla();
						modTabla.setNombre(cadaMod.getNombre());
						modTabla.setListaIds(new HashSet<Long>());
						modTabla.getListaIds().add(cadaMod.getIdModificadorLiquidacion());
						modTabla.setLiquidacionTasa(cadaMod.getLiquidacionTasa());

						modTabla.setValorModificador(cadaMod.getValorModificador().toString());

						listaModsCommunication.add(modTabla);
					}
				}

				if(!this.getCommunicationSAICBean().getMapaVencimientosLiq().containsKey(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre())) {
					this.getCommunicationSAICBean().getMapaVencimientosLiq().put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), new TreeSet<VencimientoEnTabla>());
				}

				SortedSet<VencimientoEnTabla> listaVencCommunication = this.getCommunicationSAICBean().getMapaVencimientosLiq()
						.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre());
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone(TimeZone.getDefault());
				sdf.applyPattern("dd/MM/yyyy");
				for(Vencimiento cadaVenc : cadaLiquidacion.getListaVencimientos()) {
					boolean yaExiste = false;
					for(VencimientoEnTabla cadaVencCom : listaVencCommunication) {
						if(cadaVenc.getNombre() != null && cadaVenc.getNombre().equals(cadaVencCom.getNombre())) {
							yaExiste = true;
							cadaVencCom.getListaIds().add(cadaVenc.getIdVencimiento());

							// Comentado
							// if (!sdf.format(cadaVenc.getFecha()).equals(cadaVencCom.getFecha())) {
							// cadaVencCom.setFecha("diferentes valores");
							// }
						}
					}

					if(!yaExiste) {
						VencimientoEnTabla vencTabla = new VencimientoEnTabla();
						vencTabla.setNombre(cadaVenc.getNombre());
						vencTabla.setListaIds(new HashSet<Long>());
						vencTabla.getListaIds().add(cadaVenc.getIdVencimiento());
						vencTabla.setLiquidacion(cadaLiquidacion);
						vencTabla.setNumero(cadaVenc.getNumero());
						vencTabla.setValor(cadaVenc.getValor());

						// Comentado
						// vencTabla.setFecha(sdf.format(cadaVenc.getFecha()));
						listaVencCommunication.add(vencTabla);
					}
				}

				boolean sonDiferentes = false;

				if(!mapaAuxiliarBasicos.containsKey(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre())) {
					mapaAuxiliarBasicos.put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), new ArrayList<Double>());
				}
				mapaAuxiliarBasicos.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre()).add(cadaLiquidacion.getValor());

				sonDiferentes = false;
				if(mapaAuxiliarBasicos.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre()).get(0) != null) {
					Double unBasico = mapaAuxiliarBasicos.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre()).get(0);
					for(Double cadaBasico : mapaAuxiliarBasicos.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre())) {
						if(!unBasico.equals(cadaBasico)) {
							sonDiferentes = true;
						}
					}

					if(sonDiferentes) {
						this.getCommunicationSAICBean().getMapaBasicosLiq().put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), "diferentes valores");
					} else {
						this.getCommunicationSAICBean().getMapaBasicosLiq().put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), cadaLiquidacion.getValor().toString());
					}
				}

				if(!mapaAuxiliarInteres.containsKey(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre())) {
					mapaAuxiliarInteres.put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), new ArrayList<Double>());
				}
				mapaAuxiliarInteres.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre()).add(cadaLiquidacion.getInteres());

				sonDiferentes = false;
				if(mapaAuxiliarInteres.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre()).get(0) != null) {
					Double unInteres = mapaAuxiliarInteres.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre()).get(0);
					for(Double cadaInteres : mapaAuxiliarInteres.get(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre())) {
						if(!unInteres.equals(cadaInteres)) {
							sonDiferentes = true;
						}
					}

					if(sonDiferentes) {
						this.getCommunicationSAICBean().getMapaInteresesLiq().put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), "diferentes valores");
					} else {
						this.getCommunicationSAICBean().getMapaInteresesLiq()
								.put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), cadaLiquidacion.getInteres().toString());
					}
				}

				if(!this.getCommunicationSAICBean().getMapaInteresesLiq().containsKey(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre())) {
					this.getCommunicationSAICBean().getMapaInteresesLiq().put(cadaLiquidacion.getTipoTasa().getTipoObligacion().getNombre(), "diferentes valores");
				}
			}
			this.getCommunicationSAICBean().setListaNombresTasas(listaNombresTasas);
			Option[] op = null;
			op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(listaNombresTasas.toArray(), "cap");
			ddTasasDefaultOptions.setOptions(op);

			String seleccionada = listaNombresTasas.toArray()[0].toString();
			this.getDdTasas().setSelected(seleccionada);
			this.getObjectListDataProvider().setList(new ArrayList(this.getCommunicationSAICBean().getMapaModificadoresLiq().get(seleccionada)));
			this.getObjectListDataProviderVencimientos().setList(new ArrayList(this.getCommunicationSAICBean().getMapaVencimientosLiq().get(seleccionada)));
			this.getTfBasico().setText(this.getCommunicationSAICBean().getMapaBasicosLiq().get(seleccionada));
			this.getTfIntereses().setText(this.getCommunicationSAICBean().getMapaInteresesLiq().get(seleccionada));
			this.getCommunicationSAICBean().setTasaSeleccionada(seleccionada);
		} catch(Exception e) {
			e.printStackTrace();
		}

		this.getElementoPila().getObjetos().set(0, listaLiquidaciones);
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ImprimirLiquidaciones$ModificarVarias}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
}