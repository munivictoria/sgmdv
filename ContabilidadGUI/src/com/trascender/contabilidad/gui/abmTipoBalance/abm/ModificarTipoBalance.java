package com.trascender.contabilidad.gui.abmTipoBalance.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTableModel;
import com.trascender.contabilidad.gui.abmTipoBalance.TipoBalanceABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarTipoBalance extends ABMTipoBalance {

	private ModificarTipoBalanceView view;
	private TipoBalanceABMModel abmModel = new TipoBalanceABMModel();
	private CuentaTableModel tableModel = new CuentaTableModel();
	
	public ModificarTipoBalance(JDialog pDialog) throws Exception{
		this.view = new ModificarTipoBalanceView(pDialog);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.view.getPnlPie().getBtnAceptar().addActionListener(new BtnMiodificarListener(this));
	}
	
	@Override
	public ModificarTipoBalanceView getView() {
		return this.view;
	}

	@Override
	public TipoBalanceABMModel getAbmModel() {
		return abmModel;
	}

	public CuentaTableModel getTableModel() {
		return tableModel;
	}

	void modificarTipoBalance() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnMiodificarListener implements ActionListener {
	
	private ModificarTipoBalance controller;
	
	public BtnMiodificarListener(ModificarTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
