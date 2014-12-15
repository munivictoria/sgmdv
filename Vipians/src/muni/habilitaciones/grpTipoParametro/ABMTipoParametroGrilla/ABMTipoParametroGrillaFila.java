
package muni.habilitaciones.grpTipoParametro.ABMTipoParametroGrilla;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaFila;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaFilaColumna;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMTipoParametroGrillaFila extends ABMPageBean {

	private Label lbNumero = new Label();
	private Label lbCondicion = new Label();
	private Label lblListaValores = new Label();
	private TextField tfNumero = new TextField();
	private TextField tfNumeroValor = new TextField();
	private TextArea taValor = new TextArea();
	private TextArea taCondicion = new TextArea();
	private TextArea taCondicionValor = new TextArea();
	private Table tableListaValores = new Table();
	private TableRowGroup tableRowGroupListaValores = new TableRowGroup();
	private ObjectListDataProvider ldpValores = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private Object seleccion = null;
	private HtmlAjaxCommandButton btnAgregarValor = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarValor = new HtmlAjaxCommandButton();

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationValores() != null) {
			this.getLdpValores().setList(getListaDelCommunicationValores());
		}
	}

	public Label getLbNumero() {
		return lbNumero;
	}

	public void setLbNumero(Label lbNumero) {
		this.lbNumero = lbNumero;
	}

	public Label getLbCondicion() {
		return lbCondicion;
	}

	public void setLbCondicion(Label lbCondicion) {
		this.lbCondicion = lbCondicion;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public TextField getTfNumeroValor() {
		return tfNumeroValor;
	}

	public void setTfNumeroValor(TextField tfNumeroValor) {
		this.tfNumeroValor = tfNumeroValor;
	}

	public TextArea getTaValor() {
		return taValor;
	}

	public void setTaValor(TextArea taValor) {
		this.taValor = taValor;
	}

	public TextArea getTaCondicion() {
		return taCondicion;
	}

	public void setTaCondicion(TextArea taCondicion) {
		this.taCondicion = taCondicion;
	}

	public TextArea getTaDescripcionValor() {
		return taCondicionValor;
	}

	public void setTaDescripcionValor(TextArea taCondicionValor) {
		this.taCondicionValor = taCondicionValor;
	}

	public Table getTableListaValores() {
		return tableListaValores;
	}

	public void setTableListaValores(Table tableListaValores) {
		this.tableListaValores = tableListaValores;
	}

	public TableRowGroup getTableRowGroupListaValores() {
		return tableRowGroupListaValores;
	}

	public void setTableRowGroupListaValores(TableRowGroup tableRowGroupListaValores) {
		this.tableRowGroupListaValores = tableRowGroupListaValores;
	}

	public ObjectListDataProvider getLdpValores() {
		return ldpValores;
	}

	public void setLdpValores(ObjectListDataProvider ldpValores) {
		this.ldpValores = ldpValores;
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

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public Object getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Object seleccion) {
		this.seleccion = seleccion;
	}

	public String getVar() {
		return tableRowGroupListaValores.getRowKey().getRowId();
	}

	public Label getLblListaValores() {
		return lblListaValores;
	}

	public void setLblListaValores(Label lblListaValores) {
		this.lblListaValores = lblListaValores;
	}

	public HtmlAjaxCommandButton getBtnAgregarValor() {
		return btnAgregarValor;
	}

	public void setBtnAgregarValor(HtmlAjaxCommandButton btnAgregarValor) {
		this.btnAgregarValor = btnAgregarValor;
	}

	public HtmlAjaxCommandButton getBtnQuitarValor() {
		return btnQuitarValor;
	}

	public void setBtnQuitarValor(HtmlAjaxCommandButton btnQuitarValor) {
		this.btnQuitarValor = btnQuitarValor;
	}

	public TextArea getTaCondicionValor() {
		return taCondicionValor;
	}

	public void setTaCondicionValor(TextArea taCondicionValor) {
		this.taCondicionValor = taCondicionValor;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		TipoParametroGrillaFila locTipoParametroGrillaFila = this.obtenerObjetoDelElementoPila(0, TipoParametroGrillaFila.class);

		locTipoParametroGrillaFila.setNroFila(this.getTextFieldValueInteger(this.getTfNumero()));
		locTipoParametroGrillaFila.setCondicion(this.getTextAreaValue(this.getTaCondicion()));

		this.getLdpValores().commitChanges();
		this.setListaDelCommunicationValores(this.getLdpValores().getList());
		locTipoParametroGrillaFila.setColumnas(this.getListaDelCommunicationValores());

		this.getElementoPila().getObjetos().add(0, locTipoParametroGrillaFila);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		TipoParametroGrillaFila locTipoParametroGrillaFila = this.obtenerObjetoDelElementoPila(0, TipoParametroGrillaFila.class);

		if(locTipoParametroGrillaFila != null)
			this.getTfNumero().setText(locTipoParametroGrillaFila.getNroFila());
		if(locTipoParametroGrillaFila != null)
			this.getTaCondicion().setText(locTipoParametroGrillaFila.getCondicion());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoParametroGrillaFila locTipoParametroGrillaFila = (TipoParametroGrillaFila) pObject;

		this.setListaDelCommunicationValores(locTipoParametroGrillaFila.getColumnas());
		this.getLdpValores().setList(getListaDelCommunicationValores());

		this.getElementoPila().getObjetos().add(0, locTipoParametroGrillaFila);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoParametroGrillaFila";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		ep.getObjetos().add(0, new TipoParametroGrillaFila());

		this.setListaDelCommunicationValores(new ArrayList());
		this.getLdpValores().setList(getListaDelCommunicationValores());

		return ep;
	}

	public List getListaDelCommunicationValores() {
		return this.getCommunicationHabilitacionesBean().getListaVariablesTipoParametroGrillaFilaValores();
	}

	public void setListaDelCommunicationValores(List pLista) {
		this.getCommunicationHabilitacionesBean().setListaVariablesTipoParametroGrillaFilaValores(pLista);
	}

	public void btnAgregarValor_action() {
		this.getLdpValores().commitChanges();
		TipoParametroGrillaFilaColumna locColumna = new TipoParametroGrillaFilaColumna();

		this.getListaDelCommunicationValores().add(locColumna);
		this.getLdpValores().setList(getListaDelCommunicationValores());

		this.guardarEstadoObjetosUsados();
	}

	public String btnQuitarValor_action() {
		String retorno = null;
		this.getLdpValores().commitChanges();
		Object objeto = getObjetoSeleccionadoDeTabla("buttonGroup1", this.getLdpValores());
		if(objeto != null) {
			this.getListaDelCommunicationValores().remove(objeto);
			this.getLdpValores().getList().remove(objeto);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila}";
	}

	@Override
	public long getSerialVersionUID() {
		return TipoParametroGrillaFila.serialVersionUID;
	}
}