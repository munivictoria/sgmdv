
package muni.habilitaciones.grpAutomotor.ABMDocEspAutomotor;

import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import muni.habilitaciones.grpAutomotor.ABMVehiculo.VehiculoModel;

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
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.LogLiquidacion;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMDocEspAutomotor extends ABMPageBean {

	private MessageGroup messageGroup1 = new MessageGroup();
	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarPersonaJuridica = new Button();
	private Button btnSeleccionarVehiculo = new Button();
	private Button btnConsultarAutomotor = new Button();
	private HtmlAjaxCommandButton btnLimpiarVehiculo = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	private Button btnSeleccionarDomicilioPostal = new Button();
	private HtmlAjaxCommandButton btnLimpiarDomicilioPostal = new HtmlAjaxCommandButton();
	private Button btnSeleccionarDomicilioSolicitante = new Button();
	private TextField tfVehiculo = new TextField();
	private Label lblVehiculo = new Label();
	private Label lblDomicilio = new Label();
	private StaticText stDomicilioPostal = new StaticText();

	private Table tablaDocsGeneradoresDeuda = new Table();
	private ObjectListDataProvider ldpDocsGeneradoresDeuda = new ObjectListDataProvider();
	private StaticText stDocsGeneradoresDeuda = new StaticText();
	private TableRowGroup trgDocsGeneradoresDeuda = new TableRowGroup();
	private TableColumn tcAnio = new TableColumn();
	private TableColumn tcPlan = new TableColumn();
	private StaticText stAnio = new StaticText();
	private StaticText stPlan = new StaticText();

	private Table tablaPropietarios = new Table();
	private TableRowGroup trgPropietarios = new TableRowGroup();
	private ObjectListDataProvider ldpPropietarios = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcPersona = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private StaticText stPersona = new StaticText();

	private Label lblNroCuenta = new Label();
	private TextField tfNumeroCuenta = new TextField();

	private Table tablaLogsLiquidacion = new Table();
	private TableRowGroup tableRowGroup = new TableRowGroup();
	private ObjectListDataProvider ldpLogsLiquidacion = new ObjectListDataProvider();

	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();

	private StaticText staticText2 = new StaticText();
	private StaticText staticText3 = new StaticText();
	private StaticText staticText4 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText7 = new StaticText();

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	private NumberConverter numberConverter1 = new NumberConverter();

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

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tableColumn6) {
		this.tableColumn6 = tableColumn6;
	}
	
	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText staticText3) {
		this.staticText3 = staticText3;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText staticText7) {
		this.staticText7 = staticText7;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
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

	public Label getLblNroCuenta() {
		return lblNroCuenta;
	}

	public void setLblNroCuenta(Label lblNroCuenta) {
		this.lblNroCuenta = lblNroCuenta;
	}

	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tfNumeroCuenta) {
		this.tfNumeroCuenta = tfNumeroCuenta;
	}

	public Table getTablaPropietarios() {
		return tablaPropietarios;
	}

	public void setTablaPropietarios(Table tablaPropietarios) {
		this.tablaPropietarios = tablaPropietarios;
	}

	public TableRowGroup getTrgPropietarios() {
		return trgPropietarios;
	}

	public void setTrgPropietarios(TableRowGroup trgPropietarios) {
		this.trgPropietarios = trgPropietarios;
	}

	public ObjectListDataProvider getLdpPropietarios() {
		return ldpPropietarios;
	}

	public void setLdpPropietarios(ObjectListDataProvider ldpPropietarios) {
		this.ldpPropietarios = ldpPropietarios;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTcPersona() {
		return tcPersona;
	}

	public void setTcPersona(TableColumn tcPersona) {
		this.tcPersona = tcPersona;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText stPersona) {
		this.stPersona = stPersona;
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

	public HtmlAjaxCommandButton getBtnLimpiarVehiculo() {
		return btnLimpiarVehiculo;
	}

	public void setBtnLimpiarVehiculo(HtmlAjaxCommandButton btnLimpiarVehiculo) {
		this.btnLimpiarVehiculo = btnLimpiarVehiculo;
	}

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public HtmlAjaxCommandButton getBtnLimpiarDomicilioPostal() {
		return btnLimpiarDomicilioPostal;
	}

	public void setBtnLimpiarDomicilioPostal(HtmlAjaxCommandButton btnLimpiarDomicilioPostal) {
		this.btnLimpiarDomicilioPostal = btnLimpiarDomicilioPostal;
	}

	public Button getBtnSeleccionarDomicilioSolicitante() {
		return btnSeleccionarDomicilioSolicitante;
	}

	public void setBtnSeleccionarDomicilioSolicitante(Button btnSeleccionarDomicilioSolicitante) {
		this.btnSeleccionarDomicilioSolicitante = btnSeleccionarDomicilioSolicitante;
	}

	public StaticText getStDomicilioPostal() {
		return stDomicilioPostal;
	}

	public void setStDomicilioPostal(StaticText stDomicilioPostal) {
		this.stDomicilioPostal = stDomicilioPostal;
	}

	public Button getBtnSeleccionarDomicilioPostal() {
		return btnSeleccionarDomicilioPostal;
	}

	public void setBtnSeleccionarDomicilioPostal(Button btnSeleccionarDomicilioPostal) {
		this.btnSeleccionarDomicilioPostal = btnSeleccionarDomicilioPostal;
	}

	public Label getLblDomicilio() {
		return lblDomicilio;
	}

	public void setLblDomicilio(Label lblDomicilio) {
		this.lblDomicilio = lblDomicilio;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
		this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
	}

	public Label getLblVehiculo() {
		return lblVehiculo;
	}

	public void setLblVehiculo(Label lblVehiculo) {
		this.lblVehiculo = lblVehiculo;
	}

	public Button getBtnSeleccionarVehiculo() {
		return btnSeleccionarVehiculo;
	}

	public void setBtnSeleccionarVehiculo(Button btnSeleccionarVehiculo) {
		this.btnSeleccionarVehiculo = btnSeleccionarVehiculo;
	}

	public TextField getTfVehiculo() {
		return tfVehiculo;
	}

	public void setTfVehiculo(TextField tfVehiculo) {
		this.tfVehiculo = tfVehiculo;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	public String getCurrentRow2() {
		return trgPropietarios.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	public Button getBtnConsultarAutomotor() {
		return btnConsultarAutomotor;
	}

	public void setBtnConsultarAutomotor(Button btnConsultarAutomotor) {
		this.btnConsultarAutomotor = btnConsultarAutomotor;
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

	private void setListaDelCommunicationPropietarios(List lista) {
		this.getCommunicationHabilitacionesBean().setListaPropietariosVehiculo(lista);
	}

	private List getListaDelCommunicationPropietarios() {
		return this.getCommunicationHabilitacionesBean().getListaPropietariosVehiculo();
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpPropietarios().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMDocEspAutomotor() {
	}

	@Override
	protected void _init() {
		if(this.getListaDelCommunicationPropietarios() != null) {
			this.getLdpPropietarios().setList(this.getListaDelCommunicationPropietarios());
		}
		if(this.getCommunicationSAICBean().getListaLogLiquidacion() != null) {
			this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
		}

		Option[] opTipoEvento = null;
		opTipoEvento = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(LogLiquidacion.Evento.values(), "cap");
		ddFiltrarEventoLogLiqOptions.setOptions(opTipoEvento);

		dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		dateTimeConverter1.setPattern("dd/MM/yyyy HH:mm");
		dateTimeConverter1.setTimeStyle("full");
		numberConverter1.setPattern("$ #,##0.00");
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		ep.getObjetos().add(ind++, null); // Obligacion
		ep.getObjetos().add(ind++, new DocumentoAutomotor());
		ep.getObjetos().add(ind++, null); // Persona
		ep.getObjetos().add(ind++, null); // Vehiculo
		ep.getObjetos().add(ind++, null); // Atributos Dinamicos
		ep.getObjetos().add(ind++, null); // Domicilio
		ep.getObjetos().add(ind++, null); // nroCuenta
		ep.getObjetos().add(ind++, null); // 7 FiltroLogLiquidacion

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
		Obligacion obligacion = this.obtenerObjetoDelElementoPila(ind++, Obligacion.class);
		DocumentoAutomotor documentoAutomotor = this.obtenerObjetoDelElementoPila(ind++, DocumentoAutomotor.class);
		Persona persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		Vehiculo vehiculo = this.obtenerObjetoDelElementoPila(ind++, Vehiculo.class);
		ArrayList atributosDinamicos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Domicilio domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(7, FiltroLogLiquidacion.class);
		ArrayList listaPropietarios = new ArrayList();

		obligacion.setDocumentoEspecializado(documentoAutomotor);
		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		documentoAutomotor.setListaAtributosDinamicos(atributosDinamicos);
		documentoAutomotor.setVehiculo(vehiculo);

		if(domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		documentoAutomotor.setDomicilio(domicilio);

		if(vehiculo != null && vehiculo.getIdVehiculo() != -1) {

			if(vehiculo.getTituloPropiedad() != null) {
				for(RegistroPropietario rp : vehiculo.getTituloPropiedad().getListaRegistrosPropietarios()) {
					listaPropietarios.add(rp.getPersona());
				}
			}
			this.getLdpPropietarios().setList(listaPropietarios);
			this.setListaDelCommunicationPropietarios(listaPropietarios);
		}

		RowKey rk = null;
		try {
			rk = this.getSeleccionado();
			if(rk != null) {
				int index = getNroFila(rk.toString());
				Object obj = this.getLdpPropietarios().getObjects()[index];
				persona = (Persona) obj;
				// domicilio =
				// (Domicilio)locRegistroPropietario.getPersona().getDomicilioPostal();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			// int posicionEP = -1;
			if(respuesta instanceof Domicilio) {
				int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
				this.getElementoPila().getObjetos().set(posicion, respuesta);
			}
		}

		obligacion.setPersona(persona);

		this.getLdpPropietarios().setList(listaPropietarios);
		this.getLdpPropietarios().setObjectType(Persona.class);
		this.setListaDelCommunicationPropietarios(listaPropietarios);

		Integer numeroCuenta = getTextFieldValueInteger(this.getTfNumeroCuenta());

		documentoAutomotor.setNumero(numeroCuenta);

		filtroLogLiq.setObligacion(obligacion);
		filtroLogLiq.setUsuario(getTextFieldValue(this.getTfFiltrarUsuarioLogLiq()));
		filtroLogLiq.setEvento(getDDEnumValue(this.getDdFiltrarEventoLogLiq(), LogLiquidacion.Evento.class));
		filtroLogLiq.setFechaDesde(getTextFieldValueDate(this.getTfFiltrarFechaDesdeLogLiq()));
		filtroLogLiq.setFechaHasta(getTextFieldValueDate(this.getTfFiltrarFechaHastaLogLiq()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, obligacion);
		this.getElementoPila().getObjetos().set(ind++, documentoAutomotor);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, vehiculo);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(7, filtroLogLiq);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Obligacion obligacion = null;
		DocumentoAutomotor documentoAutomotor = null;
		Persona persona = null;
		Vehiculo vehiculo = null;
		ArrayList atributosDinamicos = null;
		Domicilio domicilio = null;
		ArrayList listaPropietarios = new ArrayList();

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();

			if(respuesta instanceof Domicilio) {
				this.getElementoPila().getObjetos().set(5, respuesta);
			}
		}

		try {
			DocumentoAutomotor locDocumento = (DocumentoAutomotor) this.obtenerObjetoDelElementoPila(1);
			this.getElementoPila()
					.getObjetos()
					.set(4,
							this.getComunicationBean().getRemoteSystemParametro()
									.getAtributosPorRecurso(DocumentoAutomotor.serialVersionUID, locDocumento.getListaAtributosDinamicos(), true));
		} catch(Exception e) {
			e.printStackTrace();
		}

		ind = 0;
		ind++;
		documentoAutomotor = this.obtenerObjetoDelElementoPila(ind++, DocumentoAutomotor.class);
		persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		vehiculo = this.obtenerObjetoDelElementoPila(ind++, Vehiculo.class);
		atributosDinamicos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(7, FiltroLogLiquidacion.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);

		if(persona != null) {
			this.seleccionarPersonaObligacion(persona);
		}

		if(vehiculo != null && vehiculo.getIdVehiculo() != -1) {
			this.getTfVehiculo().setText(vehiculo.toString());

			if(vehiculo.getTituloPropiedad() != null) {
				for(RegistroPropietario rp : vehiculo.getTituloPropiedad().getListaRegistrosPropietarios()) {
					listaPropietarios.add(rp.getPersona());
				}
			}
			this.getLdpPropietarios().setList(listaPropietarios);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunicationPropietarios(listaPropietarios);
		} else {
			this.getLdpPropietarios().setList(null);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunicationPropietarios(null);
		}

		if(domicilio != null) {
			this.getStDomicilioPostal().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
		}

		if(documentoAutomotor.getNumero() != null) {
			this.getTfNumeroCuenta().setText(documentoAutomotor.getNumero());
		}

		this.getTfFiltrarUsuarioLogLiq().setText(filtroLogLiq.getUsuario());
		this.getDdFiltrarEventoLogLiq().setSelected(filtroLogLiq.getEvento());
		this.getTfFiltrarFechaDesdeLogLiq().setText(filtroLogLiq.getFechaDesde());
		this.getTfFiltrarFechaHastaLogLiq().setText(filtroLogLiq.getFechaHasta());
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosAutomotor(lista);
	}

	private void setListaDelCommunicationDocsGeneradoresDeuda(List pLista) {
		this.getCommunicationHabilitacionesBean().setListaDocsGeneradoresDeuda(pLista);
	}

	private List getListaDelCommunicationDocsGeneradoresDeuda() {
		return this.getCommunicationHabilitacionesBean().getListaDocsGeneradoresDeuda();
	}

	public void seleccionarPersonaObligacion(Persona persona) {
		Integer cantidadPersonas = this.getLdpPropietarios().getList().size();

		Persona locPersona;
		for(int i = 0; i < cantidadPersonas; i++) {
			locPersona = (Persona) this.getLdpPropietarios().getObjects()[i];
			if(locPersona.getIdPersona() == persona.getIdPersona()) {
				lastSelected = new Long(i).toString();
			}
		}
	}

	public String btnSeleccionarVehiculo_action() {
		return navegarParaSeleccionar("AdminVehiculo");
	}

	public String btnLimpiarVehiculo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(3, this.getTfVehiculo());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarDomicilioPostal_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 5;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Persona locPersona = this.obtenerObjetoDelElementoPila(2, Persona.class);
			Vehiculo locVehiculo = this.obtenerObjetoDelElementoPila(3, Vehiculo.class);
			Domicilio locDomicilio = this.obtenerObjetoDelElementoPila(posicionObjetoSeleccionado, Domicilio.class);

			if((locDomicilio.getIdDomicilio() != -1)
					|| ((locDomicilio.getIdDomicilio() != -1 && locPersona.getDomicilioPostal().getIdDomicilio() != -1) && locDomicilio.getIdDomicilio() == locPersona
							.getDomicilioPostal().getIdDomicilio())) {
				retorno = "AgregarDomicilio";
			} else {
				this.getRequestBean1().setObjetoABM(locDomicilio);
				retorno = "ModificarDomicilio";
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
			this.limpiarObjeto(5, this.getStDomicilioPostal());
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
		int posicionObjetoSeleccionado = 5;

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Persona persona = this.obtenerObjetoDelElementoPila(2, Persona.class);
			Domicilio domicilio = null;
			if(persona != null && persona.getDomicilioPostal() != null) {
				domicilio = persona.getDomicilioPostal();
			}

			this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDocEspAutomotor";
	}

	public void btnFiltrarLogLiq_action() {
		this.guardarEstadoObjetosUsados();
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				FiltroLogLiquidacion locFiltro = (FiltroLogLiquidacion) this.obtenerObjetoDelElementoPila(7);

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
		this.getElementoPila().getObjetos().set(7, null);

		this.getTfFiltrarUsuarioLogLiq().setText("");
		this.getDdFiltrarEventoLogLiq().setSelected("");
		this.getTfFiltrarFechaDesdeLogLiq().setText("");
		this.getTfFiltrarFechaHastaLogLiq().setText("");

		this.getCommunicationSAICBean().setListaLogLiquidacion(new ArrayList<LogLiquidacion>());
		this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Vehiculo vehiculo = null;

		if(pObject instanceof Vehiculo) {
			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(this.getSessionBean1().getLlave());
				vehiculo = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getVehiculoPorId(((Vehiculo) pObject).getIdVehiculo());
			} catch(Exception e) {
				e.printStackTrace();
			}
			this.getElementoPila().getObjetos().set(3, vehiculo);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Obligacion obligacion = null;
		DocumentoAutomotor documentoAutomotor = null;
		Persona persona = null;
		Vehiculo vehiculo = null;
		Domicilio domicilio = null;
		Integer numeroCuenta = null;

		if(pObject instanceof PlantillaObligacion) {
			PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().generarArbol(plantillaObligacion);
			} catch(Exception ex) {
				log(this.getCasoNavegacion() + "_GenerarObligacion:", ex);
				error(this.getNombrePagina() + " - No se pudo generar la Obligaci\363n: " + ex.getMessage());
			}

			documentoAutomotor = (DocumentoAutomotor) obligacion.getDocumentoEspecializado();
			persona = this.obtenerObjetoDelElementoPila(2, Persona.class);

			this.setListaDelCommunicationDocsGeneradoresDeuda(new ArrayList());

			int ind = 0;
			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoAutomotor);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, vehiculo);
			this.getElementoPila().getObjetos().set(5, domicilio);
			this.getElementoPila().getObjetos().set(6, numeroCuenta);

		} else if(pObject instanceof Obligacion) {
			obligacion = (Obligacion) this.getRequestBean1().getObjetoABM();
			long id = obligacion.getIdObligacion();
			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(id);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			documentoAutomotor = (DocumentoAutomotor) obligacion.getDocumentoEspecializado();
			try {
				documentoAutomotor = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getDocumentoAutomotor(obligacion);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			try {
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				this.setListaDelCommunicationDocsGeneradoresDeuda(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaDocsGeneradoresDeuda(obligacion));
			} catch(Exception e) {
				e.printStackTrace();
			}

			persona = documentoAutomotor.getObligacion().getPersona();

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(this.getSessionBean1().getLlave());
				vehiculo = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getVehiculoPorId(documentoAutomotor.getVehiculo().getIdVehiculo());
			} catch(Exception e) {
				e.printStackTrace();
			}

			domicilio = documentoAutomotor.getDomicilio();
			if(documentoAutomotor.getListaAtributosDinamicos() != null) {
				try {
					List atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro()
							.getAtributosPorRecurso(DocumentoAutomotor.serialVersionUID, documentoAutomotor.getListaAtributosDinamicos(), null);
					this.getElementoPila().getObjetos().set(4, atributosDinamicos);
					documentoAutomotor.setListaAtributosDinamicos(atributosDinamicos);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}

			numeroCuenta = documentoAutomotor.getNumero();

			ArrayList listaPropietarios = new ArrayList();
			for(RegistroPropietario rp : vehiculo.getTituloPropiedad().getListaRegistrosPropietarios()) {
				listaPropietarios.add(rp.getPersona());
			}

			this.getLdpPropietarios().setList(listaPropietarios);
			this.getLdpPropietarios().setObjectType(Persona.class);
			this.setListaDelCommunicationPropietarios(listaPropietarios);

			int ind = 0;
			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoAutomotor);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, vehiculo);
			this.getElementoPila().getObjetos().set(5, domicilio);
			this.getElementoPila().getObjetos().set(6, numeroCuenta);
		}
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		DocumentoAutomotor locDocumento = this.obtenerObjetoDelElementoPila(1, DocumentoAutomotor.class);
		this.getTablaLogs().getLdpLogs().setList(locDocumento.getListaLogsAuditoria());

		if(this.getListaDelCommunicationDocsGeneradoresDeuda() != null) {
			this.getLdpDocsGeneradoresDeuda().setList(this.getListaDelCommunicationDocsGeneradoresDeuda());
		}

		FiltroLogLiquidacion filtroLogLiq = (FiltroLogLiquidacion) this.obtenerObjetoDelElementoPila(7);
		if(filtroLogLiq != null) {
			this.getTfFiltrarUsuarioLogLiq().setText(filtroLogLiq.getUsuario());
			this.getDdFiltrarEventoLogLiq().setSelected(filtroLogLiq.getEvento());
			this.getTfFiltrarFechaDesdeLogLiq().setText(filtroLogLiq.getFechaDesde());
			this.getTfFiltrarFechaHastaLogLiq().setText(filtroLogLiq.getFechaHasta());
		}
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoAutomotor.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor}";
	}

	public String btnConsultarAutomotor_action() {
		String ret = null;
		Vehiculo vehiculo = null;

		this.guardarEstadoObjetosUsados();
		vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(3);

		if((vehiculo != null) && (vehiculo.getIdVehiculo() != -1)) {
			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(this.getSessionBean1().getLlave());
				vehiculo = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getVehiculoPorId(vehiculo.getIdVehiculo());
			} catch(Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.getRequestBean1().setObjetoABM(vehiculo);
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new VehiculoModel().new ConsultarController());
			ret = "ABMVehiculo";
		}
		return ret;
	}
}
