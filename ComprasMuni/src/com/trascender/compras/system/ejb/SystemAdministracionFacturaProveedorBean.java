package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessFacturaLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroFacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaProveedor;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;


@Stateful(name = "ejb/SystemAdministracionFacturaProveedor")
public class SystemAdministracionFacturaProveedorBean implements SystemAdministracionFacturaProveedor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private BusinessFacturaLocal locBusinessFacturaLocal = null;
	private long llave;

	public SystemAdministracionFacturaProveedorBean() {
		super();
	}

	public void setSessionContext(SessionContext ctx)
		throws EJBException,
		RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

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
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaProveedor addFacturaProveedor(
		com.trascender.compras.recurso.persistent.suministros.FacturaProveedor pFacturaProveedor)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, FacturaProveedor.serialVersionUID,Permiso.Accion.INSERT)){
				return this.locBusinessFacturaLocal.addFactura(pFacturaProveedor);
			}else{
				throw new TrascenderComprasException(870);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(470);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public FiltroFacturaProveedor findListaFacturaProveedor(FiltroFacturaProveedor pFiltro)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				FacturaProveedor.serialVersionUID,
				Permiso.Accion.SELECT))
				return this.locBusinessFacturaLocal.findListaFacturaProveedor(
					pFiltro);
			else
				throw new TrascenderComprasException(871);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(471);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaProveedor findFacturaProveedorByID(
		long pID) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				FacturaProveedor.serialVersionUID,
				Permiso.Accion.SELECT))
				return this.locBusinessFacturaLocal
					.findFacturaProveedorByID(pID);
			else
				throw new TrascenderComprasException(871);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(471);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaProveedor updateFacturaProveedor(
		com.trascender.compras.recurso.persistent.suministros.FacturaProveedor pFactura)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				FacturaProveedor.serialVersionUID,
				Permiso.Accion.UPDATE))
				return this.locBusinessFacturaLocal
					.updateFacturaProveedor(pFactura);
			else
				throw new TrascenderComprasException(872);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(472);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteFacturaProveedor(
		com.trascender.compras.recurso.persistent.suministros.FacturaProveedor pFacturaProveedor)
		throws java.lang.Exception {
		try{
			if(SecurityMgr.getInstance().getPermiso(
					this.llave,FacturaProveedor.serialVersionUID,Permiso.Accion.DELETE))
				this.locBusinessFacturaLocal.deleteFacturaProveedor(pFacturaProveedor);
			else
				throw new TrascenderComprasException(873);
		}catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(473);
		}
	}
	
}
