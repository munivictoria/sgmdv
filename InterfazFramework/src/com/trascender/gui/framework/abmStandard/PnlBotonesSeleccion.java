package com.trascender.gui.framework.abmStandard;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class PnlBotonesSeleccion extends JPanel{

	private static final long serialVersionUID = 2900447479042330649L;
	
	private JButton btnSeleccionar;
	private JButton btnLimpiar;
	
	
	public PnlBotonesSeleccion(){
		this.init();
	}


	private void init() {
		this.setLayout(null);
		this.setSize(ConstantesTamanio.BTN_SELECCION_WIDTH*2+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		
		Dimension dimBotones = new Dimension(ConstantesTamanio.BTN_SELECCION_WIDTH,ConstantesTamanio.BTN_SELECCION_HEIGHT);
		
		this.btnSeleccionar = new JButton();
		this.btnSeleccionar.setText(Messages.getString("Application.btnSeleccionarObjeto"));
		this.btnSeleccionar.setToolTipText(Messages.getString("Application.btnSeleccionarObjetoToolTip"));
		this.btnSeleccionar.setPreferredSize(dimBotones);
		this.btnSeleccionar.setBounds(0, 0, ConstantesTamanio.BTN_SELECCION_WIDTH, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		this.add(this.btnSeleccionar);
		
		this.btnLimpiar = new JButton();
		this.btnLimpiar.setText(Messages.getString("Application.btnLimpiarObjeto"));
		this.btnLimpiar.setToolTipText(Messages.getString("Application.btnLimpiarObjetoToolTip"));
		this.btnLimpiar.setPreferredSize(dimBotones);
		this.btnLimpiar.setBounds(ConstantesTamanio.BTN_SELECCION_WIDTH+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN, 0, ConstantesTamanio.BTN_SELECCION_WIDTH, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		this.add(this.btnLimpiar);
	}


	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}


	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}
	
}
