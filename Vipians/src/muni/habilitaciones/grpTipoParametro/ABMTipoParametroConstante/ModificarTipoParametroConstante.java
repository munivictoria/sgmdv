/*
 * ModificarTipoParametroConstante.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpTipoParametro.ABMTipoParametroConstante;

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
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import com.sun.rave.web.ui.model.Option;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP.UnidadMedida;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.trascender.presentacion.utiles.Constantes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ModificarTipoParametroConstante extends AbstractPageBean {
    
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;
    public Long getIdPagina() { return idPagina; }
    public void setIdPagina(Long idPagina) { this.idPagina = idPagina; }
    public Long getIdSubSesion() { return idSubSesion; }
    public void setIdSubSesion(Long idSubSesion) { this.idSubSesion = idSubSesion; }
    
    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Modificar Par\341metro Constante";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarTipoParametroConstante";
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
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(UnidadMedida.values(),"cap");
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
    
    public TextField getTfValor() {
        return tfValor;
    }
    
    public void setTfValor(TextField tf) {
        this.tfValor = tf;
    }

    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    private TextField tfNombreVariable = new TextField();

    public TextField getTfNombreVariable() {
        return tfNombreVariable;
    }

    public void setTfNombreVariable(TextField tf) {
        this.tfNombreVariable = tf;
    }

    private RadioButton rbFijo = new RadioButton();

    public RadioButton getRbFijo() {
        return rbFijo;
    }

    public void setRbFijo(RadioButton rb) {
        this.rbFijo = rb;
    }

    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }

    private RadioButton rbPorcentual = new RadioButton();

    public RadioButton getRbPorcentual() {
        return rbPorcentual;
    }

    public void setRbPorcentual(RadioButton rb) {
        this.rbPorcentual = rb;
    }

    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }
    // </editor-fold>
    
    
    /**
     * <p>Construir una instancia de bean de página.</p>
     */
    public ModificarTipoParametroConstante() {
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
     */
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
            log("ModificarTipoParametroConstante Initialization Failure", e);
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
        ep.getObjetos().add(ind++, new TipoParametroConstante());
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        TipoParametroConstante tipoParametroConstante = (TipoParametroConstante) this.obtenerObjetoDelElementoPila(0, TipoParametroConstante.class);
        
        Object nombre = this.getTfNombre().getText();
        Object nombreVariable = this.getTfNombreVariable().getText();
        Object valor = this.getTfValor().getText();
        Object fijo = this.getRbFijo().getValue();
        
        if (nombre != null && nombre != "") tipoParametroConstante.setNombre(nombre.toString());
        else tipoParametroConstante.setNombre(null);
        if (nombreVariable != null && nombreVariable != "") tipoParametroConstante.setNombreVariable(nombreVariable.toString());
        else tipoParametroConstante.setNombreVariable(null);

        if (valor != null && valor != "") tipoParametroConstante.setValor(Conversor.getDoubleDeString(valor.toString()));
        else tipoParametroConstante.setValor(null);
        if (fijo != null && fijo != "") tipoParametroConstante.setFijo(((Boolean)fijo).booleanValue());
        this.getElementoPila().getObjetos().set(0, tipoParametroConstante);
    }
    
    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        TipoParametroConstante tipoParametroConstante = null;
        
        this.acomodarSeleccionado();
        
        if (this.getRequestBean1().getObjetoABM() != null) {
            tipoParametroConstante = (TipoParametroConstante) this.getRequestBean1().getObjetoABM();
            this.getElementoPila().getObjetos().set(0, tipoParametroConstante);
        }

        tipoParametroConstante = (TipoParametroConstante) this.obtenerObjetoDelElementoPila(0, TipoParametroConstante.class);

        //Double valor = null;
//        valor = Math.floor(tipoParametroConstante.getValor());

        //BigDecimal valor = new BigDecimal(tipoParametroConstante.getValor().toString());

        this.getTfNombre().setText(tipoParametroConstante.getNombre());
        this.getTfNombreVariable().setText(tipoParametroConstante.getNombreVariable());
        if (tipoParametroConstante.getValor() != null) this.getTfValor().setText(Conversor.getStringDeDouble(tipoParametroConstante.getValor()));
        this.getRbFijo().setValue(new Boolean(tipoParametroConstante.isFijo()));
        this.getRbPorcentual().setValue(new Boolean(!tipoParametroConstante.isFijo()));
        
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
            if (campo!=null) campo.setText("");
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
    
    
    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
        
        if (ultimo) {
            
            //APLICAR LOGICA AQUI...
            try {
                this.guardarEstadoObjetosUsados();
                
                // CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                UIComponent[] noVacios = null;
                String[] nomNoVacios = null;
                UIComponent[] flotantes = null;
                String[] nomFlotantes = null;
//
                noVacios = new UIComponent[3];
                nomNoVacios = new String[3];
                flotantes = new UIComponent[1];
                nomFlotantes = new String[1];
//
                int pos = 0;
                noVacios[pos] = this.getTfNombre();
                nomNoVacios[pos++] = "Nombre";
                noVacios[pos] = this.getTfNombreVariable();
                nomNoVacios[pos++] = "Nombre de Variable";
                noVacios[pos] = this.getTfValor();
                nomNoVacios[pos++] = "Valor";

                pos = 0;
                flotantes[pos] = this.getTfValor();
                nomFlotantes[pos++] = "Valor";

                v.noSonVacios(noVacios, nomNoVacios);
                v.sonFlotantes(flotantes, nomFlotantes);
//                 CAMBIAR:

                TipoParametroConstante tipoParametroConstante = (TipoParametroConstante) this.obtenerObjetoDelElementoPila(0, TipoParametroConstante.class);
                // CAMBIAR: Utilizar el EJBClient adecuado.
                this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());

//                if(this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaParametrosConstantes(tipoParametroConstante.getNombre()).size() > 0){
//                    TipoParametroConstante locTipoParametroConstante = (TipoParametroConstante) this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaParametrosConstantes(tipoParametroConstante.getNombre()).get(0);
//                    if(locTipoParametroConstante.getIdTipoParametro() != tipoParametroConstante.getIdTipoParametro()){
//                        String msg = "No se puede modificar a un Tipo de Parámetro de nombre ya existente. ";
//                        v.getErrores().add(msg);
//                        this.getTfNombre().setValid(false);
//                    }
//                }
                
                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                tipoParametroConstante = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().updateTipoParametroConstante(tipoParametroConstante);
                
                this.getRequestBean1().setRespuestaABM(tipoParametroConstante);
                info("El Par\341metro Constante se modific\363 exitosamente.");
                
                retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);
            } catch (Exception ex) {
                if (ex instanceof TrascenderException) {
                    int codigoError = ((TrascenderException)ex).getCodeTrascenderException();
                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) retorno = null;
                    else retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
                }
                log(CASO_NAVEGACION+"_GuardarError:", ex);
                error(NOMBRE_PAGINA+" - Guardar: " + ex.getMessage());
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
    
    
    private void cargarValoresPorDefecto() {
        // CAMBIAR: Obtener los valores por defecto.
        return;
    }
    
}
