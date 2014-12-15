/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.catastro.business.interfaces;

import javax.ejb.Local;

import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroCalle;
import com.trascender.catastro.recurso.filtros.FiltroCuadra;
import com.trascender.catastro.recurso.filtros.FiltroManzana;
import com.trascender.catastro.recurso.filtros.FiltroTipoCalle;
import com.trascender.catastro.recurso.persistent.TipoCalle;

/**
 * Local interface for BusinessRegistroGeografico.
 */

@Local
public interface BusinessRegistroGeograficoLocal
{
	public static final String JNDI_NAME="BusinessRegistroGeograficoLocal/local";
   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.Calle addCalle( com.trascender.catastro.recurso.persistent.Calle pCalle ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   @SuppressWarnings("unchecked")
   public FiltroCalle findCalle(FiltroCalle pFiltro ) ;

   public com.trascender.catastro.recurso.persistent.Calle findCalle( java.lang.String pCodigo ) ;

   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.TipoCalle addTipoCalle( com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.TipoCalle updateTipoCalle( com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   @SuppressWarnings("unchecked")
   public FiltroTipoCalle findListaTiposCalle(FiltroTipoCalle pFiltro ) ;

   /**
    * Business method
    * @throws CatastroException
    */
   public com.trascender.catastro.recurso.persistent.Servicio addServicio( com.trascender.catastro.recurso.persistent.Servicio pServicio ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.Servicio updateServicio( com.trascender.catastro.recurso.persistent.Servicio pServicio ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   @SuppressWarnings("unchecked")
   public java.util.List findServicio( java.lang.String pNombre ) ;

   /**
    * Business method
    * @throws Exception
    */
   public com.trascender.catastro.recurso.persistent.Zona addZona( com.trascender.catastro.recurso.persistent.Zona pZona ) 
   throws java.lang.Exception;

   public void deleteZona( com.trascender.catastro.recurso.persistent.Zona pZona ) 
   throws java.lang.Exception;

   public void deleteTipoCalle( com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.Zona updateZona( com.trascender.catastro.recurso.persistent.Zona pZona ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.Manzana addManzana( com.trascender.catastro.recurso.persistent.Manzana pManzana ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.Manzana updateManzana( com.trascender.catastro.recurso.persistent.Manzana pManzana ) 
   throws java.lang.Exception;

   /**
    * Recupera una lista de manzanas a partir de las primeras letras del nombre
    */
   @SuppressWarnings("unchecked")
   public FiltroManzana findListaManzanas(FiltroManzana pFiltro) ;

   /**
    * Recupera una manzana a partir del número de manzana
    */
   public com.trascender.catastro.recurso.persistent.Manzana findManzana( java.lang.Integer pNumero ) 
   throws java.lang.Exception;

   public com.trascender.catastro.recurso.persistent.Manzana getManzanaPorId( long pId ) 
   throws java.lang.Exception;

   public TipoCalle getTipoCallePorId(long pIdTipoCalle) throws Exception;
   
   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.Cuadra addCuadra( com.trascender.catastro.recurso.persistent.Cuadra pCuadra ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.Cuadra updateCuadra( com.trascender.catastro.recurso.persistent.Cuadra pCuadra ) 
   throws java.lang.Exception;

   /**
    * Business method
    */
   @SuppressWarnings("unchecked")
   public FiltroCuadra findListaCuadras( FiltroCuadra pFiltro ) ;

   /**
    * Business method
    */
   public com.trascender.catastro.recurso.persistent.Calle updateCalle( com.trascender.catastro.recurso.persistent.Calle pCalle ) 
   throws java.lang.Exception;

   public void deleteServicio( com.trascender.catastro.recurso.persistent.Servicio pServicio ) 
   throws java.lang.Exception;

   @SuppressWarnings("unchecked")
   public java.util.List findListaCuadrasPorManzana( com.trascender.catastro.recurso.persistent.Manzana pManzana ) 
   throws java.lang.Exception;

   public com.trascender.catastro.recurso.persistent.Calle getCallePorId( java.lang.Long pId ) 
   throws java.lang.Exception;

   /**
    * recupera una cuadra por el número de identificación único
    * @param pId
    * @return 
    * @throws Exception
    */
   public com.trascender.catastro.recurso.persistent.Cuadra getCuadraPorId( java.lang.Long pId ) 
   throws java.lang.Exception;

}
