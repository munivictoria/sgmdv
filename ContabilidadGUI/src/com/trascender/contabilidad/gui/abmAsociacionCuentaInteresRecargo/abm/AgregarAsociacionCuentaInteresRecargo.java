package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.AsociacionCuentaInteresRecargoABMModel;
import com.trascender.contabilidad.recurso.persistent.CuentaInteresRecargo;
import com.trascender.gui.framework.main.AppManager;

public class AgregarAsociacionCuentaInteresRecargo extends ABMAsociacionCuentaInteresRecargo {
	
	private AgregarAsociacionCuentaInteresRecargoView view;
	private AsociacionCuentaInteresRecargoABMModel abmModel = new AsociacionCuentaInteresRecargoABMModel();
	
	public AgregarAsociacionCuentaInteresRecargo(JDialog owner) {
		this.view = new AgregarAsociacionCuentaInteresRecargoView(owner);
		this.getAbmModel().setObjetoABM(new CuentaInteresRecargo());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}

	
	@Override
	public AsociacionCuentaInteresRecargoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarAsociacionCuentaInteresRecargoView getView() {
		return this.view;
	}
	
	void agregarAsociacionCuentaInteresRecargo() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}


class BtnAgregarListener implements ActionListener {

	private AgregarAsociacionCuentaInteresRecargo controller;
	
	public BtnAgregarListener(AgregarAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacionCuentaInteresRecargo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}