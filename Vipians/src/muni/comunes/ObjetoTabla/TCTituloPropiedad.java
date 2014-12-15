/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.comunes.ObjetoTabla;

import com.trascender.catastro.recurso.persistent.AsociacionParcelaBridge;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Persona.Estado;

/**
 *
 * @author analia
 */
public class TCTituloPropiedad{
    
    private Persona persona;
    private String  descripcion;
    private Persona.Estado estado;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }    

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
    
}
