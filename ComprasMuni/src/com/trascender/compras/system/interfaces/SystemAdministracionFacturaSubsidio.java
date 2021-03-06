/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.compras.system.interfaces;

import javax.ejb.Remote;

import com.trascender.compras.recurso.filtros.FiltroFacturaSubsidio;

@Remote
public interface SystemAdministracionFacturaSubsidio 
	{
	
	public static final String JNDI_NAME = "ejb/SystemAdministracionFacturaSubsidio/remote";
	
	/**
	 * Business method
	 */
	 public void setLlave(long pLlave)
	 	throws java.rmi.RemoteException;
	 
	
	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio addFacturaSubsidio(com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio)
		throws java.lang.Exception, java.rmi.RemoteException;
	
	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio updateFacturaSubsidio(com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio)
		throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public void deleteFacturaSubsidio(com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio)
		throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio getFacturaSubsidioPorId(long pId)
		throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public FiltroFacturaSubsidio findListaFacturasSubsidios(FiltroFacturaSubsidio pFiltro)
			throws java.lang.Exception;
	
}
