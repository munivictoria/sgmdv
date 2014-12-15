package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AsociacionCuentaSolicitudSuministroABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.FirmaPermisoTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.LineaSolicitudSuministroTableModel;
import com.trascender.gui.framework.main.AppManager;


public class EliminarAsociacionCuentaSolicitudSuministro extends ABMAsociacionCuentaSolicitudSuministro {
	
	private EliminarAsociacionCuentaSolicitudSuministroView view;
	private AsociacionCuentaSolicitudSuministroABMModel abmModel = new AsociacionCuentaSolicitudSuministroABMModel();
	private FirmaPermisoTableModel firmaPermisoTableModel = new FirmaPermisoTableModel();
	private LineaSolicitudSuministroTableModel lineaSolicitudSuministroTableModel = new LineaSolicitudSuministroTableModel();
	
	public EliminarAsociacionCuentaSolicitudSuministro(JDialog owner) throws Exception {
		this.view = new EliminarAsociacionCuentaSolicitudSuministroView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void disabledAll() {
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnAgregar().setEnabled(false);
		this.getView().getPnlTablaLineaSS().getPnlVerticalBotones().getBtnModificar().setEnabled(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}

	@Override
	public AsociacionCuentaSolicitudSuministroABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarAsociacionCuentaSolicitudSuministroView getView() {
		return this.view;
	}
	
	void eliminarAsociacionCuentaSolicitudSuministro() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

	@Override
	public FirmaPermisoTableModel getFirmaPermisoTableModel() {
		return this.firmaPermisoTableModel;
	}

	@Override
	public LineaSolicitudSuministroTableModel getLineaSolicitudSuministroTableModel() {
		return this.lineaSolicitudSuministroTableModel;
	}

}

class BtnEliminarListener implements ActionListener {

	private EliminarAsociacionCuentaSolicitudSuministro controller;
	
	public BtnEliminarListener(
			EliminarAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarAsociacionCuentaSolicitudSuministro();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

