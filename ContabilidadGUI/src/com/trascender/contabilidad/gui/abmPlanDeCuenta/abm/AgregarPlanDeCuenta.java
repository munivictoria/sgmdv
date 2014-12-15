package com.trascender.contabilidad.gui.abmPlanDeCuenta.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmCuenta.CuentaTreeModel;
import com.trascender.contabilidad.gui.abmPlanDeCuenta.PlanDeCuentaABMModel;
import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.gui.framework.main.AppManager;

public class AgregarPlanDeCuenta extends ABMPlanDeCuenta {
	
	private AgregarPlanDeCuentaView view;
	private PlanDeCuentaABMModel abmModel = new PlanDeCuentaABMModel();
	private CuentaTreeModel treeModel;
	
	public AgregarPlanDeCuenta(JFrame owner) {
		PlanDeCuenta locPlanDeCuenta = new PlanDeCuenta();
		this.treeModel = new CuentaTreeModel(locPlanDeCuenta);
		this.abmModel.setObjetoABM(locPlanDeCuenta);
		this.view = new AgregarPlanDeCuentaView(owner);
		this.init();
	}
	
	public AgregarPlanDeCuenta(JDialog owner) {
		PlanDeCuenta locPlanDeCuenta = new PlanDeCuenta();
		this.treeModel = new CuentaTreeModel(locPlanDeCuenta);
		this.abmModel.setObjetoABM(locPlanDeCuenta);
		this.view = new AgregarPlanDeCuentaView(owner);
		
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().setText(MessagesContabilidad.getString("Application.btnQuitar"));
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().setMnemonic(MessagesContabilidad.getChar("Application.btnQuitarMnemonic"));
		
		this.setListener();
	}
	
	private void setListener() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
		this.getView().getPnlVerticalBotonesArbol().getBtnAgregar().addActionListener(new BtnAgregarNodoListener(this));
		this.getView().getPnlVerticalBotonesArbol().getBtnModificar().addActionListener(new BtnModificarNodoListener(this));
		this.getView().getPnlVerticalBotonesArbol().getBtnEliminar().addActionListener(new BtnQuitarNodoListener(this));
		this.getView().getPnlVerticalBotonesArbol().getBtnQuitarTodos().addActionListener(new BtnQuitarTodosListener(this));
	}
	
	public void agregarPlanDeCuenta() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();		
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
	@Override
	public PlanDeCuentaABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	public AgregarPlanDeCuentaView getView() {
		return this.view;
	}
	
	@Override
	public CuentaTreeModel getTreeModel() {
		return this.treeModel;
	}
	
}


class BtnAgregarListener implements ActionListener {
	private AgregarPlanDeCuenta controller;
	public BtnAgregarListener(AgregarPlanDeCuenta controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarPlanDeCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
