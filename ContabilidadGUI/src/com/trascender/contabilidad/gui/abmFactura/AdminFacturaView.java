package com.trascender.contabilidad.gui.abmFactura;

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

public class AdminFacturaView extends AdminView {

	private static final long serialVersionUID = -3248049003749825807L;
	
	private FacturaTableModel tableModel;
	private FacturaBusquedaModel busquedaModel;

	private JLabel lblProveedor;
	private JTextField tfProveedor;
	private PnlBotonesSeleccion pnlBotonesSeleccionProveedor;
	private JLabel lblFechaDesdeHasta;
	private JFormattedTextField tfFechaDesde;
	private JFormattedTextField tfFechaHasta;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	
	private final String NOMBRE_RECURSO = "AdminFactura";
	private MaskFormatter formatter;
	
	public AdminFacturaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminFacturaView(JFrame owner) {
		super(owner);
		this.init();
	}

	private void init() {
		int numFila = -1;
		
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
		this.lblFechaDesdeHasta = new TLabel();
		this.lblFechaDesdeHasta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblFechaDesdeHasta"));
		this.lblFechaDesdeHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblFechaDesdeHasta);
		
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

	public FacturaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(FacturaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public FacturaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(FacturaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public JTextField getTfProveedor() {
		return tfProveedor;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionProveedor() {
		return pnlBotonesSeleccionProveedor;
	}

	public JFormattedTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public JFormattedTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public JComboBox getCbEstado() {
		return cbEstado;
	}

}
