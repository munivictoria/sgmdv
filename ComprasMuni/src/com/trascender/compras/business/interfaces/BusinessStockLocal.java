package com.trascender.compras.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.compras.recurso.filtros.FiltroArticulo;
import com.trascender.compras.recurso.filtros.FiltroDeposito;
import com.trascender.compras.recurso.filtros.FiltroMovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.compras.recurso.persistent.suministros.Bien;

@Local
public interface BusinessStockLocal{

	public static final String JNDI_NAME = "BusinessStockLocal";
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pStock
	 * @return
	 * @throws Exception
	 */
	public Stock addStock(Stock pStock) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pStock
	 * @return
	 * @throws Exception
	 */
	public Stock updateStock(Stock pStock) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Stock getStockByID(long pId) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pBien
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Stock> findListaStock (Bien pBien, Deposito pDeposito) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pStock
	 * @throws Exception
	 */
	public void deleteStock(Stock pStock) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pDeposito
	 * @return
	 * @throws Exception
	 */
	public Deposito addDeposito (Deposito pDeposito) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pDeposito
	 * @return
	 * @throws Exception
	 */
	public Deposito updateDeposito (Deposito pDeposito) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Deposito getDepositoByID(long pId) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pDeposito
	 * @throws Exception
	 */
	public void deleteDeposito (Deposito pDeposito) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pNombre
	 * @param pArea
	 * @return
	 * @throws Exception
	 */
	public FiltroDeposito findListaDeposito(FiltroDeposito pFiltro) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pMovimientoDeMercaderia
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia addMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pMovimientoDeMercaderia
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia updateMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pMovimientoDeMercaderia
	 * @throws Exception
	 */
	public void deleteMovimientoDeMercaderia (MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia getMovimientoDeMercaderiaByID(long pId) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pStock
	 * @param pArea
	 * @param pUsuario
	 * @return
	 * @throws Exception
	 */
	public FiltroMovimientoDeMercaderia findListaMovimientoDeMercaderia (FiltroMovimientoDeMercaderia pFiltro) throws Exception;
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="local"
	 * @param pMovimientoDeMercaderia
	 * @param pStock
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia actualizarMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception;
	
	public Articulo addArticulo(Articulo pArticulo) throws Exception;
	
	public Articulo updateArticulo(Articulo pArticulo) throws Exception;
	
	public void deleteArticulo(Articulo pArticulo) throws Exception;
	
	public FiltroArticulo findListaArticulo(FiltroArticulo pFiltro) throws Exception;
	
	public Articulo getArticuloPorId(long pIdArticulo) throws Exception;
	
	
}
