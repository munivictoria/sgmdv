/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMTipoBien;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.filtros.FiltroTipoBien;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author danilo
 */
public class AdminTipoBien extends AdminPageBean {

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
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpEstadoSolicitud();
	}

	@Override
	protected void _init() throws Exception {

	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroTipoBien locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroTipoBien locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findListaTipoBien((FiltroTipoBien) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroTipoBien locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		
		this.tfNombre.setText("");
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaTipoBienes();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaTipoBienes(lista);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaTipoBien();
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		TipoBien locTipoBien = (TipoBien) pObject;
		getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
		locTipoBien = getCommunicationComprasBean().getRemoteSystemAdministracionBienes().getTipoBienByID(locTipoBien.getIdTipoBien());
		return locTipoBien;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Categor\355a Bien";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminTipoBien";
	}

	public String btnAgregar_action() {
		return toAbm(new TipoBienModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new TipoBienModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new TipoBienModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new TipoBienModel().new ConsultarController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return TipoBien.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMTipoBien$AdminTipoBien}";
	}
}