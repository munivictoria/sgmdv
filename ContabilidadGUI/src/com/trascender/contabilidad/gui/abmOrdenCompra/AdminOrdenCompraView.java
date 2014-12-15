package com.trascender.contabilidad.gui.abmOrdenCompra;

import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminOrdenCompraView extends AdminView {

	private static final long serialVersionUID = -7979168139167358535L;

	private OrdenCompraBusquedaModel busquedaModel;
	private OrdenCompraTableModel tableModel;
	
	private JLabel lblFecha;
	private JFormattedTextField tfFechaDesde;
	private JFormattedTextField tfFechaHasta;
	private JLabel lblTipoOrdenCompra;
	private JTextField tfTipoOrdenCompra;
	private PnlBotonesSeleccion pnlBotonesSeleccionTipoOrdenCompra;
	private JLabel lblProveedor;
	private JTextField tfProveedor;
	private PnlBotonesSeleccion pnlBotonesSeleccionProveedor;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	
	private final String NOMBRE_RECURSO = "AdminOrdenCompra";
	private MaskFormatter formatter;
	
	public AdminOrdenCompraView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminOrdenCompraView(JDialog owner) {
		super(owner);
		this.init();
	}
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblFecha = new TLabel();
		this.lblFecha.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblFecha"));
		this.lblFecha.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFecha);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaDesde = new JFormattedTextField(this.formatter);
		this.tfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfFechaDesde);
		
		this.tfFechaHasta = new JFormattedTextField(this.formatter);
		this.tfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaHasta(numFila));
		this.getPnlBusqueda().add(this.tfFechaHasta);
		
		numFila++;
		this.lblTipoOrdenCompra = new TLabel();
		this.lblTipoOrdenCompra.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipoOrdenCompra"));
		this.lblTipoOrdenCompra.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoOrdenCompra);
		
		this.tfTipoOrdenCompra = new JTextField();
		this.tfTipoOrdenCompra.setEditable(false);
		this.tfTipoOrdenCompra.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfTipoOrdenCompra);
		
		this.pnlBotonesSeleccionTipoOrdenCompra = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionTipoOrdenCompra.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionTipoOrdenCompra);
		
		numFila++;
		this.lblProveedor = new TLabel();
		this.lblProveedor.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblProveedor"));
		this.lblProveedor.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblProveedor);
		
		this.tfProveedor = new JTextField();
		this.tfProveedor.setEditable(false);
		this.tfProveedor.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfProveedor);
		
		this.pnlBotonesSeleccionProveedor = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionProveedor.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionProveedor);
		
		numFila++;
		this.lblEstado = new TLabel();
		this.lblEstado.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblEstado"));
		this.lblEstado.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblEstado);
		
		this.cbEstado = new JComboBox();
		this.cbEstado.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbEstado);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public OrdenCompraBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(OrdenCompraBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public OrdenCompraTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(OrdenCompraTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}



	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public JLabel getLblTipoOrdenCompra() {
		return lblTipoOrdenCompra;
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}

	public JFormattedTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public JFormattedTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public JTextField getTfProveedor() {
		return tfProveedor;
	}

	public JTextField getTfTipoOrdenCompra() {
		return tfTipoOrdenCompra;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionProveedor() {
		return pnlBotonesSeleccionProveedor;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionTipoOrdenCompra() {
		return pnlBotonesSeleccionTipoOrdenCompra;
	}


}
