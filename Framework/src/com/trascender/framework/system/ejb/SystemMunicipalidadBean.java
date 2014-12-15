package com.trascender.framework.system.ejb;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.business.interfaces.BusinessCalendarioLocal;
import com.trascender.framework.business.interfaces.BusinessMunicipalidadLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroArea;
import com.trascender.framework.recurso.filtros.FiltroCodigoCiiu;
import com.trascender.framework.recurso.filtros.FiltroDiaFeriado;
import com.trascender.framework.recurso.filtros.FiltroDigestoMunicipal;
import com.trascender.framework.recurso.filtros.FiltroLocalidad;
import com.trascender.framework.recurso.filtros.FiltroMunicipalidad;
import com.trascender.framework.recurso.filtros.FiltroPais;
import com.trascender.framework.recurso.filtros.FiltroProvincia;
import com.trascender.framework.recurso.filtros.FiltroSecretaria;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Calendario.EstadoCalendario;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemMunicipalidad")
public class SystemMunicipalidadBean implements SystemMunicipalidad {

	private static final long serialVersionUID = 1L;

	private long llave = 0;
	@EJB
	private final BusinessMunicipalidadLocal municipalidadLocal = null;

	@EJB
	private BusinessCalendarioLocal busnessCalendario;

	public SystemMunicipalidadBean() {
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
	 * Obtiene los datos de la municipalidad actual Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroMunicipalidad getMunicipalidad(FiltroMunicipalidad pFiltro) throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Municipalidad.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.getMunicipalidad(pFiltro);
			} else
				throw new TrascenderFrameworkException(700);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(300);
		}
	}

	/**
	 * Permite definir los datos de la municipalidad, solo se permite una
	 * municipalidad. Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setMunicipalidad(com.trascender.framework.recurso.persistent.Municipalidad pMunicipalidad) throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Municipalidad.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.municipalidadLocal.updateMunicipalidad(pMunicipalidad);
			} else
				throw new TrascenderFrameworkException(701);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			throw new TrascenderFrameworkException(301);
		}
	}

	// Este metodo no pide llave a proposito, para poder obtener la unformacion
	// de la municipalidad a cualquier usuario.
	public Municipalidad getMunicipalidad() throws Exception {
		return this.municipalidadLocal.getMunicipalidad(new FiltroMunicipalidad()).getListaResultados().get(0);
	}

	/**
	 * Permite agregar un area a la base de datos
	 * 
	 * @param pArea
	 *            �rea que se desea agregar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addArea(com.trascender.framework.recurso.persistent.Area pArea) throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Area.serialVersionUID, Permiso.Accion.INSERT)) {
				this.municipalidadLocal.addArea(pArea);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			throw new TrascenderFrameworkException(302);
		}
	}

	/**
	 * Actualiza los datos de un area
	 * 
	 * @param pArea
	 *            �rea a actualizar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateArea(com.trascender.framework.recurso.persistent.Area pArea) throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Area.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.municipalidadLocal.updateArea(pArea);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(303);
		}
	}

	/**
	 * Permite eliminar un area
	 * 
	 * @param pArea
	 *            �rea a eliminar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void removeArea(com.trascender.framework.recurso.persistent.Area pArea) throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Area.serialVersionUID, Permiso.Accion.DELETE)) {
				pArea.setEstado(Area.Estado.ELIMINADO);
				this.municipalidadLocal.updateArea(pArea);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(304);
		}
	}

	/**
	 * Permite restaurar un area previamente eliminada
	 * 
	 * @param pArea
	 *            area que se desea restaurar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void restoreArea(com.trascender.framework.recurso.persistent.Area pArea) throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Area.serialVersionUID, Permiso.Accion.UPDATE)) {
				pArea.setEstado(Area.Estado.ACTIVO);
				this.municipalidadLocal.updateArea(pArea);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(305);
		}
	}

	/**
	 * Realiza b�squedas de area
	 * 
	 * @param pNombre
	 *            nombre del area (o las primeras letras del mismo
	 * @param pEstado
	 *            estado en que se encuentra el area (ACTIVO, ELIMINADO)
	 *            Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroArea findArea(FiltroArea filtro) throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Area.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findArea(filtro);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(306);
		}
	}
	
	public FiltroArea findAreasSinPermisoSelect(FiltroArea filtro) throws com.trascender.framework.exception.TrascenderException {
		try {
				return this.municipalidadLocal.findArea(filtro);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(306);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	/**
	 * Agrega un pais
	 * 
	 * @param pPais
	 *            pais a agregar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public Pais addPais(com.trascender.framework.recurso.persistent.Pais pPais) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Pais.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.municipalidadLocal.addPais(pPais);
			} else {
				throw new TrascenderFrameworkException(805);
			}

		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(307);
		}
	}

	/**
	 * Realiza b�squedas de pais por nombre
	 * 
	 * @param pPais
	 *            nombre del pais a buscar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroPais findPais(FiltroPais filtro) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Pais.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findPais(filtro);
			} else {
				throw new TrascenderFrameworkException(805);
			}

		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(308);
		}
	}

	/**
	 * Actualiza los datos de un pais
	 * 
	 * @param pPais
	 *            pais a actualizar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updatePais(com.trascender.framework.recurso.persistent.Pais pPais) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Pais.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.municipalidadLocal.updatePais(pPais);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(309);
		}
	}

	/**
	 * Elimina un pais
	 * 
	 * @param pPais
	 *            pais a eliminar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void removePais(com.trascender.framework.recurso.persistent.Pais pPais) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Pais.serialVersionUID, Permiso.Accion.DELETE)) {
				this.municipalidadLocal.removePais(pPais);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(310);
		}
	}

	/**
	 * Permite agregar una localidad
	 * 
	 * @param pLocalidad
	 *            localidad a agregar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addLocalidad(com.trascender.framework.recurso.persistent.Localidad pLocalidad) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Localidad.serialVersionUID, Permiso.Accion.INSERT)) {
				this.municipalidadLocal.addLocalidad(pLocalidad);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(311);
		}
	}

	/**
	 * Actualiza en la base de datos una localidad
	 * 
	 * @param pLocalidad
	 *            localidad a actualizar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateLocalidad(com.trascender.framework.recurso.persistent.Localidad pLocalidad) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (!SecurityMgr.getInstance().getPermiso(this.llave, Localidad.serialVersionUID, Permiso.Accion.UPDATE)) {
				throw new TrascenderFrameworkException(805);
			}
			this.municipalidadLocal.updateLocalidad(pLocalidad);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(312);
		}
	}

	/**
	 * Elimina una localidad
	 * 
	 * @param pLocalidad
	 *            localidad que se desea eliminar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void removeLocalidad(com.trascender.framework.recurso.persistent.Localidad pLocalidad) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (!SecurityMgr.getInstance().getPermiso(this.llave, Localidad.serialVersionUID, Permiso.Accion.DELETE)) {
				throw new TrascenderFrameworkException(805);
			}
			this.municipalidadLocal.removeLocalidad(pLocalidad);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(313);
		}

	}

	/**
	 * Realiza una b�squeda de localidades
	 * 
	 * @param pCodPostal
	 *            c�digo postal de la localidad que se desa emcpmtrar
	 * @param pProvincia
	 *            provincia a la que pertenecen las localidades
	 * @param pPais
	 *            pa�s al que pertenecen las localidades Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroLocalidad findLocalidad(FiltroLocalidad filtro) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (!SecurityMgr.getInstance().getPermiso(this.llave, Localidad.serialVersionUID, Permiso.Accion.SELECT)) {
				throw new TrascenderFrameworkException(805);
			}
			return this.municipalidadLocal.findLocalidad(filtro);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(314);
		}
	}

	/**
	 * Permite agregar una nueva provincia
	 * 
	 * @param pProvincia
	 *            provincia que se desea agregar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public Provincia addProvincia(com.trascender.framework.recurso.persistent.Provincia pProvincia) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (!SecurityMgr.getInstance().getPermiso(this.llave, Provincia.serialVersionUID, Permiso.Accion.INSERT)) {
				throw new TrascenderFrameworkException(805);
			}
			return this.municipalidadLocal.addProvincia(pProvincia);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(315);
		}
	}

	/**
	 * Permite actualizar una provincia
	 * 
	 * @param pProvincia
	 *            provincia a actulizar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateProvincia(com.trascender.framework.recurso.persistent.Provincia pProvincia) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (!SecurityMgr.getInstance().getPermiso(this.llave, Provincia.serialVersionUID, Permiso.Accion.UPDATE)) {
				throw new TrascenderFrameworkException(805);
			}
			this.municipalidadLocal.updateProvincia(pProvincia);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(316);
		}
	}

	/**
	 * Permite eliminar una provincia
	 * 
	 * @param pProvincia
	 *            provincia que se desea eliminar Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void removeProvincia(com.trascender.framework.recurso.persistent.Provincia pProvincia) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (!SecurityMgr.getInstance().getPermiso(this.llave, Provincia.serialVersionUID, Permiso.Accion.DELETE)) {
				throw new TrascenderFrameworkException(805);
			}
			this.municipalidadLocal.removeProvincia(pProvincia);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(317);
		}
	}

	/**
	 * Permite encontrar una provincia
	 * 
	 * @param pProvincia
	 *            primeras letras del nombre de la provincia que se desea
	 *            encontrar
	 * @param pPais
	 *            pais al que pertenece la provincia Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroProvincia findProvincia(FiltroProvincia filtro) throws com.trascender.framework.exception.TrascenderFrameworkException {
		try {
			if (!SecurityMgr.getInstance().getPermiso(this.llave, Provincia.serialVersionUID, Permiso.Accion.SELECT)) {
				throw new TrascenderFrameworkException(805);
			}
			return this.municipalidadLocal.findProvincia(filtro);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(318);
		}
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.Localidad getLocalidadPorId(long pId) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Localidad.serialVersionUID, Permiso.Accion.SELECT)) {
				return municipalidadLocal.getLocalidadPorId(pId);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.Provincia getProvinciaPorId(long pId) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Provincia.serialVersionUID, Permiso.Accion.SELECT)) {
				return municipalidadLocal.getProvinciaPorId(pId);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.Pais getPaisPorId(long pId) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Pais.serialVersionUID, Permiso.Accion.SELECT)) {
				return municipalidadLocal.getPaisPorId(pId);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DiaFeriado getDiaFeriadoPorId(long pId) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DiaFeriado.serialVersionUID, Permiso.Accion.SELECT)) {
				return municipalidadLocal.getDiaFeriadoPorId(pId);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(322);
		}
	}

	/**
	 * Registra un d�a feriado
	 * 
	 * @param pDiaFeriado
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.DiaFeriado addDiaFeriado(com.trascender.framework.recurso.persistent.DiaFeriado pDiaFeriado) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DiaFeriado.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.municipalidadLocal.addDiaFeriado(pDiaFeriado);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(319);
		}
	}

	/**
	 * Actualiza los datos de los dias feriados
	 * 
	 * @param pDiaFeriado
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.DiaFeriado updateDiaFeriado(com.trascender.framework.recurso.persistent.DiaFeriado pDiaFeriado) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DiaFeriado.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.municipalidadLocal.updateDiaFeriado(pDiaFeriado);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(320);
		}
	}

	/**
	 * Elimina un d�a feriado
	 * 
	 * @param pDiaFeriado
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteDiaFeriado(com.trascender.framework.recurso.persistent.DiaFeriado pDiaFeriado) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DiaFeriado.serialVersionUID, Permiso.Accion.DELETE)) {
				this.municipalidadLocal.deleteDiaFeriado(pDiaFeriado);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(321);
		}
	}

	/**
	 * Recupera el listado de d�as feriados por nombre y a�o
	 * 
	 * @param pNombre
	 * @param pAnio
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroDiaFeriado findListaDiasFeriados(FiltroDiaFeriado filtro) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DiaFeriado.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findListadoDiasFeriados(filtro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(322);
		}
	}

	/**
	 * 
	 * @param pDigestoMunicipal
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.DigestoMunicipal addDigestoMunicipal(com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal)
			throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DigestoMunicipal.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.municipalidadLocal.addDigestoMunicipal(pDigestoMunicipal);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(323);
		}
	}

	/**
	 * @param pDigestoMunicipal
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.DigestoMunicipal updateDigestoMunicipal(com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal)
			throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DigestoMunicipal.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.municipalidadLocal.updateDigestoMunicipal(pDigestoMunicipal);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			if (!pDigestoMunicipal.getListaConcordancias().isEmpty()) {
				throw new TrascenderFrameworkException(1328);
			} else {
				throw new TrascenderFrameworkException(324);
			}

		}
	}

	/**
	 * 
	 * @param pDigestoMunicipal
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteDigestoMuncipal(com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DigestoMunicipal.serialVersionUID, Permiso.Accion.DELETE)) {
				if (pDigestoMunicipal.getListaConcordancias().isEmpty()) {
					this.municipalidadLocal.deleteDigestoMuncipal(pDigestoMunicipal);
				} else {
					throw new TrascenderFrameworkException(328);
				}

			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(325);
		}
	}

	/**
	 * 
	 * @param pNombre
	 * @param pTipo
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 * 
	 */
	public FiltroDigestoMunicipal findListaDigestosMunicipales(FiltroDigestoMunicipal pFiltro) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DigestoMunicipal.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findListaDigestosMunicipales(pFiltro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(326);
		}
	}

	/**
	 * 
	 * @param pIdDigestoMunicipal
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.DigestoMunicipal getDigestoMunicipalPorId(long pIdDigestoMunicipal) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DigestoMunicipal.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.getDigestoMunicipalPorId(pIdDigestoMunicipal);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(327);
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type="remote"
	 */
	public Localidad getLocalidadMunicipal() throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Localidad.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.getLocalidadMunicipal();
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(328);
		}
	}

	public FiltroCodigoCiiu findListaCodigosCiiu(FiltroCodigoCiiu filtro) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, CodigoCiiu.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findListaCodigosCiiu(filtro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(604);
		}
	}
	
	public List<AuxIdEntidad> findListaAuxIdCodigoCiiu(String cadena) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, CodigoCiiu.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findListaAuxIdCodigoCiiu(cadena);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(604);
		}
	}

	public Area getAreaById(Long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, Area.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.getAreaById(pId);
			}
			else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(306);
		}
	}

	public CodigoCiiu getCodigoCiiuById(Long pId) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, CodigoCiiu.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.getCodigoCiiuById(pId);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(604);
		}
	}

	public List<SeccionCiiu> findListaSeccionCiiu(String pCodigo, String pNombre) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, CodigoCiiu.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findListaSeccionCiiu(pCodigo, pNombre);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(606);
		}
	}

	public List<GrupoCiiu> findListaGrupoCiiu(String pCodigo, String pNombre, SeccionCiiu pSeccion) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, CodigoCiiu.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findListaGrupoCiiu(pCodigo, pNombre, pSeccion);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(607);
		}
	}

	public DigestoMunicipal findDigestoMunicipalPorNumero(Integer pNumero) throws TrascenderException, RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @ejb.interface-method view-type="remote"
	 * @param pNodo
	 * @return
	 * @throws TrascenderException
	 */
	public List<DigestoMunicipal> findListaDigestoMunicipalPorConcordancia(com.trascender.framework.recurso.persistent.DigestoMunicipal pNodo) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, DigestoMunicipal.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.municipalidadLocal.findListaDigestoMunicipalPorConcordancia(pNodo);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(325);
		}
	}

	public Calendario addCalendario(Calendario pCalendario) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.INSERT)) {
				return this.busnessCalendario.addCalendario(pCalendario);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(850);
		} catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public Calendario updateCalendario(Calendario pCalendario) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.UPDATE)) {
				return this.busnessCalendario.updateCalendario(pCalendario);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(850);
		} catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public Collection<Calendario> findListaCalendarios(Long pIdTipoObligacion, Integer pAnio, EstadoCalendario pEstado) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.UPDATE)) {
				return this.busnessCalendario.findListaCalendarios(pIdTipoObligacion, pAnio, pEstado);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(852);
		} catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public Secretaria addSecretaria(Secretaria pSecretaria) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Secretaria.serialVersionUID, Accion.INSERT)) {
				return this.municipalidadLocal.addSecretaria(pSecretaria);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(852);
		} catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public void updateSecretaria(Secretaria pSecretaria) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Secretaria.serialVersionUID, Accion.UPDATE)) {
				this.municipalidadLocal.updateSecretaria(pSecretaria);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(852);
		} catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public void deleteSecretaria(Secretaria pSecretaria) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Secretaria.serialVersionUID, Accion.DELETE)) {
				this.municipalidadLocal.deleteSecretaria(pSecretaria);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(501);	} catch (Exception locE2) {
				locE2.printStackTrace();
				throw new TrascenderFrameworkException(999);
			}
	}

	public FiltroSecretaria findListaSecretarias(FiltroSecretaria pFiltro) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Secretaria.serialVersionUID, Accion.SELECT)) {
				return this.municipalidadLocal.findListaSecretarias(pFiltro);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException locE) {
			// locE.printStackTrace();
			// throw new TrascenderFrameworkException(852);
			locE.printStackTrace();
			throw locE;
		} catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public Secretaria getSecretariaPorId(long pIdSecretaria) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Secretaria.serialVersionUID, Accion.SELECT)) {
				return this.municipalidadLocal.getSecretariaPorId(pIdSecretaria);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(852);
		} catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

}
