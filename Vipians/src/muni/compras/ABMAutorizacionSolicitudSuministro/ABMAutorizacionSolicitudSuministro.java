/*
 * AgregarTipoOrdenCompra.java
 *
 * Created on 2 de noviembre de 2006, 11:21
 * Copyright Trascender SRL
 */
package muni.compras.ABMAutorizacionSolicitudSuministro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
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
import com.trascender.compras.recurso.persistent.suministros.AutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.ReglaFirmaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizador;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolSumSuplente;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolicitudSuministro;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.Periodicidad;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMAutorizacionSolicitudSuministro extends ABMPageBean {

	private StaticText stSeparador1 = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stUsuario = new StaticText();
	private StaticText stUsuarioSuplente = new StaticText();
	private StaticText stUsuarioSuplido = new StaticText();
	private StaticText stOrden = new StaticText();
	private StaticText stEstado = new StaticText();
	private StaticText stListaUsuarios = new StaticText();

	private Label lblPeriodoNumero = new Label();
	private Label lblPeriodo = new Label();
	private Label lblUsuario = new Label();
	private Label lblPeriodicidad = new Label();

	private Label lblArea = new Label();
	private Label lblUsuariosSuplentes = new Label();
	private Label lblReglasFirmas = new Label();

	private TextField tfPeriodoNumero = new TextField();

	private TextField tfMontoMaximo = new TextField();
	private TextField tfMontoMinimo = new TextField();

	private Button btnAgregar = new Button();
	private Button btnModificar = new Button();
	private Button btnConsultar = new Button();
	protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
	private Button btnAgregarSuplente = new Button();
	private Button btnModificarSuplente = new Button();
	private Button btnConsultarSuplente = new Button();
	protected HtmlAjaxCommandButton btnQuitarTodosSuplente = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarSuplente = new HtmlAjaxCommandButton();
	private Button btnSeleccionarArea = new Button();
	private Button btnLimpiarArea = new Button();
	private Button btnAgregarRegla = new Button();
	protected Button btnQuitarTodosRegla = new Button();
	protected Button btnQuitarRegla = new Button();
	private Button btnConsultarRegla = new Button();
	private Button btnModificarRegla = new Button();

	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcUsuarioSuplente = new TableColumn();
	private TableColumn tcUsuarioSuplido = new TableColumn();
	private TableColumn tcOrden = new TableColumn();
	private TableColumn tcEstado = new TableColumn();
	private TableColumn tcListaUsuarios = new TableColumn();

	private Table table1 = new Table();
	private Table table2 = new Table();
	private Table table3 = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private TableRowGroup tableRowGroup3 = new TableRowGroup();
	private PanelGroup groupPanel1 = new PanelGroup();
	private PanelGroup groupPanel2 = new PanelGroup();
	private PanelGroup groupPanel3 = new PanelGroup();
	private RadioButton radioButton1 = new RadioButton();
	private RadioButton radioButton2 = new RadioButton();
	private RadioButton radioButton3 = new RadioButton();

	private DropDown ddPeriodicidad = new DropDown();
	private SingleSelectOptionsList ddPeriodicidadDefaultOptions = new SingleSelectOptionsList();
	private ObjectListDataProvider LdpUsuariosAutorizadores = new ObjectListDataProvider();
	private ObjectListDataProvider ldpUsuariosSuplentes = new ObjectListDataProvider();
	private ObjectListDataProvider ldpReglasFirmas = new ObjectListDataProvider();
	protected SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();
	protected DropDown ddArea = new DropDown();

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	@Override
	protected void _init() throws Exception {
		this.getDateTimeConverter1().setPattern("dd/MM/yyyy");
		this.getDateTimeConverter1().setTimeZone(TimeZone.getDefault());

		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Periodicidad.values(), "cap");
		ddPeriodicidadDefaultOptions.setOptions(op);
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		this.getObjectListDataProviderUsuariosSuplentes().setList(this.getListaDelCommunicationUsuariosSuplentes());
		this.getObjectListDataProviderReglasFirmas().setList(this.getListaDelCommunicationReglasFirmas());
		Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasSolSum().keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		int i = 0;
		opAreas[i++] = new Option("", "");
		for(String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
		
		getTable1().setClearSortButton(true);
		getTable2().setClearSortButton(true);
		getTable3().setClearSortButton(true);
		
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public StaticText getStListaUsuarios() {
		return stListaUsuarios;
	}

	public void setStListaUsuarios(StaticText stListaUsuarios) {
		this.stListaUsuarios = stListaUsuarios;
	}

	public TableColumn getTcListaUsuarios() {
		return tcListaUsuarios;
	}

	public void setTcListaUsuarios(TableColumn tcListaUsuarios) {
		this.tcListaUsuarios = tcListaUsuarios;
	}

	public Button getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(Button btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public Button getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(Button btnModificar) {
		this.btnModificar = btnModificar;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public StaticText getStOrden() {
		return stOrden;
	}

	public void setStOrden(StaticText stOrden) {
		this.stOrden = stOrden;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public TableColumn getTcOrden() {
		return tcOrden;
	}

	public void setTcOrden(TableColumn tcOrden) {
		this.tcOrden = tcOrden;
	}

	public Label getLblReglasFirmas() {
		return lblReglasFirmas;
	}

	public void setLblReglasFirmas(Label lblReglasFirmas) {
		this.lblReglasFirmas = lblReglasFirmas;
	}

	public Button getBtnAgregarRegla() {
		return btnAgregarRegla;
	}

	public void setBtnAgregarRegla(Button btnAgregarRegla) {
		this.btnAgregarRegla = btnAgregarRegla;
	}

	public Button getBtnConsultarRegla() {
		return btnConsultarRegla;
	}

	public void setBtnConsultarRegla(Button btnConsultarRegla) {
		this.btnConsultarRegla = btnConsultarRegla;
	}

	public Button getBtnModificarRegla() {
		return btnModificarRegla;
	}

	public void setBtnModificarRegla(Button btnModificarRegla) {
		this.btnModificarRegla = btnModificarRegla;
	}

	public Button getBtnQuitarTodosRegla() {
		return btnQuitarTodosRegla;
	}

	public void setBtnQuitarTodosRegla(Button btnQuitarTodosRegla) {
		this.btnQuitarTodosRegla = btnQuitarTodosRegla;
	}

	public Button getBtnQuitarRegla() {
		return btnQuitarRegla;
	}

	public void setBtnQuitarRegla(Button btnQuitarRegla) {
		this.btnQuitarRegla = btnQuitarRegla;
	}

	public ObjectListDataProvider getLdpReglasFirmas() {
		return ldpReglasFirmas;
	}

	public void setLdpReglasFirmas(ObjectListDataProvider ldpReglasFirmas) {
		this.ldpReglasFirmas = ldpReglasFirmas;
	}

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup groupPanel3) {
		this.groupPanel3 = groupPanel3;
	}

	public RadioButton getRadioButton3() {
		return radioButton3;
	}

	public void setRadioButton3(RadioButton radioButton3) {
		this.radioButton3 = radioButton3;
	}

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table table3) {
		this.table3 = table3;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup tableRowGroup3) {
		this.tableRowGroup3 = tableRowGroup3;
	}

	public Button getBtnConsultarSuplente() {
		return btnConsultarSuplente;
	}

	public void setBtnConsultarSuplente(Button btnConsultarSuplente) {
		this.btnConsultarSuplente = btnConsultarSuplente;
	}

	public Button getBtnModificarSuplente() {
		return btnModificarSuplente;
	}

	public void setBtnModificarSuplente(Button btnModificarSuplente) {
		this.btnModificarSuplente = btnModificarSuplente;
	}

	public void setStUsuarioSuplido(StaticText stUsuarioSuplido) {
		this.stUsuarioSuplido = stUsuarioSuplido;
	}

	public StaticText getStUsuarioSuplido() {
		return stUsuarioSuplido;
	}

	public TableColumn getTcUsuarioSuplido() {
		return tcUsuarioSuplido;
	}

	public void setTcUsuarioSuplido(TableColumn tcUsuarioSuplido) {
		this.tcUsuarioSuplido = tcUsuarioSuplido;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public Button getBtnAgregarSuplente() {
		return btnAgregarSuplente;
	}

	public void setBtnAgregarSuplente(Button btnAgregarSuplente) {
		this.btnAgregarSuplente = btnAgregarSuplente;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosSuplente() {
		return btnQuitarTodosSuplente;
	}

	public void setBtnQuitarTodosSuplente(
			HtmlAjaxCommandButton btnQuitarTodosSuplente) {
		this.btnQuitarTodosSuplente = btnQuitarTodosSuplente;
	}

	public HtmlAjaxCommandButton getBtnQuitarSuplente() {
		return btnQuitarSuplente;
	}

	public void setBtnQuitarSuplente(HtmlAjaxCommandButton btnQuitarSuplente) {
		this.btnQuitarSuplente = btnQuitarSuplente;
	}

	public StaticText getStUsuarioSuplente() {
		return stUsuarioSuplente;
	}

	public void setStUsuarioSuplente(StaticText stUsuarioSuplente) {
		this.stUsuarioSuplente = stUsuarioSuplente;
	}

	public TableColumn getTcUsuarioSuplente() {
		return tcUsuarioSuplente;
	}

	public void setTcUsuarioSuplente(TableColumn tcUsuarioSuplente) {
		this.tcUsuarioSuplente = tcUsuarioSuplente;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public Label getLblUsuariosSuplentes() {
		return lblUsuariosSuplentes;
	}

	public void setLblUsuariosSuplentes(Label lblUsuariosSuplentes) {
		this.lblUsuariosSuplentes = lblUsuariosSuplentes;
	}

	public ObjectListDataProvider getLdpUsuariosSuplentes() {
		return ldpUsuariosSuplentes;
	}

	public void setLdpUsuariosSuplentes(ObjectListDataProvider ldpUsuariosSuplentes) {
		this.ldpUsuariosSuplentes = ldpUsuariosSuplentes;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
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

	public Button getBtnLimpiarArea() {
		return btnLimpiarArea;
	}

	public void setBtnLimpiarArea(Button btnLimpiarArea) {
		this.btnLimpiarArea = btnLimpiarArea;
	}

	public Button getBtnSeleccionarArea() {
		return btnSeleccionarArea;
	}

	public void setBtnSeleccionarArea(Button btnSeleccionarArea) {
		this.btnSeleccionarArea = btnSeleccionarArea;
	}

	public Label getLblArea() {
		return lblArea;
	}

	public void setLblArea(Label lblArea) {
		this.lblArea = lblArea;
	}

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public Label getLblPeriodoNumero() {
		return lblPeriodoNumero;
	}

	public void setLblPeriodoNumero(Label lblPeriodoNumero) {
		this.lblPeriodoNumero = lblPeriodoNumero;
	}

	public Label getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(Label lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public Label getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(Label lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public Label getLblPeriodicidad() {
		return lblPeriodicidad;
	}

	public void setLblPeriodicidad(Label lblPeriodicidad) {
		this.lblPeriodicidad = lblPeriodicidad;
	}

	public DropDown getDdPeriodicidad() {
		return ddPeriodicidad;
	}

	public void setDdPeriodicidad(DropDown ddPeriodicidad) {
		this.ddPeriodicidad = ddPeriodicidad;
	}

	public SingleSelectOptionsList getDdPeriodicidadDefaultOptions() {
		return ddPeriodicidadDefaultOptions;
	}

	public void setDdPeriodicidadDefaultOptions(SingleSelectOptionsList ddPeriodicidadDefaultOptions) {
		this.ddPeriodicidadDefaultOptions = ddPeriodicidadDefaultOptions;
	}

	public TextField getTfPeriodoNumero() {
		return tfPeriodoNumero;
	}

	public void setTfPeriodoNumero(TextField tfPeriodoNumero) {
		this.tfPeriodoNumero = tfPeriodoNumero;
	}

	public TextField getTfMontoMaximo() {
		return tfMontoMaximo;
	}

	public void setTfMontoMaximo(TextField tf) {
		this.tfMontoMaximo = tf;
	}

	public TextField getTfMontoMinimo() {
		return tfMontoMinimo;
	}

	public void setTfMontoMinimo(TextField tf) {
		this.tfMontoMinimo = tf;
	}

	public ObjectListDataProvider getLdpUsuariosAutorizadores() {
		return LdpUsuariosAutorizadores;
	}

	public void setLdpUsuariosAutorizadores(ObjectListDataProvider LdpUsuariosAutorizadores) {
		this.LdpUsuariosAutorizadores = LdpUsuariosAutorizadores;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button b) {
		this.btnAgregar = b;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public StaticText getStUsuario() {
		return stUsuario;
	}

	public void setStUsuario(StaticText stUsuario) {
		this.stUsuario = stUsuario;
	}

	public ABMAutorizacionSolicitudSuministro() {
	}

	public DropDown getDdArea() {
		return ddArea;
	}

	public void setDdArea(DropDown ddArea) {
		this.ddArea = ddArea;
	}

	public SingleSelectOptionsList getDdAreaOptions() {
		return ddAreaOptions;
	}

	public void setDdAreaOptions(SingleSelectOptionsList ddAreaOptions) {
		this.ddAreaOptions = ddAreaOptions;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new AutorizacionSolicitudSuministro());//
		ep.getObjetos().add(ind++, new ArrayList());// 1 Usuarios Autorizadores
		ep.getObjetos().add(ind++, new ArrayList());// 2 Usuarios Autorizadores
		// Suplentes
		ep.getObjetos().add(ind++, new ArrayList());// 3 List
		// ReglaFirmaSolicitudSuministro

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		AutorizacionSolicitudSuministro autorizacionSolicitudSuministro = this.obtenerObjetoDelElementoPila(0,
				AutorizacionSolicitudSuministro.class);
		List listaUsuario;
		List listaUsuariosSuplentes;
		List listaReglasFirmas;

		this.getObjectListDataProvider().commitChanges();
		listaUsuario = this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(listaUsuario);

		autorizacionSolicitudSuministro.setListaUsuarios(new HashSet(listaUsuario));

		this.getObjectListDataProviderUsuariosSuplentes().commitChanges();
		listaUsuariosSuplentes = this.getObjectListDataProviderUsuariosSuplentes().getList();
		this.setListaDelCommunicationUsuariosSuplentes(listaUsuariosSuplentes);

		autorizacionSolicitudSuministro.setListaUsuariosSuplentes(new HashSet(listaUsuariosSuplentes));

		this.getObjectListDataProviderReglasFirmas().commitChanges();
		listaReglasFirmas = this.getObjectListDataProviderReglasFirmas().getList();
		this.setListaDelCommunicationReglasFirmas(listaReglasFirmas);

		autorizacionSolicitudSuministro.setListaReglasFirma(listaReglasFirmas);

		autorizacionSolicitudSuministro.setArea(this.getDDObjectValue(getDdArea(), this.getComunicationBean().getMapaAreas()));

		this.getElementoPila().getObjetos().set(0, autorizacionSolicitudSuministro);
		this.getElementoPila().getObjetos().set(1, listaUsuario);
		this.getElementoPila().getObjetos().set(2, listaUsuariosSuplentes);
		this.getElementoPila().getObjetos().set(3, listaReglasFirmas);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		AutorizacionSolicitudSuministro autorizacionSolicitudSuministro = (AutorizacionSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++);
		List listaUsuario = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List listaUsuariosSuplentes = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List listaReglasFirmas = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		this.acomodarSeleccionado();

		this.getObjectListDataProvider().setList(listaUsuario);
		this.setListaDelCommunication(listaUsuario);

		this.getObjectListDataProviderUsuariosSuplentes().setList(listaUsuariosSuplentes);
		this.setListaDelCommunicationUsuariosSuplentes(listaUsuariosSuplentes);

		this.getObjectListDataProviderReglasFirmas().setList(listaReglasFirmas);
		this.setListaDelCommunicationReglasFirmas(listaReglasFirmas);

		System.out.println("listas en el mostrarEstado");
		for (Object cadaObj : listaUsuario) {
			UsuarioAutorizadorSolicitudSuministro cadaUsuario = (UsuarioAutorizadorSolicitudSuministro) cadaObj;
			System.out.println("cadaUsuario.getIdUsuarioAutorizador(): " + cadaUsuario.getIdUsuarioAutorizador());
			System.out.println("cadaUsuario.getUsuario().getIdUsuario()" + cadaUsuario.getUsuario().getIdUsuario());
		}
		for (Object cadaObj : listaUsuariosSuplentes) {
			UsuarioAutorizadorSolSumSuplente cadaUsuarioSuplente = (UsuarioAutorizadorSolSumSuplente) cadaObj;
			System.out.println("cadaUsuarioSuplente.getIdUsuarioAutorizador(): " + cadaUsuarioSuplente.getIdUsuarioAutorizador());
			System.out.println("cadaUsuarioSuplente.getUsuario().getIdUsuario()" + cadaUsuarioSuplente.getUsuario().getIdUsuario());
			System.out.println("cadaUsuarioSuplente.getUsuarioSuplido().getIdUsuarioAutorizador() "
					+ cadaUsuarioSuplente.getUsuarioSuplido().getIdUsuarioAutorizador());
			System.out.println("cadaUsuarioSuplente.getUsuarioSuplido().getUsuario().getIdUsuario() "
					+ cadaUsuarioSuplente.getUsuarioSuplido().getUsuario().getIdUsuario());
		}

		System.out.println("fin listas en el mostrarEstado");

		if (autorizacionSolicitudSuministro.getArea() != null && autorizacionSolicitudSuministro.getArea().getIdArea() != -1) {
			this.getDdArea().setSelected(autorizacionSolicitudSuministro.getArea().toString());
		}

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, autorizacionSolicitudSuministro);
		this.getElementoPila().getObjetos().set(ind++, listaUsuario);
		this.getElementoPila().getObjetos().set(ind++, listaUsuariosSuplentes);
		this.getElementoPila().getObjetos().set(ind++, listaReglasFirmas);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpUsuariosAutorizadores();
	}

	private List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaUsuariosAutorizadores();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaUsuariosAutorizadores(lista);
	}

	private ObjectListDataProvider getObjectListDataProviderUsuariosSuplentes() {
		return this.getLdpUsuariosSuplentes();
	}

	private List getListaDelCommunicationUsuariosSuplentes() {
		return this.getCommunicationComprasBean().getListaUsuariosAutorizadoresSuplentes();
	}

	private void setListaDelCommunicationUsuariosSuplentes(List lista) {
		this.getCommunicationComprasBean().setListaUsuariosAutorizadoresSuplentes(lista);
	}

	private ObjectListDataProvider getObjectListDataProviderReglasFirmas() {
		return this.getLdpReglasFirmas();
	}

	private List getListaDelCommunicationReglasFirmas() {
		return this.getCommunicationComprasBean().getListaReglasFirmasSolSum();
	}

	private void setListaDelCommunicationReglasFirmas(List lista) {
		this.getCommunicationComprasBean().setListaReglasFirmasSolSum(lista);
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
		if (selected != null) {
			lastSelected = selected;
		}
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

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	private Object lastSelected3 = null;

	public Object getRBSelected3() {
		String sv = (String) radioButton3.getSelectedValue();
		return sv.equals(lastSelected3) ? sv : null;
	}

	public void setRBSelected3(Object selected) {
		if (selected != null) {
			lastSelected3 = selected;
		}
	}

	public String getCurrentRow3() {
		return tableRowGroup3.getRowKey().getRowId();
	}

	public void setCurrentRow3(int row) {
	}

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
				if (!encontrado)
					inicioPagina += cantRegistrosPorPagina;
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if (cantFilas > 0) {
			// si hay al menos una fila
			if (posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}
			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

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
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {

		}
		return rk;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getObjectListDataProviderUsuariosSuplentes().getRowKey(aRowId);
		} catch (Exception ex) {

		}
		return rk;
	}

	public RowKey getSeleccionado3() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup3");
			rk = this.getObjectListDataProviderReglasFirmas().getRowKey(aRowId);
		} catch (Exception ex) {

		}
		return rk;
	}

	public String btnAgregar_action() throws TrascenderException {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			this.getRequestBean1().setAbmController(new UsuarioAutorizadorModel().new AgregarController());
			retorno = "ABMUsuarioAutorizador";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getObjectListDataProvider().getObjects()[index];

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoABM(obj);
					this.getRequestBean1().setAbmController(new UsuarioAutorizadorModel().new ModificarController());
					retorno = "ABMUsuarioAutorizador";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnConsultar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getObjectListDataProvider().getObjects()[index];

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoABM(obj);
					this.getRequestBean1().setAbmController(new UsuarioAutorizadorModel().new ConsultarController());
					retorno = "ABMUsuarioAutorizador";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitar_action() throws TrascenderException {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];

					boolean tieneReglaFirma = false;
					for (Object cadaObject : this.getObjectListDataProviderReglasFirmas().getList()) {
						ReglaFirmaSolicitudSuministro cadaRegla = (ReglaFirmaSolicitudSuministro) cadaObject;

						if (cadaRegla.getListaUsuarios().contains(obj)) {
							tieneReglaFirma = true;
							break;
						}
					}

					if (!tieneReglaFirma) {

						this.getListaDelCommunication().remove(obj);
						UsuarioAutorizadorSolicitudSuministro usuarioRemovido = (UsuarioAutorizadorSolicitudSuministro) obj;
						List suplentesRemover = new ArrayList();
						for (Object cadaObject : this.getListaDelCommunicationUsuariosSuplentes()) { // REVISAR
							// ESTO
							UsuarioAutorizadorSolSumSuplente cadaSuplente = (UsuarioAutorizadorSolSumSuplente) cadaObject;
							if (cadaSuplente.getUsuarioSuplido().getIdUsuarioAutorizador() == usuarioRemovido.getIdUsuarioAutorizador()) {
								suplentesRemover.add(cadaSuplente);
							}
						}

						this.getObjectListDataProviderUsuariosSuplentes().getList().removeAll(suplentesRemover);
					} else {
						warn("El Usuario que desea quitar se encuentra en una Regla Firma");
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodos_action() throws TrascenderException {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				boolean tieneReglaFirma = false;
				for (Object cadaObjectUsuario : getListaDelCommunication()) {
					for (Object cadaObjectFirma : this.getObjectListDataProviderReglasFirmas().getList()) {
						ReglaFirmaSolicitudSuministro cadaRegla = (ReglaFirmaSolicitudSuministro) cadaObjectFirma;
						if (cadaRegla.getListaUsuarios().contains(cadaObjectUsuario)) {
							tieneReglaFirma = true;
							break;
						}
					}
				}
				if (tieneReglaFirma) {
					warn("No pueden quitarse los Usuarios Firmantes por que uno o mas participan en Reglas de Firma.");
					return null;
				}
				this.getListaDelCommunication().clear();
				this.getListaDelCommunicationUsuariosSuplentes().clear();
				this.getObjectListDataProviderUsuariosSuplentes().getList().clear();
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarSuplente_action() throws TrascenderException {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			this.guardarEstadoObjetosUsados();

			if (this.getLdpUsuariosAutorizadores().getList().size() > 0) {
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				// CAMBIAR: Caso de navegacion de la pagina de administracion
				// correspondiente.
				this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, ArrayList.class));
				this.getRequestBean1().setAbmController(new UsuarioAutorizadorSuplenteModel().new AgregarController());
				retorno = "ABMUsuarioAutorizadorSuplente";
			} else {
				warn("Debe agregar al menos un Usuario Autorizador");
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarSuplente_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado2();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderUsuariosSuplentes().getObjects()[index];
					this.getListaDelCommunicationUsuariosSuplentes().remove(obj);
				}
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosSuplente_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationUsuariosSuplentes().clear();
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarSuplente_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado2();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getObjectListDataProviderUsuariosSuplentes().getObjects()[index];
					UsuarioAutorizadorSolSumSuplente usuarioSuplente = (UsuarioAutorizadorSolSumSuplente) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, ArrayList.class));
					this.getRequestBean1().setObjetoABM(usuarioSuplente);
					this.getRequestBean1().setAbmController(new UsuarioAutorizadorSuplenteModel().new ModificarController());
					retorno = "ABMUsuarioAutorizadorSuplente";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnConsultarSuplente_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado2();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getObjectListDataProviderUsuariosSuplentes().getObjects()[index];
					UsuarioAutorizadorSolSumSuplente usuarioSuplente = (UsuarioAutorizadorSolSumSuplente) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, ArrayList.class));
					this.getRequestBean1().setObjetoABM(usuarioSuplente);
					this.getRequestBean1().setAbmController(new UsuarioAutorizadorSuplenteModel().new ConsultarController());
					retorno = "ABMUsuarioAutorizadorSuplente";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarArea_action() {
		return navegarParaSeleccionar("AdminArea");
	}

	public String btnLimpiarArea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(getDdArea());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarRegla_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			this.guardarEstadoObjetosUsados();

			if (this.getLdpUsuariosAutorizadores().getList().size() > 0) {
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				// CAMBIAR: Caso de navegacion de la pagina de administracion
				// correspondiente.
				this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, ArrayList.class));
				this.getRequestBean1().setAbmController(new ReglaFirmaSolicitudSuministroModel().new AgregarController());
				retorno = "ABMReglaFirmaSolicitudSuministro";
			} else {
				warn("Debe agregar al menos un Usuario Autorizador");
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarRegla_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado3();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderReglasFirmas().getObjects()[index];
					this.getListaDelCommunicationReglasFirmas().remove(obj);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosRegla_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationReglasFirmas().clear();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarRegla_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado3();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getObjectListDataProviderReglasFirmas().getObjects()[index];
					ReglaFirmaSolicitudSuministro locReglaFirma = (ReglaFirmaSolicitudSuministro) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, ArrayList.class));
					this.getRequestBean1().setObjetoABM(locReglaFirma);
					this.getRequestBean1().setAbmController(new ReglaFirmaSolicitudSuministroModel().new ModificarController());
					retorno = "ABMReglaFirmaSolicitudSuministro";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnConsultarRegla_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado3();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getObjectListDataProviderReglasFirmas().getObjects()[index];
					ReglaFirmaSolicitudSuministro locRegla = (ReglaFirmaSolicitudSuministro) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, ArrayList.class));
					this.getRequestBean1().setObjetoABM(locRegla);
					this.getRequestBean1().setAbmController(new ReglaFirmaSolicitudSuministroModel().new ConsultarController());
					retorno = "ABMReglaFirmaSolicitudSuministro";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMAutorizacionSolicitudSuministro";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		AutorizacionSolicitudSuministro autorizacionSolicitudSuministro = (AutorizacionSolicitudSuministro) this.obtenerObjetoDelElementoPila(0);
		List listaUsuariosSuplentes;
		List listaUsuario;
		List listaReglasFirmas = new ArrayList();

		if (pObject instanceof UsuarioAutorizadorSolSumSuplente) {
			UsuarioAutorizadorSolSumSuplente nuevoUsuarioSuplente = (UsuarioAutorizadorSolSumSuplente) pObject;
			nuevoUsuarioSuplente.setAutorizacion(autorizacionSolicitudSuministro);

			listaUsuariosSuplentes = new ArrayList(autorizacionSolicitudSuministro.getListaUsuariosSuplentes());

			listaUsuariosSuplentes.add(nuevoUsuarioSuplente);
			this.getElementoPila().getObjetos().set(2, listaUsuariosSuplentes);
			this.getRequestBean1().setObjetoSeleccion(null);
		} else if (pObject instanceof UsuarioAutorizadorSolicitudSuministro) {
			UsuarioAutorizadorSolicitudSuministro nuevoUsuarioAutorizador = (UsuarioAutorizadorSolicitudSuministro) pObject;

			listaUsuario = new ArrayList(autorizacionSolicitudSuministro.getListaUsuarios());

			boolean yaExiste = false;
			for (Object cadaObj : listaUsuario) {
				UsuarioAutorizadorSolicitudSuministro cadaUsuario = (UsuarioAutorizadorSolicitudSuministro) cadaObj;
				if (cadaUsuario.getUsuario().equals(nuevoUsuarioAutorizador.getUsuario())) {
					warn("El Usuario seleccionado ya se encuentra en la lista");
					yaExiste = true;
					break;
				}
			}
			if (!yaExiste) {
				nuevoUsuarioAutorizador.setAutorizacion(autorizacionSolicitudSuministro);
				listaUsuario.add(nuevoUsuarioAutorizador);
				this.getElementoPila().getObjetos().set(1, listaUsuario);
			}
		}

		if (pObject instanceof ReglaFirmaSolicitudSuministro) {
			ReglaFirmaSolicitudSuministro locRegla = (ReglaFirmaSolicitudSuministro) pObject;
			locRegla.setAutorizacionSolicitudSuministro(autorizacionSolicitudSuministro);

			listaReglasFirmas = autorizacionSolicitudSuministro.getListaReglasFirma();

			listaReglasFirmas.add(locRegla);
			this.getElementoPila().getObjetos().set(3, listaReglasFirmas);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		AutorizacionSolicitudSuministro autorizacionSolicitudSuministro = (AutorizacionSolicitudSuministro) pObject;
		try {
			this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
			autorizacionSolicitudSuministro = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro()
					.getAutorizacionSolicitudSuministroByID(autorizacionSolicitudSuministro.getIdAutorizacionSolicitudSuministro());
		} catch (Exception e) {
			e.printStackTrace();
		}

		List listaUsuario = new ArrayList(autorizacionSolicitudSuministro.getListaUsuarios());
		List listaUsuariosSuplentes = new ArrayList(autorizacionSolicitudSuministro.getListaUsuariosSuplentes());
		List listaReglasFirmas = autorizacionSolicitudSuministro.getListaReglasFirma();

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, autorizacionSolicitudSuministro);
		this.getElementoPila().getObjetos().set(ind++, listaUsuario);
		this.getElementoPila().getObjetos().set(ind++, listaUsuariosSuplentes);
		this.getElementoPila().getObjetos().set(ind++, listaReglasFirmas);
	}

	@Override
	public long getSerialVersionUID() {
		return AutorizacionSolicitudSuministro.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		AutorizacionSolicitudSuministro locAutorizacionSolicitudSuministro = this.obtenerObjetoDelElementoPila(0, AutorizacionSolicitudSuministro.class);
		this.getTablaLogs().getLdpLogs().setList(locAutorizacionSolicitudSuministro.getListaLogsAuditoria());
	}
}