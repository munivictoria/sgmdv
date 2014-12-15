package com.trascender.contabilidad.gui.abmDebitoBancario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioABMModel;
import com.trascender.contabilidad.gui.abmDebitoBancario.DebitoBancarioTableModel;
import com.trascender.gui.framework.main.AppManager;


public class EliminarDebitoBancario extends ABMDebitoBancario{
	
	private EliminarDebitoBancarioView view;
	private DebitoBancarioABMModel abmModel = new DebitoBancarioABMModel();
	

	public EliminarDebitoBancario(JDialog owner) {
		this.view = new EliminarDebitoBancarioView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfFecha().setEditable(false);
		this.getView().getTfImporte().setEditable(false);
		this.getView().getTfCuentaBancaria().setEditable(false);
		
		this.getView().getPnlBotonesSeleccion().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}

	void eliminarDebitoBancario() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
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
	public EliminarDebitoBancarioView getView() {
		return this.view;
	}
}

class BtnEliminarListener implements ActionListener {

	private EliminarDebitoBancario controller;
	
	public BtnEliminarListener(EliminarDebitoBancario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarDebitoBancario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

