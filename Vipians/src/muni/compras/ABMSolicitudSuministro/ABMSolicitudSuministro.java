/*
 * ABMSolicitudSuministro.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */

package muni.compras.ABMSolicitudSuministro;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ActionEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Form;
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
import com.trascender.compras.recurso.persistent.reference.CuentaRfr;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.LineaPresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.PresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMSolicitudSuministro extends ABMPageBean {

	protected StaticText stSeparador1 = new StaticText();
	protected StaticText stSeparador2 = new StaticText();
	protected StaticText stSeparador3 = new StaticText();
	protected StaticText stBien = new StaticText();
	protected StaticText stPresupuestosSolSum = new StaticText();
	protected StaticText stProveedor = new StaticText();
	protected StaticText stPlazo = new StaticText();
	protected StaticText stTotalPresupuestado = new StaticText();
	protected StaticText stSeparador4 = new StaticText();
	protected StaticText stMovimientoMercaderia = new StaticText();
	protected StaticText stStockBien = new StaticText();
	protected StaticText stCantidadMercaderia = new StaticText();
	protected StaticText stDeposito = new StaticText();
	protected StaticText stDepositoDestino = new StaticText();
	protected StaticText stFecha = new StaticText();
	protected StaticText stUsuario = new StaticText();
	protected StaticText stCuentaRfr = new StaticText();
	private StaticText stBienLineaOrdenCompra = new StaticText();
	private StaticText stCantidadLineaOrdenCompra = new StaticText();
	private StaticText stMontoUnitarioLineaOrdenCompra = new StaticText();
	private StaticText stMontoTotalLineaOrdenCompra = new StaticText();
	
	protected Table table2 = new Table();
	protected Table table3 = new Table();
	private Table tablaLineaOrdenCompra = new Table();
	protected TableRowGroup tableRowGroup2 = new TableRowGroup();
	protected TableRowGroup tableRowGroup3 = new TableRowGroup();
	private TableRowGroup trgLineaOrdenCompra = new TableRowGroup();
	protected Table tableFirmas = new Table();
	protected TableRowGroup trgFirmas = new TableRowGroup();
	protected ObjectListDataProvider ldpFirmas = new ObjectListDataProvider();
	protected ObjectListDataProvider ldpPresupuestoSolSum = new ObjectListDataProvider();
	protected ObjectListDataProvider ldpMovimientoMercaderia = new ObjectListDataProvider();
	private ObjectListDataProvider ldpLineaOrdenCompra = new ObjectListDataProvider();

	protected TableColumn tableColumn1 = new TableColumn();
	protected TableColumn tcBien = new TableColumn();
	protected TableColumn tcEstado = new TableColumn();
	protected TableColumn tcCuentaRfr = new TableColumn();
	protected TableColumn tcCantidad = new TableColumn();
	protected TableColumn tableColumn2 = new TableColumn();
	protected TableColumn tableColumn3 = new TableColumn();
	protected TableColumn tcProveedor = new TableColumn();
	protected TableColumn tcPlazo = new TableColumn();
	protected TableColumn tcTotalPresupuestado = new TableColumn();
	protected TableColumn tcStockBien = new TableColumn();
	protected TableColumn tcCantidadMercaderia = new TableColumn();
	protected TableColumn tcDeposito = new TableColumn();
	protected TableColumn tcDepositoDestino = new TableColumn();
	protected TableColumn tcFecha = new TableColumn();
	protected TableColumn tcUsuario = new TableColumn();
	private TableColumn tcBienLineaOrdenCompra = new TableColumn();
	private TableColumn tcCantidadLineaOrdenCompra = new TableColumn();
	private TableColumn tcMontoUnitarioLineaOrdenCompra = new TableColumn();
	private TableColumn tcMontoTotalLineaOrdenCompra = new TableColumn();
	
	protected TextField tfFechaEmision = new TextField();
	protected TextField tfUsuario = new TextField();
	protected TextField tfEstado = new TextField();
	protected TextField tfCantidad = new TextField();
	protected TextField tfNumero = new TextField();
	protected TextField tfBien = new TextField();

	protected Label lblUsuario = new Label();
	protected Label lblFechaEmision = new Label();
	protected Label lblArea = new Label();
	protected Label lblDescripcion = new Label();
	protected Label lblLineaSolSuministro = new Label();
	protected Label lblFinalizarComo = new Label();
	protected Label lblComentario = new Label();
	protected Label lblNumero = new Label();
	protected Label lblListaFirmas = new Label();
	protected Label lblUrgente = new Label();
	private Label lblLineaOrdenCompra = new Label();
	
	protected Button btnSeleccionarBien = new Button();
	protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnAgregarBien = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnLimpiarBien = new HtmlAjaxCommandButton();
	protected Button btnAgregarPresupuestoSolSum = new Button();
	protected Button btnModificarPresupuestoSolSum = new Button();
	protected Button btnConsultarPresupuestoSolSum = new Button();
	protected HtmlAjaxCommandButton btnQuitarPresupuestoSolSum = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodosPresupuestoSolSum = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnCambiarCuenta = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnSugerirCuenta = new HtmlAjaxCommandButton();

	protected SingleSelectOptionsList ddFinalizarComoDefaultOptions = new SingleSelectOptionsList();
	protected DropDown ddFinalizarComo = new DropDown();

	protected SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();
	protected DropDown ddArea = new DropDown();

	protected TextArea taDescripcion = new TextArea();
	protected TextArea taComentario = new TextArea();
	protected TableRowGroup tableRowGroup1 = new TableRowGroup();
	protected Table table1 = new Table();
	protected ObjectListDataProvider ldpLineaSolSuministro = new ObjectListDataProvider();
	protected RadioButton radioButton1 = new RadioButton();
	protected RadioButton radioButton2 = new RadioButton();
	protected NumberConverter numberConverter1 = new NumberConverter();
	protected PanelGroup groupPanel1 = new PanelGroup();
	protected PanelGroup groupPanel2 = new PanelGroup();
	protected PanelGroup gpLineas = new PanelGroup();
	protected PanelGroup gpFinalizarComo = new PanelGroup();
	protected RowKey rowKeySeleccionado = null;
	protected Object lastSelected = null;
	protected Object lastSelected2 = null;
	protected PanelGroup pgFirmasSolicitud = new PanelGroup();
	protected PanelGroup pgMovimientoMercaderia = new PanelGroup();
	protected Checkbox cbUrgente = new Checkbox();
	protected PanelGroup pgUrgente = new PanelGroup();

	protected ObjectListDataProvider objectListDataProvider = new ObjectListDataProvider();
	protected DateTimeConverter dateTimeConverter = new DateTimeConverter();

	// ***GETTERS & SETTERS***
	
	@Override
	public Body getBody1() {
		Body body1 = super.getBody1();
		body1.setOnKeyPress("");
		return body1;
	}
	// Sobreescritos para que funcione el javascript en la tabla de agregar bienes...
	@Override
	public Form getForm1() {
		Form form1 =  super.getForm1();
		form1.setOnKeyPress("");
		return form1;
	}
	
	public StaticText getStUsuario() {
		return stUsuario;
	}

	public StaticText getStBienLineaOrdenCompra() {
		return stBienLineaOrdenCompra;
	}

	public void setStBienLineaOrdenCompra(StaticText stBienLineaOrdenCompra) {
		this.stBienLineaOrdenCompra = stBienLineaOrdenCompra;
	}

	public StaticText getStCantidadLineaOrdenCompra() {
		return stCantidadLineaOrdenCompra;
	}

	public void setStCantidadLineaOrdenCompra(StaticText stCantidadLineaOrdenCompra) {
		this.stCantidadLineaOrdenCompra = stCantidadLineaOrdenCompra;
	}

	public StaticText getStMontoUnitarioLineaOrdenCompra() {
		return stMontoUnitarioLineaOrdenCompra;
	}

	public void setStMontoUnitarioLineaOrdenCompra(
			StaticText stMontoUnitarioLineaOrdenCompra) {
		this.stMontoUnitarioLineaOrdenCompra = stMontoUnitarioLineaOrdenCompra;
	}

	public StaticText getStMontoTotalLineaOrdenCompra() {
		return stMontoTotalLineaOrdenCompra;
	}

	public void setStMontoTotalLineaOrdenCompra(
			StaticText stMontoTotalLineaOrdenCompra) {
		this.stMontoTotalLineaOrdenCompra = stMontoTotalLineaOrdenCompra;
	}

	public Table getTablaLineaOrdenCompra() {
		return tablaLineaOrdenCompra;
	}

	public void setTablaLineaOrdenCompra(Table tablaLineaOrdenCompra) {
		this.tablaLineaOrdenCompra = tablaLineaOrdenCompra;
	}

	public TableRowGroup getTrgLineaOrdenCompra() {
		return trgLineaOrdenCompra;
	}

	public void setTrgLineaOrdenCompra(TableRowGroup trgLineaOrdenCompra) {
		this.trgLineaOrdenCompra = trgLineaOrdenCompra;
	}

	public ObjectListDataProvider getLdpLineaOrdenCompra() {
		return ldpLineaOrdenCompra;
	}

	public void setLdpLineaOrdenCompra(ObjectListDataProvider ldpLineaOrdenCompra) {
		this.ldpLineaOrdenCompra = ldpLineaOrdenCompra;
	}

	public TableColumn getTcBienLineaOrdenCompra() {
		return tcBienLineaOrdenCompra;
	}

	public void setTcBienLineaOrdenCompra(TableColumn tcBienLineaOrdenCompra) {
		this.tcBienLineaOrdenCompra = tcBienLineaOrdenCompra;
	}

	public TableColumn getTcCantidadLineaOrdenCompra() {
		return tcCantidadLineaOrdenCompra;
	}

	public void setTcCantidadLineaOrdenCompra(TableColumn tcCantidadLineaOrdenCompra) {
		this.tcCantidadLineaOrdenCompra = tcCantidadLineaOrdenCompra;
	}

	public TableColumn getTcMontoUnitarioLineaOrdenCompra() {
		return tcMontoUnitarioLineaOrdenCompra;
	}

	public void setTcMontoUnitarioLineaOrdenCompra(
			TableColumn tcMontoUnitarioLineaOrdenCompra) {
		this.tcMontoUnitarioLineaOrdenCompra = tcMontoUnitarioLineaOrdenCompra;
	}

	public TableColumn getTcMontoTotalLineaOrdenCompra() {
		return tcMontoTotalLineaOrdenCompra;
	}

	public void setTcMontoTotalLineaOrdenCompra(
			TableColumn tcMontoTotalLineaOrdenCompra) {
		this.tcMontoTotalLineaOrdenCompra = tcMontoTotalLineaOrdenCompra;
	}

	public Label getLblLineaOrdenCompra() {
		return lblLineaOrdenCompra;
	}

	public void setLblLineaOrdenCompra(Label lblLineaOrdenCompra) {
		this.lblLineaOrdenCompra = lblLineaOrdenCompra;
	}

	public PanelGroup getPgUrgente() {
		return pgUrgente;
	}

	public void setPgUrgente(PanelGroup pgUrgente) {
		this.pgUrgente = pgUrgente;
	}

	public HtmlAjaxCommandButton getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(HtmlAjaxCommandButton btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
	}

	public TextField getTfBien() {
		return tfBien;
	}

	public void setTfBien(TextField tfBien) {
		this.tfBien = tfBien;
	}

	public HtmlAjaxCommandButton getBtnAgregarBien() {
		return btnAgregarBien;
	}

	public void setBtnAgregarBien(HtmlAjaxCommandButton btnAgregarBien) {
		this.btnAgregarBien = btnAgregarBien;
	}

	public Label getLblUrgente() {
		return lblUrgente;
	}

	public void setLblUrgente(Label lblUrgente) {
		this.lblUrgente = lblUrgente;
	}

	public Checkbox getCbUrgente() {
		return cbUrgente;
	}

	public void setCbUrgente(Checkbox cbUrgente) {
		this.cbUrgente = cbUrgente;
	}

	public void setStUsuario(StaticText stUsuario) {
		this.stUsuario = stUsuario;
	}

	public TableColumn getTcUsuario() {
		return tcUsuario;
	}

	public void setTcUsuario(TableColumn tcUsuario) {
		this.tcUsuario = tcUsuario;
	}

	public PanelGroup getPgFirmasSolicitud() {
		return pgFirmasSolicitud;
	}

	public HtmlAjaxCommandButton getBtnSugerirCuenta() {
		return btnSugerirCuenta;
	}

	public void setBtnSugerirCuenta(HtmlAjaxCommandButton btnSugerirCuenta) {
		this.btnSugerirCuenta = btnSugerirCuenta;
	}

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public HtmlAjaxCommandButton getBtnCambiarCuenta() {
		return btnCambiarCuenta;
	}

	public void setBtnCambiarCuenta(HtmlAjaxCommandButton btnCambiarCuenta) {
		this.btnCambiarCuenta = btnCambiarCuenta;
	}

	public PanelGroup getPgMovimientoMercaderia() {
		return pgMovimientoMercaderia;
	}

	public void setPgMovimientoMercaderia(PanelGroup pgMovimientoMercaderia) {
		this.pgMovimientoMercaderia = pgMovimientoMercaderia;
	}

	public StaticText getStCuentaRfr() {
		return stCuentaRfr;
	}

	public void setStCuentaRfr(StaticText stCuentaRfr) {
		this.stCuentaRfr = stCuentaRfr;
	}

	public StaticText getStMovimientoMercaderia() {
		return stMovimientoMercaderia;
	}

	public void setStMovimientoMercaderia(StaticText stMovimientoMercaderia) {
		this.stMovimientoMercaderia = stMovimientoMercaderia;
	}

	public StaticText getStStockBien() {
		return stStockBien;
	}

	public void setStStockBien(StaticText stStockBien) {
		this.stStockBien = stStockBien;
	}

	public StaticText getStCantidadMercaderia() {
		return stCantidadMercaderia;
	}

	public void setStCantidadMercaderia(StaticText stCantidadMercaderia) {
		this.stCantidadMercaderia = stCantidadMercaderia;
	}

	public StaticText getStDeposito() {
		return stDeposito;
	}

	public void setStDeposito(StaticText stDeposito) {
		this.stDeposito = stDeposito;
	}

	public StaticText getStDepositoDestino() {
		return stDepositoDestino;
	}

	public void setStDepositoDestino(StaticText stDepositoDestino) {
		this.stDepositoDestino = stDepositoDestino;
	}

	public StaticText getStFecha() {
		return stFecha;
	}

	public void setStFecha(StaticText stFecha) {
		this.stFecha = stFecha;
	}

	public TableColumn getTcStockBien() {
		return tcStockBien;
	}

	public void setTcStockBien(TableColumn tcStockBien) {
		this.tcStockBien = tcStockBien;
	}

	public TableColumn getTcCantidadMercaderia() {
		return tcCantidadMercaderia;
	}

	public void setTcCantidadMercaderia(TableColumn tcCantidadMercaderia) {
		this.tcCantidadMercaderia = tcCantidadMercaderia;
	}

	public TableColumn getTcDeposito() {
		return tcDeposito;
	}

	public void setTcDeposito(TableColumn tcDeposito) {
		this.tcDeposito = tcDeposito;
	}

	public TableColumn getTcDepositoDestino() {
		return tcDepositoDestino;
	}

	public void setTcDepositoDestino(TableColumn tcDepositoDestino) {
		this.tcDepositoDestino = tcDepositoDestino;
	}

	public TableColumn getTcFecha() {
		return tcFecha;
	}

	public void setTcFecha(TableColumn tcFecha) {
		this.tcFecha = tcFecha;
	}

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table table3) {
		this.table3 = table3;
	}

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup tableRowGroup3) {
		this.tableRowGroup3 = tableRowGroup3;
	}

	public ObjectListDataProvider getLdpMovimientoMercaderia() {
		return ldpMovimientoMercaderia;
	}

	public void setLdpMovimientoMercaderia(ObjectListDataProvider ldpMovimientoMercaderia) {
		this.ldpMovimientoMercaderia = ldpMovimientoMercaderia;
	}

	public TableColumn getTcCuentaRfr() {
		return tcCuentaRfr;
	}

	public void setTcCuentaRfr(TableColumn tcCuentaRfr) {
		this.tcCuentaRfr = tcCuentaRfr;
	}

	public StaticText getStPresupuestosSolSum() {
		return stPresupuestosSolSum;
	}

	public void setStPresupuestosSolSum(StaticText stPresupuestosSolSum) {
		this.stPresupuestosSolSum = stPresupuestosSolSum;
	}

	public StaticText getStProveedor() {
		return stProveedor;
	}

	public void setStProveedor(StaticText stProveedor) {
		this.stProveedor = stProveedor;
	}

	public StaticText getStPlazo() {
		return stPlazo;
	}

	public void setStPlazo(StaticText stPlazo) {
		this.stPlazo = stPlazo;
	}

	public StaticText getStTotalPresupuestado() {
		return stTotalPresupuestado;
	}

	public void setStTotalPresupuestado(StaticText stTotalPresupuestado) {
		this.stTotalPresupuestado = stTotalPresupuestado;
	}

	public StaticText getStSeparador4() {
		return stSeparador4;
	}

	public void setStSeparador4(StaticText stSeparador4) {
		this.stSeparador4 = stSeparador4;
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public ObjectListDataProvider getLdpPresupuestoSolSum() {
		return ldpPresupuestoSolSum;
	}

	public void setLdpPresupuestoSolSum(ObjectListDataProvider ldpPresupuestoSolSum) {
		this.ldpPresupuestoSolSum = ldpPresupuestoSolSum;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableColumn getTcProveedor() {
		return tcProveedor;
	}

	public void setTcProveedor(TableColumn tcProveedor) {
		this.tcProveedor = tcProveedor;
	}

	public TableColumn getTcPlazo() {
		return tcPlazo;
	}

	public void setTcPlazo(TableColumn tcPlazo) {
		this.tcPlazo = tcPlazo;
	}

	public TableColumn getTcTotalPresupuestado() {
		return tcTotalPresupuestado;
	}

	public void setTcTotalPresupuestado(TableColumn tcTotalPresupuestado) {
		this.tcTotalPresupuestado = tcTotalPresupuestado;
	}

	public Button getBtnAgregarPresupuestoSolSum() {
		return btnAgregarPresupuestoSolSum;
	}

	public void setBtnAgregarPresupuestoSolSum(Button btnAgregarPresupuestoSolSum) {
		this.btnAgregarPresupuestoSolSum = btnAgregarPresupuestoSolSum;
	}

	public Button getBtnModificarPresupuestoSolSum() {
		return btnModificarPresupuestoSolSum;
	}

	public void setBtnModificarPresupuestoSolSum(Button btnModificarPresupuestoSolSum) {
		this.btnModificarPresupuestoSolSum = btnModificarPresupuestoSolSum;
	}

	public Button getBtnConsultarPresupuestoSolSum() {
		return btnConsultarPresupuestoSolSum;
	}

	public void setBtnConsultarPresupuestoSolSum(Button btnConsultarPresupuestoSolSum) {
		this.btnConsultarPresupuestoSolSum = btnConsultarPresupuestoSolSum;
	}

	public HtmlAjaxCommandButton getBtnQuitarPresupuestoSolSum() {
		return btnQuitarPresupuestoSolSum;
	}

	public void setBtnQuitarPresupuestoSolSum(HtmlAjaxCommandButton btnQuitarPresupuestoSolSum) {
		this.btnQuitarPresupuestoSolSum = btnQuitarPresupuestoSolSum;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosPresupuestoSolSum() {
		return btnQuitarTodosPresupuestoSolSum;
	}

	public void setBtnQuitarTodosPresupuestoSolSum(HtmlAjaxCommandButton btnQuitarTodosPresupuestoSolSum) {
		this.btnQuitarTodosPresupuestoSolSum = btnQuitarTodosPresupuestoSolSum;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public Object getLastSelected2() {
		return lastSelected2;
	}

	public void setLastSelected2(Object lastSelected2) {
		this.lastSelected2 = lastSelected2;
	}

	public void setObjectListDataProvider(ObjectListDataProvider objectListDataProvider) {
		this.objectListDataProvider = objectListDataProvider;
	}

	public void setPgFirmasSolicitud(PanelGroup pgFirmasSolicitud) {
		this.pgFirmasSolicitud = pgFirmasSolicitud;
	}

	public DateTimeConverter getDateTimeConverter() {
		return dateTimeConverter;
	}

	public void setDateTimeConverter(DateTimeConverter dateTimeConverter) {
		this.dateTimeConverter = dateTimeConverter;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public Label getLblListaFirmas() {
		return lblListaFirmas;
	}

	public void setLblListaFirmas(Label lblListaFirmas) {
		this.lblListaFirmas = lblListaFirmas;
	}

	public ObjectListDataProvider getLdpFirmas() {
		return ldpFirmas;
	}

	public void setLdpFirmas(ObjectListDataProvider ldpFirmas) {
		this.ldpFirmas = ldpFirmas;
	}

	public Table getTableFirmas() {
		return tableFirmas;
	}

	public void setTableFirmas(Table tableFirmas) {
		this.tableFirmas = tableFirmas;
	}

	public TableRowGroup getTrgFirmas() {
		return trgFirmas;
	}

	public void setTrgFirmas(TableRowGroup trgFirmas) {
		this.trgFirmas = trgFirmas;
	}

	public TextField getTfCantidad() {
		return tfCantidad;
	}

	public void setTfCantidad(TextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public StaticText getStBien() {
		return stBien;
	}

	public void setStBien(StaticText stBien) {
		this.stBien = stBien;
	}

	public TableColumn getTcBien() {
		return tcBien;
	}

	public void setTcBien(TableColumn tcBien) {
		this.tcBien = tcBien;
	}

	public PanelGroup getGpFinalizarComo() {
		return gpFinalizarComo;
	}

	public void setGpFinalizarComo(PanelGroup gpFinalizarComo) {
		this.gpFinalizarComo = gpFinalizarComo;
	}

	public Label getLblComentario() {
		return lblComentario;
	}

	public void setLblComentario(Label lblComentario) {
		this.lblComentario = lblComentario;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	public SingleSelectOptionsList getDdFinalizarComoDefaultOptions() {
		return ddFinalizarComoDefaultOptions;
	}

	public void setDdFinalizarComoDefaultOptions(SingleSelectOptionsList ddFinalizarComoDefaultOptions) {
		this.ddFinalizarComoDefaultOptions = ddFinalizarComoDefaultOptions;
	}

	public DropDown getDdFinalizarComo() {
		return ddFinalizarComo;
	}

	public void setDdFinalizarComo(DropDown ddFinalizarComo) {
		this.ddFinalizarComo = ddFinalizarComo;
	}

	public Label getLblFinalizarComo() {
		return lblFinalizarComo;
	}

	public void setLblFinalizarComo(Label lblFinalizarComo) {
		this.lblFinalizarComo = lblFinalizarComo;
	}

	public PanelGroup getGpLineas() {
		return gpLineas;
	}

	public void setGpLineas(PanelGroup gpLineas) {
		this.gpLineas = gpLineas;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public Label getLblArea() {
		return lblArea;
	}

	public void setLblArea(Label lblArea) {
		this.lblArea = lblArea;
	}

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public Label getLblFechaEmision() {
		return lblFechaEmision;
	}

	public void setLblFechaEmision(Label lblFechaEmision) {
		this.lblFechaEmision = lblFechaEmision;
	}

	public Label getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(Label lblUsuario) {
		this.lblUsuario = lblUsuario;
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

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public TextField getTfEstado() {
		return tfEstado;
	}

	public void setTfEstado(TextField tfEstado) {
		this.tfEstado = tfEstado;
	}

	public TextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(TextField tf) {
		this.tfUsuario = tf;
	}

	public TextField getTfFechaEmision() {
		return tfFechaEmision;
	}

	public void setTfFechaEmision(TextField tf) {
		this.tfFechaEmision = tf;
	}

	public DropDown getDdArea() {
		return ddArea;
	}

	public void setDdArea(DropDown ddArea) {
		this.ddArea = ddArea;
	}

	public SingleSelectOptionsList getDdAreaOptions() {
		return ddAreaOptions;
	}

	public void setDdAreaOptions(SingleSelectOptionsList ddAreaOptions) {
		this.ddAreaOptions = ddAreaOptions;
	}

	public Label getLblLineaSolSuministro() {
		return lblLineaSolSuministro;
	}

	public void setLblLineaSolSuministro(Label lblLineaSolSuministro) {
		this.lblLineaSolSuministro = lblLineaSolSuministro;
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

	public Button getBtnSeleccionarBien() {
		return btnSeleccionarBien;
	}

	public void setBtnSeleccionarBien(Button b) {
		this.btnSeleccionarBien = b;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public ObjectListDataProvider getLdpLineaSolSuministro() {
		return ldpLineaSolSuministro;
	}

	public void setLdpLineaSolSuministro(ObjectListDataProvider ldpLineaSolSuministro) {
		this.ldpLineaSolSuministro = ldpLineaSolSuministro;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	// ***METHODS***
	public ABMSolicitudSuministro() {
	}

	@Override
	protected void _init() throws Exception {

		getTable1().setClearSortButton(true);
		getTable2().setClearSortButton(true);
		getTable3().setClearSortButton(true);
		
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if(this.getListaDelCommunicationPresupuestoSolSum() != null) {
			this.getObjectListDataProviderPresupuestosSolSum().setList(this.getListaDelCommunicationPresupuestoSolSum());
		}
		if(this.getListaDelCommunicationMovimientoMercaderia() != null) {
			this.getObjectListDataProviderMovimientoMercaderia().setList(this.getListaDelCommunicationMovimientoMercaderia());
		}

		numberConverter1.setPattern("$ #,##0.00");

		dateTimeConverter.setPattern("dd/MM/yyyy hh:mm");
		dateTimeConverter.setTimeZone(TimeZone.getDefault());

		// Tipo Documento
		List<EstadoSolicitudSuministro> listaEstadosFinales = this.getCommunicationComprasBean().getListaEstadosFinalizacion();
		Option[] op = new Option[listaEstadosFinales.size()];
		int i = 0;
		for(EstadoSolicitudSuministro cadaEstado : listaEstadosFinales) {
			op[i++] = new Option(cadaEstado.getNombre(), cadaEstado.getNombre());
		}

		ddFinalizarComoDefaultOptions.setOptions(op);

		Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasSolSum().keySet();
		Option[] opAreas;
		i = 0;
		if(locListaAreas.size() == 1) {
			opAreas = new Option[locListaAreas.size()];
		} else {
			opAreas = new Option[locListaAreas.size() + 1];
			opAreas[i++] = new Option("", "");
		}

		for(String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new SolicitudSuministro()); // 0
		ep.getObjetos().add(ind++, new ArrayList()); // 1 listaLineas de Sol
		// suministro

		ep.getObjetos().add(ind++, new ArrayList()); // 2 Lista Presupuestos
		ep.getObjetos().add(ind++, new LineaSolicitudSuministro()); // 3
		ep.getObjetos().add(ind++, Boolean.FALSE); // 4 para saber si muestro o no el checkbox urgente
		ep.getObjetos().add(ind++, null); // 5 bien para agregar
		ep.getObjetos().add(ind++, new ArrayList()); // 6 Lista Lineas Orden Compra

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		SolicitudSuministro solicitudSuministro = this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);
		List listaLineaSolSuministro = null;
		List listaPresupuestosSolSum = this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		
		solicitudSuministro.setDescripcion(this.getTextAreaValue(getTaDescripcion()));
		solicitudSuministro.setComentarioFinalizacion(this.getTextAreaValue(getTaComentario()));

		String stringEstado = this.getDdFinalizarComo().getSelected().toString();

		if(solicitudSuministro.getUsuario() == null)
			solicitudSuministro.setUsuario(getSessionBean1().getUsuario());

		Object fechaEmision = new Date();
		solicitudSuministro.setFechaEmision((Date) fechaEmision);
		solicitudSuministro.setArea(this.getDDObjectValue(getDdArea(), getCommunicationComprasBean().getMapaAreasSolSum()));
		solicitudSuministro.setUrgente(this.getCbUrgente().isChecked());
		
		this.getObjectListDataProvider().commitChanges(); // para el listado

		solicitudSuministro.setListaPresupuestos(listaPresupuestosSolSum);
		this.getObjectListDataProviderPresupuestosSolSum().commitChanges();
		this.setListaDelCommunicationPresupuestoSolSum(listaPresupuestosSolSum);

		listaLineaSolSuministro = this.getObjectListDataProvider().getList();

		if(!listaLineaSolSuministro.isEmpty()) {
			HashSet locListadoLineas = new HashSet(listaLineaSolSuministro);
			solicitudSuministro.setListaLineaSolSuministro(locListadoLineas);
		}

		this.getElementoPila().getObjetos().set(0, solicitudSuministro);
		this.getElementoPila().getObjetos().set(1, listaLineaSolSuministro);
		this.getElementoPila().getObjetos().set(2, listaPresupuestosSolSum);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		SolicitudSuministro solicitudSuministro = this.obtenerObjetoDelElementoPila(ind++, SolicitudSuministro.class);
		List listaLineaSolSuministro = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List listaPresupuestosSolSum = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List listaLineasOrdenCompra = this.obtenerObjetoDelElementoPila(6, ArrayList.class);
		
		if(solicitudSuministro != null) {
			this.getTaDescripcion().setText(solicitudSuministro.getDescripcion());
			if(solicitudSuministro.getArea() != null && solicitudSuministro.getArea().getIdArea() != -1) {
				this.getDdArea().setSelected(solicitudSuministro.getArea().toString());
			}
		}

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object seleccionado = this.getRequestBean1().getRespuestaABM();

			if(seleccionado instanceof PresupuestoSolicitudSuministro) {
				PresupuestoSolicitudSuministro presupuestoSolSum = (PresupuestoSolicitudSuministro) seleccionado;

				solicitudSuministro = this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);

				boolean yaExiste = false;

				if(solicitudSuministro.getListaPresupuestos().contains(presupuestoSolSum)) {
					listaPresupuestosSolSum.remove(presupuestoSolSum);
				}
				solicitudSuministro.getListaPresupuestos().add(presupuestoSolSum);
				this.getElementoPila().getObjetos().set(2, solicitudSuministro.getListaPresupuestos());
			}
		}

		ind = 0;
		solicitudSuministro = this.obtenerObjetoDelElementoPila(ind++, SolicitudSuministro.class);
		listaLineaSolSuministro = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		listaPresupuestosSolSum = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Bien bien = this.obtenerObjetoDelElementoPila(5, Bien.class);

		this.getObjectListDataProviderPresupuestosSolSum().setList(listaPresupuestosSolSum);
		this.setListaDelCommunicationPresupuestoSolSum(listaPresupuestosSolSum);

		this.getObjectListDataProviderMovimientoMercaderia().setList(solicitudSuministro.getListaLineasMovimientoMercaderia());
		this.setListaDelCommunicationMovimientoMercaderia(solicitudSuministro.getListaLineasMovimientoMercaderia());
		
		this.getObjectListDataProviderLineaOrdenCompra().setList(listaLineasOrdenCompra);
		this.setListaDelCommunicationLineaOrdenCompra(listaLineasOrdenCompra);

		this.getDdFinalizarComo().setSelected(Util.getEnumNameFromString(String.valueOf(solicitudSuministro.getEstado())));
		this.getDdFinalizarComoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(solicitudSuministro.getEstado())));

		System.out.println("---------------------------------------------->mostrarEstado: " + solicitudSuministro.isUrgente());
		this.getCbUrgente().setValue(solicitudSuministro.isUrgente());
		this.getTfFechaEmision().setText(Conversor.getStringDeFechaCorta(new Date()));
		this.getTfUsuario().setText(solicitudSuministro.getUsuario() != null ? solicitudSuministro.getUsuario() : getSessionBean1().getUsuario().toString());
		this.getObjectListDataProvider().setList(listaLineaSolSuministro);
		this.setListaDelCommunication(listaLineaSolSuministro);
		this.getTfNumero().setText(solicitudSuministro.getNumero());
		this.getTaComentario().setText(solicitudSuministro.getComentarioFinalizacion());

		if(bien != null && bien.getIdBien() != -1) {
			this.getTfBien().setText(bien);
		}
		
		this.getLdpFirmas().setList(new ArrayList(solicitudSuministro.getListaFirmaPermiso()));
	}

	protected void limpiarObjetosUsados() {

	}

	public ObjectListDataProvider getObjectListDataProvider() {
		return this.objectListDataProvider;
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaLineasSoLSuministro();
	}

	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaLineasSoLSuministro(lista);
	}

	protected ObjectListDataProvider getObjectListDataProviderPresupuestosSolSum() {
		return this.getLdpPresupuestoSolSum();
	}

	protected List getListaDelCommunicationPresupuestoSolSum() {
		return this.getCommunicationComprasBean().getListaPresupuestosSolSum();
	}

	protected void setListaDelCommunicationPresupuestoSolSum(List lista) {
		this.getCommunicationComprasBean().setListaPresupuestosSolSum(lista);
	}

	protected ObjectListDataProvider getObjectListDataProviderMovimientoMercaderia() {
		return this.getLdpMovimientoMercaderia();
	}

	protected List getListaDelCommunicationMovimientoMercaderia() {
		return this.getCommunicationComprasBean().getListaMovimientoMercaderiaSolSum();
	}

	protected void setListaDelCommunicationMovimientoMercaderia(List lista) {
		this.getCommunicationComprasBean().setListaMovimientoMercaderiaSolSum(lista);
	}
	
	protected List getListaDelCommunicationLineaOrdenCompra(){
		return this.getCommunicationComprasBean().getListaMovimientoMercaderiaSolSum();
	}

	protected void setListaDelCommunicationLineaOrdenCompra(List lista) {
		this.getCommunicationComprasBean().setListaMovimientoMercaderiaSolSum(lista);
	}
	
	public ObjectListDataProvider getObjectListDataProviderLineaOrdenCompra() {
		return this.ldpLineaOrdenCompra;
	}
	
	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}
	
	public String getCurrentRowLineaOrdenCompra() {
		return trgLineaOrdenCompra.getRowKey().getRowId();
	}

	public void setCurrentRowLineaOrdenCompra(int row) {
	}

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
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

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getObjectListDataProviderPresupuestosSolSum().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private ArrayList crearListaLineaFactura(ArrayList list, SolicitudSuministro solicitudS) {
		// private ArrayList crearListaLineaFactura(ArrayList list,
		// FacturaContrato facturaContrato) {
		Iterator iterator = list.iterator();
		LineaSolicitudSuministro locLineaSolSuministro;
		ArrayList locListaLineaSolSuministro = new ArrayList();

		while(iterator.hasNext()) {
			locLineaSolSuministro = (LineaSolicitudSuministro) iterator.next();

			if(locLineaSolSuministro.getCantidad() != 0) {
				locLineaSolSuministro.setSolicitudSuministro(solicitudS);
				locListaLineaSolSuministro.add(locLineaSolSuministro);
			}
		}
		return locListaLineaSolSuministro;
	}

	public String btnCambiarCuenta_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					LineaSolicitudSuministro lineaSolSum = (LineaSolicitudSuministro) this.getObjectListDataProvider().getObjects()[index];

					this.getElementoPila().getObjetos().set(3, lineaSolSum);

					retorno = "AdminCuenta";
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

	public String btnSeleccionarBien_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminBien";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void btnAgregarBien_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		
		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			Bien bien = (Bien) this.obtenerObjetoDelElementoPila(5);

			if(bien != null) {
				List listaLineaSolSuministro = this.obtenerObjetoDelElementoPila(1, ArrayList.class);

				boolean esta = false;
				for(Object cadaObject : listaLineaSolSuministro) {
					LineaSolicitudSuministro cadaLinea = (LineaSolicitudSuministro) cadaObject;
					if(cadaLinea.getBien().equals(bien)) {
						esta = true;
						break;
					}
				}
				if(!esta) {
					SolicitudSuministro solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0);
					LineaSolicitudSuministro locLinea = new LineaSolicitudSuministro();
					locLinea.setSolicitudSuministro(solicitudSuministro);
					locLinea.setBien(bien);
					CuentaRfr cuentaRfr = null;
					try {
						cuentaRfr = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().getCuentaRfr(locLinea);
					} catch(TrascenderException e1) {
						e1.printStackTrace();
					}
					if(cuentaRfr != null) {
						locLinea.setCuenta(cuentaRfr);
					}
					listaLineaSolSuministro.add(locLinea);

					this.getObjectListDataProvider().setList(listaLineaSolSuministro);
					this.setListaDelCommunication(listaLineaSolSuministro);

					this.getElementoPila().getObjetos().set(1, listaLineaSolSuministro);
					this.limpiarObjeto(5, this.getTfBien());
				}
			}
		}
	}

	public String btnLimpiarBien_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(5, this.getTfBien());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	// public void btnAgregarLineaSolicitud_action() {
	// this.guardarEstadoObjetosUsados();
	// ArrayList listaLineaSolSuministro = (ArrayList)
	// this.obtenerObjetoDelElementoPila(2, ArrayList.class);
	// LineaSolicitudSuministro lineaSolSuministro = (LineaSolicitudSuministro)
	// this.obtenerObjetoDelElementoPila(5, LineaSolicitudSuministro.class);
	//
	// Validador v = new Validador();
	// UIComponent[] noVacios = new UIComponent[2];
	// String[] nomNoVacios = new String[2];
	// UIComponent[] flotante = new UIComponent[1];
	// String[] nomFlotante = new String[1];
	//
	// int pos = 0;
	// noVacios[pos] = this.getTfBien();
	// nomNoVacios[pos++] = "Bien Asociado";
	// noVacios[pos] = this.getTfCantidad();
	// nomNoVacios[pos++] = "Cantidad";
	//
	//
	// pos = 0;
	// flotante[pos] = this.getTfCantidad();
	// nomFlotante[pos++] = "Cantidad";
	//
	//
	// v.noSonVacios(noVacios, nomNoVacios);
	// v.sonFlotantes(flotante, nomFlotante);
	//
	// if (this.getTfCantidad().getText().toString().equals("0.0") ||
	// this.getTfCantidad().getText().toString().equals("0")) {
	// String msg = "Se debe Ingresar una Cantidad mayor a 0.";
	// this.getTfCantidad().setValid(false);
	// v.getErrores().add(msg);
	// }
	//
	//
	//
	// if (v.getErrores().size() > 0) {
	// error("Existen Errores:");
	// for (int i = 0; i < v.getErrores().size(); i++) {
	// warn(v.getErrores().toArray()[i].toString());
	// }
	//
	// } else {
	// if (lineaSolSuministro != null) {
	// listaLineaSolSuministro.add(lineaSolSuministro);
	// }
	// this.setListaDelCommunication(listaLineaSolSuministro);
	// this.getObjectListDataProvider().setList(listaLineaSolSuministro);
	// this.limpiarObjetosUsados();
	// this.guardarEstadoObjetosUsados();
	//
	//
	// }
	// }

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					this.guardarEstadoObjetosUsados();
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					LineaSolicitudSuministro locLineaSeleccionada = (LineaSolicitudSuministro) obj;

					for(LineaPresupuestoSolicitudSuministro cadaLineaPresupuesto : locLineaSeleccionada.getListaLineasPresupuestoSolicitudSuministro()) {
						if(cadaLineaPresupuesto.getLineaSolicitudSuministro().equals(obj)) {
							error("La Línea de Solicitud que intenta quitar posee Presupuestos asociados");
							return null;
						}
					}

					this.getListaDelCommunication().remove(index);
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

	public String btnSugerirCuenta_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					this.guardarEstadoObjetosUsados();
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];

					LineaSolicitudSuministro locLinea = (LineaSolicitudSuministro) obj;
					CuentaRfr cuentaRfr = null;
					try {
						cuentaRfr = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().getCuentaRfr(locLinea);
					} catch(TrascenderException e1) {
						e1.printStackTrace();
					}
					locLinea.setCuenta(cuentaRfr);
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

	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...
			try {

				if(this.getLdpPresupuestoSolSum().getList().size() > 0) {
					error("Alguna de las Líneas que desea quitar esta asociada a un Presupuesto");
					return null;
				}

				this.getListaDelCommunication().clear();
				this.getObjectListDataProvider().clearObjectList();
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarPresupuestoSolSum_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();
		SolicitudSuministro solicitudSuministro = this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);

		List listaLineasSolicitud = this.getListaDelCommunication();

		if(listaLineasSolicitud == null || listaLineasSolicitud.isEmpty()) {
			error("Existen Errores:");
			warn("Debe agregar al menos una L\355nea de Solicitud de Suministro.");
			return null;
		} else {
			for(Object cadaObject : listaLineasSolicitud) {
				LineaSolicitudSuministro cadaLinea = (LineaSolicitudSuministro) cadaObject;
				if(cadaLinea.getCantidad() == 0) {
					error("Existen Errores:");
					warn("La cantidad solicitada debe ser mayor a 0.");
					return null;
				}
			}
		}

		if(ultimo) {
			PresupuestoSolicitudSuministro presupuestoSolSum = new PresupuestoSolicitudSuministro();
			for(LineaSolicitudSuministro r : solicitudSuministro.getListaLineaSolSuministro()) {
				LineaPresupuestoSolicitudSuministro pss = new LineaPresupuestoSolicitudSuministro();
				// pss.setPrecioUnitario(0.00);
				pss.setLineaSolicitudSuministro(r);
				presupuestoSolSum.getListaLineasPresupuestoSolicitud().add(pss);
			}
			presupuestoSolSum.setSolicitudSuministro(solicitudSuministro);
			this.getRequestBean1().setObjetoABM(presupuestoSolSum);
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new PresupuestoSolicitudSuministroModel().new AgregarPresupuestoSolicitudSuministroController());

			guardarEstadoObjetosUsados();

			retorno = "ABMPresupuestoSolicitudSuministro";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarPresupuestoSolSum_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					PresupuestoSolicitudSuministro presupuestoSolSum = (PresupuestoSolicitudSuministro) this.getObjectListDataProviderPresupuestosSolSum().getObjects()[index];

					SolicitudSuministro solicitudSuministro = this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);

					if(presupuestoSolSum.getListaLineasPresupuestoSolicitud().size() < solicitudSuministro.getListaLineaSolSuministro().size()) {
						for(LineaSolicitudSuministro cadaLineaSolSum : solicitudSuministro.getListaLineaSolSuministro()) {
							boolean tienePresupuesto = false;

							for(LineaPresupuestoSolicitudSuministro cadaLinea : presupuestoSolSum.getListaLineasPresupuestoSolicitud()) {
								if(cadaLinea.getLineaSolicitudSuministro().equals(cadaLineaSolSum)) {
									tienePresupuesto = true;
								}
							}

							if(!tienePresupuesto) {
								LineaPresupuestoSolicitudSuministro nuevaLineaPresupuesto = new LineaPresupuestoSolicitudSuministro();
								nuevaLineaPresupuesto.setPrecioUnitario(0.00);
								nuevaLineaPresupuesto.setLineaSolicitudSuministro(cadaLineaSolSum);
								presupuestoSolSum.getListaLineasPresupuestoSolicitud().add(nuevaLineaPresupuesto);
							}
						}
					}
					this.getRequestBean1().setObjetoABM(presupuestoSolSum);
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setAbmController(new PresupuestoSolicitudSuministroModel().new ModificarPresupuestoSolicitudSuministroController());

					retorno = "ABMPresupuestoSolicitudSuministro";
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

	public String btnConsultarPresupuestoSolSum_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					PresupuestoSolicitudSuministro presupuestoSolSum = (PresupuestoSolicitudSuministro) this.getObjectListDataProviderPresupuestosSolSum().getObjects()[index];

					this.getRequestBean1().setObjetoABM(presupuestoSolSum);
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setAbmController(new PresupuestoSolicitudSuministroModel().new ConsultarPresupuestoSolicitudSuministroController());

					retorno = "ABMPresupuestoSolicitudSuministro";

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

	public String btnQuitarPresupuestoSolSum_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderPresupuestosSolSum().getObjects()[index];
					this.getListaDelCommunicationPresupuestoSolSum().remove(obj);
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

	public String btnQuitarTodosPresupuestoSolSum_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationPresupuestoSolSum().clear();
				// MODIFICAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void actionListenerDropArea(ActionEvent event) {
		guardarEstadoObjetosUsados();
		SolicitudSuministro solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0);
		List<LineaSolicitudSuministro> listaLineaSolSuministro = this.getObjectListDataProvider().getList();

		if(!listaLineaSolSuministro.isEmpty()) {
			CuentaRfr cuentaRfr = null;
			for(LineaSolicitudSuministro cadaLinea : listaLineaSolSuministro) {
				cadaLinea.setSolicitudSuministro(solicitudSuministro);
				try {
					cuentaRfr = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().getCuentaRfr(cadaLinea);
				} catch(TrascenderException e1) {
					e1.printStackTrace();
				}
				cadaLinea.setCuenta(cuentaRfr);
			}

			HashSet locListadoLineas = new HashSet(listaLineaSolSuministro);
			solicitudSuministro.setListaLineaSolSuministro(locListadoLineas);
			this.getObjectListDataProvider().setList(listaLineaSolSuministro);

			this.getElementoPila().getObjetos().set(0, solicitudSuministro);
			this.getElementoPila().getObjetos().set(1, listaLineaSolSuministro);
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMSolicitudSuministro";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Bien) {
			Bien bien = (Bien) pObject;
			List listaLineaSolSuministro = this.obtenerObjetoDelElementoPila(1, ArrayList.class);
			boolean esta = false;
			for(Object cadaObject : listaLineaSolSuministro) {
				LineaSolicitudSuministro cadaLinea = (LineaSolicitudSuministro) cadaObject;
				if(cadaLinea.getBien().equals(bien)) {
					esta = true;
					break;
				}
			}
			if(!esta) {
				SolicitudSuministro solicitudSuministro = (SolicitudSuministro) this.obtenerObjetoDelElementoPila(0);
				LineaSolicitudSuministro locLinea = new LineaSolicitudSuministro();
				locLinea.setSolicitudSuministro(solicitudSuministro);
				locLinea.setBien(bien);
				CuentaRfr cuentaRfr = null;
				try {

					cuentaRfr = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().getCuentaRfr(locLinea);
				} catch(TrascenderException e1) {
					e1.printStackTrace();
				}
				if(cuentaRfr != null) {
					locLinea.setCuenta(cuentaRfr);
				}
				listaLineaSolSuministro.add(locLinea);
				this.getElementoPila().getObjetos().set(1, listaLineaSolSuministro);
			}
		}
		if(pObject instanceof Cuenta) {
			Cuenta cuenta = (Cuenta) pObject;
			CuentaRfr cuentaRfr = new CuentaRfr();

			cuentaRfr.setAbreviatura(cuenta.getAbreviatura());
			cuentaRfr.setCodigoImputacion(cuenta.getCodigoImputacion());
			cuentaRfr.setNombre(cuenta.getNombre());
			cuentaRfr.setIdCuenta(cuenta.getIdCuenta());
			cuentaRfr.setCodigoImputacionCompleto(cuenta.getCodigoImputacionCompleto());

			LineaSolicitudSuministro lineaSolSum = (LineaSolicitudSuministro) this.obtenerObjetoDelElementoPila(3);
			lineaSolSum.setCuenta(cuentaRfr);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		if(pObject instanceof SolicitudSuministro) {
			SolicitudSuministro solicitudSuministro = (SolicitudSuministro) pObject;
			List listaPresupuestosSolSum = solicitudSuministro.getListaPresupuestos();
			List listaLineaSolSuministro = new ArrayList(solicitudSuministro.getListaLineaSolSuministro());

			this.getElementoPila().getObjetos().set(0, solicitudSuministro);
			this.getElementoPila().getObjetos().set(1, listaLineaSolSuministro);
			this.getElementoPila().getObjetos().set(2, listaPresupuestosSolSum);
		}
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		SolicitudSuministro locSolSum = this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);
		this.getTablaLogs().getLdpLogs().setList(locSolSum.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return SolicitudSuministro.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro}";
	}

	public boolean isMostrarCbUrgente() {
		SolicitudSuministro locSolSum = this.obtenerObjetoDelElementoPila(0, SolicitudSuministro.class);
		Area locArea = locSolSum.getArea();
		boolean mostrarCbUrgente = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().validarOperarSolicitudesUrgentes(locArea);
		this.getElementoPila().getObjetos().set(4, mostrarCbUrgente);
	
		Boolean retorno = false;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			retorno = (Boolean) this.obtenerObjetoDelElementoPila(4);
			if(retorno == null) {
				retorno = false;
			}
		}
		return retorno;
	}

	public void setBienAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Bien bien = null;

		try {
			bien = (Bien) this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findBienByID(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(5, bien);
	}

	public boolean isHayBien() {
		Bien locBien = (Bien) this.obtenerObjetoDelElementoPila(5);
		return(locBien != null && locBien.getIdBien() != -1);
	}
}