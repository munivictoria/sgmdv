/*
 * AdministrarSubdivisiones.java
 *
 * Created on 2 de noviembre de 2006, 12:18
 * Copyright Trascender
 */
package muni.catastro.ABMParcela;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import muni.catastro.ABMDeclaracionJurada.*;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.*;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdministrarSubdivisiones extends AbstractPageBean {
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
    private Link link1 = new Link();

    public Link getLink1() {
        return link1;
    }

    public void setLink1(Link l) {
        this.link1 = l;
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
    private Label label4 = new Label();
    private Label label42 = new Label();
    private Label label43 = new Label();
    private Label lblSuperficieDividida = new Label();
    private Label lblSuperficieTotal = new Label();
    private Label lblParcela = new Label();

    public Label getLblParcela() {
        return lblParcela;
    }

    public void setLblParcela(Label lblParcela) {
        this.lblParcela = lblParcela;
    }

    public Label getLblSuperficieDividida() {
        return lblSuperficieDividida;
    }

    public void setLblSuperficieDividida(Label lblSuperficieDividida) {
        this.lblSuperficieDividida = lblSuperficieDividida;
    }

    public Label getLblSuperficieTotal() {
        return lblSuperficieTotal;
    }

    public void setLblSuperficieTotal(Label lblSuperficieTotal) {
        this.lblSuperficieTotal = lblSuperficieTotal;
    }

    public Label getLabel43() {
        return label43;
    }

    public void setLabel43(Label label43) {
        this.label43 = label43;
    }

    public Label getLabel42() {
        return label42;
    }

    public void setLabel42(Label label42) {
        this.label42 = label42;
    }

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }
    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private ObjectListDataProvider ldpSubparcelas = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpSubparcelas() {
        return ldpSubparcelas;
    }

    public void setLdpSubparcelas(ObjectListDataProvider ldpSubparcelas) {
        this.ldpSubparcelas = ldpSubparcelas;
    }
    private TextField tfFechaInscripcion = new TextField();

    public TextField getTfFechaInscripcion() {
        return tfFechaInscripcion;
    }

    public void setTfFechaInscripcion(TextField tfCodigo) {
        this.tfFechaInscripcion = tfCodigo;
    }
    private Button btnGuardar = new Button();
    private Button btnAgregarSubdivision = new Button();
    private Button btnQuitarSubdivision = new Button();

    public Button getBtnAgregarSubdivision() {
        return btnAgregarSubdivision;
    }

    public void setBtnAgregarSubdivision(Button btnAgregarSubdivision) {
        this.btnAgregarSubdivision = btnAgregarSubdivision;
    }

    public Button getBtnQuitarSubdivision() {
        return btnQuitarSubdivision;
    }

    public void setBtnQuitarSubdivision(Button btnQuitarSubdivision) {
        this.btnQuitarSubdivision = btnQuitarSubdivision;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(Button b) {
        this.btnGuardar = b;
    }
    private Button btnCancelar = new Button();

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button b) {
        this.btnCancelar = b;
    }
    private StaticText stSeparador = new StaticText();

    public StaticText getStSeparador() {
        return stSeparador;
    }

    public void setStSeparador(StaticText st) {
        this.stSeparador = st;
    }
    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
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
    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn2 = new TableColumn();
    private TableColumn tableColumn3 = new TableColumn();
    private TableColumn tableColumn4 = new TableColumn();
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tableColumn2) {
        this.tableColumn2 = tableColumn2;
    }

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tableColumn3) {
        this.tableColumn3 = tableColumn3;
    }

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tableColumn4) {
        this.tableColumn4 = tableColumn4;
    }

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tableColumn5) {
        this.tableColumn5 = tableColumn5;
    }

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tableColumn1) {
        this.tableColumn1 = tableColumn1;
    }
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    private RadioButton radioButton1 = new RadioButton();

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton rb) {
        this.radioButton1 = rb;
    }

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
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Vincular a los campos ocultos.
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

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    // CAMBIAR: Constantes que varian segun la pagina.
    // cantidad de objetos administrados por la pagina
    //private final int CANTIDAD_OBJETOS = 2;
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Administrar Subdivisiones";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdministrarSubdivisiones";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private Button btnGenerarObligacionesOSP = new Button();

    public Button getBtnGenerarObligacionesOSP() {
        return btnGenerarObligacionesOSP;
    }

    public void setBtnGenerarObligacionesOSP(Button btnGenerarObligacionesOSP) {
        this.btnGenerarObligacionesOSP = btnGenerarObligacionesOSP;
    }
    private TextField tfCodigo = new TextField();

    public TextField getTfCodigo() {
        return tfCodigo;
    }

    public void setTfCodigo(TextField tfCodigo) {
        this.tfCodigo = tfCodigo;
    }
    private StaticText staticText1 = new StaticText();
    private StaticText staticText2 = new StaticText();
    private StaticText staticText3 = new StaticText();
    private StaticText staticText4 = new StaticText();
    private Script scriptFinal1 = new Script();

    public Script getScriptFinal1() {
        return scriptFinal1;
    }

    public void setScriptFinal1(Script scriptFinal1) {
        this.scriptFinal1 = scriptFinal1;
    }

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText staticText2) {
        this.staticText2 = staticText2;
    }

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText staticText3) {
        this.staticText3 = staticText3;
    }

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText staticText4) {
        this.staticText4 = staticText4;
    }
    private StaticText staticText20 = new StaticText();
    private StaticText staticText21 = new StaticText();

    public StaticText getStaticText21() {
        return staticText21;
    }

    public void setStaticText21(StaticText staticText21) {
        this.staticText21 = staticText21;
    }

    public StaticText getStaticText20() {
        return staticText20;
    }

    public void setStaticText20(StaticText staticText20) {
        this.staticText20 = staticText20;
    }

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    // </editor-fold>

    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public AdministrarSubdivisiones() {
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
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se navega hasta
     * esta p�gina, ya sea directamente mediante un URL o de manera indirecta a
     * trav�s de la navegaci�n de p�ginas. Puede personalizar este m�todo para
     * adquirir recursos que se necesitar�n para los controladores de eventos y
     * m�todos del proceso, sin tener en cuenta si esta p�gina realiza
     * procesamiento de devoluci�n de env�os.</p>
     *
     * <p>Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o,
     * los valores de propiedad de los componentes <strong>no</strong>
     * representan ning�n valor enviado con esta petici�n. En su lugar,
     * representan los valores de propiedades que se guardaron para esta vista
     * cuando se proces�.</p>
     */
    public void init() {
        // Realizar iniciaciones heredadas de la superclase
        super.init();
        // Realizar inicio de aplicaci�n que debe finalizar
        // *antes* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

        // <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
        // Iniciar componentes administrados autom�ticamente
        // *Nota* - esta l�gica NO debe modificarse
        try {
            _init();
        } catch (Exception e) {
            log("AdministrarSubdivisiones Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

    }

    /**
     * <p>M�todo de devoluci�n de llamada al que se llama cuando el �rbol de
     * componentes se ha restaurado, pero antes de que se produzca el
     * procesamiento de cualquier evento. Este m�todo <strong>s�lo</strong> se
     * llamar� en una petici�n de devoluci�n de env�o que est� procesando el
     * env�o de un formulario. Puede personalizar este m�todo para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /**
     * <p>M�todo de devoluci�n de llamada al que se llama justo antes del
     * procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la
     * p�gina que se procesa, no se llamar�, por ejemplo, en una p�gina que ha
     * procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra
     * p�gina. Puede personalizar este m�todo para asignar recursos necesarios
     * para procesar esta p�gina.</p>
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

        // Pagina nueva - Inicio de transaccion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            if (this.PUEDE_SER_PAGINA_INICIAL) {
                Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
                this.setIdPagina(key);
                this.setIdSubSesion(key);
                this.crearElementoPila();
            }
        }

        // Recarga de la misma pagina por validacion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
            // no se hace nada por ahora
            recarga = true;
            // APLICAR LOGICA AQUI.. ver si es as� realmente..
        }

        // Pagina nueva - hacia adelante en la transaccion
        // Para el caso de las paginas de inicio de transaccion nunca entra
        if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
            this.setIdPagina(key);
            this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
            this.crearElementoPila();
        }

        // Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());
        }

        if (!recarga) {
            this.mostrarEstadoObjetosUsados();
        }

        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));

    }

    /**
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se completa el
     * procesamiento de esta petici�n, si se llam� al m�todo
     * <code>init()</code> (sin tener en cuenta si se trata de la p�gina que se
     * ha procesado o no). Puede personalizar este m�todo para liberar los
     * recursos adquiridos en los m�todos
     * <code>init()</code>,
     * <code>preprocess()</code> o
     * <code>prerender()</code> (o durante la ejecuci�n de un controlador de
     * eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new Parcela());
        ep.getObjetos().add(ind++, new ArrayList());//1 lista de subparcelas NUEVAS
        ep.getObjetos().add(ind++, new ArrayList());//2 lista de subparcelas A ELIMINAR

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(0, Parcela.class);

        if (parcela.getIdParcela() == -1) {
            parcela = null;
        }
        this.getElementoPila().getObjetos().set(0, parcela);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        Parcela parcela = null;
        List listaSubparcelasNuevas = new ArrayList();
        List listaSubparcelasAEliminar = new ArrayList();

        // CAMBIAR: Obtener datos del Request y asignarlos a los textField
        //  this.acomodarSeleccionado();
        if (this.getRequestBean1().getObjetoABM() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoABM();
            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.            

            if (seleccionado != null) {
                parcela = (Parcela) seleccionado;
                this.setListaDelCommunication(new ArrayList(parcela.getListaSubParcelas()));
                this.getLdpSubparcelas().setList(this.getListaDelCommunication());
                this.getElementoPila().getObjetos().set(0, parcela);
                this.getElementoPila().getObjetos().set(1, listaSubparcelasNuevas);
                this.getElementoPila().getObjetos().set(2, listaSubparcelasAEliminar);

            }
        }
        //SI AGREGO O QUITO SUBPARCELAS:
        if (this.getRequestBean1().getObjetosSeleccionMultiple() != null) {//si agrego subdivisiones
            ArrayList seleccion = (ArrayList) this.getRequestBean1().getObjetosSeleccionMultiple();
            System.out.println("cantidad de la lista seleccion-- " + seleccion.size());
            listaSubparcelasNuevas = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);

            if (seleccion.get(0) instanceof SubParcela) {
                System.out.println("SELECCION_MULTIPLE TRAE SUBPARCELASS");
                listaSubparcelasNuevas.addAll(this.getRequestBean1().getObjetosSeleccionMultiple());

                this.getListaDelCommunication().addAll(this.getRequestBean1().getObjetosSeleccionMultiple());
                System.out.println("size subp new: " + this.getRequestBean1().getObjetosSeleccionMultiple().size());
            }
            this.getElementoPila().getObjetos().set(1, listaSubparcelasNuevas);
            this.getRequestBean1().setObjeto(null);
        }


        parcela = (Parcela) this.obtenerObjetoDelElementoPila(0, Parcela.class);
        listaSubparcelasNuevas = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        listaSubparcelasAEliminar = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

        if (parcela != null && parcela.getIdParcela() != -1) {
            this.getLblParcela().setText(parcela.toString());
        }

        this.getLdpSubparcelas().setList(this.getListaDelCommunication());

        /*
         * superficie
         */
        double superficieDividida = 0;

        if (this.getListaDelCommunication() != null) {
            for (Iterator it = this.getListaDelCommunication().iterator(); it.hasNext();) {
                SubParcela subParcela = (SubParcela) it.next();
                superficieDividida += subParcela.getSuperficie().doubleValue();
            }
        }
        this.getLblSuperficieDividida().setText(new Double(superficieDividida));
        this.getLblSuperficieTotal().setText(parcela.getSuperficie());

    }
    // </editor-fold>

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
        this.setListaDelCommunication(null);
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

    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo != null) {
                campo.setText(null);
            }
        } catch (Exception ex) {
        }
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
    // </editor-fold>

    private int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
    }

    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            // CAMBIAR: Utilizar el ListDataProvider correspondiente
            rk = this.getLdpSubparcelas().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }

    /*
     * subdivisiones
     */
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpSubparcelas();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationCatastroBean().getListaSubdivisiones();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationCatastroBean().setListaSubdivisiones(lista);
    }

    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {

                this.guardarEstadoObjetosUsados();

                Parcela parcela = (Parcela) this.getElementoPila().getObjetos().get(0);

                /*
                 * Metodos de persistencia: Parcelas a eliminar:
                 */
                List listaSubparcelasAEliminar = (List) this.obtenerObjetoDelElementoPila(2, List.class);
                if (listaSubparcelasAEliminar != null && listaSubparcelasAEliminar.size() > 0) {                    
                    for (Object object : listaSubparcelasAEliminar) {
                        SubParcela locSubParcela = (SubParcela) object;         
                        if (parcela.getListaSubParcelas().contains(locSubParcela)) {
                            System.out.println("la subparcela esta contenida en la lista de la parcela----");
                        }
                        this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().unionSubParcelaria(parcela, locSubParcela);
                        parcela.getListaSubParcelas().remove(locSubParcela);
                    }
                }

                /*
                 * Metodos de persistencia: Nuevas Subparcelas:
                 */
                List listaSubparcelasNuevas = (List) this.obtenerObjetoDelElementoPila(1, List.class);

                //antes de subparcelar, saco la subparcela de la lista de subparcelas de la parcela, sino considera que ya es una subparcela
                //de esta y no me permite subparcelar, luego si se las volvere a asignar:
                if (listaSubparcelasNuevas != null && listaSubparcelasNuevas.size() > 0) {
                    if (parcela.getListaSubParcelas().containsAll(listaSubparcelasNuevas)) {
                        parcela.getListaSubParcelas().removeAll(listaSubparcelasNuevas);
                        System.out.println("elimino las subparcelas a subparcelar que son:--- " + listaSubparcelasNuevas.size());

                    }
                    this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
                    this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().subParcelarParcela(parcela, listaSubparcelasNuevas);  //                    
                    //asigno a la parcela la lista de subparcelas nuevas, luego de usar el metodo subParcelarParcela!
                    parcela.getListaSubParcelas().addAll(listaSubparcelasNuevas);

                }

                this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
                parcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().updateParcela(parcela);

                info("Las Subdivisiones se modificaron exitosamente.");

                this.getElementoPila().getObjetos().set(0, parcela);
                this.getElementoPila().getObjetos().set(1, null);
                this.getElementoPila().getObjetos().set(2, null);
               
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_GuardarError:", ex);
                error(NOMBRE_PAGINA + " - Guardar: " + ex.getMessage());
                ex.printStackTrace();
            }

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnGenerarObligacionesOSP_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(0, Parcela.class);
        List listaParcelasAgregar = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        List listaParcelasEliminar = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

        if (ultimo) {

            try {
                if ((listaParcelasAgregar != null && listaParcelasAgregar.size() > 0) || (listaParcelasEliminar != null && listaParcelasEliminar.size() > 0)) {
                    warn("Para generar Obligaciones OSP debe guardar los cambios realizados.");
                    return null;
                }
                getRequestBean1().setObjetoABM(parcela);

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_EliminarError:", ex);
                error(NOMBRE_PAGINA + " - Eliminar: " + ex.getMessage());
                ex.printStackTrace();
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            retorno = "GenerarObligacionOSP";

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnCancelar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            // CAMBIAR: comentar esta linea y cambiar el retorno
            // cuando la pagina es una de inicio de transaccion
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnAgregarSubdivision_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            try {

                this.guardarEstadoObjetosUsados();

                Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(0, Parcela.class);

                if (parcela == null || parcela.getIdParcela() == -1) {
                    return null;
                }
                if (parcela.getTituloPropiedad() != null && parcela.getTituloPropiedad().getIdTituloPropiedad() != -1) {
                    //this.getBtnAgregarRegMejora().i
                } else {
                    warn("La parcela debe tener T\355tulo de propiedad para ser subdividida.");
                    return null;
                }

                this.getRequestBean1().setObjeto(parcela);
                this.getRequestBean1().setCommunication(this.getListaDelCommunication());

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_AgregarSubdivisionError:", ex);
                error(NOMBRE_PAGINA + " - Agregar Subdivision: " + ex.getMessage());
            }
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            retorno = "SubdividirParcela";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnQuitarSubdivision_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(0, Parcela.class);
        List listaSubParcelasNuevas = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        List listaSubParcelasAEliminar = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

        if (ultimo) {
            RowKey rk = null;
            try {

                rk = this.getSeleccionado();
                if (rk != null) {

                    if (parcela == null || parcela.getIdParcela() == -1) {
                        return null;
                    }
                    int index = getNroFila(rk.toString());
                    // CAMBIAR: Utilizar el ListDataProvider adecuado.
                    Object obj = this.getLdpSubparcelas().getObjects()[index];
                    SubParcela subParcela = (SubParcela) obj;

                    /*
                     * En caso de ser una subdivision nueva la saco de la lista
                     * de subdivisiones nuevas sino lo agrego a la lista de
                     * subdivisiones a unir despues en el guardar
                     */
                    if (subParcela.getIdParcela() != -1) {
                        listaSubParcelasAEliminar.add(subParcela);
                    } else {
                        listaSubParcelasNuevas.remove(subParcela);
                    }

                    this.getListaDelCommunication().remove(subParcela);
                    this.getObjectListDataProvider().setList(this.getListaDelCommunication());

                    double superficieDividida = (Double) this.getLblSuperficieDividida().getText();
                    superficieDividida -= subParcela.getSuperficie();
                    this.getLblSuperficieDividida().setText(superficieDividida);

                    //  this.getElementoPila().getObjetos().set(0, parcela);
                    this.getElementoPila().getObjetos().set(1, listaSubParcelasNuevas);
                    this.getElementoPila().getObjetos().set(2, listaSubParcelasAEliminar);

                    info("La subparcela se elimin\363 exitosamente.");
                }
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_EliminarSubparcelaError:", ex);
                error(NOMBRE_PAGINA + " - Eliminar Subparcela: " + ex.getMessage());
                ex.printStackTrace();
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
}
