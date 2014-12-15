package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAsociacionCuentaModificadorView extends AdminView {

	private static final long serialVersionUID = -7337058385350410926L;
	
	private AsociacionCuentaModificadorBusquedaModel busquedaModel;
	private AsociacionCuentaModificadorTableModel tableModel;
	
	private JLabel lblTipoModificador;
	private JTextField tfTipoModificador;
	private PnlBotonesSeleccion pnlBotonesSeleccionTipoModificador;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "AdminAsociacionCuentaModificador";
	
	public AdminAsociacionCuentaModificadorView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminAsociacionCuentaModificadorView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblTipoModificador = new TLabel();
		this.lblTipoModificador.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipoModificador"));
		this.lblTipoModificador.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblTipoModificador);
		
		this.tfTipoModificador = new JTextField();
		this.tfTipoModificador.setEditable(false);
		this.tfTipoModificador.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfTipoModificador);
		
		this.pnlBotonesSeleccionTipoModificador = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionTipoModificador.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionTipoModificador);
		
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

	public AsociacionCuentaModificadorBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(
			AsociacionCuentaModificadorBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public AsociacionCuentaModificadorTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AsociacionCuentaModificadorTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JLabel getLblTipoModificador() {
		return lblTipoModificador;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionTipoModificador() {
		return pnlBotonesSeleccionTipoModificador;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JTextField getTfTipoModificador() {
		return tfTipoModificador;
	}
}
