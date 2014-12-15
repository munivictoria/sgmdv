/*
 * CommunicationMesaEntradaBean.java
 *
 * Created on 04 de junio de 2008, 10:11
 * Copyright Trascender S.R.L.
 */
package muni;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.trascender.mesaentradas.system.interfaces.SystemAdministracionMesaEntradas;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class CommunicationMesaEntradaBean extends AbstractSessionBean {

	// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">

	private int __placeholder;

	/**
	 * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
	 * This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.</p>
	 */
	private void _init() throws Exception {
	}
	// </editor-fold>
	Properties props = null;
	Context ctx = null;

	/**
	 * <p>Construir una instancia de bean de datos de la sesión.</p>
	 */
	public CommunicationMesaEntradaBean() {
		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "localhost:1099");
		//        setProps(new Hashtable());
		//        getProps().put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		//        getProps().put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		//        getProps().put(Context.PROVIDER_URL, "localhost:1099");
		try {
			ctx = new InitialContext(props);
			this.remoteSystemAdministracionMesa = (SystemAdministracionMesaEntradas) ctx.lookup(SystemAdministracionMesaEntradas.JNDI_NAME);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <p>Devolver una referencia al bean de datos con ámbito.</p>
	 */
	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>Se llama a este método al agregar este bean al
	 * ámbito de la sesión.  Normalmente, esto ocurre como resultado de la evaluación
	 * de una expresión de enlace de valores o de métodos, que utiliza la
	 * función de bean administrado para crear una instancia de este bean y almacenarla en el
	 * ámbito de la sesión.</p>
	 *
	 * <p>Puede personalizar este método para inicializar y almacenar en caché los valores
	 * o recursos necesarios para el ciclo de duración de una
	 * sesión de usuario en particular.</p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicación que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar código de inicio propio aquí

		// <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados automáticamente
		// *Nota* - esta lógica NO debe modificarse
		try {
			_init();
		} catch (Exception e) {
			log("CommunicationMesaEntradaBean Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicación que debe finalizar
		// *después* de que se inicien los componentes administrados
		// TODO - Agregar código de inicio propio aquí

	}

	/**
	 * <p>Se llama a este método cuando la sesión que lo contiene está apunto de
	 * configurarse en modo pasivo.  Normalmente, esto ocurre en un contenedor de servlet distribuido
	 * cuando la sesión está apunto de transferirse a otra
	 * instancia de contenedor, después de la cual se llamará al método <code>activate()</code>
	 * para indicar que la transferencia se ha completado.</p>
	 *
	 * <p>Puede personalizar este método para liberar las referencias a datos
	 * o recursos de sesión que no pueden serializarse con la propia sesión.</p>
	 */
	@Override
	public void passivate() {
	}

	/**
	 * <p>Se llama a este método cuando la sesión que lo contiene se
	 * reactiva.</p>
	 *
	 * <p>Puede personalizar este método para volver a adquirir las referencias a
	 * datos o recursos de la sesión que no pudieron serializarse con la
	 * propia sesión.</p>
	 */
	@Override
	public void activate() {
	}

	/**
	 * <p>Se llama a este método al eliminar este bean del
	 * ámbito de la sesión.  Normalmente, esto ocurre cuando
	 * se supera el tiempo de espera de la sesión o la aplicación la finaliza.</p>
	 *
	 * <p>Puede personalizar este método para limpiar los recursos asignados
	 * durante la ejecución del método <code>init()</code> o
	 * más adelante durante el ciclo de vida de la aplicación.</p>
	 */
	@Override
	public void destroy() {
	}
	/**
	 * Definición de la interfaz remota SystemAdministracionMesa, para invocar
	 * la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed" desc="SystemAdministracionBienes">
	//    @EJB
	private SystemAdministracionMesaEntradas remoteSystemAdministracionMesa = null;

	public SystemAdministracionMesaEntradas getRemoteSystemAdministracionMesa() {
		/*    try{
        if (this.remoteSystemAdministracionMesa==null){
        Context ctx = new InitialContext(getProps());
        Object obj = ctx.lookup(SystemAdministracionMesaEntradasHome.JNDI_NAME);
        SystemAdministracionMesaEntradasHome locAdministracionMesaEntradasHome = (SystemAdministracionMesaEntradasHome)
        PortableRemoteObject.narrow(obj, SystemAdministracionMesaEntradasHome.class);
        this.remoteSystemAdministracionMesa = locAdministracionMesaEntradasHome.create();
        }
        }catch(Exception e){
        e.printStackTrace();
        }*/

		return remoteSystemAdministracionMesa;
	}

	public void setRemoteSystemAdministracionMesa(SystemAdministracionMesaEntradas remoteSystemAdministracionMesa) {
		this.remoteSystemAdministracionMesa = remoteSystemAdministracionMesa;
	}
	//</editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionBienes, para invocar
	 * la lógica de negocio.
	 */
	// <editor
	/**
	 * Sección de declaración de Listas que completan las listas
	 *
	 */
	// Lista de Tramites
	//</editor-fold>
	private List listaTramites = new ArrayList();

	/**
	 * Getter para propiedad ListaTramites
	 * @return Valor de la propiedad listaTramites.
	 */
	public List getListaTramites() {
		return this.listaTramites;
	}

	/**
	 * Setter para propiedad listaTramites
	 * @param listaBienesProvistos Nuevo valor de la propiedad listaTramites.
	 */
	public void setListaTramites(ArrayList pListaTramites) {
		this.listaTramites = pListaTramites;
	}

	private Set listaEstadoTramite = null;

	/**
	 * Getter para propiedad ListaEstadoTramites
	 * @return Valor de la propiedad listaTramites.
	 */
	public Set getListaEstadoTramite() {
		return this.listaEstadoTramite;
	}

	/**
	 * Setter para propiedad listaEstadoTramites
	 * @param listaBienesProvistos Nuevo valor de la propiedad listaTramites.
	 */
	public void setListaEstadoTramite(Set pListaEstadoTramite) {
		this.listaEstadoTramite = pListaEstadoTramite;
	}
	//Lista de Indices
	//</editor-fold>
	private ArrayList listaIndices = null;

	/**
	 * Getter para propiedad ListaIndice
	 * @return Valor de la propiedad listaIndice.
	 */
	public ArrayList getListaIndices() {
		return this.listaIndices;
	}

	/**
	 * Setter para propiedad listaTramites
	 * @param listaBienesProvistos Nuevo valor de la propiedad listaTramites.
	 */
	public void setListaIndice(ArrayList pListaIndice) {
		this.listaIndices = pListaIndice;
	}
	//Lista de Expedientes
	//</editor-fold>
	private ArrayList listaExpedientes = null;

	/**
	 * Getter para propiedad ListaIndice
	 * @return Valor de la propiedad listaIndice.
	 */
	public ArrayList getListaExpedientes() {
		return this.listaExpedientes;
	}

	/**
	 * Setter para propiedad listaTramites
	 * @param listaBienesProvistos Nuevo valor de la propiedad listaTramites.
	 */
	public void setListaExpedientes(ArrayList pListaExpedientes) {
		this.listaExpedientes = pListaExpedientes;
	}
	//Lista de Expedientes
	//</editor-fold>
	private ArrayList listaExpedientesAdmin = null;

	/**
	 * Getter para propiedad ListaIndice
	 * @return Valor de la propiedad listaIndice.
	 */
	public ArrayList getListaExpedientesAdmin() {
		return this.listaExpedientesAdmin;
	}

	/**
	 * Setter para propiedad listaTramites
	 * @param listaBienesProvistos Nuevo valor de la propiedad listaTramites.
	 */
	public void setListaExpedientesAdmin(ArrayList pListaExpedientesAdmin) {
		this.listaExpedientesAdmin = pListaExpedientesAdmin;
	}

	private ArrayList listaReclamos = null;

	/**
	 * Getter para propiedad ListaIndice
	 * @return Valor de la propiedad listaIndice.
	 */
	public ArrayList getListaReclamos() {
		return this.listaReclamos;
	}

	/**
	 * Setter para propiedad listaTramites
	 * @param listaReclamos Nuevo valor de la propiedad listaTramites.
	 */
	public void setListaReclamos(ArrayList pListaReclamos) {
		this.listaReclamos = pListaReclamos;
	}
}
