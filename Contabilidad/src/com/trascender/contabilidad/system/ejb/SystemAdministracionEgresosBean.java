package com.trascender.contabilidad.system.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.business.interfaces.BusinessBancoLocal;
import com.trascender.contabilidad.business.interfaces.BusinessCajaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.contabilidad.recurso.transients.CuentaCorriente;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionPlanillaDiaria;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;

@Stateful(name = "ejb/SystemAdministracionEgresos")
public class SystemAdministracionEgresosBean implements SystemAdministracionEgresos {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BusinessCajaLocal locCaja;
	@SuppressWarnings("unused")
	@EJB
	private BusinessLiquidacionTasaLocal locTasa;
	@SuppressWarnings("unused")
	@EJB
	private SystemAdministracionPlanillaDiaria systemPlanillaDiaria;
	@EJB
	private BusinessBancoLocal businessBanco;
	private long llave;
	@EJB
	private SystemUsuario systemUsuario;
	
	public void setSystemPlanillaDiaria (SystemAdministracionPlanillaDiaria pSystemAdministracionPlanillaDiaria){
		this.systemPlanillaDiaria = pSystemAdministracionPlanillaDiaria;
	}
		
	public SystemAdministracionEgresosBean() {
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
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}
	
	private void inicializarTasa() throws CreateException {
	}
	
	/**
	 * @ejb.interface-method view-type ="remote"
	 * @param llave
	 * @throws TrascenderException
	 */
	public void setLlave(long llave) throws TrascenderException{
		try{
			this.llave = llave;
			this.systemUsuario.setLlave(llave);
		}
		catch(Exception e){
			throw new TrascenderContabilidadException(605);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pDebito
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Debito addDebito (com.trascender.contabilidad.recurso.persistent.Debito pDebito)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Debito.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessBanco.addDebito(pDebito);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(530);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pDebito
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Debito updateDebito (com.trascender.contabilidad.recurso.persistent.Debito pDebito)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Debito.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.updateDebito(pDebito);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(532);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pDebito
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteDebito (com.trascender.contabilidad.recurso.persistent.Debito pDebito)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Debito.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessBanco.deleteDebito(pDebito);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(533);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Debito getDebitoByID(Long pId) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Debito.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getDebitoByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(531);
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
	public List findListaDebito(Date pFechaDesde, Date pFechaHasta, Double pImporteDesde, Double pImporteHasta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Debito.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaDebito(pFechaDesde, pFechaHasta, pImporteDesde, pImporteHasta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(534);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCheque
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Cheque addCheque (com.trascender.contabilidad.recurso.persistent.Cheque pCheque)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Cheque.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessBanco.addCheque(pCheque);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(420);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCheque
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Cheque updateCheque (com.trascender.contabilidad.recurso.persistent.Cheque pCheque)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Cheque.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.updateCheque(pCheque);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(422);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Cheque getChequeByID(Long pId) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Cheque.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getChequeByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(421);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCheque
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteCheque(com.trascender.contabilidad.recurso.persistent.Cheque pCheque) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Cheque.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessBanco.deleteCheque(pCheque);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(423);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNumeroCheque
	 * @param pFechaEmisionDesde
	 * @param pFechaEmisionHasta
	 * @param pFechaPagoDesde
	 * @param pFechaPagoHasta
	 * @param pImporteDesde
	 * @param pImporteHasta
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaCheque (String pNumeroCheque, Date pFechaEmisionDesde, Date pFechaEmisionHasta, 
			Date pFechaPagoDesde, Date pFechaPagoHasta, Double pImporteDesde, Double pImporteHasta) 
				throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Cheque.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaCheque(pNumeroCheque, pFechaEmisionDesde, pFechaEmisionHasta, pFechaPagoDesde, pFechaPagoHasta, pImporteDesde, pImporteHasta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(424);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pBoletaDeposito
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.BoletaDeposito addBoletaDeposito (com.trascender.contabilidad.recurso.persistent.BoletaDeposito pBoletaDeposito,
			com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta)throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BoletaDeposito.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessBanco.addBoletaDeposito(pBoletaDeposito, pCuenta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(430);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pBoletaDeposito
	 * @param pCuenta
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.BoletaDeposito updateBoletaDeposito (com.trascender.contabilidad.recurso.persistent.BoletaDeposito pBoletaDeposito,
			com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta)	throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BoletaDeposito.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.updateBoletaDeposito(pBoletaDeposito, pCuenta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(432);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.BoletaDeposito getBoletaDepositoByID(Long pId) 
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BoletaDeposito.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getBoletaDepositoByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(431);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pBoletaDeposito
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteBoletaDeposito (com.trascender.contabilidad.recurso.persistent.BoletaDeposito pBoletaDeposito)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BoletaDeposito.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessBanco.deleteBoletaDeposito(pBoletaDeposito);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(433);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNumeroBoleta
	 * @param pImporteDesde
	 * @param pImporteHasta
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaBoletaDeposito(String pNumeroBoleta, Double pImporteDesde, Double pImporteHasta,
			Date pFechaDesde, Date pFechaHasta) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, BoletaDeposito.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaBoletaDeposito(pNumeroBoleta, pImporteDesde, pImporteHasta, pFechaDesde, pFechaHasta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(434);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pOrdenPago
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.OrdenPago addOrdenPago (com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessBanco.addOrdenPago(pOrdenPago);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e) {
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(610);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pOrdenPago
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.OrdenPago confirmarOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.confirmarOrdenPago(pOrdenPago);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(612);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pOrdenPago
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.OrdenPago cancelarOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.cancelarOrdenPago(pOrdenPago);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(612);
		}
	}
	
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pOrdenPago
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.OrdenPago updateOrdenPago (com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.updateOrdenPago(pOrdenPago);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(612);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.OrdenPago getOrdenPagoByID(Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getOrdenPagoByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(611);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pOrdenPago
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteOrdenPago(com.trascender.contabilidad.recurso.persistent.OrdenPago pOrdenPago)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessBanco.deleteOrdenPago(pOrdenPago);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(613);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pFechaEmisionDesde
	 * @param pFechaEmisionHasta
	 * @param pFechaPagoDesde
	 * @param pFechaPagoHasta
	 * @param pImporteDesde
	 * @param pImporteHasta
	 * @param pProveedor
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaOrdenPago (Date pFechaEmisionDesde, Date pFechaEmisionHasta, 
			Date pFechaPagoDesde, Date pFechaPagoHasta, Double pImporteDesde, Double pImporteHasta, Proveedor pProveedor,
			OrdenPago.Estado pEstado) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaOrdenPago(pFechaEmisionDesde, pFechaEmisionHasta, pFechaPagoDesde, pFechaPagoHasta, pImporteDesde, pImporteHasta, pProveedor, pEstado);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(614);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pParametroRetencion
	 * @param pPeriodo
	 * @param pProveedor
	 * @return
	 * @throws Exception 
	 */
	public Double calcularMontoRetencion(ParametroRetencion pParametroRetencion, Periodo pPeriodo, Proveedor pProveedor)
			throws java.lang.Exception{
		try{
			return this.businessBanco.calcularMontoRetencion(pParametroRetencion, pPeriodo, pProveedor);
		}
		catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type ="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.LineaOrdenPago getLineaOrdenPagoByID(Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getLineaOrdenPagoByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(615);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type ="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.LineaOrdenPagoDevolucion getLineaOrdenPagoDevolucionByID(Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPagoDevolucion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getLineaOrdenPagoDevolucionByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(615);
		}
	}
	
	/**
    * Business method
    * @ejb.interface-method view-type ="remote"
    * @param pParametroRetencion
    * @return
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.ParametroRetencion addParametroRetencion(com.trascender.contabilidad.recurso.persistent.ParametroRetencion pParametroRetencion)
	throws java.lang.Exception {
	   try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.addParametroRetencion(pParametroRetencion);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(710);
		}
   }

   /**
    * Business method
    * @ejb.interface-method view-type="remote"
    * @param pParametroRetencion
    * @return
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.ParametroRetencion updateParametroRetencion(com.trascender.contabilidad.recurso.persistent.ParametroRetencion pParametroRetencion)
	throws Exception {
   		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.updateParametroRetencion(pParametroRetencion);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(711);
		}
   }
   
   /**
    * Business method
    * @ejb.interface-method view-type="remote"
    * @param pParametroRetencion
    * @return
    * @throws java.lang.Exception    */
   public void deleteParametroRetencion(com.trascender.contabilidad.recurso.persistent.ParametroRetencion pParametroRetencion)
	throws Exception {
   		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				this.businessBanco.deleteParametroRetencion(pParametroRetencion);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(712);
		}
   }
   
   /**
    * Business method
    * @ejb.interface-method view-type="remote"
	* @param pParametroRetencion
    * @return
    * @throws java.lang.Exception    */
   public List<ParametroRetencion> findListaParametroRetencion() throws java.lang.Exception {
	try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaParametroRetencion();
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(713);
		}
  }
   
   /**
    * Business method
    * @ejb.interface-method view-type="remote"
	* @param pId
    * @return
    * @throws java.lang.Exception    */
	public com.trascender.contabilidad.recurso.persistent.ParametroRetencion getParametroRetencionByID(Long pId) throws java.lang.Exception{
	try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getParametroRetencionByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(714);
		}
  }
	
	/**
    * Business method
    * @ejb.interface-method view-type ="remote"
    * @param pComprobanteRetencion
    * @param pProveedor
    * @return
    * @throws java.lang.Exception    */
	public com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion addComprobanteRetencion(com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion pComprobanteRetencion, com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
	throws java.lang.Exception {
	   try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.addComprobanteRetencion(pComprobanteRetencion, pProveedor);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(726);
		}
   }
	
	/**
    * Business method
    * @ejb.interface-method view-type ="remote"
    * @param pComprobanteRetencion
	 * @return 
    * @return
    * @throws java.lang.Exception    */
	public void deleteComprobanteRetencion(com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion pComprobanteRetencion)
	throws java.lang.Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				this.businessBanco.deleteComprobanteRetencion(pComprobanteRetencion);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(727);
		}
   }
	
	/**
	 * Business method
	 * @ejb.interface-method view-type ="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion getComprobanteRetencionByID(Long pId)
		throws java.lang.Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getComprobanteRetencionByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(712);
		}
	}
	
 
	/**
    * Business method
    * @ejb.interface-method view-type="remote"
	* @param pPeriodo
	* @param pProveedor
    * @return
    * @throws java.lang.Exception    */
	public List<ComprobanteRetencion> findListaRetencion(Periodo pPeriodo, Proveedor pProveedor) throws java.lang.Exception{
	try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaRetencion(pPeriodo, pProveedor);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(714);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type ="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.LineaRetencion getLineaRetencionByID(Long pId)
		throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPago.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getLineaRetencionByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(615);
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
	 * @throws java.lang.Exception
	 */
	public List<MovimientoCajaEgreso> findListaMovimientoCajaEgreso (Date pFechaDesde, Date pFechaHasta, Double pImporteDesde, Double pImporteHasta) throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaIngreso.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCaja.findListaMovimientoCajaEgreso(pFechaDesde, pFechaHasta, pImporteDesde, pImporteHasta);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(700);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote" 
	 * @param pOrdenPago
	 * @return
	 * @throws Exception
	 */
	public OrdenPagoDevolucion addOrdenPago(OrdenPagoDevolucion pOrdenPago) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPagoDevolucion.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessBanco.addOrdenPago(pOrdenPago);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(700);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote" 
	 * @param pOrdenPago
	 * @return
	 * @throws Exception
	 */
	public OrdenPagoDevolucion updateOrdenPago(OrdenPagoDevolucion pOrdenPago) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPagoDevolucion.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.updateOrdenPago(pOrdenPago);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(700);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote" 
	 * @param pOrdenPago
	 * @return
	 * @throws Exception
	 */
	public void deleteOrdenPago(OrdenPagoDevolucion pOrdenPago) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPagoDevolucion.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessBanco.deleteOrdenPago(pOrdenPago);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(700);
		}
	}
	
	public OrdenPagoDevolucion getOrdenPagoDevolucionByID(Long pId) throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, OrdenPagoDevolucion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getOrdenPagoDevolucionByID(pId);
			} else{
					throw new TrascenderContabilidadException(900);
				}
			}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(700);
		}
	}
	
	public OrdenPagoDevolucion confirmarOrdenPago(OrdenPagoDevolucion pOrdenPagoDevolucion) throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, OrdenPagoDevolucion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.confirmarOrdenPago(pOrdenPagoDevolucion);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(4818);
		}
	}
	
	public List findListaOrdenPagoDev(Date pFechaEmisionDesde, Date pFechaEmisionHasta, 
			Date pFechaPagoDesde, Date pFechaPagoHasta, Double pImporteDesde, Double pImporteHasta, Persona pPersona, OrdenPago.Estado pEstado) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenPagoDevolucion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaOrdenPagoDev(pFechaEmisionDesde, pFechaEmisionHasta, pFechaPagoDesde, pFechaPagoHasta, pImporteDesde, pImporteHasta, pPersona, pEstado);
			}
			else{
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(4818);
		}
		
	}

	@Override
	public CuentaCorriente generarCuentaCorriente(Proveedor pProveedor) {		
		return businessBanco.generarCuentaCorriente(pProveedor);
	}
}
