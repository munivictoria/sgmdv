/*
 * Enlace.java
 *
 * Created on 19 de octubre de 2006, 15:16
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.trascender.presentacion.navegacion;

/**
 *
 * @author Ariel & Juan Pablo
 */
public class Enlace {
    
    private long idRecurso;
    private String link;
    
    public Enlace(long pIdRecurso,String pLink){
        this.idRecurso=pIdRecurso;
        this.link=pLink;
    }
    
    public long getIdRecurso(){
        return this.idRecurso;
    }
    
    public String getLink(){
        return link;
    }
    
}
