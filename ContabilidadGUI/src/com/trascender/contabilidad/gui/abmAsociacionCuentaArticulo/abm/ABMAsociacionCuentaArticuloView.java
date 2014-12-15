package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.AsociacionCuentaArticuloABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsociacionCuentaArticuloView extends ABMView{
	private static final long serialVersionUID = -6015634765973553606L;

	private AsociacionCuentaArticuloABMModel abmModel;
	
	private JLabel lblAnioPeriodo;
	private JTextField tfAnioPeriodo;
	private JLabel lblArticulo;
	private JTextField tfArticulo;
	private PnlBotonesSeleccion pnlBtnSeleccionArticulo;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBtnSeleccionCuenta;
	
	private static final String NOMBRE_RECURSO = "ABMAsociacionCuentaArticulo";
	
	
	public ABMAsociacionCuentaArticuloView(JFrame frame){
		super(frame);
		init();
	}
	
	public ABMAsociacionCuentaArticuloView(JDialog dialog){
		super(dialog);
		init();
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
		this.lblArticulo = new TLabel();
		this.lblArticulo.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblArticulo"));
		this.lblArticulo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblArticulo);
		
		this.tfArticulo = new JTextField();
		this.tfArticulo.setEditable(false);
		this.tfArticulo.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfArticulo);
		
		this.pnlBtnSeleccionArticulo = new PnlBotonesSeleccion();
		this.pnlBtnSeleccionArticulo.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBtnSeleccionArticulo);
		
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
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public AsociacionCuentaArticuloABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(AsociacionCuentaArticuloABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblAnioPeriodo() {
		return lblAnioPeriodo;
	}

	public void setLblAnioPeriodo(JLabel lblAnioPeriodo) {
		this.lblAnioPeriodo = lblAnioPeriodo;
	}

	public JTextField getTfAnioPeriodo() {
		return tfAnioPeriodo;
	}

	public void setTfAnioPeriodo(JTextField tfAnioPeriodo) {
		this.tfAnioPeriodo = tfAnioPeriodo;
	}

	public JLabel getLblArticulo() {
		return lblArticulo;
	}

	public void setLblArticulo(JLabel lblArticulo) {
		this.lblArticulo = lblArticulo;
	}

	public JTextField getTfArticulo() {
		return tfArticulo;
	}

	public void setTfArticulo(JTextField tfArticulo) {
		this.tfArticulo = tfArticulo;
	}

	public PnlBotonesSeleccion getPnlBtnSeleccionArticulo() {
		return pnlBtnSeleccionArticulo;
	}

	public void setPnlBtnSeleccionArticulo(
			PnlBotonesSeleccion pnlBtnSeleccionArticulo) {
		this.pnlBtnSeleccionArticulo = pnlBtnSeleccionArticulo;
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

	public PnlBotonesSeleccion getPnlBtnSeleccionCuenta() {
		return pnlBtnSeleccionCuenta;
	}

	public void setPnlBtnSeleccionCuenta(PnlBotonesSeleccion pnlBtnSeleccionCuenta) {
		this.pnlBtnSeleccionCuenta = pnlBtnSeleccionCuenta;
	}

}
