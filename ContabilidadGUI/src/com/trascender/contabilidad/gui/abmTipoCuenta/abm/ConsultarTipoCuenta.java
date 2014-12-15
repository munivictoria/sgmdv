package com.trascender.contabilidad.gui.abmTipoCuenta.abm;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaABMModel;
import com.trascender.contabilidad.gui.abmTipoCuenta.TipoCuentaExcluidosTableModel;

public class ConsultarTipoCuenta extends ABMTipoCuenta {
	
	private ConsultarTipoCuentaView view;
	private TipoCuentaABMModel abmModel = new TipoCuentaABMModel();
	private TipoCuentaExcluidosTableModel tableModelTipoCuenta = new TipoCuentaExcluidosTableModel();
	
	public ConsultarTipoCuenta(JDialog pDialog) throws Exception {
		this.view = new ConsultarTipoCuentaView(pDialog);
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
		this.getView().getPnlPie().getBtnAceptar().setVisible(false);
		this.getView().getPnlBtnTablaTipoCuenta().getBtnAgregar().setEnabled(false);
		this.getView().getPnlBtnTablaTipoCuenta().getBtnEliminar().setEnabled(false);
	}
	
	private void setListeners() {
	}
	
	@Override
	public TipoCuentaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ConsultarTipoCuentaView getView() {
		return this.view;
	}

	@Override
	public TipoCuentaExcluidosTableModel getTableModelTipoCuenta() {
		return this.tableModelTipoCuenta;
	}
}
