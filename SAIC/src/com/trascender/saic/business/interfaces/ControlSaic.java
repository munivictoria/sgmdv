/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.saic.business.interfaces;

import javax.ejb.Remote;

/**
 * Remote interface for ControlSaic.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
@Remote
public interface ControlSaic
{
	  public static final String JNDI_NAME="ejb/ControlSaic/remote";

   public boolean generateGrupoRecusosSaic(  )
      throws java.lang.Exception, java.rmi.RemoteException;

}
