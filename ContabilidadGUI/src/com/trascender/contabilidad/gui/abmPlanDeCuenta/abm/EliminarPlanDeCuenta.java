package com.trascender.contabilidad.gui.abmPlanDeCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTreeModel;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaABMModel;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.main.AppManager;

public class EliminarPlanDeCuenta extends ABMPlanDeCuenta {
	
	private EliminarPlanDeCuentaView view;
	private PlanDeCuentaABMModel abmModel = new PlanDeCuentaABMModel();
	private CuentaTreeModel treeModel;
	
	public EliminarPlanDeCuenta(JFrame owner, PlanDeCuenta pPlanDeCuenta) {
		this.view = new EliminarPlanDeCuentaView(owner);
		this.getAbmModel().setObjetoABM(pPlanDeCuenta);
		this.treeModel = new CuentaTreeModel(pPlanDeCuenta);
		this.init();
	}
	  
	public EliminarPlanDeCuenta(JDialog owner, PlanDeCuenta pPlanDeCuenta) {
		this.view = new EliminarPlanDeCuentaView(owner);
		this.getAbmModel().setObjetoABM(pPlanDeCuenta);
		this.treeModel = new CuentaTreeModel(pPlanDeCuenta);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getPnlVerticalBotonesArbol().setVisible(false);
		this.getView().getTfDescripcion().setEnabled(false);
		this.getView().getTfFechaAlta().setEnabled(false);
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().setVisible(false);
		this.getView().getPnlVerticalBotonesArbol().getBtnQuitarTodos().setVisible(false);
		this.setListener();
	}	
	
	private void setListener(){
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}

	@Override
	protected PlanDeCuentaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	protected CuentaTreeModel getTreeModel() {
		return this.treeModel;
	}

	@Override
	protected EliminarPlanDeCuentaView getView() {
		return this.view;
	}
	
	void eliminarPlanDeCuenta() throws Exception {
		this.getAbmModel().getObjetoABM().getListaCuentas().clear();
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
	
}

class BtnEliminarListener implements ActionListener {
	private EliminarPlanDeCuenta controller;
	public BtnEliminarListener(EliminarPlanDeCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}