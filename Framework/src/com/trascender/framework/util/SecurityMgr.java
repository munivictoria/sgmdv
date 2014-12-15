
package com.trascender.framework.util;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Conexion;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemParametro;

/**
 * Centro de seguridad del sistema
 * 
 * @author Mariano Lusardi
 * 
 */
public class SecurityMgr {
	private final Random semilla = new Random();

	// private SystemLogSeguridadLocal systemLogSeguridad=null;

	/**
	 * Listado de grupos del sistema (inicializados con los ejb)
	 */
	private final ArrayList<Grupo> listaGrupos = new ArrayList<Grupo>();

	private static SecurityMgr instance = null;

	private final Map<Long, Long> listaLlaves = Collections.synchronizedMap(new HashMap<Long, Long>());

	private final Map<Long, Conexion> listaConexiones = Collections.synchronizedMap(new HashMap<Long, Conexion>());

	private Map<Long, ConfiguracionRecurso> mapaConfiguracionesRecurso;

	private String encryptionKey = "QWERTY321654MNBVCXZ7896540FGHJ";

	/**
	 * Llave del root
	 */
	private Long rootKey = null;

	private static Hashtable props = null;

	private Municipalidad municipalidad;

	private SecurityMgr() {
	}

	/**
	 * @return instancia del security manager
	 */

	@SuppressWarnings("unchecked")
	public static SecurityMgr getInstance() {
		if(instance == null) {
			instance = new SecurityMgr();
		}
		return instance;
	}

	// SEGURIDAD
	/**
	 * Añade el grupo a la lista. Si ya existiera un grupo con el mismo Id, añade los recursos a ese Grupo.
	 */
	public void addGrupo(Grupo pGrupo) {
		Grupo locGrupo = getGrupo(pGrupo);
		if(locGrupo == null)
			listaGrupos.add(pGrupo);
		else
			locGrupo.getListaRecursos().addAll(pGrupo.getListaRecursos());
	}

	private Grupo getGrupo(Grupo pGrupo) {
		for(Grupo cadaGrupo : listaGrupos) {
			if(cadaGrupo.getId() == pGrupo.getId()) {
				return cadaGrupo;
			}
		}
		return null;
	}

	public Map<Long, Conexion> getListaConexiones() {
		return this.listaConexiones;

	}

	/**
	 * Crea una lista de grupos con recursos, donde cada recurso tiene un id de recurso al que hace referencia y los permisos del usuario con ese recurso
	 * 
	 * @param pKey
	 *            llave asignada al usuario al momento del logueo
	 * @return List<Grupo> donde cada grupo tiene una lista de recursos con sus permisos
	 * @throws TrascenderFrameworkException
	 *             cuando el usuario no se encuentra registrado con esa llave
	 */
	public List<Grupo> getListaPermisos(long pKey) throws TrascenderFrameworkException {
		List<Grupo> locListaGrupos = new ArrayList<Grupo>();
		Usuario usuario = this.getListaConexiones().get(pKey).getUsuario();
		if(usuario == null)
			throw new TrascenderFrameworkException(820);

		for(Grupo cadaGrupo : this.getListaGrupos()) {
			Grupo locGrupoAgregar = new Grupo();
			locGrupoAgregar.setId(cadaGrupo.getId());
			locGrupoAgregar.setNombre(cadaGrupo.getNombre());
			for(Recurso cadaRecurso : cadaGrupo.getListaRecursos()) {
				long locIdRecurso = cadaRecurso.getIdRecurso();
				Permiso locPermiso = usuario.getPermisos().get(locIdRecurso);
				Recurso recurso;
				if(locPermiso != null) {
					recurso = new Recurso(locPermiso);
				} else {
					recurso = new Recurso();
					recurso.setIdRecurso(cadaRecurso.getIdRecurso());
				}
				recurso.setNombre(cadaRecurso.getNombre());
				locGrupoAgregar.getListaRecursos().add(recurso);

			}
			locListaGrupos.add(locGrupoAgregar);
		}

		return locListaGrupos;
	}

	/**
	 * Registra una conexi�n al sistema
	 * 
	 * @param pConexion
	 * @return
	 * @throws Exception
	 */
	public long registerUsuario(Conexion pConexion) throws Exception {
		Long llave = this.getListaLlaves().get(pConexion.getUsuario().getIdUsuario());
		if(llave != null) {
			this.unregisterUsuario(llave);
		}

		llave = 0l;
		while(llave == 0) {
			llave = this.semilla.nextLong();
		}

		this.getListaConexiones().put(llave, pConexion);
		this.getListaLlaves().put(pConexion.getUsuario().getIdUsuario(), llave);
		pConexion.setLlave(llave);

		if(pConexion.getUsuario().getUser().equals("root")) {
			this.rootKey = llave;
		}
		// creo el log
		// this.systemLogSeguridad.registrarLogSeguridad(pConexion,pConexion.getUsuario().getUser(),null,true,Permiso.Accion.LOGIN);

		return llave;

	}

	/**
	 * Quita la conexi�n del registro
	 */
	public void unregisterUsuario(long pKey) throws TrascenderException {
		Conexion locConexion = this.getListaConexiones().get(pKey);
		if(locConexion != null) {
			this.getListaLlaves().remove(locConexion.getUsuario().getIdUsuario());
			if((this.rootKey != null) && (pKey == this.rootKey)) {
				this.rootKey = null;
			}

			// creo el log
			// this.systemLogSeguridad.registrarLogSeguridad(locConexion,locConexion.getUsuario().getUser(),null,true,Permiso.Accion.LOGOUT);

			// ELimina la conexion
			this.getListaConexiones().remove(pKey);
		} else {
			throw new TrascenderFrameworkException(821);
		}

	}

	/**
	 * Valida si puede o no realizarse una acci�n por un usuario sobre un recurso
	 * 
	 * @param pKey
	 *            llave dada al usuario en su login
	 * @param pRecurso
	 *            recurso al que pretende acceder
	 * @param pAccion
	 *            acci�n que desea realizar
	 * @return verdadero si puede realizar la acci�n solicitada, falso en caso contrario
	 * @throws Exception
	 * @throws RemoteException
	 * @throws TrascenderFrameworkException
	 *             851: el permiso de permanencia del usuario ha caducado
	 */
	public boolean getPermiso(long pKey, long pRecurso, Permiso.Accion pAccion) throws RemoteException, Exception {
		boolean habilitado = false;
		Conexion locConexion = this.getListaConexiones().get(pKey);

		try {
			if((this.rootKey != null) && (this.rootKey == pKey)) {
				habilitado = true;
			} else {
				if(locConexion != null) {
					Permiso locPermiso = locConexion.getUsuario().getPermisos().get(pRecurso);
					if(locPermiso != null) {
						// valida la acci�n para ese recurso
						habilitado = locPermiso.validar(pAccion, locConexion.getUsuario());
					} else {
						// significa que el usuario no tiene permiso para ese recurso
						habilitado = false;
					}
				} else {
					// Significa que el registro caduc�
					throw new TrascenderFrameworkException(820);
				}
			}

			return habilitado;
		} finally {
			// dejo "Sesion caducada" porque en caso que la conexi�n se encuentre activa tioma el nombre de usuario de la conexion
			// this.systemLogSeguridad.registrarLogSeguridad(locConexion, "Sesión Caducada", this.getNombreRecurso(pRecurso), habilitado, pAccion);
		}
	}

	public Object getRecursoBySerial(long pSerialVersion) {
		for(Grupo cadaGrupo : this.listaGrupos) {
			if(cadaGrupo.serialVersionUID == pSerialVersion) {
				return cadaGrupo;
			} else {
				for(Recurso cadaRecurso : cadaGrupo.getListaRecursos()) {
					if(cadaRecurso.getIdRecurso() == pSerialVersion) {
						return cadaRecurso;
					}
				}
			}
		}

		return null;
	}

	/**
	 * @return devuelve la fecha actual
	 */
	public Calendar getFechaActual() {
		return Calendar.getInstance();
	}

	/**
	 * 
	 * @return ArrayList (Grupo) grupos creados por los ejb al inicio del sistema
	 */
	public ArrayList<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	/**
	 * 
	 * @return Map(idUsuario, llave)
	 */
	private Map<Long, Long> getListaLlaves() {
		return listaLlaves;
	}

	public Map<Long, ConfiguracionRecurso> getMapaConfiguracionesRecurso() {
		if (mapaConfiguracionesRecurso == null) {
			this.armarMapaConfiguracionesRecurso();
		}
		return mapaConfiguracionesRecurso;
	}
	
	
	public void armarMapaConfiguracionesRecurso() {
		SystemParametro systemParametro = this.getRemoteSystemParametro();
		try {			
			mapaConfiguracionesRecurso = new HashMap<Long, ConfiguracionRecurso>();
			
			List<ConfiguracionRecurso> lista = systemParametro.getListaTodasConfiguracionRecurso();
			for(ConfiguracionRecurso cadaConfigRecurso : lista) {
				mapaConfiguracionesRecurso.put(cadaConfigRecurso.getRecurso().getIdRecurso(), cadaConfigRecurso);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private SystemParametro getRemoteSystemParametro() {
		SystemParametro remoteSystemParametro = null;
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			props.put(Context.PROVIDER_URL, "localhost:1099");

			Context ctx = new InitialContext(props);

			remoteSystemParametro = (SystemParametro) ctx.lookup(SystemParametro.JNDI_NAME);
		} catch(NamingException e) {
			e.printStackTrace();
		}

		return remoteSystemParametro;
	}

	public Usuario getUsuario(long pKey) throws TrascenderFrameworkException {
		Conexion locConexion = this.getListaConexiones().get(new Long(pKey));
		if(locConexion == null) {
			throw new TrascenderFrameworkException(820);
		} else {
			return locConexion.getUsuario();
		}
	}

	public Set<Area> getListaAreasUsuario(long pKey) throws TrascenderFrameworkException {
		Usuario locUsuario = this.getUsuario(pKey);
		Set<Area> locListaAreas = new java.util.HashSet<Area>(locUsuario.getListaAreas());
		return locListaAreas;
	}

	public String getNombreRecurso(long pSerialNumber) {
		String nombreRecurso = "";
		completo: {
			for(Grupo locGrupo : listaGrupos) {
				for(Recurso locRecurso : locGrupo.getListaRecursos()) {
					if(locRecurso.getIdRecurso() == pSerialNumber) {
						nombreRecurso = locRecurso.getNombre();
						break completo;
					}
				}
			}
		}
		return nombreRecurso;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String pEncryptionKey) {
		encryptionKey = pEncryptionKey;
	}

	public Municipalidad getMunicipalidad() {
		if(this.municipalidad == null) {
			try {
				SystemMunicipalidad locSystemMunicipalidad = (SystemMunicipalidad) new InitialContext().lookup(SystemMunicipalidad.JNDI_NAME);
				this.municipalidad = locSystemMunicipalidad.getMunicipalidad();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return municipalidad;
	}

	public void setMunicipalidad(Municipalidad pMunicipalidad) {
		municipalidad = pMunicipalidad;
	}

	public Class<?> getClassFromSerialID(long pSerial) {
		for(Grupo cadaGrupo : this.getListaGrupos()) {
			for(Recurso cadaRecurso : cadaGrupo.getListaRecursos()) {
				if(cadaRecurso.getIdRecurso() == pSerial) {
					return cadaRecurso.getClase();
				}
			}
		}
		return null;
	}

}
