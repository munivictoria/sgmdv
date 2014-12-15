package muni.saic.grpAutomotor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.ImageComponent;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.exception.ResultadoLiquidacion;

public class GenerarLiquidacionAutomotor extends AbstractPageBean {

	// <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
	// Atributos propios de la pagina.
	// CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
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
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Generar Liquidaciones de Automotor";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "GenerarLiquidacionAutomotor";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
	private int __placeholder;

	/**
	 * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
	 * This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.</p>
	 */
	private void _init() throws Exception {
		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalAutomotor().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosAutomotor(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalAutomotor(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalAutomotor(null).keySet());

	}

	public void eventoSeleccionAnio(ValueChangeEvent event) {
		String opcion = this.getDdAnios().getSelected().toString();
		seleccionarAnio(opcion, true);
	}

	public void eventoSeleccionCalendario(ValueChangeEvent event) {
		String opcion = this.getDdCalendarios().getSelected().toString();
		seleccionarCalendario(opcion, true);
	}

	public void eventoSeleccionPeriodo(ValueChangeEvent event) {
		String opcion = this.getDdPeriodos().getSelected().toString();
		seleccionarPeriodo(opcion, true);
	}

	private void seleccionarAnio(String pAnio, boolean actualizar){
		this.ddAnios.setSelected(pAnio);
		if (actualizar){
			actualizarOpcionesDDCalendario(pAnio);
		}
	}

	private void seleccionarCalendario(String pCalendario, boolean actualizar){
		this.ddCalendarios.setSelected(pCalendario);
		if (actualizar){
			this.actualizarOpcionesDDPeriodo(pCalendario);
		}
	}

	private void seleccionarPeriodo(String pPeriodo, boolean actualizar){
		this.ddPeriodos.setSelected(pPeriodo);
		if (actualizar) {
			this.actualizarOpcionesDDCuotas(pPeriodo);
		}
	}

	private void seleccionarCuota(String pCuota){
		this.ddCuotas.setSelected(pCuota);
	}

	private void actualizarOpcionesDDCalendario(String pAnio){
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosAutomotor(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario){
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalAutomotor(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo){
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalAutomotor(pPeriodo).keySet());
		seleccionarCuota(opcion);
	}

	private String llenarDD(SingleSelectOptionsList ddOpciones, Set<String> llaves) {
		Option[] opciones;
		String opcion = "";
		if (llaves == null || llaves.isEmpty()) {
			opciones = new Option[0];
		} else if (llaves.size() > 1) {
			opciones = new Option[llaves.size() + 1];
			opciones[0] = new Option("");
			int i = 1;
			for (String cadaLlave : llaves){
				opciones[i++] = new Option(cadaLlave);
			}
		} else {
			opciones = new Option[llaves.size()];
			opcion = llaves.iterator().next();
			opciones[0] = new Option(opcion);
		}
		ddOpciones.setOptions(opciones);
		return opcion;
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(
			PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
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
	private Label label2 = new Label();
	private Label lblVehiculo = new Label();

	private Button btnGenerarLiquidaciones = new Button();

	public Button getBtnGenerarLiquidaciones() {
		return btnGenerarLiquidaciones;
	}

	public void setBtnGenerarLiquidaciones(Button b) {
		this.btnGenerarLiquidaciones = b;
	}
	private Button btnCancelar = new Button();
	private Button btnSeleccionarVehiculo = new Button();
	private HtmlAjaxCommandButton btnLimpiarVehiculo = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarVehiculo() {
		return btnLimpiarVehiculo;
	}

	public void setBtnLimpiarVehiculo(HtmlAjaxCommandButton btnLimpiarVehiculo) {
		this.btnLimpiarVehiculo = btnLimpiarVehiculo;
	}

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

	private TextField tfPersona = new TextField();
	private TextField tfVehiculo = new TextField();

	public Button getBtnSeleccionarVehiculo() {
		return btnSeleccionarVehiculo;
	}

	public void setBtnSeleccionarVehiculo(Button btnSeleccionarVehiculo) {
		this.btnSeleccionarVehiculo = btnSeleccionarVehiculo;
	}

	public Label getLblVehiculo() {
		return lblVehiculo;
	}

	public void setLblVehiculo(Label lblVehiculo) {
		this.lblVehiculo = lblVehiculo;
	}

	public TextField getTfVehiculo() {
		return tfVehiculo;
	}

	public void setTfVehiculo(TextField tfVehiculo) {
		this.tfVehiculo = tfVehiculo;
	}

	private Label lblCalendarios = new Label();
	private Label lblPeriodos = new Label();
	private Label lblCuotas = new Label();
	private Label lblAnios = new Label();
	private Label lblPeriodo = new Label();


	public Label getLblAnios() {
		return lblAnios;
	}

	public void setLblAnios(Label lblAnios) {
		this.lblAnios = lblAnios;
	}

	public Label getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(Label lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public Label getLblCalendarios() {
		return lblCalendarios;
	}

	public void setLblCalendarios(Label lblCalendarios) {
		this.lblCalendarios = lblCalendarios;
	}

	public Label getLblCuotas() {
		return lblCuotas;
	}

	public void setLblCuotas(Label lblCuotas) {
		this.lblCuotas = lblCuotas;
	}

	public Label getLblPeriodos() {
		return lblPeriodos;
	}

	public void setLblPeriodos(Label lblPeriodos) {
		this.lblPeriodos = lblPeriodos;
	}

	private DropDown ddCalendarios = new DropDown();
	private DropDown ddPeriodos = new DropDown();
	private DropDown ddCuotas = new DropDown();
	private DropDown ddAnios = new DropDown();

	private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddAniosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddTipoSepulturaOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoSepulturaOptions() {
		return ddTipoSepulturaOptions;
	}

	public void setDdTipoSepulturaOptions(
			SingleSelectOptionsList ddTipoSepulturaOptions) {
		this.ddTipoSepulturaOptions = ddTipoSepulturaOptions;
	}

	public DropDown getDdAnios() {
		return ddAnios;
	}

	public void setDdAnios(DropDown ddAnios) {
		this.ddAnios = ddAnios;
	}

	public SingleSelectOptionsList getDdAniosOptions() {
		return ddAniosOptions;
	}

	public void setDdAniosOptions(SingleSelectOptionsList ddAniosOptions) {
		this.ddAniosOptions = ddAniosOptions;
	}

	public DropDown getDdCalendarios() {
		return ddCalendarios;
	}

	public void setDdCalendarios(DropDown ddCalendarios) {
		this.ddCalendarios = ddCalendarios;
	}

	public SingleSelectOptionsList getDdCalendariosOptions() {
		return ddCalendariosOptions;
	}

	public void setDdCalendariosOptions(SingleSelectOptionsList ddCalendariosOptions) {
		this.ddCalendariosOptions = ddCalendariosOptions;
	}

	public DropDown getDdCuotas() {
		return ddCuotas;
	}

	public void setDdCuotas(DropDown ddCuotas) {
		this.ddCuotas = ddCuotas;
	}

	public SingleSelectOptionsList getDdCuotasOptions() {
		return ddCuotasOptions;
	}

	public void setDdCuotasOptions(SingleSelectOptionsList ddCuotasOptions) {
		this.ddCuotasOptions = ddCuotasOptions;
	}

	public DropDown getDdPeriodos() {
		return ddPeriodos;
	}

	public void setDdPeriodos(DropDown ddPeriodos) {
		this.ddPeriodos = ddPeriodos;
	}

	public SingleSelectOptionsList getDdPeriodosOptions() {
		return ddPeriodosOptions;
	}

	public void setDdPeriodosOptions(SingleSelectOptionsList ddPeriodosOptions) {
		this.ddPeriodosOptions = ddPeriodosOptions;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}
	private HtmlPanelGrid gdpGenerarLiquidaciones = new HtmlPanelGrid();

	public HtmlPanelGrid getGdpGenerarLiquidaciones() {
		return gdpGenerarLiquidaciones;
	}

	public void setGdpGenerarLiquidaciones(HtmlPanelGrid hpg) {
		this.gdpGenerarLiquidaciones = hpg;
	}
	private StaticText stMensajeGeneracion1 = new StaticText();

	public StaticText getStMensajeGeneracion1() {
		return stMensajeGeneracion1;
	}

	public void setStMensajeGeneracion1(StaticText st) {
		this.stMensajeGeneracion1 = st;
	}
	private StaticText stSpacer1 = new StaticText();

	public StaticText getStSpacer1() {
		return stSpacer1;
	}

	public void setStSpacer1(StaticText st) {
		this.stSpacer1 = st;
	}
	private Button btnGenerarLiquidacionesFinal = new Button();
	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarPersonaJuridica = new Button();
	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button btnSeleccionarPersonaFisica) {
		this.btnSeleccionarPersonaFisica = btnSeleccionarPersonaFisica;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
		this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}

	public Button getBtnGenerarLiquidacionesFinal() {
		return btnGenerarLiquidacionesFinal;
	}

	public void setBtnGenerarLiquidacionesFinal(Button b) {
		this.btnGenerarLiquidacionesFinal = b;
	}
	private ImageComponent image1 = new ImageComponent();

	public ImageComponent getImage1() {
		return image1;
	}

	public void setImage1(ImageComponent ic) {
		this.image1 = ic;
	}
	private HtmlPanelGrid grpCargando = new HtmlPanelGrid();

	public HtmlPanelGrid getGrpCargando() {
		return grpCargando;
	}

	public void setGrpCargando(HtmlPanelGrid hpg) {
		this.grpCargando = hpg;
	}
	private ImageComponent image2 = new ImageComponent();

	public ImageComponent getImage2() {
		return image2;
	}

	public void setImage2(ImageComponent ic) {
		this.image2 = ic;
	}
	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}
	private Button btnGenerarLiquidacionPrueba = new Button();

	public Button getBtnGenerarLiquidacionPrueba() {
		return btnGenerarLiquidacionPrueba;
	}

	public void setBtnGenerarLiquidacionPrueba(Button b) {
		this.btnGenerarLiquidacionPrueba = b;
	}
	private StaticText stSeparador1 = new StaticText();

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText st) {
		this.stSeparador1 = st;
	}
	// </editor-fold>

	/** 
	 * <p>Construir una instancia de bean de p�gina.</p>
	 */
	public GenerarLiquidacionAutomotor() {
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
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
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
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
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
	@Override
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
			log("GenerarLiquidacionAutomotor Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

	}

	/** 
	 * <p>M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha
	 * restaurado, pero antes de que se produzca el procesamiento de cualquier evento.  Este m�todo
	 * <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que
	 * est� procesando el env�o de un formulario.  Puede personalizar este m�todo para asignar
	 * recursos necesarios para los controladores de eventos.</p>
	 */
	@Override
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
	@Override
	public void prerender() {
		boolean existeIdSubSesionReq = false;
		boolean existeIdPaginaReq = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag = false;
		boolean recarga = false;
		boolean cargarTabla = false;

		if (this.getRequestBean1() != null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag = this.getIdPagina() != null;

		// 1. Pagina nueva - Inicio de transaccion
		if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if (this.PUEDE_SER_PAGINA_INICIAL) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();
			}
		}

		// 2. Recarga de la misma pagina por validacion
		if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// .. ver si es as� realmente..
		}

		// 3. Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();

			cargarTabla = true;
			cargarValoresPorDefecto();
		}

		// 4. Pagina nueva - hacia atras en la transaccion
		if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());
		}

		if (!recarga) {
			this.mostrarEstadoObjetosUsados();
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));

		System.out.println("//////////////// FIN prerender()");
	}

	/** 
	 * <p>M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de
	 * esta petici�n, si se llam� al m�todo <code>init()</code> (sin tener en cuenta
	 * si se trata de la p�gina que se ha procesado o no).  Puede personalizar este
	 * m�todo para liberar los recursos adquiridos en los m�todos <code>init()</code>,
	 * <code>preprocess()</code> o <code>prerender()</code> (o
	 * durante la ejecuci�n de un controlador de eventos).</p>
	 */
	@Override
	public void destroy() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {

		//CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(0, null);     // Calendario
		ep.getObjetos().add(1, null);     // Periodo
		ep.getObjetos().add(2, null);     // Cuota
		ep.getObjetos().add(3, null);
		ep.getObjetos().add(4, new Vehiculo());
		ep.getObjetos().add(5, getAnioCorriente().toString()); // 5 anios
		ep.getObjetos().add(6, new ArrayList()); // 6 lista atributos dinamicos

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(7, new Integer(0));
		return ep;
	}

	private String getAnioCorriente (){
		String anioActual = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString();
		Integer locAnioActualMapa = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalAutomotor().get(anioActual);
		if (locAnioActualMapa != null){
			return anioActual;
		} else {
			return this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalAutomotor().keySet().iterator().next();
		}
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.

		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
		Vehiculo vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(4, Vehiculo.class);
		List listaAtributosDinamicos = (List) this.obtenerObjetoDelElementoPila(6, ArrayList.class);

		if (persona != null && persona.getIdPersona() == -1) {
			persona = null;
		}
		if (vehiculo != null && vehiculo.getIdVehiculo() == -1) {
			vehiculo = null;
		}

		String anioSeleccionado = this.getDdAnios().getSelected().toString();
		String calendarioSeleccionado = this.getDdCalendarios().getSelected().toString();
		String periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected().toString();
		String cuotaSeleccionada = this.getDdCuotas().getSelected().toString();
		listaAtributosDinamicos = panelAtributoDinamico.obtenerListaAtributosDinamicos(listaAtributosDinamicos);

		this.getElementoPila().getObjetos().set(0, calendarioSeleccionado);
		this.getElementoPila().getObjetos().set(1, periodoCalendarioSeleccionado);
		this.getElementoPila().getObjetos().set(2, cuotaSeleccionada);
		this.getElementoPila().getObjetos().set(3, persona);
		this.getElementoPila().getObjetos().set(4, vehiculo);
		this.getElementoPila().getObjetos().set(5, anioSeleccionado);
		this.getElementoPila().getObjetos().set(6, listaAtributosDinamicos);
	}

	private void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.

		Persona persona = null;
		Vehiculo vehiculo = null;

		this.acomodarSeleccionado();

		persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
		vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(4, Vehiculo.class);
		String anio = (String) this.obtenerObjetoDelElementoPila(5, String.class); 

		if (persona != null) {
			this.getTfPersona().setText(persona.toString());
		}

		if (vehiculo != null && vehiculo.getIdVehiculo() != -1) {
			this.getTfVehiculo().setText(vehiculo.toString());
		}
		seleccionarAnio(anio, true);

		List listaAtributosDinamicos;
		listaAtributosDinamicos = (List) this.obtenerObjetoDelElementoPila(6, ArrayList.class);

		if (listaAtributosDinamicos.isEmpty()) {
			try {
				listaAtributosDinamicos =  this.getComunicationBean()
						.getRemoteSystemParametro().getAtributosPorRecurso(DocumentoAutomotor.serialVersionUID, null, true);
				if ( listaAtributosDinamicos != null) {
					this.getElementoPila().getObjetos().set(6, listaAtributosDinamicos);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		panelAtributoDinamico = new PanelAtributoDinamico(listaAtributosDinamicos,"#{saic$grpAutomotor$ABMLiquidacionAutomotor$GenerarLiquidacionAutomotor}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(listaAtributosDinamicos);

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

	private void acomodarSeleccionado() {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if (seleccionado != null) {
			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, seleccionado);
		}
	}

	private int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
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
	// </editor-fold>        

	public String btnGenerarLiquidaciones_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			try {
				this.guardarEstadoObjetosUsados();

				// CAMBIAR: Validar los campos necesarios.
				Validador v = new Validador();
				UIComponent[] noVacios = new UIComponent[4];
				String[] nomNoVacios = new String[4];

				int pos = 0;
				noVacios[pos] = this.getDdAnios();
				nomNoVacios[pos++] = "Año";
				noVacios[pos] = this.getDdCalendarios();
				nomNoVacios[pos++] = "Calendario";
				noVacios[pos] = this.getDdPeriodos();
				nomNoVacios[pos++] = "Periodo";
				noVacios[pos] = this.getDdCuotas();
				nomNoVacios[pos++] = "Cuota";

				v.noSonVacios(noVacios, nomNoVacios);

				if (v.getErrores().size() > 0) {
					error("Existen Errores:");
					for (int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					return null;
				}

				CuotaLiquidacion locCuota = null;
				String cuota = (String) this.obtenerObjetoDelElementoPila(2, String.class);
				Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
				Vehiculo vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(4, Vehiculo.class);
				this.getCommunicationSAICBean().desactivarButton(this.btnCancelar);

				if ((cuota != null) && (cuota.toString().length() > 0)) {
					locCuota = this.getRecuperarCuotaPorPeriodo(cuota);
				} else {
					locCuota = null;
				}

				System.out.println("LA CUOTA DEL ORTO: " + locCuota);

				if (vehiculo.getIdVehiculo() == -1) {
					vehiculo = null;
				}

				int totalGenerados = 0;
				// CAMBIAR: Utilizar el EJBClient adecuado.

				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());

				//                Comentado
				CuotaLiquidacion[] arregloCuotas = new CuotaLiquidacion[1];
				arregloCuotas[0] = locCuota;
                ResultadoLiquidacion locResultadoLiquidacion = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().liquidarAutomotor(vehiculo, persona, locCuota);
                
                totalGenerados = locResultadoLiquidacion.getCantidadLiquidadas();

				this.getRequestBean1().setRespuestaABM(null);

				String mensaje = "";
				switch (totalGenerados) {
				case 0:
					mensaje = "No se generaron Liquidaciones de Automotor.";
					break;
				case 1:
					mensaje = "Se gener\363 exitosamente 1 Liquidaci\363n de Automotor.";
					break;
				default:
					mensaje = "Se generaron exitosamente " + totalGenerados + " Liquidaciones de Automotor.";
				}
				
				if(!locResultadoLiquidacion.getListaObligacionesNoLiquidadas().isEmpty()){
					mensaje += " No se generaron " + locResultadoLiquidacion.getListaObligacionesNoLiquidadas().size() + " liquidaciones. " + locResultadoLiquidacion.getListaObligacionesNoLiquidadas();
				}
				
				mensaje += " " + locResultadoLiquidacion.getMensajes();
				info(mensaje);
				
				retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);

			} catch (Exception ex) {
				if (ex instanceof TrascenderException) {
					int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
					if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) {
						retorno = null;
					} else {
						retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
					}
				}
				log(CASO_NAVEGACION + "_GenerarLiquidacionesError:", ex);
				error(NOMBRE_PAGINA + " - Generar Liquidaciones: " + ex.getMessage());
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnCancelar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {

			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	private void cargarValoresPorDefecto() {
		//        int ind = 0;
		//        this.getElementoPila().getObjetos().set(ind++, new Integer(Calendar.getInstance().get(Calendar.YEAR)));
		//        this.getElementoPila().getObjetos().set(ind++, Periodicidad.ANUAL);
		//        this.getElementoPila().getObjetos().set(ind++, new Integer(1));
		return;
	}

	public String btnGenerarLiquidacionPrueba_action() {
		/**Mines*/
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			try {
				this.guardarEstadoObjetosUsados();

				Parcela parcela = null;
				// CAMBIAR: Validar los campos necesarios.
				Validador v = new Validador();
				UIComponent[] noVacios = new UIComponent[3];
				String[] nomNoVacios = new String[3];

				int pos = 0;
				noVacios[pos] = this.getDdCalendarios();
				nomNoVacios[pos++] = "Calendario";
				noVacios[pos] = this.getDdPeriodos();
				nomNoVacios[pos++] = "Periodo";
				noVacios[pos] = this.getDdCuotas();
				nomNoVacios[pos++] = "Cuota";

				v.noSonVacios(noVacios, nomNoVacios);

				if (v.getErrores().size() > 0) {
					error("Existen Errores:");
					for (int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					return null;
				}

				CuotaLiquidacion locCuota = null;
				String cuota = (String) this.obtenerObjetoDelElementoPila(2, String.class);
				Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
				Vehiculo locVehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(4, Vehiculo.class);

				if ((cuota != null) && (cuota.toString().length() > 0)) {
					locCuota = this.getRecuperarCuotaPorPeriodo( cuota);
				} else {
					locCuota = null;
				}
				if (locVehiculo != null && locVehiculo.getIdVehiculo() != -1) {
					try {
						this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
						//                        locVehiculo = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(locVehiculo.getIdVehiculo());
					} catch (Exception ex) {
						error("Error al Obtener Parcela: " + ex.getMessage());
						return null;
					}
				}
				int totalGenerados = 0;

				// CAMBIAR: Utilizar el EJBClient adecuado.

				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());

//				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().generarLiquidacionPrueba(locCuota, persona, parcela);

				info("Finaliz\363 exitosamente la Liquidaci\363n de prueba Automotor.");

				//List liquidacionesGeneradas = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().generarLiquidacionPrueba(periodo);
				//if (liquidacionesGeneradas != null) totalGenerados = liquidacionesGeneradas.size();

				this.getRequestBean1().setRespuestaABM(null);
				this.getSessionBean1().setObjetoImpresion(new Integer(totalGenerados));


				retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);

			} catch (Exception ex) {
				if (ex instanceof TrascenderException) {
					int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
					if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) {
						retorno = null;
					} else {
						retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
					}
				}
				log(CASO_NAVEGACION + "_GenerarLiquidacionesPruebaError:", ex);
				error(NOMBRE_PAGINA + " - Generar Liquidaciones Prueba: " + ex.getMessage());
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarPersonaFisica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 3;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarVehiculo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 4;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminVehiculo";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarVehiculo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(4, this.getTfVehiculo());
			this.guardarEstadoObjetosUsados();
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
			this.limpiarObjeto(3, this.getTfPersona());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarPersonaJuridica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 3;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminPersonaJuridica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	private CuotaLiquidacion getRecuperarCuotaPorPeriodo( String pCuota ){
		CuotaLiquidacion locCuota = this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalAutomotor(null).get(pCuota);
		return locCuota;
	}


}
