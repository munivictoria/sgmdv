package com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaInteresRecargo.AsociacionCuentaInteresRecargoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarAsociacionCuentaInteresRecargo extends ABMAsociacionCuentaInteresRecargo {
	
	private EliminarAsociacionCuentaInteresRecargoView view;
	private AsociacionCuentaInteresRecargoABMModel abmModel = new AsociacionCuentaInteresRecargoABMModel();
	
	public EliminarAsociacionCuentaInteresRecargo(JDialog owner) {
		this.view = new EliminarAsociacionCuentaInteresRecargoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfInteresRecargo().setEnabled(false);
		this.getView().getPnlBtnSeleccionInteresRecargo().setVisible(false);
		
		this.getView().getTfCuenta().setEnabled(false);
		this.getView().getPnlBtnSeleccionCuenta().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public AsociacionCuentaInteresRecargoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarAsociacionCuentaInteresRecargoView getView() {
		return this.view;
	}

	void eliminarAsociacionCuentaInteresRecargo() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

}

class BtnEliminarListener implements ActionListener {

	private EliminarAsociacionCuentaInteresRecargo controller;
	
	public BtnEliminarListener(EliminarAsociacionCuentaInteresRecargo controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarAsociacionCuentaInteresRecargo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
