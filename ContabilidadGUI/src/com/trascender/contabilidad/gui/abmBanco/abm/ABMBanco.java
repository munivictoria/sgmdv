package com.trascender.contabilidad.gui.abmBanco.abm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmBanco.BancoABMModel;
import com.trascender.contabilidad.recurso.persistent.Banco;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMBanco extends ABMController<Banco> {

	public abstract ABMBancoView getView();
	public abstract BancoABMModel getAbmModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(getAbmModel());
	}
	
	private void setListeners() {
		
	}
	
	@Override
	public void actualizarABMModel() {
		BancoABMModel locModel = this.getAbmModel();
		
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setSucursal(Conversor.getNullSiVacio(this.getView().getTfSucursal().getText()));
		
		locModel.fireActualizarDatos();
	}
	
	@Override
	public void actualizarView() {
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getTfSucursal().setText(Conversor.getVacioSiNull(this.getAbmModel().getSucursal()));
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attNulos.add(this.getView().getTfSucursal().getText());
		lblNulos.add(this.getView().getLblSucursal());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
}
