/*
 * AdminTipoConstruccion.java
 *
 * Created on 19 de octubre de 2006, 8:04
 * Copyright Trascender
 */
package muni.catastro.ABMTipoConstruccion;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroTipoConstruccion;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
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
public class AdminTipoConstruccion extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		// CAMBIAR: Utilizar el EJBCliet adecuado.
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		this.habilitarBtnExportar();
	}

	private ObjectListDataProvider ldpTipoConstrucciones = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpTipoConstrucciones() {
		return ldpTipoConstrucciones;
	}

	public void setLdpTipoConstrucciones(ObjectListDataProvider oldp) {
		this.ldpTipoConstrucciones = oldp;
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

	private TipoConstruccion tipoConstruccionABuscar = null;

	public TipoConstruccion getTipoConstruccionABuscar() {
		if (this.tipoConstruccionABuscar == null) {
			this.tipoConstruccionABuscar = new TipoConstruccion();
		}
		return this.tipoConstruccionABuscar;
	}

	public void setTipoConstruccionABuscar(TipoConstruccion pTipoConstruccion) {
		this.tipoConstruccionABuscar = pTipoConstruccion;
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

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroTipoConstruccion locFiltro = getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroTipoConstruccion locFiltro = getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().findTiposConstruccion((FiltroTipoConstruccion) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroTipoConstruccion locFiltro = getFiltro();
		locFiltro.setNombre(null);

		this.getTfNombre().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpTipoConstrucciones();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaTipoConstruccion();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaTipoConstruccion((ArrayList) lista);
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
	public AdminTipoConstruccion() {
	}

	public String btnAgregar_action() {
		return toAbm(new TipoConstruccionModel().new AgregarTipoConstruccionController());
	}

	public String btnModificar_action() {
		return toAbm(new TipoConstruccionModel().new ModificarTipoConstruccionController());

	}

	public String btnEliminar_action() {
		return toAbm(new TipoConstruccionModel().new EliminarTipoConstruccionController());

	}

	public String btnConsultar_action() {
		return toAbm(new TipoConstruccionModel().new ConsultarTipoConstruccionController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		TipoConstruccion locTipoConstruccion = (TipoConstruccion) pObject;
		getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
		locTipoConstruccion = getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().getTipoConstruccionPorId(locTipoConstruccion.getIdTipoConstruccion());
		return locTipoConstruccion;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Tipos de Construcci\363n";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminTipoConstruccion";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaTipoConstruccion();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return TipoConstruccion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMTipoConstruccion$AdminTipoConstruccion}";
	}
}