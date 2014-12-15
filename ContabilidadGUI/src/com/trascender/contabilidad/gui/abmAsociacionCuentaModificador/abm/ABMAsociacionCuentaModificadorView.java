package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.AsociacionCuentaModificadorABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsociacionCuentaModificadorView extends ABMView {
	
	private static final long serialVersionUID = 5886306128535137738L;

	private AsociacionCuentaModificadorABMModel abmModel;
	
	private JLabel lblTipoModificador;
	private JTextField tfTipoModificador;
	private PnlBotonesSeleccion pnlBtnSeleccionTipoModificador;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBtnSeleccionCuenta;
	private JLabel lblCuentaAtrasada;
	private JTextField tfCuentaAtrasada;
	private PnlBotonesSeleccion pnlBtnSeleccionCuentaAtrasada;
	
	private static final String NOMBRE_RECURSO = "ABMAsociacionCuentaModificador";
	
	public ABMAsociacionCuentaModificadorView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMAsociacionCuentaModificadorView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblTipoModificador = new TLabel();
		this.lblTipoModificador.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblTipoModificador"));
		this.lblTipoModificador.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblTipoModificador);
		
		this.tfTipoModificador = new JTextField();
		this.tfTipoModificador.setEditable(false);
		this.tfTipoModificador.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfTipoModificador);
		
		this.pnlBtnSeleccionTipoModificador = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionTipoModificador.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionTipoModificador);
		
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
	
	public AsociacionCuentaModificadorABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaModificadorABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JLabel getLblTipoModificador() {
		return lblTipoModificador;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuenta() {
		return pnlBtnSeleccionCuenta;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionTipoModificador() {
		return pnlBtnSeleccionTipoModificador;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JTextField getTfTipoModificador() {
		return tfTipoModificador;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuentaAtrasada() {
		return pnlBtnSeleccionCuentaAtrasada;
	}

	public JTextField getTfCuentaAtrasada() {
		return tfCuentaAtrasada;
	}
}
