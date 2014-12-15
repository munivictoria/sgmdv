package com.trascender.contabilidad.gui.abmParametroRetencion.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmParametroRetencion.ParametroRetencionABMModel;
import com.trascender.contabilidad.recurso.persistent.ParametroRetencion;
import com.trascender.gui.framework.main.AppManager;

public class AgregarParametroRetencion extends ABMParametroRetencion {
	
	private AgregarParametroRetencionView view;
	private ParametroRetencionABMModel abmModel = new ParametroRetencionABMModel();

	public AgregarParametroRetencion(JDialog owner) {
		this.view = new AgregarParametroRetencionView(owner);
		this.abmModel.setObjetoABM(new ParametroRetencion());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	@Override
	public ParametroRetencionABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public AgregarParametroRetencionView getView() {
		return this.view;
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarListener(this));
	}

	
	void agregarParametroRetencion() throws Exception {
		if (validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnAgregarListener implements ActionListener {

	private AgregarParametroRetencion controller;
	
	public BtnAgregarListener(AgregarParametroRetencion controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarParametroRetencion();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
