package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessLicitacionLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroContratacion;
import com.trascender.compras.recurso.persistent.suministros.ActaApertura;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionLicitacion;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionLicitacion")
public class SystemAdministracionLicitacionBean implements SystemAdministracionLicitacion {
	private static final long serialVersionUID = -2277553724782984066L;
	
	private long llave;
	@EJB
	private BusinessLicitacionLocal businessContratacion;

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
	}
	
	public void ejbCreate(){
	}
	
	public void setLlave(long pLlave) throws RemoteException{
		this.llave = pLlave;
	}
	
	public Contratacion addContratacion(Contratacion pContratacion) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave,Contratacion.serialVersionUID,Permiso.Accion.INSERT)) {
			try{
				return businessContratacion.addContratacion(pContratacion);
			} catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public Contratacion updateContratacion(Contratacion pContratacion) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave,Contratacion.serialVersionUID,Permiso.Accion.UPDATE)) {
			try{
				return businessContratacion.updateContratacion(pContratacion);
			} catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public FiltroContratacion findListaContratacion(FiltroContratacion pFiltro) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave,Contratacion.serialVersionUID,Permiso.Accion.SELECT)) {
			try{
				return businessContratacion.findListaContratacion(pFiltro);
			} catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public Contratacion getContratacionPorId(long pIdContratacion) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave,Contratacion.serialVersionUID,Permiso.Accion.UPDATE)) {
			try{
				return businessContratacion.getContratacionPorId(pIdContratacion);
			} catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public void deleteContratacion(Contratacion pContratacion) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, Contratacion.serialVersionUID, Permiso.Accion.DELETE)){
			try{
				businessContratacion.deleteContratacion(pContratacion);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public OfertaContratacion addOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, OfertaContratacion.serialVersionUID,Permiso.Accion.INSERT)) {
			try{
				return businessContratacion.addOfertaLicitacion(pOfertaLicitacion);
			} catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public OfertaContratacion updateOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave,OfertaContratacion.serialVersionUID,Permiso.Accion.UPDATE)) {
			try{
				return businessContratacion.updateOfertaLicitacion(pOfertaLicitacion);
			} catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public List<OfertaContratacion> findListaOfertaLicitacion(Contratacion pLicitacion, Date pFechaOferta, Proveedor pProveedor) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave,OfertaContratacion.serialVersionUID,Permiso.Accion.SELECT)) {
			try{
				return businessContratacion.findListaOfertaLicitacion(pLicitacion, pFechaOferta, pProveedor);
			} catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public OfertaContratacion getOfertaLicitacionPorId(long pIdLicitacion) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave,OfertaContratacion.serialVersionUID,Permiso.Accion.SELECT)) {
			try{
		return businessContratacion.getOfertaLicitacionPorId(pIdLicitacion);
			} catch (TrascenderException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}
	
	public void deleteOfertaLicitacion(OfertaContratacion pOfertaLicitacion) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, OfertaContratacion.serialVersionUID, Permiso.Accion.DELETE)){
			try{
				businessContratacion.deleteOfertaLicitacion(pOfertaLicitacion);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}

	public ActaApertura addActaApertura(ActaApertura pActaApertura)	throws Exception, RemoteException {
		if (SecurityMgr.getInstance().getPermiso(this.llave, ActaApertura.serialVersionUID, Permiso.Accion.INSERT)){
			try{
				return this.businessContratacion.addActaApertura(pActaApertura);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}

	public ActaApertura updateActaApertura(ActaApertura pActaApertura) throws Exception, RemoteException {
		if (SecurityMgr.getInstance().getPermiso(this.llave, ActaApertura.serialVersionUID, Permiso.Accion.UPDATE)){
			try{
				return this.businessContratacion.updateActaApertura(pActaApertura);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}

	public boolean deleteActaApertura(ActaApertura pActaApertura) throws Exception, RemoteException {
		if (SecurityMgr.getInstance().getPermiso(this.llave, ActaApertura.serialVersionUID, Permiso.Accion.DELETE)){
			try{
				return this.businessContratacion.deleteActaApertura(pActaApertura);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}

	public ActaApertura getActaAperturaById(Long pId) throws Exception,	RemoteException {
		if (SecurityMgr.getInstance().getPermiso(this.llave, ActaApertura.serialVersionUID, Permiso.Accion.SELECT)){
			try{
				return this.businessContratacion.getActaAperturaById(pId);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}

	public List<ActaApertura> findListaActaApertura(Contratacion pLicitacion, Proveedor pProveedor, Date pFechaDesde, Date pFechaHasta) throws Exception{
		if (SecurityMgr.getInstance().getPermiso(this.llave, ActaApertura.serialVersionUID, Permiso.Accion.SELECT)){
			try{
				return this.businessContratacion.findListaActaApertura(pLicitacion, pProveedor, pFechaDesde, pFechaHasta);
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderComprasException(391);
			}
		} else {
			throw new TrascenderComprasException(791);
		}
	}

}
