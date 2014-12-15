/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.reportes;

import ar.com.fdvs.dj.domain.CustomExpression;
import java.util.Map;

/**
 *
 * @author pedro
 */
public class BooleanCustomExpression implements CustomExpression{
    private String field;
    
    public BooleanCustomExpression(String field){
        this.field = field;
    }

    public Object evaluate(Map map, Map map1, Map map2) {
        Boolean valor = (Boolean) map.get(field);
        return valor ? "Si" : "No";
    }

    public String getClassName() {
        return String.class.getName();
    }
}
