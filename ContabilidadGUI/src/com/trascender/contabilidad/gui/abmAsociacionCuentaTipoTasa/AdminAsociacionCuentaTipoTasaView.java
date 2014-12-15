package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAsociacionCuentaTipoTasaView extends AdminView {

	private static final long serialVersionUID = 2690296818980115963L;
	
	private AsociacionCuentaTipoTasaTableModel tableModel;
	private AsociacionCuentaTipoTasaBusquedaModel busquedaModel;
	
	private JLabel lblTipoTasa;
	private JTextField tfTipoTasa;
	private PnlBotonesSeleccion pnlBotonesSeleccionTipoTasa;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "AdminAsociacionCuentaTipoTasa";
	
	public AdminAsociacionCuentaTipoTasaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminAsociacionCuentaTipoTasaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblTipoTasa = new TLabel();
		this.lblTipoTasa.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipoTasa"));
		this.lblTipoTasa.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoTasa);
		
		this.tfTipoTasa = new JTextField();
		this.tfTipoTasa.setEditable(false);
		this.tfTipoTasa.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfTipoTasa);
		
		this.pnlBotonesSeleccionTipoTasa = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionTipoTasa.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionTipoTasa);
		
		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setEditable(false);
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCuenta);
		
		this.pnlBotonesSeleccionCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionCuenta);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public AsociacionCuentaTipoTasaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(AsociacionCuentaTipoTasaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public AsociacionCuentaTipoTasaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AsociacionCuentaTipoTasaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JLabel getLblTipoTasa() {
		return lblTipoTasa;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionTipoTasa() {
		return pnlBotonesSeleccionTipoTasa;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JTextField getTfTipoTasa() {
		return tfTipoTasa;
	}
}
