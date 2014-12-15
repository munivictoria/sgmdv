/*
 * AgregarExencion.java
 *
 */

package muni.saic.ABMExencionObligacion;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
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
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencion;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencionAnio;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencionCalendario;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencionCuota;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencionNumeroCuota;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencionPeriodo;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMExencionObligacion extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalExencionObligacion().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosExencionObligacion(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalExencionObligacion(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalExencionObligacion(null).keySet());

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
			this.getObjectListDataProvider().setObjectType(CondicionAplicacionExencion.class);
		}

		if(this.getListaDelCommunicationObligacion() != null) {
			this.getObjectListDataProviderObligacion().setList(this.getListaDelCommunicationObligacion());
		}

		if(this.getListaDelCommunicationCondicionAplicacionNumeroCuota() != null) {
			this.getObjectListDataProviderCondicionAplicacionNumeroCuota().setList(this.getListaDelCommunicationCondicionAplicacionNumeroCuota());
		}

		Set<String> locListaCalendarios = new HashSet<String>();

		Option[] opCalendarios = new Option[locListaCalendarios.size() + 1];
		int i = 0;
		opCalendarios[i++] = new Option("", "");
		for(String cadaCalendario : locListaCalendarios) {
			opCalendarios[i++] = new Option(cadaCalendario, cadaCalendario);
		}
		ddCalendariosOptions.setOptions(opCalendarios);

		Option[] opPeriodos = new Option[0];
		ddPeriodosOptions.setOptions(opPeriodos);

		Option[] opCuotas = new Option[0];
		ddCuotasOptions.setOptions(opCuotas);

		ddSeAplicaOptions.setOptions(new com.sun.rave.web.ui.model.Option[] {new com.sun.rave.web.ui.model.Option("false", "Según las condiciónes"),
				new com.sun.rave.web.ui.model.Option("true", "Siempre")});
	}

	private Label lblObligacion = new Label();
	private Label lblAnios = new Label();
	private Label lblSeAplica = new Label();
	private DropDown ddSeAplica = new DropDown();
	private SingleSelectOptionsList ddSeAplicaOptions = new SingleSelectOptionsList();
	private Label lblTipoValor = new Label();
	private Label lblFijo = new Label();
	private Label lblPorcentual = new Label();
	private RadioButton rbFijo = new RadioButton();
	private RadioButton rbPorcentual = new RadioButton();
	private Table tablaObligacion = new Table();
	private ObjectListDataProvider ldpObligacion = new ObjectListDataProvider();
	private TableRowGroup trgObligacion = new TableRowGroup();
	private PanelGroup pgObligacion = new PanelGroup();
	private TableColumn tcRbObligacion = new TableColumn();
	private TableColumn tcDocumento = new TableColumn();
	private RadioButton rbObligacion = new RadioButton();
	private StaticText stDocumento = new StaticText();

	private Label lblCondicionAplicacionNumeroCuota = new Label();
	private Table tablaCondicionAplicacionNumeroCuota = new Table();
	private ObjectListDataProvider ldpCondicionAplicacionNumeroCuota = new ObjectListDataProvider();
	private TableRowGroup trgCondicionAplicacionNumeroCuota = new TableRowGroup();
	private PanelGroup pgCondicionAplicacionNumeroCuota = new PanelGroup();
	private TableColumn tcRbCondicionAplicacionNumeroCuota = new TableColumn();
	private TableColumn tcNumeroCuota = new TableColumn();
	private TableColumn tcNumeroPeriodo = new TableColumn();
	private RadioButton rbCondicionAplicacionNumeroCuota = new RadioButton();
	private TextField tfNumeroCuota = new TextField();
	private TextField tfNumeroPeriodo = new TextField();

	public Label getLblCondicionAplicacionNumeroCuota() {
		return lblCondicionAplicacionNumeroCuota;
	}

	public void setLblCondicionAplicacionNumeroCuota(Label lblCondicionAplicacionNumeroCuota) {
		this.lblCondicionAplicacionNumeroCuota = lblCondicionAplicacionNumeroCuota;
	}

	public Table getTablaCondicionAplicacionNumeroCuota() {
		return tablaCondicionAplicacionNumeroCuota;
	}

	public void setTablaCondicionAplicacionNumeroCuota(Table tablaCondicionAplicacionNumeroCuota) {
		this.tablaCondicionAplicacionNumeroCuota = tablaCondicionAplicacionNumeroCuota;
	}

	public ObjectListDataProvider getLdpCondicionAplicacionNumeroCuota() {
		return ldpCondicionAplicacionNumeroCuota;
	}

	public void setLdpCondicionAplicacionNumeroCuota(ObjectListDataProvider ldpCondicionAplicacionNumeroCuota) {
		this.ldpCondicionAplicacionNumeroCuota = ldpCondicionAplicacionNumeroCuota;
	}

	public TableRowGroup getTrgCondicionAplicacionNumeroCuota() {
		return trgCondicionAplicacionNumeroCuota;
	}

	public void setTrgCondicionAplicacionNumeroCuota(TableRowGroup trgCondicionAplicacionNumeroCuota) {
		this.trgCondicionAplicacionNumeroCuota = trgCondicionAplicacionNumeroCuota;
	}

	public PanelGroup getPgCondicionAplicacionNumeroCuota() {
		return pgCondicionAplicacionNumeroCuota;
	}

	public void setPgCondicionAplicacionNumeroCuota(PanelGroup pgCondicionAplicacionNumeroCuota) {
		this.pgCondicionAplicacionNumeroCuota = pgCondicionAplicacionNumeroCuota;
	}

	public TableColumn getTcRbCondicionAplicacionNumeroCuota() {
		return tcRbCondicionAplicacionNumeroCuota;
	}

	public void setTcRbCondicionAplicacionNumeroCuota(TableColumn tcRbCondicionAplicacionNumeroCuota) {
		this.tcRbCondicionAplicacionNumeroCuota = tcRbCondicionAplicacionNumeroCuota;
	}

	public TableColumn getTcNumeroCuota() {
		return tcNumeroCuota;
	}

	public void setTcNumeroCuota(TableColumn tcNumeroCuota) {
		this.tcNumeroCuota = tcNumeroCuota;
	}

	public TableColumn getTcNumeroPeriodo() {
		return tcNumeroPeriodo;
	}

	public void setTcNumeroPeriodo(TableColumn tcNumeroPeriodo) {
		this.tcNumeroPeriodo = tcNumeroPeriodo;
	}

	public RadioButton getRbCondicionAplicacionNumeroCuota() {
		return rbCondicionAplicacionNumeroCuota;
	}

	public void setRbCondicionAplicacionNumeroCuota(RadioButton rbCondicionAplicacionNumeroCuota) {
		this.rbCondicionAplicacionNumeroCuota = rbCondicionAplicacionNumeroCuota;
	}

	public TextField getTfNumeroCuota() {
		return tfNumeroCuota;
	}

	public void setTfNumeroCuota(TextField tfNumeroCuota) {
		this.tfNumeroCuota = tfNumeroCuota;
	}

	public TextField getTfNumeroPeriodo() {
		return tfNumeroPeriodo;
	}

	public void setTfNumeroPeriodo(TextField tfNumeroPeriodo) {
		this.tfNumeroPeriodo = tfNumeroPeriodo;
	}

	public TableColumn getTcRbObligacion() {
		return tcRbObligacion;
	}

	public void setTcRbObligacion(TableColumn tcRbObligacion) {
		this.tcRbObligacion = tcRbObligacion;
	}

	public RadioButton getRbObligacion() {
		return rbObligacion;
	}

	public void setRbObligacion(RadioButton rbObligacion) {
		this.rbObligacion = rbObligacion;
	}

	public PanelGroup getPgObligacion() {
		return pgObligacion;
	}

	public void setPgObligacion(PanelGroup pgObligacion) {
		this.pgObligacion = pgObligacion;
	}

	public Table getTablaObligacion() {
		return tablaObligacion;
	}

	public void setTablaObligacion(Table tablaObligacion) {
		this.tablaObligacion = tablaObligacion;
	}

	public ObjectListDataProvider getLdpObligacion() {
		return ldpObligacion;
	}

	public void setLdpObligacion(ObjectListDataProvider ldpObligacion) {
		this.ldpObligacion = ldpObligacion;
	}

	public TableRowGroup getTrgObligacion() {
		return trgObligacion;
	}

	public void setTrgObligacion(TableRowGroup trgObligacion) {
		this.trgObligacion = trgObligacion;
	}

	public TableColumn getTcDocumento() {
		return tcDocumento;
	}

	public void setTcDocumento(TableColumn tcDocumento) {
		this.tcDocumento = tcDocumento;
	}

	public StaticText getStDocumento() {
		return stDocumento;
	}

	public void setStDocumento(StaticText stDocumento) {
		this.stDocumento = stDocumento;
	}

	public Label getLblTipoValor() {
		return lblTipoValor;
	}

	public void setLblTipoValor(Label lblTipoValor) {
		this.lblTipoValor = lblTipoValor;
	}

	public Label getLblFijo() {
		return lblFijo;
	}

	public void setLblFijo(Label lblFijo) {
		this.lblFijo = lblFijo;
	}

	public Label getLblPorcentual() {
		return lblPorcentual;
	}

	public void setLblPorcentual(Label lblPorcentual) {
		this.lblPorcentual = lblPorcentual;
	}

	public RadioButton getRbFijo() {
		return rbFijo;
	}

	public void setRbFijo(RadioButton rbFijo) {
		this.rbFijo = rbFijo;
	}

	public RadioButton getRbPorcentual() {
		return rbPorcentual;
	}

	public void setRbPorcentual(RadioButton rbPorcentual) {
		this.rbPorcentual = rbPorcentual;
	}

	public DropDown getDdSeAplica() {
		return ddSeAplica;
	}

	public void setDdSeAplica(DropDown ddSeAplica) {
		this.ddSeAplica = ddSeAplica;
	}

	public SingleSelectOptionsList getDdSeAplicaOptions() {
		return ddSeAplicaOptions;
	}

	public void setDdSeAplicaOptions(SingleSelectOptionsList ddSeAplicaOptions) {
		this.ddSeAplicaOptions = ddSeAplicaOptions;
	}

	public Label getLblSeAplica() {
		return lblSeAplica;
	}

	public void setLblSeAplica(Label lblSeAplica) {
		this.lblSeAplica = lblSeAplica;
	}

	public Label getLblAnios() {
		return lblAnios;
	}

	public void setLblAnios(Label lblAnios) {
		this.lblAnios = lblAnios;
	}

	public Label getLblObligacion() {
		return lblObligacion;
	}

	public void setLblObligacion(Label lblObligacion) {
		this.lblObligacion = lblObligacion;
	}

	private Label lblNombre = new Label();

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label l) {
		this.lblNombre = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label lblCondicionAplicacionExencion = new Label();
	private Label lblCalendarios = new Label();
	private Label lblPeriodos = new Label();
	private Label lblCuotas = new Label();

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

	private Label lblValor = new Label();

	public Label getLblValor() {
		return lblValor;
	}

	public void setLblValor(Label l) {
		this.lblValor = l;
	}

	private TextField tfValor = new TextField();

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tf) {
		this.tfValor = tf;
	}

	private Label lblMotivo = new Label();

	public Label getLblMotivo() {
		return lblMotivo;
	}

	public void setLblMotivo(Label l) {
		this.lblMotivo = l;
	}

	private TextArea taMotivo = new TextArea();

	public TextArea getTaMotivo() {
		return taMotivo;
	}

	public void setTaMotivo(TextArea ta) {
		this.taMotivo = ta;
	}

	private Label lblDigestoMunicipal = new Label();

	public Label getLblDigestoMunicipal() {
		return lblDigestoMunicipal;
	}

	public void setLblDigestoMunicipal(Label l) {
		this.lblDigestoMunicipal = l;
	}

	private TextField tfDigestoMunicipal = new TextField();

	public TextField getTfDigestoMunicipal() {
		return tfDigestoMunicipal;
	}

	public void setTfDigestoMunicipal(TextField tf) {
		this.tfDigestoMunicipal = tf;
	}

	private Button btnSeleccionarDigesto = new Button();

	public Button getBtnSeleccionarDigesto() {
		return btnSeleccionarDigesto;
	}

	public void setBtnSeleccionarDigesto(Button b) {
		this.btnSeleccionarDigesto = b;
	}

	private HtmlAjaxCommandButton btnLimpiarDigesto = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarDigesto() {
		return btnLimpiarDigesto;
	}

	public void setBtnLimpiarDigesto(HtmlAjaxCommandButton btnLimpiarDigesto) {
		this.btnLimpiarDigesto = btnLimpiarDigesto;
	}

	private Table tablaCondicionAplicacionExencion = new Table();
	private TableRowGroup trgCondicionAplicacionExencion = new TableRowGroup();
	private TableColumn tcRbCondicionAplicacionExencion = new TableColumn();

	public String getCurrentRowObligacion() {
		return trgObligacion.getRowKey().getRowId();
	}

	public void setCurrentRowObligacion(int row) {
	}

	private Object lastSelectedObligacion = null;

	public Object getRBSelectedObligacion() {
		String sv = (String) rbObligacion.getSelectedValue();
		return sv.equals(lastSelectedObligacion) ? sv : null;
	}

	public void setRBSelectedObligacion(Object selected) {
		if(selected != null) {
			lastSelectedObligacion = selected;
		}
	}

	public String getCurrentRowCondicionAplicacionNumeroCuota() {
		return trgCondicionAplicacionNumeroCuota.getRowKey().getRowId();
	}

	public void setCurrentRowCondicionAplicacionNumeroCuota(int row) {
	}

	private Object lastSelectedCondicionAplicacionNumeroCuota = null;

	public Object getRBSelectedCondicionAplicacionNumeroCuota() {
		String sv = (String) rbCondicionAplicacionNumeroCuota.getSelectedValue();
		return sv.equals(lastSelectedCondicionAplicacionNumeroCuota) ? sv : null;
	}

	public void setRBSelectedCondicionAplicacionNumeroCuota(Object selected) {
		if(selected != null) {
			lastSelectedCondicionAplicacionNumeroCuota = selected;
		}
	}

	public String getCurrentRowCondicionAplicacionExencion() {
		return trgCondicionAplicacionExencion.getRowKey().getRowId();
	}

	public void setCurrentRowCondicionAplicacionExencion(int row) {
	}

	private Object lastSelectedCondicionAplicacionExencion = null;

	public Object getRBSelectedCondicionAplicacionExencion() {
		String sv = (String) rbCondicionAplicacionExencion.getSelectedValue();
		return sv.equals(lastSelectedCondicionAplicacionExencion) ? sv : null;
	}

	public void setRBSelectedCondicionAplicacionExencion(Object selected) {
		if(selected != null) {
			lastSelectedCondicionAplicacionExencion = selected;
		}
	}

	private ObjectListDataProvider ldpCondicionAplicacionExencion = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCondicionAplicacionExencion() {
		return ldpCondicionAplicacionExencion;
	}

	public void setLdpCondicionAplicacionExencion(ObjectListDataProvider oldp) {
		this.ldpCondicionAplicacionExencion = oldp;
	}

	private RadioButton rbCondicionAplicacionExencion = new RadioButton();
	private TableColumn tcNombre = new TableColumn();

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	private StaticText stNombre = new StaticText();

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public StaticText getStNombre() {
		return stNombre;
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

	private StaticText stMonto = new StaticText();

	public void setStMonto(StaticText stMonto) {
		this.stMonto = stMonto;
	}

	public StaticText getStMonto() {
		return stMonto;
	}

	private StaticText stMontoExento = new StaticText();

	public void setStMontoExento(StaticText stMontoExento) {
		this.stMontoExento = stMontoExento;
	}

	public StaticText getStMontoExento() {
		return stMontoExento;
	}

	private StaticText stFechaVencimiento = new StaticText();

	public void setStFechaVencimiento(StaticText stFechaVencimiento) {
		this.stFechaVencimiento = stFechaVencimiento;
	}

	public StaticText getStFechaVencimiento() {
		return stFechaVencimiento;
	}

	private StaticText stReferenciaNotaHCD = new StaticText();

	public void setStReferenciaNotaHCD(StaticText stReferenciaNotaHCD) {
		this.stReferenciaNotaHCD = stReferenciaNotaHCD;
	}

	public StaticText getStReferenciaNotaHCD() {
		return stReferenciaNotaHCD;
	}

	private PanelGroup pgCondicionAplicacionExencion = new PanelGroup();
	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup pg) {
		this.gpBotones = pg;
	}

	private SingleSelectOptionsList ddPeriodicidadDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdPeriodicidadDefaultOptions() {
		return ddPeriodicidadDefaultOptions;
	}

	public void setDdPeriodicidadDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddPeriodicidadDefaultOptions = ssol;
	}

	private Button btnSeleccionarObligacionOSP = new Button();

	public Button getBtnSeleccionarObligacionOSP() {
		return btnSeleccionarObligacionOSP;
	}

	public void setBtnSeleccionarObligacionOSP(Button b) {
		this.btnSeleccionarObligacionOSP = b;
	}

	private Button btnSeleccionarObligacionTGI = new Button();

	public Button getBtnSeleccionarObligacionTGI() {
		return btnSeleccionarObligacionTGI;
	}

	public void setBtnSeleccionarObligacionTGI(Button b) {
		this.btnSeleccionarObligacionTGI = b;
	}

	private Button btnSeleccionarObligacionPFO = new Button();

	public Button getBtnSeleccionarObligacionPFO() {
		return btnSeleccionarObligacionPFO;
	}

	public void setBtnSeleccionarObligacionPFO(Button b) {
		this.btnSeleccionarObligacionPFO = b;
	}

	private Button btnSeleccionarObligacionSHPS = new Button();

	public Button getBtnSeleccionarObligacionSHPS() {
		return btnSeleccionarObligacionSHPS;
	}

	public void setBtnSeleccionarObligacionSHPS(Button b) {
		this.btnSeleccionarObligacionSHPS = b;
	}

	private Button btnSeleccionarObligacionAutomotor = new Button();
	private Button btnSeleccionarObligacionCementerio = new Button();

	public Button getBtnSeleccionarObligacionAutomotor() {
		return btnSeleccionarObligacionAutomotor;
	}

	public void setBtnSeleccionarObligacionAutomotor(Button btnSeleccionarObligacionAutomotor) {
		this.btnSeleccionarObligacionAutomotor = btnSeleccionarObligacionAutomotor;
	}

	public Button getBtnSeleccionarObligacionCementerio() {
		return btnSeleccionarObligacionCementerio;
	}

	public void setBtnSeleccionarObligacionCementerio(Button btnSeleccionarObligacionCementerio) {
		this.btnSeleccionarObligacionCementerio = btnSeleccionarObligacionCementerio;
	}

	private HtmlAjaxCommandButton btnQuitarCondicionAplicacionExencion = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnAgregarCondicionAplicacionExencion = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarObligacion = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnAgregarCondicionAplicacionNumeroCuota = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarCondicionAplicacionNumeroCuota = new HtmlAjaxCommandButton();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stSeparador3 = new StaticText();

	public Label getLblCondicionAplicacionExencion() {
		return lblCondicionAplicacionExencion;
	}

	public void setLblCondicionAplicacionExencion(Label lblCondicionAplicacionExencion) {
		this.lblCondicionAplicacionExencion = lblCondicionAplicacionExencion;
	}

	public Table getTablaCondicionAplicacionExencion() {
		return tablaCondicionAplicacionExencion;
	}

	public void setTablaCondicionAplicacionExencion(Table tablaCondicionAplicacionExencion) {
		this.tablaCondicionAplicacionExencion = tablaCondicionAplicacionExencion;
	}

	public TableRowGroup getTrgCondicionAplicacionExencion() {
		return trgCondicionAplicacionExencion;
	}

	public void setTrgCondicionAplicacionExencion(TableRowGroup trgCondicionAplicacionExencion) {
		this.trgCondicionAplicacionExencion = trgCondicionAplicacionExencion;
	}

	public TableColumn getTcRbCondicionAplicacionExencion() {
		return tcRbCondicionAplicacionExencion;
	}

	public void setTcRbCondicionAplicacionExencion(TableColumn tcRbCondicionAplicacionExencion) {
		this.tcRbCondicionAplicacionExencion = tcRbCondicionAplicacionExencion;
	}

	public RadioButton getRbCondicionAplicacionExencion() {
		return rbCondicionAplicacionExencion;
	}

	public void setRbCondicionAplicacionExencion(RadioButton rbCondicionAplicacionExencion) {
		this.rbCondicionAplicacionExencion = rbCondicionAplicacionExencion;
	}

	public PanelGroup getPgCondicionAplicacionExencion() {
		return pgCondicionAplicacionExencion;
	}

	public void setPgCondicionAplicacionExencion(PanelGroup pgCondicionAplicacionExencion) {
		this.pgCondicionAplicacionExencion = pgCondicionAplicacionExencion;
	}

	public HtmlAjaxCommandButton getBtnQuitarCondicionAplicacionExencion() {
		return btnQuitarCondicionAplicacionExencion;
	}

	public void setBtnQuitarCondicionAplicacionExencion(HtmlAjaxCommandButton btnQuitarCondicionAplicacionExencion) {
		this.btnQuitarCondicionAplicacionExencion = btnQuitarCondicionAplicacionExencion;
	}

	public HtmlAjaxCommandButton getBtnAgregarCondicionAplicacionExencion() {
		return btnAgregarCondicionAplicacionExencion;
	}

	public void setBtnAgregarCondicionAplicacionExencion(HtmlAjaxCommandButton btnAgregarCondicionAplicacionExencion) {
		this.btnAgregarCondicionAplicacionExencion = btnAgregarCondicionAplicacionExencion;
	}

	public HtmlAjaxCommandButton getBtnAgregarCondicionAplicacionNumeroCuota() {
		return btnAgregarCondicionAplicacionNumeroCuota;
	}

	public void setBtnAgregarCondicionAplicacionNumeroCuota(HtmlAjaxCommandButton btnAgregarCondicionAplicacionNumeroCuota) {
		this.btnAgregarCondicionAplicacionNumeroCuota = btnAgregarCondicionAplicacionNumeroCuota;
	}

	public HtmlAjaxCommandButton getBtnQuitarCondicionAplicacionNumeroCuota() {
		return btnQuitarCondicionAplicacionNumeroCuota;
	}

	public void setBtnQuitarCondicionAplicacionNumeroCuota(HtmlAjaxCommandButton btnQuitarCondicionAplicacionNumeroCuota) {
		this.btnQuitarCondicionAplicacionNumeroCuota = btnQuitarCondicionAplicacionNumeroCuota;
	}

	public StaticText getStSeparador3() {
		return stSeparador3;
	}

	public void setStSeparador3(StaticText stSeparador3) {
		this.stSeparador3 = stSeparador3;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador) {
		this.stSeparador2 = stSeparador;
	}

	public HtmlAjaxCommandButton getBtnQuitarObligacion() {
		return btnQuitarObligacion;
	}

	public void setBtnQuitarObligacion(HtmlAjaxCommandButton btnQuitarObligacion) {
		this.btnQuitarObligacion = btnQuitarObligacion;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private final int CANTIDAD_OBJETOS = 2;
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Agregar Exencion";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AgregarExencionObligacion";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;

	public ABMExencionObligacion() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new ExencionObligacion());
		ep.getObjetos().add(ind++, new DigestoMunicipal());
		ep.getObjetos().add(ind++, null); // Calendario
		ep.getObjetos().add(ind++, null); // Periodo de los calendarios
		ep.getObjetos().add(ind++, null); // Cuotas

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		ExencionObligacion locExencionObligacion = (ExencionObligacion) this.obtenerObjetoDelElementoPila(ind++, ExencionObligacion.class);
		DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);

		CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
		PeriodoLiquidacion periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
		CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);

		Object fijo = this.getRbFijo().getValue();
		Object siempre = this.getDdSeAplica().getSelected();
		Object nombre = this.getTfNombre().getText();
		Object valor = this.tfValor.getText();
		Object motivo = this.taMotivo.getText();

		if(nombre != null && nombre != "")
			locExencionObligacion.setNombre(nombre.toString());
		else
			locExencionObligacion.setNombre(null);
		if(valor != null && valor != "")
			locExencionObligacion.setValor(new Double(valor.toString()));
		else
			locExencionObligacion.setValor(null);
		if(motivo != null && motivo != "")
			locExencionObligacion.setMotivo(motivo.toString());
		else
			locExencionObligacion.setMotivo(null);

		if(fijo != null && fijo != "")
			locExencionObligacion.setFijo(((Boolean) fijo).booleanValue());

		if(digestoMunicipal.getIdDigestoMunicipal() == -1)
			digestoMunicipal = null;
		locExencionObligacion.setDigestoMunicipal(digestoMunicipal);

		if(siempre != null && siempre != "")
			locExencionObligacion.setSiempre(new Boolean(siempre.toString()));

		locExencionObligacion.setListaCondicionAplicacion(this.getLdpCondicionAplicacionExencion().getList());
		this.setListaDelCommunication(locExencionObligacion.getListaCondicionAplicacion());

		locExencionObligacion.setListaObligacion(this.getLdpObligacion().getList());
		this.setListaDelCommunicationObligacion(locExencionObligacion.getListaObligacion());

		this.getLdpCondicionAplicacionNumeroCuota().commitChanges();
		locExencionObligacion.setListaCondicionAplicacionNumeroCuota(this.getLdpCondicionAplicacionNumeroCuota().getList());
		this.setListaDelCommunicationCondicionAplicacionNumeroCuota(locExencionObligacion.getListaCondicionAplicacionNumeroCuota());

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locExencionObligacion);
		this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);

		this.getElementoPila().getObjetos().set(ind++, calendario);
		this.getElementoPila().getObjetos().set(ind++, periodoCalendario);
		this.getElementoPila().getObjetos().set(ind++, cuota);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {

		ExencionObligacion locExencionObligacion = null;
		DigestoMunicipal digesto = null;

		CalendarioMunicipal calendario = null;
		PeriodoLiquidacion periodoCalendario = null;
		CuotaLiquidacion cuota = null;

		int ind = 0;
		locExencionObligacion = (ExencionObligacion) this.obtenerObjetoDelElementoPila(ind++, ExencionObligacion.class);
		digesto = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);

		calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++, CalendarioMunicipal.class);
		periodoCalendario = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++, PeriodoLiquidacion.class);
		cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++, CuotaLiquidacion.class);

		this.getRbFijo().setValue(new Boolean(locExencionObligacion.isFijo()));
		this.getRbPorcentual().setValue(new Boolean(!locExencionObligacion.isFijo()));
		this.tfNombre.setText(locExencionObligacion.getNombre());
		this.tfValor.setText(locExencionObligacion.getValor());
		this.taMotivo.setText(locExencionObligacion.getMotivo());
		this.getDdSeAplica().setSelected(locExencionObligacion.isSiempre() ? "true" : "false");

		if(digesto.getIdDigestoMunicipal() == -1)
			digesto = null;
		if(digesto != null)
			this.getTfDigestoMunicipal().setText(digesto.toString());
		else
			this.getTfDigestoMunicipal().setText(null);

		if(calendario != null && calendario.getIdCalendario() != -1) {
			this.getDdCalendarios().setSelected(calendario.toString());
		}
		if(periodoCalendario != null && periodoCalendario.getIdPeriodo() != -1) {
			this.getDdPeriodos().setSelected(periodoCalendario.toString());
		}

		this.getObjectListDataProvider().setList(locExencionObligacion.getListaCondicionAplicacion());
		this.setListaDelCommunication(locExencionObligacion.getListaCondicionAplicacion());

		this.getObjectListDataProviderObligacion().setList(locExencionObligacion.getListaObligacion());
		this.setListaDelCommunicationObligacion(locExencionObligacion.getListaObligacion());

		this.getObjectListDataProviderCondicionAplicacionNumeroCuota().setList(locExencionObligacion.getListaCondicionAplicacionNumeroCuota());
		this.setListaDelCommunicationCondicionAplicacionNumeroCuota(locExencionObligacion.getListaCondicionAplicacionNumeroCuota());
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCondicionAplicacionExencion();
	}

	private List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaCondicionAplicacionExencion();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaCondicionAplicacionExencion(lista);
	}

	private ObjectListDataProvider getObjectListDataProviderCondicionAplicacionNumeroCuota() {
		return this.getLdpCondicionAplicacionNumeroCuota();
	}

	private List getListaDelCommunicationCondicionAplicacionNumeroCuota() {
		return this.getCommunicationSAICBean().getListaCondicionAplicacionNumeroCuota();
	}

	private void setListaDelCommunicationCondicionAplicacionNumeroCuota(List lista) {
		this.getCommunicationSAICBean().setListaCondicionAplicacionNumeroCuota(lista);
	}

	private ObjectListDataProvider getObjectListDataProviderObligacion() {
		return this.getLdpObligacion();
	}

	private List getListaDelCommunicationObligacion() {
		return this.getCommunicationSAICBean().getListaObligacionExencion();
	}

	private void setListaDelCommunicationObligacion(List lista) {
		this.getCommunicationSAICBean().setListaObligacionExencion(lista);
	}

	public void cambiarEstadoComponentes(boolean deshabilitar) {
		this.ddPeriodos.setDisabled(deshabilitar);
		this.ddCalendarios.setDisabled(deshabilitar);
		this.ddCuotas.setDisabled(deshabilitar);
	}

	public String btnLimpiarDigesto_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(1, tfDigestoMunicipal);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarDigesto_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminDigestoMunicipal";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarObligacionOSP_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminDocEspOSP";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarObligacionTGI_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminDocEspTGI";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarObligacionPFO_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminDocEspPFO";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarObligacionSHPS_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminDocEspSHPS";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarObligacionAutomotor_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminDocEspAutomotor";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarObligacionCementerio_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminDocEspCementerio";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpCondicionAplicacionExencion().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionadoObligacion() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupObligacion");
			rk = this.getLdpObligacion().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionadoCondicionAplicacionNumeroCuota() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupCondicionAplicacionNumeroCuota");
			rk = this.getLdpCondicionAplicacionNumeroCuota().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public void btnAgregarCondicionAplicacionExencion_action() {
		this.guardarEstadoObjetosUsados();
		ExencionObligacion locExencionObligacion = (ExencionObligacion) this.obtenerObjetoDelElementoPila(0, ExencionObligacion.class);

		Integer anioSeleccionado = this.getDDObjectValue(this.getDdAnios(), this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalExencionObligacion());
		if(anioSeleccionado != null) {
			CalendarioMunicipal calendarioSeleccionado = this.getDDObjectValue(this.getDdCalendarios(),
					this.getCommunicationSAICBean().getMapaCalendariosExencionObligacion(anioSeleccionado.toString()));
			if(calendarioSeleccionado != null) {
				PeriodoLiquidacion periodoSeleccionado = this.getDDObjectValue(this.getDdPeriodos(), this.getCommunicationSAICBean()
						.getMapaPeriodosCalendarioMunicipalExencionObligacion(calendarioSeleccionado.getNombre()));
				if(periodoSeleccionado != null) {
					CuotaLiquidacion cuotaSeleccionada = this.getDDObjectValue(this.getDdCuotas(),
							this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalExencionObligacion(periodoSeleccionado.toString()));
					if(cuotaSeleccionada != null) {
						for(CondicionAplicacionExencion cadaCondicion : locExencionObligacion.getListaCondicionAplicacion()) {
							if(cadaCondicion instanceof CondicionAplicacionExencionCuota && ((CondicionAplicacionExencionCuota) cadaCondicion).getCuota().equals(cuotaSeleccionada)) {
								this.getDdAnios().setSelected("");
								return;
							}
						}
						CondicionAplicacionExencionCuota nuevaCondicionCuota = new CondicionAplicacionExencionCuota();
						nuevaCondicionCuota.setCuota(cuotaSeleccionada);
						nuevaCondicionCuota.setExencionObligacion(locExencionObligacion);
						this.getListaDelCommunication().add(nuevaCondicionCuota);
					} else {
						for(CondicionAplicacionExencion cadaCondicion : locExencionObligacion.getListaCondicionAplicacion()) {
							if(cadaCondicion instanceof CondicionAplicacionExencionPeriodo
									&& ((CondicionAplicacionExencionPeriodo) cadaCondicion).getPeriodo().equals(periodoSeleccionado)) {
								this.getDdAnios().setSelected("");
								return;
							}
						}

						CondicionAplicacionExencionPeriodo nuevaCondicionPeriodo = new CondicionAplicacionExencionPeriodo();
						nuevaCondicionPeriodo.setPeriodo(periodoSeleccionado);
						nuevaCondicionPeriodo.setExencionObligacion(locExencionObligacion);
						this.getListaDelCommunication().add(nuevaCondicionPeriodo);
					}
				} else {
					for(CondicionAplicacionExencion cadaCondicion : locExencionObligacion.getListaCondicionAplicacion()) {
						if(cadaCondicion instanceof CondicionAplicacionExencionCalendario
								&& ((CondicionAplicacionExencionCalendario) cadaCondicion).getCalendario().equals(calendarioSeleccionado)) {
							this.getDdAnios().setSelected("");
							return;
						}
					}

					CondicionAplicacionExencionCalendario nuevaCondicionCalendario = new CondicionAplicacionExencionCalendario();
					nuevaCondicionCalendario.setCalendario(calendarioSeleccionado);
					nuevaCondicionCalendario.setExencionObligacion(locExencionObligacion);
					this.getListaDelCommunication().add(nuevaCondicionCalendario);
				}
			} else {
				for(CondicionAplicacionExencion cadaCondicion : locExencionObligacion.getListaCondicionAplicacion()) {
					if(cadaCondicion instanceof CondicionAplicacionExencionAnio && ((CondicionAplicacionExencionAnio) cadaCondicion).getAnio().equals(anioSeleccionado)) {
						this.getDdAnios().setSelected("");
						return;
					}
				}

				CondicionAplicacionExencionAnio nuevaCondicionAnio = new CondicionAplicacionExencionAnio();
				nuevaCondicionAnio.setAnio(anioSeleccionado);
				nuevaCondicionAnio.setExencionObligacion(locExencionObligacion);
				this.getListaDelCommunication().add(nuevaCondicionAnio);
			}
		}

		this.getDdAnios().setSelected("");
	}

	public String btnQuitarCondicionAplicacionExencion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					ExencionObligacion locExencion = (ExencionObligacion) this.obtenerObjetoDelElementoPila(0, ExencionObligacion.class);
					locExencion.getListaCondicionAplicacion().remove(obj);
					this.getElementoPila().getObjetos().set(0, locExencion);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarObligacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderObligacion().getObjects()[index];
					ExencionObligacion locExencion = (ExencionObligacion) this.obtenerObjetoDelElementoPila(0, ExencionObligacion.class);
					locExencion.getListaObligacion().remove(obj);
					this.getElementoPila().getObjetos().set(0, locExencion);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarCondicionAplicacionNumeroCuota_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionadoCondicionAplicacionNumeroCuota();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderCondicionAplicacionNumeroCuota().getObjects()[index];
					ExencionObligacion locExencion = (ExencionObligacion) this.obtenerObjetoDelElementoPila(0, ExencionObligacion.class);
					locExencion.getListaCondicionAplicacionNumeroCuota().remove(obj);
					this.getElementoPila().getObjetos().set(0, locExencion);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void btnAgregarCondicionAplicacionNumeroCuota_action() {
		this.guardarEstadoObjetosUsados();

		ExencionObligacion locExencionObligacion = (ExencionObligacion) this.obtenerObjetoDelElementoPila(0, ExencionObligacion.class);

		CondicionAplicacionExencionNumeroCuota locNuevaCondicion = new CondicionAplicacionExencionNumeroCuota();
		locNuevaCondicion.setExencionObligacion(locExencionObligacion);

		this.getLdpCondicionAplicacionNumeroCuota().getList().add(locNuevaCondicion);
		this.getLdpCondicionAplicacionNumeroCuota().setObjectType(CondicionAplicacionExencionNumeroCuota.class);
		// this.getListaDelCommunicationCondicionAplicacionNumeroCuota().add(locNuevaExencion);
	}

	// private void refrescarTabla() throws Exception{
	//
	// ExencionObligacion locExencionObligacion= (ExencionObligacion) this.obtenerObjetoDelElementoPila(0, ExencionObligacion.class);
	// ArrayList listaObligaciones= new ArrayList();
	// listaObligaciones.addAll(locExencionObligacion.getListaRegistrosExencion());
	// try{
	// this.getCommunicationSAICBean().getRemoteSystemExencion().setLlave(this.getSessionBean1().getLlave());
	// this.setListaDelCommunication(listaObligaciones);
	// this.getObjectListDataProvider().setList(this.getListaDelCommunication());
	//
	// }catch(Exception ex){
	// this.getObjectListDataProvider().setList(null);
	// }
	// this.setRBSelected((new Long(0)).toString());
	// }

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
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosExencionObligacion(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalExencionObligacion(pCalendario)
				.keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalExencionObligacion(pPeriodo).keySet());
		seleccionarCuota(opcion);
	}

	private String llenarDD(SingleSelectOptionsList ddOpciones, Set<String> llaves) {
		Option[] opciones;
		String opcion = "";
		if(llaves == null || llaves.isEmpty()) {
			opciones = new Option[0];
		} else {
			opciones = new Option[llaves.size() + 1];
			opciones[0] = new Option("");
			int i = 1;
			for(String cadaLlave : llaves) {
				opciones[i++] = new Option(cadaLlave);
			}
		}
		ddOpciones.setOptions(opciones);
		return opcion;
	}

	public void valueChangeEventDdSeAplica(ActionEvent event) {
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		ExencionObligacion locExencionObligacion = (ExencionObligacion) this.obtenerObjetoDelElementoPila(0, ExencionObligacion.class);

		if(pObject instanceof Obligacion) {
			Obligacion locObligacion = (Obligacion) pObject;
			TipoObligacion locTipoObligacion = null;
			try {
				locTipoObligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getTipoObligacionFromObligacion(locObligacion);

				if(!locExencionObligacion.getListaObligacion().contains(locObligacion)) {
					locExencionObligacion.getListaObligacion().add(locObligacion);

					this.getCommunicationSAICBean().setTipoObligacionSeleccionada(locTipoObligacion.getNombre());
					this.getCommunicationSAICBean().setMapaAniosCalendarioMunicipalExencionObligacion(null);
					llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalExencionObligacion().keySet());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(pObject instanceof DigestoMunicipal) {
			DigestoMunicipal digesto = (DigestoMunicipal) pObject;
			this.getElementoPila().getObjetos().set(1, digesto);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ExencionObligacion locExencion = (ExencionObligacion) pObject;

		if(!locExencion.getListaObligacion().isEmpty()) {
			TipoObligacion locTipoObligacion = null;
			try {
				locTipoObligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getTipoObligacionFromObligacion(locExencion.getListaObligacion().get(0));
			} catch(Exception e) {
				e.printStackTrace();
			}

			this.getCommunicationSAICBean().setTipoObligacionSeleccionada(locTipoObligacion.getNombre());
			this.getCommunicationSAICBean().setMapaAniosCalendarioMunicipalExencionObligacion(null);
			llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalExencionObligacion().keySet());

			this.getObjectListDataProvider().setList(locExencion.getListaCondicionAplicacion());
			this.setListaDelCommunication(locExencion.getListaCondicionAplicacion());

			this.getObjectListDataProviderObligacion().setList(locExencion.getListaObligacion());
			this.setListaDelCommunicationObligacion(locExencion.getListaObligacion());

			this.getObjectListDataProviderCondicionAplicacionNumeroCuota().setList(locExencion.getListaCondicionAplicacionNumeroCuota());
			this.setListaDelCommunicationCondicionAplicacionNumeroCuota(locExencion.getListaCondicionAplicacionNumeroCuota());
		}

		this.getObjectListDataProvider().setObjectType(CondicionAplicacionExencion.class);
		this.getObjectListDataProviderCondicionAplicacionNumeroCuota().setObjectType(CondicionAplicacionExencionNumeroCuota.class);

		this.getElementoPila().getObjetos().set(0, locExencion);
		this.getElementoPila().getObjetos().set(1, locExencion.getDigestoMunicipal());
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMExencionObligacion";
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ABMExencionObligacion$ABMExencionObligacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return ExencionObligacion.serialVersionUID;
	}
}