/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.reportes;

import ar.com.fdvs.dj.domain.CustomExpression;
import java.util.Map;

/**
 *
 * @author Fernando
 */
public class CharacterCustomExpression implements CustomExpression{
    
    private String field;
    
    public CharacterCustomExpression(String field){
        this.field = field;
    }

    public Object evaluate(Map map, Map map1, Map map2) {
        Character letra = (Character) map.get(field);
        return letra.toString();
    }

    public String getClassName() {
        return String.class.getName();
    }
    
}
