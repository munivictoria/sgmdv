package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessOrdenCompraLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroOrdenCompra;
import com.trascender.compras.recurso.filtros.FiltroTipoOrdenCompra;
import com.trascender.compras.recurso.filtros.FiltroUsuarioFirmante;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorOrdenCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionOrdenCompra;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionOrdenCompra")
public class SystemAdministracionOrdenCompraBean implements SystemAdministracionOrdenCompra {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@EJB
	private BusinessOrdenCompraLocal locOrdenCompra;
	private long llave;

	public SystemAdministracionOrdenCompraBean() {
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
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra addTipoOrdenCompra(
			com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra pTipoOrdenCompra) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoOrdenCompra.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.locOrdenCompra.addTipoOrdenCompra(pTipoOrdenCompra);
			} else
				throw new TrascenderComprasException(790);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(390);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroTipoOrdenCompra findListaTiposOrdenesCompra(FiltroTipoOrdenCompra filtro) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoOrdenCompra.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locOrdenCompra.findListaTiposOrdenesCompra(filtro);
			} else
				throw new TrascenderComprasException(791);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			throw new TrascenderComprasException(391);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra updateTipoOrdenCompra(
			com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra pTipoOrdenCompra) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoOrdenCompra.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.locOrdenCompra.updateTipoOrdenCompra(pTipoOrdenCompra);
			} else
				throw new TrascenderComprasException(792);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(392);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteTipoOrdenCompra(com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra pTipoOrdenCompra) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoOrdenCompra.serialVersionUID, Permiso.Accion.DELETE)) {
				this.locOrdenCompra.deleteTipoOrdenCompra(pTipoOrdenCompra);
			} else
				throw new TrascenderComprasException(793);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(393);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra findTipoOrdenCompraByID(long pId) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, TipoOrdenCompra.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locOrdenCompra.findTipoOrdenCompraByID(pId);
			} else
				throw new TrascenderComprasException(791);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(391);
		}
	}

	public com.trascender.compras.recurso.persistent.suministros.OrdenCompra addOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra)
			throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenCompra.serialVersionUID, Permiso.Accion.INSERT)) {
				return this.locOrdenCompra.addOrdenCompra(pOrdenCompra);
			} else
				throw new TrascenderComprasException(830);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(430);
		}

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroOrdenCompra findOrdenCompra(FiltroOrdenCompra filtro) throws TrascenderException {
		// PRE:
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenCompra.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.locOrdenCompra.findListaOrdenCompra(filtro);
			} else
				throw new TrascenderComprasException(831);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(431);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.OrdenCompra updateOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra)
			throws java.lang.Exception {
		// PRE:
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenCompra.serialVersionUID, Permiso.Accion.UPDATE)) {
				return this.locOrdenCompra.updateOrdenCompra(pOrdenCompra);
			} else
				throw new TrascenderComprasException(832);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(432);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.OrdenCompra firmarOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra)
			throws java.lang.Exception {
		// PRE:
		try {
			if (SecurityMgr.getInstance().getUsuario(llave) != null) {

				FirmaPermiso locFirmaPermiso = new FirmaPermiso();
				locFirmaPermiso.setUsuario(SecurityMgr.getInstance().getUsuario(this.llave));
				locFirmaPermiso.setFechaHora(SecurityMgr.getInstance().getFechaActual().getTime());
				locFirmaPermiso.setComentario("Firma la orden de compra " + pOrdenCompra);

				return this.locOrdenCompra.firmarOrdenCompra(pOrdenCompra, locFirmaPermiso);
			} else
				throw new TrascenderComprasException(832);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(432);
		}
	}

	/**
	 * Business method
	 * 
	 * @return
	 * @ejb.interface-method view-type = "remote"
	 */
	public void finalizarOrdenCompra(com.trascender.compras.recurso.persistent.suministros.OrdenCompra pOrdenCompra) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenCompra.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.locOrdenCompra.finalizarOrdenCompra(pOrdenCompra);
			} else
				throw new TrascenderComprasException(0);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(0);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.OrdenCompra findOrdenCompraByID(long pID) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenCompra.serialVersionUID, Permiso.Accion.SELECT))
				return this.locOrdenCompra.findOrdenCompraByID(pID);
			else
				throw new TrascenderComprasException(831);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(431);
		}
	}

	public void transferirOrdenCompra(OrdenCompra pOrden, Proveedor pNuevoProveedor, String pComentario) throws Exception, RemoteException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave, OrdenCompra.serialVersionUID, Permiso.Accion.UPDATE))
				this.locOrdenCompra.transferirOrdenCompra(pOrden, pNuevoProveedor, pComentario);
			else
				throw new TrascenderComprasException(831);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(431);
		}
	}
	
	
	public UsuarioAutorizadorOrdenCompra addUsuarioAutorizadorOrdenCompra(UsuarioAutorizadorOrdenCompra pUsuarioAutorizador) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,UsuarioAutorizadorOrdenCompra.serialVersionUID,Permiso.Accion.INSERT)){
				return this.locOrdenCompra.addUsuarioAutorizadorOrdenCompra(pUsuarioAutorizador);
			}
			else{
				throw new TrascenderComprasException(0);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(0);
		}
	}
	
	
	public UsuarioAutorizadorOrdenCompra updateUsuarioAutorizadorOrdenCompra(UsuarioAutorizadorOrdenCompra pUsuarioAutorizador) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,UsuarioAutorizadorOrdenCompra.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.locOrdenCompra.updateUsuarioAutorizadorOrdenCompra(pUsuarioAutorizador);
			}
			else{
				throw new TrascenderComprasException(0);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(0);
		}
	}
	
	public void deleteUsuarioAutorizadorOrdenCompra(UsuarioAutorizadorOrdenCompra pUsuarioAutorizador) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,UsuarioAutorizadorOrdenCompra.serialVersionUID,Permiso.Accion.DELETE)){
				this.locOrdenCompra.deleteUsuarioAutorizadorOrdenCompra(pUsuarioAutorizador);
			}
			else{
				throw new TrascenderComprasException(0);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(0);
		}
	}
	
	public FiltroUsuarioFirmante findListaUsuariosFirmantes(FiltroUsuarioFirmante pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,UsuarioAutorizadorOrdenCompra.serialVersionUID,Permiso.Accion.SELECT)){
				return this.locOrdenCompra.findListaUsuariosFirmantes(pFiltro);	
			}
			else{
				throw new TrascenderComprasException(0);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(0);
		}
	}
	
	public UsuarioAutorizadorOrdenCompra getUsuarioFirmantePorId(long pIdUsuarioFirmante) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,UsuarioAutorizadorOrdenCompra.serialVersionUID,Permiso.Accion.SELECT)){
				return this.locOrdenCompra.getUsuarioFirmantePorId(pIdUsuarioFirmante);	
			}
			else{
				throw new TrascenderComprasException(0);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(0);
		}
	}
}
