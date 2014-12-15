package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTGILocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.persistent.FiltroObligacionTGI;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoTGI;

@Stateful(name = "ejb/SystemDocumentoTGI")
public class SystemDocumentoTGIBean implements SystemDocumentoTGI {
	private long llave;
	@EJB
	private BusinessDocumentoTGILocal businessDocumentoTGI;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6479385790123765819L;

	public SystemDocumentoTGIBean() {
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
	 * Agrega un documento TGI al registro
	 * @param pDocumentoTGI
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI addDocumentoTGI(com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI pDocumentoTGI) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoTGI.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoTGI.addDocumentoTGI(pDocumentoTGI);
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
			throw new HabilitacionesException(420);
		}
	}

	
	/**
	 * Actualiza los datos de un documento TGI
	 * @param pDocumentoTGI
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI updateDocumentoTGI(com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI pDocumentoTGI) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoTGI.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessDocumentoTGI.updateDocumentoTGI(pDocumentoTGI);
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
			throw new HabilitacionesException(421);
		} 
	}
	
	/**
	 * Elimina un documento tgi
	 * @param pDocumentoTGI
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteDocumentoTGI(com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI pDocumentoTGI) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoTGI.serialVersionUID,Permiso.Accion.INSERT)){
				this.businessDocumentoTGI.deleteDocumentoTGI(pDocumentoTGI);
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
			throw new HabilitacionesException(422);
		}
	}
	
	
	/**
	 * Recupera un listado de documentos de tgi
	 * @param pPersona
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroObligacionTGI findListaDocumentoTGI(FiltroObligacionTGI pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoTGI.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoTGI.findListaDocumentosTGI(pFiltro);
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
	
	/**
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI getDocumentoTGI(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoTGI.serialVersionUID,Permiso.Accion.SELECT)){
				DocumentoTGI locDoc = this.businessDocumentoTGI.getDocumentoTGI(pObligacion);
				return locDoc;
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(424);
		}
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
