package com.trascender.catastro.system.interfaces;

import java.util.Date;

import javax.ejb.Remote;

import com.trascender.catastro.recurso.filtros.FiltroTituloPropiedad;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;

/**
 * Remote interface for SystemRegistroPropiedad.
 */
@Remote
public interface SystemRegistroPropiedad
{
	 public static final String JNDI_NAME="ejb/SystemRegistroPropiedad/remote";
   /**
    * Agrega un t�tulo de propiedad
    * @param pTituloPropiedad titulo de propiedad a agregar
    * @throws TrascenderException
    */
   public TituloPropiedadParcelario addTituloPropiedad(TituloPropiedadParcelario pTituloPropiedad )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Actualiza un t�tulo de propiedad
    * @param pTituloPropiedad t�tulo de propiedad a agregar
    * @throws TrascenderException
    */
   public TituloPropiedadParcelario updateTituloPropiedad(TituloPropiedadParcelario pTituloPropiedad ) throws TrascenderException, java.rmi.RemoteException;

   /**
    * Recupera un listado de t�tulos de propiedad
    * @param pFechaDesde l�mite inferior de fechas desde la cual buscar el t�tulo de propiedad
    * @param pFechaHasta l�mite superior de fechas hasta la cual buscar el t�tulo de propiedad
    * @param pPersona Persona a la que pertenece el t�tulo de propiedad
    * @throws TrascenderException
    */
   @SuppressWarnings("unchecked")
   public FiltroTituloPropiedad findListaTituloPropiedad( FiltroTituloPropiedad pFiltro) throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Business method
    */
   public void setLlave( long pLlave )
      throws java.rmi.RemoteException;

   public com.trascender.catastro.recurso.persistent.TituloPropiedad getTituloPropiedadPorId( long pIdTituloPropiedad )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

}
