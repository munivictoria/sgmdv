package com.trascender.contabilidad.gui.abmMayor.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmLineaMayor.LineaMayorTableModel;
import com.trascender.contabilidad.gui.abmMayor.MayorABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarMayor extends ABMMayor {

	private ModificarMayorView view;
	private MayorABMModel abmModel = new MayorABMModel();
	private LineaMayorTableModel tableModel = new LineaMayorTableModel();
	
	public ModificarMayor(JDialog owner) throws Exception {
		this.view = new ModificarMayorView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners(); //ACA
//		this.getView().getTfMes().setEditable(false);
		this.getView().getTfAnio().setEditable(false);
		this.getView().getPnlBotonesSeleccionCuenta().setVisible(false);
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	@Override
	public MayorABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public LineaMayorTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	public ModificarMayorView getView() {
		return this.view;
	}

	void modificarMayor() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarListener implements ActionListener {

	private ModificarMayor controller;
	
	public BtnModificarListener(ModificarMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
