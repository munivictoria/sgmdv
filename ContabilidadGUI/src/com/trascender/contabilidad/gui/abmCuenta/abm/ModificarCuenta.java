package com.trascender.contabilidad.gui.abmCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmCuenta.CuentaABMModel;
import com.trascender.contabilidad.gui.abmCuenta.TipoCuentaTableModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarCuenta extends ABMCuenta {
	
	private ModificarCuentaView view;
	private CuentaABMModel abmModel = new CuentaABMModel();
	private TipoCuentaTableModel tableModelTipoCuenta = new TipoCuentaTableModel();
	
	public ModificarCuenta(JFrame owner) throws Exception{
		this.view = new ModificarCuentaView(owner);
		this.init();
	}
	
	public ModificarCuenta(JDialog owner) throws Exception {
		this.view = new ModificarCuentaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	@Override
	public CuentaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public TipoCuentaTableModel getTableModelTipoCuenta() {
		return this.tableModelTipoCuenta;
	}
	
	@Override
	public ModificarCuentaView getView() {
		return this.view;
	}
	
	void modificarCuenta() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnModificarListener implements ActionListener {
	private ModificarCuenta controller;
	public BtnModificarListener(ModificarCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
