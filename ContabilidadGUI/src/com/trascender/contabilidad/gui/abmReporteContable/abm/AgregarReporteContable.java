package com.trascender.contabilidad.gui.abmReporteContable.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmReporteContable.ParametroReporteContableTableModel;
import com.trascender.contabilidad.gui.abmReporteContable.ReporteContableABMModel;
import com.trascender.contabilidad.gui.abmReporteContable.UsuarioTableModel;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.gui.framework.main.AppManager;

public class AgregarReporteContable extends ABMReporteContable{
	
	private AgregarReporteContableView view;
	private ReporteContableABMModel abmModel = new ReporteContableABMModel();
	private ParametroReporteContableTableModel tableModelParametro = new ParametroReporteContableTableModel();
	private UsuarioTableModel tableModelUsuario = new UsuarioTableModel();
	
	public AgregarReporteContable(JDialog owner) throws Exception{
		this.view = new AgregarReporteContableView(owner);
		this.abmModel.setObjetoABM(new ReporteContable());
		this.init();
	}
	
	public AgregarReporteContable(JFrame owner) throws Exception{
		this.view = new AgregarReporteContableView(owner);
		this.abmModel.setObjetoABM(new ReporteContable());
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	private void setModels() {
		
	}
	
	@Override
	public ReporteContableABMModel getAbmModel() {
		return abmModel;
	}

	@Override
	public ABMReporteContableView getView() {
		return view;
	}
	
	void agregarReporteContable() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();		
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public ParametroReporteContableTableModel getTableModelParametro() {
		return this.tableModelParametro;
	}

	@Override
	public UsuarioTableModel getTableModelUsuario() {
		return this.tableModelUsuario;
	}

}
class BtnAgregarListener implements ActionListener {
	private AgregarReporteContable controller;
	public BtnAgregarListener(AgregarReporteContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarReporteContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
