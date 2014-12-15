
package muni.habilitaciones.grpTipoParametro.ABMTipoParametroGrilla;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaFila;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrillaVariable;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMTipoParametroGrilla extends ABMPageBean {

	private Label lblListaVariables = new Label();
	private Label lblNombre = new Label();
	private Label lblListaFilas = new Label();
	private TextField tfNombre = new TextField();
	private TextField tfNombreVariable = new TextField();
	private TextArea taCondicion = new TextArea();
	private TextArea taDescripcion = new TextArea();
	private TextArea taValor = new TextArea();
	private HtmlAjaxCommandButton btnAgregarVariable = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarVariable = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnAgregarFila = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarFila = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnModificarFila = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnConsultarFila = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnClonarFila = new HtmlAjaxCommandButton();
	private Table tableListaVariables = new Table();
	private Table tableListaFila = new Table();
	private ObjectListDataProvider ldpVariables = new ObjectListDataProvider();
	private ObjectListDataProvider ldpFilas = new ObjectListDataProvider();
	private TableRowGroup tableRowGroupListaVariables = new TableRowGroup();
	private TableRowGroup tableRowGroupListaFila = new TableRowGroup();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private RadioButton radioButton2 = new RadioButton();
	private Object seleccion = null;
	private Object seleccionFila = null;
	private PanelGroup groupPanel1 = new PanelGroup();
	private StaticText stSeparadorLocal = new StaticText();

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationVariables() != null) {
			this.getLdpVariables().setList(getListaDelCommunicationVariables());
		}

		if(this.getListaDelCommunicationFilas() != null) {
			this.getLdpFilas().setList(getListaDelCommunicationFilas());
		}
	}

	public Label getLblListaVariables() {
		return lblListaVariables;
	}

	public void setLblListaVariables(Label lblListaVariables) {
		this.lblListaVariables = lblListaVariables;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public Label getLblListaFilas() {
		return lblListaFilas;
	}

	public void setLblListaFilas(Label lblListaFilas) {
		this.lblListaFilas = lblListaFilas;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public HtmlAjaxCommandButton getBtnAgregarVariable() {
		return btnAgregarVariable;
	}

	public void setBtnAgregarVariable(HtmlAjaxCommandButton btnAgregarVariable) {
		this.btnAgregarVariable = btnAgregarVariable;
	}

	public HtmlAjaxCommandButton getBtnQuitarVariable() {
		return btnQuitarVariable;
	}

	public void setBtnQuitarVariable(HtmlAjaxCommandButton btnQuitarVariable) {
		this.btnQuitarVariable = btnQuitarVariable;
	}

	public Table getTableListaVariables() {
		return tableListaVariables;
	}

	public void setTableListaVariables(Table tableListaVariables) {
		this.tableListaVariables = tableListaVariables;
	}

	public Table getTableListaFila() {
		return tableListaFila;
	}

	public void setTableListaFila(Table tableListaFila) {
		this.tableListaFila = tableListaFila;
	}

	public ObjectListDataProvider getLdpVariables() {
		return ldpVariables;
	}

	public void setLdpVariables(ObjectListDataProvider ldpVariables) {
		this.ldpVariables = ldpVariables;
	}

	public ObjectListDataProvider getLdpFilas() {
		return ldpFilas;
	}

	public void setLdpFilas(ObjectListDataProvider ldpFilas) {
		this.ldpFilas = ldpFilas;
	}

	private ObjectListDataProvider getObjectListDataProviderVariable() {
		return this.getLdpVariables();
	}

	public TableRowGroup getTableRowGroupListaVariables() {
		return tableRowGroupListaVariables;
	}

	public void setTableRowGroupListaVariables(TableRowGroup tableRowGroupListaVariables) {
		this.tableRowGroupListaVariables = tableRowGroupListaVariables;
	}

	public TableRowGroup getTableRowGroupListaFila() {
		return tableRowGroupListaFila;
	}

	public void setTableRowGroupListaFila(TableRowGroup tableRowGroupListaFila) {
		this.tableRowGroupListaFila = tableRowGroupListaFila;
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

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tableColumn6) {
		this.tableColumn6 = tableColumn6;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public Object getSeleccion() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(seleccion) ? sv : null;
	}

	public void setSeleccion(Object selec) {
		if(selec != null) {
			seleccion = selec;
		}
	}

	public Object getSeleccionFila() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(seleccionFila) ? sv : null;
	}

	public void setSeleccionFila(Object seleccionFila) {
		if(seleccionFila != null) {
			this.seleccionFila = seleccionFila;
		}
	}

	public String getVar() {
		return tableRowGroupListaVariables.getRowKey().getRowId();
	}

	public String getVarFilas() {
		return tableRowGroupListaFila.getRowKey().getRowId();
	}

	public TextField getTfNombreVariable() {
		return tfNombreVariable;
	}

	public void setTfNombreVariable(TextField tfNombreVariable) {
		this.tfNombreVariable = tfNombreVariable;
	}

	public TextArea getTaCondicion() {
		return taCondicion;
	}

	public void setTaCondicion(TextArea taCondicion) {
		this.taCondicion = taCondicion;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	public TextArea getTaValor() {
		return taValor;
	}

	public void setTaValor(TextArea taValor) {
		this.taValor = taValor;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public StaticText getStSeparadorLocal() {
		return stSeparadorLocal;
	}

	public void setStSeparadorLocal(StaticText stSeparadorLocal) {
		this.stSeparadorLocal = stSeparadorLocal;
	}

	public HtmlAjaxCommandButton getBtnQuitarFila() {
		return btnQuitarFila;
	}

	public void setBtnQuitarFila(HtmlAjaxCommandButton btnQuitarFila) {
		this.btnQuitarFila = btnQuitarFila;
	}

	public HtmlAjaxCommandButton getBtnAgregarFila() {
		return btnAgregarFila;
	}

	public void setBtnAgregarFila(HtmlAjaxCommandButton btnAgregarFila) {
		this.btnAgregarFila = btnAgregarFila;
	}

	public HtmlAjaxCommandButton getBtnModificarFila() {
		return btnModificarFila;
	}

	public void setBtnModificarFila(HtmlAjaxCommandButton btnModificarFila) {
		this.btnModificarFila = btnModificarFila;
	}

	public HtmlAjaxCommandButton getBtnConsultarFila() {
		return btnConsultarFila;
	}

	public void setBtnConsultarFila(HtmlAjaxCommandButton btnConsultarFila) {
		this.btnConsultarFila = btnConsultarFila;
	}

	public HtmlAjaxCommandButton getBtnClonarFila() {
		return btnClonarFila;
	}

	public void setBtnClonarFila(HtmlAjaxCommandButton btnClonarFila) {
		this.btnClonarFila = btnClonarFila;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		TipoParametroGrilla tipoParametroGrilla = this.obtenerObjetoDelElementoPila(0, TipoParametroGrilla.class);

		tipoParametroGrilla.setNombreVariable(this.getTextFieldValue(this.getTfNombre()));

		this.getLdpVariables().commitChanges();
		this.setListaDelCommunicationVariables(this.getLdpVariables().getList());
		tipoParametroGrilla.setListaVariables(getListaDelCommunicationVariables());

		tipoParametroGrilla.setFilas(getListaDelCommunicationFilas());

		this.getElementoPila().getObjetos().add(0, tipoParametroGrilla);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		TipoParametroGrilla tipoParametroGrilla = this.obtenerObjetoDelElementoPila(0, TipoParametroGrilla.class);
		this.getTfNombre().setText(tipoParametroGrilla.getNombreVariable());

		Object respuestaABM = this.getRequestBean1().getRespuestaABM();
		if(respuestaABM != null) {
			if(respuestaABM instanceof TipoParametroGrillaFila) {
				TipoParametroGrillaFila locTipoParametroGrillaFila = (TipoParametroGrillaFila) respuestaABM;
				TipoParametroGrilla locTipoParametroGrilla = this.obtenerObjetoDelElementoPila(0, TipoParametroGrilla.class);

				if(!locTipoParametroGrilla.getFilas().contains(locTipoParametroGrillaFila)) {
					locTipoParametroGrilla.getFilas().add(locTipoParametroGrillaFila);
				}
				this.setListaDelCommunicationFilas(locTipoParametroGrilla.getFilas());
				this.getLdpFilas().setList(this.getListaDelCommunicationFilas());
				this.getElementoPila().getObjetos().set(0, locTipoParametroGrilla);
			}
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoParametroGrilla tipoParametroGrilla = (TipoParametroGrilla) pObject;

		this.setListaDelCommunicationVariables(tipoParametroGrilla.getListaVariables());
		this.getLdpVariables().setList(getListaDelCommunicationVariables());
		this.setListaDelCommunicationFilas(tipoParametroGrilla.getFilas());
		this.getLdpFilas().setList(getListaDelCommunicationFilas());

		this.getElementoPila().getObjetos().add(0, tipoParametroGrilla);
	}

	@Override
	protected String getCasoNavegacion() {
		// TODO Auto-generated method stub
		return "ABMTipoParametroGrilla";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		// TODO Auto-generated method stub
		ep.getObjetos().add(0, new TipoParametroGrilla());

		this.setListaDelCommunicationVariables(new ArrayList());
		this.getLdpVariables().setList(getListaDelCommunicationVariables());

		this.setListaDelCommunicationFilas(new ArrayList());
		this.getLdpFilas().setList(getListaDelCommunicationFilas());

		return ep;
	}

	public void btnAgregarVariable_action() {
		this.getObjectListDataProviderVariable().commitChanges();
		TipoParametroGrillaVariable locVariable = new TipoParametroGrillaVariable();
		System.out.println(this.getListaDelCommunicationVariables());

		this.getListaDelCommunicationVariables().add(locVariable);
		this.getObjectListDataProviderVariable().setList(getListaDelCommunicationVariables());

		this.guardarEstadoObjetosUsados();
	}

	public List getListaDelCommunicationVariables() {
		return this.getCommunicationHabilitacionesBean().getListaVariablesTipoParametroGrilla();
	}

	public void setListaDelCommunicationVariables(List pLista) {
		this.getCommunicationHabilitacionesBean().setListaVariablesTipoParametroGrilla(pLista);
	}

	public List getListaDelCommunicationFilas() {
		return this.getCommunicationHabilitacionesBean().getListaVariablesTipoParametroGrillaFila();
	}

	public void setListaDelCommunicationFilas(List pLista) {
		this.getCommunicationHabilitacionesBean().setListaVariablesTipoParametroGrillaFila(pLista);
	}

	public String btnQuitarVariable_action() {
		String retorno = null;
		this.getObjectListDataProviderVariable().commitChanges();
		Object objeto = getObjetoSeleccionadoDeTabla("buttonGroup1", this.getObjectListDataProviderVariable());
		if(objeto != null) {
			this.getListaDelCommunicationVariables().remove(objeto);
			this.getObjectListDataProviderVariable().getList().remove(objeto);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarFila_action() {
		return toAbm(new TipoParametroGrillaFilaModel().new AgregarController(), "ABMTipoParametroGrillaFila");
	}

	public String btnModificarFila_action() {
		return toAbmConSeleccion(new TipoParametroGrillaFilaModel().new ModificarController(), "ABMTipoParametroGrillaFila", "buttonGroup2", this.getLdpFilas());
	}

	public String btnQuitarFila_action() {// Quita de la tabla directamente
		String retorno = null;
		Object objeto = getObjetoSeleccionadoDeTabla("buttonGroup2", this.getLdpFilas());
		if(objeto != null) {
			this.getListaDelCommunicationFilas().remove(objeto);
			this.getLdpFilas().getList().remove(objeto);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnConsultarFila_action() {
		return toAbmConSeleccion(new TipoParametroGrillaFilaModel().new ConsultarController(), "ABMTipoParametroGrillaFila", "buttonGroup2", this.getLdpFilas());
	}

	public String btnClonarFila_action() {
		String retorno = null;
		TipoParametroGrillaFila locFila = getObjetoSeleccionadoDeTabla("buttonGroup2", this.getLdpFilas());
		if(locFila != null) {
			try {
				TipoParametroGrillaFila clon = locFila.clone();
				this.getListaDelCommunicationFilas().add(clon);
				// this.getLdpFilas().getList().add(clon);
				this.guardarEstadoObjetosUsados();
			} catch(CloneNotSupportedException e) {
				e.printStackTrace();
				error(e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		System.out.println(getListaDelCommunicationFilas().size());
		System.out.println(getLdpFilas().getList().size());
		return retorno;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla}";
	}

	@Override
	public long getSerialVersionUID() {
		return TipoParametroGrilla.serialVersionUID;
	}
}