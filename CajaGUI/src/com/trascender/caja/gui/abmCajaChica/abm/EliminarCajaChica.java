package com.trascender.caja.gui.abmCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmCajaChica.CajaChicaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarCajaChica extends ABMCajaChica {
	
	private EliminarCajaChicaView view;
	private CajaChicaABMModel abmModel = new CajaChicaABMModel();
	
	public EliminarCajaChica(JDialog owner) {
		this.view = new EliminarCajaChicaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setDisabledAll();
		this.setListeners();
	}
	
	private void setDisabledAll() {
		this.getView().getTfNombre().setEditable(false);
		this.getView().getTfImporteReposicion().setEditable(false);
		}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public CajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarCajaChicaView getView() {
		return this.view;
	}
	
	void eliminarCajaChica() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

}

class BtnEliminarListener implements ActionListener {
	private EliminarCajaChica controller;
	public BtnEliminarListener(EliminarCajaChica controller) {
		this.controller = controller;
	}	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}