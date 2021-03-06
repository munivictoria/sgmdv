/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.compras.system.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroProveedores;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.exception.TrascenderException;

@Remote
public interface SystemAdministracionProveedores
{
	
	public static final String JNDI_NAME = "ejb/SystemAdministracionProveedores/remote";

   public void setLlave( long pLlave )
      throws java.rmi.RemoteException;

//   /**
//    * Business method
//    */
//   public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor addGrupoProveedor( com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor )
//      throws java.lang.Exception, java.rmi.RemoteException;
//
//   /**
//    * Business method
//    */
//   public java.util.List findListadoGruposProveedores(  )
//      throws java.lang.Exception, java.rmi.RemoteException;
//
//   /**
//    * Business method
//    */
//   public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor updateGrupoProveedor( com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor )
//      throws java.lang.Exception, java.rmi.RemoteException;
//
//   /**
//    * Business method
//    */
//   public void deleteGrupoProveedor( com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor )
//      throws java.lang.Exception, java.rmi.RemoteException;
//
//   /**
//    * Business method
//    */
//   public java.util.List findListaGrupoProveedoresPorNodo( com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor )
//      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Business method
    */
   public com.trascender.compras.recurso.persistent.suministros.Proveedor addProveedor( com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Business method
    */
   public FiltroProveedores findListadoProveedores(FiltroProveedores pFiltro)
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Business method
    */
   public com.trascender.compras.recurso.persistent.suministros.Proveedor updateProveedor( com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor )
      throws java.lang.Exception, java.rmi.RemoteException;
   
   /**
    * Business method 
    */
   public void validarQuitadoBienProvisto(BienProvisto pBienProvisto) throws TrascenderComprasException, java.rmi.RemoteException;

   /**
    * Business method
    */
   public void deleteProveedor( com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Business method
    */
   public com.trascender.compras.recurso.persistent.suministros.Proveedor restoreProveedor( com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Business method
    */
   public com.trascender.compras.recurso.persistent.suministros.Proveedor findProveedorByID( long pIdProveedor )
      throws java.lang.Exception, java.rmi.RemoteException;

//   /**
//    * Business method
//    */
//   public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor findGrupoProveedorPorNombre( java.lang.String pNombre )
//      throws java.lang.Exception, java.rmi.RemoteException;
   
   public List<Bien> findListaBienesProvistos(Proveedor pProveedor) throws TrascenderException;

}
