package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.AsociacionCuentaConceptoSelladoAdministrativoABMModel;
import com.trascender.contabilidad.recurso.persistent.CuentaConceptoIngresoVario;
import com.trascender.gui.framework.main.AppManager;

public class AgregarAsociacionCuentaConceptoIngresoVario extends ABMAsociacionCuentaConceptoSelladoAdministrativo {

	private AgregarAsociacionCuentaConceptoSelladoAdministrativoView view;
	private AsociacionCuentaConceptoSelladoAdministrativoABMModel abmModel = new AsociacionCuentaConceptoSelladoAdministrativoABMModel();
	
	public AgregarAsociacionCuentaConceptoIngresoVario(JDialog owner) {
		this.view = new AgregarAsociacionCuentaConceptoSelladoAdministrativoView(owner);
		this.getAbmModel().setObjetoABM(new CuentaConceptoIngresoVario());
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
	public AsociacionCuentaConceptoSelladoAdministrativoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMAsociacionCuentaConceptoSelladoAdministrativoView getView() {
		return this.view;
	}

	void agregarAsociacionCuentaConceptoSelladoAdministrativo() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnAgregarListener implements ActionListener {

	private AgregarAsociacionCuentaConceptoIngresoVario controller;

	public BtnAgregarListener(
			AgregarAsociacionCuentaConceptoIngresoVario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacionCuentaConceptoSelladoAdministrativo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
