package com.trascender.framework.business.interfaces;

import javax.ejb.Local;

import com.trascender.framework.recurso.filtros.FiltroRol;
import com.trascender.framework.recurso.persistent.Rol;

@Local
public interface BusinessRolLocal {
	
	public final static String JNDI_NAME = "ejb/BusinessRol";
	
	/**
	 * Devuelve un Rol segun el id Ingresado
	 * @param pId
	 * @throws Exception
	 */
	public Rol getRolById(long pId) throws Exception;
	
   /**
    * Business method
    */
   public void addRol( com.trascender.framework.recurso.persistent.Rol pRol ) throws java.lang.Exception;

   /**
    * Business method
    */
   public void updateRol( com.trascender.framework.recurso.persistent.Rol pRol ) throws java.lang.Exception;

   /**
    * Business method
    */
   public FiltroRol findRol(FiltroRol pFiltro) throws java.lang.Exception;

   /**
    * Business method
    */
   public void removePermiso( com.trascender.framework.recurso.persistent.Permiso pPermiso ) throws java.lang.Exception;

   /**
    * Business method
    */
   public java.util.Set getListaPermisos( com.trascender.framework.recurso.persistent.Rol pRol ) ;

   /**
    * Recupera una lista de permisos del rol en conjunto con todos los permisos que a�n no han sido asignados
    * @param pRol rol por el cual se desea filtrar
    * @return lista de permisos
    */
   public java.util.List getListaPermisosPorRol( com.trascender.framework.recurso.persistent.Rol pRol ) throws com.trascender.framework.exception.TrascenderException;

}
