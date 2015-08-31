/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMTramiteProcedimiento;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteProcedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminTramiteProcedimiento extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminTramiteProcedimiento";
	private static final String NOMBRE_PAGINA = "Administraci\363n de Tramites de Procedimiento";
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
		return getCommunicationExpedientesBean().getListaTramiteProcedimientos();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaTramiteProcedimientos(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfNombre().setValue(null);

	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return getCommunicationExpedientesBean().getTablaTramiteProcedimiento();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		TramiteProcedimiento locTramiteProcedimiento = (TramiteProcedimiento) pObject;
		getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
		locTramiteProcedimiento = getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getTramiteProcedimientoPorId(locTramiteProcedimiento.getIdNodoProcedimiento());
		
		return pObject;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().findListaTramiteProcedimiento((FiltroTramiteProcedimiento) pFiltro);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroTramiteProcedimiento locFiltro = this.getFiltro();
		locFiltro.setNombre(getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroTramiteProcedimiento locFiltro = this.getFiltro();
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
		return toAbm(new TramiteProcedimientoModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new TramiteProcedimientoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return null;
	}

	public String btnConsultar_action() {
		return null;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		super.procesarObjetoABM(pObject);
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento}";
	}

	@Override
	public long getSerialVersionUID() {
		return TramiteProcedimiento.serialVersionUID;
	}
	
}