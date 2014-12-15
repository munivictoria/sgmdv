/*
 * ConsultarArticulo.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.inventario.ABMArticulo;

import muni.framework.ABMContrato.*;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
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
import com.sun.rave.web.ui.component.TextField;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.InformacionTecnica;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.utiles.Constantes;
import muni.compras.ABMFacturaSubsidio.ABMFacturaSubsidio;
//import muni.compras.ABMFacturaProveedor.ConsultarFacturaProveedor;
import muni.compras.ABMFacturaContrato.ABMFacturaContrato;
import muni.CommunicationMesaEntradaBean;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ConsultarArticulo extends AbstractPageBean {

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
    private final String NOMBRE_PAGINA = "Consultar Articulo";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ConsultarArticulo";
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
        Option[] opEstado = null;
        opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(InformacionTecnica.Estado.values(), "may");
        ddEstadoDefaultOptions.setOptions(opEstado);
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
    private TextField tfCosto = new TextField();

    public TextField getTfCosto() {
        return tfCosto;
    }

    public void setTfCosto(TextField tfCosto) {
        this.tfCosto = tfCosto;
    }

    private TextField tfArea = new TextField();

    public TextField getTfArea() {
        return tfArea;
    }

    public void setTfArea(TextField tfArea) {
        this.tfArea = tfArea;
    }
    
    private TextField tfCodigoArticulo = new TextField();

    public TextField getTfCodigoArticulo() {
        return tfCodigoArticulo;
    }

    public void setTfCodigoArticulo(TextField tfCodigoArticulo) {
        this.tfCodigoArticulo = tfCodigoArticulo;
    }

    private TextField tfMarca = new TextField();

    public TextField getTfMarca() {
        return tfMarca;
    }

    public void setTfMarca(TextField tfMarca) {
        this.tfMarca = tfMarca;
    }
    
    private TextField tfEstado = new TextField();

    public TextField getTfEstado() {
        return tfEstado;
    }

    public void setTfEstado(TextField tfEstado) {
        this.tfEstado = tfEstado;
    }
    
    private TextField tfModelo = new TextField();

    public TextField getTfModelo() {
        return tfModelo;
    }

    public void setTfModelo(TextField tfModelo) {
        this.tfModelo = tfModelo;
    }
    
    private TextField tfNumeroSerie = new TextField();

    public TextField getTfNumeroSerie() {
        return tfNumeroSerie;
    }

    public void setTfNumeroSerie(TextField tfNumeroSerie) {
        this.tfNumeroSerie = tfNumeroSerie;
    }
    
    private TextField tfMaterial = new TextField();

    public TextField getTfMaterial() {
        return tfMaterial;
    }

    public void setTfMaterial(TextField tfMaterial) {
        this.tfMaterial = tfMaterial;
    }
    
    private TextField tfColor = new TextField();

    public TextField getTfColor() {
        return tfColor;
    }

    public void setTfColor(TextField tfColor) {
        this.tfColor = tfColor;
    }
    
    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }

    private Label label15 = new Label();

    public Label getLabel15() {
        return label15;
    }

    public void setLabel15(Label label15) {
        this.label15 = label15;
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
    
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tfNombre) {
        this.tfNombre = tfNombre;
    }
    
    private TextArea taDescripcion = new TextArea();

    public TextArea getTaDescripcion() {
        return taDescripcion;
    }

    public void setTaDescripcion(TextArea ta) {
        this.taDescripcion = ta;
    }
    
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label label6) {
        this.label6 = label6;
    }
   
    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label label7) {
        this.label7 = label7;
    }
    
    private Label label8 = new Label();

    public Label getLabel8() {
        return label8;
    }

    public void setLabel8(Label label8) {
        this.label8 = label8;
    }
    
    private Label label9 = new Label();

    public Label getLabel9() {
        return label9;
    }

    public void setLabel9(Label label9) {
        this.label9 = label9;
    }
    
    private Label label10 = new Label();

    public Label getLabel10() {
        return label10;
    }

    public void setLabel10(Label label10) {
        this.label10 = label10;
    }
    
    private Label label11 = new Label();

    public Label getLabel11() {
        return label11;
    }

    public void setLabel11(Label label11) {
        this.label11 = label11;
    }
    
    private Label label12 = new Label();

    public Label getLabel12() {
        return label12;
    }

    public void setLabel12(Label label12) {
        this.label12 = label12;
    }
    
    private Label label13 = new Label();

    public Label getLabel13() {
        return label13;
    }

    public void setLabel13(Label label13) {
        this.label13 = label13;
    }
    
    private Label label14 = new Label();

    public Label getLabel14() {
        return label14;
    }

    public void setLabel14(Label label14) {
        this.label14 = label14;
    }
    
    private DropDown ddEstado = new DropDown();

    public DropDown getDdEstado() {
        return ddEstado;
    }

    public void setDdEstado(DropDown ddEstado) {
        this.ddEstado = ddEstado;
    }

    public SingleSelectOptionsList getDdEstadoDefaultOptions() {
        return ddEstadoDefaultOptions;
    }

    public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
        this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
    }
    private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
    
    private TextField tfFechaEntradaServicio = new TextField();

    public TextField getTfFechaEntradaServicio() {
        return tfFechaEntradaServicio;
    }

    public void setTfFechaEntradaServicio(TextField tfFechaEntradaServicio) {
        this.tfFechaEntradaServicio = tfFechaEntradaServicio;
    }
    
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    
    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label label4) {
        this.label4 = label4;
    }
    
    private TextField tfFechaCompra = new TextField();

    public TextField getTfFechaCompra() {
        return tfFechaCompra;
    }

    public void setTfFechaCompra(TextField tfFechaCompra) {
        this.tfFechaCompra = tfFechaCompra;
    }

    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    // </editor-fold>

    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ConsultarArticulo() {
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
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
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
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
    }

    /** 
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina,
     * ya sea directamente mediante un URL o de manera indirecta a trav�s de la navegaci�n de p�ginas.
     * Puede personalizar este m�todo para adquirir recursos que se necesitar�n
     * para los controladores de eventos y m�todos del proceso, sin tener en cuenta si esta
     * p�gina realiza procesamiento de devoluci�n de env�os.</p>
     * 
     * <p>Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores
     * de propiedad de los componentes <strong>no</strong> representan ning�n
     * valor enviado con esta petici�n.  En su lugar, representan los
     * valores de propiedades que se guardaron para esta vista cuando se proces�.</p>
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
            log("AgregarArticulo Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

    }

    /** 
     * <p>M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha
     * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este m�todo
     * <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que
     * est� procesando el env�o de un formulario.  Puede personalizar este m�todo para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /** 
     * <p>M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento.
     * <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que
     * se procesa, no se llamar�, por ejemplo, en una p�gina que
     * ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina.  Puede personalizar
     * este m�todo para asignar recursos necesarios para procesar
     * esta p�gina.</p>
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
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de
     * esta petici�n, si se llam� al m�todo <code>init()</code> (sin tener en cuenta
     * si se trata de la p�gina que se ha procesado o no).  Puede personalizar este
     * m�todo para liberar los recursos adquiridos en los m�todos <code>init()</code>,
     * <code>preprocess()</code> o <code>prerender()</code> (o
     * durante la ejecuci�n de un controlador de eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new Articulo());
        ep.getObjetos().add(ind++, new Area()); 
        ep.getObjetos().add(ind++, new InformacionTecnica());

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        Articulo articulo = (Articulo) this.obtenerObjetoDelElementoPila(ind++, Articulo.class);
        Area area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
        InformacionTecnica informacionTecnica = (InformacionTecnica) this.obtenerObjetoDelElementoPila(ind++, InformacionTecnica.class);

        InformacionTecnica.Estado estado = null;
        
        //Obtener datos de los TextField
        Object codigo = this.getTfCodigoArticulo().getText();
        Object nombre = this.getTfNombre().getText();
        Object descripcion = this.getTaDescripcion().getText();
        Object fechaCompra = this.getTfFechaCompra().getText();
        Object fechaEntradaServicio = this.getTfFechaEntradaServicio().getText();
        Object costo = this.getTfCosto().getText();
        Object marca = this.getTfMarca().getText();
        Object modelo = this.getTfModelo().getText();
        Object numeroSerie = this.getTfNumeroSerie().getText();
        Object material = this.getTfMaterial().getText();
        Object color = this.getTfColor().getText();
        Object estadoSelected = ddEstado.getSelected();        

        if (area.getIdArea() == -1) {
            area = null;
        }
        articulo.setArea(area);
        
        if (codigo != null && codigo != "") {
            articulo.setCodigo(codigo.toString());
        } else {
            articulo.setCodigo(null);
        }

        if (nombre != null && nombre != "") {
            articulo.setNombre(nombre.toString());
        } else {
            articulo.setNombre(null);
        }
        
        if (descripcion != null && descripcion != "") {
            articulo.setDescripcion(descripcion.toString());
        } else {
            articulo.setDescripcion(null);
        }

        if (fechaCompra != null && fechaCompra != "") {
            articulo.setFechaCompra(Conversor.getFechaCortaDeString(fechaCompra.toString()));
        } else {
            articulo.setFechaCompra(null);
        }
        
        if (fechaEntradaServicio != null && fechaEntradaServicio != "") {
            articulo.setFechaPuestaServicio(Conversor.getFechaCortaDeString(fechaEntradaServicio.toString()));
        } else {
            articulo.setFechaPuestaServicio(null);
        }
        
        if (costo != null && costo != "") {
            articulo.setCosto(Conversor.getDoubleDeString(costo.toString()));
        } else {
            articulo.setCosto(null);
        }
        
        if (marca != null && marca != "") {
            informacionTecnica.setMarca(marca.toString());
        } else {
            informacionTecnica.setMarca(null);
        }
        
        if (modelo != null && modelo != "") {
            informacionTecnica.setModelo(modelo.toString());
        } else {
            informacionTecnica.setModelo(null);
        }

        if (numeroSerie != null && numeroSerie != "") {
            informacionTecnica.setNumeroSerie(numeroSerie.toString());
        } else {
            informacionTecnica.setNumeroSerie(null);
        }
        
        if (material != null && material != "") {
            informacionTecnica.setMaterial(material.toString());
        } else {
            informacionTecnica.setMaterial(null);
        }
        
        if (color != null && color != "") {
            informacionTecnica.setColor(color.toString());
        } else {
            informacionTecnica.setColor(null);
        }
        
        if ((estadoSelected != null) && (estadoSelected.toString().length() > 0)) {
            estado = InformacionTecnica.Estado.valueOf(estadoSelected.toString());
        } else {
            estado = null;
        }
        if (estado != null) {
            informacionTecnica.setEstado(estado);
        } else {
            informacionTecnica.setEstado(null);
        }
        
        articulo.setInformacionTecnica(informacionTecnica);

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, articulo);
        this.getElementoPila().getObjetos().set(ind++, area);
        this.getElementoPila().getObjetos().set(ind++, informacionTecnica);
    }

    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        Articulo articulo = null;
        Area area = null;
        InformacionTecnica informacionTecnica = null;
        
        if (this.getRequestBean1().getObjetoABM() != null) {
            articulo = (Articulo) this.getRequestBean1().getObjetoABM();
            area = (Area) articulo.getArea();
            informacionTecnica = (InformacionTecnica) articulo.getInformacionTecnica();
            
            int ind = 0;
            this.getElementoPila().getObjetos().set(ind++, articulo);
            this.getElementoPila().getObjetos().set(ind++, area);
            this.getElementoPila().getObjetos().set(ind++, informacionTecnica);
        }
        
        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();

            if (seleccionado instanceof Area) {
                area = (Area) seleccionado;
                this.getElementoPila().getObjetos().set(1, area);
            }
        }
        
        int ind = 0;
        articulo = (Articulo) this.obtenerObjetoDelElementoPila(ind++, Articulo.class);
        area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
        informacionTecnica = (InformacionTecnica) this.obtenerObjetoDelElementoPila(ind++, InformacionTecnica.class);

        this.getTfCodigoArticulo().setText(articulo.getCodigo());        
        this.getTfNombre().setText(articulo.getNombre());
        this.getTaDescripcion().setText(articulo.getDescripcion());
        this.getTfEstado().setText(articulo.getEstadoContable());
        this.getTfFechaCompra().setText(Conversor.getStringDeFechaCorta(articulo.getFechaCompra()));
        this.getTfFechaEntradaServicio().setText(Conversor.getStringDeFechaCorta(articulo.getFechaPuestaServicio()));
        this.getTfCosto().setText(Conversor.getStringDeDouble(articulo.getCosto()));                
        if (area != null && area.getIdArea() != -1) {
            this.getTfArea().setText(area);
        }
        this.getTfMarca().setText(informacionTecnica.getMarca());
        this.getTfModelo().setText(informacionTecnica.getModelo());
        this.getTfNumeroSerie().setText(informacionTecnica.getNumeroSerie());
        this.getTfMaterial().setText(informacionTecnica.getMaterial());
        this.getTfColor().setText(informacionTecnica.getColor());
        this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(informacionTecnica.getEstado())));
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
            campo.setText("");
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

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ABMFacturaSubsidio getcompras$ABMFacturaSubsidio$AgregarFacturaSubsidio() {
        return (ABMFacturaSubsidio) getBean("compras$ABMFacturaSubsidio$AgregarFacturaSubsidio");
    }

//    /**
//     * <p>Return a reference to the scoped data bean.</p>
//     *
//     * @return reference to the scoped data bean
//     */
//    protected ConsultarFacturaProveedor getcompras$ABMFacturaProveedor$ConsultarFacturaProveedor() {
//        return (ConsultarFacturaProveedor) getBean("compras$ABMFacturaProveedor$ConsultarFacturaProveedor");
//    }
    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ABMFacturaContrato getcompras$ABMFacturaContrato$AgregarFacturaContrato() {
        return (ABMFacturaContrato) getBean("compras$ABMFacturaContrato$AgregarFacturaContrato");
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
