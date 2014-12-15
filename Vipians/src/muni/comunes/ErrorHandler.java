/*
 * ErrorHandler.java
 *
 * Created on 30 de enero de 2007, 08:06
 * Copyright Trascender SRL
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
import com.sun.rave.web.ui.component.PageAlert;
import com.sun.rave.web.ui.component.Alert;
import com.sun.rave.web.ui.component.Label;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ErrorHandler extends AbstractPageBean {
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


    /** 
     * <p>Construir una instancia de bean de pagina.</p>
     */
    public ErrorHandler() {
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (muni.CommunicationExcepcionesBean)getBean("CommunicationExcepcionesBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.CommunicationCajaBean getCommunicationCajaBean() {
        return (muni.CommunicationCajaBean)getBean("CommunicationCajaBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean)getBean("CommunicationComprasBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean)getBean("CommunicationSAICBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1)getBean("ApplicationBean1");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean)getBean("CommunicationHabilitacionesBean");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1)getBean("RequestBean1");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1)getBean("SessionBean1");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean)getBean("ComunicationBean");
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
            log("ErrorHandler Initialization Failure", e);
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
        
        this.getSessionBean1().setRutaOperacion("\241 Ha ocurrido un Error !");
        
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
}

