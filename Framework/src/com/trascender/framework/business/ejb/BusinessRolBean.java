
package com.trascender.framework.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.business.interfaces.BusinessRolLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroRol;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

/**
 * @ejb.bean name="BusinessRol" display-name="Name for BusinessRol" description="Description for BusinessRol" jndi-name="ejb/BusinessRol" type="Stateless"
 *           view-type="local"
 */

@Stateless(name = "ejb/BusinessRol")
public class BusinessRolBean implements BusinessRolLocal {
	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessRolBean.serialVersionUID);
		grupo.setNombre(BusinessRolBean.NOMBRE);

		Recurso rol = new Recurso();
		rol.setIdRecurso(Rol.serialVersionUID);
		rol.setNombre("Rol");
		rol.setAtributosConsultables("Nombre", "nombre");
		rol.setClase(Rol.class);
		grupo.getListaRecursos().add(rol);

		Recurso recurso = new Recurso();
		recurso.setIdRecurso(Recurso.serialVersionUID);
		recurso.setNombre("Recurso");
		recurso.setClase(Recurso.class);
		grupo.getListaRecursos().add(recurso);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	private static final long serialVersionUID = 4069324043147574089L;
	private static final String NOMBRE = "FRM|Adm. de Roles";

	@PersistenceContext(name = "vipians")
	private EntityManager entity;

	public BusinessRolBean() {
		super();
	}

	public void setSessionContext(SessionContext pCtx) throws EJBException, RemoteException {

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
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void addRol(com.trascender.framework.recurso.persistent.Rol pRol) throws java.lang.Exception {
		this.validarRol(pRol);
		pRol.setEstado(Rol.Estado.ACTIVO);
		pRol.setDesde(Calendar.getInstance().getTime());
		this.filterPermisos(pRol);
		for(Permiso cadaPermiso : pRol.getListaPermisos()) {
			cadaPermiso.setRol(pRol);
		}
		TrascenderEnverListener.setValoresEnAuditoriaBean(pRol);
		entity.persist(pRol);
		entity.flush();
	}

	private void validarRol(Rol pRol) throws TrascenderException {
		Criterio locCriterio = Criterio.getInstance(entity, Rol.class).add(Restriccion.LIKE("nombre", pRol.getNombre(), false)).add(Restriccion.DISTINTO("idRol", pRol.getIdRol()))
				.setProyeccion(Proyeccion.COUNT());
		Long locCantidad = locCriterio.uniqueResult();
		if(locCantidad > 0) {
			throw new TrascenderFrameworkException(40);
		}
	}

	/**
	 * Elimina todos los permisos de un rol que no posean ning�n tipo de acceso excepto auditor�a
	 * 
	 * @param pRol
	 *            rol a modificar
	 */
	private java.util.Collection<Permiso> filterPermisos(Rol pRol) {
		Collection<Permiso> locListaPermisosEliminar = new HashSet<Permiso>();

		for(Permiso per : pRol.getListaPermisos()) {
			if(!(per.isInsert() || per.isDelete() || per.isSelect() || per.isUpdate())) {
				locListaPermisosEliminar.add(per);
			}
		}
		pRol.getListaPermisos().removeAll(locListaPermisosEliminar);
		return locListaPermisosEliminar;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateRol(com.trascender.framework.recurso.persistent.Rol pRol) throws java.lang.Exception {
		validarRol(pRol);
		Collection<Permiso> locListaPermisos = this.filterPermisos(pRol);
		for(Permiso per : locListaPermisos) {
			if(per.getIdPermiso() != -1) {
				entity.remove(entity.merge(per));
			}
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pRol);

		entity.merge(pRol);

		entity.flush();
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroRol findRol(FiltroRol filtro) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Rol.class).add(Restriccion.ILIKE("nombre", filtro.getNombre())).add(Restriccion.MAYOR("desde", filtro.getDesde()))
				.add(Restriccion.MENOR("hasta", filtro.getHasta())).add(Restriccion.IGUAL("estado", filtro.getEstado() == null ? Rol.Estado.ACTIVO : filtro.getEstado()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Rol.serialVersionUID, "idRol", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void removePermiso(com.trascender.framework.recurso.persistent.Permiso pPermiso) throws java.lang.Exception {
		pPermiso = entity.merge(pPermiso);
		entity.remove(pPermiso);
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.Set getListaPermisos(com.trascender.framework.recurso.persistent.Rol pRol) {
		Rol locRol = entity.find(Rol.class, pRol.getIdRol());
		return locRol.getListaPermisos();
	}

	/**
	 * Recupera una lista de permisos del rol en conjunto con todos los permisos que a�n no han sido asignados
	 * 
	 * @param pRol
	 *            rol por el cual se desea filtrar
	 * @return lista de permisos
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List getListaPermisosPorRol(com.trascender.framework.recurso.persistent.Rol pRol) throws TrascenderException {
		Map<Long, Permiso> locListaPermisos = new HashMap<Long, Permiso>();
		if(pRol != null && pRol.getIdRol() != -1) {
			Rol locRol = entity.find(Rol.class, pRol.getIdRol());
			for(Permiso locPermiso : locRol.getListaPermisos()) {
				locListaPermisos.put(locPermiso.getIdRecurso(), locPermiso);
			}
		}
		List<Permiso> locListaRetorno = new ArrayList<Permiso>();
		for(Grupo locGrupo : SecurityMgr.getInstance().getListaGrupos()) {
			for(Recurso locRecurso : locGrupo.getListaRecursos()) {
				if(locListaPermisos.containsKey(locRecurso.getIdRecurso())) {
					locListaRetorno.add(locListaPermisos.get(locRecurso.getIdRecurso()));
				} else {
					Permiso per = new Permiso(locRecurso);
					per.setRol(pRol);
					locListaRetorno.add(per);
				}
			}
		}
		return locListaRetorno;
	}

	public Rol getRolById(long pId) throws Exception {
		Rol locRol = this.entity.find(Rol.class, pId);
		locRol.toString();
		for(Permiso cadaPermiso : locRol.getListaPermisos()) {
			cadaPermiso.toString();
		}

		if(locRol != null) {
			locRol.getListaLogsAuditoria().size();
		}

		return locRol;
	}
}
