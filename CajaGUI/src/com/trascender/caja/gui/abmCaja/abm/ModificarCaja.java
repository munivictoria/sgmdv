package com.trascender.caja.gui.abmCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmCaja.CajaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarCaja extends ABMCaja {
	
	private ModificarCajaView view;
	private CajaABMModel abmModel = new CajaABMModel();
	
	public ModificarCaja(JDialog owner) {
		this.view = new ModificarCajaView(owner);
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
	
	@Override
	public CajaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarCajaView getView() {
		return this.view;
	}

	void modificarCaja() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {
	private ModificarCaja controller;
	public BtnModificarListener(ModificarCaja controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}