/*
 * ComunicationBean.java
 *
 * Created on 13 de septiembre de 2006, 13:03
 * Copyright Trascender
 */

package muni;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeMap;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.filtros.FiltroArea;
import com.trascender.framework.recurso.filtros.FiltroCodigoCiiu;
import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.filtros.FiltroContrato;
import com.trascender.framework.recurso.filtros.FiltroDiaFeriado;
import com.trascender.framework.recurso.filtros.FiltroDigestoMunicipal;
import com.trascender.framework.recurso.filtros.FiltroLocalidad;
import com.trascender.framework.recurso.filtros.FiltroMunicipalidad;
import com.trascender.framework.recurso.filtros.FiltroPais;
import com.trascender.framework.recurso.filtros.FiltroPersonaFisica;
import com.trascender.framework.recurso.filtros.FiltroPersonaJuridica;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.filtros.FiltroProcesoDB;
import com.trascender.framework.recurso.filtros.FiltroProvincia;
import com.trascender.framework.recurso.filtros.FiltroRol;
import com.trascender.framework.recurso.filtros.FiltroSecretaria;
import com.trascender.framework.recurso.filtros.FiltroUsuario;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.system.interfaces.SystemAuxiliarAuditoria;
import com.trascender.framework.system.interfaces.SystemContrato;
import com.trascender.framework.system.interfaces.SystemMunicipalidad;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.system.interfaces.SystemPersonaFisica;
import com.trascender.framework.system.interfaces.SystemPersonaJuridica;
import com.trascender.framework.system.interfaces.SystemRol;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;
import com.trascender.habilitaciones.system.interfaces.SystemPeriodo;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.abstracts.TablaBusquedaPorLogs;
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
public class ComunicationBean extends AbstractSessionBean {
	// desc="Creator-managed Component Definition">

	private int __placeholder;

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {
	}

	Properties props = null;
	Context ctx = null;

	/**
	 * <p>
	 * Construir una instancia de bean de datos de la sesi�n.
	 * </p>
	 */
	public ComunicationBean() {
		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "localhost:1099");

		// nuevo probando
		// props.put(Context.INITIAL_CONTEXT_FACTORY,
		// "org.jnp.interfaces.NamingContextFactory");
		// props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		// props.put(Context.PROVIDER_URL, "127.0.0.1:1099");

		// final Hashtable jndiProperties = new Hashtable();
		// jndiProperties.put(Context.URL_PKG_PREFIXES,
		// "org.jboss.ejb.client.naming");
		// jndiProperties.put(Context.PROVIDER_URL, "127.0.0.1:4447");
		// jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
		// "org.jnp.interfaces.NamingContextFactory");
		// return jndiProperties;

		try {
			ctx = new InitialContext(props);
			this.remoteSystemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
			this.remoteSystemMunicipalidad = (SystemMunicipalidad) ctx.lookup(SystemMunicipalidad.JNDI_NAME);
			this.remoteSystemPersonaFisica = (SystemPersonaFisica) ctx.lookup(SystemPersonaFisica.JNDI_NAME);
			this.remoteSystemPersonaJuridica = (SystemPersonaJuridica) ctx.lookup(SystemPersonaJuridica.JNDI_NAME);
			this.remoteSystemContrato = (SystemContrato) ctx.lookup(SystemContrato.JNDI_NAME);
			this.remoteSystemRol = (SystemRol) ctx.lookup(SystemRol.JNDI_NAME);
			this.remoteSystemParametro = (SystemParametro) ctx.lookup(SystemParametro.JNDI_NAME);
			this.remoteSystemPeriodo = (SystemPeriodo) ctx.lookup(SystemPeriodo.JNDI_NAME);
			this.remoteSystemAuxiliarAuditoria = (SystemAuxiliarAuditoria) ctx.lookup(SystemAuxiliarAuditoria.JNDI_NAME);

			System.out.println("ComunicationBean");
			// *****TABLAS**//
			FiltroPersonaFisica locFiltro = new FiltroPersonaFisica();
			locFiltro.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaPersonaFisica = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(PersonaFisica.serialVersionUID),
					"#{framework$ABMPersonaFisica$AdminPersonaFisica}", locFiltro);

			FiltroArea locFiltroArea = new FiltroArea();
			locFiltroArea.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaArea = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Area.serialVersionUID), "#{framework$ABMArea$AdminArea}", locFiltroArea);

			FiltroSecretaria locFiltroSecretaria = new FiltroSecretaria();
			locFiltroSecretaria.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaSecretaria = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Secretaria.serialVersionUID), "#{framework$ABMSecretaria$AdminSecretaria}",
					locFiltroSecretaria);

			FiltroPlantillaAtributosDinamicos locFiltroAtributo = new FiltroPlantillaAtributosDinamicos();
			locFiltroAtributo.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaAtributoDinamico = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(PlantillaAtributoDinamico.serialVersionUID),
					"#{framework$ABMAtributoDinamico$AdminAtributoDinamico}", locFiltroAtributo);

			FiltroCodigoCiiu locFiltroCodigoCIIU = new FiltroCodigoCiiu();
			locFiltroCodigoCIIU.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaCodigoCIIU = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(CodigoCiiu.serialVersionUID), "#{framework$ABMCodigoCiiu$AdminCodigoCiiu}",
					locFiltroCodigoCIIU);

			FiltroConfiguracionRecurso locFiltroCR = new FiltroConfiguracionRecurso();
			locFiltroCR.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaConfiguracionRecurso = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ConfiguracionRecurso.serialVersionUID),
					"#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso}", locFiltroCR);

			FiltroContrato locFiltroContrato = new FiltroContrato();
			locFiltroContrato.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaContrato = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Contrato.serialVersionUID), "#{framework$ABMContrato$AdminContrato}",
					locFiltroContrato);

			FiltroDiaFeriado locFiltroDiaFeriado = new FiltroDiaFeriado();
			locFiltroDiaFeriado.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDiaFeriado = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DiaFeriado.serialVersionUID), "#{framework$ABMDiaFeriado$AdminDiaFeriado}",
					locFiltroDiaFeriado);

			FiltroDigestoMunicipal locFiltroDigesto = new FiltroDigestoMunicipal();
			locFiltroDigesto.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDigestoMunicipal = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DigestoMunicipal.serialVersionUID),
					"#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal}", locFiltroDigesto);

			FiltroLocalidad locFiltroLocalidad = new FiltroLocalidad();
			locFiltroLocalidad.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLocalidad = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Localidad.serialVersionUID), "#{framework$ABMLocalidad$AdminLocalidad}",
					locFiltroLocalidad);

			FiltroPais locFiltroPais = new FiltroPais();
			locFiltroPais.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaPais = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Pais.serialVersionUID), "#{framework$ABMPais$AdminPais}", locFiltroPais);

			FiltroPersonaJuridica locFiltroJuridica = new FiltroPersonaJuridica();
			locFiltroJuridica.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaPersonaJuridica = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(PersonaJuridica.serialVersionUID),
					"#{framework$ABMPersonaJuridica$AdminPersonaJuridica}", locFiltroJuridica);

			FiltroProvincia locFiltroProvincia = new FiltroProvincia();
			locFiltroProvincia.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaProvincia = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Provincia.serialVersionUID), "#{framework$ABMProvincia$AdminProvincia}",
					locFiltroProvincia);

			FiltroRol locFiltroRol = new FiltroRol();
			locFiltroRol.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaRol = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Rol.serialVersionUID), "#{framework$ABMRol$AdminRol}", locFiltroRol);

			FiltroUsuario locFiltroUsuario = new FiltroUsuario();
			locFiltroUsuario.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaUsuario = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Usuario.serialVersionUID), "#{framework$ABMUsuario$AdminUsuario}", locFiltroUsuario);

			FiltroMunicipalidad locFiltroMunicipalidad = new FiltroMunicipalidad();
			locFiltroMunicipalidad.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaMunicipalidad = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Municipalidad.serialVersionUID),
					"#{framework$ABMMunicipalidad$AdminMunicipalidad}", locFiltroMunicipalidad);

			FiltroCalendarioMunicipal locFiltroCalendario = new FiltroCalendarioMunicipal();
			locFiltroCalendario.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaCalendarioMunicipal = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Calendario.serialVersionUID),
					"#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal}", locFiltroCalendario);

			// FiltroReportesJasper locFiltroReporteJasper = new FiltroReportesJasper();
			// locFiltroReporteJasper.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			// this.tablaReporteJasper = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ReportesJasper.serialVersionUID),
			// "#{framework$ABMReporteJasper$AdminReporteJasper}", locFiltroReporteJasper);
			
			FiltroProcesoDB locFiltroDB = new FiltroProcesoDB();
			locFiltroDB.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaProcesoDB = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ProcesoDB.serialVersionUID)
					, "#{framework$ABMProcesoDB$AdminProcesoDB}", locFiltroDB);

		} catch(Exception ex) {
			// Logger.getLogger(ComunicationBean.class.getName()).log(Level.SEVERE,
			// null, ex);
			ex.printStackTrace();
		}

	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
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
	 * Se llama a este m�todo al agregar este bean al �mbito de la sesi�n. Normalmente, esto ocurre como resultado de la evaluaci�n de una expresi�n de enlace
	 * de valores o de m�todos, que utiliza la funci�n de bean administrado para crear una instancia de este bean y almacenarla en el �mbito de la sesi�n.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este m�todo para inicializar y almacenar en cach� los valores o recursos necesarios para el ciclo de duraci�n de una sesi�n de usuario
	 * en particular.
	 * </p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicaci�n que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

		// desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("SessionBeanABM Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// Realizar inicio de aplicaci�n que debe finalizar
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
	@Override
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
	@Override
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
	@Override
	public void destroy() {
	}

	/**
	 * Definición de la interfaz remota SystemUsuario, para invocar la lógica de negocio.
	 */
	// @EJB
	private SystemUsuario remoteSystemUsuario = null;

	public SystemUsuario getRemoteSystemUsuario() {
		// try {
		// // Context ctx = new InitialContext(props);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try{
		//
		// if (this.remoteSystemUsuario==null){
		// Context ctx = new InitialContext(props);
		// Object obj = ctx.lookup(SystemUsuarioHome.JNDI_NAME);
		// SystemUsuarioHome locUsuarioHome = (SystemUsuarioHome)
		// PortableRemoteObject.narrow(obj, SystemUsuarioHome.class);
		// this.remoteSystemUsuario = locUsuarioHome.create();
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		// return this.remoteSystemUsuario;
		// final Hashtable jndiProperties = new Hashtable();
		// jndiProperties.put(Context.URL_PKG_PREFIXES,
		// "org.jboss.ejb.client.naming");
		// jndiProperties.put(Context.PROVIDER_URL, "127.0.0.1:1099");
		// jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
		// "org.jnp.interfaces.NamingContextFactory");
		// try{
		// Context ctx = new InitialContext(jndiProperties);
		// return (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return null;
		return this.remoteSystemUsuario;
	}

	public void setRemoteSystemUsuario(SystemUsuario pRemoteSystemUsuario) {
		this.remoteSystemUsuario = pRemoteSystemUsuario;
	}

	/**
	 * Definicion de la interfaz remota SystemMunicipalidad, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemMunicipalidad remoteSystemMunicipalidad = null;

	public SystemMunicipalidad getRemoteSystemMunicipalidad() {
		try {
			remoteSystemMunicipalidad.setLlave(getSessionBean1().getLlave());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return this.remoteSystemMunicipalidad;
	}

	// @EJB
	private SystemParametro remoteSystemParametro = null;

	public SystemParametro getRemoteSystemParametro() {

		try {
			// Context ctx = new InitialContext(props);
		} catch(Exception e) {
			e.printStackTrace();
		}
		// try{
		//
		// if (this.remoteSystemMunicipalidad==null){
		// Context ctx = new InitialContext(props);
		// Object obj = ctx.lookup(SystemMunicipalidadHome.JNDI_NAME);
		// SystemMunicipalidadHome locMunicipalidadHome =
		// (SystemMunicipalidadHome) PortableRemoteObject.narrow(obj,
		// SystemMunicipalidadHome.class);
		// this.remoteSystemMunicipalidad = locMunicipalidadHome.create();
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		this.remoteSystemParametro.setLlave(this.getSessionBean1().getLlave());
		return this.remoteSystemParametro;
	}

	public void setRemoteSystemParametro(SystemParametro pRemoteSystemParametro) {
		this.remoteSystemParametro = pRemoteSystemParametro;
	}

	/**
	 * Definición de la interfaz remota SystemPersonaFisica, para invocar la lógica de negocio.
	 */
	// @EJB
	private SystemPersonaFisica remoteSystemPersonaFisica = null;

	public SystemPersonaFisica getRemoteSystemPersonaFisica() {
		try {
			// Context ctx = new InitialContext(props);
		} catch(Exception e) {
			e.printStackTrace();
		}
		// try{
		// if (this.remoteSystemPersonaFisica==null){
		// Context ctx = new InitialContext(props);
		// Object obj = ctx.lookup(SystemPersonaFisicaHome.JNDI_NAME);
		// SystemPersonaFisicaHome locPersonaFisicaHome =
		// (SystemPersonaFisicaHome) PortableRemoteObject.narrow(obj,
		// SystemPersonaFisicaHome.class);
		// this.remoteSystemPersonaFisica = locPersonaFisicaHome.create();
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		return this.remoteSystemPersonaFisica;
	}

	public void setRemoteSystemPersonaFisica(SystemPersonaFisica pRemoteSystemPersonaFisica) {
		this.remoteSystemPersonaFisica = pRemoteSystemPersonaFisica;
	}

	/**
	 * Definición de la interfaz remota SystemPersonaJuridica, para invocar la lógica de negocio.
	 */
	// @EJB
	private SystemPersonaJuridica remoteSystemPersonaJuridica = null;

	public SystemPersonaJuridica getRemoteSystemPersonaJuridica() {
		try {
			// Context ctx = new InitialContext(props);
		} catch(Exception e) {
			e.printStackTrace();
		}

		// try{
		// if (this.remoteSystemPersonaJuridica==null){
		// Context ctx = new InitialContext(props);
		// Object obj = ctx.lookup(SystemPersonaJuridicaHome.JNDI_NAME);
		// SystemPersonaJuridicaHome locPersonaJuridicaHome =
		// (SystemPersonaJuridicaHome) PortableRemoteObject.narrow(obj,
		// SystemPersonaJuridicaHome.class);
		// this.remoteSystemPersonaJuridica = locPersonaJuridicaHome.create();
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		return this.remoteSystemPersonaJuridica;
	}

	public void setRemoteSystemPersonaJuridica(SystemPersonaJuridica pRemoteSystemPersonaJuridica) {
		this.remoteSystemPersonaJuridica = pRemoteSystemPersonaJuridica;
	}

	/**
	 * Definición de la interfaz remota SystemPersonaFisica, para invocar la lógica de negocio.
	 */
	// @EJB
	private SystemRol remoteSystemRol = null;

	public SystemRol getRemoteSystemRol() {
		try {
			// Context ctx = new InitialContext(props);
		} catch(Exception e) {
			e.printStackTrace();
		}
		// try{
		// if (this.remoteSystemRol==null){
		// Context ctx = new InitialContext(props);
		// Object obj = ctx.lookup(SystemRolHome.JNDI_NAME);
		// SystemRolHome locRolHome = (SystemRolHome)
		// PortableRemoteObject.narrow(obj, SystemRolHome.class);
		// this.remoteSystemRol = locRolHome.create();
		// }
		// }catch(Exception e){Periodo
		// e.printStackTrace();
		// }
		return this.remoteSystemRol;
	}

	public void setRemoteSystemRol(SystemRol pRemoteSystemRol) {
		this.remoteSystemRol = pRemoteSystemRol;
	}

	private SystemPeriodo remoteSystemPeriodo = null;

	public SystemPeriodo getRemoteSystemPeriodo() {
		return remoteSystemPeriodo;
	}

	public void setRemoteSystemPeriodo(SystemPeriodo remoteSystemPeriodo) {
		this.remoteSystemPeriodo = remoteSystemPeriodo;
	}

	private SystemAuxiliarAuditoria remoteSystemAuxiliarAuditoria = null;

	public SystemAuxiliarAuditoria getRemoteSystemAuxiliarAuditoria() {
		return remoteSystemAuxiliarAuditoria;
	}

	public void setRemoteSystemAuxiliarAuditoria(SystemAuxiliarAuditoria remoteSystemAuxiliarAuditoria) {
		this.remoteSystemAuxiliarAuditoria = remoteSystemAuxiliarAuditoria;
	}

	/**
	 * Sección de declaración de Listas que completan las listas
	 * 
	 */

	// Lista original de logs de los ABM...
	private List listaLogs = null;

	public List getListaLogs() {
		return listaLogs;
	}

	public void setListaLogs(List listaLogs) {
		this.listaLogs = listaLogs;
	}
	
	private List listaProcesosDB = null;
	
	public List getListaProcesosDB() {
		return listaProcesosDB;
	}

	public void setListaProcesosDB(List listaProcesosDB) {
		this.listaProcesosDB = listaProcesosDB;
	}

	/**
	 * Código extra de comunicación RMI
	 */
	private List listaPersonasFisicas = null;

	public List getListaPersonasFisicas() {
		return this.listaPersonasFisicas;
	}

	public void setListaPersonasFisicas(List pListaPersonasFisicas) {
		this.listaPersonasFisicas = pListaPersonasFisicas;
	}

	private List listaDigestoConcordancia = null;

	public List getListaDigestoConcordancia() {
		return this.listaDigestoConcordancia;
	}

	public void setListaDigestoConcordancia(List pLista) {
		this.listaDigestoConcordancia = pLista;
	}

	private List listaAtributosDinamicos = null;

	public List getListaAtributosDinamicos() {
		return this.listaAtributosDinamicos;
	}

	public void setListaAtributosDinamicos(List pListaAtributosDinamicos) {
		this.listaAtributosDinamicos = pListaAtributosDinamicos;
	}

	private List listaReportesJasper = null;

	public List getListaReportesJasper() {
		return listaReportesJasper;
	}

	public void setListaReportesJasper(List listaReportesJasper) {
		this.listaReportesJasper = listaReportesJasper;
	}

	private ArrayList listaAtributosDinamicosVehiculo = null;

	public ArrayList getListaAtributosDinamicosVehiculo() {
		return listaAtributosDinamicosVehiculo;
	}

	public void setListaAtributosDinamicosVehiculo(ArrayList listaAtributosDinamicosVehiculo) {
		this.listaAtributosDinamicosVehiculo = listaAtributosDinamicosVehiculo;
	}

	private ArrayList listaAtributosDinamicosTipoVehiculo = null;

	public ArrayList getListaAtributosDinamicosTipoVehiculo() {
		return listaAtributosDinamicosTipoVehiculo;
	}

	public void setListaAtributosDinamicosTipoVehiculo(ArrayList listaAtributosDinamicosTipoVehiculo) {
		this.listaAtributosDinamicosTipoVehiculo = listaAtributosDinamicosTipoVehiculo;
	}

	private ArrayList listaAtributosDinamicosModelo = null;

	public ArrayList getListaAtributosDinamicosModelo() {
		return listaAtributosDinamicosModelo;
	}

	public void setListaAtributosDinamicosModelo(ArrayList listaAtributosDinamicosModelo) {
		this.listaAtributosDinamicosModelo = listaAtributosDinamicosModelo;
	}

	private ArrayList listaAtributosDinamicosMarcas = null;

	public ArrayList getListaAtributosDinamicosMarca() {
		return listaAtributosDinamicosMarcas;
	}

	public void setListaAtributosDinamicosMarca(ArrayList listaAtributosDinamicosMarca) {
		this.listaAtributosDinamicosMarcas = listaAtributosDinamicosMarcas;
	}

	private List listaAtributosDinamicosPersonasFisicas = null;

	public List getListaAtributosDinamicosPersonasFisicas() {
		return listaAtributosDinamicosPersonasFisicas;
	}

	public void setListaAtributosDinamicosPersonasFisicas(List listaAtributosDinamicosPersonasFisicas) {
		this.listaAtributosDinamicosPersonasFisicas = listaAtributosDinamicosPersonasFisicas;
	}

	private ArrayList listaAtributosDinamicosPersonasJuridicas = null;

	public ArrayList getListaAtributosDinamicosPersonasJuridicas() {
		return listaAtributosDinamicosPersonasJuridicas;
	}

	public void setListaAtributosDinamicosPersonasJuridicas(ArrayList listaAtributosDinamicosPersonasJuridicas) {
		this.listaAtributosDinamicosPersonasJuridicas = listaAtributosDinamicosPersonasJuridicas;
	}

	private List listaDatosPlantillaObligacionTasaMenor = null;

	public List getListaDatosPlantillaObligacionTasaMenor() {
		return listaDatosPlantillaObligacionTasaMenor;
	}

	public void setListaDatosPlantillaObligacionTasaMenor(List listaDatosPlantillaObligacionTasaMenor) {
		this.listaDatosPlantillaObligacionTasaMenor = listaDatosPlantillaObligacionTasaMenor;
	}

	private List listaDatosRegValuadoPlantillaObligacionTasaMenor = null;

	public List getListaDatosRegValuadoPlantillaObligacionTasaMenor() {
		return listaDatosRegValuadoPlantillaObligacionTasaMenor;
	}

	public void setListaDatosRegValuadoPlantillaObligacionTasaMenor(List listaDatosRegValuadoPlantillaObligacionTasaMenor) {
		this.listaDatosRegValuadoPlantillaObligacionTasaMenor = listaDatosRegValuadoPlantillaObligacionTasaMenor;
	}

	private ArrayList listaPaises = null;

	public ArrayList getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(ArrayList listaPaises) {
		this.listaPaises = listaPaises;
	}

	private ArrayList listaUsuarios = null;

	public ArrayList getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	private ArrayList listaProvincias = null;

	public ArrayList getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(ArrayList listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	private List listaLocalidades = null;

	public List getListaLocalidades() {
		return listaLocalidades;
	}

	private TableSelectPhaseListener tspPersonasJuridicas;

	public TableSelectPhaseListener getTspPersonasJuridicas() {
		return tspPersonasJuridicas;
	}

	public void setTspPersonasJuridicas(TableSelectPhaseListener tspPersonasJuridicas) {
		this.tspPersonasJuridicas = tspPersonasJuridicas;
	}

	private TableSelectPhaseListener tsplPersonasFisicas = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTsplPersonasFisicas() {
		return tsplPersonasFisicas;
	}

	public void setTsplPersonasFisicas(TableSelectPhaseListener tsplPersonasFisicas) {
		this.tsplPersonasFisicas = tsplPersonasFisicas;
	}

	public void setListaLocalidades(List listaLocalidades) {
		this.listaLocalidades = listaLocalidades;
	}

	private List listaPersonasJuridicas = null;

	public List getListaPersonasJuridicas() {
		return listaPersonasJuridicas;
	}

	public void setListaPersonasJuridicas(List listaPersonasJuridicas) {
		this.listaPersonasJuridicas = listaPersonasJuridicas;
	}

	private ArrayList listaSocios = null;

	public ArrayList getListaSocios() {
		return listaSocios;
	}

	public void setListaSocios(ArrayList listaSocios) {
		this.listaSocios = listaSocios;
	}

	private List listaRoles = null;

	public List getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List listaRoles) {
		this.listaRoles = listaRoles;
	}

	private List listaConfiguracionRecursos = null;

	public List getListaConfiguracionRecursos() {
		return listaConfiguracionRecursos;
	}

	public void setListaConfiguracionRecursos(List listaRecursos) {
		this.listaConfiguracionRecursos = listaRecursos;
	}

	private List listaConjAtrTabConfRec;
	
	public List getListaConjAtrTabConfRec() {
		return listaConjAtrTabConfRec;
	}

	public void setListaConjAtrTabConfRec(List listaConjAtrTabConfRec) {
		this.listaConjAtrTabConfRec = listaConjAtrTabConfRec;
	}

	private List listaAtrTabConfiguracionRecurso;
	
	public List getListaAtrTabConfiguracionRecurso() {
		return listaAtrTabConfiguracionRecurso;
	}

	public void setListaAtrTabConfiguracionRecurso(List listaAtrTabConfiguracionRecurso) {
		this.listaAtrTabConfiguracionRecurso = listaAtrTabConfiguracionRecurso;
	}

	private List listaUsuariosConfiguracionRecurso;

	public List getListaUsuariosConfiguracionRecurso() {
		return listaUsuariosConfiguracionRecurso;
	}

	public void setListaUsuariosConfiguracionRecurso(List listaUsuariosConfiguracionRecurso) {
		this.listaUsuariosConfiguracionRecurso = listaUsuariosConfiguracionRecurso;
	}

	private List listaPropiedadesConfiguracionRecurso;

	public List getListaPropiedadesConfiguracionRecurso() {
		return listaPropiedadesConfiguracionRecurso;
	}

	public void setListaPropiedadesConfiguracionRecurso(List listaPropiedadesConfiguracionRecurso) {
		this.listaPropiedadesConfiguracionRecurso = listaPropiedadesConfiguracionRecurso;
	}

	private ArrayList listaRecursos = null;

	public ArrayList getListaRecursos() {
		return listaRecursos;
	}

	public void setListaRecursos(ArrayList listaRecursos) {
		this.listaRecursos = listaRecursos;
	}

	private List listaSecretarias = null;

	public List getListaSecretarias() {
		return listaSecretarias;
	}

	public void setListaSecretarias(List listaSecretarias) {
		this.listaSecretarias = listaSecretarias;
	}

	private List listaAreas = null;

	public List getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(List listaAreas) {
		this.listaAreas = listaAreas;
	}

	private ArrayList listaAreasUsuarios;

	public ArrayList getListaAreasUsuarios() {
		return listaAreasUsuarios;
	}

	public void setListaAreasUsuarios(ArrayList listaAreasUsuarios) {
		this.listaAreasUsuarios = listaAreasUsuarios;
	}

	private List listaAreasSecretaria = null;

	public List getListaAreasSecretaria() {
		return listaAreasSecretaria;
	}

	public void setListaAreasSecretaria(List listaAreasSecretaria) {
		this.listaAreasSecretaria = listaAreasSecretaria;
	}

	private ArrayList listaMunicipalidades = null;

	public ArrayList getListaMunicipalidades() {
		return listaMunicipalidades;
	}

	public void setListaMunicipalidades(ArrayList listaMunicipalidades) {
		this.listaMunicipalidades = listaMunicipalidades;
	}

	private ArrayList listaPermisos = null;

	public ArrayList getListaPermisos() {
		return listaPermisos;
	}

	public void setListaPermisos(ArrayList listaPermisos) {
		this.listaPermisos = listaPermisos;
	}

	private List listaContratos = null;

	public List getListaContratos() {
		return listaContratos;
	}

	public void setListaContratos(List listaContratos) {
		this.listaContratos = listaContratos;
	}

	/**
	 * Conserva el valor de la propiedad listaRolesUsuario.
	 */
	private ArrayList listaRolesUsuario;

	/**
	 * Getter para propiedad listaRolesUsuario.
	 * 
	 * @return Valor de la propiedad listaRolesUsuario.
	 */
	public ArrayList getListaRolesUsuario() {

		return this.listaRolesUsuario;
	}

	/**
	 * Setter para propiedad listaRolesUsuario.
	 * 
	 * @param listaRolesUsuario
	 *            Nuevo valor de la propiedad listaRolesUsuario.
	 */
	public void setListaRolesUsuario(ArrayList listaRolesUsuario) {

		this.listaRolesUsuario = listaRolesUsuario;
	}

	/**
	 * Conserva el valor de la propiedad listaPlantillaDocumentoTasaMenor.
	 */
	private List listaPlantillaDocumentoTasaMenor;

	/**
	 * Getter para propiedad listaPlantillaDocumentoTasaMenor.
	 * 
	 * @return Valor de la propiedad listaPlantillaDocumentoTasaMenor.
	 */
	public List getListaPlantillaDocumentoTasaMenor() {

		return this.listaPlantillaDocumentoTasaMenor;
	}

	/**
	 * Setter para propiedad listaPlantillaDocumentoTasaMenor.
	 * 
	 * @param listaPlantillaDocumentoTasaMenor
	 *            Nuevo valor de la propiedad listaPlantillaDocumentoTasaMenor.
	 */
	public void setListaPlantillaDocumentoTasaMenor(List listaPlantillaDocumentoTasaMenor) {

		this.listaPlantillaDocumentoTasaMenor = listaPlantillaDocumentoTasaMenor;
	}

	/**
	 * Conserva el valor de la propiedad listaPlantillaDocumentoTasaMenor.
	 */
	private List listaDatosListadoAtributoDinamico;

	/**
	 * Getter para propiedad listaDatosListadoAtributoDinamico.
	 * 
	 * @return Valor de la propiedad listaDatosListadoAtributoDinamico.
	 */
	public List getListaDatosListadoAtributoDinamico() {

		return this.listaDatosListadoAtributoDinamico;
	}

	/**
	 * Setter para propiedad listaDatosListadoAtributoDinamico.
	 * 
	 * @param listaDatosListadoAtributoDinamico
	 *            Nuevo valor de la propiedad listaDatosListadoAtributoDinamico.
	 */
	public void setListaDatosListadoAtributoDinamico(List listaDatosListadoAtributoDinamico) {

		this.listaDatosListadoAtributoDinamico = listaDatosListadoAtributoDinamico;
	}

	/**
	 * Conserva el valor de la propiedad listaDiasFeriados.
	 */
	private ArrayList listaDiasFeriados;

	/**
	 * Getter para propiedad listaDiasFeriados.
	 * 
	 * @return Valor de la propiedad listaDiasFeriados.
	 */
	public ArrayList getListaDiasFeriados() {

		return this.listaDiasFeriados;
	}

	/**
	 * Setter para propiedad listaDiasFeriados.
	 * 
	 * @param listaDiasFeriados
	 *            Nuevo valor de la propiedad listaDiasFeriados.
	 */
	public void setListaDiasFeriados(ArrayList listaDiasFeriados) {

		this.listaDiasFeriados = listaDiasFeriados;
	}

	/**
	 * Conserva el valor de la propiedad listaDigestosMunicipales.
	 */
	private ArrayList listaDigestosMunicipales;

	/**
	 * Getter para propiedad listaDigestosMunicipales.
	 * 
	 * @return Valor de la propiedad listaDigestosMunicipales.
	 */
	public ArrayList getListaDigestosMunicipales() {

		return this.listaDigestosMunicipales;
	}

	/**
	 * Setter para propiedad listaDigestosMunicipales.
	 * 
	 * @param listaDigestosMunicipales
	 *            Nuevo valor de la propiedad listaDigestosMunicipales.
	 */
	public void setListaDigestosMunicipales(ArrayList listaDigestosMunicipales) {

		this.listaDigestosMunicipales = listaDigestosMunicipales;
	}

	/**
	 * Conserva el valor de la propiedad listaTemas.
	 */
	private ArrayList listaTemas;

	/**
	 * Getter para propiedad listaTemas.
	 * 
	 * @return Valor de la propiedad listaTemas.
	 */
	public ArrayList getListaTemas() {

		return this.listaTemas;
	}

	/**
	 * Setter para propiedad listaTemas.
	 * 
	 * @param listaTemas
	 *            Nuevo valor de la propiedad listaTemas.
	 */
	public void setListaTemas(ArrayList listaTemas) {

		this.listaTemas = listaTemas;
	}

	/**
	 * Marcos Definición de la interfaz remota SystemContratoLocal, para invocar la lógica de negocio.
	 */
	// @EJB
	private SystemContrato remoteSystemContrato = null;

	public SystemContrato getRemoteSystemContrato() {

		try {
			// Context ctx = new InitialContext(props);
		} catch(Exception e) {
			e.printStackTrace();
		}
		// try{
		//
		// if (this.remoteSystemContrato==null){
		// Context ctx = new InitialContext(props);
		// Object obj = ctx.lookup(SystemContratoHome.JNDI_NAME);
		// SystemContratoHome locContratoHome = (SystemContratoHome)
		// PortableRemoteObject.narrow(obj, SystemContratoHome.class);
		// this.remoteSystemContrato = locContratoHome.create();
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		return this.remoteSystemContrato;
	}

	public void setRemoteSystemContratoLocal(SystemContrato pRemoteSystemContratoLocal) {
		this.remoteSystemContrato = pRemoteSystemContratoLocal;
	}

	private List listaCodigosCiiu;

	public List getListaCodigosCiiu() {
		return listaCodigosCiiu;
	}

	public void setListaCodigosCiiu(List listaCodigosCiiu) {
		this.listaCodigosCiiu = listaCodigosCiiu;
	}

	private Map<String, GrupoCiiu> mapaGruposCiiu;

	private Map<String, SeccionCiiu> mapaSeccionesCiiu;

	public Map<String, GrupoCiiu> getMapaGruposCiiu() {
		return mapaGruposCiiu;
	}

	public void setMapaGruposCiiu(Map<String, GrupoCiiu> mapaGruposCiiu) {
		this.mapaGruposCiiu = mapaGruposCiiu;
	}

	public Map<String, SeccionCiiu> getMapaSeccionesCiiu() {
		return mapaSeccionesCiiu;
	}

	public void setMapaSeccionesCiiu(Map<String, SeccionCiiu> mapaSeccionesCiiu) {
		this.mapaSeccionesCiiu = mapaSeccionesCiiu;
	}

	// ***** PaginatedTables *****

	private PaginatedTable tablaPersonaFisica;
	private PaginatedTable tablaPersonaJuridica;
	private PaginatedTable tablaArea;
	private PaginatedTable tablaSecretaria;
	private PaginatedTable tablaAtributoDinamico;
	private PaginatedTable tablaCodigoCIIU;
	private PaginatedTable tablaConfiguracionRecurso;
	private PaginatedTable tablaContrato;
	private PaginatedTable tablaDiaFeriado;
	private PaginatedTable tablaDigestoMunicipal;
	private PaginatedTable tablaLocalidad;
	private PaginatedTable tablaMunicipalidad;
	private PaginatedTable tablaPais;
	private PaginatedTable tablaProvincia;
	private PaginatedTable tablaRol;
	private PaginatedTable tablaUsuario;
	private PaginatedTable tablaCalendarioMunicipal;
	private PaginatedTable tablaReporteJasper;
	private PaginatedTable tablaProcesoDB;
	
	public PaginatedTable getTablaProcesoDB() {
		return tablaProcesoDB;
	}

	public void setTablaProcesoDB(PaginatedTable tablaProcesoDB) {
		this.tablaProcesoDB = tablaProcesoDB;
	}

	public PaginatedTable getTablaReporteJasper() {
		return tablaReporteJasper;
	}

	public void setTablaReporteJasper(PaginatedTable tablaReporteJasper) {
		this.tablaReporteJasper = tablaReporteJasper;
	}

	public PaginatedTable getTablaCalendarioMunicipal() {
		return tablaCalendarioMunicipal;
	}

	public void setTablaCalendarioMunicipal(PaginatedTable tablaCalendarioMunicipal) {
		this.tablaCalendarioMunicipal = tablaCalendarioMunicipal;
	}

	public PaginatedTable getTablaArea() {
		return tablaArea;
	}

	public void setTablaArea(PaginatedTable tablaArea) {
		this.tablaArea = tablaArea;
	}

	public PaginatedTable getTablaSecretaria() {
		return tablaSecretaria;
	}

	public void setTablaSecretaria(PaginatedTable tablaSecretaria) {
		this.tablaSecretaria = tablaSecretaria;
	}

	public PaginatedTable getTablaAtributoDinamico() {
		return tablaAtributoDinamico;
	}

	public void setTablaAtributoDinamico(PaginatedTable tablaAtributoDinamico) {
		this.tablaAtributoDinamico = tablaAtributoDinamico;
	}

	public PaginatedTable getTablaCodigoCIIU() {
		return tablaCodigoCIIU;
	}

	public void setTablaCodigoCIIU(PaginatedTable tablaCodigoCIIU) {
		this.tablaCodigoCIIU = tablaCodigoCIIU;
	}

	public PaginatedTable getTablaConfiguracionRecurso() {
		return tablaConfiguracionRecurso;
	}

	public void setTablaConfiguracionRecurso(PaginatedTable tablaConfiguracionRecurso) {
		this.tablaConfiguracionRecurso = tablaConfiguracionRecurso;
	}

	public PaginatedTable getTablaContrato() {
		return tablaContrato;
	}

	public void setTablaContrato(PaginatedTable tablaContrato) {
		this.tablaContrato = tablaContrato;
	}

	public PaginatedTable getTablaDiaFeriado() {
		return tablaDiaFeriado;
	}

	public void setTablaDiaFeriado(PaginatedTable tablaDiaFeriado) {
		this.tablaDiaFeriado = tablaDiaFeriado;
	}

	public PaginatedTable getTablaDigestoMunicipal() {
		return tablaDigestoMunicipal;
	}

	public void setTablaDigestoMunicipal(PaginatedTable tablaDigestoMunicipal) {
		this.tablaDigestoMunicipal = tablaDigestoMunicipal;
	}

	public PaginatedTable getTablaLocalidad() {
		return tablaLocalidad;
	}

	public void setTablaLocalidad(PaginatedTable tablaLocalidad) {
		this.tablaLocalidad = tablaLocalidad;
	}

	public PaginatedTable getTablaMunicipalidad() {
		return tablaMunicipalidad;
	}

	public void setTablaMunicipalidad(PaginatedTable tablaMunicipalidad) {
		this.tablaMunicipalidad = tablaMunicipalidad;
	}

	public PaginatedTable getTablaPais() {
		return tablaPais;
	}

	public void setTablaPais(PaginatedTable tablaPais) {
		this.tablaPais = tablaPais;
	}

	public PaginatedTable getTablaPersonaJuridica() {
		return tablaPersonaJuridica;
	}

	public void setTablaPersonaJuridica(PaginatedTable tablaPersonaJuridica) {
		this.tablaPersonaJuridica = tablaPersonaJuridica;
	}

	public PaginatedTable getTablaProvincia() {
		return tablaProvincia;
	}

	public void setTablaProvincia(PaginatedTable tablaProvincia) {
		this.tablaProvincia = tablaProvincia;
	}

	public PaginatedTable getTablaRol() {
		return tablaRol;
	}

	public void setTablaRol(PaginatedTable tablaRol) {
		this.tablaRol = tablaRol;
	}

	public PaginatedTable getTablaUsuario() {
		return tablaUsuario;
	}

	public void setTablaUsuario(PaginatedTable tablaUsuario) {
		this.tablaUsuario = tablaUsuario;
	}

	public PaginatedTable getTablaPersonaFisica() {
		return tablaPersonaFisica;
	}

	public void setTablaPersonaFisica(PaginatedTable tablaPersonaFisica) {
		this.tablaPersonaFisica = tablaPersonaFisica;
	}

	private Map<String, Area> mapaAreasUsuario;

	/**
	 * @return Un HashMap que contiene las Areas del Usuario logueado, sin repetir y cuya llave es el nombre del Area. Ideal para usar en DropdsDowns.
	 */
	public Map<String, Area> getMapaAreasUsuario() {
		if(this.mapaAreasUsuario == null) {
			this.mapaAreasUsuario = this.armarMapaAreasUsuario();
		}
		return mapaAreasUsuario;
	}

	public void setMapaAreasUsuario(Map<String, Area> mapaAreasUsuario) {
		this.mapaAreasUsuario = mapaAreasUsuario;
	}

	/**
	 * Arma el mapa de Areas del usuario logueado. Si el usuario es root, usa todas las areas del sistema.
	 */
	public Map<String, Area> armarMapaAreasUsuario() {
		Map<String, Area> locMapa = new HashMap<String, Area>();
		try {
			Usuario usuarioLogueado = getRemoteSystemUsuario().findUsuarioPorLlave(this.getSessionBean1().getLlave());
			if(usuarioLogueado.getUser().equalsIgnoreCase("root")) {
				locMapa = this.armarMapaAreasTodas();
			} else {
				for(Area cadaArea : usuarioLogueado.getListaAreas()) {
					locMapa.put(cadaArea.getNombre(), cadaArea);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return locMapa;
	}

	private List listaCalendariosMunicipales;

	public List getListaCalendariosMunicipales() {
		return listaCalendariosMunicipales;
	}

	public void setListaCalendariosMunicipales(List listaCalendariosMunicipales) {
		this.listaCalendariosMunicipales = listaCalendariosMunicipales;
	}

	private SortedSet<Periodo> listaPeriodos;

	public SortedSet<Periodo> getListaPeriodos() {
		return listaPeriodos;
	}

	public void setListaPeriodos(SortedSet<Periodo> listaPeriodos) {
		this.listaPeriodos = listaPeriodos;
	}

	private SortedSet<CuotaLiquidacion> listaCuotas;

	public SortedSet<CuotaLiquidacion> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(SortedSet<CuotaLiquidacion> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	private SortedSet<Calendar> listaVencimientos;

	public SortedSet<Calendar> getListaVencimientos() {
		return listaVencimientos;
	}

	public void setListaVencimientos(SortedSet<Calendar> listaVencimientos) {
		this.listaVencimientos = listaVencimientos;
	}

	private Map<String, Area> mapaAreas;

	public Map<String, Area> getMapaAreas() {
		if(this.mapaAreas == null) {
			this.mapaAreas = this.armarMapaAreasTodas();
		}
		return mapaAreas;
	}

	public void setMapaAreas(Map<String, Area> mapaAreas) {
		this.mapaAreas = mapaAreas;
	}

	public Map<String, Area> armarMapaAreasTodas() {
		Map<String, Area> locMapa = new TreeMap<String, Area>();
		try {
			this.getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
			List<Area> locListaAreas = this.getRemoteSystemMunicipalidad().findAreasSinPermisoSelect(new FiltroArea()).getListaResultados();
			Collections.sort(locListaAreas, new Comparator<Area>() {
				@Override
				public int compare(Area o1, Area o2) {
					String objeto1 = Util.reemplazarAcentos(o1.getNombre());
					String objeto2 = Util.reemplazarAcentos(o2.getNombre());
					return objeto1.compareToIgnoreCase(objeto2);
				}
			});
			for(Area cadaArea : locListaAreas) {
				locMapa.put(cadaArea.getNombre(), cadaArea);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return locMapa;
	}

	private List<String> listaNombresPropiedadesLogs;

	public List<String> getListaNombresPropiedadesLogs(long pSerialVersionUID) {
		this.listaNombresPropiedadesLogs = this.getRemoteSystemParametro().getListaNombresPropiedadesLogs(pSerialVersionUID);
		return listaNombresPropiedadesLogs;
	}

	public void setListaNombresPropiedadesLogs(List<String> listaNombresPropiedadesLogs) {
		this.listaNombresPropiedadesLogs = listaNombresPropiedadesLogs;
	}

	private Map<String, Usuario> mapaUsuariosLogs;

	public Map<String, Usuario> getMapaUsuariosLogs(long pSerialVersionUID) {
		this.mapaUsuariosLogs = this.getRemoteSystemParametro().getMapaUsuariosLogs(pSerialVersionUID);
		return mapaUsuariosLogs;
	}

	public void setMapaUsuariosLogs(Map<String, Usuario> mapaUsuariosLogs) {
		this.mapaUsuariosLogs = mapaUsuariosLogs;
	}

	private Map<Long, TablaBusquedaPorLogs> mapaTablasLogs;

	public Map<Long, TablaBusquedaPorLogs> getMapaTablasLogs() {
		if(this.mapaTablasLogs == null)
			this.mapaTablasLogs = new HashMap<Long, TablaBusquedaPorLogs>();
		return mapaTablasLogs;
	}

	public void setMapaTablasLogs(Map<Long, TablaBusquedaPorLogs> mapaTablasLogs) {
		this.mapaTablasLogs = mapaTablasLogs;
	}

	private Map<Long, Checkbox> mapaCkbBuscarPorLogs;

	public Map<Long, Checkbox> getMapaCkbBuscarPorLogs() {
		if(this.mapaCkbBuscarPorLogs == null)
			this.mapaCkbBuscarPorLogs = new HashMap<Long, Checkbox>();
		return mapaCkbBuscarPorLogs;
	}

	public void setMapaCkbBuscarPorLogs(Map<Long, Checkbox> mapaCkbBuscarPorLogs) {
		this.mapaCkbBuscarPorLogs = mapaCkbBuscarPorLogs;
	}

}
