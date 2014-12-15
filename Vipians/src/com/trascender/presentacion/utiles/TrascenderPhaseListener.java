/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trascender.presentacion.utiles;

import com.sun.rave.web.ui.appbase.faces.ActionListenerImpl;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.faces.event.*;

/**
 *
 * @author fer
 */
public class TrascenderPhaseListener  implements PhaseListener{
    

    @Override
    public void afterPhase(PhaseEvent pe) {
        System.out.println("RESTORING VIEW");
        System.out.println(pe.getPhaseId().equals(PhaseId.RESTORE_VIEW));
//        Iterator<ExceptionQueuedEvent> it = pe.getFacesContext().getExceptionHandler().getHandledExceptionQueuedEvents().iterator();
//        while (it.hasNext()){
//            System.out.println(it.next().getContext().getException().getClass());
//        }
//        it = pe.getFacesContext().getExceptionHandler().getUnhandledExceptionQueuedEvents().iterator();
//        System.out.println("Unhandleds");
//        while (it.hasNext()){
//            System.out.println(it.next().getContext().getException().getClass());
//        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        System.out.println("RESTORING VIEW");
        System.out.println(pe.getPhaseId().equals(PhaseId.RESTORE_VIEW));
        System.out.println(pe.getFacesContext().getViewRoot());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
