package muni.framework.ABMReporteJasper;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.filtros.FiltroReportesJasper;
import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminReporteJasper extends AdminPageBean {
	
	Label lblNombre = new Label();
	TextField tfNombre = new TextField();
	StaticText staticText2 = new StaticText();
	
	
	public StaticText getStaticText2() {
		return staticText2;
	}
	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}
	public Label getLblNombre() {
		return lblNombre;
	}
	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}
	public TextField getTfNombre() {
		return tfNombre;
	}
	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}
	
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}
	
	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroReportesJasper locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}
	
	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroReportesJasper locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
	}
	
	@Override
	protected void limpiarObjetosUsados() {
		FiltroReportesJasper locFiltro = getFiltro();
		locFiltro.setNombre(null);
		
		this.getTfNombre().setText("");
	}
	
	public String btnAgregar_action() {
		return toAbm(new ReporteJasperModel().new AgregarReporteController());
	}

	public String btnModificar_action() {
		return toAbm(new ReporteJasperModel().new ModificarReporteController());
	}

	public String btnConsultar_action() {
		return toAbm(new ReporteJasperModel().new ConsultarReporteController());
	}
	
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemParametro().findListaReportesJasper((FiltroReportesJasper) pFiltro);
	}
	
	private ObjectListDataProvider ldpReportesJasper = new ObjectListDataProvider();
	
	public ObjectListDataProvider getLdpReportesJasper() {
		return ldpReportesJasper;
	}

	public void setLdpReportesJasper(ObjectListDataProvider ldpReportesJasper) {
		this.ldpReportesJasper = ldpReportesJasper;
	}
	
	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpReportesJasper();
	}

	@Override
	protected List getListaDelCommunication() {
		return (ArrayList) this.getComunicationBean().getListaReportesJasper();
	}
	
	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaReportesJasper((ArrayList) lista);
	}
	
	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ReportesJasper locReportes = (ReportesJasper) pObject;
		this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemParametro().getReporteJasperPorId(locReportes.getIdReporte());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Reportes";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminReporteJasper";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaReporteJasper();
	}
	
	@Override
	public long getSerialVersionUID() {
		return ReportesJasper.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMAReporteJasper$AdminReporteJasper}";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroReportesJasper locFiltro = getFiltro();
		
	}

}
