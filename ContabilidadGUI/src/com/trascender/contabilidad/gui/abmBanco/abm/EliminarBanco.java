package com.trascender.contabilidad.gui.abmBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmBanco.BancoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarBanco extends ABMBanco {
	
	private EliminarBancoView view;
	private BancoABMModel abmModel = new BancoABMModel();

	public EliminarBanco(JDialog owner) {
		this.view = new EliminarBancoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.setDisabledAll();
	}
	
	private void setDisabledAll() {
		this.getView().getTfNombre().setEnabled(false);
		this.getView().getTfSucursal().setEnabled(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public BancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMBancoView getView() {
		return this.view;
	}
	
	void eliminarBanco() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
}

class BtnEliminarListener implements ActionListener {

	private EliminarBanco controller;
	
	public BtnEliminarListener(EliminarBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
