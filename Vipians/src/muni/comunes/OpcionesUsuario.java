/*
 * OpcionesUsuario.java
 *
 * Created on 7 de noviembre de 2006, 12:19
 * Copyright Trascender
 */
package muni.comunes;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.Page;
import javax.faces.FacesException;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PasswordField;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;
import com.sun.rave.web.ui.component.MessageGroup;
import muni.*;
import com.sun.rave.web.ui.component.Script;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class OpcionesUsuario extends AbstractPageBean {
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
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    
    // Atributos propios de la pagina.
    // CAMBIAR: Vincular a campos ocultos.
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
    private final String NOMBRE_PAGINA = "Opciones de Usuario";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "OpcionesUsuario";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = true;
    // </editor-fold>
    
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

    private StaticText stTitulo = new StaticText();

    public StaticText getStTitulo() {
        return stTitulo;
    }

    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }

    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }

    private Button btnCancelar = new Button();

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button b) {
        this.btnCancelar = b;
    }

    private Button btnGuardar = new Button();

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(Button b) {
        this.btnGuardar = b;
    }

    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private PasswordField pfAnterior = new PasswordField();

    public PasswordField getPfAnterior() {
        return pfAnterior;
    }

    public void setPfAnterior(PasswordField pf) {
        this.pfAnterior = pf;
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

    public void setLabel4(Label l) {
        this.label4 = l;
    }

    private PasswordField pfNuevo1 = new PasswordField();

    public PasswordField getPfNuevo1() {
        return pfNuevo1;
    }

    public void setPfNuevo1(PasswordField pf) {
        this.pfNuevo1 = pf;
    }

    private PasswordField pfNuevo2 = new PasswordField();

    public PasswordField getPfNuevo2() {
        return pfNuevo2;
    }

    public void setPfNuevo2(PasswordField pf) {
        this.pfNuevo2 = pf;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }

    private Script scriptFinal1 = new Script();

    public Script getScriptFinal1() {
        return scriptFinal1;
    }

    public void setScriptFinal1(Script s) {
        this.scriptFinal1 = s;
    }

    /** 
     * <p>Construir una instancia de bean de pagina.</p>
     */
    public OpcionesUsuario() {
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (CommunicationExcepcionesBean)getBean("CommunicationExcepcionesBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected CommunicationCajaBean getCommunicationCajaBean() {
        return (CommunicationCajaBean)getBean("CommunicationCajaBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected CommunicationComprasBean getCommunicationComprasBean() {
        return (CommunicationComprasBean)getBean("CommunicationComprasBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected CommunicationSAICBean getCommunicationSAICBean() {
        return (CommunicationSAICBean)getBean("CommunicationSAICBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (CommunicationHabilitacionesBean)getBean("CommunicationHabilitacionesBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected ComunicationCatastroBean getComunicationCatastroBean() {
        return (ComunicationCatastroBean)getBean("ComunicationCatastroBean");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected ComunicationBean getComunicationBean() {
        return (ComunicationBean)getBean("ComunicationBean");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1)getBean("RequestBean1");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
    }

    /** 
     * <p>Metodo de devolucion de llamada al que se llama cuando se navega hasta esta pagina,
     * ya sea directamente mediante un URL o de manera indirecta a traves de la navegacion de paginas.
     * Puede personalizar este metodo para adquirir recursos que se necesitaran
     * para los controladores de eventos y metodos del proceso, sin tener en cuenta si esta
     * pagina realiza procesamiento de devolucion de envios.</p>
     * 
     * <p>Tenga en cuenta que si la peticion actual es una devolucion de envio, los valores
     * de propiedad de los componentes <strong>no</strong> representan ningun
     * valor enviado con esta peticion.  En su lugar, representan los
     * valores de propiedades que se guardaron para esta vista cuando se proceso.</p>
     */
    public void init() {
        // Realizar iniciaciones heredadas de la superclase
        super.init();
        // Realizar inicio de aplicacion que debe finalizar
        // *antes* de que se inicien los componentes administrados
        // TODO - Agregar codigo de inicio propio aqui

        // <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
        // Iniciar componentes administrados automaticamente
        // *Nota* - esta logica NO debe modificarse
        try {
            _init();
        } catch (Exception e) {
            log("OpcionesUsuario Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicacion que debe finalizar
        // *despues* de que se inicien los componentes administrados
        // TODO - Agregar codigo de inicio propio aqui

    }

    /** 
     * <p>Metodo de devolucion de llamada al que se llama cuando el arbol de componentes se ha
     * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este metodo
     * <strong>solo</strong> se llamara en una peticion de devolucion de envio que
     * esta procesando el envio de un formulario.  Puede personalizar este metodo para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /** 
     * <p>Metodo de devolucion de llamada al que se llama justo antes del procesamiento.
     * <strong>Solo</strong> se llamara a este metodo en la pagina que
     * se procesa, no se llamara, por ejemplo, en una pagina que
     * ha procesado una devolucion de envio y a continuacion ha navegado hasta otra pagina.  Puede personalizar
     * este metodo para asignar recursos necesarios para procesar
     * esta pagina.</p>
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
            // APLICAR LOGICA AQUI.. ver si es asi realmente..
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
     * <p>Metodo de devolucion de llamada al que se llama cuando se completa el procesamiento de
     * esta peticion, si se llamo al metodo <code>init()</code> (sin tener en cuenta
     * si se trata de la pagina que se ha procesado o no).  Puede personalizar este
     * metodo para liberar los recursos adquiridos en los metodos <code>init()</code>,
     * <code>preprocess()</code> o <code>prerender()</code> (o
     * durante la ejecucion de un controlador de eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new String()); // pass anterior
        ep.getObjetos().add(ind++, new String()); // pass nuevo 1
        ep.getObjetos().add(ind++, new String()); // pass nuevo 2
        
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        int ind = 0;
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        String passAnt = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
        String passNue1 = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
        String passNue2 = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
        
        Object pas1 = this.getPfAnterior().getText();
        Object pas2 = this.getPfNuevo1().getText();
        Object pas3 = this.getPfNuevo2().getText();
        
        if(pas1 != null && pas1 != "") passAnt = pas1.toString(); else passAnt = null;
        if(pas2 != null && pas2 != "") passNue1 = pas2.toString(); else passNue1 = null;
        if(pas3 != null && pas3 != "") passNue2 = pas3.toString(); else passNue2 = null;
        
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, passAnt);
        this.getElementoPila().getObjetos().set(ind++, passNue1);
        this.getElementoPila().getObjetos().set(ind++, passNue2);
    }
    
    private void mostrarEstadoObjetosUsados() {
        int ind = 0;
        // CAMBIAR: Obtener la instancia por cada objeto manejado en la pagina
        String passAnt = null;
        String passNue1 = null;
        String passNue2 = null;
        
// Ariel - Por ahora nada es requerido
//        if (this.getRequestBean1().getObjetoSeleccion() != null) {
//            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
//            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.
//            if (seleccionado instanceof Municipalidad) {
//                if (seleccionado != null) {
//                    muni = (Municipalidad) seleccionado;
//                    this.getElementoPila().getObjetos().set(1, muni);
//                }
//            }
//        }
        
        ind = 0;
        Object pAn = this.getElementoPila().getObjetos().get(ind++);
        Object pN1 = this.getElementoPila().getObjetos().get(ind++);
        Object pN2 = this.getElementoPila().getObjetos().get(ind++);
        
        if (pAn!=null) this.getPfAnterior().setText(pAn.toString());
        if (pN1!=null) this.getPfNuevo1().setText(pN1.toString());
        if (pN2!=null) this.getPfNuevo2().setText(pN2.toString());
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
    
    private String prepararParaVolver() {
        String retorno = null;
        ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
        
        if (locElementoAnterior != null) {
            this.getSessionBean1().getMgrPilas().removeElemento(this.getElementoPila());
            this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
            this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
            this.getRequestBean1().setElementoPilaPaginaAnt(locElementoAnterior);
            retorno = locElementoAnterior.getCasoNavegacion();
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
    // </editor-fold>
    
    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            //APLICAR LOGICA AQUI...
            try {
                this.guardarEstadoObjetosUsados();
                
                // Diferentes opciones (agregar a medida que sea necesario)
                boolean cambiarPassword = false;
                
                // Usuario del Session
                Usuario user = this.getSessionBean1().getUsuario();
                
                // Obtencion de datos
                String pasAnt = (String) this.obtenerObjetoDelElementoPila(0, String.class);
                String pasNue1 = (String) this.obtenerObjetoDelElementoPila(1, String.class);
                String pasNue2 = (String) this.obtenerObjetoDelElementoPila(2, String.class);
                
                if (pasAnt.length()>0 || pasNue1.length()>0 || pasNue2.length()>0) {
                    
                    Validador v = new Validador();
                    
                    if (pasAnt.length()==0 || pasNue1.length()==0 || pasNue2.length()==0) {
                        if (pasAnt.length()==0) this.getPfAnterior().setValid(false);
                        if (pasNue1.length()==0) this.getPfNuevo1().setValid(false);
                        if (pasNue2.length()==0) this.getPfNuevo2().setValid(false);
                        v.getErrores().add("Debe completar los tres campos para cambiar la contrase\361a actual.");
                    }
                    else {
                        String pass = user.getPassword();
                        if (!v.sonIguales(pass, pasAnt.toString())) {
                            this.getPfAnterior().setValid(false);
                            v.getErrores().add("La Contrase\361a Anterior ingresada no es correcta.");
                        }

                        UIComponent[] igualdad1 = new UIComponent[1];
                        String[] nomIgualdad1 = new String[1];
                        UIComponent[] igualdad2 = new UIComponent[1];
                        String[] nomIgualdad2 = new String[1];
                        UIComponent[] minimo = new UIComponent[2];
                        Integer[] intMin = new Integer[2];
                        String[] nomMin = new String[2];
                        
                        int pos = 0;
                        igualdad1[pos] = this.getPfNuevo1();
                        nomIgualdad1[pos++] = "Nueva Contrase\361a";
                        pos = 0;
                        igualdad2[pos] = this.getPfNuevo2();
                        nomIgualdad2[pos++] = "Repetir Nueva Contrase\361a"; 

                        pos = 0;
                        minimo[pos] = this.getPfNuevo1();
                        intMin[pos] = new Integer(8);
                        nomMin[pos++] = "Nueva Contrase\361a";
                        minimo[pos] = this.getPfNuevo2();
                        intMin[pos] = new Integer(8);
                        nomMin[pos++] = "Repetir Nueva Contrase\361a";
                        
                        v.sonIguales(igualdad1, igualdad2, nomIgualdad1, nomIgualdad2);
                        v.cumplenLargo(minimo, nomMin, intMin, new Integer[2]);
                    }
                    
                    if (v.getErrores().size() > 0) {
                        error("Existen Errores:");
                        for (int i = 0; i < v.getErrores().size(); i++) {
                            warn(v.getErrores().toArray()[i].toString());
                        }
                        return null;
                    }
                    
                    cambiarPassword = true;
                }
                
                // Agregar los if necesarios segun las diferentes opciones.
                if (cambiarPassword) user.setPassword(pasNue1);
                
                // Agregar los boolean con || (or) para guardar cambios.
                if (cambiarPassword) {
                    // CAMBIAR: Utilizar el EJBClient y ListDataProvider adecuados.
                    this.getComunicationBean().getRemoteSystemUsuario().setLlave(this.getSessionBean1().getLlave());
                    this.getComunicationBean().getRemoteSystemUsuario().cambiarClave(pasAnt, pasNue1);
                    info("Los cambios han sido guardados exitosamente.");
                }
                else {
                    info("No hay cambios para guardar.");
                }
                
            } catch (Exception ex) {
                log(CASO_NAVEGACION+"_GuardarCambiosError:", ex);
                error(NOMBRE_PAGINA+" - Guardar Cambios: " + ex.getMessage());
            }
            // CAMBIAR: comentar esta linea y cambiar el retorno
            // cuando la pagina es una de inicio de transaccion
            retorno = this.prepararParaVolver();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnCancelar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            //APLICAR LOGICA AQUI...
            
            retorno = "Main";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
}
