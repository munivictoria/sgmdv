/*
 * Login.java
 *
 * Created on 22 de agosto de 2006, 15:11
 * Copyright Asus
 */
package muni;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.*;
import java.rmi.NoSuchObjectException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class Logout extends AbstractPageBean {
    
    private Html html1;
    private Page page1;
    private Head head1;
    private Body body1;
    private Form form1;

    public Form getForm1() {
        return form1;
    }

    public void setForm1(Form form1) {
        this.form1 = form1;
    }

    public Body getBody1() {
        return body1;
    }

    public void setBody1(Body body1) {
        this.body1 = body1;
    }

    public Head getHead1() {
        return head1;
    }

    public void setHead1(Head head1) {
        this.head1 = head1;
    }

    public Html getHtml1() {
        return html1;
    }

    public void setHtml1(Html html1) {
        this.html1 = html1;
    }

    public Page getPage1() {
        return page1;
    }

    public void setPage1(Page page1) {
        this.page1 = page1;
    }
    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public Logout() {
    }
    
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1)getBean("RequestBean1");
    }
    
    
    /**
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
    }
    
    protected ComunicationBean getComunicationBean() {
        return (ComunicationBean)getBean("ComunicationBean");
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
        
        /**
         * Si llega a este Bean hay que hacer Logout. Por ahora, los ViewExpiredException te lanzan aqui.
         */
        
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (this.getSessionBean1() != null && this.getSessionBean1().getLlave() != 0L) {
                this.getComunicationBean().getRemoteSystemUsuario().logout(this.getSessionBean1().getLlave());
            }
            if (session != null) {
                session.invalidate();
            }
            this.getExternalContext().redirect("/Vipians/faces/Login.jsp");
        } catch (NoSuchObjectException nsoe) {
            // No se pudo recuperar un archivo .ser que manten�a la sesion del Usuario.
                //java.rmi.NoSuchObjectException: Could not activate; failed to restore state; CausedByException is:
                //[JBOSS_HOME]\server\default\tmp\sessions\SystemUsuario-f17onoec-5\f17y73hi-w.ser (El sistema no puede hallar el archivo especificado)
        } catch (Exception ex) {
            log("LogOutError_", ex);
            error("Salir: "+ex.getMessage());
        }
//        FacesContext fc = FacesContext.getCurrentInstance();
//        fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "Logout");

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

}

