/*
 * AdminMarca.java
 *
 * Created on 1 de noviembre de 2006, 08:32
 * Copyright Trascender
 */
package muni.habilitaciones.grpAutomotor.ABMMarca;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroMarca;
import com.trascender.habilitaciones.recurso.filtros.FiltroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminMarca extends AdminPageBean {
    
	@Override
	protected void _init () throws Exception{
		
//		this.setListaDelCommunicationAtributosDinamicos(null);
	}
   
    /**
     * Solo se declaran los componentes propios de esta pagina. En este caso
     * son los parametros de busqueda y las columnas de la tabla
     */
    private ObjectListDataProvider ldpMarca = new ObjectListDataProvider();
    
    private Label lblNombre = new Label();
    private Label lblCodigo = new Label();
    
    private TextField tfNombre = new TextField();
    private TextField tfCodigo = new TextField();
    
    private StaticText stCodigo = new StaticText();
    private StaticText stNombre = new StaticText();
   
    private TableColumn tcCodigo = new TableColumn();
    private TableColumn tcNombre = new TableColumn();

    private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();
    
    public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public StaticText getStCodigo() {
        return stCodigo;
    }

    public void setStCodigo(StaticText stCodigo) {
        this.stCodigo = stCodigo;
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

    public TableColumn getTcNombre() {
        return tcNombre;
    }

    public void setTcNombre(TableColumn tcNombre) {
        this.tcNombre = tcNombre;
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

    public ObjectListDataProvider getLdpMarca() {
        return ldpMarca;
    }

    public void setLdpMarca(ObjectListDataProvider oldp) {
        this.ldpMarca = oldp;
    }

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }

    public TextField getTfCodigo() {
        return tfCodigo;
    }

    public void setTfCodigo(TextField tfCodigo) {
        this.tfCodigo = tfCodigo;
    }

    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public AdminMarca() {
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroMarca locFiltroMarca = this.getFiltro();
        locFiltroMarca.setNombre(getTextFieldValue(this.getTfNombre()));
        locFiltroMarca.setCodigo(getTextFieldValue(this.getTfCodigo()));
        
        if (locFiltroMarca.getListaAtributoDinamico() != null) {
        	locFiltroMarca.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltroMarca.getListaAtributoDinamico()));
		}
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Obtener una instancia por cada objeto manejado en la pagina
        FiltroMarca locFiltroMarca = this.getFiltro();

        this.getTfNombre().setText(locFiltroMarca.getNombre());
        this.getTfCodigo().setText(locFiltroMarca.getCodigo());
        
        panelAtributoDinamico = new PanelAtributoDinamico(locFiltroMarca.getListaAtributoDinamico(), "#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltroMarca.getListaAtributoDinamico());
    }
    
    @Override
    protected Object getObjectPorId(Object pObject) throws Exception{
        Marca locMarca = (Marca) pObject;
        this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(this.getSessionBean1().getLlave());
        locMarca = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getMarcaById(locMarca.getIdMarca());
        return locMarca;
    }

    @Override
    protected void limpiarObjetosUsados() {
        FiltroMarca locFiltroMarca = this.getFiltro();
        locFiltroMarca.setCodigo("");
        locFiltroMarca.setNombre("");
        locFiltroMarca.setListaAtributoDinamico(null);
        
        this.getTfNombre().setText("");
        this.getTfCodigo().setText("");
        
        panelAtributoDinamico.limpiarCampos();
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        return this.getLdpMarca();
    }

    @Override
    protected List getListaDelCommunication() {
        return this.getCommunicationHabilitacionesBean().getListaMarcas();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        this.getCommunicationHabilitacionesBean().setListaMarcas(lista);
    }

    @Override
    protected TableSelectPhaseListener getTablePhaseListener() {
        return this.getCommunicationHabilitacionesBean().getTablePhaseListenerMarca();
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminMarca";
    }

    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Marcas";
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
    	FiltroMarca locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Marca.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);
        return ep;
    }
    
    public String btnAgregar_action(){
        return toAbm(new MarcaModel().new AgregarController());
    }
    
    public String btnModificar_action(){
        return toAbm(new MarcaModel().new ModificarController());
    }
    
    public String btnEliminar_action(){
        return toAbm(new MarcaModel().new EliminarController());
    }
    
   public String btnConsultar_action(){
        return toAbm(new MarcaModel().new ConsultarController());
    }

    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        FiltroMarca locFiltroMarca = (FiltroMarca) pFiltro;
        return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().findListaMarca(locFiltroMarca);
    }
    @Override
    public PaginatedTable getPaginatedTable(){
        return this.getCommunicationHabilitacionesBean().getTablaMarca();
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
		
	}
    
//    private ArrayList getListaDelCommunicationAtributosDinamicos() {
//        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
//        return this.getComunicationBean().getListaAtributosDinamicosMarca();
//    }
//
//    private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
//        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
//        this.getComunicationBean().setListaAtributosDinamicosMarca(lista);
//    }
    
	@Override
	public long getSerialVersionUID() {
		return Marca.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca}";
	}
}
