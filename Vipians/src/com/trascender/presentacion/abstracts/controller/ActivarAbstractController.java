/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.abstracts.controller;

import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 *
 * @author pedro
 */
public abstract class ActivarAbstractController implements ABMController{

	@Override
	public String getTituloPagina() {
		return "Recuperar " + this.getModel().getNombreEntidad();
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
    public boolean recargarPaginaAdmin() {
        return true;
    }

    @Override
    public String getTextoBotonAceptar() {
        return "Recuperar";
    }

    @Override
    public String getTextoBotonCancelar() {
        return "Cancelar";
    }

    @Override
    public String getCodigoColores() {
        return Constantes.ACCION_MODIFICAR;
    }
    
    @Override
	public boolean seleccionarObjeto() {
		// TODO Auto-generated method stub
		return true;
	}
    
}
