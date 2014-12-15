package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.AsociacionCuentaLineaFacturaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarAsociacionCuentaLineaFactura extends ABMAsociacionCuentaLineaFactura {

	private EliminarAsociacionCuentaLineaFacturaView view;
	private AsociacionCuentaLineaFacturaABMModel abmModel = new AsociacionCuentaLineaFacturaABMModel();
	
	public EliminarAsociacionCuentaLineaFactura(JDialog owner) {
		this.view = new EliminarAsociacionCuentaLineaFacturaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getTfAnioPeriodo().setEditable(false);
		
		this.getView().getPnlBtnSeleccionCuenta().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	@Override
	public AsociacionCuentaLineaFacturaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMAsociacionCuentaLineaFacturaView getView() {
		return this.view;
	}

	void eliminarAsociacionCuentaSolicitudSuministro() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
}

class BtnEliminarListener implements ActionListener {

	private EliminarAsociacionCuentaLineaFactura controller;

	public BtnEliminarListener(EliminarAsociacionCuentaLineaFactura controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarAsociacionCuentaSolicitudSuministro();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

