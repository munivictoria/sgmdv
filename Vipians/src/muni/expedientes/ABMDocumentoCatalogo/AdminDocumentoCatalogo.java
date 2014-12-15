
package muni.expedientes.ABMDocumentoCatalogo;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.filtro.FiltroDocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminDocumentoCatalogo extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminDocumentoCatalogo";
	private static final String NOMBRE_PAGINA = "Administraci\363n de Documentos de Catálogo";
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

	public ObjectListDataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(ObjectListDataProvider dataProvider) {
		this.dataProvider = dataProvider;
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
		return getCommunicationExpedientesBean().getListaDocumentoCatalogos();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaDocumentoCatalogos(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfNombre().setValue(null);
		this.getDdEstado().setSelected("");
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(EstadoPlantilla.ACTIVO)));
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return getCommunicationExpedientesBean().getTablaDocumentoCatalogo();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) pObject;
		getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
		locDocumentoCatalogo = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getDocumentoCatalogoPorId(locDocumentoCatalogo.getIdDocumentoCatalogo());
		return pObject;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().findListaDocumentoCatalogo((FiltroDocumentoCatalogo) pFiltro);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroDocumentoCatalogo locFiltro = this.getFiltro();
		locFiltro.setNombre(getTextFieldValue(getTfNombre()));
		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), EstadoPlantilla.class));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroDocumentoCatalogo locFiltro = this.getFiltro();
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
		return toAbm(new DocumentoCatalogoModel().new AgregarController());
	}

	public String btnModificar_action() {
		DocumentoCatalogo documentoCatalogo = (DocumentoCatalogo) getObjetoSeleccionado();
		if (documentoCatalogo.getEstado().equals(EstadoPlantilla.BAJA)) {
			warn("No se puede modificar un Documento en estado BAJA.");
			return null;
		}
		return toAbm(new DocumentoCatalogoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new DocumentoCatalogoModel().new EliminarControler());
	}

	public String btnConsultar_action() {
		return toAbm(new DocumentoCatalogoModel().new ConsultarControler());
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
		return "#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo}";
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoCatalogo.serialVersionUID;
	}
	
	public String btnActivar_action() {
		DocumentoCatalogo documentoCatalogo = (DocumentoCatalogo) getObjetoSeleccionado();
		if (documentoCatalogo != null && documentoCatalogo.getEstado().equals(EstadoPlantilla.ACTIVO)) {
			warn("El Documento seleccionado ya se encuentra activo.");
			return null;
		}

		return toAbm(new DocumentoCatalogoModel().new RecuperarDocumentoCatalogo());
	}
}