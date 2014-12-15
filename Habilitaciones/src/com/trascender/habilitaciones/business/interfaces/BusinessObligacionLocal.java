/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.habilitaciones.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PermisoHab;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Local
public interface BusinessObligacionLocal {
	
	public static final String JNDI_NAME = "ejb/BusinessObligacionLocal/local";
	
   /**
    * Agrega una obligación al sistema
    * @param pObligacion Obligación a agregar
    * @throws Exception
    */
   public void addObligacion(Obligacion pObligacion ) throws Exception;

   /**
    * Actualiza los datos de una obligacion
    * @param pObligacion Obligación a actualizar
    * @throws Exception
    */
   public void updateObligacion(Obligacion pObligacion) throws Exception;

   /**
    * Recupera un listado de las obligaciones que cumplan con los parámetros ingresados
    * @return 
    * @throws Exception
    */
   public List findListaObligaciones(Persona pPersona, TipoObligacion pTipoObligacion, Obligacion.Estado pEstado, Parcela pParcela, Vehiculo pVehiuculo) throws Exception;

   public List findListaDocHabEspecializados(Persona pPersona,
		   										TipoObligacion pTipoObligacion,
		   										DocHabilitanteEspecializado.Estado pEstadoDocEspecializado,
		   										Obligacion.Estado pEstadoObligacion ) throws Exception;

   /**
    * Firma un permiso de habilitaciones para el usuario acutal
    * @param pPermisoHab
    */
   public PermisoHab firmarDocHabilitante(long pLlave,
		   									PermisoHab pPermisoHab,
		   									String pComentario ) throws Exception;

   /**
    * Recupera un listado de los Permisos que puede firmar el usuario pasado por parámetro
    * @param pUsuario Usuario del que se desean recuperar los permisos a firmar
    * @return 
    * @throws TrascenderException
    */
   public List findListaPermisosHabAFirmar(Usuario pUsuario, Obligacion pObligacion ) throws Exception;
   
   /**
	 * Recupera un listado de los Permisos que puede firmar el usuario pasado por parámetro
	 * @param pUsuario Usuario del que se desean recuperar los permisos a firmar
	 * @param pPersona Persona de la cual se recuperaran las obligaciones pendientes de firma
	 * @return
	 * @throws TrascenderException
	 */
   public List findListaPermisosHabAFirmar(Usuario pUsuario, Persona pPersona ) throws Exception;

   /**
    * Retorna una obligación por el id
    * @param pIdObligacion id de la obligación a recuperar
    * @return obligacion
    * @throws Exception
    */
   public Obligacion getObligacionPorId( long pIdObligacion ) throws Exception;

   public TipoObligacion getTipoObligacionFromObligacion(Obligacion pObligacion ) throws Exception;
   
   public void modificarTitularObligacion(Persona pPersona, Parcela pParcela) throws Exception;

	public List<TipoObligacion> findListaTipoObligacion(String pNombre, Boolean pEsTipoTasaMenor);
}
