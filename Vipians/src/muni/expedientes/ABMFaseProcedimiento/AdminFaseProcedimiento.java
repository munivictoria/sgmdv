
package muni.expedientes.ABMFaseProcedimiento;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.filtro.FiltroFaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminFaseProcedimiento extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminFaseProcedimiento";
	private static final String NOMBRE_PAGINA = "Administraci\363n de Fases de Procedimiento";
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
		return getCommunicationExpedientesBean().getListaFaseProcedimientos();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaFaseProcedimientos(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfNombre().setValue(null);

	}

	@Override
	public PaginatedTable getPaginatedTable() {

		return getCommunicationExpedientesBean().getTablaFaseProcedimiento();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		FaseProcedimiento locFaseProcedimiento = (FaseProcedimiento) pObject;
		getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
		locFaseProcedimiento = getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getFaseProcedimientoPorId(locFaseProcedimiento.getIdNodoProcedimiento());
		return pObject;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {

		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().findListaFaseProcedimiento((FiltroFaseProcedimiento) pFiltro);

	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroFaseProcedimiento locFiltro = this.getFiltro();
		locFiltro.setNombre(getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroFaseProcedimiento locFiltro = this.getFiltro();
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
		return toAbm(new FaseProcedimientoModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new FaseProcedimientoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return null;
	}

	public String btnConsultar_action() {
		return null;
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
		return "#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento}";
	}

	@Override
	public long getSerialVersionUID() {
		return FaseProcedimiento.serialVersionUID;
	}
}