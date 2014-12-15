package com.trascender.contabilidad.gui.abmDebitoBancario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioABMModel;
import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioTableModel;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.gui.framework.main.AppManager;

public class AgregarDebitoBancario extends ABMDebitoBancario {

	private AgregarDebitoBancarioView view;
	private DebitoBancarioABMModel abmModel = new DebitoBancarioABMModel();
	
	public AgregarDebitoBancario(JDialog owner) {
		this.view = new AgregarDebitoBancarioView(owner);
		this.abmModel.setObjetoABM(new Debito());
		this.init();
	}
	
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
	

	void agregarDebitoBancario() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public DebitoBancarioTableModel getDebitoBancarioTableModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DebitoBancarioABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	public AgregarDebitoBancarioView getView() {
		return this.view;
	}
}

class BtnAgregarListener implements ActionListener {

	private AgregarDebitoBancario controller;
	
	public BtnAgregarListener(AgregarDebitoBancario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarDebitoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
