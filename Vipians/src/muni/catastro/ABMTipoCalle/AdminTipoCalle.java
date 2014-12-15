/*
 * AdminTipoCalle.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */
package muni.catastro.ABMTipoCalle;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroTipoCalle;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminTipoCalle extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		this.habilitarBtnExportar();
	}

	private ObjectListDataProvider ldpTipoCalles = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpTipoCalles() {
		return ldpTipoCalles;
	}

	public void setLdpTipoCalles(ObjectListDataProvider oldp) {
		this.ldpTipoCalles = oldp;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
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

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public AdminTipoCalle() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroTipoCalle locFiltro = getFiltro();
		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroTipoCalle locFiltro = getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().findListaTipoCalles((FiltroTipoCalle) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroTipoCalle locFiltro = getFiltro();
		locFiltro.setNombre(null);

		this.getTfNombre().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpTipoCalles();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaTipoCalles();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaTipoCalles((ArrayList) lista);
	}

	// public void setPropiedadesTabla() {
	// String width = null;
	// int cantidadFilas =
	// this.getApplicationBean1().getCantidadFilasTablasAdmin().intValue();
	//
	// this.getTableRowGroup1().setRows(cantidadFilas);
	//
	// this.getTable1().setWidth(width);
	// this.getTable1().setClearSortButton(true);
	// this.getTable1().setDeselectMultipleButton(false);
	// this.getTable1().setDeselectSingleButton(false);
	// this.getTable1().setPaginateButton(false);
	// this.getTable1().setPaginationControls(true);
	// this.getTable1().setSelectMultipleButton(false);
	// this.getTable1().setSortPanelToggleButton(false);
	// this.getTable1().setTitle(null);
	// }

	public String btnAgregar_action() {
		return toAbm(new TipoCalleModel().new AgregarTipoCalleController());
	}

	public String btnModificar_action() {
		return toAbm(new TipoCalleModel().new ModificarTipoCalleController());
	}

	public String btnEliminar_action() {
		return toAbm(new TipoCalleModel().new EliminarTipoCalleController());
	}

	public String btnConsultar_action() {
		return toAbm(new TipoCalleModel().new ConsultarTipoCalleController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		TipoCalle locTipoCalle = (TipoCalle) pObject;
		getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
		locTipoCalle = getComunicationCatastroBean().getRemoteSystemInformacionGeografica().getTipoCallePorId(locTipoCalle.getIdTipoCalle());
		return locTipoCalle;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Tipos de Calle";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminTipoCalle";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaTipoCalle();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return TipoCalle.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMTipoCalle$AdminTipoCalle}";
	}
}