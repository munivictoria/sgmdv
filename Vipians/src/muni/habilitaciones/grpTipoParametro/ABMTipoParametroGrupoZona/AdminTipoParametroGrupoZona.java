/*
 * AdminTipoParametroGrupoZona.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpTipoParametro.ABMTipoParametroGrupoZona;

import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;


public class AdminTipoParametroGrupoZona extends AdminPageBean {

    
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Par\341metros de Grupos de Zonas";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminTipoParametroGrupoZona";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarTipoParametroGrupoZona";
    private final String lnkModificar = "ModificarTipoParametroGrupoZona";
    private final String lnkEliminar = "EliminarTipoParametroGrupoZona";

    private ObjectListDataProvider ldpTipoParametroGrupoZona = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpTipoParametroGrupoZona() {
        return ldpTipoParametroGrupoZona;
    }

    public void setLdpTipoParametroGrupoZona(ObjectListDataProvider oldp) {
        this.ldpTipoParametroGrupoZona = oldp;
    }
    
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private Label lblZonificacion = new Label();

    public Label getLblZonificacion() {
        return lblZonificacion;
    }

    public void setLblZonificacion(Label lblZonificacion) {
        this.lblZonificacion = lblZonificacion;
    }
    private TextField tfZonificacion = new TextField();

    public TextField getTfZonificacion() {
        return tfZonificacion;
    }

    public void setTfZonificacion(TextField tfZonificacion) {
        this.tfZonificacion = tfZonificacion;
    }
    private Button btnSeleccionarZonificacion = new Button();

    public Button getBtnSeleccionarZonificacion() {
        return btnSeleccionarZonificacion;
    }

    public void setBtnSeleccionarZonificacion(Button btnSeleccionarZonificacion) {
        this.btnSeleccionarZonificacion = btnSeleccionarZonificacion;
    }
    private HtmlAjaxCommandButton btnLimpiarZonificacion = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarZonificacion() {
		return btnLimpiarZonificacion;
	}

	public void setBtnLimpiarZonificacion(HtmlAjaxCommandButton btnLimpiarZonificacion) {
		this.btnLimpiarZonificacion = btnLimpiarZonificacion;
	}
  
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    
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
    
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }
    
    public AdminTipoParametroGrupoZona() {
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroTipoParametroGrupoZona locFiltro = this.getFiltro();
    	
    	locFiltro.setNombre(this.getTextFieldValue(this.getTfNombre()));
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	FiltroTipoParametroGrupoZona locFiltro = this.getFiltro();
    	
        if (locFiltro.getZonificacion() != null) {
            this.getTfZonificacion().setText(locFiltro.getZonificacion().getNombre());
        } else {
            this.getTfZonificacion().setText("");
        }
        
        if(locFiltro.getNombre() != null){
        	this.getTfNombre().setText(locFiltro.getNombre());
        }
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroTipoParametroGrupoZona locFiltro = this.getFiltro();
    	locFiltro.setNombre(null);
    	locFiltro.setZonificacion(null);
    	
        this.getTfNombre().setText(null);
        this.getTfZonificacion().setText(null);
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpTipoParametroGrupoZona();
    }

    @Override
    protected List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaTiposParametroGrupoZona();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaTiposParametroGrupoZona(lista);
    }

    public String btnAgregar_action() {
        return toAbm(new TipoParametroGrupoZonaModel(). new AgregarTipoParametroGrupoZonaController());
    }

    public String btnModificar_action() {
    	return toAbm(new TipoParametroGrupoZonaModel(). new ModificarTipoParametroGrupoZonaController());
    }

    public String btnEliminar_action() {
    	return toAbm(new TipoParametroGrupoZonaModel(). new EliminarTipoParametroGrupoZonaController());
    }

    public String btnConsultar_action() {
    	return toAbm(new TipoParametroGrupoZonaModel(). new ConsultarTipoParametroGrupoZonaController());
    }

    public String btnSeleccionarZonificacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminZonificacion";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarZonificacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
        	FiltroTipoParametroGrupoZona locFiltro = this.getFiltro();
        	locFiltro.setZonificacion(null);
            this.getTfZonificacion().setText("");
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		TipoParametroGrupoZona locTipoParametroGrupoZona = (TipoParametroGrupoZona) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getTipoParametroGrupoZonaPorId(locTipoParametroGrupoZona.getIdTipoParametro());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTipoParametroGrupoZona((FiltroTipoParametroGrupoZona) pFiltro);
	}
	
	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaTipoParametroGrupoZona();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroTipoParametroGrupoZona locFiltro = this.getFiltro();
		if(pObject instanceof Zonificacion){
			Zonificacion locZonificacion = (Zonificacion) pObject;
			locFiltro.setZonificacion(locZonificacion);
		}
	}

	@Override
	protected String getNombrePagina() {
		return "Par\341metro de Grupo de Zonas";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminTipoParametroGrupoZona";
	}
	
	@Override
	public long getSerialVersionUID() {
		return TipoParametroGrupoZona.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$AdminTipoParametroGrupoZona}";
	}
}
