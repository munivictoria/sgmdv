package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.AsociacionCuentaInteresRecargoABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsociacionCuentaInteresRecargoView extends ABMView {
	
	private static final long serialVersionUID = 5886306128535137738L;

	private AsociacionCuentaInteresRecargoABMModel abmModel;
	
	private JLabel lblInteresRecargo;
	private JTextField tfInteresRecargo;
	private PnlBotonesSeleccion pnlBtnSeleccionInteresRecargo;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBtnSeleccionCuenta;
	private JLabel lblCuentaAtrasada;
	private JTextField tfCuentaAtrasada;
	private PnlBotonesSeleccion pnlBtnSeleccionCuentaAtrasada;
	
	private static final String NOMBRE_RECURSO = "ABMAsociacionCuentaInteresRecargo";
	
	public ABMAsociacionCuentaInteresRecargoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMAsociacionCuentaInteresRecargoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
			
		numFila++;
		this.lblInteresRecargo = new TLabel();
		this.lblInteresRecargo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblInteresRecargo"));
		this.lblInteresRecargo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblInteresRecargo);
		
		this.tfInteresRecargo = new JTextField();
		this.tfInteresRecargo.setEditable(false);
		this.tfInteresRecargo.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfInteresRecargo);
		
		this.pnlBtnSeleccionInteresRecargo = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionInteresRecargo.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionInteresRecargo);
		
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
		
		numFila++;
		this.lblCuentaAtrasada = new TLabel();
		this.lblCuentaAtrasada.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuentaAtrasada"));
		this.lblCuentaAtrasada.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuentaAtrasada);
		
		this.tfCuentaAtrasada = new JTextField();
		this.tfCuentaAtrasada.setEditable(false);
		this.tfCuentaAtrasada.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfCuentaAtrasada);
		
		this.pnlBtnSeleccionCuentaAtrasada = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionCuentaAtrasada.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionCuentaAtrasada);
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}
	
	public AsociacionCuentaInteresRecargoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaInteresRecargoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuenta() {
		return pnlBtnSeleccionCuenta;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuentaAtrasada() {
		return pnlBtnSeleccionCuentaAtrasada;
	}

	public JTextField getTfCuentaAtrasada() {
		return tfCuentaAtrasada;
	}

	public JLabel getLblInteresRecargo() {
		return lblInteresRecargo;
	}

	public JTextField getTfInteresRecargo() {
		return tfInteresRecargo;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionInteresRecargo() {
		return pnlBtnSeleccionInteresRecargo;
	}
	
}
