package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.trascender.compras.business.interfaces.BusinessOrdenCompraLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroCondicionCompra;
import com.trascender.compras.recurso.persistent.suministros.CondicionCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionCondicionCompra;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionCondicionCompra")
public class SystemAdministracionCondicionCompraBean implements SystemAdministracionCondicionCompra {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BusinessOrdenCompraLocal locOrdenCompra;
	private long llave;

	public SystemAdministracionCondicionCompraBean() {
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
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.CondicionCompra addCondicionCompra(
		com.trascender.compras.recurso.persistent.suministros.CondicionCompra pCondicionCompra)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				CondicionCompra.serialVersionUID,
				Permiso.Accion.INSERT)) {
				return this.locOrdenCompra.addCondicionCompra(pCondicionCompra);
			} else
				throw new TrascenderComprasException(801);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(401);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public FiltroCondicionCompra findCondicionCompra(FiltroCondicionCompra filtro)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				CondicionCompra.serialVersionUID,
				Permiso.Accion.SELECT)) {
				return this.locOrdenCompra.findListaCondicionCompra(filtro);
			} else
				throw new TrascenderComprasException(802);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			throw new TrascenderComprasException(402);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.CondicionCompra updateCondicionCompra(
		com.trascender.compras.recurso.persistent.suministros.CondicionCompra pCondicionCompa)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				CondicionCompra.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				return this.locOrdenCompra
					.updateCondicionCompra(pCondicionCompa);
			} else
				throw new TrascenderComprasException(803);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			throw new TrascenderComprasException(403);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteCondicionCompra(
		com.trascender.compras.recurso.persistent.suministros.CondicionCompra pCondicionCompra)
		throws java.lang.Exception {
		try{
			if(SecurityMgr.getInstance().getPermiso(
					this.llave,CondicionCompra.serialVersionUID,Permiso.Accion.DELETE)){
				this.locOrdenCompra.deleteCondicionCompra(pCondicionCompra);
			}else
				throw new TrascenderComprasException(804);
		}catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			throw new TrascenderComprasException(404);
		}
	}
}
