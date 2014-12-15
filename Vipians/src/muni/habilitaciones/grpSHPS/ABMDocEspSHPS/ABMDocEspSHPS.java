/**
 * ModificarDocEspSHPS.java
 *
 * Created on 3 de noviembre de 2006, 15:17 Copyright Trascender SRL
 */

package muni.habilitaciones.grpSHPS.ABMDocEspSHPS;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.habilitaciones.recurso.persistent.LogModificacionDocEsp;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.AsocRubro;
import com.trascender.habilitaciones.recurso.persistent.shps.ClausuraSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.Tasa;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMDocEspSHPS extends ABMPageBean {

	private static int POSICION_CONTADOR;

	static {
		POSICION_CONTADOR = 11;
	}

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if(this.getListaDelCommunicationTabla2() != null) {
			this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());
		}
		if(this.getListaDelCommunicationTabla3() != null) {
			this.getObjectListDataProviderTabla3().setList(this.getListaDelCommunicationTabla3());
		}
		if(this.getListaDelCommunicationTabla4() != null) {
			this.getObjectListDataProviderTabla4().setList(this.getListaDelCommunicationTabla4());
		}
		if(this.getListaDelCommunicationTabla5() != null) {
			this.getObjectListDataProviderTabla5().setList(this.getListaDelCommunicationTabla5());
		}
		if(this.getListaDelCommunicationTabla6() != null) {
			this.getObjectListDataProviderTabla6().setList(this.getListaDelCommunicationTabla6());
		}

		dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		dateTimeConverter1.setPattern("dd/MM/yyyy");
		dateTimeConverter1.setTimeStyle("full");

		if(this.getCommunicationSAICBean().getListaLogLiquidacion() != null) {
			this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
		}

		Option[] opTipoEvento = null;
		opTipoEvento = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(LogLiquidacion.Evento.values(), "cap");
		ddFiltrarEventoLogLiqOptions.setOptions(opTipoEvento);

		dateTimeConverter2.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		dateTimeConverter2.setPattern("dd/MM/yyyy HH:mm");
		dateTimeConverter2.setTimeStyle("full");
		numberConverter2.setPattern("$ #,##0.00");
	}

	private Label label17 = new Label();
	private TextField tfContador = new TextField();
	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	private Table tablaDocsGeneradoresDeuda = new Table();
	private ObjectListDataProvider ldpDocsGeneradoresDeuda = new ObjectListDataProvider();
	private StaticText stDocsGeneradoresDeuda = new StaticText();
	private TableRowGroup trgDocsGeneradoresDeuda = new TableRowGroup();
	private TableColumn tcAnio = new TableColumn();
	private TableColumn tcPlan = new TableColumn();
	private StaticText stAnio = new StaticText();
	private StaticText stPlan = new StaticText();

	private Table tablaLogsLiquidacion = new Table();
	private TableRowGroup tableRowGroup = new TableRowGroup();
	private ObjectListDataProvider ldpLogsLiquidacion = new ObjectListDataProvider();

	private TableColumn tableColumn30 = new TableColumn();
	private TableColumn tableColumn31 = new TableColumn();
	private TableColumn tableColumn32 = new TableColumn();
	private TableColumn tableColumn33 = new TableColumn();
	private TableColumn tableColumn34 = new TableColumn();
	private TableColumn tableColumn35 = new TableColumn();

	private StaticText staticText30 = new StaticText();
	private StaticText staticText31 = new StaticText();
	private StaticText staticText32 = new StaticText();
	private StaticText staticText33 = new StaticText();
	private StaticText staticText34 = new StaticText();
	private StaticText staticText35 = new StaticText();
	
	private DateTimeConverter dateTimeConverter2 = new DateTimeConverter();
	private NumberConverter numberConverter2 = new NumberConverter();

	private PanelGroup gpFiltroTablaLogsLiquidacion = new PanelGroup();

	private TextField tfFiltrarUsuarioLogLiq = new TextField();
	private DropDown ddFiltrarEventoLogLiq = new DropDown();
	private SingleSelectOptionsList ddFiltrarEventoLogLiqOptions = new SingleSelectOptionsList();
	private TextField tfFiltrarFechaDesdeLogLiq = new TextField();
	private TextField tfFiltrarFechaHastaLogLiq = new TextField();

	private HtmlAjaxCommandButton btnFiltrarLogLiq = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarFiltradoLogLiq = new HtmlAjaxCommandButton();

	public Table getTablaLogsLiquidacion() {
		return tablaLogsLiquidacion;
	}

	public void setTablaLogsLiquidacion(Table tablaLogsLiquidacion) {
		this.tablaLogsLiquidacion = tablaLogsLiquidacion;
	}

	public TableRowGroup getTableRowGroup() {
		return tableRowGroup;
	}

	public void setTableRowGroup(TableRowGroup tableRowGroup) {
		this.tableRowGroup = tableRowGroup;
	}

	public ObjectListDataProvider getLdpLogsLiquidacion() {
		return ldpLogsLiquidacion;
	}

	public void setLdpLogsLiquidacion(ObjectListDataProvider ldpLogsLiquidacion) {
		this.ldpLogsLiquidacion = ldpLogsLiquidacion;
	}

	public TableColumn getTableColumn30() {
		return tableColumn30;
	}

	public void setTableColumn30(TableColumn tableColumn30) {
		this.tableColumn30 = tableColumn30;
	}

	public TableColumn getTableColumn31() {
		return tableColumn31;
	}

	public void setTableColumn31(TableColumn tableColumn31) {
		this.tableColumn31 = tableColumn31;
	}

	public TableColumn getTableColumn32() {
		return tableColumn32;
	}

	public void setTableColumn32(TableColumn tableColumn32) {
		this.tableColumn32 = tableColumn32;
	}

	public TableColumn getTableColumn33() {
		return tableColumn33;
	}

	public void setTableColumn33(TableColumn tableColumn33) {
		this.tableColumn33 = tableColumn33;
	}

	public TableColumn getTableColumn34() {
		return tableColumn34;
	}

	public void setTableColumn34(TableColumn tableColumn34) {
		this.tableColumn34 = tableColumn34;
	}
	
	public TableColumn getTableColumn35() {
		return tableColumn35;
	}

	public void setTableColumn35(TableColumn tableColumn35) {
		this.tableColumn35 = tableColumn35;
	}

	public StaticText getStaticText30() {
		return staticText30;
	}

	public void setStaticText30(StaticText staticText30) {
		this.staticText30 = staticText30;
	}

	public StaticText getStaticText31() {
		return staticText31;
	}

	public void setStaticText31(StaticText staticText31) {
		this.staticText31 = staticText31;
	}

	public StaticText getStaticText32() {
		return staticText32;
	}

	public void setStaticText32(StaticText staticText32) {
		this.staticText32 = staticText32;
	}

	public StaticText getStaticText33() {
		return staticText33;
	}

	public void setStaticText33(StaticText staticText33) {
		this.staticText33 = staticText33;
	}

	public StaticText getStaticText34() {
		return staticText34;
	}

	public void setStaticText34(StaticText staticText34) {
		this.staticText34 = staticText34;
	}
	
	public StaticText getStaticText35() {
		return staticText35;
	}

	public void setStaticText35(StaticText staticText35) {
		this.staticText35 = staticText35;
	}

	public DateTimeConverter getDateTimeConverter2() {
		return dateTimeConverter2;
	}

	public void setDateTimeConverter2(DateTimeConverter dateTimeConverter2) {
		this.dateTimeConverter2 = dateTimeConverter2;
	}

	public NumberConverter getNumberConverter2() {
		return numberConverter2;
	}

	public void setNumberConverter2(NumberConverter numberConverter2) {
		this.numberConverter2 = numberConverter2;
	}

	public PanelGroup getGpFiltroTablaLogsLiquidacion() {
		return gpFiltroTablaLogsLiquidacion;
	}

	public void setGpFiltroTablaLogsLiquidacion(PanelGroup gpFiltroTablaLogsLiquidacion) {
		this.gpFiltroTablaLogsLiquidacion = gpFiltroTablaLogsLiquidacion;
	}

	public TextField getTfFiltrarUsuarioLogLiq() {
		return tfFiltrarUsuarioLogLiq;
	}

	public void setTfFiltrarUsuarioLogLiq(TextField tfFiltrarUsuarioLogLiq) {
		this.tfFiltrarUsuarioLogLiq = tfFiltrarUsuarioLogLiq;
	}

	public DropDown getDdFiltrarEventoLogLiq() {
		return ddFiltrarEventoLogLiq;
	}

	public void setDdFiltrarEventoLogLiq(DropDown ddFiltrarEventoLogLiq) {
		this.ddFiltrarEventoLogLiq = ddFiltrarEventoLogLiq;
	}

	public SingleSelectOptionsList getDdFiltrarEventoLogLiqOptions() {
		return ddFiltrarEventoLogLiqOptions;
	}

	public void setDdFiltrarEventoLogLiqOptions(SingleSelectOptionsList ddFiltrarEventoLogLiqOptions) {
		this.ddFiltrarEventoLogLiqOptions = ddFiltrarEventoLogLiqOptions;
	}

	public TextField getTfFiltrarFechaDesdeLogLiq() {
		return tfFiltrarFechaDesdeLogLiq;
	}

	public void setTfFiltrarFechaDesdeLogLiq(TextField tfFiltrarFechaDesdeLogLiq) {
		this.tfFiltrarFechaDesdeLogLiq = tfFiltrarFechaDesdeLogLiq;
	}

	public TextField getTfFiltrarFechaHastaLogLiq() {
		return tfFiltrarFechaHastaLogLiq;
	}

	public void setTfFiltrarFechaHastaLogLiq(TextField tfFiltrarFechaHastaLogLiq) {
		this.tfFiltrarFechaHastaLogLiq = tfFiltrarFechaHastaLogLiq;
	}

	public HtmlAjaxCommandButton getBtnFiltrarLogLiq() {
		return btnFiltrarLogLiq;
	}

	public void setBtnFiltrarLogLiq(HtmlAjaxCommandButton btnFiltrarLogLiq) {
		this.btnFiltrarLogLiq = btnFiltrarLogLiq;
	}

	public HtmlAjaxCommandButton getBtnLimpiarFiltradoLogLiq() {
		return btnLimpiarFiltradoLogLiq;
	}

	public void setBtnLimpiarFiltradoLogLiq(HtmlAjaxCommandButton btnLimpiarFiltradoLogLiq) {
		this.btnLimpiarFiltradoLogLiq = btnLimpiarFiltradoLogLiq;
	}

	public Table getTablaDocsGeneradoresDeuda() {
		return tablaDocsGeneradoresDeuda;
	}

	public void setTablaDocsGeneradoresDeuda(Table tablaDocsGeneradoresDeuda) {
		this.tablaDocsGeneradoresDeuda = tablaDocsGeneradoresDeuda;
	}

	public ObjectListDataProvider getLdpDocsGeneradoresDeuda() {
		return ldpDocsGeneradoresDeuda;
	}

	public void setLdpDocsGeneradoresDeuda(ObjectListDataProvider ldpDocsGeneradoresDeuda) {
		this.ldpDocsGeneradoresDeuda = ldpDocsGeneradoresDeuda;
	}

	public StaticText getStDocsGeneradoresDeuda() {
		return stDocsGeneradoresDeuda;
	}

	public void setStDocsGeneradoresDeuda(StaticText stDocsGeneradoresDeuda) {
		this.stDocsGeneradoresDeuda = stDocsGeneradoresDeuda;
	}

	public TableRowGroup getTrgDocsGeneradoresDeuda() {
		return trgDocsGeneradoresDeuda;
	}

	public void setTrgDocsGeneradoresDeuda(TableRowGroup trgDocsGeneradoresDeuda) {
		this.trgDocsGeneradoresDeuda = trgDocsGeneradoresDeuda;
	}

	public TableColumn getTcAnio() {
		return tcAnio;
	}

	public void setTcAnio(TableColumn tcAnio) {
		this.tcAnio = tcAnio;
	}

	public TableColumn getTcPlan() {
		return tcPlan;
	}

	public void setTcPlan(TableColumn tcPlan) {
		this.tcPlan = tcPlan;
	}

	public StaticText getStAnio() {
		return stAnio;
	}

	public void setStAnio(StaticText stAnio) {
		this.stAnio = stAnio;
	}

	public StaticText getStPlan() {
		return stPlan;
	}

	public void setStPlan(StaticText stPlan) {
		this.stPlan = stPlan;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public Label getLabel17() {
		return label17;
	}

	public void setLabel17(Label label17) {
		this.label17 = label17;
	}

	public TextField getTfContador() {
		return tfContador;
	}

	public void setTfContador(TextField tfContador) {
		this.tfContador = tfContador;
	}

	private Label label15 = new Label();

	public Label getLabel15() {
		return label15;
	}

	public void setLabel15(Label l) {
		this.label15 = l;
	}

	private Label label16 = new Label();

	public Label getLabel16() {
		return label16;
	}

	public void setLabel16(Label l) {
		this.label16 = l;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		System.out.println("VALUE " + tfPersona.getValue());
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private TextField tfNumeroInscripcion = new TextField();

	public TextField getTfNumeroInscripcion() {
		return tfNumeroInscripcion;
	}

	public void setTfNumeroInscripcion(TextField tf) {
		this.tfNumeroInscripcion = tf;
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

	private ObjectListDataProvider ldpLocalComercialSHPS = new ObjectListDataProvider();
	private ObjectListDataProvider ldpLibretasSanitarias = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLibretasSanitarias() {
		return ldpLibretasSanitarias;
	}

	public void setLdpLibretasSanitarias(ObjectListDataProvider ldpLibretasSanitarias) {
		this.ldpLibretasSanitarias = ldpLibretasSanitarias;
	}

	public ObjectListDataProvider getLdpLocalComercialSHPS() {
		return ldpLocalComercialSHPS;
	}

	public void setLdpLocalComercialSHPS(ObjectListDataProvider oldp) {
		this.ldpLocalComercialSHPS = oldp;
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

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private ObjectListDataProvider ldpTransporteVehicularSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpTransporteVehicularSHPS() {
		return ldpTransporteVehicularSHPS;
	}

	public void setLdpTransporteVehicularSHPS(ObjectListDataProvider oldp) {
		this.ldpTransporteVehicularSHPS = oldp;
	}

	private ObjectListDataProvider ldpLogModificacionesSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpLogModificacionesSHPS() {
		return ldpLogModificacionesSHPS;
	}

	public void setLdpLogModificacionesSHPS(ObjectListDataProvider oldp) {
		this.ldpLogModificacionesSHPS = oldp;
	}

	private Label label27 = new Label();

	public Label getLabel27() {
		return label27;
	}

	public void setLabel27(Label l) {
		this.label27 = l;
	}

	private Label label28 = new Label();
	private Label label29 = new Label();
	private Label label30 = new Label();

	public Label getLabel30() {
		return label30;
	}

	public void setLabel30(Label label30) {
		this.label30 = label30;
	}

	public Label getLabel29() {
		return label29;
	}

	public void setLabel29(Label label29) {
		this.label29 = label29;
	}

	public Label getLabel28() {
		return label28;
	}

	public void setLabel28(Label l) {
		this.label28 = l;
	}

	private Table table2 = new Table();

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table t) {
		this.table2 = t;
	}

	private TableRowGroup tableRowGroup2 = new TableRowGroup();

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup trg) {
		this.tableRowGroup2 = trg;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Button btnAgregarTransporteVehicular = new Button();

	public Button getBtnAgregarTransporteVehicular() {
		return btnAgregarTransporteVehicular;
	}

	public void setBtnAgregarTransporteVehicular(Button b) {
		this.btnAgregarTransporteVehicular = b;
	}

	private HtmlAjaxCommandButton btnQuitarTransporteVehicular = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarTransporteVehicular() {
		return btnQuitarTransporteVehicular;
	}

	public void setBtnQuitarTransporteVehicular(HtmlAjaxCommandButton b) {
		this.btnQuitarTransporteVehicular = b;
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

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private Table table3 = new Table();

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table t) {
		this.table3 = t;
	}

	private TableRowGroup tableRowGroup3 = new TableRowGroup();

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup trg) {
		this.tableRowGroup3 = trg;
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

	public void setTableColumn8(TableColumn tc) {
		this.tableColumn8 = tc;
	}

	private StaticText staticText8 = new StaticText();
	private StaticText staticText9 = new StaticText();
	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private TableColumn tableColumn11 = new TableColumn();

	public TableColumn getTableColumn11() {
		return tableColumn11;
	}

	public void setTableColumn11(TableColumn tc) {
		this.tableColumn11 = tc;
	}

	private RadioButton radioButton2 = new RadioButton();

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton rb) {
		this.radioButton2 = rb;
	}

	private RadioButton radioButton3 = new RadioButton();

	public RadioButton getRadioButton3() {
		return radioButton3;
	}

	public void setRadioButton3(RadioButton rb) {
		this.radioButton3 = rb;
	}

	private RadioButton radioButton7 = new RadioButton();

	public RadioButton getRadioButton7() {
		return radioButton7;
	}

	public void setRadioButton7(RadioButton rb) {
		this.radioButton7 = rb;
	}

	private PanelGroup groupPanel3 = new PanelGroup();
	private PanelGroup groupPanel4 = new PanelGroup();

	public PanelGroup getGroupPanel4() {
		return groupPanel4;
	}

	public void setGroupPanel4(PanelGroup groupPanel4) {
		this.groupPanel4 = groupPanel4;
	}

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup pg) {
		this.groupPanel3 = pg;
	}

	private Object lastPrincipalSelected = null;

	public Object getRBPrincipalSelected() {
		String sv = (String) radioButton7.getSelectedValue();
		return sv.equals(lastPrincipalSelected) ? sv : null;
	}

	public void setRBPrincipalSelected(Object selected) {
		if(selected != null) {
			lastPrincipalSelected = selected;
		}
	}

	public void seleccionarFilaPrincipal(Object principal) {
		int cantFilas = this.getObjectListDataProviderTabla6().getList().size();
		AsocRubro enTabla;
		for(int i = 0; i < cantFilas; i++) {
			enTabla = (AsocRubro) this.getObjectListDataProviderTabla6().getObjects()[i];
			if(enTabla.getRegistroAlicuota().getIdTipoAlicuota() == ((AsocRubro) principal).getRegistroAlicuota().getIdTipoAlicuota()) {
				lastPrincipalSelected = new Long(i).toString();
			}
		}
	}

	public String getPrincipalRow() {
		return tableRowGroup6.getRowKey().getRowId();
	}

	public void setPrincipalRow(int row) {
	}

	private Button btnSeleccionarDomicilioPostal = new Button();

	public Button getBtnSeleccionarDomicilioPostal() {
		return btnSeleccionarDomicilioPostal;
	}

	public void setBtnSeleccionarDomicilioPostal(Button b) {
		this.btnSeleccionarDomicilioPostal = b;
	}

	private HtmlAjaxCommandButton btnLimpiarDomicilioPostal = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarDomicilioPostal() {
		return btnLimpiarDomicilioPostal;
	}

	public void setBtnLimpiarDomicilioPostal(HtmlAjaxCommandButton btnLimpiarDomicilioPostal) {
		this.btnLimpiarDomicilioPostal = btnLimpiarDomicilioPostal;
	}

	private Button btnAgregarLocalComercial = new Button();
	private Button btnAgregarLibretaSanitaria = new Button();
	private Button btnSeleccionarContador = new Button();

	public Button getBtnSeleccionarContador() {
		return btnSeleccionarContador;
	}

	public void setBtnSeleccionarContador(Button btnSeleccionarContador) {
		this.btnSeleccionarContador = btnSeleccionarContador;
	}

	public Button getBtnAgregarLocalComercial() {
		return btnAgregarLocalComercial;
	}

	public void setBtnAgregarLibretaSanitaria(Button btnAgregarLibretaSanitaria) {
		this.btnAgregarLibretaSanitaria = btnAgregarLibretaSanitaria;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfFechaInicio = new TextField();

	public TextField getTfFechaInicio() {
		return tfFechaInicio;
	}

	public void setTfFechaInicio(TextField tf) {
		this.tfFechaInicio = tf;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfFechaCese = new TextField();

	public TextField getTfFechaCese() {
		return tfFechaCese;
	}

	public void setTfFechaCese(TextField tf) {
		this.tfFechaCese = tf;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText st) {
		this.staticText12 = st;
	}

	private StaticText stDomicilioPostal = new StaticText();

	public StaticText getStDomicilioPostal() {
		return stDomicilioPostal;
	}

	public void setStDomicilioPostal(StaticText st) {
		this.stDomicilioPostal = st;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private Button btnSeleccionarDomicilioSolicitanteContador = new Button();

	public Button getBtnSeleccionarDomicilioSolicitanteContador() {
		return btnSeleccionarDomicilioSolicitanteContador;
	}

	public void setBtnSeleccionarDomicilioSolicitanteContador(Button btnSeleccionarDomicilioSolicitanteContador) {
		this.btnSeleccionarDomicilioSolicitanteContador = btnSeleccionarDomicilioSolicitanteContador;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();
	private Button btnSeleccionarPersonaJuridicaContador = new Button();

	public Button getBtnSeleccionarPersonaJuridicaContador() {
		return btnSeleccionarPersonaJuridicaContador;
	}

	public void setBtnSeleccionarPersonaJuridicaContador(Button btnSeleccionarPersonaJuridicaContador) {
		this.btnSeleccionarPersonaJuridicaContador = btnSeleccionarPersonaJuridicaContador;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarContador = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarContador() {
		return btnLimpiarContador;
	}

	public void setBtnLimpiarContador(HtmlAjaxCommandButton btnLimpiarContador) {
		this.btnLimpiarContador = btnLimpiarContador;
	}

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	private Button btnSeleccionarLibretaSanitaria = new Button();

	public Button getBtnSeleccionarLibretaSanitaria() {
		return btnSeleccionarLibretaSanitaria;
	}

	public void setBtnSeleccionarLibretaSanitaria(Button b) {
		this.btnSeleccionarLibretaSanitaria = b;
	}

	private TableColumn tableColumn13 = new TableColumn();

	public TableColumn getTableColumn13() {
		return tableColumn13;
	}

	public void setTableColumn13(TableColumn tc) {
		this.tableColumn13 = tc;
	}

	private StaticText staticText13 = new StaticText();

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText st) {
		this.staticText13 = st;
	}

	private TableColumn tableColumn14 = new TableColumn();

	public TableColumn getTableColumn14() {
		return tableColumn14;
	}

	public void setTableColumn14(TableColumn tc) {
		this.tableColumn14 = tc;
	}

	private TextArea textArea1 = new TextArea();

	public TextArea getTextArea1() {
		return textArea1;
	}

	public void setTextArea1(TextArea ta) {
		this.textArea1 = ta;
	}

	private Button btnModificarLocalComercial = new Button();

	public Button getBtnModificarLocalComercial() {
		return btnModificarLocalComercial;
	}

	public void setBtnModificarLocalComercial(Button b) {
		this.btnModificarLocalComercial = b;
	}

	private StaticText staticText14 = new StaticText();

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText st) {
		this.staticText14 = st;
	}

	private Button btnConsultarLocalComercial = new Button();

	public Button getBtnConsultarLocalComercial() {
		return btnConsultarLocalComercial;
	}

	public void setBtnConsultarLocalComercial(Button b) {
		this.btnConsultarLocalComercial = b;
	}

	private Button btnSeleccionarDomicilioSolicitante = new Button();

	public Button getBtnSeleccionarDomicilioSolicitante() {
		return btnSeleccionarDomicilioSolicitante;
	}

	public void setBtnSeleccionarDomicilioSolicitante(Button b) {
		this.btnSeleccionarDomicilioSolicitante = b;
	}

	private TableColumn tableColumn15 = new TableColumn();

	public TableColumn getTableColumn15() {
		return tableColumn15;
	}

	public void setTableColumn15(TableColumn tc) {
		this.tableColumn15 = tc;
	}

	private TextArea textArea2 = new TextArea();

	public TextArea getTextArea2() {
		return textArea2;
	}

	public void setTextArea2(TextArea ta) {
		this.textArea2 = ta;
	}

	private TableColumn tableColumn16 = new TableColumn();

	public TableColumn getTableColumn16() {
		return tableColumn16;
	}

	public void setTableColumn16(TableColumn tc) {
		this.tableColumn16 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TableColumn tableColumn17 = new TableColumn();

	public TableColumn getTableColumn17() {
		return tableColumn17;
	}

	public void setTableColumn17(TableColumn tc) {
		this.tableColumn17 = tc;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private StaticText staticText5 = new StaticText();

	public void setBtnAgregarLocalComercial(Button b) {
		this.btnAgregarLocalComercial = b;
	}

	protected HtmlAjaxCommandButton btnQuitarLocalComercial = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarLibretaSanitaria = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarRubro = new HtmlAjaxCommandButton();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public HtmlAjaxCommandButton getBtnQuitarRubro() {
		return btnQuitarRubro;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	public void setBtnQuitarRubro(HtmlAjaxCommandButton btnQuitarRubro) {
		this.btnQuitarRubro = btnQuitarRubro;
	}

	private StaticText staticText15 = new StaticText();

	public Button getBtnAgregarLibretaSanitaria() {
		return btnAgregarLibretaSanitaria;
	}

	public StaticText getStaticText15() {
		return staticText15;
	}

	public void setStaticText15(StaticText st) {
		this.staticText15 = st;
	}

	public HtmlAjaxCommandButton getBtnQuitarLibretaSanitaria() {
		return btnQuitarLibretaSanitaria;
	}

	private StaticText staticText16 = new StaticText();
	private StaticText staticText17 = new StaticText();
	private StaticText staticText18 = new StaticText();
	private StaticText staticText19 = new StaticText();
	private StaticText staticText20 = new StaticText();

	public void setBtnQuitarLibretaSanitaria(HtmlAjaxCommandButton btnQuitarLibretaSanitaria) {
		this.btnQuitarLibretaSanitaria = btnQuitarLibretaSanitaria;
	}

	public StaticText getStaticText17() {
		return staticText17;
	}

	public HtmlAjaxCommandButton getBtnQuitarLocalComercial() {
		return btnQuitarLocalComercial;
	}

	public void setStaticText17(StaticText staticText17) {
		this.staticText17 = staticText17;
	}

	public void setBtnQuitarLocalComercial(HtmlAjaxCommandButton btnQuitarLocalComercial) {
		this.btnQuitarLocalComercial = btnQuitarLocalComercial;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText18() {
		return staticText18;
	}

	public void setStaticText18(StaticText staticText18) {
		this.staticText18 = staticText18;
	}

	public StaticText getStaticText19() {
		return staticText19;
	}

	public void setStaticText19(StaticText staticText19) {
		this.staticText19 = staticText19;
	}

	public StaticText getStaticText20() {
		return staticText20;
	}

	public void setStaticText20(StaticText staticText20) {
		this.staticText20 = staticText20;
	}

	public StaticText getStaticText16() {
		return staticText16;
	}

	public void setStaticText16(StaticText st) {
		this.staticText16 = st;
	}

	private Button btnModificarTransporteVehicular = new Button();

	public Button getBtnModificarTransporteVehicular() {
		return btnModificarTransporteVehicular;
	}

	public void setBtnModificarTransporteVehicular(Button b) {
		this.btnModificarTransporteVehicular = b;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private TextField tfDenominacionEntidad = new TextField();

	public TextField getTfDenominacionEntidad() {
		return tfDenominacionEntidad;
	}

	public void setTfDenominacionEntidad(TextField tf) {
		this.tfDenominacionEntidad = tf;
	}

	private Label label8 = new Label();

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label l) {
		this.label8 = l;
	}

	private Table table4 = new Table();
	private Table table5 = new Table();
	private Table table6 = new Table();

	public Table getTable6() {
		return table6;
	}

	public void setTable6(Table table6) {
		this.table6 = table6;
	}

	public Table getTable5() {
		return table5;
	}

	public void setTable5(Table table5) {
		this.table5 = table5;
	}

	public Table getTable4() {
		return table4;
	}

	public void setTable4(Table t) {
		this.table4 = t;
	}

	private TableRowGroup tableRowGroup4 = new TableRowGroup();
	private TableRowGroup tableRowGroup5 = new TableRowGroup();
	private TableRowGroup tableRowGroup6 = new TableRowGroup();

	public TableRowGroup getTableRowGroup6() {
		return tableRowGroup6;
	}

	public void setTableRowGroup6(TableRowGroup tableRowGroup6) {
		this.tableRowGroup6 = tableRowGroup6;
	}

	public TableRowGroup getTableRowGroup5() {
		return tableRowGroup5;
	}

	public void setTableRowGroup5(TableRowGroup tableRowGroup5) {
		this.tableRowGroup5 = tableRowGroup5;
	}

	public TableRowGroup getTableRowGroup4() {
		return tableRowGroup4;
	}

	public void setTableRowGroup4(TableRowGroup trg) {
		this.tableRowGroup4 = trg;
	}

	private ObjectListDataProvider ldpClausuraSHPS = new ObjectListDataProvider();
	private ObjectListDataProvider ldpRubroSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRubroSHPS() {
		return ldpRubroSHPS;
	}

	public void setLdpRubroSHPS(ObjectListDataProvider ldpRubroSHPS) {
		this.ldpRubroSHPS = ldpRubroSHPS;
	}

	public ObjectListDataProvider getLdpClausuraSHPS() {
		return ldpClausuraSHPS;
	}

	public void setLdpClausuraSHPS(ObjectListDataProvider oldp) {
		this.ldpClausuraSHPS = oldp;
	}

	private PanelGroup groupPanel2 = new PanelGroup();
	private PanelGroup groupPanel5 = new PanelGroup();

	public PanelGroup getGroupPanel5() {
		return groupPanel5;
	}

	public void setGroupPanel5(PanelGroup groupPanel5) {
		this.groupPanel5 = groupPanel5;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup pg) {
		this.groupPanel2 = pg;
	}

	private Button btnAgregarClausura = new Button();
	private Button btnAgregarRubro = new Button();

	public Button getBtnAgregarRubro() {
		return btnAgregarRubro;
	}

	public void setBtnAgregarRubro(Button btnAgregarRubro) {
		this.btnAgregarRubro = btnAgregarRubro;
	}

	public Button getBtnAgregarClausura() {
		return btnAgregarClausura;
	}

	public void setBtnAgregarClausura(Button b) {
		this.btnAgregarClausura = b;
	}

	private Button btnModificarTransporteVehicular1 = new Button();

	public Button getBtnModificarTransporteVehicular1() {
		return btnModificarTransporteVehicular1;
	}

	public void setBtnModificarTransporteVehicular1(Button b) {
		this.btnModificarTransporteVehicular1 = b;
	}

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	private RadioButton radioButton4 = new RadioButton();
	private RadioButton radioButton5 = new RadioButton();
	private RadioButton radioButton6 = new RadioButton();

	public RadioButton getRadioButton6() {
		return radioButton6;
	}

	public void setRadioButton6(RadioButton radioButton6) {
		this.radioButton6 = radioButton6;
	}

	public RadioButton getRadioButton5() {
		return radioButton5;
	}

	public void setRadioButton5(RadioButton radioButton5) {
		this.radioButton5 = radioButton5;
	}

	public RadioButton getRadioButton4() {
		return radioButton4;
	}

	public void setRadioButton4(RadioButton rb) {
		this.radioButton4 = rb;
	}

	private TableColumn tableColumn10 = new TableColumn();

	public TableColumn getTableColumn10() {
		return tableColumn10;
	}

	public void setTableColumn10(TableColumn tc) {
		this.tableColumn10 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn12 = new TableColumn();

	public TableColumn getTableColumn12() {
		return tableColumn12;
	}

	public void setTableColumn12(TableColumn tc) {
		this.tableColumn12 = tc;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private TableColumn tableColumn18 = new TableColumn();

	public TableColumn getTableColumn18() {
		return tableColumn18;
	}

	public void setTableColumn18(TableColumn tc) {
		this.tableColumn18 = tc;
	}

	private Checkbox checkbox1 = new Checkbox();

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox c) {
		this.checkbox1 = c;
	}

	private TableColumn tableColumn19 = new TableColumn();
	private TableColumn tableColumn20 = new TableColumn();
	private TableColumn tableColumn21 = new TableColumn();
	private TableColumn tableColumn22 = new TableColumn();
	private TableColumn tableColumn23 = new TableColumn();
	private TableColumn tableColumn24 = new TableColumn();
	private TableColumn tableColumn25 = new TableColumn();
	private TableColumn tableColumn26 = new TableColumn();
	private TableColumn tableColumn27 = new TableColumn();
	private TableColumn tableColumn28 = new TableColumn();
	private TableColumn tableColumn29 = new TableColumn();

	public TableColumn getTableColumn29() {
		return tableColumn29;
	}

	public void setTableColumn29(TableColumn tableColumn29) {
		this.tableColumn29 = tableColumn29;
	}

	public TableColumn getTableColumn28() {
		return tableColumn28;
	}

	public void setTableColumn28(TableColumn tableColumn28) {
		this.tableColumn28 = tableColumn28;
	}

	public TableColumn getTableColumn26() {
		return tableColumn26;
	}

	public void setTableColumn26(TableColumn tableColumn26) {
		this.tableColumn26 = tableColumn26;
	}

	public TableColumn getTableColumn27() {
		return tableColumn27;
	}

	public void setTableColumn27(TableColumn tableColumn27) {
		this.tableColumn27 = tableColumn27;
	}

	public TableColumn getTableColumn23() {
		return tableColumn23;
	}

	public void setTableColumn23(TableColumn tableColumn23) {
		this.tableColumn23 = tableColumn23;
	}

	public TableColumn getTableColumn24() {
		return tableColumn24;
	}

	public void setTableColumn24(TableColumn tableColumn24) {
		this.tableColumn24 = tableColumn24;
	}

	public TableColumn getTableColumn25() {
		return tableColumn25;
	}

	public void setTableColumn25(TableColumn tableColumn25) {
		this.tableColumn25 = tableColumn25;
	}

	public TableColumn getTableColumn22() {
		return tableColumn22;
	}

	public void setTableColumn22(TableColumn tableColumn22) {
		this.tableColumn22 = tableColumn22;
	}

	public TableColumn getTableColumn20() {
		return tableColumn20;
	}

	public void setTableColumn20(TableColumn tableColumn20) {
		this.tableColumn20 = tableColumn20;
	}

	public TableColumn getTableColumn21() {
		return tableColumn21;
	}

	public void setTableColumn21(TableColumn tableColumn21) {
		this.tableColumn21 = tableColumn21;
	}

	public TableColumn getTableColumn19() {
		return tableColumn19;
	}

	public void setTableColumn19(TableColumn tc) {
		this.tableColumn19 = tc;
	}

	private TextArea textArea3 = new TextArea();
	private TextArea textArea4 = new TextArea();

	public TextArea getTextArea4() {
		return textArea4;
	}

	public void setTextArea4(TextArea textArea4) {
		this.textArea4 = textArea4;
	}

	public TextArea getTextArea3() {
		return textArea3;
	}

	public void setTextArea3(TextArea ta) {
		this.textArea3 = ta;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMDocEspSHPS() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new DocumentoSHPS());
		ep.getObjetos().add(ind++, null); // Persona solicitante: personaFisica o personaJuridica
		ep.getObjetos().add(ind++, new ArrayList()); // listaLibretaSanitaria
		ep.getObjetos().add(ind++, new Domicilio());
		ep.getObjetos().add(ind++, new ArrayList()); // Tabla 1: locales comerciales
		ep.getObjetos().add(ind++, new ArrayList()); // Tabla 2: transportes vehiculares
		ep.getObjetos().add(ind++, new ArrayList()); // Tabla 3: log de modificaciones
		ep.getObjetos().add(ind++, new ArrayList()); // 7 // tabla 6 lista regAlicuota = rubro
		ep.getObjetos().add(ind++, new ArrayList()); // Tabla 4: clausuras
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos
		ep.getObjetos().add(ind++, new LogModificacionDocEsp()); // LogModifcacionDocEsp
		ep.getObjetos().add(POSICION_CONTADOR, null); // persona F o J contador
		ind++;

		// ep.getObjetos().add(ind++, new PersonaFisica());

		ep.getObjetos().add(ind++, null); // 12 FiltroLogLiquidacion

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		if(this.getCommunicationSAICBean().getListaLogLiquidacion() != null) {
			this.getCommunicationSAICBean().getListaLogLiquidacion().clear();
		}

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		DocumentoSHPS documentoSHPS = this.obtenerObjetoDelElementoPila(ind++, DocumentoSHPS.class);
		Persona persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		ArrayList listaLibretasSanitarias = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Domicilio domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		ArrayList locales = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList transportes = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList modificaciones = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList listaAsocRubros = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList clausuras = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList atributosDinamicos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		LogModificacionDocEsp locLogModDocEsp = this.obtenerObjetoDelElementoPila(ind++, LogModificacionDocEsp.class);
		Persona contador = (Persona) this.obtenerObjetoDelElementoPila(ind++);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(12, FiltroLogLiquidacion.class);

		documentoSHPS.setNumeroInscripcion(getTextFieldValue(this.getTfNumeroInscripcion()));
		documentoSHPS.setNombre(getTextFieldValue(this.getTfNombre()));
		documentoSHPS.setFechaInicioActividad(getTextFieldValueDate(this.getTfFechaInicio()));
		documentoSHPS.setFechaCeseActividad(getTextFieldValueDate(this.getTfFechaCese()));
		documentoSHPS.setDenominacionEntidad(getTextFieldValue(this.getTfDenominacionEntidad()));

		if(domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		documentoSHPS.setDomicilio(domicilio);

		this.getObjectListDataProviderTabla5().commitChanges();
		listaLibretasSanitarias = (ArrayList) this.getObjectListDataProviderTabla5().getList();
		if(listaLibretasSanitarias.size() <= 0) {
			listaLibretasSanitarias = null;
		}
		if(documentoSHPS.getListaLibretaSanitarias() != null) {
			documentoSHPS.getListaLibretaSanitarias().clear();
			if(listaLibretasSanitarias != null) {
				documentoSHPS.getListaLibretaSanitarias().addAll(listaLibretasSanitarias);
			}
		}
		this.setListaDelCommunicationTabla5(listaLibretasSanitarias);

		this.getObjectListDataProviderTabla6().commitChanges();
		listaAsocRubros = (ArrayList) this.getObjectListDataProviderTabla6().getList();
		if(listaAsocRubros.size() <= 0) {
			listaAsocRubros = null;
		}

		if(listaAsocRubros == null) {
			listaAsocRubros = new ArrayList();
		}
		documentoSHPS.setListaAsocRegAlicuota(listaAsocRubros);
		this.setListaDelCommunicationTabla6(listaAsocRubros);

		this.getObjectListDataProvider().commitChanges();
		locales = (ArrayList) this.getObjectListDataProvider().getList();
		if(locales.size() <= 0) {
			locales = null;
		}
		if(documentoSHPS.getListaLocalesComerciales() != null) {
			documentoSHPS.getListaLocalesComerciales().clear();
			if(locales != null) {
				documentoSHPS.getListaLocalesComerciales().addAll(locales);
			}
		}
		this.setListaDelCommunication(locales);

		this.getObjectListDataProviderTabla2().commitChanges();
		transportes = (ArrayList) this.getObjectListDataProviderTabla2().getList();
		if(transportes.size() <= 0) {
			transportes = null;
		}
		if(documentoSHPS.getListaTransportesVehiculares() != null) {
			documentoSHPS.getListaTransportesVehiculares().clear();
			if(transportes != null) {
				documentoSHPS.getListaTransportesVehiculares().addAll(transportes);
			}
		}
		this.setListaDelCommunicationTabla2(transportes);

		this.getObjectListDataProviderTabla4().commitChanges();
		clausuras = (ArrayList) this.getObjectListDataProviderTabla4().getList();
		if(clausuras.size() <= 0) {
			clausuras = null;
		}
		this.setListaDelCommunicationTabla4(clausuras);

		AsocRubro rubroPrincipal = (AsocRubro) this.obtenerPrincipalSeleccionado();
		if(rubroPrincipal != null) {
			documentoSHPS.setRubroPrincipal(rubroPrincipal);
		}

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		documentoSHPS.setListaAtributosDinamicos(atributosDinamicos);

		documentoSHPS.setContador(contador);
		documentoSHPS.getObligacion().setPersona(persona);

		filtroLogLiq.setObligacion(documentoSHPS.getObligacion());
		filtroLogLiq.setUsuario(getTextFieldValue(this.getTfFiltrarUsuarioLogLiq()));
		filtroLogLiq.setEvento(getDDEnumValue(this.getDdFiltrarEventoLogLiq(), LogLiquidacion.Evento.class));
		filtroLogLiq.setFechaDesde(getTextFieldValueDate(this.getTfFiltrarFechaDesdeLogLiq()));
		filtroLogLiq.setFechaHasta(getTextFieldValueDate(this.getTfFiltrarFechaHastaLogLiq()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, documentoSHPS);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, listaLibretasSanitarias);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, locales);
		this.getElementoPila().getObjetos().set(ind++, transportes);
		ind++; // solo se muestran las modificaciones
		this.getElementoPila().getObjetos().set(ind++, listaAsocRubros);
		this.getElementoPila().getObjetos().set(ind++, clausuras);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
		ind++;
		this.getElementoPila().getObjetos().set(ind++, contador);
		this.getElementoPila().getObjetos().set(12, filtroLogLiq);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		// acomodarSeleccionado();
		DocumentoSHPS documentoSHPS = null;
		Persona persona = null;
		// LibretaSanitaria libretaSanitaria = null;
		Domicilio domicilio = null;
		ArrayList locales = null;
		ArrayList transportes = null;
		ArrayList modificaciones = null;
		ArrayList listaAsocRubro = null;
		ArrayList clausuras = null;
		ArrayList atributosDinamicos = null;
		ArrayList listaLibretasSanitarias = null;
		Persona contador = null;

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();

			if(respuesta instanceof Domicilio) {
				this.getElementoPila().getObjetos().set(3, respuesta);
			}

			if(respuesta instanceof ClausuraSHPS) {
				ClausuraSHPS clausuraRespuesta = (ClausuraSHPS) respuesta;
				clausuras = this.obtenerObjetoDelElementoPila(8, ArrayList.class);
				clausuras.add(clausuraRespuesta);
				this.getElementoPila().getObjetos().set(posicion, clausuras);
			}
		}

		try {
			DocumentoSHPS locDocumento = (DocumentoSHPS) this.obtenerObjetoDelElementoPila(0);
			this.getElementoPila()
					.getObjetos()
					.set(9,
							this.getComunicationBean().getRemoteSystemParametro()
									.getAtributosPorRecurso(DocumentoSHPS.serialVersionUID, locDocumento.getListaAtributosDinamicos(), null));
		} catch(Exception e) {
			e.printStackTrace();
		}

		ind = 0;
		documentoSHPS = this.obtenerObjetoDelElementoPila(ind++, DocumentoSHPS.class);
		persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		listaLibretasSanitarias = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		locales = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		transportes = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		modificaciones = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		listaAsocRubro = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		clausuras = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		atributosDinamicos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		LogModificacionDocEsp locLogModDocEsp = this.obtenerObjetoDelElementoPila(ind++, LogModificacionDocEsp.class);
		contador = (Persona) this.obtenerObjetoDelElementoPila(11);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(12, FiltroLogLiquidacion.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		// this.ocultarLibretaSanitaria(persona);

		this.getTfNumeroInscripcion().setText(documentoSHPS.getNumeroInscripcion());
		this.getTfNombre().setText(documentoSHPS.getNombre());
		if(documentoSHPS.getFechaInicioActividad() != null) {
			this.getTfFechaInicio().setText(Conversor.getStringDeFechaCorta(documentoSHPS.getFechaInicioActividad()));
		}
		if(documentoSHPS.getFechaCeseActividad() != null) {
			this.getTfFechaCese().setText(Conversor.getStringDeFechaCorta(documentoSHPS.getFechaCeseActividad()));
		}
		if(persona != null && persona.getIdPersona() != -1) {
			this.getTfPersona().setText(persona.toString());
		}
		if(contador != null && contador.getIdPersona() != -1) {
			this.getTfContador().setText(contador.toString());
		}
		// this.getTfLibretaSanitaria().setText(documentoSHPS.getLibretaSanitaria());
		this.getTfDenominacionEntidad().setText(documentoSHPS.getDenominacionEntidad());
		this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));

		// Tabla 1
		this.getObjectListDataProvider().setList(locales);
		this.setListaDelCommunication(locales);
		// Tabla 2
		this.getObjectListDataProviderTabla2().setList(transportes);
		this.setListaDelCommunicationTabla2(transportes);
		// Tabla 3
		this.getObjectListDataProviderTabla3().setList(modificaciones);
		this.setListaDelCommunicationTabla3(modificaciones);
		// Tabla 4
		this.getObjectListDataProviderTabla4().setList(clausuras);
		this.setListaDelCommunicationTabla4(clausuras);
		// Tabla 5
		this.getObjectListDataProviderTabla5().setList(listaLibretasSanitarias);
		this.setListaDelCommunicationTabla5(listaLibretasSanitarias);
		// Tabla 6
		this.getObjectListDataProviderTabla6().setList(listaAsocRubro);
		this.setListaDelCommunicationTabla6(listaAsocRubro);

		if(documentoSHPS.getRubroPrincipal() != null) {
			this.seleccionarFilaPrincipal(documentoSHPS.getRubroPrincipal());
		} else if(listaAsocRubro.size() == 1) {
			this.seleccionarFilaPrincipal(listaAsocRubro.get(0));
		}

		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);

		this.getTfFiltrarUsuarioLogLiq().setText(filtroLogLiq.getUsuario());
		this.getDdFiltrarEventoLogLiq().setSelected(filtroLogLiq.getEvento());
		this.getTfFiltrarFechaDesdeLogLiq().setText(filtroLogLiq.getFechaDesde());
		this.getTfFiltrarFechaHastaLogLiq().setText(filtroLogLiq.getFechaHasta());
	}

	private ArrayList getListaDelCommunicationAtributosDinamicos() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaAtributosDinamicosSHPS();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosSHPS(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLocalComercialSHPS();
	}

	private ObjectListDataProvider getObjectListDataProviderTabla2() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpTransporteVehicularSHPS();
	}

	private ObjectListDataProvider getObjectListDataProviderTabla3() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLogModificacionesSHPS();
	}

	private ObjectListDataProvider getObjectListDataProviderTabla4() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpClausuraSHPS();
	}

	private ObjectListDataProvider getObjectListDataProviderTabla5() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLibretasSanitarias();
	}

	private ObjectListDataProvider getObjectListDataProviderTabla6() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpRubroSHPS();
	}

	// Tabla 1
	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaLocalesComercialesSHPS();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaLocalesComercialesSHPS(lista);
	}

	// Tabla 2

	private ArrayList getListaDelCommunicationTabla2() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaTransportesVehicularesSHPS();
	}

	private void setListaDelCommunicationTabla2(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaTransportesVehicularesSHPS(lista);
	}

	// Tabla 3

	private List getListaDelCommunicationTabla3() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaLogModificacionesSHPS();
	}

	private void setListaDelCommunicationTabla3(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaLogModificacionesSHPS(lista);
	}

	// Tabla 4

	private ArrayList getListaDelCommunicationTabla4() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaClausurasSHPS();
	}

	private void setListaDelCommunicationTabla4(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaClausurasSHPS(lista);
	}

	// Tabla 5

	private ArrayList getListaDelCommunicationTabla5() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaLibrerasSanitariasObligaciones();
	}

	private void setListaDelCommunicationTabla5(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaLibrerasSanitariasObligaciones(lista);
	}

	// Tabla 6

	private ArrayList getListaDelCommunicationTabla6() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaRubrosSHPS();
	}

	private void setListaDelCommunicationTabla6(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaRubrosSHPS(lista);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Metodos estaticos de la pagina">
	public RowKey getPrincipalSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup7");
			rk = this.getObjectListDataProviderTabla6().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private Object obtenerPrincipalSeleccionado() {
		RowKey rk = null;
		Object ppal = null;
		try {
			rk = this.getPrincipalSeleccionado();
			if(rk != null) {
				int index = getNroFila(rk.toString());
				ppal = this.getObjectListDataProviderTabla6().getObjects()[index];
			}
		} catch(Exception ex) {
		}
		return ppal;
	}

	private void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
	}

	// Tabla 2

	private void limpiarTabla2() {
		this.getObjectListDataProviderTabla2().getList().clear();
	}

	// Tabla 3

	private void limpiarTabla3() {
		this.getObjectListDataProviderTabla3().getList().clear();
	}

	// Tabla 4

	private void limpiarTabla4() {
		this.getObjectListDataProviderTabla4().getList().clear();
	}

	// <editor-fold defaultstate="collapsed"
	// desc="Metodos para seleccionar RaddioButton">

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

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	private Object lastSelected2 = null;

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	// Tabla 3

	public String getCurrentRow3() {
		return tableRowGroup3.getRowKey().getRowId();
	}

	public void setCurrentRow3(int row) {
	}

	private Object lastSelected3 = null;

	public Object getRBSelected3() {
		String sv = (String) radioButton3.getSelectedValue();
		return sv.equals(lastSelected3) ? sv : null;
	}

	public void setRBSelected3(Object selected) {
		if(selected != null) {
			lastSelected3 = selected;
		}
	}

	// Tabla 4

	public String getCurrentRow4() {
		return tableRowGroup4.getRowKey().getRowId();
	}

	public void setCurrentRow4(int row) {
	}

	private Object lastSelected4 = null;

	public Object getRBSelected4() {
		String sv = (String) radioButton4.getSelectedValue();
		return sv.equals(lastSelected4) ? sv : null;
	}

	public void setRBSelected4(Object selected) {
		if(selected != null) {
			lastSelected4 = selected;
		}
	}

	// Tabla 5
	public String getCurrentRow5() {
		return tableRowGroup5.getRowKey().getRowId();
	}

	public void setCurrentRow5(int row) {
	}

	private Object lastSelected5 = null;

	public Object getRBSelected5() {
		String sv = (String) radioButton5.getSelectedValue();
		return sv.equals(lastSelected5) ? sv : null;
	}

	public void setRBSelected5(Object selected) {
		if(selected != null) {
			lastSelected5 = selected;
		}
	}

	// Tabla 6
	public String getCurrentRow6() {
		return tableRowGroup6.getRowKey().getRowId();
	}

	public void setCurrentRow6(int row) {
	}

	private Object lastSelected6 = null;

	public Object getRBSelected6() {
		String sv = (String) radioButton6.getSelectedValue();
		return sv.equals(lastSelected6) ? sv : null;
	}

	public void setRBSelected6(Object selected) {
		if(selected != null) {
			lastSelected6 = selected;
		}
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

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if(cantFilas > 0) {
			// si hay al menos una fila
			if(posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}

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

	// Tabla 2
	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getObjectListDataProviderTabla2().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	// Tabla 3

	public RowKey getSeleccionado3() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup3");
			rk = this.getObjectListDataProviderTabla3().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	// Tabla 5

	public RowKey getSeleccionado5() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup5");
			rk = this.getObjectListDataProviderTabla5().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	// Tabla 6

	public RowKey getSeleccionado6() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup6");
			rk = this.getObjectListDataProviderTabla6().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public String btnSeleccionarDomicilioPostal_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 3;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// Persona locPersona = (Persona)
			// this.obtenerObjetoDelElementoPila(1, Persona.class);
			// Domicilio locDomicilio = (Domicilio)
			// this.obtenerObjetoDelElementoPila(posicionObjetoSeleccionado,
			// Domicilio.class);
			//
			// if (locDomicilio.getIdDomicilio() ==
			// locPersona.getDomicilioPostal().getIdDomicilio()) {
			// retorno = "AgregarDomicilio";
			// } else {
			// this.getRequestBean1().setObjetoABM(locDomicilio);
			// retorno = "ModificarDomicilio";
			// }
			Domicilio domicilio = this.obtenerObjetoDelElementoPila(3, Domicilio.class);
			Localidad localidad = domicilio.getLocalidad();

			if(localidad != null) {
				this.getRequestBean1().setObjetoABM(domicilio);
				retorno = "ModificarDomicilio";
			} else {
				retorno = "AgregarDomicilio";
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarDomicilioPostal_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(3, this.getStDomicilioPostal());
			this.getStDomicilioPostal().setText(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarDomicilioSolicitante_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 3;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Object pers = this.getElementoPila().getObjetos().get(1);
			Persona persona = null;
			if(pers != null) {
				persona = (Persona) pers;
			}
			if(persona != null && persona.getIdPersona() != -1) {
				Domicilio domicilio = persona.getDomicilioPostal();
				this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
			} else {
				warn("No existe la Persona Solicitante para obtener su Domicilio.");
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarDomicilioContador_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 3;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Object pers = this.getElementoPila().getObjetos().get(11);
			Persona persona = null;
			if(pers != null) {
				persona = (Persona) pers;
			}
			if(persona != null && persona.getIdPersona() != -1) {
				Domicilio domicilio = persona.getDomicilioPostal();
				this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
			} else {
				warn("No existe la Persona Solicitante para obtener su Domicilio.");
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarLocalComercial_action() {
		return navegarParaSeleccionar("AdminLocalComercial");
	}

	public String btnAgregarLibretaSanitaria_action() {
		return navegarParaSeleccionar("AdminLibretaSanitaria");
	}

	public String btnQuitarLocalComercial_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarLibretaSanitaria_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado5();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderTabla5().getObjects()[index];
					this.getListaDelCommunicationTabla5().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarTransporteVehicular_action() {
		return navegarParaSeleccionar("AdminTransporteVehicular");
	}

	public String btnQuitarTransporteVehicular_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderTabla2().getObjects()[index];
					this.getListaDelCommunicationTabla2().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarClausura_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 8;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Object documento = this.obtenerObjetoDelElementoPila(0, DocumentoSHPS.class);
			this.getRequestBean1().setObjetoSeleccion(documento);

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AgregarClausuraSHPS";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarContador_action() {
		return navegarParaSeleccionar("AdminPersonaFisica", 11);
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnSeleccionarPersonaJuridicaContador_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica", 11);
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfPersona());
			this.guardarEstadoObjetosUsados();
			// this.ocultarLibretaSanitaria(null);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarContador_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(POSICION_CONTADOR, this.getTfContador());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarRubro_action() {
		return navegarParaSeleccionar("AdminRegAlicuotaSHPS");
	}

	public String btnQuitarRubro_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		this.getObjectListDataProviderTabla6().commitChanges();
		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado6();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderTabla6().getObjects()[index];
					this.getListaDelCommunicationTabla6().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDocEspSHPS";
	}

	public void btnFiltrarLogLiq_action() {
		this.guardarEstadoObjetosUsados();
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				FiltroLogLiquidacion locFiltro = (FiltroLogLiquidacion) this.obtenerObjetoDelElementoPila(12);

				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				List<LogLiquidacion> listaLogs = new ArrayList<LogLiquidacion>();
				listaLogs = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getListaLogLiquidacion(locFiltro);

				this.getCommunicationSAICBean().setListaLogLiquidacion(listaLogs);
				this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void btnLimpiarFiltradoLogLiq_action() {
		this.getElementoPila().getObjetos().set(12, null);

		this.getTfFiltrarUsuarioLogLiq().setText("");
		this.getDdFiltrarEventoLogLiq().setSelected("");
		this.getTfFiltrarFechaDesdeLogLiq().setText("");
		this.getTfFiltrarFechaHastaLogLiq().setText("");

		this.getCommunicationSAICBean().setListaLogLiquidacion(new ArrayList<LogLiquidacion>());
		this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		DocumentoSHPS documentoSHPS = null;
		Persona persona = null;
		Domicilio domicilio = null;
		ArrayList locales = null;
		ArrayList transportes = null;
		ArrayList listaAsocRubro = null;
		ArrayList listaLibretasSanitarias = null;

		Object seleccionado = pObject;
		// int posicionEP = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() -
		// 1, Integer.class).intValue();

		if(seleccionado instanceof Persona) {
			Persona locPersona = null;
			if(seleccionado instanceof PersonaFisica) {
				locPersona = (PersonaFisica) seleccionado;
				try {
					this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
					locPersona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(((PersonaFisica) locPersona).getIdPersonaFisica());
				} catch(RemoteException e) {
					e.printStackTrace();
				} catch(TrascenderFrameworkException e) {
					e.printStackTrace();
				}

			} else if(seleccionado instanceof PersonaJuridica) {
				locPersona = (PersonaJuridica) seleccionado;
				try {
					this.getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(this.getSessionBean1().getLlave());
					locPersona = this.getComunicationBean().getRemoteSystemPersonaJuridica().getPersonaJuridicaPorId(locPersona.getIdPersona());
				} catch(RemoteException e) {
					e.printStackTrace();
				}
			}
			Integer posicion = (Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1);
			if(posicion != null && posicion.equals(11)) {
				this.getElementoPila().getObjetos().set(11, locPersona);
				this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, null);
			} else {
				domicilio = locPersona.getDomicilioPostal();
				this.getElementoPila().getObjetos().set(1, locPersona);
			}
		}

		if(seleccionado instanceof LocalComercial) {
			// posicionEP = 4;
			LocalComercial localSeleccionado = (LocalComercial) seleccionado;
			locales = this.obtenerObjetoDelElementoPila(4, ArrayList.class);

			LocalComercial deLaTabla = null;
			boolean esta = false;
			int i = -1;
			while(i + 1 < locales.size() && !esta) {
				i++;
				deLaTabla = (LocalComercial) locales.get(i);
				esta = (deLaTabla.getIdLocalComercial() == localSeleccionado.getIdLocalComercial());
				// esta =
				// deLaTabla.getNumeroInscripcion().equalsIgnoreCase(localRespuesta.getNumeroInscripcion());
			}
			if(!esta) {
				locales.add(localSeleccionado);
			} else {
				warn("El Local Comercial que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(4, locales);
		}

		if(seleccionado instanceof TransporteVehicular) {
			// posicionEP = 5;
			TransporteVehicular transpSeleccionado = (TransporteVehicular) seleccionado;
			transportes = this.obtenerObjetoDelElementoPila(5, ArrayList.class);

			TransporteVehicular deLaTabla = null;
			boolean esta = false;
			int i = -1;
			while(i + 1 < transportes.size() && !esta) {
				i++;
				deLaTabla = (TransporteVehicular) transportes.get(i);
				esta = (deLaTabla.getIdTransporteVehicular() == transpSeleccionado.getIdTransporteVehicular());
			}
			if(!esta) {
				transportes.add(transpSeleccionado);
			} else {
				warn("El Transporte Vehicular que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(5, transportes);
		}

		if(seleccionado instanceof LibretaSanitaria) {
			LibretaSanitaria libretaSanitariaSeleccionada = (LibretaSanitaria) seleccionado;
			listaLibretasSanitarias = this.obtenerObjetoDelElementoPila(2, ArrayList.class);

			LibretaSanitaria deLaTabla = null;
			boolean esta = false;
			int i = -1;
			while(i + 1 < listaLibretasSanitarias.size() && !esta) {
				i++;
				deLaTabla = (LibretaSanitaria) listaLibretasSanitarias.get(i);
				esta = (deLaTabla.getIdLibretaSanitaria() == libretaSanitariaSeleccionada.getIdLibretaSanitaria());
			}
			if(!esta) {
				listaLibretasSanitarias.add(libretaSanitariaSeleccionada);
			} else {
				warn("La Libreta Sanitaria que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(2, listaLibretasSanitarias);
		}

		if(seleccionado instanceof Rubro) {
			Rubro rubroSeleccionado = (Rubro) seleccionado;
			listaAsocRubro = this.obtenerObjetoDelElementoPila(7, ArrayList.class);

			AsocRubro deLaTabla = null;
			boolean esta = false;
			int i = -1;
			while(i + 1 < listaAsocRubro.size() && !esta) {
				i++;
				deLaTabla = (AsocRubro) listaAsocRubro.get(i);
				esta = (deLaTabla.getRegistroAlicuota().getIdTipoAlicuota() == rubroSeleccionado.getIdTipoAlicuota());
			}
			if(!esta) {
				AsocRubro nuevaAsoc = new AsocRubro();
				try {
					this.getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().setLlave(this.getSessionBean1().getLlave());
					rubroSeleccionado = (Rubro) this.getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().getRegistroAlicuotaPorId(rubroSeleccionado.getIdTipoAlicuota());
				} catch(Exception e) {
					e.printStackTrace();
				}
				nuevaAsoc.setPrincipal(false);
				nuevaAsoc.setDocHabilitanteEspecializado(documentoSHPS);
				nuevaAsoc.setRegistroAlicuota(rubroSeleccionado);
				listaAsocRubro.add(nuevaAsoc);
			} else {
				warn("El Rubro que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(7, listaAsocRubro);
		}

	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		int ind = 0;
		DocumentoSHPS documentoSHPS = null;
		Persona persona = null;
		// LibretaSanitaria libretaSanitaria = null;
		Domicilio domicilio = null;
		ArrayList locales = null;
		ArrayList transportes = null;
		ArrayList modificaciones = null;
		ArrayList listaAsocRubro = null;
		ArrayList clausuras = null;
		ArrayList atributosDinamicos = null;
		ArrayList listaLibretasSanitarias = null;
		Persona contador = null;

		// Si es intancia de Plantilla Obligacion, es un agregar, si no, viene
		// la obligacion directamente
		if(pObject instanceof PlantillaObligacion) {
			PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;
			Obligacion obligacion = null;

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				plantillaObligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().getPlantillaObligacion(plantillaObligacion.getIdPlantillaObligacion());
				obligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().generarArbol(plantillaObligacion);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.setListaDelCommunicationDocsGeneradoresDeuda(new ArrayList());

			documentoSHPS = (DocumentoSHPS) obligacion.getDocumentoEspecializado();
			documentoSHPS.setObligacion(obligacion);
			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, documentoSHPS);
		} else {
			Obligacion obligacion = (Obligacion) pObject;
			documentoSHPS = null;

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(this.getSessionBean1().getLlave());
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(obligacion.getIdObligacion());
				documentoSHPS = this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getDocumentoHabilitanteSHPS(obligacion);
			} catch(Exception ex) {
				log(getCasoNavegacion() + "_getDocumentoHabilitanteSHPS:", ex);
				error(getNombrePagina() + " - No se pudo obtener el Documento SHPS: " + ex.getMessage());
			}

			documentoSHPS.setObligacion(obligacion);
			persona = obligacion.getPersona();
			// libretaSanitaria = documentoSHPS.getLibretaSanitaria();
			domicilio = documentoSHPS.getDomicilio();
			locales = new ArrayList(documentoSHPS.getListaLocalesComerciales());
			transportes = new ArrayList(documentoSHPS.getListaTransportesVehiculares());
			modificaciones = new ArrayList(documentoSHPS.getListaModificaciones());
			listaAsocRubro = new ArrayList(documentoSHPS.getListaAsocRegAlicuota());
			clausuras = new ArrayList(documentoSHPS.getListaClausuras());
			listaLibretasSanitarias = new ArrayList(documentoSHPS.getListaLibretaSanitarias());
			contador = documentoSHPS.getContador();

			try {
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				this.setListaDelCommunicationDocsGeneradoresDeuda(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaDocsGeneradoresDeuda(obligacion));
			} catch(Exception e) {
				e.printStackTrace();
			}

			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, documentoSHPS);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, listaLibretasSanitarias);
			this.getElementoPila().getObjetos().set(ind++, domicilio);
			this.getElementoPila().getObjetos().set(ind++, locales);
			this.getElementoPila().getObjetos().set(ind++, transportes);
			this.getElementoPila().getObjetos().set(ind++, modificaciones);
			this.getElementoPila().getObjetos().set(ind++, listaAsocRubro);
			this.getElementoPila().getObjetos().set(ind++, clausuras);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
			ind++;
			this.getElementoPila().getObjetos().set(ind++, contador);

		}
	}

	private void setListaDelCommunicationDocsGeneradoresDeuda(List<Tasa> pLista) {
		this.getCommunicationHabilitacionesBean().setListaDocsGeneradoresDeuda(pLista);
	}

	private List<Tasa> getListaDelCommunicationDocsGeneradoresDeuda() {
		return this.getCommunicationHabilitacionesBean().getListaDocsGeneradoresDeuda();
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		DocumentoSHPS locDocumento = this.obtenerObjetoDelElementoPila(0, DocumentoSHPS.class);
		this.getTablaLogs().getLdpLogs().setList(locDocumento.getListaLogsAuditoria());

		if(this.getListaDelCommunicationDocsGeneradoresDeuda() != null) {
			this.getLdpDocsGeneradoresDeuda().setList(this.getListaDelCommunicationDocsGeneradoresDeuda());
		}
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoSHPS.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS}";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSesion) {
		Long id = Long.parseLong(pId);
		Persona persona = null;

		try {
			persona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, persona);
	}

	public void setContadorAutocompletar(String pId, String pIdSubSesion) {
		Long id = Long.parseLong(pId);
		Persona contador = null;

		try {
			contador = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(POSICION_CONTADOR, contador);
	}

	public boolean isHayPersona() {
		Persona locPersona = (Persona) this.obtenerObjetoDelElementoPila(1);
		return(locPersona != null && locPersona.getIdPersona() != -1);
	}

	public boolean isHayContador() {
		Persona locContador = (Persona) this.obtenerObjetoDelElementoPila(POSICION_CONTADOR);
		return(locContador != null && locContador.getIdPersona() != -1);
	}
}