package com.trascender.contabilidad.gui.abmCuentaBancaria.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCuentaBancaria.CuentaBancariaAbmModel;
import com.trascender.contabilidad.recurso.persistent.CuentaBancaria;
import com.trascender.gui.framework.main.AppManager;

public class AgregarCuentaBancaria extends ABMCuentaBancaria {

	private AgregarCuentaBancariaView view;
	private CuentaBancariaAbmModel abmModel = new CuentaBancariaAbmModel();
	
	
	public AgregarCuentaBancaria(JDialog owner) throws Exception {
		this.view = new AgregarCuentaBancariaView(owner);
		this.abmModel.setObjetoABM(new CuentaBancaria());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	@Override
	public CuentaBancariaAbmModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarCuentaBancariaView getView() {
		return view;
	}

	void agregarCuentaBancaria() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnAgregarListener implements ActionListener {

	private AgregarCuentaBancaria controller;
	
	public BtnAgregarListener(AgregarCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}