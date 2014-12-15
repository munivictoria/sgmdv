package com.trascender.contabilidad.gui.abmParametroAsociacion.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaRefinanciacion.ParametroAsociacionTableModel;
import com.trascender.contabilidad.gui.abmParametroAsociacion.ParametroAsociacionABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMParametroAsociacionView extends ABMView {

	private static final long serialVersionUID = -8364151261849845401L;
	
	private ParametroAsociacionABMModel abmModel;
	private ParametroAsociacionTableModel tableModel;
	
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	private TLabel lblPorcentaje;
	private JTextField tfPorcentaje;
	
	private final String NOMBRE_RECURSO = "ABMParametroAsociacion";
			
	public ABMParametroAsociacionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMParametroAsociacionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;

		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setEditable(false);
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuenta);
		
		this.pnlBotonesSeleccionCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionCuenta);
		
		numFila++;
		this.lblPorcentaje = new TLabel();
		this.lblPorcentaje.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblPorcentaje"));
		this.lblPorcentaje.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblPorcentaje);
		
		this.tfPorcentaje = new JTextField();
		this.tfPorcentaje.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfPorcentaje);
		
		setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(cantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public ParametroAsociacionABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(ParametroAsociacionABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public ParametroAsociacionTableModel getTableModel() {
		return tableModel;
	}

	public void setTablemodel(ParametroAsociacionTableModel tableModel) {
		this.tableModel = tableModel;
//		this.getPnlCuerpo().getTblDatos().setModel(tablemodel);
	}

	public JTextField getTfPorcentaje() {
		return tfPorcentaje;
	}

	public TLabel getLblPorcentaje() {
		return lblPorcentaje;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public void setLblCuenta(JLabel lblCuenta) {
		this.lblCuenta = lblCuenta;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public void setTfCuenta(JTextField tfCuenta) {
		this.tfCuenta = tfCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

	public void setPnlBotonesSeleccionCuenta(
			PnlBotonesSeleccion pnlBotonesSeleccionCuenta) {
		this.pnlBotonesSeleccionCuenta = pnlBotonesSeleccionCuenta;
	}

	public void setTableModel(ParametroAsociacionTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public void setLblPorcentaje(TLabel lblPorcentaje) {
		this.lblPorcentaje = lblPorcentaje;
	}

	public void setTfPorcentaje(JTextField tfPorcentaje) {
		this.tfPorcentaje = tfPorcentaje;
	}
}
