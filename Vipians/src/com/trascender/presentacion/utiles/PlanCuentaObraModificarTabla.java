/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trascender.presentacion.utiles;

import com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra;

/**
 *
 * @author mines
 */
public class PlanCuentaObraModificarTabla {
    private String nombre;
    PlanCuentaObra planCuentaObra;

   
    public PlanCuentaObraModificarTabla() {
    }

    public PlanCuentaObraModificarTabla(String nombre, PlanCuentaObra planCuentaObra) {
        this.nombre = nombre;
        this.planCuentaObra = planCuentaObra;
    }
    
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PlanCuentaObra getPlanCuentaObra() {
        return planCuentaObra;
    }

    public void setPlanCuentaObra(PlanCuentaObra planCuentaObra) {
        this.planCuentaObra = planCuentaObra;
    }

}
