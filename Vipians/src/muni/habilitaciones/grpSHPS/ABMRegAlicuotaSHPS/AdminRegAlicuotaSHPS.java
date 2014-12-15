/*
 * AdminRegAlicuotaSHPS.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpSHPS.ABMRegAlicuotaSHPS;

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
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroRubroSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminRegAlicuotaSHPS extends AdminPageBean {

	private Label lblCodigoCiiu = new Label();
	private TextField tfCodigoCiiu = new TextField();
	private Button btnSeleccionarCodigoCiiu = new Button();
	private HtmlAjaxCommandButton btnLimpiarCodigoCiiu = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarCodigoCiiu() {
		return btnLimpiarCodigoCiiu;
	}

	public void setBtnLimpiarCodigoCiiu(HtmlAjaxCommandButton btnLimpiarCodigoCiiu) {
		this.btnLimpiarCodigoCiiu = btnLimpiarCodigoCiiu;
	}

	public Button getBtnSeleccionarCodigoCiiu() {
		return btnSeleccionarCodigoCiiu;
	}

	public void setBtnSeleccionarCodigoCiiu(Button btnSeleccionarCodigoCiiu) {
		this.btnSeleccionarCodigoCiiu = btnSeleccionarCodigoCiiu;
	}

	public Label getLblCodigoCiiu() {
		return lblCodigoCiiu;
	}

	public void setLblCodigoCiiu(Label lblCodigoCiiu) {
		this.lblCodigoCiiu = lblCodigoCiiu;
	}

	public TextField getTfCodigoCiiu() {
		return tfCodigoCiiu;
	}

	public void setTfCodigoCiiu(TextField tfCodigoCiiu) {
		this.tfCodigoCiiu = tfCodigoCiiu;
	}

	private ObjectListDataProvider ldpRegAlicuotaSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRegAlicuotaSHPS() {
		return ldpRegAlicuotaSHPS;
	}

	public void setLdpRegAlicuotaSHPS(ObjectListDataProvider oldp) {
		this.ldpRegAlicuotaSHPS = oldp;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfNombre) {
		this.tfCodigo = tfNombre;
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

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
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

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public AdminRegAlicuotaSHPS() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroRubroSHPS locFiltro = getFiltro();
		List atributosDinamicos = null;

		try {
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Rubro.serialVersionUID, null, true);
		} catch(Exception e) {

		}
		locFiltro.setListaAtributo(atributosDinamicos);

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroRubroSHPS locFiltro = this.getFiltro();

		locFiltro.setCodigo(getTextFieldValue(this.getTfCodigo()));

		if(locFiltro.getListaAtributo() != null) {
			locFiltro.setListaAtributo(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributo()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroRubroSHPS locFiltro = this.getFiltro();

		this.getTfCodigo().setText(locFiltro.getCodigo());
		if(locFiltro.getCodigoCiuu() != null) {
			this.getTfCodigoCiiu().setText(locFiltro.getCodigoCiuu());
		}

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributo(), "#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributo());
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroRubroSHPS locFiltro = this.getFiltro();
		locFiltro.setCodigo(null);
		locFiltro.setCodigoCiuu(null);

		panelAtributoDinamico.limpiarCampos();
		// CAMBIAR: Limpiar los textField y dropDown
		this.getTfCodigo().setText("");
		this.getTfCodigoCiiu().setText("");
		this.setListaDelCommunication(new ArrayList());
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpRegAlicuotaSHPS();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaRegAlicuotasSHPS();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaRegAlicuotasSHPS((ArrayList) lista);
	}

	public String btnSeleccionarCodigoCiiu_action() {
		return navegarParaSeleccionar("AdminCodigoCiiu");
	}

	public String btnLimpiarCodigoCiiu_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			FiltroRubroSHPS locFiltro = this.getFiltro();
			this.getTfCodigoCiiu().setText("");
			locFiltro.setCodigoCiuu(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregar_action() {
		return toAbm(new RegAlicuotaModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new RegAlicuotaModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new RegAlicuotaModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new RegAlicuotaModel().new ConsultarController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Rubro locRubro = (Rubro) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().setLlave(this.getSessionBean1().getLlave());
		locRubro = this.getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().getRubroPorId(locRubro.getIdTipoAlicuota());
		return locRubro;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Registros de Alicuota";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminRegAlicuotaSHPS";
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().findListaRubros((FiltroRubroSHPS) pFiltro);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaRubros();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroRubroSHPS locFiltro = this.getFiltro();

		if(pObject instanceof CodigoCiiu) {
			locFiltro.setCodigoCiuu((CodigoCiiu) pObject);
		}
	}

	@Override
	public long getSerialVersionUID() {
		return Rubro.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS}";
	}
}