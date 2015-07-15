package com.trascender.gui.framework.main;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.gui.framework.exception.GuiException;

public class AdministracionSystems {

	private static Hashtable props;
	private static Context contexto;
	private long llave;

	private SystemUsuario systemUsuario;
	private SystemParametro systemParametro;
	private SystemPersonaFisica systemPersonaFisica;
	private SystemMunicipalidad systemMunicipalidad;


	//	private Hashtable getProps(){
	//		if (this.props == null){
	//			props = new Hashtable();
	//			props.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.security.jndi.JndiLoginInitialContextFactory");
	//		    props.put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");                                                
	//		    props.put(Context.PROVIDER_URL, "localhost:1099");
	//		}
	//		return props;
	//	}
	/**
	 * Actualiza la llave del usuario.
	 * @param pLlave
	 * @throws Exception
	 */
	public void setLlave(long pLlave) throws Exception {
		this.llave = pLlave;
		AppManager.getInstance().setLlaveUsuarioConectado(pLlave);
		if (this.systemUsuario != null) this.systemUsuario.setLlave(this.llave);
		if (this.systemPersonaFisica != null) this.systemPersonaFisica.setLlave(this.llave);
		if (this.systemMunicipalidad != null) this.systemMunicipalidad.setLlave(this.llave);
	}

	/**
	 * Obtiene un InitialContext.
	 * @return InitialContext
	 * @throws Exception
	 */
	protected Context getInitialContext() throws Exception {
		if (contexto==null){
			contexto  = new InitialContext();
		}
		return contexto;
	}

	/**
	 * Obtiene la interfaz SystemUsuario para la invocación de los métodos de
	 * negocio.
	 * @return SystemUsuario
	 * @throws TrascenderException
	 */
	public SystemUsuario getSystemUsuario() throws TrascenderException{
		try{
			if (this.systemUsuario == null){
				Context ctx = this.getInitialContext();
				this.systemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
				//				this.systemUsuario.setLlave(this.llave);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(CommunicationException e){
			e.printStackTrace();
			throw new GuiException(4);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(3);
		}
		return this.systemUsuario;
	}
	
	/**
	 * Obtiene la interfaz SystemUsuario para la invocación de los métodos de
	 * negocio.
	 * @return SystemUsuario
	 * @throws TrascenderException
	 */
	public SystemParametro getSystemParametros() throws TrascenderException{
		try{
			if (this.systemParametro == null){
				Context ctx = this.getInitialContext();
				this.systemParametro = (SystemParametro) ctx.lookup(SystemParametro.JNDI_NAME);
				//				this.systemUsuario.setLlave(this.llave);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(CommunicationException e){
			e.printStackTrace();
			throw new GuiException(4);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(3);
		}
		return this.systemParametro;
	}

	/**
	 * Obtiene la interfaz SystemPersonaFisica para la invocación de los métodos
	 * de negocio.
	 * @return SystemPersonaFisica
	 * @throws TrascenderException
	 */
	public SystemPersonaFisica getSystemPersonaFisica() throws TrascenderException {
		try{
			if (this.systemPersonaFisica == null){
				Context ctx = this.getInitialContext();
				this.systemPersonaFisica = (SystemPersonaFisica) ctx.lookup(SystemPersonaFisica.JNDI_NAME);
				this.systemPersonaFisica.setLlave(this.llave);
			}
			return this.systemPersonaFisica;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}

	public SystemMunicipalidad getSystemMunicipalidad() throws TrascenderException{
		try{
			if (this.systemMunicipalidad == null){
				Context ctx = this.getInitialContext();
				this.systemMunicipalidad = (SystemMunicipalidad) ctx.lookup(SystemMunicipalidad.JNDI_NAME);
				this.systemMunicipalidad.setLlave(this.llave);
			}
			return this.systemMunicipalidad;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new GuiException(2);
		}
	}

	/**
	 * Login del usuario.
	 * @param pNombre Nombre del usuario
	 * @param pClave Contraseña del usuario
	 * @throws Exception
	 */
	public long login(String pNombre, String pClave) throws Exception {
		contexto = new InitialContext();
		this.setLlave(this.getSystemUsuario().login(pNombre,pClave));
		return this.llave;
	}

	/**
	 * Retorna la llave del usuario.
	 * @return La llave del usuario.
	 */
	protected long getLlave() {
		return this.llave;
	}


}
