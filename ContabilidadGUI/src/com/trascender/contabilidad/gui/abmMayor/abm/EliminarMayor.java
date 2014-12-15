package com.trascender.contabilidad.gui.abmMayor.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmLineaMayor.LineaMayorTableModel;
import com.trascender.contabilidad.gui.abmMayor.MayorABMModel;
import com.trascender.gui.framework.main.AppManager;

public class EliminarMayor extends ABMMayor {

	private EliminarMayorView view;
	private MayorABMModel abmModel = new MayorABMModel();
	private LineaMayorTableModel tableModel = new LineaMayorTableModel();

	public EliminarMayor(JDialog owner) throws Exception {
		this.view = new EliminarMayorView(owner);
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.disabledAll();
	}

	private void disabledAll() {
		this.getView().getTfMes().setEnabled(false);
		this.getView().getTfAnio().setEnabled(false);
		this.getView().getTfCuenta().setEnabled(false);
		this.getView().getCbTipo().setEnabled(false);
		this.getView().getBtnGenerarMayor().setVisible(false);
		this.getView().getPnlBotonesSeleccionCuenta().setVisible(false);
		
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(
				new BtnEliminarListener(this));
	}

	@Override
	public MayorABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public EliminarMayorView getView() {
		return this.view;
	}

	void eliminarMayor() throws Exception {
		this.getAbmModel().eliminar();
		this.setOperacionRealizada(true);
		this.close();
	}

	@Override
	public LineaMayorTableModel getTableModel() {
		return this.tableModel;
	}

}

class BtnEliminarListener implements ActionListener {

	private EliminarMayor controller;

	public BtnEliminarListener(EliminarMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.eliminarMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}

}