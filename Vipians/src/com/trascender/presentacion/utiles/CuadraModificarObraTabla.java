/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.utiles;

import com.trascender.catastro.recurso.persistent.Cuadra;

public class CuadraModificarObraTabla {

    private String nombre;
    Cuadra cuadra;

    public CuadraModificarObraTabla(){

    }

    public CuadraModificarObraTabla(Cuadra locCuadra, String locNombre){
        this.setCuadra(locCuadra);
        this.setNombre(locNombre);
    }

    public Cuadra getCuadra() {
        return cuadra;
    }

    public void setCuadra(Cuadra cuadra) {
        this.cuadra = cuadra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
