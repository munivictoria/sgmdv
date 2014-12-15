/*
 * CommunicationComprasBean.java
 *
 * Created on 21 de noviembre de 2006, 10:11
 * Copyright ignacio
 */

package muni;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.filtros.FiltroArticulo;
import com.trascender.compras.recurso.filtros.FiltroAutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.filtros.FiltroBien;
import com.trascender.compras.recurso.filtros.FiltroContratacion;
import com.trascender.compras.recurso.filtros.FiltroDeposito;
import com.trascender.compras.recurso.filtros.FiltroEstadoSolicitudSuministro;
import com.trascender.compras.recurso.filtros.FiltroFacturaContrato;
import com.trascender.compras.recurso.filtros.FiltroFacturaProveedor;
import com.trascender.compras.recurso.filtros.FiltroFacturaServicio;
import com.trascender.compras.recurso.filtros.FiltroFacturaSubsidio;
import com.trascender.compras.recurso.filtros.FiltroLiquidacionCompra;
import com.trascender.compras.recurso.filtros.FiltroMovimientoDeMercaderia;
import com.trascender.compras.recurso.filtros.FiltroOrdenCompra;
import com.trascender.compras.recurso.filtros.FiltroProveedores;
import com.trascender.compras.recurso.filtros.FiltroSolicitudSuministro;
import com.trascender.compras.recurso.filtros.FiltroTipoBien;
import com.trascender.compras.recurso.filtros.FiltroUnidad;
import com.trascender.compras.recurso.filtros.FiltroUsuarioFirmante;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.suministros.AutorizacionSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.LineaOfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra.LineaLiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolicitudSuministro;
import com.trascender.compras.system.interfaces.SystemAdministracionBienes;
import com.trascender.compras.system.interfaces.SystemAdministracionCondicionCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionFactura;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaContrato;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaProveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaServicio;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaSubsidio;
import com.trascender.compras.system.interfaces.SystemAdministracionLicitacion;
import com.trascender.compras.system.interfaces.SystemAdministracionOrdenCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.compras.system.interfaces.SystemAdministracionSolicitudSuministro;
import com.trascender.compras.system.interfaces.SystemReportesCompras;
import com.trascender.compras.system.interfaces.SystemStock;
import com.trascender.framework.recurso.filtros.FiltroSecretaria;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
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
public class CommunicationComprasBean extends AbstractSessionBean {
	// <editor-fold defaultstate="collapsed"
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

	// </editor-fold>
	Properties props = null;
	Context ctx = null;

	/**
	 * <p>
	 * Construir una instancia de bean de datos de la sesión.
	 * </p>
	 */
	public CommunicationComprasBean() {
		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "localhost:1099");
		try {
			ctx = new InitialContext(props);
			this.remoteSystemAdministracionBienes = (SystemAdministracionBienes) ctx.lookup(SystemAdministracionBienes.JNDI_NAME);
			this.remoteSystemAdministracionProveedores = (SystemAdministracionProveedores) ctx.lookup(SystemAdministracionProveedores.JNDI_NAME);
			this.remoteSystemAdministracionFacturaSubsidio = (SystemAdministracionFacturaSubsidio) ctx.lookup(SystemAdministracionFacturaSubsidio.JNDI_NAME);
			this.remoteSystemAdministracionFacturaContrato = (SystemAdministracionFacturaContrato) ctx.lookup(SystemAdministracionFacturaContrato.JNDI_NAME);
			this.remoteSystemAdministracionFacturaServicio = (SystemAdministracionFacturaServicio) ctx.lookup(SystemAdministracionFacturaServicio.JNDI_NAME);
			this.remoteSystemAdministracionSolicitudSuministro = (SystemAdministracionSolicitudSuministro) ctx.lookup(SystemAdministracionSolicitudSuministro.JNDI_NAME);
			this.remoteSystemAdministracionOrdenCompra = (SystemAdministracionOrdenCompra) ctx.lookup(SystemAdministracionOrdenCompra.JNDI_NAME);
			this.remoteSystemAdministracionCondicionCompra = (SystemAdministracionCondicionCompra) ctx.lookup(SystemAdministracionCondicionCompra.JNDI_NAME);
			this.remoteSystemAdministracionFacturaProveedor = (SystemAdministracionFacturaProveedor) ctx.lookup(SystemAdministracionFacturaProveedor.JNDI_NAME);
			this.remoteSystemAdministracionLicitacion = (SystemAdministracionLicitacion) ctx.lookup(SystemAdministracionLicitacion.JNDI_NAME);
			this.remoteSystemStock = (SystemStock) ctx.lookup(SystemStock.JNDI_NAME);
			this.remoteSystemReportesCompras = (SystemReportesCompras) ctx.lookup(SystemReportesCompras.JNDI_NAME);
			this.remoteSystemAdministracionFactura = (SystemAdministracionFactura) ctx.lookup(SystemAdministracionFactura.JNDI_NAME);

			System.out.println("CommunicationComprasBean");
			// *****TABLAS**//
			FiltroArticulo locFiltroArticulo = new FiltroArticulo();
			locFiltroArticulo.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaArticulo = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Articulo.serialVersionUID), "#{inventario$ABMArticulo$AdminArticulo}",
					locFiltroArticulo);

			FiltroDeposito locFiltroDeposito = new FiltroDeposito();
			locFiltroDeposito.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDeposito = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Deposito.serialVersionUID), "#{inventario$ABMDeposito$AdminDeposito}",
					locFiltroDeposito);

			FiltroMovimientoDeMercaderia locFiltroMovimientoMerc = new FiltroMovimientoDeMercaderia();
			locFiltroMovimientoMerc.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaMovimientoDeMercaderia = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(MovimientoDeMercaderia.serialVersionUID),
					"#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia}", locFiltroMovimientoMerc);

			FiltroBien locFiltroBien = new FiltroBien();
			locFiltroBien.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaBien = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Bien.serialVersionUID), "#{compras$ABMBien$AdminBien}", locFiltroBien);

			FiltroAutorizacionSolicitudSuministro locFiltroAutorizacionSolSum = new FiltroAutorizacionSolicitudSuministro();
			locFiltroAutorizacionSolSum.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaAutorizacionSolicitudSuministro = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(AutorizacionSolicitudSuministro.serialVersionUID),
					"#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro}", locFiltroAutorizacionSolSum);

			// FiltroCondicionCompra locFiltroCondicionCompra = new FiltroCondicionCompra();
			// locFiltroCondicionCompra.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			// this.tablaCondicionCompra = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(CondicionCompra.serialVersionUID),
			// "#{compras$ABMCondicionCompra$AdminCondicionCompra}",
			// locFiltroCondicionCompra);

			FiltroOrdenCompra locFiltroOrdenCompra = new FiltroOrdenCompra();
			locFiltroOrdenCompra.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaOrdenCompra = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(OrdenCompra.serialVersionUID), "#{compras$ABMOrdenCompra$AdminOrdenCompra}",
					locFiltroOrdenCompra);

			FiltroUsuarioFirmante locFiltroUsuarioFirmante = new FiltroUsuarioFirmante();
			locFiltroUsuarioFirmante.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaUsuarioFirmante = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(UsuarioAutorizadorOrdenCompra.serialVersionUID),
					"#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante}", locFiltroUsuarioFirmante);

			FiltroProveedores locFiltroProveedores = new FiltroProveedores();
			locFiltroProveedores.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaProveedor = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Proveedor.serialVersionUID), "#{compras$ABMProveedor$AdminProveedor}",
					locFiltroProveedores);

			FiltroSolicitudSuministro locFiltroSolicitudSuministro = new FiltroSolicitudSuministro();
			locFiltroSolicitudSuministro.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaSolicitudSuministro = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(SolicitudSuministro.serialVersionUID),
					"#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro}", locFiltroSolicitudSuministro);

			FiltroUnidad locFiltroUnidad = new FiltroUnidad();
			locFiltroUnidad.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaUnidad = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Unidad.serialVersionUID), "#{compras$ABMUnidad$AdminUnidad}", locFiltroUnidad);

			FiltroContratacion locFiltroContratacion = new FiltroContratacion();
			locFiltroContratacion.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaContratacion = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Contratacion.serialVersionUID), "#{compras$ABMLicitacion$AdminLicitacion}",
					locFiltroContratacion);

			FiltroFacturaProveedor locFiltroFacturaProveedor = new FiltroFacturaProveedor();
			locFiltroFacturaProveedor.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaFacturaProveedor = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(FacturaProveedor.serialVersionUID),
					"#{compras$ABMFacturaProveedor$AdminFacturaProveedor}", locFiltroFacturaProveedor);

			FiltroEstadoSolicitudSuministro locFiltroEstadoSolicitudSuministro = new FiltroEstadoSolicitudSuministro();
			locFiltroEstadoSolicitudSuministro.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaEstadoSolicitudSuministro = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(EstadoSolicitudSuministro.serialVersionUID),
					"#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro}", locFiltroEstadoSolicitudSuministro);

			FiltroTipoBien locFiltroTipoBien = new FiltroTipoBien();
			locFiltroTipoBien.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoBien = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoBien.serialVersionUID), "#{compras$ABMTipoBien$AdminTipoBien}",
					locFiltroTipoBien);

			FiltroFacturaSubsidio locFiltroFacturaSubsidio = new FiltroFacturaSubsidio();
			locFiltroFacturaSubsidio.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaFacturaSubsidio = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(FacturaSubsidio.serialVersionUID),
					"#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio}", locFiltroFacturaSubsidio);

			FiltroFacturaContrato locFiltroFacturaContrato = new FiltroFacturaContrato();
			locFiltroFacturaContrato.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaFacturaContrato = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(FacturaContrato.serialVersionUID),
					"#{compras$ABMFacturaContrato$AdminFacturaContrato}", locFiltroFacturaContrato);

			FiltroFacturaServicio locFiltroFacturaServicio = new FiltroFacturaServicio();
			locFiltroFacturaServicio.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaFacturaServicio = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(FacturaContrato.serialVersionUID),
					"#{compras$ABMFacturaServicio$AdminFacturaServicio}", locFiltroFacturaServicio);

			FiltroLiquidacionCompra locFiltroLiquidacionCompra = new FiltroLiquidacionCompra();
			locFiltroLiquidacionCompra.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaLiquidacionCompra = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(LiquidacionCompra.serialVersionUID),
					"#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra}", locFiltroLiquidacionCompra);

		} catch(Exception ex) {
			// Logger.getLogger(ComunicationBean.class.getName()).log(Level.SEVERE,
			// null, ex);
			ex.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ámbito.
	 * </p>
	 */
	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
	}

	private muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Se llama a este método al agregar este bean al ámbito de la sesión. Normalmente, esto ocurre como resultado de la evaluación de una expresión de enlace
	 * de valores o de métodos, que utiliza la función de bean administrado para crear una instancia de este bean y almacenarla en el ámbito de la sesión.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este método para inicializar y almacenar en caché los valores o recursos necesarios para el ciclo de duración de una sesión de usuario
	 * en particular.
	 * </p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicación que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar código de inicio propio aquí

		// <editor-fold defaultstate="collapsed"
		// desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados automáticamente
		// *Nota* - esta lógica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("CommunicationComprasBean Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicación que debe finalizar
		// *después* de que se inicien los componentes administrados
		// TODO - Agregar código de inicio propio aquí

	}

	/**
	 * <p>
	 * Se llama a este método cuando la sesión que lo contiene está apunto de configurarse en modo pasivo. Normalmente, esto ocurre en un contenedor de servlet
	 * distribuido cuando la sesión está apunto de transferirse a otra instancia de contenedor, después de la cual se llamará al método <code>activate()</code>
	 * para indicar que la transferencia se ha completado.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este método para liberar las referencias a datos o recursos de sesión que no pueden serializarse con la propia sesión.
	 * </p>
	 */
	@Override
	public void passivate() {
	}

	/**
	 * <p>
	 * Se llama a este método cuando la sesión que lo contiene se reactiva.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este método para volver a adquirir las referencias a datos o recursos de la sesión que no pudieron serializarse con la propia sesión.
	 * </p>
	 */
	@Override
	public void activate() {
	}

	/**
	 * <p>
	 * Se llama a este método al eliminar este bean del ámbito de la sesión. Normalmente, esto ocurre cuando se supera el tiempo de espera de la sesión o la
	 * aplicación la finaliza.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este método para limpiar los recursos asignados durante la ejecución del método <code>init()</code> o más adelante durante el ciclo de
	 * vida de la aplicación.
	 * </p>
	 */
	@Override
	public void destroy() {
	}

	/**
	 * Definición de la interfaz remota SystemAdministracionBienes, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed" desc="SystemAdministracionBienes">
	// @EJB
	private SystemAdministracionBienes remoteSystemAdministracionBienes = null;

	public com.trascender.compras.system.interfaces.SystemAdministracionBienes getRemoteSystemAdministracionBienes() {
		try {
			// if (this.remoteSystemAdministracionBienes == null) {
			// Context ctx = new InitialContext(getProps());
			// Object obj =
			// ctx.lookup(SystemAdministracionBienesHome.JNDI_NAME);
			// SystemAdministracionBienesHome locAdministracionBienesHome =
			// (SystemAdministracionBienesHome) PortableRemoteObject.narrow(obj,
			// SystemAdministracionBienesHome.class);
			// this.remoteSystemAdministracionBienes =
			// locAdministracionBienesHome.create();
			// }
			this.remoteSystemAdministracionBienes.setLlave(getSessionBean1().getLlave());
		} catch(Exception e) {
			e.printStackTrace();
		}

		return remoteSystemAdministracionBienes;
	}

	public void setRemoteSystemAdministracionBienes(com.trascender.compras.system.interfaces.SystemAdministracionBienes remoteSystemAdministracionBienes) {
		this.remoteSystemAdministracionBienes = remoteSystemAdministracionBienes;
	}

	// </editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionBienes, para invocar la lógica de negocio.
	 */
	// <editor
	/**
	 * Definición de la interfaz remota SystemAdministracionBienes, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionProveedores">
	// @EJB
	private SystemAdministracionProveedores remoteSystemAdministracionProveedores = null;

	public com.trascender.compras.system.interfaces.SystemAdministracionProveedores getRemoteSystemAdministracionProveedores() {
		// try {
		// if (this.remoteSystemAdministracionProveedores == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionProveedoresHome.JNDI_NAME);
		// SystemAdministracionProveedoresHome locAdministracionProveedoresHome
		// = (SystemAdministracionProveedoresHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionProveedoresHome.class);
		// this.remoteSystemAdministracionProveedores =
		// locAdministracionProveedoresHome.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemAdministracionProveedores;
	}

	public void setRemoteSystemAdministracionProveedores(com.trascender.compras.system.interfaces.SystemAdministracionProveedores remoteSystemAdministracionProveedores) {
		this.remoteSystemAdministracionProveedores = remoteSystemAdministracionProveedores;
	}

	// </editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionFacturaSubsidios, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionFacturaSubsidio">
	// @EJB
	private SystemAdministracionFacturaSubsidio remoteSystemAdministracionFacturaSubsidio = null;

	public com.trascender.compras.system.interfaces.SystemAdministracionFacturaSubsidio getRemoteSystemAdministracionFacturaSubsidio() {
		// try {
		// if (this.remoteSystemAdministracionFacturaSubsidio == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionFacturaSubsidioHome.JNDI_NAME);
		// SystemAdministracionFacturaSubsidioHome
		// locAdministracionFacturaSubsidioHome =
		// (SystemAdministracionFacturaSubsidioHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionFacturaSubsidioHome.class);
		// this.remoteSystemAdministracionFacturaSubsidio =
		// locAdministracionFacturaSubsidioHome.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemAdministracionFacturaSubsidio;
	}

	public void setRemoteSystemAdministracionFacturaSubsidio(com.trascender.compras.system.interfaces.SystemAdministracionFacturaSubsidio remoteSystemAdministracionFacturaSubsidio) {
		this.remoteSystemAdministracionFacturaSubsidio = remoteSystemAdministracionFacturaSubsidio;
	}

	// </editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionFacturaContratos, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionFacturaContrato">
	// @EJB
	private SystemAdministracionFacturaContrato remoteSystemAdministracionFacturaContrato = null;

	public com.trascender.compras.system.interfaces.SystemAdministracionFacturaContrato getRemoteSystemAdministracionFacturaContrato() {
		// try {
		// if (this.remoteSystemAdministracionFacturaContrato == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionFacturaContratoHome.JNDI_NAME);
		// SystemAdministracionFacturaContratoHome
		// locAdministracionFacturaContratoHome =
		// (SystemAdministracionFacturaContratoHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionFacturaContratoHome.class);
		// this.remoteSystemAdministracionFacturaContrato =
		// locAdministracionFacturaContratoHome.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemAdministracionFacturaContrato;
	}

	public void setRemoteSystemAdministracionFacturaContrato(com.trascender.compras.system.interfaces.SystemAdministracionFacturaContrato remoteSystemAdministracionFacturaContrato) {
		this.remoteSystemAdministracionFacturaContrato = remoteSystemAdministracionFacturaContrato;
	}

	// </editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionFacturaServicios, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionFacturaServicio">
	// @EJB
	private SystemAdministracionFacturaServicio remoteSystemAdministracionFacturaServicio = null;

	public com.trascender.compras.system.interfaces.SystemAdministracionFacturaServicio getRemoteSystemAdministracionFacturaServicio() {
		// try {
		// if (this.remoteSystemAdministracionFacturaServicio == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionFacturaServicioHome.JNDI_NAME);
		// SystemAdministracionFacturaServicioHome
		// locAdministracionFacturaServicioHome =
		// (SystemAdministracionFacturaServicioHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionFacturaServicioHome.class);
		// this.remoteSystemAdministracionFacturaServicio =
		// locAdministracionFacturaServicioHome.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return remoteSystemAdministracionFacturaServicio;
	}

	public void setRemoteSystemAdministracionFacturaServicio(com.trascender.compras.system.interfaces.SystemAdministracionFacturaServicio remoteSystemAdministracionFacturaServicio) {
		this.remoteSystemAdministracionFacturaServicio = remoteSystemAdministracionFacturaServicio;
	}

	// </editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionBienes, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionSolicitudSuministro">
	// @EJB
	private SystemAdministracionSolicitudSuministro remoteSystemAdministracionSolicitudSuministro = null;

	public SystemAdministracionSolicitudSuministro getRemoteSystemAdministracionSolicitudSuministro() {
		// try {
		// if (this.remoteSystemAdministracionSolicitudSuministro == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionSolicitudSuministroHome.JNDI_NAME);
		// SystemAdministracionSolicitudSuministroHome
		// locAdministracionSolicitudSuministroHome =
		// (SystemAdministracionSolicitudSuministroHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionSolicitudSuministroHome.class);
		// this.remoteSystemAdministracionSolicitudSuministro =
		// locAdministracionSolicitudSuministroHome.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		try {
			this.remoteSystemAdministracionSolicitudSuministro.setLlave(this.getSessionBean1().getLlave());
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		return remoteSystemAdministracionSolicitudSuministro;
	}

	public void setRemoteSystemAdministracionSolicitudSuministro(SystemAdministracionSolicitudSuministro remoteSystemAdministracionSolicitudSuministro) {
		this.remoteSystemAdministracionSolicitudSuministro = remoteSystemAdministracionSolicitudSuministro;

	}

	// </editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionBienes, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionOrdenCompra">
	// @EJB
	private SystemAdministracionOrdenCompra remoteSystemAdministracionOrdenCompra = null;

	public SystemAdministracionOrdenCompra getRemoteSystemAdministracionOrdenCompra() {
		// try {
		// if (this.remoteSystemAdministracionOrdenCompra == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionOrdenCompraHome.JNDI_NAME);
		// SystemAdministracionOrdenCompraHome locAdministracionOrdenCompra =
		// (SystemAdministracionOrdenCompraHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionOrdenCompraHome.class);
		// this.remoteSystemAdministracionOrdenCompra =
		// locAdministracionOrdenCompra.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemAdministracionOrdenCompra;
	}

	public void setRemoteSystemAdministracionOrdenCompra(SystemAdministracionOrdenCompra remoteSystemAdministracionOrdenCompra) {
		this.remoteSystemAdministracionOrdenCompra = remoteSystemAdministracionOrdenCompra;

	}

	// </editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionBienes, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionCondicionCompra">
	// @EJB
	private SystemAdministracionCondicionCompra remoteSystemAdministracionCondicionCompra = null;

	public SystemAdministracionCondicionCompra getRemoteSystemAdministracionCondicionCompra() {
		// try {
		// if (this.remoteSystemAdministracionCondicionCompra == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionCondicionCompraHome.JNDI_NAME);
		// SystemAdministracionCondicionCompraHome
		// locAdministracionCondicionCompra =
		// (SystemAdministracionCondicionCompraHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionCondicionCompraHome.class);
		// this.remoteSystemAdministracionCondicionCompra =
		// locAdministracionCondicionCompra.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemAdministracionCondicionCompra;
	}

	public void setRemoteSystemAdministracionCondicionCompra(SystemAdministracionCondicionCompra remoteSystemAdministracionCondicionCompra) {
		this.remoteSystemAdministracionCondicionCompra = remoteSystemAdministracionCondicionCompra;

	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionLicitacion">
	// @EJB
	private SystemAdministracionLicitacion remoteSystemAdministracionLicitacion = null;

	public SystemAdministracionLicitacion getRemoteSystemAdministracionLicitacion() {
		// try {
		// if (this.remoteSystemAdministracionCondicionCompra == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionCondicionCompraHome.JNDI_NAME);
		// SystemAdministracionCondicionCompraHome
		// locAdministracionCondicionCompra =
		// (SystemAdministracionCondicionCompraHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionCondicionCompraHome.class);
		// this.remoteSystemAdministracionCondicionCompra =
		// locAdministracionCondicionCompra.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemAdministracionLicitacion;
	}

	public void setRemoteSystemAdministracionLicitacion(SystemAdministracionLicitacion remoteSystemAdministracionLicitacion) {
		this.remoteSystemAdministracionLicitacion = remoteSystemAdministracionLicitacion;

	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemReportesCompras">
	// @EJB
	private SystemReportesCompras remoteSystemReportesCompras = null;

	public SystemReportesCompras getRemoteSystemReportesCompras() {
		// try {
		// if (this.remoteSystemAdministracionCondicionCompra == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionCondicionCompraHome.JNDI_NAME);
		// SystemAdministracionCondicionCompraHome
		// locAdministracionCondicionCompra =
		// (SystemAdministracionCondicionCompraHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionCondicionCompraHome.class);
		// this.remoteSystemAdministracionCondicionCompra =
		// locAdministracionCondicionCompra.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemReportesCompras;
	}

	public void setRemoteSystemReportesCompras(SystemReportesCompras remoteSystemReportesCompras) {
		this.remoteSystemReportesCompras = remoteSystemReportesCompras;

	}

	// </editor-fold>
	// /**
	// * Definición de la interfaz remota SystemAdministracionConsultaContable,
	// para invocar
	// * la lógica de negocio.
	// */
	// // <editor-fold defaultstate="collapsed"
	// desc="SystemAdministracionConsultaContable">
	// private SystemAdministracionConsultaContable
	// remoteSystemAdministracionConsultaContable = null;
	//
	// public SystemAdministracionConsultaContable
	// getRemoteSystemAdministracionConsultaContable(){
	// try{
	// if(this.remoteSystemAdministracionConsultaContable == null){
	// Context ctx = new InitialContext(getProps());
	// Object obj =
	// ctx.lookup(SystemAdministracionConsultaContableHome.JNDI_NAME);
	// SystemAdministracionConsultaContableHome
	// locAdministracionConsultaContable =
	// (SystemAdministracionConsultaContableHome)
	// PortableRemoteObject.narrow(obj,
	// SystemAdministracionConsultaContableHome.class);
	// this.remoteSystemAdministracionConsultaContable =
	// locAdministracionConsultaContable.create();
	// }
	// }catch(Exception ex){
	// ex.printStackTrace();
	// }
	// return remoteSystemAdministracionConsultaContable;
	//
	// }
	// //</editor-fold>
	// @EJB
	private SystemAdministracionFacturaProveedor remoteSystemAdministracionFacturaProveedor = null;

	public SystemAdministracionFacturaProveedor getRemoteSystemAdministracionFacturaProveedor() {
		// try {
		// if (this.remoteSystemAdministracionFacturaProveedor == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj =
		// ctx.lookup(SystemAdministracionFacturaProveedorHome.JNDI_NAME);
		// SystemAdministracionFacturaProveedorHome
		// locAdministracionFacturaProveedor =
		// (SystemAdministracionFacturaProveedorHome)
		// PortableRemoteObject.narrow(obj,
		// SystemAdministracionFacturaProveedorHome.class);
		// this.remoteSystemAdministracionFacturaProveedor =
		// locAdministracionFacturaProveedor.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemAdministracionFacturaProveedor;
	}

	public void setRemoteSystemAdministracionFacturaProveedor(SystemAdministracionFacturaProveedor remoteSystemAdministracionFacturaProveedor) {
		this.remoteSystemAdministracionFacturaProveedor = remoteSystemAdministracionFacturaProveedor;

	}

	private SystemAdministracionFactura remoteSystemAdministracionFactura = null;

	public SystemAdministracionFactura getRemoteSystemAdministracionFactura() {
		return remoteSystemAdministracionFactura;
	}

	public void setRemoteSystemAdministracionFactura(SystemAdministracionFactura remoteSystemAdministracionFactura) {
		this.remoteSystemAdministracionFactura = remoteSystemAdministracionFactura;

	}

	/**
	 * Sección de declaración de Listas que completan las listas
	 * 
	 */
	// <editor-fold defaultstate="collapsed" desc="ArrayList Bienes">
	// Lita de Bienes
	private List listaEstadoSolicitud = null;

	public List getListaEstadoSolicitud() {
		return listaEstadoSolicitud;
	}

	public void setListaEstadoSolicitud(List listaEstadoSolicitud) {
		this.listaEstadoSolicitud = listaEstadoSolicitud;
	}

	private List listaTipoBienes = null;

	public List getListaTipoBienes() {
		return listaTipoBienes;
	}

	public void setListaTipoBienes(List listaTipoBienes) {
		this.listaTipoBienes = listaTipoBienes;
	}

	private List listaTipoBienesBien = null;

	public List getListaTipoBienesBien() {
		return listaTipoBienesBien;
	}

	public void setListaTipoBienesBien(List listaTipoBienesBien) {
		this.listaTipoBienesBien = listaTipoBienesBien;
	}

	private List listaTipoBienesProveedor = null;

	public List getListaTipoBienesProveedor() {
		return listaTipoBienesProveedor;
	}

	public void setListaTipoBienesProveedor(List listaTipoBienesProveedor) {
		this.listaTipoBienesProveedor = listaTipoBienesProveedor;
	}

	private List listaBienes = null;

	public List getListaBienes() {
		return listaBienes;
	}

	public void setListaBienes(List pListaBienes) {
		this.listaBienes = pListaBienes;
	}

	// </editor-fold>
	private List listaProveedores = null;

	public List getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List pListaProveedores) {
		this.listaProveedores = pListaProveedores;
	}

	private List listaLicitaciones = null;

	public List getListaLicitaciones() {
		return listaLicitaciones;
	}

	public void setListaLicitaciones(List pListaLicitaciones) {
		this.listaLicitaciones = pListaLicitaciones;
	}

	private ArrayList listaRenglonesLicitacion = null;

	public ArrayList getListaRenglonesLicitacion() {
		return listaRenglonesLicitacion;
	}

	public void setListaRenglonesLicitacion(ArrayList pListaRenglonesLicitacion) {
		this.listaRenglonesLicitacion = pListaRenglonesLicitacion;
	}

	private List listaOfertasLicitaciones = null;

	public List getListaOfertasLicitaciones() {
		return listaOfertasLicitaciones;
	}

	public void setListaOfertasLicitaciones(List pListaOfertasLicitaciones) {
		this.listaOfertasLicitaciones = pListaOfertasLicitaciones;
	}

	private List listaPresupuestosSolSum = null;

	public List getListaPresupuestosSolSum() {
		return listaPresupuestosSolSum;
	}

	public void setListaPresupuestosSolSum(List listaPresupuestosSolSum) {
		this.listaPresupuestosSolSum = listaPresupuestosSolSum;
	}

	private List listaMovimientoMercaderiaSolSum = null;
	private List listaMovimientoMercaderiaOrdenCompra = null;
	private List listaLineaOrdenCompraSolSum = null;

	public List getListaLineaOrdenCompraSolSum() {
		return listaLineaOrdenCompraSolSum;
	}

	public void setListaLineaOrdenCompraSolSum(List listaLineaOrdenCompraSolSum) {
		this.listaLineaOrdenCompraSolSum = listaLineaOrdenCompraSolSum;
	}

	public List getListaMovimientoMercaderiaSolSum() {
		return listaMovimientoMercaderiaSolSum;
	}

	public void setListaMovimientoMercaderiaSolSum(List listaMovimientoMercaderiaSolSum) {
		this.listaMovimientoMercaderiaSolSum = listaMovimientoMercaderiaSolSum;
	}

	public List getListaMovimientoMercaderiaOrdenCompra() {
		return listaMovimientoMercaderiaOrdenCompra;
	}

	public void setListaMovimientoMercaderiaOrdenCompra(List listaMovimientoMercaderiaOrdenCompra) {
		this.listaMovimientoMercaderiaOrdenCompra = listaMovimientoMercaderiaOrdenCompra;
	}

	private List listaLineasPresupuestosSolSum = null;

	public List getListaLineasPresupuestosSolSum() {
		return listaLineasPresupuestosSolSum;
	}

	public void setListaLineasPresupuestosSolSum(List listaLineasPresupuestosSolSum) {
		this.listaLineasPresupuestosSolSum = listaLineasPresupuestosSolSum;
	}

	private List listaAtributosDinamicosFacturaProveedor = null;

	public List getListaAtributosDinamicosFacturaProveedor() {
		return listaAtributosDinamicosFacturaProveedor;
	}

	public void setListaAtributosDinamicosFacturaProveedor(List listaAtributosDinamicosFacturaProveedor) {
		this.listaAtributosDinamicosFacturaProveedor = listaAtributosDinamicosFacturaProveedor;
	}

	private List listaOfertaRenglonLicitacion = null;

	public List getListaOfertaRenglonLicitacion() {
		return listaOfertaRenglonLicitacion;
	}

	public void setListaOfertaRenglonLicitacion(List pListaOfertaRenglonLicitacion) {
		this.listaOfertaRenglonLicitacion = pListaOfertaRenglonLicitacion;
	}

	private List listaMovIngresos = null;

	public List getListaMovIngresos() {
		return listaMovIngresos;
	}

	public void setListaMovIngresos(List pListaMovIngresos) {
		this.listaMovIngresos = pListaMovIngresos;
	}

	private List listaMovEgresos = null;

	public List getListaMovEgresos() {
		return listaMovEgresos;
	}

	public void setListaMovEgresos(List pListaMovEgresos) {
		this.listaMovEgresos = pListaMovEgresos;
	}

	/**
	 * Marina Lista de unidades de Medida
	 */
	private List listaUnidades;

	public List getListaUnidades() {
		return listaUnidades;
	}

	public void setListaUnidades(List listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	/*
	 * Mines para las facturasSubsidios
	 */
	private List listaFacturaSubsidios = null;

	/**
	 * Getter para propiedad listaFacturaSubsidios.
	 * 
	 * @return Valor de la propiedad listaFacturaSubsidios.
	 */
	public List getListaFacturaSubsidio() {
		return listaFacturaSubsidios;
	}

	/**
	 * Setter para propiedad listaFacturaSubsidios.
	 * 
	 * @param listaFacturaSubsidios
	 *            Nuevo valor de la propiedad listaFacturaSubsidios.
	 */
	public void setListaFacturaSubsidios(List pListaFacturaSubsidios) {
		this.listaFacturaSubsidios = pListaFacturaSubsidios;
	}

	/**
	 * Conserva el valor de la propiedad listaLineasFacturaSubsidio.
	 */
	private ArrayList listaLineasFacturaSubsidio;

	/**
	 * Getter para propiedad listaLineasFacturaSubsidio.
	 * 
	 * @return Valor de la propiedad listaLineasFacturaSubsidio.
	 */
	public ArrayList getListaLineasFacturaSubsidio() {

		return this.listaLineasFacturaSubsidio;
	}

	/**
	 * Setter para propiedad listaLineasFacturaProveedor.
	 * 
	 * @param listaLineasFacturaProveedor
	 *            Nuevo valor de la propiedad listaLineasFacturaProveedor.
	 */
	public void setListaLineasFacturaSubsidio(ArrayList listaLineasFacturaSubsidio) {

		this.listaLineasFacturaSubsidio = listaLineasFacturaSubsidio;
	}

	private TableSelectPhaseListener tablePhaseListenerLineaOrdenCompra = new TableSelectPhaseListener();
	private TableSelectPhaseListener tablePhaseListenerLineaSolSuministro = new TableSelectPhaseListener();
	private TableSelectPhaseListener tablePhaseListenerCodigoCiiu = new TableSelectPhaseListener();
	private TableSelectPhaseListener tablePhaseListenerLineaContratacion = new TableSelectPhaseListener();
	private TableSelectPhaseListener tablePhaseListenerTipoBien = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerTipoBien() {
		return tablePhaseListenerTipoBien;
	}

	public void setTablePhaseListenerTipoBien(TableSelectPhaseListener tablePhaseListenerTipoBien) {
		this.tablePhaseListenerTipoBien = tablePhaseListenerTipoBien;
	}

	public TableSelectPhaseListener getTablePhaseListenerCodigoCiiu() {
		return tablePhaseListenerCodigoCiiu;
	}

	public void setTablePhaseListenerCodigoCiiu(TableSelectPhaseListener tablePhaseListenerCodigoCiiu) {
		this.tablePhaseListenerCodigoCiiu = tablePhaseListenerCodigoCiiu;
	}

	public TableSelectPhaseListener getTablePhaseListenerLineaSolSuministro() {
		return tablePhaseListenerLineaSolSuministro;
	}

	public void setTablePhaseListenerLineaSolSuministro(TableSelectPhaseListener tablePhaseListenerLineaSolSuministro) {
		this.tablePhaseListenerLineaSolSuministro = tablePhaseListenerLineaSolSuministro;
	}

	public TableSelectPhaseListener getTablePhaseListenerLineaOrdenCompra() {
		return this.tablePhaseListenerLineaOrdenCompra;
	}

	public void setTablePhaseListenerLineaOrdenCompra(TableSelectPhaseListener tablePhaseListenerLineaOrdenCompra) {
		this.tablePhaseListenerLineaOrdenCompra = tablePhaseListenerLineaOrdenCompra;
	}

	public TableSelectPhaseListener getTablePhaseListenerLineaContratacion() {
		return tablePhaseListenerLineaContratacion;
	}

	public void setTablePhaseListenerLineaContratacion(TableSelectPhaseListener tablePhaseListenerLineaContratacion) {
		this.tablePhaseListenerLineaContratacion = tablePhaseListenerLineaContratacion;
	}

	private TableSelectPhaseListener tablePhaseListenerBienes = new TableSelectPhaseListener();

	/**
	 * Getter para propiedad tablePhaseListenerCuadras.
	 * 
	 * @return Valor de la propiedad tablePhaseListenerCuadras.
	 */
	public TableSelectPhaseListener getTablePhaseListenerBienes() {

		return this.tablePhaseListenerBienes;
	}

	/**
	 * Setter para propiedad tablePhaseListenerCuadras.
	 * 
	 * @param tablePhaseListenerCuadras
	 *            Nuevo valor de la propiedad tablePhaseListenerCuadras.
	 */
	public void setTablePhaseListenerBienes(TableSelectPhaseListener tablePhaseListenerBienes) {

		this.tablePhaseListenerBienes = tablePhaseListenerBienes;
	}

	public List getListaLineasFactura() {
		return listaLineasFactura;
	}

	public void setListaLineasFactura(ArrayList listaLineasFactura) {
		this.listaLineasFactura = listaLineasFactura;
	}

	/*
	 * Marcos Para las facturas de los contratos
	 */
	private ArrayList listaFacturaContratos = null;

	/**
	 * Getter para propiedad listaFacturaContratos.
	 * 
	 * @return Valor de la propiedad listaFacturaContratos.
	 */
	public ArrayList getListaFacturaContratos() {
		return listaFacturaContratos;
	}

	/**
	 * Setter para la propiedad listaFacturaContratos.
	 * 
	 * @param listaFacturaContratos
	 *            Nuevo valor de la propiedad listaFacturaContratos.
	 */
	public void setListaFacturaContratos(ArrayList pListaFacturaContratos) {
		this.listaFacturaContratos = pListaFacturaContratos;
	}

	/**
	 * Marcos Conserva el valor de la propiedad listaLineasFacturaContrato.
	 */
	private ArrayList listaLineasFacturaContrato;

	/**
	 * Getter para propiedad listaLineasFacturaContrato.
	 * 
	 * @return Valor de la propiedad listaLineasFacturaContrato.
	 */
	public ArrayList getListaLineasFacturaContrato() {

		return this.listaLineasFacturaContrato;
	}

	/**
	 * Setter para propiedad listaLineasFacturaProveedor.
	 * 
	 * @param listaLineasFacturaContrato
	 *            Nuevo valor de la propiedad listaLineasFacturaContrato.
	 */
	public void setListaLineasFacturaContrato(ArrayList listaLineasFacturaContrato) {

		this.listaLineasFacturaContrato = listaLineasFacturaContrato;
	}

	/**
	 * Marcos Conserva el valor de la propiedad listaLineasFacturaServicio.
	 */
	private ArrayList listaLineasFacturaServicio;

	/**
	 * Getter para propiedad listaLineasFacturaServicio.
	 * 
	 * @return Valor de la propiedad listaLineasFacturaServicio.
	 */
	public ArrayList getListaLineasFacturaServicio() {

		return this.listaLineasFacturaServicio;
	}

	/**
	 * Setter para propiedad listaLineasFacturaServicio.
	 * 
	 * @param listaLineasFacturaServicio
	 *            Nuevo valor de la propiedad listaLineasFacturaServicio.
	 */
	public void setListaLineasFacturaServicio(ArrayList locListaLineasFacturaServicio) {

		this.listaLineasFacturaServicio = locListaLineasFacturaServicio;
	}

	private List listaFacturaServicios = null;

	/**
	 * Getter para la propiedad listaFacturaServicios.
	 * 
	 * @return Valor de la propiedad listaFacturaServicios.
	 */
	public List getListaFacturaServicios() {
		return listaFacturaServicios;
	}

	/**
	 * Setter para la propiedad listaFacturaServicios.
	 * 
	 * @param listaFacturaServicios
	 *            Nuevo valor de la propiedad listaFacturaServicios.
	 */
	public void setListaFacturaServicios(List pListaFacturaServicios) {
		this.listaFacturaServicios = pListaFacturaServicios;
	}

	// ***hasta aca
	private List listaTipoOrdenCompra = null;

	public List getListaTipoOrdenCompra() {
		return listaTipoOrdenCompra;
	}

	public void setListaTipoOrdenCompra(List pListaTipoOrdenCompra) {
		this.listaTipoOrdenCompra = pListaTipoOrdenCompra;
	}

	private List listaSolicitudesSuministro = null;

	public List getListaSolicitudesSuministro() {
		return listaSolicitudesSuministro;
	}

	public void setListaSolicitudesSuministro(List pListaSolicitudesSuministro) {
		this.listaSolicitudesSuministro = pListaSolicitudesSuministro;
	}

	private List listaUsuarioTipoOrdenCompra = null;

	public List getListaUsuarioTipoOrdenCompra() {
		return listaUsuarioTipoOrdenCompra;
	}

	public void setListaUsuarioTipoOrdenCompra(List pListaUsuarioTipoOrdenCompra) {
		this.listaUsuarioTipoOrdenCompra = pListaUsuarioTipoOrdenCompra;
	}

	/**
	 * Conserva el valor de la propiedad listaBienesProvistos.
	 */
	private List listaBienesProvistos;

	/**
	 * Getter para propiedad listaBienesProvistos.
	 * 
	 * @return Valor de la propiedad listaBienesProvistos.
	 */
	public List getListaBienesProvistos() {

		return this.listaBienesProvistos;
	}

	/**
	 * Setter para propiedad listaBienesProvistos.
	 * 
	 * @param listaBienesProvistos
	 *            Nuevo valor de la propiedad listaBienesProvistos.
	 */
	public void setListaBienesProvistos(List listaBienesProvistos) {
		System.out.println("SETEANDO EN COMMUNICATCION");
		System.out.println(listaBienesProvistos);
		this.listaBienesProvistos = listaBienesProvistos;
	}

	/**
	 * Conserva el valor de la propiedad listaCondicionesCompra.
	 */
	private List listaCondicionesCompra;

	/**
	 * Getter para propiedad listaCondicionesCompra.
	 * 
	 * @return Valor de la propiedad listaCondicionesCompra.
	 */
	public List getListaCondicionesCompra() {

		return this.listaCondicionesCompra;
	}

	/**
	 * Setter para propiedad listaCondicionesCompra.
	 * 
	 * @param listaCondicionesCompra
	 *            Nuevo valor de la propiedad listaCondicionesCompra.
	 */
	public void setListaCondicionesCompra(List listaCondicionesCompra) {

		this.listaCondicionesCompra = listaCondicionesCompra;
	}

	/**
	 * Conserva el valor de la propiedad listaGruposBienes.
	 */
	private List listaGruposBienes;

	/**
	 * Getter para propiedad listaGruposBienes.
	 * 
	 * @return Valor de la propiedad listaGruposBienes.
	 */
	public List getListaGruposBienes() {

		return this.listaGruposBienes;
	}

	/**
	 * Setter para propiedad listaGruposBienes.
	 * 
	 * @param listaGruposBienes
	 *            Nuevo valor de la propiedad listaGruposBienes.
	 */
	public void setListaGruposBienes(List listaGruposBienes) {

		this.listaGruposBienes = listaGruposBienes;
	}

	private ArrayList listaPasesArticulo = null;

	public ArrayList getListaPasesArticulo() {
		return listaPasesArticulo;
	}

	public void setListaPasesArticulo(ArrayList listaPasesArticulo) {
		this.listaPasesArticulo = listaPasesArticulo;
	}

	/**
	 * Conserva el valor de la propiedad listaGruposProveedores.
	 */
	private List listaGruposProveedores;

	/**
	 * Getter para propiedad listaGruposProveedores.
	 * 
	 * @return Valor de la propiedad listaGruposProveedores.
	 */
	public List getListaGruposProveedores() {

		return this.listaGruposProveedores;
	}

	/**
	 * Setter para propiedad listaGruposProveedores.
	 * 
	 * @param listaGruposProveedores
	 *            Nuevo valor de la propiedad listaGruposProveedores.
	 */
	public void setListaGruposProveedores(List listaGruposProveedores) {

		this.listaGruposProveedores = listaGruposProveedores;
	}

	/**
	 * Conserva el valor de la propiedad listaOrdenesCompra.
	 */
	private List listaOrdenesCompra;

	/**
	 * Getter para propiedad listaOrdenesCompra.
	 * 
	 * @return Valor de la propiedad listaOrdenesCompra.
	 */
	public List getListaOrdenesCompra() {

		return this.listaOrdenesCompra;
	}

	/**
	 * Setter para propiedad listaOrdenesCompra.
	 * 
	 * @param listaOrdenesCompra
	 *            Nuevo valor de la propiedad listaOrdenesCompra.
	 */
	public void setListaOrdenesCompra(List listaOrdenesCompra) {

		this.listaOrdenesCompra = listaOrdenesCompra;
	}

	/**
	 * Conserva el valor de la propiedad listaSolicitudesSuministros.
	 */
	private ArrayList listaSolicitudesSuministros;

	/**
	 * Getter para propiedad listaSolicitudesSuministros.
	 * 
	 * @return Valor de la propiedad listaSolicitudesSuministros.
	 */
	public ArrayList getListaSolicitudesSuministros() {

		return this.listaSolicitudesSuministros;
	}

	/**
	 * Setter para propiedad listaSolicitudesSuministros.
	 * 
	 * @param listaSolicitudesSuministros
	 *            Nuevo valor de la propiedad listaSolicitudesSuministros.
	 */
	public void setListaSolicitudesSuministros(ArrayList listaSolicitudesSuministros) {

		this.listaSolicitudesSuministros = listaSolicitudesSuministros;
	}

	/**
	 * Conserva el valor de la propiedad listaLineasOrdenesCompra.
	 */
	private List listaLineasOrdenesCompra;

	/**
	 * Getter para propiedad listaLineasOrdenesCompra.
	 * 
	 * @return Valor de la propiedad listaLineasOrdenesCompra.
	 */
	public List getListaLineasOrdenesCompra() {

		return this.listaLineasOrdenesCompra;
	}

	/**
	 * Setter para propiedad listaLineasOrdenesCompra.
	 * 
	 * @param listaLineasOrdenesCompra
	 *            Nuevo valor de la propiedad listaLineasOrdenesCompra.
	 */
	public void setListaLineasOrdenesCompra(List listaLineasOrdenesCompra) {

		this.listaLineasOrdenesCompra = listaLineasOrdenesCompra;
	}

	/**
	 * Conserva el valor de la propiedad lista.
	 */
	private List listaLineasFactura;

	/**
	 * Getter para propiedad lista.
	 * 
	 * @return Valor de la propiedad lista.
	 */
	public List getListaLineaFacturas() {

		return this.listaLineasFactura;
	}

	/**
	 * Setter para propiedad lista.
	 * 
	 * @param listas
	 *            Nuevo valor de la propiedad listas.
	 */
	public void setListaLineaFacturas(List listaLineasF) {

		this.listaLineasFactura = listaLineasF;
	}

	/**
	 * Conserva el valor de la propiedad lista.
	 */
	private ArrayList listaFirmaPermisos;

	/**
	 * Getter para propiedad lista.
	 * 
	 * @return Valor de la propiedad lista.
	 */
	public ArrayList getListaFirmaPermisos() {
		return this.listaFirmaPermisos;
	}

	/**
	 * Setter para propiedad lista.
	 * 
	 * @param listas
	 *            Nuevo valor de la propiedad listas.
	 */
	public void setListaFirmaPermisos(ArrayList listaFirmaPermisos) {
		this.listaFirmaPermisos = listaFirmaPermisos;
	}

	/**
	 * Conserva el valor de la propiedad lista.
	 */
	private List listaLineasSoLSuministro;

	/**
	 * Getter para propiedad lista.
	 * 
	 * @return Valor de la propiedad lista.
	 */
	public List getListaLineasSoLSuministro() {

		return this.listaLineasSoLSuministro;
	}

	/**
	 * Setter para propiedad lista.
	 * 
	 * @param listas
	 *            Nuevo valor de la propiedad listas.
	 */
	public void setListaLineasSoLSuministro(List listaLineasS) {

		this.listaLineasSoLSuministro = listaLineasS;
	}

	private List listaLineasSolSumContratacion;

	public List getListaLineasSolSumContratacion() {
		return listaLineasSolSumContratacion;
	}

	public void setListaLineasSolSumContratacion(List listaLineasSolSumContratacion) {
		this.listaLineasSolSumContratacion = listaLineasSolSumContratacion;
	}

	/**
	 * Conserva el valor de la propiedad listaFacturas.
	 */
	private List listaFacturas;

	/**
	 * Getter para propiedad listaFacturas.
	 * 
	 * @return Valor de la propiedad listaFacturas.
	 */
	public List getListaFacturas() {

		return this.listaFacturas;
	}

	/**
	 * Setter para propiedad listaFacturas.
	 * 
	 * @param listaFacturas
	 *            Nuevo valor de la propiedad listaFacturas.
	 */
	public void setListaFacturas(List listaFacturas) {

		this.listaFacturas = listaFacturas;
	}

	/**
	 * Conserva el valor de la propiedad listaFacturasSubsidios.
	 */
	private ArrayList listaFacturasSubsidio;

	/**
	 * Getter para propiedad listaFacturasSubsidios.
	 * 
	 * @return Valor de la propiedad listaFacturasSubsidios.
	 */
	public ArrayList getListaFacturasSubsidio() {

		return this.listaFacturasSubsidio;
	}

	/**
	 * Setter para propiedad listaFacturasSubsidios.
	 * 
	 * @param listaFacturas
	 *            Nuevo valor de la propiedad listaFacturasSubsidios.
	 */
	public void setListaFacturasSubsidios(ArrayList listaFacturas) {

		this.listaFacturasSubsidio = listaFacturas;
	}

	/**
	 * Conserva el valor de la propiedad listaLineasFacturaProveedor.
	 */
	private ArrayList listaLineasFacturaProveedor;

	/**
	 * Getter para propiedad listaLineasFacturaProveedor.
	 * 
	 * @return Valor de la propiedad listaLineasFacturaProveedor.
	 */
	public ArrayList getListaLineasFacturaProveedor() {

		return this.listaLineasFacturaProveedor;
	}

	/**
	 * Setter para propiedad listaLineasFacturaProveedor.
	 * 
	 * @param listaLineasFacturaProveedor
	 *            Nuevo valor de la propiedad listaLineasFacturaProveedor.
	 */
	public void setListaLineasFacturaProveedor(ArrayList listaLineasFacturaProveedor) {

		this.listaLineasFacturaProveedor = listaLineasFacturaProveedor;
	}

	/**
	 * Conserva el valor de la propiedad listaFacturaCompras.
	 */
	private List listaFacturaCompras;

	/**
	 * Getter para propiedad listaFacturaCompras.
	 * 
	 * @return Valor de la propiedad listaFacturaCompras.
	 */
	public List getListaFacturaCompras() {

		return this.listaFacturaCompras;
	}

	/**
	 * Setter para propiedad listaFacturaCompras.
	 * 
	 * @param listaFacturas
	 *            Nuevo valor de la propiedad listaFacturaCompras.
	 */
	public void setListaFacturaCompras(List listaFacturaCompras) {

		this.listaFacturaCompras = listaFacturaCompras;
	}

	/**
	 * Conserva el valor de la propiedad listaFirmasPermisos.
	 */
	/**
	 * Getter para propiedad listaFirmasPermisos.
	 * 
	 * @return Valor de la propiedad listaFirmasPermisos.
	 */
	/**
	 * Setter para propiedad listaFirmasPermisos.
	 * 
	 * @param listaFirmasPermisos
	 *            Nuevo valor de la propiedad listaFirmasPermisos.
	 */
	/**
	 * Conserva el valor de la propiedad listaAutorizaciones.
	 */
	private List listaAutorizaciones;

	/**
	 * Getter para propiedad listaAutorizaciones.
	 * 
	 * @return Valor de la propiedad listaAutorizaciones.
	 */
	public List getListaAutorizaciones() {

		return this.listaAutorizaciones;
	}

	/**
	 * Setter para propiedad listaAutorizaciones.
	 * 
	 * @param listaAutorizaciones
	 *            Nuevo valor de la propiedad listaAutorizaciones.
	 */
	public void setListaAutorizaciones(List listaAutorizaciones) {

		this.listaAutorizaciones = listaAutorizaciones;
	}

	/**
	 * Conserva el valor de la propiedad listaUsuariosAutorizadores.
	 */
	private List listaUsuariosAutorizadores;

	/**
	 * Getter para propiedad listaUsuariosAutorizadores.
	 * 
	 * @return Valor de la propiedad listaUsuariosAutorizadores.
	 */
	public List getListaUsuariosAutorizadores() {
		return this.listaUsuariosAutorizadores;
	}

	/**
	 * Setter para propiedad listaUsuariosAutorizadores.
	 * 
	 * @param listaUsuariosAutorizadores
	 *            Nuevo valor de la propiedad listaUsuariosAutorizadores.
	 */
	public void setListaUsuariosAutorizadores(List listaUsuariosAutorizadores) {
		this.listaUsuariosAutorizadores = listaUsuariosAutorizadores;
	}

	// Inventario:
	/**
	 * Definición de la interfaz remota SystemAdministracionMesa, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed" desc="SystemAdministracionBienes">
	// @EJB
	private SystemStock remoteSystemStock = null;

	public SystemStock getRemoteSystemStock() {
		// try {
		// if (this.remoteSystemStock == null) {
		// Context ctx = new InitialContext(getProps());
		// Object obj = ctx.lookup(SystemStockHome.JNDI_NAME);
		// SystemStockHome locStockHome = (SystemStockHome)
		// PortableRemoteObject.narrow(obj, SystemStockHome.class);
		// this.remoteSystemStock = locStockHome.create();
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return remoteSystemStock;
	}

	public void setRemoteSystemStock(SystemStock remoteSystemStock) {
		this.remoteSystemStock = remoteSystemStock;
	}

	// </editor-fold>
	/**
	 * Definición de la interfaz remota SystemAdministracionBienes, para invocar la lógica de negocio.
	 */
	// <editor
	/**
	 * Sección de declaración de Listas que completan las listas
	 * 
	 */
	// Lista de Articulos
	// </editor-fold>
	private List listaArticulos = new ArrayList();

	public List getListaArticulos() {
		return listaArticulos;
	}

	public void setListaArticulos(List listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

	// Lista de Depositos
	// </editor-fold>
	private List listaDepositos = new ArrayList();

	/**
	 * Getter para propiedad ListaTramites
	 * 
	 * @return Valor de la propiedad listaTramites.
	 */
	public List getListaDepositos() {
		return this.listaDepositos;
	}

	/**
	 * Setter para propiedad listaDepositos
	 * 
	 * @param listaDepositos
	 *            Nuevo valor de la propiedad Depositos.
	 */
	public void setListaDepositos(List pListaDepositos) {
		this.listaDepositos = pListaDepositos;
	}

	private List listaStockEnDeposito = null;

	/**
	 * Getter para propiedad ListaStockEnDeposito
	 * 
	 * @return Valor de la propiedad listaDepositos.
	 */
	public List getListaStockEnDeposito() {
		return this.listaStockEnDeposito;
	}

	/**
	 * Setter para propiedad listaStockEnDeposito
	 * 
	 * @param listaStockEnDeposito
	 *            Nuevo valor de la propiedad listaDeposito.
	 */
	public void setListaStockEnDeposito(List pListaStockEnDeposito) {
		this.listaStockEnDeposito = pListaStockEnDeposito;
	}

	// Lista de Stock
	// </editor-fold>
	private ArrayList listaStocks = null;

	/**
	 * Getter para propiedad ListaStock
	 * 
	 * @return Valor de la propiedad listaStock.
	 */
	public ArrayList getListaStocks() {
		return this.listaStocks;
	}

	/**
	 * Setter para propiedad listaTramites
	 * 
	 * @param listaBienesProvistos
	 *            Nuevo valor de la propiedad listaTramites.
	 */
	public void setListaStocks(ArrayList pListaStocks) {
		this.listaStocks = pListaStocks;
	}

	// Lista de MovimientosDeMercaderia
	// </editor-fold>
	private List listaMovimientosDeMercaderia = null;

	/**
	 * Getter para propiedad ListaStock
	 * 
	 * @return Valor de la propiedad listaStock.
	 */
	public List getListaMovimientosDeMercaderia() {
		return this.listaMovimientosDeMercaderia;
	}

	/**
	 * Setter para propiedad listaTramites
	 * 
	 * @param listaBienesProvistos
	 *            Nuevo valor de la propiedad listaTramites.
	 */
	public void setListaMovimientosDeMercaderia(List pListaMovimientosDeMercaderia) {
		this.listaMovimientosDeMercaderia = pListaMovimientosDeMercaderia;
	}

	private TableSelectPhaseListener tablePhaseListenerStockEnDeposito = new TableSelectPhaseListener();

	public TableSelectPhaseListener getTablePhaseListenerStockEnDeposito() {
		return this.tablePhaseListenerStockEnDeposito;
	}

	public void setTablePhaseListenerStockEnDeposito(TableSelectPhaseListener tablePhaseListenerStockEnDeposito) {
		this.tablePhaseListenerStockEnDeposito = tablePhaseListenerStockEnDeposito;
	}

	// Lista de Movimientos de Ingreso
	// </editor-fold>
	private ArrayList listaMovimientosIngreso = null;

	/**
	 * Getter para propiedad ListaMovimientosIngreso
	 * 
	 * @return Valor de la propiedad ListaMovimientosIngreso.
	 */
	public ArrayList getListaMovimientosIngreso() {
		return this.listaMovimientosIngreso;
	}

	/**
	 * Setter para propiedad listaMovimientosIngreso
	 * 
	 * @param listaMovimientosIngreso
	 *            Nuevo valor de la propiedad listaMovimientosIngreso.
	 */
	public void setListaMovimientosIngreso(ArrayList pListaMovimientosIngreso) {
		this.listaMovimientosIngreso = pListaMovimientosIngreso;
	}

	// Lista de Movimientos de Egreso
	// </editor-fold>
	private ArrayList listaMovimientosEgreso = null;

	/**
	 * Getter para propiedad ListaMovimientosEgreso
	 * 
	 * @return Valor de la propiedad ListaMovimientosEgreso.
	 */

	public ArrayList getListaMovimientosEgreso() {
		return listaMovimientosEgreso;
	}

	/**
	 * Setter para propiedad listaMovimientosEgreso
	 * 
	 * @param listaMovimientosEgreso
	 *            Nuevo valor de la propiedad listaMovimientosEgreso.
	 */

	public void setListaMovimientosEgreso(ArrayList listaMovimientosEgreso) {
		this.listaMovimientosEgreso = listaMovimientosEgreso;
	}

	private List listaCodigosCiiuBien = null;
	private List listaCodigosCiiuProveedor = null;

	public List getListaCodigosCiiuBien() {
		return listaCodigosCiiuBien;
	}

	public void setListaCodigosCiiuBien(List listaCodigosCiiuBien) {
		this.listaCodigosCiiuBien = listaCodigosCiiuBien;
	}

	public List getListaCodigosCiiuProveedor() {
		return listaCodigosCiiuProveedor;
	}

	public void setListaCodigosCiiuProveedor(List listaCodigosCiiuProveedor) {
		this.listaCodigosCiiuProveedor = listaCodigosCiiuProveedor;
	}

	private List listaActasApertura = null;

	public List getListaActasApertura() {
		return listaActasApertura;
	}

	public void setListaActasApertura(List listaActasApertura) {
		this.listaActasApertura = listaActasApertura;
	}

	private List listaRepresentantesActaApertura = null;

	public List getListaRepresentantesActaApertura() {
		return listaRepresentantesActaApertura;
	}

	public void setListaRepresentantesActaApertura(List listaRepresentantesActaApertura) {
		this.listaRepresentantesActaApertura = listaRepresentantesActaApertura;
	}

	private List listaLineasContratacion = null;

	public List getListaLineasContratacion() {
		return listaLineasContratacion;
	}

	public void setListaLineasContratacion(List listaLineasContratacion) {
		this.listaLineasContratacion = listaLineasContratacion;
	}

	private List listaUsuariosFirmantes;

	public List getListaUsuariosFirmantes() {
		return listaUsuariosFirmantes;
	}

	public void setListaUsuariosFirmantes(List listaUsuariosFirmantes) {
		this.listaUsuariosFirmantes = listaUsuariosFirmantes;
	}

	private PaginatedTable tablaArticulo;
	private PaginatedTable tablaDeposito;
	private PaginatedTable tablaMovimientoDeMercaderia;
	private PaginatedTable tablaBien;
	private PaginatedTable tablaUnidad;
	private PaginatedTable tablaOrdenCompra;
	private PaginatedTable tablaUsuarioFirmante;
	private PaginatedTable tablaTipoOrdenCompra;
	private PaginatedTable tablaCondicionCompra;
	private PaginatedTable tablaProveedor;
	private PaginatedTable tablaSolicitudSuministro;
	private PaginatedTable tablaAutorizacionSolicitudSuministro;
	private PaginatedTable tablaContratacion;
	private PaginatedTable tablaFacturaProveedor;
	private PaginatedTable tablaEstadoSolicitudSuministro;
	private PaginatedTable tablaTipoBien;
	private PaginatedTable tablaFacturaSubsidio;
	private PaginatedTable tablaFacturaContrato;
	private PaginatedTable tablaFacturaServicio;
	private PaginatedTable tablaLiquidacionCompra;

	public PaginatedTable getTablaFacturaServicio() {
		return tablaFacturaServicio;
	}

	public void setTablaFacturaServicio(PaginatedTable tablaFacturaServicio) {
		this.tablaFacturaServicio = tablaFacturaServicio;
	}

	public PaginatedTable getTablaFacturaContrato() {
		return tablaFacturaContrato;
	}

	public void setTablaFacturaContrato(PaginatedTable tablaFacturaContrato) {
		this.tablaFacturaContrato = tablaFacturaContrato;
	}

	public PaginatedTable getTablaFacturaSubsidio() {
		return tablaFacturaSubsidio;
	}

	public void setTablaFacturaSubsidio(PaginatedTable tablaFacturaSubsidio) {
		this.tablaFacturaSubsidio = tablaFacturaSubsidio;
	}

	public PaginatedTable getTablaTipoBien() {
		return tablaTipoBien;
	}

	public void setTablaTipoBien(PaginatedTable tablaTipoBien) {
		this.tablaTipoBien = tablaTipoBien;
	}

	public PaginatedTable getTablaEstadoSolicitudSuministro() {
		return tablaEstadoSolicitudSuministro;
	}

	public void setTablaEstadoSolicitudSuministro(PaginatedTable tablaEstadoSolicitudSuministro) {
		this.tablaEstadoSolicitudSuministro = tablaEstadoSolicitudSuministro;
	}

	public PaginatedTable getTablaContratacion() {
		return tablaContratacion;
	}

	public void setTablaContratacion(PaginatedTable tablaContratacion) {
		this.tablaContratacion = tablaContratacion;
	}

	public PaginatedTable getTablaFacturaProveedor() {
		return tablaFacturaProveedor;
	}

	public void setTablaFacturaProveedor(PaginatedTable tablaFacturaProveedor) {
		this.tablaFacturaProveedor = tablaFacturaProveedor;
	}

	public PaginatedTable getTablaBien() {
		return tablaBien;
	}

	public void setTablaBien(PaginatedTable tablaBien) {
		this.tablaBien = tablaBien;
	}

	public PaginatedTable getTablaUnidad() {
		return tablaUnidad;
	}

	public void setTablaUnidad(PaginatedTable tablaUnidad) {
		this.tablaUnidad = tablaUnidad;
	}

	public PaginatedTable getTablaOrdenCompra() {
		return tablaOrdenCompra;
	}

	public void setTablaOrdenCompra(PaginatedTable tablaOrdenCompra) {
		this.tablaOrdenCompra = tablaOrdenCompra;
	}

	public PaginatedTable getTablaUsuarioFirmante() {
		return tablaUsuarioFirmante;
	}

	public void setTablaUsuarioFirmante(PaginatedTable tablaUsuarioFirmante) {
		this.tablaUsuarioFirmante = tablaUsuarioFirmante;
	}

	public PaginatedTable getTablaTipoOrdenCompra() {
		return tablaTipoOrdenCompra;
	}

	public void setTablaTipoOrdenCompra(PaginatedTable tablaTipoOrdenCompra) {
		this.tablaTipoOrdenCompra = tablaTipoOrdenCompra;
	}

	public PaginatedTable getTablaCondicionCompra() {
		return tablaCondicionCompra;
	}

	public void setTablaCondicionCompra(PaginatedTable tablaCondicionCompra) {
		this.tablaCondicionCompra = tablaCondicionCompra;
	}

	public PaginatedTable getTablaProveedor() {
		return tablaProveedor;
	}

	public void setTablaProveedor(PaginatedTable tablaProveedor) {
		this.tablaProveedor = tablaProveedor;
	}

	public PaginatedTable getTablaSolicitudSuministro() {
		return tablaSolicitudSuministro;
	}

	public void setTablaSolicitudSuministro(PaginatedTable tablaSolicitudSuministro) {
		this.tablaSolicitudSuministro = tablaSolicitudSuministro;
	}

	public PaginatedTable getTablaAutorizacionSolicitudSuministro() {
		return tablaAutorizacionSolicitudSuministro;
	}

	public void setTablaAutorizacionSolicitudSuministro(PaginatedTable tablaAutorizacionSolicitudSuministro) {
		this.tablaAutorizacionSolicitudSuministro = tablaAutorizacionSolicitudSuministro;
	}

	public PaginatedTable getTablaDeposito() {
		return tablaDeposito;
	}

	public void setTablaDeposito(PaginatedTable tablaDeposito) {
		this.tablaDeposito = tablaDeposito;
	}

	public PaginatedTable getTablaArticulo() {
		return tablaArticulo;
	}

	public void setTablaArticulo(PaginatedTable tablaArticulo) {
		this.tablaArticulo = tablaArticulo;
	}

	public PaginatedTable getTablaMovimientoDeMercaderia() {
		return tablaMovimientoDeMercaderia;
	}

	public void setTablaMovimientoDeMercaderia(PaginatedTable tablaMovimientoDeMercaderia) {
		this.tablaMovimientoDeMercaderia = tablaMovimientoDeMercaderia;
	}

	private Map<String, Proveedor> mapaProveedores;

	public Map<String, Proveedor> getMapaProveedores() {
		return mapaProveedores;
	}

	public void setMapaProveedores(Map<String, Proveedor> mapaProveedores) {
		this.mapaProveedores = mapaProveedores;
	}

	private Map<Bien, LineaOfertaContratacion> mapaOpcionesAjudicacionSeleccionada = new HashMap<Bien, LineaOfertaContratacion>();

	public Map<Bien, LineaOfertaContratacion> getMapaOpcionesAjudicacionSeleccionada() {
		return mapaOpcionesAjudicacionSeleccionada;
	}

	public void setMapaOpcionesAjudicacionSeleccionada(Map<Bien, LineaOfertaContratacion> mapaOpcionesAjudicacionSeleccionada) {
		this.mapaOpcionesAjudicacionSeleccionada = mapaOpcionesAjudicacionSeleccionada;
	}

	private List listaProveedoresContratacion = null;

	public List getListaProveedoresContratacion() {
		return listaProveedoresContratacion;
	}

	public void setListaProveedoresContratacion(List listaProveedoresContratacion) {
		this.listaProveedoresContratacion = listaProveedoresContratacion;
	}

	private Proveedor proveedorSeleccionado;

	public Proveedor getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}

	private Map<String, Area> mapaAreasUsuarioCompras;

	/**
	 * @return Un HashMap que contiene las Areas del Usuario logueado, sin repetir y cuya llave es el nombre del Area. Ideal para usar en DropdsDowns.
	 */
	public Map<String, Area> getMapaAreasUsuarioCompras() {

		if(this.mapaAreasUsuarioCompras == null) {
			this.armarMapaAreas();
		}
		return mapaAreasUsuarioCompras;
	}

	public void setMapaAreasUsuarioCompras(Map<String, Area> mapaAreasUsuario) {
		this.mapaAreasUsuarioCompras = mapaAreasUsuario;
	}

	/**
	 * Arma el mapa de Areas del usuario logueado. Si el usuario es root, usa todas las areas del sistema.
	 */
	private void armarMapaAreas() {

		try {
			this.mapaAreasUsuarioCompras = new HashMap<String, Area>();
			this.getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());

			List<Area> locListaArea = (List<Area>) this.getRemoteSystemAdministracionSolicitudSuministro().findListaAreasCompras();

			Collections.sort(locListaArea, new Comparator<Area>() {

				@Override
				public int compare(Area o1, Area o2) {
					// para comparar sin acentos
					String area1 = Util.reemplazarAcentos(o1.getDescripcion());
					String area2 = Util.reemplazarAcentos(o2.getDescripcion());

					return area1.compareToIgnoreCase(area2);
				}
			});

			for(Area cadaArea : this.getRemoteSystemAdministracionSolicitudSuministro().findListaAreasCompras()) {
				this.mapaAreasUsuarioCompras.put(cadaArea.getNombre(), cadaArea);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	private List<UsuarioAutorizadorSolicitudSuministro> listaUsuariosAutorizadoresDesdeABM = new ArrayList<UsuarioAutorizadorSolicitudSuministro>();

	public List<UsuarioAutorizadorSolicitudSuministro> getListaUsuariosAutorizadoresDesdeABM() {
		return listaUsuariosAutorizadoresDesdeABM;
	}

	public void setListaUsuariosAutorizadoresDesdeABM(List<UsuarioAutorizadorSolicitudSuministro> listaUsuariosAutorizadoresDesdeABM) {
		this.listaUsuariosAutorizadoresDesdeABM = listaUsuariosAutorizadoresDesdeABM;
	}

	private List listaUsuariosAutorizadoresSuplentes;

	public List getListaUsuariosAutorizadoresSuplentes() {
		return listaUsuariosAutorizadoresSuplentes;
	}

	public void setListaUsuariosAutorizadoresSuplentes(List listaUsuariosAutorizadoresSuplentes) {
		this.listaUsuariosAutorizadoresSuplentes = listaUsuariosAutorizadoresSuplentes;
	}

	private List listaPagosOrdenCompra;

	public List getListaPagosOrdenCompra() {
		return listaPagosOrdenCompra;
	}

	public void setListaPagosOrdenCompra(List listaPagosOrdenCompra) {
		this.listaPagosOrdenCompra = listaPagosOrdenCompra;
	}

	private List listaReglasFirmasSolSum;

	public List getListaReglasFirmasSolSum() {
		return listaReglasFirmasSolSum;
	}

	public void setListaReglasFirmasSolSum(List listaReglasFirmasSolSum) {
		this.listaReglasFirmasSolSum = listaReglasFirmasSolSum;
	}

	private List listaUsuariosAutorizadosABMReglaFirma;

	public List getListaUsuariosAutorizadosABMReglaFirma() {
		return listaUsuariosAutorizadosABMReglaFirma;
	}

	public void setListaUsuariosAutorizadosABMReglaFirma(List listaUsuariosAutorizadosABMReglaFirma) {
		this.listaUsuariosAutorizadosABMReglaFirma = listaUsuariosAutorizadosABMReglaFirma;
	}

	private List listaEstadosSolSum;

	public List getListaEstadosSolSum() {
		return listaEstadosSolSum;
	}

	public void setListaEstadosSolSum(List listaEstadosSolSum) {
		this.listaEstadosSolSum = listaEstadosSolSum;
	}

	private List listaPagos;

	public List getListaPagos() {
		return listaPagos;
	}

	public void setListaPagos(List listaPagos) {
		this.listaPagos = listaPagos;
	}

	private List<OrdenCompra> ordenesCompra;

	public List<OrdenCompra> getOrdenesCompra() {
		return ordenesCompra;
	}

	public void setOrdenesCompra(List<OrdenCompra> ordenesCompra) {
		this.ordenesCompra = ordenesCompra;
	}

	private List listaLineasSolSumOrdenCompra;

	public List getListaLineasSolSumOrdenCompra() {
		return listaLineasSolSumOrdenCompra;
	}

	public void setListaLineasSolSumOrdenCompra(List listaLineasSolSumOrdenCompra) {
		this.listaLineasSolSumOrdenCompra = listaLineasSolSumOrdenCompra;
	}

	private List<LineaMovimientoMercaderia> listaLineasMovMerc;

	public List<LineaMovimientoMercaderia> getListaLineasMovMerc() {
		return listaLineasMovMerc;
	}

	public void setListaLineasMovMerc(List<LineaMovimientoMercaderia> listaLineasMovMerc) {
		this.listaLineasMovMerc = listaLineasMovMerc;
	}

	private List<LiquidacionCompra> listaLiquidacionCompra;

	public List<LiquidacionCompra> getListaLiquidacionCompra() {
		return listaLiquidacionCompra;
	}

	public void setListaLiquidacionCompra(List<LiquidacionCompra> listaLiquidacionCompra) {
		this.listaLiquidacionCompra = listaLiquidacionCompra;
	}

	public PaginatedTable getTablaLiquidacionCompra() {
		return tablaLiquidacionCompra;
	}

	public void setTablaLiquidacionCompra(PaginatedTable tablaLiquidacionCompra) {
		this.tablaLiquidacionCompra = tablaLiquidacionCompra;
	}

	private List<Factura> listaFacturasLiquidacionCompra;

	public List<Factura> getListaFacturasLiquidacionCompra() {
		return listaFacturasLiquidacionCompra;
	}

	public void setListaFacturasLiquidacionCompra(List<Factura> listaFacturasLiquidacionCompra) {
		this.listaFacturasLiquidacionCompra = listaFacturasLiquidacionCompra;
	}

	private List<LineaLiquidacionCompra> listaLineasLiquidacionCompra;

	public List<LineaLiquidacionCompra> getListaLineasLiquidacionCompra() {
		return listaLineasLiquidacionCompra;
	}

	public void setListaLineasLiquidacionCompra(List<LineaLiquidacionCompra> listaLineasLiquidacionCompra) {
		this.listaLineasLiquidacionCompra = listaLineasLiquidacionCompra;
	}

	private List<EstadoSolicitudSuministro> listaEstadoSolSumFinalizable;
	private List<EstadoSolicitudSuministro> listaEstadoSolSumFinalizacion;
	private List<EstadoSolicitudSuministro> listaEstadosFinalizacion = new ArrayList<EstadoSolicitudSuministro>();

	public List<EstadoSolicitudSuministro> getListaEstadoSolSumFinalizable() {
		return listaEstadoSolSumFinalizable;
	}

	public void setListaEstadoSolSumFinalizable(List<EstadoSolicitudSuministro> listaEstadoSolSumFinalizable) {
		this.listaEstadoSolSumFinalizable = listaEstadoSolSumFinalizable;
	}

	public List<EstadoSolicitudSuministro> getListaEstadoSolSumFinalizacion() {
		return listaEstadoSolSumFinalizacion;
	}

	public void setListaEstadoSolSumFinalizacion(List<EstadoSolicitudSuministro> listaEstadoSolSumFinalizacion) {
		this.listaEstadoSolSumFinalizacion = listaEstadoSolSumFinalizacion;

	}

	public List<EstadoSolicitudSuministro> getListaEstadosFinalizacion() {
		return listaEstadosFinalizacion;
	}

	public void setListaEstadosFinalizacion(List<EstadoSolicitudSuministro> listaEstadosFinalizacion) {
		this.listaEstadosFinalizacion = listaEstadosFinalizacion;
	}

	private Map<String, Secretaria> mapaSecretariaSolSum = null;

	public Map<String, Secretaria> getMapaSecretariaSolSum() {
		if(mapaSecretariaSolSum == null) {
			try {
				getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
				mapaSecretariaSolSum = new TreeMap<String, Secretaria>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroSecretaria locFiltro = new FiltroSecretaria();

				List<Secretaria> locListaSecretarias = getComunicationBean().getRemoteSystemMunicipalidad().findListaSecretarias(locFiltro).getListaResultados();
				for(Secretaria cadaSecretaria : locListaSecretarias) {
					mapaSecretariaSolSum.put(cadaSecretaria.getNombre(), cadaSecretaria);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaSecretariaSolSum;
	}

	public Map<String, Secretaria> mapaSecretariasArea;

	public Map<String, Secretaria> getMapaSecretariasArea() {
		if(this.mapaAreasSolSum == null) {
			this.mapaAreasSolSum = this.armarMapaAreasSolSum();
		}
		if(mapaAreasSolSum != null && mapaSecretariasArea == null) {
			try {
				getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
				mapaSecretariasArea = new TreeMap<String, Secretaria>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});

				List<Secretaria> locListaSecretarias = new ArrayList();

				for(String cadaLlave : mapaAreasSolSum.keySet()) {
					Area locArea = mapaAreasSolSum.get(cadaLlave);
					if(locArea.getSecretaria() != null)
						mapaSecretariasArea.put(locArea.getSecretaria().getNombre(), locArea.getSecretaria());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaSecretariasArea;
	}

	public Map<String, Area> mapaAreasSolSum;

	public Map<String, Area> getMapaAreasSolSum() {
		return getMapaAreasSolSum(null);
	}

	public Map<String, Area> getMapaAreasSolSum(Secretaria pSecretaria) {
		if(this.mapaAreasSolSum == null) {
			this.mapaAreasSolSum = this.armarMapaAreasSolSum();
		}
		if(pSecretaria != null) {
			Map<String, Area> locMapa = new HashMap<String, Area>();
			for(String cadaLlave : mapaAreasSolSum.keySet()) {
				Area locArea = mapaAreasSolSum.get(cadaLlave);
				if(locArea.getSecretaria() != null && locArea.getSecretaria().equals(pSecretaria))
					locMapa.put(cadaLlave, locArea);
			}
			return locMapa;
		}
		return mapaAreasSolSum;
	}

	public void setMapaAreasSolSum(Map<String, Area> mapaAreasSolSum) {
		this.mapaAreasSolSum = mapaAreasSolSum;
	}

	private Map<String, Area> armarMapaAreasSolSum() {
		Map<String, Area> locMapa = new HashMap<String, Area>();
		try {
			locMapa = getComunicationBean().armarMapaAreasUsuario();

			if(SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), SolicitudSuministro.serialVersionUID, Permiso.Accion.AUDITH)) {
				locMapa = getComunicationBean().armarMapaAreasTodas();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return locMapa;
	}

	public Map<String, Area> mapaAreasDeposito;

	public Map<String, Area> getMapaAreasDeposito() {
		return getMapaAreasDeposito(null);
	}

	public Map<String, Area> getMapaAreasDeposito(Secretaria pSecretaria) {
		if(this.mapaAreasDeposito == null) {
			this.mapaAreasDeposito = this.armarMapaAreasDeposito();
		}
		if(pSecretaria != null) {
			Map<String, Area> locMapa = new HashMap<String, Area>();
			for(String cadaLlave : mapaAreasDeposito.keySet()) {
				Area locArea = mapaAreasDeposito.get(cadaLlave);
				if(locArea.getSecretaria() != null && locArea.getSecretaria().equals(pSecretaria))
					locMapa.put(cadaLlave, locArea);
			}
			return locMapa;
		}
		return mapaAreasDeposito;
	}

	public void setMapaAreasDeposito(Map<String, Area> mapaAreasDeposito) {
		this.mapaAreasDeposito = mapaAreasDeposito;
	}

	private Map<String, Area> armarMapaAreasDeposito() {
		Map<String, Area> locMapa = new HashMap<String, Area>();
		try {
			locMapa = getComunicationBean().armarMapaAreasUsuario();

			if(SecurityMgr.getInstance().getPermiso(this.getSessionBean1().getLlave(), Deposito.serialVersionUID, Permiso.Accion.AUDITH)) {
				locMapa = getComunicationBean().armarMapaAreasTodas();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return locMapa;
	}

	public Map<String, Deposito> getMapaDepositosOrigen() {
		Map<String, Deposito> mapaDepositosOrigen = new HashMap<String, Deposito>();
		this.getSessionBean1().getUsuario().getPermisos();
		FiltroDeposito locFiltro = new FiltroDeposito();
		try {
			this.getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
			locFiltro.setListaAreas(SecurityMgr.getInstance().getListaAreasUsuario(this.getSessionBean1().getLlave()));
			List<Deposito> locListaDepositos = this.getRemoteSystemStock().findListaDeposito(locFiltro).getListaResultados();
			for(Deposito cadaDeposito : locListaDepositos) {
				mapaDepositosOrigen.put(cadaDeposito.toString(), cadaDeposito);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mapaDepositosOrigen;
	}

	public Map<String, Deposito> getMapaDepositoDestino() {
		Map<String, Deposito> mapaDepositoDestino = new HashMap<String, Deposito>();
		FiltroDeposito locFiltro = new FiltroDeposito();
		List<Deposito> locListaDepositos;
		try {
			locListaDepositos = this.getRemoteSystemStock().findListaDeposito(locFiltro).getListaResultados();

			for(Deposito cadaDeposito : locListaDepositos) {
				mapaDepositoDestino.put(cadaDeposito.toString(), cadaDeposito);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mapaDepositoDestino;
	}

	// private MovimientoDeMercaderia movimientoMercaderia = new MovimientoDeMercaderia();
	//
	// public MovimientoDeMercaderia getMovimientoMercaderia() {
	// return movimientoMercaderia;
	// }
	//
	// public void setMovimientoMercaderia(MovimientoDeMercaderia movimientoMercaderia) {
	// this.movimientoMercaderia = movimientoMercaderia;
	// }
	//

	private Map<String, Area> mapaAreas;

	public Map<String, Area> getMapaAreas() {
		if(mapaAreas == null) {
			this.mapaAreas = this.getComunicationBean().armarMapaAreasTodas();
		}
		return mapaAreas;
	}

	public void setMapaAreas(Map<String, Area> mapaAreas) {
		this.mapaAreas = mapaAreas;
	}

	private Map<String, EstadoSolicitudSuministro> mapaEstadosFirmables;

	public Map<String, EstadoSolicitudSuministro> getMapaEstadosFirmables() {
		if(this.mapaEstadosFirmables == null) {
			this.mapaEstadosFirmables = this.armarMapaEstadosFirmables();
		}
		return mapaEstadosFirmables;
	}

	public void setMapaEstadosFirmables(Map<String, EstadoSolicitudSuministro> mapaEstadosFirmables) {
		this.mapaEstadosFirmables = mapaEstadosFirmables;
	}

	private Map<String, EstadoSolicitudSuministro> armarMapaEstadosFirmables() {
		Map<String, EstadoSolicitudSuministro> locMapaResultado = new HashMap<String, EstadoSolicitudSuministro>();
		List<EstadoSolicitudSuministro> locListaResultado = new ArrayList<EstadoSolicitudSuministro>();
		try {
			this.getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
			locListaResultado = this.getRemoteSystemAdministracionSolicitudSuministro().findListaEstadoSolSum(new FiltroEstadoSolicitudSuministro()).getListaResultados();
		} catch(Exception e) {
			e.printStackTrace();
		}

		for(EstadoSolicitudSuministro cadaEstado : locListaResultado) {
			locMapaResultado.put(cadaEstado.getNombre(), cadaEstado);
		}

		return locMapaResultado;
	}

	private Map<String, EstadoSolicitudSuministro> mapaEstadosFinalizables;

	public Map<String, EstadoSolicitudSuministro> getMapaEstadosFinalizables() {
		if(this.mapaEstadosFinalizables == null) {
			this.mapaEstadosFinalizables = this.armarMapaEstadosFirmables();
		}
		return mapaEstadosFinalizables;
	}

	public void setMapaEstadosFinalizables(Map<String, EstadoSolicitudSuministro> mapaEstadosFinalizables) {
		this.mapaEstadosFinalizables = mapaEstadosFinalizables;
	}

	private Map<String, EstadoSolicitudSuministro> mapaEstadosFinalizacion;

	public Map<String, EstadoSolicitudSuministro> getMapaEstadosFinalizacion() {
		if(this.mapaEstadosFinalizacion == null) {
			this.mapaEstadosFinalizacion = this.armarMapaEstadosFinalizacion();
		}
		return mapaEstadosFinalizacion;
	}

	public void setMapaEstadosFinalizacion(Map<String, EstadoSolicitudSuministro> mapaEstadosFinalizacion) {
		this.mapaEstadosFinalizacion = mapaEstadosFinalizacion;
	}

	private Map<String, EstadoSolicitudSuministro> armarMapaEstadosFinalizacion() {
		Map<String, EstadoSolicitudSuministro> locMapaResultado = new HashMap<String, EstadoSolicitudSuministro>();
		List<EstadoSolicitudSuministro> locListaResultado = new ArrayList<EstadoSolicitudSuministro>();
		try {
			this.getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
			locListaResultado = this.getRemoteSystemAdministracionSolicitudSuministro().findListaEstadoSolSumFinal();
		} catch(Exception e) {
			e.printStackTrace();
		}

		for(EstadoSolicitudSuministro cadaEstado : locListaResultado) {
			locMapaResultado.put(cadaEstado.getNombre(), cadaEstado);
		}

		return locMapaResultado;
	}

	private boolean seleccionMultipleLineaContratacion = false;

	public boolean isSeleccionMultipleLineaContratacion() {
		return seleccionMultipleLineaContratacion;
	}

	public void setSeleccionMultipleLineaContratacion(boolean seleccionMultipleLineaContratacion) {
		this.seleccionMultipleLineaContratacion = seleccionMultipleLineaContratacion;
	}

	private Set seleccionadosSeleccionMultipleLineaContratacion = new HashSet();

	public Set getSeleccionadosSeleccionMultipleLineaContratacion() {
		return seleccionadosSeleccionMultipleLineaContratacion;
	}

	public void setSeleccionadosSeleccionMultipleLineaContratacion(Set seleccionadosSeleccionMultipleLineaContratacion) {
		this.seleccionadosSeleccionMultipleLineaContratacion = seleccionadosSeleccionMultipleLineaContratacion;
	}
	
	private List listaAtributosDinamicosProveedor = null;

	public List getListaAtributosDinamicosProveedor() {
		return listaAtributosDinamicosProveedor;
	}

	public void setListaAtributosDinamicosProveedor(
			List listaAtributosDinamicosProveedor) {
		this.listaAtributosDinamicosProveedor = listaAtributosDinamicosProveedor;
	}
}
