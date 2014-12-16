/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.saic.ABMEstadoCuenta;

import javax.faces.FacesException;

import muni.ApplicationBean1;
import muni.CommunicationCajaBean;
import muni.CommunicationComprasBean;
import muni.CommunicationExcepcionesBean;
import muni.CommunicationHabilitacionesBean;
import muni.CommunicationSAICBean;
import muni.ComunicationBean;
import muni.ComunicationCatastroBean;
import muni.RequestBean1;

import com.sun.rave.web.ui.appbase.AbstractPageBean;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version ImprimirEstadoCuenta.java
 * @version Created on 11/12/2009, 11:18:00
 * @author maru
 */

public class ImprimirEstadoCuenta extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private String obligacion;
    private String tramite;
    private String periodo;

    private String idObligacion;
    
    private String vencimiento;
    private String monto;
    private String interes;
    private String multa;
    private String tipoDeuda;
    private String estado;
    private String usuario;
    private String recargo;

    private Long idPagina = null;
    private Long idSubSesion = null;
    public Long getIdPagina() { return idPagina; }
    public void setIdPagina(Long idPagina) { this.idPagina = idPagina; }
    public Long getIdSubSesion() { return idSubSesion; }
    public void setIdSubSesion(Long idSubSesion) { this.idSubSesion = idSubSesion; }
    

    public void birtViewer(){
    }

     /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }
    
    public String getObligacion() {
        this.cargarDatos();
        return obligacion;
    }

    public void setObligacion(String obligacion) {
        this.obligacion = obligacion;
    }
    
    public String getIdObligacion() {
        this.cargarDatos();
        return idObligacion;
    }
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public void setIdObligacion(String idObligacion) {
        this.idObligacion = idObligacion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }

    

    public String getTipoDeuda() {
        return tipoDeuda;
    }

    public void setTipoDeuda(String tipoDeuda) {
        this.tipoDeuda = tipoDeuda;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getRecargo() {
        return recargo;
    }

    public void setRecargo(String recargo) {
        this.recargo = recargo;
    }


   
    //Carga los parámetros del reporte Estado Cuenta del Contribuyente
    public void cargarDatos(){

        try {

            while(this.getSessionBean1().getObjetoImpresion()==null){
                Thread.sleep(10000);
            }
           this.getSessionBean1().setObjetoImpresion(null);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    private void _init() throws Exception {
    }
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public ImprimirEstadoCuenta() {
    }

    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("ImprimirEstadoCuenta Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
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
    protected CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
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
    protected ComunicationCatastroBean getComunicationCatastroBean() {
        return (ComunicationCatastroBean) getBean("ComunicationCatastroBean");
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
    protected CommunicationCajaBean getCommunicationCajaBean() {
        return (CommunicationCajaBean) getBean("CommunicationCajaBean");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
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
    protected CommunicationComprasBean getCommunicationComprasBean() {
        return (CommunicationComprasBean) getBean("CommunicationComprasBean");
    }

}

