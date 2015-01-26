/*
 * CommunicationSAICBean.java
 *
 * Created on 30 de enero de 2007, 14:26
 * Copyright Asus
 */

package muni;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import muni.saic.ImprimirLiquidaciones.ModificarVarias;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.habilitaciones.recurso.filtros.FiltroExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencionNumeroCuota;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;
import com.trascender.habilitaciones.system.interfaces.SystemPeriodo;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.saic.recurso.filtros.FiltroCobroExterno;
import com.trascender.saic.recurso.filtros.FiltroDDJJSHPS;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionAutomotor;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionCementerio;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionOSP;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionPFO;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionSHPS;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTGI;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaMenor;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaRefer;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.filtros.FiltroValorMedidor;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.CobroExterno;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.system.interfaces.SystemAuditoriaTributaria;
import com.trascender.saic.system.interfaces.SystemEstadoCuentaContribuyente;
import com.trascender.saic.system.interfaces.SystemExencionRegistroDeuda;
import com.trascender.saic.system.interfaces.SystemImpresion;
import com.trascender.saic.system.interfaces.SystemLiquidacionTasa;
import com.trascender.saic.system.interfaces.SystemRegistroValuado;
import com.trascender.saic.system.interfaces.SystemReliquidacion;

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
public class CommunicationSAICBean extends AbstractSessionBean {

	Properties props = null;
	Context ctx = null;

	// desc="Creator-managed Component Definition">

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
	 * Construir una instancia de bean de datos de la sesi�n.
	 * </p>
	 */
	public CommunicationSAICBean() {
		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "localhost:1099");
		try {
			ctx = new InitialContext(props);
			this.remoteSystemAuditoriaTributaria = (SystemAuditoriaTributaria) ctx.lookup(SystemAuditoriaTributaria.JNDI_NAME);
			this.remoteSystemRegistroValuado = (SystemRegistroValuado) ctx.lookup(SystemRegistroValuado.JNDI_NAME);
			this.remoteSystemLiquidacionTasa = (SystemLiquidacionTasa) ctx.lookup(SystemLiquidacionTasa.JNDI_NAME);
			this.remoteSystemEstadoCuentaContribuyente = (SystemEstadoCuentaContribuyente) ctx.lookup(SystemEstadoCuentaContribuyente.JNDI_NAME);
			this.remoteSystemReliquidacion = (SystemReliquidacion) ctx.lookup(SystemReliquidacion.JNDI_NAME);
			this.remoteSystemImpresion = (SystemImpresion) ctx.lookup(SystemImpresion.JNDI_NAME);
			this.remoteSystemExencion = (SystemExencionRegistroDeuda) ctx.lookup(SystemExencionRegistroDeuda.JNDI_NAME);
			this.remoteSystemPeriodo = (SystemPeriodo) ctx.lookup(SystemPeriodo.JNDI_NAME);

			System.out.println("CommunicationSAICBean");
			FiltroLiquidacionTasaRefer locFiltroLiquidacionTasaRefer = new FiltroLiquidacionTasaRefer();
			locFiltroLiquidacionTasaRefer.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionTasaRefer = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionTasa.codigoTasasUnificadas),
					"#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones}", locFiltroLiquidacionTasaRefer);

			FiltroDDJJSHPS locFiltroDDJJSHPS = new FiltroDDJJSHPS();
			locFiltroDDJJSHPS.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDDJJSHPS = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DeclaracionJuradaSHPS.serialVersionUID),
					"#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS}", locFiltroDDJJSHPS);

			FiltroValorMedidor locFiltroValorMedidor = new FiltroValorMedidor();
			locFiltroValorMedidor.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaValorMedidor = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ValorMedidor.serialVersionUID),
					"#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor}", locFiltroValorMedidor);

			FiltroExencionObligacion locFiltroExencion = new FiltroExencionObligacion();
			locFiltroExencion.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaExencionObligacion = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ExencionObligacion.serialVersionUID),
					"#{saic$ABMExencionObligacion$AdminExencionObligacion}", locFiltroExencion);

			FiltroLiquidacionTGI locFiltroLiquidacionTGI = new FiltroLiquidacionTGI();
			locFiltroLiquidacionTGI.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionTGI = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionTasa.codigoTGI),
					"#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI}", locFiltroLiquidacionTGI);

			FiltroLiquidacionSHPS locFiltroLiquidacionSHPS = new FiltroLiquidacionSHPS();
			locFiltroLiquidacionSHPS.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionSHPS = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionTasa.codigoSHPS),
					"#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS}", locFiltroLiquidacionSHPS);

			FiltroLiquidacionOSP locFiltroLiquidacionOSP = new FiltroLiquidacionOSP();
			locFiltroLiquidacionOSP.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionOSP = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionTasa.codigoOSP),
					"#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP}", locFiltroLiquidacionOSP);

			FiltroLiquidacionPFO locFiltroLiquidacionPFO = new FiltroLiquidacionPFO();
			locFiltroLiquidacionPFO.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionPFO = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionTasa.codigoPFO),
					"#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO}", locFiltroLiquidacionPFO);

			FiltroLiquidacionCementerio locFiltroLiquidacionCementerio = new FiltroLiquidacionCementerio();
			locFiltroLiquidacionCementerio.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionCementerio = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionTasa.codigoCementerio),
					"#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio}", locFiltroLiquidacionCementerio);

			FiltroLiquidacionAutomotor locFiltroLiquidacionAutomotor = new FiltroLiquidacionAutomotor();
			locFiltroLiquidacionAutomotor.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionAutomotor = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionTasa.codigoAutomotor),
					"#{saic$grpAutomotor$ABMLiquidacionAutomotor$AdminLiquidacionAutomotor}", locFiltroLiquidacionAutomotor);

			FiltroLiquidacionTasaMenor locFiltroLiquidacionTasaMenor = new FiltroLiquidacionTasaMenor();
			locFiltroLiquidacionTasaMenor.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionTasaMenor = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionTasa.codigoTasaMenor),
					"#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor}", locFiltroLiquidacionTasaMenor);
			
			FiltroLogLiquidacion locFiltroLogLiquidacion = new FiltroLogLiquidacion();
			locFiltroLogLiquidacion.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLogLiquidaciones = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LogLiquidacion.serialVersionUID),
					"#{saic$LogLiquidaciones$AdminLogLiquidaciones}", locFiltroLogLiquidacion);

			FiltroCobroExterno locFiltroCobroExterno = new FiltroCobroExterno();
			locFiltroCobroExterno.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaCobroExterno = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(CobroExterno.serialVersionUID), "#{saic$ABMCobroExterno$AdminCobroExterno}",
					locFiltroCobroExterno);

		} catch(Exception ex) {
			// Logger.getLogger(ComunicationBean.class.getName()).log(Level.SEVERE,
			// null, ex);
			ex.printStackTrace();
		}
	}

	private muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	public muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
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

		// <editor-fold defaultstate="collapsed"
		// desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados autom�ticamente
		// *Nota* - esta l�gica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("CommunicationSAICBean Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
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

	//
	// Interfaces
	//
	// <editor-fold defaultstate="collapsed" desc="SystemRegistroValuado">
	/**
	 * Definicion de la interfaz remota SystemRegistroValuado, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemRegistroValuado remoteSystemRegistroValuado = null;

	public SystemRegistroValuado getRemoteSystemRegistroValuado() {

		// try{
		// this.remoteSystemRegistroValuado =
		// (SystemRegistroValuado)ctx.lookup(SystemRegistroValuado.JNDI_NAME);

		/*
		 * if (this.remoteSystemRegistroValuado==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemRegistroValuadoHome.JNDI_NAME);
		 * SystemRegistroValuadoHome locSystemRegistroValuadoHome = (SystemRegistroValuadoHome) PortableRemoteObject.narrow(obj,
		 * SystemRegistroValuadoHome.class); this.remoteSystemRegistroValuado = locSystemRegistroValuadoHome.create(); }
		 */
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		return this.remoteSystemRegistroValuado;
	}

	public void setRemoteSystemRegistroValuado(SystemRegistroValuado pRemoteSystemRegistroValuado) {
		this.remoteSystemRegistroValuado = pRemoteSystemRegistroValuado;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemAuditoriaTributaria">
	/**
	 * Definicion de la interfaz remota SystemRegistroValuado, para invocar la logica de negocio.
	 */
	// @EJB

	private SystemPeriodo remoteSystemPeriodo = null;

	public SystemPeriodo getRemoteSystemPeriodo() {
		return this.remoteSystemPeriodo;
	}

	public void setRemoteSystemPeriodo(SystemPeriodo pRemoteSystemPeriodo) {
		this.remoteSystemPeriodo = pRemoteSystemPeriodo;
	}

	private SystemAuditoriaTributaria remoteSystemAuditoriaTributaria = null;

	public SystemAuditoriaTributaria getRemoteSystemAuditoriaTributaria() {

		// try{
		// this.remoteSystemRegistroValuado =
		// (SystemRegistroValuado)ctx.lookup(SystemRegistroValuado.JNDI_NAME);

		/*
		 * if (this.remoteSystemRegistroValuado==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemRegistroValuadoHome.JNDI_NAME);
		 * SystemRegistroValuadoHome locSystemRegistroValuadoHome = (SystemRegistroValuadoHome) PortableRemoteObject.narrow(obj,
		 * SystemRegistroValuadoHome.class); this.remoteSystemRegistroValuado = locSystemRegistroValuadoHome.create(); }
		 */
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		return this.remoteSystemAuditoriaTributaria;
	}

	public void setRemoteSystemAuditoriaTributaria(SystemAuditoriaTributaria pRemoteSystemAuditoriaTributaria) {
		this.remoteSystemAuditoriaTributaria = pRemoteSystemAuditoriaTributaria;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemLiquidacionTasa">
	/**
	 * Definicion de la interfaz remota SystemLiquidacionTasa, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemLiquidacionTasa remoteSystemLiquidacionTasa = null;

	public SystemLiquidacionTasa getRemoteSystemLiquidacionTasa() {
		/*
		 * try{ if (this.remoteSystemLiquidacionTasa==null){ Context ctx = new InitialContext(props); Object obj =
		 * ctx.lookup(SystemLiquidacionTasaHome.JNDI_NAME); SystemLiquidacionTasaHome locSystemLiquidacionTasaHome = (SystemLiquidacionTasaHome)
		 * PortableRemoteObject.narrow(obj, SystemLiquidacionTasaHome.class); this.remoteSystemLiquidacionTasa = locSystemLiquidacionTasaHome.create(); }
		 * }catch(Exception e){ e.printStackTrace(); }
		 */
		return this.remoteSystemLiquidacionTasa;
	}

	public void setRemoteSystemLiquidacionTasa(SystemLiquidacionTasa pRemoteSystemLiquidacionTasa) {
		this.remoteSystemLiquidacionTasa = pRemoteSystemLiquidacionTasa;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed"
	// desc="SystemEstadoCuentaContribuyente">
	/**
	 * Definicion de la interfaz remota SystemEstadoCuentaContribuyente, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemEstadoCuentaContribuyente remoteSystemEstadoCuentaContribuyente = null;

	public SystemEstadoCuentaContribuyente getRemoteSystemEstadoCuentaContribuyente() {
		try {
			/*
			 * try{ if (this.remoteSystemEstadoCuentaContribuyente==null){ Context ctx = new InitialContext(props); Object obj =
			 * ctx.lookup(SystemEstadoCuentaContribuyenteHome.JNDI_NAME); SystemEstadoCuentaContribuyenteHome locSystemEstadoCuentaContribuyenteHome =
			 * (SystemEstadoCuentaContribuyenteHome) PortableRemoteObject.narrow(obj, SystemEstadoCuentaContribuyenteHome.class);
			 * this.remoteSystemEstadoCuentaContribuyente = locSystemEstadoCuentaContribuyenteHome.create(); } }catch(Exception e){ e.printStackTrace(); }
			 */
			this.remoteSystemEstadoCuentaContribuyente.setLlave(this.getSessionBean1().getLlave());
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
		return this.remoteSystemEstadoCuentaContribuyente;
	}

	public void setRemoteSystemEstadoCuentaContribuyente(SystemEstadoCuentaContribuyente pRemoteSystemEstadoCuentaContribuyente) {
		this.remoteSystemEstadoCuentaContribuyente = pRemoteSystemEstadoCuentaContribuyente;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemReliquidacion">
	/**
	 * Definicion de la interfaz remota SystemReliquidacion, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemReliquidacion remoteSystemReliquidacion = null;

	public SystemReliquidacion getRemoteSystemReliquidacion() {
		/*
		 * try{ if (this.remoteSystemReliquidacion==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemReliquidacionHome.JNDI_NAME);
		 * SystemReliquidacionHome locSystemReliquidacionHome = (SystemReliquidacionHome) PortableRemoteObject.narrow(obj, SystemReliquidacionHome.class);
		 * this.remoteSystemReliquidacion = locSystemReliquidacionHome.create(); } }catch(Exception e){ e.printStackTrace(); }
		 */
		return this.remoteSystemReliquidacion;
	}

	public void setRemoteSystemReliquidacion(SystemReliquidacion pRemoteSystemReliquidacion) {
		this.remoteSystemReliquidacion = pRemoteSystemReliquidacion;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemImpresion">
	/**
	 * Definicion de la interfaz remota SystemImpresion, para invocar la logica de negocio.
	 */
	// @EJB
	private SystemImpresion remoteSystemImpresion = null;

	public SystemImpresion getRemoteSystemImpresion() {
		/*
		 * try{ if (this.remoteSystemImpresion==null){ Context ctx = new InitialContext(props); Object obj = ctx.lookup(SystemImpresionHome.JNDI_NAME);
		 * SystemImpresionHome locSystemImpresionHome = (SystemImpresionHome) PortableRemoteObject.narrow(obj, SystemImpresionHome.class);
		 * this.remoteSystemImpresion = locSystemImpresionHome.create(); } }catch(Exception e){ e.printStackTrace(); }
		 */
		return this.remoteSystemImpresion;
	}

	public void setRemoteSystemImpresion(SystemImpresion pRemoteSystemImpresion) {
		this.remoteSystemImpresion = pRemoteSystemImpresion;
	}

	// </editor-fold>
	/**
	 * Definicion de la interfaz remota SystemRegistroValuado, para invocar la logica de negocio.
	 */
	private SystemExencionRegistroDeuda remoteSystemExencion = null;

	public SystemExencionRegistroDeuda getRemoteSystemExencion() {
		/*
		 * try{ if (this.remoteSystemExencion==null){ Context ctx = new InitialContext(props); Object obj =
		 * ctx.lookup(SystemExencionRegistroDeudaHome.JNDI_NAME); SystemExencionRegistroDeudaHome locSystemExencionHome = (SystemExencionRegistroDeudaHome)
		 * PortableRemoteObject.narrow(obj, SystemExencionRegistroDeudaHome.class); this.remoteSystemExencion = locSystemExencionHome.create(); }
		 * }catch(Exception e){ e.printStackTrace(); }
		 */
		return this.remoteSystemExencion;
	}

	public void setRemoteSystemExencion(SystemExencionRegistroDeuda pRemoteSystemExencion) {
		this.remoteSystemExencion = pRemoteSystemExencion;
	}

	//
	// ArrayLists
	//
	/**
	 * Conserva el valor de Exención
	 */
	private ArrayList listaExencion;

	/**
	 * Getter para propiedad listaExencion.
	 * 
	 * @return Valor de la listaExencion
	 */
	public ArrayList getListaExencion() {

		return this.listaExencion;
	}

	/**
	 * Setter para propiedad Exencion.
	 * 
	 * @param listaExencion
	 *            Nuevo valor de la Lista de Exenciones
	 */
	public void setListaExencion(ArrayList listaExencion) {
		this.listaExencion = listaExencion;
	}

	/**
	 * Conserva el valor de Registros de Deuda de una Exencion
	 */
	private ArrayList listaExencionRegistroDeuda;

	/**
	 * Getter para propiedad listaExencionRegistroDeuda.
	 * 
	 * @return Valor de la listaExencionRegistroDeuda
	 */
	public ArrayList getListaExencionRegistroDeuda() {

		return this.listaExencionRegistroDeuda;
	}

	/**
	 * Setter para propiedad ExencionRegistroDeuda.
	 * 
	 * @param listaExencionRegistroDeuda
	 *            Nuevo valor de la Lista de Registros de Deuda de Exenciones
	 */
	public void setListaExencionRegistroDeuda(ArrayList listaExencionRegistroDeuda) {
		this.listaExencionRegistroDeuda = listaExencionRegistroDeuda;
	}

	/**
	 * Conserva el valor de Registros de Deuda de una Obligacion
	 */
	private List listaExencionObligacion;

	/**
	 * Getter para propiedad listaExencionObligacion.
	 * 
	 * @return Valor de la listaExencionObligacion
	 */
	public List getListaExencionObligacion() {

		return this.listaExencionObligacion;
	}

	/**
	 * Setter para propiedad ExencionObligacion.
	 * 
	 * @param listaExencionRegistroDeuda
	 *            Nuevo valor de la Lista de Obligacion de una Exencion
	 */
	public void setListaExencionObligacion(List listaExencionObligacion) {
		this.listaExencionObligacion = listaExencionObligacion;
	}

	/**
	 * Conserva el valor de Firmas de Exenciones
	 */
	private ArrayList listaExencionFirmas;

	/**
	 * Getter para propiedad listaExencionObligacion.
	 * 
	 * @return Valor de la listaExencionObligacion
	 */
	public ArrayList getListaExencionFirmas() {

		return this.listaExencionFirmas;
	}

	/**
	 * Setter para propiedad ExencionObligacion.
	 * 
	 * @param listaExencionRegistroDeuda
	 *            Nuevo valor de la Lista de Obligacion de una Exencion
	 */
	public void setListaExencionFirmas(ArrayList listaExencionFirmas) {
		this.listaExencionFirmas = listaExencionFirmas;
	}

	private List listaParametrosReliquidacion;

	public List getListaParametrosReliquidacion() {
		return listaParametrosReliquidacion;
	}

	public void setListaParametrosReliquidacion(List listaParametrosReliquidacion) {
		this.listaParametrosReliquidacion = listaParametrosReliquidacion;
	}

	private List listaParametrosReliquidacionVarias;

	public List getListaParametrosReliquidacionVarias() {
		return listaParametrosReliquidacionVarias;
	}

	public void setListaParametrosReliquidacionVarias(List listaParametrosReliquidacionVarias) {
		this.listaParametrosReliquidacionVarias = listaParametrosReliquidacionVarias;
	}

	/**
	 * 
	 * 
	 /** Conserva el valor de Registro de Deuda.
	 */
	private ArrayList listaRegistroDeudaContribuyente;

	/**
	 * Getter para propiedad listaRegistroDeudaContribuyente.
	 * 
	 * @return Valor del Registro Deuda listaDeclaracionesJuradasSHPS.
	 */
	public ArrayList getListaRegistroDeudaContribuyente() {

		return this.listaRegistroDeudaContribuyente;
	}

	/**
	 * Setter para propiedad RegistroDeudaContribuyente.
	 * 
	 * @param listaRegistroDeudaContribuyente
	 *            Nuevo valor de el registro de deuda.
	 */
	public void setListaRegistroDeudaContribuyente(ArrayList listaRegistroDeudaContribuyente) {

		this.listaRegistroDeudaContribuyente = listaRegistroDeudaContribuyente;
	}

	/**
	 * Conserva el valor de Registro Valuado Tasa Menor.
	 */
	private ArrayList listaRegistroValuadoTasaMenor;

	/**
	 * Getter para propiedad listaRegistroValuadoTasaMenor.
	 * 
	 * @return Valor del Registro Valuado Tasa Menor RegistroValuadoTasaMenor.
	 */
	public ArrayList getListaRegistroValuadoTasaMenor() {

		return this.listaRegistroValuadoTasaMenor;
	}

	/**
	 * Setter para propiedad listaRegistroValuadoTasaMenor.
	 * 
	 * @param listaRegistroValuadoTasaMenor
	 *            Nuevo valor de el registro de deuda.
	 */
	public void setListaRegistroValuadoTasaMenor(ArrayList listaRegistroValuadoTasaMenor) {

		this.listaRegistroValuadoTasaMenor = listaRegistroValuadoTasaMenor;
	}

	/**
	 * Conserva el valor del Registro de Deuda.
	 */
	private List listaDeclaracionesJuradasSHPS;

	/**
	 * Getter para propiedad listaDeclaracionesJuradasSHPS.
	 * 
	 * @return Valor de la propiedad listaDeclaracionesJuradasSHPS.
	 */
	public List getListaDeclaracionesJuradasSHPS() {

		return this.listaDeclaracionesJuradasSHPS;
	}

	/**
	 * Setter para propiedad listaDeclaracionesJuradasSHPS.
	 * 
	 * @param listaDeclaracionesJuradasSHPS
	 *            Nuevo valor de la propiedad listaDeclaracionesJuradasSHPS.
	 */
	public void setListaDeclaracionesJuradasSHPS(List listaDeclaracionesJuradasSHPS) {

		this.listaDeclaracionesJuradasSHPS = listaDeclaracionesJuradasSHPS;
	}

	/**
	 * Conserva el valor de la propiedad listaMontosImponiblesDeclarados.
	 */
	private List listaMontosImponiblesDeclarados = null;

	/**
	 * Getter para propiedad listaMontosImponiblesDeclarados.
	 * 
	 * @return Valor de la propiedad listaMontosImponiblesDeclarados.
	 */
	public List getListaMontosImponiblesDeclarados() {

		return this.listaMontosImponiblesDeclarados;
	}

	/**
	 * Setter para propiedad listaMontosImponiblesDeclarados.
	 * 
	 * @param listaMontosImponiblesDeclarados
	 *            Nuevo valor de la propiedad listaMontosImponiblesDeclarados.
	 */
	public void setListaMontosImponiblesDeclarados(List listaMontosImponiblesDeclarados) {

		this.listaMontosImponiblesDeclarados = listaMontosImponiblesDeclarados;
	}

	/**
	 * Conserva el valor de AuditoriaTributaria
	 */
	private ArrayList listaAuditoriaTributaria;

	/**
	 * Getter para propiedad listaAuditoriaTributaria.
	 * 
	 * @return Valor de la listaAuditoriaTributaria
	 */
	public ArrayList getListaAuditoriaTributaria() {

		return this.listaAuditoriaTributaria;
	}

	/**
	 * Setter para propiedad AuditoriaTributaria.
	 * 
	 * @param listaAuditoriaTributaria
	 *            Nuevo valor de la Lista de AuditoriaTributaria
	 */
	public void setListaAuditoriaTributaria(ArrayList listaAuditoriaTributarian) {
		this.listaAuditoriaTributaria = listaAuditoriaTributarian;
	}

	/**
	 * Conserva el valor de IntimacionesAuditoriaTributaria
	 */
	private ArrayList listaIntimacionesAuditoriaTributaria;

	/**
	 * Getter para propiedad listaIntimacionesAuditoriaTributaria.
	 * 
	 * @return Valor de la listaIntimacionesAuditoriaTributaria
	 */
	public ArrayList getListaIntimacionesAuditoriaTributaria() {

		return this.listaIntimacionesAuditoriaTributaria;
	}

	/**
	 * Setter para propiedad IntimacionesAuditoriaTributaria.
	 * 
	 * @param listaIntimacionesAuditoriaTributaria
	 *            Nuevo valor de la Lista de AuditoriaTributaria
	 */
	public void setListaIntimacionesAuditoriaTributaria(ArrayList listaIntimacionesAuditoriaTributaria) {
		this.listaIntimacionesAuditoriaTributaria = listaIntimacionesAuditoriaTributaria;
	}

	/**
	 * Conserva el valor de LogAuditoriaTributaria
	 */
	private ArrayList listaLogAuditoriaTributaria;

	/**
	 * Getter para propiedad listaLogAuditoriaTributaria.
	 * 
	 * @return Valor de la listaLogAuditoriaTributaria
	 */
	public ArrayList getListaLogAuditoriaTributaria() {

		return this.listaLogAuditoriaTributaria;
	}

	/**
	 * Setter para propiedad LogAuditoriaTributaria.
	 * 
	 * @param listaLogAuditoriaTributaria
	 *            Nuevo valor de la Lista de AuditoriaTributaria
	 */
	public void setListaLogAuditoriaTributaria(ArrayList listaLogAuditoriaTributaria) {
		this.listaLogAuditoriaTributaria = listaLogAuditoriaTributaria;
	}

	/**
	 * Conserva el valor de CuotasRefinanciacion
	 */
	private ArrayList listaCuotasRefinanciacion;

	/**
	 * Getter para propiedad listaCuotasRefinanciacion.
	 * 
	 * @return Valor de la listaCuotasRefinanciacion
	 */
	public ArrayList getListaCuotasRefinanciacion() {

		return this.listaCuotasRefinanciacion;
	}

	/**
	 * Setter para propiedad AuditoriaTributaria.
	 * 
	 * @param listaAuditoriaTributaria
	 *            Nuevo valor de la Lista de AuditoriaTributaria
	 */
	public void setListaCuotasRefinanciacion(ArrayList listaCuotasRefinanciacion) {
		this.listaCuotasRefinanciacion = listaCuotasRefinanciacion;
	}

	/**
	 * Getter para propiedad listaLiquidacionesSHPS.
	 * 
	 * @return Valor de la propiedad listaLiquidacionesSHPS.
	 */
	public List getListaLiquidacionesSHPS() {

		return this.listaLiquidacionesSHPS;
	}

	/**
	 * Setter para propiedad listaLiquidacionesSHPS.
	 * 
	 * @param listaLiquidacionesSHPS
	 *            Nuevo valor de la propiedad listaLiquidacionesSHPS.
	 */
	public void setListaLiquidacionesSHPS(List listaLiquidacionesSHPS) {

		this.listaLiquidacionesSHPS = listaLiquidacionesSHPS;
	}

	public List getListaLiquidacionesAutomotor() {
		return listaLiquidacionesAutomotor;
	}

	public void setListaLiquidacionesAutomotor(List listaLiquidacionesAutomotor) {
		this.listaLiquidacionesAutomotor = listaLiquidacionesAutomotor;
	}

	public List getListaLiquidacionesCementerio() {
		return listaLiquidacionesCementerio;
	}

	public void setListaLiquidacionesCementerio(List listaLiquidacionesCementerio) {
		this.listaLiquidacionesCementerio = listaLiquidacionesCementerio;
	}

	/**
	 * Getter para propiedad listaLiquidacionesTasaMenor.
	 * 
	 * @return Valor de la propiedad listaLiquidacionesTasaMenor.
	 */
	public List getListaLiquidacionesTasaMenor() {

		return this.listaLiquidacionesTasaMenor;
	}

	/**
	 * Setter para propiedad listaLiquidacionesTasaMenor.
	 * 
	 * @param listaLiquidacionesTasaMenor
	 *            Nuevo valor de la propiedad listaLiquidacionesTasaMenor.
	 */
	public void setListaLiquidacionesTasaMenor(List listaLiquidacionesTasaMenor) {

		this.listaLiquidacionesTasaMenor = listaLiquidacionesTasaMenor;
	}

	/**
	 * Getter para propiedad listaLiquidacionesOSP.
	 * 
	 * @return Valor de la propiedad listaLiquidacionesOSP.
	 */
	public List getListaLiquidacionesOSP() {

		return this.listaLiquidacionesOSP;
	}

	/**
	 * Setter para propiedad listaLiquidacionesOSP.
	 * 
	 * @param listaLiquidacionesOSP
	 *            Nuevo valor de la propiedad listaLiquidacionesOSP.
	 */
	public void setListaLiquidacionesOSP(List listaLiquidacionesOSP) {
		this.listaLiquidacionesOSP = listaLiquidacionesOSP;
	}

	/**
	 * Conserva el valor de la propiedad listaValoresMedidoresOSP.
	 */
	private List listaValoresMedidoresOSP;

	/**
	 * Getter para propiedad listaValoresMedidoresOSP.
	 * 
	 * @return Valor de la propiedad listaValoresMedidoresOSP.
	 */
	public List getListaValoresMedidoresOSP() {

		return this.listaValoresMedidoresOSP;
	}

	/**
	 * Setter para propiedad listaValoresMedidoresOSP.
	 * 
	 * @param listaValoresMedidoresOSP
	 *            Nuevo valor de la propiedad listaValoresMedidoresOSP.
	 */
	public void setListaValoresMedidoresOSP(List listaValoresMedidoresOSP) {

		this.listaValoresMedidoresOSP = listaValoresMedidoresOSP;
	}

	/**
	 * Conserva el valor de la propiedad listaMedicionesMedidoresOSP.
	 */
	private ArrayList listaMedicionesMedidoresOSP;

	/**
	 * Getter para propiedad listaMedicionesMedidoresOSP.
	 * 
	 * @return Valor de la propiedad listaMedicionesMedidoresOSP.
	 */
	public ArrayList getListaMedicionesMedidoresOSP() {

		return this.listaMedicionesMedidoresOSP;
	}

	/**
	 * Setter para propiedad listaMedicionesMedidoresOSP.
	 * 
	 * @param listaMedicionesMedidoresOSP
	 *            Nuevo valor de la propiedad listaMedicionesMedidoresOSP.
	 */
	public void setListaMedicionesMedidoresOSP(ArrayList listaMedicionesMedidoresOSP) {

		this.listaMedicionesMedidoresOSP = listaMedicionesMedidoresOSP;
	}

	/**
	 * Conserva el valor de la propiedad listaLiquidacionesTGI.
	 */
	private List listaLiquidacionesTGI;
	private List listaLiquidacionesSHPS;
	private List listaLiquidacionesOSP;
	private List listaLiquidacionesPFO;
	private List listaLiquidacionesCementerio;
	private List listaLiquidacionesAutomotor;
	private List listaLiquidacionesTasaMenor;
	private List listaLiquidacionesTasaUnificada;
	private List listaCobroExterno;
	private List listaLogLiquidaciones;
	
	public List getListaLogLiquidaciones() {
		return listaLogLiquidaciones;
	}

	public void setListaLogLiquidaciones(List listaLogLiquidaciones) {
		this.listaLogLiquidaciones = listaLogLiquidaciones;
	}

	public List getListaCobroExterno() {
		return listaCobroExterno;
	}

	public void setListaCobroExterno(List listaCobroExterno) {
		this.listaCobroExterno = listaCobroExterno;
	}

	public List getListaLiquidacionesTasaUnificada() {
		return listaLiquidacionesTasaUnificada;
	}

	public void setListaLiquidacionesTasaUnificada(List listaLiquidacionesTasaUnificada) {
		this.listaLiquidacionesTasaUnificada = listaLiquidacionesTasaUnificada;
	}

	/**
	 * Getter para propiedad listaLiquidacionesTGI.
	 * 
	 * @return Valor de la propiedad listaLiquidacionesTGI.
	 */
	public List getListaLiquidacionesTGI() {

		return this.listaLiquidacionesTGI;
	}

	/**
	 * Setter para propiedad listaLiquidacionesTGI.
	 * 
	 * @param listaLiquidacionesTGI
	 *            Nuevo valor de la propiedad listaLiquidacionesTGI.
	 */
	public void setListaLiquidacionesTGI(List listaLiquidacionesTGI) {

		this.listaLiquidacionesTGI = listaLiquidacionesTGI;
	}

	/**
	 * Getter para propiedad listaLiquidacionesPFO.
	 * 
	 * @return Valor de la propiedad listaLiquidacionesPFO.
	 */
	public List getListaLiquidacionesPFO() {

		return this.listaLiquidacionesPFO;
	}

	/**
	 * Setter para propiedad listaLiquidacionesPFO.
	 * 
	 * @param listaLiquidacionesPFO
	 *            Nuevo valor de la propiedad listaLiquidacionesPFO.
	 */
	public void setListaLiquidacionesPFO(List listaLiquidacionesPFO) {

		this.listaLiquidacionesPFO = listaLiquidacionesPFO;
	}

	/**
	 * Conserva el valor de la propiedad listaObligacionesEstadoCuenta.
	 */
	private ArrayList listaObligacionesEstadoCuenta;

	/**
	 * Getter para propiedad listaObligacionesEstadoCuenta.
	 * 
	 * @return Valor de la propiedad listaObligacionesEstadoCuenta.
	 */
	public ArrayList getListaObligacionesEstadoCuenta() {

		return this.listaObligacionesEstadoCuenta;
	}

	/**
	 * Setter para propiedad listaObligacionesEstadoCuenta.
	 * 
	 * @param listaObligacionesEstadoCuenta
	 *            Nuevo valor de la propiedad listaObligacionesEstadoCuenta.
	 */
	public void setListaObligacionesEstadoCuenta(ArrayList listaObligacionesEstadoCuenta) {

		this.listaObligacionesEstadoCuenta = listaObligacionesEstadoCuenta;
	}

	/**
	 * Conserva el valor de la propiedad listaPeriodosAdeudadosEstadoCuenta.
	 */
	private ArrayList listaPeriodosAdeudadosEstadoCuenta;

	/**
	 * Getter para propiedad listaPeriodosAdeudadosEstadoCuenta.
	 * 
	 * @return Valor de la propiedad listaPeriodosAdeudadosEstadoCuenta.
	 */
	public ArrayList getListaPeriodosAdeudadosEstadoCuenta() {

		return this.listaPeriodosAdeudadosEstadoCuenta;
	}

	/**
	 * Setter para propiedad listaPeriodosAdeudadosEstadoCuenta.
	 * 
	 * @param listaPeriodosAdeudadosEstadoCuenta
	 *            Nuevo valor de la propiedad listaPeriodosAdeudadosEstadoCuenta.
	 */
	public void setListaPeriodosAdeudadosEstadoCuenta(ArrayList listaPeriodosAdeudadosEstadoCuenta) {

		this.listaPeriodosAdeudadosEstadoCuenta = listaPeriodosAdeudadosEstadoCuenta;
	}

	private Button btnCancelar;

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void desactivarButton(Button b) {

		b.setDisabled(true);
	}

	private List listaLiquidacionesImprimir;

	public List getListaLiquidacionesImprimir() {
		return listaLiquidacionesImprimir;
	}

	public void setListaLiquidacionesImprimir(List listaLiquidacionesImprimir) {
		this.listaLiquidacionesImprimir = listaLiquidacionesImprimir;
	}

	private PaginatedTable tablaLiquidacionTGI;
	private PaginatedTable tablaLiquidacionSHPS;
	private PaginatedTable tablaLiquidacionOSP;
	private PaginatedTable tablaLiquidacionPFO;
	private PaginatedTable tablaLiquidacionCementerio;
	private PaginatedTable tablaLiquidacionAutomotor;
	private PaginatedTable tablaLiquidacionTasaMenor;
	private PaginatedTable tablaCobroExterno;
	private PaginatedTable tablaLogLiquidaciones;
	
	public PaginatedTable getTablaLogLiquidaciones() {
		return tablaLogLiquidaciones;
	}

	public void setTablaLogLiquidaciones(PaginatedTable tablaLogLiquidaciones) {
		this.tablaLogLiquidaciones = tablaLogLiquidaciones;
	}

	public PaginatedTable getTablaCobroExterno() {
		return tablaCobroExterno;
	}

	public void setTablaCobroExterno(PaginatedTable tablaCobroExterno) {
		this.tablaCobroExterno = tablaCobroExterno;
	}

	public PaginatedTable getTablaLiquidacionTGI() {
		return tablaLiquidacionTGI;
	}

	public void setTablaLiquidacionTGI(PaginatedTable tablaLiquidacionTGI) {
		this.tablaLiquidacionTGI = tablaLiquidacionTGI;
	}

	public PaginatedTable getTablaLiquidacionSHPS() {
		return tablaLiquidacionSHPS;
	}

	public void setTablaLiquidacionSHPS(PaginatedTable tablaLiquidacionSHPS) {
		this.tablaLiquidacionSHPS = tablaLiquidacionSHPS;
	}

	public PaginatedTable getTablaLiquidacionOSP() {
		return tablaLiquidacionOSP;
	}

	public void setTablaLiquidacionOSP(PaginatedTable tablaLiquidacionOSP) {
		this.tablaLiquidacionOSP = tablaLiquidacionOSP;
	}

	public PaginatedTable getTablaLiquidacionPFO() {
		return tablaLiquidacionPFO;
	}

	public void setTablaLiquidacionPFO(PaginatedTable tablaLiquidacionPFO) {
		this.tablaLiquidacionPFO = tablaLiquidacionPFO;
	}

	public PaginatedTable getTablaLiquidacionCementerio() {
		return tablaLiquidacionCementerio;
	}

	public void setTablaLiquidacionCementerio(PaginatedTable tablaLiquidacionCementerio) {
		this.tablaLiquidacionCementerio = tablaLiquidacionCementerio;
	}

	public PaginatedTable getTablaLiquidacionAutomotor() {
		return tablaLiquidacionAutomotor;
	}

	public void setTablaLiquidacionAutomotor(PaginatedTable tablaLiquidacionAutomotor) {
		this.tablaLiquidacionAutomotor = tablaLiquidacionAutomotor;
	}

	public PaginatedTable getTablaLiquidacionTasaMenor() {
		return tablaLiquidacionTasaMenor;
	}

	public void setTablaLiquidacionTasaMenor(PaginatedTable tablaLiquidacionTasaMenor) {
		this.tablaLiquidacionTasaMenor = tablaLiquidacionTasaMenor;
	}

	private PaginatedTable tablaLiquidacionTasaRefer;

	public PaginatedTable getTablaLiquidacionTasaRefer() {
		return tablaLiquidacionTasaRefer;
	}

	public void setTablaLiquidacionTasaRefer(PaginatedTable tablaLiquidacionTasaRefer) {
		this.tablaLiquidacionTasaRefer = tablaLiquidacionTasaRefer;
	}

	private PaginatedTable tablaDDJJSHPS;

	public PaginatedTable getTablaDDJJSHPS() {
		return tablaDDJJSHPS;
	}

	public void setTablaDDJJSHPS(PaginatedTable tablaDDJJSHPS) {
		this.tablaDDJJSHPS = tablaDDJJSHPS;
	}

	private PaginatedTable tablaValorMedidor;

	public PaginatedTable getTablaValorMedidor() {
		return tablaValorMedidor;
	}

	public void setTablaValorMedidor(PaginatedTable tablaValorMedidor) {
		this.tablaValorMedidor = tablaValorMedidor;
	}

	private TableSelectPhaseListener tablePhaseListenerImprimirLiquidaciones = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerImprimirLiquidaciones() {
		return tablePhaseListenerImprimirLiquidaciones;
	}

	public void setTablePhaseListenerImprimirLiquidaciones(TableSelectPhaseListener tablePhaseListenerImprimirLiquidaciones) {
		this.tablePhaseListenerImprimirLiquidaciones = tablePhaseListenerImprimirLiquidaciones;
	}

	private List<LogLiquidacion> listaLogLiquidacion;

	public List<LogLiquidacion> getListaLogLiquidacion() {
		return listaLogLiquidacion;
	}

	public void setListaLogLiquidacion(List<LogLiquidacion> listaLogLiquidacion) {
		this.listaLogLiquidacion = listaLogLiquidacion;
	}

	private List<LiquidacionTasa> listaLiquidaciones;

	public List<LiquidacionTasa> getListaLiquidaciones() {
		return listaLiquidaciones;
	}

	public void setListaLiquidaciones(List<LiquidacionTasa> listaLiquidaciones) {
		this.listaLiquidaciones = listaLiquidaciones;
	}

	private LiquidacionTasa liquidacionSeleccionada;

	public LiquidacionTasa getLiquidacionSeleccionada() {
		return liquidacionSeleccionada;
	}

	public void setLiquidacionSeleccionada(LiquidacionTasa liquidacionSeleccionada) {
		this.liquidacionSeleccionada = liquidacionSeleccionada;
	}

	private PaginatedTable tablaExencionObligacion;

	public PaginatedTable getTablaExencionObligacion() {
		return tablaExencionObligacion;
	}

	public void setTablaExencionObligacion(PaginatedTable tablaExencionObligacion) {
		this.tablaExencionObligacion = tablaExencionObligacion;
	}

	private Map<String, Set> mapaModificadores;

	public Map<String, Set> getMapaModificadores() {
		return mapaModificadores;
	}

	public void setMapaModificadores(Map<String, Set> mapaModificadores) {
		this.mapaModificadores = mapaModificadores;
	}
	
	private List<AlicuotaLiquidada> listaAlicuotasLiquidadas;

	public List<AlicuotaLiquidada> getListaAlicuotasLiquidadas() {
		return listaAlicuotasLiquidadas;
	}

	public void setListaAlicuotasLiquidadas(List<AlicuotaLiquidada> listaAlicuotasLiquidadas) {
		this.listaAlicuotasLiquidadas = listaAlicuotasLiquidadas;
	}

	private Map<String, SortedSet> mapaVencimientos;

	public Map<String, SortedSet> getMapaVencimientos() {
		return mapaVencimientos;
	}

	public void setMapaVencimientos(Map<String, SortedSet> mapaVencimientos) {
		this.mapaVencimientos = mapaVencimientos;
	}

	private boolean seleccionMultipleImprimirLiquidaciones = false;

	public boolean isSeleccionMultipleImprimirLiquidaciones() {
		return seleccionMultipleImprimirLiquidaciones;
	}

	public void setSeleccionMultipleImprimirLiquidaciones(boolean seleccionMultipleImprimirLiquidaciones) {
		this.seleccionMultipleImprimirLiquidaciones = seleccionMultipleImprimirLiquidaciones;
	}
	
	private Set seleccionadosSeleccionMultipleNotificacion = new HashSet();
	
	public Set getSeleccionadosSeleccionMultipleNotificacion() {
		return seleccionadosSeleccionMultipleNotificacion;
	}

	public void setSeleccionadosSeleccionMultipleNotificacion(
			Set seleccionadosSeleccionMultipleNotificacion) {
		this.seleccionadosSeleccionMultipleNotificacion = seleccionadosSeleccionMultipleNotificacion;
	}

	private Set seleccionadosSeleccionMultipleImprimirLiquidaciones = new HashSet();

	public Set getSeleccionadosSeleccionMultipleImprimirLiquidaciones() {
		return seleccionadosSeleccionMultipleImprimirLiquidaciones;
	}

	public void setSeleccionadosSeleccionMultipleImprimirLiquidaciones(Set seleccionadosSeleccionMultipleImprimirLiquidaciones) {
		this.seleccionadosSeleccionMultipleImprimirLiquidaciones = seleccionadosSeleccionMultipleImprimirLiquidaciones;
	}

	private Set seleccionadosSeleccionMultipleActualizarDeuda = new HashSet();

	public Set getSeleccionadosSeleccionMultipleActualizarDeuda() {
		return seleccionadosSeleccionMultipleActualizarDeuda;
	}

	public void setSeleccionadosSeleccionMultipleActualizarDeuda(Set seleccionadosSeleccionMultipleActualizarDeuda) {
		this.seleccionadosSeleccionMultipleActualizarDeuda = seleccionadosSeleccionMultipleActualizarDeuda;
	}

	private List listaLiquidacionesActualizarDeuda = new ArrayList();

	public List getListaLiquidacionesActualizarDeuda() {
		return listaLiquidacionesActualizarDeuda;
	}

	public void setListaLiquidacionesActualizarDeuda(List listaLiquidacionesActualizarDeuda) {
		this.listaLiquidacionesActualizarDeuda = listaLiquidacionesActualizarDeuda;
	}
	
	private List listaLiquidacionesNotificar = new ArrayList();
	
	public List getListaLiquidacionesNotificar() {
		return listaLiquidacionesNotificar;
	}

	public void setListaLiquidacionesNotificar(List listaLiquidacionesNotificar) {
		this.listaLiquidacionesNotificar = listaLiquidacionesNotificar;
	}

	private muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	private Boolean esAdministradorLiquidaciones = null;

	public Boolean getEsAdministradorLiquidaciones(long pCodigo) {
		if(esAdministradorLiquidaciones == null) {
			try {
				this.getComunicationBean().getRemoteSystemUsuario().setLlave(this.getSessionBean1().getLlave());
				Usuario usuarioLogueado = this.getComunicationBean().getRemoteSystemUsuario().findUsuarioPorLlave(this.getSessionBean1().getLlave());
				boolean encontrado = false;
				if(!usuarioLogueado.getUser().equals("root")) {
					for(Iterator<Rol> itRol = usuarioLogueado.getListaRoles().iterator(); itRol.hasNext() && !encontrado;) {
						for(Iterator<Permiso> itPermiso = itRol.next().getListaPermisos().iterator(); itPermiso.hasNext() && !encontrado;) {
							Permiso cadaPermiso = itPermiso.next();
							if(cadaPermiso.getIdRecurso() == pCodigo && cadaPermiso.isAudith()) {
								encontrado = true;
							}
						}
					}
				} else {
					encontrado = true;
				}
				this.esAdministradorLiquidaciones = encontrado;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return this.esAdministradorLiquidaciones;
	}

	public Boolean getEsAdministradorLiquidacionesTGI() {
		return getEsAdministradorLiquidaciones(LiquidacionTasa.codigoTGI);
	}

	public Boolean getEsAdministradorLiquidacionesSHPS() {
		return getEsAdministradorLiquidaciones(LiquidacionTasa.codigoSHPS);
	}

	public Boolean getEsAdministradorLiquidacionesOSP() {
		return getEsAdministradorLiquidaciones(LiquidacionTasa.codigoOSP);
	}

	public Boolean getEsAdministradorLiquidacionesPFO() {
		return getEsAdministradorLiquidaciones(LiquidacionTasa.codigoPFO);
	}

	public Boolean getEsAdministradorLiquidacionesCementerio() {
		return getEsAdministradorLiquidaciones(LiquidacionTasa.codigoCementerio);
	}

	public Boolean getEsAdministradorLiquidacionesAutomotor() {
		return getEsAdministradorLiquidaciones(LiquidacionTasa.codigoAutomotor);
	}

	public Boolean getEsAdministradorLiquidacionesTasaMenor() {
		return getEsAdministradorLiquidaciones(LiquidacionTasa.codigoTasaMenor);
	}

	public Boolean getEsAdministradorLiquidacionesTasasUnificadas() {
		return getEsAdministradorLiquidaciones(LiquidacionTasa.codigoTasasUnificadas);
	}

	private Map<String, Set<ModificarVarias.ModificadorEnTabla>> mapaModificadoresLiq;

	public Map<String, Set<ModificarVarias.ModificadorEnTabla>> getMapaModificadoresLiq() {
		return mapaModificadoresLiq;
	}

	public void setMapaModificadoresLiq(Map<String, Set<ModificarVarias.ModificadorEnTabla>> mapaModificadoresLiq) {
		this.mapaModificadoresLiq = mapaModificadoresLiq;
	}

	private Set listaNombresTasas;

	public Set getListaNombresTasas() {
		return listaNombresTasas;
	}

	public void setListaNombresTasas(Set listaNombresTasas) {
		this.listaNombresTasas = listaNombresTasas;
	}

	private List listaCondicionAplicacionExencion;

	public List getListaCondicionAplicacionExencion() {
		return listaCondicionAplicacionExencion;
	}

	public void setListaCondicionAplicacionExencion(List listaCondicionAplicacionExencion) {
		this.listaCondicionAplicacionExencion = listaCondicionAplicacionExencion;
	}

	private List<Obligacion> listaObligacionExencion;
	private List<CondicionAplicacionExencionNumeroCuota> listaCondicionAplicacionNumeroCuota;

	public List<CondicionAplicacionExencionNumeroCuota> getListaCondicionAplicacionNumeroCuota() {
		return listaCondicionAplicacionNumeroCuota;
	}

	public void setListaCondicionAplicacionNumeroCuota(List<CondicionAplicacionExencionNumeroCuota> listaCondicionAplicacionNumeroCuota) {
		this.listaCondicionAplicacionNumeroCuota = listaCondicionAplicacionNumeroCuota;
	}

	public List<Obligacion> getListaObligacionExencion() {
		return listaObligacionExencion;
	}

	public void setListaObligacionExencion(List<Obligacion> listaObligacionExencion) {
		this.listaObligacionExencion = listaObligacionExencion;
	}

	private Map<String, String> mapaBasicosLiq;

	private Map<String, String> mapaInteresesLiq;

	public Map<String, String> getMapaBasicosLiq() {
		return mapaBasicosLiq;
	}

	public void setMapaBasicosLiq(Map<String, String> mapaBasicosLiq) {
		this.mapaBasicosLiq = mapaBasicosLiq;
	}

	public Map<String, String> getMapaInteresesLiq() {
		return mapaInteresesLiq;
	}

	public void setMapaInteresesLiq(Map<String, String> mapaInteresesLiq) {
		this.mapaInteresesLiq = mapaInteresesLiq;
	}

	private Map<String, SortedSet<ModificarVarias.VencimientoEnTabla>> mapaVencimientosLiq;

	public Map<String, SortedSet<ModificarVarias.VencimientoEnTabla>> getMapaVencimientosLiq() {
		return mapaVencimientosLiq;
	}

	private Map<String, Integer> mapaAniosCalendarioMunicipalCementerio = null;

	public Map<String, Integer> getMapaAniosCalendarioMunicipalCementerio() {
		if(mapaAniosCalendarioMunicipalCementerio == null) {
			try {
				mapaAniosCalendarioMunicipalCementerio = new LinkedHashMap<String, Integer>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("CEMENTERIO"));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				Collections.sort(loclistaCalendario, new Comparator<CalendarioMunicipal>() {
					@Override
					public int compare(CalendarioMunicipal o1, CalendarioMunicipal o2) {
						return (o1.getAnio().compareTo(o2.getAnio())) * -1;
					}
				});

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaAniosCalendarioMunicipalCementerio.put(cadaCalendario.getAnio().toString(), cadaCalendario.getAnio());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaAniosCalendarioMunicipalCementerio;
	}

	private Map<String, CalendarioMunicipal> mapaCalendariosCementerio = new HashMap<String, CalendarioMunicipal>();

	public Map<String, CalendarioMunicipal> getMapaCalendariosCementerio(String pAnio) {
		if(pAnio != null) {
			try {
				mapaCalendariosCementerio = new LinkedHashMap<String, CalendarioMunicipal>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("CEMENTERIO"));
				locFiltro.setAnio(Integer.valueOf(pAnio));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaCalendariosCementerio.put(cadaCalendario.getNombre(), cadaCalendario);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaCalendariosCementerio;
	}

	private Map<String, PeriodoLiquidacion> mapaPeriodosCalendarioMunicipalCementerio = new LinkedHashMap<String, PeriodoLiquidacion>();

	public Map<String, PeriodoLiquidacion> getMapaPeriodosCalendarioMunicipalCementerio(String pCalendario) {
		if(pCalendario != null) {
			mapaPeriodosCalendarioMunicipalCementerio.clear();
			CalendarioMunicipal locCalendario = mapaCalendariosCementerio.get(pCalendario);
			if(locCalendario != null) {
				for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
					mapaPeriodosCalendarioMunicipalCementerio.put(cadaPeriodo.getNombre(), (PeriodoLiquidacion) cadaPeriodo);
				}
			}
		}
		return mapaPeriodosCalendarioMunicipalCementerio;
	}

	private Map<String, CuotaLiquidacion> mapaCuotasCalendarioMunicipalCementerio = new LinkedHashMap<String, CuotaLiquidacion>();

	public Map<String, CuotaLiquidacion> getMapaCuotasCalendarioMunicipalCementerio(String pPeriodo) {
		if(pPeriodo != null) {
			mapaCuotasCalendarioMunicipalCementerio.clear();
			PeriodoLiquidacion locPeriodo = mapaPeriodosCalendarioMunicipalCementerio.get(pPeriodo);
			if(locPeriodo != null) {
				for(CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
					mapaCuotasCalendarioMunicipalCementerio.put(cadaCuota.getNombre(), cadaCuota);
				}
			}
		}
		return mapaCuotasCalendarioMunicipalCementerio;
	}

	// //////////////////////////////////////////////////////////////////
	private Map<String, Integer> mapaAniosCalendarioMunicipalAutomotor = null;

	public Map<String, Integer> getMapaAniosCalendarioMunicipalAutomotor() {
		if(mapaAniosCalendarioMunicipalAutomotor == null) {
			try {
				mapaAniosCalendarioMunicipalAutomotor = new LinkedHashMap<String, Integer>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("AUTOMOTOR"));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				Collections.sort(loclistaCalendario, new Comparator<CalendarioMunicipal>() {
					@Override
					public int compare(CalendarioMunicipal o1, CalendarioMunicipal o2) {
						return (o1.getAnio().compareTo(o2.getAnio())) * -1;
					}
				});

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaAniosCalendarioMunicipalAutomotor.put(cadaCalendario.getAnio().toString(), cadaCalendario.getAnio());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaAniosCalendarioMunicipalAutomotor;
	}

	private Map<String, CalendarioMunicipal> mapaCalendariosAutomotor = new HashMap<String, CalendarioMunicipal>();

	public Map<String, CalendarioMunicipal> getMapaCalendariosAutomotor(String pAnio) {
		if(pAnio != null) {
			try {
				mapaCalendariosAutomotor = new LinkedHashMap<String, CalendarioMunicipal>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("AUTOMOTOR"));
				locFiltro.setAnio(Integer.valueOf(pAnio));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaCalendariosAutomotor.put(cadaCalendario.getNombre(), cadaCalendario);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaCalendariosAutomotor;
	}

	private Map<String, PeriodoLiquidacion> mapaPeriodosCalendarioMunicipalAutomotor = new LinkedHashMap<String, PeriodoLiquidacion>();

	public Map<String, PeriodoLiquidacion> getMapaPeriodosCalendarioMunicipalAutomotor(String pCalendario) {
		if(pCalendario != null) {
			mapaPeriodosCalendarioMunicipalAutomotor.clear();
			CalendarioMunicipal locCalendario = mapaCalendariosAutomotor.get(pCalendario);
			if(locCalendario != null) {
				for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
					mapaPeriodosCalendarioMunicipalAutomotor.put(cadaPeriodo.getNombre(), (PeriodoLiquidacion) cadaPeriodo);
				}
			}
		}
		return mapaPeriodosCalendarioMunicipalAutomotor;
	}

	private Map<String, CuotaLiquidacion> mapaCuotasCalendarioMunicipalAutomotor = new LinkedHashMap<String, CuotaLiquidacion>();

	public Map<String, CuotaLiquidacion> getMapaCuotasCalendarioMunicipalAutomotor(String pPeriodo) {
		if(pPeriodo != null) {
			mapaCuotasCalendarioMunicipalAutomotor.clear();
			PeriodoLiquidacion locPeriodo = mapaPeriodosCalendarioMunicipalAutomotor.get(pPeriodo);
			if(locPeriodo != null) {
				for(CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
					mapaCuotasCalendarioMunicipalAutomotor.put(cadaCuota.getNombre(), cadaCuota);
				}
			}
		}
		return mapaCuotasCalendarioMunicipalAutomotor;
	}

	private Map<String, Integer> mapaAniosCalendarioMunicipalPFO = null;

	public Map<String, Integer> getMapaAniosCalendarioMunicipalPFO() {
		if(mapaAniosCalendarioMunicipalPFO == null) {
			try {
				mapaAniosCalendarioMunicipalPFO = new LinkedHashMap<String, Integer>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("PLAN_FINANCIACION_OBRA"));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				Collections.sort(loclistaCalendario, new Comparator<CalendarioMunicipal>() {
					@Override
					public int compare(CalendarioMunicipal o1, CalendarioMunicipal o2) {
						return (o1.getAnio().compareTo(o2.getAnio())) * -1;
					}
				});

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaAniosCalendarioMunicipalPFO.put(cadaCalendario.getAnio().toString(), cadaCalendario.getAnio());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaAniosCalendarioMunicipalPFO;
	}

	private Map<String, CalendarioMunicipal> mapaCalendariosPFO = new HashMap<String, CalendarioMunicipal>();

	public Map<String, CalendarioMunicipal> getMapaCalendariosPFO(String pAnio) {
		if(pAnio != null) {
			try {
				mapaCalendariosPFO = new LinkedHashMap<String, CalendarioMunicipal>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("PLAN_FINANCIACION_OBRA"));
				locFiltro.setAnio(Integer.valueOf(pAnio));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaCalendariosPFO.put(cadaCalendario.getNombre(), cadaCalendario);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaCalendariosPFO;
	}

	private Map<String, PeriodoLiquidacion> mapaPeriodosCalendarioMunicipalPFO = new LinkedHashMap<String, PeriodoLiquidacion>();

	public Map<String, PeriodoLiquidacion> getMapaPeriodosCalendarioMunicipalPFO(String pCalendario) {
		if(pCalendario != null) {
			mapaPeriodosCalendarioMunicipalPFO.clear();
			CalendarioMunicipal locCalendario = mapaCalendariosPFO.get(pCalendario);
			if(locCalendario != null) {
				for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
					mapaPeriodosCalendarioMunicipalPFO.put(cadaPeriodo.getNombre(), (PeriodoLiquidacion) cadaPeriodo);
				}
			}
		}
		return mapaPeriodosCalendarioMunicipalPFO;
	}

	private Map<String, CuotaLiquidacion> mapaCuotasCalendarioMunicipalPFO = new LinkedHashMap<String, CuotaLiquidacion>();

	public Map<String, CuotaLiquidacion> getMapaCuotasCalendarioMunicipalPFO(String pPeriodo) {
		if(pPeriodo != null) {
			mapaCuotasCalendarioMunicipalPFO.clear();
			PeriodoLiquidacion locPeriodo = mapaPeriodosCalendarioMunicipalPFO.get(pPeriodo);
			if(locPeriodo != null) {
				for(CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
					mapaCuotasCalendarioMunicipalPFO.put(cadaCuota.getNombre(), cadaCuota);
				}
			}
		}
		return mapaCuotasCalendarioMunicipalPFO;
	}

	private Map<String, Integer> mapaAniosCalendarioMunicipalOSP = null;

	public Map<String, Integer> getMapaAniosCalendarioMunicipalOSP() {
		if(mapaAniosCalendarioMunicipalOSP == null) {
			try {
				mapaAniosCalendarioMunicipalOSP = new LinkedHashMap<String, Integer>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("OYSP"));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				Collections.sort(loclistaCalendario, new Comparator<CalendarioMunicipal>() {
					@Override
					public int compare(CalendarioMunicipal o1, CalendarioMunicipal o2) {
						return (o1.getAnio().compareTo(o2.getAnio())) * -1;
					}
				});

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaAniosCalendarioMunicipalOSP.put(cadaCalendario.getAnio().toString(), cadaCalendario.getAnio());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaAniosCalendarioMunicipalOSP;
	}

	private Map<String, CalendarioMunicipal> mapaCalendariosOSP = new HashMap<String, CalendarioMunicipal>();

	public Map<String, CalendarioMunicipal> getMapaCalendariosOSP(String pAnio) {
		if(pAnio != null) {
			try {
				mapaCalendariosOSP = new LinkedHashMap<String, CalendarioMunicipal>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("OYSP"));
				locFiltro.setAnio(Integer.valueOf(pAnio));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaCalendariosOSP.put(cadaCalendario.getNombre(), cadaCalendario);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaCalendariosOSP;
	}

	private Map<String, PeriodoLiquidacion> mapaPeriodosCalendarioMunicipalOSP = new LinkedHashMap<String, PeriodoLiquidacion>();

	public Map<String, PeriodoLiquidacion> getMapaPeriodosCalendarioMunicipalOSP(String pCalendario) {
		if(pCalendario != null) {
			mapaPeriodosCalendarioMunicipalOSP.clear();
			CalendarioMunicipal locCalendario = mapaCalendariosOSP.get(pCalendario);
			if(locCalendario != null) {
				for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
					mapaPeriodosCalendarioMunicipalOSP.put(cadaPeriodo.getNombre(), (PeriodoLiquidacion) cadaPeriodo);
				}
			}
		}
		return mapaPeriodosCalendarioMunicipalOSP;
	}

	private Map<String, CuotaLiquidacion> mapaCuotasCalendarioMunicipalOSP = new LinkedHashMap<String, CuotaLiquidacion>();

	public Map<String, CuotaLiquidacion> getMapaCuotasCalendarioMunicipalOSP(String pPeriodo) {
		if(pPeriodo != null) {
			mapaCuotasCalendarioMunicipalOSP.clear();
			PeriodoLiquidacion locPeriodo = mapaPeriodosCalendarioMunicipalOSP.get(pPeriodo);
			if(locPeriodo != null) {
				for(CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
					mapaCuotasCalendarioMunicipalOSP.put(cadaCuota.getNombre(), cadaCuota);
				}
			}
		}
		return mapaCuotasCalendarioMunicipalOSP;
	}

	private Map<String, Integer> mapaAniosCalendarioMunicipalTasaMenor = new HashMap<String, Integer>();

	public Map<String, Integer> getMapaAniosCalendarioMunicipalTasaMenor(String pTipoObligacion) {
		if(pTipoObligacion != null) {
			try {
				mapaAniosCalendarioMunicipalTasaMenor = new LinkedHashMap<String, Integer>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(pTipoObligacion));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> locListaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				Collections.sort(locListaCalendario, new Comparator<CalendarioMunicipal>() {
					@Override
					public int compare(CalendarioMunicipal o1, CalendarioMunicipal o2) {
						return (o1.getAnio().compareTo(o2.getAnio())) * -1;
					}
				});

				for(CalendarioMunicipal cadaCalendario : locListaCalendario) {
					mapaAniosCalendarioMunicipalTasaMenor.put(cadaCalendario.getAnio().toString(), cadaCalendario.getAnio());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaAniosCalendarioMunicipalTasaMenor;
	}

	private Map<String, CalendarioMunicipal> mapaCalendariosTasaMenor = new HashMap<String, CalendarioMunicipal>();

	public Map<String, CalendarioMunicipal> getMapaCalendariosTasaMenor(String pAnio, String pTipoObligacion) {
		if(pAnio != null) {
			try {
				mapaCalendariosTasaMenor = new LinkedHashMap<String, CalendarioMunicipal>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(pTipoObligacion));
				locFiltro.setAnio(Integer.valueOf(pAnio));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaCalendariosTasaMenor.put(cadaCalendario.getNombre(), cadaCalendario);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaCalendariosTasaMenor;
	}

	private Map<String, PeriodoLiquidacion> mapaPeriodosCalendarioMunicipalTasaMenor = new LinkedHashMap<String, PeriodoLiquidacion>();

	public Map<String, PeriodoLiquidacion> getMapaPeriodosCalendarioMunicipalTasaMenor(String pCalendario) {
		if(pCalendario != null) {
			mapaPeriodosCalendarioMunicipalTasaMenor.clear();
			CalendarioMunicipal locCalendario = mapaCalendariosTasaMenor.get(pCalendario);
			if(locCalendario != null) {
				for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
					mapaPeriodosCalendarioMunicipalTasaMenor.put(cadaPeriodo.getNombre(), (PeriodoLiquidacion) cadaPeriodo);
				}
			}
		}
		return mapaPeriodosCalendarioMunicipalTasaMenor;
	}

	private Map<String, CuotaLiquidacion> mapaCuotasCalendarioMunicipalTasaMenor = new LinkedHashMap<String, CuotaLiquidacion>();

	public Map<String, CuotaLiquidacion> getMapaCuotasCalendarioMunicipalTasaMenor(String pPeriodo) {
		if(pPeriodo != null) {
			mapaCuotasCalendarioMunicipalTasaMenor.clear();
			PeriodoLiquidacion locPeriodo = mapaPeriodosCalendarioMunicipalTasaMenor.get(pPeriodo);
			if(locPeriodo != null) {
				for(CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
					mapaCuotasCalendarioMunicipalTasaMenor.put(cadaCuota.getNombre(), cadaCuota);
				}
			}
		}
		return mapaCuotasCalendarioMunicipalTasaMenor;
	}

	private Map<String, Integer> mapaAniosCalendarioMunicipalSHPS = null;

	public Map<String, Integer> getMapaAniosCalendarioMunicipalSHPS() {
		if(mapaAniosCalendarioMunicipalSHPS == null) {
			try {
				mapaAniosCalendarioMunicipalSHPS = new LinkedHashMap<String, Integer>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("SHPS"));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				Collections.sort(loclistaCalendario, new Comparator<CalendarioMunicipal>() {
					@Override
					public int compare(CalendarioMunicipal o1, CalendarioMunicipal o2) {
						return (o1.getAnio().compareTo(o2.getAnio())) * -1;
					}
				});

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaAniosCalendarioMunicipalSHPS.put(cadaCalendario.getAnio().toString(), cadaCalendario.getAnio());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaAniosCalendarioMunicipalSHPS;
	}

	private Map<String, CalendarioMunicipal> mapaCalendariosSHPS = new HashMap<String, CalendarioMunicipal>();

	public Map<String, CalendarioMunicipal> getMapaCalendariosSHPS(String pAnio) {
		if(pAnio != null) {
			try {
				mapaCalendariosSHPS = new LinkedHashMap<String, CalendarioMunicipal>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("SHPS"));
				locFiltro.setAnio(Integer.valueOf(pAnio));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaCalendariosSHPS.put(cadaCalendario.getNombre(), cadaCalendario);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaCalendariosSHPS;
	}

	private Map<String, PeriodoLiquidacion> mapaPeriodosCalendarioMunicipalSHPS = new LinkedHashMap<String, PeriodoLiquidacion>();

	public Map<String, PeriodoLiquidacion> getMapaPeriodosCalendarioMunicipalSHPS(String pCalendario) {
		if(pCalendario != null) {
			mapaPeriodosCalendarioMunicipalSHPS.clear();
			CalendarioMunicipal locCalendario = mapaCalendariosSHPS.get(pCalendario);
			if(locCalendario != null) {
				for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
					mapaPeriodosCalendarioMunicipalSHPS.put(cadaPeriodo.getNombre(), (PeriodoLiquidacion) cadaPeriodo);
				}
			}
		}
		return mapaPeriodosCalendarioMunicipalSHPS;
	}

	private Map<String, CuotaLiquidacion> mapaCuotasCalendarioMunicipalSHPS = new LinkedHashMap<String, CuotaLiquidacion>();

	public Map<String, CuotaLiquidacion> getMapaCuotasCalendarioMunicipalSHPS(String pPeriodo) {
		if(pPeriodo != null) {
			mapaCuotasCalendarioMunicipalSHPS.clear();
			PeriodoLiquidacion locPeriodo = mapaPeriodosCalendarioMunicipalSHPS.get(pPeriodo);
			if(locPeriodo != null) {
				for(CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
					mapaCuotasCalendarioMunicipalSHPS.put(cadaCuota.getNombre(), cadaCuota);
				}
			}
		}
		return mapaCuotasCalendarioMunicipalSHPS;
	}

	private String tipoObligacionSeleccionada;

	public String getTipoObligacionSeleccionada() {
		return tipoObligacionSeleccionada;
	}

	public void setTipoObligacionSeleccionada(String tipoObligacionSeleccionada) {
		this.tipoObligacionSeleccionada = tipoObligacionSeleccionada;
	}

	private Map<String, Integer> mapaAniosCalendarioMunicipalExencionObligacion = null;

	public Map<String, Integer> getMapaAniosCalendarioMunicipalExencionObligacion() {
		if(mapaAniosCalendarioMunicipalExencionObligacion == null) {
			try {
				if(tipoObligacionSeleccionada == null) {
					return new HashMap<String, Integer>();
				}
				mapaAniosCalendarioMunicipalExencionObligacion = new LinkedHashMap<String, Integer>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(tipoObligacionSeleccionada)); // ojo esto

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				Collections.sort(loclistaCalendario, new Comparator<CalendarioMunicipal>() {
					@Override
					public int compare(CalendarioMunicipal o1, CalendarioMunicipal o2) {
						return (o1.getAnio().compareTo(o2.getAnio())) * -1;
					}
				});

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaAniosCalendarioMunicipalExencionObligacion.put(cadaCalendario.getAnio().toString(), cadaCalendario.getAnio());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaAniosCalendarioMunicipalExencionObligacion;
	}

	public void setMapaAniosCalendarioMunicipalExencionObligacion(Map<String, Integer> pMapa) {
		this.mapaAniosCalendarioMunicipalExencionObligacion = pMapa;
	}

	private Map<String, CalendarioMunicipal> mapaCalendariosExencionObligacion = new HashMap<String, CalendarioMunicipal>();

	public Map<String, CalendarioMunicipal> getMapaCalendariosExencionObligacion(String pAnio) {
		if(pAnio != null) {
			try {
				if(tipoObligacionSeleccionada == null) {
					return new HashMap<String, CalendarioMunicipal>();
				}

				mapaCalendariosExencionObligacion = new LinkedHashMap<String, CalendarioMunicipal>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(tipoObligacionSeleccionada)); // ojo aca tmb
				locFiltro.setAnio(Integer.valueOf(pAnio));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaCalendariosExencionObligacion.put(cadaCalendario.getNombre(), cadaCalendario);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaCalendariosExencionObligacion;
	}

	private Map<String, PeriodoLiquidacion> mapaPeriodosCalendarioMunicipalExencionObligacion = new LinkedHashMap<String, PeriodoLiquidacion>();

	public Map<String, PeriodoLiquidacion> getMapaPeriodosCalendarioMunicipalExencionObligacion(String pCalendario) {
		if(pCalendario != null) {
			mapaPeriodosCalendarioMunicipalExencionObligacion.clear();
			CalendarioMunicipal locCalendario = mapaCalendariosExencionObligacion.get(pCalendario);
			if(locCalendario != null) {
				for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
					mapaPeriodosCalendarioMunicipalExencionObligacion.put(cadaPeriodo.getNombre(), (PeriodoLiquidacion) cadaPeriodo);
				}
			}
		}
		return mapaPeriodosCalendarioMunicipalExencionObligacion;
	}

	private Map<String, CuotaLiquidacion> mapaCuotasCalendarioMunicipalExencionObligacion = new LinkedHashMap<String, CuotaLiquidacion>();

	public Map<String, CuotaLiquidacion> getMapaCuotasCalendarioMunicipalExencionObligacion(String pPeriodo) {
		if(pPeriodo != null) {
			mapaCuotasCalendarioMunicipalExencionObligacion.clear();
			PeriodoLiquidacion locPeriodo = mapaPeriodosCalendarioMunicipalExencionObligacion.get(pPeriodo);
			if(locPeriodo != null) {
				for(CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
					mapaCuotasCalendarioMunicipalExencionObligacion.put(cadaCuota.getNombre(), cadaCuota);
				}
			}
		}
		return mapaCuotasCalendarioMunicipalExencionObligacion;
	}

	private Map<String, Integer> mapaAniosCalendarioMunicipalTGI = null;

	public Map<String, Integer> getMapaAniosCalendarioMunicipalTGI() {
		if(mapaAniosCalendarioMunicipalTGI == null) {
			try {
				mapaAniosCalendarioMunicipalTGI = new LinkedHashMap<String, Integer>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("TGI"));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				Collections.sort(loclistaCalendario, new Comparator<CalendarioMunicipal>() {
					@Override
					public int compare(CalendarioMunicipal o1, CalendarioMunicipal o2) {
						return (o1.getAnio().compareTo(o2.getAnio())) * -1;
					}
				});

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaAniosCalendarioMunicipalTGI.put(cadaCalendario.getAnio().toString(), cadaCalendario.getAnio());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaAniosCalendarioMunicipalTGI;
	}

	private Map<String, CalendarioMunicipal> mapaCalendariosTGI = new HashMap<String, CalendarioMunicipal>();

	public Map<String, CalendarioMunicipal> getMapaCalendariosTGI(String pAnio) {
		if(pAnio != null) {
			try {
				mapaCalendariosTGI = new LinkedHashMap<String, CalendarioMunicipal>();
				FiltroCalendarioMunicipal locFiltro = new FiltroCalendarioMunicipal();
				locFiltro.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("TGI"));
				locFiltro.setAnio(Integer.valueOf(pAnio));

				getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
				List<CalendarioMunicipal> loclistaCalendario = this.getRemoteSystemPeriodo().findListaCalendarios(locFiltro).getListaResultados();

				for(CalendarioMunicipal cadaCalendario : loclistaCalendario) {
					mapaCalendariosTGI.put(cadaCalendario.getNombre(), cadaCalendario);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaCalendariosTGI;
	}

	private Map<String, PeriodoLiquidacion> mapaPeriodosCalendarioMunicipalTGI = new LinkedHashMap<String, PeriodoLiquidacion>();

	public Map<String, PeriodoLiquidacion> getMapaPeriodosCalendarioMunicipalTGI(String pCalendario) {
		if(pCalendario != null) {
			mapaPeriodosCalendarioMunicipalTGI.clear();
			CalendarioMunicipal locCalendario = mapaCalendariosTGI.get(pCalendario);
			if(locCalendario != null) {
				for(Periodo cadaPeriodo : locCalendario.getListaPeriodos()) {
					mapaPeriodosCalendarioMunicipalTGI.put(cadaPeriodo.getNombre(), (PeriodoLiquidacion) cadaPeriodo);
				}
			}
		}
		return mapaPeriodosCalendarioMunicipalTGI;
	}

	private Map<String, CuotaLiquidacion> mapaCuotasCalendarioMunicipalTGI = new LinkedHashMap<String, CuotaLiquidacion>();

	public Map<String, CuotaLiquidacion> getMapaCuotasCalendarioMunicipalTGI(String pPeriodo) {
		if(pPeriodo != null) {
			mapaCuotasCalendarioMunicipalTGI.clear();
			PeriodoLiquidacion locPeriodo = mapaPeriodosCalendarioMunicipalTGI.get(pPeriodo);
			if(locPeriodo != null) {
				for(CuotaLiquidacion cadaCuota : locPeriodo.getListaCuotas()) {
					mapaCuotasCalendarioMunicipalTGI.put(cadaCuota.getNombre(), cadaCuota);
				}
			}
		}
		return mapaCuotasCalendarioMunicipalTGI;
	}

	public void setMapaVencimientosLiq(Map<String, SortedSet<ModificarVarias.VencimientoEnTabla>> mapaVencimientosLiq) {
		this.mapaVencimientosLiq = mapaVencimientosLiq;
	}

	private String tasaSeleccionada;

	public String getTasaSeleccionada() {
		return tasaSeleccionada;
	}

	public void setTasaSeleccionada(String tasaSeleccionada) {
		this.tasaSeleccionada = tasaSeleccionada;
	}

	private TableSelectPhaseListener tablePhaseListenerActualizarDeuda = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerActualizarDeuda() {
		return tablePhaseListenerActualizarDeuda;
	}

	public void setTablePhaseListenerActualizarDeuda(TableSelectPhaseListener tablePhaseListenerActualizarDeuda) {
		this.tablePhaseListenerActualizarDeuda = tablePhaseListenerActualizarDeuda;
	}

}
