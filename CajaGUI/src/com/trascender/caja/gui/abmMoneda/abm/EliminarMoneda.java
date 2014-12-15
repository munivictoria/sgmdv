package com.trascender.caja.gui.abmMoneda.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmMoneda.MonedaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarMoneda extends ABMMoneda {

	private EliminarMonedaView view;
	private MonedaABMModel abmModel = new MonedaABMModel();
	
	public EliminarMoneda(JDialog owner) {
		this.view = new EliminarMonedaView(owner);
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
		this.getView().getCbTipo().setEnabled(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	public MonedaABMModel getAbmModel() {
		return abmModel;
	}
	public EliminarMonedaView getView() {
		return view;
	}
	
	void eliminarMoneda() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
}

class BtnEliminarListener implements ActionListener {
	private EliminarMoneda controller;
	public BtnEliminarListener(EliminarMoneda controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarMoneda();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
