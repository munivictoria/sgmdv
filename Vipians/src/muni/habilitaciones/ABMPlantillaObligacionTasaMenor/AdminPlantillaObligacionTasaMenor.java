/*
 * AdminPlantillaObligacionTasaMenor.java
 *
 * Created on 28 de noviembre de 2006, 14:59
 * Copyright Trascender SRL
 */
package muni.habilitaciones.ABMPlantillaObligacionTasaMenor;

import java.util.List;

import javax.faces.convert.IntegerConverter;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
//import jasperReports.Reporte;

public class AdminPlantillaObligacionTasaMenor extends AdminPageBean {

    private Label lblNombre = new Label();

    public Label getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(Label lblNombre) {
        this.lblNombre = lblNombre;
    }
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tfNombre) {
        this.tfNombre = tfNombre;
    }
   
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

    private ObjectListDataProvider ldpPlantillaDocumentoTasaMenor = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpPlantillaDocumentoTasaMenor() {
        return ldpPlantillaDocumentoTasaMenor;
    }

    public void setLdpPlantillaDocumentoTasaMenor(ObjectListDataProvider ldpPlantillaDocumentoTasaMenor) {
        this.ldpPlantillaDocumentoTasaMenor = ldpPlantillaDocumentoTasaMenor;
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
    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
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
    private StaticText staticText14 = new StaticText();

    public StaticText getStaticText14() {
        return staticText14;
    }

    public void setStaticText14(StaticText staticText14) {
        this.staticText14 = staticText14;
    }
    
    public AdminPlantillaObligacionTasaMenor() {
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new PlantillaDocumentoTasaMenor());
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        PlantillaDocumentoTasaMenor locPlantilla = (PlantillaDocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, PlantillaDocumentoTasaMenor.class);

        Object nombre = this.getTfNombre().getText();

        if (nombre != null && nombre != "") {
            locPlantilla.setNombre(nombre.toString());
        } else {
            locPlantilla.setNombre(null);
        }

        ind = 0;
        this.getElementoPila().getObjetos().set(0, locPlantilla);//0
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        PlantillaDocumentoTasaMenor locPlantilla = null;

        int ind = 0;
        locPlantilla = (PlantillaDocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, PlantillaDocumentoTasaMenor.class);
        this.getTfNombre().setText(locPlantilla.getNombre());
    }
    
    @Override
	protected void limpiarObjetosUsados() {
    	
        this.getTfNombre().setText("");
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpPlantillaDocumentoTasaMenor();
    }

    @Override
    protected List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationBean().getListaPlantillaDocumentoTasaMenor();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationBean().setListaPlantillaDocumentoTasaMenor(lista);
    }

    public String btnAgregar_action() {
        return toAbm(new PlantillaObligacionTasaMenorModel(). new AgregarPlantillaObligacionTasaMenorController());
    }

    public String btnModificar_action() {       
    	return toAbm(new PlantillaObligacionTasaMenorModel(). new ModificarPlantillaObligacionTasaMenorController());
    }

    public String btnEliminar_action() {
    	return toAbm(new PlantillaObligacionTasaMenorModel(). new EliminarPlantillaObligacionTasaMenorController());
    }

    public String btnConsultar_action() {
    	return toAbm(new PlantillaObligacionTasaMenorModel(). new ConsultarPlantillaObligacionTasaMenorController());

    }
    
	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		PlantillaDocumentoTasaMenor locPlantilla = (PlantillaDocumentoTasaMenor) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().setLlave(this.getSessionBean1().getLlave());
	    return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().getPlantillaDocumentoPorId(locPlantilla.getIdDocumentoTasaMenor());
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaPlantillaObligacionTasaMenor();
	}
	
	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().findListaPlantillaDocumentoTasaMenor((FiltroPlantillaDocumentoTasaMenor) pFiltro);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Plantillas de Obligaciones Menores";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminPlantillaObligacionTasaMenor";
	}
	
	@Override
	public long getSerialVersionUID() {
		return PlantillaDocumentoTasaMenor.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor}";
	}
}
