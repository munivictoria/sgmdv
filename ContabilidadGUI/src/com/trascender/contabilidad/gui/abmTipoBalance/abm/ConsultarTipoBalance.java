package com.trascender.contabilidad.gui.abmTipoBalance.abm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTableModel;
import com.trascender.contabilidad.gui.abmTipoBalance.TipoBalanceABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ConsultarTipoBalance extends ABMTipoBalance {

	private ConsultarTipoBalanceView view;
	private TipoBalanceABMModel abmModel = new TipoBalanceABMModel();
	private CuentaTableModel tableModel = new CuentaTableModel();
	
	public ConsultarTipoBalance(JDialog pDialog) throws Exception {
		this.view = new ConsultarTipoBalanceView(pDialog);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setDisabledAll();
		this.setListeners();
	}
	
	private void setDisabledAll() {
		this.view.getTfNombre().setEditable(false);
		this.view.getTfFechaCreacion().setEditable(false);
		this.view.getPnlSpTabla().getTblDatos().setEnabled(false);
		this.view.getPnlBtnTabla().setVisible(false);
		this.view.getPnlPie().getBtnAceptar().setVisible(false);
	}

	private void setListeners() {
		this.view.getPnlPie().getBtnAceptar().addActionListener(new BtnCerrarListener(this));
	}
	
	void CerrarTipoBalance() throws Exception{
		this.close();
	}
	
	@Override
	public ConsultarTipoBalanceView getView() {
		return this.view;
	}

	@Override
	public CuentaTableModel getTableModel() {
		return tableModel;
	}

	@Override
	public TipoBalanceABMModel getAbmModel() {
		return this.abmModel;
	}
	
	public void setAbmModel(TipoBalanceABMModel abmModel) {
		this.abmModel = abmModel;
	}

}

class BtnCerrarListener implements ActionListener {
	
	private ConsultarTipoBalance controller;

	public BtnCerrarListener(ConsultarTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.CerrarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}