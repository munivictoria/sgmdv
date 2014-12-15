
package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessTipoAlicuotaLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroRubroSHPS;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.system.interfaces.SystemAlicuota;

@Stateful(name = "ejb/SystemAlicuota")
public class SystemAlicuotaBean implements SystemAlicuota {

	private long llave;

	@EJB
	private BusinessTipoAlicuotaLocal businessTipoAlicuota;
	/**
	 * 
	 */
	private static final long serialVersionUID = 105242132757100820L;

	public SystemAlicuotaBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
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
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	/**
	 * 
	 * @param pRegistroAlicuota
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.RegAlicuota addRegistroAlicuota(com.trascender.habilitaciones.recurso.persistent.RegAlicuota pRegistroAlicuota)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegAlicuota.serialVersionUID, Permiso.Accion.INSERT)) {
				return (RegAlicuota) this.businessTipoAlicuota.addTipoAlicuota(pRegistroAlicuota);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(300);
		}
	}

	/**
	 * 
	 * @param pRegistroAlicuota
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.RegAlicuota updateRegistroAlicuota(com.trascender.habilitaciones.recurso.persistent.RegAlicuota pRegistroAlicuota)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegAlicuota.serialVersionUID, Permiso.Accion.UPDATE)) {
				return (RegAlicuota) this.businessTipoAlicuota.updateTipoAlicuota(pRegistroAlicuota);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(301);
		}
	}

	/**
	 * 
	 * @param pRegistroAlicuota
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteRegistroAlicuota(com.trascender.habilitaciones.recurso.persistent.RegAlicuota pRegistroAlicuota) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegAlicuota.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessTipoAlicuota.deleteTipoAlicuota(pRegistroAlicuota);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(302);
		}
	}

	/**
	 * @param pNombre
	 * @param pTipoObligacion
	 * @param codigo
	 * @param pPeriodicidad
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroRubroSHPS findListaRubros(FiltroRubroSHPS pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegAlicuota.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoAlicuota.findListaRubros(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(303);
		}
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type="remote"
	 */
	public RegAlicuota getRegistroAlicuotaPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, RegAlicuota.serialVersionUID, Permiso.Accion.SELECT)) {
				RegAlicuota locRegAlicuota = (RegAlicuota) this.businessTipoAlicuota.getRegAlicuotaPorId(pId);
				if(locRegAlicuota != null) {
					locRegAlicuota.toString();
				}

				return locRegAlicuota;

			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(304);
		}
	}

	public Rubro getRubroPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Rubro.serialVersionUID, Permiso.Accion.SELECT)) {
				return (Rubro) this.businessTipoAlicuota.getRubroPorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(304);
		}
	}
}