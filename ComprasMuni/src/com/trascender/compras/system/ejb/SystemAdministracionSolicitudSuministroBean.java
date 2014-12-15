
package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessSolicitudSuministroLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroAutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.filtros.FiltroEstadoSolicitudSuministro;
import com.trascender.compras.recurso.filtros.FiltroSolicitudSuministro;
import com.trascender.compras.recurso.persistent.reference.CuentaRfr;
import com.trascender.compras.recurso.persistent.suministros.AutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.FirmaPermisoSolicitud;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolicitudSuministro;
import com.trascender.compras.system.interfaces.SystemAdministracionSolicitudSuministro;
import com.trascender.framework.business.interfaces.BusinessMunicipalidadLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionSolicitudSuministro")
public class SystemAdministracionSolicitudSuministroBean implements SystemAdministracionSolicitudSuministro {

	/**
	 * 
	 */
	@EJB
	private BusinessSolicitudSuministroLocal locSolicitudSuministro;

	@EJB
	private BusinessMunicipalidadLocal businessMunicipalidad;

	@EJB
	private SystemMunicipalidad systemMunicipalidad;

	private static final long serialVersionUID = 1L;
	private long llave;

	public SystemAdministracionSolicitudSuministroBean() {
		super();

	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbCreate() throws CreateException {
	}

	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	public com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro addSolicitudSuministro(
			com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro pSolicitudSuministro) throws java.lang.Exception {
		try {
			// PRE:
			// if (pSolicitudSuministro.getOrdenCompra() != null)
			// throw new TrascenderComprasException(371);
			if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.locSolicitudSuministro.addSolicitudSuministro(pSolicitudSuministro);
			} else
				throw new TrascenderComprasException(770);
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(370);
		}
	}

	public FiltroSolicitudSuministro findListadoSolicitudSuministro(FiltroSolicitudSuministro filtro) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.AUDITH)) {
				return this.locSolicitudSuministro.findListadoSolicitudSuministro(filtro);
			} else {
				if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.SELECT)) {
					filtro.setUsuario(SecurityMgr.getInstance().getUsuario(llave));
					return this.locSolicitudSuministro.findListadoSolicitudSuministroPorUsuario(filtro);
				} else
					throw new TrascenderComprasException(773);
			}

		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(373);
		}
	}

	public com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro updateSolicitudSuministro(
			com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro pSolicitudSuministro) throws java.lang.Exception {
		try {
			// PRE:
			if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.locSolicitudSuministro.updateSolicitudSuministro(pSolicitudSuministro);
			} else
				throw new TrascenderComprasException(774);
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(374);
		}
	}

	public void updateCuentaRfr(
			com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro pSolicitudSuministro) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.locSolicitudSuministro.updateCuentaRfr(pSolicitudSuministro);
			} else
				throw new TrascenderComprasException(774);
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(374);
		}
	}

	public com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro findSolicitudSuministroByID(long pIdSolicitudSuministro) throws java.lang.Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locSolicitudSuministro.findSolicitudSuministroByID(pIdSolicitudSuministro);
			} else
				throw new TrascenderComprasException(773);
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(373);
		}
	}

	public FiltroAutorizacionSolicitudSuministro findListaAutorizacionSolicitudSuministro(FiltroAutorizacionSolicitudSuministro filtro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, AutorizacionSolicitudSuministro.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locSolicitudSuministro.findListaAutorizacionSolicitudSuministro(filtro);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(924);
		}
	}

	public void deleteAutorizacionSolicitudSuministro(AutorizacionSolicitudSuministro pAutorizacion) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, AutorizacionSolicitudSuministro.serialVersionUID, Permiso.Accion.DELETE)) {
				this.locSolicitudSuministro.deleteAutorizacionSolicitudSuministro(pAutorizacion);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(922);
		}
	}

	public AutorizacionSolicitudSuministro getAutorizacionSolicitudSuministroByID(Long pId) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, AutorizacionSolicitudSuministro.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locSolicitudSuministro.getAutorizacionSolicitudSuministroByID(pId);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(923);
		}
	}

	public AutorizacionSolicitudSuministro updateAutorizacionSolicitudSuministro(AutorizacionSolicitudSuministro pAutorizacion) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, AutorizacionSolicitudSuministro.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.locSolicitudSuministro.updateAutorizacionSolicitudSuministro(pAutorizacion);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(921);
		}
	}

	public AutorizacionSolicitudSuministro addAutorizacionSolicitudSuministro(AutorizacionSolicitudSuministro pAutorizacion) throws Exception {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, AutorizacionSolicitudSuministro.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.locSolicitudSuministro.addAutorizacionSolicitudSuministro(pAutorizacion);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	public void finalizarSolicitud(SolicitudSuministro pSolicitud) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.locSolicitudSuministro.finalizarSolicitud(pSolicitud);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	public SolicitudSuministro firmarSolicitudSuminstro(SolicitudSuministro pSolicitudSuministro) throws Exception {
		try {
			FirmaPermiso firma = new FirmaPermiso();
			firma.setFechaHora(SecurityMgr.getInstance().getFechaActual().getTime());
			firma.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
			firma.setComentario("Firmó la solicitud de suministro: " + pSolicitudSuministro);

			FirmaPermisoSolicitud firmaSol = new FirmaPermisoSolicitud();
			firmaSol.setFirmaPermiso(firma);
			firmaSol.setSolicitudSuministro(pSolicitudSuministro);

			if(SecurityMgr.getInstance().getUsuario(llave) != null) {
				return this.locSolicitudSuministro.firmarSolicitudSuminstro(pSolicitudSuministro, firmaSol);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	public List<LineaSolicitudSuministro> findListaLineasSolicitudSuministro(boolean filtrarPorCodCiiu, boolean filtrarPorCategoria, List<Proveedor> pListaProveedores, Bien pBien,
			SolicitudSuministro pSolicitud) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locSolicitudSuministro.findListaLineasSolicitudSuministro(filtrarPorCodCiiu, filtrarPorCategoria, pListaProveedores, pBien, pSolicitud);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	/**
	 * Devuelve las Areas que puede ver un usuario para operar sobre Solicitudes de Suministros. Si es un usuario común, devuelve las areas segun sus roles, si
	 * tiene permisos de Auditoría sobre Solicitudes de Suministro o es root, devuelve todas las areas.
	 */
	public Set<Area> findListaAreasCompras() throws TrascenderException {
		try {
			Set<Area> locListaRetorno = null;
			if(SecurityMgr.getInstance().getPermiso(this.llave, SolicitudSuministro.serialVersionUID, Permiso.Accion.AUDITH)) {
				// Si es root entra por aquí.
				// locListaRetorno = new HashSet<Area>(systemMunicipalidad.findListaTodasLasAreas());
			} else {
				locListaRetorno = new HashSet<Area>(SecurityMgr.getInstance().getUsuario(llave).getListaAreas());
			}
			return locListaRetorno;
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	public EstadoSolicitudSuministro addEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolSum) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, EstadoSolicitudSuministro.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.locSolicitudSuministro.addEstadoSolicitudSuministro(pEstadoSolSum);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}

	}

	public void updateEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolSum) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, EstadoSolicitudSuministro.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.locSolicitudSuministro.updateEstadoSolicitudSuministro(pEstadoSolSum);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	public void deleteEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolSum) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, EstadoSolicitudSuministro.serialVersionUID, Permiso.Accion.DELETE)) {
				this.locSolicitudSuministro.deleteEstadoSolicitudSuministro(pEstadoSolSum);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}

	}

	public FiltroEstadoSolicitudSuministro findListaEstadoSolSum(FiltroEstadoSolicitudSuministro pFiltro) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, EstadoSolicitudSuministro.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locSolicitudSuministro.findListaEstadoSolSum(pFiltro);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}
	
	public EstadoSolicitudSuministro getEstadoSolicitudSuministroByID(long pId) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, EstadoSolicitudSuministro.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locSolicitudSuministro.getEstadoSolicitudSuministroByID(pId);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	public List<EstadoSolicitudSuministro> findListaTodosEstadosSolSum() throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getUsuario(llave) != null) {
				FiltroEstadoSolicitudSuministro locFiltro = new FiltroEstadoSolicitudSuministro();
				return this.locSolicitudSuministro.findListaEstadoSolSum(locFiltro).getListaResultados();
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}
	
	public List<EstadoSolicitudSuministro> findListaEstadoSolSumFinal() throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, EstadoSolicitudSuministro.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locSolicitudSuministro.findListaEstadoSolSumFinal();
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	public CuentaRfr getCuentaRfr(LineaSolicitudSuministro pLinea) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getUsuario(llave) != null) {
				return this.locSolicitudSuministro.getCuentaRfr(pLinea);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(920);
		}
	}

	public List<EstadoSolicitudSuministro> getListaEstadosFinalizacion(long pLlave, SolicitudSuministro pSolicitud) throws TrascenderException {
		try {
			Usuario locUsuario = SecurityMgr.getInstance().getUsuario(pLlave);
			if(locUsuario != null) {
				return this.locSolicitudSuministro.getListaEstadosFinalizacion(locUsuario, pSolicitud);
			} else {
				throw new TrascenderComprasException(775);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(0);
		}
	}
	
	public boolean validarOperarSolicitudesUrgentes(Area pArea){
		Usuario locUsuario = null;
		try {
			locUsuario = SecurityMgr.getInstance().getUsuario(llave);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
		}
		if(locUsuario != null) {
			return this.locSolicitudSuministro.validarOperarSolicitudesUrgentes(pArea, locUsuario);
		}
		
		return false;
	}
	
	public List<LineaOrdenCompra> getListaLineaOrdenCompraPorSolicitud(SolicitudSuministro pSolicitudSuministro){
		return this.locSolicitudSuministro.getListaLineaOrdenCompraPorSolicitud(pSolicitudSuministro);
	}
	
	public AutorizacionSolicitudSuministro validarFirma(SolicitudSuministro pSolicitudSuministro, Usuario pUsuario) throws TrascenderException{
		return this.locSolicitudSuministro.validarFirma(pSolicitudSuministro, pUsuario);
	}
}
