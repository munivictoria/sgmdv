package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessProveedorLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroProveedores;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaProveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionProveedores")
public class SystemAdministracionProveedoresBean implements SystemAdministracionProveedores{

	/**
	 * 
	 */
	
	@EJB
	private BusinessProveedorLocal locProveedor;
	private static final long serialVersionUID = -7915555246303849723L;
	private long llave;

	public SystemAdministracionProveedoresBean() {
		super();

	}

	public void setSessionContext(SessionContext ctx)
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
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}
//	/**
//	 * Business method
//	 * @ejb.interface-method  view-type = "remote"
//	 */
//	public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor addGrupoProveedor(
//		com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor)
//		throws java.lang.Exception {
//		try {
//			if (SecurityMgr.getInstance().getPermiso(
//				this.llave,
//				GrupoProveedor.serialVersionUID,
//				Permiso.Accion.INSERT)) {
//				return this.locProveedor.addGrupoProveedor(pGrupoProveedor);
//			} else
//				throw new TrascenderComprasException(740);
//		} catch (TrascenderException e) {
//			e.printStackTrace();
//			throw e;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new TrascenderComprasException(340);
//		}
//	}
//	/**
//	 * Business method
//	 * @ejb.interface-method  view-type = "remote"
//	 */
//	public java.util.List findListadoGruposProveedores()
//		throws java.lang.Exception {
//		try {
//			if (SecurityMgr.getInstance().getPermiso(
//				this.llave,
//				GrupoProveedor.serialVersionUID,
//				Permiso.Accion.SELECT)) {
//				return this.locProveedor.findListadoGrupoProveedor();
//			} else
//				throw new TrascenderComprasException(741);
//		} catch (TrascenderException e) {
//			throw e;
//		} catch (Exception e) {
//			throw new TrascenderComprasException(341);
//		}
//	}
//	/**
//	 * Business method
//	 * @ejb.interface-method  view-type = "remote"
//	 */
//	public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor updateGrupoProveedor(
//		com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor)
//		throws java.lang.Exception {
//		try {
//			if (SecurityMgr.getInstance().getPermiso(
//				this.llave,
//				GrupoProveedor.serialVersionUID,
//				Permiso.Accion.UPDATE)) {
//				return this.locProveedor.updateGrupoProveedor(pGrupoProveedor);
//			} else
//				throw new TrascenderComprasException(742);
//		} catch (TrascenderException e) {
//			e.printStackTrace();
//			throw e;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new TrascenderComprasException(342);
//		}
//	}
//	/**
//	 * Business method
//	 * @ejb.interface-method  view-type = "remote"
//	 */
//	public void deleteGrupoProveedor(
//		com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor)
//		throws java.lang.Exception {
//		try {
//			if (SecurityMgr.getInstance().getPermiso(
//				this.llave,
//				GrupoProveedor.serialVersionUID,
//				Permiso.Accion.DELETE)) {
//				this.locProveedor.deleteGrupoProveedor(pGrupoProveedor);
//			} else
//				new TrascenderComprasException(743);
//		} catch (TrascenderException e) {
//			e.printStackTrace();
//			throw e;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new TrascenderComprasException(343);
//		}
//	}
//	/**
//	 * Business method
//	 * @ejb.interface-method  view-type = "remote"
//	 */
//	public java.util.List findListaGrupoProveedoresPorNodo(
//		com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor)
//		throws java.lang.Exception {
//		try {
//			if (SecurityMgr.getInstance().getPermiso(
//				this.llave,
//				GrupoProveedor.serialVersionUID,
//				Permiso.Accion.SELECT)) {
//				return this.locProveedor
//					.findListaGrupoProveedoresPorNodo(pGrupoProveedor);
//			} else
//				throw new TrascenderComprasException(741);
//		} catch (TrascenderException e) {
//			e.printStackTrace();
//			throw e;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new TrascenderComprasException(341);
//		}
//	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Proveedor addProveedor(
		com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Proveedor.serialVersionUID,
				Permiso.Accion.INSERT)) {
				return this.locProveedor.addProveedor(pProveedor);
			} else
				throw new TrascenderComprasException(750);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(350);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public FiltroProveedores findListadoProveedores(FiltroProveedores filtro)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Proveedor.serialVersionUID,
				Permiso.Accion.SELECT)) {
				return this.locProveedor.findListadoProveedores(filtro);
			} else
				throw new TrascenderComprasException(751);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(351);

		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Proveedor updateProveedor(
		com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
		throws java.lang.Exception {
		try {
			//PRE: El proveedor debe estar ACTIVO
			if (pProveedor.getEstado() == Proveedor.Estado.INACTIVO)
				throw new TrascenderComprasException(355);

			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Proveedor.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				return this.locProveedor.updateProveedor(pProveedor);
			} else
				throw new TrascenderComprasException(752);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(352);
		}
	}
	
	public void validarQuitadoBienProvisto(BienProvisto pBienProvisto) throws TrascenderComprasException {
		try{
			this.locProveedor.validarQuitadoBienProvisto(pBienProvisto);
		} catch (TrascenderComprasException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(352);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteProveedor(
		com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Proveedor.serialVersionUID,
				Permiso.Accion.DELETE)) {
				this.locProveedor.deleteProveedor(pProveedor);
			} else
				throw new TrascenderComprasException(753);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(353);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Proveedor restoreProveedor(
		com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
		throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Proveedor.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				if (pProveedor.getEstado() == Proveedor.Estado.INACTIVO)
					return this.locProveedor.restoreProveedor(pProveedor);
				else
					return pProveedor;
			} else
				throw new TrascenderComprasException(754);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(354);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Proveedor findProveedorByID(
		long pIdProveedor) throws java.lang.Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Proveedor.serialVersionUID,
				Permiso.Accion.SELECT)) {
				return this.locProveedor.findProveedorByID(pIdProveedor);
			} else
				throw new TrascenderComprasException(751);
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(351);
		}
	}
//	/**
//	 * Business method
//	 * @ejb.interface-method  view-type = "remote"
//	 */
//	public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor findGrupoProveedorPorNombre(
//		String pNombre) throws java.lang.Exception {
//		// PRE:
//		
//		try{
//			if (SecurityMgr.getInstance().getPermiso(
//					this.llave,GrupoProveedor.serialVersionUID,Permiso.Accion.SELECT)){
//				return this.locProveedor.findGrupoProveedorPorNombre(pNombre);
//			}else
//				throw new TrascenderComprasException(741);
//		}catch(TrascenderException e){
//			e.printStackTrace();
//			throw e;
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new TrascenderComprasException(341);
//		}
//	}
	
	public List<Bien> findListaBienesProvistos(Proveedor pProveedor) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(
					this.llave,Bien.serialVersionUID,Permiso.Accion.SELECT)){
				return locProveedor.findListaBienesProvistos(pProveedor);
			} else {
				throw new TrascenderComprasException(741);
			}
		} catch(TrascenderException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(341);
		}
	}
}
