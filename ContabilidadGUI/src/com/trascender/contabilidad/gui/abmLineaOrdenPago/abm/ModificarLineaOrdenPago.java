package com.trascender.contabilidad.gui.abmLineaOrdenPago.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaOrdenPago.LineaOrdenPagoABMModel;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.gui.framework.main.AppManager;

public class ModificarLineaOrdenPago extends ABMLineaOrdenPago {

	private ModificarLineaOrdenPagoView view;
	private LineaOrdenPagoABMModel abmModel = new LineaOrdenPagoABMModel();
	
	public ModificarLineaOrdenPago(JFrame owner) {
		this.view = new ModificarLineaOrdenPagoView(owner);
		this.abmModel.setObjetoABM(new LineaOrdenPago());
		this.init();
	}
	
	public ModificarLineaOrdenPago(JDialog owner) {
		this.view = new ModificarLineaOrdenPagoView(owner);
		this.abmModel.setObjetoABM(new LineaOrdenPago());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	@Override
	public LineaOrdenPagoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarLineaOrdenPagoView getView() {
		return this.view;
	}
	
	void modificarLineaOrdenPago() throws Exception {
		
	}
}

class BtnModificarListener implements ActionListener {
	private ModificarLineaOrdenPago controller;
	
	public BtnModificarListener(ModificarLineaOrdenPago controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarLineaOrdenPago();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
