package com.trascender.contabilidad.gui.abmParametroAsociacion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmParametroAsociacion.ParametroAsociacionABMModel;
import com.trascender.contabilidad.recurso.persistent.ParametroAsociacion;
import com.trascender.gui.framework.main.AppManager;

public class AgregarParametroAsociacion extends ABMParametroAsociacion {

	private AgregarParametroAsociacionView view;
	private ParametroAsociacionABMModel abmModel = new ParametroAsociacionABMModel();

	public AgregarParametroAsociacion(JDialog owner) {
		this.view = new AgregarParametroAsociacionView(owner);
		this.abmModel.setObjetoABM(new ParametroAsociacion());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	@Override
	public ParametroAsociacionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarParametroAsociacionView getView() {
		return this.view;
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}

	
	void agregarParametroAsociacion() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarListener implements ActionListener {

	private AgregarParametroAsociacion controller;
	
	public BtnAgregarListener(AgregarParametroAsociacion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarParametroAsociacion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
