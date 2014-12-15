package com.trascender.gui.framework.abmStandard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;


public class PnlTablaCompleto extends JPanel {
	
	private static final long serialVersionUID = 8598835624166875956L;
	
	private JLabel lblTitulo;
	private PnlTabla pnlTabla;
	private PnlVerticalBotones pnlVerticalBotones;
	
	public PnlTablaCompleto() {
		this.pnlVerticalBotones = new PnlVerticalBotones();
		this.init();
	}
	
	public PnlTablaCompleto(JButton... pBotonesAdicionales) {
		pnlVerticalBotones = new PnlVerticalBotones(pBotonesAdicionales);
		this.init();
	}
	
	private void init() {
		Border bordePanel = BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY), 
				BorderFactory.createEmptyBorder(ConstantesSeparacion.SEPARADOR_VERTICAL * 2, 0, 0, 0));
		
		Border bordePnlBotones = BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
				BorderFactory.createEmptyBorder(120, 120, 120, 120));
		
		this.setLayout(new BorderLayout());
		this.setBorder(bordePanel);
		
		this.lblTitulo = new JLabel();
		this.lblTitulo.setFont(new Font("Verdana",1,13));
		this.lblTitulo.setPreferredSize(new Dimension(ConstantesTamanio.LBL_WIDTH, ConstantesTamanio.LBL_HEIGHT));
		this.lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		this.add(this.lblTitulo, BorderLayout.NORTH);
		
		this.pnlTabla =  new PnlTabla();
		this.add(this.pnlTabla, BorderLayout.CENTER);
		
		this.pnlVerticalBotones.setBorder(bordePnlBotones);
		this.add(this.pnlVerticalBotones, BorderLayout.EAST);
		
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public PnlTabla getPnlTabla() {
		return pnlTabla;
	}

	public PnlVerticalBotones getPnlVerticalBotones() {
		return pnlVerticalBotones;
	}
	
}
