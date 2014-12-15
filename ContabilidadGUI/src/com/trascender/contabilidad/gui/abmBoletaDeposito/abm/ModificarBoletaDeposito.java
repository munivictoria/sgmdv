package com.trascender.contabilidad.gui.abmBoletaDeposito.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmBoletaDeposito.BoletaDepositoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarBoletaDeposito extends ABMBoletaDeposito {

	private ModificarBoletaDepositoView view;
	private BoletaDepositoABMModel abmModel = new BoletaDepositoABMModel();
	
	public ModificarBoletaDeposito(JDialog owner) {
		this.view = new ModificarBoletaDepositoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		//this.getView().getPnlBtnSeleccionCuentaAfectada().setVisible(false);
	} 
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	@Override
	public BoletaDepositoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMBoletaDepositoView getView() {
		return this.view;
	}

	void modificarBoletaDeposito() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private ModificarBoletaDeposito controller;
	
	public BtnModificarListener(ModificarBoletaDeposito controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarBoletaDeposito();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

