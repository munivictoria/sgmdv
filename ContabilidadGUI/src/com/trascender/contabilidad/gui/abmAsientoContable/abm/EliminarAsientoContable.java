package com.trascender.contabilidad.gui.abmAsientoContable.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmAsientoContable.AsientoContableABMModel;
import com.trascender.contabilidad.gui.abmLineaAsientoContable.LineaAsientoContableTableModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarAsientoContable extends ABMAsientoContable {

	private EliminarAsientoContableView view;
	private AsientoContableABMModel abmModel = new AsientoContableABMModel();
	private LineaAsientoContableTableModel tableModel = new LineaAsientoContableTableModel();

	public EliminarAsientoContable(JFrame owner) throws Exception {
		this.view = new EliminarAsientoContableView(owner);
		this.init();
	}
	
	public EliminarAsientoContable(JDialog owner) throws Exception {
		this.view = new EliminarAsientoContableView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.disableAll();
		this.setListeners();
		this.getView().getCbTipoSubdiarioCaja().setEnabled(false);
	}
	
	private void disableAll() {
		boolean flag = false;
		this.getView().getTfNumeroAsiento().setEditable(flag);
		this.getView().getCbFolioLibroDiario().setEditable(flag);
		this.getView().getTaObservaciones().setEditable(flag);
		this.getView().getFtfFecha().setEditable(flag);
		this.getView().getPnlBtnTabla().getBtnAgregar().setEnabled(flag);
		this.getView().getPnlTabla().getTblDatos().setEnabled(flag);
		
		this.getView().getPnlBotonesSeleccionLibroDiario().setVisible(flag);
		this.getView().getCbTipoSubdiarioCaja().setEditable(flag);
		this.getView().getBtnGenerarAsientoContable().setVisible(flag);
		this.getView().getBtnCargarAsientoContable().setVisible(flag);
		this.getView().getPnlBtnTabla().setVisible(flag);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarAsientoContableListener(this));
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
	public EliminarAsientoContableView getView() {
		return this.view;
	}
	
	void eliminarAsientoContable() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().eliminar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnEliminarAsientoContableListener implements ActionListener {
	private EliminarAsientoContable controller;
	public BtnEliminarAsientoContableListener(EliminarAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
