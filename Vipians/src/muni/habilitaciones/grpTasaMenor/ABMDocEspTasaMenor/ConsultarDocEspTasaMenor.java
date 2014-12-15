/*
 * ConsultarDocEspTasaMenor.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */
/**
 * Mines de Modificar
 */
package muni.habilitaciones.grpTasaMenor.ABMDocEspTasaMenor;

import java.util.ArrayList;

import javax.faces.FacesException;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class ConsultarDocEspTasaMenor extends AbstractPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a campos ocultos.
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
    private final String NOMBRE_PAGINA = "Consultar Obligaci\363n: Tasa Menor";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ConsultarDocEspTasaMenor";
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

        if (this.getListaDelCommunicationTabla5() != null) {
            this.getObjectListDataProviderTabla5().setList(this.getListaDelCommunicationTabla5());
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
    private Button btnVolver = new Button();

    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button b) {
        this.btnVolver = b;
    }
    private Label label16 = new Label();

    public Label getLabel16() {
        return label16;
    }

    public void setLabel16(Label l) {
        this.label16 = l;
    }
    private Label label9 = new Label();

    public Label getLabel9() {
        return label9;
    }

    public void setLabel9(Label l) {
        this.label9 = l;
    }
    private TextField tfPersona = new TextField();

    public TextField getTfPersona() {
        return tfPersona;
    }

    public void setTfPersona(TextField tf) {
        this.tfPersona = tf;
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
    private ObjectListDataProvider ldpLogModificacionesSHPS = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpLogModificacionesSHPS() {
        return ldpLogModificacionesSHPS;
    }

    public void setLdpLogModificacionesSHPS(ObjectListDataProvider oldp) {
        this.ldpLogModificacionesSHPS = oldp;
    }
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private StaticText stDomicilioPostal = new StaticText();

    public StaticText getStDomicilioPostal() {
        return stDomicilioPostal;
    }

    public void setStDomicilioPostal(StaticText st) {
        this.stDomicilioPostal = st;
    }
    private Label lblParcela = new Label();

    public Label getLblParcela() {
        return lblParcela;
    }

    public void setLblParcela(Label l) {
        this.lblParcela = l;
    }
    private TextField tfParcela = new TextField();

    public TextField getTfParcela() {
        return tfParcela;
    }

    public void setTfParcela(TextField tf) {
        this.tfParcela = tf;
    }
    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }
    private TextField tfFechaInicio = new TextField();

    public TextField getTfFechaInicio() {
        return tfFechaInicio;
    }

    public void setTfFechaInicio(TextField tf) {
        this.tfFechaInicio = tf;
    }
    private TableColumn tcExencion = new TableColumn();

    public TableColumn getTcExencion() {
        return tcExencion;
    }

    public void setTcExencion(TableColumn tc) {
        this.tcExencion = tc;
    }
    private StaticText stExencion = new StaticText();

    public StaticText getStExencion() {
        return stExencion;
    }

    public void setStExencion(StaticText st) {
        this.stExencion = st;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private TextField tfFechaCese = new TextField();

    public TextField getTfFechaCese() {
        return tfFechaCese;
    }

    public void setTfFechaCese(TextField tf) {
        this.tfFechaCese = tf;
    }
    private HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(HtmlAjaxCommandButton btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}
	private Button btnSeleccionarParcela = new Button();

    public Button getBtnSeleccionarParcela() {
        return btnSeleccionarParcela;
    }

    public void setBtnSeleccionarParcela(Button b) {
        this.btnSeleccionarParcela = b;
    }
    private ObjectListDataProvider ldpExenciones = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpExenciones() {
        return ldpExenciones;
    }

    public void setLdpExenciones(ObjectListDataProvider oldp) {
        this.ldpExenciones = oldp;
    }
    private RadioButton radioButton5 = new RadioButton();

    public RadioButton getRadioButton5() {
        return radioButton5;
    }

    public void setRadioButton5(RadioButton rb) {
        this.radioButton5 = rb;
    }

    // Tabla 5
    public String getCurrentRow5() {
        return tableRowGroup5.getRowKey().getRowId();
    }

    public void setCurrentRow5(int row) {
    }
    private Object lastSelected5 = null;

    public Object getRBSelected5() {
        String sv = (String) radioButton5.getSelectedValue();
        return sv.equals(lastSelected5) ? sv : null;
    }

    public void setRBSelected5(Object selected) {
        if (selected != null) {
            lastSelected5 = selected;
        }
    }
    private Table table5 = new Table();

    public Table getTable5() {
        return table5;
    }

    public void setTable5(Table t) {
        this.table5 = t;
    }
    private TableColumn tableColumn20 = new TableColumn();

    public TableColumn getTableColumn20() {
        return tableColumn20;
    }

    public void setTableColumn20(TableColumn tc) {
        this.tableColumn20 = tc;
    }
    private TableRowGroup tableRowGroup5 = new TableRowGroup();

    public TableRowGroup getTableRowGroup5() {
        return tableRowGroup5;
    }

    public void setTableRowGroup5(TableRowGroup trg) {
        this.tableRowGroup5 = trg;
    }
    private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

    public PanelAtributoDinamico getPanelAtributoDinamico() {
        return panelAtributoDinamico;
    }

    public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
        this.panelAtributoDinamico = panelAtributoDinamico;
    }

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ConsultarDocEspTasaMenor() {
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
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
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
            log("ConsultarDocEspTasaMenor Initialization Failure", e);
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

    private ObjectListDataProvider getObjectListDataProviderTabla5() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpExenciones();
    }
    // Tabla 5

    private ArrayList getListaDelCommunicationTabla5() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaExenciones();
    }

    private void setListaDelCommunicationTabla5(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaExenciones(lista);
    }
    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">

    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, null); // Obligacion
        ep.getObjetos().add(ind++, new DocumentoTasaMenor());
        ep.getObjetos().add(ind++, null); // Persona solicitante
        ep.getObjetos().add(ind++, new Parcela());
        ep.getObjetos().add(ind++, new Domicilio());
        ep.getObjetos().add(ind++, new ArrayList()); //AtributosDinamicos

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Obligacion obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
        DocumentoTasaMenor documentoTasaMenor = (DocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, DocumentoTasaMenor.class);
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        Object nombre = this.getTfNombre().getText();
        Object fechaInicio = this.getTfFechaInicio().getText();
        Object fechaCese = this.getTfFechaCese().getText();

        if (nombre != null && nombre != "") {
            documentoTasaMenor.setNombre(nombre.toString());
        } else {
            documentoTasaMenor.setNombre(null);
        }
        if (fechaInicio != null && fechaInicio != "") {
            documentoTasaMenor.setFechaInicioActividad(Conversor.getFechaCortaDeString(fechaInicio.toString()));
        } else {
            documentoTasaMenor.setFechaInicioActividad(null);
        }
        if (fechaCese != null && fechaCese != "") {
            documentoTasaMenor.setFechaCeseActividad(Conversor.getFechaCortaDeString(fechaCese.toString()));
        } else {
            documentoTasaMenor.setFechaCeseActividad(null);
        }

        if (parcela.getIdParcela() == -1) {
            parcela = null;
        }
        documentoTasaMenor.setParcela(parcela);

        if (domicilio.getLocalidad() == null) {
            domicilio = null;
        }
        documentoTasaMenor.setDomicilio(domicilio);

        atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);

        ind = 0;
        ind++;
        this.getElementoPila().getObjetos().set(ind++, documentoTasaMenor);
        this.getElementoPila().getObjetos().set(ind++, persona);
        this.getElementoPila().getObjetos().set(ind++, parcela);
        this.getElementoPila().getObjetos().set(ind++, domicilio);
        this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Obligacion obligacion = null;
        DocumentoTasaMenor documentoTasaMenor = null;
        Persona persona = null;
        Parcela parcela = null;
        Domicilio domicilio = null;
        ArrayList atributosDinamicos = null;

        this.acomodarSeleccionado();

        if (this.getRequestBean1().getRespuestaABM() != null) {
            Object respuesta = this.getRequestBean1().getRespuestaABM();
            int posicionEP = -1;
            if (respuesta instanceof Domicilio) {
                int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
                this.getElementoPila().getObjetos().set(posicion, respuesta);
            }
        }

        if (this.getRequestBean1().getObjetoABM() != null) {

            obligacion = (Obligacion) this.getRequestBean1().getObjetoABM();

            try {
                this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().setLlave(this.getSessionBean1().getLlave());
                documentoTasaMenor = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().getDocumentoTasaMenor(obligacion);
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_getDocumentoHabilitanteTasaMenor:", ex);
                error(NOMBRE_PAGINA + " - No se pudo obtener el Documento Tasa Menor: " + ex.getMessage());
            }

            persona = (Persona) obligacion.getPersona();

            try {
                this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
                parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(documentoTasaMenor.getParcela().getIdParcela());
            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_getParcelaPorId:", ex);
                error(NOMBRE_PAGINA + " - No se pudo obtener la Parcela asociada: " + ex.getMessage());
            }

            domicilio = documentoTasaMenor.getDomicilio();

            if (documentoTasaMenor.getListaAtributosDinamicos() != null) {
                try {
                    atributosDinamicos = (ArrayList) documentoTasaMenor.generarAtributosDinamicos();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            ind = 0;
            this.getElementoPila().getObjetos().set(ind++, obligacion);
            this.getElementoPila().getObjetos().set(ind++, documentoTasaMenor);
            this.getElementoPila().getObjetos().set(ind++, persona);
            this.getElementoPila().getObjetos().set(ind++, parcela);
            this.getElementoPila().getObjetos().set(ind++, domicilio);
            this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
        }

        ind = 0;
        //ind++;
        obligacion = (Obligacion) this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
        documentoTasaMenor = (DocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, DocumentoTasaMenor.class);
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
        domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
        atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos,"#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor}");
        panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
        panelAtributoDinamico.deshabilitarCampos();

        ArrayList listaExenciones = new ArrayList();
        listaExenciones.addAll(obligacion.getListaRegistrosExencion());

        this.getTfNombre().setText(documentoTasaMenor.getNombre());
        if (documentoTasaMenor.getFechaInicioActividad() != null) {
            this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoTasaMenor.getFechaInicioActividad()));
        }
        if (documentoTasaMenor.getFechaCeseActividad() != null) {
            this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoTasaMenor.getFechaCeseActividad()));
        }
        if (persona != null && persona.getIdPersona() != -1){
            this.getTfPersona().setText(persona.toString());
        }
        this.getTfParcela().setText(parcela.toString());
        if (domicilio.getLocalidad() != null) {
            this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
        }

        //tabla 5
        this.getObjectListDataProviderTabla5().setList(listaExenciones);
        this.setListaDelCommunicationTabla5(listaExenciones);

        this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
    }

    private ArrayList getListaDelCommunicationAtributosDinamicos() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaAtributosDinamicosTasaMenor();
    }

    private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosTasaMenor(lista);
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

    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo != null) {
                campo.setText(null);
            }
        } catch (Exception ex) {
        }
    }
    // </editor-fold>

    public String btnVolver_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.setListaDelCommunicationAtributosDinamicos(null);
            retorno = this.prepararParaVolver(Constantes.ACCION_CONSULTAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
}
