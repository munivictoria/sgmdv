package com.trascender.caja.gui.abmConceptoMovimientoCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmConceptoMovimientoCajaChica.ConceptoMovimientoCajaChicaABMModel;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.gui.framework.main.AppManager;

public class AgregarConceptoMovimientoCajaChica extends ABMConceptoMovimientoCajaChica {

	private AgregarConceptoMovimientoCajaChicaView view;
	private ConceptoMovimientoCajaChicaABMModel abmModel = new ConceptoMovimientoCajaChicaABMModel();
	
	public AgregarConceptoMovimientoCajaChica(JDialog owner) {
		this.view = new AgregarConceptoMovimientoCajaChicaView(owner);
		this.getAbmModel().setObjetoABM(new ConceptoMovimientoCajaChica());
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
	public ConceptoMovimientoCajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarConceptoMovimientoCajaChicaView getView() {
		return this.view;
	}
	
	void agregarConceptoMovimientoCajaChica() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarConceptoMovimientoCajaChica controller;
	public BtnAgregarListener(AgregarConceptoMovimientoCajaChica controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarConceptoMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}