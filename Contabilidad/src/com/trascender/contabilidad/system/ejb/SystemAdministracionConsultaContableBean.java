package com.trascender.contabilidad.system.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.contabilidad.business.interfaces.BusinessLibroDiarioLocal;
import com.trascender.contabilidad.business.interfaces.BusinessPlanDeCuentaLocal;
import com.trascender.contabilidad.business.interfaces.BusinessPresupuestoLocal;
import com.trascender.contabilidad.business.interfaces.BusinessSubdiarioCajaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.filtros.FiltroCuenta;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion;
import com.trascender.contabilidad.recurso.persistent.BajaArticulo;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.contabilidad.recurso.persistent.TipoCuenta;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.saic.recurso.persistent.ParametroAsociacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

@Stateful(name = "ejb/SystemAdministracionConsultaContable")
public class SystemAdministracionConsultaContableBean implements SystemAdministracionConsultaContable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6605937806022568788L;
	
	private long llave;
	
	@EJB
	private BusinessPlanDeCuentaLocal businessPlanDeCuenta;
	
	@EJB
	private BusinessSubdiarioCajaLocal businessSubdiarioCaja;
	
	@EJB
	private BusinessLibroDiarioLocal businessLibroDiario;
	
	@EJB
	private BusinessPresupuestoLocal businessPresupuesto;
	
	@EJB
	private SystemUsuario systemUsuario;
	
	public SystemAdministracionConsultaContableBean() {
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

	public void ejbCreate() throws CreateException {
	}
	
	public void setLlave(long llave) throws TrascenderException{
		try{
			this.llave = llave;
			this.systemUsuario.setLlave(this.llave);
		}
		catch(Exception e){
			throw new TrascenderContabilidadException(901);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.PlanDeCuenta addPlanDeCuenta(com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanDeCuenta.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addPlanDeCuenta(pPlanDeCuenta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(470);
		}
		
	}
	
	public com.trascender.contabilidad.recurso.persistent.PlanDeCuenta updatePlanDeCuenta (com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanDeCuenta.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updatePlanDeCuenta(pPlanDeCuenta);
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
			throw new TrascenderContabilidadException(472);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.PlanDeCuenta getPlanDeCuentaByID(Long pIdPlanDeCuenta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanDeCuenta.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getPlanDeCuentaByID(pIdPlanDeCuenta);
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
			throw new TrascenderContabilidadException (471);
		}
	}
	
	public void deletePlanDeCuenta(com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta) throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanDeCuenta.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deletePlanDeCuenta(pPlanDeCuenta);
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
			throw new TrascenderContabilidadException (473);
		}
	}
	
	public List findListaPlanDeCuenta (String pDescripcion, Date pFechaDesde, Date pFechaHasta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanDeCuenta.serialVersionUID, Permiso.Accion.DELETE)){
				return this.businessPlanDeCuenta.findListaPlanDeCuenta(pDescripcion, pFechaDesde, pFechaHasta);
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
			throw new TrascenderContabilidadException (474);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Cuenta updateCuenta(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Cuenta.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateCuenta(pCuenta);
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
			throw new TrascenderContabilidadException(453);
		}
	}
	
	public void deleteCuenta(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Cuenta.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteCuenta(pCuenta);
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
			throw new TrascenderContabilidadException(453);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Cuenta getCuentaByID(Long pId) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Cuenta.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getCuentaByID(pId);
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
			throw new TrascenderContabilidadException(451);
		}
	}
	
	public void validarCuenta(Cuenta pCuenta) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Cuenta.serialVersionUID, Permiso.Accion.SELECT)){
				this.businessPlanDeCuenta.validarCuenta(pCuenta);
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
			throw new TrascenderContabilidadException(451);
		}
	}
	
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNombre
	 * @param pAbreviatura
	 * @param pCodigoImputacion
	 * @param pPlanDeCuenta
	 * @param pTipoCuenta
	 * @param pAceptaAsientoContable verifica si las cuentas pueden o no registrar asientos
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public FiltroCuenta findListaCuenta (FiltroCuenta pFiltro)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Cuenta.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaCuenta(pFiltro);
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
			throw new TrascenderContabilidadException(455);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.TipoCuenta addTipoCuenta (com.trascender.contabilidad.recurso.persistent.TipoCuenta pTipoCuenta)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoCuenta.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addTipoCuenta(pTipoCuenta);
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
			throw new TrascenderContabilidadException(460);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.TipoCuenta updateTipoCuenta (com.trascender.contabilidad.recurso.persistent.TipoCuenta pTipoCuenta)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoCuenta.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateTipoCuenta(pTipoCuenta);
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
			throw new TrascenderContabilidadException(462);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.TipoCuenta getTipoCuentaByID(Long pIdTipoCuenta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoCuenta.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getTipoCuentaByID(pIdTipoCuenta);
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
			throw new TrascenderContabilidadException(461);
		}
	}
	
	public void deleteTipoCuenta (com.trascender.contabilidad.recurso.persistent.TipoCuenta pTipoCuenta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoCuenta.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteTipoCuenta(pTipoCuenta);
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
			throw new TrascenderContabilidadException(463);
		}
	}
	
	public List findListaTipoCuenta (String pNombre, com.trascender.contabilidad.recurso.persistent.TipoCuenta.Abreviatura pAbreviatura,
			com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera pOperaMovimientosCaja, 
				com.trascender.contabilidad.recurso.persistent.TipoCuenta.Opera pOperaAsientos)throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoCuenta.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaTipoCuenta(pNombre, pAbreviatura, pOperaMovimientosCaja, pOperaAsientos);
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
			throw new TrascenderContabilidadException(464);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.TipoBalance addTipoBalance(com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoBalance.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addTipoBalance(pTipoBalance);
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
			throw new TrascenderContabilidadException (480);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.TipoBalance updateTipoBalance(com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoBalance.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateTipoBalance(pTipoBalance);
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
			throw new TrascenderContabilidadException(482);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.TipoBalance getTipoBalanceByID (Long pIdTipoBalance) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoBalance.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getTipoBalanceByID(pIdTipoBalance);
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
			throw new TrascenderContabilidadException(481);
		}
	}
	
	public void deleteTipoBalance(com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoBalance.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteTipoBalance(pTipoBalance);
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
			throw new TrascenderContabilidadException(483);
		}
	}
	
	public List findListaTipoBalance (String pNombre, Date pFechaDesde, Date pFechaHasta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoBalance.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaTipoBalance(pNombre, pFechaDesde, pFechaHasta);
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
			throw new TrascenderContabilidadException(484);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Balance generarBalance(com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Balance.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.generarBalance(pTipoBalance);
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
			throw new TrascenderContabilidadException(540);
		}
	}
	
	public void deleteBalance(com.trascender.contabilidad.recurso.persistent.Balance pBalance)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Balance.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteBalance(pBalance);
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
			throw new TrascenderContabilidadException(540);
		}
	}
			
	public com.trascender.contabilidad.recurso.persistent.Balance addBalance(com.trascender.contabilidad.recurso.persistent.Balance pBalance)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Balance.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addBalance(pBalance);
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
			throw new TrascenderContabilidadException(630);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Balance getBalanceByID(Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Balance.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.getBalanceByID(pId);
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
			throw new TrascenderContabilidadException(631);
		}
	}
	
	public List findListaBalance(String pNombre, Date pFechaDesde, Date pFechaHasta, 
			com.trascender.contabilidad.recurso.persistent.TipoBalance pTipoBalance) 
				throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Balance.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaBalance(pNombre, pFechaDesde, pFechaHasta, pTipoBalance);
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
			throw new TrascenderContabilidadException(632);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.Mayor generarMayor(Calendar pCalendar, com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Mayor.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessLibroDiario.generarMayor(pCalendar, pCuenta);
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
			throw new TrascenderContabilidadException(541);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja getSubdiarioCajaByID(Long pIdSubdiarioCaja) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, SubdiarioCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessSubdiarioCaja.getSubdiarioCajaByID(pIdSubdiarioCaja);
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
			throw new TrascenderContabilidadException(551);
		}
	}
	
	public List findListaSubdiarioCaja(com.trascender.contabilidad.recurso.persistent.SubdiarioCaja.Tipo pTipo, Date pFechaDesde, Date pFechaHasta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, SubdiarioCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessSubdiarioCaja.findListaSubdiarioCaja(pTipo, pFechaDesde, pFechaHasta);
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
			throw new TrascenderContabilidadException (554);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja addSubdiarioCaja(com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja)
			throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, SubdiarioCaja.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessSubdiarioCaja.addSubdiarioCaja(pSubdiarioCaja);
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
			throw new TrascenderContabilidadException (550);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja updateSubdiarioCaja(com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, SubdiarioCaja.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessSubdiarioCaja.updateSubdiarioCaja(pSubdiarioCaja);
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
			throw new TrascenderContabilidadException(552);
		}
	}
	
	public void deleteSubdiarioCaja(com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, SubdiarioCaja.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessSubdiarioCaja.deleteSubdiarioCaja(pSubdiarioCaja);
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
			throw new TrascenderContabilidadException (553);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja generarSubdiarioCaja (Date pFecha,
				com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta,
					com.trascender.contabilidad.recurso.persistent.SubdiarioCaja.Tipo pTipo)
						throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, SubdiarioCaja.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessSubdiarioCaja.generarSubdiarioCaja(pFecha, pPlanDeCuenta, pTipo);
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
			throw new TrascenderContabilidadException (555);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.LibroDiario addLibroDiario(com.trascender.contabilidad.recurso.persistent.LibroDiario pLibroDiario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroDiario.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessLibroDiario.addLibroDiario(pLibroDiario);
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
			throw new TrascenderContabilidadException(560);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.LibroDiario updateLibroDiario (com.trascender.contabilidad.recurso.persistent.LibroDiario pLibroDiario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroDiario.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessLibroDiario.updateLibroDiario(pLibroDiario);
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
			throw new TrascenderContabilidadException (562);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.LibroDiario getLibroDiarioByID(Long pId) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroDiario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.getLibroDiarioByID(pId);
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
			throw new TrascenderContabilidadException (561);
		}
	}
	
	public void deleteLibroDiario(com.trascender.contabilidad.recurso.persistent.LibroDiario pLibroDiario) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroDiario.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessLibroDiario.deleteLibroDiario(pLibroDiario);
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
			throw new TrascenderContabilidadException (563);
		}
	}
	
	public List findListaLibroDiario (String pCodigoAutorizacion, String pNumero) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroDiario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.findListaLibroDiario(pCodigoAutorizacion, pNumero);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(564);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.FolioLibroDiario getFolioLibroDiarioByID(Long pId) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, FolioLibroDiario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.getFolioLibroDiarioByID(pId);
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
			throw new TrascenderContabilidadException(571);
		}
	}
	
	public List findListaFolioLibroDiario(String pNumero, LibroDiario pLibroDiario) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, FolioLibroDiario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.findListaFolioLibroDiario(pNumero, pLibroDiario);
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
			throw new TrascenderContabilidadException(574);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.AsientoContable traerSubdiarioCaja(Date pFecha, com.trascender.contabilidad.recurso.persistent.SubdiarioCaja.Tipo pTipo)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, AsientoContable.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.traerSubdiarioCaja(pFecha, pTipo);
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
			throw new TrascenderContabilidadException(585);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.AsientoContable addAsientoContable(com.trascender.contabilidad.recurso.persistent.AsientoContable pAsientoContable)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, AsientoContable.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessLibroDiario.addAsientoContable(pAsientoContable);
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
			throw new TrascenderContabilidadException(580);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.AsientoContable updateAsientoContable(com.trascender.contabilidad.recurso.persistent.AsientoContable pAsientoContable)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, AsientoContable.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessLibroDiario.updateAsientoContable(pAsientoContable);
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
			throw new TrascenderContabilidadException(582);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.AsientoContable getAsientoContableByID (Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, AsientoContable.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.getAsientoContableByID(pId);
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
			throw new TrascenderContabilidadException (581);
		}
	}
	
	public void deleteAsientoContable (com.trascender.contabilidad.recurso.persistent.AsientoContable pAsientoContable)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, AsientoContable.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessLibroDiario.deleteAsientoContable(pAsientoContable);
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
			throw new TrascenderContabilidadException (583);
		}
	}
	
	public List findListaAsientoContable (Integer pNumeroAsiento, Date pFechaDesde, Date pFechaHasta, FolioLibroDiario pFolioLibroDiario)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, AsientoContable.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.findListaAsientoContable(pNumeroAsiento, pFechaDesde, pFechaHasta, pFolioLibroDiario);
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
			throw new TrascenderContabilidadException(584);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Presupuesto addPresupuesto (com.trascender.contabilidad.recurso.persistent.Presupuesto pPresupuesto)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Presupuesto.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPresupuesto.addPresupuesto(pPresupuesto);
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
			throw new TrascenderContabilidadException (520);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Presupuesto updatePresupuesto (com.trascender.contabilidad.recurso.persistent.Presupuesto pPresupuesto)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Presupuesto.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPresupuesto.updatePresupuesto(pPresupuesto, this.systemUsuario.findUsuarioPorLlave(this.llave));
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(522);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Presupuesto getPresupuestoByID(Long pIdPresupuesto) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Presupuesto.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPresupuesto.getPresupuestoByID(pIdPresupuesto);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(521);
		}
	}
	
	public void deletePresupuesto (com.trascender.contabilidad.recurso.persistent.Presupuesto pPresupuesto) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Presupuesto.serialVersionUID, Permiso.Accion.SELECT)){
				this.businessPresupuesto.deletePresupuesto(pPresupuesto);
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
			throw new TrascenderContabilidadException (523);
		}
	}
	
	public void deleteListaLineaPresupuesto(Set<com.trascender.contabilidad.recurso.persistent.LineaPresupuesto> pListaLineaPresupuesto)
		throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, LineaPresupuesto.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPresupuesto.deleteListaLineaPresupuesto(pListaLineaPresupuesto, this.systemUsuario.findUsuarioPorLlave(this.llave));
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(525);
		}
		
	}
	
	public java.util.List findListaPresupuesto (com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo pTipo, 
			com.trascender.contabilidad.recurso.persistent.Presupuesto.Estado pEstado, Date pFechaDesde, Date pFechaHasta)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Presupuesto.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPresupuesto.findListaPresupuesto(pTipo, pEstado, pFechaDesde, pFechaHasta);
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
			throw new TrascenderContabilidadException(524);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario addCuentaConceptoIngresoVario(
			com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario pCuentaConceptoIngresoVario) throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaConceptoIngresoVario.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addCuentaConceptoIngresoVario(pCuentaConceptoIngresoVario);
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
			throw new TrascenderContabilidadException(660);
		}
	}
	
	public	com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura addCuentaLineaCuenta(
			com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura pCuentaLineaFactura)
			throws java.lang.Exception{

		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,CuentaLineaFactura.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addCuentaLineaFactura(pCuentaLineaFactura);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderContabilidadException(670);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura getCuentaLineaFactura(com.trascender.compras.recurso.persistent.suministros.LineaFactura pLineaFactura) 
		throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,CuentaLineaFactura.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getCuentaLineaFactura(pLineaFactura);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (671);
		}
		
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario updateCuentaConceptoIngresoVario(
			com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario pCuentaConceptoIngresoVario) throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaConceptoIngresoVario.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateCuentaConceptoIngresoVario(pCuentaConceptoIngresoVario);
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
			throw new TrascenderContabilidadException(662);
		}
	}

	public void deleteCuentaConceptoIngresoVario(
			com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario pCuentaConceptoIngresoVario) throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaConceptoIngresoVario.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteCuentaConceptoIngresoVario(pCuentaConceptoIngresoVario);
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
			throw new TrascenderContabilidadException(663);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario getCuentaConceptoIngresoVarioByID(
			Long pId) throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaConceptoIngresoVario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getCuentaConceptoIngresoVarioByID(pId);
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
			throw new TrascenderContabilidadException(661);
		}
	}
	
	public List<CuentaConceptoIngresoVario>  findListaCuentaConceptoIngresoVario(
			ConceptoIngresoVario pConceptoIngresoVario, 
			Integer pAnio, Cuenta pCuenta) throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaConceptoIngresoVario.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaCuentaConceptoIngresoVario(pConceptoIngresoVario, pAnio, pCuenta);
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
			throw new TrascenderContabilidadException(664);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaModificador addCuentaModificador (com.trascender.contabilidad.recurso.persistent.CuentaModificador pCuentaModificador)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaModificador.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addCuentaModificador(pCuentaModificador);
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
			throw new TrascenderContabilidadException(590);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaModificador updateCuentaModificador (com.trascender.contabilidad.recurso.persistent.CuentaModificador pCuentaModificador)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaModificador.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateCuentaModificador(pCuentaModificador);
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
			throw new TrascenderContabilidadException(592);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaModificador getCuentaModificadorByID (Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaModificador.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getCuentaModificadorByID(pId);
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
			throw new TrascenderContabilidadException(591);
		}
	}
	
	public void deleteCuentaModificador (com.trascender.contabilidad.recurso.persistent.CuentaModificador pCuentaModificador)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaModificador.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteCuentaModificador(pCuentaModificador);
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
			throw new TrascenderContabilidadException(593);
		}
	}
	
	public List findListaCuentaModificador (Cuenta pCuenta, 
			TipoModificador pTipoModificador)
				throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaModificador.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaCuentaModificador(pCuenta, pTipoModificador);
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
			throw new TrascenderContabilidadException(594);
		}
	}
	
	public AsociacionRefinanciacion addAsociacionRefinanciacion(Set<ParametroAsociacion> pListaParametrosAsociacion, 
			Integer pAnio)
		throws com.trascender.framework.exception.TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.INSERT)){
					return this.businessPlanDeCuenta.addAsociacionRefinanciacion(pListaParametrosAsociacion, pAnio);
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
				throw new TrascenderContabilidadException(590);
			}
		}	
	
	public com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion updateAsociacionRefinanciacion(AsociacionRefinanciacion pAsociacionRefinanciacion)
		throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.updateAsociacionRefinanciacion(pAsociacionRefinanciacion);
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
			throw new TrascenderContabilidadException(756);
		}
	}
	
	public void deleteAsociacionRefinanciacion(com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion pAsociacionRefinanciacion)
		throws com.trascender.framework.exception.TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.DELETE)){
					this.businessPlanDeCuenta.deleteAsociacionRefinanciacion(pAsociacionRefinanciacion);
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
				throw new TrascenderContabilidadException (755);
			}
		}

	public com.trascender.contabilidad.recurso.persistent.AsociacionRefinanciacion getAsociacionRefinanciacionByID(Long pId) 
		throws com.trascender.framework.exception.TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)){
					return this.businessPlanDeCuenta.getAsociacionRefinanciacionByID(pId);
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
				throw new TrascenderContabilidadException(754);
			}
		}	
	
	public List<AsociacionRefinanciacion>  findListaAsociacionRefinanciacion (
			Integer pAnio) throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaAsociacionRefinanciacion(pAnio);
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
			throw new TrascenderContabilidadException(590);
		}
	}	
	
	public com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion addCuentaRefinanciacion(com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion pCuentaRefinanciacion)
	throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addCuentaRefinanciacion(pCuentaRefinanciacion);
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
			throw new TrascenderContabilidadException(590);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion updateCuentaRefinanciacion(com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion pCuentaRefinanciacion)
	throws com.trascender.framework.exception.TrascenderException{
	try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateCuentaRefinanciacion(pCuentaRefinanciacion);
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
			throw new TrascenderContabilidadException(592);
		}
	}
	
	public void deleteCuentaRefinanciacion(com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion pCuentaRefinanciacion)
	throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)){
				this.businessPlanDeCuenta.deleteCuentaRefinanciacion(pCuentaRefinanciacion);
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
			throw new TrascenderContabilidadException(591);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaRefinanciacion getCuentaRefinanciacionByID(
			Long pId) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.DELETE)){
				return this.businessPlanDeCuenta.getCuentaRefinanciacionByID(pId);
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
			throw new TrascenderContabilidadException(593);
		}
	}
	
	public List<CuentaRefinanciacion>  findListaCuentaRefinanciacion (
			DocumentoRefinanciacion pRefinanciacion, 
			Integer pAnio, Cuenta pCuenta) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaCuentaRefinanciacion(pRefinanciacion, pAnio, pCuenta);
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
			throw new TrascenderContabilidadException(594);
		}
	}
	
	public com.trascender.saic.recurso.persistent.ParametroAsociacion addParametroAsociacion(com.trascender.saic.recurso.persistent.ParametroAsociacion pParametroAsociacion)
		throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addParametroAsociacion(pParametroAsociacion);
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
			throw new TrascenderContabilidadException(732);
		}
	}

	public com.trascender.saic.recurso.persistent.ParametroAsociacion updateParametroAsociacion(com.trascender.saic.recurso.persistent.ParametroAsociacion pParametroAsociacion)
		throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateParametroAsociacion(pParametroAsociacion);
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
			throw new TrascenderContabilidadException(733);
		}
	}
	
	public void deleteParametroAsociacion(com.trascender.saic.recurso.persistent.ParametroAsociacion pParametroAsociacion)
		throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteParametroAsociacion(pParametroAsociacion);
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
			throw new TrascenderContabilidadException(734);
		}
	}
	
	public com.trascender.saic.recurso.persistent.ParametroAsociacion getParametroAsociacionByID(Long pId) 
		throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getParametroAsociacionByID(pId);
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
			throw new TrascenderContabilidadException(735);
		}
	}

	public List<ParametroAsociacion> findListaParametroAsociacion() 
		throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaRefinanciacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaParametroAsociacion();
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
			throw new TrascenderContabilidadException(736);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa addCuentaTipoTasa (com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa pCuentaTipoTasa)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaTipoTasa.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addCuentaTipoTasa(pCuentaTipoTasa);
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
			throw new TrascenderContabilidadException(600);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa updateCuentaTipoTasa (com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa pCuentaTipoTasa)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaTipoTasa.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateCuentaTipoTasa(pCuentaTipoTasa);
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
			throw new TrascenderContabilidadException(602);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa getCuentaTipoTasaByID (Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaTipoTasa.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getCuentaTipoTasaByID(pId);
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
			throw new TrascenderContabilidadException(601);
		}
	}

	public void deleteCuentaTipoTasa (com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa pCuentaTipoTasa)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaTipoTasa.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteCuentaTipoTasa(pCuentaTipoTasa);
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
			throw new TrascenderContabilidadException(603);
		}
	}

	public List findListaCuentaTipoTasa (Cuenta pCuenta, 
			TipoTasa pTipoTasa, 
			Integer pAnio	)
	throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaTipoTasa.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaCuentaTipoTasa(pCuenta, pTipoTasa, pAnio);
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
			throw new TrascenderContabilidadException(604);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura addCuentaLineaFactura(com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura
			pCuentaLineaFactura) throws com.trascender.framework.exception.TrascenderException{
		
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,CuentaLineaFactura.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addCuentaLineaFactura(pCuentaLineaFactura);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(670);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura updateCuentaLineaFactura(
			com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura pCuentaLineaFactura) throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, CuentaLineaFactura.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateCuentaLineaFactura(pCuentaLineaFactura);
			}else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderContabilidadException(671);
		}
	}
	
	public void deleteCuentaLineaFactura(com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura pCuentaLineaFactura) throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, CuentaLineaFactura.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteCuentaLineaFactura(pCuentaLineaFactura);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderContabilidadException(672);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura getCuentaLineaFacturaPorId(long pId) throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, CuentaLineaFactura.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getCuentaLineaFacturaPorId(pId);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(673);
		}
	}
	
	public java.util.List findListaCuentaLineaFactura(com.trascender.compras.recurso.persistent.suministros.LineaFactura pLineaFactura,
			com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta,
			com.trascender.framework.recurso.transients.Periodo pPeriodo) throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, CuentaLineaFactura.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaCuentaLineaFactura(pLineaFactura, pCuenta, pPeriodo);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderContabilidadException(674);
		}
		
	}
		
	public com.trascender.contabilidad.recurso.persistent.Mayor addMayor (com.trascender.contabilidad.recurso.persistent.Mayor pMayor)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Mayor.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessLibroDiario.addMayor(pMayor);
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
			throw new TrascenderContabilidadException(620);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Mayor updateMayor (com.trascender.contabilidad.recurso.persistent.Mayor pMayor)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Mayor.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessLibroDiario.updateMayor(pMayor);
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
			throw new TrascenderContabilidadException(622);
		}
	}
	
	public com.trascender.contabilidad.recurso.persistent.Mayor getMayorByID (Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Mayor.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.getMayorByID(pId);
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
			throw new TrascenderContabilidadException(621);
		}
	}
	
	public void deleteMayor (com.trascender.contabilidad.recurso.persistent.Mayor pMayor)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Mayor.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessLibroDiario.deleteMayor(pMayor);
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
			throw new TrascenderContabilidadException(623);
		}
	}
	
	public List findListaMayor(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Mayor.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessLibroDiario.findListaMayor(pCuenta);
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
			throw new TrascenderContabilidadException(624);
		}
	}

	public com.trascender.framework.recurso.persistent.referencia.CuentaRfr getCuentaRfr(
			com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) 
				throws com.trascender.framework.exception.TrascenderException{
		try{
			return this.businessPlanDeCuenta.getCuentaRfr(pCuenta);
		}
		catch (TrascenderContabilidadException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(640);
		}
	}

	public com.trascender.contabilidad.recurso.persistent.PlanDeCuenta importarPlanDeCuenta (com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta)
			throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanDeCuenta.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.importarPlanDeCuenta(pPlanDeCuenta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderContabilidadException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(848);
		}
	}
	
	public void aceptarOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra) throws java.lang.Exception{
		if (SecurityMgr.getInstance().getPermiso(this.llave,
				OrdenCompra.serialVersionUID, Permiso.Accion.UPDATE)) {
			this.businessSubdiarioCaja.aceptarOrdenCompra(pOrdenCompra);
		} else {
			throw new TrascenderContabilidadException(900);
		}
	}

	public boolean validarAceptacionOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra) throws java.lang.Exception{
		System.out.println("PROBANDO");
		return this.businessSubdiarioCaja.validarAceptacionOrdenCompra(pOrdenCompra);
	}
	
	public CuentaArticulo addCuentaArticulo(CuentaArticulo pCuentaArticulo) throws java.lang.Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaArticulo.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addCuentaArticulo(pCuentaArticulo);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderContabilidadException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(848);
		}
	}
	
	public CuentaArticulo updateCuentaArticulo(CuentaArticulo pCuentaArticulo) throws java.lang.Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaArticulo.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateCuentaArticulo(pCuentaArticulo);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderContabilidadException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(848);
		}
	}
	   
	//  public CuentaArticulo deleteCuentaArticulo(CuentaArticulo pCuentaArticulo) throws java.lang.Exception;
  
	public List<CuentaArticulo> findListaCuentaArticulo(Articulo pArticulo, Integer pAnio, Cuenta pCuenta) throws java.lang.Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaArticulo.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaCuentaArticulo(pArticulo, pAnio, pCuenta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderContabilidadException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(848);
		}
	}
  
	public CuentaArticulo getCuentaArticuloPorId(long pIdCuentaArticulo) throws Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaArticulo.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getCuentaArticuloPorId(pIdCuentaArticulo);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderContabilidadException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(848);
		}
	}
	
	public BajaArticulo addBajaArticulo(BajaArticulo pBajaArticulo) throws Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaArticulo.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.addBajaArticulo(pBajaArticulo);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderContabilidadException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(848);
		}
	}
	
	public CuentaInteresRecargo addCuentaInteresRecargo (CuentaInteresRecargo pCuentaInteresRecargo) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaInteresRecargo.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessPlanDeCuenta.addCuentaInteresRecargo(pCuentaInteresRecargo);
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
			throw new TrascenderContabilidadException(770);
		}
	}
	
	public CuentaInteresRecargo updateCuentaInteresRecargo (CuentaInteresRecargo pCuentaInteresRecargo) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaInteresRecargo.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessPlanDeCuenta.updateCuentaInteresRecargo(pCuentaInteresRecargo);
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
			throw new TrascenderContabilidadException(772);
		}
	}
	
	public CuentaInteresRecargo getCuentaInteresRecargoByID (Long pId) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaInteresRecargo.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.getCuentaInteresRecargoByID(pId);
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
			throw new TrascenderContabilidadException(771);
		}
	}
	
	public void deleteCuentaInteresRecargo (CuentaInteresRecargo pCuentaInteresRecargo) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaInteresRecargo.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessPlanDeCuenta.deleteCuentaInteresRecargo(pCuentaInteresRecargo);
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
			throw new TrascenderContabilidadException(773);
		}
	}
	
	public List findListaCuentaInteresRecargo (Cuenta pCuenta, ConceptoPorMora pConceptoMora) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaInteresRecargo.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessPlanDeCuenta.findListaCuentaInteresRecargo(pCuenta, pConceptoMora);
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
			throw new TrascenderContabilidadException(774);
		}
	}
}