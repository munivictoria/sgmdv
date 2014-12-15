package com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.ConceptoMovimientoCajaChicaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarConceptoMovimientoCajaChica extends ABMConceptoMovimientoCajaChica {
	
	private ModificarConceptoMovimientoCajaChicaView view;
	private ConceptoMovimientoCajaChicaABMModel abmModel = new ConceptoMovimientoCajaChicaABMModel();
	
	public ModificarConceptoMovimientoCajaChica(JDialog owner) {
		this.view = new ModificarConceptoMovimientoCajaChicaView(owner);
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
	public ConceptoMovimientoCajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarConceptoMovimientoCajaChicaView getView() {
		return this.view;
	}
	
	void modificarConceptoMovimientoCajaChica() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {
	private ModificarConceptoMovimientoCajaChica controller;
	public BtnModificarListener(ModificarConceptoMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarConceptoMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}