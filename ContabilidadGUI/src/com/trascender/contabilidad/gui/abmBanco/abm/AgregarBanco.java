package com.trascender.contabilidad.gui.abmBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmBanco.BancoABMModel;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.gui.framework.main.AppManager;

public class AgregarBanco extends ABMBanco {
	
	private AgregarBancoView view;
	private BancoABMModel abmModel = new BancoABMModel();
	
	public AgregarBanco(JDialog owner) {
		this.view = new AgregarBancoView(owner);
		this.abmModel.setObjetoABM(new Banco());
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	private void setModels() {
		
	}
	
	@Override
	public BancoABMModel getAbmModel() {
		return abmModel;
	}

	@Override
	public ABMBancoView getView() {
		return view;
	}
	
	void agregarBanco() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();		
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}
class BtnAgregarListener implements ActionListener {
	private AgregarBanco controller;
	public BtnAgregarListener(AgregarBanco controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

