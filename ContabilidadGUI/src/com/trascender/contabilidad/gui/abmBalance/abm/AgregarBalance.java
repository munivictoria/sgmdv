package com.trascender.contabilidad.gui.abmBalance.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmBalance.BalanceABMModel;
import com.trascender.contabilidad.gui.abmBalance.CuentaHistoricaBalanceTableModel;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AgregarBalance extends ABMBalance {

	private AgregarBalanceView view;
	private BalanceABMModel abmModel = new BalanceABMModel();
	private CuentaHistoricaBalanceTableModel tableModel = new CuentaHistoricaBalanceTableModel();
	
	public AgregarBalance(JFrame owner) throws Exception {
		this.view = new AgregarBalanceView(owner);
		this.abmModel.setObjetoABM(new Balance());
		this.init();
	}
	
	public AgregarBalance(JDialog owner) throws Exception {
		this.view = new AgregarBalanceView(owner);
		this.abmModel.setObjetoABM(new Balance());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getFtfFecha().setText(Conversor.getString(Calendar.getInstance().getTime()));
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().setTableModel(this.getTableModel());
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarBalanceListener(this));
	}
	
	@Override
	public BalanceABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public CuentaHistoricaBalanceTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	public AgregarBalanceView getView() {
		return this.view;
	}
	
	void agregarBalance() throws Exception {
		if (this.validarDatos() && !this.getTableModel().getListaObjetos().isEmpty()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarBalanceListener implements ActionListener {
	private AgregarBalance controller;
	public BtnAgregarBalanceListener(AgregarBalance controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarBalance();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

