/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.accionSocial.recurso.filtros.FiltroFichaSocial;
import com.trascender.accionSocial.system.interfaces.SystemFichaSocial;
import com.trascender.presentacion.abstracts.PaginatedTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author tincho
 */
public class CommunicationAccionSocialBean extends AbstractSessionBean {

//    @EJB
    private SystemFichaSocial remoteSystemFichaSocial = null;
    Properties props = null;
    Context ctx = null;
    
    private int __placeholder;
    private List listaObraSociales = null;
    private ArrayList listaGrupoFamiliar = null;
    private ArrayList listaSolicitudesBeneficio = null;
    private List listaFichaSociales = null;
    private List listaBeneficios = null;

    public int getPlaceholder() {
        return __placeholder;
    }

    public void setPlaceholder(int __placeholder) {
        this.__placeholder = __placeholder;
    }
   
    public ArrayList getListaGrupoFamiliar() {
        return this.listaGrupoFamiliar;
    }

    public void setListaGrupoFamiliar(ArrayList listaGrupoFamiliar) {
        this.listaGrupoFamiliar = listaGrupoFamiliar;
    }
    
    public ArrayList getListaSolicitudesBeneficio() {
        return this.listaSolicitudesBeneficio;
    }

    public void setListaSolicitudesBeneficio(ArrayList listaSolicitudesBeneficio) {
        this.listaSolicitudesBeneficio = listaSolicitudesBeneficio;
    }

    public List getListaBeneficios() {
        return this.listaBeneficios;
    }

    public void setListaBeneficios(List listaBeneficios) {
        this.listaBeneficios = listaBeneficios;
    }

    public List getListaFichaSociales() {
        return this.listaFichaSociales;
    }

    public void setListaFichaSociales(List listaFichaSociales) {
        this.listaFichaSociales = listaFichaSociales;
    }

    public List getListaObraSociales() {
        return this.listaObraSociales;
    }

    public void setListaObraSociales(List listaObraSociales) {
        this.listaObraSociales = listaObraSociales;
    }

    public CommunicationAccionSocialBean() {
        props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        props.put(Context.PROVIDER_URL, "localhost:1099");
        try {
            ctx= new InitialContext(props);
            this.remoteSystemFichaSocial = (SystemFichaSocial)ctx.lookup(SystemFichaSocial.JNDI_NAME);                        
        } catch (Exception ex) {
          //  Logger.getLogger(ComunicationBean.class.getName()).log(Level.SEVERE, null, ex);
          ex.printStackTrace();
        }
    }

    public SystemFichaSocial getRemoteSystemFichaSocial() {

//        try {
//            if (this.remoteSystemFichaSocial == null) {
//                Context ctx = new InitialContext(props);
//                Object obj = ctx.lookup(SystemFichaSocialHome.JNDI_NAME);
//                SystemFichaSocialHome locFichaSocialHome = (SystemFichaSocialHome) PortableRemoteObject.narrow(obj, SystemFichaSocialHome.class);
//                this.remoteSystemFichaSocial = locFichaSocialHome.create();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return this.remoteSystemFichaSocial;
    }

   
    public void setRemoteSystemFichaSocial(SystemFichaSocial remoteSystemFichaSocial) {
        this.remoteSystemFichaSocial = remoteSystemFichaSocial;
    }
  
    
    
    
    /**
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }
    /** 
     * <p>Devolver una referencia al bean de datos con ámbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1)getBean("RequestBean1");
    }
    
    
    
    private TableSelectPhaseListener tablePhaseListenerBeneficio = new TableSelectPhaseListener();

    /**
     * Getter para propiedad tablePhaseListenerCuadras.
     * @return Valor de la propiedad tablePhaseListenerCuadras.
     */
    public TableSelectPhaseListener getTablePhaseListenerBeneficio() {

        return this.tablePhaseListenerBeneficio;
    }

    /**
     * Setter para propiedad tablePhaseListenerCuadras.
     * @param tablePhaseListenerCuadras Nuevo valor de la propiedad tablePhaseListenerCuadras.
     */
    public void setTablePhaseListenerBeneficio(TableSelectPhaseListener tablePhaseListenerBeneficio) {

        this.tablePhaseListenerBeneficio = tablePhaseListenerBeneficio;
    }
    
    private PaginatedTable tableObraSocial;

    public PaginatedTable getTableObraSocial() {
        return tableObraSocial;
    }

    public void setTableObraSocial(PaginatedTable tableObraSocial) {
        this.tableObraSocial = tableObraSocial;
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
    private void _init() throws Exception {
    }

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
    public void destroy() {
    }
}
