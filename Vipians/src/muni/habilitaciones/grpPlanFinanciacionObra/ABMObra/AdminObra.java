/*
 * AdminObra.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpPlanFinanciacionObra.ABMObra;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;
// comment for ana

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra.TipoObra;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminObra extends AdminPageBean {

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		// Tipo de Obra
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoObra.values(), "cap");
		ddTipoObraDefaultOptions.setOptions(op);
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		this.habilitarBtnExportar();
	}

	private Label label1 = new Label();
	private Label lblNumeroObra = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private HtmlAjaxCommandButton btnLimpiarCuadra = new HtmlAjaxCommandButton();
	private Button btnSeleccionarNumeroObra = new Button();
	private Button btnLimpiarNumeroObra = new Button();

	public HtmlAjaxCommandButton getBtnLimpiarCuadra() {
		return btnLimpiarCuadra;
	}

	public void setBtnLimpiarCuadra(HtmlAjaxCommandButton btnLimpiarCuadra) {
		this.btnLimpiarCuadra = btnLimpiarCuadra;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Button btnSeleccionarCuadra = new Button();

	public Button getBtnSeleccionarCuadra() {
		return btnSeleccionarCuadra;
	}

	public void setBtnSeleccionarCuadra(Button b) {
		this.btnSeleccionarCuadra = b;
	}

	private TextField tfCuadra = new TextField();
	private TextField tfNumeroObra = new TextField();

	public Button getBtnLimpiarNumeroObra() {
		return btnLimpiarNumeroObra;
	}

	public void setBtnLimpiarNumeroObra(Button btnLimpiarNumeroObra) {
		this.btnLimpiarNumeroObra = btnLimpiarNumeroObra;
	}

	public Button getBtnSeleccionarNumeroObra() {
		return btnSeleccionarNumeroObra;
	}

	public void setBtnSeleccionarNumeroObra(Button btnSeleccionarNumeroObra) {
		this.btnSeleccionarNumeroObra = btnSeleccionarNumeroObra;
	}

	public Label getLblNumeroObra() {
		return lblNumeroObra;
	}

	public void setLblNumeroObra(Label lblNumeroObra) {
		this.lblNumeroObra = lblNumeroObra;
	}

	public TextField getTfNumeroObra() {
		return tfNumeroObra;
	}

	public void setTfNumeroObra(TextField tfNumeroObra) {
		this.tfNumeroObra = tfNumeroObra;
	}

	public TextField getTfCuadra() {
		return tfCuadra;
	}

	public void setTfCuadra(TextField tf) {
		this.tfCuadra = tf;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private ObjectListDataProvider ldpObra = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpObra() {
		return ldpObra;
	}

	public void setLdpObra(ObjectListDataProvider oldp) {
		this.ldpObra = oldp;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private DropDown ddTipoObra = new DropDown();

	public DropDown getDdTipoObra() {
		return ddTipoObra;
	}

	public void setDdTipoObra(DropDown dd) {
		this.ddTipoObra = dd;
	}

	private SingleSelectOptionsList ddTipoObraDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoObraDefaultOptions() {
		return ddTipoObraDefaultOptions;
	}

	public void setDdTipoObraDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoObraDefaultOptions = ssol;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private TableColumn tableColumn7 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tc) {
		this.tableColumn7 = tc;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText st) {
		this.staticText10 = st;
	}

	private Button btnImprimirReporte = new Button();

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	public AdminObra() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroObra locFiltro = this.getFiltro();

		locFiltro.setTipoObra(getDDEnumValue(this.getDdTipoObra(), TipoObra.class));
		locFiltro.setDescripcion(getTextAreaValue(this.getTaDescripcion()));
		locFiltro.setNumero(getTextFieldValue(this.getTfNumeroObra()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroObra locFiltro = this.getFiltro();

		if(locFiltro.getDescripcion() != null) {
			this.getTaDescripcion().setText(locFiltro.getDescripcion());
		}

		if(locFiltro.getCuadra() != null && locFiltro.getCuadra().getIdCuadra() != -1) {
			this.getTfCuadra().setText(locFiltro.getCuadra().toString());
		}
		if(locFiltro.getObra() != null && locFiltro.getObra().getNumeroObra() != null) {
			this.getTfNumeroObra().setText(locFiltro.getNumero());
		}

		ddTipoObra.setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoObra())));
		ddTipoObraDefaultOptions.setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoObra())));
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpObra();
	}

	@Override
	public ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaObras();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaObras((ArrayList) lista);
	}

	// </editor-fold>
	public String btnSeleccionarCuadra_action() {
		return navegarParaSeleccionar("AdminCuadra");
	}

	public String btnAgregar_action() {
		return toAbm(new ObraModel().new agregarObraController());
	}

	public String btnModificar_action() {
		return toAbm(new ObraModel().new modificarObraController());
	}

	public String btnEliminar_action() {
		return toAbm(new ObraModel().new eliminarObraController());
	}

	public String btnConsultar_action() {
		return toAbm(new ObraModel().new consultarObraController());
	}

	public String btnLimpiarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(this.getTfCuadra());
			FiltroObra locFiltro = this.getFiltro();
			locFiltro.setCuadra(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Obras";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminObra";
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroObra locFiltro = this.getFiltro();
		locFiltro.setCuadra(null);
		locFiltro.setDescripcion(null);
		locFiltro.setNumero(null);
		locFiltro.setTipoObra(null);

		this.getTfCuadra().setText("");
		this.getTfNumeroObra().setText("");
		this.getTaDescripcion().setText("");
		this.getDdTipoObra().setSelected(null);
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Obra locObra = (Obra) pObject;
		return this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().getObraPorId(locObra.getIdObra());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		FiltroObra fitroObra = (FiltroObra) pFiltro;
		return this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().findListaObras(fitroObra);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaObra();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroObra locFiltro = this.getFiltro();

		if(pObject instanceof Cuadra) {
			Cuadra locCuadra = (Cuadra) pObject;
			locFiltro.setCuadra(locCuadra);
		}
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra}";
	}

	@Override
	public long getSerialVersionUID() {
		return Obra.serialVersionUID;
	}
}