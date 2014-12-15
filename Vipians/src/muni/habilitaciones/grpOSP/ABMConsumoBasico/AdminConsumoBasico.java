/*
 * AdminConsumoBasico.java
 *
 * Created on 01 de juino de 2007, 9:55
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpOSP.ABMConsumoBasico;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminConsumoBasico extends AdminPageBean {

	private ObjectListDataProvider ldpConsumosBasicos = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpConsumosBasicos() {
		return ldpConsumosBasicos;
	}

	public void setLdpConsumosBasicos(ObjectListDataProvider oldp) {
		this.ldpConsumosBasicos = oldp;
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

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public AdminConsumoBasico() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		// ConsumoBasico consumoBasico = null;
		//
		// consumoBasico = (ConsumoBasico) this.obtenerObjetoDelElementoPila(0, ConsumoBasico.class);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpConsumosBasicos();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaConsumosBasicos();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaConsumosBasicos((ArrayList) lista);
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().getListaConsumosBasicos((FiltroConsumoBasico) pFiltro);
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Consumos Basicos";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminConsumoBasico";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaConsumoBasico();
	}

	public String btnAgregar_action() {
		return toAbm(new ConsumoBasicoModel().new AgregarConsumoBasicoController());
	}

	public String btnModificar_action() {
		return toAbm(new ConsumoBasicoModel().new ModificarConsumoBasicoController());
	}

	public String btnConsultar_action() {
		return toAbm(new ConsumoBasicoModel().new ConsultarConsumoBasicoController());
	}

	public String btnEliminar_action() {
		return toAbm(new ConsumoBasicoModel().new EliminarConsumoBasicoController());
	}

	@Override
	protected void limpiarObjetosUsados() {
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpOSP$ABMConsumoBasico$AdminConsumoBasico}";
	}

	@Override
	public long getSerialVersionUID() {
		return ConsumoBasico.serialVersionUID;
	}
}