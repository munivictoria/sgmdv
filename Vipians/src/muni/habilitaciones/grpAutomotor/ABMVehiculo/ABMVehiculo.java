/*
 * ABMVehiculo.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpAutomotor.ABMVehiculo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Tab;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.abstracts.TablaLogs;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class ABMVehiculo extends ABMPageBean {

	protected TextField tfPatente = new TextField();
	protected TextField tfModelo = new TextField();
	protected TextArea taDescripcion = new TextArea();
	protected TextField tfFechaInscripcion = new TextField();
	protected TextField tfCodigo = new TextField();
	protected TextField tfAnio = new TextField();
	protected TextField tfPeso = new TextField();
	protected TextField tfCapacidad = new TextField();
	protected TextField tfChasis = new TextField();
	protected TextField tfMotor = new TextField();
	protected TextField tfPropietario = new TextField();
	protected Label lblPatente = new Label();
	protected Label lblDescripcion = new Label();
	protected Label lblModelo = new Label();
	protected Label lblPropietarios = new Label();
	protected Label lblFechaInscripcion = new Label();
	protected Label lblCodigo = new Label();
	protected Label lblAnio = new Label();
	protected Label lblPeso = new Label();
	protected Label lblCapacidad = new Label();
	protected Label lblChasis = new Label();
	protected Label lblMotor = new Label();

	protected Button btnSeleccionarModelo = new Button();
	protected HtmlAjaxCommandButton btnLimpiarModelo = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnAgregarPropietario = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnLimpiarPropietario = new HtmlAjaxCommandButton();
	protected Table tablePropietarios = new Table();
	protected TableRowGroup tableRowGroup1 = new TableRowGroup();
	protected ObjectListDataProvider ldpRegistroPropietario = new ObjectListDataProvider();
	protected TableColumn tableColumn1 = new TableColumn();
	protected TableColumn tcPersona = new TableColumn();
	protected TableColumn tcDescripcion = new TableColumn();
	protected StaticText stPersona = new StaticText();
	protected StaticText staticText1 = new StaticText();
	protected StaticText stSeparador2 = new StaticText();
	protected TextArea taDescripcionPropietarios = new TextArea();
	protected RadioButton radioButton1 = new RadioButton();
	protected PanelGroup groupPanel1 = new PanelGroup();
	protected Button btnAgregarPersonaJuridica = new Button();
	protected Button btnAgregarPersonaFisica = new Button();
	protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
	private TabSet tabSet1 = new TabSet();
	private Tab tabOne = new Tab();
	private Tab tabTwo = new Tab();
	private PanelAtributoDinamico panelAtributoDinamico2 = new PanelAtributoDinamico();

	private TablaLogs tablaLogs2 = new TablaLogs(this.getNombreBean());
	private Label lblComentarioLogAuditoria = new Label();
	private TextArea taComentarioLogAuditoria = new TextArea();
	private Label lblComentarioLogAuditoria2 = new Label();
	private TextArea taComentarioLogAuditoria2 = new TextArea();
	
	private PanelGroup groupPanelObligaciones = new PanelGroup();
	private Table tableObligaciones = new Table();
	private TableRowGroup tableRowObligacion = new TableRowGroup();
	private ObjectListDataProvider ldpObligaciones = new ObjectListDataProvider();
	private TableColumn tableColumnObligacion1 = new TableColumn();
	private TableColumn tableColumnObligacion2 = new TableColumn();
	private TableColumn tableColumnObligacion3 = new TableColumn();
	private StaticText staticTextObligacion1 = new StaticText();
	private StaticText staticTextObligacion2 = new StaticText();
	private StaticText staticTextObligacion3 = new StaticText();
	
	// ***Getters & Setters***
	
	public TextField getTfFechaInscripcion() {
		return tfFechaInscripcion;
	}

	public PanelGroup getGroupPanelObligaciones() {
		return groupPanelObligaciones;
	}

	public void setGroupPanelObligaciones(PanelGroup groupPanelObligaciones) {
		this.groupPanelObligaciones = groupPanelObligaciones;
	}

	public Table getTableObligaciones() {
		return tableObligaciones;
	}

	public void setTableObligaciones(Table tableObligaciones) {
		this.tableObligaciones = tableObligaciones;
	}

	public TableRowGroup getTableRowObligacion() {
		return tableRowObligacion;
	}

	public void setTableRowObligacion(TableRowGroup tableRowObligacion) {
		this.tableRowObligacion = tableRowObligacion;
	}

	public ObjectListDataProvider getLdpObligaciones() {
		return ldpObligaciones;
	}

	public void setLdpObligaciones(ObjectListDataProvider ldpObligaciones) {
		this.ldpObligaciones = ldpObligaciones;
	}

	public TableColumn getTableColumnObligacion1() {
		return tableColumnObligacion1;
	}

	public void setTableColumnObligacion1(TableColumn tableColumnObligacion1) {
		this.tableColumnObligacion1 = tableColumnObligacion1;
	}

	public TableColumn getTableColumnObligacion2() {
		return tableColumnObligacion2;
	}

	public void setTableColumnObligacion2(TableColumn tableColumnObligacion2) {
		this.tableColumnObligacion2 = tableColumnObligacion2;
	}

	public TableColumn getTableColumnObligacion3() {
		return tableColumnObligacion3;
	}

	public void setTableColumnObligacion3(TableColumn tableColumnObligacion3) {
		this.tableColumnObligacion3 = tableColumnObligacion3;
	}

	public StaticText getStaticTextObligacion1() {
		return staticTextObligacion1;
	}

	public void setStaticTextObligacion1(StaticText staticTextObligacion1) {
		this.staticTextObligacion1 = staticTextObligacion1;
	}

	public StaticText getStaticTextObligacion2() {
		return staticTextObligacion2;
	}

	public void setStaticTextObligacion2(StaticText staticTextObligacion2) {
		this.staticTextObligacion2 = staticTextObligacion2;
	}

	public StaticText getStaticTextObligacion3() {
		return staticTextObligacion3;
	}

	public void setStaticTextObligacion3(StaticText staticTextObligacion3) {
		this.staticTextObligacion3 = staticTextObligacion3;
	}

	public TextField getTfPropietario() {
		return tfPropietario;
	}

	public void setTfPropietario(TextField tfPropietario) {
		this.tfPropietario = tfPropietario;
	}

	public HtmlAjaxCommandButton getBtnAgregarPropietario() {
		return btnAgregarPropietario;
	}

	public void setBtnAgregarPropietario(HtmlAjaxCommandButton btnAgregarPropietario) {
		this.btnAgregarPropietario = btnAgregarPropietario;
	}

	public HtmlAjaxCommandButton getBtnLimpiarPropietario() {
		return btnLimpiarPropietario;
	}

	public void setBtnLimpiarPropietario(HtmlAjaxCommandButton btnLimpiarPropietario) {
		this.btnLimpiarPropietario = btnLimpiarPropietario;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public Label getLblComentarioLogAuditoria() {
		return lblComentarioLogAuditoria;
	}

	public void setLblComentarioLogAuditoria(Label lblComentarioLogAuditoria) {
		this.lblComentarioLogAuditoria = lblComentarioLogAuditoria;
	}

	public TextArea getTaComentarioLogAuditoria() {
		return taComentarioLogAuditoria;
	}

	public void setTaComentarioLogAuditoria(TextArea taComentarioLogAuditoria) {
		this.taComentarioLogAuditoria = taComentarioLogAuditoria;
	}

	public Label getLblComentarioLogAuditoria2() {
		return lblComentarioLogAuditoria2;
	}

	public void setLblComentarioLogAuditoria2(Label lblComentarioLogAuditoria2) {
		this.lblComentarioLogAuditoria2 = lblComentarioLogAuditoria2;
	}

	public TextArea getTaComentarioLogAuditoria2() {
		return taComentarioLogAuditoria2;
	}

	public void setTaComentarioLogAuditoria2(TextArea taComentarioLogAuditoria2) {
		this.taComentarioLogAuditoria2 = taComentarioLogAuditoria2;
	}

	public TablaLogs getTablaLogs2() {
		return tablaLogs2;
	}

	public void setTablaLogs2(TablaLogs tablaLogs2) {
		this.tablaLogs2 = tablaLogs2;
	}

	public PanelAtributoDinamico getPanelAtributoDinamico2() {
		return panelAtributoDinamico2;
	}

	public void setPanelAtributoDinamico2(PanelAtributoDinamico panelAtributoDinamico2) {
		this.panelAtributoDinamico2 = panelAtributoDinamico2;
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

	public HtmlAjaxCommandButton getBtnLimpiarModelo() {
		return btnLimpiarModelo;
	}

	public void setBtnLimpiarModelo(HtmlAjaxCommandButton btnLimpiarModelo) {
		this.btnLimpiarModelo = btnLimpiarModelo;
	}

	public void setTfFechaInscripcion(TextField tfFechaInscripcion) {
		this.tfFechaInscripcion = tfFechaInscripcion;
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public Label getLblFechaInscripcion() {
		return lblFechaInscripcion;
	}

	public void setLblFechaInscripcion(Label lblFechaInscripcion) {
		this.lblFechaInscripcion = lblFechaInscripcion;
	}

	public Label getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(Label lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public Button getBtnAgregarPersonaFisica() {
		return btnAgregarPersonaFisica;
	}

	public void setBtnAgregarPersonaFisica(Button btnAgregarPersonaFisica) {
		this.btnAgregarPersonaFisica = btnAgregarPersonaFisica;
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

	public Button getBtnAgregarPersonaJuridica() {
		return btnAgregarPersonaJuridica;
	}

	public void setBtnAgregarPersonaJuridica(Button btnAgregarPersonaJuridica) {
		this.btnAgregarPersonaJuridica = btnAgregarPersonaJuridica;
	}

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public Label getLblPropietarios() {
		return lblPropietarios;
	}

	public void setLblPropietarios(Label lblPropietarios) {
		this.lblPropietarios = lblPropietarios;
	}

	public Table getTablePropietarios() {
		return tablePropietarios;
	}

	public void setTablePropietarios(Table tablePropietarios) {
		this.tablePropietarios = tablePropietarios;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public ObjectListDataProvider getLdpRegistroPropietario() {
		return ldpRegistroPropietario;
	}

	public void setLdpRegistroPropietario(ObjectListDataProvider ldpRegistroPropietario) {
		this.ldpRegistroPropietario = ldpRegistroPropietario;
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

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText stPersona) {
		this.stPersona = stPersona;
	}

	public TextArea getTaDescripcionPropietarios() {
		return taDescripcionPropietarios;
	}

	public void setTaDescripcionPropietarios(TextArea taDescripcionPropietarios) {
		this.taDescripcionPropietarios = taDescripcionPropietarios;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public Button getBtnSeleccionarModelo() {
		return btnSeleccionarModelo;
	}

	public void setBtnSeleccionarModelo(Button btnSeleccionarModelo) {
		this.btnSeleccionarModelo = btnSeleccionarModelo;
	}

	public Label getLblModelo() {
		return lblModelo;
	}

	public void setLblModelo(Label lblModelo) {
		this.lblModelo = lblModelo;
	}

	public TextField getTfModelo() {
		return tfModelo;
	}

	public void setTfModelo(TextField tfModelo) {
		this.tfModelo = tfModelo;
	}

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public Label getLblPatente() {
		return lblPatente;
	}

	public void setLblPatente(Label lblPatente) {
		this.lblPatente = lblPatente;
	}

	public TextField getTfPatente() {
		return tfPatente;
	}

	public void setTfPatente(TextField tf) {
		this.tfPatente = tf;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
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

	public TextField getTfAnio() {
		return tfAnio;
	}

	public void setTfAnio(TextField tfAnio) {
		this.tfAnio = tfAnio;
	}

	public TextField getTfPeso() {
		return tfPeso;
	}

	public void setTfPeso(TextField tfPeso) {
		this.tfPeso = tfPeso;
	}

	public TextField getTfCapacidad() {
		return tfCapacidad;
	}

	public void setTfCapacidad(TextField tfCapacidad) {
		this.tfCapacidad = tfCapacidad;
	}

	public TextField getTfChasis() {
		return tfChasis;
	}

	public void setTfChasis(TextField tfChasis) {
		this.tfChasis = tfChasis;
	}

	public TextField getTfMotor() {
		return tfMotor;
	}

	public void setTfMotor(TextField tfMotor) {
		this.tfMotor = tfMotor;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public Label getLblPeso() {
		return lblPeso;
	}

	public void setLblPeso(Label lblPeso) {
		this.lblPeso = lblPeso;
	}

	public Label getLblCapacidad() {
		return lblCapacidad;
	}

	public void setLblCapacidad(Label lblCapacidad) {
		this.lblCapacidad = lblCapacidad;
	}

	public Label getLblChasis() {
		return lblChasis;
	}

	public void setLblChasis(Label lblChasis) {
		this.lblChasis = lblChasis;
	}

	public Label getLblMotor() {
		return lblMotor;
	}

	public void setLblMotor(Label lblMotor) {
		this.lblMotor = lblMotor;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpRegistroPropietario().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private List<RegistroPropietario> getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaRegistroPropietarioVehiculo();
	}

	private void setListaDelCommunication(List<RegistroPropietario> lista) {
		this.getCommunicationHabilitacionesBean().setListaRegistroPropietarioVehiculo(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpRegistroPropietario();
	}
	
	private ObjectListDataProvider getObjectListDataProviderObligaciones() {
		return this.getLdpObligaciones();
	}

	private List getListaDelCommunicationObligaciones() {
		return this.getCommunicationHabilitacionesBean().getListaObligacionesAutomotor();
	}

	private void setListaDelCommunicationObligaciones(List lista) {
		this.getCommunicationHabilitacionesBean().setListaObligacionesAutomotor(lista);
	}

	// ***Methods***
	public ABMVehiculo() {
	}

	@Override
	protected void _init() throws Exception {

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if (this.getListaDelCommunicationObligaciones() != null) {
			this.getObjectListDataProviderObligaciones().setList(this.getListaDelCommunicationObligaciones());
		}
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new Vehiculo());
		ep.getObjetos().add(ind++, null);		// 1 Modelo
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos Vehiculo
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos Titulo Propiedad
		ep.getObjetos().add(ind++, null); 		// 4 Propietario

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		Vehiculo vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(0, Vehiculo.class);
		Modelo modelo = (Modelo) this.obtenerObjetoDelElementoPila(1, Modelo.class);
		List atributosDinamicos = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		List atributosDinamicos2 = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

		vehiculo.setPatente(getTextFieldValue(this.getTfPatente()));
		vehiculo.setDescripcion(getTextAreaValue(this.getTaDescripcion()));
		vehiculo.setAnio(getTextFieldValueInteger(this.getTfAnio()));
		vehiculo.setPeso(getTextFieldValueDouble(this.getTfPeso()));
		vehiculo.setCapacidad(getTextFieldValueDouble(this.getTfCapacidad()));
		vehiculo.setChasis(getTextFieldValue(this.getTfChasis()));
		vehiculo.setMotor(getTextFieldValue(this.getTfMotor()));
		vehiculo.getTituloPropiedad().setFechaInscripcion(Conversor.getFechaCortaDeString(getTextFieldValue(this.getTfFechaInscripcion())));
		vehiculo.getTituloPropiedad().setCodigo(getTextFieldValue(this.getTfCodigo()));

		this.getObjectListDataProvider().commitChanges();
		this.setListaDelCommunication(this.getObjectListDataProvider().getList());
		vehiculo.getTituloPropiedad().setListaRegistrosPropietarios(this.getObjectListDataProvider().getList());

		atributosDinamicos = (List) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		vehiculo.setListaAtributosDinamicos(atributosDinamicos);
		atributosDinamicos2 = (List) panelAtributoDinamico2.obtenerListaAtributosDinamicos(atributosDinamicos2);
		vehiculo.getTituloPropiedad().setListaAtributosDinamicos(atributosDinamicos2);
		vehiculo.getTituloPropiedad().setVehiculo(vehiculo);
		vehiculo.setModelo(modelo);
		
		this.getElementoPila().getObjetos().set(0, vehiculo);
		this.getElementoPila().getObjetos().set(1, modelo);
		this.getElementoPila().getObjetos().set(2, atributosDinamicos);
		this.getElementoPila().getObjetos().set(3, atributosDinamicos2);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		List atributosDinamicos = null;
		List atributosDinamicos2 = null;
		Vehiculo vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(0, Vehiculo.class);
		Modelo modelo = (Modelo) this.obtenerObjetoDelElementoPila(1, Modelo.class);

		this.getLdpObligaciones().setList(this.getListaDelCommunicationObligaciones());
		
		if(vehiculo.getListaAtributosDinamicos() != null) {
			try {
				atributosDinamicos = (List) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(Vehiculo.serialVersionUID, vehiculo.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(2, atributosDinamicos);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		this.getTfPatente().setText(vehiculo.getPatente());
		this.getTaDescripcion().setText(vehiculo.getDescripcion());
		this.getTfAnio().setText(vehiculo.getAnio());
		this.getTfPeso().setText(vehiculo.getPeso());
		this.getTfCapacidad().setText(vehiculo.getCapacidad());
		this.getTfChasis().setText(vehiculo.getChasis());
		this.getTfMotor().setText(vehiculo.getMotor());

		if(vehiculo.getTituloPropiedad() == null) {
			TituloPropiedadAutomotor tituloPropiedad = new TituloPropiedadAutomotor();
			vehiculo.setTituloPropiedad(tituloPropiedad);
			tituloPropiedad.setVehiculo(vehiculo);
		}

		if(vehiculo.getTituloPropiedad().getListaAtributosDinamicos() != null) {
			try {
				atributosDinamicos2 = (List) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(TituloPropiedadAutomotor.serialVersionUID, vehiculo.getTituloPropiedad().getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(3, atributosDinamicos2);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(4, Persona.class);
		
		this.getTfFechaInscripcion().setText(Conversor.getStringDeFechaCorta(vehiculo.getTituloPropiedad().getFechaInscripcion()));
		this.getTfCodigo().setText(vehiculo.getTituloPropiedad().getCodigo());
		this.getObjectListDataProvider().setList(vehiculo.getTituloPropiedad().getListaRegistrosPropietarios());
		this.setListaDelCommunication(this.getObjectListDataProvider().getList());

		if(modelo != null && modelo.getIdModelo() != -1) {
			this.getTfModelo().setText(modelo);
		}
		if(persona != null && persona.getIdPersona() != -1) {
			this.getTfPropietario().setText(persona);
		}

		atributosDinamicos = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo}", "vehiculo");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		panelAtributoDinamico2 = new PanelAtributoDinamico(atributosDinamicos2, "#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo}", "tituloPropiedad");
		panelAtributoDinamico2.establecerListaAtributosDinamicos(atributosDinamicos2);
		
	}

	public String btnSeleccionarModelo_action() {
		return navegarParaSeleccionar("AdminModelo");
	}

	public String btnLimpiarModelo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(1, this.getTfModelo());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	public void btnAgregarPropietario_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			Persona persona = (Persona) this.obtenerObjetoDelElementoPila(4);

			if(persona != null) {
				Vehiculo vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(0, Vehiculo.class);
				TituloPropiedadAutomotor tituloPropiedad = vehiculo.getTituloPropiedad();

				List<RegistroPropietario> registrosGuardados = (List<RegistroPropietario>) this.getListaDelCommunication();

				if(registrosGuardados == null) {
					registrosGuardados = new ArrayList<RegistroPropietario>();
					this.setListaDelCommunication(registrosGuardados);
				}

				RegistroPropietario deLaTabla = null;
				boolean esta = false;
				int i = 0;
				while(i < registrosGuardados.size() && !esta) {
					deLaTabla = (RegistroPropietario) registrosGuardados.get(i);
					esta = (deLaTabla.getPersona().getIdPersona() == persona.getIdPersona());
					i++;
				}
				if(!esta) {
					RegistroPropietario nuevoReg = new RegistroPropietario();
					nuevoReg.setPersona(persona);
					nuevoReg.setTituloPropiedad(tituloPropiedad);
					registrosGuardados.add(nuevoReg);
				} else {
					warn("La Persona que intenta agregar ya se encuentra en la lista.");
				}
				tituloPropiedad.setListaRegistrosPropietarios(registrosGuardados);

				this.getObjectListDataProvider().setList(registrosGuardados);
				this.setListaDelCommunication(registrosGuardados);
				vehiculo.getTituloPropiedad().setListaRegistrosPropietarios(registrosGuardados);

				this.getElementoPila().getObjetos().set(0, vehiculo);
				this.limpiarObjeto(4, this.getTfPropietario());
			}
		}
	}

	public String btnLimpiarPropietario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(4, this.getTfPropietario());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnAgregarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		Vehiculo vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(0, Vehiculo.class);

		TituloPropiedadAutomotor tituloPropiedad = vehiculo.getTituloPropiedad();
		List<RegistroPropietario> registros = (List<RegistroPropietario>) tituloPropiedad.getListaRegistrosPropietarios();
		System.out.println("titulo = " + tituloPropiedad.getTitulo() + ", lista = " + registros + ", tamaño = " + registros.size());

		if(ultimo) {

			this.guardarEstadoObjetosUsados();

			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					RegistroPropietario registroPropietario = (RegistroPropietario) obj;

					if(this.getObjectListDataProvider().getList().size() > 0) {
						this.getObjectListDataProvider().commitChanges();
					}
					registros.remove(registroPropietario);

					this.setListaDelCommunication(registros);
					this.getObjectListDataProvider().setList(this.getListaDelCommunication());
				}
			} catch(Exception ex) {
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {
				this.getListaDelCommunication().clear();
				this.getLdpRegistroPropietario().setList(this.getListaDelCommunication());
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
		return "ABMVehiculo";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Object seleccionado = pObject;
		System.out.println("*****OBJETO SELECCIONADO**********");
		System.out.println(seleccionado);

		if(seleccionado instanceof Modelo) {
			Modelo modelo = (Modelo) seleccionado;
			this.getElementoPila().getObjetos().set(1, modelo);
		}

		if(seleccionado instanceof Persona) {
			Persona persona = (Persona) seleccionado;
			Vehiculo vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(0, Vehiculo.class);
			TituloPropiedadAutomotor tituloPropiedad = vehiculo.getTituloPropiedad();

			List<RegistroPropietario> registrosGuardados = (List<RegistroPropietario>) this.getListaDelCommunication();

			if(registrosGuardados == null) {
				registrosGuardados = new ArrayList<RegistroPropietario>();
				this.setListaDelCommunication(registrosGuardados);
			}

			RegistroPropietario deLaTabla = null;
			boolean esta = false;
			int i = 0;
			while(i < registrosGuardados.size() && !esta) {
				deLaTabla = (RegistroPropietario) registrosGuardados.get(i);
				esta = (deLaTabla.getPersona().getIdPersona() == persona.getIdPersona());
				i++;
			}
			if(!esta) {
				RegistroPropietario nuevoReg = new RegistroPropietario();
				nuevoReg.setPersona(persona);
				nuevoReg.setTituloPropiedad(tituloPropiedad);
				registrosGuardados.add(nuevoReg);
			} else {
				warn("La Persona que intenta agregar ya se encuentra en la lista.");
			}
			tituloPropiedad.setListaRegistrosPropietarios(registrosGuardados);

			this.getObjectListDataProvider().setList(registrosGuardados);
			this.setListaDelCommunication(registrosGuardados);
			vehiculo.getTituloPropiedad().setListaRegistrosPropietarios(registrosGuardados);

			this.getElementoPila().getObjetos().set(0, vehiculo);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Vehiculo vehiculo = (Vehiculo) pObject;

		this.getElementoPila().getObjetos().set(0, vehiculo);
		this.getElementoPila().getObjetos().set(1, vehiculo.getModelo());
		this.getElementoPila().getObjetos().set(2, vehiculo.getListaAtributosDinamicos());
		if(vehiculo.getTituloPropiedad() != null) {
			this.getElementoPila().getObjetos().set(3, vehiculo.getTituloPropiedad().getListaAtributosDinamicos());
		}
		
		try {
			this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
			this.setListaDelCommunicationObligaciones((ArrayList) this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligaciones(null, null, null, null, vehiculo));
			this.getLdpObligaciones().setList(this.getListaDelCommunicationObligaciones());
			
		} catch (Exception ex) {
			error("No se pudieron obtener las Obligaciones del Vehiculo: " + ex.getMessage());
		}
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		try {
			String idTab = this.getTabSet1().getSelected();
			Vehiculo locVehiculo = this.obtenerObjetoDelElementoPila(0, Vehiculo.class);

			if(idTab != null) {
				if(idTab.equals("one")) {
					this.getTablaLogs().getLdpLogs().setList(locVehiculo.getListaLogsAuditoria());
				} else if(idTab.equals("two") && locVehiculo.getTituloPropiedad() != null) {
					this.getTablaLogs().getLdpLogs().setList(locVehiculo.getTituloPropiedad().getListaLogsAuditoria());
				}
			} else {
				this.getTablaLogs().getLdpLogs().setList(locVehiculo.getListaLogsAuditoria());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getSerialVersionUID() {
		return Vehiculo.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo}";
	}

	public void setModeloVehiculoAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Modelo modelo = null;

		try {
			modelo = (Modelo) this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getModeloById(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, modelo);
	}

	public boolean isHayModelo() {
		Modelo locModelo = (Modelo) this.obtenerObjetoDelElementoPila(1);
		return(locModelo != null && locModelo.getIdModelo() != -1);
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
		this.getElementoPila().getObjetos().set(4, persona);
	}

	public boolean isHayPersona() {
		Persona locPersona = (Persona) this.obtenerObjetoDelElementoPila(4);
		return(locPersona != null && locPersona.getIdPersona() != -1);
	}
}