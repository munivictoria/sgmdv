/*
 * CommunicationContabilidadBean.java
 *
 * Created on 12 de agosto de 2010, 13:47
 * Copyright Trascender SRL
 */

package muni;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.trascender.contabilidad.recurso.filtros.FiltroCuenta;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.contabilidad.system.interfaces.SystemReportesContabilidad;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>
 * Session scope data bean for your application. Create properties here to represent cached data that should be made available across multiple HTTP requests for
 * an individual user.
 * </p>
 *
 * <p>
 * An instance of this class will be created for you automatically, the first time your application evaluates a value binding expression or method binding
 * expression that references a managed bean using this class.
 * </p>
 */
public class CommunicationContabilidadBean extends AbstractSessionBean {

	Properties props = null;
	Context ctx = null;

	// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
	private int __placeholder;

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {
	}

	/**
	 * <p>
	 * Construir una instancia de bean de datos de la sesion.
	 * </p>
	 */
	public CommunicationContabilidadBean() {
		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "localhost:1099");
		try {
			ctx = new InitialContext(props);
			this.remoteSystemAdministracionConsultaContable = (SystemAdministracionConsultaContable) ctx.lookup(SystemAdministracionConsultaContable.JNDI_NAME);
			this.remoteSystemReportesContabilidad = (SystemReportesContabilidad) ctx.lookup(SystemReportesContabilidad.JNDI_NAME);
			this.remoteSystemParametro = (SystemParametro) ctx.lookup(SystemParametro.JNDI_NAME);

			System.out.println("CommunicationContabilidadBean");
			// TABLAS
			FiltroCuenta locFiltroCuenta = new FiltroCuenta();
			locFiltroCuenta.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaCuenta = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Cuenta.serialVersionUID), "#{compras$ABMCuenta$AdminCuenta}", locFiltroCuenta);

		} catch(Exception ex) {
			// Logger.getLogger(ComunicationBean.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ambito.
	 * </p>
	 */
	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
	}

	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	/**
	 * <p>
	 * Se llama a este metodo al agregar este bean al ambito de la sesion. Normalmente, esto ocurre como resultado de la evaluaci�n de una expresion de enlace
	 * de valores o de m�todos, que utiliza la funcion de bean administrado para crear una instancia de este bean y almacenarla en el ambito de la sesion.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este metodo para inicializar y almacenar en cacho los valores o recursos necesarios para el ciclo de duracion de una sesion de usuario
	 * en particular.
	 * </p>
	 */
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicacion que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar codigo de inicio propio aqui

		// <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("CommunicationContabilidadBean Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicacion que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

	}

	/**
	 * <p>
	 * Se llama a este m�todo cuando la sesi�n que lo contiene est� apunto de configurarse en modo pasivo. Normalmente, esto ocurre en un contenedor de servlet
	 * distribuido cuando la sesi�n est� apunto de transferirse a otra instancia de contenedor, despu�s de la cual se llamar� al m�todo <code>activate()</code>
	 * para indicar que la transferencia se ha completado.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este m�todo para liberar las referencias a datos o recursos de sesi�n que no pueden serializarse con la propia sesi�n.
	 * </p>
	 */
	public void passivate() {
	}

	/**
	 * <p>
	 * Se llama a este m�todo cuando la sesi�n que lo contiene se reactiva.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este m�todo para volver a adquirir las referencias a datos o recursos de la sesi�n que no pudieron serializarse con la propia sesi�n.
	 * </p>
	 */
	public void activate() {
	}

	/**
	 * <p>
	 * Se llama a este m�todo al eliminar este bean del �mbito de la sesi�n. Normalmente, esto ocurre cuando se supera el tiempo de espera de la sesi�n o la
	 * aplicaci�n la finaliza.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este m�todo para limpiar los recursos asignados durante la ejecuci�n del m�todo <code>init()</code> o m�s adelante durante el ciclo de
	 * vida de la aplicaci�n.
	 * </p>
	 */
	public void destroy() {
	}

	//
	// Interfaces
	//

	/**
	 * Definicion de la interfaz remota SystemAdministracionConsultaContable, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemParametro remoteSystemParametro = null;

	public SystemParametro getRemoteSystemParametro() {
		return remoteSystemParametro;
	}

	public void setRemoteSystemParametro(SystemParametro remoteSystemParametro) {
		this.remoteSystemParametro = remoteSystemParametro;
	}

	private SystemAdministracionConsultaContable remoteSystemAdministracionConsultaContable = null;

	public SystemAdministracionConsultaContable getRemoteSystemAdministracionConsultaContable() {
		// try{
		// if (this.remoteSystemAdministracionConsultaContable==null){
		// Context ctx = new InitialContext(props);
		// Object obj = ctx.lookup(SystemAdministracionConsultaContableHome.JNDI_NAME);
		// SystemAdministracionConsultaContableHome locSystemAdministracionConsultaContableHome = (SystemAdministracionConsultaContableHome)
		// PortableRemoteObject.narrow(obj, SystemAdministracionConsultaContableHome.class);
		// this.remoteSystemAdministracionConsultaContable = locSystemAdministracionConsultaContableHome.create();
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		return this.remoteSystemAdministracionConsultaContable;
	}

	public void setRemoteSystemAdministracionConsultaContable(SystemAdministracionConsultaContable pRemoteSystemAdministracionConsultaContable) {
		this.remoteSystemAdministracionConsultaContable = pRemoteSystemAdministracionConsultaContable;
	}

	// @EJB
	private SystemReportesContabilidad remoteSystemReportesContabilidad = null;

	public SystemReportesContabilidad getRemoteSystemReportesContabilidad() {
		// try{
		// if (this.remoteSystemAdministracionConsultaContable==null){
		// Context ctx = new InitialContext(props);
		// Object obj = ctx.lookup(SystemAdministracionConsultaContableHome.JNDI_NAME);
		// SystemAdministracionConsultaContableHome locSystemAdministracionConsultaContableHome = (SystemAdministracionConsultaContableHome)
		// PortableRemoteObject.narrow(obj, SystemAdministracionConsultaContableHome.class);
		// this.remoteSystemAdministracionConsultaContable = locSystemAdministracionConsultaContableHome.create();
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		return this.remoteSystemReportesContabilidad;
	}

	public void setRemoteSystemReportesContabilidad(SystemReportesContabilidad pRemoteSystemReportesContabilidad) {
		this.remoteSystemReportesContabilidad = pRemoteSystemReportesContabilidad;
	}

	//
	// ArrayLists
	//

	/**
	 * Conserva el valor de la propiedad listaConceptosIngresoVario.
	 */
	private List listaCuenta = new ArrayList();

	/**
	 * Getter para propiedad listaConceptosIngresoVario.
	 * 
	 * @return Valor de la propiedad listaConceptosIngresoVario.
	 */
	public List getListaCuenta() {

		return this.listaCuenta;
	}

	/**
	 * Setter para propiedad listaConceptosIngresoVario.
	 * 
	 * @param listaConceptosIngresoVario
	 *            Nuevo valor de la propiedad listaConceptosIngresoVario.
	 */
	public void setListaCuenta(List listaCuentas) {

		this.listaCuenta = listaCuentas;
	}

	// ***** PaginatedTables *****

	private PaginatedTable tablaCuenta;

	public PaginatedTable getTablaCuenta() {
		return tablaCuenta;
	}

	public void setTablaCuenta(PaginatedTable tablaCuenta) {
		this.tablaCuenta = tablaCuenta;
	}

}
