/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMParcela;

import javax.faces.FacesException;

import muni.ApplicationBean1;
import muni.CommunicationCajaBean;
import muni.CommunicationComprasBean;
import muni.CommunicationExcepcionesBean;
import muni.CommunicationHabilitacionesBean;
import muni.CommunicationSAICBean;
import muni.ComunicationBean;
import muni.ComunicationCatastroBean;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.trascender.catastro.recurso.persistent.Parcela;

/**
 *
 * @author marcosdy
 */
public class ImprimirParcela extends AbstractPageBean {

    private String idParcela;

    public String getIdParcela() {
        this.cargarDatos();        
        return idParcela;
    }

    public void setIdParcela(String idParcela) {
        this.idParcela = idParcela;
    }
    
    public void birtViewer() {
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }

    

    public void delay(int howLong) {
        for (int i = 1; i <= howLong; i++) {
            double garbage = Math.PI * Math.PI;
        }
    }


    public void cargarDatos() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        Parcela parcela = (Parcela) this.getSessionBean1().getObjetoImpresion();
        
        
        if (parcela != null){
            this.idParcela = Long.toString(parcela.getIdParcela());
        }
        

    }
    // Manejo de lo q esta en session
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     *
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
// Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here

// <editor-fold defaultstate="collapsed" desc="Visual-Web-managed Component Initialization">
// Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
    }

    /**
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    public void preprocess() {
    }

    /**
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    public void prerender() {
    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
     */
    public void destroy() {
    }

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() {
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationCajaBean getCommunicationCajaBean() {
        return (CommunicationCajaBean) getBean("CommunicationCajaBean");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ComunicationBean getComunicationBean() {
        return (ComunicationBean) getBean("ComunicationBean");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationSAICBean getCommunicationSAICBean() {
        return (CommunicationSAICBean) getBean("CommunicationSAICBean");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected CommunicationComprasBean getCommunicationComprasBean() {
        return (CommunicationComprasBean) getBean("CommunicationComprasBean");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ComunicationCatastroBean getComunicationCatastroBean() {
        return (ComunicationCatastroBean) getBean("ComunicationCatastroBean");
    }
}