/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMExpediente;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import muni.expedientes.AbmNodoExpediente;
import muni.expedientes.NodoExpedienteControler;
import muni.expedientes.ABMExpediente.ABMTramite.TramiteModel;
import muni.expedientes.panels.PanelFases;
import muni.expedientes.panels.PanelPlazoExpediente;
import muni.expedientes.tables.TableTramite;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.PasswordField;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Tab;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.enums.Estado;
import com.trascender.expedientes.recurso.persistent.AreaResponsable;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Expediente.AccionFase;
import com.trascender.expedientes.recurso.persistent.Fase;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.expedientes.recurso.persistent.UsuarioResponsable;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;

public class ABMExpediente extends AbmNodoExpediente {

	private TextArea taAsunto = new TextArea();
	private Label lblAsunto = new Label();
	private TextField tfNroRegistro = new TextField();
	private Label lblNroRegistro = new Label();
	private Label lblFechaRegistro = new Label();
	private TextField tfFechaRegistro = new TextField();

	private TextField tfPersona = new TextField();
	private Label lblPersona = new Label();
	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarPersonaJuridica = new Button();
	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	private Button btnAgregarExtensionFase = new Button();
	private HtmlAjaxCommandButton btnAgregarExtensionTramite = new HtmlAjaxCommandButton();

	private Label lblResponsable = new Label();
	private Label labelFases = new Label();

	private Label lblProcedimiento = new Label();
	private Label lblEstadoProcedimiento = new Label();
	private PanelGroup gpBotones = new PanelGroup();
	private HtmlAjaxCommandButton btnSeleccionarProcedimiento = new HtmlAjaxCommandButton();

	private PanelPlazoExpediente panelPlazoExpediente = new PanelPlazoExpediente();
	private PanelFases panelFases = new PanelFases();

	private TextArea taComentario = new TextArea();
	private HtmlAjaxCommandButton btnComentarioIrAFaseEspecial = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnComentarioRetroceso = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnComentarioCancelarAvance = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnComentarioCierre = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnCancelarComentario = new HtmlAjaxCommandButton();

	private Table tablaHitos = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpHitos = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();

	private Table tableDocPresentada = new Table();
	private TableRowGroup trgDocPresentada = new TableRowGroup();
	private ObjectListDataProvider ldpDocPresentada = new ObjectListDataProvider();

	private Label lblNroTelefono = new Label();
	private Label lblNroCelular = new Label();
	private Label lblEmail = new Label();
	private TextField tfNroTelefono = new TextField();
	private TextField tfNroCelular = new TextField();
	private TextField tfEmail = new TextField();

	private HtmlAjaxCommandButton btnPassAvanzarFase = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnCancelarPass = new HtmlAjaxCommandButton();

	private DropDown ddProcedimiento = new DropDown();
	protected SingleSelectOptionsList ddProcedimientoDefaultOptions = new SingleSelectOptionsList();

	private TabSet tabSet1 = new TabSet();
	private Tab tabOne = new Tab();
	private Tab tabTwo = new Tab();
	private Tab tabThree = new Tab();

	private PanelGroup pgBotonesFases = new PanelGroup();

	private Label lbContraseniaExpediente = new Label();
	private PasswordField pfContraseniaExpediente = new PasswordField();

	private HtmlAjaxCommandButton btnGuardarAjax = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnGuardarAjax() {
		return btnGuardarAjax;
	}

	public void setBtnGuardarAjax(HtmlAjaxCommandButton btnGuardarAjax) {
		this.btnGuardarAjax = btnGuardarAjax;
	}

	public PanelGroup getPgBotonesFases() {
		return pgBotonesFases;
	}

	public void setPgBotonesFases(PanelGroup pgBotonesFases) {
		this.pgBotonesFases = pgBotonesFases;
	}

	public Label getLbContraseniaExpediente() {
		return lbContraseniaExpediente;
	}

	public void setLbContraseniaExpediente(Label lbContraseniaExpediente) {
		this.lbContraseniaExpediente = lbContraseniaExpediente;
	}

	public PasswordField getPfContraseniaExpediente() {
		return pfContraseniaExpediente;
	}

	public void setPfContraseniaExpediente(PasswordField pfContraseniaExpediente) {
		this.pfContraseniaExpediente = pfContraseniaExpediente;
	}

	public Tab getTabThree() {
		return tabThree;
	}

	public void setTabThree(Tab tabThree) {
		this.tabThree = tabThree;
	}

	public TabSet getTabSet1() {
		return tabSet1;
	}

	public void setTabSet1(TabSet tabSet1) {
		this.tabSet1 = tabSet1;
	}

	public Tab getTabOne() {
		return tabOne;
	}

	public void setTabOne(Tab tabOne) {
		this.tabOne = tabOne;
	}

	public Tab getTabTwo() {
		return tabTwo;
	}

	public void setTabTwo(Tab tabTwo) {
		this.tabTwo = tabTwo;
	}

	public DropDown getDdProcedimiento() {
		return ddProcedimiento;
	}

	public void setDdProcedimiento(DropDown ddProcedimiento) {
		this.ddProcedimiento = ddProcedimiento;
	}

	public SingleSelectOptionsList getDdProcedimientoDefaultOptions() {
		return ddProcedimientoDefaultOptions;
	}

	public void setDdProcedimientoDefaultOptions(SingleSelectOptionsList ddProcedimientoDefaultOptions) {
		this.ddProcedimientoDefaultOptions = ddProcedimientoDefaultOptions;
	}

	public Table getTableDocPresentada() {
		return tableDocPresentada;
	}

	public void setTableDocPresentada(Table tableDocPresentada) {
		this.tableDocPresentada = tableDocPresentada;
	}

	public TableRowGroup getTrgDocPresentada() {
		return trgDocPresentada;
	}

	public void setTrgDocPresentada(TableRowGroup trgDocPresentada) {
		this.trgDocPresentada = trgDocPresentada;
	}

	public ObjectListDataProvider getLdpDocPresentada() {
		return ldpDocPresentada;
	}

	public void setLdpDocPresentada(ObjectListDataProvider ldpDocPresentada) {
		this.ldpDocPresentada = ldpDocPresentada;
	}

	public HtmlAjaxCommandButton getBtnPassAvanzarFase() {
		return btnPassAvanzarFase;
	}

	public void setBtnPassAvanzarFase(HtmlAjaxCommandButton btnPassAvanzarFase) {
		this.btnPassAvanzarFase = btnPassAvanzarFase;
	}

	public HtmlAjaxCommandButton getBtnCancelarPass() {
		return btnCancelarPass;
	}

	public void setBtnCancelarPass(HtmlAjaxCommandButton btnCancelarPass) {
		this.btnCancelarPass = btnCancelarPass;
	}

	public Label getLblNroTelefono() {
		return lblNroTelefono;
	}

	public void setLblNroTelefono(Label lblNroTelefono) {
		this.lblNroTelefono = lblNroTelefono;
	}

	public Label getLblNroCelular() {
		return lblNroCelular;
	}

	public void setLblNroCelular(Label lblNroCelular) {
		this.lblNroCelular = lblNroCelular;
	}

	public Label getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(Label lblEmail) {
		this.lblEmail = lblEmail;
	}

	public TextField getTfNroTelefono() {
		return tfNroTelefono;
	}

	public void setTfNroTelefono(TextField tfNroTelefono) {
		this.tfNroTelefono = tfNroTelefono;
	}

	public TextField getTfNroCelular() {
		return tfNroCelular;
	}

	public void setTfNroCelular(TextField tfNroCelular) {
		this.tfNroCelular = tfNroCelular;
	}

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public HtmlAjaxCommandButton getBtnAgregarExtensionTramite() {
		return btnAgregarExtensionTramite;
	}

	public void setBtnAgregarExtensionTramite(HtmlAjaxCommandButton btnAgregarExtensionTramite) {
		this.btnAgregarExtensionTramite = btnAgregarExtensionTramite;
	}

	public Button getBtnAgregarExtensionFase() {
		return btnAgregarExtensionFase;
	}

	public void setBtnAgregarExtensionFase(Button btnAgregarExtensionFase) {
		this.btnAgregarExtensionFase = btnAgregarExtensionFase;
	}

	public Table getTablaHitos() {
		return tablaHitos;
	}

	public void setTablaHitos(Table tablaHitos) {
		this.tablaHitos = tablaHitos;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public ObjectListDataProvider getLdpHitos() {
		return ldpHitos;
	}

	public void setLdpHitos(ObjectListDataProvider ldpHitos) {
		this.ldpHitos = ldpHitos;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	public HtmlAjaxCommandButton getBtnComentarioIrAFaseEspecial() {
		return btnComentarioIrAFaseEspecial;
	}

	public void setBtnComentarioIrAFaseEspecial(HtmlAjaxCommandButton btnComentarioIrAFaseEspecial) {
		this.btnComentarioIrAFaseEspecial = btnComentarioIrAFaseEspecial;
	}

	public HtmlAjaxCommandButton getBtnComentarioCancelarAvance() {
		return btnComentarioCancelarAvance;
	}

	public void setBtnComentarioCancelarAvance(HtmlAjaxCommandButton btnComentarioCancelarAvance) {
		this.btnComentarioCancelarAvance = btnComentarioCancelarAvance;
	}

	public HtmlAjaxCommandButton getBtnComentarioRetroceso() {
		return btnComentarioRetroceso;
	}

	public void setBtnComentarioRetroceso(HtmlAjaxCommandButton btnComentarioRetroceso) {
		this.btnComentarioRetroceso = btnComentarioRetroceso;
	}

	public HtmlAjaxCommandButton getBtnComentarioCierre() {
		return btnComentarioCierre;
	}

	public void setBtnComentarioCierre(HtmlAjaxCommandButton btnComentarioCierre) {
		this.btnComentarioCierre = btnComentarioCierre;
	}

	public HtmlAjaxCommandButton getBtnCancelarComentario() {
		return btnCancelarComentario;
	}

	public void setBtnCancelarComentario(HtmlAjaxCommandButton btnCancelarComentario) {
		this.btnCancelarComentario = btnCancelarComentario;
	}

	public List<AreaResponsable> getListaAreasResponsable() {
		if(this.expediente == null || this.expediente.getNodoProcedimiento() == null || this.expediente.getNodoProcedimiento().getResponsable() == null)
			return null;
		return expediente.getNodoProcedimiento().getResponsable().getListaAreasResponsables();
	}

	public List<UsuarioResponsable> getListaUsuariosResponsable() {
		if(this.expediente == null || this.expediente.getNodoProcedimiento() == null || this.expediente.getNodoProcedimiento().getResponsable() == null)
			return null;
		return expediente.getNodoProcedimiento().getResponsable().getListaUsuariosResponsables();
	}

	public List<AreaResponsable> getListaAreasResponsableFaseActual() {
		if(this.expediente == null || this.expediente.getNodoProcedimiento() == null || this.expediente.getFaseActual().getNodoProcedimiento().getResponsable() == null)
			return null;
		return expediente.getFaseActual().getNodoProcedimiento().getResponsable().getListaAreasResponsables();
	}

	public List<UsuarioResponsable> getListaUsuariosResponsableFaseActual() {
		if(this.expediente == null || this.expediente.getNodoProcedimiento() == null || this.expediente.getFaseActual().getNodoProcedimiento().getResponsable() == null)
			return null;
		return expediente.getFaseActual().getNodoProcedimiento().getResponsable().getListaUsuariosResponsables();
	}

	private StaticText staticText1 = new StaticText();

	public Expediente expediente;

	// // mantiente los tramites modificados hasta que se haga un avance de fase o
	// // se guarde el expediente
	// // util para mantener los tramites entre las navegaciones sin tener que
	// // llamar al metodo getPorId(..);
	// private Map<Integer, Tramite> mapTramitesModificados = new HashMap<Integer, Tramite>();

	private Persona interesado;
	private String comentarioExpediente;
	private String strContraseniaExpediente = new String();

	public PanelFases getPanelFases() {
		return panelFases;
	}

	public void setPanelFases(PanelFases panelFases) {
		this.panelFases = panelFases;
	}

	public PanelPlazoExpediente getPanelPlazoExpediente() {
		return panelPlazoExpediente;
	}

	public void setPanelPlazoExpediente(PanelPlazoExpediente panelPlazoExpediente) {
		this.panelPlazoExpediente = panelPlazoExpediente;
	}

	public Label getLabelFases() {
		return labelFases;
	}

	public void setLabelFases(Label labelFases) {
		this.labelFases = labelFases;
	}

	public Label getLblResponsable() {
		return lblResponsable;
	}

	public void setLblResponsable(Label lblResponsable) {
		this.lblResponsable = lblResponsable;
	}

	public Label getLblProcedimiento() {
		return lblProcedimiento;
	}

	public void setLblProcedimiento(Label lblProcedimiento) {
		this.lblProcedimiento = lblProcedimiento;
	}

	public Label getLblEstadoProcedimiento() {
		return lblEstadoProcedimiento;
	}

	public void setLblEstadoProcedimiento(Label lblEstadoProcedimiento) {
		this.lblEstadoProcedimiento = lblEstadoProcedimiento;
	}

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup gpBotones) {
		this.gpBotones = gpBotones;
	}

	public HtmlAjaxCommandButton getBtnSeleccionarProcedimiento() {
		return btnSeleccionarProcedimiento;
	}

	public void setBtnSeleccionarProcedimiento(HtmlAjaxCommandButton btnSeleccionarProcedimiento) {
		this.btnSeleccionarProcedimiento = btnSeleccionarProcedimiento;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public TextArea getTaAsunto() {
		return taAsunto;
	}

	public void setTaAsunto(TextArea taAsunto) {
		this.taAsunto = taAsunto;
	}

	public Label getLblAsunto() {
		return lblAsunto;
	}

	public void setLblAsunto(Label lblAsunto) {
		this.lblAsunto = lblAsunto;
	}

	public Label getLblNroRegistro() {
		return lblNroRegistro;
	}

	public void setLblNroRegistro(Label lblNroRegistro) {
		this.lblNroRegistro = lblNroRegistro;
	}

	public TextField getTfNroRegistro() {
		return tfNroRegistro;
	}

	public void setTfNroRegistro(TextField tfNroRegistro) {
		this.tfNroRegistro = tfNroRegistro;
	}

	public Label getLblFechaRegistro() {
		return lblFechaRegistro;
	}

	public void setLblFechaRegistro(Label lblFechaRegistro) {
		this.lblFechaRegistro = lblFechaRegistro;
	}

	public TextField getTfFechaRegistro() {
		return tfFechaRegistro;
	}

	public void setTfFechaRegistro(TextField tfFechaRegistro) {
		this.tfFechaRegistro = tfFechaRegistro;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}

	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button btnSeleccionarPersonaFisica) {
		this.btnSeleccionarPersonaFisica = btnSeleccionarPersonaFisica;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
		this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
	}

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	@Override
	protected void _prerender() throws Exception {
		if(expediente == null)
			getElementosPila();

		if(expediente.getEstado().equals(Estado.CERRADO)) {
			this.getLblEstadoProcedimiento().setText("(CERRADO)");
		}

		panelFases.deshabilitarAccionesFases(expediente);
	}

	@Override
	protected void _init() throws Exception {
		panelFases._init();
		super._init();

		if(this.getCommunicationExpedientesBean().getListaDocPresentada() != null) {
			this.getLdpDocPresentada().setList(this.getCommunicationExpedientesBean().getListaDocPresentada());
		}
		if(this.getCommunicationExpedientesBean().getListaHitos() != null) {
			this.getLdpHitos().setList(this.getCommunicationExpedientesBean().getListaHitos());
		}

		Set<String> locListaProcedimiento = this.getCommunicationExpedientesBean().getMapaProcedimientoExpediente().keySet();
		Option[] opProcedimiento = new Option[locListaProcedimiento.size() + 1];
		int i = 0;
		opProcedimiento[i++] = new Option("", "");
		for(String cadaProcedimiento : locListaProcedimiento) {
			opProcedimiento[i++] = new Option(cadaProcedimiento, cadaProcedimiento);
		}
		ddProcedimientoDefaultOptions.setOptions(opProcedimiento);
	}

	@Override
	public void getElementosPila() {
		int ind = 0;
		expediente = obtenerObjetoDelElementoPila(ind++, Expediente.class);
		interesado = obtenerObjetoDelElementoPila(ind++, Persona.class);
		// mapTramitesModificados = obtenerObjetoDelElementoPila(ind++, HashMap.class);
		ind++;
		comentarioExpediente = obtenerObjetoDelElementoPila(ind++, String.class);
		strContraseniaExpediente = obtenerObjetoDelElementoPila(ind++, String.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void setElementosPila() {
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, expediente);
		this.getElementoPila().getObjetos().set(ind++, interesado);
		ind++;
		// this.getElementoPila().getObjetos().set(ind++, mapTramitesModificados);
		this.getElementoPila().getObjetos().set(ind++, comentarioExpediente);
		this.getElementoPila().getObjetos().set(ind++, strContraseniaExpediente);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		getElementosPila();

		Object pass = this.getPfContraseniaExpediente().getText();
		if(pass != null && pass != "") {
			strContraseniaExpediente = pass.toString();
		} else {
			strContraseniaExpediente = null;
		}

		expediente.setAsunto(this.getTextAreaValue(getTaAsunto()));
		expediente.setFechaRegistro(this.getTextFieldValueDate(getTfFechaRegistro()));
		try {
			expediente.setNroRegistro(Long.parseLong(this.getTextFieldValue(getTfNroRegistro())));
		} catch(Exception e) {
		}

		if(interesado != null) {
			interesado.setTelefono(this.getTextFieldValue(getTfNroTelefono()));
			interesado.setCelular(this.getTextFieldValue(getTfNroCelular()));
			interesado.setEmail(this.getTextFieldValue(getTfEmail()));

			expediente.setInteresado(interesado);
		} else {
			limpiarYDeshabilitarDatosDeContacto();
		}

		setElementosPila();
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		getElementosPila();
		Date fechaRegistro = expediente.getFechaRegistro() != null ? expediente.getFechaRegistro() : new Date();
		this.getTaAsunto().setText(expediente.getAsunto());
		this.getTfNroRegistro().setText(expediente.getNroRegistro());

		this.getTfFechaRegistro().setText(Conversor.getStringDeFechaCorta(fechaRegistro));

		if(interesado != null) {
			this.getTfPersona().setText(interesado);

			this.getTfNroTelefono().setText(interesado.getTelefono());
			this.getTfNroCelular().setText(interesado.getCelular());
			this.getTfEmail().setText(interesado.getEmail());
		} else {
			limpiarYDeshabilitarDatosDeContacto();
		}

		this.panelFases.mostrarDatos(expediente);

		this.mostrartHitos(expediente);
		this.getLdpHitos().setList(expediente.listarHitos());

		this.getLdpDocPresentada().setList(expediente.listarDocumentacionPresentada());
		this.getCommunicationExpedientesBean().setListaDocPresentada(expediente.listarDocumentacionPresentada());

		panelPlazoExpediente.mostrarDatos(expediente.getPlazo());

		this.lblProcedimiento.setText(expediente.getNodoProcedimiento() != null ? ((Procedimiento) expediente.getNodoProcedimiento()).getNombre() : "");
		this.ddProcedimiento.setSelected(this.lblProcedimiento.getText());
	}
	
	public void eventoActualizarDatosInteresado(ValueChangeEvent event) {
		expediente = obtenerObjetoDelElementoPila(0, Expediente.class);
		interesado = obtenerObjetoDelElementoPila(1, Persona.class);

		if(interesado != null) {
			interesado.setTelefono(this.getTextFieldValue(getTfNroTelefono()));
			interesado.setCelular(this.getTextFieldValue(getTfNroCelular()));
			interesado.setEmail(this.getTextFieldValue(getTfEmail()));

			expediente.setInteresado(interesado);
		} else {
			limpiarYDeshabilitarDatosDeContacto();
		}

		this.getElementoPila().getObjetos().set(0, expediente);
		this.getElementoPila().getObjetos().set(1, interesado);
	}

	private void limpiarYDeshabilitarDatosDeContacto() {
		this.getTfNroTelefono().setText("");
		this.getTfNroTelefono().setDisabled(true);
		this.getTfNroCelular().setText("");
		this.getTfNroCelular().setDisabled(true);
		this.getTfEmail().setText("");
		this.getTfEmail().setDisabled(true);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Expediente());
		ep.getObjetos().add(ind++, null);
		ep.getObjetos().add(ind++, new HashMap<Integer, Tramite>());
		ep.getObjetos().add(ind++, new String());
		ep.getObjetos().add(ind++, strContraseniaExpediente);

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		getElementosPila();

		if(pObject instanceof Persona) {
			Persona locInteresado = (Persona) pObject;
			try {
				interesado = locInteresado;
				expediente.setInteresado(interesado);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(pObject instanceof TableTramite.WTramite) {
			TableTramite.WTramite wt = (TableTramite.WTramite) pObject;
			this.panelFases.actualizarTramite(expediente, wt);
		}

		this.getRequestBean1().setObjetoSeleccion(null);
		setElementosPila();
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		expediente = (Expediente) pObject;
		interesado = expediente.getInteresado();

		setElementosPila();
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMExpediente";
	}

	public void btnSeleccionarProcedimiento_action() {
		getElementosPila();
		Procedimiento procedimiento = this.getDDObjectValue(ddProcedimiento, getCommunicationExpedientesBean().getMapaProcedimientoExpediente());
		this.setProcedimientoExpediente(procedimiento);

		setElementosPila();
		mostrarEstadoObjetosUsados();
	}

	public void setProcedimientoExpediente(Procedimiento procedimiento) {
		try {
			if(procedimiento != null) {
				procedimiento = this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getProcedimientoPorId(procedimiento.getIdNodoProcedimiento());
				expediente.setProcedimiento(procedimiento, getSessionBean1().getUsuario());
			} else {
				expediente = new Expediente();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
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
			this.getTfPersona().setText("");
			Expediente locExpediente = (Expediente) this.obtenerObjetoDelElementoPila(0);
			locExpediente.setInteresado(null);

			this.getElementoPila().getObjetos().set(0, locExpediente);
			this.getElementoPila().getObjetos().set(1, null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String getFase(ActionEvent event) {
		return null;
	}

	public void deshabilitarComponentesPanelFases() {
		panelFases.getLbFasesEspeciales().setRendered(false);
		panelFases.getDdFasesEspeciales().setRendered(false);
		panelFases.getBtnIrAFaseEspecial().setRendered(false);
		panelFases.getStaticText1().setRendered(false);
		panelFases.getBtnAvanzarFase().setRendered(false);
		panelFases.getBtnRetrocederFase().setRendered(false);
		panelFases.getBtnCerrarExpediente().setRendered(false);
	}

	private DropDown ddFaseEspecial = new DropDown();

	public String btnIrAFaseEspecial_action() {
		try {
			getElementosPila();
			System.out.println(this.getDdFaseEspecial().getSelected());
			comentarioExpediente = (String) this.getTaComentario().getText();
			panelFases.irAFaseEspecial(expediente, this.getSessionBean1().getUsuario(), comentarioExpediente);
			// mapTramitesModificados.clear();
			setElementosPila();
			this.deshabilitarComponentesPanelFases();
		} catch(Exception e) {
			e.printStackTrace();
			warn(e.getMessage());
		}

		return null;
	}

	public String btnAvanzarFase_action() {
		try {
			getElementosPila();
			List<TramiteCatalogo> locTramitesPorTrabajar = getListaTramiteCatalogoPorTrabajar();
			if(!locTramitesPorTrabajar.isEmpty()) {
				for(TramiteCatalogo cadaTramite : locTramitesPorTrabajar) {
					warn(cadaTramite.getNombre() + " debe ser trabajado para avanzar de Fase.");
				}

				return null;
			}
			panelFases.avanzarFase(expediente, AccionFase.AVANCE, this.getSessionBean1().getUsuario(), null);

			setElementosPila();
			this.deshabilitarComponentesPanelFases();
		} catch(Exception e) {
			e.printStackTrace();
			warn(e.getMessage());
		}

		return null;
	}

	public String btnCancelarAvanceFase_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(!ultimo)
			return null;

		getElementosPila();
		NodoExpediente nodoFaseEnvio = expediente.getListaFasesOrdenada().get(expediente.getIndexActiva() - 1);
		if(!nodoFaseEnvio.esResponsableDirectoOPadre(this.getSessionBean1().getUsuario())) {
			warn("No tiene permisos para realizar esta acción");

			return null;
		}
		try {
			comentarioExpediente = (String) this.getTaComentario().getText();
			panelFases.retrocederFase(expediente, AccionFase.CANCELACION, this.getSessionBean1().getUsuario(), comentarioExpediente);
			this.deshabilitarComponentesPanelFases();
		} catch(Exception e) {
			e.printStackTrace();
			warn(e.getMessage());
		}

		return null;
	}

	public String btnRetrocederFase_action() {
		try {
			getElementosPila();
			comentarioExpediente = (String) this.getTaComentario().getText();
			panelFases.retrocederFase(expediente, AccionFase.RETROCESO, this.getSessionBean1().getUsuario(), comentarioExpediente);
			for(NodoExpediente cadaNodo : expediente.getFaseActual().getListaNodosExpedientes()) {
				Tramite tramite = (Tramite) cadaNodo;
				TramiteProcedimiento tramiteProcedimiento = (TramiteProcedimiento) tramite.getNodoProcedimiento();
				TramiteCatalogo tramiteCatalogo = tramiteProcedimiento.getTramiteCatalogo();
				if(tramiteCatalogo.isReiniciarConFase()) {
					tramite.setEstadoTramite(null);
				}
			}
			setElementosPila();
			this.deshabilitarComponentesPanelFases();
		} catch(Exception e) {
			e.printStackTrace();
			warn(e.getMessage());
		}

		return null;
	}

	private List<TramiteCatalogo> getListaTramiteCatalogoPorTrabajar() {
		List<TramiteCatalogo> locLista = new ArrayList<TramiteCatalogo>();
		for(NodoExpediente cadaNodo : expediente.getFaseActual().getListaNodosExpedientes()) {
			Tramite tramite = (Tramite) cadaNodo;
			TramiteProcedimiento tramiteProcedimiento = (TramiteProcedimiento) tramite.getNodoProcedimiento();
			TramiteCatalogo tramiteCatalogo = tramiteProcedimiento.getTramiteCatalogo();
			if(tramiteCatalogo.isRequeridoParaAvanzarDeFase() && tramite.getEstadoTramite() == null) {
				locLista.add(tramiteCatalogo);
			}
		}

		return locLista;
	}

	public String btnCerrarExpediente_action() {
		getElementosPila();
		List<TramiteCatalogo> locTramitesPorTrabajar = getListaTramiteCatalogoPorTrabajar();
		if(!locTramitesPorTrabajar.isEmpty()) {
			for(TramiteCatalogo cadaTramite : locTramitesPorTrabajar) {
				warn(cadaTramite.getNombre() + " debe ser trabajado para cerrar el Expediente.");
			}

			return null;
		}

		comentarioExpediente = (String) this.getTaComentario().getText();
		expediente.setEstado(Estado.CERRADO);
		try {
			panelFases.cerrarExpediente(expediente);
			this.getLblEstadoProcedimiento().setText("(CERRADO)");
		} catch(Exception e) {
			e.printStackTrace();
		}

		setElementosPila();
		this.deshabilitarComponentesPanelFases();
		panelFases.getTableTramite().getBtnConsultarTramite().setRendered(false);
		panelFases.getTableTramite().getBtnModificarTramite().setRendered(false);

		return null;
	}

	public String btnModificarTramite_action() {
		return toAbm(new TramiteModel().new ModificarController());
	}

	public String btnAgregarExtensionFase_action() {
		getElementosPila();
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new ExtensionModel().new AgregarController());

			Fase locFase = expediente.getFaseActual();
			this.getRequestBean1().setObjetoABM(locFase);

			return "ABMExtension";
		}

		return null;
	}

	public String btnAgregarExtensionTramite_action() {
		getElementosPila();
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			RowKey rk = null;
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new ExtensionModel().new AgregarController());

			rk = this.panelFases.getTableTramite().getSeleccionado();
			if(rk != null) {
				int index = getNroFila(rk.toString());
				Tramite tramite = (Tramite) this.panelFases.getTableTramite().getObjectListDataProvider().getObjects()[index];
				this.getRequestBean1().setObjetoABM(tramite);
				return "ABMExtension";
			}
		}
		warn("Debe seleccionar un Tramite");

		return null;
	}

	public String btnConsultarTramite_action() {
		return toAbm(new TramiteModel().new ConsultarControler());
	}

	public void btnGuardarAjax_action() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;

		noVacios[pos] = this.getTfPersona();
		nomNoVacios[pos++] = "Interesado";
		noVacios[pos] = this.getTaAsunto();
		nomNoVacios[pos++] = "Asunto";
		noVacios[pos] = this.getTfNroRegistro();
		nomNoVacios[pos++] = "Nro Registro";
		v.noSonVacios(noVacios, nomNoVacios);

		Expediente locExpediente = (Expediente) obtenerObjetoDelElementoPila(0, Expediente.class);
		if(locExpediente.getInteresado() != null && locExpediente.getInteresado().getTelefono() == null && locExpediente.getInteresado().getCelular() == null) {
			v.getErrores().add("El interesado no tiene teléfono ni celular de contacto.");
		}

		if(v.getErrores().size() > 0) {
			error("Errores:");
			for(Object obj : v.getErrores()) {
				warn(obj.toString());
			}
		}
	}

	protected String toAbm(NodoExpedienteControler pController) {
		getElementosPila();
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			RowKey rk = null;
			if(pController.seleccionarObjeto()) {
				try {
					rk = this.panelFases.getTableTramite().getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());

						Tramite tramite = (Tramite) this.panelFases.getTableTramite().getObjectListDataProvider().getObjects()[index];
						if(!pController.getValidacion(tramite)) {
							error(pController.getMessage());
							return null;
						}
						this.getRequestBean1().setObjetoABM(this.panelFases.getTableTramite().new WTramite(tramite, index));
					}

				} catch(Exception ex) {
					log(getCasoNavegacion() + "toAbmException:", ex);
					error(getNombrePagina() + " - toAbm: " + ex.getMessage());
				}
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			if(!pController.seleccionarObjeto() || (pController.seleccionarObjeto() && rk != null)) {
				this.getRequestBean1().setAbmController(pController);
				retorno = pController.getModel().getReglaNavegacion();
			} else {
				warn("Debe seleccionar un Tramite");
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		setElementosPila();

		return retorno;
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMExpediente$ABMExpediente}";
	}

	@Override
	public long getSerialVersionUID() {
		return Expediente.serialVersionUID;
	}

	// public void setPersonaAutocompletar(String pId, String pIdSubSesion) throws Exception {
	// Long id = Long.parseLong(pId);
	// Persona locInteresado = null;
	//
	// try {
	// locInteresado = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
	// } catch(TrascenderFrameworkException e) {
	// e.printStackTrace();
	// } catch(RemoteException e) {
	// e.printStackTrace();
	// }
	//
	// this.setIdSubSesion(Long.parseLong(pIdSubSesion));
	// getElementosPila();
	// interesado = locInteresado;
	// expediente.setInteresado(interesado);
	// setElementosPila();
	// }

	public boolean isHayPersona() {
		getElementoPila();
		Persona locInteresado = interesado;

		return(locInteresado != null && locInteresado.getIdPersona() != -1);
	}

	public DropDown getDdFaseEspecial() {
		return ddFaseEspecial;
	}

	public void setDdFaseEspecial(DropDown ddFaseEspecial) {
		this.ddFaseEspecial = ddFaseEspecial;
	}

}