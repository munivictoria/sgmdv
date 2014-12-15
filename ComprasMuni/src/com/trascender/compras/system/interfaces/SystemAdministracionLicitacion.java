package com.trascender.compras.system.interfaces;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.compras.recurso.filtros.FiltroContratacion;
import com.trascender.compras.recurso.persistent.suministros.ActaApertura;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;

@Remote
public interface SystemAdministracionLicitacion{
	
	public static final String JNDI_NAME = "ejb/SystemAdministracionLicitacion/remote";
	
	public void setLlave(long pLlave) throws RemoteException;
	
	public Contratacion addContratacion(Contratacion pContratacion) throws Exception, RemoteException;
	
	public Contratacion updateContratacion(Contratacion pContratacion) throws Exception, RemoteException;
	
	public FiltroContratacion findListaContratacion(FiltroContratacion pFiltro) throws Exception, RemoteException;
	
	public Contratacion getContratacionPorId(long pIdContratacion) throws Exception, RemoteException;
	
	public void deleteContratacion(Contratacion pContratacion) throws Exception, RemoteException;
	
	public OfertaContratacion addOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception, RemoteException;
	
	public OfertaContratacion updateOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception, RemoteException;
	
	public List<OfertaContratacion> findListaOfertaLicitacion(Contratacion pLicitacion, Date pFechaOferta, Proveedor pProveedor) throws Exception, RemoteException;
	
	public OfertaContratacion getOfertaLicitacionPorId(long pIdLicitacion) throws Exception, RemoteException;
	
	public void deleteOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception, RemoteException;
	
	public ActaApertura addActaApertura(ActaApertura pActaApertura) throws Exception;
	public ActaApertura updateActaApertura(ActaApertura pActaApertura) throws Exception;
	public boolean deleteActaApertura(ActaApertura pActaApertura) throws Exception;
	public ActaApertura getActaAperturaById(Long pId) throws Exception;
	public List<ActaApertura> findListaActaApertura(Contratacion pLicitacion, Proveedor pProveedor, Date pFechaDesde, Date pFechaHasta) throws Exception;
}
