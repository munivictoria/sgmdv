/*
 * AdminCalle.java
 *
 * Created on 1 de noviembre de 2006, 08:32
 * Copyright Trascender
 */
package muni.catastro.ABMCalle;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroCalle;
import com.trascender.catastro.recurso.persistent.Calle;
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
public class AdminCalle extends AdminPageBean {

	private Label lblActiva = new Label();
	private Label label1 = new Label();
	private Checkbox cbActiva = new Checkbox();
	private Label lblCodigo = new Label();
	private TextField tfCodigo = new TextField();


	public Label getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(Label lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public Label getLblActiva() {
		return lblActiva;
	}

	public void setLblActiva(Label lblActiva) {
		this.lblActiva = lblActiva;
	}

	public Checkbox getCbActiva() {
		return cbActiva;
	}

	public void setCbActiva(Checkbox cbActiva) {
		this.cbActiva = cbActiva;
	}

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private HtmlAjaxCommandButton btnLimpiarTipoCalle = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarTipoCalle() {
		return btnLimpiarTipoCalle;
	}

	public void setBtnLimpiarTipoCalle(HtmlAjaxCommandButton btnLimpiarTipoCalle) {
		this.btnLimpiarTipoCalle = btnLimpiarTipoCalle;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TextField tfTipoCalle = new TextField();

	public TextField getTfTipoCalle() {
		return tfTipoCalle;
	}

	public void setTfTipoCalle(TextField tf) {
		this.tfTipoCalle = tf;
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

	private ObjectListDataProvider ldpCalle = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCalle() {
		return ldpCalle;
	}

	public void setLdpCalle(ObjectListDataProvider oldp) {
		this.ldpCalle = oldp;
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

	private Button btnSeleccionarTipoCalle = new Button();

	public Button getBtnSeleccionarTipoCalle() {
		return btnSeleccionarTipoCalle;
	}

	public void setBtnSeleccionarTipoCalle(Button b) {
		this.btnSeleccionarTipoCalle = b;
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

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminCalle() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroCalle locFiltro = getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
		locFiltro.setEstado(this.getCbActiva().isChecked());
		locFiltro.setCodigo(this.getTextFieldValue(getTfCodigo()));

	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroCalle locFiltro = getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
		this.getTfCodigo().setText(locFiltro.getCodigo());
		if (locFiltro.getTipoCalle() != null) {
			this.getTfTipoCalle().setText(locFiltro.getTipoCalle().toString());
		}
		this.getCbActiva().setValue(locFiltro.getEstado());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().findListaCalles((FiltroCalle) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroCalle locFiltro = getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setEstado(null);
		locFiltro.setTipoCalle(null);
		locFiltro.setCodigo(null);

		this.getTfCodigo().setText("");
		this.getTfNombre().setText("");
		this.getTfTipoCalle().setText("");
		this.getCbActiva().setSelected(true);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCalle();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaCalles();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaCalles((ArrayList) lista);
	}

	public String btnConsultar_action() {
		return toAbm(new CalleModel().new ConsultarCalleController());
	}

	public String btnAgregar_action() {
		return toAbm(new CalleModel().new AgregarCalleController());
	}

	public String btnModificar_action() {
		Calle calle = (Calle) getObjetoSeleccionado();
		if (calle.isActivo()) {
			return toAbm(new CalleModel().new ModificarCalleController());
		} else {
			error("Solo pueden modificarse las calles activas.");
			return null;
		}
	}

	public String btnEliminar_action() {
		Calle calle = (Calle) getObjetoSeleccionado();
		if (calle.isActivo()) {
			return toAbm(new CalleModel().new EliminarCalleController());
		} else {
			error("La calle ya esta inactiva.");
			return null;
		}
	}

	public String btnSeleccionarTipoCalle_action() {
		return navegarParaSeleccionar("AdminTipoCalle");
	}

	@Override
	protected String getIdSinPrefijo(String idCompleto, String idComponente) {
		String retorno = null;
		if (idCompleto != null && idCompleto.length() > 0) {
			retorno = idCompleto.substring(idCompleto.indexOf(idComponente) + idComponente.length() + 1);
		}
		return retorno;
	}

	public String btnLimpiarTipoCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.limpiarObjeto(getTfTipoCalle());
			FiltroCalle locFiltro = getFiltro();
			locFiltro.setTipoCalle(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return getComunicationCatastroBean().getTablePhaseListenerCalle();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Calle locCalle = (Calle) pObject;
		getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
		locCalle = getComunicationCatastroBean().getRemoteSystemInformacionGeografica().getCallePorId(locCalle.getIdCalle());
		return locCalle;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Calles";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminCalle";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaCalle();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroCalle locFiltro = getFiltro();
		if (pObject instanceof TipoCalle) {
			TipoCalle tipoCalle = (TipoCalle) pObject;
			locFiltro.setTipoCalle(tipoCalle);
		}
	}

	@Override
	public long getSerialVersionUID() {
		return Calle.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMCalle$AdminCalle}";
	}
}