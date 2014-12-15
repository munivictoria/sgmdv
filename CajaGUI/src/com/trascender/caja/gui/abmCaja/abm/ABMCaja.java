package com.trascender.caja.gui.abmCaja.abm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.caja.gui.abmCaja.CajaABMModel;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMCaja extends ABMController<Caja> {

	public abstract ABMCajaView getView();
	public abstract CajaABMModel getAbmModel();
	
	@Override
	protected void init() {
		super.init();
		this.setModels();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
	}
	
	@Override
	public void actualizarABMModel() {
		CajaABMModel locModel = this.getAbmModel();
		
		locModel.setIdentificador(Conversor.getNullSiVacio(this.getView().getTfIdentificador().getText()));
		locModel.setIp(Conversor.getNullSiVacio(this.getView().getTfIp().getText()));
		locModel.setNombre(Conversor.getNullSiVacio(this.getView().getTfNombre().getText()));
		locModel.setPuerto(Conversor.getNullSiVacio(this.getView().getTfPuerto().getText()));
		
		locModel.fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		this.getView().getTfIdentificador().setText(Conversor.getVacioSiNull(this.getAbmModel().getIdentificador()));
		this.getView().getTfIp().setText(Conversor.getVacioSiNull(this.getAbmModel().getIp()));
		this.getView().getTfNombre().setText(Conversor.getVacioSiNull(this.getAbmModel().getNombre()));
		this.getView().getTfPuerto().setText(Conversor.getVacioSiNull(this.getAbmModel().getPuerto()));
	}

	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Object> attIp = new ArrayList<Object>();
		List<JLabel> lblIp = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfIdentificador().getText());
		lblNulos.add(this.getView().getLblIdentificador());
		
		attNulos.add(this.getView().getTfIp().getText());
		lblNulos.add(this.getView().getLblIp());
		
		attNulos.add(this.getView().getTfNombre().getText());
		lblNulos.add(this.getView().getLblNombre());
		
		attIp.add(this.getView().getTfIp().getText());
		lblIp.add(this.getView().getLblIp());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarIp(attIp, lblIp));
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
