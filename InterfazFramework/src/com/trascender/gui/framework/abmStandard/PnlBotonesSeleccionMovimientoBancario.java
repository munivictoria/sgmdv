package com.trascender.gui.framework.abmStandard;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.trascender.gui.framework.recursos.Messages;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class PnlBotonesSeleccionMovimientoBancario extends JPanel{

	private static final long serialVersionUID = 1L;

	private JButton btnSeleccionarCheque;
	private JButton btnSeleccionarDebitoBancario;
	private JButton btnLimpiar;
	 
	public PnlBotonesSeleccionMovimientoBancario() {
		this.init();
	}
	
	private void init() {
		this.setLayout(null);
		this.setSize(ConstantesTamanio.BTN_SELECCION_WIDTH*3+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN *2, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		Dimension dimBotones = new Dimension(ConstantesTamanio.BTN_SELECCION_WIDTH,ConstantesTamanio.BTN_SELECCION_HEIGHT);
		
		this.btnSeleccionarCheque = new JButton();
		this.btnSeleccionarCheque.setText(Messages.getString("Application.btnSeleccionarCheque"));
		this.btnSeleccionarCheque.setToolTipText(Messages.getString("Application.btnSeleccionarChequeToolTip"));
		this.btnSeleccionarCheque.setPreferredSize(dimBotones);
		this.btnSeleccionarCheque.setBounds(0, 0, ConstantesTamanio.BTN_SELECCION_WIDTH, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		this.add(this.btnSeleccionarCheque);
		
		this.btnSeleccionarDebitoBancario = new JButton();
		this.btnSeleccionarDebitoBancario.setText(Messages.getString("Application.btnSeleccionarDebitoBancario"));
		this.btnSeleccionarDebitoBancario.setToolTipText(Messages.getString("Application.btnSeleccionarDebitoBancarioToolTip"));
		this.btnSeleccionarDebitoBancario.setPreferredSize(dimBotones);
		this.btnSeleccionarDebitoBancario.setBounds(ConstantesTamanio.BTN_SELECCION_WIDTH+ConstantesSeparacion.SEPARADOR_HORIZONTAL_BTN , 0, ConstantesTamanio.BTN_SELECCION_WIDTH, ConstantesTamanio.BTN_SELECCION_HEIGHT);
		this.add(this.btnSeleccionarDebitoBancario);
		
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

	public JButton getBtnSeleccionarCheque() {
		return btnSeleccionarCheque;
	}

	public JButton getBtnSeleccionarDebitoBancario() {
		return btnSeleccionarDebitoBancario;
	}
}