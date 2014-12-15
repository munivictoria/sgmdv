package com.trascender.contabilidad.gui.abmLineaLibroBanco.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmLineaLibroBanco.LineaLibroBancoABMModel;
import com.trascender.contabilidad.recurso.persistent.LibroBanco;
import com.trascender.contabilidad.recurso.persistent.LineaLibroBanco;
import com.trascender.gui.framework.main.AppManager;

public class AgregarLineaLibroBanco extends ABMLineaLibroBanco {

	private AgregarLineaLibroBancoView view;
	public LineaLibroBancoABMModel abmModel = new LineaLibroBancoABMModel();
	
	public AgregarLineaLibroBanco(JDialog owner, LibroBanco pLibroBanco) throws Exception {
		this.view = new AgregarLineaLibroBancoView(owner);
		
		LineaLibroBanco locLineaLibroBanco = new LineaLibroBanco();
		pLibroBanco.getLineasLibroBanco().add(locLineaLibroBanco);
		
		this.abmModel.setObjetoABM(new LineaLibroBanco());
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
	public LineaLibroBancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMLineaLibroBancoView getView() {
		return this.view;
	}
	
	void agregarLineaLibroBanco() throws Exception {
		
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.setOperacionRealizada(true);
			this.close();
		}
	}

}

class BtnAgregarListener implements ActionListener {
	private AgregarLineaLibroBanco controller;
	
	public BtnAgregarListener(AgregarLineaLibroBanco controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarLineaLibroBanco();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}
