///*
// * CommunicationInventarioBean.java
// *
// * Created on 04 de junio de 2008, 10:11
// * Copyright Trascender S.R.L.
// */
//package muni;
//
//import com.sun.rave.web.ui.appbase.AbstractSessionBean;
//import com.sun.rave.web.ui.event.TableSelectPhaseListener;
//import com.trascender.inventario.system.interfaces.SystemStock;
//import com.trascender.inventario.system.interfaces.SystemStockHome;
//import java.util.ArrayList;
//import java.util.Hashtable;
//import java.util.Set;
//import javax.faces.FacesException;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.rmi.PortableRemoteObject;
//
///**
// * <p>Session scope data bean for your application.  Create properties
// *  here to represent cached data that should be made available across
// *  multiple HTTP requests for an individual user.</p>
// *
// * <p>An instance of this class will be created for you automatically,
// * the first time your application evaluates a value binding expression
// * or method binding expression that references a managed bean using
// * this class.</p>
// */
// public class CommunicationInventarioBean extends AbstractSessionBean {
//    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
//    private int __placeholder;
//
//    /**
//     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
//     * This method is automatically generated, so any user-specified code inserted
//     * here is subject to being replaced.</p>
//     */
//    private void _init() throws Exception {
//    }
//    // </editor-fold>
//
//    private Hashtable props = null;
//
//    public Hashtable getProps() {
//        return props;
//    }
//
//    public void setProps(Hashtable props) {
//        this.props = props;
//    }
//
//    /**
//     * <p>Construir una instancia de bean de datos de la sesión.</p>
//     */
//    public CommunicationInventarioBean() {
//        setProps(new Hashtable());
//        getProps().put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
//        getProps().put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
//        getProps().put(Context.PROVIDER_URL, "localhost:1099");
//    }
//
//    /**
//     * <p>Devolver una referencia al bean de datos con ámbito.</p>
//     */
//    protected ApplicationBean1 getApplicationBean1() {
//        return (ApplicationBean1)getBean("ApplicationBean1");
//    }
//
//
//    /**
//     * <p>Se llama a este método al agregar este bean al
//     * ámbito de la sesión.  Normalmente, esto ocurre como resultado de la evaluación
//     * de una expresión de enlace de valores o de métodos, que utiliza la
//     * función de bean administrado para crear una instancia de este bean y almacenarla en el
//     * ámbito de la sesión.</p>
//     *
//     * <p>Puede personalizar este método para inicializar y almacenar en caché los valores
//     * o recursos necesarios para el ciclo de duración de una
//     * sesión de usuario en particular.</p>
//     */
//    public void init() {
//        // Realizar iniciaciones heredadas de la superclase
//        super.init();
//        // Realizar inicio de aplicación que debe finalizar
//        // *antes* de que se inicien los componentes administrados
//        // TODO - Agregar código de inicio propio aquí
//
//        // <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
//        // Iniciar componentes administrados automáticamente
//        // *Nota* - esta lógica NO debe modificarse
//        try {
//            _init();
//        } catch (Exception e) {
//            log("CommunicationInventarioBean Initialization Failure", e);
//            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
//        }
//        // </editor-fold>
//        // Realizar inicio de aplicación que debe finalizar
//        // *después* de que se inicien los componentes administrados
//        // TODO - Agregar código de inicio propio aquí
//        this.tablePhaseListenerStockEnDeposito.keepSelected(true);
//    }
//
//    /**
//     * <p>Se llama a este método cuando la sesión que lo contiene está apunto de
//     * configurarse en modo pasivo.  Normalmente, esto ocurre en un contenedor de servlet distribuido
//     * cuando la sesión está apunto de transferirse a otra
//     * instancia de contenedor, después de la cual se llamará al método <code>activate()</code>
//     * para indicar que la transferencia se ha completado.</p>
//     *
//     * <p>Puede personalizar este método para liberar las referencias a datos
//     * o recursos de sesión que no pueden serializarse con la propia sesión.</p>
//     */
//    public void passivate() {
//    }
//
//    /**
//     * <p>Se llama a este método cuando la sesión que lo contiene se
//     * reactiva.</p>
//     *
//     * <p>Puede personalizar este método para volver a adquirir las referencias a
//     * datos o recursos de la sesión que no pudieron serializarse con la
//     * propia sesión.</p>
//     */
//    public void activate() {
//    }
//
//    /**
//     * <p>Se llama a este método al eliminar este bean del
//     * ámbito de la sesión.  Normalmente, esto ocurre cuando
//     * se supera el tiempo de espera de la sesión o la aplicación la finaliza.</p>
//     *
//     * <p>Puede personalizar este método para limpiar los recursos asignados
//     * durante la ejecución del método <code>init()</code> o
//     * más adelante durante el ciclo de vida de la aplicación.</p>
//     */
//    public void destroy() {
//    }
//
//
//
//    /**
//     * Definición de la interfaz remota SystemAdministracionMesa, para invocar
//     * la lógica de negocio.
//     */
//    // <editor-fold defaultstate="collapsed" desc="SystemAdministracionBienes">
//    private SystemStock  remoteSystemStock = null;
//
//
//    public SystemStock getRemoteSystemStock() {
//        try{
//            if (this.remoteSystemStock==null){
//                Context ctx = new InitialContext(getProps());
//                Object obj = ctx.lookup(SystemStockHome.JNDI_NAME);
//                SystemStockHome locStockHome = (SystemStockHome)
//                PortableRemoteObject.narrow(obj, SystemStockHome.class);
//                this.remoteSystemStock = locStockHome.create();
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return remoteSystemStock;
//    }
//
//    public void setRemoteSystemStock (SystemStock remoteSystemStock) {
//        this.remoteSystemStock = remoteSystemStock;
//    }
//    //</editor-fold>
//    /**
//     * Definición de la interfaz remota SystemAdministracionBienes, para invocar
//     * la lógica de negocio.
//     */
//    // <editor
//
//    /**
//     * Sección de declaración de Listas que completan las listas
//     *
//     */
//
//    // Lista de Depositos
//    //</editor-fold>
//
//    private ArrayList listaDepositos = new ArrayList();
//
//    /**
//     * Getter para propiedad ListaTramites
//     * @return Valor de la propiedad listaTramites.
//     */
//
//    public ArrayList getListaDepositos() {
//        return this.listaDepositos;
//    }
//
//    /**
//     * Setter para propiedad listaDepositos
//     * @param listaDepositos Nuevo valor de la propiedad Depositos.
//     */
//    public void setListaDepositos(ArrayList pListaDepositos) {
//        this.listaDepositos = pListaDepositos;
//    }
//
//    private ArrayList listaStockEnDeposito = null;
//
//    /**
//     * Getter para propiedad ListaStockEnDeposito
//     * @return Valor de la propiedad listaDepositos.
//     */
//
//    public ArrayList getListaStockEnDeposito(){
//        return this.listaStockEnDeposito;
//    }
//
//    /**
//     * Setter para propiedad listaStockEnDeposito
//     * @param listaStockEnDeposito Nuevo valor de la propiedad listaDeposito.
//     */
//    public void setListaStockEnDeposito(ArrayList pListaStockEnDeposito){
//        this.listaStockEnDeposito = pListaStockEnDeposito;
//    }
//
//    //Lista de Stock
//    //</editor-fold>
//    private ArrayList listaStocks = null;
//
//    /**
//     * Getter para propiedad ListaStock
//     * @return Valor de la propiedad listaStock.
//     */
//    public ArrayList getListaStocks(){
//        return this.listaStocks;
//    }
//
//    /**
//     * Setter para propiedad listaTramites
//     * @param listaBienesProvistos Nuevo valor de la propiedad listaTramites.
//     */
//    public void setListaStocks(ArrayList pListaStocks){
//        this.listaStocks = pListaStocks;
//    }
//
//    //Lista de MovimientosDeMercaderia
//    //</editor-fold>
//    private ArrayList listaMovimientosDeMercaderia = null;
//
//    /**
//     * Getter para propiedad ListaStock
//     * @return Valor de la propiedad listaStock.
//     */
//    public ArrayList getListaMovimientosDeMercaderia(){
//        return this.listaMovimientosDeMercaderia;
//    }
//
//    /**
//     * Setter para propiedad listaTramites
//     * @param listaBienesProvistos Nuevo valor de la propiedad listaTramites.
//     */
//    public void setListaMovimientosDeMercaderia(ArrayList pListaMovimientosDeMercaderia){
//        this.listaMovimientosDeMercaderia = pListaMovimientosDeMercaderia;
//    }
//
//    private TableSelectPhaseListener tablePhaseListenerStockEnDeposito = new TableSelectPhaseListener();
//
//    public TableSelectPhaseListener getTablePhaseListenerStockEnDeposito() {
//        return this.tablePhaseListenerStockEnDeposito;
//    }
//    public void setTablePhaseListenerStockEnDeposito(TableSelectPhaseListener tablePhaseListenerStockEnDeposito) {
//        this.tablePhaseListenerStockEnDeposito = tablePhaseListenerStockEnDeposito;
//    }
//
//}
