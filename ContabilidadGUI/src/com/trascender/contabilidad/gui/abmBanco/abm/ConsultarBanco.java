package com.trascender.contabilidad.gui.abmBanco.abm;

import javax.swing.JDialog;

import com.trascender.contabilidad.gui.abmBanco.BancoABMModel;

public class ConsultarBanco extends ABMBanco {
	
	private ConsultarBancoView view;
	private BancoABMModel abmModel = new BancoABMModel();

	public ConsultarBanco(JDialog owner) {
		this.view = new ConsultarBancoView(owner);
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setDisabledAll();
	}
	
	private void setDisabledAll() {
		this.getView().getTfNombre().setEditable(false);
		this.getView().getTfSucursal().setEditable(false);
	}

	@Override
	public BancoABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public ABMBancoView getView() {
		return this.view;
	}
}
