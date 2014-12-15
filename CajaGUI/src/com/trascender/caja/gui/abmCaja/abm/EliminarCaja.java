package com.trascender.caja.gui.abmCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmCaja.CajaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarCaja extends ABMCaja {
	
	private EliminarCajaView view;
	private CajaABMModel abmModel = new CajaABMModel();
	
	public EliminarCaja(JDialog owner) {
		this.view = new EliminarCajaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setDisabledAll();
		this.setListeners();
	}
	
	private void setDisabledAll() {
		this.getView().getTfIdentificador().setEnabled(false);
		this.getView().getTfIp().setEnabled(false);
		this.getView().getTfNombre().setEnabled(false);
		this.getView().getTfPuerto().setEnabled(false);
	}
	
	protected void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public CajaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMCajaView getView() {
		return this.view;
	}

	void eliminarCaja() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
}

class BtnEliminarListener implements ActionListener {
	private EliminarCaja controller;
	public BtnEliminarListener(EliminarCaja controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}