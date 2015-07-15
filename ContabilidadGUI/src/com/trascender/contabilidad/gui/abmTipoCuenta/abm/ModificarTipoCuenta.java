package com.trascender.contabilidad.gui.abmTipoCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaABMModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaExcluidosTableModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarTipoCuenta extends ABMTipoCuenta {

	private ModificarTipoCuentaView view;
	private TipoCuentaABMModel abmModel = new TipoCuentaABMModel();
	private TipoCuentaExcluidosTableModel tableModelTipoCuenta = new TipoCuentaExcluidosTableModel();
	
	public ModificarTipoCuenta(JDialog pDialog) throws Exception{
		this.view = new ModificarTipoCuentaView(pDialog);
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
	public TipoCuentaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarTipoCuentaView getView() {
		return this.view;
	}
	
	void modificarTipoCuenta() throws Exception{
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public TipoCuentaExcluidosTableModel getTableModelTipoCuenta() {
		return this.tableModelTipoCuenta;
	}
	
}

class BtnModificarListener implements ActionListener {
	
	private ModificarTipoCuenta controller;
	
	public BtnModificarListener(ModificarTipoCuenta controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarTipoCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
