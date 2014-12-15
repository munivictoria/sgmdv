package com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaModificador.AsociacionCuentaModificadorABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarAsociacionCuentaModificador extends ABMAsociacionCuentaModificador {
	
	private EliminarAsociacionCuentaModificadorView view;
	private AsociacionCuentaModificadorABMModel abmModel = new AsociacionCuentaModificadorABMModel();
	
	public EliminarAsociacionCuentaModificador(JDialog owner) {
		this.view = new EliminarAsociacionCuentaModificadorView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfTipoModificador().setEnabled(false);
		this.getView().getPnlBtnSeleccionTipoModificador().setVisible(false);
		
		this.getView().getTfCuenta().setEnabled(false);
		this.getView().getPnlBtnSeleccionCuenta().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public AsociacionCuentaModificadorABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarAsociacionCuentaModificadorView getView() {
		return this.view;
	}

	void eliminarAsociacionCuentaModificador() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

}

class BtnEliminarListener implements ActionListener {

	private EliminarAsociacionCuentaModificador controller;
	
	public BtnEliminarListener(EliminarAsociacionCuentaModificador controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarAsociacionCuentaModificador();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
