package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmPresupuesto.PresupuestoABMModel;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo;
import com.trascender.gui.framework.main.AppManager;

public class ModificarPresupuesto extends ABMPresupuesto {
	
	private ModificarPresupuestoView view;
	private PresupuestoABMModel abmModel = new PresupuestoABMModel();
	private LineaPresupuestoTableModel tableModel;
	
	public ModificarPresupuesto(JFrame owner) {
		this.view = new ModificarPresupuestoView(owner);
		this.init();
	}
	
	public ModificarPresupuesto(JDialog owner) throws Exception {
		this.view = new ModificarPresupuestoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getCbTipo().setEnabled(false);
		
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	public void instanciarTableModel(Tipo tipoPresupuesto) throws Exception {
		if (tipoPresupuesto.equals(Tipo.GASTOS)) {
			this.tableModel = new LineaPresupuestoGastosABMTableModel();
		}
		else if (tipoPresupuesto.equals(Tipo.RECURSOS)) {
			this.tableModel = new LineaPresupuestoRecursoABMTableModel();
		}
		this.getView().setTableModel(this.getTableModel());
		this.setTableCellEditor();
	}

	
//	public void setTableModel() {
//		try {
//			if (this.getAbmModel().getObjetoABM().getTipo().equals(Presupuesto.Tipo.GASTOS)) {
//				this.tableModel = new LineaPresupuestoGastosABMTableModel();
//			}
//			else if (this.getAbmModel().getObjetoABM().getTipo().equals(Presupuesto.Tipo.RECURSOS)) {
//				this.tableModel = new LineaPresupuestoRecursoABMTableModel();
//			}
//			this.getView().setTableModel(this.getTableModel());
//		}
//		catch (Exception ex) {
//			ex.printStackTrace();
//			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
//		}
//	}
	
	@Override
	public PresupuestoABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	public ModificarPresupuestoView getView() {
		return this.view;
	}
	
	@Override
	public LineaPresupuestoTableModel getTableModel() {
		return this.tableModel;
	}
	
	void modificarPresupuesto() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {
	private ModificarPresupuesto controller;
	public BtnModificarListener(ModificarPresupuesto controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.getView().getPnlTabla().getTblDatos().isEditing()) {
				this.controller.getView().getPnlTabla().getTblDatos().getCellEditor().stopCellEditing();
			}
			this.controller.modificarPresupuesto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		}
	}
}
