/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.abstracts.controller;

import com.trascender.presentacion.utiles.Constantes;

/**
 *
 * @author fer
 */
public abstract class EliminarAbstractController implements ABMController{
	
	@Override
	public String getTituloPagina() {
		return "Eliminar " + this.getModel().getNombreEntidad();
	}
    
    @Override
    public boolean guardaEstadoObjetosUsados() {
        return false;
    }

    @Override
    public boolean mostrarBotonAceptar() {
        return true;
    }

    @Override
    public boolean mostrarBotonCancelar() {
        return true;
    }

    @Override
    public boolean mostrarStSeparador() {
        return true;
    }

    @Override
    public String getTextoBotonAceptar() {
        return "Eliminar";
    }

    @Override
    public String getTextoBotonCancelar() {
        return "Cancelar";
    }

    @Override
    public String getCodigoColores(){
        return Constantes.COLORES_ELIMINAR;
    }
    
    @Override
    public boolean recargarPaginaAdmin() {
        return true;
    }
    
    @Override
	public boolean seleccionarObjeto() {
		return true;
	}
    
}
