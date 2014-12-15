package com.trascender.gui.framework.abmStandard;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class PnlAdminBotonesBusqueda extends JPanel {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	private JButton btnBuscar;
	private JButton btnReiniciar;
	
	public PnlAdminBotonesBusqueda() {
		this.init();
	}
	
	private void init() {
		this.setLayout(null);
		this.setSize(ConstantesTamanio.BTN_WIDTH*2+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN, ConstantesTamanio.BTN_HEIGHT);
		
		Dimension dimBotones = new Dimension(ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT);
		
		this.btnBuscar = new JButton(Messages.getString("Application.btnBuscar"));
		this.btnBuscar.setToolTipText(Messages.getString("Application.btnBuscarToolTip"));
		this.btnBuscar.setMnemonic(Messages.getString("Application.btnBuscarMnemonic").charAt(0));
		this.btnBuscar.setPreferredSize(dimBotones);
		this.btnBuscar.setBounds(0, 0, ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT);
		this.add(this.btnBuscar);
		
		this.btnReiniciar = new JButton(Messages.getString("Application.btnReiniciar"));
		this.btnReiniciar.setToolTipText(Messages.getString("Application.btnReiniciarToolTip"));
		this.btnReiniciar.setMnemonic(Messages.getString("Application.btnReiniciarMnemonic").charAt(0));
		this.btnReiniciar.setPreferredSize(dimBotones);
		this.btnReiniciar.setBounds(ConstantesTamanio.BTN_WIDTH+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN, 0, ConstantesTamanio.BTN_WIDTH, ConstantesTamanio.BTN_HEIGHT);
		this.add(this.btnReiniciar);
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnReiniciar() {
		return btnReiniciar;
	}
	
}
