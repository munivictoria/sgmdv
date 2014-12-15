/*
 * AdminConceptoSellado.java
 *
 * Created on 28 de mayo de 2007, 11.21
 * Copyright Trascender SRL
 */

package muni.comunes.ABMConceptoIngresoVario;

import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.contabilidad.recurso.filtros.FiltroConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminConceptoIngresoVario extends AdminPageBean {

	protected PanelGroup pgParametros = new PanelGroup();
	protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();
	private Label label1 = new Label();
	private ObjectListDataProvider ldpConceptosIngresoVario = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpConceptosIngresoVario() {
		return ldpConceptosIngresoVario;
	}

	public void setLdpConceptosIngresoVario(ObjectListDataProvider ldpConceptosIngresoVario) {
		this.ldpConceptosIngresoVario = ldpConceptosIngresoVario;
	}

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

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
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

	public AdminConceptoIngresoVario() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroConceptoIngresoVario locFiltro = this.getFiltro();
		locFiltro.setNombre(getTextFieldValue(this.getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroConceptoIngresoVario locFiltro = this.getFiltro();
		this.getTfNombre().setText(locFiltro.getNombre());

	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroConceptoIngresoVario locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		this.getTfNombre().setText(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpConceptosIngresoVario();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationCajaBean().getListaConceptosIngresoVario();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationCajaBean().setListaConceptosIngresoVario(lista);
	}

	public String btnAgregar_action() {
		return toAbm(new ConceptoIngresoVarioModel().new AgregarConceptoIngresoVarioController());
	}

	public String btnModificar_action() {
		return toAbm(new ConceptoIngresoVarioModel().new ModificarConceptoIngresoVarioController());
	}

	public String btnEliminar_action() {
		return toAbm(new ConceptoIngresoVarioModel().new EliminarConceptoIngresoVarioController());
	}

	public String btnConsultar_action() {
		return toAbm(new ConceptoIngresoVarioModel().new ConsultarConceptoIngresoVarioController());
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) pObject;
		this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().getConceptoIngresoVarioByID(locConcepto.getIdConceptoIngresoVario());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().findListaConceptoIngresoVario((FiltroConceptoIngresoVario) pFiltro);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationCajaBean().getTablaConceptosIngresosVarios();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Conceptos de Ingresos Varios";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminConceptoIngresoVario";
	}

	@Override
	public String getNombreBean() {
		return "#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario}";
	}

	@Override
	public long getSerialVersionUID() {
		return ConceptoIngresoVario.serialVersionUID;
	}
}