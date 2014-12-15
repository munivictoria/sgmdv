/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.accionsocial.ABMObraSocial;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.accionSocial.recurso.filtros.FiltroFichaSocial;
import com.trascender.accionSocial.recurso.persistent.ObraSocial;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;
import jasper.ConstantesReportes;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;
import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

/**
 *
 * @author tincho
 */
public class AdminObraSocial extends AbstractPageBean {
    /*
     * AdminObraSocial.java
     *
     * Created on 21 de noviembre de 2006, 11:19 Copyright Trascencer
     */

    private int __placeholder;
    
    public void ordenarPor(ActionEvent actionEvent){
        String orden = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("criterioOrdenamiento");
        this.getCustomTable().setearOrden(orden);
        try {
            this.refrescarTabla();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     public void goBackPage(ActionEvent actionEvent){
        this.getCustomTable().restarUnaPagina();
        try{
            this.refrescarTabla();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
     
      public void goNextPage(ActionEvent actionEvent){
        this.getCustomTable().sumarUnaPagina();
        try{
            this.refrescarTabla();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
      
       public void goFirstPage(ActionEvent actionEvent){
        this.getCustomTable().irPrimerPagina();
        try{
            this.refrescarTabla();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
       
        public void goLastPage(ActionEvent actionEvent){
        this.getCustomTable().irUltimaPagina();
        try{
            this.refrescarTabla();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public PaginatedTable getCustomTable() {
        return this.getCommunicationAccionSocialBean().getTableObraSocial();
    }

    public void setCustomTable(PaginatedTable customTable) {
//        this.getCommunicationAccionSocialBean().setTableObraSocial(customTable);
    }

    private void _init() throws Exception {
        // CAMBIAR: Utilizar el EJBCliet adecuado.
        if (this.getCommunicationAccionSocialBean().getListaObraSociales() != null) {
            this.getLdpObraSociales().setList(this.getCommunicationAccionSocialBean().getListaObraSociales());
        }

        this.habilitarBtnExportar();
    }
    
    
protected PanelGroup pgParametros = new PanelGroup();

 protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();
 protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(HtmlAjaxCommandButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public HtmlAjaxCommandButton getBtnReiniciar() {
        return btnReiniciar;
    }

    public void setBtnReiniciar(HtmlAjaxCommandButton btnReiniciar) {
        this.btnReiniciar = btnReiniciar;
    }

    public PanelGroup getPgParametros() {
        return pgParametros;
    }

    public void setPgParametros(PanelGroup pgParametros) {
        this.pgParametros = pgParametros;
    }
 
    private Page page1 = new Page();

    public Page getPage1() {
        return page1;
    }

    public void setPage1(Page p) {
        this.page1 = p;
    }
    private Html html1 = new Html();

    public Html getHtml1() {
        return html1;
    }

    public void setHtml1(Html h) {
        this.html1 = h;
    }
    private Head head1 = new Head();

    public Head getHead1() {
        return head1;
    }

    public void setHead1(Head h) {
        this.head1 = h;
    }
    private Body body1 = new Body();

    public Body getBody1() {
        return body1;
    }

    public void setBody1(Body b) {
        this.body1 = b;
    }
    private Form form1 = new Form();

    public Form getForm1() {
        return form1;
    }

    public void setForm1(Form f) {
        this.form1 = f;
    }
    private StaticText stTitulo = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }
 
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private Button btnCancelar = new Button();

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button b) {
        this.btnCancelar = b;
    }
    private MessageGroup messageGroup = new MessageGroup();

    public MessageGroup getMessageGroup() {
        return messageGroup;
    }

    public void setMessageGroup(MessageGroup mg) {
        this.messageGroup = mg;
    }
    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    private HiddenField hidIdPagina = new HiddenField();

    public HiddenField getHidIdPagina() {
        return hidIdPagina;
    }

    public void setHidIdPagina(HiddenField hf) {
        this.hidIdPagina = hf;
    }
    private HiddenField hidIdSubSesion = new HiddenField();

    public HiddenField getHidIdSubSesion() {
        return hidIdSubSesion;
    }

    public void setHidIdSubSesion(HiddenField hf) {
        this.hidIdSubSesion = hf;
    }
    private Script scriptValidador = new Script();

    public Script getScriptValidador() {
        return scriptValidador;
    }

    public void setScriptValidador(Script scriptValidador) {
        this.scriptValidador = scriptValidador;
    }
    private Link link1 = new Link();

    public Link getLink1() {
        return link1;
    }

    public void setLink1(Link l) {
        this.link1 = l;
    }
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Vincular a campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;

    public Long getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(Long idPagina) {
        this.idPagina = idPagina;
    }

    public Long getIdSubSesion() {
        return idSubSesion;
    }

    public void setIdSubSesion(Long idSubSesion) {
        this.idSubSesion = idSubSesion;
    }
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tableColumn5) {
        this.tableColumn5 = tableColumn5;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText staticText10) {
        this.staticText10 = staticText10;
    }
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }
    private Button btnImprimirReporte = new Button();

    public Button getBtnImprimirReporte() {
        return btnImprimirReporte;
    }

    public void setBtnImprimirReporte(Button b) {
        this.btnImprimirReporte = b;
    }
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }
    private Button btnConsultar = new Button();

    public Button getBtnConsultar() {
        return btnConsultar;
    }

    public void setBtnConsultar(Button b) {
        this.btnConsultar = b;
    }
    private Button btnExportar = new Button();

    public Button getBtnExportar() {
        return btnExportar;
    }

    public void setBtnExportar(Button btnExportar) {
        this.btnExportar = btnExportar;
    }

// </editor-fold>
    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (muni.CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationCajaBean getCommunicationCajaBean() {
        return (muni.CommunicationCajaBean) getBean("CommunicationCajaBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.CommunicationAccionSocialBean getCommunicationAccionSocialBean() {
        return (muni.CommunicationAccionSocialBean) getBean("CommunicationAccionSocialBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando se navega hasta
     * esta página, ya sea directamente mediante un URL o de manera indirecta a
     * través de la navegación de páginas. Puede personalizar este método para
     * adquirir recursos que se necesitarán para los controladores de eventos y
     * métodos del proceso, sin tener en cuenta si esta página realiza
     * procesamiento de devolución de envíos.</p>
     *
     * <p>Tenga en cuenta que si la petición actual es una devolución de envío,
     * los valores de propiedad de los componentes <strong>no</strong>
     * representan ningún valor enviado con esta petición. En su lugar,
     * representan los valores de propiedades que se guardaron para esta vista
     * cuando se procesó.</p>
     */
    public void init() {
        // Realizar iniciaciones heredadas de la superclase
        super.init();
        // Realizar inicio de aplicación que debe finalizar
        // *antes* de que se inicien los componentes administrados
        // TODO - Agregar código de inicio propio aquí

        // <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
        // Iniciar componentes administrados automáticamente
        // *Nota* - esta lógica NO debe modificarse
        try {
            _init();
        } catch (Exception e) {


            log("AdminObraSocial Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicación que debe finalizar
        // *después* de que se inicien los componentes administrados
        // TODO - Agregar código de inicio propio aquí

    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando el árbol de
     * componentes se ha restaurado, pero antes de que se produzca el
     * procesamiento de cualquier evento. Este método <strong>sólo</strong> se
     * llamará en una petición de devolución de envío que esté procesando el
     * envío de un formulario. Puede personalizar este método para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /**
     * <p>Método de devolución de llamada al que se llama justo antes del
     * procesamiento. <strong>Sólo</strong> se llamará a este método en la
     * página que se procesa, no se llamará, por ejemplo, en una página que ha
     * procesado una devolución de envío y a continuación ha navegado hasta otra
     * página. Puede personalizar este método para asignar recursos necesarios
     * para procesar esta página.</p>
     */
    public void prerender() {

        boolean existeIdSubSesionReq = false;
        boolean existeIdPaginaReq = false;
        boolean existeIdSubSesionPag = false;
        boolean existeIdPaginaPag = false;
        boolean recarga = false;

        if (this.getRequestBean1() != null) {
            existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
            existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
        }

        this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
        this.setIdPagina((Long) this.getHidIdPagina().getText());

        existeIdSubSesionPag = this.getIdSubSesion() != null;
        existeIdPaginaPag = this.getIdPagina() != null;

        // 1. Pagina nueva - Inicio de transaccion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            if (this.PUEDE_SER_PAGINA_INICIAL) {
                Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
                this.setIdPagina(key);
                this.setIdSubSesion(key);
                this.crearElementoPila();
            }
        }

        // 2. Recarga de la misma pagina por validacion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
            // no se hace nada por ahora
            recarga = true;
            // APLICAR LOGICA AQUI.. ver si es as� realmente..
        }

        // 3. Pagina nueva - hacia adelante en la transaccion
        // Para el caso de las paginas de inicio de transaccion nunca entra
        if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
            this.setIdPagina(key);
            this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
            this.crearElementoPila();
        }

        // 4. Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());

            if (ep.getOrdenamiento() != null) {
                this.setearOrdenamiento();
                this.getElementoPila().setOrdenamiento(null);
            }

            if (this.getCommunicationAccionSocialBean().getListaObraSociales() != null
                    && this.getCommunicationAccionSocialBean().getListaObraSociales().size() > 0
                    && this.getRequestBean1().getAccion() != null
                    && (this.getRequestBean1().getAccion().equals(Constantes.ACCION_AGREGAR)
                    || this.getRequestBean1().getAccion().equals(Constantes.ACCION_MODIFICAR)
                    || this.getRequestBean1().getAccion().equals(Constantes.ACCION_ELIMINAR))) {
                try {
                    this.refrescarTabla();
                } catch (Exception ex) {
                    this.limpiarTabla();
                }
            }

        }
        if (!recarga) {
            this.mostrarEstadoObjetosUsados();
        }
        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
        
        this.getCustomTable().getFiltro().setCantidadPorPagina(5);

        this.habilitarBtnExportar();
    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando se completa el
     * procesamiento de esta petición, si se llamó al método
     * <code>init()</code> (sin tener en cuenta si se trata de la página que se
     * ha procesado o no). Puede personalizar este método para liberar los
     * recursos adquiridos en los métodos
     * <code>init()</code>,
     * <code>preprocess()</code> o
     * <code>prerender()</code> (o durante la ejecución de un controlador de
     * eventos).</p>
     */
    public void destroy() {
    }

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    // CAMBIAR: Constantes que varian segun la pagina.
    // cantidad de objetos administrados por la pagina
    private final int CANTIDAD_OBJETOS = 3;
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Obras Sociales";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminObraSocial";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarObraSocial";
    private final String lnkModificar = "ModificarObraSocial";
    private final String lnkEliminar = "EliminarObraSocial";
    private final String lnkConsultar = "ConsultarObraSocial";
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private Button btnEliminar = new Button();

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Button b) {
        this.btnEliminar = b;
    }
    private Button btnModificar = new Button();

    public Button getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(Button b) {
        this.btnModificar = b;
    }
    private Button btnAgregar = new Button();

    public Button getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(Button b) {
        this.btnAgregar = b;
    }
    private Button btnSeleccionar = new Button();

    public Button getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public void setBtnSeleccionar(Button b) {
        this.btnSeleccionar = b;
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

    private ObjectListDataProvider ldpObraSociales = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpObraSociales() {
        return ldpObraSociales;
    }

    public void setLdpObraSociales(ObjectListDataProvider oldp) {
        this.ldpObraSociales = oldp;
    }
    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }
    private RadioButton radioButton1 = new RadioButton();

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton rb) {
        this.radioButton1 = rb;
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
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar RaddioButton">
    public String getCurrentRow() {
        return tableRowGroup1.getRowKey().getRowId();
    }

    public void setCurrentRow(int row) {
    }
    private Object lastSelected = null;

    public Object getRBSelected() {
        String sv = (String) radioButton1.getSelectedValue();
        return sv.equals(lastSelected) ? sv : null;
    }

    public void setRBSelected(Object selected) {
        if (selected != null) {
            lastSelected = selected;
        }
    }

    private int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar la fila recientemente seleccionada">
    private RowKey rowKeySeleccionado = null;

    public RowKey getRowKeySeleccionado() {
        return rowKeySeleccionado;
    }

    public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
        this.rowKeySeleccionado = rowKeySeleccionado;
    }

    public void guardarOrdenamiento() {
// Juan Pablo
        //this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion()).setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
        this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
    }

    public void setearOrdenamiento() {
// Juan Pablo
        //ElementoPila ep = this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
        //this.getTableRowGroup1().setSortCriteria((SortCriteria[]) ep.getOrdenamiento());
        this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
    }
    /*
     * public void setearCantidadFilasPorPagina() { int cantidadFilas = 10;
     * this.getTableRowGroup1().setRows(cantidadFilas); }
     */

    public Long getPosicionEnTabla(RowKey rk) {
        long inicioPagina = 0;
        long posicionGlobal = 0;
        long posicionEnPagina = 0;
        long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
        long cantRegistros = this.getTableRowGroup1().getRowCount();
        boolean encontrado = false;

        if (rk != null) {
            while (!encontrado && inicioPagina < cantRegistros) {
                this.getTableRowGroup1().setFirst((int) inicioPagina);
                posicionEnPagina = 0;
                while (!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
                    encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
                    if (!encontrado) {
                        posicionEnPagina++;
                        posicionGlobal++;
                    }
                }
                if (!encontrado) {
                    inicioPagina += cantRegistrosPorPagina;
                }
            }
        }
        return new Long(posicionGlobal);
    }

    public RowKey getRowKeyAsociado(Long posicionEnTabla) {
        RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
        return rk;
    }

    public void seleccionarFila(Long posicionGlobal) { 
            lastSelected = posicionGlobal.toString();        
    }

    public Long getInicioPagina(Long posicionGlobal) {
        long inicioPagina = 0;
        long posicionEnPagina = 0;
        long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

        inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
        return new Long(inicioPagina);
    }

    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            // CAMBIAR: Utilizar el ListDataProvider correspondiente
            rk = this.getLdpObraSociales().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }

    private int getCantidadObjetosUsados() {
        return this.getElementoPila().getObjetos().size();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        ObraSocial ObraSocial = (ObraSocial) this.obtenerObjetoDelElementoPila(0, ObraSocial.class);

        Object nombre = this.getTfNombre().getText();

        if (nombre != null && nombre != "") {
            ObraSocial.setNombre(nombre.toString());
        } else {
            ObraSocial.setNombre(null);
        }
        
        FiltroFichaSocial locFiltro = (FiltroFichaSocial) this.getCustomTable().getFiltro();
//        FiltroFichaSocial.setNombre(ObraSocial.getNombre());



        this.getElementoPila().getObjetos().set(0, ObraSocial);

    }

    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new ObraSocial());


        ep.getObjetos().add(ind++, new Integer(0));

        return ep;
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Obtener una instancia por cada objeto manejado en la pagina
        ObraSocial ObraSocial = new ObraSocial();


        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.

        }
        try {
            ObraSocial = (ObraSocial) this.obtenerObjetoDelElementoPila(0, ObraSocial.class);


        } catch (Exception ex) {
            log(CASO_NAVEGACION + "_ReiniciarError:", ex);
            error(NOMBRE_PAGINA + " - Reiniciar: " + ex.getMessage());
        }
        this.getTfNombre().setText(ObraSocial.getNombre());
        
        if (this.getLdpObraSociales().getList() != null){
             Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
             System.out.println("filaSeleccionada :" +filaSeleccionada);
             this.seleccionarFila(filaSeleccionada);
        }

    }

    private void refrescarTabla() throws Exception {
        // CAMBIAR: Segun objeto de busqueda.        
        FiltroFichaSocial locFiltro = (FiltroFichaSocial) this.getCustomTable().getFiltro();
        this.getCommunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
//        locFiltro = this.getCommunicationAccionSocialBean().getRemoteSystemFichaSocial().findListaObraSocial(locFiltro);
        this.getCustomTable().setFiltro(locFiltro);
        this.getCommunicationAccionSocialBean().setListaObraSociales(locFiltro.getListaResultados());
        this.getLdpObraSociales().setList(this.getCommunicationAccionSocialBean().getListaObraSociales());
        this.setRBSelected((new Long(0)).toString());
    }

    private void limpiarTabla() {
        // CAMBIAR: Utilizar el ListDataProvider adecuado.
        this.getLdpObraSociales().getList().clear();
    }

    private void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        ObraSocial ObraSocial = new ObraSocial();

        this.getElementoPila().getObjetos().set(0, ObraSocial);
        this.getElementoPila().getObjetos().set(1, null);

        this.getTfNombre().setText("");

    }

    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpObraSociales();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodos estaticos de la pagina">
    private void crearElementoPila() {
        ElementoPila ep = new ElementoPila();
        ep.setCasoNavegacion(CASO_NAVEGACION);
        ep.setIdPagina(this.getIdPagina());
        ep.setIdSubSesion(this.getIdSubSesion());
        ep.setNombrePagina(NOMBRE_PAGINA);

        ep = this.agregarObjetosAElementoPila(ep);

        this.getSessionBean1().getMgrPilas().addElemento(ep);
    }

    private String prepararParaVolver(String pAccionRetorno) {
        this.getRequestBean1().setAccion(pAccionRetorno);
        String retorno = null;
        ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());

        if (locElementoAnterior != null) {
            this.getSessionBean1().getMgrPilas().removeElemento(this.getElementoPila());

            this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
            this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
            this.getRequestBean1().setElementoPilaPaginaAnt(locElementoAnterior);
            retorno = locElementoAnterior.getCasoNavegacion();
        } else {
            retorno = CASO_NAV_POST_CADUCIDAD;
        }

        return retorno;
    }

    private String prepararCaducidad() {
        // redireccionar a pagina con mensaje de caducidad
        this.getRequestBean1().setCasoNavegacionPostCaducidad(CASO_NAV_POST_CADUCIDAD);
        return CASO_NAV_CADUCIDAD;
    }
// Juan Pablo

    private boolean ultimoElementoPilaDeSubSesion() {
        return this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
    }

    private Object obtenerObjetoDelElementoPila(int posicion, Class tipoClase) {
        Object objeto = null;
        try {
            objeto = this.getElementoPila().getObjetos().get(posicion);
            if (objeto == null) {
                objeto = tipoClase.newInstance();
            }
        } catch (Exception ex) {
        }
        return objeto;
    }

    //Implementación de los métodos controladores de acción
    public String btnBuscar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                this.getCustomTable().resetearEstado();
                this.guardarEstadoObjetosUsados();
                this.refrescarTabla();
                this.guardarOrdenamiento();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());

            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION + "_BuscarError:", ex);
                error(NOMBRE_PAGINA + " - Buscar: " + ex.getMessage());
            }

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnReiniciar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                this.limpiarObjetosUsados();
                this.guardarEstadoObjetosUsados();
                this.getCustomTable().resetearEstado();
                this.limpiarTabla();
                this.guardarOrdenamiento();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());

            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION + "_ReiniciarError:", ex);
                error(NOMBRE_PAGINA + " - Reiniciar: " + ex.getMessage());
            }

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnCancelar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.getCommunicationAccionSocialBean().setListaObraSociales(null);
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnSeleccionar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                    Object obj = this.getLdpObraSociales().getObjects()[index];
                    ObraSocial obraSocial = (ObraSocial) obj;
                    obraSocial = (ObraSocial) this.getCommunicationAccionSocialBean().getRemoteSystemFichaSocial().getObraSocialPorId(obraSocial.getIdObraSocial());
                    getRequestBean1().setObjetoSeleccion(obraSocial);
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_SeleccionarError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
            }

            if (rk != null) {
                ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
                if (locElementoAnterior == null) {
                    return null;
                }
                retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnAgregar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_AgregarError:", ex);
                error(NOMBRE_PAGINA + " - Agregar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());

            this.getElementoPila().setPosicionGlobal(pos.longValue());

            retorno = lnkAgregar;
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnModificar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                    Object obj = this.getLdpObraSociales().getObjects()[index];
                    this.getRequestBean1().setObjetoABM(obj);
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ModificarError:", ex);
                error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            if (rk != null) {
                retorno = lnkModificar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnEliminar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                    Object obj = this.getLdpObraSociales().getObjects()[index];
                    getRequestBean1().setObjetoABM(obj);

                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_EliminarError:", ex);
                error(NOMBRE_PAGINA + " - Eliminar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            if (rk != null) {
                retorno = lnkEliminar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }    

    public String btnSeleccionGrupoObraSociales_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;
        if (ultimo) {

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_SeleccionarGrupoObraSocialError:", ex);
                error(NOMBRE_PAGINA + " - Seleccionar Grupo de Obras Sociales: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminGrupoObraSocial";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnImprimirReporte_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = this.getNroFila(rk.toString());

                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    this.getSessionBean1().setObjetoImpresion(obj);

                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception e) {
                log(CASO_NAVEGACION + "_ImprimirError: ", e);
                error(NOMBRE_PAGINA + " - Imprimir: " + e.getMessage());
            }
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    /**
     * Mines: boton Consultar...
     */
    public String btnConsultar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                    Object obj = this.getLdpObraSociales().getObjects()[index];
                    this.getRequestBean1().setObjetoABM(obj);
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ConsultarError:", ex);
                error(NOMBRE_PAGINA + " - Consultar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            if (rk != null) {
                retorno = lnkConsultar;
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo != null) {
                campo.setText(null);
            }
        } catch (Exception ex) {
        }
    }

    public String btnExportar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getCommunicationAccionSocialBean().getListaObraSociales(), this.getTableRowGroup1(), "Reporte Din\341mico de Obras Sociales");

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_ObrasSociales");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

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

    //Metodo para habilitar el boton exportar cuando corresponda
    public void habilitarBtnExportar() {
        this.btnExportar.setDisabled(true);
        if (this.getCommunicationAccionSocialBean().getListaObraSociales() != null) {
            if (this.getCommunicationAccionSocialBean().getListaObraSociales().isEmpty()) {
                this.btnExportar.setDisabled(true);
            } else {
                this.btnExportar.setDisabled(false);
            }
        }
    }
}
