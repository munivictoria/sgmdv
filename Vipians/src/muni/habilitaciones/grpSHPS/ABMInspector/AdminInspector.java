/*
 * AdminInspector.java
 *
 * Created on 20 de octubre de 2006, 07:28
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpSHPS.ABMInspector;

import jasper.ConstantesReportes;
import java.util.List;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;
import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroInspector;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminInspector extends AdminPageBean {
	
	// <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Inspectores";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminInspector";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarInspector";
    private final String lnkModificar = "ModificarInspector";
    private final String lnkEliminar = "EliminarInspector";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }

    private ObjectListDataProvider ldpInspector = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpInspector() {
        return ldpInspector;
    }

    public void setLdpInspector(ObjectListDataProvider oldp) {
        this.ldpInspector = oldp;
    }
    
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
 
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    
    private HtmlAjaxCommandButton btnLimpiarPersonaFisica = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarPersonaFisica() {
		return btnLimpiarPersonaFisica;
	}

	public void setBtnLimpiarPersonaFisica(HtmlAjaxCommandButton btnLimpiarPersonaFisica) {
		this.btnLimpiarPersonaFisica = btnLimpiarPersonaFisica;
	}
	
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }
    
    private Button btnImprimirReporte = new Button();

    public Button getBtnImprimirReporte() {
        return btnImprimirReporte;
    }

    public void setBtnImprimirReporte(Button b) {
        this.btnImprimirReporte = b;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    
    private TextField tfPersonaFisica = new TextField();

    public TextField getTfPersonaFisica() {
        return tfPersonaFisica;
    }

    public void setTfPersonaFisica(TextField tf) {
        this.tfPersonaFisica = tf;
    }
    private Button btnSeleccionarPersonaFisica = new Button();

    public Button getBtnSeleccionarPersonaFisica() {
        return btnSeleccionarPersonaFisica;
    }

    public void setBtnSeleccionarPersonaFisica(Button b) {
        this.btnSeleccionarPersonaFisica = b;
    }
    
    // </editor-fold>

    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public AdminInspector() {
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
    	Persona persona = this.getSessionBean1().getPersonaSeleccionada();
    	
    	 FiltroInspector locFiltro = this.getFiltro();
    	 locFiltro.setNombre(getTextFieldValue(this.getTfNombre()));
    	 
    	 if (locFiltro.getPersona() != null) {
 			this.getTfPersonaFisica().setText(locFiltro.getPersona().toString());
 		}
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	FiltroInspector locFiltro = this.getFiltro();
    	
    	if (locFiltro.getPersona() != null) {
			this.getTfPersonaFisica().setText(locFiltro.getPersona().toString());
		} else {
			this.getTfPersonaFisica().setText("");
		}
    	
    	this.getTfNombre().setText(locFiltro.getNombre());
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroInspector locFiltro = this.getFiltro();
    	locFiltro.setNombre(null);
    	locFiltro.setPersona(null);
    	
        this.getTfNombre().setText("");
        this.getTfPersonaFisica().setText("");
    }

   public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpInspector();
   }
   
   public String btnSeleccionarPersonaFisica_action() {
       return this.navegarParaSeleccionar("AdminPersonaFisica");
   }
   
   public String btnLimpiarPersonaFisica_action() {
       String retorno = null;
       boolean ultimo = this.ultimoElementoPilaDeSubSesion();
       if (ultimo) {
           // CAMBIAR: Especificar objeto
           this.limpiarObjetosUsados();
           this.guardarEstadoObjetosUsados();
       } else {
           retorno = this.prepararCaducidad();
       }
       return retorno;
   } 

    public String btnImprimirReporte_action() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        return null;
    }

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Inspector locInspector = (Inspector) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemInspectores().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationHabilitacionesBean().getRemoteSystemInspectores().getInspectorPorId(locInspector.getIdInspector());
	}
	
	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaInspector();
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception { 
        this.getCommunicationHabilitacionesBean().getRemoteSystemInspectores().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationHabilitacionesBean().getRemoteSystemInspectores().findListaInspectores((FiltroInspector) pFiltro);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Inspectores";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminInspector";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaInspectores();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaInspectores(lista);
	}
	
	public String btnConsultar_action() {
		return toAbm(new InspectorModel().new ConsultarInspectorController());
	}

	public String btnAgregar_action() {
		return toAbm(new InspectorModel().new AgregarInspectorController());
	}

	public String btnModificar_action() {
		return toAbm(new InspectorModel().new ModificarInspectorController());
	}

	public String btnEliminar_action() {
		return toAbm(new InspectorModel().new EliminarInspectorController());
	}
	
	@Override
	public long getSerialVersionUID() {
		return Inspector.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpSHPS$ABMInspector$AdminInspector}";
	}
}
