/*
 * AdminPais.java
 *
 * Created on 21 de septiembre de 2006, 10:18
 * Copyright Trascender
 */
package muni.framework.ABMPais;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.filtros.FiltroPais;
import com.trascender.framework.recurso.persistent.Pais;
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
public class AdminPais extends AdminPageBean {

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

	// CAMBIAR: Objetos administrados por la pagina.
	// Generar getters y setters.
	// En el getter poner:
	// if (this.objeto == null) this.objeto = new Objeto();
	private Pais paisABuscar = null;

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

	private ObjectListDataProvider ldpPais = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPais() {
		return ldpPais;
	}

	public void setLdpPais(ObjectListDataProvider oldp) {
		this.ldpPais = oldp;
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
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminPais() {
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroPais locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroPais locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemMunicipalidad().findPais((FiltroPais) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroPais locFiltro = this.getFiltro();
		locFiltro.setNombre(null);

		this.getTfNombre().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpPais();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaPaises();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaPaises((ArrayList) lista);
	}

	public Pais getPaisABuscar() {
		if (this.paisABuscar == null) {
			this.paisABuscar = new Pais();
		}
		return paisABuscar;
	}

	public void setPaisABuscar(Pais paisABuscar) {
		this.paisABuscar = paisABuscar;
	}

	public String btnAgregar_action() {
		return toAbm(new PaisModel().new AgregarPaisController());
	}

	public String btnModificar_action() {
		return toAbm(new PaisModel().new ModificarPaisController());
	}

	public String btnEliminar_action() {
		return toAbm(new PaisModel().new EliminarPaisController());
	}

	public String btnConsultar_action() {
		return toAbm(new PaisModel().new ConsultarPaisController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Pais locPais = (Pais) pObject;
		getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
		locPais = getComunicationBean().getRemoteSystemMunicipalidad().getPaisPorId(locPais.getIdPais());
		return locPais;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Paises";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminPais";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaPais();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return Pais.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMPais$AdminPais}";
	}
}