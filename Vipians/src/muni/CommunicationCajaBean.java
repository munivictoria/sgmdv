/*
 * CommunicationCajaBean.java
 *
 * Created on 28 de mayo de 2007, 13:47
 * Copyright Trascender SRL
 */
package muni;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.trascender.contabilidad.recurso.filtros.FiltroConceptoIngresoVario;
import com.trascender.contabilidad.recurso.filtros.FiltroIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.RelaConceptoIngresoVarioCuenta;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.utiles.Constantes;

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
public class CommunicationCajaBean extends AbstractSessionBean {
    
    Properties props = null;
    Context ctx = null;
    
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


    /** 
     * <p>Construir una instancia de bean de datos de la sesion.</p>
     */
    public CommunicationCajaBean() {
       props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        props.put(Context.PROVIDER_URL, "localhost:1099");
        try {
            ctx = new InitialContext(props);
            this.remoteSystemAdministracionIngresos = (SystemAdministracionIngresos) ctx.lookup(SystemAdministracionIngresos.JNDI_NAME);
            this.remoteSystemAdministracionEgresos = (SystemAdministracionEgresos) ctx.lookup(SystemAdministracionEgresos.JNDI_NAME);
            this.remoteSystemUsuario = (SystemUsuario) ctx.lookup(SystemUsuario.JNDI_NAME);

            System.out.println("CommunicationCajaBean");
            FiltroConceptoIngresoVario locFiltroConceptoIngresoVario = new FiltroConceptoIngresoVario();
			locFiltroConceptoIngresoVario.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaConceptosIngresosVarios = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(ConceptoIngresoVario.serialVersionUID), "#{comunes$ABMConceptoIngresoVario$AdminConceptoIngresoVario}", locFiltroConceptoIngresoVario);
			
			FiltroIngresoVario locFiltroIngresoVario = new FiltroIngresoVario();
			locFiltroIngresoVario.setCantidadPorPagina(Constantes.cantidadFilasTablasAdmin);
			this.tablaIngresoVario = new PaginatedTable(this.getSessionBean1().getAtributosConsultables(IngresoVario.serialVersionUID), "#{comunes$ABMIngresoVario$AdminIngresoVario}", locFiltroIngresoVario);
        } catch (Exception ex) {
            //  Logger.getLogger(ComunicationBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /** 
     * <p>Devolver una referencia al bean de datos con ambito.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }

    private muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

    /** 
     * <p>Se llama a este metodo al agregar este bean al
     * ambito de la sesion.  Normalmente, esto ocurre como resultado de la evaluaci�n
     * de una expresion de enlace de valores o de m�todos, que utiliza la
     * funcion de bean administrado para crear una instancia de este bean y almacenarla en el
     * ambito de la sesion.</p>
     * 
     * <p>Puede personalizar este metodo para inicializar y almacenar en cacho los valores
     * o recursos necesarios para el ciclo de duracion de una
     * sesion de usuario en particular.</p>
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
        } catch (Exception e) {
            log("CommunicationCajaBean Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicacion que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

    }

    /** 
     * <p>Se llama a este m�todo cuando la sesi�n que lo contiene est� apunto de
     * configurarse en modo pasivo.  Normalmente, esto ocurre en un contenedor de servlet distribuido
     * cuando la sesi�n est� apunto de transferirse a otra
     * instancia de contenedor, despu�s de la cual se llamar� al m�todo <code>activate()</code>
     * para indicar que la transferencia se ha completado.</p>
     * 
     * <p>Puede personalizar este m�todo para liberar las referencias a datos
     * o recursos de sesi�n que no pueden serializarse con la propia sesi�n.</p>
     */
    public void passivate() {
    }

    /** 
     * <p>Se llama a este m�todo cuando la sesi�n que lo contiene se
     * reactiva.</p>
     * 
     * <p>Puede personalizar este m�todo para volver a adquirir las referencias a
     * datos o recursos de la sesi�n que no pudieron serializarse con la
     * propia sesi�n.</p>
     */
    public void activate() {
    }

    /** 
     * <p>Se llama a este m�todo al eliminar este bean del
     * �mbito de la sesi�n.  Normalmente, esto ocurre cuando
     * se supera el tiempo de espera de la sesi�n o la aplicaci�n la finaliza.</p>
     * 
     * <p>Puede personalizar este m�todo para limpiar los recursos asignados
     * durante la ejecuci�n del m�todo <code>init()</code> o
     * m�s adelante durante el ciclo de vida de la aplicaci�n.</p>
     */
    public void destroy() {
    }
    
    //
    // Interfaces
    //
    
    // <editor-fold defaultstate="collapsed" desc="SystemAdministracionIngresos">
    /**
     * Definicion de la interfaz remota SystemAdministracionIngresos, para invocar
     * la logica de negocio.
     */
    
//    @EJB
    private SystemAdministracionIngresos remoteSystemAdministracionIngresos = null;
    
    public SystemAdministracionIngresos getRemoteSystemAdministracionIngresos(){
//        try{
//            if (this.remoteSystemAdministracionIngresos==null){
//                Context ctx = new InitialContext(props);
//                Object obj = ctx.lookup(SystemAdministracionIngresosHome.JNDI_NAME);
//                SystemAdministracionIngresosHome locSystemAdministracionIngresosHome = (SystemAdministracionIngresosHome) PortableRemoteObject.narrow(obj, SystemAdministracionIngresosHome.class);
//                this.remoteSystemAdministracionIngresos = locSystemAdministracionIngresosHome.create();
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        return this.remoteSystemAdministracionIngresos;
    }
    
    public void setRemoteSystemAdministracionIngresos(SystemAdministracionIngresos pRemoteSystemAdministracionIngresos){
        this.remoteSystemAdministracionIngresos = pRemoteSystemAdministracionIngresos;
    }
//     </editor-fold>

    //    @EJB
    private SystemAdministracionEgresos remoteSystemAdministracionEgresos = null;
    
    public SystemAdministracionEgresos getRemoteSystemAdministracionEgresos(){
//        try{
//            if (this.remoteSystemAdministracionIngresos==null){
//                Context ctx = new InitialContext(props);
//                Object obj = ctx.lookup(SystemAdministracionIngresosHome.JNDI_NAME);
//                SystemAdministracionIngresosHome locSystemAdministracionIngresosHome = (SystemAdministracionIngresosHome) PortableRemoteObject.narrow(obj, SystemAdministracionIngresosHome.class);
//                this.remoteSystemAdministracionIngresos = locSystemAdministracionIngresosHome.create();
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        return this.remoteSystemAdministracionEgresos;
    }
    
    public void setRemoteSystemAdministracionEgresos(SystemAdministracionEgresos pRemoteSystemAdministracionEgresos){
        this.remoteSystemAdministracionEgresos = pRemoteSystemAdministracionEgresos;
    }
//     </editor-fold>
    
    private SystemUsuario remoteSystemUsuario = null;
    
    public SystemUsuario getRemoteSystemUsuario() {
		return remoteSystemUsuario;
	}


	public void setRemoteSystemUsuario(SystemUsuario remoteSystemUsuario) {
		this.remoteSystemUsuario = remoteSystemUsuario;
	}

	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}
    //
    // ArrayLists
    //
	
	/**
     * Conserva el valor de la propiedad listaConceptosIngresoVario.
     */
    private List listaConceptosIngresoVario;
    
    /**
     * Getter para propiedad listaConceptosIngresoVario.
     * @return Valor de la propiedad listaConceptosIngresoVario.
     */
    public List getListaConceptosIngresoVario() {
        
        return this.listaConceptosIngresoVario;
    }
    
    /**
     * Setter para propiedad listaConceptosIngresoVario.
     * @param listaConceptosIngresoVario Nuevo valor de la propiedad listaConceptosIngresoVario.
     */
    public void setListaConceptosIngresoVario(List listaConceptosIngresoVario) {
        
        this.listaConceptosIngresoVario = listaConceptosIngresoVario;
    }

    /**
     * Conserva el valor de la propiedad listaIngresoVario.
     */
    private List listaIngresosVarios;

    /**
     * Getter para propiedad listaIngresosVarios.
     * @return Valor de la propiedad listaIngresosVarios.
     */
    public List getListaIngresosVarios() {

        return this.listaIngresosVarios;
    }

    /**
     * Setter para propiedad listaIngresosVarios.
     * @param listaIngresosVarios Nuevo valor de la propiedad listaIngresosVarios.
     */
    public void setListaIngresosVarios(List listaIngresosVarios) {

        this.listaIngresosVarios = listaIngresosVarios;
    }

    private PaginatedTable tablaConceptosIngresosVarios;
    private PaginatedTable tablaIngresoVario;

	public PaginatedTable getTablaConceptosIngresosVarios() {
		return tablaConceptosIngresosVarios;
	}


	public void setTablaConceptosIngresosVarios(
			PaginatedTable tablaConceptosIngresosVarios) {
		this.tablaConceptosIngresosVarios = tablaConceptosIngresosVarios;
	}


	public PaginatedTable getTablaIngresoVario() {
		return tablaIngresoVario;
	}


	public void setTablaIngresoVario(PaginatedTable tablaIngresoVario) {
		this.tablaIngresoVario = tablaIngresoVario;
	}
    
	private List<Usuario> listaUsuariosConceptoIngresoVario;
	private List<Rol> listaRolesConceptoIngresoVario;
	private List<Cuenta> listaCuentasConceptoIngresoVario;
	private List<ImputacionIngresoVario> listaImputacionesIngresoVario;

	public List<ImputacionIngresoVario> getListaImputacionesIngresoVario() {
		return listaImputacionesIngresoVario;
	}


	public void setListaImputacionesIngresoVario(
			List<ImputacionIngresoVario> listaImputacionesIngresoVario) {
		this.listaImputacionesIngresoVario = listaImputacionesIngresoVario;
	}


	public List<Usuario> getListaUsuariosConceptoIngresoVario() {
		return listaUsuariosConceptoIngresoVario;
	}


	public void setListaUsuariosConceptoIngresoVario(
			List<Usuario> listaUsuariosConceptoIngresoVario) {
		this.listaUsuariosConceptoIngresoVario = listaUsuariosConceptoIngresoVario;
	}


	public List<Rol> getListaRolesConceptoIngresoVario() {
		return listaRolesConceptoIngresoVario;
	}


	public void setListaRolesConceptoIngresoVario(
			List<Rol> listaRolesConceptoIngresoVario) {
		this.listaRolesConceptoIngresoVario = listaRolesConceptoIngresoVario;
	}


	public List<Cuenta> getListaCuentasConceptoIngresoVario() {
		return listaCuentasConceptoIngresoVario;
	}


	public void setListaCuentasConceptoIngresoVario(
			List<Cuenta> listaCuentasConceptoIngresoVario) {
		this.listaCuentasConceptoIngresoVario = listaCuentasConceptoIngresoVario;
	}
	
	private Map<String, ConceptoIngresoVario> mapaConceptosIngresosVarios;
	
	public Map<String, ConceptoIngresoVario> getMapaConceptosIngresosVarios() {
//		if (this.mapaConceptosIngresosVarios == null) {
			this.mapaConceptosIngresosVarios = this.armarMapaConceptosIngresosVarios();
//		}
		return mapaConceptosIngresosVarios;
	}

	public void setMapaConceptosIngresosVarios(Map<String, ConceptoIngresoVario> mapaConceptosIngresosVarios) {
		this.mapaConceptosIngresosVarios = mapaConceptosIngresosVarios;
	}
	
	private Map<String, ConceptoIngresoVario> armarMapaConceptosIngresosVarios() {
		Map<String, ConceptoIngresoVario> locMapa = new HashMap<String, ConceptoIngresoVario>();
		try {
			Usuario usuarioLogueado = getRemoteSystemUsuario().findUsuarioPorLlave(this.getSessionBean1().getLlave());
			
			this.getRemoteSystemAdministracionIngresos().setLlave(this.getSessionBean1().getLlave());
			List<ConceptoIngresoVario> listaResultado = this.getRemoteSystemAdministracionIngresos().findListaConceptoIngresoVarioPorUsuario(usuarioLogueado);
			
			for(ConceptoIngresoVario cadaConcepto : listaResultado){
				locMapa.put(cadaConcepto.getNombre(), cadaConcepto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locMapa;
	}
	
	private ConceptoIngresoVario conceptoSeleccionado;
	
	public ConceptoIngresoVario getConceptoSeleccionado() {
		return conceptoSeleccionado;
	}
	
	public void setConceptoSeleccionado(ConceptoIngresoVario conceptoSeleccionado) {
		this.conceptoSeleccionado = conceptoSeleccionado;
	}
	
	private Map<String, RelaConceptoIngresoVarioCuenta> mapaRelaConceptoIngresoVarioCuenta = new HashMap<String, RelaConceptoIngresoVarioCuenta>();

	public Map<String, RelaConceptoIngresoVarioCuenta> getMapaRelaConceptoIngresoVarioCuenta() {
		return mapaRelaConceptoIngresoVarioCuenta;
	}


	public void setMapaRelaConceptoIngresoVarioCuenta(
			Map<String, RelaConceptoIngresoVarioCuenta> mapaRelaConceptoIngresoVarioCuenta) {
		this.mapaRelaConceptoIngresoVarioCuenta = mapaRelaConceptoIngresoVarioCuenta;
	}
	
}
