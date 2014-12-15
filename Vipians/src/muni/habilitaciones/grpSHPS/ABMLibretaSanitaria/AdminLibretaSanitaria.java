/*
 * AdminLibretaSanitaria.java
 *
 * Created on 20 de octubre de 2006, 07:28
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpSHPS.ABMLibretaSanitaria;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

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
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroLibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminLibretaSanitaria extends AdminPageBean {

   
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Libretas Sanitarias";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminLibretaSanitaria";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarLibretaSanitaria";
    private final String lnkModificar = "ModificarLibretaSanitaria";
    private final String lnkEliminar = "EliminarLibretaSanitaria";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
     */
    private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
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

    private ObjectListDataProvider ldpLibretaSanitaria = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpLibretaSanitaria() {
        return ldpLibretaSanitaria;
    }

    public void setLdpLibretaSanitaria(ObjectListDataProvider oldp) {
        this.ldpLibretaSanitaria = oldp;
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
    private TextField tfNumeroLibretaSanitaria = new TextField();

    public TextField getTfNumeroLibretaSanitaria() {
        return tfNumeroLibretaSanitaria;
    }

    public void setTfNumeroLibretaSanitaria(TextField tf) {
        this.tfNumeroLibretaSanitaria = tf;
    }
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
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
    // </editor-fold>

    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public AdminLibretaSanitaria() {
    }
    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroLibretaSanitaria locFiltro = this.getFiltro();
    	
    	locFiltro.setNumeroLibretaSanitaria(getTextFieldValueInteger(this.getTfNumeroLibretaSanitaria()));
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        FiltroLibretaSanitaria locFiltro = this.getFiltro();

        this.getTfNumeroLibretaSanitaria().setText(locFiltro.getNumeroLibretaSanitaria());
        this.getTfPersonaFisica().setText(locFiltro.getPersonaFisica());
        
    }
    @Override
    protected void limpiarObjetosUsados() {
    	FiltroLibretaSanitaria locFiltro = this.getFiltro();
    	locFiltro.setNumeroLibretaSanitaria(null);
    	locFiltro.setPersonaFisica(null);
        // CAMBIAR: Limpiar los textField y los dropDown
        this.getTfNumeroLibretaSanitaria().setText("");
        this.getTfPersonaFisica().setText("");
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpLibretaSanitaria();
    }

    @Override
    protected ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaLibretasSanitarias();
    }
    // </editor-fold>

    // </editor-fold>
    public String btnSeleccionarPersonaFisica_action() {
        return navegarParaSeleccionar("AdminPersonaFisica");
    }

    public String btnAgregar_action() {
        return toAbm(new LibretaSanitariaModel(). new agregarLibretaSanitariaController());
    }

    public String btnModificar_action() {
        return toAbm(new LibretaSanitariaModel(). new modificarLibretaSanitariaController());
    }

    public String btnEliminar_action() {
        return toAbm(new LibretaSanitariaModel(). new eliminarLibretaSanitariaController());
    }

    public String btnImprimirReporte_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                RowKey rk = null;
                rk = this.getSeleccionado();
                System.out.println("----RK " + rk);
                if (rk != null) {
                    int index = this.getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    LibretaSanitaria locLibretaSanitaria = (LibretaSanitaria) obj;
                    // this.getSessionBean1().setObjetoImpresion(obj);
                    this.getCommunicationHabilitacionesBean().getRemoteSystemReportesHabilitaciones().setLlave(this.getSessionBean1().getLlave());
                    JasperPrint jp = this.getCommunicationHabilitacionesBean().getRemoteSystemReportesHabilitaciones().getReporteLibretaSanitaria(locLibretaSanitaria.getIdLibretaSanitaria());

                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_LibretaSanitaria");
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
                log(CASO_NAVEGACION + "_ReporteDinamicoError: ", e);
                error(NOMBRE_PAGINA + " - ReporteDinamico: " + e.getMessage());
            }
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    /**
     * Mines: boton Consultar...s
     */
    public String btnConsultar_action() {
        return toAbm(new LibretaSanitariaModel(). new consutlarLibretaSanitariaController());
    }

    public String btnLimpiarPersona_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(this.getTfPersonaFisica());
            FiltroLibretaSanitaria locFiltro = this.getFiltro();
            locFiltro.setPersonaFisica(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    public void valueChangeEvent(ValueChangeEvent event) {
        this.btnImprimirReporte.setDisabled(false);
    }

    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Libretas Sanitarias";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminLibretaSanitaria";
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        ArrayList lis = (ArrayList)lista;
        this.getCommunicationHabilitacionesBean().setListaLibretasSanitarias(lis);
    }
        

    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {
        LibretaSanitaria libreta = (LibretaSanitaria)pObject;
       return  this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getLibretaSanitariaPorId(libreta.getIdLibretaSanitaria());
    }

    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        FiltroLibretaSanitaria libreta =  (FiltroLibretaSanitaria)pFiltro;
        return  this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().findListaLibretasSanitarias(libreta);
    }
    
    @Override
    public PaginatedTable getPaginatedTable(){
        return this.getCommunicationHabilitacionesBean().getTablaLibretaSanitaria();
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroLibretaSanitaria locFiltro = this.getFiltro();
		
        if (pObject instanceof PersonaFisica) {
        	locFiltro.setPersonaFisica((PersonaFisica)pObject);
            try {
                this.refrescarTabla();
            } catch (Exception ex) {
            }
        }
	}
	
	@Override
	public long getSerialVersionUID() {
		return LibretaSanitaria.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria}";
	}
}
