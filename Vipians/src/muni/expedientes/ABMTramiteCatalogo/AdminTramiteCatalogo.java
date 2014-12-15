
package muni.expedientes.ABMTramiteCatalogo;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminTramiteCatalogo extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminTramiteCatalogo";
	private static final String NOMBRE_PAGINA = "Administraci\363n de Tr\341mites de Cat\341logo";
	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();
	private Label lblEstado = new Label();
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private Button btnActivar = new Button();
	private ObjectListDataProvider dataProvider = new ObjectListDataProvider();
	
	@Override
	protected void _init() throws Exception {
		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(EstadoPlantilla.values(), "may");
		ddEstadoDefaultOptions.setOptions(opEstado);
		
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(EstadoPlantilla.ACTIVO)));
	}

	public Button getBtnActivar() {
		return btnActivar;
	}


	public void setBtnActivar(Button btnActivar) {
		this.btnActivar = btnActivar;
	}


	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(
			SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

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
		return getCommunicationExpedientesBean().getListaTramiteCatalogos();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaTramiteCatalogos(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfNombre().setValue(null);
		this.getDdEstado().setSelected("");
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(EstadoPlantilla.ACTIVO)));
	}

	@Override
	public PaginatedTable getPaginatedTable() {

		return getCommunicationExpedientesBean().getTablaTramiteCatalogo();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		TramiteCatalogo tramiteCatalogo = (TramiteCatalogo) pObject;
		getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
		tramiteCatalogo = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getTramiteCatalogoPorId(tramiteCatalogo.getIdTramiteCatalogo());
		return pObject;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {

		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().findListaTramiteCatalogos((FiltroTramiteCatalogo) pFiltro);

	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroTramiteCatalogo locFiltro = this.getFiltro();
		locFiltro.setNombre(getTextFieldValue(getTfNombre()));
		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), EstadoPlantilla.class));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroTramiteCatalogo locFiltro = this.getFiltro();
		getTfNombre().setText(locFiltro.getNombre());
		if (locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		}
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
		return toAbm(new TramiteCatalogoModel().new AgregarController());
	}

	public String btnModificar_action() {
		TramiteCatalogo tramiteCatalogo = (TramiteCatalogo) getObjetoSeleccionado();
		if (tramiteCatalogo.getEstado().equals(EstadoPlantilla.BAJA)) {
			warn("No se puede modificar un Trámite en estado BAJA.");
			return null;
		}
		return toAbm(new TramiteCatalogoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new TramiteCatalogoModel().new EliminarControler());
	}

	public String btnConsultar_action() {
		return toAbm(new TramiteCatalogoModel().new ConsultarControler());
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
		return "#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo}";
	}

	@Override
	public long getSerialVersionUID() {
		return TramiteCatalogo.serialVersionUID;
	}
	
	public String btnActivar_action() {
		TramiteCatalogo tramiteCatalogo = (TramiteCatalogo) getObjetoSeleccionado();
		if (tramiteCatalogo != null && tramiteCatalogo.getEstado().equals(EstadoPlantilla.ACTIVO)) {
			warn("El Trámite seleccionado ya se encuentra activo.");
			return null;
		}

		return toAbm(new TramiteCatalogoModel().new RecuperarTramiteCatalogo());
	}
}