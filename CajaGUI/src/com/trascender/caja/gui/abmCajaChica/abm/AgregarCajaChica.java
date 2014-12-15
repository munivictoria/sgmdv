package com.trascender.caja.gui.abmCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmCajaChica.CajaChicaABMModel;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.gui.framework.main.AppManager;

public class AgregarCajaChica extends ABMCajaChica {

	private AgregarCajaChicaView view;
	private CajaChicaABMModel abmModel = new CajaChicaABMModel();
	
	public AgregarCajaChica(JDialog owner) {
		this.view = new AgregarCajaChicaView(owner);
		this.abmModel.setObjetoABM(new CajaChica());
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
	public CajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarCajaChicaView getView() {
		return  this.view;
	}

	void agregarCajaChica() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.abmModel.agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarCajaChica controller;
	public BtnAgregarListener(AgregarCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}