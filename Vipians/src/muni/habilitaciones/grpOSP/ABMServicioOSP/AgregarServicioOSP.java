/*
 * AgregarServicioOSP.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpOSP.ABMServicioOSP;

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
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.presentacion.conversores.Conversor;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Script;
import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota.TipoRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP.UnidadMedida;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class AgregarServicioOSP extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Agregar C\363digo de Servicio de OSP";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarServicioOSP";
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
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        Option[] op = null;
        // Unidades de Medida
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(UnidadMedida.values(), "cap");
        ddUnidadMedidaDefaultOptions.setOptions(op);

        Option[] opPeriodicidad = null;

        opPeriodicidad = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(Periodicidad.values(), "cap");
        ddPeriodicidadDefaultOptions.setOptions(opPeriodicidad);

//        this.ddUnidadMedida.setDisabled(true);
//        this.getTfValorPorExcedente().setDisabled(true);
//        this.getTfBaseConsumo().setDisabled(true);
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
    private TextField tfCodigo = new TextField();

    public TextField getTfCodigo() {
        return tfCodigo;
    }

    public void setTfCodigo(TextField tf) {
        this.tfCodigo = tf;
    }
    private Label label4 = new Label();
    private Label lblCoeficienteValorTerreno = new Label();
    private Label lblCoeficienteValorEdificado = new Label();
    private Label lblPeriodicidad = new Label();
    private Label label8 = new Label();

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
    private Button btnGuardar = new Button();

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(Button b) {
        this.btnGuardar = b;
    }
    private Button btnCancelar = new Button();

    public Label getLabel8() {
        return label8;
    }

    public void setLabel8(Label label8) {
        this.label8 = label8;
    }

    private Label lblVolcadoEfluentesIndustriales = new Label();
    
    public Label getLblVolcadoEfluentesIndustriales() {
        return lblVolcadoEfluentesIndustriales;
    }

    public void setLblVolcadoEfluentesIndustriales(Label pLblVolcadoEfluentesIndustriales) {
        this.lblVolcadoEfluentesIndustriales = pLblVolcadoEfluentesIndustriales;
    }

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
    
    private TextArea taNombre = new TextArea();

    public TextArea getTaNombre() {
        return taNombre;
    }

    public void setTaNombre(TextArea ta) {
        this.taNombre = ta;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    private TextField tfValor = new TextField();
    private TextField tfCoeficienteCodigo = new TextField();
    private TextField tfCoeficienteValorTerreno = new TextField();
    private TextField tfCoeficienteValorEdificado = new TextField();

    public Label getLblCoeficienteValorEdificado() {
        return lblCoeficienteValorEdificado;
    }

    public void setLblCoeficienteValorEdificado(Label lblCoeficienteValorEdificado) {
        this.lblCoeficienteValorEdificado = lblCoeficienteValorEdificado;
    }

    public Label getLblCoeficienteValorTerreno() {
        return lblCoeficienteValorTerreno;
    }

    public void setLblCoeficienteValorTerreno(Label lblCoeficienteValorTerreno) {
        this.lblCoeficienteValorTerreno = lblCoeficienteValorTerreno;
    }

    public TextField getTfCoeficienteValorEdificado() {
        return tfCoeficienteValorEdificado;
    }

    public void setTfCoeficienteValorEdificado(TextField tfCoeficienteValorEdificado) {
        this.tfCoeficienteValorEdificado = tfCoeficienteValorEdificado;
    }

    public TextField getTfCoeficienteValorTerreno() {
        return tfCoeficienteValorTerreno;
    }

    public void setTfCoeficienteValorTerreno(TextField tfCoeficienteValorTerreno) {
        this.tfCoeficienteValorTerreno = tfCoeficienteValorTerreno;
    }

    public Label getLblPeriodicidad() {
        return lblPeriodicidad;
    }

    public void setLblPeriodicidad(Label lblPeriodicidad) {
        this.lblPeriodicidad = lblPeriodicidad;
    }

    public TextField getTfCoeficienteCodigo() {
        return tfCoeficienteCodigo;
    }

    public void setTfCoeficienteCodigo(TextField tfCoeficienteCodigo) {
        this.tfCoeficienteCodigo = tfCoeficienteCodigo;
    }

    public TextField getTfValor() {
        return tfValor;
    }

    public void setTfValor(TextField tf) {
        this.tfValor = tf;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private Checkbox cbMedido = new Checkbox();

    public Checkbox getCbMedido() {
        return cbMedido;
    }

    public void setCbMedido(Checkbox c) {
        this.cbMedido = c;
    }

    private Checkbox cbVolcadoEfluentesIndustriales = new Checkbox();

    public Checkbox getCbVolcadoEfluentesIndustriales() {
        return cbVolcadoEfluentesIndustriales;
    }
    public void setCbVolcadoEfluentesIndustriales(Checkbox pCbVolcadoEfluentesIndustriales) {
        this.cbVolcadoEfluentesIndustriales = pCbVolcadoEfluentesIndustriales;
    }
    
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }
    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }
    private TextField tfBaseConsumo = new TextField();

    public TextField getTfBaseConsumo() {
        return tfBaseConsumo;
    }

    public void setTfBaseConsumo(TextField tf) {
        this.tfBaseConsumo = tf;
    }
    private TextField tfValorPorExcedente = new TextField();

    public TextField getTfValorPorExcedente() {
        return tfValorPorExcedente;
    }

    public void setTfValorPorExcedente(TextField tf) {
        this.tfValorPorExcedente = tf;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private DropDown ddUnidadMedida = new DropDown();

    public DropDown getDdUnidadMedida() {
        return ddUnidadMedida;
    }

    public void setDdUnidadMedida(DropDown dd) {
        this.ddUnidadMedida = dd;
    }
    private SingleSelectOptionsList ddUnidadMedidaDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdUnidadMedidaDefaultOptions() {
        return ddUnidadMedidaDefaultOptions;
    }

    public void setDdUnidadMedidaDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddUnidadMedidaDefaultOptions = ssol;
    }
    private SingleSelectOptionsList ddPeriodicidadDefaultOptions = new SingleSelectOptionsList();
    private DropDown ddPeriodicidad = new DropDown();

    public DropDown getDdPeriodicidad() {
        return ddPeriodicidad;
    }

    public void setDdPeriodicidad(DropDown ddPeriodicidad) {
        this.ddPeriodicidad = ddPeriodicidad;
    }

    public SingleSelectOptionsList getDdPeriodicidadDefaultOptions() {
        return ddPeriodicidadDefaultOptions;
    }

    public void setDdPeriodicidadDefaultOptions(SingleSelectOptionsList ddPeriodicidadDefaultOptions) {
        this.ddPeriodicidadDefaultOptions = ddPeriodicidadDefaultOptions;
    }

    // </editor-fold>
    /**
     * <p>Construir una instancia de bean de página.</p>
     */
    public AgregarServicioOSP() {
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
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
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
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando se navega hasta esta página,
     * ya sea directamente mediante un URL o de manera indirecta a través de la navegación de páginas.
     * Puede personalizar este método para adquirir recursos que se necesitarán
     * para los controladores de eventos y métodos del proceso, sin tener en cuenta si esta
     * página realiza procesamiento de devolución de envíos.</p>
     *
     * <p>Tenga en cuenta que si la petición actual es una devolución de envío, los valores
     * de propiedad de los componentes <strong>no</strong> representan ningún
     * valor enviado con esta petición.  En su lugar, representan los
     * valores de propiedades que se guardaron para esta vista cuando se procesó.</p>
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
            log("AgregarServicioOSP Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicación que debe finalizar
        // *después* de que se inicien los componentes administrados
        // TODO - Agregar código de inicio propio aquí

    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando el árbol de componentes se ha
     * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este método
     * <strong>sólo</strong> se llamará en una petición de devolución de envío que
     * esté procesando el envío de un formulario.  Puede personalizar este método para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /**
     * <p>Método de devolución de llamada al que se llama justo antes del procesamiento.
     * <strong>Sólo</strong> se llamará a este método en la página que
     * se procesa, no se llamará, por ejemplo, en una página que
     * ha procesado una devolución de envío y a continuación ha navegado hasta otra página.  Puede personalizar
     * este método para asignar recursos necesarios para procesar
     * esta página.</p>
     */
    public void prerender() {
        boolean existeIdSubSesionReq = false;
        boolean existeIdPaginaReq = false;
        boolean existeIdSubSesionPag = false;
        boolean existeIdPaginaPag = false;
        boolean recarga = false;

        //ACAAA
        if (this.cbMedido.isChecked()) {
            this.tfBaseConsumo.setDisabled(false);
            this.ddUnidadMedida.setDisabled(false);
            this.tfValorPorExcedente.setDisabled(false);
        }

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

            this.cargarValoresPorDefecto();
            //this.getRequestBean1().setRespuestaABM("first");
        }

        // Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());
        }

        if (!recarga) {
//            this.cargarValoresPorDefecto();
            this.mostrarEstadoObjetosUsados();
        }

        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));

    }

    /**
     * <p>Método de devolución de llamada al que se llama cuando se completa el procesamiento de
     * esta petición, si se llamó al método <code>init()</code> (sin tener en cuenta
     * si se trata de la página que se ha procesado o no).  Puede personalizar este
     * método para liberar los recursos adquiridos en los métodos <code>init()</code>,
     * <code>preprocess()</code> o <code>prerender()</code> (o
     * durante la ejecución de un controlador de eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new ServicioOSP());

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        ServicioOSP servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(0, ServicioOSP.class);
        Periodicidad periodicidad = null;

        Object codigo = this.getTfCodigo().getText();
        Object coeficienteCodigo = this.getTfCoeficienteCodigo().getText();
        Object coeficienteValorTerreno = this.getTfCoeficienteValorTerreno().getText();
        Object coeficienteValorEdificado = this.getTfCoeficienteValorEdificado().getText();
        Object nombre = this.getTaNombre().getText();
        Object valor = this.getTfValor().getText();

        Object medido = this.getCbMedido().getValue();
        Object volcadoEfluentesIndustriales = this.getCbVolcadoEfluentesIndustriales().getValue();

        if (this.cbMedido.isChecked()) {
            this.getTfBaseConsumo().setDisabled(false);
            this.getTfValorPorExcedente().setDisabled(false);
            this.getDdUnidadMedida().setDisabled(false);
        }

       //TipoRegAlicuota tipoRegAlicuota = ;
       //servicioOSP.setTipoRegAlicuota(RegAlicuota.TipoRegAlicuota.FIJO_MENSUAL);

        Object periodicidadSelected = this.getDdPeriodicidad().getSelected();
        if ((periodicidadSelected != null) && (periodicidadSelected.toString().length() > 0)) {
            periodicidad = Periodicidad.valueOf(periodicidadSelected.toString());
         
        }
        servicioOSP.setPeriodicidad(periodicidad);
        Object unidad = this.getDdUnidadMedida().getSelected();

        UnidadMedida unidadMedida = null;
        if ((unidad != null) && (unidad.toString().length() > 0)) {
            unidadMedida = UnidadMedida.valueOf(unidad.toString());
        }

        Object baseConsumo = this.getTfBaseConsumo().getText();
        Object valorPorExcedente = this.getTfValorPorExcedente().getText();

        if (codigo != null && codigo != "") {
            servicioOSP.setCodigo(codigo.toString());
        } else {
            servicioOSP.setCodigo(null);
        }
        if (coeficienteCodigo != null && coeficienteCodigo != "") {
            servicioOSP.setCoeficienteCodigoServicio(Conversor.getDoubleDeString(coeficienteCodigo.toString()));
        } else {
            servicioOSP.setCoeficienteCodigoServicio(null);
        }
        if (coeficienteValorTerreno != null && coeficienteValorTerreno != "") {
            servicioOSP.setCoeficienteValorTerreno(Conversor.getDoubleDeString(coeficienteValorTerreno.toString()));
        }
        if (coeficienteValorEdificado != null && coeficienteValorEdificado != "") {
            servicioOSP.setCoeficienteValorEdificado(Conversor.getDoubleDeString(coeficienteValorEdificado.toString()));
        }
        if (nombre != null && nombre != "") {
            servicioOSP.setNombre(nombre.toString());
        } else {
            servicioOSP.setNombre(null);
        }
        if (valor != null && valor != "") {
            servicioOSP.setValor(Conversor.getDoubleDeString(valor.toString()));
        } else {
            servicioOSP.setValor(null);
        }

        if (medido != null && medido != "") {
            servicioOSP.setMedido(((Boolean) medido).booleanValue());
        } else {
            servicioOSP.setMedido(false);
        }

        if (volcadoEfluentesIndustriales != null && volcadoEfluentesIndustriales != "") {
            servicioOSP.setVolcadoEfluentesIndustriales(((Boolean) volcadoEfluentesIndustriales).booleanValue());
        } else {
            servicioOSP.setVolcadoEfluentesIndustriales(false);
        }

        
        if (servicioOSP.isMedido()) {
            if (unidadMedida != null) {
                servicioOSP.setUnidadMedida(unidadMedida);
            } else {
                servicioOSP.setUnidadMedida(null);
            }
            if (baseConsumo != null && baseConsumo != "") {
                servicioOSP.setBaseConsumo(Conversor.getDoubleDeString(baseConsumo.toString()));
            } else {
                servicioOSP.setBaseConsumo(null);
            }
            if (valorPorExcedente != null && valorPorExcedente != "") {
                servicioOSP.setValorPorExcedente(Conversor.getDoubleDeString(valorPorExcedente.toString()));
            } else {
                servicioOSP.setValorPorExcedente(null);
            }
        } else {
            servicioOSP.setUnidadMedida(null);
            servicioOSP.setBaseConsumo(Conversor.getDoubleDeString("0"));
            servicioOSP.setValorPorExcedente(Conversor.getDoubleDeString("0"));
        }
       
        // NOTA: seteo el tipo de obligacion como OYSP
        servicioOSP.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("OYSP"));

      

        //tipoRegAlicuota = RegAlicuota.TipoRegAlicuota.FIJO_MENSUAL;
        System.out.println("period-----GE1--" +  servicioOSP.getPeriodicidad().toString());

        System.out.println((servicioOSP.getTipoRegAlicuota()!=null)?"alic-----GE1--" +  servicioOSP.getTipoRegAlicuota().toString():"el coso es null");
       // servicioOSP.setTipoRegAlicuota(RegAlicuota.TipoRegAlicuota.PORCENTUAL_MENSUAL);

        //System.out.println("period-----GE2--" +  servicioOSP.getPeriodicidad().toString());
       // System.out.println("alic-----GE2--" +  servicioOSP.getTipoRegAlicuota().toString());
  
        this.getElementoPila().getObjetos().set(0, servicioOSP);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        ServicioOSP servicioOSP = null;
        this.acomodarSeleccionado();

        if (this.getRequestBean1().getObjetoABM() != null) {
            servicioOSP = (ServicioOSP) this.getRequestBean1().getObjetoABM();
            this.getElementoPila().getObjetos().set(0, servicioOSP);
        }

        servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(0, ServicioOSP.class);

        this.getDdPeriodicidad().setSelected(Util.getEnumNameFromString(String.valueOf(servicioOSP.getPeriodicidad())));
        this.getDdPeriodicidadDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(servicioOSP.getPeriodicidad())));

        if (servicioOSP.getCodigo() != null) {
            this.getTfCodigo().setText(servicioOSP.getCodigo());
        }
        if (servicioOSP.getCoeficienteCodigoServicio() != null) {
            this.getTfCoeficienteCodigo().setText(servicioOSP.getCoeficienteCodigoServicio());
        }

        if (servicioOSP.getCoeficienteValorTerreno() != null) {
            this.getTfCoeficienteValorTerreno().setText(Conversor.getStringDeDouble(servicioOSP.getCoeficienteValorTerreno()));
        }
        if (servicioOSP.getCoeficienteValorEdificado() != null) {
            this.getTfCoeficienteValorEdificado().setText(Conversor.getStringDeDouble(servicioOSP.getCoeficienteValorEdificado()));
        }
        this.getTaNombre().setText(servicioOSP.getNombre());
        if (servicioOSP.getValor() != null) {
            this.getTfValor().setText(Conversor.getStringDeDouble(servicioOSP.getValor()));
        }

        this.getCbVolcadoEfluentesIndustriales().setValue(new Boolean(servicioOSP.isVolcadoEfluentesIndustriales()));
        this.getCbMedido().setValue(new Boolean(servicioOSP.isMedido()));
        this.cbMedido_onSelect();
        if (servicioOSP.isMedido()) {
            if (servicioOSP.getBaseConsumo() != null) {
                this.getTfBaseConsumo().setText(Conversor.getStringDeDouble(servicioOSP.getBaseConsumo()));
            }
            if (servicioOSP.getValorPorExcedente() != null) {
                this.getTfValorPorExcedente().setText(Conversor.getStringDeDouble(servicioOSP.getValorPorExcedente()));
            }
            this.getDdUnidadMedida().setSelected(Util.getEnumNameFromString(servicioOSP.getUnidadMedida().toString()));
            this.getDdUnidadMedidaDefaultOptions().setSelectedValue(Util.getEnumNameFromString(servicioOSP.getUnidadMedida().toString()));
        } else {
            this.getTfBaseConsumo().setText(null);
            this.getTfValorPorExcedente().setText(null);
            this.getDdUnidadMedida().setSelected(null);
            this.getDdUnidadMedidaDefaultOptions().setSelectedValue(null);
        }

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

    public void limpiarObjeto(int posicion, Class tipoClase, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, tipoClase.newInstance());
            if (campo != null) {
                campo.setText("");
            }
        } catch (Exception ex) {
        }
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

    // </editor-fold>
    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());

        if (ultimo) {

            try {
                this.guardarEstadoObjetosUsados();

                // NOTA: lo leo ac� para saber si el Servicio es medido o no (para las validaciones)
                ServicioOSP servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(0, ServicioOSP.class);

                // CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                UIComponent[] noVacios = null;
                String[] nomNoVacios = null;
                UIComponent[] flotantes = null;
                String[] nomFlotantes = null;
                UIComponent[] positivos = null;
                String[] nomPositivos = null;

                if (servicioOSP.isMedido()) {
                    noVacios = new UIComponent[6];
                    nomNoVacios = new String[6];
                    flotantes = new UIComponent[6];
                    nomFlotantes = new String[6];
                    positivos = new UIComponent[7];
                    nomPositivos = new String[7];
                } else {
                    noVacios = new UIComponent[3];
                    nomNoVacios = new String[3];
                    flotantes = new UIComponent[4];
                    nomFlotantes = new String[4];
                    positivos = new UIComponent[5];
                    nomPositivos = new String[5];
                }

                int pos = 0;
                noVacios[pos] = this.getTfCodigo();
                nomNoVacios[pos++] = "C\363digo";
                noVacios[pos] = this.getTaNombre();
                nomNoVacios[pos++] = "Descripci\363n";
                noVacios[pos] = this.getTfValor();
                nomNoVacios[pos++] = "Valor";

                if (servicioOSP.isMedido()) {
                    noVacios[pos] = this.getDdUnidadMedida();
                    nomNoVacios[pos++] = "Unidad de Medida";
                    noVacios[pos] = this.getTfBaseConsumo();
                    nomNoVacios[pos++] = "Base de Consumo";
                    noVacios[pos] = this.getTfValorPorExcedente();
                    nomNoVacios[pos++] = "Valor del Excedente";
                }

                pos = 0;
                flotantes[pos] = this.getTfCoeficienteCodigo();
                nomFlotantes[pos++] = "Coeficiente C\363digo de Servicio";
                flotantes[pos] = this.getTfCoeficienteValorTerreno();
                nomFlotantes[pos++] = "Coeficiente Valor de Terreno";
                flotantes[pos] = this.getTfCoeficienteValorEdificado();
                nomFlotantes[pos++] = "Coeficiente Valor Edificado";
                flotantes[pos] = this.getTfValor();
                nomFlotantes[pos++] = "Valor";

                if (servicioOSP.isMedido()) {
                    flotantes[pos] = this.getTfBaseConsumo();
                    nomFlotantes[pos++] = "Base de Consumo";
                    flotantes[pos] = this.getTfValorPorExcedente();
                    nomFlotantes[pos++] = "Valor del Excedente";
                }

                pos = 0;
                positivos[pos] = this.getTfCodigo();
                nomPositivos[pos++] = "C\363digo";
                positivos[pos] = this.getTfCoeficienteCodigo();
                nomPositivos[pos++] = "Coeficiente C\363digo de Servicio";
                positivos[pos] = this.getTfCoeficienteValorTerreno();
                nomPositivos[pos++] = "Coeficiente Valor de Terreno";
                positivos[pos] = this.getTfCoeficienteValorEdificado();
                nomPositivos[pos++] = "Coeficiente Valor Edificado";
                positivos[pos] = this.getTfValor();
                nomPositivos[pos++] = "Valor";

                if (servicioOSP.isMedido()) {
                    positivos[pos] = this.getTfBaseConsumo();
                    nomPositivos[pos++] = "Base de Consumo";
                    positivos[pos] = this.getTfValorPorExcedente();
                    nomPositivos[pos++] = "Valor del Excedente";
                }

                v.noSonVacios(noVacios, nomNoVacios);
                v.sonFlotantes(flotantes, nomFlotantes);
                v.sonPositivos(positivos, nomPositivos);

                this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
//                if (this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().findListaServiciosOSP(servicioOSP.getCodigo(), null).size() > 0) {
//                    ServicioOSP locServicioOSP = (ServicioOSP) this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().findListaServiciosOSP(servicioOSP.getCodigo(), null).get(0);
//                    if ((servicioOSP.getIdTipoAlicuota() != locServicioOSP.getIdTipoAlicuota()) && (locServicioOSP.getCodigo().equalsIgnoreCase(servicioOSP.getCodigo()))) {
//                        String msg = "No puede agregarse un Servicio con un C\363digo ya existente.";
//                        v.getErrores().add(msg);
//                        this.getTfCodigo().setValid(false);
//                    }
//                }
                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                // CAMBIAR: (NOTA: lo leo arriba para saber si es medido o no el servicio)
                //ServicioOSP servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(0, ServicioOSP.class);

                // CAMBIAR: Utilizar el EJBClient adecuado.
                //this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(this.getSessionBean1().getLlave());
                              
                servicioOSP = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().addServicioOSP(servicioOSP);

                this.getRequestBean1().setRespuestaABM(servicioOSP);

                info("El C\363digo de Servicio de OySP se agreg\363 exitosamente.");

                retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
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

    public boolean cbMedido_onSelect() {
        boolean medido = this.getCbMedido().isChecked();
        this.getDdUnidadMedida().setDisabled(medido);
        this.getDdUnidadMedida().setDisabled(medido);
        this.getTfBaseConsumo().setDisabled(medido);
        this.getTfValorPorExcedente().setDisabled(medido);
        return medido;
    }

    private void cargarValoresPorDefecto() {
        // CAMBIAR: Obtener los valores por defecto.
        //LO DEJO A MODO DE EJEMPLO.. PERO NO HAY VALORES POR DEFECTO EN ESTA P�GINA
      /*  TipoRegAlicuota tipoRegAlicuota = null;
        
       tipoRegAlicuota = RegAlicuota.TipoRegAlicuota.PORCENTUAL_MENSUAL;
        
        RegAlicuota regAlicuota = (RegAlicuota) this.obtenerObjetoDelElementoPila(0, RegAlicuota.class);
        regAlicuota.setTipoRegAlicuota(tipoRegAlicuota);
        this.getElementoPila().getObjetos().set(0, regAlicuota);

        return;*/
    }
}
