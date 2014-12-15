package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.AsociacionCuentaModificadorABMModel;
import com.trascender.contabilidad.recurso.persistent.CuentaModificador;
import com.trascender.gui.framework.main.AppManager;

public class AgregarAsociacionCuentaModificador extends ABMAsociacionCuentaModificador {
	
	private AgregarAsociacionCuentaModificadorView view;
	private AsociacionCuentaModificadorABMModel abmModel = new AsociacionCuentaModificadorABMModel();
	
	public AgregarAsociacionCuentaModificador(JDialog owner) {
		this.view = new AgregarAsociacionCuentaModificadorView(owner);
		this.getAbmModel().setObjetoABM(new CuentaModificador());
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
	public AsociacionCuentaModificadorABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarAsociacionCuentaModificadorView getView() {
		return this.view;
	}
	
	void agregarAsociacionCuentaModificador() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}


class BtnAgregarListener implements ActionListener {

	private AgregarAsociacionCuentaModificador controller;
	
	public BtnAgregarListener(AgregarAsociacionCuentaModificador controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacionCuentaModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}