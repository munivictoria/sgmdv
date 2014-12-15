package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.AsociacionCuentaConceptoSelladoAdministrativoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarAsociacionCuentaConceptoIngresoVario extends ABMAsociacionCuentaConceptoSelladoAdministrativo {

	private ModificarAsociacionCuentaConceptoSelladoAdministrativoView view;
	private AsociacionCuentaConceptoSelladoAdministrativoABMModel abmModel = new AsociacionCuentaConceptoSelladoAdministrativoABMModel();
	
	public ModificarAsociacionCuentaConceptoIngresoVario(JDialog owner) {
		this.view = new ModificarAsociacionCuentaConceptoSelladoAdministrativoView(owner);
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
	public AsociacionCuentaConceptoSelladoAdministrativoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMAsociacionCuentaConceptoSelladoAdministrativoView getView() {
		return this.view;
	}

	void modificarAsociacionCuentaConceptoSelladoAdministrativo() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private ModificarAsociacionCuentaConceptoIngresoVario controller;
	
	public BtnModificarListener(
			ModificarAsociacionCuentaConceptoIngresoVario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarAsociacionCuentaConceptoSelladoAdministrativo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
