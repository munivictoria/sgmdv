package com.trascender.caja.gui.abmMovimientoCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmMovimientoCajaChica.MovimientoCajaChicaABMModel;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.gui.framework.main.AppManager;

public class AgregarMovimientoCajaChica extends ABMMovimientoCajaChica {
	
	private AgregarMovimientoCajaChicaView view;
	private MovimientoCajaChicaABMModel abmModel = new MovimientoCajaChicaABMModel();

	public AgregarMovimientoCajaChica(JDialog owner) {
		this.view = new AgregarMovimientoCajaChicaView(owner);
		this.abmModel.setObjetoABM(new MovimientoCajaChica());
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
	public MovimientoCajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarMovimientoCajaChicaView getView() {
		return this.view;
	}

	void agregarMovimientoCajaChica() throws Exception {
		if (validarDatos()) { 
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarMovimientoCajaChica controller;
	public BtnAgregarListener(AgregarMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}