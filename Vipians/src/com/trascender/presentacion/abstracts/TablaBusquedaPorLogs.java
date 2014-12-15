package com.trascender.presentacion.abstracts;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.el.ValueBinding;
import javax.faces.event.MethodExpressionActionListener;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.transients.AtributoConsultable;

public class TablaBusquedaPorLogs extends Table{

	private String nombreBean;
	//    private FiltroAbstracto filtro;
	//El mapa que relaciona el nombre interno con el atributo consultable
	private final Map<String, AtributoConsultable> mapaAtributosConsultables = new LinkedHashMap<String, AtributoConsultable>();
	//El mapa que relaciona el nombre interno con el nombre de la columna, que puede variar según se haga click para ordenar.
	private Map<String, String> mapaNombreColumnas = new LinkedHashMap<String, String>();

	private final FacesContext context = FacesContext.getCurrentInstance();
	private final Application application = context.getApplication();
	private final ELContext elContext = context.getELContext();
	private final ExpressionFactory elFactory = context.getApplication().getExpressionFactory();

	private TableRowGroup tableRowGroup;

	private Object objetoSeleccionado = null;

	private TableColumn tcSeleccion;

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	protected SingleSelectOptionsList ddNombrePropiedadBusquedaLogsOptions = new SingleSelectOptionsList();
	protected SingleSelectOptionsList ddUsuarioBusquedaLogsOptions = new SingleSelectOptionsList();
	protected SingleSelectOptionsList ddEventoBusquedaLogsOptions = new SingleSelectOptionsList();

	protected PanelGroup groupPanelTablaBusquedaLogs = new PanelGroup();

	private Checkbox ckbBuscarPorLogs = new Checkbox();

	public TablaBusquedaPorLogs() {
		super();
	}

	public Checkbox getCkbBuscarPorLogs() {
		return ckbBuscarPorLogs;
	}

	public void setCkbBuscarPorLogs(Checkbox ckbBuscarPorLogs) {
		this.ckbBuscarPorLogs = ckbBuscarPorLogs;
	}

	public TablaBusquedaPorLogs(String pNombreBean){
		super();
		this.setWidth("100%");

		if(pNombreBean != null){
			AtributoConsultable nombrePropiedad = new AtributoConsultable("nombrePropiedad", "Nombre Propiedad", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(nombrePropiedad.getNombreInterno(), nombrePropiedad);

			AtributoConsultable evento = new AtributoConsultable("evento", "Evento", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(evento.getNombreInterno(), evento);

			AtributoConsultable valor = new AtributoConsultable("valor", "Valor", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(valor.getNombreInterno(), valor);

			AtributoConsultable usuario = new AtributoConsultable("usuario", "Usuario", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(usuario.getNombreInterno(), usuario);

			AtributoConsultable fechaDesde = new AtributoConsultable("fechaDesde", "Fecha Desde", AtributoConsultable.Tipo.FECHA);
			this.mapaAtributosConsultables.put(fechaDesde.getNombreInterno(), fechaDesde);

			AtributoConsultable fechaHasta = new AtributoConsultable("fechaHasta", "Fecha Hasta", AtributoConsultable.Tipo.FECHA);
			this.mapaAtributosConsultables.put(fechaHasta.getNombreInterno(), fechaHasta);

			AtributoConsultable comentario = new AtributoConsultable("comentario", "Comentario", AtributoConsultable.Tipo.TEXTO);
			this.mapaAtributosConsultables.put(comentario.getNombreInterno(), comentario);

			this.nombreBean = pNombreBean;

			this.ckbBuscarPorLogs.setId("ckbBuscarPorLogs");
			this.ckbBuscarPorLogs.setLabel("Filtrar por modificaciones");
			HtmlAjaxSupport support = new HtmlAjaxSupport();
			support.setId("supportCkb");
			support.setEvent("onChange");
			support.setReRender("form1:tablaBusquedaLogs");
			try {
				Class[] parameterList = { Class.forName("javax.faces.event.ActionEvent") };
				support.setActionListener(application.createMethodBinding(
						this.armarExpressionEnBean("valueChangeEventBusquedaLogs()"),parameterList));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			this.ckbBuscarPorLogs.getChildren().add(support);

			dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
			dateTimeConverter1.setPattern("dd/MM/yyyy hh:mm:ss");
			dateTimeConverter1.setTimeStyle("full");

			this.init();
		}
	}

	private void init(){
		this.inicializarColumnas();
	}

	private void inicializarColumnas(){
		this.getTableRowGroupPaginacion();

		tcSeleccion = this.getTableColumnSeleccion();
		tableRowGroup.getChildren().add(tcSeleccion);

		TableColumn tcNombrePropiedad = getTcNombrePropiedad();
		tableRowGroup.getChildren().add(tcNombrePropiedad);

		TableColumn tcEvento = getTcEvento();
		tableRowGroup.getChildren().add(tcEvento);

		TableColumn tcValor = getTcValor();
		tableRowGroup.getChildren().add(tcValor);

		TableColumn tcUsuario = getTcUsuario();
		tableRowGroup.getChildren().add(tcUsuario);

		TableColumn tcFechaDesde = getTcFechaDesde();
		tableRowGroup.getChildren().add(tcFechaDesde);

		TableColumn tcFechaHasta = getTcFechaHasta();
		tableRowGroup.getChildren().add(tcFechaHasta);

		TableColumn tcComentario = getTcComentario();
		tableRowGroup.getChildren().add(tcComentario);

		this.btnAgregarBusquedaLogs.setValue("Agregar");
		this.btnAgregarBusquedaLogs.setId("btnAgregarBusquedaLogs");
		this.btnAgregarBusquedaLogs.addActionListener(getActionListenerEnBean("btnAgregarBusqueda_action(evento)"));
		this.btnAgregarBusquedaLogs.setReRender("tablaBusquedaLogs");
		this.btnAgregarBusquedaLogs.setStyleClass("btnAjax");
		this.btnAgregarBusquedaLogs.setOncomplete("changeStyleAlIngresar()");
		this.getGroupPanelTablaBusquedaLogs().getChildren().add(this.btnAgregarBusquedaLogs);

		this.btnQuitarBusquedaLogs.setValue("Quitar");
		this.btnQuitarBusquedaLogs.setId("btnQuitarBusquedaLogs");
		this.btnQuitarBusquedaLogs.addActionListener(getActionListenerEnBean("btnQuitarBusqueda_action(evento)"));
		this.btnQuitarBusquedaLogs.setReRender("tablaBusquedaLogs");
		this.btnQuitarBusquedaLogs.setStyleClass("btnAjax");
		this.btnQuitarBusquedaLogs.setOncomplete("changeStyleAlIngresar()");
		this.getGroupPanelTablaBusquedaLogs().getChildren().add(this.btnQuitarBusquedaLogs);

		this.btnQuitarTodosBusquedaLogs.setValue("Quitar Todos");
		this.btnQuitarTodosBusquedaLogs.setId("btnQuitarTodosBusquedaLogs");
		this.btnQuitarTodosBusquedaLogs.addActionListener(getActionListenerEnBean("btnQuitarTodosBusqueda_action(evento)"));
		this.btnQuitarTodosBusquedaLogs.setReRender("tablaBusquedaLogs");
		this.btnQuitarTodosBusquedaLogs.setStyleClass("btnAjax");
		this.btnQuitarTodosBusquedaLogs.setOncomplete("changeStyleAlIngresar()");
		this.getGroupPanelTablaBusquedaLogs().getChildren().add(this.btnQuitarTodosBusquedaLogs);

		this.btnAgregarBusquedaLogs.setValueBinding("rendered", this._getValueBinding(armarExpressionEnBean("paginatedTable.filtro.buscarPorLogs")));
		this.btnQuitarBusquedaLogs.setValueBinding("rendered", this._getValueBinding(armarExpressionEnBean("paginatedTable.filtro.buscarPorLogs")));
		this.btnQuitarTodosBusquedaLogs.setValueBinding("rendered", this._getValueBinding(armarExpressionEnBean("paginatedTable.filtro.buscarPorLogs")));

		this.setValueBinding("style", this._getValueBinding(armarExpressionEnBean("estiloTabla")));

		this.getChildren().add(tableRowGroup);
		this.getFacets().put(Table.ACTIONS_TOP_FACET, this.getGroupPanelTablaBusquedaLogs());
	}

	private TableColumn getTcComentario() {
		String comentario = "comentario";
		TableColumn tcComentario = new TableColumn();
		tcComentario.setWidth("100");
		tcComentario.setId("tcComentario");
		tcComentario.getFacets().put("header", getHpHeaderColumna(comentario));
		tcComentario.setHeaderText(this.getMapaNombreColumnas().get(comentario));
		tcComentario.getChildren().add(getComponenteValorColumna(comentario, "TextArea"));
		tcComentario.setSort(comentario);
		return tcComentario;
	}

	private TableColumn getTcFechaHasta() {
		String fechaHasta = "fechaHasta";
		TableColumn tcFechaHasta = new TableColumn();
		tcFechaHasta.setWidth("100");
		tcFechaHasta.setId("tcFechaHasta");
		tcFechaHasta.getFacets().put("header", getHpHeaderColumna(fechaHasta));
		tcFechaHasta.setHeaderText(this.getMapaNombreColumnas().get(fechaHasta));
		tcFechaHasta.getChildren().add(getComponenteValorColumna(fechaHasta, "TextField"));
		((TextField)tcFechaHasta.getChildren().get(0)).setColumns(10);
		((TextField)tcFechaHasta.getChildren().get(0)).setOnKeyUp("mascara(this,'/',patronFecha,true)");
		((TextField)tcFechaHasta.getChildren().get(0)).setValueBinding("converter", this._getValueBinding(armarExpressionEnBean("tablaBusquedaLogs.dateTimeConverter1")));
		tcFechaHasta.setSort(fechaHasta);
		return tcFechaHasta;
	}

	private TableColumn getTcFechaDesde() {
		String fechaDesde = "fechaDesde";
		TableColumn tcFechaDesde = new TableColumn();
		tcFechaDesde.setWidth("100");
		tcFechaDesde.setId("tcFechaDesde");
		tcFechaDesde.getFacets().put("header", getHpHeaderColumna(fechaDesde));
		tcFechaDesde.setHeaderText(this.getMapaNombreColumnas().get(fechaDesde));
		tcFechaDesde.getChildren().add(getComponenteValorColumna(fechaDesde, "TextField"));
		((TextField)tcFechaDesde.getChildren().get(0)).setColumns(10);
		((TextField)tcFechaDesde.getChildren().get(0)).setOnKeyUp("mascara(this,'/',patronFecha,true)");
		((TextField)tcFechaDesde.getChildren().get(0)).setValueBinding("converter", this._getValueBinding(armarExpressionEnBean("tablaBusquedaLogs.dateTimeConverter1")));
		tcFechaDesde.setSort(fechaDesde);
		return tcFechaDesde;
	}

	private TableColumn getTcUsuario() {
		String usuario = "usuario";
		TableColumn tcUsuario = new TableColumn();
		tcUsuario.setWidth("100");
		tcUsuario.setId("tcUsuario");
		tcUsuario.getFacets().put("header", getHpHeaderColumna(usuario));
		tcUsuario.setHeaderText(this.getMapaNombreColumnas().get(usuario));
		tcUsuario.getChildren().add(getComponenteValorColumna(usuario, "DropDown"));
		tcUsuario.setSort(usuario);
		return tcUsuario;
	}

	private TableColumn getTcValor() {
		String valor = "valor";
		TableColumn tcValor = new TableColumn();
		tcValor.setWidth("100");
		tcValor.setId("tcValor");
		tcValor.getFacets().put("header", getHpHeaderColumna(valor));
		tcValor.setHeaderText(this.getMapaNombreColumnas().get(valor));
		tcValor.getChildren().add(getComponenteValorColumna(valor, "TextField"));
		tcValor.setSort(valor);
		return tcValor;
	}

	private TableColumn getTcEvento() {
		String evento = "evento";
		TableColumn tcEvento = new TableColumn();
		tcEvento.setWidth("100");
		tcEvento.setId("tcEvento");
		tcEvento.getFacets().put("header", getHpHeaderColumna(evento));
		tcEvento.setHeaderText(this.getMapaNombreColumnas().get(evento));
		tcEvento.getChildren().add(getComponenteValorColumna(evento, "DropDown"));
		tcEvento.setSort(evento);
		return tcEvento;
	}

	private TableColumn getTcNombrePropiedad() {
		String nombrePropiedad = "nombrePropiedad";
		TableColumn tcNombrePropiedad = new TableColumn();
		tcNombrePropiedad.setWidth("100");
		tcNombrePropiedad.setId("tcNombrePropiedad");
		tcNombrePropiedad.getFacets().put("header", getHpHeaderColumna(nombrePropiedad));
		tcNombrePropiedad.setHeaderText(this.getMapaNombreColumnas().get(nombrePropiedad));
		tcNombrePropiedad.getChildren().add(getComponenteValorColumna(nombrePropiedad, "DropDown"));
		tcNombrePropiedad.setSort(nombrePropiedad);
		return tcNombrePropiedad;
	}

	private MethodExpressionActionListener getActionListenerEnBean(String pValor) {
		return this.getActionListener(this.armarExpressionEnBean(pValor));
	}

	private MethodExpressionActionListener getActionListener(String pValor) {
		Class[] args = new Class[] { javax.faces.event.ActionEvent.class };
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);
		return listener;
	}

	private ValueExpression _getValueExpression(String pValue) {
		ValueExpression valueExpresion = application.getExpressionFactory().createValueExpression(elContext, pValue, Object.class);
		return valueExpresion;
	}

	private UIComponent getComponenteValorColumna(String pPropiedad, String tipo){
		if(tipo.equals("DropDown")) return this.getDropDownPropiedad(pPropiedad);
		else if(tipo.equals("TextField")) return this.getTextFieldPropiedad(pPropiedad);
		else if(tipo.equals("TextArea")) return this.getTextAreaPropiedad(pPropiedad);
		return null;
	}

	private DropDown getDropDownPropiedad(String pPropiedad){
		DropDown dd = new DropDown();
		dd.setId("dd"+pPropiedad.substring(0,1).toUpperCase() + pPropiedad.substring(1) + "BusquedaLogs");
		dd.setValueBinding("selected", this._getValueBinding(getCurrentRowValue(pPropiedad)));
		dd.setValueBinding("items", this._getValueBinding(armarExpressionEnBean("tablaBusquedaLogs.dd" + pPropiedad.substring(0,1).toUpperCase() + pPropiedad.substring(1) + "BusquedaLogsOptions.options")));
		return dd;
	}

	private TextField getTextFieldPropiedad(String pPropiedad){
		TextField tf = new TextField();
		tf.setId("tf"+pPropiedad.replace(".", ""));
		tf.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));
		return tf;
	}

	private TextArea getTextAreaPropiedad(String pPropiedad){
		TextArea ta = new TextArea();
		ta.setId("tf"+pPropiedad.replace(".", ""));
		ta.setColumns(10);
		ta.setStyle("height: 15px;");
		ta.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));
		return ta;
	}

	private StaticText getHpHeaderColumna(String pPropiedad){
		StaticText st = new StaticText();
		st.setStyle("color:#454545; font-size: 11pt; width: 780px; height: 15px; font-weight: bold;");
		st.setValueBinding("text", this._getValueBinding(this.armarExpressionEnBean("tablaBusquedaLogs.mapaNombreColumnas['"+pPropiedad+"']")));
		this.mapaNombreColumnas.put(pPropiedad, mapaAtributosConsultables.get(pPropiedad).getNombreExterno());
		return st;
	}

	private TableColumn getTableColumnSeleccion(){
		TableColumn tc = new TableColumn();
		tc.setAlign("center");
		tc.setId("tableColumn1");
		tc.setValign("middle");
		tc.setWidth("10");
		tc.getChildren().add(this.getRbSeleccion());
		//        tc.setSelectId("buttonGroup");
		//        tc.setOnClick("function initAllRows() {"+
		//            "var table = document.getElementById('form1:table1');"+
		//            "table.initAllRows();"+
		//            "}"+
		//            "setTimeout(initAllRows(), 0);");
		return tc;
	}

	public RadioButton getRbSeleccion(){
		RadioButton rb = new RadioButton();
		rb.setLabel("");
		rb.setName("buttonGroupBusquedaLogs");
		//        rb.setValueExpression("selected", this._getValueExpression(this.armarExpressionEnBean("RBSelected")));
		//        rb.setValueExpression("selectedValue", this._getValueExpression(this.armarExpressionEnBean("currentRow")));.
		rb.setValueBinding("selected", this._getValueBinding(this.armarExpressionEnBean("tablaBusquedaLogs.RBSelected2")));
		rb.setValueBinding("selectedValue", this._getValueBinding(this.armarExpressionEnBean("tablaBusquedaLogs.currentRow2")));
		rb.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("radioButton1")));
//		rb.setOnClick("checkUncheck(this)");	// comentado junto con los eventos del table row group en el metodo de abajo

		return rb;
	}

	private TableRowGroup getTableRowGroupPaginacion(){
		this.tableRowGroup = new TableRowGroup();
		this.tableRowGroup.setEmptyDataMsg("Ningún registro encontrado");
		this.tableRowGroup.setSourceVar("currentRow2");
		this.tableRowGroup.setId("tableRowGroup1");
		this.tableRowGroup.setValueBinding("sourceData", this._getValueBinding(this.armarExpressionEnBean("tablaBusquedaLogs.ldpBusquedaLogs")));
		this.tableRowGroup.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("tablaBusquedaLogs.tableRowGroup")));
		
		//Comentado para q no marque/desmarque las filas seleccionadas.
//		this.tableRowGroup.setOnMouseOver("jsRowMouseOver(this)");
//		this.tableRowGroup.setOnMouseOut("jsRowMouseOut(this)");
//		this.tableRowGroup.setOnClick("jsRowMouseClick(this)");
//		this.tableRowGroup.setOnDblClick("funcionSeleccionar()");
		return this.tableRowGroup;
	}

	private String getCurrentRowValue(String pValue){
		int indice = pValue.indexOf(".");

		String retorno = "#{currentRow2.value['"+(indice != -1 ?
				pValue.substring(0, indice) + "']" + pValue.substring(indice) + "}"
				: pValue + "']}");
		return retorno;
	}

	private ValueBinding _getValueBinding(String pPropiedad){
		return application.createValueBinding(pPropiedad);
	}

	private String armarExpressionEnBean(String propiedad){
		StringBuilder sb = new StringBuilder(nombreBean);
		sb.insert(nombreBean.length() - 1, "."+propiedad);
		return sb.toString();
	}

	public void resetearOrdenamientos() {
		this.resetearCabeceras();
	}

	private void resetearCabeceras() {
		for (String cadaLlave : mapaNombreColumnas.keySet()) {
			mapaNombreColumnas.put(cadaLlave, mapaAtributosConsultables.get(cadaLlave).getNombreExterno());
		}
	}

	public Map<String, String> getMapaNombreColumnas() {
		return mapaNombreColumnas;
	}

	public void setMapaNombreColumnas(Map<String, String> mapaNombreColumnas) {
		this.mapaNombreColumnas = mapaNombreColumnas;
	}

	public TableRowGroup getTableRowGroup(){
		return this.tableRowGroup;
	}

	public void setTableRowGroup(TableRowGroup pTableRowGroup){

	}

	public TableColumn getTcSeleccion() {
		return tcSeleccion;
	}

	public void setTcSeleccion(TableColumn tcSeleccion) {
		this.tcSeleccion = tcSeleccion;
	}

	public Object getObjetoSeleccionado() {
		return objetoSeleccionado;
	}

	public void setObjetoSeleccionado(Object objetoSeleccionado) {
		this.objetoSeleccionado = objetoSeleccionado;
	}

	public SingleSelectOptionsList getDdNombrePropiedadBusquedaLogsOptions() {
		return ddNombrePropiedadBusquedaLogsOptions;
	}

	public void setDdNombrePropiedadBusquedaLogsOptions(
			SingleSelectOptionsList ddNombrePropiedadBusquedaLogsOptions) {
		this.ddNombrePropiedadBusquedaLogsOptions = ddNombrePropiedadBusquedaLogsOptions;
	}

	public SingleSelectOptionsList getDdUsuarioBusquedaLogsOptions() {
		return ddUsuarioBusquedaLogsOptions;
	}

	public void setDdUsuarioBusquedaLogsOptions(
			SingleSelectOptionsList ddUsuarioBusquedaLogsOptions) {
		this.ddUsuarioBusquedaLogsOptions = ddUsuarioBusquedaLogsOptions;
	}

	public SingleSelectOptionsList getDdEventoBusquedaLogsOptions() {
		return ddEventoBusquedaLogsOptions;
	}

	public void setDdEventoBusquedaLogsOptions(
			SingleSelectOptionsList ddEventoBusquedaLogsOptions) {
		this.ddEventoBusquedaLogsOptions = ddEventoBusquedaLogsOptions;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	protected ObjectListDataProvider ldpBusquedaLogs = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpBusquedaLogs() {
		return ldpBusquedaLogs;
	}

	public void setLdpBusquedaLogs(ObjectListDataProvider ldpBusquedaLogs) {
		this.ldpBusquedaLogs = ldpBusquedaLogs;
	}

	public String getCurrentRow2() {
		return this.getTableRowGroup().getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	private Object lastSelectedBusquedaLogs = null;

	public Object getRBSelected2() {
		String sv = this.getRbSeleccion().getSelectedValue().toString();
		return sv.equals(lastSelectedBusquedaLogs) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if (selected != null) {
			lastSelectedBusquedaLogs = selected;
		}
	}

	public RowKey getSeleccionadoBusquedaLogs() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupBusquedaLogs");
			rk = this.getLdpBusquedaLogs().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	protected HtmlAjaxCommandButton btnAgregarBusquedaLogs = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarBusquedaLogs = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosBusquedaLogs = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnAgregarBusquedaLogs() {
		return btnAgregarBusquedaLogs;
	}

	public void setBtnAgregarBusquedaLogs(
			HtmlAjaxCommandButton btnAgregarBusquedaLogs) {
		this.btnAgregarBusquedaLogs = btnAgregarBusquedaLogs;
	}

	public HtmlAjaxCommandButton getBtnQuitarBusquedaLogs() {
		return btnQuitarBusquedaLogs;
	}

	public void setBtnQuitarBusquedaLogs(HtmlAjaxCommandButton btnQuitarBusquedaLogs) {
		this.btnQuitarBusquedaLogs = btnQuitarBusquedaLogs;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosBusquedaLogs() {
		return btnQuitarTodosBusquedaLogs;
	}

	public void setBtnQuitarTodosBusquedaLogs(
			HtmlAjaxCommandButton btnQuitarTodosBusquedaLogs) {
		this.btnQuitarTodosBusquedaLogs = btnQuitarTodosBusquedaLogs;
	}

	public PanelGroup getGroupPanelTablaBusquedaLogs() {
		return groupPanelTablaBusquedaLogs;
	}

	public void setGroupPanelTablaBusquedaLogs(
			PanelGroup groupPanelTablaBusquedaLogs) {
		this.groupPanelTablaBusquedaLogs = groupPanelTablaBusquedaLogs;
	}

}
