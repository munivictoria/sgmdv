package com.trascender.compras.system.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.compras.recurso.filtros.FiltroArticulo;
import com.trascender.compras.recurso.filtros.FiltroDeposito;
import com.trascender.compras.recurso.filtros.FiltroMovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.framework.exception.TrascenderException;

/**
 * Remote interface for SystemStock.
 */
@Remote
public interface SystemStock {
	
	public static final String JNDI_NAME = "ejb/SystemStock/remote";

	public void setLlave( long llave )
	throws TrascenderException, java.rmi.RemoteException;

	public Deposito addDeposito(Deposito pDeposito) throws Exception, java.rmi.RemoteException;

	public Deposito updateDeposito (Deposito pDeposito) throws Exception, java.rmi.RemoteException;

	public void deleteDeposito (Deposito pDeposito) throws Exception, java.rmi.RemoteException;

	public Deposito getDepositoByID(long pId) throws Exception, java.rmi.RemoteException;

	public FiltroDeposito findListaDeposito (FiltroDeposito pFiltro) throws Exception, java.rmi.RemoteException;

	public Stock addStock(Stock pStock) throws Exception, java.rmi.RemoteException;

	public Stock updateStock (Stock pStock) throws Exception, java.rmi.RemoteException;

	public void deleteStock (Stock pStock) throws TrascenderException, java.rmi.RemoteException;

	public Stock getStockByID(long pId) throws Exception, java.rmi.RemoteException;

	public List<Stock> findListaStock (Bien pBien, Deposito pDeposito) throws Exception, java.rmi.RemoteException;

	public MovimientoDeMercaderia addMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception, java.rmi.RemoteException;

	public MovimientoDeMercaderia updateMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception, java.rmi.RemoteException;
	
	public MovimientoDeMercaderia actualizarMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws TrascenderException, java.rmi.RemoteException;

	public void deleteMovimientoDeMercaderia (MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception, java.rmi.RemoteException;

	public FiltroMovimientoDeMercaderia findListaMovimientoDeMercaderia (FiltroMovimientoDeMercaderia pFiltro) throws Exception, java.rmi.RemoteException;

	public MovimientoDeMercaderia getMovimientoDeMercaderiaByID(long pId) throws Exception, java.rmi.RemoteException;

	public Articulo addArticulo(Articulo pArticulo) throws Exception, RemoteException;
	
	public Articulo updateArticulo(Articulo pArticulo) throws Exception, RemoteException;
	
	public void deleteArticulo(Articulo pArticulo) throws Exception, RemoteException;
	
	public FiltroArticulo findListaArticulo(FiltroArticulo pFiltro) throws Exception, RemoteException;
	
	public Articulo getArticuloPorId(long pIdArticulo) throws Exception, RemoteException;
}