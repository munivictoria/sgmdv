package com.trascender.framework.system.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.business.interfaces.BusinessUsuarioLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.filtros.FiltroUsuario;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.Usuario.Estado;
import com.trascender.framework.recurso.transients.Conexion;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.system.interfaces.SystemLogSeguridadLocal;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemUsuario"
 *           display-name="Name for SystemUsuario"
 *           description="Description for SystemUsuario"
 *           jndi-name="ejb/SystemUsuario"
 *           type="Stateful"
 *           view-type="remote"
 */
@Stateful(name = "ejb/SystemUsuario")
public class SystemUsuarioBean implements SystemUsuario {

	private static final long serialVersionUID = 1L;
	private long llave = 0;
	@EJB
	private BusinessUsuarioLocal usuarioLocal = null;
	@EJB
	private SystemLogSeguridadLocal logSeguridadLocal=null;
	
	public SystemUsuarioBean() {
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
	public void addUsuario(
		com.trascender.framework.recurso.persistent.Usuario pUsuario)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Usuario.serialVersionUID,
				Permiso.Accion.INSERT)) {
				this.usuarioLocal.addUsuario(pUsuario);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(400);
		}
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void updateUsuario(
		com.trascender.framework.recurso.persistent.Usuario pUsuario)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Usuario.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				this.usuarioLocal.updateUsuario(pUsuario);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(401);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void removeUsuario(
		com.trascender.framework.recurso.persistent.Usuario pUsuario)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Usuario.serialVersionUID,
				Permiso.Accion.DELETE)) {
				pUsuario.setEstado(Usuario.Estado.ELIMINADO);
				this.usuarioLocal.updateUsuario(pUsuario);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(402);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void restoreUsuario(
		com.trascender.framework.recurso.persistent.Usuario pUsuario)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Usuario.serialVersionUID,
				Permiso.Accion.UPDATE)) {
				pUsuario.setEstado(Usuario.Estado.ACTIVO);
				this.usuarioLocal.updateUsuario(pUsuario);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(403);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 * @param pUser nombre de usuario
	 * @param pPassword clave del usuario
	 * @param pEstado estado en que se encuentra el usuario
	 * @param pPersonaFisica filtra los usuarios de una persona fisica
	 */
	public FiltroUsuario findUsuario(FiltroUsuario filtro)
		throws com.trascender.framework.exception.TrascenderException {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Usuario.serialVersionUID,
				Permiso.Accion.SELECT)) {

				return this.usuarioLocal.findUsuario(filtro);
			} else
				throw new TrascenderFrameworkException(805);
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderFrameworkException(404);
		}
	}

	
	
	/**
	 * Ingresa al usuario al sistema 
	 * @param pUser
	 * @param pPassword
	 * @param pRemoteAddress
	 * @param pRemotePort
	 * @param pRemoteHost
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public long login(String pUser, String pPassword, String pRemoteAddress, String pRemotePort, String pRemoteHost) throws com.trascender.framework.exception.TrascenderException{
		Conexion locConexion=new Conexion();
		locConexion.setFechaHora(Calendar.getInstance().getTime());
		locConexion.setRemoteAddress(pRemoteAddress);
		locConexion.setRemoteHost(pRemoteHost);
		locConexion.setRemotePort(pRemotePort);
		
		try{
			
			Usuario locUsuario=this.usuarioLocal.getUsuarioPorNombre(pUser);
			if (locUsuario==null){
				if (pUser.equals("root")){
					locUsuario=this.setRoot();
					if (!pPassword.equals(locUsuario.getPassword())){
						//SIGNIFICA QUE NO TIENE PERMISO PARA SER ROOT
						throw new TrascenderFrameworkException(405);
					}
				}else{
					//significa que no tiene permiso para acceder
					throw new TrascenderFrameworkException(405);
				}
			}else{
				if (!locUsuario.getPassword().equals(pPassword)){
					throw new TrascenderFrameworkException(405);
				}
			}
			if (locUsuario.getEstado() == Estado.ELIMINADO){
				throw new TrascenderFrameworkException(67);
			}
			locConexion.setUsuario(locUsuario);
			long locLlave=SecurityMgr.getInstance().registerUsuario(locConexion);
			return locLlave;
		}
		catch(TrascenderException e){
			this.logSeguridadLocal.registrarLogSeguridad(locConexion, pUser, null, false, Permiso.Accion.LOGIN);
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(408);
		}
	}
	
	
	/**
	 * Crea el root en caso que no exista
	 * @return
	 * @throws Exception
	 * 
	 */
	private Usuario setRoot() throws Exception{
		Usuario root = new Usuario();
		root.setEstado(Usuario.Estado.ACTIVO);
		root.setUser("root");
		root.setPassword("weinwein");
		return this.usuarioLocal.addUsuario(root);
	}
	
	/**
	 * Registra un usuario
	 * @return llave del sistema
	 * @ejb.interface-method  view-type = "remote"
	 */
	public long login(String pUser, String pPassword) throws com.trascender.framework.exception.TrascenderException {
		return this.login(pUser, pPassword, "127.0.0.1", "8080", "127.0.0.1");
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave = pLlave;
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void logout(long pKey) throws TrascenderException{
		SecurityMgr.getInstance().unregisterUsuario(pKey);
	}
	
	
	/**
	 * 
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void logout() throws TrascenderException{
		SecurityMgr.getInstance().unregisterUsuario(this.llave);
	}
	
	/**
	 * Trae todos los permisos del usuario actual una vez seteada la llave.
	 * @throws TrascenderFrameworkException cuando la llave ha caducado
	 * @ejb.interface-method  view-type = "remote"
	 */
	public java.util.List getListaPermisos()
		throws TrascenderFrameworkException {
		return SecurityMgr.getInstance().getListaPermisos(this.llave);
	}

	/**
	 * Obtiene la lista de grupos completa. debe tener permiso para ver los grupos y recursos.
	 * @return Lista de recusos completa
	 * @throws TrascenderFrameworkException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List getListaGruposRecursos()
		throws TrascenderFrameworkException {
		try {
			if ((SecurityMgr.getInstance().getPermiso(
					this.llave,
					Recurso.serialVersionUID,
					Permiso.Accion.SELECT))) {
				return SecurityMgr.getInstance().getListaGrupos();
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderFrameworkException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			throw new TrascenderFrameworkException(407);
		}
	}
	
	public Recurso getRecursoPorId(Long pIdRecurso)
	throws TrascenderFrameworkException {
	try {
		if ((SecurityMgr.getInstance().getPermiso(
				this.llave,
				Recurso.serialVersionUID,
				Permiso.Accion.SELECT))) {
			return (Recurso) SecurityMgr.getInstance().getRecursoBySerial(pIdRecurso);
		} else {
			throw new TrascenderFrameworkException(805);
		}
	} catch (TrascenderFrameworkException e) {
		e.printStackTrace();
		throw e;
	} catch (Exception e) {
		throw new TrascenderFrameworkException(407);
	}
}

	/**
	 * Recupera un usuario por id
	 * @param pId número de identificación del usuario
	 * @return usaurio que pertenece a ese id, nulo en caso que no exista ninguno
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.Usuario getUsuarioPorId(
		long pId) throws Exception {
		try {
			if (SecurityMgr.getInstance().getPermiso(
				this.llave,
				Usuario.serialVersionUID,
				Permiso.Accion.SELECT)) {
				return this.usuarioLocal.getUsuarioPorId(pId);
			} else {
				throw new TrascenderFrameworkException(805);
			}
		} catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		} 
			catch (Exception e) {
			return null;
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public Usuario findUsuarioPorLlave(
		long pKey)
		throws com.trascender.framework.exception.TrascenderException {
		return SecurityMgr.getInstance().getUsuario(pKey);
	}
	
	
	/**
	 * 
	 * @param pClaveVieja
	 * @param pClaveNueva
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void cambiarClave(String pClaveVieja, String pClaveNueva) throws TrascenderException{
		try{
			Usuario usuario=SecurityMgr.getInstance().getUsuario(this.llave);
			String pass=usuario.getPassword();
			System.out.println("Clave actual " + pass);
			System.out.println("Clave vieja " + pClaveVieja);
			System.out.println("Clave nueva " + pClaveNueva);
			if (pass.equals(pClaveVieja)){
				usuario.setPassword(pClaveNueva);
			}
			else{
				throw new TrascenderFrameworkException(63);
			}
			this.usuarioLocal.updateUsuario(usuario);
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(64);
		}
	}
	
	
	/**
	 * Genera una firma del usuario actual
	 * @param pComentario comentario para la firma
	 * @return
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.framework.recurso.persistent.FirmaPermiso firmar(String pComentario) throws TrascenderException{
		try{
			Usuario usr=this.findUsuarioPorLlave(this.llave);
			return this.usuarioLocal.firmar(usr, pComentario);
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderFrameworkException(66);
		}
		
	}
	
}
