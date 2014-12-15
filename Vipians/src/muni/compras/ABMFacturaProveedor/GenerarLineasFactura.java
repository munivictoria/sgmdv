/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.compras.ABMFacturaProveedor;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.PagoOrdenCompra;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fer Luca
 */
public class GenerarLineasFactura extends ABMPageBean {

	private Label lblPagos = new Label();
	private Label lblGenerarLineas = new Label();
	private Label lblBienes = new Label();
	private Label lblOrdenesCompra = new Label();
	private Label lblLineasOrdenCompra = new Label();
	private Table table1 = new Table();
	private Table table2 = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private ObjectListDataProvider ldpPagos = new ObjectListDataProvider();
	private ObjectListDataProvider ldpLineasOrdenCompra = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcMonto = new TableColumn();
	private TableColumn tcBien = new TableColumn();
	private TableColumn tcCantidad = new TableColumn();
	private TableColumn tcMontoUnitario = new TableColumn();
	private TableColumn tcMontoTotal = new TableColumn();
	private Checkbox checkbox1 = new Checkbox();
	private Checkbox ckbPorBienes = new Checkbox();
	private Checkbox ckbPorPagos = new Checkbox();
	private StaticText stNombre = new StaticText();
	private StaticText stMonto = new StaticText();
	private StaticText stBien = new StaticText();
	private StaticText stCantidad = new StaticText();
	private StaticText stMontoUnitario = new StaticText();
	private StaticText stMontoTotal = new StaticText();
	private TableSelectPhaseListener tablePhaseListener = new TableSelectPhaseListener();
	private Button btnSeleccionarOrdenesCompra = new Button();
	private TextField tfOrdenCompra = new TextField();

	public Label getLblLineasOrdenCompra() {
		return lblLineasOrdenCompra;
	}

	public void setLblLineasOrdenCompra(Label lblLineasOrdenCompra) {
		this.lblLineasOrdenCompra = lblLineasOrdenCompra;
	}

	public ObjectListDataProvider getLdpLineasOrdenCompra() {
		return ldpLineasOrdenCompra;
	}

	public void setLdpLineasOrdenCompra(ObjectListDataProvider ldpLineasOrdenCompra) {
		this.ldpLineasOrdenCompra = ldpLineasOrdenCompra;
	}

	public StaticText getStBien() {
		return stBien;
	}

	public void setStBien(StaticText stBien) {
		this.stBien = stBien;
	}

	public StaticText getStCantidad() {
		return stCantidad;
	}

	public void setStCantidad(StaticText stCantidad) {
		this.stCantidad = stCantidad;
	}

	public StaticText getStMontoTotal() {
		return stMontoTotal;
	}

	public void setStMontoTotal(StaticText stMontoTotal) {
		this.stMontoTotal = stMontoTotal;
	}

	public StaticText getStMontoUnitario() {
		return stMontoUnitario;
	}

	public void setStMontoUnitario(StaticText stMontoUnitario) {
		this.stMontoUnitario = stMontoUnitario;
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

	public TableColumn getTcBien() {
		return tcBien;
	}

	public void setTcBien(TableColumn tcBien) {
		this.tcBien = tcBien;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public TableColumn getTcMontoTotal() {
		return tcMontoTotal;
	}

	public void setTcMontoTotal(TableColumn tcMontoTotal) {
		this.tcMontoTotal = tcMontoTotal;
	}

	public TableColumn getTcMontoUnitario() {
		return tcMontoUnitario;
	}

	public void setTcMontoUnitario(TableColumn tcMontoUnitario) {
		this.tcMontoUnitario = tcMontoUnitario;
	}

	public TextField getTfOrdenCompra() {
		return tfOrdenCompra;
	}

	public void setTfOrdenCompra(TextField tfOrdenCompra) {
		this.tfOrdenCompra = tfOrdenCompra;
	}

	public Label getLblGenerarLineas() {
		return lblGenerarLineas;
	}

	public void setLblGenerarLineas(Label lblGenerarLineas) {
		this.lblGenerarLineas = lblGenerarLineas;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public Button getBtnSeleccionarOrdenesCompra() {
		return btnSeleccionarOrdenesCompra;
	}

	public void setBtnSeleccionarOrdenesCompra(Button btnSeleccionarOrdenesCompra) {
		this.btnSeleccionarOrdenesCompra = btnSeleccionarOrdenesCompra;
	}

	public Label getLblOrdenesCompra() {
		return lblOrdenesCompra;
	}

	public void setLblOrdenesCompra(Label lblOrdenesCompra) {
		this.lblOrdenesCompra = lblOrdenesCompra;
	}

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table table2) {
		this.table2 = table2;
	}

	public Label getLblBienes() {
		return lblBienes;
	}

	public void setLblBienes(Label lblBienes) {
		this.lblBienes = lblBienes;
	}

	public Checkbox getCkbPorBienes() {
		return ckbPorBienes;
	}

	public void setCkbPorBienes(Checkbox ckbPorBienes) {
		this.ckbPorBienes = ckbPorBienes;
	}

	public Checkbox getCkbPorPagos() {
		return ckbPorPagos;
	}

	public void setCkbPorPagos(Checkbox ckbPorPagos) {
		this.ckbPorPagos = ckbPorPagos;
	}

	public TableSelectPhaseListener getTablePhaseListener() {
		return tablePhaseListener;
	}

	public void setTablePhaseListener(TableSelectPhaseListener tablePhaseListener) {
		this.tablePhaseListener = tablePhaseListener;
	}

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox checkbox1) {
		this.checkbox1 = checkbox1;
	}

	public StaticText getStMonto() {
		return stMonto;
	}

	public void setStMonto(StaticText stMonto) {
		this.stMonto = stMonto;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public Label getLblPagos() {
		return lblPagos;
	}

	public void setLblPagos(Label lblPagos) {
		this.lblPagos = lblPagos;
	}

	public ObjectListDataProvider getLdpPagos() {
		return ldpPagos;
	}

	public void setLdpPagos(ObjectListDataProvider ldpPagos) {
		this.ldpPagos = ldpPagos;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public String getCurrentRow() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public void setSelected(Object object) {
		RowKey rowKey = tableRowGroup2.getRowKey();
		if(rowKey != null) {
			tablePhaseListener.setSelected(rowKey, object);
		}
	}

	public Object getSelected() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return tablePhaseListener.getSelected(rowKey);
	}

	public Object getSelectedValue() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public boolean isCurrentRowSelected() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return tablePhaseListener.isSelected(rowKey);
	}

	public ObjectListDataProvider getObjectListDataProviderPagos() {
		return this.getLdpPagos();
	}

	private List getListaDelCommunicationPagos() {
		return this.getCommunicationComprasBean().getListaPagos();
	}

	private void setListaDelCommunicationPagos(List lista) {
		this.getCommunicationComprasBean().setListaPagos(lista);
	}

	private ObjectListDataProvider getObjectListDataProviderLineasOrdenCompra() {
		return this.getLdpLineasOrdenCompra();
	}

	private List getListaDelCommunicationLineasOrdenCompra() {
		return this.getCommunicationComprasBean().getListaLineasOrdenesCompra();
	}

	private void setListaDelCommunicationLineasOrdenCompra(List lista) {
		this.getCommunicationComprasBean().setListaLineasOrdenesCompra(lista);
	}

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationLineasOrdenCompra() != null) {
			this.getObjectListDataProviderLineasOrdenCompra().setList(this.getListaDelCommunicationLineasOrdenCompra());
		}

		if(this.getListaDelCommunicationPagos() != null) {
			this.getObjectListDataProviderPagos().setList(this.getListaDelCommunicationPagos());
		}
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		OrdenCompra ordenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(ind++);
		List<PagoOrdenCompra> listaPagos;
		List<LineaOrdenCompra> listaLineasOrdenCompra;

		this.getObjectListDataProviderPagos().commitChanges();
		listaPagos = this.getObjectListDataProviderPagos().getList();
		this.setListaDelCommunicationPagos(listaPagos);

		this.getObjectListDataProviderLineasOrdenCompra().commitChanges();
		listaLineasOrdenCompra = this.getObjectListDataProviderLineasOrdenCompra().getList();
		this.setListaDelCommunicationLineasOrdenCompra(listaLineasOrdenCompra);

		this.getElementoPila().getObjetos().set(0, ordenCompra);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		OrdenCompra ordenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(0);
		List<PagoOrdenCompra> listaPagos = null;
		List<LineaOrdenCompra> listaLineasOrdenCompra = null;

		ordenCompra = (OrdenCompra) this.obtenerObjetoDelElementoPila(0, OrdenCompra.class);
		listaPagos = (List<PagoOrdenCompra>) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
		listaLineasOrdenCompra = (List<LineaOrdenCompra>) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

		if(ordenCompra != null && ordenCompra.getIdOrdenCompra() != -1) {
			if(ordenCompra.getEstado().equals(ordenCompra.getEstado().APROBADA)){
			this.getTfOrdenCompra().setText(ordenCompra);
			}

			if(ordenCompra.getListaPagosOrdenCompra().size() <= 1) {
				this.getTable2().setRendered(false);
				this.getLblPagos().setRendered(false);
				this.getTable1().setRendered(true);
				this.getLblLineasOrdenCompra().setRendered(true);
			} else {
				this.getTable2().setRendered(true);
				this.getLblPagos().setRendered(true);
				this.getTable1().setRendered(false);
				this.getLblLineasOrdenCompra().setRendered(false);
			}
		} else {
			this.getTable1().setRendered(false);
			this.getTable2().setRendered(false);
			this.getLblLineasOrdenCompra().setRendered(false);
			this.getLblPagos().setRendered(false);
		}

		this.getObjectListDataProviderPagos().setList(listaPagos);
		this.setListaDelCommunicationPagos(listaPagos);

		this.getObjectListDataProviderLineasOrdenCompra().setList(listaLineasOrdenCompra);
		this.setListaDelCommunicationLineasOrdenCompra(listaLineasOrdenCompra);

		this.getElementoPila().getObjetos().set(0, ordenCompra);
	}

	public String btnSeleccionarOrdenesCompra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			retorno = "AdminOrdenCompra";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;

	}

	@Override
	protected String getCasoNavegacion() {
		return "GenerarLineasFactura";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new OrdenCompra());
		ep.getObjetos().add(ind++, new ArrayList<PagoOrdenCompra>());
		ep.getObjetos().add(ind++, null);
		ep.getObjetos().add(ind++, new ArrayList<LineaOrdenCompra>());
		ep.getObjetos().add(ind++, null); // Factura

		return ep;
	}

	@Override
	public int getNroFila(String pCadena) {
		// Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		OrdenCompra ordenCompra = (OrdenCompra) pObject;
		this.setListaDelCommunicationPagos(new ArrayList<PagoOrdenCompra>());
		List listaPagos = new ArrayList<PagoOrdenCompra>();
		List listaLineasOrdenCompra = new ArrayList<LineaOrdenCompra>();
		try {
			this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(this.getSessionBean1().getLlave());
			ordenCompra = this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().findOrdenCompraByID(ordenCompra.getIdOrdenCompra());

			if(ordenCompra.getEstado().equals(OrdenCompra.Estado.APROBADA)) {
				for(PagoOrdenCompra cadaPAgo : ordenCompra.getListaPagosOrdenCompra()) {
					if(cadaPAgo.getFactura() == null) {
						listaPagos.add(cadaPAgo);
					}
				}
				listaLineasOrdenCompra.addAll(ordenCompra.getListaLineaOrdenCompra());
				if(ordenCompra.getListaPagosOrdenCompra().size() <= 1) {
					this.getLblGenerarLineas().setRendered(false);
					this.getTable1().setRendered(true);
					this.getLblLineasOrdenCompra().setRendered(true);
					this.getObjectListDataProviderLineasOrdenCompra().setList(ordenCompra.getListaLineaOrdenCompra());
					this.setListaDelCommunicationLineasOrdenCompra(ordenCompra.getListaLineaOrdenCompra());
				} else {
					this.getLblGenerarLineas().setRendered(false);
					this.getTable2().setRendered(true);
					this.getLblPagos().setRendered(true);
					this.getObjectListDataProviderPagos().setList(listaPagos);
					this.setListaDelCommunicationPagos(listaPagos);
				}
			} else {
				error("La Orden de Compra debe estar APROBADA");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		this.setListaDelCommunicationPagos(listaPagos);
		this.setListaDelCommunicationLineasOrdenCompra(listaLineasOrdenCompra);

		this.getElementoPila().getObjetos().set(0, ordenCompra);
		this.getElementoPila().getObjetos().set(1, listaPagos);
		this.getElementoPila().getObjetos().set(3, listaLineasOrdenCompra);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		FacturaProveedor factura = (FacturaProveedor) pObject;
		this.getElementoPila().getObjetos().set(4, factura);
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMFacturaProveedor$GenerarLineasFactura}";
	}

	@Override
	public long getSerialVersionUID() {
		return LineaFactura.serialVersionUID;
	}
}