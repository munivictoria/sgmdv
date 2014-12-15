/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.accionsocial.ABMBeneficiario;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.accionSocial.recurso.persistent.Beneficiario;
import com.trascender.accionSocial.recurso.persistent.Beneficiario;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.accionSocial.recurso.persistent.ObraSocial;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ModificarBeneficiario extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">

    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        
        Option[] opV = null;
        Option[] opI = null;
        
        opV = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Beneficiario.Vinculo.values(), "may");
        ddVinculoDefaultOptions.setOptions(opV);
        
        opI = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Beneficiario.Instruccion.values(), "may");
        ddInstruccionDefaultOptions.setOptions(opI);
              
    }
    private Page page1 = new Page();
    
    public Page getPage1() {
        return page1;
    }
    
    public void setPage1(Page p) {
        this.page1 = p;
    }
    private Html html1 = new Html();
    
    public Html getHtml1() {
        return html1;
    }
    
    public void setHtml1(Html h) {
        this.html1 = h;
    }
    private Head head1 = new Head();
    
    public Head getHead1() {
        return head1;
    }
    
    public void setHead1(Head h) {
        this.head1 = h;
    }
    private Link link1 = new Link();
    
    public Link getLink1() {
        return link1;
    }
    
    public void setLink1(Link l) {
        this.link1 = l;
    }
    private Body body1 = new Body();
    
    public Body getBody1() {
        return body1;
    }
    
    public void setBody1(Body b) {
        this.body1 = b;
    }
    private Form form1 = new Form();
    
    public Form getForm1() {
        return form1;
    }
    
    public void setForm1(Form f) {
        this.form1 = f;
    }
    private StaticText stTitulo = new StaticText();
    
    public StaticText getStTitulo() {
        return stTitulo;
    }
    
    public void setStTitulo(StaticText st) {
        this.stTitulo = st;
    }
    private Button btnGuardar = new Button();
    
    public Button getBtnGuardar() {
        return btnGuardar;
    }
    
    public void setBtnGuardar(Button b) {
        this.btnGuardar = b;
    }
    private Button btnCancelar = new Button();
    
    public Button getBtnCancelar() {
        return btnCancelar;
    }
    
    public void setBtnCancelar(Button b) {
        this.btnCancelar = b;
    }
    private StaticText stSeparador = new StaticText();
    
    public StaticText getStSeparador() {
        return stSeparador;
    }
    
    public void setStSeparador(StaticText st) {
        this.stSeparador = st;
    }
    private MessageGroup messageGroup1 = new MessageGroup();
    
    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }
    
    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
    private HiddenField hidIdPagina = new HiddenField();
    
    public HiddenField getHidIdPagina() {
        return hidIdPagina;
    }
    
    public void setHidIdPagina(HiddenField hf) {
        this.hidIdPagina = hf;
    }
    private HiddenField hidIdSubSesion = new HiddenField();
    
    public HiddenField getHidIdSubSesion() {
        return hidIdSubSesion;
    }
    
    public void setHidIdSubSesion(HiddenField hf) {
        this.hidIdSubSesion = hf;
    }
    
    private Script scriptValidador = new Script();

    public Script getScriptValidador() {
        return scriptValidador;
    }

    public void setScriptValidador(Script scriptValidador) {
        this.scriptValidador = scriptValidador;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Vincular a los campos ocultos.
    private Long idPagina = null;
    private Long idSubSesion = null;
    
    public Long getIdPagina() {
        return idPagina;
    }
    
    public void setIdPagina(Long idPagina) {
        this.idPagina = idPagina;
    }
    
    public Long getIdSubSesion() {
        return idSubSesion;
    }
    
    public void setIdSubSesion(Long idSubSesion) {
        this.idSubSesion = idSubSesion;
    }
    
    public ElementoPila getElementoPila() {
        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    // CAMBIAR: Constantes que varian segun la pagina.
    // cantidad de objetos administrados por la pagina
    //private final int CANTIDAD_OBJETOS = 2;
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "ModificarBeneficiario";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarBeneficiario";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    private Label label1 = new Label();
    
    public Label getLabel1() {
        return label1;
    }
    
    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private DropDown ddVinculo = new DropDown();
    
    public DropDown getDdVinculo() {
        return ddVinculo;
    }
    
    public void setDdVinculo(DropDown ddVinculo) {
        this.ddVinculo = ddVinculo;
    }
    private SingleSelectOptionsList ddVinculoDefaultOptions = new SingleSelectOptionsList();
    
    public SingleSelectOptionsList getDdVinculoDefaultOptions() {
        return ddVinculoDefaultOptions;
    }
    
    public void setDdVinculoDefaultOptions(SingleSelectOptionsList ddVinculoDefaultOptions) {
        this.ddVinculoDefaultOptions = ddVinculoDefaultOptions;
    }    
    private Label label2 = new Label();
    
    public Label getLabel2() {
        return label2;
    }
    
    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private Label label3 = new Label();
    
    public Label getLabel3() {
        return label3;
    }
    
    public void setLabel3(Label label3) {
        this.label3 = label3;
    }
    private Label label4 = new Label();
    
    public Label getLabel4() {
        return label4;
    }
    
    public void setLabel4(Label l) {
        this.label4 = l;
    }
    private Label label5 = new Label();
    
    public Label getLabel5() {
        return label5;
    }
    
    public void setLabel5(Label l) {
        this.label5 = l;
    }
    private Label label6 = new Label();
    
    public Label getLabel6() {
        return label6;
    }
    
    public void setLabel6(Label l) {
        this.label6 = l;
    }    
    private Label label7 = new Label();
    
    public Label getLabel7() {
        return label7;
    }
    
    public void setLabel7(Label l) {
        this.label7 = l;
    }    
    private TextField tfOcupacion = new TextField();
    
    public TextField getTfOcupacion() {
        return tfOcupacion;
    }
    
    public void setTfOcupacion(TextField tfOcupacion) {
        this.tfOcupacion = tfOcupacion;
    }
    private Label label8 = new Label();
    
    public Label getLabel8() {
        return label8;
    }
    
    public void setLabel8(Label l) {
        this.label8 = l;
    }    
    private Label label9 = new Label();
    
    public Label getLabel9() {
        return label9;
    }
    
    public void setLabel9(Label l) {
        this.label9 = l;
    }
    private TextField tfIngresos = new TextField();
    
    public TextField getTfIngresos() {
        return tfIngresos;
    }
    
    public void setTfIngresos(TextField tfIngresos) {
        this.tfIngresos = tfIngresos;
    }    
    private TextField tfSalud = new TextField();
    
    public TextField getTfSalud() {
        return tfSalud;
    }
    
    public void setTfSalud(TextField tfSalud) {
        this.tfSalud = tfSalud;
    }
    private DropDown ddInstruccion = new DropDown();
    
    public DropDown getDdInstruccion() {
        return ddInstruccion;
    }
    
    public void setDdInstruccion(DropDown ddInstruccion) {
        this.ddInstruccion = ddInstruccion;
    }
    private SingleSelectOptionsList ddInstruccionDefaultOptions = new SingleSelectOptionsList();
    
    public SingleSelectOptionsList getDdInstruccionDefaultOptions() {
        return ddInstruccionDefaultOptions;
    }
    
    public void setDdInstruccionDefaultOptions(SingleSelectOptionsList ddInstruccionDefaultOptions) {
        this.ddInstruccionDefaultOptions = ddInstruccionDefaultOptions;
    }
    private TextField tfObraSocial = new TextField();
    
    public TextField getTfObraSocial() {
        return tfObraSocial;
    }
    
    public void setTfObraSocial(TextField tfObraSocial) {
        this.tfObraSocial = tfObraSocial;
    }
    
    private TextField tfPersona = new TextField();
    
    public TextField getTfPersona() {
        return tfPersona;
    }
    
    public void setTfPersona(TextField tfPersona) {
        this.tfPersona = tfPersona;
    }
    private Button btnSeleccionarPersonaFisica = new Button();
    
    public Button getBtnSeleccionarPersonaFisica() {
        return btnSeleccionarPersonaFisica;
    }
    
    public void setBtnSeleccionarPersonaFisica(Button btnSeleccionarPersonaFisica) {
        this.btnSeleccionarPersonaFisica = btnSeleccionarPersonaFisica;
    }
    private Button btnSeleccionarPersonaJuridica = new Button();
    
    public Button getBtnSeleccionarPersonaJuridica() {
        return btnSeleccionarPersonaJuridica;
    }
    
    public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
        this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
    }
    private Button btnLimpiarPersona = new Button();
    
    public Button getBtnLimpiarPersona() {
        return btnLimpiarPersona;
    }
    
    public void setBtnLimpiarPersona(Button btnLimpiarPersona) {
        this.btnLimpiarPersona = btnLimpiarPersona;
    }
    private Button btnSeleccionarObraSocial = new Button();
    
    public Button getBtnSeleccionarObraSocial() {
        return btnSeleccionarObraSocial;
    }
    
    public void setBtnSeleccionarObraSocial(Button btnSeleccionarObraSocial) {
        this.btnSeleccionarObraSocial = btnSeleccionarObraSocial;
    }
    private Button btnLimpiarObraSocial = new Button();
    
    public Button getBtnLimpiarObraSocial() {
        return btnLimpiarObraSocial;
    }
    
    public void setBtnLimpiarObraSocial(Button btnLimpiarObraSocial) {
        this.btnLimpiarObraSocial = btnLimpiarObraSocial;
    }
    
    private ObjectListDataProvider ldpGrupoFamiliar = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpGrupoFamiliar() {
        return ldpGrupoFamiliar;
    }

    public void setLdpGrupoFamiliar(ObjectListDataProvider ldpGrupoFamiliar) {
        this.ldpGrupoFamiliar = ldpGrupoFamiliar;
    }

    // </editor-fold>
    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ModificarBeneficiario() {
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
        return (muni.CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationCajaBean getCommunicationCajaBean() {
        return (muni.CommunicationCajaBean) getBean("CommunicationCajaBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationComprasBean getCommunicationComprasBean() {
        return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationSAICBean getCommunicationSAICBean() {
        return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
        return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.CommunicationAccionSocialBean getCommunicationAccionSocialBean() {
        return (muni.CommunicationAccionSocialBean) getBean("CommunicationAccionSocialBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ApplicationBean1 getApplicationBean1() {
        return (muni.ApplicationBean1) getBean("ApplicationBean1");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.ComunicationBean getComunicationBean() {
        return (muni.ComunicationBean) getBean("ComunicationBean");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.SessionBean1 getSessionBean1() {
        return (muni.SessionBean1) getBean("SessionBean1");
    }

    /** 
     * <p>Devolver una referencia al bean de datos con �mbito.</p>
     */
    protected muni.RequestBean1 getRequestBean1() {
        return (muni.RequestBean1) getBean("RequestBean1");
    }

    /** 
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina,
     * ya sea directamente mediante un URL o de manera indirecta a trav�s de la navegaci�n de p�ginas.
     * Puede personalizar este m�todo para adquirir recursos que se necesitar�n
     * para los controladores de eventos y m�todos del proceso, sin tener en cuenta si esta
     * p�gina realiza procesamiento de devoluci�n de env�os.</p>
     * 
     * <p>Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores
     * de propiedad de los componentes <strong>no</strong> representan ning�n
     * valor enviado con esta petici�n.  En su lugar, representan los
     * valores de propiedades que se guardaron para esta vista cuando se proces�.</p>
     */
    public void init() {
        // Realizar iniciaciones heredadas de la superclase
        super.init();
        // Realizar inicio de aplicaci�n que debe finalizar
        // *antes* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

        // <editor-fold defaultstate="collapsed" desc="Inicio de componente administrado por el programa">
        // Iniciar componentes administrados autom�ticamente
        // *Nota* - esta l�gica NO debe modificarse
        try {
            _init();
        } catch (Exception e) {
            log("ModificarBeneficiario Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        // </editor-fold>
        // Realizar inicio de aplicaci�n que debe finalizar
        // *despu�s* de que se inicien los componentes administrados
        // TODO - Agregar c�digo de inicio propio aqu�

    }

    /** 
     * <p>Metodo de devolucion de llamada al que se llama cuando el arbol de componentes se ha
     * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este metodo
     * <strong>solo</strong> se llamara en una peticion de devolucion de envio que
     * esta procesando el envio de un formulario.  Puede personalizar este m�todo para asignar
     * recursos necesarios para los controladores de eventos.</p>
     */
    public void preprocess() {
    }

    /** 
     * <p>M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento.
     * <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que
     * se procesa, no se llamar�, por ejemplo, en una p�gina que
     * ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina.  Puede personalizar
     * este m�todo para asignar recursos necesarios para procesar
     * esta p�gina.</p>
     */
    public void prerender() {
        boolean existeIdSubSesionReq = false;
        boolean existeIdPaginaReq = false;
        boolean existeIdSubSesionPag = false;
        boolean existeIdPaginaPag = false;
        boolean recarga = false;
        
        if (this.getRequestBean1() != null) {
            existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
            existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
        }
        
        this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
        this.setIdPagina((Long) this.getHidIdPagina().getText());
        
        existeIdSubSesionPag = this.getIdSubSesion() != null;
        existeIdPaginaPag = this.getIdPagina() != null;

        // Pagina nueva - Inicio de transaccion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            if (this.PUEDE_SER_PAGINA_INICIAL) {
                Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
                this.setIdPagina(key);
                this.setIdSubSesion(key);
                this.crearElementoPila();
            }
        }

        // Recarga de la misma pagina por validacion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
            // no se hace nada por ahora
            recarga = true;
            // APLICAR LOGICA AQUI.. ver si es as� realmente..
        }

        // Pagina nueva - hacia adelante en la transaccion
        // Para el caso de las paginas de inicio de transaccion nunca entra
        if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
            this.setIdPagina(key);
            this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
            this.crearElementoPila();
        }

        // Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
            ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
            this.setIdPagina(ep.getIdPagina());
            this.setIdSubSesion(ep.getIdSubSesion());
        }
        
        if (!recarga) {
            this.mostrarEstadoObjetosUsados();
        }
        
        this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
        
    }

    /** 
     * <p>M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de
     * esta petici�n, si se llam� al m�todo <code>init()</code> (sin tener en cuenta
     * si se trata de la p�gina que se ha procesado o no).  Puede personalizar este
     * m�todo para liberar los recursos adquiridos en los m�todos <code>init()</code>,
     * <code>preprocess()</code> o <code>prerender()</code> (o
     * durante la ejecuci�n de un controlador de eventos).</p>
     */
    public void destroy() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new Beneficiario()); //0
        ep.getObjetos().add(ind++, null); // Persona F o J //1
        ep.getObjetos().add(ind++, new ObraSocial());  //2
        ep.getObjetos().add(ind++, new String()); //para almacenar el tipo de seleccion, si es Titular o Familiar. //3

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    private void guardarEstadoObjetosUsados() {
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        
        Beneficiario beneficiario = (Beneficiario) this.obtenerObjetoDelElementoPila(0, Beneficiario.class);
        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);
        ObraSocial obraSocial = (ObraSocial) this.obtenerObjetoDelElementoPila(2, ObraSocial.class);
        String tipoSeleccion = (String) this.obtenerObjetoDelElementoPila(3, String.class);
        
        Beneficiario.Vinculo vinculoBeneficiario = null;
        Beneficiario.Instruccion instruccionBeneficiario = null;
        
        Object vinculoSelected = this.getDdVinculo().getSelected();
        Object instruccionSelected = this.getDdInstruccion().getSelected();
        Object ocupacion = this.getTfOcupacion().getText();
        Object ingresos = this.getTfIngresos().getText();        
        Object salud = this.getTfSalud().getText();

        //Validador v = new Validador();

//        if (monto != null && !monto.toString().equals("") && v.esFlotante(monto.toString())) {
//            beneficiario.Monto(Double.valueOf(Double.parseDouble(monto.toString())));
//        }

        if ((vinculoSelected != null) && (vinculoSelected.toString().length() > 0) && tipoSeleccion == "FAMILIAR") {
            vinculoBeneficiario = Beneficiario.Vinculo.valueOf(vinculoSelected.toString());
        } else {
            vinculoBeneficiario = null;
        }
        beneficiario.setVinculo(vinculoBeneficiario);
        
        if ((instruccionSelected != null) && (instruccionSelected.toString().length() > 0)) {
            instruccionBeneficiario = Beneficiario.Instruccion.valueOf(instruccionSelected.toString());
        } else {
            instruccionBeneficiario = null;
        }
        beneficiario.setInstruccion(instruccionBeneficiario);
        
        if (ocupacion != null && ocupacion != "") {
            beneficiario.setOcupacion(ocupacion.toString());
        } else {
            beneficiario.setOcupacion(null);
        }
        
        if (ingresos != null && ingresos != "") {
            beneficiario.setIngresos(Conversor.getDoubleDeString(ingresos.toString()));
        } else {
            beneficiario.setIngresos(null);
        }
        
        if (salud != null && salud != "") {
            beneficiario.setSalud((salud.toString()));
        } else {
            beneficiario.setSalud(null);
        }
        
        if (persona != null && persona.getIdPersona() != -1) {
            beneficiario.setPersona(persona);
        } else {
            beneficiario.setPersona(null);
        }
        
        if (obraSocial != null && obraSocial.getIdObraSocial() != -1) {
            beneficiario.setObraSocial(obraSocial);
        } else {
            beneficiario.setObraSocial(null);
        }
        
        int ind = 0;
        this.getElementoPila().getObjetos().set(ind++, beneficiario);
        this.getElementoPila().getObjetos().set(ind++, persona);
        this.getElementoPila().getObjetos().set(ind++, obraSocial);
    }
    
    private void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        
        Beneficiario beneficiario = null;
        Persona persona = null;
        ObraSocial obraSocial = null;
        Beneficiario.Vinculo vinculoBeneficiario = null;
        Beneficiario.Instruccion instruccionBeneficiario = null;
        String ocupacion = null;
        Double ingresos = null;
        String tipoSeleccion = null;
        String locTipoSeleccion = (String) this.obtenerObjetoDelElementoPila(3, String.class);
        
        if (this.getRequestBean1().getTipoSeleccion() != null) {
            tipoSeleccion = this.getRequestBean1().getTipoSeleccion();
             if (tipoSeleccion == "TITULAR"){
                this.getDdVinculo().setDisabled(true);
            } else if (tipoSeleccion == "FAMILIAR"){
                this.getDdVinculo().setDisabled(false);
            }
            this.getElementoPila().getObjetos().set(3, tipoSeleccion);            
        }else if (locTipoSeleccion == "TITULAR"){
             this.getDdVinculo().setDisabled(true);        
        }
        
//        Beneficiario beneficiario = (Beneficiario) this.obtenerObjetoDelElementoPila(0, Beneficiario.class);
//        Persona persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);
//        ObraSocial obraSocial = (ObraSocial) this.obtenerObjetoDelElementoPila(2, ObraSocial.class);

        // CAMBIAR: Obtener datos del Request y asignarlos a los textField
        if (this.getRequestBean1().getObjetoABM() != null) {
            beneficiario = (Beneficiario) this.getRequestBean1().getObjetoABM();
            persona = (Persona) beneficiario.getPersona();
            obraSocial = (ObraSocial) beneficiario.getObraSocial();
            this.getElementoPila().getObjetos().set(0, beneficiario);
            this.getElementoPila().getObjetos().set(1, persona);
            this.getElementoPila().getObjetos().set(2, obraSocial);
        }

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
            
            if (seleccionado instanceof Persona) {
                persona = (Persona) seleccionado;
                this.getElementoPila().getObjetos().set(1, persona);
            } else if (seleccionado instanceof ObraSocial) {
                obraSocial = (ObraSocial) seleccionado;
                this.getElementoPila().getObjetos().set(2, obraSocial);
            }
            // CAMBIAR: Crear los if necesarios para cada posible objeto a seleccionar.
        }
//
//        if (this.getElementoPila().getObjetos() != null && !this.getElementoPila().getObjetos().isEmpty()) {
//            // CAMBIAR:
//            Beneficiario = (Beneficiario) this.obtenerObjetoDelElementoPila(0, Beneficiario.class);
//
//        }
        int ind = 0;
        beneficiario = (Beneficiario) this.obtenerObjetoDelElementoPila(ind++, Beneficiario.class);
        persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
        obraSocial = (ObraSocial) this.obtenerObjetoDelElementoPila(ind++, ObraSocial.class);
        
        if (persona != null && persona.getIdPersona() != -1) {
            this.getTfPersona().setText(persona.toString());
        }
        if (obraSocial != null && obraSocial.getIdObraSocial() != -1) {
            this.getTfObraSocial().setText(obraSocial.getNombre());
        }        
        this.getDdVinculo().setSelected(Util.getEnumNameFromString(String.valueOf(beneficiario.getVinculo())));
        this.getDdVinculoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(beneficiario.getVinculo())));
        
        this.getDdInstruccion().setSelected(Util.getEnumNameFromString(String.valueOf(beneficiario.getInstruccion())));
        this.getDdInstruccionDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(beneficiario.getInstruccion())));
        
        this.getTfOcupacion().setText(beneficiario.getOcupacion());
        
        if (beneficiario.getIngresos() != null) {
            this.getTfIngresos().setText(beneficiario.getIngresos().toString());
        }
        this.getTfSalud().setText(beneficiario.getSalud());
        
    }
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Metodos estaticos de la pagina">
    private void crearElementoPila() {
        ElementoPila ep = new ElementoPila();
        ep.setCasoNavegacion(CASO_NAVEGACION);
        ep.setIdPagina(this.getIdPagina());
        ep.setIdSubSesion(this.getIdSubSesion());
        ep.setNombrePagina(NOMBRE_PAGINA);
        
        ep = this.agregarObjetosAElementoPila(ep);
        
        this.getSessionBean1().getMgrPilas().addElemento(ep);
    }
    
    private String prepararParaVolver(String pAccionRetorno) {
        this.getRequestBean1().setAccion(pAccionRetorno);
        String retorno = null;
        ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
        if (locElementoAnterior != null) {
            this.getSessionBean1().getMgrPilas().removeElemento(this.getElementoPila());
            this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
            this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
            this.getRequestBean1().setElementoPilaPaginaAnt(locElementoAnterior);
            retorno = locElementoAnterior.getCasoNavegacion();            
        } else {
            retorno = CASO_NAV_POST_CADUCIDAD;
        }
        return retorno;
    }
    
    private String prepararCaducidad() {
        // redireccionar a pagina con mensaje de caducidad
        this.getRequestBean1().setCasoNavegacionPostCaducidad(CASO_NAV_POST_CADUCIDAD);
        return CASO_NAV_CADUCIDAD;
    }
    
    private boolean ultimoElementoPilaDeSubSesion() {
        return this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
    }
    
    private Object obtenerObjetoDelElementoPila(int posicion, Class tipoClase) {
        Object objeto = null;
        try {
            objeto = this.getElementoPila().getObjetos().get(posicion);
            if (objeto == null) {
                objeto = tipoClase.newInstance();
            }
        } catch (Exception ex) {
        }
        return objeto;
    }
    
    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo != null) {
                campo.setText(null);
            }
        } catch (Exception ex) {
        }
    }
    
    private int getCantidadObjetosUsados() {
        return this.getElementoPila().getObjetos().size();
    }
    // </editor-fold>

    public String btnGuardar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            try {             
                
//          CAMBIAR: Validar los campos necesarios.
                Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[4];
                String[] nomNoVacios = new String[4];
                UIComponent[] flotantes = new UIComponent[1];
                String[] nomflotantes = new String[1];

                int pos = 0;
                
                noVacios[pos] = this.getTfPersona();
                nomNoVacios[pos++] = "Persona";
                noVacios[pos] = this.getDdInstruccion();
                nomNoVacios[pos++] = "Instrucci\363n";
                noVacios[pos] = this.getTfOcupacion();
                nomNoVacios[pos++] = "Ocupaci\363n";
//                noVacios[pos] = this.getTfIngresos();
//                nomNoVacios[pos++] = "Ingresos";
                noVacios[pos] = this.getTfSalud();
                nomNoVacios[pos++] = "Salud";
                
                pos = 0;
                flotantes[pos] = this.getTfIngresos();
                nomflotantes[pos++] = "Ingresos";
               
                v.noSonVacios(noVacios, nomNoVacios);
                v.sonFlotantes(flotantes, nomflotantes);
                
                if (v.getErrores().size() > 0) {
                    error("Existen Errores:");
                    for (int i = 0; i < v.getErrores().size(); i++) {
                        warn(v.getErrores().toArray()[i].toString());
                    }
                    return null;
                }

                this.guardarEstadoObjetosUsados();
                // CAMBIAR:
                Beneficiario beneficiario = (Beneficiario) this.obtenerObjetoDelElementoPila(0, Beneficiario.class);
                String tipoSeleccion = (String) this.obtenerObjetoDelElementoPila(3, String.class);
                
                this.getRequestBean1().setObjetoSeleccion(beneficiario);
                this.getRequestBean1().setTipoSeleccion(tipoSeleccion);
                
                info("El Beneficiario se modific\363 exitosamente.");
                retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
            } catch (Exception ex) {
                if (ex instanceof TrascenderException) {
                    if (((TrascenderException) ex).getCodeTrascenderException() < 2300) {
                        retorno = null;
                    } else {
                        retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
                    }
                }
                log(CASO_NAVEGACION + "_GuardarError:", ex);
                error(NOMBRE_PAGINA + " - Guardar: " + ex.getMessage());
            }
            
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public String btnCancelar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        String tipoSeleccion = (String) this.obtenerObjetoDelElementoPila(3, String.class);
        
        if (ultimo) {
            if (tipoSeleccion == "FAMILIAR"){
                 Beneficiario beneficiario = (Beneficiario) this.obtenerObjetoDelElementoPila(0, Beneficiario.class);
                 this.getRequestBean1().setObjetoSeleccion(beneficiario);
                 this.getRequestBean1().setTipoSeleccion(tipoSeleccion);
            }
            retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public String btnSeleccionarPersonaFisica_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        String tipoSeleccion = (String)this.obtenerObjetoDelElementoPila(3, String.class);

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 3;
        
        if (ultimo) {
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion(tipoSeleccion);
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPersonaFisica";
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }
    
    public String btnSeleccionarPersonaJuridica_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        String tipoSeleccion = (String)this.obtenerObjetoDelElementoPila(3, String.class);

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 3;
        
        if (ultimo) {
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setTipoSeleccion(tipoSeleccion);
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminPersonaJuridica";
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }
    
    public String btnSeleccionarObraSocial_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 3;
        
        if (ultimo) {
            
            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminObraSocial";
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }
    
    public String btnLimpiarPersona_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(1, this.getTfPersona());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
        
    }
    
    public String btnLimpiarObraSocial_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(2, this.getTfObraSocial());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
        
    }
}
