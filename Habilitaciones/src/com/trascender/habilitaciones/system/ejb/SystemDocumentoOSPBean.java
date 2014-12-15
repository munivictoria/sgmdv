package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoOSPLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessTipoAlicuotaLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroConsumoBasico;
import com.trascender.habilitaciones.recurso.filtros.FiltroServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoOSP;

@Stateful(name = "ejb/SystemDocumentoOSP")
public class SystemDocumentoOSPBean implements SystemDocumentoOSP{

	private long llave;
	
	@EJB
	private BusinessDocumentoOSPLocal businessDocumentoOSP;
	@EJB
	private BusinessTipoAlicuotaLocal businessTipoAlicuota;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5569645626781774777L;

	public SystemDocumentoOSPBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx)
		throws EJBException,
		RemoteException {
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
	 * @param pDocumentoOSP
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 * 
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP addDocumentoOSP(com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP pDocumentoOSP) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoOSP.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoOSP.addDocumentoOSP(pDocumentoOSP);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(410);
		}
	}
	/**
	 * 
	 * @param pDocumentoOSP
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 * 
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP updateDocumentoOSP(com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP pDocumentoOSP) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoOSP.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessDocumentoOSP.updateDocumentoOSP(pDocumentoOSP);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(411);
		}
	}
	
	/**
	 * 
	 * @param pDocumentoOSP
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 * 
	 */
	public void deleteDocumentoOSP(com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP pDocumentoOSP) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoOSP.serialVersionUID,Permiso.Accion.DELETE)){
				this.businessDocumentoOSP.deleteDocumentoOSP(pDocumentoOSP);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(412);
		}
	}
	
	/**
	 * 
	 * @param pPersona persona a la que pertenece el documento
	 * @param pNumeroRegistro número de registro de la parcela
	 * @param pNumeroCuenta número de cuenta 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 * 
	 */
	public java.util.List findListaDocumentosOSP(com.trascender.framework.recurso.persistent.Persona pPersona, Parcela pParcela ,Integer pNumeroCuenta) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoOSP.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoOSP.findListaDocumentosOSP(pPersona,pParcela,pNumeroCuenta);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(413);
		}
	}
	
	
	
	/**
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP getDocumentoOSP(
				com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoOSP.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoOSP.getDocumentoOSP(pObligacion);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(414);
		}
	}
	
	public com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP getDocumentoOSPPorId(
			long pIdDocHabEsp) throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, DocumentoOSP.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessDocumentoOSP.getDocumentoOSPPorId(pIdDocHabEsp);
			} else{
				throw new HabilitacionesException(799);
			}
		}catch(TrascenderException ex){
			throw ex;
		}catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(414);
		}
	}
	/**
	 * 
	 * @param pServicioOSP
	 * @return  
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP addServicioOSP(com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP pServicioOSP) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ServicioOSP.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessTipoAlicuota.addServicioOSP(pServicioOSP);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(415);
		}		
	}
	
	
	/**
	 * 
	 * @param pServicioOSP
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP updateServicioOSP(com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP pServicioOSP) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ServicioOSP.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessTipoAlicuota.updateServicioOSP(pServicioOSP);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(416);
		}		
	}
	
	/**
	 * 
	 * @param pServicioOSP
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteServicioOSP(com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP pServicioOSP) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ServicioOSP.serialVersionUID,Permiso.Accion.DELETE)){
				this.businessTipoAlicuota.deleteServicioOSP(pServicioOSP);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(417);
		}		
	}
	
	
	/**
	 * Recupera una lista de servicios de osp
	 * @return
	 * @param pCodigo código del servicio osp
	 * @param pNombre nombre del servicio osp
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroServicioOSP findListaServiciosOSP(FiltroServicioOSP pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ServicioOSP.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessTipoAlicuota.findListaServiciosOSP(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(418);
		}		
	}
	
	
	/**
	 * Recupera un servicio OSP por id 
	 * @param pIdServicioOSP
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP getServicioOSPPorId(long pIdServicioOSP) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ServicioOSP.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessTipoAlicuota.getServicioOSPPorId(pIdServicioOSP);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(ClassCastException e){
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(419);
		}
	}
	
	
	/**
	 * Agrega un registro al consumo básico
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico addConsumoBasico(com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico pConsumoBasico) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ConsumoBasico.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoOSP.addConsumoBasico(pConsumoBasico);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(86);
		}
		
	}
	
	
	/**
	 * 
	 * @param pConsumoBasico
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico updateConsumoBasico(com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico pConsumoBasico) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ConsumoBasico.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessDocumentoOSP.updateConsumoBasico(pConsumoBasico);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(87);
		}
	}
	
	
	/**
	 * 
	 * @param pConsumoBasico
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteConsumoBasico(com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico pConsumoBasico) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ConsumoBasico.serialVersionUID,Permiso.Accion.DELETE)){
				pConsumoBasico.setActivo(false);
				this.businessDocumentoOSP.deleteConsumoBasico(pConsumoBasico);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(88);
		}
	}

	public FiltroConsumoBasico getListaConsumosBasicos(FiltroConsumoBasico pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ConsumoBasico.serialVersionUID,Permiso.Accion.SELECT)){
				return  businessDocumentoOSP.getListaConsumosBasicos(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(423);
		}
	}
	
	public com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico getConsumoBasico(
			com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP pDocumentoOSP) throws TrascenderException
			{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,ConsumoBasico.serialVersionUID,Permiso.Accion.SELECT)){
				ConsumoBasico locConsumoBasico = this.businessDocumentoOSP.getConsumoBasico(pDocumentoOSP);
				return  locConsumoBasico;
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(429);
		}
		
	}
	
	/**
	    * Devuelve el último numero de cuenta ocupado + 1
	    */
	   public Integer getSugerenciaNumeroCuenta(){
		   return this.businessDocumentoOSP.getSugerenciaNumeroCuenta();
	   }
	
	/**
	 * 
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
}
