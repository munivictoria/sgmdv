package com.trascender.contabilidad.gui.abmCuentaBancaria.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCuentaBancaria.CuentaBancariaAbmModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarCuentaBancaria extends ABMCuentaBancaria {

	private EliminarCuentaBancariaView view;
	private CuentaBancariaAbmModel abmModel = new CuentaBancariaAbmModel();
	
	public EliminarCuentaBancaria(JDialog owner) {
		this.view = new EliminarCuentaBancariaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfTipoCuenta().setEnabled(false);
		this.getView().getTfNumero().setEnabled(false);
		this.getView().getTfBanco().setEnabled(false);
		this.getView().getChkPropia().setEnabled(false);
		this.getView().getTfTitularCuentaBancaria().setEnabled(false);
		
		this.getView().getPnlBotonesSeleccionBanco().setVisible(false);
		this.getView().getPnlBotonesSeleccionPersona().setVisible(false);
		
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	@Override
	public CuentaBancariaAbmModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMCuentaBancariaView getView() {
		return this.view;
	}
	
	void eliminarCuentaBancaria() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
}

class BtnEliminarListener implements ActionListener {

	private EliminarCuentaBancaria controller;
	
	public BtnEliminarListener(EliminarCuentaBancaria controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarCuentaBancaria();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}