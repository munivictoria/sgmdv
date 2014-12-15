package com.trascender.caja.gui.abmCaja.abm;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.caja.gui.abmCaja.CajaABMModel;
import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public abstract class ABMCajaView extends ABMView {

	private static final long serialVersionUID = -1205009208691401424L;
	
	private CajaABMModel abmModel;
	
	private JLabel lblIdentificador;
	private JTextField tfIdentificador;
	private JLabel lblIp;
	private JTextField tfIp;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblPuerto;
	private JTextField tfPuerto;
	
	private final String NOMBRE_RECURSO = "ABMCaja";
	
	public ABMCajaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMCajaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblIdentificador = new TLabel();
		this.lblIdentificador.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblIdentificador"));
		this.lblIdentificador.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblIdentificador);
		
		this.tfIdentificador = new JTextField();
		this.tfIdentificador.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfIdentificador);
		
		numFila++;
		this.lblIp = new TLabel();
		this.lblIp.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblIp"));
		this.lblIp.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblIp);
		
		this.tfIp = new JTextField();
		this.tfIp.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfIp);
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfNombre);
		
		numFila++;
		this.lblPuerto = new TLabel();
		this.lblPuerto.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblPuerto"));
		this.lblPuerto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblPuerto);
		
		this.tfPuerto = new JTextField();
		this.tfPuerto.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlCuerpo().add(this.tfPuerto);
		
		setTamanioPosicionVentana(numFila + 1);		
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);		
	}

	public CajaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(CajaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JTextField getTfPuerto() {
		return tfPuerto;
	}

	public void setTfPuerto(JTextField tfPuerto) {
		this.tfPuerto = tfPuerto;
	}

	public JTextField getTfIdentificador() {
		return tfIdentificador;
	}

	public void setTfIdentificador(JTextField tfIdentificador) {
		this.tfIdentificador = tfIdentificador;
	}

	public JTextField getTfIp() {
		return tfIp;
	}

	public void setTfIp(JTextField tfIp) {
		this.tfIp = tfIp;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JLabel getLblIdentificador() {
		return lblIdentificador;
	}

	public void setLblIdentificador(JLabel lblIdentificador) {
		this.lblIdentificador = lblIdentificador;
	}

	public JLabel getLblIp() {
		return lblIp;
	}

	public void setLblIp(JLabel lblIp) {
		this.lblIp = lblIp;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getLblPuerto() {
		return lblPuerto;
	}

	public void setLblPuerto(JLabel lblPuerto) {
		this.lblPuerto = lblPuerto;
	}

}
