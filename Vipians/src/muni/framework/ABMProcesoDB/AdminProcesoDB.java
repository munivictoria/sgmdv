package muni.framework.ABMProcesoDB;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.filtros.FiltroProcesoDB;
import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminProcesoDB extends AdminPageBean {
	
	private TextField tfNombre;
	private TextField tfNombreProceso;
	private Button btnEjecutar;
	private ObjectListDataProvider ldp = new ObjectListDataProvider();

	public Button getBtnEjecutar() {
		return btnEjecutar;
	}

	public void setBtnEjecutar(Button btnEjecutar) {
		this.btnEjecutar = btnEjecutar;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public TextField getTfNombreProceso() {
		return tfNombreProceso;
	}

	public void setTfNombreProceso(TextField tfNombreProceso) {
		this.tfNombreProceso = tfNombreProceso;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return ldp;
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationBean().getListaProcesosDB();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaProcesosDB(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		tfNombre.setText(null);
		tfNombreProceso.setText(null);
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		return getComunicationBean().getRemoteSystemParametro().findListaProcesosDB((FiltroProcesoDB) pFiltro);
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroProcesoDB locFiltro = this.getFiltro();
		locFiltro.setNombre(this.getTextFieldValue(tfNombre));
		locFiltro.setNombreProceso(this.getTextFieldValue(tfNombreProceso));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroProcesoDB locFiltro = this.getFiltro();
		if (locFiltro.getNombre() != null) this.tfNombre.setText(locFiltro.getNombre());
		if (locFiltro.getNombreProceso() != null) this.tfNombreProceso.setText(locFiltro.getNombreProceso());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Procesos DB";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminProcesoDB";
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMProcesoDB$AdminProcesoDB}";
	}

	@Override
	public long getSerialVersionUID() {
		return ProcesoDB.serialVersionUID;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return getComunicationBean().getTablaProcesoDB();
	}
	
	public String btnAgregar_action(){
		return null;
	}
	
	public String btnModificar_action(){
		return null;
	}
	
	public String btnEliminar_action() {
		return null;
	}
	
	public String btnConsultar_action(){
		return null;
	}
	
	public String btnEjecutar_action() {
		return toAbm(new ProcesoDBModel().new EjecutarController());
	}

}
