/*
 * ABMFacturaServicio.java
 *
 * Created on 11 de febrero de 2009, 08:00
 * Copyright Trascender SRL
 * 
 * Marcos
 */
package muni.compras.ABMFacturaServicio;

import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import muni.CommunicationMesaEntradaBean;

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
import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
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
public class ABMFacturaServicio extends ABMPageBean {

	private StaticText stPrecio = new StaticText();
	private StaticText stSeparador1 = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stTotal = new StaticText();
	private StaticText stBienProvisto = new StaticText();
	private StaticText stCuenta = new StaticText();

	private TextField tfProveedor = new TextField();
	private TextField tfCantidad = new TextField();
	private TextField tfBien = new TextField();
	private TextField tfTotal = new TextField();
	private TextField tfNumeroFactura = new TextField();
	private TextField tfMontoTotal = new TextField();
	private TextField tfCodigoProveedor = new TextField();
	private TextField tfFecha = new TextField();

	private Label lblProveedor = new Label();
	private Label lblTotalF = new Label();
	private Label lblNumeroFactura = new Label();
	private Label lblLineasFactura = new Label();
	private Label labelValor = new Label();
	private Label labelConcepto = new Label();
	private Label lblFecha = new Label();

	private Button btnAgregarLinea = new Button();
	private Button btnQuitar = new Button();
	private Button btnQuitarTodos = new Button();
	private Button btnSeleccionarProveedor = new Button();
	private Button btnSeleccionarBien = new Button();
	private HtmlAjaxCommandButton btnLimpiarBien = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarProveedor = new HtmlAjaxCommandButton();

	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcPrecio = new TableColumn();
	private TableColumn tcCuentaAsociada = new TableColumn();
	private TableColumn tcBienProvisto = new TableColumn();
	private TableColumn tcCantidad = new TableColumn();
	private TableColumn tcTotal = new TableColumn();

	private ObjectListDataProvider ldpLineasFactura = new ObjectListDataProvider();
	private Table table1 = new Table();
	private SingleSelectOptionsList ddTipoFacturaDefaultOptions = new SingleSelectOptionsList();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private RadioButton radioButton1 = new RadioButton();
	private DropDown ddTipoFactura = new DropDown();
	private PanelGroup groupPanel1 = new PanelGroup();
	private NumberConverter numberConverter1 = new NumberConverter();

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(FacturaServicio.TipoFactura.values(), "may");
		this.ddTipoFacturaDefaultOptions.setOptions(op);
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		// if (this.getListaDelCommunication() != null) {
		// List locList = new ArrayList(this.getListaDelCommunication());
		// this.getObjectListDataProvider().setList(locList);
		// }
		numberConverter1.setPattern("$ #,##0.00");
		numberConverter1.setMinIntegerDigits(1);
		numberConverter1.setMaxIntegerDigits(40);
		numberConverter1.setMaxFractionDigits(3);
	}

	public Label getLblLineasFactura() {
		return lblLineasFactura;
	}

	public void setLblLineasFactura(Label lblLineasFactura) {
		this.lblLineasFactura = lblLineasFactura;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
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

	public TextField getTfCantidad() {
		return tfCantidad;
	}

	public void setTfCantidad(TextField tf) {
		this.tfCantidad = tf;
	}

	public TextField getTfBien() {
		return tfBien;
	}

	public void setTfBien(TextField tf) {
		this.tfBien = tf;
	}

	public Label getLblNumeroFactura() {
		return lblNumeroFactura;
	}

	public void setLblNumeroFactura(Label lblNumeroFactura) {
		this.lblNumeroFactura = lblNumeroFactura;
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

	public Label getLblTotalF() {
		return lblTotalF;
	}

	public void setLblTotalF(Label lblTotalF) {
		this.lblTotalF = lblTotalF;
	}

	public ObjectListDataProvider getLdpLineasFactura() {
		return ldpLineasFactura;
	}

	public void setLdpLineasFactura(ObjectListDataProvider oldp) {
		this.ldpLineasFactura = oldp;
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

	public TableColumn getTcPrecio() {
		return tcPrecio;
	}

	public void setTcPrecio(TableColumn tcPrecio) {
		this.tcPrecio = tcPrecio;
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

	public Button getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public void setBtnSeleccionarProveedor(Button b) {
		this.btnSeleccionarProveedor = b;
	}

	public Button getBtnSeleccionarBien() {
		return btnSeleccionarBien;
	}

	public void setBtnSeleccionarBien(Button b) {
		this.btnSeleccionarBien = b;
	}
	
	public HtmlAjaxCommandButton getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(HtmlAjaxCommandButton btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
	}

	public HtmlAjaxCommandButton getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(HtmlAjaxCommandButton btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
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

	public TextField getTfNumeroFactura() {
		return tfNumeroFactura;
	}

	public void setTfNumeroFactura(TextField tfNumeroFactura) {
		this.tfNumeroFactura = tfNumeroFactura;
	}

	public TextField getTfTotal() {
		return tfTotal;
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

	public ABMFacturaServicio() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new FacturaServicio());
		ep.getObjetos().add(ind++, new Proveedor());
		ep.getObjetos().add(ind++, new Bien());
		ep.getObjetos().add(ind++, new ArrayList()); // para el getLineaFactura

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		FacturaServicio facturaServicio = (FacturaServicio) this.obtenerObjetoDelElementoPila(ind++, FacturaServicio.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
		Bien bien = (Bien) this.obtenerObjetoDelElementoPila(ind++, Bien.class);

		FacturaServicio.TipoFactura tipoFactura = null;

		// Object montoTotal = this.getTfMontoTotal().getText();
		Object tipoFacturaSelected = this.ddTipoFactura.getSelected();
		Object codigoProveedor = this.tfCodigoProveedor.getText();
		Object numero = this.getTfNumeroFactura().getText();
		Object fecha = tfFecha.getText();
		// no se pondra un DdEstado ya que al agregar una factura por defecto
		// sera con el estado CREADA, PAGADA aparecera en ModificarFactura

		if (codigoProveedor != null && !codigoProveedor.toString().trim().equals("")) {
			facturaServicio.setCodigoProveedor(codigoProveedor.toString());
		} else {
			facturaServicio.setCodigoProveedor(null);
		}

		if ((tipoFacturaSelected != null) && tipoFacturaSelected.toString().length() > 0) {
			tipoFactura = FacturaServicio.TipoFactura.valueOf(tipoFacturaSelected.toString());
		} else {
			tipoFactura = null;
		}

		if (numero != null && numero != "") {
			facturaServicio.setNumero(numero.toString());
		} else {
			facturaServicio.setNumero(null);
		}

		if (fecha != null && fecha != "") {
			facturaServicio.setFechaEmision(Conversor.getFechaCortaDeString(fecha.toString()));
		} else {
			facturaServicio.setFechaEmision(null);
		}

		this.getObjectListDataProvider().commitChanges();
		ArrayList listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

		if (listaLineaFactura.isEmpty()) {
			listaLineaFactura = null;
		}

		facturaServicio.setListaLineaFactura(listaLineaFactura);
//		 facturaContrato.setListaOrdenesDeCompra(listaOrdenesCompra);

		try {
			// calculo de los montos totales por cada una de las lineas de la
			// factura
			facturaServicio.setTotal(null); // se calcula desde la logica

		} catch (Exception e) {
		}

		facturaServicio.setProveedor(proveedor);

		if (bien.getIdBien() == -1) {
			bien = null;
		}
		facturaServicio.setBien(bien);
		if (tipoFactura != null) {
			facturaServicio.setTipoFactura(tipoFactura);
		}
		// TODO

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, facturaServicio);
		this.getElementoPila().getObjetos().set(ind++, proveedor);
		this.getElementoPila().getObjetos().set(ind++, bien);
		this.getElementoPila().getObjetos().set(ind++, listaLineaFactura);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		FacturaServicio facturaServicio = null;
		Proveedor proveedor = null;
		Bien bien = null;
		FacturaServicio.TipoFactura tipoFactura = null;
		ArrayList listaLineaFactura = null;

		try {
			ind = 0;
			facturaServicio = (FacturaServicio) this.obtenerObjetoDelElementoPila(ind++, FacturaServicio.class);
			proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
			bien = (Bien) this.obtenerObjetoDelElementoPila(ind++, Bien.class);
			listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		} catch (Exception e) {
			log("Error Description: ", e);
			e.printStackTrace();
		}

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

		this.getTfFecha().setText(Conversor.getStringDeFechaCorta(facturaServicio.getFechaEmision()));
		this.getTfCodigoProveedor().setText(facturaServicio.getCodigoProveedor());
		if (facturaServicio.getNumero() != null)
			this.getTfNumeroFactura().setText(facturaServicio.getNumero());

		if (bien != null && bien.getIdBien() != -1) {
			this.getTfBien().setText(bien.toString());
		}
		this.getTfProveedor().setText(proveedor);
		this.getDdTipoFactura().setSelected(Util.getEnumNameFromString(String.valueOf(facturaServicio.getTipoFactura())));
		this.getDdTipoFacturaDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(facturaServicio.getTipoFactura())));

		this.getObjectListDataProvider().setList(listaLineaFactura);
		this.setListaDelCommunication(listaLineaFactura);

	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLineasFactura();
	}

	public ArrayList getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaLineasFacturaServicio();
	}

	private void setListaDelCommunication(ArrayList lista) {
		this.getCommunicationComprasBean().setListaLineasFacturaServicio(lista);
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

	private ArrayList crearListaLineaFactura(ArrayList list, FacturaServicio facturaServicio) {
		Iterator iterator = list.iterator();
		LineaFactura locLineaFactura;
		ArrayList locListaLineaFactura = new ArrayList();
		while (iterator.hasNext()) {
			locLineaFactura = (LineaFactura) iterator.next();

			if (locLineaFactura.getCantidad() != null && locLineaFactura.getCantidad().doubleValue() != 0) {
				locLineaFactura.setFactura(facturaServicio);
				locListaLineaFactura.add(locLineaFactura);
			}
		}

		return locListaLineaFactura;
	}

	public String btnSeleccionarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 1;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminProveedor";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfProveedor());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
		return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
	}

	public String btnSeleccionarBien_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 2;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminBien";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarBien_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(2, this.getTfBien());
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
		/*if (proveedor.getIdProveedor() == -1) {
			warn("Seleccione un Proveedor para listar sus Bienes.");
			this.getTfProveedor().setValid(false);
			retorno = null;
		} else { */
			if (ultimo) {
				this.guardarEstadoObjetosUsados();

				this.getRequestBean1().setObjetoABM(proveedor);
				// this.getRequestBean1().setTipoSeleccion("MULTIPLE");
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

				retorno = "AgregarLineaFacturaProveedor";
			} else {
				retorno = this.prepararCaducidad();
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
		return "ABMFacturaServicio";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Proveedor proveedor = null;
		Bien bien = null;
		ArrayList listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if (seleccionado instanceof Proveedor) {
			try {
				proveedor = (Proveedor) seleccionado;
				this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(this.getSessionBean1().getLlave());
				proveedor = this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores()
						.findProveedorByID(proveedor.getIdProveedor());
				this.getElementoPila().getObjetos().set(1, proveedor);

			} catch (Exception ex) {
				log("Error Description", ex);
			}
		}
		if (seleccionado instanceof Bien) {
			try {
				FacturaServicio locFacturaServicio = (FacturaServicio) this.obtenerObjetoDelElementoPila(0, FacturaServicio.class);
				bien = (Bien) seleccionado;
				bien = this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findBienByID(bien.getIdBien());
				locFacturaServicio.setBien(bien);

				this.getElementoPila().getObjetos().set(0, locFacturaServicio);
				this.getElementoPila().getObjetos().set(2, bien);

			} catch (Exception ex) {
				log("Error Description", ex);
			}
		}
		if (seleccionado instanceof ArrayList) {
			try {
				ArrayList locListado = (ArrayList) seleccionado;
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
		FacturaServicio facturaServicio = null;
		Proveedor proveedor = null;
		Bien bien = null;
		ArrayList listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
		
		facturaServicio = (FacturaServicio) this.getRequestBean1().getObjetoABM();
		try {
			this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().setLlave(this.getSessionBean1().getLlave());
			facturaServicio = this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio()
					.getFacturaServicioPorId(facturaServicio.getIdFactura());
		} catch (Exception e) {

		}
		proveedor = null;
		listaLineaFactura = null;
		if (facturaServicio.getIdFactura() != -1) {
			try {
				proveedor = facturaServicio.getProveedor();
				bien = facturaServicio.getBien();
				listaLineaFactura = new ArrayList(facturaServicio.getListaLineaFactura());
			} catch (Exception ex) {
				log(getCasoNavegacion() + "_GuardarError:", ex);
			}
		}

		if (facturaServicio.getListaLineaFactura() != null && !facturaServicio.getListaLineaFactura().isEmpty()
				&& facturaServicio.getListaLineaFactura().size() > 0) {
			for (int i = 0; i < facturaServicio.getListaLineaFactura().size(); i++) {
				LineaFactura cadaLineaFactura = (LineaFactura) facturaServicio.getListaLineaFactura().get(i);
				try {
					cadaLineaFactura.setTotal(null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			facturaServicio.setTotal(null);
		}

		this.getElementoPila().getObjetos().set(0, facturaServicio);
		this.getElementoPila().getObjetos().set(1, proveedor);
		this.getElementoPila().getObjetos().set(2, bien);
		this.getElementoPila().getObjetos().set(3, listaLineaFactura);
	}
	
	@Override
	public long getSerialVersionUID() {
		return FacturaServicio.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMFacturaServicio$ABMFacturaServicio}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		FacturaServicio locFacturaServicio = this.obtenerObjetoDelElementoPila(0, FacturaServicio.class);
		this.getTablaLogs().getLdpLogs().setList(locFacturaServicio.getListaLogsAuditoria());
	}
}
