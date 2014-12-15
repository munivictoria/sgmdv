/*
 * ABMBien.java
 *
 * Created on 22 de noviembre de 2006, 09:53
 * Copyright Trascender
 */
package muni.compras.ABMBien;

import java.util.ArrayList;
import java.util.Date;
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
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMBien extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		table1.setClearSortButton(true);
		tableTipoBien.setClearSortButton(true);
		
		Option[] opTipo = null;
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(Bien.Tipo.values(), "may");
		ddTipoDefaultOptions.setOptions(opTipo);

		this.getLdpCodigosCiiu().setList(this.getListaDelCommunicationCodCiiu());
		tablePhaseListener = this.getTableSelectPhaseListener();

		Set<String> locListaUnidades = getApplicationBean1().getMapaUnidad().keySet();
		Option[] opUnidad = new Option[locListaUnidades.size() + 1];
		int i = 0;
		opUnidad[i++] = new Option("", "");
		for (String cadaUnidad : locListaUnidades) {
			opUnidad[i++] = new Option(cadaUnidad, cadaUnidad);
		}
		this.ddUnidadDefaultOptions.setOptions(opUnidad);

		this.getObjectListDataProviderTipoBien().setList(this.getListaDelCommunicationTipoBien());
		tablePhaseListenerTipoBien = this.getTableSelectPhaseListenerTipoBien();

		Set<String> locListaTipoBien = getApplicationBean1().getMapaTipoBien().keySet();

		Option[] opTipoBien = new Option[locListaTipoBien.size() + 1];
		int j = 0;
		opTipoBien[j++] = new Option("", "");
		for (String cadaTipo : locListaTipoBien) {
			opTipoBien[j++] = new Option(cadaTipo, cadaTipo);
		}
		this.ddTipoBienDefaultOptions.setOptions(opTipoBien);
	}

	private Label lblTipo = new Label();
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();

	private Button btnSeleccionarUnidad = new Button();
	private HtmlAjaxCommandButton btnLimpiarUnidad = new HtmlAjaxCommandButton();
	private Label lblUnidad = new Label();
	private DropDown ddUnidad = new DropDown();
	private SingleSelectOptionsList ddUnidadDefaultOptions = new SingleSelectOptionsList();

	private TextField tfNombre = new TextField();
	private TextField tfUnidad = new TextField();
	private TextField tfValorReferencial = new TextField();
	private TextField tfFechaUltimaActualizacion = new TextField();

	private Label lblNombre = new Label();
	private Label lblDescripcion = new Label();
	private Label lblValorReferencial = new Label();
	private Label lblFechaUltimaActualizacion = new Label();

	private StaticText stCodigosCiiu = new StaticText();
	private StaticText stCodigo = new StaticText();
	private StaticText stDescripcionCodigo = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stTipoBien = new StaticText();
	private StaticText stNombre = new StaticText();
	private StaticText tsDescripcionTipoBien = new StaticText();

	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcCodigo = new TableColumn();
	private TableColumn tcDescripcionCodigo = new TableColumn();
	private TableColumn tableColumnTipoBien = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcDescripcionTipoBien = new TableColumn();

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	private Table table1 = new Table();
	private Table tableTipoBien = new Table();

	private PanelGroup groupPanel1 = new PanelGroup();
	private PanelGroup groupPanelTipoBien = new PanelGroup();

	private Button btnAgregar = new Button();
	 protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
	 protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
	private Button btnSeleccionarTipoBien = new Button();
	private Button btnLimpiarTipoBien = new Button();
	private Button btnAgregarTipoBien = new Button();
	 protected HtmlAjaxCommandButton btnQuitarTipoBien = new HtmlAjaxCommandButton();
	 protected HtmlAjaxCommandButton btnQuitarTodosTipoBien = new HtmlAjaxCommandButton();
	private StaticText stSeparadorTipoBien = new StaticText();
	private StaticText stSeparadorTipoBien2 = new StaticText();

	private DropDown ddTipoBien = new DropDown();
	private SingleSelectOptionsList ddTipoBienDefaultOptions = new SingleSelectOptionsList();

	private TextArea taDescripcion = new TextArea();

	private ObjectListDataProvider ldpCodigosCiiu = new ObjectListDataProvider();

	private Checkbox checkbox1 = new Checkbox();
	private Checkbox checkbox2 = new Checkbox();

	private TableSelectPhaseListener tablePhaseListener;
	
	private TableRowGroup tableRowGroupTipoBien = new TableRowGroup();
	private TableSelectPhaseListener tablePhaseListenerTipoBien = new TableSelectPhaseListener();
	private ObjectListDataProvider ldpTipoBien = new ObjectListDataProvider();
	
	public HtmlAjaxCommandButton getBtnLimpiarUnidad() {
		return btnLimpiarUnidad;
	}

	public void setBtnLimpiarUnidad(HtmlAjaxCommandButton btnLimpiarUnidad) {
		this.btnLimpiarUnidad = btnLimpiarUnidad;
	}

	public StaticText getTsDescripcionTipoBien() {
		return tsDescripcionTipoBien;
	}

	public void setTsDescripcionTipoBien(StaticText tsDescripcionTipoBien) {
		this.tsDescripcionTipoBien = tsDescripcionTipoBien;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public Button getBtnAgregarTipoBien() {
		return btnAgregarTipoBien;
	}

	public void setBtnAgregarTipoBien(Button btnAgregarTipoBien) {
		this.btnAgregarTipoBien = btnAgregarTipoBien;
	}

	public Button getBtnLimpiarTipoBien() {
		return btnLimpiarTipoBien;
	}

	public void setBtnLimpiarTipoBien(Button btnLimpiarTipoBien) {
		this.btnLimpiarTipoBien = btnLimpiarTipoBien;
	}

	public HtmlAjaxCommandButton getBtnQuitarTipoBien() {
		return btnQuitarTipoBien;
	}

	public void setBtnQuitarTipoBien(HtmlAjaxCommandButton btnQuitarTipoBien) {
		this.btnQuitarTipoBien = btnQuitarTipoBien;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosTipoBien() {
		return btnQuitarTodosTipoBien;
	}

	public void setBtnQuitarTodosTipoBien(
			HtmlAjaxCommandButton btnQuitarTodosTipoBien) {
		this.btnQuitarTodosTipoBien = btnQuitarTodosTipoBien;
	}

	public Button getBtnSeleccionarTipoBien() {
		return btnSeleccionarTipoBien;
	}

	public void setBtnSeleccionarTipoBien(Button btnSeleccionarTipoBien) {
		this.btnSeleccionarTipoBien = btnSeleccionarTipoBien;
	}

	public Checkbox getCheckbox2() {
		return checkbox2;
	}

	public void setCheckbox2(Checkbox checkbox2) {
		this.checkbox2 = checkbox2;
	}

	public DropDown getDdTipoBien() {
		return ddTipoBien;
	}

	public void setDdTipoBien(DropDown ddTipoBien) {
		this.ddTipoBien = ddTipoBien;
	}

	public SingleSelectOptionsList getDdTipoBienDefaultOptions() {
		return ddTipoBienDefaultOptions;
	}

	public void setDdTipoBienDefaultOptions(SingleSelectOptionsList ddTipoBienDefaultOptions) {
		this.ddTipoBienDefaultOptions = ddTipoBienDefaultOptions;
	}

	public PanelGroup getGroupPanelTipoBien() {
		return groupPanelTipoBien;
	}

	public void setGroupPanelTipoBien(PanelGroup groupPanelTipoBien) {
		this.groupPanelTipoBien = groupPanelTipoBien;
	}

	public ObjectListDataProvider getLdpTipoBien() {
		return ldpTipoBien;
	}

	public void setLdpTipoBien(ObjectListDataProvider ldpTipoBien) {
		this.ldpTipoBien = ldpTipoBien;
	}

	public StaticText getStSeparadorTipoBien() {
		return stSeparadorTipoBien;
	}

	public void setStSeparadorTipoBien(StaticText stSeparadorTipoBien) {
		this.stSeparadorTipoBien = stSeparadorTipoBien;
	}

	public StaticText getStSeparadorTipoBien2() {
		return stSeparadorTipoBien2;
	}

	public void setStSeparadorTipoBien2(StaticText stSeparadorTipoBien2) {
		this.stSeparadorTipoBien2 = stSeparadorTipoBien2;
	}

	public StaticText getStTipoBien() {
		return stTipoBien;
	}

	public void setStTipoBien(StaticText stTipoBien) {
		this.stTipoBien = stTipoBien;
	}

	public TableColumn getTableColumnTipoBien() {
		return tableColumnTipoBien;
	}

	public void setTableColumnTipoBien(TableColumn tableColumnTipoBien) {
		this.tableColumnTipoBien = tableColumnTipoBien;
	}

	public TableSelectPhaseListener getTablePhaseListenerTipoBien() {
		return tablePhaseListenerTipoBien;
	}

	public void setTablePhaseListenerTipoBien(TableSelectPhaseListener tablePhaseListenerTipoBien) {
		this.tablePhaseListenerTipoBien = tablePhaseListenerTipoBien;
	}

	public TableRowGroup getTableRowGroupTipoBien() {
		return tableRowGroupTipoBien;
	}

	public void setTableRowGroupTipoBien(TableRowGroup tableRowGroupTipoBien) {
		this.tableRowGroupTipoBien = tableRowGroupTipoBien;
	}

	public Table getTableTipoBien() {
		return tableTipoBien;
	}

	public void setTableTipoBien(Table tableTipoBien) {
		this.tableTipoBien = tableTipoBien;
	}

	public TableColumn getTcDescripcionTipoBien() {
		return tcDescripcionTipoBien;
	}

	public void setTcDescripcionTipoBien(TableColumn tcDescripcionTipoBien) {
		this.tcDescripcionTipoBien = tcDescripcionTipoBien;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown ddTipo) {
		this.ddTipo = ddTipo;
	}

	public SingleSelectOptionsList getDdTipoDefaultOptions() {
		return ddTipoDefaultOptions;
	}

	public void setDdTipoDefaultOptions(SingleSelectOptionsList ddTipoDefaultOptions) {
		this.ddTipoDefaultOptions = ddTipoDefaultOptions;
	}

	public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public DropDown getDdUnidad() {
		return ddUnidad;
	}

	public void setDdUnidad(DropDown ddUnidad) {
		this.ddUnidad = ddUnidad;
	}

	public SingleSelectOptionsList getDdUnidadDefaultOptions() {
		return ddUnidadDefaultOptions;
	}

	public void setDdUnidadDefaultOptions(SingleSelectOptionsList ddUnidadDefaultOptions) {
		this.ddUnidadDefaultOptions = ddUnidadDefaultOptions;
	}

	public Label getLblValorReferencial() {
		return lblValorReferencial;
	}

	public void setLblValorReferencial(Label lblValorReferencial) {
		this.lblValorReferencial = lblValorReferencial;
	}

	public TextField getTfValorReferencial() {
		return tfValorReferencial;
	}

	public void setTfValorReferencial(TextField tfValorReferencial) {
		this.tfValorReferencial = tfValorReferencial;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox checkbox1) {
		this.checkbox1 = checkbox1;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public ObjectListDataProvider getLdpCodigosCiiu() {
		return ldpCodigosCiiu;
	}

	public void setLdpCodigosCiiu(ObjectListDataProvider ldpCodigosCiiu) {
		this.ldpCodigosCiiu = ldpCodigosCiiu;
	}

	public StaticText getStCodigo() {
		return stCodigo;
	}

	public void setStCodigo(StaticText stCodigo) {
		this.stCodigo = stCodigo;
	}

	public StaticText getStCodigosCiiu() {
		return stCodigosCiiu;
	}

	public void setStCodigosCiiu(StaticText stCodigosCiiu) {
		this.stCodigosCiiu = stCodigosCiiu;
	}

	public StaticText getStDescripcionCodigo() {
		return stDescripcionCodigo;
	}

	public void setStDescripcionCodigo(StaticText stDescripcionCodigo) {
		this.stDescripcionCodigo = stDescripcionCodigo;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
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

	public TableSelectPhaseListener getTablePhaseListener() {
		return tablePhaseListener;
	}

	public void setTablePhaseListener(TableSelectPhaseListener tablePhaseListener) {
		this.tablePhaseListener = tablePhaseListener;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public boolean isCurrentRowSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListener.isSelected(rowKey);
	}

	public void setSelected(Object object) {
		RowKey rowKey = tableRowGroup1.getRowKey();
		if (rowKey != null) {
			tablePhaseListener.setSelected(rowKey, object);
		}
	}

	public Object getSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListener.getSelected(rowKey);
	}

	public Object getSelectedValue() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public void setSelectedTipoBien(Object object) {
		RowKey rowKey = tableRowGroupTipoBien.getRowKey();
		if (rowKey != null) {
			tablePhaseListenerTipoBien.setSelected(rowKey, object);
		}
	}

	public Object getSelectedTipoBien() {
		RowKey rowKey = tableRowGroupTipoBien.getRowKey();
		return tablePhaseListenerTipoBien.getSelected(rowKey);
	}

	public Object getSelectedValueTipoBien() {
		RowKey rowKey = tableRowGroupTipoBien.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProviderTipoBien().getRowKey(aRowId);
		} catch (Exception ex) {

		}
		return rk;
	}

	public TableColumn getTcCodigo() {
		return tcCodigo;
	}

	public void setTcCodigo(TableColumn tcCodigo) {
		this.tcCodigo = tcCodigo;
	}

	public TableColumn getTcDescripcionCodigo() {
		return tcDescripcionCodigo;
	}

	public void setTcDescripcionCodigo(TableColumn tcDescripcionCodigo) {
		this.tcDescripcionCodigo = tcDescripcionCodigo;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	public Button getBtnSeleccionarUnidad() {
		return btnSeleccionarUnidad;
	}

	public void setBtnSeleccionarUnidad(Button btnSeleccionarUnidad) {
		this.btnSeleccionarUnidad = btnSeleccionarUnidad;
	}

	public Label getLblUnidad() {
		return lblUnidad;
	}

	public void setLblUnidad(Label lblUnidad) {
		this.lblUnidad = lblUnidad;
	}

	public TextField getTfUnidad() {
		return tfUnidad;
	}

	public void setTfUnidad(TextField tfUnidad) {
		this.tfUnidad = tfUnidad;
	}

	public Label getLblFechaUltimaActualizacion() {
		return lblFechaUltimaActualizacion;
	}

	public void setLblFechaUltimaActualizacion(Label lblFechaUltimaActualizacion) {
		this.lblFechaUltimaActualizacion = lblFechaUltimaActualizacion;
	}

	public TextField getTfFechaUltimaActualizacion() {
		return tfFechaUltimaActualizacion;
	}

	public void setTfFechaUltimaActualizacion(TextField tfFechaUltimaActualizacion) {
		this.tfFechaUltimaActualizacion = tfFechaUltimaActualizacion;
	}

	public List getListaDelCommunicationCodCiiu() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaCodigosCiiuBien();
	}

	private void setListaDelCommunicationCodCiiu(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaCodigosCiiuBien(lista);
	}

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		// CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que
		// corresponda
		return this.getCommunicationComprasBean().getTablePhaseListenerCodigoCiiu();
	}

	private ObjectListDataProvider getObjectListDataProviderTipoBien() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpTipoBien();
	}

	private TableSelectPhaseListener getTableSelectPhaseListenerTipoBien() {
		// CAMBIAR: Utilizar el TableSelectPhaseListener del Comunication que
		// corresponda
		return this.getCommunicationComprasBean().getTablePhaseListenerTipoBien();
	}

	private void setListaDelCommunicationTipoBien(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaTipoBienesBien(lista);
	}

	public List getListaDelCommunicationTipoBien() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaTipoBienesBien();
	}

	public RowKey getCodCiiuSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpCodigosCiiu().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	public ABMBien() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Bien()); // 0
		ep.getObjetos().add(ind++, new ArrayList()); // 1 Lista Codigos Ciiu
		ep.getObjetos().add(ind++, new ArrayList()); // 2 Lista Tipo Bien

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Obtener los valores de los campos y
		// asignarlos a los atributos de los objetos de la pagina
		int ind = 0;
		Bien bien = (Bien) this.obtenerObjetoDelElementoPila(ind++, Bien.class);
		List codigosCiiu = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List tipoBien = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		bien.setNombre(getTextFieldValue(getTfNombre()));
		bien.setDescripcion(getTextAreaValue(getTaDescripcion()));
		bien.setValorReferencial(getTextFieldValueDouble(getTfValorReferencial()));
		bien.setTipo(getDDEnumValue(getDdTipo(), Bien.Tipo.class));
		bien.setUnidad(getDDObjectValue(getDdUnidad(), getApplicationBean1().getMapaUnidad()));

		if (!codigosCiiu.isEmpty()) {
			bien.setListaCodigosCiiu(codigosCiiu);
		}
		if (!tipoBien.isEmpty()) {
			bien.setListaTipoBien(tipoBien);
		}

		this.getObjectListDataProviderTipoBien().commitChanges();
		tipoBien = this.getObjectListDataProviderTipoBien().getList();
		bien.setListaTipoBien(tipoBien);

		this.getLdpCodigosCiiu().commitChanges();
		codigosCiiu = this.getLdpCodigosCiiu().getList();
		bien.setListaCodigosCiiu(codigosCiiu);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, bien);
		this.getElementoPila().getObjetos().set(ind++, codigosCiiu);
		this.getElementoPila().getObjetos().set(ind++, tipoBien);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		Bien bien = (Bien) this.obtenerObjetoDelElementoPila(ind++, Bien.class);
		List codigosCiiu = (List) this.obtenerObjetoDelElementoPila(ind++, List.class);
		List tipoBien = (List) this.obtenerObjetoDelElementoPila(ind++, List.class);

		this.getTfNombre().setText(bien.getNombre());
		this.getTaDescripcion().setText(bien.getDescripcion());
		
		if (bien.getValorReferencial() != null) {
			this.getTfValorReferencial().setText(bien.getValorReferencial().toString());
		}
		if (bien.getUnidad() != null) {
			this.getDdUnidad().setSelected(bien.getUnidad().getDescripcion());
		}
		if (bien.getTipo() != null) {
			this.getDdTipo().setSelected(bien.getTipo().toString());
		}
		if (bien.getFechaUltimaActualizacion() != null) {
			this.getTfFechaUltimaActualizacion().setText(Conversor.getStringDeFechaCorta(bien.getFechaUltimaActualizacion()));
		} else {
			Date hoy = new Date();
			this.getTfFechaUltimaActualizacion().setText(Conversor.getStringDeFechaCorta(hoy));
		}

		this.getLdpCodigosCiiu().setList(codigosCiiu);
		this.setListaDelCommunicationCodCiiu(codigosCiiu);
		this.getObjectListDataProviderTipoBien().setList(tipoBien);
		this.setListaDelCommunicationTipoBien(tipoBien);
	}

	public String btnSeleccionarUnidad_action() {
		return navegarParaSeleccionar("AdminUnidad");
	}

	public String btnLimpiarUnidad_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(getDdUnidad());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminCodigoCiiu";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				RowKey[] selectedRowKeys = this.getTableRowGroup1().getSelectedRowKeys();
				List locListaSeleccionados = new ArrayList();
				for (int i = 0; i < selectedRowKeys.length; i++) {
					String rowId = selectedRowKeys[i].getRowId();
					System.out.println("rowId " + rowId);
					RowKey rowKey = this.getLdpCodigosCiiu().getRowKey(rowId);
					System.out.println("rowKey " + rowKey);
					Object obj = this.getLdpCodigosCiiu().getObjects()[getNroFila(rowKey.toString())];
					System.out.println("obj " + obj);
					locListaSeleccionados.add(obj);
				}
				System.out.println("Codigos seleccionados: ");
				System.out.println(locListaSeleccionados);
				this.getListaDelCommunicationCodCiiu().removeAll(locListaSeleccionados);
				this.tablePhaseListener.clear();
			} catch (Exception ex) {
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

		if (ultimo) {
			try {
				this.getListaDelCommunicationCodCiiu().clear();
			} catch (Exception ex) {
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public boolean isCurrentRowSelectedTipoBien() {
		RowKey rowKey = tableRowGroupTipoBien.getRowKey();
		return tablePhaseListenerTipoBien.isSelected(rowKey);
	}

	public String btnSeleccionarTipoBien_action() {
		return navegarParaSeleccionar("AdminTipoBien");
	}

	public String btnAgregar_actionTipoBien() {
		this.guardarEstadoObjetosUsados();
		Bien locBien = (Bien) this.obtenerObjetoDelElementoPila(0);

		for (Object cadaObject : getApplicationBean1().getMapaTipoBien().values()) {
			TipoBien cadaTipo = (TipoBien) cadaObject;
			if (this.getDdTipoBien().getSelected().toString().equals(cadaTipo.getNombre().toString())) {
				if (locBien.getListaTipoBien() == null) {
					locBien.setListaTipoBien(new ArrayList<TipoBien>());
				}

				if (!locBien.getListaTipoBien().contains(cadaTipo)) {
					locBien.getListaTipoBien().add((TipoBien) cadaTipo);

					this.getObjectListDataProviderTipoBien().setList(locBien.getListaTipoBien());
				} else {
					warn("El Tipo Bien seleccionado ya se encuentra en la lista");
				}

				break;
			}
		}
		this.getDdTipoBien().setSelected("");

		return "";
	}

	public String btnQuitar_actionTipoBien() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				RowKey[] selectedRowKeys = this.getTableRowGroupTipoBien().getSelectedRowKeys();
				List locListaSeleccionados = new ArrayList();
				for (int i = 0; i < selectedRowKeys.length; i++) {
					String rowId = selectedRowKeys[i].getRowId();
					RowKey rowKey = this.getObjectListDataProviderTipoBien().getRowKey(rowId);
					Object obj = this.getObjectListDataProviderTipoBien().getObjects()[getNroFila(rowKey.toString())];
					locListaSeleccionados.add(obj);
				}
				this.getObjectListDataProviderTipoBien().getList().removeAll(locListaSeleccionados);
				this.getListaDelCommunicationTipoBien().removeAll(locListaSeleccionados);

			} catch (Exception e) {
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarTodos_actionTipoBien() {
		this.guardarEstadoObjetosUsados();
		Bien locBien = (Bien) this.obtenerObjetoDelElementoPila(0);
		locBien.setListaTipoBien(new ArrayList<TipoBien>());
		this.getObjectListDataProviderTipoBien().getList().clear();

		return "";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMBien";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Bien bien;
		Unidad unidad;
		List codigosCiiu = new ArrayList();
		List tipoBien = new ArrayList();
		
		if (pObject instanceof Unidad) {
			if (pObject != null) {
				unidad = (Unidad) pObject;

				bien = (Bien) this.obtenerObjetoDelElementoPila(0, Bien.class);
				bien.setUnidad(unidad);
				this.getElementoPila().getObjetos().set(0, bien);
			}
		}
		if (pObject instanceof CodigoCiiu) {
			if (pObject != null) {
				if (!((List) this.getElementoPila().getObjetos().get(1)).contains(pObject)) {
					codigosCiiu = this.obtenerObjetoDelElementoPila(1, ArrayList.class);
					
					codigosCiiu.add((CodigoCiiu) pObject);
					this.getElementoPila().getObjetos().set(1, codigosCiiu);
				} else {
					warn("El Código Ciiu seleccionado ya se encuentra en la lista");
				}
				this.getRequestBean1().setObjetoSeleccion(null);
			}
		}
		if (pObject instanceof TipoBien) {
			if (!((List) this.getElementoPila().getObjetos().get(2)).contains(pObject)) {
				tipoBien = this.obtenerObjetoDelElementoPila(2, ArrayList.class);
				
				tipoBien.add((TipoBien) pObject);
				this.getElementoPila().getObjetos().set(2, tipoBien);
			} else {
				warn("La Categoría seleccionada ya se encuentra en la lista");
			}
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Bien bien = (Bien) pObject;
		Unidad unidad = bien.getUnidad();
		
		List codigosCiiu = new ArrayList();
		codigosCiiu = bien.getListaCodigosCiiu();
		List tipoBien = new ArrayList();
		tipoBien = bien.getListaTipoBien();
		
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, bien);
		this.getElementoPila().getObjetos().set(ind++, codigosCiiu);
		this.getElementoPila().getObjetos().set(ind++, tipoBien);
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Bien locBien = this.obtenerObjetoDelElementoPila(0, Bien.class);
		this.getTablaLogs().getLdpLogs().setList(locBien.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return Bien.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMBien$ABMBien}";
	}
}