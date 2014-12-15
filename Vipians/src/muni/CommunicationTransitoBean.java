/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni;

import javax.rmi.PortableRemoteObject;
import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoAutomotor;
import com.trascender.habilitaciones.system.interfaces.SystemTransito;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author tincho
 */
public class CommunicationTransitoBean extends AbstractSessionBean {

//    @EJB
    private SystemTransito remoteSystemTransito = null;
    private SystemDocumentoAutomotor remoteSystemAutomotor = null;
    Properties props = null;
    Context ctx = null;
    private int __placeholder;

    public int getPlaceholder() {
        return __placeholder;
    }

    public void setPlaceholder(int __placeholder) {
        this.__placeholder = __placeholder;
    }

    public CommunicationTransitoBean() {
        props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        props.put(Context.PROVIDER_URL, "localhost:1099");
        try {
            ctx = new InitialContext(props);
            this.remoteSystemTransito = (SystemTransito) ctx.lookup(SystemTransito.JNDI_NAME);
            this.remoteSystemAutomotor = (SystemDocumentoAutomotor) ctx.lookup(SystemDocumentoAutomotor.JNDI_NAME);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public SystemTransito getRemoteSystemTransito() {

//        try {
//            if (this.remoteSystemTransito == null) {
//                Context ctx = new InitialContext(props);
//                Object obj = ctx.lookup(SystemSystemTransitoHome.JNDI_NAME);
//                SystemTransitoHome locTransitoHome = (SystemTransitoHome) PortableRemoteObject.narrow(obj, SystemTransitoHome.class);
//                this.remoteSystemTransito = locTransitoHome.create();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return this.remoteSystemTransito;
    }

    public void setRemoteSystemTransito(SystemTransito remoteSystemTransito) {
        this.remoteSystemTransito = remoteSystemTransito;
    }

    public SystemDocumentoAutomotor getRemoteSystemAutomotor() {

//        try {
//            if (this.remoteSystemTransito == null) {
//                Context ctx = new InitialContext(props);
//                Object obj = ctx.lookup(SystemSystemTransitoHome.JNDI_NAME);
//                SystemTransitoHome locTransitoHome = (SystemTransitoHome) PortableRemoteObject.narrow(obj, SystemTransitoHome.class);
//                this.remoteSystemTransito = locTransitoHome.create();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return this.remoteSystemAutomotor;
    }

    public void setRemoteSystemAutomotor(SystemDocumentoAutomotor remoteSystemAutomotor) {
        this.remoteSystemAutomotor = remoteSystemAutomotor;
    }
    
    private ArrayList listaVehiculos = null;

    public ArrayList getListaVehiculos() {
        return this.listaVehiculos;
    }

    public void setListaVehiculos(ArrayList pListaVehiculos) {
        this.listaVehiculos = pListaVehiculos;
    }
    private ArrayList listaAtributosDinamicosVehiculos = null;

    public ArrayList getListaAtributosDinamicosVehiculos() {
        return this.listaAtributosDinamicosVehiculos;
    }

    public void setListaAtributosDinamicosVehiculos(ArrayList pListaAtributosDinamicosVehiculos) {
        this.listaAtributosDinamicosVehiculos = pListaAtributosDinamicosVehiculos;
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
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Se llama a este método al agregar este bean al ámbito de la sesión.
     * Normalmente, esto ocurre como resultado de la evaluación de una expresión
     * de enlace de valores o de métodos, que utiliza la función de bean
     * administrado para crear una instancia de este bean y almacenarla en el
     * ámbito de la sesión.</p>
     *
     * <p>Puede personalizar este método para inicializar y almacenar en caché
     * los valores o recursos necesarios para el ciclo de duración de una sesión
     * de usuario en particular.</p>
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
            log("CommunicationTransito Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicación que debe finalizar
        // *después* de que se inicien los componentes administrados
        // TODO - Agregar código de inicio propio aquí

    }

    /**
     * <p>Se llama a este método cuando la sesión que lo contiene está apunto de
     * configurarse en modo pasivo. Normalmente, esto ocurre en un contenedor de
     * servlet distribuido cuando la sesión está apunto de transferirse a otra
     * instancia de contenedor, después de la cual se llamará al método
     * <code>activate()</code> para indicar que la transferencia se ha
     * completado.</p>
     *
     * <p>Puede personalizar este método para liberar las referencias a datos o
     * recursos de sesión que no pueden serializarse con la propia sesión.</p>
     */
    public void passivate() {
    }

    /**
     * <p>Se llama a este método cuando la sesión que lo contiene se
     * reactiva.</p>
     *
     * <p>Puede personalizar este método para volver a adquirir las referencias
     * a datos o recursos de la sesión que no pudieron serializarse con la
     * propia sesión.</p>
     */
    public void activate() {
    }

    /**
     * <p>Se llama a este método al eliminar este bean del ámbito de la sesión.
     * Normalmente, esto ocurre cuando se supera el tiempo de espera de la
     * sesión o la aplicación la finaliza.</p>
     *
     * <p>Puede personalizar este método para limpiar los recursos asignados
     * durante la ejecución del método
     * <code>init()</code> o más adelante durante el ciclo de vida de la
     * aplicación.</p>
     */
    public void destroy() {
    }
}
