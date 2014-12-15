package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AsociacionCuentaSolicitudSuministroABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.FirmaPermisoTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.LineaSolicitudSuministroTableModel;
import com.trascender.gui.framework.main.AppManager;

public class ModificarAsociacionCuentaSolicitudSuministro extends ABMAsociacionCuentaSolicitudSuministro {
	
	private ModificarAsociacionCuentaSolicitudSuministroView view;
	private AsociacionCuentaSolicitudSuministroABMModel abmModel = new AsociacionCuentaSolicitudSuministroABMModel();
	private FirmaPermisoTableModel firmaPermisoTableModel = new FirmaPermisoTableModel();
	private LineaSolicitudSuministroTableModel lineaSolicitudSuministroTableModel = new LineaSolicitudSuministroTableModel();
	
	public ModificarAsociacionCuentaSolicitudSuministro(JDialog owner) throws Exception {
		this.view = new ModificarAsociacionCuentaSolicitudSuministroView(owner);
//		this.getAbmModel().setObjetoABM(new SolicitudSuministro());
		this.init();
	}
	
	public ModificarAsociacionCuentaSolicitudSuministro(JFrame owner) throws Exception {
		this.view = new ModificarAsociacionCuentaSolicitudSuministroView(owner);
		this.getAbmModel().setObjetoABM(new SolicitudSuministro());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}

	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnModificarListener(this));
	}
	
	@Override
	public AsociacionCuentaSolicitudSuministroABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ModificarAsociacionCuentaSolicitudSuministroView getView() {
		return this.view;
	}
	
	void modificarAsociacionCuentaSolicitudSuministro() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().modificar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

	@Override
	public FirmaPermisoTableModel getFirmaPermisoTableModel() {
		return this.firmaPermisoTableModel;
	}

	@Override
	public LineaSolicitudSuministroTableModel getLineaSolicitudSuministroTableModel() {
		return this.lineaSolicitudSuministroTableModel;
	}

}

class BtnModificarListener implements ActionListener {

	private ModificarAsociacionCuentaSolicitudSuministro controller;
	
	public BtnModificarListener(
			ModificarAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.modificarAsociacionCuentaSolicitudSuministro();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

