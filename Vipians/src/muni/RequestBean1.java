/*
 * RequestBean1.java
 *
 * Created on 1 de septiembre de 2006, 14:01
 * Copyright Asus
 */
package muni;
 
import com.sun.rave.web.ui.appbase.AbstractRequestBean;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.navegacion.ElementoPila;
import java.util.ArrayList;
import javax.faces.FacesException;
 
/**
 * <p>Request scope data bean for your application.  Create properties
 *  here to represent data that should be made available across different
 *  pages in the same HTTP request, so that the page bean classes do not
 *  have to be directly linked to each other.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class RequestBean1 extends AbstractRequestBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>


    /** 
     * <p>Construir una instancia de bean de datos de la petici�n.</p>
     */
    public RequestBean1() {
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (CommunicationExcepcionesBean)getBean("CommunicationExcepcionesBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected CommunicationCajaBean getCommunicationCajaBean() {
        return (CommunicationCajaBean)getBean("CommunicationCajaBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected CommunicationComprasBean getCommunicationComprasBean() {
        return (CommunicationComprasBean)getBean("CommunicationComprasBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected CommunicationSAICBean getCommunicationSAICBean() {
        return (CommunicationSAICBean)getBean("CommunicationSAICBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
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
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected ComunicationBean getComunicationBean() {
        return (ComunicationBean)getBean("ComunicationBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }
    
     /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
        return (CommunicationMesaEntradaBean)getBean("CommunicationMesaEntradaBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
    }


    /** 
     * <p>Se llama a este m�todo al agregar este bean al
     * �mbito de petici�n.  Normalmente, esto ocurre como resultado de la evaluaci�n
     * de una expresi�n de enlace de valores o de m�todos, que utiliza la
     * funci�n de bean administrado para crear una instancia de este bean y almacenarla en el
     * �mbito de petici�n.</p>
     * 
     * <p>Puede personalizar este m�todo para asignar recursos necesarios
     * durante el ciclo de la petici�n actual.</p>
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
            log("RequestBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

    }

    /** 
     * <p>Se llama a este m�todo al eliminar este bean del
     * �mbito de petici�n.  Esto ocurre de manera autom�tica cuando la
     * respuesta HTTP correspondiente se ha completado y enviado al cliente.</p>
     * 
     * <p>Puede personalizar este m�todo para limpiar los recursos asignados
     * durante la ejecuci�n del m�todo <code>init()</code> o
     * m�s adelante durante el ciclo de vida de la petici�n.</p>
     */
    public void destroy() {
    }

    /**
     * Conserva el valor de la propiedad communication.
     */
    private ArrayList communication;

    /**
     * Getter para propiedad communication.
     * @return Valor de la propiedad communication.
     */
    public ArrayList getCommunication() {

        return this.communication;
    }

    /**
     * Setter para propiedad Objeto.
     * @param Objeto Nuevo valor de la propiedad Objeto.
     */
    public void setCommunication(ArrayList communication) {

        this.communication = communication;
    }

    /**
     * Conserva el valor de la propiedad Objeto.
     */
    private Object objeto;

    /**
     * Getter para propiedad Objeto.
     * @return Valor de la propiedad Objeto.
     */
    public Object getObjeto() {

        return this.objeto;
    }

    /**
     * Setter para propiedad Objeto.
     * @param Objeto Nuevo valor de la propiedad Objeto.
     */
    public void setObjeto(Object objeto) {

        this.objeto = objeto;
    }

    
    /**
     * Conserva el valor de la propiedad objetoABM.
     */
    private Object objetoABM;

    /**
     * Getter para propiedad objetoABM.
     * @return Valor de la propiedad objetoABM.
     */
    public Object getObjetoABM() {

        return this.objetoABM;
    }

    /**
     * Setter para propiedad objetoABM.
     * @param objetoABM Nuevo valor de la propiedad objetoABM.
     */
    public void setObjetoABM(final Object objetoABM) {

        this.objetoABM = objetoABM;
    }

    /**
     * Conserva el valor de la propiedad respuestaABM.
     */
    private Object respuestaABM;

    /**
     * Getter para propiedad respuestaABM.
     * @return Valor de la propiedad respuestaABM.
     */
    public Object getRespuestaABM() {

        return this.respuestaABM;
    }

    /**
     * Setter para propiedad respuestaABM.
     * @param respuestaABM Nuevo valor de la propiedad respuestaABM.
     */
    public void setRespuestaABM(Object respuestaABM) {

        this.respuestaABM = respuestaABM;
    }
    
        /**
     * Conserva el valor de la propiedad idSubSesion.
     */
    private Long idSubSesion;

    /**
     * Getter para propiedad idSubSesion.
     * @return Valor de la propiedad idSubSesion.
     */
    public Long getIdSubSesion() {

        return this.idSubSesion;
    }

    /**
     * Setter para propiedad idSubSesion.
     * @param idSubSesion Nuevo valor de la propiedad idSubSesion.
     */
    public void setIdSubSesion(Long idSubSesion) {

        this.idSubSesion = idSubSesion;
    }

    /**
     * Conserva el valor de la propiedad idPagina.
     */
    private Long idPagina;

    /**
     * Getter para propiedad idPagina.
     * @return Valor de la propiedad idPagina.
     */
    public Long getIdPagina() {

        return this.idPagina;
    }

    /**
     * Setter para propiedad idPagina.
     * @param idPagina Nuevo valor de la propiedad idPagina.
     */
    public void setIdPagina(Long idPagina) {

        this.idPagina = idPagina;
    }

    /**
     * Conserva el valor de la propiedad elementoPilaPaginaAnt.
     */
    private ElementoPila elementoPilaPaginaAnt;

    /**
     * Getter para propiedad elementoPilaPaginaAnt.
     * @return Valor de la propiedad elementoPilaPaginaAnt.
     */
    public ElementoPila getElementoPilaPaginaAnt() {

        return this.elementoPilaPaginaAnt;
    }

    /**
     * Setter para propiedad elementoPilaPaginaAnt.
     * @param elementoPilaPaginaAnt Nuevo valor de la propiedad elementoPilaPaginaAnt.
     */
    public void setElementoPilaPaginaAnt(ElementoPila elementoPilaPaginaAnt) {

        this.elementoPilaPaginaAnt = elementoPilaPaginaAnt;
    }

    /**
     * Conserva el valor de la propiedad casoNavegacionPostCaducidad.
     */
    private String casoNavegacionPostCaducidad;

    /**
     * Getter para propiedad casoNavegacionPostCaducidad.
     * @return Valor de la propiedad casoNavegacionPostCaducidad.
     */
    public String getCasoNavegacionPostCaducidad() {

        return this.casoNavegacionPostCaducidad;
    }

    /**
     * Setter para propiedad casoNavegacionPostCaducidad.
     * @param casoNavegacionPostCaducidad Nuevo valor de la propiedad casoNavegacionPostCaducidad.
     */
    public void setCasoNavegacionPostCaducidad(String casoNavegacionPostCaducidad) {

        this.casoNavegacionPostCaducidad = casoNavegacionPostCaducidad;
    }

    /**
     * Conserva el valor de la propiedad objetoSeleccion.
     */
    private Object objetoSeleccion;

    /**
     * Getter para propiedad objetoSeleccion.
     * @return Valor de la propiedad objetoSeleccion.
     */
    public Object getObjetoSeleccion() {

        return this.objetoSeleccion;
    }

    /**
     * Setter para propiedad objetoSeleccion.
     * @param objetoSeleccion Nuevo valor de la propiedad objetoSeleccion.
     */
    public void setObjetoSeleccion(Object objetoSeleccion) {

        this.objetoSeleccion = objetoSeleccion;
    }

    /**
     * Conserva el valor de la propiedad objetoImpresion.
     */
    private Object objetoImpresion;

    /**
     * Getter para propiedad objetoImpresion.
     * @return Valor de la propiedad objetoImpresion.
     */
    public Object getObjetoImpresion() {

        return this.objetoImpresion;
    }

    /**
     * Setter para propiedad objetoImpresion.
     * @param objetoImpresion Nuevo valor de la propiedad objetoImpresion.
     */
    public void setObjetoImpresion(Object objetoImpresion) {

        this.objetoImpresion = objetoImpresion;
    }

    /**
     * Conserva el valor de la propiedad accion.
     */
    private Object accion;

    /**
     * Getter para propiedad accion.
     * @return Valor de la propiedad accion.
     */
    public Object getAccion() {

        return this.accion;
    }

    /**
     * Setter para propiedad accion.
     * @param accion Nuevo valor de la propiedad accion.
     */
    public void setAccion(Object accion) {

        this.accion = accion;
    }
    
    /**
     * Conserva el valor de la propiedad objetosSeleccionMultiple.
     */
    private ArrayList objetosSeleccionMultiple;

    /**
     * Getter para propiedad objetosSeleccionMultiple.
     * @return Valor de la propiedad objetosSeleccionMultiple.
     */
    public ArrayList getObjetosSeleccionMultiple() {

        return this.objetosSeleccionMultiple;
    }

    /**
     * Setter para propiedad objetosSeleccionMultiple.
     * @param objetosSeleccionMultiple Nuevo valor de la propiedad objetosSeleccionMultiple.
     */
    public void setObjetosSeleccionMultiple(ArrayList objetosSeleccionMultiple) {

        this.objetosSeleccionMultiple = objetosSeleccionMultiple;
    }
    
        /**
     * Conserva el valor de la propiedad objetosSeleccionMultiple.
     */
    private ArrayList listaSeleccionMultiple;

    public ArrayList getListaSeleccionMultiple() {
        return listaSeleccionMultiple;
    }

    public void setListaSeleccionMultiple(ArrayList listaSeleccionMultiple) {
        this.listaSeleccionMultiple = listaSeleccionMultiple;
    }

    /**
     * Conserva el valor de la propiedad tipoSeleccion.
     */
    private String tipoSeleccion;

    /**
     * Getter para propiedad tipoSeleccion.
     * @return Valor de la propiedad tipoSeleccion.
     */
    public String getTipoSeleccion() {

        return this.tipoSeleccion;
    }

    /**
     * Setter para propiedad tipoSeleccion.
     * @param tipoSeleccion Nuevo valor de la propiedad tipoSeleccion.
     */
    public void setTipoSeleccion(String tipoSeleccion) {

        this.tipoSeleccion = tipoSeleccion;
    }

    /**
     * Conserva el valor de la propiedad refrescarTabla.
     */
    private boolean refrescarTabla;

    /**
     * Getter para propiedad refrescarTabla.
     * @return Valor de la propiedad refrescarTabla.
     */
    public boolean isRefrescarTabla() {

        return this.refrescarTabla;
    }

    /**
     * Setter para propiedad refrescarTabla.
     * @param refrescarTabla Nuevo valor de la propiedad refrescarTabla.
     */
    public void setRefrescarTabla(boolean refrescarTabla) {

        this.refrescarTabla = refrescarTabla;
    }
    
    private ABMController abmController;

    public ABMController getAbmController() {
        return abmController;
    }

    public void setAbmController(ABMController abmController) {
        this.abmController = abmController;
    }

}
