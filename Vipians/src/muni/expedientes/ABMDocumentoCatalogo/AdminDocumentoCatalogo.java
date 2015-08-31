/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMDocumentoCatalogo;

import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
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

	private Label lblTipo = new Label();
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddTipoOptions = new SingleSelectOptionsList();

	private PanelGroup pgSeleccionTipo = new PanelGroup();
	private Label lblSeleccionTipo = new Label();
	private DropDown ddSeleccionTipo = new DropDown();
	private SingleSelectOptionsList ddSeleccionTipoOptions = new SingleSelectOptionsList();

	@Override
	protected void _init() throws Exception {
		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(EstadoPlantilla.values(), "may");
		ddEstadoDefaultOptions.setOptions(opEstado);

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(EstadoPlantilla.ACTIVO)));

		Option[] opTipo = null;
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(DocumentoCatalogo.Tipo.values(), "may");
		ddTipoOptions.setOptions(opTipo);

		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(DocumentoCatalogo.Tipo.values(), "may");
		ddSeleccionTipoOptions.setOptions(opTipo);
		this.getDdSeleccionTipo().setSelected(Util.getEnumNameFromString(String.valueOf(DocumentoCatalogo.Tipo.ENTRADA)));
	}

	public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown ddTipo) {
		this.ddTipo = ddTipo;
	}

	public SingleSelectOptionsList getDdTipoOptions() {
		return ddTipoOptions;
	}

	public void setDdTipoOptions(SingleSelectOptionsList ddTipoOptions) {
		this.ddTipoOptions = ddTipoOptions;
	}

	public PanelGroup getPgSeleccionTipo() {
		return pgSeleccionTipo;
	}

	public void setPgSeleccionTipo(PanelGroup pgSeleccionTipo) {
		this.pgSeleccionTipo = pgSeleccionTipo;
	}

	public Label getLblSeleccionTipo() {
		return lblSeleccionTipo;
	}

	public void setLblSeleccionTipo(Label lblSeleccionTipo) {
		this.lblSeleccionTipo = lblSeleccionTipo;
	}

	public DropDown getDdSeleccionTipo() {
		return ddSeleccionTipo;
	}

	public void setDdSeleccionTipo(DropDown ddSeleccionTipo) {
		this.ddSeleccionTipo = ddSeleccionTipo;
	}

	public SingleSelectOptionsList getDdSeleccionTipoOptions() {
		return ddSeleccionTipoOptions;
	}

	public void setDdSeleccionTipoOptions(SingleSelectOptionsList ddSeleccionTipoOptions) {
		this.ddSeleccionTipoOptions = ddSeleccionTipoOptions;
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

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
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
		locFiltro.setTipo(this.getDDEnumValue(this.getDdTipo(), DocumentoCatalogo.Tipo.class));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroDocumentoCatalogo locFiltro = this.getFiltro();
		getTfNombre().setText(locFiltro.getNombre());

		if(locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		}
		if(locFiltro.getTipo() != null) {
			this.getDdTipo().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipo())));
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
		DocumentoCatalogo.Tipo locTipoDocumento = this.getDDEnumValue(this.getDdSeleccionTipo(), DocumentoCatalogo.Tipo.class);
		this.getRequestBean1().setObjeto(locTipoDocumento);

		return toAbm(new DocumentoCatalogoModel().new AgregarController());
	}

	public String btnModificar_action() {
		DocumentoCatalogo documentoCatalogo = (DocumentoCatalogo) getObjetoSeleccionado();
		if(documentoCatalogo != null) {
			if(documentoCatalogo.getEstado().equals(EstadoPlantilla.BAJA)) {
				warn("No se puede modificar un Documento en estado BAJA.");
				return null;
			}
		} else {
			return "";
		}

		return toAbm(new DocumentoCatalogoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		RowKey rk = this.getSeleccionado();
		if(rk != null)
			return toAbm(new DocumentoCatalogoModel().new EliminarControler());
		else
			return "";
	}

	public String btnConsultar_action() {
		RowKey rk = this.getSeleccionado();
		if(rk != null)
			return toAbm(new DocumentoCatalogoModel().new ConsultarControler());
		else
			return "";
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
		return "#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo}";
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoCatalogo.serialVersionUID;
	}

	public String btnActivar_action() {
		DocumentoCatalogo documentoCatalogo = (DocumentoCatalogo) getObjetoSeleccionado();
		if(documentoCatalogo != null && documentoCatalogo.getEstado().equals(EstadoPlantilla.ACTIVO)) {
			warn("El Documento seleccionado ya se encuentra activo.");

			return null;
		}

		return toAbm(new DocumentoCatalogoModel().new RecuperarDocumentoCatalogo());
	}

}