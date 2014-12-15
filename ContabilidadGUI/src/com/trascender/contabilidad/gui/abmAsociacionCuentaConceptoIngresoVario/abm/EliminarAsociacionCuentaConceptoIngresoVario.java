package com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaConceptoIngresoVario.AsociacionCuentaConceptoSelladoAdministrativoABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarAsociacionCuentaConceptoIngresoVario extends ABMAsociacionCuentaConceptoSelladoAdministrativo {

	private EliminarAsociacionCuentaConceptoSelladoAdministrativoView view;
	private AsociacionCuentaConceptoSelladoAdministrativoABMModel abmModel = new AsociacionCuentaConceptoSelladoAdministrativoABMModel();
	
	public EliminarAsociacionCuentaConceptoIngresoVario(JDialog owner) {
		this.view = new EliminarAsociacionCuentaConceptoSelladoAdministrativoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	public void disabledAll() {
		
		this.getView().getTfAnioPeriodo().setEditable(false);
		
		this.getView().getTfConceptoSelladoAdministrativo().setEditable(false);
		this.getView().getPnlBotonesSeleccionConceptoSelladoAdministrativo().setVisible(false);
		
		this.getView().getTfCuenta().setEditable(false);
		this.getView().getPnlBotonesSeleccionCuenta().setVisible(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	
	@Override
	public AsociacionCuentaConceptoSelladoAdministrativoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMAsociacionCuentaConceptoSelladoAdministrativoView getView() {
		return this.view;
	}
	
	void eliminarAsociacionCuentaConceptoSelladoAdministrativo() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

}

class BtnEliminarListener implements ActionListener {

	private EliminarAsociacionCuentaConceptoIngresoVario controller;
	
	public BtnEliminarListener(
			EliminarAsociacionCuentaConceptoIngresoVario controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarAsociacionCuentaConceptoSelladoAdministrativo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

