package com.trascender.contabilidad.gui.abmAsientoContable.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmAsientoContable.AsientoContableABMModel;
import com.trascender.contabilidad.gui.abmLineaAsientoContable.LineaAsientoContableTableModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarAsientoContable extends ABMAsientoContable {

	private ModificarAsientoContableView view;
	private AsientoContableABMModel abmModel = new AsientoContableABMModel();
	private LineaAsientoContableTableModel tableModel = new LineaAsientoContableTableModel();
	
	public ModificarAsientoContable(JFrame owner) throws Exception {
		this.view = new ModificarAsientoContableView(owner);
		this.init();
	}
	
	public ModificarAsientoContable(JDialog owner) throws Exception {
		this.view = new ModificarAsientoContableView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.getView().getCbFolioLibroDiario().setEnabled(true);
		this.getView().getFtfFecha().setEditable(false);
		this.getView().getCbTipoSubdiarioCaja().setEditable(false);
		this.getView().getBtnGenerarAsientoContable().setEnabled(false);
		this.getView().getPnlBtnTabla().getBtnAgregar().setEnabled(false);
		this.getView().getCbTipoSubdiarioCaja().setEnabled(false);
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarAsientoContableListener(this));
	}
	
	@Override
	public AsientoContableABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public LineaAsientoContableTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	public ModificarAsientoContableView getView() {
		return this.view;
	}
	
	void modificarAsientoContable() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}


class BtnModificarAsientoContableListener implements ActionListener {
	private ModificarAsientoContable controller;
	public BtnModificarAsientoContableListener(ModificarAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
