/*
 * PaginaCaducidad.java
 *
 * Created on 20 de septiembre de 2006, 08:36
 * Copyright Asus
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
import com.sun.rave.web.ui.component.PanelLayout;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.MessageGroup;
import muni.*;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Alert;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class PaginaCaducidad extends AbstractPageBean {
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

    private String paginaSiguiente = null;

    private HiddenField hidPaginaSiguiente = new HiddenField();

    public HiddenField getHidPaginaSiguiente() {
        return hidPaginaSiguiente;
    }

    public void setHidPaginaSiguiente(HiddenField hf) {
        this.hidPaginaSiguiente = hf;
    }

    private Alert alert1 = new Alert();

    public Alert getAlert1() {
        return alert1;
    }

    public void setAlert1(Alert a) {
        this.alert1 = a;
    }

    /** 
     * <p>Construir una instancia de bean de pagina.</p>
     */
    public PaginaCaducidad() {
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
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected ComunicationCatastroBean getComunicationCatastroBean() {
        return (ComunicationCatastroBean)getBean("ComunicationCatastroBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
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
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1)getBean("RequestBean1");
    }


    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected ComunicationBean getComunicationBean() {
        return (ComunicationBean)getBean("ComunicationBean");
    }


    /** 
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta pagina,
     * ya sea directamente mediante un URL o de manera indirecta a trav�s de la navegaci�n de paginas.
     * Puede personalizar este m�todo para adquirir recursos que se necesitar�n
     * para los controladores de eventos y m�todos del proceso, sin tener en cuenta si esta
     * pagina realiza procesamiento de devoluci�n de env�os.</p>
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
            log("PaginaCaducidad Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
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
     * <strong>S�lo</strong> se llamar� a este m�todo en la pagina que
     * se procesa, no se llamar�, por ejemplo, en una pagina que
     * ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra pagina.  Puede personalizar
     * este m�todo para asignar recursos necesarios para procesar
     * esta pagina.</p>
     */
    public void prerender() {
        if (this.getPaginaSiguiente() == null) {
            this.setPaginaSiguiente(this.getRequestBean1().getCasoNavegacionPostCaducidad());
        }
        this.getSessionBean1().setRutaOperacion(null);
    }

    /** 
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de
     * esta petici�n, si se llam� al m�todo <code>init()</code> (sin tener en cuenta
     * si se trata de la pagina que se ha procesado o no).  Puede personalizar este
     * m�todo para liberar los recursos adquiridos en los m�todos <code>init()</code>,
     * <code>preprocess()</code> o <code>prerender()</code> (o
     * durante la ejecuci�n de un controlador de eventos).</p>
     */
    public void destroy() {
    }


    public String button1_action() {
        return this.getPaginaSiguiente();
    }

    public String getPaginaSiguiente() {
        return paginaSiguiente;
    }

    public void setPaginaSiguiente(String paginaSiguiente) {
        this.paginaSiguiente = paginaSiguiente;
    }


    public String alert1_action() {
        return this.getPaginaSiguiente();
    }
}

