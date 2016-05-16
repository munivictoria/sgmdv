package muni.excepciones.ABMRefinanciacion;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegCancelacionPorRefinanciacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;

public class ABMRefinanciacion extends ABMPageBean {

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Consultar Refinanciaci\363n";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "ConsultarRefinanciacion";

	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProviderTabla1().setList(this.getListaDelCommunication());
		}
		if(this.getListaDelCommunication2() != null) {
			this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunication2());
		}
//		if(this.getCommunicationHabilitacionesBean().getListaLibresDeuda() != null) {
//			this.getLdpLibresDeuda().setList(this.getCommunicationHabilitacionesBean().getListaLibresDeuda());
//		}

		numberConverter1.setPattern("$ #,##0.00");

		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yy");
		dateTimeConverter1.setTimeStyle("full");
		dateTimeConverter2.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		dateTimeConverter2.setPattern("dd/MM/yyyy HH:mm");

		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoTasa().keySet().toArray(), "cap");
		ddTipoObligacionDefaultOptions.setOptions(op);
	}

	private TextField tfFechaActualizacionDeuda = new TextField();
	private Checkbox cbAplicarIntereses = new Checkbox();
	private PanelGroup groupPanel1 = new PanelGroup();
	private HtmlAjaxCommandButton btnActualizarDeuda = new HtmlAjaxCommandButton();
	private Checkbox cbSeleccion = new Checkbox();
	
	public TextField getTfFechaActualizacionDeuda() {
		return tfFechaActualizacionDeuda;
	}

	public void setTfFechaActualizacionDeuda(TextField tfFechaActualizacionDeuda) {
		this.tfFechaActualizacionDeuda = tfFechaActualizacionDeuda;
	}

	public Checkbox getCbAplicarIntereses() {
		return cbAplicarIntereses;
	}

	public void setCbAplicarIntereses(Checkbox cbAplicarIntereses) {
		this.cbAplicarIntereses = cbAplicarIntereses;
	}

	public Checkbox getCbSeleccion() {
		return cbSeleccion;
	}

	public void setCbSeleccion(Checkbox cbSeleccion) {
		this.cbSeleccion = cbSeleccion;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public HtmlAjaxCommandButton getBtnActualizarDeuda() {
		return btnActualizarDeuda;
	}

	public void setBtnActualizarDeuda(HtmlAjaxCommandButton btnActualizarDeuda) {
		this.btnActualizarDeuda = btnActualizarDeuda;
	}

	private TextField tfDeudaAlDia = new TextField();

	public TextField getTfDeudaAlDia() {
		return tfDeudaAlDia;
	}

	public void setTfDeudaAlDia(TextField tfDeudaAlDia) {
		this.tfDeudaAlDia = tfDeudaAlDia;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	private ObjectListDataProvider ldpObligaciones = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpObligaciones() {
		return ldpObligaciones;
	}

	public void setLdpObligaciones(ObjectListDataProvider oldp) {
		this.ldpObligaciones = oldp;
	}

	private Table table2 = new Table();

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private TableRowGroup tableRowGroup2 = new TableRowGroup();

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private NumberConverter numberConverter1 = new NumberConverter();

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
		return ddTipoObligacionDefaultOptions;
	}

	public void setDdTipoObligacionDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoObligacionDefaultOptions = ssol;
	}

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup gpBotones) {
		this.gpBotones = gpBotones;
	}

	private PanelGroup panelGroup1 = new PanelGroup();

	public PanelGroup getPanelGroup1() {
		return panelGroup1;
	}

	public void setPanelGroup1(PanelGroup pg) {
		this.panelGroup1 = pg;
	}

	private ObjectListDataProvider ldpCuotasGeneradas = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCuotasGeneradas() {
		return ldpCuotasGeneradas;
	}

	public void setLdpCuotasGeneradas(ObjectListDataProvider ldpCuotasGeneradas) {
		this.ldpCuotasGeneradas = ldpCuotasGeneradas;
	}

	private ObjectListDataProvider ldpPeriodosAdeudados = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPeriodosAdeudados() {
		return ldpPeriodosAdeudados;
	}

	public void setLdpPeriodosAdeudados(ObjectListDataProvider oldp) {
		this.ldpPeriodosAdeudados = oldp;
	}

	public TableSelectPhaseListener getTablePhaseListener() {
		return tablePhaseListener;
	}

	public void setTablePhaseListener(TableSelectPhaseListener tablePhaseListener) {
		this.tablePhaseListener = tablePhaseListener;
	}

	public TableSelectPhaseListener getTablePhaseListenerTabla1() {
		return tablePhaseListenerTabla1;
	}

	public void setTablePhaseListenerTabla1(TableSelectPhaseListener tablePhaseListenerTabla1) {
		this.tablePhaseListenerTabla1 = tablePhaseListenerTabla1;
	}

	// ----------------StaticTexts----------------
	private StaticText staticText15 = new StaticText();
	private StaticText staticText14 = new StaticText();
	private StaticText staticText13 = new StaticText();
	private StaticText staticText12 = new StaticText();
	private StaticText staticText11 = new StaticText();
	private StaticText staticText10 = new StaticText();
	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText staticText14) {
		this.staticText14 = staticText14;
	}

	public StaticText getStaticText15() {
		return staticText15;
	}

	public void setStaticText15(StaticText staticText15) {
		this.staticText15 = staticText15;
	}

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText staticText8) {
		this.staticText8 = staticText8;
	}

	private StaticText stSeparador1 = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stSeparador3 = new StaticText();
	private StaticText stSeparador4 = new StaticText();
	private StaticText stSeparador5 = new StaticText();
	private StaticText stSeparador6 = new StaticText();
	private StaticText stSeparador7 = new StaticText();
	private StaticText stSeparador8 = new StaticText();
	private StaticText stSeparador9 = new StaticText();
	private StaticText stSeparador10 = new StaticText();
	private StaticText stSeparador11 = new StaticText();
	private StaticText stSeparador12 = new StaticText();

	public StaticText getStSeparador11() {
		return stSeparador11;
	}

	public void setStSeparador11(StaticText stSeparador11) {
		this.stSeparador11 = stSeparador11;
	}

	public StaticText getStSeparador12() {
		return stSeparador12;
	}

	public void setStSeparador12(StaticText stSeparador12) {
		this.stSeparador12 = stSeparador12;
	}

	public StaticText getStSeparador10() {
		return stSeparador10;
	}

	public void setStSeparador10(StaticText stSeparador10) {
		this.stSeparador10 = stSeparador10;
	}

	public StaticText getStSeparador7() {
		return stSeparador7;
	}

	public void setStSeparador7(StaticText stSeparador7) {
		this.stSeparador7 = stSeparador7;
	}

	public StaticText getStSeparador8() {
		return stSeparador8;
	}

	public void setStSeparador8(StaticText stSeparador8) {
		this.stSeparador8 = stSeparador8;
	}

	public StaticText getStSeparador9() {
		return stSeparador9;
	}

	public void setStSeparador9(StaticText stSeparador9) {
		this.stSeparador9 = stSeparador9;
	}

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public StaticText getStSeparador3() {
		return stSeparador3;
	}

	public void setStSeparador3(StaticText stSeparador3) {
		this.stSeparador3 = stSeparador3;
	}

	public StaticText getStSeparador4() {
		return stSeparador4;
	}

	public void setStSeparador4(StaticText stSeparador4) {
		this.stSeparador4 = stSeparador4;
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public StaticText getStSeparador6() {
		return stSeparador6;
	}

	public void setStSeparador6(StaticText stSeparador6) {
		this.stSeparador6 = stSeparador6;
	}

	private StaticText stNombreRefinanciacion = new StaticText();

	public StaticText getStNombreRefinanciacion() {
		return stNombreRefinanciacion;
	}

	public void setStNombreRefinanciacion(StaticText stNombreRefinanciacion) {
		this.stNombreRefinanciacion = stNombreRefinanciacion;
	}

	private StaticText stTitulo = new StaticText();

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText staticText7) {
		this.staticText7 = staticText7;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	// ----------------TextAreas----------------
	private TextArea taComercios = new TextArea();

	public TextArea getTaComercios() {
		return taComercios;
	}

	public void setTaComercios(TextArea taComercios) {
		this.taComercios = taComercios;
	}

	private TextArea taInmuebles = new TextArea();

	public TextArea getTaInmuebles() {
		return taInmuebles;
	}

	public void setTaInmuebles(TextArea taInmuebles) {
		this.taInmuebles = taInmuebles;
	}

	// ----------------TextFields----------------
	private TextField tfTotalCondonado = new TextField();

	public TextField getTfTotalCondonado() {
		return tfTotalCondonado;
	}

	public void setTfTotalCondonado(TextField tfTotalCondonado) {
		this.tfTotalCondonado = tfTotalCondonado;
	}

	private TextField tfMontoTotal = new TextField();

	public TextField getTfMontoTotal() {
		return tfMontoTotal;
	}

	public void setTfMontoTotal(TextField tfMontoTotal) {
		this.tfMontoTotal = tfMontoTotal;
	}

	private TextField tfEstado = new TextField();

	public TextField getTfEstado() {
		return tfEstado;
	}

	public void setTfEstado(TextField tfEstado) {
		this.tfEstado = tfEstado;
	}

	private TextField tfNombreRefinanciacion = new TextField();

	public TextField getTfNombreRefinanciacion() {
		return tfNombreRefinanciacion;
	}

	public void setTfNombreRefinanciacion(TextField tfNombreRefinanciacion) {
		this.tfNombreRefinanciacion = tfNombreRefinanciacion;
	}

	private TextField tfCapitalRefinanciado = new TextField();

	public TextField getTfCapitalRefinanciado() {
		return tfCapitalRefinanciado;
	}

	public void setTfCapitalRefinanciado(TextField tfCapitalRefinanciado) {
		this.tfCapitalRefinanciado = tfCapitalRefinanciado;
	}

	private TextField tfMultaRefinanciacion = new TextField();

	public TextField getTfMultaRefinanciacion() {
		return tfMultaRefinanciacion;
	}

	public void setTfMultaRefinanciacion(TextField tfMultaRefinanciacion) {
		this.tfMultaRefinanciacion = tfMultaRefinanciacion;
	}

	private TextField tfMultaCondonado = new TextField();

	public TextField getTfMultaCondonado() {
		return tfMultaCondonado;
	}

	public void setTfMultaCondonado(TextField tfMultaCondonado) {
		this.tfMultaCondonado = tfMultaCondonado;
	}

	private TextField tfMultaTotal = new TextField();

	public TextField getTfMultaTotal() {
		return tfMultaTotal;
	}

	public void setTfMultaTotal(TextField tfMultaTotal) {
		this.tfMultaTotal = tfMultaTotal;
	}

	private TextField tfRecargoRefinanciacion = new TextField();

	public TextField getTfRecargoRefinanciacion() {
		return tfRecargoRefinanciacion;
	}

	public void setTfRecargoRefinanciacion(TextField tfRecargoRefinanciacion) {
		this.tfRecargoRefinanciacion = tfRecargoRefinanciacion;
	}

	private TextField tfRecargoCondonado = new TextField();

	public TextField getTfRecargoCondonado() {
		return tfRecargoCondonado;
	}

	public void setTfRecargoCondonado(TextField tfRecargoCondonado) {
		this.tfRecargoCondonado = tfRecargoCondonado;
	}

	private TextField tfRecargoTotal = new TextField();

	public TextField getTfRecargoTotal() {
		return tfRecargoTotal;
	}

	public void setTfRecargoTotal(TextField tfRecargoTotal) {
		this.tfRecargoTotal = tfRecargoTotal;
	}

	private TextField tfInteresRefinanciacion = new TextField();

	public TextField getTfInteresRefinanciacion() {
		return tfInteresRefinanciacion;
	}

	public void setTfInteresRefinanciacion(TextField tfInteresRefinanciacion) {
		this.tfInteresRefinanciacion = tfInteresRefinanciacion;
	}

	private TextField tfInteresCondonado = new TextField();

	public TextField getTfInteresCondonado() {
		return tfInteresCondonado;
	}

	public void setTfInteresCondonado(TextField tfInteresCondonado) {
		this.tfInteresCondonado = tfInteresCondonado;
	}

	private TextField tfInteresTotal = new TextField();

	public TextField getTfInteresTotal() {
		return tfInteresTotal;
	}

	public void setTfInteresTotal(TextField tfInteresTotal) {
		this.tfInteresTotal = tfInteresTotal;
	}

	private TextField tfSubTotal = new TextField();

	public TextField getTfSubTotal() {
		return tfSubTotal;
	}

	public void setTfSubTotal(TextField tfSubTotal) {
		this.tfSubTotal = tfSubTotal;
	}

	private TextField tfImporteCondonado = new TextField();

	public TextField getTfImporteCondonado() {
		return tfImporteCondonado;
	}

	public void setTfImporteCondonado(TextField tfImporteCondonado) {
		this.tfImporteCondonado = tfImporteCondonado;
	}

	private TextField tfImporteTotal = new TextField();

	public TextField getTfImporteTotal() {
		return tfImporteTotal;
	}

	public void setTfImporteTotal(TextField tfImporteTotal) {
		this.tfImporteTotal = tfImporteTotal;
	}

	private TextField tfInteresDiario = new TextField();

	public TextField getTfInteresDiario() {
		return tfInteresDiario;
	}

	public void setTfInteresDiario(TextField tfInteresDiario) {
		this.tfInteresDiario = tfInteresDiario;
	}

	private TextField tfTasaNominal = new TextField();

	public TextField getTfTasaNominal() {
		return tfTasaNominal;
	}

	public void setTfTasaNominal(TextField tfTasaNominal) {
		this.tfTasaNominal = tfTasaNominal;
	}

	private TextField tfCantCuotas = new TextField();

	public TextField getTfCantCuotas() {
		return tfCantCuotas;
	}

	public void setTfCantCuotas(TextField tfCantCuotas) {
		this.tfCantCuotas = tfCantCuotas;
	}

	private TextField tfDigesto = new TextField();

	public TextField getTfDigesto() {
		return tfDigesto;
	}

	public void setTfDigesto(TextField tfDigesto) {
		this.tfDigesto = tfDigesto;
	}

	private TextField tfFechaRefinanciacion = new TextField();

	public TextField getTfFechaRefinanciacion() {
		return tfFechaRefinanciacion;
	}

	public void setTfFechaRefinanciacion(TextField tfFechaRefinanciacion) {
		this.tfFechaRefinanciacion = tfFechaRefinanciacion;
	}

	private TextField tfNroRefinanciacion = new TextField();

	public TextField getTfNroRefinanciacion() {
		return tfNroRefinanciacion;
	}

	public void setTfNroRefinanciacion(TextField tfNroRefinanciacion) {
		this.tfNroRefinanciacion = tfNroRefinanciacion;
	}

	private TextField tfContribuyente = new TextField();

	public TextField getTfContribuyente() {
		return tfContribuyente;
	}

	public void setTfContribuyente(TextField tf) {
		this.tfContribuyente = tf;
	}

	// ----------------Labels----------------
	private Label lblTotalCondonado = new Label();

	public Label getLblTotalCondonado() {
		return lblTotalCondonado;
	}

	public void setLblTotalCondonado(Label lblTotalCondonado) {
		this.lblTotalCondonado = lblTotalCondonado;
	}

	private Label lblMontoTotal = new Label();

	public Label getLblMontoTotal() {
		return lblMontoTotal;
	}

	public void setLblMontoTotal(Label lblMontoTotal) {
		this.lblMontoTotal = lblMontoTotal;
	}

	private Label lblCuotasGeneradas = new Label();

	public Label getLblCuotasGeneradas() {
		return lblCuotasGeneradas;
	}

	public void setLblCuotasGeneradas(Label lblCuotasGeneradas) {
		this.lblCuotasGeneradas = lblCuotasGeneradas;
	}

	private Label lblEstado = new Label();

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	private Label lblResumenDeuda = new Label();

	public Label getLblResumenDeuda() {
		return lblResumenDeuda;
	}

	public void setLblResumenDeuda(Label lblResumenDeuda) {
		this.lblResumenDeuda = lblResumenDeuda;
	}

	private Label lblCapitalRefinanciado = new Label();

	public Label getLblCapitalRefinanciado() {
		return lblCapitalRefinanciado;
	}

	public void setLblCapitalRefinanciado(Label lblCapitalRefinanciado) {
		this.lblCapitalRefinanciado = lblCapitalRefinanciado;
	}

	private Label lblMultaRefinanciacion = new Label();

	public Label getLblMultaRefinanciacion() {
		return lblMultaRefinanciacion;
	}

	public void setLblMultaRefinanciacion(Label lblMultaRefinanciacion) {
		this.lblMultaRefinanciacion = lblMultaRefinanciacion;
	}

	private Label lblMultaCondonado = new Label();

	public Label getLblMultaCondonado() {
		return lblMultaCondonado;
	}

	public void setLblMultaCondonado(Label lblMultaCondonado) {
		this.lblMultaCondonado = lblMultaCondonado;
	}

	private Label lblMultaTotal = new Label();

	public Label getLblMultaTotal() {
		return lblMultaTotal;
	}

	public void setLblMultaTotal(Label lblMultaTotal) {
		this.lblMultaTotal = lblMultaTotal;
	}

	private Label lblRecargoRefinanciacion = new Label();

	public Label getLblRecargoRefinanciacion() {
		return lblRecargoRefinanciacion;
	}

	public void setLblRecargoRefinanciacion(Label lblRecargoRefinanciacion) {
		this.lblRecargoRefinanciacion = lblRecargoRefinanciacion;
	}

	private Label lblRecargoCondonado = new Label();

	public Label getLblRecargoCondonado() {
		return lblRecargoCondonado;
	}

	public void setLblRecargoCondonado(Label lblRecargoCondonado) {
		this.lblRecargoCondonado = lblRecargoCondonado;
	}

	private Label lblRecargoTotal = new Label();

	public Label getLblRecargoTotal() {
		return lblRecargoTotal;
	}

	public void setLblRecargoTotal(Label lblRecargoTotal) {
		this.lblRecargoTotal = lblRecargoTotal;
	}

	private Label lblInteresRefinanciacion = new Label();

	public Label getLblInteresRefinanciacion() {
		return lblInteresRefinanciacion;
	}

	public void setLblInteresRefinanciacion(Label lblInteresRefinanciacion) {
		this.lblInteresRefinanciacion = lblInteresRefinanciacion;
	}

	private Label lblInteresCondonado = new Label();

	public Label getLblInteresCondonado() {
		return lblInteresCondonado;
	}

	public void setLblInteresCondonado(Label lblInteresCondonado) {
		this.lblInteresCondonado = lblInteresCondonado;
	}

	private Label lblInteresTotal = new Label();

	public Label getLblInteresTotal() {
		return lblInteresTotal;
	}

	public void setLblInteresTotal(Label lblInteresTotal) {
		this.lblInteresTotal = lblInteresTotal;
	}

	private Label lblSubTotal = new Label();

	public Label getLblSubTotal() {
		return lblSubTotal;
	}

	public void setLblSubTotal(Label lblSubTotal) {
		this.lblSubTotal = lblSubTotal;
	}

	private Label lblImporteCondonado = new Label();

	public Label getLblImporteCondonado() {
		return lblImporteCondonado;
	}

	public void setLblImporteCondonado(Label lblImporteCondonado) {
		this.lblImporteCondonado = lblImporteCondonado;
	}

	private Label lblImporteTotal = new Label();

	public Label getLblImporteTotal() {
		return lblImporteTotal;
	}

	public void setLblImporteTotal(Label lblImporteTotal) {
		this.lblImporteTotal = lblImporteTotal;
	}

	private Label lblInteresDiario = new Label();

	public Label getLblInteresDiario() {
		return lblInteresDiario;
	}

	public void setLblInteresDiario(Label lblInteresDiario) {
		this.lblInteresDiario = lblInteresDiario;
	}

	private Label lblTasaNominal = new Label();

	public Label getLblTasaNominal() {
		return lblTasaNominal;
	}

	public void setLblTasaNominal(Label lblTasaNominal) {
		this.lblTasaNominal = lblTasaNominal;
	}

	private Label lblCantCuotas = new Label();

	public Label getLblCantCuotas() {
		return lblCantCuotas;
	}

	public void setLblCantCuotas(Label lblCantCuotas) {
		this.lblCantCuotas = lblCantCuotas;
	}

	private Label lblTitle1 = new Label();

	public Label getLblTitle1() {
		return lblTitle1;
	}

	public void setLblTitle1(Label lblTitle1) {
		this.lblTitle1 = lblTitle1;
	}

	private Label lblNombreRefinanciacion = new Label();

	public Label getLblNombreRefinanciacion() {
		return lblNombreRefinanciacion;
	}

	public void setLblNombreRefinanciacion(Label l) {
		this.lblNombreRefinanciacion = l;
	}

	private Label lblNroRefinanciacion = new Label();

	public Label getLblNroRefinanciacion() {
		return lblNroRefinanciacion;
	}

	public void setLblNroRefinanciacion(Label l) {
		this.lblNroRefinanciacion = l;
	}

	private Label lblFechaRefinanciacion = new Label();

	public Label getLblFechaRefinanciacion() {
		return lblFechaRefinanciacion;
	}

	public void setLblFechaRefinanciacion(Label l) {
		this.lblFechaRefinanciacion = l;
	}

	private Label lblContribuyente = new Label();

	public Label getLblContribuyente() {
		return lblContribuyente;
	}

	public void setLblContribuyente(Label l) {
		this.lblContribuyente = l;
	}

	private Label lblDigesto = new Label();

	public Label getLblDigesto() {
		return lblDigesto;
	}

	public void setLblDigesto(Label lblDigesto) {
		this.lblDigesto = lblDigesto;
	}

	private Label lblInmuebles = new Label();

	public Label getLblInmuebles() {
		return lblInmuebles;
	}

	public void setLblInmuebles(Label lblInmuebles) {
		this.lblInmuebles = lblInmuebles;
	}

	private Label lblComercios = new Label();

	public Label getLblComercios() {
		return lblComercios;
	}

	public void setLblComercios(Label lblComercios) {
		this.lblComercios = lblComercios;
	}

	// ----------------Buttons----------------
	private HtmlAjaxCommandButton btnImprimirReconocimientoDeuda = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnImprimirReconocimientoDeuda() {
		return btnImprimirReconocimientoDeuda;
	}

	public void setBtnImprimirReconocimientoDeuda(HtmlAjaxCommandButton btnImprimirReconocimientoDeuda) {
		this.btnImprimirReconocimientoDeuda = btnImprimirReconocimientoDeuda;
	}

	private Button btnImprimirCuotasGeneradas = new Button();

	public Button getBtnImprimirCuotasGeneradas() {
		return btnImprimirCuotasGeneradas;
	}

	public void setBtnImprimirCuotasGeneradas(Button btnImprimirCuotasGeneradas) {
		this.btnImprimirCuotasGeneradas = btnImprimirCuotasGeneradas;
	}

	private Button btnImprimirTodas = new Button();

	public Button getBtnImprimirTodas() {
		return btnImprimirTodas;
	}

	public void setBtnImprimirTodas(Button btnImprimirTodas) {
		this.btnImprimirTodas = btnImprimirTodas;
	}

	private Button btnVerPeriodos = new Button();

	public Button getBtnVerPeriodos() {
		return btnVerPeriodos;
	}

	public void setBtnVerPeriodos(Button b) {
		this.btnVerPeriodos = b;
	}

	// ----------------TableColumns----------------
	private TableColumn tableColumn16 = new TableColumn();
	private TableColumn tableColumn15 = new TableColumn();
	private TableColumn tableColumn14 = new TableColumn();
	private TableColumn tableColumn13 = new TableColumn();
	private TableColumn tableColumn12 = new TableColumn();
	private TableColumn tableColumn11 = new TableColumn();

	public TableColumn getTableColumn11() {
		return tableColumn11;
	}

	public void setTableColumn11(TableColumn tableColumn11) {
		this.tableColumn11 = tableColumn11;
	}

	public TableColumn getTableColumn12() {
		return tableColumn12;
	}

	public void setTableColumn12(TableColumn tableColumn12) {
		this.tableColumn12 = tableColumn12;
	}

	public TableColumn getTableColumn13() {
		return tableColumn13;
	}

	public void setTableColumn13(TableColumn tableColumn13) {
		this.tableColumn13 = tableColumn13;
	}

	public TableColumn getTableColumn14() {
		return tableColumn14;
	}

	public void setTableColumn14(TableColumn tableColumn14) {
		this.tableColumn14 = tableColumn14;
	}

	public TableColumn getTableColumn15() {
		return tableColumn15;
	}

	public void setTableColumn15(TableColumn tableColumn15) {
		this.tableColumn15 = tableColumn15;
	}

	public TableColumn getTableColumn16() {
		return tableColumn16;
	}

	public void setTableColumn16(TableColumn tableColumn16) {
		this.tableColumn16 = tableColumn16;
	}

	private TableColumn tableColumn10 = new TableColumn();

	public TableColumn getTableColumn10() {
		return tableColumn10;
	}

	public void setTableColumn10(TableColumn tableColumn10) {
		this.tableColumn10 = tableColumn10;
	}

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tableColumn9) {
		this.tableColumn9 = tableColumn9;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private TableColumn tableColumn7 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tc) {
		this.tableColumn7 = tc;
	}

	private TableColumn tableColumn8 = new TableColumn();

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tableColumn8) {
		this.tableColumn8 = tableColumn8;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private PanelGroup pgLibreDeuda = new PanelGroup();
	private Table tablaLibresDeuda = new Table();
	private TableRowGroup trgLibresDeuda = new TableRowGroup();
	private ObjectListDataProvider ldpLibresDeuda = new ObjectListDataProvider();
	private TableColumn tcNroLibreDeuda = new TableColumn();
	private TableColumn tcSolicitanteLibreDeuda = new TableColumn();
	private TableColumn tcMotivoLibreDeuda = new TableColumn();
	private TableColumn tcFechaGeneradaLibreDeuda = new TableColumn();
	private TableColumn tcFechaSolicitadaLibreDeuda = new TableColumn();
	private TableColumn tcObservacionesLibreDeuda = new TableColumn();
	private TableColumn tcUsuario = new TableColumn();
	private StaticText stNroLibreDeuda = new StaticText();
	private StaticText stSolicitanteLibreDeuda = new StaticText();
	private TextArea taMotivoLibreDeuda = new TextArea();
	private TextArea taObservacionesLibreDeuda = new TextArea();
	private StaticText stUsuario = new StaticText();
	private StaticText stFechaGeneradaLibreDeuda = new StaticText();
	private StaticText stFechaSolicitadaLibreDeuda = new StaticText();
	private DateTimeConverter dateTimeConverter2 = new DateTimeConverter();

	public PanelGroup getPgLibreDeuda() {
		return pgLibreDeuda;
	}

	public void setPgLibreDeuda(PanelGroup pgLibreDeuda) {
		this.pgLibreDeuda = pgLibreDeuda;
	}

	public Table getTablaLibresDeuda() {
		return tablaLibresDeuda;
	}

	public void setTablaLibresDeuda(Table tablaLibresDeuda) {
		this.tablaLibresDeuda = tablaLibresDeuda;
	}

	public TableRowGroup getTrgLibresDeuda() {
		return trgLibresDeuda;
	}

	public void setTrgLibresDeuda(TableRowGroup trgLibresDeuda) {
		this.trgLibresDeuda = trgLibresDeuda;
	}

	public ObjectListDataProvider getLdpLibresDeuda() {
		return ldpLibresDeuda;
	}

	public void setLdpLibresDeuda(ObjectListDataProvider ldpLibresDeuda) {
		this.ldpLibresDeuda = ldpLibresDeuda;
	}

	public TableColumn getTcNroLibreDeuda() {
		return tcNroLibreDeuda;
	}

	public void setTcNroLibreDeuda(TableColumn tcNroLibreDeuda) {
		this.tcNroLibreDeuda = tcNroLibreDeuda;
	}

	public TableColumn getTcSolicitanteLibreDeuda() {
		return tcSolicitanteLibreDeuda;
	}

	public void setTcSolicitanteLibreDeuda(TableColumn tcSolicitanteLibreDeuda) {
		this.tcSolicitanteLibreDeuda = tcSolicitanteLibreDeuda;
	}

	public TableColumn getTcMotivoLibreDeuda() {
		return tcMotivoLibreDeuda;
	}

	public void setTcMotivoLibreDeuda(TableColumn tcMotivoLibreDeuda) {
		this.tcMotivoLibreDeuda = tcMotivoLibreDeuda;
	}

	public TableColumn getTcFechaGeneradaLibreDeuda() {
		return tcFechaGeneradaLibreDeuda;
	}

	public void setTcFechaGeneradaLibreDeuda(TableColumn tcFechaGeneradaLibreDeuda) {
		this.tcFechaGeneradaLibreDeuda = tcFechaGeneradaLibreDeuda;
	}

	public TableColumn getTcFechaSolicitadaLibreDeuda() {
		return tcFechaSolicitadaLibreDeuda;
	}

	public void setTcFechaSolicitadaLibreDeuda(TableColumn tcFechaSolicitadaLibreDeuda) {
		this.tcFechaSolicitadaLibreDeuda = tcFechaSolicitadaLibreDeuda;
	}

	public TableColumn getTcObservacionesLibreDeuda() {
		return tcObservacionesLibreDeuda;
	}

	public void setTcObservacionesLibreDeuda(TableColumn tcObservacionesLibreDeuda) {
		this.tcObservacionesLibreDeuda = tcObservacionesLibreDeuda;
	}

	public TableColumn getTcUsuario() {
		return tcUsuario;
	}

	public void setTcUsuario(TableColumn tcUsuario) {
		this.tcUsuario = tcUsuario;
	}

	public StaticText getStNroLibreDeuda() {
		return stNroLibreDeuda;
	}

	public void setStNroLibreDeuda(StaticText stNroLibreDeuda) {
		this.stNroLibreDeuda = stNroLibreDeuda;
	}

	public StaticText getStSolicitanteLibreDeuda() {
		return stSolicitanteLibreDeuda;
	}

	public void setStSolicitanteLibreDeuda(StaticText stSolicitanteLibreDeuda) {
		this.stSolicitanteLibreDeuda = stSolicitanteLibreDeuda;
	}

	public TextArea getTaMotivoLibreDeuda() {
		return taMotivoLibreDeuda;
	}

	public void setTaMotivoLibreDeuda(TextArea taMotivoLibreDeuda) {
		this.taMotivoLibreDeuda = taMotivoLibreDeuda;
	}

	public TextArea getTaObservacionesLibreDeuda() {
		return taObservacionesLibreDeuda;
	}

	public void setTaObservacionesLibreDeuda(TextArea taObservacionesLibreDeuda) {
		this.taObservacionesLibreDeuda = taObservacionesLibreDeuda;
	}

	public StaticText getStUsuario() {
		return stUsuario;
	}

	public void setStUsuario(StaticText stUsuario) {
		this.stUsuario = stUsuario;
	}

	public StaticText getStFechaGeneradaLibreDeuda() {
		return stFechaGeneradaLibreDeuda;
	}

	public void setStFechaGeneradaLibreDeuda(StaticText stFechaGeneradaLibreDeuda) {
		this.stFechaGeneradaLibreDeuda = stFechaGeneradaLibreDeuda;
	}

	public StaticText getStFechaSolicitadaLibreDeuda() {
		return stFechaSolicitadaLibreDeuda;
	}

	public void setStFechaSolicitadaLibreDeuda(StaticText stFechaSolicitadaLibreDeuda) {
		this.stFechaSolicitadaLibreDeuda = stFechaSolicitadaLibreDeuda;
	}

	public DateTimeConverter getDateTimeConverter2() {
		return dateTimeConverter2;
	}

	public void setDateTimeConverter2(DateTimeConverter dateTimeConverter2) {
		this.dateTimeConverter2 = dateTimeConverter2;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMRefinanciacion() {
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

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1());
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new DocumentoRefinanciacion());
		ep.getObjetos().add(ind++, new ArrayList()); // lista de periodos adeudados (......)
		ep.getObjetos().add(ind++, null); // persona
		ep.getObjetos().add(ind++, new Date()); //3 - Fecha actualizacion.
		ep.getObjetos().add(ind++, new Boolean(true)); //4 - Aplicar intereses.

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

//		if(this.getCommunicationHabilitacionesBean().getListaLibresDeuda() != null) {
//			this.getCommunicationHabilitacionesBean().getListaLibresDeuda().clear();
//		}

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);
		ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, documentoRefinanciacion);
		this.getElementoPila().getObjetos().set(ind++, periodosAdeudados);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, getTextFieldValueDate(tfFechaActualizacionDeuda));
		this.getElementoPila().getObjetos().set(ind++, cbAplicarIntereses.isChecked());
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// Double interesRef = new Double(0.0);
		double montoTotal = 0.0;
		double totalCondonado = 0.0;
		double capitalAPagar = 0.0;
		double recargoRef = 0.0;
		double multaRef = 0.0;
		double interesRef = 0.0;
		double subtotal = 0.0;

		int ind = 0;
		ArrayList periodosAdeudados = null;
		DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);
		ArrayList listadoCuotas = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		RegCancelacionPorRefinanciacion registroCancelacion = documentoRefinanciacion.getRegCancelacionPorRefinanciacion();

		// Calculo de los montos refinanciados
		if(registroCancelacion != null) {
			// Calculo de los montos totales, total condonado y total a pagar
			if(registroCancelacion.getInteres() != null && registroCancelacion.getImporteTotal() != null && registroCancelacion.getMultasTotal() != null
					&& registroCancelacion.getRecargoTotal() != null) {

				montoTotal = registroCancelacion.getImporteTotal().doubleValue() + registroCancelacion.getInteres().doubleValue() + registroCancelacion.getRecargoTotal().doubleValue()
						+ registroCancelacion.getMultasTotal().doubleValue();

				this.getTfMontoTotal().setText(Conversor.getStringDeDouble(new Double(montoTotal)));
			}
			if(registroCancelacion.getImporteCondonado() != null && registroCancelacion.getInteresCondonado() != null && registroCancelacion.getMultaCondonada() != null
					&& registroCancelacion.getRecargoCondonado() != null) {

				totalCondonado = registroCancelacion.getImporteCondonado().doubleValue() + registroCancelacion.getInteresCondonado().doubleValue()
						+ registroCancelacion.getRecargoCondonado().doubleValue() + registroCancelacion.getMultaCondonada().doubleValue();

				this.getTfTotalCondonado().setText(Conversor.getStringDeDouble(new Double(totalCondonado)));
			}

			if(registroCancelacion.getImporteTotal() != null && registroCancelacion.getImporteCondonado() != null) {

				subtotal = registroCancelacion.getImporteTotal().doubleValue() - registroCancelacion.getImporteCondonado().doubleValue();

				this.getTfSubTotal().setText(Conversor.getStringDeDouble(new Double(subtotal)));
			}
			if(registroCancelacion.getInteres() != null && registroCancelacion.getInteresCondonado() != null) {

				interesRef = registroCancelacion.getInteres().doubleValue() - registroCancelacion.getInteresCondonado().doubleValue();

				this.getTfInteresRefinanciacion().setText(Conversor.getStringDeDouble(new Double(interesRef)));
			}
			if(registroCancelacion.getRecargoTotal() != null && registroCancelacion.getRecargoCondonado() != null) {

				recargoRef = registroCancelacion.getRecargoTotal().doubleValue() - registroCancelacion.getRecargoCondonado().doubleValue();

				this.getTfRecargoRefinanciacion().setText(Conversor.getStringDeDouble(new Double(recargoRef)));
			}
			if(registroCancelacion.getMultasTotal() != null && registroCancelacion.getMultaCondonada() != null) {

				if(registroCancelacion.getMultaCondonada().doubleValue() > 0) {
					multaRef = registroCancelacion.getMultasTotal().doubleValue() - registroCancelacion.getMultaCondonada().doubleValue();
				} else {
					multaRef = registroCancelacion.getMultasTotal().doubleValue() + registroCancelacion.getMultaCondonada().doubleValue();
				}

				this.getTfMultaRefinanciacion().setText(Conversor.getStringDeDouble(new Double(multaRef)));
			}
		}

		capitalAPagar = subtotal + interesRef + recargoRef + multaRef;
		this.getTfCapitalRefinanciado().setText(Conversor.getStringDeDouble(new Double(capitalAPagar)));

		if(documentoRefinanciacion.getStringNombreRefinanciacion() != null) {
			this.getTfNombreRefinanciacion().setText(documentoRefinanciacion.getStringNombreRefinanciacion());
		}

		try {
			this.getTfEstado().setText(Conversor.getStringDeObject(documentoRefinanciacion.getEstadoRefinanciacion()));
		} catch(TrascenderException ex) {
			ex.printStackTrace();
		}
		if(documentoRefinanciacion.getStringPersona() != null) {
			this.getTfContribuyente().setText(documentoRefinanciacion.getStringPersona());
		}
		if(documentoRefinanciacion.getNumeroRefinanciacion() != null) {
			this.getTfNroRefinanciacion().setText(documentoRefinanciacion.getNumeroRefinanciacion().toString());
		}
		if(documentoRefinanciacion.getAnioInicioRefinanciacion() != null) {
			this.getTfFechaRefinanciacion().setText(documentoRefinanciacion.getMesInicioRefinanciacion() + "/" + documentoRefinanciacion.getAnioInicioRefinanciacion().toString());
		}
		if(documentoRefinanciacion.getNombre() != null) {
			this.getStNombreRefinanciacion().setText(documentoRefinanciacion.getNombre().toString());
		}
		if(registroCancelacion.getDigestoMunicipal() != null) {
			this.getTfDigesto().setText(registroCancelacion.getDigestoMunicipal().toString());
		}
		if(documentoRefinanciacion.getStringInmuebles() != null) {
			this.getTaInmuebles().setText(documentoRefinanciacion.getStringInmuebles());
		}
		if(documentoRefinanciacion.getStringComercios() != null) {
			this.getTaComercios().setText(documentoRefinanciacion.getStringComercios());
		}

		// FALTA: recargoRefinanciacion
		if(documentoRefinanciacion.getCantidadCuotas() != null) {
			this.getTfCantCuotas().setText(documentoRefinanciacion.getCantidadCuotas().toString());
		}
		if(documentoRefinanciacion.getTasaNominalAnual() != null) {
			this.getTfTasaNominal().setText(documentoRefinanciacion.getTasaNominalAnual().toString());
		}
		if(documentoRefinanciacion.getInteresDiario() != null) {
			this.getTfInteresDiario().setText(documentoRefinanciacion.getInteresDiario().toString());
		}
		if(registroCancelacion.getImporteTotal() != null) {
			this.getTfImporteTotal().setText(registroCancelacion.getImporteTotal().toString());
		}
		if(registroCancelacion.getImporteCondonado() != null) {
			this.getTfImporteCondonado().setText(registroCancelacion.getImporteCondonado().toString());
		}
		if(registroCancelacion.getInteres() != null) {
			this.getTfInteresTotal().setText(registroCancelacion.getInteres().toString());
		}
		if(registroCancelacion.getInteresCondonado() != null) {
			this.getTfInteresCondonado().setText(registroCancelacion.getInteresCondonado().toString());
		}
		if(registroCancelacion.getRecargoTotal() != null) {
			this.getTfRecargoTotal().setText(registroCancelacion.getRecargoTotal().toString());
		}
		if(registroCancelacion.getRecargoCondonado() != null) {
			this.getTfRecargoCondonado().setText(registroCancelacion.getRecargoCondonado().toString());
		}
		if(registroCancelacion.getMultasTotal() != null) {
			this.getTfMultaTotal().setText(registroCancelacion.getMultasTotal().toString());
		}
		if(registroCancelacion.getMultaCondonada() != null) {
			this.getTfMultaCondonado().setText(registroCancelacion.getMultaCondonada().toString());
		}

		if(documentoRefinanciacion.getListaRegistrosDeuda().size() > 0) {
			listadoCuotas = new ArrayList(documentoRefinanciacion.getListaRegistrosDeuda());
			ordenarListaCuotas(listadoCuotas);

			Double locValorCuota = 0.0;
			Double locInteres = 0.0;
			Double locRecargo = 0.0;
			Double locMulta = 0.0;
			for(Object obj : listadoCuotas) {
				CuotaRefinanciacion cuota = (CuotaRefinanciacion) obj;
				if(cuota.getEstado() != RegistroDeuda.EstadoRegistroDeuda.PAGADA) {
					locValorCuota += cuota.getValorCuota();
					// locInteres += cuota.getInteres();
					locRecargo += cuota.getRecargo();
					locMulta += cuota.getMultas();
				}
			}

			this.getTfDeudaAlDia().setText(documentoRefinanciacion.getTotalAPagar());
		}
		if(registroCancelacion.getListaRegistrosDeuda().size() > 0) {
			periodosAdeudados = new ArrayList(registroCancelacion.getListaRegistrosDeuda());
		}

		// Table1
		this.setListaDelCommunication(listadoCuotas);
		this.getObjectListDataProviderTabla1().setList(listadoCuotas);
		// Table2
		ordenarPeriodos(periodosAdeudados);
		this.setListaDelCommunication2(periodosAdeudados);
		this.getObjectListDataProviderTabla2().setList(periodosAdeudados);
		
		Date fechaActualizacion = (Date) obtenerObjetoDelElementoPila(3);
		boolean aplicarIntereses = (Boolean) obtenerObjetoDelElementoPila(4);
		
		this.setTextFieldValueDate(tfFechaActualizacionDeuda, fechaActualizacion);
		this.getCbAplicarIntereses().setValue(aplicarIntereses);
	}

	@SuppressWarnings("unchecked")
	private void ordenarListaCuotas(List pListadoCuotas) {
		Collections.sort(pListadoCuotas, new Comparator<CuotaRefinanciacion>() {

			public int compare(CuotaRefinanciacion c1, CuotaRefinanciacion c2) {
				return c1.getNumeroCuota().compareTo(c2.getNumeroCuota());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void ordenarPeriodos(List pListadoPeriodos) {
		// provisorio..
		if(pListadoPeriodos != null) {
			Collections.sort(pListadoPeriodos, new Comparator<LiquidacionTasa>() {

				public int compare(LiquidacionTasa l1, LiquidacionTasa l2) {
					return l1.getNombre().compareTo(l2.getNombre());
				}
			});
		}
	}

	private ArrayList getListaDelCommunication() {
		return this.getCommunicationExcepcionesBean().getListaCuotasRefinanciacion();
	}

	private void setListaDelCommunication(ArrayList lista) {
		this.getCommunicationExcepcionesBean().setListaCuotasRefinanciacion(lista);
	}

	private ArrayList getListaDelCommunication2() {
		return this.getCommunicationExcepcionesBean().getListaPeriodosAdeudadosRefinanciacion();
	}

	private void setListaDelCommunication2(ArrayList lista) {
		this.getCommunicationExcepcionesBean().setListaPeriodosAdeudadosRefinanciacion(lista);
	}

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		return this.getCommunicationExcepcionesBean().getTablePhaseListenerPeriodosAdeudadosRefinanciacion();
	}

	private ObjectListDataProvider getObjectListDataProviderTabla1() {
		return this.getLdpCuotasGeneradas();
	}

	private ObjectListDataProvider getObjectListDataProviderTabla2() {
		return this.getLdpPeriodosAdeudados();
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
				if(!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}

		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];

		return rk;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProviderTabla1().getRowKey(aRowId);
		} catch(Exception ex) {
		}

		return rk;
	}

	// TABLA 1
	private TableSelectPhaseListener tablePhaseListener = new TableSelectPhaseListener();

	public void setSelected(Object object) {
		RowKey rowKey = tableRowGroup1.getRowKey();
		if(rowKey != null) {
			tablePhaseListener.setSelected(rowKey, object);
		}
	}

	public Object getSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListener.getSelected(rowKey);
	}

	public Object getSelectedValue() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public boolean isCurrentRowSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListener.isSelected(rowKey);
	}

	// TABLA 2
	private TableSelectPhaseListener tablePhaseListenerTabla1 = new TableSelectPhaseListener();

	public void setSelectedTabla1(Object object) {
		RowKey rowKey = tableRowGroup1.getRowKey();
		if(rowKey != null) {
			tablePhaseListenerTabla1.setSelected(rowKey, object);
		}
	}

	public Object getSelectedTabla1() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListenerTabla1.getSelected(rowKey);
	}

	public Object getSelectedValueTabla1() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public void ddTipoObligacion_processValueChange(ValueChangeEvent vce) {
		this.getElementoPila().getObjetos().set(1, vce.getNewValue());

		return;
	}

	public void btnActualizarDeuda_action() {
		guardarEstadoObjetosUsados();
		DocumentoRefinanciacion locRefinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(0, DocumentoRefinanciacion.class);
		Date fechaActualizar = (Date) obtenerObjetoDelElementoPila(3);
		Boolean intereses = (Boolean) obtenerObjetoDelElementoPila(4);
		
		for (CuotaRefinanciacion cadaCuota : getCuotasSeleccionadas()) {
			cadaCuota.calcularRecargo(intereses, fechaActualizar);
		}
		try {
			this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
			this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().updateRefinanciacion(locRefinanciacion);

			this.getElementoPila().getObjetos().set(0, locRefinanciacion);
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(TrascenderException e) {
			e.printStackTrace();
		}

//		this.setListaDelCommunication(new ArrayList(locRefinanciacion.getListaRegistrosDeuda()));
//		this.getObjectListDataProviderTabla1().setList(this.getListaDelCommunication());
	}

	public String btnVerPeriodos_action() {
		// TODO: Buscar las deudas de las obligaciones seleccionadas.
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.guardarEstadoObjetosUsados();

				List obligacionesSeleccionadas = new ArrayList();

				RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();

				if(selectedRowKeys.length > 0) {
					for(int i = 0; i < selectedRowKeys.length; i++) {
						String rowId = selectedRowKeys[i].getRowId();
						RowKey rowKey = this.getObjectListDataProviderTabla1().getRowKey(rowId);
						Object obj = this.getObjectListDataProviderTabla1().getObjects()[Integer.valueOf(rowKey.getRowId()).intValue()];
						obligacionesSeleccionadas.add(obj);
					}

					this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				} else {
					retorno = null;
				}
			} catch(Exception ex) {
				if(ex instanceof TrascenderException) {
					ex.printStackTrace();
					int codigoError = ((TrascenderException) ex).getCodeTrascenderException();
					if(this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) {
						retorno = null;
					} else {
						retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
					}
				}
				log(CASO_NAVEGACION, ex);
				error(NOMBRE_PAGINA + " - " + this.btnVerPeriodos.getText() + ":" + ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	private List<CuotaRefinanciacion> getCuotasSeleccionadas() {
		List<CuotaRefinanciacion> cuotasSeleccionadas = new ArrayList<CuotaRefinanciacion>();
		RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();

		if(selectedRowKeys.length > 0) {
			boolean noVigente = false;
			for(int i = 0; i < selectedRowKeys.length; i++) {
				String rowId = selectedRowKeys[i].getRowId();
				RowKey rowKey = this.getObjectListDataProviderTabla1().getRowKey(rowId);
				Object obj = this.getObjectListDataProviderTabla1().getObjects()[Integer.valueOf(rowKey.getRowId()).intValue()];

				CuotaRefinanciacion c = (CuotaRefinanciacion) obj;

				cuotasSeleccionadas.add(c);
			}
		}
		return cuotasSeleccionadas;
	}

	public String btnImprimirCuotasGeneradas_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			try {
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				List<CuotaRefinanciacion> cuotasSeleccionadas = getCuotasSeleccionadas();
				cuotasSeleccionadas = quitarCuotasPagadasSegunPermiso(cuotasSeleccionadas);

				if(!cuotasSeleccionadas.isEmpty()) {
					 ordenarListaCuotas(cuotasSeleccionadas);

					this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
					JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteListadoCuotasRefinanciacion(cuotasSeleccionadas);

					subirReporteASesion("Reporte_CuotasRefinanciacion", ConstantesReportes.PDF, jp);
				} else {
					retorno = null;
					warn("Solo se pueden imprimir cuotas VIGENTES.");
					subirErrorEnReporteASesion();
				}
			} catch (Exception e) {
				error(e.getMessage());
				subirErrorEnReporteASesion();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	private List<CuotaRefinanciacion> quitarCuotasPagadas(List<CuotaRefinanciacion> pListaCuotas) {
		List<CuotaRefinanciacion> locListaResultado = new ArrayList<CuotaRefinanciacion>();
		for(Iterator<CuotaRefinanciacion> it = pListaCuotas.iterator(); it.hasNext();) {
			CuotaRefinanciacion cuotaRefinanciacion = it.next();
			if(cuotaRefinanciacion.getEstado() == EstadoRegistroDeuda.VIGENTE) {
				locListaResultado.add(cuotaRefinanciacion);
			}
		}
		return locListaResultado;
	}

	private List<CuotaRefinanciacion> quitarCuotasPagadasSegunPermiso(
			List<CuotaRefinanciacion> pListaCuotas) throws Exception{
		boolean permiso = SecurityMgr.getInstance().getPermiso(
						this.getSessionBean1().getLlave(),
						DocumentoRefinanciacion.serialVersionUID, 
						Permiso.Accion.AUDITH);
		if(!permiso) {
			return quitarCuotasPagadas(pListaCuotas);
		}
		return pListaCuotas;
	}

	public String btnImprimirTodas_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			try {
				List<CuotaRefinanciacion> cuotasSeleccionadas = this.getListaDelCommunication();
				cuotasSeleccionadas = quitarCuotasPagadasSegunPermiso(cuotasSeleccionadas);

				if(!cuotasSeleccionadas.isEmpty()) {
					ordenarListaCuotas(cuotasSeleccionadas);

					this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
					JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteListadoCuotasRefinanciacion(cuotasSeleccionadas);

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_CuotasRefinanciacion");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
				} else {
					retorno = null;
					warn("Solo se pueden imprimir cuotas VIGENTES.");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				}
			} catch(Exception e) {
				log(CASO_NAVEGACION + "_ReporteDinamicoError: ", e);
				error(NOMBRE_PAGINA + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) pObject;

//		List<LibreDeuda> listaLibresDeuda = new ArrayList<LibreDeuda>();
//		listaLibresDeuda.addAll(documentoRefinanciacion.getObligacion().getListaLibresDeuda());

		// Mostramos la lista de forma ordenada por nro y descendente...
//		Collections.sort(listaLibresDeuda);
//		Collections.reverse(listaLibresDeuda);

//		this.getCommunicationHabilitacionesBean().setListaLibresDeuda(listaLibresDeuda);
//		this.getLdpLibresDeuda().setList(this.getCommunicationHabilitacionesBean().getListaLibresDeuda());

		this.getElementoPila().getObjetos().set(0, documentoRefinanciacion);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMRefinanciacion";
	}

	@Override
	public String getNombreBean() {
		return "#{excepciones$ABMRefinanciacion$ABMRefinanciacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoRefinanciacion.serialVersionUID;
	}

}