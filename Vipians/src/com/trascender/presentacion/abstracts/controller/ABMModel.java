package com.trascender.presentacion.abstracts.controller;

import com.trascender.presentacion.validadores.Validador;
import javax.faces.context.FacesContext;
import muni.ApplicationBean1;
import muni.CommunicationContabilidadBean;

/**
 *
 * @author Fernando
 */
public abstract class ABMModel {
	
    /**
     * La regla de navegacion que lleva al ABM asociadio a este controller.
     * @return
     */
    public abstract String getReglaNavegacion();
    
    public abstract String getNombreEntidad();
    
    public Object getSessionBean(String pBeanName){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(pBeanName);
    }
    
    public Object getRequestBean(String pBeanName){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(pBeanName);
    }
    
    public muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (muni.CommunicationExcepcionesBean) getSessionBean("CommunicationExcepcionesBean");
    }

    public muni.CommunicationCajaBean getCommunicationCajaBean() {
        return (muni.CommunicationCajaBean) getSessionBean("CommunicationCajaBean");
    }

    public muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean) getSessionBean("CommunicationComprasBean");
    }

    public muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean) getSessionBean("CommunicationSAICBean");
    }

    public muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean) getSessionBean("CommunicationHabilitacionesBean");
    }

    public muni.ComunicationCatastroBean getComunicationCatastroBean() {
        return (muni.ComunicationCatastroBean) getSessionBean("ComunicationCatastroBean");
        
    }
    
    
    public muni.CommunicationExpedientesBean getCommunicationExpedientesBean(){
    	return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
    }

    public muni.ApplicationBean1 getApplicationBean1() {
        FacesContext context = FacesContext.getCurrentInstance();
        return (ApplicationBean1) context.getApplication().evaluateExpressionGet(context, "#{ApplicationBean1}", ApplicationBean1.class);
    }
    
    public muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getSessionBean("ComunicationBean");
    }
    
    public muni.CommunicationContabilidadBean getCommunicationContabilidadBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        return (CommunicationContabilidadBean) context.getApplication().evaluateExpressionGet(context, "#{CommunicationContabilidadBean}", CommunicationContabilidadBean.class);
    }
    
    public muni.CommunicationAccionSocialBean getCommunicationAccionSocialBean() {
        return (muni.CommunicationAccionSocialBean) getSessionBean("CommunicationAccionSocialBean");
    }
    
    public muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getSessionBean("SessionBean1");
    }
    
    public muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getRequestBean("RequestBean1");
    }
    
}
