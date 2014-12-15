/*
 * AdminRol.java
 *
 * Created on 19 de octubre de 2006, 15:30
 * Copyright Trascender SRL
 */
package muni.framework.ABMRol;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.filtros.FiltroRol;
import com.trascender.framework.recurso.persistent.Rol;
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
public class AdminRol extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private ObjectListDataProvider ldpRol = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRol() {
		return ldpRol;
	}

	public void setLdpRol(ObjectListDataProvider oldp) {
		this.ldpRol = oldp;
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

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Checkbox checkboxFirma = new Checkbox();

	public Checkbox getCheckboxFirma() {
		return checkboxFirma;
	}

	public void setCheckboxFirma(Checkbox c) {
		this.checkboxFirma = c;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
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
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminRol() {
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroRol locFiltro = this.getFiltro();
		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroRol locFiltro = this.getFiltro();
		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemRol().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemRol().findRol((FiltroRol) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroRol locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		
		this.getTfNombre().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpRol();
	}

	@Override
	protected List getListaDelCommunication() {
		return (ArrayList) this.getComunicationBean().getListaRoles();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaRoles((ArrayList) lista);
	}

	public String btnAgregar_action() {
		return toAbm(new RolModel().new AgregarRolController());
	}

	public String btnModificar_action() {
		return toAbm(new RolModel().new ModificarRolController());
	}

	public String btnEliminar_action() {
		return toAbm(new RolModel().new EliminarRolController());
	}

	public String btnConsultar_action() {
		return toAbm(new RolModel().new ConsultarRolController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Rol locRol = (Rol) pObject;
		this.getComunicationBean().getRemoteSystemRol().setLlave(getSessionBean1().getLlave());
		locRol = this.getComunicationBean().getRemoteSystemRol().getRolById(locRol.getIdRol());
		return locRol;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Roles";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminRol";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaRol();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return Rol.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMRol$AdminRol}";
	}
}