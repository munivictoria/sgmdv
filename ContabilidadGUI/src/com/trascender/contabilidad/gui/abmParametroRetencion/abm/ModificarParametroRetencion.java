package com.trascender.contabilidad.gui.abmParametroRetencion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmParametroRetencion.ParametroRetencionABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarParametroRetencion extends ABMParametroRetencion {

	private ModificarParametroRetencionView view;
	private ParametroRetencionABMModel abmModel = new ParametroRetencionABMModel();
	
	public ModificarParametroRetencion(JDialog owner) {
		this.view = new ModificarParametroRetencionView(owner);
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
	public ParametroRetencionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarParametroRetencionView getView() {
		return this.view;
	}
	
	void modificarParametroRetencion() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnModificarListener implements ActionListener {

	private ModificarParametroRetencion controller;
	
	public BtnModificarListener(ModificarParametroRetencion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarParametroRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

