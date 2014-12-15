package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.AsociacionCuentaTipoTasaABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsociacionCuentaTipoTasaView extends ABMView {

	private static final long serialVersionUID = -7926290105085596915L;

	private AsociacionCuentaTipoTasaABMModel abmModel;
	
	private JLabel lblTipoTasa;
	private JTextField tfTipoTasa;
	private PnlBotonesSeleccion pnlBtnSeleccionTipoTasa;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBtnSeleccionCuenta;
	private JLabel lblCuentaAtrasada;
	private JTextField tfCuentaAtrasada;
	private PnlBotonesSeleccion pnlBtnSeleccionCuentaAtrasada;
	
	
	private static final String NOMBRE_RECURSO = "ABMAsociacionCuentaTipoTasa";
	
	public JTextField getTfCuentaAtrasada() {
		return tfCuentaAtrasada;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuentaAtrasada() {
		return pnlBtnSeleccionCuentaAtrasada;
	}

	public ABMAsociacionCuentaTipoTasaView(JFrame pFrame) {
		super(pFrame);
		this.init();
	}
	
	public ABMAsociacionCuentaTipoTasaView(JDialog pDialog) {
		super(pDialog);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblTipoTasa = new TLabel();
		this.lblTipoTasa.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblTipoTasa"));
		this.lblTipoTasa.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblTipoTasa);
		
		this.tfTipoTasa = new JTextField();
		this.tfTipoTasa.setEditable(false);
		this.tfTipoTasa.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfTipoTasa);
		
		this.pnlBtnSeleccionTipoTasa = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionTipoTasa.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.getPnlBtnSeleccionTipoTasa());
		
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
		this.getPnlCuerpo().add(this.getPnlBtnSeleccionCuenta());
		
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
	
	public AsociacionCuentaTipoTasaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaTipoTasaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public void setLblCuenta(JLabel lblCuenta) {
		this.lblCuenta = lblCuenta;
	}

	public JLabel getLblTipoTasa() {
		return lblTipoTasa;
	}

	public void setLblTipoTasa(JLabel lblTipoTasa) {
		this.lblTipoTasa = lblTipoTasa;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionCuenta() {
		return pnlBtnSeleccionCuenta;
	}

	public void setPnlBtnSeleccionCuenta(PnlBotonesSeleccion pnlBtnSeleccionCuenta) {
		this.pnlBtnSeleccionCuenta = pnlBtnSeleccionCuenta;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionTipoTasa() {
		return pnlBtnSeleccionTipoTasa;
	}

	public void setPnlBtnSeleccionTipoTasa(
			PnlBotonesSeleccion pnlBtnSeleccionTipoTasa) {
		this.pnlBtnSeleccionTipoTasa = pnlBtnSeleccionTipoTasa;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public void setTfCuenta(JTextField tfCuenta) {
		this.tfCuenta = tfCuenta;
	}

	public JTextField getTfTipoTasa() {
		return tfTipoTasa;
	}

	public void setTfTipoTasa(JTextField tfTipoTasa) {
		this.tfTipoTasa = tfTipoTasa;
	}

	public JLabel getLblCuentaAtrasada() {
		return lblCuentaAtrasada;
	}

}
