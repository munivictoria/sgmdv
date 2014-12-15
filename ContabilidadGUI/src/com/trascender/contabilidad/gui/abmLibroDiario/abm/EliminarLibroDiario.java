package com.trascender.contabilidad.gui.abmLibroDiario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLibroDiario.LibroDiarioABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarLibroDiario extends ABMLibroDiario {
	
	private EliminarLibroDiarioView view;
	private LibroDiarioABMModel abmModel = new LibroDiarioABMModel();
	
	public EliminarLibroDiario(JFrame owner) {
		this.view = new EliminarLibroDiarioView(owner);
		this.init();
	}
	
	public EliminarLibroDiario(JDialog owner) {
		this.view = new EliminarLibroDiarioView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.disableAll();
		this.setListeners();
	}
	
	private void disableAll() {
		this.getView().getTfCodigoAutorizacion().setEditable(false);
		this.getView().getTfNumero().setEditable(false);
		this.getView().getTfCantidadFolios().setEditable(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public LibroDiarioABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarLibroDiarioView getView() {
		return this.view;
	}
	
	protected void eliminarLibroDiario() throws Exception {
		this.actualizarABMModel();
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		AppManager.getInstance().showInformationMsg(this.getView(), "Libro Diario eliminado");
		this.close();
	}

}

class BtnEliminarListener implements ActionListener {
	
	private EliminarLibroDiario controller;
	
	public BtnEliminarListener(EliminarLibroDiario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarLibroDiario();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}