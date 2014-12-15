package muni.compras.ABMAutorizacionSolicitudSuministro;

import java.util.List;
import java.util.Set;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.filtros.FiltroAutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.AutorizacionSolicitudSuministro;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/*
 * pPage bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.p
 */
public class AdminAutorizacionSolicitudSuministro extends AdminPageBean {
	
	@Override
	protected void _init(){
		Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasSolSum().keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		int i = 0;
		opAreas[i++] = new Option("", "");
		for(String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}

	private ObjectListDataProvider ldpUsuarioAutorizadorSolicitudSuministro = new ObjectListDataProvider();

	private StaticText stFiltrarPor = new StaticText();
	private Label lblArea = new Label();
	private DropDown ddArea = new DropDown();
	private SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();

	public Label getLblArea() {
		return lblArea;
	}

	public void setLblArea(Label lblArea) {
		this.lblArea = lblArea;
	}

	public DropDown getDdArea() {
		return ddArea;
	}

	public void setDdArea(DropDown ddArea) {
		this.ddArea = ddArea;
	}

	public SingleSelectOptionsList getDdAreaOptions() {
		return ddAreaOptions;
	}

	public void setDdAreaOptions(SingleSelectOptionsList ddAreaOptions) {
		this.ddAreaOptions = ddAreaOptions;
	}

	public StaticText getStFiltrarPor() {
		return stFiltrarPor;
	}

	public void setStFiltrarPor(StaticText stFiltrarPor) {
		this.stFiltrarPor = stFiltrarPor;
	}

	public ObjectListDataProvider getLdpUsuarioAutorizadorSolicitudSuministro() {
		return ldpUsuarioAutorizadorSolicitudSuministro;
	}

	public void setLdpUsuarioAutorizadorSolicitudSuministro(ObjectListDataProvider ldpUsuarioAutorizadorSolicitudSuministro) {
		this.ldpUsuarioAutorizadorSolicitudSuministro = ldpUsuarioAutorizadorSolicitudSuministro;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	public String btnModificar_action() {
		return toAbm(new AutorizacionSolicitudSuministroModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new AutorizacionSolicitudSuministroModel().new EliminarController());
	}

	public String btnAgregar_action() {
		return toAbm(new AutorizacionSolicitudSuministroModel().new AgregarController());
	}

	public String btnConsultar_action() {
		return toAbm(new AutorizacionSolicitudSuministroModel().new ConsultarController());
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroAutorizacionSolicitudSuministro locFiltro = this.getFiltro();
		
		locFiltro.setArea(this.getDDObjectValue(getDdArea(), getCommunicationComprasBean().getMapaAreas()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroAutorizacionSolicitudSuministro locFiltro = this.getFiltro();
		
		if(locFiltro.getArea() != null) {
			this.getDdArea().setSelected(locFiltro.getArea().toString());
		}
	}

//	@Override
//	protected boolean validarBuscar() {
//		Validador v = new Validador();
//		UIComponent[] enteros = new UIComponent[1];
//		String[] nomEnteros = new String[1];
//		int pos = 0;
//		enteros[pos] = getTfAnio();
//		nomEnteros[pos++] = "A\361o";
//		v.esNumero(enteros, nomEnteros);
//		if (v.getErrores().size() > 0) {
//			error("Existen Errores:");
//			for (int i = 0; i < v.getErrores().size(); i++) {
//				warn(v.getErrores().toArray()[i].toString());
//			}
//			return false;
//		}
//		return true;
//	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro()
				.findListaAutorizacionSolicitudSuministro((FiltroAutorizacionSolicitudSuministro) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
//		this.tfAnio.setText("");
		FiltroAutorizacionSolicitudSuministro locFiltro = this.getFiltro();
		locFiltro.setArea(null);
		this.getDdArea().setSelected(null);
		this.getTablaBusquedaLogs().getCkbBuscarPorLogs().setSelected(false);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpUsuarioAutorizadorSolicitudSuministro();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaAutorizaciones();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaAutorizaciones(lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaAutorizacionSolicitudSuministro();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		AutorizacionSolicitudSuministro locAutorizacionSolicitudSuministro = (AutorizacionSolicitudSuministro) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
		this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro()
				.getAutorizacionSolicitudSuministroByID(locAutorizacionSolicitudSuministro.getIdAutorizacionSolicitudSuministro());
		return locAutorizacionSolicitudSuministro;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Autorizaciones de Solicitud de Suministro";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminAutorizacionSolicitudSuministro";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return AutorizacionSolicitudSuministro.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro}";
	}
}