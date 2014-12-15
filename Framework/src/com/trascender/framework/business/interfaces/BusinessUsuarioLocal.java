package com.trascender.framework.business.interfaces;

import javax.ejb.Local;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.filtros.FiltroRol;
import com.trascender.framework.recurso.filtros.FiltroUsuario;
import com.trascender.framework.recurso.persistent.Usuario;

@Local
public interface BusinessUsuarioLocal {
	
	public final static String JNDI_NAME = "ejb/BusinessUsuario/local";
	
   /**
    * Business method
    */
   public com.trascender.framework.recurso.persistent.Usuario addUsuario( com.trascender.framework.recurso.persistent.Usuario pUsuario ) throws java.lang.Exception;

   /**
    * Business method
    */
   public void updateUsuario( com.trascender.framework.recurso.persistent.Usuario pUsuario ) throws java.lang.Exception;

   /**
    * Permite la recuperaci�n de un listado de usuarios de la base de datos
    * @param pUser nombre del usuario, en caso de haber sido ingresado un password, se filtrar� por el nombre completo del usuario, en caso contrario se utilizar�n las primeras letras solamente
    * @param pPassword clave del usuario, en caso de haber sido ingresado un nombre de usuario, entonces se utilizar� la clave completa para filtrar. sino este criterio se ignora Business method
    */
   public FiltroUsuario findUsuario(FiltroUsuario pFiltro) throws java.lang.Exception;

   public Usuario getUsuarioPorId(long pId) throws TrascenderException;
   /**
    * Business method
    */
   public com.trascender.framework.recurso.persistent.FirmaPermiso firmar( com.trascender.framework.recurso.persistent.Usuario pUsuario,java.lang.String pComentario ) throws java.lang.Exception;

   /**
    * Recupera un usuario por nombre de usuario y clave
    * @param pUser
    * @param pClave
    * @return 
    */
   public com.trascender.framework.recurso.persistent.Usuario getUsuarioPorNombre( java.lang.String pUser ) throws java.lang.Exception;

}
