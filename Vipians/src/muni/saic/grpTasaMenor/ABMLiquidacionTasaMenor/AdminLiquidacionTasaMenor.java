/*
 * AdminLiquidacionTasaMenor.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.saic.grpTasaMenor.ABMLiquidacionTasaMenor;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaMenor;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminLiquidacionTasaMenor extends AdminPageBean {

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

		this.habilitarBtnExportar();

		Option[] opEstado = null;

		RegistroDeuda.EstadoRegistroDeuda[] estados = new RegistroDeuda.EstadoRegistroDeuda[] {RegistroDeuda.EstadoRegistroDeuda.PAGADA, RegistroDeuda.EstadoRegistroDeuda.VENCIDA,
				RegistroDeuda.EstadoRegistroDeuda.VIGENTE, RegistroDeuda.EstadoRegistroDeuda.ANULADA, RegistroDeuda.EstadoRegistroDeuda.RELIQUIDADA,
				RegistroDeuda.EstadoRegistroDeuda.REFINANCIADA, RegistroDeuda.EstadoRegistroDeuda.NO_OPTADA};
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(estados, "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);

		Option[] opTipo = null;
		RegistroDeuda.TipoDeuda[] tipos = new RegistroDeuda.TipoDeuda[] {RegistroDeuda.TipoDeuda.LIQUIDACION, RegistroDeuda.TipoDeuda.RELIQUIDACION,
				RegistroDeuda.TipoDeuda.REFINANCIACION};
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(tipos, "cap");
		ddTipoDefaultOptions.setOptions(opTipo);

		Set<String> locListaTipoObligacion = getApplicationBean1().getMapaTipoObligacionTasaMenor().keySet();
		Option[] opTipoObligacion = new Option[locListaTipoObligacion.size() + 1];
		int i = 0;
		opTipoObligacion[i++] = new Option("", "");
		for(String cadaTipoObligacion : locListaTipoObligacion) {
			opTipoObligacion[i++] = new Option(cadaTipoObligacion, cadaTipoObligacion);
		}
		this.ddTipoObligacionTasaOptions.setOptions(opTipoObligacion);

		llenarConjuntoDD();
	}

	private void llenarConjuntoDD() {
		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalTasaMenor(null).keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosTasaMenor(null, null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalTasaMenor(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalTasaMenor(null).keySet());
	}

	public void eventoSeleccionTipoObligacion(ValueChangeEvent event) {
		if(this.getDdTipoObligacionTasa().getSelected() != null) {
			String opcion = this.getDdTipoObligacionTasa().getSelected().toString();
			seleccionarTipoObligacion(opcion, true);
		}
	}

	public void eventoSeleccionAnio(ValueChangeEvent event) {
		if(this.getDdAnios().getSelected() != null && this.getDdTipoObligacionTasa().getSelected() != null) {
			String opcion1 = this.getDdAnios().getSelected().toString();
			String opcion2 = this.getDdTipoObligacionTasa().getSelected().toString();
			seleccionarAnio(opcion1, opcion2, true);
		}
	}

	public void eventoSeleccionCalendario(ValueChangeEvent event) {
		if(this.getDdCalendarios().getSelected() != null) {
			String opcion = this.getDdCalendarios().getSelected().toString();
			seleccionarCalendario(opcion, true);
		}
	}

	public void eventoSeleccionPeriodo(ValueChangeEvent event) {
		if(this.getDdPeriodos().getSelected() != null) {
			String opcion = this.getDdPeriodos().getSelected().toString();
			seleccionarPeriodo(opcion, true);
		}
	}

	private void seleccionarTipoObligacion(String pTipoObligacion, boolean actualizar) {
		this.ddTipoObligacionTasa.setSelected(pTipoObligacion);
		if(actualizar) {
			actualizarOpcionesDDAnio(pTipoObligacion);
		}
	}

	private void seleccionarAnio(String pAnio, String pTipoObligacion, boolean actualizar) {
		this.ddAnios.setSelected(pAnio);
		if(actualizar) {
			actualizarOpcionesDDCalendario(pAnio, pTipoObligacion);
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

	private void actualizarOpcionesDDAnio(String pTipoObligacion) {
		String opcion = llenarDD(ddAniosOptions, pTipoObligacion.isEmpty() ? null : this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalTasaMenor(pTipoObligacion).keySet());
		seleccionarAnio(opcion, pTipoObligacion, true);
	}

	private void actualizarOpcionesDDCalendario(String pAnio, String pTipoObligacion) {
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosTasaMenor(pAnio, pTipoObligacion).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalTasaMenor(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalTasaMenor(pPeriodo).keySet());
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

	protected PanelGroup pgParametros = new PanelGroup();

	public PanelGroup getPgParametros() {
		return pgParametros;
	}

	public void setPgParametros(PanelGroup pgParametros) {
		this.pgParametros = pgParametros;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private ObjectListDataProvider ldpLiquidacionTasaMenor = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLiquidacionTasaMenor() {
		return ldpLiquidacionTasaMenor;
	}

	public void setLdpLiquidacionTasaMenor(ObjectListDataProvider ldpLiquidacionTasaMenor) {
		this.ldpLiquidacionTasaMenor = ldpLiquidacionTasaMenor;
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

	private PanelGroup gpReliquidacion = new PanelGroup();

	public PanelGroup getGpReliquidacion() {
		return gpReliquidacion;
	}

	public void setGpReliquidacion(PanelGroup pg) {
		this.gpReliquidacion = pg;
	}

	// ---------------Static Texts---------------
	private StaticText stTitulo = new StaticText();
	private StaticText staticText2 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText8 = new StaticText();
	private StaticText staticText3 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText13 = new StaticText();
	private StaticText staticText4 = new StaticText();
	private StaticText staticText9 = new StaticText();
	private StaticText staticText10 = new StaticText();
	private StaticText staticText11 = new StaticText();
	private StaticText staticText15 = new StaticText();
	private StaticText staticText14 = new StaticText();
	private StaticText staticText12 = new StaticText();
	private StaticText staticText16 = new StaticText();

	public StaticText getStaticText16() {
		return staticText16;
	}

	public void setStaticText16(StaticText staticText16) {
		this.staticText16 = staticText16;
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

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
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

	public StaticText getStaticText15() {
		return staticText15;
	}

	public void setStaticText15(StaticText staticText15) {
		this.staticText15 = staticText15;
	}

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText st) {
		this.staticText14 = st;
	}

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText st) {
		this.staticText12 = st;
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
	private Label lblEstado = new Label();
	private Label label7 = new Label();
	private Label label8 = new Label();
	private Label label9 = new Label();
	private Label lblFechaReliquidacion = new Label();
	private Label lblNumeroCuota = new Label();
	private Label label2 = new Label();
	private Label label3 = new Label();
	private Label lblPago = new Label();
	private Label lblCalendarios = new Label();
	private Label lblPeriodos = new Label();
	private Label lblCuotas = new Label();
	private Label lblPeriodo = new Label();
	private Label lblAnio = new Label();

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
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

	public Label getLabel9() {
		return label9;
	}

	public void setLabel9(Label label9) {
		this.label9 = label9;
	}

	public Label getLblPago() {
		return lblPago;
	}

	public void setLblPago(Label lblPago) {
		this.lblPago = lblPago;
	}

	public void setLblNumeroCuota(Label lblNumeroCuota) {
		this.lblNumeroCuota = lblNumeroCuota;
	}

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
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

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label label7) {
		this.label7 = label7;
	}

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label label8) {
		this.label8 = label8;
	}

	public Label getLblFechaReliquidacion() {
		return lblFechaReliquidacion;
	}

	public void setLblFechaReliquidacion(Label l) {
		this.lblFechaReliquidacion = l;
	}

	public Label getLblNumeroCuota() {
		return lblNumeroCuota;
	}

	// ---------------Buttons---------------
	private Button btnSeleccionarParcela = new Button();
	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();
	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarPersonaJuridica = new Button();
	private Button btnGenerarLiquidaciones = new Button();
	private Button btnImprimir = new Button();
	private Button btnReliquidar = new Button();
	private Button btnActualizarDeuda = new Button();

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

	public HtmlAjaxCommandButton getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(HtmlAjaxCommandButton btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button b) {
		this.btnSeleccionarParcela = b;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
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

	// ---------------Text Fields---------------
	private TextField tfParcela = new TextField();
	private TextField tfFechaReliquidacion = new TextField();
	private TextField tfNumeroCuota = new TextField();
	private TextField tfPersona = new TextField();

	public TextField getTfParcela() {
		return tfParcela;
	}

	public void setTfParcela(TextField tf) {
		this.tfParcela = tf;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	public TextField getTfNumeroCuota() {
		return tfNumeroCuota;
	}

	public void setTfNumeroCuota(TextField tfNumeroCuota) {
		this.tfNumeroCuota = tfNumeroCuota;
	}

	public TextField getTfFechaReliquidacion() {
		return tfFechaReliquidacion;
	}

	public void setTfFechaReliquidacion(TextField tf) {
		this.tfFechaReliquidacion = tf;
	}

	// ---------------DropDowns---------------
	private DropDown ddPeriodicidadCuota = new DropDown();
	private DropDown ddEstado = new DropDown();
	private DropDown ddCalendarios = new DropDown();
	private DropDown ddPeriodos = new DropDown();
	private DropDown ddCuotas = new DropDown();
	private DropDown ddAnios = new DropDown();
	private DropDown ddTipoObligacionTasa = new DropDown();

	public DropDown getDdTipoObligacionTasa() {
		return ddTipoObligacionTasa;
	}

	public void setDdTipoObligacionTasa(DropDown ddTipoObligacionTasa) {
		this.ddTipoObligacionTasa = ddTipoObligacionTasa;
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

	public DropDown getDdPeriodicidadCuota() {
		return ddPeriodicidadCuota;
	}

	public void setDdPeriodicidadCuota(DropDown ddPeriodicidadCuota) {
		this.ddPeriodicidadCuota = ddPeriodicidadCuota;
	}

	// ---------------SingleSelectedOptionLists---------------
	private SingleSelectOptionsList ddPeriodicidadCuotaDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddAniosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddTipoObligacionTasaOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoObligacionTasaOptions() {
		return ddTipoObligacionTasaOptions;
	}

	public void setDdTipoObligacionTasaOptions(SingleSelectOptionsList ddTipoObligacionTasaOptions) {
		this.ddTipoObligacionTasaOptions = ddTipoObligacionTasaOptions;
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

	public SingleSelectOptionsList getDdPeriodicidadCuotaDefaultOptions() {
		return ddPeriodicidadCuotaDefaultOptions;
	}

	public void setDdPeriodicidadCuotaDefaultOptions(SingleSelectOptionsList ddPeriodicidadCuotaDefaultOptions) {
		this.ddPeriodicidadCuotaDefaultOptions = ddPeriodicidadCuotaDefaultOptions;
	}

	private Checkbox cbPago = new Checkbox();

	public Checkbox getCbPago() {
		return cbPago;
	}

	public void setCbPago(Checkbox cbPago) {
		this.cbPago = cbPago;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminLiquidacionTasaMenor() {
	}

	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		ep.setSeleccionMultiple(true);

		return ep;
	}

	protected void guardarEstadoObjetosUsados() {
		FiltroLiquidacionTasaMenor locFiltro = this.getFiltro();

		Object tipoObligacionSelected = this.getDdTipoObligacionTasa().getSelected();
		if((tipoObligacionSelected != null) && (tipoObligacionSelected.toString().length() > 0)) {
			locFiltro.setTipoObligacionTasaMenor(this.getApplicationBean1().getMapaTipoObligacionTasaMenor().get((String) tipoObligacionSelected));
		} else {
			locFiltro.setTipoObligacionTasaMenor(null);
		}

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
			Integer locAnio = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalTasaMenor((String) tipoObligacionSelected).get(anioSeleccionado);
			locFiltro.setAnio(locAnio);

			if(this.getDdCalendarios().getSelected() != null) {
				String calendarioSeleccionado = this.getDdCalendarios().getSelected().toString();
				CalendarioMunicipal locCalendario = this.getCommunicationSAICBean().getMapaCalendariosTasaMenor(anioSeleccionado, (String) tipoObligacionSelected)
						.get(calendarioSeleccionado);
				locFiltro.setCalendario(locCalendario);

				String periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected().toString();
				PeriodoLiquidacion locPeriodo = this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalTasaMenor(calendarioSeleccionado).get(periodoCalendarioSeleccionado);
				locFiltro.setPeriodo(locPeriodo);

				String cuotaSeleccionada = this.getDdCuotas().getSelected().toString();
				CuotaLiquidacion locCuota = this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalTasaMenor(periodoCalendarioSeleccionado).get(cuotaSeleccionada);
				locFiltro.setCuota(locCuota);
			}
		}

		locFiltro.setNoCero(this.getChkOmitir().isChecked());
	}

	protected void mostrarEstadoObjetosUsados() {
		FiltroLiquidacionTasaMenor locFiltro = this.getFiltro();

		if(locFiltro.getPersona() != null && locFiltro.getPersona().getIdPersona() != -1) {
			this.getTfPersona().setText(locFiltro.getPersona().toString());
		}

		if(locFiltro.getParcela() != null && locFiltro.getParcela().getIdParcela() != -1) {
			this.getTfParcela().setText(locFiltro.getParcela().toString());
		}

		if(locFiltro.getAnio() != null && !locFiltro.getAnio().toString().isEmpty()) {
			seleccionarAnio(locFiltro.getAnio().toString(), locFiltro.getTipoObligacionTasaMenor().getNombre().toString(), true);
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

		if(locFiltro.getTipoObligacionTasaMenor() != null && !locFiltro.getTipoObligacionTasaMenor().getNombre().isEmpty()) {
			this.getDdTipoObligacionTasa().setSelected(locFiltro.getTipoObligacionTasaMenor().getNombre().toString());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstadoLiquidacion())));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstadoLiquidacion())));

		this.getDdTipo().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoLiquidacion())));
		this.getDdTipoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoLiquidacion())));

		this.getChkOmitir().setSelected(locFiltro.isNoCero());
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationSAICBean().getTablaLiquidacionTasaMenor();
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	protected void limpiarObjetosUsados() {
		FiltroLiquidacionTasaMenor locFiltro = this.getFiltro();
		locFiltro.setPersona(null);
		locFiltro.setParcela(null);
		locFiltro.setTipoObligacionTasaMenor(null);
		locFiltro.setEstadoLiquidacion(null);
		locFiltro.setTipoLiquidacion(null);
		locFiltro.setAnio(null);
		locFiltro.setCalendario(null);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);
		locFiltro.setNoCero(false);

		this.setListaSeleccionados(new HashSet());
		this.getPaginatedTable().setSeleccionadosSeleccionMultiple(new ArrayList());

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().setParcelaSeleccionada(null);
		this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalTasaMenor(null).clear();
		this.getCommunicationSAICBean().getMapaCalendariosTasaMenor(null, null).clear();
		this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalTasaMenor(null).clear();
		this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalTasaMenor(null).clear();
		// CAMBIAR: Limpiar los textField y los dropDown
		this.getDdTipoObligacionTasa().setSelected(null);
		this.getTfPersona().setText(null);
		this.getTfParcela().setText(null);
		this.getDdAnios().setSelected(null);
		this.getDdCalendarios().setSelected(null);
		this.getDdPeriodos().setSelected(null);
		this.getDdCalendarios().setSelected(null);
		this.getTfFechaReliquidacion().setText(null);

		this.getDdEstado().setSelected(null);
		this.getDdEstadoDefaultOptions().setSelectedValue(null);

		this.getDdTipo().setSelected(null);
		this.getDdTipoDefaultOptions().setSelectedValue(null);

		this.getChkOmitir().setSelected(false);

		llenarConjuntoDD();
	}

	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLiquidacionTasaMenor();
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaLiquidacionesTasaMenor();
	}

	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaLiquidacionesTasaMenor(lista);
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarParcela_action() {
		return navegarParaSeleccionar("AdminParcela");
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

			retorno = "GenerarLiquidacionTasaMenor";
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
				seleccionados = new ArrayList(this.getListaSeleccionados());
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
		return toAbm(new LiquidacionTasaMenorModel().new ConsultarController());
	}

	public String btnImprimir_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// Maru: selecciono el objeto de la tabla y de ahi obtengo los datos para imprimir el reporte, en vez de usar la pila.
		if(ultimo) {
			RowKey rk = null;
			// ariel - descomentar cuando este listo
			Object cbChecked = this.cbPago.getValue();
			Boolean igualPagado = Boolean.FALSE;
			try {
				Persona persona = this.getSessionBean1().getPersonaSeleccionada();
				Parcela parcela = this.getSessionBean1().getParcelaSeleccionada();
				Periodicidad periodicidadCuota = (Periodicidad) this.obtenerObjetoDelElementoPila(4, Periodicidad.class);
				RegistroDeuda.EstadoRegistroDeuda estadoRegistroDeuda = (RegistroDeuda.EstadoRegistroDeuda) this
						.obtenerObjetoDelElementoPila(5, RegistroDeuda.EstadoRegistroDeuda.class);
				// periodo = (Periodo) this.obtenerObjetoDelElementoPila(8, Periodo.class);

				CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(0, CalendarioMunicipal.class);
				PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(1, PeriodoLiquidacion.class);
				CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(2, CuotaLiquidacion.class);

				Object periodicidadCuotaSelected = this.getDdPeriodicidadCuota().getSelected();
				if((periodicidadCuotaSelected != null) && (periodicidadCuotaSelected.toString().length() > 0)) {
					periodicidadCuota = Periodicidad.valueOf(periodicidadCuotaSelected.toString());
				}

				Integer locNumeroCuota = null;
				Object numeroCuota = this.getTfNumeroCuota().getText();
				if(cbChecked != null && cbChecked != "") {
					igualPagado = Boolean.valueOf(cbChecked.toString());
				} else {
					igualPagado = Boolean.FALSE;
				}
				if(numeroCuota != null) {
					locNumeroCuota = Conversor.getIntegerDeString(numeroCuota.toString());
				}
				if(parcela == null || parcela.getIdParcela() == -1) {
					parcela = null;
				}
				if(persona == null || persona.getIdPersona() == -1) {
					persona = null;
				}

				this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
				JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion()
						.getReporteTGI(cuota, persona, parcela, periodicidadCuota, locNumeroCuota, estadoRegistroDeuda, igualPagado.booleanValue(), RegistroDeuda.TipoDeuda.LIQUIDACION);

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_LiquidacionTGI");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

			} catch(Exception ex) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				ex.printStackTrace();
			}

			// ariel - no guardar. utilizar lo ya guardado (con resultado de la busqueda)
			// this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

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
			FiltroLiquidacionTasaMenor locFiltro = this.getFiltro();
			locFiltro.setPersona(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getTfParcela().setText("");
			this.getSessionBean1().setParcelaSeleccionada(null);
			FiltroLiquidacionTasaMenor locFiltro = this.getFiltro();
			locFiltro.setParcela(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnExportar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Liquidaciones TGI");

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_LiquidacionesTGI");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

			} catch(Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log("AdminLiquidacionTasaMenor" + "_ReporteDinamicoError: ", e);
				error("Administraci\363n de Liquidaciones de Tasas Menores" + " - ReporteDinamico: " + e.getMessage());
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
		return this.getCommunicationSAICBean().getEsAdministradorLiquidacionesTasaMenor();
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
				log("AdminLiquidacionTasaMenor" + "_ImprimirLiquidacionesSHPS", ex);
				error("Administraci\363n de Liquidaciones de Tasas Menores" + " - Imprimir: " + ex.getMessage());
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
		FiltroLiquidacionTasaMenor filtro = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaLiquidacionesTasaMenor((FiltroLiquidacionTasaMenor) pFiltro);
		return filtro;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroLiquidacionTasaMenor locFiltro = this.getFiltro();

		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getSessionBean1().setPersonaSeleccionada(persona);
			locFiltro.setPersona(persona);
		}
		if(pObject instanceof Parcela) {
			Parcela parcela = (Parcela) pObject;
			this.getSessionBean1().setParcelaSeleccionada(parcela);
			locFiltro.setParcela(parcela);
		}
		this.getRequestBean1().setObjetoSeleccion(null);
	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Liquidaciones de Tasa Menor";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminLiquidacionTasaMenor";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroLiquidacionTasaMenor locFiltro = this.getFiltro();
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

	public void setParcelaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroLiquidacionTasaMenor locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Parcela locParcela = null;

		try {
			locParcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(TrascenderException e) {
			e.printStackTrace();
		}

		locFiltro.setParcela(locParcela);
		this.getSessionBean1().setParcelaSeleccionada(locParcela);
	}

	@Override
	public String getNombreBean() {
		return "#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor}";
	}

	@Override
	public long getSerialVersionUID() {
		return LiquidacionTasa.codigoTasaMenor;
	}
}