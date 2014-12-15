/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.abstracts.controller;

import com.trascender.presentacion.utiles.Constantes;

/**
 *
 * @author ubuntero
 */

public abstract class FirmarAbstractController implements ABMController {
    
    
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
        return "Firmar";
    }

    @Override
    public String getTextoBotonCancelar() {
        return "Cancelar";
    }

    @Override
    public String getTituloPagina() {
        return "Firmar";
    }

    @Override
    public String getCodigoColores() {
        return Constantes.COLORES_MODIFICAR;
    }
    
}
