package com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.AsociacionCuentaSolicitudSuministroABMModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.FirmaPermisoTableModel;
import com.trascender.contabilidad.gui.abmAsociacionCuentaSolicitudSuministro.LineaSolicitudSuministroTableModel;
import com.trascender.gui.framework.main.AppManager;

public class AgregarAsociacionCuentaSolicitudSuministro extends ABMAsociacionCuentaSolicitudSuministro {
	
	private AgregarAsociacionCuentaSolicitudSuministroView view;
	private AsociacionCuentaSolicitudSuministroABMModel abmModel = new AsociacionCuentaSolicitudSuministroABMModel();
	private FirmaPermisoTableModel firmaPermisoTableModel = new FirmaPermisoTableModel();
	private LineaSolicitudSuministroTableModel lineaSolicitudSuministroTableModel = new LineaSolicitudSuministroTableModel();
	
	public AgregarAsociacionCuentaSolicitudSuministro(JDialog owner) throws Exception {
		this.view = new AgregarAsociacionCuentaSolicitudSuministroView(owner);
		this.getAbmModel().setObjetoABM(new SolicitudSuministro());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}

	@Override
	public AsociacionCuentaSolicitudSuministroABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarAsociacionCuentaSolicitudSuministroView getView() {
		return this.view;
	}
	
	void agregarAsociacionCuentaSolicitudSuministro() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
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

class BtnAgregarListener implements ActionListener {

	private AgregarAsociacionCuentaSolicitudSuministro controller;
	
	public BtnAgregarListener(
			AgregarAsociacionCuentaSolicitudSuministro controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacionCuentaSolicitudSuministro();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

