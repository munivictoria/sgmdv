/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.contabilidad.business.interfaces;

import javax.ejb.Local;

/**
 * Local interface for BusinessSubdiarioCaja.
 */
@Local
public interface BusinessSubdiarioCajaLocal
{
	
	public static final String JNDI_NAME = "BusinessSubdiarioCajaLocal";
   /**
    * Business method
    * @param pSubdiarioCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja addSubdiarioCaja( com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja ) throws java.lang.Exception;

   /**
    * Business method
    * @param pSubdiarioCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja updateSubdiarioCaja( com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja ) throws java.lang.Exception;

   /**
    * Business method
    * @param pSubdiarioCaja
    * @throws java.lang.Exception    */
   public void deleteSubdiarioCaja( com.trascender.contabilidad.recurso.persistent.SubdiarioCaja pSubdiarioCaja ) throws java.lang.Exception;

   /**
    * Business method
    * @param pIdSubdiarioCaja
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja getSubdiarioCajaByID( java.lang.Long pIdSubdiarioCaja ) throws java.lang.Exception;

   /**
    * Busines method
    * @param pTipo
    * @param pMes
    * @param pAnio
    * @return 
    * @throws java.lang.Exception    */
   public java.util.List findListaSubdiarioCaja( com.trascender.contabilidad.recurso.persistent.SubdiarioCaja.Tipo pTipo,java.util.Date pFechaDesde,java.util.Date pFechaHasta ) throws java.lang.Exception;

   /**
    * Business method
    * @param pFecha
    * @param pTipo
    * @return 
    * @throws java.lang.Exception    */
   public com.trascender.contabilidad.recurso.persistent.SubdiarioCaja generarSubdiarioCaja( java.util.Date pFecha,com.trascender.contabilidad.recurso.persistent.PlanDeCuenta pPlanDeCuenta,com.trascender.contabilidad.recurso.persistent.SubdiarioCaja.Tipo pTipo ) throws java.lang.Exception;
   
   /**
    * Business method
    * @param pOrdenCompra
    * @return
    * @throws java.lang.Exception
    */
   public boolean validarAceptacionOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra) throws java.lang.Exception;
   
   /**
    * Business method
    * @param pOrdenCompra*/
   public void aceptarOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra) throws java.lang.Exception;

}
