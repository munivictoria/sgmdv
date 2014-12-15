package com.trascender.gui.framework.abmStandard;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class PnlBotonesSeleccionPersona extends JPanel{

	private static final long serialVersionUID = 5513255615940373091L;

	private JButton btnSeleccionarPersonaFisica;
	private JButton btnSeleccionarPersonaJuridica;
	private JButton btnLimpiar;
	
	public PnlBotonesSeleccionPersona() {
		this.init();
	}
	
	private void init() {
		this.setLayout(null);
		this.setSize(ConstantesTamanio.BTN_SELECCION_WIDTH*3+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN *2, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		Dimension dimBotones = new Dimension(ConstantesTamanio.BTN_SELECCION_WIDTH,ConstantesTamanio.BTN_SELECCION_HEIGHT);
		
		this.btnSeleccionarPersonaFisica = new JButton();
		this.btnSeleccionarPersonaFisica.setText(Messages.getString("Application.btnSeleccionarPersonaFisica"));
		this.btnSeleccionarPersonaFisica.setToolTipText(Messages.getString("Application.btnSeleccionarPersonaFisicaToolTip"));
		this.btnSeleccionarPersonaFisica.setPreferredSize(dimBotones);
		this.btnSeleccionarPersonaFisica.setBounds(0, 0, ConstantesTamanio.BTN_SELECCION_WIDTH, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		this.add(this.btnSeleccionarPersonaFisica);
		
		this.btnSeleccionarPersonaJuridica = new JButton();
		this.btnSeleccionarPersonaJuridica.setText(Messages.getString("Application.btnSeleccionarPersonaJuridica"));
		this.btnSeleccionarPersonaJuridica.setToolTipText(Messages.getString("Application.btnSeleccionarPersonaJuridicaToolTip"));
		this.btnSeleccionarPersonaJuridica.setPreferredSize(dimBotones);
		this.btnSeleccionarPersonaJuridica.setBounds(ConstantesTamanio.BTN_SELECCION_WIDTH+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN , 0, ConstantesTamanio.BTN_SELECCION_WIDTH, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		this.add(this.btnSeleccionarPersonaJuridica);
		
		this.btnLimpiar = new JButton();
		this.btnLimpiar.setText(Messages.getString("Application.btnLimpiarObjeto"));
		this.btnLimpiar.setToolTipText(Messages.getString("Application.btnLimpiarObjetoToolTip"));
		this.btnLimpiar.setPreferredSize(dimBotones);
		this.btnLimpiar.setBounds(ConstantesTamanio.BTN_SELECCION_WIDTH*2+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN*2, 0, ConstantesTamanio.BTN_SELECCION_WIDTH, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		this.add(this.btnLimpiar);
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public JButton getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public JButton getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}
}
