
package com.trascender.presentacion.abstracts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.MethodExpressionActionListener;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.transients.AtributoConsultable;
import com.trascender.framework.util.LogAuditoria;

public class TablaLogs extends Table {

	private String idTablaLogs;
	private String nombreBean;
	// private FiltroAbstracto filtro;
	// El mapa que relaciona el nombre interno con el atributo consultable
	private final Map<String, AtributoConsultable> mapaAtributosConsultables = new LinkedHashMap<String, AtributoConsultable>();
	// El mapa que relaciona el nombre interno con el nombre de la columna, que puede variar según se haga click para ordenar.

	private final FacesContext context = FacesContext.getCurrentInstance();
	private final Application application = context.getApplication();

	private TableRowGroup tableRowGroup;

	protected PanelGroup groupPanelTablaLogs = new PanelGroup();

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	
	private List<LogAuditoria> listaLogsFiltrada = new ArrayList<LogAuditoria>();

	public List<LogAuditoria> getListaLogsFiltrada() {
		return listaLogsFiltrada;
	}

	public void setListaLogsFiltrada(List<LogAuditoria> listaLogsFiltrada) {
		this.listaLogsFiltrada = listaLogsFiltrada;
	}

	private MethodExpressionActionListener getActionListenerEnBean(String pValor) {
		return this.getActionListener(this.armarExpressionEnBean(pValor));
	}

	private MethodExpressionActionListener getActionListener(String pValor) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		ExpressionFactory elFactory = context.getApplication().getExpressionFactory();

		Class[] args = new Class[] {javax.faces.event.ActionEvent.class};
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);
		return listener;
	}
	
	private MethodBinding getMethodBinding(String pValor) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		ExpressionFactory elFactory = context.getApplication().getExpressionFactory();

		Class[] args = new Class[] {};
		MethodBinding methodBinding = application.createMethodBinding(this.armarExpressionEnBean(pValor), args);
		return methodBinding;
	}

	public TablaLogs() {
		super();
	}

	public TablaLogs(String pNombreBean) {
		super();

		if(pNombreBean != null) {
			AtributoConsultable propiedad = new AtributoConsultable("propiedad", "Propiedad", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(propiedad.getNombreInterno(), propiedad);

			AtributoConsultable tipo = new AtributoConsultable("tipo", "Tipo", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(tipo.getNombreInterno(), tipo);

			AtributoConsultable valorAnterior = new AtributoConsultable("valorAnterior", "Valor Anterior", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(valorAnterior.getNombreInterno(), valorAnterior);

			AtributoConsultable valorNuevo = new AtributoConsultable("valorNuevo", "Valor Nuevo", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(valorNuevo.getNombreInterno(), valorNuevo);

			AtributoConsultable comentario = new AtributoConsultable("comentario", "Comentario", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(comentario.getNombreInterno(), comentario);

			AtributoConsultable usuario = new AtributoConsultable("usuario", "Usuario", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(usuario.getNombreInterno(), usuario);

			AtributoConsultable fecha = new AtributoConsultable("fecha", "Fecha", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(fecha.getNombreInterno(), fecha);

			dateTimeConverter1.setTimeZone(java.util.TimeZone.getDefault());
			dateTimeConverter1.setPattern("dd/MM/yyyy hh:mm:ss");
			dateTimeConverter1.setTimeStyle("full");

			this.nombreBean = pNombreBean;
//			this.idTablaLogs = "tbLogsAuditoria";
			this.setClearSortButton(true); // suprimir todo el orden
			this.init();
		}
	}

	private void init() {
		this.inicializarColumnas();
	}

	private void inicializarColumnas() {
		this.getTableRowGroupPaginacion();

		TableColumn tcPropiedad = getTcPropiedad();
		tableRowGroup.getChildren().add(tcPropiedad);

		TableColumn tcTipo = getTcTipo();
		tableRowGroup.getChildren().add(tcTipo);

		TableColumn tcValorAnterior = getTcValorAnterior();
		tableRowGroup.getChildren().add(tcValorAnterior);

		TableColumn tcValorNuevo = getTcValorNuevo();
		tableRowGroup.getChildren().add(tcValorNuevo);

		TableColumn tcComentario = getTcComentario();
		tableRowGroup.getChildren().add(tcComentario);

		TableColumn tcUsuario = getTcUsuario();
		tableRowGroup.getChildren().add(tcUsuario);

		TableColumn tcFecha = getTcFecha();
		tableRowGroup.getChildren().add(tcFecha);

		this.getChildren().add(tableRowGroup);

		PanelGroup pgFiltrado = new PanelGroup();
		pgFiltrado.setId("gpFiltroTablaLogs");
		pgFiltrado.getChildren().add(getComponenteFiltrarPropiedad());
		pgFiltrado.getChildren().add(getComponenteFiltrarUsuario());
		pgFiltrado.getChildren().add(getComponenteFiltrarFechaDesde());
		pgFiltrado.getChildren().add(getComponenteFiltrarFechaHasta());

		StaticText st1 = new StaticText();
		st1.setId("staticTextSeparadora1");
		st1.setStyleClass("barraSeparadoraVertical");
		st1.setText("|");
		st1.setEscape(false);

		pgFiltrado.getChildren().add(st1);
		pgFiltrado.getChildren().add(getComponenteFiltrar());
		pgFiltrado.getChildren().add(getComponenteLimpiarFiltrado());
		
		StaticText st2 = new StaticText();
		st2.setId("staticTextSeparadora2");
		st2.setStyleClass("barraSeparadoraVertical");
		st2.setText("|");
		st2.setEscape(false);
		
		pgFiltrado.getChildren().add(st2);
		pgFiltrado.getChildren().add(getComponenteFormatoExportar());
		pgFiltrado.getChildren().add(getComponenteExportar());
		
		this.getFacets().put("filter", pgFiltrado);

		this.setTitle("Historial de Modificaciones");
	}

	private TableColumn getTcFecha() {
		String fecha = "fecha";
		TableColumn tcFecha = new TableColumn();
		tcFecha.setWidth("100");
		tcFecha.setId("tcFecha");
		tcFecha.setHeaderText("Fecha");
		tcFecha.getChildren().add(getComponenteValorColumna(fecha));
		((TextArea) tcFecha.getChildren().get(0)).setValueBinding("converter", this._getValueBinding(armarExpressionEnBean("tablaLogs.dateTimeConverter1")));
		tcFecha.setSort(fecha);
		tcFecha.setWidth("5px");
		return tcFecha;
	}

	private TableColumn getTcUsuario() {
		String usuario = "usuario";
		TableColumn tcUsuario = new TableColumn();
		tcUsuario.setWidth("100");
		tcUsuario.setId("tcUsuario");
		tcUsuario.setHeaderText("Usuario");
		tcUsuario.getChildren().add(getComponenteValorColumna(usuario));
		tcUsuario.setSort(usuario);
		return tcUsuario;
	}

	private TableColumn getTcComentario() {
		String comentario = "comentario";
		TableColumn tcComentario = new TableColumn();
		tcComentario.setWidth("100");
		tcComentario.setId("tcComentario");
		tcComentario.setHeaderText("Comentario");
		tcComentario.getChildren().add(getComponenteValorColumna(comentario));
		tcComentario.setSort(comentario);
		tcComentario.setWidth("5px");
		return tcComentario;
	}

	private TableColumn getTcValorNuevo() {
		String valorNuevo = "valorNuevo";
		TableColumn tcValorNuevo = new TableColumn();
		tcValorNuevo.setWidth("100");
		tcValorNuevo.setId("tcValorNuevo");
		tcValorNuevo.setHeaderText("Valor Nuevo");
		tcValorNuevo.getChildren().add(getComponenteValorColumna(valorNuevo));
		tcValorNuevo.setSort(valorNuevo);
		tcValorNuevo.setWidth("5px");
		return tcValorNuevo;
	}

	private TableColumn getTcValorAnterior() {
		String valorAnterior = "valorAnterior";
		TableColumn tcValorAnterior = new TableColumn();
		tcValorAnterior.setWidth("100");
		tcValorAnterior.setId("tcValorAnterior");
		tcValorAnterior.setHeaderText("Valor Anterior");
		tcValorAnterior.getChildren().add(getComponenteValorColumna(valorAnterior));
		tcValorAnterior.setSort(valorAnterior);
		tcValorAnterior.setWidth("5px");
		return tcValorAnterior;
	}

	private TableColumn getTcTipo() {
		String tipo = "tipo";
		TableColumn tcTipo = new TableColumn();
		tcTipo.setWidth("100");
		tcTipo.setId("tcTipo");
		tcTipo.setHeaderText("Tipo");
		tcTipo.getChildren().add(getComponenteValorColumna(tipo));
		tcTipo.setSort(tipo);
		tcTipo.setWidth("5px");
		return tcTipo;
	}

	private TableColumn getTcPropiedad() {
		String propiedad = "propiedad";
		TableColumn tcPropiedad = new TableColumn();
		tcPropiedad.setWidth("100");
		tcPropiedad.setId("tcPropiedad");
		tcPropiedad.setHeaderText("Propiedad");
		tcPropiedad.getChildren().add(getComponenteValorColumna(propiedad));
		tcPropiedad.setSort(propiedad);
		tcPropiedad.setWidth("5px");
		return tcPropiedad;
	}

	private UIComponent getComponenteValorColumna(String pPropiedad) {
		TextArea ta = new TextArea();
		ta.setId("ta" + pPropiedad.replace(".", ""));
		ta.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));
		ta.setDisabled(true);
		ta.setStyleClass("textFieldDisabled");
		ta.setStyle("height: 15px;");
		ta.setColumns(10);
		ta.setRows(1);
		return ta;
	}
	
	private UIComponent getComponenteFiltrarPropiedad() {
		TextField tfU = new TextField();
		tfU.setId("tfFiltrarPropiedad");
		tfU.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("tfFiltrarPropiedad")));
		tfU.setStyleClass("textField");
		tfU.setColumns(20);
		
		return tfU;
	}
	
	private UIComponent getComponenteFiltrarUsuario() {
		TextField tfU = new TextField();
		tfU.setId("tfFiltrarUsuario");
		tfU.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("tfFiltrarUsuario")));
		tfU.setStyleClass("textField");
		tfU.setColumns(20);
		
		return tfU;
	}
	
	private UIComponent getComponenteFiltrarFechaDesde() {
		TextField tfFD = new TextField();
		tfFD.setId("tfFiltrarFechaDesde");
		tfFD.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("tfFiltrarFechaDesde")));
		tfFD.setStyleClass("textField");
		tfFD.setColumns(10);
		
		return tfFD;
	}
	
	private UIComponent getComponenteFiltrarFechaHasta() {
		TextField tfFH = new TextField();
		tfFH.setId("tfFiltrarFechaHasta");
		tfFH.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("tfFiltrarFechaHasta")));
		tfFH.setStyleClass("textField");
		tfFH.setColumns(10);
		
		return tfFH;
	}

	private UIComponent getComponenteFiltrar() {
		HtmlAjaxCommandButton btn = new HtmlAjaxCommandButton();
		btn.setId("btnFiltrar");
		btn.setStyleClass("buttonFiltrarAjax");
		btn.setReRender("tbLogsAuditoria");
		btn.setOncomplete("cargarComportamientoEnTablaLogs();");
		btn.setAction(this.getMethodBinding("setearComponentesFiltroTablaLogs_action"));
		
		return btn;
	}

	private UIComponent getComponenteLimpiarFiltrado() {
		HtmlAjaxCommandButton btn = new HtmlAjaxCommandButton();
		btn.setId("btnLimpiarFiltrado");
		btn.setStyleClass("buttonLimpiarAjax");
		btn.setReRender("tbLogsAuditoria, tfFiltrarPropiedad, tfFiltrarUsuario, tfFiltrarFechaDesde, tfFiltrarFechaHasta");
		btn.setOncomplete("cargarComportamientoEnTablaLogs();");
		btn.setAction(this.getMethodBinding("limpiarFiltradoTablaLogs_action"));

		return btn;
	}
	
	private UIComponent getComponenteFormatoExportar() {
		DropDown dd = new DropDown();
		dd.setId("ddFormatoExportar");
		dd.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("ddFormatoExportar")));
		dd.setStyleClass("textField");
		dd.setValueBinding("items", this._getValueBinding(this.armarExpressionEnBean("ddFormatoExportarDefaultOptions.options")));

		return dd;
	}
	
	private UIComponent getComponenteExportar() {
		Button btn = new Button();
		btn.setId("btnExportar");
		btn.setText("Exportar");
		btn.setStyleClass("button");
		btn.setAction(this.getMethodBinding("btnExportar_action"));
		btn.setOnClick("window.open('/Vipians/faces/ImpresionServlet');");
		
		return btn;
	}

	public RadioButton getRbSeleccion() {
		RadioButton rb = new RadioButton();
		rb.setLabel("");
		rb.setName("buttonGroupLogs");
		rb.setValueBinding("selected", this._getValueBinding(this.armarExpressionEnBean("tablaLogs.RBSelected2")));
		rb.setValueBinding("selectedValue", this._getValueBinding(this.armarExpressionEnBean("tablaLogs.currentRow2")));
		rb.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("radioButton1")));
		rb.setOnClick("checkUncheck(this)");

		return rb;
	}

	private TableRowGroup getTableRowGroupPaginacion() {
		this.tableRowGroup = new TableRowGroup();
		this.tableRowGroup.setEmptyDataMsg("Ningún registro encontrado");
		this.tableRowGroup.setSourceVar("currentRow2");
		this.tableRowGroup.setId("tableRowGroup1");
		this.tableRowGroup.setValueBinding("sourceData", this._getValueBinding(this.armarExpressionEnBean("tablaLogs.ldpLogs")));
		this.tableRowGroup.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("tablaLogs.tableRowGroup")));
		this.tableRowGroup.setOnMouseOver("jsRowMouseOver(this)");
		this.tableRowGroup.setOnMouseOut("jsRowMouseOut(this)");
		this.tableRowGroup.setOnClick("jsRowMouseClick(this)");
		this.tableRowGroup.setOnDblClick("funcionSeleccionar()");
		return this.tableRowGroup;
	}

	private String getCurrentRowValue(String pValue) {
		int indice = pValue.indexOf(".");

		String retorno = "#{currentRow2.value['" + (indice != -1 ? pValue.substring(0, indice) + "']" + pValue.substring(indice) + "}" : pValue + "']}");
		return retorno;
	}

	private ValueBinding _getValueBinding(String pPropiedad) {
		return application.createValueBinding(pPropiedad);
	}

	private String armarExpressionEnBean(String propiedad) {
		StringBuilder sb = new StringBuilder(nombreBean);
		sb.insert(nombreBean.length() - 1, "." + propiedad);
		return sb.toString();
	}

	public TableRowGroup getTableRowGroup() {
		return this.tableRowGroup;
	}

	public void setTableRowGroup(TableRowGroup pTableRowGroup) {
	}

	protected ObjectListDataProvider ldpLogs = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLogs() {
		return ldpLogs;
	}

	public void setLdpLogs(ObjectListDataProvider ldpLogs) {
		this.ldpLogs = ldpLogs;
	}

	public String getCurrentRow2() {
		return this.getTableRowGroup().getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	private Object lastSelectedLogs = null;

	public Object getRBSelected2() {
		String sv = this.getRbSeleccion().getSelectedValue().toString();
		return sv.equals(lastSelectedLogs) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelectedLogs = selected;
		}
	}

	public RowKey getSeleccionadoLogs() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupLogs");
			rk = this.getLdpLogs().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}
}
