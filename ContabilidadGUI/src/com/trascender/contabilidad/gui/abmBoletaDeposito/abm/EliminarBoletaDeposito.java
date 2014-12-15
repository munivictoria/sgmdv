package com.trascender.contabilidad.gui.abmBoletaDeposito.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmBoletaDeposito.BoletaDepositoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarBoletaDeposito extends ABMBoletaDeposito {

	private EliminarBoletaDepositoView view;
	private BoletaDepositoABMModel abmModel = new BoletaDepositoABMModel();
	
	public EliminarBoletaDeposito(JDialog owner) {
		this.view = new EliminarBoletaDepositoView(owner);
		this.init();
	}
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.visibleAll();
	}
	
	private void visibleAll() {
		this.getView().getPnlBtnSeleccionCuentaAfectada().setVisible(false);
		this.getView().getPnlBtnSeleccionCuentaBancaria().setVisible(false);
	}
	private void disabledAll() {
		this.getView().getTfNumeroBoleta().setEditable(false);
		this.getView().getTfImporte().setEditable(false);
		this.getView().getTfFecha().setEditable(false);
		this.getView().getTfCuentaBancaria().setEditable(false);
		this.getView().getTfCuentaAfectada().setEditable(false);
		this.getView().getTaObservaciones().setEditable(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public BoletaDepositoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarBoletaDepositoView getView() {
		return this.view;
	}
	
	void eliminarBoletaDeposito() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
}

class BtnEliminarListener implements ActionListener {

	private EliminarBoletaDeposito controller;
	
	public BtnEliminarListener(EliminarBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarBoletaDeposito();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
