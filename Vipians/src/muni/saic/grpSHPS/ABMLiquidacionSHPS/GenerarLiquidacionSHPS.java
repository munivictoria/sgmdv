/*
 * GenerarLiquidacionSHPS.java
 *
 * Created on 1 de noviembre de 2006, 14:27
 * Copyright Trascender SRL
 */

package muni.saic.grpSHPS.ABMLiquidacionSHPS;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
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
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.exception.ResultadoLiquidacion;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class GenerarLiquidacionSHPS extends AbstractPageBean {

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
	private final String NOMBRE_PAGINA = "Generar Liquidaciones de SHPS";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "GenerarLiquidacionSHPS";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;
	private int __placeholder;

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	private void _init() throws Exception {

		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosSHPS(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(null).keySet());

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

	private void seleccionarAnio(String pAnio, boolean actualizar) {
		this.ddAnios.setSelected(pAnio);
		if(actualizar) {
			actualizarOpcionesDDCalendario(pAnio);
		}
	}

	private void seleccionarCalendario(String pCalendario, boolean actualizar) {
		this.ddCalendarios.setSelected(pCalendario);
		if(actualizar) {
			this.actualizarOpcionesDDPeriodo(pCalendario);
		}
	}

	private void seleccionarPeriodo(String pPeriodo, boolean actualizar) {
		this.ddPeriodos.setSelected(pPeriodo);
		if(actualizar) {
			this.actualizarOpcionesDDCuotas(pPeriodo);
		}
	}

	private void seleccionarCuota(String pCuota) {
		this.ddCuotas.setSelected(pCuota);
	}

	private void actualizarOpcionesDDCalendario(String pAnio) {
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosSHPS(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(pPeriodo).keySet());
		seleccionarCuota(opcion);
	}

	private String llenarDD(SingleSelectOptionsList ddOpciones, Set<String> llaves) {
		Option[] opciones;
		String opcion = "";
		if(llaves == null || llaves.isEmpty()) {
			opciones = new Option[0];
		} else if(llaves.size() > 1) {
			opciones = new Option[llaves.size() + 1];
			opciones[0] = new Option("");
			int i = 1;
			for(String cadaLlave : llaves) {
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

	private Label lblLiquidarDDJJSHPS = new Label();
	private Label lblDDJJSHPS = new Label();

	private Button btnGenerarLiquidaciones = new Button();

	public Button getBtnGenerarLiquidaciones() {
		return btnGenerarLiquidaciones;
	}

	public void setBtnGenerarLiquidaciones(Button b) {
		this.btnGenerarLiquidaciones = b;
	}

	private Button btnCancelar = new Button();
	private Button btnSeleccionarDDJJSHPS = new Button();
	private Button btnLimpiarDDJJSHPS = new Button();

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

	private TextField tfDDJJSHPS = new TextField();

	public Button getBtnLimpiarDDJJSHPS() {
		return btnLimpiarDDJJSHPS;
	}

	public void setBtnLimpiarDDJJSHPS(Button btnLimpiarDDJJSHPS) {
		this.btnLimpiarDDJJSHPS = btnLimpiarDDJJSHPS;
	}

	public Button getBtnSeleccionarDDJJSHPS() {
		return btnSeleccionarDDJJSHPS;
	}

	public void setBtnSeleccionarDDJJSHPS(Button btnSeleccionarDDJJSHPS) {
		this.btnSeleccionarDDJJSHPS = btnSeleccionarDDJJSHPS;
	}

	public Label getLblDDJJSHPS() {
		return lblDDJJSHPS;
	}

	public void setLblDDJJSHPS(Label lblDDJJSHPS) {
		this.lblDDJJSHPS = lblDDJJSHPS;
	}

	public Label getLblLiquidarDDJJSHPS() {
		return lblLiquidarDDJJSHPS;
	}

	public void setLblLiquidarDDJJSHPS(Label lblLiquidarDDJJSHPS) {
		this.lblLiquidarDDJJSHPS = lblLiquidarDDJJSHPS;
	}

	public TextField getTfDDJJSHPS() {
		return tfDDJJSHPS;
	}

	public void setTfDDJJSHPS(TextField tfDDJJSHPS) {
		this.tfDDJJSHPS = tfDDJJSHPS;
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

	private final Checkbox cbIgnorarPlan = new Checkbox();
	private Label lblIgnorarPlan = new Label();

	public Label getLblIgnorarPlan() {
		return lblIgnorarPlan;
	}

	public void setLblIgnorarPlan(Label lblIgnorarPlan) {
		this.lblIgnorarPlan = lblIgnorarPlan;
	}

	public Checkbox getCbIgnorarPlan() {
		return cbIgnorarPlan;
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

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private Label lblNroInscripcion = new Label();

	public Label getLblNroInscripcion() {
		return lblNroInscripcion;
	}

	public void setLblNroInscripcion(Label lblNroInscripcion) {
		this.lblNroInscripcion = lblNroInscripcion;
	}

	private TextField tfNroInscripcion = new TextField();

	public TextField getTfNroInscripcion() {
		return tfNroInscripcion;
	}

	public void setTfNroInscripcion(TextField tfNroInscripcion) {
		this.tfNroInscripcion = tfNroInscripcion;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	private TextField tfRubro = new TextField();

	public TextField getTfRubro() {
		return tfRubro;
	}

	public void setTfRubro(TextField tf) {
		this.tfRubro = tf;
	}

	private Button btnSeleccionarRubro = new Button();

	public Button getBtnSeleccionarRubro() {
		return btnSeleccionarRubro;
	}

	public void setBtnSeleccionarRubro(Button b) {
		this.btnSeleccionarRubro = b;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private Button btnLimpiarRubro = new Button();

	public Button getBtnLimpiarRubro() {
		return btnLimpiarRubro;
	}

	public void setBtnLimpiarRubro(Button b) {
		this.btnLimpiarRubro = b;
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

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private Label label8 = new Label();

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label l) {
		this.label8 = l;
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

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public GenerarLiquidacionSHPS() {
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (muni.CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationCajaBean getCommunicationCajaBean() {
		return (muni.CommunicationCajaBean) getBean("CommunicationCajaBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationSAICBean getCommunicationSAICBean() {
		return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1) getBean("ApplicationBean1");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	/**
	 * <p>
	 * Devolver una referencia al bean de datos con �mbito.
	 * </p>
	 */
	protected muni.RequestBean1 getRequestBean1() {
		return (muni.RequestBean1) getBean("RequestBean1");
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina, ya sea directamente mediante un URL o de manera indirecta a trav�s de
	 * la navegaci�n de p�ginas. Puede personalizar este m�todo para adquirir recursos que se necesitar�n para los controladores de eventos y m�todos del
	 * proceso, sin tener en cuenta si esta p�gina realiza procesamiento de devoluci�n de env�os.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores de propiedad de los componentes <strong>no</strong> representan ning�n
	 * valor enviado con esta petici�n. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se proces�.
	 * </p>
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
		} catch(Exception e) {
			log("GenerarLiquidacionSHPS Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
		// </editor-fold>
		// Realizar inicio de aplicaci�n que debe finalizar
		// *despu�s* de que se inicien los componentes administrados
		// TODO - Agregar c�digo de inicio propio aqu�

	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	@Override
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */
	@Override
	public void prerender() {
		boolean existeIdSubSesionReq = false;
		boolean existeIdPaginaReq = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag = false;
		boolean recarga = false;
		boolean cargarTabla = false;

		if(this.getRequestBean1() != null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag = this.getIdPagina() != null;

		// 1. Pagina nueva - Inicio de transaccion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if(this.PUEDE_SER_PAGINA_INICIAL) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();
			}
		}

		// 2. Recarga de la misma pagina por validacion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// APLICAR LOGICA AQUI.. ver si es as� realmente..
		}

		// 3. Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if(existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();

			cargarValoresPorDefecto();
			cargarTabla = true;
		}

		// 4. Pagina nueva - hacia atras en la transaccion
		if(existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());
		}

		if(!recarga) {
			this.mostrarEstadoObjetosUsados();
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));

		System.out.println("//////////////// FIN prerender()");
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	@Override
	public void destroy() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(0, null); // Calendario
		ep.getObjetos().add(1, null); // Periodo
		ep.getObjetos().add(2, null); // Cuota
		ep.getObjetos().add(3, null); // Persona (F�sica o Jur�dica)
		// ep.getObjetos().add(4, new PersonaJuridica());
		ep.getObjetos().add(4, new RegAlicuota());
		ep.getObjetos().add(5, new DeclaracionJuradaSHPS());
		ep.getObjetos().add(6, new ArrayList());// Atributos dinamicos SHPS
		ep.getObjetos().add(7, getAnioCorriente().toString());
		ep.getObjetos().add(8, new Boolean(false));// 8 Checkbox Ignorar Plan
		ep.getObjetos().add(9, null); // 9 Nro de Inscripcion
		ep.getObjetos().add(10, null); // 10 String para saber a que pagina volver
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(11, new Integer(0));
		return ep;
	}

	private String getAnioCorriente() {
		String anioActual = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString();
		Integer locAnioActualMapa = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().get(anioActual);
		if(locAnioActualMapa != null) {
			return anioActual;
		} else {
			return this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet().iterator().next();
		}
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.

		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
		RegAlicuota rubro = (RegAlicuota) this.obtenerObjetoDelElementoPila(4, RegAlicuota.class);
		DeclaracionJuradaSHPS declaracionJurada = (DeclaracionJuradaSHPS) this.obtenerObjetoDelElementoPila(5, DeclaracionJuradaSHPS.class);
		List listaAtributosDinamicos = (List) this.obtenerObjetoDelElementoPila(6, ArrayList.class);

		listaAtributosDinamicos = panelAtributoDinamico.obtenerListaAtributosDinamicos(listaAtributosDinamicos);
		String anioSeleccionado = this.getDdAnios().getSelected().toString();
		String calendarioSeleccionado = this.getDdCalendarios().getSelected().toString();
		String periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected().toString();
		String cuotaSeleccionada = this.getDdCuotas().getSelected().toString();
		Boolean ignorarPlan = this.getCbIgnorarPlan().isChecked();
		String nroInscripcion = (String) this.getTfNroInscripcion().getText();

		this.getElementoPila().getObjetos().set(0, calendarioSeleccionado);
		this.getElementoPila().getObjetos().set(1, periodoCalendarioSeleccionado);
		this.getElementoPila().getObjetos().set(2, cuotaSeleccionada);
		this.getElementoPila().getObjetos().set(3, persona);
		this.getElementoPila().getObjetos().set(4, rubro);
		this.getElementoPila().getObjetos().set(5, declaracionJurada);
		this.getElementoPila().getObjetos().set(6, listaAtributosDinamicos);
		this.getElementoPila().getObjetos().set(7, anioSeleccionado);
		this.getElementoPila().getObjetos().set(8, ignorarPlan);
		this.getElementoPila().getObjetos().set(9, nroInscripcion);
	}

	private void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.

		if(this.getRequestBean1().getObjetoSeleccion() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();

			if(seleccionado instanceof Persona) {
				Persona persona = (Persona) seleccionado;
				this.getElementoPila().getObjetos().set(3, persona);
			}
		}
		if(this.getRequestBean1().getObjetoABM() != null) {
			DeclaracionJuradaSHPS locDDJJSHPS = (DeclaracionJuradaSHPS) this.getRequestBean1().getObjetoABM();

			this.getElementoPila().getObjetos().set(0, locDDJJSHPS.getCuotaLiquidacion().getPeriodo().getCalendario().getNombre());
			this.getElementoPila().getObjetos().set(1, locDDJJSHPS.getCuotaLiquidacion().getPeriodo().getNombre());
			this.getElementoPila().getObjetos().set(2, locDDJJSHPS.getCuotaLiquidacion().getNombre());
			this.getElementoPila().getObjetos().set(3, locDDJJSHPS.getDocHabilitanteEspecializado().getObligacion().getPersona());
			if(locDDJJSHPS.getDocHabilitanteEspecializado() instanceof DocumentoSHPS) {
				DocumentoSHPS locDocumento = (DocumentoSHPS) locDDJJSHPS.getDocHabilitanteEspecializado();
				this.getElementoPila().getObjetos().set(9, locDocumento.getNumeroInscripcion());
			}
			this.getElementoPila().getObjetos().set(10, "AdminDDJJSHPS");

			this.deshabilitarComponentesDesdeABMDDJJSHPS();
		}

		this.acomodarSeleccionado();

		String calendario = (String) this.obtenerObjetoDelElementoPila(0, String.class);
		String periodoCalendario = (String) this.obtenerObjetoDelElementoPila(1, String.class);
		String cuota = (String) this.obtenerObjetoDelElementoPila(2, String.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
		RegAlicuota rubro = (RegAlicuota) this.obtenerObjetoDelElementoPila(4, RegAlicuota.class);
		DeclaracionJuradaSHPS declaracionJurada = (DeclaracionJuradaSHPS) this.obtenerObjetoDelElementoPila(5, DeclaracionJuradaSHPS.class);
		List listaAtributosDinamicos = (List) this.obtenerObjetoDelElementoPila(6, ArrayList.class);
		String anio = (String) this.obtenerObjetoDelElementoPila(7, String.class);
		Boolean locIgnorarplan = (Boolean) this.obtenerObjetoDelElementoPila(8, Boolean.class);
		String nroInscripcion = (String) this.obtenerObjetoDelElementoPila(9, String.class);

		if(listaAtributosDinamicos.isEmpty()) {
			try {
				listaAtributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoSHPS.serialVersionUID, null, true);
				if(listaAtributosDinamicos != null) {
					this.getElementoPila().getObjetos().set(6, listaAtributosDinamicos);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		if(persona != null && persona.getIdPersona() != -1) {
			this.getTfPersona().setText(persona.toString());
		}
		this.getTfRubro().setText(rubro.toString());
		this.getTfNroInscripcion().setText(nroInscripcion);
		if(declaracionJurada.getIdRegistroValuado() != -1) {
			this.getTfDDJJSHPS().setText(declaracionJurada.toString());
		} else {
			this.getTfDDJJSHPS().setText("");
		}

		panelAtributoDinamico = new PanelAtributoDinamico(listaAtributosDinamicos, "#{saic$grpSHPS$ABMLiquidacionSHPS$GenerarLiquidacionSHPS}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(listaAtributosDinamicos);

		seleccionarAnio(anio, true);
		if(calendario != null && !calendario.isEmpty()) {
			seleccionarCalendario(calendario, true);
		}
		if(periodoCalendario != null && !calendario.isEmpty()) {
			seleccionarPeriodo(periodoCalendario, true);
		}
		if(cuota != null && !cuota.isEmpty()) {
			seleccionarCuota(cuota);
		}

		this.getCbIgnorarPlan().setValue(locIgnorarplan);
	}

	// Deshabilita componentes si viene del ABMDDJJSHPS...
	private void deshabilitarComponentesDesdeABMDDJJSHPS() {
		this.getTfPersona().setDisabled(true);
		this.getBtnSeleccionarPersonaFisica().setRendered(false);
		this.getBtnSeleccionarPersonaJuridica().setRendered(false);
		this.getBtnLimpiarPersona().setRendered(false);
		this.getTfNroInscripcion().setDisabled(true);
		this.getDdAnios().setDisabled(true);
		this.getDdCalendarios().setDisabled(true);
		this.getDdPeriodos().setDisabled(true);
		this.getDdCuotas().setDisabled(true);
	}

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
		if(locElementoAnterior != null) {
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
			if(objeto == null) {
				objeto = tipoClase.newInstance();
			}
		} catch(Exception ex) {
		}
		return objeto;
	}

	private void acomodarSeleccionado() {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if(seleccionado != null) {
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
			if(campo != null) {
				campo.setText(null);
			}
		} catch(Exception ex) {
		}
	}

	public String btnSeleccionarPersonaFisica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 3;

		if(ultimo) {

			// APLICAR LOGICA AQUI...
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

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
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

		if(ultimo) {

			// APLICAR LOGICA AQUI...
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

	public String btnSeleccionarRubro_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 4;

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminRegAlicuotaSHPS";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarRubro_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(4, this.getTfRubro());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnGenerarLiquidaciones_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
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

				if(v.getErrores().size() > 0) {
					error("Existen Errores:");
					for(int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					return null;
				}

				CuotaLiquidacion locCuota = null;
				String cuota = (String) this.obtenerObjetoDelElementoPila(2, String.class);

				if((cuota != null) && (cuota.toString().length() > 0)) {
					locCuota = this.getRecuperarCuotaPorPeriodo(cuota);
				} else {
					locCuota = null;
				}

				this.getCommunicationSAICBean().desactivarButton(this.btnCancelar);
				// trato de obtener un periodo

				int totalGenerados = 0;

				Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
				RegAlicuota rubro = (RegAlicuota) this.obtenerObjetoDelElementoPila(4, RegAlicuota.class);
				DeclaracionJuradaSHPS declaracioJurada = (DeclaracionJuradaSHPS) this.obtenerObjetoDelElementoPila(5, DeclaracionJuradaSHPS.class);
				List listaAtributosDinamicos = (List) this.obtenerObjetoDelElementoPila(6, ArrayList.class);
				Boolean locIgnorarPlan = (Boolean) this.obtenerObjetoDelElementoPila(8, Boolean.class);
				String nroInscripcion = (String) this.obtenerObjetoDelElementoPila(9, String.class);

				if(persona != null && persona.getIdPersona() == -1) {
					persona = null;
				}
				if(rubro != null && rubro.getIdTipoAlicuota() == -1) {
					rubro = null;
				}
				if(declaracioJurada != null && declaracioJurada.getIdRegistroValuado() == -1) {
					declaracioJurada = null;
				}
				if (nroInscripcion.trim().isEmpty()) {
					nroInscripcion = null;
				}

				FiltroObligacionSHPS locFiltro = new FiltroObligacionSHPS();
				locFiltro.setListaAtributosDinamicos(listaAtributosDinamicos);
				locFiltro.setNumeroInscripcion(nroInscripcion);
				// CAMBIAR: Utilizar el EJBClient adecuado.
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());

				CuotaLiquidacion[] cuotas = new CuotaLiquidacion[1];
				cuotas[0] = locCuota;
				
				/**
				 * Chequear permiso para liquidar sin parametros.
				 */
				if (persona == null 
						&& locFiltro.getNumeroInscripcion() == null
						&& !SecurityMgr.getInstance().getPermiso(
								getSessionBean1().getLlave(), 
								LiquidacionTasa.codigoLiquidarSinParametros, 
								Permiso.Accion.INSERT)) {
					error("Debe seleccionar una Persona o Número de Inscripción");
					return null;
				}

				ResultadoLiquidacion locResultadoLiquidacion = 
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().liquidarSHPS(persona, cuotas, locFiltro, locIgnorarPlan);

				totalGenerados = locResultadoLiquidacion.getCantidadLiquidadas();

				this.getRequestBean1().setRespuestaABM(null);

				String mensaje = "";
				switch(totalGenerados) {
					case 0:
						mensaje = "No se generaron Liquidaciones de SHPS.";
						break;
					case 1:
						mensaje = "Se gener\363 exitosamente 1 Liquidaci\363n de SHPS.";
						break;
					default:
						mensaje = "Se generaron exitosamente " + totalGenerados + " Liquidaciones de SHPS.";
				}
				info(mensaje);
				
				for (String cadaMensaje : locResultadoLiquidacion.getListaMensajes()) {
					warn(cadaMensaje);
				}

				// if (totalGenerados > 0) {
				// if (totalGenerados == 1) info("Se gener\363 exitosamente 1 Liquidaci\363n de SHPS.");
				// else info("Se generaron exitosamente "+ totalGenerados +" Liquidaciones de SHPS.");
				// }
				// else {
				// info("No se generaron Liquidaciones de SHPS.");
				// }

				retorno = "AdminLiquidacionSHPS";
			} catch(Exception ex) {
				if(ex instanceof TrascenderException) {
					int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
					if(this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) {
						retorno = null;
					} else {
						retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
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

		if(ultimo) {
			String valor = (String) this.obtenerObjetoDelElementoPila(10, String.class);
			if(valor != null && valor.length() > 0) {
				retorno = valor;
			} else {
				retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	private void cargarValoresPorDefecto() {
		return;
	}

	public String btnGenerarLiquidacionPrueba_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
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
				nomNoVacios[pos++] = "Periodos";
				noVacios[pos] = this.getDdCuotas();
				nomNoVacios[pos++] = "Cuota";

				v.noSonVacios(noVacios, nomNoVacios);

				if(v.getErrores().size() > 0) {
					error("Existen Errores:");
					for(int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
					return null;
				}

				int ind = 0;
				CalendarioMunicipal calendario = null;
				PeriodoLiquidacion periodoCalendario = null;
				CuotaLiquidacion cuota = null;

				Object calendarioSeleccionado = this.getDdCalendarios().getSelected();
				Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
				Object cuotaSeleccionada = this.getDdCuotas().getSelected();

				int totalGenerados = 0;
				List liquidacionesGeneradas = null;

				Persona persona = (Persona) this.obtenerObjetoDelElementoPila(3, Persona.class);
				RegAlicuota rubro = (RegAlicuota) this.obtenerObjetoDelElementoPila(4, RegAlicuota.class);
				DeclaracionJuradaSHPS declaracionJuradaSHPS = (DeclaracionJuradaSHPS) this.obtenerObjetoDelElementoPila(5, DeclaracionJuradaSHPS.class);
				List listaAtributosDinamicos = (List) this.obtenerObjetoDelElementoPila(6, ArrayList.class);

				if(persona.getIdPersona() != -1) {
					persona = null;
				}
				if(rubro.getIdTipoAlicuota() == -1) {
					rubro = null;
				}
				if(declaracionJuradaSHPS.getIdRegistroValuado() == -1) {
					declaracionJuradaSHPS = null;
				}

				FiltroObligacionSHPS locFiltro = new FiltroObligacionSHPS();
				locFiltro.setListaAtributosDinamicos(listaAtributosDinamicos);

				// CAMBIAR: Utilizar el EJBClient adecuado.
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());

				List locListaLiquidacionesGeneradas = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().generarLiquidacionPruebaSHPS(persona, cuota, locFiltro);

				info("Finaliz\363 Exitosamente la Liquidaci\363 de prueba SHPS.");

				// JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteSHPS(locListaLiquidacionesGeneradas);
				//
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_LiquidacionSHPS");
				//
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

				this.getRequestBean1().setRespuestaABM(null);

				/*
				 * if (liquidacionesGeneradas != null) totalGenerados = liquidacionesGeneradas.size();
				 * 
				 * this.getRequestBean1().setRespuestaABM(null);
				 * 
				 * switch(totalGenerados) { case 0: info("No se generaron Liquidaciones de prueba SHPS."); break; case 1:
				 * info("Se gener\363 exitosamente 1 Liquidaci\363n de prueba SHPS."); break; default: info("Se generaron exitosamente "+ totalGenerados
				 * +" Liquidaciones de prueba SHPS."); }
				 */

				retorno = this.prepararParaVolver(Constantes.ACCION_MODIFICAR);

			} catch(Exception ex) {
				if(ex instanceof TrascenderException) {
					int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
					if(this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) {
						retorno = null;
					} else {
						retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
					}
				}
				log(CASO_NAVEGACION + "_GenerarLiquidacionesPruebaError:", ex);
				error(NOMBRE_PAGINA + " - Generar Liquidaciones Prueba: " + ex.getMessage());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;

	}

	public String btnSeleccionarDDJJSHPS_action() {
		// TODO: Replace with your code
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
		int posicionObjetoSeleccionado = 5;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminDDJJSHPS";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarDDJJSHPS_action() {
		// TODO: Replace with your code
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(5, this.getTfDDJJSHPS());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	private CuotaLiquidacion getRecuperarCuotaPorPeriodo(String pCuota) {
		CuotaLiquidacion locCuota = this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(null).get(pCuota);
		return locCuota;
	}
}