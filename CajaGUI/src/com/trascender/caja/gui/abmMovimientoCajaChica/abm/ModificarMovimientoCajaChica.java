package com.trascender.caja.gui.abmMovimientoCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmMovimientoCajaChica.MovimientoCajaChicaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarMovimientoCajaChica extends ABMMovimientoCajaChica {

	private ModificarMovimientoCajaChicaView view;
	private MovimientoCajaChicaABMModel abmModel = new MovimientoCajaChicaABMModel();
	
	public ModificarMovimientoCajaChica(JDialog owner) {
		this.view = new ModificarMovimientoCajaChicaView(owner);
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
	public MovimientoCajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarMovimientoCajaChicaView getView() {
		return this.view;
	}
	
	void modificarMovimietnoCajaChica() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {
	private ModificarMovimientoCajaChica controller;
	public BtnModificarListener(ModificarMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarMovimietnoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}