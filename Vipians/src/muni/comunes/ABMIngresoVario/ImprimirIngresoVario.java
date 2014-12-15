/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.comunes.ABMIngresoVario;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.conversores.Conversor;
import java.text.SimpleDateFormat;
import javax.faces.FacesException;
import muni.CommunicationCajaBean;
import muni.ComunicationBean;
import muni.CommunicationExcepcionesBean;
import muni.CommunicationHabilitacionesBean;
import muni.CommunicationMesaEntradaBean;
import muni.ApplicationBean1;
import muni.CommunicationSAICBean;
import muni.CommunicationComprasBean;
import muni.ComunicationCatastroBean;

/**
 *
 * @author marcosdy
 */
public class ImprimirIngresoVario extends AbstractPageBean {

 //   private String cuentaCobro;
    private String titularResponsable;
    private String conceptoIngresoVario;
    private String montoValor;
    private String fechaVencimiento;
    private String fechaEmision;
    private String codigoBarras;
    private String numero;
    private String observaciones = new String("");;
    private String usuario ;
    private String area;


    public void birtViewer() {
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMontoValor() {
        return montoValor;
    }

    public void setMontoValor(String montoValor) {
        this.montoValor = montoValor;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getNumero() {
       
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision ) {
        this.fechaEmision = fechaEmision;
    }

  /* public String getCuentaCobro() {
        
        return cuentaCobro;
    }*/

/**
 *
 * @author marcosdy
 */
  /*  public void setCuentaCobro(String cuentaCobro) {
        this.cuentaCobro = cuentaCobro;
    }*/

    public String getTitularResponsable() {
        this.cargarDatos();
        return titularResponsable;
    }

    public void setTitularResponsable(String titularResponsable) {
        this.titularResponsable = titularResponsable;
    }

    public String getConceptoIngresoVario() {
        return conceptoIngresoVario;
    }

    public void setConceptoIngresoVario(String conceptoIngresoVario) {
        this.conceptoIngresoVario = conceptoIngresoVario;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void delay(int howLong) {
        for (int i = 1; i <= howLong; i++) {
            double garbage = Math.PI * Math.PI;
        }
    }

    /** 
     * Retorna una cadena de caracteres a la cual se le completa con 0 
     * @param pTamanio Tamanio de la variable
     * @param pCadena Variable a agregar 0
     * @return cadena procesada
     */
    public String rellenarCodigoBarra(String pCadena) {
        String retorno = "";
        int locTamanio = pCadena.length();
        int agregar = 18 - locTamanio;
        retorno += "2";
        if (agregar > 0) {
            for (int i = 0; i < agregar; i++) {
                retorno += "0";
            }
        }
        return retorno + pCadena;
    }

    public void cargarDatos() {
        Usuario usuario = new Usuario();

        String locCodigoBarra;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        IngresoVario ingresoVario = (IngresoVario) this.getSessionBean1().getObjetoImpresion();
        if (ingresoVario != null) {
          //  this.cuentaCobro = "";
            this.montoValor = "";
            this.titularResponsable = "";
            this.numero = "";
            this.fechaEmision = "";
            this.fechaVencimiento = "";
            this.codigoBarras = "";
            this.observaciones = "";

            this.numero = ingresoVario.getNumero().toString();
            this.montoValor = ingresoVario.getValor().toString();
            this.titularResponsable = ingresoVario.getPersona().toString();
            this.conceptoIngresoVario = ingresoVario.getConceptoIngresoVario().toString();
            
            //Obtener fecha Actual
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            this.fechaEmision = formato.format(ingresoVario.getFechaEmision());
            this.fechaVencimiento = this.getFechaEmision();
            locCodigoBarra = String.valueOf(ingresoVario.getIdIngresoVario());
            this.codigoBarras = this.rellenarCodigoBarra(locCodigoBarra);
            if(ingresoVario.getObservaciones() == null) ingresoVario.setObservaciones(" ");
            this.observaciones = ingresoVario.getObservaciones().toString();
            this.usuario = this.getSessionBean1().getUsuario().toString();
         
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
    protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
        return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
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
