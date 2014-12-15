package com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.LineaSubdiarioCajaABMModel;
import com.trascender.contabilidad.recurso.persistent.LineaSubdiarioCaja;
import com.trascender.gui.framework.main.AppManager;

public class AgregarLineaSubdiarioCaja extends ABMLineaSubdiarioCaja {

	private AgregarLineaSubdiarioCajaView view;
	private LineaSubdiarioCajaABMModel abmModel = new LineaSubdiarioCajaABMModel();
	
	public AgregarLineaSubdiarioCaja(JFrame owner) throws Exception {
		this.view = new AgregarLineaSubdiarioCajaView(owner);
		this.abmModel.setObjetoABM(new LineaSubdiarioCaja());
		this.init();
	}
	
	public AgregarLineaSubdiarioCaja(JDialog owner) {
		this.view = new AgregarLineaSubdiarioCajaView(owner);
		this.abmModel.setObjetoABM(new LineaSubdiarioCaja());
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
	public LineaSubdiarioCajaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarLineaSubdiarioCajaView getView() {
		return this.view;
	}
	
	void agregarLineaSubdiarioCaja() throws Exception {
		String fecha = this.getView().getTfDia().getText() + "/" + this.getAbmModel().getMes() + "/" + this.getAbmModel().getAnio();
		
		if (this.validarDia(fecha)) {
			if (this.validarDatos()) {
				 this.actualizarABMModel();
				 this.setOperacionRealizada(true);
				 this.close();
			}
		}
	}
	
}

class BtnAgregarListener implements ActionListener {
	private AgregarLineaSubdiarioCaja controller;
	
	public BtnAgregarListener(AgregarLineaSubdiarioCaja controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarLineaSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
