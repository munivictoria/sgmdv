package com.trascender.contabilidad.gui.abmTipoCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaABMModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaExcluidosTableModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarTipoCuenta extends ABMTipoCuenta {
	
	private EliminarTipoCuentaView view;
	private TipoCuentaABMModel abmModel = new TipoCuentaABMModel();
	private TipoCuentaExcluidosTableModel tableModelTipoCuenta = new TipoCuentaExcluidosTableModel();
	
	public EliminarTipoCuenta(JDialog pDialog) throws Exception {
		this.view = new EliminarTipoCuentaView(pDialog);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.disableAll();
		this.setListeners();
	}
	
	
	private void disableAll() {
		this.getView().getTfNombre().setEnabled(false);
		this.getView().getCbAbreviatura().setEnabled(false);
		this.getView().getCbOperaAsientos().setEnabled(false);
		this.getView().getCbOperaMovimientosCaja().setEnabled(false);
		this.getView().getPnlBtnTablaTipoCuenta().getBtnAgregar().setEnabled(false);
		this.getView().getPnlBtnTablaTipoCuenta().getBtnEliminar().setEnabled(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	void eliminarTipoCuenta() throws Exception {
		this.actualizarABMModel();
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
	@Override
	public TipoCuentaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarTipoCuentaView getView() {
		return this.view;
	}

	@Override
	public TipoCuentaExcluidosTableModel getTableModelTipoCuenta() {
		return this.tableModelTipoCuenta;
	}

}

class BtnEliminarListener implements ActionListener {
	
	private EliminarTipoCuenta controller;
	
	public BtnEliminarListener(EliminarTipoCuenta controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarTipoCuenta();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
		
	}
	
}