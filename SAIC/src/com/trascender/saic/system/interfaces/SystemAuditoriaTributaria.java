package com.trascender.saic.system.interfaces;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Set;

import javax.ejb.Remote;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;

@Remote
public interface SystemAuditoriaTributaria {
	 public static final String JNDI_NAME="ejb/SystemAuditoriaTributaria/remote";
	 
	/**
	 * 
	 * @param pAuditoriaTributaria
	 * @return La auditoria que se agrego
	 * @throws Exception
	 */
	public AuditoriaTributaria addAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception, RemoteException;
	
	/**
	 * 
	 * @param pAuditoriaTributaria
	 * @return la auditoria que se actualizo
	 * @throws Exception
	 */
	public AuditoriaTributaria updateAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception, RemoteException;
	
	/**
	 * Borra una auditoria solo si la auditoria esta en estado normal y no esta firmada.
	 * @param pAuditoriaTributaria
	 * @return un valor booleando si la operacion fue exitosa
	 * @throws Exception
	 */
	public boolean deleteAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception, RemoteException;
	
	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	
	public AuditoriaTributaria getAuditoriaTributariaById(Long pId) throws Exception, RemoteException;
	/**
	 * devuelve una lista con todas las auditorias tributarias segun los parametros
	 * @param pContribuyente
	 * @param pTipoObligacion
	 * @param pEstadoAuditoria
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @return
	 */
	public Set<AuditoriaTributaria> findListaAuditoriaTributaria(Persona pContribuyente, TipoObligacion pTipoObligacion, AuditoriaTributaria.EstadoAuditoriaTributaria pEstadoAuditoria, Date pFechaDesde, Date pFechaHasta) throws Exception, RemoteException;
	
		/**
	 * 
	 * @param pUsuario
	 * @throws Exception
	 */
	public AuditoriaTributaria firmarAuditoriaTributaria(Usuario pUsuario, AuditoriaTributaria pAuditoriaTributaria) throws Exception, RemoteException;
	
	/**
	 * 
	 * @param pAuditoriaTributaria
	 * @throws Exception
	 */
	public void enjuiciarAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception, RemoteException;
	
	/**
	 * Pasa una auditoria a estado provisorio.
	 * @param pAuditoriaTributaria
	 * @throws Exception
	 */
	public void previsionarAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception, RemoteException;
	
	
	   public void setLlave( long pLlave )
	      throws java.rmi.RemoteException;
}
