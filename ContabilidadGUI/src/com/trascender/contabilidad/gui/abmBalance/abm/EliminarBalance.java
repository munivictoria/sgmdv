package com.trascender.contabilidad.gui.abmBalance.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmBalance.BalanceABMModel;
import com.trascender.contabilidad.gui.abmBalance.CuentaHistoricaBalanceTableModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarBalance extends ABMBalance {

	private EliminarBalanceView view;
	private BalanceABMModel abmModel = new BalanceABMModel();
	private CuentaHistoricaBalanceTableModel tableModel;
	
	public EliminarBalance(JDialog owner) throws Exception {
		this.view = new EliminarBalanceView(owner);
		this.tableModel = new CuentaHistoricaBalanceTableModel();
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.setDisabledAll();
	}
	
	private void setDisabledAll() {
		this.getView().getPnlBotonesSeleccionTipoBalance().setVisible(false);
		this.getView().getBtnGenerarBalance().setVisible(false);
		this.getView().getTfNombre().setEditable(false);
		this.getView().getTfTipoBalance().setEditable(false);
		this.getView().getFtfFecha().setEditable(false);
		this.getView().getPnlTabla().getTblDatos().setEnabled(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public BalanceABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMBalanceView getView() {
		return this.view;
	}

	@Override
	public CuentaHistoricaBalanceTableModel getTableModel() {
		return this.tableModel;
	}
	
	void eliminarBalance() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

}

class BtnEliminarListener implements ActionListener {
	private EliminarBalance controller;
	
	public BtnEliminarListener(EliminarBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
