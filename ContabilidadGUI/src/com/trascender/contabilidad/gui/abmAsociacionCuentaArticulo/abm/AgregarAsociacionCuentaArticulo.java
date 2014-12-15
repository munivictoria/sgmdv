package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.AsociacionCuentaArticuloABMModel;
import com.trascender.contabilidad.recurso.persistent.CuentaArticulo;
import com.trascender.gui.framework.main.AppManager;

public class AgregarAsociacionCuentaArticulo extends ABMAsociacionCuentaArticulo{
	
	private AgregarAsociacionCuentaArticuloView view;
	private AsociacionCuentaArticuloABMModel abmModel = new AsociacionCuentaArticuloABMModel();
	
	
	public AgregarAsociacionCuentaArticulo(JDialog dialog){
		this.view = new AgregarAsociacionCuentaArticuloView(dialog);
		this.getAbmModel().setObjetoABM(new CuentaArticulo());
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
	
	void agregarAsociacionCuentaArticulo () throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ABMAsociacionCuentaArticuloView getView() {
		return this.view;
	}

	@Override
	public AsociacionCuentaArticuloABMModel getAbmModel() {
		return this.abmModel;
	}

}

class BtnAgregarListener implements ActionListener {

	private AgregarAsociacionCuentaArticulo controller;
	
	public BtnAgregarListener(AgregarAsociacionCuentaArticulo controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacionCuentaArticulo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
