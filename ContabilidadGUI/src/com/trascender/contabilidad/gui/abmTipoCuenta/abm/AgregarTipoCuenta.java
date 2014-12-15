package com.trascender.contabilidad.gui.abmTipoCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaABMModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaExcluidosTableModel;
import com.trascender.gui.framework.main.AppManager;

public class AgregarTipoCuenta extends ABMTipoCuenta {
	
	private AgregarTipoCuentaView view;
	private TipoCuentaABMModel abmModel = new TipoCuentaABMModel();
	private TipoCuentaExcluidosTableModel tableModelTipoCuenta = new TipoCuentaExcluidosTableModel();
	
	public AgregarTipoCuenta(JDialog pDialog) throws Exception{
		this.view = new AgregarTipoCuentaView(pDialog);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.view.getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	@Override
	public AgregarTipoCuentaView getView() {
		return this.view;
	}
	
	@Override
	public TipoCuentaABMModel getAbmModel() {
		return this.abmModel;
	}
	
	void agregarTipoCuenta() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public TipoCuentaExcluidosTableModel getTableModelTipoCuenta() {
		return this.tableModelTipoCuenta;
	}
	
}

class BtnAgregarListener implements ActionListener {
	private AgregarTipoCuenta controller;
	
	public BtnAgregarListener(AgregarTipoCuenta controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarTipoCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
