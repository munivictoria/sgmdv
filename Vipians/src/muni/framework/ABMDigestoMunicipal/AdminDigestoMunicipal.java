/*
 * AdminDigestoMunicipal.java
 *
 * Created on 1 de noviembre de 2006, 08:32
 * Copyright Trascender
 */

package muni.framework.ABMDigestoMunicipal;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.filtros.FiltroDigestoMunicipal;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminDigestoMunicipal extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(DigestoMunicipal.Tipo.values(), "may");
		ddTipoDigestoDefaultOptions.setOptions(op);
		ddTipoDigestoDefaultOptions.setSelectedValue(null);

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(DigestoMunicipal.Estado.values(), "may");
		ddEstadoDigestoDefaultOptions.setOptions(opEstado);

		Option[] opEjeTematico = null;
		opEjeTematico = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(DigestoMunicipal.EjeTematico.values(), "may");
		ddEjeTematicoDigestoDefaultOptions.setOptions(opEjeTematico);
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

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
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

	private ObjectListDataProvider ldpDigestoMunicipal = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDigestoMunicipal() {
		return ldpDigestoMunicipal;
	}

	public void setLdpDigestoMunicipal(ObjectListDataProvider oldp) {
		this.ldpDigestoMunicipal = oldp;
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

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
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

	private TableColumn tcDescripcion = new TableColumn();

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	private TableColumn tcEstado = new TableColumn();

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	private StaticText stDescripcion = new StaticText();

	public StaticText getStDescripcion() {
		return stDescripcion;
	}

	public void setStDescripcion(StaticText stDescripcion) {
		this.stDescripcion = stDescripcion;
	}

	private StaticText stEstado = new StaticText();

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	private DropDown ddEjeTematico = new DropDown();

	private DropDown ddTipoDigesto = new DropDown();

	public DropDown getDdTipoDigesto() {
		return ddTipoDigesto;
	}

	public void setDdTipoDigesto(DropDown dd) {
		this.ddTipoDigesto = dd;
	}

	private SingleSelectOptionsList ddTipoDigestoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoDigestoDefaultOptions() {
		return ddTipoDigestoDefaultOptions;
	}

	public void setDdTipoDigestoDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoDigestoDefaultOptions = ssol;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	private Label lblEstadoDigesto = new Label();

	public Label getLblEstadoDigesto() {
		return lblEstadoDigesto;
	}

	public void setLblEstadoDigesto(Label lblEstadoDigesto) {
		this.lblEstadoDigesto = lblEstadoDigesto;
	}

	private DropDown ddEstadoDigesto = new DropDown();

	public DropDown getDdEstadoDigesto() {
		return ddEstadoDigesto;
	}

	public void setDdEstadoDigesto(DropDown ddEstadoDigesto) {
		this.ddEstadoDigesto = ddEstadoDigesto;
	}

	public SingleSelectOptionsList getDdEstadoDigestoDefaultOptions() {
		return ddEstadoDigestoDefaultOptions;
	}

	public void setDdEstadoDigestoDefaultOptions(SingleSelectOptionsList ddEstadoDigestoDefaultOptions) {
		this.ddEstadoDigestoDefaultOptions = ddEstadoDigestoDefaultOptions;
	}

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label lblFecha) {
		this.lblFecha = lblFecha;
	}

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tfFecha) {
		this.tfFecha = tfFecha;
	}

	private SingleSelectOptionsList ddEjeTematicoDigestoDefaultOptions = new SingleSelectOptionsList();

	private SingleSelectOptionsList ddEstadoDigestoDefaultOptions = new SingleSelectOptionsList();
	private Label lblFecha = new Label();
	private TextField tfFecha = new TextField();
	private TextArea taDescripcion = new TextArea();
	private Label lblDescripcion = new Label();

	private Label lblNumero = new Label();

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	private TextField tfNumero = new TextField();

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminDigestoMunicipal() {
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroDigestoMunicipal locFiltro = this.getFiltro();

		locFiltro.setTipo(this.getDDEnumValue(getDdTipoDigesto(), DigestoMunicipal.Tipo.class));
		locFiltro.setEstado(this.getDDEnumValue(getDdEstadoDigesto(), DigestoMunicipal.Estado.class));
		locFiltro.setEjeTematico(this.getDDEnumValue(getDdEjeTematico(), DigestoMunicipal.EjeTematico.class));
		locFiltro.setNumero(this.getTextFieldValueInteger(getTfNumero()));
		locFiltro.setAnio(this.getTextFieldValueInteger(getTfFecha()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroDigestoMunicipal locFiltro = this.getFiltro();
		// String tipoSeleccion = null;

		// para verificar si el digesto es para la licitacion o la adjudicacion
		// en abmLicitacion

		// if (this.getRequestBean1().getTipoSeleccion() != null) {
		// tipoSeleccion = this.getRequestBean1().getTipoSeleccion();
		// this.getElementoPila().getObjetos().set(0, tipoSeleccion);
		// }

		// else if (locTipoSeleccion == "TITULAR") {
		// this.getDdVinculo().setDisabled(true);
		// }

		// if (this.getRequestBean1().getRespuestaABM() != null ||
		// this.getRequestBean1().getObjetoSeleccion() != null){
		this.getDdTipoDigesto().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipo())));
		this.getDdEstadoDigesto().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		// }

		this.getTfNumero().setText(locFiltro.getNumero());
		if(locFiltro.getAnio() != null) {
			this.getTfFecha().setText((new Integer(locFiltro.getAnio().toString())));
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemMunicipalidad().findListaDigestosMunicipales((FiltroDigestoMunicipal) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroDigestoMunicipal locFiltro = this.getFiltro();
		locFiltro.setTipo(null);
		locFiltro.setEstado(null);
		locFiltro.setEjeTematico(null);
		locFiltro.setNumero(null);
		locFiltro.setAnio(null);

		this.getDdTipoDigesto().setSelected(null);// Util.getEnumNameFromString(String.valueOf(DigestoMunicipal.Tipo.DECRETO))
		this.getDdEstadoDigesto().setSelected(null);// Util.getEnumNameFromString(String.valueOf(DigestoMunicipal.Estado.VIGENTE))
		this.getDdEjeTematico().setSelected(null);
		this.getTaDescripcion().setText(null);
		this.getTfFecha().setText(null);
		this.getTfNumero().setText(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpDigestoMunicipal();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaDigestosMunicipales();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaDigestosMunicipales((ArrayList) lista);
	}

	public String btnAgregar_action() {
		return toAbm(new DigestoMunicipalModel().new AgregarDigestoMunicpalController());
	}

	public String btnModificar_action() {
		return toAbm(new DigestoMunicipalModel().new ModificarDigestoMunicipalController());
	}

	public String btnEliminar_action() {
		return toAbm(new DigestoMunicipalModel().new EliminarDigestoMunicipalController());
	}

	public String btnConsultar_action() {
		return toAbm(new DigestoMunicipalModel().new ConsultarDigestoMunicipalController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		DigestoMunicipal locDigesto = (DigestoMunicipal) pObject;
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		locDigesto = this.getComunicationBean().getRemoteSystemMunicipalidad().getDigestoMunicipalPorId(locDigesto.getIdDigestoMunicipal());
		return locDigesto;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Digestos Municipales";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDigestoMunicipal";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaDigestoMunicipal();
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new String());

		return ep;
	}

	/**
	 * @return the ddEjeTematicoDigestoDefaultOptions
	 */
	public SingleSelectOptionsList getDdEjeTematicoDigestoDefaultOptions() {
		return ddEjeTematicoDigestoDefaultOptions;
	}

	/**
	 * @param ddEjeTematicoDigestoDefaultOptions
	 *            the ddEjeTematicoDigestoDefaultOptions to set
	 */
	public void setDdEjeTematicoDigestoDefaultOptions(SingleSelectOptionsList ddEjeTematicoDigestoDefaultOptions) {
		this.ddEjeTematicoDigestoDefaultOptions = ddEjeTematicoDigestoDefaultOptions;
	}

	/**
	 * @return the ddEjeTematico
	 */
	public DropDown getDdEjeTematico() {
		return ddEjeTematico;
	}

	/**
	 * @param ddEjeTematico
	 *            the ddEjeTematico to set
	 */
	public void setDdEjeTematico(DropDown ddEjeTematico) {
		this.ddEjeTematico = ddEjeTematico;
	}

	/**
	 * @return the tfDescripcion
	 */

	/**
	 * @return the lblDescripcion
	 */
	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	/**
	 * @param lblDescripcion
	 *            the lblDescripcion to set
	 */
	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal}";
	}

	@Override
	public long getSerialVersionUID() {
		return DigestoMunicipal.serialVersionUID;
	}
}