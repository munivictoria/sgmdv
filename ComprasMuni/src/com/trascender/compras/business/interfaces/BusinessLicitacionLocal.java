package com.trascender.compras.business.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.trascender.compras.recurso.filtros.FiltroContratacion;
import com.trascender.compras.recurso.persistent.suministros.ActaApertura;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;

@Local
public interface BusinessLicitacionLocal{
	
	public static final String JNDI_NAME = "BusinessLicitacionLocal";
	
	public Contratacion addContratacion(Contratacion pContratacion) throws Exception;
	
	public Contratacion updateContratacion(Contratacion pContratacion) throws Exception;
	
	public FiltroContratacion findListaContratacion(FiltroContratacion pFiltroContratacion) throws Exception;
	
	public Contratacion getContratacionPorId(long pIdContratacion) throws Exception;
	
	public void deleteContratacion(Contratacion pContratacion) throws Exception;
	
	public OfertaContratacion addOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception;
	
	public OfertaContratacion updateOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception;
	
	public List<OfertaContratacion> findListaOfertaLicitacion(Contratacion pLicitacion, Date pFechaOferta, Proveedor pProveedor) throws Exception;
	
	public OfertaContratacion getOfertaLicitacionPorId(long pIdLicitacion) throws Exception;
	
	public void deleteOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception;
	
	public ActaApertura addActaApertura(ActaApertura pActaApertura) throws Exception;
	public ActaApertura updateActaApertura(ActaApertura pActaApertura) throws Exception;
	public boolean deleteActaApertura(ActaApertura pActaApertura) throws Exception;
	public ActaApertura getActaAperturaById(Long pId) throws Exception;
	public List<ActaApertura> findListaActaApertura(Contratacion pLicitacion, Proveedor pProveedor, Date pFechaDesde, Date pFechaHasta);

}
