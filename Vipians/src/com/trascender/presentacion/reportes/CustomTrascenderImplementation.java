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
public class CustomTrascenderImplementation implements CustomExpression{
    
        private String field;
	
	public CustomTrascenderImplementation(String field){
		this.field = field;
	}

	public Object evaluate(Map fields, Map variables, Map parameters) {
		return fields.get(field).toString();
	}

	public String getClassName() {
		return String.class.getName();
	}
}
