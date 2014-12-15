/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMEstadoSolicitudSuministro;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.filtros.FiltroEstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author danilo
 */
public class AdminEstadoSolicitudSuministro extends AdminPageBean {

	StaticText stFiltrarPor = new StaticText();
	Label lblNombre = new Label();
	TextField tfNombre = new TextField();
	private ObjectListDataProvider ldpEstadoSolicitud = new ObjectListDataProvider();

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public StaticText getStFiltrarPor() {
		return stFiltrarPor;
	}

	public void setStFiltrarPor(StaticText stFiltrarPor) {
		this.stFiltrarPor = stFiltrarPor;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public ObjectListDataProvider getLdpEstadoSolicitud() {
		return ldpEstadoSolicitud;
	}

	public void setLdpEstadoSolicitud(ObjectListDataProvider ldpEstadoSolicitud) {
		this.ldpEstadoSolicitud = ldpEstadoSolicitud;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpEstadoSolicitud();
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroEstadoSolicitudSuministro locFiltro = this.getFiltro();
		
		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroEstadoSolicitudSuministro locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro()
				.findListaEstadoSolSum((FiltroEstadoSolicitudSuministro) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroEstadoSolicitudSuministro locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		
		this.tfNombre.setText("");
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaEstadoSolicitud();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaEstadoSolicitud(lista);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaEstadoSolicitudSuministro();
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		EstadoSolicitudSuministro locEstadoSolicitudSuministro = (EstadoSolicitudSuministro) pObject;
		getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
		locEstadoSolicitudSuministro = getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().getEstadoSolicitudSuministroByID(locEstadoSolicitudSuministro.getIdEstadoSolicitudSuministro());
		return locEstadoSolicitudSuministro;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Estados de Solicitudes de Suministro";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminEstadoSolicitudSuministro";
	}

	public String btnAgregar_action() {
		return toAbm(new EstadoSolicitudSuministroModel().new AgregarController());

	}

	public String btnModificar_action() {
		return toAbm(new EstadoSolicitudSuministroModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new EstadoSolicitudSuministroModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new EstadoSolicitudSuministroModel().new ConsultarController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return EstadoSolicitudSuministro.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro}";
	}
}