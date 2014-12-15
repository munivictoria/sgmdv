/*
 * GenerarLiquidacionPFO.java
 *
 * Created on 1 de noviembre de 2006, 14:27
 * Copyright Trascender SRL
 */
package muni.saic.grpPFO.ABMLiquidacionPFO;
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
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;


/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class GenerarLiquidacionPFO extends AbstractPageBean {

	// <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">

	// Atributos propios de la pagina.
	// CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
	private Long idPagina = null;
	private Long idSubSesion = null;
	public Long getIdPagina() { return idPagina; }
	public void setIdPagina(Long idPagina) { this.idPagina = idPagina; }
	public Long getIdSubSesion() { return idSubSesion; }
	public void setIdSubSesion(Long idSubSesion) { this.idSubSesion = idSubSesion; }

	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Generar Liquidaciones de PFO";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "GenerarLiquidacionPFO";
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
		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalPFO().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosPFO(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalPFO(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalPFO(null).keySet());
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
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosPFO(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario){
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalPFO(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo){
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalPFO(pPeriodo).keySet());
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

	private Button btnGenerarLiquidaciones = new Button();

	public Button getBtnGenerarLiquidaciones() {
		return btnGenerarLiquidaciones;
	}

	public void setBtnGenerarLiquidaciones(Button b) {
		this.btnGenerarLiquidaciones = b;
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

	private Label lblCalendarios = new Label();
	private Label lblPeriodos = new Label();
	private Label lblCuotas = new Label();
	private Label lblAnios = new Label();


	public Label getLblAnios() {
		return lblAnios;
	}
	public void setLblAnios(Label lblAnios) {
		this.lblAnios = lblAnios;
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

	private TextField tfCuadra = new TextField();

	public TextField getTfCuadra() {
		return tfCuadra;
	}

	public void setTfCuadra(TextField tf) {
		this.tfCuadra = tf;
	}

	private TextField tfCalle = new TextField();

	public TextField getTfCalle() {
		return tfCalle;
	}

	public void setTfCalle(TextField tf) {
		this.tfCalle = tf;
	}

	private Button btnSeleccionarCuadra = new Button();

	public Button getBtnSeleccionarCuadra() {
		return btnSeleccionarCuadra;
	}

	public void setBtnSeleccionarCuadra(Button b) {
		this.btnSeleccionarCuadra = b;
	}

	private Button btnSeleccionarCalle = new Button();

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button b) {
		this.btnSeleccionarCalle = b;
	}

	private HtmlAjaxCommandButton btnLimpiarCuadra = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarCuadra() {
		return btnLimpiarCuadra;
	}
	public void setBtnLimpiarCuadra(HtmlAjaxCommandButton btnLimpiarCuadra) {
		this.btnLimpiarCuadra = btnLimpiarCuadra;
	}

	private HtmlAjaxCommandButton btnLimpiarCalle = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarCalle() {
		return btnLimpiarCalle;
	}
	public void setBtnLimpiarCalle(HtmlAjaxCommandButton btnLimpiarCalle) {
		this.btnLimpiarCalle = btnLimpiarCalle;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private TextField tfObra = new TextField();

	public TextField getTfObra() {
		return tfObra;
	}

	public void setTfObra(TextField tf) {
		this.tfObra = tf;
	}

	private Button btnSeleccionarObra = new Button();

	public Button getBtnSeleccionarObra() {
		return btnSeleccionarObra;
	}

	public void setBtnSeleccionarObra(Button b) {
		this.btnSeleccionarObra = b;
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

	private HtmlAjaxCommandButton btnLimpiarPersonaFisica = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarPersonaFisica() {
		return btnLimpiarPersonaFisica;
	}
	public void setBtnLimpiarPersonaFisica(HtmlAjaxCommandButton btnLimpiarPersonaFisica) {
		this.btnLimpiarPersonaFisica = btnLimpiarPersonaFisica;
	}

	private HtmlAjaxCommandButton btnLimpiarObra = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarObra() {
		return btnLimpiarObra;
	}
	public void setBtnLimpiarObra(HtmlAjaxCommandButton btnLimpiarObra) {
		this.btnLimpiarObra = btnLimpiarObra;
	}

	private Label label8 = new Label();

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label l) {
		this.label8 = l;
	}

	private TextField tfPersonaJuridica = new TextField();

	public TextField getTfPersonaJuridica() {
		return tfPersonaJuridica;
	}

	public void setTfPersonaJuridica(TextField tf) {
		this.tfPersonaJuridica = tf;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private Button btnLimpiarPersonaJuridica = new Button();

	public Button getBtnLimpiarPersonaJuridica() {
		return btnLimpiarPersonaJuridica;
	}

	public void setBtnLimpiarPersonaJuridica(Button b) {
		this.btnLimpiarPersonaJuridica = b;
	}

	private HtmlPanelGrid grpCargando = new HtmlPanelGrid();

	public HtmlPanelGrid getGrpCargando() {
		return grpCargando;
	}

	public void setGrpCargando(HtmlPanelGrid hpg) {
		this.grpCargando = hpg;
	}

	private ImageComponent image1 = new ImageComponent();

	public ImageComponent getImage1() {
		return image1;
	}

	public void setImage1(ImageComponent ic) {
		this.image1 = ic;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}
	private StaticText stSeparador1 = new StaticText();

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText st) {
		this.stSeparador1 = st;
	}
	private Button btnGenerarLiquidacionPrueba = new Button();

	public Button getBtnGenerarLiquidacionPrueba() {
		return btnGenerarLiquidacionPrueba;
	}

	public void setBtnGenerarLiquidacionPrueba(Button b) {
		this.btnGenerarLiquidacionPrueba = b;
	}
	// </editor-fold>

	/** 
	 * <p>Construir una instancia de bean de p�gina.</p>
	 */
	public GenerarLiquidacionPFO() {
	}

	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (muni.CommunicationExcepcionesBean)getBean("CommunicationExcepcionesBean");
	}

	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationCajaBean getCommunicationCajaBean() {
		return (muni.CommunicationCajaBean)getBean("CommunicationCajaBean");
	}

	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean)getBean("CommunicationComprasBean");
	}

	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationSAICBean getCommunicationSAICBean() {
		return (muni.CommunicationSAICBean)getBean("CommunicationSAICBean");
	}

	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean)getBean("CommunicationHabilitacionesBean");
	}

	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1)getBean("SessionBean1");
	}


	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1)getBean("ApplicationBean1");
	}


	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean)getBean("ComunicationBean");
	}


	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean)getBean("ComunicationCatastroBean");
	}


	/** 
	 * <p>Devolver una referencia al bean de datos con �mbito.</p>
	 */
	protected muni.RequestBean1 getRequestBean1() {
		return (muni.RequestBean1)getBean("RequestBean1");
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
			log("GenerarLiquidacionPFO Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
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
		boolean existeIdPaginaReq    = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag    = false;
		boolean recarga              = false;
		boolean cargarTabla          = false;

		if(this.getRequestBean1()!=null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq    = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag    = this.getIdPagina() != null;

		// 1. Pagina nueva - Inicio de transaccion
		if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if(this.PUEDE_SER_PAGINA_INICIAL) {
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

			cargarValoresPorDefecto();
			cargarTabla = true;
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

		//        if (this.getDdCalendarios().getSelected() != null && !this.getDdCalendarios().getSelected().toString().equals("")) {
		//
		//            System.out.println("/*/* Calendario = tiene algo seleccionado = " + this.getDdCalendarios().getSelected() + ", se cargan periodos");
		//            CalendarioMunicipal locCalendarioSeleccionado = this.getCalendarioPorNombre(this.getDdCalendarios().getSelected().toString());
		//
		//            cargarComboPeriodos(locCalendarioSeleccionado);     
		//            if (this.getDdPeriodos().getSelected() != null && !this.getDdPeriodos().getSelected().toString().equals("")) {
		//                System.out.println("/*/* Periodo = tiene algo seleccionado = " + this.getDdPeriodos().getSelected() + ", se cargan cuotas");
		//                String strPeriodo = this.getDdPeriodos().getSelected().toString();
		//                PeriodoLiquidacion locPeriodoCalendarioSeleccionado = this.getPeriodoPorNombre(locCalendarioSeleccionado, strPeriodo);
		//
		//                cargarComboCuotas(locPeriodoCalendarioSeleccionado);                
		//            } else {
		//                System.out.println("/*/*/* Periodo = es distinto.. se pone en 0 cuotas");
		//                this.ddCuotasOptions.setOptions(new Option[0]);
		//            }
		//        } else {
		//            System.out.println("/*/*/* Calendario = es distinto.. se pone en 0 periodos y cuotas");
		//            this.ddPeriodosOptions.setOptions(new Option[0]);
		//            this.ddCuotasOptions.setOptions(new Option[0]);
		//        }

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
		ep.getObjetos().add(0, null); // 0 PF o PJ
		//ep.getObjetos().add(ind++, new PersonaJuridica()); // oculta en el JSP
		ep.getObjetos().add(1, new Obra()); // 1 //ACORDATE DE PONERLO EN NEW OBRA()
		ep.getObjetos().add(2, new Cuadra()); // 2 oculta en el JSP
		ep.getObjetos().add(3, new Calle());// 3
		ep.getObjetos().add(4, null);     // 4 Calendario
		ep.getObjetos().add(5, null);     // 5 Periodo
		ep.getObjetos().add(6, null);     // 6 Cuota
		ep.getObjetos().add(7, getAnioCorriente().toString()); //7
		ep.getObjetos().add(8, new ArrayList()); // 8 lista atributos dinamicos
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(9, new Integer(0));
		return ep;
	}

	private String getAnioCorriente (){
		String anioActual = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString();
		Integer locAnioActualMapa = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalPFO().get(anioActual);
		if (locAnioActualMapa != null){
			return anioActual;
		} else {
			return this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalPFO().keySet().iterator().hasNext()?
					this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalPFO().keySet().iterator().next():"";
		}
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.

		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(0, Persona.class); //0
		Obra obra = (Obra) this.obtenerObjetoDelElementoPila(1, Obra.class); // 1
		Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(2, Cuadra.class);// 2
		Calle calle = (Calle) this.obtenerObjetoDelElementoPila(3, Calle.class);// 3
		List listaAtributosDinamicos = (List) this.obtenerObjetoDelElementoPila(8, ArrayList.class);

		listaAtributosDinamicos = panelAtributoDinamico.obtenerListaAtributosDinamicos(listaAtributosDinamicos);
		String anioSeleccionado = this.getDdAnios().getSelected().toString();
		String calendarioSeleccionado = this.getDdCalendarios().getSelected().toString();
		String periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected().toString();
		String cuotaSeleccionada = this.getDdCuotas().getSelected().toString();

		if (persona != null && persona.getIdPersona() == -1) {
			persona = null;
		}
		if(obra != null && obra.getIdObra() == -1){
			obra = null;
		}

		if(cuadra != null && cuadra.getIdCuadra() == -1){
			cuadra = null;
		}
		if(calle != null && calle.getIdCalle() == -1){
			calle = null;
		}

		this.getElementoPila().getObjetos().set(0, persona); // 0
		this.getElementoPila().getObjetos().set(1, obra);// 1
		this.getElementoPila().getObjetos().set(2, cuadra); // 2
		this.getElementoPila().getObjetos().set(3, calle);// 3
		this.getElementoPila().getObjetos().set(4, calendarioSeleccionado);// 4
		this.getElementoPila().getObjetos().set(5, periodoCalendarioSeleccionado);// 5
		this.getElementoPila().getObjetos().set(6, cuotaSeleccionada);// 6
		this.getElementoPila().getObjetos().set(7, anioSeleccionado);// 7
		this.getElementoPila().getObjetos().set(8, listaAtributosDinamicos);// 8
	}

	private void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.

		if (this.getRequestBean1().getObjetoSeleccion() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
			int posicion = ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();

			if (posicion == 0) {
				this.getElementoPila().getObjetos().set(0, seleccionado);
			}
			else if (posicion == 1) {
				this.getElementoPila().getObjetos().set(1, seleccionado);
			}
			else {
				this.getElementoPila().getObjetos().set(posicion, seleccionado);
			}
		}

		int ind = 0;
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(0, Persona.class);
		Obra obra = (Obra) this.obtenerObjetoDelElementoPila(1, Obra.class);
		Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(2, Cuadra.class);
		Calle calle = (Calle) this.obtenerObjetoDelElementoPila(3, Calle.class);
		String calendario = (String) this.obtenerObjetoDelElementoPila(4, String.class);
		String periodoCalendario =  (String) this.obtenerObjetoDelElementoPila(5, String.class);
		String cuota = (String) this.obtenerObjetoDelElementoPila(6, String.class);
		String anio = (String) this.obtenerObjetoDelElementoPila(7, String.class);
		List listaAtributosDinamicos = (List) this.obtenerObjetoDelElementoPila(8, ArrayList.class);

		seleccionarAnio(anio, true);
		if (calendario != null && !calendario.isEmpty()){
			seleccionarCalendario(calendario, true);
		}
		if (periodoCalendario != null && !calendario.isEmpty()){
			seleccionarPeriodo(periodoCalendario, true);
		}
		if (cuota != null && !cuota.isEmpty()){
			seleccionarCuota(cuota);
		}

		if(persona != null) this.getTfPersona().setText(persona.toString());
		this.getTfObra().setText(obra.toString());
		this.getTfCuadra().setText(cuadra.toString());
		this.getTfCalle().setText(calle.toString());

		if (listaAtributosDinamicos.isEmpty()) {
			try {
				listaAtributosDinamicos =  this.getComunicationBean()
						.getRemoteSystemParametro().getAtributosPorRecurso(DocumentoPlanObra.serialVersionUID, null, true);
				if ( listaAtributosDinamicos != null) {
					this.getElementoPila().getObjetos().set(8, listaAtributosDinamicos);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		panelAtributoDinamico = new PanelAtributoDinamico(listaAtributosDinamicos,"#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO}");
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
			int posicion = ((Integer)this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, seleccionado);
		}
	}

	private int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
	}

	public void limpiarObjeto(int posicion, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, null);
			if (campo!=null) campo.setText(null);
		} catch (Exception ex) { }
	}
	// </editor-fold>        


	public String btnSeleccionarPersonaFisica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 0;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarPersonaFisica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(0,this.getTfPersona());
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
		int posicionObjetoSeleccionado = 0;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminPersonaJuridica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarPersonaJuridica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(0,this.getTfPersona());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}


	public String btnSeleccionarObra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 1;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminObra";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarObra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1,this.getTfObra());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}


	public String btnSeleccionarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 2;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminCuadra";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(2,this.getTfCuadra());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}


	public String btnSeleccionarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 3;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados()-1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminCalle";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(3, this.getTfCalle());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}


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
				String cuota = (String) this.obtenerObjetoDelElementoPila(6, String.class);

				if ((cuota != null) && (cuota.toString().length() > 0)) {
					locCuota = this.getRecuperarCuotaPorPeriodo(cuota);
				} else {
					locCuota = null;
				}

				Persona persona = (Persona) this.obtenerObjetoDelElementoPila(0, Persona.class);
				Obra obra = (Obra) this.obtenerObjetoDelElementoPila(1, Obra.class);
				Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(2, Cuadra.class);
				Calle calle = (Calle) this.obtenerObjetoDelElementoPila(3, Calle.class);

				try{
					if(persona instanceof PersonaFisica){
						this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
						persona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(persona.getIdPersona());
					} else 
						this.getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(this.getSessionBean1().getLlave());
					persona = this.getComunicationBean().getRemoteSystemPersonaJuridica().getPersonaJuridicaPorId(persona.getIdPersona());

					if(obra != null){
						this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().setLlave(this.getSessionBean1().getLlave());
						obra = this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().getObraPorId(obra.getIdObra());
					} else obra = null;
					if(cuadra != null){
						this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
						cuadra = this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().getCuadraPorId(new Long(cuadra.getIdCuadra()));
					} else cuadra = null;
					if(calle != null){
						this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
						calle = this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().findCalle(calle.getNombre());
					} else calle = null;

				}catch(Exception ex){

				}

				//if (persona == null ) persona = null;
				//if (obra.getIdObra() == -1) obra = null;
				//                if (cuadra == null) cuadra = null;
				if(obra != null && obra.getIdObra() == -1) obra = null;
				if (calle != null && calle.getIdCalle() == -1) calle = null;
				int totalGenerados = 0;
				// CAMBIAR: Utilizar el EJBClient adecuado.
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());

				//                Comentado
				//                List liquidacionesGeneradas = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().liquidarPFO(persona, obra, calle, periodo);
				//                if (liquidacionesGeneradas != null) totalGenerados = liquidacionesGeneradas.size();

				this.getRequestBean1().setRespuestaABM(null);

				switch(totalGenerados) {
				case 0: info("No se generaron Liquidaciones de PFO."); break;
				case 1: info("Se gener\363 exitosamente 1 Liquidaci\363n de PFO."); break;
				default: info("Se generaron exitosamente "+ totalGenerados +" Liquidaciones de PFO.");
				}

				retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);

			} catch (Exception ex) {

				log(CASO_NAVEGACION+"_GenerarLiquidacionesError:", ex);
				error(NOMBRE_PAGINA+" - Generar Liquidaciones: " + ex.getMessage());
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
		//        this.getElementoPila().getObjetos().set(4, new Integer(Calendar.getInstance().get(Calendar.YEAR)));
		//        this.getElementoPila().getObjetos().set(5, Periodicidad.ANUAL);
		//        this.getElementoPila().getObjetos().set(6, new Integer(1));
		return;
	}

	public String btnGenerarLiquidacionPrueba_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			try {
				this.guardarEstadoObjetosUsados();

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

				CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(4, CalendarioMunicipal.class);
				PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(5, PeriodoLiquidacion.class);
				CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(6, CuotaLiquidacion.class);

				Object calendarioSeleccionado = this.getDdCalendarios().getSelected();
				Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
				Object cuotaSeleccionada = this.getDdCuotas().getSelected();

				//                if ((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0)) {
				//                    calendario = getCalendarioPorNombre(calendarioSeleccionado.toString());
				//                } else {
				//                    calendario = null;
				//                }
				//                if ((periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0)) {
				//                    periodoCalendario = getPeriodoPorNombre(calendario, periodoCalendarioSeleccionado.toString());
				//                } else {
				//                    periodoCalendario = null;
				//                }
				//                if ((cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)) {
				//                    cuota = this.getCuotaPorNombre(periodoCalendario, cuotaSeleccionada.toString());
				//                } else {
				//                    cuota = null;
				//                }

				this.getCommunicationSAICBean().desactivarButton(this.btnCancelar);

				Persona persona = (Persona) this.obtenerObjetoDelElementoPila(0, Persona.class);
				Obra obra = (Obra) this.obtenerObjetoDelElementoPila(1, Obra.class);
				Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(2, Cuadra.class);
				Calle calle = (Calle) this.obtenerObjetoDelElementoPila(3, Calle.class);

				if (persona.getIdPersona() == -1) persona = null;
				if (obra.getIdObra() == -1) obra = null;
				if (cuadra.getIdCuadra() == -1) cuadra = null;
				if (calle.getIdCalle() == -1) calle = null;

				int totalGenerados = 0;

				// CAMBIAR: Utilizar el EJBClient adecuado.
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());

				List liquidacionesGeneradas = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().generarLiquidacionPruebaPFO(persona, obra, calle, cuota);
				if (liquidacionesGeneradas != null) totalGenerados = liquidacionesGeneradas.size();

				this.getRequestBean1().setRespuestaABM(null);

				this.getSessionBean1().setObjetoImpresion(new Integer(totalGenerados));

				switch(totalGenerados) {
				case 0: info("No se generaron Liquidaciones de prueba PFO."); break;
				case 1: info("Finaliz\363 exitosamente 1 Liquidaci\363n de prueba PFO."); break;
				default: info("Finalizaron exitosamente "+ totalGenerados +" Liquidaciones de prueba PFO.");
				}

				retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);

			} catch (Exception ex) {
				if (ex instanceof TrascenderException) {
					int codigoError = ((TrascenderException)ex).getCodeTrascenderException();
					if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) retorno = null;
					else retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
				}
				log(CASO_NAVEGACION+"_GenerarLiquidacionesPruebaError:", ex);
				error(NOMBRE_PAGINA+" - Generar Liquidaciones Prueba: " + ex.getMessage());
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	private CuotaLiquidacion getRecuperarCuotaPorPeriodo(String pCuota ){
		CuotaLiquidacion locCuota = this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalPFO(null).get(pCuota);
		return locCuota;
	}
}
