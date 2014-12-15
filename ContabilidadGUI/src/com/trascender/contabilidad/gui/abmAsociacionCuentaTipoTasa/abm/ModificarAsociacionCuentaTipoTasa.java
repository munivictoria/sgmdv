package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.AsociacionCuentaTipoTasaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarAsociacionCuentaTipoTasa extends	ABMAsociacionCuentaTipoTasa {

	private ModificarAsociacionCuentaTipoTasaView view;
	private AsociacionCuentaTipoTasaABMModel abmModel = new AsociacionCuentaTipoTasaABMModel();
	
	public ModificarAsociacionCuentaTipoTasa(JDialog pDialog) {
		this.view = new ModificarAsociacionCuentaTipoTasaView(pDialog);
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
	public AsociacionCuentaTipoTasaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMAsociacionCuentaTipoTasaView getView() {
		return view;
	}
	
	void modificarAsociacionCuentaTipoTasa() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {

	private ModificarAsociacionCuentaTipoTasa controller;
	
	public BtnModificarListener(ModificarAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarAsociacionCuentaTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}