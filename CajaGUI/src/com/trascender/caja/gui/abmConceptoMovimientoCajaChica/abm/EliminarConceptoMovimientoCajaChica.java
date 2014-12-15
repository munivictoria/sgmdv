package com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.ConceptoMovimientoCajaChicaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarConceptoMovimientoCajaChica extends ABMConceptoMovimientoCajaChica {

	private EliminarConceptoMovimientoCajaChicaView view;
	private ConceptoMovimientoCajaChicaABMModel abmModel = new ConceptoMovimientoCajaChicaABMModel();
	
	public EliminarConceptoMovimientoCajaChica(JDialog owner) {
		this.view = new EliminarConceptoMovimientoCajaChicaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.setDisabledAll();
	}
	
	private void setDisabledAll() {
		this.getView().getTfNombre().setEnabled(false);
		this.getView().getTfDescripcion().setEnabled(false);
		this.getView().getCbTipo().setEnabled(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public ConceptoMovimientoCajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarConceptoMovimientoCajaChicaView getView() {
		return this.view;
	}
	
	void eliminarConceptoMovimientoCajaChica() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
}

class BtnEliminarListener implements ActionListener {
	private EliminarConceptoMovimientoCajaChica controller;
	public BtnEliminarListener(EliminarConceptoMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarConceptoMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
