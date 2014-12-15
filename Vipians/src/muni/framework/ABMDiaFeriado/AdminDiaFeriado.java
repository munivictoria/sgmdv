/*
 * AdminDiaFeriado.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */
package muni.framework.ABMDiaFeriado;

import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.filtros.FiltroDiaFeriado;
import com.trascender.framework.recurso.persistent.DiaFeriado;
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
public class AdminDiaFeriado extends AdminPageBean {

	private ObjectListDataProvider ldpDiaFeriado = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDiaFeriado() {
		return ldpDiaFeriado;
	}

	public void setLdpDiaFeriado(ObjectListDataProvider oldp) {
		this.ldpDiaFeriado = oldp;
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

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfAno = new TextField();

	public TextField getTfAno() {
		return tfAno;
	}

	public void setTfAno(TextField tf) {
		this.tfAno = tf;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dtc) {
		this.dateTimeConverter1 = dtc;
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

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public AdminDiaFeriado() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new DiaFeriado());
		ep.getObjetos().add(ind++, null); // a�o

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroDiaFeriado locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
		locFiltro.setAnio(this.getTextFieldValueInteger(getTfAno()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroDiaFeriado locFiltro = this.getFiltro();
		
		this.getTfNombre().setText(locFiltro.getNombre());
		if (locFiltro.getAnio() != null) {
			this.getTfAno().setText(locFiltro.getAnio().toString());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemMunicipalidad().findListaDiasFeriados((FiltroDiaFeriado) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroDiaFeriado locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setAnio(null);
		
		this.getTfNombre().setText(null);
		this.getTfAno().setText(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpDiaFeriado();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaDiasFeriados();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaDiasFeriados((ArrayList) lista);
	}

	public String btnAgregar_action() {
		return toAbm(new DiaFeriadoModel().new AgregarDiaFeriadoController());
	}

	public String btnModificar_action() {
		return toAbm(new DiaFeriadoModel().new ModificarDiaFeriadoController());
	}

	public String btnEliminar_action() {
		return toAbm(new DiaFeriadoModel().new EliminarDiaFeriadoController());
	}

	public String btnConsultar_action() {
		return toAbm(new DiaFeriadoModel().new ConsultarDiaFeriadoController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		DiaFeriado locDiaFeriado = (DiaFeriado) pObject;
		getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
		locDiaFeriado = getComunicationBean().getRemoteSystemMunicipalidad().getDiaFeriadoPorId(locDiaFeriado.getIdDiaFeriado());
		return locDiaFeriado;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Dias Feriados";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDiaFeriado";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaDiaFeriado();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return DiaFeriado.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMDiaFeriado$AdminDiaFeriado}";
	}
}