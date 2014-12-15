package com.trascender.contabilidad.gui.abmReporteContable.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmAsientoContable.AsientoContableABMModel;
import com.trascender.contabilidad.gui.abmAsientoContable.abm.ModificarAsientoContable;
import com.trascender.contabilidad.gui.abmAsientoContable.abm.ModificarAsientoContableView;
import com.trascender.contabilidad.gui.abmLineaAsientoContable.LineaAsientoContableTableModel;
import com.trascender.contabilidad.gui.abmReporteContable.ParametroReporteContableTableModel;
import com.trascender.contabilidad.gui.abmReporteContable.ReporteContableABMModel;
import com.trascender.contabilidad.gui.abmReporteContable.UsuarioTableModel;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.gui.framework.main.AppManager;

public class ModificarReporteContable extends ABMReporteContable{

	private ModificarReporteContableView view;
	private ReporteContableABMModel abmModel = new ReporteContableABMModel();
	private ParametroReporteContableTableModel tableModelParametros = new ParametroReporteContableTableModel();
	private UsuarioTableModel tableModelUsuarios = new UsuarioTableModel();
	
	public ModificarReporteContable(JDialog owner) throws Exception{
		this.view = new ModificarReporteContableView(owner);
		this.init();
	}
	
	public ModificarReporteContable(JFrame owner) throws Exception{
		this.view = new ModificarReporteContableView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarReporteContableListener(this));
	}
	
	@Override
	public ReporteContableABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ParametroReporteContableTableModel getTableModelParametro() {
		return this.tableModelParametros;
	}

	@Override
	public ModificarReporteContableView getView() {
		return this.view;
	}
	
	void modificarReporteContable() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public UsuarioTableModel getTableModelUsuario() {
		return this.tableModelUsuarios;
	}
}
class BtnModificarReporteContableListener implements ActionListener {
	private ModificarReporteContable controller;
	public BtnModificarReporteContableListener(ModificarReporteContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarReporteContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
