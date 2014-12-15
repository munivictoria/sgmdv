package com.trascender.framework.system.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.business.interfaces.BusinessContratoLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroContrato;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.system.interfaces.SystemContrato;
import com.trascender.framework.util.SecurityMgr;


/**
 * @ejb.bean name="SystemContrato"
 *           display-name="Name for SystemContrato"
 *           description="Description for SystemContrato"
 *           jndi-name="ejb/SystemContrato"
 *           type="Stateful"
 *           view-type="remote"
 */
@Stateful(name = "ejb/SystemContrato")
public class SystemContratoBean implements SystemContrato{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private BusinessContratoLocal locBusinessContrato = null;
	
	private long llave = 0;
	
	public SystemContratoBean() {
		super();
	}
	
	
	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext pArg0) throws EJBException, RemoteException {
	}
	
	/**
	 * Default create method
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException{
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public void addContrato(
			com.trascender.framework.recurso.persistent.Contrato pContrato)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Contrato.serialVersionUID
					, Permiso.Accion.INSERT)){
				this.locBusinessContrato.addContrato(pContrato);
			}
			else{
				throw new TrascenderFrameworkException(805);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(705);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateContrato(
			com.trascender.framework.recurso.persistent.Contrato pContrato)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					Contrato.serialVersionUID, Permiso.Accion.UPDATE)){
				this.locBusinessContrato.updateContrato(pContrato);
			}
			else{
				throw new TrascenderFrameworkException(805);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(706);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public void deleteContrato(com.trascender.framework.recurso.persistent.Contrato pContrato)
		throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
						Contrato.serialVersionUID, Permiso.Accion.DELETE)){
					this.locBusinessContrato.deleteContrato(pContrato);
			}
			else{
				throw new TrascenderFrameworkException(805);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(707);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.framework.recurso.persistent.Contrato getContratoPorId(long pId)
		throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
				Contrato.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessContrato.getContratoPorId(pId);
			}
			else{
				throw new TrascenderFrameworkException(805);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(708);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type ="remote"
	 */
	public FiltroContrato findListaContratos(FiltroContrato filtro)
			throws Exception, RemoteException {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Contrato.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessContrato.findListaContratos(filtro);
			}
			else{
				throw new TrascenderFrameworkException(805);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(709);
		}
	
	}
	
	
}
