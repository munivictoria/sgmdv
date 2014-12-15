
package muni.expedientes.ABMExpediente;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import muni.expedientes.NodoExpedienteControler;
import muni.expedientes.panels.PanelListaTrabajo;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Tab;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.recurso.filtro.FiltroExpediente;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminExpediente extends AdminPageBean {

	private static final String CASO_NAVEGACION = "AdminExpediente";
	private static final String NOMBRE_PAGINA = "Administraci\363n de Expedientes";

	private TabSet tabSetAdmin = new TabSet();
	private Tab tabBusquedaBasica = new Tab();
	private Tab tabListaTrabajo = new Tab();

	private TextField tfAsunto = new TextField();
	private Label lblAsunto = new Label();
	private TextField tfNroRegistro = new TextField();
	private Label lblNroRegistro = new Label();
	private Label lblFechaRegistro = new Label();
	private TextField tfFechaRegistroDesde = new TextField();
	private TextField tfFechaRegistroHasta = new TextField();

	private PanelListaTrabajo panelListaTrabajo = new PanelListaTrabajo();

	private Label labelProcedimiento = new Label();
	private HtmlAjaxCommandButton btnLimpiarProcedimiento = new HtmlAjaxCommandButton();
	private Button btnSeleccionarProcedimiento = new Button();
	private SingleSelectOptionsList ddProcedimientoOptions = new SingleSelectOptionsList();
	private DropDown ddProcedimiento = new DropDown();
	private Button btnImprimir = new Button();

	private ObjectListDataProvider dataProvider = new ObjectListDataProvider();
	private String selectedTab = "tabBusquedaBasica";

	public Button getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(Button btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public TabSet getTabSetAdmin() {
		return tabSetAdmin;
	}

	public void setTabSetAdmin(TabSet tabSetAdmin) {
		this.tabSetAdmin = tabSetAdmin;
	}

	public Tab getTabBusquedaBasica() {
		return tabBusquedaBasica;
	}

	public void setTabBusquedaBasica(Tab tabBusquedaBasica) {
		this.tabBusquedaBasica = tabBusquedaBasica;
	}

	public Tab getTabListaTrabajo() {
		return tabListaTrabajo;
	}

	public void setTabListaTrabajo(Tab tabListaTrabajo) {
		this.tabListaTrabajo = tabListaTrabajo;
	}

	public TextField getTfAsunto() {
		return tfAsunto;
	}

	public void setTfAsunto(TextField tfAsunto) {
		this.tfAsunto = tfAsunto;
	}

	public Label getLblAsunto() {
		return lblAsunto;
	}

	public void setLblAsunto(Label lblAsunto) {
		this.lblAsunto = lblAsunto;
	}

	public TextField getTfNroRegistro() {
		return tfNroRegistro;
	}

	public void setTfNroRegistro(TextField tfNroRegistro) {
		this.tfNroRegistro = tfNroRegistro;
	}

	public Label getLblNroRegistro() {
		return lblNroRegistro;
	}

	public void setLblNroRegistro(Label lblNroRegistro) {
		this.lblNroRegistro = lblNroRegistro;
	}

	public Label getLblFechaRegistro() {
		return lblFechaRegistro;
	}

	public void setLblFechaRegistro(Label lblFechaRegistro) {
		this.lblFechaRegistro = lblFechaRegistro;
	}

	public TextField getTfFechaRegistroDesde() {
		return tfFechaRegistroDesde;
	}

	public void setTfFechaRegistroDesde(TextField tfFechaRegistroDesde) {
		this.tfFechaRegistroDesde = tfFechaRegistroDesde;
	}

	public TextField getTfFechaRegistroHasta() {
		return tfFechaRegistroHasta;
	}

	public void setTfFechaRegistroHasta(TextField tfFechaRegistroHasta) {
		this.tfFechaRegistroHasta = tfFechaRegistroHasta;
	}

	public PanelListaTrabajo getPanelListaTrabajo() {
		return panelListaTrabajo;
	}

	public void setPanelListaTrabajo(PanelListaTrabajo panelListaTrabajo) {
		this.panelListaTrabajo = panelListaTrabajo;
	}

	public Label getLabelProcedimiento() {
		return labelProcedimiento;
	}

	public void setLabelProcedimiento(Label labelProcedimiento) {
		this.labelProcedimiento = labelProcedimiento;
	}

	public HtmlAjaxCommandButton getBtnLimpiarProcedimiento() {
		return btnLimpiarProcedimiento;
	}

	public void setBtnLimpiarProcedimiento(HtmlAjaxCommandButton btnLimpiarProcedimiento) {
		this.btnLimpiarProcedimiento = btnLimpiarProcedimiento;
	}

	public Button getBtnSeleccionarProcedimiento() {
		return btnSeleccionarProcedimiento;
	}

	public void setBtnSeleccionarProcedimiento(Button btnSeleccionarProcedimiento) {
		this.btnSeleccionarProcedimiento = btnSeleccionarProcedimiento;
	}

	public SingleSelectOptionsList getDdProcedimientoOptions() {
		return ddProcedimientoOptions;
	}

	public void setDdProcedimientoOptions(SingleSelectOptionsList ddProcedimientoOptions) {
		this.ddProcedimientoOptions = ddProcedimientoOptions;
	}

	public DropDown getDdProcedimiento() {
		return ddProcedimiento;
	}

	public void setDdProcedimiento(DropDown ddProcedimiento) {
		this.ddProcedimiento = ddProcedimiento;
	}

	@Override
	protected void _init() throws Exception {
		Set<String> locListaProcedimientos = this.getCommunicationExpedientesBean().getMapaProcedimiento().keySet();
		Option[] opProcedimientos = new Option[locListaProcedimientos.size() + 1];
		int i = 0;
		opProcedimientos[i++] = new Option("", "");
		for(String cadaProcedimiento : locListaProcedimientos) {
			opProcedimientos[i++] = new Option(cadaProcedimiento, cadaProcedimiento);
		}

		ddProcedimientoOptions.setOptions(opProcedimientos);

		try {
			panelListaTrabajo._init();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return dataProvider;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getListaDelCommunication() {
		return getCommunicationExpedientesBean().getListaExpedientes();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationExpedientesBean().setListaExpedientes(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfAsunto().setValue(null);
		this.getTfFechaRegistroHasta().setValue(null);
		this.getTfNroRegistro().setValue(null);
		this.getTfPersonaSeleccionada().setValue(null);
		this.getDdProcedimiento().setSelected(null);

		this.getSessionBean1().setPersonaSeleccionada(null);

		FiltroExpediente locFiltro = this.getFiltro();

		locFiltro.setAsunto(null);
		locFiltro.setNroRegistro(null);
		locFiltro.setFechaRegistroHasta(null);
		locFiltro.setProcedimiento(null);
		locFiltro.setInteresado(null);
		locFiltro.setFechaRegistroDesde(locFiltro.getPrimerDiaAnioActual());
		this.getTfFechaRegistroDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaRegistroDesde()));
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return getCommunicationExpedientesBean().getTablaExpediente();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Expediente locExpediente = (Expediente) pObject;
		getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
		locExpediente = getCommunicationExpedientesBean().getRemoteSystemExpedientes().getExpedientePorId(locExpediente.getIdNodoExpediente());
		return locExpediente;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationExpedientesBean().getRemoteSystemExpedientes().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationExpedientesBean().getRemoteSystemExpedientes().findListaExpediente((FiltroExpediente) pFiltro);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroExpediente locFiltro = this.getFiltro();
		locFiltro.setAsunto(getTextFieldValue(getTfAsunto()));

		borrarListIdAuxPersonas(this.getTfPersonaSeleccionada(), locFiltro.getInteresado());
		locFiltro.setListaIdInteresados(this.getSessionBean1().getListaIdPersonas());

		Long nro = null;
		if(getTfNroRegistro().getValue() != null) {
			nro = Long.parseLong(getTfNroRegistro().getValue().toString());
		}
		locFiltro.setNroRegistro(nro);
		locFiltro.setFechaRegistroDesde(getTextFieldValueDate(tfFechaRegistroDesde));
		locFiltro.setFechaRegistroHasta(getTextFieldValueDate(tfFechaRegistroHasta));
		locFiltro.setProcedimiento(getDDObjectValue(getDdProcedimiento(), getCommunicationExpedientesBean().getMapaProcedimiento()));

		panelListaTrabajo.guardarEstado();
		selectedTab = tabSetAdmin.getSelected();
		setElementosPila();
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		getElementosPila();
		FiltroExpediente locFiltro = this.getFiltro();
		getTfAsunto().setText(locFiltro.getAsunto());
		getTfNroRegistro().setText(locFiltro.getNroRegistro());
		getTfFechaRegistroDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaRegistroDesde()));
		getTfFechaRegistroHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaRegistroHasta()));
		getTfPersonaSeleccionada().setText(locFiltro.getInteresado());
		if(locFiltro.getProcedimiento() != null) {
			getDdProcedimiento().setSelected(locFiltro.getProcedimiento().getNombre());
		}
		panelListaTrabajo.mostrarEstado();
		getTabSetAdmin().setSelected(selectedTab);
	}

	@Override
	protected String getNombrePagina() {
		return NOMBRE_PAGINA;
	}

	@Override
	protected String getCasoNavegacion() {
		return CASO_NAVEGACION;
	}

	public void getElementosPila() {
		int ind = 0;
		selectedTab = obtenerObjetoDelElementoPila(ind++, String.class);

	}

	@SuppressWarnings("unchecked")
	protected void setElementosPila() {
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, selectedTab);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroExpediente locFiltro = getFiltro();
		locFiltro.setFechaRegistroDesde(locFiltro.getPrimerDiaAnioActual());
		int ind = 0;
		ep.getObjetos().add(ind++, selectedTab);
		return ep;
	}

	public String btnAgregar_action() {
		return toAbm(new ExpedienteModel().new AgregarController());
	}

	public String btnModificar_action() {
		Expediente expendiente = (Expediente) this.getObjetoSeleccionado();
		if (expendiente != null && expendiente.getEstado().toString().equals("CERRADO")) {
			warn("No se pueden Modificar los Expedientes en estado Cerrado.");
			return null;
		}
		return toAbm(new ExpedienteModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new ExpedienteModel().new EliminarControler());
	}

	public String btnConsultar_action() {
		return toAbm(new ExpedienteModel().new ConsultarControler());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

		FiltroExpediente locFiltro = getFiltro();
		if(pObject instanceof Persona) {
			if(pObject != null) {
				Persona locInteresado = (Persona) pObject;
				locFiltro.setInteresado(locInteresado);
				this.getSessionBean1().setPersonaSeleccionada(locInteresado);
			}
		}
		if(pObject instanceof Procedimiento) {
			if(pObject != null) {
				Procedimiento locProcedimiento = (Procedimiento) pObject;
				locFiltro.setProcedimiento(locProcedimiento);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		super.procesarObjetoABM(pObject);
	}

	public String btnSeleccionarProcedimiento_action() {
		return navegarParaSeleccionar("AdminProcedimiento");
	}

	public String btnLimpiarProcedimiento_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getDdProcedimiento().setSelected(null);

			FiltroExpediente locFiltro = getFiltro();
			locFiltro.setAsunto(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getTfPersonaSeleccionada().setText("");
			this.getSessionBean1().setPersonaSeleccionada(null);
			FiltroExpediente locFiltro = getFiltro();
			locFiltro.setInteresado(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void seleccionarOpcionFases(ActionEvent event) {
		panelListaTrabajo.actualizarSegunFasesSeleccionadas();
	}

	public void seleccionarOpcionProcedimientos(ActionEvent event) {
		panelListaTrabajo.actualizarSegunProcedimientosSeleccionados();
	}

	public void seleccionarOpcionTramites(ActionEvent event) {
		panelListaTrabajo.actualizarSegunTramitesSeleccionados();
	}

	public void seleccionarOpcionVencidos(ActionEvent event) {
		panelListaTrabajo.actualizarSegunVencidos();
	}

	// TODO
	public String btnModificarListaTrabajo_action() {
		return toAbmFromListaTrabajo(new ExpedienteModel().new ModificarController());
	}

	public String btnConsultarListaTrabajo_action() {
		return toAbmFromListaTrabajo(new ExpedienteModel().new ConsultarControler());
	}

	protected String toAbmFromListaTrabajo(NodoExpedienteControler pController) {
		getElementosPila();
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			RowKey rk = null;
			if(pController.seleccionarObjeto()) {
				try {
					rk = this.panelListaTrabajo.getTableExpedientes().getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());

						Expediente expediente = (Expediente) this.panelListaTrabajo.getTableExpedientes().getObjectListDataProvider().getObjects()[index];
						try {
							Long id = expediente.getIdNodoExpediente();
							if(id != null && id > 0) {
								this.getCommunicationExpedientesBean().getRemoteSystemExpedientes().setLlave(this.getSessionBean1().getLlave());
								expediente = this.getCommunicationExpedientesBean().getRemoteSystemExpedientes().getExpedientePorId(id);
							}
							if(!pController.getValidacion(expediente)) {
								error(pController.getMessage());
								return null;
							}

						} catch(Exception ex) {
							error("No se pudo recuperar Expediente: " + ex.getMessage());
						}

						this.getRequestBean1().setObjetoABM(expediente);

					}

				} catch(Exception ex) {
					log(getCasoNavegacion() + "toAbmListaTrabajoException:", ex);
					error(getNombrePagina() + " - toAbmFromListaTrabajo: " + ex.getMessage());
				}
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			if(!pController.seleccionarObjeto() || (pController.seleccionarObjeto() && rk != null)) {
				this.getRequestBean1().setAbmController(pController);
				retorno = pController.getModel().getReglaNavegacion();
			} else {
				error("Debe seleccionar un Expediente");
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		setElementosPila();
		return retorno;
	}

	public String btnImprimir_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			// APLICAR LOGICA AQUI...
			// ariel - no guardar. utilizar lo ya guardado (con resultado de la busqueda)
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			Expediente locExpediente = null;

			try {

				RowKey rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					locExpediente = ((Expediente) obj);

					this.guardarOrdenamiento();
					Long pos = this.getPosicionEnTabla(rk);
					this.getElementoPila().setPosicionGlobal(pos.longValue());
				}

				this.getCommunicationExpedientesBean().getRemoteSystemExpedientes().setLlave(this.getSessionBean1().getLlave());
				Usuario usuario = this.getSessionBean1().getUsuario();
				JasperPrint jp = this.getCommunicationExpedientesBean().getRemoteSystemExpedientes().getReporteAltasExpedientes(locExpediente, usuario);

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Alta_Expediente");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
			} catch(Exception ex) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
			}

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMExpediente$AdminExpediente}";
	}

	@Override
	public long getSerialVersionUID() {
		return Expediente.serialVersionUID;
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroExpediente locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona interesado = null;

		try {
			interesado = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		locFiltro.setInteresado(interesado);
	}
}