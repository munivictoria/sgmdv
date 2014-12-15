package com.trascender.contabilidad.gui.abmCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmCuenta.CuentaABMModel;
import com.trascender.contabilidad.gui.abmCuenta.TipoCuentaTableModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaExcluidosTableModel;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.gui.framework.main.AppManager;

public class AgregarCuenta extends ABMCuenta {
	
	private AgregarCuentaView view;
	private CuentaABMModel abmModel = new CuentaABMModel();
	private TipoCuentaTableModel tableModelTipoCuenta = new TipoCuentaTableModel();
	
	public AgregarCuenta(JFrame owner) throws Exception{
		this.view = new AgregarCuentaView(owner);
		Cuenta nuevaCuenta = new Cuenta();
		this.getAbmModel().setObjetoABM(nuevaCuenta);
		this.init();
	}
	
	public AgregarCuenta(JDialog owner) throws Exception {
		this.view = new AgregarCuentaView(owner);
		Cuenta nuevaCuenta = new Cuenta();
		this.getAbmModel().setObjetoABM(nuevaCuenta);
		this.init();
//		this.getAbmModel().setObjetoABM(new Cuenta());
//		//this.abmModel.agregar();
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
	public CuentaABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	public AgregarCuentaView getView() {
		return this.view;
	}
	
	@Override
	public TipoCuentaTableModel getTableModelTipoCuenta() {
		return this.tableModelTipoCuenta;
	}
	
	void agregarCuenta() throws Exception {
		
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnAgregarListener implements ActionListener {
	private AgregarCuenta controller;
	public BtnAgregarListener(AgregarCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
