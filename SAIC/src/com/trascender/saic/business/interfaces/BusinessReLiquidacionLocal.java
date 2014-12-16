/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.saic.business.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.ParametroValuadoAlicuota;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;

/**
 * Local interface for BusinessReLiquidacion.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
@Local
public interface BusinessReLiquidacionLocal
{
	 public static final String JNDI_NAME="BusinessReLiquidacionLocal/local";
	 
   /**
    * Reliquida una obligacion sin fechas de vencimiento
    * @param pLiquidacionTasa
    * @param pFechaReLiquidacion
    * @param pGuardarReliquidacion 
    * @return 
    * @throws Exception
    */
   public List reliquidarObligacion( 
		   com.trascender.saic.recurso.persistent.LiquidacionTasa pLiquidacionTasa,
		   java.util.Date pFechaReLiquidacion, 
		   List<String> pListaNombresNuevosParametrosValuados, 
		   List<ParametroValuadoAlicuota> pListaNuevosParametrosAlicuotas,
		   Map<String, Object> pMapaValoresFijos,
		   com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal,
		   boolean pAplicarIntereses,
		   boolean pGuardarReliquidacion 
		   ) throws java.lang.Exception;

   public List reliquidarObligacion( 
		   com.trascender.saic.recurso.persistent.LiquidacionTasa pLiquidacionTasa,
		   java.util.Date pFechaReLiquidacion,java.util.Date pFechaNuevoVencimiento,
		   boolean pAplicarIntereses
		   //com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal 
		   ) throws java.lang.Exception;

   public List findListaRegistrosDeudaContribuyente( 
		   Persona pPersona,
		   TipoDeuda pTipoDeuda,
		   Periodo pPeriodo,
		   EstadoRegistroDeuda pEstadoRegistroDeuda,
		   TipoObligacion pTipoObligacion,
		   com.trascender.catastro.recurso.persistent.Parcela pParcela) throws java.lang.Exception;

   public List getListaRegistrosDeudaAsociados(
		   RegistroDeuda pRegistroDeuda,
		   TipoDeuda pTipoDeuda) throws java.lang.Exception;
   
   public com.trascender.saic.recurso.persistent.Reliquidacion getReliquidacionPorId(long pId) 
   	throws java.lang.Exception;
   
   public com.trascender.saic.recurso.persistent.Reliquidacion getReliquidacionPorIdNuevaDeuda(long pIdDeuda)
	throws java.lang.Exception;
   
   public LiquidacionTasa calcularIntereses(LiquidacionTasa pLiquidacionTasa, 
			Date pFecha, boolean aplicarIntereses,
			boolean guardarCambios, boolean duranteReliquidacion) throws Exception;
   
   public void setLlave(long llave);
   
}