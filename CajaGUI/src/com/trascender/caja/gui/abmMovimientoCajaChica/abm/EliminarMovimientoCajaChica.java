package com.trascender.caja.gui.abmMovimientoCajaChica.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.caja.gui.abmMovimientoCajaChica.MovimientoCajaChicaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarMovimientoCajaChica extends ABMMovimientoCajaChica {

	private EliminarMovimientoCajaChicaView view;
	private MovimientoCajaChicaABMModel abmModel = new MovimientoCajaChicaABMModel();
	
	public EliminarMovimientoCajaChica(JDialog owner) {
		this.view = new EliminarMovimientoCajaChicaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.setVisible();
		this.setDisabled();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	private void setDisabled() {
		this.getView().getTfImporte().setEditable(false);
	}
	
	private void setVisible() {
		this.getView().getPnlBotonesSeleccionCajaChica().setVisible(false);
		this.getView().getPnlBotonesSeleccionConcepto().setVisible(false);
	}
	
	@Override
	public MovimientoCajaChicaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarMovimientoCajaChicaView getView() {
		return this.view;
	}
	
	void eliminarMovimientoCajaChica() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
}

class BtnEliminarListener implements ActionListener {
	private EliminarMovimientoCajaChica controller;
	public BtnEliminarListener(EliminarMovimientoCajaChica controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarMovimientoCajaChica();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
