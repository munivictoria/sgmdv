package com.trascender.contabilidad.gui.abmCuentaBancaria.abm;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.abmCuentaBancaria.CuentaBancariaAbmModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccionPersona;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMCuentaBancariaView extends ABMView {

	private static final long serialVersionUID = 5513255615940373091L;

	private CuentaBancariaAbmModel abmModel;
	
	private JLabel lblTipoCuenta;
	private JTextField tfTipoCuenta;
	private JLabel lblNumero;
	private JTextField tfNumero;
	private JLabel lblPropia;
	private JCheckBox chkPropia;
	private JLabel lblBanco;
	private JTextField tfBanco;
	private JLabel lblTitularCuentaBancaria;
	private JTextField tfTitularCuentaBancaria;
	
	private PnlBotonesSeleccion pnlBotonesSeleccionBanco;
	private PnlBotonesSeleccionPersona pnlBotonesSeleccionPersona;
	
	private final String NOMBRE_RECURSO = "ABMCuentaBancaria";

	public ABMCuentaBancariaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public ABMCuentaBancariaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblTipoCuenta = new TLabel();
		this.lblTipoCuenta.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTipoCuenta"));
		this.lblTipoCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblTipoCuenta);
		
		this.tfTipoCuenta = new JTextField();
		this.tfTipoCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfTipoCuenta);
		
		numFila++;
		this.lblNumero = new TLabel();
		this.lblNumero.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNumero"));
		this.lblNumero.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNumero);
		
		this.tfNumero = new JTextField();
		this.tfNumero.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfNumero);
		
		numFila++;
		this.lblPropia = new TLabel();
		this.lblPropia.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblPropia"));
		this.lblPropia.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblPropia);
		
		this.chkPropia = new JCheckBox();
		this.chkPropia.setBounds(Util.getBoundsColumnaCheckBox(numFila));
		this.getPnlCuerpo().add(this.chkPropia);
		
		numFila++;
		this.lblBanco = new TLabel();
		this.lblBanco.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblBanco"));
		this.lblBanco.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblBanco);
		
		this.tfBanco = new JTextField();
		this.tfBanco.setEditable(false);
		this.tfBanco.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfBanco);
		
		this.pnlBotonesSeleccionBanco = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionBanco.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionBanco);
		
		numFila++;
		this.lblTitularCuentaBancaria = new TLabel();
		this.lblTitularCuentaBancaria.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblTitularCuentaBancaria"));
		this.lblTitularCuentaBancaria.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblTitularCuentaBancaria);
		
		this.tfTitularCuentaBancaria = new JTextField();
		this.tfTitularCuentaBancaria.setEditable(false);
		this.tfTitularCuentaBancaria.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfTitularCuentaBancaria);
		
		this.pnlBotonesSeleccionPersona = new PnlBotonesSeleccionPersona();
		this.pnlBotonesSeleccionPersona.setBounds(Util.getBoundsColumnaSeleccionPersona(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionPersona);
		
		setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X+
				ConstantesTamanio.TF_WIDTH+
				ConstantesSeparacion.SEPARADOR_HORIZONTAL+
				ConstantesTamanio.PNL_BOTONES_SELECCION_PERSONA_WIDTH,
				
				ConstantesTamanio.PNL_CABECERA_HEIGHT+ConstantesTamanio.PNL_PIE_HEIGHT+pCantidadFilasComponentes*
				ConstantesSeparacion.INCREMENTO_Y+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		this.setLocationRelativeTo(null);
		
	}

	public CuentaBancariaAbmModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(CuentaBancariaAbmModel abmModel) {
		this.abmModel = abmModel;
	}

	public JCheckBox getChkPropia() {
		return chkPropia;
	}

	public JLabel getLblBanco() {
		return lblBanco;
	}

	public JLabel getLblNumero() {
		return lblNumero;
	}

	public JLabel getLblPropia() {
		return lblPropia;
	}

	public JLabel getLblTipoCuenta() {
		return lblTipoCuenta;
	}

	public JLabel getLblTitularCuentaBancaria() {
		return lblTitularCuentaBancaria;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionBanco() {
		return pnlBotonesSeleccionBanco;
	}

	public JTextField getTfBanco() {
		return tfBanco;
	}

	public JTextField getTfNumero() {
		return tfNumero;
	}

	public JTextField getTfTipoCuenta() {
		return tfTipoCuenta;
	}

	public JTextField getTfTitularCuentaBancaria() {
		return tfTitularCuentaBancaria;
	}

	public PnlBotonesSeleccionPersona getPnlBotonesSeleccionPersona() {
		return pnlBotonesSeleccionPersona;
	}

}
