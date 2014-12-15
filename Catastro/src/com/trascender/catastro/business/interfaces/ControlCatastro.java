package com.trascender.catastro.business.interfaces;

import javax.ejb.Remote;

@Remote
public interface ControlCatastro {
	
	public final static String JNDI_NAME = "ejb/ConstrolCatastro/remote";
   /**
    * Business method
    */
   public boolean generateGrupoRecusosCatastro(  )
      throws java.lang.Exception, java.rmi.RemoteException;

}
