/*
 * AgregarManzana.java
 *
 * Created on 2 de noviembre de 2006, 11:21
 * Copyright Trascender SRL
 */
package muni.compras.ABMFacturaProveedor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.LineaFacturaLineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaFacturaPagoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.PagoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class ABMFacturaProveedor extends ABMPageBean {

	protected static long idLineaOrdenCompraTemp = -1;
	protected StaticText stNombre = new StaticText();
	protected StaticText stFechaEmision = new StaticText();
	protected StaticText stSeparador2 = new StaticText();
	protected StaticText stSeparador3 = new StaticText();
	protected StaticText stTotal = new StaticText();
	protected StaticText stCuenta = new StaticText();
	protected TextField tfProveedor = new TextField();
	protected TextField tfNumeroFactura = new TextField();
	protected TextField tfFechaEmision = new TextField();
	protected TextField tfCantidad = new TextField();
	protected TextField tfTotal = new TextField();
	protected TextField tfCuentaRfr = new TextField();
	protected TextField tfPrecio = new TextField();
	protected Label lblNumeroFactura = new Label();
	protected Label lblFechaEmision = new Label();
	protected Label lblTipoFactura = new Label();
	protected Label lblProveedor = new Label();
	protected Label lblTipo = new Label();
	protected Label lblLineaFactura = new Label();
	protected Label lblTotalF = new Label();
	protected Button btnAgregarOrden = new Button();
	protected Button btnAgregarLinea = new Button();
	 protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
	 protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
	protected Button btnSeleccionarProveedor = new Button();
	protected Button btnSeleccionarCuentaRfr = new Button();
	protected Button btnModificarLinea = new Button();
	protected TableColumn tableColumn1 = new TableColumn();
	protected TableColumn tcNombre = new TableColumn();
	protected TableColumn tcCantidad = new TableColumn();
	protected TableColumn tcTotal = new TableColumn();
	protected TableColumn tcPrecio = new TableColumn();
	protected TableColumn tcCuentaAsociada = new TableColumn();
	protected ObjectListDataProvider ldpLineaFactura = new ObjectListDataProvider();
	protected TableRowGroup tableRowGroup1 = new TableRowGroup();
	protected Table table1 = new Table();
	protected DropDown ddTipoFactura = new DropDown();
	protected SingleSelectOptionsList ddTipoFacturaDefaultOptions = new SingleSelectOptionsList();
	protected PanelGroup groupPanel1 = new PanelGroup();
	protected RadioButton radioButton1 = new RadioButton();
	protected NumberConverter numberConverter1 = new NumberConverter();
	protected Object lastSelected = null;

	// ***GETTERS & SETTERS***
	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
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

	public TableColumn getTcTotal() {
		return tcTotal;
	}

	public void setTcTotal(TableColumn tcTotal) {
		this.tcTotal = tcTotal;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public StaticText getStFechaEmision() {
		return stFechaEmision;
	}

	public void setStFechaEmision(StaticText stFechaEmision) {
		this.stFechaEmision = stFechaEmision;
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

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter nc) {
		this.numberConverter1 = nc;
	}

	public StaticText getStCuenta() {
		return stCuenta;
	}

	public void setStCuenta(StaticText stCuenta) {
		this.stCuenta = stCuenta;
	}

	public StaticText getStTotal() {
		return stTotal;
	}

	public void setStTotal(StaticText stTotal) {
		this.stTotal = stTotal;
	}

	public TextField getTfCuentaRfr() {
		return tfCuentaRfr;
	}

	public void setTfCuentaRfr(TextField tfCuentaRfr) {
		this.tfCuentaRfr = tfCuentaRfr;
	}

	public TextField getTfTotal() {
		return tfTotal;
	}

	public void setTfTotal(TextField tfTotal) {
		this.tfTotal = tfTotal;
	}

	public TextField getTfCantidad() {
		return tfCantidad;
	}

	public void setTfCantidad(TextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tf) {
		this.tfProveedor = tf;
	}

	public TextField getTfNumeroFactura() {
		return tfNumeroFactura;
	}

	public void setTfNumeroFactura(TextField tf) {
		this.tfNumeroFactura = tf;
	}

	public TextField getTfFechaEmision() {
		return tfFechaEmision;
	}

	public void setTfFechaEmision(TextField tf) {
		this.tfFechaEmision = tf;
	}

	public Label getLblTotalF() {
		return lblTotalF;
	}

	public void setLblTotalF(Label lblTotalF) {
		this.lblTotalF = lblTotalF;
	}

	public Label getLblLineaFactura() {
		return lblLineaFactura;
	}

	public void setLblLineaFactura(Label lblLineaFactura) {
		this.lblLineaFactura = lblLineaFactura;
	}

	public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public Label getLblNumeroFactura() {
		return lblNumeroFactura;
	}

	public void setLblNumeroFactura(Label l) {
		this.lblNumeroFactura = l;
	}

	public Label getLblFechaEmision() {
		return lblFechaEmision;
	}

	public void setLblFechaEmision(Label l) {
		this.lblFechaEmision = l;
	}

	public Label getLblTipoFactura() {
		return lblTipoFactura;
	}

	public void setLblTipoFactura(Label lb) {
		this.lblTipoFactura = lb;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label l) {
		this.lblProveedor = l;
	}

	public TextField getTfPrecio() {
		return tfPrecio;
	}

	public void setTfPrecio(TextField tfPrecio) {
		this.tfPrecio = tfPrecio;
	}

	public Button getBtnModificarLinea() {
		return btnModificarLinea;
	}

	public void setBtnModificarLinea(Button btnModificarLinea) {
		this.btnModificarLinea = btnModificarLinea;
	}

	public Button getBtnSeleccionarCuentaRfr() {
		return btnSeleccionarCuentaRfr;
	}

	public void setBtnSeleccionarCuentaRfr(Button btnSeleccionarCuentaRfr) {
		this.btnSeleccionarCuentaRfr = btnSeleccionarCuentaRfr;
	}

	public Button getBtnAgregarLinea() {
		return btnAgregarLinea;
	}

	public void setBtnAgregarLinea(Button btnAgregarLinea) {
		this.btnAgregarLinea = btnAgregarLinea;
	}

	public Button getBtnAgregarOrden() {
		return btnAgregarOrden;
	}

	public void setBtnAgregarOrden(Button b) {
		this.btnAgregarOrden = b;
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

	public Button getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public void setBtnSeleccionarProveedor(Button b) {
		this.btnSeleccionarProveedor = b;
	}

	public ObjectListDataProvider getLdpLineaFactura() {
		return ldpLineaFactura;
	}

	public void setLdpLineaFactura(ObjectListDataProvider oldp) {
		this.ldpLineaFactura = oldp;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	public DropDown getDdTipoFactura() {
		return ddTipoFactura;
	}

	public void setDdTipoFactura(DropDown ddTipoFactura) {
		this.ddTipoFactura = ddTipoFactura;
	}

	public SingleSelectOptionsList getDdTipoFacturaDefaultOptions() {
		return ddTipoFacturaDefaultOptions;
	}

	public void setDdTipoFacturaDefaultOptions(SingleSelectOptionsList ddTipoFacturaDefaultOptions) {
		this.ddTipoFacturaDefaultOptions = ddTipoFacturaDefaultOptions;
	}

	// ***METHODS***
	public ABMFacturaProveedor() {
	}

	@Override
	protected void _init() throws Exception {
		
		getTable1().setClearSortButton(true);

		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(FacturaProveedor.TipoFactura.values(), "may");
		ddTipoFacturaDefaultOptions.setOptions(op);
		numberConverter1.setPattern("$ #,##0.00");
		numberConverter1.setMinIntegerDigits(1);
		numberConverter1.setMaxIntegerDigits(40);
		numberConverter1.setMaxFractionDigits(3);

		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
			this.getLdpLineaFactura().setObjectType(LineaFactura.class);
		}

	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new FacturaProveedor()); // 0
		ep.getObjetos().add(ind++, new Proveedor()); // 1
		ep.getObjetos().add(ind++, new ArrayList()); // 2 lineas de la factura
														// <LineaFactura>
		ep.getObjetos().add(ind++, new HashSet()); // 3 listado para la factura
													// proveedor y Orden de
													// compra
		ep.getObjetos().add(3, new ArrayList()); // AtributosDinamicos

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		FacturaProveedor facturaProveedor = this.obtenerObjetoDelElementoPila(0, FacturaProveedor.class);
		Proveedor proveedor = facturaProveedor.getProveedor();
		List<LineaFactura> listaLineaFactura = facturaProveedor.getListaLineaFactura();
		
		// HashSet listaOrdenesCompra = (HashSet)
		// this.obtenerObjetoDelElementoPila(3, HashSet.class);

		facturaProveedor.setFechaEmision(this.getTextFieldValueDate(getTfFechaEmision()));
		facturaProveedor.setNumero(this.getTextFieldValue(getTfNumeroFactura()));
		facturaProveedor.setTipoFactura(this.getDDEnumValue(getDdTipoFactura(), FacturaProveedor.TipoFactura.class));
		ArrayList atributosDinamicos = this.obtenerObjetoDelElementoPila(3, ArrayList.class);

		if (proveedor != null) {
			facturaProveedor.setProveedor(proveedor);
		}
		
		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		facturaProveedor.setListaAtributosDinamicos(atributosDinamicos);

		this.getObjectListDataProvider().commitChanges();
		listaLineaFactura = this.getObjectListDataProvider().getList();
		for (LineaFactura cadaLinea : listaLineaFactura) {
			cadaLinea.setTotal(null);
		}
		facturaProveedor.setListaLineaFactura(listaLineaFactura);

		try {
			// calculo de los montos totales por cada una de las lineas de la
			// factura
			facturaProveedor.setTotal(null); // se calcula desde la logica
		} catch (Exception e) {
		}

		this.getElementoPila().getObjetos().set(0, facturaProveedor);
		this.getElementoPila().getObjetos().set(3, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		FacturaProveedor facturaProveedor = this.obtenerObjetoDelElementoPila(ind++, FacturaProveedor.class);
		Proveedor proveedor = (Proveedor) obtenerObjetoDelElementoPila(ind++);
		List listaLineaFactura = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		
		if (facturaProveedor.getFechaEmision() != null) {
			this.getTfFechaEmision().setText(Conversor.getStringDeFechaCorta(facturaProveedor.getFechaEmision()));
		}
		if (proveedor != null) {
			this.getTfProveedor().setText(proveedor.getRazonSocial());
		}
		if (facturaProveedor.getNumero() != null) {
			this.getTfNumeroFactura().setText(facturaProveedor.getNumero());
		}

		this.getObjectListDataProvider().setList(listaLineaFactura);
		this.getLdpLineaFactura().setObjectType(LineaFactura.class);
		this.setListaDelCommunication(listaLineaFactura);

		if (this.getLdpLineaFactura().getList() != null && !this.getLdpLineaFactura().getList().isEmpty()) {
			for (int i = 0; i < facturaProveedor.getListaLineaFactura().size(); i++) {
				LineaFactura cadaLineaFactura = facturaProveedor.getListaLineaFactura().get(i);
				try {
					cadaLineaFactura.setTotal(null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			facturaProveedor.setTotal(null);
			this.getStTotal().setText(facturaProveedor.getTotal());
		} else {
			this.getStTotal().setText(null);
		}
		
		if (facturaProveedor.getListaAtributosDinamicos() != null) {
			facturaProveedor = this.obtenerObjetoDelElementoPila(0, FacturaProveedor.class);
			try {
				ArrayList atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(FacturaProveedor.serialVersionUID, facturaProveedor.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(3, atributosDinamicos);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(3);
		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, getNombreBean());
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
		
		this.getDdTipoFactura().setSelected(Util.getEnumNameFromString(String.valueOf(facturaProveedor.getTipoFactura())));
		this.getDdTipoFacturaDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(facturaProveedor.getTipoFactura())));
		
		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}
	
	protected List getListaDelCommunicationAtributosDinamicos() {
		return this.getCommunicationComprasBean().getListaAtributosDinamicosFacturaProveedor();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getCommunicationComprasBean().setListaAtributosDinamicosFacturaProveedor(lista);
	}

	protected ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpLineaFactura();
	}

	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaLineaFacturas();
	}

	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaLineaFacturas(lista);
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

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

	// </editor-fold>

	// </editor-fold>
	public String btnAgregarOrden_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.getRequestBean1().setObjetoABM(this.getRequestBean1().getObjetosSeleccionMultiple());
			this.getRequestBean1().setAbmController(new GenerarLineasFacturaModel().new GenerarLineasController());
			retorno = "GenerarLineasFactura";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarLinea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		FacturaProveedor facturaProveedor = this.obtenerObjetoDelElementoPila(0, FacturaProveedor.class);
		Proveedor proveedor = facturaProveedor.getProveedor();
		/*
		 * if (proveedor.getIdProveedor() == -1) {
		 * warn("Seleccione un Proveedor para listar sus Bienes.");
		 * this.getTfProveedor().setValid(false); retorno = null; } else {
		 */
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

	public String btnModificarLinea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			RowKey rk = null;
			// APLICAR LOGICA AQUI...
			try {

				rk = this.getSeleccionado();

				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					LineaFactura lineaFactura = (LineaFactura) obj;

					this.getRequestBean1().setObjetoABM(lineaFactura);
					this.setRowKeySeleccionado(this.getSeleccionado());
				} else {
					warn("Debe seleccionar una L\355nea de Factura para su modificaci\363n.");

				}

			} catch (Exception ex) {
				log(getCasoNavegacion() + "_ModificarError:", ex);
				error(getNombrePagina() + " - Modificar: " + ex.getMessage());
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			// Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			// this.getElementoPila().setPosicionGlobal(pos.longValue());
			if (rk != null) {
				retorno = "ModificarLineaFacturaProveedor";
			}

		}
		return retorno;
	}

	public void cambiarID(boolean idDistinto) {
		FacturaProveedor facturaProveedor = this.obtenerObjetoDelElementoPila(0, FacturaProveedor.class);
		List listaLineaFactura = facturaProveedor.getListaLineaFactura();
		for (Iterator it = listaLineaFactura.iterator(); it.hasNext();) {
			LineaFactura lineaFactura = (LineaFactura) it.next();
			if (idDistinto) {
				lineaFactura.setIdLineaFactura(idLineaOrdenCompraTemp--);
			} else {
				lineaFactura.setIdLineaFactura(-1);
			}
		}
		facturaProveedor.setListaLineaFactura(listaLineaFactura);

		this.getElementoPila().getObjetos().set(0, facturaProveedor);
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;

			try {
				this.cambiarID(true);// este metodo es para q funke el remove..
										// sino como el id de las
				// lineas es -1 me borra siempre el primero de la lista no el
				// seleccionado
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					LineaFactura lineaFactura = (LineaFactura) obj;
					lineaFactura.setFactura(null);

					FacturaProveedor locFactura = (FacturaProveedor) this.getElementoPila().getObjetos().get(0);
					List listaLineas = locFactura.getListaLineaFactura();

					locFactura.getListaLineaFactura().remove(lineaFactura);
					listaLineas.remove(lineaFactura);
					if (lineaFactura instanceof LineaFacturaPagoOrdenCompra) {
						((FacturaProveedor) this.getElementoPila().getObjetos().get(0)).getListaPagosOrdenCompra().remove(
								((LineaFacturaPagoOrdenCompra) lineaFactura).getPagoOrdenCompra());
					}

					this.getListaDelCommunication().remove(lineaFactura);
					this.getObjectListDataProvider().getList().remove(obj);
					
					this.cambiarID(false);// aca lo vuelvo a -1 :)
				}
			} catch (Exception ex) {
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
		FacturaProveedor facturaProveedor = this.obtenerObjetoDelElementoPila(0, FacturaProveedor.class);
		List listaOrdenesCompra = facturaProveedor.getListaPagosOrdenCompra();

		if (ultimo) {

			try {
				this.getListaDelCommunication().clear();
				listaOrdenesCompra.clear();
				facturaProveedor.setListaPagosOrdenCompra(listaOrdenesCompra);
				this.getElementoPila().getObjetos().set(0, facturaProveedor);
//				((List) this.getElementoPila().getObjetos().get(2)).clear();
//				((FacturaProveedor) this.getElementoPila().getObjetos().get(0)).getListaPagosOrdenCompra().clear();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarProveedor_action() {
		return navegarParaSeleccionar("AdminProveedor", 1);
	}

	// public String btnLimpiarProveedor_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	// if (ultimo) {
	// // CAMBIAR: Especificar objeto
	// this.limpiarObjeto(1, this.getTfProveedor());
	// this.guardarEstadoObjetosUsados();
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	// return retorno;
	// }

	@Override
	protected String getCasoNavegacion() {
		return "ABMFacturaProveedor";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FacturaProveedor facturaProveedor = this.obtenerObjetoDelElementoPila(0, FacturaProveedor.class);
		List listaLineaFactura = facturaProveedor.getListaLineaFactura();
		HashSet listaOrdenesCompra = new HashSet(facturaProveedor.getListaPagosOrdenCompra());

		System.out.println("objetoSeleccion != null");
        facturaProveedor = this.obtenerObjetoDelElementoPila(0, FacturaProveedor.class);
        
        OrdenCompra nuevaOrdenCompra = new OrdenCompra();
        
        if (pObject instanceof OrdenCompra) {
        	//Entra aqui si desde Generar Lineas Facturas se selecciono una Orden de Compra con un solo Pago.
            nuevaOrdenCompra = (OrdenCompra) pObject;
            try {
                this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(this.getSessionBean1().getLlave());
                nuevaOrdenCompra = this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().findOrdenCompraByID(nuevaOrdenCompra.getIdOrdenCompra());
                listaOrdenesCompra.add(nuevaOrdenCompra);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                if (nuevaOrdenCompra.getEstado().equals(OrdenCompra.Estado.APROBADA)) {
                    if (nuevaOrdenCompra.getListaLineaOrdenCompra().size() > 0) {
                        for (int i = 0; i < nuevaOrdenCompra.getListaLineaOrdenCompra().size(); i++) {
                            LineaOrdenCompra cadaLineaOrdenCompra = nuevaOrdenCompra.getListaLineaOrdenCompra().get(i);
                            LineaFacturaLineaOrdenCompra nuevaLineaFactura = new LineaFacturaLineaOrdenCompra();
                            
                            //cargo una linea de factura
                            nuevaLineaFactura.setLineaOrdenCompra(cadaLineaOrdenCompra);
                            nuevaLineaFactura.setCantidad(cadaLineaOrdenCompra.getCantidad());
                            if (cadaLineaOrdenCompra.getCuentaRfr() != null) {
                                nuevaLineaFactura.setCuenta(cadaLineaOrdenCompra.getCuentaRfr());
                            }

                            nuevaLineaFactura.setMontoUnitario(cadaLineaOrdenCompra.getMontoUnitario());
                            nuevaLineaFactura.setTotal(cadaLineaOrdenCompra.getMontoTotal());
                            nuevaLineaFactura.setFactura(facturaProveedor);

                            //agrego la nueva linea, al listado anterior, y prosigo con otra linea de la orden de compra
                            listaLineaFactura.add(nuevaLineaFactura); //lista de lineas factura para luego settearlo a la factura
                        }
                        facturaProveedor.setProveedor(nuevaOrdenCompra.getProveedor());
                        PagoOrdenCompra locPago = nuevaOrdenCompra.getListaPagosOrdenCompra().iterator().next();
                        facturaProveedor.addPagoOrdenCompra(locPago);
                        this.getElementoPila().getObjetos().set(1, nuevaOrdenCompra.getProveedor());
                        this.getElementoPila().getObjetos().set(2, listaLineaFactura);
                        this.getElementoPila().getObjetos().set(3, listaOrdenesCompra);
                    } else {
                        warn("La Orden de Compra no posee L\355neas para Agregar a la Factura.");
                    }
                } else {
                    warn("La Orden de Compra debe encontrarse en estado CURSADA.");
                }
            } catch (Exception e) {
                log("Error Description", e);
                e.printStackTrace();
            }
        }

        if (pObject instanceof ArrayList) { //viene del agregar linea factura
            System.out.println("seleccionado instanceof ArrayList");
            try {
                ArrayList locListado = (ArrayList) pObject;
                
                Proveedor proveedorActual = this.obtenerObjetoDelElementoPila(1, Proveedor.class);
                if(this.getCommunicationComprasBean().getProveedorSeleccionado().equals(proveedorActual) || proveedorActual.getIdProveedor() == -1){
                    
                    for (Object cadaObj : locListado) {
                        System.out.println("cadaLinea: " + cadaObj);
                        LineaFactura cadaLinea = (LineaFactura) cadaObj;
                        
                        boolean yaExiste = false;
                        if(cadaLinea instanceof LineaFacturaPagoOrdenCompra){
                        	for(Object cadaObject : listaLineaFactura){
                        		LineaFactura cadaLineaLista = (LineaFactura) cadaObject;
                        		if(cadaLineaLista instanceof LineaFacturaPagoOrdenCompra){
                        			if(((LineaFacturaPagoOrdenCompra)cadaLinea).getPagoOrdenCompra().equals(((LineaFacturaPagoOrdenCompra)cadaLineaLista).getPagoOrdenCompra())){
                        				yaExiste = true;
                        			}
                        		}
                        	}
                        }
                        
                        if(!yaExiste){
	                        cadaLinea.setFactura(facturaProveedor);
	                        if(cadaLinea instanceof LineaFacturaPagoOrdenCompra){
	                            ((LineaFacturaPagoOrdenCompra)cadaLinea).getPagoOrdenCompra().setFactura(facturaProveedor);
	                            facturaProveedor.getListaPagosOrdenCompra().add(((LineaFacturaPagoOrdenCompra)cadaLinea).getPagoOrdenCompra());
	                        }
	                        listaLineaFactura.add(cadaLinea);
                        }
                    }

                    facturaProveedor.setListaLineaFactura(listaLineaFactura);
                    facturaProveedor.setProveedor(this.getCommunicationComprasBean().getProveedorSeleccionado());
                    this.getElementoPila().getObjetos().set(1, facturaProveedor.getProveedor());
                    this.getElementoPila().getObjetos().set(2, listaLineaFactura);
                    this.getObjectListDataProvider().setList(listaLineaFactura);
                    this.setListaDelCommunication(listaLineaFactura);
                    this.getCommunicationComprasBean().setProveedorSeleccionado(null);
                }
                else{
                    warn("Las Líneas de Factura seleccionadas pertenecen a un Proveedor diferente");
                }
            } catch (Exception ex) {
                log("Error Description", ex);
            }
        }
        
        if(pObject instanceof Proveedor){
        	Proveedor proveedorNuevo = (Proveedor) pObject;
        	
        	boolean invalido = false;
        	
        	for(Object cadaObject : listaLineaFactura){
        		LineaFactura cadaLinea = (LineaFactura) cadaObject;
        		
        		if(cadaLinea instanceof LineaFacturaLineaOrdenCompra){ // || cadaLinea instanceof LineaFacturaPagoOrdenCompra){
        			LineaFacturaLineaOrdenCompra lineaFacturaOrdenCompra = (LineaFacturaLineaOrdenCompra)cadaLinea;
        			if(!lineaFacturaOrdenCompra.getLineaOrdenCompra().getOrdenCompra().getProveedor().equals(proveedorNuevo)){
        				System.out.println("ES LINEA DE LINEAORDENCOMPRA Y TIENE DIFERENTE PROVEEDOR");
            			invalido = true;
            			break;
        			}
        		}
        		if(cadaLinea instanceof LineaFacturaPagoOrdenCompra){
        			LineaFacturaPagoOrdenCompra lineaFacturaPago = (LineaFacturaPagoOrdenCompra) cadaLinea;
        			if(!lineaFacturaPago.getPagoOrdenCompra().getOrdenCompra().getProveedor().equals(proveedorNuevo)){
        				System.out.println("ES LINEA DE LINEAORDENCOMPRA Y TIENE DIFERENTE PROVEEDOR");
            			invalido = true;
            			break;
        			}
        		}
        	}
        	
        	if(!invalido){
        		this.getElementoPila().getObjetos().set(1, proveedorNuevo);
        		facturaProveedor.setProveedor(proveedorNuevo);
        	} else{
        		warn("No se pudo seleccionar el Proveedor ya que hay Líneas de Factura pertenecientes a una Orden de Compra de otro Proveedor");
        	}
        	// recorrer las lineas de la factura. Preguntar por cada una si es instanceof linea factura generada x orden de compra, si es asi, comparar el nuevo proveedor
        	//con el de las lineas de la orden. Si son diferentes, mostrar un warn de q no puede seleccionar el proveedor.
        	
        }
        
        this.getElementoPila().getObjetos().set(0, facturaProveedor);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) { // viene del admin
		FacturaProveedor facturaProveedor = (FacturaProveedor) pObject;
		Proveedor proveedor = facturaProveedor.getProveedor();
		List listaLineaFactura = facturaProveedor.getListaLineaFactura();
		if (facturaProveedor.getListaLineaFactura() != null && !facturaProveedor.getListaLineaFactura().isEmpty()) {
			for (int i = 0; i < facturaProveedor.getListaLineaFactura().size(); i++) {
				LineaFactura cadaLineaFactura = facturaProveedor.getListaLineaFactura().get(i);
				cadaLineaFactura.setTotal(null);
			}
			facturaProveedor.setTotal(null);
		}
		ArrayList atributosDinamicos = null;
		
		this.setListaDelCommunication(listaLineaFactura);
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		this.getElementoPila().getObjetos().set(0, facturaProveedor);
		this.getElementoPila().getObjetos().set(1, proveedor);
		this.getElementoPila().getObjetos().set(2, listaLineaFactura);
		this.getElementoPila().getObjetos().set(3, atributosDinamicos);
	}
	
	@Override
	public long getSerialVersionUID() {
		return FacturaProveedor.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMFacturaProveedor$ABMFacturaProveedor}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		FacturaProveedor locFacturaProveedor = this.obtenerObjetoDelElementoPila(0, FacturaProveedor.class);
		this.getTablaLogs().getLdpLogs().setList(locFacturaProveedor.getListaLogsAuditoria());
	}
}