/*
 * AdminManzana.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpSHPS.ABMLocalComercial;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroLocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminLocalComercial extends AdminPageBean {

    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Locales Comerciales";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminLocalComercial";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarLocalComercial";
    private final String lnkModificar = "ModificarLocalComercial";
    private final String lnkEliminar = "EliminarLocalComercial";
    private final String lnkConsultar = "ConsultarLocalComercial";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
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

    private ObjectListDataProvider ldpLocalComercial = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpLocalComercial() {
        return ldpLocalComercial;
    }

    public void setLdpLocalComercial(ObjectListDataProvider oldp) {
        this.ldpLocalComercial = oldp;
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
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private TextField tfNumeroInscripcion = new TextField();

    public TextField getTfNumeroInscripcion() {
        return tfNumeroInscripcion;
    }

    public void setTfNumeroInscripcion(TextField tf) {
        this.tfNumeroInscripcion = tf;
    }
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }
    private TextArea textArea1 = new TextArea();

    public TextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(TextArea ta) {
        this.textArea1 = ta;
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
    private Button btnImprimirReporte = new Button();

    public Button getBtnImprimirReporte() {
        return btnImprimirReporte;
    }

    public void setBtnImprimirReporte(Button b) {
        this.btnImprimirReporte = b;
    }
    // </editor-fold>

    /**
     * <p>Construir una instancia de bean de p?gina.</p>
     */
    public AdminLocalComercial() {
    }
    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        Parcela parcela = this.getSessionBean1().getParcelaSeleccionada();

        FiltroLocalComercial locFiltro = this.getFiltro();
        
        borrarListIdAuxParcelas(this.getTfParcelaSeleccionada(), locFiltro.getParcela());
    	locFiltro.setListaIdParcelas(this.getSessionBean1().getListaIdParcelas());
        
        locFiltro.setNumeroInscripcion(getTextFieldValue(this.getTfNumeroInscripcion()));
        locFiltro.setParcela(parcela);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        Parcela parcela = this.getSessionBean1().getParcelaSeleccionada();
        
        FiltroLocalComercial locFiltro = this.getFiltro();
        
        this.getTfNumeroInscripcion().setText(locFiltro.getNumeroInscripcion());
        if (parcela != null && parcela.getIdParcela() != -1){
            this.getTfParcelaSeleccionada().setText(parcela.toString());
        }
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroLocalComercial locFiltro = this.getFiltro();
    	locFiltro.setNumeroInscripcion(null);
    	locFiltro.setParcela(null);
    	
        this.getSessionBean1().setParcelaSeleccionada(null);
        this.getSessionBean1().getListaIdParcelas().clear();
        
        // CAMBIAR: Limpiar los textField y los dropDown
        this.getTfNumeroInscripcion().setText(null);
        this.getTfParcelaSeleccionada().setText(null);
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpLocalComercial();
    }

    @Override
    protected ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaLocalesComerciales();
    }
    
    @Override
    protected void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaLocalesComerciales((ArrayList)lista);
    }
    // </editor-fold>
    
    public String btnAgregar_action() {
        return toAbm(new LocalComercialModel(). new agregarLocalComercialController());
    }

    public String btnModificar_action() {
        return toAbm(new LocalComercialModel(). new modificarLocalComercialController());
    }

    public String btnEliminar_action() {
        return toAbm(new LocalComercialModel(). new eliminarLocalComercialController());
    }

    public String btnConsultar_action() {
        return toAbm(new LocalComercialModel(). new consultarLocalComercialController());
    }

    public String btnImprimirReporte_action() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        return null;
    }
    
    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {
        LocalComercial comercial = (LocalComercial) pObject;
        return this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getLocalComercialPorId(comercial.getIdLocalComercial());
    }

    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
       FiltroLocalComercial locFiltroLocalComercial = (FiltroLocalComercial) pFiltro;
       return this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().findListaLocalesComerciales(locFiltroLocalComercial);
    }

    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Locales Comerciales";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminLocalComercial";
    }
   @Override
    public PaginatedTable getPaginatedTable(){
        return this.getCommunicationHabilitacionesBean().getTablaLocalComercial();
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Object seleccionado = pObject;
    
	    if (seleccionado instanceof Parcela) {
	        Parcela parcela = (Parcela) seleccionado;
	        this.getSessionBean1().setParcelaSeleccionada(parcela);
	    }
	}
	
	@Override
	public long getSerialVersionUID() {
		return LocalComercial.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial}";
	}
	
	public void setParcelaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroLocalComercial locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Parcela locParcela = null;
		
		try {
			try {
				locParcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(id);
			} catch (TrascenderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		
		locFiltro.setParcela(locParcela);
		this.getSessionBean1().setParcelaSeleccionada(locParcela);
	}
}
