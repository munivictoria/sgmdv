package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.business.interfaces.BusinessExencionObligacionLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.system.interfaces.SystemExencionObligacion;

@Stateful(name = "ejb/SystemExencionObligacion")
public class SystemExencionObligacionBean implements SystemExencionObligacion {

	private static final long serialVersionUID = 596581773700365736L;
	private long llave;
	
	@EJB
	private BusinessExencionObligacionLocal businessExencion;

	public void ejbCreate() throws CreateException {
	}
	
	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		
	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		
	}
	
	/**
	 * 
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
	
	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public ExencionObligacion addExencionObligacion (ExencionObligacion pExencionObligacion) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionObligacion.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessExencion.addExencionObligacion(pExencionObligacion);
			}
			else throw new HabilitacionesException(799);
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(444);
		}
	}
	
	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public ExencionObligacion updateExencionObligacion (ExencionObligacion pExencionObligacion) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionObligacion.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessExencion.updateExencionObligacion(pExencionObligacion);
			}
			else throw new HabilitacionesException(799);
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(917);
		}
	}

	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteExencionObligacion (ExencionObligacion pExencionObligacion) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionObligacion.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessExencion.deleteExencionObligacion(pExencionObligacion);
			}
			else throw new HabilitacionesException(799);
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(444);
		}
	}
	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public ExencionObligacion getExencionObligacionByID(Long pId) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionObligacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessExencion.getExencionObligacionByID(pId);
			}
			else throw new HabilitacionesException(799);
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(444);
		}
	}
	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroExencionObligacion findListaExencionesObligacion(FiltroExencionObligacion pFiltro) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionObligacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessExencion.findListaExencionesObligacion(pFiltro);
			}
			else throw new HabilitacionesException(799);
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(444);
		}
	}
	
	
	public void autorizarExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception{
		try{
			if(SecurityMgr.getInstance().getUsuario(llave) != null){
				if(!pExencionObligacion.getEstado().equals(Estado.VIGENTE)){
					FirmaPermiso locFirma = new FirmaPermiso();
					locFirma.setFechaHora(SecurityMgr.getInstance().getFechaActual().getTime());
					locFirma.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
					locFirma.setComentario("Exención Autorizada por "+locFirma.getUsuario().getUser()+" en Fecha ["+Util.getStringFechaYHora(locFirma.getFechaHora())+"].");
					
					pExencionObligacion.setEstado(Estado.VIGENTE);
					pExencionObligacion.getListaFirmas().add(locFirma);
					
					this.businessExencion.updateExencionObligacion(pExencionObligacion);
				}
				else{
					throw new HabilitacionesException(916);
				}
			}
			else throw new HabilitacionesException(799);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(444);
		}
	}
	
	public void terminarExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception{
		try{
			if(SecurityMgr.getInstance().getUsuario(llave) != null){
				if(!pExencionObligacion.getEstado().equals(Estado.TERMINADA)){
					FirmaPermiso locFirma = new FirmaPermiso();
					locFirma.setFechaHora(SecurityMgr.getInstance().getFechaActual().getTime());
					locFirma.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
					locFirma.setComentario("Exención Terminada por "+locFirma.getUsuario().getUser()+" en Fecha ["+Util.getStringFechaYHora(locFirma.getFechaHora())+"].");
					
					pExencionObligacion.setEstado(Estado.TERMINADA);
					pExencionObligacion.getListaFirmas().add(locFirma);
					
					//this.businessExencion.updateExencionObligacion(pExencionObligacion);
					this.businessExencion.deleteExencionObligacion(pExencionObligacion);
				}
				else{
					throw new HabilitacionesException(915);
				}
			}
			else throw new HabilitacionesException(799);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(444);
		}
	}
}
