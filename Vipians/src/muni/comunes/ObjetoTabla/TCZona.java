/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.comunes.ObjetoTabla;

import com.trascender.catastro.recurso.persistent.AsociacionParcelaBridge;
import com.trascender.catastro.recurso.persistent.Parcela;
import java.util.List;

/**
 *
 * @author analia
 */
public class TCZona {
    
    private String nombre;
    private String tipo;
    private String estado;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     
}
