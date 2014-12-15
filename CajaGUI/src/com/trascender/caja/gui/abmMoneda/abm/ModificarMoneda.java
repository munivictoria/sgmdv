package com.trascender.caja.gui.abmMoneda.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmMoneda.MonedaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarMoneda extends ABMMoneda {

	private ModificarMonedaView view;
	private MonedaABMModel abmModel = new MonedaABMModel();
	
	public ModificarMoneda(JDialog owner) {
		this.view = new ModificarMonedaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}

	public MonedaABMModel getAbmModel() {
		return this.abmModel;
	}

	public ModificarMonedaView getView() {
		return this.view;
	}
	void modificarMoneda() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {
	private ModificarMoneda controller;
	public BtnModificarListener(ModificarMoneda controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarMoneda();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}