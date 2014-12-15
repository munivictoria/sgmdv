/*
 * AdminProvincia.java
 *
 * Created on 3 de octubre de 2006, 15:49
 * Copyright Trascender
 */
package muni.framework.ABMProvincia;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
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
import com.trascender.framework.recurso.filtros.FiltroProvincia;
import com.trascender.framework.recurso.persistent.Pais;
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
public class AdminProvincia extends AdminPageBean {

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private HtmlAjaxCommandButton btnLimpiarPais = new HtmlAjaxCommandButton();
	
	public HtmlAjaxCommandButton getBtnLimpiarPais() {
		return btnLimpiarPais;
	}

	public void setBtnLimpiarPais(HtmlAjaxCommandButton btnLimpiarPais) {
		this.btnLimpiarPais = btnLimpiarPais;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	@Override
	protected void _init() throws Exception {
		Set<String> locListaPaises = getApplicationBean1().getMapaPais().keySet();

		Option[] opPaises = new Option[locListaPaises.size() + 1];
		int i = 0;
		opPaises[i++] = new Option("", "");
		for (String cadaPais : locListaPaises) {
			opPaises[i++] = new Option(cadaPais, cadaPais);
		}
		this.ddPaisOptions.setOptions(opPaises);
	}

	private DropDown ddPais = new DropDown();
	private SingleSelectOptionsList ddPaisOptions = new SingleSelectOptionsList();

	public DropDown getDdPais() {
		return ddPais;
	}

	public void setDdPais(DropDown ddPais) {
		this.ddPais = ddPais;
	}

	public SingleSelectOptionsList getDdPaisOptions() {
		return ddPaisOptions;
	}

	public void setDdPaisOptions(SingleSelectOptionsList ddPaisOptions) {
		this.ddPaisOptions = ddPaisOptions;
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

	private ObjectListDataProvider ldpProvincia = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpProvincia() {
		return ldpProvincia;
	}

	public void setLdpProvincia(ObjectListDataProvider oldp) {
		this.ldpProvincia = oldp;
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

	private Button btnSeleccionarPais = new Button();

	public Button getBtnSeleccionarPais() {
		return btnSeleccionarPais;
	}

	public void setBtnSeleccionarPais(Button b) {
		this.btnSeleccionarPais = b;
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
	public AdminProvincia() {
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroProvincia locFiltro = this.getFiltro();

		locFiltro.setProvincia(this.getTextFieldValue(getTfNombre()));
		locFiltro.setPais(this.getDDObjectValue(getDdPais(), getApplicationBean1().getMapaPais()));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroProvincia locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getProvincia());
		if (locFiltro.getPais() != null && locFiltro.getPais().getIdPais() != -1) {
			this.getDdPais().setSelected(locFiltro.getPais().toString());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemMunicipalidad().findProvincia((FiltroProvincia) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroProvincia locFiltro = this.getFiltro();
		locFiltro.setProvincia(null);
		locFiltro.setPais(null);

		this.getTfNombre().setText("");
		this.ddPais.setSelected(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpProvincia();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaProvincias();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaProvincias((ArrayList) lista);
	}

	public String btnSeleccionarPais_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			// APLICAR LOGICA AQUI...
			try {
				RowKey rk = this.getSeleccionado();
				if (rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch (Exception ex) {
				// CAMBIAR:
				log("AdminProvincia" + "_SeleccionarPaisError:", ex);
				error("Administraci\363n de Provincias" + " - Seleccionar Pa\355s: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminPais";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregar_action() {
		return toAbm(new ProvinciaModel().new AgregarProvinciaController());
	}

	public String btnModificar_action() {
		return toAbm(new ProvinciaModel().new ModificarProvinciaController());
	}

	public String btnEliminar_action() {
		return toAbm(new ProvinciaModel().new EliminarPronvinciaController());
	}

	public String btnConsultar_action() {
		return toAbm(new ProvinciaModel().new ConsultarPronvinciaController());
	}

	public String btnLimpiarPais_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.ddPais.setSelected(null);
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
		Provincia locProvincia = (Provincia) pObject;
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		locProvincia = this.getComunicationBean().getRemoteSystemMunicipalidad().getProvinciaPorId(locProvincia.getIdProvincia());
		return locProvincia;
	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Provincias";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminProvincia";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaProvincia();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Pais) {
			if (pObject != null) {
				Pais pais = (Pais) pObject;
				FiltroProvincia locFiltro = getFiltro();
				locFiltro.setPais(pais);
			}
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return Provincia.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMProvincia$AdminProvincia}";
	}
}