/*
 * AdminZonificacion.java
 *
 * Created on 19 de octubre de 2006, 15:34
 * Copyright Trascender
 */
package muni.catastro.ABMZonificacion;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroZonificacion;
import com.trascender.catastro.recurso.persistent.Zonificacion;
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
public class AdminZonificacion extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		this.habilitarBtnExportar();
	}

	private ObjectListDataProvider ldpZonificacion = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpZonificacion() {
		return ldpZonificacion;
	}

	public void setLdpZonificacion(ObjectListDataProvider oldp) {
		this.ldpZonificacion = oldp;
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

	private Zonificacion zonificacionABuscar = null;

	public Zonificacion getZonificacionABuscar() {
		return this.zonificacionABuscar;
	}

	public void setZonificacionABuscar(Zonificacion pZonificacion) {
		this.zonificacionABuscar = pZonificacion;
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

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroZonificacion locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroZonificacion locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().findListaZonificacion((FiltroZonificacion) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroZonificacion locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		
		this.getTfNombre().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpZonificacion();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getCommunicationCatastroBean().getListaZonificacion();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationCatastroBean().setListaZonificacion((ArrayList) lista);
	}

	// </editor-fold>

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
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
	public AdminZonificacion() {
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ámbito.
	 * </p>
	 */
	protected muni.ComunicationCatastroBean getCommunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	public String btnAgregar_action() {
		return toAbm(new ZonificacionModel().new AgregarZonificacionController());
	}

	public String btnModificar_action() {
		return toAbm(new ZonificacionModel().new ModificarZonificacionController());
	}

	public String btnEliminar_action() {
		return toAbm(new ZonificacionModel().new EliminarZonificacionController());
	}

	public String btnConsultar_action() {
		return toAbm(new ZonificacionModel().new ConsultarZonificacionController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Zonificacion locZonificacion = (Zonificacion) pObject;
		locZonificacion = getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().getZonificacionPorId(locZonificacion.getIdZonificacion());
		return locZonificacion;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Zonificaciones";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminZonificacion";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaZonificacion();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return Zonificacion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMZonificacion$AdminZonificacion}";
	}
}