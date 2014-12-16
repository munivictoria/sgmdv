/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.contabilidad.system.interfaces;

import javax.ejb.Remote;


/**
 * Remote interface for SystemAdministracionCajaChica.
 */
@Remote
public interface SystemAdministracionCajaChica
{
	
	public static final String JNDI_NAME = "ejb/SystemAdministracionCajaChica/remote";

   public void setLlave( long llave )
      throws java.rmi.RemoteException;

   /**
    * Business method
    * @param pCajaChica
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.CajaChica addCajaChica( com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCajaChica
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.CajaChica updateCajaChica( com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pIdCajaChica
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.CajaChica findCajaChicaByID( long pIdCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pCajaChica
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteCajaChica( com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pNombre
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaCajaChica( java.lang.String pNombre )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pMovCajaChica
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica addMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pMovCajaChica
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica updateMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pId
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica findMovimientoCajaChicaByID( long pId )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pMovCajaChica
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pFechaDesde
    * @param pFechaHasta
    * @param pConceptoMovimientoCajaChica
    * @param pCajaChica
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaMovimientoCajaChica( java.util.Date pFechaDesde,java.util.Date pFechaHasta,com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica,com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pConceptoMovimientoCajaChica
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica addConceptoMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pConceptoMovimientoCajaChica
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica updateConceptoMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pConceptoMovimientoCajaChica
    * @throws com.trascender.framework.exception.TrascenderException    */
   public void deleteConceptoMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pId
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica findConceptoMovimientoCajaChicaByID( java.lang.Long pId )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    * @param pNombre
    * @return 
    * @throws com.trascender.framework.exception.TrascenderException    */
   public java.util.List findListaConceptosCajaChica( java.lang.String pNombre )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;
   

}