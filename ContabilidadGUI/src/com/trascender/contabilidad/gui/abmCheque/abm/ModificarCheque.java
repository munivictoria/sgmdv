package com.trascender.contabilidad.gui.abmCheque.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCheque.ChequeABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarCheque extends ABMCheque {

	private ModificarChequeView view;
	private ChequeABMModel abmModel = new ChequeABMModel();
	
	public ModificarCheque(JDialog owner) {
		this.view = new ModificarChequeView(owner);
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
	public ChequeABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarChequeView getView() {
		return this.view;
	}

	void modificarCheque() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private ModificarCheque controller;
	
	public BtnModificarListener(ModificarCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
