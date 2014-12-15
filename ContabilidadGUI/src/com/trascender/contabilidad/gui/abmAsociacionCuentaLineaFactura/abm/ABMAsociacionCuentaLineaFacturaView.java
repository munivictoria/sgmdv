package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.AsociacionCuentaLineaFacturaABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsociacionCuentaLineaFacturaView extends ABMView {

	private static final long serialVersionUID = 2192204923547566480L;

	private JLabel lblAnioPeriodo;
	private JTextField tfAnioPeriodo;
	private JLabel lblLineaFactura;
	private JTextField tfLineaFactura;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBtnSeleccionCuenta;
	
	private AsociacionCuentaLineaFacturaABMModel abmModel;
	private static final String NOMBRE_RECURSO = "ABMAsociacionCuentaLineaFactura";
	
	public ABMAsociacionCuentaLineaFacturaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMAsociacionCuentaLineaFacturaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblAnioPeriodo = new TLabel();
		this.lblAnioPeriodo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblAnioPeriodo"));
		this.lblAnioPeriodo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblAnioPeriodo);
		
		this.tfAnioPeriodo = new JTextField();
		this.tfAnioPeriodo.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		this.getPnlCuerpo().add(this.tfAnioPeriodo);

		numFila++;
		this.lblLineaFactura = new TLabel();
		this.lblLineaFactura.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblLineaFactura"));
		this.lblLineaFactura.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblLineaFactura);
		
		this.tfLineaFactura = new JTextField();
		this.tfLineaFactura.setEditable(false);
		this.tfLineaFactura.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfLineaFactura);
		
		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setEditable(false);
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuenta);
		
		this.pnlBtnSeleccionCuenta = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionCuenta);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(cantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public AsociacionCuentaLineaFacturaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaLineaFacturaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblAnioPeriodo() {
		return lblAnioPeriodo;
	}

	public JTextField getTfAnioPeriodo() {
		return tfAnioPeriodo;
	}

	public JLabel getLblLineaFactura() {
		return lblLineaFactura;
	}

	public JTextField getTfLineaFactura() {
		return tfLineaFactura;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuenta() {
		return pnlBtnSeleccionCuenta;
	}

}
