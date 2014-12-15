package com.trascender.accionSocial.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.trascender.accionSocial.business.interfaces.BusinessFichaSocialLocal;
import com.trascender.accionSocial.exception.TrascenderAccionSocialException;
import com.trascender.accionSocial.recurso.filtros.FiltroFichaSocial;
import com.trascender.accionSocial.recurso.filtros.FiltroObraSocial;
import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.accionSocial.recurso.persistent.ObraSocial;
import com.trascender.accionSocial.system.interfaces.SystemFichaSocial;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemFichaSocial")
public class SystemFichaSocialBean implements SystemFichaSocial{
	
	private static final long serialVersionUID = 37001370775195538L;

	@EJB
	private BusinessFichaSocialLocal businessFichaSocial;
	
	private long llave = -1;
	
	public void setLlave(long pLlave) throws RemoteException{
		this.llave = pLlave;
	}

	public void ejbCreate(){
	}
	
	public ObraSocial addObraSocial(ObraSocial pObraSocial) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, ObraSocial.serialVersionUID, Permiso.Accion.INSERT)){
			try{
				return businessFichaSocial.addObraSocial(pObraSocial);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public ObraSocial updateObraSocial(ObraSocial pObraSocial) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, ObraSocial.serialVersionUID, Permiso.Accion.UPDATE)){
			try{
				return businessFichaSocial.updateObraSocial(pObraSocial);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public void deleteObraSocial(ObraSocial pObraSocial) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, ObraSocial.serialVersionUID, Permiso.Accion.DELETE)){
			try{
				businessFichaSocial.deleteObraSocial(pObraSocial);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public FiltroObraSocial findListaObraSocial(FiltroObraSocial pFiltro) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, ObraSocial.serialVersionUID, Permiso.Accion.SELECT)){
			try{
				return businessFichaSocial.findListaObraSocial(pFiltro);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public ObraSocial getObraSocialPorId(long pId) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, ObraSocial.serialVersionUID, Permiso.Accion.SELECT)){
			try{
				return businessFichaSocial.getObraSocialPorId(pId);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
		
	}
	
	public List findListaBeneficio(String pNombre, Beneficio.TipoBeneficio pTipoBeneficio) throws Exception{
		if (SecurityMgr.getInstance().getPermiso(this.llave, Beneficio.serialVersionUID, Permiso.Accion.SELECT)){
			try{
		return businessFichaSocial.findListaBeneficio(pNombre, pTipoBeneficio);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public Beneficio addbeneficio(Beneficio pBeneficio) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, Beneficio.serialVersionUID, Permiso.Accion.INSERT)){
			try{
				return businessFichaSocial.addbeneficio(pBeneficio);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public Beneficio updateBeneficio(Beneficio pBeneficio) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, Beneficio.serialVersionUID, Permiso.Accion.UPDATE)){
			try{
				return businessFichaSocial.updateBeneficio(pBeneficio);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public void deleteBeneficio(Beneficio pBeneficio) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, Beneficio.serialVersionUID, Permiso.Accion.DELETE)){
			try{
				this.businessFichaSocial.deleteBeneficio(pBeneficio);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public Beneficio getBeneficioPorId(long pId) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, Beneficio.serialVersionUID, Permiso.Accion.SELECT)){
			try{
				return businessFichaSocial.getBeneficioPorId(pId);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public FichaSocial addFichaSocial(FichaSocial pFichaSocial) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, FichaSocial.serialVersionUID, Permiso.Accion.INSERT)){
			try{
				return businessFichaSocial.addFichaSocial(pFichaSocial);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}

	public FichaSocial updateFichaSocial(FichaSocial pFichaSocial) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, FichaSocial.serialVersionUID, Permiso.Accion.UPDATE)){
			try{
				return businessFichaSocial.updateFichaSocial(pFichaSocial);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public void deleteFichaSocial(FichaSocial pFichaSocial) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, FichaSocial.serialVersionUID, Permiso.Accion.DELETE)){
			try{
				this.businessFichaSocial.deleteFichaSocial(pFichaSocial);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public FichaSocial getFichaSocialPorId(long pId) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, FichaSocial.serialVersionUID, Permiso.Accion.SELECT)){
			try{
				return businessFichaSocial.getFichaSocialPorId(pId);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}
	
	public FiltroFichaSocial findListaFichaSocial(FiltroFichaSocial pFiltro) throws Exception, RemoteException{
		if (SecurityMgr.getInstance().getPermiso(this.llave, FichaSocial.serialVersionUID, Permiso.Accion.SELECT)){
			try{
				return businessFichaSocial.findListaFichaSocial(pFiltro);
			} catch (TrascenderException e){
				e.printStackTrace();
				throw e;
			} catch (Exception e){
				e.printStackTrace();
				throw new TrascenderAccionSocialException(901);
			}
		} else {
			throw new TrascenderAccionSocialException(900);
		}
	}

}
