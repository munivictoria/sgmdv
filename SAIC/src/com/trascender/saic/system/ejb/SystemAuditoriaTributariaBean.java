package com.trascender.saic.system.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.business.interfaces.BusinessAuditoriaTributariaLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.LogCambiosAuditoriaTributaria;
import com.trascender.saic.system.interfaces.SystemAuditoriaTributaria;

@Stateful(name="ejb/SystemAuditoriaTributaria")
public class SystemAuditoriaTributariaBean implements SystemAuditoriaTributaria{
	
private static final long serialVersionUID = 5542958954525817503L;
	
	private long llave;
	
	@EJB
	private BusinessAuditoriaTributariaLocal businessAuditoria;
	
	/**
	 * 
	 * @param pAuditoriaTributaria
	 * @return La auditoria que se agrego
	 * @throws Exception
	 */
	@Override
	public AuditoriaTributaria addAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, AuditoriaTributaria.serialVersionUID, com.trascender.framework.recurso.persistent.Permiso.Accion.INSERT)){
				if(!pAuditoriaTributaria.getListaIntimaciones().isEmpty()){
					LogCambiosAuditoriaTributaria locCambio = new LogCambiosAuditoriaTributaria();
						locCambio.setAuditoriaTributaria(pAuditoriaTributaria);
						locCambio.setCausa("Cambio producido al momento del alta. Se agregó al menos una Intimación.");
						locCambio.setEstadoAnterior(pAuditoriaTributaria.getEstado());
						locCambio.setFecha(Calendar.getInstance().getTime());
						locCambio.setUsuario(SecurityMgr.getInstance().getUsuario(llave));
					pAuditoriaTributaria.getListaLogCambios().add(locCambio);
					pAuditoriaTributaria.setEstado(AuditoriaTributaria.EstadoAuditoriaTributaria.INTIMADO);
				}
				return this.businessAuditoria.addAuditoriaTributaria(pAuditoriaTributaria);
			}else{
				throw new SaicException(772);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param pAuditoriaTributaria
	 * @return la auditoria que se actualizo
	 * @throws Exception
	 */
	@Override
	public AuditoriaTributaria updateAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(llave, AuditoriaTributaria.serialVersionUID, Permiso.Accion.UPDATE)){
				if(!pAuditoriaTributaria.getListaIntimaciones().isEmpty() 
						&& pAuditoriaTributaria.getEstado().equals(AuditoriaTributaria.EstadoAuditoriaTributaria.NORMAL)){
					LogCambiosAuditoriaTributaria locCambio = new LogCambiosAuditoriaTributaria();
						locCambio.setAuditoriaTributaria(pAuditoriaTributaria);
						locCambio.setCausa("Cambio producido al momento de actualización. Se agrego al menos una intimación.");
						locCambio.setEstadoAnterior(pAuditoriaTributaria.getEstado());
						locCambio.setFecha(Calendar.getInstance().getTime());
						locCambio.setUsuario(SecurityMgr.getInstance().getUsuario(llave));
					pAuditoriaTributaria.getListaLogCambios().add(locCambio);
				pAuditoriaTributaria.setEstado(AuditoriaTributaria.EstadoAuditoriaTributaria.INTIMADO);
				}
				
				return this.businessAuditoria.updateAuditoriaTributaria(pAuditoriaTributaria);
			}else{
				throw new SaicException(772);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Borra una auditoria solo si la auditoria esta en estado normal y no esta firmada.
	 * @param pAuditoriaTributaria
	 * @return un valor booleando si la operacion fue exitosa
	 * @throws Exception
	 */
	@Override
	public boolean deleteAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(llave, AuditoriaTributaria.serialVersionUID, Permiso.Accion.DELETE)){
				if(pAuditoriaTributaria.getEstado().equals(AuditoriaTributaria.EstadoAuditoriaTributaria.NORMAL) 
						&& pAuditoriaTributaria.getFirma() == null){
					return this.businessAuditoria.deleteAuditoriaTributaria(pAuditoriaTributaria);
				}else {
					throw new SaicException(404);
				}
			}else {
				throw new SaicException(772);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	@Override
	public AuditoriaTributaria getAuditoriaTributariaById(Long pId) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(llave, AuditoriaTributaria.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessAuditoria.getAuditoriaTributariaById(pId);
			}else {
				throw new SaicException(772);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * devuelve una lista con todas las auditorias tributarias segun los parametros
	 * @param pContribuyente
	 * @param pTipoObligacion
	 * @param pEstadoAuditoria
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @return
	 */
	@Override
	public Set<AuditoriaTributaria> findListaAuditoriaTributaria(Persona pContribuyente, 
						TipoObligacion pTipoObligacion, 
						AuditoriaTributaria.EstadoAuditoriaTributaria pEstadoAuditoria, 
						Date pFechaDesde, Date pFechaHasta) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(llave, AuditoriaTributaria.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessAuditoria.findListaAuditoriaTributaria(pContribuyente, pTipoObligacion, pEstadoAuditoria, pFechaDesde, pFechaHasta);
			}else {
				throw new SaicException(772);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
		/**
	 * 
	 * @param pUsuario
	 * @throws Exception
	 */
	@Override
	public AuditoriaTributaria firmarAuditoriaTributaria(Usuario pUsuario, AuditoriaTributaria pAuditoriaTributaria) throws Exception{
		try{
			if(pAuditoriaTributaria.getFirma() != null){
				throw new SaicException(414);
			}
			FirmaPermiso locFirma = this.businessAuditoria.firmarAuditoriaTributaria(pUsuario);
			if(locFirma == null){
				throw new SaicException(415);
			}
			pAuditoriaTributaria.setFirma(locFirma);
			return this.businessAuditoria.updateAuditoriaTributaria(pAuditoriaTributaria);
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Cambia el estado de una auditoria a en juicio.
	 * @param pAuditoriaTributaria
	 * @throws Exception
	 */
	@Override
	public void enjuiciarAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(llave, AuditoriaTributaria.serialVersionUID, Permiso.Accion.UPDATE))
			{
			
			if(pAuditoriaTributaria == null){
				throw new SaicException(400);
			}
			
			if(pAuditoriaTributaria.getEstado().equals(AuditoriaTributaria.EstadoAuditoriaTributaria.EN_JUICIO)){
				throw new SaicException(505);
			}
			
			if(!(pAuditoriaTributaria.getEstado().equals(AuditoriaTributaria.EstadoAuditoriaTributaria.INTIMADO)
						|| pAuditoriaTributaria.getEstado().equals(AuditoriaTributaria.EstadoAuditoriaTributaria.PROVISORIO))){
				throw new SaicException(506);
			}
			
			if(pAuditoriaTributaria.getFirma() == null){
				throw new SaicException(411);
			}
			
			LogCambiosAuditoriaTributaria locCambio = new LogCambiosAuditoriaTributaria();
				locCambio.setAuditoriaTributaria(pAuditoriaTributaria);
				locCambio.setCausa("Cambio producido al momento de enjuiciar.");
				locCambio.setEstadoAnterior(pAuditoriaTributaria.getEstado());
				locCambio.setFecha(Calendar.getInstance().getTime());
				locCambio.setUsuario(SecurityMgr.getInstance().getUsuario(llave));
			pAuditoriaTributaria.getListaLogCambios().add(locCambio);
			
			pAuditoriaTributaria.setEstado(AuditoriaTributaria.EstadoAuditoriaTributaria.EN_JUICIO);

		this.businessAuditoria.updateAuditoriaTributaria(pAuditoriaTributaria);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Pasa una auditoria estado Provisorio
	 * @param pAuditoriaTributaria
	 * @throws Exception
	 */
	@Override
	public void previsionarAuditoriaTributaria(AuditoriaTributaria pAuditoriaTributaria) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(llave, AuditoriaTributaria.serialVersionUID, Permiso.Accion.UPDATE)){
				
				if(pAuditoriaTributaria.getEstado().equals(AuditoriaTributaria.EstadoAuditoriaTributaria.EN_JUICIO)){
					pAuditoriaTributaria.setEstado(AuditoriaTributaria.EstadoAuditoriaTributaria.PROVISORIO);
				}else {
					throw new SaicException(407);
				}
				
			LogCambiosAuditoriaTributaria locCambio = new LogCambiosAuditoriaTributaria();
				locCambio.setAuditoriaTributaria(pAuditoriaTributaria);
				locCambio.setCausa("Cambio producido al momento de previsionar.");
				locCambio.setEstadoAnterior(pAuditoriaTributaria.getEstado());
				locCambio.setFecha(Calendar.getInstance().getTime());
				locCambio.setUsuario(SecurityMgr.getInstance().getUsuario(llave));
			pAuditoriaTributaria.getListaLogCambios().add(locCambio);
			
			this.businessAuditoria.updateAuditoriaTributaria(pAuditoriaTributaria);
			}
		}catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setLlave( long pLlave ) throws java.rmi.RemoteException{
		this.llave = pLlave;
	}


}
