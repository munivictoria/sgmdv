package muni.habilitaciones.grpSHPS.ABMInspector;

import javax.faces.component.UIComponent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

public class ABMInspector extends ABMPageBean{
    
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Agregar Inspector";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarInspector";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    // </editor-fold>
    
    private TextField tfNombre = new TextField();
    
    public TextField getTfNombre() {
        return tfNombre;
    }
    
    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    
    private Label label4 = new Label();
    
    public Label getLabel4() {
        return label4;
    }
    
    public void setLabel4(Label l) {
        this.label4 = l;
    }
    
    private MessageGroup messageGroup1 = new MessageGroup();
    
    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }
    
    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
    
    private Label label1 = new Label();
    
    public Label getLabel1() {
        return label1;
    }
    
    public void setLabel1(Label l) {
        this.label1 = l;
    }
    
    private TextField tfPersona = new TextField();
    
    public TextField getTfPersona() {
        return tfPersona;
    }
    
    public void setTfPersona(TextField tf) {
        this.tfPersona = tf;
    }
    
    private Button btnSeleccionarPersona = new Button();
    
    public Button getBtnSeleccionarPersona() {
        return btnSeleccionarPersona;
    }
    
    public void setBtnSeleccionarPersona(Button b) {
        this.btnSeleccionarPersona = b;
    }

    private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}
	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}
	
    public ABMInspector() {
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new Inspector());
        ep.getObjetos().add(ind++, new PersonaFisica());
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        Inspector inspector = (Inspector) this.obtenerObjetoDelElementoPila(0, Inspector.class);
        PersonaFisica persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(1, PersonaFisica.class);
        
        Object nombre = this.getTfNombre().getText();
        
        if (nombre != null && nombre != "") inspector.setNombre(nombre.toString()); else inspector.setNombre(null);
        
        if(persona.getIdPersonaFisica()==-1) persona = null;
        inspector.setPersona(persona);
        
        this.getElementoPila().getObjetos().set(0, inspector);
    }
    
    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        
        int ind = 0;
        Inspector inspector = (Inspector) this.obtenerObjetoDelElementoPila(ind++, Inspector.class);
        PersonaFisica persona = (PersonaFisica) this.obtenerObjetoDelElementoPila(ind++, PersonaFisica.class);
        
        this.getTfNombre().setText(inspector.getNombre());
        this.getTfPersona().setText(persona.toString());
    }

    public String btnSeleccionarPersona_action() {
        return this.navegarParaSeleccionar("AdminPersonaFisica");    
    }

    public String btnLimpiarPersona_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if(ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(1,PersonaFisica.class,this.getTfPersona());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof PersonaFisica){
			this.getElementoPila().getObjetos().set(1, pObject);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Inspector inspector = null;
        PersonaFisica persona = null;
        
        if (pObject != null) {
            inspector = (Inspector) pObject;
            persona = inspector.getPersona();
            
            this.getElementoPila().getObjetos().set(0, inspector);
            this.getElementoPila().getObjetos().set(1, persona);
        }
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMInspector";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Inspector locInspector = this.obtenerObjetoDelElementoPila(0, Inspector.class);
		this.getTablaLogs().getLdpLogs().setList(locInspector.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return Inspector.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpSHPS$ABMInspector$ABMInspector}";
	}
}
