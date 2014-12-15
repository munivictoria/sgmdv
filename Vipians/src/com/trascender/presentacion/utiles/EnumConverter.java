/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.utiles;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.trascender.framework.util.Util;

/**
 *
 * @author fer
 */
public class EnumConverter implements javax.faces.convert.Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent comp, String value) {
        Class enumType = comp.getValueBinding("selected").getType(context);
        for (Object cadaObject : enumType.getEnumConstants()){
            if (Util.capitalizeEnumName(value).equalsIgnoreCase(cadaObject.toString())){
                return cadaObject;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String) {
            return (String) object;
        }
        Enum type = (Enum) object;
        return type.name();
    }
    
}
