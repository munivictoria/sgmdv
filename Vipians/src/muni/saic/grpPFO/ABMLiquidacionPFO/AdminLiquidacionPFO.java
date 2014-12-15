/*
 * AdminLiquidacionPFO.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.saic.grpPFO.ABMLiquidacionPFO;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import muni.saic.ABMReliquidacion.ActualizarDeudaModel;
import muni.saic.ABMReliquidacion.ReliquidarVariasModel;
import muni.saic.ImprimirLiquidaciones.LiquidacionModel;
import muni.saic.ImprimirLiquidaciones.ModificarVariasModel;
import muni.saic.ImprimirLiquidaciones.RegistroCancelacionManualModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.ImageComponent;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionPFO;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminLiquidacionPFO extends AdminPageBean {

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		Option[] opEstado = null;

		RegistroDeuda.EstadoRegistroDeuda[] estadosRegistroDeuda = new RegistroDeuda.EstadoRegistroDeuda[] {RegistroDeuda.EstadoRegistroDeuda.PAGADA,
				RegistroDeuda.EstadoRegistroDeuda.VENCIDA, RegistroDeuda.EstadoRegistroDeuda.VIGENTE, RegistroDeuda.EstadoRegistroDeuda.ANULADA,
				RegistroDeuda.EstadoRegistroDeuda.RELIQUIDADA, RegistroDeuda.EstadoRegistroDeuda.REFINANCIADA, RegistroDeuda.EstadoRegistroDeuda.NO_OPTADA};
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(estadosRegistroDeuda, "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);

		Option[] opTipo = null;
		RegistroDeuda.TipoDeuda[] tipos = new RegistroDeuda.TipoDeuda[] {RegistroDeuda.TipoDeuda.LIQUIDACION, RegistroDeuda.TipoDeuda.RELIQUIDACION,
				RegistroDeuda.TipoDeuda.REFINANCIACION};
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(tipos, "cap");
		ddTipoDefaultOptions.setOptions(opTipo);

		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalPFO().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosPFO(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalPFO(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalPFO(null).keySet());

		this.habilitarBtnExportar();

		String opcion = getAnioCorriente();
		seleccionarAnio(opcion, true);
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
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosPFO(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalPFO(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalPFO(pPeriodo).keySet());
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

	private Button btnSeleccionarTodas = new Button();
	private Button btnDeseleccionarTodas = new Button();
	private Set listaSeleccionados = new HashSet();
	private Label lblTipo = new Label();
	private Label lblOmitir = new Label();
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();
	private Checkbox chkOmitir = new Checkbox();
	private Button btnMarcarPagada = new Button();
	private Button btnMarcarImpaga = new Button();
	private Button btnModificarLiquidacion = new Button();
	private Button btnEliminarLiquidacion = new Button();
	private HtmlAjaxCommandButton btnImprimirEnServidor = new HtmlAjaxCommandButton();

	public Button getBtnDeseleccionarTodas() {
		return btnDeseleccionarTodas;
	}

	public void setBtnDeseleccionarTodas(Button btnDeseleccionarTodas) {
		this.btnDeseleccionarTodas = btnDeseleccionarTodas;
	}

	public Button getBtnSeleccionarTodas() {
		return btnSeleccionarTodas;
	}

	public void setBtnSeleccionarTodas(Button btnSeleccionarTodas) {
		this.btnSeleccionarTodas = btnSeleccionarTodas;
	}

	public Set getListaSeleccionados() {
		return listaSeleccionados;
	}

	public void setListaSeleccionados(Set listaSeleccionados) {
		this.listaSeleccionados = listaSeleccionados;
	}

	public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public Label getLblOmitir() {
		return lblOmitir;
	}

	public void setLblOmitir(Label lblOmitir) {
		this.lblOmitir = lblOmitir;
	}

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown ddTipo) {
		this.ddTipo = ddTipo;
	}

	public SingleSelectOptionsList getDdTipoDefaultOptions() {
		return ddTipoDefaultOptions;
	}

	public void setDdTipoDefaultOptions(SingleSelectOptionsList ddTipoDefaultOptions) {
		this.ddTipoDefaultOptions = ddTipoDefaultOptions;
	}

	public Checkbox getChkOmitir() {
		return chkOmitir;
	}

	public void setChkOmitir(Checkbox chkOmitir) {
		this.chkOmitir = chkOmitir;
	}

	public Button getBtnMarcarPagada() {
		return btnMarcarPagada;
	}

	public void setBtnMarcarPagada(Button btnMarcarPagada) {
		this.btnMarcarPagada = btnMarcarPagada;
	}

	public Button getBtnMarcarImpaga() {
		return btnMarcarImpaga;
	}

	public void setBtnMarcarImpaga(Button btnMarcarImpaga) {
		this.btnMarcarImpaga = btnMarcarImpaga;
	}

	public Button getBtnModificarLiquidacion() {
		return btnModificarLiquidacion;
	}

	public void setBtnModificarLiquidacion(Button btnModificarLiquidacion) {
		this.btnModificarLiquidacion = btnModificarLiquidacion;
	}

	public Button getBtnEliminarLiquidacion() {
		return btnEliminarLiquidacion;
	}

	public void setBtnEliminarLiquidacion(Button btnEliminarLiquidacion) {
		this.btnEliminarLiquidacion = btnEliminarLiquidacion;
	}

	public HtmlAjaxCommandButton getBtnImprimirEnServidor() {
		return btnImprimirEnServidor;
	}

	public void setBtnImprimirEnServidor(HtmlAjaxCommandButton btnImprimirEnServidor) {
		this.btnImprimirEnServidor = btnImprimirEnServidor;
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	protected PanelGroup pgParametros = new PanelGroup();

	public PanelGroup getPgParametros() {
		return pgParametros;
	}

	public void setPgParametros(PanelGroup pgParametros) {
		this.pgParametros = pgParametros;
	}

	private ObjectListDataProvider ldpLiquidacionPFO = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLiquidacionPFO() {
		return ldpLiquidacionPFO;
	}

	public void setLdpLiquidacionPFO(ObjectListDataProvider oldp) {
		this.ldpLiquidacionPFO = oldp;
	}

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup pg) {
		this.gpBotones = pg;
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

	private PanelGroup gpReliquidacion1 = new PanelGroup();

	public PanelGroup getGpReliquidacion1() {
		return gpReliquidacion1;
	}

	public void setGpReliquidacion1(PanelGroup pg) {
		this.gpReliquidacion1 = pg;
	}

	// ---------------StaticTexts---------------
	private StaticText stTitulo = new StaticText();
	private StaticText staticText2 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText8 = new StaticText();
	private StaticText staticText4 = new StaticText();
	private StaticText staticText1 = new StaticText();
	private StaticText staticText13 = new StaticText();
	private StaticText staticText14 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText3 = new StaticText();
	private StaticText staticText9 = new StaticText();
	private StaticText staticText10 = new StaticText();
	private StaticText staticText11 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText15 = new StaticText();

	public StaticText getStaticText15() {
		return staticText15;
	}

	public void setStaticText15(StaticText staticText15) {
		this.staticText15 = staticText15;
	}

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText staticText14) {
		this.staticText14 = staticText14;
	}

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText st) {
		this.staticText10 = st;
	}

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private TextArea taObligacion = new TextArea();

	public TextArea getTaObligacion() {
		return taObligacion;
	}

	public void setTaObligacion(TextArea taObligacion) {
		this.taObligacion = taObligacion;
	}

	// ---------------Labels---------------
	private Label label1 = new Label();
	private PanelGroup groupPanel1 = new PanelGroup();
	private Label label2 = new Label();
	private Label label8 = new Label();
	private Label label3 = new Label();
	private Label label7 = new Label();
	private Label label9 = new Label();
	private Label lblFechaReliquidacion = new Label();
	private Label lblEstado = new Label();
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

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label l) {
		this.label8 = l;
	}

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	public Label getLabel9() {
		return label9;
	}

	public void setLabel9(Label l) {
		this.label9 = l;
	}

	public Label getLblFechaReliquidacion() {
		return lblFechaReliquidacion;
	}

	public void setLblFechaReliquidacion(Label l) {
		this.lblFechaReliquidacion = l;
	}

	// ---------------Buttons---------------
	private Button btnSeleccionarPersonaJuridica = new Button();
	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarCuadra = new Button();
	private Button btnSeleccionarCalle = new Button();
	private Button btnSeleccionarObra = new Button();
	private Button btnGenerarLiquidaciones = new Button();
	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	private Button btnImprimir = new Button();
	private Button btnReliquidar = new Button();
	private Button btnActualizarDeuda = new Button();
	private HtmlAjaxCommandButton btnLimpiarObra = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarCuadra = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarCalle = new HtmlAjaxCommandButton();

	public Button getBtnActualizarDeuda() {
		return btnActualizarDeuda;
	}

	public void setBtnActualizarDeuda(Button btnActualizarDeuda) {
		this.btnActualizarDeuda = btnActualizarDeuda;
	}

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public HtmlAjaxCommandButton getBtnLimpiarObra() {
		return btnLimpiarObra;
	}

	public void setBtnLimpiarObra(HtmlAjaxCommandButton btnLimpiarObra) {
		this.btnLimpiarObra = btnLimpiarObra;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCuadra() {
		return btnLimpiarCuadra;
	}

	public void setBtnLimpiarCuadra(HtmlAjaxCommandButton btnLimpiarCuadra) {
		this.btnLimpiarCuadra = btnLimpiarCuadra;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCalle() {
		return btnLimpiarCalle;
	}

	public void setBtnLimpiarCalle(HtmlAjaxCommandButton btnLimpiarCalle) {
		this.btnLimpiarCalle = btnLimpiarCalle;
	}

	public Button getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(Button btnExportar) {
		this.btnExportar = btnExportar;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	public Button getBtnSeleccionarCuadra() {
		return btnSeleccionarCuadra;
	}

	public void setBtnSeleccionarCuadra(Button b) {
		this.btnSeleccionarCuadra = b;
	}

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button b) {
		this.btnSeleccionarCalle = b;
	}

	public Button getBtnSeleccionarObra() {
		return btnSeleccionarObra;
	}

	public void setBtnSeleccionarObra(Button b) {
		this.btnSeleccionarObra = b;
	}

	public Button getBtnGenerarLiquidaciones() {
		return btnGenerarLiquidaciones;
	}

	public void setBtnGenerarLiquidaciones(Button b) {
		this.btnGenerarLiquidaciones = b;
	}

	public Button getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(Button b) {
		this.btnImprimir = b;
	}

	public Button getBtnReliquidar() {
		return btnReliquidar;
	}

	public void setBtnReliquidar(Button b) {
		this.btnReliquidar = b;
	}

	// ---------------TextFields---------------
	private TextField tfPersona = new TextField();
	private TextField tfCuadra = new TextField();
	private TextField tfFechaReliquidacion = new TextField();
	private TextField tfObra = new TextField();
	private TextField tfCalle = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	public TextField getTfCuadra() {
		return tfCuadra;
	}

	public void setTfCuadra(TextField tf) {
		this.tfCuadra = tf;
	}

	public TextField getTfFechaReliquidacion() {
		return tfFechaReliquidacion;
	}

	public void setTfFechaReliquidacion(TextField tf) {
		this.tfFechaReliquidacion = tf;
	}

	public TextField getTfObra() {
		return tfObra;
	}

	public void setTfObra(TextField tf) {
		this.tfObra = tf;
	}

	public TextField getTfCalle() {
		return tfCalle;
	}

	public void setTfCalle(TextField tf) {
		this.tfCalle = tf;
	}

	// --------------- DropDowns---------------
	private DropDown ddEstado = new DropDown();
	private DropDown ddCalendarios = new DropDown();
	private DropDown ddPeriodos = new DropDown();
	private DropDown ddCuotas = new DropDown();
	private DropDown ddAnios = new DropDown();

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

	public DropDown getDdCuotas() {
		return ddCuotas;
	}

	public void setDdCuotas(DropDown ddCuotas) {
		this.ddCuotas = ddCuotas;
	}

	public DropDown getDdPeriodos() {
		return ddPeriodos;
	}

	public void setDdPeriodos(DropDown ddPeriodos) {
		this.ddPeriodos = ddPeriodos;
	}

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	// ---------------SingleSelectedOptionsLists---------------
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddAniosOptions = new SingleSelectOptionsList();

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

	public SingleSelectOptionsList getDdCuotasOptions() {
		return ddCuotasOptions;
	}

	public void setDdCuotasOptions(SingleSelectOptionsList ddCuotasOptions) {
		this.ddCuotasOptions = ddCuotasOptions;
	}

	public SingleSelectOptionsList getDdPeriodosOptions() {
		return ddPeriodosOptions;
	}

	public void setDdPeriodosOptions(SingleSelectOptionsList ddPeriodosOptions) {
		this.ddPeriodosOptions = ddPeriodosOptions;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminLiquidacionPFO() {
	}

	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		ep.setSeleccionMultiple(true);

		return ep;
	}

	private String getAnioCorriente() {
		String anioActual = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString();
		Integer locAnioActualMapa = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalPFO().get(anioActual);
		if(locAnioActualMapa != null) {
			return anioActual;
		} else {
			return this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalPFO().keySet().iterator().next();
		}
	}

	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		FiltroLiquidacionPFO locFiltro = this.getFiltro();

		Object estadoSelected = this.getDdEstado().getSelected();
		if((estadoSelected != null) && (estadoSelected.toString().length() > 0)) {
			locFiltro.setEstadoLiquidacion(RegistroDeuda.EstadoRegistroDeuda.valueOf(estadoSelected.toString()));
		} else {
			locFiltro.setEstadoLiquidacion(null);
		}

		Object tipoSelected = this.getDdTipo().getSelected();
		if((tipoSelected != null) && (tipoSelected.toString().length() > 0)) {
			locFiltro.setTipoLiquidacion(RegistroDeuda.TipoDeuda.valueOf(tipoSelected.toString()));
		} else {
			locFiltro.setTipoLiquidacion(null);
		}

		if(this.getDdAnios().getSelected() != null) {
			String anioSeleccionado = this.getDdAnios().getSelected().toString();
			Integer locAnio = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalPFO().get(anioSeleccionado);
			locFiltro.setAnio(locAnio);

			String calendarioSeleccionado = this.getDdCalendarios().getSelected().toString();
			CalendarioMunicipal locCalendario = this.getCommunicationSAICBean().getMapaCalendariosPFO(anioSeleccionado).get(calendarioSeleccionado);
			locFiltro.setCalendario(locCalendario);

			String periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected().toString();
			PeriodoLiquidacion locPeriodo = this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalPFO(calendarioSeleccionado).get(periodoCalendarioSeleccionado);
			locFiltro.setPeriodo(locPeriodo);

			String cuotaSeleccionada = this.getDdCuotas().getSelected().toString();
			CuotaLiquidacion locCuota = this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalPFO(periodoCalendarioSeleccionado).get(cuotaSeleccionada);
			locFiltro.setCuota(locCuota);
		}

		locFiltro.setNoCero(this.getChkOmitir().isChecked());

		if(locFiltro.getListaAtributosDinamicos() != null) {
			List listaAtributosDinamicos = panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());
			locFiltro.setListaAtributosDinamicos(listaAtributosDinamicos);
		}
	}

	protected void mostrarEstadoObjetosUsados() {
		FiltroLiquidacionPFO locFiltro = this.getFiltro();

		if(locFiltro.getPersona() != null && locFiltro.getPersona().getIdPersona() != -1) {
			this.getTfPersona().setText(locFiltro.getPersona().toString());
		}
		if(locFiltro.getObra() != null && locFiltro.getObra().getIdObra() != -1) {
			this.getTfObra().setText(locFiltro.getObra().toString());
		}
		if(locFiltro.getCuadra() != null && locFiltro.getCuadra().getIdCuadra() != -1) {
			this.getTfCuadra().setText(locFiltro.getCuadra().toString());
		}
		if(locFiltro.getCalle() != null && locFiltro.getCalle().getIdCalle() != -1) {
			this.getTfCalle().setText(locFiltro.getCalle().toString());
		}

		if(locFiltro.getAnio() != null && !locFiltro.getAnio().toString().isEmpty()) {
			seleccionarAnio(locFiltro.getAnio().toString(), true);
		}
		if(locFiltro.getCalendario() != null && !locFiltro.getCalendario().getNombre().isEmpty()) {
			seleccionarCalendario(locFiltro.getCalendario().getNombre(), true);
		}
		if(locFiltro.getPeriodo() != null && !locFiltro.getPeriodo().toString().isEmpty()) {
			seleccionarPeriodo(locFiltro.getPeriodo().toString(), true);
		}
		if(locFiltro.getCuota() != null && !locFiltro.getCuota().getNombre().isEmpty()) {
			seleccionarCuota(locFiltro.getCuota().getNombre());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstadoLiquidacion())));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstadoLiquidacion())));

		this.getDdTipo().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoLiquidacion())));
		this.getDdTipoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoLiquidacion())));

		this.getChkOmitir().setSelected(locFiltro.isNoCero());

		if(locFiltro.getListaAtributosDinamicos() != null && locFiltro.getListaAtributosDinamicos().isEmpty()) {
			try {
				List listaAtributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoPlanObra.serialVersionUID, null, true);
				if(listaAtributosDinamicos != null) {
					locFiltro.setListaAtributosDinamicos(listaAtributosDinamicos);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(), "#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());
	}

	protected void limpiarObjetosUsados() {
		FiltroLiquidacionPFO locFiltro = this.getFiltro();
		locFiltro.setPersona(null);
		locFiltro.setCalle(null);
		locFiltro.setObra(null);
		locFiltro.setCuadra(null);
		locFiltro.setEstadoLiquidacion(null);
		locFiltro.setTipoLiquidacion(null);
		locFiltro.setNoCero(false);
		locFiltro.setListaAtributosDinamicos(null);
		locFiltro.setAnio(null);
		locFiltro.setCalendario(null);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);

		this.setListaSeleccionados(new HashSet());
		this.getPaginatedTable().setSeleccionadosSeleccionMultiple(new ArrayList());

		this.getSessionBean1().setPersonaSeleccionada(null);

		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfPersona().setText(null);
		this.getTfObra().setText(null);
		this.getTfCuadra().setText(null);
		this.getTfCalle().setText(null);
		this.getTfFechaReliquidacion().setText(null);
		this.getDdEstado().setSelected(null);
		this.getDdEstadoDefaultOptions().setSelectedValue(null);
		this.getDdTipo().setSelected(null);
		this.getDdTipoDefaultOptions().setSelectedValue(null);

		this.getChkOmitir().setSelected(false);
		this.panelAtributoDinamico.limpiarCampos();
		this.seleccionarAnio(this.getAnioCorriente().toString(), true);
	}

	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLiquidacionPFO();
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaLiquidacionesPFO();
	}

	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaLiquidacionesPFO(lista);
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnSeleccionarObra_action() {
		return navegarParaSeleccionar("AdminObra");
	}

	public String btnSeleccionarCuadra_action() {
		return navegarParaSeleccionar("AdminCuadra");
	}

	public String btnSeleccionarCalle_action() {
		return navegarParaSeleccionar("AdminCalle");
	}

	public String btnGenerarLiquidaciones_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			retorno = "GenerarLiquidacionPFO";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnActualizarDeuda_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			List seleccionados = new ArrayList();
			if(this.getElementoPila().isSeleccionMultiple()) {
				seleccionados = new ArrayList(this.getPaginatedTable().getSeleccionadosSeleccionMultiple());
			} else {
				try {
					RowKey rk = this.getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());
						Object obj = this.getObjectListDataProvider().getObjects()[index];
						seleccionados.add(obj);

						this.guardarOrdenamiento();
						Long pos = this.getPosicionEnTabla(rk);
						this.getElementoPila().setPosicionGlobal(pos.longValue());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(seleccionados.size() > 0) {
				List<LiquidacionTasa> locListaLiquidaciones = seleccionados;
				List<LiquidacionTasa> listaLiquidaciones = new ArrayList<LiquidacionTasa>();

				for(LiquidacionTasa cadaLiquidacion : locListaLiquidaciones) {
					if(!cadaLiquidacion.getEstado().equals(EstadoRegistroDeuda.VIGENTE) && !cadaLiquidacion.getEstado().equals(EstadoRegistroDeuda.VENCIDA)) {
						warn("Solo pueden actualizarse deudas vigentes o vencidas.");
						return null;
					}

					try {
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
						listaLiquidaciones.add(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getLiquidacionTasaPorId(cadaLiquidacion.getIdRegistroDeuda()));
					} catch(RemoteException e) {
						e.printStackTrace();
					} catch(TrascenderException e) {
						e.printStackTrace();
					}
				}

				this.getRequestBean1().setObjetoABM(listaLiquidaciones);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new ActualizarDeudaModel().new ActualizarController());

				retorno = "ABMActualizarDeuda";
			} else {
				warn("Debe seleccionar una liquidación.");
				return null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnConsultar_action() {
		return toAbm(new LiquidacionPFOModel().new ConsultarController());
	}

	public String btnImprimir_action() {
		// CAMBIAR:
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// ariel - no guardar. utilizar lo ya guardado (con resultado de la busqueda)
			// this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			try {
				int ind = 0;
				Persona persona = this.getSessionBean1().getPersonaSeleccionada();
				Obra obra = (Obra) this.obtenerObjetoDelElementoPila(ind++, Obra.class);
				Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(ind++, Cuadra.class);
				Calle calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);

				CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
				PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
				CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);

				RegistroDeuda.EstadoRegistroDeuda estadoRegistroDeuda = (RegistroDeuda.EstadoRegistroDeuda) this.obtenerObjetoDelElementoPila(ind++,
						RegistroDeuda.EstadoRegistroDeuda.class);
				Integer locCuotaDesde = (Integer) this.obtenerObjetoDelElementoPila(8, Integer.class);
				Integer locCuotaHasta = (Integer) this.obtenerObjetoDelElementoPila(9, Integer.class);

				if(obra.getIdObra() == -1) {
					obra = null;
				}
				if(cuadra.getIdCuadra() == -1) {
					cuadra = null;
				}
				if(calle.getIdCalle() == -1) {
					calle = null;
				}

				if(locCuotaDesde == null) {
					locCuotaDesde = new Integer(0);
				}

				if(locCuotaHasta == null) {
					locCuotaHasta = new Integer(0);
				}

				// ariel - descomentar cuando este listo
				// this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
				// this.getCommunicationSAICBean().getRemoteSystemImpresion().imprimirReportePFO(persona, obra, cuadra, calle, periodo, estadoRegistroDeuda,new
				// Integer(locCuotaDesde.toString()),new Integer(locCuotaHasta.toString()));

				this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
				// JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReportePFO(persona, obra, cuadra, calle, cuota,
				// estadoRegistroDeuda, RegistroDeuda.TipoDeuda.LIQUIDACION, locCuotaDesde, locCuotaHasta);

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_LiquidacionPFO");
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

			} catch(Exception ex) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log("AdminLiquidacionPFO" + "_ImprimirLiquidacionesPFO", ex);
				error("Administraci\363n de Liquidaciones de PFO" + " - Imprimir: " + ex.getMessage());
			}

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getTfPersona().setText("");
			this.getSessionBean1().setPersonaSeleccionada(null);
			FiltroLiquidacionPFO locFiltro = this.getFiltro();
			locFiltro.setPersona(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarObra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getTfObra().setText("");
			FiltroLiquidacionPFO locFiltro = this.getFiltro();
			locFiltro.setObra(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getTfCuadra().setText("");
			FiltroLiquidacionPFO locFiltro = this.getFiltro();
			locFiltro.setCuadra(null);

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
			this.getTfCalle().setText("");
			FiltroLiquidacionPFO locFiltro = this.getFiltro();
			locFiltro.setCalle(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnExportar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Liquidaciones PFO");

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_LiquidacionesPFO");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

			} catch(Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log("AdminLiquidacionPFO" + "_ReporteDinamicoError: ", e);
				error("Administraci\363n de Liquidaciones de PFO" + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarTodas_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		if(ultimo) {
			this.setListaSeleccionados(new HashSet(this.getObjectListDataProvider().getList()));

			List<RowKey> rowKeys = new ArrayList();
			if(this.getListaDelCommunication() != null) {
				for(int i = 0; i < this.getListaDelCommunication().size(); i++) {
					RowKey rowK = new RowKey(i + "");
					rowKeys.add(rowK);
					this.setSelected(i + "");
				}
			}
			this.getPaginatedTable().setSeleccionadosSeleccionMultiple(new ArrayList(listaSeleccionados));
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnDeseleccionarTodas_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		if(ultimo) {
			this.setListaSeleccionados(new HashSet());

			this.getPaginatedTable().setSeleccionadosSeleccionMultiple(new ArrayList(listaSeleccionados));
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnReliquidar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			Object obj = null;

			this.guardarEstadoObjetosUsados();
			List seleccionados = new ArrayList();
			if(this.getElementoPila().isSeleccionMultiple()) {
				seleccionados = new ArrayList(this.getPaginatedTable().getSeleccionadosSeleccionMultiple());
			} else {
				try {
					rk = this.getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());
						seleccionados.add(this.getObjectListDataProvider().getObjects()[index]);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(seleccionados.size() == 1) {
				obj = seleccionados.get(0);

				this.guardarEstadoObjetosUsados();
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				this.guardarOrdenamiento();
				Long pos = this.getPosicionEnTabla(rk);
				this.getElementoPila().setPosicionGlobal(pos.longValue());
				LiquidacionTasa locLiquidacion = (LiquidacionTasa) obj;
				if(locLiquidacion.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VIGENTE) || (locLiquidacion.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA))
						|| (locLiquidacion.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.NO_OPTADA))) {
					try {
						obj = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getLiquidacionTasaPorId(locLiquidacion.getIdRegistroDeuda());
					} catch(RemoteException e) {
						e.printStackTrace();
					} catch(TrascenderException e) {
						e.printStackTrace();
					}
					this.getRequestBean1().setObjetoABM(obj);
					retorno = "GenerarReliquidacion";
				} else {
					warn("S\363lo pueden reliquidarse obligaciones Vigentes, Vencidas y No Optadas.");
					retorno = null;
				}
			} else if(seleccionados.size() > 1) {
				List<LiquidacionTasa> locListaLiquidaciones = seleccionados;
				List<LiquidacionTasa> listaLiquidaciones = new ArrayList<LiquidacionTasa>();

				this.guardarEstadoObjetosUsados();
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				this.guardarOrdenamiento();
				Long pos = this.getPosicionEnTabla(rk);
				this.getElementoPila().setPosicionGlobal(pos.longValue());

				for(LiquidacionTasa cadaLiquidacion : locListaLiquidaciones) {
					if(!(cadaLiquidacion.getEstado().equals(EstadoRegistroDeuda.VIGENTE)) && !(cadaLiquidacion.getEstado().equals(EstadoRegistroDeuda.VENCIDA))
							&& !(cadaLiquidacion.getEstado().equals(EstadoRegistroDeuda.NO_OPTADA))) {
						warn("S\363lo pueden reliquidarse obligaciones Vigentes, Vencidas y No Optadas.");
						return null;
					}
					try {
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
						listaLiquidaciones.add(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getLiquidacionTasaPorId(cadaLiquidacion.getIdRegistroDeuda()));
					} catch(RemoteException e) {
						e.printStackTrace();
					} catch(TrascenderException e) {
						e.printStackTrace();
					}
				}

				this.getRequestBean1().setObjetoABM(listaLiquidaciones);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new ReliquidarVariasModel().new ReliquidarVariasController());
				retorno = "ReliquidarVarias";
			} else {
				warn("Debe seleccionar una liquidación.");
				return null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public boolean isRenderPanelAdminLiquidaciones() {
		return this.getCommunicationSAICBean().getEsAdministradorLiquidacionesPFO();
	}

	public String btnMarcarPagada_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			List seleccionados = new ArrayList();
			if(this.getElementoPila().isSeleccionMultiple()) {
				seleccionados = new ArrayList(this.getPaginatedTable().getSeleccionadosSeleccionMultiple());
			} else {
				try {
					RowKey rk = this.getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());
						Object obj = this.getObjectListDataProvider().getObjects()[index];
						seleccionados.add(obj);

						this.guardarOrdenamiento();
						Long pos = this.getPosicionEnTabla(rk);
						this.getElementoPila().setPosicionGlobal(pos.longValue());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(seleccionados.size() > 0) {
				List<LiquidacionTasa> locListaLiquidaciones = seleccionados;
				List<LiquidacionTasa> listaLiquidaciones = new ArrayList<LiquidacionTasa>();

				for(LiquidacionTasa cadaLiquidacion : locListaLiquidaciones) {
					if(cadaLiquidacion.getEstado().equals(EstadoRegistroDeuda.PAGADA) || cadaLiquidacion.getEstado().equals(EstadoRegistroDeuda.RELIQUIDADA)) {
						warn("Las Liquidaciones seleccionadas no deben estar en estado PAGADA");
						return null;
					}

					try {
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
						listaLiquidaciones.add(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getLiquidacionTasaPorId(cadaLiquidacion.getIdRegistroDeuda()));
					} catch(RemoteException e) {
						e.printStackTrace();
					} catch(TrascenderException e) {
						e.printStackTrace();
					}
				}

				this.getRequestBean1().setObjetoSeleccion(listaLiquidaciones);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new RegistroCancelacionManualModel().new AgregarRegCancelacionController());

				retorno = "ABMRegistroCancelacionManual";
			} else {
				warn("Debe seleccionar una liquidación");
				return null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnMarcarImpaga_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			List seleccionados = new ArrayList();

			if(this.getElementoPila().isSeleccionMultiple()) {
				seleccionados = new ArrayList(this.getPaginatedTable().getSeleccionadosSeleccionMultiple());
			} else {
				try {
					RowKey rk = this.getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());
						Object obj = this.getObjectListDataProvider().getObjects()[index];
						seleccionados.add(obj);

						this.guardarOrdenamiento();
						Long pos = this.getPosicionEnTabla(rk);
						this.getElementoPila().setPosicionGlobal(pos.longValue());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(seleccionados.size() > 0) {
				List<LiquidacionTasa> locListaLiquidaciones = seleccionados;
				List<LiquidacionTasa> listaLiquidaciones = new ArrayList<LiquidacionTasa>();

				for(LiquidacionTasa cadaLiquidacion : locListaLiquidaciones) {
					if(!cadaLiquidacion.getEstado().equals(EstadoRegistroDeuda.PAGADA)) {
						warn("Las Liquidaciones seleccionadas deben estar en estado PAGADA");
						return null;
					}
					try {
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
						listaLiquidaciones.add(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getLiquidacionTasaPorId(cadaLiquidacion.getIdRegistroDeuda()));
					} catch(RemoteException e) {
						e.printStackTrace();
					} catch(TrascenderException e) {
						e.printStackTrace();
					}
				}

				this.getRequestBean1().setObjetoSeleccion(listaLiquidaciones);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new RegistroCancelacionManualModel().new MarcarImpagaController());

				retorno = "ABMRegistroCancelacionManual";
			} else {
				warn("Debe seleccionar una liquidación");
				return null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarLiquidacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			List seleccionados = new ArrayList();

			if(this.getElementoPila().isSeleccionMultiple()) {
				seleccionados = new ArrayList(this.getPaginatedTable().getSeleccionadosSeleccionMultiple());
			} else {
				try {
					RowKey rk = this.getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());
						Object obj = this.getObjectListDataProvider().getObjects()[index];
						seleccionados.add(obj);

						this.guardarOrdenamiento();
						Long pos = this.getPosicionEnTabla(rk);
						this.getElementoPila().setPosicionGlobal(pos.longValue());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			List<LiquidacionTasa> locListaLiquidaciones = seleccionados;
			List<LiquidacionTasa> listaLiquidaciones = new ArrayList<LiquidacionTasa>();

			for(LiquidacionTasa cadaLiquidacion : locListaLiquidaciones) {
				try {
					this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
					listaLiquidaciones.add(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getLiquidacionTasaPorId(cadaLiquidacion.getIdRegistroDeuda()));
				} catch(RemoteException e) {
					e.printStackTrace();
				} catch(TrascenderException e) {
					e.printStackTrace();
				}
			}

			if(listaLiquidaciones.size() == 1) {
				// this.getRequestBean1().setObjetosSeleccionMultiple((ArrayList) liquidaciones);
				this.getRequestBean1().setObjetoABM(listaLiquidaciones);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new LiquidacionModel().new ModificarLiquidacionController());

				retorno = "ABMLiquidacion";
			} else if(listaLiquidaciones.size() > 1) {
				this.getRequestBean1().setObjetoABM(listaLiquidaciones);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new ModificarVariasModel().new ModificarVariasController());

				retorno = "ModificarVarias";
			} else {
				warn("Debe seleccionar una liquidación");
				return null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnEliminarLiquidacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			List seleccionados = new ArrayList();

			if(this.getElementoPila().isSeleccionMultiple()) {
				seleccionados = new ArrayList(this.getPaginatedTable().getSeleccionadosSeleccionMultiple());
			} else {
				try {
					RowKey rk = this.getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());
						Object obj = this.getObjectListDataProvider().getObjects()[index];
						seleccionados.add(obj);

						this.guardarOrdenamiento();
						Long pos = this.getPosicionEnTabla(rk);
						this.getElementoPila().setPosicionGlobal(pos.longValue());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(seleccionados.size() > 0) {
				this.getRequestBean1().setObjetoSeleccion(seleccionados);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new RegistroCancelacionManualModel().new EliminarRegCancelacionController());

				retorno = "ABMRegistroCancelacionManual";
			} else {
				warn("Debe seleccionar una liquidación");
				return null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnImprimirEnServidor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Usuario usuario = this.getSessionBean1().getUsuario();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			try {
				this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());

				List<LiquidacionTasa> locListaLiquidaciones = this.getListaDelCommunication();
				locListaLiquidaciones = this.quitarLiquidacionesPagadas(locListaLiquidaciones);
				this.getCommunicationSAICBean().getRemoteSystemImpresion().imprimirLiquidacionesTasasEnServidor(locListaLiquidaciones, usuario);
			} catch(Exception ex) {
				log("AdminLiquidacionPFO" + "_ImprimirLiquidacionesSHPS", ex);
				error("Administraci\363n de Liquidaciones de PFO" + " - Imprimir: " + ex.getMessage());
			}
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	private List<LiquidacionTasa> quitarLiquidacionesPagadas(List<LiquidacionTasa> pListaLiquidaciones) {
		List<LiquidacionTasa> locListaResultado = new ArrayList<LiquidacionTasa>();
		for(Iterator<LiquidacionTasa> it = pListaLiquidaciones.iterator(); it.hasNext();) {
			LiquidacionTasa liquidacionTasa = it.next();
			if(!liquidacionTasa.getEstado().equals("PAGADA") && !liquidacionTasa.getEstado().equals("NO_OPTADA")) {
				locListaResultado.add(liquidacionTasa);
			}
		}
		return locListaResultado;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		LiquidacionTasa locLiquidacion = (LiquidacionTasa) pObject;
		getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
		locLiquidacion = getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getLiquidacionTasaPorId(locLiquidacion.getId());
		return locLiquidacion;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
		FiltroLiquidacionPFO filtro = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaLiquidacionesPFO((FiltroLiquidacionPFO) pFiltro);
		return filtro;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationSAICBean().getTablaLiquidacionPFO();
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroLiquidacionPFO locFiltro = this.getFiltro();

		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getSessionBean1().setPersonaSeleccionada(persona);
			locFiltro.setPersona(persona);
		}
		if(pObject instanceof Obra) {
			Obra obra = (Obra) pObject;
			locFiltro.setObra(obra);
		}
		if(pObject instanceof Cuadra) {
			Cuadra cuadra = (Cuadra) pObject;
			locFiltro.setCuadra(cuadra);
		}
		if(pObject instanceof Calle) {
			Calle calle = (Calle) pObject;
			locFiltro.setCalle(calle);
		}
		this.getRequestBean1().setObjetoSeleccion(null);
	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Liquidaciones de Plan de Fianciación de Obras";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminLiquidacionPFO";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroLiquidacionPFO locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona locPersona = null;

		try {
			locPersona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		locFiltro.setPersona(locPersona);
		this.getSessionBean1().setPersonaSeleccionada(locPersona);
	}

	@Override
	public String getNombreBean() {
		return "#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO}";
	}

	@Override
	public long getSerialVersionUID() {
		return LiquidacionTasa.codigoPFO;
	}
}