package com.trascender.contabilidad.system.ejb;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import net.sf.jasperreports.engine.JasperPrint;

import com.trascender.contabilidad.business.interfaces.BusinessCajaLocal;
import com.trascender.contabilidad.business.interfaces.BusinessIngresoVarioLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.filtros.FiltroConceptoIngresoVario;
import com.trascender.contabilidad.recurso.filtros.FiltroIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.DetalleTicketCaja;
import com.trascender.contabilidad.recurso.persistent.HistoricoReimpresionTicket;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCancelado;
import com.trascender.contabilidad.recurso.transients.ResumenActualCajaDataSource;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionPlanillaDiaria;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.Sellado;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.recurso.interfaces.Pagable;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.system.interfaces.SystemRegistroValuado;

/**
 * @ejb.bean name="SystemAdministracionIngresos"
 *           display-name="Name for SystemAdministracionIngresos"
 *           description="Description for SystemAdministracionIngresos"
 *           jndi-name="ejb/SystemAdministracionIngresos"
 *           type="Stateful"
 *           view-type="remote"
 */

@Stateful(name = "ejb/SystemAdministracionIngresos")
public class SystemAdministracionIngresosBean implements SystemAdministracionIngresos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BusinessCajaLocal locCaja;
	
	@EJB
	private BusinessLiquidacionTasaLocal locTasa;
	
	@EJB
	private BusinessIngresoVarioLocal locIngresoVario;
	
	@EJB
	private SystemAdministracionPlanillaDiaria systemPlanillaDiaria;
	
	@EJB
	private SystemRegistroValuado systemRegistroValuado;
	
	private long llave;
	
	@EJB
	private SystemUsuario systemUsuario;
	
	public void setSystemPlanillaDiaria(SystemAdministracionPlanillaDiaria pSystemAdministracionPlanillaDiaria){
		this.systemPlanillaDiaria = pSystemAdministracionPlanillaDiaria;
		
	}
	
	public SystemAdministracionIngresosBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

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
	 * @ejb.interface-method view-type ="remote"
	 */
	public void setLlave(long llave) throws TrascenderException{
		try{
			this.llave = llave;
			this.systemUsuario.setLlave(llave);
			this.systemRegistroValuado.setLlave(llave);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(901);
		}
	}
	
	//CAJA
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCaja
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Caja addCaja(com.trascender.contabilidad.recurso.persistent.Caja pCaja) throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Caja.serialVersionUID, Permiso.Accion.INSERT))
			{ 
				return this.locCaja.addCaja(pCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(300);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNombre
	 * @param pEstado
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public java.util.List findListaCaja(String pNombre, com.trascender.contabilidad.recurso.persistent.Caja.Estado pEstado) throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					Caja.serialVersionUID,
					Permiso.Accion.SELECT)){
				return this.locCaja.findListaCaja(pNombre, pEstado);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
				throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (301);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCaja
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Caja updateCaja(com.trascender.contabilidad.recurso.persistent.Caja pCaja) 
	throws com.trascender.framework.exception.TrascenderException {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
					Caja.serialVersionUID , 
					Permiso.Accion.UPDATE)){
				return this.locCaja.updateCaja(pCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(302);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCaja
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteCaja(com.trascender.contabilidad.recurso.persistent.Caja pCaja)  throws com.trascender.framework.exception.TrascenderException {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,Caja.serialVersionUID,	Permiso.Accion.DELETE)){
				if (pCaja.getTickets() == null){
					this.locCaja.deleteCaja(pCaja);
				}else {
					pCaja.setEstado(Caja.Estado.INACTIVO);
					this.locCaja.updateCaja(pCaja);
				}
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(303);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIdCaja
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Caja findCajaByID(Long pIdCaja) throws java.lang.Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(
					this.llave, Caja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findCajaByID(pIdCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(305);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIPAddress
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Caja findCajaByIP(String pIPAddress) throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Caja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findCajaByIP(pIPAddress);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(305);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCaja
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Caja restaurarCaja (com.trascender.contabilidad.recurso.persistent.Caja pCaja)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Caja.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locCaja.restaurarCaja(pCaja);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (306);
		}
	}
	//Ticket Caja
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pDetalleTicketCaja
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.TicketCaja addTicketCaja (com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.INSERT)){
				pTicketCaja.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
				if (this.systemPlanillaDiaria.getPlanillaDiariaCaja() == null){
					throw new TrascenderContabilidadException(45);
				}
				pTicketCaja.setPlanillaDiariaCaja(this.systemPlanillaDiaria.getPlanillaDiariaCaja());
				if (pTicketCaja.getCaja()==null){
					Caja locCaja = this.systemPlanillaDiaria.getCaja();
					pTicketCaja.setCaja(locCaja);
				}
				return this.locCaja.addTicketCaja(pTicketCaja);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(310);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIdTicketCaja
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.TicketCaja findTicketCajaByID (Long pIdTicketCaja) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findTicketCajaByID(pIdTicketCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(311);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNumeroTicket
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.TicketCaja getTicketCajaPorNumero (Integer pNumeroTicket) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.getTicketCajaPorNumero(pNumeroTicket);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(311);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pCaja
	 * @param pNumero
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaTicketCaja 
			(java.util.Date pFechaDesde, java.util.Date pFechaHasta, Integer pNumero, com.trascender.framework.recurso.persistent.Usuario pUsuario, com.trascender.contabilidad.recurso.persistent.Caja pCaja) 
				throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findListaTicketCaja(pFechaDesde, pFechaHasta, pNumero, pUsuario, pCaja);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(312);
		}
	}
	
	
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCaja
	 * @param pUsuario
	 * @param pEstado
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findResumenCajaActual(com.trascender.contabilidad.recurso.persistent.Caja 
			pCaja, com.trascender.framework.recurso.persistent.Usuario pUsuario, com.trascender.contabilidad.recurso.persistent.TicketCaja.Estado pEstado,
			Date pFechaDesde, Date pFechaHasta) 
				throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findResumenCajaActual(pCaja, pUsuario, pEstado, pFechaDesde, pFechaHasta);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(312);
		}
	}

	//SAIC
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIdRegistroDeuda
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.saic.recurso.persistent.RegistroDeuda findRegistroDeudaByID (Long pIdRegistroDeuda) 
			throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, RegistroDeuda.serialVersionUID, Permiso.Accion.SELECT)){
				RegistroDeuda locRegistroDeuda =  this.locTasa.getRegistroDeudaPorId(pIdRegistroDeuda);
				if (locRegistroDeuda.getRegistroCancelacion()!=null){
					throw new TrascenderContabilidadException(413);
				}
				return locRegistroDeuda;
			}
			else {
				throw new TrascenderContabilidadException (900);
			}
		}catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (410);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pRegistroDeuda
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.saic.recurso.persistent.Vencimiento getVencimientoPorRegistroDeuda (com.trascender.saic.recurso.persistent.RegistroDeuda pRegistroDeuda)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, RegistroDeuda.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locTasa.getVencimientoActualPorRegistroDeuda(pRegistroDeuda);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(411);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pRegistroDeuda
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	
	/**
	    * Obtiene la el TicketCancelado a partir del TicketCaja Business method
	    * @param pTicketCaja
	    * @return 
	    * @throws java.lang.Exception    */
	public TicketCancelado getTicketCanceladoPorTicketCaja(com.trascender.contabilidad.recurso.persistent.TicketCaja pTicketCaja) 
			throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, RegistroDeuda.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.getTicketCanceladoPorTicketCaja(pTicketCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(054);
		}
	}
	
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param Id
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.framework.recurso.persistent.Persona getPersonaPorDeuda (Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, RegistroDeuda.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.getPersonaPorDeuda(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(410); 
		}
	}
	
	//INGRESO VARIO
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIngresoVario
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.IngresoVario addIngresoVario (com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, IngresoVario.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locIngresoVario.addIngresoVario(pIngresoVario);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(500);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIngresoVario
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.IngresoVario updateIngresoVario (com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, IngresoVario.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locIngresoVario.updateIngresoVario(pIngresoVario);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(502);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIdIngresoVario
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.IngresoVario getIngresoVarioByID (Long pIdIngresoVario) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, IngresoVario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locIngresoVario.getIngresoVarioByID(pIdIngresoVario);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(501);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIngresoVario
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteIngresoVario (com.trascender.contabilidad.recurso.persistent.IngresoVario pIngresoVario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, IngresoVario.serialVersionUID, Permiso.Accion.DELETE)){
				this.locIngresoVario.deleteIngresoVario(pIngresoVario);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(503);
		}
	}
	
	//CONCEPTO INGRESO VARIO
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pConceptoIngresoVario
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario addConceptoIngresoVario(com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario pConceptoIngresoVario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoIngresoVario.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locIngresoVario.addConceptoIngresoVario(pConceptoIngresoVario);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (510);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pConceptoIngresoVario
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario updateConceptoIngresoVario (com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario pConceptoIngresoVario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoIngresoVario.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locIngresoVario.updateConceptoIngresoVario(pConceptoIngresoVario);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (512);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIdConceptoIngresoVario
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario getConceptoIngresoVarioByID (Long pIdConceptoIngresoVario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoIngresoVario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locIngresoVario.getConceptoIngresoVarioByID(pIdConceptoIngresoVario);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (511);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pConceptoIngresoVario
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteConceptoIngresoVario(com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario pConceptoIngresoVario) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoIngresoVario.serialVersionUID, Permiso.Accion.DELETE)){
				this.locIngresoVario.deleteConceptoIngresoVario(pConceptoIngresoVario);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (513);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pPersona
	 * @param pConceptoIngresoVario
	 * @param pEstado
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public FiltroIngresoVario findListaIngresoVario (FiltroIngresoVario pFiltro)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, IngresoVario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locIngresoVario.findListaIngresoVario(pFiltro);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (504);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pFiltro
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public FiltroConceptoIngresoVario findListaConceptoIngresoVario (FiltroConceptoIngresoVario pFiltro) throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoIngresoVario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locIngresoVario.findListaConceptoIngresoVario(pFiltro);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(514);
		}
	}
	
	public List<ConceptoIngresoVario> findListaConceptoIngresoVarioPorUsuario(Usuario pUsuario) throws java.lang.Exception {
		try{
			if (SecurityMgr.getInstance().getUsuario(this.llave) != null){
				return this.locIngresoVario.findListaConceptoIngresoVarioPorUsuario(pUsuario);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(514);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pPersona
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaPagoSellado (com.trascender.framework.recurso.persistent.Persona pPersona) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Sellado.serialVersionUID, Permiso.Accion.SELECT)){
				return this.systemRegistroValuado.findListaPagoSellado(pPersona, null, false);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(505);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pTicketCaja
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void cancelarTicketCaja (TicketCaja pTicketCaja) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.UPDATE)){
				this.locCaja.setLlave(this.llave);
				this.locCaja.cancelarTicketCaja(pTicketCaja, systemPlanillaDiaria.getPlanillaDiariaCaja());
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (313);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public Pagable getDeudaByID (Long pId) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locCaja.getDeudaByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(650);
		}
	}
	
	public List<DetalleTicketCaja> getListaDetalleByIdLiquidacion(String codigo) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.INSERT)){
				this.locCaja.setLlave(llave);
				return this.locCaja.getListaDetalleByIdLiquidacion(codigo);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(650);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pTicketCaja
	 * @return
	 * @throws TrascenderException
	 */
	public HistoricoReimpresionTicket reimprimirTicket(TicketCaja pTicketCaja) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.INSERT)){
				HistoricoReimpresionTicket locHRT = new HistoricoReimpresionTicket();
				Usuario locUsuario = this.systemUsuario.findUsuarioPorLlave(this.llave);
				locHRT.setTicket(pTicketCaja);
				locHRT.setUsuario(locUsuario);
				return this.locCaja.addHistoricoReimpresionTicket(locHRT);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(680);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type ="remote"
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pImporteDesde
	 * @param pImporteHasta
	 * @return
	 * @throws java.lang.Exception
	 * @throws java.rmi.RemoteException
	 */
	public java.util.List<MovimientoCajaIngreso> findListaMovimientoCajaIngreso(
			java.util.Date pFechaDesde, java.util.Date pFechaHasta, Double pImporteDesde, Double pImporteHasta, TicketCaja.Estado pEstado) 
			throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaIngreso.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findListaMovimientoCajaIngreso(pFechaDesde, pFechaHasta, pImporteDesde, pImporteHasta, pEstado);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(690);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pListaTicketCaja
	 * @throws Exception
	 */
	public void validarListaTicketCaja(List<TicketCaja> pListaTicketCaja) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.INSERT)){
				this.locCaja.validarListaTicketCaja(pListaTicketCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(310);
		}
	}
	
	public ResumenActualCajaDataSource generarReporteCajaPorTasa(List<TicketCaja> pListaTickets) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.generarReporteCajaPorTasa(pListaTickets);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(310);
		}
	}
	
	public ResumenActualCajaDataSource generarReporteCajaPorTasa(
			Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.generarReporteCajaPorTasa(pIdUsuario, pIdCaja, pFechaDesde, pFechaHasta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(310);
		}
	}
	
	public JasperPrint generarReporteCajaPorTasaDinamico(
			Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta){
		return locCaja.generarReporteCajaPorTasaDinamico(pIdUsuario, pIdCaja, pFechaDesde, pFechaHasta);
	}
	
	
	public void addPagosPagoFacil(File pFile) throws Exception{
		this.addPagosPagoFacil(pFile);
	}

	@Override
	public List<MovimientoCajaIngreso> getListaMovimientosCaja(
			List<Pagable> pListaDeudas, boolean conIntereses) throws Exception {
		return locCaja.getListaMovimientosCaja(pListaDeudas, conIntereses);
	}
	
	public JasperPrint generarReporteCajaPorIngresoVario(Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta) 
			throws Exception{
		return this.locCaja.generarReporteCajaPorIngresoVario(pIdUsuario, pIdCaja, pFechaDesde, pFechaHasta);
	}
	
	public List<JasperPrint> generarReporteCajaGeneral(Long pIdUsuario, Long pIdCaja, Date pFechaDesde, Date pFechaHasta) 
			throws Exception{
		return this.locCaja.generarReporteCajaGeneral(pIdUsuario, pIdCaja, pFechaDesde, pFechaHasta);
	}

	@Override
	public List<AuxIdEntidad> findListaAuxIdConceptoIngresoVario(String cadena) throws Exception{
		Usuario locUsuario = SecurityMgr.getInstance().getUsuario(llave);
		return locIngresoVario.findListaAuxIdConceptoIngresoVario(cadena, locUsuario);
	}
	
}
