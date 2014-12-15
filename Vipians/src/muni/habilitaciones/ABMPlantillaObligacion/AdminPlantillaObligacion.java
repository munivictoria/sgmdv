/*
 * AdminPlantillaObligacion.java
 *
 * Created on 20 de octubre de 2006, 07:28
 * Copyright Trascender SRL
 */
package muni.habilitaciones.ABMPlantillaObligacion;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminPlantillaObligacion extends AdminPageBean {

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

    private ObjectListDataProvider ldpPlantillaObligacion = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpPlantillaObligacion() {
        return ldpPlantillaObligacion;
    }

    public void setLdpPlantillaObligacion(ObjectListDataProvider oldp) {
        this.ldpPlantillaObligacion = oldp;
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
    
    public AdminPlantillaObligacion() {
    }
    
    @Override
    protected void _init() throws Exception {
        // Tipo de Obligacion
        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().keySet().toArray(), "cap");
        ddTipoObligacionDefaultOptions.setOptions(op);
    }
    
//    @Override
//    protected void _prerender(){
//        try {
//            this.btnBuscar_action();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

   @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {        
        return ep;
    }
    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroPlantillaObligacion locFiltro = this.getFiltro();
    	
        locFiltro.setNombre(getTextFieldValue(this.getTfNombre()));
        locFiltro.setTipoObligacion(getDDObjectValue(this.ddTipoObligacion, this.getCommunicationHabilitacionesBean().getMapaTipoObligacion()));
    }
    @Override
    protected void mostrarEstadoObjetosUsados() {
    	FiltroPlantillaObligacion locFiltro = this.getFiltro();
    	
        this.getTfNombre().setText(locFiltro.getNombre());
        this.getDdTipoObligacion().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoObligacion())));
        this.getDdTipoObligacionDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoObligacion())));
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpPlantillaObligacion();
    }

    @Override
    protected ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaPlantillasObligacion();
    }

    @Override
     protected void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaPlantillasObligacion((ArrayList)lista);
    }

    
    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {    
        PlantillaObligacion locPlantilla = (PlantillaObligacion) pObject;
        this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().setLlave(getSessionBean1().getLlave());
        locPlantilla = this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().getPlantillaObligacion(locPlantilla.getIdPlantillaObligacion());
        return locPlantilla;
    }
    
    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        return this.getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().findListaPlantillaObligaciones((FiltroPlantillaObligacion)pFiltro);
    }

    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Platillas de Obligaciones";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminPlantillaObligacion";
    }
    
    @Override
    public PaginatedTable getPaginatedTable(){
        return this.getCommunicationHabilitacionesBean().getTablaPlantillaObligacion();
    }
    
    public String btnAgregar_action(){
    	System.out.println("ANTES DE AGREGAR");
         return toAbm(new PlantillaObligacionesModel().new AgregarPlantillaObligacionesController());
    }
    
    public String btnModificar_action(){
        return toAbm(new PlantillaObligacionesModel().new ModificarPlantillaObligacionController());
    }
    
    public String btnConsultar_action(){
        return toAbm(new PlantillaObligacionesModel().new ConsultarPlantillaObligacionController());
    }
    
    public String btnEliminar_action(){
        return toAbm(new PlantillaObligacionesModel().new EliminarPlantillaObligacionController());
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroPlantillaObligacion locFiltro = this.getFiltro();
    	
    	locFiltro.setNombre(null);
    	locFiltro.setTipoObligacion(null);

        this.getTfNombre().setText("");
        this.getDdTipoObligacion().setSelected("");
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		PlantillaObligacion plantilla = null;
		
		if (pObject instanceof TipoObligacion) {
            plantilla = (PlantillaObligacion) this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);
            plantilla.setTipoObligacion((TipoObligacion) pObject);
            this.getElementoPila().getObjetos().set(0, plantilla);
            try {
                this.refrescarTabla();
            } catch (Exception ex) {
            }
        }
	}
	
	@Override
	public long getSerialVersionUID() {
		return PlantillaObligacion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion}";
	}
}
