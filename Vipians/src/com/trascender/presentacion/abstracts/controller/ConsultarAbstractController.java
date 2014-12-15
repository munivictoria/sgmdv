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
public abstract class ConsultarAbstractController implements ABMController{
	
	@Override
	public String getTituloPagina() {
		return "Consultar " + this.getModel().getNombreEntidad();
	}
    
    @Override
    public boolean guardaEstadoObjetosUsados() {
        return false;
    }

    @Override
    public boolean mostrarBotonAceptar() {
        return false;
    }

    @Override
    public boolean mostrarBotonCancelar() {
        return true;
    }

    @Override
    public boolean mostrarStSeparador() {
        return false;
    }

    @Override
    public String getTextoBotonAceptar() {
        return "Nada";
    }

    @Override
    public String getTextoBotonCancelar() {
        return "Volver";
    }

    @Override
    public String getCodigoColores(){
        return Constantes.COLORES_CONSULTAR;
    }
    
    @Override
    public boolean recargarPaginaAdmin() {
        return false;
    }
    
    @Override
	public boolean seleccionarObjeto() {
		return true;
	}
    
}
