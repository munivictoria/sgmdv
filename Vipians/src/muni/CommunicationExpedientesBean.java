
package muni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import muni.expedientes.utils.FiltroListaTrabajo;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.model.MultipleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.expedientes.recurso.filtro.FiltroDocumentoCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroEstadoTramite;
import com.trascender.expedientes.recurso.filtro.FiltroExpediente;
import com.trascender.expedientes.recurso.filtro.FiltroFaseCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroProcedimiento;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.expedientes.system.interfaces.SystemCatalogos;
import com.trascender.expedientes.system.interfaces.SystemExpedientes;
import com.trascender.expedientes.system.interfaces.SystemProcedimientos;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.utiles.Constantes;

public class CommunicationExpedientesBean extends AbstractSessionBean {

	public static final long serialVersionUID = 5184895048527289305L;

	private Properties props;
	private Context ctx;

	private SystemCatalogos remoteSystemCatalogos;
	private SystemExpedientes remoteSystemExpedientes;
	private SystemProcedimientos remoteSystemProcedimientos;

	private List<TramiteProcedimiento> listaTramiteProcedimientos;
	private List<Expediente> listaExpedientes;
	private List<TramiteCatalogo> listaTramiteCatalogos;
	private List<FaseCatalogo> listaFaseCatalogos;
	private List<Procedimiento> listaProcedimientos;
	private List<FaseProcedimiento> listaFaseProcedimientos;
	private List<DocumentoCatalogo> listaDocumentoCatalogos;
	private List<EstadoTramite> listaEstadosTramite;

	private PaginatedTable tablaDocumentoCatalogo;
	private PaginatedTable tablaTramiteCatalogo;
	private PaginatedTable tablaFaseCatalogo;
	private PaginatedTable tablaProcedimiento;
	private PaginatedTable tablaFaseProcedimiento;
	private PaginatedTable tablaTramiteProcedimiento;
	private PaginatedTable tablaExpediente;
	private PaginatedTable tablaEstadosTramite;

	private MultipleSelectOptionsList lbFasesMultipleOptions = new MultipleSelectOptionsList();
	private MultipleSelectOptionsList lbTramitesMultipleOptions = new MultipleSelectOptionsList();
	private MultipleSelectOptionsList lbProcedimientosMultipleOptions = new MultipleSelectOptionsList();

	public Map<Object, List<NodoExpediente>> mapProcedimiento = new HashMap<Object, List<NodoExpediente>>();
	public Map<Object, List<NodoExpediente>> mapFaseCatalogo = new HashMap<Object, List<NodoExpediente>>();
	public Map<Object, List<NodoExpediente>> mapTramiteCatalogo = new HashMap<Object, List<NodoExpediente>>();

	public FiltroListaTrabajo filtroListaTrabajo = new FiltroListaTrabajo();

	private List<DiaFeriado> listaFeriados;

	public FiltroListaTrabajo getFiltroListaTrabajo() {
		return filtroListaTrabajo;
	}

	public void setFiltroListaTrabajo(FiltroListaTrabajo filtroListaTrabajo) {
		this.filtroListaTrabajo = filtroListaTrabajo;
	}

	public MultipleSelectOptionsList getLbFasesMultipleOptions() {
		return lbFasesMultipleOptions;
	}

	public void setLbFasesMultipleOptions(MultipleSelectOptionsList lbFasesMultipleOptions) {
		this.lbFasesMultipleOptions = lbFasesMultipleOptions;
	}

	public MultipleSelectOptionsList getLbTramitesMultipleOptions() {
		return lbTramitesMultipleOptions;
	}

	public void setLbTramitesMultipleOptions(MultipleSelectOptionsList lbTramitesMultipleOptions) {
		this.lbTramitesMultipleOptions = lbTramitesMultipleOptions;
	}

	public MultipleSelectOptionsList getLbProcedimientosMultipleOptions() {
		return lbProcedimientosMultipleOptions;
	}

	public void setLbProcedimientosMultipleOptions(MultipleSelectOptionsList lbProcedimientosMultipleOptions) {
		this.lbProcedimientosMultipleOptions = lbProcedimientosMultipleOptions;
	}

	public List<FaseCatalogo> getListaFaseCatalogos() {
		return listaFaseCatalogos;
	}

	public void setListaFaseCatalogos(List<FaseCatalogo> listaFaseCatalogos) {
		this.listaFaseCatalogos = listaFaseCatalogos;
	}

	public Map<Object, List<NodoExpediente>> getMapProcedimiento() {
		return mapProcedimiento;
	}

	public void setMapProcedimiento(Map<Object, List<NodoExpediente>> mapProcedimiento) {
		this.mapProcedimiento = mapProcedimiento;
	}

	public Map<Object, List<NodoExpediente>> getMapFaseCatalogo() {
		return mapFaseCatalogo;
	}

	public void setMapFaseCatalogo(Map<Object, List<NodoExpediente>> mapFaseCatalogo) {
		this.mapFaseCatalogo = mapFaseCatalogo;
	}

	public Map<Object, List<NodoExpediente>> getMapTramiteCatalogo() {
		return mapTramiteCatalogo;
	}

	public void setMapTramiteCatalogo(Map<Object, List<NodoExpediente>> mapTramiteCatalogo) {
		this.mapTramiteCatalogo = mapTramiteCatalogo;
	}

	public List<Procedimiento> getListaProcedimientos() {
		return listaProcedimientos;
	}

	public void setListaProcedimientos(List<Procedimiento> listaProcedimientos) {
		this.listaProcedimientos = listaProcedimientos;
	}

	public List<FaseProcedimiento> getListaFaseProcedimientos() {
		return listaFaseProcedimientos;
	}

	public void setListaFaseProcedimientos(List<FaseProcedimiento> listaFaseProcedimientos) {
		this.listaFaseProcedimientos = listaFaseProcedimientos;
	}

	public List<TramiteProcedimiento> getListaTramiteProcedimientos() {
		return listaTramiteProcedimientos;
	}

	public void setListaTramiteProcedimientos(List<TramiteProcedimiento> listaTramiteProcedimientos) {
		this.listaTramiteProcedimientos = listaTramiteProcedimientos;
	}

	public List<Expediente> getListaExpedientes() {
		return listaExpedientes;
	}

	public void setListaExpedientes(List<Expediente> listaExpedientes) {
		this.listaExpedientes = listaExpedientes;
	}

	public List<DocumentoCatalogo> getListaDocumentoCatalogos() {
		return listaDocumentoCatalogos;
	}

	public void setListaDocumentoCatalogos(List<DocumentoCatalogo> listaDocumentoCatalogos) {
		this.listaDocumentoCatalogos = listaDocumentoCatalogos;
	}

	public List<EstadoTramite> getListaEstadosTramite() {
		return listaEstadosTramite;
	}

	public void setListaEstadosTramite(List<EstadoTramite> listaEstadosTramite) {
		this.listaEstadosTramite = listaEstadosTramite;
	}

	public PaginatedTable getTablaFaseCatalogo() {
		return tablaFaseCatalogo;
	}

	public void setTablaFaseCatalogo(PaginatedTable tablaFaseCatalogo) {
		this.tablaFaseCatalogo = tablaFaseCatalogo;
	}

	public PaginatedTable getTablaProcedimiento() {
		return tablaProcedimiento;
	}

	public void setTablaProcedimiento(PaginatedTable tablaProcedimiento) {
		this.tablaProcedimiento = tablaProcedimiento;
	}

	public PaginatedTable getTablaFaseProcedimiento() {
		return tablaFaseProcedimiento;
	}

	public void setTablaFaseProcedimiento(PaginatedTable tablaFaseProcedimiento) {
		this.tablaFaseProcedimiento = tablaFaseProcedimiento;
	}

	public PaginatedTable getTablaTramiteProcedimiento() {
		return tablaTramiteProcedimiento;
	}

	public void setTablaTramiteProcedimiento(PaginatedTable tablaTramiteProcedimiento) {
		this.tablaTramiteProcedimiento = tablaTramiteProcedimiento;
	}

	public PaginatedTable getTablaExpediente() {
		return tablaExpediente;
	}

	public void setTablaExpediente(PaginatedTable tablaExpediente) {
		this.tablaExpediente = tablaExpediente;
	}

	public PaginatedTable getTablaDocumentoCatalogo() {
		return tablaDocumentoCatalogo;
	}

	public void setTablaDocumentoCatalogo(PaginatedTable tablaDocumentoCatalogo) {
		this.tablaDocumentoCatalogo = tablaDocumentoCatalogo;
	}

	public PaginatedTable getTablaEstadosTramite() {
		return tablaEstadosTramite;
	}

	public void setTablaEstadosTramite(PaginatedTable tablaEstadosTramite) {
		this.tablaEstadosTramite = tablaEstadosTramite;
	}

	public SystemCatalogos getRemoteSystemCatalogos() {
		return remoteSystemCatalogos;
	}

	public void setRemoteSystemCatalogos(SystemCatalogos remoteSystemCatalogos) {
		this.remoteSystemCatalogos = remoteSystemCatalogos;
	}

	public SystemExpedientes getRemoteSystemExpedientes() {
		remoteSystemExpedientes.setLlave(this.getSessionBean1().getLlave());
		return remoteSystemExpedientes;
	}

	public void setRemoteSystemExpedientes(SystemExpedientes remoteSystemExpedientes) {
		this.remoteSystemExpedientes = remoteSystemExpedientes;
	}

	public SystemProcedimientos getRemoteSystemProcedimientos() {
		return remoteSystemProcedimientos;
	}

	public void setRemoteSystemProcedimientos(SystemProcedimientos remoteSystemProcedimientos) {
		this.remoteSystemProcedimientos = remoteSystemProcedimientos;
	}

	private void _init() throws Exception {
	}

	public CommunicationExpedientesBean() {
		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "localhost:1099");
		try {
			ctx = new InitialContext(props);
			// interfaces
			// TABLAS

			this.remoteSystemCatalogos = (SystemCatalogos) ctx.lookup(SystemCatalogos.JNDI_NAME);
			this.remoteSystemExpedientes = (SystemExpedientes) ctx.lookup(SystemExpedientes.JNDI_NAME);
			this.remoteSystemProcedimientos = (SystemProcedimientos) ctx.lookup(SystemProcedimientos.JNDI_NAME);

			System.out.println("CommunicationExpedientesBean");
			FiltroTramiteCatalogo locFiltroTramiteCatalogo = new FiltroTramiteCatalogo();
			locFiltroTramiteCatalogo.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaTramiteCatalogo = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(TramiteCatalogo.serialVersionUID),
					"#{expedientes$ABMTramiteCatalogo$AdminTramiteCatalogo}", locFiltroTramiteCatalogo);

			FiltroFaseCatalogo locFiltroFaseCatalogo = new FiltroFaseCatalogo();
			locFiltroFaseCatalogo.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaFaseCatalogo = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(FaseCatalogo.serialVersionUID),
					"#{expedientes$ABMFaseCatalogo$AdminFaseCatalogo}", locFiltroFaseCatalogo);

			FiltroDocumentoCatalogo locFiltroDocumentoCatalogo = new FiltroDocumentoCatalogo();
			locFiltroDocumentoCatalogo.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaDocumentoCatalogo = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(DocumentoCatalogo.serialVersionUID),
					"#{expedientes$ABMDocumentoCatalogo$AdminDocumentoCatalogo}", locFiltroDocumentoCatalogo);

			FiltroProcedimiento locFiltroProcedimiento = new FiltroProcedimiento();
			locFiltroProcedimiento.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaProcedimiento = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Procedimiento.serialVersionUID),
					"#{expedientes$ABMProcedimiento$AdminProcedimiento}", locFiltroProcedimiento);
			// FiltroFaseProcedimiento locFiltroFaseProcedimiento = new
			// FiltroFaseProcedimiento();
			// locFiltroFaseProcedimiento.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			// this.tablaFaseProcedimiento = new
			// PaginatedTable(this.getSessionBean1().getAtributosConsultables(FaseProcedimiento.serialVersionUID),
			// "#{expedientes$ABMFaseProcedimiento$AdminFaseProcedimiento}",
			// locFiltroFaseProcedimiento);
			// FiltroTramiteProcedimiento locFiltroTramiteProcedimiento = new
			// FiltroTramiteProcedimiento();
			// locFiltroTramiteProcedimiento.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			// this.tablaTramiteProcedimiento = new
			// PaginatedTable(this.getSessionBean1().getAtributosConsultables(TramiteProcedimiento.serialVersionUID),
			// "#{expedientes$ABMTramiteProcedimiento$AdminTramiteProcedimiento}",
			// locFiltroTramiteProcedimiento);

			FiltroExpediente locFiltroExpediente = new FiltroExpediente();
			locFiltroExpediente.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaExpediente = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(Expediente.serialVersionUID), "#{expedientes$ABMExpediente$AdminExpediente}",
					locFiltroExpediente);
			listaFeriados = getRemoteSystemExpedientes().getDiasFeriadosEntre(null, null);

			FiltroEstadoTramite locFiltroEstadosTramite = new FiltroEstadoTramite();
			locFiltroEstadosTramite.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaEstadosTramite = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(EstadoTramite.serialVersionUID),
					"#{expedientes$ABMEstadoTramite$AdminEstadoTramite}", locFiltroEstadosTramite);

		} catch(Exception e) {

			e.printStackTrace();
		}
	}

	public List<DiaFeriado> getListaFeriados() {
		return listaFeriados;
	}

	public void setListaFeriados(List<DiaFeriado> listaFeriados) {
		this.listaFeriados = listaFeriados;
	}

	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	private muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	public List<TramiteCatalogo> getListaTramiteCatalogos() {
		return listaTramiteCatalogos;
	}

	public void setListaTramiteCatalogos(List<TramiteCatalogo> listaTramiteCatalogos) {
		this.listaTramiteCatalogos = listaTramiteCatalogos;
	}

	public PaginatedTable getTablaTramiteCatalogo() {
		return tablaTramiteCatalogo;
	}

	public void setTablaTramiteCatalogo(PaginatedTable tablaTramiteCatalogo) {
		this.tablaTramiteCatalogo = tablaTramiteCatalogo;
	}

	@Override
	public void init() {
		super.init();
		try {
			_init();
		} catch(Exception e) {
			log("CommunicationComprasBean Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
	}

	// asociaciones catalogos

	@SuppressWarnings("rawtypes")
	private List listaTramitesPorFaseCatalogo = null;
	@SuppressWarnings("rawtypes")
	private List listaDocumentosPorTramiteCatalogo = null;

	@SuppressWarnings("rawtypes")
	public List getListaTramitesPorFaseCatalogo() {
		return listaTramitesPorFaseCatalogo;
	}

	@SuppressWarnings("rawtypes")
	public void setListaTramitesPorFaseCatalogo(List listaTramitesPorFaseCatalogo) {
		this.listaTramitesPorFaseCatalogo = listaTramitesPorFaseCatalogo;
	}

	@SuppressWarnings("rawtypes")
	public List getListaDocumentosPorTramiteCatalogo() {
		return listaDocumentosPorTramiteCatalogo;
	}

	@SuppressWarnings("rawtypes")
	public void setListaDocumentosPorTramiteCatalogo(List listaDocumentosPorTramiteCatalogo) {
		this.listaDocumentosPorTramiteCatalogo = listaDocumentosPorTramiteCatalogo;
	}

	// asociaciones procedimientos
	@SuppressWarnings("rawtypes")
	private List listaTramitesPorFaseProcedimientos = null;
	@SuppressWarnings("rawtypes")
	private ArrayList listaFasePorProcedimientos = null;
	@SuppressWarnings("rawtypes")
	private List listaAreasResponsables = null;
	@SuppressWarnings("rawtypes")
	private List listaUsuariosResponsables = null;
	@SuppressWarnings("rawtypes")
	private List listaAreasResponsablesNodo = null;
	@SuppressWarnings("rawtypes")
	private List listaUsuariosResponsablesNodo = null;
	@SuppressWarnings("rawtypes")
	private List listaUsuariosExtensores = null;
	
	// asociaciones expediente
	@SuppressWarnings("rawtypes")
	private List listaTramitesExpediente = null;
	@SuppressWarnings("rawtypes")
	private List listaDocumentoTramites = null;
	@SuppressWarnings("rawtypes")
	private List listaHitos = null;
	
	private List listaDocPresentada = null;
	// Lista de trabajo
	private List<Expediente> listaExpedientesSoyResponsable = null;

	private List listaFasesEspecialesCatalogo = null;

	public List getListaDocPresentada() {
		return listaDocPresentada;
	}
	
	public List getListaUsuariosExtensores() {
		return listaUsuariosExtensores;
	}

	public void setListaUsuariosExtensores(List listaUsuariosExtensores) {
		this.listaUsuariosExtensores = listaUsuariosExtensores;
	}

	public void setListaDocPresentada(List listaDocPresentada) {
		this.listaDocPresentada = listaDocPresentada;
	}

	public List getListaFasesEspecialesCatalogo() {
		return listaFasesEspecialesCatalogo;
	}

	public void setListaFasesEspecialesCatalogo(List listaFasesEspecialesCatalogo) {
		this.listaFasesEspecialesCatalogo = listaFasesEspecialesCatalogo;
	}

	@SuppressWarnings("rawtypes")
	public List getListaUsuariosResponsables() {
		return listaUsuariosResponsables;
	}

	@SuppressWarnings("rawtypes")
	public void setListaUsuariosResponsables(List listaUsuariosResponsables) {
		this.listaUsuariosResponsables = listaUsuariosResponsables;
	}

	@SuppressWarnings("rawtypes")
	public List getListaTramitesPorFaseProcedimientos() {
		return listaTramitesPorFaseProcedimientos;
	}

	@SuppressWarnings("rawtypes")
	public void setListaTramitesPorFaseProcedimientos(List listaTramitesPorFaseProcedimientos) {
		this.listaTramitesPorFaseProcedimientos = listaTramitesPorFaseProcedimientos;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getListaFasePorProcedimientos() {
		return listaFasePorProcedimientos;
	}

	@SuppressWarnings("rawtypes")
	public void setListaFasePorProcedimientos(ArrayList listaFasePorProcedimientos) {
		this.listaFasePorProcedimientos = listaFasePorProcedimientos;
	}

	@SuppressWarnings("rawtypes")
	public List getListaAreasResponsables() {
		return listaAreasResponsables;
	}

	@SuppressWarnings("rawtypes")
	public void setListaAreasResponsables(List listaAreasResponsables) {
		this.listaAreasResponsables = listaAreasResponsables;
	}

	@SuppressWarnings("rawtypes")
	public List getListaAreasResponsablesNodo() {
		return listaAreasResponsablesNodo;
	}

	@SuppressWarnings("rawtypes")
	public void setListaAreasResponsablesNodo(List listaAreasResponsablesNodo) {
		this.listaAreasResponsablesNodo = listaAreasResponsablesNodo;
	}

	@SuppressWarnings("rawtypes")
	public List getListaUsuariosResponsablesNodo() {
		return listaUsuariosResponsablesNodo;
	}

	@SuppressWarnings("rawtypes")
	public void setListaUsuariosResponsablesNodo(List listaUsuariosResponsablesNodo) {
		this.listaUsuariosResponsablesNodo = listaUsuariosResponsablesNodo;
	}

	@SuppressWarnings("rawtypes")
	public List getListaTramitesExpediente() {
		return listaTramitesExpediente;
	}

	@SuppressWarnings("rawtypes")
	public void setListaTramitesExpediente(List listaTramitesExpediente) {
		this.listaTramitesExpediente = listaTramitesExpediente;
	}

	@SuppressWarnings("rawtypes")
	public List getListaDocumentoTramites() {
		return listaDocumentoTramites;
	}

	@SuppressWarnings("rawtypes")
	public void setListaDocumentoTramites(List listaDocumentoTramites) {
		this.listaDocumentoTramites = listaDocumentoTramites;
	}

	@SuppressWarnings("rawtypes")
	public List getListaHitos() {
		return listaHitos;
	}

	@SuppressWarnings("rawtypes")
	public void setListaHitos(List listaHitos) {
		this.listaHitos = listaHitos;
	}

	public List<Expediente> getListaExpedientesSoyResponsable() {
		return listaExpedientesSoyResponsable;
	}

	public void setListaExpedientesSoyResponsable(List<Expediente> listaExpedientesSoyResponsable) {
		this.listaExpedientesSoyResponsable = listaExpedientesSoyResponsable;
	}

	// maps
	private Map<String, TramiteCatalogo> mapaTramiteCatalogo;

	public Map<String, TramiteCatalogo> getMapaTramiteCatalogo() {
		if(mapaTramiteCatalogo == null) {
			armarMapaTramiteCatalogos();
		}
		return mapaTramiteCatalogo;
	}

	public void setMapaTramiteCatalogo(Map<String, TramiteCatalogo> mapaTramiteCatalogo) {
		this.mapaTramiteCatalogo = mapaTramiteCatalogo;
	}

	private void armarMapaTramiteCatalogos() {
		try {
			this.mapaTramiteCatalogo = new LinkedHashMap<String, TramiteCatalogo>();
			this.getRemoteSystemCatalogos().setLlave(this.getSessionBean1().getLlave());
			FiltroTramiteCatalogo locFiltro = new FiltroTramiteCatalogo();
			// locFiltro.setEstado(true);
			List<TramiteCatalogo> locListaTramiteCatalogos = this.getRemoteSystemCatalogos().findListaTramiteCatalogos(locFiltro).getListaResultados();
			Collections.sort(locListaTramiteCatalogos, new Comparator<TramiteCatalogo>() {
				@Override
				public int compare(TramiteCatalogo o1, TramiteCatalogo o2) {
					String calle1 = Util.reemplazarAcentos(o1.getNombre());
					String calle2 = Util.reemplazarAcentos(o2.getNombre());
					return calle1.compareToIgnoreCase(calle2);
				}
			});
			for(TramiteCatalogo cadaTramiteCatalogo : locListaTramiteCatalogos) {
				mapaTramiteCatalogo.put(cadaTramiteCatalogo.getNombre(), cadaTramiteCatalogo);

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, Procedimiento> mapaProcedimiento;

	public Map<String, Procedimiento> getMapaProcedimiento() {
		if(this.mapaProcedimiento == null) {
			armarMapaProcedimientos();
		}
		return mapaProcedimiento;
	}

	public void setMapaProcedimiento(Map<String, Procedimiento> mapaProcedimiento) {
		this.mapaProcedimiento = mapaProcedimiento;
	}

	private void armarMapaProcedimientos() {
		try {
			this.mapaProcedimiento = new LinkedHashMap<String, Procedimiento>();
			this.getRemoteSystemProcedimientos().setLlave(this.getSessionBean1().getLlave());
			FiltroProcedimiento locFiltro = new FiltroProcedimiento();
			// locFiltro.setEstado(true);
			List<Procedimiento> locListaProcedimientos = this.getRemoteSystemProcedimientos().findListaProcedimiento(locFiltro).getListaResultados();
			Collections.sort(locListaProcedimientos, new Comparator<Procedimiento>() {
				@Override
				public int compare(Procedimiento o1, Procedimiento o2) {
					String calle1 = Util.reemplazarAcentos(o1.getNombre());
					String calle2 = Util.reemplazarAcentos(o2.getNombre());
					return calle1.compareToIgnoreCase(calle2);
				}
			});
			for(Procedimiento cadaProcedimiento : locListaProcedimientos) {
				mapaProcedimiento.put(cadaProcedimiento.getNombre(), cadaProcedimiento);

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// atributos dinamicos

	@SuppressWarnings("rawtypes")
	private ArrayList atributosDinamicosFaseCatalogo = null;

	@SuppressWarnings("rawtypes")
	public ArrayList getAtributosDinamicosFaseCatalogo() {
		return atributosDinamicosFaseCatalogo;
	}

	@SuppressWarnings("rawtypes")
	public void setAtributosDinamicosFaseCatalogo(ArrayList atributosDinamicosFaseCatalogo) {
		this.atributosDinamicosFaseCatalogo = atributosDinamicosFaseCatalogo;
	}

	@SuppressWarnings("rawtypes")
	private ArrayList atributosDinamicosTramiteCatalogo = null;

	@SuppressWarnings("rawtypes")
	public ArrayList getAtributosDinamicosTramiteCatalogo() {
		return atributosDinamicosTramiteCatalogo;
	}

	@SuppressWarnings("rawtypes")
	public void setAtributosDinamicosTramiteCatalogo(ArrayList atributosDinamicosTramiteCatalogo) {
		this.atributosDinamicosTramiteCatalogo = atributosDinamicosTramiteCatalogo;
	}

	private List listaEstadosTramitePorTramiteCatalogo;

	public List getListaEstadosTramitePorTramiteCatalogo() {
		return listaEstadosTramitePorTramiteCatalogo;
	}

	public void setListaEstadosTramitePorTramiteCatalogo(List lista) {
		this.listaEstadosTramitePorTramiteCatalogo = lista;
	}

	private Map<String, EstadoTramite> mapaEstadoTramite = new HashMap<String, EstadoTramite>();

	public void setOpcionesMapaEstadoTramite(List<EstadoTramite> pListaEstadoTramite) {
		mapaEstadoTramite = new TreeMap<String, EstadoTramite>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String objeto1 = Util.reemplazarAcentos(o1);
				String objeto2 = Util.reemplazarAcentos(o2);
				return objeto1.compareToIgnoreCase(objeto2);
			}
		});
		for(EstadoTramite cadaEstado : pListaEstadoTramite) {
			mapaEstadoTramite.put(cadaEstado.getNombre(), cadaEstado);
		}
	}

	public Map<String, EstadoTramite> getMapaEstadoTramite() {
		return mapaEstadoTramite;
	}

	private Map<String, FaseProcedimiento> mapaFasesEspeciales = null;

	public Map<String, FaseProcedimiento> getMapaFasesEspeciales() {
		return mapaFasesEspeciales;
	}

	public Map<String, FaseProcedimiento> armarMapaFasesEspeciales(FaseProcedimiento pFase) {
		if(mapaFasesEspeciales == null) {
			try {
				this.getRemoteSystemCatalogos().setLlave(this.getSessionBean1().getLlave());
				mapaFasesEspeciales = new TreeMap<String, FaseProcedimiento>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				mapaFasesEspeciales.put("", null);
				for(FaseProcedimiento cadaFase : pFase.getListaFasesEspeciales()) {
					mapaFasesEspeciales.put(cadaFase.getFaseCatalogo().getNombre(), cadaFase);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mapaFasesEspeciales;
	}
	
	public Map<String, Procedimiento> mapaProcedimientoExpediente;

	public Map<String, Procedimiento> getMapaProcedimientoExpediente() {
		if(mapaProcedimientoExpediente == null){
		try {
			this.mapaProcedimientoExpediente = new HashMap<String, Procedimiento>();
			this.getRemoteSystemProcedimientos().setLlave(this.getSessionBean1().getLlave());
			List<Procedimiento> locListaProcedimientos = 
					this.getRemoteSystemProcedimientos().getListaProcedimientosPuedoEmpezar(this.getSessionBean1().getUsuario());
			
			Collections.sort(locListaProcedimientos, new Comparator<Procedimiento>() {
				@Override
				public int compare(Procedimiento o1, Procedimiento o2) {
					String calle1 = Util.reemplazarAcentos(o1.getNombre());
					String calle2 = Util.reemplazarAcentos(o2.getNombre());
					return calle1.compareToIgnoreCase(calle2);
				}
			});
			for (Procedimiento cadaProcedimiento : locListaProcedimientos) {
				mapaProcedimientoExpediente.put(cadaProcedimiento.getNombre(), cadaProcedimiento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return mapaProcedimientoExpediente;
	}
}