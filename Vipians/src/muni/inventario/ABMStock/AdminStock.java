/*
 * AdminStock.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */
package muni.inventario.ABMStock;

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
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.presentacion.navegacion.ElementoPila;
import java.util.ArrayList;
import javax.faces.FacesException;
import com.sun.rave.web.ui.component.Script;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;
import jasper.ConstantesReportes;
import javax.faces.context.FacesContext;
import muni.CommunicationMesaEntradaBean;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;
import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminStock extends AbstractPageBean {

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
    private Bien bienSeleccionado = null;
    private Deposito depositoSeleccionado = null;

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    // CAMBIAR: Constantes que varian segun la pagina.
    // Deposito a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administraci\363n de Stocks";
    // Deposito del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminStock";
    // Deposito del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // Deposito del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar
    private final String lnkAgregar = "AgregarStock";
    private final String lnkModificar = "ModificarStock";
    private final String lnkEliminar = "EliminarStock";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        
        this.habilitarBtnExportar();
    }
    protected PanelGroup pgParametros = new PanelGroup();

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

    public void setPage1(Page pPage1) {
        this.page1 = pPage1;
    }
    private Html html1 = new Html();

    public Html getHtml1() {
        return html1;
    }

    public void setHtml1(Html pHtml1) {
        this.html1 = pHtml1;
    }
    private Head head1 = new Head();

    public Head getHead1() {
        return head1;
    }

    public void setHead1(Head pHead1) {
        this.head1 = pHead1;
    }
    private Body body1 = new Body();

    public Body getBody1() {
        return body1;
    }

    public void setBody1(Body pBody1) {
        this.body1 = pBody1;
    }
    private Form form1 = new Form();

    public Form getForm1() {
        return form1;
    }

    public void setForm1(Form pForm1) {
        this.form1 = pForm1;
    }
    private ObjectListDataProvider ldpStock = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpStock() {
        return ldpStock;
    }

    public void setLdpStock(ObjectListDataProvider pOldpStock) {
        this.ldpStock = pOldpStock;
    }
    private StaticText stTitulo = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText pStTitulo) {
        this.stTitulo = pStTitulo;
    }
    private Label lblDeposito = new Label();

    public Label getLblDeposito() {
        return lblDeposito;
    }

    public void setLblDeposito(Label pLblDeposito) {
        this.lblDeposito = pLblDeposito;
    }
    private Label lblBien = new Label();

    public Label getLblBien() {
        return lblBien;
    }

    public void setLblBien(Label pLblBien) {
        this.lblBien = pLblBien;
    }
 protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(HtmlAjaxCommandButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }
 
    private StaticText stColBien = new StaticText();

    public StaticText getStColBien() {
        return stColBien;
    }

    public void setStColBien(StaticText pStColBien) {
        this.stColBien = pStColBien;
    }
    private StaticText stColCantidad = new StaticText();

    public StaticText getStColCantidad() {
        return stColCantidad;
    }

    public void setStColCantidad(StaticText pStColCantidad) {
        this.stColCantidad = pStColCantidad;
    }
    private Button btnCancelar = new Button();

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button pBtnCancelar) {
        this.btnCancelar = pBtnCancelar;
    }
    private Button btnSeleccionarBien = new Button();

    public Button getBtnSeleccionarBien() {
        return btnSeleccionarBien;
    }

    public void setBtnSeleccionarBien(Button pBtnSeleccionarBien) {
        this.btnSeleccionarBien = pBtnSeleccionarBien;
    }
    private Button btnLimpiarBien = new Button();

    public Button getBtnLimpiarBien() {
        return btnLimpiarBien;
    }

    public void setBtnLimpiarBien(Button pBtnLimpiarBien) {
        this.btnLimpiarBien = pBtnLimpiarBien;
    }
    private Button btnSeleccionarDeposito = new Button();

    public Button getBtnSeleccionarDeposito() {
        return btnSeleccionarDeposito;
    }

    public void setBtnSeleccionarDeposito(Button pBtnSeleccionarDeposito) {
        this.btnSeleccionarDeposito = pBtnSeleccionarDeposito;
    }
    private Button btnLimpiarDeposito = new Button();

    public Button getBtnLimpiarDeposito() {
        return btnLimpiarDeposito;
    }

    public void setBtnLimpiarDeposito(Button pBtnLimpiarDeposito) {
        this.btnLimpiarDeposito = pBtnLimpiarDeposito;
    }
    private MessageGroup messageGroup = new MessageGroup();

    public MessageGroup getMessageGroup() {
        return messageGroup;
    }

    public void setMessageGroup(MessageGroup pMessageGroup) {
        this.messageGroup = pMessageGroup;
    }
    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table pTable1) {
        this.table1 = pTable1;
    }
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup pTableRowGroup1) {
        this.tableRowGroup1 = pTableRowGroup1;
    }
    private HiddenField hidIdPagina = new HiddenField();

    public HiddenField getHidIdPagina() {
        return hidIdPagina;
    }

    public void setHidIdPagina(HiddenField pHidIdPagina) {
        this.hidIdPagina = pHidIdPagina;
    }
    private HiddenField hidIdSubSesion = new HiddenField();

    public HiddenField getHidIdSubSesion() {
        return hidIdSubSesion;
    }

    public void setHidIdSubSesion(HiddenField pHidIdSubSession) {
        this.hidIdSubSesion = pHidIdSubSession;
    }
    private Link link1 = new Link();

    public Link getLink1() {
        return link1;
    }

    public void setLink1(Link pLink1) {
        this.link1 = pLink1;
    }
    private TextField tfDeposito = new TextField();

    public TextField getTfDeposito() {
        return tfDeposito;
    }

    public void setTfDeposito(TextField pTfDeposito) {
        this.tfDeposito = pTfDeposito;
    }
    private TextField tfBien = new TextField();

    public TextField getTfBien() {
        return tfBien;
    }

    public void setTfBien(TextField pTfBien) {
        this.tfBien = pTfBien;
    }
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pGroupPanel1) {
        this.groupPanel1 = pGroupPanel1;
    }
    private Button btnEliminar = new Button();

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Button pBtnEliminar) {
        this.btnEliminar = pBtnEliminar;
    }
    private Button btnModificar = new Button();

    public Button getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(Button pBtnModificar) {
        this.btnModificar = pBtnModificar;
    }
    private Button btnAgregar = new Button();

    public Button getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(Button pBtnAgregar) {
        this.btnAgregar = pBtnAgregar;
    }
    private Button btnSeleccionar = new Button();

    public Button getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public void setBtnSeleccionar(Button pBtnSeleccionar) {
        this.btnSeleccionar = pBtnSeleccionar;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText pStaticText2) {
        this.staticText2 = pStaticText2;
    }
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText pStaticText6) {
        this.staticText6 = pStaticText6;
    }
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText pStaticText8) {
        this.staticText8 = pStaticText8;
    }

    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn pTableColumn1) {
        this.tableColumn1 = pTableColumn1;
    }
    private RadioButton radioButton1 = new RadioButton();

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton pRadioButton1) {
        this.radioButton1 = pRadioButton1;
    }
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn pTableColumn2) {
        this.tableColumn2 = pTableColumn2;
    }
    private StaticText stColDeposito = new StaticText();

    public StaticText getStColDeposito() {
        return stColDeposito;
    }

    public void setStColDeposito(StaticText pStColDeposito) {
        this.stColDeposito = pStColDeposito;
    }
    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn pTableColumn3) {
        this.tableColumn3 = pTableColumn3;
    }
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn pTableColumn4) {
        this.tableColumn4 = pTableColumn4;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText pTableColumn3) {
        this.staticText3 = pTableColumn3;
    }
  protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnReiniciar() {
        return btnReiniciar;
    }

    public void setBtnReiniciar(HtmlAjaxCommandButton btnReiniciar) {
        this.btnReiniciar = btnReiniciar;
    }
  
    private Script scriptFinal1 = new Script();

    public Script getScriptFinal1() {
        return scriptFinal1;
    }

    public void setScriptFinal1(Script pScriptFinal1) {
        this.scriptFinal1 = pScriptFinal1;
    }
    private Script scriptValidador = new Script();

    public Script getScriptValidador() {
        return scriptValidador;
    }

    public void setScriptValidador(Script scriptValidador) {
        this.scriptValidador = scriptValidador;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText pStaticText4) {
        this.staticText4 = pStaticText4;
    }
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }
    private Button btnExportar = new Button();

    public Button getBtnExportar() {
        return btnExportar;
    }

    public void setBtnExportar(Button btnExportar) {
        this.btnExportar = btnExportar;
    }
    private Button btnConsultar = new Button();

    public Button getBtnConsultar() {
        return btnConsultar;
    }

    public void setBtnConsultar(Button pBtnConsultar) {
        this.btnConsultar = pBtnConsultar;
    }

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de página.</p>
     */
    public AdminStock() {
    }

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
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
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
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
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
            log("AdminStock Initialization Failure", e);
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
            if (this.getListaDelCommunication() != null
                    && this.getListaDelCommunication().size() > 0
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
        this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1());
        
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

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new Stock());
        ep.getObjetos().add(ind++, new Bien());
        ep.getObjetos().add(ind++, new Deposito());

        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        Stock locStock = (Stock) this.obtenerObjetoDelElementoPila(0, Stock.class);

        Bien locBien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
        Deposito locDeposito = (Deposito) this.obtenerObjetoDelElementoPila(2, Deposito.class);

        if (locBien.getIdBien() == -1) {
            locBien = null;
        }
        locStock.setBien(locBien);

        if (locDeposito.getIdDeposito() == -1l) {
            locDeposito = null;
        }
        locStock.setDeposito(locDeposito);

        this.getElementoPila().getObjetos().set(0, locStock);
        this.getElementoPila().getObjetos().set(1, locBien);
        this.getElementoPila().getObjetos().set(2, locDeposito);

    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        Stock locStock = (Stock) this.obtenerObjetoDelElementoPila(0, Stock.class);
        Bien locBien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
        Deposito locDeposito = (Deposito) this.obtenerObjetoDelElementoPila(2, Deposito.class);

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object locSeleccionado = this.getRequestBean1().getObjetoSeleccion();
            if (locSeleccionado instanceof Bien) {
                if (locSeleccionado != null) {
                    locBien = (Bien) locSeleccionado;
                    this.getElementoPila().getObjetos().set(1, locBien);
                }
            }

            if (locSeleccionado instanceof Deposito) {
                if (locSeleccionado != null) {
                    locDeposito = (Deposito) locSeleccionado;
                    this.getElementoPila().getObjetos().set(2, locDeposito);
                }
            }
        }

        if (this.getElementoPila().getObjetos() != null && !this.getElementoPila().getObjetos().isEmpty()) {
            locStock = (Stock) this.obtenerObjetoDelElementoPila(0, Stock.class);
            locBien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
            locDeposito = (Deposito) this.obtenerObjetoDelElementoPila(2, Deposito.class);
        }

        if (locBien != null && locBien.getIdBien() != -1) {
            this.getTfBien().setText(locBien.toString());
        } else {
            this.getTfBien().setText(null);
        }

        if (locDeposito != null && locDeposito.getIdDeposito() != -1l) {
            this.getTfDeposito().setText(locDeposito.toString());
        } else {
            this.getTfDeposito().setText(null);
        }
        
        if (this.getLdpStock().getList() != null){
             Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
             System.out.println("filaSeleccionada :" +filaSeleccionada);
             this.seleccionarFila(filaSeleccionada);
        }
    }

    private void refrescarTabla() throws Exception {
        // CAMBIAR: Segun objeto de busqueda.
        Bien locBien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
        Deposito locDeposito = (Deposito) this.obtenerObjetoDelElementoPila(2, Deposito.class);

        if (locBien.getIdBien() == -1) {
            locBien = null;
        }
        if (locDeposito.getIdDeposito() == -1l) {
            locDeposito = null;
        }
        // CAMBIAR: Utilizar el ListDataProvider y EJBClient adecuados.
        try {
            this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
            this.setListaDelCommunication((ArrayList) this.getCommunicationComprasBean().getRemoteSystemStock().findListaStock(locBien, locDeposito));
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        } catch (Exception e) {
            e.printStackTrace();

        }
        this.setRBSelected((new Long(0)).toString());
    }

    private void limpiarObjetosUsados() {
        for (int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
            this.getElementoPila().getObjetos().set(i, null);
        }

        // CAMBIAR: Limpiar los textField y dropDown
        this.getTfBien().setText("");
        this.getTfDeposito().setText("");
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpStock();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaStocks();
    }

    private void setListaDelCommunication(ArrayList pListaStocks) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaStocks(pListaStocks);
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

    private void acomodarSeleccionado() {
        Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
        if (seleccionado != null) {
            int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
            this.getElementoPila().getObjetos().set(posicion, seleccionado);
        }
    }

    private int getCantidadObjetosUsados() {
        return this.getElementoPila().getObjetos().size();
    }

    private void limpiarTabla() {
        this.getObjectListDataProvider().getList().clear();
    }

//    public void setPropiedadesTabla() {
//        String width = null;
//        int cantidadFilas = this.getApplicationBean1().getCantidadFilasTablasAdmin().intValue();
//        
//        this.getTableRowGroup1().setRows(cantidadFilas);
//        
//        this.getTable1().setWidth(width);
//        this.getTable1().setClearSortButton(true);
//        this.getTable1().setDeselectMultipleButton(false);
//        this.getTable1().setDeselectSingleButton(false);
//        this.getTable1().setPaginateButton(false);
//        this.getTable1().setPaginationControls(true);
//        this.getTable1().setSelectMultipleButton(false);
//        this.getTable1().setSortPanelToggleButton(false);
//        this.getTable1().setTitle(null);
//    }
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
        this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
    }

    public void setearOrdenamiento() {
        this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
    }

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
            rk = this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }
    // </editor-fold>

    // </editor-fold>
    public Bien getBienSeleccionado() {
        if (this.bienSeleccionado == null) {
            this.bienSeleccionado = new Bien();
        }
        return this.bienSeleccionado;
    }

    public void setBienSeleccionado(Bien pBienSeleccionado) {
        this.bienSeleccionado = pBienSeleccionado;
    }

    public String btnSeleccionarBien_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, Bien.class));
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminBien";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarDeposito_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(2, Deposito.class));
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminDeposito";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnBuscar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {
                this.guardarEstadoObjetosUsados();
                this.refrescarTabla();
                this.guardarOrdenamiento();
                Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
                this.getElementoPila().setPosicionGlobal(pos.longValue());
                this.getRequestBean1().setAccion(Constantes.ACCION_BUSCAR);
            } catch (Exception ex) {
                this.limpiarTabla();
                log(CASO_NAVEGACION + "_BuscarError:", ex);
                error(NOMBRE_PAGINA + " - Buscar: " + ex.getMessage());
                this.getRequestBean1().setAccion(Constantes.ACCION_RSLT_ERROR);
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
                this.limpiarTabla();
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
            this.setListaDelCommunication(null);
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
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    getRequestBean1().setObjetoSeleccion(obj);

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
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    Stock locStock = (Stock) obj;
                    this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
                    locStock = this.getCommunicationComprasBean().getRemoteSystemStock().getStockByID(locStock.getIdStock());
                    this.getRequestBean1().setObjetoABM(locStock);
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
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    Stock locStock = (Stock) obj;

                    this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
                    locStock = this.getCommunicationComprasBean().getRemoteSystemStock().getStockByID(locStock.getIdStock());

                    getRequestBean1().setObjetoABM(locStock);
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

    public String btnConsultar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;
            try {
                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    Stock locStock = (Stock) obj;

                    this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
                    locStock = this.getCommunicationComprasBean().getRemoteSystemStock().getStockByID(locStock.getIdStock());

                    this.getRequestBean1().setObjetoABM(locStock);
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
                retorno = "ConsultarStock";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarBien_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.getElementoPila().getObjetos().set(1, null);
            this.getTfBien().setText(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarDeposito_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.getElementoPila().getObjetos().set(2, null);
            this.getTfDeposito().setText(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
         public String btnExportar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Stocks");

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Stocks");
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
        if (getListaDelCommunication() != null) {
            if (getListaDelCommunication().isEmpty()) {
                this.btnExportar.setDisabled(true);
            } else {
                this.btnExportar.setDisabled(false);
            }
        }
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
        return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
    }
}