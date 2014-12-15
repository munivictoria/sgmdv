package com.trascender.saic.business.interfaces;

import java.util.Date;
import java.util.Set;

import javax.ejb.Local;

import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;

@Local
public interface BusinessAuditoriaTributariaLocal {
		
	 public static final String JNDI_NAME="BusinessAuditoriaTributariaLocal";
		
	 public AuditoriaTributaria addAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception;
	
	 public AuditoriaTributaria updateAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception;
	
	 /**
	 * Borra una auditoria solo si la auditoria esta en estado normal y no esta firmada.
	 * @param pAuditoriaTributaria
	 * @return un valor booleando si la operacion fue exitosa
	 * @throws Exception
	 */
	public boolean deleteAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception;
	
	public AuditoriaTributaria getAuditoriaTributariaById(Long pId) throws Exception;
		
	/**
	 * devuelve una lista con todas las auditorias tributarias segun los parametros
	 * @param pContribuyente
	 * @param pTipoObligacion
	 * @param pEstadoAuditoria
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @return
	 */
	public Set<AuditoriaTributaria> findListaAuditoriaTributaria(Persona pContribuyente, 
											TipoObligacion pTipoObligacion, 
											AuditoriaTributaria.EstadoAuditoriaTributaria pEstadoAuditoria, 
											Date pFechaDesde, Date pFechaHasta) throws Exception;
		/**
		 * @return una firma que Da validez juridica a una Auditoria.
		 */
		public FirmaPermiso firmarAuditoriaTributaria(Usuario pUsuario) throws Exception;
	

}
