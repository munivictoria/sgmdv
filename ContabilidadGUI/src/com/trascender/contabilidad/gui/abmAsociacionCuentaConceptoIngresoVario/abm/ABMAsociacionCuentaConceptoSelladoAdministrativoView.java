package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.AsociacionCuentaConceptoSelladoAdministrativoABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMAsociacionCuentaConceptoSelladoAdministrativoView extends ABMView {

	private static final long serialVersionUID = 1998464501019821297L;
	
	private AsociacionCuentaConceptoSelladoAdministrativoABMModel abmModel;

	private JLabel lblAnioPeriodo;
	private JTextField tfAnioPeriodo;
	private JLabel lblConceptoSelladoAdministrativo;
	private JTextField tfConceptoSelladoAdministrativo;
	private PnlBotonesSeleccion pnlBotonesSeleccionConceptoSelladoAdministrativo;
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	
	private final String NOMBRE_RECURSO = "ABMAsociacionCuentaConceptoSelladoAdministrativo";
	
	public ABMAsociacionCuentaConceptoSelladoAdministrativoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMAsociacionCuentaConceptoSelladoAdministrativoView(JFrame owner) {
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
		this.lblConceptoSelladoAdministrativo = new TLabel();
		this.lblConceptoSelladoAdministrativo.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblConceptoSelladoAdministrativo"));
		this.lblConceptoSelladoAdministrativo.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblConceptoSelladoAdministrativo);
		
		this.tfConceptoSelladoAdministrativo = new JTextField();
		this.tfConceptoSelladoAdministrativo.setEditable(false);
		this.tfConceptoSelladoAdministrativo.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfConceptoSelladoAdministrativo);
		
		this.pnlBotonesSeleccionConceptoSelladoAdministrativo = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionConceptoSelladoAdministrativo.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionConceptoSelladoAdministrativo);
		
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
		
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(cantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}

	public AsociacionCuentaConceptoSelladoAdministrativoABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(
			AsociacionCuentaConceptoSelladoAdministrativoABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public void setTfCuenta(JTextField tfCuenta) {
		this.tfCuenta = tfCuenta;
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

	public JLabel getLblConceptoSelladoAdministrativo() {
		return lblConceptoSelladoAdministrativo;
	}

	public JTextField getTfConceptoSelladoAdministrativo() {
		return tfConceptoSelladoAdministrativo;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionConceptoSelladoAdministrativo() {
		return pnlBotonesSeleccionConceptoSelladoAdministrativo;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

}
