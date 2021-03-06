/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.habilitaciones.system.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionArrendamiento;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionOSP;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.FiltroObligacionTGI;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PermisoHab;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Remote
public interface SystemObligacion {
	
	public static final String JNDI_NAME = "ejb/SystemObligacion/remote";
	
   /**
    * Agrega una obligación
    * @param pObligacion
    * @throws TrascenderException
    */
   public void addObligacion( Persona pPersona, Obligacion pObligacion ) throws TrascenderException, RemoteException;

   /**
    * Actualiza los datos de una obligacion
    * @param pObligacion
    * @throws TrascenderException
    */
   public void updateObligacion( Obligacion pObligacion ) throws TrascenderException, RemoteException;

   /**
    * Recupera un listado de obligaciones
    * @return 
    * @throws TrascenderException
    */
   public List findListaObligaciones( Persona pPersona, TipoObligacion pTipoObligacion, Obligacion.Estado pEstadoObligacion, Parcela pParcela, Vehiculo pVehiculo) throws TrascenderException, RemoteException;

   /**
    * Recupera un listado de documentos habilitantes
    * @param pPersona persona a la que pertenece la obligacion
    * @param pTipoObligacion tipo de obligacion (especificada por el tipo de documento habilitante)
    * @param pEstadoDocHabilitante estado del documento habilitante
    * @param pEstadoObligacion estado en que se encuentra la obligación
    * @return Listado de documentos habilitantes
    * @throws TrascenderException    */
   public List findListaDocHabEspecializados( Persona pPersona, TipoObligacion pTipoObligacion, DocHabilitanteEspecializado.Estado pEstadoDocHabilitante,com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado pEstadoObligacion ) throws TrascenderException, RemoteException;

   /**
    * Firma un permiso de habilitaciones para el usuario acutal
    * @param pPermisoHab
    */
   public PermisoHab firmarDocHabilitante( PermisoHab pPermisoHab, String pComentario ) throws TrascenderException, RemoteException;

   /**
    * Recupera un listado de los Permisos que puede firmar el usuario pasado por parámetro
    * @param pUsuario Usuario del que se desean recuperar los permisos a firmar
    * @return 
    * @throws TrascenderException
    */
   public List findListaPermisosHabAFirmar( Usuario pUsuario, Obligacion pObligacion ) throws TrascenderException, RemoteException;

   /**
    * Recupera un listado de los permisos a habilitar por el usuario actual
    * @return Listado de PermisoHab
    * @throws TrascenderException
    */
   public List findListaPermisosHabAFirmar( Obligacion pObligacion ) throws TrascenderException, RemoteException;
   
   /**
	 * Recupera un listado de los permisos a habilitar por el usuario actual
	 * @return Listado de PermisoHab
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaPermisosHabAFirmar(Persona pPersona) throws TrascenderException, RemoteException; 

   /**
    * Recupera una obligación por el id
    * @param pIdObligacion id de la obligacion a recuperar
    * @return obligacion
    * @throws TrascenderException
    */
   public Obligacion getObligacionPorId( long pIdObligacion ) throws TrascenderException, RemoteException;

   public TipoObligacion getTipoObligacionFromObligacion( Obligacion pObligacion )   throws Exception, RemoteException;

   /**
    * Recupera el listado de obligaciones cuyos documentos son del tipo TGI y cumplen con los parámetros ingresados
    * @param pPersona persona a la que pertenece la obligacion
    * @param pNumeroRegistro número de registro de la parcela
    * @return 
    * @throws TrascenderException
    */
   public FiltroObligacionTGI findListaObligacionesTGI(FiltroObligacionTGI pFiltro) throws TrascenderException, RemoteException;

   public FiltroObligacionOSP findListaObligacionesOSP(FiltroObligacionOSP pFiltro) throws TrascenderException, RemoteException;

   public List findListaObligacionesPFO( Persona pPersona, Integer pNumeroRegistro, Obra pObra ) throws TrascenderException, RemoteException;

   public FiltroObligacionSHPS findListaObligacionesSHPS(FiltroObligacionSHPS pFiltro) throws TrascenderException, RemoteException;
   
   public FiltroObligacionTasaMenor findListaObligacionesTasaMenor(FiltroObligacionTasaMenor pFiltro) throws TrascenderException;

   public void modificarTitularObligacion(Persona pPersona, Parcela pParcela) throws RemoteException, TrascenderException;
   
   public FiltroObligacionAutomotor findListaObligacionesAutomotor(FiltroObligacionAutomotor pFiltro) throws TrascenderException, RemoteException;
   
   public FiltroObligacionCementerio findListaObligacionesCementerio(FiltroObligacionCementerio pFiltro) throws TrascenderException, RemoteException;
   
   public FiltroObligacionArrendamiento findListaObligacionesArrendamiento(FiltroObligacionArrendamiento pFiltro) throws TrascenderException;
   /**
    * Coloca el valor de la llave
    * @param pLlave
    */
   public void setLlave( long pLlave ) throws RemoteException;

}
