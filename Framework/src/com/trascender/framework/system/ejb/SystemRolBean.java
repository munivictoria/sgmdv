package com.trascender.framework.system.ejb;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.business.interfaces.BusinessRolLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroRol;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.system.interfaces.SystemRol;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemRol"
 *           display-name="Name for SystemRol"
 *           description="Description for SystemRol"
 *           jndi-name="ejb/SystemRol"
 *           type="Stateful"
 *           view-type="remote"
 */
@Stateful(name = "ejb/SystemRol")
public class SystemRolBean implements SystemRol {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long llave = 0;
	
	@EJB
	private BusinessRolLocal rolLocal = null;

	public SystemRolBean() {
		super();
	}

	public void setSessionContext(SessionContext pCtx)
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
	public void addRol(com.trascender.framework.recurso.persistent.Rol pRol)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Rol.serialVersionUID,
				Permiso.Accion.INSERT)) {
				pRol.setDesde(Calendar.getInstance().getTime());
				this.rolLocal.addRol(pRol);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(380);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void updateRol(com.trascender.framework.recurso.persistent.Rol pRol)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Rol.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				this.rolLocal.updateRol(pRol);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(381);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void removeRol(Rol pRol)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Rol.serialVersionUID,
				Permiso.Accion.DELETE)) {
				pRol.setEstado(Rol.Estado.ELIMINADO);
				pRol.setHasta(Calendar.getInstance().getTime());
				this.rolLocal.updateRol(pRol);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(382);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void restoreRol(com.trascender.framework.recurso.persistent.Rol pRol)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Rol.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				pRol.setEstado(Rol.Estado.ACTIVO);
				pRol.setHasta(null);
				this.rolLocal.updateRol(pRol);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(383);
		}
	}
	/**
	 * Recupera un listado de roles
	 * @param pNombre primeras letras del nombre del rol
	 * @param pDesde 
	 * @param pHasta
	 * @param pFirma si puede realizar firma o no
	 * @param pArea area a la que pertenece el rol
	 * @ejb.interface-method  view-type = "remote"
	 */
	public FiltroRol findRol(FiltroRol filtro)	throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Rol.serialVersionUID,
				Permiso.Accion.SELECT)) {
				return this.rolLocal.findRol(filtro);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(384);
		}
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
	public java.util.Set getListaPermisos(
		com.trascender.framework.recurso.persistent.Rol pRol)
		throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave,Rol.serialVersionUID,Permiso.Accion.SELECT)) {
				return this.rolLocal.getListaPermisos(pRol);			
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(384);
		}
	}
	
	/**
	 * Recupera un rol por el id 
	 * @param pId id del rol
	 * @return nulo si no encuentra nada.
	 * @ejb.interface-method view-type = "remote"
	 */
	public Rol getRolById(long pId) throws TrascenderException{
	try{
		if (SecurityMgr.getInstance().getPermiso(this.llave,Rol.serialVersionUID,Permiso.Accion.SELECT)) {
			return this.rolLocal.getRolById(pId);		
		} else
			throw new TrascenderFrameworkException(805);
	} catch (TrascenderFrameworkException e) {
		e.printStackTrace();
		throw e;
	} catch (Exception e) {
		e.printStackTrace();
		throw new TrascenderFrameworkException(384);
	}
		
	}
	
	/**
	 * Recupera una lista de permisos del rol en conjunto con todos los permisos
	 * que aún no han sido asignados
	 * @param pRol rol por el cual se desea filtrar
	 * @return lista de permisos
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List getListaPermisosPorRol(com.trascender.framework.recurso.persistent.Rol pRol) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Rol.serialVersionUID,Permiso.Accion.SELECT)){
				return this.rolLocal.getListaPermisosPorRol(pRol);
			}
			else{
				throw new TrascenderFrameworkException(805);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(384);
		}
	}
}
