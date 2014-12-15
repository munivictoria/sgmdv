package muni.compras.ABMUsuarioFirmante;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.filtros.FiltroUsuarioFirmante;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizador;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorOrdenCompra;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminUsuarioFirmante extends AdminPageBean {

	private ObjectListDataProvider ldpUsuarioFirmante = new ObjectListDataProvider();
	
	public ObjectListDataProvider getLdpUsuarioFirmante() {
		return ldpUsuarioFirmante;
	}

	public void setLdpUsuarioFirmante(ObjectListDataProvider ldpUsuarioFirmante) {
		this.ldpUsuarioFirmante = ldpUsuarioFirmante;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	public String btnModificar_action() {
		return toAbm(new UsuarioFirmanteModel().new ModificarUsuarioFirmanteController());
	}

	public String btnEliminar_action() {
		return toAbm(new UsuarioFirmanteModel().new EliminarUsuarioFirmanteController());
	}

	public String btnAgregar_action() {
		return toAbm(new UsuarioFirmanteModel().new AgregarUsuarioFirmanteController());
	}

	public String btnConsultar_action() {
		return toAbm(new UsuarioFirmanteModel().new ConsultarUsuarioFirmanteController());
	}

	@Override
	protected void guardarEstadoObjetosUsados() {

	}

	@Override
	protected void mostrarEstadoObjetosUsados() {

	}
	
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra()
				.findListaUsuariosFirmantes((FiltroUsuarioFirmante) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpUsuarioFirmante();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaUsuariosFirmantes();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaUsuariosFirmantes(lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaUsuarioFirmante();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		UsuarioAutorizador locUsuarioFirmante = (UsuarioAutorizador) pObject;
		getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
		locUsuarioFirmante = getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().getUsuarioFirmantePorId(locUsuarioFirmante.getIdUsuarioAutorizador());
		return locUsuarioFirmante;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Usuarios Firmante";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminUsuarioFirmante";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return UsuarioAutorizadorOrdenCompra.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante}";
	}
}