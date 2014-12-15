package com.trascender.contabilidad.gui.abmTipoBalance.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTableModel;
import com.trascender.contabilidad.gui.abmTipoBalance.TipoBalanceABMModel;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.gui.framework.main.AppManager;

public class AgregarTipoBalance extends ABMTipoBalance {

	private AgregarTipoBalanceView view;
	private TipoBalanceABMModel abmModel = new TipoBalanceABMModel();
	private CuentaTableModel tableModel = new CuentaTableModel();

	
	public AgregarTipoBalance(JDialog pDialog) throws Exception {
		this.view = new AgregarTipoBalanceView(pDialog);
		this.getAbmModel().setObjetoABM(new TipoBalance());
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setListener();
		this.setModels();
	}

	private void setModels(){
		
	}
	
	private void setListener() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	@Override
	public AgregarTipoBalanceView getView() {
		return this.view;
	}

	@Override
	public TipoBalanceABMModel getAbmModel() {
		return abmModel;
	}

	public CuentaTableModel getTableModel() {
		return tableModel;
	}

	void agregarTipoBalance() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnAgregarListener implements ActionListener {

	private AgregarTipoBalance controller;
	
	public BtnAgregarListener(AgregarTipoBalance controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarTipoBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}