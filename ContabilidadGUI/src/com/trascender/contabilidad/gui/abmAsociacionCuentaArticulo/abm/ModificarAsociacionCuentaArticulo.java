package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.AsociacionCuentaArticuloABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarAsociacionCuentaArticulo extends ABMAsociacionCuentaArticulo{
	
	private ModificarAsociacionCuentaArticuloView view;
	private AsociacionCuentaArticuloABMModel abmModel = new AsociacionCuentaArticuloABMModel();
	
	public ModificarAsociacionCuentaArticulo(JDialog dialog){
		view = new ModificarAsociacionCuentaArticuloView(dialog);
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
	
	void modificarAsociacionCuentaArticulo() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ABMAsociacionCuentaArticuloView getView() {
		return this.view;
	}

	@Override
	public AsociacionCuentaArticuloABMModel getAbmModel() {
		return this.abmModel;
	}

}

class BtnModificarListener implements ActionListener {

	private ModificarAsociacionCuentaArticulo controller;
	
	public BtnModificarListener(ModificarAsociacionCuentaArticulo controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarAsociacionCuentaArticulo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
