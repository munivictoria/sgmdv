/*
 * SessionBean1.java
 *
 * Created on 1 de septiembre de 2006, 14:01
 * Copyright Asus
 */

package muni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.kohsuke.rngom.digested.DInterleavePattern;

import ar.trascender.util.ReflectionUtils;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.ConfiguracionAccesosDirectos;
import com.trascender.framework.recurso.persistent.ConfiguracionAtributoTabla;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ConjuntoAtributoTabla;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.recurso.transients.AtributoConsultable;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.system.interfaces.SystemParametro;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TListMap;
import com.trascender.presentacion.navegacion.MgrPilas;

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
public class SessionBean1 extends AbstractSessionBean {
	// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">

	private int __placeholder;

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {
		// numberConverterCantidad.setPattern("$ #0.000");
		numberConverterCantidad.setMinIntegerDigits(1);
		numberConverterCantidad.setMaxIntegerDigits(40);
		numberConverterCantidad.setMaxFractionDigits(3);
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de datos de la sesion.
	 * </p>
	 */
	public SessionBean1() {
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con ambito.
	 * </p>
	 */
	protected ApplicationBean1 getApplicationBean1() {
		return (ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>
	 * Se llama a este metodo al agregar este bean al ambito de la sesion. Normalmente, esto ocurre como resultado de la evaluacion de una expresion de enlace
	 * de valores o de metodos, que utiliza la funcion de bean administrado para crear una instancia de este bean y almacenarla en el ambito de la sesion.
	 * </p>
	 *
	 * <p>
	 * Puede personalizar este metodo para inicializar y almacenar en cache los valores o recursos necesarios para el ciclo de duracion de una sesion de usuario
	 * en particular.
	 * </p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		// Realizar inicio de aplicacion que debe finalizar
		// *antes* de que se inicien los componentes administrados
		// TODO - Agregar codigo de inicio propio aqui

		// <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
		// Iniciar componentes administrados automaticamente
		// *Nota* - esta logica NO debe modificarse
		try {
			_init();
		} catch(Exception e) {
			log("SessionBean1 Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicacion que debe finalizar
		// *despues* de que se inicien los componentes administrados
		// TODO - Agregar codigo de inicio propio aqui

	}

	/**
	 * <p>
	 * Se llama a este metodo cuando la sesion que lo contiene este apunto de configurarse en modo pasivo. Normalmente, esto ocurre en un contenedor de servlet
	 * distribuido cuando la sesion este apunto de transferirse a otra instancia de contenedor, despues de la cual se llamare al metodo <code>activate()</code>
	 * para indicar que la transferencia se ha completado.
	 * </p>
	 *
	 * <p>
	 * Puede personalizar este metodo para liberar las referencias a datos o recursos de sesion que no pueden serializarse con la propia sesion.
	 * </p>
	 */
	@Override
	public void passivate() {
	}

	/**
	 * <p>
	 * Se llama a este metodo cuando la sesion que lo contiene se reactiva.
	 * </p>
	 *
	 * <p>
	 * Puede personalizar este metodo para volver a adquirir las referencias a datos o recursos de la sesion que no pudieron serializarse con la propia sesion.
	 * </p>
	 */
	@Override
	public void activate() {
	}

	/**
	 * <p>
	 * Se llama a este metodo al eliminar este bean del ambito de la sesion. Normalmente, esto ocurre cuando se supera el tiempo de espera de la sesion o la
	 * aplicacion la finaliza.
	 * </p>
	 *
	 * <p>
	 * Puede personalizar este metodo para limpiar los recursos asignados durante la ejecucion del metodo <code>init()</code> o mas adelante durante el ciclo de
	 * vida de la aplicacion.
	 * </p>
	 */
	@Override
	public void destroy() {
	}

	/**
	 * Conserva el valor de la propiedad llave.
	 */
	private long llave = 0;

	/**
	 * Getter para propiedad llave.
	 *
	 * @return Valor de la propiedad llave.
	 */
	public long getLlave() {

		return this.llave;
	}

	/**
	 * Setter para propiedad llave.
	 *
	 * @param llave
	 *            Nuevo valor de la propiedad llave.
	 */
	public void setLlave(long llave) {

		this.llave = llave;
	}

	private NumberConverter numberConverterCantidad = new NumberConverter();

	public NumberConverter getNumberConverterCantidad() {
		return numberConverterCantidad;
	}

	public void setNumberConverterCantidad(NumberConverter numberConverterCantidad) {
		this.numberConverterCantidad = numberConverterCantidad;
	}

	/**
	 * Conserva el valor de la propiedad mgrPilas.
	 */
	private MgrPilas mgrPilas;

	/**
	 * Getter para propiedad mgrPilas.
	 *
	 * @return Valor de la propiedad mgrPilas.
	 */
	public MgrPilas getMgrPilas() {
		if(this.mgrPilas == null) {
			this.mgrPilas = new MgrPilas();
		}
		return this.mgrPilas;
	}

	/**
	 * Setter para propiedad mgrPilas.
	 *
	 * @param mgrPilas
	 *            Nuevo valor de la propiedad mgrPilas.
	 */
	public void setMgrPilas(MgrPilas mgrPilas) {

		this.mgrPilas = mgrPilas;
	}

	/**
	 * Conserva el valor de la propiedad ordenamiento.
	 */
	/**
	 * Getter para propiedad ordenamiento.
	 *
	 * @return Valor de la propiedad ordenamiento.
	 */
	/**
	 * Setter para propiedad ordenamiento.
	 *
	 * @param ordenamiento
	 *            Nuevo valor de la propiedad ordenamiento.
	 */
	/**
	 * Conserva el valor de la propiedad rutaOperacion.
	 */
	private String rutaOperacion;

	/**
	 * Getter para propiedad rutaOperacion.
	 *
	 * @return Valor de la propiedad rutaOperacion.
	 */
	public String getRutaOperacion() {
		return this.rutaOperacion;
	}

	/**
	 * Setter para propiedad rutaOperacion.
	 *
	 * @param rutaOperacion
	 *            Nuevo valor de la propiedad rutaOperacion.
	 */
	public void setRutaOperacion(String rutaOperacion) {
		this.rutaOperacion = rutaOperacion;
	}

	/**
	 * Conserva el valor de la propiedad personaUsuario.
	 */
	private PersonaFisica personaUsuario;

	/**
	 * Getter para propiedad personaUsuario.
	 *
	 * @return Valor de la propiedad personaUsuario.
	 */
	public PersonaFisica getPersonaUsuario() {

		return this.personaUsuario;
	}

	/**
	 * Setter para propiedad personaUsuario.
	 *
	 * @param personaUsuario
	 *            Nuevo valor de la propiedad personaUsuario.
	 */
	public void setPersonaUsuario(PersonaFisica personaUsuario) {

		this.personaUsuario = personaUsuario;
	}

	/**
	 * Conserva el valor de la propiedad usuario.
	 */
	private Usuario usuario;

	/**
	 * Getter para propiedad usuario.
	 *
	 * @return Valor de la propiedad usuario.
	 */
	public Usuario getUsuario() {

		return this.usuario;
	}

	/**
	 * Setter para propiedad usuario.
	 *
	 * @param usuario
	 *            Nuevo valor de la propiedad usuario.
	 */
	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

	/**
	 * Conserva el valor de la propiedad objetoImpresion.
	 */
	private Object objetoImpresion;

	/**
	 * Getter para propiedad objetoImpresion.
	 *
	 * @return Valor de la propiedad objetoImpresion.
	 */
	public Object getObjetoImpresion() {

		return this.objetoImpresion;
	}

	/**
	 * Setter para propiedad objetoImpresion.
	 *
	 * @param objetoImpresion
	 *            Nuevo valor de la propiedad objetoImpresion.
	 */
	public void setObjetoImpresion(Object objetoImpresion) {

		this.objetoImpresion = objetoImpresion;
	}

	/**
	 * Conserva el valor de la propiedad test.
	 */
	private Object test;

	/**
	 * Getter para propiedad test.
	 *
	 * @return Valor de la propiedad test.
	 */
	public Object getTest() {

		return this.test;
	}

	/**
	 * Setter para propiedad test.
	 *
	 * @param test
	 *            Nuevo valor de la propiedad test.
	 */
	public void setTest(Object test) {

		this.test = test;
	}

	/**
	 * Conserva el valor de la propiedad test2.
	 */
	private Object test2;

	/**
	 * Getter para propiedad test2.
	 *
	 * @return Valor de la propiedad test2.
	 */
	public Object getTest2() {

		return this.test2;
	}

	/**
	 * Setter para propiedad test2.
	 *
	 * @param test2
	 *            Nuevo valor de la propiedad test2.
	 */
	public void setTest2(Object test2) {

		this.test2 = test2;
	}

	private String accion;

	/**
	 * La accion que es esta llevando a cabo, usar las constantes de la clase Constante
	 * 
	 * @return
	 */
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	private Persona personaSeleccionada;
	private Parcela parcelaSeleccionada;
	private String nroParcela;
	private String nroDocumento;

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNroParcela() {
		return nroParcela;
	}

	public void setNroParcela(String nroParcela) {
		this.nroParcela = nroParcela;
	}

	public Parcela getParcelaSeleccionada() {
		return parcelaSeleccionada;
	}

	public void setParcelaSeleccionada(Parcela parcelaSeleccionada) {
		this.parcelaSeleccionada = parcelaSeleccionada;
	}

	public Persona getPersonaSeleccionada() {
		return personaSeleccionada;
	}

	public void setPersonaSeleccionada(Persona personaSeleccionada) {
		this.personaSeleccionada = personaSeleccionada;
	}

	public void actualizarPagina(ActionEvent evento) {
		String url = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("paginaNavegacion");
		System.out.println("URL seleccionada: " + url);
		this.paginaActual = url;
	}

	private String paginaActual;

	public String getPaginaActual() {
		if(paginaActual == null || paginaActual.equals("")) {
			paginaActual = "Main.jsp";
		}
		return paginaActual;
	}

	public void setPaginaActual(String paginaActual) {
	}

	public ConfiguracionRecurso getConfiguracionRecurso(Long pSerialVersion) {
		return SecurityMgr.getInstance().getMapaConfiguracionesRecurso().get(pSerialVersion);
	}

	public Set<AtributoConsultable> getAtributosConsultables(Long pIdRecurso) {
		try {
			ConfiguracionRecurso locConfiguracion = this.getConfiguracionRecurso(pIdRecurso);

			// Si existen configuraciones de recursos para ese recurso, se cargan... Sino se traen las por defecto.
			if(locConfiguracion != null) {
				Set<AtributoConsultable> locListaResultado = new LinkedHashSet<AtributoConsultable>();

				List<ConfiguracionAtributoTabla> listaOrdenada = new ArrayList<ConfiguracionAtributoTabla>();
				locConfiguracion = this.getRemoteSystemParametro().getConfiguracionRecursoPorId(locConfiguracion.getIdConfiguracionRecurso());
				ConjuntoAtributoTabla locConjunto = locConfiguracion.getConjuntoConUsuario(this.getUsuario());
				if(locConjunto != null) {
					listaOrdenada = locConjunto.getListaAtributosTabla();
				} else {
					locConjunto = locConfiguracion.getConjuntoSinUsuario();
					if(locConjunto != null) {
						listaOrdenada = locConjunto.getListaAtributosTabla();
					}
				}

				if(!listaOrdenada.isEmpty()) {
					Collections.sort(listaOrdenada, new Comparator<ConfiguracionAtributoTabla>() {
						@Override
						public int compare(ConfiguracionAtributoTabla o1, ConfiguracionAtributoTabla o2) {
							return o1.getOrden().compareTo(o2.getOrden());
						}
					});

					for(ConfiguracionAtributoTabla cadaAtributo : listaOrdenada) {
						String nombre = cadaAtributo.getTipoDato() != Tipo.ATRIBUTO_DINAMICO ? ReflectionUtils.decapitalizeOnlyFirst(cadaAtributo.getNombreAtributo()) :cadaAtributo.getNombreAtributo();
						locListaResultado.add(new AtributoConsultable(nombre,
								cadaAtributo.getNombreAtributoTabla() != null && !cadaAtributo.getNombreAtributoTabla().isEmpty() ? cadaAtributo.getNombreAtributoTabla() : cadaAtributo
										.getNombreAtributo(), cadaAtributo.getTipoDato(), cadaAtributo.getAnchoColumna()));
					}

					return locListaResultado;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return this.getRemoteSystemParametro().getAtributosConsultablePorRecurso(pIdRecurso);
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
		remoteSystemParametro.setLlave(llave);
		return remoteSystemParametro;
	}

	protected ComunicationBean getComunicationBean() {
		return (ComunicationBean) getBean("ComunicationBean");
	}

	// Lista de la busqueda de cada autocomplete
	private List<Long> listaIdPersonas = new ArrayList<Long>();
	private List<Long> listaIdParcelas = new ArrayList<Long>();
	private List<Long> listaIdBienes = new ArrayList<Long>();
	private List<Long> listaIdModelosVehiculo = new ArrayList<Long>();
	private List<Long> listaIdCodigoCiiu = new ArrayList<Long>();
	private List<Long> listaIdsConceptoIngresoVario = new ArrayList<Long>();
	
	public List<Long> getListaIdsConceptoIngresoVario() {
		return listaIdsConceptoIngresoVario;
	}

	public void setListaIdsConceptoIngresoVario(
			List<Long> listaIdsConceptoIngresoVario) {
		this.listaIdsConceptoIngresoVario = listaIdsConceptoIngresoVario;
	}

	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}

	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}

	public List<Long> getListaIdParcelas() {
		return listaIdParcelas;
	}

	public void setListaIdParcelas(List<Long> listaIdParcelas) {
		this.listaIdParcelas = listaIdParcelas;
	}

	public List<Long> getListaIdBienes() {
		return listaIdBienes;
	}

	public void setListaIdBienes(List<Long> listaIdBienes) {
		this.listaIdBienes = listaIdBienes;
	}

	public List<Long> getListaIdModelosVehiculo() {
		return listaIdModelosVehiculo;
	}

	public void setListaIdModelosVehiculo(List<Long> listaIdModelosVehiculo) {
		this.listaIdModelosVehiculo = listaIdModelosVehiculo;
	}

	public List<Long> getListaIdCodigoCiiu() {
		return listaIdCodigoCiiu;
	}

	public void setListaIdCodigoCiiu(List<Long> listaIdCodigoCiiu) {
		this.listaIdCodigoCiiu = listaIdCodigoCiiu;
	}
	
	private ConfiguracionAccesosDirectos configuracionAccesosDirectos;

	public ConfiguracionAccesosDirectos getConfiguracionAccesosDirectos() {
		if (configuracionAccesosDirectos == null) {
			levantarConfiguracionAccesoDirecto();
		}
		return configuracionAccesosDirectos;
	}
	
	public void levantarConfiguracionAccesoDirecto(){
		try {
			configuracionAccesosDirectos = getRemoteSystemParametro().getConfiguracionPorUsuario(this.usuario.getIdUsuario());
		} catch (TrascenderException e) {
			e.printStackTrace();
		}
	}

	public void setConfiguracionAccesosDirectos(
			ConfiguracionAccesosDirectos locConfiguracionAccesosDirectos) {
		this.configuracionAccesosDirectos = locConfiguracionAccesosDirectos;
	}
	
	private TListMap<Long, Reporte> mapaDeListasReportesDelUsuario;

	public TListMap<Long, Reporte> getMapaDeListasReportesDelUsuario() {
		if(mapaDeListasReportesDelUsuario == null) {
			mapaDeListasReportesDelUsuario = new TListMap<Long, Reporte>();
		}

		return mapaDeListasReportesDelUsuario;
	}

	public void setMapaDeListasReportesDelUsuario(TListMap<Long, Reporte> mapaDeListasReportesDelUsuario) {
		this.mapaDeListasReportesDelUsuario = mapaDeListasReportesDelUsuario;
	}
	
}