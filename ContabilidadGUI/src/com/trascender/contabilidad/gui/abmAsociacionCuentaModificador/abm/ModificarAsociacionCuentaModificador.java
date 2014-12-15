package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.AsociacionCuentaModificadorABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarAsociacionCuentaModificador extends ABMAsociacionCuentaModificador {
	
	private ModificarAsociacionCuentaModificadorView view;
	private AsociacionCuentaModificadorABMModel abmModel = new AsociacionCuentaModificadorABMModel();
	
	public ModificarAsociacionCuentaModificador(JDialog owner) {
		this.view = new ModificarAsociacionCuentaModificadorView(owner);
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
	public AsociacionCuentaModificadorABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarAsociacionCuentaModificadorView getView() {
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

	private ModificarAsociacionCuentaModificador controller;
	
	public BtnModificarListener(ModificarAsociacionCuentaModificador controller) {
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