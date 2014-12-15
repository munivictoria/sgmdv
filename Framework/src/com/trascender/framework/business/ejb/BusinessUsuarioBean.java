
package com.trascender.framework.business.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.business.interfaces.BusinessUsuarioLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroUsuario;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.StringEncrypter;
import com.trascender.framework.util.StringEncrypter.EncryptionException;
import com.trascender.framework.util.TrascenderEnverListener;

/**
 * @ejb.bean name="BusinessUsuario" display-name="Name for BusinessUsuario" description="Description for BusinessUsuario" jndi-name="ejb/BusinessUsuario"
 *           type="Stateless" view-type="local"
 */
@Stateless(name = "ejb/BusinessUsuario")
public class BusinessUsuarioBean implements BusinessUsuarioLocal {

	static {
		Grupo grupo = new Grupo();
		Recurso usuario = new Recurso();

		grupo.setId(BusinessUsuarioBean.serialVersionUID);
		grupo.setNombre(BusinessUsuarioBean.NOMBRE);
		usuario.setIdRecurso(Usuario.serialVersionUID);
		usuario.setNombre("Usuario");
		usuario.setAtributosConsultables("Nombre", "user", "Persona fisica", "nombrePersonaFisica", "Estado", "estado");
		usuario.setClase(Usuario.class);

		grupo.getListaRecursos().add(usuario);
		SecurityMgr.getInstance().addGrupo(grupo);
	}

	private static final long serialVersionUID = 1033871661975385519L;
	private static final String NOMBRE = "FRM|Adm. de Usuarios";

	@PersistenceContext(name = "vipians")
	private EntityManager entity;

	public BusinessUsuarioBean() {
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
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.framework.recurso.persistent.Usuario addUsuario(com.trascender.framework.recurso.persistent.Usuario pUsuario) throws java.lang.Exception {

		this.validarUsuario(pUsuario);

		StringEncrypter encrypter = new StringEncrypter(StringEncrypter.DES_ENCRYPTION_SCHEME, SecurityMgr.getInstance().getEncryptionKey());
		pUsuario.setPassword(encrypter.encrypt(pUsuario.getPassword()));
		TrascenderEnverListener.setValoresEnAuditoriaBean(pUsuario);
		this.entity.merge(pUsuario);
		this.entity.flush();
		return pUsuario;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateUsuario(com.trascender.framework.recurso.persistent.Usuario pUsuario) throws java.lang.Exception {

		this.validarUsuario(pUsuario);

		if(pUsuario.getIdUsuario() == 1l) {
			if(pUsuario.getEstado().equals(Usuario.Estado.ELIMINADO)) {
				throw new TrascenderFrameworkException(61);
			}
			if(!pUsuario.getUser().equals("root"))
				pUsuario.setUser("root");
		}
		StringEncrypter encrypter = new StringEncrypter(StringEncrypter.DES_ENCRYPTION_SCHEME, SecurityMgr.getInstance().getEncryptionKey());
		pUsuario.setPassword(encrypter.encrypt(pUsuario.getPassword()));

		TrascenderEnverListener.setValoresEnAuditoriaBean(pUsuario);

		entity.merge(pUsuario);

		entity.flush();
	}

	private void validarUsuario(Usuario pUsuario) throws TrascenderException {
		Criterio locCriterio = Criterio.getInstance(entity, Usuario.class).add(Restriccion.LIKE("user", pUsuario.getUser(), false))
				.add(Restriccion.IGUAL("estado", Usuario.Estado.ACTIVO))
				// .add(Restriccion.DISTINTO("idUsuario", pUsuario.getIdUsuario()))
				.setProyeccion(Proyeccion.COUNT());

		if(pUsuario.getIdUsuario() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idUsuario", pUsuario.getIdUsuario())));
		}

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderFrameworkException(60);
		}
	}

	/**
	 * Permite la recuperaci�n de un listado de usuarios de la base de datos
	 * 
	 * @param pUser
	 *            nombre del usuario, en caso de haber sido ingresado un password, se filtrar� por el nombre completo del usuario, en caso contrario se
	 *            utilizar�n las primeras letras solamente
	 * @param pPassword
	 *            clave del usuario, en caso de haber sido ingresado un nombre de usuario, entonces se utilizar� la clave completa para filtrar. sino este
	 *            criterio se ignora Business method
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroUsuario findUsuario(FiltroUsuario filtro) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, Usuario.class);

		StringEncrypter encrypter = new StringEncrypter(StringEncrypter.DES_ENCRYPTION_SCHEME, SecurityMgr.getInstance().getEncryptionKey());
		if(filtro.getUser() != null) {
			if(filtro.getPassword() != null) {
				locCriterio.add(Restriccion.IGUAL("user", filtro.getUser()));
				locCriterio.add(Restriccion.IGUAL("password", encrypter.encrypt(filtro.getPassword())));
			} else {
				locCriterio.add(Restriccion.ILIKE("user", filtro.getUser()));
				if((filtro.getUser() != "root") && (filtro.getEstado() != null)) {
					locCriterio.add(Restriccion.IGUAL("estado", filtro.getEstado()));
				}
			}
		} else if(filtro.getEstado() != null) {
			locCriterio.add(Restriccion.IGUAL("estado", filtro.getEstado()));
		}

		if(filtro.getPersonaFisica() != null) {
			locCriterio.add(Restriccion.IGUAL("personaFisica", filtro.getPersonaFisica()));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Usuario.serialVersionUID, "idUsuario", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		List retorno = filtro.getListaResultados();
		for(Object obj : retorno) {
			Usuario locUsuario = (Usuario) obj;
			PersonaFisica locPersona = (PersonaFisica) Criterio.getInstance(entity, PersonaFisica.class).add(Restriccion.IGUAL("idPersona", locUsuario.getIdPersonaFisica()))
					.uniqueResult();
			locUsuario.setPersonaFisica(locPersona);
			locUsuario.toString();
			for(Rol cadaRol : locUsuario.getListaRoles()) {
				cadaRol.toString();
			}
			entity.detach(locUsuario);
			locUsuario.setPassword(encrypter.decrypt(locUsuario.getPassword()));
		}

		return filtro;

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.framework.recurso.persistent.FirmaPermiso firmar(com.trascender.framework.recurso.persistent.Usuario pUsuario, String pComentario) throws java.lang.Exception {
		FirmaPermiso firma = new FirmaPermiso();
		if(pUsuario == null)
			throw new TrascenderFrameworkException(62);
		firma.setUsuario(pUsuario);
		firma.setComentario(pComentario);

		firma.setFechaHora(Calendar.getInstance().getTime());
		return firma;
	}

	/**
	 * Recupera un usuario por nombre de usuario y clave
	 * 
	 * @param pUser
	 * @param pClave
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.framework.recurso.persistent.Usuario getUsuarioPorNombre(String pUser) throws Exception {
		Usuario locUsuario;
		StringEncrypter encrypter = new StringEncrypter(StringEncrypter.DES_ENCRYPTION_SCHEME, SecurityMgr.getInstance().getEncryptionKey());
		locUsuario = (Usuario) Criterio.getInstance(entity, Usuario.class).add(Restriccion.IGUAL("user", pUser)).setModoDebug(true).uniqueResult();

		System.out.println(locUsuario);

		if(locUsuario != null) {
			if(!locUsuario.getListaRoles().isEmpty()) {
				for(Rol cadaRol : locUsuario.getListaRoles()) {
					cadaRol.toString();
					if(!cadaRol.getListaPermisos().isEmpty()) {
						for(Permiso cadaPermiso : cadaRol.getListaPermisos()) {
							cadaPermiso.toString();
							cadaPermiso.getRol().toString();
						}
					}

				}
			}
			locUsuario.getListaAreas().size();
			PersonaFisica locPersona = (PersonaFisica) Criterio.getInstance(entity, PersonaFisica.class).add(Restriccion.IGUAL("idPersona", locUsuario.getIdPersonaFisica()))
					.uniqueResult();
			locUsuario.setPersonaFisica(locPersona);
			entity.detach(locUsuario);
			locUsuario.setPassword(encrypter.decrypt(locUsuario.getPassword()));

		}
		return locUsuario;

	}

	public Usuario getUsuarioPorId(long pId) throws TrascenderException {
		Usuario locUsuario = this.entity.find(Usuario.class, pId);

		if(locUsuario != null) {
			locUsuario.toString();
			locUsuario.getListaLogsAuditoria().size();
			locUsuario.getListaRoles().size();
			locUsuario.getListaAreas().size();

			PersonaFisica locFisica = this.entity.find(PersonaFisica.class, locUsuario.getIdPersonaFisica());
			if(locFisica != null) {
				locUsuario.setPersonaFisica(locFisica);
			}

			entity.detach(locUsuario);
			StringEncrypter encrypter;
			try {
				encrypter = new StringEncrypter(StringEncrypter.DES_ENCRYPTION_SCHEME, SecurityMgr.getInstance().getEncryptionKey());
				locUsuario.setPassword(encrypter.decrypt(locUsuario.getPassword()));
			} catch(EncryptionException e) {
				e.printStackTrace();
			}
		}

		return locUsuario;
	}
}
