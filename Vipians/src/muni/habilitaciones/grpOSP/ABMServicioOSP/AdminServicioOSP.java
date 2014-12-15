/*
 * AdminServicioOSP.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpOSP.ABMServicioOSP;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminServicioOSP extends AdminPageBean {

	private Label lblCodigo = new Label();
	private Label lblNombre = new Label();
	private TextField tfCodigo = new TextField();
	private TextField tfNombre = new TextField();

	public Label getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(Label lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
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

	private ObjectListDataProvider ldpServicioOSP = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpServicioOSP() {
		return ldpServicioOSP;
	}

	public void setLdpServicioOSP(ObjectListDataProvider oldp) {
		this.ldpServicioOSP = oldp;
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public AdminServicioOSP() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroServicioOSP locFiltro = getFiltro();
		List atributosDinamicos = null;

		try {
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(ServicioOSP.serialVersionUID, null, true);
		} catch(Exception e) {

		}
		locFiltro.setListaAtributosDinamicos(atributosDinamicos);

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroServicioOSP filtroServicioOSP = this.getFiltro();

		filtroServicioOSP.setCodigo(this.getTextFieldValue(this.getTfCodigo()));
		filtroServicioOSP.setNombre(this.getTextFieldValue(this.getTfNombre()));

		if(filtroServicioOSP.getListaAtributosDinamicos() != null) {
			filtroServicioOSP.setListaAtributosDinamicos(panelAtributoDinamico.obtenerListaAtributosDinamicos(filtroServicioOSP.getListaAtributosDinamicos()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroServicioOSP filtroServicioOSP = this.getFiltro();

		this.getTfNombre().setText(filtroServicioOSP.getNombre());
		this.getTfCodigo().setText(filtroServicioOSP.getCodigo());

		panelAtributoDinamico = new PanelAtributoDinamico(filtroServicioOSP.getListaAtributosDinamicos(), "#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(filtroServicioOSP.getListaAtributosDinamicos());
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroServicioOSP filtroServicioOSP = this.getFiltro();
		filtroServicioOSP.setCodigo(null);
		filtroServicioOSP.setNombre(null);

		panelAtributoDinamico.limpiarCampos();
		this.getTfCodigo().setText("");
		this.getTfNombre().setText("");
		this.setListaDelCommunication(new ArrayList());
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpServicioOSP();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaServiciosOSP();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaServiciosOSP(lista);
	}

	public String btnAgregar_action() {
		return toAbm(new ServicioOSPModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new ServicioOSPModel().new ModificarController());
	}

	public String btnConsultar_action() {
		return toAbm(new ServicioOSPModel().new ConsultarController());
	}

	public String btnEliminar_action() {
		return toAbm(new ServicioOSPModel().new EliminarController());
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaServiciosOSP();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ServicioOSP locServicio = (ServicioOSP) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
		locServicio = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().getServicioOSPPorId(locServicio.getIdTipoAlicuota());
		return locServicio;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().findListaServiciosOSP((FiltroServicioOSP) pFiltro);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getNombrePagina() {
		return "Servicios OSP";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminServicioOSP";
	}
	
	@Override
	public long getSerialVersionUID() {
		return ServicioOSP.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP}";
	}
}