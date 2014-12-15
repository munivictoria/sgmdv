/*
 * ABMFacturaContrato.java
 *
 * Created on 11 de febrero de 2009, 08:00
 * Copyright Trascender SRL
 * 
 * Marcos
 */
package muni.compras.ABMFacturaContrato;

import java.util.ArrayList;
import java.util.Iterator;

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
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMFacturaContrato extends ABMPageBean {

	private StaticText stPrecio = new StaticText();
	private StaticText stSeparador1 = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stTotal = new StaticText();
	private StaticText stBienProvisto = new StaticText();
	private StaticText stCuenta = new StaticText();

	private TextField tfProveedor = new TextField();
	private TextField tfCantidad = new TextField();
	private TextField tfLineaCantidad = new TextField();
	private TextField tfContrato = new TextField();
	private TextField tfNumeroFactura = new TextField();
	private TextField tfTotal = new TextField();
	private TextField tfLineaProveedor = new TextField();
	private TextField tfBienProvisto = new TextField();
	private TextField tfCuenta = new TextField();
	private TextField tfMonto = new TextField();
	private TextField tfMontoTotal = new TextField();
	private TextField tfCodigoProveedor = new TextField();
	private TextField tfFecha = new TextField();

	private Label lblProveedor = new Label();
	private Label lblTotalF = new Label();
	private Label lblNumeroFactura = new Label();
	private Label lblLineaFactura = new Label();
	private Label lblLineaProveedor = new Label();
	private Label lblBienProvisto = new Label();
	private Label labelValor = new Label();
	private Label lblCuenta = new Label();
	private Label lblMonto = new Label();
	private Label lblCantidad = new Label();
	private Label labelConcepto = new Label();
	private Label lblFecha = new Label();

	private Button btnAgregarLinea = new Button();
	private Button btnQuitar = new Button();
	private Button btnQuitarTodos = new Button();
	private Button btnSeleccionarCuenta = new Button();
	private Button btnLimpiarCuenta = new Button();
	private Button btnAgregarLineaFactura = new Button();
	private Button btnSeleccionarProveedor = new Button();
	private Button btnLimpiarBien = new Button();
	private Button btnSeleccionarBienProvisto = new Button();
	private Button btnSeleccionarContrato = new Button();
	private HtmlAjaxCommandButton btnLimpiarContrato = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarProveedor = new HtmlAjaxCommandButton();

	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcCuentaAsociada = new TableColumn();
	private TableColumn tcBienProvisto = new TableColumn();
	private TableColumn tcCantidad = new TableColumn();
	private TableColumn tcTotal = new TableColumn();
	private TableColumn tcPrecio = new TableColumn();

	private Table table1 = new Table();
	private SingleSelectOptionsList ddTipoFacturaDefaultOptions = new SingleSelectOptionsList();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private RadioButton radioButton1 = new RadioButton();
	private DropDown ddTipoFactura = new DropDown();
	private PanelGroup groupPanel1 = new PanelGroup();
	private ObjectListDataProvider ldpLineaFactura = new ObjectListDataProvider();
	private NumberConverter numberConverter1 = new NumberConverter();

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(FacturaContrato.TipoFactura.values(), "may");
		this.ddTipoFacturaDefaultOptions.setOptions(op);
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		numberConverter1.setPattern("$ #,##0.00");
		numberConverter1.setMinIntegerDigits(1);
		numberConverter1.setMaxIntegerDigits(40);
		numberConverter1.setMaxFractionDigits(3);
	}
	
	public HtmlAjaxCommandButton getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public HtmlAjaxCommandButton getBtnLimpiarContrato() {
		return btnLimpiarContrato;
	}

	public void setBtnLimpiarContrato(HtmlAjaxCommandButton btnLimpiarContrato) {
		this.btnLimpiarContrato = btnLimpiarContrato;
	}

	public void setBtnLimpiarProveedor(HtmlAjaxCommandButton btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public StaticText getStPrecio() {
		return stPrecio;
	}

	public void setStPrecio(StaticText st) {
		this.stPrecio = st;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tf) {
		this.tfProveedor = tf;
	}

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public TextField getTfLineaCantidad() {
		return tfLineaCantidad;
	}

	public void setTfLineaCantidad(TextField tfLineaCantidad) {
		this.tfLineaCantidad = tfLineaCantidad;
	}

	public TextField getTfCantidad() {
		return tfCantidad;
	}

	public void setTfCantidad(TextField tf) {
		this.tfCantidad = tf;
	}

	public TextField getTfNumeroFactura() {
		return tfNumeroFactura;
	}

	public void setTfNumeroFactura(TextField tfNumeroFactura) {
		this.tfNumeroFactura = tfNumeroFactura;
	}

	public TextField getTfContrato() {
		return tfContrato;
	}

	public void setTfContrato(TextField tf) {
		this.tfContrato = tf;
	}

	public Label getLblBienProvisto() {
		return lblBienProvisto;
	}

	public void setLblBienProvisto(Label lblBienProvisto) {
		this.lblBienProvisto = lblBienProvisto;
	}

	public Label getLblLineaProveedor() {
		return lblLineaProveedor;
	}

	public void setLblLineaProveedor(Label lblLineaProveedor) {
		this.lblLineaProveedor = lblLineaProveedor;
	}

	public Label getLblLineaFactura() {
		return lblLineaFactura;
	}

	public void setLblLineaFactura(Label lblLineaFactura) {
		this.lblLineaFactura = lblLineaFactura;
	}

	public Label getLblNumeroFactura() {
		return lblNumeroFactura;
	}

	public void setLblNumeroFactura(Label lblNumeroFactura) {
		this.lblNumeroFactura = lblNumeroFactura;
	}

	public Label getLblTotalF() {
		return lblTotalF;
	}

	public void setLblTotalF(Label lblTotalF) {
		this.lblTotalF = lblTotalF;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public Button getBtnAgregarLineaFactura() {
		return btnAgregarLineaFactura;
	}

	public void setBtnAgregarLineaFactura(Button btnAgregarLineaFactura) {
		this.btnAgregarLineaFactura = btnAgregarLineaFactura;
	}

	public Button getBtnLimpiarCuenta() {
		return btnLimpiarCuenta;
	}

	public void setBtnLimpiarCuenta(Button btnLimpiarCuenta) {
		this.btnLimpiarCuenta = btnLimpiarCuenta;
	}

	public Button getBtnSeleccionarCuenta() {
		return btnSeleccionarCuenta;
	}

	public void setBtnSeleccionarCuenta(Button btnSeleccionarCuenta) {
		this.btnSeleccionarCuenta = btnSeleccionarCuenta;
	}

	public Button getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(Button btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public Button getBtnAgregarLinea() {
		return btnAgregarLinea;
	}

	public void setBtnAgregarLinea(Button btnAgregarLinea) {
		this.btnAgregarLinea = btnAgregarLinea;
	}

	public Button getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(Button btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public SingleSelectOptionsList getDdTipoFacturaDefaultOptions() {
		return ddTipoFacturaDefaultOptions;
	}

	public void setDdTipoFacturaDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoFacturaDefaultOptions = ssol;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	public TableColumn getTcCuentaAsociada() {
		return tcCuentaAsociada;
	}

	public void setTcCuentaAsociada(TableColumn tcCuentaAsociada) {
		this.tcCuentaAsociada = tcCuentaAsociada;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	public TableColumn getTcBienProvisto() {
		return tcBienProvisto;
	}

	public void setTcBienProvisto(TableColumn tcBienProvisto) {
		this.tcBienProvisto = tcBienProvisto;
	}

	public StaticText getStCuenta() {
		return stCuenta;
	}

	public void setStCuenta(StaticText stCuenta) {
		this.stCuenta = stCuenta;
	}

	public StaticText getStBienProvisto() {
		return stBienProvisto;
	}

	public void setStBienProvisto(StaticText stBienProvisto) {
		this.stBienProvisto = stBienProvisto;
	}

	public Button getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(Button btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
	}

	public Button getBtnSeleccionarBienProvisto() {
		return btnSeleccionarBienProvisto;
	}

	public void setBtnSeleccionarBienProvisto(Button btnSeleccionarBienProvisto) {
		this.btnSeleccionarBienProvisto = btnSeleccionarBienProvisto;
	}

	public Button getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public void setBtnSeleccionarProveedor(Button b) {
		this.btnSeleccionarProveedor = b;
	}

	public Button getBtnSeleccionarContrato() {
		return btnSeleccionarContrato;
	}

	public void setBtnSeleccionarContrato(Button b) {
		this.btnSeleccionarContrato = b;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public TableColumn getTcPrecio() {
		return tcPrecio;
	}

	public void setTcPrecio(TableColumn tcPrecio) {
		this.tcPrecio = tcPrecio;
	}

	public TableColumn getTcTotal() {
		return tcTotal;
	}

	public void setTcTotal(TableColumn tcTotal) {
		this.tcTotal = tcTotal;
	}

	public StaticText getStTotal() {
		return stTotal;
	}

	public void setStTotal(StaticText st) {
		this.stTotal = st;
	}

	public DropDown getDdTipoFactura() {
		return ddTipoFactura;
	}

	public void setDdTipoFactura(DropDown dd) {
		this.ddTipoFactura = dd;
	}

	public TextField getTfMonto() {
		return tfMonto;
	}

	public void setTfMonto(TextField tfMonto) {
		this.tfMonto = tfMonto;
	}

	public TextField getTfCuenta() {
		return tfCuenta;
	}

	public void setTfCuenta(TextField tfCuenta) {
		this.tfCuenta = tfCuenta;
	}

	public TextField getTfBienProvisto() {
		return tfBienProvisto;
	}

	public void setTfBienProvisto(TextField tfBienProvisto) {
		this.tfBienProvisto = tfBienProvisto;
	}

	public TextField getTfLineaProveedor() {
		return tfLineaProveedor;
	}

	public void setTfLineaProveedor(TextField tfLineaProveedor) {
		this.tfLineaProveedor = tfLineaProveedor;
	}

	public TextField getTfTotal() {
		return tfTotal;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public void setTfTotal(TextField tf) {
		this.tfTotal = tf;
	}

	public TextField getTfMontoTotal() {
		return tfMontoTotal;
	}

	public void setTfMontoTotal(TextField tf) {
		this.tfMontoTotal = tf;
	}

	public TextField getTfCodigoProveedor() {
		return tfCodigoProveedor;
	}

	public void setTfCodigoProveedor(TextField tf) {
		this.tfCodigoProveedor = tf;
	}

	public Label getLblCantidad() {
		return lblCantidad;
	}

	public void setLblCantidad(Label lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	public Label getLblMonto() {
		return lblMonto;
	}

	public void setLblMonto(Label lblMonto) {
		this.lblMonto = lblMonto;
	}

	public Label getLblCuenta() {
		return lblCuenta;
	}

	public void setLblCuenta(Label lblCuenta) {
		this.lblCuenta = lblCuenta;
	}

	public Label getLabelValor() {
		return labelValor;
	}

	public void setLabelValor(Label lb) {
		this.labelValor = lb;
	}

	public Label getLabelConcepto() {
		return labelConcepto;
	}

	public void setLabelConcepto(Label lb) {
		this.labelConcepto = lb;
	}

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label lblFecha) {
		this.lblFecha = lblFecha;
	}

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tf) {
		this.tfFecha = tf;
	}

	public ObjectListDataProvider getLdpLineaFactura() {
		return ldpLineaFactura;
	}

	public void setLdpLineaFactura(ObjectListDataProvider oldp) {
		this.ldpLineaFactura = oldp;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new FacturaContrato()); // 0
		ep.getObjetos().add(ind++, new Proveedor()); // 1
		ep.getObjetos().add(ind++, new Contrato()); // 2
		ep.getObjetos().add(ind++, new ArrayList()); // 3 para el getLineaFacturaContrato

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		FacturaContrato facturaContrato = (FacturaContrato) this.obtenerObjetoDelElementoPila(ind++, FacturaContrato.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
		Contrato contrato = (Contrato) this.obtenerObjetoDelElementoPila(ind++, Contrato.class);
		ArrayList listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		
		facturaContrato.setTipoFactura(this.getDDEnumValue(getDdTipoFactura(), FacturaContrato.TipoFactura.class));
		facturaContrato.setFechaEmision(this.getTextFieldValueDate(getTfFecha()));
		facturaContrato.setNumero(this.getTextFieldValue(getTfNumeroFactura()));
		facturaContrato.setCodigoProveedor(this.getTextFieldValue(getTfCodigoProveedor()));

		// Linea de Factura

		if (proveedor.getIdProveedor() == -1) {
			proveedor = null;
		}
		facturaContrato.setProveedor(proveedor);
		if (contrato.getIdContrato() == -1) {
			contrato = null;
		}
		facturaContrato.setContrato(contrato);

		this.getObjectListDataProvider().commitChanges();
		

		if (listaLineaFactura.isEmpty()) {
			listaLineaFactura = null;
		}

		facturaContrato.setListaLineaFactura(listaLineaFactura);
		try {
			// calculo de los montos totales por cada una de las lineas de la
			// factura
			facturaContrato.setTotal(null); // se calcula desde la logica

		} catch (Exception e) {
		}

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, facturaContrato);
		this.getElementoPila().getObjetos().set(ind++, proveedor);
		this.getElementoPila().getObjetos().set(ind++, contrato);
		this.getElementoPila().getObjetos().set(ind++, listaLineaFactura);
	}

	private void agregarBienesProvistosALista(ArrayList list) {

		Proveedor proveedor = (Proveedor) this.getElementoPila().getObjetos().get(1);
		ArrayList locListaBienesProveedor = (ArrayList) proveedor.getListaBienProvisto();

		ArrayList listaLineaFactura = new ArrayList();
		boolean bandera = false;

		// variables del while
		LineaFactura locLineaFactura;
		Bien locBienProvistoProveedor;
		Bien auxBienProvisto;

		Iterator iteratorListaBienesProveedor = locListaBienesProveedor.iterator();

		while (iteratorListaBienesProveedor.hasNext()) {
			locLineaFactura = null;
			auxBienProvisto = null;
			locBienProvistoProveedor = (Bien) iteratorListaBienesProveedor.next();
			Iterator locIterator = list.iterator();

			while (locIterator.hasNext() && !bandera) {
				locLineaFactura = (LineaFactura) locIterator.next();
				// auxBienProvisto = locLineaFactura.getBien();

				if (auxBienProvisto.getIdBien() == locBienProvistoProveedor.getIdBien()) {
					bandera = true;
				}
			}

			if (!bandera) {
				// locLineaFactura = new LineaFactura();
				// locLineaFactura.setBien(locBienProvistoProveedor);
			}

			listaLineaFactura.add(locLineaFactura);
			bandera = false;
		}

		this.getElementoPila().getObjetos().set(3, listaLineaFactura);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		FacturaContrato facturaContrato = null;
		Proveedor proveedor = null;
		Contrato contrato = null;
		FacturaContrato.TipoFactura tipoFactura = null;
		ArrayList listaLineaFactura = null;

		try {
			facturaContrato = (FacturaContrato) this.obtenerObjetoDelElementoPila(0, FacturaContrato.class);
			proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);
			contrato = (Contrato) this.obtenerObjetoDelElementoPila(2, Contrato.class);
			listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		} catch (Exception e) {
			log("Error Description", e);
			e.printStackTrace();
		}

		if (facturaContrato.getFechaEmision() != null)
			this.getTfFecha().setText(Conversor.getStringDeFechaCorta(facturaContrato.getFechaEmision()));
		if (proveedor != null)
			this.getTfProveedor().setText(proveedor.getRazonSocial());
		if (contrato != null && contrato.getIdContrato() != -1)
			this.getTfContrato().setText(contrato);
		if (facturaContrato.getNumero() != null)
			this.getTfNumeroFactura().setText(facturaContrato.getNumero());
		if (facturaContrato.getCodigoProveedor() != null)
			this.getTfCodigoProveedor().setText(facturaContrato.getCodigoProveedor());

		  double total = 0.0;
		if (listaLineaFactura != null && !listaLineaFactura.isEmpty()) {

			for (int i = 0; i < listaLineaFactura.size(); i++) {
				LineaFactura cadaLineaFactura = (LineaFactura) listaLineaFactura.get(i);
				  total += cadaLineaFactura.getTotal().doubleValue();
				try {
					cadaLineaFactura.setTotal(null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			  this.getStTotal().setText(total);
		}
		this.getDdTipoFactura().setSelected(Util.getEnumNameFromString(String.valueOf(facturaContrato.getTipoFactura())));
		this.getDdTipoFacturaDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(facturaContrato.getTipoFactura())));

		this.getObjectListDataProvider().setList(listaLineaFactura);
		this.setListaDelCommunication(listaLineaFactura);

	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLineaFactura();
	}

	public ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaLineasFacturaContrato();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaLineasFacturaContrato(lista);
	}

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
		if (selected != null) {
			lastSelected = selected;
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

		if (rk != null) {
			while (!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while (!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if (!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;

					}
				}
				if (!encontrado) {
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

		if (cantFilas > 0) {
			// si hay al menos una fila
			if (posicionGlobal.longValue() >= cantFilas) {
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
		} catch (Exception ex) {
		}
		return rk;
	}

	private ArrayList crearListaLineaFactura(ArrayList list, FacturaContrato facturaContrato) {
		Iterator iterator = list.iterator();
		LineaFactura locLineaFactura;
		ArrayList locListaLineaFactura = new ArrayList();

		while (iterator.hasNext()) {
			locLineaFactura = (LineaFactura) iterator.next();

			if (locLineaFactura.getCantidad() != null && locLineaFactura.getCantidad().doubleValue() != 0) {

				locLineaFactura.setFactura(facturaContrato);
				locListaLineaFactura.add(locLineaFactura);
			}
		}

		return locListaLineaFactura;
	}

	public String btnSeleccionarProveedor_action() {
		return navegarParaSeleccionar("AdminProveedor", 1);
	}

	public String btnLimpiarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(1, this.getTfProveedor());
			this.getTfMontoTotal().setText("0.0");
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnSeleccionarContrato_action() {
		return navegarParaSeleccionar("AdminContrato", 2);
	}

	public String btnLimpiarContrato_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(2, this.getTfContrato());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarLinea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);
		if (proveedor.getIdProveedor() == -1) {
			warn("Seleccione un Proveedor para Listar sus Bienes.");
			this.getTfProveedor().setValid(false);
			retorno = null;
		} else {
			if (ultimo) {
				this.guardarEstadoObjetosUsados();

				this.getRequestBean1().setObjetoABM(proveedor);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				retorno = "AgregarLineaFacturaProveedor";
			} else {
				retorno = this.prepararCaducidad();
			}
		}

		return retorno;
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(obj);
				}
			} catch (Exception ex) {
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

		if (ultimo) {

			try {
				this.getListaDelCommunication().clear();
			} catch (Exception ex) {
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
		return "ABMFacturaContrato";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Proveedor) {
			try {
				Proveedor proveedor = (Proveedor) pObject;
				this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(this.getSessionBean1().getLlave());
				proveedor = this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores()
						.findProveedorByID(proveedor.getIdProveedor());
				this.getElementoPila().getObjetos().set(1, proveedor);
			} catch (Exception ex) {
				log("Error Description", ex);
			}
		}

		if (pObject instanceof Contrato) {
			try {
				Contrato contrato = (Contrato) pObject;
				this.getComunicationBean().getRemoteSystemContrato().setLlave(this.getSessionBean1().getLlave());
				contrato = this.getComunicationBean().getRemoteSystemContrato().getContratoPorId(contrato.getIdContrato());
				this.getElementoPila().getObjetos().set(2, contrato);
			} catch (Exception ex) {
				log("Error Description", ex);
			}
		}

		if (pObject instanceof ArrayList) {
			try {
				ArrayList listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
				
				ArrayList locListado = (ArrayList) pObject;
				for (int i = 0; i < locListado.size(); i++) {
					listaLineaFactura.add(locListado.get(i));
				}
				this.getElementoPila().getObjetos().set(3, listaLineaFactura);
				// o.O .... O.o
			} catch (Exception ex) {
				log("Error Description", ex);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		FacturaContrato facturaContrato = (FacturaContrato) pObject;
		Proveedor proveedor = null;
		Contrato contrato = null;
		ArrayList listaLineaFactura = null;
		if (facturaContrato.getIdFactura() != -1) {
			try {
				proveedor = facturaContrato.getProveedor();
				contrato = facturaContrato.getContrato();
				listaLineaFactura = new ArrayList(facturaContrato.getListaLineaFactura());
			} catch (Exception ex) {
				log(getCasoNavegacion() + "_GuardarError:", ex);
			}
		}

		if (facturaContrato.getListaLineaFactura() != null && !facturaContrato.getListaLineaFactura().isEmpty()
				&& facturaContrato.getListaLineaFactura().size() > 0) {
			for (int i = 0; i < facturaContrato.getListaLineaFactura().size(); i++) {
				LineaFactura cadaLineaFactura = (LineaFactura) facturaContrato.getListaLineaFactura().get(i);
				try {
					cadaLineaFactura.setTotal(null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			facturaContrato.setTotal(null);
		}

		this.getElementoPila().getObjetos().set(0, facturaContrato);
		this.getElementoPila().getObjetos().set(1, proveedor);
		this.getElementoPila().getObjetos().set(2, contrato);
		this.getElementoPila().getObjetos().set(3, listaLineaFactura);
	}
	
	@Override
	public long getSerialVersionUID() {
		return FacturaContrato.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMFacturaContrato$ABMFacturaContrato}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		FacturaContrato locFacturaContrato = this.obtenerObjetoDelElementoPila(0, FacturaContrato.class);
		this.getTablaLogs().getLdpLogs().setList(locFacturaContrato.getListaLogsAuditoria());
	}
}