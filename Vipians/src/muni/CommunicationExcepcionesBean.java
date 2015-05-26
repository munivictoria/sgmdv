/*
 * CommunicationExcepcionesBean.java
 *
 * Created on 4 de junio de 2007, 15:49
 * Copyright Trascender SRL
 */
package muni;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class CommunicationExcepcionesBean extends AbstractSessionBean {
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
     * <p>Construir una instancia de bean de datos de la sesion.</p>
     */
    public CommunicationExcepcionesBean() {
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }


    /** 
     * <p>Se llama a este metodo al agregar este bean al
     * ambito de la sesion.  Normalmente, esto ocurre como resultado de la evaluacion
     * de una expresion de enlace de valores o de metodos, que utiliza la
     * funcion de bean administrado para crear una instancia de este bean y almacenarla en el
     * ambito de la sesion.</p>
     * 
     * <p>Puede personalizar este metodo para inicializar y almacenar en cache los valores
     * o recursos necesarios para el ciclo de duracion de una
     * sesion de usuario en particular.</p>
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
            log("CommunicationExcepcionesBean Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicacion que debe finalizar
        // *despues* de que se inicien los componentes administrados
        // TODO - Agregar codigo de inicio propio aqui

        // ARIEL - NUEVO
        tablePhaseListenerObligacionesRefinanciacion.keepSelected(true);
        tablePhaseListenerPeriodosAdeudadosRefinanciacion.keepSelected(true);

    }

    /** 
     * <p>Se llama a este metodo cuando la sesion que lo contiene esta apunto de
     * configurarse en modo pasivo.  Normalmente, esto ocurre en un contenedor de servlet distribuido
     * cuando la sesion esta apunto de transferirse a otra
     * instancia de contenedor, despues de la cual se llamara al metodo <code>activate()</code>
     * para indicar que la transferencia se ha completado.</p>
     * 
     * <p>Puede personalizar este metodo para liberar las referencias a datos
     * o recursos de sesion que no pueden serializarse con la propia sesion.</p>
     */
    public void passivate() {
    }

    /** 
     * <p>Se llama a este metodo cuando la sesion que lo contiene se
     * reactiva.</p>
     * 
     * <p>Puede personalizar este metodo para volver a adquirir las referencias a
     * datos o recursos de la sesion que no pudieron serializarse con la
     * propia sesion.</p>
     */
    public void activate() {
    }

    /** 
     * <p>Se llama a este metodo al eliminar este bean del
     * ambito de la sesion.  Normalmente, esto ocurre cuando
     * se supera el tiempo de espera de la sesion o la aplicacion la finaliza.</p>
     * 
     * <p>Puede personalizar este metodo para limpiar los recursos asignados
     * durante la ejecucion del metodo <code>init()</code> o
     * mas adelante durante el ciclo de vida de la aplicacion.</p>
     */
    public void destroy() {
    }

    /**
     * Conserva el valor de la propiedad listaRefinanciaciones.
     */
    private List listaRefinanciaciones;

    /**
     * Getter para propiedad listaRefinanciaciones.
     * @return Valor de la propiedad listaRefinanciaciones.
     */
    public List getListaRefinanciaciones() {

        return this.listaRefinanciaciones;
    }

    /**
     * Setter para propiedad listaRefinanciaciones.
     * @param listaRefinanciaciones Nuevo valor de la propiedad listaRefinanciaciones.
     */
    public void setListaRefinanciaciones(List listaRefinanciaciones) {

        this.listaRefinanciaciones = listaRefinanciaciones;
    }
    
    /**
     * Conserva el valor de la propiedad listaDelCommunicationTabla3.
     */
    private ArrayList listaPeriodosAdeudadosAuditoria;

    /**
     * Getter para propiedad listaRefinanciaciones.
     * @return Valor de la propiedad listaPeriodosAdeudadosAuditoria.
     */
    public ArrayList getListaPeriodosAdeudadosAuditoria() {

        return this.listaPeriodosAdeudadosAuditoria;
    }

    /**
     * Setter para propiedad listaRefinanciaciones.
     * @param listaPeriodosAdeudadosAuditoria Nuevo valor de la propiedad listaPeriodosAdeudadosAuditoria.
     */
    public void setListaPeriodosAdeudadosAuditoria(ArrayList listaPeriodosAdeudadosAuditoria) {

        this.listaPeriodosAdeudadosAuditoria = listaPeriodosAdeudadosAuditoria;
    }
    
    /**
     * Conserva el valor de la propiedad listaObligacionesRefinanciacion.
     */
    private List listaObligacionesRefinanciacion;

    /**
     * Getter para propiedad listaObligacionesRefinanciacion.
     * @return Valor de la propiedad listaObligacionesRefinanciacion.
     */
    public List getListaObligacionesRefinanciacion() {

        return this.listaObligacionesRefinanciacion;
    }

    /**
     * Setter para propiedad listaObligacionesRefinanciacion.
     * @param listaObligacionesRefinanciacion Nuevo valor de la propiedad listaObligacionesRefinanciacion.
     */
    public void setListaObligacionesRefinanciacion(List listaObligacionesRefinanciacion) {

        this.listaObligacionesRefinanciacion = listaObligacionesRefinanciacion;
    }

    /**
     * Conserva el valor de la propiedad listaPeriodosAdeudadosRefinanciacion.
     */
    private ArrayList listaPeriodosAdeudadosRefinanciacion;

    /**
     * Getter para propiedad listaPeriodosAdeudadosRefinanciacion.
     * @return Valor de la propiedad listaPeriodosAdeudadosRefinanciacion.
     */
    public ArrayList getListaPeriodosAdeudadosRefinanciacion() {

        return this.listaPeriodosAdeudadosRefinanciacion;
    }

    /**
     * Setter para propiedad listaPeriodosAdeudadosRefinanciacion.
     * @param listaPeriodosAdeudadosRefinanciacion Nuevo valor de la propiedad listaPeriodosAdeudadosRefinanciacion.
     */
    public void setListaPeriodosAdeudadosRefinanciacion(ArrayList listaPeriodosAdeudadosRefinanciacion) {

        this.listaPeriodosAdeudadosRefinanciacion = listaPeriodosAdeudadosRefinanciacion;
    }

    /**
     * Conserva el valor de la propiedad listaCuotasRefinanciacion.
     */
    private ArrayList listaCuotasRefinanciacion;

    /**
     * Getter para propiedad listaCuotasRefinanciacion.
     * @return Valor de la propiedad listaCuotasRefinanciacion.
     */
    public ArrayList getListaCuotasRefinanciacion() {

        return this.listaCuotasRefinanciacion;
    }

    /**
     * Setter para propiedad listaCuotasRefinanciacion.
     * @param listaCuotasRefinanciacion Nuevo valor de la propiedad listaCuotasRefinanciacion.
     */
    public void setListaCuotasRefinanciacion(ArrayList listaCuotasRefinanciacion) {

        this.listaCuotasRefinanciacion = listaCuotasRefinanciacion;
    }
    
    // ariel - nuevo
    private TableSelectPhaseListener tablePhaseListenerObligacionesRefinanciacion = new TableSelectPhaseListener();
    
    public TableSelectPhaseListener getTablePhaseListenerObligacionesRefinanciacion() {
        return this.tablePhaseListenerObligacionesRefinanciacion;
    }
    public void setTablePhaseListenerObligacionesRefinanciacion(TableSelectPhaseListener tablePhaseListenerObligacionesRefinanciacion) {
        this.tablePhaseListenerObligacionesRefinanciacion = tablePhaseListenerObligacionesRefinanciacion;
    }

    private TableSelectPhaseListener tablePhaseListenerPeriodosAdeudadosRefinanciacion = new TableSelectPhaseListener();
    
    public TableSelectPhaseListener getTablePhaseListenerPeriodosAdeudadosRefinanciacion() {
        return this.tablePhaseListenerPeriodosAdeudadosRefinanciacion;
    }
    public void setTablePhaseListenerPeriodosAdeudadosRefinanciacion(TableSelectPhaseListener tablePhaseListenerPeriodosAdeudadosRefinanciacion) {
        this.tablePhaseListenerPeriodosAdeudadosRefinanciacion = tablePhaseListenerPeriodosAdeudadosRefinanciacion;
    }

}
