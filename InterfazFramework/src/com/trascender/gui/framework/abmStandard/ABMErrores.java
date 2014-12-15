package com.trascender.gui.framework.abmStandard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;

public class ABMErrores {
	
	private ABMErroresView view;
	
	public ABMErrores(JDialog owner) {
		this.view = new ABMErroresView(owner);
		this.init();
	}
	
	public void open() {
		if (this.getView().getListaErrores() != null) {
			this.getView().setVisible(true);
		}
	}
	
	public void close() {
		this.getView().dispose();
	}
	
	protected void init() {
		this.getView().getTaErrores().addKeyListener(new TaErroresKeyListener(this));
		this.getView().getPnlPie().getBtnCancelar().addActionListener(new BtnCancelarListener(this.getView()));
	}
	
	public ABMErroresView getView() {
		return this.view;
	}

}

class TaErroresKeyListener extends KeyAdapter {
	private ABMErrores controller;
	
	public TaErroresKeyListener(ABMErrores controller) {
		this.controller = controller;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.controller.close();
		}
	}
}