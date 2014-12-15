package com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaLineaFactura.AsociacionCuentaLineaFacturaABMModel;
import com.trascender.contabilidad.recurso.persistent.CuentaLineaFactura;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AgregarAsociacionCuentaLineaFactura extends ABMAsociacionCuentaLineaFactura {
	
	public AgregarAsociacionCuentaLineaFacturaView view;
	public AsociacionCuentaLineaFacturaABMModel abmModel = new AsociacionCuentaLineaFacturaABMModel();

	public AgregarAsociacionCuentaLineaFactura(JDialog owner) {
		this.view = new AgregarAsociacionCuentaLineaFacturaView(owner);
		this.getAbmModel().setObjetoABM(new CuentaLineaFactura());
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
	public AsociacionCuentaLineaFacturaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarAsociacionCuentaLineaFacturaView getView() {
		return this.view;
	}
	
	@Override
	public void actualizarView() {
		super.actualizarView();
		this.getView().getTfLineaFactura().setText(Conversor.getVacioSiNull(this.getAbmModel().getObjetoABM().getLineaFactura().toString()));
	}

	void agregarAsociacionCuentaLineaFactura() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}

class BtnAgregarListener implements ActionListener {

	private AgregarAsociacionCuentaLineaFactura controller;
	
	public BtnAgregarListener(AgregarAsociacionCuentaLineaFactura controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacionCuentaLineaFactura();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
