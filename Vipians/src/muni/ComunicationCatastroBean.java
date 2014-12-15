/*
 * ComunicationCatastroBean.java
 *
 * Created on 17 de octubre de 2006, 8:54
 * Copyright mariano
 */

package muni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroCalle;
import com.trascender.catastro.recurso.filtros.FiltroCategoria;
import com.trascender.catastro.recurso.filtros.FiltroCuadra;
import com.trascender.catastro.recurso.filtros.FiltroDeclaracionJurada;
import com.trascender.catastro.recurso.filtros.FiltroManzana;
import com.trascender.catastro.recurso.filtros.FiltroParcela;
import com.trascender.catastro.recurso.filtros.FiltroPlanoConstruccion;
import com.trascender.catastro.recurso.filtros.FiltroPlanoMensura;
import com.trascender.catastro.recurso.filtros.FiltroSubParcela;
import com.trascender.catastro.recurso.filtros.FiltroTipoCalle;
import com.trascender.catastro.recurso.filtros.FiltroTipoConstruccion;
import com.trascender.catastro.recurso.filtros.FiltroTituloPropiedad;
import com.trascender.catastro.recurso.filtros.FiltroValorBasicoMejora;
import com.trascender.catastro.recurso.filtros.FiltroZona;
import com.trascender.catastro.recurso.filtros.FiltroZonificacion;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.catastro.system.interfaces.SystemAdministracionDDJJ;
import com.trascender.catastro.system.interfaces.SystemAdministracionZona;
import com.trascender.catastro.system.interfaces.SystemAdministracionZonificacion;
import com.trascender.catastro.system.interfaces.SystemCodigosCatastrales;
import com.trascender.catastro.system.interfaces.SystemInformacionGeografica;
import com.trascender.catastro.system.interfaces.SystemInformacionParcelaria;
import com.trascender.catastro.system.interfaces.SystemRegistroPropiedad;
import com.trascender.catastro.system.interfaces.SystemReportesCatastro;
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
public class ComunicationCatastroBean extends AbstractSessionBean {
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

	// </editor-fold>
	Properties props = null;
	Context ctx = null;

	/**
	 * <p>
	 * Construir una instancia de bean de datos de la sesión.
	 * </p>
	 */
	public ComunicationCatastroBean() {
		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "localhost:1099");
		try {
			ctx = new InitialContext(props);
			this.remoteSystemInformacionGeografica = (SystemInformacionGeografica) ctx.lookup(SystemInformacionGeografica.JNDI_NAME);
			this.remoteSystemCodigosCatastrales = (SystemCodigosCatastrales) ctx.lookup(SystemCodigosCatastrales.JNDI_NAME);
			this.remoteSystemRegistroPropiedad = (SystemRegistroPropiedad) ctx.lookup(SystemRegistroPropiedad.JNDI_NAME);
			this.remoteSystemInformacionParcelaria = (SystemInformacionParcelaria) ctx.lookup(SystemInformacionParcelaria.JNDI_NAME);
			this.remoteSystemAdministracionZonificacion = (SystemAdministracionZonificacion) ctx.lookup(SystemAdministracionZonificacion.JNDI_NAME);
			this.remoteSystemAdministracionZona = (SystemAdministracionZona) ctx.lookup(SystemAdministracionZona.JNDI_NAME);
			this.remoteSystemAdministracionDDJJ = (SystemAdministracionDDJJ) ctx.lookup(SystemAdministracionDDJJ.JNDI_NAME);
			this.remoteSystemReportesCatastro = (SystemReportesCatastro) ctx.lookup(SystemReportesCatastro.JNDI_NAME);

			System.out.println("ComunicationCatastroBean");
			FiltroCalle locFiltroCalle = new FiltroCalle();
			locFiltroCalle.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaCalle = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Calle.serialVersionUID), "#{catastro$ABMCalle$AdminCalle}", locFiltroCalle);

			FiltroCategoria locFiltroCoeficientoDepreciacion = new FiltroCategoria();
			locFiltroCoeficientoDepreciacion.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaCoeficienteDepreciacion = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Categoria.serialVersionUID),
					"#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion}", locFiltroCoeficientoDepreciacion);

			FiltroCategoria locFiltroCategoria = new FiltroCategoria();
			locFiltroCategoria.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaCategoria = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Categoria.serialVersionUID), "#{catastro$ABMCategoria$AdminCategoria}",
					locFiltroCategoria);

			FiltroCuadra locFiltroCuadra = new FiltroCuadra();
			locFiltroCuadra.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaCuadra = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Cuadra.serialVersionUID), "#{catastro$ABMCuadra$AdminCuadra}", locFiltroCuadra);

			FiltroDeclaracionJurada locFiltroDeclaracionJurada = new FiltroDeclaracionJurada();
			locFiltroDeclaracionJurada.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDeclaracionJurada = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DeclaracionJurada.serialVersionUID),
					"#{catastro$ABMDeclaracionJurada$AdminDeclaracionJurada}", locFiltroDeclaracionJurada);

			FiltroManzana locFiltroManzana = new FiltroManzana();
			locFiltroManzana.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaManzana = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Manzana.serialVersionUID), "#{catastro$ABMManzana$AdminManzana}", locFiltroManzana);

			FiltroParcela locFiltroParcela = new FiltroParcela();
			locFiltroParcela.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaParcela = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Parcela.serialVersionUID), "#{catastro$ABMParcela$AdminParcela}", locFiltroParcela);

			FiltroPlanoConstruccion locFiltroPlanoConstruccion = new FiltroPlanoConstruccion();
			locFiltroPlanoConstruccion.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaPlanoConstruccion = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(PlanoConstruccion.serialVersionUID),
					"#{catastro$ABMPlanoConstruccion$AdminPlanoConstruccion}", locFiltroPlanoConstruccion);

			FiltroPlanoMensura locFiltroPlanoMensura = new FiltroPlanoMensura();
			locFiltroPlanoMensura.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaPlanoMensura = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(PlanoMensura.serialVersionUID), "#{catastro$ABMPlanoMensura$AdminPlanoMensura}",
					locFiltroPlanoMensura);

			// FiltroServicio locFiltroServicio = new FiltroServicio();
			// locFiltroServicio.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			// this.tablaServicio = new PaginatedTable(
			// this.getSessionBean1().getAtributosConsultables(Servicio.serialVersionUID),
			// "#{catastro$ABMServicio$AdminServicio}",
			// locFiltroServicio);

			FiltroSubParcela locFiltroSubParcela = new FiltroSubParcela();
			locFiltroSubParcela.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaSubparcela = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(SubParcela.serialVersionUID), "#{catastro$ABMSubparcela$AdminSubparcela}",
					locFiltroSubParcela);

			FiltroTipoCalle locFiltroTipoCalle = new FiltroTipoCalle();
			locFiltroTipoCalle.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoCalle = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoCalle.serialVersionUID), "#{catastro$ABMTipoCalle$AdminTipoCalle}",
					locFiltroTipoCalle);

			FiltroTipoConstruccion locFiltroTipoConstruccion = new FiltroTipoConstruccion();
			locFiltroTipoConstruccion.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTipoConstruccion = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TipoConstruccion.serialVersionUID),
					"#{catastro$ABMTipoConstruccion$AdminTipoConstruccion}", locFiltroTipoConstruccion);

			FiltroTituloPropiedad locFiltroTituloPropiedad = new FiltroTituloPropiedad();
			locFiltroTituloPropiedad.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTituloPropiedad = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TituloPropiedadParcelario.serialVersionUID),
					"#{catastro$ABMTituloPropiedad$AdminTituloPropiedad}", locFiltroTituloPropiedad);

			FiltroValorBasicoMejora locFiltroValorBasicoMejora = new FiltroValorBasicoMejora();
			locFiltroValorBasicoMejora.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaValorBasicoMejora = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ValorBasicoMejora.serialVersionUID),
					"#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora}", locFiltroValorBasicoMejora);

			FiltroZona locFiltroZona = new FiltroZona();
			locFiltroZona.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaZona = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Zona.serialVersionUID), "#{catastro$ABMZona$AdminZona}", locFiltroZona);

			FiltroZonificacion locFiltroZonificacion = new FiltroZonificacion();
			locFiltroZonificacion.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaZonificacion = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Zonificacion.serialVersionUID), "#{catastro$ABMZonificacion$AdminZonificacion}",
					locFiltroZonificacion);

		} catch(Exception ex) {
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

		// <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados automáticamente
		// *Nota* - esta lógica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("ComunicationCatastroBean Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicación que debe finalizar
		// *después* de que se inicien los componentes administrados
		// TODO - Agregar código de inicio propio aquí

		tablePhaseListenerCuadras.keepSelected(true);
		// Mines agregaste la siguiente linea para los selects de la zonificacion
		tablePhaseListenerParcelas.keepSelected(true);
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
	 * Puede personalizar este método para volver a adquirir las referencias a datos o recursos de la sesión que no pieron serializarse con la propia sesión.
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
	 * Definición de la interfaz remota SystemInformacionGeografica, para invocar la lógica de negocio.
	 */
	// <editor-fold defaultstate="collapsed" desc="SystemInformacionGeografica">
	// @EJB
	private SystemInformacionGeografica remoteSystemInformacionGeografica = null;

	public SystemInformacionGeografica getRemoteSystemInformacionGeografica() {
		/*
		 * try { if (this.remoteSystemInformacionGeografica == null) { Context ctx = new InitialContext(getProps()); Object obj =
		 * ctx.lookup(SystemInformacionGeograficaHome.JNDI_NAME); SystemInformacionGeograficaHome locInformacionGeograficaHome =
		 * (SystemInformacionGeograficaHome) PortableRemoteObject.narrow(obj, SystemInformacionGeograficaHome.class); this.remoteSystemInformacionGeografica =
		 * locInformacionGeograficaHome.create(); } } catch (Exception e) { e.printStackTrace(); }
		 */

		return remoteSystemInformacionGeografica;
	}

	public void setRemoteSystemInformacionGeografica(SystemInformacionGeografica remoteSystemInformacionGeografica) {
		this.remoteSystemInformacionGeografica = remoteSystemInformacionGeografica;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemCodigosCatastrales">
	// @EJB
	private SystemCodigosCatastrales remoteSystemCodigosCatastrales = null;

	public SystemCodigosCatastrales getRemoteSystemCodigosCatastrales() {
		/*
		 * try { if (this.remoteSystemCodigosCatastrales == null) { Context ctx = new InitialContext(getProps()); Object obj =
		 * ctx.lookup(SystemCodigosCatastralesHome.JNDI_NAME); SystemCodigosCatastralesHome locCodigosCatastralesHome = (SystemCodigosCatastralesHome)
		 * PortableRemoteObject.narrow(obj, SystemCodigosCatastralesHome.class); this.remoteSystemCodigosCatastrales = locCodigosCatastralesHome.create(); } }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

		return remoteSystemCodigosCatastrales;
	}

	public void setRemoteSystemCodigosCatastrales(SystemCodigosCatastrales remoteSystemCodigosCatastrales) {
		this.remoteSystemCodigosCatastrales = remoteSystemCodigosCatastrales;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemRegistroPropiedad">
	// @EJB
	private SystemRegistroPropiedad remoteSystemRegistroPropiedad = null;

	public SystemRegistroPropiedad getRemoteSystemRegistroPropiedad() {

		return remoteSystemRegistroPropiedad;
	}

	public void setRemoteSystemRegistroPropiedad(SystemRegistroPropiedad remoteSystemRegistroPropiedad) {
		this.remoteSystemRegistroPropiedad = remoteSystemRegistroPropiedad;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemInformacionParcelaria">
	// @EJB
	private SystemInformacionParcelaria remoteSystemInformacionParcelaria = null;

	public SystemInformacionParcelaria getRemoteSystemInformacionParcelaria() {
		/*
		 * try { if (this.remoteSystemInformacionParcelaria == null) { Context ctx = new InitialContext(getProps()); Object obj =
		 * ctx.lookup(SystemInformacionParcelariaHome.JNDI_NAME); SystemInformacionParcelariaHome locInformacionParcelariaHome =
		 * (SystemInformacionParcelariaHome) PortableRemoteObject.narrow(obj, SystemInformacionParcelariaHome.class); this.remoteSystemInformacionParcelaria =
		 * locInformacionParcelariaHome.create(); } } catch (Exception e) { e.printStackTrace(); }
		 */
		return remoteSystemInformacionParcelaria;
	}

	public void setRemoteSystemInformacionParcelaria(SystemInformacionParcelaria remoteSystemInformacionParcelaria) {
		this.remoteSystemInformacionParcelaria = remoteSystemInformacionParcelaria;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemAdministraci�n Zonificacion">
	// @EJB
	private SystemAdministracionZonificacion remoteSystemAdministracionZonificacion = null;

	public SystemAdministracionZonificacion getRemoteSystemAdministracionZonificacion() {
		/*
		 * try { if (this.remoteSystemAdministracionZonificacion == null) { Context ctx = new InitialContext(getProps()); Object obj =
		 * ctx.lookup(SystemAdministracionZonificacionHome.JNDI_NAME); SystemAdministracionZonificacionHome locAdministracionZonificacionHome =
		 * (SystemAdministracionZonificacionHome) PortableRemoteObject.narrow(obj, SystemAdministracionZonificacionHome.class);
		 * this.remoteSystemAdministracionZonificacion = locAdministracionZonificacionHome.create(); } } catch (Exception e) { e.printStackTrace(); }
		 */
		return remoteSystemAdministracionZonificacion;
	}

	public void setRemoteSystemAdministracionZonificacion(SystemAdministracionZonificacion remoteSystemAdministracionZonificacion) {
		this.remoteSystemAdministracionZonificacion = remoteSystemAdministracionZonificacion;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemAdministraci�n Zona">
	// @EJB
	private SystemAdministracionZona remoteSystemAdministracionZona = null;

	public SystemAdministracionZona getRemoteSystemAdministracionZona() {
		return this.remoteSystemAdministracionZona;
	}

	// public SystemAdministracionZona getRemoteSystemAdministracionZona() {
	/*
	 * try { //if (this.remoteSystemAdministracionZona==null){ Context ctx = new InitialContext(getProps()); Object obj =
	 * ctx.lookup(SystemAdministracionZonaHome.JNDI_NAME); SystemAdministracionZonaHome locAdministracionZonaHome = (SystemAdministracionZonaHome)
	 * PortableRemoteObject.narrow(obj, SystemAdministracionZonaHome.class); this.remoteSystemAdministracionZona = locAdministracionZonaHome.create(); //} }
	 * catch (Exception e) { e.printStackTrace(); }
	 */
	// return remoteSystemAdministracionZona;
	// }
	public void setRemoteSystemAdministracionZona(SystemAdministracionZona remoteSystemAdministracionZona) {
		this.remoteSystemAdministracionZona = remoteSystemAdministracionZona;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemAdministraci�n DDJJ">
	// @EJB
	private SystemAdministracionDDJJ remoteSystemAdministracionDDJJ = null;

	public SystemAdministracionDDJJ getRemoteSystemAdministracionDDJJ() {
		/*
		 * try { if (this.remoteSystemAdministracionDDJJ == null) { Context ctx = new InitialContext(getProps()); Object obj =
		 * ctx.lookup(SystemAdministracionDDJJHome.JNDI_NAME); SystemAdministracionDDJJHome locAdministracionDDJJHome = (SystemAdministracionDDJJHome)
		 * PortableRemoteObject.narrow(obj, SystemAdministracionDDJJHome.class); this.remoteSystemAdministracionDDJJ = locAdministracionDDJJHome.create(); } }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

		return remoteSystemAdministracionDDJJ;
	}

	public void setRemoteSystemAdministracionDDJJ(SystemAdministracionDDJJ remoteSystemAdministracionDDJJ) {
		this.remoteSystemAdministracionDDJJ = remoteSystemAdministracionDDJJ;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="SystemCodigosCatastrales">
	// @EJB
	private SystemReportesCatastro remoteSystemReportesCatastro = null;

	public SystemReportesCatastro getRemoteSystemReportesCatastro() {
		/*
		 * try { if (this.remoteSystemCodigosCatastrales == null) { Context ctx = new InitialContext(getProps()); Object obj =
		 * ctx.lookup(SystemCodigosCatastralesHome.JNDI_NAME); SystemCodigosCatastralesHome locCodigosCatastralesHome = (SystemCodigosCatastralesHome)
		 * PortableRemoteObject.narrow(obj, SystemCodigosCatastralesHome.class); this.remoteSystemCodigosCatastrales = locCodigosCatastralesHome.create(); } }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

		return remoteSystemReportesCatastro;
	}

	public void setRemoteSystemReportesCatastro(SystemReportesCatastro remoteSystemReportesCatastro) {
		this.remoteSystemReportesCatastro = remoteSystemReportesCatastro;
	}

	// </editor-fold>
	/**
	 * Código extra de comunicación RMI
	 */
	// <editor-fold defaultstate="collapsed" desc="ArrayList Recursos Catastrales">
	// Lita de Zonas

	private List listaZonasParcelas = null;

	public List getListaZonasParcelas() {
		return listaZonasParcelas;
	}

	public void setListaZonasParcelas(List listaZonasParcelas) {
		this.listaZonasParcelas = listaZonasParcelas;
	}

	private List listaZonasCalles = null;

	public List getListaZonasCalles() {
		return listaZonasCalles;
	}

	public void setListaZonasCalles(List listaZonasCalles) {
		this.listaZonasCalles = listaZonasCalles;
	}

	private List ListaZonasCuadras = null;

	public List getListaZonasCuadras() {
		return ListaZonasCuadras;
	}

	public void setListaZonasCuadras(List listaZonasCuadras) {
		ListaZonasCuadras = listaZonasCuadras;
	}

	private List ListaZonasManzana = null;

	public List getListaZonasManzana() {
		return ListaZonasManzana;
	}

	public void setListaZonasManzana(List listaZonasManzana) {
		ListaZonasManzana = listaZonasManzana;
	}

	private ArrayList listaZonas = null;

	public ArrayList getListaZonas() {
		return listaZonas;
	}

	public void setListaZonas(ArrayList pListaZonas) {
		this.listaZonas = pListaZonas;
	}

	private List listaAsociacionParcela = null;

	/**
	 * Lista Asociaciones con AsociacionParcelaBridge (Asociacion entre zona y calle, parcela, cuadra y manzana)
	 *
	 * @return
	 */
	public List getListaAsociacionParcela() {
		return listaAsociacionParcela;
	}

	/**
	 * Lista Asociaciones con AsociacionParcelaBridge (Asociacion entre zona y calle, parcela, cuadra y manzana)
	 *
	 * @return
	 */
	public void setListaAsociacionParcela(List listaAsociacionParcela) {
		this.listaAsociacionParcela = listaAsociacionParcela;
	}

	public List listaFiltradaAsocParcela = null;

	public List getListaFiltradaAsocParcela() {
		return listaFiltradaAsocParcela;
	}

	public void setListaFiltradaAsocParcela(List listaFiltradaAsocParcela) {
		this.listaFiltradaAsocParcela = listaFiltradaAsocParcela;
	}

	// Lista de Tipos de Calles
	private ArrayList listaTipoCalles = null;

	public ArrayList getListaTipoCalles() {
		return this.listaTipoCalles;
	}

	public void setListaTipoCalles(ArrayList pListaTipoCalles) {
		this.listaTipoCalles = pListaTipoCalles;
	} // Lista de Tipos de Construcción

	private ArrayList listaTipoConstruccion = null;

	public ArrayList getListaTipoConstruccion() {
		return this.listaTipoConstruccion;
	}

	public void setListaTipoConstruccion(ArrayList pTipoConstruccion) {
		this.listaTipoConstruccion = pTipoConstruccion;
	}

	private ArrayList listaServicios = null;

	public ArrayList getListaServicios() {
		return this.listaServicios;
	}

	public void setListaServicios(ArrayList pListaServicios) {
		this.listaServicios = pListaServicios;
	}

	private ArrayList listaTipoTransaccionCatastral = null;

	public ArrayList getListaTipoTransaccionCatastral() {
		return this.listaTipoTransaccionCatastral;
	}

	public void setListaTipoTransaccionCatastral(ArrayList pListaTipoTransaccionCatastral) {
		this.listaTipoTransaccionCatastral = pListaTipoTransaccionCatastral;
	}

	private ArrayList listaZonificacion = null;

	public ArrayList getListaZonificacion() {
		return this.listaZonificacion;
	}

	public void setListaZonificacion(ArrayList pListaZonificacion) {
		this.listaZonificacion = pListaZonificacion;
	}

	private ArrayList listaCategorias = null;

	public ArrayList getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(ArrayList listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	private ArrayList listaManzanas = null;

	public ArrayList getListaManzanas() {
		return listaManzanas;
	}

	public void setListaManzanas(ArrayList pListaManzanas) {
		this.listaManzanas = pListaManzanas;
	}

	private List listaCategoriaCoeficientesDepreciacion = null;

	public List getListaCategoriaCoeficientesDepreciacion() {
		return this.listaCategoriaCoeficientesDepreciacion;
	}

	public void setListaCategoriaCoeficientesDepreciacion(List listaCategoriaCoeficientesDepreciacion) {
		this.listaCategoriaCoeficientesDepreciacion = listaCategoriaCoeficientesDepreciacion;
	}

	private ArrayList listaValoresBasicosMejora = null;

	public ArrayList getListaValoresBasicosMejora() {
		return listaValoresBasicosMejora;
	}

	public void setListaValoresBasicosMejora(ArrayList listaValoresBasicosMejora) {
		this.listaValoresBasicosMejora = listaValoresBasicosMejora;
	}

	private ArrayList listaSubparcelas = null;

	public ArrayList getListaSubparcelas() {
		return listaSubparcelas;
	}

	public void setListaSubparcelas(ArrayList listaSubparcelas) {
		this.listaSubparcelas = listaSubparcelas;
	}

	private ArrayList listaParcelas = null;

	public ArrayList getListaParcelas() {
		return this.listaParcelas;
	}

	public void setListaParcelas(ArrayList pListaParcelas) {
		this.listaParcelas = pListaParcelas;
	}

	private ArrayList listaVolanteCatastrales = null;

	public ArrayList getListaVolanteCatastrales() {
		return this.listaVolanteCatastrales;
	}

	public void setListaVolanteCatastrales(ArrayList pListaVolanteCatastrales) {
		this.listaVolanteCatastrales = pListaVolanteCatastrales;
	}

	private ArrayList listaPlanoMensura = null;

	public ArrayList getListaPlanoMensura() {
		return this.listaPlanoMensura;
	}

	public void setListaPlanoMensura(ArrayList pListaPlanoMensura) {
		this.listaPlanoMensura = pListaPlanoMensura;
	}

	private List listaPlanoConstrucciones = null;

	public List getListaPlanoConstrucciones() {
		return listaPlanoConstrucciones;
	}

	public void setListaPlanoConstrucciones(List listaPlanoConstrucciones) {
		this.listaPlanoConstrucciones = listaPlanoConstrucciones;
	}

	private List listaCargosPlanoConstruccion = null;

	public List getListaCargosPlanoConstruccion() {
		return listaCargosPlanoConstruccion;
	}

	public void setListaCargosPlanoConstruccion(List listaCargosPlanoConstruccion) {
		this.listaCargosPlanoConstruccion = listaCargosPlanoConstruccion;
	}

	private ArrayList listaCalles = null;

	public ArrayList getListaCalles() {
		return listaCalles;
	}

	public void setListaCalles(ArrayList listaCalles) {
		this.listaCalles = listaCalles;
	}

	private ArrayList listaCallesComienzoDinamica = null;

	public ArrayList getListaCallesComienzoDinamica() {
		return listaCallesComienzoDinamica;
	}

	public void setListaCallesComienzoDinamica(ArrayList listaCalles) {
		this.listaCallesComienzoDinamica = listaCalles;
	}

	private ArrayList listaCallesFinDinamica = null;

	public ArrayList getListaCallesFinDinamica() {
		return listaCallesFinDinamica;
	}

	public void setListaCallesFinDinamica(ArrayList listaCalles) {
		this.listaCallesFinDinamica = listaCalles;
	}

	private ArrayList listaDeclaracionesJuradas = null;

	public ArrayList getListaDeclaracionesJuradas() {
		return listaDeclaracionesJuradas;
	}

	public void setListaDeclaracionesJuradas(ArrayList listaDeclaracionesJuradas) {
		this.listaDeclaracionesJuradas = listaDeclaracionesJuradas;
	}

	private ArrayList listaTitulosPropiedad = null;

	public ArrayList getListaTitulosPropiedad() {
		return listaTitulosPropiedad;
	}

	public void setListaTitulosPropiedad(ArrayList listaTitulosPropiedad) {
		this.listaTitulosPropiedad = listaTitulosPropiedad;
	}

	private List listaRegistrosPropietarios = null;

	public List getListaRegistrosPropietarios() {
		return listaRegistrosPropietarios;
	}

	public void setListaRegistrosPropietarios(List listaRegistrosPropietarios) {
		this.listaRegistrosPropietarios = listaRegistrosPropietarios;
	}

	private ArrayList listaCuadras = null;

	public ArrayList getListaCuadras() {
		return listaCuadras;
	}

	public void setListaCuadras(ArrayList listaCuadras) {
		this.listaCuadras = listaCuadras;
	}

	private ArrayList listaSubdivisiones = null;

	public ArrayList getListaSubdivisiones() {
		return listaSubdivisiones;
	}

	public void setListaSubdivisiones(ArrayList listaSubdivisiones) {
		this.listaSubdivisiones = listaSubdivisiones;
	}

	private ArrayList listaSubParcelas = null;

	public ArrayList getListaSubParcelas() {
		return listaSubParcelas;
	}

	public void setListaSubParcelas(ArrayList listaSubParcelas) {
		this.listaSubParcelas = listaSubParcelas;
	}

	private ArrayList listaCuadrasPorManzana = null;

	public ArrayList getListaCuadrasPorManzana() {
		return listaCuadrasPorManzana;
	}

	public void setListaCuadrasPorManzana(ArrayList listaCuadrasPorManzana) {
		this.listaCuadrasPorManzana = listaCuadrasPorManzana;
	}

	private ArrayList listaAtributosDinamicosManzana = null;

	public ArrayList getListaAtributosDinamicosManzana() {
		return listaAtributosDinamicosManzana;
	}

	public void setListaAtributosDinamicosManzana(ArrayList listaAtributosDinamicosManzana) {
		this.listaAtributosDinamicosManzana = listaAtributosDinamicosManzana;
	}

	private ArrayList listaAtributosDinamicosCuadra = null;

	public ArrayList getListaAtributosDinamicosCuadra() {
		return listaAtributosDinamicosCuadra;
	}

	public void setListaAtributosDinamicosCuadra(ArrayList listaAtributosDinamicosCuadra) {
		this.listaAtributosDinamicosCuadra = listaAtributosDinamicosCuadra;
	}

	private ArrayList listaAtributosDinamicosRegistroMejora = null;

	public ArrayList getListaAtributosDinamicosRegistroMejora() {
		return listaAtributosDinamicosRegistroMejora;
	}

	public void setListaAtributosDinamicosRegistroMejora(ArrayList listaAtributosDinamicosRegistroMejora) {
		this.listaAtributosDinamicosRegistroMejora = listaAtributosDinamicosRegistroMejora;
	}

	private ArrayList listaRegistrosMejora = null;

	public ArrayList getListaRegistrosMejora() {
		return listaRegistrosMejora;
	}

	public void setListaRegistrosMejora(ArrayList listaRegistrosMejora) {
		this.listaRegistrosMejora = listaRegistrosMejora;
	}

	private List listaParcelaPorCuadras = null;

	public List getListaParcelaPorCuadras() {
		return listaParcelaPorCuadras;
	}

	public void setListaParcelaPorCuadras(List listaParcelaPorCuadras) {
		this.listaParcelaPorCuadras = listaParcelaPorCuadras;
	}

	private ArrayList listaRegistrosMejoraParcela = null;

	public ArrayList getListaRegistrosMejoraParcela() {
		return listaRegistrosMejoraParcela;
	}

	public void setListaRegistrosMejoraParcela(ArrayList listaRegistrosMejoraParcela) {
		this.listaRegistrosMejoraParcela = listaRegistrosMejoraParcela;
	}

	private ArrayList listaVolatesCatastralesParcela = null;

	public ArrayList getListaVolatesCatastralesParcela() {
		return listaVolatesCatastralesParcela;
	}

	public void setListaVolatesCatastralesParcela(ArrayList listaVolatesCatastralesParcela) {
		this.listaVolatesCatastralesParcela = listaVolatesCatastralesParcela;
	}

	private List listaObligacionesParcela = null;

	public List getListaObligacionesParcela() {
		return listaObligacionesParcela;
	}

	public void setListaObligacionesParcela(List listaObligacionesParcela) {
		this.listaObligacionesParcela = listaObligacionesParcela;
	}

	private ArrayList listaCoeficientesDepreciacionPorCategoria = null;

	public ArrayList getListaCoeficientesDepreciacionPorCategoria() {
		return listaCoeficientesDepreciacionPorCategoria;
	}

	public void setListaCoeficientesDepreciacionPorCategoria(ArrayList listaCoeficientesDepreciacionPorCategoria) {
		this.listaCoeficientesDepreciacionPorCategoria = listaCoeficientesDepreciacionPorCategoria;
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="TableSelectPhaseListener Recursos Catastrales">
	/**
	 * Conserva el valor de la propiedad tablePhaseListenerCuadras.
	 */
	private TableSelectPhaseListener tablePhaseListenerCuadras = new TableSelectPhaseListener();

	/**
	 * Getter para propiedad tablePhaseListenerCuadras.
	 *
	 * @return Valor de la propiedad tablePhaseListenerCuadras.
	 */
	public TableSelectPhaseListener getTablePhaseListenerCuadras() {

		return this.tablePhaseListenerCuadras;
	}

	/**
	 * Setter para propiedad tablePhaseListenerCuadras.
	 *
	 * @param tablePhaseListenerCuadras
	 *            Nuevo valor de la propiedad tablePhaseListenerCuadras.
	 */
	public void setTablePhaseListenerCuadras(TableSelectPhaseListener tablePhaseListenerCuadras) {

		this.tablePhaseListenerCuadras = tablePhaseListenerCuadras;
	}

	// MINES : AGERGASTE lo siguiente para la zonificacion
	private TableSelectPhaseListener tablePhaseListenerParcelas = new TableSelectPhaseListener();

	/**
	 * Getter para propiedad tablePhaseListenerCuadras.
	 *
	 * @return Valor de la propiedad tablePhaseListenerCuadras.
	 */
	public TableSelectPhaseListener getTablePhaseListenerParcelas() {

		return this.tablePhaseListenerParcelas;
	}

	/**
	 * Setter para propiedad tablePhaseListenerCuadras.
	 *
	 * @param tablePhaseListenerCuadras
	 *            Nuevo valor de la propiedad tablePhaseListenerCuadras.
	 */
	public void setTablePhaseListenerParcelas(TableSelectPhaseListener tablePhaseListenerParcelas) {

		this.tablePhaseListenerParcelas = tablePhaseListenerParcelas;
	}

	private TableSelectPhaseListener tablePhaseListenerCalle = new TableSelectPhaseListener();

	/**
	 * Getter para propiedad tablePhaseListenerCuadras.
	 *
	 * @return Valor de la propiedad tablePhaseListenerCuadras.
	 */
	public TableSelectPhaseListener getTablePhaseListenerCalle() {

		return this.tablePhaseListenerCalle;
	}

	/**
	 * Setter para propiedad tablePhaseListenerCuadras.
	 *
	 * @param tablePhaseListenerCuadras
	 *            Nuevo valor de la propiedad tablePhaseListenerCuadras.
	 */
	public void setTablePhaseListenerCalle(TableSelectPhaseListener tablePhaseListenerCalle) {

		this.tablePhaseListenerCalle = tablePhaseListenerCalle;
	}

	private TableSelectPhaseListener tablePhaseListenerManzana = new TableSelectPhaseListener();

	/**
	 * Getter para propiedad tablePhaseListenerCuadras.
	 *
	 * @return Valor de la propiedad tablePhaseListenerCuadras.
	 */
	public TableSelectPhaseListener getTablePhaseListenerManzana() {

		return this.tablePhaseListenerManzana;
	}

	/**
	 * Setter para propiedad tablePhaseListenerCuadras.
	 *
	 * @param tablePhaseListenerCuadras
	 *            Nuevo valor de la propiedad tablePhaseListenerCuadras.
	 */
	public void setTablePhaseListenerManzana(TableSelectPhaseListener tablePhaseListenerManzana) {

		this.tablePhaseListenerManzana = tablePhaseListenerManzana;
	}

	// agregaste hasta aca
	// </editor-fold >
	// <editor-fold defaultstate="collapsed" desc="Tablas utilizadas en los Filtros">
	private PaginatedTable tablaCalle;
	private PaginatedTable tablaCategoria;
	private PaginatedTable tablaCuadra;
	private PaginatedTable tablaDeclaracionJurada;
	private PaginatedTable tablaManzana;
	private PaginatedTable tablaParcela;
	private PaginatedTable tablaPlanoConstruccion;
	private PaginatedTable tablaPlanoMensura;
	private PaginatedTable tablaServicio;
	private PaginatedTable tablaSubparcela;
	private PaginatedTable tablaTipoCalle;
	private PaginatedTable tablaTipoConstruccion;
	private PaginatedTable tablaTituloPropiedad;
	private PaginatedTable tablaValorBasicoMejora;
	private PaginatedTable tablaZona;
	private PaginatedTable tablaZonificacion;
	private PaginatedTable tablaCoeficienteDepreciacion;

	public PaginatedTable getTablaCoeficienteDepreciacion() {
		return tablaCoeficienteDepreciacion;
	}

	public void setTablaCoeficienteDepreciacion(PaginatedTable tablaCoeficienteDepreciacion) {
		this.tablaCoeficienteDepreciacion = tablaCoeficienteDepreciacion;
	}

	public PaginatedTable getTablaCalle() {
		return tablaCalle;
	}

	public void setTablaCalle(PaginatedTable tablaCalle) {
		this.tablaCalle = tablaCalle;
	}

	public PaginatedTable getTablaCategoria() {
		return tablaCategoria;
	}

	public void setTablaCategoria(PaginatedTable tablaCategoria) {
		this.tablaCategoria = tablaCategoria;
	}

	public PaginatedTable getTablaCuadra() {
		return tablaCuadra;
	}

	public void setTablaCuadra(PaginatedTable tablaCuadra) {
		this.tablaCuadra = tablaCuadra;
	}

	public PaginatedTable getTablaDeclaracionJurada() {
		return tablaDeclaracionJurada;
	}

	public void setTablaDeclaracionJurada(PaginatedTable tablaDeclaracionJurada) {
		this.tablaDeclaracionJurada = tablaDeclaracionJurada;
	}

	public PaginatedTable getTablaManzana() {
		return tablaManzana;
	}

	public void setTablaManzana(PaginatedTable tablaManzana) {
		this.tablaManzana = tablaManzana;
	}

	public PaginatedTable getTablaParcela() {
		return tablaParcela;
	}

	public void setTablaParcela(PaginatedTable tablaParcela) {
		this.tablaParcela = tablaParcela;
	}

	public PaginatedTable getTablaPlanoConstruccion() {
		return tablaPlanoConstruccion;
	}

	public void setTablaPlanoConstruccion(PaginatedTable tablaPlanoConstruccion) {
		this.tablaPlanoConstruccion = tablaPlanoConstruccion;
	}

	public PaginatedTable getTablaPlanoMensura() {
		return tablaPlanoMensura;
	}

	public void setTablaPlanoMensura(PaginatedTable tablaPlanoMensura) {
		this.tablaPlanoMensura = tablaPlanoMensura;
	}

	public PaginatedTable getTablaServicio() {
		return tablaServicio;
	}

	public void setTablaServicio(PaginatedTable tablaServicio) {
		this.tablaServicio = tablaServicio;
	}

	public PaginatedTable getTablaSubparcela() {
		return tablaSubparcela;
	}

	public void setTablaSubparcela(PaginatedTable tablaSubparcela) {
		this.tablaSubparcela = tablaSubparcela;
	}

	public PaginatedTable getTablaTipoCalle() {
		return tablaTipoCalle;
	}

	public void setTablaTipoCalle(PaginatedTable tablaTipoCalle) {
		this.tablaTipoCalle = tablaTipoCalle;
	}

	public PaginatedTable getTablaTipoConstruccion() {
		return tablaTipoConstruccion;
	}

	public void setTablaTipoConstruccion(PaginatedTable tablaTipoConstruccion) {
		this.tablaTipoConstruccion = tablaTipoConstruccion;
	}

	public PaginatedTable getTablaTituloPropiedad() {
		return tablaTituloPropiedad;
	}

	public void setTablaTituloPropiedad(PaginatedTable tablaTituloPropiedad) {
		this.tablaTituloPropiedad = tablaTituloPropiedad;
	}

	public PaginatedTable getTablaValorBasicoMejora() {
		return tablaValorBasicoMejora;
	}

	public void setTablaValorBasicoMejora(PaginatedTable tablaValorBasicoMejora) {
		this.tablaValorBasicoMejora = tablaValorBasicoMejora;
	}

	public PaginatedTable getTablaZona() {
		return tablaZona;
	}

	public void setTablaZona(PaginatedTable tablaZona) {
		this.tablaZona = tablaZona;
	}

	public PaginatedTable getTablaZonificacion() {
		return tablaZonificacion;
	}

	public void setTablaZonificacion(PaginatedTable tablaZonificacion) {
		this.tablaZonificacion = tablaZonificacion;
	}

	private Map<String, Calle> mapaCalles;

	public Map<String, Calle> getMapaCalles() {
		if(this.mapaCalles == null) {
			this.armarMapaCalles();
		}

		return mapaCalles;
	}

	public void setMapaCalles(Map<String, Calle> mapaCalles) {
		this.mapaCalles = mapaCalles;
	}

	private Map<String, Zona> mapaZona;

	public Map<String, Zona> getMapaZona() {
		this.armarMapaZona();

		return mapaZona;
	}

	public void setMapaZona(Map<String, Zona> mapaZona) {
		this.mapaZona = mapaZona;
	}

	private Map<String, Cuadra> mapaCuadras;

	public Map<String, Cuadra> getMapaCuadras() {
		if(this.mapaCuadras == null) {
			this.armarMapaCuadras();
		}

		return mapaCuadras;
	}

	public void setMapaCuadras(Map<String, Cuadra> mapaCuadras) {
		this.mapaCuadras = mapaCuadras;
	}

	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	/**
	 * Arma el mapa de Areas del usuario logueado. Si el usuario es root, usa todas las areas del sistema.
	 */
	private void armarMapaZona() {
		try {
			this.mapaZona = new LinkedHashMap<String, Zona>();
			this.getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
			FiltroZona locFiltro = new FiltroZona();
			this.getRemoteSystemAdministracionZonificacion().setLlave(this.getSessionBean1().getLlave());
			List<Zona> locListaZona = this.getRemoteSystemAdministracionZonificacion().findListaZonas(locFiltro).getListaResultados();
			Collections.sort(locListaZona, new Comparator<Zona>() {
				@Override
				public int compare(Zona o1, Zona o2) {

					// para comparar sin acentos
					String zona1 = Util.reemplazarAcentos(o1.getNombre());
					String zona2 = Util.reemplazarAcentos(o2.getNombre());

					return zona1.compareToIgnoreCase(zona2);
				}
			});
			for(Zona cadaZona : locListaZona) {
				mapaZona.put(cadaZona.getNombre(), cadaZona);

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void armarMapaCalles() {
		try {
			this.mapaCalles = new LinkedHashMap<String, Calle>();
			this.getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
			FiltroCalle locFiltro = new FiltroCalle();
			locFiltro.setEstado(true);
			List<Calle> locListaCalles = this.getRemoteSystemInformacionGeografica().findListaCalles(locFiltro).getListaResultados();
			Collections.sort(locListaCalles, new Comparator<Calle>() {
				@Override
				public int compare(Calle o1, Calle o2) {

					// para comparar sin acentos
					String calle1 = Util.reemplazarAcentos(o1.getNombre());
					String calle2 = Util.reemplazarAcentos(o2.getNombre());

					return calle1.compareToIgnoreCase(calle2);
				}
			});
			for(Calle cadaCalle : locListaCalles) {
				mapaCalles.put(cadaCalle.getNombre(), cadaCalle);

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// </editor-fold>

	private void armarMapaCuadras() {
		try {
			this.mapaCuadras = new HashMap<String, Cuadra>();
			this.getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
			List<Cuadra> locListaCuadras = this.getRemoteSystemInformacionGeografica().findListaCuadras(new FiltroCuadra()).getListaResultados();
			for(Cuadra cadaCuadra : locListaCuadras) {
				mapaCuadras.put(cadaCuadra.getNombre(), cadaCuadra);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getListaCuadrasCalleComienzo(Calle pCalle) {
		if(pCalle == null) {
			return((this.listaCallesComienzoDinamica != null) ? this.listaCallesComienzoDinamica : new ArrayList<String>());

		}
		List<String> retorno = new ArrayList<String>();
		this.listaCallesComienzoDinamica = new ArrayList();
		for(Cuadra cadaCuadra : this.getMapaCuadras().values()) {
			if(cadaCuadra.getCalle().getIdCalle() == pCalle.getIdCalle()) {
				if((cadaCuadra.getCalleComienza() != null && !retorno.contains(cadaCuadra.getCalleComienza().getNombre())) && cadaCuadra.getCalleComienza().isActivo()) {
					retorno.add(cadaCuadra.getCalleComienza().getNombre());
					this.listaCallesComienzoDinamica.add(cadaCuadra.getCalleComienza().getNombre());
				}
			}
		}

		Collections.sort(retorno, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {

				// para comparar sin acentos
				String calle1 = Util.reemplazarAcentos(o1);
				String calle2 = Util.reemplazarAcentos(o2);

				return calle1.compareToIgnoreCase(calle2);
			}
		});

		return retorno;
	}

	public List<String> getListaCuadrasCalleFin(Calle pCalle, Calle pCalleComienza) {
		List<String> retorno = new ArrayList<String>();

		if(pCalle == null && this.listaCallesFinDinamica != null && !this.listaCallesFinDinamica.isEmpty()) {
			return this.listaCallesFinDinamica;
		}

		if(pCalle == null) {
			return new ArrayList<String>();
		}

		this.listaCallesFinDinamica = new ArrayList();
		for(Cuadra cadaCuadra : this.getMapaCuadras().values()) {
			if(cadaCuadra.getCalle().getIdCalle() == pCalle.getIdCalle() && cadaCuadra.getCalleComienza() != null
					&& cadaCuadra.getCalleComienza().getIdCalle() == pCalleComienza.getIdCalle()) {
				if(cadaCuadra.getCalleFinaliza() != null && !retorno.contains(cadaCuadra.getCalleFinaliza().getNombre()) && cadaCuadra.getCalleFinaliza().isActivo()) {
					retorno.add(cadaCuadra.getCalleFinaliza().getNombre());
					this.listaCallesFinDinamica.add(cadaCuadra.getCalleFinaliza().getNombre());
				}
			}
		}

		return retorno;
	}

	public String getStringCallePorId(Long pId) {
		for(Calle cadaCalle : this.getMapaCalles().values()) {
			if(cadaCalle.getIdCalle() == pId.longValue()) {
				return cadaCalle.getNombre();
			}
		}
		return null;
	}
}