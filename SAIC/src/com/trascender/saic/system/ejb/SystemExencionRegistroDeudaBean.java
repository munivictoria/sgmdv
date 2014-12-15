package com.trascender.saic.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.saic.business.interfaces.BusinessExencionRegistroDeudaLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;
import com.trascender.saic.system.interfaces.SystemExencionRegistroDeuda;

/**
 * @ejb.bean name="SystemExencionRegistroDeuda"
 *           display-name="Name for SystemExencionRegistroDeuda"
 *           description="Description for SystemExencionRegistroDeuda"
 *           jndi-name="ejb/SystemExencionRegistroDeuda"
 *           type="Stateful"
 *           view-type="remote"
 */
@Stateful(name="ejb/SystemExencionRegistroDeuda")
public class SystemExencionRegistroDeudaBean implements SystemExencionRegistroDeuda {

	private static final long serialVersionUID = 596581773700365736L;
	private long llave;
	
	@EJB
	private BusinessExencionRegistroDeudaLocal businessExencion;

	public void ejbCreate() throws CreateException {
//		try{
//			Context ctx = new InitialContext();
//			Object o = ctx.lookup(BusinessExencionRegistroDeudaLocalHome.JNDI_NAME);
//			BusinessExencionRegistroDeudaLocalHome localHome = (BusinessExencionRegistroDeudaLocalHome) PortableRemoteObject.narrow(o, BusinessExencionRegistroDeudaLocalHome.class);
//			this.businessExencion = localHome.create();
//			
//		}
//		catch(Exception e){
//			throw new CreateException("No se ha podido inicializar EJB de Exencion");
//		}
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
	public ExencionRegistroDeuda addExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionRegistroDeuda.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessExencion.addExencionRegistroDeuda(pExencionRegistroDeuda);
			}
			else throw new SaicException(772);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new SaicException(444);
		}
	}
	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public ExencionRegistroDeuda updateExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionRegistroDeuda.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessExencion.updateExencionRegistroDeuda(pExencionRegistroDeuda);
			}
			else throw new SaicException(772);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new SaicException(444);
		}
	}
	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public ExencionRegistroDeuda deleteExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionRegistroDeuda.serialVersionUID, Permiso.Accion.DELETE)){
				return this.businessExencion.deleteExencionRegistroDeuda(pExencionRegistroDeuda);
			}
			else throw new SaicException(772);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new SaicException(444);
		}
	}
	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public ExencionRegistroDeuda getExencionRegistroDeudaByID(Long pId) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionRegistroDeuda.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessExencion.getExencionRegistroDeudaByID(pId);
			}
			else throw new SaicException(772);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new SaicException(444);
		}
	}
	
	/**
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List<ExencionRegistroDeuda> findListaExencionesRegistrosDeuda(String pNombre, 
			CuotaLiquidacion pCuota,
			PeriodoLiquidacion pPeriodo,
			CalendarioMunicipal pCalendario,
			Estado pEstado,
			Double pPorcentaje) throws Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ExencionRegistroDeuda.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessExencion.findListaExencionesRegistrosDeuda(pNombre,pCuota, pPeriodo, pCalendario, pEstado,pPorcentaje);
			}
			else throw new SaicException(772);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new SaicException(444);
		}
	}
	
	public void autorizarExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception{
		try{
			if(SecurityMgr.getInstance().getUsuario(llave) != null){
				if(!pExencionRegistroDeuda.getEstado().equals(Estado.VIGENTE)){
					FirmaPermiso locFirma = new FirmaPermiso();
					locFirma.setFechaHora(SecurityMgr.getInstance().getFechaActual().getTime());
					locFirma.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
					locFirma.setComentario("Exención Autorizada por "+locFirma.getUsuario().getUser()+" en Fecha ["+Util.getStringFechaYHora(locFirma.getFechaHora())+"].");
					
					pExencionRegistroDeuda.setEstado(Estado.VIGENTE);
					pExencionRegistroDeuda.getListaFirmas().add(locFirma);
					
					this.businessExencion.updateExencionRegistroDeuda(pExencionRegistroDeuda);
				}
				else{
					throw new SaicException(803);
				}
			}
			else throw new SaicException(772);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new SaicException(444);
		}
	}
	
	public void terminarExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception{
		try{
			if(SecurityMgr.getInstance().getUsuario(llave) != null){
				if(!pExencionRegistroDeuda.getEstado().equals(Estado.TERMINADA)){
					FirmaPermiso locFirma = new FirmaPermiso();
					locFirma.setFechaHora(SecurityMgr.getInstance().getFechaActual().getTime());
					locFirma.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
					locFirma.setComentario("Exención Terminada por "+locFirma.getUsuario().getUser()+" en Fecha ["+Util.getStringFechaYHora(locFirma.getFechaHora())+"].");
					
					pExencionRegistroDeuda.setEstado(Estado.TERMINADA);
					pExencionRegistroDeuda.getListaFirmas().add(locFirma);
					
					this.businessExencion.updateExencionRegistroDeuda(pExencionRegistroDeuda);
				} else {
					throw new SaicException(804);
				}
			} else {
				throw new SaicException(772);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
