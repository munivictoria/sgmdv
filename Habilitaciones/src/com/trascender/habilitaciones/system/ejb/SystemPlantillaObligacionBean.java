
package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessPlantillaObligacionLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.system.interfaces.SystemPlantillaObligaciones;

@Stateful(name = "ejb/SystemPlantillaObligaciones")
public class SystemPlantillaObligacionBean implements SystemPlantillaObligaciones {

	@EJB
	private BusinessPlantillaObligacionLocal businessPlantillaObligacionHome;

	private long llave;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2899122569071695198L;

	public SystemPlantillaObligacionBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	/**
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	/**
	 * Agrega una plantilla de obligación
	 * 
	 * @param pObligacion
	 *            Plantilla de obligacion a agregar
	 * @ejb.interface-method view-type = "remote"
	 */
	public PlantillaObligacion addPlantillaObligacion(com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion pPlantillaObligacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaObligacion.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessPlantillaObligacionHome.addPlantillaObligacion(pPlantillaObligacion);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(390);
		}
	}

	/**
	 * Actualiza los datos de una plantilla de obligaciones
	 * 
	 * @param pPlantillaObligacion
	 *            Plantilla de obligacion a actualizar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public PlantillaObligacion updatePlantillaObligacion(com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion pPlantillaObligacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaObligacion.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessPlantillaObligacionHome.updatePlantillaObligacion(pPlantillaObligacion);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(391);
		}
	}

	/**
	 * Recupera un listado de Plantillas de obligaciones
	 * 
	 * @param pNombre
	 *            primeras letras del nombre de la plantilla de obligacion
	 * @return Listado de plantilla de obligaciones
	 * 
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroPlantillaObligacion findListaPlantillaObligaciones(FiltroPlantillaObligacion pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaObligacion.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessPlantillaObligacionHome.findListaPlantillaObligacion(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(392);
		}
	}

	public List<PlantillaObligacion> findPlantillasObligacionTasaMenor() {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaDocumentoTasaMenor.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessPlantillaObligacionHome.findPlantillasObligacionTasaMenor();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(HabilitacionesException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Elimina físicamente una plantilla de obligaciones
	 * 
	 * @param pPlantillaObligacion
	 *            plantilla de obligaciones a eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deletePlantillaObligacion(com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion pPlantillaObligacion) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaObligacion.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessPlantillaObligacionHome.deletePlantillaObligacion(pPlantillaObligacion);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(393);
		}
	}

	/**
	 * Genera un arbol de obligaciones para una persona a partir de una plantilla de obligaciones
	 * 
	 * @param pPersona
	 *            Persona a la cual se generará la obligacion
	 * @param pPlantillaObligacion
	 *            Plantilla de obligaciones a partir de la cual se generará el arbol
	 * @return com.trascender.recurso.persistent.Obligacion obligación generada por el método
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.Obligacion generarArbol(com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion pPlantillaObligacion)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Obligacion.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessPlantillaObligacionHome.generarArbolObligacion(pPlantillaObligacion);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(394);
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	public PlantillaObligacion getPlantillaObligacion(Long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaObligacion.serialVersionUID, Accion.SELECT)) {
				return this.businessPlantillaObligacionHome.getPlantillaObligacion(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;

		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(394);
		}
	}

	/**
	 * Recupera una plantilla obligacion segun la obligacion pasada por parametro si no la encuentra devuelve alguna otra para el mismo tipo de la obligacion.
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 */
	public PlantillaObligacion getPlantillaObligacionFromObligacion(Obligacion pObligacion) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PlantillaObligacion.serialVersionUID, Accion.SELECT)) {
				return this.businessPlantillaObligacionHome.getPlantillaObligacionFromObligacion(pObligacion);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean esTipoObligacionTasaMenor(TipoObligacion pTipo) throws TrascenderException {
		return this.businessPlantillaObligacionHome.esTipoObligacionTasaMenor(pTipo);
	}

}
