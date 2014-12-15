package com.trascender.contabilidad.gui.abmLibroBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLibroBanco.LibroBancoABMModel;
import com.trascender.contabilidad.gui.abmLibroBanco.LineasLibroBancoTableModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarLibroBanco extends ABMLibroBanco{

	private EliminarLibroBancoView view;
	private LibroBancoABMModel abmModel = new LibroBancoABMModel();
	private LineasLibroBancoTableModel abmTableModel = new LineasLibroBancoTableModel();
	
	public EliminarLibroBanco(JDialog owner) throws Exception {
		this.view = new EliminarLibroBancoView(owner);
		this.init();
	}
	
	public EliminarLibroBanco(JFrame owner) throws Exception {
		this.view = new EliminarLibroBancoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
		this.enableBotonesTabla(false);
		this.getView().getLblFechaGeneracion().setVisible(false);
		this.getView().getTfFechaGeneracion().setVisible(false);
	}
	
	private void disabledAll() {
		this.getView().getTfNombre().setEditable(false);
		this.getView().getTfCuentaBancaria().setEditable(false);
		this.getView().getBtnGenerarLibroBanco().setEnabled(false);
		this.getView().getPnlBotonesSeleccion().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public LibroBancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMLibroBancoView getView() {
		return this.view;
	}

	void eliminarLibrobanco() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	@Override
	public LineasLibroBancoTableModel getAbmTableModel() {
		return this.abmTableModel;
	}
	
}

class BtnEliminarListener implements ActionListener {

	private EliminarLibroBanco controller;
	
	public BtnEliminarListener(EliminarLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarLibrobanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}