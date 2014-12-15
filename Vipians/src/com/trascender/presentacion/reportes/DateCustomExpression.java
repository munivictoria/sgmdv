/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.reportes;

import ar.com.fdvs.dj.domain.CustomExpression;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author fer
 */
public class DateCustomExpression implements CustomExpression{
    
    private String field;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public DateCustomExpression(String field){
        this.field = field;
    }

    @Override
    public Object evaluate(Map map, Map map1, Map map2) {
        Date valor = (Date) map.get(field);
        return dateFormat.format(valor);
    }

    @Override
    public String getClassName() {
        return String.class.getName();
    }
    
}
