/*
 * MgrDropDown.java
 *
 * Created on 12 de octubre de 2006, 08:00
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.trascender.presentacion.utiles;

import com.sun.rave.web.ui.model.Option;

/**
 *
 * @author Ariel & Juan Pablo ??
 */
public class MgrDropDown {
    
    public MgrDropDown() {}
    
    public Option[] armarArrayOptions(Object[] objetos, String capitalizacion) {
        Option[] o = new Option[objetos.length+1];
        o[0] = new Option("", "");
        
        for (int i = 0; i < objetos.length; i++) {
            String display = objetos[i].toString();
            
            if (capitalizacion.equalsIgnoreCase("may")) {
                display = display.toUpperCase();
            } else if(capitalizacion.equalsIgnoreCase("min")) {
                display = display.toLowerCase();
            } else if (capitalizacion.equalsIgnoreCase("cap")) {
                display = this.capitalize(display);
            }
            
            //objetos[i].toString().toUpperCase().replaceAll(" ", "_"); reemplazado por getEnumNameFromString
            Option opcion = new Option(com.trascender.framework.util.Util.getEnumNameFromString(objetos[i].toString()), display);
            o[i+1] = opcion;
        }
        return o;
    }
    
    
    public Option[] armarArrayOptionsObjects (Object[] objetos, String capitalizacion) {
        Option[] o = new Option[objetos.length+1];
        o[0] = new Option("", "");
        
        for (int i = 0; i < objetos.length; i++) {
            String display = objetos[i].toString();
            
            if (capitalizacion.equalsIgnoreCase("may")) {
                display = display.toUpperCase();
            } else if(capitalizacion.equalsIgnoreCase("min")) {
                display = display.toLowerCase();
            } else if (capitalizacion.equalsIgnoreCase("cap")) {
                display = this.capitalize(display);
            }
            
            //display = display.replaceAll("_", " ");
            Option opcion = new Option(objetos[i], display);
            o[i+1] = opcion;
        }
        return o;
    }
    
     public Option[] armarArrayOptionsObjectsList (Object[] objetos, String capitalizacion) {
        Option[] o = new Option[objetos.length];
        //o[0] = new Option("", "");
        
        for (int i = 0; i < objetos.length; i++) {
            String display = objetos[i].toString();
            
            if (capitalizacion.equalsIgnoreCase("may")) {
                display = display.toUpperCase();
            } else if(capitalizacion.equalsIgnoreCase("min")) {
                display = display.toLowerCase();
            } else if (capitalizacion.equalsIgnoreCase("cap")) {
                display = this.capitalize(display);
            }
            
            display = display.replaceAll("_", " ");
            Option opcion = new Option(objetos[i], display);
            o[i] = opcion;
        }
        return o;
    }

    public Option[] armarArrayOptionsList(Object[] objetos, String capitalizacion) {
        Option[] o = new Option[objetos.length];
        //o[0] = new Option("", "");
        
        for (int i = 0; i < objetos.length; i++) {
            String display = objetos[i].toString();
            
            if (capitalizacion.equalsIgnoreCase("may")) {
                display = display.toUpperCase();
            } else if(capitalizacion.equalsIgnoreCase("min")) {
                display = display.toLowerCase();
            } else if (capitalizacion.equalsIgnoreCase("cap")) {
                display = this.capitalize(display);
            }
            
            display = display.replaceAll("_", " ");
            //objetos[i].toString().toUpperCase().replaceAll(" ", "_"); reemplazado por getEnumNameFromString
            Option opcion = new Option( com.trascender.framework.util.Util.getEnumNameFromString(objetos[i].toString()), display);
            o[i] = opcion;
        }
        return o;
    }
    
    private static String capitalize(String cadena) {
        cadena = cadena.toLowerCase();
        char ch;
        char prevCh;
        int i;
        prevCh = '.';
        String salida = "";
        for ( i = 0;  i < cadena.length();  i++ ) {
            ch = cadena.charAt(i);
            if ( Character.isLetter(ch)  &&  ! Character.isLetter(prevCh) )
                salida += Character.toUpperCase(ch);
            else
                salida += ch;
            prevCh = ch;
        }
        return salida;
    }  
    
    public Object getValorSeleccionado(Object obj, Option[] opcionesDisponibles) {
        String seleccionado;
        
        if (obj != null) seleccionado = obj.toString();
        else             seleccionado = "";
        
        int i = 0;
        while (!opcionesDisponibles[i].getValue().toString().equals(seleccionado)) i++;
        
        return opcionesDisponibles[i].getValue();
    }

}
