package com.trascender.contabilidad.gui.abmLibroBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLibroBanco.LibroBancoABMModel;
import com.trascender.contabilidad.gui.abmLibroBanco.LineasLibroBancoTableModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarLibroBanco extends ABMLibroBanco{

	private ModificarLibroBancoView view;
	private LibroBancoABMModel abmModel = new LibroBancoABMModel();
	private LineasLibroBancoTableModel abmTableModel = new LineasLibroBancoTableModel();
	
	public ModificarLibroBanco(JDialog owner) throws Exception {
		this.view = new ModificarLibroBancoView(owner);
		this.init();
	}
	
	public ModificarLibroBanco(JFrame owner) throws Exception {
		this.view = new ModificarLibroBancoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.enableBotonesTabla(true);
		this.getView().getLblFechaGeneracion().setVisible(false);
		this.getView().getTfFechaGeneracion().setVisible(false);
		this.getView().getBtnGenerarLibroBanco().setEnabled(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	
	@Override
	public LibroBancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMLibroBancoView getView() {
		return this.view;
	}

	void modificarLibrobanco() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public LineasLibroBancoTableModel getAbmTableModel() {
		return this.abmTableModel;
	}
	
}

class BtnModificarListener implements ActionListener {

	private ModificarLibroBanco controller;
	
	public BtnModificarListener(ModificarLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarLibrobanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}