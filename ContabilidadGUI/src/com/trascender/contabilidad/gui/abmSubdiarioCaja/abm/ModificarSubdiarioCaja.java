package com.trascender.contabilidad.gui.abmSubdiarioCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.LineaSubdiarioCajaTableModel;
import com.trascender.contabilidad.gui.abmSubdiarioCaja.SubdiarioCajaABMModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarSubdiarioCaja extends ABMSubdiarioCaja {
	
	private ModificarSubdiarioCajaView view;
	private SubdiarioCajaABMModel abmModel = new SubdiarioCajaABMModel();
	private LineaSubdiarioCajaTableModel tableModel = new LineaSubdiarioCajaTableModel();
	
	public ModificarSubdiarioCaja(JFrame owner) throws Exception {
		this.view = new ModificarSubdiarioCajaView(owner);
		this.init();
	}
	
	public ModificarSubdiarioCaja(JDialog owner) throws Exception {
		this.view = new ModificarSubdiarioCajaView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		boolean flag = false;
		this.getView().getFtfFechaCreacion().setEditable(flag);
		this.getView().getCbTipo().setEnabled(flag);
		this.getView().getBtnGenerarSubdiario().setVisible(flag);
		this.getView().getTfPlanDeCuenta().setVisible(flag);
		this.getView().getLblPlanDeCuenta().setVisible(flag);
		this.getView().getPnlBotonesSeleccionPlanDeCuenta().setVisible(flag);
		
		this.getView().getPnlBotonesTabla().getBtnModificar().setVisible(true);
		
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarSubdiarioCajaListener(this));
	}
	
	@Override
	public SubdiarioCajaABMModel getAbmModel() {
		return this.abmModel;
	}
	
	@Override
	public LineaSubdiarioCajaTableModel getTableModel() {
		return this.tableModel;
	}
	
	@Override
	public ModificarSubdiarioCajaView getView() {
		return this.view;
	}
	
	void modificarSubdiarioCaja() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
}

class BtnModificarSubdiarioCajaListener implements ActionListener {
	private ModificarSubdiarioCaja controller;
	public BtnModificarSubdiarioCajaListener(ModificarSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

