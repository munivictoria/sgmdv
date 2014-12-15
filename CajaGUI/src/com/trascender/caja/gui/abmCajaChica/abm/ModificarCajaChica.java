package com.trascender.caja.gui.abmCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmCajaChica.CajaChicaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarCajaChica extends ABMCajaChica {

	private ModificarCajaChicaView view;
	private CajaChicaABMModel abmModel = new CajaChicaABMModel();
	
	public ModificarCajaChica (JDialog owner) {
		this.view = new ModificarCajaChicaView(owner);
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
	public CajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarCajaChicaView getView() {
		return view;
	}

	void modificarCajaChica() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {
	private ModificarCajaChica controller;
	
	public BtnModificarListener(ModificarCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
