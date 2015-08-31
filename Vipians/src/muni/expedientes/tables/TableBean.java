/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.el.ValueBinding;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;

public abstract class TableBean {

	public Label titulo = new Label();
	public Table table = new Table();
	public TableRowGroup tableRowGroup1 = new TableRowGroup();
	public RadioButton radioButton1 = new RadioButton();
	public TableColumn tableColumn1 = new TableColumn();
	public TableColumn tableColumn2 = new TableColumn();

	public StaticText staticText1 = new StaticText();
	public StaticText staticText4 = new StaticText();

	public PanelGroup groupPanel1 = new PanelGroup();
	public PanelGroup groupPanel2 = new PanelGroup();
	public Object lastSelected = null;
	public Button btnAgregar = new Button();
	public HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
	public HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();

	public ObjectListDataProvider objectListDataProvider = new ObjectListDataProvider();

	private Label tituloProcesarDocumentos = new Label();
	private Table tableProcesarDocumentos = new Table();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private ObjectListDataProvider objectListDataProvider2 = new ObjectListDataProvider();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private RadioButton radioButton2 = new RadioButton();
	private Object lastSelected2 = null;
	public StaticText staticText2 = new StaticText();
	public StaticText staticText3 = new StaticText();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText staticText3) {
		this.staticText3 = staticText3;
	}

	public Label getTituloProcesarDocumentos() {
		return tituloProcesarDocumentos;
	}

	public void setTituloProcesarDocumentos(Label tituloProcesarDocumentos) {
		this.tituloProcesarDocumentos = tituloProcesarDocumentos;
	}

	public Table getTableProcesarDocumentos() {
		return tableProcesarDocumentos;
	}

	public void setTableProcesarDocumentos(Table tableProcesarDocumentos) {
		this.tableProcesarDocumentos = tableProcesarDocumentos;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public ObjectListDataProvider getObjectListDataProvider2() {
		return objectListDataProvider2;
	}

	public void setObjectListDataProvider2(ObjectListDataProvider objectListDataProvider2) {
		this.objectListDataProvider2 = objectListDataProvider2;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public String getNombreBean() {
		return nombreBean;
	}

	public void setNombreBean(String nombreBean) {
		this.nombreBean = nombreBean;
	}

	public Label getTitulo() {
		return titulo;
	}

	public void setTitulo(Label titulo) {
		this.titulo = titulo;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public TableRowGroup getTableRowGroup1() {
		tableRowGroup1.setOnMouseOver("jsRowMouseOver(this)");
		tableRowGroup1.setOnMouseOut("jsRowMouseOut(this)");
		tableRowGroup1.setOnClick("jsRowMouseClick(this)");
		tableRowGroup1.setOnDblClick("funcionSeleccionar()");
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
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

	public ObjectListDataProvider getObjectListDataProvider() {
		return objectListDataProvider;
	}

	public void setObjectListDataProvider(ObjectListDataProvider objectListDataProvider) {
		this.objectListDataProvider = objectListDataProvider;
	}

	public void warn(String pMensaje) {
		FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage(FacesMessage.SEVERITY_WARN, pMensaje, pMensaje));
	}

	public Object getSessionBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(pBeanName);
	}

	public Object getRequestBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(pBeanName);
	}

	public void _init() {
		if(this.getListaDelCommunication() != null) {
			this.objectListDataProvider.setList(getListaDelCommunication());
		}
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected(getNombreButtonGroup());
			rk = this.objectListDataProvider.getRowKey(aRowId);
		} catch(Exception ex) {
			ex.getMessage();
		}

		return rk;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected(getNombreButtonGroup2());
			rk = this.objectListDataProvider2.getRowKey(aRowId);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return rk;
	}

	public String getNombreButtonGroup() {
		String nombre = getClass().getSimpleName() + "bg";
		return nombre;
	}

	public String getNombreButtonGroup2() {
		String nombre = getClass().getSimpleName() + "bg2";
		return nombre;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey() != null ? tableRowGroup1.getRowKey().getRowId() : null;
	}

	public void setCurrentRow(int row) {
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey() != null ? tableRowGroup2.getRowKey().getRowId() : null;
	}

	public void setCurrentRow2(int row) {
	}

	public Object getRBSelected() {
		String sv = radioButton1.getSelectedValue().toString();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public Object getRBSelected2() {
		String sv = radioButton2.getSelectedValue().toString();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	@SuppressWarnings("rawtypes")
	public abstract List getListaDelCommunication();

	@SuppressWarnings("rawtypes")
	public abstract void setListaDelCommunication(List lista);

	public void quitarElemento() {
		RowKey rk = null;
		try {
			rk = this.getSeleccionado();
			if(rk != null) {
				int index = getNroFila(rk.toString());
				this.getListaDelCommunication().remove(index);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void quitarTodosLosElementos() {
		try {
			this.getListaDelCommunication().clear();
		} catch(Exception ex) {
		}
	}

	protected int getNroFila(String pCadena) {
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);

		return new Integer(lCadenaAuxiliar).intValue();
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		List lista = new ArrayList();
		this.objectListDataProvider.commitChanges();
		lista = this.objectListDataProvider.getList();
		this.setListaDelCommunication(lista);

		return lista;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.objectListDataProvider.setList(list);
		this.setListaDelCommunication(list);
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public Object getLastSelected2() {
		return lastSelected2;
	}

	public void setLastSelected2(Object lastSelected2) {
		this.lastSelected2 = lastSelected2;
	}

	@SuppressWarnings("rawtypes")
	public abstract void addToList(List pList, Object pObject);

	private final FacesContext context = FacesContext.getCurrentInstance();
	private final Application application = context.getApplication();
	private String nombreBean;
	private DateTimeConverter dateTimeConverter = new DateTimeConverter();

	private ValueBinding _getValueBinding(String pPropiedad) {
		return application.createValueBinding(pPropiedad);
	}

	public RadioButton getRbSeleccion() {
		RadioButton rb = new RadioButton();
		rb.setLabel("");
		rb.setName(getNombreButtonGroup());
		rb.setValueBinding("selected", this._getValueBinding(this.armarExpressionEnBean("RBSelected")));
		rb.setValueBinding("selectedValue", this._getValueBinding(this.armarExpressionEnBean("currentRow")));
		rb.setValueBinding("binding", this._getValueBinding(this.armarExpressionEnBean("radioButton1")));
		rb.setOnClick("checkUncheck(this)");

		return rb;
	}

	private String armarExpressionEnBean(String propiedad) {
		StringBuilder sb = new StringBuilder(nombreBean);
		sb.insert(nombreBean.length() - 1, "." + propiedad);
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private TableColumn getTableColumnPropiedad(String text, UIInput component) {
		TableColumn tc = new TableColumn();
		tc.setWidth("100");
		tc.setId("tc" + text);
		tc.setHeaderText(text);
		tc.getChildren().add(component);
		tc.setSort(component.getValue());

		return tc;
	}

	@SuppressWarnings("unused")
	private Checkbox getCheckBoxPropiedad(String pPropiedad) {
		Checkbox cb = new Checkbox();
		cb.setId("ta" + pPropiedad.replace(".", "").replace("'", ""));
		cb.setDisabled(true);
		// cb.setValueBinding("selected", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return cb;
	}

	@SuppressWarnings("unused")
	private TextArea getTextAreaPropiedad(String pPropiedad) {
		TextArea ta = new TextArea();
		ta.setId("ta" + pPropiedad.replace(".", "").replace("'", ""));
		ta.setDisabled(true);
		ta.setStyleClass("textFieldDisabled");
		ta.setRows(2);
		// ta.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return ta;
	}

	@SuppressWarnings("unused")
	private StaticText getStaticTextPropiedad(String pPropiedad) {
		StaticText st = new StaticText();
		st.setId("st" + pPropiedad.replace(".", "").replace("'", ""));
		// st.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return st;
	}

	@SuppressWarnings("unused")
	private StaticText getStaticTextPropiedadFecha(String pPropiedad) {
		StaticText st = new StaticText();
		st.setId("st" + pPropiedad.replace(".", "").replace("'", ""));
		DateTimeConverter locConverter = new DateTimeConverter();
		locConverter.setPattern("dd/MM/yyyy");
		locConverter.setTimeZone(TimeZone.getDefault());
		st.setConverter(locConverter);
		// st.setValueBinding("text", this._getValueBinding(getCurrentRowValue(pPropiedad)));

		return st;
	}

	public DateTimeConverter getDateTimeConverter() {
		dateTimeConverter.setPattern("dd/MM/yyyy");
		dateTimeConverter.setTimeZone(TimeZone.getDefault());

		return dateTimeConverter;
	}

	public void setDateTimeConverter(DateTimeConverter dateTimeConverter) {
		this.dateTimeConverter = dateTimeConverter;
	}

}