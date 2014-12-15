/*
 * AdminLocalidad.java
 *
 * Created on 5 de octubre de 2006, 07:46
 * Copyright Trascender SRL
 */
package muni.framework.ABMLocalidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.filtros.FiltroLocalidad;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Provincia;
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
public class AdminLocalidad extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		Set<String> locListaProvincias = getApplicationBean1().getMapaProvincia().keySet();

		Option[] opProvincias = new Option[locListaProvincias.size() + 1];
		int i = 0;
		opProvincias[i++] = new Option("", "");
		for (String cadaProvincia : locListaProvincias) {
			opProvincias[i++] = new Option(cadaProvincia, cadaProvincia);
		}
		this.ddProvinciaOptions.setOptions(opProvincias);
	}

	private HtmlAjaxCommandButton btnLimpiarProvincia = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarProvincia() {
		return btnLimpiarProvincia;
	}

	public void setBtnLimpiarProvincia(HtmlAjaxCommandButton btnLimpiarProvincia) {
		this.btnLimpiarProvincia = btnLimpiarProvincia;
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

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private ObjectListDataProvider ldpLocalidad = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLocalidad() {
		return ldpLocalidad;
	}

	public void setLdpLocalidad(ObjectListDataProvider oldp) {
		this.ldpLocalidad = oldp;
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

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private DropDown ddProvincia = new DropDown();
	private SingleSelectOptionsList ddProvinciaOptions = new SingleSelectOptionsList();

	public DropDown getDdProvincia() {
		return ddProvincia;
	}

	public void setDdProvincia(DropDown ddProvincia) {
		this.ddProvincia = ddProvincia;
	}

	public SingleSelectOptionsList getDdProvinciaOptions() {
		return ddProvinciaOptions;
	}

	public void setDdProvinciaOptions(SingleSelectOptionsList ddProvinciaOptions) {
		this.ddProvinciaOptions = ddProvinciaOptions;
	}

	private Button btnSeleccionarProvincia = new Button();

	public Button getBtnSeleccionarProvincia() {
		return btnSeleccionarProvincia;
	}

	public void setBtnSeleccionarProvincia(Button b) {
		this.btnSeleccionarProvincia = b;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextField tfCodigoPostal = new TextField();

	public TextField getTfCodigoPostal() {
		return tfCodigoPostal;
	}

	public void setTfCodigoPostal(TextField tf) {
		this.tfCodigoPostal = tf;
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
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminLocalidad() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Localidad());
		ep.getObjetos().add(ind++, new Provincia());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroLocalidad locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
		locFiltro.setCodPostal(this.getTextFieldValue(getTfCodigoPostal()));
		locFiltro.setProvincia(this.getDDObjectValue(getDdProvincia(), getApplicationBean1().getMapaProvincia()));
	}

	private Provincia getProvinciaPorNombre(String pProvincia) {
		return getApplicationBean1().getMapaProvincia().get(pProvincia);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroLocalidad locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
		this.getTfCodigoPostal().setText(locFiltro.getCodPostal());
		if (locFiltro.getProvincia() != null) {
			this.getDdProvincia().setSelected(locFiltro.getProvincia().toString());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemMunicipalidad().findLocalidad((FiltroLocalidad) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroLocalidad locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setCodPostal(null);
		locFiltro.setProvincia(null);

		this.getTfNombre().setText("");
		this.getTfCodigoPostal().setText("");
		this.ddProvincia.setSelected(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLocalidad();
	}

	@Override
	protected List getListaDelCommunication() {
		return (ArrayList) this.getComunicationBean().getListaLocalidades();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaLocalidades((ArrayList) lista);
	}

	public String btnSeleccionarProvincia_action() {
		return navegarParaSeleccionar("AdminProvincia");
	}

	public String btnAgregar_action() {
		return toAbm(new LocalidadModel().new AgregarLocalidadController());
	}

	public String btnModificar_action() {
		return toAbm(new LocalidadModel().new ModificarLocalidadController());
	}

	public String btnEliminar_action() {
		return toAbm(new LocalidadModel().new EliminarLocalidadController());
	}

	public String btnConsultar_action() {
		return toAbm(new LocalidadModel().new ConsultarLocalidadController());
	}

	public String btnLimpiarProvincia_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.ddProvincia.setSelected(null);
			FiltroLocalidad locFiltro = this.getFiltro();
			locFiltro.setProvincia(null);

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
		Localidad locLocalidad = (Localidad) pObject;
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
		locLocalidad = this.getComunicationBean().getRemoteSystemMunicipalidad().getLocalidadPorId(locLocalidad.getIdLocalidad());
		return locLocalidad;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Localidades";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminLocalidad";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaLocalidad();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroLocalidad locFiltro = getFiltro();
		if (pObject instanceof Provincia) {
			Provincia locProvincia = (Provincia) pObject;

			locFiltro.setProvincia(locProvincia);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return Localidad.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMLocalidad$AdminLocalidad}";
	}
}