package com.trascender.presentacion.abstracts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.el.ValueBinding;
import javax.faces.event.MethodExpressionActionListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Hyperlink;
import com.sun.rave.web.ui.component.ImageHyperlink;
import com.sun.rave.web.ui.component.Label;
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
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.recurso.transients.AtributoConsultable;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.util.FiltroAbstracto;

public class PaginatedTable extends Table {

	private String nombreBean;
	private FiltroAbstracto filtro;

	long idRecurso;

	// El mapa que relaciona el nombre interno con el atributo consultable
	private final Map<String, AtributoConsultable> mapaAtributosConsultables = new LinkedHashMap<String, AtributoConsultable>();

	// El mapa que relaciona el nombre interno con el nombre de la columna, que puede variar según se hagla click para ordenar.
	private Map<String, String> mapaNombreColumnas = new LinkedHashMap<String, String>();

	private final FacesContext context = FacesContext.getCurrentInstance();
	private final Application application = context.getApplication();
	private final ELContext elContext = context.getELContext();
	private final ExpressionFactory elFactory = context.getApplication().getExpressionFactory();

	private boolean goNextPageDeshabilitado = true;
	private boolean goBackPageDeshabilitado = true;
	private boolean goLastPageDeshabilitado = true;
	private boolean goFirstPageDeshabilitado = true;
	private boolean mostrarLimpiarOrdenamiento = false;

	private Integer cantidadPaginas = 0;

	private TableRowGroup tableRowGroup;

	private ImageHyperlink botonOrdenamiento;
	private StaticText stSeparadorOrdenamiento;

	private Object objetoSeleccionado = null;

	private TableColumn tcSeleccion;

	TableColumn tcSeleccionMultiple;

	DropDown ddReportes;
	List<Reporte> listaReportes;

	public PaginatedTable() {
		super();
	}

	public PaginatedTable(Set<AtributoConsultable> pListaAtributos, String pNombreBean, FiltroAbstracto filtroAbstracto) {
		super();
		if(pListaAtributos != null) {
			for(AtributoConsultable cadaAtributo : pListaAtributos) {
				this.mapaAtributosConsultables.put(cadaAtributo.getNombreInterno(), cadaAtributo);
			}
		}
		this.nombreBean = pNombreBean;
		this.filtro = filtroAbstracto;

		this.ddReportes = new DropDown();

		this.setCellPadding("0");
		this.setCellSpacing("0");

		this.init();
	}

	private void init() {
		this.inicializarColumnas();
		this.inicializarBotonOrdenamiento();
	}

	private void inicializarColumnas() {
		TableRowGroup trg = this.getTableRowGroupPaginacion();

		tcSeleccion = this.getTableColumnSeleccion();
		tcSeleccionMultiple = this.getTableColumnSeleccionMultiple();

		trg.getChildren().add(tcSeleccion);
		trg.getChildren().add(tcSeleccionMultiple);

		for(String cadaColumna : mapaAtributosConsultables.keySet()) {
			TableColumn tcPropiedad = this.getTableColumnPropiedad(cadaColumna);

			Integer ancho = mapaAtributosConsultables.get(cadaColumna).getAnchoColumna();
			if(ancho != null && ancho > 0) {
				tcPropiedad.setWidth(ancho.toString());
			}

			trg.getChildren().add(tcPropiedad);
		}

		trg.getFacets().put(TableRowGroup.FOOTER_FACET, this.getPanelGroupPaginacion());
		this.getFacets().put(Table.TITLE_FACET, this.getPanelGroupReportes());

		this.getChildren().add(trg);
		this.tableRowGroup = trg;
	}

	private TableColumn getTableColumnPropiedad(String pPropiedad) {
		TableColumn tc = new TableColumn();
		tc.setWidth("1000");
		tc.setId("tc" + pPropiedad.replace(".", "").replace("'", "").replace(" ", ""));
		tc.getFacets().put("header", getHpHeaderColumna(pPropiedad));
		tc.setHeaderText(this.getMapaNombreColumnas().get(pPropiedad));
		tc.getChildren().add(getComponenteValorColumna(pPropiedad));
		tc.setSort(pPropiedad);

		return tc;
	}

	private UIComponent getComponenteValorColumna(String pPropiedad) {
		AtributoConsultable locAtributo = this.mapaAtributosConsultables.get(pPropiedad);
		switch(locAtributo.getTipo()) {
			case TEXTO:
				return this.getStaticTextPropiedad(pPropiedad);
			case FECHA:
				return this.getStaticTextPropiedadFecha(pPropiedad);
			case FECHA_HORA:
				return this.getStaticTextPropiedadFechaHora(pPropiedad);
			case MONTO:
				return this.getStaticTextPropiedadMonto(pPropiedad);
			case TEXTO_LARGO:
				return this.getTextAreaPropiedad(pPropiedad);
			case BOOLEANO:
				return this.getCheckBoxPropiedad(pPropiedad);
			case ATRIBUTO_DINAMICO:
				return this.getStaticTextAtributoDinamico(pPropiedad);
		}

		return null;
	}
	
	private StaticText getStaticTextAtributoDinamico(String pPropiedad) {
		StaticText st = new StaticText();
		st.setId("st" + pPropiedad.replace(".", "").replace("'", "").replace(" ", ""));
		st.setValueBinding("text", this._getValueBinding(this.armarExpressionEnBean(getCurrentRowAtributoDinamico(pPropiedad))));
		return st;
	}
	private String getCurrentRowAtributoDinamico(String nombreAtributo) {
		return "obtenerMapaAtributosDinamicos(currentRow)['" + nombreAtributo + "']";
	}

	private Checkbox getCheckBoxPropiedad(String pPropiedad) {
		Checkbox cb = new Checkbox();
		cb.setId("ta" + pPropiedad.replace(".", "").replace("'", "").replace(" ", ""));
		cb.setDisabled(true);
		cb.setValueBinding("selected", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return cb;
	}

	private TextArea getTextAreaPropiedad(String pPropiedad) {
		TextArea ta = new TextArea();
		ta.setId("ta" + pPropiedad.replace(".", "").replace("'", "").replace(" ", ""));
		ta.setReadOnly(true);
		ta.setStyleClass("textFieldDisabled");
		ta.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		TextArea aux = new TextArea();
		ta.setStyle("width: 400px; height: 15px;");
		aux.setDisabled(true);
		ta.getFacets().put("readOnly", aux);

		return ta;
	}

	private StaticText getStaticTextPropiedad(String pPropiedad) {
		StaticText st = new StaticText();
		st.setId("st" + pPropiedad.replace(".", "").replace("'", "").replace(" ", ""));
		st.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return st;
	}

	private StaticText getStaticTextPropiedadFecha(String pPropiedad) {
		StaticText st = new StaticText();
		st.setId("st" + pPropiedad.replace(".", "").replace("'", "").replace(" ", ""));
		DateTimeConverter locConverter = new DateTimeConverter();
		locConverter.setPattern("dd/MM/yyyy");
		locConverter.setTimeZone(TimeZone.getDefault());
		st.setConverter(locConverter);
		st.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return st;
	}

	private StaticText getStaticTextPropiedadFechaHora(String pPropiedad) {
		StaticText st = new StaticText();
		st.setId("st" + pPropiedad.replace(".", "").replace("'", "").replace(" ", ""));
		DateTimeConverter locConverter = new DateTimeConverter();
		locConverter.setPattern("dd/MM/yyyy hh:mm:ss");
		locConverter.setTimeZone(TimeZone.getDefault());
		st.setConverter(locConverter);
		st.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return st;
	}

	private StaticText getStaticTextPropiedadMonto(String pPropiedad) {
		StaticText st = new StaticText();
		st.setId("st" + pPropiedad.replace(".", "").replace("'", "").replace(" ", ""));
		NumberConverter locConverter = new NumberConverter();
		locConverter.setPattern("$ #,##0.00");
		st.setConverter(locConverter);
		st.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return st;
	}

	private UIParameter getUIParameter(String pPropiedad) {
		UIParameter uiParameter = new UIParameter();
		uiParameter.setName("criterioOrdenamiento");
		uiParameter.setValue(pPropiedad);

		return uiParameter;
	}

	private Hyperlink getHpHeaderColumna(String pPropiedad) {
		Hyperlink hp = new Hyperlink();
		hp.addActionListener(this.getActionListener(this.armarExpressionEnBean("ordenarPor(javax.faces.event.ActionEvent)")));
		hp.getChildren().add(this.getUIParameter(pPropiedad));
		hp.setStyle("font-size: 11pt; margin: 7px; font-weight: bold;");
		hp.setValueBinding("text", this._getValueBinding(this.armarExpressionEnBean("paginatedTable.mapaNombreColumnas['" + pPropiedad + "']")));
		this.mapaNombreColumnas.put(pPropiedad, mapaAtributosConsultables.get(pPropiedad).getNombreExterno());

		return hp;
	}

	private TableColumn getTableColumnSeleccion() {
		TableColumn tc = new TableColumn();
		tc.setAlign("center");
		tc.setId("tableColumn1");
		tc.setValign("middle");
		tc.setWidth("10");
		tc.getChildren().add(this.getRbSeleccion());
		// tc.setValueExpression("rendered", this._getValueExpression(this.armarExpressionEnBean("elementoPila.seleccionMultiple")));

		return tc;
	}

	private TableColumn getTableColumnSeleccionMultiple() {
		TableColumn tc = new TableColumn();
		tc.setAlign("center");
		tc.setId("tableColumn2");
		tc.setValign("middle");
		tc.setWidth("10");
		tc.getChildren().add(this.getCkbSeleccion());
		// tc.setSelectId("buttonGroup");
		tc.setOnClick("function initAllRows() {" + "var table = document.getElementById('form1:table1');" + "table.initAllRows();" + "}" + "setTimeout(initAllRows(), 0);");
		// tc.setValueExpression("rendered", this._getValueExpression(this.armarExpressionEnBean("elementoPila.seleccionMultiple")));
		
		return tc;
	}

	public RadioButton getRbSeleccion() {
		RadioButton rb = new RadioButton();
		rb.setLabel("");
		rb.setName("buttonGroup");
		// rb.setValueExpression("selected",
		// this._getValueExpression(this.armarExpressionEnBean("RBSelected")));
		// rb.setValueExpression("selectedValue",
		// this._getValueExpression(this.armarExpressionEnBean("currentRow")));.
		rb.setValueBinding("selected", this._getValueBinding(this.armarExpressionEnBean("RBSelected")));
		rb.setValueBinding("selectedValue", this._getValueBinding(this.armarExpressionEnBean("currentRow")));
		rb.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("radioButton1")));
		rb.setOnClick("checkUncheck(this)");

		return rb;
	}

	public Checkbox getCkbSeleccion() {
		Checkbox ckb = new Checkbox();
		ckb.setLabel("");
		ckb.setName("buttonGroup1");
		ckb.setValueBinding("selected", this._getValueBinding(this.armarExpressionEnBean("selected")));
		ckb.setValueBinding("selectedValue", this._getValueBinding(this.armarExpressionEnBean("currentRow")));
		ckb.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("checkbox1")));
		ckb.setOnClick("checkUncheck(this)");

		return ckb;
	}

	private TableRowGroup getTableRowGroupPaginacion() {
		TableRowGroup trg = new TableRowGroup();
		trg.setEmptyDataMsg("Ningún registro encontrado");
		trg.setSourceVar("currentRow");
		trg.setId("tableRowGroup1");
		trg.setValueBinding("sourceData", this._getValueBinding(this.armarExpressionEnBean("objectListDataProvider")));
		trg.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("paginatedTable.tableRowGroup")));
		trg.setOnMouseOver("jsRowMouseOver(this)");
		trg.setOnMouseOut("jsRowMouseOut(this)");
		trg.setOnClick("jsRowMouseClick(this)");
		trg.setOnDblClick("funcionSeleccionar()");

		return trg;
	}

	private String getCurrentRowValue(String pValue) {
		int indice = pValue.indexOf(".");
		String retorno = "#{currentRow.value['" + (indice != -1 ? pValue.substring(0, indice) + "']" + pValue.substring(indice) + "}" : pValue + "']}");

		return retorno;
	}

	private PanelGroup getPanelGroupPaginacion() {
		PanelGroup locPanelGroup = new PanelGroup();
		locPanelGroup.getChildren().add(getBotonPrimerPagina());
		locPanelGroup.getChildren().add(getBotonPaginaAnterior());
		locPanelGroup.getChildren().add(getTfNumeroPagina());
		locPanelGroup.getChildren().add(getStBarraSeparadora());
		locPanelGroup.getChildren().add(getStCantidadPaginas());
		locPanelGroup.getChildren().add(getBotonIrPagina());
		locPanelGroup.getChildren().add(getBotonPaginaSiguiente());
		locPanelGroup.getChildren().add(getBotonUltimaPagina());

		return locPanelGroup;
	}

	public TextField getTfNumeroPagina() {
		TextField tf = new TextField();
		tf.setId("tfPaginaActual");
		tf.setOnKeyPress("return ValidarNum(event,this);");
		tf.setValueBinding("text", this._getValueBinding(this.armarExpressionEnBean("paginatedTable.filtro.numeroPagina")));
		tf.setColumns(3);

		return tf;
	}

	private StaticText getStBarraSeparadora() {
		StaticText st = new StaticText();
		st.setText(" de ");

		return st;
	}

	private StaticText getStCantidadPaginas() {
		StaticText st = new StaticText();
		st.setValueBinding("text", this._getValueBinding(this.armarExpressionEnBean("paginatedTable.cantidadPaginas")));

		return st;
	}

	private HtmlAjaxCommandButton getBotonUltimaPagina() {
		HtmlAjaxCommandButton locButton = new HtmlAjaxCommandButton();
		locButton.setValue(">>");
		locButton.addActionListener(getActionListenerEnBean("goLastPage(evento)"));
		locButton.setValueExpression("disabled", this._getValueExpression(this.armarExpressionEnBean("paginatedTable.goLastPageDeshabilitado")));
		locButton.setReRender("table1");
		locButton.setStyleClass("btnAjax");
		locButton.setOncomplete("changeStyleAlIngresar()");

		return locButton;
	}

	private HtmlAjaxCommandButton getBotonIrPagina() {
		HtmlAjaxCommandButton locButton = new HtmlAjaxCommandButton();
		locButton.setId("btnIrPagina");
		locButton.setValue("Ir");
		locButton.addActionListener(getActionListenerEnBean("goPage()"));
		locButton.setReRender("table1");
		locButton.setStyleClass("btnAjax");
		locButton.setOncomplete("changeStyleAlIngresar()");

		return locButton;
	}

	private HtmlAjaxCommandButton getBotonPaginaSiguiente() {
		HtmlAjaxCommandButton locButton = new HtmlAjaxCommandButton();
		locButton.setValue(">");
		locButton.addActionListener(getActionListenerEnBean("goNextPage(evento)"));
		locButton.setValueExpression("disabled", this._getValueExpression(this.armarExpressionEnBean("paginatedTable.goNextPageDeshabilitado")));
		locButton.setReRender("table1");
		locButton.setStyleClass("btnAjax");
		locButton.setOncomplete("changeStyleAlIngresar()");

		return locButton;
	}

	private HtmlAjaxCommandButton getBotonPrimerPagina() {
		HtmlAjaxCommandButton locButton = new HtmlAjaxCommandButton();
		locButton.setValue("<<");
		locButton.addActionListener(getActionListenerEnBean("goFirstPage(evento)"));
		locButton.setValueExpression("disabled", this._getValueExpression(this.armarExpressionEnBean("paginatedTable.goFirstPageDeshabilitado")));
		locButton.setReRender("table1, groupPanel1");
		locButton.setStyleClass("btnAjax");
		locButton.setOncomplete("changeStyleAlIngresar()");

		return locButton;
	}

	private HtmlAjaxCommandButton getBotonPaginaAnterior() {
		HtmlAjaxCommandButton locButton = new HtmlAjaxCommandButton();
		locButton.setValue("<");
		locButton.addActionListener(getActionListenerEnBean("goBackPage(evento)"));
		locButton.setValueExpression("disabled", this._getValueExpression(this.armarExpressionEnBean("paginatedTable.goBackPageDeshabilitado")));
		locButton.setReRender("table1");
		locButton.setStyleClass("btnAjax");
		locButton.setOncomplete("changeStyleAlIngresar()");

		return locButton;
	}

	private MethodExpressionActionListener getActionListenerEnBean(String pValor) {
		return this.getActionListener(this.armarExpressionEnBean(pValor));
	}

	private MethodExpressionActionListener getActionListener(String pValor) {
		Class[] args = new Class[] {javax.faces.event.ActionEvent.class};
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);

		return listener;
	}

	private ValueExpression _getValueExpression(String pValue) {
		ValueExpression valueExpresion = application.getExpressionFactory().createValueExpression(elContext, pValue, Object.class);

		return valueExpresion;
	}

	private ValueBinding _getValueBinding(String pPropiedad) {
		return application.createValueBinding(pPropiedad);
	}

	private String armarExpressionEnBean(String propiedad) {
		StringBuilder sb = new StringBuilder(nombreBean);
		sb.insert(nombreBean.length() - 1, "." + propiedad);

		return sb.toString();
	}

	private void checkBotonesNavegacion() {
		if(this.getCantidadPaginas() <= 1) {
			goNextPageDeshabilitado = true;
			goLastPageDeshabilitado = true;
			goBackPageDeshabilitado = true;
			goFirstPageDeshabilitado = true;
		} else if(filtro.getNumeroPagina() <= 1) {
			goBackPageDeshabilitado = true;
			goFirstPageDeshabilitado = true;
			goNextPageDeshabilitado = false;
			goLastPageDeshabilitado = false;
		} else if(filtro.getNumeroPagina() >= this.cantidadPaginas) {
			goBackPageDeshabilitado = false;
			goFirstPageDeshabilitado = false;
			goNextPageDeshabilitado = true;
			goLastPageDeshabilitado = true;
		} else {
			goNextPageDeshabilitado = false;
			goLastPageDeshabilitado = false;
			goBackPageDeshabilitado = false;
			goFirstPageDeshabilitado = false;
		}
	}

	private void procesarFiltro() {
		if(this.filtro != null) {
			if(filtro.getCantidadFilasTotales() != null && filtro.getCantidadPorPagina() != null) {
				float f = (float) filtro.getCantidadFilasTotales() / (float) filtro.getCantidadPorPagina();
				this.cantidadPaginas = (int) Math.ceil(f);
				this.checkBotonesNavegacion();
			}
		}
	}

	public void setearOrden(String columna) {
		Integer orden = (Integer) this.filtro.getMapaOrden().get(columna);
		if(orden == null || orden.equals(FiltroAbstracto.DESC)) {
			this.filtro.getMapaOrden().put(columna, FiltroAbstracto.ASC);
			this.mapaNombreColumnas.put(columna, mapaAtributosConsultables.get(columna).getNombreExterno() + " ↓");
		} else {
			this.filtro.getMapaOrden().put(columna, FiltroAbstracto.DESC);
			this.mapaNombreColumnas.put(columna, mapaAtributosConsultables.get(columna).getNombreExterno() + " ↑");
		}
		this.setMostrarLimpiarOrdenamiento(true);
	}

	public void irPagina(Integer pPagina) {
		if(pPagina < 1) {
			pPagina = 1;
		} else if(pPagina > this.cantidadPaginas) {
			pPagina = this.cantidadPaginas;
		}
		this.filtro.setNumeroPagina(pPagina);
		this.checkBotonesNavegacion();
	}

	public void sumarUnaPagina() {
		this.filtro.setNumeroPagina(this.filtro.getNumeroPagina() + 1);
		this.checkBotonesNavegacion();
	}

	public void restarUnaPagina() {
		this.filtro.setNumeroPagina(this.filtro.getNumeroPagina() - 1);
		this.checkBotonesNavegacion();
	}

	public void irPrimerPagina() {
		this.filtro.setNumeroPagina(1);
		this.checkBotonesNavegacion();
	}

	public void irUltimaPagina() {
		this.filtro.setNumeroPagina(this.cantidadPaginas);
		this.checkBotonesNavegacion();
	}

	public void irAPagina() {
		this.filtro.setNumeroPagina(Integer.parseInt(this.getTfNumeroPagina().toString()));
		this.checkBotonesNavegacion();
	}

	public void resetearEstado() {
		this.filtro.setNumeroPagina(1);
		this.checkBotonesNavegacion();
	}

	public void resetearOrdenamientos() {
		this.filtro.getMapaOrden().clear();
		this.resetearCabeceras();
		this.setMostrarLimpiarOrdenamiento(false);
	}

	private void resetearCabeceras() {
		for(String cadaLlave : mapaNombreColumnas.keySet()) {
			mapaNombreColumnas.put(cadaLlave, mapaAtributosConsultables.get(cadaLlave).getNombreExterno());
		}
	}

	public FiltroAbstracto getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroAbstracto filtro) {
		this.filtro = filtro;
		this.procesarFiltro();
	}

	public boolean isGoBackPageDeshabilitado() {
		return goBackPageDeshabilitado;
	}

	public void setGoBackPageDeshabilitado(boolean goBackPageDeshabilitado) {
		this.goBackPageDeshabilitado = goBackPageDeshabilitado;
	}

	public boolean isGoFirstPageDeshabilitado() {
		return goFirstPageDeshabilitado;
	}

	public void setGoFirstPageDeshabilitado(boolean goFirstPageDeshabilitado) {
		this.goFirstPageDeshabilitado = goFirstPageDeshabilitado;
	}

	public boolean isGoLastPageDeshabilitado() {
		return goLastPageDeshabilitado;
	}

	public void setGoLastPageDeshabilitado(boolean goLastPageDeshabilitado) {
		this.goLastPageDeshabilitado = goLastPageDeshabilitado;
	}

	public boolean isGoNextPageDeshabilitado() {
		return goNextPageDeshabilitado;
	}

	public void setGoNextPageDeshabilitado(boolean goNextPageDeshabilitado) {
		this.goNextPageDeshabilitado = goNextPageDeshabilitado;
	}

	public Integer getCantidadPaginas() {
		return cantidadPaginas;
	}

	public void setCantidadPaginas(Integer cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public Map<String, String> getMapaNombreColumnas() {
		return mapaNombreColumnas;
	}

	public void setMapaNombreColumnas(Map<String, String> mapaNombreColumnas) {
		this.mapaNombreColumnas = mapaNombreColumnas;
	}

	public boolean isMostrarLimpiarOrdenamiento() {
		return mostrarLimpiarOrdenamiento;
	}

	public void setMostrarLimpiarOrdenamiento(boolean mostrarLimpiarOrdenamiento) {
		this.mostrarLimpiarOrdenamiento = mostrarLimpiarOrdenamiento;
	}

	public TableRowGroup getTableRowGroup() {
		return this.tableRowGroup;
	}

	public void setTableRowGroup(TableRowGroup pTableRowGroup) {
	}

	public ImageHyperlink getBotonOrdenamiento() {
		return this.botonOrdenamiento;
	}

	public void setBotonOrdenamiento(ImageHyperlink boton) {
		this.botonOrdenamiento = boton;
	}

	public StaticText getStSeparadorOrdenamiento() {
		return stSeparadorOrdenamiento;
	}

	public void setStSeparadorOrdenamiento(StaticText stSeparadorOrdenamiento) {
		this.stSeparadorOrdenamiento = stSeparadorOrdenamiento;
	}

	public TableColumn getTcSeleccion() {
		return tcSeleccion;
	}

	public void setTcSeleccion(TableColumn tcSeleccion) {
		this.tcSeleccion = tcSeleccion;
	}

	public TableColumn getTcSeleccionMultiple() {
		return tcSeleccionMultiple;
	}

	public void setTcSeleccionMultiple(TableColumn tcSeleccionMultiple) {
		this.tcSeleccionMultiple = tcSeleccionMultiple;
	}

	public DropDown getDdReportes() {
		return ddReportes;
	}

	public void setDdReportes(DropDown ddReportes) {
		this.ddReportes = ddReportes;
	}

	public List<Reporte> getListaReportes() {
		return listaReportes;
	}

	public void setListaReportes(List<Reporte> listaReportes) {
		this.listaReportes = listaReportes;
	}

	public Object getObjetoSeleccionado() {
		return objetoSeleccionado;
	}

	public void setObjetoSeleccionado(Object objetoSeleccionado) {
		this.objetoSeleccionado = objetoSeleccionado;
	}

	private List seleccionadosSeleccionMultiple = new ArrayList();

	public List getSeleccionadosSeleccionMultiple() {
		return seleccionadosSeleccionMultiple;
	}

	public void setSeleccionadosSeleccionMultiple(List seleccionadosSeleccionMultiple) {
		this.seleccionadosSeleccionMultiple = seleccionadosSeleccionMultiple;
	}

	public void inicializarBotonOrdenamiento() {
		this.botonOrdenamiento = new ImageHyperlink();
		this.botonOrdenamiento.setImageURL("/theme/com/sun/rave/web/ui/mytheme/images/table/sort_clear.gif");
		this.botonOrdenamiento.setStyle("vertical-align: top;");
		this.botonOrdenamiento.setText("");
		this.botonOrdenamiento.setValueExpression("rendered", this._getValueExpression(this.armarExpressionEnBean("paginatedTable.mostrarLimpiarOrdenamiento")));
		this.botonOrdenamiento.addActionListener(this.getActionListener(this.armarExpressionEnBean("limpiarOrdenamiento(javax.faces.event.ActionEvent)")));
		this.botonOrdenamiento.setToolTip("Limpiar criterio de orden");

		stSeparadorOrdenamiento = new StaticText();
		stSeparadorOrdenamiento.setText("  |  ");
		stSeparadorOrdenamiento.setId("stSeparadorOrdenamiento");
		stSeparadorOrdenamiento.setEscape(false);
		this.stSeparadorOrdenamiento.setValueExpression("rendered", this._getValueExpression(this.armarExpressionEnBean("paginatedTable.mostrarLimpiarOrdenamiento")));
	}

	private PanelGroup getPanelGroupReportes() {
		PanelGroup locPanelGroup = new PanelGroup();
		locPanelGroup.setId("gpReportes");

		Label lbReportes = new Label();
		lbReportes.setId("lbReportes");
		lbReportes.setText("Reportes");

		HtmlAjaxSupport support = new HtmlAjaxSupport();
		support.setId("supportDdReportes");
		support.setEvent("onChange");
		support.setReRender("tableRowGroup1");
		try {
			Class[] parameterList = {Class.forName("javax.faces.event.ActionEvent")};
			support.setActionListener(application.createMethodBinding(this.armarExpressionEnBean("valueChangeReportes(evento)"), parameterList));
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		ddReportes.setId("ddReportes");
		ddReportes.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("ddReportes")));
		ddReportes.getChildren().add(support);

		HtmlAjaxCommandButton btnEjecutarReporte = new HtmlAjaxCommandButton();
		btnEjecutarReporte.setId("btnEjecutarReporte");
		btnEjecutarReporte.setValue("Ejecutar");
		btnEjecutarReporte.addActionListener(getActionListenerEnBean("executeDinamicReport_action(evento)"));
		btnEjecutarReporte.setStyleClass("btnAjax");
		btnEjecutarReporte.setOncomplete("changeStyleAlIngresar()");

		locPanelGroup.getChildren().add(lbReportes);
		locPanelGroup.getChildren().add(ddReportes);
		locPanelGroup.getChildren().add(btnEjecutarReporte);

		return locPanelGroup;
	}

	public void cargarDropDownReportes() {
		SingleSelectOptionsList ddReportesOptions = new SingleSelectOptionsList();

		Option[] opReportes = new Option[listaReportes.size() + 1];
		int i = 0;
		opReportes[i++] = new Option("", "");
		for(Reporte cadaReporte : listaReportes) {
			opReportes[i++] = new Option(cadaReporte.getIdReporte(), cadaReporte.getNombre());
		}
		ddReportesOptions.setOptions(opReportes);

		ddReportes.setItems(ddReportesOptions.getOptions());
	}

	public void ejecutarReporteDinamico() {
		System.out.println("funcion ejecutarReporteDinamico() en el paginatedTable");
	}

	public void onChangeDD() {
		System.out.println("funcion onChangeDD() en el paginatedTable");
	}

	private SystemParametro getSystemParametro() {
		try {
			return (SystemParametro) new InitialContext().lookup(SystemParametro.JNDI_NAME);
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

}