/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMProcedimiento;

import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.filtro.FiltroProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminProcedimiento extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminProcedimiento";
	private static final String NOMBRE_PAGINA = "Administraci\363n de Procedimientos";
	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();
	private ObjectListDataProvider dataProvider = new ObjectListDataProvider();

	private Label lblEstado = new Label();
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	@Override
	protected void _init() throws Exception {
		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(EstadoPlantilla.values(), "may");
		ddEstadoDefaultOptions.setOptions(opEstado);

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(EstadoPlantilla.ACTIVO)));
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
		this.getDdEstado().setSelected("");
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(EstadoPlantilla.ACTIVO)));
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
		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), EstadoPlantilla.class));
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
		Procedimiento procedimiento = (Procedimiento) getObjetoSeleccionado();
		if(procedimiento != null) {
			if(procedimiento.getEstado().equals(EstadoPlantilla.BAJA)) {
				warn("No se puede modificar un Prodecimiento en estado BAJA.");
				
				return null;
			}
		} else
			return "";

		return toAbm(new ProcedimientoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		RowKey rk = this.getSeleccionado();
		if(rk != null)
			return toAbm(new ProcedimientoModel().new EliminarControler());
		else
			return "";
	}

	public String btnConsultar_action() {
		RowKey rk = this.getSeleccionado();
		if(rk != null)
			return toAbm(new ProcedimientoModel().new ConsultarControler());
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
		return "#{expedientes$ABMProcedimiento$AdminProcedimiento}";
	}

	@Override
	public long getSerialVersionUID() {
		return Procedimiento.serialVersionUID;
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
	
}