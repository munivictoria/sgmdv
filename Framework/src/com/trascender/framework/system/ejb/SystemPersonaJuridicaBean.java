package com.trascender.framework.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.business.interfaces.BusinessPersonaLocal;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroPersonaJuridica;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.system.interfaces.SystemPersonaJuridica;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemPersonaJuridica"
 *           display-name="Name for SystemPersonaJuridica"
 *           description="Description for SystemPersonaJuridica"
 *           jndi-name="ejb/SystemPersonaJuridica"
 *           type="Stateful"
 *           view-type="remote"
 */
@Stateful(name = "ejb/SystemPersonaJuridica")
public class SystemPersonaJuridicaBean implements SystemPersonaJuridica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long llave = 0;
	@EJB
	private BusinessPersonaLocal personaLocal = null;

	public SystemPersonaJuridicaBean() {
		super();
	}

	public void setSessionContext(SessionContext pCtx)
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
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void addPersonaJuridica(
		com.trascender.framework.recurso.persistent.PersonaJuridica pPersonaJuridica)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave,PersonaJuridica.serialVersionUID,Permiso.Accion.INSERT)) {
				this.personaLocal.addPersonaJuridica(pPersonaJuridica);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(360);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void updatePersonaJuridica(
		com.trascender.framework.recurso.persistent.PersonaJuridica pPersonaJuridica)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				PersonaJuridica.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				this.personaLocal.updatePersonaJuridica(pPersonaJuridica);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(361);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void removePersonaJuridica(
		com.trascender.framework.recurso.persistent.PersonaJuridica pPersonaJuridica)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				PersonaJuridica.serialVersionUID,
				Permiso.Accion.DELETE)) {
				if (pPersonaJuridica.getEstado()==Persona.Estado.ELIMINADO){
					throw new TrascenderFrameworkException(27);
				}
				pPersonaJuridica.setEstado(Persona.Estado.ELIMINADO);
				this.personaLocal.updatePersonaJuridica(pPersonaJuridica);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(362);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void restorePersonaJuidica(
		com.trascender.framework.recurso.persistent.PersonaJuridica pPersonaJuridica)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				PersonaJuridica.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				pPersonaJuridica.setEstado(Persona.Estado.ACTIVO);
				this.personaLocal.updatePersonaJuridica(pPersonaJuridica);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(363);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public FiltroPersonaJuridica findPersonaJuridica( FiltroPersonaJuridica filtro)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				PersonaJuridica.serialVersionUID,
				Permiso.Accion.SELECT)) {				
				return this.personaLocal.findPersonaJuridica(filtro);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(364);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.PersonaJuridica getPersonaJuridicaPorCuit(
		String pCuit) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				PersonaJuridica.serialVersionUID,
				Permiso.Accion.SELECT)) {
				return this.personaLocal.getPersonaJuridicaPorCuit(pCuit);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(365);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave=pLlave;
	}
	
	
	/**
	 * Recupera una persona juridica por id
	 * @param pId
	 * @return nulo en caso que no encuentre nada
	 * @ejb.interface-method view-type = "remote"
	 */
	public PersonaJuridica getPersonaJuridicaPorId(long pId){
		try{
			return this.personaLocal.getPersonaJuridicaPorId(pId);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
