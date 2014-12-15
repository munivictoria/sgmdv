package com.trascender.contabilidad.gui.abmParametroAsociacion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmParametroAsociacion.ParametroAsociacionABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarParametroAsociacion extends ABMParametroAsociacion {

	private EliminarParametroAsociacionView view;
	private ParametroAsociacionABMModel abmModel = new ParametroAsociacionABMModel();
	
	public EliminarParametroAsociacion(JDialog owner) {
		this.view = new EliminarParametroAsociacionView(owner);
		this.init();
	}
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfCuenta().setEditable(false);
		this.getView().getTfPorcentaje().setEditable(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public ParametroAsociacionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarParametroAsociacionView getView() {
		return this.view;
	}
	
	void eliminarParametroAsociacion() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
}

class BtnEliminarListener implements ActionListener {

	private EliminarParametroAsociacion controller;
	
	public BtnEliminarListener(EliminarParametroAsociacion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarParametroAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}