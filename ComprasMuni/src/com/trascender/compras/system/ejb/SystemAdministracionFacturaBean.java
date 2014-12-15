package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessFacturaLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroLiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionFactura;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionFactura")
public class SystemAdministracionFacturaBean implements SystemAdministracionFactura{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BusinessFacturaLocal locBusinessFactura;
	private long llave;
	
	public SystemAdministracionFacturaBean() {
		super();
	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
	RemoteException {
		
	}

	
	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		
	}
	
	/**
	 * Default Create method
	 * 
	 * @throws javax.ejb.CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public void setLlave(long pLlave){
		this.llave = pLlave;
	}
	
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.LineaFactura getLineaFacturaPorId(
			long pId) throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					Factura.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessFactura.getLineaFacturaPorId(pId);
			}
			else{
				throw new TrascenderComprasException(890);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(911);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Factura getFacturaPorId(
			long pId) throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					Factura.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessFactura.getFacturaPorId(pId);
			}
			else{
				throw new TrascenderComprasException(890);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(894);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public List<Factura> findListaFactura(com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor,
			java.util.Date pDesde,java.util.Date pHasta, com.trascender.compras.recurso.persistent.suministros.Factura.Estado pEstado)	
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Factura.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessFactura.findListaFactura(pProveedor, pDesde, pHasta, pEstado);
			}else{
				throw new TrascenderComprasException(890);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(894);
		}
		
	}
	
	public void addLiquidacionCompra(LiquidacionCompra pLiquidacionCompra)	throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionCompra.serialVersionUID,Permiso.Accion.INSERT)){
				this.locBusinessFactura.addLiquidacionCompra(pLiquidacionCompra);
			}
			else{
				throw new TrascenderComprasException(917);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(912);
		}
	}

	public LiquidacionCompra updateLiquidacionCompra(LiquidacionCompra pLiquidacionCompra) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionCompra.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.locBusinessFactura.updateLiquidacionCompra(pLiquidacionCompra);
			}
			else{
				throw new TrascenderComprasException(917);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(913);
		}
	}

	public void deleteLiquidacionCompra(LiquidacionCompra pLiquidacionCompra) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionCompra.serialVersionUID,Permiso.Accion.DELETE)){
				this.locBusinessFactura.deleteLiquidacionCompra(pLiquidacionCompra);
			}
			else{
				throw new TrascenderComprasException(917);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(914);
		}
	}

	public LiquidacionCompra getLiquidacionCompraPorId(Long pId) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionCompra.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessFactura.getLiquidacionCompraPorId(pId);
			}else{
				throw new TrascenderComprasException(917);
			}
		}catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(915);
		}
	}

	public FiltroLiquidacionCompra findListaLiquidacionCompra(
			FiltroLiquidacionCompra pFiltro) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionCompra.serialVersionUID,Permiso.Accion.SELECT)){
				return this.locBusinessFactura.findListaLiquidacionCompra(pFiltro);	
			}
			else{
				throw new TrascenderComprasException(917);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(916);
		}
	}
}
