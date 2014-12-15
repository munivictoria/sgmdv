package com.trascender.contabilidad.gui.abmParametroAsociacion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmParametroAsociacion.ParametroAsociacionABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarParametroAsociacion extends ABMParametroAsociacion {

	private ModificarParametroAsociacionView view;
	private ParametroAsociacionABMModel abmModel = new ParametroAsociacionABMModel();
	
	public ModificarParametroAsociacion(JDialog owner) {
		this.view = new ModificarParametroAsociacionView(owner);
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
	public ParametroAsociacionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarParametroAsociacionView getView() {
		return this.view;
	}
	
	void modificarParametroAsociacion() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {

	private ModificarParametroAsociacion controller;
	
	public BtnModificarListener(ModificarParametroAsociacion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarParametroAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}