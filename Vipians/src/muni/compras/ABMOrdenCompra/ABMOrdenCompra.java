/*
 * ABMOrdenCompra.java
 *
 * Created on 2 de noviembre de 2006, 11:21
 * Copyright Trascender SRL
 */

package muni.compras.ABMOrdenCompra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
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
import com.trascender.compras.recurso.persistent.suministros.AutorizacionOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.PagoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMOrdenCompra extends ABMPageBean {

	protected TextField tfProveedor = new TextField();
	protected TextField tfFecha = new TextField();
	protected TextField tfNumero = new TextField();
	protected TextField tfPrecioUnitario = new TextField();

	protected TextField tfNuevoProveedor = new TextField();

	protected StaticText stTotal = new StaticText();
	protected StaticText stArea = new StaticText();
	protected StaticText stBien = new StaticText();
	protected StaticText stCantidad = new StaticText();
	protected StaticText staticText5 = new StaticText();
	protected StaticText stCuenta = new StaticText();
	protected StaticText stBien2 = new StaticText();
	protected StaticText stCantidad2 = new StaticText();
	protected StaticText stPrecioTotal = new StaticText();
	protected StaticText stSeparador2 = new StaticText();
	protected StaticText stUsuario = new StaticText();
	protected StaticText stNumeroSolicitud = new StaticText();
	protected StaticText stProveedorAnterior = new StaticText();
	protected StaticText stFechaTransferencia = new StaticText();
	protected StaticText stComentarioTransferencia = new StaticText();
	protected StaticText stNroFactura = new StaticText();
	protected StaticText stStockBien = new StaticText();
	protected StaticText stCantidadMercaderia = new StaticText();
	protected StaticText stDeposito = new StaticText();
	protected StaticText stDepositoDestino = new StaticText();
	protected StaticText stFecha = new StaticText();
	protected StaticText stMovimientoMercaderia = new StaticText();

	protected Label lblProveedor = new Label();
	protected Label lblMontoTotal = new Label();
	protected Label lblLineasOrdenCompra = new Label();
	protected Label lblFecha = new Label();
	protected Label lblDescripcion = new Label();
	protected Label lblNumero = new Label();
	protected Label lblSolicitudSuministro = new Label();
	protected Label lblLineaOrdenCompra = new Label();
	protected Label lblFirmas = new Label();
	protected Label lblFinalizarComo = new Label();
	protected Label lblComentario = new Label();
	protected Label lblProveedor2 = new Label();
	protected Label lblComentarioTransferencia = new Label();
	protected Label lblHistoricidad = new Label();
	protected Label lblPagos = new Label();
	protected Label lblListaFirmas = new Label();
	protected Button btnQuitar = new Button();
	protected Button btnQuitarTodos = new Button();
	protected Button btnAgregarLineaOrden = new Button();
	protected Button btnSeleccionarProveedor = new Button();
	protected HtmlAjaxCommandButton btnLimpiarProveedor = new HtmlAjaxCommandButton();
	protected Button btnSeleccionarNuevoProveedor = new Button();
	protected HtmlAjaxCommandButton btnLimpiarNuevoProveedor = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnAgregarPago = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarPago = new HtmlAjaxCommandButton();

	protected TableColumn tableColumn1 = new TableColumn();
	protected TableColumn tcArea = new TableColumn();
	protected TableColumn tcBien = new TableColumn();
	protected TableColumn tcCantidad = new TableColumn();
	protected TableColumn tcPrecioUnit = new TableColumn();
	protected TableColumn tcCuenta = new TableColumn();
	protected TableColumn tcBien2 = new TableColumn();
	protected TableColumn tcCantidad2 = new TableColumn();
	protected TableColumn tcPrecioTotal = new TableColumn();
	protected TableColumn tableColumn2 = new TableColumn();
	protected TableColumn tcUsuario = new TableColumn();
	protected TableColumn tableColumn3 = new TableColumn();
	protected TableColumn tcNumeroSolicitud = new TableColumn();
	protected TableColumn tcProveedorAnterior = new TableColumn();
	protected TableColumn tcFechaTransferencia = new TableColumn();
	protected TableColumn tcComentarioTransferencia = new TableColumn();
	protected TableColumn tcNroFactura = new TableColumn();
	protected TableColumn tcStockBien = new TableColumn();
	protected TableColumn tcCantidadMercaderia = new TableColumn();
	protected TableColumn tcDeposito = new TableColumn();
	protected TableColumn tcDepositoDestino = new TableColumn();
	protected TableColumn tcFecha = new TableColumn();

	protected Table table1 = new Table();
	protected Table table2 = new Table();
	protected Table table3 = new Table();
	protected Table table4 = new Table();
	protected Table table5 = new Table();
	protected Table tableFirmas = new Table();

	protected TableRowGroup tableRowGroup1 = new TableRowGroup();
	protected TableRowGroup tableRowGroup2 = new TableRowGroup();
	protected TableRowGroup tableRowGroup3 = new TableRowGroup();
	protected TableRowGroup tableRowGroup4 = new TableRowGroup();
	protected TableRowGroup tableRowGroup5 = new TableRowGroup();

	protected NumberConverter numberConverter1 = new NumberConverter();
	protected ObjectListDataProvider ldpLineasOrdenCompra = new ObjectListDataProvider();
	protected ObjectListDataProvider ldpLineasSoSuministro = new ObjectListDataProvider();
	protected ObjectListDataProvider ldpTransferenciaOrdenCompra = new ObjectListDataProvider();
	protected ObjectListDataProvider ldpFirmaPermiso = new ObjectListDataProvider();
	protected ObjectListDataProvider ldpMovimientoMercaderia = new ObjectListDataProvider();
	protected SingleSelectOptionsList ddFinalizarComoDefaultOptions = new SingleSelectOptionsList();

	protected RadioButton radioButton2 = new RadioButton();
	protected RadioButton radioButton3 = new RadioButton();

	protected PanelGroup pgFinalizarComo = new PanelGroup();
	protected PanelGroup groupPanel1 = new PanelGroup();
	protected PanelGroup pgTransferirOrdenCompra = new PanelGroup();
	protected PanelGroup pgHistoricidadTransferencias = new PanelGroup();
	protected PanelGroup pgPagos = new PanelGroup();
	protected PanelGroup pgFirmas = new PanelGroup();
	protected DropDown ddFinalizarComo = new DropDown();
	protected TextArea taDescripcion = new TextArea();
	protected TextArea taComentario = new TextArea();
	protected TextArea taComentarioTransferencia = new TextArea();
	protected Object lastSelected = null;
	protected RowKey rowKeySeleccionado = null;

	private Label lblEntregaInicial = new Label();
	private Label lblCantidadCuotas = new Label();
	private Label lblValorCuota = new Label();
	private Label lblValorCuotaFijo = new Label();
	private Label lblValorCuotaPorcentual = new Label();

	private TextField tfEntregaInicial = new TextField();
	private TextField tfCantidadCuotas = new TextField();
	private TextField tfValorCuota = new TextField();
	private TextField tfMontoPago = new TextField();

	private RadioButton rbValorCuotaFijo = new RadioButton();
	private RadioButton rbValorCuotaPorcentual = new RadioButton();

	private Table tablePagos = new Table();
	private TableRowGroup tableRowGroupPagos = new TableRowGroup();
	private ObjectListDataProvider objectListDataProviderPagos = new ObjectListDataProvider();
	private RadioButton radioButton1Pagos = new RadioButton();

	private TableColumn tableColumn1Pagos = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcMonto = new TableColumn();

	private TextField tfNombrePago = new TextField();
	private StaticText stMontoPago = new StaticText();

	private PanelGroup groupPanelPagos = new PanelGroup();

	private Object lastSelectedPagos = null;

	private HtmlAjaxCommandButton btnGenerarPagos = new HtmlAjaxCommandButton();
	private TableRowGroup trgFirmas = new TableRowGroup();
	private ObjectListDataProvider ldpFirmas = new ObjectListDataProvider();
	protected DateTimeConverter dateTimeConverter = new DateTimeConverter();

	// ***GETTERS & SETTERS***

	public StaticText getStNroFactura() {
		return stNroFactura;
	}

	public DateTimeConverter getDateTimeConverter() {
		return dateTimeConverter;
	}

	public void setDateTimeConverter(DateTimeConverter dateTimeConverter) {
		this.dateTimeConverter = dateTimeConverter;
	}

	public StaticText getStMovimientoMercaderia() {
		return stMovimientoMercaderia;
	}

	public void setStMovimientoMercaderia(StaticText stMovimientoMercaderia) {
		this.stMovimientoMercaderia = stMovimientoMercaderia;
	}

	public Table getTableFirmas() {
		return tableFirmas;
	}

	public void setTableFirmas(Table tableFirmas) {
		this.tableFirmas = tableFirmas;
	}

	public Label getLblListaFirmas() {
		return lblListaFirmas;
	}

	public void setLblListaFirmas(Label lblListaFirmas) {
		this.lblListaFirmas = lblListaFirmas;
	}

	public PanelGroup getPgFirmas() {
		return pgFirmas;
	}

	public void setPgFirmas(PanelGroup pgFirmas) {
		this.pgFirmas = pgFirmas;
	}

	public TableRowGroup getTrgFirmas() {
		return trgFirmas;
	}

	public void setTrgFirmas(TableRowGroup trgFirmas) {
		this.trgFirmas = trgFirmas;
	}

	public ObjectListDataProvider getLdpFirmas() {
		return ldpFirmas;
	}

	public void setLdpFirmas(ObjectListDataProvider ldpFirmas) {
		this.ldpFirmas = ldpFirmas;
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

	public Table getTable5() {
		return table5;
	}

	public void setTable5(Table table5) {
		this.table5 = table5;
	}

	public TableRowGroup getTableRowGroup5() {
		return tableRowGroup5;
	}

	public void setTableRowGroup5(TableRowGroup tableRowGroup5) {
		this.tableRowGroup5 = tableRowGroup5;
	}

	public ObjectListDataProvider getLdpMovimientoMercaderia() {
		return ldpMovimientoMercaderia;
	}

	public void setLdpMovimientoMercaderia(ObjectListDataProvider ldpMovimientoMercaderia) {
		this.ldpMovimientoMercaderia = ldpMovimientoMercaderia;
	}

	public HtmlAjaxCommandButton getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(HtmlAjaxCommandButton btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public HtmlAjaxCommandButton getBtnLimpiarNuevoProveedor() {
		return btnLimpiarNuevoProveedor;
	}

	public void setBtnLimpiarNuevoProveedor(HtmlAjaxCommandButton btnLimpiarNuevoProveedor) {
		this.btnLimpiarNuevoProveedor = btnLimpiarNuevoProveedor;
	}

	public TextField getTfMontoPago() {
		return tfMontoPago;
	}

	public void setTfMontoPago(TextField tfMontoPago) {
		this.tfMontoPago = tfMontoPago;
	}

	public PanelGroup getPgPagos() {
		return pgPagos;
	}

	public void setPgPagos(PanelGroup pgPagos) {
		this.pgPagos = pgPagos;
	}

	public void setStNroFactura(StaticText stNroFactura) {
		this.stNroFactura = stNroFactura;
	}

	public TableColumn getTcNroFactura() {
		return tcNroFactura;
	}

	public void setTcNroFactura(TableColumn tcNroFactura) {
		this.tcNroFactura = tcNroFactura;
	}

	public TextField getTfNombrePago() {
		return tfNombrePago;
	}

	public void setTfNombrePago(TextField tfNombrePago) {
		this.tfNombrePago = tfNombrePago;
	}

	public PanelGroup getPgHistoricidadTransferencias() {
		return pgHistoricidadTransferencias;
	}

	public void setPgHistoricidadTransferencias(PanelGroup pgHistoricidadTransferencias) {
		this.pgHistoricidadTransferencias = pgHistoricidadTransferencias;
	}

	public Label getLblPagos() {
		return lblPagos;
	}

	public void setLblPagos(Label lblPagos) {
		this.lblPagos = lblPagos;
	}

	public HtmlAjaxCommandButton getBtnAgregarPago() {
		return btnAgregarPago;
	}

	public void setBtnAgregarPago(HtmlAjaxCommandButton btnAgregarPago) {
		this.btnAgregarPago = btnAgregarPago;
	}

	public HtmlAjaxCommandButton getBtnQuitarPago() {
		return btnQuitarPago;
	}

	public void setBtnQuitarPago(HtmlAjaxCommandButton btnQuitarPago) {
		this.btnQuitarPago = btnQuitarPago;
	}

	public HtmlAjaxCommandButton getBtnGenerarPagos() {
		return btnGenerarPagos;
	}

	public void setBtnGenerarPagos(HtmlAjaxCommandButton btnGenerarPagos) {
		this.btnGenerarPagos = btnGenerarPagos;
	}

	public PanelGroup getGroupPanelPagos() {
		return groupPanelPagos;
	}

	public void setGroupPanelPagos(PanelGroup groupPanelPagos) {
		this.groupPanelPagos = groupPanelPagos;
	}

	public Object getLastSelectedPagos() {
		return lastSelectedPagos;
	}

	public void setLastSelectedPagos(Object lastSelectedPagos) {
		this.lastSelectedPagos = lastSelectedPagos;
	}

	public Label getLblCantidadCuotas() {
		return lblCantidadCuotas;
	}

	public void setLblCantidadCuotas(Label lblCantidadCuotas) {
		this.lblCantidadCuotas = lblCantidadCuotas;
	}

	public Label getLblEntregaInicial() {
		return lblEntregaInicial;
	}

	public void setLblEntregaInicial(Label lblEntregaInicial) {
		this.lblEntregaInicial = lblEntregaInicial;
	}

	public Label getLblValorCuota() {
		return lblValorCuota;
	}

	public void setLblValorCuota(Label lblValorCuota) {
		this.lblValorCuota = lblValorCuota;
	}

	public Label getLblValorCuotaFijo() {
		return lblValorCuotaFijo;
	}

	public void setLblValorCuotaFijo(Label lblValorCuotaFijo) {
		this.lblValorCuotaFijo = lblValorCuotaFijo;
	}

	public Label getLblValorCuotaPorcentual() {
		return lblValorCuotaPorcentual;
	}

	public void setLblValorCuotaPorcentual(Label lblValorCuotaPorcentual) {
		this.lblValorCuotaPorcentual = lblValorCuotaPorcentual;
	}

	public ObjectListDataProvider getObjectListDataProviderPagos() {
		return objectListDataProviderPagos;
	}

	public void setObjectListDataProviderPagos(ObjectListDataProvider objectListDataProviderPagos) {
		this.objectListDataProviderPagos = objectListDataProviderPagos;
	}

	public RadioButton getRadioButton1Pagos() {
		return radioButton1Pagos;
	}

	public void setRadioButton1Pagos(RadioButton radioButton1Pagos) {
		this.radioButton1Pagos = radioButton1Pagos;
	}

	public RadioButton getRbValorCuotaFijo() {
		return rbValorCuotaFijo;
	}

	public void setRbValorCuotaFijo(RadioButton rbValorCuotaFijo) {
		this.rbValorCuotaFijo = rbValorCuotaFijo;
	}

	public RadioButton getRbValorCuotaPorcentual() {
		return rbValorCuotaPorcentual;
	}

	public void setRbValorCuotaPorcentual(RadioButton rbValorCuotaPorcentual) {
		this.rbValorCuotaPorcentual = rbValorCuotaPorcentual;
	}

	public StaticText getStMontoPago() {
		return stMontoPago;
	}

	public void setStMontoPago(StaticText stMontoPago) {
		this.stMontoPago = stMontoPago;
	}

	public TableColumn getTableColumn1Pagos() {
		return tableColumn1Pagos;
	}

	public void setTableColumn1Pagos(TableColumn tableColumn1Pagos) {
		this.tableColumn1Pagos = tableColumn1Pagos;
	}

	public Table getTablePagos() {
		return tablePagos;
	}

	public void setTablePagos(Table tablePagos) {
		this.tablePagos = tablePagos;
	}

	public TableRowGroup getTableRowGroupPagos() {
		return tableRowGroupPagos;
	}

	public void setTableRowGroupPagos(TableRowGroup tableRowGroupPagos) {
		this.tableRowGroupPagos = tableRowGroupPagos;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public TextField getTfCantidadCuotas() {
		return tfCantidadCuotas;
	}

	public void setTfCantidadCuotas(TextField tfCantidadCuotas) {
		this.tfCantidadCuotas = tfCantidadCuotas;
	}

	public TextField getTfEntregaInicial() {
		return tfEntregaInicial;
	}

	public void setTfEntregaInicial(TextField tfEntregaInicial) {
		this.tfEntregaInicial = tfEntregaInicial;
	}

	public TextField getTfValorCuota() {
		return tfValorCuota;
	}

	public void setTfValorCuota(TextField tfValorCuota) {
		this.tfValorCuota = tfValorCuota;
	}

	public StaticText getStComentarioTransferencia() {
		return stComentarioTransferencia;
	}

	public void setStComentarioTransferencia(StaticText stComentarioTransferencia) {
		this.stComentarioTransferencia = stComentarioTransferencia;
	}

	public TableColumn getTcComentarioTransferencia() {
		return tcComentarioTransferencia;
	}

	public void setTcComentarioTransferencia(TableColumn tcComentarioTransferencia) {
		this.tcComentarioTransferencia = tcComentarioTransferencia;
	}

	public Label getLblHistoricidad() {
		return lblHistoricidad;
	}

	public void setLblHistoricidad(Label lblHistoricidad) {
		this.lblHistoricidad = lblHistoricidad;
	}

	public StaticText getStFechaTransferencia() {
		return stFechaTransferencia;
	}

	public void setStFechaTransferencia(StaticText stFechaTransferencia) {
		this.stFechaTransferencia = stFechaTransferencia;
	}

	public StaticText getStProveedorAnterior() {
		return stProveedorAnterior;
	}

	public void setStProveedorAnterior(StaticText stProveedorAnterior) {
		this.stProveedorAnterior = stProveedorAnterior;
	}

	public Table getTable4() {
		return table4;
	}

	public void setTable4(Table table4) {
		this.table4 = table4;
	}

	public TableRowGroup getTableRowGroup4() {
		return tableRowGroup4;
	}

	public void setTableRowGroup4(TableRowGroup tableRowGroup4) {
		this.tableRowGroup4 = tableRowGroup4;
	}

	public TableColumn getTcFechaTransferencia() {
		return tcFechaTransferencia;
	}

	public void setTcFechaTransferencia(TableColumn tcFechaTransferencia) {
		this.tcFechaTransferencia = tcFechaTransferencia;
	}

	public TableColumn getTcProveedorAnterior() {
		return tcProveedorAnterior;
	}

	public void setTcProveedorAnterior(TableColumn tcProveedorAnterior) {
		this.tcProveedorAnterior = tcProveedorAnterior;
	}

	public PanelGroup getPgTransferirOrdenCompra() {
		return pgTransferirOrdenCompra;
	}

	public void setPgTransferirOrdenCompra(PanelGroup pgTransferirOrdenCompra) {
		this.pgTransferirOrdenCompra = pgTransferirOrdenCompra;
	}

	public Label getLblComentarioTransferencia() {
		return lblComentarioTransferencia;
	}

	public void setLblComentarioTransferencia(Label lblComentarioTransferencia) {
		this.lblComentarioTransferencia = lblComentarioTransferencia;
	}

	public TextArea getTaComentarioTransferencia() {
		return taComentarioTransferencia;
	}

	public void setTaComentarioTransferencia(TextArea taComentarioTransferencia) {
		this.taComentarioTransferencia = taComentarioTransferencia;
	}

	public Button getBtnSeleccionarNuevoProveedor() {
		return btnSeleccionarNuevoProveedor;
	}

	public void setBtnSeleccionarNuevoProveedor(Button btnSeleccionarNuevoProveedor) {
		this.btnSeleccionarNuevoProveedor = btnSeleccionarNuevoProveedor;
	}

	public Label getLblProveedor2() {
		return lblProveedor2;
	}

	public void setLblProveedor2(Label lblProveedor2) {
		this.lblProveedor2 = lblProveedor2;
	}

	public TextField getTfPrecioUnitario() {
		return tfPrecioUnitario;
	}

	public void setTfPrecioUnitario(TextField tfPrecioUnitario) {
		this.tfPrecioUnitario = tfPrecioUnitario;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	public TableColumn getTcNumeroSolicitud() {
		return tcNumeroSolicitud;
	}

	public void setTcNumeroSolicitud(TableColumn tcNumeroSolicitud) {
		this.tcNumeroSolicitud = tcNumeroSolicitud;
	}

	public StaticText getStNumeroSolicitud() {
		return stNumeroSolicitud;
	}

	public void setStNumeroSolicitud(StaticText stNumeroSolicitud) {
		this.stNumeroSolicitud = stNumeroSolicitud;
	}

	public PanelGroup getPgFinalizarComo() {
		return pgFinalizarComo;
	}

	public void setPgFinalizarComo(PanelGroup pgFinalizarComo) {
		this.pgFinalizarComo = pgFinalizarComo;
	}

	public Label getLblFinalizarComo() {
		return lblFinalizarComo;
	}

	public void setLblFinalizarComo(Label lblFinalizarComo) {
		this.lblFinalizarComo = lblFinalizarComo;
	}

	public DropDown getDdFinalizarComo() {
		return ddFinalizarComo;
	}

	public void setDdFinalizarComo(DropDown ddFinalizarComo) {
		this.ddFinalizarComo = ddFinalizarComo;
	}

	public SingleSelectOptionsList getDdFinalizarComoDefaultOptions() {
		return ddFinalizarComoDefaultOptions;
	}

	public void setDdFinalizarComoDefaultOptions(SingleSelectOptionsList ddFinalizarComoDefaultOptions) {
		this.ddFinalizarComoDefaultOptions = ddFinalizarComoDefaultOptions;
	}

	public Label getLblComentario() {
		return lblComentario;
	}

	public void setLblComentario(Label lblComentario) {
		this.lblComentario = lblComentario;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public StaticText getStUsuario() {
		return stUsuario;
	}

	public void setStUsuario(StaticText stUsuario) {
		this.stUsuario = stUsuario;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public RadioButton getRadioButton3() {
		return radioButton3;
	}

	public void setRadioButton3(RadioButton radioButton3) {
		this.radioButton3 = radioButton3;
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public TableColumn getTcUsuario() {
		return tcUsuario;
	}

	public void setTcUsuario(TableColumn tcUsuario) {
		this.tcUsuario = tcUsuario;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup tableRowGroup3) {
		this.tableRowGroup3 = tableRowGroup3;
	}

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table table3) {
		this.table3 = table3;
	}

	public Label getLblFirmas() {
		return lblFirmas;
	}

	public void setLblFirmas(Label lblFirmas) {
		this.lblFirmas = lblFirmas;
	}

	public StaticText getStPrecioTotal() {
		return stPrecioTotal;
	}

	public void setStPrecioTotal(StaticText stPrecioTotal) {
		this.stPrecioTotal = stPrecioTotal;
	}

	public TableColumn getTcPrecioTotal() {
		return tcPrecioTotal;
	}

	public void setTcPrecioTotal(TableColumn tcPrecioTotal) {
		this.tcPrecioTotal = tcPrecioTotal;
	}

	public TableColumn getTcBien2() {
		return tcBien2;
	}

	public void setTcBien2(TableColumn tcBien2) {
		this.tcBien2 = tcBien2;
	}

	public StaticText getStBien2() {
		return stBien2;
	}

	public void setStBien2(StaticText stBien2) {
		this.stBien2 = stBien2;
	}

	public TableColumn getTcCantidad2() {
		return tcCantidad2;
	}

	public void setTcCantidad2(TableColumn tcCantidad2) {
		this.tcCantidad2 = tcCantidad2;
	}

	public StaticText getStCantidad2() {
		return stCantidad2;
	}

	public void setStCantidad2(StaticText stCantidad2) {
		this.stCantidad2 = stCantidad2;
	}

	public TableColumn getTcPrecioUnit() {
		return tcPrecioUnit;
	}

	public void setTcPrecioUnit(TableColumn tcPrecioUnit) {
		this.tcPrecioUnit = tcPrecioUnit;
	}

	public Label getLblLineaOrdenCompra() {
		return lblLineaOrdenCompra;
	}

	public void setLblLineaOrdenCompra(Label lblLineaOrdenCompra) {
		this.lblLineaOrdenCompra = lblLineaOrdenCompra;
	}

	public TableColumn getTcCuenta() {
		return tcCuenta;
	}

	public void setTcCuenta(TableColumn tcCuenta) {
		this.tcCuenta = tcCuenta;
	}

	public ObjectListDataProvider getLdpFirmaPermiso() {
		return ldpFirmaPermiso;
	}

	public void setLdpFirmaPermiso(ObjectListDataProvider ldpFirmaPermiso) {
		this.ldpFirmaPermiso = ldpFirmaPermiso;
	}

	public ObjectListDataProvider getLdpLineasSoSuministro() {
		return ldpLineasSoSuministro;
	}

	public void setLdpLineasSoSuministro(ObjectListDataProvider oldp) {
		this.ldpLineasSoSuministro = oldp;
	}

	public ObjectListDataProvider getLdpTransferenciaOrdenCompra() {
		return ldpTransferenciaOrdenCompra;
	}

	public void setLdpTransferenciaOrdenCompra(ObjectListDataProvider ldpTransferenciaOrdenCompra) {
		this.ldpTransferenciaOrdenCompra = ldpTransferenciaOrdenCompra;
	}

	public Label getLblSolicitudSuministro() {
		return lblSolicitudSuministro;
	}

	public void setLblSolicitudSuministro(Label lblSolicitudSuministro) {
		this.lblSolicitudSuministro = lblSolicitudSuministro;
	}

	public Label getLblLineasOrdenCompra() {
		return lblLineasOrdenCompra;
	}

	public void setLblLineasOrdenCompra(Label lblLineasOrdenCompra) {
		this.lblLineasOrdenCompra = lblLineasOrdenCompra;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tf) {
		this.tfProveedor = tf;
	}

	public TextField getTfNuevoProveedor() {
		return tfNuevoProveedor;
	}

	public void setTfNuevoProveedor(TextField tfNuevoProveedor) {
		this.tfNuevoProveedor = tfNuevoProveedor;
	}

	public Label getLblMontoTotal() {
		return lblMontoTotal;
	}

	public void setLblMontoTotal(Label lblMontoTotal) {
		this.lblMontoTotal = lblMontoTotal;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label l) {
		this.lblProveedor = l;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	public StaticText getStTotal() {
		return stTotal;
	}

	public void setStTotal(StaticText stTotal) {
		this.stTotal = stTotal;
	}

	public ObjectListDataProvider getLdpLineasOrdenCompra() {
		return ldpLineasOrdenCompra;
	}

	public void setLdpLineasOrdenCompra(ObjectListDataProvider oldp) {
		this.ldpLineasOrdenCompra = oldp;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public Button getBtnAgregarLineaOrden() {
		return btnAgregarLineaOrden;
	}

	public void setBtnAgregarLineaOrden(Button b) {
		this.btnAgregarLineaOrden = b;
	}

	public Button getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(Button b) {
		this.btnQuitar = b;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	public TableColumn getTcArea() {
		return tcArea;
	}

	public void setTcArea(TableColumn tcArea) {
		this.tcArea = tcArea;
	}

	public StaticText getStArea() {
		return stArea;
	}

	public void setStArea(StaticText stArea) {
		this.stArea = stArea;
	}

	public Button getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(Button b) {
		this.btnQuitarTodos = b;
	}

	public Button getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public void setBtnSeleccionarProveedor(Button b) {
		this.btnSeleccionarProveedor = b;
	}

	public TableColumn getTcBien() {
		return tcBien;
	}

	public void setTcBien(TableColumn tcBien) {
		this.tcBien = tcBien;
	}

	public StaticText getStBien() {
		return stBien;
	}

	public void setStBien(StaticText stBien) {
		this.stBien = stBien;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public StaticText getStCantidad() {
		return stCantidad;
	}

	public void setStCantidad(StaticText stCantidad) {
		this.stCantidad = stCantidad;
	}

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label l) {
		this.lblDescripcion = l;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label l) {
		this.lblFecha = l;
	}

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tf) {
		this.tfFecha = tf;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	public StaticText getStCuenta() {
		return stCuenta;
	}

	public void setStCuenta(StaticText stCuenta) {
		this.stCuenta = stCuenta;
	}

	// ***METHODS***
	public ABMOrdenCompra() {
	}

	@Override
	protected void _init() throws Exception {
		
		getTable1().setClearSortButton(true);
		getTable2().setClearSortButton(true);
		getTable3().setClearSortButton(true);
		getTable4().setClearSortButton(true);
		getTable5().setClearSortButton(true);
		getTablePagos().setClearSortButton(true);
		getTableFirmas().setClearSortButton(true);
		
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		if(this.getListaDelCommunicationPagos() != null) {
			this.getObjectListDataProviderPagos().setList(this.getListaDelCommunicationPagos());
		}

		if(this.getListaDelCommunicationSolSum() != null) {
			this.getObjectListDataProviderSolSum().setList(this.getListaDelCommunicationSolSum());
		}

		if(this.getListaDelCommunicationMovimientoMercaderia() != null) {
			this.getObjectListDataProviderMovimientoMercaderia().setList(this.getListaDelCommunicationMovimientoMercaderia());
		}

		Option[] op = null;
		// Tipo Documento
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(new Object[] {OrdenCompra.Estado.ANULADA, OrdenCompra.Estado.RESCINDIDA}, "may");
		ddFinalizarComoDefaultOptions.setOptions(op);

		dateTimeConverter.setPattern("dd/MM/yyyy hh:mm");
		dateTimeConverter.setTimeZone(TimeZone.getDefault());

		numberConverter1.setPattern("$ #,##0.00");
		numberConverter1.setMinIntegerDigits(1);
		numberConverter1.setMaxIntegerDigits(40);
		numberConverter1.setMaxFractionDigits(3);

		dateTimeConverter.setPattern("dd/MM/yyyy hh:mm");
		dateTimeConverter.setTimeZone(TimeZone.getDefault());
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		OrdenCompra locOrdenCompra = this.obtenerObjetoDelElementoPila(0, OrdenCompra.class);
		this.getTablaLogs().getLdpLogs().setList(locOrdenCompra.getListaLogsAuditoria());
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new OrdenCompra()); // 0
		ep.getObjetos().add(ind++, new Proveedor()); // 1
		ep.getObjetos().add(ind++, new ArrayList()); // 2 LineasOrdenCompra
		ep.getObjetos().add(ind++, new ArrayList()); // 3 Solicitudes suministro
		ep.getObjetos().add(ind++, new ArrayList()); // 4 Firmas Autorizaciones

		// String Necesario para diferenciar si se selecciono el proveedor o
		// proveedor nuevo
		ep.getObjetos().add(ind++, null); // 5 String

		ep.getObjetos().add(ind++, new Proveedor()); // 6 Proveedor Nuevo
														// (transferencia)

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		OrdenCompra ordenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(0, OrdenCompra.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);
		List lineasOrdenCompra = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		List solSuministros = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		List autorizaciones = (List) this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		Proveedor proveedorNuevo = (Proveedor) this.obtenerObjetoDelElementoPila(6, Proveedor.class);

		ordenCompra.setFechaEmision(this.getTextFieldValueDate(getTfFecha()));
		ordenCompra.setDescripcion(this.getTextAreaValue(getTaDescripcion()));

		if(proveedor.getIdProveedor() == -1) {
			proveedor = null;
		}

		if(proveedorNuevo.getIdProveedor() == -1) {
			proveedorNuevo = null;
		}

		if(ordenCompra.getIdOrdenCompra() == -1) {
			ordenCompra = new OrdenCompra();
		}

		if(proveedor != null) {
			try {
				ordenCompra.setProveedor(proveedor);
			} catch(Exception ex) {
				log(getCasoNavegacion() + "_GuardarError:", ex);
				error(getNombrePagina() + " - Guardar: " + ex.getMessage());
			}
		}

		if(!lineasOrdenCompra.isEmpty()) {

			for(int i = 0; i < lineasOrdenCompra.size(); i++) {
				LineaOrdenCompra cadaLineaOrdenCompra = (LineaOrdenCompra) lineasOrdenCompra.get(i);
				this.getTfPrecioUnitario().setText(cadaLineaOrdenCompra.getMontoUnitario());
				// solSuministros.addAll(cadaLineaOrdenCompra.getListaLineasSolicitudSuministro());
				// ////////////////////////////////////////////////////
			}
			ordenCompra.setListaLineaOrdenCompra(lineasOrdenCompra);
		} else {
			ordenCompra.setListaLineaOrdenCompra(null);
		}

		this.getObjectListDataProviderSolSum().commitChanges();
		solSuministros = (ArrayList) this.getObjectListDataProviderSolSum().getList();
		if(solSuministros.isEmpty())
			solSuministros = null;
		this.setListaDelCommunication(solSuministros);

		this.getObjectListDataProvider().commitChanges();

		if(lineasOrdenCompra.isEmpty()) {
			lineasOrdenCompra = null;
		}
		this.setListaDelCommunication(lineasOrdenCompra);

		if(lineasOrdenCompra != null && !lineasOrdenCompra.isEmpty()) {
			ordenCompra.setTotal();
		}

		this.getObjectListDataProviderAutorizaciones().commitChanges();
		autorizaciones = (ArrayList) this.getObjectListDataProviderAutorizaciones().getList();
		if(autorizaciones.isEmpty())
			autorizaciones = null;

		this.getObjectListDataProviderPagos().commitChanges();
		List pagos = this.getObjectListDataProviderPagos().getList();
		ordenCompra.setListaPagosOrdenCompra(pagos);

		ind = 0;
		this.getElementoPila().getObjetos().set(0, ordenCompra);
		this.getElementoPila().getObjetos().set(1, proveedor);
		this.getElementoPila().getObjetos().set(2, lineasOrdenCompra);
		this.getElementoPila().getObjetos().set(3, solSuministros);
		this.getElementoPila().getObjetos().set(4, autorizaciones);
		this.getElementoPila().getObjetos().set(6, proveedorNuevo);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		OrdenCompra ordenCompra = new OrdenCompra();
		Proveedor proveedor = null;
		List lineasOrdenCompra = (List) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		List solSuministro = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		List autorizaciones = (List) this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		List firmas = new ArrayList();
		double montoTotal = 0.0;
		Proveedor proveedorNuevo = null;
		acomodarSeleccionado();

		if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {

			for(Object cadaObject : this.getRequestBean1().getObjetosSeleccionMultiple()) {
				LineaSolicitudSuministro cadaLineaSolSum = (LineaSolicitudSuministro) cadaObject;
				boolean repetida = false;

				if(this.obtenerObjetoDelElementoPila(2) != null) {
					for(Object l : (List) this.obtenerObjetoDelElementoPila(2)) {
						LineaOrdenCompra lineaOrdComp = (LineaOrdenCompra) l;

						/*
						 * if (lineaOrdComp.getListaLineasSolicitudSuministro(). contains(cadaLineaSolSum)) { repetida = true; }
						 */
					}
				}

				if(!repetida) {
					this.agregarLineaSolicitudSuministro(lineasOrdenCompra, cadaLineaSolSum);
					solSuministro.add(cadaObject);
				}
			}

			this.getElementoPila().getObjetos().set(2, lineasOrdenCompra);
			this.getElementoPila().getObjetos().set(3, solSuministro);
		}
		try {
			ind = 0;
			ordenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(0, OrdenCompra.class);

			lineasOrdenCompra = (List) this.obtenerObjetoDelElementoPila(2, List.class);
			solSuministro = (List) this.obtenerObjetoDelElementoPila(3, List.class);
			firmas = (List) this.obtenerObjetoDelElementoPila(4, List.class);
			proveedorNuevo = (Proveedor) this.obtenerObjetoDelElementoPila(6, Proveedor.class);

		} catch(Exception ex) {
			log("Error Description", ex);
			ex.printStackTrace();
		}
		proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);

		if(ordenCompra.getNumero() != null) {
			this.getTfNumero().setText(ordenCompra.getNumero().toString());
		}

		this.getTfFecha().setText(Conversor.getStringDeFechaCorta(ordenCompra.getFechaEmision()));
		this.getTaDescripcion().setText(ordenCompra.getDescripcion());

		if(proveedor != null) {
			this.getTfProveedor().setText(proveedor.getRazonSocial());
		}

		if(proveedorNuevo != null) {
			this.getTfNuevoProveedor().setText(proveedorNuevo.getRazonSocial());
		}

		if(lineasOrdenCompra != null && !lineasOrdenCompra.isEmpty()) {
			for(int i = 0; i < lineasOrdenCompra.size(); i++) {
				LineaOrdenCompra cadaLineaOrdenCompra = (LineaOrdenCompra) lineasOrdenCompra.get(i);
				if(cadaLineaOrdenCompra != null) {
					this.getTfPrecioUnitario().setText(cadaLineaOrdenCompra.getMontoUnitario());
				}
				montoTotal += cadaLineaOrdenCompra.getMontoTotal().doubleValue();
			}
			this.getStTotal().setText(new Double(montoTotal));
		}

		this.getObjectListDataProvider().setList(lineasOrdenCompra);
		this.setListaDelCommunication(lineasOrdenCompra);

		this.getObjectListDataProviderSolSum().setList(solSuministro);
		this.setListaDelCommunicationSolSum(solSuministro);

		if(ordenCompra.getListaLineasMovimientoMercaderia().size() > 0) {
			this.getObjectListDataProviderMovimientoMercaderia().setList(ordenCompra.getListaLineasMovimientoMercaderia());
			this.setListaDelCommunicationMovimientoMercaderia(ordenCompra.getListaLineasMovimientoMercaderia());
		}

		this.getObjectListDataProviderAutorizaciones().setList(firmas);

		this.getObjectListDataProviderTransferencia().setList(ordenCompra.getListaTransferencias());

		this.setListaDelCommunicationPagos(ordenCompra.getListaPagosOrdenCompra());
		this.getObjectListDataProviderPagos().setList(ordenCompra.getListaPagosOrdenCompra());

		this.getLdpFirmas().setList(ordenCompra.getListaFirmaPermisos());
	}

	protected ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLineasOrdenCompra();
	}

	private ObjectListDataProvider getObjectListDataProviderSolSum() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLineasSoSuministro();
	}

	private ObjectListDataProvider getObjectListDataProviderAutorizaciones() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpFirmaPermiso();
	}

	private ObjectListDataProvider getObjectListDataProviderTransferencia() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpTransferenciaOrdenCompra();
	}

	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaLineasOrdenesCompra();
	}

	protected List getListaDelCommunicationPagos() {
		return this.getCommunicationComprasBean().getListaPagosOrdenCompra();
	}

	protected void setListaDelCommunicationPagos(List listaPagos) {
		this.getCommunicationComprasBean().setListaPagosOrdenCompra(listaPagos);
	}

	protected List getListaDelCommunicationSolSum() {
		return this.getCommunicationComprasBean().getListaLineasSolSumOrdenCompra();
	}

	protected void setListaDelCommunicationSolSum(List lista) {
		this.getCommunicationComprasBean().setListaLineasSolSumOrdenCompra(lista);
	}

	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaLineasOrdenesCompra(lista);
	}

	protected ObjectListDataProvider getObjectListDataProviderMovimientoMercaderia() {
		return this.getLdpMovimientoMercaderia();
	}

	protected List getListaDelCommunicationMovimientoMercaderia() {
		return this.getCommunicationComprasBean().getListaMovimientoMercaderiaOrdenCompra();
	}

	protected void setListaDelCommunicationMovimientoMercaderia(List lista) {
		this.getCommunicationComprasBean().setListaMovimientoMercaderiaOrdenCompra(lista);
	}

	public String getCurrentRow() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public String getCurrentRowPagos() {
		return tableRowGroupPagos.getRowKey().getRowId();
	}

	public void setCurrentRowPagos(int row) {
	}

	public Object getRBSelected() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public Object getRBSelectedPagos() {
		String sv = (String) radioButton1Pagos.getSelectedValue();
		return sv.equals(lastSelectedPagos) ? sv : null;
	}

	public void setRBSelectedPagos(Object selected) {
		if(selected != null) {
			lastSelectedPagos = selected;
		}
	}

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
			;

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

	public RowKey getSeleccionadoPago() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupPago");
			rk = this.getObjectListDataProviderPagos().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public String btnAgregarLineaOrden_action() {

		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		this.guardarEstadoObjetosUsados();
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);
		if(proveedor.getIdProveedor() == -1) {
			warn("Debe seleccionar un proveedor para realizar esta acci\363n.");
			this.getTfProveedor().setValid(false);
			retorno = null;
		} else {
			if(ultimo) {

				this.getRequestBean1().setObjetoABM(proveedor);
				this.getRequestBean1().setTipoSeleccion("MULTIPLE");
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				retorno = "AgregarLineaOrdenCompra";
			} else {
				retorno = this.prepararCaducidad();
			}
		}

		return retorno;
	}

	public String btnQuitar_action() {
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

	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {
				this.getListaDelCommunication().clear();
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarProveedor_action() {
		return navegarParaSeleccionar("AdminProveedor", 1);
	}

	public String btnLimpiarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfProveedor());
			this.getElementoPila().getObjetos().set(4, null);
			this.getObjectListDataProvider().clearObjectList();
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarNuevoProveedor_action() {
		return navegarParaSeleccionar("AdminProveedor", 6);
	}

	public String btnLimpiarNuevoProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(6, this.getTfNuevoProveedor());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMOrdenCompra";
	}

	private void agregarLineaSolicitudSuministro(List pListaLineasOrdenCompra, LineaSolicitudSuministro pLineaSolicitud) {

		boolean encontrado = false;
		for(Object cadaLinea : pListaLineasOrdenCompra) {

			LineaOrdenCompra linea = (LineaOrdenCompra) cadaLinea;

			if(linea.getBien().equals(pLineaSolicitud.getBien())) {
				linea.setCantidad(linea.getCantidad() + pLineaSolicitud.getCantidad());
				// linea.getListaLineasSolicitudSuministro().add(pLineaSolicitud);
				encontrado = true;
			}
		}

		if(!encontrado) {

			LineaOrdenCompra lineaOrdCompra = new LineaOrdenCompra();
			// lineaOrdCompra.getListaLineasSolicitudSuministro().add(pLineaSolicitud);

			lineaOrdCompra.setBien(pLineaSolicitud.getBien());
			lineaOrdCompra.setCantidad(pLineaSolicitud.getCantidad());
			lineaOrdCompra.setMontoUnitario(0.00);

			pListaLineasOrdenCompra.add(lineaOrdCompra);

		}
	}

	public String btnGenerarPagos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			OrdenCompra locOrden = (OrdenCompra) this.obtenerObjetoDelElementoPila(0);

			Object objectEntregaInicial = this.getTfEntregaInicial().getText();
			Double entregaInicial = null;
			if(objectEntregaInicial != null && !objectEntregaInicial.toString().isEmpty()) {
				entregaInicial = Double.valueOf(objectEntregaInicial.toString());
			}

			Object objectCantidadCuotas = this.getTfCantidadCuotas().getText();
			if(objectCantidadCuotas != null && !objectCantidadCuotas.toString().isEmpty()) {
				Integer cantidadCuotas = Integer.valueOf(objectCantidadCuotas.toString());
				try {
					locOrden.generarPagosPorCantidadCuotas(entregaInicial, cantidadCuotas);
				} catch(Exception ex) {
					log(ex.getMessage());
					error(ex.getMessage());
				}
			} else {
				Object stringValorCuota = this.getTfValorCuota().getText();
				if(stringValorCuota != null && stringValorCuota != "") {
					Double valorCuota = Double.valueOf(stringValorCuota.toString());
					boolean valorFijo = this.getRbValorCuotaFijo().isChecked();
					try {
						if(valorFijo) {
							locOrden.generarPagosPorValorCuota(entregaInicial, valorCuota);
						} else {
							locOrden.generarPagosPorPorcentajeCuota(entregaInicial, valorCuota);
						}
					} catch(Exception e) {
						e.printStackTrace();
						error(e.getMessage());
					}
				}
			}
			this.getElementoPila().getObjetos().set(0, locOrden);
			this.setListaDelCommunicationPagos(locOrden.getListaPagosOrdenCompra());
			this.getObjectListDataProviderPagos().setList(this.getListaDelCommunicationPagos());
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarPago_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			OrdenCompra locOrden = (OrdenCompra) this.obtenerObjetoDelElementoPila(0);
			locOrden.generarPago(null, null);
			this.getElementoPila().getObjetos().set(0, locOrden);
			this.setListaDelCommunicationPagos(locOrden.getListaPagosOrdenCompra());
			this.getObjectListDataProviderPagos().setList(this.getListaDelCommunicationPagos());
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarPago_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionadoPago();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderPagos().getObjects()[index];
					PagoOrdenCompra locPago = (PagoOrdenCompra) obj;
					if(locPago.getFactura() != null) {
						error("El Pago no se puede quitar por que ya fue asociado a una Factura");
					} else {
						this.getListaDelCommunicationPagos().remove(obj);
					}
				}
			} catch(Exception ex) {
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public boolean getReadOnly() {
		PagoOrdenCompra locPago = (PagoOrdenCompra) this.getObjectListDataProviderPagos().getList().get(Integer.parseInt(this.getCurrentRowPagos()));
		if(locPago.getFactura() != null && locPago.getFactura().getIdFactura() != -1) {
			return true;
		}
		return false;
	}

	public void setReadOnly(boolean disabled) {
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// if (pObject instanceof Proveedor) {
		// try {
		//
		//
		// if (this.obtenerObjetoDelElementoPila(5, String.class) != null
		// && this.obtenerObjetoDelElementoPila(5,
		// String.class).equals("ProveedorNuevo")) {
		// Proveedor proveedorNuevo = (Proveedor) pObject;
		// proveedorNuevo =
		// this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores()
		// .findProveedorByID(proveedorNuevo.getIdProveedor());
		// this.getElementoPila().getObjetos().set(6, proveedorNuevo);
		//
		// } else if (this.obtenerObjetoDelElementoPila(5, String.class) != null
		// && this.obtenerObjetoDelElementoPila(5,
		// String.class).equals("ProveedorActual")) {
		// Proveedor proveedor = (Proveedor) pObject;
		// proveedor =
		// this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores()
		// .findProveedorByID(proveedor.getIdProveedor());
		// this.getElementoPila().getObjetos().set(1, proveedor);
		// this.getElementoPila().getObjetos().set(2, null);
		// this.getObjectListDataProvider().clearObjectList();
		// }
		// } catch (Exception ex) {
		// log("Error Description", ex);
		// }
		// }
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		OrdenCompra ordenCompra = (OrdenCompra) pObject;

		Proveedor proveedor = ordenCompra.getProveedor();
		List lineasOrdenCompra = ordenCompra.getListaLineaOrdenCompra();
		List solSuministro = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		List autorizaciones = (List) this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		List firmas = new ArrayList();

		for(Iterator<LineaOrdenCompra> it = lineasOrdenCompra.iterator(); it.hasNext();) {
			LineaOrdenCompra linea = it.next();
			solSuministro.addAll(linea.getLineaContratacion().getListaLineasSolicitudSuministro());
		}
		if(ordenCompra.getListaFirmaPermisos() != null) {
			autorizaciones.addAll(ordenCompra.getListaFirmaPermisos());
			for(int x = 0; x < autorizaciones.size(); x++) {
				AutorizacionOrdenCompra autorizacion = (AutorizacionOrdenCompra) autorizaciones.get(x);
				FirmaPermiso firma = autorizacion.getFirmaPermiso();
				firmas.add(firma);
			}
		}

		this.getElementoPila().getObjetos().set(0, ordenCompra);
		this.getElementoPila().getObjetos().set(1, proveedor);

		this.getElementoPila().getObjetos().set(2, lineasOrdenCompra);
		this.getElementoPila().getObjetos().set(3, solSuministro);
		this.getElementoPila().getObjetos().set(4, firmas);
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMOrdenCompra$ABMOrdenCompra}";
	}

	@Override
	public long getSerialVersionUID() {
		return OrdenCompra.serialVersionUID;
	}
}