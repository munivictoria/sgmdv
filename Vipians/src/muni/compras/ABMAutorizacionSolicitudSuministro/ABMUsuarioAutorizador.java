/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.compras.ABMAutorizacionSolicitudSuministro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizador;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolicitudSuministro;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fer Luca
 */
public class ABMUsuarioAutorizador extends ABMPageBean {

	private Label lblUsuario = new Label();
	private Label lblEstados = new Label();

	private TextField tfUsuario = new TextField();
	private Table table1 = new Table();
	private Button btnAgregar = new Button();
	protected Button btnQuitar = new Button();
	protected Button btnQuitarTodos = new Button();
	private Button btnSeleccionarUsuario = new Button();
	private HtmlAjaxCommandButton btnLimpiarUsuario = new HtmlAjaxCommandButton();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpEstadosSolSum = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcDescripcion = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private StaticText stNombre = new StaticText();
	private StaticText stDescripcion = new StaticText();
	private PanelGroup groupPanel1 = new PanelGroup();
	private StaticText stSeparador1 = new StaticText();
	private DropDown ddEstadoFirmable = new DropDown();
	private SingleSelectOptionsList ddEstadoFirmableOptions = new SingleSelectOptionsList();
	private HtmlAjaxCommandButton btnAgregarEstadoFirmableSeleccionado = new HtmlAjaxCommandButton();

	private Label lblEstadoFinalizable = new Label();
	private Table tablaEstadoFinalizable = new Table();
	private TableRowGroup trgEstadoFinalizable = new TableRowGroup();
	private ObjectListDataProvider ldpEstadosSolSumFinalizables = new ObjectListDataProvider();
	private TableColumn tcRbEstadoFinalizable = new TableColumn();
	private TableColumn tcNombreEstadoFinalizable = new TableColumn();
	private TableColumn tcDescripcionEstadoFinalizable = new TableColumn();
	private RadioButton rbEstadoFinalizable = new RadioButton();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stNombreEstadoFinalizable = new StaticText();
	private StaticText stDescripcionEstadoFinalizable = new StaticText();
	private PanelGroup gpEstadoFinalizable = new PanelGroup();
	private DropDown ddEstadoFinalizable = new DropDown();
	private SingleSelectOptionsList ddEstadoFinalizableOptions = new SingleSelectOptionsList();
	private HtmlAjaxCommandButton btnAgregarEstadoFinalizableSeleccionado = new HtmlAjaxCommandButton();
	private Button btnAgregarEstadoFinalizable = new Button();
	private Button btnQuitarEstadoFinalizable = new Button();
	private Button btnQuitarTodosEstadoFinalizable = new Button();

	private Label lblEstadoFinalizacion = new Label();
	private Table tablaEstadoFinalizacion = new Table();
	private TableRowGroup trgEstadoFinalizacion = new TableRowGroup();
	private ObjectListDataProvider ldpEstadosSolSumFinalizacion = new ObjectListDataProvider();
	private TableColumn tcRbEstadoFinalizacion = new TableColumn();
	private TableColumn tcNombreEstadoFinalizacion = new TableColumn();
	private TableColumn tcDescripcionEstadoFinalizacion = new TableColumn();
	private RadioButton rbEstadoFinalizacion = new RadioButton();
	private StaticText stSeparador3 = new StaticText();
	private StaticText stNombreEstadoFinalizacion = new StaticText();
	private StaticText stDescripcionEstadoFinalizacion = new StaticText();
	private PanelGroup gpEstadoFinalizacion = new PanelGroup();
	private DropDown ddEstadoFinalizacion = new DropDown();
	private SingleSelectOptionsList ddEstadoFinalizacionOptions = new SingleSelectOptionsList();
	private HtmlAjaxCommandButton btnAgregarEstadoFinalizacionSeleccionado = new HtmlAjaxCommandButton();
	private Button btnAgregarEstadoFinalizacion = new Button();
	private Button btnQuitarEstadoFinalizacion = new Button();
	private Button btnQuitarTodosEstadoFinalizacion = new Button();
	private Label lblOperaUrgentes = new Label();
	private Checkbox cbOperaUrgentes = new Checkbox();

	public Label getLblOperaUrgentes() {
		return lblOperaUrgentes;
	}

	public void setLblOperaUrgentes(Label lblOperaUrgentes) {
		this.lblOperaUrgentes = lblOperaUrgentes;
	}

	public Checkbox getCbOperaUrgentes() {
		return cbOperaUrgentes;
	}

	public void setCbOperaUrgentes(Checkbox cbOperaUrgentes) {
		this.cbOperaUrgentes = cbOperaUrgentes;
	}

	public Label getLblEstadoFinalizable() {
		return lblEstadoFinalizable;
	}

	public void setLblEstadoFinalizable(Label lblEstadoFinalizable) {
		this.lblEstadoFinalizable = lblEstadoFinalizable;
	}

	public Table getTablaEstadoFinalizable() {
		return tablaEstadoFinalizable;
	}

	public void setTablaEstadoFinalizable(Table tablaEstadoFinalizable) {
		this.tablaEstadoFinalizable = tablaEstadoFinalizable;
	}

	public TableRowGroup getTrgEstadoFinalizable() {
		return trgEstadoFinalizable;
	}

	public void setTrgEstadoFinalizable(TableRowGroup trgEstadoFinalizable) {
		this.trgEstadoFinalizable = trgEstadoFinalizable;
	}

	public ObjectListDataProvider getLdpEstadosSolSumFinalizables() {
		return ldpEstadosSolSumFinalizables;
	}

	public void setLdpEstadosSolSumFinalizables(ObjectListDataProvider ldpEstadosSolSumFinalizables) {
		this.ldpEstadosSolSumFinalizables = ldpEstadosSolSumFinalizables;
	}

	public TableColumn getTcRbEstadoFinalizable() {
		return tcRbEstadoFinalizable;
	}

	public void setTcRbEstadoFinalizable(TableColumn tcRbEstadoFinalizable) {
		this.tcRbEstadoFinalizable = tcRbEstadoFinalizable;
	}

	public TableColumn getTcNombreEstadoFinalizable() {
		return tcNombreEstadoFinalizable;
	}

	public void setTcNombreEstadoFinalizable(TableColumn tcNombreEstadoFinalizable) {
		this.tcNombreEstadoFinalizable = tcNombreEstadoFinalizable;
	}

	public TableColumn getTcDescripcionEstadoFinalizable() {
		return tcDescripcionEstadoFinalizable;
	}

	public void setTcDescripcionEstadoFinalizable(TableColumn tcDescripcionEstadoFinalizable) {
		this.tcDescripcionEstadoFinalizable = tcDescripcionEstadoFinalizable;
	}

	public RadioButton getRbEstadoFinalizable() {
		return rbEstadoFinalizable;
	}

	public void setRbEstadoFinalizable(RadioButton rbEstadoFinalizable) {
		this.rbEstadoFinalizable = rbEstadoFinalizable;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public StaticText getStNombreEstadoFinalizable() {
		return stNombreEstadoFinalizable;
	}

	public void setStNombreEstadoFinalizable(StaticText stNombreEstadoFinalizable) {
		this.stNombreEstadoFinalizable = stNombreEstadoFinalizable;
	}

	public StaticText getStDescripcionEstadoFinalizable() {
		return stDescripcionEstadoFinalizable;
	}

	public void setStDescripcionEstadoFinalizable(StaticText stDescripcionEstadoFinalizable) {
		this.stDescripcionEstadoFinalizable = stDescripcionEstadoFinalizable;
	}

	public PanelGroup getGpEstadoFinalizable() {
		return gpEstadoFinalizable;
	}

	public void setGpEstadoFinalizable(PanelGroup gpEstadoFinalizable) {
		this.gpEstadoFinalizable = gpEstadoFinalizable;
	}

	public DropDown getDdEstadoFinalizable() {
		return ddEstadoFinalizable;
	}

	public void setDdEstadoFinalizable(DropDown ddEstadoFinalizable) {
		this.ddEstadoFinalizable = ddEstadoFinalizable;
	}

	public SingleSelectOptionsList getDdEstadoFinalizableOptions() {
		return ddEstadoFinalizableOptions;
	}

	public void setDdEstadoFinalizableOptions(SingleSelectOptionsList ddEstadoFinalizableOptions) {
		this.ddEstadoFinalizableOptions = ddEstadoFinalizableOptions;
	}

	public HtmlAjaxCommandButton getBtnAgregarEstadoFinalizableSeleccionado() {
		return btnAgregarEstadoFinalizableSeleccionado;
	}

	public void setBtnAgregarEstadoFinalizableSeleccionado(HtmlAjaxCommandButton btnAgregarEstadoFinalizableSeleccionado) {
		this.btnAgregarEstadoFinalizableSeleccionado = btnAgregarEstadoFinalizableSeleccionado;
	}

	public Button getBtnAgregarEstadoFinalizable() {
		return btnAgregarEstadoFinalizable;
	}

	public void setBtnAgregarEstadoFinalizable(Button btnAgregarEstadoFinalizable) {
		this.btnAgregarEstadoFinalizable = btnAgregarEstadoFinalizable;
	}

	public Button getBtnQuitarEstadoFinalizable() {
		return btnQuitarEstadoFinalizable;
	}

	public void setBtnQuitarEstadoFinalizable(Button btnQuitarEstadoFinalizable) {
		this.btnQuitarEstadoFinalizable = btnQuitarEstadoFinalizable;
	}

	public Button getBtnQuitarTodosEstadoFinalizable() {
		return btnQuitarTodosEstadoFinalizable;
	}

	public void setBtnQuitarTodosEstadoFinalizable(Button btnQuitarTodosEstadoFinalizable) {
		this.btnQuitarTodosEstadoFinalizable = btnQuitarTodosEstadoFinalizable;
	}

	public Label getLblEstadoFinalizacion() {
		return lblEstadoFinalizacion;
	}

	public void setLblEstadoFinalizacion(Label lblEstadoFinalizacion) {
		this.lblEstadoFinalizacion = lblEstadoFinalizacion;
	}

	public Table getTablaEstadoFinalizacion() {
		return tablaEstadoFinalizacion;
	}

	public void setTablaEstadoFinalizacion(Table tablaEstadoFinalizacion) {
		this.tablaEstadoFinalizacion = tablaEstadoFinalizacion;
	}

	public TableRowGroup getTrgEstadoFinalizacion() {
		return trgEstadoFinalizacion;
	}

	public void setTrgEstadoFinalizacion(TableRowGroup trgEstadoFinalizacion) {
		this.trgEstadoFinalizacion = trgEstadoFinalizacion;
	}

	public ObjectListDataProvider getLdpEstadosSolSumFinalizacion() {
		return ldpEstadosSolSumFinalizacion;
	}

	public void setLdpEstadosSolSumFinalizacion(ObjectListDataProvider ldpEstadosSolSumFinalizacion) {
		this.ldpEstadosSolSumFinalizacion = ldpEstadosSolSumFinalizacion;
	}

	public TableColumn getTcRbEstadoFinalizacion() {
		return tcRbEstadoFinalizacion;
	}

	public void setTcRbEstadoFinalizacion(TableColumn tcRbEstadoFinalizacion) {
		this.tcRbEstadoFinalizacion = tcRbEstadoFinalizacion;
	}

	public TableColumn getTcNombreEstadoFinalizacion() {
		return tcNombreEstadoFinalizacion;
	}

	public void setTcNombreEstadoFinalizacion(TableColumn tcNombreEstadoFinalizacion) {
		this.tcNombreEstadoFinalizacion = tcNombreEstadoFinalizacion;
	}

	public TableColumn getTcDescripcionEstadoFinalizacion() {
		return tcDescripcionEstadoFinalizacion;
	}

	public void setTcDescripcionEstadoFinalizacion(TableColumn tcDescripcionEstadoFinalizacion) {
		this.tcDescripcionEstadoFinalizacion = tcDescripcionEstadoFinalizacion;
	}

	public RadioButton getRbEstadoFinalizacion() {
		return rbEstadoFinalizacion;
	}

	public void setRbEstadoFinalizacion(RadioButton rbEstadoFinalizacion) {
		this.rbEstadoFinalizacion = rbEstadoFinalizacion;
	}

	public StaticText getStSeparador3() {
		return stSeparador3;
	}

	public void setStSeparador3(StaticText stSeparador3) {
		this.stSeparador3 = stSeparador3;
	}

	public StaticText getStNombreEstadoFinalizacion() {
		return stNombreEstadoFinalizacion;
	}

	public void setStNombreEstadoFinalizacion(StaticText stNombreEstadoFinalizacion) {
		this.stNombreEstadoFinalizacion = stNombreEstadoFinalizacion;
	}

	public StaticText getStDescripcionEstadoFinalizacion() {
		return stDescripcionEstadoFinalizacion;
	}

	public void setStDescripcionEstadoFinalizacion(StaticText stDescripcionEstadoFinalizacion) {
		this.stDescripcionEstadoFinalizacion = stDescripcionEstadoFinalizacion;
	}

	public PanelGroup getGpEstadoFinalizacion() {
		return gpEstadoFinalizacion;
	}

	public void setGpEstadoFinalizacion(PanelGroup gpEstadoFinalizacion) {
		this.gpEstadoFinalizacion = gpEstadoFinalizacion;
	}

	public DropDown getDdEstadoFinalizacion() {
		return ddEstadoFinalizacion;
	}

	public void setDdEstadoFinalizacion(DropDown ddEstadoFinalizacion) {
		this.ddEstadoFinalizacion = ddEstadoFinalizacion;
	}

	public SingleSelectOptionsList getDdEstadoFinalizacionOptions() {
		return ddEstadoFinalizacionOptions;
	}

	public void setDdEstadoFinalizacionOptions(SingleSelectOptionsList ddEstadoFinalizacionOptions) {
		this.ddEstadoFinalizacionOptions = ddEstadoFinalizacionOptions;
	}

	public HtmlAjaxCommandButton getBtnAgregarEstadoFinalizacionSeleccionado() {
		return btnAgregarEstadoFinalizacionSeleccionado;
	}

	public void setBtnAgregarEstadoFinalizacionSeleccionado(HtmlAjaxCommandButton btnAgregarEstadoFinalizacionSeleccionado) {
		this.btnAgregarEstadoFinalizacionSeleccionado = btnAgregarEstadoFinalizacionSeleccionado;
	}

	public Button getBtnAgregarEstadoFinalizacion() {
		return btnAgregarEstadoFinalizacion;
	}

	public void setBtnAgregarEstadoFinalizacion(Button btnAgregarEstadoFinalizacion) {
		this.btnAgregarEstadoFinalizacion = btnAgregarEstadoFinalizacion;
	}

	public Button getBtnQuitarEstadoFinalizacion() {
		return btnQuitarEstadoFinalizacion;
	}

	public void setBtnQuitarEstadoFinalizacion(Button btnQuitarEstadoFinalizacion) {
		this.btnQuitarEstadoFinalizacion = btnQuitarEstadoFinalizacion;
	}

	public Button getBtnQuitarTodosEstadoFinalizacion() {
		return btnQuitarTodosEstadoFinalizacion;
	}

	public void setBtnQuitarTodosEstadoFinalizacion(Button btnQuitarTodosEstadoFinalizacion) {
		this.btnQuitarTodosEstadoFinalizacion = btnQuitarTodosEstadoFinalizacion;
	}

	public DropDown getDdEstadoFirmable() {
		return ddEstadoFirmable;
	}

	public void setDdEstadoFirmable(DropDown ddEstadoFirmable) {
		this.ddEstadoFirmable = ddEstadoFirmable;
	}

	public SingleSelectOptionsList getDdEstadoFirmableOptions() {
		return ddEstadoFirmableOptions;
	}

	public void setDdEstadoFirmableOptions(SingleSelectOptionsList ddEstadosFirmablesOptions) {
		this.ddEstadoFirmableOptions = ddEstadosFirmablesOptions;
	}

	public HtmlAjaxCommandButton getBtnAgregarEstadoFirmableSeleccionado() {
		return btnAgregarEstadoFirmableSeleccionado;
	}

	public void setBtnAgregarEstadoFirmableSeleccionado(HtmlAjaxCommandButton btnAgregarEstadoFirmableSeleccionado) {
		this.btnAgregarEstadoFirmableSeleccionado = btnAgregarEstadoFirmableSeleccionado;
	}

	public HtmlAjaxCommandButton getBtnLimpiarUsuario() {
		return btnLimpiarUsuario;
	}

	public void setBtnLimpiarUsuario(HtmlAjaxCommandButton btnLimpiarUsuario) {
		this.btnLimpiarUsuario = btnLimpiarUsuario;
	}

	public Button getBtnSeleccionarUsuario() {
		return btnSeleccionarUsuario;
	}

	public void setBtnSeleccionarUsuario(Button btnSeleccionarUsuario) {
		this.btnSeleccionarUsuario = btnSeleccionarUsuario;
	}

	public Label getLblEstados() {
		return lblEstados;
	}

	public void setLblEstados(Label lblEstados) {
		this.lblEstados = lblEstados;
	}

	public Label getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(Label lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public ObjectListDataProvider getLdpEstadosSolSum() {
		return ldpEstadosSolSum;
	}

	public void setLdpEstadosSolSum(ObjectListDataProvider ldpEstadosSolSum) {
		this.ldpEstadosSolSum = ldpEstadosSolSum;
	}

	public StaticText getStDescripcion() {
		return stDescripcion;
	}

	public void setStDescripcion(StaticText stDescripcion) {
		this.stDescripcion = stDescripcion;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	public TextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(TextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public Button getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(Button btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public Button getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(Button btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public String getCurrentRowEstadoFinalizable() {
		return trgEstadoFinalizable.getRowKey().getRowId();
	}

	public void setCurrentRowEstadoFinalizable(int row) {
	}

	private Object lastSelectedEstadoFinalizable = null;

	public Object getRBSelectedEstadoFinalizable() {
		String sv = (String) rbEstadoFinalizable.getSelectedValue();
		return sv.equals(lastSelectedEstadoFinalizable) ? sv : null;
	}

	public void setRBSelectedEstadoFinalizable(Object selected) {
		if(selected != null) {
			lastSelectedEstadoFinalizable = selected;
		}
	}

	public RowKey getSeleccionadoEstadoFinalizable() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupEstadoFinalizable");
			rk = this.getObjectListDataProviderEstadoFinalizable().getRowKey(aRowId);
		} catch(Exception ex) {

		}
		return rk;
	}

	private ObjectListDataProvider getObjectListDataProviderEstadoFinalizable() {
		return this.getLdpEstadosSolSumFinalizables();
	}

	private List getListaDelCommunicationEstadoFinalizable() {
		return this.getCommunicationComprasBean().getListaEstadoSolSumFinalizable();
	}

	private void setListaDelCommunicationEstadoFinalizable(List lista) {
		this.getCommunicationComprasBean().setListaEstadoSolSumFinalizable(lista);
	}

	public String getCurrentRowEstadoFinalizacion() {
		return trgEstadoFinalizacion.getRowKey().getRowId();
	}

	public void setCurrentRowEstadoFinalizacion(int row) {
	}

	private Object lastSelectedEstadoFinalizacion = null;

	public Object getRBSelectedEstadoFinalizacion() {
		String sv = (String) rbEstadoFinalizacion.getSelectedValue();
		return sv.equals(lastSelectedEstadoFinalizacion) ? sv : null;
	}

	public void setRBSelectedEstadoFinalizacion(Object selected) {
		if(selected != null) {
			lastSelectedEstadoFinalizacion = selected;
		}
	}

	public RowKey getSeleccionadoEstadoFinalizacion() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupEstadoFinalizacion");
			rk = this.getObjectListDataProviderEstadoFinalizacion().getRowKey(aRowId);
		} catch(Exception ex) {

		}
		return rk;
	}

	private ObjectListDataProvider getObjectListDataProviderEstadoFinalizacion() {
		return this.getLdpEstadosSolSumFinalizacion();
	}

	private List getListaDelCommunicationEstadoFinalizacion() {
		return this.getCommunicationComprasBean().getListaEstadoSolSumFinalizacion();
	}

	private void setListaDelCommunicationEstadoFinalizacion(List lista) {
		this.getCommunicationComprasBean().setListaEstadoSolSumFinalizacion(lista);
	}

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
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {

		}
		return rk;
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpEstadosSolSum();
	}

	private List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaEstadosSolSum();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaEstadosSolSum(lista);
	}

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		if(this.getListaDelCommunicationEstadoFinalizable() != null) {
			this.getObjectListDataProviderEstadoFinalizable().setList(this.getListaDelCommunicationEstadoFinalizable());
		}

		if(this.getListaDelCommunicationEstadoFinalizacion() != null) {
			this.getObjectListDataProviderEstadoFinalizacion().setList(this.getListaDelCommunicationEstadoFinalizacion());
		}

		Set<String> locListaEstadosFirmables = this.getCommunicationComprasBean().getMapaEstadosFirmables().keySet();
		Option[] opEstadosFirmables = new Option[locListaEstadosFirmables.size() + 1];
		int i = 0;
		opEstadosFirmables[i++] = new Option("", "");
		for(String cadaEstado : locListaEstadosFirmables) {
			opEstadosFirmables[i++] = new Option(cadaEstado, cadaEstado);
		}
		ddEstadoFirmableOptions.setOptions(opEstadosFirmables);

		Set<String> locListaEstadosFinalizables = this.getCommunicationComprasBean().getMapaEstadosFinalizables().keySet();
		Option[] opEstadosFinalizables = new Option[locListaEstadosFinalizables.size() + 1];
		i = 0;
		opEstadosFinalizables[i++] = new Option("", "");
		for(String cadaEstado : locListaEstadosFinalizables) {
			opEstadosFinalizables[i++] = new Option(cadaEstado, cadaEstado);
		}
		ddEstadoFinalizableOptions.setOptions(opEstadosFinalizables);

		Set<String> locListaEstadosFinalizacion = this.getCommunicationComprasBean().getMapaEstadosFinalizacion().keySet();
		Option[] opEstadosFinalizacion = new Option[locListaEstadosFinalizacion.size() + 1];
		i = 0;
		opEstadosFinalizacion[i++] = new Option("", "");
		for(String cadaEstado : locListaEstadosFinalizacion) {
			opEstadosFinalizacion[i++] = new Option(cadaEstado, cadaEstado);
		}
		ddEstadoFinalizacionOptions.setOptions(opEstadosFinalizacion);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		UsuarioAutorizadorSolicitudSuministro usuarioAutorizador = (UsuarioAutorizadorSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++);
		List<EstadoSolicitudSuministro> listaEstados = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(ind++);
		List<EstadoSolicitudSuministro> listaEstadosFinalizables = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(ind++);
		List<EstadoSolicitudSuministro> listaEstadosFinalizacion = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(ind++);
		Usuario usuario = (Usuario) this.obtenerObjetoDelElementoPila(ind++);

		if(this.getTfUsuario().getText() != null && this.getTfUsuario().getText() != "") {
			usuarioAutorizador.setUsuario(usuario);
		} else {
			usuarioAutorizador.setUsuario(null);
		}

		this.getObjectListDataProvider().commitChanges();
		listaEstados = this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(listaEstados);
		usuarioAutorizador.setListaEstadosFirmables(listaEstados);

		this.getObjectListDataProviderEstadoFinalizable().commitChanges();
		listaEstadosFinalizables = this.getObjectListDataProviderEstadoFinalizable().getList();
		this.setListaDelCommunicationEstadoFinalizable(listaEstadosFinalizables);
		usuarioAutorizador.setListaEstadosFinalizables(listaEstadosFinalizables);

		this.getObjectListDataProviderEstadoFinalizacion().commitChanges();
		listaEstadosFinalizacion = this.getObjectListDataProviderEstadoFinalizacion().getList();
		this.setListaDelCommunicationEstadoFinalizacion(listaEstadosFinalizacion);
		usuarioAutorizador.setListaEstadosFinalizacion(listaEstadosFinalizacion);

		usuarioAutorizador.setOperaUrgentes(this.getCbOperaUrgentes().isChecked());

		this.getElementoPila().getObjetos().set(0, usuarioAutorizador);
		this.getElementoPila().getObjetos().set(1, listaEstados);
		this.getElementoPila().getObjetos().set(2, listaEstadosFinalizables);
		this.getElementoPila().getObjetos().set(3, listaEstadosFinalizacion);
		this.getElementoPila().getObjetos().set(4, usuario);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		UsuarioAutorizadorSolicitudSuministro usuarioAutorizador = (UsuarioAutorizadorSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++,
				UsuarioAutorizadorSolicitudSuministro.class);
		List<EstadoSolicitudSuministro> listaEstados = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List<EstadoSolicitudSuministro> listaEstadosFinalizables = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List<EstadoSolicitudSuministro> listaEstadosFinalizacion = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Usuario usuario = (Usuario) this.obtenerObjetoDelElementoPila(ind++, Usuario.class);

		this.getObjectListDataProvider().setList(listaEstados);
		this.setListaDelCommunication(listaEstados);
		usuarioAutorizador.setListaEstadosFirmables(listaEstados);

		this.getObjectListDataProviderEstadoFinalizable().setList(listaEstadosFinalizables);
		this.setListaDelCommunicationEstadoFinalizable(listaEstadosFinalizables);
		usuarioAutorizador.setListaEstadosFinalizables(listaEstadosFinalizables);

		this.getObjectListDataProviderEstadoFinalizacion().setList(listaEstadosFinalizacion);
		this.setListaDelCommunicationEstadoFinalizacion(listaEstadosFinalizacion);
		usuarioAutorizador.setListaEstadosFinalizacion(listaEstadosFinalizacion);

		this.getCbOperaUrgentes().setValue(usuarioAutorizador.isOperaUrgentes());

		if(usuario != null && usuario.getIdUsuario() != -1) {
			this.getTfUsuario().setText(usuario);
		}

		this.getElementoPila().getObjetos().set(0, usuarioAutorizador);
		this.getElementoPila().getObjetos().set(1, listaEstados);
		this.getElementoPila().getObjetos().set(2, listaEstadosFinalizables);
		this.getElementoPila().getObjetos().set(3, listaEstadosFinalizacion);
		this.getElementoPila().getObjetos().set(4, usuario);
	}

	public String btnSeleccionarUsuario_action() {
		return navegarParaSeleccionar("AdminUsuario");
	}

	public String btnLimpiarUsuario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(getTfUsuario());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getElementoPila().getObjetos().set(5, "Firmable");
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			retorno = "AdminEstadoSolicitudSuministro";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(obj);
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

	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunication().clear();
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

	public void btnAgregarEstadoFirmableSeleccionado_action() {
		this.guardarEstadoObjetosUsados();

		EstadoSolicitudSuministro locEstadoSeleccionado = this.getDDObjectValue(getDdEstadoFirmable(), this.getCommunicationComprasBean().getMapaEstadosFirmables());
		List<EstadoSolicitudSuministro> locListaActual = this.getLdpEstadosSolSum().getList();

		if(locEstadoSeleccionado != null) {

			if(!locListaActual.contains(locEstadoSeleccionado)) {
				// this.getListaDelCommunication().add(locEstadoSeleccionado);
				List listaAux = this.getLdpEstadosSolSum().getList();
				listaAux.add(locEstadoSeleccionado);
				this.getLdpEstadosSolSum().setList(listaAux);
			}
		}

	}

	public String btnAgregarEstadoFinalizable_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getElementoPila().getObjetos().set(5, "Finalizable");
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			retorno = "AdminEstadoSolicitudSuministro";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarEstadoFinalizable_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionadoEstadoFinalizable();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderEstadoFinalizable().getObjects()[index];
					this.getListaDelCommunicationEstadoFinalizable().remove(obj);
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

	public String btnQuitarTodosEstadoFinalizable_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationEstadoFinalizable().clear();
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

	public void btnAgregarEstadoFinalizableSeleccionado_action() {
		this.guardarEstadoObjetosUsados();

		EstadoSolicitudSuministro locEstadoSeleccionado = this.getDDObjectValue(getDdEstadoFinalizable(), this.getCommunicationComprasBean().getMapaEstadosFinalizables());
		List<EstadoSolicitudSuministro> locListaActual = this.getLdpEstadosSolSumFinalizables().getList();

		if(locEstadoSeleccionado != null) {

			if(!locListaActual.contains(locEstadoSeleccionado)) {
				// this.getListaDelCommunicationEstadoFinalizable().add(locEstadoSeleccionado);

				List listaAux = this.getLdpEstadosSolSumFinalizables().getList();
				listaAux.add(locEstadoSeleccionado);
				this.getLdpEstadosSolSumFinalizables().setList(listaAux);
			}
		}

	}

	public String btnAgregarEstadoFinalizacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getElementoPila().getObjetos().set(5, "Finalizacion");
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			retorno = "AdminEstadoSolicitudSuministro";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarEstadoFinalizacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionadoEstadoFinalizacion();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderEstadoFinalizacion().getObjects()[index];
					this.getListaDelCommunicationEstadoFinalizacion().remove(obj);
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

	public String btnQuitarTodosEstadoFinalizacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationEstadoFinalizacion().clear();
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

	public void btnAgregarEstadoFinalizacionSeleccionado_action() {
		this.guardarEstadoObjetosUsados();

		EstadoSolicitudSuministro locEstadoSeleccionado = this.getDDObjectValue(getDdEstadoFinalizacion(), this.getCommunicationComprasBean().getMapaEstadosFinalizacion());
		List<EstadoSolicitudSuministro> locListaActual = this.getLdpEstadosSolSumFinalizacion().getList();

		if(locEstadoSeleccionado != null) {

			if(!locListaActual.contains(locEstadoSeleccionado)) {
				// this.getListaDelCommunicationEstadoFinalizacion().add(locEstadoSeleccionado);
				// this.getLdpEstadosSolSumFinalizacion().setList(getListaDelCommunicationEstadoFinalizacion());

				List listaAux = this.getLdpEstadosSolSumFinalizacion().getList();
				listaAux.add(locEstadoSeleccionado);
				this.getLdpEstadosSolSumFinalizacion().setList(listaAux);
			}
		}

	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMUsuarioAutorizador";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new UsuarioAutorizadorSolicitudSuministro());
		ep.getObjetos().add(ind++, new ArrayList<EstadoSolicitudSuministro>());
		ep.getObjetos().add(ind++, new ArrayList<EstadoSolicitudSuministro>()); // estados finalizables
		ep.getObjetos().add(ind++, new ArrayList<EstadoSolicitudSuministro>()); // estados de finalizacion
		ep.getObjetos().add(ind++, new Usuario());
		ep.getObjetos().add(ind++, null); // para saber a que lista de estados se quiere agregar

		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		UsuarioAutorizadorSolicitudSuministro usuarioAutorizador;
		Usuario usuario;

		if(pObject instanceof Usuario) {
			usuario = (Usuario) pObject;

			usuarioAutorizador = (UsuarioAutorizadorSolicitudSuministro) this.obtenerObjetoDelElementoPila(0, UsuarioAutorizadorSolicitudSuministro.class);

			usuarioAutorizador.setUsuario(usuario);
			this.getElementoPila().getObjetos().set(0, usuarioAutorizador);
			this.getElementoPila().getObjetos().set(4, usuario);
			this.getRequestBean1().setObjetoSeleccion(null);
		}

		if(pObject instanceof EstadoSolicitudSuministro) {
			EstadoSolicitudSuministro estadoSeleccionado = (EstadoSolicitudSuministro) pObject;

			usuarioAutorizador = (UsuarioAutorizadorSolicitudSuministro) this.obtenerObjetoDelElementoPila(0, UsuarioAutorizadorSolicitudSuministro.class);
			Object seleccion = this.obtenerObjetoDelElementoPila(5);
			if(seleccion.equals("Firmable")) {
				if(usuarioAutorizador.getListaEstadosFirmables() == null) {
					usuarioAutorizador.setListaEstadosFirmables(new ArrayList<EstadoSolicitudSuministro>());
				}

				boolean yaExiste = false;
				List<EstadoSolicitudSuministro> listaEstados = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
				for(EstadoSolicitudSuministro cadaEstado : listaEstados) {
					if(cadaEstado.equals(estadoSeleccionado)) {
						yaExiste = true;
						warn("El Estado Solicitud Suministro seleccionado ya se encuentra en la lista");
					}
				}
				if(!yaExiste) {
					listaEstados.add(estadoSeleccionado);
					this.getElementoPila().getObjetos().set(1, listaEstados);
				}
			} else if(seleccion.equals("Finalizable")) {
				if(usuarioAutorizador.getListaEstadosFinalizables() == null) {
					usuarioAutorizador.setListaEstadosFinalizables(new ArrayList<EstadoSolicitudSuministro>());
				}

				boolean yaExiste = false;
				List<EstadoSolicitudSuministro> listaEstados = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
				for(EstadoSolicitudSuministro cadaEstado : listaEstados) {
					if(cadaEstado.equals(estadoSeleccionado)) {
						yaExiste = true;
						warn("El Estado Solicitud Suministro seleccionado ya se encuentra en la lista");
					}
				}
				if(!yaExiste) {
					listaEstados.add(estadoSeleccionado);
					this.getElementoPila().getObjetos().set(2, listaEstados);
				}
			} else if(seleccion.equals("Finalizacion")) {
				if(usuarioAutorizador.getListaEstadosFinalizacion() == null) {
					usuarioAutorizador.setListaEstadosFinalizacion(new ArrayList<EstadoSolicitudSuministro>());
				}

				boolean yaExiste = false;
				List<EstadoSolicitudSuministro> listaEstados = (List<EstadoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
				for(EstadoSolicitudSuministro cadaEstado : listaEstados) {
					if(cadaEstado.equals(estadoSeleccionado)) {
						yaExiste = true;
						warn("El Estado Solicitud Suministro seleccionado ya se encuentra en la lista");
					}
				}
				if(!yaExiste) {
					listaEstados.add(estadoSeleccionado);
					this.getElementoPila().getObjetos().set(3, listaEstados);
				}
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		UsuarioAutorizadorSolicitudSuministro usuarioAutorizador = (UsuarioAutorizadorSolicitudSuministro) pObject;
		List listaEstados = usuarioAutorizador.getListaEstadosFirmables();
		List listaEstadosFinalizables = usuarioAutorizador.getListaEstadosFinalizables();
		List listaEstadosFinalizacion = usuarioAutorizador.getListaEstadosFinalizacion();
		Usuario usuario = usuarioAutorizador.getUsuario();

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, usuarioAutorizador);
		this.getElementoPila().getObjetos().set(ind++, listaEstados);
		this.getElementoPila().getObjetos().set(ind++, listaEstadosFinalizables);
		this.getElementoPila().getObjetos().set(ind++, listaEstadosFinalizacion);
		this.getElementoPila().getObjetos().set(ind++, usuario);
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador}";
	}

	@Override
	public long getSerialVersionUID() {
		return UsuarioAutorizador.getSerialversionuid();
	}
}