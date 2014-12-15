package com.trascender.contabilidad.system.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.contabilidad.business.interfaces.BusinessCajaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.ArqueoCaja;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.Moneda;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionPlanillaDiaria;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.SecurityMgr;


@Stateful(name = "ejb/SystemAdministracionPlanillaDiaria")
public class SystemAdministracionPlanillaDiariaBean implements SystemAdministracionPlanillaDiaria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BusinessCajaLocal locCaja;
	private long llave;
	
	@EJB
	private SystemUsuario systemUsuario;
	private Caja caja = null;
	
	private PlanillaDiariaCaja planillaDiariaCaja;
	
	
	public SystemAdministracionPlanillaDiariaBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		try{
			if (this.planillaDiariaCaja != null){
				this.updatePlanillaDiariaCaja(this.planillaDiariaCaja);
			}
		} catch (EJBException e){
			throw e;
		} catch (Exception e){
		}
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx)
		throws EJBException,
		RemoteException {

	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}
	
	/**
	 * @ejb.interface-method view-type="remote"
	 */
	public void setLlave(long llave)throws TrascenderException{
		try{
			this.llave = llave;
			this.systemUsuario.setLlave(this.llave);
		} 
		catch (Exception e){
			throw new TrascenderContabilidadException(605);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIPAddress
	 * @return
	 * @throws Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Caja getCajaActual(String pIPAddress) throws Exception{
		this.caja = locCaja.findCajaByIP(pIPAddress);
		if (this.caja==null){
			throw new Exception("No existe la caja. Por favor, contactese con el administrador.");
		} else {
			Usuario pUsuario = this.systemUsuario.findUsuarioPorLlave(this.llave);
			this.planillaDiariaCaja = this.locCaja.getPlanillaDiariaDelDia(pUsuario);
			
			if (this.planillaDiariaCaja == null){
				this.planillaDiariaCaja = new PlanillaDiariaCaja();
				this.planillaDiariaCaja.setUsuario(this.systemUsuario.findUsuarioPorLlave(this.llave));
				this.planillaDiariaCaja.setFecha(SecurityMgr.getInstance().getFechaActual().getTime());
				this.locCaja.addPlanillaDiariaCaja(this.planillaDiariaCaja);
				}
		}
		return this.caja;
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void cerrarCaja() throws com.trascender.framework.exception.TrascenderException {
		try{
			if (this.planillaDiariaCaja != null){
				this.updatePlanillaDiariaCaja(this.planillaDiariaCaja);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException(307);
		}
	}
	
	//Planilla Diaria Caja
	
	private com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja updatePlanillaDiariaCaja (com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanillaDiariaCaja.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locCaja.updatePlanillaDiariaCaja(pPlanillaDiariaCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException (353);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja findPlanillaDiariaByID 
	(long pId) throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanillaDiariaCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findPlanillaDiariaByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException (351);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pUsuario
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaPlanillaDiariaCaja (Date pFechaDesde, Date pFechaHasta, Usuario pUsuario) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanillaDiariaCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findListaPlanillaDiariaCaja(pFechaDesde, pFechaHasta, pUsuario);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException(352);
		}
	}
	
	//Moneda
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pMoneda
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Moneda addMoneda (com.trascender.contabilidad.recurso.persistent.Moneda pMoneda)
	throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Moneda.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locCaja.addMoneda(pMoneda);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(340);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pMoneda
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Moneda updateMoneda (com.trascender.contabilidad.recurso.persistent.Moneda pMoneda)
	throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Moneda.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locCaja.updateMoneda(pMoneda);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException (342);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Moneda findMonedaByID (Long pId)
	throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Moneda.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findMonedaByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException (341);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pMoneda
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteMoneda (com.trascender.contabilidad.recurso.persistent.Moneda pMoneda)
	throws com.trascender.framework.exception.TrascenderException {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Moneda.serialVersionUID, Permiso.Accion.DELETE)){
				this.locCaja.deleteMoneda(pMoneda);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException(343);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNombre
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaMoneda (String pNombre) throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Moneda.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findListaMoneda(pNombre);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}catch(TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException(344);
		}
	}
	
	//Arqueo Caja
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pArqueoCaja
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ArqueoCaja addArqueoCaja (com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja)
	throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ArqueoCaja.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locCaja.addArqueoCaja(pArqueoCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		}catch (Exception e){
			throw new TrascenderContabilidadException (370);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pArqueoCaja
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ArqueoCaja updateArqueoCaja (com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja)
	throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ArqueoCaja.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locCaja.updateArqueoCaja(pArqueoCaja);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			throw e;
		}catch (Exception e){
			throw new TrascenderContabilidadException(372);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ArqueoCaja findArqueoCajaByID
	(long pId) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ArqueoCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findArqueoCajaByID(pId);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch(TrascenderException e){
			throw e;
		}catch (Exception e){
			throw new TrascenderContabilidadException(371);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pArqueoCaja
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteArqueoCaja (com.trascender.contabilidad.recurso.persistent.ArqueoCaja pArqueoCaja) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ArqueoCaja.serialVersionUID, Permiso.Accion.DELETE)){
				this.locCaja.deleteArqueoCaja(pArqueoCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch(TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException(373);
		}
	}
	/**
	 * Busines method
	 * @ejb.interface-method view-type="remote"
	 * @param pPlanillaDiariaCaja
	 * @param pMoneda
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaArqueoCaja(com.trascender.contabilidad.recurso.persistent.PlanillaDiariaCaja pPlanillaDiariaCaja, com.trascender.contabilidad.recurso.persistent.Moneda pMoneda)
			throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ArqueoCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findListaArqueoCaja(pPlanillaDiariaCaja, pMoneda);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException(374);
		}
	}
	//Movimiento Caja Egreso
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso findMovimientoCajaEgresoByID
			(long pId) throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaEgreso.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findMovimientoCajaEgreso(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException(381);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pImporteDesde
	 * @param pImporteHasta
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaMovimientoCajaEgreso (Date pFechaDesde, Date pFechaHasta, Double pImporteDesde, Double pImporteHasta)
			throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaEgreso.serialVersionUID, Permiso.Accion.INSERT)){
				return this.findListaMovimientoCajaEgreso(pFechaDesde, pFechaHasta, pImporteDesde, pImporteHasta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			throw e;
		} catch (Exception e){
			throw new TrascenderContabilidadException (382);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @return the caja
	 */
	public Caja getCaja() {
		return caja;
	}

	/**
	 * @param caja the caja to set
	 */
	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @return
	 */
	public PlanillaDiariaCaja getPlanillaDiariaCaja() {
		return planillaDiariaCaja;
	}

	/**
	 * @param planillaDiariaCaja the planillaDiariaCaja to set
	 */
	public void setPlanillaDiariaCaja(PlanillaDiariaCaja planillaDiariaCaja) {
		this.planillaDiariaCaja = planillaDiariaCaja;
	}

}
