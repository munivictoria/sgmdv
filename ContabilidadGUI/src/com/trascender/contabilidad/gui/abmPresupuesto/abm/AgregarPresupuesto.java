package com.trascender.contabilidad.gui.abmPresupuesto.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmPresupuesto.PresupuestoABMModel;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo;
import com.trascender.gui.framework.main.AppManager;

public class AgregarPresupuesto extends ABMPresupuesto {
	
	private AgregarPresupuestoView view;
	private PresupuestoABMModel abmModel = new PresupuestoABMModel();
	private LineaPresupuestoTableModel tableModel;
	
	public AgregarPresupuesto(JDialog owner) {
		this.view = new AgregarPresupuestoView(owner);
		this.getAbmModel().setObjetoABM(new Presupuesto());
		this.init();
	}
	
	@Override
	protected void init(){
		super.init();
		this.setListener();
		this.cambiarEstadoBotones(false);
	}
	
	private void setListener() {
		this.getView().getCbTipo().addActionListener(new CambioTipoPresupuestoListener(this));
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}
	
	@Override
	public PresupuestoABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	public AgregarPresupuestoView getView() {
		return this.view;
	}
	
	@Override
	public LineaPresupuestoTableModel getTableModel() {
		return this.tableModel;
	}
	
	void agregarPresupuesto() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
	void cambiarEstadoBotones(Boolean estado) {
		this.getView().getPnlBtnTabla().getBtnAgregar().setEnabled(estado);
		this.getView().getPnlBtnTabla().getBtnEliminar().setEnabled(estado);
		this.getView().getPnlBtnTabla().getBtnQuitarTodos().setEnabled(estado);
	}
	
	void instanciarTableModel(Tipo tipoPresupuesto) throws Exception {
		if (tipoPresupuesto.equals(Tipo.GASTOS)) {
			this.tableModel = new LineaPresupuestoGastosABMTableModel();
		}
		else if (tipoPresupuesto.equals(Tipo.RECURSOS)) {
			this.tableModel = new LineaPresupuestoRecursoABMTableModel();
		}
		this.getView().setTableModel(this.getTableModel());
		this.setTableCellEditor();
	}
	
	void cambioTipoPresupuesto() {
		JComboBox locTipoPresupuestoCb = this.getView().getCbTipo();
		
		if (locTipoPresupuestoCb.getSelectedItem() == Tipo.RECURSOS) {
			try {
				this.instanciarTableModel(Tipo.RECURSOS);
				this.getView().getCbTipo().setEnabled(false);
				this.cambiarEstadoBotones(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}
		
		if (locTipoPresupuestoCb.getSelectedItem() == Tipo.GASTOS) {
			try {
				this.instanciarTableModel(Tipo.GASTOS);
				this.getView().getCbTipo().setEnabled(false);
				this.cambiarEstadoBotones(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			}
		}
	}
	
}


class CambioTipoPresupuestoListener implements ActionListener {
	private AgregarPresupuesto controller;
	public CambioTipoPresupuestoListener(AgregarPresupuesto controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		this.controller.cambioTipoPresupuesto();
	}
}

class BtnAgregarListener implements ActionListener {
	private AgregarPresupuesto controller;
	public BtnAgregarListener(AgregarPresupuesto controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.getView().getPnlTabla().getTblDatos().isEditing()) {
				this.controller.getView().getPnlTabla().getTblDatos().getCellEditor().stopCellEditing();
			}
			this.controller.agregarPresupuesto();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),ex.getMessage());
		}
	}
}

