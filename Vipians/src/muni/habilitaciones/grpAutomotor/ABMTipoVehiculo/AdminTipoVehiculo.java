/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpAutomotor.ABMTipoVehiculo;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoVehiculo;
import com.trascender.habilitaciones.recurso.filtros.FiltroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fer
 */
public class AdminTipoVehiculo extends AdminPageBean{
    
	protected void _init () throws Exception{
//		this.setListaDelCommunicationAtributosDinamicos(null);
	}
    private ObjectListDataProvider ldpTipoVehiculo = new ObjectListDataProvider();
    
    private Label lblNombre = new Label();
    private Label lblCodigo = new Label();
    
    private TextField tfNombre = new TextField();
    private TextField tfCodigo = new TextField();
    
    private TableColumn tcNombre = new TableColumn();
    private TableColumn tcCodigo = new TableColumn();
    private TableColumn tcDescripcion = new TableColumn();
    
    private StaticText stNombre = new StaticText();
    private StaticText stCodigo = new StaticText();
    private StaticText stDescripcion = new StaticText();

    private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();
    
    public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public Label getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(Label lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public Label getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(Label lblNombre) {
        this.lblNombre = lblNombre;
    }

    public ObjectListDataProvider getLdpTipoVehiculo() {
        return ldpTipoVehiculo;
    }

    public void setLdpTipoVehiculo(ObjectListDataProvider ldpTipoVehiculo) {
        this.ldpTipoVehiculo = ldpTipoVehiculo;
    }

    public StaticText getStCodigo() {
        return stCodigo;
    }

    public void setStCodigo(StaticText stCodigo) {
        this.stCodigo = stCodigo;
    }

    public StaticText getStDescripcion() {
        return stDescripcion;
    }

    public void setStDescripcion(StaticText stDescripcion) {
        this.stDescripcion = stDescripcion;
    }

    public StaticText getStNombre() {
        return stNombre;
    }

    public void setStNombre(StaticText stNombre) {
        this.stNombre = stNombre;
    }

    public TableColumn getTcCodigo() {
        return tcCodigo;
    }

    public void setTcCodigo(TableColumn tcCodigo) {
        this.tcCodigo = tcCodigo;
    }

    public TableColumn getTcDescripcion() {
        return tcDescripcion;
    }

    public void setTcDescripcion(TableColumn tcDescripcion) {
        this.tcDescripcion = tcDescripcion;
    }

    public TableColumn getTcNombre() {
        return tcNombre;
    }

    public void setTcNombre(TableColumn tcNombre) {
        this.tcNombre = tcNombre;
    }

    public TextField getTfCodigo() {
        return tfCodigo;
    }

    public void setTfCodigo(TextField tfCodigo) {
        this.tfCodigo = tfCodigo;
    }

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tfNombre) {
        this.tfNombre = tfNombre;
    }
    

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        return this.getLdpTipoVehiculo();
    }

    @Override
    protected List getListaDelCommunication() {
        return getCommunicationHabilitacionesBean().getListaTiposVehiculo();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        getCommunicationHabilitacionesBean().setListaTiposVehiculo(lista);
    }

    @Override
    protected TableSelectPhaseListener getTablePhaseListener() {
        return getCommunicationHabilitacionesBean().getTablePhaseListenerTipoVehiculo();
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
       
    	FiltroTipoVehiculo locFiltroTipoVehiculo = this.getFiltro();
        locFiltroTipoVehiculo.setNombre(getTextFieldValue(this.getTfNombre()));
        locFiltroTipoVehiculo.setCodigo(getTextFieldValue(this.getTfCodigo()));
        
        if (locFiltroTipoVehiculo.getListaAtributoDinamico() != null) {
        	locFiltroTipoVehiculo.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltroTipoVehiculo.getListaAtributoDinamico()));
		}
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	FiltroTipoVehiculo locFiltroTipoVehiculo = this.getFiltro();
    	
        this.getTfNombre().setText(locFiltroTipoVehiculo.getNombre());
        this.getTfCodigo().setText(locFiltroTipoVehiculo.getCodigo());
        
        panelAtributoDinamico = new PanelAtributoDinamico(locFiltroTipoVehiculo.getListaAtributoDinamico(), "#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltroTipoVehiculo.getListaAtributoDinamico());
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroTipoVehiculo locFiltro = this.getFiltro();
    	locFiltro.setCodigo("");
    	locFiltro.setNombre("");
    	
        this.getTfNombre().setText("");
        this.getTfCodigo().setText("");
        this.getStCantidadRegistros().setText("");
        panelAtributoDinamico.limpiarCampos();
    }

    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {
        TipoVehiculo locTipoVehiculo = (TipoVehiculo) pObject;
        this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(this.getSessionBean1().getLlave());
        locTipoVehiculo = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getTipoVehiculoById(locTipoVehiculo.getIdTipoVehiculo());
        return locTipoVehiculo;
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminTipoVehiculo";
    }
    
    @Override
    protected String getNombrePagina() {
        return "Administración de Tipos de Vehiculo";
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
    	FiltroTipoVehiculo locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(TipoVehiculo.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);
        return ep;
    }
    
    public String btnAgregar_action(){
        return toAbm(new TipoVehiculoModel().new AgregarController());
    }
    
    public String btnModificar_action(){
        return toAbm(new TipoVehiculoModel().new ModificarController());
    }
    
    public String btnEliminar_action(){
        return toAbm(new TipoVehiculoModel().new EliminarController());
    }
    
   public String btnConsultar_action(){
        return toAbm(new TipoVehiculoModel().new ConsultarController());
    }

    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        FiltroTipoVehiculo locFiltroTipoVehiculo = (FiltroTipoVehiculo)pFiltro;
        return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().findListaTipoVehiculo(locFiltroTipoVehiculo);
    }
    @Override
    public PaginatedTable getPaginatedTable(){
        return this.getCommunicationHabilitacionesBean().getTablaTipoVehiculo();
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		
	}
    
//    private ArrayList getListaDelCommunicationAtributosDinamicos() {
//        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
//        return this.getComunicationBean().getListaAtributosDinamicosTipoVehiculo();
//    }
//
//    private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
//        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
//        this.getComunicationBean().setListaAtributosDinamicosTipoVehiculo(lista);
//    }
    
	@Override
	public long getSerialVersionUID() {
		return TipoVehiculo.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo}";
	}
}
