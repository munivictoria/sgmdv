package com.trascender.contabilidad.gui.abmLineaOrdenPago.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaOrdenPago.LineaOrdenPagoABMModel;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.gui.framework.main.AppManager;

public class AgregarLineaOrdenPago extends ABMLineaOrdenPago {

	private AgregarLineaOrdenPagoView view;
	private LineaOrdenPagoABMModel abmModel = new LineaOrdenPagoABMModel();
	
	public AgregarLineaOrdenPago(JFrame owner) {
		this.view = new AgregarLineaOrdenPagoView(owner);
		this.abmModel.setObjetoABM(new LineaOrdenPago());
		this.init();
	}
	
	public AgregarLineaOrdenPago(JDialog owner) {
		this.view = new AgregarLineaOrdenPagoView(owner);
		this.abmModel.setObjetoABM(new LineaOrdenPago());
		this.init();
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
	public LineaOrdenPagoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarLineaOrdenPagoView getView() {
		return this.view;
	}

	void agregarLineaOrdenPago() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarLineaOrdenPago controller;
	
	public BtnAgregarListener(AgregarLineaOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarLineaOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
