package com.trascender.caja.gui.abmCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmCaja.CajaABMModel;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.gui.framework.main.AppManager;

public class AgregarCaja extends ABMCaja {

	private AgregarCajaView view;
	private CajaABMModel abmModel = new CajaABMModel();
	
	public AgregarCaja(JDialog owner) {
		this.view = new AgregarCajaView(owner);
		this.getAbmModel().setObjetoABM(new Caja());
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
	public CajaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarCajaView getView() {
		return this.view;
	}
	
	void agregarCaja() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarCaja controller;
	public BtnAgregarListener(AgregarCaja controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}