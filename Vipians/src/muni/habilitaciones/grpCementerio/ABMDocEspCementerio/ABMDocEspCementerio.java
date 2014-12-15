
package muni.habilitaciones.grpCementerio.ABMDocEspCementerio;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

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
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.Tasa;

public class ABMDocEspCementerio extends ABMPageBean {

	private TextField tfPersona = new TextField();
	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarPersonaJuridica = new Button();
	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	private Button btnSeleccionarDomicilioPostal = new Button();
	private Button btnSeleccionarDomicilioSolicitante = new Button();
	private Button btnLimpiarDomicilioPostal = new Button();
	private Label lblPersona = new Label();
	private Label lblParcelasCementerio = new Label();
	private Label lblDomicilio = new Label();
	private MessageGroup messageGroup1 = new MessageGroup();
	private ObjectListDataProvider ldpParcelasCementerio = new ObjectListDataProvider();
	private RadioButton radioButton1 = new RadioButton();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private Table tablaParcelasCementerio = new Table();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcConcesion = new TableColumn();
	private TableColumn tcSuperficie = new TableColumn();
	private TableColumn tcTipoSepultura = new TableColumn();
	private StaticText stConcesion = new StaticText();
	private StaticText stSuperficie = new StaticText();
	private StaticText stTipoSepultura = new StaticText();
	private StaticText staticText1 = new StaticText();
	private PanelGroup groupPanel1 = new PanelGroup();
	private Button btnAgregarParcelaCementerio = new Button();
	private HtmlAjaxCommandButton btnQuitarParcelaCementerio = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarTodosParcelaCementerio = new HtmlAjaxCommandButton();
	private StaticText stDomicilioPostal = new StaticText();
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

	public TableColumn getTcTipoSepultura() {
		return tcTipoSepultura;
	}

	public void setTcTipoSepultura(TableColumn tcTipoSepultura) {
		this.tcTipoSepultura = tcTipoSepultura;
	}

	public StaticText getStTipoSepultura() {
		return stTipoSepultura;
	}

	public void setStTipoSepultura(StaticText stTipoSepultura) {
		this.stTipoSepultura = stTipoSepultura;
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

	public Button getBtnLimpiarDomicilioPostal() {
		return btnLimpiarDomicilioPostal;
	}

	public void setBtnLimpiarDomicilioPostal(Button btnLimpiarDomicilioPostal) {
		this.btnLimpiarDomicilioPostal = btnLimpiarDomicilioPostal;
	}

	public Label getLblDomicilio() {
		return lblDomicilio;
	}

	public void setLblDomicilio(Label lblDomicilio) {
		this.lblDomicilio = lblDomicilio;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public Button getBtnAgregarParcelaCementerio() {
		return btnAgregarParcelaCementerio;
	}

	public void setBtnAgregarParcelaCementerio(Button btnAgregarParcelaCementerio) {
		this.btnAgregarParcelaCementerio = btnAgregarParcelaCementerio;
	}

	public HtmlAjaxCommandButton getBtnQuitarParcelaCementerio() {
		return btnQuitarParcelaCementerio;
	}

	public void setBtnQuitarParcelaCementerio(HtmlAjaxCommandButton btnQuitarParcelaCementerio) {
		this.btnQuitarParcelaCementerio = btnQuitarParcelaCementerio;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosParcelaCementerio() {
		return btnQuitarTodosParcelaCementerio;
	}

	public void setBtnQuitarTodosParcelaCementerio(HtmlAjaxCommandButton btnQuitarTodosParcelaCementerio) {
		this.btnQuitarTodosParcelaCementerio = btnQuitarTodosParcelaCementerio;
	}

	public TableColumn getTcSuperficie() {
		return tcSuperficie;
	}

	public void setTcSuperficie(TableColumn tcSuperficie) {
		this.tcSuperficie = tcSuperficie;
	}

	public TableColumn getTcConcesion() {
		return tcConcesion;
	}

	public void setTcConcesion(TableColumn tcConcesion) {
		this.tcConcesion = tcConcesion;
	}

	public StaticText getStConcesion() {
		return stConcesion;
	}

	public void setStConcesion(StaticText stConcesion) {
		this.stConcesion = stConcesion;
	}

	public StaticText getStSuperficie() {
		return stSuperficie;
	}

	public void setStSuperficie(StaticText stSuperficie) {
		this.stSuperficie = stSuperficie;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public Table getTablaParcelasCementerio() {
		return tablaParcelasCementerio;
	}

	public void setTablaParcelasCementerio(Table tablaParcelasCementerio) {
		this.tablaParcelasCementerio = tablaParcelasCementerio;
	}

	public Label getLblParcelasCementerio() {
		return lblParcelasCementerio;
	}

	public void setLblParcelasCementerio(Label lblParcelasCementerio) {
		this.lblParcelasCementerio = lblParcelasCementerio;
	}

	public ObjectListDataProvider getLdpParcelasCementerio() {
		return ldpParcelasCementerio;
	}

	public void setLdpParcelasCementerio(ObjectListDataProvider ldpParcelasCementerio) {
		this.ldpParcelasCementerio = ldpParcelasCementerio;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
		this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
	}

	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
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

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton b) {
		this.btnLimpiarPersona = b;
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

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpParcelasCementerio().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaParcelasCementerio();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaParcelasCementerio(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpParcelasCementerio();
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMDocEspCementerio() {
	}

	@Override
	protected void _init() {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
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
		ep.getObjetos().add(ind++, new DocumentoCementerio());
		ep.getObjetos().add(ind++, null); // Persona
		ep.getObjetos().add(ind++, new ArrayList<ParcelaCementerio>()); // Lista ParcelaCementerio
		ep.getObjetos().add(ind++, new ArrayList()); // Atributos Dinamicos
		ep.getObjetos().add(ind++, null); // Domicilio
		ep.getObjetos().add(ind++, null); // FiltroLogLiquidacion

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
		DocumentoCementerio documentoCementerio = this.obtenerObjetoDelElementoPila(ind++, DocumentoCementerio.class);
		Persona persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		List listaParcelasCementerio = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList atributosDinamicos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Domicilio domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(ind++, FiltroLogLiquidacion.class);

		obligacion.setDocumentoEspecializado(documentoCementerio);
		obligacion.setPersona(persona);
		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		documentoCementerio.setListaAtributosDinamicos(atributosDinamicos);
		documentoCementerio.setListaAsocRegAlicuota(listaParcelasCementerio);

		if(domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		documentoCementerio.setDomicilio(domicilio);

		documentoCementerio.setListaAsocRegAlicuota(this.getObjectListDataProvider().getList());
		this.setListaDelCommunication(documentoCementerio.getListaAsocRegAlicuota());

		filtroLogLiq.setObligacion(obligacion);
		filtroLogLiq.setUsuario(getTextFieldValue(this.getTfFiltrarUsuarioLogLiq()));
		filtroLogLiq.setEvento(getDDEnumValue(this.getDdFiltrarEventoLogLiq(), LogLiquidacion.Evento.class));
		filtroLogLiq.setFechaDesde(getTextFieldValueDate(this.getTfFiltrarFechaDesdeLogLiq()));
		filtroLogLiq.setFechaHasta(getTextFieldValueDate(this.getTfFiltrarFechaHastaLogLiq()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, obligacion);
		this.getElementoPila().getObjetos().set(ind++, documentoCementerio);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, listaParcelasCementerio);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, filtroLogLiq);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Obligacion obligacion = null;
		DocumentoCementerio documentoCementerio = null;
		Persona persona = null;
		List listaParcelasCementerio = this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		ArrayList atributosDinamicos = null;
		Domicilio domicilio = null;

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();

			if(respuesta instanceof Domicilio) {
				this.getElementoPila().getObjetos().set(5, respuesta);
			}
		}

		try {
			DocumentoCementerio locDocumento = (DocumentoCementerio) this.obtenerObjetoDelElementoPila(1);
			this.getElementoPila()
					.getObjetos()
					.set(4,
							this.getComunicationBean().getRemoteSystemParametro()
									.getAtributosPorRecurso(DocumentoCementerio.serialVersionUID, locDocumento.getListaAtributosDinamicos(), true));
		} catch(Exception e) {
			e.printStackTrace();
		}

		ind = 0;
		ind++;
		documentoCementerio = this.obtenerObjetoDelElementoPila(ind++, DocumentoCementerio.class);
		persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		listaParcelasCementerio = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		atributosDinamicos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		FiltroLogLiquidacion filtroLogLiq = this.obtenerObjetoDelElementoPila(ind++, FiltroLogLiquidacion.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);

		if(persona != null) {
			this.getTfPersona().setText(persona.toString());
		}
		if(domicilio != null) {
			this.getStDomicilioPostal().setText(domicilio.toString());
		}

		this.getObjectListDataProvider().setList(listaParcelasCementerio);
		this.setListaDelCommunication(listaParcelasCementerio);

		this.getTfFiltrarUsuarioLogLiq().setText(filtroLogLiq.getUsuario());
		this.getDdFiltrarEventoLogLiq().setSelected(filtroLogLiq.getEvento());
		this.getTfFiltrarFechaDesdeLogLiq().setText(filtroLogLiq.getFechaDesde());
		this.getTfFiltrarFechaHastaLogLiq().setText(filtroLogLiq.getFechaHasta());
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosCementerio(lista);
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {

			this.limpiarObjeto(2, this.getTfPersona());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarParcelaCementerio_action() {
		return navegarParaSeleccionar("AdminParcelaCementerio");
	}

	public String btnQuitarParcelaCementerio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		List<ParcelaCementerio> listaParcelaCementerio = this.getListaDelCommunication();
		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getLdpParcelasCementerio().getObjects()[index];
					ParcelaCementerio parcela = (ParcelaCementerio) obj;
					parcela.setDocHabilitanteEspecializado(null);

					this.getListaDelCommunication().remove(parcela);
					this.getObjectListDataProvider().setList(this.getListaDelCommunication());

					this.getElementoPila().getObjetos().set(3, listaParcelaCementerio);
				}
			} catch(Exception ex) {
				log(this.getCasoNavegacion() + "_EliminarDifuntoError:", ex);
				error(this.getNombrePagina() + " - Eliminar Difunto: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarTodosDifunto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {
				this.getListaDelCommunication().clear();
				this.getObjectListDataProvider().setList(this.getListaDelCommunication());
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarDomicilioPostal_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		int posicionObjetoSeleccionado = 5;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilio = this.obtenerObjetoDelElementoPila(5, Domicilio.class);
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

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Object pers = this.getElementoPila().getObjetos().get(2);
			Persona persona = null;
			if(pers != null) {
				persona = (Persona) pers;
			}
			if(persona != null && persona.getIdPersona() != -1) {
				Domicilio domicilio = persona.getDomicilioPostal();
				this.getElementoPila().getObjetos().set(posicionObjetoSeleccionado, domicilio);
			} else {
				warn("Seleccione una Persona Solicitante para obtener su Domicilio.");
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDocEspCementerio";
	}

	public void btnFiltrarLogLiq_action() {
		this.guardarEstadoObjetosUsados();
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				FiltroLogLiquidacion locFiltro = (FiltroLogLiquidacion) this.obtenerObjetoDelElementoPila(6);

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
		this.getElementoPila().getObjetos().set(6, null);

		this.getTfFiltrarUsuarioLogLiq().setText("");
		this.getDdFiltrarEventoLogLiq().setSelected("");
		this.getTfFiltrarFechaDesdeLogLiq().setText("");
		this.getTfFiltrarFechaHastaLogLiq().setText("");

		this.getCommunicationSAICBean().setListaLogLiquidacion(new ArrayList<LogLiquidacion>());
		this.getLdpLogsLiquidacion().setList(this.getCommunicationSAICBean().getListaLogLiquidacion());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Obligacion obligacion = this.obtenerObjetoDelElementoPila(0, Obligacion.class);
		DocumentoCementerio documentoCementerio = this.obtenerObjetoDelElementoPila(1, DocumentoCementerio.class);
		Persona persona = null;
		List listaParcelasCementerio = this.obtenerObjetoDelElementoPila(3, ArrayList.class);

		if(pObject instanceof Persona) {
			persona = (Persona) pObject;

			try {
				if(persona instanceof PersonaFisica) {
					this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
					persona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(persona.getIdPersona());

				} else if(persona instanceof PersonaJuridica) {
					this.getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(this.getSessionBean1().getLlave());
					persona = this.getComunicationBean().getRemoteSystemPersonaJuridica().getPersonaJuridicaPorId(persona.getIdPersona());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}

			obligacion.setPersona(persona);
			this.getElementoPila().getObjetos().set(2, persona);

		}

		if(pObject instanceof ParcelaCementerio) {
			ParcelaCementerio locParcelaCem = (ParcelaCementerio) pObject;

			boolean yaEsta = false;
			for(Object cadaObject : listaParcelasCementerio) {
				ParcelaCementerio cadaParcela = (ParcelaCementerio) cadaObject;
				if(cadaParcela.getIdAsocRegAlicuota() == locParcelaCem.getIdAsocRegAlicuota()) {
					yaEsta = true;
					break;
				}
			}

			if(!yaEsta) {
				locParcelaCem.setDocHabilitanteEspecializado(documentoCementerio);
				listaParcelasCementerio.add(locParcelaCem);
				this.getElementoPila().getObjetos().set(3, listaParcelasCementerio);
			} else {
				warn("La Parcela seleccionada ya se encuentra en la lista");
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		int ind = 0;
		Obligacion obligacion = null;
		DocumentoCementerio documentoCementerio = null;
		Persona persona = null;
		List listaParcelasCementerio = this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		ArrayList atributosDinamicos = null;
		Domicilio domicilio = null;

		if(pObject instanceof PlantillaObligacion) {
			PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;

			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().generarArbol(plantillaObligacion);
			} catch(Exception ex) {
				log(this.getCasoNavegacion() + "_GenerarObligacion:", ex);
				error(this.getNombrePagina() + " - No se pudo generar la Obligaci\363n: " + ex.getMessage());
			}

			documentoCementerio = (DocumentoCementerio) obligacion.getDocumentoEspecializado();
			persona = this.obtenerObjetoDelElementoPila(2, Persona.class);

			this.setListaDelCommunicationDocsGeneradoresDeuda(new ArrayList());

			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoCementerio);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, listaParcelasCementerio);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
			this.getElementoPila().getObjetos().set(ind++, domicilio);
		} else if(pObject instanceof Obligacion) {
			obligacion = (Obligacion) pObject;
			long id = obligacion.getIdObligacion();
			try {
				this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
				obligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(id);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			documentoCementerio = (DocumentoCementerio) obligacion.getDocumentoEspecializado();
			try {
				documentoCementerio = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().getDocumentoCementerio(obligacion);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			persona = documentoCementerio.getObligacion().getPersona();
			listaParcelasCementerio = documentoCementerio.getListaAsocRegAlicuota();
			domicilio = documentoCementerio.getDomicilio();
			if(documentoCementerio.getListaAtributosDinamicos() != null) {
				try {
					atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
							.getAtributosPorRecurso(DocumentoCementerio.serialVersionUID, documentoCementerio.getListaAtributosDinamicos(), null);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}

			try {
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				this.setListaDelCommunicationDocsGeneradoresDeuda(this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaDocsGeneradoresDeuda(obligacion));
			} catch(Exception e) {
				e.printStackTrace();
			}

			ind = 0;
			this.getElementoPila().getObjetos().set(ind++, obligacion);
			this.getElementoPila().getObjetos().set(ind++, documentoCementerio);
			this.getElementoPila().getObjetos().set(ind++, persona);
			this.getElementoPila().getObjetos().set(ind++, listaParcelasCementerio);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
			this.getElementoPila().getObjetos().set(ind++, domicilio);
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

		DocumentoCementerio locDocumento = this.obtenerObjetoDelElementoPila(1, DocumentoCementerio.class);
		this.getTablaLogs().getLdpLogs().setList(locDocumento.getListaLogsAuditoria());

		if(this.getListaDelCommunicationDocsGeneradoresDeuda() != null) {
			this.getLdpDocsGeneradoresDeuda().setList(this.getListaDelCommunicationDocsGeneradoresDeuda());
		}
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoCementerio.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio}";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Persona persona = null;

		try {
			persona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(2, persona);
	}

	public boolean isHayPersona() {
		Persona locPersona = (Persona) this.obtenerObjetoDelElementoPila(2);
		return(locPersona != null && locPersona.getIdPersona() != -1);
	}
}
