/*
 * AgregarValorMedidor.java
 *
 * Created on 1 de noviembre de 2006, 14:27
 * Copyright Trascender SRL
 */

package muni.saic.grpOSP.ABMValorMedidor;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
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
import com.sun.rave.web.ui.component.Message;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AgregarValorMedidor extends AbstractPageBean {

	private Long idPagina = null;
	private Long idSubSesion = null;
	private TextField tfParcela = new TextField();
	private Button btnSeleccionarParcela = new Button();
	private Button btnLimpiarParcela = new Button();

	public TextField getTfParcela() {
		return tfParcela;
	}

	public void setTfParcela(TextField tfParcela) {
		this.tfParcela = tfParcela;
	}

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button btnSeleccionarParcela) {
		this.btnSeleccionarParcela = btnSeleccionarParcela;
	}

	public Button getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(Button btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

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
	private final String NOMBRE_PAGINA = "Agregar Medici\363n del Medidor";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AgregarValorMedidor";
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
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalOSP().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosOSP(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalOSP(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalOSP(null).keySet());

		dateTimeConverter1.setPattern("dd/MM/yyyy");
		dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Buenos_Aires"));
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
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosOSP(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalOSP(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalOSP(pPeriodo).keySet());
		seleccionarCuota(opcion);
	}

	private String getAnioCorriente() {
		String anioActual = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString();
		Integer locAnioActualMapa = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalOSP().get(anioActual);
		if(locAnioActualMapa != null) {
			return anioActual;
		} else {
			return this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalOSP().keySet().iterator().next();
		}
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

	private Label lblCodigoMedidor = new Label();
	private Label lblServicioOSP = new Label();
	private Label lblCalle = new Label();
	private Label lblAnio = new Label();
	private Label lblCalendario = new Label();
	private Label lblPeriodo = new Label();
	private Label lblCuota = new Label();
	private TextField tfCodigoMedidor = new TextField();
	private TextField tfServicioOSP = new TextField();
	private TextField tfCalle = new TextField();
	private DropDown ddAnios = new DropDown();
	private DropDown ddCalendarios = new DropDown();
	private DropDown ddPeriodos = new DropDown();
	private DropDown ddCuotas = new DropDown();
	private SingleSelectOptionsList ddAniosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();
	private Button btnSeleccionarCalle = new Button();
	private HtmlAjaxCommandButton btnLimpiarServicio = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarCalle = new HtmlAjaxCommandButton();
	private Button btnSeleccionarServicioOSP = new Button();
	private HtmlAjaxCommandButton btnCargarMedidores = new HtmlAjaxCommandButton();

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button btnSeleccionarCalle) {
		this.btnSeleccionarCalle = btnSeleccionarCalle;
	}

	public HtmlAjaxCommandButton getBtnLimpiarServicio() {
		return btnLimpiarServicio;
	}

	public void setBtnLimpiarServicio(HtmlAjaxCommandButton btnLimpiarServicio) {
		this.btnLimpiarServicio = btnLimpiarServicio;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCalle() {
		return btnLimpiarCalle;
	}

	public void setBtnLimpiarCalle(HtmlAjaxCommandButton btnLimpiarCalle) {
		this.btnLimpiarCalle = btnLimpiarCalle;
	}

	public Button getBtnSeleccionarServicioOSP() {
		return btnSeleccionarServicioOSP;
	}

	public void setBtnSeleccionarServicioOSP(Button btnSeleccionarServicioOSP) {
		this.btnSeleccionarServicioOSP = btnSeleccionarServicioOSP;
	}

	public HtmlAjaxCommandButton getBtnCargarMedidores() {
		return btnCargarMedidores;
	}

	public void setBtnCargarMedidores(HtmlAjaxCommandButton btnCargarMedidores) {
		this.btnCargarMedidores = btnCargarMedidores;
	}

	public Label getLblCodigoMedidor() {
		return lblCodigoMedidor;
	}

	public void setLblCodigoMedidor(Label lblCodigoMedidor) {
		this.lblCodigoMedidor = lblCodigoMedidor;
	}

	public Label getLblServicioOSP() {
		return lblServicioOSP;
	}

	public void setLblServicioOSP(Label lblServicioOSP) {
		this.lblServicioOSP = lblServicioOSP;
	}

	public Label getLblCalle() {
		return lblCalle;
	}

	public void setLblCalle(Label lblCalle) {
		this.lblCalle = lblCalle;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public Label getLblCalendario() {
		return lblCalendario;
	}

	public void setLblCalendario(Label lblCalendario) {
		this.lblCalendario = lblCalendario;
	}

	public Label getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(Label lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public Label getLblCuota() {
		return lblCuota;
	}

	public void setLblCuota(Label lblCuota) {
		this.lblCuota = lblCuota;
	}

	public TextField getTfCodigoMedidor() {
		return tfCodigoMedidor;
	}

	public void setTfCodigoMedidor(TextField tfCodigoMedidor) {
		this.tfCodigoMedidor = tfCodigoMedidor;
	}

	public TextField getTfServicioOSP() {
		return tfServicioOSP;
	}

	public void setTfServicioOSP(TextField tfServicioOSP) {
		this.tfServicioOSP = tfServicioOSP;
	}

	public TextField getTfCalle() {
		return tfCalle;
	}

	public void setTfCalle(TextField tfCalle) {
		this.tfCalle = tfCalle;
	}

	public DropDown getDdAnios() {
		return ddAnios;
	}

	public void setDdAnios(DropDown ddAnios) {
		this.ddAnios = ddAnios;
	}

	public DropDown getDdCalendarios() {
		return ddCalendarios;
	}

	public void setDdCalendarios(DropDown ddCalendarios) {
		this.ddCalendarios = ddCalendarios;
	}

	public DropDown getDdPeriodos() {
		return ddPeriodos;
	}

	public void setDdPeriodos(DropDown ddPeriodos) {
		this.ddPeriodos = ddPeriodos;
	}

	public DropDown getDdCuotas() {
		return ddCuotas;
	}

	public void setDdCuotas(DropDown ddCuotas) {
		this.ddCuotas = ddCuotas;
	}

	public SingleSelectOptionsList getDdAniosOptions() {
		return ddAniosOptions;
	}

	public void setDdAniosOptions(SingleSelectOptionsList ddAniosOptions) {
		this.ddAniosOptions = ddAniosOptions;
	}

	public SingleSelectOptionsList getDdCalendariosOptions() {
		return ddCalendariosOptions;
	}

	public void setDdCalendariosOptions(SingleSelectOptionsList ddCalendariosOptions) {
		this.ddCalendariosOptions = ddCalendariosOptions;
	}

	public SingleSelectOptionsList getDdPeriodosOptions() {
		return ddPeriodosOptions;
	}

	public void setDdPeriodosOptions(SingleSelectOptionsList ddPeriodosOptions) {
		this.ddPeriodosOptions = ddPeriodosOptions;
	}

	public SingleSelectOptionsList getDdCuotasOptions() {
		return ddCuotasOptions;
	}

	public void setDdCuotasOptions(SingleSelectOptionsList ddCuotasOptions) {
		this.ddCuotasOptions = ddCuotasOptions;
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

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
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

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfPeriodo = new TextField();

	public TextField getTfPeriodo() {
		return tfPeriodo;
	}

	public void setTfPeriodo(TextField tf) {
		this.tfPeriodo = tf;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private ObjectListDataProvider ldpMedicionMedidorOSP = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpMedicionMedidorOSP() {
		return ldpMedicionMedidorOSP;
	}

	public void setLdpMedicionMedidorOSP(ObjectListDataProvider oldp) {
		this.ldpMedicionMedidorOSP = oldp;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private TextField textField1 = new TextField();

	public TextField getTextField1() {
		return textField1;
	}

	public void setTextField1(TextField tf) {
		this.textField1 = tf;
	}

	private TableColumn tableColumn7 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private TextField tfConsumo = new TextField();

	public TextField getTfConsumo() {
		return tfConsumo;
	}

	public void setTfConsumo(TextField tfConsumo) {
		this.tfConsumo = tfConsumo;
	}

	private TextField tfLecturaAnterior = new TextField();

	public TextField getTfLecturaAnterior() {
		return tfLecturaAnterior;
	}

	public void setTfLecturaAnterior(TextField tfLecturaAnterior) {
		this.tfLecturaAnterior = tfLecturaAnterior;
	}

	private TextField tfLectura = new TextField();

	public TextField getTfLectura() {
		return tfLectura;
	}

	public void setTfLectura(TextField tf) {
		this.tfLectura = tf;
	}

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dtc) {
		this.dateTimeConverter1 = dtc;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Message message1 = new Message();

	public Message getMessage1() {
		return message1;
	}

	public void setMessage1(Message m) {
		this.message1 = m;
	}

	private StaticText mensaje = new StaticText();

	public StaticText getMensaje() {
		return mensaje;
	}

	public void setMensaje(StaticText mensaje) {
		this.mensaje = mensaje;
	}

	private Message message2 = new Message();

	public Message getMessage2() {
		return message2;
	}

	public void setMessage2(Message m) {

		this.message2 = m;
	}

	private TableColumn tableColumn8 = new TableColumn();

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tableColumn8) {
		this.tableColumn8 = tableColumn8;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AgregarValorMedidor() {
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
			log("AgregarValorMedidor Initialization Failure", e);
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
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama justo antes del procesamiento. <strong>S�lo</strong> se llamar� a este m�todo en la p�gina que se
	 * procesa, no se llamar�, por ejemplo, en una p�gina que ha procesado una devoluci�n de env�o y a continuaci�n ha navegado hasta otra p�gina. Puede
	 * personalizar este m�todo para asignar recursos necesarios para procesar esta p�gina.
	 * </p>
	 */
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

				cargarTabla = true;
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

			cargarTabla = true;
		}

		// 4. Pagina nueva - hacia atras en la transaccion
		if(existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());
		}

		if(!recarga) {
			try {

				this.mostrarEstadoObjetosUsados();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		if(cargarTabla) {
			try {
				this.refrescarTabla();

			} catch(Exception ex) {
				ex.printStackTrace();
				this.limpiarTabla();
			}
		}

		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	public void destroy() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">

	private ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, null); // ServicioOSP
		ep.getObjetos().add(ind++, null); // Calle
		ep.getObjetos().add(ind++, null); // Calendario
		ep.getObjetos().add(ind++, null); // Periodo
		ep.getObjetos().add(ind++, null); // Cuota
		ep.getObjetos().add(ind++, null); // Año
		ep.getObjetos().add(ind++, new ArrayList()); // ValorMedidor
		ep.getObjetos().add(ind++, null); // String de codigo Medidor

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		
		// Se limpia la lista de rubros porque siempre queda cargada cuando haces un Add...
        if(this.getListaDelCommunication() != null) {
        	this.getListaDelCommunication().clear();
        	this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
		
		return ep;
	}

	private void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		ServicioOSP servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
		Calle calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
		CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++);
		PeriodoLiquidacion periodo = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++);
		CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++);
		String anio = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
		ArrayList medicionesCargadas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		String codigoMedidor = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);

		Object objAnio = this.getDDObjectValue(getDdAnios(), this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalOSP());
		if(objAnio != null) {
			anio = objAnio.toString();
		}
		if(anio != null && anio.length() > 0) {
			calendario = this.getDDObjectValue(getDdCalendarios(), this.getCommunicationSAICBean().getMapaCalendariosOSP(anio));
		}
		if(calendario != null) {
			periodo = this.getDDObjectValue(getDdPeriodos(), this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalOSP(calendario.getNombre()));
		}
		if(periodo != null) {
			cuota = this.getDDObjectValue(getDdCuotas(), this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalOSP(periodo.toString()));
		}
		
		codigoMedidor = tfCodigoMedidor.getText().toString();

		this.getObjectListDataProvider().commitChanges();
		medicionesCargadas = (ArrayList) this.getObjectListDataProvider().getList();
		if(medicionesCargadas.size() <= 0)
			medicionesCargadas = null;
		this.setListaDelCommunication(medicionesCargadas);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, servicioOSP);
		this.getElementoPila().getObjetos().set(ind++, calle);
		this.getElementoPila().getObjetos().set(ind++, calendario);
		this.getElementoPila().getObjetos().set(ind++, periodo);
		this.getElementoPila().getObjetos().set(ind++, cuota);
		this.getElementoPila().getObjetos().set(ind++, anio);
		this.getElementoPila().getObjetos().set(ind++, medicionesCargadas);
		this.getElementoPila().getObjetos().set(ind++, codigoMedidor);
	}

	private void mostrarEstadoObjetosUsados() {
		int ind = 0;
		ServicioOSP servicioOSP = null;
		Calle calle = null;

		if(this.getRequestBean1().getObjetoSeleccion() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoSeleccion();

			if(seleccionado instanceof ServicioOSP) {
				servicioOSP = (ServicioOSP) seleccionado;
				this.getElementoPila().getObjetos().set(0, servicioOSP);
			}
			if(seleccionado instanceof Calle) {
				calle = (Calle) seleccionado;
				this.getElementoPila().getObjetos().set(1, calle);
			}
		}

		ind = 0;
		servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++);
		calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
		CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++);
		PeriodoLiquidacion periodo = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++);
		CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++);
		String anio = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
		ArrayList medicionesCargadas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		String codigoMedidor = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);

		if(servicioOSP != null && servicioOSP.getIdTipoAlicuota() != -1) {
			this.getTfServicioOSP().setText(servicioOSP.toString());
		}
		if(calle != null) {
			this.getTfCalle().setText(calle.toString());
		}

		if(anio != null) {
			seleccionarAnio(anio, true);
		}
		if(calendario != null && !calendario.getNombre().isEmpty()) {
			seleccionarCalendario(calendario.getNombre(), true);
		}
		if(periodo != null && !periodo.toString().isEmpty()) {
			seleccionarPeriodo(periodo.toString(), true);
		}
		if(cuota != null && !cuota.toString().isEmpty()) {
			seleccionarCuota(cuota.toString());
		}
		this.getTfCodigoMedidor().setText(codigoMedidor);

		this.getObjectListDataProvider().setList(medicionesCargadas);
		this.setListaDelCommunication(medicionesCargadas);
	}

	protected <T> T getDDObjectValue(DropDown pDropDown, Map<String, T> pMapa) {
		if(pDropDown.getSelected() != null && !pDropDown.getSelected().toString().isEmpty()) {
			return pMapa.get(pDropDown.getSelected().toString());
		} else
			return null;
	}

	private void refrescarTabla() throws Exception {
		try {
			int ind = 0;
			ServicioOSP servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
			Calle calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
			Periodo periodo = (Periodo) this.obtenerObjetoDelElementoPila(ind++, Periodo.class);
			String codigoMedidor = (String) this.obtenerObjetoDelElementoPila(4, String.class);

			if(periodo != null && periodo.getIdPeriodo() == -1) {
				periodo = null;
			}

			if(servicioOSP != null && servicioOSP.getIdTipoAlicuota() == -1) {
				servicioOSP = null;
			}
			if(calle != null && calle.getIdCalle() == -1) {
				calle = null;
			}

			// CAMBIAR: Utilizar el EJBClient adecuado.
			this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
			// Comentado
			// this.setListaDelCommunication((ArrayList)
			// this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getListaNuevasMedidasServiciosOSP(calle, periodo, servicioOSP, codigoMedidor));
			for(int i = 0; i < this.getListaDelCommunication().size(); i++) {
				ValorMedidor locValorMedidor = (ValorMedidor) this.getListaDelCommunication().get(i);
			}
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		} catch(RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpMedicionMedidorOSP();
	}

	private ArrayList getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaMedicionesMedidoresOSP();
	}

	private void setListaDelCommunication(ArrayList lista) {
		this.getCommunicationSAICBean().setListaMedicionesMedidoresOSP(lista);
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
		this.getRequestBean1().setCasoNavegacionPostCaducidad(CASO_NAV_POST_CADUCIDAD);
		return CASO_NAV_CADUCIDAD;
	}

	private boolean ultimoElementoPilaDeSubSesion() {
		return this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
	}

	protected Object obtenerObjetoDelElementoPila(int posicion) {
		Object objeto = null;
		try {
			objeto = this.getElementoPila().getObjetos().get(posicion);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return objeto;
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

	private void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
	}

	public void limpiarObjeto(int posicion, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, null);
			if(campo != null)
				campo.setText(null);
		} catch(Exception ex) {
		}
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	private int getNroFila(String pCadena) {
		// Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if(!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if(!encontrado)
					inicioPagina += cantRegistrosPorPagina;
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if(cantFilas > 0) {
			// si hay al menos una fila
			if(posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}
			;

			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {

		}
		return rk;
	}

	public void limpiarObjeto(int posicion, Class tipoClase, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, tipoClase.newInstance());
			campo.setText("");
		} catch(Exception ex) {
		}
	}

	public String btnSeleccionarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminCalle";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarServicioOSP_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminServicioOSP";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarServicio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(0, ServicioOSP.class, this.getTfServicioOSP());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(1, Calle.class, this.getTfCalle());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnCargarMedidores_action() throws RemoteException, TrascenderException {
		guardarEstadoObjetosUsados();
		Calle pCalle = null;
		CuotaLiquidacion pCuota = null;
		ServicioOSP pServicio = null;
		String pCodigoMedidor = null;

		pServicio = (ServicioOSP) this.obtenerObjetoDelElementoPila(0);
		pCalle = (Calle) this.obtenerObjetoDelElementoPila(1);
		pCuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(4);
		pCodigoMedidor = (String) this.obtenerObjetoDelElementoPila(7);
		
		if (pServicio == null || pServicio.getIdTipoAlicuota() == -1) {
			pServicio = null;
		}
		
		if (pCalle == null || pCalle.getIdCalle() == -1) {
			pCalle = null;
		}
		
		if (pCodigoMedidor == null || pCodigoMedidor.trim().isEmpty()) {
			pCodigoMedidor = null;
		}

		if(pCuota != null) {
			List medidores = null;
			this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
			medidores = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getListaNuevasMedidasServiciosOSP(pCalle, pCuota, pServicio, pCodigoMedidor);

			this.getObjectListDataProvider().setList(medidores);
			this.setListaDelCommunication((ArrayList) medidores);
		} else {
			warn("Debe seleccionar una cuota.");
		}
		return "";
	}

	public String btnGuardar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.guardarEstadoObjetosUsados();
				Validador v = new Validador();
				// Envio toda la lista de Mediciones al m�todo. Este toma la lista y las graba una por una..
				ArrayList medicionesCargadas = (ArrayList) this.obtenerObjetoDelElementoPila(6, ArrayList.class);
				
				UIComponent[] positivos = new UIComponent[medicionesCargadas.size()];
				String[] nomPositivos = new String[medicionesCargadas.size()];

				if(this.getListaDelCommunication() == null || this.getListaDelCommunication().size() == 0) {
					error("Existen Errores:");
					error("Debe cargar al menos un valor a los medidores.");
					return null;
				}
				
				boolean error = false;
				String msgFecha = "Alguna Fecha es Superior a la Actual. ";
				String msgPositivo = "No se Pueden Ingresar Valores Negativos. ";

				for(int i = 0; i < medicionesCargadas.size(); i++) {
					ValorMedidor locValorMedidor = (ValorMedidor) medicionesCargadas.get(i);
					if(!v.esPositivo(locValorMedidor.getMontoImponible().toString())) {
						error = true;
					}
				}
				if(error)
					v.getErrores().add(msgPositivo);

				error = false;
				Calendar fecha = Calendar.getInstance();

				for(int i = 0; i < medicionesCargadas.size(); i++) {
					ValorMedidor locValorMedidor = (ValorMedidor) medicionesCargadas.get(i);
					if(locValorMedidor.getFecha().after(fecha.getTime())) {
						error = true;
					}
				}
				if(error)
					v.getErrores().add(msgFecha);

				if(v.getErrores().size() > 0) {
					error("Existen Errores:");
					for(int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					return null;
				}
				// CAMBIAR: Utilizar el EJBClient adecuado.
				this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
				this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().addListaValorMedidor(medicionesCargadas);
				this.getRequestBean1().setRespuestaABM(null);

				if(medicionesCargadas.size() == 1)
					info("La Medici\363n del Medidor se agreg\363 exitosamente.");
				else
					info("Las Mediciones de los Medidores se agregaron exitosamente.");

				this.setListaDelCommunication(null);

				retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);

			} catch(NumberFormatException e) {
				log(CASO_NAVEGACION + "_GuardarError:", e);
				error(NOMBRE_PAGINA + " - Guardar: " + e.getMessage());
			} catch(Exception ex) {
				if(ex instanceof TrascenderException) {
					int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
					if(this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError))
						retorno = null;
					else
						retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
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

		if(ultimo) {

			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	public String btnSeleccionarParcela_action() {
		return null;
	}
	
	public String btnLimpiarParcela_action() {
		return null;
	}
}