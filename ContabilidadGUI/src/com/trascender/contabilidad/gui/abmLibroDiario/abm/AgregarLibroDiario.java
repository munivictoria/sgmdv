package com.trascender.contabilidad.gui.abmLibroDiario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLibroDiario.LibroDiarioABMModel;
import com.trascender.gui.framework.main.AppManager;

public class AgregarLibroDiario extends ABMLibroDiario {
	
	private AgregarLibroDiarioView view;
	private LibroDiarioABMModel abmModel = new LibroDiarioABMModel();
	
	public AgregarLibroDiario(JFrame owner) {
		this.view = new AgregarLibroDiarioView(owner);
		this.init();
	}
	
	public AgregarLibroDiario(JDialog owner) {
		this.view = new AgregarLibroDiarioView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	@Override
	public LibroDiarioABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarLibroDiarioView getView() {
		return this.view;
	}
	
	protected void agregarLibroDiario() throws Exception {
		this.actualizarABMModel();
		this.getAbmModel().agregar();
		this.setOperacionRealizada(true);
		AppManager.getInstance().showInformationMsg(this.getView(), "Libro Diario agregado");
		this.close();
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarLibroDiario controller;
	
	public BtnAgregarListener(AgregarLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarLibroDiario();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
