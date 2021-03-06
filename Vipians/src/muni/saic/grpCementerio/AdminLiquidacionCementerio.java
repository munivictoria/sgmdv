
package muni.saic.grpCementerio;

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
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionCementerio;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;

public class AdminLiquidacionCementerio extends AdminPageBean {

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

		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalCementerio().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosCementerio(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalCementerio(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalCementerio(null).keySet());

		Set<String> locListaTipoSepultura = getApplicationBean1().getMapaTipoSepultura().keySet();
		Option[] opTipoSepultura = new Option[locListaTipoSepultura.size() + 1];
		int i = 0;
		opTipoSepultura[i++] = new Option("", "");
		for(String cadaTipo : locListaTipoSepultura) {
			opTipoSepultura[i++] = new Option(cadaTipo, cadaTipo);
		}
		this.ddTipoSepulturaOptions.setOptions(opTipoSepultura);

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
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosCementerio(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalCementerio(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalCementerio(pPeriodo).keySet());
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

	private String getAnioCorriente() {
		String anioActual = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString();
		Integer locAnioActualMapa = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalCementerio().get(anioActual);
		if(locAnioActualMapa != null) {
			return anioActual;
		} else {
			return this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalCementerio().keySet().iterator().next();
		}
	}

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
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

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private ObjectListDataProvider ldpLiquidacionCementerio = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLiquidacionCementerio() {
		return ldpLiquidacionCementerio;
	}

	public void setLdpLiquidacionCementerio(ObjectListDataProvider ldpLiquidacionCementerio) {
		this.ldpLiquidacionCementerio = ldpLiquidacionCementerio;
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
	private StaticText staticText5 = new StaticText();
	private StaticText staticText11 = new StaticText();
	private StaticText staticText12 = new StaticText();
	private StaticText staticText13 = new StaticText();
	private StaticText staticText1 = new StaticText();
	private StaticText staticText10 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText3 = new StaticText();
	private StaticText staticText9 = new StaticText();
	private StaticText staticText14 = new StaticText();

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText staticText14) {
		this.staticText14 = staticText14;
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
	private Label label2 = new Label();
	private Label label3 = new Label();
	private Label lblFechaReliquidacion = new Label();
	private Label label7 = new Label();
	private Label lblCalendarios = new Label();
	private Label lblPeriodos = new Label();
	private Label lblCuotas = new Label();
	private Label lblAnio = new Label();
	private Label lblPeriodo = new Label();
	private Label lblTipoSepultura = new Label();

	public Label getLblTipoSepultura() {
		return lblTipoSepultura;
	}

	public void setLblTipoSepultura(Label lblTipoSepultura) {
		this.lblTipoSepultura = lblTipoSepultura;
	}

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

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
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
	private Button btnSeleccionarPersonaJuridica = new Button();
	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarParcela = new Button();
	private Button btnImprimir = new Button();
	private Button btnGenerarLiquidaciones = new Button();
	private Button btnReliquidar = new Button();
	private Button btnActualizarDeuda = new Button();
	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();

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

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button btnSeleccionarParcela) {
		this.btnSeleccionarParcela = btnSeleccionarParcela;
	}

	// ---------------Text Fields---------------
	private TextField tfPersona = new TextField();
	private TextField tfParcela = new TextField();
	private TextField tfFechaReliquidacion = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	public TextField getTfParcela() {
		return tfParcela;
	}

	public void setTfParcela(TextField tfParcela) {
		this.tfParcela = tfParcela;
	}

	public TextField getTfFechaReliquidacion() {
		return tfFechaReliquidacion;
	}

	public void setTfFechaReliquidacion(TextField tf) {
		this.tfFechaReliquidacion = tf;
	}

	// ---------------DropDowns---------------
	private DropDown ddEstado = new DropDown();
	private DropDown ddCalendarios = new DropDown();
	private DropDown ddPeriodos = new DropDown();
	private DropDown ddCuotas = new DropDown();
	private DropDown ddAnios = new DropDown();
	private DropDown ddTipoSepultura = new DropDown();

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

	public DropDown getDdTipoSepultura() {
		return ddTipoSepultura;
	}

	public void setDdTipoSepultura(DropDown ddTipoSepultura) {
		this.ddTipoSepultura = ddTipoSepultura;
	}

	// ---------------SingleSelectOptionsLists---------------
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddAniosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddTipoSepulturaOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoSepulturaOptions() {
		return ddTipoSepulturaOptions;
	}

	public void setDdTipoSepulturaOptions(SingleSelectOptionsList ddTipoSepulturaOptions) {
		this.ddTipoSepulturaOptions = ddTipoSepulturaOptions;
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

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminLiquidacionCementerio() {
	}

	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		ep.setSeleccionMultiple(true);

		return ep;
	}

	protected void guardarEstadoObjetosUsados() {
		FiltroLiquidacionCementerio locFiltro = this.getFiltro();

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
			Integer locAnio = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalCementerio().get(anioSeleccionado);
			locFiltro.setAnio(locAnio);

			String calendarioSeleccionado = this.getDdCalendarios().getSelected().toString();
			CalendarioMunicipal locCalendario = this.getCommunicationSAICBean().getMapaCalendariosCementerio(anioSeleccionado).get(calendarioSeleccionado);
			locFiltro.setCalendario(locCalendario);

			String periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected().toString();
			PeriodoLiquidacion locPeriodo = this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalCementerio(calendarioSeleccionado).get(periodoCalendarioSeleccionado);
			locFiltro.setPeriodo(locPeriodo);

			String cuotaSeleccionada = this.getDdCuotas().getSelected().toString();
			CuotaLiquidacion locCuota = this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalCementerio(periodoCalendarioSeleccionado).get(cuotaSeleccionada);
			locFiltro.setCuota(locCuota);
		}

		if(this.getDdTipoSepultura().getSelected() != null) {
			String tipoSepulturaSeleccionada = this.getDdTipoSepultura().getSelected().toString();
			TipoSepultura locTipoSepultura = this.getApplicationBean1().getMapaTipoSepultura().get(tipoSepulturaSeleccionada);
			locFiltro.setTipoSepultura(locTipoSepultura);
		}

		locFiltro.setNoCero(this.getChkOmitir().isChecked());

		if(locFiltro.getListaAtributosDinamicos() != null) {
			List listaAtributosDinamicos = panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());
			locFiltro.setListaAtributosDinamicos(listaAtributosDinamicos);
		}
	}

	protected void mostrarEstadoObjetosUsados() {
		FiltroLiquidacionCementerio locFiltro = this.getFiltro();

		if(locFiltro.getPersona() != null && locFiltro.getPersona().getIdPersona() != -1) {
			this.getTfPersona().setText(locFiltro.getPersona().toString());
		}

		if(locFiltro.getParcelaCementerio() != null && locFiltro.getParcelaCementerio().getIdAsocRegAlicuota() != -1) {
			this.getTfParcela().setText(locFiltro.getParcelaCementerio().toString());
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

		if(locFiltro.getTipoSepultura() != null && !locFiltro.getTipoSepultura().toString().isEmpty()) {
			this.getDdTipoSepultura().setSelected(locFiltro.getTipoSepultura().toString());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstadoLiquidacion())));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstadoLiquidacion())));

		this.getDdTipo().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoLiquidacion())));
		this.getDdTipoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipoLiquidacion())));

		this.getChkOmitir().setSelected(locFiltro.isNoCero());

		if(locFiltro.getListaAtributosDinamicos() != null && locFiltro.getListaAtributosDinamicos().isEmpty()) {
			try {
				List listaAtributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoCementerio.serialVersionUID, null, true);
				if(listaAtributosDinamicos != null) {
					locFiltro.setListaAtributosDinamicos(listaAtributosDinamicos);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(), "#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());
	}

	protected void limpiarObjetosUsados() {
		FiltroLiquidacionCementerio locFiltro = this.getFiltro();
		locFiltro.setPersona(null);
		locFiltro.setParcelaCementerio(null);
		locFiltro.setTipoSepultura(null);
		locFiltro.setEstadoLiquidacion(null);
		locFiltro.setTipoLiquidacion(null);
		locFiltro.setNoCero(false);
		locFiltro.setAnio(null);
		locFiltro.setCalendario(null);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);

		this.setListaSeleccionados(new HashSet());
		this.getPaginatedTable().setSeleccionadosSeleccionMultiple(new ArrayList());

		this.seleccionarAnio(this.getAnioCorriente().toString(), true);

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().setParcelaSeleccionada(null);
		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfPersona().setText(null);
		this.getTfParcela().setText(null);
		this.getTfFechaReliquidacion().setText(null);
		this.getDdTipoSepultura().setSelected("");

		this.getDdEstado().setSelected(null);
		this.getDdEstadoDefaultOptions().setSelectedValue(null);
		this.getDdTipo().setSelected(null);
		this.getDdTipoDefaultOptions().setSelectedValue(null);

		this.getChkOmitir().setSelected(false);

		this.panelAtributoDinamico.limpiarCampos();
	}

	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLiquidacionCementerio();
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaLiquidacionesCementerio();
	}

	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaLiquidacionesCementerio(lista);
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnSeleccionarParcela_action() {
		return navegarParaSeleccionar("AdminParcelaCementerio");
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

			retorno = "GenerarLiquidacionCementerio";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationSAICBean().getTablaLiquidacionCementerio();
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
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
		return toAbm(new LiquidacionCementerioModel().new ConsultarController());
	}

	public String btnImprimir_action() {
		// CAMBIAR:
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			// APLICAR LOGICA AQUI...
			// ariel - no guardar. utilizar lo ya guardado (con resultado de la busqueda)
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			try {
				int ind = 0;
				Persona persona = this.getSessionBean1().getPersonaSeleccionada();
				RegAlicuota rubro = (RegAlicuota) this.obtenerObjetoDelElementoPila(ind++, RegAlicuota.class);

				CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
				PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
				CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);

				RegistroDeuda.EstadoRegistroDeuda estadoRegistroDeuda = (RegistroDeuda.EstadoRegistroDeuda) this
						.obtenerObjetoDelElementoPila(5, RegistroDeuda.EstadoRegistroDeuda.class);

				if(persona != null && persona.getIdPersona() == -1) {
					persona = null;
				}

				if(rubro.getIdTipoAlicuota() == -1) {
					rubro = null;
				}

				// ariel - descomentar cuando este listo
				// this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
				// this.getCommunicationSAICBean().getRemoteSystemImpresion().imprimirListadoSHPS(persona, rubro, periodo, estadoRegistroDeuda);
				// JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteSHPS(persona, rubro, cuota, estadoRegistroDeuda);

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_LiquidacionSHPS");
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
			} catch(Exception ex) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log("AdminLiquidacionCementerio" + "_ImprimirLiquidacionesSHPS", ex);
				error("Administraci\363n de Liquidaciones de Cementerio" + " - Imprimir: " + ex.getMessage());
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
			FiltroLiquidacionCementerio locFiltro = this.getFiltro();
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
			FiltroLiquidacionCementerio locFiltro = this.getFiltro();
			locFiltro.setParcelaCementerio(null);

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
				JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Liquidaciones SHPS");

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_LiquidacionesSHPS");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

			} catch(Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log("AdminLiquidacionCementerio" + "_ReporteDinamicoError: ", e);
				error("Administraci\363n de Liquidaciones de Cementerio" + " - ReporteDinamico: " + e.getMessage());
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
		return this.getCommunicationSAICBean().getEsAdministradorLiquidacionesCementerio();
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
				log("AdminLiquidacionCementerio" + "_ImprimirLiquidacionesSHPS", ex);
				error("Administraci\363n de Liquidaciones de Cementerio" + " - Imprimir: " + ex.getMessage());
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
		FiltroLiquidacionCementerio filtro = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaLiquidacionesCementerio((FiltroLiquidacionCementerio) pFiltro);
		return filtro;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroLiquidacionCementerio locFiltro = this.getFiltro();

		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getSessionBean1().setPersonaSeleccionada(persona);
			locFiltro.setPersona(persona);
		}
		if(pObject instanceof ParcelaCementerio) {
			ParcelaCementerio parcelaCementerio = (ParcelaCementerio) pObject;
			locFiltro.setParcelaCementerio(parcelaCementerio);
		}
		this.getRequestBean1().setObjetoSeleccion(null);
	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Liquidaciones de Cementerio";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminLiquidacionCementerio";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroLiquidacionCementerio locFiltro = this.getFiltro();
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
		return "#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio}";
	}

	@Override
	public long getSerialVersionUID() {
		return LiquidacionTasa.codigoCementerio;
	}
}