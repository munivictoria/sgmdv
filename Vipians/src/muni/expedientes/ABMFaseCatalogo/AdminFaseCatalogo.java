
package muni.expedientes.ABMFaseCatalogo;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.filtro.FiltroFaseCatalogo;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminFaseCatalogo extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminFaseCatalogo";
	private static final String NOMBRE_PAGINA = "Administraci\363n de Fases de Catálogo";
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

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(
			SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	public Button getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(Button btnActivar) {
		this.btnActivar = btnActivar;
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
		return getCommunicationExpedientesBean().getListaFaseCatalogos();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaFaseCatalogos(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfNombre().setValue(null);
		this.getDdEstado().setSelected("");
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(EstadoPlantilla.ACTIVO)));
	}

	@Override
	public PaginatedTable getPaginatedTable() {

		return getCommunicationExpedientesBean().getTablaFaseCatalogo();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		FaseCatalogo locFaseCatalogo = (FaseCatalogo) pObject;
		getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
		locFaseCatalogo = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getFaseCatalogoPorId(locFaseCatalogo.getIdFaseCatalogo());
		return pObject;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {

		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().findListaFaseCatalogo((FiltroFaseCatalogo) pFiltro);

	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroFaseCatalogo locFiltro = this.getFiltro();
		locFiltro.setNombre(getTextFieldValue(getTfNombre()));
		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), EstadoPlantilla.class));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroFaseCatalogo locFiltro = this.getFiltro();
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
		return toAbm(new FaseCatalogoModel().new AgregarController());
	}

	public String btnModificar_action() {
		FaseCatalogo faseCatalogo = (FaseCatalogo) getObjetoSeleccionado();
		if (faseCatalogo.getEstado().equals(EstadoPlantilla.BAJA)) {
			warn("No se puede modificar una Fase en estado BAJA.");
			return null;
		}
		return toAbm(new FaseCatalogoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new FaseCatalogoModel().new EliminarControler());
	}

	public String btnConsultar_action() {
		return toAbm(new FaseCatalogoModel().new ConsultarControler());
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
		return "#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo}";
	}

	@Override
	public long getSerialVersionUID() {
		return FaseCatalogo.serialVersionUID;
	}
	
	public String btnActivar_action() {
		FaseCatalogo faseCatalogo = (FaseCatalogo) getObjetoSeleccionado();
		if (faseCatalogo != null && faseCatalogo.getEstado().equals(EstadoPlantilla.ACTIVO)) {
			warn("La Fase seleccionada ya se encuentra activa.");
			return null;
		}

		return toAbm(new FaseCatalogoModel().new RecuperarFaseCatalogo());
	}
}