package com.trascender.framework.business.interfaces;

import javax.ejb.Local;

import com.trascender.framework.recurso.filtros.FiltroContrato;
import com.trascender.framework.recurso.persistent.Contrato;

@Local
public interface BusinessContratoLocal{
	
	public final static String JNDI_NAME = "ejb/BusinessContrato";
	
	/**
	 * Business method
	 */
	public void addContrato(Contrato pContrato) throws Exception;
	
	/**
	 * Business method
	 */
	public void updateContrato(Contrato pContrato) throws Exception;
	
	/**
	 * Business method
	 */
	public void deleteContrato(Contrato pContrato) throws Exception;
	
	/**
	 * Business method
	 */
	public com.trascender.framework.recurso.persistent.Contrato getContratoPorId(long pId) throws Exception;
	
	/**
	 * Business method
	 */
	public FiltroContrato findListaContratos(FiltroContrato pFiltro) throws Exception;
}
