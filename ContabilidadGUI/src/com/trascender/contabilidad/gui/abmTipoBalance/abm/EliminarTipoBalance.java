package com.trascender.contabilidad.gui.abmTipoBalance.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTableModel;
import com.trascender.contabilidad.gui.abmTipoBalance.TipoBalanceABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarTipoBalance extends ABMTipoBalance {

	private EliminarTipoBalanceView view;
	private TipoBalanceABMModel abmModel= new TipoBalanceABMModel();
	private CuentaTableModel tableModel = new CuentaTableModel();
	
	public EliminarTipoBalance(JDialog pDialog) throws Exception {
		this.view = new EliminarTipoBalanceView(pDialog);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setDisabledAll();
		this.setListeners();
	}
	
	private void setDisabledAll() {
		this.view.getTfNombre().setEnabled(false);
		this.view.getTfFechaCreacion().setEnabled(false);
		this.view.getPnlSpTabla().getTblDatos().setEnabled(false);
		this.view.getPnlBtnTabla().setVisible(false);
	}

	private void setListeners() {
		this.view.getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	void eliminarTipoBalance() throws Exception{
		this.actualizarABMModel();
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
	@Override
	public EliminarTipoBalanceView getView() {
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

}

class BtnEliminarListener implements ActionListener {
	
	private EliminarTipoBalance controller;

	public BtnEliminarListener(EliminarTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}