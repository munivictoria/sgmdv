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
public abstract class AgregarAbstractController implements ABMController {
	
    @Override
	public String getTituloPagina() {
		return "Agregar " + this.getModel().getNombreEntidad();
	}

	@Override
    public boolean guardaEstadoObjetosUsados() {
        return true;
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
        return "Guardar";
    }

    @Override
    public String getTextoBotonCancelar() {
        return "Cancelar";
    }

    @Override
    public String getCodigoColores(){
        return Constantes.COLORES_AGREGAR ;
    }
    
    @Override
    public boolean recargarPaginaAdmin() {
        return true;
    }

	@Override
	public boolean seleccionarObjeto() {
		return false;
	}
    
}
