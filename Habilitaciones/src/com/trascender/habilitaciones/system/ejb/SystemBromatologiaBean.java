
package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoSHPSLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroLibretaSanitaria;
import com.trascender.habilitaciones.recurso.filtros.FiltroLocalComercial;
import com.trascender.habilitaciones.recurso.filtros.FiltroTransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.ClausuraSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionVehicular;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.system.interfaces.SystemBromatologia;

@Stateful(name = "ejb/SystemBromatologia")
public class SystemBromatologiaBean implements SystemBromatologia {

	@EJB
	private BusinessDocumentoSHPSLocal businessDocumentoSHPS;

	private long llave;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6424010978999956960L;

	public SystemBromatologiaBean() {
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
	 * @param pLibretaSanitaria
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addLibretaSanitaria(com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria pLibretaSanitaria) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LibretaSanitaria.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessDocumentoSHPS.addLibretaSanitaria(pLibretaSanitaria);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(320);
		}
	}

	/**
	 * 
	 * @param pLibretaSanitaria
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateLibretaSanitaria(com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria pLibretaSanitaria) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LibretaSanitaria.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessDocumentoSHPS.updateLibretaSanitaria(pLibretaSanitaria);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(321);
		}
	}

	/**
	 * 
	 * @param pPersona
	 * @return
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroLibretaSanitaria findListaLibretasSanitarias(FiltroLibretaSanitaria pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LibretaSanitaria.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.findListaLibretasSanitarias(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(322);
		}

	}

	/**
	 * 
	 * @param pLibretaSanitaria
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteLibretaSanitaria(com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria pLibretaSanitaria) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LibretaSanitaria.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessDocumentoSHPS.deleteLibretaSanitaria(pLibretaSanitaria);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(323);
		}
	}

	/**
	 * Agrega una inspeccion
	 * 
	 * @param pInspeccion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addInspeccion(InspeccionSHPS pInspeccion) throws TrascenderException {
		try {
			if(pInspeccion instanceof InspeccionComercial) {
				if(SecurityMgr.getInstance().getPermiso(this.llave, InspeccionComercial.serialVersionUID, Permiso.Accion.INSERT)) {
					this.businessDocumentoSHPS.addInspeccionComercial((InspeccionComercial) pInspeccion);

				} else {
					throw new HabilitacionesException(799);
				}
			} else {
				if(SecurityMgr.getInstance().getPermiso(this.llave, InspeccionVehicular.serialVersionUID, Permiso.Accion.INSERT)) {
					this.businessDocumentoSHPS.addInspeccionVehicular((InspeccionVehicular) pInspeccion);
				} else {
					throw new HabilitacionesException(799);
				}
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(324);
		}
	}

	/**
	 * Actualiza una inspeccion
	 * 
	 * @param pInspeccion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateInspeccion(InspeccionSHPS pInspeccion) throws TrascenderException {
		try {
			if(pInspeccion instanceof InspeccionComercial) {
				if(SecurityMgr.getInstance().getPermiso(this.llave, InspeccionComercial.serialVersionUID, Permiso.Accion.UPDATE)) {
					this.businessDocumentoSHPS.updateInspeccionComercial((InspeccionComercial) pInspeccion);
				} else {
					throw new HabilitacionesException(799);
				}
			} else {
				if(SecurityMgr.getInstance().getPermiso(this.llave, InspeccionVehicular.serialVersionUID, Permiso.Accion.UPDATE)) {
					this.businessDocumentoSHPS.updateInspeccionVehicular((InspeccionVehicular) pInspeccion);
				} else {
					throw new HabilitacionesException(799);
				}
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(325);
		}

	}

	/**
	 * 
	 * @param pInspeccion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteInspeccion(InspeccionSHPS pInspeccion) throws TrascenderException {
		try {
			if(pInspeccion instanceof InspeccionComercial) {
				if(SecurityMgr.getInstance().getPermiso(this.llave, InspeccionComercial.serialVersionUID, Permiso.Accion.DELETE)) {
					this.businessDocumentoSHPS.deleteInspeccionComercial((InspeccionComercial) pInspeccion);
				} else {
					throw new HabilitacionesException(799);
				}
			} else {
				if(SecurityMgr.getInstance().getPermiso(this.llave, InspeccionVehicular.serialVersionUID, Permiso.Accion.DELETE)) {
					this.businessDocumentoSHPS.deleteInspeccionVehicular((InspeccionVehicular) pInspeccion);
				} else {
					throw new HabilitacionesException(799);
				}
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(326);
		}
	}

	/**
	 * Recupera un listado de inspecciones vehiculares
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaInspeccionesVehiculares(Date pFechaDesde, Date pFechaHasta, TransporteVehicular pTransporteVehicular, InspeccionSHPS.Estado pEstado) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, InspeccionVehicular.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.findListaInspeccionesVehiculares(pFechaDesde, pFechaHasta, pTransporteVehicular, pEstado);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(328);
		}
	}

	/**
	 * Recupera una lista de inspecciones comerciales
	 * 
	 * @return
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pLocalComercial
	 * @param pEstado
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaInspeccionesComerciales(Date pFechaDesde, Date pFechaHasta, LocalComercial pLocalComercial, InspeccionSHPS.Estado pEstado) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, InspeccionComercial.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.findListaInspeccionesComerciales(pFechaDesde, pFechaHasta, pLocalComercial, pEstado);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(328);
		}
	}

	/**
	 * 
	 * @param pLocalComercial
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addLocalComercial(LocalComercial pLocalComercial) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LocalComercial.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessDocumentoSHPS.addLocalComercial(pLocalComercial);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(329);
		}
	}

	/**
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateLocalComercial(LocalComercial pLocalComercial) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LocalComercial.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessDocumentoSHPS.updateLocalComercial(pLocalComercial);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(330);
		}
	}

	/**
	 * 
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteLocalComercial(LocalComercial pLocalComercial) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LocalComercial.serialVersionUID, Permiso.Accion.DELETE)) {
				pLocalComercial.setFechaBaja(Calendar.getInstance().getTime());
				this.businessDocumentoSHPS.deleteLocalComercial(pLocalComercial);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(331);
		}
	}

	public FiltroLocalComercial findListaLocalesComerciales(FiltroLocalComercial pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LocalComercial.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.findListaLocalesComerciales(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(332);

		}
	}

	/**
	 * 
	 * @param pTransporteVehicular
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addTransporteVehicular(TransporteVehicular pTransporteVehicular) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TransporteVehicular.serialVersionUID, Permiso.Accion.INSERT)) {
				this.businessDocumentoSHPS.addTransporteVehicular(pTransporteVehicular);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(333);
		}
	}

	/**
	 * 
	 * @param pTransporteVehicular
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateTranporteVehicular(TransporteVehicular pTransporteVehicular) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TransporteVehicular.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessDocumentoSHPS.updateTransporteVehicular(pTransporteVehicular);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(334);
		}
	}

	/**
	 * 
	 * @param pTransporteVehicular
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteTransporteVehicular(TransporteVehicular pTransporteVehicular) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TransporteVehicular.serialVersionUID, Permiso.Accion.DELETE)) {
				this.businessDocumentoSHPS.deleteTransporteVehicular(pTransporteVehicular);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(335);
		}
	}

	/**
	 * Crea un nuevo transporte vehicular para el mismo vehículo, con una nueva fecha de alta
	 * 
	 * @param pTransporteVehicular
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public TransporteVehicular restoreTransporteVehicular(TransporteVehicular pTransporteVehicular) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TransporteVehicular.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessDocumentoSHPS.restoreTransporteVehicular(pTransporteVehicular);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(336);
		}
	}

	/**
	 * Recupera un listado de transportes vehiculares
	 * 
	 * @param pNumeroInscripcion
	 *            numero de inscripcion de los transportes vehiculares
	 * @param pVehiculo
	 *            vehiculo al que pertecen los transportes vehiculares
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroTransporteVehicular findListaTransportesVehiculares(FiltroTransporteVehicular pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TransporteVehicular.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.findListaTransportesVehiculares(pFiltro);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(337);
		}
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public LibretaSanitaria getLibretaSanitariaPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LibretaSanitaria.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.getLibretaSanitariaPorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(338);
		}
	}

	/**
	 * Obtiene un documento habilitante a partir de la obligación
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public DocumentoSHPS getDocumentoHabilitanteSHPS(Obligacion pObligacion) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoSHPS.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.getDocumentoHabilitanteSHPS(pObligacion);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(339);
		}
	}

	/**
	 * Agrega una clausura a un documentoSHPS
	 * 
	 * @param pClausuraSHPS
	 *            clausura asociada
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public ClausuraSHPS addClausuraSHPS(ClausuraSHPS pClausuraSHPS) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ClausuraSHPS.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.businessDocumentoSHPS.addClausuraSHPS(pClausuraSHPS);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(340);
		}
	}

	/**
	 * Actualiza los datos de una clausura
	 * 
	 * @param pClausuraSHPS
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public ClausuraSHPS updateClausuraSHPS(ClausuraSHPS pClausuraSHPS) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ClausuraSHPS.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.businessDocumentoSHPS.updateClausuraSHPS(pClausuraSHPS);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(341);
		}
	}

	/**
	 * Recupera un listado de clausuras
	 * 
	 * @param pFechaAltaDesde
	 *            fecha de alta desde la cual filtrar el listado
	 * @param pFechaAltaHasta
	 *            fecha de alta hasta la cual filtrar el listado
	 * @param pFechaBajaDesde
	 *            fecha de baja desde la cual filtrar el listado
	 * @param pFechaBajaHasta
	 *            fecha de baja hasta la cual filtrar el listado
	 * @param isActiva
	 *            si la clausura se encuentra activa o no
	 * @param pDocumentoSHPS
	 *            documento al que pertenecen las clausuras
	 * @return Listado de clausuras
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaClausurasSHPS(Date pFechaAltaDesde, Date pFechaAltaHasta, Date pFechaBajaDesde, Date pFechaBajaHasta, Boolean isActiva, DocumentoSHPS pDocumentoSHPS)
			throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ClausuraSHPS.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.findListaClausurasSHPS(pFechaAltaDesde, pFechaAltaHasta, pFechaBajaDesde, pFechaBajaHasta, isActiva, pDocumentoSHPS);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(342);
		}
	}

	/**
	 * Recupera un listado de Documentos SHPS
	 * 
	 * @param Persona
	 *            pPersona
	 * @return lista de Documentos SHPS
	 * @throws TrascenderException
	 * @ejb.interface-method view-type="remote"
	 */
	public List findListaDocumentosSHPS(Persona pPersona) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoSHPS.serialVersionUID, Accion.SELECT)) {
				return this.businessDocumentoSHPS.findListaDocumentosSHPS(pPersona);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(348);
		}

	}

	/**
	 * Recupera un tranporte vehicular según el id
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public TransporteVehicular getTransporteVehicularPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, TransporteVehicular.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.getTransporteVehicularPorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(343);
		}
	}

	/**
	 * Recupera un local comercial por el número de identificación único
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public LocalComercial getLocalComercialPorId(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, LocalComercial.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.getLocalComercialPorId(pId);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(344);
		}
	}

	/**
	 * Actualiza un documentoSHPS
	 * 
	 * @param pDocumentoSHPS
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateDocumentoSHPS(DocumentoSHPS pDocumentoSHPS) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoSHPS.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.businessDocumentoSHPS.updateDocumentoSHPS(pDocumentoSHPS);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(345);
		}
	}

	public Integer getNroInscripcionDocEspSHPS() {
		try {
			return this.businessDocumentoSHPS.getNroInscripcionDocEspSHPS();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Firma una clausura para habilitar nuevamente la obligacion
	 * 
	 * @param pClausura
	 * @param pComentario
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FirmaPermiso firmarClausura(ClausuraSHPS pClausura, String pComentario) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ClausuraSHPS.serialVersionUID, Permiso.Accion.UPDATE)) {
				Usuario locUsuario = SecurityMgr.getInstance().getUsuario(this.llave);
				return this.businessDocumentoSHPS.firmarClausura(locUsuario, pClausura, pComentario);
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(346);
		}

	}

	/**
	 * Recupera el listado de clauras que pueden ser levantadas por el usuario actual
	 * 
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaClausurasPorUsuario() throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, ClausuraSHPS.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.businessDocumentoSHPS.findListaClausurasPorUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
			} else {
				throw new HabilitacionesException(799);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new HabilitacionesException(347);
		}

	}

	/**
	 * 
	 * @param pLlave
	 *            llave obtenida del login
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}
}
