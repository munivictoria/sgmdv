package com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.abmEliminar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaArticulo.AsociacionCuentaArticuloABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarAsociacionCuentaArticulo extends ABMAsociacionCuentaArticuloEliminar{
	
	private EliminarAsociacionCuentaArticuloView view;
	private BajaArticuloABMModel abmModelBajaArticulo = new BajaArticuloABMModel();
	private AsociacionCuentaArticuloABMModel abmModel = new AsociacionCuentaArticuloABMModel();
	
	public EliminarAsociacionCuentaArticulo(JDialog dialog){
		this.view = new EliminarAsociacionCuentaArticuloView(dialog);
		this.init();
	}
	
	@Override
	protected void init(){
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	public void agregarBajaArticulo() throws Exception{
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModelBajaArticulo().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
	@Override
	public ABMAsociacionCuentaArticuloEliminarView getView() {
		return this.view;
	}

	@Override
	public AsociacionCuentaArticuloABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public BajaArticuloABMModel getAbmModelBajaArticulo() {
		return this.abmModelBajaArticulo;
	}

}

class BtnAgregarListener implements ActionListener {

	private EliminarAsociacionCuentaArticulo controller;
	
	public BtnAgregarListener(EliminarAsociacionCuentaArticulo controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarBajaArticulo();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
