/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.contabilidad.business.interfaces;

import javax.ejb.Local;

/**
 * Local interface for BusinessCajaChica.
 */
@Local
public interface BusinessCajaChicaLocal
{
	
	public static final String JNDI_NAME = "BusinessCajaChicaLocal";
   /**
    * Agregua cajas chicas. Business method
    * @param pCajaChica
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.CajaChica addCajaChica( com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica ) throws java.lang.Exception;

   /**
    * MOodifica cajas chicas. Business method
    * @param pCajaChica
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.CajaChica updateCajaChica( com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica ) throws java.lang.Exception;

   /**
    * Borra cajas chicas. Business method
    * @param pCajaChica
    * @throws java.lang.Exception    */
   public void deleteCajaChica( com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica ) throws java.lang.Exception;

   /**
    * Busca cajas chicas por ID. Business method
    * @param pIdCajaChica
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.CajaChica findCajaChicaByID( long pIdCajaChica ) throws java.lang.Exception;

   /**
    * Busca cajas chicas por nombre. Business method
    * @param pNombre
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaCajaChica( java.lang.String pNombre ) throws java.lang.Exception;

   /**
    * Agrega un Concepto de movimiento de caja chica. Business method
    * @param pConceptoMovimientoCajaChica
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica addConceptoMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica ) throws java.lang.Exception;

   /**
    * Modifica un Concepto de movimiento de caja chica. Business method
    * @param pConceptoMovimientoCajaChica
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica updateConceptoMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica ) throws java.lang.Exception;

   /**
    * Elimina un Concepto de movimiento de caja chica. Business method
    * @param pConceptoMovimientoCajaChica
    * @throws java.lang.Exception    */
   public void deleteConceptoMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica ) throws java.lang.Exception;

   /**
    * Busca un Concepto de movimiento de caja chica por ID Business method
    * @param pId
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica findConceptoMovimientoCajaChicaByID( java.lang.Long pId ) throws java.lang.Exception;

   /**
    * Busca un Concepto de movimiento de caja chica por nombre. Business method
    * @param pNombre
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaConceptoMovimientoCajaChica( java.lang.String pNombre ) throws java.lang.Exception;

   /**
    * Agrega un movimiento de caja chica. Business method
    * @param pMovCajaChica
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica addMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica ) throws java.lang.Exception;

   /**
    * Modifica un movimiento de caja chica. Business method
    * @param pMovCajaChica
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica updateMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica ) throws java.lang.Exception;

   /**
    * Busca un movimiento de caja chica por ID Business method
    * @param pId
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica findMovimientoCajaChicaByID( long pId ) throws java.lang.Exception;

   /**
    * Borra un movimiento de caja chica. Business method
    * @param pMovimientoCajaChica
    * @throws java.lang.Exception    */
   public void deleteMovimientoCajaChica( com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovimientoCajaChica ) throws java.lang.Exception;

   /**
    * Busca una lista de movimiento de caja chica por Fecha desda y hasta, Caja Chica y concepto de movimiento de caja chica. Business method
    * @param pFechaDesde
    * @param pFechaHasta
    * @param pConceptoMovimientoCajaChica
    * @param pCajaChica
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaMovimientoCajaChica( java.util.Date pFechaDesde,java.util.Date pFechaHasta,com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica,com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica ) throws java.lang.Exception;

}
