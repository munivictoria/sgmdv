/*
 * AdminZona.java
 *
 * Created on 17 de octubre de 2006, 8:24
 * Copyright Trascender
 */
package muni.catastro.ABMZona;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroZona;
import com.trascender.catastro.recurso.persistent.Zona;
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
public class AdminZona extends AdminPageBean {

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong>
	 * This method is automatically generated, so any user-specified code
	 * inserted here is subject to being replaced.
	 * </p>
	 */
	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		this.habilitarBtnExportar();
	}

	private ObjectListDataProvider ldpZonas = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpZonas() {
		return ldpZonas;
	}

	public void setLdpZonas(ObjectListDataProvider oldp) {
		this.ldpZonas = oldp;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
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

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
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
	public AdminZona() {
	}

	// CAMBIAR: Objetos administrados por la pagina.
	// Generar getters y setters.
	// En el getter poner:
	// if (this.objeto == null) this.objeto = new Objeto();
	private Zona zonaABuscar = null;

	// CAMBIAR: Constantes que varian segun la pagina.
	// cantidad de objetos administrados por la pagina
	// private final int CANTIDAD_OBJETOS = 1;
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Administraci\363n de Zonas";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AdminZona";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = true;

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private TextField tfZonificacion = new TextField();

	public TextField getTfZonificacion() {
		return tfZonificacion;
	}

	public void setTfZonificacion(TextField tfZonificacion) {
		this.tfZonificacion = tfZonificacion;
	}

	private Button btnSeleccionarZonificacion = new Button();

	public Button getBtnSeleccionarZonificacion() {
		return btnSeleccionarZonificacion;
	}

	public void setBtnSeleccionarZonificacion(Button btnSeleccionarZonificacion) {
		this.btnSeleccionarZonificacion = btnSeleccionarZonificacion;
	}

	private HtmlAjaxCommandButton btnLimpiarZonificacion = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarZonificacion() {
		return btnLimpiarZonificacion;
	}

	public void setBtnLimpiarZonificacion(HtmlAjaxCommandButton btnLimpiarZonificacion) {
		this.btnLimpiarZonificacion = btnLimpiarZonificacion;
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

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
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

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroZona locFiltro = getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroZona locFiltro = getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
		if (locFiltro.getZonificacion() != null) {
			this.getTfZonificacion().setText(locFiltro.getZonificacion().getNombre());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().findListaZonas((FiltroZona) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroZona locFiltro = getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setZonificacion(null);

		this.getTfNombre().setText(null);
		this.getTfZonificacion().setText(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpZonas();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaZonas();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaZonas((ArrayList) lista);
	}

	// </editor-fold>

	public String btnAgregar_action() {
		return toAbm(new ZonaModel().new AgregarZonaController());
	}

	public String btnModificar_action() {
		return toAbm(new ZonaModel().new ModificarZonaController());
	}

	public String btnEliminar_action() {
		return toAbm(new ZonaModel().new EliminarZonaController());
	}

	public Zona getZonaABuscar() {
		return zonaABuscar;
	}

	public void setZonaABuscar(Zona zonaABuscar) {
		this.zonaABuscar = zonaABuscar;
	}

	/**
	 * Mines: boton consultar...
	 */
	public String btnConsultar_action() {
		return toAbm(new ZonaModel().new ConsultarZonaController());
	}

	public String btnSeleccionarZonificacion_action() {
		return navegarParaSeleccionar("AdminZonificacion");
	}

	public String btnLimpiarZonificacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(getTfZonificacion());
			FiltroZona locFiltro = getFiltro();
			locFiltro.setZonificacion(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Zona locZona = (Zona) pObject;
		getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(this.getSessionBean1().getLlave());
		locZona = getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().getZonaById(locZona.getIdZona());
		return locZona;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Zonas";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminZona";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaZona();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Zonificacion) {
			Zonificacion zonificacion = (Zonificacion) pObject;
			FiltroZona locFiltro = getFiltro();
			locFiltro.setZonificacion(zonificacion);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return Zona.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMZona$AdminZona}";
	}
}