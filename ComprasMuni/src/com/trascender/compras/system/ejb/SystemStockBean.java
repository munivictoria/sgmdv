package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessStockLocal;
import com.trascender.compras.exception.TrascenderInventarioException;
import com.trascender.compras.recurso.filtros.FiltroArticulo;
import com.trascender.compras.recurso.filtros.FiltroDeposito;
import com.trascender.compras.recurso.filtros.FiltroMovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.system.interfaces.SystemStock;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;


@Stateful(name = "ejb/SystemStock")
public class SystemStockBean implements SystemStock{

	private static final long serialVersionUID = 1L;
	@EJB
	private BusinessStockLocal businessStock;
	@EJB
	private SystemUsuario systemUsuario;
	private long llave;

	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		
	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		
	}
	
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}
	
	/**
	 * @ejb.interface-method view-type ="remote"
	 */
	public void setLlave(long llave) throws TrascenderException{
		try{
			this.llave = llave;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(951);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pStock
	 * @return
	 * @throws Exception
	 */
	public Stock addStock(Stock pStock) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Stock.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessStock.addStock(pStock);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 *  Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pStock
	 * @return
	 * @throws Exception
	 */
	public Stock updateStock (Stock pStock) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Stock.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessStock.updateStock(pStock);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pStock
	 * @throws Exception
	 */
	public void deleteStock (Stock pStock) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Stock.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessStock.deleteStock(pStock);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 *  Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Stock getStockByID(long pId) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Stock.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessStock.getStockByID(pId);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	
	/**
	 *  Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pBien
	 * @return
	 * @throws Exception
	 */
	public List<Stock> findListaStock (Bien pBien, Deposito pDeposito) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Stock.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessStock.findListaStock(pBien, pDeposito);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
		
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pMovimientoDeMercaderia
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia addMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoDeMercaderia.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessStock.addMovimientoDeMercaderia(pMovimientoDeMercaderia);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pMovimientoDeMercaderia
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia updateMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoDeMercaderia.serialVersionUID, Permiso.Accion.UPDATE)){
				
				return this.businessStock.updateMovimientoDeMercaderia(pMovimientoDeMercaderia);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type = "remote"
	 * @param pMovimientoDeMercaderia
	 * @param pStock
	 * @return
	 * @throws TrascenderException
	 */
	public MovimientoDeMercaderia actualizarMovimientoDeMercaderia(MovimientoDeMercaderia pMovimientoDeMercaderia) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getUsuario(llave) != null){
				 FirmaPermiso locFirmaPermiso = new FirmaPermiso();
                 locFirmaPermiso.setFechaHora(Calendar.getInstance().getTime());
                 locFirmaPermiso.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
                 locFirmaPermiso.setComentario("Movimiento de Mercadería Aceptado por "+locFirmaPermiso.getUsuario().toString()+" en Fecha: "+Util.getString(Calendar.getInstance().getTime(), "dd/MM/yyyy hh:mm:ss"));
                 pMovimientoDeMercaderia.setFirmaReceptor(locFirmaPermiso);
                 
				return this.businessStock.actualizarMovimientoDeMercaderia(pMovimientoDeMercaderia);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(100);
		}
	}
	/**
	 * Business method 
	 * @ejb.interface-method view-type="remote"
	 * @param pMovimientoDeMercaderia
	 * @throws Exception
	 */
	public void deleteMovimientoDeMercaderia (MovimientoDeMercaderia pMovimientoDeMercaderia) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoDeMercaderia.serialVersionUID, Permiso.Accion.DELETE)){
				FirmaPermiso locFirmaPermiso = new FirmaPermiso();
                locFirmaPermiso.setFechaHora(Calendar.getInstance().getTime());
                locFirmaPermiso.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
                locFirmaPermiso.setComentario("Movimiento de Mercadería Anulado por "+locFirmaPermiso.getUsuario().toString()+" en Fecha: "+Util.getString(Calendar.getInstance().getTime(), "dd/MM/yyyy hh:mm:ss"));
                pMovimientoDeMercaderia.setFirmaReceptor(locFirmaPermiso);	
				this.businessStock.deleteMovimientoDeMercaderia(pMovimientoDeMercaderia);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pStock
	 * @param pArea
	 * @param pUsuario
	 * @return
	 * @throws Exception
	 */
	public FiltroMovimientoDeMercaderia findListaMovimientoDeMercaderia (FiltroMovimientoDeMercaderia pFiltro) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoDeMercaderia.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessStock.findListaMovimientoDeMercaderia(pFiltro);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public MovimientoDeMercaderia getMovimientoDeMercaderiaByID(long pId) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoDeMercaderia.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessStock.getMovimientoDeMercaderiaByID(pId);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pDeposito
	 * @return
	 * @throws Exception
	 */
	public Deposito addDeposito (Deposito pDeposito) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Deposito.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessStock.addDeposito(pDeposito);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pDeposito
	 * @return
	 * @throws Exception
	 */
	public Deposito updateDeposito (Deposito pDeposito) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Deposito.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessStock.updateDeposito(pDeposito);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Deposito getDepositoByID(long pId) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Deposito.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessStock.getDepositoByID(pId);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pDeposito
	 * @throws Exception
	 */
	public void deleteDeposito (Deposito pDeposito) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Deposito.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessStock.deleteDeposito(pDeposito);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNombre
	 * @param pArea
	 * @return
	 * @throws Exception
	 */
	public FiltroDeposito findListaDeposito(FiltroDeposito pFiltro) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Deposito.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessStock.findListaDeposito(pFiltro);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	public Articulo addArticulo(Articulo pArticulo) throws Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Articulo.serialVersionUID, Permiso.Accion.INSERT)){
				return this.businessStock.addArticulo(pArticulo);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	public Articulo updateArticulo(Articulo pArticulo) throws Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Articulo.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.businessStock.updateArticulo(pArticulo);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	public void deleteArticulo(Articulo pArticulo) throws Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Articulo.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessStock.deleteArticulo(pArticulo);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	public FiltroArticulo findListaArticulo(FiltroArticulo pFiltro) throws Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Articulo.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessStock.findListaArticulo(pFiltro);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
	
	public Articulo getArticuloPorId(long pIdArticulo) throws Exception, RemoteException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Articulo.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessStock.getArticuloPorId(pIdArticulo);
			}
			else throw new TrascenderInventarioException(950);
		}
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderInventarioException(99);
		}
	}
}
