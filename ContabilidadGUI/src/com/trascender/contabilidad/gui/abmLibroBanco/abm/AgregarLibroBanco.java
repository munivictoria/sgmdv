package com.trascender.contabilidad.gui.abmLibroBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLibroBanco.LibroBancoABMModel;
import com.trascender.contabilidad.gui.abmLibroBanco.LineasLibroBancoTableModel;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.gui.framework.main.AppManager;

public class AgregarLibroBanco extends ABMLibroBanco{

	private AgregarLibroBancoView view;
	private LibroBancoABMModel abmModel = new LibroBancoABMModel();
	private LineasLibroBancoTableModel abmTableModel = new LineasLibroBancoTableModel();
	
	public AgregarLibroBanco(JDialog owner) throws Exception {
		this.view = new AgregarLibroBancoView(owner);
		this.abmModel.setObjetoABM(new LibroBanco());
		this.init();
	}
	 
	public AgregarLibroBanco(JFrame owner) throws Exception {
		this.view = new AgregarLibroBancoView(owner);
		this.abmModel.setObjetoABM(new LibroBanco());
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
	public LibroBancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMLibroBancoView getView() {
		return this.view;
	}

	void agregarLibroBanco() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public LineasLibroBancoTableModel getAbmTableModel() {
		return this.abmTableModel;
	}
	
}

class BtnAgregarListener implements ActionListener {

	private AgregarLibroBanco controller;
	
	public BtnAgregarListener(AgregarLibroBanco controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}