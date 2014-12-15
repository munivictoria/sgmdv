
package muni.expedientes.ABMProcedimiento;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.filtro.FiltroProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminProcedimiento extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminProcedimiento";
	private static final String NOMBRE_PAGINA = "Administraci\363n de Procedimientos";
	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();
	private ObjectListDataProvider dataProvider = new ObjectListDataProvider();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return dataProvider;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaProcedimientos();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaProcedimientos(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfNombre().setValue(null);

	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return getCommunicationExpedientesBean().getTablaProcedimiento();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Procedimiento locProcedimiento = (Procedimiento) pObject;
		getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
		locProcedimiento = getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getProcedimientoPorId(locProcedimiento.getIdNodoProcedimiento());
		return locProcedimiento;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().findListaProcedimiento((FiltroProcedimiento) pFiltro);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroProcedimiento locFiltro = this.getFiltro();
		locFiltro.setNombre(getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroProcedimiento locFiltro = this.getFiltro();
		getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected String getNombrePagina() {
		return NOMBRE_PAGINA;
	}

	@Override
	protected String getCasoNavegacion() {
		return CASO_NAVEGACION;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	public String btnAgregar_action() {
		return toAbm(new ProcedimientoModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new ProcedimientoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new ProcedimientoModel().new EliminarControler());
	}

	public String btnConsultar_action() {
		return toAbm(new ProcedimientoModel().new ConsultarControler());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		super.procesarObjetoABM(pObject);
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMProcedimiento$AdminProcedimiento}";
	}

	@Override
	public long getSerialVersionUID() {
		return Procedimiento.serialVersionUID;
	}
}