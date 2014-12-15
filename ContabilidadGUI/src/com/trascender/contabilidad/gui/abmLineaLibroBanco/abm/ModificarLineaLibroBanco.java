package com.trascender.contabilidad.gui.abmLineaLibroBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmLineaLibroBanco.LineaLibroBancoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarLineaLibroBanco extends ABMLineaLibroBanco {

	public LineaLibroBancoABMModel abmModel = new LineaLibroBancoABMModel();
	public ModificarLineaLibroBancoView view;
	
	public ModificarLineaLibroBanco(JDialog owner) {
		
		this.view = new ModificarLineaLibroBancoView(owner);
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
	public LineaLibroBancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMLineaLibroBancoView getView() {
		return this.view;
	}
	
	void modificarLineaLibroBanco() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {
	private ModificarLineaLibroBanco controller;
	
	public BtnModificarListener(ModificarLineaLibroBanco controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarLineaLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
