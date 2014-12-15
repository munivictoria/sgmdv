package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmPresupuesto.PresupuestoABMModel;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo;
import com.trascender.gui.framework.main.AppManager;

public class EliminarPresupuesto extends ABMPresupuesto {
	
	private EliminarPresupuestoView view;
	private PresupuestoABMModel abmModel = new PresupuestoABMModel();
	// adrian
	private LineaPresupuestoTableModel tableModel;
	
	public EliminarPresupuesto(JDialog owner) throws Exception {
		this.view = new EliminarPresupuestoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnEliminarListener(this));
	}
	
	private void disabledAll() {
		this.getView().getTfAnioPeriodo().setEnabled(false);
		this.getView().getTfDigesto().setEnabled(false);
		this.getView().getTfNombre().setEnabled(false);
		this.getView().getCbEstado().setEnabled(false);
		this.getView().getCbTipo().setEnabled(false);
		this.getView().getPnlBotonesSeleccionDigesto().setVisible(false);
		this.getView().getPnlBtnTabla().setVisible(false);
		this.getView().getPnlTabla().getTblDatos().setEnabled(false);
	}
	
	public void instanciarTableModel(Tipo tipoPresupuesto) throws Exception {
		if (tipoPresupuesto.equals(Tipo.GASTOS)) {
			this.tableModel = new LineaPresupuestoGastosABMTableModel();
		}
		else if (tipoPresupuesto.equals(Tipo.RECURSOS)) {
			this.tableModel = new LineaPresupuestoRecursoABMTableModel();
		}
		this.getView().setTableModel(this.getTableModel());
	}
	
	@Override
	public EliminarPresupuestoView getView() {
		return this.view;
	}

	@Override
	public PresupuestoABMModel getAbmModel() {
		return this.abmModel;
	}


	@Override
	public LineaPresupuestoTableModel getTableModel() {
		return this.tableModel;
	}
	

	void eliminarPresupuesto() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}
}

class BtnEliminarListener implements ActionListener {

	private EliminarPresupuesto controller;
	
	public BtnEliminarListener(EliminarPresupuesto controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarPresupuesto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
