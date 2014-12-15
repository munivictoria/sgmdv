package com.trascender.framework.system.interfaces;

import javax.ejb.Remote;

import com.trascender.framework.recurso.filtros.FiltroContrato;


/**
 * Remote interface for SystemContrato.
 */
@Remote
public interface SystemContrato{
	
	public static final String JNDI_NAME = "ejb/SystemContrato/remote";

	public void setLlave(long pLlave) throws java.rmi.RemoteException;
	
	/**
	 * Agrega un Contrato
	 */
	public void addContrato(com.trascender.framework.recurso.persistent.Contrato pContrato)
	throws java.lang.Exception, java.rmi.RemoteException;
	
	/**
	 * Actualiza un Contrato
	 */
	public void updateContrato(com.trascender.framework.recurso.persistent.Contrato pContrato)
	throws java.lang.Exception, java.rmi.RemoteException;
	
	/**
	 * Elimina un Contrato
	 */
	public void deleteContrato(com.trascender.framework.recurso.persistent.Contrato pContrato)
	throws java.lang.Exception, java.rmi.RemoteException;
	
	/**
	 * Busca un Contrato por Id
	 */
	public com.trascender.framework.recurso.persistent.Contrato getContratoPorId(long pId)
	throws java.lang.Exception,java.rmi.RemoteException;
	
	/**
	 * Buscar una lista de Contratos
	 */
	public FiltroContrato findListaContratos(FiltroContrato pFiltro) 
	throws java.lang.Exception,java.rmi.RemoteException;
}

