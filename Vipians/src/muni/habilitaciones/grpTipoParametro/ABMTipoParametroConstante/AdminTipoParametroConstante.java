/*
 * AdminTipoParametroConstante.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpTipoParametro.ABMTipoParametroConstante;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;


public class AdminTipoParametroConstante extends AdminPageBean {

    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Par\341metros Constantes";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminTipoParametroConstante";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    
    private ObjectListDataProvider ldpTipoParametroConstante = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpTipoParametroConstante() {
        return ldpTipoParametroConstante;
    }

    public void setLdpTipoParametroConstante(ObjectListDataProvider oldp) {
        this.ldpTipoParametroConstante = oldp;
    }
    
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
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

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }
    
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }
    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tableColumn7) {
        this.tableColumn7 = tableColumn7;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
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
    
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }
    
    public AdminTipoParametroConstante() {
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroTipoParametroConstante locFiltro = this.getFiltro();
        locFiltro.setNombre(getTextFieldValue(this.getTfNombre()));
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        FiltroTipoParametroConstante locFiltro = this.getFiltro();

        this.getTfNombre().setText(locFiltro.getNombre());
    }

    @Override
	protected void limpiarObjetosUsados() {
        FiltroTipoParametroConstante locFiltro = (FiltroTipoParametroConstante) this.getFiltro();
        locFiltro.setNombre(null);
        this.getTfNombre().setText(null);
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpTipoParametroConstante();
    }

    @Override
    protected List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaTiposParametroConstante();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda

        this.getCommunicationHabilitacionesBean().setListaTiposParametroConstante(lista);
    }
    
    public String btnAgregar_action() {
        return toAbm(new TipoParametroConstanteModel(). new AgregarTipoParametroConstanteController());
    }

    public String btnModificar_action() {
        return toAbm(new TipoParametroConstanteModel(). new ModificarTipoParametroConstanteController());
    }

    public String btnEliminar_action() {
        return toAbm(new TipoParametroConstanteModel(). new EliminarTipoParametroConstanteController());
    }

    public String btnConsultar_action() {
        return toAbm(new TipoParametroConstanteModel(). new ConsultarTipoParametroConstanteController());
    }

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		TipoParametroConstante locTipoParametro = (TipoParametroConstante) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getTipoParametroConstantePorId(locTipoParametro.getIdTipoParametro());
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaTipoParametroConstante();
	}
	
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaParametrosConstantes((FiltroTipoParametroConstante) pFiltro);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Par\341metros Constantes";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminTipoParametroConstante";
	}
	
	@Override
	public long getSerialVersionUID() {
		return TipoParametroConstante.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$AdminTipoParametroConstante}";
	}
}
