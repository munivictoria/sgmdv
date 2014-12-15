package com.trascender.contabilidad.gui.abmBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmBanco.BancoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarBanco extends ABMBanco {

	private ModificarBancoView view;
	private BancoABMModel abmModel = new BancoABMModel();
	
	public ModificarBanco(JDialog owner) {
		this.view = new ModificarBancoView(owner);
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
	public BancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMBancoView getView() {
		return view;
	}

	void modificarBanco() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {
	private ModificarBanco controller;
	
	public BtnModificarListener(ModificarBanco controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
