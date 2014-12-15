package com.trascender.contabilidad.gui.abmAsientoContable.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmAsientoContable.AsientoContableABMModel;
import com.trascender.contabilidad.gui.abmLineaAsientoContable.LineaAsientoContableTableModel;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AgregarAsientoContable extends ABMAsientoContable {
	
	private AgregarAsientoContableView view;
	private AsientoContableABMModel abmModel = new AsientoContableABMModel();
	private LineaAsientoContableTableModel tableModel = new LineaAsientoContableTableModel();
	
	public AgregarAsientoContable(JFrame owner) throws Exception {
		this.view = new AgregarAsientoContableView(owner);
		this.abmModel.setObjetoABM(new AsientoContable());
		this.init();
	}
	
	public AgregarAsientoContable(JDialog owner) throws Exception {
		this.view = new AgregarAsientoContableView(owner);
		this.abmModel.setObjetoABM(new AsientoContable());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getFtfFecha().setText(Conversor.getString(Calendar.getInstance().getTime()));
		
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlBtnTabla().getBtnAgregar().addActionListener(new BtnEliminarLineaAsientoContableListener(this));
		
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarAsientoContableListener(this));
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
	public AgregarAsientoContableView getView() {
		return this.view;
	}

	void agregarAsientoContable() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
	void eliminarLineaAsientoContable() throws Exception {
		int fila = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
		this.getTableModel().deleteRow(fila);
		
		this.getAbmModel().getObjetoABM().getLineasAsientoContable();
		this.getView().getAbmModel().getObjetoABM().getLineasAsientoContable().clear();
		this.getView().getAbmModel().getObjetoABM().getLineasAsientoContable().addAll(this.getTableModel().getListaObjetos());
	}

}

class BtnAgregarAsientoContableListener implements ActionListener {
	private AgregarAsientoContable controller;
	public BtnAgregarAsientoContableListener(AgregarAsientoContable controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnEliminarLineaAsientoContableListener implements ActionListener {
	private AgregarAsientoContable controller;

	public BtnEliminarLineaAsientoContableListener(AgregarAsientoContable controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.controller.eliminarLineaAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
