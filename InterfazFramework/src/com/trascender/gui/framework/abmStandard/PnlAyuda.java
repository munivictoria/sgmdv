package com.trascender.gui.framework.abmStandard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.trascender.gui.framework.util.ConstantesTamanio;

public class PnlAyuda extends JPanel {

	private static final long serialVersionUID = -7047502112099900789L;
	
	private JLabel lblLeyendaAyuda;
	private Font fuente;

	public PnlAyuda() {
		this.init();
	}

	private void init() {
		this.setLayout(new BorderLayout());
		this.setSize(ConstantesTamanio.LBL_WIDTH * 2, ConstantesTamanio.LBL_HEIGHT);
		
		this.fuente = new Font("Verdana", 1, 11);
		
		this.lblLeyendaAyuda = new JLabel("Asiento DEVENGAMIENTO");
		this.lblLeyendaAyuda.setSize(ConstantesTamanio.LBL_WIDTH * 2, ConstantesTamanio.LBL_HEIGHT);
		this.lblLeyendaAyuda.setFont(fuente);
		this.lblLeyendaAyuda.setForeground(Color.BLUE);
		this.add(this.lblLeyendaAyuda, BorderLayout.CENTER);
		
		this.setVisible(false);
	}

	public JLabel getLblLeyendaAyuda() {
		return lblLeyendaAyuda;
	}

	public void setLblLeyendaAyuda(JLabel lblLeyendaAyuda) {
		this.lblLeyendaAyuda = lblLeyendaAyuda;
	}
	
	
}
