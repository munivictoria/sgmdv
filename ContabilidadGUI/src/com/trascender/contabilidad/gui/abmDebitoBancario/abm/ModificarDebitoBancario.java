package com.trascender.contabilidad.gui.abmDebitoBancario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioABMModel;
import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioTableModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarDebitoBancario extends ABMDebitoBancario{

	private ModificarDebitoBancarioView view;
	private DebitoBancarioABMModel abmModel = new DebitoBancarioABMModel();
	
	public ModificarDebitoBancario(JDialog owner) {
		this.view = new ModificarDebitoBancarioView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	void modificarDebitoBancario() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	
	@Override
	public DebitoBancarioABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarDebitoBancarioView getView() {
		return this.view;
	}

	@Override
	public DebitoBancarioTableModel getDebitoBancarioTableModel() {
		// TODO Auto-generated method stub
		return null;
	}
}

class BtnModificarListener implements ActionListener {

	private ModificarDebitoBancario controller;
	
	public BtnModificarListener(ModificarDebitoBancario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarDebitoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
