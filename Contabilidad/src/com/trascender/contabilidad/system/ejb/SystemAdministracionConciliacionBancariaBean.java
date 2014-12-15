package com.trascender.contabilidad.system.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.contabilidad.business.interfaces.BusinessBancoLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConciliacionBancaria;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionConciliacionBancaria")
public class SystemAdministracionConciliacionBancariaBean implements SystemAdministracionConciliacionBancaria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4210503680418887956L;
	private long llave;
	
	@EJB
	private BusinessBancoLocal businessBanco;
	
	
	public SystemAdministracionConciliacionBancariaBean() {
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
	 * @param llave
	 * @throws TrascenderException
	 */
	public void setLlave(long llave) throws TrascenderException{
		try{
			this.llave = llave;
		}
		catch(Exception e){
			throw new TrascenderContabilidadException(901);
		}
	}
	
	/**
	 * Business method 
	 * @ejb.interface-method view-type="remote"
	 * @param pBanco
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Banco addBanco(com.trascender.contabilidad.recurso.persistent.Banco pBanco)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Banco.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessBanco.addBanco(pBanco);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(380);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pBanco
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Banco updateBanco(com.trascender.contabilidad.recurso.persistent.Banco pBanco)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Banco.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.updateBanco(pBanco);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(382);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.Banco getBancoByID(Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Banco.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getBancoByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(381);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pBanco
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteBanco (com.trascender.contabilidad.recurso.persistent.Banco pBanco)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Banco.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessBanco.deleteBanco(pBanco);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(383);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNombre
	 * @param pSucursal
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaBanco (String pNombre, String pSucursal)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Banco.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaBanco(pNombre, pSucursal);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(384);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCuentaBancaria
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.CuentaBancaria addCuentaBancaria (com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaBancaria.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessBanco.addCuentaBancaria(pCuentaBancaria);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(490);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCuentaBancaria
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.CuentaBancaria updateCuentaBancaria (com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaBancaria.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.updateCuentaBancaria(pCuentaBancaria);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(492);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.CuentaBancaria getCuentaBancariaByID(Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaBancaria.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getCuentaBancariaByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(491);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCuentaBancaria
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteCuentaBancaria (com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaBancaria.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessBanco.deleteCuentaBancaria(pCuentaBancaria);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(493);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pTipoCuenta
	 * @param pNumero
	 * @param pPropia
	 * @param pBanco
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaCuentaBancaria (String pTipoCuenta, String pNumero, boolean pPropia, 
			com.trascender.contabilidad.recurso.persistent.Banco pBanco) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CuentaBancaria.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaCuentaBancaria(pTipoCuenta, pNumero, pPropia, pBanco);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(494);
		}
	}
	
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pLibroBanco
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.LibroBanco addLibroBanco(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroBanco.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessBanco.addLibroBanco(pLibroBanco);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(390);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pLibroBanco
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.LibroBanco updateLibroBanco(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroBanco.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessBanco.updateLibroBanco(pLibroBanco);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(392);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pLibroBanco
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteLibroBanco(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroBanco.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessBanco.deleteLibroBanco(pLibroBanco);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(393);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.LibroBanco getLibroBancoByID(Long pId)
			throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroBanco.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.getLibroBancoById(pId);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(391);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNombre
	 * @param pCuentaBancaria
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public List findListaLibroBanco(String pNombre, com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria)
			 throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroBanco.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.findListaLibroBanco(pNombre, pCuentaBancaria);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			throw new TrascenderContabilidadException(394);
		}
	}
	
	/**
	 * Business method
	 * @param pLibroBanco
	 * @param pFecha
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.LibroBanco generarLibroBancoDiario(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco, Date pFecha) 
			throws java.lang.Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LibroBanco.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessBanco.generarLibroBancoDiario(pLibroBanco, pFecha);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(395);
		}
	}
}
