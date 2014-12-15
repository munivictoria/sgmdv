/*
 * ConsultarStock.java
 *
 * Copyright Trascender SRL
 * autor Maximiliano Fontanini
 */
package muni.inventario.ABMStock;
 
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
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.PuntoDeReposicion;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import javax.faces.FacesException;


/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ConsultarStock extends AbstractPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">

    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;
    public Long getIdPagina() { return idPagina; }
    public void setIdPagina(Long idPagina) { this.idPagina = idPagina; }
    public Long getIdSubSesion() { return idSubSesion; }
    public void setIdSubSesion(Long idSubSesion) { this.idSubSesion = idSubSesion; }

    private Stock stockABM = null;
    private Bien bienSeleccionado = null;
    private Deposito depositoSeleccionado = null;
    private PuntoDeReposicion puntoDeReposicionSeleccionado = null;

    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }

    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Consultar Stock";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ConsultarStock";
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

    private Link link1 = new Link();

    public Link getLink1() {
        return link1;
    }

    public void setLink1(Link pLink1) {
        this.link1 = pLink1;
    }

    private Body body1 = new Body();

    public Body getBody1() {
        return body1;
    }

    public void setBody1(Body pBody) {
        this.body1 = pBody;
    }

    private Form form1 = new Form();

    public Form getForm1() {
        return form1;
    }

    public void setForm1(Form pForm1) {
        this.form1 = pForm1;
    }

    private StaticText stTitulo = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText pStTitulo) {
        this.stTitulo = pStTitulo;
    }

     //Componentes
    private TextField tfBien = new TextField();

    public TextField getTfBien() {
        return tfBien;
    }
    public void setTfBien(TextField pTfBien) {
        this.tfBien = pTfBien;
    }

    private TextField tfCantidad = new TextField();

    public TextField getTfCantidad() {
        return tfCantidad;
    }
    public void setTfCantidad(TextField pTfCantidad) {
        this.tfCantidad = pTfCantidad;
    }

    private TextArea taDescripcion = new TextArea();

    public TextArea getTaDescripcion() {
        return taDescripcion;
    }
    public void setTaDescripcion(TextArea pTaDescripcion) {
        this.taDescripcion = pTaDescripcion;
    }

    private TextField tfDeposito = new TextField();

    public TextField getTfDeposito() {
        return tfDeposito;
    }
    public void setTfDeposito(TextField pTfDeposito) {
        this.tfDeposito = pTfDeposito;
    }

    //Labels
    private Label lblBien = new Label();

    public Label getLblBien() {
        return lblBien;
    }
    public void setLblBien(Label pLblBien) {
        this.lblBien = pLblBien;
    }

    private Label lblCantidad = new Label();

    public Label getLblCantidad() {
        return lblCantidad;
    }
    public void setLblCantidad(Label pLblCantidad) {
        this.lblCantidad = pLblCantidad;
    }

    private Label lblDescripcion = new Label();

    public Label getLblDescripcion() {
        return lblDescripcion;
    }
    public void setLblDescripcion(Label pLblDescripcion) {
        this.lblDescripcion = pLblDescripcion;
    }

    private Label lblDeposito = new Label();

    public Label getLblDeposito() {
        return lblDeposito;
    }
    public void setLblDeposito(Label pLblDeposito) {
        this.lblDeposito = pLblDeposito;
    }

    private Label lblPuntoDeReposicion = new Label();

    public Label getLblPuntoDeReposicion() {
        return lblPuntoDeReposicion;
    }
    public void setLblPuntoDeReposicion(Label pLblPuntoDeReposicion) {
        this.lblPuntoDeReposicion = pLblPuntoDeReposicion;
    }

    //Buttons
    private Button btnVolver = new Button();

    public Button getBtnVolver() {
        return btnVolver;
    }
    public void setBtnVolver(Button pBtnVolver) {
        this.btnVolver = pBtnVolver;
    }

    private Button btnSeleccionarPuntoDeReposicion = new Button();

    public Button getBtnSeleccionarPuntoDeReposicion() {
        return btnSeleccionarPuntoDeReposicion;
    }
    public void setBtnSeleccionarPuntoDeReposicion(Button pBtnSeleccionarPuntoDeReposicion) {
        this.btnSeleccionarPuntoDeReposicion = pBtnSeleccionarPuntoDeReposicion;
    }

    public Button btnLimpiarPuntoDeReposicion = new Button();

    public Button getBtnLimpiarPuntoDeReposicion(){
        return btnLimpiarPuntoDeReposicion;
    }
    public void setBtnLimpiarPuntoDeReposicion(Button pBtnLimpiarPuntoDeReposicion){
        this.btnLimpiarPuntoDeReposicion = pBtnLimpiarPuntoDeReposicion;
    }

    //Componentes del Punto de Reposición

     private Label lblCantidadAComprar = new Label();

    public Label getLblCantidadAComprar() {
        return lblCantidadAComprar;
    }

    public void setLblCantidadAComprar(Label pLblCantidadAComprar) {
        this.lblCantidadAComprar = pLblCantidadAComprar;
    }

    private Label lblCantidadLimite = new Label();

    public Label getLblCantidadLimite() {
        return lblCantidadLimite;
    }

    public void setLblCantidadLimite(Label pLblCantidadLimite) {
        this.lblCantidadLimite = pLblCantidadLimite;
    }

    private Label lblDescripcionPtoRep = new Label();

    public Label getLblDescripcionPtoRep() {
        return lblDescripcionPtoRep;
    }

    public void setLblDescripcionPtoRep(Label pLblDescripcionPtoRep) {
        this.lblDescripcionPtoRep = pLblDescripcionPtoRep;
    }

    private TextField tfCantidadAComprar = new TextField();

    public TextField getTfCantidadAComprar() {
        return tfCantidadAComprar;
    }

    public void setTfCantidadAComprar(TextField pTfCantidadAComprar) {
        this.tfCantidadAComprar = pTfCantidadAComprar;
    }

    private TextField tfCantidadLimite = new TextField();

    public TextField getTfCantidadLimite() {
        return tfCantidadLimite;
    }

    public void setTfCantidadLimite(TextField pTfCantidadLimite) {
        this.tfCantidadLimite = pTfCantidadLimite;
    }

    private TextArea taDescripcionPtoRep = new TextArea();

    public TextArea getTaDescripcionPtoRep() {
        return taDescripcionPtoRep;
    }

    public void setTaDescripcionPtoRep(TextArea pTaDescripcionPtoRep) {
        this.taDescripcionPtoRep = pTaDescripcionPtoRep;
    }

    
    private StaticText stSeparador = new StaticText();

    public StaticText getStSeparador() {
        return stSeparador;
    }

    public void setStSeparador(StaticText pStSeparador) {
        this.stSeparador = pStSeparador;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup pMessageGroup1) {
        this.messageGroup1 = pMessageGroup1;
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

    public void setHidIdSubSesion(HiddenField pHidIdSubSesion) {
        this.hidIdSubSesion = pHidIdSubSesion;
    }
    
    private Script scriptValidador = new Script();

    public Script getScriptValidador() {
        return scriptValidador;
    }

    public void setScriptValidador(Script scriptValidador) {
        this.scriptValidador = scriptValidador;
    }

    // </editor-fold>


    /**
     * <p>Construir una instancia de bean de página.</p>
     */
    public ConsultarStock() {
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (muni.CommunicationExcepcionesBean)getBean("CommunicationExcepcionesBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationCajaBean getCommunicationCajaBean() {
        return (muni.CommunicationCajaBean)getBean("CommunicationCajaBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean)getBean("CommunicationComprasBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean)getBean("CommunicationSAICBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean)getBean("CommunicationHabilitacionesBean");
    }

    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     * mines
     */

    protected muni.CommunicationMesaEntradaBean getCommunicationMesaEntradaBean(){
        return(muni.CommunicationMesaEntradaBean)getBean("CommunicationMesaEntradaBean");
    }

    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean)getBean("ComunicationBean");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1)getBean("ApplicationBean1");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1)getBean("SessionBean1");
    }


    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1)getBean("RequestBean1");
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
            log("ConsultarStock Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
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
        boolean existeIdPaginaReq    = false;
        boolean existeIdSubSesionPag = false;
        boolean existeIdPaginaPag    = false;
        boolean recarga              = false;

        if(this.getRequestBean1()!=null) {
            existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
            existeIdPaginaReq    = (this.getRequestBean1().getIdPagina() != null);
        }

        this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
        this.setIdPagina((Long) this.getHidIdPagina().getText());

        existeIdSubSesionPag = this.getIdSubSesion() != null;
        existeIdPaginaPag    = this.getIdPagina() != null;

        // Pagina nueva - Inicio de transaccion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            if(this.PUEDE_SER_PAGINA_INICIAL) {
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
        ep.getObjetos().add(ind++, new Stock());
        ep.getObjetos().add(ind++, new Bien());
        ep.getObjetos().add(ind++, new Deposito());
        ep.getObjetos().add(ind++, new PuntoDeReposicion());

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        Stock locStock = (Stock) this.obtenerObjetoDelElementoPila(0, Stock.class);
        Bien locBien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
        Deposito locDeposito = (Deposito) this.obtenerObjetoDelElementoPila(2, Deposito.class);
        PuntoDeReposicion locPuntoDeReposicion = (PuntoDeReposicion) this.obtenerObjetoDelElementoPila(3, PuntoDeReposicion.class);


        Object locCantidad = this.getTfCantidad().getText();
        Object locDescripcion = this.getTaDescripcion().getText();

        if (locCantidad != null && locCantidad != "") {
            locStock.setCantidad(new Double(locCantidad.toString()));
        }
        else {
            locStock.setCantidad(null);
        }

        if (locDescripcion != null && locDescripcion != "") {
            locStock.setDescripcion(locDescripcion.toString());
        }
        else {
            locStock.setDescripcion(null);
        }

        if(locBien != null && locBien.getIdBien() != -1){
            locStock.setBien(locBien);
        }

        if(locDeposito != null && locDeposito.getIdDeposito() != -1l){
            locStock.setDeposito(locDeposito);
        }

        //Me parece que el PuntoDeReposicion tiene id -1 a esta altura porque el Stock se encarga de persistirlo
        if(locPuntoDeReposicion != null && locPuntoDeReposicion.getIdPuntoDeReposicion() != -1l){
            locStock.setPuntoDeReposicion(locPuntoDeReposicion);
        }

        this.getElementoPila().getObjetos().set(0, locStock);
        this.getElementoPila().getObjetos().set(1, locBien);
        this.getElementoPila().getObjetos().set(2, locDeposito);
        this.getElementoPila().getObjetos().set(3, locPuntoDeReposicion);
    }

    private void mostrarEstadoObjetosUsados() {
         // CAMBIAR: Verificar el metodo completo.
        Stock locStock = null;
        Bien locBien = null;
        Deposito locDeposito = null;
        PuntoDeReposicion locPuntoDeReposicion = null;

        if (this.getRequestBean1().getObjetoABM() != null) {
            locStock = (Stock) this.getRequestBean1().getObjetoABM();
            this.setStockABM(locStock);
            locBien = locStock.getBien();
            locDeposito = locStock.getDeposito();
            locPuntoDeReposicion = locStock.getPuntoDeReposicion();

            this.getElementoPila().getObjetos().set(0, locStock);
            this.getElementoPila().getObjetos().set(1, locBien);
            this.getElementoPila().getObjetos().set(2, locDeposito);
            this.getElementoPila().getObjetos().set(3, locPuntoDeReposicion);
        }

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.
            if (seleccionado instanceof Bien) {
                if (seleccionado != null) {
                    locBien = (Bien) seleccionado;
                    this.getElementoPila().getObjetos().set(1, locBien);
                    this.getRequestBean1().setObjetoSeleccion(null);
                }
            }

            if(seleccionado instanceof Deposito){
                if(seleccionado != null){
                    locDeposito = (Deposito) seleccionado;
                    this.getElementoPila().getObjetos().set(2, locDeposito);
                    this.getRequestBean1().setObjetoSeleccion(null);
                }
            }
        }

        if (this.getElementoPila().getObjetos() != null && !this.getElementoPila().getObjetos().isEmpty()) {
            locStock = (Stock) this.obtenerObjetoDelElementoPila(0, Stock.class);
            locBien = (Bien) this.obtenerObjetoDelElementoPila(1, Bien.class);
            locDeposito = (Deposito) this.obtenerObjetoDelElementoPila(2, Deposito.class);
        }

        if(locBien != null)   this.getTfBien().setText(locBien.toString());
        if(locStock.getCantidad() != null) this.getTfCantidad().setText(locStock.getCantidad());
        if(locStock.getDescripcion() != null) this.getTaDescripcion().setText(locStock.getDescripcion());
        if(locDeposito != null && locDeposito.getIdDeposito() != -1l) {
            this.getTfDeposito().setText(locDeposito.toString());
        }
        else{
            this.getTfDeposito().setText(null);
        }

        if(locPuntoDeReposicion != null && locPuntoDeReposicion.getIdPuntoDeReposicion() != -1l){
            this.getTaDescripcionPtoRep().setText(locPuntoDeReposicion.getDescripcion());
            this.getTfCantidadAComprar().setText(locPuntoDeReposicion.getCantidadAComprar());
            this.getTfCantidadLimite().setText(locPuntoDeReposicion.getCantidadLimite());
        }
        else{
            this.getTaDescripcionPtoRep().setText(null);
            this.getTfCantidadAComprar().setText(null);
            this.getTfCantidadLimite().setText(null);
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

    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo!=null) campo.setText(null);
        } catch (Exception ex) { }
    }

    private void acomodarSeleccionado() {
        Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
        if (seleccionado != null) {
            int posicion = ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
            this.getElementoPila().getObjetos().set(posicion, seleccionado);
        }
    }

    private int getCantidadObjetosUsados() {
        return this.getElementoPila().getObjetos().size();
    }

    // </editor-fold>


    public String btnVolver_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            retorno = this.prepararParaVolver(Constantes.ACCION_CONSULTAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public Stock getStockABM() {
        if (this.stockABM == null) this.stockABM = new Stock();
        return this.stockABM;
    }
    public void setStockABM(Stock pStockABM) {
        this.stockABM = pStockABM;
    }

    public Deposito getDepositoSeleccionado(){
        return this.depositoSeleccionado;
    }
    public void setDepositoSeleccionado(Deposito pDepositoSeleccionado){
        if(this.depositoSeleccionado == null) this.depositoSeleccionado = new Deposito();
        this.depositoSeleccionado = pDepositoSeleccionado;
    }

    public Bien getBienSeleccionado() {
        if (this.bienSeleccionado == null) this.bienSeleccionado = new Bien();
        return this.bienSeleccionado;
    }
    public void setBienSeleccionado(Bien pBienSeleccionado) {
        this.bienSeleccionado = pBienSeleccionado;
    }
    public String btnSeleccionarPuntoDeReposicion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 3;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            PuntoDeReposicion locPuntoDeReposicion = (PuntoDeReposicion) this.obtenerObjetoDelElementoPila(3, PuntoDeReposicion.class);

            if (locPuntoDeReposicion != null && locPuntoDeReposicion.getStock() != null) {
                this.getRequestBean1().setObjetoABM(locPuntoDeReposicion);
                retorno = "ModificarPuntoDeReposicion";
            }
            else {
                retorno = "AgregarPuntoDeReposicion";
            }

            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(3, PuntoDeReposicion.class));
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarPuntoDeReposicion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if(ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(3, null);
            this.getTaDescripcionPtoRep().setText(null);
            this.getTfCantidadAComprar().setText(null);
            this.getTfCantidadLimite().setText(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }


}
