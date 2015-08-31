/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMEstadoTramite;

import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.filtro.FiltroEstadoTramite;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminEstadoTramite extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminEstadoTramite";
	private static final String NOMBRE_PAGINA = "Administraci\363n Estado Trámite";

	private Label lblNombre = new Label();
	private TextField tfNombre = new TextField();
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

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	public Button getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(Button btnActivar) {
		this.btnActivar = btnActivar;
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
	public ObjectListDataProvider getObjectListDataProvider() {
		return dataProvider;
	}

	@Override
	protected List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaEstadosTramite();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaEstadosTramite(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfNombre().setText("");
		this.getDdEstado().setSelected("");
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(EstadoPlantilla.ACTIVO)));
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return getCommunicationExpedientesBean().getTablaEstadosTramite();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		EstadoTramite locEstadosTramite = (EstadoTramite) pObject;
		getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
		locEstadosTramite = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getEstadosTramitePorId(locEstadosTramite.getIdEstadoTramite());
		
		return pObject;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
		
		return this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().findListaEstadosTramite((FiltroEstadoTramite) pFiltro);
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroEstadoTramite locFiltro = this.getFiltro();
		locFiltro.setNombre(getTextFieldValue(getTfNombre()));
		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), EstadoPlantilla.class));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroEstadoTramite locFiltro = this.getFiltro();
		this.getTfNombre().setText(locFiltro.getNombre());
		if(locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		super.procesarObjetoABM(pObject);
	}

	public String btnAgregar_action() {
		return toAbm(new EstadoTramiteModel().new AgregarController());
	}

	public String btnModificar_action() {
		EstadoTramite estadoTramiteCatalogo = (EstadoTramite) getObjetoSeleccionado();
		if(estadoTramiteCatalogo != null) {
			if(estadoTramiteCatalogo.getEstado().equals(EstadoPlantilla.BAJA)) {
				warn("No se puede modificar un Estado Trámite en estado BAJA.");
				return null;
			}
		} else
			return "";
		
		return toAbm(new EstadoTramiteModel().new ModificarController());
	}

	public String btnEliminar_action() {
		RowKey rk = this.getSeleccionado();
		if(rk != null)
			return toAbm(new EstadoTramiteModel().new EliminarControler());
		else
			return "";
	}

	public String btnConsultar_action() {
		RowKey rk = this.getSeleccionado();
		if(rk != null)
			return toAbm(new EstadoTramiteModel().new ConsultarControler());
		else
			return "";
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
	public String getNombreBean() {
		return "#{expedientes$ABMEstadoTramite$AdminEstadoTramite}";
	}

	@Override
	public long getSerialVersionUID() {
		return EstadoTramite.serialVersionUID;
	}

	public String btnActivar_action() {
		EstadoTramite estadoTramiteCatalogo = (EstadoTramite) getObjetoSeleccionado();
		if(estadoTramiteCatalogo != null && estadoTramiteCatalogo.getEstado().equals(EstadoPlantilla.ACTIVO)) {
			warn("El Estado Trámite seleccionado ya se encuentra activo.");
			
			return null;
		}

		return toAbm(new EstadoTramiteModel().new RecuperarEstadoTramiteCatalogo());
	}

}