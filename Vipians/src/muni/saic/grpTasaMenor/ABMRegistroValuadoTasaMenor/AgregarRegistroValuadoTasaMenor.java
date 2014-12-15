/*
 * AgregarValorMedidor.java
 *
 * Created on 1 de noviembre de 2006, 14:27
 * Copyright Trascender SRL
 */
package muni.saic.grpTasaMenor.ABMRegistroValuadoTasaMenor;

import muni.saic.grpOSP.ABMValorMedidor.*;
import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
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
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.presentacion.navegacion.ElementoPila;
import javax.faces.FacesException;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.transients.Periodo;
import java.util.ArrayList;
import javax.faces.convert.DateTimeConverter;
import com.sun.rave.web.ui.component.Message;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.taglib.HtmlTag;
import com.sun.rave.web.ui.taglib.TableColumnTag;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.RegistroValuadoTasaMenor;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import java.util.Calendar;
import java.util.Date;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AgregarRegistroValuadoTasaMenor extends AbstractPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
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
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Agregar Medici\363n del Medidor";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarValorMedidor";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        this.CrearTablaDinamica();

        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }

        dateTimeConverter1.setPattern("dd/MM/yyyy");
        dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Buenos_Aires"));
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
    private FacesContext context = FacesContext.getCurrentInstance();
    private Application application = context.getApplication();
    private ELContext elContext = context.getELContext();
    private ExpressionFactory elFactory = context.getApplication().getExpressionFactory();
    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }
    private Button btnGuardar = new Button();

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
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    private TextField tfPeriodo = new TextField();

    public TextField getTfPeriodo() {
        return tfPeriodo;
    }

    public void setTfPeriodo(TextField tf) {
        this.tfPeriodo = tf;
    }
    private TextField tfCalle = new TextField();

    public TextField getTfCalle() {
        return tfCalle;
    }

    public void setTfCalle(TextField tf) {
        this.tfCalle = tf;
    }
    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
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
    private ObjectListDataProvider ldpRegistroValuado = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpRegistroValuado() {
        return ldpRegistroValuado;
    }

    public void setLdpRegistroValuado(ObjectListDataProvider ldpRegistroValuado) {
        this.ldpRegistroValuado = ldpRegistroValuado;
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
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }
    private TextField textField1 = new TextField();

    public TextField getTextField1() {
        return textField1;
    }

    public void setTextField1(TextField tf) {
        this.textField1 = tf;
    }
    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tableColumn7) {
        this.tableColumn7 = tableColumn7;
    }
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }
    private TextField tfConsumo = new TextField();

    public TextField getTfConsumo() {
        return tfConsumo;
    }

    public void setTfConsumo(TextField tfConsumo) {
        this.tfConsumo = tfConsumo;
    }
    private TextField tfLecturaAnterior = new TextField();

    public TextField getTfLecturaAnterior() {
        return tfLecturaAnterior;
    }

    public void setTfLecturaAnterior(TextField tfLecturaAnterior) {
        this.tfLecturaAnterior = tfLecturaAnterior;
    }
    private TextField tfLectura = new TextField();

    public TextField getTfLectura() {
        return tfLectura;
    }

    public void setTfLectura(TextField tf) {
        this.tfLectura = tf;
    }
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText staticText6) {
        this.staticText6 = staticText6;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private Message message1 = new Message();

    public Message getMessage1() {
        return message1;
    }

    public void setMessage1(Message m) {
        this.message1 = m;
    }
    private StaticText mensaje = new StaticText();

    public StaticText getMensaje() {
        return mensaje;
    }

    public void setMensaje(StaticText mensaje) {
        this.mensaje = mensaje;
    }
    private Message message2 = new Message();

    public Message getMessage2() {
        return message2;
    }

    public void setMessage2(Message m) {

        this.message2 = m;
    }
    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tableColumn8) {
        this.tableColumn8 = tableColumn8;
    }
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
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
    private TextField tfServicioOSP = new TextField();

    public TextField getTfServicioOSP() {
        return tfServicioOSP;
    }

    public void setTfServicioOSP(TextField tf) {
        this.tfServicioOSP = tf;
    }
    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
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
    public AgregarRegistroValuadoTasaMenor() {
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
    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
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
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
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
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
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
            log("AgregarValorMedidor Initialization Failure", e);
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
        boolean cargarTabla = false;

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

                cargarTabla = true;
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

            cargarTabla = true;
        }

        // 4. Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());
        }

        if (!recarga) {
            try {

                this.mostrarEstadoObjetosUsados();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (cargarTabla) {
            try {
                this.refrescarTabla();

            } catch (Exception ex) {
                ex.printStackTrace();
                this.limpiarTabla();
            }
        }

        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
        // Si no hay ninguna obligaci�n, deshabilito el bot�n Guardar
        if (this.getListaDelCommunication() == null || this.getListaDelCommunication().isEmpty()) {
            this.getBtnGuardar().setDisabled(true);
        }

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
        ep.getObjetos().add(ind++, null);   // 0 Periodo
        ep.getObjetos().add(ind++, null);   // 1 PlantillaDocumentoTasaMenor
        ep.getObjetos().add(ind++, null);   // 2 Persona
        ep.getObjetos().add(ind++, new ArrayList());    // 

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
//        ServicioOSP servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
//        Calle calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
//        Periodo periodo = (Periodo) this.obtenerObjetoDelElementoPila(ind++, Periodo.class);
//        ArrayList medicionesCargadas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
//        String codigoMedidor = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
//
//        
//        this.getObjectListDataProvider().commitChanges();
//        medicionesCargadas = (ArrayList) this.getObjectListDataProvider().getList();
//        if (medicionesCargadas.size()<=0) medicionesCargadas = null;
//        this.setListaDelCommunication(medicionesCargadas);
//        
//        ind = 0;
//        this.getElementoPila().getObjetos().set(ind++, servicioOSP);
//        this.getElementoPila().getObjetos().set(ind++, calle);
//        this.getElementoPila().getObjetos().set(ind++, periodo);
//        this.getElementoPila().getObjetos().set(ind++, medicionesCargadas);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;

        Periodo periodo = null;
        PlantillaDocumentoTasaMenor plantillaDocumentoTasaMenor = null;
        Persona persona = null;

//        ServicioOSP servicioOSP = null;
//        Calle calle = null;
//        Periodo periodo = null;
//        ArrayList medicionesCargadas = null;
//        String codigoMedidor = null;

        // Para tomar varios objetos pasados desde la p�gina de AdminRegistroValuadoTasaMenor
        ArrayList objetosPasados = null;

        this.acomodarSeleccionado();

        // CAMBIAR: Obtener datos del Request y asignarlos a los textField
//        if (this.getRequestBean1().getObjetoABM() != null) {
//            
//            objetosPasados = (ArrayList) this.getRequestBean1().getObjetoABM();
//
//            ind = 0 ;
//            calle = (Calle) objetosPasados.get(ind++);
//            periodo = (Periodo) objetosPasados.get(ind++);
//            servicioOSP = (ServicioOSP) objetosPasados.get(ind++);
//            codigoMedidor = (String) objetosPasados.get(ind++);
//
//            ind = 0;
//            this.getElementoPila().getObjetos().set(ind++, servicioOSP);
//            this.getElementoPila().getObjetos().set(ind++, calle);
//            this.getElementoPila().getObjetos().set(ind++, periodo);
//            this.getElementoPila().getObjetos().set(ind++, medicionesCargadas);
//            this.getElementoPila().getObjetos().set(ind++, codigoMedidor);
//
//        }
//        
//        ind = 0;
//        servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
//        calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
//        periodo = (Periodo) this.obtenerObjetoDelElementoPila(ind++, Periodo.class);
//        medicionesCargadas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
//        codigoMedidor = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
//
//        if(this.getListaDelCommunication() != null && this.getListaDelCommunication().size() > 0){
//            medicionesCargadas = this.getListaDelCommunication();
//        }
//
//        this.getTfServicioOSP().setText(servicioOSP.toString());
//        this.getTfCalle().setText(calle.toString());
//        this.getTfPeriodo().setText(periodo.getNombrePeriodoReducido());
//
//        
//        this.getObjectListDataProvider().setList(medicionesCargadas);
//        this.setListaDelCommunication(medicionesCargadas);
    }

    private void refrescarTabla() throws Exception {
        // CAMBIAR: Segun objeto de busqueda.
//        try {
//            int ind = 0;
//            ServicioOSP servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
//            Calle calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
//            Periodo periodo = (Periodo) this.obtenerObjetoDelElementoPila(ind++, Periodo.class);
//            String codigoMedidor = (String) this.obtenerObjetoDelElementoPila(4, String.class);
//
//            if (periodo != null && periodo.getIdPeriodo() == -1) {
//                periodo = null;
//            }
//            
//            if (servicioOSP != null && servicioOSP.getIdTipoAlicuota() == -1) {
//                servicioOSP = null;
//            }
//            if (calle != null && calle.getIdCalle() == -1) {
//                calle = null;
//            }
//            
//
//            // CAMBIAR: Utilizar el EJBClient adecuado.
//            this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
//            this.setListaDelCommunication((ArrayList) this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getListaNuevasMedidasServiciosOSP(calle, periodo, servicioOSP, codigoMedidor));
//            for(int i = 0; i < this.getListaDelCommunication().size(); i++){
//            ValorMedidor locValorMedidor = (ValorMedidor) this.getListaDelCommunication().get(i);
//        }
//            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            throw e;
//        }
    }
    //comment

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpRegistroValuado();
    }

    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationSAICBean().getListaRegistroValuadoTasaMenor();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationSAICBean().setListaRegistroValuadoTasaMenor(lista);
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

    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo != null) {
                campo.setText(null);
            }
        } catch (Exception ex) {
        }
    }

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
        long cantFilas = this.getTableRowGroup1().getRowCount();

        if (cantFilas > 0) {
            // si hay al menos una fila
            if (posicionGlobal.longValue() >= cantFilas) {
                // si elimine la ultima fila, me posiciono en la anterior
                posicionGlobal = new Long(cantFilas - 1);
            };

            int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
            this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
            lastSelected = new Long(index).toString();
        }
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
    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            try {
                this.guardarEstadoObjetosUsados();
                Validador v = new Validador();
                // Envio toda la lista de Mediciones al m�todo. Este toma la lista y las graba una por una..
                ArrayList medicionesCargadas = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
                UIComponent[] positivos = new UIComponent[medicionesCargadas.size()];
                String[] nomPositivos = new String[medicionesCargadas.size()];

                boolean error = false;
                String msgFecha = "Alguna Fecha es Superior a la Actual. ";
                String msgPositivo = "No se Pueden Ingresar Valores Negativos. ";

                for (int i = 0; i < medicionesCargadas.size(); i++) {
                    ValorMedidor locValorMedidor = (ValorMedidor) medicionesCargadas.get(i);
                    if (!v.esPositivo(locValorMedidor.getMontoImponible().toString())) {
                        error = true;
                    }
                }
                if (error) {
                    v.getErrores().add(msgPositivo);
                }

                error = false;
                Calendar fecha = Calendar.getInstance();

                for (int i = 0; i < medicionesCargadas.size(); i++) {
                    ValorMedidor locValorMedidor = (ValorMedidor) medicionesCargadas.get(i);
                    if (locValorMedidor.getFecha().after(fecha.getTime())) {
                        error = true;
                    }
                }
                if (error) {
                    v.getErrores().add(msgFecha);
                }

                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }
                // CAMBIAR: Utilizar el EJBClient adecuado.
                this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
                this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().addListaValorMedidor(medicionesCargadas);
                this.getRequestBean1().setRespuestaABM(null);

                if (medicionesCargadas.size() == 1) {
                    info("La Medici\363n del Medidor se agreg\363 exitosamente.");
                } else {
                    info("Las Mediciones de los Medidores se agregaron exitosamente.");
                }

                this.setListaDelCommunication(null);

                retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);

            } catch (NumberFormatException e) {
                log(CASO_NAVEGACION + "_GuardarError:", e);
                error(NOMBRE_PAGINA + " - Guardar: " + e.getMessage());
            } catch (Exception ex) {
                if (ex instanceof TrascenderException) {
                    int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) {
                        retorno = null;
                    } else {
                        retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
                    }
                }
                log(CASO_NAVEGACION + "_GuardarError:", ex);
                error(NOMBRE_PAGINA + " - Guardar: " + ex.getMessage());
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

            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public void CrearTablaDinamica() {
        tableRowGroup1.setId("tableRowGroup1");
        tableRowGroup1.setSourceData(ldpRegistroValuado);
        tableRowGroup1.setSourceVar("currentRow");
//        tableRowGroup1.setValueBinding("sourceData", getApplication().
//              createValueBinding("#{Page1.tripDataProvider}"));
//        tableRowGroup1.setValueExpression("sourceData", 
//                getValueExpresion("#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.ldpRegistroValuado}",
//                Object.class));
        

        ArrayList objetosPasados = (ArrayList) this.getRequestBean1().getObjetoABM();
        Periodo periodo = (Periodo) objetosPasados.get(0);
        PlantillaDocumentoTasaMenor plantilla = (PlantillaDocumentoTasaMenor) objetosPasados.get(1);
        Persona persona = (Persona) objetosPasados.get(2);
        ArrayList listaRegistrosTasaMenor = null;

        try {
            this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
//            Comentado
//            listaRegistrosTasaMenor = (ArrayList) this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().generarRegistrosTasaMenor(persona, periodo, plantilla);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ind = 0;
        System.out.println("--> SIZE " + listaRegistrosTasaMenor.size());
//        ArrayList<RegistroValuadoTasaMenor> lista = new ArrayList();
//        for (Object object : listaRegistrosTasaMenor) {
//            RegistroValuadoTasaMenor re = (RegistroValuadoTasaMenor) object;
//            re.setFecha(new Date());
//            lista.add(re);
//
//            System.out.println("--> Posicion " + ind++ + ((RegistroValuadoTasaMenor) object).getFecha());
//        }



        // -----------Columna fijas--------------
        TableColumn tableColumnFecha = new TableColumn();
//        application.addComponent(TableColumn.CURRENT_COMPONENT, TableColumn.class.getName());
//        TableColumn tableColumnFecha = (TableColumn) application.createComponent(getValueExpresion("#{currentRow.value['plantillaDocTasaMenor']}", Object.class), context, TableColumn.CURRENT_COMPONENT);
        tableColumnFecha.setId("tcFecha");
        tableColumnFecha.setHeaderText("Fecha");
        tableColumnFecha.setSort("plantillaDocTasaMenor");
        tableColumnFecha.setWidth("20");

        StaticText stFecha = new StaticText();
        stFecha.setId("stFecha");
        stFecha.setStyleClass("label");
        stFecha.setValueBinding("text", application.createValueBinding("#{currentRow.value['fecha']}"));
        
//        ValueExpression ve = getValueExpresion("#{currentRow.value['plantillaDocTasaMenor']}", PlantillaDocumentoTasaMenor.class);
//        stFecha.setValueExpression("text", ve);

        tableColumnFecha.getChildren().add(stFecha);
        tableRowGroup1.getChildren().add(tableColumnFecha);
        
        // --------------------------------------

        this.getObjectListDataProvider().setList(listaRegistrosTasaMenor);
        this.setListaDelCommunication(listaRegistrosTasaMenor);

//RegistroValuadoTasaMenor r = null;
//r.


//        PlantillaAtributoDinamico atributo1 = plantilla.getListaPlantillasRegistroValuado().get(0);
//
//        tableColumn3.setId("tableColumn3");
//        tableColumn3.setHeaderText(atributo1.getNombre());
//        tableColumn3.setSort("nombre");
//        tableColumn3.setWidth("20");

//        StringBuffer atributoNombre = new StringBuffer();
//        atributoNombre.append(atributo1.getNombre());
//        atributoNombre.
//        String atributoNombre = atributo1.getNombre().replace(" ", "");
//        System.out.println("NOMBRE " + atributoNombre);
//        TextField textField = new TextField();
//        textField.setId("tf" + atributoNombre);
//        textField.setText("#{currentRow.value['nombre']}");
//        tableColumn3.getChildren().add(textField);
//        tableRowGroup1.getChildren().add(tableColumn3);

//        staticText5.setId("staticText5");
//        staticText5.setText("#{currentRow.value['nombre']}");


        table1.getChildren().add(tableRowGroup1);

    }

    private ValueExpression getValueExpresion(String pValue, Class<?> pClase) {
        ValueExpression valueExpresion = application.getExpressionFactory().createValueExpression(
                elContext, pValue, pClase);
        return valueExpresion;
    }
    
    
}
