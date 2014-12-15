package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminAsociacionCuentaConceptoIngresoVarioView extends AdminView {

	private static final long serialVersionUID = -605757659243356178L;

	private AsociacionCuentaConceptoIngresoVarioBusquedaModel busquedaModel;
	private AsociacionCuentaConceptoIngresoVarioTableModel tableModel;
	
	private JLabel lblAnioPeriodo;
	private JTextField tfAnioPeriodo;
	private JLabel lblConceptoIngresoVario;
	private JTextField tfConceptoIngresoVario;
	private PnlBotonesSeleccion pnlBotonesSeleccionConceptoIngresoVario;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "AdminAsociacionCuentaConceptoIngresoVario";
	
	public AdminAsociacionCuentaConceptoIngresoVarioView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminAsociacionCuentaConceptoIngresoVarioView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public void init() {
		int numFila = -1;
		
		numFila++;
		this.lblAnioPeriodo = new TLabel();
		this.lblAnioPeriodo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblAnioPeriodo"));
		this.lblAnioPeriodo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblAnioPeriodo);
		
		this.tfAnioPeriodo = new JTextField();
		this.tfAnioPeriodo.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlBusqueda().add(this.tfAnioPeriodo);

		numFila++;
		this.lblConceptoIngresoVario = new TLabel();
		this.lblConceptoIngresoVario.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblConceptoIngresoVario"));
		this.lblConceptoIngresoVario.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblConceptoIngresoVario);
		
		this.tfConceptoIngresoVario = new JTextField();
		this.tfConceptoIngresoVario.setEditable(false);
		this.tfConceptoIngresoVario.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfConceptoIngresoVario);
		
		this.pnlBotonesSeleccionConceptoIngresoVario = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionConceptoIngresoVario.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlBusqueda().add(this.pnlBotonesSeleccionConceptoIngresoVario);
		
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

	public AsociacionCuentaConceptoIngresoVarioBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(
			AsociacionCuentaConceptoIngresoVarioBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public AsociacionCuentaConceptoIngresoVarioTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(
			AsociacionCuentaConceptoIngresoVarioTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JTextField getTfAnioPeriodo() {
		return tfAnioPeriodo;
	}

	public JTextField getTfConceptoIngresoVario() {
		return tfConceptoIngresoVario;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionConceptoIngresoVario() {
		return pnlBotonesSeleccionConceptoIngresoVario;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public JLabel getLblAnioPeriodo() {
		return lblAnioPeriodo;
	}
	
}
