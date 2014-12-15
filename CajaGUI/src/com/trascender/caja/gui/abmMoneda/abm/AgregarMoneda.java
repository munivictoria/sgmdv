package com.trascender.caja.gui.abmMoneda.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmMoneda.MonedaABMModel;
import com.trascender.contabilidad.recurso.persistent.Moneda;
import com.trascender.gui.framework.main.AppManager;

public class AgregarMoneda extends ABMMoneda {
	
	private AgregarMonedaView view;
	private MonedaABMModel abmModel = new MonedaABMModel();
	
	public AgregarMoneda(JDialog owner) {
		this.view = new AgregarMonedaView(owner);
		this.getAbmModel().setObjetoABM(new Moneda());
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
	public MonedaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarMonedaView getView() {
		return this.view;
	}
	void agregarMoneda() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarMoneda controller;
	public BtnAgregarListener(AgregarMoneda controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarMoneda();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}