package com.trascender.contabilidad.gui.abmReporteContable.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmReporteContable.ParametroReporteContableTableModel;
import com.trascender.contabilidad.gui.abmReporteContable.ReporteContableABMModel;
import com.trascender.contabilidad.gui.abmReporteContable.UsuarioTableModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarReporteContable extends ABMReporteContable{

	private EliminarReporteContableView view;
	private ReporteContableABMModel abmModel = new ReporteContableABMModel();
	private ParametroReporteContableTableModel tableModelParametros = new ParametroReporteContableTableModel();
	private UsuarioTableModel tableModelUsuarios = new UsuarioTableModel();
	
	public EliminarReporteContable(JDialog owner) throws Exception{
		this.view = new EliminarReporteContableView(owner);
		this.init();
	}
	
	public EliminarReporteContable(JFrame owner) throws Exception{
		this.view = new EliminarReporteContableView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.disableAll();
		this.setListeners();
	}
	
	private void disableAll() {
		boolean flag = false;
		this.getView().getTfNombre().setEditable(flag);
		this.getView().getTfNombreArchivoJasper().setEditable(flag);
//		this.getView().getCellEditorCheckBox().cancelCellEditing();
//		this.getView().getCellEditorComboBox().cancelCellEditing();
//		this.getView().getCellEditorTextField().cancelCellEditing();
		this.getView().getPnlBtnTablaParametros().getBtnAgregar().setEnabled(flag);
		this.getView().getPnlBtnTablaParametros().getBtnEliminar().setEnabled(flag);
		this.getView().getPnlBtnTablaUsuarios().getBtnAgregar().setEnabled(flag);
		this.getView().getPnlBtnTablaUsuarios().getBtnEliminar().setEnabled(flag);
//		
//		this.getView().getTfNumeroAsiento().setEditable(flag);
//		this.getView().getCbFolioLibroDiario().setEditable(flag);
//		this.getView().getTaObservaciones().setEditable(flag);
//		this.getView().getFtfFecha().setEditable(flag);
//		this.getView().getPnlBtnTabla().getBtnAgregar().setEnabled(flag);
		this.getView().getPnlTablaParametrosReporte().getTblDatos().setEnabled(flag);
//		
//		this.getView().getPnlBotonesSeleccionLibroDiario().setVisible(flag);
//		this.getView().getCbTipoSubdiarioCaja().setEditable(flag);
//		this.getView().getBtnGenerarAsientoContable().setVisible(flag);
//		this.getView().getBtnCargarAsientoContable().setVisible(flag);
//		this.getView().getPnlBtnTabla().setVisible(flag);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarReporteContableListener(this));
	}
	
	@Override
	public ReporteContableABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarReporteContableView getView() {
		return this.view;
	}
	
	void eliminarReporteContable() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().eliminar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ParametroReporteContableTableModel getTableModelParametro() {
		return this.tableModelParametros;
	}

	@Override
	public UsuarioTableModel getTableModelUsuario() {
		return this.tableModelUsuarios;
	}
}

class BtnEliminarReporteContableListener implements ActionListener {
	private EliminarReporteContable controller;
	public BtnEliminarReporteContableListener(EliminarReporteContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarReporteContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
