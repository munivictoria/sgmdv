package com.trascender.gui.framework.abmStandard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class PnlABMPie extends JPanel {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnImprimir;
	
	public PnlABMPie() {
		this.init();
	}
	
	private void init() {
		
		GridBagLayout gblPie = new GridBagLayout();
		this.setLayout(gblPie);
		
		GridBagConstraints gbcPnlPie = new GridBagConstraints();
		gbcPnlPie.anchor = GridBagConstraints.PAGE_END;
		
		Border bordePie = BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK),
				BorderFactory.createEmptyBorder(
						ConstantesTamanio.PNL_PIE_EMPTY_BORDER,
						ConstantesTamanio.PNL_PIE_EMPTY_BORDER,
						ConstantesTamanio.PNL_PIE_EMPTY_BORDER,
						ConstantesTamanio.PNL_PIE_EMPTY_BORDER));
		this.setBorder(bordePie);
		
		// BOTONES (FILA 1)
		GridBagConstraints gbcBtns = new GridBagConstraints();
		
		int numColumna = -1;
		
		// Propiedades comunes
		gbcBtns.gridy = 0;
		gbcBtns.weightx = 0;
		gbcBtns.anchor = GridBagConstraints.EAST;
		gbcBtns.insets = new Insets(0,ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN,0,0);
		
		// BOTON IMPRIMIR
		numColumna++;
		this.btnImprimir = new JButton();
		this.btnImprimir.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		this.btnImprimir.setText(Messages.getString("Application.btnImprimir"));
		this.btnImprimir.setMnemonic(Messages.getString("Application.btnImprimirMnemonic").charAt(0));
		this.btnImprimir.setToolTipText(Messages.getString("Application.btnImprimirToolTip"));
		gbcBtns.gridx = numColumna;
		gbcBtns.weightx = 0;
		this.add(this.btnImprimir,gbcBtns);
		
		// BOTON ACEPTAR
		numColumna++;
		this.btnAceptar = new JButton();
		this.btnAceptar.setText(Messages.getString("Application.btnAceptar"));
		this.btnAceptar.setMnemonic(Messages.getString("Application.btnAceptarMnemonic").charAt(0));
		this.btnAceptar.setToolTipText(Messages.getString("Application.btnAceptarToolTip"));
		this.btnAceptar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		gbcBtns.gridx = numColumna;
		gbcBtns.weightx = 150; // ancho de la columna (se necesita para que quede alineado a la izquierda)
		this.add(this.btnAceptar,gbcBtns);
		
		// BOTON CANCELAR
		numColumna++;
		this.btnCancelar = new JButton();
		this.btnCancelar.setPreferredSize(new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT));
		this.btnCancelar.setText(Messages.getString("Application.btnCancelar"));
		this.btnCancelar.setMnemonic(Messages.getString("Application.btnCancelarMnemonic").charAt(0));
		this.btnCancelar.setToolTipText(Messages.getString("Application.btnCancelarToolTip"));
		gbcBtns.gridx = numColumna;
		gbcBtns.weightx = 0;
		this.add(this.btnCancelar,gbcBtns);
		
		this.btnImprimir.setVisible(false); 
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	public JButton getBtnImprimir() {
		return btnImprimir;
	}
}
