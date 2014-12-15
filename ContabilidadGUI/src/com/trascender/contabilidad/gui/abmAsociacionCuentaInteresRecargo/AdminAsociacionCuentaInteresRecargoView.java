package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAsociacionCuentaInteresRecargoView extends AdminView {

	private static final long serialVersionUID = -7337058385350410926L;
	
	private AsociacionCuentaInteresRecargoBusquedaModel busquedaModel;
	private AsociacionCuentaInteresRecargoTableModel tableModel;
	
	private JLabel lblConceptoPorMora;
	private JTextField tfConceptoPorMora;
	private PnlBotonesSeleccion pnlBotonesSeleccionConceptoPorMora;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "AdminAsociacionCuentaInteresRecargo";
	
	public AdminAsociacionCuentaInteresRecargoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminAsociacionCuentaInteresRecargoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblConceptoPorMora = new TLabel();
		this.lblConceptoPorMora.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblConceptoPorMora"));
		this.lblConceptoPorMora.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblConceptoPorMora);
		
		this.tfConceptoPorMora = new JTextField();
		this.tfConceptoPorMora.setEditable(false);
		this.tfConceptoPorMora.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfConceptoPorMora);
		
		this.pnlBotonesSeleccionConceptoPorMora = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionConceptoPorMora.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionConceptoPorMora);
		
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

	public AsociacionCuentaInteresRecargoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(
			AsociacionCuentaInteresRecargoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public AsociacionCuentaInteresRecargoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AsociacionCuentaInteresRecargoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}
	
	public JLabel getLblConceptoPorMora() {
		return lblConceptoPorMora;
	}

	public JTextField getTfConceptoPorMora() {
		return tfConceptoPorMora;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionConceptoPorMora() {
		return pnlBotonesSeleccionConceptoPorMora;
	}
}
