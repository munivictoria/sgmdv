/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpAutomotor.ABMModelo;

import java.util.ArrayList;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 *
 * @author Fernando Luca
 */
public class ABMModelo extends ABMPageBean {
    
    private Label lblNombre = new Label();
    private Label lblDescripcion = new Label();
    private Label lblMinimo = new Label();
    private Label lblMarca = new Label();
    private Label lblTipoVehiculo = new Label();
    
    private TextField tfNombre = new TextField();
    private TextField tfMinimo = new TextField();
    private TextField tfTipoVehiculo = new TextField();
    private TextField tfMarca = new TextField();
    private TextArea taDescripcion = new TextArea();

    private Button btnSeleccionarMarca = new Button();
    private Button btnSeleccionarTipoVehiculo = new Button();
    private HtmlAjaxCommandButton btnLimpiarMarca = new HtmlAjaxCommandButton();
    private HtmlAjaxCommandButton btnLimpiarTipoVehiculo = new HtmlAjaxCommandButton();
    
    private DropDown ddTipoVehiculo = new DropDown();
    private SingleSelectOptionsList ddTipoVehiculoDefaultOptions = new SingleSelectOptionsList();
    
    private DropDown ddMarca = new DropDown();
    private SingleSelectOptionsList ddMarcaDefaultOptions = new SingleSelectOptionsList();
    
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
        
    public Button getBtnSeleccionarMarca() {
		return btnSeleccionarMarca;
	}

	public void setBtnSeleccionarMarca(Button btnSeleccionarMarca) {
		this.btnSeleccionarMarca = btnSeleccionarMarca;
	}

	public Button getBtnSeleccionarTipoVehiculo() {
		return btnSeleccionarTipoVehiculo;
	}

	public void setBtnSeleccionarTipoVehiculo(Button btnSeleccionarTipoVehiculo) {
		this.btnSeleccionarTipoVehiculo = btnSeleccionarTipoVehiculo;
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

    public TextArea getTaDescripcion() {
        return taDescripcion;
    }

    public void setTaDescripcion(TextArea taDescripcion) {
        this.taDescripcion = taDescripcion;
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
    
    @Override
    protected void _init () throws Exception{
    
    	Set<String> locListaTipoVehiculo = this.getCommunicationHabilitacionesBean().getMapaTipoVehiculo().keySet();
    	Option[] opTipo = new Option[locListaTipoVehiculo.size() + 1];
    	
    	int i=0;
    	opTipo[i++] = new Option("" , "");
    	for (String cadaTipo : locListaTipoVehiculo){
    		opTipo[i++] = new Option(cadaTipo, cadaTipo);
    	}
    	this.ddTipoVehiculoDefaultOptions.setOptions(opTipo);
    	
    	Set<String> locListaMarca = this.getCommunicationHabilitacionesBean().getMapaMarca().keySet();
    	Option[] opMarca = new Option[locListaMarca.size() + 1];
    	
    	int j=0;
    	opMarca[j++] = new Option("" , "");
    	for (String cadaMarca : locListaMarca){
    		opMarca[j++] = new Option(cadaMarca, cadaMarca);
    	}
    	this.ddMarcaDefaultOptions.setOptions(opMarca);
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        ep.getObjetos().add(ind++, new Modelo());
        ep.getObjetos().add(ind++, new Marca());
        ep.getObjetos().add(ind++, new TipoVehiculo());
        ep.getObjetos().add(ind++, new ArrayList()); //AtributosDinamicos
        return ep;
    }
    
    public String btnSeleccionarMarca_action() {
        return navegarParaSeleccionar("AdminMarca");
    }
    
    public String btnLimpiarMarca_action() {
        String retorno = null;

        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(1, this.getDdMarca());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public String btnSeleccionarTipoVehiculo_action() {
        return navegarParaSeleccionar("AdminTipoVehiculo");
    }
    
    public String btnLimpiarTipoVehiculo_action() {
        String retorno = null;

        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(2, this.getDdTipoVehiculo());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        
        Modelo modelo = (Modelo) this.obtenerObjetoDelElementoPila(0, Modelo.class);
        ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

        modelo.setNombre(getTextFieldValue(this.getTfNombre()));
        modelo.setMinimo(Double.parseDouble(getTextFieldValue(this.getTfMinimo())));
        modelo.setDescripcion(getTextAreaValue(this.getTaDescripcion()));
        Marca marca = getDDObjectValue(this.getDdMarca(), this.getCommunicationHabilitacionesBean().getMapaMarca());
        modelo.setMarca(marca);
        TipoVehiculo tipoVehiculo = getDDObjectValue(this.getDdTipoVehiculo(), this.getCommunicationHabilitacionesBean().getMapaTipoVehiculo());
        modelo.setTipoVehiculo(tipoVehiculo);
        
        atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
        modelo.setListaAtributosDinamicos(atributosDinamicos);
        
        this.getElementoPila().getObjetos().set(0, modelo);
        this.getElementoPila().getObjetos().set(1, marca);
        this.getElementoPila().getObjetos().set(2, tipoVehiculo);
        this.getElementoPila().getObjetos().set(3, atributosDinamicos);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() 
    {
    	Modelo modelo = (Modelo) this.obtenerObjetoDelElementoPila(0, Modelo.class);
        Marca marca = (Marca) this.obtenerObjetoDelElementoPila(1, Marca.class);
        TipoVehiculo tipoVehiculo = (TipoVehiculo) this.obtenerObjetoDelElementoPila(2, TipoVehiculo.class);
        ArrayList atributosDinamicos =  (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
        
		if (modelo.getListaAtributosDinamicos() != null) {
			try {
				atributosDinamicos = (ArrayList) this
						.getComunicationBean()
						.getRemoteSystemParametro()
						.getAtributosPorRecurso(Modelo.serialVersionUID,
								modelo.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(3, atributosDinamicos);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		modelo = (Modelo) this.obtenerObjetoDelElementoPila(0, Modelo.class);
        marca = (Marca) this.obtenerObjetoDelElementoPila(1, Marca.class);
        tipoVehiculo = (TipoVehiculo) this.obtenerObjetoDelElementoPila(2, TipoVehiculo.class);
        
        this.getTfNombre().setText(modelo.getNombre());
        this.getTaDescripcion().setText(modelo.getDescripcion());
        this.getTfMinimo().setText(modelo.getMinimo());
        this.getDdMarca().setSelected(marca.toString());
        this.getDdTipoVehiculo().setSelected(tipoVehiculo.toString());
        
        atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

        panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo}");
        panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
    }
    
    @Override
    protected String getCasoNavegacion() {
        return "ABMModelo";
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

        if (pObject instanceof Marca) {
            this.getElementoPila().getObjetos().set(1, pObject);
        }

        if (pObject instanceof TipoVehiculo) {
            this.getElementoPila().getObjetos().set(2, pObject);
        }
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Modelo modelo = null;
        Marca marca = null;
        TipoVehiculo tipoVehiculo = null;
		
		try{
            modelo = (Modelo) pObject;
            marca = modelo.getMarca();
            tipoVehiculo = modelo.getTipoVehiculo();
        }catch(Exception e){
            log(this.getCasoNavegacion() + "_Guardar Error:", e);
            e.printStackTrace();
        }
        
        if (marca != null && marca.getIdMarca() != -1){
        getDdMarca().setSelected(marca.getNombre().toString());
        }
        if (tipoVehiculo != null && tipoVehiculo.getIdTipoVehiculo() != -1){
        	getDdTipoVehiculo().setSelected(tipoVehiculo.getNombre().toString());
        }
        
        
        this.getElementoPila().getObjetos().set(0, modelo);
        this.getElementoPila().getObjetos().set(1, marca);
        this.getElementoPila().getObjetos().set(2, tipoVehiculo);
	}  
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Modelo locModelo = this.obtenerObjetoDelElementoPila(0, Modelo.class);
		this.getTablaLogs().getLdpLogs().setList(locModelo.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return Modelo.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpAutomotor$ABMModelo$ABMModelo}";
	}
}
