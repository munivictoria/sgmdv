package com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.LineaSubdiarioCajaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarLineaSubdiarioCaja extends ABMLineaSubdiarioCaja {
	
	private ModificarLineaSubdiarioCajaView view;
	private LineaSubdiarioCajaABMModel abmModel = new LineaSubdiarioCajaABMModel();
	
	public ModificarLineaSubdiarioCaja(JFrame owner) throws Exception {
		this.view = new ModificarLineaSubdiarioCajaView(owner);
		this.init();
	}
	
	public ModificarLineaSubdiarioCaja(JDialog owner) throws Exception {
		this.view = new ModificarLineaSubdiarioCajaView(owner);
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
	public LineaSubdiarioCajaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarLineaSubdiarioCajaView getView() {
		return this.view;
	}
	
	void modificarLineaSubdiarioCaja() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {
	private ModificarLineaSubdiarioCaja controller;
	
	public BtnModificarListener(ModificarLineaSubdiarioCaja controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarLineaSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
