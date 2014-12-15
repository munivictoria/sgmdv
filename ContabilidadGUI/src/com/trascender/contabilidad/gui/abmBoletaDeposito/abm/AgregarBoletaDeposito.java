package com.trascender.contabilidad.gui.abmBoletaDeposito.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmBoletaDeposito.BoletaDepositoABMModel;
import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.gui.framework.main.AppManager;

public class AgregarBoletaDeposito extends ABMBoletaDeposito{

	private AgregarBoletaDepositoView view;
	private BoletaDepositoABMModel abmModel = new BoletaDepositoABMModel();
	
	public AgregarBoletaDeposito(JDialog owner) {
		this.view = new AgregarBoletaDepositoView(owner);
		this.abmModel.setObjetoABM(new BoletaDeposito());
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
	public BoletaDepositoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarBoletaDepositoView getView() {
		return this.view;
	}

	void agregarBoletaDeposito() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnAgregarListener implements ActionListener {

	private AgregarBoletaDeposito controller;
	
	public BtnAgregarListener(AgregarBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarBoletaDeposito();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}