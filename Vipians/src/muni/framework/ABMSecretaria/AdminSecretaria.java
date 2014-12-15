package muni.framework.ABMSecretaria;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.filtros.FiltroSecretaria;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author nico
 * 
 */
public class AdminSecretaria extends AdminPageBean {

	private Label lbNombre = new Label();

	public Label getLbNombre() {
		return lbNombre;
	}

	public void setLbNombre(Label lbNombre) {
		this.lbNombre = lbNombre;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	private ObjectListDataProvider ldpSecretaria = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpSecretaria() {
		return ldpSecretaria;
	}

	public void setLdpSecretaria(ObjectListDataProvider ldpSecretaria) {
		this.ldpSecretaria = ldpSecretaria;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpSecretaria();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationBean().getListaSecretarias();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaSecretarias(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroSecretaria locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		
		this.getTfNombre().setText("");
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Secretaria locSecretaria = (Secretaria) pObject;
		getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
		locSecretaria = getComunicationBean().getRemoteSystemMunicipalidad().getSecretariaPorId(locSecretaria.getIdSecretaria());
		return locSecretaria;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemMunicipalidad().findListaSecretarias((FiltroSecretaria) pFiltro);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroSecretaria locFiltro = this.getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroSecretaria locFiltro = this.getFiltro();
		
		this.getTfNombre().setText(locFiltro.getNombre());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Secretarias";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminSecretaria";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaSecretaria();
	}

	public String btnAgregar_action() {
		return toAbm(new SecretariaModel().new AgregarSecretariaController());
	}

	public String btnModificar_action() {
		return toAbm(new SecretariaModel().new ModificarSecretariaController());
	}

	public String btnEliminar_action() {
		return toAbm(new SecretariaModel().new EliminarSecretariaController());
	}

	public String btnConsultar_action() {
		return toAbm(new SecretariaModel().new ConsultarSecretariaController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public long getSerialVersionUID() {
		return Secretaria.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMSecretaria$AdminSecretaria}";
	}
}