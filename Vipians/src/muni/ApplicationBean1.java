/*
 * ApplicationBean1.java
 *
 * Created on 1 de septiembre de 2006, 14:01
 * Copyright Asus
 */
package muni;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.sun.rave.web.ui.appbase.AbstractApplicationBean;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.trascender.compras.recurso.filtros.FiltroTipoBien;
import com.trascender.compras.recurso.filtros.FiltroUnidad;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.framework.recurso.filtros.FiltroPais;
import com.trascender.framework.recurso.filtros.FiltroProvincia;
import com.trascender.framework.recurso.filtros.FiltroSecretaria;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaObligacion;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.presentacion.utiles.MgrDropDown;

/**
 * <p>
 * Application scope data bean for your application. Create properties here to
 * represent cached data that should be made available to all users and pages in
 * the application.
 * </p>
 * 
 * <p>
 * An instance of this class will be created for you automatically, the first
 * time your application evaluates a value binding expression or method binding
 * expression that references a managed bean using this class.
 * </p>
 */
public class ApplicationBean1 extends AbstractApplicationBean {

	private Municipalidad municipalidad;

	HashMap procesos = new HashMap();
	int maximoErrorLogica;

	/**
	 * <p>
	 * Construir una instancia de bean de datos de la aplicacion.
	 * </p>
	 */
	public ApplicationBean1() {

		// CAMBIAR: Agregar procesos cuando haga falta.
		// pares: nombreProceso, codigoProceso.
		procesos.put("framework", new Integer(1000));
		procesos.put("catastro", new Integer(2000));
		procesos.put("habilitaciones", new Integer(3000));
		procesos.put("compras", new Integer(4000));
		procesos.put("inventario", new Integer(5000));
		procesos.put("contabilidad", new Integer(6000));
		procesos.put("entrada", new Integer(7000));// mines: se agrega el modulo
		// de entrada..

		maximoErrorLogica = 299;
	}

	/**
	 * <p>
	 * Se llama a este metodo al agregar este bean al ambito de la aplicacion.
	 * Normalmente, esto ocurre como resultado de la evaluacion de una expresion
	 * de enlace de valores o de metodos, que utiliza la funcion de bean
	 * administrado para crear una instancia de este bean y almacenarla en el
	 * ambito de la aplicacion.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este metodo para iniciar y almacenar en cache valores
	 * de datos generales de la aplicacion (como las listas de opciones validas
	 * para componentes de listas desplegables), o para asignar recursos
	 * necesarios durante el ciclo de duracion de la aplicacion.
	 * </p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
	}

	public Municipalidad getMunicipalidad() {
		if (municipalidad == null) {
			this.inicializarMunicipalidad();
		}
		return municipalidad;
	}

	public void setMunicipalidad(Municipalidad municipalidad) {
		this.municipalidad = municipalidad;
	}

	private void inicializarMunicipalidad() {
		try {
			this.municipalidad = this.getComunicationBean().getRemoteSystemMunicipalidad().getMunicipalidad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Se llama a este metodo al eliminar este bean del ambito de la aplicacion.
	 * Normalmente, esto ocurre cuando el propio contenedor de la aplicacion la
	 * cierra.
	 * </p>
	 * 
	 * <p>
	 * Puede personalizar este metodo para limpiar los recursos asignados
	 * durante la ejecucion del metodo <code>init()</code> o mas adelante
	 * durante el ciclo de vida de la aplicacion.
	 * </p>
	 */
	@Override
	public void destroy() {
	}

	// /
	// / METODOS
	// /

	/**
	 * Le aplica las propiedades standard utilizadas en las tablas de Admin.
	 */
	public void aplicarPropiedadesTablaAdmin(Table tabla, TableRowGroup tableRowGroup) {
		String width = null;
		int cantidadFilas = this.getCantidadFilasTablasAdmin().intValue();
		tableRowGroup.setRows(cantidadFilas);

		tabla.setWidth(width); // ancho de la tabla
		tabla.setClearSortButton(true); // suprimir todo el orden
		tabla.setDeselectMultipleButton(false); // quitar seleccion multiple
		tabla.setDeselectSingleButton(false); // quitar seleccion simple
		tabla.setPaginateButton(false); // muestra/oculta todas las filas
		tabla.setPaginationControls(true); // navegacion de paginas
		tabla.setSelectMultipleButton(false); // seleccionar multiples filas
		tabla.setSortPanelToggleButton(false); // ordenaci�n de m�ltiples
		// columnas
	}

	/**
	 * Verifica si el codigo de error indica si es un error de l�gica.
	 * 
	 * @return verdadero si es un error de l�gica de negocio.
	 */
	public boolean esErrorDeLogica(String requestPathInfo, int codigoError) {
		try {
			String proceso = requestPathInfo.substring(1, requestPathInfo.indexOf("/", 1));
			int codigoProceso = ((Integer) procesos.get(proceso)).intValue();
			return ((codigoError >= codigoProceso) && (codigoError <= (codigoProceso + maximoErrorLogica)));
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Obtiene el nombre del usuario logueado.
	 * 
	 * @return [userName] Nombre1 Nombre2 Apellido
	 */
	public String getStringUsuario() {
		String nombre = "";
		if (this.getSessionBean1().getUsuario() != null) {
			nombre += "[ " + this.getSessionBean1().getUsuario().toString() + " ]";
		}
		if (this.getSessionBean1().getPersonaUsuario() != null) {
			StringTokenizer st = new StringTokenizer(this.getSessionBean1().getPersonaUsuario().toString(), ",");
			String aux = st.nextToken();
			nombre += st.nextToken() + " " + aux;
		}
		return nombre;
	}

	/**
	 * Genera un Per�odo con un A�o, una Periodicidad y el N�mero de Per�odo.
	 */
	// public Periodo getPeriodo(SystemRegistroValuado pSRV, Integer pAnio,
	// Periodicidad pPeriodicidad, Integer pNumeroPeriodo) throws Exception {
	// Periodo locPeriodo = null;
	// if ( (pAnio != null) && (pPeriodicidad != null) && (pNumeroPeriodo !=
	// null) ) {
	// pSRV.setLlave(this.getSessionBean1().getLlave());
	// locPeriodo = pSRV.getPeriodo(pPeriodicidad, new
	// Integer(pNumeroPeriodo.toString()), new Integer(pAnio.toString()));
	// }
	// return locPeriodo;
	// }

	// /
	// / ATRIBUTOS
	// /

	/**
	 * <p>
	 * Return an appropriate character encoding based on the <code>Locale</code>
	 * defined for the current JavaServer Faces view. If no more suitable
	 * encoding can be found, return "UTF-8" as a general purpose default.
	 * </p>
	 * 
	 * <p>
	 * The default implementation uses the implementation from our superclass,
	 * <code>AbstractApplicationBean</code>.
	 * </p>
	 */
	@Override
	public String getLocaleCharacterEncoding() {
		return super.getLocaleCharacterEncoding();
	}

	/**
	 * Conserva el valor de la propiedad mgrDropDown.
	 */
	private MgrDropDown mgrDropDown;

	/**
	 * Getter para propiedad mgrDropDown.
	 * 
	 * @return Valor de la propiedad mgrDropDown.
	 */
	public MgrDropDown getMgrDropDown() {
		if (this.mgrDropDown == null)
			this.mgrDropDown = new MgrDropDown();
		return this.mgrDropDown;
	}

	/**
	 * Setter para propiedad mgrDropDown.
	 * 
	 * @param mgrDropDown
	 *            Nuevo valor de la propiedad mgrDropDown.
	 */
	public void setMgrDropDown(MgrDropDown mgrDropDown) {

		this.mgrDropDown = mgrDropDown;
	}

	/**
	 * Conserva el valor de la propiedad cantidadFilasTablasAdmin.
	 */
	private Integer cantidadFilasTablasAdmin = new Integer(15);

	/**
	 * Getter para propiedad cantidadFilasTablasAdmin.
	 * 
	 * @return Valor de la propiedad cantidadFilasTablasAdmin.
	 */
	public Integer getCantidadFilasTablasAdmin() {

		return this.cantidadFilasTablasAdmin;
	}

	/**
	 * Setter para propiedad cantidadFilasTablasAdmin.
	 * 
	 * @param cantidadFilasTablasAdmin
	 *            Nuevo valor de la propiedad cantidadFilasTablasAdmin.
	 */
	public void setCantidadFilasTablasAdmin(Integer cantidadFilasTablasAdmin) {

		this.cantidadFilasTablasAdmin = cantidadFilasTablasAdmin;
	}

	/**
	 * Conserva el valor de la propiedad superUsuario.
	 */
	private String superUsuario;

	/**
	 * Getter para propiedad superUsuario.
	 * 
	 * @return Valor de la propiedad superUsuario.
	 */
	public String getSuperUsuario() {
		return this.superUsuario;
	}

	/**
	 * Setter para propiedad superUsuario.
	 * 
	 * @param superUsuario
	 *            Nuevo valor de la propiedad superUsuario.
	 */
	public void setSuperUsuario(String superUsuario) {
		this.superUsuario = superUsuario;
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected SessionBean1 getSessionBean1() {
		return (SessionBean1) getBean("SessionBean1");
	}

	protected muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	protected muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
	}

	protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	// ******** MAPAS ********
	// ComunicationBean
	private Map<String, Secretaria> mapaSecretaria = null;

	public Map<String, Secretaria> getMapaSecretaria() {
		if (mapaSecretaria == null) {
			try {
				getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
				mapaSecretaria = new TreeMap<String, Secretaria>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroSecretaria locFiltro = new FiltroSecretaria();

				List<Secretaria> locListaSecretarias = getComunicationBean().getRemoteSystemMunicipalidad().findListaSecretarias(locFiltro).getListaResultados();
				for (Secretaria cadaSecretaria : locListaSecretarias) {
					agregarNuevaSecretaria(cadaSecretaria);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaSecretaria;
	}

	public void agregarNuevaSecretaria(Secretaria pSecretaria) {
		getMapaSecretaria().put(pSecretaria.getNombre(), pSecretaria);
	}

	public void modificarSecretaria(Secretaria pSecretaria) {
		eliminarSecretaria(pSecretaria);
		agregarNuevaSecretaria(pSecretaria);
	}

	public void eliminarSecretaria(Secretaria pSecretaria) {
		Secretaria cadaSecretaria = null;
		for (String cadaLlave : this.getMapaSecretaria().keySet()) {
			cadaSecretaria = getMapaSecretaria().get(cadaLlave);
			if (cadaSecretaria.equals(pSecretaria)) {
				getMapaSecretaria().remove(cadaLlave);
				break;
			}
		}
	}
	
	private Map<String, TipoObligacion> mapaTipoObligacionTasaMenor = null;

	public Map<String, TipoObligacion> getMapaTipoObligacionTasaMenor() {
		if (mapaTipoObligacionTasaMenor == null) {
			try {
				getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().setLlave(this.getSessionBean1().getLlave());
				mapaTipoObligacionTasaMenor = new TreeMap<String, TipoObligacion>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});

				List<TipoObligacion> locListaTipoObligacion = getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTipoObligacion("", true);
				for (TipoObligacion cadaTipo: locListaTipoObligacion) {
					mapaTipoObligacionTasaMenor.put(cadaTipo.getNombre(), cadaTipo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaTipoObligacionTasaMenor;
	}
	
	// usar funciones
	
	public void agregarNuevoTipoObligacionTasaMenor(TipoObligacion pTipoObligacionTasaMenor) {
		getMapaTipoObligacionTasaMenor().put(pTipoObligacionTasaMenor.getNombre(), pTipoObligacionTasaMenor);
	}

	public void modificarTipoObligacionTasaMenor(TipoObligacion pTipoObligacionTasaMenor) {
		eliminarTipoObligacionTasaMenor(pTipoObligacionTasaMenor);
		agregarNuevoTipoObligacionTasaMenor(pTipoObligacionTasaMenor);
	}

	public void eliminarTipoObligacionTasaMenor(TipoObligacion pTipoObligacionTasaMenor) {
		TipoObligacion cadaTipoObligacionTasaMenor = null;
		for (String cadaLlave : this.getMapaTipoObligacionTasaMenor().keySet()) {
			cadaTipoObligacionTasaMenor = getMapaTipoObligacionTasaMenor().get(cadaLlave);
			if (cadaTipoObligacionTasaMenor.equals(pTipoObligacionTasaMenor)) {
				getMapaTipoObligacionTasaMenor().remove(cadaLlave);
				break;
			}
		}
	}

	private Map<String, Provincia> mapaProvincia = null;

	public Map<String, Provincia> getMapaProvincia() {
		if (mapaProvincia == null) {
			try {
				getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
				mapaProvincia = new TreeMap<String, Provincia>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroProvincia locFiltro = new FiltroProvincia();

				List<Provincia> locListaProvincias = getComunicationBean().getRemoteSystemMunicipalidad().findProvincia(locFiltro).getListaResultados();
				for (Provincia cadaProvincia : locListaProvincias) {
					mapaProvincia.put(cadaProvincia.getNombre(), cadaProvincia);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaProvincia;
	}

	public void agregarNuevaProvincia(Provincia pProvincia) {
		getMapaProvincia().put(pProvincia.getNombre(), pProvincia);
	}

	public void modificarProvincia(Provincia pProvincia) {
		eliminarProvincia(pProvincia);
		agregarNuevaProvincia(pProvincia);
	}

	public void eliminarProvincia(Provincia pProvincia) {
		Provincia cadaProvincia = null;
		for (String cadaLlave : this.getMapaProvincia().keySet()) {
			cadaProvincia = getMapaProvincia().get(cadaLlave);
			if (cadaProvincia.equals(pProvincia)) {
				getMapaProvincia().remove(cadaLlave);
				break;
			}
		}
	}

	private Map<String, Pais> mapaPais = null;

	public Map<String, Pais> getMapaPais() {
		if (mapaPais == null) {
			try {
				getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
				mapaPais = new TreeMap<String, Pais>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroPais locFiltro = new FiltroPais();

				List<Pais> locListaPaises = getComunicationBean().getRemoteSystemMunicipalidad().findPais(locFiltro).getListaResultados();
				for (Pais cadaPais : locListaPaises) {
					mapaPais.put(cadaPais.getNombre(), cadaPais);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaPais;
	}

	public void agregarNuevoPais(Pais pPais) {
		getMapaPais().put(pPais.getNombre(), pPais);
	}

	public void modificarPais(Pais pPais) {
		eliminarPais(pPais);
		agregarNuevoPais(pPais);
	}

	public void eliminarPais(Pais pPais) {
		Pais cadaPais = null;
		for (String cadaLlave : this.getMapaPais().keySet()) {
			cadaPais = getMapaPais().get(cadaLlave);
			if (cadaPais.equals(pPais)) {
				getMapaPais().remove(cadaLlave);
				break;
			}
		}
	}

	// /ComunicationBean

	// CommunicationComprasBean
	private Map<String, Unidad> mapaUnidad = null;

	public Map<String, Unidad> getMapaUnidad() {
		if (mapaUnidad == null) {
			try {
				getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(this.getSessionBean1().getLlave());
				mapaUnidad = new TreeMap<String, Unidad>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroUnidad locFiltro = new FiltroUnidad();

				List<Unidad> lista = getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findListaUnidad();
				for (Unidad cadaUnidad : lista) {
					mapaUnidad.put(cadaUnidad.getDescripcion(), cadaUnidad);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaUnidad;
	}

	public void agregarNuevaUnidad(Unidad pUnidad) {
		getMapaUnidad().put(pUnidad.getDescripcion(), pUnidad);
	}

	public void modificarUnidad(Unidad pUnidad) {
		eliminarUnidad(pUnidad);
		agregarNuevaUnidad(pUnidad);
	}

	public void eliminarUnidad(Unidad pUnidad) {
		Unidad cadaUnidad = null;
		for (String cadaLlave : this.getMapaUnidad().keySet()) {
			cadaUnidad = getMapaUnidad().get(cadaLlave);
			if (cadaUnidad.equals(pUnidad)) {
				getMapaUnidad().remove(cadaLlave);
				break;
			}
		}
	}

	private Map<String, TipoBien> mapaTipoBien = null;

	public Map<String, TipoBien> getMapaTipoBien() {
		if (mapaTipoBien == null) {
			try {
				getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(this.getSessionBean1().getLlave());
				mapaTipoBien = new TreeMap<String, TipoBien>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroTipoBien locFiltro = new FiltroTipoBien();

				List<TipoBien> lista = getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findListaTipoBien();
				for (TipoBien cadaTipo : lista) {
					mapaTipoBien.put(cadaTipo.getNombre(), cadaTipo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaTipoBien;
	}

	public void agregarNuevoTipoBien(TipoBien pTipoBien) {
		getMapaTipoBien().put(pTipoBien.getNombre(), pTipoBien);
	}

	public void modificarTipoBien(TipoBien pTipoBien) {
		eliminarTipoBien(pTipoBien);
		agregarNuevoTipoBien(pTipoBien);
	}

	public void eliminarTipoBien(TipoBien pTipoBien) {
		TipoBien cadaTipoBien = null;
		for (String cadaLlave : this.getMapaTipoBien().keySet()) {
			cadaTipoBien = getMapaTipoBien().get(cadaLlave);
			if (cadaTipoBien.equals(pTipoBien)) {
				getMapaTipoBien().remove(cadaLlave);
				break;
			}
		}
	}

	private Map<String, EstadoSolicitudSuministro> mapaEstadosSolicitudSuministro;

	public Map<String, EstadoSolicitudSuministro> getMapaEstadosSolicitudSuministro() {
		if (this.mapaEstadosSolicitudSuministro == null) {
			try {
				getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
				mapaEstadosSolicitudSuministro = new TreeMap<String, EstadoSolicitudSuministro>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				List<EstadoSolicitudSuministro> lista = getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().findListaTodosEstadosSolSum();
				for (EstadoSolicitudSuministro cadaTipo : lista) {
					mapaEstadosSolicitudSuministro.put(cadaTipo.getNombre(), cadaTipo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaEstadosSolicitudSuministro;
	}

	public void agregarNuevoEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolicitudSuministro) {
		getMapaEstadosSolicitudSuministro().put(pEstadoSolicitudSuministro.getNombre(), pEstadoSolicitudSuministro);
	}

	public void modificarEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolicitudSuministro) {
		eliminarEstadoSolicitudSuministro(pEstadoSolicitudSuministro);
		agregarNuevoEstadoSolicitudSuministro(pEstadoSolicitudSuministro);
	}

	public void eliminarEstadoSolicitudSuministro(EstadoSolicitudSuministro pEstadoSolicitudSuministro) {
		EstadoSolicitudSuministro cadaEstadoSolicitudSuministro = null;
		for (String cadaLlave : this.getMapaEstadosSolicitudSuministro().keySet()) {
			cadaEstadoSolicitudSuministro = getMapaEstadosSolicitudSuministro().get(cadaLlave);
			if (cadaEstadoSolicitudSuministro.equals(pEstadoSolicitudSuministro)) {
				getMapaEstadosSolicitudSuministro().remove(cadaLlave);
				break;
			}
		}
	}

	// /CommunicationComprasBean

	// CommunicationHabilitacionesBean

	private Map<String, TipoSepultura> mapaTipoSepultura = null;

	public Map<String, TipoSepultura> getMapaTipoSepultura() {
		if (mapaTipoSepultura == null) {
			try {
				getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(this.getSessionBean1().getLlave());
				mapaTipoSepultura = new TreeMap<String, TipoSepultura>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroTipoSepultura locFiltro = new FiltroTipoSepultura();

				List<TipoSepultura> lista = getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().findListaTipoSepultura(locFiltro).getListaResultados();

				for (TipoSepultura cadaTipo : lista) {
					mapaTipoSepultura.put(cadaTipo.getNombre(), cadaTipo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaTipoSepultura;
	}

	private Map<String, PlantillaObligacion> mapaPlantillaObligacionOSP = null;

	public Map<String, PlantillaObligacion> getMapaPlantillaObligacionOSP() {
		if (mapaPlantillaObligacionOSP == null) {
			try {
				getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(this.getSessionBean1().getLlave());
				mapaPlantillaObligacionOSP = new TreeMap<String, PlantillaObligacion>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
				TipoObligacion tipoObligacion = null;
				tipoObligacion = getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("OYSP");
				locFiltro.setTipoObligacion(tipoObligacion);

				List<PlantillaObligacion> lista = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().findListaPlantillaObligaciones(locFiltro)
						.getListaResultados();

				for (PlantillaObligacion cadaPlantilla : lista) {
					mapaPlantillaObligacionOSP.put(cadaPlantilla.getNombre(), cadaPlantilla);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaPlantillaObligacionOSP;
	}

	private Map<String, PlantillaObligacion> mapaPlantillaObligacionSHPS = null;

	public Map<String, PlantillaObligacion> getMapaPlantillaObligacionSHPS() {
		if (mapaPlantillaObligacionSHPS == null) {
			try {
				mapaPlantillaObligacionSHPS = new TreeMap<String, PlantillaObligacion>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
				TipoObligacion tipoObligacion = null;
				tipoObligacion = getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("SHPS");
				locFiltro.setTipoObligacion(tipoObligacion);

				List<PlantillaObligacion> lista = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().findListaPlantillaObligaciones(locFiltro).getListaResultados();

				for (PlantillaObligacion cadaPlantilla : lista) {
					mapaPlantillaObligacionSHPS.put(cadaPlantilla.getNombre(), cadaPlantilla);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaPlantillaObligacionSHPS;
	}

	private Map<String, PlantillaObligacion> mapaPlantillaObligacionTGI = null;

	public Map<String, PlantillaObligacion> getMapaPlantillaObligacionTGI() {
		if (mapaPlantillaObligacionTGI == null) {
			try {
				mapaPlantillaObligacionTGI = new TreeMap<String, PlantillaObligacion>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
				TipoObligacion tipoObligacion = null;
				tipoObligacion = getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("TGI");
				locFiltro.setTipoObligacion(tipoObligacion);

				List<PlantillaObligacion> lista = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().findListaPlantillaObligaciones(locFiltro).getListaResultados();

				for (PlantillaObligacion cadaPlantilla : lista) {
					mapaPlantillaObligacionTGI.put(cadaPlantilla.getNombre(), cadaPlantilla);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaPlantillaObligacionTGI;
	}
	
	private Map<String, PlantillaObligacion> mapaPlantillaObligacionArrendamiento = null;

	public Map<String, PlantillaObligacion> getMapaPlantillaObligacionArrendamiento() {
		if (mapaPlantillaObligacionArrendamiento == null) {
			try {
				mapaPlantillaObligacionArrendamiento = new TreeMap<String, PlantillaObligacion>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
				TipoObligacion tipoObligacion = null;
				tipoObligacion = getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("ARRENDAMIENTO");
				locFiltro.setTipoObligacion(tipoObligacion);

				List<PlantillaObligacion> lista = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().findListaPlantillaObligaciones(locFiltro).getListaResultados();

				for (PlantillaObligacion cadaPlantilla : lista) {
					mapaPlantillaObligacionArrendamiento.put(cadaPlantilla.getNombre(), cadaPlantilla);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaPlantillaObligacionArrendamiento;
	}
	
	private Map<String, PlantillaObligacion> mapaPlantillaObligacionTasaMenor = null;

	public Map<String, PlantillaObligacion> getMapaPlantillaObligacionTasaMenor() {
		if (mapaPlantillaObligacionTasaMenor == null) {
			try {
				mapaPlantillaObligacionTasaMenor = new TreeMap<String, PlantillaObligacion>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});

				List<PlantillaObligacion> lista = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().findPlantillasObligacionTasaMenor();
				
				for (PlantillaObligacion cadaPlantilla : lista) {
						mapaPlantillaObligacionTasaMenor.put(cadaPlantilla.getNombre(), cadaPlantilla);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaPlantillaObligacionTasaMenor;
	}

	private Map<String, PlantillaObligacion> mapaPlantillaObligacionAutomotor = null;

	public Map<String, PlantillaObligacion> getMapaPlantillaObligacionAutomotor() {
		if (mapaPlantillaObligacionAutomotor == null) {
			try {
				mapaPlantillaObligacionAutomotor = new TreeMap<String, PlantillaObligacion>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
				TipoObligacion tipoObligacion = null;
				tipoObligacion = getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("AUTOMOTOR");
				locFiltro.setTipoObligacion(tipoObligacion);

				List<PlantillaObligacion> lista = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().findListaPlantillaObligaciones(locFiltro).getListaResultados();

				for (PlantillaObligacion cadaPlantilla : lista) {
					mapaPlantillaObligacionAutomotor.put(cadaPlantilla.getNombre(), cadaPlantilla);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaPlantillaObligacionAutomotor;
	}

	private Map<String, PlantillaObligacion> mapaPlantillaObligacionCementerio = null;

	public Map<String, PlantillaObligacion> getMapaPlantillaObligacionCementerio() {
		if (mapaPlantillaObligacionCementerio == null) {
			try {
				mapaPlantillaObligacionCementerio = new TreeMap<String, PlantillaObligacion>(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String objeto1 = Util.reemplazarAcentos(o1);
						String objeto2 = Util.reemplazarAcentos(o2);
						return objeto1.compareToIgnoreCase(objeto2);
					}
				});
				FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
				TipoObligacion tipoObligacion = null;
				tipoObligacion = getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("CEMENTERIO");
				locFiltro.setTipoObligacion(tipoObligacion);

				List<PlantillaObligacion> lista = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().findListaPlantillaObligaciones(locFiltro).getListaResultados();

				for (PlantillaObligacion cadaPlantilla : lista) {
					mapaPlantillaObligacionCementerio.put(cadaPlantilla.getNombre(), cadaPlantilla);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapaPlantillaObligacionCementerio;
	}

	public void agregarNuevaPlantilla(PlantillaObligacion pPlantillaObligacion) {
		if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("OYSP")) {
			getMapaPlantillaObligacionOSP().put(pPlantillaObligacion.getNombre(), pPlantillaObligacion);
		}
		if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("SHPS")) {
			getMapaPlantillaObligacionSHPS().put(pPlantillaObligacion.getNombre(), pPlantillaObligacion);
		}
		if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("TGI")) {
			getMapaPlantillaObligacionTGI().put(pPlantillaObligacion.getNombre(), pPlantillaObligacion);
		}
		if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("AUTOMOTOR")) {
			getMapaPlantillaObligacionAutomotor().put(pPlantillaObligacion.getNombre(), pPlantillaObligacion);
		}
		if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("CEMENTERIO")) {
			getMapaPlantillaObligacionCementerio().put(pPlantillaObligacion.getNombre(), pPlantillaObligacion);
		}
		if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("Tasa_Menor")) {
			getMapaPlantillaObligacionTasaMenor().put(pPlantillaObligacion.getNombre(), pPlantillaObligacion);
		}
	}

	public void modificarPlantilla(PlantillaObligacion pPlantillaObligacion) {
		eliminarPlantilla(pPlantillaObligacion);
		agregarNuevaPlantilla(pPlantillaObligacion);
	}

	public void eliminarPlantilla(PlantillaObligacion pPlantillaObligacion) {
		Map<String, PlantillaObligacion> mapaPlantillaObligacion = null;
		if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("OYSP")) {
			mapaPlantillaObligacion = this.getMapaPlantillaObligacionOSP();
		}
		else if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("SHPS")) {
			mapaPlantillaObligacion = this.getMapaPlantillaObligacionSHPS();
		}
		else if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("TGI")) {
			mapaPlantillaObligacion = this.getMapaPlantillaObligacionTGI();
		}
		else if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("AUTOMOTOR")) {
			mapaPlantillaObligacion = this.getMapaPlantillaObligacionAutomotor();
		}
		else if (pPlantillaObligacion.getTipoObligacion().getNombre().equals("CEMENTERIO")) {
			mapaPlantillaObligacion = this.getMapaPlantillaObligacionCementerio();
		}
		else {
			mapaPlantillaObligacion = this.getMapaPlantillaObligacionTasaMenor();
		}
		PlantillaObligacion cadaPlantilla = null;
		for (String cadaLlave : mapaPlantillaObligacion.keySet()) {
			cadaPlantilla = mapaPlantillaObligacion.get(cadaLlave);
			if (cadaPlantilla.equals(pPlantillaObligacion)) {
				mapaPlantillaObligacion.remove(cadaLlave);
				break;
			}
		}
	}

	// /CommunicationHabilitacionesBean

}
