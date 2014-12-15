package com.trascender.contabilidad.gui.abmLibroDiario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLibroDiario.LibroDiarioABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarLibroDiario extends ABMLibroDiario {
	
	private ModificarLibroDiarioView view;
	private LibroDiarioABMModel abmModel = new LibroDiarioABMModel();
	
	public ModificarLibroDiario(JFrame owner) {
		this.view = new ModificarLibroDiarioView(owner);
		this.init();
	}
	
	public ModificarLibroDiario(JDialog owner) {
		this.view = new ModificarLibroDiarioView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.getView().getTfCantidadFolios().setEditable(false);
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	@Override
	public LibroDiarioABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMLibroDiarioView getView() {
		return this.view;
	}
	
	protected void modificarLibroDiario() throws Exception {
		this.actualizarABMModel();
		this.getAbmModel().modificar();
		this.setOperacionRealizada(true);
		AppManager.getInstance().showInformationMsg(this.getView(), "Libro Diario modificado");
		this.close();
	}

}

class BtnModificarListener implements ActionListener {
	
	private ModificarLibroDiario controller;
	
	public BtnModificarListener(ModificarLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarLibroDiario();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}