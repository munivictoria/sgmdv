package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.AsociacionCuentaInteresRecargoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarAsociacionCuentaInteresRecargo extends ABMAsociacionCuentaInteresRecargo {
	
	private ModificarAsociacionCuentaInteresRecargoView view;
	private AsociacionCuentaInteresRecargoABMModel abmModel = new AsociacionCuentaInteresRecargoABMModel();
	
	public ModificarAsociacionCuentaInteresRecargo(JDialog owner) {
		this.view = new ModificarAsociacionCuentaInteresRecargoView(owner);
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
	public AsociacionCuentaInteresRecargoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarAsociacionCuentaInteresRecargoView getView() {
		return this.view;
	}
	
	void modificarAsociacionCuentaModificaodor() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}


class BtnModificarListener implements ActionListener {

	private ModificarAsociacionCuentaInteresRecargo controller;
	
	public BtnModificarListener(ModificarAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarAsociacionCuentaModificaodor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}