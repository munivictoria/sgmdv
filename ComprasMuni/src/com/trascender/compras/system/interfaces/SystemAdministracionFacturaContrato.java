/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.compras.system.interfaces;

import javax.ejb.Remote;

import com.trascender.compras.recurso.filtros.FiltroFacturaContrato;

/**
 * Remote interface for SystemAdministracionFacturaContrato
 */
@Remote
public interface SystemAdministracionFacturaContrato 
	{
	
	public static final String JNDI_NAME = "ejb/SystemAdministracionFacturaContrato/remote";
	
	public void setLlave(long pLlave) throws java.rmi.RemoteException;
	
	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato addFacturaContrato(com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato)
		throws java.lang.Exception, java.rmi.RemoteException;
	
	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato updateFacturaContrato(com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato)
		throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public void deleteFacturaContrato(com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato)
		throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato getFacturaContratoPorId(long pId)
		throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public FiltroFacturaContrato findListaFacturasContrato(FiltroFacturaContrato pFiltro)
		throws java.lang.Exception,java.rmi.RemoteException;
	

}
