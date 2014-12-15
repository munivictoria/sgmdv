
package com.trascender.framework.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.business.interfaces.BusinessPersonaLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroPersonaFisica;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;

/**
 * @ejb.bean name="SystemPersonaFisica" display-name="Name for SystemPersonaFisica" description="Description for SystemPersonaFisica"
 *           jndi-name="ejb/SystemPersonaFisica" type="Stateful" view-type="remote"
 */
@Stateful(name = "ejb/SystemPersonaFisica")
public class SystemPersonaFisicaBean implements SystemPersonaFisica {

	private static final long serialVersionUID = -2141480125523126518L;

	private long llave = 0;

	@EJB
	private BusinessPersonaLocal personaLocal = null;

	public SystemPersonaFisicaBean() {
		super();

	}

	public void setSessionContext(SessionContext pCtx) throws EJBException, RemoteException {

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
	 * Permite agregar una persona f�sica
	 * 
	 * @param Persona
	 *            f�sica que se desea agregar, �sta no debe estar registrada
	 * @throws TrascenderFrameworkException
	 *             , Exception Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addPersonaFisica(PersonaFisica pPersonaFisica) throws TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.INSERT)) {
				this.personaLocal.addPersonaFisica(pPersonaFisica);
			} else
				throw new TrascenderFrameworkException(805);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(340);
		}
	}

	/**
	 * Permite actualizar los datos ingresados de una persona f�sica
	 * 
	 * @param Persona
	 *            f�sica que se desea actualizar, �sta debe estar registrada Business method
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updatePersonaFisica(PersonaFisica pPersonaFisica) throws TrascenderException {

		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.UPDATE)) {
				this.personaLocal.updatePersonaFisica(pPersonaFisica);
			} else
				throw new TrascenderFrameworkException(805);

		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(341);
		}
	}

	/**
	 * Business method
	 * 
	 * @throws TrascenderFrameworkException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void removePersonaFisica(PersonaFisica pPersona) throws com.trascender.framework.exception.TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.DELETE)) {
				this.personaLocal.removePersonaFisica(pPersona);
			} else
				throw new TrascenderFrameworkException(805);

		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(342);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void restorePersonaFisica(com.trascender.framework.recurso.persistent.PersonaFisica pPersona) throws com.trascender.framework.exception.TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.UPDATE)) {
				pPersona.setEstado(Persona.Estado.ACTIVO);
				this.personaLocal.updatePersonaFisica(pPersona);

			} else
				throw new TrascenderFrameworkException(805);

		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(343);
		}
	}

	/**
	 * Business method
	 * 
	 * @param pCuil
	 *            cuil
	 * @param pNombre
	 *            nombre de la persona fisica
	 * @param pApellido
	 *            aplellido de la persona f�sica
	 * @param pDomicilioPostal
	 *            domicilio postal (no funciona)
	 * @param pFechaNacimiento
	 *            fecha de nacimiento
	 * @param pEdad
	 *            edad de la persona
	 * @param pSexo
	 *            sexo de la persona
	 * @param pTelefono
	 *            telefono de la persona
	 * @param pCelular
	 *            celular de la persona
	 * @param pEmail
	 *            email de la persona
	 * @param pEstado
	 *            estado en que se encuentra la persona
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroPersonaFisica findPersonaFisica(FiltroPersonaFisica filtro) throws com.trascender.framework.exception.TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.SELECT)) {
				Persona.Estado locEstado = null;
				return this.personaLocal.findPersonaFisica(filtro);
			} else
				throw new TrascenderFrameworkException(805);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(344);
		}
	}

	/**
	 * Business method
	 * 
	 * @throws TrascenderFrameworkException
	 *             (14) en caso que la persona no exista
	 * @ejb.interface-method view-type = "remote"
	 */
	public PersonaFisica getPersonaFisicaPorCuim(String pCuim) throws Exception {
		PersonaFisica per = null;
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.SELECT)) {
				per = this.personaLocal.getPersonaFisicaPorCuim(pCuim);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(344);
		}
		return per;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	/**
	 * Recupera el listado completo de personas f�sicas Business method
	 * 
	 * @throws TrascenderFrameworkException
	 *             (12) no se puede recuperar el listado de personas f�sicas
	 * @ejb.interface-method view-type = "remote"
	 */
	public List getListadoPersonasFisicas(String pEstado) throws TrascenderFrameworkException {
		List lista = null;
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.SELECT)) {
				Persona.Estado estado = null;
				if(pEstado == null) {
					estado = Persona.Estado.ACTIVO;
				} else {
					estado = Persona.Estado.valueOf(pEstado);
				}
				lista = this.personaLocal.getListadoPersonasFisicas(estado);
			} else
				throw new TrascenderFrameworkException(805);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(344);
		}

		return lista;
	}

	/**
	 * Business method
	 * 
	 * @throws TrascenderFrameworkException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List getListadoPersonasFisicas() throws TrascenderFrameworkException {
		return this.getListadoPersonasFisicas(Util.getEnumNameFromString(Persona.Estado.ACTIVO.toString()));
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.PersonaFisica getPersonaFisicaPorId(long pId) throws com.trascender.framework.exception.TrascenderFrameworkException {
		PersonaFisica per = null;
		try {
			boolean esPersonaActual = (SecurityMgr.getInstance().getUsuario(this.llave).getIdPersonaFisica() == pId);
			if((!SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.SELECT)) && (!esPersonaActual)) {
				throw new TrascenderFrameworkException(805);
			} else {
				per = personaLocal.getPersonaFisicaPorId(pId);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(344);
		}
		return per;
	}

	public Persona getPersonaPorId(long pId) throws TrascenderFrameworkException {
		Persona persona = null;
		try {
			if((SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.SELECT))
					|| (SecurityMgr.getInstance().getPermiso(this.llave, PersonaJuridica.serialVersionUID, Permiso.Accion.SELECT))) {
				persona = personaLocal.getPersonaPorId(pId, Persona.class);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(344);
		}
		return persona;
	}

	public List<AuxIdEntidad> findListaAuxIdPersona(String cadena) throws com.trascender.framework.exception.TrascenderException {
		try {
			if(SecurityMgr.getInstance().getPermiso(this.llave, PersonaFisica.serialVersionUID, Permiso.Accion.SELECT)
					|| SecurityMgr.getInstance().getPermiso(this.llave, PersonaJuridica.serialVersionUID, Permiso.Accion.SELECT)) {
				return this.personaLocal.findListaAuxIdPersona(cadena);
			} else
				throw new TrascenderFrameworkException(805);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(344);
		}
	}
}