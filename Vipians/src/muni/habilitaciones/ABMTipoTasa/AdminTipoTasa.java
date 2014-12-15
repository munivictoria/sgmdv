/*
 * AdminTipoTasa.java
 *
 * Created on 20 de octubre de 2006, 07:28
 * Copyright Trascender SRL
 */
package muni.habilitaciones.ABMTipoTasa;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoTasa;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminTipoTasa extends AdminPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a campos ocultos.
    
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarTipoTasa";
    private final String lnkModificar = "ModificarTipoTasa";
    private final String lnkEliminar = "EliminarTipoTasa";
    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
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

     	armarDropDownTipoObligacion();
    	
        Option[] op = null;
        // Tipo de Obligacion

        Option[] opEstado = null;
        //Estado de Obligacion
        //TipoTasa tTasa = null;
        opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoTasa.Estado.values(), "cap");
        ddEstadoObligacionDefaultOptions.setOptions(opEstado);
        this.getDdEstadoObligacion().setSelected("");
        this.getDdEstadoObligacionDefaultOptions().setSelectedValue("");

        this.habilitarBtnExportar();
    }
  
    private void armarDropDownTipoObligacion(){
    	System.out.println("ejecuto armarDropDownTipoObligacion");
        Option[] op = this.getApplicationBean1().getMgrDropDown().
                armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().keySet().toArray(),"");
        ddTipoObligacionParaAgregarDefaultOptions.setOptions(op);
        ddTipoObligacionDefaultOptions.setOptions(op);
    }

	private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    
    private ObjectListDataProvider ldpTipoTasa = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpTipoTasa() {
        return ldpTipoTasa;
    }

    public void setLdpTipoTasa(ObjectListDataProvider oldp) {
        this.ldpTipoTasa = oldp;
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
    private DropDown ddTipoObligacion = new DropDown();

    public DropDown getDdTipoObligacion() {
        return ddTipoObligacion;
    }

    public void setDdTipoObligacion(DropDown dd) {
        this.ddTipoObligacion = dd;
    }
    private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
        return ddTipoObligacionDefaultOptions;
    }

    public void setDdTipoObligacionDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddTipoObligacionDefaultOptions = ssol;
    }
    private DropDown ddEstadoObligacion = new DropDown();

    public DropDown getDdEstadoObligacion() {
        return ddEstadoObligacion;
    }

    public void setDdEstadoObligacion(DropDown ddEstadoObligacion) {
        this.ddEstadoObligacion = ddEstadoObligacion;
    }
    private SingleSelectOptionsList ddEstadoObligacionDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdEstadoObligacionDefaultOptions() {
        return ddEstadoObligacionDefaultOptions;
    }

    public void setDdEstadoObligacionDefaultOptions(SingleSelectOptionsList ddEstadoObligacionDefaultOptions) {
        this.ddEstadoObligacionDefaultOptions = ddEstadoObligacionDefaultOptions;
    }
    private Label lblEstado = new Label();

    public Label getLblEstado() {
        return lblEstado;
    }

    public void setLblEstado(Label lblEstado) {
        this.lblEstado = lblEstado;
    }
    
    private PanelGroup gpSeleccion1 = new PanelGroup();

    public PanelGroup getGpSeleccion1() {
        return gpSeleccion1;
    }

    public void setGpSeleccion1(PanelGroup pg) {
        this.gpSeleccion1 = pg;
    }
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private PanelGroup gpBotones1 = new PanelGroup();

    public PanelGroup getGpBotones1() {
        return gpBotones1;
    }

    public void setGpBotones1(PanelGroup pg) {
        this.gpBotones1 = pg;
    }
    
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    
    private Button btnClonarFormula = new Button();

    public Button getBtnClonarFormula() {
        return btnClonarFormula;
    }

    public void setBtnClonarFormula(Button btnClonarFormula) {
        this.btnClonarFormula = btnClonarFormula;
    }
    
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }
    
    private DropDown ddTipoObligacionParaAgregar = new DropDown();

    public DropDown getDdTipoObligacionParaAgregar() {
        return ddTipoObligacionParaAgregar;
    }

    public void setDdTipoObligacionParaAgregar(DropDown dd) {
        this.ddTipoObligacionParaAgregar = dd;
    }
    private SingleSelectOptionsList ddTipoObligacionParaAgregarDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdTipoObligacionParaAgregarDefaultOptions() {
        return ddTipoObligacionParaAgregarDefaultOptions;
    }

    public void setDdTipoObligacionParaAgregarDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddTipoObligacionParaAgregarDefaultOptions = ssol;
    }
    private Button btnActivarFormula = new Button();

    public Button getBtnActivarFormula() {
        return btnActivarFormula;
    }

    public void setBtnActivarFormula(Button b) {
        this.btnActivarFormula = b;
    }
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }
    
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }

    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }

    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }

    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText st) {
        this.staticText11 = st;
    }
    
    private StaticText staticText12 = new StaticText();

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText st) {
        this.staticText12 = st;
    }
    private StaticText staticText13 = new StaticText();

    public StaticText getStaticText13() {
        return staticText13;
    }

    public void setStaticText13(StaticText staticText13) {
        this.staticText13 = staticText13;
    }
    
    @Override
    public PaginatedTable getPaginatedTable() {
        return this.getCommunicationHabilitacionesBean().getTablaTipoTasa();
    }

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public AdminTipoTasa() {
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroTipoTasa locFiltro = this.getFiltro();
    	
        locFiltro.setNombre(this.getTextFieldValue(this.getTfNombre()));
        locFiltro.setTipoObligacion(this.getDDObjectValue(this.getDdTipoObligacion(), this.getCommunicationHabilitacionesBean().getMapaTipoObligacion()));
        locFiltro.setEstado(this.getDDEnumValue(this.getDdEstadoObligacion(), TipoTasa.Estado.class));
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	FiltroTipoTasa locFiltro = this.getFiltro();
    	
        this.getTfNombre().setText(locFiltro.getNombre());
        this.getDdEstadoObligacion().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
        this.getDdEstadoObligacionDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroTipoTasa locFiltro = this.getFiltro();
    	locFiltro.setEstado(null);
    	locFiltro.setNombre("");
    	locFiltro.setPeriodicidad(null);
    	locFiltro.setTipoObligacion(null);
    	
        this.getTfNombre().setText(null);
        this.getDdTipoObligacion().setSelected("");
        this.getDdTipoObligacionDefaultOptions().setSelectedValue("");
        this.getDdEstadoObligacion().setSelected("");
        this.getDdEstadoObligacionDefaultOptions().setSelectedValue("");
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpTipoTasa();
    }

    @Override
    public List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaTiposTasa();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaTiposTasa(lista);
    }

    // </editor-fold>
    
    public String btnAgregar_action() {
    	if(this.getDdTipoObligacionParaAgregar().getSelected() != ""){
    		TipoObligacion tipoObligacion = null;
    		tipoObligacion = this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(ddTipoObligacionParaAgregar.getSelected());
	
	            this.getRequestBean1().setObjetoSeleccion(tipoObligacion);
	            return toAbm(new TipoTasaModel().new AgregarTipoTasaController());
    	} else{
    		warn("Seleccione un Tipo de Obligaci\363n para agregar una F\363rmula de C\341lculo.");
            return null;
    	}
    }

    public String btnModificar_action() {

        TipoTasa tipoTasa = (TipoTasa) this.getObjetoSeleccionado();
        this.getRequestBean1().setObjetoSeleccion(tipoTasa.getTipoObligacion());
        
        if(tipoTasa == null){
            return null;
        }
        //siguiente validacion a pedido de santa :P...
        /*if (tipoTasa.getEstado().equals(TipoTasa.Estado.ACTIVA) || tipoTasa.getEstado().equals(TipoTasa.Estado.INACTIVA)) {
            warn("Las f\363rmulas de c\341lculo en estado ACTIVA o INACTIVA, no pueden ser modificadas.");
            return null;
        }*/

        return toAbm(new TipoTasaModel().new ModificarTipoTasaController());
    }

    public String btnClonarFormula_action() {
        
        TipoTasa tipoTasa = (TipoTasa) this.getObjetoSeleccionado();
        if(tipoTasa == null){
            return null;
        }
        
        try {
            this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
            tipoTasa = (TipoTasa) this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getTipoTasaPorId(tipoTasa.getIdTipoTasa());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if (tipoTasa.getEstado().equals(TipoTasa.Estado.EN_ESPERA) || tipoTasa.getEstado().equals(TipoTasa.Estado.INACTIVA)) {
            warn("Las f\363rmulas de c\341lculo en estado EN ESPERA o INACTIVA, no pueden ser clonadas.");
            return null;
        }
        
        try{

            TipoTasa nuevoTipoTasa = tipoTasa.clone();
            this.getRequestBean1().setObjetoABM(nuevoTipoTasa);
//            this.getDdTipoObligacion().setSelected(Util.getEnumNameFromString(String.valueOf(nuevoTipoTasa.getTipoObligacion())));
//            this.getDdTipoObligacionDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(nuevoTipoTasa.getTipoObligacion())));
            return toAbm(new TipoTasaModel().new AgregarTipoTasaController());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;        
    }

    public String btnEliminar_action() {
        TipoTasa tipoTasa = (TipoTasa) this.getObjetoSeleccionado();
        
        if(tipoTasa == null){
            return null;
        }
        if (!tipoTasa.getEstado().equals(TipoTasa.Estado.EN_ESPERA)) {
            warn("Solo se pueden eliminar Fórmulas de Cálculo 'En Espera'.");
            return null;
        }
        return toAbm(new TipoTasaModel().new EliminarTipoTasaController());
    }

    public String btnActivarFormula_action() {

        TipoTasa tipoTasa = (TipoTasa) this.getObjetoSeleccionado();
        
        if(tipoTasa == null){
            return null;
        }
        
        if (!tipoTasa.getEstado().equals(TipoTasa.Estado.EN_ESPERA)) {
            error("S\363lo puede Activar una F\363rmula de C\341lculo con estado En Espera");
            return null;
        }
        
        try{
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().activarTipoTasa(tipoTasa, null);
        } catch (Exception e){
            e.printStackTrace();
        }
        info("La F\363rmula de C\341lculo ha cambiado su estado a Activa.");
        return null;
    }

    public String btnConsultar_action() {
        return toAbm(new TipoTasaModel().new ConsultarTipoTasaController());
    }

    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {
        TipoTasa locTipoTasa = (TipoTasa) pObject;
        this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
        locTipoTasa = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getTipoTasaPorId(locTipoTasa.getIdTipoTasa());
        return locTipoTasa;
    }

    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTiposTasa((FiltroTipoTasa)pFiltro);
    }

    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de F\363rmulas de C\341lculo";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminTipoTasa";
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
        if (pObject instanceof TipoObligacion) {
            TipoTasa tipoTasa = (TipoTasa) this.obtenerObjetoDelElementoPila(0, TipoTasa.class);
//            tipoTasa.setTipoObligacion((TipoObligacion) seleccionado);
            this.getElementoPila().getObjetos().set(0, tipoTasa);
            try {
                this.refrescarTabla();
            } catch (Exception ex) {
            }
        }
	}
	
	@Override
	public long getSerialVersionUID() {
		return TipoTasa.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$ABMTipoTasa$AdminTipoTasa}";
	}
}
