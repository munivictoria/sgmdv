package com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmAsociacionCuentaTipoTasa.AsociacionCuentaTipoTasaABMModel;
import com.trascender.contabilidad.recurso.persistent.CuentaTipoTasa;
import com.trascender.gui.framework.main.AppManager;

public class AgregarAsociacionCuentaTipoTasa extends ABMAsociacionCuentaTipoTasa {

	private AgregarAsociacionCuentaTipoTasaView view;
	private AsociacionCuentaTipoTasaABMModel abmModel = new AsociacionCuentaTipoTasaABMModel();
	
	
	public AgregarAsociacionCuentaTipoTasa(JDialog pDialig) {
		this.view = new AgregarAsociacionCuentaTipoTasaView(pDialig);
		this.getAbmModel().setObjetoABM(new CuentaTipoTasa());
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
	
	void agregarAsociacionCuentaTipoTasa () throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
	
	@Override
	public AsociacionCuentaTipoTasaABMModel getAbmModel() {
		return abmModel;
	}

	@Override
	public ABMAsociacionCuentaTipoTasaView getView() {
		return view;
	}

}

class BtnAgregarListener implements ActionListener {

	private AgregarAsociacionCuentaTipoTasa controller;
	
	public BtnAgregarListener(AgregarAsociacionCuentaTipoTasa controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsociacionCuentaTipoTasa();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}