package com.trascender.contabilidad.gui.abmImportarPlanDeCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmImportarPlanDeCuenta.ImportarPlanDeCuentaABMModel;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.abmConsultar.PlanDeCuentaTableModelConsultar;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.main.AppManager;

public class ImportarPlanDeCuenta extends ABMImportarPlanDeCuenta {
	ImportarPlanDeCuentaView view;
	PlanDeCuentaTableModelConsultar tableModel = new PlanDeCuentaTableModelConsultar();
	ImportarPlanDeCuentaABMModel abmModel = new ImportarPlanDeCuentaABMModel();

	public ImportarPlanDeCuenta(JDialog owner, PlanDeCuenta pPlanDeCuentaImportado) throws Exception {
		this.view = new ImportarPlanDeCuentaView(owner);
		this.abmModel.setObjetoABM(pPlanDeCuentaImportado);
		this.init();
	}
	
	public ImportarPlanDeCuenta(JFrame owner, PlanDeCuenta pPlanDeCuentaImportado) throws Exception {
		this.view = new ImportarPlanDeCuentaView(owner);
		this.abmModel.setObjetoABM(pPlanDeCuentaImportado);
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
	
	@Override
	public ImportarPlanDeCuentaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public PlanDeCuentaTableModelConsultar getTableModel() {
		return this.tableModel;
	}

	@Override
	public ImportarPlanDeCuentaView getView() {
		return this.view;
	}

	protected void importarPlanDeCuenta() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
	public void instanciarTableModel() {
		this.getView().setTableModel(this.tableModel);
	}

}

class BtnAgregarListener implements ActionListener {
	ImportarPlanDeCuenta controller;
	
	public BtnAgregarListener(ImportarPlanDeCuenta controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.importarPlanDeCuenta();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
