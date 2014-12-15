
package muni.habilitaciones.grpTipoParametro.ABMTipoParametroGrilla;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrilla;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminTipoParametroGrilla extends AdminPageBean {

	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();
	private ObjectListDataProvider ldpTipoParametroGrilla = new ObjectListDataProvider();

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
		// TODO Auto-generated method stub
		return this.getLdpTipoParametroGrilla();
	}

	@Override
	protected List getListaDelCommunication() {
		// TODO Auto-generated method stub
		return this.getCommunicationHabilitacionesBean().getListaTipoParametroGrilla();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// TODO Auto-generated method stub
		this.getCommunicationHabilitacionesBean().setListaTipoParametroGrilla(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		// TODO Auto-generated method stub
		FiltroTipoParametroGrilla locFiltro = this.getFiltro();
		if(locFiltro != null)
			locFiltro.setNombre(null);
		this.getTfNombre().setText(null);
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		// TODO Auto-generated method stub
		TipoParametroGrilla locTipoParametroGrilla = (TipoParametroGrilla) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getTipoParametroGrillaPorId(locTipoParametroGrilla.getIdTipoParametro());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		// TODO Auto-generated method stub
		this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTipoParametroGrilla((FiltroTipoParametroGrilla) pFiltro);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// TODO Auto-generated method stub
		FiltroTipoParametroGrilla locFiltro = this.getFiltro();
		locFiltro.setNombre(this.getTextFieldValue(this.getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// TODO Auto-generated method stub
		FiltroTipoParametroGrilla locFiltro = this.getFiltro();
		if(locFiltro != null) {
			if(locFiltro.getNombre() != null) {
				this.getTfNombre().setText(locFiltro.getNombre());
			}
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
		FiltroTipoParametroGrilla locFiltro = this.getFiltro();
	}

	@Override
	protected String getNombrePagina() {
		// TODO Auto-generated method stub
		return "Par\341metro de Grilla";
	}

	@Override
	protected String getCasoNavegacion() {
		// TODO Auto-generated method stub
		return "AdminTipoParametroGrilla";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		// TODO Auto-generated method stub
		return ep;
	}

	public String btnAgregar_action() {
		return toAbm(new TipoParametroGrillaModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new TipoParametroGrillaModel().new ModificarController());
	}

	public String btnConsultar_action() {
		return toAbm(new TipoParametroGrillaModel().new ConsultarController());
	}

	public String btnEliminar_action() {
		return toAbm(new TipoParametroGrillaModel().new EliminarController());
	}

	public ObjectListDataProvider getLdpTipoParametroGrilla() {
		return ldpTipoParametroGrilla;
	}

	public void setLdpTipoParametroGrilla(ObjectListDataProvider ldpTipoParametroGrilla) {
		this.ldpTipoParametroGrilla = ldpTipoParametroGrilla;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaTipoParametroGrilla();
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$AdminTipoParametroGrilla}";
	}

	@Override
	public long getSerialVersionUID() {
		return TipoParametroGrilla.serialVersionUID;
	}
}