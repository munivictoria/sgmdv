package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.AsociacionCuentaLineaFacturaABMModel;
import com.trascender.gui.framework.main.AppManager;

public  class ModificarAsociacionCuentaLineaFactura extends ABMAsociacionCuentaLineaFactura {

	public AsociacionCuentaLineaFacturaABMModel abmModel = new AsociacionCuentaLineaFacturaABMModel();
	public ModificarAsociacionCuentaLineaFacturaView view;
	
	public ModificarAsociacionCuentaLineaFactura(JDialog owner) {
		this.view = new ModificarAsociacionCuentaLineaFacturaView(owner);
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
	public AsociacionCuentaLineaFacturaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarAsociacionCuentaLineaFacturaView getView() {
		return this.view;
	}
	
	void modificarAsociacionCuentaLineaFactura() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private ModificarAsociacionCuentaLineaFactura controller;
	
	public BtnModificarListener(ModificarAsociacionCuentaLineaFactura controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarAsociacionCuentaLineaFactura();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
