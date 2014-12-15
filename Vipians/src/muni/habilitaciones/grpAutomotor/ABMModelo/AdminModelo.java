/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpAutomotor.ABMModelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroModelo;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
/**
 *
 * @author Fernando Luca
 */
public class AdminModelo extends AdminPageBean {
    
    private ObjectListDataProvider ldpModelo = new ObjectListDataProvider();
    
    private Label lblNombre = new Label();
    private Label lblDescripcion = new Label();
    private Label lblMinimo = new Label();
    private Label lblTipoVehiculo = new Label();
    private Label lblMarca = new Label();
    
    private TextField tfNombre = new TextField();
    private TextField tfMinimo = new TextField();
    private TextField tfTipoVehiculo = new TextField();
    private TextField tfMarca = new TextField();
    
    
    private StaticText stDescripcion = new StaticText();
    private StaticText stNombre = new StaticText();
    private StaticText stMinimo = new StaticText();
    
    private TableColumn tcDescripcion = new TableColumn();
    private TableColumn tcNombre = new TableColumn();
    private TableColumn tcMinimo = new TableColumn();
    
    private Button btnSeleccionMarca = new Button();
    private HtmlAjaxCommandButton btnLimpiarMarca = new HtmlAjaxCommandButton();
    private Button btnSeleccionarTipoVehiculo = new Button();
    private HtmlAjaxCommandButton btnLimpiarTipoVehiculo = new HtmlAjaxCommandButton();
    private Button btnSeleccionarMarca = new Button();

    private DropDown ddTipoVehiculo = new DropDown();
    private SingleSelectOptionsList ddTipoVehiculoDefaultOptions = new SingleSelectOptionsList();
    private DropDown ddMarca = new DropDown();
    private SingleSelectOptionsList ddMarcaDefaultOptions = new SingleSelectOptionsList();
    
    private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();
    
    public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public Button getBtnSeleccionarMarca() {
		return btnSeleccionarMarca;
	}

	public HtmlAjaxCommandButton getBtnLimpiarMarca() {
		return btnLimpiarMarca;
	}

	public void setBtnLimpiarMarca(HtmlAjaxCommandButton btnLimpiarMarca) {
		this.btnLimpiarMarca = btnLimpiarMarca;
	}

	public HtmlAjaxCommandButton getBtnLimpiarTipoVehiculo() {
		return btnLimpiarTipoVehiculo;
	}

	public void setBtnLimpiarTipoVehiculo(HtmlAjaxCommandButton btnLimpiarTipoVehiculo) {
		this.btnLimpiarTipoVehiculo = btnLimpiarTipoVehiculo;
	}

	public void setBtnSeleccionarMarca(Button btnSeleccionarMarca) {
		this.btnSeleccionarMarca = btnSeleccionarMarca;
	}

	public DropDown getDdMarca() {
		return ddMarca;
	}

	public void setDdMarca(DropDown ddMarca) {
		this.ddMarca = ddMarca;
	}

	public SingleSelectOptionsList getDdMarcaDefaultOptions() {
		return ddMarcaDefaultOptions;
	}

	public void setDdMarcaDefaultOptions(
			SingleSelectOptionsList ddMarcaDefaultOptions) {
		this.ddMarcaDefaultOptions = ddMarcaDefaultOptions;
	}

	public DropDown getDdTipoVehiculo() {
		return ddTipoVehiculo;
	}

	public void setDdTipoVehiculo(DropDown ddTipoVehiculo) {
		this.ddTipoVehiculo = ddTipoVehiculo;
	}

	public SingleSelectOptionsList getDdTipoVehiculoDefaultOptions() {
		return ddTipoVehiculoDefaultOptions;
	}

	public void setDdTipoVehiculoDefaultOptions(
			SingleSelectOptionsList ddTipoVehiculoDefaultOptions) {
		this.ddTipoVehiculoDefaultOptions = ddTipoVehiculoDefaultOptions;
	}

	public Label getLblDescripcion() {
        return lblDescripcion;
    }

    public void setLblDescripcion(Label lblDescripcion) {
        this.lblDescripcion = lblDescripcion;
    }

    public Label getLblMinimo() {
        return lblMinimo;
    }

    public void setLblMinimo(Label lblMinimo) {
        this.lblMinimo = lblMinimo;
    }

    public Label getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(Label lblNombre) {
        this.lblNombre = lblNombre;
    }

    public ObjectListDataProvider getLdpModelo() {
        return ldpModelo;
    }

    public void setLdpModelo(ObjectListDataProvider oldpModelo) {
        this.ldpModelo = oldpModelo;
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

    public StaticText getStMinimo() {
        return stMinimo;
    }

    public void setStMinimo(StaticText stMinimo) {
        this.stMinimo = stMinimo;
    }

    public TableColumn getTcMinimo() {
        return tcMinimo;
    }

    public void setTcMinimo(TableColumn tcMinimo) {
        this.tcMinimo = tcMinimo;
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

    public TextField getTfMinimo() {
        return tfMinimo;
    }

    public void setTfMinimo(TextField tfMinimo) {
        this.tfMinimo = tfMinimo;
    }

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tfNombre) {
        this.tfNombre = tfNombre;
    }

    public Button getBtnSeleccionMarca() {
        return btnSeleccionMarca;
    }

    public void setBtnSeleccionMarca(Button btnSeleccionMarca) {
        this.btnSeleccionMarca = btnSeleccionMarca;
    }

    public Button getBtnSeleccionarTipoVehiculo() {
		return btnSeleccionarTipoVehiculo;
	}

	public void setBtnSeleccionarTipoVehiculo(Button btnSeleccionarTipoVehiculo) {
		this.btnSeleccionarTipoVehiculo = btnSeleccionarTipoVehiculo;
	}

	public Label getLblMarca() {
        return lblMarca;
    }

    public void setLblMarca(Label lblMarca) {
        this.lblMarca = lblMarca;
    }

    public Label getLblTipoVehiculo() {
        return lblTipoVehiculo;
    }

    public void setLblTipoVehiculo(Label lblTipoVehiculo) {
        this.lblTipoVehiculo = lblTipoVehiculo;
    }

    public TextField getTfMarca() {
        return tfMarca;
    }

    public void setTfMarca(TextField tfMarca) {
        this.tfMarca = tfMarca;
    }

    public TextField getTfTipoVehiculo() {
        return tfTipoVehiculo;
    }

    public void setTfTipoVehiculo(TextField tfTipoVehiculo) {
        this.tfTipoVehiculo = tfTipoVehiculo;
    }
    
    
    public AdminModelo(){
    }
    
    @Override
    protected void _init() throws Exception{
    	
//    	this.setListaDelCommunicationAtributosDinamicos(null);
      	Set<String> locListaMarcas = this.getCommunicationHabilitacionesBean().getMapaMarca().keySet();
        
        Option[] opMarca = new Option[locListaMarcas.size() + 1];
        int j = 0;
        opMarca[j++] = new Option("", "");
        for (String cadaMarca : locListaMarcas){
     	   opMarca[j++] = new Option(cadaMarca, cadaMarca);
        }
        this.ddMarcaDefaultOptions.setOptions(opMarca);
    	
    	Set<String> locListaTipoVehiculos = this.getCommunicationHabilitacionesBean().getMapaTipoVehiculo().keySet();
           
           Option[] opTipo = new Option[locListaTipoVehiculos.size() + 1];
           int i = 0;
           opTipo[i++] = new Option("", "");
           for (String cadaModelo : locListaTipoVehiculos){
        	   opTipo[i++] = new Option(cadaModelo, cadaModelo);
           }
           this.ddTipoVehiculoDefaultOptions.setOptions(opTipo);
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
        FiltroModelo locFiltro = this.getFiltro();
        
        locFiltro.setNombre(getTextFieldValue(this.getTfNombre()));
        locFiltro.setMarca(getDDObjectValue(this.getDdMarca(), this.getCommunicationHabilitacionesBean().getMapaMarca()));
        locFiltro.setTipoVehiculo(getDDObjectValue(this.getDdTipoVehiculo(), this.getCommunicationHabilitacionesBean().getMapaTipoVehiculo()));
        
        if (locFiltro.getListaAtributoDinamico() != null) {
        	locFiltro.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico()));
		}
    }
    
    @Override
    protected void mostrarEstadoObjetosUsados() {
        
    	FiltroModelo locFiltroModelo = this.getFiltro();

        this.getTfNombre().setText(locFiltroModelo.getNombre());
        if (locFiltroModelo.getMarca() != null){
        this.getDdMarca().setSelected(locFiltroModelo.getMarca().toString());
        }
        if (locFiltroModelo.getTipoVehiculo() != null){
        this.getDdTipoVehiculo().setSelected(locFiltroModelo.getTipoVehiculo().toString());
        }
        
        panelAtributoDinamico = new PanelAtributoDinamico(locFiltroModelo.getListaAtributoDinamico(), "#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltroModelo.getListaAtributoDinamico());
    }
    
    @Override
    protected void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina

    	FiltroModelo locFiltro = this.getFiltro();
    	locFiltro.setMarca(null);
    	locFiltro.setNombre("");
    	locFiltro.setTipoVehiculo(null);
    	
        this.getTfNombre().setText("");
        this.getTfMinimo().setText("");
        this.getDdMarca().setSelected("");
        this.getDdTipoVehiculo().setSelected("");
        panelAtributoDinamico.limpiarCampos();
    }
    
    public String btnSeleccionMarca_action() {
        return navegarParaSeleccionar("AdminMarca");
    }
    
    public String btnLimpiarMarca_action() {
        String retorno = null;

        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(this.getDdMarca());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public String btnSeleccionarTipoVehiculo_action() {
        return navegarParaSeleccionar("AdminTipoVehiculo");
    }
    
    public String btnSeleccionarMarca_action() {
        return navegarParaSeleccionar("AdminMarca");
    }
    
    public String btnLimpiarTipoVehiculo_action() {
        String retorno = null;

        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(this.getDdTipoVehiculo());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        return this.getLdpModelo();
    }

    @Override
    protected List getListaDelCommunication() {
        return this.getCommunicationHabilitacionesBean().getListaModelos();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        this.getCommunicationHabilitacionesBean().setListaModelos(lista);
    }

    @Override
    protected TableSelectPhaseListener getTablePhaseListener() {
        return this.getCommunicationHabilitacionesBean().getTablePhaseListenerModelo();
    }

    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {
        Modelo locModelo = (Modelo) pObject;
        this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(this.getSessionBean1().getLlave());
        locModelo = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getModeloById(locModelo.getIdModelo());
        return locModelo;        
    }
    
    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Modelos";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminModelo";
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
    	FiltroModelo locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Modelo.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);
        return ep;
    } 
    
    public String btnAgregar_action(){
        return toAbm(new ModeloModel().new AgregarController());
    }
    
    public String btnModificar_action(){
        return toAbm(new ModeloModel().new ModificarController());
    }
    
    public String btnEliminar_action(){
        return toAbm(new ModeloModel().new EliminarController());
    }
    
   public String btnConsultar_action(){
        return toAbm(new ModeloModel().new ConsultarController());
    }

    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        FiltroModelo locFiltroModelo = (FiltroModelo) pFiltro;
        return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().findListaModelo(locFiltroModelo);
    }
    
    @Override
    public PaginatedTable getPaginatedTable(){
        return this.getCommunicationHabilitacionesBean().getTablaModelo();
    }
//    private ArrayList getListaDelCommunicationAtributosDinamicos() {
//        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
//        return this.getComunicationBean().getListaAtributosDinamicosModelo();
//    }
//
//    private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
//        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
//        this.getComunicationBean().setListaAtributosDinamicosModelo(lista);
//    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroModelo locFiltro = this.getFiltro();
        if (pObject instanceof Marca) {
        	Marca locMarca = (Marca) pObject;
        	locFiltro.setMarca(locMarca);
        }

        if (pObject instanceof TipoVehiculo) {
        	TipoVehiculo locTipoVehiculo = (TipoVehiculo) pObject;
        	locFiltro.setTipoVehiculo(locTipoVehiculo);
        }
	}
    
	@Override
	public long getSerialVersionUID() {
		return Modelo.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo}";
	}
}
