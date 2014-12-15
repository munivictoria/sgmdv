package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import org.hibernate.TransactionException;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessTipoTasaLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlan;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroConstante;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrilla;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoTasa;
import com.trascender.habilitaciones.recurso.persistent.PermisoTipoTasa;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAutomotor;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroCementerio;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroDeuda;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroDinamico;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroOSP;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroObra;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroPFO;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroParcelario;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroPersona;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroSHPS;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroTGI;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroTipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.habilitaciones.system.interfaces.SystemTipoTasa;

@Stateful(name = "ejb/SystemTipoTasa")
public class SystemTipoTasaBean implements SystemTipoTasa {

	private long llave;

	@EJB
	private BusinessTipoTasaLocal businessTipoTasa;

	@EJB
	private BusinessObligacionLocal businessObligacion;

	private static final long serialVersionUID = 4462434268768696405L;

	public SystemTipoTasaBean() {
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
	 * Agrega una constante a los tipos de parámetros para poder ser utilizada
	 * en las fórmulas
	 * 
	 * @param pTipoParametroConstante
	 *            Tipo de parámetro constante a agregar
	 * @return TipoParametroConstante actualizado
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante addTipoParametroConstante(
			com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante pTipoParametroConstante) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroConstante.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessTipoTasa.addTipoParametroConstante(pTipoParametroConstante);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(500);
		}

	}

	/**
	 * Agrega un tipo de parámetro de grupos de zonas
	 * 
	 * @param pTipoParametroGrupoZona
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona addTipoParametroGrupoZona(
			com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona pTipoParametroGrupoZona) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrupoZona.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessTipoTasa.addTipoParametroGrupoZona(pTipoParametroGrupoZona);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(501);
		}
	}

	/**
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List<?> getListaTiposParametrosParcelarios() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroParcelario.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosParcelarios();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(502);
		}
	}

	/**
	 * Recupera un listado de los parámetos constantes
	 * 
	 * @param pNombre
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroTipoParametroConstante findListaParametrosConstantes(FiltroTipoParametroConstante pFiltro) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroConstante.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.findListaParametrosConstantes(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(503);
		}
	}

	/**
	 * 
	 * @param pNombre
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroTipoParametroGrupoZona findListaTipoParametroGrupoZona(FiltroTipoParametroGrupoZona pFiltro) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrupoZona.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.findListaTipoParametroGrupoZona(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(504);
		}
	}

	/**
	 * Recupera el listado de parámetros de obra
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List<?> getListaTiposParametrosObra() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroObra.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosObra();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(505);
		}
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void validarFormula(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa) throws TrascenderException {
		// NO REQUIERE PERMISOS DE SEGURIDAD PORQUE EN REALIDAD NO HACE MÁS QUE
		// VALIDAR
		try {
			this.businessTipoTasa.validarFormula(pTipoTasa);
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(506);
		}
	}

	/**
	 * Agrega un tipo de tasa
	 * 
	 * @param pTipoTasa
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoTasa addTipoTasa(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.INSERT)) {

				// TipoTasa
				// locTipoTasa=this.businessTipoTasa.getTipoTasa(pTipoTasa.getPeriodicidad(),
				// pTipoTasa.getTipoObligacion(), Estado.EN_ESPERA,
				// pTipoTasa.getPeriodicidadCuotas(),
				// pTipoTasa.getCantidadCuotas());
				// if (locTipoTasa!=null){
				// //singnifica que tengo un tipo de tasa en espera para
				// reemplazar
				// PORQUE LA BORRAS!!??? Y PORQUE EN EL SYSTEM???!! QUIEN
				// SOS!?!?!?!?!?
				// this.businessTipoTasa.deleteFisicamenteTipoTasa(locTipoTasa);
				// return this.businessTipoTasa.addTipoTasa(pTipoTasa);
				// }
				// else{
				return this.businessTipoTasa.addTipoTasa(pTipoTasa);
				// }
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(507);
		}
	}

	/**
	 * Recupera un listado de tipos de tasa
	 * 
	 * @param pNombre
	 *            Nombre del tipo de tasa
	 * @param pTipoObligacion
	 *            tipo de obligacion a la que pertenece
	 * @param pPeriodicidad
	 *            periodicidad del tipo de tasa
	 * @param pEstado
	 *            estado en que se encuentra el tipo de tasa
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroTipoTasa findListaTiposTasa(FiltroTipoTasa pFiltro) throws TrascenderException {
		try {
			System.out.println("Llave ingresada= " + this.llave);
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.findListaTipoTasa(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(508);
		}
	}

	/**
	 * Actualiza los datos de un tipo de tasa
	 * 
	 * @param pTipoTasa
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoTasa updateTipoTasa(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessTipoTasa.updateTipoTasa(pTipoTasa);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(509);
		}
	}

	/**
	 * Recupera un tipo de tasa con todos los datos asociados
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoTasa getTipoTasaPorId(long pId) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getTipoTasaPorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(510);
		}
	}

	/**
	 * Recupera el listado de tipos de parámetros de personas
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List<?> getListaTiposParametrosPersonas() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroPersona.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosPersonas();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(511);
		}
	}

	/**
	 * Recupera el listado de parámetros de alicuota
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List<?> getListaTiposParametrosOSP() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroOSP.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosOSP();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(512);
		}

	}

	/**
	 * Actualiza los datos de un tipo de parámetro constante
	 * 
	 * @param pTipoParametroConstante
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante updateTipoParametroConstante(
			com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante pTipoParametroConstante) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroConstante.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessTipoTasa.updateTipoParametroConstante(pTipoParametroConstante);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(513);
		}
	}

	/**
	 * Actualiza los datos de un tipo de parámetro de grupos de zonas
	 * 
	 * @param pTipoParametroGrupoZona
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona updateTipoParametroGrupoZona(
			com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona pTipoParametroGrupoZona) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrupoZona.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessTipoTasa.updateTipoParametroGrupoZona(pTipoParametroGrupoZona);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(514);
		}
	}

	/**
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List<?> getListaTiposParametrosSHPS() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroSHPS.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosSHPS();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(515);
		}
	}

	public List<TipoParametroAlicuota> getListaParametrosAlicuotaSHPS() throws TrascenderException {
		return businessTipoTasa.getListaParametrosAlicuotaSHPS();
	}

	/**
	 * Elimina un tipo de parámetro constante
	 * 
	 * @param pTipoParametroConstante
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteTipoParametroConstante(com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante pTipoParametroConstante) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroConstante.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessTipoTasa.deleteTipoParametro(pTipoParametroConstante);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(516);
		}
	}

	/**
	 * Elimina un tipo de parámetro de grupo de zona
	 * 
	 * @param pTipoParametroGrupoZona
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteTipoParametroGrupoZona(com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona pTipoParametroGrupoZona) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrupoZona.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessTipoTasa.deleteTipoParametro(pTipoParametroGrupoZona);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(517);
		}
	}

	/**
	 * Recupera el tipo de parámetro de grupo de zonas por id
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona getTipoParametroGrupoZonaPorId(long pId) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrupoZona.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getTipoParametroGrupoZonaPorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(518);
		}
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante getTipoParametroConstantePorId(long pId) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroConstante.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getTipoParametroConstantePorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(519);
		}
	}

	/**
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List<?> getListaTiposParametrosTGI() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroTGI.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosTGI();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(520);
		}
	}

	/**
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List<?> getListaTiposParametrosPFO() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroPFO.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosPFO();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(521);
		}
	}

	public List<TipoParametroDinamico> getListaParametrosDinamicos(TipoObligacion pTipo) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroDinamico.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosDinamicos(pTipo);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(521);
		}
	}

	/**
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List<?> getListaTiposParametrosVencimiento() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroVencimiento.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosVencimiento();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(522);
		}
	}

	public java.util.List<TipoParametroAlicuota> getListaTiposParametrosCementerio() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroCementerio.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosCementerio();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(532);
		}
	}

	public java.util.List<?> getListaTiposParametrosParcelaCementerio() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroParcelaCementerio.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosParcelaCementerio();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(532);
		}
	}

	public java.util.List<?> getListaTiposParametrosTipoSepultura() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroTipoSepultura.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosTipoSepultura();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(532);
		}
	}
	
	public java.util.List getListaTiposParametrosAutomotor() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroAutomotor.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosAutomotor();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(533);
		}
	}
	
	public java.util.List getListaTiposParametrosVehiculo() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroVehiculo.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosVehiculo();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(533);
		}
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @param pComentario
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void activarTipoTasa(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa, String pComentario) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, PermisoTipoTasa.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessTipoTasa.activarTipoTasa(pTipoTasa, pComentario, this.llave);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(523);
		}
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @param pListaValores
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public Double calcularTasa(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa, java.util.Map pListaValores) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.calcularTasa(pTipoTasa, pListaValores);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(524);
		}
	}

	public Double calcularTasaAlicuota(TipoTasa pTipoTasa, Map pListaValores) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.calcularTasaAlicuota(pTipoTasa, pListaValores);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(524);
		}
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @param valores
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.Map<String, Double> calcularModificadoresSobreTasa(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa, java.util.Map valores)
			throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.calcularModificadoresSobreTasa(pTipoTasa, valores);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(525);
		}
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @param valores
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.Map<java.util.Date, Double> calcularVencimientos(java.util.Date pFechaLiquidacion, java.util.Date pFechaCobro,
			com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa, java.util.Map pValores) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
				System.out.println("Fecha de liquidacion = " + locDateFormat.format(pFechaLiquidacion.getTime()));
				System.out.println("fecha de cobro =" + locDateFormat.format(pFechaCobro.getTime()));

				return this.businessTipoTasa.calcularVencimientos(pFechaLiquidacion, pFechaCobro, pTipoTasa, pValores);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(526);
		}
	}

	/**
	 * 
	 * @param pTipoTasa
	 * @param pValores
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.Map<String, Double> calcularModificadoresSobreSubtotal(com.trascender.habilitaciones.recurso.persistent.TipoTasa pTipoTasa, java.util.Map pValores)
			throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.calcularModificadoresSobreSubtotal(pTipoTasa, pValores);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(527);
		}
	}

	/**
	 * Calcula el interés y el recargo de un tipo tasa
	 * 
	 * @param pFechaLiquidacion
	 * @param pFechaCobro
	 * @param pTipoTasa
	 * @param pValores
	 * @return
	 * @throws TrascenderException
	 */
	public Map<String, Double> calcularIntereses(Date pFechaLiquidacion, Date pFechaCobro, TipoTasa pTipoTasa, Map<String, Double> pValores) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.calcularIntereses(pFechaLiquidacion, pFechaCobro, pTipoTasa, pValores);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(529);
		}
	}

	/**
	 * 
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	public List<TipoObligacion> findListaTipoObligacion(String pNombre, Boolean pEsTipoTasaMenor) throws TrascenderException {

		try {
			if (SecurityMgr.getInstance().getUsuario(llave) != null) {
				return businessObligacion.findListaTipoObligacion(pNombre, pEsTipoTasaMenor);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(392);

		}
	}

	public void deleteTipoTasa(TipoTasa pTipoTasa) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoTasa.serialVersionUID, Accion.DELETE)) {
				this.businessTipoTasa.deleteTipoTasa(pTipoTasa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPlan(Plan pPlan) throws TrascenderException {

		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Plan.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessTipoTasa.addPlan(pPlan);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(392);

		}
	}

	public void updatePlan(Plan pPlan) throws TrascenderException {

		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Plan.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessTipoTasa.updatePlan(pPlan);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(392);

		}
	}

	public void deletePlan(Plan pPlan) throws TrascenderException {

		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Plan.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessTipoTasa.deletePlan(pPlan);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(392);

		}
	}

	public FiltroPlan findListaPlan(FiltroPlan pFiltro) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Plan.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.findListaPlan(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(392);

		}
	}

	public Plan getPlanPorId(long pId) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, Plan.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getPlanPorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(534);
		}
	}
	
	public java.util.List getListaTiposParametrosDeuda() throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroDeuda.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getListaParametrosDeuda();
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(535);
		}
	}
	
	public List<TipoParametroAlicuota> getListaParametrosAlicuotaOSP() throws TrascenderException {
		return businessTipoTasa.getListaParametrosAlicuotaOSP();
	}

	public void addTipoParametroGrilla(TipoParametroGrilla pTipoParametro)
			throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrilla.serialVersionUID, Permiso.Accion.INSERT)) {
				businessTipoTasa.addTipoParametroGrilla(pTipoParametro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(535);
		}		
	}

	public void updateTipoParametroGrilla(TipoParametroGrilla pTipoParametro)
			throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrilla.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessTipoTasa.updateTipoParametroGrilla(pTipoParametro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(535);
		}		
	}
	
	public void deleteTipoParametroGrilla(TipoParametroGrilla pTipoParametro) throws TrascenderException{
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrilla.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessTipoTasa.deleteTipoParametroGrilla(pTipoParametro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(392);

		}
	}

	public FiltroTipoParametroGrilla findListaTipoParametroGrilla(
			FiltroTipoParametroGrilla pFiltro) throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrilla.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.findListaTipoParametroGrilla(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(535);
		}
	}

	public TipoParametroGrilla getTipoParametroGrillaPorId(Long pId)
			throws TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoParametroGrilla.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessTipoTasa.getTipoParametroGrillaPorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch (TrascenderException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(535);
		}
	}
}
