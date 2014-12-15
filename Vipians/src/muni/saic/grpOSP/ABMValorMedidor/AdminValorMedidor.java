/*
 * AdminValorMedidor.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.saic.grpOSP.ABMValorMedidor;

import jasper.ConstantesReportes;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.saic.recurso.filtros.FiltroValorMedidor;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminValorMedidor extends AdminPageBean {

	// Atributos propios de la pagina.
	// CAMBIAR: Ir al dise�o y vincular a campos ocultos.
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

	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalOSP().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosOSP(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalOSP(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalOSP(null).keySet());
		String opcion = getAnioCorriente();
		seleccionarAnio(opcion, true);

		this.habilitarBtnExportar();
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
		if(pCalendario != null) {
			this.ddCalendarios.setSelected(pCalendario);
			if(actualizar) {
				this.actualizarOpcionesDDPeriodo(pCalendario);
			}
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

	protected PanelGroup pgParametros = new PanelGroup();

	public PanelGroup getPgParametros() {
		return pgParametros;
	}

	public void setPgParametros(PanelGroup pgParametros) {
		this.pgParametros = pgParametros;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private ObjectListDataProvider ldpValorMedidorOSP = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpValorMedidorOSP() {
		return ldpValorMedidorOSP;
	}

	public void setLdpValorMedidorOSP(ObjectListDataProvider oldp) {
		this.ldpValorMedidorOSP = oldp;
	}

	private StaticText staticText13 = new StaticText();

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Label lblCodigoMedidor = new Label();

	public Label getLblCodigoMedidor() {
		return lblCodigoMedidor;
	}

	public void setLblCodigoMedidor(Label lblCodigoMedidor) {
		this.lblCodigoMedidor = lblCodigoMedidor;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfCodigoMedidor = new TextField();

	public TextField getTfCodigoMedidor() {
		return tfCodigoMedidor;
	}

	public void setTfCodigoMedidor(TextField tfCodigoMedidor) {
		this.tfCodigoMedidor = tfCodigoMedidor;
	}

	private TextField tfCalle = new TextField();

	public TextField getTfCalle() {
		return tfCalle;
	}

	public void setTfCalle(TextField tf) {
		this.tfCalle = tf;
	}

	private Button btnSeleccionarCalle = new Button();

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button b) {
		this.btnSeleccionarCalle = b;
	}

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup pg) {
		this.gpBotones = pg;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private Label lblPeriodo = new Label();
	private Label lblAnio = new Label();
	private Label lblCalendarios = new Label();
	private Label lblPeriodos = new Label();
	private Label lblCuotas = new Label();

	public Label getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(Label lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
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

	private DropDown ddAnios = new DropDown();
	private DropDown ddCalendarios = new DropDown();
	private DropDown ddPeriodos = new DropDown();
	private DropDown ddCuotas = new DropDown();
	private SingleSelectOptionsList ddAniosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();

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

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText st) {
		this.staticText10 = st;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfServicioOSP = new TextField();

	public TextField getTfServicioOSP() {
		return tfServicioOSP;
	}

	public void setTfServicioOSP(TextField tf) {
		this.tfServicioOSP = tf;
	}

	private Button btnSeleccionarServicioOSP = new Button();

	public Button getBtnSeleccionarServicioOSP() {
		return btnSeleccionarServicioOSP;
	}

	public void setBtnSeleccionarServicioOSP(Button b) {
		this.btnSeleccionarServicioOSP = b;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText st) {
		this.staticText12 = st;
	}

	private StaticText staticText14 = new StaticText();

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText staticText14) {
		this.staticText14 = staticText14;
	}

	private HtmlAjaxCommandButton btnLimpiarServicio = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarServicio() {
		return btnLimpiarServicio;
	}

	public void setBtnLimpiarServicio(HtmlAjaxCommandButton btnLimpiarServicio) {
		this.btnLimpiarServicio = btnLimpiarServicio;
	}

	private HtmlAjaxCommandButton btnLimpiarCalle = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarCalle() {
		return btnLimpiarCalle;
	}

	public void setBtnLimpiarCalle(HtmlAjaxCommandButton btnLimpiarCalle) {
		this.btnLimpiarCalle = btnLimpiarCalle;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminValorMedidor() {
	}

	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		FiltroValorMedidor locFiltro = this.getFiltro();

		locFiltro.setCodigoMedidor(this.getTextFieldValue(this.getTfCalle()));

		locFiltro.setAnio(this.getDDObjectValue(getDdAnios(), this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalOSP()));
		if(locFiltro.getAnio() != null && locFiltro.getAnio().toString().length() > 0) {
			locFiltro.setCalendario(this.getDDObjectValue(getDdCalendarios(), this.getCommunicationSAICBean().getMapaCalendariosOSP(locFiltro.getAnio().toString())));
		}
		if(locFiltro.getCalendario() != null) {
			locFiltro.setPeriodo(this.getDDObjectValue(getDdPeriodos(), this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalOSP(locFiltro.getCalendario().getNombre())));
		}
		if(locFiltro.getPeriodo() != null) {
			locFiltro.setCuota(this.getDDObjectValue(getDdCuotas(), this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalOSP(locFiltro.getPeriodo().toString())));
		}
	}

	protected void mostrarEstadoObjetosUsados() {
		FiltroValorMedidor locFiltro = getFiltro();

		this.getTfCodigoMedidor().setText(locFiltro.getCodigoMedidor());
		if(locFiltro.getCalle() != null) {
			this.getTfCalle().setText(locFiltro.getCalle().toString());
		}
		if(locFiltro.getServicioOSP() != null) {
			this.getTfServicioOSP().setText(locFiltro.getServicioOSP().toString());
		}

		if(locFiltro.getAnio() != null) {
			seleccionarAnio(locFiltro.getAnio().toString(), true);
		}
		if(locFiltro.getCalendario() != null && !locFiltro.getCalendario().getNombre().isEmpty()) {
			seleccionarCalendario(locFiltro.getCalendario().getNombre(), true);
		}
		if(locFiltro.getPeriodo() != null && !locFiltro.getPeriodo().toString().isEmpty()) {
			seleccionarPeriodo(locFiltro.getPeriodo().toString(), true);
		}
		if(locFiltro.getCuota() != null && !locFiltro.getCuota().toString().isEmpty()) {
			seleccionarCuota(locFiltro.getCuota().toString());
		}
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationSAICBean().getTablaValorMedidor();
	}

	protected void limpiarObjetosUsados() {
		FiltroValorMedidor locFiltro = this.getFiltro();
		locFiltro.setAnio(null);
		locFiltro.setCalendario(null);
		locFiltro.setPeriodo(null);
		locFiltro.setCuota(null);
		locFiltro.setCalle(null);
		locFiltro.setCodigoMedidor(null);
		locFiltro.setServicioOSP(null);

		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfCodigoMedidor().setText(null);
		this.getTfCalle().setText(null);
		this.seleccionarAnio(this.getAnioCorriente().toString(), true);
		this.getDdPeriodos().setSelected(null);
		this.getDdCuotas().setSelected(null);
		this.getTfServicioOSP().setText(null);
	}

	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpValorMedidorOSP();
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaValoresMedidoresOSP();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaValoresMedidoresOSP(lista);
	}

	public String btnSeleccionarCalle_action() {
		return navegarParaSeleccionar("AdminCalle");
	}

	public String btnSeleccionarServicioOSP_action() {
		return navegarParaSeleccionar("AdminServicioOSP");
	}

	// public String btnBuscar_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	// if(ultimo) {
	//
	// try {
	// this.guardarEstadoObjetosUsados();
	//
	// CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(1, CalendarioMunicipal.class);
	// PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(2, PeriodoLiquidacion.class);
	// CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(3, CuotaLiquidacion.class);
	//
	// Object calendarioSeleccionado = this.getDdCalendarios().getSelected();
	// Object periodoCalendarioSeleccionado = this.getDdPeriodos().getSelected();
	// Object cuotaSeleccionada = this.getDdCuotas().getSelected();
	//
	// if((calendarioSeleccionado != null) && (calendarioSeleccionado.toString().length() > 0)) {
	// // calendario = getCalendarioPorNombre(calendarioSeleccionado.toString());
	//
	// if((periodoCalendarioSeleccionado != null) && (periodoCalendarioSeleccionado.toString().length() > 0)) {
	// periodoCalendario = getPeriodoPorNombre(calendario, periodoCalendarioSeleccionado.toString());
	//
	// if((cuotaSeleccionada != null) && (cuotaSeleccionada.toString().length() > 0)) {
	// cuota = this.getCuotaPorNombre(periodoCalendario, cuotaSeleccionada.toString());
	// } else {
	// cuota = null;
	// }
	// }
	// }
	//
	// // if(cuota != null && cuota.getIdPeriodo() != -1) {
	// // System.out.println("btnBuscar_action() -> Periodo seleccionado");
	// // } else {
	// // warn("Debe seleccionar un Per\355odo para realizar la busqueda.");
	// // return null;
	// // }
	//
	// // Object servicioOSPTexto = this.getTfServicioOSP().getText();
	// // if ( (servicioOSPTexto == null || servicioOSPTexto.toString().length()==0) ) {
	// // warn("Debe seleccionar un Servicio para realizar la b\372squeda.");
	// // return null;
	// // }
	//
	// this.refrescarTabla();
	// this.guardarOrdenamiento();
	// Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
	// this.getElementoPila().setPosicionGlobal(pos.longValue());
	//
	// } catch(Exception ex) {
	// this.limpiarTabla();
	// log(CASO_NAVEGACION + "_BuscarError:", ex);
	// error(NOMBRE_PAGINA + " - Buscar: " + ex.getMessage());
	// }
	//
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	// return retorno;
	// }

	// public String btnReiniciar_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	// if(ultimo) {
	// try {
	// this.limpiarObjetosUsados();
	// this.limpiarTabla();
	// } catch(Exception ex) {
	// this.limpiarTabla();
	// log(CASO_NAVEGACION + "_ReiniciarError:", ex);
	// error(NOMBRE_PAGINA + " - Reiniciar: " + ex.getMessage());
	// }
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	// return retorno;
	// }

	// public String btnCancelar_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	// if(ultimo) {
	// this.setListaDelCommunication(null);
	// retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	//
	// return retorno;
	// }

	// public String btnSeleccionar_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	// if(ultimo) {
	// RowKey rk = null;
	//
	// try {
	// rk = this.getSeleccionado();
	//
	// if(rk != null) {
	// int index = getNroFila(rk.toString());
	// Object obj = this.getObjectListDataProvider().getObjects()[index];
	// getRequestBean1().setObjetoSeleccion(obj);
	//
	// this.setRowKeySeleccionado(this.getSeleccionado());
	// }
	//
	// } catch(Exception ex) {
	// log(CASO_NAVEGACION + "_SeleccionarError:", ex);
	// error(NOMBRE_PAGINA + " - Seleccionar: " + ex.getMessage());
	// }
	//
	// if(rk != null) {
	// ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
	// if(locElementoAnterior == null) {
	// return null;
	// }
	// retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
	// }
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	// return retorno;
	// }

	public String btnAgregar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// Para pasar varios objetos a la p�gina de AgregarValorMedidor
		int ind = 0;

		if(ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			retorno = "AgregarValorMedidor";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnModificar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];

					if(!(((ValorMedidor) obj).getEstado().equals(ValorMedidor.Estado.CARGADO))) {
						warn("S\363lo se pueden modificar las Mediciones de Medidor que est\351n en estado 'Cargado'.");
						return null;
					}

					this.getRequestBean1().setObjetoABM(obj);
					this.setRowKeySeleccionado(this.getSeleccionado());

				}

			} catch(Exception ex) {
				log("AdminValorMedidor" + "_ModificarError:", ex);
				error("Administraci\363n de Mediciones de los Medidores" + " - Modificar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null)
				retorno = "ModificarValorMedidor";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnEliminar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];

					getRequestBean1().setObjetoABM(obj);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log("AdminValorMedidor" + "_EliminarError:", ex);
				error("Administraci\363n de Mediciones de los Medidores" + " - Eliminar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null)
				retorno = null;
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnConsultar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
					ValorMedidor locValorMedidor = (ValorMedidor) obj;
					locValorMedidor = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getValorMedidorPorId(locValorMedidor.getIdRegistroValuado());

					this.getRequestBean1().setObjetoABM(locValorMedidor);
					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log("AdminValorMedidor" + "_ConsultarError:", ex);
				error("Administraci\363n de Mediciones de los Medidores" + " - Consultar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());
			if(rk != null)
				retorno = "ConsultarValorMedidor";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarServicio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			FiltroValorMedidor locFiltro = this.getFiltro();
			this.getTfServicioOSP().setText("");
			locFiltro.setServicioOSP(null);
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
			FiltroValorMedidor locFiltro = this.getFiltro();
			this.getTfCalle().setText("");
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
				JasperPrint jp = ImpresionReporteDinamico.imprimirLista(this.getListaDelCommunication(), this.getTableRowGroup1(), "Reporte Din\341mico de Valores de Medidores");

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.XLSX);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_ValoresMedidores");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);

			} catch(Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log("AdminValorMedidor" + "_ReporteDinamicoError: ", e);
				error("Administraci\363n de Mediciones de los Medidores" + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	// Metodo para habilitar el boton exportar cuando corresponda
	public void habilitarBtnExportar() {
		this.btnExportar.setDisabled(true);
		if(getListaDelCommunication() != null) {
			if(getListaDelCommunication().isEmpty()) {
				this.btnExportar.setDisabled(true);
			} else {
				this.btnExportar.setDisabled(false);
			}
		}
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ValorMedidor locValorMedidor = (ValorMedidor) pObject;
		getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(getSessionBean1().getLlave());
		locValorMedidor = getCommunicationSAICBean().getRemoteSystemRegistroValuado().getValorMedidorPorId(locValorMedidor.getIdRegistroValuado());
		return locValorMedidor;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
		FiltroValorMedidor locFiltro = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().findListaValoresMedidor((FiltroValorMedidor) pFiltro);
		return locFiltro;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroValorMedidor locFiltro = getFiltro();
		if(pObject instanceof Calle) {
			Calle locCalle = (Calle) pObject;
			locFiltro.setCalle(locCalle);
		}
		if(pObject instanceof ServicioOSP) {
			ServicioOSP locServicioOSP = (ServicioOSP) pObject;
			locFiltro.setServicioOSP(locServicioOSP);
		}
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Mediciones de los Medidores";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminValorMedidor";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	public String getNombreBean() {
		return "#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor}";
	}

	@Override
	public long getSerialVersionUID() {
		return ValorMedidor.serialVersionUID;
	}
}