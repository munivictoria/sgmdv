/*
 * AdminTransporteVehicular.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpBromatologia.ABMTransporteVehicular;

import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroTransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminTransporteVehicular extends AdminPageBean {
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Transportes Vehiculares";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminTransporteVehicular";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarTransporteVehicular";
    private final String lnkModificar = "ModificarTransporteVehicular";
    private final String lnkEliminar = "EliminarTransporteVehicular";
    private final String lnkConsultar = "ConsultarTransporteVehicular";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
     */
    @Override
    protected void _init() throws Exception {
        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        dateTimeConverter1.setTimeStyle("short");
        dateTimeConverter1.setPattern("dd/MM/yyyy");
        this.habilitarBtnExportar();
    }
    private Label label1 = new Label();
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label label3) {
        this.label3 = label3;
    }

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private HtmlAjaxCommandButton btnLimpiarVehiculo = new HtmlAjaxCommandButton();
    private Checkbox cbActivo = new Checkbox();

    public Checkbox getCbActivo() {
        return cbActivo;
    }

    public void setCbActivo(Checkbox cbActivo) {
        this.cbActivo = cbActivo;
    }

    public HtmlAjaxCommandButton getBtnLimpiarVehiculo() {
		return btnLimpiarVehiculo;
	}

	public void setBtnLimpiarVehiculo(HtmlAjaxCommandButton btnLimpiarVehiculo) {
		this.btnLimpiarVehiculo = btnLimpiarVehiculo;
	}
	private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private Button btnSeleccionarVehiculo = new Button();

    public Button getBtnSeleccionarVehiculo() {
        return btnSeleccionarVehiculo;
    }

    public void setBtnSeleccionarVehiculo(Button b) {
        this.btnSeleccionarVehiculo = b;
    }
    private TextField tfVehiculo = new TextField();

    public TextField getTfVehiculo() {
        return tfVehiculo;
    }

    public void setTfVehiculo(TextField tf) {
        this.tfVehiculo = tf;
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
    private StaticText staticText9 = new StaticText();
    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText staticText10) {
        this.staticText10 = staticText10;
    }

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }

    private ObjectListDataProvider ldpTransporteVehicular = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpTransporteVehicular() {
        return ldpTransporteVehicular;
    }

    public void setLdpTransporteVehicular(ObjectListDataProvider oldp) {
        this.ldpTransporteVehicular = oldp;
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
    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tableColumn7) {
        this.tableColumn7 = tableColumn7;
    }

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
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p?gina.</p>
     */
    public AdminTransporteVehicular() {
    }
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {        
        FiltroTransporteVehicular locFiltro = this.getFiltro();

        locFiltro.setNumeroInscripcion(getTextFieldValue(this.getTfNumeroInscripcion()));
        locFiltro.setActivo(this.getCbActivo().isChecked());
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	FiltroTransporteVehicular locFiltro = this.getFiltro();
    	
        if(locFiltro.getNumeroInscripcion() != null){
        	this.getTfNumeroInscripcion().setText(locFiltro.getNumeroInscripcion());
        }
        if (locFiltro.getVehiculo() != null && locFiltro.getVehiculo().getIdVehiculo() != -1) {
            this.getTfVehiculo().setText(locFiltro.getVehiculo().toString());
        }
        this.getCbActivo().setValue(locFiltro.getActivo());
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroTransporteVehicular locFiltro = this.getFiltro();
    	locFiltro.setActivo(true);
    	locFiltro.setVehiculo(null);
    	locFiltro.setNumeroInscripcion("");
    	
        // CAMBIAR: Limpiar los textField y los dropDown
        this.getTfNumeroInscripcion().setText(null);
        this.getTfVehiculo().setText(null);
        this.getCbActivo().setSelected(true);
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpTransporteVehicular();
    }

    @Override
    protected ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaTransportesVehiculares();
    }
    public String btnAgregar_action() {
        return toAbm(new TransporteVehicularModel(). new AgregarTransporteVehicularController());
    }

    public String btnModificar_action() {
        return toAbm(new TransporteVehicularModel(). new ModificarTransporteVehicularController());
    }

    public String btnEliminar_action() {
        return toAbm(new TransporteVehicularModel(). new EliminarTransporteVehicularController());
    }

    public String btnConsultar_action() {
        return toAbm(new TransporteVehicularModel(). new ConsultarTrasnporteVehicularController());
    }

    public String btnSeleccionarVehiculo_action(){
        return navegarParaSeleccionar("AdminVehiculo");
    }
    
    
    public String btnLimpiarVehiculo_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.limpiarObjeto(this.getTfVehiculo());
            FiltroTransporteVehicular locFiltro = this.getFiltro();
        	locFiltro.setVehiculo(null);
            
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        ArrayList array = (ArrayList) lista;
        this.getCommunicationHabilitacionesBean().setListaTransportesVehiculares(array);
    }

    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {
        TransporteVehicular trasporte = (TransporteVehicular) pObject;
        return this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getTransporteVehicularPorId(trasporte.getIdTransporteVehicular());
    }
    
    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        FiltroTransporteVehicular locFiltroTransporteVehicular = (FiltroTransporteVehicular)pFiltro;
        return this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().findListaTransportesVehiculares(locFiltroTransporteVehicular);
    }

    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Transportes Vehiculares";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminTransporteVehicular";
    }
    @Override
    public PaginatedTable getPaginatedTable(){
        return this.getCommunicationHabilitacionesBean().getTablaTransporteVehicular();
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroTransporteVehicular locFiltro = this.getFiltro();
		
		if(pObject instanceof Vehiculo){
			Vehiculo locVehiculo = (Vehiculo) pObject;
    		locFiltro.setVehiculo(locVehiculo);
    	}
	}
	
	@Override
	public long getSerialVersionUID() {
		return TransporteVehicular.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular}";
	}
}
