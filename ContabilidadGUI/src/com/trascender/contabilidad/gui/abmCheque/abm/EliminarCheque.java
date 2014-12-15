package com.trascender.contabilidad.gui.abmCheque.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCheque.ChequeABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarCheque extends ABMCheque {

	private EliminarChequeView view;
	private ChequeABMModel abmModel = new ChequeABMModel();
	
	public EliminarCheque(JDialog owner) {
		this.view = new EliminarChequeView(owner);
		this.init();
	}
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfNumeroCheque().setEditable(false);
		this.getView().getTfFechaEmision().setEditable(false);
		this.getView().getTfFechaPago().setEditable(false);
		this.getView().getTfImporte().setEditable(false);
		this.getView().getChkPostdatado().setEnabled(false);
		this.getView().getTfCuentaBancaria().setEditable(false);
		
		this.getView().getPnlBotonesSeleccion().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public ChequeABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarChequeView getView() {
		return this.view;
	}

	void eliminarCheque() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
}

class BtnEliminarListener implements ActionListener {

	private EliminarCheque controller;
	
	public BtnEliminarListener(EliminarCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
