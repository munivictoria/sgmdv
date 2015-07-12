/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.framework.ABMReporte;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.filtros.FiltroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminReporte extends AdminPageBean {

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

	private ObjectListDataProvider ldpReporte = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpReporte() {
		return ldpReporte;
	}

	public void setLdpReporte(ObjectListDataProvider oldp) {
		this.ldpReporte = oldp;
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

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
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

	private Button btnReporteDinamico = new Button();

	public Button getBtnReporteDinamico() {
		return btnReporteDinamico;
	}

	public void setBtnReporteDinamico(Button btnReporteDinamico) {
		this.btnReporteDinamico = btnReporteDinamico;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminReporte() {
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaReporte();
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroReporte locFiltro = this.getFiltro();
		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroReporte locFiltro = this.getFiltro();
		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemParametro().findListaReporte((FiltroReporte) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroReporte locFiltro = this.getFiltro();
		locFiltro.setNombre(null);

		this.getTfNombre().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpReporte();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationBean().getListaReporte();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaReporte(lista);
	}

	public String btnAgregar_action() {
		return toAbm(new ReporteModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new ReporteModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new ReporteModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new ReporteModel().new ConsultarController());
	}

	public String btnEjecutar_action() {
		return toAbm(new EjecutarReporteModel().new EjecutarController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Reporte locReporte = (Reporte) pObject;
		locReporte = getComunicationBean().getRemoteSystemParametro().getReporteByID(locReporte.getIdReporte());

		return locReporte;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Reportes";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminReporte";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	public long getSerialVersionUID() {
		return Reporte.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMReporte$AdminReporte}";
	}

}