
package com.trascender.catastro.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import com.trascender.catastro.business.interfaces.BusinessRegistroGeograficoLocal;
import com.trascender.catastro.business.interfaces.BusinessZonificacionLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroZona;
import com.trascender.catastro.recurso.filtros.FiltroZonificacion;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.catastro.system.interfaces.SystemAdministracionZonificacion;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemAdministracionDDJJ" display-name="Name for SystemAdministracionDDJJ" description="Description for SystemAdministracionDDJJ"
 *           jndi-name="ejb/SystemAdministracionDDJJ" type="Stateless" view-type="remote"
 */
@Stateful(name = "ejb/SystemAdministracionZonificacion")
public class SystemAdministracionZonificacionBean implements SystemAdministracionZonificacion {

	private long llave;

	@EJB
	private BusinessZonificacionLocal businessZonificacion;

	@EJB
	private BusinessRegistroGeograficoLocal businessRegistroGeografico;

	private static final long serialVersionUID = -7072030166506978478L;

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @throws NamingException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
	}

	/**
	 * Recupera un listado de zonas
	 * 
	 * @param pNombre
	 *            primeras letras del nombre de las zonas que se desean recuperar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroZona findListaZonas(FiltroZona pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zona.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessZonificacion.findListaZonas(pFiltro);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(355);
		}
	}

	public FiltroZonificacion findListaZonificacion(FiltroZonificacion pFiltro) throws TrascenderException, RemoteException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zonificacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessZonificacion.findListaZonificacion(pFiltro);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(96);
		}
	}

	// ---------------Métodos de ABM
	public void addZonificacion(Zonificacion pZonificacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zonificacion.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessZonificacion.addZonificacion(pZonificacion);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(97);
		}
	}

	public void updateZonificacion(Zonificacion pZonificacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zonificacion.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessZonificacion.updateZonificacion(pZonificacion);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(98);
		}
	}

	public void removeZonificacion(Zonificacion pZonificacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zonificacion.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessZonificacion.removeZonificacion(pZonificacion);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(99);
		}
	}

	public long getLlave() {
		return llave;
	}

	public void setLlave(long llave) {
		this.llave = llave;
	}

	/**
	 * Agrega una zona
	 * 
	 * @param pZona
	 *            zona a agregar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Zona addZona(com.trascender.catastro.recurso.persistent.Zona pZona) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zona.serialVersionUID, Permiso.Accion.INSERT)) {
				pZona.setEstado(Zona.Estado.ACTIVO);
				return this.businessRegistroGeografico.addZona(pZona);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(352);
		}
	}

	/**
	 * Actualiza una zona
	 * 
	 * @param pZona
	 *            zona que se desea actualizar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Zona updateZona(com.trascender.catastro.recurso.persistent.Zona pZona) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zona.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessRegistroGeografico.updateZona(pZona);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(353);
		}
	}

	/**
	 * Elimina una zona
	 * 
	 * @param pZona
	 *            zona a eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteZona(com.trascender.catastro.recurso.persistent.Zona pZona) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zona.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessRegistroGeografico.deleteZona(pZona);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(354);
		}
	}

	/**
	 * 
	 * @param pParcela
	 * @return
	 * @throws TrascenderException
	 */
	public List<Zona> getListaZonasFromParcela(Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zona.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessZonificacion.getZonasFromParcela(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(368);
		}
	}

	public List<Zona> getListaZonasFromParcelaSinLimitar(Parcela pParcela) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zona.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessZonificacion.getZonasFromParcelaSinLimitar(pParcela);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(368);
		}
	}

	public Zonificacion getZonificacionPorId(Long pIdZonificacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zonificacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessZonificacion.getZonificacionPorId(pIdZonificacion);
			} else {
				throw new CatastroException(791);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatastroException(369);
		}
	}

	public Zona getZonaById(Long pIdZona) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Zonificacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessZonificacion.getZonaById(pIdZona);
			} else {
				throw new CatastroException(791);
			}

		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;

		}
	}
}
