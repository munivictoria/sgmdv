package com.trascender.contabilidad.gui.abmCheque.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCheque.ChequeABMModel;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.gui.framework.main.AppManager;

public class AgregarCheque extends ABMCheque {

	private AgregarChequeView view;
	private ChequeABMModel abmModel = new ChequeABMModel();
	
	public AgregarCheque(JDialog owner) {
		this.view = new AgregarChequeView(owner);
		this.abmModel.setObjetoABM(new Cheque());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	
	@Override
	public ChequeABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarChequeView getView() {
		return this.view;
	}
	
	void agregarCheque() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnAgregarListener implements ActionListener {

	private AgregarCheque controller;
	
	public BtnAgregarListener(AgregarCheque controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarCheque();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}