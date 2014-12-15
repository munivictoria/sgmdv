/*
 * ElementoPila.java
 *
 * Created on 12 de octubre de 2006, 07:52
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.trascender.presentacion.navegacion;

import java.io.Serializable;
import java.util.ArrayList;

import com.trascender.presentacion.abstracts.TablaLogs;
import com.trascender.presentacion.abstracts.controller.ABMController;

/**
 * Elementos Pila de las p\225ginas.
 * 20/09/2006
 * @author Ariel & Juan Pablo
 */
public class ElementoPila implements Serializable {
    
    private static final long serialVersionUID = -1406523825984989685L;
    private Long idPagina;
    private Long idSubSesion;
    private int orden;
    private String nombrePagina;
    private String casoNavegacion;
    private ArrayList objetos;
    private Object[] ordenamiento;
    private long posicionGlobal;
    private boolean seleccionMultiple = false;
    private ABMController abmController;
    private TablaLogs tablaLogs = new TablaLogs();

	/**
     * @return Returns the posicionGlobal.
     */
    public long getPosicionGlobal() {
        return posicionGlobal;
    }
    
    /**
     * @param posicionGlobal The posicionGlobal to set.
     */
    public void setPosicionGlobal(long posicionGlobal) {
        this.posicionGlobal = posicionGlobal;
    }
    
    /**
     * Constructor nulo
     */
    public ElementoPila() {}
    
    /**
     * @return Returns the casoNavegacion.
     */
    public String getCasoNavegacion() {
        return casoNavegacion;
    }
    /**
     * @param casoNavegacion The casoNavegacion to set.
     */
    public void setCasoNavegacion(String casoNavegacion) {
        this.casoNavegacion = casoNavegacion;
    }
    /**
     * @return Returns the idPagina.
     */
    public Long getIdPagina() {
        return idPagina;
    }
    /**
     * @param idPagina The idPagina to set.
     */
    public void setIdPagina(Long idPagina) {
        this.idPagina = idPagina;
    }
    /**
     * @return Returns the idSubSesion.
     */
    public Long getIdSubSesion() {
        return idSubSesion;
    }
    /**
     * @param idSubSesion The idSubSesion to set.
     */
    public void setIdSubSesion(Long idSubSesion) {
        this.idSubSesion = idSubSesion;
    }
    /**
     * @return Returns the nombrePagina.
     */
    public String getNombrePagina() {
        return nombrePagina;
    }
    /**
     * @param nombrePagina The nombrePagina to set.
     */
    public void setNombrePagina(String nombrePagina) {
        this.nombrePagina = nombrePagina;
    }
    /**
     * @return Returns the orden.
     */
    public int getOrden() {
        return orden;
    }
    /**
     * @param orden The orden to set.
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
    
    /**
     * @return Returns the objetos.
     */
    public ArrayList getObjetos() {
        if (this.objetos == null) this.objetos = new ArrayList();
        return objetos;
    }
    
    /**
     * @param objetos The objetos to set.
     */
    public void setObjetos(ArrayList objetos) {
        this.objetos = objetos;
    }
    
    /**
     * @return Returns the ordenamiento.
     */
    public Object[] getOrdenamiento() {
        return ordenamiento;
    }
    
    /**
     * @param ordenamiento The ordenamiento to set.
     */
    public void setOrdenamiento(Object[] ordenamiento) {
        this.ordenamiento = ordenamiento;
    }

    public boolean isSeleccionMultiple() {
        return seleccionMultiple;
    }

    public void setSeleccionMultiple(boolean seleccionMultiple) {
        this.seleccionMultiple = seleccionMultiple;
    }

    public ABMController getAbmController() {
        return abmController;
    }

    public void setAbmController(ABMController abmController) {
        this.abmController = abmController;
    }

	public TablaLogs getTablaLogs() {
		return tablaLogs;
	}

	public void setTablaLogs(TablaLogs tablaLogs) {
		this.tablaLogs = tablaLogs;
	}
}