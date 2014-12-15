package com.trascender.contabilidad.gui.abmCuentaBancaria.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCuentaBancaria.CuentaBancariaAbmModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarCuentaBancaria extends ABMCuentaBancaria {
	
	private ModificarCuentaBancariaView view;
	private CuentaBancariaAbmModel abmModel = new CuentaBancariaAbmModel();

	public ModificarCuentaBancaria(JDialog owner) {
		this.view = new ModificarCuentaBancariaView(owner);
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
	public CuentaBancariaAbmModel getAbmModel() {
		return abmModel;
	}

	@Override
	public ABMCuentaBancariaView getView() {
		return view;
	}
	
	void modificarCuentaBancaria() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {

	private ModificarCuentaBancaria controller;
	
	public BtnModificarListener(ModificarCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}