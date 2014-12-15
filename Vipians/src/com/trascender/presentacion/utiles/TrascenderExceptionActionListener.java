/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.utiles;

import com.sun.rave.web.ui.appbase.faces.ActionListenerImpl;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 * @author fer
 */
public class TrascenderExceptionActionListener extends ActionListenerImpl{
    
    public TrascenderExceptionActionListener(ActionListener actionListener){
        super(actionListener);
    }
    
    @Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
        System.out.println("FFUUUUCKKKKK");
        try{
            super.processAction(event);
        } catch (Exception e){
            System.out.println("EXCEPTION!!!!");
            System.out.println(e);
            System.out.println(e.getClass());
        }
    }
    
}
