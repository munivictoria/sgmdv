/*
 * AdminLiquidacionSHPS.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.saic.ImprimirLiquidaciones;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import muni.saic.ABMReliquidacion.ActualizarDeudaModel;
import muni.saic.ABMReliquidacion.NotificacionModel;
import muni.saic.ABMReliquidacion.ReliquidarVariasModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.ImageComponent;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
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
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaRefer;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminImprimirLiquidaciones extends AdminPageBean {

	// Atributos propios de la pagina.
	// CAMBIAR: Ir al dise�o y vincular a campos ocultos.
	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Imprimir Liquidaciones";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AdminImprimirLiquidaciones";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private boolean ocurrioEventoSeleccion = false;

	// CAMBIAR: Links hacia las paginas de agregar/modificar/eliminar

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */

	@Override
	protected void _init() throws Exception {
		Option[] opTipoTasa = new Option[2];
		Option opcion = new Option("Servicios Municipales");
		opTipoTasa[0] = opcion;
		Option opcion2 = new Option("TSH");
		opTipoTasa[1] = opcion2;
		rbgTipoObligacionOptions.setOptions(opTipoTasa);

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
			this.getBtnSeleccionarTodas().setRendered(true);
			this.getTcCheckbox().setRendered(true);
			this.getTableColumn1().setRendered(false);
		} else {
			this.getBtnSeleccionarTodas().setRendered(false);
			this.getTcCheckbox().setRendered(false);
			this.getTableColumn1().setRendered(true);
		}

		Option[] opEstado = null;
		RegistroDeuda.EstadoRegistroDeuda[] estados = new RegistroDeuda.EstadoRegistroDeuda[] {RegistroDeuda.EstadoRegistroDeuda.PAGADA, RegistroDeuda.EstadoRegistroDeuda.VENCIDA,
				RegistroDeuda.EstadoRegistroDeuda.VIGENTE, RegistroDeuda.EstadoRegistroDeuda.ANULADA, RegistroDeuda.EstadoRegistroDeuda.RELIQUIDADA,
				RegistroDeuda.EstadoRegistroDeuda.REFINANCIADA, RegistroDeuda.EstadoRegistroDeuda.NO_OPTADA, 
				RegistroDeuda.EstadoRegistroDeuda.PRESCRITA};
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(estados, "cap");

		Option[] opTipo = null;
		RegistroDeuda.TipoDeuda[] tipos = new RegistroDeuda.TipoDeuda[] {RegistroDeuda.TipoDeuda.LIQUIDACION, RegistroDeuda.TipoDeuda.RELIQUIDACION,
				RegistroDeuda.TipoDeuda.REFINANCIACION};
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(tipos, "cap");

		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Periodicidad.values(), "cap");

		ddEstadoDefaultOptions.setOptions(opEstado);
		ddTipoDefaultOptions.setOptions(opTipo);

		numberConverter1.setPattern("$ #,##0.00");
		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yyyy");
		dateTimeConverter1.setTimeStyle("full");
	}

	public void actionEventCambioTipoTasa(ActionEvent evento) {
		this.ocurrioEventoSeleccion = true;
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		locFiltro.setAnio(null);
		locFiltro.setCalendario(null);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);

		this.getElementoPila().getObjetos().set(0, locFiltro);

		String opcionSeleccionada = this.getRbgTipoObligacion().getValue().toString();
		if(opcionSeleccionada != null) {
			this.getElementoPila().getObjetos().set(1, opcionSeleccionada);
			seleccionarAnio(getAnioCorriente(), true);
			this.armarDropDownCalendarios();
		}
	}

	public void armarDropDownCalendarios() {
		String opcionSeleccionada = (String) this.obtenerObjetoDelElementoPila(1);

		if(opcionSeleccionada.equalsIgnoreCase("Servicios Municipales")) {
			llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalTGI().keySet());
			llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosTGI(null).keySet());
			if(ddCalendariosOptions.getOptions().length < 1) {
				llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalTGI(null).keySet());
			}
			if(ddCuotasOptions.getOptions().length > 1) {
				llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalTGI(null).keySet());
			}
		}
		if(opcionSeleccionada.equalsIgnoreCase("TSH")) {
			llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet());
			llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosSHPS(null).keySet());
			if(ddCalendariosOptions.getOptions().length < 1) {
				llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(null).keySet());
			}
			if(ddCuotasOptions.getOptions().length > 1) {
				llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(null).keySet());
			}
		}
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

	public void eventoSeleccionAnio(ValueChangeEvent event) {
		this.ocurrioEventoSeleccion = true;
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		locFiltro.setAnio(null);
		locFiltro.setCalendario(null);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);
		String opcion = this.getDdAnios().getSelected().toString();
		seleccionarAnio(opcion, true);
		this.getElementoPila().getObjetos().set(0, locFiltro);
	}

	public void actionListenerSeleccionAnio(ActionEvent event) {
		String opcion = this.getDdAnios().getSelected().toString();
		seleccionarAnio(opcion, true);
	}

	public void eventoSeleccionCalendario(ValueChangeEvent event) {
		this.ocurrioEventoSeleccion = true;
		String opcion = this.getDdCalendarios().getSelected().toString();
		seleccionarCalendario(opcion, true);
	}

	public void actionListenerSeleccionCalendario(ActionEvent evento) {
		this.ocurrioEventoSeleccion = true;
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		locFiltro.setCalendario(null);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);
		String opcion = this.getDdCalendarios().getSelected().toString();
		seleccionarCalendario(opcion, true);
	}

	public void eventoSeleccionPeriodo(ValueChangeEvent event) {
		this.ocurrioEventoSeleccion = true;
		String opcion = this.getDdPeriodos().getSelected().toString();
		seleccionarPeriodo(opcion, true);
	}

	public void actionListenerSeleccionPeriodo(ActionEvent event) {
		this.ocurrioEventoSeleccion = true;
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);
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
		String opcionSeleccionada = (String) this.obtenerObjetoDelElementoPila(1);
		String opcion = "";
		if(opcionSeleccionada != null) {
			if(opcionSeleccionada.equals("Servicios Municipales")) {
				opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosTGI(pAnio).keySet());
			} else if(opcionSeleccionada.equals("TSH")) {
				opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosSHPS(pAnio).keySet());
			}
		}
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcionSeleccionada = (String) this.obtenerObjetoDelElementoPila(1);
		String opcion = "";
		if(opcionSeleccionada != null) {
			if(opcionSeleccionada.equals("Servicios Municipales")) {
				opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalTGI(pCalendario).keySet());
			} else if(opcionSeleccionada.equals("TSH")) {
				opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(pCalendario).keySet());
			}
		}
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcionSeleccionada = (String) this.obtenerObjetoDelElementoPila(1);
		String opcion = "";
		if(opcionSeleccionada != null) {
			if(opcionSeleccionada.equals("Servicios Municipales")) {
				if(pPeriodo != null) {
					opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalTGI(pPeriodo).keySet());
				}
			} else if(opcionSeleccionada.equals("TSH")) {
				opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(pPeriodo).keySet());
			}
		}
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

	private Label lblTipo = new Label();
	private Label lblOmitir = new Label();
	private TableColumn tcPersona = new TableColumn();
	private TableColumn tcAnio = new TableColumn();
	private TableColumn tcParcela = new TableColumn();
	private StaticText stAnio = new StaticText();
	private StaticText stParcela = new StaticText();
	private StaticText stTotal = new StaticText();
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();
	private Button btnMarcarPagada = new Button();
	private Button btnMarcarImpaga = new Button();
	private Button btnNotificar = new Button();
	private Button btnRefinanciar = new Button();
	private Button btnEliminarLiquidacion = new Button();
	private Button btnModificarLiquidacion = new Button();
	private Button btnModificarVarias = new Button();
	private Button btnCambiarSeleccion = new Button();
	private Button btnSeleccionarTodas = new Button();
	private Button btnExportarPDF = new Button();
	private PanelGroup pgBotonesAdicionales = new PanelGroup();
	private Checkbox checkBox1 = new Checkbox();
	private Checkbox chkOmitir = new Checkbox();
	private Set listaSeleccionados = new HashSet();
	private TextArea taPersona = new TextArea();
	
	public Button getBtnRefinanciar() {
		return btnRefinanciar;
	}
	public void setBtnRefinanciar(Button btnRefinanciar) {
		this.btnRefinanciar = btnRefinanciar;
	}
	public Button getBtnNotificar() {
		return btnNotificar;
	}
	public void setBtnNotificar(Button btnNotificar) {
		this.btnNotificar = btnNotificar;
	}
	public TextArea getTaPersona() {
		return taPersona;
	}

	public void setTaPersona(TextArea taPersona) {
		this.taPersona = taPersona;
	}

	public Label getLblOmitir() {
		return lblOmitir;
	}
	
	private Checkbox cbOmitirMostrador = new Checkbox();
	
	public Checkbox getCbOmitirMostrador() {
		return cbOmitirMostrador;
	}

	public void setCbOmitirMostrador(Checkbox cbOmitirMostrador) {
		this.cbOmitirMostrador = cbOmitirMostrador;
	}

	public void setLblOmitir(Label lblOmitir) {
		this.lblOmitir = lblOmitir;
	}

	public Checkbox getChkOmitir() {
		return chkOmitir;
	}

	public void setChkOmitir(Checkbox chkOmitir) {
		this.chkOmitir = chkOmitir;
	}

	private HtmlAjaxCommandButton btnImprimirEnServidor = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnImprimirEnServidor() {
		return btnImprimirEnServidor;
	}

	public void setBtnImprimirEnServidor(HtmlAjaxCommandButton btnImprimirEnServidor) {
		this.btnImprimirEnServidor = btnImprimirEnServidor;
	}

	public Button getBtnExportarPDF() {
		return btnExportarPDF;
	}

	public void setBtnExportarPDF(Button btnExportarPDF) {
		this.btnExportarPDF = btnExportarPDF;
	}

	public Button getBtnModificarVarias() {
		return btnModificarVarias;
	}

	public void setBtnModificarVarias(Button btnModificarVarias) {
		this.btnModificarVarias = btnModificarVarias;
	}

	public Button getBtnSeleccionarTodas() {
		return btnSeleccionarTodas;
	}

	public void setBtnSeleccionarTodas(Button btnSeleccionarTodas) {
		this.btnSeleccionarTodas = btnSeleccionarTodas;
	}

	public Button getBtnMarcarImpaga() {
		return btnMarcarImpaga;
	}

	public void setBtnMarcarImpaga(Button btnMarcarImpaga) {
		this.btnMarcarImpaga = btnMarcarImpaga;
	}

	public Button getBtnCambiarSeleccion() {
		return btnCambiarSeleccion;
	}

	public void setBtnCambiarSeleccion(Button btnCambiarSeleccion) {
		this.btnCambiarSeleccion = btnCambiarSeleccion;
	}

	public Button getBtnModificarLiquidacion() {
		return btnModificarLiquidacion;
	}

	public void setBtnModificarLiquidacion(Button btnModificarLiquidacion) {
		this.btnModificarLiquidacion = btnModificarLiquidacion;
	}

	public Set getListaSeleccionados() {
		return listaSeleccionados;
	}

	public void setListaSeleccionados(Set listaSeleccionados) {
		this.listaSeleccionados = listaSeleccionados;
	}

	private Checkbox cbNoAgruparLiquidaciones = new Checkbox();

	public Checkbox getCbNoAgruparLiquidaciones() {
		return cbNoAgruparLiquidaciones;
	}

	public void setCbNoAgruparLiquidaciones(Checkbox cbNoAgruparLiquidaciones) {
		this.cbNoAgruparLiquidaciones = cbNoAgruparLiquidaciones;
	}

	public Checkbox getCheckBox1() {
		return checkBox1;
	}

	public void setCheckBox1(Checkbox checkBox1) {
		this.checkBox1 = checkBox1;
	}

	public Button getBtnEliminarLiquidacion() {
		return btnEliminarLiquidacion;
	}

	public void setBtnEliminarLiquidacion(Button btnEliminarLiquidacion) {
		this.btnEliminarLiquidacion = btnEliminarLiquidacion;
	}

	public Button getBtnMarcarPagada() {
		return btnMarcarPagada;
	}

	public void setBtnMarcarPagada(Button btnMarcarPagada) {
		this.btnMarcarPagada = btnMarcarPagada;
	}

	public PanelGroup getPgBotonesAdicionales() {
		return pgBotonesAdicionales;
	}

	public void setPgBotonesAdicionales(PanelGroup pgBotonesAdicionales) {
		this.pgBotonesAdicionales = pgBotonesAdicionales;
	}

	public StaticText getStTotal() {
		return stTotal;
	}

	public void setStTotal(StaticText stTotal) {
		this.stTotal = stTotal;
	}

	public StaticText getStAnio() {
		return stAnio;
	}

	public void setStAnio(StaticText stAnio) {
		this.stAnio = stAnio;
	}

	public StaticText getStParcela() {
		return stParcela;
	}

	public void setStParcela(StaticText stParcela) {
		this.stParcela = stParcela;
	}

	public TableColumn getTcAnio() {
		return tcAnio;
	}

	public void setTcAnio(TableColumn tcAnio) {
		this.tcAnio = tcAnio;
	}

	public TableColumn getTcParcela() {
		return tcParcela;
	}

	public void setTcParcela(TableColumn tcParcela) {
		this.tcParcela = tcParcela;
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

	public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public TableColumn getTcPersona() {
		return tcPersona;
	}

	public void setTcPersona(TableColumn tcPersona) {
		this.tcPersona = tcPersona;
	}

	private ObjectListDataProvider ldpLiquidacionTasaRefer = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLiquidacionTasaRefer() {
		return ldpLiquidacionTasaRefer;
	}

	public void setLdpLiquidacionTasaRefer(ObjectListDataProvider oldp) {
		this.ldpLiquidacionTasaRefer = oldp;
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
	private StaticText staticText2 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText8 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText11 = new StaticText();
	private StaticText staticText12 = new StaticText();
	private StaticText staticText13 = new StaticText();
	private StaticText stObligacion = new StaticText();
	private StaticText staticText1 = new StaticText();
	private StaticText staticText10 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText3 = new StaticText();
	private StaticText staticText9 = new StaticText();

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

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	public StaticText getStObligacion() {
		return stObligacion;
	}

	public void setStObligacion(StaticText stObligacion) {
		this.stObligacion = stObligacion;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private StaticText staticText4 = new StaticText();

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

	// ---------------Converters---------------
	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	private NumberConverter numberConverter1 = new NumberConverter();

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	// ---------------Labels---------------
	private Label label1 = new Label();
	private Label lblEstado = new Label();
	private Label lblCalendarios = new Label();
	private Label lblPeriodos = new Label();
	private Label lblCuotas = new Label();
	private Label lblFechaReliquidacion = new Label();
	private Label label7 = new Label();
	private Label label8 = new Label();
	private Label lblTotal = new Label();
	private Label lblAnios = new Label();

	public Label getLblAnios() {
		return lblAnios;
	}

	public void setLblAnios(Label lblAnios) {
		this.lblAnios = lblAnios;
	}

	public Label getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(Label lblTotal) {
		this.lblTotal = lblTotal;
	}

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label label8) {
		this.label8 = label8;
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

	public Label getLblFechaReliquidacion() {
		return lblFechaReliquidacion;
	}

	public void setLblFechaReliquidacion(Label l) {
		this.lblFechaReliquidacion = l;
	}

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	// ---------------Buttons---------------
	private Button btnImprimirSeleccionada = new Button();

	public Button getBtnImprimirSeleccionada() {
		return btnImprimirSeleccionada;
	}

	public void setBtnImprimirSeleccionada(Button btnImprimirSeleccionada) {
		this.btnImprimirSeleccionada = btnImprimirSeleccionada;
	}

	private Button btnReliquidar = new Button();

	public Button getBtnReliquidar() {
		return btnReliquidar;
	}

	public void setBtnReliquidar(Button b) {
		this.btnReliquidar = b;
	}

	private Button btnActualizarDeuda = new Button();
	private Button btnEstadoDeuda = new Button();

	// ---------------Text Fields---------------

	public Button getBtnActualizarDeuda() {
		return btnActualizarDeuda;
	}

	public Button getBtnEstadoDeuda() {
		return btnEstadoDeuda;
	}

	public void setBtnEstadoDeuda(Button btnEstadoDeuda) {
		this.btnEstadoDeuda = btnEstadoDeuda;
	}

	public void setBtnActualizarDeuda(Button btnActualizarDeuda) {
		this.btnActualizarDeuda = btnActualizarDeuda;
	}

	private TextField tfFechaReliquidacion = new TextField();

	public TextField getTfFechaReliquidacion() {
		return tfFechaReliquidacion;
	}

	public void setTfFechaReliquidacion(TextField tf) {
		this.tfFechaReliquidacion = tf;
	}

	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();
	private TableColumn tcObligacion = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tableColumn6) {
		this.tableColumn6 = tableColumn6;
	}

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	public TableColumn getTcObligacion() {
		return tcObligacion;
	}

	public void setTcObligacion(TableColumn tcObligacion) {
		this.tcObligacion = tcObligacion;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	// ---------------DropDowns---------------
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

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
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

	// ---------------SingleSelectOptionsLists---------------
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

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
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

	public RadioButtonGroup rbgTipoObligacion = new RadioButtonGroup();

	public RadioButtonGroup getRbgTipoObligacion() {
		return rbgTipoObligacion;
	}

	public void setRbgTipoObligacion(RadioButtonGroup rbgTipoObligacion) {
		this.rbgTipoObligacion = rbgTipoObligacion;
	}

	public SingleSelectOptionsList rbgTipoObligacionOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getRbgTipoObligacionOptions() {
		return rbgTipoObligacionOptions;
	}

	public void setRbgTipoObligacionOptions(SingleSelectOptionsList rbgTipoObligacionOptions) {
		this.rbgTipoObligacionOptions = rbgTipoObligacionOptions;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminImprimirLiquidaciones() {
	}

	@Override
	protected void _prerender() throws Exception {
		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1());

		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		String opcionSeleccionada = (String) this.obtenerObjetoDelElementoPila(1);

		if((opcionSeleccionada == null)) {
			Option[] opCombos = new Option[1];
			Option opcionCombos = new Option("Seleccione Tipo Obligacion");
			opCombos[0] = opcionCombos;
			ddAniosOptions.setOptions(opCombos);
			ddCalendariosOptions.setOptions(new Option[0]);
			this.ddPeriodosOptions.setOptions(new Option[0]);
			this.ddCuotasOptions.setOptions(new Option[0]);
		}
		if(opcionSeleccionada != null) {
			if(opcionSeleccionada.equalsIgnoreCase("Servicios Municipales")) {
				this.rbgTipoObligacion.setSelected("Servicios Municipales");
			} else if(opcionSeleccionada.equalsIgnoreCase("TSH")) {
				this.rbgTipoObligacion.setSelected("TSH");
			}
		}

		if(!ocurrioEventoSeleccion) {
			if(opcionSeleccionada.equalsIgnoreCase("Servicios Municipales")) {
				llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalTGI().keySet());
				llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosTGI(null).keySet());
				llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalTGI(null).keySet());
				llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalTGI(null).keySet());
			} else if(opcionSeleccionada.equalsIgnoreCase("TSH")) {
				llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet());
				llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosSHPS(null).keySet());
				llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(null).keySet());
				llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(null).keySet());
			}
		}
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		// CAMBIAR: settear los objetos administrados por la pagina
		FiltroLiquidacionTasaRefer locFiltro = new FiltroLiquidacionTasaRefer();
		locFiltro.setAnio(Integer.valueOf(getAnioCorriente()));
		ep.getObjetos().add(0, locFiltro); // FiltroLiquidacionTasaRefer
		ep.getObjetos().add(1, "Servicios Municipales"); // 6 opcion seleccionado
		ep.getObjetos().add(2, getAnioCorriente()); // 7 anio

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(3, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);

		String dni = getTextFieldValue(this.getTfNroDocumento());
		String nroParcela = getTextFieldValue(this.getTfNroParcela());

		locFiltro.setEstadoLiquidacion(getDDEnumValue(this.getDdEstado(), RegistroDeuda.EstadoRegistroDeuda.class));
		locFiltro.setTipoLiquidacion(getDDEnumValue(this.getDdTipo(), RegistroDeuda.TipoDeuda.class));

		String tasaSeleccionada = (String) this.obtenerObjetoDelElementoPila(1);

		List<TipoObligacion> listaTipoTasa = new ArrayList<TipoObligacion>();
		if(tasaSeleccionada != null && this.getDdAnios().getSelected().toString() != null) {
			String anioSeleccionado = this.getDdAnios().getSelected().toString();
			String calendarioSeleccionado = null;
			String periodoCalendarioSeleccionado = null;
			String cuotaSeleccionada = null;
			if(this.getDdCalendarios().getSelected() != null) {
				calendarioSeleccionado = this.getDdCalendarios().getSelected().toString();
			}
			if(this.getDdPeriodos().getSelected() != null) {
				periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected().toString();
			}
			if(this.getDdCuotas().getSelected() != null) {
				cuotaSeleccionada = this.getDdCuotas().getSelected().toString();
			}

			Integer locAnio = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalTGI().get(anioSeleccionado);
			CalendarioMunicipal locCalendario = null;
			PeriodoLiquidacion locPeriodo = null;
			CuotaLiquidacion locCuota = null;

			if(tasaSeleccionada.equalsIgnoreCase("Servicios Municipales")) {
				locCalendario = this.getCommunicationSAICBean().getMapaCalendariosTGI(anioSeleccionado).get(calendarioSeleccionado);
				locPeriodo = this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalTGI(calendarioSeleccionado).get(periodoCalendarioSeleccionado);
				locCuota = this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalTGI(periodoCalendarioSeleccionado).get(cuotaSeleccionada);

				try {
					listaTipoTasa.add(this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTipoObligacion("Tgi", null).get(0));
					listaTipoTasa.add(this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTipoObligacion("Oysp", null).get(0));
					listaTipoTasa.add(this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTipoObligacion("Arrendamiento", null).get(0));
				} catch(TrascenderException e) {
					e.printStackTrace();
				}
			} else if(tasaSeleccionada.equalsIgnoreCase("TSH")) {
				locCalendario = this.getCommunicationSAICBean().getMapaCalendariosSHPS(anioSeleccionado).get(calendarioSeleccionado);
				locPeriodo = this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(calendarioSeleccionado).get(periodoCalendarioSeleccionado);
				locCuota = this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(periodoCalendarioSeleccionado).get(cuotaSeleccionada);

				try {
					listaTipoTasa.add(this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTipoObligacion("Shps", null).get(0));
				} catch(TrascenderException e) {
					e.printStackTrace();
				}
			}

			locFiltro.setAnio(locAnio);
			locFiltro.setCalendario(locCalendario);
			locFiltro.setPeriodo(locPeriodo);
			locFiltro.setCuota(locCuota);
			locFiltro.setListaTipoObligacion(listaTipoTasa);
		}
		
		this.setListaSeleccionados(this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleImprimirLiquidaciones());

		locFiltro.setNoCero((Boolean) this.getChkOmitir().getSelected());
		locFiltro.setNoAgrupar(this.getCbNoAgruparLiquidaciones().isChecked());
		locFiltro.setOmitirMostrador(this.getCbOmitirMostrador().isChecked());

		locFiltro.setDni(dni);
		this.getSessionBean1().setNroDocumento(dni);
		locFiltro.setNumeroParcela(nroParcela);
		this.getSessionBean1().setNroParcela(nroParcela);

		locFiltro.setPersona(this.getSessionBean1().getPersonaSeleccionada());
		locFiltro.setParcela(this.getSessionBean1().getParcelaSeleccionada());

		this.getElementoPila().getObjetos().set(0, locFiltro);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		Persona persona = this.getSessionBean1().getPersonaSeleccionada();
		Parcela parcela = this.getSessionBean1().getParcelaSeleccionada();
		String dni = this.getSessionBean1().getNroDocumento();
		String nroParcela = this.getSessionBean1().getNroParcela();

		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		String tasaSeleccionada = this.obtenerObjetoDelElementoPila(1, String.class);

		if(this.getRequestBean1().getObjetoSeleccion() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoSeleccion();

			if(seleccionado instanceof Persona) {
				persona = (Persona) seleccionado;
				locFiltro.setPersona(persona);
				this.getSessionBean1().setPersonaSeleccionada(persona);
			}
			if(seleccionado instanceof Parcela) {
				parcela = (Parcela) seleccionado;
				locFiltro.setParcela(parcela);
				this.getSessionBean1().setParcelaSeleccionada(parcela);
			}
		}
		
		getRbgTipoObligacion().setSelected(tasaSeleccionada);

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

		if(persona != null && persona.getIdPersona() != -1) {
			this.getTfPersonaSeleccionada().setText(persona);
		} else {
			this.getTfPersonaSeleccionada().setText(null);
		}
		if(parcela != null && parcela.getIdParcela() != -1) {
			this.getTfParcelaSeleccionada().setText(parcela);
		} else {
			this.getTfParcelaSeleccionada().setText(null);
		}
		if(dni != null && dni.length() > 0) {
			this.getTfNroDocumento().setText(dni);
		}
		if(nroParcela != null && nroParcela.length() > 0) {
			this.getTfNroParcela().setText(nroParcela);
		}

		if(this.getLdpLiquidacionTasaRefer().getList() != null) {
			Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
			this.seleccionarFila(filaSeleccionada);
		}
	}

	@Override
	protected void refrescarTabla() throws Exception {
		// CAMBIAR: Segun objeto de busqueda.
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);

		boolean err = false;

		if(locFiltro.getPersona() == null && (locFiltro.getDni() == null || locFiltro.getDni().equals("")) && locFiltro.getParcela() == null
				&& (locFiltro.getNumeroParcela() == null || locFiltro.getNumeroParcela().equals(""))
				&& (locFiltro.getCuota() == null || locFiltro.getCuota().getIdCuotaLiquidacion() == -1)) {
			warn("Debe seleccionar una Persona, Per\355odo o Parcela para realizar la b\372squeda.");
			err = true;
		}

		if(locFiltro.getListaTipoObligacion() == null || locFiltro.getListaTipoObligacion().size() < 1) {
			warn("Debe seleccionar al menos un Tipo de Obligaci\363n para realizar la b\372squeda.");
			err = true;
		}

		if(!err) {
			// CAMBIAR: Utilizar el EJBClient adecuado.
			this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());

			List<LiquidacionTasaRefer> locListaLiquidacion = this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().findListaLiquidacionTasaRefer(locFiltro)
					.getListaResultados();

			double montoTotal = 0;
			for(LiquidacionTasaRefer cadaLiquidacion : locListaLiquidacion) {
				montoTotal = cadaLiquidacion.getTotal() + montoTotal;
			}

			DecimalFormat newFormat = new DecimalFormat("##########.##");
			String convertMontoTotal = newFormat.format(montoTotal);
			this.stTotal.setText("$" + convertMontoTotal);

			this.setListaDelCommunication(locListaLiquidacion);
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		} else {
			this.getObjectListDataProvider().setList(new ArrayList());
		}
		this.setRBSelected((new Long(0)).toString());
	}

	@Override
	public String btnReiniciar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.limpiarObjetosUsados();
				this.limpiarTabla();
			} catch(Exception ex) {
				this.limpiarTabla();
				log(CASO_NAVEGACION + "_ReiniciarError:", ex);
				error(NOMBRE_PAGINA + " - Reiniciar: " + ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void limpiarObjetosUsados() {
		for(int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
			this.getElementoPila().getObjetos().set(i, null);
		}

		this.setListaSeleccionados(new HashSet());
		this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleImprimirLiquidaciones(new HashSet());

		this.getSessionBean1().setParcelaSeleccionada(null);
		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().setNroDocumento(null);
		this.getSessionBean1().setNroParcela(null);
		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfPersonaSeleccionada().setText(null);
		this.getTfParcelaSeleccionada().setText(null);

		this.getTfNroDocumento().setText(null);
		this.getTfNroParcela().setText(null);
		this.getDdEstado().setSelected(null);
		this.getDdEstadoDefaultOptions().setSelectedValue(null);
		this.getDdTipo().setSelected(null);
		this.getDdTipoDefaultOptions().setSelectedValue(null);
		this.stTotal.setText("");
		
		this.getCbOmitirMostrador().setSelected(false);
		this.getChkOmitir().setSelected(false);

		this.rbgTipoObligacion.setSelected("Servicios Municipales");
		this.getElementoPila().getObjetos().set(1, "Servicios Municipales");
		seleccionarAnio(getAnioCorriente(), true);
		this.armarDropDownCalendarios();
	}

	@Override
	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(getTfPersonaSeleccionada());
			FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
			locFiltro.setPersona(null);
			this.getElementoPila().getObjetos().set(0, locFiltro);
			this.getSessionBean1().setPersonaSeleccionada(null);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public String btnLimpiarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(getTfParcelaSeleccionada());
			FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
			locFiltro.setParcela(null);
			this.getElementoPila().getObjetos().set(0, locFiltro);
			this.getSessionBean1().setParcelaSeleccionada(null);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLiquidacionTasaRefer();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaLiquidacionesImprimir();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaLiquidacionesImprimir(lista);
	}

	private RowKey getTableRow() {
		FacesContext context = FacesContext.getCurrentInstance();
		ValueBinding vb = context.getApplication().createValueBinding("#{currentRow.tableRow}");
		return (RowKey) vb.getValue(context);
	}

	@Override
	public void setSelected(Object object) {
		RowKey rowKey = this.getTableRow();
		if(rowKey != null) {
			if(object != null) {
				object = this.getObjectListDataProvider().getObjects()[Integer.parseInt(object.toString())];
				this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleImprimirLiquidaciones().add(object);
				tspl.setSelected(rowKey, object);
			}
			if(!this.getTspl().isSelected(rowKey)) {
				object = this.getObjectListDataProvider().getObjects()[Integer.parseInt(rowKey.getRowId())];
				this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleImprimirLiquidaciones().remove(object);
			}
		}
	}

	@Override
	public Object getSelected() {
		String sv = getCheckBox1().getSelectedValue().toString();
		Object objectSeleccionado = this.getObjectListDataProvider().getObjects()[Integer.parseInt(sv)];

		if(this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleImprimirLiquidaciones().contains(objectSeleccionado)) {
			return sv;
		}

		return null;
	}

	@Override
	public String getSelectedValue() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		if(rowKey != null) {
		}
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		return this.getCommunicationSAICBean().getTablePhaseListenerImprimirLiquidaciones();
	}

	@Override
	public String btnBuscar_action() {
		this.setListaSeleccionados(new HashSet());
		this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleImprimirLiquidaciones(new HashSet());
		return super.btnBuscar_action();
	}

	public String btnConsultar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		
		if(ultimo) {
			try {
				RowKey rk = this.getSeleccionado();

				List seleccionados = new ArrayList();
				if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
					seleccionados = new ArrayList(this.getListaSeleccionados());
				} else {
					try {
						if(rk != null) {
							int index = getNroFila(rk.toString());
							Object obj = this.getObjectListDataProvider().getObjects()[index];

							seleccionados.add(obj);
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
				}

				if(seleccionados.size() == 1) {
					Object obj = seleccionados.get(0);

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

					this.guardarOrdenamiento();
					Long pos = this.getPosicionEnTabla(rk);
					this.getElementoPila().setPosicionGlobal(pos.longValue());

					LiquidacionTasaRefer locLiquidacion = (LiquidacionTasaRefer) obj;
					getRequestBean1().setObjetoABM(locLiquidacion);
				} else {
					warn("Debe seleccionar una liquidación");
					return null;
				}
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_ConsultarError:", ex);
				error(NOMBRE_PAGINA + " - Consultar: " + ex.getMessage());
			}

			retorno = "ConsultarLiquidaciones";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnReliquidar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		
		if(ultimo) {
			RowKey rk = null;

			List seleccionados = new ArrayList();
			if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
				seleccionados = new ArrayList(this.getListaSeleccionados());
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
				Object obj = seleccionados.get(0);

				this.guardarEstadoObjetosUsados();
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				this.guardarOrdenamiento();
				Long pos = this.getPosicionEnTabla(rk);
				this.getElementoPila().setPosicionGlobal(pos.longValue());
				LiquidacionTasaRefer locLiquidacion = (LiquidacionTasaRefer) obj;

				if(locLiquidacion.getEstado().equals("VIGENTE") || (locLiquidacion.getEstado().equals("VENCIDA")) || (locLiquidacion.getEstado().equals("NO_OPTADA"))) {
					obj = this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().inicializarLiquidacionTasaAgrupada(locLiquidacion);
					this.getRequestBean1().setObjetoABM(obj);
					retorno = "GenerarReliquidacion";
				} else {
					warn("S\363lo pueden Reliquidarse obligaciones Vigentes, Vencidas y No Optadas.");
					retorno = null;
				}
			} else if(seleccionados.size() > 1) {
				List<LiquidacionTasaRefer> listaLiquidacionesRefer = seleccionados;
				List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();

				this.guardarEstadoObjetosUsados();
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				this.guardarOrdenamiento();
				Long pos = this.getPosicionEnTabla(rk);
				this.getElementoPila().setPosicionGlobal(pos.longValue());

				for(LiquidacionTasaRefer cadaLiquidacionRefer : listaLiquidacionesRefer) {
					if(!(cadaLiquidacionRefer.getEstado().equals("VIGENTE")) && !(cadaLiquidacionRefer.getEstado().equals("VENCIDA"))
							&& !(cadaLiquidacionRefer.getEstado().equals("NO_OPTADA"))) {
						warn("S\363lo pueden Reliquidarse obligaciones Vigentes, Vencidas y No Optadas.");
						return null;
					}
					try {
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
						listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente()
								.inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
					} catch(RemoteException e) {
						e.printStackTrace();
					}
				}

				this.getRequestBean1().setObjetoABM(listaLiquidacionesAgrupadas);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new ReliquidarVariasModel().new ReliquidarVariasController());
				retorno = "ReliquidarVarias";

			} else {
				warn("Debe seleccionar una liquidación");
				return null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	public String btnNotificar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		
		if(ultimo) {
			List<LiquidacionTasaRefer> listaLiquidacionesRefer = Util.castearLista(this.getObjectListDataProvider().getList());
			List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();

			for(Iterator<LiquidacionTasaRefer> iterator = listaLiquidacionesRefer.iterator(); iterator.hasNext();) {
				LiquidacionTasaRefer cadaLiquidacionRefer = iterator.next();
				if(!cadaLiquidacionRefer.getEstado().equals("VIGENTE") && !cadaLiquidacionRefer.getEstado().equals("VENCIDA")) {
					iterator.remove();
					continue;
				}
				try {
					this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
					listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
				} catch(RemoteException e) {
					e.printStackTrace();
				}
			}
//			this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleActualizarDeuda(new HashSet());

			this.getRequestBean1().setObjetoABM(listaLiquidacionesAgrupadas);
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new NotificacionModel().new NotificarController());

			retorno = "ABMNotificacion";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnActualizarDeuda_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		
		if(ultimo) {
			List<LiquidacionTasaRefer> listaLiquidacionesRefer = Util.castearLista(this.getObjectListDataProvider().getList());
			List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();

			for(Iterator<LiquidacionTasaRefer> iterator = listaLiquidacionesRefer.iterator(); iterator.hasNext();) {
				LiquidacionTasaRefer cadaLiquidacionRefer = iterator.next();
				if(!cadaLiquidacionRefer.getEstado().equals("VIGENTE") && !cadaLiquidacionRefer.getEstado().equals("VENCIDA")) {
					iterator.remove();
					continue;
				}
				try {
					this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
					listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
				} catch(RemoteException e) {
					e.printStackTrace();
				}
			}
			this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleActualizarDeuda(new HashSet());

			this.getRequestBean1().setObjetoABM(listaLiquidacionesAgrupadas);
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new ActualizarDeudaModel().new ActualizarController());

			retorno = "ABMActualizarDeuda";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	/**
	 * Quita de la lista las liquidaciones con estado "PAGADAS" o "NO_OPTADAS", idealmente para que no vuelvan a imprimirse. No quita nada para los usuarios con
	 * Permiso AUDITH sobre las Liquidaciones.
	 * 
	 * @param pListaLiquidaciones
	 */
	private List<LiquidacionTasaRefer> quitarLiquidacionesPagadas(List<LiquidacionTasaRefer> pListaLiquidaciones) {
		List<LiquidacionTasaRefer> locListaResultado = new ArrayList<LiquidacionTasaRefer>();
		for(Iterator<LiquidacionTasaRefer> it = pListaLiquidaciones.iterator(); it.hasNext();) {
			LiquidacionTasaRefer liquidacionTasaRefer = it.next();
			if(!liquidacionTasaRefer.getEstado().equals("PAGADA") && !liquidacionTasaRefer.getEstado().equals("NO_OPTADA")) {
				locListaResultado.add(liquidacionTasaRefer);
			}
		}
		return locListaResultado;
	}

	private List<LiquidacionTasaRefer> quitarLiquidacionesPagadasSegunPermiso(List<LiquidacionTasaRefer> pListaLiquidaciones) {
		if(!this.getCommunicationSAICBean().getEsAdministradorLiquidacionesTasasUnificadas()) {
			return quitarLiquidacionesPagadas(pListaLiquidaciones);
		}
		return pListaLiquidaciones;
	}

	public String btnImprimirSeleccionadas_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		
		if(ultimo) {
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			List liquidaciones = new ArrayList();
			if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
				liquidaciones = new ArrayList(this.getListaSeleccionados());
			} else {
				try {
					RowKey rk = this.getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());
						Object obj = this.getObjectListDataProvider().getObjects()[index];
						liquidaciones.add(obj);

						this.guardarOrdenamiento();
						Long pos = this.getPosicionEnTabla(rk);
						this.getElementoPila().setPosicionGlobal(pos.longValue());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			liquidaciones = this.quitarLiquidacionesPagadasSegunPermiso(liquidaciones);
			if(liquidaciones.size() > 0) {
				try {
					this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
					Usuario usuario = this.getSessionBean1().getUsuario();
					FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
					List<TipoObligacion> listaTipoTasa = locFiltro.getListaTipoObligacion();
					JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteTasaUnificada(liquidaciones, listaTipoTasa, usuario);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Liquidaciones");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
				} catch(Exception ex) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
					log(CASO_NAVEGACION + "_ImprimirLiquidacionesSHPS", ex);
					error(NOMBRE_PAGINA + " - Imprimir: " + ex.getMessage());
				}
			} else {
				warn("Debe seleccionar una liquidación");
			}
		}
		return null;
	}

	public String btnMarcarPagada_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		
		if(ultimo) {
			List seleccionados = new ArrayList();
			if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
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
				List<LiquidacionTasaRefer> listaLiquidacionesRefer = seleccionados;
				List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();

				for(LiquidacionTasaRefer cadaLiquidacionRefer : listaLiquidacionesRefer) {
					if(cadaLiquidacionRefer.getEstado().equals("PAGADA") || cadaLiquidacionRefer.getEstado().equals("RELIQUIDADA")) {
						warn("Las Liquidaciones seleccionadas no deben estar en estado PAGADA o RELIQUIDADA");
						return null;
					}
					try {
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
						listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente()
								.inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
					} catch(RemoteException e) {
						e.printStackTrace();
					}
				}

				this.getRequestBean1().setObjetoSeleccion(listaLiquidacionesAgrupadas);
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

		this.guardarEstadoObjetosUsados();
		
		if(ultimo) {
			List seleccionados = new ArrayList();
			if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
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
				List<LiquidacionTasaRefer> listaLiquidacionesRefer = seleccionados;
				List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();

				for(LiquidacionTasaRefer cadaLiquidacionRefer : listaLiquidacionesRefer) {
					if(!cadaLiquidacionRefer.getEstado().equals("PAGADA")) {
						warn("Las Liquidaciones seleccionadas deben estar en estado PAGADA");
						return null;
					}
					try {
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
						listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente()
								.inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
					} catch(RemoteException e) {
						e.printStackTrace();
					}
				}

				this.getRequestBean1().setObjetoSeleccion(listaLiquidacionesAgrupadas);
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

	public String btnEliminarLiquidacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			List seleccionados = new ArrayList();
			if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
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
				List<LiquidacionTasaRefer> listaLiquidacionesRefer = seleccionados;
				List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();

				for(LiquidacionTasaRefer cadaLiquidacionRefer : listaLiquidacionesRefer) {
					try {
						this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
						listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente()
								.inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
					} catch(RemoteException e) {
						e.printStackTrace();
					}
				}

				this.getRequestBean1().setObjetoSeleccion(listaLiquidacionesAgrupadas);
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

	public String btnModificarLiquidacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			List seleccionados = new ArrayList();
			if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
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

			List<LiquidacionTasaRefer> listaLiquidacionesRefer = seleccionados;
			List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();

			for(LiquidacionTasaRefer cadaLiquidacionRefer : listaLiquidacionesRefer) {
				try {
					this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
					listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
				} catch(RemoteException e) {
					e.printStackTrace();
				}
			}

			if(listaLiquidacionesAgrupadas.size() == 1) {
				// this.getRequestBean1().setObjetosSeleccionMultiple((ArrayList) liquidaciones);
				this.getRequestBean1().setObjetoABM(listaLiquidacionesAgrupadas);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new LiquidacionModel().new ModificarLiquidacionController());

				retorno = "ABMLiquidacion";
			} else if(listaLiquidacionesAgrupadas.size() > 1) {
				this.getRequestBean1().setObjetoABM(listaLiquidacionesAgrupadas);
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

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return null;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().findListaLiquidacionTasaRefer((FiltroLiquidacionTasaRefer) pFiltro);
	}

	@Override
	protected String getNombrePagina() {
		return "Imprimir Liquidaciones";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminImprimirLiquidaciones";
	}

	@Override
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
				if(!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	@Override
	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	@Override
	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public List<RowKey> getSeleccionados() {
		List<RowKey> rowKeys = new ArrayList();
		try {
			List seleccionados = Checkbox.getSelected("buttonGroupCB");
			for(Object cadaObject : seleccionados) {
				String aRowId = cadaObject.toString();
				rowKeys.add(this.getObjectListDataProvider().getRowKey(aRowId));
			}
		} catch(Exception ex) {
		}
		return rowKeys;
	}

	private RowKey rowKeySeleccionado = null;

	@Override
	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	@Override
	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	@Override
	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	@Override
	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public String btnCambiarSeleccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
				this.getBtnSeleccionarTodas().setRendered(false);
				this.getCommunicationSAICBean().setSeleccionMultipleImprimirLiquidaciones(false);
				this.getTableColumn1().setRendered(true);
				this.getTcCheckbox().setRendered(false);
			} else {
				this.getBtnSeleccionarTodas().setRendered(true);
				this.getCommunicationSAICBean().setSeleccionMultipleImprimirLiquidaciones(true);
				this.getTableColumn1().setRendered(false);
				this.getTcCheckbox().setRendered(true);
				this.setListaSeleccionados(new HashSet());
				this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleImprimirLiquidaciones(new HashSet());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnImprimirEnServidor_action() {
		// CAMBIAR:
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Usuario usuario = this.getSessionBean1().getUsuario();

		if(ultimo) {
			// APLICAR LOGICA AQUI...
			// ariel - no guardar. utilizar lo ya guardado (con resultado de la busqueda)
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			try {
				this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
				// Thread locThread = new Thread(new ImpresorEnServidorRunnable(
				// this.getCommunicationSAICBean().getRemoteSystemImpresion(), this.getListaDelCommunication()));
				// locThread.start();
				List<LiquidacionTasaRefer> locListaLiquidaciones = this.getListaDelCommunication();
				locListaLiquidaciones = this.quitarLiquidacionesPagadas(locListaLiquidaciones);
				this.getCommunicationSAICBean().getRemoteSystemImpresion().imprimirLiquidacionesEnServidor(locListaLiquidaciones, usuario);
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_ImprimirLiquidacionesSHPS", ex);
				error(NOMBRE_PAGINA + " - Imprimir: " + ex.getMessage());
			}
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());
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
			if(this.getCommunicationSAICBean().isSeleccionMultipleImprimirLiquidaciones()) {
				this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleImprimirLiquidaciones(new HashSet(this.getObjectListDataProvider().getList()));
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnExportarPDF_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			try {
				TableRowGroup trg;
				List locListaResultados;
				if(this.getPaginatedTable() != null) {
					trg = this.getPaginatedTable().getTableRowGroup();
					// FiltroAbstracto filtro = this.getPaginatedTable().getFiltro();
					FiltroLiquidacionTasaRefer filtro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);

					// Se guardan la cantidad de paginas y el numero de las misma en la paginacion del filtro para su posterior re asignacion
					int auxCantidad = filtro.getCantidadPorPagina();
					int auxNumeroPagina = filtro.getNumeroPagina();

					// Se establece como parametros de busqueda valores nulos para garantizar que busca todos los datos
					filtro.setCantidadPorPagina(null);
					filtro.setNumeroPagina(null);

					// se guarda en una variable los datos de la busqueda completa
					FiltroAbstracto auxFiltro = this.buscar(filtro);
					locListaResultados = auxFiltro.getListaResultados();

					// Se retorna los valores de filtrado al filtro
					filtro.setCantidadPorPagina(auxCantidad);
					filtro.setNumeroPagina(auxNumeroPagina);
				} else {
					trg = this.getTableRowGroup1();
					locListaResultados = this.getListaDelCommunication();
				}

				JasperPrint jp = ImpresionReporteDinamico.imprimirLista(locListaResultados, trg, "Reporte Din\341mico");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
			} catch(Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public boolean isRenderPanelAdminLiquidaciones() {
		return this.getCommunicationSAICBean().getEsAdministradorLiquidacionesTasasUnificadas();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			locFiltro.setPersona(persona);
			this.getSessionBean1().setPersonaSeleccionada(persona);
		}
		if(pObject instanceof Parcela) {
			Parcela parcela = (Parcela) pObject;
			locFiltro.setParcela(parcela);
			this.getSessionBean1().setParcelaSeleccionada(parcela);
		}
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		this.setIdSubSesion(Long.parseLong(pIdSubSession));
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		Long id = Long.parseLong(pId);
		Persona locPersona = null;

		try {
			locPersona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		locFiltro.setPersona(locPersona);
		this.getSessionBean1().setPersonaSeleccionada(locPersona);
		this.getElementoPila().getObjetos().set(0, locFiltro);
	}

	public void setParcelaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		this.setIdSubSesion(Long.parseLong(pIdSubSession));
		FiltroLiquidacionTasaRefer locFiltro = this.obtenerObjetoDelElementoPila(0, FiltroLiquidacionTasaRefer.class);
		Long id = Long.parseLong(pId);
		Parcela locParcela = null;

		try {
			locParcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(TrascenderException e) {
			e.printStackTrace();
		}

		locFiltro.setParcela(locParcela);
		this.getSessionBean1().setParcelaSeleccionada(locParcela);
		this.getElementoPila().getObjetos().set(0, locFiltro);
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0l;
		// return LiquidacionTasa.codigoTasasUnificadas;
	}

	public String btnEstadoDeuda_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarEstadoObjetosUsados();
			List<LiquidacionTasaRefer> liquidaciones = Util.castearLista(this.getObjectListDataProvider().getList());
			for(Iterator<LiquidacionTasaRefer> iterator = liquidaciones.iterator(); iterator.hasNext();) {
				LiquidacionTasaRefer liquidacionTasaRefer = iterator.next();
				if(!liquidacionTasaRefer.getEstado().equals("VENCIDA") && !liquidacionTasaRefer.getEstado().equals("VIGENTE")) {
					iterator.remove();
				}
			}
			if(liquidaciones.size() > 0) {
				try {
					this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
					this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
					List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();
					for(LiquidacionTasaRefer cadaLiquidacionRefer : liquidaciones) {
						listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente()
								.inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
					}
					JasperPrint jp = this.getCommunicationContabilidadBean().getRemoteSystemReportesContabilidad().getReporteLiquidacionDeuda(listaLiquidacionesAgrupadas);
					subirReporteASesion("Reporte_Estado_Deuda", ConstantesReportes.PDF, jp);
				} catch(Exception ex) {
					subirErrorEnReporteASesion();
					log(CASO_NAVEGACION + "_ImprimirLiquidacionesSHPS", ex);
					error(NOMBRE_PAGINA + " - Imprimir: " + ex.getMessage());
				}
			} else {
				warn("No hay liquidaciones Vencidas ni Vigentes.");
				subirErrorEnReporteASesion();
			}
		}
		return null;
	}
	
	public String btnRefinanciar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		
		if(ultimo) {
			List<LiquidacionTasaRefer> listaLiquidacionesRefer = Util.castearLista(this.getObjectListDataProvider().getList());
			List<LiquidacionTasaAgrupada> listaLiquidacionesAgrupadas = new ArrayList<LiquidacionTasaAgrupada>();

			for(Iterator<LiquidacionTasaRefer> iterator = listaLiquidacionesRefer.iterator(); iterator.hasNext();) {
				LiquidacionTasaRefer cadaLiquidacionRefer = iterator.next();
				if(!cadaLiquidacionRefer.getEstado().equals("VIGENTE") && !cadaLiquidacionRefer.getEstado().equals("VENCIDA")) {
					iterator.remove();
					continue;
				}
				try {
					this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
					listaLiquidacionesAgrupadas.add(this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().inicializarLiquidacionTasaAgrupada(cadaLiquidacionRefer));
				} catch(RemoteException e) {
					e.printStackTrace();
				}
			}
			
			if (!listaLiquidacionesAgrupadas.isEmpty()) {
				this.getRequestBean1().setObjetoABM(listaLiquidacionesAgrupadas);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setAbmController(new NotificacionModel().new NotificarController());
				retorno = "AgregarPlanPagoRefinanciacion";
			} else {
				info("No hay Liquidaciones para Refinanciar");
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	@Override
	public Label getLblNroParcela() {
		lblNroParcela.setText("Nº de Parcela /\n Inscripción");
		return lblNroParcela;
	}
}