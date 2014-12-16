/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.contabilidad.system.interfaces;

import java.util.Date;

import javax.ejb.Remote;

/**
 * Remote interface for SystemAdministracionConciliacionBancaria.
 */

@Remote
public interface SystemAdministracionConciliacionBancaria
{
	
	public static final String JNDI_NAME = "ejb/SystemAdministracionConciliacionBancaria/remote";

   public void setLlave( long llave )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pBanco
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.Banco addBanco( com.trascender.contabilidad.recurso.persistent.Banco pBanco )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pBanco
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.Banco updateBanco( com.trascender.contabilidad.recurso.persistent.Banco pBanco )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pId
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.Banco getBancoByID( java.lang.Long pId )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pBanco
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteBanco( com.trascender.contabilidad.recurso.persistent.Banco pBanco )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pNombre
    * @param pSucursal
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaBanco( java.lang.String pNombre,java.lang.String pSucursal )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCuentaBancaria
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.CuentaBancaria addCuentaBancaria( com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCuentaBancaria
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.CuentaBancaria updateCuentaBancaria( com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pId
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.CuentaBancaria getCuentaBancariaByID( java.lang.Long pId )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCuentaBancaria
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteCuentaBancaria( com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pTipoCuenta
    * @param pNumero
    * @param pPropia
    * @param pBanco
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaCuentaBancaria( java.lang.String pTipoCuenta,java.lang.String pNumero,boolean pPropia,com.trascender.contabilidad.recurso.persistent.Banco pBanco )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pLibroBanco
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.LibroBanco addLibroBanco( com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pLibroBanco
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.LibroBanco updateLibroBanco( com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pLibroBanco
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteLibroBanco( com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pId
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.LibroBanco getLibroBancoByID( java.lang.Long pId )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pNombre
    * @param pCuentaBancaria
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaLibroBanco( java.lang.String pNombre,com.trascender.contabilidad.recurso.persistent.CuentaBancaria pCuentaBancaria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
	 * Business method
	 * @param pLibroBanco
	 * @param pFecha
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException 
	 */
	public com.trascender.contabilidad.recurso.persistent.LibroBanco generarLibroBancoDiario(com.trascender.contabilidad.recurso.persistent.LibroBanco pLibroBanco, Date pFecha) 
			throws java.lang.Exception, java.rmi.RemoteException;
			
}