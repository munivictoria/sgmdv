/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.abstracts.controller;

import com.trascender.presentacion.validadores.Validador;

/**
 *
 * @author Fernando
 */
public interface ABMController {
    
    /**
     * Devuelve al AbmPageBean el validador requerido para esta accion.
     * @return 
     */
    public abstract Validador getValidador();
    
    /**
     * Realiza la accion correspondiente a esta pantalla, generalmente llamadas a la lógica,
     * para agregar, modificar, elimnar, etc, a pObject.
     */
    public abstract String accionBotonAceptar(Object pObject) throws Exception;
    
    /**
     * Oculta/deshabilita los elementos necesarios en el AbmPageBean correspondiente.
     */
    public abstract void ocultarDeshabilitarEnVista();
    
    /**
     * Le informa al AbmPageBean si es necesario tomar los datos de la pagina y guardarlo
     * en donde corresponda.
     */
    public abstract boolean guardaEstadoObjetosUsados();
    
    /**
     * Informa al AbmPageBean si mostrar o no el boton Aceptar/Guardar
     * @return 
     */
    public abstract boolean mostrarBotonAceptar();
    
    /**
     * Informa al AbmPageBean si mostrar el boton Cancelar/Volver
     * @return 
     */
    public abstract boolean mostrarBotonCancelar();
    
    /**
     * Informa al AbmPageBean si mostrar o no el StaticText
     * que separa al boton Aceptar del Cancelar.
     * @return 
     */
    public abstract boolean mostrarStSeparador();
    
    /**
     * Informa al AdminPageBean si debe recargar la tabla de resultados
     * al volver desde el ABMPageBean.
     * @return 
     */
    public abstract boolean recargarPaginaAdmin();
    
    /**
     * Informa al AbmPageBean que texto mostrar en el boton aceptar
     * @return 
     */
    public abstract String getTextoBotonAceptar();
    
    /**
     * Informa al AbmPageBean que texto mostrar en el boton cancelar.
     */
    public abstract String getTextoBotonCancelar();
    
    /**
     * Informa al AbmPageBean el titulo de la pagina.
     * @return 
     */
    public abstract String getTituloPagina();
    
    /**
     * Informa al AbmPageBean el color de la pagina, en el formato XXX,XXX,XXX,
     * por ejemplo, 245,245,245.
     */
    public abstract String getCodigoColores();
    
    /**
     * Indica si necesita usar el objeto seleccionado de la tabla de resultados del admin. 
     */
    public abstract boolean seleccionarObjeto();
    
    public abstract ABMModel getModel();
    
}
