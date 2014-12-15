package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.AsociacionCuentaTipoTasaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarAsociacionCuentaTipoTasa extends ABMAsociacionCuentaTipoTasa {

	private EliminarAsociacionCuentaTipoTasaView view;
	private AsociacionCuentaTipoTasaABMModel abmModel = new AsociacionCuentaTipoTasaABMModel();
	
	
	public EliminarAsociacionCuentaTipoTasa(JDialog pDialog) {
		this.view = new EliminarAsociacionCuentaTipoTasaView(pDialog);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfTipoTasa().setEnabled(false);
		this.getView().getPnlBtnSeleccionTipoTasa().setVisible(false);
		
		this.getView().getTfCuenta().setEnabled(false);
		this.getView().getPnlBtnSeleccionCuenta().setVisible(false);
		
		this.getView().getTfCuentaAtrasada().setEnabled(false);
		this.getView().getPnlBtnSeleccionCuentaAtrasada().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public AsociacionCuentaTipoTasaABMModel getAbmModel() {
		return abmModel;
	}

	@Override
	public ABMAsociacionCuentaTipoTasaView getView() {
		return view;
	}
	
	void eliminarAsociacionCuentaTipoTasa() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

}

class BtnEliminarListener implements ActionListener {

	private EliminarAsociacionCuentaTipoTasa controller;
	
	public BtnEliminarListener(EliminarAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarAsociacionCuentaTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}